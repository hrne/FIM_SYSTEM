package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modle.util.ObjectMapperUtils;
import com.springmvc.dto.ModSenDto;
import com.springmvc.dto.SenSwitchDto;
import com.springmvc.entity.ModData;
import com.springmvc.entity.ModSen;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenHx711;
import com.springmvc.entity.SenSwitch;
import com.springmvc.service.SenDht11Service;
import com.springmvc.service.SenHx711Service;
import com.springmvc.service.SenSwitchService;

/**
 * 感應模組controller
 * 
 * @author hrne
 *
 */
@Controller
public class SenModController {

	@Autowired
	private SenDht11Service senDht11Service;

	@Autowired
	private SenHx711Service senHx711Service;

	@Autowired
	private SenSwitchService senSwitchService;

	@RequestMapping(value = { "/index.html", "/index", "/" }, method = RequestMethod.GET)
	public String homepage(Model model) {
		// 查詢溫濕度資料
		List<SenDht11> senDht11List = senDht11Service.findLatestDht11Data();
		model.addAttribute("senDht11List", senDht11List);

		// 查詢重量資料
		List<SenHx711> senHx711List = senHx711Service.findLatestHx711Data();
		model.addAttribute("senHx711List", senHx711List);

		// 查詢電源開關資料
		List<SenSwitch> senSwitchList = senSwitchService.findLatestSwitchData();
		model.addAttribute("senSwitchList", senSwitchList);

		return "senMod/indexSen";
	}

	/**
	 * 查詢溫濕度資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listSenDht11", method = RequestMethod.GET)
	public String showAllSenMod(Model model) {
		List<SenDht11> senDht11List = senDht11Service.findLatestDht11Data();
		model.addAttribute("senDht11List", senDht11List);
		return "senMod/listSenDht11";
	}

	/**
	 * ajax 查詢溫濕度資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listDht11", method = RequestMethod.GET)
	public String getTimeDht11(Model model) {
		List<SenDht11> senDht11List = senDht11Service.findLatestDht11Data();
		model.addAttribute("senDht11List", senDht11List);
		return "senMod/tableDht11";
	}

	/**
	 * ajax 查詢重量資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listHx711", method = RequestMethod.GET)
	public String getTimeHx711(Model model) {
		// 查詢重量資料
		List<SenHx711> senHx711List = senHx711Service.findLatestHx711Data();
		model.addAttribute("senHx711List", senHx711List);
		return "senMod/tableHx711";
	}

	/**
	 * ajax 查詢電源開關資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listSwitch", method = RequestMethod.GET)
	public String getTimeSwitch(Model model) {
		// 查詢電源開關資料
		List<SenSwitch> senSwitchList = senSwitchService.findLatestSwitchData();
		model.addAttribute("senSwitchList", senSwitchList);
		return "senMod/tableSwitch";
	}
	
	/**
	 * ajax 查詢電源開關資料
	 * 
	 */
	@RequestMapping(value = "/senMod/listSwitchtt")
	@ResponseBody
	public List<SenSwitchDto> getTimeSwitch() {

		// 查詢電源開關資料
		List<SenSwitch> listSenSwitch = senSwitchService.findLatestSwitchData();

		// 將電源開關資料map到DTO上供頁面顯示
		List<SenSwitchDto> listSenSwitchDto = ObjectMapperUtils.mapAll(listSenSwitch, SenSwitchDto.class);

		return listSenSwitchDto;
	}

	/**
	 * ajax 開關電源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/turnPowerSwitch")
	public @ResponseBody String turnPowerSwitch(int id) {
		SenSwitch senSwitch = senSwitchService.findByPK(id);
		boolean returnStatus = senSwitchService.turnSwitch(senSwitch);
		String codeName = "";
		if (senSwitch.getPowStatus() == 0) {
			codeName = "電源打開";
		} else {
			codeName = "電源關閉";
		}
		if (returnStatus) {
			return codeName + "成功";
		} else {
			return codeName + "失敗";
		}
	}

}