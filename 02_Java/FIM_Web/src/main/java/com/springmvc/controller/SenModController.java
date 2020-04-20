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

	@Autowired
	private SenDht11Service senDht11Service;

	/**
	 * 查詢溫濕度資料
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listSenDht11", method = RequestMethod.GET)
	public String showAllSenMod(Model model) {
		List<SenDht11> senDht11List = senDht11Service.findAll();
		model.addAttribute("senDht11List", senDht11List);
		return "senMod/listSenDht11";
	}

	/**
	 * ajax 查詢溫濕度資料
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/senMod/listSenDht11Data", method = RequestMethod.GET)
	public String getTime(Model model) {
		List<SenDht11> senDht11List = senDht11Service.findAll();
		model.addAttribute("senDht11List", senDht11List);
		System.out.println("test");
		return "senMod/listSenDht11Data";
	}

}