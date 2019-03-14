package com.eBolivar.service.declaracionJurada.interfaces;

import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.PadronAsociado;
import com.eBolivar.domain.Persona;

import javax.servlet.ServletOutputStream;
import java.util.List;

/**
 * Created by Fede Beron on 10/7/2017.
 */
public interface IDeclaracionJuradaService {
    DeclaracionJurada get(Long id);

    DeclaracionJurada getUltimaDeclaracionJuradaPorPersona(Persona persona);

    DeclaracionJurada save(DeclaracionJurada declaracionJurada);

    void actualizarImportesCalculados(DeclaracionJurada declaracionJurada);

    void export(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream);

    DeclaracionJurada getUltimaDeclaracionJurada(String padron, Long cuit);

    List<DeclaracionJurada> findAllPageable(Integer page);

    List<DeclaracionJurada> find(String valor);

    List<DeclaracionJurada> getByPersona(Persona persona);

    void imprimirAcuseDeRecibo(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream);

    List<DeclaracionJurada> getByPadronAsociado(PadronAsociado padronAsociado, Integer page);

    Boolean isBeforeOneDaysAgo(DeclaracionJurada declaracionJurada);

    String getDateFromDeclaracionJurada(DeclaracionJurada declaracionJurada);

    String checkCurrentDay(DeclaracionJurada declaracionJurada);

    List<DeclaracionJurada> findAllPageable(String valor, Integer pageNumber);

    void calcularCalculoMinimo (DeclaracionJurada declaracionJurada);
}
