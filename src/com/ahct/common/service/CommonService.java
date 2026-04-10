package com.ahct.common.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmSeq;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.emailConfiguration.EmailVO;

public interface CommonService {
	/**
     * ;
     * @param String seqIdentifier
     * @return EhfmSeq ehfmSeq
     * @function This Method is Used For getting EhfmSeq data based on seqIdentifier
     */
	public EhfmSeq getSeqNextVal(String seqIdentifier);
	public void updateSequenceVal(EhfmSeq ehfmSeq);
	/**
     * ;
     * @param EhfmSeq ehfmSeq
     * @function This Method is Used For saving seqVal in EhfmSeq Table
     */
	
	
	  
	  
	//public SgvcSeqMst getNextVal(String seqIdentifier);
	//public void updateSequence(SgvcSeqMst sgvcSeqMst);
	
	
	public List<LabelBean> getDistrictList() throws Exception;
	public List<LabelBean> getMandalList(String distId) throws Exception;
	public List<LabelBean> getVillageList(String mandalId) throws Exception;
	public List<LabelBean> getHamletList(String villageId) throws Exception;
	public List<LabelBean> getCastes() throws Exception;
	public List<LabelBean> getRelations() throws Exception;
	public List<LabelBean> getAsrimLocations(String locHdrId,String locParntId) throws Exception;
	public String getLocationName(String locId) throws Exception;
	public List<LabelBean> getOccupations()throws Exception;
	public List<LabelBean> getComboDetails(String cmbHdrId) throws Exception;
	public int getPhaseId(String distId)throws Exception;
	public List<LabelBean> getPhases() throws Exception;
	public String getDecryptedPswd(String pStrLoginName);
	public String getCmbHdrname(String cmbHdrId,String cmbVal)throws Exception;
	//public List<LabelBean> getGoDetails(String goType);
	public List<LabelBean> getHospitals() throws Exception;
	public List<LabelBean> getPersonalHistory(String parntCode)throws Exception;
	public List<LabelBean> getFamilyHistory()throws Exception;
	public List<LabelBean> getInvestigations()throws Exception;
	public List<LabelBean> getDrugs()throws Exception;
	public List<LabelBean> getGenExaminFndgs(String schemeId) throws Exception;
	public List<LabelBean> getPastIllnessHitory()throws Exception;
	public List<LabelBean> getAllDiagnosisCat()throws Exception;
	public List<LabelBean> getDiagnosisSubCat(String lStrDiagCat);
	public List<LabelBean> getMainSymptonLst();
	public List<LabelBean> getAsriCategoryList(String hospId,String scheme);
	public List<String> getICDCategoryList(String asriCatCode, String procType);
	public List<LabelBean> getAsriDrugs(String drugSubTypeId) throws Exception;
	public List<LabelBean> getDynamicWrkFlowDtls(String pCurrStatus,String pCurrRole,String pMainModule,String pSubModule);
	public List<LabelBean> getCategorys(String pStrCatId,String userId, String caseID);
	public List<LabelBean> getProcedures(String pStrCatCode , String pStrProcCode,String pStrHospId,String Scheme,String dentalEnhFlag);
	public List<LabelBean> getProceduresNABH(String pStrCatCode , String pStrProcCode,String pStrHospId,String Scheme,String dentalEnhFlag, String ipFlag);
	public List<LabelBean> getCategoryList(String userId, String userRole);
	public String getPastIllnessHitoryNames(String illnessHistory);
	public String getFamilyHisNames(String illnessHistory);
	public List<LabelBean> getICDCategoryList(String pStrCatId,String userId,String hosType);
	
	public Long getCaseTherapyInvestId();
	/**
     * ;
     * @param PatientSmsVO patientSmsVO
     * @return String Msg
     * @function This method is used for sending sms to the specified patient
     */
	public String sendPatientSms(PatientSmsVO patientSmsVO);
	/**
     * ;
     * @param EmailVO emailVO
     * @param Map model
     * @function This method is used for sending Email to the specified patient
     */
	public void generateMail(EmailVO emailVO, Map model);
	
	/**
	 * @function This method is used for sending Email to the specified receipent without any images
	 * @param emailVO
	 * @param model
	 */
	public void generateNonImageMail(EmailVO emailVO,Map model);
	/**
     * ;
     * @param String userId
     * @param String caseId
     * @param String lockStatus
     * @return int recordsInserted
     * @function This method is used for locking a specific case against a specified user
     */
	public int lockCase(String userId,String caseId,String lockStatus);
	/**
     * ;
     * @param String userId
     * @param String caseId
     * @function This method is used for unlocking cases locked by a specified user
     */
	public void unlockCases(String userId);
	/**
     * ;
     * @param String caseId
     * @function This method is used for getting lock status for a specific case
     */
	public String getLockStatus(String caseId);
	public List<LabelBean> getInvestBlockName();
	public List<LabelBean> getInvestBlockNameNew(PatientVO patientVO);
	public List<LabelBean> getOpDrugs(String pStrIpOpType)throws Exception;
	public List<LabelBean> getRouteType(String pStrIpOpType)throws Exception;
	public List<LabelBean> getStrengthType(String pStrIpOpType) throws Exception;
	public String getSequence(String pStrSeqName)throws Exception;
	public List<String> getDocListBySpec(String asriCatCode, String hospCode,String scheme);
	public String getHospType(String hospId);
	public void saveToBuffer(String message,String phoneList);
	public String getDutyDoctorById(String regNo);
	public List<LabelBean> getOpDrugsAuto() throws Exception;
	public EhfmHospitals getHospInfo(String hospId) throws Exception;
	public List<LabelBean> getICDProcedures(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag);
	public String ckDentalClinic(String userid, String caseId);
	public List<LabelBean> getDentalInvestBlockNameNew(PatientVO patientVO);
	public List<LabelBean> getDentalMainSymptonLst();
	public List<LabelBean> getDentalGenExaminFndgs(String schemeId) throws Exception;
	public List<LabelBean> getDentalPersonalHistory(String parntCode) throws Exception;
	public String saveToAsritSms(Map<String,Object> lmap);
	public List<LabelBean> getGeneralInvest(String patientId,String dispId);//sai krishna:CR#8134:API Integration main.
	public List<LabelBean> getDistrictListNew();
	public List<LabelBean> getSpecialityList();
	public List<LabelBean> getDrugTypeList(String drugTypeCode);
	public List<LabelBean> getHosptlDtlsList();
	public List<LabelBean> getMedicalCatgryList(String medicalSpclty);
	public List<LabelBean> getMedicalSpltyList(String loggedUserHospId, String schemeId);
	public List<LabelBean> getMedicalCatgryListDflt();
	public String getloggedUserHospId(String lStrUserId, String schemeId);
	public String getloggedUserDispId(String lStrUserId, String schemeId);
	public List<LabelBean> getTelephncHospitals();
	public List<LabelBean> getDispSpecialities();
	public List<LabelBean> getCardDetails(String cardNo);
	public LabelBean getNewLocations(LabelBean labelBeanVillage);
	public List<LabelBean> getLocationsNew(String locHdrId, String distId, String stateType);
	public String getActionDoneName(String lastToken, String string);
	public List<LabelBean> getMedicalSpecialities(String hospId, String schemeId, String critical);
	public List<LabelBean> getMedicalCatgryListCritical(String splcty,String critical);
	public List<LabelBean> getAllUsersFromDepts(String deptId);
	public List<LabelBean> getapp(String dispname, int startIndex,
			int maxResults, int pageNo, String healthCardNo, String toDate);
	public List<LabelBean> checkUsrMapped(String lStrUserId);
	public List<LabelBean> getwellnessapp();
	public List<LabelBean> getDoctorNames(String lStrUserId, String dispId);
	public String saveEmployeeDtls(PatientVO patientVo);
	public PatientVO getEmployeeDtls(PatientVO patientVo);
	public PatientVO getDocDtls(PatientVO patientVo);
	public List<LabelBean> getMobileNum(String docId, String startDate,
			String toDate);
	public String saveEmployeeDtls(String docId, Date leavedate, String id);
	public List<LabelBean> getDoctorDates(String docId);
	public String getspecialityDisp(String cardNo, String dispname,
			String appntdate);
	public List<LabelBean> getappNims(int startIndex,
			int maxResults, int pageNo, String fromDate, String toDate);
	public List<LabelBean> getComboDetailsNew(String string);
	public void generateMailNew(EmailVO emailVO, Map<String, String> model, Map<String, List<String>> model1);
	public String getFlagMonthBased(String dispId, String monthYr);//Chandana - 8251 - New method for getting the flag based on the dispId, year and month
}
