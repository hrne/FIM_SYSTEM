package com.springmvc.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.SenMachDao;
import com.springmvc.entity.SenMach;
import com.springmvc.entity.SenMod;

/**
 * 工具機資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("senMachService")
public class SenMachServiceImpl extends BaseServiceImpl<SenMach> implements SenMachService {
	
    @Autowired
    private SenMachDao senMachDao;
    
    @Autowired
    private SenModService senModService;
    
	/**
	 * 查詢所有啟用的工具機
	 */
	public List<SenMach> findByMachEnable(){
		return this.getSenMachDao().findByMachEnable();
	}
	
	/**
	 * 儲存工具機資料
	 * @param senMach
	 */
	public void saveSenMachForm(SenMach senMach) {
		//將感應裝置加入
		Set<SenMod> senModSet = new HashSet<SenMod>();
		for(Integer id :senMach.getSenModsID()) {
			SenMod senMod = senModService.findByPK(id);
			senModSet.add(senMod);
		}
		senMach.setSenModSet(senModSet);
		senMachDao.saveOrUpdate(senMach);
	}


	public SenMachDao getSenMachDao() {
		return senMachDao;
	}

	public void setSenMachDao(SenMachDao senMachDao) {
		this.senMachDao = senMachDao;
	}

}
