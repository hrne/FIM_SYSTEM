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
 * 溫濕度dht11感應資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Dht11")
public class SenDht11 {

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
	 * 濕度
	 */
	@Column(name = "humidity", precision = 5, scale = 2)
	private BigDecimal humidity;

	/**
	 * 溫度(攝氏C)
	 */
	@Column(name = "temp_cal", precision = 5, scale = 2)
	private BigDecimal tempCal;

	/**
	 * 溫度(華氏F)
	 */
	@Column(name = "temp_fah", precision = 5, scale = 2)
	private BigDecimal tempFah;

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

}