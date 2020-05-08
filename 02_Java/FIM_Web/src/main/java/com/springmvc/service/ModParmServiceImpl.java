package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.ModParmDataDao;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;

/**
 * 感應模組參數的Service實做
 * 
 * @author hrne
 *
 */
@Service("modParmService")
public class ModParmServiceImpl extends BaseServiceImpl<ModParmData> implements ModParmService {

	@Autowired
	private ModParmDataDao modParmDao;

	public List<ModParmData> findModParmBySenId(Integer modSen_id) {
		return modParmDao.findModParmBySenId(modSen_id);
	}
}
