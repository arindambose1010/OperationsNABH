package com.ahct.patient.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.model.EhfAisDrugs;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientTests;
import com.ahct.model.EhfWcIndentVndrGst;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.LabelVO;
import com.ahct.patient.vo.SpecialityVO;

public interface PatientService {
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
    public PatientVO retrieveCardDetails(PatientVO patientVO);
    /**
     * ;
     * @param PatientVO patientVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	public String sendMobileOtp(String patientId,String mobileNo);
	public String verifyMobileOTP(String patientId,String otp);
    public PatientVO retrieveAisCardDetails(PatientVO patientVO);
    public String exemptOtp(String patientId);
    public List<PatientVO> viewExemptOtpList(String userId,String patientId);
    public String exemptOtpApproval(String patientId,String userId,String remarks);
	public int registerPatient(PatientVO patientVO);
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
	public List<LabelBean> getHospital(String userId,String roleId);
	/**
     * ;
     * @param HashMap<String,String> searchMap
     * @return PatientVO registeredPatientsList
     * @function This Method is Used For retrieving registered patients list for given search data
     */
	public PatientVO getRegisteredPatients(HashMap<String, String> searchMap);
	/**
     * ;
     * @param String patientId
     * @return AsritPatient asritPatient
     * @function This Method is Used For retrieving PatientDetails for given PatientId
     */
	public EhfPatient getPatientDetails(String patientId);
	/**
     * ;
     * @return List<String> complaintList
     * @function This Method is Used For getting Complaints List
     */
	public List<String> getComplaints(String mainCompId);
	/**
     * ;
	 * @param fromDisp 
	 * @param grpId 
     * @param String doctorType
     * @param String hospId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For retrieving Doctors List associated for the specified hospId
     */
	public List<LabelBean> getDoctorsList(String doctorType,String hospId,String scheme, String fromDisp, String grpId);
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return String caseId
	 * @function This Method is Used For Registering Patient Case as IP/OP/Chronic OP
	 */
	public String saveCaseDetails(PatientVO patientVO);
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	public String getHospName(String hospId);
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	public String getLocationName(String locId);
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @param String doctorId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For getting Selected Doctor Details
     */
	public List<LabelBean> getSelDocDetails(String doctorType,String hospId,String docId,String schemeId); 
	 /**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
    public List<LabelBean> getRelations();
    /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
    public String getRelationName(String relationId);
    /**
     * ;
     * @param String occupationId
     * @return String occupationName
     * @function This Method is Used For getting occupationName for given occupationId from EhfmOccupationMst table
     */
    public String getOccupationName(String occupationId);
    /**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
    public String getPatientPhoto(String cardNo);
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
	public int captureTelephonicPatientDtls(PatientVO patientVO);
	/**
     * ;
     * @param PatientVO patientVO
     * @return PatientVO patientVO
     * @function This Method is Used For Retrieving telephonic registered details for specified telephonicId
     */
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo);
	/**
     * ;
     * @param String hospId
     * @return List<LabelBean> ICDCategorylist 
     * @function This Method is Used For getting ICD Category List for specific hospital
     */
	public List<LabelBean> getTherapyCategory(String hospId);
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> ICDSubCategorylist 
     * @function This Method is Used For getting ICD Sub Category List for specific category
     */
	public List<LabelBean> getTherapySubCategory(String categoryId);
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> procList 
     * @function This Method is Used For getting ICD Procedure List for specific category
     */
	public List<LabelBean> getTherapyProcedure(String subCategoryId);
	/**
     * ;
     * @return List<LabelBean> diagnosisTypesList
     * @function This Method is Used For getting Diagnosis Types List(EHFM_DIAGNOSIS_MST--EhfmDiagnosisMst)
     */
	public List<LabelBean> getDiagnosisTypes();
	/**
     * ;
     * @param String diagnosisId
     * @return List<LabelBean> diagnosisMainCategoryList
     * @function This Method is Used For getting Diagnosis Main Category List(EHFM_DIA_MAINCAT_MST--EhfmDiagMainCatMst)
     */
	public List<LabelBean> getDiagnMainCategory(String diagnosisId);
	/**
     * ;
     * @param String mainCategoryId
     * @return List<LabelBean> diagnosisCategoryList
     * @function This Method is Used For getting Diagnosis Category List(EHFM_DIA_CATEGORY_MST--EhfmDiagCategoryMst)
     */
	public List<LabelBean> getDiagnCategory(String mainCategoryId);
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> diagnosisSubCategoryList
     * @function This Method is Used For getting Diagnosis Sub Category List(EHFM_DIA_SUBCAT_MST--EhfmDiagSubCatMst)
     */
	public List<LabelBean> getDiagnSubCategory(String categoryId);
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDiseaseList
     * @function This Method is Used For getting Diagnosis Disease List(EHFM_DIA_DISEASE_MST--EhfmDiagDiseaseMst)
     */
	public List<LabelBean> getDiagnDisease(String code,String param);
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDisAnatomicalList
     * @function This Method is Used For getting Diagnosis Disease Anatomical List(EHFM_DIA_DISANATOMICAL_MST--EhfmDiagDisAnatomicalMst)
     */
	public List<LabelBean> getDiagnDisAnatomical(String code,String param);
	/**
     * ;
     * @return List<LabelBean> mainComplaintList
     * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
     */
	public List<LabelBean> getMainComplaintLst();
	/**
     * ;
     * @param String mainSymptomCode
     * @param String subSymptomCode
     * @return List<String> symptomList
     * @function This Method is Used For retrieving Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	public List<String> getSymptomLists(String mainSymptomCode, String subSymptomCode);
	/**
     * ;
     * @param String icdCatCode
     * @param String asriCatCode
     * @param String hospId
     * @return List<String> procedureList
     * @function This Method is Used For retrieving Icd procedure List(EHFM_MAIN_THERAPY--EhfmTherapyProcMst)
     */
	public List<String> getIcdProcList(String icdCatCode,String asriCatCode, String hospId,String state,String hosType, String treatType);
	/**
     * ;
     * @param String drugTypeCode
     * @return List<String> drugSubTypeList
     * @function This Method is Used For getting Drug Sub Type List From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public List<String> getDrugSubList(String drugTypeCode);
	
	public List<String> getOpDrugSubList(String drugTypeCode,String pStrIpOpType);
	public List<String> getOpPharSubList(String drugTypeCode,String pStrIpOpType);
	public List<String> getOpChemSubList(String pharSubCode,String pStrIpOpType);
	public List<String> getChemSubList(String cSubGrpCode,String pStrIpOpType);
	
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public String getDrugDetails(String drugCode);
	/**
     * ;
     * @param String icdProcCode
     * @return String procCode
     * @function This Method is Used For getting Procedure Codes From EhfmTherapyProcMst(EHFM_THERAPY_PROC_MST )
     */
	public String getTherapyProcCodes(String icdProcCode);
	/**
     * ;
     * @return List<LabelBean> opCategoryList
     * @function This Method is Used For getting Therapy OP Category Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<LabelBean> getTherapyOPCategory();
	/**
     * ;
     * @param String opMainCode
     * @return List<LabelBean> opPkgList
     * @function This Method is Used For getting Therapy OP Package Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<String> getOpPkgList(String opCatCode);
	/**
     * ;
     * @param String opPkgCode
     * @return List<LabelBean> opIcdList
     * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	public List<String> getOpIcdList(String opPkgCode);
	/**
     * ;
     * @param String cardNo
     * @param String opCatCode
     * @return int count
     * @function This Method is Used For getting count of cases with given cardNo and therapyOPCategory whose medication period is not completed
     */
	public int validateTherapyOPCat(String cardNo,String opCatCode);
	
	
	public List<DrugsVO> getChronicDetails(String cardNo);
	public List<String> getSubSymptomLists(String mainSymptomCode);
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId, String fromDisp);
	public List<SpecialityVO> getPatientSpecialities(String fromDisp, String patientId);
	public String deleteInvestigations(String caseId, String procCode,String investId,String asriCode);
	public String deleteGenInvest(String patientId, String investId);
	public List<String> getInvestigations(String lStrBlockId);
	public List<String> getRouteList(String routeTypeCode,String pStrIpOpType);
	public List<String> getStrengthList(String strengthTypeCode,String pStrIpOpType);
	public String getSequence(String pStrSeqName) throws Exception;
	public List<CommonDtlsVO> getDtrsFormDtls(String caseId, String fromDisp) throws Exception;
	public PatientVO getOPCases(HashMap<String, String> searchMap) throws Exception;
	public String getDutyDoctorById(String regNo);
	public String getProcedureType(String procCode,String asriCode);
	
	/**
	 * ;
	 * @param String caseId
	 * @return List<CommonDtlsVO> caseDetailsList
	 	 * @function This Method is Used For common details for the caseId specified
	 */
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId);
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<LabelBean> docAavilList
	 * @function This Method is Used For getting available doctor list for given hospital and specialty
	 */
	public List<LabelBean> getDocAvailLst(PatientVO patientVO);
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<String> specInvestList
	 * @function This Method is Used For getting special investigation list for given therapy
	 */
	public List<String> getSpecialInvestigationLst(PatientVO patientVO);
	
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
     * @function This Method is Used For getting Dental Units for given Procedure
     */
	public int getDenUnitsList(String icdProcCode);
	/*
	 * Added by Srikalyan to Verify New Born
	 * Baby Condition for Mithra based on 
	 * the scheme
	 */
	public String checkNewBornCond(String lStrUserId);
	
	public String getInvestPrice(String blockId,String investId);
	
	
	
	public String getDaysActiveOP(String caseId);
	
	public List<LabelBean> getConsultDtls(String patientId);
	
	public List<CasesSearchVO> getOpPastHistoryDetails(CasesSearchVO vo);
	
	
	public EhfmOpDrugMst getopdrugDataAuto(String chemicalCode);
	
	public String getEmpCode(String PatientId);

	/*
	 * Added by Srikalyan to Verify and get
	 * Dental Conditions for TG Patients 
	 */
	public PreauthVO validateAndGetDentalCond(PreauthVO preauthVO);
	/*
	 * Added by Srikalyan to get the Procedure Name 
	 * for corresponding Procedure ID
	 */
	public PreauthVO getProcName(String procId,String patSchemeId);

	/*
	 * Added by Srikalyan to get the Submit
	 * Drug Details of Pharmacy Case
	 */
	public String submitPharmacyCase(String caseId,String patId,String drugDtls);
	
	public EhfOpConsultData getOpDocDtls(String patientId);
	
	
/*Added by venkatesh for chronic op migration*/
	public String saveChronicCaseDetails(PatientVO patientVO) throws Exception ;
	
	/*
	 * Added by Srikalyan to get the Biometric Details 
	 * for corresponding Registered Patient
	 */
	public PatientVO getBiomDtls(CommonDtlsVO commonDtlsVO);
	
	public List<LabelBean> getDiagListSearch(String data,String type) throws Exception;

public LabelBean getDiagnosisDtlsAuto(String disCode) throws Exception;
	
	public List<LabelBean> getDiagList();

	/*
	 * Added by Srikalyan to get the Biometric Details 
	 * for corresponding Registered Patient
	 */
	public boolean verifyPatientBiometrics(String patId ,String fingerPrint);
	
	public String checkDentalClinic(String userid , String patientId);
	public String checkGovernmentHosp(String userid , String fromDisp);
	public String checkGovernmentHosp(String userid, String patientId, String fromDisp);
	public PatientVO getPatientDentalDtls(String patientId);
	public List<LabelBean> getdentalexaminationDtls(String mainCompId);
	/***
	 * @author Kalyan
	 * @return type
	 * @function This Method is Used to check Special conditions for TG. 
	 */
	public String checkSpecialDenatlCond(PreauthVO preauthVO);
	public List<LabelBean> getDentalMainComplaintLst();
	public String checkDentalHospital(String hospCode);
	
	/***
	 * @author Kalyan
	 * @param Patient ID
	 * @return Follow Up Flag a String
	 * @function This Method is Used to check Dental Follow UP Eligibilty for a Specific Patient. 
	 */
	public List<String> checkDenFlp(String patientID,List<String> therapyList);
	public String getTreatmentDntlvalue(String caseId);
	public List<LabelBean> getReferredCenterDtls(String lStrUserId,
			String roleId);
	public String getDispName(String hospCode);
	public List<LabelBean> getHospDtlsList(String district, String speciality);
	public DrugsVO getDrugDtlsList(String caseId);
	public List<CommonDtlsVO> getDtrsReferealDtls(String caseId, String fromDisp);
	public List<LabelBean> getCatName(String lStrCaseId);
	public String getTokenNo(String hospId, String onlyDate,String specialityType,String roomNo);
	public List<LabelBean> getRoomNos(String dispId,String specialityType);
	public List<LabelBean> getAllRoomTokenNo(String dispId);
	public int  updateRoomTokenNo(String dispId,String roleId,String userId);
	public String getRoomTokenNo(String dispId,String roleId,String userId);
	public String getDispensaryName(String dispId);
	public List<LabelVO> getLabels(HashMap<String,Object> lMap);
	public boolean saveLabReportData(HashMap<String, Object> lMap);
	public List<LabelVO> retrieveLabelsData(HashMap<String, Object> lMap);
	public boolean checkLabReportsubmitted(HashMap<String, Object> lMap);
	public boolean checkAllSubmit(HashMap<String, Object> lMap);
	public List<LabelBean> getEmpDtls(String empId);
	public List<LabelBean> getEmpDtls1(String empId);
	public List<LabelBean> getEmpDtls2(String empId);
	public LabelBean saveEmployeeFeedbackData(LabelBean labelBeanVo);
	public String getDrugQuant(LabelBean drugBalParam);
	public List<LabelBean> getDispDrugList(String drugType,String dispId);
	public List<LabelBean> getDrugTypeList();
	public boolean updateDrugsInventory(List<LabelBean> drugDtls);
	public List<DrugsVO> getDispInventDrugBal(LabelBean drugBalParam);
	public List<LabelBean> getDispDrugDetails(String drugType,String drugId,String userId, String transferType);
	public List<LabelBean> getDispDrugMfg(String drugId);
	public List<LabelBean> getDrugMftrList();
	public List<LabelBean> getDrugDstbtrList();
	public boolean addNewMnfNDist(LabelBean drgDetails);
	public boolean deleteDrugSet(String drugCode);
	public DrugsVO getDispDrugDtlsList(String caseId);
	public List<CommonDtlsVO> getPreviousDrugHist(String cardNo);
	public EhfmHospitals getHospTaashaFlg(String currHospId);
	public List<LabelBean> getFedBackDtls(String enrollId);
	public List<CasesSearchVO> getDispPastHistoryDetails(String patientId, String cardNo);
	public List<LabelBean> getPatInvestList(String patientId);
	public void generateSendInvReports(String patientId);
	public List<LabelBean> getPatientPastVisitDetails(String cardNo);
	public List<LabelBean> getdispdentalexaminationDtls(String string);
	public String cancelPatient(PatientVO patVo);
	public String cancelPatientReg(PatientVO patVo);
	public boolean validateSandostatinInj(String patientNo, String icdProcCode);
	public String getCriticalFlg(String caseId);
	public String saveDocDrugs(PatientVO patientVO);
	public String saveDocSpecialities(PatientVO patientVO);
	public List<LabelBean> getDoctorNames(String dispCode, String splstGroup);  
	public List<DrugsVO> getPharmDrugDtls(PatientVO patientVO);
	public String getLeaveSequence(String string);

	public List<LabelBean> getDistributer(DrugsVO drg );
	public List<LabelBean> getAvailabeDrugs(DrugsVO drg );
	public String savePharmaDrugs(PatientVO patientVO) ;
	public DrugsVO  getOldDrugStatus(PatientVO drg );
	public CreateEmployeeVO getLeaveReport(CreateEmployeeVO createEmployeeVO);
	public PreauthVO getPatientFullDtlsPharma(String patientId);
	
	public String getPatientContactNo(String patientId);
		public List<LabelBean> getwellness(String dispId);
	public List<LabelBean> getwellness();
	public String getinvestcheck(String patientId, String investValue);
	public List<LabelBean> getRelationsNew();
	public String regPatient(PatientVO patVo);
	public String getPckNames(String ayushID);
	public String getPckNamesNew(String dosage);
	public String getTherapyOrAyush(String patientId);
	public String regPatientPck(PatientVO patVo);
	public String saveAttach(PatientVO patVo);
	public String getMartialName(String martialId);
	public List<LabelBean> getAisAttach(String patientId);
	public String getTherapyOrAyushNew(String patientId);
	public int getAyush(String ayushID);
	public String getAyushName(String ayushId);
	public List<LabelBean> getAisNames(String serviceType,String typeActive);
	public List<LabelBean> getAisDepNames(String cardNo);
	public List<LabelBean> getdrugs(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo, String userName);
	public List<LabelBean> getDrugsDetailed(String fromdate, String todate,
			String dispname, String drugId, String drugType, String caseId,
			int startIndex, int maxResults, int pageNo, String userName);
	public List<DrugsVO> getDispDrugInventReport(HashMap drugParam);
	public PatientVO getRegisteredPatientsNims(HashMap<String, String> searchMap);
	public String saveMedicalCaseDetails(String patientId, String drugId,String userRole);
	public EhfAisDrugs getPatientDetailsAis(String patientId);
	public String saveDocDrugsNew(PatientVO patientVO);
	public String savePharmaDrugsNims(PatientVO patientVO);
	public List<LabelBean> getAttachList(String patientId);
	public String rejectPatient(PatientVO patVo);
	public String getpartialFlg(String patientId);
	public String getPatientDetailsAisCnt(String contactNum);
	public List<LabelBean> getAttachListPr(String patientId);
	public String getsaveNewDrug(String string, String drugValues, String lStrUserId);
	public List<DrugsVO> getPharmDrugDtlsPick(PatientVO patientVO);
	public String savePharmaDrugsPick(PatientVO patientVO);
	public EhfAisDrugs getPatientDetailsAisDrugs(String patientId);
	public List<LabelBean> getDrugTypeListAis();
	public boolean updateTransferDrugs(HashMap drugparam);
	public List<LabelBean> getDispDrugListSubStore(HashMap drugParam);
	public List<LabelBean> getDispList(String dispId);
	public List<DrugsVO> getDrugsTransferredList(HashMap drugParam);
	public List<DrugsVO> getDrugsIntiatedDtlsList(HashMap drugParam);
	public boolean updateDrugsToWC(HashMap drugParam);
	public boolean updateDrugsInventoryNew(List<LabelBean> drugDtls);
	public List<LabelBean> getled(String fromdate, String todate, String dispname, int lStrStartIndex, int lStrRowsperpage, int pageNo, String drugName);
	public List<LabelBean> getLedgerDrugsDetailed(String fromdate, String todate, String dispname, String drugCode, String drugType, String caseId, int page, int page2, int pageNo, String userName, String drugName);
	public List<LabelBean> getexp(String fromdate, String todate,
			String dispname, int lStrStartIndex, int lStrRowsperpage,
			int pageNo);
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
			String dispname, int page, int page2, int page3, String dstrName);
	public List<LabelBean> indentList(String dispname, int startIndex,
			int maxResults, int pageNo, String drugName);
	public List<LabelBean> indentListNew(String dispname, int startIndex,
			int maxResults, int pageNo, String drugName);
	public boolean saveManfctDrugDetailsNew(HashMap paramMap);
	public boolean saveDistrDrugDetailsNew(HashMap paramMap);
	public String getDrugName(String dispDrugID);
	public List<LabelBean> getpat(String fromdate, String todate, String dispname, int startIndex, int maxResults,
			int pageNo);
	public String saveSpecialistDoctors(String patientId, List<SpecialityVO> specList);		
	public List<LabelBean> lowStockList(String drugType,String dispname, String drugName);
	public boolean submitLowStockList(HashMap drugparam);
	List<LabelBean> lowStockPO(String drugType, String dispid, String drugName);
	public boolean moveLowStockDataFromWCItoAudit(Map indNumList);
	public List<LabelBean> getwcName();
	public List<LabelBean> getdistributorList();
	public List<LabelBean> getdrugType();
	public List<LabelBean> getOutStandingdrugs(String drugType, String drugName, String wc, String selectType, String drugDrill);
	public List<LabelBean> getdrugsDistRpt(String pStrFromDate , String pStrtoDate,String center,int lStrStartIndex ,int lStrRowsperpage,int pageNo,String userName);
	public List<LabelBean> getrequiredusers(String dispname);
	public List<LabelBean> getDrugsDistDetailed(String fromdate, String todate,String dispname, String drugId,String drugType,String caseId,int page, int page1,int pageNo,String userName);
	public List<LabelBean> getIndentNoList(String dispname, String ajaxCal);
	public List<LabelBean> getIndentNoListRpt(String dispname, String ajaxCal);
	public List<LabelBean> getIndentNoListRpt1(String dispname, String ajaxCal);
	public List<LabelBean> getindentPO(String wc, String indNo, String distributorId);
	public List<LabelBean> getindentPORpt(String wc, String indNo, String distributorId,String fromDate, String destDate);
	public List<LabelBean> getOnclickPORpt(String poNo);
	public List<LabelBean> getOnclickAvlRpt(String dispname1,String drugCode);
	public List<LabelBean> getOnclickMnfPORpt(String mnfPoNo);
	public String submitIndented(HashMap drugparam);
	public String submitStoreKeeper(HashMap drugparam);
	public List<PreauthVO> getConsentData(String wc, String pStrCaseId) throws Exception;
	public List<LabelBean> getDistrGen(String drugList);
	public List<LabelBean> getDistrGenAll(String dispname,List<LabelBean> result);
	public List<LabelBean> getstoreList(String wc, String indNo);
	public List<LabelBean> getStoreIndentNoList(String dispname, String ajaxCal);
	public List<LabelBean> getExpdtRpt(String dispname, String ajaxCal);
	public List<LabelBean> lowStockwellness(String dispname);
	public List<LabelBean> getPoStatusList(String dispname1,String poDate,String dispId);
	public List<LabelBean> getpoNotRsv(String dispname1);
	public String getpoNotRsvCacl(String drugList);
	public List<LabelBean> getOnclickPORpt(String poNo,String status);
	
	public List<LabelBean> getdistributorsList();
	public List<LabelBean> getdrugsInve(String pStrFromDate , String pStrtoDate, String dispname, String drugname, int lStrStartIndex, int lStrRowsperpage, int pageNo, String distributorId);
	public Map<String, Object> updateDrugsInve(HashMap<String, Object> paramMap);
	public Map<String, Object> getDrugInventoryMaster();
	//sowmya added for get legislative names
	public List<LabelBean> getLegislativeNames(String serviceType);
	public PatientVO saveLHSEnrollmentDetails(PatientVO patientVO);
	public PatientVO updateLhsMemberDetails(PatientVO patientVO);
	public PatientVO getLhsMlaConstituencies();
	public PatientVO getLhsMlcConstituencies();
	public PatientVO getLhsDistrictsList(String constituencyId);
	public PatientVO getAllDistrictList();
	public PatientVO getRegistredLhsMembersList(HashMap<String, String> searchMap);
	public PatientVO getRegistredLhsDepenentList(HashMap<String, String> searchMap);
	public String convertPDFToBase64(String docPath) throws Exception;
	public String convertImageToBase64(String docPath) throws Exception;
	public String updateLhsMemberApproveOrReject(HashMap<String, String> searchMap);
	public String getLhsDistrict(String distId);
	public String updateLhsMemberSignedDocument(PatientVO patientVO);
	//pravalika
	public List<LabelBean> getDrugDtls(String cardNo) throws Exception;
    public List<EhfPatientTests> getInvestigationDetails(String patientId );
	public List<LabelBean> getPrintDrugDtls(String caseId );
	public String getMlaConstituencyId(String constituencyName);
	public String updateLhsMemberRevert(HashMap<String, String> searchMap);
	
	public String updDrugMonthlyDtls(HashMap<String, Object> hParamMap) throws Exception;//Chandana - 8251 - New method for updating the details
	public String updateLhsCheckerRejection(HashMap<String, String> searchMap);
	//sai krishna:CR#8134API Integrations.
	public String tdEhfRegistration(String patientNo, String url,String facilityId);
	public PatientVO getSavedIPLab(String patientId);
	public String getCardDtlsForAbhaSearch(String cardNo) throws Exception;//Chandana - 8443 - New method for getting the card details when the card type is ABHA
	//Tejasri- CR-8812 Wellness Centers - Attendance dashboard to Wellness Centre Admin
	public List<LabelBean> wellnessCentreList(String dispId);
	//Tejasri- CR-8812 Wellness Centers - Attendance dashboard to Wellness Centre Admin
	public List<LabelBean> EmployeeList(String dsgId,String reqType,String fromDate,String toDate);
	public List<LabelBean> SearchList(String reqType,String fromDate,String dsgId,String dispname);

		//pravalika
	public String saveInventoryDetailsVendor(HashMap vendordrug,String lstrUserId);
	public List<LabelBean> getWCList();
	public List<LabelBean> getStoreDrugList(String dispId);
	public List<LabelBean> getinvoiceNoList(String poId,String status);
	public List<LabelBean> getConsentData1(String wc, String pStrCaseId) throws Exception;
	public List<LabelBean> getPoItemsForPdf(String poNo,String itemIds);
	public String updateVendorPdfPath(String pdfFileName,String filePath,String poNo,String itemId);
	public List<LabelBean> getIndentStatus(String dispId,String lStrUserId,String status);
	public List<LabelBean> getPoListforUpload(String lStrUserId,String status);
	public List<LabelBean> indentOnclick(String invoiceNo,String status,String dispId,String poNo);
	public List<LabelBean> viewVendorDocument(String docId);
	public String uploadVendorDocument(HashMap fileUpload,String lStrUserId);
	public List<LabelBean> getUploadList(String lStrUserId,String status);
	public List<LabelBean> getOnclickPORpt1(String poNo,String status,String lStrUserId,String drugId);
	public boolean addNewMnfNDist1(HashMap storedrugList,String lstrUserId);
	public List<LabelBean>getGenPdf (String poNo ,String status, int lStrStartIndex, int lStrRowsperpage,int pageNo);
	public List<LabelBean> getIntendPdf(String dispname1,String poDate,int lStrStartIndex, int lStrRowsperpage,int pageNo);
	public List<LabelBean> getDesignationDtls(String lStrUserId);
	public List<LabelBean> getDispIdDtls(String lStrUserId);
}


