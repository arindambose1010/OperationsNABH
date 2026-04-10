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
@Table(name = "EHF_MEDICAL_HISTORY")
public class EhfMedicalHistory implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String enrollId;
	private String enrollPrntId;
	private Date dOs;
	private String preexistDiseases;
	private String drugscurrent;
	private String Knowndrugaller;
	private String mdclhtry;
	private String anysurgerdne;
	private String noSurgery;
	private String anycongenital;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String viewflg;
	
	public EhfMedicalHistory(){
		
	}
	
	@Id
	@Column(name = "ENROLL_ID", nullable = false)
	public String getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}
	
	
	@Column(name = "ENROLL_PRNT_ID", nullable = false)
	public String getEnrollPrntId() {
		return enrollPrntId;
	}
	public void setEnrollPrntId(String enrollPrntId) {
		this.enrollPrntId = enrollPrntId;
	}
	
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE_OF_SURGERY" )
	public Date getdOs() {
		return dOs;
	}
	public void setdOs(Date dOs) {
		this.dOs = dOs;
	}
	
	@Column(name = "PRE_EXISTING_DISEASES" )
	public String getPreexistDiseases() {
		return preexistDiseases;
	}
	public void setPreexistDiseases(String preexistDiseases) {
		this.preexistDiseases = preexistDiseases;
	}
	
	@Column(name = "DRUGS_CURRENT" )
	public String getDrugscurrent() {
		return drugscurrent;
	}
	public void setDrugscurrent(String drugscurrent) {
		this.drugscurrent = drugscurrent;
	}
	
	@Column(name = "KNOWN_DRUG_ALLERGIES" )
	public String getKnowndrugaller() {
		return Knowndrugaller;
	}
	public void setKnowndrugaller(String knowndrugaller) {
		Knowndrugaller = knowndrugaller;
	}
	
	@Column(name = "MDCL_HTRY" )
	public String getMdclhtry() {
		return mdclhtry;
	}
	public void setMdclhtry(String mdclhtry) {
		this.mdclhtry = mdclhtry;
	}
	
	@Column(name = "ANY_SURGER_DNE" )
	public String getAnysurgerdne() {
		return anysurgerdne;
	}
	public void setAnysurgerdne(String anysurgerdne) {
		this.anysurgerdne = anysurgerdne;
	}
	
	@Column(name = "NAME_OF_SURGERY" )
	public String getNoSurgery() {
		return noSurgery;
	}
	public void setNoSurgery(String noSurgery) {
		this.noSurgery = noSurgery;
	}
	
	@Column(name = "ANY_CONGENITAL" )
	public String getAnycongenital() {
		return anycongenital;
	}
	public void setAnycongenital(String anycongenital) {
		this.anycongenital = anycongenital;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT", nullable = false)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column(name = "CRT_USR")
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	
	@Column(name = "LST_UPD_DT")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLstUpdDt() {
		return lstUpdDt;
	}
	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	
	@Column(name = "LST_UPD_USR")
	public String getLstUpdUsr() {
		return lstUpdUsr;
	}
	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Column(name = "VIEW_FLG")
	public String getViewflg() {
		return viewflg;
	}
	public void setViewflg(String viewflg) {
		this.viewflg = viewflg;
	}
	
	
}
