package com.ahct.model;

import java.io.Serializable;
import java.util.Date;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "EHF_DISP_DENTAL_PATIENT")
public class EhfDispDentalPatient implements Serializable{
	
	private String patientId;
	private String diabetesRemarks;
	private String hypertensionRemarks;
	private String cardiacHistRemarks;
	private String drugAllergyRemarks;
	private String thyroidRemarks;
	private String oralProphylaxisRemarks;
	private String compositeRemarks;
	private String gicRemarks;
	private String pitFissureSealantsRemarks;
	private String extractionRemarks;
	private String medicationRemarks;
	private Date crtDt;
	private String crtUsr;
	private Date updDt;
	private String updUsr;
	
	public EhfDispDentalPatient() {
    }
	
    @Id
    @Column ( name = "patient_id", nullable = false )
	public String getPatientId() {
		return patientId;
	}
    
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Column ( name = "DIABETES_REMARKS" )
	public String getDiabetesRemarks() {
		return diabetesRemarks;
	}

	public void setDiabetesRemarks(String diabetesRemarks) {
		this.diabetesRemarks = diabetesRemarks;
	}
	
	@Column ( name = "HYPERTENSION_REMARKS" )
	public String getHypertensionRemarks() {
		return hypertensionRemarks;
	}

	public void setHypertensionRemarks(String hypertensionRemarks) {
		this.hypertensionRemarks = hypertensionRemarks;
	}

	@Column ( name = "CARDIAC_HIST_REMARKS" )
	public String getCardiacHistRemarks() {
		return cardiacHistRemarks;
	}

	public void setCardiacHistRemarks(String cardiacHistRemarks) {
		this.cardiacHistRemarks = cardiacHistRemarks;
	}

	@Column ( name = "DRUG_ALLERGY_REMARKS" )
	public String getDrugAllergyRemarks() {
		return drugAllergyRemarks;
	}

	public void setDrugAllergyRemarks(String drugAllergyRemarks) {
		this.drugAllergyRemarks = drugAllergyRemarks;
	}

	@Column ( name = "THYROID_REMARKS" )
	public String getThyroidRemarks() {
		return thyroidRemarks;
	}

	public void setThyroidRemarks(String thyroidRemarks) {
		this.thyroidRemarks = thyroidRemarks;
	}

	@Column ( name = "ORAL_PROPHYLAXIS_REMARKS" )
	public String getOralProphylaxisRemarks() {
		return oralProphylaxisRemarks;
	}

	public void setOralProphylaxisRemarks(String oralProphylaxisRemarks) {
		this.oralProphylaxisRemarks = oralProphylaxisRemarks;
	}

	@Column ( name = "COMPOSITE_REMARKS" )
	public String getCompositeRemarks() {
		return compositeRemarks;
	}

	public void setCompositeRemarks(String compositeRemarks) {
		this.compositeRemarks = compositeRemarks;
	}

	@Column ( name = "GIC_REMARKS" )
	public String getGicRemarks() {
		return gicRemarks;
	}

	public void setGicRemarks(String gicRemarks) {
		this.gicRemarks = gicRemarks;
	}

	@Column ( name = "PIT_FISSURESEALANTS_REMARKS" )
	public String getPitFissureSealantsRemarks() {
		return pitFissureSealantsRemarks;
	}

	public void setPitFissureSealantsRemarks(String pitFissureSealantsRemarks) {
		this.pitFissureSealantsRemarks = pitFissureSealantsRemarks;
	}

	@Column ( name = "EXTRACTION_REMARKS" )
	public String getExtractionRemarks() {
		return extractionRemarks;
	}

	public void setExtractionRemarks(String extractionRemarks) {
		this.extractionRemarks = extractionRemarks;
	}

	@Column ( name = "MEDICATION_REMARKS" )
	public String getMedicationRemarks() {
		return medicationRemarks;
	}

	public void setMedicationRemarks(String medicationRemarks) {
		this.medicationRemarks = medicationRemarks;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column ( name = "CRT_DT" )
	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	
	@Column ( name = "CRT_USR" )
	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column ( name = "UPD_DT" )
	public Date getUpdDt() {
		return updDt;
	}

	public void setUpdDt(Date updDt) {
		this.updDt = updDt;
	}
	
	@Column ( name = "UPD_USR" )
	public String getUpdUsr() {
		return updUsr;
	}

	public void setUpdUsr(String updUsr) {
		this.updUsr = updUsr;
	}

}
