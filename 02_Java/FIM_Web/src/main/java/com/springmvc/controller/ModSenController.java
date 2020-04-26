package com.springmvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenHx711;
import com.springmvc.service.SenDht11Service;
import com.springmvc.service.SenHx711Service;

/**
 * 感應模組controller
 * 
 * @author hrne
 *
 */
@Controller
public class ModSenController {

	@Autowired
	private SenDht11Service senDht11Service;
	
	@Autowired
	private SenHx711Service senHx711Service;
	
	@RequestMapping(value = { "/index.html", "/index", "/" }, method = RequestMethod.GET)
	public String homepage(Model model) {
		//查詢溫濕度資料
		List<SenDht11> senDht11List = senDht11Service.findLatestDht11Data();
		model.addAttribute("senDht11List", senDht11List);
		
		//查詢重量資料
		List<SenHx711> senHx711List = senHx711Service.findLatestHx711Data();
		model.addAttribute("senHx711List", senHx711List);
		
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
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listDht11", method = RequestMethod.GET)
	public String getTimeDht11(Model model) {
		List<SenDht11> senDht11List = senDht11Service.findLatestDht11Data();
		model.addAttribute("senDht11List", senDht11List);
		System.out.println("/senMod/listDht11");
		return "senMod/tableDht11";
	}
	
	/**
	 * ajax 查詢重量資料
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listHx711", method = RequestMethod.GET)
	public String getTimeHx711(Model model) {
		//查詢重量資料
		List<SenHx711> senHx711List = senHx711Service.findLatestHx711Data();
		model.addAttribute("senHx711List", senHx711List);
		return "senMod/tableHx711";
	}

}