package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.dao.ModParmDataDao;
import com.springmvc.dto.ModParmDataDto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;

/**
 * 模組參數資料Service實作
 * 
 * @author hrne
 *
 */
@Service("modParmDataService")
public class ModParmDataServiceImpl extends BaseServiceImpl<ModParmData> implements ModParmDataService {

	@Autowired
	private ModParmDataDao modParmDataDao;

	public List<ModParmData> find_modSenId_show(Integer modSenId) {
		return modParmDataDao.find_modSenId_show(modSenId);
	}
	
	public void save_modParmDataDto(ModParmDataDto modParmDataDto){
		
		ModParmData modParmData = new ModParmData();
		
		//將dto資料轉換回entity
		modParmData = ObjectMapperUtils.map(modParmDataDto, ModParmData.class);
		
		// 寫入DB
		saveOrUpdate(modParmData);

	}
}
