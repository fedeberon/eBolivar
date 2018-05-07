/**
 * PersonaServiceA5_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class PersonaServiceA5_ServiceLocator extends org.apache.axis.client.Service implements com.eBolivar.service.contribuyente.PersonaServiceA5_Service {

    public PersonaServiceA5_ServiceLocator() {
    }


    public PersonaServiceA5_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PersonaServiceA5_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PersonaServiceA5Port
    private java.lang.String PersonaServiceA5Port_address = "http://aws.afip.gov.ar/sr-padron/webservices/personaServiceA5";

    public java.lang.String getPersonaServiceA5PortAddress() {
        return PersonaServiceA5Port_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PersonaServiceA5PortWSDDServiceName = "PersonaServiceA5Port";

    public java.lang.String getPersonaServiceA5PortWSDDServiceName() {
        return PersonaServiceA5PortWSDDServiceName;
    }

    public void setPersonaServiceA5PortWSDDServiceName(java.lang.String name) {
        PersonaServiceA5PortWSDDServiceName = name;
    }

    public com.eBolivar.service.contribuyente.PersonaServiceA5_PortType getPersonaServiceA5Port() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PersonaServiceA5Port_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPersonaServiceA5Port(endpoint);
    }

    public com.eBolivar.service.contribuyente.PersonaServiceA5_PortType getPersonaServiceA5Port(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.eBolivar.service.contribuyente.PersonaServiceA5SoapBindingStub _stub = new com.eBolivar.service.contribuyente.PersonaServiceA5SoapBindingStub(portAddress, this);
            _stub.setPortName(getPersonaServiceA5PortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPersonaServiceA5PortEndpointAddress(java.lang.String address) {
        PersonaServiceA5Port_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.eBolivar.service.contribuyente.PersonaServiceA5_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.eBolivar.service.contribuyente.PersonaServiceA5SoapBindingStub _stub = new com.eBolivar.service.contribuyente.PersonaServiceA5SoapBindingStub(new java.net.URL(PersonaServiceA5Port_address), this);
                _stub.setPortName(getPersonaServiceA5PortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PersonaServiceA5Port".equals(inputPortName)) {
            return getPersonaServiceA5Port();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "PersonaServiceA5");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "PersonaServiceA5Port"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PersonaServiceA5Port".equals(portName)) {
            setPersonaServiceA5PortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
