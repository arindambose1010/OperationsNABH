package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ehfm_oprtns_reports_labels")
public class EhfmOprtnsReportsLabels {

	private static final long serialVersionUID = 1L;

	private String activeYn;	
	private String arrangeOrder;	
	private Date crtDt;	
	private String crtUsr;	
	private String referenceInterval;	
	private String investigationName;	
	private String fieldType;	
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String sid;	
	private String reportName;
	private String method;
	private String investigationId;
	
	
	public EhfmOprtnsReportsLabels() {
	}
	
	@Column(name="METHOD")
	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	@Column(name="ACTIVE_YN")
	public String getActiveYn() {
		return this.activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Column(name="ARRANGE_ORDER")
	public String getArrangeOrder() {
		return this.arrangeOrder;
	}

	public void setArrangeOrder(String arrangeOrder) {
		this.arrangeOrder = arrangeOrder;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Column(name="FIELD_TYPE")
	public String getFieldType() {
		return this.fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	@Temporal(TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Id
	@Column(name="SID")
	public String getSid() {
		return this.sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	@Column(name="REFERENCE_INTERVAL")
	public String getReferenceInterval() {
		return referenceInterval;
	}


	public void setReferenceInterval(String referenceInterval) {
		this.referenceInterval = referenceInterval;
	}

	@Column(name="INVESTIGATION_NAME")
	public String getInvestigationName() {
		return investigationName;
	}

	
	public void setInvestigationName(String investigationName) {
		this.investigationName = investigationName;
	}

	@Column(name="REPORT_NAME")
	public String getReportName() {
		return reportName;
	}


	public void setReportName(String reportName) {
		this.reportName = reportName;
	}
	@Column(name="INVESTIGATION_ID")
	public String getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}

	
}
