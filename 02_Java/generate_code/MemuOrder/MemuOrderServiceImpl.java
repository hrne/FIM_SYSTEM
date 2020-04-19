package com.springmvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.dao.SenMachDao;
import com.springmvc.dto.SenMachDto;
import com.springmvc.entity.SenMach;
import com.springmvc.entity.SenMod;

/**
 * 感應器資料的Service實做
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
	 * 查詢所有啟用的感應器
	 */
	public List<SenMach> findByMachEnable(){
		return this.getSenMachDao().findByMachEnable();
	}
	
	/**
	 * 儲存感應器資料
	 * @param senMachDto
	 */
	public void saveSenMachForm(SenMachDto senMachDto) {
		
		SenMach senMach =new SenMach();
		
		//將感應裝置加入
		Set<SenMod> senModSet = new HashSet<SenMod>();
		for(Integer id :senMachDto.getSenModsID()) {
			SenMod senMod = senModService.findByPK(id);
			senModSet.add(senMod);
		}
		
		senMach = ObjectMapperUtils.map(senMachDto, SenMach.class);
		
		//感應裝置資料放入
		senMach.setSenModSet(senModSet);
		
		//寫入DB
		senMachDao.saveOrUpdate(senMach);
	}


	public SenMachDao getSenMachDao() {
		return senMachDao;
	}

	public void setSenMachDao(SenMachDao senMachDao) {
		this.senMachDao = senMachDao;
	}

}
