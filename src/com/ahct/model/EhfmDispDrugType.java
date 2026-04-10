package com.ahct.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EHFM_DISP_DRUG_TYPE")
public class EhfmDispDrugType  implements Serializable{

	private String drugTypeId;
	private String drugTypeName;
	
	@Id
	@Column(name="DRUG_TYPE_ID")
	public String getDrugTypeId() {
		return drugTypeId;
	}
	public void setDrugTypeId(String drugTypeId) {
		this.drugTypeId = drugTypeId;
	}
	
	@Column(name="DRUG_TYPE_NAME")
	public String getDrugTypeName() {
		return drugTypeName;
	}
	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}
	
	
}
