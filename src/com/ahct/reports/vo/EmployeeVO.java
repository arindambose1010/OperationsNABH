package com.ahct.reports.vo;

import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

public class EmployeeVO 
{
	private String fName;
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	private String lname;
	private String fname;
	private String mnum;
	private String lnum;
	private String fnum;
	private String age;
	private String dob;
	private String qul;
	private String cug;
	private String addr1;
	private String addr2;
	private String state;
	private String city;
	private String country;
	private FormFile attach;
	private String pnum;
	private String pin;
	private String lgname;
    private String empId;
	private String locId;
	private String langId;
	private String email;
	private String gender;
	private String bloodGroup;
	private String idMarks;
	private String nativeDistrict;
	private String religion;
	private String mstatus;
	private String passPortNo;
	private String placeOfIssue;
	private String expiryDate;
	private String drivingLicense;
	private String fatherName;
	private String motherName;
	private String fatherDOB;
	private String motherDOB;
	private String nationality;
	private String empWorkLoc;
	private String englishKnows;
	private String teluguKnows;
	private String hindiKnows;
	private String otherKnows;
	private String wordKnows;
	private String excelKnows;
	private String pptKnows;
	private String detailsType;
	private String worklocation; 
	private String doj;
	private String userRole;
	private String photoPath;
	private String casteId;
	private List<LabelBean> casteList;
	private List<LabelBean> distList;
	private String distId;
	private String mrmId;
	private String mId;
	private String vrcrtId;
	private List<LabelBean> qualList;
	private String hod;
	private String ddo;
	private String empType;
	private String emp;
	private String empName;
	private String enrollId;
	private String aadhar;
	private String stoDist;
	private String sto;
	private String empPen;
	private String empCode;
	private String fpTemplateOne;
	private String fpTemplateTwo;
	private String relation;
	private String dir;
	private String tempDir;
	private String dragAttach;
	private String DISPID;
	private String DISPNAME;
	private String USERID;
	private String startDt;
	private String mobileNo;
	private String dist;
	private String addrMandMunci1;
	private String userNo;
	private String addr;
	private  String mandMunci;
	private String addrMandMunci;
	private String deptId;
	private String id;
	private String value;

	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getAddrMandMunci() {
		return addrMandMunci;
	}
	public void setAddrMandMunci(String addrMandMunci) {
		this.addrMandMunci = addrMandMunci;
	}
	public String getMandMunci() {
		return mandMunci;
	}
	public void setMandMunci(String mandMunci) {
		this.mandMunci = mandMunci;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public String getAddrMandMunci1() {
		return addrMandMunci1;
	}
	public void setAddrMandMunci1(String addrMandMunci1) {
		this.addrMandMunci1 = addrMandMunci1;
	}
	public String getDist() {
		return dist;
	}
	public void setDist(String dist) {
		this.dist = dist;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getExportFlag() {
		return exportFlag;
	}
	public void setExportFlag(String exportFlag) {
		this.exportFlag = exportFlag;
	}
	public String getCsvFlag() {
		return csvFlag;
	}
	public void setCsvFlag(String csvFlag) {
		this.csvFlag = csvFlag;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	private String status;
	private String loginName;
	private int start_index;
	private int end_index;
	private Number COUNT = 0;
	private int maxresults;
	private int pageId;
 	private int totalPages;
 	private int totalRecords;
 	private int noOfPages;
 	private int strtIndex;
	private int rows;
	private String  desgName;
	private String wellnessCenter;
	 private List<EmployeeVO> empDetails1;
	 private String exportFlag; 
		private String csvFlag;
		private String serviceFlg;
		private String lName;
	public String getWellnessCenter() {
		return wellnessCenter;
	}
	public List<EmployeeVO> getEmpDetails1() {
		return empDetails1;
	}
	public void setEmpDetails1(List<EmployeeVO> empDetails1) {
		this.empDetails1 = empDetails1;
	}
	public void setWellnessCenter(String wellnessCenter) {
		this.wellnessCenter = wellnessCenter;
	}
	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
	}
	private Number ROWNUM_;

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
	public int getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public int getStrtIndex() {
		return strtIndex;
	}
	public void setStrtIndex(int strtIndex) {
		this.strtIndex = strtIndex;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Number getROWNUM_() {
		return ROWNUM_;
	}
	public void setROWNUM_(Number rOWNUM_) {
		ROWNUM_ = rOWNUM_;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServiceFlg() {
		return serviceFlg;
	}
	public void setServiceFlg(String serviceFlg) {
		this.serviceFlg = serviceFlg;
	}
	public String getDISPNAME() {
		return DISPNAME;
	}
	public void setDISPNAME(String dISPNAME) {
		DISPNAME = dISPNAME;
	}
	public String getUSERID() {
		return USERID;
	}
	public void setUSERID(String uSERID) {
		USERID = uSERID;
	}
	private List<EmployeeVO> empDetails;
	
	public List<EmployeeVO> getEmpDetails() {
		return empDetails1;
	}
	public void setEmpDetails(List<EmployeeVO> empDetails) {
		this.empDetails1 = empDetails;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public String getFpTemplateOne() {
		return fpTemplateOne;
	}
	public void setFpTemplateOne(String fpTemplateOne) {
		this.fpTemplateOne = fpTemplateOne;
	}
	public String getFpTemplateTwo() {
		return fpTemplateTwo;
	}
	public void setFpTemplateTwo(String fpTemplateTwo) {
		this.fpTemplateTwo = fpTemplateTwo;
	}
	public String getEmpCode() {
		return empCode;
	}
	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}
	public String getEmpPen() {
		return empPen;
	}
	public void setEmpPen(String empPen) {
		this.empPen = empPen;
	}
	public String getEnrollId() {
		return enrollId;
	}
	public void setEnrollId(String enrollId) {
		this.enrollId = enrollId;
	}
	public String getAadhar() {
		return aadhar;
	}
	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}
	public String getStoDist() {
		return stoDist;
	}
	public void setStoDist(String stoDist) {
		this.stoDist = stoDist;
	}
	public String getSto() {
		return sto;
	}
	public void setSto(String sto) {
		this.sto = sto;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpType() {
		return empType;
	}
	public void setEmpType(String empType) {
		this.empType = empType;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getHod() {
		return hod;
	}
	public void setHod(String hod) {
		this.hod= hod;
	}
	public String getDdo() {
		return ddo;
	}
	public void setDdo(String ddo) {
		this.ddo = ddo;
	}
	public List<LabelBean> getQualList() {
		return qualList;
	}
	public void setQualList(List<LabelBean> qualList) {
		this.qualList = qualList;
	}
	public String getMrmId() {
		return mrmId;
	}
	public void setMrmId(String mrmId) {
		this.mrmId = mrmId;
	}
	public String getmId() {
		return mId;
	}
	public void setmId(String mId) {
		this.mId = mId;
	}
	public String getVrcrtId() {
		return vrcrtId;
	}
	public void setVrcrtId(String vrcrtId) {
		this.vrcrtId = vrcrtId;
	}
	public List<LabelBean> getDistList() {
		return distList;
	}
	public void setDistList(List<LabelBean> distList) {
		this.distList = distList;
	}
	public String getDistId() {
		return distId;
	}
	public void setDistId(String distId) {
		this.distId = distId;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getCasteId() {
		return casteId;
	}
	public void setCasteId(String casteId) {
		this.casteId = casteId;
	}
	public List<LabelBean> getCasteList() {
		return casteList;
	}
	public void setCasteList(List<LabelBean> casteList) {
		this.casteList = casteList;
	}
	public String getDoj() {
		return doj;
	}
	public void setDoj(String doj) {
		this.doj = doj;
	}
	public String getWorklocation() {
		return worklocation;
	}
	public void setWorklocation(String worklocation) {
		this.worklocation = worklocation;
	}
	public String getDetailsType() {
		return detailsType;
	}
	public void setDetailsType(String detailsType) {
		this.detailsType = detailsType;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getIdMarks() {
		return idMarks;
	}
	public void setIdMarks(String idMarks) {
		this.idMarks = idMarks;
	}
	public String getNativeDistrict() {
		return nativeDistrict;
	}
	public void setNativeDistrict(String nativeDistrict) {
		this.nativeDistrict = nativeDistrict;
	}
	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
	}
	public String getMstatus() {
		return mstatus;
	}
	public void setMstatus(String mstatus) {
		this.mstatus = mstatus;
	}
	public String getPassPortNo() {
		return passPortNo;
	}
	public void setPassPortNo(String passPortNo) {
		this.passPortNo = passPortNo;
	}
	public String getPlaceOfIssue() {
		return placeOfIssue;
	}
	public void setPlaceOfIssue(String placeOfIssue) {
		this.placeOfIssue = placeOfIssue;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getDrivingLicense() {
		return drivingLicense;
	}
	public void setDrivingLicense(String drivingLicense) {
		this.drivingLicense = drivingLicense;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getMotherName() {
		return motherName;
	}
	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}
	public String getFatherDOB() {
		return fatherDOB;
	}
	public void setFatherDOB(String fatherDOB) {
		this.fatherDOB = fatherDOB;
	}
	public String getMotherDOB() {
		return motherDOB;
	}
	public void setMotherDOB(String motherDOB) {
		this.motherDOB = motherDOB;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getEmpWorkLoc() {
		return empWorkLoc;
	}
	public void setEmpWorkLoc(String empWorkLoc) {
		this.empWorkLoc = empWorkLoc;
	}
	public String getEnglishKnows() {
		return englishKnows;
	}
	public void setEnglishKnows(String englishKnows) {
		this.englishKnows = englishKnows;
	}
	public String getTeluguKnows() {
		return teluguKnows;
	}
	public void setTeluguKnows(String teluguKnows) {
		this.teluguKnows = teluguKnows;
	}
	public String getHindiKnows() {
		return hindiKnows;
	}
	public void setHindiKnows(String hindiKnows) {
		this.hindiKnows = hindiKnows;
	}
	public String getOtherKnows() {
		return otherKnows;
	}
	public void setOtherKnows(String otherKnows) {
		this.otherKnows = otherKnows;
	}
	public String getWordKnows() {
		return wordKnows;
	}
	public void setWordKnows(String wordKnows) {
		this.wordKnows = wordKnows;
	}
	public String getExcelKnows() {
		return excelKnows;
	}
	public void setExcelKnows(String excelKnows) {
		this.excelKnows = excelKnows;
	}
	public String getPptKnows() {
		return pptKnows;
	}
	public void setPptKnows(String pptKnows) {
		this.pptKnows = pptKnows;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmpId() {
			return empId;
		}
		public void setEmpId(String empId) {
			this.empId = empId;
		}
		public String getLocId() {
			return locId;
		}
		public void setLocId(String locId) {
			this.locId = locId;
		}
		public String getLangId() {
			return langId;
		}
		public void setLangId(String langId) {
			this.langId = langId;
		}
	public String getLgname() {
		return lgname;
	}
	public void setLgname(String lgname) {
		this.lgname = lgname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getMnum() {
		return mnum;
	}
	public void setMnum(String mnum) {
		this.mnum = mnum;
	}
	public String getLnum() {
		return lnum;
	}
	public void setLnum(String lnum) {
		this.lnum = lnum;
	}
	public String getFnum() {
		return fnum;
	}
	public void setFnum(String fnum) {
		this.fnum = fnum;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getQul() {
		return qul;
	}
	public void setQul(String qul) {
		this.qul = qul;
	}
	public String getCug() {
		return cug;
	}
	public void setCug(String cug) {
		this.cug = cug;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public FormFile getAttach() {
		return attach;
	}
	public void setAttach(FormFile attach) {
		this.attach = attach;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getTempDir() {
		return tempDir;
	}
	public void setTempDir(String tempDir) {
		this.tempDir = tempDir;
	}
	public String getDragAttach() {
		return dragAttach;
	}
	public void setDragAttach(String dragAttach) {
		this.dragAttach = dragAttach;
	}
	public String getDISPID() {
		return DISPID;
	}
	public void setDISPID(String dISPID) {
		DISPID = dISPID;
	}
	//Chandana - 8133 - For abha
	private Date abhaUpdDt;
	private String abhaNumber;
	private String firstName;
	private String middleName;
	private String lastName;
	private String file;
	private String authType;
	
	public Date getAbhaUpdDt() {
		return abhaUpdDt;
	}
	public void setAbhaUpdDt(Date abhaUpdDt) {
		this.abhaUpdDt = abhaUpdDt;
	}
	public String getAbhaNumber() {
		return abhaNumber;
	}
	public void setAbhaNumber(String abhaNumber) {
		this.abhaNumber = abhaNumber;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}

}
