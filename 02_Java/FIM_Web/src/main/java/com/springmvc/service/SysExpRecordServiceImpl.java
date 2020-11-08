package com.springmvc.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.SenDht11;
import com.springmvc.entity.SysExpRecord;
import com.springmvc.dao.SenDht11Dao;

/**
 * 實驗紀錄資料的Service實做
 * 
 * @author hrne
 *
 */
@Service("sysExpRecordService")
public class SysExpRecordServiceImpl extends BaseServiceImpl<SysExpRecord> implements SysExpRecordService {
	

}
