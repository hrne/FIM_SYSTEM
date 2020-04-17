package com.springmvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.modle.entity.User;
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
		List<SenMach> senMachs = senMachService.findAll();
		model.addAttribute("senMachs", senMachs);
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
		SenMach senMach = new SenMach();

		model.addAttribute("senMachForm", senMach);

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
		SenMach senMach = senMachService.findByPK(id);
		List<Integer> senModIDs = new ArrayList<Integer>();
		// 將現有的感應裝置放入顯示
		for (SenMod senMod : senMach.getSenModSet()) {
			senModIDs.add(senMod.getId());
		}
		senMach.setSenModsID(senModIDs);
		model.addAttribute("senMachForm", senMach);

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
	public String saveOrUpdateUser(@ModelAttribute("senMachForm") @Validated SenMach senMach, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, Locale locale) {
		if (result.hasErrors()) {
			createFormOptions(model);
			return "senMach/senMachForm";
		} else {
			senMachService.saveSenMachForm(senMach);

			redirectAttributes.addFlashAttribute("css", "success");
			if (senMach.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "User added successfully!");
			} else {
				redirectAttributes.addFlashAttribute("msg",
						messageSource.getMessage("welcome", new Object[] { "John Doe" }, locale));
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

	private User createModelDefaultValues() {
		User user = new User();
		// set default value
		user.setName("Jim");
		user.setEmail("test@gmail.com");
		user.setAddress("台灣");
		// user.setPassword("test@1111");
		// user.setConfirmPassword("test@1111");
		user.setNewsletter(true);
		user.setSex("M");
		user.setFramework(new ArrayList<String>(Arrays.asList("Spring MVC", "Struts")));
		user.setSkill(new ArrayList<String>(Arrays.asList("HTML", "CSS", "JavaScript")));
		user.setCountry("TW");
		user.setHeight(170);
		return user;
	}

}