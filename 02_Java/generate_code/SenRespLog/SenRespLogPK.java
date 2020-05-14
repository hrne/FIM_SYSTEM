package com.springmvc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 溫濕度dht11感應資料pk
 * 
 * @author hrne
 *
 */
@Embeddable
public class SenDht11PK implements Serializable{
	
	/**
	 * 工具機資料id
	 */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="sen_mach_id")
    private SenRespLog SenRespLog;
	
	/**
	 * 工具機資料id
	 */
	public SenRespLog getSenRespLog() {
		return SenRespLog;
	}

	/**
	 * 工具機資料id
	 */
	public void setSenRespLog(SenRespLog SenRespLog) {
		this.SenRespLog = SenRespLog;
	}




}
