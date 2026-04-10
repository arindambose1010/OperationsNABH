package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "EJHS_WELLNESS_CONSULTDTLS")
public class EjhsWellnessConsultDtls implements Serializable {


	private String consultCode;
	
	
	private String consultName;
	

	private String grpId;
	private String consultantName;
	
	@Column(name = "CONSULTANT_NAME")
	public String getConsultantName() {
		return consultantName;
	}
	public void setConsultantName(String consultantName) {
		this.consultantName = consultantName;
	}
	private String roomNo;
	

	private String activeYN;
	private String docId;
	@Column(name = "WELLNESS_CENTER")
	public String getWellnessName() {
		return wellnessName;
	}
	public void setWellnessName(String wellnessName) {
		this.wellnessName = wellnessName;
	}
	@Column(name = "CONSULTANT_MOBILE")
	public Long getConMob() {
		return conMob;
	}
	public void setConMob(Long conMob) {
		this.conMob = conMob;
	}
	private String wellnessName;
	private Long conMob;
	
	
	
	@Id
	@Column(name = "consult_code",nullable = false)
	public String getConsultCode() {
		return consultCode;
	}
	public void setConsultCode(String consultCode) {
		this.consultCode = consultCode;
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
	@Column(name = "ROOM_NO")	
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	@Column(name = "ACTIVE_YN")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	

	@Column(name="DOC_ID")
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	
}
