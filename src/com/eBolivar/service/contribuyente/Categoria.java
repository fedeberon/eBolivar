/**
 * Categoria.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class Categoria  implements java.io.Serializable {
    private java.lang.String descripcionCategoria;

    private java.lang.Integer idCategoria;

    private java.lang.Integer idImpuesto;

    private java.lang.Integer periodo;

    public Categoria() {
    }

    public Categoria(
           java.lang.String descripcionCategoria,
           java.lang.Integer idCategoria,
           java.lang.Integer idImpuesto,
           java.lang.Integer periodo) {
           this.descripcionCategoria = descripcionCategoria;
           this.idCategoria = idCategoria;
           this.idImpuesto = idImpuesto;
           this.periodo = periodo;
    }


    /**
     * Gets the descripcionCategoria value for this Categoria.
     * 
     * @return descripcionCategoria
     */
    public java.lang.String getDescripcionCategoria() {
        return descripcionCategoria;
    }


    /**
     * Sets the descripcionCategoria value for this Categoria.
     * 
     * @param descripcionCategoria
     */
    public void setDescripcionCategoria(java.lang.String descripcionCategoria) {
        this.descripcionCategoria = descripcionCategoria;
    }


    /**
     * Gets the idCategoria value for this Categoria.
     * 
     * @return idCategoria
     */
    public java.lang.Integer getIdCategoria() {
        return idCategoria;
    }


    /**
     * Sets the idCategoria value for this Categoria.
     * 
     * @param idCategoria
     */
    public void setIdCategoria(java.lang.Integer idCategoria) {
        this.idCategoria = idCategoria;
    }


    /**
     * Gets the idImpuesto value for this Categoria.
     * 
     * @return idImpuesto
     */
    public java.lang.Integer getIdImpuesto() {
        return idImpuesto;
    }


    /**
     * Sets the idImpuesto value for this Categoria.
     * 
     * @param idImpuesto
     */
    public void setIdImpuesto(java.lang.Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }


    /**
     * Gets the periodo value for this Categoria.
     * 
     * @return periodo
     */
    public java.lang.Integer getPeriodo() {
        return periodo;
    }


    /**
     * Sets the periodo value for this Categoria.
     * 
     * @param periodo
     */
    public void setPeriodo(java.lang.Integer periodo) {
        this.periodo = periodo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Categoria)) return false;
        Categoria other = (Categoria) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcionCategoria==null && other.getDescripcionCategoria()==null) || 
             (this.descripcionCategoria!=null &&
              this.descripcionCategoria.equals(other.getDescripcionCategoria()))) &&
            ((this.idCategoria==null && other.getIdCategoria()==null) || 
             (this.idCategoria!=null &&
              this.idCategoria.equals(other.getIdCategoria()))) &&
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
        if (getDescripcionCategoria() != null) {
            _hashCode += getDescripcionCategoria().hashCode();
        }
        if (getIdCategoria() != null) {
            _hashCode += getIdCategoria().hashCode();
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
        new org.apache.axis.description.TypeDesc(Categoria.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "categoria"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcionCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idCategoria");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idCategoria"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
