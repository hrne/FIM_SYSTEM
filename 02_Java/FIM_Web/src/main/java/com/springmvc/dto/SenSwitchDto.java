package com.springmvc.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
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
import com.springmvc.entity.ModSen;

/**
 * 電源開關感應資料Dto
 * 
 * @author hrne
 *
 */
public class SenSwitchDto {

	private int id;

	/**
     * 感應裝置id
     */
	@JsonIgnore
    private ModMain modData;

	/**
	 * 電源開關狀態，1:通電、0:關閉
	 */
	private int powStatus;

	/**
	 * 電池電力(v)
	 */
	private BigDecimal batteryVolt;

	/**
	 * 更新時間，透過SQL自動產生
	 */
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
     * 感應裝置id
     */
	public ModMain getModData() {
		return modData;
	}

	public void setModData(ModMain modData) {
		this.modData = modData;
	}

	/**
	 * 電源開關狀態，1:通電、0:關閉
	 */
	public int getPowStatus() {
		return powStatus;
	}

	public void setPowStatus(int powStatus) {
		this.powStatus = powStatus;
	}

	/**
	 * 電池電力(v)
	 */
	public BigDecimal getBatteryVolt() {
		return batteryVolt;
	}

	public void setBatteryVolt(BigDecimal batteryVolt) {
		this.batteryVolt = batteryVolt;
	}

	/**
	 * 更新時間，透過SQL自動產生
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	// 頁面使用
	/**
	 * 感應裝置名稱
	 */
	public String getSenName() {
		return getModData().getModName();
	}
}
