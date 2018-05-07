package com.eBolivar.domain;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "loginTicketResponse")
@Entity
@Table(name="CREDENCIAL_AUTENTICACION_AFIP")
public class LoginTicketResponse implements java.io.Serializable {


    @XmlElement(name = "uniqueId")
    @Id
    @Column(name="uniqueId")
    private String uniqueId;
    @XmlElement(name="token")
    @Column(name="token")
    private String token;
    @XmlElement(name="sign")
    @Column(name = "sign")
    private String sign;
    @XmlElement(name="generationTime")
    @Column(name = "fechaDePedido")
    private Date generationTime;
    @XmlElement(name="expirationTime")
    @Column(name = "fechaDeCaducacion")
    private Date expirationTime;

    final static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public LoginTicketResponse() {
    }

    public LoginTicketResponse(String uniqueId, String token, String sign, String generationTime, String expirationTime) throws ParseException {
        this.uniqueId = uniqueId;
        this.token = token;
        this.sign = sign;
        this.generationTime = sf.parse(generationTime);
        this.expirationTime = sf.parse(expirationTime);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Date getGenerationTime() {
        return generationTime;
    }

    public void setGenerationTime(Date generationTime) {
        this.generationTime = generationTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }
}
