package com.ahct.bioMetric.service;

import java.util.ArrayList;
import java.util.List;

import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.common.vo.LabelBean;

public interface BioMetricRegistrationService {

	public String getMacIdForHosp(String userId);
	
	public List<BioMetricVo> getUserDetails(String userId);
	
	public String uploadEmpPhoto(BioMetricVo bioMetricVo );
	
	public boolean bioEnrollStatus(String userId);
	
	public boolean biometricEnroll(BioMetricVo bioMetricVo);
	
	public String biometricAttendance(BioMetricVo bioMetricVo);
	
	public String validateAttendance(BioMetricVo bioMetricVo);
	
	public String validateDispAttendance(BioMetricVo bioMetricVo);
	public List<LabelBean> getOthersList();
	
	public String getUserIdByRole(BioMetricVo biometricVO);
	
	public List<BioMetricVo> getDispUserDetails(String userId,String dispUserId);
	
	public boolean matchFingerPrint(BioMetricVo bioMetricVo);
	
	public List<LabelBean> getDistrictList(String scheme) throws Exception;
	
	public BioMetricVo getBiometricReport(BioMetricVo bioMetricVo);
	
	public List<BioMetricVo> getBiomDepts(String SchemeId);
	
	public List<String> getBiomDsgnLst(String SchemeId,String deptId);
	
	public List<BioMetricVo> getAttendanceReport(BioMetricVo bioMetricVo);
	
	public List<BioMetricVo> getBiomDsgns(String SchemeId,String deptId);

	public List<BioMetricVo> getNewOthersList(BioMetricVo bioMetricVo);

	public List<BioMetricVo> getMacIdForOthers(String userId);

	public List<LabelBean> getReferredCenterDtls(String lStrUserId,
			String roleId);

	public String getMacIdForDisp(LabelBean labelBean);

	public List<LabelBean> getVacantDtls();

	public boolean biometricDispEnroll(BioMetricVo bioMetricVo);

	public String biometricDispAttendance(BioMetricVo bioMetricVo);
}
