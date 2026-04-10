package com.ahct.createEmployee.service;

import java.util.List;

import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfmDesignation;

public interface CreateEmpService {

	List<LabelBean> getDeptDetails();

	List<LabelBean> getDesgDetails();

	List<LabelBean> getWeekList();
	List<LabelBean> getGrpList();
	List<LabelBean> getShiftList();
	List<LabelBean> getLocList();
	List<LabelBean> getLevelList();
	List<LabelBean> getReprtngPerDetails(String level);

	String saveInvstDtls(CreateEmployeeVO createEmpVo);

	String getUnitSeqId();

	List<LabelBean> getVacantDetails(String userDeptId);

	String checkEmpNo(String userId, String editFlg);
	
	String checkLoginName(String loginName);

	List<LabelBean> getDepartment(String department);

	List<LabelBean> getDesgDetails(String vacPos, String dept);
		
	List<LabelBean> getLevelDetails(String vacPos, String dept);

	List<LabelBean> getReportingPerDtls(String vacPos, String dept);
	
	List<LabelBean> getVacantDtls();

	String checkVacantPosition(String dep, String vacPos);

	String getUserSeqId();

	String saveCreateEmpDtls(CreateEmployeeVO createEmpVo);

	List<LabelBean> getVacantList();

	List<LabelBean> getLocationList(String dist, String distId,String lStrHdrId);

	List<LabelBean> getGroupDtls(String vacPos, String dept);

	List<LabelBean> searchEmp(CreateEmployeeVO createEmpVo);
	
	CreateEmployeeVO searchEmployeeList(CreateEmployeeVO createEmpVO);
	


	CreateEmployeeVO loadEmpDtls(String empCode);

	List<LabelBean> getHospList();

	List<LabelBean> searchLogin(CreateEmployeeVO createEmpVo);

	List<CreateEmployeeVO> getHospsMapped(String userId,String grpId);

	List<LabelBean> getHospsNotMapped(String userId,String grpId);

	List<LabelBean> searchHospsList(String searchField);

	String saveMapping(List<CreateEmployeeVO> newList);

	String deleteMappingDtls(List<CreateEmployeeVO> newList);

	String checkUnitName(String unitName);

	List<LabelBean> getReportingPerson(String dept,String level, String lStrScheme);

	List<LabelBean> getStates();

	List<LabelBean> getDistrictList(String state);

	LabelBean getVacPosDtls(String dept, String vacPos);

	String getReportingPerName(String repPersonId);

	String getIFSCCode(String lStrBankCode);

	List<LabelBean> getBankNames();

	String checkEmailId(String emailId);

	CreateEmployeeVO loadEmpBankDtls(String userNo);

	String decryptPassword(String userId);

	List<CreateEmployeeVO> getEmployeeList(String empNumber, String loginName,String allocationFlag,String lStrScheme);
	
	

	String allocateEmployee(CreateEmployeeVO createEmpVo);

	List<LabelBean> getAllocatedDeptsList(String loginName);

	List<LabelBean> getAlloPosDetails(String dept, String loginName);

	LabelBean getallocEmpPosDtls(String dept, String empPos);

	List<LabelBean> getDesignationList(String deptName);

	List<LabelBean> getGroupsList(String desgName,String deptName);

	List<LabelBean> getGroupList(String unitId);

	String saveDesgDetails(CreateEmployeeVO createEmpVO);

	String getDesignationSeq();
	
	List<CreateEmployeeVO> searchDesg(CreateEmployeeVO createEmpVO);
	
	CreateEmployeeVO loadDesgDtls(String designationId);

	void sendBulkEmails();
	public List<LabelBean> getNewDistrictList(String state,String stateType);
	List<LabelBean> getBankCode(String ifscCode);
	
	public LabelBean getNewLocations(LabelBean labelBeanVillage);

	List<LabelBean> getReferredCenterDtls(String lStrUserId, String roleId);
	CreateEmployeeVO getEmployeedata(String empId);
}
