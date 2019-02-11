//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.eBolivar.common;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.usuario.Usuario;

public class SearchObject {
    private long localidad;
    private long provincia;
    private long pais;
    private String cluster;
    private String alm;
    private String campo;
    private String ubicacion;
    private String valor;
    private String provinciaString;
    private String paisString;
    private String localidadCod;
    private int page = 1;
    private int maxResults;
    private String filter;
    private Integer codigo;
    private String tipo;
    private String[] roles;
    private String tecnologia;
    private String[] sitios;
    private String activo;
    private String desactivo;
    private String[] estado;
    private String[] Wicap;
    private Integer[] layers;
    private String tipotarea;
    private String configuracion;
    private String proveedor;
    private String estados;
    private String prioridad;
    private String valorAv1;
    private String valorAv2;
    private String valorAv3;
    private String campoAv;
    private String dosG;
    private String tresG;
    private String cuatroG;
    private Integer reclamo;
    private String bandejaDeEntrada;
    private Persona persona = new Persona();
    private Padron padron = new Padron();
    private Usuario usuario = new Usuario();
    private String nroPo;
    private String posPo;
    private String recepcion;
    private int e1;
    private double eth;
    private String cellId;
    private String campo2;
    private String campo1;
    private String valor1;
    private String valor2;
    private String valor3;
    private String ubicacion1;
    private String ubicacion2;

    public SearchObject() {
    }

    public String getBandejaDeEntrada() {
        return this.bandejaDeEntrada;
    }

    public void setBandejaDeEntrada(String bandejaDeEntrada) {
        this.bandejaDeEntrada = bandejaDeEntrada;
    }

    public Integer getReclamo() {
        return this.reclamo;
    }

    public void setReclamo(Integer reclamo) {
        this.reclamo = reclamo;
    }

    public String getCuatroG() {
        return this.cuatroG;
    }

    public void setCuatroG(String cuatroG) {
        this.cuatroG = cuatroG;
    }

    public String getDosG() {
        return this.dosG;
    }

    public void setDosG(String dosG) {
        this.dosG = dosG;
    }

    public String getTresG() {
        return this.tresG;
    }

    public void setTresG(String tresG) {
        this.tresG = tresG;
    }

    public String getValorAv3() {
        return this.valorAv3;
    }

    public void setValorAv3(String valorAv3) {
        this.valorAv3 = valorAv3;
    }

    public String getCampoAv() {
        return this.campoAv;
    }

    public void setCampoAv(String campoAv) {
        this.campoAv = campoAv;
    }

    public String getCampo2() {
        return this.campo2;
    }

    public void setCampo2(String campo2) {
        this.campo2 = campo2;
    }

    public String getCampo1() {
        return this.campo1;
    }

    public void setCampo1(String campo1) {
        this.campo1 = campo1;
    }

    public String getValor1() {
        return this.valor1;
    }

    public void setValor1(String valor1) {
        this.valor1 = valor1;
    }

    public String getValor2() {
        return this.valor2;
    }

    public void setValor2(String valor2) {
        this.valor2 = valor2;
    }

    public String getValor3() {
        return this.valor3;
    }

    public void setValor3(String valor3) {
        this.valor3 = valor3;
    }

    public String getUbicacion1() {
        return this.ubicacion1;
    }

    public void setUbicacion1(String ubicacion1) {
        this.ubicacion1 = ubicacion1;
    }

    public String getUbicacion2() {
        return this.ubicacion2;
    }

    public void setUbicacion2(String ubicacion2) {
        this.ubicacion2 = ubicacion2;
    }

    public String getValorAv1() {
        return this.valorAv1;
    }

    public void setValorAv1(String valorAv1) {
        this.valorAv1 = valorAv1;
    }

    public String getValorAv2() {
        return this.valorAv2;
    }

    public void setValorAv2(String valorAv2) {
        this.valorAv2 = valorAv2;
    }

    public String getCellId() {
        return this.cellId;
    }

    public void setCellId(String cellId) {
        this.cellId = cellId;
    }

    public String getNroPo() {
        return this.nroPo;
    }

    public void setNroPo(String nroPo) {
        this.nroPo = nroPo;
    }

    public String getPosPo() {
        return this.posPo;
    }

    public void setPosPo(String posPo) {
        this.posPo = posPo;
    }

    public String getRecepcion() {
        return this.recepcion;
    }

    public void setRecepcion(String recepcion) {
        this.recepcion = recepcion;
    }

    public int getE1() {
        return this.e1;
    }

    public void setE1(int e1) {
        this.e1 = e1;
    }

    public double getEth() {
        return this.eth;
    }

    public void setEth(double eth) {
        this.eth = eth;
    }

    public String getTipotarea() {
        return this.tipotarea;
    }

    public void setTipotarea(String tipotarea) {
        this.tipotarea = tipotarea;
    }

    public String getConfiguracion() {
        return this.configuracion;
    }

    public void setConfiguracion(String configuracion) {
        this.configuracion = configuracion;
    }

    public String getProveedor() {
        return this.proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstados() {
        return this.estados;
    }

    public void setEstados(String estados) {
        this.estados = estados;
    }

    public String getPrioridad() {
        return this.prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getFilter() {
        return this.filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public int getPage() {
        return this.page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getMaxResults() {
        return this.maxResults;
    }

    public void setMaxResults(int maxResults) {
        this.maxResults = maxResults;
    }

    public String getProvinciaString() {
        return this.provinciaString;
    }

    public void setProvinciaString(String provinciaString) {
        this.provinciaString = provinciaString;
    }

    public String getPaisString() {
        return this.paisString;
    }

    public void setPaisString(String paisString) {
        this.paisString = paisString;
    }

    public String getCampo() {
        return this.campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getUbicacion() {
        return this.ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getValor() {
        return this.valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public long getLocalidad() {
        return this.localidad;
    }

    public void setLocalidad(long localidad) {
        this.localidad = localidad;
    }

    public String getCluster() {
        return this.cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getAlm() {
        return this.alm;
    }

    public void setAlm(String alm) {
        this.alm = alm;
    }

    public long getProvincia() {
        return this.provincia;
    }

    public void setProvincia(long provincia) {
        this.provincia = provincia;
    }

    public long getPais() {
        return this.pais;
    }

    public void setPais(long pais) {
        this.pais = pais;
    }

    public String getLocalidadCod() {
        return this.localidadCod;
    }

    public void setLocalidadCod(String localidadCod) {
        this.localidadCod = localidadCod;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return this.codigo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String[] getRoles() {
        return this.roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getTecnologia() {
        return this.tecnologia;
    }

    public void setTecnologia(String tecnologia) {
        this.tecnologia = tecnologia;
    }

    public String[] getSitios() {
        return this.sitios;
    }

    public void setSitios(String[] sitios) {
        this.sitios = sitios;
    }

    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDesactivo() {
        return this.desactivo;
    }

    public void setDesactivo(String desactivo) {
        this.desactivo = desactivo;
    }

    public String[] getEstado() {
        return this.estado;
    }

    public void setEstado(String[] estado) {
        this.estado = estado;
    }

    public String[] getWicap() {
        return this.Wicap;
    }

    public void setWicap(String[] wicap) {
        this.Wicap = wicap;
    }

    public Integer[] getLayers() {
        return this.layers;
    }

    public void setLayers(Integer[] layers) {
        this.layers = layers;
    }

    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Padron getPadron() {
        return this.padron;
    }

    public void setPadron(Padron padron) {
        this.padron = padron;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
