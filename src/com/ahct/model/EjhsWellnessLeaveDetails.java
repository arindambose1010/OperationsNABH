package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "EJHS_WELLNESS_LEAVEDTLS")
public class EjhsWellnessLeaveDetails implements Serializable {


	private String userId;
	
	
	private String consultName;
	

	private String grpId;
	

	private String wellnessCenterName;
	private Long consultMobile;
	private Date leaveFomDate;
	private Date leaveToDate;
	private Date crtDt;
	private String crtUsr;
	private String reason;
	
	
	@Column(name = "CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	private String activeYN;
	private String docId;
	private String dispId;
	
	
	@Column(name = "DOC_ID")
	public String getDocId() {
		return docId;
	}
	
	public void setDocId(String docId) {
		this.docId = docId;
	}
	@Id
	@Column(name = "USER_ID",nullable = false)
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Column(name = "CONSULT_NAME")
	public String getConsultName() {
		return consultName;
	}
	public void setConsultName(String consultName) {
		this.consultName = consultName;
	}
	@Column(name = "GRP_ID")
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	@Column(name = "WELLNESS_CENTER")
	public String getWellnessCenterName() {
		return wellnessCenterName;
	}
	public void setWellnessCenterName(String wellnessCenterName) {
		this.wellnessCenterName = wellnessCenterName;
	}
	@Column(name = "CONSULTANT_MOBILE")
	public Long getConsultMobile() {
		return consultMobile;
	}
	public void setConsultMobile(Long consultMobile) {
		this.consultMobile = consultMobile;
	}
	@Column(name = "LEAVE_FROMDATE")
	public Date getLeaveFomDate() {
		return leaveFomDate;
	}

	public void setLeaveFomDate(Date leaveFomDate) {
		this.leaveFomDate = leaveFomDate;
	}
	@Column(name = "LEAVE_TODATE")
	public Date getLeaveToDate() {
		return leaveToDate;
	}

	public void setLeaveToDate(Date leaveToDate) {
		this.leaveToDate = leaveToDate;
	}
	@Column(name = "CRT_DATE")
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "ACTIVE_YN")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name = "DISP_ID")
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}
	@Column(name = "REASON")
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
	
}
