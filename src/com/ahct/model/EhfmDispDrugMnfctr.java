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
@Table(name="EHFM_DISP_DRUG_MNFCTR")
public class EhfmDispDrugMnfctr implements Serializable {
	
	private String mnfctrId;
	private String mnfctrName;
	private String crtUsr;
	private Date crtDate;
	
	@Id
	@Column(name="MNFCTR_ID")
	public String getMnfctrId() {
		return mnfctrId;
	}
	public void setMnfctrId(String mnfctrId) {
		this.mnfctrId = mnfctrId;
	}
	@Column(name="MNFCTR_NAME")
	public String getMnfctrName() {
		return mnfctrName;
	}
	public void setMnfctrName(String mnfctrName) {
		this.mnfctrName = mnfctrName;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DATE")
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	

}
