package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.SenMachDao;
import com.springmvc.entity.SenMach;

/**
 * 工具機資料的Service實做
 * 
 * @author hrne
 *
 */
@Service
public class SenMachServiceImpl extends BaseServiceImpl<SenMach> implements SenMachService {
	
    @Autowired
    private SenMachDao senMachDao;
    
	/**
	 * 查詢所有啟用的工具機
	 */
	public List<SenMach> findByMachEnable(){
		return this.getSenMachDao().findByMachEnable();
	}

	public SenMachDao getSenMachDao() {
		return senMachDao;
	}

	public void setSenMachDao(SenMachDao senMachDao) {
		this.senMachDao = senMachDao;
	}

}
