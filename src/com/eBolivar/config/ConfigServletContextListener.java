package com.eBolivar.config;

import com.eBolivar.enumeradores.PeriodoEnum;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.stream.Collectors;

/**
 * Created by Fede Beron on 12/7/2017.
 */
@WebListener
public class ConfigServletContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> !periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
