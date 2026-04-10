package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_WC_INDENT_AUDIT database table.
 * 
 */
@Entity
@Table(name="EHF_WC_INDENT_AUDIT")
@NamedQuery(name="EhfWcIndentAudit.findAll", query="SELECT e FROM EhfWcIndentAudit e")
public class EhfWcIndentAudit implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="ACT_ORDER")
	private String actOrder;

	@Column(name="ACTIVE_YN")
	private String activeYn;

	@Column(name="AVAILABLE_COUNT")
	private String availableCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="DISP_ID")
	private String dispId;
	
	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="DRUG_NAME")
	private String drugName;

	@Column(name="IDEAL_STOCK")
	private String idealStock;

	@Column(name="INDENT_COUNT")
	private String indentCount;

	@Column(name="INDENT_ID")
	private String indentId;
	
	@Column(name="SNO")
	private Long sno;
	
	public Long getSno() {
		return sno;
	}

	public void setSno(Long sno) {
		this.sno = sno;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="PENDING_INDENT")
	private String pendingIndent;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PO_DATE")
	private Date poDate;

	@Column(name="PO_ID")
	private String poId;


	private String status;

	@Column(name="VENDOR_ID")
	private String vendorId;

	@Column(name="REMARKS")
	private String remarks;

	public EhfWcIndentAudit() {
	}

	public String getActOrder() {
		return this.actOrder;
	}

	public void setActOrder(String actOrder) {
		this.actOrder = actOrder;
	}

	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}

	public String getAvailableCount() {
		return this.availableCount;
	}

	public void setAvailableCount(String availableCount) {
		this.availableCount = availableCount;
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

	public String getDispId() {
		return this.dispId;
	}

	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

	public String getDrugCode() {
		return this.drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getIdealStock() {
		return this.idealStock;
	}

	public void setIdealStock(String idealStock) {
		this.idealStock = idealStock;
	}

	public String getIndentCount() {
		return this.indentCount;
	}

	public void setIndentCount(String indentCount) {
		this.indentCount = indentCount;
	}

	public String getIndentId() {
		return this.indentId;
	}

	public void setIndentId(String indentId) {
		this.indentId = indentId;
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

	public String getPendingIndent() {
		return this.pendingIndent;
	}

	public void setPendingIndent(String pendingIndent) {
		this.pendingIndent = pendingIndent;
	}

	public Date getPoDate() {
		return this.poDate;
	}

	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}

	public String getPoId() {
		return this.poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	

}