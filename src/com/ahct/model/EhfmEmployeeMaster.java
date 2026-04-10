package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
@Entity
@Table(name = "EHFM_DISP_EMPLOYEE_MASTER")
public class EhfmEmployeeMaster implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String address;
    private Date birthDate;
    private Date crtDt;
    private String crtUsr;
    private String desigId;
    private String emailId;
    private String firstName;
    private String gender;
    private String inactiveRemarks;
    private String inService;
    private Date joinDate;
    private String lastName;
    private Date lstUpdDt;
    private String lstUpdUsr;
    private String mobileNo;
    private String panNo;
    private String schemeId;
    private String userId;
    private String userCode;
     private String Dispcode;   
 	private String city;
 	private String district;
 	private String state;
 	private String country;
 	private String pin;
	private String mandMunci;
	private String addrMandMunci;

	 @Column(name="VILLAGE", nullable=false)
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Column(name="district")
	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}
 	@Column(name="state", nullable=false)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

 	@Column(name="country", nullable=false)
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

 	@Column(name="pin")
	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}


	@Column(name="MANDL_OR_MUNC")
	public String getMandMunci() {
		return mandMunci;
	}

	public void setMandMunci(String mandMunci) {
		this.mandMunci = mandMunci;
	}

	@Column(name="MANDAL")
	public String getAddrMandMunci() {
		return addrMandMunci;
	}

	public void setAddrMandMunci(String addrMandMunci) {
		this.addrMandMunci = addrMandMunci;
	}
	
	
    @Column(name="ADDRESS")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

/*    @Column(name="APPOINTMENT_DATE")
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }*/

    @Column(name="BIRTH_DATE")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name="CRT_DT")
    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    @Column(name="CRT_USR")
    public String getCrtUsr() {
        return crtUsr;
    }

    public void setCrtUsr(String crtUsr) {
        this.crtUsr = crtUsr;
    }

    @Column(name="DESIG_ID", nullable = false)
    public String getDesigId() {
        return desigId;
    }

    public void setDesigId(String desigId) {
        this.desigId = desigId;
    }

    @Column(name="EMAIL_ID")
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Column(name="FIRST_NAME", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Column(name="GENDER")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

/*    @Column(name="GIS_NO")
    public String getGisNo() {
        return gisNo;
    }

    public void setGisNo(String gisNo) {
        this.gisNo = gisNo;
    }

    @Column(name="GPF_NO")
    public String getGpfNo() {
        return gpfNo;
    }

    public void setGpfNo(String gpfNo) {
        this.gpfNo = gpfNo;
    }
*/
    @Column(name="INACTIVE_REMARKS")
    public String getInactiveRemarks() {
        return inactiveRemarks;
    }

    public void setInactiveRemarks(String inactiveRemarks) {
        this.inactiveRemarks = inactiveRemarks;
    }

    @Column(name="IN_SERVICE")
    public String getInService() {
        return inService;
    }

    public void setInService(String inService) {
        this.inService = inService;
    }

    @Column(name="JOIN_DATE")
    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    @Column(name="LAST_NAME", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name="LST_UPD_DT")
    public Date getLstUpdDt() {
        return lstUpdDt;
    }

    public void setLstUpdDt(Date lstUpdDt) {
        this.lstUpdDt = lstUpdDt;
    }

    @Column(name="LST_UPD_USR")
    public String getLstUpdUsr() {
        return lstUpdUsr;
    }

    public void setLstUpdUsr(String lstUpdUsr) {
        this.lstUpdUsr = lstUpdUsr;
    }

    @Column(name="MOBILE_NO")
    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String string) {
        this.mobileNo = string;
    }

    @Column(name="PAN_NO")
    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

   /* @Column(name="PAY_ROLE")
    public String getPayRole() {
        return payRole;
    }

    public void setPayRole(String payRole) {
        this.payRole = payRole;
    }*/

    @Column(name="SCHEME_ID")
    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String userType) {
        this.schemeId = userType;
    }
    @Id
    @Column(name="USER_ID")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

/*    @Column(name="VENDOR_ID", nullable = false)
    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }
    */
    @Column(name="USER_CODE", nullable = true)
	public String getUserCode() {
		return userCode;
	}
    
    public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
    @Column(name="DISP_CODE", nullable = true)
	public String getDispcode() {
		return Dispcode;
	}

	public void setDispcode(String dispcode) {
		Dispcode = dispcode;
	}

}