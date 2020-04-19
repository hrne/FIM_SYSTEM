package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.dto.SenMachDto;
import com.springmvc.entity.SenMach;

/**
 * 感應器資料的Service介面
 * 
 * @author hrne
 *
 */
public interface SenMachService extends BaseService<SenMach> {
	
	/**
	 * 查詢所有啟用的感應器
	 */
	List<SenMach> findByMachEnable();
	
	/**
	 * 儲存感應器資料
	 * @param senMachDto
	 */
	void saveSenMachForm(SenMachDto senMachDto);

}