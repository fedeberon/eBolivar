package com.eBolivar.web.declaracionJurada;

import com.eBolivar.bean.AnioTypeEditor;
import com.eBolivar.domain.DeclaracionJurada;
import com.eBolivar.domain.Padron;
import com.eBolivar.domain.Persona;
import com.eBolivar.domain.Tasa;
import com.eBolivar.enumeradores.AnioEnum;
import com.eBolivar.enumeradores.EstadoDeDeclaracionJurada;
import com.eBolivar.enumeradores.PeriodoEnum;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.declaracionJurada.interfaces.IDeclaracionJuradaService;
import com.eBolivar.service.mail.interfaces.IMailService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.tasa.interfaces.ITasaService;
import com.eBolivar.validator.DeclaracionJuradaEditadaValidator;
import com.eBolivar.validator.DeclaracionJuradaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.format.DecimalStyle;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("ddjj")
public class DeclaracionJuradaController {

    @Autowired
    private ITasaService tasaService;

    @Autowired
    private IDeclaracionJuradaService declaracionJuradaService;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private DeclaracionJuradaValidator validator;

    @Autowired
    private DeclaracionJuradaEditadaValidator ddjjEditadaValidator;

    @Autowired
    private IMailService mailService;


    @Autowired
    private IPadronService padronService;


    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("tasas", tasaService.findAllAnio("2018"));
        model.addAttribute("anio", Arrays.asList("2018"));
        return "declaracionJurada/create";
    }


    @RequestMapping(value = "declaracionJuradaAnteriores", method = RequestMethod.GET)
    public String declaracionJuradaAnteriores(@RequestParam String anio, Model model) {
        model.addAttribute("tasas", tasaService.findAllAnio(anio));
        model.addAttribute("anio", Arrays.asList(anio));

        return "declaracionJurada/create";
    }


    @ModelAttribute("ddjj")
    public DeclaracionJurada getDclaracionJurada() {
        return new DeclaracionJurada();
    }

    @ModelAttribute("tasas")
    public List<Tasa> getTasas(){return tasaService.findAll();}

    @RequestMapping("save")
    public String save(@ModelAttribute("ddjj") DeclaracionJurada declaracionJurada, @RequestParam String tipo, BindingResult result, RedirectAttributes redirectAttributes) {
        declaracionJurada.getTasas().removeIf(tasaAsociada -> tasaAsociada.getTasa().getId() == Tasa.SIN_DATOS);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.EN_PROCESO);
        this.validator.validate(declaracionJurada, result);
        if (result.hasErrors()) return "declaracionJurada/" + tipo;
        DeclaracionJurada ddjj = declaracionJuradaService.save(declaracionJurada);
        redirectAttributes.addAttribute("id", ddjj.getId());
        try {
            mailService.send("rentas.bolivar@gmail.com", "Nueva DDJJ Web", "Se Cargo una nueva declaracion Jurada en la web " + declaracionJurada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:actualizarImportesCalculados";
    }

    @RequestMapping("actualizarImportesCalculados")
    public String actualizarImportesCalculados(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.MODIFICADA);
        this.recalcularValores(declaracionJurada);
        redirectAttributes.addAttribute("id", declaracionJurada.getId());

        return "redirect:show";
    }

    @RequestMapping(value = "guardarCambios", params = {"action = recalcularValores"})
    public String recalcularValoresYGuardarCambios(@ModelAttribute DeclaracionJurada ddjj, RedirectAttributes redirectAttributes) {
        ddjj.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.MODIFICADA);
        this.recalcularValores(ddjj);
        redirectAttributes.addAttribute("id", ddjj.getId());

        return "redirect:show";
    }

    private void recalcularValores(DeclaracionJurada declaracionJurada) {
        try {
            declaracionJuradaService.actualizarImportesCalculados(declaracionJurada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("show")
    public String show(@RequestParam Long id, Model model) {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        final Persona persona = personaService.get(declaracionJurada.getPersona().getId());
        declaracionJurada.setPersona(persona);
        model.addAttribute("declaracionJurada", declaracionJurada);

        return "declaracionJurada/formularioDeclaracionJurada";
    }

    @RequestMapping("editar")
    public String editar(@RequestParam Long id, Model model) {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        final Persona persona = personaService.get(declaracionJurada.getPersona().getId());
        declaracionJurada.setPersona(persona);
        model.addAttribute("ddjj", declaracionJurada);

        return "declaracionJurada/update";
    }

    @RequestMapping("getUltimaDeclaracionPorCUIT")
    public String getUltimaDeclaracionPorCUIT(@ModelAttribute("ddjj") DeclaracionJurada declaracionJurada, BindingResult result, RedirectAttributes redirectAttributes) {
        this.ddjjEditadaValidator.validate(declaracionJurada, result);
        if (result.hasErrors()) return "declaracionJurada/formularioBuscar";
        DeclaracionJurada ultimaDeclaracionJurada = declaracionJuradaService.getUltimaDeclaracionJurada(declaracionJurada.getPadron().getNumero(), declaracionJurada.getPersona().getIdPersona());
        redirectAttributes.addAttribute("id", ultimaDeclaracionJurada.getId());

        return "redirect:show";
    }

    @RequestMapping("enviar")
    public String enviar(@RequestParam Long id, Model model) {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.PRESENTADA);
        final Persona persona = personaService.get(declaracionJurada.getPersona().getId());
        declaracionJurada.setPersona(persona);
        model.addAttribute("declaracionJurada", declaracionJurada);
        try {
            mailService.send("rentas.bolivar@gmail.com", "Nueva DDJJ Web", "Se Cargo una nueva declaracion Jurada en la web: " + declaracionJurada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "declaracionJurada/formularioDeclaracionJurada";
    }

    @RequestMapping("guardarCambios")
    public String guardarCambios(@ModelAttribute DeclaracionJurada ddjj, RedirectAttributes redirectAttributes) {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(ddjj.getId());
        declaracionJurada.setPeriodo(ddjj.getPeriodo());
        declaracionJurada.setTasas(ddjj.getTasas());
        declaracionJurada.setTotalCalculado(ddjj.getTotalCalculado());
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.MODIFICADA);
        declaracionJuradaService.save(declaracionJurada);
        redirectAttributes.addAttribute("id", declaracionJurada.getId());

        return "redirect:show";
    }

    @RequestMapping("formularioBuscar")
    public String formBuscar() {
        return "declaracionJurada/formularioBuscar";
    }

    @RequestMapping("exportar")
    public String exportar(@RequestParam Long idDeclaracionJurada, Model model, HttpServletResponse response) throws IOException {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(idDeclaracionJurada);
        declaracionJuradaService.export(declaracionJurada, response.getOutputStream());
        model.addAttribute("declaracionJurada", declaracionJurada);

        return "declaracionJurada/formularioDeclaracionJurada";
    }

    @RequestMapping("list")
    public String list(@RequestParam(defaultValue = "1", required = false) Integer page, Model model) {
        model.addAttribute("ddjj", declaracionJuradaService.findAllPageable(page));
        model.addAttribute("page", page);

        return "declaracionJurada/list";

    }

    @RequestMapping("buscar")
    public String list(@RequestParam String valor, Model model) {
        model.addAttribute("ddjj", declaracionJuradaService.find(valor));

        return "declaracionJurada/list";

    }

    @RequestMapping("aceptarDeclaracionJurada")
    public String aceptarDeclaracionJurada(@RequestParam Long id, RedirectAttributes redirectAttributes){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.ACEPTADA);
        redirectAttributes.addAttribute("id" , declaracionJurada.getId());
        declaracionJuradaService.save(declaracionJurada);

        return "redirect:show";
    }

    @RequestMapping("presentarDeclaracionJurada")
    public String presentarDeclaracionJurada(@RequestParam Long id, RedirectAttributes redirectAttributes){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.PRESENTADA);
        redirectAttributes.addAttribute("id" , declaracionJurada.getId());
        declaracionJuradaService.save(declaracionJurada);
        try {
            mailService.send("rentas.bolivar@gmail.com", "Nueva DDJJ Web", "Se Presentó la declaracion Jurada: " + declaracionJurada);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:show";
    }

    @RequestMapping("rechazarDeclaracionJurada")
    public String rechazarDeclaracionJurada(@RequestParam Long id, RedirectAttributes redirectAttributes){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.RECHAZADA);
        redirectAttributes.addAttribute("id" , declaracionJurada.getId());
        declaracionJuradaService.save(declaracionJurada);

        return "redirect:show";
    }

    @RequestMapping("imprimirAcuseDeRecibo")
    public String imprimirAcuseDeRecibo(@RequestParam Long id, HttpServletResponse response,  RedirectAttributes redirectAttributes) throws IOException {
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        redirectAttributes.addAttribute("id" , declaracionJurada.getId());
        declaracionJuradaService.imprimirAcuseDeRecibo(declaracionJurada, response.getOutputStream());

        return "redirect:show";
    }

    @RequestMapping("declaracionJurada/byPersona")
    public String getDeclaracionesJuradas(Model model , @RequestParam String cuit){
        Persona persona = personaService.getByCUIT(cuit);
        model.addAttribute("ddjjs", declaracionJuradaService.getByPersona(persona));
        model.addAttribute("persona", persona);

        return "declaracionJurada/list";
    }


    @RequestMapping("declaracionJurada/create/byPersona")
    public String createByPersonaAsociada(Model model , @RequestParam String idPersona, @RequestParam AnioEnum anio, @RequestParam Integer idPadron, RedirectAttributes redirectAttributes){
        if(idPersona == null || idPersona.isEmpty()){
            redirectAttributes.addAttribute("anio", anio.getDescripcion());
            return "redirect:/webapp/ddjj/declaracionJuradaAnteriores";
        }
        Persona persona = personaService.getByCUIT(idPersona);
        Padron padron = padronService.get(idPadron);
        DeclaracionJurada declaracionJurada = new DeclaracionJurada(persona, anio, padron);
        model.addAttribute("declaracionJurada", declaracionJurada);
        model.addAttribute("periodoEnum", PeriodoEnum.values());

        return "declaracionJurada/create-por-personaAsociada";
    }


    @RequestMapping("declaracionJurada/bimestral")
    public String createBimestral(Model model , @RequestParam Long idDDJJ){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(idDDJJ);
        DeclaracionJurada declaracionJuradaACrear = new DeclaracionJurada(declaracionJurada.getPersona(), AnioEnum.A_2018, declaracionJurada.getPadron());
        model.addAttribute("declaracionJurada", declaracionJuradaACrear);
        model.addAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> !periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));

        return "declaracionJurada/create-por-personaAsociada";
    }

    @RequestMapping("declaracionJurada/anual")
    public String createAnual(Model model , @RequestParam Long idDDJJ){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(idDDJJ);
        DeclaracionJurada declaracionJuradaACrear = new DeclaracionJurada(declaracionJurada.getPersona(), AnioEnum.A_2018, declaracionJurada.getPadron(), PeriodoEnum.ANUAL);
        model.addAttribute("declaracionJurada", declaracionJuradaACrear);
        model.addAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));

        return "declaracionJurada/create-por-personaAsociada";
    }


    @InitBinder("anio")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(AnioEnum.class, new AnioTypeEditor());
    }



}
