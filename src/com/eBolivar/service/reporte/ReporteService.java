package com.eBolivar.service.reporte;

import com.eBolivar.domain.Impuesto;
import com.eBolivar.service.ImpuestoServiceImpl;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Fede Beron on 17/12/2016.
 */
@Service
public class ReporteService {

    @Autowired
    ImpuestoServiceImpl impuestoService;

    public void descargarTasa(Impuesto impuesto, OutputStream outStream) throws JRException {
        String path = "/actualizaciones/reporte.jasper";
        Map<String, Object> params = new HashMap();
        params.put("idFactura", impuesto.getIdFactura());
        params.put("importe", impuesto.getImporte1reVencimiento());
        params.put("padron", impuesto.getNumeroDePadron());
        params.put("nroRecibo", impuesto.getCodigoDeBarra().substring(0, 3));
        JasperReport templateTasa = (JasperReport) JRLoader.loadObject(new File(path));
        JasperPrint jasperPrint = impuestoService.crearReporte(templateTasa, params);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }


    public DataHandler attachmentFile(Impuesto impuesto) throws JRException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JasperExportManager.exportReportToPdfStream(createTasa(impuesto), baos);
        final javax.activation.DataSource dataSource =  new ByteArrayDataSource(baos.toByteArray(), "application/pdf");
        return new DataHandler(dataSource);
    }

    public JasperPrint createTasa(Impuesto tasa) throws JRException {
        Map<String, Object> mapa = new HashMap();
        mapa.put("idFactura", tasa.getIdFactura());
        mapa.put("importe", tasa.getImporte1reVencimiento());
        File file = new File("/actualizaciones/reporte.jasper");
        JasperReport reporte = (JasperReport) JRLoader.loadObject(file);

        return impuestoService.crearReporte(reporte, mapa);

    }

}
