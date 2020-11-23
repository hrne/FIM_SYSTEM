package com.springmvc.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.dto.SenFireAlmDto;
import com.springmvc.dto.SenHx711Dto;
import com.springmvc.service.ModMainService;
import com.springmvc.service.ModParmDataService;
import com.springmvc.service.ModSenService;
import com.springmvc.service.SenDht11Service;
import com.springmvc.service.SenFireAlmService;
import com.springmvc.service.SenHx711Service;
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
	private SenDht11Service senDht11Service;

	@Autowired
	private SenHx711Service senHx711Service;

	@Autowired
	private SenFireAlmService senFireAlmService;

	@Autowired
	private ResourceBundleMessageSource messageSource;

	/**
	 * 導頁:溫濕度報表
	 * 
	 * @param model
	 * @return senReport/reportDht11
	 */
	@RequestMapping(value = "/senReport/reportDht11", method = RequestMethod.GET)
	public String showReportDht11(Model model) {
		// 導頁至溫濕度報表
		return "senReport/reportDht11";
	}

	/**
	 * 導頁:重量報表
	 * 
	 * @param model
	 * @return senReport/reportHx711
	 */
	@RequestMapping(value = "/senReport/reportHx711", method = RequestMethod.GET)
	public String showReportHx711(Model model) {
		// 導頁至重量報表
		return "senReport/reportHx711";
	}

	/**
	 * 導頁:火災警報報表
	 * 
	 * @param model
	 * @return /senReport/reportFireAlm
	 */
	@RequestMapping(value = "/senReport/reportFireAlm", method = RequestMethod.GET)
	public String showReportFireAlm(Model model) {
		// 導頁至火災警報報表報表
		return "senReport/reportFireAlm";
	}

	/**
	 * ajax 查詢折線圖用溫度資料
	 * 
	 */
	@RequestMapping(value = "/senReport/showChartDht11")
	@ResponseBody
	public List<List<List<Long>>> showChartDht11(int protocol_type, Date s_date, Date e_date) {

		List<List<List<Long>>> resultList = new ArrayList<List<List<Long>>>();
		List<List<Long>> oneList = new ArrayList<List<Long>>();
		List<List<Long>> twoList = new ArrayList<List<Long>>();
		List<SenDht11Dto> senDht11DtoList = senDht11Service.find_show_chart(protocol_type, s_date, e_date);

		for (SenDht11Dto senDht11Dto : senDht11DtoList) {
			List<Long> ss = new ArrayList<Long>();
			ss.add(senDht11Dto.getUpdateDate().getTime());
			ss.add(senDht11Dto.getTempCal().longValue());
			oneList.add(ss);
		}
		resultList.add(oneList);
		for (SenDht11Dto senDht11Dto : senDht11DtoList) {
			List<Long> ss = new ArrayList<Long>();
			ss.add(senDht11Dto.getUpdateDate().getTime());
			ss.add(senDht11Dto.getHumidity().longValue());
			twoList.add(ss);
		}
		resultList.add(twoList);
		return resultList;
	}

	/**
	 * ajax 查詢折線圖用重量資料
	 * 
	 */
	@RequestMapping(value = "/senReport/showChartHx711")
	@ResponseBody
	public List<List<List<Long>>> showChartHx711(int protocol_type, Date s_date, Date e_date) {

		List<List<List<Long>>> resultList = new ArrayList<List<List<Long>>>();
		List<List<Long>> oneList = new ArrayList<List<Long>>();
		List<SenHx711Dto> senHx711DtoList = senHx711Service.find_show_chart(protocol_type, s_date, e_date);

		for (SenHx711Dto senHx711Dto : senHx711DtoList) {
			List<Long> ss = new ArrayList<Long>();
			ss.add(senHx711Dto.getUpdateDate().getTime());
			ss.add(senHx711Dto.getWeight().longValue());
			oneList.add(ss);
		}
		resultList.add(oneList);

		return resultList;
	}

	/**
	 * ajax 查詢折線圖用火災警報感應資料
	 * 
	 */
	@RequestMapping(value = "/senReport/showCharFireAlm")
	@ResponseBody
	public List<List<List<Long>>> showChartFireAlm(int protocol_type, Date s_date, Date e_date) {

		List<List<List<Long>>> resultList = new ArrayList<List<List<Long>>>();
		List<List<Long>> oneList = new ArrayList<List<Long>>();
		List<List<Long>> twoList = new ArrayList<List<Long>>();
		List<SenFireAlmDto> senFireAlmDtoList = senFireAlmService.find_show_chart(protocol_type, s_date, e_date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (SenFireAlmDto senFireAlmDto : senFireAlmDtoList) {
			List<Long> ss = new ArrayList<Long>();
			ss.add(senFireAlmDto.getUpdateDate().getTime());
			Long a = senFireAlmDto.getFireStatus().longValue();
			if (a == (long) 1) {
				a = (long) 0;
			} else {
				a = (long) 1;
			}
			ss.add(a);
			oneList.add(ss);
			System.out.println(sdf.format(senFireAlmDto.getUpdateDate().getTime()));
		}
		resultList.add(oneList);
		for (SenFireAlmDto senFireAlmDto : senFireAlmDtoList) {
			List<Long> ss = new ArrayList<Long>();
			ss.add(senFireAlmDto.getUpdateDate().getTime());
			ss.add(senFireAlmDto.getMq7Status().longValue());
			twoList.add(ss);
		}
		resultList.add(twoList);
		return resultList;
	}
	// https://ithelp.ithome.com.tw/questions/10188080?sc=pt
	// https://blog.csdn.net/bsh_csn/article/details/51700514
	// https://blog.csdn.net/u011781521/article/details/82284449

}