package com.ahct.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHF_WC_INDENT_VNDR_GST")
public class EhfWcIndentVndrGst implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfWcVndGstId id;
	
	@Column(name="ACTIVE_YN")
	private String activeYn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="DISP_ID")
	private String dispId;
	
	@Column(name="RATE_CONTRACT_PRICE")
	private String rateContractPrice;

	@Column(name="DRUG_NAME")
	private String drugName;

	@Column(name="INDENT_COUNT")
	private String indentCount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Temporal(TemporalType.DATE)
	@Column(name="PO_DATE")
	private Date poDate;

	@Column(name="PO_ID")
	private String poId;

	@Column(name="STATUS")
	private String status;
	
	@Column(name="INTERNAL_STATUS")
	private String internalStatus;
	
   @Column(name="MNFCTR_NAME")
   private String mnfctrName;
   
   @Column(name="INDENT_VALUE")
   private String indentValue;

   @Column(name="BATCH_NO")
   private String batchNo;
   
   @Column(name="EXPIRY_DATE")
   private String expiryDate;
   
   @Column(name="INVOICE_NO")
   private String invoiceNo;
   
   @Column(name="INVOICE_DATE")
   private String invoiceDate;
   
   @Column(name="INVOICE_AMOUNT")
   private Double invoiceAmount;
   
   @Column(name="GST_SLAB")
   private String gstSlab;
   
   @Column(name="QUANTITY")
   private String quantity;
   
   @Column(name="CGST")
   private Double cgst;
   
   @Column(name="SGST")
   private Double sgst;
   
   @Column(name="CGST_AMOUNT")
   private Double cgstAmount;
   
   @Column(name="SGST_AMOUNT")
   private Double sgstAmount;
   
   @Column(name="GST_AMOUNT")
   private Double gstAmount;
   
   @Column(name="TOTAL_AMOUNT")
   private Double totalAmount;
   
   @Column(name="ROUNDOFF_AMOUNT")
   private Long roundOffAmount;
   
   @Column(name= "VNDR_UPLD_FLG")
   private String vndrFlag;
 
   @Column(name="DRUG_TYPE_ID")
   private String drugTypeId;
   
   @Column(name="MNFCTR_ID")
   private String mnfctrId;
   
   @Column(name="DSTRBTR_ID")
   private String dstrbtrId;
   
   @Column(name="DRUG_ID")
   private String drugId;
   
   @Column(name="DRUG_TYPE")
   private String drugType;
  
   @Column(name="PDF_FILE_NAME")
   private String pdfFileName;
   
   @Column(name="SAVED_PATH")
   private String savedPath;
   
   @Column(name="QUANTITY_FLG")
   private String quantityFlg;
   
   @Column(name="MNFCTR_PO_ID")
	private String poNo;

	public String getPoNo() {
	return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getQuantityFlg() {
	return quantityFlg;
	}
	
	public void setQuantityFlg(String quantityFlg) {
		this.quantityFlg = quantityFlg;
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
	
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
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

	public String getIndentCount() {
		return this.indentCount;
	}

	public void setIndentCount(String indentCount) {
		this.indentCount = indentCount;
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
	
	public String getRateContractPrice() {
		return rateContractPrice;
	}

	public void setRateContractPrice(String rateContractPrice) {
		this.rateContractPrice = rateContractPrice;
	}
	
	public String getMnfctrId() {
		return mnfctrId;
	}

	public void setMnfctrName(String mnfctrId) {
		this.mnfctrId = mnfctrId;
	}

	public Double getInvoiceAmount() {
		return invoiceAmount;
	}

	public void setInvoiceAmount(Double invoiceAmount) {
		this.invoiceAmount = invoiceAmount;
	}

	public String getGstSlab() {
		return gstSlab;
	}

	public void setGstSlab(String gstSlab) {
		this.gstSlab = gstSlab;
	}

	public void setMnfctrId(String mnfctrId) {
		this.mnfctrId = mnfctrId;
	}

	public String getIndentValue() {
		return indentValue;
	}

	public void setIndentValue(String indentValue) {
		this.indentValue = indentValue;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMnfctrName() {
		return mnfctrName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public Double getCgst() {
		return cgst;
	}

	public void setCgst(Double cgst) {
		this.cgst = cgst;
	}

	public Double getSgst() {
		return sgst;
	}

	public void setSgst(Double sgst) {
		this.sgst = sgst;
	}

	public Double getCgstAmount() {
		return cgstAmount;
	}

	public void setCgstAmount(Double cgstAmount) {
		this.cgstAmount = cgstAmount;
	}

	public Double getSgstAmount() {
		return sgstAmount;
	}

	public void setSgstAmount(Double sgstAmount) {
		this.sgstAmount = sgstAmount;
	}

	public Double getGstAmount() {
		return gstAmount;
	}

	public void setGstAmount(Double gstAmount) {
		this.gstAmount = gstAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Long getRoundOffAmount() {
		return roundOffAmount;
	}

	public void setRoundOffAmount(Long roundOffAmount) {
		this.roundOffAmount = roundOffAmount;
	}

	public String getVndrFlag() {
		return vndrFlag;
	}

	public void setVndrFlag(String vndrFlag) {
		this.vndrFlag = vndrFlag;
	}


	public String getDrugTypeId() {
		return drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getDstrbtrId() {
		return dstrbtrId;
	}

	public void setDstrbtrId(String dstrbtrId) {
		this.dstrbtrId = dstrbtrId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getInternalStatus() {
		return internalStatus;
	}

	public void setInternalStatus(String internalStatus) {
		this.internalStatus = internalStatus;
	}


	public EhfWcVndGstId getId() {
		return id;
	}

	public void setId(EhfWcVndGstId id) {
		this.id = id;
	}

	public String getPdfFileName() {
		return pdfFileName;
	}

	public void setPdfFileName(String pdfFileName) {
		this.pdfFileName = pdfFileName;
	}

	public String getSavedPath() {
		return savedPath;
	}

	public void setSavedPath(String savedPath) {
		this.savedPath = savedPath;
	}
	
	
	
}
