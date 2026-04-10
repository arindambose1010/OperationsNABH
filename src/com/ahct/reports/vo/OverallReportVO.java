package com.ahct.reports.vo;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class OverallReportVO implements Serializable{

	private String hosp;
	private String procedure;
	private String diagnosis;
	private String slab;
	private String cardType;
	private String TDS_AMT;
	private String RF_AMT;
	private String fromDate;
	private String toDate;
	private String hospType;
	private String govtHospType;
	private String patDist;
	private String hospDist;
	private String hospName;
	private String patDistrictId;
	private String patMandalId;
	private String hospDistId;
	private String categoryType;
	private String procedureType;
	private String[] categoryList;
	private String[] procedureList;
	private String categoryCode;
	private String icdCatName;
	private String procedureCode;
	private String procName;
	private String catName;
	private String hospDistChk;
	private String patDistChk;
	private String mndl;
	private String gender;
	private String caste;
	private String date;
	private String age;
	//added bhaskar
	private String deathCase;
	private String hospAmt;
	private String billAmt;
	private String patIpOpType;
	private String relation;
	private String monthWise;
	private String year;
	private String hospTypeChk;
	private String designation;
	private String department;
	private String patName;
	private String cardNo;
	private String address;
	private String contactNo;
	private String village;
	private String nhCode;
	private String nhLocation;
	private String patType;
	private String regDate;
	private String cardTypeChk;
	private String hospTypeExcd;
	private String beneficiaryType;
	private String hodId;
	private String teleCase;
	private Number therapiesPreauthed;
	private String therapiesPreauthedAmt;
	private Number therapiesRaised;
	private String therapiesRaisedAmt;
	private Number therpiesDone;
	private String therpiesDoneAmt;
	private String comorbidAmt;
	private Number noOfPatients;
	private String enhApprvAmt;
	private String preauthAppvAmt;
	private Number dischargeCnt;
	private Number dischargeUpdCnt;
	private String ipopDate;
	private Number claimSubmittedCnt;
	private String claimSubmittedAmt;
	private Number claimPaidCnt;
	private String claimPaidAmt;
	private Number claimApprovedCnt;
	private String claimApprovedAmt;
	private String sfpDate;
	private String statusDate;
	private String tdDate;
	private String sfpFromDate;
	private String sfpToDate;
	private String tpFromDate;
	private String tpToDate;
	private String caseNo;
	private String claimNo;
	private String sName;
	private String iName;
	private String wellnesscenter;
	private String dispname;
	private String selfDependent;
	private String caseRegDt;
	private String USERID;
	private String DOCTORNAME;
	private String CONTACT;
	private String TYPE;
	private String dispCode;
	
	private String caseStatus;
	private String preauthApprvDt;
	private String preauthStatus;
	private String caseSurgDt;
	private String caseSurgUpdDt;
	private String enhRaisedAmt;
	private String enhRaisedDt;
	private String enhApprvDt;
	private String deathDt;
	private String deathType;
	private String deathTypeCode;
	private String treatingDocName;
	private String docRegNum;
	private String dischargeDt;
	private String dischargeUpdDt;
	private String telephonicInDt;
	private String claimSubDt;
	private String userState;
	private String categoryFlag;
	private Number TOTAL_REG_STATE;
    private Number TOTAL_OP_REG;
    private Number NIMS_REG;
    private Number NIMS_OP_REG;
    private Number TOTAL_CHRONIC_REG;
    private Number NIMS_CHRONIC_OP;
	private int start_index;
	private int end_index;
	private Number COUNT = 0;
	private int maxresults;
	private int pageId;
 	private int totalPages;
 	private int totalRecords;
	private int strtIndex;
	private Number ROWNUM_;
	private List<OverallReportVO> claimList;
	private String csvFlag;
	private String CARD_NO;
	private String PATIENT_ID;
	private String CARD_TYPE;
	private String CASE_NO;
	private String CLAIM_NO;
	private String PATIENT_NAME;
	private String AGE;
	private String GENDER;
	private String CNTCT_NO;
	private String ADDRESS;
	private String VILLAGE;
	private String MANDAL;
	private String PATIENT_DIST;
	private String DDO_STO_DIST;
	private String HOSP_CODE;
	private String HOSP_NAME;
	private String HOSP_LOC;
	private String HOSP_DIST;
	private String HOSP_TYPE;
	private String CATGRY_NAME;
	private String CATGRY_CODE;
	private String PACKG_AMNT;
	private String PREAUTH_STATUS_DT;
	private String PREAUTH_APPRVD_AMNT;
	private String CL_SUB_AMT;
	private String CLAIM_SUB_DT;
	private String CURRENT_STATUS_DATE;
	private String PREV_STATUS;
	private String PREV_DATE;
	private String ACT_NO;
	private Number TOTAL;
	private Number PACKAGE_AMOUNT;
	
	private String FLG;
	private String EMPLOYEE;
	private String PENSIONER;
	private String JOURNALIST;
	private String RETIRED_JOURNALIST;
	private Number LAST24HOURS;
	private Number COUNT_NO;
	private Number AMOUNT;
	private Number COUNT_NO1;
	private Number AMOUNT1;
	private Number COUNT_NO2;
	private Number AMOUNT2;
	private Number CEX_COUNT;
	private Number CEX_AMOUNT;
	private Number CPD_COUNT;
	private Number CPD_AMOUNT;
	private Number CTD_COUNT;
	private Number CTD_AMOUNT;
	private Number CH_COUNT;
	private Number CH_AMOUNT;
	private Number ACO_COUNT;
	private Number ACO_AMOUNT;
	private Number MEDCO_COUNT;
	private Number MEDCO_AMOUNT;
	private Number CEO_COUNT;
	private Number CEO_AMOUNT;
	private Number CEX_COUNT1;
	private Number CEX_AMOUNT1;
	private Number COUNT_NO3;
	private String TRUST;
	private String TRUST1;
	private String HOSPITAL;
	private String TRUST2;
	private String TRUST3;
	private String HOSPITAL2;
	private Number TOT;
	private Number NABHCEX_COUNT;
	private Number NABHCEX_COUNT1;
	private Number NABHCPD_COUNT;
	private Number NABHCPD_COUNT1;
	private Number NABHCTD_COUNT;
	private Number NABHCTD_COUNT1;
	private Number NABHCH_COUNT;
	private Number NABHCH_COUNT1;
	private Number NABHACO_COUNT;
	private Number NABHACO_COUNT1;
	private Number NABHMEDCO_COUNT;
	private Number NABHMEDCO_COUNT1;
	private Number NABHCEO_COUNT;
	private Number NABHCEO_COUNT1;
	private Number NABHTOT;
	private Number NABHTOT1;
	private Number NABHTOT2;
	private Number NABHTOT3;
	private Number NABHCEX_AMOUNT;
	private Number NABHCEX_AMOUNT1;
	private Number NABHCPD_AMOUNT;
	private Number NABHCPD_AMOUNT1;
	private Number NABHCTD_AMOUNT;
	private Number NABHCTD_AMOUNT1;
	private Number NABHCH_AMOUNT;
	private Number NABHCH_AMOUNT1;
	private Number NABHACO_AMOUNT;
	private Number NABHACO_AMOUNT1;
	private Number NABHCOUNT_NO;
	private Number NABHAMOUNT;
	private Number NABHCOUNT_NO1;
	private Number NABHAMOUNT1;
	private Number NABHCOUNT_NO2;
	private Number NABHAMOUNT2;
	private Number NABHCOUNT_NO3;
	private Number NABHAMOUNT3;
	private Number NABHCOUNT_NO4;
	private Number NABHAMOUNT4;
	private Number NABHCOUNT_NO5;
	private Number ACK_RCVD_BANK_COUNT;
	private Number ACK_RCVD_BANK_AMOUNT;
	private Number CLAIM_REJ_COUNT;
	private Number CLAIM_REJ_AMOUNT;
	private Number CLAIM_SENT_COUNT;
	private Number CLAIM_SENT_AMOUNT;
	private Number ACK_RCVD_BANK_COUNT1;
	private Number ACK_RCVD_BANK_AMOUNT1;
	private Number CLAIM_REJ_COUNT1;
	private Number CLAIM_REJ_AMOUNT1;
	private Number CLAIM_SENT_COUNT1;
	private Number BANK1;
	private Number BANK2;
	private Number BANK3;
	private Number BANK4;
	private Number BANK5;
	private Number BANK6;
	private Number BANK7;
	private Number BANK8;
	private Number EHS_COUNT;
	private Number EHS_AMOUNT;
	private Number JHS_COUNT;
	private String EMPCOMMTY;
	private String EMPPOSTDIST;
	private String PATTYPE;
	private String BENTYPE;
	private String CLAIMPAIDDT;
	private Number EO_COUNT;
	private Number EO_AMOUNT;
	private Number EOCOMM_COUNT;
	private Number EOCOMM_AMOUNT;
	private Number EO_COUNT1;
	private Number EO_AMOUNT1;
	private Number EOCOMM_COUNT1;
	private Number EOCOMM_AMOUNT1;
	private Number NABHEO_COUNT;
	private Number NABHEO_AMOUNT;
	private Number NABHEOCOMM_COUNT;
	private Number NABHEOCOMM_AMOUNT;
	private Number NABHEO_COUNT1;
	private Number NABHEO_AMOUNT1;
	private Number NABHEOCOMM_COUNT1;
	private Number NABHEOCOMM_AMOUNT1;
	
	private String TotalNoCases;
	private String TotalAmount;
	
	
	
	public String getTotalNoCases() {
		return TotalNoCases;
	}
	public void setTotalNoCases(String totalNoCases) {
		TotalNoCases = totalNoCases;
	}
	public String getTotalAmount() {
		return TotalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		TotalAmount = totalAmount;
	}
	public Number getEO_COUNT() {
		return EO_COUNT;
	}
	public void setEO_COUNT(Number eO_COUNT) {
		EO_COUNT = eO_COUNT;
	}
	public Number getEO_AMOUNT() {
		return EO_AMOUNT;
	}
	public void setEO_AMOUNT(Number eO_AMOUNT) {
		EO_AMOUNT = eO_AMOUNT;
	}
	public Number getEOCOMM_COUNT() {
		return EOCOMM_COUNT;
	}
	public void setEOCOMM_COUNT(Number eOCOMM_COUNT) {
		EOCOMM_COUNT = eOCOMM_COUNT;
	}
	public Number getEOCOMM_AMOUNT() {
		return EOCOMM_AMOUNT;
	}
	public void setEOCOMM_AMOUNT(Number eOCOMM_AMOUNT) {
		EOCOMM_AMOUNT = eOCOMM_AMOUNT;
	}
	public Number getEO_COUNT1() {
		return EO_COUNT1;
	}
	public void setEO_COUNT1(Number eO_COUNT1) {
		EO_COUNT1 = eO_COUNT1;
	}
	public Number getEO_AMOUNT1() {
		return EO_AMOUNT1;
	}
	public void setEO_AMOUNT1(Number eO_AMOUNT1) {
		EO_AMOUNT1 = eO_AMOUNT1;
	}
	public Number getEOCOMM_COUNT1() {
		return EOCOMM_COUNT1;
	}
	public void setEOCOMM_COUNT1(Number eOCOMM_COUNT1) {
		EOCOMM_COUNT1 = eOCOMM_COUNT1;
	}
	public Number getEOCOMM_AMOUNT1() {
		return EOCOMM_AMOUNT1;
	}
	public void setEOCOMM_AMOUNT1(Number eOCOMM_AMOUNT1) {
		EOCOMM_AMOUNT1 = eOCOMM_AMOUNT1;
	}
	public Number getNABHEO_COUNT() {
		return NABHEO_COUNT;
	}
	public void setNABHEO_COUNT(Number nABHEO_COUNT) {
		NABHEO_COUNT = nABHEO_COUNT;
	}
	public Number getNABHEO_AMOUNT() {
		return NABHEO_AMOUNT;
	}
	public void setNABHEO_AMOUNT(Number nABHEO_AMOUNT) {
		NABHEO_AMOUNT = nABHEO_AMOUNT;
	}
	public Number getNABHEOCOMM_COUNT() {
		return NABHEOCOMM_COUNT;
	}
	public void setNABHEOCOMM_COUNT(Number nABHEOCOMM_COUNT) {
		NABHEOCOMM_COUNT = nABHEOCOMM_COUNT;
	}
	public Number getNABHEOCOMM_AMOUNT() {
		return NABHEOCOMM_AMOUNT;
	}
	public void setNABHEOCOMM_AMOUNT(Number nABHEOCOMM_AMOUNT) {
		NABHEOCOMM_AMOUNT = nABHEOCOMM_AMOUNT;
	}
	public Number getNABHEO_COUNT1() {
		return NABHEO_COUNT1;
	}
	public void setNABHEO_COUNT1(Number nABHEO_COUNT1) {
		NABHEO_COUNT1 = nABHEO_COUNT1;
	}
	public Number getNABHEO_AMOUNT1() {
		return NABHEO_AMOUNT1;
	}
	public void setNABHEO_AMOUNT1(Number nABHEO_AMOUNT1) {
		NABHEO_AMOUNT1 = nABHEO_AMOUNT1;
	}
	public Number getNABHEOCOMM_COUNT1() {
		return NABHEOCOMM_COUNT1;
	}
	public void setNABHEOCOMM_COUNT1(Number nABHEOCOMM_COUNT1) {
		NABHEOCOMM_COUNT1 = nABHEOCOMM_COUNT1;
	}
	public Number getNABHEOCOMM_AMOUNT1() {
		return NABHEOCOMM_AMOUNT1;
	}
	public void setNABHEOCOMM_AMOUNT1(Number nABHEOCOMM_AMOUNT1) {
		NABHEOCOMM_AMOUNT1 = nABHEOCOMM_AMOUNT1;
	}
	public String getCLAIMPAIDDT() {
		return CLAIMPAIDDT;
	}
	public void setCLAIMPAIDDT(String cLAIMPAIDDT) {
		CLAIMPAIDDT = cLAIMPAIDDT;
	}
	public String getCEOPAYMENTSENTDT() {
		return CEOPAYMENTSENTDT;
	}
	public void setCEOPAYMENTSENTDT(String cEOPAYMENTSENTDT) {
		CEOPAYMENTSENTDT = cEOPAYMENTSENTDT;
	}
	private String CEOPAYMENTSENTDT;
	
	public String getEMPCOMMTY() {
		return EMPCOMMTY;
	}
	public void setEMPCOMMTY(String eMPCOMMTY) {
		EMPCOMMTY = eMPCOMMTY;
	}
	public String getEMPPOSTDIST() {
		return EMPPOSTDIST;
	}
	public void setEMPPOSTDIST(String eMPPOSTDIST) {
		EMPPOSTDIST = eMPPOSTDIST;
	}
	public String getPATTYPE() {
		return PATTYPE;
	}
	public void setPATTYPE(String pATTYPE) {
		PATTYPE = pATTYPE;
	}
	public Number getEHS_COUNT() {
		return EHS_COUNT;
	}
	public void setEHS_COUNT(Number eHS_COUNT) {
		EHS_COUNT = eHS_COUNT;
	}
	public Number getEHS_AMOUNT() {
		return EHS_AMOUNT;
	}
	public void setEHS_AMOUNT(Number eHS_AMOUNT) {
		EHS_AMOUNT = eHS_AMOUNT;
	}
	public Number getJHS_COUNT() {
		return JHS_COUNT;
	}
	public void setJHS_COUNT(Number jHS_COUNT) {
		JHS_COUNT = jHS_COUNT;
	}
	public Number getJHS_AMOUNT() {
		return JHS_AMOUNT;
	}
	public void setJHS_AMOUNT(Number jHS_AMOUNT) {
		JHS_AMOUNT = jHS_AMOUNT;
	}
	public Number getEHS_COUNT1() {
		return EHS_COUNT1;
	}
	public void setEHS_COUNT1(Number eHS_COUNT1) {
		EHS_COUNT1 = eHS_COUNT1;
	}
	public Number getEHS_AMOUNT1() {
		return EHS_AMOUNT1;
	}
	public void setEHS_AMOUNT1(Number eHS_AMOUNT1) {
		EHS_AMOUNT1 = eHS_AMOUNT1;
	}
	public Number getJHS_COUNT1() {
		return JHS_COUNT1;
	}
	public void setJHS_COUNT1(Number jHS_COUNT1) {
		JHS_COUNT1 = jHS_COUNT1;
	}
	public Number getJHS_AMOUNT1() {
		return JHS_AMOUNT1;
	}
	public void setJHS_AMOUNT1(Number jHS_AMOUNT1) {
		JHS_AMOUNT1 = jHS_AMOUNT1;
	}
	public Number getEHS_COUNT2() {
		return EHS_COUNT2;
	}
	public void setEHS_COUNT2(Number eHS_COUNT2) {
		EHS_COUNT2 = eHS_COUNT2;
	}
	public Number getEHS_AMOUNT2() {
		return EHS_AMOUNT2;
	}
	public void setEHS_AMOUNT2(Number eHS_AMOUNT2) {
		EHS_AMOUNT2 = eHS_AMOUNT2;
	}
	public Number getJHS_COUNT2() {
		return JHS_COUNT2;
	}
	public void setJHS_COUNT2(Number jHS_COUNT2) {
		JHS_COUNT2 = jHS_COUNT2;
	}
	public Number getJHS_AMOUNT2() {
		return JHS_AMOUNT2;
	}
	public void setJHS_AMOUNT2(Number jHS_AMOUNT2) {
		JHS_AMOUNT2 = jHS_AMOUNT2;
	}
	private Number JHS_AMOUNT;
	private Number EHS_COUNT1;
	private Number EHS_AMOUNT1;
	private Number JHS_COUNT1;
	private Number JHS_AMOUNT1;
	private Number EHS_COUNT2;
	private Number EHS_AMOUNT2;
	private Number JHS_COUNT2;
	private Number JHS_AMOUNT2;
	private Number EHS_COUNT3;
	private Number EHS_COUNT5;
	private Number EHS_AMOUNT5;
	private Number JHS_COUNT5;
	private Number JHS_AMOUNT5;
	private Number EHS_COUNT6;
	private Number EHS_AMOUNT6;
	private Number JHS_COUNT6;
	private Number JHS_AMOUNT6;
	private Number EHS_COUNT7;
	private Number EHS_AMOUNT7;
	private Number JHS_COUNT7;
	private Number JHS_AMOUNT7;
	public Number getEHS_COUNT5() {
		return EHS_COUNT5;
	}
	public void setEHS_COUNT5(Number eHS_COUNT5) {
		EHS_COUNT5 = eHS_COUNT5;
	}
	public Number getEHS_AMOUNT5() {
		return EHS_AMOUNT5;
	}
	public void setEHS_AMOUNT5(Number eHS_AMOUNT5) {
		EHS_AMOUNT5 = eHS_AMOUNT5;
	}
	public Number getJHS_COUNT5() {
		return JHS_COUNT5;
	}
	public void setJHS_COUNT5(Number jHS_COUNT5) {
		JHS_COUNT5 = jHS_COUNT5;
	}
	public Number getJHS_AMOUNT5() {
		return JHS_AMOUNT5;
	}
	public void setJHS_AMOUNT5(Number jHS_AMOUNT5) {
		JHS_AMOUNT5 = jHS_AMOUNT5;
	}
	public Number getEHS_COUNT6() {
		return EHS_COUNT6;
	}
	public void setEHS_COUNT6(Number eHS_COUNT6) {
		EHS_COUNT6 = eHS_COUNT6;
	}
	public Number getEHS_AMOUNT6() {
		return EHS_AMOUNT6;
	}
	public void setEHS_AMOUNT6(Number eHS_AMOUNT6) {
		EHS_AMOUNT6 = eHS_AMOUNT6;
	}
	public Number getJHS_COUNT6() {
		return JHS_COUNT6;
	}
	public void setJHS_COUNT6(Number jHS_COUNT6) {
		JHS_COUNT6 = jHS_COUNT6;
	}
	public Number getJHS_AMOUNT6() {
		return JHS_AMOUNT6;
	}
	public void setJHS_AMOUNT6(Number jHS_AMOUNT6) {
		JHS_AMOUNT6 = jHS_AMOUNT6;
	}
	public Number getEHS_COUNT7() {
		return EHS_COUNT7;
	}
	public void setEHS_COUNT7(Number eHS_COUNT7) {
		EHS_COUNT7 = eHS_COUNT7;
	}
	public Number getEHS_AMOUNT7() {
		return EHS_AMOUNT7;
	}
	public void setEHS_AMOUNT7(Number eHS_AMOUNT7) {
		EHS_AMOUNT7 = eHS_AMOUNT7;
	}
	public Number getJHS_COUNT7() {
		return JHS_COUNT7;
	}
	public void setJHS_COUNT7(Number jHS_COUNT7) {
		JHS_COUNT7 = jHS_COUNT7;
	}
	public Number getJHS_AMOUNT7() {
		return JHS_AMOUNT7;
	}
	public void setJHS_AMOUNT7(Number jHS_AMOUNT7) {
		JHS_AMOUNT7 = jHS_AMOUNT7;
	}
	public Number getEHS_COUNT3() {
		return EHS_COUNT3;
	}
	public void setEHS_COUNT3(Number eHS_COUNT3) {
		EHS_COUNT3 = eHS_COUNT3;
	}
	public Number getEHS_AMOUNT3() {
		return EHS_AMOUNT3;
	}
	public void setEHS_AMOUNT3(Number eHS_AMOUNT3) {
		EHS_AMOUNT3 = eHS_AMOUNT3;
	}
	public Number getJHS_COUNT3() {
		return JHS_COUNT3;
	}
	public void setJHS_COUNT3(Number jHS_COUNT3) {
		JHS_COUNT3 = jHS_COUNT3;
	}
	public Number getJHS_AMOUNT3() {
		return JHS_AMOUNT3;
	}
	public void setJHS_AMOUNT3(Number jHS_AMOUNT3) {
		JHS_AMOUNT3 = jHS_AMOUNT3;
	}
	public Number getEHS_COUNT4() {
		return EHS_COUNT4;
	}
	public void setEHS_COUNT4(Number eHS_COUNT4) {
		EHS_COUNT4 = eHS_COUNT4;
	}
	public Number getEHS_AMOUNT4() {
		return EHS_AMOUNT4;
	}
	public void setEHS_AMOUNT4(Number eHS_AMOUNT4) {
		EHS_AMOUNT4 = eHS_AMOUNT4;
	}
	public Number getJHS_COUNT4() {
		return JHS_COUNT4;
	}
	public void setJHS_COUNT4(Number jHS_COUNT4) {
		JHS_COUNT4 = jHS_COUNT4;
	}
	public Number getJHS_AMOUNT4() {
		return JHS_AMOUNT4;
	}
	public void setJHS_AMOUNT4(Number jHS_AMOUNT4) {
		JHS_AMOUNT4 = jHS_AMOUNT4;
	}
	private Number EHS_AMOUNT3;
	private Number JHS_COUNT3;
	private Number JHS_AMOUNT3;
	private Number EHS_COUNT4;
	private Number EHS_AMOUNT4;
	private Number JHS_COUNT4;
	private Number JHS_AMOUNT4;
	public Number getBANK5() {
		return BANK5;
	}
	public void setBANK5(Number bANK5) {
		BANK5 = bANK5;
	}
	public Number getBANK6() {
		return BANK6;
	}
	public void setBANK6(Number bANK6) {
		BANK6 = bANK6;
	}
	public Number getBANK7() {
		return BANK7;
	}
	public void setBANK7(Number bANK7) {
		BANK7 = bANK7;
	}
	public Number getBANK8() {
		return BANK8;
	}
	public void setBANK8(Number bANK8) {
		BANK8 = bANK8;
	}
	private Number ACK_RCVD_BANK_COUNT2;
	private Number ACK_RCVD_BANK_AMOUNT2;
	private Number CLAIM_REJ_COUNT2;
	private Number CLAIM_REJ_AMOUNT2;
	private Number CLAIM_SENT_COUNT2;
	private Number CLAIM_SENT_AMOUNT2;
	private Number ACK_RCVD_BANK_COUNT3;
	private Number ACK_RCVD_BANK_AMOUNT3;
	private Number CLAIM_REJ_COUNT3;
	private Number CLAIM_REJ_AMOUNT3;
	private Number CLAIM_SENT_COUNT3;
	private String PatNabhType;
	public String getPatNabhType() {
		return PatNabhType;
	}
	public void setPatNabhType(String patNabhType) {
		PatNabhType = patNabhType;
	}
	public Number getACK_RCVD_BANK_COUNT2() {
		return ACK_RCVD_BANK_COUNT2;
	}
	public void setACK_RCVD_BANK_COUNT2(Number aCK_RCVD_BANK_COUNT2) {
		ACK_RCVD_BANK_COUNT2 = aCK_RCVD_BANK_COUNT2;
	}
	public Number getACK_RCVD_BANK_AMOUNT2() {
		return ACK_RCVD_BANK_AMOUNT2;
	}
	public void setACK_RCVD_BANK_AMOUNT2(Number aCK_RCVD_BANK_AMOUNT2) {
		ACK_RCVD_BANK_AMOUNT2 = aCK_RCVD_BANK_AMOUNT2;
	}
	public Number getCLAIM_REJ_COUNT2() {
		return CLAIM_REJ_COUNT2;
	}
	public void setCLAIM_REJ_COUNT2(Number cLAIM_REJ_COUNT2) {
		CLAIM_REJ_COUNT2 = cLAIM_REJ_COUNT2;
	}
	public Number getCLAIM_REJ_AMOUNT2() {
		return CLAIM_REJ_AMOUNT2;
	}
	public void setCLAIM_REJ_AMOUNT2(Number cLAIM_REJ_AMOUNT2) {
		CLAIM_REJ_AMOUNT2 = cLAIM_REJ_AMOUNT2;
	}
	public Number getCLAIM_SENT_COUNT2() {
		return CLAIM_SENT_COUNT2;
	}
	public void setCLAIM_SENT_COUNT2(Number cLAIM_SENT_COUNT2) {
		CLAIM_SENT_COUNT2 = cLAIM_SENT_COUNT2;
	}
	public Number getCLAIM_SENT_AMOUNT2() {
		return CLAIM_SENT_AMOUNT2;
	}
	public void setCLAIM_SENT_AMOUNT2(Number cLAIM_SENT_AMOUNT2) {
		CLAIM_SENT_AMOUNT2 = cLAIM_SENT_AMOUNT2;
	}
	public Number getACK_RCVD_BANK_COUNT3() {
		return ACK_RCVD_BANK_COUNT3;
	}
	public void setACK_RCVD_BANK_COUNT3(Number aCK_RCVD_BANK_COUNT3) {
		ACK_RCVD_BANK_COUNT3 = aCK_RCVD_BANK_COUNT3;
	}
	public Number getACK_RCVD_BANK_AMOUNT3() {
		return ACK_RCVD_BANK_AMOUNT3;
	}
	public void setACK_RCVD_BANK_AMOUNT3(Number aCK_RCVD_BANK_AMOUNT3) {
		ACK_RCVD_BANK_AMOUNT3 = aCK_RCVD_BANK_AMOUNT3;
	}
	public Number getCLAIM_REJ_COUNT3() {
		return CLAIM_REJ_COUNT3;
	}
	public void setCLAIM_REJ_COUNT3(Number cLAIM_REJ_COUNT3) {
		CLAIM_REJ_COUNT3 = cLAIM_REJ_COUNT3;
	}
	public Number getCLAIM_REJ_AMOUNT3() {
		return CLAIM_REJ_AMOUNT3;
	}
	public void setCLAIM_REJ_AMOUNT3(Number cLAIM_REJ_AMOUNT3) {
		CLAIM_REJ_AMOUNT3 = cLAIM_REJ_AMOUNT3;
	}
	public Number getCLAIM_SENT_COUNT3() {
		return CLAIM_SENT_COUNT3;
	}
	public void setCLAIM_SENT_COUNT3(Number cLAIM_SENT_COUNT3) {
		CLAIM_SENT_COUNT3 = cLAIM_SENT_COUNT3;
	}
	public Number getCLAIM_SENT_AMOUNT3() {
		return CLAIM_SENT_AMOUNT3;
	}
	public void setCLAIM_SENT_AMOUNT3(Number cLAIM_SENT_AMOUNT3) {
		CLAIM_SENT_AMOUNT3 = cLAIM_SENT_AMOUNT3;
	}
	private Number CLAIM_SENT_AMOUNT3;

	private String caseType;
	private String schemeType;

	private String sdFromDate;
	private String sdToDate;
	private String sUpdFromDate;
	private String sUpdToDate;
	

	//added bhaskar
	private String ESTIMATED_AMOUNT;
	private String historyIllness;
	private String DISTRICT;
	private Number NON_NABH_COUNT;
	private Number NON_NABH_AMOUNT;
	private Number NABH_COUNT;
	private Number EXCLUDE_NABH_AMT;
	private Number NABH_AMOUNT;
	private Number TOT_CLAIM_PAID_COUNT;
	private Number TOT_CLAIM_PAID_AMT;
	

	private String dateChk1;
	
	private String ddoStoDistChk;
	 private String communityChk;
	 private String patTypeChk;
	 private String benTypeChk;
	 
	 public String getDdoStoDistChk() {
		return ddoStoDistChk;
	}
	public void setDdoStoDistChk(String ddoStoDistChk) {
		this.ddoStoDistChk = ddoStoDistChk;
	}
	public String getCommunityChk() {
		return communityChk;
	}
	public void setCommunityChk(String communityChk) {
		this.communityChk = communityChk;
	}
	public String getPatTypeChk() {
		return patTypeChk;
	}
	public void setPatTypeChk(String patTypeChk) {
		this.patTypeChk = patTypeChk;
	}
	
	public String getSdFromDate() {
		return sdFromDate;
	}
	public void setSdFromDate(String sdFromDate) {
		this.sdFromDate = sdFromDate;
	}
	public String getSdToDate() {
		return sdToDate;
	}
	public void setSdToDate(String sdToDate) {
		this.sdToDate = sdToDate;
	}
	public String getsUpdFromDate() {
		return sUpdFromDate;
	}
	public void setsUpdFromDate(String sUpdFromDate) {
		this.sUpdFromDate = sUpdFromDate;
	}
	public String getsUpdToDate() {
		return sUpdToDate;
	}
	public void setsUpdToDate(String sUpdToDate) {
		this.sUpdToDate = sUpdToDate;
	}
	public Number getBANK1() {
		return BANK1;
	}
	public void setBANK1(Number bANK1) {
		BANK1 = bANK1;
	}
	public Number getBANK2() {
		return BANK2;
	}
	public void setBANK2(Number bANK2) {
		BANK2 = bANK2;
	}
	public Number getBANK3() {
		return BANK3;
	}
	public void setBANK3(Number bANK3) {
		BANK3 = bANK3;
	}
	public Number getBANK4() {
		return BANK4;
	}
	public void setBANK4(Number bANK4) {
		BANK4 = bANK4;
	}
	public Number getACK_RCVD_BANK_COUNT() {
		return ACK_RCVD_BANK_COUNT;
	}
	public void setACK_RCVD_BANK_COUNT(Number aCK_RCVD_BANK_COUNT) {
		ACK_RCVD_BANK_COUNT = aCK_RCVD_BANK_COUNT;
	}
	public Number getACK_RCVD_BANK_AMOUNT() {
		return ACK_RCVD_BANK_AMOUNT;
	}
	public void setACK_RCVD_BANK_AMOUNT(Number aCK_RCVD_BANK_AMOUNT) {
		ACK_RCVD_BANK_AMOUNT = aCK_RCVD_BANK_AMOUNT;
	}
	public Number getCLAIM_REJ_COUNT() {
		return CLAIM_REJ_COUNT;
	}
	public void setCLAIM_REJ_COUNT(Number cLAIM_REJ_COUNT) {
		CLAIM_REJ_COUNT = cLAIM_REJ_COUNT;
	}
	public Number getCLAIM_REJ_AMOUNT() {
		return CLAIM_REJ_AMOUNT;
	}
	public void setCLAIM_REJ_AMOUNT(Number cLAIM_REJ_AMOUNT) {
		CLAIM_REJ_AMOUNT = cLAIM_REJ_AMOUNT;
	}
	public Number getCLAIM_SENT_COUNT() {
		return CLAIM_SENT_COUNT;
	}
	public void setCLAIM_SENT_COUNT(Number cLAIM_SENT_COUNT) {
		CLAIM_SENT_COUNT = cLAIM_SENT_COUNT;
	}
	public Number getCLAIM_SENT_AMOUNT() {
		return CLAIM_SENT_AMOUNT;
	}
	public void setCLAIM_SENT_AMOUNT(Number cLAIM_SENT_AMOUNT) {
		CLAIM_SENT_AMOUNT = cLAIM_SENT_AMOUNT;
	}
	public Number getACK_RCVD_BANK_COUNT1() {
		return ACK_RCVD_BANK_COUNT1;
	}
	public void setACK_RCVD_BANK_COUNT1(Number aCK_RCVD_BANK_COUNT1) {
		ACK_RCVD_BANK_COUNT1 = aCK_RCVD_BANK_COUNT1;
	}
	public Number getACK_RCVD_BANK_AMOUNT1() {
		return ACK_RCVD_BANK_AMOUNT1;
	}
	public void setACK_RCVD_BANK_AMOUNT1(Number aCK_RCVD_BANK_AMOUNT1) {
		ACK_RCVD_BANK_AMOUNT1 = aCK_RCVD_BANK_AMOUNT1;
	}
	public Number getCLAIM_REJ_COUNT1() {
		return CLAIM_REJ_COUNT1;
	}
	public void setCLAIM_REJ_COUNT1(Number cLAIM_REJ_COUNT1) {
		CLAIM_REJ_COUNT1 = cLAIM_REJ_COUNT1;
	}
	public Number getCLAIM_REJ_AMOUNT1() {
		return CLAIM_REJ_AMOUNT1;
	}
	public void setCLAIM_REJ_AMOUNT1(Number cLAIM_REJ_AMOUNT1) {
		CLAIM_REJ_AMOUNT1 = cLAIM_REJ_AMOUNT1;
	}
	public Number getCLAIM_SENT_COUNT1() {
		return CLAIM_SENT_COUNT1;
	}
	public void setCLAIM_SENT_COUNT1(Number cLAIM_SENT_COUNT1) {
		CLAIM_SENT_COUNT1 = cLAIM_SENT_COUNT1;
	}
	public Number getCLAIM_SENT_AMOUNT1() {
		return CLAIM_SENT_AMOUNT1;
	}
	public void setCLAIM_SENT_AMOUNT1(Number cLAIM_SENT_AMOUNT1) {
		CLAIM_SENT_AMOUNT1 = cLAIM_SENT_AMOUNT1;
	}
	private Number CLAIM_SENT_AMOUNT1;
	
	
	public Number getNABHCOUNT_NO() {
		return NABHCOUNT_NO;
	}
	public void setNABHCOUNT_NO(Number nABHCOUNT_NO) {
		NABHCOUNT_NO = nABHCOUNT_NO;
	}
	public Number getNABHAMOUNT() {
		return NABHAMOUNT;
	}
	public void setNABHAMOUNT(Number nABHAMOUNT) {
		NABHAMOUNT = nABHAMOUNT;
	}
	public Number getNABHCOUNT_NO1() {
		return NABHCOUNT_NO1;
	}
	public void setNABHCOUNT_NO1(Number nABHCOUNT_NO1) {
		NABHCOUNT_NO1 = nABHCOUNT_NO1;
	}
	public Number getNABHAMOUNT1() {
		return NABHAMOUNT1;
	}
	public void setNABHAMOUNT1(Number nABHAMOUNT1) {
		NABHAMOUNT1 = nABHAMOUNT1;
	}
	public Number getNABHCOUNT_NO2() {
		return NABHCOUNT_NO2;
	}
	public void setNABHCOUNT_NO2(Number nABHCOUNT_NO2) {
		NABHCOUNT_NO2 = nABHCOUNT_NO2;
	}
	public Number getNABHAMOUNT2() {
		return NABHAMOUNT2;
	}
	public void setNABHAMOUNT2(Number nABHAMOUNT2) {
		NABHAMOUNT2 = nABHAMOUNT2;
	}
	public Number getNABHCOUNT_NO3() {
		return NABHCOUNT_NO3;
	}
	public void setNABHCOUNT_NO3(Number nABHCOUNT_NO3) {
		NABHCOUNT_NO3 = nABHCOUNT_NO3;
	}
	public Number getNABHAMOUNT3() {
		return NABHAMOUNT3;
	}
	public void setNABHAMOUNT3(Number nABHAMOUNT3) {
		NABHAMOUNT3 = nABHAMOUNT3;
	}
	public Number getNABHCOUNT_NO4() {
		return NABHCOUNT_NO4;
	}
	public void setNABHCOUNT_NO4(Number nABHCOUNT_NO4) {
		NABHCOUNT_NO4 = nABHCOUNT_NO4;
	}
	public Number getNABHAMOUNT4() {
		return NABHAMOUNT4;
	}
	public void setNABHAMOUNT4(Number nABHAMOUNT4) {
		NABHAMOUNT4 = nABHAMOUNT4;
	}
	public Number getNABHCOUNT_NO5() {
		return NABHCOUNT_NO5;
	}
	public void setNABHCOUNT_NO5(Number nABHCOUNT_NO5) {
		NABHCOUNT_NO5 = nABHCOUNT_NO5;
	}
	public Number getNABHAMOUNT5() {
		return NABHAMOUNT5;
	}
	public void setNABHAMOUNT5(Number nABHAMOUNT5) {
		NABHAMOUNT5 = nABHAMOUNT5;
	}
	private Number NABHAMOUNT5;
	
	
	public Number getNABHCEX_COUNT() {
		return NABHCEX_COUNT;
	}
	public void setNABHCEX_COUNT(Number nABHCEX_COUNT) {
		NABHCEX_COUNT = nABHCEX_COUNT;
	}
	public Number getNABHCEX_COUNT1() {
		return NABHCEX_COUNT1;
	}
	public void setNABHCEX_COUNT1(Number nABHCEX_COUNT1) {
		NABHCEX_COUNT1 = nABHCEX_COUNT1;
	}
	public Number getNABHCPD_COUNT() {
		return NABHCPD_COUNT;
	}
	public void setNABHCPD_COUNT(Number nABHCPD_COUNT) {
		NABHCPD_COUNT = nABHCPD_COUNT;
	}
	public Number getNABHCPD_COUNT1() {
		return NABHCPD_COUNT1;
	}
	public void setNABHCPD_COUNT1(Number nABHCPD_COUNT1) {
		NABHCPD_COUNT1 = nABHCPD_COUNT1;
	}
	public Number getNABHCTD_COUNT() {
		return NABHCTD_COUNT;
	}
	public void setNABHCTD_COUNT(Number nABHCTD_COUNT) {
		NABHCTD_COUNT = nABHCTD_COUNT;
	}
	public Number getNABHCTD_COUNT1() {
		return NABHCTD_COUNT1;
	}
	public void setNABHCTD_COUNT1(Number nABHCTD_COUNT1) {
		NABHCTD_COUNT1 = nABHCTD_COUNT1;
	}
	public Number getNABHCH_COUNT() {
		return NABHCH_COUNT;
	}
	public void setNABHCH_COUNT(Number nABHCH_COUNT) {
		NABHCH_COUNT = nABHCH_COUNT;
	}
	public Number getNABHCH_COUNT1() {
		return NABHCH_COUNT1;
	}
	public void setNABHCH_COUNT1(Number nABHCH_COUNT1) {
		NABHCH_COUNT1 = nABHCH_COUNT1;
	}
	public Number getNABHACO_COUNT() {
		return NABHACO_COUNT;
	}
	public void setNABHACO_COUNT(Number nABHACO_COUNT) {
		NABHACO_COUNT = nABHACO_COUNT;
	}
	public Number getNABHACO_COUNT1() {
		return NABHACO_COUNT1;
	}
	public void setNABHACO_COUNT1(Number nABHACO_COUNT1) {
		NABHACO_COUNT1 = nABHACO_COUNT1;
	}
	public Number getNABHMEDCO_COUNT() {
		return NABHMEDCO_COUNT;
	}
	public void setNABHMEDCO_COUNT(Number nABHMEDCO_COUNT) {
		NABHMEDCO_COUNT = nABHMEDCO_COUNT;
	}
	public Number getNABHMEDCO_COUNT1() {
		return NABHMEDCO_COUNT1;
	}
	public void setNABHMEDCO_COUNT1(Number nABHMEDCO_COUNT1) {
		NABHMEDCO_COUNT1 = nABHMEDCO_COUNT1;
	}
	public Number getNABHCEO_COUNT() {
		return NABHCEO_COUNT;
	}
	public void setNABHCEO_COUNT(Number nABHCEO_COUNT) {
		NABHCEO_COUNT = nABHCEO_COUNT;
	}
	public Number getNABHCEO_COUNT1() {
		return NABHCEO_COUNT1;
	}
	public void setNABHCEO_COUNT1(Number nABHCEO_COUNT1) {
		NABHCEO_COUNT1 = nABHCEO_COUNT1;
	}
	public Number getNABHTOT() {
		return NABHTOT;
	}
	public void setNABHTOT(Number nABHTOT) {
		NABHTOT = nABHTOT;
	}
	public Number getNABHTOT1() {
		return NABHTOT1;
	}
	public void setNABHTOT1(Number nABHTOT1) {
		NABHTOT1 = nABHTOT1;
	}
	public Number getNABHTOT2() {
		return NABHTOT2;
	}
	public void setNABHTOT2(Number nABHTOT2) {
		NABHTOT2 = nABHTOT2;
	}
	public Number getNABHTOT3() {
		return NABHTOT3;
	}
	public void setNABHTOT3(Number nABHTOT3) {
		NABHTOT3 = nABHTOT3;
	}
	public Number getNABHCEX_AMOUNT() {
		return NABHCEX_AMOUNT;
	}
	public void setNABHCEX_AMOUNT(Number nABHCEX_AMOUNT) {
		NABHCEX_AMOUNT = nABHCEX_AMOUNT;
	}
	public Number getNABHCEX_AMOUNT1() {
		return NABHCEX_AMOUNT1;
	}
	public void setNABHCEX_AMOUNT1(Number nABHCEX_AMOUNT1) {
		NABHCEX_AMOUNT1 = nABHCEX_AMOUNT1;
	}
	public Number getNABHCPD_AMOUNT() {
		return NABHCPD_AMOUNT;
	}
	public void setNABHCPD_AMOUNT(Number nABHCPD_AMOUNT) {
		NABHCPD_AMOUNT = nABHCPD_AMOUNT;
	}
	public Number getNABHCPD_AMOUNT1() {
		return NABHCPD_AMOUNT1;
	}
	public void setNABHCPD_AMOUNT1(Number nABHCPD_AMOUNT1) {
		NABHCPD_AMOUNT1 = nABHCPD_AMOUNT1;
	}
	public Number getNABHCTD_AMOUNT() {
		return NABHCTD_AMOUNT;
	}
	public void setNABHCTD_AMOUNT(Number nABHCTD_AMOUNT) {
		NABHCTD_AMOUNT = nABHCTD_AMOUNT;
	}
	public Number getNABHCTD_AMOUNT1() {
		return NABHCTD_AMOUNT1;
	}
	public void setNABHCTD_AMOUNT1(Number nABHCTD_AMOUNT1) {
		NABHCTD_AMOUNT1 = nABHCTD_AMOUNT1;
	}
	public Number getNABHCH_AMOUNT() {
		return NABHCH_AMOUNT;
	}
	public void setNABHCH_AMOUNT(Number nABHCH_AMOUNT) {
		NABHCH_AMOUNT = nABHCH_AMOUNT;
	}
	public Number getNABHCH_AMOUNT1() {
		return NABHCH_AMOUNT1;
	}
	public void setNABHCH_AMOUNT1(Number nABHCH_AMOUNT1) {
		NABHCH_AMOUNT1 = nABHCH_AMOUNT1;
	}
	public Number getNABHACO_AMOUNT() {
		return NABHACO_AMOUNT;
	}
	public void setNABHACO_AMOUNT(Number nABHACO_AMOUNT) {
		NABHACO_AMOUNT = nABHACO_AMOUNT;
	}
	public Number getNABHACO_AMOUNT1() {
		return NABHACO_AMOUNT1;
	}
	public void setNABHACO_AMOUNT1(Number nABHACO_AMOUNT1) {
		NABHACO_AMOUNT1 = nABHACO_AMOUNT1;
	}
	public Number getNABHMEDCO_AMOUNT() {
		return NABHMEDCO_AMOUNT;
	}
	public void setNABHMEDCO_AMOUNT(Number nABHMEDCO_AMOUNT) {
		NABHMEDCO_AMOUNT = nABHMEDCO_AMOUNT;
	}
	public Number getNABHMEDCO_AMOUNT1() {
		return NABHMEDCO_AMOUNT1;
	}
	public void setNABHMEDCO_AMOUNT1(Number nABHMEDCO_AMOUNT1) {
		NABHMEDCO_AMOUNT1 = nABHMEDCO_AMOUNT1;
	}
	public Number getNABHCEO_AMOUNT() {
		return NABHCEO_AMOUNT;
	}
	public void setNABHCEO_AMOUNT(Number nABHCEO_AMOUNT) {
		NABHCEO_AMOUNT = nABHCEO_AMOUNT;
	}
	public Number getNABHCEO_AMOUNT1() {
		return NABHCEO_AMOUNT1;
	}
	public void setNABHCEO_AMOUNT1(Number nABHCEO_AMOUNT1) {
		NABHCEO_AMOUNT1 = nABHCEO_AMOUNT1;
	}
	private Number NABHMEDCO_AMOUNT;
	private Number NABHMEDCO_AMOUNT1;
	private Number NABHCEO_AMOUNT;
	private Number NABHCEO_AMOUNT1;
	
	
	
	
	public String getTRUST() {
		return TRUST;
	}
	public void setTRUST(String tRUST) {
		TRUST = tRUST;
	}
	public String getTRUST1() {
		return TRUST1;
	}
	public void setTRUST1(String tRUST1) {
		TRUST1 = tRUST1;
	}
	public String getHOSPITAL() {
		return HOSPITAL;
	}
	public void setHOSPITAL(String hOSPITAL) {
		HOSPITAL = hOSPITAL;
	}
	public String getTRUST2() {
		return TRUST2;
	}
	public void setTRUST2(String tRUST2) {
		TRUST2 = tRUST2;
	}
	public String getTRUST3() {
		return TRUST3;
	}
	public void setTRUST3(String tRUST3) {
		TRUST3 = tRUST3;
	}
	public String getHOSPITAL2() {
		return HOSPITAL2;
	}
	public void setHOSPITAL2(String hOSPITAL2) {
		HOSPITAL2 = hOSPITAL2;
	}
	public String getHOSPITAL3() {
		return HOSPITAL3;
	}
	public void setHOSPITAL3(String hOSPITAL3) {
		HOSPITAL3 = hOSPITAL3;
	}
	public String getHOSPITAL1() {
		return HOSPITAL1;
	}
	public void setHOSPITAL1(String hOSPITAL1) {
		HOSPITAL1 = hOSPITAL1;
	}
	private Number TOT1;
	private Number TOT2;
	private Number TOT3;
    private String HOSPITAL3;
	private String HOSPITAL1;
	
	private String roles;
	private String ehsNoCases;
	private String ehsAmount;
	private String jhsNoCases;
	private String jhsAmount;
	private String SNO;
	
	
	

	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	public String getEhsNoCases() {
		return ehsNoCases;
	}
	public void setEhsNoCases(String ehsNoCases) {
		this.ehsNoCases = ehsNoCases;
	}
	public String getEhsAmount() {
		return ehsAmount;
	}
	public void setEhsAmount(String ehsAmount) {
		this.ehsAmount = ehsAmount;
	}
	public String getJhsNoCases() {
		return jhsNoCases;
	}
	public void setJhsNoCases(String jhsNoCases) {
		this.jhsNoCases = jhsNoCases;
	}
	public String getJhsAmount() {
		return jhsAmount;
	}
	public void setJhsAmount(String jhsAmount) {
		this.jhsAmount = jhsAmount;
	}
	public Number getTOT1() {
		return TOT1;
	}
	public void setTOT1(Number tOT1) {
		TOT1 = tOT1;
	}
	public Number getTOT2() {
		return TOT2;
	}
	public void setTOT2(Number tOT2) {
		TOT2 = tOT2;
	}
	public Number getTOT3() {
		return TOT3;
	}
	public void setTOT3(Number tOT3) {
		TOT3 = tOT3;
	}
	public Number getTOT() {
		return TOT;
	}
	public void setTOT(Number tOT) {
		TOT = tOT;
	}
	
	
	
	
	public Number getCOUNT_NO3() {
		return COUNT_NO3;
	}
	public void setCOUNT_NO3(Number cOUNT_NO3) {
		COUNT_NO3 = cOUNT_NO3;
	}
	public Number getAMOUNT3() {
		return AMOUNT3;
	}
	public void setAMOUNT3(Number aMOUNT3) {
		AMOUNT3 = aMOUNT3;
	}
	public Number getCOUNT_NO4() {
		return COUNT_NO4;
	}
	public void setCOUNT_NO4(Number cOUNT_NO4) {
		COUNT_NO4 = cOUNT_NO4;
	}
	public Number getAMOUNT4() {
		return AMOUNT4;
	}
	public void setAMOUNT4(Number aMOUNT4) {
		AMOUNT4 = aMOUNT4;
	}
	public Number getCOUNT_NO5() {
		return COUNT_NO5;
	}
	public void setCOUNT_NO5(Number cOUNT_NO5) {
		COUNT_NO5 = cOUNT_NO5;
	}
	public Number getAMOUNT5() {
		return AMOUNT5;
	}
	public void setAMOUNT5(Number aMOUNT5) {
		AMOUNT5 = aMOUNT5;
	}
	private Number AMOUNT3;
	private Number COUNT_NO4;
	private Number AMOUNT4;
	private Number COUNT_NO5;
	private Number AMOUNT5;
	
	public Number getCEX_COUNT1() {
		return CEX_COUNT1;
	}
	public void setCEX_COUNT1(Number cEX_COUNT1) {
		CEX_COUNT1 = cEX_COUNT1;
	}
	public Number getCEX_AMOUNT1() {
		return CEX_AMOUNT1;
	}
	public void setCEX_AMOUNT1(Number cEX_AMOUNT1) {
		CEX_AMOUNT1 = cEX_AMOUNT1;
	}
	public Number getCPD_COUNT1() {
		return CPD_COUNT1;
	}
	public void setCPD_COUNT1(Number cPD_COUNT1) {
		CPD_COUNT1 = cPD_COUNT1;
	}
	public Number getCPD_AMOUNT1() {
		return CPD_AMOUNT1;
	}
	public void setCPD_AMOUNT1(Number cPD_AMOUNT1) {
		CPD_AMOUNT1 = cPD_AMOUNT1;
	}
	public Number getCTD_COUNT1() {
		return CTD_COUNT1;
	}
	public void setCTD_COUNT1(Number cTD_COUNT1) {
		CTD_COUNT1 = cTD_COUNT1;
	}
	public Number getCTD_AMOUNT1() {
		return CTD_AMOUNT1;
	}
	public void setCTD_AMOUNT1(Number cTD_AMOUNT1) {
		CTD_AMOUNT1 = cTD_AMOUNT1;
	}
	public Number getCH_COUNT1() {
		return CH_COUNT1;
	}
	public void setCH_COUNT1(Number cH_COUNT1) {
		CH_COUNT1 = cH_COUNT1;
	}
	public Number getCH_AMOUNT1() {
		return CH_AMOUNT1;
	}
	public void setCH_AMOUNT1(Number cH_AMOUNT1) {
		CH_AMOUNT1 = cH_AMOUNT1;
	}
	public Number getACO_COUNT1() {
		return ACO_COUNT1;
	}
	public void setACO_COUNT1(Number aCO_COUNT1) {
		ACO_COUNT1 = aCO_COUNT1;
	}
	public Number getACO_AMOUNT1() {
		return ACO_AMOUNT1;
	}
	public void setACO_AMOUNT1(Number aCO_AMOUNT1) {
		ACO_AMOUNT1 = aCO_AMOUNT1;
	}
	public Number getMEDCO_COUNT1() {
		return MEDCO_COUNT1;
	}
	public void setMEDCO_COUNT1(Number mEDCO_COUNT1) {
		MEDCO_COUNT1 = mEDCO_COUNT1;
	}
	public Number getMEDCO_AMOUNT1() {
		return MEDCO_AMOUNT1;
	}
	public void setMEDCO_AMOUNT1(Number mEDCO_AMOUNT1) {
		MEDCO_AMOUNT1 = mEDCO_AMOUNT1;
	}
	public Number getCEO_COUNT1() {
		return CEO_COUNT1;
	}
	public void setCEO_COUNT1(Number cEO_COUNT1) {
		CEO_COUNT1 = cEO_COUNT1;
	}
	public Number getCEO_AMOUNT1() {
		return CEO_AMOUNT1;
	}
	public void setCEO_AMOUNT1(Number cEO_AMOUNT1) {
		CEO_AMOUNT1 = cEO_AMOUNT1;
	}
	private Number CPD_COUNT1;
	private Number CPD_AMOUNT1;
	private Number CTD_COUNT1;
	private Number CTD_AMOUNT1;
	private Number CH_COUNT1;
	private Number CH_AMOUNT1;
	private Number ACO_COUNT1;
	private Number ACO_AMOUNT1;
	private Number MEDCO_COUNT1;
	private Number MEDCO_AMOUNT1;
	private Number CEO_COUNT1;
	private Number CEO_AMOUNT1;
	
	
	
	
	public Number getCEX_COUNT() {
		return CEX_COUNT;
	}
	public void setCEX_COUNT(Number cEX_COUNT) {
		CEX_COUNT = cEX_COUNT;
	}
	public Number getCEX_AMOUNT() {
		return CEX_AMOUNT;
	}
	public void setCEX_AMOUNT(Number cEX_AMOUNT) {
		CEX_AMOUNT = cEX_AMOUNT;
	}
	public Number getCPD_COUNT() {
		return CPD_COUNT;
	}
	public void setCPD_COUNT(Number cPD_COUNT) {
		CPD_COUNT = cPD_COUNT;
	}
	
	public Number getCPD_AMOUNT() {
		return CPD_AMOUNT;
	}
	public void setCPD_AMOUNT(Number cPD_AMOUNT) {
		CPD_AMOUNT = cPD_AMOUNT;
	}
	public Number getCTD_COUNT() {
		return CTD_COUNT;
	}
	public void setCTD_COUNT(Number cTD_COUNT) {
		CTD_COUNT = cTD_COUNT;
	}
	public Number getCTD_AMOUNT() {
		return CTD_AMOUNT;
	}
	public void setCTD_AMOUNT(Number cTD_AMOUNT) {
		CTD_AMOUNT = cTD_AMOUNT;
	}
	public Number getCH_COUNT() {
		return CH_COUNT;
	}
	public void setCH_COUNT(Number cH_COUNT) {
		CH_COUNT = cH_COUNT;
	}
	public Number getCH_AMOUNT() {
		return CH_AMOUNT;
	}
	public void setCH_AMOUNT(Number cH_AMOUNT) {
		CH_AMOUNT = cH_AMOUNT;
	}
	public Number getACO_COUNT() {
		return ACO_COUNT;
	}
	public void setACO_COUNT(Number aCO_COUNT) {
		ACO_COUNT = aCO_COUNT;
	}
	public Number getACO_AMOUNT() {
		return ACO_AMOUNT;
	}
	public void setACO_AMOUNT(Number aCO_AMOUNT) {
		ACO_AMOUNT = aCO_AMOUNT;
	}
	public Number getMEDCO_COUNT() {
		return MEDCO_COUNT;
	}
	public void setMEDCO_COUNT(Number mEDCO_COUNT) {
		MEDCO_COUNT = mEDCO_COUNT;
	}
	public Number getMEDCO_AMOUNT() {
		return MEDCO_AMOUNT;
	}
	public void setMEDCO_AMOUNT(Number mEDCO_AMOUNT) {
		MEDCO_AMOUNT = mEDCO_AMOUNT;
	}
	public Number getCEO_COUNT() {
		return CEO_COUNT;
	}
	public void setCEO_COUNT(Number cEO_COUNT) {
		CEO_COUNT = cEO_COUNT;
	}
	public Number getCEO_AMOUNT() {
		return CEO_AMOUNT;
	}
	public void setCEO_AMOUNT(Number cEO_AMOUNT) {
		CEO_AMOUNT = cEO_AMOUNT;
	}
	public Number getCOUNT_NO2() {
		return COUNT_NO2;
	}
	public void setCOUNT_NO2(Number cOUNT_NO2) {
		COUNT_NO2 = cOUNT_NO2;
	}
	public Number getAMOUNT2() {
		return AMOUNT2;
	}
	public void setAMOUNT2(Number aMOUNT2) {
		AMOUNT2 = aMOUNT2;
	}
	public Number getCOUNT_NO1() {
		return COUNT_NO1;
	}
	public void setCOUNT_NO1(Number cOUNT_NO1) {
		COUNT_NO1 = cOUNT_NO1;
	}
	public Number getAMOUNT1() {
		return AMOUNT1;
	}
	public void setAMOUNT1(Number aMOUNT1) {
		AMOUNT1 = aMOUNT1;
	}
	public Number getCOUNT_NO() {
		return COUNT_NO;
	}
	public void setCOUNT_NO(Number cOUNT_NO) {
		COUNT_NO = cOUNT_NO;
	}
	public Number getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(Number aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public int getStart_index() {
		return start_index;
	}
	public void setStart_index(int start_index) {
		this.start_index = start_index;
	}
	public int getEnd_index() {
		return end_index;
	}
	public void setEnd_index(int end_index) {
		this.end_index = end_index;
	}
	public Number getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}
	public int getMaxresults() {
		return maxresults;
	}
	public void setMaxresults(int maxresults) {
		this.maxresults = maxresults;
	}
	public int getPageId() {
		return pageId;
	}
	public void setPageId(int pageId) {
		this.pageId = pageId;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getTotalRecords() {
		return totalRecords;
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	public int getStrtIndex() {
		return strtIndex;
	}
	public void setStrtIndex(int strtIndex) {
		this.strtIndex = strtIndex;
	}
	public Number getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(Number rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public List<OverallReportVO> getClaimList() {
		return claimList;
	}
	public void setClaimList(List<OverallReportVO> claimList) {
		this.claimList = claimList;
	}
	public String getCsvFlag() {
		return csvFlag;
	}
	public void setCsvFlag(String csvFlag) {
		this.csvFlag = csvFlag;
	}
	public String getCARD_NO() {
		return CARD_NO;
	}
	public void setCARD_NO(String cARD_NO) {
		CARD_NO = cARD_NO;
	}
	public String getPATIENT_ID() {
		return PATIENT_ID;
	}
	public void setPATIENT_ID(String pATIENT_ID) {
		PATIENT_ID = pATIENT_ID;
	}
	public String getCARD_TYPE() {
		return CARD_TYPE;
	}
	public void setCARD_TYPE(String cARD_TYPE) {
		CARD_TYPE = cARD_TYPE;
	}
	public String getCASE_NO() {
		return CASE_NO;
	}
	public void setCASE_NO(String cASE_NO) {
		CASE_NO = cASE_NO;
	}
	public String getCLAIM_NO() {
		return CLAIM_NO;
	}
	public void setCLAIM_NO(String cLAIM_NO) {
		CLAIM_NO = cLAIM_NO;
	}
	public String getPATIENT_NAME() {
		return PATIENT_NAME;
	}
	public void setPATIENT_NAME(String pATIENT_NAME) {
		PATIENT_NAME = pATIENT_NAME;
	}
	public String getAGE() {
		return AGE;
	}
	public void setAGE(String aGE) {
		AGE = aGE;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	public String getCNTCT_NO() {
		return CNTCT_NO;
	}
	public void setCNTCT_NO(String cNTCT_NO) {
		CNTCT_NO = cNTCT_NO;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getVILLAGE() {
		return VILLAGE;
	}
	public void setVILLAGE(String vILLAGE) {
		VILLAGE = vILLAGE;
	}
	public String getMANDAL() {
		return MANDAL;
	}
	public void setMANDAL(String mANDAL) {
		MANDAL = mANDAL;
	}
	public String getPATIENT_DIST() {
		return PATIENT_DIST;
	}
	public void setPATIENT_DIST(String pATIENT_DIST) {
		PATIENT_DIST = pATIENT_DIST;
	}
	public String getDDO_STO_DIST() {
		return DDO_STO_DIST;
	}
	public void setDDO_STO_DIST(String dDO_STO_DIST) {
		DDO_STO_DIST = dDO_STO_DIST;
	}
	public String getHOSP_CODE() {
		return HOSP_CODE;
	}
	public void setHOSP_CODE(String hOSP_CODE) {
		HOSP_CODE = hOSP_CODE;
	}
	public String getHOSP_NAME() {
		return HOSP_NAME;
	}
	public void setHOSP_NAME(String hOSP_NAME) {
		HOSP_NAME = hOSP_NAME;
	}
	public String getHOSP_LOC() {
		return HOSP_LOC;
	}
	public void setHOSP_LOC(String hOSP_LOC) {
		HOSP_LOC = hOSP_LOC;
	}
	public String getHOSP_DIST() {
		return HOSP_DIST;
	}
	public void setHOSP_DIST(String hOSP_DIST) {
		HOSP_DIST = hOSP_DIST;
	}
	public String getHOSP_TYPE() {
		return HOSP_TYPE;
	}
	public void setHOSP_TYPE(String hOSP_TYPE) {
		HOSP_TYPE = hOSP_TYPE;
	}
	public String getCATGRY_NAME() {
		return CATGRY_NAME;
	}
	public void setCATGRY_NAME(String cATGRY_NAME) {
		CATGRY_NAME = cATGRY_NAME;
	}
	public String getCATGRY_CODE() {
		return CATGRY_CODE;
	}
	public void setCATGRY_CODE(String cATGRY_CODE) {
		CATGRY_CODE = cATGRY_CODE;
	}
	public String getPACKG_AMNT() {
		return PACKG_AMNT;
	}
	public void setPACKG_AMNT(String pACKG_AMNT) {
		PACKG_AMNT = pACKG_AMNT;
	}
	public String getPREAUTH_STATUS_DT() {
		return PREAUTH_STATUS_DT;
	}
	public void setPREAUTH_STATUS_DT(String pREAUTH_STATUS_DT) {
		PREAUTH_STATUS_DT = pREAUTH_STATUS_DT;
	}
	public String getPREAUTH_APPRVD_AMNT() {
		return PREAUTH_APPRVD_AMNT;
	}
	public void setPREAUTH_APPRVD_AMNT(String pREAUTH_APPRVD_AMNT) {
		PREAUTH_APPRVD_AMNT = pREAUTH_APPRVD_AMNT;
	}
	public String getCL_SUB_AMT() {
		return CL_SUB_AMT;
	}
	public void setCL_SUB_AMT(String cL_SUB_AMT) {
		CL_SUB_AMT = cL_SUB_AMT;
	}
	public String getCLAIM_SUB_DT() {
		return CLAIM_SUB_DT;
	}
	public void setCLAIM_SUB_DT(String cLAIM_SUB_DT) {
		CLAIM_SUB_DT = cLAIM_SUB_DT;
	}
	public String getCURRENT_STATUS_DATE() {
		return CURRENT_STATUS_DATE;
	}
	public void setCURRENT_STATUS_DATE(String cURRENT_STATUS_DATE) {
		CURRENT_STATUS_DATE = cURRENT_STATUS_DATE;
	}
	public String getPREV_STATUS() {
		return PREV_STATUS;
	}
	public void setPREV_STATUS(String pREV_STATUS) {
		PREV_STATUS = pREV_STATUS;
	}
	public String getPREV_DATE() {
		return PREV_DATE;
	}
	public void setPREV_DATE(String pREV_DATE) {
		PREV_DATE = pREV_DATE;
	}
	public String getACT_NO() {
		return ACT_NO;
	}
	public void setACT_NO(String aCT_NO) {
		ACT_NO = aCT_NO;
	}
	public Number getTOTAL_REG_STATE() {
		return TOTAL_REG_STATE;
	}
	public void setTOTAL_REG_STATE(Number tOTAL_REG_STATE) {
		TOTAL_REG_STATE = tOTAL_REG_STATE;
	}
	public Number getTOTAL_OP_REG() {
		return TOTAL_OP_REG;
	}
	public void setTOTAL_OP_REG(Number tOTAL_OP_REG) {
		TOTAL_OP_REG = tOTAL_OP_REG;
	}
	public Number getNIMS_REG() {
		return NIMS_REG;
	}
	public void setNIMS_REG(Number nIMS_REG) {
		NIMS_REG = nIMS_REG;
	}
	public Number getNIMS_OP_REG() {
		return NIMS_OP_REG;
	}
	public void setNIMS_OP_REG(Number nIMS_OP_REG) {
		NIMS_OP_REG = nIMS_OP_REG;
	}
	public Number getTOTAL_CHRONIC_REG() {
		return TOTAL_CHRONIC_REG;
	}
	public void setTOTAL_CHRONIC_REG(Number tOTAL_CHRONIC_REG) {
		TOTAL_CHRONIC_REG = tOTAL_CHRONIC_REG;
	}
	public Number getNIMS_CHRONIC_OP() {
		return NIMS_CHRONIC_OP;
	}
	public void setNIMS_CHRONIC_OP(Number nIMS_CHRONIC_OP) {
		NIMS_CHRONIC_OP = nIMS_CHRONIC_OP;
	}
	public String getHosp() {
		return hosp;
	}
	public void setHosp(String hosp) {
		this.hosp = hosp;
	}
	public String getProcedure() {
		return procedure;
	}
	public void setProcedure(String procedure) {
		this.procedure = procedure;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getSlab() {
		return slab;
	}
	public void setSlab(String slab) {
		this.slab = slab;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}
	public String getGovtHospType() {
		return govtHospType;
	}
	public void setGovtHospType(String govtHospType) {
		this.govtHospType = govtHospType;
	}
	public String getPatDist() {
		return patDist;
	}
	public void setPatDist(String patDist) {
		this.patDist = patDist;
	}
	public String getHospDist() {
		return hospDist;
	}
	public void setHospDist(String hospDist) {
		this.hospDist = hospDist;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getPatDistrictId() {
		return patDistrictId;
	}
	public void setPatDistrictId(String patDistrictId) {
		this.patDistrictId = patDistrictId;
	}
	public String getPatMandalId() {
		return patMandalId;
	}
	public void setPatMandalId(String patMandalId) {
		this.patMandalId = patMandalId;
	}
	public String getHospDistId() {
		return hospDistId;
	}
	public void setHospDistId(String hospDistId) {
		this.hospDistId = hospDistId;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getProcedureType() {
		return procedureType;
	}
	public void setProcedureType(String procedureType) {
		this.procedureType = procedureType;
	}
	public String[] getCategoryList() {
		return categoryList;
	}
	public void setCategoryList(String[] categoryList) {
		this.categoryList = categoryList;
	}
	public String[] getProcedureList() {
		return procedureList;
	}
	public void setProcedureList(String[] procedureList) {
		this.procedureList = procedureList;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getIcdCatName() {
		return icdCatName;
	}
	public void setIcdCatName(String icdCatName) {
		this.icdCatName = icdCatName;
	}
	public String getProcedureCode() {
		return procedureCode;
	}
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}
	public String getProcName() {
		return procName;
	}
	public void setProcName(String procName) {
		this.procName = procName;
	}
	public String getHospDistChk() {
		return hospDistChk;
	}
	public void setHospDistChk(String hospDistChk) {
		this.hospDistChk = hospDistChk;
	}
	public String getPatDistChk() {
		return patDistChk;
	}
	public void setPatDistChk(String patDistChk) {
		this.patDistChk = patDistChk;
	}
	public String getMndl() {
		return mndl;
	}
	public void setMndl(String mndl) {
		this.mndl = mndl;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCaste() {
		return caste;
	}
	public void setCaste(String caste) {
		this.caste = caste;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getMonthWise() {
		return monthWise;
	}
	public void setMonthWise(String monthWise) {
		this.monthWise = monthWise;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getHospTypeChk() {
		return hospTypeChk;
	}
	public void setHospTypeChk(String hospTypeChk) {
		this.hospTypeChk = hospTypeChk;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPatName() {
		return patName;
	}
	public void setPatName(String patName) {
		this.patName = patName;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getNhCode() {
		return nhCode;
	}
	public void setNhCode(String nhCode) {
		this.nhCode = nhCode;
	}
	public String getNhLocation() {
		return nhLocation;
	}
	public void setNhLocation(String nhLocation) {
		this.nhLocation = nhLocation;
	}
	public String getPatType() {
		return patType;
	}
	public void setPatType(String patType) {
		this.patType = patType;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getCardTypeChk() {
		return cardTypeChk;
	}
	public void setCardTypeChk(String cardTypeChk) {
		this.cardTypeChk = cardTypeChk;
	}
	public String getHospTypeExcd() {
		return hospTypeExcd;
	}
	public void setHospTypeExcd(String hospTypeExcd) {
		this.hospTypeExcd = hospTypeExcd;
	}
	public String getBeneficiaryType() {
		return beneficiaryType;
	}
	public void setBeneficiaryType(String beneficiaryType) {
		this.beneficiaryType = beneficiaryType;
	}
	public String getHodId() {
		return hodId;
	}
	public void setHodId(String hodId) {
		this.hodId = hodId;
	}
	public String getTeleCase() {
		return teleCase;
	}
	public void setTeleCase(String teleCase) {
		this.teleCase = teleCase;
	}
	public Number getTherapiesPreauthed() {
		return therapiesPreauthed;
	}
	public void setTherapiesPreauthed(Number therapiesPreauthed) {
		this.therapiesPreauthed = therapiesPreauthed;
	}
	public String getTherapiesPreauthedAmt() {
		return therapiesPreauthedAmt;
	}
	public void setTherapiesPreauthedAmt(String therapiesPreauthedAmt) {
		this.therapiesPreauthedAmt = therapiesPreauthedAmt;
	}
	public Number getTherapiesRaised() {
		return therapiesRaised;
	}
	public void setTherapiesRaised(Number therapiesRaised) {
		this.therapiesRaised = therapiesRaised;
	}
	public String getTherapiesRaisedAmt() {
		return therapiesRaisedAmt;
	}
	public void setTherapiesRaisedAmt(String therapiesRaisedAmt) {
		this.therapiesRaisedAmt = therapiesRaisedAmt;
	}
	public Number getTherpiesDone() {
		return therpiesDone;
	}
	public void setTherpiesDone(Number therpiesDone) {
		this.therpiesDone = therpiesDone;
	}
	public String getTherpiesDoneAmt() {
		return therpiesDoneAmt;
	}
	public void setTherpiesDoneAmt(String therpiesDoneAmt) {
		this.therpiesDoneAmt = therpiesDoneAmt;
	}
	public String getComorbidAmt() {
		return comorbidAmt;
	}
	public void setComorbidAmt(String comorbidAmt) {
		this.comorbidAmt = comorbidAmt;
	}
	public Number getNoOfPatients() {
		return noOfPatients;
	}
	public void setNoOfPatients(Number noOfPatients) {
		this.noOfPatients = noOfPatients;
	}
	public String getEnhApprvAmt() {
		return enhApprvAmt;
	}
	public void setEnhApprvAmt(String enhApprvAmt) {
		this.enhApprvAmt = enhApprvAmt;
	}
	public String getPreauthAppvAmt() {
		return preauthAppvAmt;
	}
	public void setPreauthAppvAmt(String preauthAppvAmt) {
		this.preauthAppvAmt = preauthAppvAmt;
	}
	public Number getDischargeCnt() {
		return dischargeCnt;
	}
	public void setDischargeCnt(Number dischargeCnt) {
		this.dischargeCnt = dischargeCnt;
	}
	public Number getDischargeUpdCnt() {
		return dischargeUpdCnt;
	}
	public void setDischargeUpdCnt(Number dischargeUpdCnt) {
		this.dischargeUpdCnt = dischargeUpdCnt;
	}
	public String getIpopDate() {
		return ipopDate;
	}
	public void setIpopDate(String ipopDate) {
		this.ipopDate = ipopDate;
	}
	public Number getClaimSubmittedCnt() {
		return claimSubmittedCnt;
	}
	public void setClaimSubmittedCnt(Number claimSubmittedCnt) {
		this.claimSubmittedCnt = claimSubmittedCnt;
	}
	public String getClaimSubmittedAmt() {
		return claimSubmittedAmt;
	}
	public void setClaimSubmittedAmt(String claimSubmittedAmt) {
		this.claimSubmittedAmt = claimSubmittedAmt;
	}
	public Number getClaimPaidCnt() {
		return claimPaidCnt;
	}
	public void setClaimPaidCnt(Number claimPaidCnt) {
		this.claimPaidCnt = claimPaidCnt;
	}
	public String getClaimPaidAmt() {
		return claimPaidAmt;
	}
	public void setClaimPaidAmt(String claimPaidAmt) {
		this.claimPaidAmt = claimPaidAmt;
	}
	public Number getClaimApprovedCnt() {
		return claimApprovedCnt;
	}
	public void setClaimApprovedCnt(Number claimApprovedCnt) {
		this.claimApprovedCnt = claimApprovedCnt;
	}
	public String getClaimApprovedAmt() {
		return claimApprovedAmt;
	}
	public void setClaimApprovedAmt(String claimApprovedAmt) {
		this.claimApprovedAmt = claimApprovedAmt;
	}
	public String getSfpDate() {
		return sfpDate;
	}
	public void setSfpDate(String sfpDate) {
		this.sfpDate = sfpDate;
	}
	public String getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(String statusDate) {
		this.statusDate = statusDate;
	}
	public String getSfpFromDate() {
		return sfpFromDate;
	}
	public void setSfpFromDate(String sfpFromDate) {
		this.sfpFromDate = sfpFromDate;
	}
	public String getSfpToDate() {
		return sfpToDate;
	}
	public void setSfpToDate(String sfpToDate) {
		this.sfpToDate = sfpToDate;
	}
	public String getTpFromDate() {
		return tpFromDate;
	}
	public void setTpFromDate(String tpFromDate) {
		this.tpFromDate = tpFromDate;
	}
	public String getTpToDate() {
		return tpToDate;
	}
	public void setTpToDate(String tpToDate) {
		this.tpToDate = tpToDate;
	}
	public String getCaseNo() {
		return caseNo;
	}
	public void setCaseNo(String caseNo) {
		this.caseNo = caseNo;
	}
	public String getClaimNo() {
		return claimNo;
	}
	public void setClaimNo(String claimNo) {
		this.claimNo = claimNo;
	}
	public String getCaseStatus() {
		return caseStatus;
	}
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}
	public String getPreauthApprvDt() {
		return preauthApprvDt;
	}
	public void setPreauthApprvDt(String preauthApprvDt) {
		this.preauthApprvDt = preauthApprvDt;
	}
	public String getPreauthStatus() {
		return preauthStatus;
	}
	public void setPreauthStatus(String preauthStatus) {
		this.preauthStatus = preauthStatus;
	}
	public String getCaseSurgDt() {
		return caseSurgDt;
	}
	public void setCaseSurgDt(String caseSurgDt) {
		this.caseSurgDt = caseSurgDt;
	}
	public String getCaseSurgUpdDt() {
		return caseSurgUpdDt;
	}
	public void setCaseSurgUpdDt(String caseSurgUpdDt) {
		this.caseSurgUpdDt = caseSurgUpdDt;
	}
	public String getEnhRaisedAmt() {
		return enhRaisedAmt;
	}
	public void setEnhRaisedAmt(String enhRaisedAmt) {
		this.enhRaisedAmt = enhRaisedAmt;
	}
	public String getEnhRaisedDt() {
		return enhRaisedDt;
	}
	public void setEnhRaisedDt(String enhRaisedDt) {
		this.enhRaisedDt = enhRaisedDt;
	}
	public String getEnhApprvDt() {
		return enhApprvDt;
	}
	public void setEnhApprvDt(String enhApprvDt) {
		this.enhApprvDt = enhApprvDt;
	}
	public String getDeathDt() {
		return deathDt;
	}
	public void setDeathDt(String deathDt) {
		this.deathDt = deathDt;
	}
	public String getDeathType() {
		return deathType;
	}
	public void setDeathType(String deathType) {
		this.deathType = deathType;
	}
	public String getDeathTypeCode() {
		return deathTypeCode;
	}
	public void setDeathTypeCode(String deathTypeCode) {
		this.deathTypeCode = deathTypeCode;
	}
	public String getTreatingDocName() {
		return treatingDocName;
	}
	public void setTreatingDocName(String treatingDocName) {
		this.treatingDocName = treatingDocName;
	}
	public String getDocRegNum() {
		return docRegNum;
	}
	public void setDocRegNum(String docRegNum) {
		this.docRegNum = docRegNum;
	}
	public String getDischargeDt() {
		return dischargeDt;
	}
	public void setDischargeDt(String dischargeDt) {
		this.dischargeDt = dischargeDt;
	}
	public String getDischargeUpdDt() {
		return dischargeUpdDt;
	}
	public void setDischargeUpdDt(String dischargeUpdDt) {
		this.dischargeUpdDt = dischargeUpdDt;
	}
	public String getTelephonicInDt() {
		return telephonicInDt;
	}
	public void setTelephonicInDt(String telephonicInDt) {
		this.telephonicInDt = telephonicInDt;
	}
	public String getClaimSubDt() {
		return claimSubDt;
	}
	public void setClaimSubDt(String claimSubDt) {
		this.claimSubDt = claimSubDt;
	}
	public String getUserState() {
		return userState;
	}
	public void setUserState(String userState) {
		this.userState = userState;
	}
	public String getCategoryFlag() {
		return categoryFlag;
	}
	public void setCategoryFlag(String categoryFlag) {
		this.categoryFlag = categoryFlag;
	}
	
	public Number getTOTAL() {
		return TOTAL;
	}
	public void setTOTAL(Number tOTAL) {
		TOTAL = tOTAL;
	}
	
	public String getFLG() {
		return FLG;
	}
	public void setFLG(String fLG) {
		FLG = fLG;
	}
	public String getEMPLOYEE() {
		return EMPLOYEE;
	}
	public void setEMPLOYEE(String eMPLOYEE) {
		EMPLOYEE = eMPLOYEE;
	}
	public String getPENSIONER() {
		return PENSIONER;
	}
	public void setPENSIONER(String pENSIONER) {
		PENSIONER = pENSIONER;
	}
	public String getJOURNALIST() {
		return JOURNALIST;
	}
	public void setJOURNALIST(String jOURNALIST) {
		JOURNALIST = jOURNALIST;
	}
	public String getRETIRED_JOURNALIST() {
		return RETIRED_JOURNALIST;
	}
	public void setRETIRED_JOURNALIST(String rETIRED_JOURNALIST) {
		RETIRED_JOURNALIST = rETIRED_JOURNALIST;
	}
	public Number getLAST24HOURS() {
		return LAST24HOURS;
	}
	public void setLAST24HOURS(Number lAST24HOURS) {
		LAST24HOURS = lAST24HOURS;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	public String getDISTRICT() {
		return DISTRICT;
	}
	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}
	public Number getNON_NABH_COUNT() {
		return NON_NABH_COUNT;
	}
	public void setNON_NABH_COUNT(Number nON_NABH_COUNT) {
		NON_NABH_COUNT = nON_NABH_COUNT;
	}
	public Number getNON_NABH_AMOUNT() {
		return NON_NABH_AMOUNT;
	}
	public void setNON_NABH_AMOUNT(Number nON_NABH_AMOUNT) {
		NON_NABH_AMOUNT = nON_NABH_AMOUNT;
	}
	public Number getNABH_COUNT() {
		return NABH_COUNT;
	}
	public void setNABH_COUNT(Number nABH_COUNT) {
		NABH_COUNT = nABH_COUNT;
	}
	public Number getEXCLUDE_NABH_AMT() {
		return EXCLUDE_NABH_AMT;
	}
	public void setEXCLUDE_NABH_AMT(Number eXCLUDE_NABH_AMT) {
		EXCLUDE_NABH_AMT = eXCLUDE_NABH_AMT;
	}
	public Number getNABH_AMOUNT() {
		return NABH_AMOUNT;
	}
	public void setNABH_AMOUNT(Number nABH_AMOUNT) {
		NABH_AMOUNT = nABH_AMOUNT;
	}
	public Number getTOT_CLAIM_PAID_COUNT() {
		return TOT_CLAIM_PAID_COUNT;
	}
	public void setTOT_CLAIM_PAID_COUNT(Number tOT_CLAIM_PAID_COUNT) {
		TOT_CLAIM_PAID_COUNT = tOT_CLAIM_PAID_COUNT;
	}
	public Number getTOT_CLAIM_PAID_AMT() {
		return TOT_CLAIM_PAID_AMT;
	}
	public void setTOT_CLAIM_PAID_AMT(Number tOT_CLAIM_PAID_AMT) {
		TOT_CLAIM_PAID_AMT = tOT_CLAIM_PAID_AMT;
	}
	public String getDateChk1() {
		return dateChk1;
	}
	public void setDateChk1(String dateChk1) {
		this.dateChk1 = dateChk1;
	}
	
	private String PREAUTHCOUNT;
	private String SURGCOUNT;
	private String PREAUTHAMOUNT;
	private String SURGAMOUNT;
	private String ENHAPPRVAMT;
	private String TOTALPREAUTHAMOUNT;

	public String getSchemeType() {
		return schemeType;
	}
	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}
	public String getPREAUTHCOUNT() {
		return PREAUTHCOUNT;
	}
	public void setPREAUTHCOUNT(String pREAUTHCOUNT) {
		PREAUTHCOUNT = pREAUTHCOUNT;
	}
	public String getSURGCOUNT() {
		return SURGCOUNT;
	}
	public void setSURGCOUNT(String sURGCOUNT) {
		SURGCOUNT = sURGCOUNT;
	}
	public String getPREAUTHAMOUNT() {
		return PREAUTHAMOUNT;
	}
	public void setPREAUTHAMOUNT(String pREAUTHAMOUNT) {
		PREAUTHAMOUNT = pREAUTHAMOUNT;
	}
	public String getSURGAMOUNT() {
		return SURGAMOUNT;
	}
	public void setSURGAMOUNT(String sURGAMOUNT) {
		SURGAMOUNT = sURGAMOUNT;
	}
	public String getENHAPPRVAMT() {
		return ENHAPPRVAMT;
	}
	public void setENHAPPRVAMT(String eNHAPPRVAMT) {
		ENHAPPRVAMT = eNHAPPRVAMT;
	}
	public String getTOTALPREAUTHAMOUNT() {
		return TOTALPREAUTHAMOUNT;
	}
	public void setTOTALPREAUTHAMOUNT(String tOTALPREAUTHAMOUNT) {
		TOTALPREAUTHAMOUNT = tOTALPREAUTHAMOUNT;
	}
	
	private String preAuthCount;
	private String surgCount;
	private String preAuthAmount;
	private String surgAmount;
	private String enhnAmount;
	private String preAuthApprvAmount;
	private String totalPreAuthCount;

	public String getPreAuthCount() {
		return preAuthCount;
	}
	public void setPreAuthCount(String preAuthCount) {
		this.preAuthCount = preAuthCount;
	}
	public String getSurgCount() {
		return surgCount;
	}
	public void setSurgCount(String surgCount) {
		this.surgCount = surgCount;
	}
	public String getPreAuthAmount() {
		return preAuthAmount;
	}
	public void setPreAuthAmount(String preAuthAmount) {
		this.preAuthAmount = preAuthAmount;
	}
	public String getSurgAmount() {
		return surgAmount;
	}
	public void setSurgAmount(String surgAmount) {
		this.surgAmount = surgAmount;
	}
	public String getEnhnAmount() {
		return enhnAmount;
	}
	public void setEnhnAmount(String enhnAmount) {
		this.enhnAmount = enhnAmount;
	}
	public String getPreAuthApprvAmount() {
		return preAuthApprvAmount;
	}
	public void setPreAuthApprvAmount(String preAuthApprvAmount) {
		this.preAuthApprvAmount = preAuthApprvAmount;
	}
	public String getTotalPreAuthCount() {
		return totalPreAuthCount;
	}
	public void setTotalPreAuthCount(String totalPreAuthCount) {
		this.totalPreAuthCount = totalPreAuthCount;
	}
	
	private String HOSPITALCODE;
	private String HOSPITALNAME;
	private String YEAR;

	public String getHOSPITALCODE() {
		return HOSPITALCODE;
	}
	public void setHOSPITALCODE(String hOSPITALCODE) {
		HOSPITALCODE = hOSPITALCODE;
	}
	public String getHOSPITALNAME() {
		return HOSPITALNAME;
	}
	public void setHOSPITALNAME(String hOSPITALNAME) {
		HOSPITALNAME = hOSPITALNAME;
	}
	public String getYEAR() {
		return YEAR;
	}
	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}
	public String getBENTYPE() {
		return BENTYPE;
	}
	public void setBENTYPE(String bENTYPE) {
		BENTYPE = bENTYPE;
	}
	public String getBenTypeChk() {
		return benTypeChk;
	}
	public void setBenTypeChk(String benTypeChk) {
		this.benTypeChk = benTypeChk;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getHistoryIllness() {
		return historyIllness;
	}
	public void setHistoryIllness(String historyIllness) {
		this.historyIllness = historyIllness;
	}
	public String getHospAmt() {
		return hospAmt;
	}
	public void setHospAmt(String hospAmt) {
		this.hospAmt = hospAmt;
	}
	public String getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(String billAmt) {
		this.billAmt = billAmt;
	}
	public String getPatIpOpType() {
		return patIpOpType;
	}
	public void setPatIpOpType(String patIpOpType) {
		this.patIpOpType = patIpOpType;
	}
	public String getTDS_AMT() {
		return TDS_AMT;
	}
	public void setTDS_AMT(String tDS_AMT) {
		TDS_AMT = tDS_AMT;
	}
	public String getRF_AMT() {
		return RF_AMT;
	}
	public void setRF_AMT(String rF_AMT) {
		RF_AMT = rF_AMT;
	}
	public String getESTIMATED_AMOUNT() {
		return ESTIMATED_AMOUNT;
	}
	public void setESTIMATED_AMOUNT(String eSTIMATED_AMOUNT) {
		ESTIMATED_AMOUNT = eSTIMATED_AMOUNT;
	}
	
	private String stateType;



	public String getStateType() {
		return stateType;
	}
	public void setStateType(String stateType) {
		this.stateType = stateType;
	}
	public String getDeathCase() {
		return deathCase;
	}
	public void setDeathCase(String deathCase) {
		this.deathCase = deathCase;
	}
	public String getTdDate() {
		return tdDate;
	}
	public void setTdDate(String tdDate) {
		this.tdDate = tdDate;
	}
	public String getsName() {
		return sName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}
	public String getiName() {
		return iName;
	}
	public void setiName(String iName) {
		this.iName = iName;
	}
	public String getWellnesscenter() {
		return wellnesscenter;
	}
	public void setWellnesscenter(String wellnesscenter) {
		this.wellnesscenter = wellnesscenter;
	}
	public String getDispname() {
		return dispname;
	}
	public void setDispname(String dispname) {
		this.dispname = dispname;
	}
	public String getSNO() {
		return SNO;
	}
	public void setSNO(String sNO) {
		SNO = sNO;
	}
	public String getSelfDependent() {
		return selfDependent;
	}
	public void setSelfDependent(String selfDependent) {
		this.selfDependent = selfDependent;
	}
	public String getCaseRegDt() {
		return caseRegDt;
	}
	public void setCaseRegDt(String caseRegDt) {
		this.caseRegDt = caseRegDt;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	public String getDOCTORNAME() {
		return DOCTORNAME;
	}
	public void setDOCTORNAME(String dOCTORNAME) {
		DOCTORNAME = dOCTORNAME;
	}
	public Number getPACKAGE_AMOUNT() {
		return PACKAGE_AMOUNT;
	}
	public void setPACKAGE_AMOUNT(Number pACKAGE_AMOUNT) {
		PACKAGE_AMOUNT = pACKAGE_AMOUNT;
	}
	public String getCONTACT() {
		return CONTACT;
	}
	public void setCONTACT(String cONTACT) {
		CONTACT = cONTACT;
	}
	public String getTYPE() {
		return TYPE;
	}
	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}
	public String getDispCode() {
		return dispCode;
	}
	public void setDispCode(String dispCode) {
		this.dispCode = dispCode;
	}
}
