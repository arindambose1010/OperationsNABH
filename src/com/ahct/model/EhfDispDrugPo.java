package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_DISP_DRUG_PO database table.
 * 
 */
@Entity
@Table(name="EHF_DISP_DRUG_PO")
@NamedQuery(name="EhfDispDrugPo.findAll", query="SELECT e FROM EhfDispDrugPo e")
public class EhfDispDrugPo implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfDispDrugPoPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Column(name="DRUG_NAME")
	private String drugName;

	@Column(name="DRUG_TYPE_NAME")
	private String drugTypeName;

	@Column(name="DSTRBTR_ID")
	private String dstrbtrId;

	@Column(name="DSTRBTR_NAME")
	private String dstrbtrName;

	@Column(name="INDENT_COUNT")
	private String indentCount;

	@Column(name="INDENT_ID")
	private String indentId;

	@Column(name="ITEM_ID")
	private String itemId;

	@Temporal(TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	@Column(name="MNFCTR_ID")
	private String mnfctrId;

	@Column(name="MNFCTR_NAME")
	private String mnfctrName;

	@Column(name="PARTIAL_FULL")
	private String partialFull;

	@Column(name="PO_ID")
	private String poId;
	
	@Column(name="PO_ID1")
	private String poId1;

	public String getPoId1() {
		return poId1;
	}

	public void setPoId1(String poId1) {
		this.poId1 = poId1;
	}

	@Column(name="RECEIVED_COUNT")
	private String receivedCount;
	
	   
	   @Column(name="BATCH_NO")
	   private String batchNo;
	   
	   @Column(name="EXPIRY_DATE")
	   private String expiryDate;
	   
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

	@Column(name="INVOICE_NO")
	   private String invoiceNo;
	   
	   @Column(name="INVOICE_DATE")
	   private String invoiceDate;

	

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

	

	public EhfDispDrugPo() {
	}

	public EhfDispDrugPoPK getId() {
		return this.id;
	}

	public void setId(EhfDispDrugPoPK id) {
		this.id = id;
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

	public String getDrugName() {
		return this.drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getDrugTypeName() {
		return this.drugTypeName;
	}

	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}

	public String getDstrbtrId() {
		return this.dstrbtrId;
	}

	public void setDstrbtrId(String dstrbtrId) {
		this.dstrbtrId = dstrbtrId;
	}

	public String getDstrbtrName() {
		return this.dstrbtrName;
	}

	public void setDstrbtrName(String dstrbtrName) {
		this.dstrbtrName = dstrbtrName;
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

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
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

	public String getMnfctrId() {
		return this.mnfctrId;
	}

	public void setMnfctrId(String mnfctrId) {
		this.mnfctrId = mnfctrId;
	}

	public String getMnfctrName() {
		return this.mnfctrName;
	}

	public void setMnfctrName(String mnfctrName) {
		this.mnfctrName = mnfctrName;
	}

	public String getPartialFull() {
		return this.partialFull;
	}

	public void setPartialFull(String partialFull) {
		this.partialFull = partialFull;
	}

	public String getPoId() {
		return this.poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	public String getReceivedCount() {
		return this.receivedCount;
	}

	public void setReceivedCount(String receivedCount) {
		this.receivedCount = receivedCount;
	}

}