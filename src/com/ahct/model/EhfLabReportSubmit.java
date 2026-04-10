package com.ahct.model;

import java.io.Serializable;
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
@Table(name="ehf_lab_report_submit")
public class EhfLabReportSubmit implements Serializable {

	private static final long serialVersionUID = 1L;
	private EhfLabReportSubmitPK id;
	private String isSubmit;
	private Date crtDt;
	private String crtUsr;

	
	
	public EhfLabReportSubmit() {
		// TODO Auto-generated constructor stub
	}


	@EmbeddedId
    @AttributeOverrides({
    @AttributeOverride(name="patientId",column=@Column(name="PATIENT_ID",nullable=false)),
    @AttributeOverride(name="investigationId",column=@Column(name="INVESTIGATION_ID",nullable=false))
    })
	public EhfLabReportSubmitPK getId() {
		return id;
	}



	public void setId(EhfLabReportSubmitPK id) {
		this.id = id;
	}


	@Column(name="IS_SUBMIT")
	public String getIsSubmit() {
		return isSubmit;
	}

	public void setIsSubmit(String isSubmit) {
		this.isSubmit = isSubmit;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}


	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	

}
