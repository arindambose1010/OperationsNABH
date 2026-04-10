package com.ahct.patient.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;

@SuppressWarnings("serial")
public class SpecialityVO implements Serializable{

	private String specialityType;
	private String roomNo;
	private String specialistDoctorId;
	private String specialistDoctorName;
	private String otherSpecialistDoctorName;
	public String getSpecialityType() {
		return specialityType;
	}
	public void setSpecialityType(String specialityType) {
		this.specialityType = specialityType;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	

	public String getSpecialistDoctorId() {
		return specialistDoctorId;
	}
	public void setSpecialistDoctorId(String specialistDoctorId) {
		this.specialistDoctorId = specialistDoctorId;
	}
	public String getSpecialistDoctorName() {
		return specialistDoctorName;
	}
	public void setSpecialistDoctorName(String specialistDoctorName) {
		this.specialistDoctorName = specialistDoctorName;
	}
	public String getOtherSpecialistDoctorName() {
		return otherSpecialistDoctorName;
	}
	public void setOtherSpecialistDoctorName(String otherSpecialistDoctorName) {
		this.otherSpecialistDoctorName = otherSpecialistDoctorName;
	}

	private String SPECIALITYTYPE;
	private String ROOMNO;
	private String SPECIALITYDOCTORID;
	private String SPECIALITYDOCTORNAME;
	private String OTHERSPECIALITYDOCTORNAME;
	private String ID;
	private String VALUE;
	private List<LabelBean> docList;

	public List<LabelBean> getDocList() {
		return docList;
	}
	public void setDocList(List<LabelBean> docList) {
		this.docList = docList;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getVALUE() {
		return VALUE;
	}
	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}
	public String getSPECIALITYTYPE() {
		return SPECIALITYTYPE;
	}
	public void setSPECIALITYTYPE(String sPECIALITYTYPE) {
		SPECIALITYTYPE = sPECIALITYTYPE;
	}
	public String getROOMNO() {
		return ROOMNO;
	}
	public void setROOMNO(String rOOMNO) {
		ROOMNO = rOOMNO;
	}
	
	private String  specialityName;
	public String getSpecialityName() {
		return specialityName;
	}
	public void setSpecialityName(String specialityName) {
		this.specialityName = specialityName;
	}
	private String  SPECIALITYNAME;
	public String getSPECIALITYNAME() {
		return SPECIALITYNAME;
	}
	public void setSPECIALITYNAME(String sPECIALITYNAME) {
		SPECIALITYNAME = sPECIALITYNAME;
	}
	public String getSPECIALITYDOCTORID() {
		return SPECIALITYDOCTORID;
	}
	public void setSPECIALITYDOCTORID(String sPECIALITYDOCTORID) {
		SPECIALITYDOCTORID = sPECIALITYDOCTORID;
	}
	public String getSPECIALITYDOCTORNAME() {
		return SPECIALITYDOCTORNAME;
	}
	public void setSPECIALITYDOCTORNAME(String sPECIALITYDOCTORNAME) {
		SPECIALITYDOCTORNAME = sPECIALITYDOCTORNAME;
	}
	public String getOTHERSPECIALITYDOCTORNAME() {
		return OTHERSPECIALITYDOCTORNAME;
	}
	public void setOTHERSPECIALITYDOCTORNAME(String oTHERSPECIALITYDOCTORNAME) {
		OTHERSPECIALITYDOCTORNAME = oTHERSPECIALITYDOCTORNAME;
	}
	
	
	
}
