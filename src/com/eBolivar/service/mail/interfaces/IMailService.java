package com.eBolivar.service.mail.interfaces;

import com.eBolivar.domain.Impuesto;
import net.sf.jasperreports.engine.JRException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by Fede Beron on 17/12/2016.
 */
public interface IMailService {

    void send(String to, String subject, String text) throws MessagingException;

    void send(String to, String subject, String text, File... attachments) throws MessagingException;

    void send(String email, Impuesto impuesto) throws MessagingException, JRException;
}
