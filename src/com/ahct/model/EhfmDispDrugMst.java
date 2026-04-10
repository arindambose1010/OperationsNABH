package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_DISP_DRUG_MST")

public class EhfmDispDrugMst implements Serializable {
	
	private String drugId;
	private String drugType;
	private String drugName;
	private String price;
	private String activeYn;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	
	@Id
	@Column(name="DRUG_ID", nullable=false)
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	@Column(name="DRUG_TYPE", nullable=true)
	public String getDrugType() {
		return drugType;
	}
	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}
	@Column(name="DRUG_NAME", nullable=true)
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	@Column(name="PRICE", nullable=true)
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	@Column(name="ACTIVE_YN", nullable=true)
	public String getActiveYn() {
		return activeYn;
	}
	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="CRT_USR", nullable=true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="LST_UPD_USR", nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	

}
