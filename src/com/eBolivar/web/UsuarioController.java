package com.eBolivar.web;

import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.service.usuario.UsuarioService;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping("buscar")
    public String findAdministradorDeCuenta(@RequestParam String valor, @RequestParam(defaultValue = "1", required = false) Integer page, Model model){
        List<AdministradorCuenta> results = usuarioService.findAdministradorCuenta(valor, page);
        model.addAttribute("usuarios", results);
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

    @RequestMapping(value = "list/administradorDeCuenta", method = RequestMethod.GET)
    public String listAdministradorDeCuenta(Model model) {
        model.addAttribute("usuarios", usuarioService.findAllAdministradoresDeCuenta());

        return "usuario/list-administradorCuenta";
    }


    @ModelAttribute("usuario")
    public Usuario getUsuario(){
        return new Usuario();
    }

}
