package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfmDispDrugAuditId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String  drugCode;
	private Long actOrder;
	
	public EhfmDispDrugAuditId()
	{
		
	}
	
    public EhfmDispDrugAuditId(String drugId,Long actOrder)
    {
    	this.actOrder=actOrder;
    	this.setDrugCode(drugId);
    }

    @Column(name="DRUG_CODE", nullable=false)
   	public String getDrugCode() {
   		return drugCode;
   	}

   	public void setDrugCode(String drugCode) {
   		this.drugCode = drugCode;
   	}
       

	@Column(name="ACT_ORDER", nullable=false)
	public Long getActOrder() {
		return actOrder;
	}

	public void setActOrder(Long actOrder) {
		this.actOrder = actOrder;
	}

}
