package com.eBolivar.service.persona.service;

import com.eBolivar.dao.interfaces.IPersonaRepository;
import com.eBolivar.domain.*;
import com.eBolivar.service.autenticacion.interfaces.IAutenticacionAFIPService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class PersonaService implements IPersonaService {


    public static String ENDPOINT_PRODUCCION = "https://aws.afip.gov.ar/sr-padron/webservices/personaServiceA5";
    public static String ENDPOINT_HOMOLOGACION = "https://awshomo.afip.gov.ar/sr-padron/webservices/personaServiceA5";

    @Autowired
    private IPersonaRepository dao;

    @Autowired
    private IAutenticacionAFIPService autenticacionAFIPService;

    @Override
    public Persona create_PersonaRequest(String token, String sign, String cuitRepresentada, String idPersona){
        try{
            URL endPoint = new URL(ENDPOINT_PRODUCCION);
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(token, sign, cuitRepresentada, idPersona), endPoint);
            String result = printSOAPResponse(soapResponse);
            PersonaReturn personaReturn = convert(result);
            System.out.print(personaReturn);
            soapConnection.close();
            Persona persona = dao.save(personaReturn.getPersona());
            return persona;
        }
        catch (SAXException e) {
            e.printStackTrace();
            System.out.print("ERROR DE PARSEO ===========> " + e);
            return null;
        } catch (XMLStreamException e) {
            e.printStackTrace();
            System.out.print("ERROR DE PARSEO ===========> " + e);
            return null;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.out.print("ERROR DE PARSEO ===========> " + e);
            return null;
        } catch (ParserConfigurationException e) {
            System.out.print("ERROR DE PARSEO ===========> " + e);
            e.printStackTrace();
            System.out.print("ERROR DE PARSEO ===========> " + e);
            return null;
        } catch (JAXBException e) {
            System.out.print("ERROR DE PARSEO ===========> " + e);
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            System.out.print("ERROR DE PARSEO ===========> " + e);
            e.printStackTrace();
            return null;
        } catch (SOAPException e) {
            System.out.print("ERROR DE PARSEO ===========> " + e);
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.print("ERROR DE PARSEO ===========> " + e);
            e.printStackTrace();
            return null;
        }

    }

    private static SOAPMessage createSOAPRequest(String token, String sign, String cuitRepresentada, String idPersona) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();
        String serverURI = "http://a5.soap.ws.server.puc.sr/";
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("a5", serverURI);
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("getPersona", "a5");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("token");
        soapBodyElem1.addTextNode(token);
        SOAPElement soapBodyElem2 = soapBodyElem.addChildElement("sign");
        soapBodyElem2.addTextNode(sign);
        SOAPElement soapBodyElem3 = soapBodyElem.addChildElement("cuitRepresentada");
        soapBodyElem3.addTextNode(cuitRepresentada);
        SOAPElement soapBodyElem4 = soapBodyElem.addChildElement("idPersona");
        soapBodyElem4.addTextNode(idPersona);
        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", serverURI  + "getPersona");
        soapMessage.saveChanges();

        return soapMessage;
    }

    private String printSOAPResponse(SOAPMessage soapResponse) throws Exception {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        Source sourceContent = soapResponse.getSOAPPart().getContent();
        StringWriter outWriter = new StringWriter();
        StreamResult result = new StreamResult( outWriter );
        transformer.transform( sourceContent, result );
        StringBuffer sb = outWriter.getBuffer();
        String finalstring = sb.toString();

        return finalstring;
    }

    public PersonaReturn convert(String xml) throws ParserConfigurationException, IOException, SAXException, JAXBException, XMLStreamException {
        System.out.print("============> " + xml);

        JAXBContext jc = JAXBContext.newInstance(PersonaReturn.class);
        StringReader reader = new StringReader(xml);
        XMLInputFactory xif = XMLInputFactory.newFactory();
        XMLStreamReader xmlReader = xif.createXMLStreamReader(reader);
        xmlReader.nextTag();
        while(!xmlReader.getLocalName().equals("personaReturn")) {
            xmlReader.nextTag();
        }
        Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
        JAXBElement<PersonaReturn> jb  = jaxbUnmarshaller.unmarshal(xmlReader, PersonaReturn.class);
        xmlReader.close();
        PersonaReturn personaReturn = jb.getValue();

        return personaReturn;
    }

    @Override
    public Persona get(Integer id){
        return dao.get(id);
    }

    @Override
    public Persona getByCUIT(String cuit){
        if(cuit == null || cuit.isEmpty()) return null;

        Persona persona = dao.getByCUIT(cuit);
        if(persona == null){
            LoginTicketResponse credencial = autenticacionAFIPService.obtenerCredenciales();
            persona = this.create_PersonaRequest(credencial.getToken(), credencial.getSign(), Persona.CUIT_REPRESENTADA_MUNICIPALIDAD_BOLIVAR, cuit);
        }

        return persona;
    }

    @Override
    public List<Persona> getByNombreYApellido(String nombre, String apellido){
        return dao.getByNombreYApellido(nombre, apellido);
    }

    @Override
    public List<PadronAsociado> getByPadron(Padron padron){
        List<PadronAsociado> padronesAsociados = dao.getByPadron(padron);
        for (PadronAsociado p : padronesAsociados) {
            Persona persona = this.get(p.getId());
            p.setPersona(persona);
        }

        return padronesAsociados;
    }
}
