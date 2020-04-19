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
 * 感應器感應紀錄
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Resp_Log")
public class SenRespLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
	/**
     * 感應器資料id
     */
    @Column(name = "sen_mach_id")
    private int senMachId;
    
    /**
     * 查詢是否成功
     */
    @Column(name = "suc_status", nullable = false)
    private boolean sucStatus = true;

    /**
     * 回傳訊息
     */
    @Column(name = "resp_message")
    private String respMessage;
    
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
     * 感應器資料id
     */
    public int getSenMachId() {
		return senMachId;
	}

	public void setSenMachId(int senMachId) {
		this.senMachId = senMachId;
	}

	/**
     * 查詢是否成功
     */
	public boolean isSucStatus() {
		return sucStatus;
	}

	public void setSucStatus(boolean sucStatus) {
		this.sucStatus = sucStatus;
	}

    /**
     * 回傳訊息
     */
	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
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