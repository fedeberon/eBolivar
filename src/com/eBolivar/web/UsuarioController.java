package com.eBolivar.web;

import com.eBolivar.domain.Localidad;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.domain.usuario.UsuarioLocalidad;
import com.eBolivar.service.localidad.ILocalidadService;
import com.eBolivar.service.usuario.UsuarioService;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ILocalidadService localidadService;


    @RequestMapping("administrador/buscar")
    public String findAdministradorCuenta(@RequestParam String valor, @RequestParam(defaultValue = "1", required = false) Integer page, Model model) {
        model.addAttribute("usuarios", usuarioService.findAdministradorCuenta(valor, page));
        model.addAttribute("page", page);
        model.addAttribute("valor", valor);

        return "usuario/list-administradorCuenta";
    }

    @RequestMapping("usuario/buscar")
    public String findUsuarios(@RequestParam String valor, @RequestParam(defaultValue = "1", required = false) Integer page, Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        model.addAttribute("page", page);
        model.addAttribute("valor", valor);

        return "usuario/list-administradorCuenta";
    }

    @RequestMapping("create")
    public String create() {
        return "usuario/create";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);

        return "redirect:list";
    }

    @RequestMapping(value = "saveAdministradorCuenta", method = RequestMethod.GET)
    public String saveAdministradorCuenta(@RequestParam String username) {
        usuarioService.updateLikeAdministradorDeCuenta(username);

        return "redirect:list/administradorDeCuenta";
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());

        return "usuario/list";
    }

    @RequestMapping("list/administradorDeCuenta")
    public String listAdministradorDeCuenta(@RequestParam(defaultValue = "1", required = false) Integer page, @RequestParam(defaultValue = "", required = false) String valor, Model model) {
        model.addAttribute("usuarios", usuarioService.findAdministradorCuenta(valor, page));
        model.addAttribute("page", page);

        return "usuario/list-administradorCuenta";
    }

    @ModelAttribute("usuario")
    public Usuario getUsuario() {
        return new Usuario();
    }


    @ModelAttribute("usuarioLocalidad")
    public UsuarioLocalidad getUsuarioLocalidad() {
        return new UsuarioLocalidad();
    }


    @ModelAttribute("localidades")
    public List<Localidad> getLocalidades() {
        return localidadService.findAll();
    }

    @RequestMapping(value = "saveLocation", method = RequestMethod.POST)
    public String save(@ModelAttribute UsuarioLocalidad usuarioLocalidad, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", usuarioLocalidad.getUsuario().getUsername());
        usuarioService.save(usuarioLocalidad);

        return "redirect:list";
    }

    @RequestMapping("add-location")
    public String addLocation(@RequestParam String username, Model model) {
        model.addAttribute("usuario", usuarioService.get(username));

        return "personaAsociada/add-location";
    }


}
