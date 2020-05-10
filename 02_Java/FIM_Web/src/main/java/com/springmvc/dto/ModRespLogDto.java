package com.springmvc.dto;

import java.math.BigDecimal;
import java.util.Date;

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
import com.springmvc.entity.SysRespStatus;

/**
 * 感應紀錄Dto
 * 
 * @author hrne
 *
 */
public class ModRespLogDto {

	private int id;

	/**
	 * 感應裝置主檔id
	 */
	@JsonIgnore
	private ModMain modMain;

	/**
	 * 感應模組id
	 */
	@JsonIgnore
	private ModSen modSen;

	/**
	 * 回傳狀態代碼檔id
	 */
	@JsonIgnore
	private SysRespStatus sysRespStatus;

	/**
	 * 回傳訊息
	 */
	private String respMessage;

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
	 * 感應裝置主檔id
	 */
	public ModMain getModMain() {
		return modMain;
	}

	public void setModMain(ModMain modMain) {
		this.modMain = modMain;
	}

	/**
	 * 感應模組id
	 */
	public ModSen getModSen() {
		return modSen;
	}

	public void setModSen(ModSen modSen) {
		this.modSen = modSen;
	}

	/**
	 * 回傳狀態代碼檔id
	 */
	public SysRespStatus getSysRespStatus() {
		return sysRespStatus;
	}

	public void setSysRespStatus(SysRespStatus sysRespStatus) {
		this.sysRespStatus = sysRespStatus;
	}

	/**
	 * 回傳訊息
	 */
	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
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
	
	/***** 頁面使用 *****/
	/**
	 * 感應裝置id
	 */
	public Integer getModMainId() {
		return getModMain().getId();
	}
	
	/**
	 * 感應裝置名稱
	 */
	public String getSenName() {
		return getModMain().getModName();
	}
	
	/**
	 * 代碼說明
	 */
	public String getRespStatusName() {
		return getSysRespStatus().getStatusName();
	}
}
