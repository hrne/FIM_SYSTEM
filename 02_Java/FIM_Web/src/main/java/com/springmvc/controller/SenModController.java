package com.springmvc.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.modle.util.ObjectMapperUtils;
import com.springmvc.dto.ModMainDto;
import com.springmvc.dto.ModRespLogDto;
import com.springmvc.dto.ModSenDto;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.dto.SenFireAlmDto;
import com.springmvc.dto.SenHx711Dto;
import com.springmvc.dto.SenSwitchDto;
import com.springmvc.entity.ModRespLog;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenFireAlm;
import com.springmvc.entity.SenHx711;
import com.springmvc.entity.SenSwitch;
import com.springmvc.service.ModMainService;
import com.springmvc.service.ModRespLogService;
import com.springmvc.service.SenDht11Service;
import com.springmvc.service.SenFireAlmService;
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

	@Autowired
	private SenFireAlmService senFireAlmService;

	/**
	 * 首頁
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/index.html", "/index", "/" }, method = RequestMethod.GET)
	public String showAllSenData(Model model) {

		// 每進入一次就讀取一次arduino資料
		// modMainService.scan_MainMod();

		// 查詢溫濕度資料dto
		List<SenDht11Dto> senDht11DtoList = senDht11Service.find_show_page();
		model.addAttribute("senDht11DtoList", senDht11DtoList);

		// 查詢重量資料dto
		List<SenHx711Dto> senHx711DtoList = senHx711Service.find_show_page();
		model.addAttribute("senHx711DtoList", senHx711DtoList);

		// 查詢火災警報感應資料資料dto
		List<SenFireAlmDto> senFireAlmDtoList = senFireAlmService.find_show_page();
		model.addAttribute("senFireAlmDtoList", senFireAlmDtoList);

		// 查詢感應紀錄dto
		List<ModRespLogDto> modRespLogDtoList = modRespLogService.find_show_page();
		model.addAttribute("modRespLogDtoList", modRespLogDtoList);

		// 導頁至首頁
		return "senMod/indexSen";
	}

	/**
	 * ajax 查詢溫濕度資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/showAllDht11", method = RequestMethod.GET)
	public String showAllDht11(Model model) {
		// 查詢溫濕度資料dto
		List<SenDht11Dto> senDht11DtoList = senDht11Service.find_show_page();
		model.addAttribute("senDht11DtoList", senDht11DtoList);
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
		// 查詢重量資料dto
		List<SenHx711Dto> senHx711DtoList = senHx711Service.find_show_page();
		model.addAttribute("senHx711DtoList", senHx711DtoList);
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

		// 依據感應裝置id傳送開關訊號
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
	 * ajax 查詢火災警報感應資料資料
	 * 
	 */
	@RequestMapping(value = "/senMod/showAllFireAlm", method = RequestMethod.GET)
	public String showAllFireAlm(Model model) {

		// 查詢火災警報感應資料dto
		List<SenFireAlmDto> senFireAlmDtoList = senFireAlmService.find_show_page();
		model.addAttribute("senFireAlmDtoList", senFireAlmDtoList);
		return "senMod/tableFireAlm";
	}

	/**
	 * ajax 查詢感應紀錄資料
	 * 
	 */
	@RequestMapping(value = "/senMod/showAllRespLog", method = RequestMethod.GET)
	public String showAllRespLog(Model model) {

		// 查詢感應紀錄dto
		List<ModRespLogDto> modRespLogDtoList = modRespLogService.find_show_page();
		model.addAttribute("modRespLogDtoList", modRespLogDtoList);
		return "senMod/tableRespLog";
	}

	/**
	 * ajax 查詢折線圖用溫度資料
	 * 
	 */
	@RequestMapping(value = "/senMod/showChartDht11")
	public @ResponseBody String showChartDht11() {

		JSONObject obj = new JSONObject();
		// 查詢溫濕度資料dto
		List<SenDht11Dto> senDht11DtoList = senDht11Service.find_show_page();
		for (SenDht11Dto senDht11Dto : senDht11DtoList) {
			obj.put(String.valueOf(senDht11Dto.getModMain().getId()) + "id", senDht11Dto.getUpdateDate().getTime());
			obj.put(String.valueOf(senDht11Dto.getModMain().getId()) + "Cal", senDht11Dto.getTempCal());
		}

		return obj.toString();
	}

	/**
	 * ajax 查詢初始化折線圖用溫度資料
	 * 
	 */
//	@RequestMapping(value = "/senMod/showInitChartDht11")
//	public @ResponseBody String showInitChartDht11() {
//
//		String jsonStr = "";
//		List<ModMainDto> modMainDtoList = modMainService.find_chart_init();
//
//		JSONArray fromObject2 = new JSONArray();
////		fromObject2.fr
////		System.out.println(jsonStr);
//		return jsonStr;
//	}

}