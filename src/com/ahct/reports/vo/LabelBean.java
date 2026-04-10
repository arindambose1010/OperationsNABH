package com.ahct.reports.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForward;
/**
 * 
 * @author Prem
 * @Description This Class act as Common Value Bean
 * @Date
 */
public class LabelBean implements Serializable {
	
	private String caseType;
	private String DIFFTIME;
	private String district;
	private String districtdd;
	private int maxresults;
	private int pageId;
 	private int totalPages;
 	private int totalRecords;
	private int strtIndex;
	private String districts;
	private String hospitalNames;
	private String distType;
	private String patientType;
	private String date;
	private String newgen;
	private String agewise;
	private Number REGISTRATION_NAME;
	private Number INVESTIGATION_NAMES;
	private Number DRUGNAMES;
	private Number EMPLOYEECOUNT;
	private Number PENSIONERCOUNT;
	private Number JOURNALISTCOUNT;
	private Number UNIQUE_DRUGS;
	private Number DRUG_ZERO;
	private String dName;
	private String ITEM_ID;
	private String CONTRACT_YEAR;
	private String PRICE;
	private String STOCKPRICE;
	private String TOT_STOCKPRICE;
	private String IND_NOTREC;
	private String NAT_STOCK;
	private String NAT_DEF;
	private String IND_VAL;
	private String DISPNAME1;
	

	private String  DRUG_TYPE_NAME;    
	private Number ADILABAD_PRSC_QTY;
	private Number ADILABAD_ISS_QTY;
	private Number KARIMNAGAR_PRSC_QTY;
	private Number KARIMNAGAR_ISS_QTY;
	private Number KHAIRATABAD_PRSC_QTY;
	private Number KHAIRATABAD_ISS_QTY;
	private Number KHAMMAM_PRSC_QTY;
	private Number KHAMMAM_ISS_QTY;
	private Number KUKATPALLY_PRSC_QTY;
	private Number KUKATPALLY_ISS_QTY;
	private Number MAHABUBNAGAR_PRSC_QTY;
	private Number MAHABUBNAGAR_ISS_QTY;
	private Number NALGONDA_PRSC_QTY;
	private Number NALGONDA_ISS_QTY;
	private Number NIZAMBAD_PRSC_QTY;
	private Number NIZAMBAD_ISS_QTY;
	private Number SANGAREDDY_PRSC_QTY;
	private Number SANGAREDDY_ISS_QTY;
	private Number SIDDIPET_PRSC_QTY;
	private Number SIDDIPET_ISS_QTY;
	private Number VANASTHALIPURAM_PRSC_QTY;
	private Number VANASTHALIPURAM_ISS_QTY;
	private Number WARANGAL_PRSC_QTY;
	private Number WARANGAL_ISS_QTY;
	private Number TOT_PRSC_DRUG_QTY;
	private Number TOT_ISS_DRUG_QTY;

	public Number getEMPLOYEECOUNT() {
		return EMPLOYEECOUNT;
	}

	public void setEMPLOYEECOUNT(Number eMPLOYEECOUNT) {
		EMPLOYEECOUNT = eMPLOYEECOUNT;
	}

	public Number getPENSIONERCOUNT() {
		return PENSIONERCOUNT;
	}

	public void setPENSIONERCOUNT(Number pENSIONERCOUNT) {
		PENSIONERCOUNT = pENSIONERCOUNT;
	}

	public Number getJOURNALISTCOUNT() {
		return JOURNALISTCOUNT;
	}

	public void setJOURNALISTCOUNT(Number jOURNALISTCOUNT) {
		JOURNALISTCOUNT = jOURNALISTCOUNT;
	}

	public Number getUNIQUE_DRUGS() {
		return UNIQUE_DRUGS;
	}

	public void setUNIQUE_DRUGS(Number uNIQUE_DRUGS) {
		UNIQUE_DRUGS = uNIQUE_DRUGS;
	}
	private Number AVGCOUNT;
	private String fromDate;
	private String toDate;
	private String destDate;
	private String categoryType;
	private String userState;
	private String patMandal;
	private String hospDistChk;
	private String hosId;
	private String hospDistId;
	private String patDistrictId;
	private String patMandalId;
	private String gender;
	private String GENDER1;
	private String categoryCode;
	private String monthWise;
	private String year;
	private String procedureCode;
	private String slab;
	private String cardType;
	private String govtHospType;
	private String hospName;
	private String hospDist;
	private String patDist;
	private String icdCatName;
	private String procName;
	private String mndl;
	private String age;
	private String caste;
	private String scheme;
	private String hospcategory;
	private String DISPNAME;
	private String CENTERID;
	private String ID;
	private String VALUE;
	private String CONST;
	private String LVL;
	private String NHCODE;
	private String NHLOC;
	private String WFTYPE;
	private Number TOTAL;
	private Number COUNT_NO;
	private Number COUNT_NO1;
	private Number COUNT_NO2;
	private char TYPE;
	private String REMARKS;
	private String UNITID;
	private String EMPID;
	private String EMPNAME;
	private String DSGNID;
	private String LOGINNAME;
	private String LEVELID;
	private Long IDVAL;
	private Number COUNT;
	private String USERID;
private String NEW_VILLAGE;
private String NEW_MAND;
private String NEW_DIST;
private String hospActiveYn;
private String INVOICENO;
private Number TOT_NOF_PATREG;

private Number NOF_OTPEX_PENDING;
private Number NOF_OTPEX_APPRV;

	public String getINVOICENO() {
	return INVOICENO;
}

public void setINVOICENO(String iNVOICENO) {
	INVOICENO = iNVOICENO;
}

	public String getHospActiveYn() {
	return hospActiveYn;
}

public void setHospActiveYn(String hospActiveYn) {
	this.hospActiveYn = hospActiveYn;
}

	public String getNEW_VILLAGE() {
	return NEW_VILLAGE;
}

public void setNEW_VILLAGE(String nEW_VILLAGE) {
	NEW_VILLAGE = nEW_VILLAGE;
}

public String getNEW_MAND() {
	return NEW_MAND;
}

public void setNEW_MAND(String nEW_MAND) {
	NEW_MAND = nEW_MAND;
}

public String getNEW_DIST() {
	return NEW_DIST;
}

public void setNEW_DIST(String nEW_DIST) {
	NEW_DIST = nEW_DIST;
}
	private String LOGINDATE;
	private String LOGINTIME;
	private String LOGOUTTIME;
	private String PRESC_QUANTITY;
	private String DRUGTYPES;
	private String DRUGIDS;
	public String getDRUGTYPES() {
		return DRUGTYPES;
	}

	public void setDRUGTYPES(String dRUGTYPES) {
		DRUGTYPES = dRUGTYPES;
	}

	public String getDRUGIDS() {
		return DRUGIDS;
	}

	public void setDRUGIDS(String dRUGIDS) {
		DRUGIDS = dRUGIDS;
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
	public String getLOGINDATE() {
		return LOGINDATE;
	}

	public void setLOGINDATE(String lOGINDATE) {
		LOGINDATE = lOGINDATE;
	}

	public String getLOGINTIME() {
		return LOGINTIME;
	}

	public void setLOGINTIME(String lOGINTIME) {
		LOGINTIME = lOGINTIME;
	}

	public String getLOGOUTTIME() {
		return LOGOUTTIME;
	}

	public void setLOGOUTTIME(String lOGOUTTIME) {
		LOGOUTTIME = lOGOUTTIME;
	}

	public String getLOGOUTDATE() {
		return LOGOUTDATE;
	}

	public void setLOGOUTDATE(String lOGOUTDATE) {
		LOGOUTDATE = lOGOUTDATE;
	}
	private String LOGOUTDATE;
	private String EMAILID;
	private String CUGNUM;
	private Date DTVAL;
	private String FOLLOWUP_ID;
	
	private String DRUGTYPE;
	private String DRUGNAME;
	private String QUANTITY;
	private String TOTALQUANTITY;
	private String WCNAME;
	private int SNO;
	private String INVESTIGATION_NAME;
	private String INVESTIGATIONS_PRESCRIBED;
	private String INVESTIGATIONS_DONE;

	private String WELLNESS_CENTER;
	private String TIME_SLOT;
	private String APP_DATE;
	private String CARD_NUMBER;
	private String SPECIALITY;
	private String REGISTRATIONS;
	private String	MOBILE_NO;
	private String CARD_TYPE;
	private String DISPID;
	private String DRUGCODE;
	private String INVOICE_NUM;
	private String UNIT_PRICE;
	private String MNFCTRNAME;
	private String DSTRBTRNAME;
	private String BATCHNO;
	private String EXPDT;
	private String PATIENTID;
	private String PATIENTNAME;
	private String CASEID;
	private String CMBNAME;
	private Number CID;
	private Number THERAPY;
	private String TAMOUNT;
	private String RELATION;
	private String SELFDEPENDENT;
	private String DESIGNATION;
	private String DEPARTMENT;
	
	private String MNFCTR_ID;
	private String DSTRBTR_ID;
	private String DISP_ID;	

	//added bhaskar
	private String NON_NABH_COUNT;
	private String NON_NABH_AMOUNT;
	private String NABH_COUNT;
	private String EXCLUDE_NABH_AMT;
	private String NABH_AMOUNT;
	private String TOT_CLAIM_PAID_COUNT;
	private String TOT_CLAIM_PAID_AMT;
	
	
	private String NABHHOSP;
	private String PATDIST;
	private String TP_COUNT;
	private String TP_AMOUNT;
	private String TD_COUNT;
	private String TD_AMOUNT;
	private String CA_COUNT;
	private String CA_AMOUNT;
	private String CR_COUNT;
	private String CR_AMOUNT;
	private String CLB_A_COUNT;
	private String CLB_A_AMOUNT;
	
	private String PATMANID;
	private String PATMAN;
	private String CATNAME;
	private String CATCODE;
	private String PROCNAME;
	private String CARDTYPE;
	private String STATUPDATE;
	private String PROCCODE;
	private String SURGUPDATE;
	private String PATTYPE;
	private String BENEFICIARYTYPE;
	private String CASEREGDATE;
	private Number ADILABAD;
	private Number KARIMNAGAR;
	private Number KHAIRATABAD;
	private Number KHAMMAM;
	private Number KUKATPALLY;
	private Number MAHABUBNAGAR;
	private Number NALGONDA;
	private Number NIZAMBAD;
	private Number SANGAREDDY;
	private Number SIDDIPET;
	private Number VANASTHALIPURAM;
	private Number WARANGAL;


	
	
	
	
	public Number getADILABAD() {
		return ADILABAD;
	}

	public void setADILABAD(Number aDILABAD) {
		ADILABAD = aDILABAD;
	}
	
	public Number getKARIMNAGAR() {
		return KARIMNAGAR;
	}

	public void setKARIMNAGAR(Number kARIMNAGAR) {
		KARIMNAGAR = kARIMNAGAR;
	}

	public Number getKHAIRATABAD() {
		return KHAIRATABAD;
	}

	public void setKHAIRATABAD(Number kHAIRATABAD) {
		KHAIRATABAD = kHAIRATABAD;
	}

	public Number getKHAMMAM() {
		return KHAMMAM;
	}

	public void setKHAMMAM(Number kHAMMAM) {
		KHAMMAM = kHAMMAM;
	}

	public Number getKUKATPALLY() {
		return KUKATPALLY;
	}

	public void setKUKATPALLY(Number kUKATPALLY) {
		KUKATPALLY = kUKATPALLY;
	}

	public Number getMAHABUBNAGAR() {
		return MAHABUBNAGAR;
	}

	public void setMAHABUBNAGAR(Number mAHABUBNAGAR) {
		MAHABUBNAGAR = mAHABUBNAGAR;
	}

	public Number getNALGONDA() {
		return NALGONDA;
	}

	public void setNALGONDA(Number nALGONDA) {
		NALGONDA = nALGONDA;
	}

	public Number getNIZAMBAD() {
		return NIZAMBAD;
	}

	public void setNIZAMBAD(Number nIZAMBAD) {
		NIZAMBAD = nIZAMBAD;
	}

	public Number getSANGAREDDY() {
		return SANGAREDDY;
	}

	public void setSANGAREDDY(Number sANGAREDDY) {
		SANGAREDDY = sANGAREDDY;
	}

	public Number getSIDDIPET() {
		return SIDDIPET;
	}

	public void setSIDDIPET(Number sIDDIPET) {
		SIDDIPET = sIDDIPET;
	}

	public Number getVANASTHALIPURAM() {
		return VANASTHALIPURAM;
	}

	public void setVANASTHALIPURAM(Number vANASTHALIPURAM) {
		VANASTHALIPURAM = vANASTHALIPURAM;
	}

	public Number getWARANGAL() {
		return WARANGAL;
	}

	public void setWARANGAL(Number wARANGAL) {
		WARANGAL = wARANGAL;
	}

	public String getPATMANID() {
		return PATMANID;
	}

	public void setPATMANID(String pATMANID) {
		PATMANID = pATMANID;
	}

	public String getPATMAN() {
		return PATMAN;
	}

	public void setPATMAN(String pATMAN) {
		PATMAN = pATMAN;
	}

	public String getCATNAME() {
		return CATNAME;
	}

	public void setCATNAME(String cATNAME) {
		CATNAME = cATNAME;
	}

	public String getCATCODE() {
		return CATCODE;
	}

	public void setCATCODE(String cATCODE) {
		CATCODE = cATCODE;
	}

	public String getPROCNAME() {
		return PROCNAME;
	}

	public void setPROCNAME(String pROCNAME) {
		PROCNAME = pROCNAME;
	}

	public String getCARDTYPE() {
		return CARDTYPE;
	}

	public void setCARDTYPE(String cARDTYPE) {
		CARDTYPE = cARDTYPE;
	}

	public String getSTATUPDATE() {
		return STATUPDATE;
	}

	public void setSTATUPDATE(String sTATUPDATE) {
		STATUPDATE = sTATUPDATE;
	}

	public String getPROCCODE() {
		return PROCCODE;
	}

	public void setPROCCODE(String pROCCODE) {
		PROCCODE = pROCCODE;
	}

	public String getSURGUPDATE() {
		return SURGUPDATE;
	}

	public void setSURGUPDATE(String sURGUPDATE) {
		SURGUPDATE = sURGUPDATE;
	}

	public String getCR_COUNT() {
		return CR_COUNT;
	}

	public void setCR_COUNT(String cR_COUNT) {
		CR_COUNT = cR_COUNT;
	}

	public String getCR_AMOUNT() {
		return CR_AMOUNT;
	}

	public void setCR_AMOUNT(String cR_AMOUNT) {
		CR_AMOUNT = cR_AMOUNT;
	}

	public String getCLB_A_COUNT() {
		return CLB_A_COUNT;
	}

	public void setCLB_A_COUNT(String cLB_A_COUNT) {
		CLB_A_COUNT = cLB_A_COUNT;
	}

	public String getCLB_A_AMOUNT() {
		return CLB_A_AMOUNT;
	}

	public void setCLB_A_AMOUNT(String cLB_A_AMOUNT) {
		CLB_A_AMOUNT = cLB_A_AMOUNT;
	}

	public String getPATDIST() {
		return PATDIST;
	}

	public void setPATDIST(String pATDIST) {
		PATDIST = pATDIST;
	}

	public String getTP_COUNT() {
		return TP_COUNT;
	}

	public void setTP_COUNT(String tP_COUNT) {
		TP_COUNT = tP_COUNT;
	}

	public String getTP_AMOUNT() {
		return TP_AMOUNT;
	}

	public void setTP_AMOUNT(String tP_AMOUNT) {
		TP_AMOUNT = tP_AMOUNT;
	}

	public String getTD_COUNT() {
		return TD_COUNT;
	}

	public void setTD_COUNT(String tD_COUNT) {
		TD_COUNT = tD_COUNT;
	}

	public String getTD_AMOUNT() {
		return TD_AMOUNT;
	}

	public void setTD_AMOUNT(String tD_AMOUNT) {
		TD_AMOUNT = tD_AMOUNT;
	}

	public String getCA_COUNT() {
		return CA_COUNT;
	}

	public void setCA_COUNT(String cA_COUNT) {
		CA_COUNT = cA_COUNT;
	}

	public String getCA_AMOUNT() {
		return CA_AMOUNT;
	}

	public void setCA_AMOUNT(String cA_AMOUNT) {
		CA_AMOUNT = cA_AMOUNT;
	}

	
	public String getTAMOUNT() {
		return TAMOUNT;
	}

	public void setTAMOUNT(String tAMOUNT) {
		TAMOUNT = tAMOUNT;
	}

	public Number getCID() {
		return CID;
	}

	public void setCID(Number cID) {
		CID = cID;
	}

	public Number getTHERAPY() {
		return THERAPY;
	}

	public void setTHERAPY(Number tHERAPY) {
		THERAPY = tHERAPY;
	}

	public String getCMBNAME() {
		return CMBNAME;
	}

	public void setCMBNAME(String cMBNAME) {
		CMBNAME = cMBNAME;
	}




	public String getPATIENTID() {
		return PATIENTID;
	}

	public void setPATIENTID(String pATIENTID) {
		PATIENTID = pATIENTID;
	}

	public String getCASEID() {
		return CASEID;
	}

	public void setCASEID(String cASEID) {
		CASEID = cASEID;
	}

	public String getMNFCTRNAME() {
		return MNFCTRNAME;
	}

	public void setMNFCTRNAME(String mNFCTRNAME) {
		MNFCTRNAME = mNFCTRNAME;
	}

	public String getDSTRBTRNAME() {
		return DSTRBTRNAME;
	}

	public void setDSTRBTRNAME(String dSTRBTRNAME) {
		DSTRBTRNAME = dSTRBTRNAME;
	}

	public String getBATCHNO() {
		return BATCHNO;
	}

	public void setBATCHNO(String bATCHNO) {
		BATCHNO = bATCHNO;
	}

	public String getEXPDT() {
		return EXPDT;
	}

	public void setEXPDT(String eXPDT) {
		EXPDT = eXPDT;
	}

	public String getCARD_NUMBER() {
		return CARD_NUMBER;
	}

	public void setCARD_NUMBER(String cARD_NUMBER) {
		CARD_NUMBER = cARD_NUMBER;
	}

	public String getSPECIALITY() {
		return SPECIALITY;
	}

	public void setSPECIALITY(String sPECIALITY) {
		SPECIALITY = sPECIALITY;
	}

	public String getREGISTRATIONS() {
		return REGISTRATIONS;
	}

	public void setREGISTRATIONS(String rEGISTRATIONS) {
		REGISTRATIONS = rEGISTRATIONS;
	}

	public String getCARD_TYPE() {
		return CARD_TYPE;
	}

	public void setCARD_TYPE(String cARD_TYPE) {
		CARD_TYPE = cARD_TYPE;
	}

	public String getINVESTIGATION_NAME() {
		return INVESTIGATION_NAME;
	}

	public void setINVESTIGATION_NAME(String iNVESTIGATION_NAME) {
		INVESTIGATION_NAME = iNVESTIGATION_NAME;
	}

	public String getINVESTIGATIONS_PRESCRIBED() {
		return INVESTIGATIONS_PRESCRIBED;
	}

	public void setINVESTIGATIONS_PRESCRIBED(String iNVESTIGATIONS_PRESCRIBED) {
		INVESTIGATIONS_PRESCRIBED = iNVESTIGATIONS_PRESCRIBED;
	}

	public String getINVESTIGATIONS_DONE() {
		return INVESTIGATIONS_DONE;
	}

	public void setINVESTIGATIONS_DONE(String iNVESTIGATIONS_DONE) {
		INVESTIGATIONS_DONE = iNVESTIGATIONS_DONE;
	}

	public String getWELLNESS_CENTER() {
		return WELLNESS_CENTER;
	}

	public void setWELLNESS_CENTER(String wELLNESS_CENTER) {
		WELLNESS_CENTER = wELLNESS_CENTER;
	}

	public String getTIME_SLOT() {
		return TIME_SLOT;
	}

	public void setTIME_SLOT(String tIME_SLOT) {
		TIME_SLOT = tIME_SLOT;
	}

	public String getAPP_DATE() {
		return APP_DATE;
	}

	public void setAPP_DATE(String aPP_DATE) {
		APP_DATE = aPP_DATE;
	}

	

	public String getFOLLOWUP_ID() {
		return FOLLOWUP_ID;
	}

	public void setFOLLOWUP_ID(String fOLLOWUP_ID) {
		FOLLOWUP_ID = fOLLOWUP_ID;
	}
	// From eOffice
	
	private String USERNAME;
	private String wellnesslist;
	private String INITUNIT;
	private char GRADE;
	private Date INTERVIEWDATE;
	private String INTERVIEWTIME;
	private String INTERVIEWFLAG;
	private char INTERVIEWFLAG1;
	private String INTERVIEWDT;
	private String RRFSTATUS;
	private String VGLSTATUS;
	private String ISMISUPLOADED;
	private String INTERNALSTATUS;
	private Number JOBMVMNTID;
	private Number NOTECNTR;
	private Number GENNTFCNTR;
	private Number PARLLNTFCNTR;
	private String SUBID;
	private String SUBNAME;
	private String SUBDESC;
	private String SUBCODE;
	private String APPSPE;
	private String CALLID;
    private String DISTRICT;
    private String HOSPITAL;
    private String COMPROLE;
    private String COMPON;
    private String MCINO;
    private String COMPDESC;
    private String NATURE;
    private String LINENO;
    private String NATUREID;
	private String DEPTID;
	private String SECTIONID;
	private String JOBID;
	private String FILESTATUS;
	private String PRIORITYID;
	private String NODEREFID;
	private String REGNO;
	private String DOCTITLE;
	private String DOCID;
	private String INITVALUE;
	private String FILESTAGE;
	private String INTERVIEWROUND;
	private String PRECEDENTCASE;
	private String DRAFTS;
	private String KNOWLEDGEBANK;
	private String CHECKLIST;
	private String LINKEDFILES;
	private String INTERVIEWSTATUS;
	private Number INTERVIEWCODE;
	private String DSGN;
	private String PHOTOUPLOAD;
	private int NOTECOUNTER;
	private String UNITNAME;
	private String EMPCODE;
	private String UNITTYPE;
	private String PARENT_UNITID;
	private String EMPASRIROLE;
	private String EMPREPORTINGASRIROLE;
	private String DEPTNAME;
	private String DSGNNAME;
	private String ALLOCATIONFLAG;
	private String DEPTTYPE;
	private String DISTRICTNAME;

	private String PRIMARY_FLAG;
	// added bt padma
	private Number KEY;
	private Number HEADID;
	private Number SUBHEADID;
	private Number ACCTDTLDHEADID;
	private String LOCID;
	// By anitha
	private String NEWEMPCODE;
	private String POSTCODE;
	//
	private String HOSPITALNAME;
	private String HOSPITALLOCATION;
    private Number NEXTVAL;

	// By Mahanandi
	private String EMPDOB;
	private String EMPGEN;
	private String EMPMSTAT;
	private String EMPNAT;
	private String EMPDISABLED;
	private String EMPDISIBLY;
	private String EMPDISPERCNT;
	private String EMPCOMMTY;
	private String EMPCASTE;
	private String EMPNMTDFMEM;
	private String EMPHDIST;
	private String EMPMDL;
	private String EMPVLGE;
	private String EMPHNO;
	private String EMPSTRTNO;
	private String EMPRESVLGE;
	private String EMPRESMDL;
	private String EMPRESDIST;
	private String EMPRESPH;
	private String EMPRESMAIL;
	private String EMPOFCHNO;
	private String EMPOFCSTRTNO;
	private String EMPOFCVLGE;
	private String EMPOFCMDL;
	private String EMPOFCDIST;
	private String EMPOFCPH;
	private String EMPOFCMAIL;
	private String EMPPAN;
	private String EMPIDM1;
	private String EMPIDM2;
	private String EMPRATNCRDNO;
	private String EMPPRC;
	private String EMPPSCALE;
	private String EMPCURRPAY;
	private String EMPDEPT;
	private String EMPHOD;
	private String EMPPOSTDIST;
	private String EMPPOSTDDOCODE;
	private String EMPSERVDSGN;
	private String EMPDDOOFCUNIT;
	private String EMPDSGN;
	private String EMPPOSTCODE;
	private String AADHARID;
	private String EMPZPPF;
	private String EMPGPF;
	private String EMPAPGLI;
	private String EMPPHOTO;
	private String PSCALE;
	private String EMPLASTPOSTDIST;
	private String STATUS;
	private String CRTDATE;
	private String PAYSOURCE;
	private String RETIREMENTTYPE;
	private String SEQDATE;
	private String EHFCARDNO;
	private String BIOMETRICSTRING;
	private Number WAITCNT;
	private Number REJCNT;
	private Number APVDCNT;
	private Number WAITCNT1;
	private Number REJCNT1;
	private Number APVDCNT1;
	private Number exeRjct;
	private Number tlRjct;
	private Date DOB;
	private Date DOR;
	private Date NATDATE;
	private String SERVICE;
	private Number exeWait;
	private Number tlWait;
	private String empHstate;
	private String empOstate;
	private String postState;
	private String lastPostState;
	private String CICDIST;
	private String CICNAME;
	private String patName;
	private Double PPDAPPAMT;
	//By Yashasvi

	private String TESTNAME;
	private Number DRUGCOUNT;
	private Number TESTCOUNT;
	private String TESTID;
	private Date CRDATE;
	private Number ROWNUM_;
	
	private String COLVALUES0;
	private String COLVALUES1;
	private String COLVALUES2;
	private String COLVALUES3;
	private String COLVALUES4;
	private String COLVALUES5;
	private String COLVALUES6;
	private String COLVALUES7;
	private String COLVALUES8;
	private String COLVALUES9;
	private String COLVALUES10;
	private String COLVALUES11;
	private String COLVALUES12;
	private String COLVALUES13;
	private String COLVALUES14;
	private String COLVALUES15;
	private String COLVALUES16;
	private String COLVALUES17;
	private String COLVALUES18;
	private String COLVALUES19;
	private String COLVALUES20;
	private String COLVALUES21;
	private String COLVALUES22;
	private String COLVALUES23;
	private String COLVALUES24;
	private String COLVALUES25;
	private String COLVALUES26;
	private String COLVALUES27;
	private String COLVALUES28;
	private String COLVALUES29;
	private String COLVALUES30;
	private String COLVALUES31;
	private String COLVALUES32;
	private String COLVALUES33;
	private String COLVALUES34;
	private String COLVALUES35;
	private String COLVALUES36;
	private String COLVALUES37;
	private String COLVALUES38;
	private String COLVALUES39;
	private String COLVALUES40;
	private String COLVALUES41;
	private String COLVALUES42;
	private String COLVALUES43;
	private String COLVALUES44;
	private String COLVALUES45;
	private String COLVALUES46;
	private String COLVALUES47;
	private String COLVALUES48;
	private String COLVALUES49;
	private String COLVALUES50;
	private String COLVALUES51;
	private String COLVALUES52;
	private String COLVALUES53;
	private String COLVALUES54;
	private String COLVALUES55;
	private String COLVALUES56;
	private String COLVALUES57;
	private String COLVALUES58;
	private String COLVALUES59;
	
	//added by bhaskar
	private String COLNABH0;
	private String COLNABH1;
	private String COLNABH2;
	private String COLNABH3;
	private String COLNABH4;
	private String COLNABH5;
	private String COLNABH6;
	private String COLNABH7;
	private String COLNABH8;
	private String COLNABH9;
	private String COLNABH10;
	private String COLNABH11;
	private String COLNABH12;
	private String COLNABH13;
	private String COLNABH14;
	private String COLNABH15;
	private String COLNABH16;
	private String COLNABH17;
	private String COLNABH18;
	private String COLNABH19;
	private String COLNABH20;
	private String COLNABH21;
	private String COLNABH22;
	private String COLNABH23;
	private String COLNABH24;
	private String COLNABH25;
	private String COLNABH26;
	private String COLNABH27;
	private String COLNABH28;
	private String COLNABH29;
	private String COLNABH30;
	private String COLNABH31;
	private String COLNABH32;
	private String COLNABH33;
	private String COLNABH34;
	private String COLNABH35;
	private String COLNABH36;
	private String COLNABH37;
	private String COLNABH38;
	private String COLNABH39;
	private String COLNABH40;
	private String COLNABH41;
	private String COLNABH42;
	private String COLNABH43;
	private String COLNABH44;
	private String COLNABH45;
	private String COLNABH46;
	private String COLNABH47;
	private String COLNABH48;
	private String COLNABH49;
	private String COLNABH50;
	private String COLNABH51;
	private String COLNABH52;
	private String COLNABH53;
	private String COLNABH54;
	private String COLNABH55;
	private String COLNABH56;
	private String COLNABH57;
	private String COLNABH58;
	private String COLNABH59;
	private String COLNABH60;
	
	private String COLNONNABH0;
	private String COLNONNABH1;
	private String COLNONNABH2;
	private String COLNONNABH3;
	private String COLNONNABH4;
	private String COLNONNABH5;
	private String COLNONNABH6;
	private String COLNONNABH7;
	private String COLNONNABH8;
	private String COLNONNABH9;
	private String COLNONNABH10;
	private String COLNONNABH11;
	private String COLNONNABH12;
	private String COLNONNABH13;
	private String COLNONNABH14;
	private String COLNONNABH15;
	private String COLNONNABH16;
	private String COLNONNABH17;
	private String COLNONNABH18;
	private String COLNONNABH19;
	private String COLNONNABH20;
	private String COLNONNABH21;
	private String COLNONNABH22;
	private String COLNONNABH23;
	private String COLNONNABH24;
	private String COLNONNABH25;
	private String COLNONNABH26;
	private String COLNONNABH27;
	private String COLNONNABH28;
	private String COLNONNABH29;
	private String COLNONNABH30;
	private String COLNONNABH31;
	private String COLNONNABH32;
	private String COLNONNABH33;
	private String COLNONNABH34;
	private String COLNONNABH35;
	private String COLNONNABH36;
	private String COLNONNABH37;
	private String COLNONNABH38;
	private String COLNONNABH39;
	private String COLNONNABH40;
	private String COLNONNABH41;
	private String COLNONNABH42;
	private String COLNONNABH43;
	private String COLNONNABH44;
	private String COLNONNABH45;
	private String COLNONNABH46;
	private String COLNONNABH47;
	private String COLNONNABH48;
	private String COLNONNABH49;
	private String COLNONNABH50;
	private String COLNONNABH51;
	private String COLNONNABH52;
	private String COLNONNABH53;
	private String COLNONNABH54;
	private String COLNONNABH55;
	private String COLNONNABH56;
	private String COLNONNABH57;
	private String COLNONNABH58;
	private String COLNONNABH59;
	private String COLNONNABH60;
	
	private String COLNABHTOT0;
	private String COLNABHTOT1;
	private String COLNABHTOT2;
	private String COLNABHTOT3;
	private String COLNABHTOT4;
	private String COLNABHTOT5;
	private String COLNABHTOT6;
	private String COLNABHTOT7;
	private String COLNABHTOT8;
	private String COLNABHTOT9;
	private String COLNABHTOT10;
	private String COLNABHTOT11;
	private String COLNABHTOT12;
	
	private String COLNONNABHTOT0;
	private String COLNONNABHTOT1;
	private String COLNONNABHTOT2;
	private String COLNONNABHTOT3;
	private String COLNONNABHTOT4;
	private String COLNONNABHTOT5;
	private String COLNONNABHTOT6;
	private String COLNONNABHTOT7;
	private String COLNONNABHTOT8;
	private String COLNONNABHTOT9;
	private String COLNONNABHTOT10;
	private String COLNONNABHTOT11;
	private String COLNONNABHTOT12;

	
//by monika
	
	private String USRNAME;
	private String USRID;
	private String HRNUM;
	private String HOSPNAME;
	private String HOSPADD;
	private String BEDSTREN;
	private String PRESSTATUS;
	private String PRESSTATDT;
	private String CASENO;
	private String CLAIMSUB;
	
	
	private String HOSPDIST;
	private String HOSPDISTID;
	private String PATMANDAL;
	private String PATMANDALID;
	private String AGEGRP;
	private String MONTHWISE;
	private String BENTYPE;
	//added by bhaskar
	private Number PREAUTHAPPROVEDCUNTID;
	private Number SURGERIESDONECUNTID;
	private Number NUMBEROFDEATHSID;
	private Number CFRID;
	
	private Number CARDS;
	private Number PERSONS;
	private Number CASES;
	private Number CLRID;
	private Number THERAPIESPRE;
	private Number THERAPIESPREAMOUNT;
	
	
	
	

	public String getUSERNAME() {
		return USERNAME;
	}

	public void setUSERNAME(String uSERNAME) {
		USERNAME = uSERNAME;
	}

	public String getPRESSTATUS() {
		return PRESSTATUS;
	}

	public void setPRESSTATUS(String pRESSTATUS) {
		PRESSTATUS = pRESSTATUS;
	}

	public String getPRESSTATDT() {
		return PRESSTATDT;
	}

	public void setPRESSTATDT(String pRESSTATDT) {
		PRESSTATDT = pRESSTATDT;
	}

	public String getUSRNAME() {
		return USRNAME;
	}

	public void setUSRNAME(String uSRNAME) {
		USRNAME = uSRNAME;
	}

	public String getUSRID() {
		return USRID;
	}

	public void setUSRID(String uSRID) {
		USRID = uSRID;
	}

	public String getHRNUM() {
		return HRNUM;
	}

	public void setHRNUM(String hRNUM) {
		HRNUM = hRNUM;
	}

	public String getHOSPNAME() {
		return HOSPNAME;
	}

	public void setHOSPNAME(String hOSPNAME) {
		HOSPNAME = hOSPNAME;
	}

	public String getHOSPADD() {
		return HOSPADD;
	}

	public void setHOSPADD(String hOSPADD) {
		HOSPADD = hOSPADD;
	}

	public String getBEDSTREN() {
		return BEDSTREN;
	}

	public void setBEDSTREN(String bEDSTREN) {
		BEDSTREN = bEDSTREN;
	}

	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public String getAPPRECDT() {
		return APPRECDT;
	}

	public void setAPPRECDT(String aPPRECDT) {
		APPRECDT = aPPRECDT;
	}

	public String getPREVACTN() {
		return PREVACTN;
	}

	public void setPREVACTN(String pREVACTN) {
		PREVACTN = pREVACTN;
	}

	public String getPREVACTNDT() {
		return PREVACTNDT;
	}

	public void setPREVACTNDT(String pREVACTNDT) {
		PREVACTNDT = pREVACTNDT;
	}

	public String getPRESACTNDT() {
		return PRESACTNDT;
	}

	public void setPRESACTNDT(String pRESACTNDT) {
		PRESACTNDT = pRESACTNDT;
	}

	public String getPRESACTN() {
		return PRESACTN;
	}

	public void setPRESACTN(String pRESACTN) {
		PRESACTN = pRESACTN;
	}

	public String getPRESACTNTAKNBY() {
		return PRESACTNTAKNBY;
	}

	public void setPRESACTNTAKNBY(String pRESACTNTAKNBY) {
		PRESACTNTAKNBY = pRESACTNTAKNBY;
	}

	public String getFINALSTATUS() {
		return FINALSTATUS;
	}

	public void setFINALSTATUS(String fINALSTATUS) {
		FINALSTATUS = fINALSTATUS;
	}

	public String getFINALSTATUSSTATUSDT() {
		return FINALSTATUSSTATUSDT;
	}

	public void setFINALSTATUSSTATUSDT(String fINALSTATUSSTATUSDT) {
		FINALSTATUSSTATUSDT = fINALSTATUSSTATUSDT;
	}

	public String getCURREMBLOC1() {
		return CURREMBLOC1;
	}

	public void setCURREMBLOC1(String cURREMBLOC1) {
		CURREMBLOC1 = cURREMBLOC1;
	}

	public String getCURREMBLC2() {
		return CURREMBLC2;
	}

	public void setCURREMBLC2(String cURREMBLC2) {
		CURREMBLC2 = cURREMBLC2;
	}

	public String getCURREMBLC3() {
		return CURREMBLC3;
	}

	public void setCURREMBLC3(String cURREMBLC3) {
		CURREMBLC3 = cURREMBLC3;
	}

	public String getCURREMBLC4() {
		return CURREMBLC4;
	}

	public void setCURREMBLC4(String cURREMBLC4) {
		CURREMBLC4 = cURREMBLC4;
	}
	private String CITY;
	private String APPRECDT;
	private String PREVACTN;
	private String PREVACTNDT;
	private String PRESACTNDT;
	private String PRESACTN;
	private String PRESACTNTAKNBY;
	private String FINALSTATUS;
	private String FINALSTATUSSTATUSDT;
	private String CURREMBLOC1;
	private String CURREMBLC2;
	private String CURREMBLC3;
	private String CURREMBLC4;
	
	private String NAME;
	private String ISSUEDATE;
	private String MONTH;
	private String EMPLOYEE;
	private String PENSIONER;
	private String JOURNALIST;
	
	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}
	private Number APPLCTNS;
	
	

	
	
	public Number getAPPLCTNS() {
		return APPLCTNS;
	}

	public void setAPPLCTNS(Number aPPLCTNS) {
		APPLCTNS = aPPLCTNS;
	}
	private String PATIENT_DISTRICT;
	private String DDO_DIST;
	private Date PREVIOUS_STATUS_DATE;
	private Date CURRENT_STATUS_DATE;
	private String ACTION_TAKEN_REMARKS;
	private String STATUS_AFTER_PROCESSING;
	private String USER_NAME;
	private String ROLE;
	private String HOSPITAL_NAME;
	private String HOSPITAL_CODE;
	private String PROCESSED_DATE;
	private String CURRENT_STATUS_NAME;
	private Date STATUS_DATE;
	private String CSNO;
	private String CLMNO;
	private String PREVIOUS_STATUS_NAME;
	private String CARD_NO;
	private String PATIENT_NAME;
	private String ADDRESS;
	private String VILLAGE;
	private String MANDAL;
	private String CONTACT;
	private String AGE;
	private Number PACKAGE_AMOUNT;
	private Number AVAILABLE_QUANTITY;
	private Number APPROVED_AMOUNT;
	private String EMPLOYEE_PENSINOR;
	private String STATUS_NAME;
	public String getSTATUS_NAME() {
		return STATUS_NAME;
	}

	public void setSTATUS_NAME(String sTATUS_NAME) {
		STATUS_NAME = sTATUS_NAME;
	}
	private String USER_ID;
	
	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getEMPLOYEE_PENSINOR() {
		return EMPLOYEE_PENSINOR;
	}

	public void setEMPLOYEE_PENSINOR(String eMPLOYEE_PENSINOR) {
		EMPLOYEE_PENSINOR = eMPLOYEE_PENSINOR;
	}

	public String getAGE() {
		return AGE;
	}

	public void setAGE(String aGE) {
		AGE = aGE;
	}

	public Number getPACKAGE_AMOUNT() {
		return PACKAGE_AMOUNT;
	}

	public void setPACKAGE_AMOUNT(Number pACKAGE_AMOUNT) {
		PACKAGE_AMOUNT = pACKAGE_AMOUNT;
	}
	
	
	public Number getAVAILABLE_QUANTITY() {
		return AVAILABLE_QUANTITY;
	}

	public void setAVAILABLE_QUANTITY(Number aVAILABLE_QUANTITY) {
		AVAILABLE_QUANTITY = aVAILABLE_QUANTITY;
	}

	public Number getAPPROVED_AMOUNT() {
		return APPROVED_AMOUNT;
	}

	public void setAPPROVED_AMOUNT(Number aPPROVED_AMOUNT) {
		APPROVED_AMOUNT = aPPROVED_AMOUNT;
	}

	public String getCONTACT() {
		return CONTACT;
	}

	public void setCONTACT(String cONTACT) {
		CONTACT = cONTACT;
	}

	public String getMANDAL() {
		return MANDAL;
	}

	public void setMANDAL(String mANDAL) {
		MANDAL = mANDAL;
	}

	public String getVILLAGE() {
		return VILLAGE;
	}

	public void setVILLAGE(String vILLAGE) {
		VILLAGE = vILLAGE;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getPATIENT_NAME() {
		return PATIENT_NAME;
	}

	public void setPATIENT_NAME(String pATIENT_NAME) {
		PATIENT_NAME = pATIENT_NAME;
	}

	public String getCARD_NO() {
		return CARD_NO;
	}

	public void setCARD_NO(String cARD_NO) {
		CARD_NO = cARD_NO;
	}

	public String getPATIENT_DISTRICT() {
		return PATIENT_DISTRICT;
	}

	public void setPATIENT_DISTRICT(String pATIENT_DISTRICT) {
		PATIENT_DISTRICT = pATIENT_DISTRICT;
	}

	public String getDDO_DIST() {
		return DDO_DIST;
	}

	public void setDDO_DIST(String dDO_DIST) {
		DDO_DIST = dDO_DIST;
	}

	public Date getPREVIOUS_STATUS_DATE() {
		return PREVIOUS_STATUS_DATE;
	}

	public void setPREVIOUS_STATUS_DATE(Date pREVIOUS_STATUS_DATE) {
		PREVIOUS_STATUS_DATE = pREVIOUS_STATUS_DATE;
	}

	public Date getCURRENT_STATUS_DATE() {
		return CURRENT_STATUS_DATE;
	}

	public void setCURRENT_STATUS_DATE(Date cURRENT_STATUS_DATE) {
		CURRENT_STATUS_DATE = cURRENT_STATUS_DATE;
	}

	public String getACTION_TAKEN_REMARKS() {
		return ACTION_TAKEN_REMARKS;
	}

	public void setACTION_TAKEN_REMARKS(String aCTION_TAKEN_REMARKS) {
		ACTION_TAKEN_REMARKS = aCTION_TAKEN_REMARKS;
	}

	public String getSTATUS_AFTER_PROCESSING() {
		return STATUS_AFTER_PROCESSING;
	}

	public void setSTATUS_AFTER_PROCESSING(String sTATUS_AFTER_PROCESSING) {
		STATUS_AFTER_PROCESSING = sTATUS_AFTER_PROCESSING;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getROLE() {
		return ROLE;
	}

	public void setROLE(String rOLE) {
		ROLE = rOLE;
	}

	public String getHOSPITAL_NAME() {
		return HOSPITAL_NAME;
	}

	public void setHOSPITAL_NAME(String hOSPITAL_NAME) {
		HOSPITAL_NAME = hOSPITAL_NAME;
	}

	public String getHOSPITAL_CODE() {
		return HOSPITAL_CODE;
	}

	public void setHOSPITAL_CODE(String hOSPITAL_CODE) {
		HOSPITAL_CODE = hOSPITAL_CODE;
	}

	public String getPROCESSED_DATE() {
		return PROCESSED_DATE;
	}

	public void setPROCESSED_DATE(String pROCESSED_DATE) {
		PROCESSED_DATE = pROCESSED_DATE;
	}

	public String getCURRENT_STATUS_NAME() {
		return CURRENT_STATUS_NAME;
	}

	public void setCURRENT_STATUS_NAME(String cURRENT_STATUS_NAME) {
		CURRENT_STATUS_NAME = cURRENT_STATUS_NAME;
	}

	public Date getSTATUS_DATE() {
		return STATUS_DATE;
	}

	public void setSTATUS_DATE(Date sTATUS_DATE) {
		STATUS_DATE = sTATUS_DATE;
	}

	public String getCSNO() {
		return CSNO;
	}

	public void setCSNO(String cSNO) {
		CSNO = cSNO;
	}

	public String getCLMNO() {
		return CLMNO;
	}

	public void setCLMNO(String cLMNO) {
		CLMNO = cLMNO;
	}

	public String getPREVIOUS_STATUS_NAME() {
		return PREVIOUS_STATUS_NAME;
	}

	public void setPREVIOUS_STATUS_NAME(String pREVIOUS_STATUS_NAME) {
		PREVIOUS_STATUS_NAME = pREVIOUS_STATUS_NAME;
	}


	private String GRPID;
	private String GRPSHID;
	private String GRPNGID;
	private String ACTCSES;
	private String ACTUSR;
	private String AVGCSES;
	private String hosp;
	
	public String getGRPID() {
		return GRPID;
	}

	public void setGRPID(String gRPID) {
		GRPID = gRPID;
	}

	public String getGRPSHID() {
		return GRPSHID;
	}

	public void setGRPSHID(String gRPSHID) {
		GRPSHID = gRPSHID;
	}

	public String getGRPNGID() {
		return GRPNGID;
	}

	public void setGRPNGID(String gRPNGID) {
		GRPNGID = gRPNGID;
	}

	public String getACTCSES() {
		return ACTCSES;
	}

	public void setACTCSES(String aCTCSES) {
		ACTCSES = aCTCSES;
	}

	public String getACTUSR() {
		return ACTUSR;
	}

	public void setACTUSR(String aCTUSR) {
		ACTUSR = aCTUSR;
	}

	public String getAVGCSES() {
		return AVGCSES;
	}

	public void setAVGCSES(String aVGCSES) {
		AVGCSES = aVGCSES;
	}

	public String getCICDIST() {
		return CICDIST;
	}

	public void setCICDIST(String cICDIST) {
		CICDIST = cICDIST;
	}

	public String getCICNAME() {
		return CICNAME;
	}

	public void setCICNAME(String cICNAME) {
		CICNAME = cICNAME;
	}

	public Number getWAITCNT1() {
		return WAITCNT1;
	}

	public void setWAITCNT1(Number wAITCNT1) {
		WAITCNT1 = wAITCNT1;
	}

	public Number getREJCNT1() {
		return REJCNT1;
	}

	public void setREJCNT1(Number rEJCNT1) {
		REJCNT1 = rEJCNT1;
	}

	public Number getAPVDCNT1() {
		return APVDCNT1;
	}

	public void setAPVDCNT1(Number aPVDCNT1) {
		APVDCNT1 = aPVDCNT1;
	}

	public LabelBean(String ID, String VALUE) {
		this.ID = ID;
		this.VALUE = VALUE;
	}

	public LabelBean(Long IDVAL, String VALUE) {
		this.IDVAL = IDVAL;
		this.VALUE = VALUE;
	}

	public LabelBean(String ID, String VALUE, String CONST) {
		this.ID = ID;
		this.VALUE = VALUE;
		this.CONST = CONST;
	}

	public LabelBean(String ID, String VALUE, String CONST, String LVL) {
		this.ID = ID;
		this.VALUE = VALUE;
		this.CONST = CONST;
		this.LVL = LVL;
	}

	public LabelBean() {
	}

	public void setID(String iD) {
		this.ID = iD;
	}

	public String getID() {
		return ID;
	}

	public void setVALUE(String vALUE) {
		this.VALUE = vALUE;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setCONST(String cONST) {
		this.CONST = cONST;
	}

	public String getCONST() {
		return CONST;
	}

	public void setLVL(String lVL) {
		this.LVL = lVL;
	}

	public String getLVL() {
		return LVL;
	}

	public void setWFTYPE(String wFTYPE) {
		this.WFTYPE = wFTYPE;
	}

	public String getWFTYPE() {
		return WFTYPE;
	}

	public void setUNITID(String uNITID) {
		this.UNITID = uNITID;
	}

	public String getUNITID() {
		return UNITID;
	}

	public void setEMPID(String eMPID) {
		this.EMPID = eMPID;
	}

	public String getEMPID() {
		return EMPID;
	}

	public void setEMPNAME(String eMPNAME) {
		this.EMPNAME = eMPNAME;
	}

	public String getEMPNAME() {
		return EMPNAME;
	}

	public void setDSGNID(String dSGNID) {
		this.DSGNID = dSGNID;
	}

	public String getDSGNID() {
		return DSGNID;
	}

	public void setLOGINNAME(String lOGINNAME) {
		this.LOGINNAME = lOGINNAME;
	}

	public String getLOGINNAME() {
		return LOGINNAME;
	}

	public void setLEVELID(String lEVELID) {
		this.LEVELID = lEVELID;
	}

	public String getLEVELID() {
		return LEVELID;
	}

	public void setIDVAL(Long iDVAL) {
		this.IDVAL = iDVAL;
	}

	public Long getIDVAL() {
		return IDVAL;
	}

	public void setCOUNT(Number cOUNT) {
		this.COUNT = cOUNT;
	}

	public Number getCOUNT() {
		return COUNT;
	}

	public void setUSERID(String uSERID) {
		this.USERID = uSERID;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setEMAILID(String eMAILID) {
		this.EMAILID = eMAILID;
	}

	public String getEMAILID() {
		return EMAILID;
	}

	public void setCUGNUM(String cUGNUM) {
		this.CUGNUM = cUGNUM;
	}

	public String getCUGNUM() {
		return CUGNUM;
	}

	public void setINITUNIT(String iNITUNIT) {
		this.INITUNIT = iNITUNIT;
	}

	public String getINITUNIT() {
		return INITUNIT;
	}

	public void setGRADE(char gRADE) {
		this.GRADE = gRADE;
	}

	public char getGRADE() {
		return GRADE;
	}

	public void setINTERVIEWDATE(Date iNTERVIEWDATE) {
		this.INTERVIEWDATE = iNTERVIEWDATE;
	}

	public Date getINTERVIEWDATE() {
		return INTERVIEWDATE;
	}

	public void setINTERVIEWTIME(String iNTERVIEWTIME) {
		this.INTERVIEWTIME = iNTERVIEWTIME;
	}

	public String getINTERVIEWTIME() {
		return INTERVIEWTIME;
	}

	public void setINTERVIEWFLAG(String iNTERVIEWFLAG) {
		this.INTERVIEWFLAG = iNTERVIEWFLAG;
	}

	public String getINTERVIEWFLAG() {
		return INTERVIEWFLAG;
	}

	public void setINTERVIEWDT(String iNTERVIEWDT) {
		this.INTERVIEWDT = iNTERVIEWDT;
	}

	public String getINTERVIEWDT() {
		return INTERVIEWDT;
	}

	public void setRRFSTATUS(String rRFSTATUS) {
		this.RRFSTATUS = rRFSTATUS;
	}

	public String getRRFSTATUS() {
		return RRFSTATUS;
	}

	public void setVGLSTATUS(String vGLSTATUS) {
		this.VGLSTATUS = vGLSTATUS;
	}

	public String getVGLSTATUS() {
		return VGLSTATUS;
	}

	public void setISMISUPLOADED(String iSMISUPLOADED) {
		this.ISMISUPLOADED = iSMISUPLOADED;
	}

	public String getISMISUPLOADED() {
		return ISMISUPLOADED;
	}

	public void setINTERNALSTATUS(String iNTERNALSTATUS) {
		this.INTERNALSTATUS = iNTERNALSTATUS;
	}

	public String getINTERNALSTATUS() {
		return INTERNALSTATUS;
	}

	public void setJOBMVMNTID(Number jOBMVMNTID) {
		this.JOBMVMNTID = jOBMVMNTID;
	}

	public Number getJOBMVMNTID() {
		return JOBMVMNTID;
	}

	public void setNOTECNTR(Number nOTECNTR) {
		this.NOTECNTR = nOTECNTR;
	}

	public Number getNOTECNTR() {
		return NOTECNTR;
	}

	public void setGENNTFCNTR(Number gENNTFCNTR) {
		this.GENNTFCNTR = gENNTFCNTR;
	}

	public Number getGENNTFCNTR() {
		return GENNTFCNTR;
	}

	public void setPARLLNTFCNTR(Number pARLLNTFCNTR) {
		this.PARLLNTFCNTR = pARLLNTFCNTR;
	}

	public Number getPARLLNTFCNTR() {
		return PARLLNTFCNTR;
	}

	public void setSUBID(String sUBID) {
		this.SUBID = sUBID;
	}

	public String getSUBID() {
		return SUBID;
	}

	public String getSUBNAME() {
		return SUBNAME;
	}

	public void setSUBNAME(String sUBNAME) {
		SUBNAME = sUBNAME;
	}

	public String getSUBDESC() {
		return SUBDESC;
	}

	public void setSUBDESC(String sUBDESC) {
		SUBDESC = sUBDESC;
	}

	public String getSUBCODE() {
		return SUBCODE;
	}

	public void setSUBCODE(String sUBCODE) {
		SUBCODE = sUBCODE;
	}

	public String getAPPSPE() {
		return APPSPE;
	}

	public void setAPPSPE(String aPPSPE) {
		APPSPE = aPPSPE;
	}

	public String getCALLID() {
		return CALLID;
	}

	public void setCALLID(String cALLID) {
		CALLID = cALLID;
	}

	public String getDISTRICT() {
		return DISTRICT;
	}

	public void setDISTRICT(String dISTRICT) {
		DISTRICT = dISTRICT;
	}

	public String getHOSPITAL() {
		return HOSPITAL;
	}

	public void setHOSPITAL(String hOSPITAL) {
		HOSPITAL = hOSPITAL;
	}

	public String getCOMPROLE() {
		return COMPROLE;
	}

	public void setCOMPROLE(String cOMPROLE) {
		COMPROLE = cOMPROLE;
	}

	public String getCOMPON() {
		return COMPON;
	}

	public void setCOMPON(String cOMPON) {
		COMPON = cOMPON;
	}

	public String getMCINO() {
		return MCINO;
	}

	public void setMCINO(String mCINO) {
		MCINO = mCINO;
	}

	public String getCOMPDESC() {
		return COMPDESC;
	}

	public void setCOMPDESC(String cOMPDESC) {
		COMPDESC = cOMPDESC;
	}

	public String getNATURE() {
		return NATURE;
	}

	public void setNATURE(String nATURE) {
		NATURE = nATURE;
	}

	public String getLINENO() {
		return LINENO;
	}

	public void setLINENO(String lINENO) {
		LINENO = lINENO;
	}

	public String getNATUREID() {
		return NATUREID;
	}

	public void setNATUREID(String nATUREID) {
		NATUREID = nATUREID;
	}

	public void setDEPTID(String dEPTID) {
		this.DEPTID = dEPTID;
	}

	public String getDEPTID() {
		return DEPTID;
	}

	public void setSECTIONID(String sECTIONID) {
		this.SECTIONID = sECTIONID;
	}

	public String getSECTIONID() {
		return SECTIONID;
	}

	public void setJOBID(String jOBID) {
		this.JOBID = jOBID;
	}

	public String getJOBID() {
		return JOBID;
	}

	public void setFILESTATUS(String fILESTATUS) {
		this.FILESTATUS = fILESTATUS;
	}

	public String getFILESTATUS() {
		return FILESTATUS;
	}

	public void setPRIORITYID(String pRIORITYID) {
		this.PRIORITYID = pRIORITYID;
	}

	public String getPRIORITYID() {
		return PRIORITYID;
	}

	public void setNODEREFID(String nODEREFID) {
		this.NODEREFID = nODEREFID;
	}

	public String getNODEREFID() {
		return NODEREFID;
	}

	public void setREGNO(String rEGNO) {
		this.REGNO = rEGNO;
	}

	public String getREGNO() {
		return REGNO;
	}

	public void setDOCTITLE(String dOCTITLE) {
		this.DOCTITLE = dOCTITLE;
	}

	public String getDOCTITLE() {
		return DOCTITLE;
	}

	public void setDOCID(String dOCID) {
		this.DOCID = dOCID;
	}

	public String getDOCID() {
		return DOCID;
	}

	public void setINITVALUE(String iNITVALUE) {
		this.INITVALUE = iNITVALUE;
	}

	public String getINITVALUE() {
		return INITVALUE;
	}

	public void setFILESTAGE(String fILESTAGE) {
		this.FILESTAGE = fILESTAGE;
	}

	public String getFILESTAGE() {
		return FILESTAGE;
	}

	public void setINTERVIEWROUND(String iNTERVIEWROUND) {
		this.INTERVIEWROUND = iNTERVIEWROUND;
	}

	public String getINTERVIEWROUND() {
		return INTERVIEWROUND;
	}

	public void setPRECEDENTCASE(String pRECEDENTCASE) {
		this.PRECEDENTCASE = pRECEDENTCASE;
	}

	public String getPRECEDENTCASE() {
		return PRECEDENTCASE;
	}

	public void setDRAFTS(String dRAFTS) {
		this.DRAFTS = dRAFTS;
	}

	public String getDRAFTS() {
		return DRAFTS;
	}

	public void setKNOWLEDGEBANK(String kNOWLEDGEBANK) {
		this.KNOWLEDGEBANK = kNOWLEDGEBANK;
	}

	public String getKNOWLEDGEBANK() {
		return KNOWLEDGEBANK;
	}

	public void setCHECKLIST(String cHECKLIST) {
		this.CHECKLIST = cHECKLIST;
	}

	public String getCHECKLIST() {
		return CHECKLIST;
	}

	public void setLINKEDFILES(String lINKEDFILES) {
		this.LINKEDFILES = lINKEDFILES;
	}

	public String getLINKEDFILES() {
		return LINKEDFILES;
	}

	public void setINTERVIEWSTATUS(String iNTERVIEWSTATUS) {
		this.INTERVIEWSTATUS = iNTERVIEWSTATUS;
	}

	public String getINTERVIEWSTATUS() {
		return INTERVIEWSTATUS;
	}

	public void setINTERVIEWCODE(Number iNTERVIEWCODE) {
		this.INTERVIEWCODE = iNTERVIEWCODE;
	}

	public Number getINTERVIEWCODE() {
		return INTERVIEWCODE;
	}

	public void setDSGN(String dSGN) {
		this.DSGN = dSGN;
	}

	public String getDSGN() {
		return DSGN;
	}

	public void setPHOTOUPLOAD(String pHOTOUPLOAD) {
		this.PHOTOUPLOAD = pHOTOUPLOAD;
	}

	public String getPHOTOUPLOAD() {
		return PHOTOUPLOAD;
	}

	public void setNOTECOUNTER(int nOTECOUNTER) {
		this.NOTECOUNTER = nOTECOUNTER;
	}

	public int getNOTECOUNTER() {
		return NOTECOUNTER;
	}

	public void setUNITNAME(String uNITNAME) {
		this.UNITNAME = uNITNAME;
	}

	public String getUNITNAME() {
		return UNITNAME;
	}

	public void setEMPCODE(String eMPCODE) {
		this.EMPCODE = eMPCODE;
	}

	public String getEMPCODE() {
		return EMPCODE;
	}

	public void setUNITTYPE(String uNITTYPE) {
		this.UNITTYPE = uNITTYPE;
	}

	public String getUNITTYPE() {
		return UNITTYPE;
	}

	public void setPARENT_UNITID(String pARENT_UNITID) {
		this.PARENT_UNITID = pARENT_UNITID;
	}

	public String getPARENT_UNITID() {
		return PARENT_UNITID;
	}

	public void setEMPASRIROLE(String eMPASRIROLE) {
		this.EMPASRIROLE = eMPASRIROLE;
	}

	public String getEMPASRIROLE() {
		return EMPASRIROLE;
	}

	public void setEMPREPORTINGASRIROLE(String eMPREPORTINGASRIROLE) {
		this.EMPREPORTINGASRIROLE = eMPREPORTINGASRIROLE;
	}

	public String getEMPREPORTINGASRIROLE() {
		return EMPREPORTINGASRIROLE;
	}

	public void setDEPTNAME(String dEPTNAME) {
		this.DEPTNAME = dEPTNAME;
	}

	public String getDEPTNAME() {
		return DEPTNAME;
	}

	public void setDSGNNAME(String dSGNNAME) {
		this.DSGNNAME = dSGNNAME;
	}

	public String getDSGNNAME() {
		return DSGNNAME;
	}

	public void setALLOCATIONFLAG(String aLLOCATIONFLAG) {
		this.ALLOCATIONFLAG = aLLOCATIONFLAG;
	}

	public String getALLOCATIONFLAG() {
		return ALLOCATIONFLAG;
	}

	public void setDEPTTYPE(String dEPTTYPE) {
		this.DEPTTYPE = dEPTTYPE;
	}

	public String getDEPTTYPE() {
		return DEPTTYPE;
	}

	public void setDISTRICTNAME(String dISTRICTNAME) {
		this.DISTRICTNAME = dISTRICTNAME;
	}

	public String getDISTRICTNAME() {
		return DISTRICTNAME;
	}

	public void setTOTAL(Number tOTAL) {
		this.TOTAL = tOTAL;
	}

	public Number getTOTAL() {
		return TOTAL;
	}

	public void setREMARKS(String rEMARKS) {
		this.REMARKS = rEMARKS;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setDTVAL(Date dTVAL) {
		this.DTVAL = dTVAL;
	}

	public Date getDTVAL() {
		return DTVAL;
	}

	public void setTYPE(char tYPE) {
		this.TYPE = tYPE;
	}

	public char getTYPE() {
		return TYPE;
	}

	public void setPRIMARY_FLAG(String pRIMARY_FLAG) {
		this.PRIMARY_FLAG = pRIMARY_FLAG;
	}

	public String getPRIMARY_FLAG() {
		return PRIMARY_FLAG;
	}

	public void setKEY(Number kEY) {
		this.KEY = kEY;
	}

	public Number getKEY() {
		return KEY;
	}

	public void setHEADID(Number hEADID) {
		this.HEADID = hEADID;
	}

	public Number getHEADID() {
		return HEADID;
	}

	public void setSUBHEADID(Number sUBHEADID) {
		this.SUBHEADID = sUBHEADID;
	}

	public Number getSUBHEADID() {
		return SUBHEADID;
	}

	public void setACCTDTLDHEADID(Number aCCTDTLDHEADID) {
		this.ACCTDTLDHEADID = aCCTDTLDHEADID;
	}

	public Number getACCTDTLDHEADID() {
		return ACCTDTLDHEADID;
	}

	public void setLOCID(String lOCID) {
		this.LOCID = lOCID;
	}

	public String getLOCID() {
		return LOCID;
	}

	public void setNEWEMPCODE(String nEWEMPCODE) {
		this.NEWEMPCODE = nEWEMPCODE;
	}

	public String getNEWEMPCODE() {
		return NEWEMPCODE;
	}

	public void setPOSTCODE(String pOSTCODE) {
		this.POSTCODE = pOSTCODE;
	}

	public String getPOSTCODE() {
		return POSTCODE;
	}

	public void setHOSPITALNAME(String hOSPITALNAME) {
		this.HOSPITALNAME = hOSPITALNAME;
	}

	public String getHOSPITALNAME() {
		return HOSPITALNAME;
	}

	public void setHOSPITALLOCATION(String hOSPITALLOCATION) {
		this.HOSPITALLOCATION = hOSPITALLOCATION;
	}

	public String getHOSPITALLOCATION() {
		return HOSPITALLOCATION;
	}

	public void set_POSTCODE(String _POSTCODE) {
		this.POSTCODE = _POSTCODE;
	}

	public String get_POSTCODE() {
		return POSTCODE;
	}

	public void set_HOSPITALNAME(String _HOSPITALNAME) {
		this.HOSPITALNAME = _HOSPITALNAME;
	}

	public String get_HOSPITALNAME() {
		return HOSPITALNAME;
	}

	public void set_HOSPITALLOCATION(String _HOSPITALLOCATION) {
		this.HOSPITALLOCATION = _HOSPITALLOCATION;
	}

	public String get_HOSPITALLOCATION() {
		return HOSPITALLOCATION;
	}

	public void setEMPDOB(String eMPDOB) {
		this.EMPDOB = eMPDOB;
	}

	public String getEMPDOB() {
		return EMPDOB;
	}

	public void setEMPGEN(String eMPGEN) {
		this.EMPGEN = eMPGEN;
	}

	public String getEMPGEN() {
		return EMPGEN;
	}

	public void setEMPMSTAT(String eMPMSTAT) {
		this.EMPMSTAT = eMPMSTAT;
	}

	public String getEMPMSTAT() {
		return EMPMSTAT;
	}

	public void setEMPNAT(String eMPNAT) {
		this.EMPNAT = eMPNAT;
	}

	public String getEMPNAT() {
		return EMPNAT;
	}

	public void setEMPDISIBLY(String eMPDISIBLY) {
		this.EMPDISIBLY = eMPDISIBLY;
	}

	public String getEMPDISIBLY() {
		return EMPDISIBLY;
	}

	public void setEMPDISPERCNT(String eMPDISPERCNT) {
		this.EMPDISPERCNT = eMPDISPERCNT;
	}

	public String getEMPDISPERCNT() {
		return EMPDISPERCNT;
	}

	public void setEMPCOMMTY(String eMPCOMMTY) {
		this.EMPCOMMTY = eMPCOMMTY;
	}

	public String getEMPCOMMTY() {
		return EMPCOMMTY;
	}

	public void setEMPCASTE(String eMPCASTE) {
		this.EMPCASTE = eMPCASTE;
	}

	public String getEMPCASTE() {
		return EMPCASTE;
	}

	public void setEMPNMTDFMEM(String eMPNMTDFMEM) {
		this.EMPNMTDFMEM = eMPNMTDFMEM;
	}

	public String getEMPNMTDFMEM() {
		return EMPNMTDFMEM;
	}

	public void setEMPHDIST(String eMPHDIST) {
		this.EMPHDIST = eMPHDIST;
	}

	public String getEMPHDIST() {
		return EMPHDIST;
	}

	public void setEMPMDL(String eMPMDL) {
		this.EMPMDL = eMPMDL;
	}

	public String getEMPMDL() {
		return EMPMDL;
	}

	public void setEMPVLGE(String eMPVLGE) {
		this.EMPVLGE = eMPVLGE;
	}

	public String getEMPVLGE() {
		return EMPVLGE;
	}

	public void setEMPHNO(String eMPHNO) {
		this.EMPHNO = eMPHNO;
	}

	public String getEMPHNO() {
		return EMPHNO;
	}

	public void setEMPSTRTNO(String eMPSTRTNO) {
		this.EMPSTRTNO = eMPSTRTNO;
	}

	public String getEMPSTRTNO() {
		return EMPSTRTNO;
	}

	public void setEMPRESVLGE(String eMPRESVLGE) {
		this.EMPRESVLGE = eMPRESVLGE;
	}

	public String getEMPRESVLGE() {
		return EMPRESVLGE;
	}

	public void setEMPRESMDL(String eMPRESMDL) {
		this.EMPRESMDL = eMPRESMDL;
	}

	public String getEMPRESMDL() {
		return EMPRESMDL;
	}

	public void setEMPRESDIST(String eMPRESDIST) {
		this.EMPRESDIST = eMPRESDIST;
	}

	public String getEMPRESDIST() {
		return EMPRESDIST;
	}

	public void setEMPRESPH(String eMPRESPH) {
		this.EMPRESPH = eMPRESPH;
	}

	public String getEMPRESPH() {
		return EMPRESPH;
	}

	public void setEMPRESMAIL(String eMPRESMAIL) {
		this.EMPRESMAIL = eMPRESMAIL;
	}

	public String getEMPRESMAIL() {
		return EMPRESMAIL;
	}

	public void setEMPOFCHNO(String eMPOFCHNO) {
		this.EMPOFCHNO = eMPOFCHNO;
	}

	public String getEMPOFCHNO() {
		return EMPOFCHNO;
	}

	public void setEMPOFCSTRTNO(String eMPOFCSTRTNO) {
		this.EMPOFCSTRTNO = eMPOFCSTRTNO;
	}

	public String getEMPOFCSTRTNO() {
		return EMPOFCSTRTNO;
	}

	public void setEMPOFCVLGE(String eMPOFCVLGE) {
		this.EMPOFCVLGE = eMPOFCVLGE;
	}

	public String getEMPOFCVLGE() {
		return EMPOFCVLGE;
	}

	public void setEMPOFCMDL(String eMPOFCMDL) {
		this.EMPOFCMDL = eMPOFCMDL;
	}

	public String getEMPOFCMDL() {
		return EMPOFCMDL;
	}

	public void setEMPOFCDIST(String eMPOFCDIST) {
		this.EMPOFCDIST = eMPOFCDIST;
	}

	public String getEMPOFCDIST() {
		return EMPOFCDIST;
	}

	public void setEMPOFCPH(String eMPOFCPH) {
		this.EMPOFCPH = eMPOFCPH;
	}

	public String getEMPOFCPH() {
		return EMPOFCPH;
	}

	public void setEMPOFCMAIL(String eMPOFCMAIL) {
		this.EMPOFCMAIL = eMPOFCMAIL;
	}

	public String getEMPOFCMAIL() {
		return EMPOFCMAIL;
	}

	public void setEMPPAN(String eMPPAN) {
		this.EMPPAN = eMPPAN;
	}

	public String getEMPPAN() {
		return EMPPAN;
	}

	public void setEMPIDM1(String eMPIDM1) {
		this.EMPIDM1 = eMPIDM1;
	}

	public String getEMPIDM1() {
		return EMPIDM1;
	}

	public void setEMPIDM2(String eMPIDM2) {
		this.EMPIDM2 = eMPIDM2;
	}

	public String getEMPIDM2() {
		return EMPIDM2;
	}

	public void setEMPRATNCRDNO(String eMPRATNCRDNO) {
		this.EMPRATNCRDNO = eMPRATNCRDNO;
	}

	public String getEMPRATNCRDNO() {
		return EMPRATNCRDNO;
	}

	public void setEMPPRC(String eMPPRC) {
		this.EMPPRC = eMPPRC;
	}

	public String getEMPPRC() {
		return EMPPRC;
	}

	public void setEMPPSCALE(String eMPPSCALE) {
		this.EMPPSCALE = eMPPSCALE;
	}

	public String getEMPPSCALE() {
		return EMPPSCALE;
	}

	public void setEMPCURRPAY(String eMPCURRPAY) {
		this.EMPCURRPAY = eMPCURRPAY;
	}

	public String getEMPCURRPAY() {
		return EMPCURRPAY;
	}

	public void setEMPDEPT(String eMPDEPT) {
		this.EMPDEPT = eMPDEPT;
	}

	public String getEMPDEPT() {
		return EMPDEPT;
	}

	public void setEMPHOD(String eMPHOD) {
		this.EMPHOD = eMPHOD;
	}

	public String getEMPHOD() {
		return EMPHOD;
	}

	public void setEMPPOSTDIST(String eMPPOSTDIST) {
		this.EMPPOSTDIST = eMPPOSTDIST;
	}

	public String getEMPPOSTDIST() {
		return EMPPOSTDIST;
	}

	public void setEMPPOSTDDOCODE(String eMPPOSTDDOCODE) {
		this.EMPPOSTDDOCODE = eMPPOSTDDOCODE;
	}

	public String getEMPPOSTDDOCODE() {
		return EMPPOSTDDOCODE;
	}

	public void setEMPSERVDSGN(String eMPSERVDSGN) {
		this.EMPSERVDSGN = eMPSERVDSGN;
	}

	public String getEMPSERVDSGN() {
		return EMPSERVDSGN;
	}

	public void setEMPDDOOFCUNIT(String eMPDDOOFCUNIT) {
		this.EMPDDOOFCUNIT = eMPDDOOFCUNIT;
	}

	public String getEMPDDOOFCUNIT() {
		return EMPDDOOFCUNIT;
	}

	public void setEMPDSGN(String eMPDSGN) {
		this.EMPDSGN = eMPDSGN;
	}

	public String getEMPDSGN() {
		return EMPDSGN;
	}

	public void setEMPPOSTCODE(String eMPPOSTCODE) {
		this.EMPPOSTCODE = eMPPOSTCODE;
	}

	public String getEMPPOSTCODE() {
		return EMPPOSTCODE;
	}

	public void setAADHARID(String aADHARID) {
		this.AADHARID = aADHARID;
	}

	public String getAADHARID() {
		return AADHARID;
	}

	public void setEMPZPPF(String eMPZPPF) {
		this.EMPZPPF = eMPZPPF;
	}

	public String getEMPZPPF() {
		return EMPZPPF;
	}

	public void setEMPGPF(String eMPGPF) {
		this.EMPGPF = eMPGPF;
	}

	public String getEMPGPF() {
		return EMPGPF;
	}

	public void setEMPAPGLI(String eMPAPGLI) {
		this.EMPAPGLI = eMPAPGLI;
	}

	public String getEMPAPGLI() {
		return EMPAPGLI;
	}

	public void setEMPPHOTO(String eMPPHOTO) {
		this.EMPPHOTO = eMPPHOTO;
	}

	public String getEMPPHOTO() {
		return EMPPHOTO;
	}

	public void setPSCALE(String pSCALE) {
		this.PSCALE = pSCALE;
	}

	public String getPSCALE() {
		return PSCALE;
	}

	public void setEMPDISABLED(String eMPDISABLED) {
		this.EMPDISABLED = eMPDISABLED;
	}

	public String getEMPDISABLED() {
		return EMPDISABLED;
	}

	public void setEMPLASTPOSTDIST(String eMPLASTPOSTDIST) {
		this.EMPLASTPOSTDIST = eMPLASTPOSTDIST;
	}

	public String getEMPLASTPOSTDIST() {
		return EMPLASTPOSTDIST;
	}

	public void setSTATUS(String sTATUS) {
		this.STATUS = sTATUS;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setCRTDATE(String cRTDATE) {
		this.CRTDATE = cRTDATE;
	}

	public String getCRTDATE() {
		return CRTDATE;
	}

	public void setPAYSOURCE(String pAYSOURCE) {
		this.PAYSOURCE = pAYSOURCE;
	}

	public String getPAYSOURCE() {
		return PAYSOURCE;
	}

	public void setRETIREMENTTYPE(String rETIREMENTTYPE) {
		this.RETIREMENTTYPE = rETIREMENTTYPE;
	}

	public String getRETIREMENTTYPE() {
		return RETIREMENTTYPE;
	}

	public void setSEQDATE(String sEQDATE) {
		this.SEQDATE = sEQDATE;
	}

	public String getSEQDATE() {
		return SEQDATE;
	}

	public void setEHFCARDNO(String eHFCARDNO) {
		this.EHFCARDNO = eHFCARDNO;
	}

	public String getEHFCARDNO() {
		return EHFCARDNO;
	}

	public void setBIOMETRICSTRING(String bIOMETRICSTRING) {
		this.BIOMETRICSTRING = bIOMETRICSTRING;
	}

	public String getBIOMETRICSTRING() {
		return BIOMETRICSTRING;
	}

	public void setWAITCNT(Number wAITCNT) {
		this.WAITCNT = wAITCNT;
	}

	public Number getWAITCNT() {
		return WAITCNT;
	}

	public void setREJCNT(Number rEJCNT) {
		this.REJCNT = rEJCNT;
	}

	public Number getREJCNT() {
		return REJCNT;
	}

	public void setAPVDCNT(Number aPVDCNT) {
		this.APVDCNT = aPVDCNT;
	}

	public Number getAPVDCNT() {
		return APVDCNT;
	}

	public String getDOB() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(DOB);
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	

	public String getDOR() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		if(this.DOR==null)
			return "";
		else
		return sdf.format(DOR);
	}

	public void setDOR(Date dOR) {
		DOR = dOR;
	}

	public String getNATDATE() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(NATDATE);

	}

	public void setNATDATE(Date nATDATE) {
		NATDATE = nATDATE;
	}

	public String getSERVICE() {
		return SERVICE;
	}

	public void setSERVICE(String sERVICE) {
		SERVICE = sERVICE;
	}

	public Number getExeRjct() {
		return exeRjct;
	}

	public void setExeRjct(Number exeRjct) {
		this.exeRjct = exeRjct;
	}

	public Number getTlRjct() {
		return tlRjct;
	}

	public void setTlRjct(Number tlRjct) {
		this.tlRjct = tlRjct;
	}

	public Number getExeWait() {
		return exeWait;
	}

	public void setExeWait(Number exeWait) {
		this.exeWait = exeWait;
	}

	public Number getTlWait() {
		return tlWait;
	}

	public void setTlWait(Number tlWait) {
		this.tlWait = tlWait;
	}

	public String getEmpHstate() {
		return empHstate;
	}

	public void setEmpHstate(String empHstate) {
		this.empHstate = empHstate;
	}

	public String getEmpOstate() {
		return empOstate;
	}

	public void setEmpOstate(String empOstate) {
		this.empOstate = empOstate;
	}

	public String getPostState() {
		return postState;
	}

	public void setPostState(String postState) {
		this.postState = postState;
	}

	public String getLastPostState() {
		return lastPostState;
	}

	public void setLastPostState(String lastPostState) {
		this.lastPostState = lastPostState;
	}

	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}

	public Double getPPDAPPAMT() {
		return PPDAPPAMT;
	}

	public void setPPDAPPAMT(Double pPDAPPAMT) {
		PPDAPPAMT = pPDAPPAMT;
	}

	public String getDRUGNAME() {
		return DRUGNAME;
	}

	public void setDRUGNAME(String dRUGNAME) {
		DRUGNAME = dRUGNAME;
	}

	public String getTESTNAME() {
		return TESTNAME;
	}

	public void setTESTNAME(String tESTNAME) {
		TESTNAME = tESTNAME;
	}

	public Number getDRUGCOUNT() {
		return DRUGCOUNT;
	}

	public void setDRUGCOUNT(Number dRUGCOUNT) {
		DRUGCOUNT = dRUGCOUNT;
	}

	public Number getTESTCOUNT() {
		return TESTCOUNT;
	}

	public void setTESTCOUNT(Number tESTCOUNT) {
		TESTCOUNT = tESTCOUNT;
	}

	public String getTESTID() {
		return TESTID;
	}

	public void setTESTID(String tESTID) {
		TESTID = tESTID;
	}

	public Date getCRDATE() {
		return CRDATE;
	}

	public void setCRDATE(Date cRDATE) {
		CRDATE = cRDATE;
	}

	public Number getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(Number rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	
	



	public String getCOLVALUES0() {
		return COLVALUES0;
	}

	public void setCOLVALUES0(String cOLVALUES0) {
		COLVALUES0 = cOLVALUES0;
	}

	public String getCOLVALUES1() {
		return COLVALUES1;
	}

	public void setCOLVALUES1(String cOLVALUES1) {
		COLVALUES1 = cOLVALUES1;
	}

	public String getCOLVALUES2() {
		return COLVALUES2;
	}

	public void setCOLVALUES2(String cOLVALUES2) {
		COLVALUES2 = cOLVALUES2;
	}

	public String getCOLVALUES3() {
		return COLVALUES3;
	}

	public void setCOLVALUES3(String cOLVALUES3) {
		COLVALUES3 = cOLVALUES3;
	}

	public String getCOLVALUES4() {
		return COLVALUES4;
	}

	public void setCOLVALUES4(String cOLVALUES4) {
		COLVALUES4 = cOLVALUES4;
	}

	public String getCOLVALUES5() {
		return COLVALUES5;
	}

	public void setCOLVALUES5(String cOLVALUES5) {
		COLVALUES5 = cOLVALUES5;
	}

	public String getCOLVALUES6() {
		return COLVALUES6;
	}

	public void setCOLVALUES6(String cOLVALUES6) {
		COLVALUES6 = cOLVALUES6;
	}

	public String getCOLVALUES7() {
		return COLVALUES7;
	}

	public void setCOLVALUES7(String cOLVALUES7) {
		COLVALUES7 = cOLVALUES7;
	}

	public String getCOLVALUES8() {
		return COLVALUES8;
	}

	public void setCOLVALUES8(String cOLVALUES8) {
		COLVALUES8 = cOLVALUES8;
	}

	public String getCOLVALUES9() {
		return COLVALUES9;
	}

	public void setCOLVALUES9(String cOLVALUES9) {
		COLVALUES9 = cOLVALUES9;
	}

	public String getCOLVALUES10() {
		return COLVALUES10;
	}

	public void setCOLVALUES10(String cOLVALUES10) {
		COLVALUES10 = cOLVALUES10;
	}

	public String getCOLVALUES11() {
		return COLVALUES11;
	}

	public void setCOLVALUES11(String cOLVALUES11) {
		COLVALUES11 = cOLVALUES11;
	}

	public String getCOLVALUES12() {
		return COLVALUES12;
	}

	public void setCOLVALUES12(String cOLVALUES12) {
		COLVALUES12 = cOLVALUES12;
	}

	public String getCOLVALUES13() {
		return COLVALUES13;
	}

	public void setCOLVALUES13(String cOLVALUES13) {
		COLVALUES13 = cOLVALUES13;
	}

	public String getCOLVALUES14() {
		return COLVALUES14;
	}

	public void setCOLVALUES14(String cOLVALUES14) {
		COLVALUES14 = cOLVALUES14;
	}

	public String getCOLVALUES15() {
		return COLVALUES15;
	}

	public void setCOLVALUES15(String cOLVALUES15) {
		COLVALUES15 = cOLVALUES15;
	}

	public String getCOLVALUES16() {
		return COLVALUES16;
	}

	public void setCOLVALUES16(String cOLVALUES16) {
		COLVALUES16 = cOLVALUES16;
	}

	public String getCOLVALUES17() {
		return COLVALUES17;
	}

	public void setCOLVALUES17(String cOLVALUES17) {
		COLVALUES17 = cOLVALUES17;
	}

	public String getCOLVALUES18() {
		return COLVALUES18;
	}

	public void setCOLVALUES18(String cOLVALUES18) {
		COLVALUES18 = cOLVALUES18;
	}

	public String getCOLVALUES19() {
		return COLVALUES19;
	}

	public void setCOLVALUES19(String cOLVALUES19) {
		COLVALUES19 = cOLVALUES19;
	}

	public String getCOLVALUES20() {
		return COLVALUES20;
	}

	public void setCOLVALUES20(String cOLVALUES20) {
		COLVALUES20 = cOLVALUES20;
	}

	public String getCOLVALUES21() {
		return COLVALUES21;
	}

	public void setCOLVALUES21(String cOLVALUES21) {
		COLVALUES21 = cOLVALUES21;
	}

	public String getCOLVALUES22() {
		return COLVALUES22;
	}

	public void setCOLVALUES22(String cOLVALUES22) {
		COLVALUES22 = cOLVALUES22;
	}

	public String getCOLVALUES23() {
		return COLVALUES23;
	}

	public void setCOLVALUES23(String cOLVALUES23) {
		COLVALUES23 = cOLVALUES23;
	}

	public String getCOLVALUES24() {
		return COLVALUES24;
	}

	public void setCOLVALUES24(String cOLVALUES24) {
		COLVALUES24 = cOLVALUES24;
	}

	public String getCOLVALUES25() {
		return COLVALUES25;
	}

	public void setCOLVALUES25(String cOLVALUES25) {
		COLVALUES25 = cOLVALUES25;
	}

	public String getCOLVALUES26() {
		return COLVALUES26;
	}

	public void setCOLVALUES26(String cOLVALUES26) {
		COLVALUES26 = cOLVALUES26;
	}

	public String getCOLVALUES27() {
		return COLVALUES27;
	}

	public void setCOLVALUES27(String cOLVALUES27) {
		COLVALUES27 = cOLVALUES27;
	}

	public String getCOLVALUES28() {
		return COLVALUES28;
	}

	public void setCOLVALUES28(String cOLVALUES28) {
		COLVALUES28 = cOLVALUES28;
	}

	public String getCOLVALUES29() {
		return COLVALUES29;
	}

	public void setCOLVALUES29(String cOLVALUES29) {
		COLVALUES29 = cOLVALUES29;
	}

	public String getCOLVALUES30() {
		return COLVALUES30;
	}

	public void setCOLVALUES30(String cOLVALUES30) {
		COLVALUES30 = cOLVALUES30;
	}

	public String getCOLVALUES31() {
		return COLVALUES31;
	}

	public void setCOLVALUES31(String cOLVALUES31) {
		COLVALUES31 = cOLVALUES31;
	}

	public String getCOLVALUES32() {
		return COLVALUES32;
	}

	public void setCOLVALUES32(String cOLVALUES32) {
		COLVALUES32 = cOLVALUES32;
	}

	public String getCOLVALUES33() {
		return COLVALUES33;
	}

	public void setCOLVALUES33(String cOLVALUES33) {
		COLVALUES33 = cOLVALUES33;
	}

	public String getCOLVALUES34() {
		return COLVALUES34;
	}

	public void setCOLVALUES34(String cOLVALUES34) {
		COLVALUES34 = cOLVALUES34;
	}

	public String getCOLVALUES35() {
		return COLVALUES35;
	}

	public void setCOLVALUES35(String cOLVALUES35) {
		COLVALUES35 = cOLVALUES35;
	}

	public String getCOLVALUES36() {
		return COLVALUES36;
	}

	public void setCOLVALUES36(String cOLVALUES36) {
		COLVALUES36 = cOLVALUES36;
	}

	public String getCOLVALUES37() {
		return COLVALUES37;
	}

	public void setCOLVALUES37(String cOLVALUES37) {
		COLVALUES37 = cOLVALUES37;
	}

	public String getCOLVALUES38() {
		return COLVALUES38;
	}

	public void setCOLVALUES38(String cOLVALUES38) {
		COLVALUES38 = cOLVALUES38;
	}

	public String getCOLVALUES39() {
		return COLVALUES39;
	}

	public void setCOLVALUES39(String cOLVALUES39) {
		COLVALUES39 = cOLVALUES39;
	}

	public String getCOLVALUES40() {
		return COLVALUES40;
	}

	public void setCOLVALUES40(String cOLVALUES40) {
		COLVALUES40 = cOLVALUES40;
	}

	public String getCOLVALUES41() {
		return COLVALUES41;
	}

	public void setCOLVALUES41(String cOLVALUES41) {
		COLVALUES41 = cOLVALUES41;
	}

	public String getCOLVALUES42() {
		return COLVALUES42;
	}

	public void setCOLVALUES42(String cOLVALUES42) {
		COLVALUES42 = cOLVALUES42;
	}

	public String getCOLVALUES43() {
		return COLVALUES43;
	}

	public void setCOLVALUES43(String cOLVALUES43) {
		COLVALUES43 = cOLVALUES43;
	}

	public String getCOLVALUES44() {
		return COLVALUES44;
	}

	public void setCOLVALUES44(String cOLVALUES44) {
		COLVALUES44 = cOLVALUES44;
	}

	public String getCOLVALUES45() {
		return COLVALUES45;
	}

	public void setCOLVALUES45(String cOLVALUES45) {
		COLVALUES45 = cOLVALUES45;
	}

	public String getCOLVALUES46() {
		return COLVALUES46;
	}

	public void setCOLVALUES46(String cOLVALUES46) {
		COLVALUES46 = cOLVALUES46;
	}

	public String getCOLVALUES47() {
		return COLVALUES47;
	}

	public void setCOLVALUES47(String cOLVALUES47) {
		COLVALUES47 = cOLVALUES47;
	}

	public String getCOLVALUES48() {
		return COLVALUES48;
	}

	public void setCOLVALUES48(String cOLVALUES48) {
		COLVALUES48 = cOLVALUES48;
	}

	public String getCOLVALUES49() {
		return COLVALUES49;
	}

	public void setCOLVALUES49(String cOLVALUES49) {
		COLVALUES49 = cOLVALUES49;
	}

	public String getCOLVALUES50() {
		return COLVALUES50;
	}

	public void setCOLVALUES50(String cOLVALUES50) {
		COLVALUES50 = cOLVALUES50;
	}

	public String getCOLVALUES51() {
		return COLVALUES51;
	}

	public void setCOLVALUES51(String cOLVALUES51) {
		COLVALUES51 = cOLVALUES51;
	}

	public String getCOLVALUES52() {
		return COLVALUES52;
	}

	public void setCOLVALUES52(String cOLVALUES52) {
		COLVALUES52 = cOLVALUES52;
	}

	public String getCOLVALUES53() {
		return COLVALUES53;
	}

	public void setCOLVALUES53(String cOLVALUES53) {
		COLVALUES53 = cOLVALUES53;
	}

	public String getCOLVALUES54() {
		return COLVALUES54;
	}

	public void setCOLVALUES54(String cOLVALUES54) {
		COLVALUES54 = cOLVALUES54;
	}

	public String getCOLVALUES55() {
		return COLVALUES55;
	}

	public void setCOLVALUES55(String cOLVALUES55) {
		COLVALUES55 = cOLVALUES55;
	}

	public String getCOLVALUES56() {
		return COLVALUES56;
	}

	public void setCOLVALUES56(String cOLVALUES56) {
		COLVALUES56 = cOLVALUES56;
	}

	public String getCOLVALUES57() {
		return COLVALUES57;
	}

	public void setCOLVALUES57(String cOLVALUES57) {
		COLVALUES57 = cOLVALUES57;
	}

	public String getCOLVALUES58() {
		return COLVALUES58;
	}

	public void setCOLVALUES58(String cOLVALUES58) {
		COLVALUES58 = cOLVALUES58;
	}

	public String getCOLVALUES59() {
		return COLVALUES59;
	}

	public void setCOLVALUES59(String cOLVALUES59) {
		COLVALUES59 = cOLVALUES59;
	}

	public String getDRUGTYPE() {
		return DRUGTYPE;
	}

	public void setDRUGTYPE(String dRUGTYPE) {
		DRUGTYPE = dRUGTYPE;
	}

	public String getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public int getSNO() {
		return SNO;
	}

	public void setSNO(int sNO) {
		SNO = sNO;
	}

	public String getMOBILE_NO() {
		return MOBILE_NO;
	}

	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}

	public String getDISPNAME() {
		return DISPNAME;
	}

	public void setDISPNAME(String dISPNAME) {
		DISPNAME = dISPNAME;
	}

	public String getWellnesslist() {
		return wellnesslist;
	}

	public void setWellnesslist(String wellnesslist) {
		this.wellnesslist = wellnesslist;
	}

	public String getCENTERID() {
		return CENTERID;
	}

	public void setCENTERID(String cENTERID) {
		CENTERID = cENTERID;
	}

	public String getDISPID() {
		return DISPID;
	}

	public void setDISPID(String dISPID) {
		DISPID = dISPID;
	}
	public String getDRUGCODE() {
		return DRUGCODE;
	}

	public void setDRUGCODE(String dRUGCODE) {
		DRUGCODE = dRUGCODE;
	}

	public String getISSUEDATE() {
		return ISSUEDATE;
	}

	public void setISSUEDATE(String iSSUEDATE) {
		ISSUEDATE = iSSUEDATE;
	}


	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}

	public String getMndl() {
		return mndl;
	}

	public void setMndl(String mndl) {
		this.mndl = mndl;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getHospcategory() {
		return hospcategory;
	}

	public void setHospcategory(String hospcategory) {
		this.hospcategory = hospcategory;
	}

	public String getHosp() {
		return hosp;
	}

	public void setHosp(String hosp) {
		this.hosp = hosp;
	}

	public String getHospDistId() {
		return hospDistId;
	}

	public void setHospDistId(String hospDistId) {
		this.hospDistId = hospDistId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

	public String getProcedureCode() {
		return procedureCode;
	}

	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
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

	public String getGovtHospType() {
		return govtHospType;
	}

	public void setGovtHospType(String govtHospType) {
		this.govtHospType = govtHospType;
	}

	

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public String getHospDist() {
		return hospDist;
	}

	public void setHospDist(String hospDist) {
		this.hospDist = hospDist;
	}

	public String getPatDist() {
		return patDist;
	}

	public void setPatDist(String patDist) {
		this.patDist = patDist;
	}

	public String getIcdCatName() {
		return icdCatName;
	}

	public void setIcdCatName(String icdCatName) {
		this.icdCatName = icdCatName;
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

	public String getPatMandal() {
		return patMandal;
	}

	public void setPatMandal(String patMandal) {
		this.patMandal = patMandal;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getDestDate() {
		return destDate;
	}

	public void setDestDate(String destDate) {
		this.destDate = destDate;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getHospitalNames() {
		return hospitalNames;
	}

	public void setHospitalNames(String hospitalNames) {
		this.hospitalNames = hospitalNames;
	}

	public String getDistType() {
		return distType;
	}

	public void setDistType(String distType) {
		this.distType = distType;
	}

	public String getDistricts() {
		return districts;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}


	public String getHOSPDIST() {
		return HOSPDIST;
	}

	public void setHOSPDIST(String hOSPDIST) {
		HOSPDIST = hOSPDIST;
	}

	public String getHOSPDISTID() {
		return HOSPDISTID;
	}

	public void setHOSPDISTID(String hOSPDISTID) {
		HOSPDISTID = hOSPDISTID;
	}

	public String getPATMANDAL() {
		return PATMANDAL;
	}

	public void setPATMANDAL(String pATMANDAL) {
		PATMANDAL = pATMANDAL;
	}

	public String getPATMANDALID() {
		return PATMANDALID;
	}

	public void setPATMANDALID(String pATMANDALID) {
		PATMANDALID = pATMANDALID;
	}

	public String getAGEGRP() {
		return AGEGRP;
	}

	public void setAGEGRP(String aGEGRP) {
		AGEGRP = aGEGRP;
	}

	public String getMONTHWISE() {
		return MONTHWISE;
	}

	public void setMONTHWISE(String mONTHWISE) {
		MONTHWISE = mONTHWISE;
	}

	
	
	//add by varalakshmi
	
	private String HOSPDISPCODE;
	private char HOSPTYPE;
	private char  HOSPACTIVEYN;
	//private String CASEID;
	private String HOSPCITY;
	private String fromDt;
	private String toDt;
	private String hospType;
	private String stopCard;
	private Long PREAUTHAMT;
	//added by Varalakshmi.
	public char getHOSPTYPE() {
		return HOSPTYPE;
	}
	public void setHOSPTYPE(char hOSPTYPE) {
		HOSPTYPE = hOSPTYPE;
	}
	public String getHOSPCITY() {
		return HOSPCITY;
	}
	public void setHOSPCITY(String hOSPCITY) {
		HOSPCITY = hOSPCITY;
	}
	public String getFromDt() {
		return fromDt;
	}
	public void setFromDt(String fromDt) {
		this.fromDt = fromDt;
	}
	public String getToDt() {
		return toDt;
	}
	public void setToDt(String toDt) {
		this.toDt = toDt;
	}
	public String getHospType() {
		return hospType;
	}
	public void setHospType(String hospType) {
		this.hospType = hospType;
	}

	public char getHOSPACTIVEYN(){
		return HOSPACTIVEYN;
	}
	
	public void setHOSPACTIVEYN(char hOSPACTIVEYN){
		HOSPACTIVEYN=hOSPACTIVEYN;
	}
	

	public String getHOSPDISPCODE() {
		return HOSPDISPCODE;
	}

	public void setHOSPDISPCODE(String hOSPDISPCODE) {
		HOSPDISPCODE = hOSPDISPCODE;
	}

	public String getMONTH() {
		return MONTH;
	}

	public void setMONTH(String mONTH) {
		MONTH = mONTH;
	}


	public String getNON_NABH_COUNT() {
		return NON_NABH_COUNT;
	}

	public void setNON_NABH_COUNT(String nON_NABH_COUNT) {
		NON_NABH_COUNT = nON_NABH_COUNT;
	}

	public String getNON_NABH_AMOUNT() {
		return NON_NABH_AMOUNT;
	}

	public void setNON_NABH_AMOUNT(String nON_NABH_AMOUNT) {
		NON_NABH_AMOUNT = nON_NABH_AMOUNT;
	}

	public String getNABH_COUNT() {
		return NABH_COUNT;
	}

	public void setNABH_COUNT(String nABH_COUNT) {
		NABH_COUNT = nABH_COUNT;
	}

	public String getEXCLUDE_NABH_AMT() {
		return EXCLUDE_NABH_AMT;
	}

	public void setEXCLUDE_NABH_AMT(String eXCLUDE_NABH_AMT) {
		EXCLUDE_NABH_AMT = eXCLUDE_NABH_AMT;
	}

	public String getNABH_AMOUNT() {
		return NABH_AMOUNT;
	}

	public void setNABH_AMOUNT(String nABH_AMOUNT) {
		NABH_AMOUNT = nABH_AMOUNT;
	}

	public String getTOT_CLAIM_PAID_COUNT() {
		return TOT_CLAIM_PAID_COUNT;
	}

	public void setTOT_CLAIM_PAID_COUNT(String tOT_CLAIM_PAID_COUNT) {
		TOT_CLAIM_PAID_COUNT = tOT_CLAIM_PAID_COUNT;
	}

	public String getTOT_CLAIM_PAID_AMT() {
		return TOT_CLAIM_PAID_AMT;
	}

	public void setTOT_CLAIM_PAID_AMT(String tOT_CLAIM_PAID_AMT) {
		TOT_CLAIM_PAID_AMT = tOT_CLAIM_PAID_AMT;
	}

	public char getINTERVIEWFLAG1() {
		return INTERVIEWFLAG1;
	}

	public void setINTERVIEWFLAG1(char iNTERVIEWFLAG1) {
		INTERVIEWFLAG1 = iNTERVIEWFLAG1;
	}



	public String getPATTYPE() {
		return PATTYPE;
	}

	public void setPATTYPE(String pATTYPE) {
		PATTYPE = pATTYPE;
	}

	public String getBENTYPE() {
		return BENTYPE;
	}

	public void setBENTYPE(String bENTYPE) {
		BENTYPE = bENTYPE;
	}
	
	
	private String POSTDIST;
	private char GENDER;
	private String CASTE;
	private String AGEGROUP;
	private String EMP_SLF_CNT;
	private String EMP_BEN_CNT;
	private String SPEN_SELF_CNT;
	private String SPEN_BEN_CNT;
	private String FPEN_SELF_CNT;
	private String FPEN_BEN_CNT;
	private String AGEORDER;
	private String YEAR;

	public String getPOSTDIST() {
		return POSTDIST;
	}

	public void setPOSTDIST(String pOSTDIST) {
		POSTDIST = pOSTDIST;
	}

	public char getGENDER() {
		return GENDER;
	}

	public void setGENDER(char gENDER) {
		GENDER = gENDER;
	}

	public String getCASTE() {
		return CASTE;
	}

	public void setCASTE(String cASTE) {
		CASTE = cASTE;
	}

	public String getAGEGROUP() {
		return AGEGROUP;
	}

	public void setAGEGROUP(String aGEGROUP) {
		AGEGROUP = aGEGROUP;
	}

	public String getEMP_SLF_CNT() {
		return EMP_SLF_CNT;
	}

	public void setEMP_SLF_CNT(String eMP_SLF_CNT) {
		EMP_SLF_CNT = eMP_SLF_CNT;
	}

	public String getEMP_BEN_CNT() {
		return EMP_BEN_CNT;
	}

	public void setEMP_BEN_CNT(String eMP_BEN_CNT) {
		EMP_BEN_CNT = eMP_BEN_CNT;
	}

	public String getSPEN_SELF_CNT() {
		return SPEN_SELF_CNT;
	}

	public void setSPEN_SELF_CNT(String sPEN_SELF_CNT) {
		SPEN_SELF_CNT = sPEN_SELF_CNT;
	}

	public String getSPEN_BEN_CNT() {
		return SPEN_BEN_CNT;
	}

	public void setSPEN_BEN_CNT(String sPEN_BEN_CNT) {
		SPEN_BEN_CNT = sPEN_BEN_CNT;
	}

	public String getFPEN_SELF_CNT() {
		return FPEN_SELF_CNT;
	}

	public void setFPEN_SELF_CNT(String fPEN_SELF_CNT) {
		FPEN_SELF_CNT = fPEN_SELF_CNT;
	}

	public String getFPEN_BEN_CNT() {
		return FPEN_BEN_CNT;
	}

	public void setFPEN_BEN_CNT(String fPEN_BEN_CNT) {
		FPEN_BEN_CNT = fPEN_BEN_CNT;
	}

	public String getAGEORDER() {
		return AGEORDER;
	}

	public void setAGEORDER(String aGEORDER) {
		AGEORDER = aGEORDER;
	}

	public String getYEAR() {
		return YEAR;
	}

	public void setYEAR(String yEAR) {
		YEAR = yEAR;
	}

	public String getBENEFICIARYTYPE() {
		return BENEFICIARYTYPE;
	}

	public void setBENEFICIARYTYPE(String bENEFICIARYTYPE) {
		BENEFICIARYTYPE = bENEFICIARYTYPE;
	}
	
	public String getStopCard() {
		return stopCard;
	}

	public void setStopCard(String stopCard) {
		this.stopCard = stopCard;
	}

	public Long getPREAUTHAMT() {
		return PREAUTHAMT;
	}

	public void setPREAUTHAMT(Long pREAUTHAMT) {
		PREAUTHAMT = pREAUTHAMT;
	}

	public Number getNEXTVAL() {
		return NEXTVAL;
	}

	public void setNEXTVAL(Number nEXTVAL) {
		NEXTVAL = nEXTVAL;
	}

	public Number getPREAUTHAPPROVEDCUNTID() {
		return PREAUTHAPPROVEDCUNTID;
	}

	public void setPREAUTHAPPROVEDCUNTID(Number pREAUTHAPPROVEDCUNTID) {
		PREAUTHAPPROVEDCUNTID = pREAUTHAPPROVEDCUNTID;
	}

	public Number getSURGERIESDONECUNTID() {
		return SURGERIESDONECUNTID;
	}

	public void setSURGERIESDONECUNTID(Number sURGERIESDONECUNTID) {
		SURGERIESDONECUNTID = sURGERIESDONECUNTID;
	}

	public Number getNUMBEROFDEATHSID() {
		return NUMBEROFDEATHSID;
	}

	public void setNUMBEROFDEATHSID(Number nUMBEROFDEATHSID) {
		NUMBEROFDEATHSID = nUMBEROFDEATHSID;
	}

	public Number getCFRID() {
		return CFRID;
	}

	public void setCFRID(Number cFRID) {
		CFRID = cFRID;
	}

	public String getNewgen() {
		return newgen;
	}

	public void setNewgen(String newgen) {
		this.newgen = newgen;
	}
	public Number getAVGCOUNT() {
		return AVGCOUNT;
	}

	public void setAVGCOUNT(Number aVGCOUNT) {
		AVGCOUNT = aVGCOUNT;
	}

	public Number getCARDS() {
		return CARDS;
	}

	public void setCARDS(Number cARDS) {
		CARDS = cARDS;
	}

	public Number getPERSONS() {
		return PERSONS;
	}

	public void setPERSONS(Number pERSONS) {
		PERSONS = pERSONS;
	}

	public Number getCASES() {
		return CASES;
	}

	public void setCASES(Number cASES) {
		CASES = cASES;
	}

	public Number getCLRID() {
		return CLRID;
	}

	public void setCLRID(Number cLRID) {
		CLRID = cLRID;
	}

	public Number getTHERAPIESPRE() {
		return THERAPIESPRE;
	}

	public void setTHERAPIESPRE(Number tHERAPIESPRE) {
		THERAPIESPRE = tHERAPIESPRE;
	}

	public Number getTHERAPIESPREAMOUNT() {
		return THERAPIESPREAMOUNT;
	}

	public void setTHERAPIESPREAMOUNT(Number tHERAPIESPREAMOUNT) {
		THERAPIESPREAMOUNT = tHERAPIESPREAMOUNT;
	}

	public String getNHCODE() {
		return NHCODE;
	}

	public void setNHCODE(String nHCODE) {
		NHCODE = nHCODE;
	}

	public String getNHLOC() {
		return NHLOC;
	}

	public void setNHLOC(String nHLOC) {
		NHLOC = nHLOC;
	}

	public String getCOLNABH0() {
		return COLNABH0;
	}

	public void setCOLNABH0(String cOLNABH0) {
		COLNABH0 = cOLNABH0;
	}

	public String getCOLNABH1() {
		return COLNABH1;
	}

	public void setCOLNABH1(String cOLNABH1) {
		COLNABH1 = cOLNABH1;
	}

	public String getCOLNABH2() {
		return COLNABH2;
	}

	public void setCOLNABH2(String cOLNABH2) {
		COLNABH2 = cOLNABH2;
	}

	public String getCOLNABH3() {
		return COLNABH3;
	}

	public void setCOLNABH3(String cOLNABH3) {
		COLNABH3 = cOLNABH3;
	}

	public String getCOLNABH4() {
		return COLNABH4;
	}

	public void setCOLNABH4(String cOLNABH4) {
		COLNABH4 = cOLNABH4;
	}

	public String getCOLNABH5() {
		return COLNABH5;
	}

	public void setCOLNABH5(String cOLNABH5) {
		COLNABH5 = cOLNABH5;
	}

	public String getCOLNABH6() {
		return COLNABH6;
	}

	public void setCOLNABH6(String cOLNABH6) {
		COLNABH6 = cOLNABH6;
	}

	public String getCOLNABH7() {
		return COLNABH7;
	}

	public void setCOLNABH7(String cOLNABH7) {
		COLNABH7 = cOLNABH7;
	}

	public String getCOLNABH8() {
		return COLNABH8;
	}

	public void setCOLNABH8(String cOLNABH8) {
		COLNABH8 = cOLNABH8;
	}

	public String getCOLNABH9() {
		return COLNABH9;
	}

	public void setCOLNABH9(String cOLNABH9) {
		COLNABH9 = cOLNABH9;
	}

	public String getCOLNABH10() {
		return COLNABH10;
	}

	public void setCOLNABH10(String cOLNABH10) {
		COLNABH10 = cOLNABH10;
	}

	public String getCOLNABH11() {
		return COLNABH11;
	}

	public void setCOLNABH11(String cOLNABH11) {
		COLNABH11 = cOLNABH11;
	}

	public String getCOLNABH12() {
		return COLNABH12;
	}

	public void setCOLNABH12(String cOLNABH12) {
		COLNABH12 = cOLNABH12;
	}

	public String getCOLNABH13() {
		return COLNABH13;
	}

	public void setCOLNABH13(String cOLNABH13) {
		COLNABH13 = cOLNABH13;
	}

	public String getCOLNABH14() {
		return COLNABH14;
	}

	public void setCOLNABH14(String cOLNABH14) {
		COLNABH14 = cOLNABH14;
	}

	public String getCOLNABH15() {
		return COLNABH15;
	}

	public void setCOLNABH15(String cOLNABH15) {
		COLNABH15 = cOLNABH15;
	}

	public String getCOLNABH16() {
		return COLNABH16;
	}

	public void setCOLNABH16(String cOLNABH16) {
		COLNABH16 = cOLNABH16;
	}

	public String getCOLNABH17() {
		return COLNABH17;
	}

	public void setCOLNABH17(String cOLNABH17) {
		COLNABH17 = cOLNABH17;
	}

	public String getCOLNABH18() {
		return COLNABH18;
	}

	public void setCOLNABH18(String cOLNABH18) {
		COLNABH18 = cOLNABH18;
	}

	public String getCOLNABH19() {
		return COLNABH19;
	}

	public void setCOLNABH19(String cOLNABH19) {
		COLNABH19 = cOLNABH19;
	}

	public String getCOLNABH20() {
		return COLNABH20;
	}

	public void setCOLNABH20(String cOLNABH20) {
		COLNABH20 = cOLNABH20;
	}

	public String getCOLNABH21() {
		return COLNABH21;
	}

	public void setCOLNABH21(String cOLNABH21) {
		COLNABH21 = cOLNABH21;
	}

	public String getCOLNABH22() {
		return COLNABH22;
	}

	public void setCOLNABH22(String cOLNABH22) {
		COLNABH22 = cOLNABH22;
	}

	public String getCOLNABH23() {
		return COLNABH23;
	}

	public void setCOLNABH23(String cOLNABH23) {
		COLNABH23 = cOLNABH23;
	}

	public String getCOLNABH24() {
		return COLNABH24;
	}

	public void setCOLNABH24(String cOLNABH24) {
		COLNABH24 = cOLNABH24;
	}

	public String getCOLNABH25() {
		return COLNABH25;
	}

	public void setCOLNABH25(String cOLNABH25) {
		COLNABH25 = cOLNABH25;
	}

	public String getCOLNABH26() {
		return COLNABH26;
	}

	public void setCOLNABH26(String cOLNABH26) {
		COLNABH26 = cOLNABH26;
	}

	public String getCOLNABH27() {
		return COLNABH27;
	}

	public void setCOLNABH27(String cOLNABH27) {
		COLNABH27 = cOLNABH27;
	}

	public String getCOLNABH28() {
		return COLNABH28;
	}

	public void setCOLNABH28(String cOLNABH28) {
		COLNABH28 = cOLNABH28;
	}

	public String getCOLNABH29() {
		return COLNABH29;
	}

	public void setCOLNABH29(String cOLNABH29) {
		COLNABH29 = cOLNABH29;
	}

	public String getCOLNABH30() {
		return COLNABH30;
	}

	public void setCOLNABH30(String cOLNABH30) {
		COLNABH30 = cOLNABH30;
	}

	public String getCOLNABH31() {
		return COLNABH31;
	}

	public void setCOLNABH31(String cOLNABH31) {
		COLNABH31 = cOLNABH31;
	}

	public String getCOLNABH32() {
		return COLNABH32;
	}

	public void setCOLNABH32(String cOLNABH32) {
		COLNABH32 = cOLNABH32;
	}

	public String getCOLNABH33() {
		return COLNABH33;
	}

	public void setCOLNABH33(String cOLNABH33) {
		COLNABH33 = cOLNABH33;
	}

	public String getCOLNABH34() {
		return COLNABH34;
	}

	public void setCOLNABH34(String cOLNABH34) {
		COLNABH34 = cOLNABH34;
	}

	public String getCOLNABH35() {
		return COLNABH35;
	}

	public void setCOLNABH35(String cOLNABH35) {
		COLNABH35 = cOLNABH35;
	}

	public String getCOLNABH36() {
		return COLNABH36;
	}

	public void setCOLNABH36(String cOLNABH36) {
		COLNABH36 = cOLNABH36;
	}

	public String getCOLNABH37() {
		return COLNABH37;
	}

	public void setCOLNABH37(String cOLNABH37) {
		COLNABH37 = cOLNABH37;
	}

	public String getCOLNABH38() {
		return COLNABH38;
	}

	public void setCOLNABH38(String cOLNABH38) {
		COLNABH38 = cOLNABH38;
	}

	public String getCOLNABH39() {
		return COLNABH39;
	}

	public void setCOLNABH39(String cOLNABH39) {
		COLNABH39 = cOLNABH39;
	}

	public String getCOLNABH40() {
		return COLNABH40;
	}

	public void setCOLNABH40(String cOLNABH40) {
		COLNABH40 = cOLNABH40;
	}

	public String getCOLNABH41() {
		return COLNABH41;
	}

	public void setCOLNABH41(String cOLNABH41) {
		COLNABH41 = cOLNABH41;
	}

	public String getCOLNABH42() {
		return COLNABH42;
	}

	public void setCOLNABH42(String cOLNABH42) {
		COLNABH42 = cOLNABH42;
	}

	public String getCOLNABH43() {
		return COLNABH43;
	}

	public void setCOLNABH43(String cOLNABH43) {
		COLNABH43 = cOLNABH43;
	}

	public String getCOLNABH44() {
		return COLNABH44;
	}

	public void setCOLNABH44(String cOLNABH44) {
		COLNABH44 = cOLNABH44;
	}

	public String getCOLNABH45() {
		return COLNABH45;
	}

	public void setCOLNABH45(String cOLNABH45) {
		COLNABH45 = cOLNABH45;
	}

	public String getCOLNABH46() {
		return COLNABH46;
	}

	public void setCOLNABH46(String cOLNABH46) {
		COLNABH46 = cOLNABH46;
	}

	public String getCOLNABH47() {
		return COLNABH47;
	}

	public void setCOLNABH47(String cOLNABH47) {
		COLNABH47 = cOLNABH47;
	}

	public String getCOLNABH48() {
		return COLNABH48;
	}

	public void setCOLNABH48(String cOLNABH48) {
		COLNABH48 = cOLNABH48;
	}

	public String getCOLNABH49() {
		return COLNABH49;
	}

	public void setCOLNABH49(String cOLNABH49) {
		COLNABH49 = cOLNABH49;
	}

	public String getCOLNABH50() {
		return COLNABH50;
	}

	public void setCOLNABH50(String cOLNABH50) {
		COLNABH50 = cOLNABH50;
	}

	public String getCOLNABH51() {
		return COLNABH51;
	}

	public void setCOLNABH51(String cOLNABH51) {
		COLNABH51 = cOLNABH51;
	}

	public String getCOLNABH52() {
		return COLNABH52;
	}

	public void setCOLNABH52(String cOLNABH52) {
		COLNABH52 = cOLNABH52;
	}

	public String getCOLNABH53() {
		return COLNABH53;
	}

	public void setCOLNABH53(String cOLNABH53) {
		COLNABH53 = cOLNABH53;
	}

	public String getCOLNABH54() {
		return COLNABH54;
	}

	public void setCOLNABH54(String cOLNABH54) {
		COLNABH54 = cOLNABH54;
	}

	public String getCOLNABH55() {
		return COLNABH55;
	}

	public void setCOLNABH55(String cOLNABH55) {
		COLNABH55 = cOLNABH55;
	}

	public String getCOLNABH56() {
		return COLNABH56;
	}

	public void setCOLNABH56(String cOLNABH56) {
		COLNABH56 = cOLNABH56;
	}

	public String getCOLNABH57() {
		return COLNABH57;
	}

	public void setCOLNABH57(String cOLNABH57) {
		COLNABH57 = cOLNABH57;
	}

	public String getCOLNABH58() {
		return COLNABH58;
	}

	public void setCOLNABH58(String cOLNABH58) {
		COLNABH58 = cOLNABH58;
	}

	public String getCOLNABH59() {
		return COLNABH59;
	}

	public void setCOLNABH59(String cOLNABH59) {
		COLNABH59 = cOLNABH59;
	}

	public String getCOLNABH60() {
		return COLNABH60;
	}

	public void setCOLNABH60(String cOLNABH60) {
		COLNABH60 = cOLNABH60;
	}

	public String getCOLNONNABH0() {
		return COLNONNABH0;
	}

	public void setCOLNONNABH0(String cOLNONNABH0) {
		COLNONNABH0 = cOLNONNABH0;
	}

	public String getCOLNONNABH1() {
		return COLNONNABH1;
	}

	public void setCOLNONNABH1(String cOLNONNABH1) {
		COLNONNABH1 = cOLNONNABH1;
	}

	public String getCOLNONNABH2() {
		return COLNONNABH2;
	}

	public void setCOLNONNABH2(String cOLNONNABH2) {
		COLNONNABH2 = cOLNONNABH2;
	}

	public String getCOLNONNABH3() {
		return COLNONNABH3;
	}

	public void setCOLNONNABH3(String cOLNONNABH3) {
		COLNONNABH3 = cOLNONNABH3;
	}

	public String getCOLNONNABH4() {
		return COLNONNABH4;
	}

	public void setCOLNONNABH4(String cOLNONNABH4) {
		COLNONNABH4 = cOLNONNABH4;
	}

	public String getCOLNONNABH5() {
		return COLNONNABH5;
	}

	public void setCOLNONNABH5(String cOLNONNABH5) {
		COLNONNABH5 = cOLNONNABH5;
	}

	public String getCOLNONNABH6() {
		return COLNONNABH6;
	}

	public void setCOLNONNABH6(String cOLNONNABH6) {
		COLNONNABH6 = cOLNONNABH6;
	}

	public String getCOLNONNABH7() {
		return COLNONNABH7;
	}

	public void setCOLNONNABH7(String cOLNONNABH7) {
		COLNONNABH7 = cOLNONNABH7;
	}

	public String getCOLNONNABH8() {
		return COLNONNABH8;
	}

	public void setCOLNONNABH8(String cOLNONNABH8) {
		COLNONNABH8 = cOLNONNABH8;
	}

	public String getCOLNONNABH9() {
		return COLNONNABH9;
	}

	public void setCOLNONNABH9(String cOLNONNABH9) {
		COLNONNABH9 = cOLNONNABH9;
	}

	public String getCOLNONNABH10() {
		return COLNONNABH10;
	}

	public void setCOLNONNABH10(String cOLNONNABH10) {
		COLNONNABH10 = cOLNONNABH10;
	}

	public String getCOLNONNABH11() {
		return COLNONNABH11;
	}

	public void setCOLNONNABH11(String cOLNONNABH11) {
		COLNONNABH11 = cOLNONNABH11;
	}

	public String getCOLNONNABH12() {
		return COLNONNABH12;
	}

	public void setCOLNONNABH12(String cOLNONNABH12) {
		COLNONNABH12 = cOLNONNABH12;
	}

	public String getCOLNONNABH13() {
		return COLNONNABH13;
	}

	public void setCOLNONNABH13(String cOLNONNABH13) {
		COLNONNABH13 = cOLNONNABH13;
	}

	public String getCOLNONNABH14() {
		return COLNONNABH14;
	}

	public void setCOLNONNABH14(String cOLNONNABH14) {
		COLNONNABH14 = cOLNONNABH14;
	}

	public String getCOLNONNABH15() {
		return COLNONNABH15;
	}

	public void setCOLNONNABH15(String cOLNONNABH15) {
		COLNONNABH15 = cOLNONNABH15;
	}

	public String getCOLNONNABH16() {
		return COLNONNABH16;
	}

	public void setCOLNONNABH16(String cOLNONNABH16) {
		COLNONNABH16 = cOLNONNABH16;
	}

	public String getCOLNONNABH17() {
		return COLNONNABH17;
	}

	public void setCOLNONNABH17(String cOLNONNABH17) {
		COLNONNABH17 = cOLNONNABH17;
	}

	public String getCOLNONNABH18() {
		return COLNONNABH18;
	}

	public void setCOLNONNABH18(String cOLNONNABH18) {
		COLNONNABH18 = cOLNONNABH18;
	}

	public String getCOLNONNABH19() {
		return COLNONNABH19;
	}

	public void setCOLNONNABH19(String cOLNONNABH19) {
		COLNONNABH19 = cOLNONNABH19;
	}

	public String getCOLNONNABH20() {
		return COLNONNABH20;
	}

	public void setCOLNONNABH20(String cOLNONNABH20) {
		COLNONNABH20 = cOLNONNABH20;
	}

	public String getCOLNONNABH21() {
		return COLNONNABH21;
	}

	public void setCOLNONNABH21(String cOLNONNABH21) {
		COLNONNABH21 = cOLNONNABH21;
	}

	public String getCOLNONNABH22() {
		return COLNONNABH22;
	}

	public void setCOLNONNABH22(String cOLNONNABH22) {
		COLNONNABH22 = cOLNONNABH22;
	}

	public String getCOLNONNABH23() {
		return COLNONNABH23;
	}

	public void setCOLNONNABH23(String cOLNONNABH23) {
		COLNONNABH23 = cOLNONNABH23;
	}

	public String getCOLNONNABH24() {
		return COLNONNABH24;
	}

	public void setCOLNONNABH24(String cOLNONNABH24) {
		COLNONNABH24 = cOLNONNABH24;
	}

	public String getCOLNONNABH25() {
		return COLNONNABH25;
	}

	public void setCOLNONNABH25(String cOLNONNABH25) {
		COLNONNABH25 = cOLNONNABH25;
	}

	public String getCOLNONNABH26() {
		return COLNONNABH26;
	}

	public void setCOLNONNABH26(String cOLNONNABH26) {
		COLNONNABH26 = cOLNONNABH26;
	}

	public String getCOLNONNABH27() {
		return COLNONNABH27;
	}

	public void setCOLNONNABH27(String cOLNONNABH27) {
		COLNONNABH27 = cOLNONNABH27;
	}

	public String getCOLNONNABH28() {
		return COLNONNABH28;
	}

	public void setCOLNONNABH28(String cOLNONNABH28) {
		COLNONNABH28 = cOLNONNABH28;
	}

	public String getCOLNONNABH29() {
		return COLNONNABH29;
	}

	public void setCOLNONNABH29(String cOLNONNABH29) {
		COLNONNABH29 = cOLNONNABH29;
	}

	public String getCOLNONNABH30() {
		return COLNONNABH30;
	}

	public void setCOLNONNABH30(String cOLNONNABH30) {
		COLNONNABH30 = cOLNONNABH30;
	}

	public String getCOLNONNABH31() {
		return COLNONNABH31;
	}

	public void setCOLNONNABH31(String cOLNONNABH31) {
		COLNONNABH31 = cOLNONNABH31;
	}

	public String getCOLNONNABH32() {
		return COLNONNABH32;
	}

	public void setCOLNONNABH32(String cOLNONNABH32) {
		COLNONNABH32 = cOLNONNABH32;
	}

	public String getCOLNONNABH33() {
		return COLNONNABH33;
	}

	public void setCOLNONNABH33(String cOLNONNABH33) {
		COLNONNABH33 = cOLNONNABH33;
	}

	public String getCOLNONNABH34() {
		return COLNONNABH34;
	}

	public void setCOLNONNABH34(String cOLNONNABH34) {
		COLNONNABH34 = cOLNONNABH34;
	}

	public String getCOLNONNABH35() {
		return COLNONNABH35;
	}

	public void setCOLNONNABH35(String cOLNONNABH35) {
		COLNONNABH35 = cOLNONNABH35;
	}

	public String getCOLNONNABH36() {
		return COLNONNABH36;
	}

	public void setCOLNONNABH36(String cOLNONNABH36) {
		COLNONNABH36 = cOLNONNABH36;
	}

	public String getCOLNONNABH37() {
		return COLNONNABH37;
	}

	public void setCOLNONNABH37(String cOLNONNABH37) {
		COLNONNABH37 = cOLNONNABH37;
	}

	public String getCOLNONNABH38() {
		return COLNONNABH38;
	}

	public void setCOLNONNABH38(String cOLNONNABH38) {
		COLNONNABH38 = cOLNONNABH38;
	}

	public String getCOLNONNABH39() {
		return COLNONNABH39;
	}

	public void setCOLNONNABH39(String cOLNONNABH39) {
		COLNONNABH39 = cOLNONNABH39;
	}

	public String getCOLNONNABH40() {
		return COLNONNABH40;
	}

	public void setCOLNONNABH40(String cOLNONNABH40) {
		COLNONNABH40 = cOLNONNABH40;
	}

	public String getCOLNONNABH41() {
		return COLNONNABH41;
	}

	public void setCOLNONNABH41(String cOLNONNABH41) {
		COLNONNABH41 = cOLNONNABH41;
	}

	public String getCOLNONNABH42() {
		return COLNONNABH42;
	}

	public void setCOLNONNABH42(String cOLNONNABH42) {
		COLNONNABH42 = cOLNONNABH42;
	}

	public String getCOLNONNABH43() {
		return COLNONNABH43;
	}

	public void setCOLNONNABH43(String cOLNONNABH43) {
		COLNONNABH43 = cOLNONNABH43;
	}

	public String getCOLNONNABH44() {
		return COLNONNABH44;
	}

	public void setCOLNONNABH44(String cOLNONNABH44) {
		COLNONNABH44 = cOLNONNABH44;
	}

	public String getCOLNONNABH45() {
		return COLNONNABH45;
	}

	public void setCOLNONNABH45(String cOLNONNABH45) {
		COLNONNABH45 = cOLNONNABH45;
	}

	public String getCOLNONNABH46() {
		return COLNONNABH46;
	}

	public void setCOLNONNABH46(String cOLNONNABH46) {
		COLNONNABH46 = cOLNONNABH46;
	}

	public String getCOLNONNABH47() {
		return COLNONNABH47;
	}

	public void setCOLNONNABH47(String cOLNONNABH47) {
		COLNONNABH47 = cOLNONNABH47;
	}

	public String getCOLNONNABH48() {
		return COLNONNABH48;
	}

	public void setCOLNONNABH48(String cOLNONNABH48) {
		COLNONNABH48 = cOLNONNABH48;
	}

	public String getCOLNONNABH49() {
		return COLNONNABH49;
	}

	public void setCOLNONNABH49(String cOLNONNABH49) {
		COLNONNABH49 = cOLNONNABH49;
	}

	public String getCOLNONNABH50() {
		return COLNONNABH50;
	}

	public void setCOLNONNABH50(String cOLNONNABH50) {
		COLNONNABH50 = cOLNONNABH50;
	}

	public String getCOLNONNABH51() {
		return COLNONNABH51;
	}

	public void setCOLNONNABH51(String cOLNONNABH51) {
		COLNONNABH51 = cOLNONNABH51;
	}

	public String getCOLNONNABH52() {
		return COLNONNABH52;
	}

	public void setCOLNONNABH52(String cOLNONNABH52) {
		COLNONNABH52 = cOLNONNABH52;
	}

	public String getCOLNONNABH53() {
		return COLNONNABH53;
	}

	public void setCOLNONNABH53(String cOLNONNABH53) {
		COLNONNABH53 = cOLNONNABH53;
	}

	public String getCOLNONNABH54() {
		return COLNONNABH54;
	}

	public void setCOLNONNABH54(String cOLNONNABH54) {
		COLNONNABH54 = cOLNONNABH54;
	}

	public String getCOLNONNABH55() {
		return COLNONNABH55;
	}

	public void setCOLNONNABH55(String cOLNONNABH55) {
		COLNONNABH55 = cOLNONNABH55;
	}

	public String getCOLNONNABH56() {
		return COLNONNABH56;
	}

	public void setCOLNONNABH56(String cOLNONNABH56) {
		COLNONNABH56 = cOLNONNABH56;
	}

	public String getCOLNONNABH57() {
		return COLNONNABH57;
	}

	public void setCOLNONNABH57(String cOLNONNABH57) {
		COLNONNABH57 = cOLNONNABH57;
	}

	public String getCOLNONNABH58() {
		return COLNONNABH58;
	}

	public void setCOLNONNABH58(String cOLNONNABH58) {
		COLNONNABH58 = cOLNONNABH58;
	}

	public String getCOLNONNABH59() {
		return COLNONNABH59;
	}

	public void setCOLNONNABH59(String cOLNONNABH59) {
		COLNONNABH59 = cOLNONNABH59;
	}

	public String getCOLNONNABH60() {
		return COLNONNABH60;
	}

	public void setCOLNONNABH60(String cOLNONNABH60) {
		COLNONNABH60 = cOLNONNABH60;
	}

	public String getCOLNABHTOT0() {
		return COLNABHTOT0;
	}

	public void setCOLNABHTOT0(String cOLNABHTOT0) {
		COLNABHTOT0 = cOLNABHTOT0;
	}

	public String getCOLNABHTOT1() {
		return COLNABHTOT1;
	}

	public void setCOLNABHTOT1(String cOLNABHTOT1) {
		COLNABHTOT1 = cOLNABHTOT1;
	}

	public String getCOLNABHTOT2() {
		return COLNABHTOT2;
	}

	public void setCOLNABHTOT2(String cOLNABHTOT2) {
		COLNABHTOT2 = cOLNABHTOT2;
	}

	public String getCOLNABHTOT3() {
		return COLNABHTOT3;
	}

	public void setCOLNABHTOT3(String cOLNABHTOT3) {
		COLNABHTOT3 = cOLNABHTOT3;
	}

	public String getCOLNABHTOT4() {
		return COLNABHTOT4;
	}

	public void setCOLNABHTOT4(String cOLNABHTOT4) {
		COLNABHTOT4 = cOLNABHTOT4;
	}

	public String getCOLNABHTOT5() {
		return COLNABHTOT5;
	}

	public void setCOLNABHTOT5(String cOLNABHTOT5) {
		COLNABHTOT5 = cOLNABHTOT5;
	}

	public String getCOLNABHTOT6() {
		return COLNABHTOT6;
	}

	public void setCOLNABHTOT6(String cOLNABHTOT6) {
		COLNABHTOT6 = cOLNABHTOT6;
	}

	public String getCOLNABHTOT7() {
		return COLNABHTOT7;
	}

	public void setCOLNABHTOT7(String cOLNABHTOT7) {
		COLNABHTOT7 = cOLNABHTOT7;
	}

	public String getCOLNABHTOT8() {
		return COLNABHTOT8;
	}

	public void setCOLNABHTOT8(String cOLNABHTOT8) {
		COLNABHTOT8 = cOLNABHTOT8;
	}

	public String getCOLNABHTOT9() {
		return COLNABHTOT9;
	}

	public void setCOLNABHTOT9(String cOLNABHTOT9) {
		COLNABHTOT9 = cOLNABHTOT9;
	}

	public String getCOLNABHTOT10() {
		return COLNABHTOT10;
	}

	public void setCOLNABHTOT10(String cOLNABHTOT10) {
		COLNABHTOT10 = cOLNABHTOT10;
	}

	public String getCOLNABHTOT11() {
		return COLNABHTOT11;
	}

	public void setCOLNABHTOT11(String cOLNABHTOT11) {
		COLNABHTOT11 = cOLNABHTOT11;
	}

	public String getCOLNABHTOT12() {
		return COLNABHTOT12;
	}

	public void setCOLNABHTOT12(String cOLNABHTOT12) {
		COLNABHTOT12 = cOLNABHTOT12;
	}

	public String getCOLNONNABHTOT0() {
		return COLNONNABHTOT0;
	}

	public void setCOLNONNABHTOT0(String cOLNONNABHTOT0) {
		COLNONNABHTOT0 = cOLNONNABHTOT0;
	}

	public String getCOLNONNABHTOT1() {
		return COLNONNABHTOT1;
	}

	public void setCOLNONNABHTOT1(String cOLNONNABHTOT1) {
		COLNONNABHTOT1 = cOLNONNABHTOT1;
	}

	public String getCOLNONNABHTOT2() {
		return COLNONNABHTOT2;
	}

	public void setCOLNONNABHTOT2(String cOLNONNABHTOT2) {
		COLNONNABHTOT2 = cOLNONNABHTOT2;
	}

	public String getCOLNONNABHTOT3() {
		return COLNONNABHTOT3;
	}

	public void setCOLNONNABHTOT3(String cOLNONNABHTOT3) {
		COLNONNABHTOT3 = cOLNONNABHTOT3;
	}

	public String getCOLNONNABHTOT4() {
		return COLNONNABHTOT4;
	}

	public void setCOLNONNABHTOT4(String cOLNONNABHTOT4) {
		COLNONNABHTOT4 = cOLNONNABHTOT4;
	}

	public String getCOLNONNABHTOT5() {
		return COLNONNABHTOT5;
	}

	public void setCOLNONNABHTOT5(String cOLNONNABHTOT5) {
		COLNONNABHTOT5 = cOLNONNABHTOT5;
	}

	public String getCOLNONNABHTOT6() {
		return COLNONNABHTOT6;
	}

	public void setCOLNONNABHTOT6(String cOLNONNABHTOT6) {
		COLNONNABHTOT6 = cOLNONNABHTOT6;
	}

	public String getCOLNONNABHTOT7() {
		return COLNONNABHTOT7;
	}

	public void setCOLNONNABHTOT7(String cOLNONNABHTOT7) {
		COLNONNABHTOT7 = cOLNONNABHTOT7;
	}

	public String getCOLNONNABHTOT8() {
		return COLNONNABHTOT8;
	}

	public void setCOLNONNABHTOT8(String cOLNONNABHTOT8) {
		COLNONNABHTOT8 = cOLNONNABHTOT8;
	}

	public String getCOLNONNABHTOT9() {
		return COLNONNABHTOT9;
	}

	public void setCOLNONNABHTOT9(String cOLNONNABHTOT9) {
		COLNONNABHTOT9 = cOLNONNABHTOT9;
	}

	public String getCOLNONNABHTOT10() {
		return COLNONNABHTOT10;
	}

	public void setCOLNONNABHTOT10(String cOLNONNABHTOT10) {
		COLNONNABHTOT10 = cOLNONNABHTOT10;
	}

	public String getCOLNONNABHTOT11() {
		return COLNONNABHTOT11;
	}

	public void setCOLNONNABHTOT11(String cOLNONNABHTOT11) {
		COLNONNABHTOT11 = cOLNONNABHTOT11;
	}

	public String getCOLNONNABHTOT12() {
		return COLNONNABHTOT12;
	}

	public void setCOLNONNABHTOT12(String cOLNONNABHTOT12) {
		COLNONNABHTOT12 = cOLNONNABHTOT12;
	}

	public String getNABHHOSP() {
		return NABHHOSP;
	}

	public void setNABHHOSP(String nABHHOSP) {
		NABHHOSP = nABHHOSP;
	}

	public String getAgewise() {
		return agewise;
	}

	public void setAgewise(String agewise) {
		this.agewise = agewise;
	}

	public String getDistrictdd() {
		return districtdd;
	}

	public void setDistrictdd(String districtdd) {
		this.districtdd = districtdd;
	}


	public String getEMPLOYEE() {
		return EMPLOYEE;
	}

	public String getWCNAME() {
		return WCNAME;
	}

	public void setWCNAME(String wCNAME) {
		WCNAME = wCNAME;
	}

	public String getDIFFTIME() {
		return DIFFTIME;
	}

	public void setDIFFTIME(String dIFFTIME) {
		DIFFTIME = dIFFTIME;
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

	public Number getREGISTRATION_NAME() {
		return REGISTRATION_NAME;
	}

	public void setREGISTRATION_NAME(Number rEGISTRATION_NAME) {
		REGISTRATION_NAME = rEGISTRATION_NAME;
	}

	public Number getINVESTIGATION_NAMES() {
		return INVESTIGATION_NAMES;
	}

	public void setINVESTIGATION_NAMES(Number iNVESTIGATION_NAMES) {
		INVESTIGATION_NAMES = iNVESTIGATION_NAMES;
	}

	public Number getDRUGNAMES() {
		return DRUGNAMES;
	}

	public void setDRUGNAMES(Number dRUGNAMES) {
		DRUGNAMES = dRUGNAMES;
	}

	public String getPRESC_QUANTITY() {
		return PRESC_QUANTITY;
	}

	public void setPRESC_QUANTITY(String pRESC_QUANTITY) {
		PRESC_QUANTITY = pRESC_QUANTITY;
	}
	public Number getCOUNT_NO() {
		return COUNT_NO;
	}

	public void setCOUNT_NO(Number cOUNT_NO) {
		COUNT_NO = cOUNT_NO;
	}

	public Number getCOUNT_NO1() {
		return COUNT_NO1;
	}

	public void setCOUNT_NO1(Number cOUNT_NO1) {
		COUNT_NO1 = cOUNT_NO1;
	}

	public Number getCOUNT_NO2() {
		return COUNT_NO2;
	}

	public void setCOUNT_NO2(Number cOUNT_NO2) {
		COUNT_NO2 = cOUNT_NO2;
	}

	public String getRELATION() {
		return RELATION;
	}

	public void setRELATION(String rELATION) {
		RELATION = rELATION;
	}

	public String getSELFDEPENDENT() {
		return SELFDEPENDENT;
	}

	public void setSELFDEPENDENT(String sELFDEPENDENT) {
		SELFDEPENDENT = sELFDEPENDENT;
	}

	public String getDESIGNATION() {
		return DESIGNATION;
	}

	public void setDESIGNATION(String dESIGNATION) {
		DESIGNATION = dESIGNATION;
	}

	public String getDEPARTMENT() {
		return DEPARTMENT;
	}

	public void setDEPARTMENT(String dEPARTMENT) {
		DEPARTMENT = dEPARTMENT;
	}

	public String getCASEREGDATE() {
		return CASEREGDATE;
	}

	public void setCASEREGDATE(String cASEREGDATE) {
		CASEREGDATE = cASEREGDATE;
	}

	public String getGENDER1() {
		return GENDER1;
	}

	public void setGENDER1(String gENDER1) {
		GENDER1 = gENDER1;
	}

	public Number getDRUG_ZERO() {
		return DRUG_ZERO;
	}

	public void setDRUG_ZERO(Number dRUG_ZERO) {
		DRUG_ZERO = dRUG_ZERO;
	}

	public String getTOTALQUANTITY() {
		return TOTALQUANTITY;
	}

	public void setTOTALQUANTITY(String tOTALQUANTITY) {
		TOTALQUANTITY = tOTALQUANTITY;
	}

	public String getINVOICE_NUM() {
		return INVOICE_NUM;
	}

	public void setINVOICE_NUM(String iNVOICE_NUM) {
		INVOICE_NUM = iNVOICE_NUM;
	}

	public String getUNIT_PRICE() {
		return UNIT_PRICE;
	}

	public void setUNIT_PRICE(String uNIT_PRICE) {
		UNIT_PRICE = uNIT_PRICE;
	}

	public String getCASENO() {
		return CASENO;
	}

	public void setCASENO(String cASENO) {
		CASENO = cASENO;
	}

	public String getCLAIMSUB() {
		return CLAIMSUB;
	}

	public void setCLAIMSUB(String cLAIMSUB) {
		CLAIMSUB = cLAIMSUB;
	}
	
	private String DRUG_TYPE;
	private String DRUG_NAME;
	private Number OPENING_BALANCE;
	private Number RECEIVED_STOCK;
	private Number DISTRIBUTION_STOCK;
	private Number CLOSING_BALANCE;

	public String getDRUG_TYPE() {
		return DRUG_TYPE;
	}

	public void setDRUG_TYPE(String dRUG_TYPE) {
		DRUG_TYPE = dRUG_TYPE;
	}

	public String getDRUG_NAME() {
		return DRUG_NAME;
	}

	public void setDRUG_NAME(String dRUG_NAME) {
		DRUG_NAME = dRUG_NAME;
	}

	public Number getOPENING_BALANCE() {
		return OPENING_BALANCE;
	}

	public void setOPENING_BALANCE(Number oPENING_BALANCE) {
		OPENING_BALANCE = oPENING_BALANCE;
	}

	public Number getRECEIVED_STOCK() {
		return RECEIVED_STOCK;
	}

	public void setRECEIVED_STOCK(Number rECEIVED_STOCK) {
		RECEIVED_STOCK = rECEIVED_STOCK;
	}

	public Number getDISTRIBUTION_STOCK() {
		return DISTRIBUTION_STOCK;
	}

	public void setDISTRIBUTION_STOCK(Number dISTRIBUTION_STOCK) {
		DISTRIBUTION_STOCK = dISTRIBUTION_STOCK;
	}

	public Number getCLOSING_BALANCE() {
		return CLOSING_BALANCE;
	}

	public void setCLOSING_BALANCE(Number cLOSING_BALANCE) {
		CLOSING_BALANCE = cLOSING_BALANCE;
	}
	
	   private String  DISP_NAME;

	public String getDISP_NAME() {
		return DISP_NAME;
	}

	public void setDISP_NAME(String dISP_NAME) {
		DISP_NAME = dISP_NAME;
	}
	
	private String ISSUE_QUANT;
	private String RCVD_QUANT;
	private String DRUG_TYPE_ID;

	public String getISSUE_QUANT() {
		return ISSUE_QUANT;
	}

	public void setISSUE_QUANT(String iSSUE_QUANT) {
		ISSUE_QUANT = iSSUE_QUANT;
	}

	public String getRCVD_QUANT() {
		return RCVD_QUANT;
	}

	public void setRCVD_QUANT(String rCVD_QUANT) {
		RCVD_QUANT = rCVD_QUANT;
	}

	public String getDRUG_TYPE_ID() {
		return DRUG_TYPE_ID;
	}

	public void setDRUG_TYPE_ID(String dRUG_TYPE_ID) {
		DRUG_TYPE_ID = dRUG_TYPE_ID;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}
	private String RETURN_DATE;
	private String INV_NUM;
	private String INV_DT;
	private String RETURN_INV_NUM;
	private String RETURN_INV_DT;
	private String CRT_DATE;
	private Number AMOUNT;

	public String getRETURN_DATE() {
		return RETURN_DATE;
	}

	public void setRETURN_DATE(String rETURN_DATE) {
		RETURN_DATE = rETURN_DATE;
	}

	public String getINV_NUM() {
		return INV_NUM;
	}

	public void setINV_NUM(String iNV_NUM) {
		INV_NUM = iNV_NUM;
	}

	public String getINV_DT() {
		return INV_DT;
	}

	public void setINV_DT(String iNV_DT) {
		INV_DT = iNV_DT;
	}

	public String getRETURN_INV_NUM() {
		return RETURN_INV_NUM;
	}

	public void setRETURN_INV_NUM(String rETURN_INV_NUM) {
		RETURN_INV_NUM = rETURN_INV_NUM;
	}

	public String getRETURN_INV_DT() {
		return RETURN_INV_DT;
	}

	public void setRETURN_INV_DT(String rETURN_INV_DT) {
		RETURN_INV_DT = rETURN_INV_DT;
	}

	public String getCRT_DATE() {
		return CRT_DATE;
	}

	public void setCRT_DATE(String cRT_DATE) {
		CRT_DATE = cRT_DATE;
	}

	public Number getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(Number aMOUNT) {
		AMOUNT = aMOUNT;
	}
	
	private String AVG_STK;
	private String REQ;
	private String AVL;
	private String STOCK;
	private String DRUG_ID;
	private String DRUG_TYPE_NAME1;
	private Number TOTAL_DRUG_QTY;
	
	
	public Number getTOTAL_DRUG_QTY() {
		return TOTAL_DRUG_QTY;
	}

	public void setTOTAL_DRUG_QTY(Number tOTAL_DRUG_QTY) {
		if (tOTAL_DRUG_QTY == null) {
			tOTAL_DRUG_QTY = 0;
		}
		this.TOTAL_DRUG_QTY = tOTAL_DRUG_QTY != null ? tOTAL_DRUG_QTY.longValue() : null;
	}

	public String getDRUG_TYPE_NAME1() {
		return DRUG_TYPE_NAME;
	}

	public void setDRUG_TYPE_NAME1(String dRUG_TYPE_NAME) {
		DRUG_TYPE_NAME = dRUG_TYPE_NAME;
	}

	public String getAVG_STK() {
		return AVG_STK;
	}
	public void setAVG_STK(String aVG_STK) {
		AVG_STK = aVG_STK;
	}
	public String getREQ() {
		return REQ;
	}
	public void setREQ(String rEQ) {
		REQ = rEQ;
	}
	public String getAVL() {
		return AVL;
	}
	public void setAVL(String aVL) {
		AVL = aVL;
	}
	public String getSTOCK() {
		return STOCK;
	}
	public void setSTOCK(String sTOCK) {
		STOCK = sTOCK;
	}
	public String getDRUG_ID() {
		return DRUG_ID;
	}
	public void setDRUG_ID(String dRUG_ID) {
		DRUG_ID = dRUG_ID;
	}
	
	private String OPENING_BALANCE_EXP;
	private String DISTRIBUTION_STOCK_EXP;
	private String CLOSING_BALANCE_EXP;
	public String getOPENING_BALANCE_EXP() {
		return OPENING_BALANCE_EXP;
	}
	public void setOPENING_BALANCE_EXP(String oPENING_BALANCE_EXP) {
		OPENING_BALANCE_EXP = oPENING_BALANCE_EXP;
	}
	public String getDISTRIBUTION_STOCK_EXP() {
		return DISTRIBUTION_STOCK_EXP;
	}
	public void setDISTRIBUTION_STOCK_EXP(String dISTRIBUTION_STOCK_EXP) {
		DISTRIBUTION_STOCK_EXP = dISTRIBUTION_STOCK_EXP;
	}
	public String getCLOSING_BALANCE_EXP() {
		return CLOSING_BALANCE_EXP;
	}
	public void setCLOSING_BALANCE_EXP(String cLOSING_BALANCE_EXP) {
		CLOSING_BALANCE_EXP = cLOSING_BALANCE_EXP;
	}

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getCONTRACT_YEAR() {
		return CONTRACT_YEAR;
	}

	public void setCONTRACT_YEAR(String cONTRACT_YEAR) {
		CONTRACT_YEAR = cONTRACT_YEAR;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}

	public String getSTOCKPRICE() {
		return STOCKPRICE;
	}

	public void setSTOCKPRICE(String sTOCKPRICE) {
		STOCKPRICE = sTOCKPRICE;
	}

	public String getTOT_STOCKPRICE() {
		return TOT_STOCKPRICE;
	}

	public void setTOT_STOCKPRICE(String tOT_STOCKPRICE) {
		TOT_STOCKPRICE = tOT_STOCKPRICE;
	}

	public String getIND_NOTREC() {
		return IND_NOTREC;
	}

	public void setIND_NOTREC(String iND_NOTREC) {
		IND_NOTREC = iND_NOTREC;
	}

	public String getNAT_STOCK() {
		return NAT_STOCK;
	}

	public void setNAT_STOCK(String nAT_STOCK) {
		NAT_STOCK = nAT_STOCK;
	}

	public String getNAT_DEF() {
		return NAT_DEF;
	}

	public void setNAT_DEF(String nAT_DEF) {
		NAT_DEF = nAT_DEF;
	}

	public String getIND_VAL() {
		return IND_VAL;
	}

	public void setIND_VAL(String iND_VAL) {
		IND_VAL = iND_VAL;
	}
	public Number getTOT_NOF_PATREG() {
		return TOT_NOF_PATREG;
	}

	public void setTOT_NOF_PATREG(Number tOT_NOF_PATREG) {
		TOT_NOF_PATREG = tOT_NOF_PATREG;
	}



	public String getPATIENTNAME() {
		return PATIENTNAME;
	}

	public void setPATIENTNAME(String pATIENTNAME) {
		PATIENTNAME = pATIENTNAME;
	}

	public String getDISPNAME1() {
		return DISPNAME1;
	}

	public void setDISPNAME1(String dISPNAME1) {
		DISPNAME1 = dISPNAME1;
	}

	public Number getNOF_OTPEX_APPRV() {
		return NOF_OTPEX_APPRV;
	}

	public void setNOF_OTPEX_APPRV(Number nOF_OTPEX_APPRV) {
		NOF_OTPEX_APPRV = nOF_OTPEX_APPRV;
	}

	public Number getNOF_OTPEX_PENDING() {
		return NOF_OTPEX_PENDING;
	}

	public void setNOF_OTPEX_PENDING(Number nOF_OTPEX_PENDING) {
		NOF_OTPEX_PENDING = nOF_OTPEX_PENDING;
	}

	public Number getADILABAD_PRSC_QTY() {
		return ADILABAD_PRSC_QTY;
	}

	public void setADILABAD_PRSC_QTY(Number aDILABAD_PRSC_QTY) {
		ADILABAD_PRSC_QTY = aDILABAD_PRSC_QTY;
	}

	public Number getADILABAD_ISS_QTY() {
		return ADILABAD_ISS_QTY;
	}

	public void setADILABAD_ISS_QTY(Number aDILABAD_ISS_QTY) {
		ADILABAD_ISS_QTY = aDILABAD_ISS_QTY;
	}

	public Number getKARIMNAGAR_PRSC_QTY() {
		return KARIMNAGAR_PRSC_QTY;
	}

	public void setKARIMNAGAR_PRSC_QTY(Number kARIMNAGAR_PRSC_QTY) {
		KARIMNAGAR_PRSC_QTY = kARIMNAGAR_PRSC_QTY;
	}

	public Number getKARIMNAGAR_ISS_QTY() {
		return KARIMNAGAR_ISS_QTY;
	}

	public void setKARIMNAGAR_ISS_QTY(Number kARIMNAGAR_ISS_QTY) {
		KARIMNAGAR_ISS_QTY = kARIMNAGAR_ISS_QTY;
	}

	public Number getKHAIRATABAD_PRSC_QTY() {
		return KHAIRATABAD_PRSC_QTY;
	}

	public void setKHAIRATABAD_PRSC_QTY(Number kHAIRATABAD_PRSC_QTY) {
		KHAIRATABAD_PRSC_QTY = kHAIRATABAD_PRSC_QTY;
	}

	public Number getKHAIRATABAD_ISS_QTY() {
		return KHAIRATABAD_ISS_QTY;
	}

	public void setKHAIRATABAD_ISS_QTY(Number kHAIRATABAD_ISS_QTY) {
		KHAIRATABAD_ISS_QTY = kHAIRATABAD_ISS_QTY;
	}

	public Number getKHAMMAM_PRSC_QTY() {
		return KHAMMAM_PRSC_QTY;
	}

	public void setKHAMMAM_PRSC_QTY(Number kHAMMAM_PRSC_QTY) {
		KHAMMAM_PRSC_QTY = kHAMMAM_PRSC_QTY;
	}

	public Number getKHAMMAM_ISS_QTY() {
		return KHAMMAM_ISS_QTY;
	}

	public void setKHAMMAM_ISS_QTY(Number kHAMMAM_ISS_QTY) {
		KHAMMAM_ISS_QTY = kHAMMAM_ISS_QTY;
	}

	public Number getKUKATPALLY_PRSC_QTY() {
		return KUKATPALLY_PRSC_QTY;
	}

	public void setKUKATPALLY_PRSC_QTY(Number kUKATPALLY_PRSC_QTY) {
		KUKATPALLY_PRSC_QTY = kUKATPALLY_PRSC_QTY;
	}

	public Number getKUKATPALLY_ISS_QTY() {
		return KUKATPALLY_ISS_QTY;
	}

	public void setKUKATPALLY_ISS_QTY(Number kUKATPALLY_ISS_QTY) {
		KUKATPALLY_ISS_QTY = kUKATPALLY_ISS_QTY;
	}

	public Number getMAHABUBNAGAR_PRSC_QTY() {
		return MAHABUBNAGAR_PRSC_QTY;
	}

	public void setMAHABUBNAGAR_PRSC_QTY(Number mAHABUBNAGAR_PRSC_QTY) {
		MAHABUBNAGAR_PRSC_QTY = mAHABUBNAGAR_PRSC_QTY;
	}

	public Number getMAHABUBNAGAR_ISS_QTY() {
		return MAHABUBNAGAR_ISS_QTY;
	}

	public void setMAHABUBNAGAR_ISS_QTY(Number mAHABUBNAGAR_ISS_QTY) {
		MAHABUBNAGAR_ISS_QTY = mAHABUBNAGAR_ISS_QTY;
	}

	public Number getNALGONDA_PRSC_QTY() {
		return NALGONDA_PRSC_QTY;
	}

	public void setNALGONDA_PRSC_QTY(Number nALGONDA_PRSC_QTY) {
		NALGONDA_PRSC_QTY = nALGONDA_PRSC_QTY;
	}

	public Number getNALGONDA_ISS_QTY() {
		return NALGONDA_ISS_QTY;
	}

	public void setNALGONDA_ISS_QTY(Number nALGONDA_ISS_QTY) {
		NALGONDA_ISS_QTY = nALGONDA_ISS_QTY;
	}

	public Number getNIZAMBAD_PRSC_QTY() {
		return NIZAMBAD_PRSC_QTY;
	}

	public void setNIZAMBAD_PRSC_QTY(Number nIZAMBAD_PRSC_QTY) {
		NIZAMBAD_PRSC_QTY = nIZAMBAD_PRSC_QTY;
	}

	public Number getNIZAMBAD_ISS_QTY() {
		return NIZAMBAD_ISS_QTY;
	}

	public void setNIZAMBAD_ISS_QTY(Number nIZAMBAD_ISS_QTY) {
		NIZAMBAD_ISS_QTY = nIZAMBAD_ISS_QTY;
	}

	public Number getSANGAREDDY_PRSC_QTY() {
		return SANGAREDDY_PRSC_QTY;
	}

	public void setSANGAREDDY_PRSC_QTY(Number sANGAREDDY_PRSC_QTY) {
		SANGAREDDY_PRSC_QTY = sANGAREDDY_PRSC_QTY;
	}

	public Number getSANGAREDDY_ISS_QTY() {
		return SANGAREDDY_ISS_QTY;
	}

	public void setSANGAREDDY_ISS_QTY(Number sANGAREDDY_ISS_QTY) {
		SANGAREDDY_ISS_QTY = sANGAREDDY_ISS_QTY;
	}

	public Number getSIDDIPET_PRSC_QTY() {
		return SIDDIPET_PRSC_QTY;
	}

	public void setSIDDIPET_PRSC_QTY(Number sIDDIPET_PRSC_QTY) {
		SIDDIPET_PRSC_QTY = sIDDIPET_PRSC_QTY;
	}

	public Number getSIDDIPET_ISS_QTY() {
		return SIDDIPET_ISS_QTY;
	}

	public void setSIDDIPET_ISS_QTY(Number sIDDIPET_ISS_QTY) {
		SIDDIPET_ISS_QTY = sIDDIPET_ISS_QTY;
	}

	public Number getVANASTHALIPURAM_PRSC_QTY() {
		return VANASTHALIPURAM_PRSC_QTY;
	}

	public void setVANASTHALIPURAM_PRSC_QTY(Number vANASTHALIPURAM_PRSC_QTY) {
		VANASTHALIPURAM_PRSC_QTY = vANASTHALIPURAM_PRSC_QTY;
	}

	public Number getVANASTHALIPURAM_ISS_QTY() {
		return VANASTHALIPURAM_ISS_QTY;
	}

	public void setVANASTHALIPURAM_ISS_QTY(Number vANASTHALIPURAM_ISS_QTY) {
		VANASTHALIPURAM_ISS_QTY = vANASTHALIPURAM_ISS_QTY;
	}

	public Number getWARANGAL_PRSC_QTY() {
		return WARANGAL_PRSC_QTY;
	}

	public void setWARANGAL_PRSC_QTY(Number wARANGAL_PRSC_QTY) {
		WARANGAL_PRSC_QTY = wARANGAL_PRSC_QTY;
	}

	public Number getWARANGAL_ISS_QTY() {
		return WARANGAL_ISS_QTY;
	}

	public void setWARANGAL_ISS_QTY(Number wARANGAL_ISS_QTY) {
		WARANGAL_ISS_QTY = wARANGAL_ISS_QTY;
	}

	public Number getTOT_PRSC_DRUG_QTY() {
		return TOT_PRSC_DRUG_QTY;
	}

	public void setTOT_PRSC_DRUG_QTY(Number tOT_PRSC_DRUG_QTY) {
		TOT_PRSC_DRUG_QTY = tOT_PRSC_DRUG_QTY;
	}

	public Number getTOT_ISS_DRUG_QTY() {
		return TOT_ISS_DRUG_QTY;
	}

	public void setTOT_ISS_DRUG_QTY(Number tOT_ISS_DRUG_QTY) {
		TOT_ISS_DRUG_QTY = tOT_ISS_DRUG_QTY;
	}

	public String getDRUG_TYPE_NAME() {
		return DRUG_TYPE_NAME;
	}

	public void setDRUG_TYPE_NAME(String dRUG_TYPE_NAME) {
		DRUG_TYPE_NAME = dRUG_TYPE_NAME;
	}

	public String getMNFCTR_ID() {
		return MNFCTR_ID;
	}

	public void setMNFCTR_ID(String mNFCTR_ID) {
		MNFCTR_ID = mNFCTR_ID;
	}

	public String getDSTRBTR_ID() {
		return DSTRBTR_ID;
	}

	public void setDSTRBTR_ID(String dSTRBTR_ID) {
		DSTRBTR_ID = dSTRBTR_ID;
	}

	public String getDISP_ID() {
		return DISP_ID;
	}

	public void setDISP_ID(String dISP_ID) {
		DISP_ID = dISP_ID;
	}
	
}
