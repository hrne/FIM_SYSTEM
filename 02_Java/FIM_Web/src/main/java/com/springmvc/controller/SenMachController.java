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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.modle.util.ObjectMapperUtils;
import com.springmvc.dto.SenMachDto;
import com.springmvc.entity.SenMach;
import com.springmvc.entity.SenMod;
import com.springmvc.service.SenMachService;
import com.springmvc.service.SenModService;
import com.springmvc.validator.SenMachFormValidator;

@Controller
public class SenMachController {

	@Autowired
	SenMachFormValidator senMachFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(senMachFormValidator);
	}

	@Autowired
	private SenMachService senMachService;

	@Autowired
	private SenModService senModService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * 查詢所有感應器
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMach/list", method = RequestMethod.GET)
	public String showAllSenMach(Model model) {

		// 查詢所有感應器資料
		List<SenMach> senMachs = senMachService.findAll();

		// 將感應器map到dto上供頁面顯示
		List<SenMachDto> listSenMachDto = ObjectMapperUtils.mapAll(senMachs, SenMachDto.class);
		model.addAttribute("listSenMachDto", listSenMachDto);
		return "senMach/listSenMach";
	}

	/**
	 * 新增感應器
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMach/add", method = RequestMethod.GET)
	public String showAddUserForm(Model model) {
		SenMachDto senMachDto = new SenMachDto();
		model.addAttribute("senMachDto", senMachDto);
		createFormOptions(model);
		return "senMach/senMachForm";
	}

	/**
	 * 修改感應器
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMach/{id}/update", method = RequestMethod.GET)
	public String showUpdateSenMachForm(@PathVariable("id") int id, Model model) {

		// 查詢感應器資料
		SenMach senMach = senMachService.findByPK(id);

		// 將感應器map到dto上
		SenMachDto senMachDto = ObjectMapperUtils.map(senMach, SenMachDto.class);

		List<Integer> senModIDs = new ArrayList<Integer>();

		// 將現有的感應裝置放入顯示
		for (SenMod senMod : senMach.getSenModSet()) {
			senModIDs.add(senMod.getId());
		}
		senMachDto.setSenModsID(senModIDs);
		model.addAttribute("senMachDto", senMachDto);

		createFormOptions(model);
		return "senMach/senMachForm";
	}

	/**
	 * 儲存感應器 from add or update
	 * 
	 * @param user
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/senMach/save", method = RequestMethod.POST)
	public String saveOrUpdateUser(@ModelAttribute("senMachForm") @Validated SenMachDto senMachDto, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, Locale locale) {
		if (result.hasErrors()) {
			createFormOptions(model);
			return "senMach/senMachForm";
		} else {
			senMachService.saveSenMachForm(senMachDto);

			redirectAttributes.addFlashAttribute("css", "success");
			if (senMachDto.isNew()) {
				redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("senMachAddSucess", new Object[] { }, locale));
			} else {
				redirectAttributes.addFlashAttribute("msg", messageSource.getMessage("senMachUpdateSucess", new Object[] { }, locale));
			}
			return "redirect:/senMach/list";
		}
	}

	/**
	 * 產生頁面資訊
	 * 
	 * @param model
	 */
	private void createFormOptions(Model model) {
		// 查詢所有感應裝置
		List<SenMod> senMods = senModService.findAll();

		Map<Integer, String> senModList = new HashMap<>();
		// 感應裝置選單
		for (SenMod bo : senMods) {
			senModList.put(bo.getId(), bo.getMachName());
		}

		model.addAttribute("senModList", senModList);

	}

}