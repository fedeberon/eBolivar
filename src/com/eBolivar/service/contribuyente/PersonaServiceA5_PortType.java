/**
 * PersonaServiceA5_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public interface PersonaServiceA5_PortType extends java.rmi.Remote {
    public com.eBolivar.service.contribuyente.PersonaReturn getPersona(java.lang.String token, java.lang.String sign, long cuitRepresentada, long idPersona) throws java.rmi.RemoteException;
    public com.eBolivar.service.contribuyente.DummyReturn dummy() throws java.rmi.RemoteException;
}
