package com.ahct.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "LHS_DEPENDENT_DTLS")
public class LhsDependentDtls {

		@Id
		@Column(name = "SEQ_NO")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lhs_dependent_dtls_seq_generator")
		@SequenceGenerator(name = "lhs_dependent_dtls_seq_generator", sequenceName = "LHS_DEPENDENT_DTLS_SEQ", allocationSize = 1)
		private int seqNo;

		@Column(name = "ID_NO")
		private String idNo;
		
		@Column(name = "CARD_NO")
		private String cardNo;
		
		@Column(name = "DEPENDENT_NAME")
		private String dependentName;
		
		@Column(name = "GENDER")
		private String gender;
		
		//sowmya added
		@Column(name = "DOB")
		private Date dob;
		
		@Column(name = "AADHAR_ID")
		private String aadhaarId;
		
		@Column(name = "CONTACT_NO")
		private String phoneNo;
		
		@Column(name = "MARITAL_STATUS")
		private String maritalStatus;
		
		@Column(name = "RELATIONSHIP")
		private String relationShip;
		
		@Column(name = "AADHAR_ACTUAL_NAME")
		private String aadharFilePath;
		
		@Column(name = "AADHAR_DOC_NAME")
		private String aadharDocName;
		
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
		
		@Column(name = "STATUS")
		private String isApproved;
		
		@Temporal(TemporalType.TIMESTAMP)
		@Column(name = "APPRV_REJ_DT")
		private Date approvedDt;
		
		@Column(name = "APPRV_REJ_USR")
		private String approvedUsr;
		
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

		public int getSeqNo() {
			return seqNo;
		}

		public void setSeqNo(int seqNo) {
			this.seqNo = seqNo;
		}

		public String getIdNo() {
			return idNo;
		}

		public void setIdNo(String idNo) {
			this.idNo = idNo;
		}

		public String getCardNo() {
			return cardNo;
		}

		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}

		public String getDependentName() {
			return dependentName;
		}

		public void setDependentName(String dependentName) {
			this.dependentName = dependentName;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getMaritalStatus() {
			return maritalStatus;
		}

		public void setMaritalStatus(String maritalStatus) {
			this.maritalStatus = maritalStatus;
		}

		public String getRelationShip() {
			return relationShip;
		}

		public void setRelationShip(String relationShip) {
			this.relationShip = relationShip;
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

		public Date getDob() {
			return dob;
		}

		public void setDob(Date dob) {
			this.dob = dob;
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

		public String getAadhaarId() {
			return aadhaarId;
		}

		public void setAadhaarId(String aadhaarId) {
			this.aadhaarId = aadhaarId;
		}
		
		
		
}
