package com.eBolivar.dao.interfaces;

import com.eBolivar.domain.LoginTicketResponse;

/**
 * Created by Fede Beron on 30/3/2017.
 */
public interface ICredencialAutenticacionAFIPRepository {

    LoginTicketResponse get();

    LoginTicketResponse save(LoginTicketResponse loginTicketResponse);
}
