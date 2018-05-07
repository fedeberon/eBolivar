package com.eBolivar.web.rentas;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Fede Beron on 5/4/2017.
 */
@Controller
@RequestMapping(value = "/rentas")
public class RentasController {

    @RequestMapping(value = "/menu")
    public String menu(){
        return "menu/menu-rentas";
    }

}
