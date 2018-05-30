/** @author FedeBeron * @version 1.0 */

package com.eBolivar.web.impuesto;

import com.eBolivar.domain.Impuesto;
import com.eBolivar.domain.Padron;
import com.eBolivar.service.BannerServiceImpl;
import com.eBolivar.service.TipoImpuestoServiceImpl;
import com.eBolivar.service.impuesto.interfaces.IImpuestoService;
import com.eBolivar.service.padron.interfaces.IPadronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("tasas")
public class ImpuestoController {


	@Autowired
	private IPadronService padronService;

	@Autowired
	private BannerServiceImpl bannerServiceImpl;

	@Autowired
	private TipoImpuestoServiceImpl tipoImpuestoService;

	@Autowired
	private IImpuestoService impuestoService;

	@RequestMapping("search")
	public String search(){
		return "impuesto/impuestos";
	}


	@RequestMapping("listByPadron")
	public String listByPadron(@RequestParam Integer idPadron, Model model){
		Padron padron = padronService.get(idPadron);
		List<Impuesto> impuestos  = impuestoService.getByPadron(padron.getNumero());
		model.addAttribute("impuestos", impuestos);

		return "impuesto/list";
	}

	@RequestMapping("homeWeb")
	public String homeWeb(Model model) {
		model.addAttribute("banners", bannerServiceImpl.getObjects());
		model.addAttribute("tipos", tipoImpuestoService.getObjects());

		return "impuesto/homeWeb";
	}





}
