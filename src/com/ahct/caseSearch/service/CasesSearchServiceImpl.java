package com.ahct.caseSearch.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.caseSearch.dao.CasesSearchDao;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.vo.LabelBean;
import com.ahct.model.AsrimUsers;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseClaim;
import com.ahct.model.EhfmUsers;
import com.ahct.model.EhfCsvCaseSearch;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;

public class CasesSearchServiceImpl implements CasesSearchService{
	GenericDAO genericDao;
	static final Logger logger = Logger.getLogger(CasesSearchService.class);
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	CasesSearchDao casesSearchDao;
	public void setCasesSearchDao(CasesSearchDao casesSearchDao) {
		this.casesSearchDao = casesSearchDao;
	}
	HibernateTemplate hibernateTemplate;
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	public List<LabelBean> getCaseStatus(String moduleType)
	{
		List<LabelBean> caseList = new ArrayList<LabelBean>();
		  SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		  StringBuffer query = new StringBuffer();
		  String args[] = new String[1];
		  if(moduleType!=null && moduleType.equalsIgnoreCase("claim"))
		  args[0] = config.getString("Claim_case_status_hdrId");
		  else  if(moduleType!=null && moduleType.equalsIgnoreCase("preauth"))
		  args[0] = config.getString("case_status_hdrId");
		  else
		  args[0] =  config.getString("case_status_hdrId")+"','"+config.getString("Claim_case_status_hdrId");
			  
		  //args[0] = "CH602";
		  query.append(" select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbHdrId in ('"+args[0]+"')");
		  if(moduleType!=null && moduleType.equalsIgnoreCase("preauth"))
			  query.append(" and a.cmbDtlId not in ('"+config.getString("CASE.CaseDrafted")+"') ");
		  query.append(" order by a.cmbDtlName ");
		  caseList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally
		{
			session.close();
			factory.close();
		}
		return caseList;
	}
	public List<LabelBean> getListOfSurgery()
	{
		List<LabelBean> surgeryList = new ArrayList<LabelBean>();
		 SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		 StringBuffer query = new StringBuffer();
		  query.append("   select a.therapyId as  ID ,a.therapyName  as VALUE from EhfmTherapyMst a   order by a.therapyName ");
		  surgeryList= session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally{
			session.close();
			factory.close();
		}
		  return surgeryList;
	}
	public List<LabelBean> getErroneousList()
	{
		List<LabelBean> erroneousList = new ArrayList<LabelBean>();
		  SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		 StringBuffer query = new StringBuffer();
		  String args[] = new String[1];
		  args[0] = config.getString("Erroneouscase_status_hdrId");
		 // args[0] = "CH75";
		  query.append("  select a.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbHdrId = '"+args[0]+"'");
		  query.append(" order by a.cmbDtlName ");
		  erroneousList=  session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally
		{
			session.close();
			factory.close();
		}
		  return erroneousList;
	}
	
	public List<LabelBean> getCatList()
	{
		List<LabelBean> getCatList = new ArrayList<LabelBean>();
		  SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		StringBuffer query = new StringBuffer();
		query.append("  select distinct ad.diaCatId as ID, (ad.diaCatName || ' (' || ad.diaCatId || ')') as  VALUE from EhfmDiagnosisCatMst ad where ad.activeYN = 'Y'  order by 2 asc");
		getCatList=  session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally{
			session.close();
			factory.close();
		}
		return getCatList;
	}
	public CasesSearchVO getListCases(CasesSearchVO casesSearchVO)
	{
		CasesSearchVO getListCases = casesSearchDao.getListCases(casesSearchVO);
		return getListCases;
	}
	public CasesSearchVO getListCasesDirect(CasesSearchVO casesSearchVO)
	{
		CasesSearchVO getListCases = casesSearchDao.getListCasesDirect(casesSearchVO);
		return getListCases;
	}
	public CasesSearchVO getFeedbackList(CasesSearchVO casesSearchVO)
	{
		
	CasesSearchVO getFeedbackList=casesSearchDao.getFeedbackList(casesSearchVO);	
		
	return 	getFeedbackList;
		
	}
	
	@Override
	public CasesSearchVO getTeleIntimationCases(CasesSearchVO casesSearchVO) {
		CasesSearchVO getListCases = casesSearchDao.getTeleIntimationCases(casesSearchVO);
		return getListCases;
	}
	public CasesSearchVO getRegTeleIntimationCases(CasesSearchVO casesSearchVO){
		CasesSearchVO getListCases = casesSearchDao.getRegTeleIntimationCases(casesSearchVO);
		return getListCases;
	}
	
	public CasesSearchVO getUserRole(CasesSearchVO casesSearchVO){
		AsrimUsers asrimUser = genericDao.findById(AsrimUsers.class, String.class,casesSearchVO.getUserId());
		if(asrimUser!=null)
			casesSearchVO.setUserRole(asrimUser.getUserRole());
		return casesSearchVO;
	}
	public String setviewFlag(String pStrCaseId, String pStrFlag, String lStrEmpId)
	{
		String msg = null;
		Date date = new Date();
		try
		{
			EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, pStrCaseId);
			if(ehfCase != null)
			{
				ehfCase.setViewFlag(pStrFlag);	
				ehfCase.setCase_blocked_dt(new java.sql.Timestamp(date.getTime()));
				ehfCase.setCaseBlockedUsr(lStrEmpId);
				genericDao.save(ehfCase);
			}
			msg ="success";
		}catch(Exception e)
		{
			msg ="failure";
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public List<LabelBean> getCaseErrStatus() {
		List<LabelBean> caseList = new ArrayList<LabelBean>();
		  SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		  StringBuffer query = new StringBuffer();
		  String args[] = new String[1];
		  args[0] = config.getString("Erroneouscase_status_hdrId");
		  query.append(" select a.id.cmbDtlId as ID , a.cmbDtlName as VALUE from EhfmCmbDtls a  where a.cmbHdrId ='"+args[0]+"'");
		  query.append(" order by a.cmbDtlName ");
		  caseList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally
		{
			session.close();
			factory.close();
		}
		return caseList;
	}
	@Override
	public CasesSearchVO getListIssues(CasesSearchVO casesSearchVO) {
		// TODO Auto-generated method stub
		CasesSearchVO getListIssues = casesSearchDao.getListIssues(casesSearchVO);
		return getListIssues;
	}
	public List<CasesSearchVO> getDeathCases(CasesSearchVO casesSearchVO)
	{
		List<CasesSearchVO> getListCases = casesSearchDao.getDeathCases(casesSearchVO);
		return getListCases;
	}
	@Override
	public CasesSearchVO getAccountDetails(CasesSearchVO casesSearchVO) {
		EhfCaseClaim ehfCaseClaim = genericDao.findById(EhfCaseClaim.class, String.class, casesSearchVO.getCaseId());
		if(ehfCaseClaim!=null){
			if(ehfCaseClaim.getHospPctAmt()!=null && ehfCaseClaim.getHospPctAmt()>0)
			casesSearchVO.setIssueResultFlagSize(ehfCaseClaim.getHospPctAmt().toString());
			else if(ehfCaseClaim.getTdsHospPctAmt()!=null && ehfCaseClaim.getTdsHospPctAmt()>0)
				casesSearchVO.setIssueResultFlagSize(ehfCaseClaim.getTdsHospPctAmt().toString());
			if(ehfCaseClaim.getTdsPctAmt()!=null)
			casesSearchVO.setIssuestatus(ehfCaseClaim.getTdsPctAmt().toString());
			if(ehfCaseClaim.getTrustPctAmt()!=null)
			casesSearchVO.setIssuetitle(ehfCaseClaim.getTrustPctAmt().toString());
		}
		return casesSearchVO;
	}
	@Override
	public String getTimeOutCount(String caseId1, List<LabelBean> groupsList, String lStrModule) {
		List<LabelBean> caseList = new ArrayList<LabelBean>();
		String timer = "0";
		  SessionFactory factory=hibernateTemplate.getSessionFactory();
		  Session session = factory.openSession();
		try{
		  StringBuffer query = new StringBuffer();
		  query.append(" select nvl(max(b.timer),0) as ID from EhfCaseTherapy a,EhfmTherapyGrpTimeout b  where a.asriCatCode=b.id.specialityCode and a.icdProcCode=b.id.icdProcCode ");
		  query.append(" and a.activeyn='Y' and a.caseId='"+caseId1+"' and b.id.moduleType='"+lStrModule+"' and b.id.grpId in (");
		  for(int k=0; k<groupsList.size() ;k++)
			 {
			   if(k!=0 && k!=groupsList.size())
			     {
				   query.append(" , ");  
			     }
			   query.append(" '"+groupsList.get(k).getID()+"' ");	
			 }
		  query.append(" ) "); 
		  query.append(" and b.id.scheme='"+config.getString("TG")+"' ");
		  caseList=session.createQuery(query.toString()).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
		  if(caseList!=null && caseList.size()>0){
			  timer = caseList.get(0).getID();
		  }
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		finally
		{
			session.close();
			factory.close();
		}
		return timer;
	}
	public String getCaseStatusForCase(String caseId)
	{
		String lStrCaseStatus = ""; 
		EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
		if(ehfCase!=null){
			lStrCaseStatus = ehfCase.getCaseStatus();
		}
		return lStrCaseStatus;	
	}
	
	/*
	 * Used to get all the therapies of the case
	 */
	public List<CasesSearchVO> getDopDtls(String caseId)
	{
		
		StringBuffer query= new StringBuffer();
		query.append(" select ect.caseId as caseId,ect.activeyn as activeyn,ect.asriCatCode as speciality, ");
		query.append(" ect.icdProcCode as procCode,etpm.id.process as process ,ep.employeeNo as PATIPOP ");
		query.append(" ,ec.caseStatus as caseStatus,cast(ec.csSurgDt as string) as csSurgDt ");
		query.append(" from EhfCaseTherapy ect,EhfmTherapyProcMst etpm,EhfCase ec , EhfPatient ep  where ");
		query.append(" etpm.id.icdProcCode=ect.icdProcCode and ect.caseId='"+caseId+"'");
		query.append(" and ect.caseId=ec.caseId and ec.caseId='"+caseId+"'");
		query.append(" and ec.casePatientNo=ep.patientId ");
		query.append(" and etpm.id.state=ec.schemeId");
		List<CasesSearchVO> list=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
		return list;
	}
	
	/*
	 * Added by Srikalyan for CSV Downloads Query Insertion
	 */
	public CasesSearchVO getListCasesForCSV(CasesSearchVO casesSearchVO)
		{
			CasesSearchVO resultVO=new CasesSearchVO();
			try
				{
					resultVO=casesSearchDao.getListCasesForCSV(casesSearchVO);
				}
			catch(Exception e )
				{
					e.printStackTrace();
//					logger.error("Error Occured in getListCasesForCSV method of CasesSearchServiceImpl "+e.getMessage());
				}
			return resultVO;
		}
	
	/*
	 * Added by Srikalyan to view CSV Downloads  
	 */
	public CasesSearchVO viewCSVDownloads(CasesSearchVO casesSearchVO)
		{
			String userId=casesSearchVO.getUserId();
			CasesSearchVO casesSearchVOFinal=new CasesSearchVO();
			if(userId!=null)
				{
					StringBuffer query=new StringBuffer();
					query.append(" select a.requestId as requestId , a.userId as userId , a.csvStatus as issuestatus ");
					query.append(" , a.fileName as testName, a.filePath as attachTotalPath , a.crtDt as CREATEDON ");
					query.append(" , b.firstName||' '||b.lastName as PATNAME ");
					query.append(" from EhfCsvCaseSearch a , EhfmUsers b ");
					query.append(" where a.userId='"+userId+"' and a.csvStatus='Y' and a.userId = b.userId ");
					query.append(" order by a.crtDt ");
					
					try
						{
							List<CasesSearchVO> downloadList=genericDao.executeHQLQueryList(CasesSearchVO.class, query.toString());
							if(downloadList!=null)
								casesSearchVOFinal.setLstCaseSearch(downloadList);
						}
					catch(Exception e)
						{
							e.printStackTrace();
//							logger.error("Error Occured in viewCSVDownloads method of CasesSearchServiceImpl "+e.getMessage());
						}
				}	
			return casesSearchVOFinal; 
		}
	/*
	 * Added by Srikalyan to get User Details  
	 */
	public CasesSearchVO getUserDtls(CasesSearchVO casesSearchVO)
		{
			String userId=casesSearchVO.getUserId();
			CasesSearchVO casesSearchVOFinal=new CasesSearchVO();
			if(userId!=null)
				{
					EhfmUsers ehfmUsers=genericDao.findById(EhfmUsers.class, String.class, userId);
					casesSearchVOFinal.setPatientName(ehfmUsers.getFirstName()+" "+ehfmUsers.getLastName());	
				}
			return casesSearchVOFinal; 
		}
	
	/*
	 * Added by Srikalyan to get CSV File Path  
	 */
	public CasesSearchVO getCSVPath(CasesSearchVO casesSearchVO)
		{	
			CasesSearchVO csvVO=new CasesSearchVO();
			if(casesSearchVO.getRequestId()!=null && !"".equalsIgnoreCase(casesSearchVO.getRequestId()))
				{
					EhfCsvCaseSearch ehfCsvCaseSearch=new EhfCsvCaseSearch();
					ehfCsvCaseSearch=genericDao.findById(EhfCsvCaseSearch.class, String.class, casesSearchVO.getRequestId());
					if(ehfCsvCaseSearch!=null)
						{
							if(ehfCsvCaseSearch.getCsvStatus()!=null)
								{
									if(ehfCsvCaseSearch.getCsvStatus().equalsIgnoreCase("Y"))
										{
											csvVO.setRequestId(ehfCsvCaseSearch.getRequestId());
											csvVO.setTestName(ehfCsvCaseSearch.getFileName());
											csvVO.setCsvFilePath(ehfCsvCaseSearch.getFilePath());
										}
								}
						}
				}
			return csvVO;

		}
	@Override
	public CasesSearchVO getCasesSearchCSV(CasesSearchVO casesSearchVO) 
	{	
			CasesSearchVO getCasesSearchCSV = casesSearchDao.getCasesSearchCSV(casesSearchVO);
			return getCasesSearchCSV;
	
	}

	@Override
	public CasesSearchVO getListCasesNabh(CasesSearchVO casesSearchVO) {
		CasesSearchVO getListCases = casesSearchDao.getListCasesNabh(casesSearchVO);
		return getListCases;
	}
		@Override
	public String getTherapyId(String caseId) {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		List<CasesSearchVO> procList = null;
		String orgFlg="N";
		try
		{
			query.append("select asri_cat_code as SPCLTYID from ehf_case_therapy where case_id='"+caseId+"'");
			procList = genericDao.executeSQLQueryList(CasesSearchVO.class,query.toString() );
			if(procList.size()>0)
			{
				for(int i=0;i<procList.size();i++)
				{
					if(procList.get(i).getSPCLTYID().equalsIgnoreCase(config.getString("OrganTransplantation")))
					{
						orgFlg="Y";

					}
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return orgFlg;
	}
	@Override
	public String getPnlNameNew(String autoCaseId) {
		String userId=null;
		List<CasesSearchVO> lst = null;
		StringBuffer query=new StringBuffer();
		try
		{
			/*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			criteriaList.add(new GenericDAOQueryCriteria("id.locId",locId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			List<EhfmLocations> ehfmLocations = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
			for(EhfmLocations el:ehfmLocations)
			{
				locName=el.getLocName();
			}*/
			query.append(" select act_by as CREATEDBY from ehf_audit WHERE case_id='"+autoCaseId+"' and act_id in ('CD1537','CD1536') ");
			lst=genericDao.executeSQLQueryList(CasesSearchVO.class, query.toString());
			if(lst != null && !lst.isEmpty()){
			  userId =	lst.get(0).getCREATEDBY();
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userId;
	}
}
