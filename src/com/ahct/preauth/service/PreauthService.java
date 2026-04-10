package com.ahct.preauth.service;


import java.util.List;
import java.util.Map;

import com.ahct.attachments.vo.AttachmentVO;
import com.ahct.bioMetric.vo.BioMetricVo;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientDocAttach;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.preauth.vo.cochlearCaseVO;

public interface PreauthService {
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId) throws Exception;
	//public Map getPatientOP(HashMap lParamMap);
	public List<EhfPatientDocAttach> getOnBedPhotoDtls(String patId) throws Exception;
	//public Map getFraudCrDtls(String pStrCaseId);
	//public Map saveFraudCrDtls(CmaAuditVO cmaVO);
    public List<LabelBean> getDocSpecList(PreauthVO preauthVO);
	public List<LabelBean> getDocAvailLst(PreauthVO preauthVO);
	public String getDocdetails(PreauthVO preauthVO);
	public List<LabelBean> getSubDiseases(PreauthVO preauthVO);
	public List<LabelBean> getSurgeryList(PreauthVO preauthVO);
	public List<LabelBean> getSpecialInvestigationLst(PreauthVO preauthVO);
	public List<AttachmentVO> getcaseAttachments(PreauthVO preauthVO);
	public String getSurgeryDtls(PreauthVO preauthVO);
	public PreauthVO getTelephonicDtls(String caseId);
	public List<PreauthVO> getcaseSurgertDtls(String caseId);
	public String savePreauth(PreauthVO preauthVO);
	public String checkMandatoryAttch(String pStrCaseId,String attachType, String ipFlag,String drugFlag);
	public String sentForPreauth(PreauthVO preauthVO);
	public PreauthVO getQuestionerData(String pStrcaseId);
	public String saveQuestionnaire(PreauthVO preauthVO);
	public String sentVerifyPanel(PreauthVO preauthVO);
	public List<PreauthVO> getAuditTrail(PreauthVO PStrpreauthVO);
	public String checkClinicalNotes(String pStrCaseId);
	public String savePotoAttach(AttachmentVO attachmentVO);
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId);
	public PreauthVO getTreatingDocDtls(String patientId,String caseId);
	public String upldSplInvestAttachments(AttachmentVO attachmentVO);
	public String deleteSplInvstAttach(String pStrsno,String pStrCrtUsr);
	public PreauthVO getNamDeclarationDtls(String pStrPatId);
	public List<LabelBean> getHospStayist(String pStrenhType);
	public List<LabelBean> getHospStayQuantList(String pStrenhTypeId);
	public Integer getQuantAmount(String quantId);
	public List<PreauthVO> getEnhancementList(String pStrcaseId, String patientType);
	public String getCaseStatus(String caseId);
	public String saveEnhancementPreauth(PreauthVO preauthVO);
	public List<CommonDtlsVO> getComorBidVals();
	public List<CommonDtlsVO> getComorbidsDisList(String pStrCaseId,String pStrProcIds);
	public List<LabelBean> getSymptomsDtls(String lStrCaseId);
	public List<LabelBean> getInvestigations(String pStrpatientId);
	public List<LabelBean> getDocCount(String pStrCaseId,String pStrRejFlag);
	public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId);
	public List<DrugsVO> getDrugs(String pStrPatId,String flag);
	public List<PreauthVO> getCasesWorkList(String caseId);
	public String getEmpNameById(String id);
	public String getDoctorById(String id);
	public List<DrugsVO> getIpDrugs(String pStrPatId,String flag);
	public String getDocSpeciality(String docRegNo);
	public String getFollowUpStatus(String parameter);
	public String  getceoApprovalFlag(String preauthCaseId);
	public Integer getQuantAmountForNabh(String quantId, String nabhFlag,String caseId);
	public List<AttachmentVO> getAttachmentsForCase(String caseId);
	public String cancelPreauth(PreauthVO preauthVO);
	public PatientVO retrieveCardDetails(PatientVO patientVO);
	public List<String> getDrugSubList(String drugTypeCode);
	public CasesSearchVO getCaseCommonDtls(String caseId);
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo);
	public String getDrugDetails(String drugCode);
	public List<cochlearCaseVO> getCochlearCaseDtls(String CaseId,String type);
	public String saveCochlearCaseDtls(cochlearCaseVO cochlearCaseVo);
	  /*
     * Used to get all the therapies of the case
     */
    public List<CasesSearchVO> getDopDtls(String caseId);
   
    /*added by venkatesh for ceo sendback functionality*/
    public PreauthVO getCaseDetails(String caseId);
    
    public String updateSentBackClaims(ClaimsFlowVO claimFlowVO);
    
    public boolean verifyClaimPending(ClaimsFlowVO claimFlowVO);
    
    
    public boolean verifyFollowUpClaimPending(String followupId);
    
    public String getPatientScheme(String lStrCaseId);
    public String getCaseScheme(String lStrCaseId);
	public String getNABHhosptls(String intiid, String caseno);
	public int getCount(String caseId,String roleId);
	
	/***
	 * @author Kalyan
	 * @param Patient ID
	 * @return Follow Up Flag a String
	 * @function This Method is Used to check Dental Follow UP Eligibilty for a Specific Patient. 
	 */
	public PreauthVO checkDenFlp(String caseID);

	/***
	 * @author Kalyan
	 * @param Case ID and Hosp ID
	 * @return Status of Preauth-Approval or initiation
	 * @function This Method is Used to check TG Patient Registration in TG Hospital  
	 */
	public boolean checkTGPatReg(String hospId,String caseId);
	public String savePreauthDtls(PreauthVO preauthVO); 
	
	
	/***
	  * @author Kalyan
	  * @param Case ID
	  * @return Dental Flag
	  * @method Checks Whether the case is Dental or not  
	  */
	 public String checkDentalCase(String caseId);
	public List<CasesSearchVO> getPreauthDtls(String cardNo);
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId, String fromDisp);
	public EhfPatient getPatDtls(String patientId);
	public String saveEnhDtls(PreauthVO preauthVO);
	public String deleteEnhnDtls(PreauthVO preauthVO);
	public void saveImplantsPreauth(PreauthVO preauthVO);
	public List<DrugsVO> getMedicalDrugs(String patientId, String string);
	public String holdUnholdCase(PreauthVO preauthVO);
	public String updateMedcoRemarks(PreauthVO preauthVO); 
	public ClaimsFlowVO getDrugDetailsData(ClaimsFlowVO claimFlowVO);
	public ClaimsFlowVO getInvestigationDetails(ClaimsFlowVO claimFlowVO);
	public ClaimsFlowVO saveEnhanceAmounts(ClaimsFlowVO claimFlowVO);
	public String getStatusCase(String caseId); 
	

	public List<PreauthVO> getDialysisReportList(String caseId);
	public List<BioMetricVo> getOutTimeCapture(String caseId);
	public Long getDialysisCycles(String caseId);
	public Long getDialysisCyclesDone(String caseId);
	public String saveDialysisDtls(PreauthVO preauthVO); 
	public List<LabelBean> getDrugPriceList(String drugType);
	public Integer getDrugQuantAmount(String quantId);
	public List<LabelBean> saveDrugDetails(LabelBean drugVO);
	public List<LabelBean> deleteDrugDetails(LabelBean drugVO);
	public List<LabelBean> getSavedDrugDetails(String caseId);
	public String saveDrugPreauthDtls(PreauthVO preauthVO);
	public String getDrugsAmount(String caseId);
	public List<LabelBean> getDrugTypeList(String hospId);
	public Map<String, Object> getLuceneSearch(Map<String, Object> paramMap);
	public String checkAmountExceed(List<LabelBean> drugvoList);
	public String getEnhancementSum(String caseId, String string);
	public String getOldDrugsAmount(String caseId);
	public String saveMedicalCaseDetails(PreauthVO preauthVO);
	public String getpreviousStatus(String caseId);
	public String updateEnhAttch(PreauthVO preauthVO);
	public String getHospScheme(String caseId);
	
}
