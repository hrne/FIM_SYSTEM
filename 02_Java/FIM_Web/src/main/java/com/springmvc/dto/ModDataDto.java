package com.springmvc.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.springmvc.entity.ModSen;

/**
 * 感應裝置Dto
 * 
 * @author hrne
 *
 */
public class ModDataDto {

	/**
	 * 感應裝置id
	 */
	private int id;

	/**
	 * 感應裝置名稱
	 */
	private String modName;

	/**
	 * ip位址
	 */
	private String ipAddress;

	/**
	 * 感應裝置是否啟用,1啟用、0關閉
	 */
	private boolean modEnable = true;

	/**
	 * 感應裝置使用模組
	 */
	private Set<ModSen> modSenSet;

	/**
	 * 感應裝置id
	 */
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
	 * 感應裝置使用模組
	 */
	public Set<ModSen> getModSenSet() {
		return modSenSet;
	}

	public void setModSenSet(Set<ModSen> modSenSet) {
		this.modSenSet = modSenSet;
	}

	// 頁面使用
	/**
	 * 依據modEnable顯示:啟用/關閉
	 * 
	 * @return
	 */
	public String getShonEnableName() {
		if (isModEnable()) {
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
	 * 顯示使用感應模組清單
	 * 
	 * @return modSen list
	 */
	public List<String> getShonModSenList() {
		List<String> modSenList = new ArrayList<String>();
		for (ModSen modSen : getModSenSet()) {
			modSenList.add(modSen.getSenName());
		}
		return modSenList;
	}

	/**
	 * 紀錄感應模組ID
	 */
	private List<Integer> modSenIDs;

	/**
	 * 紀錄感應模組ID
	 */
	public List<Integer> getModSenIDs() {
		return modSenIDs;
	}

	public void setModSenIDs(List<Integer> modSenIDs) {
		this.modSenIDs = modSenIDs;
	}

}
