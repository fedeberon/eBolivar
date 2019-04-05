package com.eBolivar.web.declaracionJurada;

import com.eBolivar.bean.AnioTypeEditor;
import com.eBolivar.bean.FormatoUtil;
import com.eBolivar.bean.Mail;
import com.eBolivar.domain.*;
import com.eBolivar.domain.administradorCuenta.PersonaAsociada;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.domain.usuario.UsuarioLocalidad;
import com.eBolivar.enumeradores.AnioEnum;
import com.eBolivar.enumeradores.EstadoDeDeclaracionJurada;
import com.eBolivar.enumeradores.PeriodoEnum;
import com.eBolivar.service.cuitPorTasa.interfaces.ICuitPorTasaService;
import com.eBolivar.service.declaracionJurada.DeclaracionJuradaService;
import com.eBolivar.service.declaracionJurada.interfaces.IDeclaracionJuradaService;
import com.eBolivar.service.localidad.LocalidadService;
import com.eBolivar.service.mail.interfaces.IMailService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.personaAsociada.PersonaAsociadaService;
import com.eBolivar.service.tasa.TasaService;
import com.eBolivar.service.tasa.interfaces.ITasaService;
import com.eBolivar.service.tasaPersonaPadron.interfaces.ITasaPersonaPadronService;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import com.eBolivar.validator.DeclaracionJuradaEditadaValidator;
import com.eBolivar.validator.DeclaracionJuradaValidator;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.SimpleMessage;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.ldap.PagedResultsResponseControl;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private ICuitPorTasaService cuitPorTasaService;

    @Autowired
    private ITasaPersonaPadronService tasaPersonaPadronService;

    @Autowired
    private IUsuarioService usuarioService;


    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("tasas", tasaService.findAllAnio("2019"));
        model.addAttribute("anio", Arrays.asList(AnioEnum.A_2019));

        return "declaracionJurada/create";
    }

    @RequestMapping(value = "declaracionJuradaAnteriores", method = RequestMethod.GET)
    public String declaracionJuradaAnteriores(@RequestParam String anio, Model model) {
        model.addAttribute("tasas", tasaService.findAllAnio(anio));
        model.addAttribute("anio", Arrays.asList(getAnio(anio)));
        model.addAttribute("periodoEnum", PeriodoEnum.values());

        return "declaracionJurada/create";
    }


    private AnioEnum getAnio(String anio){
        switch (anio){
            case "2016":
                return AnioEnum.A_2016;
            case "2017":
                return AnioEnum.A_2017;
            case "2018":
                return AnioEnum.A_2018;
            default:
                return AnioEnum.A_2019;
        }
    }

    @RequestMapping(value = "anual", method = RequestMethod.GET)
    public String anual(Model model){
        model.addAttribute("tasas", tasaService.findAllAnio("2019"));
        model.addAttribute("anio", Arrays.asList(AnioEnum.A_2019));
        return "declaracionJurada/anual";
    }

    @ModelAttribute("ddjj")
    public DeclaracionJurada getDDJJ() {
        return new DeclaracionJurada();
    }

    @ModelAttribute("tasas")
    public List<Tasa> getTasas(){return tasaService.findAll();}

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@ModelAttribute("ddjj") DeclaracionJurada declaracionJurada, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        declaracionJurada.getTasas().removeIf(tasaAsociada -> tasaAsociada.getTasa().getId() == Tasa.SIN_DATOS);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.EN_PROCESO);
        this.validator.validate(declaracionJurada, result);
        if (result.hasErrors()) {
            model.addAttribute("anio", Arrays.asList(AnioEnum.A_2019));
            return "declaracionJurada/create";
        }

        User presentadaPor = usuarioService.getAutenticate();
        declaracionJurada.setFecha(LocalDateTime.now());
        declaracionJurada.setPresentadaPor(presentadaPor);

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
        declaracionJurada.getTasas().forEach(tasaAsociada -> tasaAsociada.setBaseImponibleView(FormatoUtil.convertirBaseImponibleNotacion(tasaAsociada.getBaseImponible())));
        declaracionJurada.setPersona(persona);
        model.addAttribute("declaracionJurada", declaracionJurada);
        model.addAttribute("acuseIsPrintable" , declaracionJuradaService.isBeforeOneDaysAgo(declaracionJurada));
        model.addAttribute("acuseAvailableDate" , declaracionJuradaService.getDateFromDeclaracionJurada(declaracionJurada));
        model.addAttribute("currentDate" , declaracionJuradaService.checkCurrentDay(declaracionJurada));

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
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=Declaracion Jurada P-"+declaracionJurada.getPadron().getNumero()+".pdf");
        declaracionJuradaService.export(declaracionJurada, response.getOutputStream());
        model.addAttribute("declaracionJurada", declaracionJurada);

        return "declaracionJurada/formularioDeclaracionJurada";
    }

    @RequestMapping("list")
    public String list() {
        return "redirect:buscar";
    }

    @RequestMapping("buscar")
    public String list(@RequestParam(defaultValue = "", required = false) String valor, @RequestParam(defaultValue = "1", required = false) Integer page, Model model) {
        model.addAttribute("ddjjs", declaracionJuradaService.findAllPageable(valor, page));
        model.addAttribute("valor", valor);
        model.addAttribute("page", page);

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

    @RequestMapping(value = "presentarDeclaracionJurada")
    public String presentarDeclaracionJurada(@RequestParam Long id, RedirectAttributes redirectAttributes){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(id);
        declaracionJurada.setEstadoDeDeclaracionJurada(EstadoDeDeclaracionJurada.PRESENTADA);
        redirectAttributes.addAttribute("id" , declaracionJurada.getId());
        declaracionJuradaService.save(declaracionJurada);

        try {
            Mail mail = new Mail();
            mail.setFrom("rentas.bolivar@gmail.com");
            Long localidad = declaracionJurada.getPadron().getLocalidad().getId();
            switch (Integer.parseInt(String.valueOf(localidad))){
                case 3:
                    String[] delegacionUrdampilleta = {Mail.DELEGACION_URDAMPILLETA, Mail.LOCALIDAD_BOLIVAR};
                    mail.setTo(delegacionUrdampilleta);
                    break;
                case 15:
                    String[] delegacionPirovano = {Mail.DELEGACION_PIROVANO, Mail.LOCALIDAD_BOLIVAR};
                    mail.setTo(delegacionPirovano);
                    break;
                default:
                    String[] delegacionBolivar = {Mail.LOCALIDAD_BOLIVAR};
                    mail.setTo(delegacionBolivar);
            }
            mail.setSubject("Nueva DDJJ cargada.");
            Map < String, Object > model = new HashMap();
            model.put("id", declaracionJurada.getId());
            model.put("padron", declaracionJurada.getPadron().getNumero());
            model.put("localidad", declaracionJurada.getPadron().getLocalidad().getNombre());
            mail.setModel(model);
            mailService.send(mail);

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
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=Acuse de Recibo.pdf");
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

    @RequestMapping("declaracionJurada/byPadronAsociado")
    public String getByPadronAsociado(Model model , @RequestParam Integer idPadronAsociado, @RequestParam(defaultValue = "1", required = false) Integer page){
        PadronAsociado padronAsociado = cuitPorTasaService.get(idPadronAsociado);
        model.addAttribute("ddjjs", declaracionJuradaService.getByPadronAsociado(padronAsociado, page));
        model.addAttribute("padronAsociado" , padronAsociado);
        model.addAttribute("page", page);

        return "declaracionJurada/list";
    }

    @RequestMapping("declaracionJurada/create/byPersona") //Metodo en ddjj/list (2016, 2017)
    public String createByPersonaAsociada(Model model , @RequestParam String idPersona, @RequestParam AnioEnum anio, @RequestParam Integer idPadron,  RedirectAttributes redirectAttributes){
        if(idPersona == null || idPersona.isEmpty()){
            redirectAttributes.addAttribute("anio", anio.getDescripcion());
            return "redirect:/webapp/ddjj/declaracionJuradaAnteriores";
        }
        Persona persona = personaService.getByCUIT(idPersona);
        Padron padron = padronService.get(idPadron);
        DeclaracionJurada declaracionJurada = new DeclaracionJurada(persona, anio, padron);
        model.addAttribute("ddjj", declaracionJurada);
        model.addAttribute("periodoEnum", PeriodoEnum.values());
        List<TasaPersonaPadron> tasaPersonaPadron = tasaPersonaPadronService.findByPersonaPadron(persona, padron);
        model.addAttribute("tasas", tasaService.findByTasaPersonaPadron(tasaPersonaPadron, anio));

        return "declaracionJurada/create-por-personaAsociada";
    }

    @RequestMapping("declaracionJurada/bimestral") //Metodo en ddjj/list
    public String createBimestral(Model model , @RequestParam Long idDDJJ){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(idDDJJ);
        DeclaracionJurada declaracionJuradaACrear = new DeclaracionJurada(declaracionJurada.getPersona(), AnioEnum.A_2019, declaracionJurada.getPadron());
        model.addAttribute("ddjj", declaracionJuradaACrear);
        model.addAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> !periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));
        List<TasaPersonaPadron> tasaPersonaPadron = tasaPersonaPadronService.findByPersonaPadron(declaracionJurada.getPersona(), declaracionJurada.getPadron());
        List<Tasa> tasas = tasaService.findByTasaPersonaPadron(tasaPersonaPadron, AnioEnum.A_2019);
        model.addAttribute("tasas", tasas);

        return "declaracionJurada/create-por-personaAsociada";
    }

    @RequestMapping("declaracionJurada/anual") //Metodo en ddjj/list
    public String createAnual(Model model , @RequestParam Long idDDJJ){
        DeclaracionJurada declaracionJurada = declaracionJuradaService.get(idDDJJ);
        DeclaracionJurada declaracionJuradaACrear = new DeclaracionJurada(declaracionJurada.getPersona(), AnioEnum.A_2019, declaracionJurada.getPadron(), PeriodoEnum.ANUAL);
        model.addAttribute("ddjj", declaracionJuradaACrear);
        model.addAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));
        List<TasaPersonaPadron> tasaPersonaPadron = tasaPersonaPadronService.findByPersonaPadron(declaracionJurada.getPersona(), declaracionJurada.getPadron());
        List<Tasa> tasas = tasaService.findByTasaPersonaPadron(tasaPersonaPadron, AnioEnum.A_2019);
        model.addAttribute("tasas", tasas);

        return "declaracionJurada/create-por-personaAsociada";
    }

    /**
     * Este Metodo crea ddjj bimestral en lista de padrones
     * @param idPadron
     * @param idPersona
     * @param model
     * @return
     */
    @RequestMapping("declaracionJurada/bimestralByPadronAsociado")
    public String createBimestralByPadronAsociado(@RequestParam Integer idPadron, @RequestParam Integer idPersona, Model model){
        Persona persona = personaService.get(idPersona);
        Padron padron = padronService.get(idPadron);
        DeclaracionJurada declaracionJuradaACrear = new DeclaracionJurada(persona, AnioEnum.A_2019, padron);
        model.addAttribute("ddjj", declaracionJuradaACrear);
        model.addAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> !periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));
        List<TasaPersonaPadron> tasaPersonaPadron = tasaPersonaPadronService.findByPersonaPadron(persona, padron);
        List<Tasa> tasas = tasaService.findByTasaPersonaPadron(tasaPersonaPadron, AnioEnum.A_2019);
        model.addAttribute("tasas", tasas);

        return "declaracionJurada/create-por-personaAsociada";
    }

    /**
     * Este Metodo crea ddjj anual en lista de padrones
     * @param idPadron
     * @param idPersona
     * @param model
     * @return String a vista
     */
    @RequestMapping("declaracionJurada/anualByPadronAsociado")
    public String createAnualByPadronAsociado(@RequestParam Integer idPadron, @RequestParam Integer idPersona, Model model){
        Persona persona = personaService.get(idPersona);
        Padron padron = padronService.get(idPadron);
        DeclaracionJurada declaracionJuradaACrear = new DeclaracionJurada(persona, AnioEnum.A_2019, padron);
        model.addAttribute("ddjj", declaracionJuradaACrear);
        model.addAttribute("periodoEnum", PeriodoEnum.stream().filter(periodo -> periodo.getDescripcion().equalsIgnoreCase(PeriodoEnum.ANUAL.getDescripcion())).collect(Collectors.toList()));
        List<TasaPersonaPadron> tasaPersonaPadron = tasaPersonaPadronService.findByPersonaPadron(persona, padron);
        List<Tasa> tasas = tasaService.findByTasaPersonaPadron(tasaPersonaPadron, AnioEnum.A_2019);
        model.addAttribute("tasas", tasas);

        return "declaracionJurada/create-por-personaAsociada";
    }

    @RequestMapping("declaracionJurada/anteriorByPadronAsociado") //Metodo en ddjj/list (2016, 2017)
    public String createAnteriorByPadronAsociado(Model model , @RequestParam Integer idPersona, @RequestParam AnioEnum anio, @RequestParam Integer idPadron,  RedirectAttributes redirectAttributes){
        Persona persona = personaService.get(idPersona);
        Padron padron = padronService.get(idPadron);
        DeclaracionJurada declaracionJurada = new DeclaracionJurada(persona, anio, padron);
        model.addAttribute("ddjj", declaracionJurada);
        model.addAttribute("periodoEnum", PeriodoEnum.values());
        List<TasaPersonaPadron> tasaPersonaPadron = tasaPersonaPadronService.findByPersonaPadron(persona, padron);
        model.addAttribute("tasas", tasaService.findByTasaPersonaPadron(tasaPersonaPadron, anio));

        return "declaracionJurada/create-por-personaAsociada";
    }

    @InitBinder("anio")
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(AnioEnum.class, new AnioTypeEditor());
    }

}
