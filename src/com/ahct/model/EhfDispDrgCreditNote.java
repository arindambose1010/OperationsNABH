package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHF_DISP_DRG_CREDIT_NOTE")
public class EhfDispDrgCreditNote implements Serializable{
    
	private long sno;
	private String creditRqstId;
	private String purchaseInvoiceNum;
	private String dispId;
	private String dstrbtrId;
	private Date purchaseInvoiceDt;
	private String returnInvoiceNum;
	private Date returnInvoiceDt;
	private Double amount;
	private String crtUsr;
	private Date crtDate;
	private String lstUpdUsr;
	private Date lstUpdDate;
    
	@Id
	@Column(name="SNO")
    public long getSno() {
		return sno;
	}
	public void setSno(long sno) {
		this.sno = sno;
	}
	@Column(name="CREDIT_NOTE_ID")
	public String getCreditRqstId() {
		return creditRqstId;
	}
	public void setCreditRqstId(String creditRqstId) {
		this.creditRqstId = creditRqstId;
	}
	@Column(name="PURCHASE_INVOICE_NO")
	public String getPurchaseInvoiceNum() {
		return purchaseInvoiceNum;
	}
	public void setPurchaseInvoiceNum(String purchaseInvoiceNum) {
		this.purchaseInvoiceNum = purchaseInvoiceNum;
	}
	@Column(name="PURCHASE_INVOICE_DT")
	public Date getPurchaseInvoiceDt() {
		return purchaseInvoiceDt;
	}
	public void setPurchaseInvoiceDt(Date purchaseInvoiceDt) {
		this.purchaseInvoiceDt = purchaseInvoiceDt;
	}
	@Column(name="RETURN_INVOICE_NO")
	public String getReturnInvoiceNum() {
		return returnInvoiceNum;
	}
	public void setReturnInvoiceNum(String returnInvoiceNum) {
		this.returnInvoiceNum = returnInvoiceNum;
	}
	@Column(name="RETURN_INVOICE_DT")
	public Date getReturnInvoiceDt() {
		return returnInvoiceDt;
	}
	public void setReturnInvoiceDt(Date returnInvoiceDt) {
		this.returnInvoiceDt = returnInvoiceDt;
	}
	@Column(name="AMOUNT")
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	@Column(name="DSTRBTR_ID")
	public String getDstrbtrId() {
		return dstrbtrId;
	}
	public void setDstrbtrId(String dstrbtrId) {
		this.dstrbtrId = dstrbtrId;
	}
	
	@Column(name="DISP_ID")
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}
	
	
	
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DATE")
	public Date getCrtDate() {
		return crtDate;
	}
	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}
	
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDate() {
		return lstUpdDate;
	}
	public void setLstUpdDate(Date lstUpdDate) {
		this.lstUpdDate = lstUpdDate;
	}
	
	
}
