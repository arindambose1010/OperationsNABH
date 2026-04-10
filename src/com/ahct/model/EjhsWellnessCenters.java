package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EJHS_WELLNESS_CENTERS")
public class EjhsWellnessCenters  implements Serializable {
	
	
	private String centerId;

	private String centerName;

	private String activeYN;
	private String dispId;
	@Id
	@Column(name = "CENTER_ID")
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	@Column(name = "CENTER_NAME")
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
	@Column(name = "ACTIVE_YN")
	public String getActiveYN() {
		return activeYN;
	}
	public void setActiveYN(String activeYN) {
		this.activeYN = activeYN;
	}
	@Column(name = "DISP_ID")
	public String getDispId() {
		return dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

}
