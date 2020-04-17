package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.SenMach;

/**
 * 工具機資料的Service介面
 * 
 * @author hrne
 *
 */
public interface SenMachService extends BaseService<SenMach> {
	
	/**
	 * 查詢所有啟用的工具機
	 */
	List<SenMach> findByMachEnable();
	
	/**
	 * 儲存工具機資料
	 * @param senMach
	 */
	void saveSenMachForm(SenMach senMach);

}