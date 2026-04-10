package com.ahct.reports.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.ahct.reports.vo.LabelBean;

public class ReportsForm extends ActionForm {

	private String userName;
	private String patMandal;
	private String fromDetailed;
	private String scheme;
	private String hospitalNames;
	private String districts;
	private String mandal;
	private String distType;
	private String ageGroup;
	private String patientDist;
	private String agewise;
	private static final long serialVersionUID = 1L;
	private List<LabelBean> hostList;
	private List<LabelBean> drugExpList;
	private List<LabelBean> wellnesscenters;
	private List<LabelBean> drugName;
	private List<LabelBean> drugType;
	private String wellnesscenter;
	private String dName;
	private String dType;
	private String dstrName;
	private String dispname;
	private String DISPNAME;
	private String hodId;
	private Number AVGCOUNT;
	private List<LabelBean> searchList;
	private String toDate;
	private List<LabelBean> dodList;
	private String distId;
	private String stoId;
	private List<LabelBean> casesFromCountList;
	// private List<OverallReportVO> claimsubmtrprt;
	// private List<OverallReportVO> claimList;
	private String ddoId;
	private String searchType;
	private String desgId;
	private List<LabelBean> execList;
	private List<LabelBean> empnlList;
	private List<LabelBean> otpExemptReportDrillDownList;
	private String msg;
	private List<LabelBean> drugReportList;
	private List<LabelBean> invsterepList;
	private List<LabelBean> regReportList;
	private List<LabelBean> drugZeroList;
	private List<LabelBean> ledrepList;
	private List<LabelBean> wcDashboardReportList;
	private List<LabelBean> wcRegistReportList;
	private List<LabelBean> returnVendorList;
	private List<LabelBean> creditNoteList;
	private String showList;
	private List<LabelBean> otpExemptList;
	private List<LabelBean> otpExemptReportList;
	private List<LabelBean> outstandingRptLst;
	private List<LabelBean> biometricReport;

	private List<LabelBean> appointmentsList;
	private List<LabelBean> drugDetailedReportList;
	private String count;
	private List<LabelBean> categoryList;
	private List<LabelBean> serviceList;
	private List<LabelBean> districtList;
	private List<LabelBean> desgnList;
	private List<LabelBean> flowList;
	private String servId;
	private String cateId;
	private int inPatCount;
	private int outPatCount;
	private int chronicOPCount;
	private String hosp;
	private String district;
	private String gender;
	private String deathCase;
	private String tdDate;
	private String cate;
	private String patientType;
	private String date;
	private String fromDate;
	private String destDate;
	private String cardType;
	private String genFlag;
	private String hospDist;
	private String districtdd;
	private String sName;
	private String iName;
	private String cardTypeChk;

	private String mndl;
	private String caste;
	private String age;
	private String type;
	private List<LabelBean> hospList;
	private List<LabelBean> mandalList;
	private List<LabelBean> mandallList;
	private List<LabelBean> hospDistList;
	private String year;
	private String selHosp;
	private String nabhType;
	private String deathType;
	private String DSTRBTRID;
	private String DSTRBTRNAME;

	private String caseType;
	private String nabhHosp;
	private String patDist;
	private String dateFlag;

	private String loginName;
	private String empName;
	private String desgName;
	private String status;
	private String wellnessCenter;
	private String successFlag;
	private String sno;
	private List<LabelBean> desgNamesList;
	// private List<EmployeeVO> employeeDetails;
	private String pageId;
	private List<LabelBean> regReportListSpeciality;

	private String stoLogin;
	// private List<PreauthReportVo> lstPreauthReportVo;
	private String approveCnt;
	private String rejCnt;
	private String pendingCnt;
	private String totCnt;
	private String miscellanoCnt;
	private String hospType;
	private String govtHospType;
	private String categoryType;
	private String procedureType;
	private String[] categoryLst;
	private String[] categorySelList;
	private String[] procedureList;
	private String[] procedureSelList;
	private String procedure;
	private String diagnosis;
	private String slab;
	private String hospDistChk;
	private String patDistChk;
	private String monthwise;
	private String department;
	private String designation;
	// private List<OverallReportVO> lstOverallReport;
	private String cardTypeCheck;
	private String hospTypeExcd;
	private String beneficiaryType;
	private String teleCase;
	private String sfpDate;
	private String statusDate;
	private String sfpFromDate;
	private String sfpToDate;
	private String tpFromDate;
	private String tpToDate;
	// private List<PanelDocPayVO> paymentsList;
	// private List<PanelDocPayVO> paymentsTotals;
	private String flag;
	private String paymentId;
	private String hiddenScheme;
	private String hiddenFromDt;
	private String hiddenToDt;
	private int startPage;
	private int endPage;
	private int startIndex;
	private int endIndex;
	private String rowsPerPage;
	private String showPage;
	private String next;
	private String prev;
	private String hiddenSearchType;
	private String stateType;

	private String frmDate;
	private String desDate;
	private String deptName;
	private String editFlag;

	// add by varalakshmi.

	private List<LabelBean> stopPaymentList;
	private List<LabelBean> sPaymentList;
	private String HospCode;
	private Number COUNT;
	private String caseId;
	private String stopCard;

	// add by varalakshmi.
	private String hosType;
	private String hospName;
	private Long preauthAmt;

	private String VacPosName;

	// private List<EhfCaseFollowupMvTg> followupRepList;

	private String fName;
	private String lName;

	private String empNumber;
	private String empDob;
	private String empDoj;
	private String email;
	private String mobNumber;

	private String postalCode;

	private String state;
	private String country;
	private String empAddress;

	private String mandals;
	private String villages;
	private String digiSignReq;
	private String inService;

	private String nabh;
	private String sdFromDate;
	private String sdToDate;
	private String sUpdFromDate;
	private String sUpdToDate;

	private String regDate;
	private String month;
	private String ageGrp;
	private String dateChk1;

	private String ddoStoDistChk;
	private String communityChk;
	private String patTypeChk;
	private String benTypeChk;
	private String excelFlag;
	private String mobileNo;

	// For Employee Performance Report
	private Number caseCount;
	private String docName;

	private String schemeType;
	

	private String reportType;
	private String reportType1;
	
	private String preAuthCount;
	private String surgCount;
	private String preAuthAmount;
	private String surgAmount;
	private String enhnAmount;
	private String preAuthApprvAmount;
	private String expType;
	
	private List<LabelBean> indentList;
	private String dispDrugID;
	private String drugTypeID;
	private String drugProcType;

	public List<LabelBean> getEmpnlList() {
		return empnlList;
	}

	public void setEmpnlList(List<LabelBean> empnlList) {
		this.empnlList = empnlList;
	}

	public List<LabelBean> getFlowList() {
		return flowList;
	}

	public void setFlowList(List<LabelBean> flowList) {
		this.flowList = flowList;
	}

	public String getNabhType() {
		return nabhType;
	}

	public void setNabhType(String nabhType) {
		this.nabhType = nabhType;
	}

	public List<LabelBean> getRegReportListSpeciality() {
		return regReportListSpeciality;
	}

	public void setRegReportListSpeciality(
			List<LabelBean> regReportListSpeciality) {
		this.regReportListSpeciality = regReportListSpeciality;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWellnessCenter() {
		return wellnessCenter;
	}

	public void setWellnessCenter(String wellnessCenter) {
		this.wellnessCenter = wellnessCenter;
	}

	public String getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public List<LabelBean> getDesgNamesList() {
		return desgNamesList;
	}

	public void setDesgNamesList(List<LabelBean> desgNamesList) {
		this.desgNamesList = desgNamesList;
	}

	public String getDateFlag() {
		return dateFlag;
	}

	public void setDateFlag(String dateFlag) {
		this.dateFlag = dateFlag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getVacPosName() {
		return VacPosName;
	}

	public void setVacPosName(String vacPosName) {
		VacPosName = vacPosName;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmpNumber() {
		return empNumber;
	}

	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}

	public String getEmpDob() {
		return empDob;
	}

	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}

	public String getEmpDoj() {
		return empDoj;
	}

	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(String empAddress) {
		this.empAddress = empAddress;
	}

	public String getMandals() {
		return mandals;
	}

	public void setMandals(String mandals) {
		this.mandals = mandals;
	}

	public String getVillages() {
		return villages;
	}

	public void setVillages(String villages) {
		this.villages = villages;
	}

	public String getDigiSignReq() {
		return digiSignReq;
	}

	public void setDigiSignReq(String digiSignReq) {
		this.digiSignReq = digiSignReq;
	}

	public String getInService() {
		return inService;
	}

	public void setInService(String inService) {
		this.inService = inService;
	}

	public String getPageId() {
		return pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getExcelFlag() {
		return excelFlag;
	}

	public void setExcelFlag(String excelFlag) {
		this.excelFlag = excelFlag;
	}

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

	public String getFrmDate() {
		return frmDate;
	}

	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}

	public String getDesDate() {
		return desDate;
	}

	public void setDesDate(String desDate) {
		this.desDate = desDate;
	}

	public String getNabh() {
		return nabh;
	}

	public void setNabh(String nabh) {
		this.nabh = nabh;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getAgeGrp() {
		return ageGrp;
	}

	public void setAgeGrp(String ageGrp) {
		this.ageGrp = ageGrp;
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

	public List<LabelBean> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<LabelBean> searchList) {
		this.searchList = searchList;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public String getShowPage() {
		return showPage;
	}

	public void setShowPage(String showPage) {
		this.showPage = showPage;
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

	public String getTotCnt() {
		return totCnt;
	}

	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}

	public String getMiscellanoCnt() {
		return miscellanoCnt;
	}

	public void setMiscellanoCnt(String miscellanoCnt) {
		this.miscellanoCnt = miscellanoCnt;
	}


	public String getReportType1() {
		return reportType1;
	}

	public void setReportType1(String reportType1) {
		this.reportType1 = reportType1;
	}

	public String getApproveCnt() {
		return approveCnt;
	}

	public void setApproveCnt(String approveCnt) {
		this.approveCnt = approveCnt;
	}

	public String getRejCnt() {
		return rejCnt;
	}

	public void setRejCnt(String rejCnt) {
		this.rejCnt = rejCnt;
	}

	public String getPendingCnt() {
		return pendingCnt;
	}

	public void setPendingCnt(String pendingCnt) {
		this.pendingCnt = pendingCnt;
	}

	public String getSelHosp() {
		return selHosp;
	}

	public void setSelHosp(String selHosp) {
		this.selHosp = selHosp;
	}

	public String getHospDist() {
		return hospDist;
	}

	public void setHospDist(String hospDist) {
		this.hospDist = hospDist;
	}

	public List<LabelBean> getCasesFromCountList() {
		return casesFromCountList;
	}

	public void setCasesFromCountList(List<LabelBean> casesFromCountList) {
		this.casesFromCountList = casesFromCountList;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public String getStoId() {
		return stoId;
	}

	public void setStoId(String stoId) {
		this.stoId = stoId;
	}

	public List<LabelBean> getHostList() {
		return hostList;
	}

	public void setHostList(List<LabelBean> hostList) {
		this.hostList = hostList;
	}

	public String getHodId() {
		return hodId;
	}

	public void setHodId(String hodId) {
		this.hodId = hodId;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public List<LabelBean> getDodList() {
		return dodList;
	}

	public void setDodList(List<LabelBean> dodList) {
		this.dodList = dodList;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDdoId() {
		return ddoId;
	}

	public void setDdoId(String ddoId) {
		this.ddoId = ddoId;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public List<LabelBean> getExecList() {
		return execList;
	}

	public void setExecList(List<LabelBean> execList) {
		this.execList = execList;
	}

	public List<LabelBean> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<LabelBean> categoryList) {
		this.categoryList = categoryList;
	}

	public List<LabelBean> getServiceList() {
		return serviceList;
	}

	public void setServiceList(List<LabelBean> serviceList) {
		this.serviceList = serviceList;
	}

	public List<LabelBean> getDistrictList() {
		return districtList;
	}

	public void setDistrictList(List<LabelBean> districtList) {
		this.districtList = districtList;
	}

	public List<LabelBean> getDesgnList() {
		return desgnList;
	}

	public void setDesgnList(List<LabelBean> desgnList) {
		this.desgnList = desgnList;
	}

	public String getServId() {
		return servId;
	}

	public void setServId(String servId) {
		this.servId = servId;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getDesgId() {
		return desgId;
	}

	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}

	public int getInPatCount() {
		return inPatCount;
	}

	public void setInPatCount(int inPatCount) {
		this.inPatCount = inPatCount;
	}

	public int getOutPatCount() {
		return outPatCount;
	}

	public void setOutPatCount(int outPatCount) {
		this.outPatCount = outPatCount;
	}

	public int getChronicOPCount() {
		return chronicOPCount;
	}

	public void setChronicOPCount(int chronicOPCount) {
		this.chronicOPCount = chronicOPCount;
	}

	public String getHosp() {
		return hosp;
	}

	public void setHosp(String hosp) {
		this.hosp = hosp;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
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

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getGenFlag() {
		return genFlag;
	}

	public void setGenFlag(String genFlag) {
		this.genFlag = genFlag;
	}

	public String getDestDate() {
		return destDate;
	}

	public void setDestDate(String destDate) {
		this.destDate = destDate;
	}

	public List<LabelBean> getHospList() {
		return hospList;
	}

	public void setHospList(List<LabelBean> hospList) {
		this.hospList = hospList;
	}

	public List<LabelBean> getMandalList() {
		return mandalList;
	}

	public void setMandalList(List<LabelBean> mandalList) {
		this.mandalList = mandalList;
	}

	public String getMndl() {
		return mndl;
	}

	public void setMndl(String mndl) {
		this.mndl = mndl;
	}

	public String getCaste() {
		return caste;
	}

	public void setCaste(String caste) {
		this.caste = caste;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<LabelBean> getHospDistList() {
		return hospDistList;
	}

	public void setHospDistList(List<LabelBean> hospDistList) {
		this.hospDistList = hospDistList;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getStoLogin() {
		return stoLogin;
	}

	public void setStoLogin(String stoLogin) {
		this.stoLogin = stoLogin;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
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

	public String[] getCategoryLst() {
		return categoryLst;
	}

	public void setCategoryLst(String[] categoryLst) {
		this.categoryLst = categoryLst;
	}

	public String[] getCategorySelList() {
		return categorySelList;
	}

	public void setCategorySelList(String[] categorySelList) {
		this.categorySelList = categorySelList;
	}

	public String[] getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(String[] procedureList) {
		this.procedureList = procedureList;
	}

	public String[] getProcedureSelList() {
		return procedureSelList;
	}

	public void setProcedureSelList(String[] procedureSelList) {
		this.procedureSelList = procedureSelList;
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

	public String getMonthwise() {
		return monthwise;
	}

	public void setMonthwise(String monthwise) {
		this.monthwise = monthwise;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getCardTypeCheck() {
		return cardTypeCheck;
	}

	public void setCardTypeCheck(String cardTypeCheck) {
		this.cardTypeCheck = cardTypeCheck;
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

	public String getTeleCase() {
		return teleCase;
	}

	public void setTeleCase(String teleCase) {
		this.teleCase = teleCase;
	}

	public Number getCaseCount() {
		return caseCount;
	}

	public void setCaseCount(Number caseCount) {
		this.caseCount = caseCount;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
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

	public String getSchemeType() {
		return schemeType;
	}

	public void setSchemeType(String schemeType) {
		this.schemeType = schemeType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getHiddenScheme() {
		return hiddenScheme;
	}

	public void setHiddenScheme(String hiddenScheme) {
		this.hiddenScheme = hiddenScheme;
	}

	public String getHiddenFromDt() {
		return hiddenFromDt;
	}

	public void setHiddenFromDt(String hiddenFromDt) {
		this.hiddenFromDt = hiddenFromDt;
	}

	public String getHiddenToDt() {
		return hiddenToDt;
	}

	public void setHiddenToDt(String hiddenToDt) {
		this.hiddenToDt = hiddenToDt;
	}

	public String getHiddenSearchType() {
		return hiddenSearchType;
	}

	public void setHiddenSearchType(String hiddenSearchType) {
		this.hiddenSearchType = hiddenSearchType;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<LabelBean> getDrugReportList() {
		return drugReportList;
	}

	public void setDrugReportList(List<LabelBean> drugReportList) {
		this.drugReportList = drugReportList;
	}

	public List<LabelBean> getRegReportList() {
		return regReportList;
	}

	public void setRegReportList(List<LabelBean> regReportList) {
		this.regReportList = regReportList;
	}

	public List<LabelBean> getInvsterepList() {
		return invsterepList;
	}

	public void setInvsterepList(List<LabelBean> invsterepList) {
		this.invsterepList = invsterepList;
	}

	public List<LabelBean> getAppointmentsList() {
		return appointmentsList;
	}

	public void setAppointmentsList(List<LabelBean> appointmentsList) {
		this.appointmentsList = appointmentsList;
	}

	public List<LabelBean> getWellnesscenters() {
		return wellnesscenters;
	}

	public void setWellnesscenters(List<LabelBean> wellnesscenters) {
		this.wellnesscenters = wellnesscenters;
	}

	public String getDispname() {
		return dispname;
	}

	public void setDispname(String dispname) {
		this.dispname = dispname;
	}

	public String getDISPNAME() {
		return DISPNAME;
	}

	public void setDISPNAME(String dISPNAME) {
		DISPNAME = dISPNAME;
	}

	public List<LabelBean> getDrugDetailedReportList() {
		return drugDetailedReportList;
	}

	public void setDrugDetailedReportList(List<LabelBean> drugDetailedReportList) {
		this.drugDetailedReportList = drugDetailedReportList;
	}

	public String getStateType() {
		return stateType;
	}

	public void setStateType(String stateType) {
		this.stateType = stateType;
	}

	public String getDistType() {
		return distType;
	}

	public void setDistType(String distType) {
		this.distType = distType;
	}

	public String getMandal() {
		return mandal;
	}

	public void setMandal(String mandal) {
		this.mandal = mandal;
	}

	public String getPatientDist() {
		return patientDist;
	}

	public void setPatientDist(String patientDist) {
		this.patientDist = patientDist;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	public String getDistricts() {
		return districts;
	}

	public void setDistricts(String districts) {
		this.districts = districts;
	}

	public String getHospitalNames() {
		return hospitalNames;
	}

	public void setHospitalNames(String hospitalNames) {
		this.hospitalNames = hospitalNames;
	}

	public String getScheme() {
		return scheme;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getPatMandal() {
		return patMandal;
	}

	public void setPatMandal(String patMandal) {
		this.patMandal = patMandal;
	}

	public List<LabelBean> getMandallList() {
		return mandallList;
	}

	public void setMandallList(List<LabelBean> mandallList) {
		this.mandallList = mandallList;
	}

	public List<LabelBean> getStopPaymentList() {
		return stopPaymentList;
	}

	public void setStopPaymentList(List<LabelBean> stopPaymentList) {
		this.stopPaymentList = stopPaymentList;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public String getHospCode() {
		return HospCode;
	}

	public void setHospCode(String hospCode) {
		HospCode = hospCode;
	}

	public Number getCOUNT() {
		return COUNT;
	}

	public void setCOUNT(Number cOUNT) {
		COUNT = cOUNT;
	}

	public List<LabelBean> getSPaymentList() {
		return sPaymentList;
	}

	public void setSPaymentList(List<LabelBean> sPaymentList) {
		this.sPaymentList = sPaymentList;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public String getHosType() {
		return hosType;
	}

	public void setHosType(String hosType) {
		this.hosType = hosType;
	}

	public String getCaseType() {
		return caseType;
	}

	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}

	

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

	public String getNabhHosp() {
		return nabhHosp;
	}

	public void setNabhHosp(String nabhHosp) {
		this.nabhHosp = nabhHosp;
	}

	public String getPatDist() {
		return patDist;
	}

	public void setPatDist(String patDist) {
		this.patDist = patDist;
	}

	public String getDateChk1() {
		return dateChk1;
	}

	public void setDateChk1(String dateChk1) {
		this.dateChk1 = dateChk1;
	}

	public String getBenTypeChk() {
		return benTypeChk;
	}

	public void setBenTypeChk(String benTypeChk) {
		this.benTypeChk = benTypeChk;
	}

	public String getStopCard() {
		return stopCard;
	}

	public void setStopCard(String stopCard) {
		this.stopCard = stopCard;
	}

	public Long getPreauthAmt() {
		return preauthAmt;
	}

	public void setPreauthAmt(Long preauthAmt) {
		this.preauthAmt = preauthAmt;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getDeathType() {
		return deathType;
	}

	public void setDeathType(String deathType) {
		this.deathType = deathType;
	}

	public String getAgewise() {
		return agewise;
	}

	public void setAgewise(String agewise) {
		this.agewise = agewise;
	}

	public Number getAVGCOUNT() {
		return AVGCOUNT;
	}

	public void setAVGCOUNT(Number aVGCOUNT) {
		AVGCOUNT = aVGCOUNT;
	}

	public String getDistrictdd() {
		return districtdd;
	}

	public void setDistrictdd(String districtdd) {
		this.districtdd = districtdd;
	}

	public String getWellnesscenter() {
		return wellnesscenter;
	}

	public void setWellnesscenter(String wellnesscenter) {
		this.wellnesscenter = wellnesscenter;
	}

	public List<LabelBean> getDrugName() {
		return drugName;
	}

	public void setDrugName(List<LabelBean> drugName) {
		this.drugName = drugName;
	}

	public List<LabelBean> getDrugType() {
		return drugType;
	}

	public void setDrugType(List<LabelBean> drugType) {
		this.drugType = drugType;
	}

	public String getdName() {
		return dName;
	}

	public void setdName(String dName) {
		this.dName = dName;
	}

	public String getdType() {
		return dType;
	}

	public void setdType(String dType) {
		this.dType = dType;
	}

	public List<LabelBean> getWcDashboardReportList() {
		return wcDashboardReportList;
	}

	public void setWcDashboardReportList(List<LabelBean> wcDashboardReportList) {
		this.wcDashboardReportList = wcDashboardReportList;
	}

	public List<LabelBean> getWcRegistReportList() {
		return wcRegistReportList;
	}

	public void setWcRegistReportList(List<LabelBean> wcRegistReportList) {
		this.wcRegistReportList = wcRegistReportList;
	}

	public List<LabelBean> getBiometricReport() {
		return biometricReport;
	}

	public void setBiometricReport(List<LabelBean> biometricReport) {
		this.biometricReport = biometricReport;
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

	public String getCardTypeChk() {
		return cardTypeChk;
	}

	public void setCardTypeChk(String cardTypeChk) {
		this.cardTypeChk = cardTypeChk;
	}

	public String getFromDetailed() {
		return fromDetailed;
	}

	public void setFromDetailed(String fromDetailed) {
		this.fromDetailed = fromDetailed;
	}

	public List<LabelBean> getDrugZeroList() {
		return drugZeroList;
	}

	public void setDrugZeroList(List<LabelBean> drugZeroList) {
		this.drugZeroList = drugZeroList;
	}

	public List<LabelBean> getLedrepList() {
		return ledrepList;
	}

	public void setLedrepList(List<LabelBean> ledrepList) {
		this.ledrepList = ledrepList;
	}

	public String getExpType() {
		return expType;
	}

	public void setExpType(String expType) {
		this.expType = expType;
	}

	public List<LabelBean> getDrugExpList() {
		return drugExpList;
	}

	public void setDrugExpList(List<LabelBean> drugExpList) {
		this.drugExpList = drugExpList;
	}

	public List<LabelBean> getReturnVendorList() {
		return returnVendorList;
	}

	public void setReturnVendorList(List<LabelBean> returnVendorList) {
		this.returnVendorList = returnVendorList;
	}

	public List<LabelBean> getCreditNoteList() {
		return creditNoteList;
	}

	public void setCreditNoteList(List<LabelBean> creditNoteList) {
		this.creditNoteList = creditNoteList;
	}

	public String getDstrName() {
		return dstrName;
	}

	public void setDstrName(String dstrName) {
		this.dstrName = dstrName;
	}

	

	public List<LabelBean> getIndentList() {
		return indentList;
	}

	public void setIndentList(List<LabelBean> indentList) {
		this.indentList = indentList;
	}

	public String getDispDrugID() {
		return dispDrugID;
	}

	public void setDispDrugID(String dispDrugID) {
		this.dispDrugID = dispDrugID;
	}

	public String getDrugTypeID() {
		return drugTypeID;
	}

	public void setDrugTypeID(String drugTypeID) {
		this.drugTypeID = drugTypeID;
	}

	public String getDrugProcType() {
		return drugProcType;
	}

	public void setDrugProcType(String drugProcType) {
		this.drugProcType = drugProcType;
	}

	public List<LabelBean> getOtpExemptList() {
		return otpExemptList;
	}

	public void setOtpExemptList(List<LabelBean> otpExemptList) {
		this.otpExemptList = otpExemptList;
	}

	public List<LabelBean> getOtpExemptReportList() {
		return otpExemptReportList;
	}

	public void setOtpExemptReportList(List<LabelBean> otpExemptReportList) {
		this.otpExemptReportList = otpExemptReportList;
	}

	public List<LabelBean> getOtpExemptReportDrillDownList() {
		return otpExemptReportDrillDownList;
	}

	public void setOtpExemptReportDrillDownList(
			List<LabelBean> otpExemptReportDrillDownList) {
		this.otpExemptReportDrillDownList = otpExemptReportDrillDownList;
	}

	public List<LabelBean> getOutstandingRptLst() {
		return outstandingRptLst;
	}

	public void setOutstandingRptLst(List<LabelBean> outstandingRptLst) {
		this.outstandingRptLst = outstandingRptLst;
	}

	public String getShowList() {
		return showList;
	}

	public void setShowList(String showList) {
		this.showList = showList;
	}

	public String getDSTRBTRID() {
		return DSTRBTRID;
	}

	public void setDSTRBTRID(String dSTRBTRID) {
		DSTRBTRID = dSTRBTRID;
	}

	public String getDSTRBTRNAME() {
		return DSTRBTRNAME;
	}

	public void setDSTRBTRNAME(String dSTRBTRNAME) {
		DSTRBTRNAME = dSTRBTRNAME;
	}
}
