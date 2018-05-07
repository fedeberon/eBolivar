package com.eBolivar.web.padron;

import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.validator.PadronAsociadoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by Fede Beron on 18/7/2017.
 */
@Controller
@RequestMapping("padron")
public class PadronController {

    @Autowired
    private IPadronService padronService;

    @Autowired
    private ICuitPorTasaService cuitPorTasaService;

    @Autowired
    private PadronAsociadoValidator validator;

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


    @ModelAttribute("padronAsociado")
    public PadronAsociado getPadronAsociado(){
        return new PadronAsociado();
    }
}
