package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Embeddable
public class EhfClaimDeductionsPK implements java.io.Serializable {


    // Fields    



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	public EhfClaimDeductionsPK() {
		super();
	}

	private String caseId;
     private String caseStatus;


   
   
    // Property accessors
    
     public EhfClaimDeductionsPK(String caseId, String caseStatus) {
		super();
		this.caseId = caseId;
		this.caseStatus = caseStatus;
	}
     @Column(name="case_id", nullable=false, length=12)
     public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
    @Column(name="case_id", nullable=false, length=12)
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

 


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof EhfClaimDeductionsPK) ) return false;
		 EhfClaimDeductionsPK castOther = ( EhfClaimDeductionsPK ) other; 
         
		 return ( (this.getCaseId()==castOther.getCaseId()) || ( this.getCaseId()!=null && castOther.getCaseId()!=null && this.getCaseId().equals(castOther.getCaseId()) ) )
 && ( (this.getCaseStatus()==castOther.getCaseStatus()) || ( this.getCaseStatus()!=null && castOther.getCaseStatus()!=null && this.getCaseStatus().equals(castOther.getCaseStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCaseId() == null ? 0 : this.getCaseId().hashCode() );
         result = 37 * result + ( getCaseStatus() == null ? 0 : this.getCaseStatus().hashCode() );
         return result;
   }


   
}
