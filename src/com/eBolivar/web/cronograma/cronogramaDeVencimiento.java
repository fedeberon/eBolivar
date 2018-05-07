package com.eBolivar.web.cronograma;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fede Beron on 11/11/2016.
 */
@Controller
@RequestMapping("/informacion")
public class cronogramaDeVencimiento {

    @RequestMapping("/cronograma")
    public String cronogramaDeVencimiento(){
        return "informacion/vencimientos";
    }


    @RequestMapping("/mediosDePago")
    public String mediosDePago(){
        return "informacion/mediosDePago";
    }
}


