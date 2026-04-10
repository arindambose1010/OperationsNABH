package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the EHF_WC_INDENT database table.
 * 
 */
@Entity
@Table(name="EHF_WC_INDENT")
public class EhfWcIndent implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfWcIndentPK id;

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
	
	@Column(name="FURTHER_INDENT_TO_BE_PLACED")
	private String furtherIndentToBePlaced;
	@Column(name="RATE_CONTRACT_PRICE")
	private String rateContractPrice;
	@Column(name="INDENT_VALUE")
	private String indentValue;

	@Column(name="DRUG_NAME")
	private String drugName;

	@Column(name="IDEAL_STOCK")
	private String idealStock;

	@Column(name="INDENT_COUNT")
	private String indentCount;
	
	@Column(name="INDENT_COUNT_CONSTANT")
	private String indentCountConstant;
	@Temporal(TemporalType.DATE)
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

	@Column(name="STATUS")
	private String status;

	
	@Column(name="SNO")
	private String sno;
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="MNFCTR_ID")
    private String mnfctrId;

	@Column(name="DSTRBTR_ID")
    private String dstrbtrId;

   @Column(name="DSTRBTR_NAME")
    private String dstrbtrName;


   @Column(name="MNFCTR_NAME")
    private String mnfctrName;
	
   @Column(name="RECEIVED_QTY")
   private String receivedQty;
   
   @Column(name="BATCH_NO")
   private String batchNo;
   
   @Column(name="EXPIRY_DATE")
   private String expiryDate;
   
   @Column(name="INVOICE_NO")
   private String invoiceNo;
   
   @Column(name="INVOICE_DATE")
   private String invoiceDate;
   
   @Column(name="ITEM_ID")
   private String itemId;
   
   @Column(name="DRUG_TYPE")
   private String drugTyp;
   
   @Column(name="PO_ID1")
	private String poId1;

   @Column(name="INDENT_STATUS")
	private String indentStatus; 
   
	public String getPoId1() {
	return poId1;
}

public void setPoId1(String poId1) {
	this.poId1 = poId1;
}

	public String getItemId() {
	return itemId;
}

public void setItemId(String itemId) {
	this.itemId = itemId;
}

public String getDrugTyp() {
	return drugTyp;
}

public void setDrugTyp(String drugTyp) {
	this.drugTyp = drugTyp;
}

	public EhfWcIndentPK getId() {
		return id;
	}

	public String getReceivedQty() {
		return receivedQty;
	}

	public void setReceivedQty(String receivedQty) {
		this.receivedQty = receivedQty;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	
	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public void setId(EhfWcIndentPK id) {
		this.id = id;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public EhfWcIndent() {
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

	
	
	public String getIndentCountConstant() {
		return indentCountConstant;
	}

	public void setIndentCountConstant(String indentCountConstant) {
		this.indentCountConstant = indentCountConstant;
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

	/*public String getVendorId() {
		return this.vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}*/
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getFurtherIndentToBePlaced() {
		return furtherIndentToBePlaced;
	}

	public void setFurtherIndentToBePlaced(String furtherIndentToBePlaced) {
		this.furtherIndentToBePlaced = furtherIndentToBePlaced;
	}

	public String getRateContractPrice() {
		return rateContractPrice;
	}

	public void setRateContractPrice(String rateContractPrice) {
		this.rateContractPrice = rateContractPrice;
	}

	public String getIndentValue() {
		return indentValue;
	}

	public void setIndentValue(String indentValue) {
		this.indentValue = indentValue;
	}

	public String getMnfctrId() {
		return mnfctrId;
	}

	public void setMnfctrId(String mnfctrId) {
		this.mnfctrId = mnfctrId;
	}

	public String getDstrbtrId() {
		return dstrbtrId;
	}

	public void setDstrbtrId(String dstrbtrId) {
		this.dstrbtrId = dstrbtrId;
	}

	public String getDstrbtrName() {
		return dstrbtrName;
	}

	public void setDstrbtrName(String dstrbtrName) {
		this.dstrbtrName = dstrbtrName;
	}

	public String getMnfctrName() {
		return mnfctrName;
	}

	public void setMnfctrName(String mnfctrName) {
		this.mnfctrName = mnfctrName;
	}
	public String getIndentStatus() {
		return indentStatus;
	}

	public void setIndentStatus(String indentStatus) {
		this.indentStatus = indentStatus;
	}
	
}