package com.eBolivar.service.notificacionPadron.interfaces;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.NotificacionPadron;

import java.util.List;

/**
 * Created by Fede Beron on 21/02/2019.
 */
public interface INotificacionService {
    List<NotificacionPadron> find(SearchObject searchObject);

    List<NotificacionPadron> find(String value, Integer page);
}
