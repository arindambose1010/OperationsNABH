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
@Table(name="EHFM_DISP_DRUG_DSTRBTR")
public class EhfmDispDrugDstrbtr implements Serializable{
	
	private String dstrbtrId;
	private String dstrbtrName;
	private String crtUsr;
	private Date crtDate;
	
	
	@Id
	@Column(name="DSTRBTR_ID")
	public String getDstrbtrId() {
		return dstrbtrId;
	}
	public void setDstrbtrId(String dstrbtrId) {
		this.dstrbtrId = dstrbtrId;
	}
	
	@Column(name="DSTRBTR_NAME")
	public String getDstrbtrName() {
		return dstrbtrName;
	}
	public void setDstrbtrName(String dstrbtrName) {
		this.dstrbtrName = dstrbtrName;
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
