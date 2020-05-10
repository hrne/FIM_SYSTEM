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
 * 電源開關感應資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Switch")
public class SenSwitch {

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
	 * 電源狀態:1:打開、0:關閉
	 */
	@Column(name = "pow_status")
	private boolean powStatus;

	/**
	 * 電池電力(V)
	 */
	@Column(name = "battery_volt", precision = 5, scale = 2)
	private BigDecimal batteryVolt;

	/**
	 * 更新時間，透過SQL自動產生
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", insertable = false, updatable = false)
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
	 * 電源開關狀態，1:通電、0:關閉
	 */
	public boolean isPowStatus() {
		return powStatus;
	}

	public void setPowStatus(boolean powStatus) {
		this.powStatus = powStatus;
	}
	
	/**
	 * 電池電力(V)
	 */
	public BigDecimal getBatteryVolt() {
		return batteryVolt;
	}

	public void setBatteryVolt(BigDecimal batteryVolt) {
		this.batteryVolt = batteryVolt;
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