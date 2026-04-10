package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfDispDrgReturnVendorId implements Serializable{
	
	private String returnRqstId;
	private String drugCode;
	
	public EhfDispDrgReturnVendorId()
	{
		
	}
	
    public EhfDispDrgReturnVendorId(String returnRqstId,String drugCode)
    {
    	this.returnRqstId=returnRqstId;
    	this.drugCode=drugCode;
    }

    @Column(name="DRUG_CODE", nullable=false)
   	public String getDrugCode() {
   		return drugCode;
   	}

   	public void setDrugCode(String drugCode) {
   		this.drugCode = drugCode;
   	}
   	@Column(name="RETURN_RQST_ID", nullable=false)
	public String getReturnRqstId() {
		return returnRqstId;
	}

	public void setReturnRqstId(String returnRqstId) {
		this.returnRqstId = returnRqstId;
	}
       
    
	

	
	
    

}
