package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The primary key class for the EJHS_APPOINTMENT_DTLS database table.
 * 
 */
@Embeddable
public class MhcEntriesPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;


	private String entryId;
	private String  appDate;
	
	

	public MhcEntriesPK() {
	}
	
	public MhcEntriesPK(String entryId,String appDate) {
	super();
	this.entryId=entryId;
	this.appDate=appDate;
	}
	
	
	@Column(name="ENTRY_ID")
	public String getEntryId() {
		return this.entryId;
	}
	public void setEntryId(String entryId) {
		this.entryId = entryId;
	}
	
/*	@Temporal( TemporalType.TIMESTAMP)*/
	@Column(name="APPOINTMENT_DATE")
	public String getAppDate() {
		return this.appDate;
	}
	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MhcEntriesPK)) {
			return false;
		}
		MhcEntriesPK castOther = (MhcEntriesPK)other;
		return 
			this.entryId.equals(castOther.entryId)
			&& this.appDate.equals(castOther.appDate);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.entryId.hashCode();
		hash = hash * prime + this.appDate.hashCode();
		
		return hash;
	}
}