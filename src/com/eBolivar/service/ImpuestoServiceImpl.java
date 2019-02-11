package com.eBolivar.service;

import com.eBolivar.common.MailSender;
import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.ImpuestoDaoHibernateImpl;
import com.eBolivar.domain.DetalleFactura;
import com.eBolivar.domain.Documento;
import com.eBolivar.domain.EstadoDeTasa;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.domain.NotificacionPorBien;
import com.eBolivar.service.ConsultasServiceImpl;
import com.eBolivar.service.DetalleFacturaServiceImpl;
import com.eBolivar.service.NotificacionPorBienServiceImpl;
import com.eBolivar.service.PersistenceContextSingleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("impuestoService")
public class ImpuestoServiceImpl {
    private ImpuestoDaoHibernateImpl dao = null;
    private NotificacionPorBienServiceImpl notificacionPorBienService;
    private DetalleFacturaServiceImpl detalleFacturaService;
    private SessionFactory sessionFactory;
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    List<DetalleFactura> detallesFactura;
    List<NotificacionPorBien> notificacionesPorBien;
    List<Impuesto> impuestosGuardar;
    List<Impuesto> impuestosActualizar;
    Integer tipoRegistro = null;
    Integer modadalidad = null;
    String rotuloDocumento = null;
    String observaciones = null;
    Date fechaGeneracion = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setNotificacionPorBienService(NotificacionPorBienServiceImpl notificacionPorBienService) {
        this.notificacionPorBienService = notificacionPorBienService;
    }

    public void setDetalleFacturaService(DetalleFacturaServiceImpl detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    public ImpuestoServiceImpl() {
        this.dao = (ImpuestoDaoHibernateImpl)PersistenceContextSingleton.getInstance().getBean("impuestoDao");
    }

    private void updateListObject(List<Impuesto> impuestos, Session session) {
        this.dao.updateListObject(impuestos, session);
    }

    private void saveListObject(List<Impuesto> impuestos, Session session) {
        this.dao.saveListObject(impuestos, session);
    }

    public Impuesto getObject(String id) {
        Impuesto impuesto = this.dao.getObject(id);
        this.parsearImporte(impuesto);
        return impuesto;
    }

    public List<Impuesto> getObjects() {
        return this.dao.getObjects();
    }

    public void removeObject(Integer id) {
        this.dao.removeObject(id);
    }

    public void saveObject(Impuesto object) throws ConstraintViolationException {
        this.dao.saveObject(object);
    }

    public List searchObjects(SearchObject search) {
        return this.dao.searchObjects(search);
    }

    public List<Impuesto> obtenerImpuestosByCliente(String idCliente) {
        return this.dao.obtenerImpuestosByCliente(idCliente);
    }

    public List<Impuesto> obtenerImpuestosSinNotificacion(String nombreArchivo) {
        return this.dao.obtenerImpuestosSinNotificacion(nombreArchivo);
    }

    public Integer crearEnviarArchivoPorMail(NotificacionPadron n) {
        String parametro = "idFactura";
        String path = "/actualizaciones/reporte.jasper";
        ConsultasServiceImpl consultaServices = new ConsultasServiceImpl();
        ImpuestoServiceImpl impuestoService = new ImpuestoServiceImpl();

        try {
            Impuesto e = impuestoService.getObject(n.getIdFactura());
            e.setFechaVencimiento((new SimpleDateFormat("dd/MM/yyyy")).format(e.getPrimerVencimiento()));
            int imp = Integer.parseInt(e.getImporte1reVencimiento());
            String strImp = imp + "";
            String entero = strImp.substring(0, strImp.length() - 2);
            if(entero.length() > 3) {
                entero = entero.substring(0, entero.length() - 3) + "," + entero.substring(entero.length() - 3, entero.length());
            }

            String decimal = strImp.substring(strImp.length() - 2, strImp.length());
            e.setImporte1reVencimiento("$ " + entero + "." + decimal);
            HashMap map = new HashMap();
            map.put(parametro, n.getIdFactura());
            map.put("importe", e.getImporte1reVencimiento());
            map.put("padron", e.getCodigoDeBarra().substring(7, 15));
            map.put("nroRecibo", e.getCodigoDeBarra().substring(0, 3));
            JasperReport reporte = (JasperReport)JRLoader.loadObject(new File(path));
            JasperPrint jp = consultaServices.crearPrint(reporte, map);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
            pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:/IMPUESTOS/" + n.getIdFactura() + ".pdf");
            pdfExporter.exportReport();
            MailSender mail = new MailSender();
            String[] destinatarios = n.getDireccionEnvio().split(",");
            String mensaje = this.mnsjeEnvio(n);
            mail.postMail(destinatarios, "eBolivar - Tasas e Impuestos - Padron: " + n.getIdFactura().substring(3, 11), mensaje, "cac@bolivar.gob.ar", "C:/IMPUESTOS/" + n.getIdFactura() + ".pdf");
        } catch (Exception var18) {
            var18.printStackTrace();
            return Integer.valueOf(0);
        }

        return Integer.valueOf(1);
    }

    public List<Documento> obtenerListadoArchivos(Integer mes, Integer anio) {
        String nombreMes = this.obtenerMes(mes);
        String sDirectorio = "C:\\actualizaciones\\" + anio + "\\" + nombreMes + "\\";
        File f = new File(sDirectorio);
        if(!f.exists()) {
            f.mkdirs();
        }

        File[] ficheros = f.listFiles();
        ArrayList documentos = new ArrayList();

        for(int x = 0; x < ficheros.length; ++x) {
            Documento d = new Documento();
            d.setDestino("\\actualizaciones\\" + anio + "\\" + nombreMes + "\\" + ficheros[x].getName());
            d.setOriginalFileName(ficheros[x].getName());
            documentos.add(d);
        }

        return documentos;
    }

    public String obtenerMes(Integer mes) {
        switch(mes.intValue()) {
            case 1:
                return "Enero";
            case 2:
                return "Febrero";
            case 3:
                return "Marzo";
            case 4:
                return "Abril";
            case 5:
                return "Mayo";
            case 6:
                return "Junio";
            case 7:
                return "Julio";
            case 8:
                return "Agosto";
            case 9:
                return "Setiembre";
            case 10:
                return "Octubre";
            case 11:
                return "Noviembre";
            case 12:
                return "Diciembre";
            default:
                return "Sin Datos";
        }
    }

    public String mnsjeEnvio(NotificacionPadron n) {
        String mensaje = "Estimado Vecino: \nUsted esta adherido al sistema de Envio de Impuestos por mail.\nNro Padron : " + n.getIdFactura().substring(3, 11) + ".\nCualquier inquietud no dude en  comunicarse con nosotros al numeri 147 opcion 1.\nMuchas Gracias.\nUn cordial Saludo.\nCentro de Atencion al Cidadano.\nMunicipaliad de Bolivar.\nBuenos Aires-Argentina.\n\n\n\n ________________________________\n\n\n\nAgradecemos tu contribucion con el medio ambiente.\nCreemos que el compromiso lo hacemos a traves de las pequenas decisiones que tomamos todos los dias.";
        return mensaje;
    }

    public Integer actualizarMailsEnviados(List<Impuesto> impuestos) {
        try {
            Iterator e = impuestos.iterator();

            while(e.hasNext()) {
                Impuesto i = (Impuesto)e.next();
                i.setNotificadoPorMail("SI");
            }

            this.dao.updateListObject(impuestos);
            return Integer.valueOf(1);
        } catch (Exception var4) {
            return null;
        }
    }

    public void readAndSaveImpuestos(File file) throws Exception {
        this.impuestosGuardar = new ArrayList();
        this.impuestosActualizar = new ArrayList();
        this.detallesFactura = new ArrayList();
        this.notificacionesPorBien = new ArrayList();
        List linesFile = this.readFile(file);
        Iterator var3 = linesFile.iterator();

        while(var3.hasNext()) {
            String line = (String)var3.next();
            this.setByTipoRegistro(line);
        }

        this.saveListImpuestosConDetalles();
    }

    private void saveListImpuestosConDetalles() {
        org.hibernate.classic.Session session = null;
        Transaction transaction = null;

        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();
            this.detalleFacturaService.saveListObject(this.detallesFactura, session);
            this.notificacionPorBienService.saveListObject(this.notificacionesPorBien, session);
            this.saveListObject(this.impuestosGuardar, session);
            this.updateListObject(this.impuestosActualizar, session);
            transaction.commit();
        } catch (Exception var7) {
            var7.printStackTrace();
            transaction.rollback();
        } finally {
            if(session != null) {
                session.flush();
                session.close();
            }

        }

    }

    private List<String> readFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferReader = new BufferedReader(fileReader);
        ArrayList linesFile = new ArrayList();

        String line;
        while((line = bufferReader.readLine()) != null) {
            linesFile.add(line);
        }

        if(bufferReader != null) {
            bufferReader.close();
        }

        return linesFile;
    }

    private void setByTipoRegistro(String line) throws Exception {
        Impuesto impuestoAGuardar = new Impuesto();
        Integer tipoRegistro = this.readTipoRegistro(line);
        switch(tipoRegistro.intValue()) {
            case 0:
                this.cargarHEADER(line);
                break;
            case 1:
                this.cargarNotificacionGeneral(line, impuestoAGuardar);
                break;
            case 2:
                this.cargarFacturas(line, impuestoAGuardar);
                break;
            case 3:
                this.cargarDetalleFactura(line, impuestoAGuardar);
                break;
            case 4:
                this.cargarNotificacionesPorNumero(line, impuestoAGuardar);
                break;
            case 5:
                this.cargarNotificaciones(line, impuestoAGuardar);
            case 6:
            case 7:
            case 8:
            default:
                break;
            case 9:
                this.cargarRegistroTail(line, impuestoAGuardar);
        }

    }

    private Integer readTipoRegistro(String line) {
        return Integer.valueOf(Integer.parseInt(line.substring(0, 1)));
    }

    private void cargarHEADER(String line) throws ParseException {
        this.tipoRegistro = this.cargarTipoRegistro(line);
        this.rotuloDocumento = this.cargarRoturo(line);
        this.modadalidad = this.cargarModalidad(line);
        this.observaciones = this.cargarObservaciones(line);
        this.fechaGeneracion = this.cargarFechaGeneracionArchivo(line);
    }

    private void cargarNotificacionGeneral(String linea, Impuesto i) {
        i.setNotificacionGeneral(linea.substring(41, 160).trim().equalsIgnoreCase("")?null:linea.substring(41, 160).trim());
    }

    private void cargarFacturas(String line, Impuesto impuestoAGuardar) throws Exception {
        impuestoAGuardar.setTipoRegistro_2(line.substring(0, 1).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(0, 1).trim())));
        impuestoAGuardar.setIdFactura(line.substring(1, 21).trim().equalsIgnoreCase("")?null:line.substring(1, 21).trim());
        impuestoAGuardar.setIdCliente(line.substring(21, 35).trim().equalsIgnoreCase("")?null:line.substring(21, 35).trim());
        impuestoAGuardar.setNum_cuit_0(line.substring(35, 46).trim().equalsIgnoreCase("")?null:line.substring(35, 46).trim());
        impuestoAGuardar.setNro_documento(line.substring(46, 54).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(46, 54).trim())));
        impuestoAGuardar.setTipoDocuento(line.substring(54, 59).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(54, 59).trim())));
        impuestoAGuardar.setCodigoElectronicoPago(line.substring(59, 73).trim().equalsIgnoreCase("")?null:line.substring(59, 73).trim());
        impuestoAGuardar.setCodigoSeguridad(line.substring(73, 78).trim().equalsIgnoreCase("")?null:line.substring(73, 78).trim());
        impuestoAGuardar.setVencimientoCodigoSeg(line.substring(78, 86).trim().equalsIgnoreCase("")?null:this.dateFormat.parse(line.substring(78, 86).trim()));
        impuestoAGuardar.setEstado(line.substring(86, 87).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(86, 87).trim())));
        impuestoAGuardar.setFiller_0(line.substring(87, 88).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(87, 88).trim())));
        impuestoAGuardar.setUltimoDiaPago(line.substring(88, 96).trim().equalsIgnoreCase("")?null:this.dateFormat.parse(line.substring(88, 96).trim()));
        impuestoAGuardar.setLeyendaTributo(line.substring(96, 176).trim().equalsIgnoreCase("")?null:line.substring(96, 176).trim());
        impuestoAGuardar.setLeyendaBien(line.substring(176, 256).trim().equalsIgnoreCase("")?null:line.substring(176, 256).trim());
        impuestoAGuardar.setLeyendaBienAdicional(line.substring(256, 336).trim().equalsIgnoreCase("")?null:line.substring(256, 336).trim());
        impuestoAGuardar.setLeyendaConcepto(line.substring(336, 416).trim().equalsIgnoreCase("")?null:line.substring(336, 416).trim());
        impuestoAGuardar.setLeyendaAdicional(line.substring(416, 496).trim().equalsIgnoreCase("")?null:line.substring(416, 496).trim());
        impuestoAGuardar.setPrimerVencimiento(line.substring(496, 504).trim().equalsIgnoreCase("")?null:this.dateFormat.parse(line.substring(496, 504).trim()));
        impuestoAGuardar.setImporte1reVencimiento(line.substring(504, 516).trim().equalsIgnoreCase("")?null:line.substring(504, 516).trim());
        impuestoAGuardar.setSegundoVencimiento(line.substring(516, 524).trim().equalsIgnoreCase("")?null:this.dateFormat.parse(line.substring(516, 524).trim()));
        impuestoAGuardar.setImporte2doVencimiento(line.substring(524, 536).trim().equalsIgnoreCase("")?null:line.substring(524, 536).trim());
        impuestoAGuardar.setTercerVencimiento(line.substring(536, 544).trim().equalsIgnoreCase("")?null:this.dateFormat.parse(line.substring(536, 544).trim()));
        impuestoAGuardar.setImporte3erVencimiento(line.substring(544, 556).trim().equalsIgnoreCase("")?null:line.substring(544, 556).trim());
        impuestoAGuardar.setCodigoDeBarra(line.substring(556, 602).trim().equalsIgnoreCase("")?null:line.substring(556, 602).trim());
        impuestoAGuardar.getTipoImpuesto().setCodigo(Integer.valueOf(Integer.parseInt(impuestoAGuardar.getCodigoDeBarra().substring(5, 7))));
        impuestoAGuardar.setFiller_1(line.substring(602, 614).trim().equalsIgnoreCase("")?null:line.substring(602, 614).trim());
        impuestoAGuardar.setObervacion(this.observaciones);
        impuestoAGuardar.setModalidadArchivo(this.modadalidad);
        impuestoAGuardar.setFechaGeneracion(this.fechaGeneracion);
        impuestoAGuardar.setTipoRegistro0(this.tipoRegistro);
        impuestoAGuardar.setRotuloArchivo(this.rotuloDocumento);
        this.impuestosGuardar.add(impuestoAGuardar);
    }

    private void cargarDetalleFactura(String linea, Impuesto i) {
        Integer tipoRegistro = this.cargarTipoRegistro(linea);
        String idFactura = this.cargarNumeroFactura(linea);
        Integer item = this.cargarItem(linea);
        String detalle = this.cargarDetalleFactura(linea);
        DetalleFactura detalleFactura = new DetalleFactura(tipoRegistro, idFactura, item, detalle);
        this.detallesFactura.add(detalleFactura);
    }

    private void cargarNotificacionesPorNumero(String linea, Impuesto impuestoAGuardar) {
        impuestoAGuardar.setNum_cuit_0(linea.substring(1, 12).trim().equalsIgnoreCase("")?null:linea.substring(1, 12).trim());
        impuestoAGuardar = this.dao.getObjectByCUIT(impuestoAGuardar.getNum_cuit_0());
        if(impuestoAGuardar != null) {
            impuestoAGuardar.setTipoRegistro_4(linea.substring(0, 1).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(linea.substring(0, 1).trim())));
            impuestoAGuardar.setNotificacionGeneral(linea.substring(12, 268).trim().equalsIgnoreCase("")?null:linea.substring(41, 160).trim());
            this.impuestosActualizar.add(impuestoAGuardar);
        }

    }

    private void cargarNotificaciones(String linea, Impuesto i) {
        Integer tipoRegistro = this.cargarTipoRegistro(linea);
        Long codigoElectronicoPago = this.cargarCodigoElectronicoPago(linea);
        Integer numeroNotificacion = this.cargarNumeroNotificacion(linea);
        String notificacion = this.cargarNotificacion(linea);
        NotificacionPorBien notificacionPorBien = new NotificacionPorBien(tipoRegistro, codigoElectronicoPago.longValue(), numeroNotificacion, notificacion);
        this.notificacionesPorBien.add(notificacionPorBien);
    }

    private void cargarRegistroTail(String linea, Impuesto i) {
        i.setTipoRegistro_6(this.cargarTipoRegistro(linea));
        i.setRegistros(linea.substring(1, 10).trim().equalsIgnoreCase("")?null:linea.substring(1, 10).trim());
        i.setEof(linea.substring(10, 15).trim().equalsIgnoreCase("")?null:linea.substring(10, 15).trim());
        i.setCrc(linea.substring(15, 20).trim().equalsIgnoreCase("")?null:linea.substring(15, 20).trim());
    }

    public File saveFile(Documento documentoAGuardar) throws IllegalStateException, IOException {
        MultipartFile file = documentoAGuardar.getFile();
        documentoAGuardar.setOriginalFileName(file.getOriginalFilename());
        documentoAGuardar.setFecha(new Date());
        String autor = SecurityContextHolder.getContext().getAuthentication().getName();
        documentoAGuardar.setAutor(autor);
        Calendar calendario = Calendar.getInstance();
        String mes = this.obtenerMes(Integer.valueOf(calendario.get(2) + 1));
        String destinationFolder = "C://actualizaciones//" + calendario.get(1) + "//" + mes + "//";
        File destination = new File(destinationFolder);
        if(!destination.exists()) {
            destination.mkdirs();
        }

        String archivo = destinationFolder + file.getOriginalFilename();
        File destinationFile = new File(archivo);

        for(int count = 1; destinationFile.exists(); ++count) {
            destinationFile = new File(archivo + "-Version_" + count);
        }

        file.transferTo(destinationFile);
        return destinationFile;
    }

    private Date cargarFechaGeneracionArchivo(String line) throws ParseException {
        Date fechaGeneracion = line.substring(31, 39).trim().equalsIgnoreCase("")?null:this.dateFormat.parse(line.substring(31, 39).trim());
        return fechaGeneracion;
    }

    private Integer cargarModalidad(String line) {
        Integer modalidad = line.substring(39, 40).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(39, 40).trim()));
        return modalidad;
    }

    private String cargarObservaciones(String line) {
        return line.substring(41, 160).trim().equalsIgnoreCase("")?null:line.substring(41, 160).trim();
    }

    private Integer cargarTipoRegistro(String line) {
        return line.substring(0, 1).trim().equalsIgnoreCase("")?null:Integer.valueOf(Integer.parseInt(line.substring(0, 1).trim()));
    }

    private String cargarRoturo(String line) {
        return line.substring(1, 29).trim().equalsIgnoreCase("")?null:line.substring(1, 29).trim();
    }

    private String cargarNumeroFactura(String line) {
        return line.substring(1, 21).trim().equalsIgnoreCase("")?null:line.substring(1, 21).trim();
    }

    private Integer cargarItem(String line) {
        return Integer.valueOf(Integer.parseInt(line.substring(21, 24).trim().equalsIgnoreCase("")?null:line.substring(21, 24).trim()));
    }

    private String cargarDetalleFactura(String line) {
        return line.substring(24, line.length()).trim().equalsIgnoreCase("")?null:line.substring(24, line.length()).trim();
    }

    private Long cargarCodigoElectronicoPago(String line) {
        return Long.valueOf(Long.parseLong(line.substring(1, 15).trim().equalsIgnoreCase("")?null:line.substring(1, 15).trim()));
    }

    private Integer cargarNumeroNotificacion(String line) {
        return Integer.valueOf(Integer.parseInt(line.substring(15, 19).trim().equalsIgnoreCase("")?null:line.substring(15, 19).trim()));
    }

    private String cargarNotificacion(String line) {
        return line.substring(19, 275).trim().equalsIgnoreCase("")?null:line.substring(15, 275).trim();
    }

    public List<Impuesto> getByPadron(String padron) {
        return this.dao.getByPadron(padron);
    }

    public List<Impuesto> getActivosByPadron(String padron) {
        return this.dao.getActivosByPadron(padron);
    }

    public List<Impuesto> getByPadronByTipoImpuesto(String padron, Integer tipo) {
        return this.dao.getByPadronByTipoImpuesto(padron, tipo);
    }

    public void verificarVencimientoDeTasa(Impuesto i) {
        if((new Date()).after(i.getPrimerVencimiento())) {
            i.setEstado_Vencimiento(EstadoDeTasa.VENCIDO);
        }

    }

    public void parsearFechaPrimerVencimiento(Impuesto i) {
        i.setFechaVencimiento((new SimpleDateFormat("dd/MM/yyyy")).format(i.getPrimerVencimiento()));
    }

    public void parsearImporte(Impuesto i) {
        int importe = Integer.parseInt(i.getImporte1reVencimiento());
        String strImporte = String.valueOf(importe);
        String entero = strImporte.substring(0, strImporte.length() - 2);
        if(entero.length() > 3) {
            entero = entero.substring(0, entero.length() - 3) + "," + entero.substring(entero.length() - 3, entero.length());
        }

        String decimal = strImporte.substring(strImporte.length() - 2, strImporte.length());
        i.setImporte1reVencimiento("$ " + entero + "." + decimal);
    }

    public Impuesto getImpuestoPorNumeroDeFactura(String idFactura) {
        Impuesto impuesto = this.getObject(idFactura);
        this.parsearFechaPrimerVencimiento(impuesto);
        this.verificarVencimientoDeTasa(impuesto);
        return impuesto;
    }

    public JasperPrint crearReporte(JasperReport jasperStream, Map<String, Object> params) throws JRException {
        return this.dao.crearReporte(jasperStream, params);
    }
}