package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@Table(name="EHF_DISP_DRUG_TRANSFER")
public class EhfDispDrugTransfer implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EhfDispDrugTransferPK id;
	private String drugId;
	private String drugTypeId;
	private String mnfctrId;
	private String dstrbtrId;
	private Double price;
	private String batchNo;
	private long quantity;
	private String toDispId;
	private String fromDispId;
	private Date expiryDate;
	private String crtUsr;
	private Date crtDate;
	private String lstUpdUsr;
	private Date lstUpdDate;
	private String status;

	  @EmbeddedId
	    @AttributeOverrides( {
	        @AttributeOverride(name="reqstId", column=@Column(name="REQST_ID", nullable=false) ), 
	        @AttributeOverride(name="drugCode", column=@Column(name="DRUG_CODE", nullable=false) )
	        
	        } )
	  public EhfDispDrugTransferPK getId() {
		return id;
	   }
	public void setId(EhfDispDrugTransferPK id) {
		this.id = id;
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
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EXPIRY_DATE")
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
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
	@Column(name="STATUS")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Column(name="TO_DISP_ID")
	public String getToDispId() {
		return toDispId;
	}
	public void setToDispId(String toDispId) {
		this.toDispId = toDispId;
	}
	@Column(name="FROM_DISP_ID")
	public String getFromDispId() {
		return fromDispId;
	}
	public void setFromDispId(String fromDispId) {
		this.fromDispId = fromDispId;
	}
	
	
	
}
