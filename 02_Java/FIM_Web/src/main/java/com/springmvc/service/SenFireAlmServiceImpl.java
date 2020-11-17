package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.modle.util.ObjectMapperUtils;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SenFireAlm;
import com.springmvc.dao.SenDht11Dao;
import com.springmvc.dao.SenFireAlmDao;
import com.springmvc.dto.SenDht11Dto;
import com.springmvc.dto.SenFireAlmDto;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 火災警報感應資料Service實作
 * 
 * @author hrne
 *
 */
@Service("senFireAlmService")
public class SenFireAlmServiceImpl extends BaseServiceImpl<SenFireAlm> implements SenFireAlmService {

	@Autowired
	private ModMainService modMainService;

	@Autowired
	private ModRespLogService modRespLogService;

	@Autowired
	private SenFireAlmDao senFireAlmDao;

	public void save_respJson(ModMain modMain, ModSen modSen, String respJson) {

		// 將回傳資料轉成json
		JSONObject obj = new JSONObject(respJson);

		try {
			// 取出火光警示狀態
			BigDecimal fireStatus = obj.getBigDecimal("fire_status");
			// 取出火光警示狀態
			BigDecimal mq7Status = obj.getBigDecimal("mq7_status");

			// 產生資料
			SenFireAlm senFireAlm = new SenFireAlm();
			senFireAlm.setModMain(modMain);
			senFireAlm.setFireStatus(fireStatus);
			senFireAlm.setMq7Status(mq7Status);

			// 將資料寫入DB
			create(senFireAlm);

			// 儲存成功紀錄，00:連線正常
			modRespLogService.save_modData_modSen(modMain, modSen, "00", obj.toString());

		} catch (Exception e) {
			// 回傳資料若其中有一筆空值，則不寫入並儲存錯誤訊息紀錄，02:讀取不到感應模組資料
			modRespLogService.save_modData_modSen(modMain, modSen, "02", obj.toString());
		}
	}

	public List<SenFireAlm> find_latest_modMain() {

		// 查詢所有啟用的感應裝置主檔
		List<ModMain> modMainList = modMainService.find_modEnabled();

		List<SenFireAlm> senFireAlmList = new ArrayList<SenFireAlm>();

		List<SenFireAlm> results = new ArrayList<SenFireAlm>();

		for (ModMain modMain : modMainList) {

			// 判斷感應裝置是否有設定火災警報感應模組
			for (ModSen modSen : modMain.getModSenSet()) {
				if (modSen.getSenCode().equals("fireAlm")) {
					// 查詢感應裝置火災警報列表，依據更新日期排序
					senFireAlmList = senFireAlmDao.find_modMainId_desc(modMain.getId());

					// 判斷是否有資料
					if (!CollectionUtils.isEmpty(senFireAlmList)) {
						// 取最新一筆放入
						results.add(senFireAlmList.get(0));
					}
				}
			}
		}
		return results;
	}

	public List<SenFireAlmDto> find_show_page() {
		// 查詢火災警報感應資料
		List<SenFireAlm> senFireAlmList = find_latest_modMain();

		// 將火災警報感應資料map到dto上供頁面顯示
		List<SenFireAlmDto> senFireAlmDtoList = ObjectMapperUtils.mapAll(senFireAlmList, SenFireAlmDto.class);
		return senFireAlmDtoList;

	}
	
	public List<SenFireAlmDto> find_show_chart(int modMainId, Date startDate, Date endDate) {

		List<SenFireAlm> senFireAlmList = new ArrayList<SenFireAlm>();

		List<SenFireAlmDto> senFireAlmDtoList = new ArrayList<SenFireAlmDto>();

		// 查詢感應裝置火災警報感應列表，依據更新日期排序
		senFireAlmList = senFireAlmDao.find_modMainId_date_desc(modMainId, startDate, endDate);

		for (int i = 0; i < senFireAlmList.size(); i++) {
			if (senFireAlmList.get(i) != null) {
				// 將火災警報感應資料map到dto上
				SenFireAlmDto senFireAlmDto = ObjectMapperUtils.map(senFireAlmList.get(i), SenFireAlmDto.class);
				senFireAlmDtoList.add(senFireAlmDto);
			}
		}

		return senFireAlmDtoList;
	}

}
