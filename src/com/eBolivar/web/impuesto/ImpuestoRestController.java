package com.eBolivar.web.impuesto;

import com.eBolivar.domain.DetalleFactura;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.LoginTicketResponse;
import com.eBolivar.domain.NotificacionPorBien;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.DetalleFacturaServiceImpl;
import com.eBolivar.service.ImpuestoServiceImpl;
import com.eBolivar.service.NotificacionPorBienServiceImpl;
import com.eBolivar.service.autenticacion.interfaces.IAutenticacionAFIPService;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.reporte.ReporteService;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping({"/tasas"})
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
    @Autowired
    private IPadronService padronService;

    public ImpuestoRestController() {
    }

    @RequestMapping(
            value = {"/{padron}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<Impuesto> getTasa(@PathVariable String padron) {
        List impuestos = this.impuestoService.getActivosByPadron(padron);
        Iterator var3 = impuestos.iterator();

        while(var3.hasNext()) {
            Impuesto i = (Impuesto)var3.next();
            this.impuestoService.parsearImporte(i);
            this.impuestoService.parsearFechaPrimerVencimiento(i);
            this.impuestoService.verificarVencimientoDeTasa(i);
        }

        return impuestos;
    }

    @RequestMapping(
            value = {"/descargar"},
            method = {RequestMethod.GET}
    )
    public ModelAndView descargarTasa(@RequestParam("idFactura") String idFactura, HttpServletResponse response) throws JRException, IOException {
        Impuesto impuesto = this.impuestoService.getImpuestoPorNumeroDeFactura(idFactura);
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=Tasa Municipal - Bolivar.pdf");
        ServletOutputStream outStream = response.getOutputStream();
        this.reporteService.descargarTasa(impuesto, outStream);
        return new ModelAndView("impuesto/impuestos");
    }

    @RequestMapping(
            value = {"/notificaciones/{idFactura}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<NotificacionPorBien> getNotificacionesDeTasa(@PathVariable String idFactura) {
        Impuesto impuesto = this.impuestoService.getImpuestoPorNumeroDeFactura(idFactura);
        Long codigoElectronicoDePago = Long.valueOf(Long.parseLong(impuesto.getCodigoElectronicoPago()));
        return this.notificacionPorBienService.getNotifiacionesDeTasa(codigoElectronicoDePago);
    }

    @RequestMapping(
            value = {"/detalles/{idFactura}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public List<DetalleFactura> getDetallesDeTasa(@PathVariable String idFactura) {
        Impuesto impuesto = this.impuestoService.getImpuestoPorNumeroDeFactura(idFactura);
        return this.detalleFacturaService.getDetalleDeTasaPorNumeroDeFactura(impuesto.getIdFactura());
    }

    @RequestMapping(
            value = {"/verificarCuitAsociado/{padron}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public boolean isCuitAsociadoAPadron(@PathVariable String padron) {
        return this.cuitPorTasaService.isCuitAsociadoAPadron(padron);
    }

    @RequestMapping(
            value = {"/guardarCuitAsociadoAlUsuario/{idPadron}/{cuit}/{leyenda}"},
            method = {RequestMethod.GET}
    )
    public ResponseEntity guardarCuitAsociadoAlUsuario(@PathVariable Integer idPadron, @PathVariable String cuit, @PathVariable String leyenda) {
        try {
            Persona e = this.personaService.getByCUIT(cuit);
            Padron padron = this.padronService.get(idPadron);
            if(e == null) {
                LoginTicketResponse padronAsociado = this.autenticacionAFIPService.obtenerCredenciales();
                e = this.personaService.create_PersonaRequest(padronAsociado.getToken(), padronAsociado.getSign(), Persona.CUIT_REPRESENTADA_MUNICIPALIDAD_BOLIVAR, cuit);
            }

            PadronAsociado padronAsociado1 = new PadronAsociado(e, padron);
            this.cuitPorTasaService.save(padronAsociado1);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception var7) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(
            value = {"/verificarCuitAsociadoPorFactura/{idFactura}"},
            method = {RequestMethod.GET}
    )
    @ResponseBody
    public boolean isCuitAsociadoAPadronPorFactura(@PathVariable String idFactura) {
        Impuesto impuesto = this.impuestoService.getObject(idFactura);
        return this.cuitPorTasaService.isCuitAsociadoAPadron(impuesto.getNumeroDePadron());
    }

    @RequestMapping({"/verificarPadron/{padron}"})
    @ResponseBody
    public boolean verificarPadron(@PathVariable String padron) {
            return this.impuestoServicio.isUnPadron(padron);
    }
}
