/** @author Fede Beron * @version 1.0 */ 
package com.eBolivar.web.impuesto;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.eBolivar.domain.Documento;
import com.eBolivar.service.ImpuestoServiceImpl;



public class ImpuestoUploadFormController extends SimpleFormController {

	private ImpuestoServiceImpl impuestoService;
	
	public ImpuestoUploadFormController() {
		setCommandName("documento");
		setCommandClass(Documento.class);
		setSupportedMethods(new String[] { METHOD_GET, METHOD_POST });
	}

	public void setImpuestoService(ImpuestoServiceImpl impuestoService) {
		this.impuestoService = impuestoService;
	}


	@Override
	protected ModelAndView onSubmit(HttpServletRequest req, HttpServletResponse res, Object command, BindException errors) {
		Documento doc = (Documento) command;
		

		try {
			res.setContentType("text/plain");
			if (!(req instanceof MultipartHttpServletRequest)) {
				res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Expected multipart request");
				return null;
			}

			if (doc.getFile().isEmpty()) {
				return new ModelAndView("redirect:actualizaciones");
			}

			File file = impuestoService.saveFile(doc);
			impuestoService.readAndSaveImpuestos(file);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		ModelAndView mav = new ModelAndView("redirect:actualizaciones");
		return mav;
	}
     

	@Override
	protected Map referenceData(HttpServletRequest req) throws Exception {
		Map  data = new HashMap();
//		data.put("tiposDocumentos", tipoDocumentoService.getByModulo("TX"));
		return data;
	}
}
