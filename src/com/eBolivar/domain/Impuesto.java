/** @author FedeBeron * @version 1.0 */

package com.eBolivar.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "IMPUESTOS")
public class Impuesto implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @Column(name = "IMP_ID_FACTURA")
    private String idFactura;

    @Column(name = "IMP_TIPO_REGISTRO_0")
    private Integer tipoRegistro0;

    @Column(name = "IMP_ROTULO_ARCHIVO")
    private String rotuloArchivo;

    @Column(name = "FECHA_GENERACION")
    private Date fechaGeneracion;

    @Column(name = "IMP_MODALIDAD_ARCHIVO")
    private Integer modalidadArchivo;

    @Column(name = "IMP_SEPARADOR_PARRAF")
    private String separadorParrafo;

    @Column(name = "IMP_OBSERVACION")
    private String obervacion;

    @Column(name = "IMP_TIPO_REGISTRO_1")
    private Integer tipoRegistro1;

    @Column(name = "IMP_NOTIFICACION_GENERAL")
    private String notificacionGeneral;

    @Column(name = "IMP_TIPO_REGISTRO_2")
    private Integer tipoRegistro_2;

    @Column(name = "IMP_NUM_CUIT_0")
    private String num_cuit_0;

    @Column(name = "IMP_NRO_DOCUMENTO")
    private Integer nro_documento;

    @Column(name = "IMP_TIPO_DOCUMENTO")
    private Integer tipoDocuento;

    @Column(name = "IMP_COD_ELECTR_PAGO")
    private String codigoElectronicoPago;

    @Column(name = "IMP_CODIGO_SEGURIDAD")
    private String codigoSeguridad;

    @Column(name = "IMP_VENCIMIENTO_COD_SEG")
    private Date vencimientoCodigoSeg;

    @Column(name = "IMP_ESTADO")
    private Integer estado;

    @Column(name = "IMP_FILLER_0")
    private Integer filler_0;

    @Column(name = "IMP_ULTIMO_DIA_PAGO")
    private Date ultimoDiaPago;

    @Column(name = "IMP_LEYENDA_TRIBUTO")
    private String leyendaTributo;

    @Column(name = "IMP_LEYENDA_BIEN")
    private String leyendaBien;

    @Column(name = "IMP_LEYENDA_BIEN_ADICIONAL")
    private String leyendaBienAdicional;

    @Column(name = "IMP_LEYENDA_CONCEPTO")
    private String leyendaConcepto;

    @Column(name = "IMP_LEYENDA_ADICIONAL")
    private String leyendaAdicional;

    @Column(name = "IMP_1ER_VENCIMIENTO")
    private Date primerVencimiento;

    @Column(name = "IMP_IMPORTE_1ER_VENCIMIENTO")
    private String importe1reVencimiento;

    @Column(name = "IMP_2DO_VENCIMIENTO")
    private Date segundoVencimiento;

    @Column(name = "IMP_IMPORTE_2DO_VENCIMIENTO")
    private String importe2doVencimiento;

    @Column(name = "IMP_3ER_VENCIMIENTO")
    private Date tercerVencimiento;

    @Column(name = "IMP_IMPORTE_3ER_VENCIMIENTO")
    private String importe3erVencimiento;

    @Column(name = "IMP_CODIGO_BARRA")
    private String codigoDeBarra;

    @Column(name = "IMP_FILLER_1")
    private String filler_1;

    @Column(name = "IMP_CTL")
    private String ctl;

    @Column(name = "IMP_TIPO_REGISTRO_3")
    private Integer tipoRegistro_3;

    @Column(name = "IMP_ID_FACTURA_CANCELADA")
    private String idFacturaCancelada;

    @Column(name = "IMP_NUM_CUIT_1")
    private String num_cuit_1;

    @Column(name = "IMP_FILLER_2")
    private Integer filler_2;

    @Column(name = "IMP_TIPO_REGISTRO_4")
    private Integer tipoRegistro_4;

    @Column(name = "IMP_NUM_CUIT_2")
    private String num_cuit_2;

    @Column(name = "IMP_NOTIFICACION")
    private Integer notificacion;

    @Column(name = "IMP_TIPO_REGISTRO_5")
    private Integer tipoRegistro_5;

    @Column(name = "IMP_CODIGO_ELECTRONICO_PAGO")
    private Integer codigoElecPago;

    @Column(name = "IMP_NOTIFICACION_1")
    private Integer notificacion_1;

    @Column(name = "IMP_TIPO_REGISTRO_6")
    private Integer tipoRegistro_6;

    @Column(name = "IMP_REGISTROS")
    private String registros;

    @Column(name = "IMP_EOF")
    private String eof;

    @Column(name = "IMP_CRC")
    private String crc;

    @Column(name = "IMP_ID_CLIENTE")
    private String idCliente;

    @Column(name = "IMP_NOTIFICADO_POR_MAIL")
    private String notificadoPorMail;

    @ManyToOne
    @JoinColumn(name = "TI_ID")
    private TipoImpuesto tipoImpuesto;

    @Column(name = "IMP_NUMERO_PADRON")
    private String numeroDePadron;

    @ManyToOne
    @JoinColumn(name = "EST_ID")
    private EstadoDeTasa estadoDeTasa;

    //    auxiliares
    @Column(name = "IMP_FECHA_VENCIMIENTO")
    private String fechaVencimiento;

    @Column(name = "IMP_ESTADO_PAGO")
    private String estado_Pago;

    @Column(name = "IMP_ESTADO_VENCIMIENTO")
    private String estado_Vencimiento;


    public Impuesto() {
    }

    public String getNotificadoPorMail() {
        return notificadoPorMail;
    }

    public void setNotificadoPorMail(String notificadoPorMail) {
        this.notificadoPorMail = notificadoPorMail;
    }

    public TipoImpuesto getTipoImpuesto() {
        return tipoImpuesto;
    }

    public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

    public String getEstado_Pago() {
        return estado_Pago;
    }

    public void setEstado_Pago(String estado_Pago) {
        this.estado_Pago = estado_Pago;
    }

    public String getEstado_Vencimiento() {
        return estado_Vencimiento;
    }

    public void setEstado_Vencimiento(String estado_Vencimiento) {
        this.estado_Vencimiento = estado_Vencimiento;
    }

    public String getCrc() {
        return crc;
    }

    public void setCrc(String crc) {
        this.crc = crc;
    }

    public String getRegistros() {
        return registros;
    }

    public void setRegistros(String registros) {
        this.registros = registros;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getTipoRegistro0() {
        return tipoRegistro0;
    }

    public void setTipoRegistro0(Integer tipoRegistro0) {
        this.tipoRegistro0 = tipoRegistro0;
    }

    public String getRotuloArchivo() {
        return rotuloArchivo;
    }

    public void setRotuloArchivo(String rotuloArchivo) {
        this.rotuloArchivo = rotuloArchivo;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Integer getModalidadArchivo() {
        return modalidadArchivo;
    }

    public void setModalidadArchivo(Integer modalidadArchivo) {
        this.modalidadArchivo = modalidadArchivo;
    }

    public String getSeparadorParrafo() {
        return separadorParrafo;
    }

    public void setSeparadorParrafo(String separadorParrafo) {
        this.separadorParrafo = separadorParrafo;
    }

    public String getObervacion() {
        return obervacion;
    }

    public void setObervacion(String obervacion) {
        this.obervacion = obervacion;
    }

    public Integer getTipoRegistro1() {
        return tipoRegistro1;
    }

    public void setTipoRegistro1(Integer tipoRegistro1) {
        this.tipoRegistro1 = tipoRegistro1;
    }

    public String getNotificacionGeneral() {
        return notificacionGeneral;
    }

    public void setNotificacionGeneral(String notificacionGeneral) {
        this.notificacionGeneral = notificacionGeneral;
    }

    public Integer getTipoRegistro_2() {
        return tipoRegistro_2;
    }

    public void setTipoRegistro_2(Integer tipoRegistro_2) {
        this.tipoRegistro_2 = tipoRegistro_2;
    }

    public void setIdFactura(String string) {
        this.idFactura = string;
    }

    public String getImporte3erVencimiento() {
        return importe3erVencimiento;
    }

    public void setImporte3erVencimiento(String importe3erVencimiento) {
        this.importe3erVencimiento = importe3erVencimiento;
    }

    public String getNum_cuit_0() {
        return num_cuit_0;
    }

    public void setNum_cuit_0(String num_cuit_0) {
        this.num_cuit_0 = num_cuit_0;
    }

    public Integer getNro_documento() {
        return nro_documento;
    }

    public void setNro_documento(Integer nro_documento) {
        this.nro_documento = nro_documento;
    }

    public Integer getTipoDocuento() {
        return tipoDocuento;
    }

    public void setTipoDocuento(Integer tipoDocuento) {
        this.tipoDocuento = tipoDocuento;
    }

    public void setCodigoElectronicoPago(String string) {
        this.codigoElectronicoPago = string;
    }

    public void setCodigoSeguridad(String string) {
        this.codigoSeguridad = string;
    }

    public Date getVencimientoCodigoSeg() {
        return vencimientoCodigoSeg;
    }

    public void setVencimientoCodigoSeg(Date vencimientoCodigoSeg) {
        this.vencimientoCodigoSeg = vencimientoCodigoSeg;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getFiller_0() {
        return filler_0;
    }

    public void setFiller_0(Integer filler_0) {
        this.filler_0 = filler_0;
    }

    public Date getUltimoDiaPago() {
        return ultimoDiaPago;
    }

    public void setUltimoDiaPago(Date ultimoDiaPago) {
        this.ultimoDiaPago = ultimoDiaPago;
    }

    public String getLeyendaTributo() {
        return leyendaTributo;
    }

    public void setLeyendaTributo(String leyendaTributo) {
        this.leyendaTributo = leyendaTributo;
    }

    public String getLeyendaBien() {
        return leyendaBien;
    }

    public void setLeyendaBien(String leyendaBien) {
        this.leyendaBien = leyendaBien;
    }

    public String getLeyendaBienAdicional() {
        return leyendaBienAdicional;
    }

    public void setLeyendaBienAdicional(String leyendaBienAdicional) {
        this.leyendaBienAdicional = leyendaBienAdicional;
    }

    public String getLeyendaConcepto() {
        return leyendaConcepto;
    }

    public void setLeyendaConcepto(String leyendaConcepto) {
        this.leyendaConcepto = leyendaConcepto;
    }

    public String getLeyendaAdicional() {
        return leyendaAdicional;
    }

    public void setLeyendaAdicional(String leyendaAdicional) {
        this.leyendaAdicional = leyendaAdicional;
    }

    public Date getPrimerVencimiento() {
        return primerVencimiento;
    }

    public void setPrimerVencimiento(Date primerVencimiento) {
        this.primerVencimiento = primerVencimiento;
    }

    public String getImporte1reVencimiento() {
        return importe1reVencimiento;
    }

    public void setImporte1reVencimiento(String importe1reVencimiento) {
        this.importe1reVencimiento = importe1reVencimiento;
    }

    public Date getSegundoVencimiento() {
        return segundoVencimiento;
    }

    public void setSegundoVencimiento(Date segundoVencimiento) {
        this.segundoVencimiento = segundoVencimiento;
    }

    public String getImporte2doVencimiento() {
        return importe2doVencimiento;
    }

    public void setImporte2doVencimiento(String importe2doVencimiento) {
        this.importe2doVencimiento = importe2doVencimiento;
    }

    public void setTercerVencimiento(Date date) {
        this.tercerVencimiento = date;
    }

    public void setCodigoDeBarra(String string) {
        this.codigoDeBarra = string;
    }

    public String getFiller_1() {
        return filler_1;
    }

    public void setFiller_1(String filler_1) {
        this.filler_1 = filler_1;
    }

    public String getCtl() {
        return ctl;
    }

    public void setCtl(String ctl) {
        this.ctl = ctl;
    }

    public Integer getTipoRegistro_3() {
        return tipoRegistro_3;
    }

    public void setTipoRegistro_3(Integer tipoRegistro_3) {
        this.tipoRegistro_3 = tipoRegistro_3;
    }

    public String getIdFacturaCancelada() {
        return idFacturaCancelada;
    }

    public void setIdFacturaCancelada(String idFacturaCancelada) {
        this.idFacturaCancelada = idFacturaCancelada;
    }

    public String getNum_cuit_1() {
        return num_cuit_1;
    }

    public void setNum_cuit_1(String num_cuit_1) {
        this.num_cuit_1 = num_cuit_1;
    }

    public Integer getFiller_2() {
        return filler_2;
    }

    public void setFiller_2(Integer filler_2) {
        this.filler_2 = filler_2;
    }

    public Integer getTipoRegistro_4() {
        return tipoRegistro_4;
    }

    public void setTipoRegistro_4(Integer tipoRegistro_4) {
        this.tipoRegistro_4 = tipoRegistro_4;
    }

    public String getNum_cuit_2() {
        return num_cuit_2;
    }

    public void setNum_cuit_2(String num_cuit_2) {
        this.num_cuit_2 = num_cuit_2;
    }

    public Integer getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(Integer notificacion) {
        this.notificacion = notificacion;
    }

    public Integer getTipoRegistro_5() {
        return tipoRegistro_5;
    }

    public void setTipoRegistro_5(Integer tipoRegistro_5) {
        this.tipoRegistro_5 = tipoRegistro_5;
    }

    public Integer getCodigoElecPago() {
        return codigoElecPago;
    }

    public void setCodigoElecPago(Integer codigoElecPago) {
        this.codigoElecPago = codigoElecPago;
    }

    public Integer getNotificacion_1() {
        return notificacion_1;
    }

    public void setNotificacion_1(Integer notificacion_1) {
        this.notificacion_1 = notificacion_1;
    }

    public Integer getTipoRegistro_6() {
        return tipoRegistro_6;
    }

    public void setTipoRegistro_6(Integer tipoRegistro_6) {
        this.tipoRegistro_6 = tipoRegistro_6;
    }

    public String getEof() {
        return eof;
    }

    public void setEof(String eof) {
        this.eof = eof;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getIdFactura() {
        return idFactura;
    }

    public String getCodigoElectronicoPago() {
        return codigoElectronicoPago;
    }

    public String getCodigoSeguridad() {
        return codigoSeguridad;
    }

    public Date getTercerVencimiento() {
        return tercerVencimiento;
    }

    public String getCodigoDeBarra() {
        return codigoDeBarra;
    }

    public String getNumeroDePadron() {
        return numeroDePadron;
    }

    public void setNumeroDePadron(String numeroDePadron) {
        this.numeroDePadron = numeroDePadron;
    }

    public EstadoDeTasa getEstadoDeTasa() {
        return estadoDeTasa;
    }

    public void setEstadoDeTasa(EstadoDeTasa estadoDeTasa) {
        this.estadoDeTasa = estadoDeTasa;
    }

}

