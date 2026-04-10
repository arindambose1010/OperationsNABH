package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class EhfWcIndentPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="INDENT_ID")
	private String indentId;

	public EhfWcIndentPK() {
	}
	
	public EhfWcIndentPK(String drugCode, String indentId) {
		this.drugCode = drugCode;
		this.indentId = indentId;
	}


	public String getDrugCode() {
		return drugCode;
	}


	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public String getIndentId() {
		return indentId;
	}


	public void setIndentId(String indentId) {
		this.indentId = indentId;
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfWcIndentPK)) {
			return false;
		}
		EhfWcIndentPK castOther = (EhfWcIndentPK)other;
		return 
			this.drugCode.equals(castOther.drugCode)
			&& this.indentId.equals(castOther.indentId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.drugCode.hashCode();
		hash = hash * prime + this.indentId.hashCode();
		
		return hash;
	}
}
