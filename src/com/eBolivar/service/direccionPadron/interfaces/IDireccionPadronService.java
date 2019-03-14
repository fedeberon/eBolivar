package com.eBolivar.service.direccionPadron.interfaces;

import com.eBolivar.domain.DireccionPadron;

import java.util.List;

/**
 * Created by erwin on 13/3/2019.
 */
public interface IDireccionPadronService {

    List<DireccionPadron> getByNumeroDePadron(String padron);
}
