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
@Table(name = "ehf_disp_drug_mstr_audit")
public class EhfDispDrugMstrAudit implements Serializable{
	
	private Long sno;
	private String caseId;
	private String patientId;
	private String drugCode;
	private Long existingQuant;
	private Long deductedQuant;
	private Long availableQuant;
	private Date crtDate;
	private String crtUsr;
	
	@Id
	@Column(name="sno")
	public Long getSno() {
		return sno;
	}
	public void setSno(Long sno) {
		this.sno = sno;
	}
	
	
	@Column(name="case_id")
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	
	@Column(name="patient_id")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Column(name="drug_code")
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Column(name="existing_quant")
	public Long getExistingQuant() {
		return existingQuant;
	}
	public void setExistingQuant(Long existingQuant) {
		this.existingQuant = existingQuant;
	}
	
	@Column(name="deducted_quant")
	public Long getDeductedQuant() {
		return deductedQuant;
	}
	public void setDeductedQuant(Long deductedQuant) {
		this.deductedQuant = deductedQuant;
	}
	
	@Column(name="available_quant")
	public Long getAvailableQuant() {
		return availableQuant;
	}
	public void setAvailableQuant(Long availableQuant) {
		this.availableQuant = availableQuant;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	
	
	

}
