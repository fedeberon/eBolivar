package com.eBolivar.dao.localidad.interfaces;

import com.eBolivar.domain.Localidad;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Lucas Cortés on 22/02/2019.
 */

public interface ILocalidadRepository {

    List<Localidad> findAll();

}
