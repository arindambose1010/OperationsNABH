
package com.ahct.model;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_CLAIM_DEDUCTION_DTLS")
public class EhfClaimDeductionDtls implements java.io.Serializable {

	private String totalClaim;
	private String dedAmount;
	private String claimableAmount;
	private String dedReamrks;
	private String crtUsr;
	private Date crtDt;
	private String lstUpdUsr;
	private Date lstUpdDt;
    private String emailSent;
    private String medcoUsrId;
    private String medcoEmail;
    private String DedctUserId;
    private String DedctName;
    private String grpId;
    private String hospId;
    private String nabh_hosp;
    private EhfClaimDeductionsPK id;
    
    @EmbeddedId

    @AttributeOverrides ( { @AttributeOverride ( name = "caseId", column = @Column ( name = "case_Id", nullable = false, length = 12 )
        )
        , @AttributeOverride ( name = "caseStatus", column = @Column ( name = "case_status", nullable = false, length = 12 )
        )
        
        } )
        public EhfClaimDeductionsPK getId() {
            return id;
        }
    public void setId(EhfClaimDeductionsPK id) {
        this.id = id;
    }
 
    @Column(name = "grp_id")
    public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	
	@Column(name = "hosp_id")
	public String getHospId() {
		return hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}
	@Column(name = "nabh_hosp")
	public String getNabh_hosp() {
		return nabh_hosp;
	}

	public void setNabh_hosp(String nabh_hosp) {
		this.nabh_hosp = nabh_hosp;
	}

	@Column(name = "Medco_user_id")
	public String getMedcoUsrId() {
		return medcoUsrId;
	}

	public void setMedcoUsrId(String medcoUsrId) {
		this.medcoUsrId = medcoUsrId;
	}
	@Column(name = "medco_Emial_id")
	public String getMedcoEmail() {
		return medcoEmail;
	}

	public void setMedcoEmail(String medcoEmail) {
		this.medcoEmail = medcoEmail;
	}
	@Column(name = "dedct_usr_id")
	public String getDedctUserId() {
		return DedctUserId;
	}

	public void setDedctUserId(String dedctUserId) {
		DedctUserId = dedctUserId;
	}

	
	@Column(name = "CLAIM_INT_AMT")
	public String getTotalClaim() {
		return totalClaim;
	}

	public void setTotalClaim(String totalClaim) {
		this.totalClaim = totalClaim;
	}


	@Column(name = "CRT_USR", length = 30)
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	@Column(name = "LST_UPD_USR", length = 30)
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
    @Column(name = "DED_Amount")
	public String getDedAmount() {
		return dedAmount;
	}

	public void setDedAmount(String dedAmount) {
		this.dedAmount = dedAmount;
	}
	@Column(name = "APPROVED_AMT")
	public String getClaimableAmount() {
		return claimableAmount;
	}

	public void setClaimableAmount(String claimableAmount) {
		this.claimableAmount = claimableAmount;
	}
	@Column(name = "ded_Reamarks")
	public String getDedReamrks() {
		return dedReamrks;
	}

	public void setDedReamrks(String dedReamrks) {
		this.dedReamrks = dedReamrks;
	}
	@Column(name = "EMAIL_SENT")
	public String getEmailSent() {
		return emailSent;
	}
	public void setEmailSent(String emailSent) {
		this.emailSent = emailSent;
	}
	
	@Column(name = "dedct_name")
	public String getDedctName() {
		return DedctName;
	}

	public void setDedctName(String dedctName) {
		DedctName = dedctName;
	}


	
}
