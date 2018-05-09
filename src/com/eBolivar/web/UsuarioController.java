package com.eBolivar.web;

import com.eBolivar.domain.usuario.Usuario;
import com.eBolivar.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping("create")
    public String create(@ModelAttribute Usuario usuario) {
        return "usuario/create";
    }

    @RequestMapping("save")
    public String save(@ModelAttribute Usuario usuario) {
        usuarioService.save(usuario);

        return "webapp/";
    }



    @ModelAttribute("usuario")
    public Usuario getUsuario(){
        return new Usuario();
    }

}
