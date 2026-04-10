package com.ahct.reports.vo;

import java.io.Serializable;


public class PanelDocPayVO implements Serializable{
	private static final long serialVersionUID = 1L;
	private String userId;
	private String ID;
	private String EMPNAME;
	private String DOC_NAME;
	private String DOC_ID;
	private String CL_APP_AMT;
	private String CL_PEND_AMT;
	private String CL_REJ_AMT;
	private String PR_APP_AMT;
	private String PR_PEND_AMT;
	private String PR_REJ_AMT;
	private String AMOUNT;
	private String selperiod;
	private String fromDate;
	private String toDate;
	private String dispType;
	private String CASE_DATE;
	private String CASE_ID;
	private String HOSP_ID;
	private String HOSP_NAME;
	private String PARTICULARS;
	private String actionType;
	private String result;
	private String CASE_STATUS;
	private String DISTINCTSTATUS;
	private String COUNT;
	private String LEVELLIST;
	private String CL_APP_STATUS;
	private String CL_PEND_STATUS;
	private String CL_REJ_STATUS;
	private String PR_APP_STATUS;
	private String PR_PEND_STATUS;
	private String PR_REJ_STATUS;
	private String REMARKS;
	private String CURRWRKFLWID;
	private String prevWrkflowId;
	private String prevOwnr;
	private String currWrkflowId;
	private String currOwnr;
	private String BANKNAME;
	private String BANKBRANCH;
	private String BANKCATEGORY;
	private String BANKIFSC;
	private String BANKID;
	private String ACCOUNTNO;
	private String ACCNAME;
	private String PANELDOCAMT;
	private String TDSAMT;
	private double SurchargeAmt;
	private double CessAmt;
	private double TaxAmt;
	private String tdsActive;
	private String caseSetId;
	private String tdsPaymentId;
	private String SRCACCNO;
	private String DESTACCNO;
	private String TDSID;
	private String ADDRESS;
	private String failedList;
	private String REGCODE;
	private String WRKFLWSETID;
	private String flag;
	private String FILEID;
	private String USERTYPE;
	private String PAYMENTID;
	private Number WORKFLW_SET_ID;
	private String TRANSACTIONID;
	private String STATUSDATE;
	
	private String CL_PEND_UPTD_STATUS;
	private String CL_PEND_UPTD_AMT;
	
	//added by bhaskar
	private String HOLD_PENDING;
	private String HOLD_RELEASED;
	private String HOLD_PENDING_AMT;
	private String HOLD_RELEASED_AMT;
	//end by bhaskar
	public String getCL_PEND_UPTD_STATUS() {
		return CL_PEND_UPTD_STATUS;
	}
	public void setCL_PEND_UPTD_STATUS(String cL_PEND_UPTD_STATUS) {
		CL_PEND_UPTD_STATUS = cL_PEND_UPTD_STATUS;
	}
	public String getCL_PEND_UPTD_AMT() {
		return CL_PEND_UPTD_AMT;
	}
	public void setCL_PEND_UPTD_AMT(String cL_PEND_UPTD_AMT) {
		CL_PEND_UPTD_AMT = cL_PEND_UPTD_AMT;
	}
	public String getCL_APP_STATUS() {
		return CL_APP_STATUS;
	}
	public void setCL_APP_STATUS(String cL_APP_STATUS) {
		CL_APP_STATUS = cL_APP_STATUS;
	}
	public String getCL_PEND_STATUS() {
		return CL_PEND_STATUS;
	}
	public void setCL_PEND_STATUS(String cL_PEND_STATUS) {
		CL_PEND_STATUS = cL_PEND_STATUS;
	}
	public String getCL_REJ_STATUS() {
		return CL_REJ_STATUS;
	}
	public void setCL_REJ_STATUS(String cL_REJ_STATUS) {
		CL_REJ_STATUS = cL_REJ_STATUS;
	}
	public String getPR_APP_STATUS() {
		return PR_APP_STATUS;
	}
	public void setPR_APP_STATUS(String pR_APP_STATUS) {
		PR_APP_STATUS = pR_APP_STATUS;
	}
	public String getPR_PEND_STATUS() {
		return PR_PEND_STATUS;
	}
	public void setPR_PEND_STATUS(String pR_PEND_STATUS) {
		PR_PEND_STATUS = pR_PEND_STATUS;
	}
	public String getPR_REJ_STATUS() {
		return PR_REJ_STATUS;
	}
	public void setPR_REJ_STATUS(String pR_REJ_STATUS) {
		PR_REJ_STATUS = pR_REJ_STATUS;
	}
	public Number getWORKFLW_SET_ID() {
		return WORKFLW_SET_ID;
	}
	public void setWORKFLW_SET_ID(Number wORKFLW_SET_ID) {
		WORKFLW_SET_ID = wORKFLW_SET_ID;
	}
	public String getUSERTYPE() {
		return USERTYPE;
	}
	public void setUSERTYPE(String uSERTYPE) {
		USERTYPE = uSERTYPE;
	}
	
	public double getSurchargeAmt() {
		return SurchargeAmt;
	}
	public void setSurchargeAmt(double surchargeAmt) {
		SurchargeAmt = surchargeAmt;
	}
	public double getCessAmt() {
		return CessAmt;
	}
	public void setCessAmt(double cessAmt) {
		CessAmt = cessAmt;
	}
	public double getTaxAmt() {
		return TaxAmt;
	}
	public void setTaxAmt(double taxAmt) {
		TaxAmt = taxAmt;
	}
	public String getBANKNAME() {
		return BANKNAME;
	}
	public void setBANKNAME(String bANKNAME) {
		BANKNAME = bANKNAME;
	}
	public String getBANKBRANCH() {
		return BANKBRANCH;
	}
	public void setBANKBRANCH(String bANKBRANCH) {
		BANKBRANCH = bANKBRANCH;
	}
	public String getBANKCATEGORY() {
		return BANKCATEGORY;
	}
	public void setBANKCATEGORY(String bANKCATEGORY) {
		BANKCATEGORY = bANKCATEGORY;
	}
	public String getBANKIFSC() {
		return BANKIFSC;
	}
	public void setBANKIFSC(String bANKIFSC) {
		BANKIFSC = bANKIFSC;
	}
	public String getBANKID() {
		return BANKID;
	}
	public void setBANKID(String bANKID) {
		BANKID = bANKID;
	}
	public String getACCOUNTNO() {
		return ACCOUNTNO;
	}
	public void setACCOUNTNO(String aCCOUNTNO) {
		ACCOUNTNO = aCCOUNTNO;
	}
	public String getACCNAME() {
		return ACCNAME;
	}
	public void setACCNAME(String aCCNAME) {
		ACCNAME = aCCNAME;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getEMPNAME() {
		return EMPNAME;
	}
	public void setEMPNAME(String eMPNAME) {
		EMPNAME = eMPNAME;
	}
	public String getDOC_NAME() {
		return DOC_NAME;
	}
	public void setDOC_NAME(String dOC_NAME) {
		DOC_NAME = dOC_NAME;
	}
	public String getDOC_ID() {
		return DOC_ID;
	}
	public void setDOC_ID(String dOC_ID) {
		DOC_ID = dOC_ID;
	}
	public String getCL_APP_AMT() {
		return CL_APP_AMT;
	}
	public void setCL_APP_AMT(String cL_APP_AMT) {
		CL_APP_AMT = cL_APP_AMT;
	}
	public String getCL_PEND_AMT() {
		return CL_PEND_AMT;
	}
	public void setCL_PEND_AMT(String cL_PEND_AMT) {
		CL_PEND_AMT = cL_PEND_AMT;
	}
	public String getCL_REJ_AMT() {
		return CL_REJ_AMT;
	}
	public void setCL_REJ_AMT(String cL_REJ_AMT) {
		CL_REJ_AMT = cL_REJ_AMT;
	}
	public String getPR_APP_AMT() {
		return PR_APP_AMT;
	}
	public void setPR_APP_AMT(String pR_APP_AMT) {
		PR_APP_AMT = pR_APP_AMT;
	}
	public String getPR_PEND_AMT() {
		return PR_PEND_AMT;
	}
	public void setPR_PEND_AMT(String pR_PEND_AMT) {
		PR_PEND_AMT = pR_PEND_AMT;
	}
	public String getPR_REJ_AMT() {
		return PR_REJ_AMT;
	}
	public void setPR_REJ_AMT(String pR_REJ_AMT) {
		PR_REJ_AMT = pR_REJ_AMT;
	}
	public String getAMOUNT() {
		return AMOUNT;
	}
	public void setAMOUNT(String aMOUNT) {
		AMOUNT = aMOUNT;
	}
	public String getSelperiod() {
		return selperiod;
	}
	public void setSelperiod(String selperiod) {
		this.selperiod = selperiod;
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
	public String getDispType() {
		return dispType;
	}
	public String getActionType() {
		return actionType;
	}
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	public void setDispType(String dispType) {
		this.dispType = dispType;
	}
	public String getCASE_DATE() {
		return CASE_DATE;
	}
	public void setCASE_DATE(String cASE_DATE) {
		CASE_DATE = cASE_DATE;
	}
	public String getCASE_ID() {
		return CASE_ID;
	}
	public void setCASE_ID(String cASE_ID) {
		CASE_ID = cASE_ID;
	}
	
	public String getHOSP_ID() {
		return HOSP_ID;
	}
	public void setHOSP_ID(String hOSP_ID) {
		HOSP_ID = hOSP_ID;
	}
	public String getHOSP_NAME() {
		return HOSP_NAME;
	}
	public void setHOSP_NAME(String hOSP_NAME) {
		HOSP_NAME = hOSP_NAME;
	}
	public String getPARTICULARS() {
		return PARTICULARS;
	}
	public void setPARTICULARS(String pARTICULARS) {
		PARTICULARS = pARTICULARS;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getDISTINCTSTATUS() {
		return DISTINCTSTATUS;
	}
	public void setDISTINCTSTATUS(String DISTINCTSTATUS) {
		this.DISTINCTSTATUS = DISTINCTSTATUS;
	}
	public String getCOUNT() {
		return COUNT;
	}
	public void setCOUNT(String COUNT) {
		this.COUNT = COUNT;
	}
	public String getLEVELLIST() {
		return LEVELLIST;
	}
	public void setLEVELLIST(String lEVELLIST) {
		LEVELLIST = lEVELLIST;
	}
	
	
	public String getCURRWRKFLWID() {
		return CURRWRKFLWID;
	}
	public void setCURRWRKFLWID(String cURRWRKFLWID) {
		CURRWRKFLWID = cURRWRKFLWID;
	}
	
	public String getPrevWrkflowId() {
		return prevWrkflowId;
	}
	public void setPrevWrkflowId(String prevWrkflowId) {
		this.prevWrkflowId = prevWrkflowId;
	}
	public String getPrevOwnr() {
		return prevOwnr;
	}
	public void setPrevOwnr(String prevOwnr) {
		this.prevOwnr = prevOwnr;
	}
	public String getCurrWrkflowId() {
		return currWrkflowId;
	}
	public void setCurrWrkflowId(String currWrkflowId) {
		this.currWrkflowId = currWrkflowId;
	}
	public String getCurrOwnr() {
		return currOwnr;
	}
	public void setCurrOwnr(String currOwnr) {
		this.currOwnr = currOwnr;
	}
	public String getTdsActive() {
		return tdsActive;
	}
	public void setTdsActive(String tdsActive) {
		this.tdsActive = tdsActive;
	}
	public String getCaseSetId() {
		return caseSetId;
	}
	public void setCaseSetId(String caseSetId) {
		this.caseSetId = caseSetId;
	}
	public String getTdsPaymentId() {
		return tdsPaymentId;
	}
	public void setTdsPaymentId(String tdsPaymentId) {
		this.tdsPaymentId = tdsPaymentId;
	}
	public String getSRCACCNO() {
		return SRCACCNO;
	}
	public void setSRCACCNO(String sRCACCNO) {
		SRCACCNO = sRCACCNO;
	}
	public String getDESTACCNO() {
		return DESTACCNO;
	}
	public void setDESTACCNO(String dESTACCNO) {
		DESTACCNO = dESTACCNO;
	}
	public String getTDSID() {
		return TDSID;
	}
	public void setTDSID(String tDSID) {
		TDSID = tDSID;
	}
	public String getADDRESS() {
		return ADDRESS;
	}
	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}
	public String getFailedList() {
		return failedList;
	}
	public void setFailedList(String failedList) {
		this.failedList = failedList;
	}
	public String getREGCODE() {
		return REGCODE;
	}
	public void setREGCODE(String rEGCODE) {
		REGCODE = rEGCODE;
	}
	public String getWRKFLWSETID() {
		return WRKFLWSETID;
	}
	public void setWRKFLWSETID(String wRKFLWSETID) {
		WRKFLWSETID = wRKFLWSETID;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getFILEID() {
		return FILEID;
	}
	public void setFILEID(String fILEID) {
		FILEID = fILEID;
	}
	
	public String getCASE_STATUS() {
		return CASE_STATUS;
	}
	public void setCASE_STATUS(String cASE_STATUS) {
		CASE_STATUS = cASE_STATUS;
	}
	public String getPAYMENTID() {
		return PAYMENTID;
	}
	public void setPAYMENTID(String pAYMENTID) {
		PAYMENTID = pAYMENTID;
	}

	public String getTRANSACTIONID() {
		return TRANSACTIONID;
	}
	public void setTRANSACTIONID(String tRANSACTIONID) {
		TRANSACTIONID = tRANSACTIONID;
	}
	public String getSTATUSDATE() {
		return STATUSDATE;
	}
	public void setSTATUSDATE(String sTATUSDATE) {
		STATUSDATE = sTATUSDATE;
	}
	public String getREMARKS() {
		return REMARKS;
	}
	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}
	public String getTDSAMT() {
		return TDSAMT;
	}
	public void setTDSAMT(String tDSAMT) {
		TDSAMT = tDSAMT;
	}
	public String getPANELDOCAMT() {
		return PANELDOCAMT;
	}
	public void setPANELDOCAMT(String pANELDOCAMT) {
		PANELDOCAMT = pANELDOCAMT;
	}
	public String getHOLD_PENDING() {
		return HOLD_PENDING;
	}
	public void setHOLD_PENDING(String hOLD_PENDING) {
		HOLD_PENDING = hOLD_PENDING;
	}
	public String getHOLD_RELEASED() {
		return HOLD_RELEASED;
	}
	public void setHOLD_RELEASED(String hOLD_RELEASED) {
		HOLD_RELEASED = hOLD_RELEASED;
	}
	public String getHOLD_PENDING_AMT() {
		return HOLD_PENDING_AMT;
	}
	public void setHOLD_PENDING_AMT(String hOLD_PENDING_AMT) {
		HOLD_PENDING_AMT = hOLD_PENDING_AMT;
	}
	public String getHOLD_RELEASED_AMT() {
		return HOLD_RELEASED_AMT;
	}
	public void setHOLD_RELEASED_AMT(String hOLD_RELEASED_AMT) {
		HOLD_RELEASED_AMT = hOLD_RELEASED_AMT;
	}
	
	
	
	
}
