package com.eBolivar.dao.notificacionPadron.interfaces;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.NotificacionPadron;

import java.util.List;

/**
 * Created by Fede Beron on 21/02/2019.
 */
public interface INotificacionPadronRepository {
    List<NotificacionPadron> findAll(SearchObject searchObject);

    List<NotificacionPadron> findAll(String value, Integer page);
}
