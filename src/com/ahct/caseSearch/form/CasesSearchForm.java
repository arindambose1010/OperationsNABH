package com.ahct.caseSearch.form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.caseSearch.vo.CasesSearchVO;


public class CasesSearchForm extends ActionForm{
private String CREATEDBY;
private String FEEDBACKID;
private String REMARKS;
private Date CREATEDON;
private String claimId;
private String startIndex;
private String feedbackId;
private String endIndex;
private String totalRows;
private String rowsPerPage;
private String catId;
private String errStatusId;
private String surgyId;
private String caseNo;
private String claimNo;
private String wapNo;
private String patName;
private String distId;
private String mandalId;
private String villageId;
private String showPage;
private String showPageNew;
private String startPage;
private String endPage;
private String next;
private String prev;
private String fromDate;
private String toDate;
private List<CasesSearchVO> lstCaseSearch;
private List<CasesSearchVO> nabhreport;
private String curPage;
private String pageStats;
private String paginFlag;
private String flag;
private String caseStatus;
private String caseId;
private String fromType;
private String colorFlag;
private String colorCode;
private String showScheme;
private String schemeType;
private String casesSelected[];
private String caseSearchType;
private String caseForApproval;
private String module;
private String ceoFlag;
private String showHospital;
private String claimType;


public String getColorFlag() {
	return colorFlag;
}
public void setColorFlag(String colorFlag) {
	this.colorFlag = colorFlag;
}
private String comm_hno;
private String comm_street;
private String comm_dist;
private String comm_mandal;
private String comm_village;
private String comm_pin;
private String cardIssueDt;
private String fname;
private String gender;
private String months;
private String days;
private String years;
private String caste;
private String contactno;
private String relation;
private String occupation;
private String photoUrl;
private String hospName;
private String SUBMITTEDDATE;
private String mainSymptom;
private String symptom;
private String diagnosisName;
private String mainCatName;
private String catName;
private String subCatName;
private String diseaseName;
private String disAnatomicalName;

private List<CasesSearchVO> IpTherapyDetails;
private List<CasesSearchVO> IpTherapyInvestigationDtls;
private List<CasesSearchVO> DrugsDetails;
private List<CasesSearchVO> ChronicOpDetails;

private String casesForApprovalFlag;
private String ErrStatusId;
private String issueId;
private String issuecaseId=null;
private String hospId=null;
private String issuestatus=null;
private String issuetitle=null;
private Date createddate=null;
private String issueResultFlag=null;
private String issueResultFlagSize=null;
private List<CasesSearchVO> issueDetailsList=null;
private String procName;
private String telephonicId;
private String requestId;


public List<CasesSearchVO> getIssueDetailsList() {
	return issueDetailsList;
}
public void setIssueDetailsList(List<CasesSearchVO> issueDetailsList) {
	this.issueDetailsList = issueDetailsList;
}
public String getIssueResultFlagSize() {
	return issueResultFlagSize;
}
public void setIssueResultFlagSize(String issueResultFlagSize) {
	this.issueResultFlagSize = issueResultFlagSize;
}
public String getIssueId() {
	return issueId;
}
public void setIssueId(String issueId) {
	this.issueId = issueId;
}
public String getIssuecaseId() {
	return issuecaseId;
}
public void setIssuecaseId(String issuecaseId) {
	this.issuecaseId = issuecaseId;
}
public String getHospId() {
	return hospId;
}
public void setHospId(String hospId) {
	this.hospId = hospId;
}
public String getIssuestatus() {
	return issuestatus;
}
public void setIssuestatus(String issuestatus) {
	this.issuestatus = issuestatus;
}
public String getIssuetitle() {
	return issuetitle;
}
public void setIssuetitle(String issuetitle) {
	this.issuetitle = issuetitle;
}
public Date getCreateddate() {
	return createddate;
}
public void setCreateddate(Date createddate) {
	this.createddate = createddate;
}
public String getIssueResultFlag() {
	return issueResultFlag;
}
public void setIssueResultFlag(String issueResultFlag) {
	this.issueResultFlag = issueResultFlag;
}


public String getCasesForApprovalFlag() {
	return casesForApprovalFlag;
}
public void setCasesForApprovalFlag(String casesForApprovalFlag) {
	this.casesForApprovalFlag = casesForApprovalFlag;
}
public List<CasesSearchVO> getIpTherapyDetails() {
	return IpTherapyDetails;
}
public void setIpTherapyDetails(List<CasesSearchVO> ipTherapyDetails) {
	IpTherapyDetails = ipTherapyDetails;
}
public List<CasesSearchVO> getIpTherapyInvestigationDtls() {
	return IpTherapyInvestigationDtls;
}
public void setIpTherapyInvestigationDtls(
		List<CasesSearchVO> ipTherapyInvestigationDtls) {
	IpTherapyInvestigationDtls = ipTherapyInvestigationDtls;
}
public List<CasesSearchVO> getDrugsDetails() {
	return DrugsDetails;
}
public void setDrugsDetails(List<CasesSearchVO> drugsDetails) {
	DrugsDetails = drugsDetails;
}
public List<CasesSearchVO> getChronicOpDetails() {
	return ChronicOpDetails;
}
public void setChronicOpDetails(List<CasesSearchVO> chronicOpDetails) {
	ChronicOpDetails = chronicOpDetails;
}
public String getDiagnosisName() {
	return diagnosisName;
}
public void setDiagnosisName(String diagnosisName) {
	this.diagnosisName = diagnosisName;
}
public String getMainCatName() {
	return mainCatName;
}
public void setMainCatName(String mainCatName) {
	this.mainCatName = mainCatName;
}
public String getCatName() {
	return catName;
}
public void setCatName(String catName) {
	this.catName = catName;
}
public String getSubCatName() {
	return subCatName;
}
public void setSubCatName(String subCatName) {
	this.subCatName = subCatName;
}
public String getDiseaseName() {
	return diseaseName;
}
public void setDiseaseName(String diseaseName) {
	this.diseaseName = diseaseName;
}
public String getDisAnatomicalName() {
	return disAnatomicalName;
}
public void setDisAnatomicalName(String disAnatomicalName) {
	this.disAnatomicalName = disAnatomicalName;
}
public String getMainSymptom() {
	return mainSymptom;
}
public void setMainSymptom(String mainSymptom) {
	this.mainSymptom = mainSymptom;
}
public String getSymptom() {
	return symptom;
}
public void setSymptom(String symptom) {
	this.symptom = symptom;
}
public String getHospName() {
	return hospName;
}
public void setHospName(String hospName) {
	this.hospName = hospName;
}
public String getSUBMITTEDDATE() {
	return SUBMITTEDDATE;
}
public void setSUBMITTEDDATE(String  sUBMITTEDDATE) {
	SUBMITTEDDATE = sUBMITTEDDATE;
}
public String getPhotoUrl() {
	return photoUrl;
}
public void setPhotoUrl(String photoUrl) {
	this.photoUrl = photoUrl;
}
public String getComm_hno() {
	return comm_hno;
}
public void setComm_hno(String comm_hno) {
	this.comm_hno = comm_hno;
}
public String getComm_street() {
	return comm_street;
}
public void setComm_street(String comm_street) {
	this.comm_street = comm_street;
}
public String getComm_dist() {
	return comm_dist;
}
public void setComm_dist(String comm_dist) {
	this.comm_dist = comm_dist;
}
public String getComm_mandal() {
	return comm_mandal;
}
public void setComm_mandal(String comm_mandal) {
	this.comm_mandal = comm_mandal;
}
public String getComm_village() {
	return comm_village;
}
public void setComm_village(String comm_village) {
	this.comm_village = comm_village;
}
public String getComm_pin() {
	return comm_pin;
}
public void setComm_pin(String comm_pin) {
	this.comm_pin = comm_pin;
}
public String getCardIssueDt() {
	return cardIssueDt;
}
public void setCardIssueDt(String cardIssueDt) {
	this.cardIssueDt = cardIssueDt;
}
public String getFname() {
	return fname;
}
public void setFname(String fname) {
	this.fname = fname;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public String getMonths() {
	return months;
}
public void setMonths(String months) {
	this.months = months;
}
public String getDays() {
	return days;
}
public void setDays(String days) {
	this.days = days;
}
public String getYears() {
	return years;
}
public void setYears(String years) {
	this.years = years;
}
public String getCaste() {
	return caste;
}
public void setCaste(String caste) {
	this.caste = caste;
}
public String getContactno() {
	return contactno;
}
public void setContactno(String contactno) {
	this.contactno = contactno;
}
public String getRelation() {
	return relation;
}
public void setRelation(String relation) {
	this.relation = relation;
}
public String getOccupation() {
	return occupation;
}
public void setOccupation(String occupation) {
	this.occupation = occupation;
}
public String getFromType() {
	return fromType;
}
public void setFromType(String fromType) {
	this.fromType = fromType;
}







public String getCaseId() {
	return caseId;
}
public void setCaseId(String caseId) {
	this.caseId = caseId;
}
public String getCaseStatus() {
	return caseStatus;
}
public void setCaseStatus(String caseStatus) {
	this.caseStatus = caseStatus;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getCurPage() {
	return curPage;
}
public void setCurPage(String curPage) {
	this.curPage = curPage;
}
public String getPageStats() {
	return pageStats;
}
public void setPageStats(String pageStats) {
	this.pageStats = pageStats;
}
public String getPaginFlag() {
	return paginFlag;
}
public void setPaginFlag(String paginFlag) {
	this.paginFlag = paginFlag;
}
public String getNext() {
	return next;
}
public void setNext(String next) {
	this.next = next;
}
public String getPrev() {
	return prev;
}
public void setPrev(String prev) {
	this.prev = prev;
}
public String getShowPage() {
	return showPage;
}
public void setShowPage(String showPage) {
	this.showPage = showPage;
}
public String getCatId() {
	return catId;
}
public void setCatId(String catId) {
	this.catId = catId;
}
public String getErrStatusId() {
	return errStatusId;
}
public void setErrStatusId(String errStatusId) {
	this.errStatusId = errStatusId;
}
public String getSurgyId() {
	return surgyId;
}
public void setSurgyId(String surgyId) {
	this.surgyId = surgyId;
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
public String getWapNo() {
	return wapNo;
}
public void setWapNo(String wapNo) {
	this.wapNo = wapNo;
}
public String getPatName() {
	return patName;
}
public void setPatName(String patName) {
	this.patName = patName;
}
public String getDistId() {
	return distId;
}
public void setDistId(String distId) {
	this.distId = distId;
}
public String getMandalId() {
	return mandalId;
}
public void setMandalId(String mandalId) {
	this.mandalId = mandalId;
}
public String getVillageId() {
	return villageId;
}
public void setVillageId(String villageId) {
	this.villageId = villageId;
}
public String getRowsPerPage() {
	return rowsPerPage;
}
public void setRowsPerPage(String rowsPerPage) {
	this.rowsPerPage = rowsPerPage;
}
public String getStartIndex() {
	return startIndex;
}
public void setStartIndex(String startIndex) {
	this.startIndex = startIndex;
}
public String getEndIndex() {
	return endIndex;
}
public void setEndIndex(String endIndex) {
	this.endIndex = endIndex;
}
public String getTotalRows() {
	return totalRows;
}
public void setTotalRows(String totalRows) {
	this.totalRows = totalRows;
}


public List<CasesSearchVO> getLstCaseSearch() {
	return lstCaseSearch;
}
public void setLstCaseSearch(List<CasesSearchVO> lstCaseSearch) {
	this.lstCaseSearch = lstCaseSearch;
}

public String getClaimId() {
	return claimId;
}

public void setClaimId(String claimId) {
	this.claimId = claimId;
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
/**
 * @return the feedbackId
 */
public String getFeedbackId() {
	return feedbackId;
}
/**
 * @param feedbackId the feedbackId to set
 */
public void setFeedbackId(String feedbackId) {
	this.feedbackId = feedbackId;
}
/**
 * @return the cREATEDBY
 */
public String getCREATEDBY() {
	return CREATEDBY;
}
/**
 * @param cREATEDBY the cREATEDBY to set
 */
public void setCREATEDBY(String cREATEDBY) {
	CREATEDBY = cREATEDBY;
}
/**
 * @return the fEEDBACKID
 */
public String getFEEDBACKID() {
	return FEEDBACKID;
}
/**
 * @param fEEDBACKID the fEEDBACKID to set
 */
public void setFEEDBACKID(String fEEDBACKID) {
	FEEDBACKID = fEEDBACKID;
}
/**
 * @return the rEMARKS
 */
public String getREMARKS() {
	return REMARKS;
}
/**
 * @param rEMARKS the rEMARKS to set
 */
public void setREMARKS(String rEMARKS) {
	REMARKS = rEMARKS;
}
/**
 * @return the cREATEDON
 */
public Date getCREATEDON() {
	return CREATEDON;
}
/**
 * @param cREATEDON the cREATEDON to set
 */
public void setCREATEDON(Date cREATEDON) {
	CREATEDON = cREATEDON;
}
public String getProcName() {
	return procName;
}
public void setProcName(String procName) {
	this.procName = procName;
}
public String getTelephonicId() {
	return telephonicId;
}
public void setTelephonicId(String telephonicId) {
	this.telephonicId = telephonicId;
}
public String getShowScheme() {
	return showScheme;
}
public void setShowScheme(String showScheme) {
	this.showScheme = showScheme;
}
public String getSchemeType() {
	return schemeType;
}
public void setSchemeType(String schemeType) {
	this.schemeType = schemeType;
}
public String[] getCasesSelected() {
	return casesSelected;
}
public void setCasesSelected(String[] casesSelected) {
	this.casesSelected = casesSelected;
}
public String getCaseSearchType() {
	return caseSearchType;
}
public void setCaseSearchType(String caseSearchType) {
	this.caseSearchType = caseSearchType;
}
public String getCaseForApproval() {
	return caseForApproval;
}
public void setCaseForApproval(String caseForApproval) {
	this.caseForApproval = caseForApproval;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getCeoFlag() {
	return ceoFlag;
}
public void setCeoFlag(String ceoFlag) {
	this.ceoFlag = ceoFlag;
}
public String getShowHospital() {
	return showHospital;
}
public void setShowHospital(String showHospital) {
	this.showHospital = showHospital;
}
public String getRequestId() {
	return requestId;
}
public void setRequestId(String requestId) {
	this.requestId = requestId;
}
public String getColorCode() {
	return colorCode;
}
public void setColorCode(String colorCode) {
	this.colorCode = colorCode;
}
public List<CasesSearchVO> getNabhreport() {
	return nabhreport;
}
public void setNabhreport(List<CasesSearchVO> nabhreport) {
	this.nabhreport = nabhreport;
}
public String getShowPageNew() {
	return showPageNew;
}
public void setShowPageNew(String showPageNew) {
	this.showPageNew = showPageNew;
}
public String getStartPage() {
	return startPage;
}
public void setStartPage(String startPage) {
	this.startPage = startPage;
}
public String getEndPage() {
	return endPage;
}
public void setEndPage(String endPage) {
	this.endPage = endPage;
}
public String getClaimType() {
	return claimType;
}
public void setClaimType(String claimType) {
	this.claimType = claimType;
}


}
