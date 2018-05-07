package com.eBolivar.web.contacto;

import com.eBolivar.bean.DatosDeContacto;
import com.eBolivar.service.mail.interfaces.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;

/**
 * Created by Fede Beron on 19/12/2016.
 */
@Controller
@RequestMapping("contacto")
public class ContactoController {

    @Autowired
    IMailService mailService;

    @RequestMapping(value = "/nuevo")
    public String nuevoContacto(){
        return "contacto/nuevo";
    }

    @RequestMapping(value = "/guardar",  method = RequestMethod.POST)
    public String guardarContacto(@ModelAttribute DatosDeContacto datosDeContacto,  BindingResult result){
        if (result.hasErrors()) {
            return "contacto/nuevo";
        }
        else {
            try {
                mailService.send(datosDeContacto.getEmail(), "Nueva contulta desde Web de Reclamos.", crearTemplateDeFormaularioDeMail(datosDeContacto));

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return "contacto/envio-sastifactorio";
    }

    @ModelAttribute("datosDeContacto")
    public DatosDeContacto getDatosDeContacto(){
        return new DatosDeContacto();
    }

    private String crearTemplateDeFormaularioDeMail(DatosDeContacto datosDeContacto){
            String cuerpoDelMail = "";

            cuerpoDelMail = "Sr/a: " + datosDeContacto.getNombre() + " " + datosDeContacto.getApellido();

            cuerpoDelMail += "<br/>";

            cuerpoDelMail += "E-Mail de contacto: " + datosDeContacto.getEmail();

            cuerpoDelMail += "<hr>";

            cuerpoDelMail += "Comentario: " + datosDeContacto.getComentarios();

            return cuerpoDelMail;
    }

}
