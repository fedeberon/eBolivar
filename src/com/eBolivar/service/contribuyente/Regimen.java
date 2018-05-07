/**
 * Regimen.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.eBolivar.service.contribuyente;

public class Regimen  implements java.io.Serializable {
    private java.lang.String descripcionRegimen;

    private java.lang.Integer idImpuesto;

    private java.lang.Integer idRegimen;

    private java.lang.Integer periodo;

    private java.lang.String tipoRegimen;

    public Regimen() {
    }

    public Regimen(
           java.lang.String descripcionRegimen,
           java.lang.Integer idImpuesto,
           java.lang.Integer idRegimen,
           java.lang.Integer periodo,
           java.lang.String tipoRegimen) {
           this.descripcionRegimen = descripcionRegimen;
           this.idImpuesto = idImpuesto;
           this.idRegimen = idRegimen;
           this.periodo = periodo;
           this.tipoRegimen = tipoRegimen;
    }


    /**
     * Gets the descripcionRegimen value for this Regimen.
     * 
     * @return descripcionRegimen
     */
    public java.lang.String getDescripcionRegimen() {
        return descripcionRegimen;
    }


    /**
     * Sets the descripcionRegimen value for this Regimen.
     * 
     * @param descripcionRegimen
     */
    public void setDescripcionRegimen(java.lang.String descripcionRegimen) {
        this.descripcionRegimen = descripcionRegimen;
    }


    /**
     * Gets the idImpuesto value for this Regimen.
     * 
     * @return idImpuesto
     */
    public java.lang.Integer getIdImpuesto() {
        return idImpuesto;
    }


    /**
     * Sets the idImpuesto value for this Regimen.
     * 
     * @param idImpuesto
     */
    public void setIdImpuesto(java.lang.Integer idImpuesto) {
        this.idImpuesto = idImpuesto;
    }


    /**
     * Gets the idRegimen value for this Regimen.
     * 
     * @return idRegimen
     */
    public java.lang.Integer getIdRegimen() {
        return idRegimen;
    }


    /**
     * Sets the idRegimen value for this Regimen.
     * 
     * @param idRegimen
     */
    public void setIdRegimen(java.lang.Integer idRegimen) {
        this.idRegimen = idRegimen;
    }


    /**
     * Gets the periodo value for this Regimen.
     * 
     * @return periodo
     */
    public java.lang.Integer getPeriodo() {
        return periodo;
    }


    /**
     * Sets the periodo value for this Regimen.
     * 
     * @param periodo
     */
    public void setPeriodo(java.lang.Integer periodo) {
        this.periodo = periodo;
    }


    /**
     * Gets the tipoRegimen value for this Regimen.
     * 
     * @return tipoRegimen
     */
    public java.lang.String getTipoRegimen() {
        return tipoRegimen;
    }


    /**
     * Sets the tipoRegimen value for this Regimen.
     * 
     * @param tipoRegimen
     */
    public void setTipoRegimen(java.lang.String tipoRegimen) {
        this.tipoRegimen = tipoRegimen;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Regimen)) return false;
        Regimen other = (Regimen) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.descripcionRegimen==null && other.getDescripcionRegimen()==null) || 
             (this.descripcionRegimen!=null &&
              this.descripcionRegimen.equals(other.getDescripcionRegimen()))) &&
            ((this.idImpuesto==null && other.getIdImpuesto()==null) || 
             (this.idImpuesto!=null &&
              this.idImpuesto.equals(other.getIdImpuesto()))) &&
            ((this.idRegimen==null && other.getIdRegimen()==null) || 
             (this.idRegimen!=null &&
              this.idRegimen.equals(other.getIdRegimen()))) &&
            ((this.periodo==null && other.getPeriodo()==null) || 
             (this.periodo!=null &&
              this.periodo.equals(other.getPeriodo()))) &&
            ((this.tipoRegimen==null && other.getTipoRegimen()==null) || 
             (this.tipoRegimen!=null &&
              this.tipoRegimen.equals(other.getTipoRegimen())));
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
        if (getDescripcionRegimen() != null) {
            _hashCode += getDescripcionRegimen().hashCode();
        }
        if (getIdImpuesto() != null) {
            _hashCode += getIdImpuesto().hashCode();
        }
        if (getIdRegimen() != null) {
            _hashCode += getIdRegimen().hashCode();
        }
        if (getPeriodo() != null) {
            _hashCode += getPeriodo().hashCode();
        }
        if (getTipoRegimen() != null) {
            _hashCode += getTipoRegimen().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Regimen.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://a5.soap.ws.server.puc.sr/", "regimen"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionRegimen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descripcionRegimen"));
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
        elemField.setFieldName("idRegimen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idRegimen"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoRegimen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoRegimen"));
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
