package com.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SysRespStatus;
import com.springmvc.dao.SysRespStatusDao;

/**
 * 回傳狀態代碼檔Service實作
 * 
 * @author hrne
 *
 */
@Service("sysRespStatusService")
public class SysRespStatusServiceImpl extends BaseServiceImpl<SysRespStatus> implements SysRespStatusService {

	@Autowired
	private SysRespStatusDao sysRespStatusDao;

	public SysRespStatus find_statusCode(String statusCode) {
		return sysRespStatusDao.find_statusCode(statusCode);
	}

}
