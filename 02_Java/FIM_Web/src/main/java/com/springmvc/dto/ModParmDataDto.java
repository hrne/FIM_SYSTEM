package com.springmvc.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springmvc.entity.ModSen;

/**
 * 模組參數資料Dto
 * 
 * @author hrne
 *
 */
public class ModParmDataDto {

	private int id;
	
	/**
	 * 感應模組id
	 */
	@JsonIgnore
	private ModSen modSen;


	/**
	 * 參數代號
	 */
	private String parmCode;

	/**
	 * 參數名稱
	 */
	private String parmName;

	/**
	 * 上限警示值
	 */
	private BigDecimal upperLimit;

	/**
	 * 下限警示值
	 */
	private BigDecimal lowerLimit;

	/**
	 * 是否啟用警示:1:啟用、0:停用
	 */
	private boolean limitEnabled = true;

	/**
	 * 是否於修改畫面顯示:1:顯示、0:不顯示只能從DB修正
	 */
	private boolean showEnabled = false;

	/**
	 * 更新時間
	 */
	private Date updateDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	 * 參數名稱
	 */
	public String getParmName() {
		return parmName;
	}

	public void setParmName(String parmName) {
		this.parmName = parmName;
	}

	/**
	 * 參數代號
	 */
	public String getParmCode() {
		return parmCode;
	}

	public void setParmCode(String parmCode) {
		this.parmCode = parmCode;
	}

	/**
	 * 上限警示值
	 */
	public BigDecimal getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

	/**
	 * 下限警示值
	 */
	public BigDecimal getLowerLimit() {
		return lowerLimit;
	}

	public void setLowerLimit(BigDecimal lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	/**
	 * 是否啟用警示:1:啟用、0:停用
	 */
	public boolean isLimitEnabled() {
		return limitEnabled;
	}

	public void setLimitEnabled(boolean limitEnabled) {
		this.limitEnabled = limitEnabled;
	}

	/**
	 * 是否於修改畫面顯示:1:顯示、0:不顯示只能從DB修正
	 */
	public boolean isShowEnabled() {
		return showEnabled;
	}

	public void setShowEnabled(boolean showEnabled) {
		this.showEnabled = showEnabled;
	}

	/**
	 * 更新時間
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
