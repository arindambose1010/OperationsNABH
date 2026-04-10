package com.ahct.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the EHF_AHC_PATIENT_INVEST database table.
 * 
 */
@Entity
@Table(name="EHF_AHC_PATIENT_INVEST")
public class EhfAhcPatientInvest implements Serializable {
	private static final long serialVersionUID = 1L;
	private String ahcId;
	private Date crtDt;
	private String crtUsr;
	private String gender;
	private Date lstUpdDt;
	private String lstUpdUsr;
	private String schemeId;
	private String t1Com;
	private String t10Com;
	private String t11Com;
	private String t12Com;
	private String t13Com;
	private String t14Com;
	private String t15Com;
	private String t16Com;
	private String t17Com;
	private String t18Com;
	private String t19Com;
	private String t2Com;
	private String t20Com;
	private String t21Com;
	private String t22Com;
	private String t23Com;
	private String t24Com;
	private String t25Com;
	private String t26Fem;
	private String t27Fem;
	private String t28Fem;
	private String t3Com;
	private String t4Com;
	private String t5Com;
	private String t6Com;
	private String t7Com;
	private String t8Com;
	private String t9Com;

    public EhfAhcPatientInvest() {
    }


	@Id
	@Column(name="AHC_ID")
	public String getAhcId() {
		return this.ahcId;
	}

	public void setAhcId(String ahcId) {
		this.ahcId = ahcId;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="CRT_DT")
	public Date getCrtDt() {
		return this.crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}


	@Column(name="CRT_USR")
	public String getCrtUsr() {
		return this.crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}


	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}


    @Temporal( TemporalType.DATE)
	@Column(name="LST_UPD_DT")
	public Date getLstUpdDt() {
		return this.lstUpdDt;
	}

	public void setLstUpdDt(Date lstUpdDt) {
		this.lstUpdDt = lstUpdDt;
	}


	@Column(name="LST_UPD_USR")
	public String getLstUpdUsr() {
		return this.lstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		this.lstUpdUsr = lstUpdUsr;
	}


	@Column(name="SCHEME_ID")
	public String getSchemeId() {
		return this.schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}


	@Column(name="T1_COM")
	public String getT1Com() {
		return this.t1Com;
	}

	public void setT1Com(String t1Com) {
		this.t1Com = t1Com;
	}


	@Column(name="T10_COM")
	public String getT10Com() {
		return this.t10Com;
	}

	public void setT10Com(String t10Com) {
		this.t10Com = t10Com;
	}


	@Column(name="T11_COM")
	public String getT11Com() {
		return this.t11Com;
	}

	public void setT11Com(String t11Com) {
		this.t11Com = t11Com;
	}


	@Column(name="T12_COM")
	public String getT12Com() {
		return this.t12Com;
	}

	public void setT12Com(String t12Com) {
		this.t12Com = t12Com;
	}


	@Column(name="T13_COM")
	public String getT13Com() {
		return this.t13Com;
	}

	public void setT13Com(String t13Com) {
		this.t13Com = t13Com;
	}


	@Column(name="T14_COM")
	public String getT14Com() {
		return this.t14Com;
	}

	public void setT14Com(String t14Com) {
		this.t14Com = t14Com;
	}


	@Column(name="T15_COM")
	public String getT15Com() {
		return this.t15Com;
	}

	public void setT15Com(String t15Com) {
		this.t15Com = t15Com;
	}


	@Column(name="T16_COM")
	public String getT16Com() {
		return this.t16Com;
	}

	public void setT16Com(String t16Com) {
		this.t16Com = t16Com;
	}


	@Column(name="T17_COM")
	public String getT17Com() {
		return this.t17Com;
	}

	public void setT17Com(String t17Com) {
		this.t17Com = t17Com;
	}


	@Column(name="T18_COM")
	public String getT18Com() {
		return this.t18Com;
	}

	public void setT18Com(String t18Com) {
		this.t18Com = t18Com;
	}


	@Column(name="T19_COM")
	public String getT19Com() {
		return this.t19Com;
	}

	public void setT19Com(String t19Com) {
		this.t19Com = t19Com;
	}


	@Column(name="T2_COM")
	public String getT2Com() {
		return this.t2Com;
	}

	public void setT2Com(String t2Com) {
		this.t2Com = t2Com;
	}


	@Column(name="T20_COM")
	public String getT20Com() {
		return this.t20Com;
	}

	public void setT20Com(String t20Com) {
		this.t20Com = t20Com;
	}


	@Column(name="T21_COM")
	public String getT21Com() {
		return this.t21Com;
	}

	public void setT21Com(String t21Com) {
		this.t21Com = t21Com;
	}


	@Column(name="T22_COM")
	public String getT22Com() {
		return this.t22Com;
	}

	public void setT22Com(String t22Com) {
		this.t22Com = t22Com;
	}


	@Column(name="T23_COM")
	public String getT23Com() {
		return this.t23Com;
	}

	public void setT23Com(String t23Com) {
		this.t23Com = t23Com;
	}


	@Column(name="T24_COM")
	public String getT24Com() {
		return this.t24Com;
	}

	public void setT24Com(String t24Com) {
		this.t24Com = t24Com;
	}


	@Column(name="T25_COM")
	public String getT25Com() {
		return this.t25Com;
	}

	public void setT25Com(String t25Com) {
		this.t25Com = t25Com;
	}


	@Column(name="T26_FEM")
	public String getT26Fem() {
		return this.t26Fem;
	}

	public void setT26Fem(String t26Fem) {
		this.t26Fem = t26Fem;
	}


	@Column(name="T27_FEM")
	public String getT27Fem() {
		return this.t27Fem;
	}

	public void setT27Fem(String t27Fem) {
		this.t27Fem = t27Fem;
	}


	@Column(name="T28_FEM")
	public String getT28Fem() {
		return this.t28Fem;
	}

	public void setT28Fem(String t28Fem) {
		this.t28Fem = t28Fem;
	}


	@Column(name="T3_COM")
	public String getT3Com() {
		return this.t3Com;
	}

	public void setT3Com(String t3Com) {
		this.t3Com = t3Com;
	}


	@Column(name="T4_COM")
	public String getT4Com() {
		return this.t4Com;
	}

	public void setT4Com(String t4Com) {
		this.t4Com = t4Com;
	}


	@Column(name="T5_COM")
	public String getT5Com() {
		return this.t5Com;
	}

	public void setT5Com(String t5Com) {
		this.t5Com = t5Com;
	}


	@Column(name="T6_COM")
	public String getT6Com() {
		return this.t6Com;
	}

	public void setT6Com(String t6Com) {
		this.t6Com = t6Com;
	}


	@Column(name="T7_COM")
	public String getT7Com() {
		return this.t7Com;
	}

	public void setT7Com(String t7Com) {
		this.t7Com = t7Com;
	}


	@Column(name="T8_COM")
	public String getT8Com() {
		return this.t8Com;
	}

	public void setT8Com(String t8Com) {
		this.t8Com = t8Com;
	}


	@Column(name="T9_COM")
	public String getT9Com() {
		return this.t9Com;
	}

	public void setT9Com(String t9Com) {
		this.t9Com = t9Com;
	}

}