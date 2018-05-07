package com.eBolivar.service.autenticacion;

import com.eBolivar.dao.interfaces.ICredencialAutenticacionAFIPRepository;
import com.eBolivar.domain.LoginTicketResponse;
import com.eBolivar.service.autenticacion.interfaces.IAutenticacionAFIPService;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.text.ParseException;

@Service
public class AutenticacionAFIPService implements IAutenticacionAFIPService {

    @Autowired
    private ICredencialAutenticacionAFIPRepository dao;

    private LoginTicketResponse getAutentication() {
        String LoginTicketResponse = null;

        System.setProperty("http.proxyHost", "");
        System.setProperty("http.proxyPort", "80");

        String endpoint = "https://wsaa.afip.gov.ar/ws/services/LoginCms";
//        String dstDN = "CN=wsaa, O=AFIP, C=AR, SERIALNUMBER=CUIT 33693450239";
//        String p12file = "C:\\certs\\afip\\produ\\fedeberon_store.pfx";
//
//        String service = "ws_sr_padron_a10";
//        String signer = "fedeberon";
//        String p12pass = "123";

        String dstDN = "CN=wsaa, O=AFIP, C=AR, SERIALNUMBER=CUIT 33693450239";
//        String p12file = "C:\\certificados_AFIP\\muni_bolivar_store.pfx";
        String p12file = "/certificados_AFIP/muni_bolivar_store.pfx";

//        String service = "ws_sr_padron_a10";
        String service = "ws_sr_padron_a5";
        String signer = "muni_bolivar";
        String p12pass = "123";

        byte[] LoginTicketRequest_xml_cms = afip_wsaa_client.create_cms(p12file, p12pass, signer, dstDN, service);
        LoginTicketResponse = afip_wsaa_client.invoke_wsaa(LoginTicketRequest_xml_cms, endpoint);

        Reader tokenReader = new StringReader(LoginTicketResponse);

        try {
            Document tokenDoc = new SAXReader(false).read(tokenReader);
            String uniqueId = tokenDoc.valueOf("/loginTicketResponse/header/uniqueId");
            String token = tokenDoc.valueOf("/loginTicketResponse/credentials/token");
            String sign = tokenDoc.valueOf("/loginTicketResponse/credentials/sign");
            String generationTime = tokenDoc.valueOf("/loginTicketResponse/header/generationTime");
            generationTime = generationTime.substring(0, 19);
            String expirationTime = tokenDoc.valueOf("/loginTicketResponse/header/expirationTime");
            expirationTime = expirationTime.substring(0, 19);
            LoginTicketResponse loginTicketResponse = new LoginTicketResponse(uniqueId, token, sign, generationTime, expirationTime);

            return loginTicketResponse;

        } catch (DocumentException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private LoginTicketResponse get() {
        return dao.get();
    }


    private LoginTicketResponse save(LoginTicketResponse loginTicketResponse) {
        return dao.save(loginTicketResponse);
    }

    @Override
    public LoginTicketResponse obtenerCredenciales() {
        LoginTicketResponse auth = this.get();
        if (auth == null) {
            auth = this.getAutentication();
            this.save(auth);
        }

        return auth;
    }

    public LoginTicketResponse convert(String xml) throws ParserConfigurationException, IOException, SAXException, JAXBException, XMLStreamException {
        JAXBContext jc = JAXBContext.newInstance(LoginTicketResponse.class);
        StringReader reader = new StringReader(xml);
        XMLInputFactory xif = XMLInputFactory.newFactory();
        XMLStreamReader xmlReader = xif.createXMLStreamReader(reader);
        Unmarshaller jaxbUnmarshaller = jc.createUnmarshaller();
        JAXBElement<LoginTicketResponse> jb = jaxbUnmarshaller.unmarshal(xmlReader, LoginTicketResponse.class);
        xmlReader.close();
        LoginTicketResponse credencial = jb.getValue();

        return credencial;
    }

}
