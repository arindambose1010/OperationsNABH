package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_OTP_EXEMPTION")
public class EhfOtpExemption implements Serializable{
	private String patientId;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
	private String exemptionFlag;
	private String remarks;
	private String crtUsr;

	@Id
	@Column(name="PATIENT_ID", nullable = false)
	public String getPatientId() {
		return patientId;
		}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
		}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable = false)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name="CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }
    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }
   
    @Column(name="EXEMPTION_FLAG")
	public String getexemptionFlag() {
		return exemptionFlag;
	}

	public void setexemptionFlag(String exemptionFlag) {
		this.exemptionFlag = exemptionFlag;
	}
	@Column(name="REMARKS")
	public String getremarks() {
		return remarks;
	}

	public void setremarks(String remarks) {
		this.remarks = remarks;
	}
	
}