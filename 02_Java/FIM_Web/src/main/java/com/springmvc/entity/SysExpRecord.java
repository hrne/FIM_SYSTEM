package com.springmvc.entity;

import java.math.BigDecimal;
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
 * 實驗紀錄資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sys_Exp_Record")
public class SysExpRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
	/**
	 * 實驗內容
	 */
	@Column(name = "exp_content")
	private String expContent;

	/**
	 * 次數
	 */
	@Column(name = "ser_number")
	private BigDecimal serNumber;

	/**
	 * 花費時間
	 */
	@Column(name = "cost_time")
	private BigDecimal costTime;

    /**
     * 更新時間，透過SQL自動產生
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;
		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * 實驗內容
	 */
    public String getExpContent() {
		return expContent;
	}

	public void setExpContent(String expContent) {
		this.expContent = expContent;
	}

	/**
	 * 次數
	 */
	public BigDecimal getSerNumber() {
		return serNumber;
	}

	public void setSerNumber(BigDecimal serNumber) {
		this.serNumber = serNumber;
	}

	/**
	 * 花費時間
	 */
	public BigDecimal getCostTime() {
		return costTime;
	}

	public void setCostTime(BigDecimal costTime) {
		this.costTime = costTime;
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