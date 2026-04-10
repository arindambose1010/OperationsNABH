package com.ahct.model;

import java.io.Serializable;
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


@Entity
@Table(name="EHFM_DISP_USR_MPG")

public class EhfmDispUsrMpg implements Serializable {
	private EhfmDispUsrMpgId id;
	private Date startDt;
	private Date endDt;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String scheme;
	
	@EmbeddedId
	@AttributeOverrides({
			@AttributeOverride(name = "dispId", column = @Column(name = "DISP_ID", nullable = false)),
			@AttributeOverride(name = "userId", column = @Column(name = "USER_ID", nullable = false)),
			@AttributeOverride(name = "grpId", column = @Column(name = "GRP_ID", nullable = false)),
			@AttributeOverride(name = "activeyn", column = @Column(name = "ACTIVE_YN", nullable = false)) })
	public EhfmDispUsrMpgId getId() {
		return id;
	}
	public void setId(EhfmDispUsrMpgId id) {
		this.id = id;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="START_DT",nullable=true)
	public Date getStartDt() {
		return startDt;
	}
	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="END_DT",nullable=true)
	public Date getEndDt() {
		return endDt;
	}
	public void setEndDt(Date endDt) {
		this.endDt = endDt;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CRT_DT",nullable=true)
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	@Column(name="CRT_USR",nullable=true)
	public String getCrtUsr() {
		return crtUsr;
	}
	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LST_UPD_DT",nullable=true)
	public Date getlstUpdDt() {
		return lstUpdDt;
	}
	public void setlstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}
	@Column(name="LST_UPD_USR",nullable=true)
	public String getlstUpdUsr() {
		return lstUpdUsr;
	}
	public void setlstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}
	@Column(name="SCHEME",nullable=true)
	public String getScheme() {
		return scheme;
	}
	
	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public EhfmDispUsrMpg() {
		super();
	}
	public EhfmDispUsrMpg(EhfmDispUsrMpgId id,Date startDt, Date crtDt, String crtUsr,String lstUpdUsr,Date lstUpdDt,Date endDt,String scheme ) {
		super();
		
		this.id=id;
		this.startDt = startDt;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt=lstUpdDt;
		this.lstUpdUsr=lstUpdUsr;
		this.endDt =endDt;
		this.scheme=scheme;
	}
	
}
