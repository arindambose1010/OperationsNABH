package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

//sai krishna:CR#8134
@Entity
@Table(name = "EHF_PT_TESTS_TD_API_AUDIT")
public class EhfPtTestsTdApiAudit implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@Column(name = "SEQ_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "audit_seq")
	@SequenceGenerator(name = "audit_seq", sequenceName = "EHF_PT_TESTS_TD_API_AUDIT_SEQ", allocationSize = 1)
	private Long ptInvSeqId;

	@Column(name = "PATIENT_ID")
	private String patientId;
	
	@Column(name = "TEST_ID")
	private String testId;
	
	@Column(name = "TD_INVST_FLG")
	private String tdInvstFlg;
	
	@Column(name = "API_STATUS")
	private Integer apiStatus;
	
	@Column(name = "API_RESPONSE")
	private String apiResponse;
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	private Date crtDt;
	
	@Column(name = "CRT_USR")
	private String crtUsr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT")
	private Date lstUpdDt;
	
	@Column(name = "LST_UPD_USR")
	private String lstUpdUsr;

	public String getTdInvstFlg() {
		return tdInvstFlg;
	}

	public void setTdInvstFlg(String tdInvstFlg) {
		this.tdInvstFlg = tdInvstFlg;
	}

	public Long getPtInvSeqId() {
		return ptInvSeqId;
	}

	public void setPtInvSeqId(Long ptInvSeqId) {
		this.ptInvSeqId = ptInvSeqId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	public Integer getApiStatus() {
		return apiStatus;
	}

	public void setApiStatus(Integer apiStatus) {
		this.apiStatus = apiStatus;
	}

	public String getApiResponse() {
		return apiResponse;
	}

	public void setApiResponse(String apiResponse) {
		this.apiResponse = apiResponse;
	}

	public String getTestId() {
		return testId;
	}

	public void setTestId(String testId) {
		this.testId = testId;
	}

	
}
