package com.eBolivar.bean;

import org.springframework.stereotype.Component;

import javax.mail.internet.InternetAddress;
import java.util.Map;

/**
 * Created by Damian Gallego on 19/3/2019.
 */
@Component
public class Mail {

    private String[] to;
    private InternetAddress[] toAll;
    private String from;
    private String subject;
    private String text;
    private Map<String, Object > model;

    public static String DELEGACION_URDAMPILLETA = "municipalidadurdampilleta@gmail.com";
    public static String DELEGACION_PIROVANO= "delegacionpirovano2013@gmail.com";
    public static String LOCALIDAD_BOLIVAR = "rentas.bolivar@gmail.com";

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String [] to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Map<String, Object> getModel() {
        return model;
    }

    public void setModel(Map<String, Object> model) {
        this.model = model;
    }


    public InternetAddress[] getToAll() {
        return toAll;
    }

    public void setToAll(InternetAddress[] toAll) {
        this.toAll = toAll;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

}
