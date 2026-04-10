package com.ahct.model;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Date;
@Entity
@Table(name="EHFM_DISP_DRUG_AUDIT")
public class EhfmDispDrugAudit implements Serializable  {

	private EhfmDispDrugAuditId id;
	private Date crtDate;
	private String quantity;
	private String pharmacistId;
	private String dispCode;
	private String invoiceNumber;
	private Date invoiceDate;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String poId;
	private Long seqId;
		
	 @EmbeddedId
	    @AttributeOverrides( {
	        @AttributeOverride(name="actOrder", column=@Column(name="ACT_ORDER", nullable=false, length=5) ), 
	        @AttributeOverride(name="drugId", column=@Column(name="DRUG_ID", nullable=false, length=5) ) } )

	public EhfmDispDrugAuditId getId() {
		return id;
	}
	public void setId(EhfmDispDrugAuditId id) {
		this.id = id;
	}
	
	
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CRT_DATE")
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}	
	
	@Column(name="QUANTITY")
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="PHARMACIST_ID")
	public String getPharmacistId() {
		return pharmacistId;
	}
	public void setPharmacistId(String pharmacistId) {
		this.pharmacistId = pharmacistId;
	}
	
	@Column(name="DISP_CODE")
	public String getDispCode() {
		return dispCode;
	}
	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}
	@Column(name="INVOICE_NUM")
	public String getInvoiceNumber() {
		return invoiceNumber;
	}
	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="INVOICE_DT")
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

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
	
	@Column(name="MNFCTR_PO_ID")
	public String getPoId() {
		return poId;
	}
	
	public void setPoId(String poId) {
		this.poId = poId;
	}
	
	@Column(name="SEQ_ID")
	public Long getSeqId() {
		return seqId;
	}
	
	public void setSeqId(Long seqId) {
		this.seqId = seqId;
	}
	
}
