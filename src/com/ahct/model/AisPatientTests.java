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
@Table(name = "AIS_PATIENT_TESTS")
public class AisPatientTests implements Serializable {

	private String attachTotalPath;
    private Date crtDt;
    private String crtUsr;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String patientId;
    private String testId;
    private String testName;
    private String sNo;
    @Id
    @Column(name="S_NO")
    public String getsNo() {
		return sNo;
	}


	public void setsNo(String sNo) {
		this.sNo = sNo;
	}


	public AisPatientTests() {
    }


    @Column(name="ATTACH_PATH")
    public String getAttachTotalPath() {
        return attachTotalPath;
    }

    public void setAttachTotalPath(String attachTotalPath) {
        this.attachTotalPath = attachTotalPath;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT", nullable=true, length=7)
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR", nullable = true)
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

    @Column(name="PATIENT_ID")
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    
    @Column(name="TEST_ID")
    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
 
	 @Column(name="TEST_NAME")
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}

    

   
}
