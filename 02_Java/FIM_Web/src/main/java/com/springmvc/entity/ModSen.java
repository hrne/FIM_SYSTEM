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
@Table(name = "Mod_Sen")
public class ModSen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
    /**
     * 模組代號
     */
    @Column(name = "sen_code")
    private String senCode;
    
    /**
     * 模組名稱
     */
    @Column(name = "sen_name")
    private String senName;    

    /**
     * 更新時間，透過SQL自動產生
     */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_date",insertable = false, updatable = false)
    private Date updateDate;
	
	/**
	 * 感應裝置使用模組
	 */
	@ManyToMany(mappedBy = "modSenSet" )
	Set<ModMain> modMainSet;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

    /**
     * 模組代號
     */
	public String getSenCode() {
		return senCode;
	}

	public void setSenCode(String senCode) {
		this.senCode = senCode;
	}
	
    /**
     * 模組名稱
     */
	public String getSenName() {
		return senName;
	}

	public void setSenName(String senName) {
		this.senName = senName;
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
	public Set<ModMain> getModMainSet() {
		return modMainSet;
	}

	public void setModMainSet(Set<ModMain> modMainSet) {
		this.modMainSet = modMainSet;
	}

 
}