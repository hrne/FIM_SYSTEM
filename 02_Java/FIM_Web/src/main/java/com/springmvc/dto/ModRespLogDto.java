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
import com.springmvc.entity.ModParmData;
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
	public String getModMainName() {
		return getModMain().getModName();
	}

	/**
	 * 模組名稱
	 */
	public String getModSenName() {
		return getModSen().getSenName();
	}

	/**
	 * 代碼說明
	 */
	public String getRespStatusName() {
		return getSysRespStatus().getStatusName();
	}

	/**
	 * 正常狀態顏色:黑色
	 */
	private String classNor = "text-body";

	/**
	 * 異常狀態顏色:紅色
	 */
	private String classNorDan = "p-3 mb-2 bg-danger text-white";

	/**
	 * 正常狀態顏色:黑色
	 */
	public String getClassNor() {
		return classNor;
	}

	public void setClassNor(String classNor) {
		this.classNor = classNor;
	}

	/**
	 * 異常狀態顏色:紅色
	 */
	public String getClassNorDan() {
		return classNorDan;
	}

	public void setClassNorDan(String classNorDan) {
		this.classNorDan = classNorDan;
	}

	/**
	 * 依據感應紀錄代碼顯示
	 * 
	 * @return
	 */
	public String getClassRespStatus() {
		// 判斷代碼不為00:連線正常
		if (!getSysRespStatus().getStatusCode().equals("00")) {
			// 異常顯示
			return getClassNorDan();

		}
		// 正常顯示
		return getClassNor();
	}
}
