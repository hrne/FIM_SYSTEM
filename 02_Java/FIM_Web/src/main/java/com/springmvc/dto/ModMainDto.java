package com.springmvc.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springmvc.entity.ModSen;

/**
 * 感應裝置主檔Dto
 * 
 * @author hrne
 *
 */
public class ModMainDto {

	private int id;

	/**
	 * 裝置名稱
	 */
	private String modName;

	/**
	 * ip位址
	 */
	private String ipAddress;

	/**
	 * 是否啟用:1:啟用、0:停用
	 */
	private boolean modEnabled = true;

	/**
	 * 更新時間
	 */
	private Date updateDate;

	/**
	 * 感應裝置使用模組
	 */
	@JsonIgnore
	private Set<ModSen> modSenSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 裝置名稱
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
	 * 是否啟用:1:啟用、0:停用
	 */
	public boolean isModEnabled() {
		return modEnabled;
	}

	public void setModEnabled(boolean modEnabled) {
		this.modEnabled = modEnabled;
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

	/**
	 * 感應裝置使用模組
	 */
	public Set<ModSen> getModSenSet() {
		return modSenSet;
	}

	public void setModSenSet(Set<ModSen> modSenSet) {
		this.modSenSet = modSenSet;
	}

	/***** 頁面使用 *****/
	/**
	 * 依據modEnabled顯示:啟用/關閉
	 * 
	 * @return
	 */
	public String getShowEnabledName() {
		if (isModEnabled()) {
			return "啟用";
		} else {
			return "關閉";
		}
	}

	/**
	 * 判斷是否為新增
	 * 
	 * @return
	 */
	public boolean isNew() {
		return (this.id == 0);
	}

	/**
	 * 顯示感應模組名稱列表
	 * 
	 * @return modSenName list
	 */
	public List<String> getShowModSenNameList() {
		List<String> modMain_list = new ArrayList<String>();
		for (ModSen modSen : getModSenSet()) {
			modMain_list.add(modSen.getSenName());
		}
		return modMain_list;
	}

	/**
	 * 紀錄頁面選擇感應模組id
	 */
	private List<Integer> modSenIdList;

	/**
	 * 紀錄頁面選擇感應模組id
	 */
	public List<Integer> getModSenIdList() {
		return modSenIdList;
	}

	public void setModSenIdList(List<Integer> modSenIdList) {
		this.modSenIdList = modSenIdList;
	}
	
	private String dht11Json;

	public String getDht11Json() {
		return dht11Json;
	}

	public void setDht11Json(String dht11Json) {
		this.dht11Json = dht11Json;
	}

}
