package com.ahct.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the EHF_DISP_DRUG_PO_GENERATION database table.
 * 
 */
@Entity
@Table(name="EHF_DISP_DRUG_PO_GENERATION")
public class EhfDispDrugPoGeneration implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="ACTIVE_YN")
	private String activeYn;

	@Temporal(TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	@Id
	@Column(name="DRUG_ID")
	private String drugId;

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

	@Temporal(TemporalType.DATE)
	@Column(name="INDENT_DT")
	private Date indentDt;

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

	public EhfDispDrugPoGeneration() {
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

	public String getDrugId() {
		return this.drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
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

	public Date getIndentDt() {
		return this.indentDt;
	}

	public void setIndentDt(Date indentDt) {
		this.indentDt = indentDt;
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

}