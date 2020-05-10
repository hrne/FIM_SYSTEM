package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modle.util.ObjectMapperUtils;
import com.springmvc.dto.ModRespLogDto;
import com.springmvc.dto.ModSenDto;
import com.springmvc.dto.SenSwitchDto;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenHx711;
import com.springmvc.entity.SenSwitch;
import com.springmvc.service.ModMainService;
import com.springmvc.service.ModRespLogService;
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
	private ModMainService modMainService;
	
	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private SenDht11Service senDht11Service;

	@Autowired
	private SenHx711Service senHx711Service;

	@Autowired
	private SenSwitchService senSwitchService;

	/**
	 * 首頁
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/index.html", "/index", "/" }, method = RequestMethod.GET)
	public String showAllSenData(Model model) {
		
		//每進入一次就讀取一次arduino資料
		//modMainService.scan_MainMod();
		
		// 查詢溫濕度資料
		List<SenDht11> senDht11List = senDht11Service.find_latest_modMain();
		model.addAttribute("senDht11List", senDht11List);

		// 查詢重量資料
		List<SenHx711> senHx711List = senHx711Service.find_latest_modMain();
		model.addAttribute("senHx711List", senHx711List);

//		// 查詢電源開關資料
//		List<SenSwitch> senSwitchList = senSwitchService.find_latest_modMain();
//		model.addAttribute("senSwitchList", senSwitchList);

		// 導頁至首頁
		return "senMod/indexSen";
	}

	/**
	 * ajax 查詢溫濕度資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/showAllDht1111", method = RequestMethod.GET)
	public String showAllDht1111(Model model) {
		// 查詢溫濕度資料
		List<SenDht11> senDht11List = senDht11Service.find_latest_modMain();
		model.addAttribute("senDht11List", senDht11List);
		return "senMod/listSenDht11";
	}

	/**
	 * ajax 查詢溫濕度資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/showAllDht11", method = RequestMethod.GET)
	public String showAllDht11(Model model) {
		// 查詢溫濕度資料
		List<SenDht11> senDht11List = senDht11Service.find_latest_modMain();
		model.addAttribute("senDht11List", senDht11List);
		return "senMod/tableDht11";
	}

	/**
	 * ajax 查詢重量資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/showAllHx711", method = RequestMethod.GET)
	public String showAllHx711(Model model) {
		// 查詢重量資料
		List<SenHx711> senHx711List = senHx711Service.find_latest_modMain();
		model.addAttribute("senHx711List", senHx711List);
		return "senMod/tableHx711";
	}

	/**
	 * ajax 查詢電源開關資料
	 * 
	 */
	@RequestMapping(value = "/senMod/showAllSwitch")
	@ResponseBody
	public List<SenSwitchDto> showAllSwitch() {

		// 查詢電源開關資料
		List<SenSwitch> senSwitchList = senSwitchService.find_latest_modMain();

		// 將電源開關資料map到dto上供頁面顯示
		List<SenSwitchDto> senSwitchDtoList = ObjectMapperUtils.mapAll(senSwitchList, SenSwitchDto.class);

		return senSwitchDtoList;
	}

	/**
	 * ajax 開關電源
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/turnPowerSwitch")
	public @ResponseBody String turnPowerSwitch(int modMainId, boolean state) {

		//依據感應裝置id傳送開關訊號
		boolean returnStatus = senSwitchService.turn_senSwitchId(modMainId, state);
		String codeName = "";
		System.out.println("id:" + modMainId + "  state:" + state);
		if (!state) {
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
	
	/**
	 * ajax 查詢感應紀錄資料
	 * 
	 */
	@RequestMapping(value = "/senMod/showAllRespLog")
	@ResponseBody
	public List<ModRespLogDto> showAllRespLog() {

		// 查詢感應紀錄資料
		List<ModRespLog> modRespLogList = modRespLogService.find_latest_modMain();

		// 將感應紀錄資料map到dto上供頁面顯示
		List<ModRespLogDto> modRespLogDtoList = ObjectMapperUtils.mapAll(modRespLogList, ModRespLogDto.class);
		System.out.println("success!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1");
		return modRespLogDtoList;
	}

}