package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfCaseConsumablesId implements Serializable {
	private String caseId;
	private String enhQuanCode;
	
	@Column(name="CASE_ID", nullable=false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	@Column(name="ENH_QUAN_CODE", nullable=false)
	public String getEnhQuanCode() {
		return enhQuanCode;
	}
	public void setEnhQuanCode(String enhQuanCode) {
		this.enhQuanCode = enhQuanCode;
	}

	public EhfCaseConsumablesId(String caseId, String enhQuanCode) {
		super();
		this.caseId = caseId;
		this.enhQuanCode = enhQuanCode;
	}
	public EhfCaseConsumablesId() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((caseId == null) ? 0 : caseId.hashCode());
		result = prime * result + ((enhQuanCode == null) ? 0 : enhQuanCode.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EhfCaseConsumablesId other = (EhfCaseConsumablesId) obj;
		if (caseId == null) {
			if (other.caseId != null)
				return false;
		} else if (!caseId.equals(other.caseId))
			return false;
		if (enhQuanCode == null) {
			if (other.enhQuanCode != null)
				return false;
		} else if (!enhQuanCode.equals(other.enhQuanCode))
			return false;
		return true;
	}
}
