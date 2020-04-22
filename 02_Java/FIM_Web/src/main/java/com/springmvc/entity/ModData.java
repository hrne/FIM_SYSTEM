package com.springmvc.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

/**
 * 感應裝置
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Mod_Data")
public class ModData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 感應裝置名稱
	 */
	@Column(name = "mod_name")
	private String modName;

	/**
	 * ip位址
	 */
	@Column(name = "ip_address")
	private String ipAddress;

	/**
	 * 感應裝置是否啟用,1啟用、0關閉
	 */
	@Column(name = "mod_enable", nullable = false)
	private boolean modEnable = true;

	/**
	 * 更新時間，透過SQL自動產生
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date", insertable = false, updatable = false)
	private Date updateDate;

	/**
	 * 感應裝置使用模組
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "Mod_Data_Sen_R", joinColumns = @JoinColumn(name = "mod_data_id"), inverseJoinColumns = @JoinColumn(name = "mod_sen_id"))
	private Set<ModSen> modSenSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 感應裝置名稱
	 */
	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	/**
	 * ip位址
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * 感應裝置是否啟用,1啟用、0關閉
	 */
	public boolean isModEnable() {
		return modEnable;
	}

	public void setModEnable(boolean modEnable) {
		this.modEnable = modEnable;
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

	/**
	 * 感應裝置使用模組
	 */
	public Set<ModSen> getModSenSet() {
		return modSenSet;
	}

	public void setModSenSet(Set<ModSen> modSenSet) {
		this.modSenSet = modSenSet;
	}

}