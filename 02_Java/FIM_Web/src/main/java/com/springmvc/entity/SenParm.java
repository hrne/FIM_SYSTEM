package com.springmvc.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 感應模組參數
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Parm")
public class SenParm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
	/**
	 * 感應模組id
	 */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="sen_mod_id")
    private SenMod senMod;
    
    /**
     * 參數名稱
     */
    @Column(name = "parm_name")
    private String parmName;
    
    /**
     * 參數代號
     */
    @Column(name = "parm_code")
    private String parmCode;

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
	 * 感應模組id
	 */
	public SenMod getSenMod() {
		return senMod;
	}

	public void setSenMod(SenMod senMod) {
		this.senMod = senMod;
	}

    /**
     * 參數名稱
     */
	public String getParmName() {
		return parmName;
	}

	public void setParmName(String parmName) {
		this.parmName = parmName;
	}

    /**
     * 參數代號
     */
	public String getParmCode() {
		return parmCode;
	}

	public void setParmCode(String parmCode) {
		this.parmCode = parmCode;
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