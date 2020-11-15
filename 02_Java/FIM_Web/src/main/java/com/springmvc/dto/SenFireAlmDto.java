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

import com.springmvc.entity.ModMain;
import com.springmvc.entity.ModSen;

/**
 * 火災警報感應資料Dto
 * 
 * @author hrne
 *
 */
public class SenFireAlmDto {

	private int id;

	/**
	 * 感應裝置主檔id
	 */
	private ModMain modMain;

	/**
	 * 火光警示:0:有火光、1:安全
	 */
	private BigDecimal fireStatus;

	/**
	 * 一氧化碳警示:1:超標、0:安全
	 */
	private BigDecimal mq7Status;

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
	 * 火光警示:0:有火光、1:安全
	 */
	public BigDecimal getFireStatus() {
		return fireStatus;
	}

	public void setFireStatus(BigDecimal fireStatus) {
		this.fireStatus = fireStatus;
	}

	/**
	 * 一氧化碳警示:1:超標、0:安全
	 */
	public BigDecimal getMq7Status() {
		return mq7Status;
	}

	public void setMq7Status(BigDecimal mq7Status) {
		this.mq7Status = mq7Status;
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
	 * 感應裝置名稱
	 */
	public String getModMainName() {
		return getModMain().getModName();
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
	 * 顯示火光警示:0:有火光、1:安全
	 */
	public String getFireStatusName() {
		if (getFireStatus().equals(new BigDecimal(0))) {
			return "有火光";
		} else {
			return "安全";
		}
	}

	/**
	 * 顯示一氧化碳警示:1:超標、0:安全
	 */
	public String getMq7StatusName() {
		if (getMq7Status().equals(new BigDecimal(1))) {
			return "超標";
		} else {
			return "安全";
		}
	}

	/**
	 * 依據火光警示狀態顯示顏色
	 * 
	 * @return
	 */
	public String getClassFireStatus() {
		// 判斷是否有火光
		if (getFireStatus().equals(new BigDecimal(0))) {
			// 異常顯示
			return getClassNorDan();
		}
		// 正常顯示
		return getClassNor();
	}

	/**
	 * 依據一氧化碳警示狀態顯示顏色
	 * 
	 * @return
	 */
	public String getClassMq7Status() {
		// 判斷是否有火光
		if (getMq7Status().equals(new BigDecimal(1))) {
			// 異常顯示
			return getClassNorDan();
		}
		// 正常顯示
		return getClassNor();
	}
}
