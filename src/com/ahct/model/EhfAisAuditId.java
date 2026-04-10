package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;


public class EhfAisAuditId implements Serializable {
    public Long actOrder;
    public String patientId;

    public EhfAisAuditId() {
    }

    public EhfAisAuditId(Long actOrder, String patientId) {
        this.actOrder = actOrder;
        this.patientId = patientId;
    }
    
    public void setActOrder(Long actOrder) {
        this.actOrder = actOrder;
    }
    @Column(name="ACT_ORDER")
    public Long getActOrder() {
        return actOrder;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
    @Column(name="PATIENT_ID")
    public String getPatientId() {
        return patientId;
    }

    
    public boolean equals(Object other) {
        if ( (this == other ) ) return true;
                if ( (other == null ) ) return false;
                if ( !(other instanceof EhfAisAuditId) ) return false;
                EhfAisAuditId castOther = ( EhfAisAuditId ) other; 
        
        return ( (this.getActOrder()==castOther.getActOrder() || ( this.getActOrder()!=null && castOther.getActOrder()!=null && this.getActOrder().equals(castOther.getActOrder()) ) )
        && ( (this.getPatientId()==castOther.getPatientId()) || ( this.getPatientId()!=null && castOther.getPatientId()!=null && this.getPatientId().equals(castOther.getPatientId()) ) ) );
      
    }

    public int hashCode() {
        int result = 17;
        result = 37 * result + ( getActOrder() == null ? 0 : this.getActOrder().hashCode() );
        result = 37 * result + ( getPatientId() == null ? 0 : this.getPatientId().hashCode() );
        return result;
    }
}