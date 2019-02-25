package com.eBolivar.web.persona;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.Domicilio;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.service.cuitPorTasa.CuitPorTasaService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.validator.PadronAsociadoValidator;
import com.eBolivar.validator.PersonaValidator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping({"/personas"})
public class PersonaController
{
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private CuitPorTasaService cuitPorTasaService;
    @Autowired
    private IPadronService padronService;
    @Autowired
    private PersonaValidator personaValidator;
    @Autowired
    private PadronAsociadoValidator padronAsociadoValidator;

    public PersonaController() {}

    @RequestMapping(value={"/busquedaPorCuit"}, method={RequestMethod.GET, RequestMethod.POST})
    public String obtenerPersonaPor(@RequestParam String cuit, Model model)
            throws Exception
    {
        Persona persona = personaService.getByCUIT(cuit);
        model.addAttribute("persona", persona);
        model.addAttribute("padronesAsociados", cuitPorTasaService.byPersona(persona));

        return "persona/show";
    }


    @RequestMapping(value={"/busquedaPorNombreYApellido"}, method={RequestMethod.GET, RequestMethod.POST})
    public String obtenerPersonasPor(@RequestParam(value="nombre", required=false) String nombre, @RequestParam(value="apellido", required=false) String apellido, Model model)
    {
        List<Persona> personas = personaService.getByNombreYApellido(nombre, apellido);
        model.addAttribute("personas", personas);

        return "persona/list";
    }

    @RequestMapping({"/busquedaPorPadron"})
    public String obtenerPadrones(@RequestParam Integer idPadron, Model model) {
        Padron padron = padronService.get(idPadron);
        List<PadronAsociado> padrones = personaService.getByPadron(padron);
        model.addAttribute("padrones", padrones);

        return "persona/padrones/list";
    }

    @RequestMapping({"/get"})
    public String get(@RequestParam Integer id, Model model) {
        Persona persona = personaService.get(id);
        model.addAttribute("persona", persona);
        model.addAttribute("padronesAsociados", cuitPorTasaService.byPersona(persona));

        return "persona/show";
    }

    @RequestMapping(value = {"asociarPadron"}, method = {RequestMethod.POST})
    public String agregarPadron(@ModelAttribute PadronAsociado padronAsociado, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        this.padronAsociadoValidator.validate(padronAsociado, result);
        if(result.hasErrors()) {
            Persona persona = personaService.get(padronAsociado.getPersona().getId());
            model.addAttribute("persona", persona);
            model.addAttribute("padronesAsociados", cuitPorTasaService.byPersona(persona));

            return "persona/show";
        } else {
            this.cuitPorTasaService.save(padronAsociado);
            redirectAttributes.addAttribute("id", padronAsociado.getPersona().getId());
            return "redirect:get";
        }
    }

    @RequestMapping(value={"desasociarPadron"}, method={RequestMethod.GET})
    public String quitarPadron(@RequestParam Integer id, RedirectAttributes redirectAttributes) {
        PadronAsociado padronAsociado = cuitPorTasaService.get(id);
        Persona persona = padronAsociado.getPersona();
        cuitPorTasaService.remove(id);
        redirectAttributes.addAttribute("id", persona.getId());

        return "redirect:get";
    }

    @RequestMapping({"create"})
    public String create() {
        return "persona/create";
    }

    @RequestMapping(value={"save"}, method={RequestMethod.POST})
    public String save(@ModelAttribute Persona persona, BindingResult result, RedirectAttributes redirectAttributes) {
        personaValidator.validate(persona, result);
        if (result.hasErrors()) return "persona/create";
        personaService.save(persona);
        redirectAttributes.addAttribute("cuit", persona.getIdPersona());

        return "redirect:busquedaPorCuit";
    }

    @RequestMapping(value={"search"}, method={RequestMethod.POST})
    public String search(@ModelAttribute SearchObject searchObject, Model model) {
        model.addAttribute("personas", personaService.search(searchObject));
        model.addAttribute("searchObject", searchObject);

        return "persona/list";
    }

    @RequestMapping({"list"})
    public String search(Model model) {
        SearchObject searchObject = new SearchObject();
        model.addAttribute("personas", personaService.search(searchObject));

        return "persona/list";
    }

    @ModelAttribute("persona")
    public Persona getPersona()
    {
        return new Persona();
    }

    @ModelAttribute("padronAsociado")
    public PadronAsociado getPadronAsociado() {
        return new PadronAsociado();
    }

    @ModelAttribute("searchObject")
    public SearchObject getSearchObject() {
        return new SearchObject();
    }

    @RequestMapping(value={"update"})
    public String update(@RequestParam Integer id, Model model) {
        Persona persona = personaService.get(id);
        model.addAttribute("persona", persona);

        return "persona/update";
    }


    public String asociarDomicilio(@RequestParam Integer id, Model model){
        Persona persona = personaService.get(id);
        model.addAttribute("domicilio", new Domicilio(persona));

        return "domicilio/create";
    }


//    public String saveDomicilio(@ModelAttribute Domicilio domicilio){
//         Persona persona = personaService.get(domicilio.getPersona().getId());
//        personaService.save(persona);
//    }
}