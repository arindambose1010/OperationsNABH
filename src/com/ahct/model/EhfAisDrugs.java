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
@Table(name = "EHF_AIS_DRUGS")
public class EhfAisDrugs implements Serializable {

	private String patientId;
    private String employeeNo;
    private String caseStatus;
    private String empName;
    private String partialFlg;
    private Date crtDt;
   
	private String lstUpdUsr;;
    private Date lstUpdDt;
    private String pickLocation;
    private String pickupName;
    private String pickupNum;
    private String month;
    private String years;
    private String contactNo;
    

 



    













@Id
@Column(name="PAT_ID", nullable = false)
    public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	@Column(name="EMPLOYEE_ID", nullable = false)
	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	@Column(name="STATUS")
	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	@Column(name="AIS_NAME")
public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}


	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="CRT_DT")
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }
    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
    @Column(name="PARTIAL_FLAG")
	 public String getPartialFlg() {
			return partialFlg;
		}

		public void setPartialFlg(String partialFlg) {
			this.partialFlg = partialFlg;
		}
	    @Column(name="PICKUP_LOCATION")
		public String getPickLocation() {
			return pickLocation;
		}

		public void setPickLocation(String pickLocation) {
			this.pickLocation = pickLocation;
		}
		 @Column(name="PICKUP_NAME")
public String getPickupName() {
		return pickupName;
	}

	public void setPickupName(String pickupName) {
		this.pickupName = pickupName;
	}
	 @Column(name="PICKUP_NUM")
	public String getPickupNum() {
		return pickupNum;
	}

	public void setPickupNum(String pickupNum) {
		this.pickupNum = pickupNum;
	}
	 @Column(name="MONTH")
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
	 @Column(name="YEAR")
	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}

	
	 @Column(name="MOBILE")
	 public String getContactNo() {
			return contactNo;
		}

		public void setContactNo(String contactNo) {
			this.contactNo = contactNo;
		}
}
