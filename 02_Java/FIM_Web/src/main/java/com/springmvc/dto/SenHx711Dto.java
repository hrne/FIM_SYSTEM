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
import com.springmvc.entity.ModParmData;
import com.springmvc.entity.ModSen;

/**
 * 重量hx711感應資料Dto
 * 
 * @author hrne
 *
 */
public class SenHx711Dto {

	private int id;

	/**
	 * 感應裝置主檔id
	 */
	private ModMain modMain;

	/**
	 * 重量(g)
	 */
	private BigDecimal weight;

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
	 * 重量(g)
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
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
	 * 重量(g)參數設定資料
	 */
	private ModParmData modParmDataWeight;

	/**
	 * 重量(g)參數設定資料
	 */
	public ModParmData getModParmDataWeight() {
		return modParmDataWeight;
	}

	public void setModParmDataWeight(ModParmData modParmDataWeight) {
		this.modParmDataWeight = modParmDataWeight;
	}

	/**
	 * 依據警示值顯示重量(g)顏色
	 * 
	 * @return
	 */
	public String getClassWeight() {
		// 判斷是否啟用警示
		if (getModParmDataWeight().isLimitEnabled()) {
			// 判斷重量(g)是否超過警示值上下限
			if (getWeight().compareTo(getModParmDataWeight().getUpperLimit()) >= 0
					|| getWeight().compareTo(getModParmDataWeight().getLowerLimit()) <= 0) {
				// 異常顯示
				return getClassNorDan();
			}
		}
		// 正常顯示
		return getClassNor();
	}

}
