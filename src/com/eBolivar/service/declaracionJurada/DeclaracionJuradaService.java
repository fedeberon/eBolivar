package com.eBolivar.service.declaracionJurada;

import com.eBolivar.bean.FormatoUtil;
import com.eBolivar.dao.declaracionJurada.interfaces.IDeclaracionJuradaRepository;
import com.eBolivar.domain.*;
import com.eBolivar.domain.administradorCuenta.AdministradorCuenta;
import com.eBolivar.domain.usuario.User;
import com.eBolivar.service.declaracionJurada.interfaces.IDeclaracionJuradaService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import com.eBolivar.service.persona.interfaces.IPersonaService;
import com.eBolivar.service.usuario.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Fede Beron on 10/7/2017.
 */
@Service
public class DeclaracionJuradaService implements IDeclaracionJuradaService{

    @Autowired
    private IDeclaracionJuradaRepository dao;

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IPadronService padronService;

    @Autowired
    private IUsuarioService usuarioService;

    @Override
    public DeclaracionJurada get(Long id){
        return dao.get(id);
    }

    @Override
    public DeclaracionJurada getUltimaDeclaracionJuradaPorPersona(Persona persona){
        return dao.getUltimaDeclaracionJuradaPorPersona(persona);
    }

    @Override
    public DeclaracionJurada save(DeclaracionJurada declaracionJurada){
        Persona persona = personaService.getByCUIT(declaracionJurada.getPersona().getIdPersona().toString());
        declaracionJurada.setPersona(persona);
        Padron padron = padronService.getByNumero(declaracionJurada.getPadron().getNumero());
        declaracionJurada.setPadron(padron);
        User presentadaPor = usuarioService.getAutenticate();
        declaracionJurada.setFecha(LocalDateTime.now());
        declaracionJurada.setPresentadaPor(presentadaPor);

        return dao.save(declaracionJurada);
    }

    @Override
    public void actualizarImportesCalculados(DeclaracionJurada declaracionJurada){
        this.descontarDeduccionesSobreBaseImponible(declaracionJurada);
        this.calcularAlicuotaSobreBaseImponible(declaracionJurada);
        this.sumarTotalAPagar(declaracionJurada);
        this.save(declaracionJurada);
    }

    private void sumarTotalAPagar(DeclaracionJurada declaracionJurada) {
        Double total = declaracionJurada.getTasas().stream().mapToDouble(tasaAsociada -> tasaAsociada.getImporteCalculadoSobreBaseImponible()).sum();
        TasaAsociada tasaAsociadaConMayorPorcentajeDeAlicuota = this.obtenerTasaAsociadaConMayorPorcentajeDeAlicuota(declaracionJurada);
        total = (total > tasaAsociadaConMayorPorcentajeDeAlicuota.getTasa().getImporte() ? total : tasaAsociadaConMayorPorcentajeDeAlicuota.getTasa().getImporte());
        if(declaracionJurada.getPadron().isCalculoMinimo()) {
            total = (total > tasaAsociadaConMayorPorcentajeDeAlicuota.getImporteCalculoMinimo() ? total : tasaAsociadaConMayorPorcentajeDeAlicuota.getImporteCalculoMinimo());
        }
        declaracionJurada.setTotalCalculado(FormatoUtil.formatearImporte(total));

    }

    private TasaAsociada obtenerTasaAsociadaConMayorPorcentajeDeAlicuota(DeclaracionJurada declaracionJurada){
        TasaAsociada tasaAsociadaDeAlicuotaACalcular = declaracionJurada.getTasas().stream().max((t1, t2)-> Float.compare(t1.getTasa().getAlicuta(), t2.getTasa().getAlicuta())).get();

        return tasaAsociadaDeAlicuotaACalcular;
    }

    @Override
    public void export(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream) {
        dao.export(declaracionJurada, outputStream);
    }

    @Override
    public DeclaracionJurada getUltimaDeclaracionJurada(String padron, Long cuit) {
        return dao.getUltimaDeclaracionJurada(padron, cuit);
    }

    @Override
    public List<DeclaracionJurada> findAllPageable(Integer page) {
        return dao.findAllPageable(page);
    }

    private void calcularAlicuotaSobreBaseImponible(DeclaracionJurada declaracionJurada){
        declaracionJurada.getTasas().forEach(tasaAsociada -> {
            Double importeCalculado = tasaAsociada.getBaseImponible() * tasaAsociada.getTasa().getAlicuta()  / 1000;
            tasaAsociada.setImporteCalculadoSobreBaseImponible(importeCalculado);
        });
    }

    public void calcularCalculoMinimo(DeclaracionJurada declaracionJurada){
        declaracionJurada.getTasas().forEach(tasaAsociada -> {
            tasaAsociada.setTotalPuestoAtencionBancaria(Double.valueOf(tasaAsociada.getPuestoAtencionBancaria() * 5500));
            tasaAsociada.setTotalPersonalContratado(Double.valueOf(tasaAsociada.getPersonalContratado() * 950));
            tasaAsociada.setTotalCajerosAutomaticos(Double.valueOf(tasaAsociada.getCajerosAutomaticos() * 30000));
            tasaAsociada.setTotalCajerosAutomaticosIndependiente(Double.valueOf(tasaAsociada.getCajerosAutomaticosIndependiente() * 30000));
            Double calculominimo = (315000 + tasaAsociada.getPuestoAtencionBancaria() + tasaAsociada.getTotalPersonalContratado() + tasaAsociada.getTotalCajerosAutomaticos() + tasaAsociada.getTotalCajerosAutomaticosIndependiente());
            tasaAsociada.setImporteCalculoMinimo(calculominimo);
        });
    }

    @Override
    public List<DeclaracionJurada> find(String valor){
        return dao.find(valor);
    }

    @Override
    public List<DeclaracionJurada> getByPersona(Persona persona) {
        return dao.getByPersona(persona);
    }

    @Override
    public void imprimirAcuseDeRecibo(DeclaracionJurada declaracionJurada, ServletOutputStream outputStream) {
        dao.imprimirAcuseDeRecibo(declaracionJurada, outputStream);
    }

    @Override
    public List<DeclaracionJurada> getByPadronAsociado(PadronAsociado padronAsociado, Integer page) {
        return dao.getByPadronAsociado(padronAsociado, page);
    }

    private void descontarDeduccionesSobreBaseImponible(DeclaracionJurada declaracionJurada){
        declaracionJurada.getTasas().forEach(o -> o.setBaseImponible(o.getBaseImponible() - o.getDeduccionArticulo90() - o.getDeduccionArticulo89()));
    }

    /**
     * Return boolen if DeclaracionJurada is before yesterday same time
     * @param declaracionJurada
     * @return
     */
    @Override
    public Boolean isBeforeOneDaysAgo(DeclaracionJurada declaracionJurada){
        return declaracionJurada.getFecha().isBefore(LocalDateTime.now().minusDays(1));
    }

    @Override
    public String getDateFromDeclaracionJurada(DeclaracionJurada declaracionJurada){
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("HH:mm");
        return declaracionJurada.getFecha().plusDays(1).format(dTF);
    }

    @Override
    public String checkCurrentDay(DeclaracionJurada declaracionJurada){
        if(declaracionJurada.getFecha().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()){
            return "ma&ntilde;ana";
        } else {
            return "hoy";
        }
    }


    @Override
    public List<DeclaracionJurada> findAllPageable(String valor, Integer pageNumber){
        return dao.findAllPageable(valor, pageNumber);
    }

}
