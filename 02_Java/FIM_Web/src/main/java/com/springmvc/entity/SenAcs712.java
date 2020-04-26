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
 * 電流acs712感應資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Acs712")
public class SenAcs712 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
	/**
     * 感應裝置id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_data_id")
    private ModData modData;
    
	/**
	 * 電流(安培)
	 */
	@Column(name = "ampere", precision = 5, scale = 2)
	private BigDecimal ampere;

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
     * 感應裝置id
     */
	public ModData getModData() {
		return modData;
	}

	public void setModData(ModData modData) {
		this.modData = modData;
	}

	/**
	 * 電流(安培)
	 */
	public BigDecimal getAmpere() {
		return ampere;
	}

	public void setAmpere(BigDecimal ampere) {
		this.ampere = ampere;
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