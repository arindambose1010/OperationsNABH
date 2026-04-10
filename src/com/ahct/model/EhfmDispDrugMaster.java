package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "EHFM_DISP_DRUG_MSTR") 
public class EhfmDispDrugMaster implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="DRUG_ID")
	private String drugId;
	
	@Column(name="DRUG_TYPE_ID")
	private String drugTypeId;
		
	@Column(name="MNFCTR_ID")
	private String mnfctrId;
	
	@Column(name="DSTRBTR_ID")
	private String dstrbtrId;

	@Column(name="BATCH_NO")
	private String batchNo;
	
	@Column(name="QUANTITY")
	private String quantity;
	
	@Column(name="DISP_ID")
	private String dispId;
	
	@Column(name="EXPIRY_DATE")
	private Date expiryDate;	
	
	@Column(name="CRT_DATE")
	private Date crtDate;

	@Column(name="CRT_USR")
	private String crtUsr;
		
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;
	
	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getDrugTypeId() {
		return drugTypeId;
	}

	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
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

	public String getDispId() {
		return dispId;
	}

	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

	public Date getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(Date crtDate) {
		this.crtDate = crtDate;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "EhfmDispDrugMaster [drugCode=" + drugCode + ", drugId="
				+ drugId + ", drugTypeId=" + drugTypeId + ", mnfctrId="
				+ mnfctrId + ", dstrbtrId=" + dstrbtrId + ", batchNo="
				+ batchNo + ", quantity=" + quantity + ", dispId=" + dispId
				+ ", expiryDate=" + expiryDate + ", crtDate=" + crtDate
				+ ", crtUsr=" + crtUsr + ", lstUpdDt=" + lstUpdDt
				+ ", lstUpdUsr=" + lstUpdUsr + "]";
	}
			
}
