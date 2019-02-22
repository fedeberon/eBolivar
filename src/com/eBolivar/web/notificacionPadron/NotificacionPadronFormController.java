package com.eBolivar.web.notificacionPadron;

import com.eBolivar.common.SearchObject;
import com.eBolivar.domain.NotificacionPadron;
import com.eBolivar.service.NotificacionPadronServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;
import com.eBolivar.service.notificacionPadron.interfaces.INotificacionService;
import com.eBolivar.validator.NotificacionPadronValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
@RequestMapping("/notificacionPadron")
public class NotificacionPadronFormController{

    @Autowired
    private NotificacionPadronServiceImpl notificacionPadronService;

    @Autowired
    private INotificacionService notificacionService;

    @Autowired
    private TipoImpuestoServiceImpl tipoImpuestoService;


    @Autowired
    private NotificacionPadronValidator validator;

    @RequestMapping("create")
    public String create(Model model) {
        model.addAttribute("tipos", this.tipoImpuestoService.getObjects());

        return "notificacionPadron/create";
    }



    @RequestMapping("list")
    public String list(@RequestParam(defaultValue = "", required = false) String value, @RequestParam(defaultValue = "1", required = false) Integer page, Model model) {
        model.addAttribute("notificacionPadrons", notificacionService.find(value, page));
        model.addAttribute("value", value);
        model.addAttribute("page", page);

        return "notificacionPadron/list";
    }



    @RequestMapping("save")
    protected String save(@ModelAttribute NotificacionPadron notificacionPadron, BindingResult result){
        this.validator.validate(notificacionPadron, result);
        if (result.hasErrors()) {
            return "notificacionPadron/create";
        }
        notificacionPadron.setFechaAlta(new Date());
        this.notificacionPadronService.saveObject(notificacionPadron);

        return "redirect:notificacionPadron/create";
    }


        @ModelAttribute("notificacionPadron")
        public NotificacionPadron getNotificacionPadron(){

            return  new NotificacionPadron();
        }

        @ModelAttribute("searchObject")
            public SearchObject getSearchObject(){

            return  new SearchObject();
    }
}

