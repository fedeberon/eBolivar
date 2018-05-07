/**
 * Dependencia.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class Dependencia  implements java.io.Serializable {
    private java.lang.String codPostal;

    private java.lang.String descripcionDependencia;

    private java.lang.String descripcionProvincia;

    private java.lang.String direccion;

    private java.lang.Integer idDependencia;

    private java.lang.Integer idProvincia;

    private java.lang.String localidad;

    public Dependencia() {
    }

    public Dependencia(
           java.lang.String codPostal,
           java.lang.String descripcionDependencia,
           java.lang.String descripcionProvincia,
           java.lang.String direccion,
           java.lang.Integer idDependencia,
           java.lang.Integer idProvincia,
           java.lang.String localidad) {
           this.codPostal = codPostal;
           this.descripcionDependencia = descripcionDependencia;
           this.descripcionProvincia = descripcionProvincia;
           this.direccion = direccion;
           this.idDependencia = idDependencia;
           this.idProvincia = idProvincia;
           this.localidad = localidad;
    }


    /**
     * Gets the codPostal value for this Dependencia.
     * 
     * @return codPostal
     */
    public java.lang.String getCodPostal() {
        return codPostal;
    }


    /**
     * Sets the codPostal value for this Dependencia.
     * 
     * @param codPostal
     */
    public void setCodPostal(java.lang.String codPostal) {
        this.codPostal = codPostal;
    }


    /**
     * Gets the descripcionDependencia value for this Dependencia.
     * 
     * @return descripcionDependencia
     */
    public java.lang.String getDescripcionDependencia() {
        return descripcionDependencia;
    }


    /**
     * Sets the descripcionDependencia value for this Dependencia.
     * 
     * @param descripcionDependencia
     */
    public void setDescripcionDependencia(java.lang.String descripcionDependencia) {
        this.descripcionDependencia = descripcionDependencia;
    }


    /**
     * Gets the descripcionProvincia value for this Dependencia.
     * 
     * @return descripcionProvincia
     */
    public java.lang.String getDescripcionProvincia() {
        return descripcionProvincia;
    }


    /**
     * Sets the descripcionProvincia value for this Dependencia.
     * 
     * @param descripcionProvincia
     */
    public void setDescripcionProvincia(java.lang.String descripcionProvincia) {
        this.descripcionProvincia = descripcionProvincia;
    }


    /**
     * Gets the direccion value for this Dependencia.
     * 
     * @return direccion
     */
    public java.lang.String getDireccion() {
        return direccion;
    }


    /**
     * Sets the direccion value for this Dependencia.
     * 
     * @param direccion
     */
    public void setDireccion(java.lang.String direccion) {
        this.direccion = direccion;
    }


    /**
     * Gets the idDependencia value for this Dependencia.
     * 
     * @return idDependencia
     */
    public java.lang.Integer getIdDependencia() {
        return idDependencia;
    }


    /**
     * Sets the idDependencia value for this Dependencia.
     * 
     * @param idDependencia
     */
    public void setIdDependencia(java.lang.Integer idDependencia) {
        this.idDependencia = idDependencia;
    }


    /**
     * Gets the idProvincia value for this Dependencia.
     * 
     * @return idProvincia
     */
    public java.lang.Integer getIdProvincia() {
        return idProvincia;
    }


    /**
     * Sets the idProvincia value for this Dependencia.
     * 
     * @param idProvincia
     */
    public void setIdProvincia(java.lang.Integer idProvincia) {
        this.idProvincia = idProvincia;
    }


    /**
     * Gets the localidad value for this Dependencia.
     * 
     * @return localidad
     */
    public java.lang.String getLocalidad() {
        return localidad;
    }


    /**
     * Sets the localidad value for this Dependencia.
     * 
     * @param localidad
     */
    public void setLocalidad(java.lang.String localidad) {
        this.localidad = localidad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Dependencia)) return false;
        Dependencia other = (Dependencia) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codPostal==null && other.getCodPostal()==null) || 
             (this.codPostal!=null &&
              this.codPostal.equals(other.getCodPostal()))) &&
            ((this.descripcionDependencia==null && other.getDescripcionDependencia()==null) || 
             (this.descripcionDependencia!=null &&
              this.descripcionDependencia.equals(other.getDescripcionDependencia()))) &&
            ((this.descripcionProvincia==null && other.getDescripcionProvincia()==null) || 
             (this.descripcionProvincia!=null &&
              this.descripcionProvincia.equals(other.getDescripcionProvincia()))) &&
            ((this.direccion==null && other.getDireccion()==null) || 
             (this.direccion!=null &&
              this.direccion.equals(other.getDireccion()))) &&
            ((this.idDependencia==null && other.getIdDependencia()==null) || 
             (this.idDependencia!=null &&
              this.idDependencia.equals(other.getIdDependencia()))) &&
            ((this.idProvincia==null && other.getIdProvincia()==null) || 
             (this.idProvincia!=null &&
              this.idProvincia.equals(other.getIdProvincia()))) &&
            ((this.localidad==null && other.getLocalidad()==null) || 
             (this.localidad!=null &&
              this.localidad.equals(other.getLocalidad())));
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
        if (getCodPostal() != null) {
            _hashCode += getCodPostal().hashCode();
        }
        if (getDescripcionDependencia() != null) {
            _hashCode += getDescripcionDependencia().hashCode();
        }
        if (getDescripcionProvincia() != null) {
            _hashCode += getDescripcionProvincia().hashCode();
        }
        if (getDireccion() != null) {
            _hashCode += getDireccion().hashCode();
        }
        if (getIdDependencia() != null) {
            _hashCode += getIdDependencia().hashCode();
        }
        if (getIdProvincia() != null) {
            _hashCode += getIdProvincia().hashCode();
        }
        if (getLocalidad() != null) {
            _hashCode += getLocalidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Dependencia.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "dependencia"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codPostal");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codPostal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcionDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionProvincia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcionProvincia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("direccion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "direccion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDependencia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idDependencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idProvincia");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idProvincia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("localidad");
        elemField.setXmlName(new javax.xml.namespace.QName("", "localidad"));
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
