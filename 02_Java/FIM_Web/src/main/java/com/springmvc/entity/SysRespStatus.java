package com.springmvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 回傳狀態代碼檔
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sys_Resp_Status")
public class SysRespStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 * 回傳代碼
	 */
	@Column(name = "status_code")
	private String statusCode;

	/**
	 * 代碼說明
	 */
	@Column(name = "status_name")
	private String statusName;

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
	 * 回傳代碼
	 */
	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * 代碼說明
	 */
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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