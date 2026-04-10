package com.ahct.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "EHF_DISP_MEDCO_MPG")
public class EhfDispMedcoMpg implements Serializable {
	
	private EhfDispMedcoMpgId id;
	private Date startDate ;
	private String  roomNo;
	private String tokenNo;
	private Date currentDate;
	
	@EmbeddedId
	public EhfDispMedcoMpgId getId() {
		return id;
	}
	public void setId(EhfDispMedcoMpgId id) {
		this.id = id;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="START_DATE")
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Column(name="ROOM_NO")
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	
	@Column(name="TOKEN_NO")
	public String getTokenNo() {
		return tokenNo;
	}
	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="CURRENT_DATE")
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	
	
	
	

}
