package com.ahct.common.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;

public class LabelBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private String ID;
	private String VALUE;
	private Date DOB;
	private char GENDER;
	private String specialistDoctorName;
	private String otherSpecialistDoctorName;
	private String CONST;
	private String LVL;
	private Number ROWNUM_;
	private String WFTYPE;
	private Number MEDCO_UNITS;
	private Number CEX_UNITS;
	private String UNITID;
	private Date PODATE;
	private String POID;
	private String PARTFULL;
	private String POQTY;
	private String PENIND;

	private String RCP;
	private List<LabelBean> dispnsryList;
	private String RECQTY;
	private String BTNO;
	private String INVNO;
	private String INVDT;
	private Number DIFF;
	private String LSTUPDDT;
	private String PATIENTSCHEME;
	private String RELATION;
	private String ENORLLNAME;
	private String EHFCARDNO;
	private String EGENDER;
	private String ENROLLSTATUS;
	private String RELATIONNAME;
	private Number SNO;
	private String ENTRY_ID;
	private String DRUGTYPE;
	private String DRUGNAME;
	private String PRESC_QUANTITY;
	private String ISSUEDATE;
	private Date EXP_DT;

	private String DRUGTYPES;
	private String DRUGIDS;
	private String DRUG_TYPE;
	private String DRUG_NAME;
	private Number OPENING_BALANCE;
	private Number RECEIVED_STOCK;
	private String RECEIVED_QTY;
	private Number DISTRIBUTION_STOCK;
	private Number CLOSING_BALANCE;
	private String DRUG_TYPE_ID;
	private String PATIENT_NAME;
	private String MNFCTRNAME;
	private String MNFCTRID;
	private String ISSUE_QUANT;
	private String RCVD_QUANT;
	private String DISP_NAME;
	private String NO_INDENT_ITEMS;
	private String TOTAL_INDENT_VALUE;
	private String DISP_ID;
	private String DRUGCODE;
	private Date CLMSUBDT;
	private String REGNO;
	private String ITEMID;
	private String CONTRACTYEAR;
	private String TOT_STOCKPRICE;
	//Chandana - 9008 - Added below 5 variables
	private String CRITICAL_FLG;
	private String NO_CRTICALINDNT_ITEMS;
	private String TOT_CRTICALINDNT_VALUE;
	private String NO_NONCINDENT_ITEMS;
	private String TOT_NONCINDENT_VALUE;
	private String ORD_FLG;
	private String IND_NOTRECV;
	private String NAT_STOCK;
	private String NAT_DEF;
	private String IND_VAL;
	private String ITEM_ID;
	private String PRICE;
	private String WCNAME;
	private String RC_VALUE;
	private String RC_PRICE;
	private String DSTRBTRNAME;
	private String BATCHNO;
	private String ENH_QUAN_CODE;
	private String EMPID;
	private String EMPNAME;
	private String DSGNID;
	private String DSGNNAME;
	private String LOGINNAME;
	private String LEVELID;
	private String PATIENTTYPE;
	private Long IDVAL;
	private String INDENT_NO;
	private Number COUNT;
	private String USERID;
	private String MODULE;
	private String EMAILID;
	private String CUGNUM;
	private String SUBID;
	private String SUBNAME;
	private String SUBDESC;
	private String SUBCODE;
	private String APPSPE;
	private String INDENT_COUNT;
	private String INDENT_COUNT_CONSTANT;
	private String IDEAL_STOCK;
	private String AVAILABLE_COUNT;
	private String PEND_IND;
	private Date CRT_DT;
	private String DISTRIBUTOR_NAME;
	private String DISTRIBUTOR_ID;
	private String MNFCTR_ID;
	private String DSTRBTR_ID;
	private String INVOICENO;
	private String CRTDATE;
	private Number PACKAGE_AMOUNT;
	private Number AVAILABLE_QUANTITY;
	private Number APPROVED_AMOUNT;
	private String VENDOR_ID;
	private String ACTIVE_YN;
	private String PRIORITYID;
	private String APPRVAUTHORITY;
	private String CALLID;
	private String DISTRICT;
	private String HOSPITAL;
	private String CONTACTNO;
	private String COMPROLE;
	private String COMPON;
	private String MCINO;
	private String COMPDESC;
	private String NATURE;
	private String LINENO;
	private String NATUREID;
	private String REMARKS;
	private Number QTY;
	private String EXPDT;
	private String LST_3M_QUANT;
	private String CRT_USR;
	private Date LST_UPD_DT;
	private String PO_ID;
	private String LST_UPD_USR;
	private Date PO_DATE;
	private String TOTALQUANTITY;
	private String UNIT_PRICE;
	private String INVOICE_NUM;

	private String DISTRICTNAME;
	private String LOCID;
	private String CENTERID;
	private String DISPID;
	private String DISPNAME;
	private String PO_NO;
	private String PO_NO1;

	private Date PO_DT;

	private String EMPRESMAIL;
	private String EMPRESPH;
	private String EMPRATNCRDNO;
	private String DDO;
	private String empHstate;
	private String NAME;
	private String ATTACHNAME;
	private String ATTACHPATH;
	private String CRTDT;
	private String AADHAREXISTS;
	private String LSTUPDUSER;
	private String DRUGAMOUNT;
	private String SEQID;
	private String drugDispDate;
	private String EnrollId;
	private String ENROLLPRNTID;
	private String PreexistDiseases;
	private String Drugscurrent;
	private String Knowndrugaller;
	private String KnownDrugaller;
	private String Mdclhtry;
	private String Anycongenital;
	private String Anysurgerdne;
	private String NoSurgery;
	private String dOs;
	private String CrtUsr;
	private String LstUpdUsr;
	private String Msg;
	private String PREEXISTDISEASES;
	private String DRUGSCURRENT;
	private String KNOWNDRUGALLER;
	private String MDCLHTRY;
	private String ANYCONGENITAL;
	private String ANYSURGERDNE;
	private String NOSURGERY;
	private Date DOS;
	private Date invDate;
	private String CONTRACT_YEAR;

	private String ADDRESS;
	private String BENDOB;
	private String CARDTYPE;
	private String ACCRIDNO;
	private String IDTYPE;
	private String NEW_DIST;
	private String NEWPROC;
	private String INVOICENUMBER;

	private String NEW_MAND;
	private String NEW_VILLAGE;
	private String PAT_CARD_NUMBER;
	private String PAT_NAME;
	private String SERVICE_TYPE;

	private String MOBILE_NO;
	private String APP_DATE;
	private String TIME_SLOT;
	private String GRP_NAME;
	private String CONSULTANT_NAME;
	private String userId;
	private Float drugQuantity;
	private String hospId;
	private String patientId;
	private Float drugAmt;
	private String filepath;
	private String fileName;
	private int drugcount;
	private String branch;
	private String deptName;
	private int maxresults;
	private int pageId;

	private int totalPages;
	private int totalRecords;
	private int strtIndex;
	private String PATIENTID;
	private String CASEID;
	private String CASENO;
	private String CASESTATUS;
	private Date REGNDATE;

	private String ACTION;
	private String NAMEOFESTABLISHMENT;
	private String FOODACTION;
	private String ICDCODE;
	private String ICDNAME;
	private String MOBILENO;
	private String ATTACH;
	private String STATUS;

	private String IcdCatName;
	private char activeYn;
	private String hospActiveYn;
	private String icdCatCode;
	private Date crtDt;
	private Date DT;
	private String INTERNALSTATUS;
	List<LabelBean> lstSub;
	private Number NEXTVAL;
	private String flagName;
	private int dopUnits;
	private String ahcFlag;
	private String chronicFlag;
	private String INDENT_ID;
	private String validity;
	private String caseId;
	private String schemeId;
	private String cochlearYN;
	private String followUpType;
	private String price;
	private String otherSymptomName;
	private String MEDISURGFLAG;
	private String unitName;
	private String diagnoisedBy;
	private String hodName;
	private Date consultationTime;
	private char SERVICETYPE;
	private char HEALTH_CHECK;

	private String ANATOMICALNAME;
	private String ANATOMICALCODE;
	private String DISEASECODE;
	private String DISEASENAME;
	private String SUBCATCODE;
	private String SUBCATNAME;
	private String CATCODE;
	private String CATNAME;
	private String MAINCATCODE;
	private String MAINCATNAME;
	private String DIAGNOSISCODE;
	private String DIAGNOSISNAME;
	private String HOSPID;

	private String HOUSENO;
	private String UNITNAME;
	private String HOSPTYPE;
	private String HOSPEMAIL;
	private String disMainId;
	private String NEWEMPCODE;
	private String PARENT_UNITID;
	private String PATH;
	private String AADHARID;
	private String AADHAREID;
	private String DISNAME;
	private String SCHEMENAME;
	private String UNITTYPE;
	private String JOBID;
	private String EMPHNO;
	private String EMPSTRTNO;
	private String EMPRESVLGE;
	private String EMPRESMDL;
	private String EMPRESDIST;
	private String TYPE;
	private String TYPE1;
	private String TYPE2;
	private String TYPE3;
	private String POSTCODE;
	private String EMPIDM1;
	private String EMPIDM2;
	private String EMPOFCHNO;
	private String EMPOFCSTRTNO;
	private String EMPOFCVLGE;
	private String EMPOFCDIST;
	private String EMPOFCMDL;
	private String empOstate;
	private String EMPOFCPH;
	private String EMPOFCMAIL;
	private String roomNo;
	private String tokenNo;

	private Number UNITAMOUNT;
	private String DRUGUNITAMOUNT;
	private Number AMOUNT;
	private Number TOTCONSUMABLEAMOUNT;
	private Number TOTALDRUGAMT;
	private String QUANTITY;
	private String drugId;
	private Long quantity;
	private String batchNo;
	private Date expiryDate;
	private Date EXPIRYDATE;

	private String drugType;
	private String drugName;
	private String newDrugName;
	private String manufaturerName;
	private String distributerName;
	private double drugPrice;
	private String DRUGPRICE;
	private String drugCode;
	private String MEDFLG;
	private String excelFlag;
	private String WELLNESSCENTER;
	private String SEARCHTYPE;
	private String brandName;
	private Date INDENT_DT;
	private String DRUGID;
	private Float unitPrice;
	private List<LabelBean> dentaldetails;
	private String ADDRESSOFESTABLISHMENT;
	private String RETURN_DATE;
	private String INV_NUM;
	private String INV_DT;
	private String RETURN_INV_NUM;
	private String RETURN_INV_DT;
	private String CRT_DATE;

	private String AVG_STK;
	private String REQ;
	private String AVL;
	private String STOCK;
	private String DRUG_ID;
	private String HOSPNAME;
	private String OPENING_BALANCE_EXP;
	private String DISTRIBUTION_STOCK_EXP;
	private String CLOSING_BALANCE_EXP;
	private String CARDNO;
	private String wellnesslist;
	private String DSTRBTRID;
	//sowmya
	private String C1;
	private String C2;
	private String C3;
	private String C4;
	private String C5;
	private String C6;
	//sai krishna:CR#8134:API Integration
    private Number MEDPERIOD;
	public Number getMEDPERIOD() {
		return MEDPERIOD;
	}
	public void setMEDPERIOD(Number mEDPERIOD) {
		MEDPERIOD = mEDPERIOD;
	}


	    private String EXP_DATE;

	    public String getEXP_DATE() {
	        return EXP_DATE;
	    }

	    public void setEXP_DATE(String EXP_DATE) {
	        this.EXP_DATE = EXP_DATE;
	    }
	    
	    private String OUT_DATE;

	    public String getOUT_DATE() {
	        return EXP_DATE;
	    }

	    public void setOUT_DATE(String OUT_DATE) {
	        this.OUT_DATE = OUT_DATE;
	    }
	

	//pravalika
	private String DOSAGE;
	private Number MEDICATIONPERIOD;
	private String DRUGSUBTYPENAME;
	private String DRUGTYPECODE;
	private String DRUGSUBTYPECODE;
	private String DRUG_CODE;
	private String DRUG_SUB_CODE;
	private Number CASE_ORDER;
	
	public Number getCASE_ORDER() {
		return CASE_ORDER;
	}
	public void setCASE_ORDER(Number cASE_ORDER) {
		CASE_ORDER = cASE_ORDER;
	}
	private String DRUGISSUED_YN;
	

	public String getDRUGISSUED_YN() {
		return DRUGISSUED_YN;
	}
	public void setDRUGISSUED_YN(String dRUGISSUED_YN) {
		DRUGISSUED_YN = dRUGISSUED_YN;
	}

	public String getDRUG_CODE() {
		return DRUG_CODE;
	}
	public void setDRUG_CODE(String dRUG_CODE) {
		DRUG_CODE = dRUG_CODE;
	}
	public String getDRUG_SUB_CODE() {
		return DRUG_SUB_CODE;
	}
	public void setDRUG_SUB_CODE(String dRUG_SUB_CODE) {
		DRUG_SUB_CODE = dRUG_SUB_CODE;
	}
	public String getDRUGSUBTYPECODE() {
		return DRUGSUBTYPECODE;
	}
	public void setDRUGSUBTYPECODE(String dRUGSUBTYPECODE) {
		DRUGSUBTYPECODE = dRUGSUBTYPECODE;
	}
	public String getDRUGTYPECODE() {
		return DRUGTYPECODE;
	}
	public void setDRUGTYPECODE(String dRUGTYPECODE) {
		DRUGTYPECODE = dRUGTYPECODE;
	}
	public String getDRUGSUBTYPENAME() {
		return DRUGSUBTYPENAME;
	}
	public void setDRUGSUBTYPENAME(String dRUGSUBTYPENAME) {
		DRUGSUBTYPENAME = dRUGSUBTYPENAME;
	}
	public Number getMEDICATIONPERIOD() {
		return MEDICATIONPERIOD;
	}
	public void setMEDICATIONPERIOD(Number mEDICATIONPERIOD) {
		MEDICATIONPERIOD = mEDICATIONPERIOD;
	}
	public String getDOSAGE() {
		return DOSAGE;
	}
	public void setDOSAGE(String dOSAGE) {
		DOSAGE = dOSAGE;
	}
	
		private String C7;
		private String C8;
		
	 public String getC7() {
	 return C7;
	}
	public void setC7(String c7) {
		C7 = c7;
	}
		
	public String getC8() {
		return C8;
	}
	public void setC8(String c8) {
		C8 = c8;
	}
	
	public String getC4() {
		return C4;
	}
	public void setC4(String c4) {
		C4 = c4;
	}
	public String getC5() {
		return C5;
	}
	public void setC5(String c5) {
		C5 = c5;
	}
	public String getC6() {
		return C6;
	}
	public void setC6(String c6) {
		C6 = c6;
	}
	public String getC1() {
		return C1;
	}
	public void setC1(String c1) {
		C1 = c1;
	}
	public String getC2() {
		return C2;
	}
	public void setC2(String c2) {
		C2 = c2;
	}
	public String getC3() {
		return C3;
	}
	public void setC3(String c3) {
		C3 = c3;
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

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getVALUE() {
		return VALUE;
	}

	public void setVALUE(String vALUE) {
		VALUE = vALUE;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public char getGENDER() {
		return GENDER;
	}

	public void setGENDER(char gENDER) {
		GENDER = gENDER;
	}

	public String getSpecialistDoctorName() {
		return specialistDoctorName;
	}

	public void setSpecialistDoctorName(String specialistDoctorName) {
		this.specialistDoctorName = specialistDoctorName;
	}

	public String getOtherSpecialistDoctorName() {
		return otherSpecialistDoctorName;
	}

	public void setOtherSpecialistDoctorName(String otherSpecialistDoctorName) {
		this.otherSpecialistDoctorName = otherSpecialistDoctorName;
	}

	public String getCONST() {
		return CONST;
	}

	public void setCONST(String cONST) {
		CONST = cONST;
	}

	public String getLVL() {
		return LVL;
	}

	public void setLVL(String lVL) {
		LVL = lVL;
	}

	public Number getROWNUM_() {
		return ROWNUM_;
	}

	public void setROWNUM_(Number rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}

	public String getWFTYPE() {
		return WFTYPE;
	}

	public void setWFTYPE(String wFTYPE) {
		WFTYPE = wFTYPE;
	}

	public Number getMEDCO_UNITS() {
		return MEDCO_UNITS;
	}

	public void setMEDCO_UNITS(Number mEDCO_UNITS) {
		MEDCO_UNITS = mEDCO_UNITS;
	}

	public Number getCEX_UNITS() {
		return CEX_UNITS;
	}

	public void setCEX_UNITS(Number cEX_UNITS) {
		CEX_UNITS = cEX_UNITS;
	}

	public String getUNITID() {
		return UNITID;
	}

	public void setUNITID(String uNITID) {
		UNITID = uNITID;
	}

	public Date getPODATE() {
		return PODATE;
	}

	public void setPODATE(Date pODATE) {
		PODATE = pODATE;
	}

	public String getPOID() {
		return POID;
	}

	public void setPOID(String pOID) {
		POID = pOID;
	}

	public String getPARTFULL() {
		return PARTFULL;
	}

	public void setPARTFULL(String pARTFULL) {
		PARTFULL = pARTFULL;
	}

	public String getPOQTY() {
		return POQTY;
	}

	public void setPOQTY(String pOQTY) {
		POQTY = pOQTY;
	}

	public String getPENIND() {
		return PENIND;
	}

	public void setPENIND(String pENIND) {
		PENIND = pENIND;
	}

	public String getRECQTY() {
		return RECQTY;
	}

	public void setRECQTY(String rECQTY) {
		RECQTY = rECQTY;
	}

	public String getBTNO() {
		return BTNO;
	}

	public void setBTNO(String bTNO) {
		BTNO = bTNO;
	}

	public String getINVNO() {
		return INVNO;
	}

	public void setINVNO(String iNVNO) {
		INVNO = iNVNO;
	}

	public String getINVDT() {
		return INVDT;
	}

	public void setINVDT(String iNVDT) {
		INVDT = iNVDT;
	}

	public Number getDIFF() {
		return DIFF;
	}

	public void setDIFF(Number dIFF) {
		DIFF = dIFF;
	}

	public String getLSTUPDDT() {
		return LSTUPDDT;
	}

	public void setLSTUPDDT(String lSTUPDDT) {
		LSTUPDDT = lSTUPDDT;
	}

	public String getPATIENTSCHEME() {
		return PATIENTSCHEME;
	}

	public void setPATIENTSCHEME(String pATIENTSCHEME) {
		PATIENTSCHEME = pATIENTSCHEME;
	}

	public String getRELATION() {
		return RELATION;
	}

	public void setRELATION(String rELATION) {
		RELATION = rELATION;
	}

	public String getENORLLNAME() {
		return ENORLLNAME;
	}

	public void setENORLLNAME(String eNORLLNAME) {
		ENORLLNAME = eNORLLNAME;
	}

	public String getEHFCARDNO() {
		return EHFCARDNO;
	}

	public void setEHFCARDNO(String eHFCARDNO) {
		EHFCARDNO = eHFCARDNO;
	}

	public String getEGENDER() {
		return EGENDER;
	}

	public void setEGENDER(String eGENDER) {
		EGENDER = eGENDER;
	}

	public String getENROLLSTATUS() {
		return ENROLLSTATUS;
	}

	public void setENROLLSTATUS(String eNROLLSTATUS) {
		ENROLLSTATUS = eNROLLSTATUS;
	}

	public String getRELATIONNAME() {
		return RELATIONNAME;
	}

	public void setRELATIONNAME(String rELATIONNAME) {
		RELATIONNAME = rELATIONNAME;
	}

	public Number getSNO() {
		return SNO;
	}

	public void setSNO(Number sNO) {
		SNO = sNO;
	}

	public String getENTRY_ID() {
		return ENTRY_ID;
	}

	public void setENTRY_ID(String eNTRY_ID) {
		ENTRY_ID = eNTRY_ID;
	}

	public String getDRUGTYPE() {
		return DRUGTYPE;
	}

	public void setDRUGTYPE(String dRUGTYPE) {
		DRUGTYPE = dRUGTYPE;
	}

	public String getDRUGNAME() {
		return DRUGNAME;
	}

	public void setDRUGNAME(String dRUGNAME) {
		DRUGNAME = dRUGNAME;
	}

	public String getPRESC_QUANTITY() {
		return PRESC_QUANTITY;
	}

	public void setPRESC_QUANTITY(String pRESC_QUANTITY) {
		PRESC_QUANTITY = pRESC_QUANTITY;
	}

	public String getISSUEDATE() {
		return ISSUEDATE;
	}

	public void setISSUEDATE(String iSSUEDATE) {
		ISSUEDATE = iSSUEDATE;
	}

	public Date getEXP_DT() {
		return EXP_DT;
	}

	public void setEXP_DT(Date eXP_DT) {
		EXP_DT = eXP_DT;
	}

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

	public String getRECEIVED_QTY() {
		return RECEIVED_QTY;
	}

	public void setRECEIVED_QTY(String rECEIVED_QTY) {
		RECEIVED_QTY = rECEIVED_QTY;
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

	public String getDRUG_TYPE_ID() {
		return DRUG_TYPE_ID;
	}

	public void setDRUG_TYPE_ID(String dRUG_TYPE_ID) {
		DRUG_TYPE_ID = dRUG_TYPE_ID;
	}

	public String getPATIENT_NAME() {
		return PATIENT_NAME;
	}

	public void setPATIENT_NAME(String pATIENT_NAME) {
		PATIENT_NAME = pATIENT_NAME;
	}

	public String getMNFCTRNAME() {
		return MNFCTRNAME;
	}

	public void setMNFCTRNAME(String mNFCTRNAME) {
		MNFCTRNAME = mNFCTRNAME;
	}

	public String getMNFCTRID() {
		return MNFCTRID;
	}

	public void setMNFCTRID(String mNFCTRID) {
		MNFCTRID = mNFCTRID;
	}

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

	public String getDISP_NAME() {
		return DISP_NAME;
	}

	public void setDISP_NAME(String dISP_NAME) {
		DISP_NAME = dISP_NAME;
	}

	public String getNO_INDENT_ITEMS() {
		return NO_INDENT_ITEMS;
	}

	public void setNO_INDENT_ITEMS(String nO_INDENT_ITEMS) {
		NO_INDENT_ITEMS = nO_INDENT_ITEMS;
	}

	public String getTOTAL_INDENT_VALUE() {
		return TOTAL_INDENT_VALUE;
	}

	public void setTOTAL_INDENT_VALUE(String tOTAL_INDENT_VALUE) {
		TOTAL_INDENT_VALUE = tOTAL_INDENT_VALUE;
	}

	public String getDISP_ID() {
		return DISP_ID;
	}

	public void setDISP_ID(String dISP_ID) {
		DISP_ID = dISP_ID;
	}

	public String getDRUGCODE() {
		return DRUGCODE;
	}

	public void setDRUGCODE(String dRUGCODE) {
		DRUGCODE = dRUGCODE;
	}

	public Date getCLMSUBDT() {
		return CLMSUBDT;
	}

	public void setCLMSUBDT(Date cLMSUBDT) {
		CLMSUBDT = cLMSUBDT;
	}

	public String getREGNO() {
		return REGNO;
	}

	public void setREGNO(String rEGNO) {
		REGNO = rEGNO;
	}

	public String getITEMID() {
		return ITEMID;
	}

	public void setITEMID(String iTEMID) {
		ITEMID = iTEMID;
	}

	public String getCONTRACTYEAR() {
		return CONTRACTYEAR;
	}

	public void setCONTRACTYEAR(String cONTRACTYEAR) {
		CONTRACTYEAR = cONTRACTYEAR;
	}

	public String getTOT_STOCKPRICE() {
		return TOT_STOCKPRICE;
	}

	public void setTOT_STOCKPRICE(String tOT_STOCKPRICE) {
		TOT_STOCKPRICE = tOT_STOCKPRICE;
	}

	public String getIND_NOTRECV() {
		return IND_NOTRECV;
	}

	public void setIND_NOTRECV(String iND_NOTRECV) {
		IND_NOTRECV = iND_NOTRECV;
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

	public String getITEM_ID() {
		return ITEM_ID;
	}

	public void setITEM_ID(String iTEM_ID) {
		ITEM_ID = iTEM_ID;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}

	public String getWCNAME() {
		return WCNAME;
	}

	public void setWCNAME(String wCNAME) {
		WCNAME = wCNAME;
	}

	public String getRC_VALUE() {
		return RC_VALUE;
	}

	public void setRC_VALUE(String rC_VALUE) {
		RC_VALUE = rC_VALUE;
	}

	public String getRC_PRICE() {
		return RC_PRICE;
	}

	public void setRC_PRICE(String rC_PRICE) {
		RC_PRICE = rC_PRICE;
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

	public String getENH_QUAN_CODE() {
		return ENH_QUAN_CODE;
	}

	public void setENH_QUAN_CODE(String eNH_QUAN_CODE) {
		ENH_QUAN_CODE = eNH_QUAN_CODE;
	}

	public String getEMPID() {
		return EMPID;
	}

	public void setEMPID(String eMPID) {
		EMPID = eMPID;
	}

	public String getEMPNAME() {
		return EMPNAME;
	}

	public void setEMPNAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}

	public String getDSGNID() {
		return DSGNID;
	}

	public void setDSGNID(String dSGNID) {
		DSGNID = dSGNID;
	}

	public String getDSGNNAME() {
		return DSGNNAME;
	}

	public void setDSGNNAME(String dSGNNAME) {
		DSGNNAME = dSGNNAME;
	}

	public String getLOGINNAME() {
		return LOGINNAME;
	}

	public void setLOGINNAME(String lOGINNAME) {
		LOGINNAME = lOGINNAME;
	}

	public String getLEVELID() {
		return LEVELID;
	}

	public void setLEVELID(String lEVELID) {
		LEVELID = lEVELID;
	}

	public String getPATIENTTYPE() {
		return PATIENTTYPE;
	}

	public void setPATIENTTYPE(String pATIENTTYPE) {
		PATIENTTYPE = pATIENTTYPE;
	}

	public Long getIDVAL() {
		return IDVAL;
	}

	public void setIDVAL(Long iDVAL) {
		IDVAL = iDVAL;
	}

	public String getINDENT_NO() {
		return INDENT_NO;
	}

	public void setINDENT_NO(String iNDENT_NO) {
		INDENT_NO = iNDENT_NO;
	}

	public Number getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}

	public String getUSERID() {
		return USERID;
	}

	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}

	public String getMODULE() {
		return MODULE;
	}

	public void setMODULE(String mODULE) {
		MODULE = mODULE;
	}

	public String getEMAILID() {
		return EMAILID;
	}

	public void setEMAILID(String eMAILID) {
		EMAILID = eMAILID;
	}

	public String getCUGNUM() {
		return CUGNUM;
	}

	public void setCUGNUM(String cUGNUM) {
		CUGNUM = cUGNUM;
	}

	public String getSUBID() {
		return SUBID;
	}

	public void setSUBID(String sUBID) {
		SUBID = sUBID;
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

	public String getINDENT_COUNT() {
		return INDENT_COUNT;
	}

	public void setINDENT_COUNT(String iNDENT_COUNT) {
		INDENT_COUNT = iNDENT_COUNT;
	}

	public String getINDENT_COUNT_CONSTANT() {
		return INDENT_COUNT_CONSTANT;
	}

	public void setINDENT_COUNT_CONSTANT(String iNDENT_COUNT_CONSTANT) {
		INDENT_COUNT_CONSTANT = iNDENT_COUNT_CONSTANT;
	}

	public String getIDEAL_STOCK() {
		return IDEAL_STOCK;
	}

	public void setIDEAL_STOCK(String iDEAL_STOCK) {
		IDEAL_STOCK = iDEAL_STOCK;
	}

	public String getAVAILABLE_COUNT() {
		return AVAILABLE_COUNT;
	}

	public void setAVAILABLE_COUNT(String aVAILABLE_COUNT) {
		AVAILABLE_COUNT = aVAILABLE_COUNT;
	}

	public String getPEND_IND() {
		return PEND_IND;
	}

	public void setPEND_IND(String pEND_IND) {
		PEND_IND = pEND_IND;
	}

	public Date getCRT_DT() {
		return CRT_DT;
	}

	public void setCRT_DT(Date cRT_DT) {
		CRT_DT = cRT_DT;
	}

	public String getDISTRIBUTOR_NAME() {
		return DISTRIBUTOR_NAME;
	}

	public void setDISTRIBUTOR_NAME(String dISTRIBUTOR_NAME) {
		DISTRIBUTOR_NAME = dISTRIBUTOR_NAME;
	}

	public String getDISTRIBUTOR_ID() {
		return DISTRIBUTOR_ID;
	}

	public void setDISTRIBUTOR_ID(String dISTRIBUTOR_ID) {
		DISTRIBUTOR_ID = dISTRIBUTOR_ID;
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

	public String getINVOICENO() {
		return INVOICENO;
	}

	public void setINVOICENO(String iNVOICENO) {
		INVOICENO = iNVOICENO;
	}

	public String getCRTDATE() {
		return CRTDATE;
	}

	public void setCRTDATE(String cRTDATE) {
		CRTDATE = cRTDATE;
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

	public String getVENDOR_ID() {
		return VENDOR_ID;
	}

	public void setVENDOR_ID(String vENDOR_ID) {
		VENDOR_ID = vENDOR_ID;
	}

	public String getACTIVE_YN() {
		return ACTIVE_YN;
	}

	public void setACTIVE_YN(String aCTIVE_YN) {
		ACTIVE_YN = aCTIVE_YN;
	}

	public String getPRIORITYID() {
		return PRIORITYID;
	}

	public void setPRIORITYID(String pRIORITYID) {
		PRIORITYID = pRIORITYID;
	}

	public String getAPPRVAUTHORITY() {
		return APPRVAUTHORITY;
	}

	public void setAPPRVAUTHORITY(String aPPRVAUTHORITY) {
		APPRVAUTHORITY = aPPRVAUTHORITY;
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

	public String getCONTACTNO() {
		return CONTACTNO;
	}

	public void setCONTACTNO(String cONTACTNO) {
		CONTACTNO = cONTACTNO;
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

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public Number getQTY() {
		return QTY;
	}

	public void setQTY(Number qTY) {
		QTY = qTY;
	}

	public String getEXPDT() {
		return EXPDT;
	}

	public void setEXPDT(String eXPDT) {
		EXPDT = eXPDT;
	}

	public String getLST_3M_QUANT() {
		return LST_3M_QUANT;
	}

	public void setLST_3M_QUANT(String lST_3M_QUANT) {
		LST_3M_QUANT = lST_3M_QUANT;
	}

	public String getCRT_USR() {
		return CRT_USR;
	}

	public void setCRT_USR(String cRT_USR) {
		CRT_USR = cRT_USR;
	}

	public Date getLST_UPD_DT() {
		return LST_UPD_DT;
	}

	public void setLST_UPD_DT(Date lST_UPD_DT) {
		LST_UPD_DT = lST_UPD_DT;
	}

	public String getPO_ID() {
		return PO_ID;
	}

	public void setPO_ID(String pO_ID) {
		PO_ID = pO_ID;
	}

	public String getLST_UPD_USR() {
		return LST_UPD_USR;
	}

	public void setLST_UPD_USR(String lST_UPD_USR) {
		LST_UPD_USR = lST_UPD_USR;
	}

	public Date getPO_DATE() {
		return PO_DATE;
	}

	public void setPO_DATE(Date pO_DATE) {
		PO_DATE = pO_DATE;
	}

	public String getTOTALQUANTITY() {
		return TOTALQUANTITY;
	}

	public void setTOTALQUANTITY(String tOTALQUANTITY) {
		TOTALQUANTITY = tOTALQUANTITY;
	}

	public String getUNIT_PRICE() {
		return UNIT_PRICE;
	}

	public void setUNIT_PRICE(String uNIT_PRICE) {
		UNIT_PRICE = uNIT_PRICE;
	}

	public String getINVOICE_NUM() {
		return INVOICE_NUM;
	}

	public void setINVOICE_NUM(String iNVOICE_NUM) {
		INVOICE_NUM = iNVOICE_NUM;
	}

	public String getDISTRICTNAME() {
		return DISTRICTNAME;
	}

	public void setDISTRICTNAME(String dISTRICTNAME) {
		DISTRICTNAME = dISTRICTNAME;
	}

	public String getLOCID() {
		return LOCID;
	}

	public void setLOCID(String lOCID) {
		LOCID = lOCID;
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

	public String getDISPNAME() {
		return DISPNAME;
	}

	public void setDISPNAME(String dISPNAME) {
		DISPNAME = dISPNAME;
	}

	public String getPO_NO() {
		return PO_NO;
	}

	public void setPO_NO(String pO_NO) {
		PO_NO = pO_NO;
	}

	public String getPO_NO1() {
		return PO_NO1;
	}

	public void setPO_NO1(String pO_NO1) {
		PO_NO1 = pO_NO1;
	}

	public Date getPO_DT() {
		return PO_DT;
	}

	public void setPO_DT(Date pO_DT) {
		PO_DT = pO_DT;
	}

	public String getEMPRESMAIL() {
		return EMPRESMAIL;
	}

	public void setEMPRESMAIL(String eMPRESMAIL) {
		EMPRESMAIL = eMPRESMAIL;
	}

	public String getEMPRESPH() {
		return EMPRESPH;
	}

	public void setEMPRESPH(String eMPRESPH) {
		EMPRESPH = eMPRESPH;
	}

	public String getEMPRATNCRDNO() {
		return EMPRATNCRDNO;
	}

	public void setEMPRATNCRDNO(String eMPRATNCRDNO) {
		EMPRATNCRDNO = eMPRATNCRDNO;
	}

	public String getDDO() {
		return DDO;
	}

	public void setDDO(String dDO) {
		DDO = dDO;
	}

	public String getEmpHstate() {
		return empHstate;
	}

	public void setEmpHstate(String empHstate) {
		this.empHstate = empHstate;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getATTACHNAME() {
		return ATTACHNAME;
	}

	public void setATTACHNAME(String aTTACHNAME) {
		ATTACHNAME = aTTACHNAME;
	}

	public String getATTACHPATH() {
		return ATTACHPATH;
	}

	public void setATTACHPATH(String aTTACHPATH) {
		ATTACHPATH = aTTACHPATH;
	}

	public String getCRTDT() {
		return CRTDT;
	}

	public void setCRTDT(String cRTDT) {
		CRTDT = cRTDT;
	}

	public String getAADHAREXISTS() {
		return AADHAREXISTS;
	}

	public void setAADHAREXISTS(String aADHAREXISTS) {
		AADHAREXISTS = aADHAREXISTS;
	}

	public String getLSTUPDUSER() {
		return LSTUPDUSER;
	}

	public void setLSTUPDUSER(String lSTUPDUSER) {
		LSTUPDUSER = lSTUPDUSER;
	}

	public String getDRUGAMOUNT() {
		return DRUGAMOUNT;
	}

	public void setDRUGAMOUNT(String dRUGAMOUNT) {
		DRUGAMOUNT = dRUGAMOUNT;
	}

	public String getSEQID() {
		return SEQID;
	}

	public void setSEQID(String sEQID) {
		SEQID = sEQID;
	}

	public String getDrugDispDate() {
		return drugDispDate;
	}

	public void setDrugDispDate(String drugDispDate) {
		this.drugDispDate = drugDispDate;
	}

	public String getEnrollId() {
		return EnrollId;
	}

	public void setEnrollId(String enrollId) {
		EnrollId = enrollId;
	}

	public String getENROLLPRNTID() {
		return ENROLLPRNTID;
	}

	public void setENROLLPRNTID(String eNROLLPRNTID) {
		ENROLLPRNTID = eNROLLPRNTID;
	}

	public String getPreexistDiseases() {
		return PreexistDiseases;
	}

	public void setPreexistDiseases(String preexistDiseases) {
		PreexistDiseases = preexistDiseases;
	}

	public String getDrugscurrent() {
		return Drugscurrent;
	}

	public void setDrugscurrent(String drugscurrent) {
		Drugscurrent = drugscurrent;
	}

	public String getKnowndrugaller() {
		return Knowndrugaller;
	}

	public void setKnowndrugaller(String knowndrugaller) {
		Knowndrugaller = knowndrugaller;
	}

	public String getKnownDrugaller() {
		return KnownDrugaller;
	}

	public void setKnownDrugaller(String knownDrugaller) {
		KnownDrugaller = knownDrugaller;
	}

	public String getMdclhtry() {
		return Mdclhtry;
	}

	public void setMdclhtry(String mdclhtry) {
		Mdclhtry = mdclhtry;
	}

	public String getAnycongenital() {
		return Anycongenital;
	}

	public void setAnycongenital(String anycongenital) {
		Anycongenital = anycongenital;
	}

	public String getAnysurgerdne() {
		return Anysurgerdne;
	}

	public void setAnysurgerdne(String anysurgerdne) {
		Anysurgerdne = anysurgerdne;
	}

	public String getNoSurgery() {
		return NoSurgery;
	}

	public void setNoSurgery(String noSurgery) {
		NoSurgery = noSurgery;
	}

	public String getdOs() {
		return dOs;
	}

	public void setdOs(String dOs) {
		this.dOs = dOs;
	}

	public String getCrtUsr() {
		return CrtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		CrtUsr = crtUsr;
	}

	public String getLstUpdUsr() {
		return LstUpdUsr;
	}

	public void setLstUpdUsr(String lstUpdUsr) {
		LstUpdUsr = lstUpdUsr;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getPREEXISTDISEASES() {
		return PREEXISTDISEASES;
	}

	public void setPREEXISTDISEASES(String pREEXISTDISEASES) {
		PREEXISTDISEASES = pREEXISTDISEASES;
	}

	public String getDRUGSCURRENT() {
		return DRUGSCURRENT;
	}

	public void setDRUGSCURRENT(String dRUGSCURRENT) {
		DRUGSCURRENT = dRUGSCURRENT;
	}

	public String getKNOWNDRUGALLER() {
		return KNOWNDRUGALLER;
	}

	public void setKNOWNDRUGALLER(String kNOWNDRUGALLER) {
		KNOWNDRUGALLER = kNOWNDRUGALLER;
	}

	public String getMDCLHTRY() {
		return MDCLHTRY;
	}

	public void setMDCLHTRY(String mDCLHTRY) {
		MDCLHTRY = mDCLHTRY;
	}

	public String getANYCONGENITAL() {
		return ANYCONGENITAL;
	}

	public void setANYCONGENITAL(String aNYCONGENITAL) {
		ANYCONGENITAL = aNYCONGENITAL;
	}

	public String getANYSURGERDNE() {
		return ANYSURGERDNE;
	}

	public void setANYSURGERDNE(String aNYSURGERDNE) {
		ANYSURGERDNE = aNYSURGERDNE;
	}

	public String getNOSURGERY() {
		return NOSURGERY;
	}

	public void setNOSURGERY(String nOSURGERY) {
		NOSURGERY = nOSURGERY;
	}

	public Date getDOS() {
		return DOS;
	}

	public void setDOS(Date dOS) {
		DOS = dOS;
	}

	public Date getInvDate() {
		return invDate;
	}

	public void setInvDate(Date invDate) {
		this.invDate = invDate;
	}

	public String getCONTRACT_YEAR() {
		return CONTRACT_YEAR;
	}

	public void setCONTRACT_YEAR(String cONTRACT_YEAR) {
		CONTRACT_YEAR = cONTRACT_YEAR;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public String getBENDOB() {
		return BENDOB;
	}

	public void setBENDOB(String bENDOB) {
		BENDOB = bENDOB;
	}

	public String getCARDTYPE() {
		return CARDTYPE;
	}

	public void setCARDTYPE(String cARDTYPE) {
		CARDTYPE = cARDTYPE;
	}

	public String getACCRIDNO() {
		return ACCRIDNO;
	}

	public void setACCRIDNO(String aCCRIDNO) {
		ACCRIDNO = aCCRIDNO;
	}

	public String getIDTYPE() {
		return IDTYPE;
	}

	public void setIDTYPE(String iDTYPE) {
		IDTYPE = iDTYPE;
	}

	public String getNEW_DIST() {
		return NEW_DIST;
	}

	public void setNEW_DIST(String nEW_DIST) {
		NEW_DIST = nEW_DIST;
	}

	public String getNEWPROC() {
		return NEWPROC;
	}

	public void setNEWPROC(String nEWPROC) {
		NEWPROC = nEWPROC;
	}

	public String getINVOICENUMBER() {
		return INVOICENUMBER;
	}

	public void setINVOICENUMBER(String iNVOICENUMBER) {
		INVOICENUMBER = iNVOICENUMBER;
	}

	public String getNEW_MAND() {
		return NEW_MAND;
	}

	public void setNEW_MAND(String nEW_MAND) {
		NEW_MAND = nEW_MAND;
	}

	public String getNEW_VILLAGE() {
		return NEW_VILLAGE;
	}

	public void setNEW_VILLAGE(String nEW_VILLAGE) {
		NEW_VILLAGE = nEW_VILLAGE;
	}

	public String getPAT_CARD_NUMBER() {
		return PAT_CARD_NUMBER;
	}

	public void setPAT_CARD_NUMBER(String pAT_CARD_NUMBER) {
		PAT_CARD_NUMBER = pAT_CARD_NUMBER;
	}

	public String getPAT_NAME() {
		return PAT_NAME;
	}

	public void setPAT_NAME(String pAT_NAME) {
		PAT_NAME = pAT_NAME;
	}

	public String getSERVICE_TYPE() {
		return SERVICE_TYPE;
	}

	public void setSERVICE_TYPE(String sERVICE_TYPE) {
		SERVICE_TYPE = sERVICE_TYPE;
	}

	public String getMOBILE_NO() {
		return MOBILE_NO;
	}

	public void setMOBILE_NO(String mOBILE_NO) {
		MOBILE_NO = mOBILE_NO;
	}

	public String getAPP_DATE() {
		return APP_DATE;
	}

	public void setAPP_DATE(String aPP_DATE) {
		APP_DATE = aPP_DATE;
	}

	public String getTIME_SLOT() {
		return TIME_SLOT;
	}

	public void setTIME_SLOT(String tIME_SLOT) {
		TIME_SLOT = tIME_SLOT;
	}

	public String getGRP_NAME() {
		return GRP_NAME;
	}

	public void setGRP_NAME(String gRP_NAME) {
		GRP_NAME = gRP_NAME;
	}

	public String getCONSULTANT_NAME() {
		return CONSULTANT_NAME;
	}

	public void setCONSULTANT_NAME(String cONSULTANT_NAME) {
		CONSULTANT_NAME = cONSULTANT_NAME;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Float getDrugQuantity() {
		return drugQuantity;
	}

	public void setDrugQuantity(Float drugQuantity) {
		this.drugQuantity = drugQuantity;
	}

	public String getHospId() {
		return hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Float getDrugAmt() {
		return drugAmt;
	}

	public void setDrugAmt(Float drugAmt) {
		this.drugAmt = drugAmt;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getDrugcount() {
		return drugcount;
	}

	public void setDrugcount(int drugcount) {
		this.drugcount = drugcount;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
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

	public String getCASENO() {
		return CASENO;
	}

	public void setCASENO(String cASENO) {
		CASENO = cASENO;
	}

	public String getCASESTATUS() {
		return CASESTATUS;
	}

	public void setCASESTATUS(String cASESTATUS) {
		CASESTATUS = cASESTATUS;
	}

	public Date getREGNDATE() {
		return REGNDATE;
	}

	public void setREGNDATE(Date rEGNDATE) {
		REGNDATE = rEGNDATE;
	}

	public String getACTION() {
		return ACTION;
	}

	public void setACTION(String aCTION) {
		ACTION = aCTION;
	}

	public String getNAMEOFESTABLISHMENT() {
		return NAMEOFESTABLISHMENT;
	}

	public void setNAMEOFESTABLISHMENT(String nAMEOFESTABLISHMENT) {
		NAMEOFESTABLISHMENT = nAMEOFESTABLISHMENT;
	}

	public String getFOODACTION() {
		return FOODACTION;
	}

	public void setFOODACTION(String fOODACTION) {
		FOODACTION = fOODACTION;
	}

	public String getICDCODE() {
		return ICDCODE;
	}

	public void setICDCODE(String iCDCODE) {
		ICDCODE = iCDCODE;
	}

	public String getICDNAME() {
		return ICDNAME;
	}

	public void setICDNAME(String iCDNAME) {
		ICDNAME = iCDNAME;
	}

	public String getMOBILENO() {
		return MOBILENO;
	}

	public void setMOBILENO(String mOBILENO) {
		MOBILENO = mOBILENO;
	}

	public String getATTACH() {
		return ATTACH;
	}

	public void setATTACH(String aTTACH) {
		ATTACH = aTTACH;
	}

	public String getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(String sTATUS) {
		STATUS = sTATUS;
	}

	public String getIcdCatName() {
		return IcdCatName;
	}

	public void setIcdCatName(String icdCatName) {
		IcdCatName = icdCatName;
	}

	public char getActiveYn() {
		return activeYn;
	}

	public void setActiveYn(char activeYn) {
		this.activeYn = activeYn;
	}

	public String getHospActiveYn() {
		return hospActiveYn;
	}

	public void setHospActiveYn(String hospActiveYn) {
		this.hospActiveYn = hospActiveYn;
	}

	public String getIcdCatCode() {
		return icdCatCode;
	}

	public void setIcdCatCode(String icdCatCode) {
		this.icdCatCode = icdCatCode;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public Date getDT() {
		return DT;
	}

	public void setDT(Date dT) {
		DT = dT;
	}

	public String getINTERNALSTATUS() {
		return INTERNALSTATUS;
	}

	public void setINTERNALSTATUS(String iNTERNALSTATUS) {
		INTERNALSTATUS = iNTERNALSTATUS;
	}

	public List<LabelBean> getLstSub() {
		return lstSub;
	}

	public void setLstSub(List<LabelBean> lstSub) {
		this.lstSub = lstSub;
	}

	public Number getNEXTVAL() {
		return NEXTVAL;
	}

	public void setNEXTVAL(Number nEXTVAL) {
		NEXTVAL = nEXTVAL;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

	public int getDopUnits() {
		return dopUnits;
	}

	public void setDopUnits(int dopUnits) {
		this.dopUnits = dopUnits;
	}

	public String getAhcFlag() {
		return ahcFlag;
	}

	public void setAhcFlag(String ahcFlag) {
		this.ahcFlag = ahcFlag;
	}

	public String getChronicFlag() {
		return chronicFlag;
	}

	public void setChronicFlag(String chronicFlag) {
		this.chronicFlag = chronicFlag;
	}

	public String getINDENT_ID() {
		return INDENT_ID;
	}

	public void setINDENT_ID(String iNDENT_ID) {
		INDENT_ID = iNDENT_ID;
	}

	public String getValidity() {
		return validity;
	}

	public void setValidity(String validity) {
		this.validity = validity;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getCochlearYN() {
		return cochlearYN;
	}

	public void setCochlearYN(String cochlearYN) {
		this.cochlearYN = cochlearYN;
	}

	public String getFollowUpType() {
		return followUpType;
	}

	public void setFollowUpType(String followUpType) {
		this.followUpType = followUpType;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getOtherSymptomName() {
		return otherSymptomName;
	}

	public void setOtherSymptomName(String otherSymptomName) {
		this.otherSymptomName = otherSymptomName;
	}

	public String getMEDISURGFLAG() {
		return MEDISURGFLAG;
	}

	public void setMEDISURGFLAG(String mEDISURGFLAG) {
		MEDISURGFLAG = mEDISURGFLAG;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getDiagnoisedBy() {
		return diagnoisedBy;
	}

	public void setDiagnoisedBy(String diagnoisedBy) {
		this.diagnoisedBy = diagnoisedBy;
	}

	public String getHodName() {
		return hodName;
	}

	public void setHodName(String hodName) {
		this.hodName = hodName;
	}

	public Date getConsultationTime() {
		return consultationTime;
	}

	public void setConsultationTime(Date consultationTime) {
		this.consultationTime = consultationTime;
	}

	public char getSERVICETYPE() {
		return SERVICETYPE;
	}

	public void setSERVICETYPE(char sERVICETYPE) {
		SERVICETYPE = sERVICETYPE;
	}

	public char getHEALTH_CHECK() {
		return HEALTH_CHECK;
	}

	public void setHEALTH_CHECK(char hEALTH_CHECK) {
		HEALTH_CHECK = hEALTH_CHECK;
	}

	public String getANATOMICALNAME() {
		return ANATOMICALNAME;
	}

	public void setANATOMICALNAME(String aNATOMICALNAME) {
		ANATOMICALNAME = aNATOMICALNAME;
	}

	public String getANATOMICALCODE() {
		return ANATOMICALCODE;
	}

	public void setANATOMICALCODE(String aNATOMICALCODE) {
		ANATOMICALCODE = aNATOMICALCODE;
	}

	public String getDISEASECODE() {
		return DISEASECODE;
	}

	public void setDISEASECODE(String dISEASECODE) {
		DISEASECODE = dISEASECODE;
	}

	public String getDISEASENAME() {
		return DISEASENAME;
	}

	public void setDISEASENAME(String dISEASENAME) {
		DISEASENAME = dISEASENAME;
	}

	public String getSUBCATCODE() {
		return SUBCATCODE;
	}

	public void setSUBCATCODE(String sUBCATCODE) {
		SUBCATCODE = sUBCATCODE;
	}

	public String getSUBCATNAME() {
		return SUBCATNAME;
	}

	public void setSUBCATNAME(String sUBCATNAME) {
		SUBCATNAME = sUBCATNAME;
	}

	public String getCATCODE() {
		return CATCODE;
	}

	public void setCATCODE(String cATCODE) {
		CATCODE = cATCODE;
	}

	public String getCATNAME() {
		return CATNAME;
	}

	public void setCATNAME(String cATNAME) {
		CATNAME = cATNAME;
	}

	public String getMAINCATCODE() {
		return MAINCATCODE;
	}

	public void setMAINCATCODE(String mAINCATCODE) {
		MAINCATCODE = mAINCATCODE;
	}

	public String getMAINCATNAME() {
		return MAINCATNAME;
	}

	public void setMAINCATNAME(String mAINCATNAME) {
		MAINCATNAME = mAINCATNAME;
	}

	public String getDIAGNOSISCODE() {
		return DIAGNOSISCODE;
	}

	public void setDIAGNOSISCODE(String dIAGNOSISCODE) {
		DIAGNOSISCODE = dIAGNOSISCODE;
	}

	public String getDIAGNOSISNAME() {
		return DIAGNOSISNAME;
	}

	public void setDIAGNOSISNAME(String dIAGNOSISNAME) {
		DIAGNOSISNAME = dIAGNOSISNAME;
	}

	public String getHOSPID() {
		return HOSPID;
	}

	public void setHOSPID(String hOSPID) {
		HOSPID = hOSPID;
	}

	public String getHOUSENO() {
		return HOUSENO;
	}

	public void setHOUSENO(String hOUSENO) {
		HOUSENO = hOUSENO;
	}

	public String getUNITNAME() {
		return UNITNAME;
	}

	public void setUNITNAME(String uNITNAME) {
		UNITNAME = uNITNAME;
	}

	public String getHOSPTYPE() {
		return HOSPTYPE;
	}

	public void setHOSPTYPE(String hOSPTYPE) {
		HOSPTYPE = hOSPTYPE;
	}

	public String getHOSPEMAIL() {
		return HOSPEMAIL;
	}

	public void setHOSPEMAIL(String hOSPEMAIL) {
		HOSPEMAIL = hOSPEMAIL;
	}

	public String getDisMainId() {
		return disMainId;
	}

	public void setDisMainId(String disMainId) {
		this.disMainId = disMainId;
	}

	public String getNEWEMPCODE() {
		return NEWEMPCODE;
	}

	public void setNEWEMPCODE(String nEWEMPCODE) {
		NEWEMPCODE = nEWEMPCODE;
	}

	public String getPARENT_UNITID() {
		return PARENT_UNITID;
	}

	public void setPARENT_UNITID(String pARENT_UNITID) {
		PARENT_UNITID = pARENT_UNITID;
	}

	public String getPATH() {
		return PATH;
	}

	public void setPATH(String pATH) {
		PATH = pATH;
	}

	public String getAADHARID() {
		return AADHARID;
	}

	public void setAADHARID(String aADHARID) {
		AADHARID = aADHARID;
	}

	public String getAADHAREID() {
		return AADHAREID;
	}

	public void setAADHAREID(String aADHAREID) {
		AADHAREID = aADHAREID;
	}

	public String getDISNAME() {
		return DISNAME;
	}

	public void setDISNAME(String dISNAME) {
		DISNAME = dISNAME;
	}

	public String getSCHEMENAME() {
		return SCHEMENAME;
	}

	public void setSCHEMENAME(String sCHEMENAME) {
		SCHEMENAME = sCHEMENAME;
	}

	public String getUNITTYPE() {
		return UNITTYPE;
	}

	public void setUNITTYPE(String uNITTYPE) {
		UNITTYPE = uNITTYPE;
	}

	public String getJOBID() {
		return JOBID;
	}

	public void setJOBID(String jOBID) {
		JOBID = jOBID;
	}

	public String getEMPHNO() {
		return EMPHNO;
	}

	public void setEMPHNO(String eMPHNO) {
		EMPHNO = eMPHNO;
	}

	public String getEMPSTRTNO() {
		return EMPSTRTNO;
	}

	public void setEMPSTRTNO(String eMPSTRTNO) {
		EMPSTRTNO = eMPSTRTNO;
	}

	public String getEMPRESVLGE() {
		return EMPRESVLGE;
	}

	public void setEMPRESVLGE(String eMPRESVLGE) {
		EMPRESVLGE = eMPRESVLGE;
	}

	public String getEMPRESMDL() {
		return EMPRESMDL;
	}

	public void setEMPRESMDL(String eMPRESMDL) {
		EMPRESMDL = eMPRESMDL;
	}

	public String getEMPRESDIST() {
		return EMPRESDIST;
	}

	public void setEMPRESDIST(String eMPRESDIST) {
		EMPRESDIST = eMPRESDIST;
	}

	public String getTYPE() {
		return TYPE;
	}

	public void setTYPE(String tYPE) {
		TYPE = tYPE;
	}

	public String getTYPE1() {
		return TYPE1;
	}

	public void setTYPE1(String tYPE1) {
		TYPE1 = tYPE1;
	}

	public String getTYPE2() {
		return TYPE2;
	}

	public void setTYPE2(String tYPE2) {
		TYPE2 = tYPE2;
	}

	public String getTYPE3() {
		return TYPE3;
	}

	public void setTYPE3(String tYPE3) {
		TYPE3 = tYPE3;
	}

	public String getPOSTCODE() {
		return POSTCODE;
	}

	public void setPOSTCODE(String pOSTCODE) {
		POSTCODE = pOSTCODE;
	}

	public String getEMPIDM1() {
		return EMPIDM1;
	}

	public void setEMPIDM1(String eMPIDM1) {
		EMPIDM1 = eMPIDM1;
	}

	public String getEMPIDM2() {
		return EMPIDM2;
	}

	public void setEMPIDM2(String eMPIDM2) {
		EMPIDM2 = eMPIDM2;
	}

	public String getEMPOFCHNO() {
		return EMPOFCHNO;
	}

	public void setEMPOFCHNO(String eMPOFCHNO) {
		EMPOFCHNO = eMPOFCHNO;
	}

	public String getEMPOFCSTRTNO() {
		return EMPOFCSTRTNO;
	}

	public void setEMPOFCSTRTNO(String eMPOFCSTRTNO) {
		EMPOFCSTRTNO = eMPOFCSTRTNO;
	}

	public String getEMPOFCVLGE() {
		return EMPOFCVLGE;
	}

	public void setEMPOFCVLGE(String eMPOFCVLGE) {
		EMPOFCVLGE = eMPOFCVLGE;
	}

	public String getEMPOFCDIST() {
		return EMPOFCDIST;
	}

	public void setEMPOFCDIST(String eMPOFCDIST) {
		EMPOFCDIST = eMPOFCDIST;
	}

	public String getEMPOFCMDL() {
		return EMPOFCMDL;
	}

	public void setEMPOFCMDL(String eMPOFCMDL) {
		EMPOFCMDL = eMPOFCMDL;
	}

	public String getEmpOstate() {
		return empOstate;
	}

	public void setEmpOstate(String empOstate) {
		this.empOstate = empOstate;
	}

	public String getEMPOFCPH() {
		return EMPOFCPH;
	}

	public void setEMPOFCPH(String eMPOFCPH) {
		EMPOFCPH = eMPOFCPH;
	}

	public String getEMPOFCMAIL() {
		return EMPOFCMAIL;
	}

	public void setEMPOFCMAIL(String eMPOFCMAIL) {
		EMPOFCMAIL = eMPOFCMAIL;
	}

	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public String getTokenNo() {
		return tokenNo;
	}

	public void setTokenNo(String tokenNo) {
		this.tokenNo = tokenNo;
	}

	public Number getUNITAMOUNT() {
		return UNITAMOUNT;
	}

	public void setUNITAMOUNT(Number uNITAMOUNT) {
		UNITAMOUNT = uNITAMOUNT;
	}

	public String getDRUGUNITAMOUNT() {
		return DRUGUNITAMOUNT;
	}

	public void setDRUGUNITAMOUNT(String dRUGUNITAMOUNT) {
		DRUGUNITAMOUNT = dRUGUNITAMOUNT;
	}

	public Number getAMOUNT() {
		return AMOUNT;
	}

	public void setAMOUNT(Number aMOUNT) {
		AMOUNT = aMOUNT;
	}

	public Number getTOTCONSUMABLEAMOUNT() {
		return TOTCONSUMABLEAMOUNT;
	}

	public void setTOTCONSUMABLEAMOUNT(Number tOTCONSUMABLEAMOUNT) {
		TOTCONSUMABLEAMOUNT = tOTCONSUMABLEAMOUNT;
	}

	public Number getTOTALDRUGAMT() {
		return TOTALDRUGAMT;
	}

	public void setTOTALDRUGAMT(Number tOTALDRUGAMT) {
		TOTALDRUGAMT = tOTALDRUGAMT;
	}

	public String getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getEXPIRYDATE() {
		return EXPIRYDATE;
	}

	public void setEXPIRYDATE(Date eXPIRYDATE) {
		EXPIRYDATE = eXPIRYDATE;
	}

	public String getDrugType() {
		return drugType;
	}

	public void setDrugType(String drugType) {
		this.drugType = drugType;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public String getNewDrugName() {
		return newDrugName;
	}

	public void setNewDrugName(String newDrugName) {
		this.newDrugName = newDrugName;
	}

	public String getManufaturerName() {
		return manufaturerName;
	}

	public void setManufaturerName(String manufaturerName) {
		this.manufaturerName = manufaturerName;
	}

	public String getDistributerName() {
		return distributerName;
	}

	public void setDistributerName(String distributerName) {
		this.distributerName = distributerName;
	}

	public double getDrugPrice() {
		return drugPrice;
	}

	public void setDrugPrice(double drugPrice) {
		this.drugPrice = drugPrice;
	}

	public String getDRUGPRICE() {
		return DRUGPRICE;
	}

	public void setDRUGPRICE(String dRUGPRICE) {
		DRUGPRICE = dRUGPRICE;
	}

	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}

	public String getMEDFLG() {
		return MEDFLG;
	}

	public void setMEDFLG(String mEDFLG) {
		MEDFLG = mEDFLG;
	}

	public String getExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}

	public String getWELLNESSCENTER() {
		return WELLNESSCENTER;
	}

	public void setWELLNESSCENTER(String wELLNESSCENTER) {
		WELLNESSCENTER = wELLNESSCENTER;
	}

	public String getSEARCHTYPE() {
		return SEARCHTYPE;
	}

	public void setSEARCHTYPE(String sEARCHTYPE) {
		SEARCHTYPE = sEARCHTYPE;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Date getINDENT_DT() {
		return INDENT_DT;
	}

	public void setINDENT_DT(Date iNDENT_DT) {
		INDENT_DT = iNDENT_DT;
	}

	public String getDRUGID() {
		return DRUGID;
	}

	public void setDRUGID(String dRUGID) {
		DRUGID = dRUGID;
	}

	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public List<LabelBean> getDentaldetails() {
		return dentaldetails;
	}

	public void setDentaldetails(List<LabelBean> dentaldetails) {
		this.dentaldetails = dentaldetails;
	}

	public String getADDRESSOFESTABLISHMENT() {
		return ADDRESSOFESTABLISHMENT;
	}

	public void setADDRESSOFESTABLISHMENT(String aDDRESSOFESTABLISHMENT) {
		ADDRESSOFESTABLISHMENT = aDDRESSOFESTABLISHMENT;
	}

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

	public String getHOSPNAME() {
		return HOSPNAME;
	}

	public void setHOSPNAME(String hOSPNAME) {
		HOSPNAME = hOSPNAME;
	}

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

	public String getCARDNO() {
		return CARDNO;
	}

	public void setCARDNO(String cARDNO) {
		CARDNO = cARDNO;
	}

	public String getWellnesslist() {
		return wellnesslist;
	}

	public void setWellnesslist(String wellnesslist) {
		this.wellnesslist = wellnesslist;
	}

	public String getDSTRBTRID() {
		return DSTRBTRID;
	}

	public void setDSTRBTRID(String dSTRBTRID) {
		DSTRBTRID = dSTRBTRID;
	}
	
	//pravalika CR8601 
	public String getRCP() {
		return RCP;
	}
	public void setRCP(String rCP) {
		RCP = rCP;
	}
	public List<LabelBean> getDispnsryList() {
		return dispnsryList;
	}
	public void setDispnsryList(List<LabelBean> dispnsryList) {
		this.dispnsryList = dispnsryList;
	}
	//Chandana - 9008 - Getters and Setters
	public String getCRITICAL_FLG() {
		return CRITICAL_FLG;
	}
	public void setCRITICAL_FLG(String cRITICAL_FLG) {
		CRITICAL_FLG = cRITICAL_FLG;
	}
	public String getNO_CRTICALINDNT_ITEMS() {
		return NO_CRTICALINDNT_ITEMS;
	}
	public void setNO_CRTICALINDNT_ITEMS(String nO_CRTICALINDNT_ITEMS) {
		NO_CRTICALINDNT_ITEMS = nO_CRTICALINDNT_ITEMS;
	}
	public String getTOT_CRTICALINDNT_VALUE() {
		return TOT_CRTICALINDNT_VALUE;
	}
	public void setTOT_CRTICALINDNT_VALUE(String tOT_CRTICALINDNT_VALUE) {
		TOT_CRTICALINDNT_VALUE = tOT_CRTICALINDNT_VALUE;
	}
	public String getNO_NONCINDENT_ITEMS() {
		return NO_NONCINDENT_ITEMS;
	}
	public void setNO_NONCINDENT_ITEMS(String nO_NONCINDENT_ITEMS) {
		NO_NONCINDENT_ITEMS = nO_NONCINDENT_ITEMS;
	}
	public String getTOT_NONCINDENT_VALUE() {
		return TOT_NONCINDENT_VALUE;
	}
	public void setTOT_NONCINDENT_VALUE(String tOT_NONCINDENT_VALUE) {
		TOT_NONCINDENT_VALUE = tOT_NONCINDENT_VALUE;
	}
	public String getORD_FLG() {
		return ORD_FLG;
	}
	public void setORD_FLG(String oRD_FLG) {
		ORD_FLG = oRD_FLG;
	}

}
