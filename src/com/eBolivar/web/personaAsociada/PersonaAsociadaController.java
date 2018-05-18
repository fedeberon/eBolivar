package com.eBolivar.web.personaAsociada;

import com.eBolivar.domain.Persona;
import com.eBolivar.domain.administradorCuenta.PersonaAsociada;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.personaAsociada.interfaces.IPersonaAsociadaService;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import com.eBolivar.validator.PersonaAsociadaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("personaAsociada")
public class PersonaAsociadaController {


    @Autowired
    private IPersonaAsociadaService personaAsociadaService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PersonaAsociadaValidator validator;


    @RequestMapping("save")
    public String save(@ModelAttribute PersonaAsociada personaAsociada, BindingResult result, RedirectAttributes redirectAttributes){
        this.validator.validate(personaAsociada, result);
        if (result.hasErrors()) return "personaAsociada/create";
        Persona persona = personaService.getByCUIT(personaAsociada.getPersona().getIdPersona().toString());
        personaAsociada.setPersona(persona);
        personaAsociadaService.save(personaAsociada);
        redirectAttributes.addAttribute("username", personaAsociada.getAdministradorCuenta().getUsername());

        return "redirect:list";
    }

    @RequestMapping("create")
    public String create(@RequestParam String username, Model model){
        model.addAttribute("usuario", usuarioService.get(username));

        return "personaAsociada/create";
    }

    @RequestMapping("list")
    public String list(Model model, @RequestParam String username, RedirectAttributes redirectAttributes) {
        List<PersonaAsociada> personas = personaAsociadaService.findAll(username);

        if(personas.size() == 1){
            redirectAttributes.addAttribute("cuit", personas.get(0).getPersona().getIdPersona());
            return "redirect:/webapp/ddjj/declaracionJurada/byPersona";
        }
        else {
            model.addAttribute("personasAsociadas", personaAsociadaService.findAll(username));
            return "personaAsociada/list";
        }
    }


    @ModelAttribute("personaAsociada")
    public PersonaAsociada getPersonaAsociada(){
        return new PersonaAsociada();
    }


}
