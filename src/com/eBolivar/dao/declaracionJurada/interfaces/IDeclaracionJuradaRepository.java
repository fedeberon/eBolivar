package com.eBolivar.dao.declaracionJurada.interfaces;

import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.Persona;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Fede Beron on 10/7/2017.
 */
public interface IDeclaracionJuradaRepository {
    DeclaracionJurada get(Long id);

    DeclaracionJurada getUltimaDeclaracionJuradaPorPersona(Persona persona);

    DeclaracionJurada save(DeclaracionJurada declaracionJurada);

    void export(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream);

    DeclaracionJurada getUltimaDeclaracionJurada(String padron, Long cuit);

    List<DeclaracionJurada> findAllPageable(Integer page);

    List<DeclaracionJurada> find(String valor);

    List<DeclaracionJurada> getByPersona(Persona persona);

    void imprimirAcuseDeRecibo(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream);
}
