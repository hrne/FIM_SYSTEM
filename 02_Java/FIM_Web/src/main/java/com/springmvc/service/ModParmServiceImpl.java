package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.ModParmDao;
import com.springmvc.entity.ModParm;
import com.springmvc.entity.ModSen;

/**
 * 感應模組參數的Service實做
 * 
 * @author hrne
 *
 */
@Service("modParmService")
public class ModParmServiceImpl extends BaseServiceImpl<ModParm> implements ModParmService {

	@Autowired
	private ModParmDao modParmDao;

	/**
	 * 依據感應裝置查詢溫濕度資料，依照更新日期新到舊排序
	 * 
	 * @param modData 感應裝置
	 * @return SenDht11 list 溫濕度列表
	 */
	public List<ModParm> findModParmBySen(Integer modSen_id) {
		return modParmDao.findModParmBySen(modSen_id);
	}
}
