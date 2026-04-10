package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfCaseDialyDtlsId implements Serializable{

	public Long cycle;
    public String caseId;
	
    public EhfCaseDialyDtlsId() {
    }

    public EhfCaseDialyDtlsId(Long cycle, String caseId) {
        this.cycle = cycle;
        this.caseId = caseId;
    }
    
	@Column(name="CASE_ID", nullable=false)
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	@Column(name="CYCLE", nullable=false)
	public Long getCycle() {
		return cycle;
	}
	public void setCycle(Long cycle) {
		this.cycle = cycle;
	}


    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof EhfCaseDialyDtlsId) ) return false;
                EhfCaseDialyDtlsId castOther = ( EhfCaseDialyDtlsId ) other; 
        
        return ( (this.getCycle()==castOther.getCycle() || ( this.getCycle()!=null && castOther.getCycle()!=null && this.getCycle().equals(castOther.getCycle()) ) )
        && ( (this.getCaseId()==castOther.getCaseId()) || ( this.getCaseId()!=null && castOther.getCaseId()!=null && this.getCaseId().equals(castOther.getCaseId()) ) ) );
      
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getCycle() == null ? 0 : this.getCycle().hashCode() );
        result = 37 * result + ( getCaseId() == null ? 0 : this.getCaseId().hashCode() );
        return result;
    }


}

