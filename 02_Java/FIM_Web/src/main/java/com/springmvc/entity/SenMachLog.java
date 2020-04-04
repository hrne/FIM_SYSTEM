package com.springmvc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 工具機資料更新紀錄
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Mach_Log")
public class SenMachLog {


	/**
	 * 工具機資料id
	 */
	@EmbeddedId
	private SenMachLogPK senMachLogPK;
	
    /**
     * 修改前工具機名稱
     */
    @Column(name = "bef_mach_name")
    private String befMachName;
    
    /**
     * 修改後工具機名稱
     */
    @Column(name = "aft_mach_name")
    private String aftMachName;
    
    /**
     * 修改前ip位址
     */
    @Column(name = "bfe_ip")
    private String bfeIp;
    
    /**
     * 修改後ip位址
     */
    @Column(name = "aft_ip")
    private String aftIp;
    
    /**
     * 修改前是否啟用
     */
    @Column(name = "bfe_mach_enable", nullable = false)
    private boolean bfeMachEnable = true;
    
    /**
     * 修改前是否啟用
     */
    @Column(name = "aft_mach_enable", nullable = false)
    private boolean aftMachEnable = true;
    
    /**
     * 更新時間，透過SQL自動產生
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;

	/**
	 * 工具機資料id
	 */
	public SenMachLogPK getSenMachLogPK() {
		return senMachLogPK;
	}

	public void setSenMachLogPK(SenMachLogPK senMachLogPK) {
		this.senMachLogPK = senMachLogPK;
	}

    /**
     * 修改前工具機名稱
     */
	public String getBefMachName() {
		return befMachName;
	}

	public void setBefMachName(String befMachName) {
		this.befMachName = befMachName;
	}

    /**
     * 修改後工具機名稱
     */
	public String getAftMachName() {
		return aftMachName;
	}

	public void setAftMachName(String aftMachName) {
		this.aftMachName = aftMachName;
	}

    /**
     * 修改前ip位址
     */
	public String getBfeIp() {
		return bfeIp;
	}

	public void setBfeIp(String bfeIp) {
		this.bfeIp = bfeIp;
	}

    /**
     * 修改後ip位址
     */
	public String getAftIp() {
		return aftIp;
	}

	public void setAftIp(String aftIp) {
		this.aftIp = aftIp;
	}

    /**
     * 修改前是否啟用
     */
	public boolean isBfeMachEnable() {
		return bfeMachEnable;
	}

	public void setBfeMachEnable(boolean bfeMachEnable) {
		this.bfeMachEnable = bfeMachEnable;
	}

    /**
     * 修改前是否啟用
     */
	public boolean isAftMachEnable() {
		return aftMachEnable;
	}

	public void setAftMachEnable(boolean aftMachEnable) {
		this.aftMachEnable = aftMachEnable;
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