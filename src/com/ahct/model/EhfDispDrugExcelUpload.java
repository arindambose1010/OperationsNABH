package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Chandana - 8251 - The persistent class for the EHF_DISP_DRUG_EXCEL_UPLOAD database table.
 * 
 */
@Entity
@Table(name="EHF_DISP_DRUG_EXCEL_UPLOAD")
public class EhfDispDrugExcelUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "SEQ_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehf_disp_drug_exl_upd_seq_generator")
	@SequenceGenerator(name = "ehf_disp_drug_exl_upd_seq_generator", sequenceName = "EHF_DISP_DRUG_EXCEL_UPLOAD_SEQ", allocationSize = 1)
    private Long seqId;
    
	@Column(name="DISP_ID")
	private String dispId;
	
	@Column(name="MONTH_YEAR")
	private String monthYear;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPLOAD_DT")
	private Date uploadDt;
	
	@Column(name="FILE_LINK")
	private String fileLink;
	
	@Column(name="FILE_NAME")
	private String fileName;
	
	@Column(name="FILE_PATH")
	private String filePath;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;
	
	@Column(name="CRT_USR")
	private String crtUsr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;
	
	@Column(name="ACTIVE_YN")
	private String activeYn;

	public Long getSeqId() {
		return seqId;
	}

	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	
	public String getDispId() {
		return dispId;
	}

	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public Date getUploadDt() {
		return uploadDt;
	}

	public void setUploadDt(Date uploadDt) {
		this.uploadDt = uploadDt;
	}

	public String getFileLink() {
		return fileLink;
	}

	public void setFileLink(String fileLink) {
		this.fileLink = fileLink;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	
}