package com.ahct.reports.vo;

import java.io.Serializable;

public class InvestigationVO implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private  String HOSP_ID;
	private  String HOSPITAL_CODE;
	private  String HOSPITAL_NAME;
	private  String HOSPITAL_DISTRICT;
	private  String HOSPITAL_TYPE;
	private  String IP;
	private  String OP;
	private  String LAB;
	private  String XRAY;
	private  String CT_SCAN;
	private  String MRI;
	private  String ULTRA_SOUND;
	private  Number COUNT;
	private char HOSPITAL_TYPE1;
	
	public String getHOSP_ID() {
		return HOSP_ID;
	}
	public void setHOSP_ID(String hOSP_ID) {
		HOSP_ID = hOSP_ID;
	}
	public  String getHOSPITAL_CODE() {
		return HOSPITAL_CODE;
	}
	public  void setHOSPITAL_CODE(String hOSPITAL_CODE) {
		HOSPITAL_CODE = hOSPITAL_CODE;
	}
	public  String getHOSPITAL_NAME() {
		return HOSPITAL_NAME;
	}
	public  void setHOSPITAL_NAME(String hOSPITAL_NAME) {
		HOSPITAL_NAME = hOSPITAL_NAME;
	}
	public  String getHOSPITAL_DISTRICT() {
		return HOSPITAL_DISTRICT;
	}
	public  void setHOSPITAL_DISTRICT(String hOSPITAL_DISTRICT) {
		HOSPITAL_DISTRICT = hOSPITAL_DISTRICT;
	}
	public  String getHOSPITAL_TYPE() {
		return HOSPITAL_TYPE;
	}
	public  void setHOSPITAL_TYPE(String hOSPITAL_TYPE) {
		HOSPITAL_TYPE = hOSPITAL_TYPE;
	}
	public  String getIP() {
		return IP;
	}
	public  void setIP(String iP) {
		IP = iP;
	}
	public  String getOP() {
		return OP;
	}
	public  void setOP(String oP) {
		OP = oP;
	}
	public  String getLAB() {
		return LAB;
	}
	public  void setLAB(String lAB) {
		LAB = lAB;
	}
	public  String getXRAY() {
		return XRAY;
	}
	public  void setXRAY(String XRAY) {
		this.XRAY = XRAY;
	}
	public  String getCT_SCAN() {
		return CT_SCAN;
	}
	public  void setCT_SCAN(String cT_SCAN) {
		CT_SCAN = cT_SCAN;
	}
	public  String getMRI() {
		return MRI;
	}
	public  void setMRI(String mRI) {
		MRI = mRI;
	}
	public  String getULTRA_SOUND() {
		return ULTRA_SOUND;
	}
	public  void setULTRA_SOUND(String uLTRA_SOUND) {
		ULTRA_SOUND = uLTRA_SOUND;
	}
	public char getHOSPITAL_TYPE1() {
		return HOSPITAL_TYPE1;
	}
	public void setHOSPITAL_TYPE1(char hOSPITAL_TYPE1) {
		HOSPITAL_TYPE1 = hOSPITAL_TYPE1;
	}
	public Number getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}

}
