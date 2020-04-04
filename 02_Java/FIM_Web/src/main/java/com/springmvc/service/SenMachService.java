package com.springmvc.service;

import java.util.List;

import com.modle.service.BaseService;
import com.springmvc.entity.SenMach;

public interface SenMachService extends BaseService<SenMach> {
	
	/**
	 * 查詢所有啟用的工具機
	 */
	List<SenMach> findByMachEnable();

}