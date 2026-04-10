package com.ahct.preauth.dao;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.util.search.Search;
import org.util.search.SearchConstants;
import org.util.search.SearchQueryBean;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.model.EhfmHospitals;
import com.ahct.preauth.vo.DrugsVO;
import com.ahct.preauth.vo.CommonDtlsVO;
import com.ahct.preauth.vo.PatientVO;
import com.ahct.preauth.vo.PreauthVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfDentalOralExaminations;
import com.ahct.model.EhfDentalOtherExaminations;
import com.ahct.model.EhfDentalTissueExaminations;
import com.ahct.model.EhfDesignationMst;
import com.ahct.model.EhfEmployeeDocAttach;
import com.ahct.model.EhfEnrollment;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfJrnlstFamily;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientDocAttach;
import com.ahct.model.EhfPatientExamFind;
import com.ahct.model.EhfPatientHospDiagnosis;
import com.ahct.model.EhfPatientPersonalHistory;
import com.ahct.model.EhfSsrMedinpData;
import com.ahct.model.EhfTelephonicRegn;
import com.ahct.model.EhfmDiagCategoryMst;
import com.ahct.model.EhfmDiagCategoryMstId;
import com.ahct.model.EhfmDiagDisAnatomicalMst;
import com.ahct.model.EhfmDiagDiseaseMst;
import com.ahct.model.EhfmDiagMainCatMst;
import com.ahct.model.EhfmDiagMainCatMstId;
import com.ahct.model.EhfmDiagSubCatMst;
import com.ahct.model.EhfmDiagSubCatMstId;
import com.ahct.model.EhfmDiagnosisMst;
import com.ahct.model.EhfmDrugsMst;
import com.ahct.model.EhfmPersonalHistoryMst;
import com.ahct.model.EhfmRelationMst;
import com.ahct.model.EhfmSpecialities;
import com.ahct.model.EhfmTherapyCatMst;
import com.ahct.model.EhfmTherapyCatMstId;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmTherapyProcMstId;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;

public class PreauthDtlsDaoImpl implements PreauthDtlsDao{
Logger glogger=Logger.getLogger(PreauthDtlsDao.class);
HibernateTemplate hibernateTemplate;
private static ConfigurationService configurationService;
private static CompositeConfiguration config;

static {
	configurationService = ConfigurationService.getInstance();
	config = configurationService.getConfiguration();
}
public HibernateTemplate getHibernateTemplate() {
	return hibernateTemplate;
}
public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
	this.hibernateTemplate = hibernateTemplate;
}
	public GenericDAO getGenericDao() 
	{
		return genericDao;
	}
	public void setGenericDao(GenericDAO genericDao)
	{
		this.genericDao = genericDao;
	}
	GenericDAO genericDao;
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

	/* public Map getPatientOP(HashMap lParamMap) 
	 {
	    Map patientOp=new HashMap();	    
	      StringBuffer lStrBuff = new StringBuffer();
	     Map lHashQValues = new HashMap();
	     int liCnt=0;
	    
	     try
	     {	         	        
	    	 lStrBuff.append(" from AsritPatient   ap, ");
	         lStrBuff.append(" AsrimCombo     ac, ");
	         lStrBuff.append(" AsrimCombo     acm, ");
	         lStrBuff.append(" AsrimLocations al, ");
	         lStrBuff.append(" AsrimLocations alm, ");
	         lStrBuff.append(" AsrimLocations alv, ");	         
	         lStrBuff.append(" AsrimLocations alc, ");
	         lStrBuff.append(" AsrimLocations almc, ");
	         lStrBuff.append(" AsrimLocations alvc ");	         
	         lStrBuff.append(" where ap.caste = ac.id.cmbDtlId and ap.patientId = '"+lParamMap.get("PatientID")+"' and ");
	         lStrBuff.append(" acm.id.cmbDtlId = ap.relation and al.id.locId = ap.districtCode and ");
	         lStrBuff.append(" al.locHdrId = '"+"LH6"+"' and alm.id.locId = ap.mandalCode and ");	         
	         lStrBuff.append(" alm.locHdrId = '"+"LM7"+"' and alv.id.locId = ap.villageCode and ");	         
	         lStrBuff.append(" alv.locHdrId = '"+"LM8"+"' and alc.locHdrId = '"+"LH6"+"' and ");	        
	         lStrBuff.append(" alc.locHdrId = ap.cDistrictCode and ");	         
	         lStrBuff.append(" almc.locHdrId = '"+"LM7"+"' and almc.locHdrId = ap.cMandalCode and ");	         
	         lStrBuff.append(" alvc.locHdrId = '"+"LM8"+"' and alvc.locHdrId = ap.cVillageCode ");	         	         	        
	         lStrBuff.append(" order by ap.crtDt desc ");	  
	         
	         Object[] pairs=genericDao.executeHQLQueryObjectPair(lStrBuff.toString());
	         if(pairs!=null)
	         {
		         AsritPatient asritPatient=(AsritPatient)pairs[0];
		         AsrimCombo asrimCombo1=(AsrimCombo)pairs[1];
		         AsrimCombo asrimCombo2=(AsrimCombo)pairs[2];
		         EhfmLocations asrimLocations1=(EhfmLocations)pairs[3];
		         EhfmLocations asrimLocations2=(EhfmLocations)pairs[4];
		         EhfmLocations asrimLocations3=(EhfmLocations)pairs[5];
		         EhfmLocations asrimLocations4=(EhfmLocations)pairs[6];
		         EhfmLocations asrimLocations5=(EhfmLocations)pairs[7];
		         EhfmLocations asrimLocations6=(EhfmLocations)pairs[8];
		         EhfmLocations asrimLocations7=(EhfmLocations)pairs[9];
		         EhfmLocations asrimLocations8=(EhfmLocations)pairs[10];	         	         
	         
	             if(asritPatient.getCardIssueDt()!=null)
	             patientOp.put("IssueDate",sdf.format(asritPatient.getCardIssueDt()));
	                        
	             if(asrimCombo1.getCmbDtlName()!=null)
	                 patientOp.put("Caste",asrimCombo1.getCmbDtlName());
	                    
	             if(asritPatient.getOccupationCd()!=null)
	                 patientOp.put("occupation",asritPatient.getOccupationCd());  
	           
                if(asritPatient.getAddr1()!=null)
                    patientOp.put("addr1",asritPatient.getAddr1());  
	           
	             if(asritPatient.getAddr2()!=null)
	                 patientOp.put("addr2",asritPatient.getAddr2());  
	         
	             if(asritPatient.getPermAddr1()!=null)
	                 patientOp.put("C_HNo",asritPatient.getPermAddr1()); 
	                 
	             if(asritPatient.getPermAddr2()!=null)
	                 patientOp.put("C_Street",asritPatient.getPermAddr2());  
	         
	             if(asritPatient.getContactNo()!=null)
	                 patientOp.put("contact",asritPatient.getContactNo());  
	             
	             if(asrimCombo2.getCmbDtlName()!=null)
	                 patientOp.put("Relation",asrimCombo2.getCmbDtlName());  
	          
	             if(asrimLocations1.getLocName()!=null)
	                 patientOp.put("district",asrimLocations1.getLocName());  
	           
	             if(asrimLocations2.getLocName()!=null)
	                 patientOp.put("mandal",asrimLocations2.getLocName());  
	            
	             if(asrimLocations3.getLocName()!=null)
	                 patientOp.put("village",asrimLocations3.getLocName());  
	          
	             if(asrimLocations4.getLocName()!=null)
	                 patientOp.put("hamlet",asrimLocations4.getLocName());  
	          
	             if(asrimLocations5.getLocName()!=null)
	                 patientOp.put("Cdistrict",asrimLocations5.getLocName());  
	             
	             if(asrimLocations6.getLocName()!=null)
	                 patientOp.put("Cmandal",asrimLocations6.getLocName());  
	             
	             if(asrimLocations7.getLocName()!=null)
	                 patientOp.put("Cvillage",asrimLocations7.getLocName());  
	             
	             if(asrimLocations8.getLocName()!=null)
	                 patientOp.put("Chamlet",asrimLocations8.getLocName());
	            
	             if(asritPatient.getSrcRegistration()!=null)
	             {
	                 if(asritPatient.getSrcRegistration().equalsIgnoreCase("D"))
	                	 patientOp.put("src","Direct");
	                 else if(asritPatient.getSrcRegistration().equalsIgnoreCase("MC"))
	                	 patientOp.put("src","Medical");
	                 else if(asritPatient.getSrcRegistration().equalsIgnoreCase("P"))
	                	 patientOp.put("src","PHC");
	                 else
	                	 patientOp.put("src","CMCO");	                 
	             }
	             
	             if(asritPatient.getRefCardNo()!=null)
	                 patientOp.put("RefCard",asritPatient.getRefCardNo());  	          
	         }	    
	         liCnt=0;  
	         lStrBuff = null;
	         lStrBuff = new StringBuffer();
	         lHashQValues = null;
	         lHashQValues = new HashMap();
	        	          	        
	         lStrBuff.append(" from AsritPatientHospDiagnosis hp,AsritPatient ap,AsritPatientTests apt,AsrimTests ast,EhfCase ac  ");
	         lStrBuff.append(" where hp.patientId = ap.patientId  and ap.patientId = apt.patientId and ast.testId = apt.testId   and ");
	         lStrBuff.append(" ast.testId = apt.testId and apt.patientId = ap.patientId and  ");
	         lStrBuff.append("  ac.casePatientNo = ap.patientId   and inv.caseId = ac.caseId and ac.caseId ='"+lParamMap.get("CaseId")+"' ");
	        	         	         
	         Object[] pair=genericDao.executeHQLQueryObjectPair(lStrBuff.toString());	                 	
	        if(pair!=null)
	        {
		        AsritPatientHospDiagnosis asritPatientHospDiagnosis=(AsritPatientHospDiagnosis)pair[0];
	         	AsritPatient asritPatient1=(AsritPatient)pair[1];
	         	AsritPatientTests asritPatientTests=(AsritPatientTests)pair[2];
	         	AsrimTests asrimTests=(AsrimTests)pair[3];
	         	EhfCase ehfCase=(EhfCase)pair[4];
	         	    	
		        	         
	             if(asritPatientHospDiagnosis.getHistoryIllness()!=null)
	             patientOp.put("history_illness",asritPatientHospDiagnosis.getHistoryIllness());
				 
				  if(asritPatientHospDiagnosis.getPastHistory()!=null)
	             patientOp.put("past_history",asritPatientHospDiagnosis.getPastHistory());
				 
	             if(asritPatientHospDiagnosis.getHospOpinion()!=null)
	             patientOp.put("hosp_diagnosis",asritPatientHospDiagnosis.getHospOpinion());
	             
				 if(asritPatientHospDiagnosis.getExamntnFindings()!=null)
	             patientOp.put("examntn_findings",asritPatientHospDiagnosis.getExamntnFindings());
				 
				  if(asritPatientHospDiagnosis.getHospComplaint()!=null)
	             patientOp.put("patient_complaint",asritPatientHospDiagnosis.getHospComplaint());				 
				 
				  if(asritPatientHospDiagnosis.getHospOpinion()!=null)
	             patientOp.put("hosp_opinion",asritPatientHospDiagnosis.getHospOpinion());
				 
				  if(asritPatientHospDiagnosis.getDoctType()!=null)
	             patientOp.put("doct_type",asritPatientHospDiagnosis.getDoctType());
				 
				  if(asritPatientHospDiagnosis.getHospDiagnosis()!=null)
	             patientOp.put("prov_diagnosis",asritPatientHospDiagnosis.getHospDiagnosis());
				 
				  if(asritPatientHospDiagnosis.getDoctName()!=null)
	             patientOp.put("doct_name",asritPatientHospDiagnosis.getDoctName());
	             
			      if(asritPatientHospDiagnosis.getDoctRegNo()!=null)
			      patientOp.put("doct_reg_no",asritPatientHospDiagnosis.getDoctRegNo());
		              
			      if(asritPatientHospDiagnosis.getDoctQuali()!=null)
			      patientOp.put("doct_quali",asritPatientHospDiagnosis.getDoctQuali());
		              
			      if(asritPatientHospDiagnosis.getDoctMobNo()!=null)
			      patientOp.put("doct_mob_no",asritPatientHospDiagnosis.getDoctMobNo());
	              			             	             				  
				  if(ehfCase.getCrtDt()!=null)
					  patientOp.put("dt",sdf.format(ehfCase.getCrtDt()));	
	        }
	        lStrBuff=new StringBuffer();
	        lStrBuff.append("SELECT ast.testDesc as VALUE from AsritPatientTests apt,AsrimTests ast ");
	        lStrBuff.append("where ast.testId=apt.testId and apt.patientId=? ");
	        String[] args=new String[1];
	        args[0]=(String)lParamMap.get("PatientID");	       
	        List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, lStrBuff.toString(),args);
	        
	        if(list!=null && !list.isEmpty())
			  {
				  //replace(stragg(asrimTests.test_desc()), '~', ',')  ;
	        	String lStrTestDesc=null;
	        	for(LabelBean labelBean:list)
	        	{
	        		if(lStrTestDesc!=null)
	        			lStrTestDesc=lStrTestDesc+","+labelBean.getVALUE();
	        		else
	        			lStrTestDesc=labelBean.getVALUE();
	        	}
				  patientOp.put("Investigation",lStrTestDesc);				 			
			  }
	     }
	     catch(Exception e)
	     {
	    	 //e.printStackTrace();
	    	 glogger.error("Error in PreauthDtlsDao getPatientOP():"+e.getMessage());
	     }	   
	     return patientOp;
	 }	*/

	 /* (non-Javadoc)
	 * @see com.tcs.Webframework.preauth.dao.PreauthDtlsDao#getPatientCommonDtls(java.lang.String)
	 */
	public List<CommonDtlsVO> getPatientCommonDtls(String caseId){
			StringBuffer queryBuff = new StringBuffer();
			List<CommonDtlsVO> resLst = null;
			try
			{
				
				
				queryBuff.append(" select distinct p.name as PATIENTNAME , p.employeeNo as EMPLOYEENO, p.cardNo as CARDNO , l1.locName as DISTRICT , l2.locName  as MANDAL , l3.locName as VILLAGE , ec.caseRegnDate as date ");
				queryBuff.append(" , p.age||' years '||p.ageMonths||' months '||p.ageDays||' days ' as AGE , cast(p.contactNo as string) as CONTACT , cast(p.age as string) as AGEYEARS , case when p.slab ='P' then 'Private Ward' else 'Semi Private Ward' end as slabType ");
				queryBuff.append(" ,ec.caseNo  as CASENO , ec.claimNo as CLAIMNO  , ec.caseId as CASEID , p.patientId as PATID , p.cardType as cardType ");
				queryBuff.append("  ,p.regHospId as INTIID, hm.hospName||''||hm.hospDispCode as HOSPNAME , case when p.gender='M' then 'Male' else case when p.gender='F' then 'Female' else p.gender end end as GENDER , a.cmbDtlName as STATUS ,ec.caseStatus as CASESTAT   ");
				queryBuff.append(" , p.patientIpopNo as IPNO ,  p.intimationId as telephonicId , p.patientIpop as PATTYPE, p.houseNo || ' ' || p.street as PATADDR, p.crtDt||'' as PATDT , ");
				queryBuff.append(" case when ph.caseAdmType='EMG' then 'Emergency' else case when ph.caseAdmType='PLN' then 'Planned' else ph.caseAdmType end end as ADMTYPE , hm.houseNumber || ' ' || hm.street || ' ' || hm.hospCity as HOSPADDR , ");
				queryBuff.append(" ph.doctType as doctType,ph.doctId as DOCID,ph.doctName as DOCNAME,ph.doctQuali as DOCQUAL,ec.newBornBaby as newBornBaby,ph.doctMobNo as DOCCONTACT,ph.doctRegNo as DOCREGNO,p.crtUsr as mithra,ec.schemeId as  scheme,p.patientScheme as patientScheme,erm.relationName as relation");
				/**
				 * Removed below code to get rows in absence of category code and sub category code
				 */
				//queryBuff.append(" ,cm.catName as DISNAME, sm.subCatName as SURGNAME");
				
				queryBuff.append(" from EhfPatient p ,EhfmLocations l1, EhfmLocations l2 , EhfmLocations l3 ,EhfCase ec , EhfmHospitals hm ");
				queryBuff.append(" , EhfmCmbDtls a , EhfPatientHospDiagnosis ph,EhfmRelationMst erm ");
				
				/**
				 * Removed below code to get rows in absence of category code and sub category code
				 */
				//queryBuff.append(" , EhfmDiagCategoryMst cm , EhfmDiagSubCatMst sm  ");
				
				
				queryBuff.append(" where p.patientId = ec.casePatientNo and  p.districtCode = l1.id.locId and p.relation=erm.relationId and ");
				queryBuff.append(" p.mandalCode = l2.id.locId and p.villageCode = l3.id.locId  and ec.caseId = '"+caseId+"' ");
				queryBuff.append(" and p.regHospId = hm.hospId and a.cmbDtlId = ec.caseStatus ");
				queryBuff.append(" and p.patientId =ph.patientId ");
				/**
				 * Removed below code to get rows in absence of category code and sub category code
				 */
				//queryBuff.append(" and ph.categoryCode = cm.id.catCode and ph.mainCatCode = cm.id.mainCatCode ");
				//queryBuff.append(" and sm.id.catCode =ph.categoryCode and sm.id.subCatCode = ph.subCatCode ");
				
				resLst = genericDao.executeHQLQueryList(CommonDtlsVO.class, queryBuff.toString());
				
				
			}
			catch(Exception e)
			{
				glogger.error("Error in PreauthDtlsDao getPatientCommonDtls():"+e.getMessage());
			}
			return resLst;		
		}
		
	
	public List<EhfPatientDocAttach> getOnBedPhotoDtls(String patId)
	{
		List<EhfPatientDocAttach> asritPatientDocAttach = null ;
	    try
	    {	  
	    	List<GenericDAOQueryCriteria> critList = new ArrayList<GenericDAOQueryCriteria>();
			critList.add(new GenericDAOQueryCriteria("patientId",patId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));		
	    	asritPatientDocAttach = genericDao.findAllByCriteria( EhfPatientDocAttach.class, critList) ;	    	    		
	    }
	    catch ( Exception e )
	    {
	    	glogger.error("Error in PreauthDtlsDao getOnBedPhotoDtls():"+e.getMessage());
	    }
	    return asritPatientDocAttach ;
	}
	
	/*public Map saveFraudCrDtls(CmaAuditVO cmaVO)
    {      
		Map lResMap=null;
		String resData ="Failed";		//@transactional       
		EhfCaseCmaAudit asritCaseCmaAudit=genericDao.findById(EhfCaseCmaAudit.class,String.class,cmaVO.getCASEID());
		if(asritCaseCmaAudit!=null)
		{
			asritCaseCmaAudit.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			asritCaseCmaAudit=genericDao.save(asritCaseCmaAudit);			
            if("CD15664".equalsIgnoreCase(cmaVO.getCRTUSERROLE()))
            {
            	asritCaseCmaAudit.setJeoUserId(cmaVO.getJEOUSERID());
            	asritCaseCmaAudit.setJeoRemarks(cmaVO.getJEORMKS());
            	asritCaseCmaAudit.setJeoCrtDt(new java.sql.Timestamp(new Date().getTime()));
            }
            else if("CD1500".equalsIgnoreCase(cmaVO.getCRTUSERROLE()))
             {
            	asritCaseCmaAudit.setCmaUserId(cmaVO.getCMAUSERID());
            	asritCaseCmaAudit.setCmaRemarks(cmaVO.getCMARMKS());
            	asritCaseCmaAudit.setCmaCrtDt(new java.sql.Timestamp(new Date().getTime()));
             }
            asritCaseCmaAudit=genericDao.save(asritCaseCmaAudit);
		}
		else
		{
			asritCaseCmaAudit=new EhfCaseCmaAudit();		
			asritCaseCmaAudit.setLstUpdDt(new java.sql.Timestamp(new Date().getTime()));
			asritCaseCmaAudit.setCaseId(cmaVO.getCASEID());
			asritCaseCmaAudit.setAuditStatus("SUB");
            if("CD15663".equalsIgnoreCase(cmaVO.getCRTUSERROLE()))
            {
            	asritCaseCmaAudit.setDeoUserId(cmaVO.getDEOUSERID());
            	asritCaseCmaAudit.setDeoRemarks(cmaVO.getDEORMKS());
            	asritCaseCmaAudit.setDeoCrtDt(new java.sql.Timestamp(new Date().getTime()));
            }
			asritCaseCmaAudit=genericDao.save(asritCaseCmaAudit);
		}
		if(asritCaseCmaAudit!=null)
			lResMap=getFraudCrDtls(cmaVO.getCASEID());
		return lResMap;      				
    }
	
	public Map getFraudCrDtls(String pStrCaseId)
	{
		CmaAuditVO cmaVO =null;
		Map lResMap=new HashMap();
		try
		{								
			  StringBuffer sb=new StringBuffer();
	          sb.append("select acp.cardNo as RATIONCARD,ac.caseNo  as CASENO,ac.claimNo as  CLAIMNO, acp.patName as  PATIENTNAME,");
	          sb.append("  acp.ageDesc || '(' || acp.ptGenderDesc || ')'  as AGE , aco.cmbDtlName  as CASESTATUS,ac.caseStatus  as CASESTATUSCD ");
	          sb.append(" from   EhfCase ac, AsritCasePatient acp, AsrimCombo aco ");
	          sb.append(" where  ac.casePatientNo = acp.patientId and ac.caseStatus = aco.id.cmbDtlId and ac.caseId = ?");
	          String[] args=new String[1];
	          args[0]=pStrCaseId;
	          List<CmaAuditVO> cmaVOLst=genericDao.executeHQLQueryList(CmaAuditVO.class, sb.toString(),args);
	          if(cmaVOLst!=null && !cmaVOLst.isEmpty())
	          {
	        	  cmaVO =cmaVOLst.get(0);
	          }
	          
	          EhfCaseCmaAudit asritCaseCmaAudit=genericDao.findById(EhfCaseCmaAudit.class, String.class, pStrCaseId);
	          if(asritCaseCmaAudit!=null)
				{
		            cmaVO.setCASEID((asritCaseCmaAudit.getCaseId()!= null ? asritCaseCmaAudit.getCaseId() : ""));
		            cmaVO.setDEOUSERID(asritCaseCmaAudit.getDeoUserId());
		            cmaVO.setDEORMKS(asritCaseCmaAudit.getDeoRemarks());
		            cmaVO.setDRCRTDT(sdf.format(asritCaseCmaAudit.getDeoCrtDt()));
		            cmaVO.setJEOUSERID(asritCaseCmaAudit.getJeoUserId());
		            cmaVO.setJEORMKS(asritCaseCmaAudit.getJeoRemarks());
		            cmaVO.setJEOCRTDT(sdf.format(asritCaseCmaAudit.getJeoCrtDt()));
		            cmaVO.setCMAUSERID(asritCaseCmaAudit.getCmaUserId());
		            cmaVO.setCMARMKS(asritCaseCmaAudit.getCmaRemarks());
		            cmaVO.setCMACRTDT(sdf.format(asritCaseCmaAudit.getCmaCrtDt()));
		            cmaVO.setLSTUPDDT(sdf.format(asritCaseCmaAudit.getLstUpdDt()));            
				}
	          //For discussion notes  of claims
	            sb = new StringBuffer();
	            sb.append("select da.actId as D_ACTID, ");
	            sb.append("da.remarks as D_REMARKS,da.actionValue as D_ACTIONVALUE, ");
	            sb.append("au.firstName ||''|| au.lastName as D_ACTBY , ");
	            sb.append("da.crtDt as D_ACTDT, ");//'DD-Mon-YYYY HH:MI AM') 
	            sb.append("au.userRole as D_USERROLE, ac.cmbDtlName as D_CMBDTLNAME ");
	            sb.append("from AsritCaseDiscussionAudit da, AsrimUsers au , AsrimCombo ac ");
	            sb.append("where da.id.caseId = ? and da.actBy = au.userID and ac.id.cmbDtlId=au.userRole ");
	            sb.append("order by da.id.actOrder");
	            List<ClaimsFlowVO> discussionList=genericDao.executeHQLQueryList(CmaAuditVO.class, sb.toString(),args);
	           
	             lResMap.put("cmaAuditVO",cmaVO);
	             lResMap.put("discussionList",discussionList);
		}
		catch(Exception e){
			glogger.error("Error in PreauthDtlsDao getFraudCrDtls():"+e.getMessage());
		}
		return lResMap;
	}*/
	/**
	 * For IP tab
	 */
	public PreauthVO getPatientOpDtls(String pStrCaseId,String pStrPatientId)
	{
		PreauthVO preauthVO = new PreauthVO(); 	
		StringBuffer query = new StringBuffer();
		try
		{
			/**
			 * query to get registration details
			 */
			query.append(" select distinct ep.patientIpop as patientType, ep.name as patientName , cast(ep.age as string) as age ,ep.gender as gender , ep.cardNo as rationCard , ep.patientIpopNo as patientIPNo , ");
			query.append(" case when ep.srcRegistration='D' then 'Direct' else case when ep.srcRegistration='MC'  then 'Medical' else case when ep.srcRegistration='P' then 'PHC' end end end  as srcRegistration, ");
			query.append(" ep.pinCode as contactNo,ep.cardIssueDt as cardIssueDt,ep.cardNo as refCardNo , ep.occupationCd as occName , rm.relationName as relationName  , cast(ep.contactNo as string) as contactNo ,  ");	
			//query.append(" ep.houseNo as houseNo , ep.street as street  ,lm.locName as district ,lm1.locName as mandal , lm2.locName as village ");	
			// card address
			query.append(" ep.houseNo as cardHNo , ep.street as cardStreet  ,lm.locName as cardDistrict ,lm1.locName as cardMandal , lm2.locName as cardVillage, ep.crtUsr as cruUsr ");
			//query.append("   , s1.id.symptomCode as symCode ,s1.symptomName as symName ,s2.id.mainSymptomCode as mainSymCode ,s2.mainSymptomName as mainSymName  ");	
			
			// get communication details
			
			query.append(" ,l4.locName as district , l5.locName  as mandal , l6.locName as village ");
			query.append(" , ep.cHouseNo as houseNo , ep.cStreet as street , ep.cPinCode as pincode ");
			query.append(" , eh.hospName as hospitalName, l7.locName as hospDistrict ");
			
			query.append(" from EhfPatient ep , EhfmRelationMst rm , " );//AsrimCombo ac ,, EhfmOccupationMst om 
			query.append(" EhfmLocations lm ,EhfmLocations lm1 , EhfmLocations lm2   "); //,EhfmSystematicExamFnd s1 ,EhfmSystematicExamFnd s2 ,EhfPatientHospDiagnosis hd
			query.append("  , EhfmLocations l4, EhfmLocations l5 , EhfmLocations l6  "); //,EhfEnrollment ee , EhfEnrollmentFamily ef 
			query.append("  , EhfmLocations    l7, EhfmHospitals    eh  "); //,EhfEnrollment ee , EhfEnrollmentFamily ef 
			
			query.append("	where ep.patientId = '"+pStrPatientId+"'   ");	
			query.append(" and ep.districtCode = lm.id.locId and ep.mandalCode =lm1.id.locId and ep.villageCode = lm2.id.locId ");
			query.append(" and ep.relation = rm.relationId  "); //and ac.id.cmbDtlId =ep.caste
			//query.append(" and s1.id.symptomCode =hd.symptomCode and s2.id.mainSymptomCode =hd.mainSymptomCode  and hd.patientId =ep.patientId ");
			query.append(" and ep.cDistrictCode = l4.id.locId  "); //ef.ehfCardNo = ep.cardNo and ef.enrollPrntId = ee.enrollPrntId and 
			query.append(" and ep.cMandalCode = l5.id.locId and ep.cVillageCode = l6.id.locId  ");
			query.append(" and eh.hospId= ep.regHospId and (l7.id.locId= eh.hospDist  or   l7.id.locId= eh.oldHospDistCode)  ");
			
			List<PreauthVO> lstPreauthVO = genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(lstPreauthVO != null && lstPreauthVO.size() > 0)
	{
		preauthVO  = lstPreauthVO.get(0); 	// assigning registration details to vo
	}
	if(preauthVO.getCruUsr()!=null && !"".equalsIgnoreCase(preauthVO.getCruUsr()))
		preauthVO.setCruUsr(getEmpNameById(preauthVO.getCruUsr()));
	/**
	 * query to get Admission Details 
	 */
	  
	  EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class, pStrPatientId);
	if(ehfPatientHospDiagnosis != null)
	{
		preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());	
		if(ehfPatientHospDiagnosis.getDoctId()!=null && !"".equalsIgnoreCase(ehfPatientHospDiagnosis.getDoctId()))
		{
			if(preauthVO.getPatientType()!=null && !"".equalsIgnoreCase(preauthVO.getPatientType()) && "IP".equalsIgnoreCase(preauthVO.getPatientType()))
				preauthVO.setDocName(getEmpNameById(ehfPatientHospDiagnosis.getDoctId()));
			else if(preauthVO.getPatientType()!=null && !"".equalsIgnoreCase(preauthVO.getPatientType()) && "OP".equalsIgnoreCase(preauthVO.getPatientType()))
				preauthVO.setDocName(getDoctorById(ehfPatientHospDiagnosis.getDoctId()));
		}
		else
			preauthVO.setDocName("-NA-");
		preauthVO.setDocQual(ehfPatientHospDiagnosis.getDoctQuali());
		preauthVO.setDocMobNo(ehfPatientHospDiagnosis.getDoctMobNo());
		preauthVO.setDocReg(ehfPatientHospDiagnosis.getDoctRegNo());;
		preauthVO.setDocType(ehfPatientHospDiagnosis.getDoctType());
		preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());
		preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
		String complaintCode = ehfPatientHospDiagnosis.getComplaintType();
		String patComplaint = ehfPatientHospDiagnosis.getPatientComplaint();
			
		StringTokenizer str = null;
		StringTokenizer str1 = null;
		if(patComplaint!=null && !"".equalsIgnoreCase(patComplaint))
			str = new StringTokenizer(patComplaint,"~");
		if(complaintCode!=null && !"".equalsIgnoreCase(complaintCode))
			str1 = new StringTokenizer(complaintCode,"~");
	//EhfmSymptomsMst ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, complaintCode);
		StringBuffer query1=null;
		if(str1!=null)
		{
			while(str1.hasMoreTokens())
			{
				String code=str1.nextToken();
				query1 = new StringBuffer();
				query1.append(" select distinct ecm.id.mainComplCode as ID,ecm.mainComplName as VALUE from EhfmComplaintMst ecm where ecm.id.mainComplCode='"+code+"'");
				List<LabelBean> ehfComplaintList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
				if(ehfComplaintList!=null && !ehfComplaintList.isEmpty())
				{
					if(preauthVO.getComplaintType() == null || preauthVO.getComplaintType().equalsIgnoreCase(""))
					{
						//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
						preauthVO.setComplaintType(ehfComplaintList.get(0).getVALUE() + "( "+ehfComplaintList.get(0).getID()+" )")	;
					}
					else
					{
						//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
						preauthVO.setComplaintType( preauthVO.getComplaintType() +" , "+ ehfComplaintList.get(0).getVALUE() + "( "+ehfComplaintList.get(0).getID()+" )")	;
					}
					
				}
			}
		}
	preauthVO.setComplaintCode(complaintCode);
	if(str!=null)
	{
		while(str.hasMoreTokens())
		{
			query1 = new StringBuffer();
			query1.append(" select ecm.id.complCode as ID,ecm.complName as VALUE from EhfmComplaintMst ecm where ecm.id.complCode='"+str.nextToken()+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			//ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, str.nextToken());	
			if(ehfComplList != null && ehfComplList.size()>0)
			{
				if(preauthVO.getPatComplaint() == null || preauthVO.getPatComplaint().equalsIgnoreCase(""))
				{
					//preauthVO.setPatComplaint(ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;	
					preauthVO.setPatComplaint(ehfComplList.get(0).getVALUE() + "( "+ehfComplList.get(0).getID()+" )")	;
				}
				else
				{
					//preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfmSymptomsMst.getSymptomName() + "( "+ehfmSymptomsMst.getSymptomId()+" )")	;
					preauthVO.setPatComplaint( preauthVO.getPatComplaint() +" , "+ ehfComplList.get(0).getVALUE() + "( "+ehfComplList.get(0).getID()+" )")	;
				}
				
			}
		} // setting list of patient complaints
	}
	
	/**
	 * get past illness
	 */	
	preauthVO.setPastIllness(ehfPatientHospDiagnosis.getPastHistory());
	preauthVO.setPastIllenesCancers(ehfPatientHospDiagnosis.getPastHistoryCancers());
	preauthVO.setPastIllenesEndDis(ehfPatientHospDiagnosis.getPastHistoryEndDis());
	preauthVO.setPastIllenesSurg(ehfPatientHospDiagnosis.getPastHistorySurg());
	if(ehfPatientHospDiagnosis.getPastHistoryOthr()!=null && !ehfPatientHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
		preauthVO.setPastIllenesOthr(ehfPatientHospDiagnosis.getPastHistoryOthr());
	else
		preauthVO.setPastIllenesOthr("Past Illness- Others not found");
	/**
	 * get medco legal case details
	 */
	preauthVO.setLegalCaseCheck(ehfPatientHospDiagnosis.getLegalCaseCheck());
	preauthVO.setLegalCaseNo(ehfPatientHospDiagnosis.getLegalCaseNo());
	preauthVO.setPoliceStatName(ehfPatientHospDiagnosis.getPoliceStatName());
	
	/**
	 * get past history values
	 */
	String pastHist=ehfPatientHospDiagnosis.getPastHistory();
	if(pastHist != null && !pastHist.equals(""))
	{
	str1 = new StringTokenizer(pastHist,"~");
	while(str1.hasMoreTokens())
	{
		query1 = new StringBuffer();
		String disCode=str1.nextToken();
		if(disCode!=null && !disCode.equalsIgnoreCase("PH11"))
		{
			query1.append(" select hm.diseaseName as ID from EhfmPastHistoryMst hm where hm.diseaseCode='"+disCode+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			//ehfmSymptomsMst = genericDao.findById(EhfmSymptomsMst.class, String.class, str.nextToken());	
			if(ehfComplList != null && ehfComplList.size()>0)
			{	

				if(preauthVO.getPastIllnessValue() == null || preauthVO.getPastIllnessValue().equalsIgnoreCase(""))
				{	
					preauthVO.setPastIllnessValue(ehfComplList.get(0).getID());
				}
				else
				{
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" , "+ehfComplList.get(0).getID())	;
				}
				String concatRemarks="";
				if(disCode.equalsIgnoreCase("PH8"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistoryCancers();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH10"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistorySurg();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				if(disCode.equalsIgnoreCase("PH12"))
				{
					concatRemarks=ehfPatientHospDiagnosis.getPastHistoryEndDis();
					preauthVO.setPastIllnessValue( preauthVO.getPastIllnessValue() +" (" + concatRemarks + ") ");
				}
				
			}
		}
	}
	}
	else
		preauthVO.setPastIllnessValue("Past Illness not found");
	/**
	 * get personal History
	 */
	
	/*String personalHis = ehfPatientHospDiagnosis.getPersonalHistory();
	str = new StringTokenizer(personalHis,"~");
	while(str.hasMoreTokens())
	{
		EhfmPersonalHistoryMst ehfmPersonalHistoryMst = genericDao.findById(EhfmPersonalHistoryMst.class, String.class, str.nextToken())	;
	if(ehfmPersonalHistoryMst != null )
	{
		if(preauthVO.getPersonalHis() == null || preauthVO.getPersonalHis().equalsIgnoreCase(""))
		{
			preauthVO.setPersonalHis(ehfmPersonalHistoryMst.getDescription() + "( "+ehfmPersonalHistoryMst.getParntCode()+" )")	;	
		}
		else
		{
			preauthVO.setPersonalHis( preauthVO.getPersonalHis() +" , "+ ehfmPersonalHistoryMst.getDescription() + "( "+ehfmPersonalHistoryMst.getParntCode()+" )")	;
		}	
	}
	}*/
	preauthVO.setPersonalHis( ehfPatientHospDiagnosis.getPersonalHistory());
	String lDesc=null;
	List<LabelBean> personalHisList=new ArrayList<LabelBean>();
	List<LabelBean> personalHis=new ArrayList<LabelBean>();
	
	List<EhfmPersonalHistoryMst> parentList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode","PR");
	if(parentList!=null && !parentList.isEmpty())
	{
		
		for(EhfmPersonalHistoryMst ehfmPersonalHistoryMst1:parentList)
		{
			LabelBean lstPersonal = new LabelBean();
			lstPersonal.setID(ehfmPersonalHistoryMst1.getCode());
			lstPersonal.setVALUE(ehfmPersonalHistoryMst1.getDescription());
			List<EhfmPersonalHistoryMst> childList=genericDao.findAllByPropertyMatch(EhfmPersonalHistoryMst.class,"parntCode",ehfmPersonalHistoryMst1.getCode());
			List<LabelBean> personalsub=new ArrayList<LabelBean>();
			if(childList!=null && !childList.isEmpty())
			{
				
				for(EhfmPersonalHistoryMst ehfmPersonalHistoryMs2: childList)
				{
					LabelBean personalHisSub=new LabelBean();
					if(ehfmPersonalHistoryMs2!=null)
					{						
						if( lDesc== null || lDesc.equalsIgnoreCase(""))
						{
							lDesc=ehfmPersonalHistoryMs2.getDescription() ;	
						}
						else
						{
							lDesc=lDesc + "~" + ehfmPersonalHistoryMs2.getDescription() 	;	
						}
						personalHisSub.setID(ehfmPersonalHistoryMs2.getCode());
						personalHisSub.setVALUE(ehfmPersonalHistoryMs2.getDescription());
						}
					personalsub.add(personalHisSub);
					}
				
				}
			lstPersonal.setLstSub(personalsub);
			personalHisList.add(new LabelBean(ehfmPersonalHistoryMst1.getCode(),ehfmPersonalHistoryMst1.getDescription()+"^"+lDesc));
			lDesc="";
			personalHis.add(lstPersonal);
		}
	}
	preauthVO.setLstPersonalHistory(personalHis);
	
	EhfPatientPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfPatientPersonalHistory.class, String.class, pStrPatientId);
	List<String> lstPerHis = new ArrayList<String>();
	if(ehfPatientPersonalHistory != null)
	{
		if(ehfPatientPersonalHistory.getAppetite() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAppetite());	
		}
		if(ehfPatientPersonalHistory.getDiet() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getDiet());	
		}
		if(ehfPatientPersonalHistory.getBowels() !=null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getBowels());		
		}
		if(ehfPatientPersonalHistory.getNutrition() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getNutrition());
		}
		if(ehfPatientPersonalHistory.getKnownAllergies() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getKnownAllergies());	
			if(ehfPatientPersonalHistory.getKnownAllergies().contains("PR5.1"))
				preauthVO.setTestKnown(ehfPatientPersonalHistory.getKnownAllergies());
		}
		if(ehfPatientPersonalHistory.getAddictions() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
			if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
			preauthVO.setTest(ehfPatientPersonalHistory.getAddictions());
		}
		if(ehfPatientPersonalHistory.getMaritalStatus() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
		}
	}
	preauthVO.setLstPerHis(lstPerHis);
	/**
	 * get family history
	 */		
	if(ehfPatientHospDiagnosis.getFamilyHistory()!=null && !ehfPatientHospDiagnosis.getFamilyHistory().equalsIgnoreCase("")){
		preauthVO.setFamilyHis(ehfPatientHospDiagnosis.getFamilyHistory());
	}//end of ehfPatientHospDiagnosis!=null
	
	if(ehfPatientHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfPatientHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
		preauthVO.setFamilyHistoryOthr(ehfPatientHospDiagnosis.getFamilyHistoryOthr());
	else
		preauthVO.setFamilyHistoryOthr("Family History -Others not found");
	
	
	
	/**
	 * get family history values
	 */
	String famhist=ehfPatientHospDiagnosis.getFamilyHistory();
	if(famhist != null && !famhist.equals(""))
	{
	str1 = new StringTokenizer(famhist,"~");
	while(str1.hasMoreTokens())
	{
		 query1 = new StringBuffer();
		String code=str1.nextToken();
		if(code!=null && !code.equalsIgnoreCase("FH11"))
		{
			query1.append(" select hm.description as ID from EhfmFamilyHistoryMst hm where hm.code='"+code+"'");
			List<LabelBean> ehfComplList = genericDao.executeHQLQueryList(LabelBean.class, query1.toString());
			if(ehfComplList != null && ehfComplList.size()>0)
			{
				if(preauthVO.getFamilyHistValue() == null || preauthVO.getFamilyHistValue().equalsIgnoreCase(""))
				{	
					preauthVO.setFamilyHistValue(ehfComplList.get(0).getID());
				}
				else
				{
					preauthVO.setFamilyHistValue( preauthVO.getFamilyHistValue() +" , "+ehfComplList.get(0).getID())	;
				}
				
			}
		}
	}
	}
	else
		preauthVO.setFamilyHistValue("Family History not found");
	}//end of ehfPatientHospDiagnosis!=null
	/**
	 * get Examination findings
	 */
	EhfPatientExamFind ehfPatientExamFind = genericDao.findById(EhfPatientExamFind.class, String.class, pStrPatientId);
	if(ehfPatientExamFind != null)
	{
		if(ehfPatientExamFind.getBmi()!=null && !ehfPatientExamFind.getBmi().equalsIgnoreCase(""))
		preauthVO.setBmi(ehfPatientExamFind.getBmi());
		else
		preauthVO.setBmi("NA");
		if(ehfPatientExamFind.getHeight()!=null && !ehfPatientExamFind.getHeight().equalsIgnoreCase(""))
		preauthVO.setHeight(ehfPatientExamFind.getHeight());
		else
		preauthVO.setHeight("NA");
		if(ehfPatientExamFind.getWeight()!=null && !ehfPatientExamFind.getWeight().equalsIgnoreCase(""))
		preauthVO.setWeight(ehfPatientExamFind.getWeight());
		else
		preauthVO.setWeight("NA");
		preauthVO.setPallor(ehfPatientExamFind.getPallor());
		preauthVO.setCyanosis(ehfPatientExamFind.getCyanosis());
		preauthVO.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrToes());
		preauthVO.setLymphadenopathy(ehfPatientExamFind.getLymphadenopathy());
		preauthVO.setEdema(ehfPatientExamFind.getOedemaInFeet());
		preauthVO.setMalNutrition(ehfPatientExamFind.getMalnutrition());
		if(ehfPatientExamFind.getDehydration() != null && ehfPatientExamFind.getDehydration().equalsIgnoreCase("N"))
		preauthVO.setDehydration(ehfPatientExamFind.getDehydration());
		else if(ehfPatientExamFind.getDehydration() != null &&  !ehfPatientExamFind.getDehydration().equals("") && ehfPatientExamFind.getDehydration().contains("Y"))
		{
				preauthVO.setDehydration(ehfPatientExamFind.getDehydration().charAt(0)+"");
				preauthVO.setDehydrationType(ehfPatientExamFind.getDehydration().substring(1));
		}
		if(ehfPatientExamFind.getTemperature()!=null && !ehfPatientExamFind.getTemperature().equalsIgnoreCase(""))
		preauthVO.setTemperature(ehfPatientExamFind.getTemperature());
		else
		preauthVO.setTemperature("NA");	
		if(ehfPatientExamFind.getPluseRate()!=null && !ehfPatientExamFind.getPluseRate().equalsIgnoreCase(""))
		preauthVO.setPulseRate(ehfPatientExamFind.getPluseRate());
		else
		preauthVO.setPulseRate("NA");
		if(ehfPatientExamFind.getRespirationRate()!=null && !ehfPatientExamFind.getRespirationRate().equalsIgnoreCase(""))
		preauthVO.setRespirationRate(ehfPatientExamFind.getRespirationRate());
		else
			preauthVO.setRespirationRate("NA");	
		if(ehfPatientExamFind.getBpLt()!=null && !ehfPatientExamFind.getBpLt().equalsIgnoreCase(""))
		preauthVO.setBpLmt(ehfPatientExamFind.getBpLt());
		else
			preauthVO.setBpLmt("NA");
		if(ehfPatientExamFind.getBpRt()!=null && !ehfPatientExamFind.getBpRt().equalsIgnoreCase(""))
		preauthVO.setBpRmt(ehfPatientExamFind.getBpRt());
		else
			preauthVO.setBpRmt("NA");
	}
	
	/**
	 * get system Examination findings
	 */
	
	//genericDao.findById(ehfm_systematic_exam_fnd, idClass, id);
	

	/**
	 * Investigation Details--wm_concate(gim.invName );
	 */
	query=new StringBuffer();
	query.append("select gim.invName as investRemarks from EhfmGenInvestigationsMst gim,EhfPatientTests pt"); 
			query.append("      where pt.testId=gim.invCode and pt.patientId='"+pStrPatientId+"' ");
	List<PreauthVO> list1=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(list1!=null && !list1.isEmpty())
	{
		for(PreauthVO preauthVO1:list1)
		{
			if(preauthVO.getInvestRemarks() == null || preauthVO.getInvestRemarks().equalsIgnoreCase(""))
			{
				preauthVO.setInvestRemarks(preauthVO1.getInvestRemarks());	
			}
			else
			{
				preauthVO.setInvestRemarks(preauthVO.getInvestRemarks()+" , "+preauthVO1.getInvestRemarks());	
			}			
		}
	}
	
	/**
	 * Main Signs and Symptoms
	 */
	query=new StringBuffer();
	query.append("select e.symptomName as symptoms from EhfmSystematicExamFnd e,EhfSymtematicExamDtls d where e.id.symptomCode=d.id.symptomCode and d.id.caseId='"+pStrCaseId+"'");
	List<PreauthVO> list2=genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
	if(list2!=null && !list2.isEmpty())
	{
		for(PreauthVO preauthVO1:list2)
		{
			if(preauthVO.getSymName() == null || preauthVO.getSymName().equalsIgnoreCase(""))
			{
				preauthVO.setSymName(preauthVO1.getSymptoms());	
			}
			else
			{
				preauthVO.setSymName(preauthVO.getSymName()+" , "+preauthVO1.getSymptoms());	
			}
		}
	}
	else
	{
		preauthVO.setSymName("NA");
	}
		
	/**
	 * drug history
	 */
	EhfPatientPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfPatientPersonalHistory.class, String.class, pStrPatientId);
	List<String> lstPerHis = new ArrayList<String>();
	if(ehfPatientPersonalHistory != null)
	{
		if(ehfPatientPersonalHistory.getAppetite() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAppetite());	
		}
		if(ehfPatientPersonalHistory.getDiet() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getDiet());	
		}
		if(ehfPatientPersonalHistory.getBowels() !=null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getBowels());		
		}
		if(ehfPatientPersonalHistory.getNutrition() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getNutrition());
		}
		if(ehfPatientPersonalHistory.getKnownAllergies() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getKnownAllergies());	
			if(ehfPatientPersonalHistory.getKnownAllergies().contains("PR5.1"))
				preauthVO.setTestKnown(ehfPatientPersonalHistory.getKnownAllergies());
		}
		if(ehfPatientPersonalHistory.getAddictions() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
			if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
			preauthVO.setTest(ehfPatientPersonalHistory.getAddictions());
		}
		if(ehfPatientPersonalHistory.getMaritalStatus() != null)
		{
			lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
		}
		if(ehfPatientPersonalHistory.getDrugHst() != null)
		{
			preauthVO.setDrugHst(ehfPatientPersonalHistory.getDrugHst());	
		}
		if(ehfPatientPersonalHistory.getDrugHstVal() != null)
		{
			preauthVO.setDrugHstVal(ehfPatientPersonalHistory.getDrugHstVal());	
		}
		else
			preauthVO.setDrugHstVal("Drug history not avialable");
	}
	preauthVO.setLstPerHis(lstPerHis);
	
	
	/**
	 * Medical History and others
	 **/
	StringBuffer query2 = new StringBuffer();
	StringBuffer query3 = new StringBuffer();
	EhfDentalOtherExaminations ehfDentalOtherExaminations = genericDao.findById(EhfDentalOtherExaminations.class, String.class, pStrPatientId);
		if(ehfDentalOtherExaminations!=null){
			if(ehfDentalOtherExaminations.getMedicalHistoryOthr()!=null && ehfDentalOtherExaminations.getMedicalHistoryOthr()!=""){
				preauthVO.setMedicalHistoryOthr(ehfDentalOtherExaminations.getMedicalHistoryOthr());
			}
			else
			{
				preauthVO.setMedicalHistoryOthr("NA");
			}
			if(ehfDentalOtherExaminations.getPreviousDentalTreatment()!=null && ehfDentalOtherExaminations.getPreviousDentalTreatment()!="")
			{
				preauthVO.setPreviousDentalTreatment(ehfDentalOtherExaminations.getPreviousDentalTreatment());
			}
			else
			{
				preauthVO.setPreviousDentalTreatment("NA");
			}
				String OcclussionTypeId=ehfDentalOtherExaminations.getOcclusionDivii();
				String OcclusionId=ehfDentalOtherExaminations.getOcclusion();
				if(ehfDentalOtherExaminations.getOcclusionOther()!=null && ehfDentalOtherExaminations.getOcclusionOther()!="" && ehfDentalOtherExaminations.getOcclusion().equalsIgnoreCase("CH83"))
				{
				preauthVO.setOcclusionTypeTxt(ehfDentalOtherExaminations.getOcclusionOther());
				}
				if(ehfDentalOtherExaminations.getClinicalProbingDepth()!=null)
					preauthVO.setProbingdepth(ehfDentalOtherExaminations.getClinicalProbingDepth());
				if(OcclussionTypeId!=null && !OcclussionTypeId.equalsIgnoreCase("") && !ehfDentalOtherExaminations.getOcclusion().equalsIgnoreCase("CH83")){
					query2.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+OcclussionTypeId+"'");
					List<LabelBean> ehfOccType = genericDao.executeHQLQueryList(LabelBean.class, query2.toString());
					if(ehfOccType != null && ehfOccType.size()>0)
					{
						preauthVO.setOcclusionTypeTxt(ehfOccType.get(0).getID());
					}
				}
				if(OcclusionId!=null && !OcclusionId.equalsIgnoreCase("")){
					query3.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+OcclusionId+"'");
					List<LabelBean> ehfOcc = genericDao.executeHQLQueryList(LabelBean.class, query3.toString());
					if(ehfOcc != null && ehfOcc.size()>0)
					{
						preauthVO.setOcclusionTxt(ehfOcc.get(0).getID());
					}
				}
				
			
			
			String medDet=ehfDentalOtherExaminations.getMedicalHistory();
			if(medDet!=null && !medDet.equalsIgnoreCase("")){
				StringTokenizer str1 = new StringTokenizer(medDet,"~");
				
				while(str1.hasMoreTokens())
				{
					query = new StringBuffer();
					String code=str1.nextToken();
					if(code!=null && !code.equalsIgnoreCase("CH117"))
					{
					query.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+code+"'");
					List<LabelBean> ehfMedHist = genericDao.executeHQLQueryList(LabelBean.class, query.toString());					
					
					if(ehfMedHist != null && ehfMedHist.size()>0)
					{
						if(preauthVO.getMedHistVal() == null || preauthVO.getMedHistVal().equalsIgnoreCase(""))
						{	
							preauthVO.setMedHistVal(ehfMedHist.get(0).getID());
						}
						else
						{
							preauthVO.setMedHistVal( preauthVO.getMedHistVal() +" , "+ehfMedHist.get(0).getID())	;
						}
						
					}
					
					}
				}
			}
			else{
				preauthVO.setMedHistVal("NA");
			}
			
		}
		/** 
		 * Extra oral Examin
		 **/
		
		EhfDentalOralExaminations ehfDentalOralExaminations = genericDao.findById(EhfDentalOralExaminations.class, String.class, pStrPatientId);
		if(ehfDentalOralExaminations!=null){
			
			String LymphId=ehfDentalOralExaminations.getLymphadenopathy();
			String jawsId=ehfDentalOralExaminations.getJaws();
			String tmjId=ehfDentalOralExaminations.getTmj();
			String faceId=ehfDentalOralExaminations.getFace();
			String LymphSub=ehfDentalOralExaminations.getLymphadenopathySub();
			String jawsSub=ehfDentalOralExaminations.getJawsSub();
			String Lymph1="";
			String Jaws1="";
			String Tmj1="";
			String Face1="";
			String SubLymphSub="";
			String SubJawsSub="";
			query = new StringBuffer();
			query.append(" select labelNAME as ID,labelPrntID as VALUE from EhfmDentalCmbDtls where id.labelID in ('"+LymphId+"','"+jawsId+"','"+tmjId+"','"+faceId+"')");
			List<LabelBean> ehfLymph = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
			for (int i=0;i<ehfLymph.size();i++){
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH4")){
				Lymph1=ehfLymph.get(i).getID();
			}
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH3")){
				Jaws1=ehfLymph.get(i).getID();
			}
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH2")){
				Tmj1=ehfLymph.get(i).getID();
			}
			if(ehfLymph.get(i).getVALUE().equalsIgnoreCase("CH1")){
				Face1=ehfLymph.get(i).getID();
			}
			}
			if(Lymph1!=null && Lymph1!="")
				preauthVO.setRegionalLymphadenopathyDtrsSub(Lymph1);
				else
					preauthVO.setRegionalLymphadenopathyDtrsSub("NA");
				if(Jaws1!=null && Jaws1!="")
				preauthVO.setJawsDtrsSub(Jaws1);
				else
					preauthVO.setJawsDtrsSub("NA");
				if(Tmj1!=null && Tmj1!="")
				preauthVO.setTmjDtrsSub(Tmj1);
				else
					preauthVO.setTmjDtrsSub("NA");
				if(Face1!=null && Face1!="")
				preauthVO.setFaceDtrsSub(Face1);
				else
					preauthVO.setFaceDtrsSub("NA");
						
			if(!Lymph1.equalsIgnoreCase("Other Lymph nodes") && !Lymph1.equalsIgnoreCase("NAD ( No Abnormality detected )")){
				if(LymphSub!=null && LymphSub!="")
				{				
				StringTokenizer str1 = new StringTokenizer(LymphSub,"~");
				while(str1.hasMoreTokens())
				{
					query = new StringBuffer();
					String code=str1.nextToken();
					query.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+code+"'");
					List<LabelBean> ehfSubLymphSub = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
					
					
					if(ehfSubLymphSub != null && ehfSubLymphSub.size()>0)
					{
						if(SubLymphSub == null || SubLymphSub.equalsIgnoreCase(""))
						{	
							SubLymphSub=ehfSubLymphSub.get(0).getID();
						}
						else
						{
							SubLymphSub=SubLymphSub+","+ehfSubLymphSub.get(0).getID();
						}
						
					}
					
				}
				}
			}
			else if(Lymph1.equalsIgnoreCase("Other Lymph nodes")){
				
				SubLymphSub=LymphSub;
			}
			else
				SubLymphSub="NA";
//			preauthVO.setRegionalLymphadenopathyDtrsTxt(SubLymphSub);
			if(!Jaws1.equalsIgnoreCase("NAD ( No Abnormality detected )") && !Jaws1.equalsIgnoreCase("Growth /swelling in mandible") && !Jaws1.equalsIgnoreCase("Growth /swelling in maxilla") && !Jaws1.equalsIgnoreCase("Others")){
				if(jawsSub!=null && jawsSub!="")
				{
				StringTokenizer str1 = new StringTokenizer(jawsSub,"~");
				while(str1.hasMoreTokens())
				{
					query = new StringBuffer();
					String code=str1.nextToken();
					query.append(" select labelNAME as ID from EhfmDentalCmbDtls where id.labelID='"+code+"'");
					List<LabelBean> ehfSubJawsSub = genericDao.executeHQLQueryList(LabelBean.class, query.toString());
					
					
					if(ehfSubJawsSub != null && ehfSubJawsSub.size()>0)
					{
						if(SubJawsSub == null || SubJawsSub.equalsIgnoreCase(""))
						{	
							SubJawsSub=ehfSubJawsSub.get(0).getID();
						}
						else
						{
							SubJawsSub=SubJawsSub+","+ehfSubJawsSub.get(0).getID();
						}
						
					}
					
				}
				}
				
			}
			else if(Jaws1.equalsIgnoreCase("Growth /swelling in mandible") || Jaws1.equalsIgnoreCase("Growth /swelling in maxilla") || Jaws1.equalsIgnoreCase("Others")){
				
				SubJawsSub=jawsSub;
			}
			else
				SubJawsSub="NA";
			preauthVO.setJawsDtrsTxt(SubJawsSub);
			preauthVO.setRegionalLymphadenopathyDtrsTxt(SubLymphSub);
			
			/**
			 * Intra Oral Examins
			 */
			if(ehfDentalOralExaminations.getHardPalate()!=null && ehfDentalOralExaminations.getHardPalate()!="")
				preauthVO.setDntsublistoral0(ehfDentalOralExaminations.getHardPalate());
				else
					preauthVO.setDntsublistoral0("NA");
				if(ehfDentalOralExaminations.getSoftPalate()!=null && ehfDentalOralExaminations.getSoftPalate()!="")
				preauthVO.setDntsublistoral1(ehfDentalOralExaminations.getSoftPalate());
				else
					preauthVO.setDntsublistoral1("NA");
				if(ehfDentalOralExaminations.getFloorMouth()!=null && ehfDentalOralExaminations.getFloorMouth()!="")
				preauthVO.setDntsublistoral2(ehfDentalOralExaminations.getFloorMouth());
				else
					preauthVO.setDntsublistoral2("NA");
				if(ehfDentalOralExaminations.getTongue()!=null && ehfDentalOralExaminations.getTongue()!="")
				preauthVO.setDntsublistoral3(ehfDentalOralExaminations.getTongue());
				else
					preauthVO.setDntsublistoral3("NA");
				if(ehfDentalOralExaminations.getFrenalAttachment()!=null && ehfDentalOralExaminations.getFrenalAttachment()!="")
				preauthVO.setDntsublistoral4(ehfDentalOralExaminations.getFrenalAttachment());
				else
					preauthVO.setDntsublistoral4("NA");
				if(ehfDentalOralExaminations.getBuccalMucosa()!=null && ehfDentalOralExaminations.getBuccalMucosa()!="")
				preauthVO.setDntsublistoral5(ehfDentalOralExaminations.getBuccalMucosa());
				else
					preauthVO.setDntsublistoral5("NA");
				if(ehfDentalOralExaminations.getGingiva()!=null && ehfDentalOralExaminations.getGingiva()!="")
				preauthVO.setDntsublistoral6(ehfDentalOralExaminations.getGingiva());
				else
					preauthVO.setDntsublistoral6("NA");
				if(ehfDentalOralExaminations.getSwSite()!=null && ehfDentalOralExaminations.getSwSite()!="")
				preauthVO.setSwSite(ehfDentalOralExaminations.getSwSite());
				else
					preauthVO.setSwSite("NA");
				if(ehfDentalOralExaminations.getSwSize()!=null && ehfDentalOralExaminations.getSwSize()!="")
				preauthVO.setSwSize(ehfDentalOralExaminations.getSwSize());
				else
					preauthVO.setSwSize("NA");
				if(ehfDentalOralExaminations.getSwExtension()!=null && ehfDentalOralExaminations.getSwExtension()!="")
				preauthVO.setSwExtension(ehfDentalOralExaminations.getSwExtension());
				else
					preauthVO.setSwExtension("NA");
				if(ehfDentalOralExaminations.getSwColour()!=null && ehfDentalOralExaminations.getSwColour()!="")
				preauthVO.setSwColour(ehfDentalOralExaminations.getSwColour());
				else
					preauthVO.setSwColour("NA");
				if(ehfDentalOralExaminations.getSwConsistency()!=null && ehfDentalOralExaminations.getSwConsistency()!="")
				preauthVO.setSwConsistency(ehfDentalOralExaminations.getSwConsistency());
				else
					preauthVO.setSwConsistency("NA");
				if(ehfDentalOralExaminations.getSwTenderness()!=null && ehfDentalOralExaminations.getSwTenderness()!="")
				preauthVO.setSwTenderness(ehfDentalOralExaminations.getSwTenderness());
				else
					preauthVO.setSwTenderness("NA");
				if(ehfDentalOralExaminations.getSwBorders()!=null && ehfDentalOralExaminations.getSwBorders()!="")
				preauthVO.setSwBorders(ehfDentalOralExaminations.getSwBorders());
				else
					preauthVO.setSwBorders("NA");
				if(ehfDentalOralExaminations.getPsSite()!=null && ehfDentalOralExaminations.getPsSite()!="")
				preauthVO.setPsSite(ehfDentalOralExaminations.getPsSite());
				else
					preauthVO.setPsSite("NA");
				if(ehfDentalOralExaminations.getPsDischarge()!=null && ehfDentalOralExaminations.getPsDischarge()!="")
				preauthVO.setPsDischarge(ehfDentalOralExaminations.getPsDischarge());
				else
					preauthVO.setPsDischarge("NA");	
		
		}
		EhfDentalTissueExaminations ehfDentalTissueExaminations = genericDao.findById(EhfDentalTissueExaminations.class, String.class, pStrPatientId);
		{
			if(ehfDentalTissueExaminations!=null)
			{
				if(ehfDentalTissueExaminations.getDeciduousCaries()!=null)
				  preauthVO.setCarriesdecidous(ehfDentalTissueExaminations.getDeciduousCaries());
				if(ehfDentalTissueExaminations.getDeciduousMissing()!=null)
					  preauthVO.setMissingdecidous(ehfDentalTissueExaminations.getDeciduousMissing());
				if(ehfDentalTissueExaminations.getMobile()!=null)
					  preauthVO.setMobiledecidous(ehfDentalTissueExaminations.getMobile());
				if(ehfDentalTissueExaminations.getGrosslyDecayed()!=null)
					  preauthVO.setGrosslydecadedecidous(ehfDentalTissueExaminations.getGrosslyDecayed());
				if(ehfDentalTissueExaminations.getPermanentCaries()!=null)
					  preauthVO.setCarriespermanent(ehfDentalTissueExaminations.getPermanentCaries());
				if(ehfDentalTissueExaminations.getRootGrosslyDecayed()!=null)
					  preauthVO.setRootstumppermannet(ehfDentalTissueExaminations.getRootGrosslyDecayed());
				if(ehfDentalTissueExaminations.getMobility()!=null)
					  preauthVO.setMobilitypermanent(ehfDentalTissueExaminations.getMobility());
				if(ehfDentalTissueExaminations.getAttritionAbrasion()!=null)
					  preauthVO.setAttritionpermanent(ehfDentalTissueExaminations.getAttritionAbrasion());
				if(ehfDentalTissueExaminations.getPermanentMissing()!=null)
					  preauthVO.setMissingpermanent(ehfDentalTissueExaminations.getPermanentMissing());
				if(ehfDentalTissueExaminations.getPermanentothers()!=null)
					  preauthVO.setOtherpermanent(ehfDentalTissueExaminations.getPermanentothers());
					
			}
				
		}
	
		}catch(Exception e)
		{
			e.printStackTrace();	
		}		
		return preauthVO;
	}
	public List<PreauthVO> getCasesWorkList(String caseId)
	{
		List<PreauthVO> casesList= new ArrayList<PreauthVO>();
		StringBuffer query = new StringBuffer();
		try
		{
			query.append(" select ea.actId as actId, eu.firstName ||' '|| eu.middleName ||' '|| eu.lastName as auditName, ");
			query.append(" ea.crtDt as auditDate, ea.remarks as remarks,to_char(ea.id.actOrder) as test ");
			query.append(" from EhfAudit ea, EhfmUsers eu ");
			query.append(" where ea.id.caseId = '"+caseId+"' and eu.userId = ea.crtUsr order by ea.id.actOrder ");
			casesList= genericDao.executeHQLQueryList(PreauthVO.class, query.toString());
			
		}
		catch(Exception e)
		{
			glogger.error("Exception in method getCasesWorkList in PreauthServiceImpl"+ e.getMessage());
		}
		return casesList;
	}
	public String getEmpNameById(String id)
	{
		String q="select firstName||' '||lastName as ID from EhfmUsers where userId='"+id+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			glogger.error("Error in PreauthDtlsDao getEmpNameById():"+e.getMessage());
		}
		return user;
	}
	
	
	public String getDoctorById(String id) {
		String q="select distinct title||' '||splstName as ID from EhfmSplstDctrs where id.regNum='"+id+"'";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, q);
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			glogger.error("Error in PreauthDtlsDao getDoctorById():"+e.getMessage());
		}
		return user;
	}
	
	/**
     * ;
     * @param String hospId
     * @return String hospName
     * @function This Method is Used For getting Hospital Name
     */
	public String getHospName(String hospId) throws Exception {
		String hospitalName=null;
		try
		{
			EhfmHospitals asrimHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
		hospitalName=asrimHospitals.getHospName();
		}
		catch(Exception e)
		{
			glogger.error("Exception Occurred in getHospName() in PreauthDtlsDaoImpl class."+e.getMessage());
		}
		return hospitalName;
	}
	/**
     * ;
     * @param PatientVO patientVo
     * @return PatientVO
     * @function This Method is Used For Retrieving Enrollment Details of Employee/Pensioner card no
     */	
	
	
	public String getPatientScheme(String lStrCaseId)
	{
		String patientScheme=null;
		EhfCase ehfCase=new EhfCase();
		try
		{
		if(lStrCaseId!=null)
		{
		ehfCase=genericDao.findById(EhfCase.class,String.class,lStrCaseId);
		}
		if(ehfCase!=null)
		{
			patientScheme=ehfCase.getPatientScheme();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return patientScheme;
		
		
	}
	
	
	@SuppressWarnings({ "unused", "null" })
	public PatientVO retrieveCardDetails(PatientVO patientVo) throws Exception {
		PatientVO patientVO=new PatientVO();;
		EhfEnrollmentFamily ehfEnrollmentFamily=null;
		List<EhfEnrollmentFamily> ehfEnrollmentFamilyList=null;
		
		EhfJrnlstFamily ehfJrnlstFamily=null;
		List<EhfJrnlstFamily> ehfJrnlstFamilyList=null;
		
		String patientScheme=patientVo.getPatientScheme();
		List<String> statusList=new ArrayList<String>();
		try
		{
			List<GenericDAOQueryCriteria> criteriaList= new ArrayList<GenericDAOQueryCriteria>();
			
			
			
			if(patientScheme!=null && patientScheme.equalsIgnoreCase(config.getString("Scheme.JHS")))
			{
			if(config.getString("Card.ValidStatus")!=null && !"".equalsIgnoreCase(config.getString("Card.ValidStatus")))
			{
				String[] arrCardStatus= config.getString("Card.ValidStatus").split("~");
				if(arrCardStatus!=null)
				{
					for(int i=0; i<arrCardStatus.length; i++)
					{
						statusList.add(arrCardStatus[i]);
					}
				}
				criteriaList.add(new GenericDAOQueryCriteria("journalCradNo", patientVo.getCardNo(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				//criteriaList.add(new GenericDAOQueryCriteria("enrollStatus",statusList,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
				ehfJrnlstFamilyList= genericDao.findAllByCriteria(EhfJrnlstFamily.class, criteriaList);
			}
			
			if(ehfJrnlstFamilyList!=null && ehfJrnlstFamilyList.size()>0)
			{
				ehfJrnlstFamily= ehfJrnlstFamilyList.get(0);
				String enrollParntId=ehfJrnlstFamily.getJournalEnrollParntId();
				
				EhfEnrollment ehfEnrollment= genericDao.findById(EhfEnrollment.class, String.class, enrollParntId);
				if(ehfJrnlstFamily!=null)
				{
					patientVO=new PatientVO();
				patientVO.setPhoto(ehfJrnlstFamily.getEnrollPhoto());
				}
/*				if(ehfEnrollment!=null)
				{
					patientVO=new PatientVO();
					patientVO.setEmpCode(ehfEnrollment.getEmpCode());
					patientVO.setDateOfBirth(ehfEnrollmentFamily.getEnrollDob().toString());
					patientVO.setGender(Character.toString(ehfEnrollmentFamily.getEnrollGender()));
					patientVO.setFirstName(ehfEnrollmentFamily.getEnrollName());
					patientVO.setRelation(ehfEnrollmentFamily.getEnrollRelation());
					patientVO.setCaste(ehfEnrollment.getEmpCommu());
					patientVO.setContactNo(ehfEnrollment.getEmpHphone());
					patientVO.setAddr1(ehfEnrollment.getEmpHno());
					patientVO.setAddr2(ehfEnrollment.getEmpHstreetno());
					patientVO.setDistrictCode(ehfEnrollment.getEmpHdist());
					patientVO.setMdl_mpl(ehfEnrollment.getEmpHmandMunciSel());
					patientVO.setMandalCode(ehfEnrollment.getEmpHmandMunci());
					patientVO.setVillageCode(ehfEnrollment.getEmpHvillTwn());
					String slab=getSlabType(ehfEnrollment.getPayScale());
					if(slab!=null)
					{
						patientVO.setSlab(slab);
					}
					else
					{
						patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
					}
					if(ehfEnrollment.getScheme()!=null)
					{
						patientVO.setSchemeId(ehfEnrollment.getScheme());
					}
					else
					{
						patientVO.setSchemeId(config.getString("Scheme.ap"));
					}
					if(ehfEnrollment.getEmpHemail()!=null)
					{
						patientVO.seteMailId(ehfEnrollment.getEmpHemail());
					}
	
					if(patientVo.getCardNo().endsWith("01"))
					{
						patientVO.setMaritalStatus(ehfEnrollment.getEmpMaritalStat());
						if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) && patientVo.getCardType().equalsIgnoreCase("E"))
						{
							String service=ehfEnrollment.getService();
							String categoryName=ehfEnrollment.getServDsgn();
							String hod=ehfEnrollment.getDeptHod();
							if(ehfEnrollment.getDeptDesignation()!=null && !"".equals(ehfEnrollment.getDeptDesignation()))
							{
								criteriaList.removeAll(criteriaList);
								Long desgnId=Long.parseLong(ehfEnrollment.getDeptDesignation());
								
								criteriaList.add(new GenericDAOQueryCriteria("id.hod",hod,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.service",service,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.categoryName",categoryName,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.dsgnId",desgnId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								
								List<EhfDesignationMst> designationList= genericDao.findAllByCriteria(EhfDesignationMst.class, criteriaList);
								
								if(designationList!=null && designationList.size()>0)
								{
									patientVO.setOccupationCd(designationList.get(0).getDeptDesignation());
								}
								
							}
						}
						Map<String, Object> photoMap=new HashMap<String, Object>();
						photoMap.put("employeeParntId", enrollParntId);
						photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
						photoMap.put("attachactiveYN","Y");
						EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
						if(ehfEmployeeDocAttach!=null)
						{
							patientVO.setPhoto(ehfEmployeeDocAttach.getSavedName());
						}
					}
					else
					{
						patientVO.setPhoto(ehfEnrollmentFamily.getEnrollPhoto());
					}
				}*/
			}
			
			}
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			else
			{
			if(config.getString("Card.ValidStatus")!=null && !"".equalsIgnoreCase(config.getString("Card.ValidStatus")))
			{
				String[] arrCardStatus= config.getString("Card.ValidStatus").split("~");
				if(arrCardStatus!=null)
				{
					for(int i=0; i<arrCardStatus.length; i++)
					{
						statusList.add(arrCardStatus[i]);
					}
				}
				criteriaList.add(new GenericDAOQueryCriteria("ehfCardNo", patientVo.getCardNo(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				//criteriaList.add(new GenericDAOQueryCriteria("enrollStatus",statusList,GenericDAOQueryCriteria.CriteriaOperator.NOT_IN));
				ehfEnrollmentFamilyList= genericDao.findAllByCriteria(EhfEnrollmentFamily.class, criteriaList);
			}
			
			if(ehfEnrollmentFamilyList!=null && ehfEnrollmentFamilyList.size()>0)
			{
				ehfEnrollmentFamily= ehfEnrollmentFamilyList.get(0);
				String enrollParntId=ehfEnrollmentFamily.getEnrollPrntId();
				
				EhfEnrollment ehfEnrollment= genericDao.findById(EhfEnrollment.class, String.class, enrollParntId);
				
				if(ehfEnrollment!=null)
				{
					patientVO=new PatientVO();
					patientVO.setEmpCode(ehfEnrollment.getEmpCode());
					patientVO.setDateOfBirth(ehfEnrollmentFamily.getEnrollDob().toString());
					patientVO.setGender(Character.toString(ehfEnrollmentFamily.getEnrollGender()));
					patientVO.setFirstName(ehfEnrollmentFamily.getEnrollName());
					patientVO.setRelation(ehfEnrollmentFamily.getEnrollRelation());
					patientVO.setCaste(ehfEnrollment.getEmpCommu());
					patientVO.setContactNo(ehfEnrollment.getEmpHphone());
					patientVO.setAddr1(ehfEnrollment.getEmpHno());
					patientVO.setAddr2(ehfEnrollment.getEmpHstreetno());
					patientVO.setDistrictCode(ehfEnrollment.getEmpHdist());
					patientVO.setMdl_mpl(ehfEnrollment.getEmpHmandMunciSel());
					patientVO.setMandalCode(ehfEnrollment.getEmpHmandMunci());
					patientVO.setVillageCode(ehfEnrollment.getEmpHvillTwn());
					String slab=getSlabType(ehfEnrollment.getPayScale());
					if(slab!=null)
					{
						patientVO.setSlab(slab);
					}
					else
					{
						patientVO.setSlab(config.getString("Slab.SemiPrivateWard"));
					}
					if(ehfEnrollment.getScheme()!=null)
					{
						patientVO.setSchemeId(ehfEnrollment.getScheme());
					}
					else
					{
						patientVO.setSchemeId(config.getString("Scheme.ap"));
					}
					if(ehfEnrollment.getEmpHemail()!=null)
					{
						patientVO.seteMailId(ehfEnrollment.getEmpHemail());
					}
	
					if(patientVo.getCardNo().endsWith("01"))
					{
						patientVO.setMaritalStatus(ehfEnrollment.getEmpMaritalStat());
						if(patientVo.getCardType()!=null && !"".equalsIgnoreCase(patientVo.getCardType()) && patientVo.getCardType().equalsIgnoreCase("E"))
						{
							String service=ehfEnrollment.getService();
							String categoryName=ehfEnrollment.getServDsgn();
							String hod=ehfEnrollment.getDeptHod();
							if(ehfEnrollment.getDeptDesignation()!=null && !"".equals(ehfEnrollment.getDeptDesignation()))
							{
								criteriaList.removeAll(criteriaList);
								Long desgnId=Long.parseLong(ehfEnrollment.getDeptDesignation());
								
								criteriaList.add(new GenericDAOQueryCriteria("id.hod",hod,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.service",service,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.categoryName",categoryName,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								criteriaList.add(new GenericDAOQueryCriteria("id.dsgnId",desgnId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
								
								List<EhfDesignationMst> designationList= genericDao.findAllByCriteria(EhfDesignationMst.class, criteriaList);
								
								if(designationList!=null && designationList.size()>0)
								{
									patientVO.setOccupationCd(designationList.get(0).getDeptDesignation());
								}
								
							}
						}
					}
				}
				
				if(patientVo.getCardNo().endsWith("01"))
					{
						Map<String, Object> photoMap=new HashMap<String, Object>();
						photoMap.put("employeeParntId", enrollParntId);
						photoMap.put("attachType", config.getString("FamilyHeadPhoto.AttachType"));
						photoMap.put("attachactiveYN","Y");
						EhfEmployeeDocAttach ehfEmployeeDocAttach=genericDao.findFirstByPropertyMatch(EhfEmployeeDocAttach.class,photoMap);
						if(ehfEmployeeDocAttach!=null)
						{
							patientVO.setPhoto(ehfEmployeeDocAttach.getSavedName());
						}
					}
				else
					{
						patientVO.setPhoto(ehfEnrollmentFamily.getEnrollPhoto());
					}
			}
			}
		}
		catch(Exception e)
		{
//			glogger.error("Exception Occurred in retrieveCardDetails() in PreauthDtlsDaoImpl class."+e.getMessage());
			e.printStackTrace();
		}
		
		return patientVO;
	}
	
	public List<LabelBean> getDrugSubList(String drugTypeCode) {
		List<LabelBean> drugSubList = null;	
    	StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugSubTypeCode as  ID , edm.drugSubTypeName as VALUE from EhfmDrugsMst edm where edm.id.drugTypeCode='"+drugTypeCode+"' and edm.activeYN='Y' order by edm.drugSubTypeName asc");
			drugSubList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
    	{
    		glogger.error("Exception Occurred in getDrugSubList() in PatientDaoImpl class."+e.getMessage());
    	}
		
		return drugSubList;
	}
	/**
     * ;
     * @param String drugCode
     * @return List<String> drugDetailsList
     * @function This Method is Used For getting Drug Details From EhfmDrugsMst(EHFM_DRUGS_MST)
     */
	public String getDrugDetails(String drugCode) {
		String drugDetails="";
    	Iterator<EhfmDrugsMst> drugItr=null;
    	EhfmDrugsMst ehfmDrugsMst=null;
    	try
    	{
    		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    		criteriaList.add(new GenericDAOQueryCriteria("activeYN",config.getString("ActiveYn"),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("id.drugCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmDrugsMst> drugsList = genericDao.findAllByCriteria(EhfmDrugsMst.class, criteriaList);
    		drugItr=drugsList.iterator();
    		if(drugItr.hasNext())
    		{
    			ehfmDrugsMst=(EhfmDrugsMst)drugItr.next();   
    			drugDetails=ehfmDrugsMst.getRouteCode()+"("+ehfmDrugsMst.getRouteName()+")~"+ehfmDrugsMst.getStrength();
    			criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    			criteriaList.add(new GenericDAOQueryCriteria("id.inpCode",drugCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
    			List<EhfSsrMedinpData> lstEhfSsrMedinpData = genericDao.findAllByCriteria(EhfSsrMedinpData.class, criteriaList);
    			if(lstEhfSsrMedinpData != null && lstEhfSsrMedinpData.size() > 0)
    			drugDetails= drugDetails+"~"+lstEhfSsrMedinpData.get(0).getUnitPrice();
    		}
    	}
    	catch(Exception e)
    	{
    		glogger.error("Exception Occurred in getDrugDetails() in PatientDaoImpl class."+e.getMessage());
    	}
		return drugDetails;
	}
	
	private String getSlabType(String payGrade) throws Exception {
		List<LabelBean> payGradeList= new ArrayList<LabelBean>();
		String slabType=null;
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct a.slab as VALUE from EhfmPayGradeMst a where a.id.payGrade='"+payGrade+"'");     
			payGradeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
			if(payGradeList!=null && payGradeList.size()>0)
			{
				slabType=payGradeList.get(0).getVALUE();
			}
		}
		catch(Exception e)
		{
			glogger.error("Exception Occurred in getSlabType() in PreauthDtlsDaoImpl.class."+e.getMessage());
		}
		return slabType;
	}  

	public CasesSearchVO getCaseCommonDtls(String caseId) {
		StringBuffer queryBuff = new StringBuffer();
		List<CasesSearchVO> resLst = null;CasesSearchVO caseSearchVO = new CasesSearchVO();
		try
		{		
			queryBuff.append(" select ec.caseNo  as caseNo, ec.claimNo as claimNo,p.name as patientName, p.cardNo as wapNo, a.cmbDtlName as claimStatus,ec.lstUpdDt as lstUpdDt, ec.caseRegnDate as inpatientCaseSubDt,  "); 
			queryBuff.append(" ec.csPreauthDt as csPreauthDt, ec.preauthAprvDate as preauthAprvDate, ec.csDisDt as csDisDt,ec.csDeathDt as csDeathDt,nvl(ec.csClAmount,0) as claimAmt ,ec.clmSubDt as clmSubDt, ec.csTransId as csTransId,ec.csTransDt as csTransDt, ec.csRemarks as csRemarks,ec.csSbhDt as csSbhDt");
			queryBuff.append(" from EhfPatient p ,EhfCase ec ,EhfmCmbDtls a where p.patientId = ec.casePatientNo and a.cmbDtlId = ec.caseStatus and ec.caseId = '"+caseId+"' ");
			resLst = genericDao.executeHQLQueryList(CasesSearchVO.class, queryBuff.toString());
			if(resLst!=null && resLst.size()>0)
				caseSearchVO = resLst.get(0);
		}
		catch(Exception e)
		{
			glogger.error("Error in PreauthDtlsDao getCaseCommonDtls():"+e.getMessage());
		}
		return caseSearchVO;		
	}
	
public PatientVO getTelephonicIntimationDtls(PatientVO patientVo) { 
		
		PatientVO patientVO = new PatientVO();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat dateformat1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SessionFactory factory=null;
		Session session=null;
		try {
			factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
			Query hQuery;
			StringBuffer query = new StringBuffer();
             query.append(" from EhfTelephonicRegn trr,EhfmHospitals EH ,EhfmLocations EL");
             query.append(" where trr.hospId=EH.hospId and EL.id.locId=trr.districtCode " );
             query.append(" and trr.telephonicId='"+patientVo.getTelephonicId()+"'");
             hQuery=session.createQuery(query.toString());
             Iterator ite = hQuery.list().iterator();
    			while(ite.hasNext())
    			{
				Object[] pair = (Object[]) ite.next();
				EhfTelephonicRegn ehfmTelephonicRegn=(EhfTelephonicRegn)pair[0];
				EhfmHospitals asrimHosp=(EhfmHospitals)pair[1];
				patientVO.setTelephonicId(ehfmTelephonicRegn.getTelephonicId());
				patientVO.setCardNo(ehfmTelephonicRegn.getCardNo());
				patientVO.setCardType(ehfmTelephonicRegn.getCardType());
				patientVO.setEmpCode(ehfmTelephonicRegn.getEmployeeNo());
				if(ehfmTelephonicRegn.getName()!=null && !ehfmTelephonicRegn.getName().equalsIgnoreCase(""))
				patientVO.setFirstName(ehfmTelephonicRegn.getName());
				else
					patientVO.setFirstName("NA");
				patientVO.setGender(ehfmTelephonicRegn.getGender());
				if(ehfmTelephonicRegn.getAge()!=null)
				patientVO.setAge(ehfmTelephonicRegn.getAge().toString());
				else
					patientVO.setAge("NA");
				if(ehfmTelephonicRegn.getAgeDays()!=null)
				patientVO.setAgeDays(ehfmTelephonicRegn.getAgeDays().toString());
				else
					patientVO.setAgeDays("NA");
				if(ehfmTelephonicRegn.getAgeMonths()!=null)
				patientVO.setAgeMonths(ehfmTelephonicRegn.getAgeMonths().toString());
				else
					patientVO.setAgeMonths("NA");
				patientVO.setRelation(ehfmTelephonicRegn.getRelation());
				if(ehfmTelephonicRegn.getRelation()!=null && !ehfmTelephonicRegn.getRelation().equalsIgnoreCase("-1")){
					EhfmRelationMst relationmst = genericDao.findById(EhfmRelationMst.class, Integer.class,Integer.parseInt(ehfmTelephonicRegn.getRelation()));
					if(relationmst!=null)
					patientVO.setRelOthers(relationmst.getRelationName());					
				}
				else
					patientVO.setRelOthers("NA");
					
				patientVO.setOccupationCd(ehfmTelephonicRegn.getOccupationCd());
				patientVO.setOccOthers(ehfmTelephonicRegn.getOccupationCd());
				patientVO.setSlab(ehfmTelephonicRegn.getSlab());
				//setting card address
				if(ehfmTelephonicRegn.gethNo()!=null)
					patientVO.setAddr1(ehfmTelephonicRegn.gethNo());
				else
					patientVO.setAddr1("NA");
				if(ehfmTelephonicRegn.getStreet()!=null)
					patientVO.setAddr2(ehfmTelephonicRegn.getStreet());
				else
					patientVO.setAddr2("NA");
				if(ehfmTelephonicRegn.getState()!=null && !ehfmTelephonicRegn.getState().equalsIgnoreCase("-1")){

					patientVO.setState(ehfmTelephonicRegn.getState());
				}
				if(ehfmTelephonicRegn.getDistrictCode()!=null && !ehfmTelephonicRegn.getDistrictCode().equalsIgnoreCase("-1")){

					patientVO.setDistrictCode(ehfmTelephonicRegn.getDistrictCode());
				}
				if(ehfmTelephonicRegn.getMdl_mpl()!=null)
					patientVO.setMdl_mpl(ehfmTelephonicRegn.getMdl_mpl());

				if(ehfmTelephonicRegn.getMandalCode()!=null && !ehfmTelephonicRegn.getMandalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setMandalCode(ehfmTelephonicRegn.getMandalCode());

					if(ehfmTelephonicRegn.getVillageCode()!=null && !ehfmTelephonicRegn.getVillageCode().equalsIgnoreCase("-1"))
					{
						patientVO.setVillageCode(ehfmTelephonicRegn.getVillageCode());
					}
					else
						patientVO.setVillageCode("NA");
				}
				else
				{
					patientVO.setMandalCode("NA");
					patientVO.setVillageCode("NA");
				}
				if(ehfmTelephonicRegn.getPinCode()!=null)
					patientVO.setPincode(ehfmTelephonicRegn.getPinCode());
				else
					patientVO.setPincode("NA");
                
                //Setting Communication address
                
                if(ehfmTelephonicRegn.getcHouseNo()!=null)
                	patientVO.setPermAddr1(ehfmTelephonicRegn.getcHouseNo());
                else
                	patientVO.setPermAddr1("NA");
                if(ehfmTelephonicRegn.getcStreet()!=null)
                	patientVO.setPermAddr2(ehfmTelephonicRegn.getcStreet());
                else
                	patientVO.setPermAddr2("NA");
				if(ehfmTelephonicRegn.getcState()!=null && !ehfmTelephonicRegn.getcState().equalsIgnoreCase("-1"))
				{
					patientVO.setC_state(ehfmTelephonicRegn.getcState());
				}
                if(ehfmTelephonicRegn.getcDistrictCode()!=null && !ehfmTelephonicRegn.getcDistrictCode().equalsIgnoreCase("-1"))
                {
                	patientVO.setC_district_code(ehfmTelephonicRegn.getcDistrictCode());
                }
                if(ehfmTelephonicRegn.getC_mdl_mpl()!=null)
                	patientVO.setC_mdl_mpl(ehfmTelephonicRegn.getC_mdl_mpl());

                if(ehfmTelephonicRegn.getcMandalCode()!=null && !ehfmTelephonicRegn.getcMandalCode().equalsIgnoreCase("-1"))
                {
                	patientVO.setC_mandal_code(ehfmTelephonicRegn.getcMandalCode());
                	if(ehfmTelephonicRegn.getcVillageCode()!=null && !ehfmTelephonicRegn.getcVillageCode().equalsIgnoreCase("-1"))
                	{
                		patientVO.setC_village_code(ehfmTelephonicRegn.getcVillageCode());
                	}
                	else
                		patientVO.setC_village_code("NA");
                }
                else
                {
                	patientVO.setC_mandal_code("NA");
                	patientVO.setC_village_code("NA");
                }
                if(ehfmTelephonicRegn.getcPinCode()!=null)
                	patientVO.setC_pin_code(ehfmTelephonicRegn.getcPinCode());
                else
                	patientVO.setC_pin_code("NA");
                if(ehfmTelephonicRegn.getContactNo()!=null)
                	patientVO.setContactNo(ehfmTelephonicRegn.getContactNo().toString());	
                else
                	patientVO.setContactNo("NA");
                patientVO.setRegHospId(ehfmTelephonicRegn.getHospId());
                patientVO.setFamilyHead(ehfmTelephonicRegn.getFamilyHead());
                if(ehfmTelephonicRegn.getEnrollDob()!=null)
                	patientVO.setDateOfBirth(dateformat.format(ehfmTelephonicRegn.getEnrollDob()));
                if(ehfmTelephonicRegn.getCardIssueDt()!=null && !ehfmTelephonicRegn.getCardIssueDt().equals(""))
                {
                patientVO.setCardIssueDt(dateformat.format(ehfmTelephonicRegn.getCardIssueDt()));
                }
                patientVO.setCaste(ehfmTelephonicRegn.getCaste());
                patientVO.setTeleCallerDesgn(ehfmTelephonicRegn.getCallerDesig());
                patientVO.setTeleCallerName(ehfmTelephonicRegn.getCallerName());
                patientVO.setTeleDocDesgn(ehfmTelephonicRegn.getDoctorDesig());
                patientVO.setTeleDocName(ehfmTelephonicRegn.getDoctorName());
                patientVO.setTeleDocPhoneNo(ehfmTelephonicRegn.getDocMobileNo());
                patientVO.setTeleDocremarks(ehfmTelephonicRegn.getDocRemarks());
                patientVO.setTelePhoneNo(ehfmTelephonicRegn.getCallerMobileNo());
                patientVO.setCrtDt(dateformat1.format(ehfmTelephonicRegn.getCrtDt()));
                patientVO.setIndication(ehfmTelephonicRegn.getTeleIntimRemarks());
                patientVO.setRegHospDt(asrimHosp.getHospName());
				
                //Setting Therapy details
                if(ehfmTelephonicRegn.getAsriCatCode()!=null)
                {
                	EhfmSpecialities ehfmSpecialities=genericDao.findById(EhfmSpecialities.class,String.class,ehfmTelephonicRegn.getAsriCatCode());
                	if(ehfmSpecialities!=null)
                	{
                		patientVO.setAsriCatCode(ehfmSpecialities.getDisMainName());
                	}
                	else
                		patientVO.setAsriCatCode("NA");
                }
				if(ehfmTelephonicRegn.getICDCatCode()!=null && !ehfmTelephonicRegn.getICDCatCode().equalsIgnoreCase("-1"))
				{
					EhfmTherapyCatMstId ehfmTherapyCatMstId=new EhfmTherapyCatMstId(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDCatCode());
					EhfmTherapyCatMst ehfmTherapyCatMst=genericDao.findById(EhfmTherapyCatMst.class,EhfmTherapyCatMstId.class,ehfmTherapyCatMstId);
					if(ehfmTherapyCatMst!=null)
						patientVO.setICDcatName(ehfmTherapyCatMst.getIcdCatName());
					else
						patientVO.setICDcatName("NA");
				}
				
				if(ehfmTelephonicRegn.getICDProcCode()!=null && !ehfmTelephonicRegn.getICDProcCode().equalsIgnoreCase("-1"))
				{
					patientVO.setICDprocName(ehfmTelephonicRegn.getICDProcCode());
					EhfmTherapyProcMstId ehfmTherapyProcMstId=new EhfmTherapyProcMstId(ehfmTelephonicRegn.getAsriCatCode(),ehfmTelephonicRegn.getICDProcCode(), null,null);
					EhfmTherapyProcMst ehfmTherapyProcMst=genericDao.findById(EhfmTherapyProcMst.class,EhfmTherapyProcMstId.class,ehfmTherapyProcMstId);
					if(ehfmTherapyProcMst!=null)
					{
						patientVO.setICDprocName(ehfmTherapyProcMst.getProcName());
						patientVO.setTherapyCatId(ehfmTherapyProcMst.getId().getIcdProcCode());
					}
					else
						patientVO.setICDprocName("NA");
				}
				//Setting Diagnosis Details
				if(ehfmTelephonicRegn.getDiaType()!=null && !ehfmTelephonicRegn.getDiaType().equalsIgnoreCase("-1"))
				{
					patientVO.setDiagnosisType(ehfmTelephonicRegn.getDiaType());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
	        		criteriaList.add(new GenericDAOQueryCriteria("id.diagnosisCode",ehfmTelephonicRegn.getDiaType(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    			List<EhfmDiagnosisMst> ehfmDiagnosisMstList = genericDao.findAllByCriteria(EhfmDiagnosisMst.class, criteriaList);
	    			for(EhfmDiagnosisMst ehfmDiagnosisMst:ehfmDiagnosisMstList)
	    			{
	    				patientVO.setDiagnosisType(ehfmDiagnosisMst.getDiagnosisName());
	    			}
				}
				if(ehfmTelephonicRegn.getDiaMainCatCode()!=null && !ehfmTelephonicRegn.getDiaMainCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setMainCatName(ehfmTelephonicRegn.getDiaMainCatCode());
					EhfmDiagMainCatMst ehfmDiagMainCatMst=genericDao.findById(EhfmDiagMainCatMst.class,EhfmDiagMainCatMstId.class,new EhfmDiagMainCatMstId(ehfmTelephonicRegn.getDiaMainCatCode(),ehfmTelephonicRegn.getDiaType()));
					if(ehfmDiagMainCatMst!=null)
						patientVO.setMainCatName(ehfmDiagMainCatMst.getMainCatName());
					else
						patientVO.setMainCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaCatCode()!=null && !ehfmTelephonicRegn.getDiaCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setCatName(ehfmTelephonicRegn.getDiaCatCode());
					EhfmDiagCategoryMst ehfmDiagCategoryMst=genericDao.findById(EhfmDiagCategoryMst.class,EhfmDiagCategoryMstId.class,new EhfmDiagCategoryMstId(ehfmTelephonicRegn.getDiaCatCode(),ehfmTelephonicRegn.getDiaMainCatCode()));
					if(ehfmDiagCategoryMst!=null)
						patientVO.setCatName(ehfmDiagCategoryMst.getCatName());
					else
						patientVO.setCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaSubCatCode()!=null && !ehfmTelephonicRegn.getDiaSubCatCode().equalsIgnoreCase("-1"))
				{
					patientVO.setSubCatName(ehfmTelephonicRegn.getDiaSubCatCode());
					EhfmDiagSubCatMst ehfmDiagSubCatMst=genericDao.findById(EhfmDiagSubCatMst.class,EhfmDiagSubCatMstId.class,new EhfmDiagSubCatMstId(ehfmTelephonicRegn.getDiaSubCatCode(),ehfmTelephonicRegn.getDiaCatCode()));
					if(ehfmDiagSubCatMst!=null)
						patientVO.setSubCatName(ehfmDiagSubCatMst.getSubCatName());
					else
						patientVO.setSubCatName("NA");
				}
				if(ehfmTelephonicRegn.getDiaDiseaseCode()!=null && !ehfmTelephonicRegn.getDiaDiseaseCode().equalsIgnoreCase("-1"))
				{
					patientVO.setDiseaseName(ehfmTelephonicRegn.getDiaDiseaseCode());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("id.subCatCode",ehfmTelephonicRegn.getDiaSubCatCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        		criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfmTelephonicRegn.getDiaDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    			List<EhfmDiagDiseaseMst> ehfmDiagDiseaseMstList = genericDao.findAllByCriteria(EhfmDiagDiseaseMst.class, criteriaList);
	    			for(EhfmDiagDiseaseMst ehfmDiagDiseaseMst:ehfmDiagDiseaseMstList)
	    			{
	    				patientVO.setDiseaseName(ehfmDiagDiseaseMst.getDiseaseName());
	    			}
				}
				if(ehfmTelephonicRegn.getDiaDisAnatomicalCode()!=null && !ehfmTelephonicRegn.getDiaDisAnatomicalCode().equalsIgnoreCase("-1"))
				{
					patientVO.setDisAnatomicalName(ehfmTelephonicRegn.getDiaDisAnatomicalCode());
					List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
					criteriaList.add(new GenericDAOQueryCriteria("id.disAnatomicalCode",ehfmTelephonicRegn.getDiaDisAnatomicalCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	        		criteriaList.add(new GenericDAOQueryCriteria("id.diseaseCode",ehfmTelephonicRegn.getDiaDiseaseCode(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
	    			List<EhfmDiagDisAnatomicalMst> ehfmDiagDisAnatomicalMstList = genericDao.findAllByCriteria(EhfmDiagDisAnatomicalMst.class, criteriaList);
	    			for(EhfmDiagDisAnatomicalMst ehfmDiagDisAnatomicalMst:ehfmDiagDisAnatomicalMstList)
	    			{
	    				patientVO.setDisAnatomicalName(ehfmDiagDisAnatomicalMst.getDisAnatomicalName());
	    			}
				}
				patientVO.setTeleDocremarks(ehfmTelephonicRegn.getRemarksProcedure()+"~"+ehfmTelephonicRegn.getRemarksDiagnosis());
				}
		} 
		catch (Exception e) {
			glogger.error("Error in PreauthDtlsDao getCaseCommonDtls():"+e.getMessage());
			return patientVO;
		}  
		finally
		{
			session.close();
			factory.close();
		}
		return patientVO;

	}

	/**
	 * @author Kalyan
	 * @param ClassPath-Result class after Query Execution and Query
	 * @method This method is used to Execute HQL Query in Dao
	 * @return Map That Contains Result class path and Result Object  
	 */
	@Override
	public Map<String,Object> executeNormalQuery(String classPath,String query)
		{
		 	Map<String,Object> returnMap=new HashMap<String,Object>();
		 	try
		 		{
		 			List<?> retLst=genericDao.executeHQLQueryList(Class.forName(classPath), query);
		 			if(retLst!=null)
		 				returnMap.put(classPath, retLst);
		 		}
		 	catch(Exception e)
		 		{
		 			e.printStackTrace();
//		 			glogger.error("Exception Occured in executeNormalQuery method of PreauthDtlsDao"+e.getMessage());
		 		}
		 	return returnMap;
		}
	
	@Override
	public PreauthVO getPatientFullDtls(String lStrCaseId, String patientId,String fromDisp) {
		PreauthVO preauthVO = new PreauthVO();
		String dentalFlg="N";
		EhfPatient ehfPatient=genericDao.findById(EhfPatient.class,String.class,patientId);
		EhfPatientHospDiagnosis ehfPatientHospDiagnosis = genericDao.findById(EhfPatientHospDiagnosis.class, String.class,patientId);
		if(ehfPatientHospDiagnosis != null)
		{
			preauthVO.setPresentIllness(ehfPatientHospDiagnosis.getHistoryIllness());	
			preauthVO.setDocName(ehfPatientHospDiagnosis.getDoctName());
			preauthVO.setDoctorDtls(ehfPatientHospDiagnosis.getDoctId());
			preauthVO.setUnitName(ehfPatientHospDiagnosis.getUnitName());
			preauthVO.setUnitHodName(ehfPatientHospDiagnosis.getUnitHodName());
			preauthVO.setDocQual(ehfPatientHospDiagnosis.getDoctQuali());
			preauthVO.setDocMobNo(ehfPatientHospDiagnosis.getDoctMobNo());
			preauthVO.setDocReg(ehfPatientHospDiagnosis.getDoctRegNo());;
			preauthVO.setDocType(ehfPatientHospDiagnosis.getDoctType());
			preauthVO.setAdmType(ehfPatientHospDiagnosis.getCaseAdmType());
			preauthVO.setLegalCaseCheck(ehfPatientHospDiagnosis.getLegalCaseCheck());
			preauthVO.setLegalCaseNo(ehfPatientHospDiagnosis.getLegalCaseNo());
			preauthVO.setPoliceStatName(ehfPatientHospDiagnosis.getPoliceStatName());
			preauthVO.setDate(ehfPatientHospDiagnosis.getCasePropSurgDate());
			preauthVO.setOtherDiagName(ehfPatientHospDiagnosis.getOtherDiagnosisName());
			if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("IP"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
			}
			else if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("OP"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getOpRemarks());
			}
			else if(ehfPatient.getPatientIpop()!=null && ehfPatient.getPatientIpop().equals("DOP"))
			{
				preauthVO.setRemarks(ehfPatientHospDiagnosis.getIpCaseRemarks());
			}
			preauthVO.setComplaintType(ehfPatientHospDiagnosis.getComplaintType());
			preauthVO.setPatComplaint(ehfPatientHospDiagnosis.getPatientComplaint());
			preauthVO.setPastIllness(ehfPatientHospDiagnosis.getPastHistory());
			preauthVO.setExamFindg(ehfPatientHospDiagnosis.getExamntnFindings());
			
			if(ehfPatientHospDiagnosis.getPastHistoryOthr()!=null && !ehfPatientHospDiagnosis.getPastHistoryOthr().equalsIgnoreCase(""))
				preauthVO.setPastIllenesOthr(ehfPatientHospDiagnosis.getPastHistoryOthr());
			if(ehfPatientHospDiagnosis.getPastHistoryCancers()!=null && !ehfPatientHospDiagnosis.getPastHistoryCancers().equalsIgnoreCase(""))
				preauthVO.setPastIllenesCancers(ehfPatientHospDiagnosis.getPastHistoryCancers());
			if(ehfPatientHospDiagnosis.getPastHistoryEndDis()!=null && !ehfPatientHospDiagnosis.getPastHistoryEndDis().equalsIgnoreCase(""))
				preauthVO.setPastIllenesEndDis(ehfPatientHospDiagnosis.getPastHistoryEndDis());
			if(ehfPatientHospDiagnosis.getPastHistorySurg()!=null && !ehfPatientHospDiagnosis.getPastHistorySurg().equalsIgnoreCase(""))
				preauthVO.setPastIllenesSurg(ehfPatientHospDiagnosis.getPastHistorySurg());
			
			preauthVO.setPersonalHis( ehfPatientHospDiagnosis.getPersonalHistory());
			
			
			if(ehfPatientHospDiagnosis.getOtherComplaintName()!=null && !("").equalsIgnoreCase(ehfPatientHospDiagnosis.getOtherComplaintName()))
			{
				preauthVO.setOtherComplaintName(ehfPatientHospDiagnosis.getOtherComplaintName());
			}
			EhfPatientPersonalHistory ehfPatientPersonalHistory= genericDao.findById(EhfPatientPersonalHistory.class, String.class, patientId);
			List<String> lstPerHis = new ArrayList<String>();
			if(ehfPatientPersonalHistory != null)
			{
				if(ehfPatientPersonalHistory.getAppetite() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getAppetite());	
				}
				if(ehfPatientPersonalHistory.getDiet() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getDiet());	
				}
				if(ehfPatientPersonalHistory.getBowels() !=null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getBowels());		
				}
				if(ehfPatientPersonalHistory.getNutrition() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getNutrition());
				}
				if(ehfPatientPersonalHistory.getKnownAllergies() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getKnownAllergies());	
					if(ehfPatientPersonalHistory.getKnownAllergies().contains("PR5.1"))
						preauthVO.setTestKnown(ehfPatientPersonalHistory.getKnownAllergies());
				}
				if(ehfPatientPersonalHistory.getAddictions() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getAddictions());	
					if(ehfPatientPersonalHistory.getAddictions().contains("PR6.1"))
					preauthVO.setTest(ehfPatientPersonalHistory.getAddictions());
				}
				if(ehfPatientPersonalHistory.getMaritalStatus() != null)
				{
					lstPerHis.add(ehfPatientPersonalHistory.getMaritalStatus());	
				}
				if(ehfPatientPersonalHistory.getDrugHst() != null)
				{
					preauthVO.setDrugHst(ehfPatientPersonalHistory.getDrugHst());
				}
				if(ehfPatientPersonalHistory.getDrugHstVal() != null)
				{
					preauthVO.setDrugHstVal(ehfPatientPersonalHistory.getDrugHstVal());
				}
			}
			preauthVO.setLstPerHis(lstPerHis);
			
			//pavan
			//retreving the dental case sheet medical history
			
			EhfDentalOtherExaminations ehfDentalOtherExaminations1 = genericDao.findById(EhfDentalOtherExaminations.class, String.class, patientId);
			if(ehfDentalOtherExaminations1!=null)
			{
					if(ehfDentalOtherExaminations1.getMedicalHistory()!=null)
					{
						String medicaldtls=ehfDentalOtherExaminations1.getMedicalHistory();
						preauthVO.setMedicalDtlsidStr(medicaldtls);
						
					}
					if(ehfDentalOtherExaminations1.getMedicalHistoryOthr()!=null)
					preauthVO.setShowMedicalTextval(ehfDentalOtherExaminations1.getMedicalHistoryOthr());
					if(ehfDentalOtherExaminations1.getClinicalProbingDepth()!=null)
					preauthVO.setProbingdepth(ehfDentalOtherExaminations1.getClinicalProbingDepth());
					if(ehfDentalOtherExaminations1.getPreviousDentalTreatment()!=null)
						preauthVO.setPreviousDentalTreatment(ehfDentalOtherExaminations1.getPreviousDentalTreatment());
					if(ehfDentalOtherExaminations1.getOcclusion()!=null)
						preauthVO.setOcclusion(ehfDentalOtherExaminations1.getOcclusion());
					if(ehfDentalOtherExaminations1.getOcclusionDivii()!=null)
						preauthVO.setOcclusionType(ehfDentalOtherExaminations1.getOcclusionDivii());
					if(ehfDentalOtherExaminations1.getOcclusionOther()!=null)
						preauthVO.setOcclusionOther(ehfDentalOtherExaminations1.getOcclusionOther());
			}
			
			
			EhfDentalOralExaminations ehfDentalOralExaminations = genericDao.findById(EhfDentalOralExaminations.class, String.class, patientId);
			if(ehfDentalOralExaminations!=null)
			{
					if(ehfDentalOralExaminations.getExtraOralCheck()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getExtraOralCheck();
						preauthVO.setExtraoralStr(medicaldtls);
						String[] requires =medicaldtls.split("~");
						if(requires.length>0)
							{
								String[] arr=new String[requires.length];
								for(int i=0 ; i<requires.length; i++)
									{
										arr[i]=requires[i];
									}
								preauthVO.setExtraoral(arr);
							}
					}
					
					if(ehfDentalOralExaminations.getIntraOralCheck()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getIntraOralCheck();
						preauthVO.setIntraoralStr(medicaldtls);
						String[] requires =medicaldtls.split("~");
						if(requires.length>0)
							{
								String[] arr=new String[requires.length];
								for(int i=0 ; i<requires.length; i++)
									{
										arr[i]=requires[i];
									}
								preauthVO.setIntraoral(arr);
							}	
					}
					
					if(ehfDentalOralExaminations.getLymphadenopathySub()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getLymphadenopathySub();
						String[] requires =medicaldtls.split("~");
						if(requires.length>0)
							{
								String[] arr=new String[requires.length];
								for(int i=0 ; i<requires.length; i++)
									{
										arr[i]=requires[i];
									}
								String rt=medicaldtls.substring(0,2);
								if("CH".equalsIgnoreCase(rt))
								{
									char rt1=(medicaldtls.charAt(3));
									boolean retval =Character.isDigit(rt1);
									if(!retval)
										preauthVO.setSubdentalrltxt(medicaldtls);
									   else
									   {
									    preauthVO.setSubdentalrl0Str(medicaldtls);
									    preauthVO.setSubdentalrl0(arr);
									   }
								}
								else
								{
									char rt1=(medicaldtls.charAt(2));
									boolean retval =Character.isDigit(rt1);
									if(!retval)
										preauthVO.setSubdentalrltxt(medicaldtls);
									   else
									   {
									    preauthVO.setSubdentalrl0Str(medicaldtls);
									    preauthVO.setSubdentalrl0(arr);
									   }
								}
							}	
					}
					if(ehfDentalOralExaminations.getJawsSub()!=null)
					{
						String medicaldtls=ehfDentalOralExaminations.getJawsSub();
						String rt=medicaldtls.substring(0,2);
						if("CH".equalsIgnoreCase(rt))
						{
							char rt2=(medicaldtls.charAt(2));
						   boolean retval =Character.isDigit(rt2);
						    if(!retval)
							preauthVO.setSubdentaljawstxt(medicaldtls);
						    else
						    {
							preauthVO.setSubdentaljaws1(medicaldtls);
						    }
						}
						else
						{
							  char rt2=(medicaldtls.charAt(2));
							   boolean retval =Character.isDigit(rt2);
							    if(!retval)
								preauthVO.setSubdentaljawstxt(medicaldtls);
							    else
							    {
								preauthVO.setSubdentaljaws1(medicaldtls);
							    }
						}
					}
					
					if(ehfDentalOralExaminations.getLymphadenopathy()!=null)
						preauthVO.setSubdental0(ehfDentalOralExaminations.getLymphadenopathy());
					if(ehfDentalOralExaminations.getJaws()!=null)
						preauthVO.setSubdental1(ehfDentalOralExaminations.getJaws());
					if(ehfDentalOralExaminations.getTmj()!=null)
						preauthVO.setSubdental2(ehfDentalOralExaminations.getTmj());
					if(ehfDentalOralExaminations.getFace()!=null)
						preauthVO.setSubdental3(ehfDentalOralExaminations.getFace());
					if(ehfDentalOralExaminations.getHardPalate()!=null)
						preauthVO.setDntsublistoral0(ehfDentalOralExaminations.getHardPalate());
					if(ehfDentalOralExaminations.getSoftPalate()!=null)
						preauthVO.setDntsublistoral1(ehfDentalOralExaminations.getSoftPalate());
					if(ehfDentalOralExaminations.getFloorMouth()!=null)
						preauthVO.setDntsublistoral2(ehfDentalOralExaminations.getFloorMouth());
					if(ehfDentalOralExaminations.getTongue()!=null)
						preauthVO.setDntsublistoral3(ehfDentalOralExaminations.getTongue());
					if(ehfDentalOralExaminations.getFrenalAttachment()!=null)
						preauthVO.setDntsublistoral4(ehfDentalOralExaminations.getFrenalAttachment());
					if(ehfDentalOralExaminations.getBuccalMucosa()!=null)
						preauthVO.setDntsublistoral5(ehfDentalOralExaminations.getBuccalMucosa());
					if(ehfDentalOralExaminations.getGingiva()!=null)
						preauthVO.setDntsublistoral6(ehfDentalOralExaminations.getGingiva());
					if(ehfDentalOralExaminations.getSwSite()!=null)
						preauthVO.setSwSite(ehfDentalOralExaminations.getSwSite());
					if(ehfDentalOralExaminations.getSwSize()!=null)
						preauthVO.setSwSize(ehfDentalOralExaminations.getSwSize());
					if(ehfDentalOralExaminations.getSwExtension()!=null)
						preauthVO.setSwExtension(ehfDentalOralExaminations.getSwExtension());
					if(ehfDentalOralExaminations.getSwColour()!=null)
						preauthVO.setSwColour(ehfDentalOralExaminations.getSwColour());
					if(ehfDentalOralExaminations.getSwConsistency()!=null)
						preauthVO.setSwConsistency(ehfDentalOralExaminations.getSwConsistency());
					if(ehfDentalOralExaminations.getSwTenderness()!=null)
						preauthVO.setSwTenderness(ehfDentalOralExaminations.getSwTenderness());
					if(ehfDentalOralExaminations.getSwBorders()!=null)
						preauthVO.setSwBorders(ehfDentalOralExaminations.getSwBorders());
					if(ehfDentalOralExaminations.getPsSite()!=null)
						preauthVO.setPsSite(ehfDentalOralExaminations.getPsSite());
					if(ehfDentalOralExaminations.getPsDischarge()!=null)
						preauthVO.setPsDischarge(ehfDentalOralExaminations.getPsDischarge());
			}
			EhfDentalTissueExaminations ehfDentalTissueExaminations = genericDao.findById(EhfDentalTissueExaminations.class, String.class, patientId);
			{
				if(ehfDentalTissueExaminations!=null)
				{
					if(ehfDentalTissueExaminations.getDeciduousCaries()!=null)
					  preauthVO.setCarriesdecidous(ehfDentalTissueExaminations.getDeciduousCaries());
					if(ehfDentalTissueExaminations.getDeciduousMissing()!=null)
						  preauthVO.setMissingdecidous(ehfDentalTissueExaminations.getDeciduousMissing());
					if(ehfDentalTissueExaminations.getMobile()!=null)
						  preauthVO.setMobiledecidous(ehfDentalTissueExaminations.getMobile());
					if(ehfDentalTissueExaminations.getGrosslyDecayed()!=null)
						  preauthVO.setGrosslydecadedecidous(ehfDentalTissueExaminations.getGrosslyDecayed());
					if(ehfDentalTissueExaminations.getPermanentCaries()!=null)
						  preauthVO.setCarriespermanent(ehfDentalTissueExaminations.getPermanentCaries());
					if(ehfDentalTissueExaminations.getRootGrosslyDecayed()!=null)
						  preauthVO.setRootstumppermannet(ehfDentalTissueExaminations.getRootGrosslyDecayed());
					if(ehfDentalTissueExaminations.getMobility()!=null)
						  preauthVO.setMobilitypermanent(ehfDentalTissueExaminations.getMobility());
					if(ehfDentalTissueExaminations.getAttritionAbrasion()!=null)
						  preauthVO.setAttritionpermanent(ehfDentalTissueExaminations.getAttritionAbrasion());
					if(ehfDentalTissueExaminations.getPermanentMissing()!=null)
						  preauthVO.setMissingpermanent(ehfDentalTissueExaminations.getPermanentMissing());
					if(ehfDentalTissueExaminations.getPermanentothers()!=null)
						  preauthVO.setOtherpermanent(ehfDentalTissueExaminations.getPermanentothers());
					
					
					
				}
				
				
			}
			/**
			 * get Examination findings
			 */
			EhfPatientExamFind ehfPatientExamFind = genericDao.findById(EhfPatientExamFind.class, String.class, patientId);
			if(ehfPatientExamFind != null)
			{
				if(ehfPatientExamFind.getBmi()!=null && !ehfPatientExamFind.getBmi().equalsIgnoreCase(""))
				preauthVO.setBmi(ehfPatientExamFind.getBmi());
				else
				preauthVO.setBmi("NA");
				if(ehfPatientExamFind.getHeight()!=null && !ehfPatientExamFind.getHeight().equalsIgnoreCase(""))
				preauthVO.setHeight(ehfPatientExamFind.getHeight());
				else
				preauthVO.setHeight("NA");
				if(ehfPatientExamFind.getWeight()!=null && !ehfPatientExamFind.getWeight().equalsIgnoreCase(""))
				preauthVO.setWeight(ehfPatientExamFind.getWeight());
				else
				preauthVO.setWeight("NA");
				preauthVO.setPallor(ehfPatientExamFind.getPallor());
				preauthVO.setCyanosis(ehfPatientExamFind.getCyanosis());
				preauthVO.setClubbingOfFingers(ehfPatientExamFind.getClubOfFingrToes());
				preauthVO.setLymphadenopathy(ehfPatientExamFind.getLymphadenopathy());
				preauthVO.setEdema(ehfPatientExamFind.getOedemaInFeet());
				preauthVO.setMalNutrition(ehfPatientExamFind.getMalnutrition());
				if(ehfPatientExamFind.getDehydration() != null && ehfPatientExamFind.getDehydration().equalsIgnoreCase("N"))
				preauthVO.setDehydration(ehfPatientExamFind.getDehydration());
				else if(ehfPatientExamFind.getDehydration() != null &&  !ehfPatientExamFind.getDehydration().equals("") && ehfPatientExamFind.getDehydration().contains("Y"))
				{
						preauthVO.setDehydration(ehfPatientExamFind.getDehydration().charAt(0)+"");
						preauthVO.setDehydrationType(ehfPatientExamFind.getDehydration().substring(1));
				}
				if(ehfPatientExamFind.getTemperature()!=null && !ehfPatientExamFind.getTemperature().equalsIgnoreCase(""))
				preauthVO.setTemperature(ehfPatientExamFind.getTemperature());
				else
				preauthVO.setTemperature("NA");	
				if(ehfPatientExamFind.getPluseRate()!=null && !ehfPatientExamFind.getPluseRate().equalsIgnoreCase(""))
				preauthVO.setPulseRate(ehfPatientExamFind.getPluseRate());
				else
				preauthVO.setPulseRate("NA");
				if(ehfPatientExamFind.getRespirationRate()!=null && !ehfPatientExamFind.getRespirationRate().equalsIgnoreCase(""))
				preauthVO.setRespirationRate(ehfPatientExamFind.getRespirationRate());
				else
					preauthVO.setRespirationRate("NA");	
				if(ehfPatientExamFind.getBpLt()!=null && !ehfPatientExamFind.getBpLt().equalsIgnoreCase(""))
				preauthVO.setBpLmt(ehfPatientExamFind.getBpLt());
				else
					preauthVO.setBpLmt("NA");
				if(ehfPatientExamFind.getBpRt()!=null && !ehfPatientExamFind.getBpRt().equalsIgnoreCase(""))
				preauthVO.setBpRmt(ehfPatientExamFind.getBpRt());
				else
					preauthVO.setBpRmt("NA");
				if(ehfPatientExamFind.getTreatmentDntl()!=null && !ehfPatientExamFind.getTreatmentDntl().equalsIgnoreCase(""))
					preauthVO.setTreatmentDntl(ehfPatientExamFind.getTreatmentDntl());
					else
						preauthVO.setTreatmentDntl("NA");
			}
			
			preauthVO.setFamilyHis(ehfPatientHospDiagnosis.getFamilyHistory());	
			if(ehfPatientHospDiagnosis.getFamilyHistoryOthr()!=null && !ehfPatientHospDiagnosis.getFamilyHistoryOthr().equalsIgnoreCase(""))
				preauthVO.setFamilyHistoryOthr(ehfPatientHospDiagnosis.getFamilyHistoryOthr());
			
			/**
			 * Investigation Details--wm_concate(gim.invName );
			 */
			StringBuffer query=new StringBuffer();
			List<PreauthVO> list1=null;
			 if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					 	query.append("select gim.investigation_name as TESTKNOWN, ");
						query.append(" case when pt.test_Id like '%OI%' then 'Others' else gim.investigation_name end as  TEST, ");
						query.append(" case when pt.test_Id like '%OI%' then pt.test_Name else gim.investigation_name end as NAME, ");
						query.append(" pt.test_Id as SPLINVSTID ");
						query.append(" ,pt.ATTACH_PATH as ROUTE,gim.price as PRICE from ehfm_investigation_dtls_nabh gim,Ehf_Patient_Tests pt"); 
						query.append(" where pt.test_Id=gim.test_id and pt.patient_Id='"+patientId+"' ");
						list1=genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
						if(list1!=null && !list1.isEmpty())
						{
							preauthVO.setInvList(list1);
						}
					 
				}
			 else
			 {
					query.append("select gim.inv_Main_Code as TESTKNOWN, ");
					query.append(" case when pt.test_Id like '%OI%' then 'Others' else gim.inv_Main_Name end as  TEST, ");
					query.append(" case when pt.test_Id like '%OI%' then pt.test_Name else gim.inv_Name end as NAME, ");
					query.append(" pt.test_Id as SPLINVSTID ");
					query.append(" ,pt.ATTACH_PATH as ROUTE,gim.invest_Price as PRICE from Ehfm_Gen_Investigations_Mst gim,Ehf_Patient_Tests pt"); 
					query.append(" where pt.test_Id=gim.inv_Code(+) and pt.patient_Id='"+patientId+"' and pt.from_Disp is null ");
					list1=genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
					if(list1!=null && !list1.isEmpty())
					{
						preauthVO.setInvList(list1);
					}	
					StringBuffer lstrQuery = new StringBuffer();
					List<PreauthVO> dispnsryList=null;
					lstrQuery.append("select gim.investigation_name as DISPTESTKNOWN, ");
					lstrQuery.append(" case when pt.test_Id like '%OI%' then 'Others' else gim.investigation_name end as  DISPTEST, ");
					lstrQuery.append(" case when pt.test_Id like '%OI%' then pt.test_Name else gim.investigation_name end as DISPNAME, ");
					lstrQuery.append(" pt.test_Id as DISPSPLINVSTID ");
					lstrQuery.append(" ,pt.ATTACH_PATH as DISPROUTE,gim.price as DISPPRICE from ehfm_investigation_dtls_nabh gim,Ehf_Patient_Tests pt"); 
					lstrQuery.append(" where pt.test_Id=gim.test_id and pt.patient_Id='"+patientId+"' and pt.from_Disp ='Y'   ");
					dispnsryList=genericDao.executeSQLQueryList(PreauthVO.class, lstrQuery.toString());
					if(dispnsryList!=null && !dispnsryList.isEmpty())
					{
						preauthVO.setDispnsryList(dispnsryList);
					}
					
			 }
			
			
			if(ehfPatient.getPatientIpop()!=null && !ehfPatient.getPatientIpop().equalsIgnoreCase("")){
				preauthVO.setIpOpFlag(ehfPatient.getPatientIpop());
				preauthVO.setDiagnosisType(ehfPatientHospDiagnosis.getDiagnosisType());
				preauthVO.setMainCatName(ehfPatientHospDiagnosis.getMainCatCode());
				preauthVO.setCatId(ehfPatientHospDiagnosis.getCategoryCode());
				preauthVO.setSubCatName(ehfPatientHospDiagnosis.getSubCatCode());
				preauthVO.setDisMainId(ehfPatientHospDiagnosis.getDiseaseCode());
				preauthVO.setDisAnatomicalSitename(ehfPatientHospDiagnosis.getDiseaseAnatCode());
				preauthVO.setPatientIPNo(ehfPatient.getPatientIpopNo());
			}
			query=new StringBuffer();
			
			List<DrugsVO> drugList=null;
			
			  if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
				  	query.append(" select EDM.DRUG_TYPE as DRUGTYPECODE,EDM.DRUG_NAME as DRUGSUBTYPECODE, ");
					query.append( " EPD.OTHER_DRUG_NAME as OTHERDRUGNAME, ");
					query.append(" ER.route_Type_Code as ROUTETYPE,ER.route_Type_Name as ROUTETYPENAME,ER.route_Code as ROUTE,ER.route_Name as ROUTENAME,ES.strength_Unit_Code as STRENGTHTYPE,ES.strength_Unit_Name as STRENGTHTYPENAME ,");
					query.append(" ES.strength_Code as STRENGTH,ES.strength_Name as STRENGTHNAME,EPD.dosage as DOSAGE,EPD.medication_Period as MEDICATIONPERIOD from Ehf_Patient_Drugs EPD,ehfm_disp_drug_mst EDM,Ehfm_Op_Strength_Mst ES, ");
					query.append(" Ehfm_Op_Route_Mst ER where EPD.patient_Id='"+patientId+"' and (EPD.DRUG_SUB_TYPE_CODE = EDM.DRUG_ID OR EPD.DRUG_SUB_TYPE_CODE = EDM.DRUG_NAME) and ER.route_Code(+)=EPD.route and ES.strength_Code(+)=EPD.strength ");
					query.append("    ");
					drugList=genericDao.executeSQLQueryList(DrugsVO.class, query.toString());
				}
			  else
			  {
					query.append(" select nvl(EDM.MAIN_GRP_CODE,'Others') as DRUGTYPECODE,nvl(EDM.ther_main_group_code,'Others') as DRUGSUBTYPECODE,nvl(EDM.PHAR_SUB_GRP_CODE,'Others') as PSUBGRPCODE,nvl(EDM.CHE_SUB_GRP_CODE,'Others') as CSUBGRPCODE,EPD.ASRI_DRUG_CODE as DRUGCODE, ");
					query.append(" nvl(EDM.main_Grp_Name,'Others') as DRUGTYPENAME,nvl(EDM.ther_Main_Grp_Name,'Others') as DRUGSUBTYPENAME,nvl(EDM.phar_Sub_Grp_Name,'Others') as PSUBGRPNAME,nvl(EDM.che_Sub_Grp_Name,'Others') as CSUBGRPNAME," );
					query.append( "  case when EPD.ASRI_DRUG_CODE like '%OD%' then");
					query.append( " EPD.other_drug_name else EDM.CHEMICAL_NAME end as  DRUGNAME,EPD.OTHER_DRUG_NAME as OTHERDRUGNAME, ");
					query.append(" ER.route_Type_Code as ROUTETYPE,ER.route_Type_Name as ROUTETYPENAME,ER.route_Code as ROUTE,ER.route_Name as ROUTENAME,ES.strength_Unit_Code as STRENGTHTYPE,ES.strength_Unit_Name as STRENGTHTYPENAME ,");
					query.append(" ES.strength_Code as STRENGTH,ES.strength_Name as STRENGTHNAME,EPD.dosage as DOSAGE,EPD.medication_Period as MEDICATIONPERIOD from Ehf_Patient_Drugs EPD,Ehfm_Op_Drug_Mst EDM,Ehfm_Op_Strength_Mst ES, ");
					query.append(" Ehfm_Op_Route_Mst ER where EPD.ASRI_drug_Code=EDM.chemical_Code(+) and ER.route_Code(+)=EPD.route and ES.strength_Code(+)=EPD.strength ");
					query.append("  and EPD.patient_Id='"+patientId+"' ");
					drugList=genericDao.executeSQLQueryList(DrugsVO.class, query.toString());
			  }
			if(drugList!=null && drugList.size()>0){
				preauthVO.setDrugList(drugList);
			}
		}
		/*StringBuffer query = new StringBuffer();
		query.append("select ehs.icd_cat_code as TEST from ehfm_hosp_speciality ehs where ehs.hosp_id='"+ehfPatient.getRegHospId()+"'");
		List<PreauthVO> specialityList = genericDao.executeSQLQueryList(PreauthVO.class, query.toString());
		if(specialityList!=null){
			for(PreauthVO vo : specialityList){
				if(vo.getTEST() !=null && "S18".equalsIgnoreCase(vo.getTEST())){
					dentalFlg="Y";
					break;
				}
			}
			
		}
		preauthVO.setDentalFlg(dentalFlg);*/
		return preauthVO;
	}
	@Override 
	public void insertDetailsLuceneEHS(String hospId) {
		String isUpdate = null;
		String actionUpdate = SearchConstants.CreateOrAppend;
		String LuceneFilesPath="/storageNAS-TS-Production/EHSLUCENEDRUGS/"+hospId;
		List<Document> docmentsList_EHS = new ArrayList<Document>();
		StringBuffer sb = new StringBuffer();
		System.out.println("In Create index in Commondaoimpl");

		try {

			List<LabelBean> lstDrugType = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
           if(config.getString("APPOLO_DRUG_HOSP_GROUP").contains("~"+hospId+"~"))
			query.append(" select a.drugName as VALUE, a.drugId as ID ,a.hospId as hospId,a.price as price from EhfmBreakupDrugMst a  where activeYn='Y'  and hospId in ('"+hospId+"','APP')");
			else
			query.append(" select a.drugName as VALUE, a.drugId as ID ,a.hospId as hospId,a.price as price from EhfmBreakupDrugMst a where activeYn='Y' and  hospId='"+hospId+"'");
			lstDrugType = genericDao.executeHQLQueryList(LabelBean.class,
					query.toString());

			// end
			deleteFiles(new File(LuceneFilesPath));
			new File(LuceneFilesPath).mkdirs();
			//System.out.println("druglist size" + lstDrugType.size());
			if (lstDrugType != null && lstDrugType.size() > 0) {
				for (LabelBean bean : lstDrugType) {
					Document doc = new Document();

					doc.add(new Field("VALUE", bean.getVALUE() == null ? "": bean.getVALUE().replace(" ", "1X2"), Field.Store.YES,Field.Index.ANALYZED));
					doc.add(new Field("drugId", bean.getID() == null ? "": bean.getID(), Field.Store.YES,Field.Index.ANALYZED));
					doc.add(new Field("price", bean.getPrice() == null ? "": bean.getPrice(), Field.Store.YES,Field.Index.ANALYZED));
					doc.add(new Field("hospId", hospId == null ? "": hospId.replace("1X2", " "), Field.Store.YES,Field.Index.ANALYZED));
					docmentsList_EHS.add(doc);
				}
			}
			System.out.println("done Inserting into file "+hospId);

			Search.createOrUpdateIndex(LuceneFilesPath, docmentsList_EHS,actionUpdate);
			System.out.println("done indexing");


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void deleteFiles(File file) {
	    if (file.isDirectory())
	        for (File f : file.listFiles())
	            deleteFiles(f);
	    else
	        file.delete();
	}
    public Map<String, Object> getLuceneSearch(Map<String, Object> paramMap){

		 List<LabelBean> drugDetails=new ArrayList<LabelBean>();
        String nameSearch = paramMap.get("nameSearch")!=null ? (String)paramMap.get("nameSearch"): "";
        String hospId = paramMap.get("hospId")!=null ? (String)paramMap.get("hospId"): "";
		String nameSearch1 = nameSearch.replace(" ","1X2");
        StringBuilder sb = new StringBuilder();
        String index="";
   
       
        List<SearchQueryBean>  searchList1=new ArrayList<SearchQueryBean>();
        List<Document> finalDrugList=new ArrayList<Document>();
        Map<String, Object> resultMap = new HashMap<String, Object>();       
        try{
			EhfmHospitals	hospGrps = genericDao.findById(EhfmHospitals.class, String.class,hospId );
	          index="/storageNAS-TS-Production/EHSLUCENEDRUGS/"+hospGrps.getHospGroup();

        if(nameSearch1!=null && nameSearch1.length()>0){ 
			System.out.println("name with spaces is " +nameSearch);		 
			System.out.println(" hosp  grp is " +hospGrps.getHospGroup());
            System.out.println("name is " +nameSearch1);
            searchList1.add(new SearchQueryBean("VALUE",nameSearch1,SearchConstants.LIKE,SearchConstants.NORMAL));
            searchList1.add(new SearchQueryBean("hospId",hospGrps.getHospGroup().replace(" ","1X2"),SearchConstants.EQUALS,SearchConstants.NORMAL));
        }
            finalDrugList= Search.searchIndex(searchList1, index);
			 System.out.println("done searchng"+searchList1.size());
          System.out.println("SearchResultList size-------"+finalDrugList.size()); 
		    if(finalDrugList!=null && finalDrugList.size()>0){			
				for(Document bean:finalDrugList){
				//System.out.println("in doclist loop"); 
				 LabelBean temp= new LabelBean();
				 temp.setVALUE(bean.get("VALUE").replace("1X2", " ")+"(Rs."+bean.get("price")+")");
				 temp.setID(bean.get("drugId"));
				 temp.setHospId(bean.get("hospId"));
				 drugDetails.add(temp);
				}
			}

		   
        }
        catch(Exception e){
            e.printStackTrace();
            }
        
        resultMap.put("drugDetails",drugDetails); 
        
    return resultMap;
    
    }
	@Override
	public String getHospScheme(String caseId) {

		String nimsHospId=null;
		EhfCase ehfCase=new EhfCase();
		try
		{
		if(caseId!=null)
		{
		ehfCase=genericDao.findById(EhfCase.class,String.class,caseId);
		}
		if(ehfCase!=null)
		{
			nimsHospId=ehfCase.getCaseHospCode();
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return nimsHospId;
		
		
	
	}
}
