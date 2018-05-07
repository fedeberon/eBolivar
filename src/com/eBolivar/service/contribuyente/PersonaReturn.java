/**
 * PersonaReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class PersonaReturn  implements java.io.Serializable {
    private com.eBolivar.service.contribuyente.DatosGenerales datosGenerales;

    private com.eBolivar.service.contribuyente.DatosMonotributo datosMonotributo;

    private com.eBolivar.service.contribuyente.DatosRegimenGeneral datosRegimenGeneral;

    private com.eBolivar.service.contribuyente.ErrorConstancia errorConstancia;

    private com.eBolivar.service.contribuyente.ErrorMonotributo errorMonotributo;

    private com.eBolivar.service.contribuyente.ErrorRegimenGeneral errorRegimenGeneral;

    private com.eBolivar.service.contribuyente.Metadata metadata;

    public PersonaReturn() {
    }

    public PersonaReturn(
           com.eBolivar.service.contribuyente.DatosGenerales datosGenerales,
           com.eBolivar.service.contribuyente.DatosMonotributo datosMonotributo,
           com.eBolivar.service.contribuyente.DatosRegimenGeneral datosRegimenGeneral,
           com.eBolivar.service.contribuyente.ErrorConstancia errorConstancia,
           com.eBolivar.service.contribuyente.ErrorMonotributo errorMonotributo,
           com.eBolivar.service.contribuyente.ErrorRegimenGeneral errorRegimenGeneral,
           com.eBolivar.service.contribuyente.Metadata metadata) {
           this.datosGenerales = datosGenerales;
           this.datosMonotributo = datosMonotributo;
           this.datosRegimenGeneral = datosRegimenGeneral;
           this.errorConstancia = errorConstancia;
           this.errorMonotributo = errorMonotributo;
           this.errorRegimenGeneral = errorRegimenGeneral;
           this.metadata = metadata;
    }


    /**
     * Gets the datosGenerales value for this PersonaReturn.
     * 
     * @return datosGenerales
     */
    public com.eBolivar.service.contribuyente.DatosGenerales getDatosGenerales() {
        return datosGenerales;
    }


    /**
     * Sets the datosGenerales value for this PersonaReturn.
     * 
     * @param datosGenerales
     */
    public void setDatosGenerales(com.eBolivar.service.contribuyente.DatosGenerales datosGenerales) {
        this.datosGenerales = datosGenerales;
    }


    /**
     * Gets the datosMonotributo value for this PersonaReturn.
     * 
     * @return datosMonotributo
     */
    public com.eBolivar.service.contribuyente.DatosMonotributo getDatosMonotributo() {
        return datosMonotributo;
    }


    /**
     * Sets the datosMonotributo value for this PersonaReturn.
     * 
     * @param datosMonotributo
     */
    public void setDatosMonotributo(com.eBolivar.service.contribuyente.DatosMonotributo datosMonotributo) {
        this.datosMonotributo = datosMonotributo;
    }


    /**
     * Gets the datosRegimenGeneral value for this PersonaReturn.
     * 
     * @return datosRegimenGeneral
     */
    public com.eBolivar.service.contribuyente.DatosRegimenGeneral getDatosRegimenGeneral() {
        return datosRegimenGeneral;
    }


    /**
     * Sets the datosRegimenGeneral value for this PersonaReturn.
     * 
     * @param datosRegimenGeneral
     */
    public void setDatosRegimenGeneral(com.eBolivar.service.contribuyente.DatosRegimenGeneral datosRegimenGeneral) {
        this.datosRegimenGeneral = datosRegimenGeneral;
    }


    /**
     * Gets the errorConstancia value for this PersonaReturn.
     * 
     * @return errorConstancia
     */
    public com.eBolivar.service.contribuyente.ErrorConstancia getErrorConstancia() {
        return errorConstancia;
    }


    /**
     * Sets the errorConstancia value for this PersonaReturn.
     * 
     * @param errorConstancia
     */
    public void setErrorConstancia(com.eBolivar.service.contribuyente.ErrorConstancia errorConstancia) {
        this.errorConstancia = errorConstancia;
    }


    /**
     * Gets the errorMonotributo value for this PersonaReturn.
     * 
     * @return errorMonotributo
     */
    public com.eBolivar.service.contribuyente.ErrorMonotributo getErrorMonotributo() {
        return errorMonotributo;
    }


    /**
     * Sets the errorMonotributo value for this PersonaReturn.
     * 
     * @param errorMonotributo
     */
    public void setErrorMonotributo(com.eBolivar.service.contribuyente.ErrorMonotributo errorMonotributo) {
        this.errorMonotributo = errorMonotributo;
    }


    /**
     * Gets the errorRegimenGeneral value for this PersonaReturn.
     * 
     * @return errorRegimenGeneral
     */
    public com.eBolivar.service.contribuyente.ErrorRegimenGeneral getErrorRegimenGeneral() {
        return errorRegimenGeneral;
    }


    /**
     * Sets the errorRegimenGeneral value for this PersonaReturn.
     * 
     * @param errorRegimenGeneral
     */
    public void setErrorRegimenGeneral(com.eBolivar.service.contribuyente.ErrorRegimenGeneral errorRegimenGeneral) {
        this.errorRegimenGeneral = errorRegimenGeneral;
    }


    /**
     * Gets the metadata value for this PersonaReturn.
     * 
     * @return metadata
     */
    public com.eBolivar.service.contribuyente.Metadata getMetadata() {
        return metadata;
    }


    /**
     * Sets the metadata value for this PersonaReturn.
     * 
     * @param metadata
     */
    public void setMetadata(com.eBolivar.service.contribuyente.Metadata metadata) {
        this.metadata = metadata;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PersonaReturn)) return false;
        PersonaReturn other = (PersonaReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosGenerales==null && other.getDatosGenerales()==null) || 
             (this.datosGenerales!=null &&
              this.datosGenerales.equals(other.getDatosGenerales()))) &&
            ((this.datosMonotributo==null && other.getDatosMonotributo()==null) || 
             (this.datosMonotributo!=null &&
              this.datosMonotributo.equals(other.getDatosMonotributo()))) &&
            ((this.datosRegimenGeneral==null && other.getDatosRegimenGeneral()==null) || 
             (this.datosRegimenGeneral!=null &&
              this.datosRegimenGeneral.equals(other.getDatosRegimenGeneral()))) &&
            ((this.errorConstancia==null && other.getErrorConstancia()==null) || 
             (this.errorConstancia!=null &&
              this.errorConstancia.equals(other.getErrorConstancia()))) &&
            ((this.errorMonotributo==null && other.getErrorMonotributo()==null) || 
             (this.errorMonotributo!=null &&
              this.errorMonotributo.equals(other.getErrorMonotributo()))) &&
            ((this.errorRegimenGeneral==null && other.getErrorRegimenGeneral()==null) || 
             (this.errorRegimenGeneral!=null &&
              this.errorRegimenGeneral.equals(other.getErrorRegimenGeneral()))) &&
            ((this.metadata==null && other.getMetadata()==null) || 
             (this.metadata!=null &&
              this.metadata.equals(other.getMetadata())));
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
        if (getDatosGenerales() != null) {
            _hashCode += getDatosGenerales().hashCode();
        }
        if (getDatosMonotributo() != null) {
            _hashCode += getDatosMonotributo().hashCode();
        }
        if (getDatosRegimenGeneral() != null) {
            _hashCode += getDatosRegimenGeneral().hashCode();
        }
        if (getErrorConstancia() != null) {
            _hashCode += getErrorConstancia().hashCode();
        }
        if (getErrorMonotributo() != null) {
            _hashCode += getErrorMonotributo().hashCode();
        }
        if (getErrorRegimenGeneral() != null) {
            _hashCode += getErrorRegimenGeneral().hashCode();
        }
        if (getMetadata() != null) {
            _hashCode += getMetadata().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonaReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "personaReturn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosGenerales");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datosGenerales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "datosGenerales"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosMonotributo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datosMonotributo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "datosMonotributo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosRegimenGeneral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "datosRegimenGeneral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "datosRegimenGeneral"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorConstancia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorConstancia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "errorConstancia"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorMonotributo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorMonotributo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "errorMonotributo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorRegimenGeneral");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errorRegimenGeneral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "errorRegimenGeneral"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("metadata");
        elemField.setXmlName(new javax.xml.namespace.QName("", "metadata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "metadata"));
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
