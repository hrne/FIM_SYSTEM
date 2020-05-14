package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.ModSenDao;
import com.springmvc.entity.ModSen;

/**
 * 感應模組Service實作
 * 
 * @author hrne
 *
 */
@Service("modSenService")
public class ModSenServiceImpl extends BaseServiceImpl<ModSen> implements ModSenService {

	@Autowired
	private ModSenDao modSenDao;
	
	public ModSen find_senCode(String senCode) {
		return modSenDao.find_senCode(senCode);
	}

}
