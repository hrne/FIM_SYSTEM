package com.springmvc.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 感應模組
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Mod")
public class SenMod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * 感應模組名稱
     */
    @Column(name = "mod_name")
    private String machName;
    
    /**
     * 感應模組代號
     */
    @Column(name = "mod_code")
    private String machCode;
    
    /**
     * 更新時間，透過SQL自動產生
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;
	
	/**
	 * 與感應器資料的關聯
	 */
	@ManyToMany(mappedBy = "senModSet")
	Set<SenMach> senMachSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    /**
     * 感應模組名稱
     */
	public String getMachName() {
		return machName;
	}

	public void setMachName(String machName) {
		this.machName = machName;
	}

    /**
     * 感應模組代號
     */
    public String getMachCode() {
		return machCode;
	}

	public void setMachCode(String machCode) {
		this.machCode = machCode;
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
	 * 與感應器資料的關聯
	 */
	public Set<SenMach> getSenMachSet() {
		return senMachSet;
	}

	public void setSenMachSet(Set<SenMach> senMachSet) {
		this.senMachSet = senMachSet;
	}
}