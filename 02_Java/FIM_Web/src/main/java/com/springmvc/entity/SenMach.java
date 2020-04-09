package com.springmvc.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.JoinColumn;

/**
 * 工具機資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Mach")
public class SenMach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * 工具機名稱
     */
    @Column(name = "mach_name")
    private String machName;
    
    /**
     * ip位址
     */
    @Column(name = "ip")
    private String ip;
    
    /**
     * 工具機是否啟用
     */
    @Column(name = "mach_enable", nullable = false)
    private boolean machEnable = true;
    
    /**
     * 更新時間，透過SQL自動產生
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;
	
	/**
	 * 與感應模組的關聯
	 */
	@ManyToMany
	@JoinTable(
	  name = "sen_mach_mod_r", 
	  joinColumns = @JoinColumn(name = "sen_mach_id"), 
	  inverseJoinColumns = @JoinColumn(name = "sen_mod_id"))
	private Set<SenMod> senModSet;
	
	/**
	 * 依據machEnable顯示:啟用/關閉
	 */
	@Transient
	private String shonEnableName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    /**
     * 工具機名稱
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
     * 工具機是否啟用
     */
	public boolean isMachEnable() {
		return machEnable;
	}

	public void setMachEnable(boolean machEnable) {
		this.machEnable = machEnable;
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
	 * 與感應模組的關聯
	 */
	public Set<SenMod> getSenModSet() {
		return senModSet;
	}

	public void setSenModSet(Set<SenMod> senModSet) {
		this.senModSet = senModSet;
	}

	/**
	 * 依據machEnable顯示:啟用/關閉
	 */
	public String getShonEnableName() {
		if(isMachEnable()) {
			return "啟用";
		}else {
			return "關閉";
		}
	}

	public void setShonEnableName(String shonEnableName) {
		this.shonEnableName = shonEnableName;
	}

}