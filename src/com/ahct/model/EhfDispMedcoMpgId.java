package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;

public class EhfDispMedcoMpgId implements Serializable {

	private String dispId;
	private String grpId;
	private String medcoId;
	
	public EhfDispMedcoMpgId(java.lang.String dispId, java.lang.String grpId,
			java.lang.String medcoId) {
		// TODO Auto-generated constructor stub
		this.dispId=dispId;
		this.grpId=grpId;
		this.medcoId=medcoId;
	}
	@Column(name="DISP_ID")
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}
	
	@Column(name="GRP_ID")
	public String getGrpId() {
		return grpId;
	}
	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}
	
	@Column(name="MEDCO_ID")
	public String getMedcoId() {
		return medcoId;
	}
	public void setMedcoId(String medcoId) {
		this.medcoId = medcoId;
	}
	
	
	
	
	
	
	
	
}
