package com.springmvc.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 * 溫濕度dht11感應資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Dht11")
public class SenDht11 {

	/**
	 * 工具機資料id
	 */
	@EmbeddedId
	private SenDht11PK senDht11PK;

	/**
	 * 濕度
	 */
	@Column(name = "humidity", precision = 5, scale = 2)
	private BigDecimal humidity;

	/**
	 * 溫度(攝氏H)
	 */
	@Column(name = "temp_cal", precision = 5, scale = 2)
	private BigDecimal tempCal;

	/**
	 * 溫度(華氏C)
	 */
	@Column(name = "temp_fah", precision = 5, scale = 2)
	private BigDecimal tempFah;

    /**
     * 更新時間，透過SQL自動產生
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;
	
	/**
	 * 工具機資料id
	 */
	public SenDht11PK getSenDht11PK() {
		return senDht11PK;
	}

	public void setSenDht11PK(SenDht11PK senDht11PK) {
		this.senDht11PK = senDht11PK;
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
	 * 溫度(攝氏H)
	 */
	public BigDecimal getTempCal() {
		return tempCal;
	}

	public void setTempCal(BigDecimal tempCal) {
		this.tempCal = tempCal;
	}

	/**
	 * 溫度(華氏C)
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