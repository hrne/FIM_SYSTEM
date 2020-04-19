package com.springmvc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springmvc.entity.SenDht11;
import com.springmvc.service.SenDht11Service;

@Controller
public class SenModController {

//	@Autowired
//	SenMachFormValidator senMachFormValidator;
//
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.setValidator(senMachFormValidator);
//	}

//	@Autowired
//	private SenMachService senMachService;
//
//	@Autowired
//	private SenModService senModService;

	@Autowired
	private SenDht11Service senDht11Service;

//	@Autowired
//	private ResourceBundleMessageSource messageSource;

	/**
	 * 查詢所有感應器
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/list", method = RequestMethod.GET)
	public String showAllSenMod(Model model) {
		List<SenDht11> senDht11s = senDht11Service.findAll();
		model.addAttribute("senMods", senDht11s);
		return "senMod/listSenMod";
	}

//	@RequestMapping("/senMod/ajax")
//	public ModelAndView helloAjaxTest() {
//		return new ModelAndView("ajax", "message", "Crunchify Spring MVC with Ajax and JQuery Demo..");
//	}
//
//	@RequestMapping(value = "/senMod/ajaxtest", method = RequestMethod.GET)
//	public @ResponseBody String getTime(Model model) {
//
//		Random rand = new Random();
//		float r = rand.nextFloat() * 100;
//		String result = "<br>Next Random # is <b>" + r + "</b>. Generated on <b>" + new Date().toString() + "</b>";
//		System.out.println("Debug Message from CrunchifySpringAjaxJQuery Controller.." + new Date().toString());
//		//return result;
//		List<SenDht11> senDht11s = senDht11Service.findAll();
//		model.addAttribute("senMods", senDht11s);
//		return "senMod/listSenMod";
//	}
//	@RequestMapping(value = "/senMod/ajaxtest", method = RequestMethod.GET)
//	public String test(Model model) {
//		System.out.println("ajaxTest");
//		List<String> list = new ArrayList<>(10);
//		for (int i = 0; i < 10; i++) {
//			list.add("hello" + i);
//		}
//		model.addAttribute("aa", list);
//		return "listSenMod::div1";
//	}
//
//	@RequestMapping(value = "/test", method = RequestMethod.POST)
//	public @ResponseBody String aaa(Model model) {
//		System.out.println("test");
//		List<SenDht11> senDht11s = senDht11Service.findAll();
//
//		model.addAttribute("articleTypes", senDht11s);
//
//		return "listSenMod::article_type";
//	}

}