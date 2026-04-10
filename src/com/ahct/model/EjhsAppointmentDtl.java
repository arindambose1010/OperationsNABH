package com.ahct.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;


/**
 * The persistent class for the EJHS_APPOINTMENT_DTLS database table.
 * 
 */
@Entity
@Table(name="EJHS_APPOINTMENT_DTLS")
public class EjhsAppointmentDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private EjhsAppointmentDtlPK id;

	private String consultant;

	
	private Date crtDt;

	
	private String mobileNo;

	
	private String patName;

	private String scheme;

	
	private String timeSlot;
	private String docId;
	private String activeYN;
	private String patientId;

	
	private String wellnessCenter;

	public EjhsAppointmentDtl() {
	}

	@EmbeddedId
	public EjhsAppointmentDtlPK getId() {
		return this.id;
	}

	public void setId(EjhsAppointmentDtlPK id) {
		this.id = id;
	}

	@Column(name="CONSULTANT")
	public String getConsultant() {
		return this.consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="MOBILE_NO")
	public String getMobileNo() {
		return this.mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	@Column(name="PAT_NAME")
	public String getPatName() {
		return this.patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	@Column(name="SCHEME")
	public String getScheme() {
		return this.scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	@Column(name="TIME_SLOT")
	public String getTimeSlot() {
		return this.timeSlot;
	}


	public void setTimeSlot(String timeSlot) {
		this.timeSlot = timeSlot;
	}
	@Column(name="WELLNESS_CENTER")
	public String getWellnessCenter() {
		return this.wellnessCenter;
	}

	public void setWellnessCenter(String wellnessCenter) {
		this.wellnessCenter = wellnessCenter;
	}

	
	@Column(name="DOC_ID")
	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}
	@Column(name="ACTIVE_YN")
	public String getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name="patient_id")
	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

}