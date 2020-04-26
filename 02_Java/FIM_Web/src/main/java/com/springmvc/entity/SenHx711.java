package com.springmvc.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

/**
 * 重量hx711感應資料
 * 
 * @author hrne
 *
 */
@Entity
@Table(name = "Sen_Hx711")
public class SenHx711 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    
	/**
     * 感應裝置id
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "mod_data_id")
    private ModData modData;
    
	/**
	 * 重量
	 */
	@Column(name = "weight", precision = 5, scale = 2)
	private BigDecimal weight;

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
     * 感應裝置id
     */
	public ModData getModData() {
		return modData;
	}

	public void setModData(ModData modData) {
		this.modData = modData;
	}

	/**
	 * 重量
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
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