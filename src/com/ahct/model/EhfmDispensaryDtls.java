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
@Table(name="EHFM_DISPENSARY_DTLS")

public class EhfmDispensaryDtls implements Serializable {

	
	private String dispId;
	private String dispName;
	private String dispCode;
	private String dispCity;
	private String dispEmail;
	private String dispContactno;
	private String dispActiveyn;
	private String langId;
	private String dispDist;
	private String dispType;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String scheme;
	private String labreportAuthorizedBy;
	private String labreport_sign_path;
	private String dispMacId;
	
	@Id
	@Column(name="DISP_ID", nullable=false)
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}
	@Column(name="DISP_NAME", nullable=true)
	public String getDispName() {
		return dispName;
	}
	public void setDispName(String dispName) {
		this.dispName = dispName;
	}
	@Column(name="DISP_CODE", nullable=true)
	public String getDispCode() {
		return dispCode;
	}
	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}
	@Column(name="DISP_CITY", nullable=true)
	public String getDispCity() {
		return dispCity;
	}
	public void setDispCity(String dispCity) {
		this.dispCity = dispCity;
	}
	@Column(name="DISP_EMAIL", nullable=true)
	public String getDispEmail() {
		return dispEmail;
	}
	public void setDispEmail(String dispEmail) {
		this.dispEmail = dispEmail;
	}
	@Column(name="DISP_CONTACT_NO", nullable=true)
	public String getDispContactno() {
		return dispContactno;
	}
	public void setDispContactno(String dispContactno) {
		this.dispContactno = dispContactno;
	}
	@Column(name="DISP_ACTIVE_YN", nullable=true)
	public String getDispActiveyn() {
		return dispActiveyn;
	}
	public void setDispActiveyn(String dispActiveyn) {
		this.dispActiveyn = dispActiveyn;
	}
	@Column(name="LANG_ID", nullable=true)
	public String getLangId() {
		return langId;
	}
	public void setLangId(String langId) {
		this.langId = langId;
	}
	@Column(name="DISP_DIST", nullable=true)
	public String getDispDist() {
		return dispDist;
	}
	public void setDispDist(String dispDist) {
		this.dispDist = dispDist;
	}
	@Column(name="DISP_TYPE", nullable=true)
	public String getDispType() {
		return dispType;
	}
	public void setDispType(String dispType) {
		this.dispType = dispType;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT", nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="CRT_USR", nullable=true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT", nullable=true)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="LST_UPD_USR", nullable=true)
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Column(name="SCHEME", nullable=true)
	public String getScheme() {
		return scheme;
	}
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}
	
	@Column(name="labreport_authorized_by", nullable=true)
	public String getLabreportAuthorizedBy() {
		return labreportAuthorizedBy;
	}
	public void setLabreportAuthorizedBy(String labreportAuthorizedBy) {
		this.labreportAuthorizedBy = labreportAuthorizedBy;
	}
	
	@Column(name="labreport_sign_path", nullable=true)
	public String getLabreport_sign_path() {
		return labreport_sign_path;
	}
	public void setLabreport_sign_path(String labreport_sign_path) {
		this.labreport_sign_path = labreport_sign_path;
	}
	@Column(name="disp_mac_id", nullable=true)
	public String getDispMacId() {
		return dispMacId;
	}
	public void setDispMacId(String dispMacId) {
		this.dispMacId = dispMacId;
	}
	
	
	
	
}
