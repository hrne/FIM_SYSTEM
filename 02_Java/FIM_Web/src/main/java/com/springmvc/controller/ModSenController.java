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
import com.springmvc.validator.ModSenFormValidator;

/**
 * 感應模組controller
 * 
 * @author hrne
 *
 */
@Controller
public class ModSenController {

	@Autowired
	ModSenFormValidator modSenFormValidator;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(modSenFormValidator);
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
	 * 導頁:感應模組警示值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modSen/showAllSenParmLimit", method = RequestMethod.GET)
	public String showAllSenParmLimit() {

		// 導頁至感應模組警示值
		return "modSen/listSenParmLimit";
	}

	/**
	 * 列出所有感應模組
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modSen/showAllModSen")
	@ResponseBody
	public List<ModSenDto> showAllModSen() {

		// 查詢所有感應模組
		List<ModSen> modSenList = modSenService.findAll();

		// 將感應模組map到dto上供頁面顯示
		List<ModSenDto> modSenDtoList = ObjectMapperUtils.mapAll(modSenList, ModSenDto.class);

		return modSenDtoList;
	}

	/**
	 * 列出對應感應模組參數
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/modSen/showModParm", method = RequestMethod.GET)
	@ResponseBody
	public List<ModParmDataDto> showModParm(Integer id) {

		// 依據感應模組id查詢模組參數資料
		List<ModParmData> modParmDataList = modParmDataService.find_modSenId_show(id);

		// 將參數map到dto上供頁面顯示
		List<ModParmDataDto> modParmDataDtoList = ObjectMapperUtils.mapAll(modParmDataList, ModParmDataDto.class);

		return modParmDataDtoList;
	}

//	/**
//	 * 導頁:修改感應模組警示值
//	 * 
//	 * @param id    感應模組id
//	 * @param model
//	 * @return modData/modMainForm
//	 */
//	@RequestMapping(value = "/modSen/{id}/showUpdateSenParmLimit", method = RequestMethod.GET)
//	public String showUpdateSenParmLimit(@PathVariable("id") int id, Model model) {
//
//		// 查詢感應模組
//		ModSen modSen = modSenService.findByPK(id);
//
//		// 依據感應模組id查詢模組參數資料
//		List<ModParmData> modParmDataList = modParmDataService.find_modSenId_show(id);
//
//		// 將感應模組map到dto上
//		ModSenDto modSenDto = ObjectMapperUtils.map(modSen, ModSenDto.class);
//
//		// 將參數map到dto上供頁面顯示
//		List<ModParmDataDto> modParmDataDtoList = ObjectMapperUtils.mapAll(modParmDataList, ModParmDataDto.class);
//
//		modSenDto.setModParmDataDtoList(modParmDataDtoList);
//
//		model.addAttribute("modSenDto", modSenDto);
//
//		// 導頁至修改頁面
//		return "modSen/senParmLimitForm";
//	}

//	/**
//	 * 儲存感應裝置 from add or update
//	 * 
//	 * @param modMainDto
//	 * @param result
//	 * @param model
//	 * @param redirectAttributes
//	 * @param locale
//	 * @return
//	 */
//	@RequestMapping(value = "/modSen/saveSenParmLimit", method = RequestMethod.POST)
//	public String save(@ModelAttribute("modSenDto") @Validated ModSenDto modSenDto,List<ModParmDataDto> modParmDataDtoList, BindingResult result,
//			Model model, final RedirectAttributes redirectAttributes, Locale locale) {
//		if (result.hasErrors()) {
//			return "modSen/senParmLimitForm";
//		} else {
//			System.out.println(modSenDto.getId());
//			for(ModParmDataDto modParmDataDto: modParmDataDtoList) {
//				// 儲存資料
//				modParmDataService.save_modParmDataDto(modParmDataDto);
//			}
//
////			// 顯示回傳訊息
////			redirectAttributes.addFlashAttribute("css", "success");
////			if (modMainDto.isNew()) {
////				redirectAttributes.addFlashAttribute("msg",
////						messageSource.getMessage("modDataAddSuc", new Object[] {}, locale));
////			} else {
////				redirectAttributes.addFlashAttribute("msg",
////						messageSource.getMessage("modDataUpdateSuc", new Object[] {}, locale));
////			}
//			//重導至感應模組列表
//			return "redirect:/modSen/showAllSenParmLimit";
//		}
//	}

	/**
	 * ajax 儲存模組警示值
	 * 
	 * @param model
	 * @return
	 */

	/**
	 * ajax 儲存模組警示值
	 * 
	 * @param id         模組參數id
	 * @param field      修改欄位
	 * @param upperLimit 修改後上限警示值
	 * @param lowerLimit 修改後下限警示值
	 * @return
	 */
	@RequestMapping(value = "/modSen/saveSenParmLimit", method = RequestMethod.GET)
	@ResponseBody
	public String saveSenParmLimit(int id, String field, int upperLimit, int lowerLimit) {

		modParmDataService.save_filed_data(id, field, upperLimit, lowerLimit);
		return "success";
	}

	/**
	 * ajax 儲存模組警示是否啟用
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/modSen/saveSenParmLimitEnabled")
	public @ResponseBody String turnPowerSwitch(int id, boolean state) {

		//改變是否啟用狀態
		//state = !state;
		modParmDataService.save_modParmDataId(id, state);
		return "success";
	}

}