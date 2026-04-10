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
@Table(name="EHF_PATIENT_SPECIALITIES")
public class EhfPatientSpecialities implements Serializable {
	private String patientId;
	private String actOrder;
	private String specialityType;
	private String roomNo;
	private String crtUsr;
	private Date crtDt;
	private String specialistDoctorId;
	private String specialistDoctorName;
	private String otherSpecialistDoctorName;

	public EhfPatientSpecialities() {
		super();
	}
	
	public EhfPatientSpecialities(String patientId, String actOrder, String specialityType,
			String roomNo, String crtUsr, Date crtDt, String specialistDoctorId, String specialistDoctorName, String otherSpecialistDoctorName
			) {
		super();
		this.patientId = patientId;
		this.actOrder = actOrder;
		this.specialityType = specialityType;
		this.roomNo = roomNo;
		this.crtUsr = crtUsr;
		this.crtDt = crtDt;
		this.specialistDoctorId = specialistDoctorId;
		this.specialistDoctorName = specialistDoctorName;
		this.otherSpecialistDoctorName = otherSpecialistDoctorName;
	}
	@Id
	@Column(name="ACT_ORDER")
	public String getActOrder() {
		return actOrder;
	}

	public void setActOrder(String actOrder) {
		this.actOrder = actOrder;
	}
	@Column(name="PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	@Column(name="SPECIALITY_TYPE")
	public String getSpecialityType() {
		return specialityType;
	}

	public void setSpecialityType(String specialityType) {
		this.specialityType = specialityType;
	}
	@Column(name="ROOM_NO")
	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
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
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="SPECIALIST_DOCTOR_ID")
	public String getSpecialistDoctorId() {
		return specialistDoctorId;
	}

	public void setSpecialistDoctorId(String specialistDoctorId) {
		this.specialistDoctorId = specialistDoctorId;
	}
	@Column(name="SPECIALIST_DOCTOR_NAME")
	public String getSpecialistDoctorName() {
		return specialistDoctorName;
	}

	public void setSpecialistDoctorName(String specialistDoctorName) {
		this.specialistDoctorName = specialistDoctorName;
	}
	@Column(name="OTHER_SPECIALIST_DOCTOR_NAME")
	public String getOtherSpecialistDoctorName() {
		return otherSpecialistDoctorName;
	}

	public void setOtherSpecialistDoctorName(String otherSpecialistDoctorName) {
		this.otherSpecialistDoctorName = otherSpecialistDoctorName;
	}
	
}
