/**
 * Metadata.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class Metadata  implements java.io.Serializable {
    private java.util.Calendar fechaHora;

    private java.lang.String servidor;

    public Metadata() {
    }

    public Metadata(
           java.util.Calendar fechaHora,
           java.lang.String servidor) {
           this.fechaHora = fechaHora;
           this.servidor = servidor;
    }


    /**
     * Gets the fechaHora value for this Metadata.
     * 
     * @return fechaHora
     */
    public java.util.Calendar getFechaHora() {
        return fechaHora;
    }


    /**
     * Sets the fechaHora value for this Metadata.
     * 
     * @param fechaHora
     */
    public void setFechaHora(java.util.Calendar fechaHora) {
        this.fechaHora = fechaHora;
    }


    /**
     * Gets the servidor value for this Metadata.
     * 
     * @return servidor
     */
    public java.lang.String getServidor() {
        return servidor;
    }


    /**
     * Sets the servidor value for this Metadata.
     * 
     * @param servidor
     */
    public void setServidor(java.lang.String servidor) {
        this.servidor = servidor;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Metadata)) return false;
        Metadata other = (Metadata) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaHora==null && other.getFechaHora()==null) || 
             (this.fechaHora!=null &&
              this.fechaHora.equals(other.getFechaHora()))) &&
            ((this.servidor==null && other.getServidor()==null) || 
             (this.servidor!=null &&
              this.servidor.equals(other.getServidor())));
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
        if (getFechaHora() != null) {
            _hashCode += getFechaHora().hashCode();
        }
        if (getServidor() != null) {
            _hashCode += getServidor().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Metadata.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "metadata"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaHora");
        elemField.setXmlName(new javax.xml.namespace.QName("", "fechaHora"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("servidor");
        elemField.setXmlName(new javax.xml.namespace.QName("", "servidor"));
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
