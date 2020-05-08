package com.springmvc.service;

import org.springframework.stereotype.Service;
import com.modle.service.BaseServiceImpl;
import com.springmvc.entity.ModUpdateLog;

/**
 * 感應裝置更新紀錄的Service實做
 * 
 * @author hrne
 *
 */
@Service("modDataLogService")
public class ModDataLogServiceImpl extends BaseServiceImpl<ModUpdateLog> implements ModDataLogService {


}
