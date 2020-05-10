package com.springmvc.entity;

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
 * 感應裝置更新紀錄
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Mod_Update_Log")
public class ModUpdateLog {

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
	 * 修改前裝置名稱
	 */
	@Column(name = "bef_mod_name")
	private String befModName;

	/**
	 * 修改後裝置名稱
	 */
	@Column(name = "aft_mod_name")
	private String aftModName;

	/**
	 * 修改前ip位址
	 */
	@Column(name = "bef_ip_address")
	private String bfeIpAddress;

	/**
	 * 修改後ip位址
	 */
	@Column(name = "aft_ip_address")
	private String aftIpAddress;

	/**
	 * 修改前是否啟用
	 */
	@Column(name = "bfe_mod_enabled")
	private boolean bfeModEnable;

	/**
	 * 修改後是否啟用
	 */
	@Column(name = "aft_mod_enabled")
	private boolean aftModEnable;

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
	 * 修改前裝置名稱
	 */
	public String getBefModName() {
		return befModName;
	}

	public void setBefModName(String befModName) {
		this.befModName = befModName;
	}

	/**
	 * 修改後裝置名稱
	 */
	public String getAftModName() {
		return aftModName;
	}

	public void setAftModName(String aftModName) {
		this.aftModName = aftModName;
	}

	/**
	 * 修改前ip位址
	 */
	public String getBfeIpAddress() {
		return bfeIpAddress;
	}

	public void setBfeIpAddress(String bfeIpAddress) {
		this.bfeIpAddress = bfeIpAddress;
	}

	/**
	 * 修改後ip位址
	 */
	public String getAftIpAddress() {
		return aftIpAddress;
	}

	public void setAftIpAddress(String aftIpAddress) {
		this.aftIpAddress = aftIpAddress;
	}

	/**
	 * 修改前是否啟用
	 */
	public boolean isBfeModEnable() {
		return bfeModEnable;
	}

	public void setBfeModEnable(boolean bfeModEnable) {
		this.bfeModEnable = bfeModEnable;
	}

	/**
	 * 修改後是否啟用
	 */
	public boolean isAftModEnable() {
		return aftModEnable;
	}

	public void setAftModEnable(boolean aftModEnable) {
		this.aftModEnable = aftModEnable;
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