package com.ahct.patient.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.model.EhfAisDrugs;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfMedicalHistory;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientDocDrugs;
import com.ahct.model.EhfPatientTests;
import com.ahct.model.EhfWcIndentVndrGst;
import com.ahct.model.EhfmDispensaryDtls;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.patient.dao.PatientDao;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.LabelVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.SpecialityVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;



public class PatientServiceImpl implements PatientService {
	private final static Logger GLOGGER = Logger.getLogger ( PatientServiceImpl.class ) ;
	PatientDao patientDao;
    GenericDAO genericDao;
	public PatientDao getPatientDao()
	{
		return patientDao;
	}
	public void setPatientDao(PatientDao patientDao) {
		this.patientDao = patientDao;
	}
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */
	@Override
	public PatientVO retrieveCardDetails(PatientVO patientVo) {
		PatientVO patientVO=null;
		try
		{
			patientVO=patientDao.retrieveCardDetails(patientVo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return patientVO;
	}
	/**
     * ;
     * @param PatientVO patientVO
     * @return int noOfRecords
     * @function This Method is Used For Registering Direct Patient
     */
	@Override
	public PatientVO retrieveAisCardDetails(PatientVO patientVo) {
		PatientVO patientVO=null;
		try
		{
			patientVO=patientDao.retrieveAisCardDetails(patientVo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return patientVO;
	}
	@Override
	public int registerPatient(PatientVO patientVO) {
		int i=0;
		try {
			i = patientDao.registerPatient(patientVO);
		}
		catch (Exception e) {
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in registerPatient() in PatientServiceImpl class."+e.getMessage());
		}
		return i;
		
	}
	/**
     * ;
     * @param String userId
     * @param String roleId
     * @return List<LabelBean> hospitalList
     * @function This Method is Used For getting Hospital List for given user and role
     */
	@Override
	public List<LabelBean> getHospital(String userId,String roleId) {
		List<LabelBean> hospitalList=null;
		try {
			hospitalList = patientDao.getHospital(userId,roleId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospital() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}
	/**
     * ;
     * @param HashMap<String,String> searchMap
     * @return PatientVO registeredPatientsList
     * @function This Method is Used For retrieving registered patients list for given search data
     */
	@Override
	public PatientVO getRegisteredPatients(HashMap<String, String> searchMap) {
		PatientVO registeredPatientsList=null;
		try {
			registeredPatientsList=patientDao.getRegisteredPatients(searchMap);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getRegisteredPatients() in PatientServiceImpl class."+e.getMessage());
		}
		return registeredPatientsList;
	}
	/**
     * ;
     * @param String patientId
     * @return AsritPatient asritPatient
     * @function This Method is Used For retrieving PatientDetails for given PatientId
     */
	@Override
	public EhfPatient getPatientDetails(String patientId) {
		EhfPatient asritPatient=null;
		try 
		{
			asritPatient=(EhfPatient)patientDao.getPatientDetails(patientId);
		} 
		catch (Exception e) 
		{
			GLOGGER.error("Exception Occurred in getPatientDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return asritPatient;
	}
	/**
     * ;
     * @return List<String> complaintList
     * @function This Method is Used For getting Complaints List
     */
	@Override
	public List<String> getComplaints(String mainCompId) {
		List<String> symptomsList=null;
		try
		{
			symptomsList=patientDao.getComplaints(mainCompId);
		}
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getComplaints() in PatientServiceImpl class."+e.getMessage());
		}
		return symptomsList;
	}
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For retrieving Doctors List associated for the specified hospId
     */
	@Override
		public List<LabelBean> getDoctorsList(String doctorType, String hospId,String schemeId,String fromDisp, String grpId) {
		List<LabelBean> doctorsList=null;
		try 
		{
			doctorsList=patientDao.getDoctorsList(doctorType, hospId,schemeId,fromDisp, grpId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getDoctorsList() in PatientServiceImpl class."+e.getMessage());
		}
		return doctorsList;
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return String caseId
	 * @function This Method is Used For Registering Patient Case as IP/OP/Chronic OP
	 */
	@Override
	public String saveCaseDetails(PatientVO patientVO)
	{
		String lStrCaseId=null;
		try 
		{
			lStrCaseId = patientDao.saveCaseDetails(patientVO);
		}
		catch (Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in saveCaseDetails() in PatientServiceImpl class."+e.getMessage());
		}      
		return lStrCaseId;
		
	}
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	@Override
	public String getHospName(String hospId) {
		String hospitalName=null;
		try
		{
			hospitalName=patientDao.getHospName(hospId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospName() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	@Override
	public String getLocationName(String locId) {
		String locationName=null;
		try
		{
			locationName=patientDao.getLocationName(locId);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getLocationName() in PatientServiceImpl class."+e.getMessage());
		}
		return locationName;
	}
	/**
     * ;
     * @param String doctorType
     * @param String hospId
     * @param String doctorId
     * @return List<LabelBean> doctorsList
     * @function This Method is Used For getting Selected Doctor Details
     */
	@Override
	public List<LabelBean> getSelDocDetails(String doctorType, String hospId,String docId,String schemeId)
	{
		List<LabelBean> docDetails=null;
		try
		{
			docDetails=patientDao.getSelDocDetails(doctorType, hospId, docId,schemeId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getSelDocDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return docDetails;
	}
	 /**
     * ;
     * @return List<LabelBean> relationList
     * @function This Method is Used For getting Relations List from RelationMst table
     */
	@Override
	public List<LabelBean> getRelations() {
		List<LabelBean> relationsList=null;
		try
		{
			relationsList=patientDao.getRelations();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelations() in PatientServiceImpl class."+e.getMessage());
		}
		return relationsList;
	}
	 /**
     * ;
     * @param String relationId
     * @return String relationName
     * @function This Method is Used For getting relationName for given relationId from RelationMst table
     */
	@Override
	public String getRelationName(String relationId) {
		String relationName=null;
		try
		{
			relationName=patientDao.getRelationName(relationId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelationName() in PatientServiceImpl class."+e.getMessage());
		}
		return relationName;
	}
	@Override
	public String getMartialName(String martialId) {
		String MartialName=null;
		try
		{
			MartialName=patientDao.getMartialName(martialId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelationName() in PatientServiceImpl class."+e.getMessage());
		}
		return MartialName;
	}
	@Override
	public String getAyushName(String ayushId) {
		String AyushName=null;
		try
		{
			AyushName=patientDao.getAyushName(ayushId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelationName() in PatientServiceImpl class."+e.getMessage());
		}
		return AyushName;
	}
	/**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo
     */
	@Override
	public String getPatientPhoto(String cardNo) {
		String photoUrl=null;
		try
		{
			photoUrl=patientDao.getPatientPhoto(cardNo);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getPatientPhoto() in PatientServiceImpl class."+e.getMessage());
		}
		return photoUrl;
	}
	
	/**
     * ;
     * @param String cardNo
     * @return String photoUrl
     * @function This Method is Used For getting photoUrl for given cardNo of journalist
     */
    public String getJournalistPhoto(String cardNo)
	    {
	    	String photoUrl=null;
			try
				{
					photoUrl=patientDao.getJournalistPhoto(cardNo);
				}
			catch(Exception e)
				{
					GLOGGER.error("Exception Occurred in getJournalistPhoto() in PatientServiceImpl class."+e.getMessage());
				}
		return photoUrl;
	    }
	/**
     * ;
     * @param PatientVO patientVO
     * @return int records inserted
     * @function This Method is Used For Saving telephonic information
     */
	@Override
	public int captureTelephonicPatientDtls(PatientVO patientVO) {
		int i=0;
		try {
			i = patientDao.captureTelephonicPatientDtls(patientVO);
		} catch (Exception e) {
			GLOGGER.error("Exception Occurred in captureTelephonicPatientDtls() in PatientServiceImpl class."+e.getMessage());
		}
		return i;
	}
	/**
     * ;
     * @param PatientVO patientVO
     * @return PatientVO patientVO
     * @function This Method is Used For Retrieving telephonic registered details for specified telephonicId
     */
	@Override
	public PatientVO getTelephonicIntimationDtls(PatientVO patientVo) {
		PatientVO patientVO = new PatientVO();
		try
		{
		patientVO =patientDao.getTelephonicIntimationDtls(patientVo);
		}
		catch(Exception e)
		{			
			GLOGGER.error("Exception Occurred in getTelephonicIntimationDtls() in PatientServiceImpl class."+e.getMessage());
		}		
		return patientVO;
	}
	/**
     * ;
     * @param String hospId
     * @return List<LabelBean> ICDCategorylist 
     * @function This Method is Used For getting ICD Category List for specific hospital
     */
	@Override
	public List<LabelBean> getTherapyCategory(String lStrhospId) {
		List<LabelBean> catList=null;
		try
		{
			
			catList=patientDao.getTherapyCategory(lStrhospId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return catList;
	}
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> ICDSubCategorylist 
     * @function This Method is Used For getting ICD Sub Category List for specific category
     */
	@Override
	public List<LabelBean> getTherapySubCategory(String categoryId) {
		List<LabelBean> subCatList=null;
		try
		{
			
			subCatList=patientDao.getTherapySubCategory(categoryId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapySubCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return subCatList;
	}
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> procList 
     * @function This Method is Used For getting ICD Procedure List for specific category
     */
	@Override
	public List<LabelBean> getTherapyProcedure(String subCategoryId) {
		List<LabelBean> procList=null;
		try
		{
			
			procList=patientDao.getTherapyProcedure(subCategoryId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyProcedure() in PatientServiceImpl class."+e.getMessage());
		}
		return procList;
	}
	/**
     * ;
     * @param String occupationId
     * @return String occupationName
     * @function This Method is Used For getting occupationName for given occupationId from EhfmOccupationMst table
     */
	@Override
	public String getOccupationName(String occupationId) {
		String relationName=null;
		try
		{
			relationName=patientDao.getOccupationName(occupationId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOccupationName() in PatientServiceImpl class."+e.getMessage());
		}
		return relationName;
	}
	/**
     * ;
     * @return List<LabelBean> diagnosisTypesList
     * @function This Method is Used For getting Diagnosis Types List(EHFM_DIAGNOSIS_MST--EhfmDiagnosisMst)
     */
	@Override
	public List<LabelBean> getDiagnosisTypes() {
		List<LabelBean> diagnosisList=null;
		try
		{
			diagnosisList=patientDao.getDiagnosisTypes();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnosisTypes() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisList;
	}
	/**
     * ;
     * @param String diagnosisId
     * @return List<LabelBean> diagnosisMainCategoryList
     * @function This Method is Used For getting Diagnosis Main Category List(EHFM_DIA_MAINCAT_MST--EhfmDiagMainCatMst)
     */
	@Override
	public List<LabelBean> getDiagnMainCategory(String diagnosisId) {
		 
		List<LabelBean> diagnosisMainCatList=null;
		try
		{
			diagnosisMainCatList=patientDao.getDiagnMainCategory(diagnosisId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnMainCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisMainCatList;
	}
	/**
     * ;
     * @param String mainCategoryId
     * @return List<LabelBean> diagnosisCategoryList
     * @function This Method is Used For getting Diagnosis Category List(EHFM_DIA_CATEGORY_MST--EhfmDiagCategoryMst)
     */
	@Override
	public List<LabelBean> getDiagnCategory(String mainCategoryId) {
		
		List<LabelBean> diagnosisCatList=null;
		try
		{
			diagnosisCatList=patientDao.getDiagnCategory(mainCategoryId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisCatList;
	}
	/**
     * ;
     * @param String categoryId
     * @return List<LabelBean> diagnosisSubCategoryList
     * @function This Method is Used For getting Diagnosis Sub Category List(EHFM_DIA_SUBCAT_MST--EhfmDiagSubCatMst)
     */
	@Override
	public List<LabelBean> getDiagnSubCategory(String categoryId) {
		List<LabelBean> diagnosisSubCatList=null;
		List<LabelBean> diagnosisSubCatList1=new ArrayList<LabelBean>();
		try
		{
			diagnosisSubCatList=patientDao.getDiagnSubCategory(categoryId);
			for (LabelBean labelBean: diagnosisSubCatList) {
                if (labelBean.getID() != null && labelBean.getVALUE() != null)
                {
                    LabelBean labelBean1=new LabelBean();
                    labelBean1.setID(labelBean.getID() + "~" + labelBean.getVALUE());
                    diagnosisSubCatList1.add(labelBean1);
                }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnSubCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return diagnosisSubCatList1;
	}
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDiseaseList
     * @function This Method is Used For getting Diagnosis Disease List(EHFM_DIA_DISEASE_MST--EhfmDiagDiseaseMst)
     */
	@Override
	public List<LabelBean> getDiagnDisease(String code, String param) {
		List<LabelBean> diseaseList=null;
		List<LabelBean> diseaseList1=new ArrayList<LabelBean>();
		try
		{
			diseaseList=patientDao.getDiagnDisease(code,param);
			for (LabelBean labelBean: diseaseList) {
                if (labelBean.getID() != null && labelBean.getVALUE() != null)
                {
                    LabelBean labelBean1=new LabelBean();
                    labelBean1.setID(labelBean.getID() + "~" + labelBean.getVALUE());
                    diseaseList1.add(labelBean1);
                }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnDisease() in PatientServiceImpl class."+e.getMessage());
		}
		return diseaseList1;
	}
	/**
     * ;
     * @param String code
     * @param String paramType
     * @return List<LabelBean> diagnosisDisAnatomicalList
     * @function This Method is Used For getting Diagnosis Disease Anatomical List(EHFM_DIA_DISANATOMICAL_MST--EhfmDiagDisAnatomicalMst)
     */
	@Override
	public List<LabelBean> getDiagnDisAnatomical(String code, String param) {
		List<LabelBean> disAnatomicalList=null;
		List<LabelBean> disAnatomicalList1=new ArrayList<LabelBean>();
		try
		{
			disAnatomicalList=patientDao.getDiagnDisAnatomical(code, param);
			for (LabelBean labelBean: disAnatomicalList) {
                if (labelBean.getID() != null && labelBean.getVALUE() != null)
                {
                    LabelBean labelBean1=new LabelBean();
                    labelBean1.setID(labelBean.getID() + "~" + labelBean.getVALUE());
                    disAnatomicalList1.add(labelBean1);
                }
            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDiagnDisAnatomical() in PatientServiceImpl class."+e.getMessage());
		}
		return disAnatomicalList1;
	}
	/**
     * ;
     * @return List<LabelBean> mainComplaintList
     * @function This Method is Used For getting Main Complaint List(EHFM_COMPLAINT_MST--EhfmComplaintMst)
     */
	@Override
	public List<LabelBean> getMainComplaintLst() {
		List<LabelBean> mainComplaintList=null;
		try
		{
			mainComplaintList=patientDao.getMainComplaintLst();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientServiceImpl class."+e.getMessage());
		}
		return mainComplaintList;
	}
	@Override
	public List<LabelBean> getDentalMainComplaintLst() {
		List<LabelBean> mainComplaintList=null;
		try
		{
			mainComplaintList=patientDao.getDentalMainComplaintLst();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientServiceImpl class."+e.getMessage());
		}
		return mainComplaintList;
	}
	/**
     * ;
     * @param String mainSymptomCode
     * @param String subSymptomCode
     * @return List<String> symptomList
     * @function This Method is Used For retrieving Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	@Override
	public List<String> getSymptomLists(String mainSymptomCode, String subSymptomCode) {
		List<String> mainSymptomList=null;
		try
		{
			mainSymptomList=patientDao.getSymptomList(mainSymptomCode,subSymptomCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getMainComplaintLst() in PatientServiceImpl class."+e.getMessage());
		}
		return mainSymptomList;
	}
	/**
     * ;
     * @param String mainSymptomCode
     * @return List<String> subSymptomList
     * @function This Method is Used For retrieving Sub Symptom List(EHFM_SYSTEMATIC_EXAM_FND--EhfmSystematicExamFnd)
     */
	@Override
	public List<String> getSubSymptomLists(String mainSymptomCode) {
		List<String> subSymptomList=null;
		try
		{
			subSymptomList=patientDao.getSubSymptomList(mainSymptomCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getSubSymptomLists() in PatientServiceImpl class."+e.getMessage());
		}
		return subSymptomList;
	}
	/**
     * ;
     * @param String icdCatCode
     * @param String asriCatCode
     * @param String hospId
     * @return List<String> procedureList
     * @function This Method is Used For retrieving Icd procedure List(EHFM_MAIN_THERAPY--EhfmTherapyProcMst)
     */
	@Override
	public List<String> getIcdProcList(String icdCatCode,String asriCatCode,String hospId,String state,String hosType,String treatType) {
		List<String> procedureList=null;
		try
		{
			procedureList=patientDao.getIcdProcList(icdCatCode,asriCatCode,hospId,state,hosType,treatType);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getIcdProcList() in PatientServiceImpl class."+e.getMessage());
		}
		return procedureList;
	}
	/**
     * ;
     * @param String drugTypeCode
     * @return List<String> drugSubTypeList
     * @function This Method is Used For getting Drug Sub Type List From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	@Override
	public List<String> getDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getDrugSubList(drugTypeCode);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	@Override
	public String getDrugDetails(String drugCode) {
		String drugDetails=null;
		try
		{
			drugDetails=patientDao.getDrugDetails(drugCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return drugDetails;
	}
	/**
     * ;
     * @param String icdProcCode
     * @return String procCode
     * @function This Method is Used For getting Procedure Codes From EhfmTherapyProcMst(EHFM_THERAPY_PROC_MST )
     */
	@Override
	public String getTherapyProcCodes(String icdProcCode) {
		String procCode=null;
		try
		{
			procCode=patientDao.getTherapyProcCodes(icdProcCode);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyProcCodes() in PatientServiceImpl class."+e.getMessage());
		}
		return procCode;
	}
	/**
     * ;
     * @return List<LabelBean> opCategoryList
     * @function This Method is Used For getting Therapy OP Category Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	@Override
	public List<LabelBean> getTherapyOPCategory() {
		List<LabelBean> opCategoryList=null;
		try
		{
			opCategoryList=patientDao.getTherapyOPCategory();
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getTherapyOPCategory() in PatientServiceImpl class."+e.getMessage());
		}
		return opCategoryList;
	}
	/**
     * ;
     * @param opMainCode
     * @return List<LabelBean> opPkgList
     * @function This Method is Used For getting Therapy OP Package Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	@Override
	public List<String> getOpPkgList(String opCatCode) {
		List<String> pkgList1 = new ArrayList<String>();
		List<LabelBean> pkgList=null;
		try
		{
			pkgList=patientDao.getOpPkgList(opCatCode);
			 for (LabelBean labelBean: pkgList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (pkgList1 != null && pkgList1.size() > 0) {
	                    	pkgList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	pkgList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getOpPkgList() in PatientServiceImpl class."+e.getMessage());
		}
		return pkgList1;
	}
	/**
     * ;
     * @param opPkgCode
     * @return List<LabelBean> opIcdList
     * @function This Method is Used For getting Therapy OP ICD Codes From EhfmOpTherapyPkgMst(EHFM_OP_THERAPY_PKG_MST )
     */
	@Override
	public List<String> getOpIcdList(String opPkgCode) {
		List<String> icdList1 = new ArrayList<String>();
		List<LabelBean> icdList=null;
		try
		{
			icdList=patientDao.getOpIcdList(opPkgCode);
			 for (LabelBean labelBean: icdList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (icdList1 != null && icdList1.size() > 0) {
	                    	icdList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	icdList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getOpPkgList() in PatientServiceImpl class."+e.getMessage());
		}
		return icdList1;
	}
	/**
     * ;
     * @param cardNo
     * @param opCatCode
     * @return int count
     * @function This Method is Used For getting count of cases with given cardNo and therapyOPCategory whose medication period is not completed
     */
	@Override
	public int validateTherapyOPCat(String cardNo, String opCatCode) {
		int count=0;
		try
		{
			count=patientDao.validateTherapyOPCat(cardNo, opCatCode);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return count;
	}
	
	
	
	@Override
	public List<DrugsVO> getChronicDetails(String cardNo) {
		List<DrugsVO> drugsList=null;
		try
		{
			drugsList=patientDao.getChronicDetails(cardNo);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return drugsList;
	}
	@Override
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId,String fromDisp) {
		PreauthVO preauthVO = patientDao.getPatientFullDtls(lStrCaseId,patientId,fromDisp);
		return preauthVO;
	}
	@Override
	public List<SpecialityVO> getPatientSpecialities(String fromDisp, String patientId) {
		List<SpecialityVO> specVO = patientDao.getPatientSpecialities(fromDisp,patientId);
		return specVO;
	}
	@Override
	public String deleteInvestigations(String caseId, String procCode,String investId,String asriCode) {
		String result="false";
		try
		{
			result=patientDao.deleteInvestigations(caseId,procCode,investId,asriCode);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return result;
	}
	@Override
	public String deleteGenInvest(String patientId, String investId) {
		String result="false";
		try
		{
			result=patientDao.deleteGenInvest(patientId,investId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in validateTherapyOPCat() in PatientServiceImpl class."+e.getMessage());	
		}
		return result;
	}
	@Override
	public List<String> getInvestigations(String lStrBlockId) {
		List<String> mainInvestList=null;
		try
		{
			mainInvestList=patientDao.getInvestigations(lStrBlockId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getInvestigations() in PatientServiceImpl class."+e.getMessage());
		}
		return mainInvestList;
	}
	@Override
	public List<String> getOpDrugSubList(String drugTypeCode ,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getOpDrugSubList(drugTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getOpPharSubList(String drugTypeCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getOpPharSubList(drugTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getOpChemSubList(String pharSubCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getOpChemSubList(pharSubCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getChemSubList(String cSubGrpCode,String pStrIpOpType) {
		List<LabelBean> drugSubList=null;
		List<String> drugSubList1 = new ArrayList<String>();
		try
		{
			drugSubList=patientDao.getChemSubList(cSubGrpCode,pStrIpOpType);
			 for (LabelBean labelBean: drugSubList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (drugSubList1 != null && drugSubList1.size() > 0) {
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	drugSubList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return drugSubList1;
	}
	@Override
	public List<String> getRouteList(String routeTypeCode,String pStrIpOpType) {
		List<LabelBean> routeList=null;
		List<String> routeList1 = new ArrayList<String>();
		try
		{
			routeList=patientDao.getRouteList(routeTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: routeList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (routeList1 != null && routeList1.size() > 0) {
	                    	routeList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	routeList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getDrugSubList() in PatientServiceImpl class."+e.getMessage());
		}
		return routeList1;
	}
	@Override
	public List<String> getStrengthList(String strengthTypeCode,String pStrIpOpType) {
		List<LabelBean> strengthList=null;
		List<String> strengthList1 = new ArrayList<String>();
		try
		{
			strengthList=patientDao.getStrengthList(strengthTypeCode,pStrIpOpType);
			 for (LabelBean labelBean: strengthList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (strengthList1 != null && strengthList1.size() > 0) {
	                    	strengthList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	                    } else
	                    	strengthList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE()+"@");
	            }
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in getStrengthList() in PatientServiceImpl class."+e.getMessage());
		}
		return strengthList1;
	}
	
	@Override
	public String getSequence(String pStrSeqName)throws Exception{
		String seqId = patientDao.getSequence(pStrSeqName);
		return seqId;
	}
	@Override
	public List<CommonDtlsVO> getDtrsFormDtls(String caseId,String fromDisp) throws Exception {
		List<CommonDtlsVO> result=null;
		try
		{
			result=patientDao.getDtrsFormDtls(caseId,fromDisp);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDtrsFormDtls() in PatientServiceImpl class."+e.getMessage());
		}
		return result;
	}
	@Override
	public PatientVO getOPCases(HashMap<String, String> searchMap)
			throws Exception {
		PatientVO registeredPatientsList=null;
		registeredPatientsList=patientDao.getOPCases(searchMap);
		return registeredPatientsList;
	}
	@Override
	public String getDutyDoctorById(String regNo) {
		return patientDao.getDutyDoctorById(regNo);
	}
	@Override
	public String getProcedureType(String procCode,String asriCode) {
		String procedureType=null;
		try
		{
			procedureType=patientDao.getProcedureType(procCode, asriCode);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getProcedureType() in PatientServiceImpl class."+e.getMessage());
		}
			return procedureType;
	}
	
	
	/**
	 * ;
	 * @param caseId
	 * @return List<CommonDtlsVO> caseDetailsList
	 	 * @function This Method is Used For common details for the caseId specified
	 */
	@Override
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId)
	{
		List<CommonDtlsVO> commonDtls=null;
		try {
			commonDtls = patientDao.getPatientCommonDtls(caseId);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getPatientCommonDtls() in PatientServiceImpl class."+e.getMessage());	
		}
		return commonDtls;		
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<LabelBean> docAavilList
	 * @function This Method is Used For getting available doctor list for given hospital and specialty
	 */
	@Override
	public List<LabelBean> getDocAvailLst(PatientVO patientVO) {
		List<LabelBean> docAvailList=null;
		try {
			docAvailList = patientDao.getDocAvailLst(patientVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getDocAvailLst() in PatientServiceImpl class."+e.getMessage());	
		}
		return docAvailList;	
	}
	/**
	 * ;
	 * @param PatientVO patientVO
	 * @return List<String> specInvestList
	 * @function This Method is Used For getting special investigation list for given therapy
	 */
	@Override
	public List<String> getSpecialInvestigationLst(PatientVO patientVO) {
		List<String> specInvestList=null;
		try {
			specInvestList = patientDao.getSpecialInvestigationLst(patientVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getSpecialInvestigationLst() in PatientServiceImpl class."+e.getMessage());	
		}
		return specInvestList;	
	}
	@Override
	public List<PreauthVO> getcaseSurgeryDtls(String caseId)  
	{
		List<PreauthVO> lstSurgerydlts = null;
		try{
			lstSurgerydlts=patientDao.getcaseSurgeryDtls(caseId);
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return lstSurgerydlts;
	}
	@Override
	public List<LabelBean> getSymptomsDtls(String lStrCaseId) 
	{
		List<LabelBean> lstSymptomDlts = null;
		try{
			lstSymptomDlts=patientDao.getSymptomsDtls(lStrCaseId);
		}catch(Exception e)
		{
			e.printStackTrace();	
		}
		return lstSymptomDlts;
	}
	@Override
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId)
	{
		PreauthVO PreauthVO = patientDao.getPatientOpDtls(pStrCaseId, pStrPatientId);
		return PreauthVO;
	}
	@Override
	public CommonDtlsVO getOtherDtls(String pStrCaseId,String pStrPatId,String fromDisp)
	{
		CommonDtlsVO commonDtlsVO=null;
		try
		{
			commonDtlsVO = patientDao.getOtherDtls(pStrCaseId, pStrPatId,fromDisp);
		}
		catch (Exception e) {
			e.printStackTrace();	
		}
		return commonDtlsVO;
	}

	/*
	 * Added by Srikalyan to get the Biometric Details for corresponding
	 * Registered Patient
	 */
	@Override
	public PatientVO getBiomDtls(CommonDtlsVO commonDtlsVO) {
		PatientVO patientVO = null;
		try {
			patientVO = patientDao.getBiomDtls(commonDtlsVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return patientVO;
	}
	
	@Override
	public String getEmpNameById(String id)
	{
		return patientDao.getEmpNameById(id);
	}
	@Override
	public String getDoctorById(String id) {
		return patientDao.getDoctorById(id);
	}
	@Override
	public List<DrugsVO> getDrugs(String pStrPatId,String flag)
	{
		List<DrugsVO> drugsList=null;
		try
		{
			drugsList=patientDao.getDrugs(pStrPatId, flag);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return drugsList;
	}
	
	public String getHospActiveStatus(String userId, String roleId)
	{
		String actStatus="";
		try
		{
			actStatus=patientDao.getHospActiveStatus(userId, roleId);
		}
		catch(Exception e)
		{
			GLOGGER.error("Error in method getHospActiveStatus() of PatientServiceimpl "+e.getMessage());
		}
		return actStatus;
	}
	
	
	/**
     * @param String icdProcCode
     * @return int dentalUnits
     * @function This Method is implementation For getting Dental Units for given Procedure
     */
	@Override
	public int getDenUnitsList(String icdProcCode)
	{
		int denUnitsList=0;
		try
		{
			denUnitsList=patientDao.getDenUnitsList(icdProcCode);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return denUnitsList;
	}
	/*
	 * Added by Srikalyan to Verify New Born
	 * Baby Condition for Mithra based on 
	 * the scheme
	 */
	public String checkNewBornCond(String lStrUserId)
		{
			String cond="N";
			cond=patientDao.checkNewBornCond(lStrUserId);
			return cond; 
		}
	
	public String getInvestPrice(String blockId,String investId)
	{
		String price=null;
		price=patientDao.getInvestPrice( blockId, investId);
		return price;
	}
	
	
	
		
		public String getDaysActiveOP(String caseId)
		{
			String msg=null;
			msg=patientDao.getDaysActiveOP(caseId);
			return msg;
			
		}
		
		public List<LabelBean> getConsultDtls(String patientId)
		{
			List<LabelBean> consulLst = new ArrayList<LabelBean>();
			consulLst=patientDao.getConsultDtls(patientId);
			return consulLst;
			
		}
		
		public List<CasesSearchVO> getOpPastHistoryDetails(CasesSearchVO vo)
		{
			List<CasesSearchVO> lstCase=new ArrayList<CasesSearchVO>();
			lstCase=patientDao.getOpPastHistoryDetails(vo);
			return lstCase;
		}
		
		public String getEmpCode(String PatientId)
		{
			String empCode=null;
			empCode=patientDao.getEmpCode(PatientId);
			return empCode;
		}
		
/*Added by venkatesh for chronic op migration*/
		public String saveChronicCaseDetails(PatientVO patientVO) throws Exception 
		{
			String chronicNo=null;
			chronicNo=patientDao.saveChronicCaseDetails(patientVO);
			return chronicNo;
		}
		public EhfmOpDrugMst getopdrugDataAuto(String chemicalCode)
		{
			EhfmOpDrugMst ehfmOpDrugMst=new EhfmOpDrugMst();
			
			ehfmOpDrugMst=patientDao. getopdrugDataAuto(chemicalCode);
			return ehfmOpDrugMst;
			
			
		}
/*
 * Added by Srikalyan to Verify and get
 * Dental Conditions for TG Patients 
 */
@Override
public PreauthVO validateAndGetDentalCond(PreauthVO preauthVO)
	{
		PreauthVO preVO=new PreauthVO();
		try
			{
				StringBuffer query=new StringBuffer();
				query.append( " select caseId as caseId from EhfCase where casePatientNo = '"+preauthVO.getPatientID()+"' " );
				
				String classPath="com.ahct.patient.vo.PreauthVO",caseId=null;
				Map<String ,Object> resMap=null;
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				if(resMap!=null)
					{
						PreauthVO returnVO=returnVOFromMap(resMap,classPath);
						if(returnVO !=null && returnVO.getCaseId()!=null)
							caseId=returnVO.getCaseId();
					}
				
				if(preauthVO!=null)
					{
						if(preauthVO.getIcdProcCode()!=null && !"".equalsIgnoreCase(preauthVO.getIcdProcCode())
							&& preauthVO.getPatientID()!=null && !"".equalsIgnoreCase(preauthVO.getPatientID())
							&& preauthVO.getScheme()!=null &&!"".equalsIgnoreCase(preauthVO.getScheme()))
						{
							String icdProcCode=preauthVO.getIcdProcCode();
							String schemeId=preauthVO.getScheme();
							
							PreauthVO resultVO=getDentalCondDB(icdProcCode,schemeId);
							if(resultVO!=null)
								{
									preVO=resultVO;
									String ageMsg=null,unitsVO=null;
									if(resultVO.getAgeLimit()!=null && !"".equalsIgnoreCase(resultVO.getAgeLimit())
											&& !"-1".equalsIgnoreCase(resultVO.getAgeLimit()))
										{
											//Method to get the Required Message for age condition
											ageMsg = getAgeMsg(Integer.parseInt(resultVO.getAgeLimit()),preauthVO.getPatientID());
											preVO.setAgeMsg(ageMsg);
										}
									if(resultVO.getLifetimeUnitsLimit()!=null && !"".equalsIgnoreCase(resultVO.getLifetimeUnitsLimit()))
										{
											//Method to get the Number of Units Left
											if(resultVO.getLifetimeUnitsLimit().equalsIgnoreCase("-1"))
												unitsVO="-1";
											else
												unitsVO=calculateUnitsLeft(icdProcCode,resultVO.getLifetimeUnitsLimit(),resultVO.getLifeTimeMonths(),preauthVO.getPatientID(),preauthVO.getTestKnown(),caseId);
											
											preVO.setActualUnitsLeft(unitsVO);
										}
									if(resultVO.getComboProcCode()!=null && !"".equalsIgnoreCase(resultVO.getComboProcCode())
											&& !"NA".equalsIgnoreCase(resultVO.getComboProcCode()))
										{
											String comboProcs=getComboNonComProcs(resultVO.getComboProcCode(),preauthVO.getScheme());
											preVO.setComboProcNames(comboProcs);
										}
									if(resultVO.getNonComboProcCode()!=null && !"".equalsIgnoreCase(resultVO.getNonComboProcCode())
											&& !"NA".equalsIgnoreCase(resultVO.getNonComboProcCode()))
										{
											String nonComboProcs=getComboNonComProcs(resultVO.getNonComboProcCode(),preauthVO.getScheme());
											preVO.setComboNonProcNames(nonComboProcs);
										}
									if(resultVO.getStandaloneProc()!=null && !"".equalsIgnoreCase(resultVO.getStandaloneProc())
											&&  !"NA".equalsIgnoreCase(resultVO.getStandaloneProc()))
										{
											String standAloneProcs=getComboNonComProcs(resultVO.getStandaloneProc(),preauthVO.getScheme());
											preVO.setStandaloneProcNames(standAloneProcs);
										}
								}
						}
				}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in validateAndGetDentalCond() in PatientServiceImpl class."+e.getMessage());
				return preVO;
			}
	return preVO;
}	
		public EhfOpConsultData getOpDocDtls(String patientId)
		
		{
			
			EhfOpConsultData ehfOpConsultData=new EhfOpConsultData();
			
			ehfOpConsultData=patientDao.getOpDocDtls(patientId);
			return ehfOpConsultData;
		}
		
		public LabelBean getDiagnosisDtlsAuto(String disCode) throws Exception
		{
			LabelBean diagDtl=new LabelBean();
			diagDtl=patientDao.getDiagnosisDtlsAuto(disCode);
			return diagDtl;
		}
		public List<LabelBean> getDiagList()
		{
			List<LabelBean> diagList=new ArrayList<LabelBean>();
			diagList=patientDao.getDiagList();
			return diagList;
		}
		
		public List<LabelBean> getDiagListSearch(String data,String type) throws Exception
		{
			List<LabelBean> diagLst=patientDao.getDiagListSearch(data,type);
			return diagLst;
		}




		/*
		 * Added by Srikalyan to get the Submit
		 * Drug Details of Pharmacy Case
		 */
		@Override
		public String submitPharmacyCase(String caseId,String patId,String drugDtls)
			{
				String retMsg = null;
				try {
						retMsg = patientDao.submitPharmacyCase(caseId, patId ,drugDtls);
					}
				catch (Exception e) 
					{ 
						e.printStackTrace();
					}
				return retMsg;
			}

		
		




/*
 * Added by Srikalyan to get Dental Conditions for TG Patients
 */
private PreauthVO getDentalCondDB(String icdProcCode,String schemeId)
	{
		PreauthVO returnVO=new PreauthVO(); 
		Map<String ,Object> resMap=null;
		StringBuffer query=new StringBuffer();
		
		try
			{
				query.append( " select edp.ageLimit as ageLimit , edp.comoboProcCode as comboProcCode, ");
				query.append( " edp.frameworkPrice as frameworkPrice , edp.ipOp as ipOp, ");
				query.append( " edp.lifetimeUnitsLimit as lifetimeUnitsLimit , edp.standaloneProc as standaloneProc, ");
				query.append( " edp.subsequentPrice as subsequentPrice , edp.unitsLimit as unitsLimit, ");
				query.append( " edp.id.icdProcCode as icdProcCode , edp.id.schemeId as scheme , edp.activeYn as activeYn , ");
				query.append( " edp.nonComboProcCode as nonComboProcCode , edp.lifeTimeMonths as lifeTimeMonths " );
				
				query.append( " from EhfmDentalProcCriteria edp  ");
				query.append( " where edp.id.icdProcCode = '"+icdProcCode+"' ");
				query.append( " and edp.id.schemeId = '"+schemeId+"' and edp.activeYn='Y' ");
				
				String classPath="com.ahct.patient.vo.PreauthVO";
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				if(resMap!=null)
					returnVO=returnVOFromMap(resMap,classPath);
				
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getDentalCondDB() in PatientServiceImpl class."+e.getMessage());
				return returnVO;
			}
		
		return returnVO;

		


	}
	public String checkDentalClinic(String userid , String patientId)
		{
			return patientDao.checkDentalClinic(userid , patientId);
			
		}
	
	public String checkGovernmentHosp(String userid , String patientId,String fromDisp)
	{
		return patientDao.checkGovernmentHosp(userid , patientId,fromDisp);
		
	}
	public String checkGovernmentHosp(String userid,String fromDisp)
	{
		return patientDao.checkGovernmentHosp(userid,fromDisp);
		
	}
/*
 * Added by Srikalyan to get Age Message in Dental Conditions for TG Patients
 */
private String getAgeMsg(int ageLimit,String patientID)
	{
		String ageMsg=null;
		try
			{
				Map<String ,Object> resMapNew=null;
				String classPath="com.ahct.model.EhfPatient";
				Map<String,Object> resMap=new HashMap<String,Object>();
				resMap.put("classPath",classPath);
				resMap.put("ID",patientID);
				resMapNew=patientDao.executeIDClass(resMap);
				
				if(resMapNew!=null)
					{
						if(resMapNew.get(classPath)!=null)
							{
								EhfPatient ehfPatient=(EhfPatient)resMapNew.get(classPath);
								if(ehfPatient!=null)
									{
										if(ehfPatient.getAge()!=null)
											{
												if(ehfPatient.getAge().intValue()>ageLimit && ageLimit!=-1)
													{
														ageMsg="Age Limit for the selected Procedure is "+ageLimit+" Years.Hence current Procedure cannot be selected as the Patient's age is "+ehfPatient.getAge()+".";
													}
												else
													ageMsg=null;
											}
									}
							}
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getAgeMsg() in PatientServiceImpl class."+e.getMessage());
				return ageMsg;
			}
		return ageMsg;
	}



/*
 * Added by Srikalyan to get Life Time Units Left
 * in Dental Conditions for TG Patients
 */
private String calculateUnitsLeft(String icdProcCode,String lifeTimeUnits , String lifeTimeMnths , String patientId,String deleteProc,String caseId)
	{
		String unitsLeft=null,unitsDone="0";
		try
			{
				StringBuffer query=new StringBuffer();
				PreauthVO returnVO=new PreauthVO(); 
				if(icdProcCode!=null && !"".equalsIgnoreCase(icdProcCode))
					{
						query.append( " select sum(c.surgProcUnits) as opProcUnits " ); 
						query.append( " from EhfPatient a , EhfCase b , " );
						query.append( " EhfCaseTherapy c , EhfPatient d " );
						query.append( " where a.patientId  = '"+patientId+"' " );
						query.append( " and d.patientId = b.casePatientNo and b.caseId = c.caseId " );
						query.append( " and d.cardNo = a.cardNo  " );
						query.append( " and c.activeyn = 'Y' ");
						query.append( " and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~', ',')+") ");
						query.append( " and c.icdProcCode = '"+icdProcCode+"' " );
						
						if(deleteProc!=null && "Y".equalsIgnoreCase(deleteProc))
							{
								query.append( " and (case when c.caseId = '"+caseId+"' and ");
								query.append( " c.id.icdProcCode = '"+icdProcCode+"' then 0 else 1 end=1)");
							}
						
						if(lifeTimeMnths!=null && !"".equalsIgnoreCase(lifeTimeMnths) && !"-1".equalsIgnoreCase(lifeTimeMnths))
							query.append( " and c.crtDt > add_months(sysdate , -"+lifeTimeMnths+") " );
						
						Map <String , Object > resMap = new HashMap <String ,Object> ();
						String classPath="com.ahct.patient.vo.PreauthVO";
						resMap=patientDao.executeNormalQuery(classPath,query.toString());
						
						if(resMap!=null)
							{	
								returnVO=returnVOFromMap(resMap,classPath);
								if(returnVO!=null)
									{
										if(returnVO.getOpProcUnits()!=null && !"".equalsIgnoreCase(returnVO.getOpProcUnits()))
											unitsDone=returnVO.getOpProcUnits();
											
										if( lifeTimeUnits!=null && unitsDone!=null)
											{
												if(Integer.parseInt(lifeTimeUnits) > Integer.parseInt(unitsDone))
													unitsLeft=Integer.toString(Integer.parseInt(lifeTimeUnits)-Integer.parseInt(unitsDone));
												else
													unitsLeft="0";
											}	
									}	
							}		
								
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in calculateUnitsLeft() in PatientServiceImpl class."+e.getMessage());
				return unitsLeft;
			}
		return unitsLeft;
	}


/*
 * Added by Srikalyan to get Names of Combo and Non Combo 
 * Procedures in Dental Conditions for TG Patients
 */
private String getComboNonComProcs(String icdProcCodes,String schemeId)
	{
		String comboCodes[]=null,comboNames=null,cond=null;
		try
			{
				if(icdProcCodes!=null && !"".equalsIgnoreCase(icdProcCodes) && schemeId!=null && !"".equalsIgnoreCase(schemeId))
					{
						if(icdProcCodes.contains("~"))
							{
								comboCodes=icdProcCodes.split("~");
							}
						else
							{
								comboCodes=new String[1];
								comboCodes[0]=icdProcCodes;
							}
						
						if(comboCodes.length>0)
							{
								int count=0;
								String orderCond="";
								for(int i=0;i<comboCodes.length;i++)
									{
										if(cond==null)
											cond="'"+comboCodes[i]+"'";
										else
											cond=cond+",'"+comboCodes[i]+"'";
										
										orderCond = orderCond + " when a.id.icdProcCode='"+comboCodes[i]+"' then "+(++count)+"";
									}
								StringBuffer query=new StringBuffer();
								query.append(" select a.procName as procName ");
								query.append(" from EhfmTherapyProcMst a " );
								query.append(" where a.id.icdProcCode in ( "+cond+" ) ");
								query.append(" and a.id.state ='"+schemeId+"' ");
								
								if(count>0)
									query.append(" order by case "+orderCond+" else 0 end ");
								
								Map <String , Object > resMap = new HashMap <String ,Object> ();
								String classPath="com.ahct.patient.vo.PreauthVO";
								resMap=patientDao.executeNormalQuery(classPath,query.toString());
								
								List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
								if(localVOLst!=null)
									{
										if(localVOLst.size()>0)
											{
												for(PreauthVO localVO:localVOLst)
													{
														if(localVO.getProcName()!=null && !"".equalsIgnoreCase(localVO.getProcName()))
															{
																if(comboNames!=null)
																	comboNames=comboNames+"~"+localVO.getProcName();
																else
																	comboNames=localVO.getProcName();
															}
													}
											}
									}
							}
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getComboNonComProcs() in PatientServiceImpl class."+e.getMessage());
				return comboNames;
			}
		return comboNames; 
	}
/*
 * Added by Srikalyan to return preauthVO List from Map 
 */
@SuppressWarnings("unchecked")
private List<PreauthVO> returnVOLst(Map<String,Object> resMap,String classPath)
	{
		List<PreauthVO> returnLst=new ArrayList<PreauthVO>();
		try
			{
				if(resMap!=null)
					{	
						if(resMap.get(classPath)!=null)
							{
								List<PreauthVO> preVOLst=(List<PreauthVO>)resMap.get(classPath);;
								if(preVOLst!=null)
									{
										if(preVOLst.size()>0)
											{
												returnLst=preVOLst;
											}
									}
							}
					}
			}	
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in returnVOLst() in PatientServiceImpl class."+e.getMessage());
				return returnLst;
			}
		return returnLst;
	}


/*
 * Added by Srikalyan to return preauthVO from Map 
 */
@SuppressWarnings("unchecked")
private PreauthVO returnVOFromMap(Map<String,Object> resMap,String classPath)
	{
		PreauthVO returnVO=new PreauthVO();
		try
			{
				if(resMap!=null)
					{	
						if(resMap.get(classPath)!=null)
							{
								List<PreauthVO> preVOLst=(List<PreauthVO>)resMap.get(classPath);;
								if(preVOLst!=null)
									{
										if(preVOLst.size()>0)
											{
												if(preVOLst.get(0)!=null)
													{
														returnVO=preVOLst.get(0);	
													}
											}
									}
							}
					}
			}	
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in returnVOFromMap() in PatientServiceImpl class."+e.getMessage());
				return returnVO;
			}
		return returnVO;
	}

/*
 * Added by Srikalyan to get the Procedure Name 
 * for corresponding Procedure ID
 */
@Override
public PreauthVO getProcName(String procId,String patSchemeId)
	{	
		PreauthVO resultVO =new PreauthVO();
		try
			{
				StringBuffer query=new StringBuffer();
				query.append( " select a.procName as procName " );
				query.append( " from EhfmTherapyProcMst a " );
				query.append( " where a.id.icdProcCode = '"+procId+"' " );
				query.append( " and a.id.state = '"+patSchemeId+"' " );
				query.append( " and a.activeYN = 'Y' " );
				
				Map <String , Object > resMap = new HashMap <String ,Object> ();
				String classPath="com.ahct.patient.vo.PreauthVO";
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				
				resultVO=returnVOFromMap(resMap,classPath);
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in getProcName() in PatientServiceImpl class."+e.getMessage());
				return resultVO;
			}
		
		return resultVO;
	}

/*
 * Added by Srikalyan to get the Biometric Details 
 * for corresponding Registered Patient
 */
@Override
public boolean verifyPatientBiometrics(String patId ,String fingerPrint)
	{
		boolean returnMsg = false;
		try {
			returnMsg = patientDao.verifyPatientBiometrics(patId,fingerPrint);
		} catch (Exception e) {
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in verifyPatientBiometrics() in PatientServiceImpl class."+e.getMessage());
		}
		return returnMsg;
	}


/***
 * @author Kalyan
 * @return type
 * @function This Method is Used to check Special conditions for TG. 
 */
@Override
public PatientVO getPatientDentalDtls(String patientId) {
	// TODO Auto-generated method stub
	return patientDao.getPatientDentalDtls(patientId);
}	
@Override
public String checkSpecialDenatlCond(PreauthVO preauthVO)
	{
		String returnMsg =null;
		try
			{
				//returnMsg
				StringBuffer query=new StringBuffer();
				query.append( " select c.icdProcCode as icdProcCode , c.crtDt as date ,  " );
				query.append( " e.procName as procName ");
				query.append( " from EhfPatient a , EhfCase b , EhfCaseTherapy c , " );
				query.append( " EhfPatient d , EhfmTherapyProcMst e " );
				query.append( " where a.patientId = '"+preauthVO.getPatientID()+"'  " );
				query.append( " and b.casePatientNo = d.patientId " );
				query.append( " and b.caseId = c.caseId and a.cardNo = d.cardNo and c.activeyn = 'Y' " );
				query.append( " and c.asriCatCode = e.id.asriCode and c.icdProcCode = e.id.icdProcCode " );
				query.append( " and e.id.state = b.schemeId and activeYN = 'Y' " );
				query.append( " and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~',',')+") ");
			
				Map <String , Object > resMap = new HashMap <String ,Object> ();
				String classPath="com.ahct.patient.vo.PreauthVO";
				resMap=patientDao.executeNormalQuery(classPath,query.toString());
				
				List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
				if( localVOLst!=null && localVOLst.size() > 0)
					{	
						Calendar cal=Calendar.getInstance();
						cal.add(Calendar.YEAR, -5);
						Date date=cal.getTime();
						for(PreauthVO localVO:localVOLst)
							{
								if(localVO!=null && localVO.getIcdProcCode()!=null
										&& localVO.getDate()!=null
										&& localVO.getIcdProcCode().equalsIgnoreCase(preauthVO.getIcdProcCode()))
									{
										StringBuffer msg =new StringBuffer();
										msg.append( "Patient has already utilized this Procedure.");
										msg.append(localVO.getProcName());
										msg.append("("+preauthVO.getIcdProcCode()+")");
										msg.append(" cannot be selected as this procedure can be availed only once. ");
										if(date.after(localVO.getDate()))
											{
												msg.append("Instead ");
												msg.append(config.getString("DentalStatus_"+localVO.getIcdProcCode()));
												msg.append(" procedure can be availed.");
											}
										returnMsg=msg.toString();
									}
							}
					}
				
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exception Occurred in checkSpecialDenatlCond() in PatientServiceImpl class."+e.getMessage());
				return returnMsg;
			}
		return returnMsg;
	}
@Override
public String checkDentalHospital(String hospCode) {
	// TODO Auto-generated method stub
	
	return patientDao.checkDentalHospital(hospCode);
}
	/***
	 * @author Kalyan
	 * @param Patient ID
	 * @return Follow Up Flag a String
	 * @function This Method is Used to check Dental Follow UP Eligibilty for a Specific Patient. 
	 */
	public List<String> checkDenFlp(String patientID,List<String> therapyList)
		{
			String procCodeCond=null;
			List<String> returnLst = therapyList;
			try
				{
					String[] arr=new String[2];
					if(config.getString("dentalFlpConds")!=null)
						arr=config.getString("dentalFlpConds").split("~");
					
					for(String str:arr)
						{
							if(procCodeCond==null)
								procCodeCond="'"+str+"'";
							else
								procCodeCond+=",'"+str+"'";
						}
					
					StringBuffer query=new StringBuffer();					
					query.append( " select " );
					query.append( "   to_char(nvl(sum(case when c.icdProcCode = '"+arr[0]+"' then 1 else 0 end),0)) as surgCount , " );
					query.append( "   to_char(nvl(sum(case when c.icdProcCode = '"+arr[1]+"' then 1 else 0 end),0)) as flpSurgCount  " );
					query.append( "   from EhfPatient a , EhfCase b , EhfCaseTherapy c " );
					query.append( "   , EhfPatient d " );
					query.append( "   where a.patientId = '"+patientID+"'  " );
					query.append( "   and a.cardNo = d.cardNo and a.schemeId = d.schemeId  " );
					query.append( "   and b.casePatientNo = d.patientId and b.caseId = c.caseId  " );
					query.append( "   and b.caseStatus not in ("+config.getString("Preauth_Cancelled_Status").replace('~',',')+") ");
					query.append( "   and c.activeyn = 'Y' and c.icdProcCode in ("+procCodeCond+") " );
					query.append( "   and d.patientId not in ('"+patientID+"') ");
					query.append( "   and case when c.icdProcCode = '"+arr[0]+"' and b.csDisDt < (sysdate-11) " );
					query.append( " 		   then 1	" );
					query.append( "            when c.icdProcCode = '"+arr[1]+"' " );
					query.append( " 		   then 1	" );			
					query.append( " 		   else 0 ");
					query.append( "       end = 1 ");
					
					
					Map <String , Object > resMap = new HashMap <String ,Object> ();
					String classPath="com.ahct.patient.vo.PreauthVO";
					resMap=patientDao.executeNormalQuery(classPath,query.toString());
					
					List<PreauthVO> localVOLst=returnVOLst(resMap,classPath);
					if( localVOLst!=null && localVOLst.size()>0 && localVOLst.get(0)!=null )
						{
							/**Only Initial Proc utilized and Follow Up Proc need to be utilized then List will be sent Unmodified 
							   1.Conditions Surgery Count Should be greater than 0.
							   2.Follow Up Surgery Count should be 0 as this should be performed only once*/
							if(localVOLst.get(0).getSurgCount() !=null && !"".equalsIgnoreCase(localVOLst.get(0).getSurgCount())
											&& (Integer.parseInt(localVOLst.get(0).getSurgCount()) > 0)
									&& localVOLst.get(0).getFlpSurgCount() !=null && !"".equalsIgnoreCase(localVOLst.get(0).getFlpSurgCount())
											&& (Integer.parseInt(localVOLst.get(0).getFlpSurgCount()) == 0))
								{
									returnLst = therapyList;
								}
							/**If both Initial Proc and Follow Up Proc are utilized or
							   Both Initial Proc and Follow Up Proc are not utilized 
							   and for all the other conditions the list will be modified 
							   */
							else
								{
									for(int i=0;i<therapyList.size();i++)
										{
											PreauthVO vo=getProcName(arr[1],config.getString("Scheme.TG.State"));
											if(vo!=null && vo.getProcName()!=null)
												{
													if(therapyList.get(i).contains(arr[1]))
														{
															if(therapyList.get(i).contains(arr[1]+"~"+vo.getProcName()+"@"))
																{
																	therapyList.remove(i);
																	//break;
																	//Here This Loop is not breaked believing the possiblity of occurance of multiple Similar Procs. 
																}	
														}
												}
										}
									returnLst = therapyList;
								}
							
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					GLOGGER.error("Exception Occurred in checkSpecialDenatlCond() in PatientServiceImpl class."+e.getMessage());
					return returnLst;
				}
			
			return returnLst;
		}
	@Override
	public List<LabelBean> getdentalexaminationDtls(String prntId) {
		// TODO Auto-generated method stub
		return patientDao.getdentalexaminationDtls(prntId);
		
	}
	public String getTreatmentDntlvalue(String caseId) {
		// TODO Auto-generated method stub
		String dentalvalue = patientDao.getTreatmentDntlvalue(caseId);
		return dentalvalue;
	}
	@Override
	public List<LabelBean> getReferredCenterDtls(String lStrUserId,String roleId)
	{
		List<LabelBean> hospitalList=null;
		try {
			hospitalList = patientDao.getReferredCenterDtls(lStrUserId,roleId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getReferredCenterDtls() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}
	@Override
	public String getDispName(String hospCode) {
		
		String hospitalName=null;
		try
		{
			hospitalName=patientDao.getDispName(hospCode);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getHospName() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalName;
		
	}
	@Override
	public List<LabelBean> getHospDtlsList(String district,String speclty)
	{
			List<LabelBean> hospitalList=null;
		try
		{
			hospitalList = patientDao.getHospDtlsList(district,speclty);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}
	@Override
	public DrugsVO getDrugDtlsList(String caseId) 
	{
		DrugsVO drugsVO = patientDao.getDrugDtlsList(caseId);
		return drugsVO;
		
	}
	@Override
	public DrugsVO getDispDrugDtlsList(String caseId) 
	{
		DrugsVO drugsVO = patientDao.getDispDrugDtlsList(caseId);
		return drugsVO;
		
	}
	@Override
	public List<CommonDtlsVO> getDtrsReferealDtls(String caseId,String fromDisp) {
		List<CommonDtlsVO> referlDtls=null;
		try
		{
			referlDtls = patientDao.getDtrsReferealDtls(caseId,fromDisp);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return referlDtls;
	}
	@Override
	public List<LabelBean> getCatName(String lStrCaseId) {
		List<LabelBean> catList=null;
		try
		{
			catList = patientDao.getCatName(lStrCaseId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return catList;
	}
	@Override
	public String getTokenNo(String hospId,String currentDate, String specialityType,String roomNo) {
		// TODO Auto-generated method stub
		String tokenNo="";
		try
		{
			tokenNo = patientDao.getTokenNo(hospId,currentDate,specialityType,roomNo);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return tokenNo;
	}
	@Override
	public List<LabelBean> getRoomNos(String dispId, String specialityType) {
		// TODO Auto-generated method stub
		List<LabelBean> roomNo=null;
		try
		{
			roomNo = patientDao.getRoomNos(dispId,specialityType);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return roomNo;
		
	}
	@Override
	public List<LabelBean> getAllRoomTokenNo(String dispId) {
		
		List<LabelBean> roomTokenNo=null;
		try
		{
			roomTokenNo = patientDao.getAllRoomTokenNo(dispId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return roomTokenNo;
	}
	@Override
	public int updateRoomTokenNo(String dispId, String roleId, String userId) {
		int flag=0;
		try
		{
			flag = patientDao.updateRoomTokenNo(dispId, roleId, userId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return flag;
	}
	@Override
	public String getRoomTokenNo(String dispId, String roleId, String userId) {
		String tokenNo="";
		try
		{
			tokenNo = patientDao.getRoomTokenNo(dispId, roleId, userId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return tokenNo;
	}
	@Override
	public String getDispensaryName(String dispId) {
		String hospName="";
		try
		{
			hospName = patientDao.getDispensaryName(dispId);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getHospDtlsList() in PatientServiceImpl class."+e.getMessage());
		}
		return hospName;
	}
	@Override
	public List<LabelVO> getLabels(HashMap<String, Object> lMap) {
		// TODO Auto-generated method stub
		List<LabelVO> rsltList=null;
		try
		{
			rsltList = patientDao.getLabels(lMap);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getLabels() in PatientServiceImpl class."+e.getMessage());
		}
		return rsltList;		
		
	}
	@Override
	public boolean saveLabReportData(HashMap<String, Object> lMap) {
		// TODO Auto-generated method stub
		boolean rslt=true;
		try
		{
			rslt = patientDao.saveLabReportData(lMap);
		}
		catch (Exception e) {
			rslt=false;
			GLOGGER.error("Exception Occurred in saveLabReportData() in PatientServiceImpl class."+e.getMessage());
		}
		return rslt;
	}
	@Override
	public List<LabelVO> retrieveLabelsData(HashMap<String, Object> lMap) {
		// TODO Auto-generated method stub
		List<LabelVO> rsltList=null;
		try
		{
			rsltList = patientDao.retrieveLabelsData(lMap);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in retrieveLabelsData() in PatientServiceImpl class."+e.getMessage());
		}
		return rsltList;
		
	}
	@Override
	public boolean checkLabReportsubmitted(HashMap<String, Object> lMap) {
		// TODO Auto-generated method stub
		
		boolean rslt=true;
		try
		{
			rslt = patientDao.checkLabReportsubmitted(lMap);
		}
		catch (Exception e) {
			rslt=false;
			GLOGGER.error("Exception Occurred in saveLabReportData() in PatientServiceImpl class."+e.getMessage());
		}
		return rslt;
	}
	@Override
	public boolean checkAllSubmit(HashMap<String, Object> lMap) {
		// TODO Auto-generated method stub
		boolean rslt=true;
		try
		{
			rslt = patientDao.checkAllSubmit(lMap);
		}
		catch (Exception e) {
			rslt=false;
			GLOGGER.error("Exception Occurred in checkAllSubmit() in PatientServiceImpl class."+e.getMessage());
		}
		return rslt;
	
	}

	@Override
	public String getDrugQuant(LabelBean drugBalParam) {
		// TODO Auto-generated method stub
		
		String quant="";
		try
		{
			quant = patientDao.getDrugQuant(drugBalParam);
		}
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in saveLabReportData() in PatientServiceImpl class."+e.getMessage());
		}
		return quant;
	}
	

	public List<LabelBean> getDispDrugList(String drugType,String dispId)
	{
		List<LabelBean> dispDrugList=new ArrayList<LabelBean>();
		try
		{
			dispDrugList = patientDao.getDispDrugList(drugType,dispId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return dispDrugList;
	}
	public List<LabelBean> getDrugTypeList()
	{
		List<LabelBean> dispDrugTypeList=new ArrayList<LabelBean>();
		try
		{
			dispDrugTypeList = patientDao.getDrugTypeList();
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return dispDrugTypeList;
	}
/*	public List<LabelBean> getDoctorNames()
	{
		List<LabelBean> docNames=new ArrayList<LabelBean>();
		try
		{
			docNames = patientDao.getDoctorNames();
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return docNames;
	}*/
	public boolean updateDrugsInventory(List<LabelBean> drugDtls)
	{
		boolean flag=false;
		try{
			flag=patientDao.updateDrugsInventory(drugDtls);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public List<LabelBean> getEmpDtls(String empId) {
		List<LabelBean> empList=new ArrayList<LabelBean>();
		try
		{
			empList = patientDao.getEmpDtls(empId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return empList;
	}
	@Override
	public List<LabelBean> getFedBackDtls(String enrollId) {
		List<LabelBean> empfeedList=new ArrayList<LabelBean>();
		try
		{
			empfeedList = patientDao.getFedBackDtls(enrollId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return empfeedList;
	}
	@Override
	public List<LabelBean> getEmpDtls1(String empId) {
		List<LabelBean> empList1=new ArrayList<LabelBean>();
		try
		{
			empList1 = patientDao.getEmpDtls1(empId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return empList1;
	}
	@Override
	public List<LabelBean> getEmpDtls2(String empId) {
		List<LabelBean> empList2=new ArrayList<LabelBean>();
		try
		{
			empList2 = patientDao.getEmpDtls2(empId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return empList2;
	}

	@Override
	public List<DrugsVO> getDispInventDrugBal(LabelBean drugBalParam) {
		List<DrugsVO> drugsBal=new ArrayList<DrugsVO>();
		try
		{
			drugsBal = patientDao.getDispInventDrugBal(drugBalParam);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispInventDrugBal() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return drugsBal;
	}
	public List<DrugsVO> getDispDrugInventReport(HashMap drugParam) {
		List<DrugsVO> drugsBal=new ArrayList<DrugsVO>();
		try
		{
			drugsBal = patientDao.getDispDrugInventReport(drugParam);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispInventDrugBal() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return drugsBal;
	}
	public List<LabelBean> getDispDrugDetails(String drugType,String drugId,String userId,String transferType)
	{
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getDispDrugDetails(drugType,drugId,userId,transferType);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugDetails() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
		
	}
	@Override
	public List<LabelBean> getDispDrugMfg(String drugId)
	{
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getDispDrugMfg(drugId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
		
	}
	public List<LabelBean> getDrugMftrList()
	{
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getDrugMftrList();
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugMftrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	public List<LabelBean> getDrugDstbtrList(){
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getDrugDstbtrList();
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugDstbtrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	
	public boolean addNewMnfNDist(LabelBean drgDetails)
	{
		boolean flag=false;
		try
		{
			flag = patientDao.addNewMnfNDist(drgDetails);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in addNewMnfNDist() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return flag;
	}
	
	public boolean deleteDrugSet(String drugCode){
		boolean deleted=false;
		try
		{
			deleted = patientDao.deleteDrugSet(drugCode);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in addNewMnfNDist() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return deleted;
	}
	public List<CommonDtlsVO> getPreviousDrugHist(String cardNo){
		List<CommonDtlsVO> details=null;
		try
		{
			details = patientDao.getPreviousDrugHist(cardNo);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugDstbtrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	public EhfmHospitals getHospTaashaFlg(String currHospId){
		EhfmHospitals ehfmHospitals=new EhfmHospitals();
		try
		{
			ehfmHospitals = patientDao.getHospTaashaFlg(currHospId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in saveLabReportData() in PatientServiceImpl class."+e.getMessage());
		}
		return ehfmHospitals;
	}
	@Override
	public LabelBean saveEmployeeFeedbackData(LabelBean labelBeanVo){
		
		@SuppressWarnings("unused")
		LabelBean labelBean=null;
		try
		{
			labelBeanVo=patientDao.saveEmployeeFeedbackData(labelBeanVo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in retrieveCardDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return labelBeanVo;
	}
	@Override
	public List<CasesSearchVO> getDispPastHistoryDetails(String patientId, String cardNo) {
		List<CasesSearchVO> pastHist=null;
		try{
			pastHist=patientDao.getDispPastHistoryDetails(patientId, cardNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pastHist;
	}
	@Override
	public List<LabelBean> getPatInvestList(String patientId) {
		List<LabelBean> investList=null;
		try{
			investList=patientDao.getPatInvestList(patientId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return investList;
	}
	@Override
	public void generateSendInvReports(String patientId) {
		
		try{
			patientDao.generateSendInvReports(patientId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@Override
	public List<LabelBean> getPatientPastVisitDetails(String cardNo) {
		List<LabelBean> pastVisitList=null;
		try{
			pastVisitList=patientDao.getPatientPastVisitDetails(cardNo);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pastVisitList;
	}
	public CreateEmployeeVO getLeaveReport(CreateEmployeeVO createEmployeeVO){
		CreateEmployeeVO leaveList=null;
		try{
			leaveList=patientDao.getLeaveReport(createEmployeeVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return leaveList;
		
	}	
	@Override
	public List<LabelBean> getdispdentalexaminationDtls(String prntId) {
		// TODO Auto-generated method stub
		return patientDao.getdispdentalexaminationDtls(prntId);
		
	}
	@Override
	public String cancelPatient(PatientVO patVo) 
	{
		return patientDao.cancelPatient(patVo);
	}
	@Override
	public String cancelPatientReg(PatientVO patVo) 
	{
		return patientDao.cancelPatientReg(patVo);
	}
	@Override
	public boolean validateSandostatinInj(String patientNo, String icdProcCode) {
		return patientDao.validateSandostatinInj(patientNo,icdProcCode);
	}
	
	public String getCriticalFlg(String caseId)
	{
		return patientDao.getCriticalFlg(caseId);
	}
	
	public String saveDocDrugs(PatientVO patientVO)  
	{
		return patientDao.saveDocDrugs(patientVO);
	}
	public String saveDocSpecialities(PatientVO patientVO)  
	{
		return patientDao.saveDocSpecialities(patientVO);
	}
	public String saveSpecialistDoctors(String patientId,  List<SpecialityVO> specList)  
	{
		return patientDao.saveSpecialistDoctors(patientId, specList);
	}
	@Override
	public List<LabelBean> getDoctorNames(String dispCode, String splstGroup) {
		// TODO Auto-generated method stub
		List<LabelBean> docNames=new ArrayList<LabelBean>();
		try
		{
			docNames = patientDao.getDoctorNames(dispCode,splstGroup);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return docNames;
	}
	
	public List<DrugsVO> getPharmDrugDtls(PatientVO patientVO)
	{
		return patientDao.getPharmDrugDtls(patientVO);
	}

	@Override
	public String getLeaveSequence(String string) {
		String seqId = patientDao.getLeaveSequence(string);
		return seqId;
	}

	public List<LabelBean> getDistributer(DrugsVO drg )
	{
		return patientDao.getDistributer(drg);
	}
	public List<LabelBean> getAvailabeDrugs(DrugsVO drg )
	{
		return patientDao.getAvailabeDrugs(drg);
	}
	
	public String savePharmaDrugs(PatientVO patientVO) 
	{
		return patientDao.savePharmaDrugs(patientVO);
	}
	
	public DrugsVO  getOldDrugStatus(PatientVO drg )
	{
		return patientDao.getOldDrugStatus(drg);
	}
	public PreauthVO getPatientFullDtlsPharma(String patientId)
	{
		return patientDao.getPatientFullDtlsPharma(patientId);
	}
	public String getPatientContactNo(String patientId)

	{
		return patientDao.getPatientContactNo(patientId);
	}
	@Override
	public List<LabelBean> getwellness(String dispId)
	{
		return patientDao.getwellness(dispId);
	}
	@Override
	public List<LabelBean> getwellness()
	{
		return patientDao.getwellness();
	}
	@Override
	public String getinvestcheck(String patientId, String investValue) {
		String flag="Y";
		try{
			flag=patientDao.getinvestcheck(patientId,investValue);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public List<LabelBean> getRelationsNew() {
		List<LabelBean> relationsList=null;
		try
		{
			relationsList=patientDao.getRelationsNew();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getRelations() in PatientServiceImpl class."+e.getMessage());
		}
		return relationsList;
	}
	@Override
	public String regPatient(PatientVO patVo) {
		return patientDao.regPatient(patVo);
	}
	@Override
	public String getPckNames(String ayushID) {
		return patientDao.getPckNames(ayushID);
	}
	@Override
	public String getPckNamesNew(String dosage) {
		return patientDao.getPckNamesNew(dosage);
	}
	@Override
	public String getTherapyOrAyush(String patientId) {
		return patientDao.getTherapyOrAyush(patientId);
	}
	@Override
	public String regPatientPck(PatientVO patVo) {
		return patientDao.regPatientPck(patVo);
	}
	@Override
	public String saveAttach(PatientVO patVo) {
		return patientDao.saveAttach(patVo);
	}
	@Override
	public List<LabelBean> getAisAttach(String patientId) {
		return patientDao.getAisAttach(patientId);
	}
	@Override
	public String getTherapyOrAyushNew(String patientId) {
		return patientDao.getTherapyOrAyushNew(patientId);
	}
	@Override
	public int getAyush(String ayushID) {
		int i=0;
		try {
			i = patientDao.getAyush(ayushID);
		}
		catch (Exception e) {
			e.printStackTrace();
//			GLOGGER.error("Exception Occurred in registerPatient() in PatientServiceImpl class."+e.getMessage());
		}
		return i;
	}
	@Override
	public List<LabelBean> getAisNames(String ServiceType, String typeActive) {
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getAisNames(ServiceType,typeActive);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugMftrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	
	//sowmya added for get legislative names 
	@Override
	public List<LabelBean> getLegislativeNames(String serviceType) {
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getLegislativeNames(serviceType);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugMftrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	@Override
	public PatientVO getLhsMlaConstituencies(){
		
		PatientVO mlaConstituenciesList=null;
		try {
			mlaConstituenciesList=patientDao.getLhsMlaConstituencies();
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getLhsMlaConstituencies() in PatientServiceImpl class."+e.getMessage());
		}
		return mlaConstituenciesList;
	}
	@Override
	public PatientVO getLhsMlcConstituencies(){
			
			PatientVO mlcConstituenciesList=null;
			try {
				mlcConstituenciesList=patientDao.getLhsMlcConstituencies();
			} 
			catch (Exception e) {
				GLOGGER.error("Exception Occurred in getLhsMlcConstituencies() in PatientServiceImpl class."+e.getMessage());
			}
			return mlcConstituenciesList;
		}
	@Override
	public PatientVO getLhsDistrictsList(String constituencyId){
		
		PatientVO lhsDistrictsList=null;
		try {
			lhsDistrictsList=patientDao.getLhsDistrictsList(constituencyId);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getLhsDistrictsList() in PatientServiceImpl class."+e.getMessage());
		}
		return lhsDistrictsList;
	}
	
	@Override
	public String updateLhsMemberApproveOrReject(HashMap<String, String> searchMap){
		
		String result=null;
		try {
			result=patientDao.updateLhsMemberApproveOrReject(searchMap);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in updateLhsMemberApproveOrReject() in PatientServiceImpl class."+e.getMessage());
		}
		return result;
	}
	
	//second level rejection
	@Override
	public String updateLhsCheckerRejection(HashMap<String, String> searchMap){
		
		String result=null;
		try {
			result=patientDao.updateLhsCheckerRejection(searchMap);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in updateLhsMemberApproveOrReject() in PatientServiceImpl class."+e.getMessage());
		}
		return result;
	}
	
	@Override
	public String updateLhsMemberRevert(HashMap<String, String> searchMap){
		
		String result=null;
		try {
			result=patientDao.updateLhsMemberRevert(searchMap);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in updateLhsMemberRevert() in PatientServiceImpl class."+e.getMessage());
		}
		return result;
	}
	
	@Override
	public String getMlaConstituencyId(String constituencyName){

		String result=null;
		try {
			result=patientDao.getMlaConstituencyId(constituencyName);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getMlaConstituencyId() in PatientServiceImpl class."+e.getMessage());
		}
		return result;
	}
	
	@Override
	public PatientVO getAllDistrictList(){
		
		PatientVO allDistrictList=null;
		try {
			allDistrictList=patientDao.getAllDistrictList();
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getAllDistrictList() in PatientServiceImpl class."+e.getMessage());
		}
		return allDistrictList;
	}
	//discharged patients list
		@Override
		public PatientVO getRegistredLhsMembersList(HashMap<String, String> searchMap) {
			PatientVO registredLhsMembersList=null;
			try {
				registredLhsMembersList=patientDao.getRegistredLhsMembersList(searchMap);
			} 
			catch (Exception e) {
				GLOGGER.error("Exception Occurred in registredLhsMembersList() in PatientServiceImpl class."+e.getMessage());
			}
			return registredLhsMembersList;
		}
		
		@Override
		public String getLhsDistrict(String distId){
			
			String lhsDistrict=null;
			try {
				lhsDistrict=patientDao.getLhsDistrict(distId);
			} 
			catch (Exception e) {
				GLOGGER.error("Exception Occurred in getAllDistrictList() in PatientServiceImpl class."+e.getMessage());
			}
			return lhsDistrict;
		}
		
		@Override
		public String updateLhsMemberSignedDocument(PatientVO patientVO){
			
			String result=null;
			try {
				result=patientDao.updateLhsMemberSignedDocument(patientVO);
			} 
			catch (Exception e) {
				GLOGGER.error("Exception Occurred in updateLhsMemberSignedDocument() in PatientServiceImpl class."+e.getMessage());
		
			}
			return result;
		}
		
		@Override
		public PatientVO getRegistredLhsDepenentList(HashMap<String, String> searchMap) {
			PatientVO registredLhsDepenentList=null;
			try {
				registredLhsDepenentList=patientDao.getRegistredLhsDepenentList(searchMap);
			} 
			catch (Exception e) {
				GLOGGER.error("Exception Occurred in registredLhsMembersList() in PatientServiceImpl class."+e.getMessage());
			}
			return registredLhsDepenentList;
		}
	
		@Override
		public String convertPDFToBase64(String filePath) throws Exception {
		    File file = new File(filePath);
	        FileInputStream fileInputStream = new FileInputStream(file);
	        byte[] pdfBytes = new byte[(int) file.length()];
	        fileInputStream.read(pdfBytes);
	        fileInputStream.close();
	        return DatatypeConverter.printBase64Binary(pdfBytes);
	    }
		
		@Override
		public String convertImageToBase64(String filePath) throws Exception {
			File file = new File(filePath);
	        if (!file.exists()) {
	            throw new IllegalArgumentException("File not found: " + filePath);
	        }
	        try {
	            Path path = Paths.get(filePath);
	            byte[] imageBytes = Files.readAllBytes(path);
	            return DatatypeConverter.printBase64Binary(imageBytes);
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	
	@Override
	public List<LabelBean> getAisDepNames(String cardNo) {
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getAisDepNames(cardNo);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugMftrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	@Override
	public List<LabelBean> getdrugs(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo,String userName) {
		// TODO Auto-generated method stub
		return patientDao.getdrugs(fromdate,todate,dispname,startIndex,maxResults,pageNo,userName);
	}
	@Override
	public List<LabelBean> getDrugsDetailed(String fromdate, String todate,
			String dispname, String drugId, String drugType, String caseId,
			int startIndex, int maxResults, int pageNo,String userName) {
		// TODO Auto-generated method stub
		return patientDao.getDrugsDetailed(fromdate,todate,dispname,drugId,drugType,caseId,startIndex,maxResults,pageNo,userName);
	}
	@Override
	public PatientVO getRegisteredPatientsNims(HashMap<String, String> searchMap) {

		PatientVO registeredPatientsList=null;
		try {
			registeredPatientsList=patientDao.getRegisteredPatientsNims(searchMap);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getRegisteredPatients() in PatientServiceImpl class."+e.getMessage());
		}
		return registeredPatientsList;
	
	}
	@Override
	public String saveMedicalCaseDetails(String patientId, String drugId,String userRole) {
		return patientDao.getTherapyOrAyushNew(patientId,drugId,userRole);
	}
	@Override
	public EhfAisDrugs getPatientDetailsAis(String patientId) {
		EhfAisDrugs asritPatient=null;
		try 
		{
			asritPatient=(EhfAisDrugs)patientDao.getPatientDetailsAis(patientId);
		} 
		catch (Exception e) 
		{
			GLOGGER.error("Exception Occurred in getPatientDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return asritPatient;
	}
	@Override
	public String saveDocDrugsNew(PatientVO patientVO) {
		return patientDao.saveDocDrugsNew(patientVO);
	}
	@Override
	public String savePharmaDrugsNims(PatientVO patientVO) {
		return patientDao.savePharmaDrugsNims(patientVO);
	}
	
	@Override
	public List<LabelBean> getAttachList(String patientId) {
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getAttachList(patientId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugMftrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	@Override
	public String rejectPatient(PatientVO patVo) {
		// TODO Auto-generated method stub
		return patientDao.rejectPatient(patVo);
	}
	@Override
	public String getpartialFlg(String patientId) {
		// TODO Auto-generated method stub
		String flag="";
		try{
			flag=patientDao.getpartialFlg(patientId);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public String getPatientDetailsAisCnt(String contactNum) {
		// TODO Auto-generated method stub
		return patientDao.getPatientDetailsAisCnt(contactNum);
	}
	@Override
	public List<LabelBean> getAttachListPr(String patientId) {
		List<LabelBean> details=null;
		try
		{
			details = patientDao.getAttachListPr(patientId);
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDrugMftrList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return details;
	}
	@Override
	public String getsaveNewDrug(String string,String drugValues,String lstrUserId) {
		// TODO Auto-generated method stub
		return patientDao.getsaveNewDrug(string,drugValues,lstrUserId);
	}
	@Override
	public List<DrugsVO> getPharmDrugDtlsPick(PatientVO patientVO) {
		return patientDao.getPharmDrugDtlsPick(patientVO);
	}
	@Override
	public String savePharmaDrugsPick(PatientVO patientVO) {
		// TODO Auto-generated method stub
		return patientDao.savePharmaDrugsPick(patientVO);
	}
	@Override
	public EhfAisDrugs getPatientDetailsAisDrugs(String patientId) {
		// TODO Auto-generated method stub
		EhfAisDrugs asritPatient=null;
		try 
		{
			asritPatient=(EhfAisDrugs)patientDao.getPatientDetailsAisDrugs(patientId);
		} 
		catch (Exception e) 
		{
			GLOGGER.error("Exception Occurred in getPatientDetails() in PatientServiceImpl class."+e.getMessage());
		}
		return asritPatient;
	}
	@Override
	public List<LabelBean> getDrugTypeListAis() {

		List<LabelBean> dispDrugTypeList=new ArrayList<LabelBean>();
		try
		{
			dispDrugTypeList = patientDao.getDrugTypeListAis();
		}
		catch (Exception e) {
//			GLOGGER.error("Exception Occurred in getDispDrugList() in PatientServiceImpl class."+e.getMessage());
		e.printStackTrace();
		}
		
		return dispDrugTypeList;
	
	}
	public boolean updateTransferDrugs(HashMap drugparam)
	{
		boolean flag=false;
		try{
			flag=patientDao.updateTransferDrugs(drugparam);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public List<LabelBean> getDispDrugListSubStore(HashMap drugParam) {
		List<LabelBean> dispDrugList=new ArrayList<LabelBean>();
		try
		{	
			dispDrugList =patientDao.getDispDrugListSubStore(drugParam);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dispDrugList;
	}
	public List<LabelBean> getDispList(String dispId) {
		List<LabelBean> dispList=new ArrayList<LabelBean>();
		try
		{	
			dispList =patientDao.getDispList(dispId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dispList;
	}
	@Override
	public List<DrugsVO> getDrugsTransferredList(HashMap drugParam) {
		List<DrugsVO> drugList=new ArrayList<DrugsVO>();
		try
		{	
			drugList =patientDao.getDrugsTransferredList(drugParam);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return drugList;

	}
	public List<DrugsVO> getDrugsIntiatedDtlsList(HashMap drugParam){
		List<DrugsVO> drugList=new ArrayList<DrugsVO>();
		try
		{	
			drugList =patientDao.getDrugsIntiatedDtlsList(drugParam);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return drugList;

	}
	@Override
	public boolean updateDrugsToWC(HashMap drugParam) {
		boolean flag=false;
		try{
			flag =patientDao.updateDrugsToWC(drugParam);
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}
	public boolean updateDrugsInventoryNew(List<LabelBean> drugDtls)
	{
		boolean flag=false;
		try{
			flag=patientDao.updateDrugsInventoryNew(drugDtls);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public List<LabelBean> getled(String pStrFromDate , String pStrtoDate,String center, int lStrStartIndex, int lStrRowsperpage, int pageNo,String drugName) {
		// TODO Auto-generated method stub
		return patientDao.getled(pStrFromDate,pStrtoDate, center,lStrStartIndex,lStrRowsperpage,pageNo,drugName);
	}
	@Override
	public List<LabelBean> getLedgerDrugsDetailed(String fromdate, String todate,String dispname, String drugId,String drugType,String caseId,int page, int page1,int pageNo,String userName,String drugName) {

		List<LabelBean> drugDetailedReportList=null;
		try
		{
			drugDetailedReportList=patientDao.getLedgerDrugsDetailed(fromdate,todate,dispname,drugId,drugType,caseId, page,  page1, pageNo,userName,drugName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDrugsDetailed() in ReportServiceImpl class."+e.getMessage());
		}
		return drugDetailedReportList;
	}
	@Override
	public List<LabelBean> getexp(String pStrFromDate, String pStrtoDate, String center,
			int lStrStartIndex, int lStrRowsperpage, int pageNo) {
		// TODO Auto-generated method stub
		return patientDao.getexp(pStrFromDate,pStrtoDate, center,lStrStartIndex,lStrRowsperpage,pageNo);
	}
	@Override
	public List<LabelBean> checkUsrMappedToWellness(String lStrUserId) {
		List<LabelBean> Flag=patientDao.checkUsrMappedToWellness(lStrUserId);
		// TODO Auto-generated method stub
		return Flag;
	}
	@Override
	public List<LabelBean> getexpiredDrugs(Map dataMap) {
		List<LabelBean> expDrugsList = new ArrayList<>();
		try{
			expDrugsList =patientDao.getexpiredDrugs(dataMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return expDrugsList;
	}
	@Override
	public boolean submitRequestForRTV(HashMap drugparam) {
		boolean flag=false;
		try{
			flag=patientDao.submitRequestForRTV(drugparam);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public List<LabelBean> getDrugsInvoiceList(Map dataMap) {
		List<LabelBean> drugsInvoiceList = new ArrayList<>();
		try{
			drugsInvoiceList =patientDao.getDrugsInvoiceList(dataMap);
		}catch(Exception e){
			e.printStackTrace();
		}
		return drugsInvoiceList;
	}
	@Override
	public boolean submitCreditNoteRequest(HashMap drugparam) {
		boolean flag=false;
		try{
			flag=patientDao.submitCreditNoteRequest(drugparam);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public boolean submitLowStockList(HashMap drugparam) {
		boolean flag=false;
		try{
			flag=patientDao.submitLowStockList(drugparam);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public boolean moveLowStockDataFromWCItoAudit(Map indNumList) {
		boolean flag=false;
		try{
			flag=patientDao.moveLowStockDataFromWCItoAudit(indNumList);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public List<LabelBean> returnVendorList(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo,
			String dstrName) {
		List<LabelBean> returnVendorList=null;
			try
			{
				returnVendorList=patientDao.returnVendorList(fromdate,todate,dispname,startIndex,maxResults,pageNo,dstrName);
			}
			catch(Exception e)
			{
				GLOGGER.error("Exception Occurred in getDrugsDetailed() in ReportServiceImpl class."+e.getMessage());
			}
			return returnVendorList;
	}
	@Override
	public String getDistrName(String dstrName) {
		String distributorName=null;
		try
		{
			distributorName=patientDao.getDistrName(dstrName);
		} 
		catch (Exception e)
		{
			GLOGGER.error("Exception Occurred in getDistrName() in PatientServiceImpl class."+e.getMessage());
		}
		return distributorName;
	}
	@Override
	public List<LabelBean> creditNoteList(String fromdate, String todate,
			String dispname, int startIndex, int maxResults, int pageNo,
			String dstrName) {
		// TODO Auto-generated method stub
		List<LabelBean> creditNoteList=null;
		try
		{
			creditNoteList=patientDao.creditNoteList(fromdate,todate,dispname,startIndex,maxResults,pageNo,dstrName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDrugsDetailed() in ReportServiceImpl class."+e.getMessage());
		}
		return creditNoteList;
	}
	
	@Override
	public List<LabelBean> indentList(String dispname, int startIndex,
			int maxResults, int pageNo, String drugName) {
		// TODO Auto-generated method stub
		List<LabelBean> indentList=null;
		try
		{
			indentList=patientDao.indentList(dispname,startIndex,maxResults,pageNo,drugName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in indentList() in ReportServiceImpl class."+e.getMessage());
		}
		return indentList;
	}
	@Override
	public List<LabelBean> indentListNew(String dispname, int startIndex,
			int maxResults, int pageNo, String drugName) {
		// TODO Auto-generated method stub
		List<LabelBean> indentList=null;
		try
		{
			indentList=patientDao.indentListNew(dispname,startIndex,maxResults,pageNo,drugName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in indentList() in ReportServiceImpl class."+e.getMessage());
		}
		return indentList;
	}
	
	@Override
	public boolean saveManfctDrugDetailsNew(HashMap paramMap) {
		boolean flag=false;
		try{
			flag=patientDao.saveManfctDrugDetailsNew(paramMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public boolean saveDistrDrugDetailsNew(HashMap paramMap) {
		boolean flag=false;
		try{
			flag=patientDao.saveDistrDrugDetailsNew(paramMap);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return flag;
	}
	@Override
	public String getDrugName(String dispDrugID) {
		// TODO Auto-generated method stub
		String drugName = null;
		try{
		drugName=patientDao.getDrugName(dispDrugID);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return drugName;
	}
	@Override
	public List<LabelBean> getpat(String pStrFromDate, String pStrtoDate, String center, int lStrStartIndex, int lStrRowsperpage,
			int pageNo) {
		// TODO Auto-generated method stub
		return patientDao.getpat(pStrFromDate,pStrtoDate, center,lStrStartIndex,lStrRowsperpage,pageNo);
	}
	@Override
	public String sendMobileOtp(String patientId, String mobileNo) {
		// TODO Auto-generated method stub
		String result = null;
		try{
			result=patientDao.sendMobileOtp(patientId,mobileNo);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return result;
	}
	public String verifyMobileOTP(String patientId,String otp){
		return patientDao.verifyMobileOTP(patientId, otp);
	}
	@Override
	public String exemptOtp(String patientId) {
		// TODO Auto-generated method stub
		String result = null;
		try{
			result=patientDao.exemptOtp(patientId);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		return result;
	}		
	@Override
	public List<LabelBean> lowStockList(String drugType,String dispid,String drugName) {
		// TODO Auto-generated method stub
		List<LabelBean> indentList=null;
		try
		{
			indentList=patientDao.lowStockList(drugType,dispid,drugName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in indentList() in ReportServiceImpl class."+e.getMessage());
		}
		return indentList;
	}
	@Override
	public List<LabelBean> lowStockPO(String drugType,String dispid,String drugName) {
		List<LabelBean> indentList=null;
		try
		{
			indentList=patientDao.lowStockPO(drugType,dispid,drugName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in indentList() in ReportServiceImpl class."+e.getMessage());
		}
		return indentList;
	}
	public List<PatientVO> viewExemptOtpList(String userId,String patientId){
		return patientDao.viewExemptOtpList(userId, patientId);
	}
	public String exemptOtpApproval(String patientId,String userId,String remarks){
		return patientDao.exemptOtpApproval(patientId, userId, remarks);
	}
	@Override
	public List<LabelBean> getdrugType() {
		List<LabelBean> list=null;
		try
		{
			list=patientDao.getdrugType();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getdrugType() in ReportServiceImpl class."+e.getMessage());
		}
		return list;
	}
	@Override
	public List<LabelBean> getwcName() {
		List<LabelBean> list=null;
		try
		{
			list=patientDao.getwcName();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getwcName() in ReportServiceImpl class."+e.getMessage());
		}
		return list;
	}
	@Override
	public List<LabelBean> getdistributorList() {
		List<LabelBean> list=null;
		try
		{
			list=patientDao.getdistributorList();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getdistributorList() in ReportServiceImpl class."+e.getMessage());
	}
		return list;
	}
	
	//@Override
	public List<LabelBean> getOutStandingdrugs(String drugType,String drugName,String wc,String selectType,String drugDrill) {
		List<LabelBean> list=null;
		try
		{
			list=patientDao.getOutStandingdrugs(drugType,drugName,wc,selectType,drugDrill);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getwcName() in ReportServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<LabelBean> getDrugsDistDetailed(String fromdate, String todate,String dispname, String drugId,String drugType,String caseId,int page, int page1,int pageNo,String userName) {

		List<LabelBean> drugDetailedReportList=null;
		try
		{
			drugDetailedReportList=patientDao.getDrugsDistDetailed(fromdate,todate,dispname,drugId,drugType,caseId, page,  page1, pageNo,userName);
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getDrugsDetailed() in ReportServiceImpl class."+e.getMessage());
		}
		return drugDetailedReportList;
	}
	@Override
	public List<LabelBean> getdrugsDistRpt(String pStrFromDate , String pStrtoDate,String center,int lStrStartIndex ,int lStrRowsperpage,int pageNo,String userName) {
		return patientDao.getdrugsDistRpt(pStrFromDate,pStrtoDate, center,lStrStartIndex,lStrRowsperpage,pageNo,userName);
	}
	@Override
	public List<LabelBean> getrequiredusers(String dispname) {
		// TODO Auto-generated method stub
		return patientDao.getrequiredusers(dispname);
	}
	@Override
	public List<LabelBean> lowStockwellness(String dispname) {
		// TODO Auto-generated method stub
		return patientDao.lowStockwellness(dispname);
	}
	@Override
		public List<LabelBean> getIndentNoList(String dispname, String ajaxCal){
	        List<LabelBean> ResultMap = null;
	        try {
	            ResultMap = 
	            		patientDao.getIndentNoList(dispname, ajaxCal);
	        } catch (Exception ex) {
	        	GLOGGER.error(" Exception in getHospitalsReport method of PatientServiceImpl " + 
	                         ex.getMessage());
	        }
	        return ResultMap;
	    }
	@Override
	public List<LabelBean> getIndentNoListRpt(String dispname, String ajaxCal){
        List<LabelBean> ResultMap = null;
        try {
            ResultMap = 
            		patientDao.getIndentNoListRpt(dispname, ajaxCal);
        } catch (Exception ex) {
        	GLOGGER.error(" Exception in getHospitalsReport method of PatientServiceImpl " + 
                         ex.getMessage());
        }
        return ResultMap;
    }
	@Override
	public List<LabelBean> getIndentNoListRpt1(String dispname, String ajaxCal){
        List<LabelBean> ResultMap = null;
        try {
            ResultMap = 
            		patientDao.getIndentNoListRpt1(dispname, ajaxCal);
        } catch (Exception ex) {
        	GLOGGER.error(" Exception in getHospitalsReport method of PatientServiceImpl " + 
                         ex.getMessage());
        }
        return ResultMap;
    }
	@Override
	public List<LabelBean> getindentPO(String wc, String indNo,String distributorId) {
		return patientDao.getindentPO(wc, indNo, distributorId);
	}
	
	@Override
	public List<LabelBean> getStoreIndentNoList(String dispname, String ajaxCal) {
		return patientDao.getStoreIndentNoList(dispname, ajaxCal);
	}
	@Override
	public List<LabelBean> getExpdtRpt(String dispname, String ajaxCal) {
		return patientDao.getExpdtRpt(dispname, ajaxCal);
	}
	@Override
	public List<LabelBean> getstoreList(String wc, String indNo) {
		return patientDao.getstoreList(wc, indNo);
	}
	@Override
	public List<LabelBean> getindentPORpt(String wc, String indNo, String distributor, String fromDate, String destDate) {
		return patientDao.getindentPORpt(wc, indNo, distributor, fromDate, destDate  );
	}
	@Override
	public List<LabelBean> getOnclickPORpt(String poNo) {
		return patientDao.getOnclickPORpt(poNo);
	}
	@Override
	public List<LabelBean> getOnclickAvlRpt(String dispname1,String drugCode) {
		return patientDao.getOnclickAvlRpt(dispname1,drugCode);
	}
	@Override
	public List<LabelBean> getOnclickMnfPORpt(String mnfPoNo) {
		return patientDao.getOnclickMnfPORpt(mnfPoNo);
	}
	@Override
	public String submitIndented(HashMap drugparam){
		return patientDao.submitIndented(drugparam);
	}
	@Override
	public String submitStoreKeeper(HashMap drugparam){
		return patientDao.submitStoreKeeper(drugparam);
	}
	@Override
	public List<LabelBean> getPoStatusList(String dispname1,String poDate,String dispId) {
		
		return patientDao.getPoStatusList(dispname1,poDate,dispId);
	}
	@Override
	public List<LabelBean> getOnclickPORpt(String poNo,String status) {
		return patientDao.getOnclickPORpt(poNo,status);
	}
	@Override
	public List<LabelBean> getpoNotRsv(String dispname1) {
		
		return patientDao.getpoNotRsv(dispname1);
	}
	@Override
	public String getpoNotRsvCacl(String drugList) {
		
		return patientDao.getpoNotRsvCacl(drugList);
	}
	@Override
	public List<LabelBean> getDistrGen(String drugList) {
		 List<LabelBean> result = null;
		try{
			result=patientDao.getDistrGen(drugList);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return result;
	}
	@Override
	public List<LabelBean> getDistrGenAll(String dispname,List<LabelBean> result) {
		 List<LabelBean> result1 = null;
		try{
			result1=patientDao.getDistrGenAll(dispname,result);
			
		}
		catch(Exception e)
		{
//			GLOGGER.error("Exception Occurred in updateDrugsInventory() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
			
		}
		return result1;
	}
	@Override
    public List<PreauthVO> getConsentData(String wc, String pStrCaseId) throws Exception{
        PreauthVO preauthVO=new PreauthVO();
        List<PreauthVO> preauthList=null;
        try{
            preauthList=patientDao.getConsentData(wc, pStrCaseId);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return preauthList;
    }
		
	@Override
	public List<LabelBean> getdistributorsList() {
		List<LabelBean> list=null;
		try
		{
			list=patientDao.getdistributorsList();
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception Occurred in getdistributorsList() in ReportServiceImpl class."+e.getMessage());
	}
		return list;
	}
	
	@Override
	public List<LabelBean> getdrugsInve(String pStrFromDate , String pStrtoDate,String center,String drugname, int lStrStartIndex ,int lStrRowsperpage,int pageNo, String distributorId)
	{
		return patientDao.getdrugreportsinve(pStrFromDate,pStrtoDate, center,drugname,lStrStartIndex,lStrRowsperpage,pageNo, distributorId);
	}
	
	@Override
	public Map<String, Object> updateDrugsInve(HashMap<String,Object> paramMap){
		return patientDao.updateDrugsInve(paramMap);
	}


	@Override
	public Map<String, Object> getDrugInventoryMaster(){
		return patientDao.getDrugInventoryMaster();
	}
	
	//added by sowmya
	@Override
	public PatientVO saveLHSEnrollmentDetails(PatientVO patientVO) {
		//String saveDetails = "";
		PatientVO saveDetails = new PatientVO();
		try {
			saveDetails=patientDao.saveLHSEnrollmentDetails(patientVO);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getRegisteredPatients() in PatientServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
		return saveDetails;
	}
	//pravalika
	  @Override
	    public List<LabelBean> getDrugDtls(String cardNo)throws Exception{
			List<LabelBean> lResLst=new ArrayList<>();
			try{
				lResLst = patientDao.getDrugDtls(cardNo);
			}catch(Exception e){
				e.printStackTrace();
			}
			return lResLst;
		}
		
		@Override
		public List<EhfPatientTests> getInvestigationDetails(String patientId)
		{
			List<EhfPatientTests> EhfPatientTests = patientDao.getInvestigationDetails(patientId);
			return EhfPatientTests;
		}
			@Override
		public List<LabelBean> getPrintDrugDtls(String caseId)
		{
			List<LabelBean> lResLst=new ArrayList<>();
			try{
				lResLst = patientDao.getPrintDrugDtls(caseId);
			}catch(Exception e){
				e.printStackTrace();
			}
			return lResLst;
			
		}
		
			@Override
	public PatientVO updateLhsMemberDetails(PatientVO patientVO){
				PatientVO saveDetails = new PatientVO();
				try {
					saveDetails=patientDao.updateLhsMemberDetails(patientVO);
				} 
				catch (Exception e) {
					GLOGGER.error("Exception Occurred in updateLhsMemberDetails() in PatientServiceImpl class."+e.getMessage());
					e.printStackTrace();
				}
				return saveDetails;
	}
	//Chandana - 8251 - New method for excel drug upload month wise details
	public String updDrugMonthlyDtls(HashMap hParamMap){
    	//HashMap<String, Object> lParamMap = new HashMap<>();
    	String ResLst="";
    	try{
    		ResLst = patientDao.updDrugMonthlyDtls(hParamMap);
    	}catch(Exception e){
    		GLOGGER.error("Error in Generating updDrugMonthlyDtls() list of ClaimsFlowPaymentServiceImpl "+ e.getMessage());
    		e.printStackTrace();
    	}
    	return ResLst;
    }
		//sai krishna:CR#8134API Integration.
	@Override
	public String tdEhfRegistration(String patientNo,String url,String facilityId){
			String saveDetails = "";
	        try{
	        	saveDetails = patientDao.tdEhfRegistration(patientNo,url,facilityId);
	      
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saveDetails;  
	}
	@Override
	public PatientVO getSavedIPLab(String patientId){
		
		PatientVO labList=null;
		try {
			labList=patientDao.getSavedIPLab(patientId);
		} 
		catch (Exception e) {
			GLOGGER.error("Exception Occurred in getRegisteredPatients() in PatientServiceImpl class."+e.getMessage());
		}
		return labList;
	}
	@Override
	public String getCardDtlsForAbhaSearch(String cardNo){//Chandana - 8443 - New method for getting the card details when the card type is ABHA
		String ResLst="";
    	try{
    		ResLst = patientDao.getCardDtlsForAbhaSearch(cardNo);
    	}catch(Exception e){
    		GLOGGER.error("Error in Generating updDrugMonthlyDtls() list of ClaimsFlowPaymentServiceImpl "+ e.getMessage());
    		e.printStackTrace();
    	}
    	return ResLst;
	}
		//Tejasri- CR-8812 Wellness Centers - Attendance dashboard to Wellness Centre Admin
	@Override
	public List<LabelBean> wellnessCentreList(String dispId)
	{
		List<LabelBean> lResLst=new ArrayList<>();
		try{
			lResLst = patientDao.wellnessCentreList(dispId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lResLst;
		
	}
	@Override
	public List<LabelBean> EmployeeList(String dsgId,String reqType,String fromDate,String toDate)
	{
		List<LabelBean> lResLst=new ArrayList<>();
		try{
			lResLst = patientDao.EmployeeList(dsgId,reqType,fromDate,toDate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lResLst;
		
	}
	@Override
	public List<LabelBean> SearchList(String reqType,String fromDate,String dsgId,String dispname)
	{
		List<LabelBean> lResLst=new ArrayList<>();
		try{
			lResLst = patientDao.SearchList(reqType,fromDate,dsgId,dispname);
		}catch(Exception e){
			e.printStackTrace();
		}
		return lResLst;
		
	}
		//pravalika
	@Override
	public List<LabelBean> getOnclickPORpt1(String poNo,String status,String lStrUserId,String drugId) {
		return patientDao.getOnclickPORpt1(poNo,status,lStrUserId,drugId);
	}
	@Override
	public String saveInventoryDetailsVendor(HashMap vendordrug,String lstrUserId) {
		
		return patientDao.saveInventoryDetailsVendor(vendordrug,lstrUserId);
	}
	@Override
	public List<LabelBean> getStoreDrugList(String dispId) {
		
		return patientDao.getStoreDrugList(dispId);
	}
	@Override
	public List<LabelBean> getWCList() {
		
		return patientDao.getWCList();
	}
	@Override
	public List<LabelBean> getinvoiceNoList(String poId,String status) {
		
		return patientDao.getinvoiceNoList(poId,status);
	}
	
	@Override
	public List<LabelBean> getPoItemsForPdf(String poNo,String itemIds) {
		return patientDao.getPoItemsForPdf(poNo,itemIds);
	}
	@Override
	public String updateVendorPdfPath(String pdfFileName,String filePath,String poNo,String itemId) {
		return patientDao.updateVendorPdfPath(pdfFileName,filePath,poNo,itemId);
	}
	@Override
	public List<LabelBean> viewVendorDocument(String docId) {
		
		return patientDao.viewVendorDocument(docId);
	}
	@Override
	public String uploadVendorDocument(HashMap fileUpload,String lStrUserId){
		return patientDao.uploadVendorDocument(fileUpload,lStrUserId);
	}
	@Override
	public List<LabelBean> indentOnclick(String invoiceNo,String status,String dispId,String poNo) {
		return patientDao.indentOnclick(invoiceNo,status,dispId,poNo);
	}
	@Override
	public List<LabelBean> getIndentStatus(String dispId,String lStrUserId,String status){
		return patientDao.getIndentStatus(dispId,lStrUserId,status);
	}
	@Override
	public List<LabelBean> getPoListforUpload(String lStrUserId,String status){
		return patientDao.getPoListforUpload(lStrUserId,status);
	}
	@Override
	public List<LabelBean> getUploadList(String lStrUserId,String status){
		return patientDao.getUploadList(lStrUserId,status);
	}
	@Override
	public List<LabelBean> getGenPdf(String poNo, String status,
			int lStrStartIndex, int lStrRowsperpage, int pageNo) {

		return patientDao.getGenPdf(poNo,status,lStrStartIndex,lStrRowsperpage,pageNo);
	}
	@Override
	public List<LabelBean> getIntendPdf(String dispname1,String poDate,
			int lStrStartIndex, int lStrRowsperpage, int pageNo) {
		
		return patientDao.getIntendPdf(dispname1,poDate,lStrStartIndex,lStrRowsperpage,pageNo);
	}
	 public List<LabelBean> getConsentData1(String wc, String pStrCaseId) throws Exception{
		 LabelBean preauthVO=new LabelBean();
	        List<LabelBean> preauthList=null;
	        try{
	            preauthList=patientDao.getConsentData1(wc, pStrCaseId);
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	        return preauthList;
	    }
	 
	 @Override
	 public boolean addNewMnfNDist1(HashMap storedrugList,String lstrUserId){
			return patientDao.addNewMnfNDist1(storedrugList,lstrUserId);
		}
	 @Override
		public List<LabelBean> getDesignationDtls(String lStrUserId) {
			List<LabelBean> list=	patientDao.getDesignationDtls(lStrUserId);
			return list;
		}
	 @Override
		public List<LabelBean> getDispIdDtls(String lStrUserId) {
			List<LabelBean> list=	patientDao.getDispIdDtls(lStrUserId);
			return list;
		}

		
	//end
}
