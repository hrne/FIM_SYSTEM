package com.modle.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {

	//@RequestMapping(value = { "/index.html", "/index", "/" }, method = RequestMethod.GET)
	public String homepage(Model model) {
		return "demo/index";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboard(Model model) {
		return "demo/dashboard";
	}

	@RequestMapping(value = "/blank", method = RequestMethod.GET)
	public String blank(Model model) {
		return "demo/blank";
	}

	@RequestMapping(value = "/buttons", method = RequestMethod.GET)
	public String buttons(Model model) {
		return "demo/buttons";
	}

	@RequestMapping(value = "/flot", method = RequestMethod.GET)
	public String flot(Model model) {
		return "demo/flot";
	}

	@RequestMapping(value = "/forms", method = RequestMethod.GET)
	public String forms(Model model) {
		return "demo/forms";
	}

	@RequestMapping(value = "/grid", method = RequestMethod.GET)
	public String grid(Model model) {
		return "demo/grid";
	}

	@RequestMapping(value = "/icons", method = RequestMethod.GET)
	public String icons(Model model) {
		return "demo/icons";
	}

	@RequestMapping(value = "/morris", method = RequestMethod.GET)
	public String morris(Model model) {
		return "demo/morris";
	}

	@RequestMapping(value = "/notifications", method = RequestMethod.GET)
	public String notifications(Model model) {
		return "demo/notifications";
	}

	@RequestMapping(value = "/panels-wells", method = RequestMethod.GET)
	public String panelsWells(Model model) {
		return "demo/panels-wells";
	}

	@RequestMapping(value = "/tables", method = RequestMethod.GET)
	public String tables(Model model) {
		return "demo/tables";
	}

	@RequestMapping(value = "/typography", method = RequestMethod.GET)
	public String typography(Model model) {
		return "demo/typography";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "demo/login";
	}

}