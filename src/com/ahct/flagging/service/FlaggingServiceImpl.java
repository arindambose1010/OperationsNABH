package com.ahct.flagging.service;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;

import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.dao.FlaggingDao;
import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.model.EhfAudit;
import com.ahct.model.EhfAuditId;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfmWorkflowStatus;
import com.ahct.model.EhfmWorkflowStatusId;
import com.ahct.preauth.service.PreauthServiceImpl;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class FlaggingServiceImpl implements FlaggingService
{
	FlaggingDao flaggingDao;
	String caseId;
	String flagId;
	String flagDocId;
	List<LabelBean> flagList=new ArrayList<LabelBean>();
	List<FlaggingVO> hospList=new ArrayList<FlaggingVO>();
	List<FlaggingVO> flaggingVOLst;
	

	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	static final Logger gLogger = Logger.getLogger(PreauthServiceImpl.class);
	private static final Logger logger = Logger.getLogger(PreauthServiceImpl.class);
	
	GenericDAO genericDao;
	public void setGenericDao(GenericDAO genericDao) {
        this.genericDao = genericDao;
    }
	
	public FlaggingDao getFlaggingDao() {
		return flaggingDao;
	}

	public void setFlaggingDao(FlaggingDao flaggingDao) {
		this.flaggingDao = flaggingDao;
	}
	/*
	 * Implementation
	 * to get districts
	 * for Interface service*/
	@Override
	public List<FlaggingVO> getHospList(String stateId,String distId,String hospType)
		{
			hospList=flaggingDao.getHospList(stateId,distId,hospType);
			return hospList;
		}
	
	/*
	 * Implementation 
	 * to save the 
	 * Flag Details*/
	@Override
	public String saveFlagDtls(FlaggingVO flaggingVO)
		{
			String msg=flaggingDao.saveFlagDtls(flaggingVO);
			return msg;
		}
	/*
	 *Implementation
	 *to get
	 *the districts */
	public List<FlaggingVO> getDistricts(String stateId)
		{
			List<FlaggingVO> distList=flaggingDao.getDistricts(stateId);
			return distList;
		}
	/*
	 * Implementation
	 * to get flags
	 * for Interface service*/
	@Override
	public List<LabelBean> getFlagList()
		{
			flagList=flaggingDao.getFlagList();
			return flagList;
		}

	/*
	 * Implementation
	 * to retrieve
	 * Flag Details*/
	@Override
	public List<FlaggingVO> getFlaggedCases(FlaggingVO flaggingVO)
		{
			flaggingVOLst=flaggingDao.getFlaggedCases(flaggingVO);
			return flaggingVOLst;
		}
	
	
	/*
	 * Implementation
	 * to get the
	 * FlagAttachments */
	@Override
	public List<FlaggingVO> getflagAttach(String caseId,String flagId,String flagDocId)
		{
		this.caseId=caseId;
		this.flagId=flagId;
		this.flagDocId=flagDocId;
		
		flaggingVOLst=flaggingDao.getflagAttach(caseId,flagId,flagDocId);
		return flaggingVOLst;
		}
	
	/*
	 * Implementation
	 * to get all
	 * Flagged Cases */
	@Override
	public List<FlaggingVO> getAllFlaggedCases(FlaggingVO flaggingVO)
		{
			flaggingVOLst=flaggingDao.getAllFlaggedCases(flaggingVO);
			return flaggingVOLst;
		}
	
	/*
	 * Function
	 * to get 
	 * Authority*/
	@Override
	public FlaggingVO checkAuthority(List<LabelBean> rolesList)
		{
			FlaggingVO flaggingVO=flaggingDao.checkAuthority(rolesList);
			return flaggingVO;
		}
	/*
	 * Function
	 * to get 
	 * Non Delagged Cases*/
	@Override
	public Number getNoOfFlaggedCases(String lStrEmpId)
		{
			Number flaggedCases=flaggingDao.getNoOfFlaggedCases(lStrEmpId);
			return flaggedCases;
		}
	/*
	 * Function
	 * to get 
	 * Flagged Cases for color*/
	@Override
	public FlaggingVO getFlaggedCasesForColor(String caseId)
		{
			FlaggingVO flaggingVO=flaggingDao.getFlaggedCasesForColor(caseId);
			return flaggingVO;
		}
	
	@Override
	public String getFlagDocId()
	{
		String id=flaggingDao.getFlagDocId();
		return id;
	}
	
	
	/*
	 * Function
	 * to check 
	 * Case is Flagged*/
	@Override
	public FlaggingVO checkCaseFlagged(String caseId)
		{
			FlaggingVO flaggingVO=flaggingDao.checkCaseFlagged(caseId);
			return flaggingVO;
		}
	
	/*
	 * Function to check the  
	 * authority for DC DM TL */
	@SuppressWarnings("unchecked")
	@Override
	public FlaggingVO checkDCTLDMAuthority(FlaggingVO flaggingVO)
		{
			FlaggingVO returnVo =new FlaggingVO();
			
			if(flaggingVO.getDcId()!=null || flaggingVO.getDmId()!=null ||
					flaggingVO.getTlId()!=null)
				{
					StringBuffer query=new StringBuffer();
					query.append( " select eht.hospId as hospId , eht.hospDist as hospDist " );
					query.append( " from EhfmHospTlDcMpg eht " );
					
					if(flaggingVO.getDcId()!=null && !"".equalsIgnoreCase(flaggingVO.getDcId()))
						query.append(" where eht.dcId = '"+flaggingVO.getDcId()+"' ");
					else if(flaggingVO.getDmId()!=null && !"".equalsIgnoreCase(flaggingVO.getDmId()))
						query.append(" where eht.dmId = '"+flaggingVO.getDmId()+"' ");
					else if(flaggingVO.getTlId()!=null && !"".equalsIgnoreCase(flaggingVO.getTlId()))
						query.append(" where eht.tlId = '"+flaggingVO.getTlId()+"' ");
						
					Map<String,Object> returnMap=new HashMap<String,Object>();
					String path="com.ahct.flagging.vo.FlaggingVO";
					returnMap=flaggingDao.executeNormalHQLQuery(query.toString(),path);
					if(returnMap!=null)
						{
							if(returnMap.get(path)!=null)
								returnVo.setFlaggingVOLst((List<FlaggingVO>)returnMap.get(path));
						}
					
					
				}
			
			return returnVo;
			
				
		}
	
	/*
	 * Function to get Case Details
	 */
	@Override
	@SuppressWarnings("unchecked")
	public FlaggingVO getCaseDtls(String caseId)
		{
			String hospId=null;
			FlaggingVO returnVO = new FlaggingVO(); 
			StringBuffer query = new StringBuffer();
			try
				{
					query.append( " select a.caseHospCode as hospId , a.schemeId as schemeId from EhfCase a " );
					query.append( " where a.caseId ='"+caseId+"' " );
					
					Map<String,Object> returnMap=new HashMap<String,Object>();
					String path="com.ahct.flagging.vo.FlaggingVO";
					returnMap=flaggingDao.executeNormalHQLQuery(query.toString(),path);
					if(returnMap!=null)
						{
							if(returnMap.get(path)!=null)
								{
									List<FlaggingVO> lst=(List<FlaggingVO>)returnMap.get(path);
									if(lst!=null)
										if(lst.size()>0)
											if(lst.get(0)!=null)
												{
													if(lst.get(0).getHospId()!=null)
														hospId = lst.get(0).getHospId();
													if(lst.get(0).getSchemeId()!=null)
														returnVO.setSchemeId(lst.get(0).getSchemeId());
												}	
								}
						}
					returnVO.setHospId(hospId);
				}
			catch(Exception e )
				{
					e.printStackTrace();
				}
			return returnVO;
		}
	
	/*
	 * Function to get auto cancelled Case Details by Satish Kola
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<LabelBean> getCancelledCases()
		{
		
		
		   List<LabelBean> allCanceledCases = new ArrayList<LabelBean>();
		   
		   List<LabelBean> validatePendingPreauth1 = validatePendingPreauth("CD10",42,"CD202"); //PTD Preauth Pending
		   if (!validatePendingPreauth1.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth1);
		   }
			
		   List<LabelBean> validatePendingPreauth2 = validatePendingPreauth("CD210",42,"CD202"); //PPD kept Pending
		   if (!validatePendingPreauth2.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth2);
		   }
			
		   List<LabelBean> validatePendingPreauth3 = validatePendingPreauth("CD208",42,"CD202"); //CEO Pending
		   if (!validatePendingPreauth3.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth3);
		   }
			
		   List<LabelBean> validatePendingPreauth4 = validatePendingPreauth("CD217",42,"CD202"); //EO Preauth Pending
		   if (!validatePendingPreauth4.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauth4);
		   }
		
			
		   List<LabelBean> validateSurgeryUpdatedCases = validateSurgeryUpdatedCases("preauthAprvDate","csSurgUpdDt",57); //Validate Surgery Updated before and  feb 01 16
		   if (!validateSurgeryUpdatedCases.isEmpty()) {
			   allCanceledCases.addAll(validateSurgeryUpdatedCases);
		   }
		   		   List<LabelBean> validateUnDischargedCasesLst = validateUnDischargedCases("preauthAprvDate","csSurgUpdDt",57); //Validate Surgery Updated before and  feb 01 16
		   if (!validateUnDischargedCasesLst.isEmpty()) {
			   allCanceledCases.addAll(validateUnDischargedCasesLst);
		   }
			/*validateUpdatedCases("csSurgUpdDt","csDisUpdDt",60); //Validate Discharge Updated*/
		
			//For revised SLA's after Feb 01 2016
		   List<LabelBean> validatePendingPreauthNew1 = validatePendingPreauthNew("CD10",27,"CD202");//PTD Preauth Pending
		   if (!validatePendingPreauthNew1.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew1);
		   }
			
		   List<LabelBean> validatePendingPreauthNew2 = validatePendingPreauthNew("CD210",27,"CD202");//PPD kept Pending
		   if (!validatePendingPreauthNew2.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew2);
		   }
			
		   List<LabelBean> validatePendingPreauthNew3 = validatePendingPreauthNew("CD208",27,"CD202");//CEO Pending
		   if (!validatePendingPreauthNew3.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew3);
		   }
			
		   List<LabelBean> validatePendingPreauthNew4 = validatePendingPreauthNew("CD217",27,"CD202");//EO Preauth Pending
		   if (!validatePendingPreauthNew4.isEmpty()) {
			   allCanceledCases.addAll(validatePendingPreauthNew4);
		   }
			
			//Claims
		   List<LabelBean> validateClaimPending1 = validateClaimPending("CD44",27,"CD202");	//CPD Pending
		   if (!validateClaimPending1.isEmpty()) {
			   allCanceledCases.addAll(validateClaimPending1);
		   }
		   
		   List<LabelBean> validateClaimPending2 = validateClaimPending("CD47",27,"CD202");	//CTD Pending
		   if (!validateClaimPending2.isEmpty()) {
			   allCanceledCases.addAll(validateClaimPending2);
		   }
		   
		   List<LabelBean> validateClaimPending3 = validateClaimPending("CD49",27,"CD202");	//CH Pending
		   if (!validateClaimPending3.isEmpty()) {
			   allCanceledCases.addAll(validateClaimPending3);
		   }
		   
		   List<LabelBean> validateClaimSubmitPending = validateClaimSubmitPending("CD21",57,"CD202"); 	//Discharge updated(claim submission)	
		   if (!validateClaimSubmitPending.isEmpty()) {
			   allCanceledCases.addAll(validateClaimSubmitPending);
		   }
		   if(allCanceledCases.size()>0)
			return allCanceledCases;
		   else return null;
		}
	private List<LabelBean> validateClaimSubmitPending(String status, int days, String schemeId) 
	{
		logger.info("Start:validateClaimSubmitPending(CD21) method");
//		System.out.println("Start:validateClaimSubmitPending(CD21) method");
		
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS='"+status+"'");
		query.append(" and A.case_hosp_code = '"+config.getString("HOSP_NIMS")+"' ");
		query.append(" AND A.SCHEME_ID='"+schemeId+"' AND TRUNC(SYSDATE-A.LST_UPD_DT) between 57 and 60 ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		query.append(" ('N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y')");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE('01/02/2016','DD/MM/YYYY')");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newCasesList;
	
	}
	
	private List<LabelBean> validateClaimPending(String status, int days, String schemeId) 
	{
		logger.info("Start:validateClaimPending(CD44,CD47,CD49) method");
//		System.out.println("Start:validateClaimPending(CD44,CD47,CD49) method");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS='"+status+"'");
		query.append(" and A.case_hosp_code = '"+config.getString("HOSP_NIMS")+"' ");
		query.append(" AND A.SCHEME_ID='"+schemeId+"' AND TRUNC(SYSDATE-A.LST_UPD_DT) between 27 and 30 ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		query.append(" ('N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y')");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE('01/02/2016','DD/MM/YYYY')");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return newCasesList;
	
		
	}
	
	private List<LabelBean> validatePendingPreauthNew(String status, int days, String schemeId) 
	{
		logger.info("Start:inside validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 ");
//		System.out.println("Start:inside validatePendingPreauthNew(CD10,CD210,CD217,CD208) after feb01 2016 ");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS='"+status+"'");
		query.append(" and A.case_hosp_code = '"+config.getString("HOSP_NIMS")+"' ");
		query.append(" AND A.SCHEME_ID='"+schemeId+"' AND TRUNC(SYSDATE-A.LST_UPD_DT) between 27 and 30 ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ('N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y')");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE('01/02/2016','DD/MM/YYYY')");
		
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	    
		return newCasesList;
	
		
	}
	private List<LabelBean> validatePendingPreauth(String status, int days,String schemeId)
	{
		logger.info("Start: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
//		System.out.println("Start: validatePendingPreauth(CD10,CD210,CD217,CD208) before feb01 2016 ");
		// TODO Auto-generated method stub
		List<LabelBean> newCasesList = new ArrayList<LabelBean>();
		try{
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS='"+status+"'");
		query.append(" and A.case_hosp_code = '"+config.getString("HOSP_NIMS")+"' ");
		query.append(" AND A.SCHEME_ID='"+schemeId+"' AND TRUNC(SYSDATE-A.LST_UPD_DT) between 42 and 45 ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ('N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y')");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)<TO_DATE('01/02/2016','DD/MM/YYYY')");
	
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return newCasesList;
	}
	private List<LabelBean> validateUnDischargedCases(String columnName1, String columnName2, int days)
		{
			logger.info("Start: validateSurgeryUpdatedCases(preauthApprvDt) before and after feb01 2016 ");
//			System.out.println("Start: validateSurgeryUpdatedCases(preauthApprvDt) before feb01 2016 ");
			
			List<LabelBean> newCasesList = null;
			try{
			StringBuffer query = new StringBuffer();
			
			query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
			query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CS_DIS_UPD_DT IS NULL ");
			query.append(" AND A.SCHEME_ID='CD202' AND A.PREAUTH_CANCEL_DT IS NULL  ");
			query.append(" and A.case_hosp_code = '"+config.getString("HOSP_NIMS")+"' AND ");
			query.append(" A.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
			query.append(" ('20.96','N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','99.23.1','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y') ");
			query.append(" and (case when trunc(b.reg_hosp_date) >= to_date('01/02/2016', 'DD/MM/YYYY') and (sysdate - trunc(a.CS_SURG_UPD_DT)) between 27 and 30 then 1 ");
			query.append(" when trunc(b.reg_hosp_date) < to_date('01/02/2016', 'DD/MM/YYYY') and (sysdate - trunc(a.CS_SURG_UPD_DT)) between 57 and 60 then 1 else 0  end) = 1 ");
		
			newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			return newCasesList;
		}
	private List<LabelBean> validateSurgeryUpdatedCases(String columnName1, String columnName2, int days)
	{
		logger.info("Start: validateSurgeryUpdatedCases(preauthApprvDt) before and after feb01 2016 ");
//		System.out.println("Start: validateSurgeryUpdatedCases(preauthApprvDt) before feb01 2016 ");
		
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE FROM EHF_CASE A , EHF_PATIENT B");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CS_SURG_DT IS NULL ");
		query.append(" AND A.SCHEME_ID='CD202' AND A.PREAUTH_CANCEL_DT IS NULL  ");
		query.append(" and A.case_hosp_code = '"+config.getString("HOSP_NIMS")+"' AND ");
		query.append(" A.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		query.append(" ('20.96','N18.6.A','92.23.2','92.23.3','92.24.2','92.20.1','92.20.2','92.27.1','92.27.2','92.29.1','99.23.2','99.23.1','95.49.7','95.49.8','95.49.3','95.49.4','95.49.5','92.24.3','64.3.1','92.23.1','92.24.1','92.29.2','92.29.3') AND ect.activeyn = 'Y') ");
		query.append(" and (case when trunc(b.reg_hosp_date) >= to_date('01/02/2016', 'DD/MM/YYYY') and (sysdate - trunc(a.preauth_aprv_dt)) between 27 and 30 then 1 ");
		query.append(" when trunc(b.reg_hosp_date) < to_date('01/02/2016', 'DD/MM/YYYY') and (sysdate - trunc(a.preauth_aprv_dt)) between 57 and 60 then 1 else 0  end) = 1 ");
	
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return newCasesList;
	}
	
	/*private List<LabelBean> validateUpdatedCases(String columnName1, String columnName2, int days)
	{
		logger.info("Start: validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before and after feb01 2016 ");
		System.out.println("Start : validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before feb01 2016 ");
		// TODO Auto-generated method stub
		Date dateWithoutTime= new Date();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR , -days);
		cal.set(Calendar.HOUR_OF_DAY, 0);
	    cal.set(Calendar.MINUTE, 0);
	    cal.set(Calendar.SECOND, 0);
	    cal.set(Calendar.MILLISECOND, 0);
	    dateWithoutTime = cal.getTime();
	    
	    Date date= Calendar.getInstance().getTime();
	    EhfCase finalCase=null;
	    
	    try
	    {
		    List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		    criteriaList.add(new GenericDAOQueryCriteria(columnName1,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NOT_NULL ));
		    criteriaList.add(new GenericDAOQueryCriteria(columnName1,dateWithoutTime, GenericDAOQueryCriteria.CriteriaOperator.LT ));
		    criteriaList.add(new GenericDAOQueryCriteria(columnName2,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
		    criteriaList.add(new GenericDAOQueryCriteria("preauthCancelDt",null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
		    criteriaList.add(new GenericDAOQueryCriteria("schemeId",config.getString("TG"), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
		    List<EhfCase> ehfCase= genericDao.findAllByCriteria(EhfCase.class, criteriaList);
			//criteriaList.removeAll(criteriaList);
			if(ehfCase!=null)
			{
				for(EhfCase caseList:ehfCase)
				{
					try
					{
						logger.info("Loop:inside validateUpdatedCases(csSurgUpdDt,csDisUpdDt)  loop");
						System.out.println("Loop:validateUpdatedCases(csSurgUpdDt,csDisUpdDt) loop");
						
						
						finalCase= genericDao.findById(EhfCase.class, String.class, caseList.getCaseId());
						
						//Start To Check Special Procs Only when Surgery Updated and Discharge not Updated
						if(columnName1.equalsIgnoreCase("csSurgUpdDt") && columnName2.equalsIgnoreCase("csDisUpdDt") &&
								finalCase.getSchemeId().equalsIgnoreCase(config.getString("TG")))
							{
								String validateSpecProcs=checkTherapyCond(finalCase,"Days");
								
								if(validateSpecProcs!=null && !"".equalsIgnoreCase(validateSpecProcs))
									{
									if(validateSpecProcs.equalsIgnoreCase("false"))
									{
										continue;
									}
										criteriaList.removeAll(criteriaList);
										
										cal = Calendar.getInstance();
										cal.add(Calendar.DAY_OF_YEAR , -Integer.parseInt(validateSpecProcs));
										cal.set(Calendar.HOUR_OF_DAY, 0);
									    cal.set(Calendar.MINUTE, 0);
									    cal.set(Calendar.SECOND, 0);
									    cal.set(Calendar.MILLISECOND, 0);
										
										dateWithoutTime = cal.getTime();
										criteriaList.add(new GenericDAOQueryCriteria("caseId",finalCase.getCaseId(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName1,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NOT_NULL ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName1,dateWithoutTime, GenericDAOQueryCriteria.CriteriaOperator.LT ));
									    criteriaList.add(new GenericDAOQueryCriteria(columnName2,null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
									    criteriaList.add(new GenericDAOQueryCriteria("preauthCancelDt",null, GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ));
									    List<EhfCase> newCaseList= genericDao.findAllByCriteria(EhfCase.class, criteriaList);
									    if(newCaseList==null)
									    	continue;
									    else if(newCaseList.size()==0)
									    	continue;
									}		
							}
						else if(columnName1.equalsIgnoreCase("preauthAprvDate") && columnName2.equalsIgnoreCase("csSurgUpdDt") &&
								finalCase.getSchemeId().equalsIgnoreCase(config.getString("TG")))
							{
								String validateSpecProcs=checkTherapyCond(finalCase,"Cancel");
								
								if(validateSpecProcs!=null && !"".equalsIgnoreCase(validateSpecProcs))
									{
										if(validateSpecProcs.equalsIgnoreCase("false"))
										{
											continue;
										}
									}
							}
						//End To Check Special Procs Only when Surgery Updated and Discharge not Updated
						
						String newCaseStatus=null;
						if(config.getString("PreauthApprStatuses").contains(("~"+finalCase.getCaseStatus()+"~")))
							newCaseStatus=config.getString("ApprovedCancelStatus");
						else if(config.getString("SurgyStatuses").contains(("~"+finalCase.getCaseStatus()+"~")))
							newCaseStatus=config.getString("SurgeryUpdCancelStatus");
						if(newCaseStatus==null)
							newCaseStatus=config.getString("auto_cancelled_status");
						
						if(finalCase!=null)
						{
							EhfmCmbDtls ehfmCmbDtls=genericDao.findById(EhfmCmbDtls.class, String.class, finalCase.getCaseStatus());
							String msg="";
							if(ehfmCmbDtls!=null)
								{
									if(ehfmCmbDtls.getCmbDtlName()!=null)
										msg=ehfmCmbDtls.getCmbDtlName();
								}
							finalCase.setCaseStatus(newCaseStatus);
							finalCase.setPreauthCancelDt(date);
							finalCase.setLstUpdDt(date);
							finalCase.setLstUpdUsr(config.getString("auto_cancel_actor"));
							finalCase= genericDao.save(finalCase);
							if(finalCase!=null)
							{
								Long actOrder = null;
								
								if(getMaxActOrder(finalCase.getCaseId())!=null)
								{
								actOrder = getMaxActOrder(finalCase.getCaseId())+1;
								}
								else{
								actOrder = 1L;
								}
								EhfAudit ehfAudit = new EhfAudit();
								ehfAudit.setId(new EhfAuditId(actOrder,finalCase.getCaseId()));
								ehfAudit.setActId(newCaseStatus);
								ehfAudit.setActBy(config.getString("auto_cancel_actor"));
								if(msg!=null)
									ehfAudit.setRemarks("As per Note order Dated 19.04.2014,this Case has been Cancelled by Trust on end of "+days+" days of inactivity in " +msg+" status");
								else
									ehfAudit.setRemarks("As per Note order Dated 19.04.2014,this Case has been Cancelled by Trust on end of "+days+" days of inactivity");
								ehfAudit.setCrtUsr(config.getString("auto_cancel_actor"));
								ehfAudit.setCrtDt(date);
								ehfAudit.setLangId("en_US");
								ehfAudit.setUserRole(config.getString("auto_cancel_role"));
								genericDao.save(ehfAudit);
							}
						}
						logger.info("Start: validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before and after feb01 2016 ");
						System.out.println("Start : validateUpdatedCases(csSurgUpdDt,csDisUpdDt) before feb01 2016 ");
						
					}
					catch(Exception e)
					{
						gLogger.error("Error in For loop in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
						break;
					}
				}
			}
	    }
	    catch(Exception e)
		{
	    	gLogger.error("Error in method validatePendingPreauth of class ValidateCaseImpl.java--> "+ e.getMessage());
			
		}
		return null;
	}
*/
	@Override
	public List<FlaggingVO> getDistrictsNew(String stateId, String patStateType)
	{
		List<FlaggingVO> distList=flaggingDao.getDistrictsNew(stateId,patStateType);
		return distList;
	}
}
