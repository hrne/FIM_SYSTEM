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
 * 溫濕度dht11感應資料Dto
 * 
 * @author hrne
 *
 */
public class SenDht11Dto {

	private int id;

	/**
	 * 感應裝置主檔id
	 */
	@JsonIgnore
	private ModMain modMain;

	/**
	 * 濕度
	 */
	private BigDecimal humidity;

	/**
	 * 溫度(攝氏C)
	 */
	private BigDecimal tempCal;

	/**
	 * 溫度(華氏F)
	 */
	private BigDecimal tempFah;

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
	 * 濕度
	 */
	public BigDecimal getHumidity() {
		return humidity;
	}

	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

	/**
	 * 溫度(攝氏C)
	 */
	public BigDecimal getTempCal() {
		return tempCal;
	}

	public void setTempCal(BigDecimal tempCal) {
		this.tempCal = tempCal;
	}

	/**
	 * 溫度(華氏F)
	 */
	public BigDecimal getTempFah() {
		return tempFah;
	}

	public void setTempFah(BigDecimal tempFah) {
		this.tempFah = tempFah;
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
	 * 濕度參數設定資料
	 */
	private ModParmData modParmDataHumidity;

	/**
	 * 溫度(攝氏C)參數設定資料
	 */
	private ModParmData modParmDataTempCal;

	/**
	 * 溫度(華氏F)參數設定資料
	 */
	private ModParmData modParmDataTempFah;

	/**
	 * 濕度參數設定資料
	 */
	public ModParmData getModParmDataHumidity() {
		return modParmDataHumidity;
	}

	public void setModParmDataHumidity(ModParmData modParmDataHumidity) {
		this.modParmDataHumidity = modParmDataHumidity;
	}

	/**
	 * 溫度(攝氏C)參數設定資料
	 */
	public ModParmData getModParmDataTempCal() {
		return modParmDataTempCal;
	}

	public void setModParmDataTempCal(ModParmData modParmDataTempCal) {
		this.modParmDataTempCal = modParmDataTempCal;
	}

	/**
	 * 溫度(華氏F)參數設定資料
	 */
	public ModParmData getModParmDataTempFah() {
		return modParmDataTempFah;
	}

	public void setModParmDataTempFah(ModParmData modParmDataTempFah) {
		this.modParmDataTempFah = modParmDataTempFah;
	}

	/**
	 * 依據警示值顯示濕度顏色
	 * 
	 * @return
	 */
	public String getClassHumidity() {
		// 判斷是否啟用警示
		if (getModParmDataHumidity().isLimitEnabled()) {
			// 判斷濕度是否超過警示值上下限
			if (getHumidity().compareTo(getModParmDataHumidity().getUpperLimit()) >= 0
					|| getHumidity().compareTo(getModParmDataHumidity().getLowerLimit()) <= 0) {
				// 異常顯示
				return getClassNorDan();
			}
		}
		// 正常顯示
		return getClassNor();
	}

	/**
	 * 依據警示值顯示溫度(攝氏C)顏色
	 * 
	 * @return
	 */
	public String getClassTempCal() {

		// 判斷是否啟用警示
		if (getModParmDataTempCal().isLimitEnabled()) {
			// 判斷溫度(攝氏C)是否超過警示值上下限
			if (getTempCal().compareTo(getModParmDataTempCal().getUpperLimit()) >= 0
					|| getTempCal().compareTo(getModParmDataTempCal().getLowerLimit()) <= 0) {
				// 異常顯示
				return getClassNorDan();
			}
		}
		// 正常顯示
		return getClassNor();
	}

	/**
	 * 依據警示值顯示溫度(華氏F)顏色
	 * 
	 * @return
	 */
	public String getClassTempFah() {

		// 判斷是否啟用警示
		if (getModParmDataTempCal().isLimitEnabled()) {
			// 判斷溫度(華氏F)是否超過警示值上下限
			if (getTempFah().compareTo(getModParmDataTempFah().getUpperLimit()) >= 0
					|| getTempFah().compareTo(getModParmDataTempFah().getLowerLimit()) <= 0) {
				// 異常顯示
				return getClassNorDan();
			}
		}
		// 正常顯示
		return getClassNor();
	}
}
