package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Chandana - 8251 - The persistent class for the EHF_DISP_DRUG_QTY_UPLOAD database table.
 * 
 */
@Entity
@Table(name="EHF_DISP_DRUG_QTY_UPLOAD")
public class EhfDispDrugQtyUpload implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="DRUG_CODE")
	private String drugCode;
	
	@Column(name="DISP_ID")
	private String dispId;

	@Column(name="BATCH_CODE")
	private String batchCode;
	
	@Temporal(TemporalType.DATE)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;
	
	@Column(name="MONTH_YEAR")
	private String monthYear;
	
	@Column(name="ACTUAL_QUANTITY")
	private Long actualQuantity;
	
	@Column(name="PHYSICAL_QUANTITY")
	private Long physicalQuantity;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPD_DT")
	private Date updDt;
	
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

	@Column(name="REMARKS")
	private String remarks;
	
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDispId() {
		return dispId;
	}

	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

	public String getBatchCode() {
		return batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getExpiryDt() {
		return expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public String getMonthYear() {
		return monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}
	
	public Long getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Long actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	public Long getPhysicalQuantity() {
		return physicalQuantity;
	}

	public void setPhysicalQuantity(Long physicalQuantity) {
		this.physicalQuantity = physicalQuantity;
	}

	public Date getUpdDt() {
		return updDt;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
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

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}