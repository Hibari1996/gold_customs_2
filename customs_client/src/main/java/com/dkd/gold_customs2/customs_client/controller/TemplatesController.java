package com.dkd.gold_customs2.customs_client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 跳转控制器
 *
 * @author Harry
 * @date 2018/5/29
 */
@Controller
public class TemplatesController {

	@RequestMapping(value = "/")
	public ModelAndView indexPage() {
		return new ModelAndView("home");
	}

	@RequestMapping("/nonprotect")
	public ModelAndView nonprotect(HttpServletRequest request){
		return new ModelAndView("nonprotect/nonprotect");
	}

	@RequestMapping(value = "/areaAssociate")
	public ModelAndView areaAssociatePage() {
		return new ModelAndView("nonprotect/areaAssociate");
	}

	@RequestMapping(value = "/areaAssociateRy")
	public ModelAndView areaAssociateRyatePage() {
		return new ModelAndView("nonprotect/areaAssociateRy");
	}

	@RequestMapping(value = "/areaMaintenance")
	public ModelAndView areaMaintenancePage() {
		return new ModelAndView("nonprotect/areaMaintenance");
	}
}
