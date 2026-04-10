package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfLabReportSubmitPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private String patientId;
	private String investigationId;
	
	public EhfLabReportSubmitPK() {
		// TODO Auto-generated constructor stub
	}

	 public EhfLabReportSubmitPK(String patientId,String investigationId) {
		    this.patientId = patientId;
		    this.investigationId = investigationId;   
	    }
	 
	 @Column(name="PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	 @Column(name="INVESTIGATION_ID")
	public String getInvestigationId() {
		return investigationId;
	}

	public void setInvestigationId(String investigationId) {
		this.investigationId = investigationId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfLabReportSubmitPK)) {
			return false;
		}
		EhfLabReportSubmitPK castOther = (EhfLabReportSubmitPK)other;
		return 
			this.patientId.equals(castOther.patientId)
			&& this.investigationId.equals(castOther.investigationId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.patientId.hashCode();
		hash = hash * prime + this.investigationId.hashCode();
		
		return hash;
	}
	

}
