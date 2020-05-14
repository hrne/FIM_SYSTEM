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
 * 火災警報感應資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_FireAlm")
public class SenFireAlm {

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
	 * 火光警示:1:有火光、0:安全
	 */
	@Column(name = "fire_status")
	private BigDecimal fireStatus;
	
	/**
	 * 一氧化碳警示:1:超標、0:安全
	 */
	@Column(name = "mq7_status")
	private BigDecimal mq7Status;

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
	 * 感應裝置主檔id
	 */
	public ModMain getModMain() {
		return modMain;
	}

	public void setModMain(ModMain modMain) {
		this.modMain = modMain;
	}

	/**
	 * 火光警示:1:有火光、0:安全
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

}