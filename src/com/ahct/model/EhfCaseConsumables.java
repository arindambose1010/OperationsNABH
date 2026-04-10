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
@Table(name="EHF_CASE_CONSUMABLES")
public class EhfCaseConsumables implements Serializable {
	private EhfCaseConsumablesId id;
	private String medcoUnits;
	private String cexUnits;
	private String cexRemarks;
	private double unitAmount;
	private double totalAmount;
	private Date crtDt;
	private String crtUsr;
	private Date lstUpdDt;
	private String lstUpdUsr;
	@EmbeddedId
	@AttributeOverrides( {
	   @AttributeOverride(name="caseId", column=@Column(name="CASE_ID", nullable=false) ),
	   @AttributeOverride(name="enhQuanCode", column=@Column(name="ENH_QUAN_CODE", nullable=false) )
	   } )
	public EhfCaseConsumablesId getId() {
		return id;
	}
	public void setId(EhfCaseConsumablesId id) {
		this.id = id;
	}
	
	@Column(name="MEDCO_UNITS")
	public String getMedcoUnits() {
		return medcoUnits;
	}
	public void setMedcoUnits(String medcoUnits) {
		this.medcoUnits = medcoUnits;
	}
	
	@Column(name="CEX_UNITS")
	public String getCexUnits() {
		return cexUnits;
	}
	public void setCexUnits(String cexUnits) {
		this.cexUnits = cexUnits;
	}
	
	@Column(name="CEX_REMARKS")
	public String getCexRemarks() {
		return cexRemarks;
	}
	public void setCexRemarks(String cexRemarks) {
		this.cexRemarks = cexRemarks;
	}
	
	@Column(name="UNIT_AMOUNT")
	public double getUnitAmount() {
		return unitAmount;
	}
	public void setUnitAmount(double unitAmount) {
		this.unitAmount = unitAmount;
	}
	
	@Column(name="TOTAL_AMOUNT")
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
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
	@Temporal(TemporalType.TIMESTAMP)
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
	
	public EhfCaseConsumables(EhfCaseConsumablesId id,String medcoUnits,String cexUnits,
			String cexRemarks,double unitAmount,double totalAmount,Date crtDt,String crtUsr,Date lstUpdDt,String lstUpdUsr) {
		super();
		this.id = id;
		this.medcoUnits = medcoUnits;
		this.cexUnits = cexUnits;
		this.cexRemarks = cexRemarks;
		this.unitAmount = unitAmount;
		this.totalAmount = totalAmount;
		this.crtDt = crtDt;
		this.crtUsr = crtUsr;
		this.lstUpdDt = lstUpdDt;
		this.lstUpdUsr = lstUpdUsr;
		
	}
	public EhfCaseConsumables() {
		super();
	}
	
	
}
