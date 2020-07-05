package com.vasut.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

@Controller
public class ErrorPageController implements ErrorController {

	private static final String ERR_PATH = "/error";
	private ErrorAttributes errorAttributes;

	// private final Logger log=LoggerFactory.getLogger(this.getClass());

	@Autowired
	public void setErrorAttributes(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@RequestMapping(ERR_PATH)
	public String error(Model model, HttpServletRequest request) {

		ServletWebRequest rA = new ServletWebRequest(request);
		Map<String, Object> error = this.errorAttributes.getErrorAttributes(rA, true);

		model.addAttribute("timestamp", error.get("timestamp"));
		model.addAttribute("error", error.get("error"));
		model.addAttribute("message", error.get("message"));
		model.addAttribute("path", error.get("path"));
		model.addAttribute("status", error.get("status"));

		String errCode = error.get("status").toString();

		if (errCode.equals("404")) {
			return "errors/404";
		}
		if (errCode.equals("500")) {
			return "errors/500";
		}
		
		return "detaildError";
	}

	@Override
	public String getErrorPath() {
		return ERR_PATH;
	}

}
