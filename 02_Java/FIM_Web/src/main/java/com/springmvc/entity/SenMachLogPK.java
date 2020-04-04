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
 * 工具機資料更新紀錄pk
 * 
 * @author hrne
 *
 */
@Embeddable
public class SenMachLogPK implements Serializable{
	
	/**
	 * 工具機資料id
	 */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="sen_mach_id")
    private SenMach senMach;
	
	/**
	 * 工具機資料id
	 */
	public SenMach getSenMach() {
		return senMach;
	}

	/**
	 * 工具機資料id
	 */
	public void setSenMach(SenMach senMach) {
		this.senMach = senMach;
	}




}
