package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EhfPatientBreakupdrugsNabhPK implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String caseId;
    private String drugId;
    private Float unitPrice;
    
    public EhfPatientBreakupdrugsNabhPK() 
    {
    	
    }
    
    public EhfPatientBreakupdrugsNabhPK(String caseId, String drugId,Float unitPrice)
    {
        this.caseId = caseId;
        this.drugId = drugId;
        this.unitPrice = unitPrice;
    }
    @Column(name="CASE_ID", nullable = false)
	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name="DRUG_ID", nullable = false)
	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	@Column(name="UNIT_PRICE", nullable = false)
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
    
  
    
    


}
