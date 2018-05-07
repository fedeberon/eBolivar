package com.eBolivar.domain;

import org.springframework.stereotype.Component;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "personaReturn")
public class PersonaReturn  implements java.io.Serializable {

    private Integer id;

    @XmlElement(name="metadata")
    private Metadata metadata;

    @XmlElement(name="datosGenerales")
    private Persona persona;

    public PersonaReturn() {
    }

    public PersonaReturn(
           Metadata metadata,
           Persona persona) {
           this.metadata = metadata;
           this.persona = persona;
    }
    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PersonaReturn{" +
                "persona=" + persona +
                '}';
    }
}
