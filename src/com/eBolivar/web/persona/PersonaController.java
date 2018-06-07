package com.eBolivar.web.persona;

import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.cuitPorTasa.CuitPorTasaService;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value = "/personas")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IImpuestoService impuestoService;

    @Autowired
    private CuitPorTasaService cuitPorTasaService;

    @Autowired
    private IPadronService padronService;


    @RequestMapping(value = "/busquedaPorCuit", method =  { RequestMethod.GET , RequestMethod.POST } )
    public String obtenerPersonaPor(@RequestParam String cuit, Model model) throws Exception {
        Persona persona = personaService.getByCUIT(cuit);
        List<PadronAsociado> padronesAsociados = cuitPorTasaService.byPersona(persona);
        model.addAttribute("persona", persona);
        model.addAttribute("padronesAsociados", padronesAsociados);

        return "persona/show";
    }

    @RequestMapping(value = "/busquedaPorNombreYApellido", method = { RequestMethod.GET, RequestMethod.POST })
    public String obtenerPersonasPor(@RequestParam(value = "nombre", required = false) String nombre,
                                                    @RequestParam(value = "apellido", required = false) String apellido,
                                                    Model model){
        List<Persona> personas = personaService.getByNombreYApellido(nombre, apellido);
        model.addAttribute("personas", personas);

        return "persona/list";
    }


    @RequestMapping(value = "/busquedaPorPadron")
    public String obtenerPadrones(@RequestParam Integer idPadron, Model model){
        Padron padron = padronService.get(idPadron);
        List<PadronAsociado> padrones = personaService.getByPadron(padron);
        model.addAttribute("padrones", padrones);

        return "persona/padrones/list";
    }

    @RequestMapping(value = "/get", method = { RequestMethod.GET, RequestMethod.POST })
    public String get(@RequestParam Integer id,
                      Model model){
        Persona persona = personaService.get(id);
        model.addAttribute("persona", persona);

        return "persona/show";
    }

    @RequestMapping(value = "/agregarPadronAPersona")
    public String agregarPadron(@RequestParam String idPadron, @RequestParam Integer idPersona, Model model){
        Persona persona = personaService.get(idPersona);

        Padron padron = padronService.getByNumero(idPadron);

        if(padron == null){
            List<Impuesto> impuestos = impuestoService.getByPadron(idPadron);
            if(impuestos != null && impuestos.size() >=1 ){
                Impuesto impuesto = impuestos.get(0);
                padron = new Padron(impuesto.getNumeroDePadron(), impuesto.getTipoImpuesto());
                padronService.save(padron);
            }
            else return "";
        }

        PadronAsociado padronAsociado = new PadronAsociado(persona, padron);
        cuitPorTasaService.save(padronAsociado);
        model.addAttribute("cuit" , persona.getIdPersona());

        return "redirect:busquedaPorCuit";
    }
}
