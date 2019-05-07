//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.eBolivar.web.padron;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.*;
import com.eBolivar.service.TipoImpuestoServiceImpl;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.localidad.ILocalidadService;
import com.eBolivar.service.padron.PadronService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.validator.PadronAsociadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping({"padron"})
public class PadronController {
    @Autowired
    private IPadronService padronService;
    @Autowired
    private ICuitPorTasaService cuitPorTasaService;
    @Autowired
    private PadronAsociadoValidator validator;
    @Autowired
    private IPersonaService personaService;
    @Autowired
    private TipoImpuestoServiceImpl tipoImpuestoService;
    @Autowired
    private ILocalidadService localidaService;

    public PadronController() {
    }

    @RequestMapping({"asociarPadronACUIT"})
    private String asociarPadronACUIT() {
        return "padron/asociarCUIT";
    }

    @RequestMapping({"save"})
    private String save(@ModelAttribute PadronAsociado padronAsociado, BindingResult result, RedirectAttributes redirectAttributes) {
        this.validator.validate(padronAsociado, result);
        if(result.hasErrors()) {
            return "padron/asociarCUIT";
        } else {
            this.cuitPorTasaService.save(padronAsociado);
            redirectAttributes.addAttribute("id", padronAsociado.getId());
            return "redirect:show";
        }
    }

    @RequestMapping({"showPadron"})
    private String showPadron(@RequestParam String id, Model model) {
        model.addAttribute("padron", this.cuitPorTasaService.getByNumero(id));

        return "padron/show";
    }

    @RequestMapping({"show"})
    private String show(@RequestParam String id, Model model) {
        model.addAttribute("padronAsociado", this.cuitPorTasaService.getByNumero(id));
        return "/padron/show";
    }

    @RequestMapping({"byPersona"})
    private String byPersona(@RequestParam Integer idPersona, Model model) {
        Persona persona = this.personaService.get(idPersona);
        model.addAttribute("padronAsociado", this.cuitPorTasaService.byPersona(persona));
        return "persona/padrones/list";
    }

    @RequestMapping(value = {"search"}, method = {RequestMethod.POST})
    public String search(@ModelAttribute SearchObject searchObject, Model model) {
        model.addAttribute("padrones", this.padronService.search(searchObject));
        model.addAttribute("searchObject", searchObject);
        return "padron/list";
    }

    @RequestMapping({"list"})
    private String list(Model model) {
        SearchObject searchObject = new SearchObject();
        model.addAttribute("padrones", this.padronService.search(searchObject));
        model.addAttribute("searchObject", searchObject);
        return "padron/list";
    }

    @RequestMapping("create")
    public String create(@ModelAttribute Padron padron, Model model) {
        model.addAttribute("tipoImpuesto", tipoImpuestoService.getObjects());
        model.addAttribute("localidad", localidaService.findAll());
        return "padron/create";
    }

    @ModelAttribute
    public List<TipoImpuesto> getTipoImpuesto(){
        return tipoImpuestoService.getObjects();
    }

    @ModelAttribute("localidades")
    public List<Localidad> getLocalidades() {
        return localidaService.findAll();
    }

    @RequestMapping({"savePadron"})
    public String create(@ModelAttribute Padron padron, BindingResult result, Model model) {
        this.validator.validateNumber(padron, result);
        if(result.hasErrors()) {
            model.addAttribute("tipoImpuesto", getTipoImpuesto());
            model.addAttribute("localidad", getLocalidades());
            return "padron/create";
        } else {
            this.padronService.save(padron);
        }
        return "redirect:list";
    }

    @RequestMapping({"/searchByPadron"})
    public String searchByPadron(@RequestParam String idPadron, Model model) {
        Padron padron = padronService.getByNumero(idPadron);
        List<PadronAsociado> padrones = personaService.getByPadron(padron);
        model.addAttribute("padronAsociado", padrones);

        return "persona/padrones/list";
    }


    @ModelAttribute("padronAsociado")
    public PadronAsociado getPadronAsociado() {
        return new PadronAsociado();
    }

    @ModelAttribute("padron")
    public Padron getPadron() {
        return new Padron();
    }
}
