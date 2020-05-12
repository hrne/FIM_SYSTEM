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
 * 感應裝置controller
 * 
 * @author hrne
 *
 */
@Controller
public class ModDataController {

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
	 * 導頁:感應裝置列表
	 * 
	 * @param model
	 * @return modData/listModData
	 */
	@RequestMapping(value = "/modData/showAllModMain", method = RequestMethod.GET)
	public String showAllModMain(Model model) {

		// 查詢所有感應裝置主檔
		List<ModMain> modMainList = modMainService.findAll();

		// 將感應裝置map到DTO上供頁面顯示
		List<ModMainDto> modMainDtoList = ObjectMapperUtils.mapAll(modMainList, ModMainDto.class);

		// 資料放至頁面
		model.addAttribute("modMainDtoList", modMainDtoList);

		// 導頁至感應裝置列表
		return "modData/listModMain";
	}

	/**
	 * 導頁:新增感應裝置頁面
	 * 
	 * @param model
	 * @return modData/modMainForm
	 */
	@RequestMapping(value = "/modData/showAddModMain", method = RequestMethod.GET)
	public String showAddModMain(Model model) {
		ModMainDto modMainDto = new ModMainDto();

		// 將dto放至頁面以暫存資料
		model.addAttribute("modMainDto", modMainDto);

		// 產生頁面選單資料
		createFormOptions(model);

		// 導頁至新增頁面
		return "modData/modMainForm";
	}

	/**
	 * 導頁:修改感應裝置頁面
	 * 
	 * @param id    感應裝置主檔id
	 * @param model
	 * @return modData/modMainForm
	 */
	@RequestMapping(value = "/modData/{id}/showUpdateModMain", method = RequestMethod.GET)
	public String showUpdateModMain(@PathVariable("id") int id, Model model) {

		// 查詢感應裝置
		ModMain modMain = modMainService.findByPK(id);

		// 將感應裝置map到dto上
		ModMainDto modMainDto = ObjectMapperUtils.map(modMain, ModMainDto.class);

		List<Integer> modSenIdList = new ArrayList<Integer>();

		// 將現有的感應裝置放入顯示
		for (ModSen modSen : modMain.getModSenSet()) {
			modSenIdList.add(modSen.getId());
		}
		modMainDto.setModSenIdList(modSenIdList);

		// 資料放至頁面
		model.addAttribute("modMainDto", modMainDto);

		// 產生頁面選單資料
		createFormOptions(model);

		// 導頁至修改頁面
		return "modData/modMainForm";
	}

	/**
	 * 儲存感應裝置 from add or update
	 * 
	 * @param modMainDto
	 * @param result
	 * @param model
	 * @param redirectAttributes
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/modData/saveModMain", method = RequestMethod.POST)
	public String saveModMain(@ModelAttribute("senMachForm") @Validated ModMainDto modMainDto, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes, Locale locale) {
		if (result.hasErrors()) {
			createFormOptions(model);
			return "modData/modMainForm";
		} else {

			// 儲存資料
			modMainService.save_modMainDto(modMainDto);

			// 顯示回傳訊息
			redirectAttributes.addFlashAttribute("css", "success");
			if (modMainDto.isNew()) {
				redirectAttributes.addFlashAttribute("msg",
						messageSource.getMessage("modDataAddSuc", new Object[] {}, locale));
			} else {
				redirectAttributes.addFlashAttribute("msg",
						messageSource.getMessage("modDataUpdateSuc", new Object[] {}, locale));
			}
			//重導至感應裝置列表
			return "redirect:/modData/showAllModMain";
		}
	}

	/**
	 * 產生頁面資訊
	 * 
	 * @param model
	 */
	private void createFormOptions(Model model) {

		// 查詢所有感應模組
		List<ModSen> modSenList = modSenService.findAll();

		Map<Integer, String> senList = new HashMap<>();

		// 感應模組選單
		for (ModSen modSen : modSenList) {
			senList.put(modSen.getId(), modSen.getSenName());
		}

		model.addAttribute("senList", senList);

	}

}