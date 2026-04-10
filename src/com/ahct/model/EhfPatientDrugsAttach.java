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
@Table(name="EHF_PATIENT_DRUGS_ATTACH")
public class EhfPatientDrugsAttach implements Serializable {
	
	
	private String caseId;
	private String fileName;
	private String filePath;
	private String patientId;
	private Date crtDt;

	
	public EhfPatientDrugsAttach() {
		super();
	}
	
	public EhfPatientDrugsAttach(String caseId, String fileName,
			String filePath,Date crtDt) {
		super();
		this.caseId = caseId;
		this.fileName = fileName;
		this.filePath = filePath;
		this.crtDt = crtDt;
	}



	@Id
	@Column(name="CASE_ID")
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name="FILENAME")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="FILEPATH")
	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
}
