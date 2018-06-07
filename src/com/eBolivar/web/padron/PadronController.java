package com.eBolivar.web.padron;

import com.eBolivar.domain.Padron;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.Tasa;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.tasa.interfaces.ITasaService;
import com.eBolivar.validator.PadronAsociadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("padron")
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
    private ITasaService tasaService;


    @RequestMapping("asociarPadronACUIT")
    private String asociarPadronACUIT(){
        return "padron/asociarCUIT";
    }

    @RequestMapping("save")
    private String save(@ModelAttribute PadronAsociado padronAsociado, BindingResult result, RedirectAttributes redirectAttributes){
        this.validator.validate(padronAsociado, result);
        if(result.hasErrors()) return "padron/asociarCUIT";
        cuitPorTasaService.save(padronAsociado);
        redirectAttributes.addAttribute("id", padronAsociado.getId());

        return "redirect:show";
    }

    @RequestMapping("show")
    private String show(@RequestParam Integer id, Model model){
        model.addAttribute("padronAsociado", cuitPorTasaService.get(id));

        return "/padron/informacion";
    }

    @RequestMapping("byPersona")
    private String byPersona(@RequestParam Integer idPersona, Model model){
        Persona persona = personaService.get(idPersona);
        model.addAttribute("padronAsociado", cuitPorTasaService.byPersona(persona));

        return "persona/padrones/list";
    }


    @ModelAttribute("padronAsociado")
    public PadronAsociado getPadronAsociado(){
        return new PadronAsociado();
    }
}
