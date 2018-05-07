package com.eBolivar.web.mail;

import com.eBolivar.bean.DatosDeContacto;
import com.eBolivar.bean.EstadoNotificacionPadron;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.service.ImpuestoServiceImpl;
import com.eBolivar.service.NotificacionPadronServiceImpl;
import com.eBolivar.service.mail.interfaces.IMailService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;

/**
 * Created by Fede Beron on 17/12/2016.
 */
@Controller
@RequestMapping("mail")
public class MailController {

    @Autowired
    ImpuestoServiceImpl impuestoService;
    @Autowired
    IMailService mailService;
    @Autowired
    NotificacionPadronServiceImpl notificacionPadronService;


    @RequestMapping(value = "/{idFactura}/{email}", method = RequestMethod.GET)
    public ResponseEntity enviarMailPorTasa(@PathVariable String idFactura, @PathVariable String email) throws MessagingException, JRException{

        try {
            Impuesto impuesto = impuestoService.getImpuestoPorNumeroDeFactura(idFactura);
            mailService.send(email, impuesto);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (MessagingException | JRException exception){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/asociarMail/{idFactura}/{email}/{padron}",  method = RequestMethod.GET)
    public ResponseEntity asociarMailPorTasa(@PathVariable String padron, @PathVariable String idFactura, @PathVariable String email){
        try {
            NotificacionPadron notificacionDePadron = new NotificacionPadron(padron, email, EstadoNotificacionPadron.ESTADO_ACTIVO, new Date(), EstadoNotificacionPadron.CONFIRMADO_SI, idFactura);
            notificacionPadronService.saveObject(notificacionDePadron);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
