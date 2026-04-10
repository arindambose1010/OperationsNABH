package com.ahct.patient.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;

@SuppressWarnings("serial")
public class DrugsVO implements Serializable{

	private Long drugId;
	private String drugTypeName;
	private String drugSubTypeName;
	private String drugName;
	private String drugTypeCode;
	private String drugSubTypeCode;
	private String drugCode;
	private String route;
	private String strength;
	private String dosage;
	private String medicationPeriod;
	private String issueFromDt;
	private String issueToDt;
	private String noOfUnit;
	private String amountPerUnit;
	private String totAmt;
	private String pSubGrpName;
	private String pSubGrpCode;
	private String cSubGrpName;
	private String cSubGrpCode;
	private String routeType;
	private String routeTypeName;
	private String strengthType;
	private String strengthTypeName;
	private String routeName;
	private String strengthName;
	private String clinicalId;
	private String installment;
	private String batchNo;
	private String expiryDt;
	private Date expDt;
	private String opPkgCode;
	private String otherDrugName;
	private String quantity;
	private String avlbQuantity;
	
	
	private Number DRUGID;
	private String DRUGTYPECODE;
	private String DRUGSUBTYPECODE;
	private String PSUBGRPCODE;
	private String CSUBGRPCODE;
	private String DRUGCODE;
	private String DRUGTYPENAME;
	private String DRUGSUBTYPENAME;
	private String PSUBGRPNAME;
	private String CSUBGRPNAME;
	private String DRUGNAME;
	private String ROUTETYPE;
	private String ROUTETYPENAME;
	private String ROUTE;
	private String ROUTENAME;
	private String STRENGTHTYPE;
	private String STRENGTHTYPENAME;
	private String STRENGTH;
	private String STRENGTHNAME;
	private String DOSAGE;
	private String MEDICATIONPERIOD;
	private String OTHERDRUGNAME;
	private String EXPIRYDATE;
	private String QUANTITY;
	private List<DrugsVO> drugList;
	
	private String DRUGTYPECODEOT;
	private String DRUGSUBTYPECODEOT;
	private String OTHERDRUGNAMEOT;
	private String ROUTETYPEOT;
	private String ROUTETYPENAMEOT;
	private String ROUTEOT;
	private String ROUTENAMEOT;
	private String STRENGTHTYPEOT;
	private String STRENGTHTYPENAMEOT;
	private String STRENGTHOT;
	private String STRENGTHNAMEOT;
	private String DOSAGEOT;
	private String MEDICATIONPERIODOT;
	private String PSUBGRPCODEOT;
	private String CSUBGRPCODEOT;
	private String DRUGCODEOT;
	private String DRUGNAMEOT;
	private String QUANTITYOT;
	private String DRUGSUBTYPENAMEOT;
	private String dispDrugMstrDrgCode;
	private String DISPDRUGMSTRDRGCODE;
	private Long outDrugsQuantity;
	private String dispDrugMfg;
	private String dispDrugDsbtr;
	private List<DrugsVO> mftrs;
	private String mftrName;
	private String mftrId;
	private String mftrLst;
	private String dbtr;
	
	private String crtDt;
	private String issuedQuant;
	
	private String docQuantity;
	private Long totQuant;
	private String invoiceNo;
	private String drugValues;
	private String newDrugName;
	private List<DrugsVO> batchNos;
	private String batchLst;
	private String totQuantity;
	
		public String getNewDrugName() {
		return newDrugName;
	}
	public void setNewDrugName(String newDrugName) {
		this.newDrugName = newDrugName;
	}
		public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	
	public String getDocQuantity() {
		return docQuantity;
	}
	public void setDocQuantity(String docQuantity) {
		this.docQuantity = docQuantity;
	}
	public Long getTotQuant() {
		return totQuant;
	}
	public void setTotQuant(Long totQuant) {
		this.totQuant = totQuant;
	}
	public String getMftrName() {
		return mftrName;
	}
	public void setMftrName(String mftrName) {
		this.mftrName = mftrName;
	}
	public String getMftrId() {
		return mftrId;
	}
	public void setMftrId(String mftrId) {
		this.mftrId = mftrId;
	}
	public String getClinicalId() {
		return clinicalId;
	}
	public void setClinicalId(String clinicalId) {
		this.clinicalId = clinicalId;
	}
	public String getNoOfUnit() {
		return noOfUnit;
	}
	public void setNoOfUnit(String noOfUnit) {
		this.noOfUnit = noOfUnit;
	}
	public String getAmountPerUnit() {
		return amountPerUnit;
	}
	public void setAmountPerUnit(String amountPerUnit) {
		this.amountPerUnit = amountPerUnit;
	}
	public String getTotAmt() {
		return totAmt;
	}
	public void setTotAmt(String totAmt) {
		this.totAmt = totAmt;
	}
	public Long getDrugId() {
		return drugId;
	}
	public void setDrugId(Long drugId) {
		this.drugId = drugId;
	}
	public String getDrugTypeName() {
		return drugTypeName;
	}
	public void setDrugTypeName(String drugTypeName) {
		this.drugTypeName = drugTypeName;
	}
	public String getDrugSubTypeName() {
		return drugSubTypeName;
	}
	public void setDrugSubTypeName(String drugSubTypeName) {
		this.drugSubTypeName = drugSubTypeName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getDosage() {
		return dosage;
	}
	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	public String getMedicationPeriod() {
		return medicationPeriod;
	}
	public void setMedicationPeriod(String medicationPeriod) {
		this.medicationPeriod = medicationPeriod;
	}
	public String getIssueFromDt() {
		return issueFromDt;
	}
	public void setIssueFromDt(String issueFromDt) {
		this.issueFromDt = issueFromDt;
	}
	public String getIssueToDt() {
		return issueToDt;
	}
	public void setIssueToDt(String issueToDt) {
		this.issueToDt = issueToDt;
	}
	public String getDrugTypeCode() {
		return drugTypeCode;
	}
	public void setDrugTypeCode(String drugTypeCode) {
		this.drugTypeCode = drugTypeCode;
	}
	public String getDrugSubTypeCode() {
		return drugSubTypeCode;
	}
	public void setDrugSubTypeCode(String drugSubTypeCode) {
		this.drugSubTypeCode = drugSubTypeCode;
	}
	public String getDrugCode() {
		return drugCode;
	}
	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	public String getpSubGrpName() {
		return pSubGrpName;
	}
	public void setpSubGrpName(String pSubGrpName) {
		this.pSubGrpName = pSubGrpName;
	}
	public String getpSubGrpCode() {
		return pSubGrpCode;
	}
	public void setpSubGrpCode(String pSubGrpCode) {
		this.pSubGrpCode = pSubGrpCode;
	}
	public String getcSubGrpName() {
		return cSubGrpName;
	}
	public void setcSubGrpName(String cSubGrpName) {
		this.cSubGrpName = cSubGrpName;
	}
	public String getcSubGrpCode() {
		return cSubGrpCode;
	}
	public void setcSubGrpCode(String cSubGrpCode) {
		this.cSubGrpCode = cSubGrpCode;
	}
	public String getRouteType() {
		return routeType;
	}
	public void setRouteType(String routeType) {
		this.routeType = routeType;
	}
	public String getStrengthType() {
		return strengthType;
	}
	public void setStrengthType(String strengthType) {
		this.strengthType = strengthType;
	}
	public String getRouteTypeName() {
		return routeTypeName;
	}
	public void setRouteTypeName(String routeTypeName) {
		this.routeTypeName = routeTypeName;
	}
	public String getStrengthTypeName() {
		return strengthTypeName;
	}
	public void setStrengthTypeName(String strengthTypeName) {
		this.strengthTypeName = strengthTypeName;
	}
	public String getRouteName() {
		return routeName;
	}
	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}
	public String getStrengthName() {
		return strengthName;
	}
	public void setStrengthName(String strengthName) {
		this.strengthName = strengthName;
	}
	public String getInstallment() {
		return installment;
	}
	public void setInstallment(String installment) {
		this.installment = installment;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	public String getExpiryDt() {
		return expiryDt;
	}
	public void setExpiryDt(String expiryDt) {
		this.expiryDt = expiryDt;
	}
	public Date getExpDt() {
		return expDt;
	}
	public void setExpDt(Date expDt) {
		this.expDt = expDt;
	}
	public String getOpPkgCode() {
		return opPkgCode;
	}
	public void setOpPkgCode(String opPkgCode) {
		this.opPkgCode = opPkgCode;
	}
	public String getOtherDrugName() {
		return otherDrugName;
	}
	public void setOtherDrugName(String otherDrugName) {
		this.otherDrugName = otherDrugName;
	}
	public String getDRUGTYPECODE() {
		return DRUGTYPECODE;
	}
	public void setDRUGTYPECODE(String dRUGTYPECODE) {
		DRUGTYPECODE = dRUGTYPECODE;
	}
	public String getDRUGSUBTYPECODE() {
		return DRUGSUBTYPECODE;
	}
	public void setDRUGSUBTYPECODE(String dRUGSUBTYPECODE) {
		DRUGSUBTYPECODE = dRUGSUBTYPECODE;
	}
	public String getPSUBGRPCODE() {
		return PSUBGRPCODE;
	}
	public void setPSUBGRPCODE(String pSUBGRPCODE) {
		PSUBGRPCODE = pSUBGRPCODE;
	}
	public String getCSUBGRPCODE() {
		return CSUBGRPCODE;
	}
	public void setCSUBGRPCODE(String cSUBGRPCODE) {
		CSUBGRPCODE = cSUBGRPCODE;
	}
	public String getDRUGCODE() {
		return DRUGCODE;
	}
	public void setDRUGCODE(String dRUGCODE) {
		DRUGCODE = dRUGCODE;
	}
	public String getDRUGTYPENAME() {
		return DRUGTYPENAME;
	}
	public void setDRUGTYPENAME(String dRUGTYPENAME) {
		DRUGTYPENAME = dRUGTYPENAME;
	}
	public String getDRUGSUBTYPENAME() {
		return DRUGSUBTYPENAME;
	}
	public void setDRUGSUBTYPENAME(String dRUGSUBTYPENAME) {
		DRUGSUBTYPENAME = dRUGSUBTYPENAME;
	}
	public String getPSUBGRPNAME() {
		return PSUBGRPNAME;
	}
	public void setPSUBGRPNAME(String pSUBGRPNAME) {
		PSUBGRPNAME = pSUBGRPNAME;
	}
	public String getCSUBGRPNAME() {
		return CSUBGRPNAME;
	}
	public void setCSUBGRPNAME(String cSUBGRPNAME) {
		CSUBGRPNAME = cSUBGRPNAME;
	}
	public String getDRUGNAME() {
		return DRUGNAME;
	}
	public void setDRUGNAME(String dRUGNAME) {
		DRUGNAME = dRUGNAME;
	}
	public String getROUTETYPE() {
		return ROUTETYPE;
	}
	public void setROUTETYPE(String rOUTETYPE) {
		ROUTETYPE = rOUTETYPE;
	}
	public String getROUTETYPENAME() {
		return ROUTETYPENAME;
	}
	public void setROUTETYPENAME(String rOUTETYPENAME) {
		ROUTETYPENAME = rOUTETYPENAME;
	}
	public String getROUTE() {
		return ROUTE;
	}
	public void setROUTE(String rOUTE) {
		ROUTE = rOUTE;
	}
	public String getROUTENAME() {
		return ROUTENAME;
	}
	public void setROUTENAME(String rOUTENAME) {
		ROUTENAME = rOUTENAME;
	}
	public String getSTRENGTHTYPE() {
		return STRENGTHTYPE;
	}
	public void setSTRENGTHTYPE(String sTRENGTHTYPE) {
		STRENGTHTYPE = sTRENGTHTYPE;
	}
	public String getSTRENGTHTYPENAME() {
		return STRENGTHTYPENAME;
	}
	public void setSTRENGTHTYPENAME(String sTRENGTHTYPENAME) {
		STRENGTHTYPENAME = sTRENGTHTYPENAME;
	}
	public String getSTRENGTH() {
		return STRENGTH;
	}
	public void setSTRENGTH(String sTRENGTH) {
		STRENGTH = sTRENGTH;
	}
	public String getSTRENGTHNAME() {
		return STRENGTHNAME;
	}
	public void setSTRENGTHNAME(String sTRENGTHNAME) {
		STRENGTHNAME = sTRENGTHNAME;
	}
	public String getDOSAGE() {
		return DOSAGE;
	}
	public void setDOSAGE(String dOSAGE) {
		DOSAGE = dOSAGE;
	}
	public String getMEDICATIONPERIOD() {
		return MEDICATIONPERIOD;
	}
	public void setMEDICATIONPERIOD(String mEDICATIONPERIOD) {
		MEDICATIONPERIOD = mEDICATIONPERIOD;
	}
	public String getOTHERDRUGNAME() {
		return OTHERDRUGNAME;
	}
	public void setOTHERDRUGNAME(String oTHERDRUGNAME) {
		OTHERDRUGNAME = oTHERDRUGNAME;
	}
	public Number getDRUGID() {
		return DRUGID;
	}
	public void setDRUGID(Number dRUGID) {
		DRUGID = dRUGID;
	}
	public String getEXPIRYDATE() {
		return EXPIRYDATE;
	}
	public void setEXPIRYDATE(String eXPIRYDATE) {
		EXPIRYDATE = eXPIRYDATE;
	}
	public String getQUANTITY() {
		return QUANTITY;
	}
	public void setQUANTITY(String qUANTITY) {
		QUANTITY = qUANTITY;
	}
	public List<DrugsVO> getDrugList() {
		return drugList;
	}
	public void setDrugList(List<DrugsVO> drugList) {
		this.drugList = drugList;
	}
	public String getDRUGTYPECODEOT() {
		return DRUGTYPECODEOT;
	}
	public void setDRUGTYPECODEOT(String dRUGTYPECODEOT) {
		DRUGTYPECODEOT = dRUGTYPECODEOT;
	}
	public String getDRUGSUBTYPECODEOT() {
		return DRUGSUBTYPECODEOT;
	}
	public void setDRUGSUBTYPECODEOT(String dRUGSUBTYPECODEOT) {
		DRUGSUBTYPECODEOT = dRUGSUBTYPECODEOT;
	}
	public String getOTHERDRUGNAMEOT() {
		return OTHERDRUGNAMEOT;
	}
	public void setOTHERDRUGNAMEOT(String oTHERDRUGNAMEOT) {
		OTHERDRUGNAMEOT = oTHERDRUGNAMEOT;
	}
	public String getROUTETYPEOT() {
		return ROUTETYPEOT;
	}
	public void setROUTETYPEOT(String rOUTETYPEOT) {
		ROUTETYPEOT = rOUTETYPEOT;
	}
	public String getROUTETYPENAMEOT() {
		return ROUTETYPENAMEOT;
	}
	public void setROUTETYPENAMEOT(String rOUTETYPENAMEOT) {
		ROUTETYPENAMEOT = rOUTETYPENAMEOT;
	}
	public String getROUTEOT() {
		return ROUTEOT;
	}
	public void setROUTEOT(String rOUTEOT) {
		ROUTEOT = rOUTEOT;
	}
	public String getROUTENAMEOT() {
		return ROUTENAMEOT;
	}
	public void setROUTENAMEOT(String rOUTENAMEOT) {
		ROUTENAMEOT = rOUTENAMEOT;
	}
	public String getSTRENGTHTYPEOT() {
		return STRENGTHTYPEOT;
	}
	public void setSTRENGTHTYPEOT(String sTRENGTHTYPEOT) {
		STRENGTHTYPEOT = sTRENGTHTYPEOT;
	}
	public String getSTRENGTHTYPENAMEOT() {
		return STRENGTHTYPENAMEOT;
	}
	public void setSTRENGTHTYPENAMEOT(String sTRENGTHTYPENAMEOT) {
		STRENGTHTYPENAMEOT = sTRENGTHTYPENAMEOT;
	}
	public String getSTRENGTHOT() {
		return STRENGTHOT;
	}
	public void setSTRENGTHOT(String sTRENGTHOT) {
		STRENGTHOT = sTRENGTHOT;
	}
	public String getSTRENGTHNAMEOT() {
		return STRENGTHNAMEOT;
	}
	public void setSTRENGTHNAMEOT(String sTRENGTHNAMEOT) {
		STRENGTHNAMEOT = sTRENGTHNAMEOT;
	}
	public String getDOSAGEOT() {
		return DOSAGEOT;
	}
	public void setDOSAGEOT(String dOSAGEOT) {
		DOSAGEOT = dOSAGEOT;
	}
	public String getMEDICATIONPERIODOT() {
		return MEDICATIONPERIODOT;
	}
	public void setMEDICATIONPERIODOT(String mEDICATIONPERIODOT) {
		MEDICATIONPERIODOT = mEDICATIONPERIODOT;
	}
	public String getPSUBGRPCODEOT() {
		return PSUBGRPCODEOT;
	}
	public void setPSUBGRPCODEOT(String pSUBGRPCODEOT) {
		PSUBGRPCODEOT = pSUBGRPCODEOT;
	}
	public String getCSUBGRPCODEOT() {
		return CSUBGRPCODEOT;
	}
	public void setCSUBGRPCODEOT(String cSUBGRPCODEOT) {
		CSUBGRPCODEOT = cSUBGRPCODEOT;
	}
	public String getDRUGCODEOT() {
		return DRUGCODEOT;
	}
	public void setDRUGCODEOT(String dRUGCODEOT) {
		DRUGCODEOT = dRUGCODEOT;
	}
	public String getQUANTITYOT() {
		return QUANTITYOT;
	}
	public void setQUANTITYOT(String qUANTITYOT) {
		QUANTITYOT = qUANTITYOT;
	}
	public String getDRUGNAMEOT() {
		return DRUGNAMEOT;
	}
	public void setDRUGNAMEOT(String dRUGNAMEOT) {
		DRUGNAMEOT = dRUGNAMEOT;
	}
	public String getDRUGSUBTYPENAMEOT() {
		return DRUGSUBTYPENAMEOT;
	}
	public void setDRUGSUBTYPENAMEOT(String dRUGSUBTYPENAMEOT) {
		DRUGSUBTYPENAMEOT = dRUGSUBTYPENAMEOT;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public Long getOutDrugsQuantity() {
		return outDrugsQuantity;
	}
	public void setOutDrugsQuantity(Long outDrugsQuantity) {
		this.outDrugsQuantity = outDrugsQuantity;
	}
	public String getDispDrugMstrDrgCode() {
		return dispDrugMstrDrgCode;
	}
	public void setDispDrugMstrDrgCode(String dispDrugMstrDrgCode) {
		this.dispDrugMstrDrgCode = dispDrugMstrDrgCode;
	}
	public String getDispDrugMfg() {
		return dispDrugMfg;
	}
	public void setDispDrugMfg(String dispDrugMfg) {
		this.dispDrugMfg = dispDrugMfg;
	}
	public String getDispDrugDsbtr() {
		return dispDrugDsbtr;
	}
	public void setDispDrugDsbtr(String dispDrugDsbtr) {
		this.dispDrugDsbtr = dispDrugDsbtr;
	}
	public String getDISPDRUGMSTRDRGCODE() {
		return DISPDRUGMSTRDRGCODE;
	}
	public void setDISPDRUGMSTRDRGCODE(String dISPDRUGMSTRDRGCODE) {
		DISPDRUGMSTRDRGCODE = dISPDRUGMSTRDRGCODE;
	}
	public String getAvlbQuantity() {
		return avlbQuantity;
	}
	public void setAvlbQuantity(String avlbQuantity) {
		this.avlbQuantity = avlbQuantity;
	}
	public List<DrugsVO> getMftrs() {
		return mftrs;
	}
	public void setMftrs(List<DrugsVO> mftrs) {
		this.mftrs = mftrs;
	}
	public String getMftrLst() {
		return mftrLst;
	}
	public void setMftrLst(String mftrLst) {
		this.mftrLst = mftrLst;
	}
	public String getDbtr() {
		return dbtr;
	}
	public void setDbtr(String dbtr) {
		this.dbtr = dbtr;
	}
	public String getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}
	public String getIssuedQuant() {
		return issuedQuant;
	}
	public void setIssuedQuant(String issuedQuant) {
		this.issuedQuant = issuedQuant;
	}
	public String getDrugValues() {
		return drugValues;
	}
	public void setDrugValues(String drugValues) {
		this.drugValues = drugValues;
	}
	public List<DrugsVO> getBatchNos() {
		return batchNos;
	}
	public void setBatchNos(List<DrugsVO> batchNos) {
		this.batchNos = batchNos;
	}
	public String getBatchLst() {
		return batchLst;
	}
	public void setBatchLst(String batchLst) {
		this.batchLst = batchLst;
	}
	public String getTotQuantity() {
		return totQuantity;
	}
	public void setTotQuantity(String totQuantity) {
		this.totQuantity = totQuantity;
	}
	
	
}
