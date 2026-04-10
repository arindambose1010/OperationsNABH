package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "AIS_ATTACHMENTS")
public class AisAttachments implements Serializable {

	private String sno;
    private String patId;
    private String attachmentPath;
    private String employeeNo;
    private Date crtDt;
    private String attachName;
    

@Id
@Column(name="SNO", nullable = false)
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	@Column(name="PAT_ID")
	public String getPatId() {
		return patId;
	}
	public void setPatId(String patId) {
		this.patId = patId;
	}
	@Column(name="ATTACHMENT_PATH")
	public String getAttachmentPath() {
		return attachmentPath;
	}
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}
	@Column(name="EMPLOYEE_ID")
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="ATTACHMENT_NAME")
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
   
	
 



    




  


	
	

	
    
}
