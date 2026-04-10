package com.ahct.patient.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.model.EhfOpConsultData;
import com.ahct.followup.vo.FollowUpVO;
import com.ahct.model.EhfAisDrugs;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientTests;
import com.ahct.model.EhfWcIndentVndrGst;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.LabelVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.SpecialityVO;
import com.ahct.model.EhfmDentalProcCriteria;
import com.ahct.model.EhfmDentalProcCriteriaPK;


public interface PatientDao {
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
	public PatientVO retrieveCardDetails(PatientVO patientVo) throws Exception;
	/**
     * ;
	
     * @param String payGrade
     * @return String slab
     * @function This Method is Used For Retrieving Slab type for given payGrade
     */
	public String getSlabType(String payGrade)throws Exception;
	/**
     * ;
     * @param PatientVO patientVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	public PatientVO retrieveAisCardDetails(PatientVO patientVo) throws Exception;
	public int registerPatient(PatientVO patientVO)throws Exception;
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
    public List<LabelBean> getHospital(String userId,String roleId)throws Exception;
    /**
     * ;
     * @param HashMap<String,String> searchMap
     * @return PatientVO registeredPatientsList
     * @function This Method is Used For retrieving list of registered patients for given search data
     */
    public PatientVO getRegisteredPatients(HashMap<String, String> searchMap)throws Exception;
    /**
     * ;
     * @param String patientId
     * @return AsritPatient asritPatient
     * @function This Method is Used For retrieving PatientDetails for given PatientId
     */
    public EhfPatient getPatientDetails(String patientId) throws Exception;
    /**
     * ;
     * @return List<String> complaintList
     * @function This Method is Used For getting Complaints List
     */
    public List<String> getComplaints(String mainCompId) throws Exception;
    /**
     * ;
     * @param fromDisp 
     * @param grpId 
     * @param String doctorType
     * @param String hospId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For retrieving Doctors List associated for the specified hospId
     */
    public List<LabelBean> getDoctorsList(String doctorType,String hospId,String scheme, String fromDisp, String grpId)throws Exception;
    /**
	 * ;
	 * @param PatientVO patientVO
	 * @return String caseId
	 * @function This Method is Used For Registering Patient Case as IP/OP/Chronic OP
	 */
    public String saveCaseDetails(PatientVO patientVO)throws Exception;
    /**
     * ;
     * @param String seqIdentifier
     * @return String seqValue
     * @function This method is used to get next sequence value for specified sequence
     */
    public String getSequenceVal(String pStrSeqIdentifier)throws Exception;
    /**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
    public String getHospName(String hospId)throws Exception;
    /**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
    public String getLocationName(String locId)throws Exception;
    /**
     * ;
     * @param String doctorType
     * @param String hospId
     * @param String doctorId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For getting Selected Doctor Details
     */
    public List<LabelBean> getSelDocDetails(String doctorType,String hospId,String docId,String schemeId)throws Exception;
    /**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations() throws Exception;
    /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
    public String getRelationName(String relationId) throws Exception;
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
    public String getPatientPhoto(String cardNo)throws Exception;
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo of journalist
     */
    public String getJournalistPhoto(String cardNo);
    /**
     * ;
     * @param PatientVO patientVO
     * @return int records inserted
     * @function This Method is Used For Saving telephonic information
     */
	public int captureTelephonicPatientDtls(PatientVO patientVO)throws Exception;
	/**
     * ;
     * @param PatientVO patientVO
     * @return PatientVO patientVO
     * @function This Method is Used For Retrieving telephonic registered details for specified telephonicId
     */
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo)throws Exception;
	/**
     * ;
     * @param String hospId
     * @return List<LabelBean> ICDCategorylist 
     * @function This Method is Used For getting ICD Category List for specific hospital
     */
	public List<LabelBean> getTherapyCategory(String hospId)throws Exception;
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> ICDSubCategorylist 
     * @function This Method is Used For getting ICD Sub Category List for specific category
     */
	public List<LabelBean> getTherapySubCategory(String categoryId)throws Exception;
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> procList 
     * @function This Method is Used For getting ICD Procedure List for specific category
     */
	public List<LabelBean> getTherapyProcedure(String subCategoryId)throws Exception;
	/**
     * ;
     * @param String occupationId
     * @return String occupationName
     * @function This Method is Used For getting occupationName for given occupationId from EhfmOccupationMst table
     */
	public String getOccupationName(String occupationId)throws Exception;
	/**
     * ;
     * @return List<LabelBean> diagnosisTypesList
     * @function This Method is Used For getting Diagnosis Types List(EHFM_DIAGNOSIS_MST--EhfmDiagnosisMst)
     */
	public List<LabelBean> getDiagnosisTypes()throws Exception;
	/**
     * ;
     * @param String diagnosisId
     * @return List<LabelBean> diagnosisMainCategoryList
     * @function This Method is Used For getting Diagnosis Main Category List(EHFM_DIA_MAINCAT_MST--EhfmDiagMainCatMst)
     */
	public List<LabelBean> getDiagnMainCategory(String diagnosisId)throws Exception;
	/**
     * ;
     * @param String mainCategoryId
     * @return List<LabelBean> diagnosisCategoryList
     * @function This Method is Used For getting Diagnosis Category List(EHFM_DIA_CATEGORY_MST--EhfmDiagCategoryMst)
     */
	public List<LabelBean> getDiagnCategory(String mainCategoryId)throws Exception;
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> diagnosisSubCategoryList
     * @function This Method is Used For getting Diagnosis Sub Category List(EHFM_DIA_SUBCAT_MST--EhfmDiagSubCatMst)
     */
	public List<LabelBean> getDiagnSubCategory(String categoryId)throws Exception;
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDiseaseList
     * @function This Method is Used For getting Diagnosis Disease List(EHFM_DIA_DISEASE_MST--EhfmDiagDiseaseMst)
     */
	public List<LabelBean> getDiagnDisease(String code,String param)throws Exception;
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDisAnatomicalList
     * @function This Method is Used For getting Diagnosis Disease Anatomical List(EHFM_DIA_DISANATOMICAL_MST--EhfmDiagDisAnatomicalMst)
     */
	public List<LabelBean> getDiagnDisAnatomical(String code,String param)throws Exception;
	/**
     * ;
     * @return List<LabelBean> mainComplaintList
     * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
     */
	public List<LabelBean> getMainComplaintLst()throws Exception;
	/**
     * ;
     * @param String mainSymptomCode
     * @return List<String> symptomList
     * @function This Method is Used For retrieving Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	public List<String> getSymptomList(String mainSymptomCode, String subSymptomCode)throws Exception;
	/**
     * ;
     * @param String icdCatCode
     * @param String asriCatCode
     * @param String hospId
     * @return List<String> procedureList
     * @function This Method is Used For retrieving Icd procedure List(EHFM_MAIN_THERAPY_NABH--EhfmTherapyProcMst)
     */
	public List<String> getIcdProcList(String icdCatCode,String asriCatCode, String hospId,String state,String hosType, String treatType );
	/**
     * ;
     * @param String drugTypeCode
     * @return List<String> drugSubTypeList
     * @function This Method is Used For getting Drug Sub Type List From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public List<LabelBean> getDrugSubList(String drugTypeCode)throws Exception;
	
	
	public List<LabelBean> getOpDrugSubList(String drugTypeCode,String pStrIpOpType);
	public List<LabelBean> getOpPharSubList(String drugTypeCode,String pStrIpOpType);
	public List<LabelBean> getOpChemSubList(String pharSubCode,String pStrIpOpType);
	public List<LabelBean> getChemSubList(String cSubGrpCode,String pStrIpOpType);
	
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public String getDrugDetails(String drugCode)throws Exception;
	/**
     * ;
     * @param String icdProcCode
     * @return String procCode
     * @function This Method is Used For getting Procedure Codes From EhfmTherapyProcMst(EHFM_THERAPY_PROC_MST )
     */
	public String getTherapyProcCodes(String icdProcCode)throws Exception;
	/**
     * ;
     * @return List<LabelBean> opCategoryList
     * @function This Method is Used For getting Therapy OP Category Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<LabelBean> getTherapyOPCategory() throws Exception;
	/**
     * ;
     * @param String opMainCode
     * @return List<LabelBean> opPkgList
     * @function This Method is Used For getting Therapy OP Package Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<LabelBean> getOpPkgList(String opCatCode)throws Exception;
	/**
     * ;
     * @param String opPkgCode
     * @return List<LabelBean> opIcdList
     * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<LabelBean> getOpIcdList(String opCode)throws Exception;
	/**
     * ;
     * @param String cardNo
     * @param String opCatCode
     * @return int count
     * @function This Method is Used For getting count of cases with given cardNo and therapyOPCategory whose medication period is not completed
     */
	public int validateTherapyOPCat(String cardNo,String opCatCode)throws Exception;
	
	
	public List<DrugsVO> getChronicDetails(String cardNo)throws Exception;
	public List<String> getSubSymptomList(String mainSymptomCode);
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId, String fromDisp);
	public List<SpecialityVO> getPatientSpecialities(String fromDisp, String patientId);
	public String deleteInvestigations(String caseId, String procCode,String investId,String asriCode);
	public String deleteGenInvest(String caseId, String investId);
	public List<String> getInvestigations(String lStrBlockId);
	public List<LabelBean> getRouteList(String routeTypeCode,String pStrIpOpType);
	public List<LabelBean> getStrengthList(String strengthTypeCode,String pStrIpOpType);
	public String getSequence(String pStrSeqName) throws Exception;
	public List<CommonDtlsVO> getDtrsFormDtls(String caseId, String fromDisp) throws Exception;
	public PatientVO getOPCases(HashMap<String, String> searchMap);
	public String getDutyDoctorById(String regNo);
	public String getProcedureType(String procCode,String asriCode);
	
	/**
	 * ;
	 * @param String caseId
	 * @return List<CommonDtlsVO> caseDetailsList
	 	 * @function This Method is Used For common details for the caseId specified
	 */
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId)throws Exception;
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<LabelBean> docAavilList
	 * @function This Method is Used For getting available doctor list for given hospital and specialty
	 */
	public List<LabelBean> getDocAvailLst(PatientVO patientVO)throws Exception;
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<String> specInvestList
	 * @function This Method is Used For getting special investigation list for given therapy
	 */
	public List<String> getSpecialInvestigationLst(PatientVO patientVO)throws Exception;
	
	public List<PreauthVO> getcaseSurgeryDtls(String caseId);
	public List<LabelBean> getSymptomsDtls(String lStrCaseId);
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId);
	public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId, String fromDisp);
	public String getEmpNameById(String id);
	public String getDoctorById(String id);
	public List<DrugsVO> getDrugs(String pStrPatId,String flag);
	public String getHospActiveStatus(String userId, String roleId);
	
	/**
     * @param String icdProcCode
     * @return int dentalUnits
     * @function This Method is Used For getting Dental Units List for given Procedure
     */
	public int getDenUnitsList(String icdProcCode);
	/*
	 * Added by Srikalyan to Verify New Born
	 * Baby Condition for Mithra based on 
	 * the scheme
	 */
	public String checkNewBornCond(String lStrUserId);
	
	public String getInvestPrice(String blockId,String investId);
	
	
	/*Added by venkatesh for Dental TG*/
	
	
	
	
	public String getDaysActiveOP(String caseId);
	
	public List<LabelBean> getConsultDtls(String patientId);
	
	public List<CasesSearchVO> getOpPastHistoryDetails(CasesSearchVO vo);
	
	public String getEmpCode(String PatientId);
	


	/*
	 * Added by Srikalyan to Execute ID Based Clases in the DB
	 */
	public Map<String,Object> executeIDClass(Map<String,Object> map);
	
	/*
	 * Added by Srikalyan to Execute HQL Query  
	 */
	 public Map<String,Object> executeNormalQuery(String classPath,String query);

	

/*Added by venkatesh for chronic op migration*/

	public String saveChronicCaseDetails(PatientVO patientVO) throws Exception ;	

public EhfmOpDrugMst getopdrugDataAuto(String chemicalCode);
	
	public EhfOpConsultData getOpDocDtls(String patientId);
	 /*
	 * Added by Srikalyan to Submit
	 * Drug Details of Pharmacy Case
	 */
	public String submitPharmacyCase(String caseId,String patId,String drugDtls);
	 
	
	public LabelBean getDiagnosisDtlsAuto(String disCode) throws Exception;
	
	public List<LabelBean> getDiagList();
	 /*
	 * Added by Srikalyan to get the Biometric Details 
	 * for corresponding Registered Patient
	 */
	public PatientVO getBiomDtls(CommonDtlsVO commonDtlsVO);

	
	public List<LabelBean> getDiagListSearch(String data,String type) throws Exception;


	/*
	 * Added by Srikalyan to get the Biometric Details 
	 * for corresponding Registered Patient
	 */
	public boolean verifyPatientBiometrics(String patId ,String fingerPrint);
	public String sendMobileOtp(String patientId,String mobileNo);
	public String verifyMobileOTP(String patientId,String otp);
	public String exemptOtp(String patientId);
	public List<PatientVO> viewExemptOtpList(String userId,String patientId);
	public String exemptOtpApproval(String patientId,String userId,String remarks);
	public String checkDentalClinic(String userid,String patientId);
	public String checkGovernmentHosp(String userid,String patientId);
	public PatientVO getPatientDentalDtls(String patientId);
	public List<LabelBean> getdentalexaminationDtls(String prntId);
	public String checkDentalHospital(String hospId);
	public String checkGovernmentHosp(String userid, String patientId, String fromDisp);
	public List<LabelBean> getDentalMainComplaintLst() throws Exception;
	public String getTreatmentDntlvalue(String caseId);
	public List<LabelBean> getReferredCenterDtls(String lStrUserId,
			String roleId) throws Exception;
	public String getDispName(String hospCode);
	public List<LabelBean> getHospDtlsList(String district, String speclty);
	public DrugsVO getDrugDtlsList(String caseId);
	public List<CommonDtlsVO> getDtrsReferealDtls(String caseId, String fromDisp);
	public String getTokenNo(String hospId, String currentDate, String specialityType,String RoomNo);
	public List<LabelBean> getRoomNos(String dispId,String specialityType);
	public List<LabelBean> getCatName(String lStrCaseId);
	public List<LabelBean> getAllRoomTokenNo(String dispId);
	public int  updateRoomTokenNo(String dispId,String roleId,String userId);
	public String getRoomTokenNo(String dispId,String roleId,String userId);
	public String getDispensaryName(String dispId);
	public List<LabelVO> getLabels(HashMap<String, Object> lMap);
	public boolean saveLabReportData(HashMap<String, Object> lMap);
	public List<LabelVO> retrieveLabelsData(HashMap<String, Object> lMap);
	public boolean checkLabReportsubmitted(HashMap<String, Object> lMap);
	public boolean checkAllSubmit(HashMap<String, Object> lMap);
	public String getDrugQuant(LabelBean drugBalParam);
	public String getLabTokenNo(String caseStatus);
	public List<LabelBean> getDispDrugList(String drugType,String dispId);
	public boolean updateDrugsInventory(List<LabelBean> drugDtls);
	public List<DrugsVO> getDispInventDrugBal(LabelBean drugBalParam);
	public List<LabelBean> getDrugTypeList();
	public List<LabelBean> getDispDrugDetails(String drugType,String drugId,String userId, String transferType);
	public List<LabelBean> getDispDrugMfg(String drugId);
	public List<LabelBean> getDrugMftrList();
	public List<LabelBean> getDrugDstbtrList();
	public boolean addNewMnfNDist(LabelBean drgDetails);
	public boolean deleteDrugSet(String drugCode);
	public DrugsVO getDispDrugDtlsList(String caseId);
	public List<CommonDtlsVO> getPreviousDrugHist(String cardNo);
	public EhfmHospitals getHospTaashaFlg(String currHospId);
	public List<LabelBean> getEmpDtls(String empId);
	public List<LabelBean> getEmpDtls1(String empId);
	List<LabelBean> getEmpDtls2(String empId1);
	public LabelBean saveEmployeeFeedbackData(LabelBean labelBeanVo);
	List<LabelBean> getFedBackDtls(String enrollId);
	public List<CasesSearchVO> getDispPastHistoryDetails(String patientId, String cardNo);
	public List<LabelBean> getPatInvestList(String patientId);
	public void generateSendInvReports(String PatientId);
	public List<LabelBean> getPatientPastVisitDetails(String cardNo);
	public List<LabelBean> getdispdentalexaminationDtls(String prntId);
	public String cancelPatient(PatientVO patVo);
	public String cancelPatientReg(PatientVO patVo);
	public boolean validateSandostatinInj(String patientNo, String icdProcCode);
	public String getCriticalFlg(String caseId);
	public String saveDocDrugs(PatientVO patientVO) ;
	public String saveDocSpecialities(PatientVO patientVO) ;
	public List<LabelBean> getDoctorNames(String dispCode,String splstGroup);
	public List<DrugsVO> getPharmDrugDtls(PatientVO patientVO);
	public List<LabelBean> getDistributer(DrugsVO drg );
	public List<LabelBean> getAvailabeDrugs(DrugsVO drg );
	public String getLeaveSequence(String string);

	public String savePharmaDrugs(PatientVO patientVO) ;
	public DrugsVO  getOldDrugStatus(PatientVO drg );
	public CreateEmployeeVO getLeaveReport(CreateEmployeeVO createEmployeeVO);
	public PreauthVO getPatientFullDtlsPharma(String patientId);
	public String getPatientContactNo(String patientId);
	public List<LabelBean> getwellness(String dispId);
	public List<LabelBean> getwellness();
	public String getinvestcheck(String patientId, String investValue);
	public List<LabelBean> getRelationsNew() throws Exception;
	public String regPatient(PatientVO patVo);
	public String getPckNames(String ayushID);
	public String getPckNamesNew(String dosage);
	public String getTherapyOrAyush(String patientId);
	public String regPatientPck(PatientVO patVo);
	public String saveAttach(PatientVO patVo);
	public String getMartialName(String martialId) throws Exception;
	public String getAyushName(String ayushId) throws Exception;
	public List<LabelBean> getAisAttach(String patientId);
	public String getTherapyOrAyushNew(String patientId);

	public int getAyush(String ayushID);
	public List<LabelBean> getAisNames(String serviceType,String typeActive);
	public List<LabelBean> getAisDepNames(String cardNo);
	public List<LabelBean> getdrugs(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo, String userName);
	public List<LabelBean> getDrugsDetailed(String fromdate, String todate,
			String dispname, String drugId, String drugType, String caseId,
			int startIndex, int maxResults, int pageNo, String userName);
	public List<DrugsVO> getDispDrugInventReport(HashMap drugParam)throws Exception;
	public PatientVO getRegisteredPatientsNims(HashMap<String, String> searchMap);
	public String getTherapyOrAyushNew(String patientId, String drugId,String userRole);
	public EhfAisDrugs getPatientDetailsAis(String patientId);
	public String saveDocDrugsNew(PatientVO patientVO);
	public String savePharmaDrugsNims(PatientVO patientVO);
	public List<LabelBean> getAttachList(String patientId);
	public String rejectPatient(PatientVO patVo);
	public String getpartialFlg(String patientId);
	public String getPatientDetailsAisCnt(String contactNum);
	public List<LabelBean> getAttachListPr(String patientId);
	public String getsaveNewDrug(String string,String drugValues, String lstrUserId);
	public List<DrugsVO> getPharmDrugDtlsPick(PatientVO patientVO);
	public String savePharmaDrugsPick(PatientVO patientVO);
	public EhfAisDrugs getPatientDetailsAisDrugs(String patientId);
	public List<LabelBean> getDrugTypeListAis();
	public boolean updateTransferDrugs(HashMap drugparam)throws Exception;
	public List<LabelBean> getDispDrugListSubStore(HashMap drugParam) throws Exception;
	public List<LabelBean> getDispList(String dispId) throws Exception;
	public List<DrugsVO> getDrugsTransferredList(HashMap drugParam) throws Exception;
	public List<DrugsVO> getDrugsIntiatedDtlsList(HashMap drugParam) throws Exception;
	public boolean updateDrugsToWC(HashMap drugParam) throws Exception;
	public boolean updateDrugsInventoryNew(List<LabelBean> drugDtls);
	public List<LabelBean> getled(String pStrFromDate,
			String pStrtoDate, String center, int lStrStartIndex, int lStrRowsperpage, int pageNo, String drugName);
	public List<LabelBean> getLedgerDrugsDetailed(String fromdate, String todate,String dispname, String drugCode, String drugType, String caseId, int page, int page1, int pageNo, String userName, String drugName);
	public List<LabelBean> getexp(String pStrFromDate,
			String pStrtoDate, String center, int lStrStartIndex,
			int lStrRowsperpage, int pageNo);
	public List<LabelBean> checkUsrMappedToWellness(String lStrUserId);
	public List<LabelBean> getexpiredDrugs(Map dataMap);
	public boolean submitRequestForRTV(HashMap drugparam);
	public List<LabelBean> getDrugsInvoiceList(Map dataMap);
	public boolean submitCreditNoteRequest(HashMap drugparam);
	public List<LabelBean> returnVendorList(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo,
			String dstrName);
	public String getDistrName(String dstrName);
	public List<LabelBean> creditNoteList(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo,
			String dstrName);
	public List<LabelBean> indentList(String dispname, int startIndex,
			int maxResults, int pageNo, String drugName);
	public List<LabelBean> indentListNew(String dispname, int startIndex,
			int maxResults, int pageNo, String drugName);
	public boolean saveManfctDrugDetailsNew(HashMap paramMap);
	public boolean saveDistrDrugDetailsNew(HashMap paramMap);
	public String getDrugName(String dispDrugID);
	public List<LabelBean> getpat(String pStrFromDate, String pStrtoDate, String center, int lStrStartIndex,
			int lStrRowsperpage, int pageNo);
	public String saveSpecialistDoctors(String patientId,  List<SpecialityVO> specList);	
	public List<LabelBean> lowStockList(String drugType,String dispid,String drugName);
	public boolean submitLowStockList(HashMap drugparam);
	public List<LabelBean> lowStockPO(String drugType, String dispid, String drugName);
	public boolean moveLowStockDataFromWCItoAudit(Map indNumList);
	public List<LabelBean> getdrugType();
	public List<LabelBean> getwcName();
	public List<LabelBean> getdistributorList();
	public List<LabelBean> getOutStandingdrugs(String drugType, String drugName, String wc, String selectType, String drugDrill);
	public List<LabelBean> getdrugsDistRpt(String pStrFromDate, String pStrtoDate, String center, int lStrStartIndex, int lStrRowsperpage, int pageNo, String userName);
	public List<LabelBean> getrequiredusers(String dispname);
	public List<LabelBean> getDrugsDistDetailed(String fromdate, String todate, String dispname, String drugId, String drugType, String caseId, int page, int page1, int pageNo, String userName);
	public List<LabelBean> getIndentNoList(String dispname, String ajaxCal);
	public List<LabelBean> getIndentNoListRpt(String dispname, String ajaxCal);
	public List<LabelBean> getIndentNoListRpt1(String dispname, String ajaxCal);
	public List<LabelBean> getindentPO(String wc, String indNo, String distributorId);
	public List<LabelBean> getindentPORpt(String wc, String indNo, String distributor, String fromDate, String destDate);
	public String submitIndented(HashMap drugparam);
	public String submitStoreKeeper(HashMap drugparam);
	public List<PreauthVO> getConsentData(String wc, String pStrCaseId);
	public List<LabelBean> getDistrGen(String drugList);
	public List<LabelBean> getDistrGenAll(String dispname,List<LabelBean> result);
	public List<LabelBean> getOnclickPORpt(String poNo);
	public List<LabelBean> getOnclickAvlRpt(String dispname1,String drugCode);
	public List<LabelBean> getOnclickMnfPORpt(String mnfPoNo);
	public List<LabelBean> getstoreList(String wc, String indNo);
	public List<LabelBean> getStoreIndentNoList(String dispname, String ajaxCal);
	public List<LabelBean> getExpdtRpt(String dispname, String ajaxCal);
	public List<LabelBean> lowStockwellness(String dispname);
	public List<LabelBean> getPoStatusList(String dispname1,String poDate,String dispId);
	public List<LabelBean> getpoNotRsv(String dispname1);
	public String getpoNotRsvCacl(String drugList);
	public List<LabelBean> getOnclickPORpt(String poNo,String status);
	
	
	public List<LabelBean> getdistributorsList();
	public List<LabelBean> getdrugreportsinve(String pStrFromDate,
			String pStrtoDate, String center, String drugname,
			int lStrStartIndex, int lStrRowsperpage, int pageNo, String distributorId);
	public Map<String, Object> updateDrugsInve(HashMap<String,Object> paramMap);
	public Map<String, Object> getDrugInventoryMaster();
	//added by sowmya to get LegislativeNames
	public List<LabelBean> getLegislativeNames(String serviceType);
	public PatientVO saveLHSEnrollmentDetails(PatientVO patientVO);
	public PatientVO updateLhsMemberDetails(PatientVO patientVO);
	public PatientVO getLhsMlaConstituencies();
	public PatientVO getLhsMlcConstituencies();
	public PatientVO getLhsDistrictsList(String constituencyId);
	public PatientVO getAllDistrictList();
	public PatientVO getRegistredLhsMembersList(HashMap<String, String> searchMap);
	public String updateLhsMemberApproveOrReject(HashMap<String, String> searchMap);
	public String updateLhsMemberRevert(HashMap<String, String> searchMap);
	public PatientVO getRegistredLhsDepenentList(HashMap<String, String> searchMap);
	public String getLhsDistrict(String distId);
	public String updateLhsMemberSignedDocument(PatientVO patientVO);
	//pravalika
	public List<LabelBean> getDrugDtls(String cardNo);
	public List<EhfPatientTests> getInvestigationDetails(String patientId);
	public List<LabelBean> getPrintDrugDtls(String caseId);
	public String getMlaConstituencyId(String constituencyName);
	public String updateLhsCheckerRejection(HashMap<String, String> searchMap);
	
	public String updDrugMonthlyDtls(HashMap hParamMap)throws Exception;//Chandana - 8251 - Added new method for updating the drug details
	//sai krishna:CR#8134API Integrations.
    public String tdEhfRegistration(String patientNo,String url,String facilityId);
	public PatientVO getSavedIPLab(String patientId);
	public String getCardDtlsForAbhaSearch(String cardNo) throws Exception;//Chandana - 8443 - New method for getting the card details when the card type is ABHA 
	//Tejasri- CR-8812 Wellness Centers - Attendance dashboard to Wellness Centre Admin
	public List<LabelBean> wellnessCentreList(String dispId);
	public List<LabelBean> EmployeeList(String dsgId,String reqType,String fromDate,String toDate);
	public List<LabelBean> SearchList(String reqType,String fromDate,String dsgId,String dispname);

	//pravalika
	public String saveInventoryDetailsVendor(HashMap vendordrug,String lstrUserId);
	public List<LabelBean> getOnclickPORpt1(String poNo,String status,String lStrUserId,String drugId);
	public List<LabelBean> getWCList();
	public List<LabelBean> getStoreDrugList(String dispId);
	public List<LabelBean> getinvoiceNoList(String poId,String status);
	public List<LabelBean> getConsentData1(String wc, String pStrCaseId);
	public List<LabelBean> indentOnclick(String invoiceNo,String status,String dispId,String poNo);
	public List<LabelBean> getPoItemsForPdf(String poNo,String itemIds);
	public List<LabelBean> viewVendorDocument(String docId);
	public String uploadVendorDocument(HashMap fileUpload,String lStrUserId);
	public String updateVendorPdfPath(String pdfFileName,String filePath,String poNo,String itemId);
	public List<LabelBean> getIndentStatus(String dispId,String lStrUserId,String status);
	public List<LabelBean> getPoListforUpload(String lStrUserId,String status);
	public List<LabelBean> getUploadList(String lStrUserId,String status);
	public boolean addNewMnfNDist1(HashMap storedrugList,String lstrUserId);
	public List<LabelBean>getGenPdf (String poNo ,String status, int lStrStartIndex, int lStrRowsperpage,int pageNo);
	public List<LabelBean>getIntendPdf (String dispname1,String poDate, int lStrStartIndex, int lStrRowsperpage,int pageNo);
	public List<LabelBean> getDesignationDtls(String lStrUserId);
	public List<LabelBean> getDispIdDtls(String lStrUserId);
}
