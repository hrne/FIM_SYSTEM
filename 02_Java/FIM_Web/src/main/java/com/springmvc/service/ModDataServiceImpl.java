package com.springmvc.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.dao.ModMainDao;
import com.springmvc.dto.ModDataDto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 感應裝置的Service實做
 * 
 * @author hrne
 *
 */
@Service("modDataService")
public class ModDataServiceImpl extends BaseServiceImpl<ModMain> implements ModDataService {

	@Autowired
	private ModMainDao modDataDao;

	@Autowired
	private ModSenService modSenService;

	public List<ModMain> findByModEnable() {
		return modDataDao.findByModEnable();
	}

	public void saveModDataByDto(ModDataDto modDataDto) {

		ModMain modData = new ModMain();

		Set<ModSen> modSenSet = new HashSet<ModSen>();

		for (Integer id : modDataDto.getModSenIDs()) {
			//查詢感應模組
			ModSen modSen = modSenService.findByPK(id);
			modSenSet.add(modSen);
		}

		modData = ObjectMapperUtils.map(modDataDto, ModMain.class);

		// 將使用感應模組放入
		modData.setModSenSet(modSenSet);

		// 寫入DB
		saveOrUpdate(modData);
	}

}
