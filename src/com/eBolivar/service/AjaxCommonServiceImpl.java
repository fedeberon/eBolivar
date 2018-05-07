package com.eBolivar.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import com.eBolivar.domain.EstadoDeTasa;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import org.json.JSONArray;

import com.eBolivar.common.MailSender;
import com.eBolivar.dao.ImpuestoDaoHibernateImpl;
import com.eBolivar.dao.NotificacionPadronDaoHibernateImpl;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.Reclamos;

public class AjaxCommonServiceImpl {

	ReclamoServicesImpl reclamoServices;
	ConsultasServiceImpl consultasService;
	ImpuestoServiceImpl impuestoService;
	String pathTemplateImpuesto;
	String pathOutputImpuesto;
	EspacioServiceImpl espacioService;
	TipoEspacioServiceImpl tipoEspacioService;
	
	
	public void setEspacioService(EspacioServiceImpl espacioService) {
		this.espacioService = espacioService;
	}

	public void setTipoEspacioService(TipoEspacioServiceImpl tipoEspacioService) {
		this.tipoEspacioService = tipoEspacioService;
	}

	public String getPathTemplateImpuesto() {
		return pathTemplateImpuesto;
	}

	public String getPathOutputImpuesto() {
		return pathOutputImpuesto;
	}

	public void setImpuestoService(ImpuestoServiceImpl impuestoService) {
		this.impuestoService = impuestoService;
	}

	public void setPathOutputImpuesto(String pathOutputImpuesto) {
		this.pathOutputImpuesto = pathOutputImpuesto;
	}

	public void setPathTemplateImpuesto(String pathTemplateImpuesto) {
		this.pathTemplateImpuesto = pathTemplateImpuesto;
	}

	public void setConsultasService(ConsultasServiceImpl consultasService) {
		this.consultasService = consultasService;
	}

	public void setReclamoServices(ReclamoServicesImpl reclamoServices) {
		this.reclamoServices = reclamoServices;
	}

	public List<Impuesto> obtenerImpuestosByCliente(String idCliente) {
		ImpuestoDaoHibernateImpl impuestoDaoHibernateImpl = (ImpuestoDaoHibernateImpl) PersistenceContextSingleton
				.getInstance().getBean("impuestoDao");
		List<Impuesto> impuestos = impuestoDaoHibernateImpl
				.obtenerImpuestosByCliente(idCliente);

		try {

			for (Impuesto i : impuestos) {

				enmascararImporte(i);

				i.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy")
						.format(i.getPrimerVencimiento()));

				if (i.getEstado() != null && i.getEstado() == 0) {

					i.setEstado_Pago("Adeudado");

				}

				else if (i.getEstado() != null && i.getEstado() == 1) {

					i.setEstado_Pago("Pagado");

				}

				if (new Date().after(i.getPrimerVencimiento())) {

					i.setEstado_Vencimiento("Vencido");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return impuestos;
	}



    private void verificarVencimientoDeTasa(Impuesto i) {
        if (new Date().after(i.getPrimerVencimiento())) {
            i.setEstado_Vencimiento(EstadoDeTasa.VENCIDO);
        }
    }

    private void parsearFechaPrimerVencimiento(Impuesto i) {
        i.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").format(i.getPrimerVencimiento()));
    }

    private void parsearImporte(Impuesto i) {
        int importe = Integer.parseInt(i.getImporte1reVencimiento());

        String strImporte = String.valueOf(importe);

        String entero = strImporte.substring(0, strImporte.length() - 2);

        if (entero.length() > 3) {
            entero = entero.substring(0, entero.length() - 3) + "," + entero.substring(entero.length() - 3, entero.length());
        }

        String decimal = strImporte.substring(strImporte.length() - 2, strImporte.length());

        i.setImporte1reVencimiento("$ " + entero + "." + decimal);
    }

    public Impuesto obtenerImpuesto(String idFactura) {
		ImpuestoDaoHibernateImpl impuestoDaoHibernateImpl = (ImpuestoDaoHibernateImpl) PersistenceContextSingleton
				.getInstance().getBean("impuestoDao");
		Impuesto impuesto = impuestoDaoHibernateImpl.getObject(idFactura);
		impuesto.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy")
				.format(impuesto.getPrimerVencimiento()));
		enmascararImporte(impuesto);
		return impuesto;
	}

	public Reclamos obtenerReclamo(Integer codigo) {
		return reclamoServices.getReclamoById(codigo);
	}

	public Integer crearEnviarArchivoPorMail(String idFactura, String addressMail) {
		String parametro = "idFactura";
		
			Impuesto impuesto = impuestoService.getObject(idFactura);
		
			impuesto.setFechaVencimiento(new SimpleDateFormat("dd/MM/yyyy").format(impuesto.getPrimerVencimiento()));
			
			enmascararImporte(impuesto);
		
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(parametro, idFactura);
			map.put("importe", impuesto.getImporte1reVencimiento());
			map.put("padron", impuesto.getCodigoDeBarra().substring(7, 15));
			map.put("nroRecibo", impuesto.getCodigoDeBarra().substring(0, 3));
		
			try {

				exportarImpuesto(idFactura, map);
				enviarMail(idFactura, addressMail);
		
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		
		return 1;
	}

	private void enviarMail(String idFactura, String addressMail)
			throws MessagingException {
		MailSender mail = new MailSender();
		String[] destinatarios = { addressMail };
		String mensaje = cuerpoDeMail();
		mail.postMail(destinatarios,"eBolivar - Tasas e Impuestos - Padron: "+ idFactura.substring(3, 11), mensaje,"cac@bolivar.gob.ar", pathOutputImpuesto + idFactura + ".pdf");
	}

	private void exportarImpuesto(String idFactura, Map<String, Object> map)
			throws JRException {
		JasperReport reporte = (JasperReport) JRLoader.loadObject(new File(pathTemplateImpuesto));
		JasperPrint jp = consultasService.crearPrint(reporte, map);

		JRPdfExporter pdfExporter = new JRPdfExporter();
		pdfExporter.setParameter(JRExporterParameter.JASPER_PRINT, jp);
		pdfExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, pathOutputImpuesto + idFactura + ".pdf");
		pdfExporter.exportReport();
	}

	private void enmascararImporte(Impuesto impuesto) {
		int importe = Integer.parseInt(impuesto.getImporte1reVencimiento());
		String strImp = importe + "";
		String entero = strImp.substring(0, strImp.length() - 2);
		if (entero.length() > 3) {
			entero = entero.substring(0, entero.length() - 3)
					+ ","
					+ entero.substring(entero.length() - 3, entero.length());
		}

		String decimal = strImp.substring(strImp.length() - 2, strImp.length());

		impuesto.setImporte1reVencimiento("$ " + entero + "." + decimal);
	}

	public String cuerpoDeMail() {
		String mensaje = ""
				+ "Estimado Vecino: \n"
				+ "Usted esta adherido al sistema de Envio de Impuestos por mail.\n"
				+ "Cualquier inquietud no dude en  comunicarse con nosotros al numero 147 opcion 1.\n"
				+ "Muchas Gracias.\n"
				+ "Un cordial Saludo.\n"
				+ "Centro de Atencion al Cidadano.\n"
				+ "Municipaliad de Bolivar.\n"
				+ "Buenos Aires-Argentina."
				+ "\n\n\n\n ________________________________\n\n\n\n"
				+ "Agradecemos tu contribucion con el medio ambiente.\n"
				+ "Creemos que el compromiso lo hacemos a traves de las pequenas decisiones que tomamos todos los dias.";
		return mensaje;
	}

	public Integer obtenerPadronesParaActualizar() {
		NotificacionPadronDaoHibernateImpl dao = (NotificacionPadronDaoHibernateImpl) PersistenceContextSingleton
				.getInstance().getBean("notificacionPadronDao");
		return dao.obtenerPadronesParaActualizar();
	}
	
	public JSONArray obtenerEspaciosPorTipo(Integer tipo , String area){
		return new JSONArray(espacioService.obtenerEspacios(tipo , area));
	}
	
	
	
}
