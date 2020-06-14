package com.springmvc.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.modle.util.ObjectMapperUtils;
import com.springmvc.dto.ModMainDto;
import com.springmvc.dto.ModParmDataDto;
import com.springmvc.dto.ModSenDto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;
import com.springmvc.service.ModMainService;
import com.springmvc.service.ModParmDataService;
import com.springmvc.service.ModSenService;
import com.springmvc.validator.ModMainFormValidator;

/**
 * 感應模組報表controller
 * 
 * @author hrne
 *
 */
@Controller
public class SenReportController {

	@Autowired
	ModMainFormValidator modMainFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(modMainFormValidator);
	}

	@Autowired
	private ModMainService modMainService;

	@Autowired
	private ModSenService modSenService;

	@Autowired
	private ModParmDataService modParmDataService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * 導頁:溫濕度報表
	 * 
	 * @param model
	 * @return senReport/reportDht11
	 */
	@RequestMapping(value = "/senReport/reportDht11", method = RequestMethod.GET)
	public String showAllModMain(Model model) {
		System.out.println("sucessd!!");

		// 導頁至溫濕度報表
		return "senReport/reportDht11";
	}




}