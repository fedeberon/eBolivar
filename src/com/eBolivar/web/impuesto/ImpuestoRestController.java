package com.eBolivar.web.impuesto;

import com.eBolivar.domain.*;
import com.eBolivar.service.DetalleFacturaServiceImpl;
import com.eBolivar.service.ImpuestoServiceImpl;
import com.eBolivar.service.NotificacionPorBienServiceImpl;
import com.eBolivar.service.autenticacion.interfaces.IAutenticacionAFIPService;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.reporte.ReporteService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Fede Beron on 5/12/2016.
 */
@Controller
@RequestMapping("/tasas")
public class ImpuestoRestController {

    @Autowired
    ImpuestoServiceImpl impuestoService;
    @Autowired
    @Qualifier("impuestoServicio")
    private IImpuestoService impuestoServicio;
    @Autowired
    NotificacionPorBienServiceImpl notificacionPorBienService;
    @Autowired
    DetalleFacturaServiceImpl detalleFacturaService;
    @Autowired
    ReporteService reporteService;
    @Autowired
    private ICuitPorTasaService cuitPorTasaService;
    @Autowired
    private IAutenticacionAFIPService autenticacionAFIPService;
    @Autowired
    private IPersonaService personaService;

    @RequestMapping(value = "/{padron}", method = RequestMethod.GET)
    public @ResponseBody  List<Impuesto> getTasa(@PathVariable String padron) {
        List<Impuesto> impuestos = impuestoService.getActivosByPadron(padron);

        for (Impuesto i : impuestos) {
            impuestoService.parsearImporte(i);
            impuestoService.parsearFechaPrimerVencimiento(i);
            impuestoService.verificarVencimientoDeTasa(i);
        }

        return impuestos;
    }

    @RequestMapping(value = "/descargar", method = RequestMethod.GET)
    public ModelAndView descargarTasa(@RequestParam(value = "idFactura") String idFactura, HttpServletResponse response) throws JRException, IOException {
        Impuesto impuesto = impuestoService.getImpuestoPorNumeroDeFactura(idFactura);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=Tasa Municipal - Bolivar.pdf");
        final OutputStream outStream = response.getOutputStream();
        reporteService.descargarTasa(impuesto, outStream);

        return  new ModelAndView("impuesto/impuestos");
    }

    @RequestMapping(value = "/notificaciones/{idFactura}", method = RequestMethod.GET)
    public @ResponseBody List<NotificacionPorBien> getNotificacionesDeTasa(@PathVariable String idFactura){
        Impuesto impuesto  = impuestoService.getImpuestoPorNumeroDeFactura(idFactura);
        Long codigoElectronicoDePago = Long.parseLong(impuesto.getCodigoElectronicoPago());

        return notificacionPorBienService.getNotifiacionesDeTasa(codigoElectronicoDePago);
    }

    @RequestMapping(value = "/detalles/{idFactura}", method = RequestMethod.GET)
    public @ResponseBody List<DetalleFactura> getDetallesDeTasa(@PathVariable String idFactura){
        Impuesto impuesto  = impuestoService.getImpuestoPorNumeroDeFactura(idFactura);

        return detalleFacturaService.getDetalleDeTasaPorNumeroDeFactura(impuesto.getIdFactura());
    }

    @RequestMapping(value = "/verificarCuitAsociado/{padron}", method = RequestMethod.GET)
    public @ResponseBody boolean isCuitAsociadoAPadron(@PathVariable String padron){
        return cuitPorTasaService.isCuitAsociadoAPadron(padron);
    }

    @RequestMapping(value = "/guardarCuitAsociadoAlUsuario/{padron}/{cuit}/{leyenda}", method = RequestMethod.GET)
    public ResponseEntity guardarCuitAsociadoAlUsuario(@PathVariable String padron, @PathVariable String cuit, @PathVariable String leyenda) {
        try {

            Persona persona = personaService.getByCUIT(cuit);

            if(persona == null){
                LoginTicketResponse credencial = autenticacionAFIPService.obtenerCredenciales();
                persona = personaService.create_PersonaRequest(credencial.getToken(), credencial.getSign(), Persona.CUIT_REPRESENTADA_MUNICIPALIDAD_BOLIVAR, cuit);
            }

            PadronAsociado padronAsociado = new PadronAsociado(persona, padron, leyenda);
            cuitPorTasaService.save(padronAsociado);

            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @RequestMapping(value = "/verificarCuitAsociadoPorFactura/{idFactura}", method = RequestMethod.GET)
    public @ResponseBody boolean isCuitAsociadoAPadronPorFactura(@PathVariable String idFactura){
        Impuesto impuesto = impuestoService.getObject(idFactura);
        return cuitPorTasaService.isCuitAsociadoAPadron(impuesto.getNumeroDePadron());
    }


    @RequestMapping(value = "/verificarPadron/{padron}")
    public  @ResponseBody boolean verificarPadron(@PathVariable String padron){
         return impuestoServicio.isUnPadron(padron);
    }

}
