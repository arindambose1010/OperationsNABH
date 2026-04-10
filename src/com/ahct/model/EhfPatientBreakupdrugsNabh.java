package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHF_PATIENT_BREAKUPDRUGS_NABH")
public class EhfPatientBreakupdrugsNabh implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfPatientBreakupdrugsNabhPK id;
	private String activeYN;
	private String patientId;
	private String hospId;
	private String crtUser;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private Float drugQuantity;
	private Float drugAmount;
	
	
 @EmbeddedId
  @AttributeOverrides( {
      @AttributeOverride(name="caseId", column=@Column(name="CASE_ID", nullable=false)) , 
      @AttributeOverride(name="drugId", column=@Column(name="DRUG_ID", nullable=false)),
      @AttributeOverride(name="unitPrice", column=@Column(name="UNIT_PRICE", nullable=false))
    } )
public EhfPatientBreakupdrugsNabhPK getId() {
    return id;
}
 @Column(name = "DRUG_AMOUNT")
 public Float getDrugAmount() {
	return drugAmount;
}

public void setDrugAmount(Float drugAmount) {
	this.drugAmount = drugAmount;
}

@Column(name = "ACTIVE_YN")
public String getActiveYN() {
	return activeYN;
}


public void setActiveYN(String activeYN) {
	this.activeYN = activeYN;
}

@Column(name = "PATIENT_ID")
public String getPatientId() {
	return patientId;
}


public void setPatientId(String patientId) {
	this.patientId = patientId;
}

@Column(name = "HOSP_ID")
public String getHospId() {
	return hospId;
}


public void setHospId(String hospId) {
	this.hospId = hospId;
}

@Column(name = "CRT_USR")
public String getCrtUser() {
	return crtUser;
}


public void setCrtUser(String crtUser) {
	this.crtUser = crtUser;
}

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "CRT_DT")
public Date getCrtDt() {
	return crtDt;
}


public void setCrtDt(Date crtDt) {
	this.crtDt = crtDt;
}

/*
public String getLstUpdUser() {
	return lstUpdUser;
}

@Column(name = "LST_UPD_USR")
public void setLstUpdUser(String lstUpdUser) {
	this.lstUpdUser = lstUpdUser;
}*/

@Temporal(TemporalType.TIMESTAMP)
@Column(name = "LST_UPD_DT")
public Date getLstUpdDt() {
	return lstUpdDt;
}


public void setLstUpdDt(Date lstUpdDt) {
	this.lstUpdDt = lstUpdDt;
}

@Column(name = "DRUG_QUANTITY")
public Float getDrugQuantity() {
	return drugQuantity;
}


public void setDrugQuantity(Float drugQuantity) {
	this.drugQuantity = drugQuantity;
}


public void setId(EhfPatientBreakupdrugsNabhPK id) {
	this.id = id;
}
@Column(name = "LST_UPD_USR")
public String getLstUpdUsr() {
	return lstUpdUsr;
}
public void setLstUpdUsr(String lstUpdUsr) {
	this.lstUpdUsr = lstUpdUsr;
}
		
}
