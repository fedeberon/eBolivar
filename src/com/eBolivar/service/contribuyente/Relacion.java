/**
 * Relacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Relacion  implements java.io.Serializable {

    @XmlElement
    private java.lang.String apellidoPersonaAsociada;

    @XmlElement
    private java.util.Calendar ffRelacion;

    @XmlElement
    private java.util.Calendar ffVencimiento;

    @XmlElement
    private java.lang.Long idPersonaAsociada;

    @XmlElement
    private java.lang.String nombrePersonaAsociada;

    @XmlElement
    private java.lang.String razonSocialPersonaAsociada;

    @XmlElement
    private java.lang.String tipoComponente;

    public Relacion() {
    }

    public Relacion(
            java.lang.String apellidoPersonaAsociada,
            java.util.Calendar ffRelacion,
            java.util.Calendar ffVencimiento,
            java.lang.Long idPersonaAsociada,
            java.lang.String nombrePersonaAsociada,
            java.lang.String razonSocialPersonaAsociada,
            java.lang.String tipoComponente) {
        this.apellidoPersonaAsociada = apellidoPersonaAsociada;
        this.ffRelacion = ffRelacion;
        this.ffVencimiento = ffVencimiento;
        this.idPersonaAsociada = idPersonaAsociada;
        this.nombrePersonaAsociada = nombrePersonaAsociada;
        this.razonSocialPersonaAsociada = razonSocialPersonaAsociada;
        this.tipoComponente = tipoComponente;
    }


    /**
     * Gets the apellidoPersonaAsociada value for this Relacion.
     *
     * @return apellidoPersonaAsociada
     */
    public java.lang.String getApellidoPersonaAsociada() {
        return apellidoPersonaAsociada;
    }


    /**
     * Sets the apellidoPersonaAsociada value for this Relacion.
     *
     * @param apellidoPersonaAsociada
     */
    public void setApellidoPersonaAsociada(java.lang.String apellidoPersonaAsociada) {
        this.apellidoPersonaAsociada = apellidoPersonaAsociada;
    }


    /**
     * Gets the ffRelacion value for this Relacion.
     *
     * @return ffRelacion
     */
    public java.util.Calendar getFfRelacion() {
        return ffRelacion;
    }


    /**
     * Sets the ffRelacion value for this Relacion.
     *
     * @param ffRelacion
     */
    public void setFfRelacion(java.util.Calendar ffRelacion) {
        this.ffRelacion = ffRelacion;
    }


    /**
     * Gets the ffVencimiento value for this Relacion.
     *
     * @return ffVencimiento
     */
    public java.util.Calendar getFfVencimiento() {
        return ffVencimiento;
    }


    /**
     * Sets the ffVencimiento value for this Relacion.
     *
     * @param ffVencimiento
     */
    public void setFfVencimiento(java.util.Calendar ffVencimiento) {
        this.ffVencimiento = ffVencimiento;
    }


    /**
     * Gets the idPersonaAsociada value for this Relacion.
     *
     * @return idPersonaAsociada
     */
    public java.lang.Long getIdPersonaAsociada() {
        return idPersonaAsociada;
    }


    /**
     * Sets the idPersonaAsociada value for this Relacion.
     *
     * @param idPersonaAsociada
     */
    public void setIdPersonaAsociada(java.lang.Long idPersonaAsociada) {
        this.idPersonaAsociada = idPersonaAsociada;
    }


    /**
     * Gets the nombrePersonaAsociada value for this Relacion.
     *
     * @return nombrePersonaAsociada
     */
    public java.lang.String getNombrePersonaAsociada() {
        return nombrePersonaAsociada;
    }


    /**
     * Sets the nombrePersonaAsociada value for this Relacion.
     *
     * @param nombrePersonaAsociada
     */
    public void setNombrePersonaAsociada(java.lang.String nombrePersonaAsociada) {
        this.nombrePersonaAsociada = nombrePersonaAsociada;
    }


    /**
     * Gets the razonSocialPersonaAsociada value for this Relacion.
     *
     * @return razonSocialPersonaAsociada
     */
    public java.lang.String getRazonSocialPersonaAsociada() {
        return razonSocialPersonaAsociada;
    }


    /**
     * Sets the razonSocialPersonaAsociada value for this Relacion.
     *
     * @param razonSocialPersonaAsociada
     */
    public void setRazonSocialPersonaAsociada(java.lang.String razonSocialPersonaAsociada) {
        this.razonSocialPersonaAsociada = razonSocialPersonaAsociada;
    }


    /**
     * Gets the tipoComponente value for this Relacion.
     *
     * @return tipoComponente
     */
    public java.lang.String getTipoComponente() {
        return tipoComponente;
    }


    /**
     * Sets the tipoComponente value for this Relacion.
     *
     * @param tipoComponente
     */
    public void setTipoComponente(java.lang.String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Relacion)) return false;
        Relacion other = (Relacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true &&
                ((this.apellidoPersonaAsociada==null && other.getApellidoPersonaAsociada()==null) ||
                        (this.apellidoPersonaAsociada!=null &&
                                this.apellidoPersonaAsociada.equals(other.getApellidoPersonaAsociada()))) &&
                ((this.ffRelacion==null && other.getFfRelacion()==null) ||
                        (this.ffRelacion!=null &&
                                this.ffRelacion.equals(other.getFfRelacion()))) &&
                ((this.ffVencimiento==null && other.getFfVencimiento()==null) ||
                        (this.ffVencimiento!=null &&
                                this.ffVencimiento.equals(other.getFfVencimiento()))) &&
                ((this.idPersonaAsociada==null && other.getIdPersonaAsociada()==null) ||
                        (this.idPersonaAsociada!=null &&
                                this.idPersonaAsociada.equals(other.getIdPersonaAsociada()))) &&
                ((this.nombrePersonaAsociada==null && other.getNombrePersonaAsociada()==null) ||
                        (this.nombrePersonaAsociada!=null &&
                                this.nombrePersonaAsociada.equals(other.getNombrePersonaAsociada()))) &&
                ((this.razonSocialPersonaAsociada==null && other.getRazonSocialPersonaAsociada()==null) ||
                        (this.razonSocialPersonaAsociada!=null &&
                                this.razonSocialPersonaAsociada.equals(other.getRazonSocialPersonaAsociada()))) &&
                ((this.tipoComponente==null && other.getTipoComponente()==null) ||
                        (this.tipoComponente!=null &&
                                this.tipoComponente.equals(other.getTipoComponente())));
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
        if (getApellidoPersonaAsociada() != null) {
            _hashCode += getApellidoPersonaAsociada().hashCode();
        }
        if (getFfRelacion() != null) {
            _hashCode += getFfRelacion().hashCode();
        }
        if (getFfVencimiento() != null) {
            _hashCode += getFfVencimiento().hashCode();
        }
        if (getIdPersonaAsociada() != null) {
            _hashCode += getIdPersonaAsociada().hashCode();
        }
        if (getNombrePersonaAsociada() != null) {
            _hashCode += getNombrePersonaAsociada().hashCode();
        }
        if (getRazonSocialPersonaAsociada() != null) {
            _hashCode += getRazonSocialPersonaAsociada().hashCode();
        }
        if (getTipoComponente() != null) {
            _hashCode += getTipoComponente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
            new org.apache.axis.description.TypeDesc(Relacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "relacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellidoPersonaAsociada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "apellidoPersonaAsociada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ffRelacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ffRelacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ffVencimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ffVencimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPersonaAsociada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idPersonaAsociada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePersonaAsociada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nombrePersonaAsociada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("razonSocialPersonaAsociada");
        elemField.setXmlName(new javax.xml.namespace.QName("", "razonSocialPersonaAsociada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoComponente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoComponente"));
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
