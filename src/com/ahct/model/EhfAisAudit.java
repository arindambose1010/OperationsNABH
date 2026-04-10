package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "EHF_AIS_AUDIT")
public class EhfAisAudit implements Serializable {

	private EhfAisAuditId id;
    private String actId;
    private Date crtDt;
    private String crtUsr; 
    private String userRole;
    //added for payment transaction
   
    
    
    @EmbeddedId
    @AttributeOverrides( {
        @AttributeOverride(name="actOrder", column=@Column(name="ACT_ORDER", nullable=false, length=5) ), 
        @AttributeOverride(name="patientId", column=@Column(name="PATIENT_ID", nullable=false, length=5) ) } )
public EhfAisAuditId getId() {
        return id;
}
public void setId(EhfAisAuditId id) {
        this.id = id;
}


@Column(name="CASE_STATUS")
public String getActId() {
    return actId;
}

public void setActId(String actId) {
    this.actId = actId;
}
@Temporal(TemporalType.TIMESTAMP)
@Column(name="CRT_DATE", nullable = false)
public Date getCrtDt() {
    return crtDt;
}

public void setCrtDt(Date crtDt) {
    this.crtDt = crtDt;
}

@Column(name="CRT_USR", nullable = false)
public String getCrtUsr() {
    return crtUsr;
}

public void setCrtUsr(String crtUsr) {
    this.crtUsr = crtUsr;
}





@Column(name="grp_id")
public String getUserRole() {
	return userRole;
}
public void setUserRole(String userRole) {
	this.userRole = userRole;
}




}
