package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EhfmDispDrugReportAuditCompKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name="DRUG_CODE")
	private String drugCode;
	
	@Column(name="ACT_ORDER")
	private String actOrder;	
	
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getActOrder() {
		return actOrder;
	}

	public void setActOrder(String actOrder) {
		this.actOrder = actOrder;
	}

	
	
}
