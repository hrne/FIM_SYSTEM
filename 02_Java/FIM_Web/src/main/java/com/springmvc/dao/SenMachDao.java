package com.springmvc.dao;

import java.util.List;

import com.modle.dao.BaseDao;
import com.springmvc.entity.SenMach;

public interface SenMachDao extends BaseDao<SenMach> {
	
	/**
	 * 查詢所有啟用的工具機
	 */
	List<SenMach> findByMachEnable();

}