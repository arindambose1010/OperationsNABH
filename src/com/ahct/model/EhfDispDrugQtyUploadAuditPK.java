package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Chandana - 8251 - The persistent class for the EHF_DISP_DRUG_QTY_UPLOAD_AUDIT Primary key database table.
 * 
 */

@Embeddable
public class EhfDispDrugQtyUploadAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="SEQ_ID")
	private long seqId;

	@Column(name="DRUG_CODE")
	private String drugCode;

	@Column(name="DISP_ID")
	private String dispId;

	public EhfDispDrugQtyUploadAuditPK() {
	}
	public EhfDispDrugQtyUploadAuditPK(long seqId,String drugCode,String dispId) {
		this.seqId = seqId;
		this.drugCode = drugCode;
		this.dispId = dispId;
	}
	public long getSeqId() {
		return this.seqId;
	}
	public void setSeqId(long seqId) {
		this.seqId = seqId;
	}
	public String getDrugCode() {
		return this.drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getDispId() {
		return this.dispId;
	}
	public void setDispId(String dispId) {
		this.dispId = dispId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfDispDrugQtyUploadAuditPK)) {
			return false;
		}
		EhfDispDrugQtyUploadAuditPK castOther = (EhfDispDrugQtyUploadAuditPK)other;
		return 
			(this.seqId == castOther.seqId)
			&& this.drugCode.equals(castOther.drugCode)
			&& this.dispId.equals(castOther.dispId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.seqId ^ (this.seqId >>> 32)));
		hash = hash * prime + this.drugCode.hashCode();
		hash = hash * prime + this.dispId.hashCode();
		
		return hash;
	}
}