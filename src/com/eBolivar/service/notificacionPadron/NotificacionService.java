package com.eBolivar.service.notificacionPadron;

import com.eBolivar.common.SearchObject;
import com.eBolivar.dao.notificacionPadron.interfaces.INotificacionPadronRepository;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.service.notificacionPadron.interfaces.INotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fede Beron on 21/02/2019.
 */
@Service
public class NotificacionService implements INotificacionService {

    @Autowired
    private INotificacionPadronRepository dao;


    @Override
    public List<NotificacionPadron> find(SearchObject searchObject){
        return dao.findAll(searchObject);
    }

    @Override
    public List<NotificacionPadron> find(String value, Integer page){
        return dao.findAll(value, page);
    }
}