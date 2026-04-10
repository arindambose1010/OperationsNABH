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
@Table(name="ehf_lab_reports_data")
public class EhfLabReportsData implements Serializable {

	private static final long serialVersionUID = 1L;
	private EhfLabReportsDataPK id;
	private String investigationId;
	private Date crtDt;
	private String crtUsr;
	private String fieldData;
	private Date lstUpdDt;
	private String lstUpdUsr;
	

	public EhfLabReportsData() {
	}

	
	@EmbeddedId
    @AttributeOverrides({
    @AttributeOverride(name="patientId",column=@Column(name="PATIENT_ID",nullable=false)),
    @AttributeOverride(name="fieldId",column=@Column(name="FIELD_ID",nullable=false))
    })
	public EhfLabReportsDataPK getId() {
		return id;
	}
	public void setId(EhfLabReportsDataPK id) {
		this.id = id;
	}
	
	
	@Column(name="INVESTIGATION_ID")
	public String getInvestigationId() {
		return investigationId;
	}
	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
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
	
	@Column(name="FIELD_DATA")
	public String getFieldData() {
		return fieldData;
	}
	public void setFieldData(String fieldData) {
		this.fieldData = fieldData;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	
	
}
