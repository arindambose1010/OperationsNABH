package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_DISP_DRUG_PO_AUDIT database table.
 * 
 */
@Entity
@Table(name="EHF_DISP_DRUG_PO_AUDIT")
@NamedQuery(name="EhfDispDrugPoAudit.findAll", query="SELECT e FROM EhfDispDrugPoAudit e")
public class EhfDispDrugPoAudit implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EhfDispDrugPoAuditPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="CRT_DT")
	private Date crtDt;

	@Column(name="CRT_USR")
	private String crtUsr;

	/*@Temporal(TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	private Date lstUpdDt;

	@Column(name="LST_UPD_USR")
	private String lstUpdUsr;*/

	private String received;

	private String status;
	@Column(name="PO_ID1")
	private String poId1;


	public String getPoId1() {
		return poId1;
	}

	public void setPoId1(String poId1) {
		this.poId1 = poId1;
	}

	public EhfDispDrugPoAudit() {
	}

	public EhfDispDrugPoAuditPK getId() {
		return this.id;
	}

	public void setId(EhfDispDrugPoAuditPK id) {
		this.id = id;
	}

	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	/*public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}

	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}*/

	public String getReceived() {
		return this.received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}