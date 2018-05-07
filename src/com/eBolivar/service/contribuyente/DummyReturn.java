/**
 * DummyReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class DummyReturn  implements java.io.Serializable {
    private java.lang.String appserver;

    private java.lang.String authserver;

    private java.lang.String dbserver;

    public DummyReturn() {
    }

    public DummyReturn(
           java.lang.String appserver,
           java.lang.String authserver,
           java.lang.String dbserver) {
           this.appserver = appserver;
           this.authserver = authserver;
           this.dbserver = dbserver;
    }


    /**
     * Gets the appserver value for this DummyReturn.
     * 
     * @return appserver
     */
    public java.lang.String getAppserver() {
        return appserver;
    }


    /**
     * Sets the appserver value for this DummyReturn.
     * 
     * @param appserver
     */
    public void setAppserver(java.lang.String appserver) {
        this.appserver = appserver;
    }


    /**
     * Gets the authserver value for this DummyReturn.
     * 
     * @return authserver
     */
    public java.lang.String getAuthserver() {
        return authserver;
    }


    /**
     * Sets the authserver value for this DummyReturn.
     * 
     * @param authserver
     */
    public void setAuthserver(java.lang.String authserver) {
        this.authserver = authserver;
    }


    /**
     * Gets the dbserver value for this DummyReturn.
     * 
     * @return dbserver
     */
    public java.lang.String getDbserver() {
        return dbserver;
    }


    /**
     * Sets the dbserver value for this DummyReturn.
     * 
     * @param dbserver
     */
    public void setDbserver(java.lang.String dbserver) {
        this.dbserver = dbserver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DummyReturn)) return false;
        DummyReturn other = (DummyReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.appserver==null && other.getAppserver()==null) || 
             (this.appserver!=null &&
              this.appserver.equals(other.getAppserver()))) &&
            ((this.authserver==null && other.getAuthserver()==null) || 
             (this.authserver!=null &&
              this.authserver.equals(other.getAuthserver()))) &&
            ((this.dbserver==null && other.getDbserver()==null) || 
             (this.dbserver!=null &&
              this.dbserver.equals(other.getDbserver())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getAppserver() != null) {
            _hashCode += getAppserver().hashCode();
        }
        if (getAuthserver() != null) {
            _hashCode += getAuthserver().hashCode();
        }
        if (getDbserver() != null) {
            _hashCode += getDbserver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DummyReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "dummyReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("appserver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "appserver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authserver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "authserver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dbserver");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dbserver"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
