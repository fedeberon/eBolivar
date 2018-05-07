/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.impuesto;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.eBolivar.common.MailSender;
import com.eBolivar.common.SearchObject;
import com.eBolivar.common.ValidarEmail;
import com.eBolivar.domain.Documento;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.domain.TipoImpuesto;
import com.eBolivar.service.BannerServiceImpl;
import com.eBolivar.service.ConsultasServiceImpl;
import com.eBolivar.service.ImpuestoServiceImpl;
import com.eBolivar.service.NotificacionPadronServiceImpl;
import com.eBolivar.service.ParametroServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;

public class ImpuestoController extends MultiActionController {

	private ImpuestoServiceImpl impuestoService;
	private ParametroServiceImpl parametroService;
	private NotificacionPadronServiceImpl notificacionPadronService;
	private TipoImpuestoServiceImpl tipoImpuestoService;

	public void setTipoImpuestoService(
			TipoImpuestoServiceImpl tipoImpuestoService) {
		this.tipoImpuestoService = tipoImpuestoService;
	}

	public ImpuestoController() {
		setSupportedMethods(new String[] { METHOD_GET, METHOD_POST });
	}

	public NotificacionPadronServiceImpl getNotificacionPadronService() {
		return notificacionPadronService;
	}

	public void setNotificacionPadronService(
			NotificacionPadronServiceImpl notificacionPadronService) {
		this.notificacionPadronService = notificacionPadronService;
	}

	public ParametroServiceImpl getParametroService() {
		return parametroService;
	}

	public void setParametroService(ParametroServiceImpl parametroService) {
		this.parametroService = parametroService;
	}

	public ImpuestoServiceImpl getImpuestoService() {
		return impuestoService;
	}

	public void setImpuestoService(ImpuestoServiceImpl impuestoService) {
		this.impuestoService = impuestoService;
	}

	public ModelAndView list(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView(
				"redirect:search?mostrar=Proceso:true");
		return mav;
	}

	public ModelAndView search(HttpServletRequest req, HttpServletResponse res) {
		SearchObject search = new SearchObject();
		ModelAndView mav = new ModelAndView("impuesto/list");
		String mostrar = "";
		if (req.getParameter("mostrar") != null
				&& !req.getParameter("mostrar").equalsIgnoreCase("0")) {
			mostrar = req.getParameter("mostrar");
		}
		ArrayList<String> aMostrar = new ArrayList<String>();

		StringTokenizer st = new StringTokenizer(mostrar, ",");
		while (st.hasMoreTokens()) {
			String tk = st.nextToken();
			String key = tk.substring(0, tk.indexOf(":"));
			String value = tk.substring(tk.indexOf(":") + 1);
			if (value.equalsIgnoreCase("true")) {
				if (aMostrar.indexOf(key) == -1)
					aMostrar.add(key);
			} else {
				int ix = aMostrar.indexOf(key);
				while (ix > -1) {
					aMostrar.remove(ix);
					ix = aMostrar.indexOf(key);
				}
			}
		}
		mostrar = "";
		for (Iterator iterator = aMostrar.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			mav.addObject(string, "on");
			mostrar += string + ":true,";
		}

		mav.addObject("inputMostrar", mostrar);
		mav.addObject("mostrar", aMostrar);

		String campo;
		if (req.getParameter("campo") != null
				&& !req.getParameter("campo").equalsIgnoreCase("0")) {
			campo = req.getParameter("campo");
			search.setCampo(campo);
			mav.addObject("campo", campo);
		}
		String ubicacion;
		if (req.getParameter("ubicacion") != null
				&& !req.getParameter("ubicacion").equalsIgnoreCase("0")) {
			ubicacion = req.getParameter("ubicacion");
			search.setUbicacion(ubicacion);
			mav.addObject("ubicacion", ubicacion);
		}
		String valor;
		if (req.getParameter("valor") != null
				&& !req.getParameter("valor").equalsIgnoreCase("0")) {
			valor = req.getParameter("valor");
			search.setValor(valor);
			mav.addObject("valor", valor);
		}

		if (req.getParameter("vista") != null) {
			if (req.getParameter("vista").toString().equalsIgnoreCase("EXCEL")) {
				// mav.setView(new ReporteImpuestosExcelView());
			} else {
				int page = (req.getParameter("page") != null && !req
						.getParameter("page").equalsIgnoreCase("")) ? Integer
						.parseInt(req.getParameter("page")) : 1;
				search.setPage(page);
				mav.addObject("page", page);
			}
		} else {
			int page = (req.getParameter("page") != null) ? Integer
					.parseInt(req.getParameter("page")) : 1;
			search.setPage(page);
			mav.addObject("page", page);
		}

		List impuestos = impuestoService.searchObjects(search);
		if (impuestos.size() < 20)
			mav.addObject("noMorePages", 1);

		mav.addObject("impuestos", impuestos);
		return mav;
	}

	public ModelAndView show(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/show");

		String id;
		try {
			id = req.getParameter("idImpuestos");
		} catch (NullPointerException e) {
			id = "";
		}

		mav.addObject("impuesto", impuestoService.getObject(id));
		return mav;
	}

	public ModelAndView impuestos(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/impuestos");

		String tipoImpuesto = req.getParameter("tipoImpuesto");

		TipoImpuestoServiceImpl tipoImpuestoServ = new TipoImpuestoServiceImpl();

		List<TipoImpuesto> tipos = tipoImpuestoServ.getObjects();

		mav.addObject("tipos", tipos);

		int codigo = (tipoImpuesto != null
				&& !tipoImpuesto.equalsIgnoreCase("") ? Integer
				.parseInt(tipoImpuesto) : 0);

		for (int i = 0; i < tipos.size(); i++) {

			if (tipos.get(i).getCodigo() == codigo) {
				mav.addObject("tipo", tipos.get(i));
				break;
			}
		}

		mav.addObject("instrucciones", new ArrayList<String>(Arrays.asList("Ingrese Numero de Padron", "Seleccione Impuesto a Cancelar")));
		if (req.getParameter("vista")!=null){
			
			if (req.getParameter("vista").toString().equalsIgnoreCase("isScreenTouch")){
				mav.addObject("isScreenTouch", true);
			}
			
		}
		return mav;

	}

	public ModelAndView homeWeb(HttpServletRequest req, HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/homeWeb");

		TipoImpuestoServiceImpl tipoImpuestoServ = new TipoImpuestoServiceImpl();
		BannerServiceImpl bannerServiceImpl = new BannerServiceImpl();

		mav.addObject("banners", bannerServiceImpl.getObjects());
		mav.addObject("tipos", tipoImpuestoServ.getObjects());

		return mav;

	}

	public ModelAndView verImpuestos(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/impuestos");

		return mav;

	}

	public ModelAndView vistaImpuestos(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/impuestos");
		ConsultasServiceImpl consultaServices = new ConsultasServiceImpl();
		System.out.println();

		String idFactura = req.getParameter("idFactura");
		String parametro = "idFactura";

		String path = "/actualizaciones/reporte.jasper";

		OutputStream out = null;

		res.setContentType("text/html");

		try {
			out = res.getOutputStream();
		} catch (Exception e) {

		}

		JasperReport reporte = null;

		try {
			reporte = (JasperReport) JRLoader.loadObject(new File(path));

		} catch (Exception e) {
			// TODO: handle exception
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(parametro, idFactura);

		JasperPrint jp = consultaServices.crearPrint(reporte, map);

		JRExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, false);
		exporter.setParameter(
				JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
				Boolean.TRUE);
		exporter.setParameter(JRHtmlExporterParameter.IS_WHITE_PAGE_BACKGROUND,
				false);
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,
				false);
		exporter.setParameter(JRHtmlExporterParameter.FRAMES_AS_NESTED_TABLES,
				false);
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, "/SIR/img/");
		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				"C:/IMPUESTOS/" + idFactura + ".pdf");

		try {
			MailSender mail = new MailSender();
			String[] destinatarios = { "federicoberon@outlook.com" };
			mail.postMail(destinatarios, "Impuestos WEB",
					"Envio de mails test", "federicoberon@outlook.com",
					"C:/IMPUESTOS/" + idFactura + ".pdf");

			exporter.exportReport();
		} catch (JRException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mav;

	}

	public ModelAndView descargarImpuesto(HttpServletRequest req,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("impuesto/impuestos");
		ConsultasServiceImpl consultaServices = new ConsultasServiceImpl();

		String idFactura = req.getParameter("idFactura");
		String parametro = "idFactura";
		String path = "/actualizaciones/reporte.jasper";

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);

		Impuesto impuesto = impuestoService.getObject(idFactura);

		impuesto.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy")
				.format(impuesto.getPrimerVencimiento()));
		int imp = Integer.parseInt(impuesto.getImporte1reVencimiento());
		String strImp = imp + "";
		String entero = strImp.substring(0, strImp.length() - 2);
		if (entero.length() > 3) {
			entero = entero.substring(0, entero.length() - 3) + ","
					+ entero.substring(entero.length() - 3, entero.length());
		}

		String decimal = strImp.substring(strImp.length() - 2, strImp.length());

		impuesto.setImporte1reVencimiento("$ " + entero + "." + decimal);

		OutputStream out = null;
		try {
			out = response.getOutputStream();
		} catch (IOException e1) {
			// e1.printStackTrace();
		}
		JasperReport reporte = null;
		try {
			reporte = (JasperReport) JRLoader.loadObject(new File(path));

		} catch (Exception e) {
			// TODO: handle exception
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put(parametro, idFactura);
		map.put("importe", impuesto.getImporte1reVencimiento());
		map.put("padron", impuesto.getCodigoDeBarra().substring(7, 15));
		map.put("nroRecibo", impuesto.getCodigoDeBarra().substring(0, 3));

		JasperPrint jp = consultaServices.crearPrint(reporte, map);

		response.setHeader("Content-Disposition", "attachment; filename=\"impuesto.pdf\";");
		response.setContentType("application/pdf");

		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);

		exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
				"C:/IMPUESTOS/impuesto.pdf");

		try {

			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			// OutputStream ouputStream = response.getOutputStream();
			// pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
			// ouputStream);
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					"C:/IMPUESTOS/payment.pdf");
			pdfExporter.exportReport();

			exporter.exportReport();

		} catch (JRException e) {
			e.printStackTrace();
		}

		return mav;

	}

	public ModelAndView actualizaciones(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/actualizaciones");
		ImpuestoServiceImpl impuestoServices = new ImpuestoServiceImpl();
		int mes;
		int anio;
		Calendar calendario = Calendar.getInstance();

		if (req.getParameter("mes") != null
				&& !req.getParameter("mes").equalsIgnoreCase("")) {
			mes = Integer.parseInt(req.getParameter("mes"));
		} else
			mes = calendario.get(Calendar.MONTH) + 1;

		if (req.getParameter("anio") != null
				&& !req.getParameter("anio").equalsIgnoreCase("")) {
			anio = Integer.parseInt(req.getParameter("anio"));
		} else
			anio = calendario.get(Calendar.YEAR);

		mav.addObject("mes", mes);
		mav.addObject("anio", anio);

		List<Documento> docs = impuestoServices.obtenerListadoArchivos(mes,
				anio);
		mav.addObject("documentos", docs);
		mav.addObject("meses", parametroService.getObjects("MESES"));

		return mav;

	}

	public void crearArchivo(String idFactura) {
		String parametro = "idFactura";
		String path = "/actualizaciones/reporte.jasper";
		JRExporter exporterPdf = new JRPdfExporter();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(parametro, idFactura);
		ConsultasServiceImpl consultaServices = new ConsultasServiceImpl();

		try {
			JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(
					path));
			JasperPrint jp = consultaServices.crearPrint(reporte, map);

			JRPdfExporter pdfExporter = new JRPdfExporter();
			pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
			// OutputStream ouputStream = response.getOutputStream();
			// pdfExporter.setParameter(JRExporterParameter.OUTPUT_STREAM,
			// ouputStream);
			pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME,
					"C:/IMPUESTOS/payment.pdf");
			pdfExporter.exportReport();

			MailSender mail = new MailSender();
			mail.postMail("lfberon@gmail.com", "Impuestos WEB",
					"Impuestos Web", "federico.beron@outlook.com.ar",
					"C:/IMPUESTOS/payment.pdf");

			exporterPdf.exportReport();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ModelAndView compilarArchivo(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView();
		String fileName = req.getParameter("filename");
		Integer result = null;
		File destinationFile = new File(fileName);

//		if (destinationFile != null) {
//			result = impuestoService.readTxt(destinationFile);
//		}

		if (result == null) {
			mav.addObject("mensaje", "Error en la Actualizacion.");
		} else if (result == 1) {
			mav.addObject("mensaje", "Se actualizo correctamente.");
		} else if (result == 0) {
			mav.addObject("error", "NO SE ACTUALIZO. INTENTE DE NUEVO.");
		} else {
			mav.addObject("mensaje", null);
			mav.addObject("error", null);
		}

		mav.setViewName("redirect:actualizaciones");
		return mav;
	}

	public ModelAndView consultaReclamos(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/reclamos");
		mav.addObject("tipos", tipoImpuestoService.getObjects());

		return mav;
	}

	public ModelAndView enviarMailsAsociados(HttpServletRequest req,
			HttpServletResponse res) {

		ModelAndView mav = new ModelAndView();
		List<NotificacionPadron> notificacionesParaMails = new ArrayList<NotificacionPadron>();

		try {
			String nombreArchivo = req.getParameter("nombreArchivo");
			List<Impuesto> impuestos = impuestoService
					.obtenerImpuestosSinNotificacion(nombreArchivo);
			List<NotificacionPadron> notificaciones = notificacionPadronService
					.getObjects();
			ArrayList<Impuesto> impuestosNotificados = new ArrayList<Impuesto>();
			for (NotificacionPadron n : notificaciones) {

				for (Impuesto i : impuestos) {
					if (n.getPadron().compareTo(
							i.getCodigoDeBarra().substring(7, 15)) == 0) {

						n.setIdFactura(i.getIdFactura());
						notificacionesParaMails.add(n);
						impuestosNotificados.add(i);
						break;
					}
				}
			}

			for (NotificacionPadron n : notificacionesParaMails) {

				impuestoService.crearEnviarArchivoPorMail(n);

			}

			Integer valor = impuestoService
					.actualizarMailsEnviados(impuestosNotificados);
			if (valor == null) {
				mav.addObject("mensaje", "No se actualizo.");
			} else
				mav.addObject("mensaje", "Se actualizo correctamente.");

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

		mav.setViewName("redirect:actualizaciones");
		return mav;

	}

	public ModelAndView guardarReclamos(HttpServletRequest req,
			HttpServletResponse res) {
		ModelAndView mav = new ModelAndView("impuesto/reclamos");
		String nombre = "", apellido = "", email = "", descripcion = "", telefono = "", dni = "", localidad = "", calle = "", numero = "",ubicacion="";
		ArrayList<String> errors = new ArrayList<String>();

		if (req.getParameter("nombre") != null
				&& !req.getParameter("nombre").equalsIgnoreCase("")
				&& req.getParameter("nombre").length() > 2) {
			nombre = req.getParameter("nombre");
			mav.addObject("nombre", nombre);
		} else
			errors.add("Ingrese un Nombre");

		if (req.getParameter("apellido") != null
				&& !req.getParameter("apellido").equalsIgnoreCase("")
				&& req.getParameter("apellido").length() > 2) {
			apellido = req.getParameter("apellido");
			mav.addObject("apellido", apellido);
		} else
			errors.add("Ingrese un Apellido");

		if (req.getParameter("email") != null
				&& !req.getParameter("email").equalsIgnoreCase("")
				&& req.getParameter("email").length() > 2
				&& ValidarEmail.validateEmail(req.getParameter("email"))) {
			email = req.getParameter("email");
			mav.addObject("email", email);
		} else
			errors.add("e-Mail Incorrecto");

		if (req.getParameter("telefono") != null
				&& !req.getParameter("telefono").equalsIgnoreCase("")
				&& req.getParameter("telefono").length() > 2) {
			telefono = req.getParameter("telefono");
			mav.addObject("telefono", telefono);
		} else
			errors.add("Ingrese un tel�fono");

		if (req.getParameter("dni") != null
				&& !req.getParameter("dni").equalsIgnoreCase("")
				&& req.getParameter("dni").length() > 2) {
			dni = req.getParameter("dni");
			mav.addObject("dni", dni);
		} else
			errors.add("Ingrese un D.N.I");

		/**
		if (req.getParameter("localidad") != null
				&& !req.getParameter("localidad").equalsIgnoreCase("")
				&& req.getParameter("localidad").length() > 2) {
			localidad = req.getParameter("localidad");
			mav.addObject("localidad", localidad);
			descripcion += " - " + localidad;
		} else
			errors.add("Ingrese una Localidad");

		if (req.getParameter("calle") != null
				&& !req.getParameter("calle").equalsIgnoreCase("")
				&& req.getParameter("calle").length() > 2) {
			calle = req.getParameter("calle");
			mav.addObject("calle", calle);
			descripcion += " - " + calle;
		} else
			errors.add("Ingrese una Calle");

		 */
		
		if (req.getParameter("ubicacion") != null
				&& !req.getParameter("ubicacion").equalsIgnoreCase("")
				&& req.getParameter("ubicacion").length() > 5) {
			ubicacion = req.getParameter("ubicacion");
			mav.addObject("ubicacion", ubicacion);
			descripcion += " - " + ubicacion;
		} else
			errors.add("Ingrese una Ubicacion Valida");

		

		if (req.getParameter("numero") != null
				&& !req.getParameter("numero").equalsIgnoreCase("")
				&& req.getParameter("numero").length() > 2) {
			numero = req.getParameter("numero");
			mav.addObject("numero", numero);
			descripcion += ": " + numero;
		} else
			errors.add("Ingrese un numero");

		if (req.getParameter("descripcionReclamo") != null
				&& !req.getParameter("descripcionReclamo").equalsIgnoreCase("")
				&& req.getParameter("descripcionReclamo").length() > 2) {
			descripcion += "\n\n-------------Mensaje---------------\n\n"
					+ req.getParameter("descripcionReclamo");
			mav.addObject("descripcion", descripcion);
		} else
			errors.add("Ingrese una Descripcion");

		if (errors.isEmpty()) {
			ConsultasServiceImpl consulta = new ConsultasServiceImpl();
			int result = consulta.insertarReclamo(nombre, apellido, email, descripcion);
			if (result != -1)
				mav.addObject(
						"mensaje",
						" Su solicitud ha sido procesada. Un operador se pondra en contacto con Ud y luego el tramite estar� finalizado.");
			else
				return mav
						.addObject("mensaje", "No se pudo Guardar el Reclamo");
			mav.setViewName("impuesto/notificaciones");
		} else
			mav.addObject("errors", errors).addObject("mensaje", null);

		TipoImpuestoServiceImpl tipoImpuestoServ = new TipoImpuestoServiceImpl();

		mav.addObject("tipos", tipoImpuestoServ.getObjects());

		return mav;
	}

}
