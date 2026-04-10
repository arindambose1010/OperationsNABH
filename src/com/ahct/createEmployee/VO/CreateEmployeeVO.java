package com.ahct.createEmployee.VO;


import java.util.Date;
import java.util.List;

import com.ahct.common.vo.LabelBean;



public class CreateEmployeeVO {
	private String id;
	private String value;
	private Number totValue;
	private String pwd;
	private String deptId;
	private String desgId;
	private String desgName;
	private String langId;
	private String crtDt;
	private String crtUsr;
	private String grpId;
	private String shiftCode;
	private String locId;
	private String locName;
	private String weekOffDay;
	private String workLoc;
	private String level;
	private String reportingPersonId;
	private String reportingPersonName;
	private String shortName;
	private String unitId;
	private Integer unitSeqId;
	private String msg;
	private String unitName;
	private String userId;
	private long userSeqId;
	private String userNo;
	private String loginName;
	private String fName;
	
	private String lName;
	private Date doj;
	private String email;
	private String mobileNo;
	private String addr;
	private String city;
	private String state;
	private String country;
	private String dist;
	private String pin;
	private Date dob;
	private String gender;
	private String userRole;
	private String location;
	private String addr1;
	private String city1;
	private String state1;
	private String country1;
	private String pin1;
	private String biometricFlag;
	private String serviceFlg;
	private String digiVerifyReq;
	private String SMS;
	private String mandMunci;
	private String addrMandMunci;
	private String mandMunci1;
	private String addrMandMunci1;
	private String mmplties1;
	private String dist1;
	private String grpName;
	private String status;
	private String editFlg;
	private Date startDt;
	private String searchText;
	private String searchType;
	private String loginType;
	Date startDate;
	Date endDate;
	private char activeYN;
	private String hospId;
	 private String startIndex;
	 private String rowsPerPage;
	 private int totRowCount;
	 private List<CreateEmployeeVO> empSearchList;
	 private List<CreateEmployeeVO> empList;
	private List<LabelBean> deptNamesList;
	 private int start_index;
		private int end_index;
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
	public List<CreateEmployeeVO> getEmpList() {
		return empList;
	}
	public void setEmpList(List<CreateEmployeeVO> empList) {
		this.empList = empList;
	}

	private String exportFlag; 
	 private String csvFlag;
	

	private String accHolderName;
	  private String bankName;
	  private String accNumber;
	  private String panName;
	  private String panNumber;
	  private String ifscCode;
	  private String index;
	  private String scheme;
	  private String unAllocFlag;
	  
	 

	private String designationId;
	  private String designationName;
	  private String designationSname;
	  private String desgStatus;
	  private String editFlag;
	  private String empNumber;
	  private String allocationFlag;
	  private String fromDate;
	  private String toDate;
	  
	  
	//For pagination
	   private int startPage;
	  private int endPage;
		private String showPage;
		private String totalRows; 
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
		public String getShowPage() {
			return showPage;
		}
		public void setShowPage(String showPage) {
			this.showPage = showPage;
		}
		public String getIndex() {
			return index;
		}
		public void setIndex(String index) {
			this.index = index;
		}
		public String getTotalRows() {
			return totalRows;
		}
		public void setTotalRows(String totalRows) {
			this.totalRows = totalRows;
		}
		public String getPrev() {
			return prev;
		}
		public void setPrev(String prev) {
			this.prev = prev;
		}
		public String getNext() {
			return next;
		}
		public void setNext(String next) {
			this.next = next;
		}

		private String prev;
		private String next;
	   
	  
	  public void setAllocationFlag(String allocationFlag) {
			this.allocationFlag = allocationFlag;
		}
		public String getAllocationFlag() {
			return allocationFlag;
		}
	  public void setEmpNumber(String empNumber) {
			this.empNumber = empNumber;
		}
		public String getEmpNumber() {
			return empNumber;
		}
	  public String getDesignationId() {
		return designationId;
	}

	public void setDesignationId(String designationId) {
		this.designationId = designationId;
	}

	public String getDesignationName() {
		return designationName;
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
	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getDesignationSname() {
		return designationSname;
	}

	public void setDesignationSname(String designationSname) {
		this.designationSname = designationSname;
	}

	public String getBankName() {
			return bankName;
		}

		public void setBankName(String bankName) {
			this.bankName = bankName;
		}

		public String getAccNumber() {
			return accNumber;
		}

		public void setAccNumber(String accNumber) {
			this.accNumber = accNumber;
		}

		public String getPanName() {
			return panName;
		}

		public void setPanName(String panName) {
			this.panName = panName;
		}

		public String getPanNumber() {
			return panNumber;
		}

		public void setPanNumber(String panNumber) {
			this.panNumber = panNumber;
		}

		public String getIfscCode() {
			return ifscCode;
		}

		public void setIfscCode(String ifscCode) {
			this.ifscCode = ifscCode;
		}
	 public String getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}

	public String getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(String rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getTotRowCount() {
		return totRowCount;
	}

	public void setTotRowCount(int totRowCount) {
		this.totRowCount = totRowCount;
	}

	
	
	public String getHospId() {
		return hospId;
	}

	public void setHospId(String hospId) {
		this.hospId = hospId;
	}

	public char getActiveYN() {
		return activeYN;
	}

	public void setActiveYN(char activeYN) {
		this.activeYN = activeYN;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Date getStartDt() {
		return startDt;
	}

	public void setStartDt(Date startDt) {
		this.startDt = startDt;
	}

	public String getEditFlg() {
		return editFlg;
	}

	public void setEditFlg(String editFlg) {
		this.editFlg = editFlg;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesgName() {
		return desgName;
	}

	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public String getMandMunci1() {
		return mandMunci1;
	}

	public void setMandMunci1(String mandMunci1) {
		this.mandMunci1 = mandMunci1;
	}

	public String getAddrMandMunci1() {
		return addrMandMunci1;
	}

	public void setAddrMandMunci1(String addrMandMunci1) {
		this.addrMandMunci1 = addrMandMunci1;
	}

	public String getDist1() {
		return dist1;
	}

	public void setDist1(String dist1) {
		this.dist1 = dist1;
	}

	public String getMmplties1() {
		return mmplties1;
	}

	public void setMmplties1(String mmplties1) {
		this.mmplties1 = mmplties1;
	}

	public String getMandMunci() {
		return mandMunci;
	}

	public void setMandMunci(String mandMunci) {
		this.mandMunci = mandMunci;
	}

	public String getAddrMandMunci() {
		return addrMandMunci;
	}

	public void setAddrMandMunci(String addrMandMunci) {
		this.addrMandMunci = addrMandMunci;
	}

	public String getSMS() {
		return SMS;
	}

	public void setSMS(String sMS) {
		SMS = sMS;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPin1() {
		return pin1;
	}

	public void setPin1(String pin1) {
		this.pin1 = pin1;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getCity1() {
		return city1;
	}

	public void setCity1(String city1) {
		this.city1 = city1;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getCountry1() {
		return country1;
	}

	public void setCountry1(String country1) {
		this.country1 = country1;
	}

	public String getBiometricFlag() {
		return biometricFlag;
	}

	public void setBiometricFlag(String biometricFlag) {
		this.biometricFlag = biometricFlag;
	}

	public String getServiceFlg() {
		return serviceFlg;
	}

	public void setServiceFlg(String serviceFlg) {
		this.serviceFlg = serviceFlg;
	}

	public String getDigiVerifyReq() {
		return digiVerifyReq;
	}

	public void setDigiVerifyReq(String digiVerifyReq) {
		this.digiVerifyReq = digiVerifyReq;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getDist() {
		return dist;
	}

	public void setDist(String dist) {
		this.dist = dist;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/*public String getDoj() {
		return doj;
	}

	public void setDoj(String doj) {
		this.doj = doj;
	}
*/
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

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public long getUserSeqId() {
		return userSeqId;
	}

	public void setUserSeqId(long l) {
		this.userSeqId = l;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getUnitSeqId() {
		return unitSeqId;
	}

	public void setUnitSeqId(Integer unitSeqId) {
		this.unitSeqId = unitSeqId;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getReportingPersonId() {
		return reportingPersonId;
	}

	public void setReportingPersonId(String reportingPersonId) {
		this.reportingPersonId = reportingPersonId;
	}
	public String getReportingPersonName() {
		return reportingPersonName;
	}

	public void setReportingPersonName(String reportingPersonName) {
		this.reportingPersonName = reportingPersonName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getWorkLoc() {
		return workLoc;
	}

	public void setWorkLoc(String workLoc) {
		this.workLoc = workLoc;
	}

	public String getWeekOffDay() {
		return weekOffDay;
	}

	public void setWeekOffDay(String weekOffDay) {
		this.weekOffDay = weekOffDay;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLocName() {
		return locName;
	}

	public void setLocName(String locName) {
		this.locName = locName;
	}

	public String getShiftCode() {
		return shiftCode;
	}

	public void setShiftCode(String shiftCode) {
		this.shiftCode = shiftCode;
	}
	
	public String getGrpId() {
		return grpId;
	}

	public void setGrpId(String grpId) {
		this.grpId = grpId;
	}

	public String getDesgId() {
		return desgId;
	}

	public void setDesgId(String desgId) {
		this.desgId = desgId;
	}

	
	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getLangId() {
		return langId;
	}

	public void setLangId(String langId) {
		this.langId = langId;
	}

	public String getLocId() {
		return locId;
	}

	public void setLocId(String locId) {
		this.locId = locId;
	}

	public String getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(String crtDt) {
		this.crtDt = crtDt;
	}

	public String getCrtUsr() {
		return crtUsr;
	}

	public void setCrtUsr(String crtUsr) {
		this.crtUsr = crtUsr;
	}

	public void setEmpSearchList(List<CreateEmployeeVO> empSearchList) {
		this.empSearchList = empSearchList;
	}

	public List<CreateEmployeeVO> getEmpSearchList() {
		return empSearchList;
	}

	public void setExportFlag(String exportFlag) {
		this.exportFlag = exportFlag;
	}

	public String getExportFlag() {
		return exportFlag;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setScheme(String scheme) {
		this.scheme = scheme;
	}

	public String getScheme() {
		return scheme;
	}

	public void setUnAllocFlag(String unAllocFlag) {
		this.unAllocFlag = unAllocFlag;
	}

	public String getUnAllocFlag() {
		return unAllocFlag;
	}

	public void setCsvFlag(String csvFlag) {
		this.csvFlag = csvFlag;
	}

	public String getCsvFlag() {
		return csvFlag;
	}

	public String getDesgStatus() {
		return desgStatus;
	}

	public void setDesgStatus(String desgStatus) {
		this.desgStatus = desgStatus;
	}

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}
	public Number getTotValue() {
		return totValue;
	}
	public void setTotValue(Number totValue) {
		this.totValue = totValue;
	}
	public List<LabelBean> getDeptNamesList() {
		return deptNamesList;
	}
	public void setDeptNamesList(List<LabelBean> deptNamesList) {
		this.deptNamesList = deptNamesList;
	}

	
	

}
