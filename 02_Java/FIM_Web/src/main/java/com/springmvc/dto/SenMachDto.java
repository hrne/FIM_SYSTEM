package com.springmvc.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.springmvc.entity.SenMod;

/**
 * 感應器Dto
 * 
 * @author hrne
 *
 */
public class SenMachDto {

	/**
	 * 感應器id
	 */
	private int id;

	/**
	 * 感應器名稱
	 */
	private String machName;

	/**
	 * ip位址
	 */
	private String ip;

	/**
	 * 感應器是否啟用
	 */
	private boolean machEnable = true;

	/**
	 * 與感應模組的關聯
	 */
	private Set<SenMod> senModSet;

	/**
	 * 感應器id
	 */
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 感應器名稱
	 */
	public String getMachName() {
		return machName;
	}

	public void setMachName(String machName) {
		this.machName = machName;
	}

	/**
	 * ip位址
	 */
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * 感應器是否啟用
	 */
	public boolean isMachEnable() {
		return machEnable;
	}

	public void setMachEnable(boolean machEnable) {
		this.machEnable = machEnable;
	}

	public Set<SenMod> getSenModSet() {
		return senModSet;
	}

	public void setSenModSet(Set<SenMod> senModSet) {
		this.senModSet = senModSet;
	}

	// 頁面使用
	/**
	 * 依據machEnable顯示:啟用/關閉
	 * 
	 * @return
	 */
	public String getShonEnableName() {
		if (machEnable) {
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
	 * 顯示感應裝置清單
	 * 
	 * @return
	 */
	public List<String> getShonSenModList() {
		List<String> senModList = new ArrayList<String>();
		for (SenMod bo : senModSet) {
			senModList.add(bo.getMachName());
		}
		return senModList;
	}

	/**
	 * 紀錄感應裝置ID
	 */
	private List<Integer> senModsID;

	public List<Integer> getSenModsID() {
		return senModsID;
	}

	public void setSenModsID(List<Integer> senModsID) {
		this.senModsID = senModsID;
	}

}
