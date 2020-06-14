package com.springmvc.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;

/**
 * 溫濕度dht11報表資料Dto
 * 
 * @author hrne
 *
 */
public class ReportDht11Dto {

}
