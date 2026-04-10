package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class EhfDispDrugTransferPK implements Serializable{
	
	private String reqstId;
	private String drugCode;
	
	public EhfDispDrugTransferPK()
	{
		
	}
	
    public EhfDispDrugTransferPK(String reqstId,String drugCode)
    {
    	this.reqstId=reqstId;
    	this.drugCode=drugCode;
    }

    @Column(name="REQST_ID", nullable=false)
	public String getReqstId() {
		return reqstId;
	}
	public void setReqstId(String reqstId) {
		this.reqstId = reqstId;
	}
	@Column(name="DRUG_CODE", nullable=false)
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	

}
