/**
 * Impuesto.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Impuesto  implements java.io.Serializable {

    @XmlElement
    private java.lang.String descripcionImpuesto;

    @XmlElement
    private java.lang.Integer idImpuesto;

    @XmlElement
    private java.lang.Integer periodo;

    public Impuesto() {
    }

    public Impuesto(
            java.lang.String descripcionImpuesto,
            java.lang.Integer idImpuesto,
            java.lang.Integer periodo) {
        this.descripcionImpuesto = descripcionImpuesto;
        this.idImpuesto = idImpuesto;
        this.periodo = periodo;
    }


    /**
     * Gets the descripcionImpuesto value for this Impuesto.
     *
     * @return descripcionImpuesto
     */
    public java.lang.String getDescripcionImpuesto() {
        return descripcionImpuesto;
    }


    /**
     * Sets the descripcionImpuesto value for this Impuesto.
     *
     * @param descripcionImpuesto
     */
    public void setDescripcionImpuesto(java.lang.String descripcionImpuesto) {
        this.descripcionImpuesto = descripcionImpuesto;
    }


    /**
     * Gets the idImpuesto value for this Impuesto.
     *
     * @return idImpuesto
     */
    public java.lang.Integer getIdImpuesto() {
        return idImpuesto;
    }


    /**
     * Sets the idImpuesto value for this Impuesto.
     *
     * @param idImpuesto
     */
    public void setIdImpuesto(java.lang.Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }


    /**
     * Gets the periodo value for this Impuesto.
     *
     * @return periodo
     */
    public java.lang.Integer getPeriodo() {
        return periodo;
    }


    /**
     * Sets the periodo value for this Impuesto.
     *
     * @param periodo
     */
    public void setPeriodo(java.lang.Integer periodo) {
        this.periodo = periodo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Impuesto)) return false;
        Impuesto other = (Impuesto) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                ((this.descripcionImpuesto==null && other.getDescripcionImpuesto()==null) ||
                        (this.descripcionImpuesto!=null &&
                                this.descripcionImpuesto.equals(other.getDescripcionImpuesto()))) &&
                ((this.idImpuesto==null && other.getIdImpuesto()==null) ||
                        (this.idImpuesto!=null &&
                                this.idImpuesto.equals(other.getIdImpuesto()))) &&
                ((this.periodo==null && other.getPeriodo()==null) ||
                        (this.periodo!=null &&
                                this.periodo.equals(other.getPeriodo())));
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
        if (getDescripcionImpuesto() != null) {
            _hashCode += getDescripcionImpuesto().hashCode();
        }
        if (getIdImpuesto() != null) {
            _hashCode += getIdImpuesto().hashCode();
        }
        if (getPeriodo() != null) {
            _hashCode += getPeriodo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(Impuesto.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "impuesto"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionImpuesto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcionImpuesto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idImpuesto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idImpuesto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("periodo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "periodo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
