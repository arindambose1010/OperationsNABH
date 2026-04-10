package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "LHS_USER_DTLS")
public class LHSPatientDetails {

	@Id
	@Column(name = "ID_NO")
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hims_pt_invst_seq_generator")
//	@SequenceGenerator(name = "hims_pt_invst_seq_generator", sequenceName = "T_HIMST_PT_INVST_DTLS_SEQ", allocationSize = 1)
	private String idNo;

	@Column(name = "CARD_NO")
	private String cardNo;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "GENDER")
	private String gender;

	@Column(name = "DOB")
	private Date dob;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	@Column(name = "PHONE_NO")
	private String phoneNo;
	
	@Column(name = "AADHAR_ID")
	private String aadharId;
	
	@Column(name = "FATHER_NAME")
	private String fatherName;
	
	@Column(name = "SPOUSE_NAME")
	private String spouseName;
	
	@Column(name = "MEMBER_TYPE")
	private String memberType;
	
	@Column(name = "CONSTITUENCY_NAME")
	private String constituencyName;
	
	@Column(name = "DIST_ID")
	private String disctCode;
	
	@Column(name = "PIN_CODE")
	private int pinCode;
	
	@Column(name = "TERM_START_DT")
	private Date termStartDate;
	
	@Column(name = "TERM_END_DT")
	private Date termEndDate;
	
	@Column(name = "PERM_ADDRESS")
	private String permnentAdd;
	
	@Column(name = "CONST_ADDRESS")
	private String constAddress;
	
	@Column(name = "OFFC_ADDRESS")
	private String officialAdd;
	
	@Column(name = "AADHAR_ACTUAL_NAME")
	private String aadharFilePath;
	
	@Column(name = "AADHAR_DOC_NAME")
	private String aadharDocName;
	
	@Column(name = "PHOTO_DOC_NAME")
	private String photoDocName;
	
	@Column(name = "PHOTO_ACTUAL_NAME")
	private String photoFilePath;
	
	@Column(name = "SUPPORT_ACTUAL_NAME")
	private String supportFilePath;
	
	@Column(name = "SUPPORT_DOC_NAME")
	private String supportDocName;
	
	@Column(name = "STATUS")
	private String isApproved;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "APPRV_REJ_DT")
	private Date approvedDt;
	
	@Column(name = "APPRV_REJ_USR")
	private String approvedUsr;
	
	@Column(name = "ACTIVE_YN")
	private String activeYN;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	private Date crtDt;
	
	@Column(name = "CRT_USR")
	private String crtUsr;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LST_UPD_DT")
	private Date lstUpdDt;
	
	@Column(name = "LST_UPD_USR")
	private String lstUpdUsr;
	
	@Column(name = "REJ_REMARKS")
	private String rejRemarks;
	
	@Column(name = "SIGNED_DOCUMENT_ACTUAL_NAME")
	private String signedDocFilePath;
	
	@Column(name = "SIGNED_DOCUMENT_NAME")
	private String signedDocName;
	
	@Column(name = "REV_REMARKS")
	private String revRemarks;
	
	@Column(name = "APPROVE_REMARKS")
	private String approveRemarks;
	
	
	public String getApproveRemarks() {
		return approveRemarks;
	}

	public void setApproveRemarks(String approveRemarks) {
		this.approveRemarks = approveRemarks;
	}

	public String getRevRemarks() {
		return revRemarks;
	}

	public void setRevRemarks(String revRemarks) {
		this.revRemarks = revRemarks;
	}

	public String getSignedDocFilePath() {
		return signedDocFilePath;
	}

	public void setSignedDocFilePath(String signedDocFilePath) {
		this.signedDocFilePath = signedDocFilePath;
	}

	public String getSignedDocName() {
		return signedDocName;
	}

	public void setSignedDocName(String signedDocName) {
		this.signedDocName = signedDocName;
	}

	public String getRejRemarks() {
		return rejRemarks;
	}

	public void setRejRemarks(String rejRemarks) {
		this.rejRemarks = rejRemarks;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public Date getApprovedDt() {
		return approvedDt;
	}

	public void setApprovedDt(Date approvedDt) {
		this.approvedDt = approvedDt;
	}

	public String getApprovedUsr() {
		return approvedUsr;
	}

	public void setApprovedUsr(String approvedUsr) {
		this.approvedUsr = approvedUsr;
	}

	public String getAadharFilePath() {
		return aadharFilePath;
	}

	public void setAadharFilePath(String aadharFilePath) {
		this.aadharFilePath = aadharFilePath;
	}

	public String getAadharDocName() {
		return aadharDocName;
	}

	public void setAadharDocName(String aadharDocName) {
		this.aadharDocName = aadharDocName;
	}

	public String getPhotoDocName() {
		return photoDocName;
	}

	public void setPhotoDocName(String photoDocName) {
		this.photoDocName = photoDocName;
	}

	public String getPhotoFilePath() {
		return photoFilePath;
	}

	public void setPhotoFilePath(String photoFilePath) {
		this.photoFilePath = photoFilePath;
	}

	public String getSupportFilePath() {
		return supportFilePath;
	}

	public void setSupportFilePath(String supportFilePath) {
		this.supportFilePath = supportFilePath;
	}

	public String getSupportDocName() {
		return supportDocName;
	}

	public void setSupportDocName(String supportDocName) {
		this.supportDocName = supportDocName;
	}

	public String getConstAddress() {
		return constAddress;
	}

	public void setConstAddress(String constAddress) {
		this.constAddress = constAddress;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getAadharId() {
		return aadharId;
	}

	public void setAadharId(String aadharId) {
		this.aadharId = aadharId;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getConstituencyName() {
		return constituencyName;
	}

	public void setConstituencyName(String constituencyName) {
		this.constituencyName = constituencyName;
	}

	public String getDisctCode() {
		return disctCode;
	}

	public void setDisctCode(String disctCode) {
		this.disctCode = disctCode;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public Date getTermStartDate() {
		return termStartDate;
	}

	public void setTermStartDate(Date termStartDate) {
		this.termStartDate = termStartDate;
	}

	public Date getTermEndDate() {
		return termEndDate;
	}

	public void setTermEndDate(Date termEndDate) {
		this.termEndDate = termEndDate;
	}

	public String getPermnentAdd() {
		return permnentAdd;
	}

	public void setPermnentAdd(String permnentAdd) {
		this.permnentAdd = permnentAdd;
	}

	public String getOfficialAdd() {
		return officialAdd;
	}

	public void setOfficialAdd(String officialAdd) {
		this.officialAdd = officialAdd;
	}

	public String getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public Date getLstUpdDt() {
		return lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	
	
	
}
