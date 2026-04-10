package com.ahct.createEmployee.form;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.common.vo.LabelBean;


public class CreateEmployeeForm extends ActionForm{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String deptName;
    private String desgName;
    private String loginName;
    private String empName;
    private String groupName;
    private String searchGrp;
    private String status;
    private int noOfPages;
    private String vacPosName;
	private String errMsg;
    private int startIndex;
    private String tableHeading;
    private String pageHeading;
    private String hospName;
    private String weekOffDay;
    private String shift;
    private String level;
    private String repPerson;
    private String successFlag;
    private String fName;
    private String lName;
    private String gender;
    private String empNumber;
    private String  empDob;
    private String  empDoj;
    private String email;
    private String mobNumber;
    private List<CreateEmployeeVO> EmpList;

	private String postalCode;
    private String district;
    private String state;
    private String country;
    private String empAddress;
    private String distType;
	private String mandals;
    private String villages;  
    private String digiSignReq;
	private String bioMetricReq;
	private String zimbraReq;
    private String inService;
    
    
	private String accHolderName;
    private String accNumber;
    private String panCardName;
    private String panNumber;
    private String bankName;
    private String ifscCode;
    private String editFlag;
  private String schemeType;
   private String schemeFlag;
  
   private String allocationFlag;
   private String empPosName;
   private String allocatedDeptName;
   private String allocRepPerson;
   private String templatePath;
   private FormFile attachment;
   
   
   private String designationId;
   private String designationName;
   private String designationSname;
   private String activate;
   private String deactivate;
   private String desgStatus;
   private String groupType;
   private String index;
   
//For pagination
   private int startPage;
   private int endPage;
	private String showPage;
	private String totalRows;
	private String rowsPerPage; 
	private String prev;
	private String next;
	private  String fromDisp;
	private String aadharCardNum;
  
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
public String getTotalRows() {
	return totalRows;
}
public String getIndex() {
	return index;
}
public void setIndex(String index) {
	this.index = index;
}
public void setTotalRows(String totalRows) {
	this.totalRows = totalRows;
}
public String getRowsPerPage() {
	return rowsPerPage;
}
public void setRowsPerPage(String rowsPerPage) {
	this.rowsPerPage = rowsPerPage;
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
public static long getSerialversionuid() {
	return serialVersionUID;
}
	
public String getGroupType() {
	return groupType;
}
public void setGroupType(String groupType) {
	this.groupType = groupType;
}
private List<CreateEmployeeVO> searchDesgList;
   
   
   public String getActivate() {
	return activate;
}
public void setActivate(String activate) {
	this.activate = activate;
}
public String getDeactivate() {
	return deactivate;
}
public void setDeactivate(String deactivate) {
	this.deactivate = deactivate;
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
public void setDesignationName(String designationName) {
	this.designationName = designationName;
}
public String getDesignationSname() {
	return designationSname;
}
public void setDesignationSname(String designationSname) {
	this.designationSname = designationSname;
}

   
   
    public String getAccHolderName() {
		return accHolderName;
	}
	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public String getPanCardName() {
		return panCardName;
	}
	public void setPanCardName(String panCardName) {
		this.panCardName = panCardName;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
    public String getDigiSignReq() {
		return digiSignReq;
	}
	public void setDigiSignReq(String digiSignReq) {
		this.digiSignReq = digiSignReq;
	}
	public String getBioMetricReq() {
		return bioMetricReq;
	}
	public void setBioMetricReq(String bioMetricReq) {
		this.bioMetricReq = bioMetricReq;
	}
	public String getInService() {
		return inService;
	}
	public void setInService(String inService) {
		this.inService = inService;
	}
    
    public String getDistType() {
		return distType;
	}
	public void setDistType(String distType) {
		this.distType = distType;
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
    public int getNoOfPages() {
		return noOfPages;
	}
	public void setNoOfPages(int noOfPages) {
		this.noOfPages = noOfPages;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public String getTableHeading() {
		return tableHeading;
	}
	public void setTableHeading(String tableHeading) {
		this.tableHeading = tableHeading;
	}
	public String getPageHeading() {
		return pageHeading;
	}
	public void setPageHeading(String pageHeading) {
		this.pageHeading = pageHeading;
	}
 
   
    public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDesgName() {
		return desgName;
	}
	public void setDesgName(String desgName) {
		this.desgName = desgName;
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
	

	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public String getHospName() {
		return hospName;
	}
	public void setVacPosName(String vacPosName) {
		this.vacPosName = vacPosName;
	}
	public String getVacPosName() {
		return vacPosName;
	}
	public void setWeekOffDay(String weekOffDay) {
		this.weekOffDay = weekOffDay;
	}
	public String getWeekOffDay() {
		return weekOffDay;
	}
	public void setShift(String shift) {
		this.shift = shift;
	}
	public String getShift() {
		return shift;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getLevel() {
		return level;
	}
	public void setRepPerson(String repPerson) {
		this.repPerson = repPerson;
	}
	public String getRepPerson() {
		return repPerson;
	}
	public void setSuccessFlag(String successFlag) {
		this.successFlag = successFlag;
	}
	public String getSuccessFlag() {
		return successFlag;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getfName() {
		return fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getlName() {
		return lName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGender() {
		return gender;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpDob(String empDob) {
		this.empDob = empDob;
	}
	public String getEmpDob() {
		return empDob;
	}
	public void setEmpDoj(String empDoj) {
		this.empDoj = empDoj;
	}
	public String getEmpDoj() {
		return empDoj;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
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
		public String getDistrict() {
			return district;
		}
		public void setDistrict(String district) {
			this.district = district;
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
		public void setEditFlag(String editFlag) {
			this.editFlag = editFlag;
		}
		public String getEditFlag() {
			return editFlag;
		}
		public void setSchemeFlag(String schemeFlag) {
			this.schemeFlag = schemeFlag;
		}
		public String getSchemeFlag() {
			return schemeFlag;
		}
		public void setSchemeType(String schemeType) {
			this.schemeType = schemeType;
		}
		public String getSchemeType() {
			return schemeType;
		}
		public void setAllocationFlag(String allocationFlag) {
			this.allocationFlag = allocationFlag;
		}
		public String getAllocationFlag() {
			return allocationFlag;
		}
		public void setEmpPosName(String empPosName) {
			this.empPosName = empPosName;
		}
		public String getEmpPosName() {
			return empPosName;
		}
		public void setAllocatedDeptName(String allocatedDeptName) {
			this.allocatedDeptName = allocatedDeptName;
		}
		public String getAllocatedDeptName() {
			return allocatedDeptName;
		}
		public void setAllocRepPerson(String allocRepPerson) {
			this.allocRepPerson = allocRepPerson;
		}
		public String getAllocRepPerson() {
			return allocRepPerson;
		}
		
		public String getSearchGrp() {
			return searchGrp;
		}
		public void setSearchGrp(String searchGrp) {
			this.searchGrp = searchGrp;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getZimbraReq() {
			return zimbraReq;
		}
		public void setZimbraReq(String zimbraReq) {
			this.zimbraReq = zimbraReq;
		}
		public String getTemplatePath() {
			return templatePath;
		}
		public void setTemplatePath(String templatePath) {
			this.templatePath = templatePath;
		}
		public FormFile getAttachment() {
			return attachment;
		}
		public void setAttachment(FormFile attachment) {
			this.attachment = attachment;
		}
		public List<CreateEmployeeVO> getSearchDesgList() {
			return searchDesgList;
		}
		public void setSearchDesgList(List<CreateEmployeeVO> searchDesgList) {
			this.searchDesgList = searchDesgList;
		}
		public String getDesgStatus() {
			return desgStatus;
		}
		public void setDesgStatus(String desgStatus) {
			this.desgStatus = desgStatus;
		}
		public List<CreateEmployeeVO> getEmpList() {
			return EmpList;
		}
		public void setEmpList(List<CreateEmployeeVO> empList) {
			EmpList = empList;
		}
		public String getFromDisp() {
			return fromDisp;
		}
		public void setFromDisp(String fromDisp) {
			this.fromDisp = fromDisp;
		}
		public String getAadharCardNum() {
			return aadharCardNum;
		}
		public void setAadharCardNum(String aadharCardNum) {
			this.aadharCardNum = aadharCardNum;
		}
		
  	
}
