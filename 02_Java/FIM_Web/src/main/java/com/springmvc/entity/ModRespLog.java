package com.springmvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 感應紀錄
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Mod_Resp_Log")
public class ModRespLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 感應裝置主檔id
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mod_main_id")
	private ModMain modMain;

	/**
	 * 感應模組id
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "mod_sen_id")
	private ModSen modSen;

	/**
	 * 回傳狀態代碼檔id
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sys_resp_status_id")
	private SysRespStatus sysRespStatus;

	/**
	 * 回傳訊息
	 */
	@Column(name = "resp_message")
	private String respMessage;

	/**
	 * 更新時間，透過SQL自動產生
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", insertable = false, updatable = false)
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

}