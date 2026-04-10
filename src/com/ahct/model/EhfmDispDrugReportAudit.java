package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table( name = "EHFM_DISP_DRUG_AUDIT" ) 
public class EhfmDispDrugReportAudit implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private EhfmDispDrugReportAuditCompKey id;	
		
	@Column(name="CRT_DATE")
	private Date crtDate;	
	
	@Column(name="QUANTITY")
	private String quantity;
	
	@Column(name="PHARMACIST_ID")
	private String pharmacistId;
	
	@Column(name="DISP_CODE")
	private String dispCode;
	
	@Column(name="INVOICE_NUM")
	private String invoiceNum;
	
	@Column(name="INVOICE_DT")
	private Date invoiceDt;
	
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;
	
	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	public EhfmDispDrugReportAuditCompKey getId() {
		return id;
	}

	public void setId(EhfmDispDrugReportAuditCompKey id) {
		this.id = id;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPharmacistId() {
		return pharmacistId;
	}

	public void setPharmacistId(String pharmacistId) {
		this.pharmacistId = pharmacistId;
	}

	public String getDispCode() {
		return dispCode;
	}

	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}

	public String getInvoiceNum() {
		return invoiceNum;
	}

	public void setInvoiceNum(String invoiceNum) {
		this.invoiceNum = invoiceNum;
	}

	public Date getInvoiceDt() {
		return invoiceDt;
	}

	public void setInvoiceDt(Date invoiceDt) {
		this.invoiceDt = invoiceDt;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
	
	
	@Override
	public String toString() {
		return "EhfmDispDrugAudit [id=" + id + ", crtDate=" + crtDate
				+ ", quantity=" + quantity + ", pharmacistId=" + pharmacistId
				+ ", dispCode=" + dispCode + ", invoiceNum=" + invoiceNum
				+ ", invoiceDt=" + invoiceDt + ", lstUpdDt=" + lstUpdDt
				+ ", lstUpdUsr=" + lstUpdUsr + "]";
	}

}
