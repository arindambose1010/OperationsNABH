package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="EHFM_DISP_DRUG_MSTR")
public class EhfmDispDrugMstr implements Serializable{

	private String drugCode;
	private String drugId;
	private String drugTypeId;
	private String mnfctrId;
	private String dstrbtrId;
	private Double price;
	private String batchNo;
	private long quantity;
	private String dispId;
	private Date expiryDate;
	private String activeYN;
	private String crtUsr;
	private Date crtDate;
	private String lstUpdUsr;
	private Date lstUpdDate;
	private String itemId;
	private String contractYear;
	private Long pendingIndent;
	private String poId;
	
	
	@Id
	@Column(name="DRUG_CODE")
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Column(name="DRUG_ID")
	public String getDrugId() {
		return drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	
	@Column(name="DRUG_TYPE_ID")
	public String getDrugTypeId() {
		return drugTypeId;
	}
	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}
	
	@Column(name="MNFCTR_ID")
	public String getMnfctrId() {
		return mnfctrId;
	}
	public void setMnfctrId(String mnfctrId) {
		this.mnfctrId = mnfctrId;
	}
	
	@Column(name="DSTRBTR_ID")
	public String getDstrbtrId() {
		return dstrbtrId;
	}
	public void setDstrbtrId(String dstrbtrId) {
		this.dstrbtrId = dstrbtrId;
	}
	
	
	
	
	@Column(name="BATCH_NO")
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Column(name="QUANTITY")
	public long getQuantity() {
		return quantity;
	}
	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	
	@Column(name="DISP_ID")
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Column(name="ACTIVE_YN")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
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
	
	@Column(name="PRICE")
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Column(name="ITEM_ID")
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	@Column(name="CONTRACT_YEAR")
	public String getContractYear() {
		return contractYear;
	}
	public void setContractYear(String contractYear) {
		this.contractYear = contractYear;
	}
	//pravalika
	@Column(name="PO_ID")
	public String getPoId() {
		return poId;
	}
	public void setPoId(String poId) {
		this.poId = poId;
	}
	
	@Column(name = "PENDING_INDENT")
	public Long getPendingIndent() {
		return pendingIndent;
	}
	public void setPendingIndent(Long pendingIndent) {
		this.pendingIndent = pendingIndent;
	}
}
