package com.eBolivar.config;

import com.eBolivar.enumeradores.PeriodoEnum;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Created by Fede Beron on 12/7/2017.
 */
@WebListener
public class ConfigServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("periodoEnum", PeriodoEnum.values());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
