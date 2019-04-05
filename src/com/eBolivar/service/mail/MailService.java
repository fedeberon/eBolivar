package com.eBolivar.service.mail;


import com.eBolivar.bean.Mail;
import com.eBolivar.domain.Impuesto;
import com.eBolivar.service.mail.interfaces.IMailService;
import com.eBolivar.service.reporte.ReporteService;
import net.sf.jasperreports.engine.JRException;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Map;

/**
 * Created by Fede Beron on 17/12/2016.
 */
@Service
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender sender;

    @Autowired
    ReporteService reporteService;

    @Autowired
    VelocityEngine velocityEngine;

    @Override
    public void send(String to, String subject, String text) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("rentas.bolivar@gmail.com");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        sender.send(message);
    }

    @Override
    public void send(Mail mail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom("rentas.bolivar@gmail.com");
//        helper.setTo(mail.getToAll());
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(geContentFromTemplate(mail.getModel()), true);

        sender.send(message);
    }

    @Override
    public void send(String to, String subject, String text, File... attachments) throws MessagingException {
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        if(attachments != null){
            for(File file : attachments){
                FileSystemResource res = new FileSystemResource(file);
                helper.addInline("identifier1234", res);
            }
        }
        sender.send(message);
    }

    @Override
    public void send(String email, Impuesto impuesto) throws MessagingException, JRException {
        MimeMessage mimeMessage = this.sender.createMimeMessage();
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mimeMessage.setFrom(new InternetAddress("rentas.bolivar@gmail.com"));
        mimeMessage.setFileName(impuesto.getTipoImpuesto().getNombre() + ".pdf");
        mimeMessage.setSubject("Tasa Municipal de Bolivar");
        mimeMessage.setDataHandler(reporteService.attachmentFile(impuesto));

        sender.send(mimeMessage);
    }
    public String geContentFromTemplate(Map<String, Object> model) throws ResourceNotFoundException {
        StringBuffer content = new StringBuffer();

        try {
            content.append(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/com/eBolivar/templates/notificacionDeMonitoreo.vm","UTF-8", model));
            velocityEngine.init();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content.toString();
    }

}
