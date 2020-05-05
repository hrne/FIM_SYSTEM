package com.springmvc.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.springmvc.entity.ModSen;

/**
 * 感應模組參數Dto
 * 
 * @author hrne
 *
 */
public class ModParmDto {

    private int id;
    
	/**
	 * 感應模組id
	 */
    private ModSen modSen;
    
    /**
     * 參數名稱
     */
    private String parmName;
    
    /**
     * 參數代號
     */
    private String parmCode;
    
	/**
	 * 上限警示值
	 */
	private BigDecimal upperLimit;
	
	/**
	 * 下限警示值
	 */
	private BigDecimal lowerLimit;

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
     * 更新時間，透過SQL自動產生
     */
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
