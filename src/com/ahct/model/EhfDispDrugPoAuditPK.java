package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the EHF_DISP_DRUG_PO_AUDIT database table.
 * 
 */
@Embeddable
public class EhfDispDrugPoAuditPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="MNFCTR_PO_ID")
	private String mnfctrPoId;

	@Column(name="DRUG_ID")
	private String drugId;

	@Column(name="ACT_ORDER")
	private long actOrder;

	public EhfDispDrugPoAuditPK() {
	}
	public String getMnfctrPoId() {
		return this.mnfctrPoId;
	}
	public void setMnfctrPoId(String mnfctrPoId) {
		this.mnfctrPoId = mnfctrPoId;
	}
	public String getDrugId() {
		return this.drugId;
	}
	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}
	public long getActOrder() {
		return this.actOrder;
	}
	public void setActOrder(long actOrder) {
		this.actOrder = actOrder;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof EhfDispDrugPoAuditPK)) {
			return false;
		}
		EhfDispDrugPoAuditPK castOther = (EhfDispDrugPoAuditPK)other;
		return 
			this.mnfctrPoId.equals(castOther.mnfctrPoId)
			&& this.drugId.equals(castOther.drugId)
			&& (this.actOrder == castOther.actOrder);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mnfctrPoId.hashCode();
		hash = hash * prime + this.drugId.hashCode();
		hash = hash * prime + ((int) (this.actOrder ^ (this.actOrder >>> 32)));
		
		return hash;
	}
}