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
@Table(name="EHF_CASE_DAILYSIS_DTLS")
public class EhfCaseDialyDtls  implements Serializable{
	 
	 private EhfCaseDialyDtlsId id; 
	    private String cardNo;
	    private String patientPhotoName;
	    private String patientConsentName;
	    private String patientPhotoPath;
	    private String patientConsentPath;
	    private Date crtDt;
	    private String crtUsr;
	    private Date lstUpdDt;
	    private String lstUpdUsr;
	    
	 
	    @EmbeddedId
	    @AttributeOverrides( {
	                    @AttributeOverride(name = "CASE_ID", column = @Column(name = "CASE_ID", nullable = false)),
	                    @AttributeOverride(name = "CYCLE", column = @Column(name = "CYCLE", nullable = false)) })
	    
	    public EhfCaseDialyDtlsId getId() {
	 			return id;
	 		}
	 		public void setId(EhfCaseDialyDtlsId id) {
	 			this.id = id;
	 		}
	 	  
	   
	    @Column (name="CARD_NO")
		public String getCardNo() {
			return cardNo;
		}
		public void setCardNo(String cardNo) {
			this.cardNo = cardNo;
		}
		 @Column (name="PATIENT_PHOTO")
		public String getPatientPhotoPath() {
			return patientPhotoPath;
		}
		public void setPatientPhotoPath(String patientPhotoPath) {
			this.patientPhotoPath = patientPhotoPath;
		}
		 @Column (name="PATIENT_CONSENT")
		public String getPatientConsentPath() {
			return patientConsentPath;
		}
		public void setPatientConsentPath(String patientConsentPath) {
			this.patientConsentPath = patientConsentPath;
		}
		 @Column (name="FILE_PHOTO_NAME")
		public String getPatientPhotoName() {
			return patientPhotoName;
		}
		public void setPatientPhotoName(String patientPhotoName) {
			this.patientPhotoName = patientPhotoName;
		}
		 @Column (name="FILE_CONSENT_NAME")
		public String getPatientConsentName() {
			return patientConsentName;
		}
		public void setPatientConsentName(String patientConsentName) {
			this.patientConsentName = patientConsentName;
		}
		 @Temporal(TemporalType.TIMESTAMP)
		@Column (name="CRT_DT")
		public Date getCrtDt() {
			return crtDt;
		}
		public void setCrtDt(Date crtDt) {
			this.crtDt = crtDt;
		}
		@Column (name="CRT_USR")
		public String getCrtUsr() {
			return crtUsr;
		}
		public void setCrtUsr(String crtUsr) {
			this.crtUsr = crtUsr;
		}
		 @Temporal(TemporalType.TIMESTAMP)
		@Column (name="LST_UPD_DT")
		public Date getLstUpdDt() {
			return lstUpdDt;
		}
		public void setLstUpdDt(Date lstUpdDt) {
			this.lstUpdDt = lstUpdDt;
		}
		@Column (name="LST_UPD_USR")
		public String getLstUpdUsr() {
			return lstUpdUsr;
		}
		public void setLstUpdUsr(String lstUpdUsr) {
			this.lstUpdUsr = lstUpdUsr;
		}
		
		public EhfCaseDialyDtls() {
			super();
		}
		
		 
		    
		public EhfCaseDialyDtls(EhfCaseDialyDtlsId id, String cardNo,
				 String crtUsr, String lstUpdUsr,
				String erithInjctn, String patientId, 
				Date crtDt, String patientConsentPath, Date lstUpdDt, String patientPhotoPath) {
			super();
			this.id = id;
			this.cardNo = cardNo;
			this.patientConsentPath=patientConsentPath;
			this.patientPhotoPath=patientPhotoPath;
			this.crtUsr = crtUsr;
			this.crtDt = crtDt;
			this.lstUpdUsr = lstUpdUsr;
			this.lstUpdDt = lstUpdDt;
		}
		
		

}
