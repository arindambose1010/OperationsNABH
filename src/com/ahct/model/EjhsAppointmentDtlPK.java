package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the EJHS_APPOINTMENT_DTLS database table.
 * 
 */
@Embeddable
public class EjhsAppointmentDtlPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;


	private String patCardNumber;
	private Date appDate;
	
	

	public EjhsAppointmentDtlPK() {
	}
	
	public EjhsAppointmentDtlPK(String patCardNumber,Date appDate) {
	super();
	this.patCardNumber=patCardNumber;
	this.appDate=appDate;
	}
	
	
	@Column(name="PAT_CARD_NUMBER")
	public String getPatCardNumber() {
		return this.patCardNumber;
	}
	public void setPatCardNumber(String patCardNumber) {
		this.patCardNumber = patCardNumber;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="APP_DATE")
	public Date getAppDate() {
		return this.appDate;
	}
	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EjhsAppointmentDtlPK)) {
			return false;
		}
		EjhsAppointmentDtlPK castOther = (EjhsAppointmentDtlPK)other;
		return 
			this.patCardNumber.equals(castOther.patCardNumber)
			&& this.appDate.equals(castOther.appDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.patCardNumber.hashCode();
		hash = hash * prime + this.appDate.hashCode();
		
		return hash;
	}
}