//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.eBolivar.service.persona.domain;

import java.io.Serializable;
import javax.xml.namespace.QName;
import org.apache.axis.description.ElementDesc;
import org.apache.axis.description.TypeDesc;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.encoding.ser.BeanDeserializer;
import org.apache.axis.encoding.ser.BeanSerializer;

public class DummyReturn implements Serializable {
    private String appserver;
    private String authserver;
    private String dbserver;
    private Object __equalsCalc = null;
    private boolean __hashCodeCalc = false;
    private static TypeDesc typeDesc = new TypeDesc(DummyReturn.class, true);

    public DummyReturn() {
    }

    public DummyReturn(String appserver, String authserver, String dbserver) {
        this.appserver = appserver;
        this.authserver = authserver;
        this.dbserver = dbserver;
    }

    public String getAppserver() {
        return this.appserver;
    }

    public void setAppserver(String appserver) {
        this.appserver = appserver;
    }

    public String getAuthserver() {
        return this.authserver;
    }

    public void setAuthserver(String authserver) {
        this.authserver = authserver;
    }

    public String getDbserver() {
        return this.dbserver;
    }

    public void setDbserver(String dbserver) {
        this.dbserver = dbserver;
    }

    public synchronized boolean equals(Object obj) {
        if(!(obj instanceof DummyReturn)) {
            return false;
        } else {
            DummyReturn other = (DummyReturn)obj;
            if(obj == null) {
                return false;
            } else if(this == obj) {
                return true;
            } else if(this.__equalsCalc != null) {
                return this.__equalsCalc == obj;
            } else {
                this.__equalsCalc = obj;
                boolean _equals = (this.appserver == null && other.getAppserver() == null || this.appserver != null && this.appserver.equals(other.getAppserver())) && (this.authserver == null && other.getAuthserver() == null || this.authserver != null && this.authserver.equals(other.getAuthserver())) && (this.dbserver == null && other.getDbserver() == null || this.dbserver != null && this.dbserver.equals(other.getDbserver()));
                this.__equalsCalc = null;
                return _equals;
            }
        }
    }

    public synchronized int hashCode() {
        if(this.__hashCodeCalc) {
            return 0;
        } else {
            this.__hashCodeCalc = true;
            int _hashCode = 1;
            if(this.getAppserver() != null) {
                _hashCode += this.getAppserver().hashCode();
            }

            if(this.getAuthserver() != null) {
                _hashCode += this.getAuthserver().hashCode();
            }

            if(this.getDbserver() != null) {
                _hashCode += this.getDbserver().hashCode();
            }

            this.__hashCodeCalc = false;
            return _hashCode;
        }
    }

    public static TypeDesc getTypeDesc() {
        return typeDesc;
    }

    public static Serializer getSerializer(String mechType, Class _javaType, QName _xmlType) {
        return new BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    public static Deserializer getDeserializer(String mechType, Class _javaType, QName _xmlType) {
        return new BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

    static {
        typeDesc.setXmlType(new QName("http://a10.soap.ws.server.puc.sr/", "dummyReturn"));
        ElementDesc elemField = new ElementDesc();
        elemField.setFieldName("appserver");
        elemField.setXmlName(new QName("", "appserver"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("authserver");
        elemField.setXmlName(new QName("", "authserver"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new ElementDesc();
        elemField.setFieldName("dbserver");
        elemField.setXmlName(new QName("", "dbserver"));
        elemField.setXmlType(new QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }
}
