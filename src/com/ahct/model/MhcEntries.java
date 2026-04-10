package com.ahct.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the EJHS_APPOINTMENT_DTLS database table.
 * 
 */
@Entity
@Table(name="MHC_ENTRIES")
public class MhcEntries implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private MhcEntriesPK id;

	private String scheme;

	
	private Date crtDt;

	
	private String serviceFlag;

	
	private String empId;

	private String empName;

	
	private String service;
	private String mobile;
	private String email;
	private String servicetype;
	private String packageSel;

	
	private String timeSlot;
	private String relation;
	private String subcat;
	private String gender;
	private String patientId;
	private String activeYn;
	private Date lstDt;
	private String lstUpdUsr;
	private String aisRemarks;

	public MhcEntries() {
	}

	@EmbeddedId
	public MhcEntriesPK getId() {
		return this.id;
	}
	public void setId(MhcEntriesPK id) {
		this.id = id;
	}
	@Column(name="SCHEME")
	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="SERVICE_FLAG")
	public String getServiceFlag() {
		return serviceFlag;
	}

	public void setServiceFlag(String serviceFlag) {
		this.serviceFlag = serviceFlag;
	}
	@Column(name="EMP_ID")
	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}
	@Column(name="EMP_NAME")
	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	@Column(name="SERVICE")
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}
	@Column(name="MOBILE")
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	@Column(name="EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="SERVICETYPE")
	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}
	@Column(name="PACKAGE_SELECTED")
	public String getPackageSel() {
		return packageSel;
	}

	public void setPackageSel(String packageSel) {
		this.packageSel = packageSel;
	}
	@Column(name="TIME_SLOT")
	public String getTimeSlot() {
		return timeSlot;
	}
	
	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	@Column(name="RELATION")
	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	@Column(name="SUBCATEGORY")
	public String getSubcat() {
		return subcat;
	}

	public void setSubcat(String subcat) {
		this.subcat = subcat;
	}
	@Column(name="GENDER")
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column(name="active_yn")
	public String getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(String activeYn) {
		this.activeYn = activeYn;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT")
	public Date getLstDt() {
		return lstDt;
	}

	public void setLstDt(Date lstDt) {
		this.lstDt = lstDt;
	}
	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	@Column(name="AIS_REMARKS")
	public String getAisRemarks() {
		return aisRemarks;
	}

	public void setAisRemarks(String aisRemarks) {
		this.aisRemarks = aisRemarks;
	}

	

	
}