package com.eBolivar.web.impuesto;

import com.eBolivar.domain.Padron;
import com.eBolivar.service.BannerServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping({"tasas"})
public class ImpuestoController {
    @Autowired
    private IPadronService padronService;
    @Autowired
    private BannerServiceImpl bannerServiceImpl;
    @Autowired
    private TipoImpuestoServiceImpl tipoImpuestoService;
    @Autowired
    private IImpuestoService impuestoService;

    public ImpuestoController() {
    }

    @RequestMapping({"search"})
    public String search() {
        return "impuesto/impuestos";
    }

    @RequestMapping({"listByPadron"})
    public String listByPadron(@RequestParam Integer idPadron, Model model) {
        Padron padron = this.padronService.get(idPadron);
        List impuestos = this.impuestoService.getByPadron(padron.getNumero());
        model.addAttribute("impuestos", impuestos);
        return "impuesto/list";
    }

    @RequestMapping({"homeWeb"})
    public String homeWeb(Model model) {
        model.addAttribute("banners", this.bannerServiceImpl.getObjects());
        model.addAttribute("tipos", this.tipoImpuestoService.getObjects());
        return "impuesto/homeWeb";
    }

    @RequestMapping({"impuestos"})
    public String impuestos() {
        return "impuesto/impuestos";
    }
}