package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Chandana - 8251 - The persistent class for the EHF_DISP_DRUG_QTY_UPLOAD_AUDIT database table.
 * 
 */
@Entity
@Table(name="EHF_DISP_DRUG_QTY_UPLOAD_AUDIT")
@NamedQuery(name="EhfDispDrugQtyUploadAudit.findAll", query="SELECT e FROM EhfDispDrugQtyUploadAudit e")
public class EhfDispDrugQtyUploadAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfDispDrugQtyUploadAuditPK id;

	@Column(name="ACTUAL_QUANTITY")
	private Long actualQuantity;

	@Column(name="BATCH_CODE")
	private String batchCode;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXPIRY_DT")
	private Date expiryDt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="MONTH_YEAR")
	private String monthYear;

	@Column(name="PHYSICAL_QUANTITY")
	private Long physicalQuantity;

	private String remarks;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="UPD_DT")
	private Date updDt;

	public EhfDispDrugQtyUploadAudit() {
	}

	public EhfDispDrugQtyUploadAuditPK getId() {
		return this.id;
	}

	public void setId(EhfDispDrugQtyUploadAuditPK id) {
		this.id = id;
	}

	public Long getActualQuantity() {
		return this.actualQuantity;
	}

	public void setActualQuantity(Long actualQuantity) {
		this.actualQuantity = actualQuantity;
	}

	public String getBatchCode() {
		return this.batchCode;
	}

	public void setBatchCode(String batchCode) {
		this.batchCode = batchCode;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getExpiryDt() {
		return this.expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public String getMonthYear() {
		return this.monthYear;
	}

	public void setMonthYear(String monthYear) {
		this.monthYear = monthYear;
	}

	public Long getPhysicalQuantity() {
		return this.physicalQuantity;
	}

	public void setPhysicalQuantity(Long physicalQuantity) {
		this.physicalQuantity = physicalQuantity;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getUpdDt() {
		return this.updDt;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}

}