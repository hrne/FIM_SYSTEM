package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.modle.service.BaseServiceImpl;
import com.springmvc.dao.SenMachDAO;
import com.springmvc.entity.SenMach;

@Service
public class SenMachServiceImpl extends BaseServiceImpl<SenMach> implements SenMachService {


}
