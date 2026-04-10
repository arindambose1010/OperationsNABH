package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;



@Embeddable
public class EhfLabReportsDataPK implements Serializable{

	private static final long serialVersionUID = 1L;
	private String patientId;
	private String fieldId;
	
	public EhfLabReportsDataPK() {
	}

    public EhfLabReportsDataPK(String patientId,String fieldId) {
	    this.patientId = patientId;
	    this.fieldId = fieldId;   
    }
    
    @Column(name="PATIENT_ID")
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Column(name="FIELD_ID")
	public String getFieldId() {
		return fieldId;
	}
	public void setFieldId(String fieldId) {
		this.fieldId = fieldId;
	}
	
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfLabReportsDataPK)) {
			return false;
		}
		EhfLabReportsDataPK castOther = (EhfLabReportsDataPK)other;
		return 
			this.patientId.equals(castOther.patientId)
			&& this.fieldId.equals(castOther.fieldId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.patientId.hashCode();
		hash = hash * prime + this.fieldId.hashCode();
		
		return hash;
	}
	
	
}
