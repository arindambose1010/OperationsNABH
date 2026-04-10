package com.ahct.common.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.AsritSms;
import com.ahct.model.EhfCase;
import com.ahct.model.EhfCaseFollowupClaim;
import com.ahct.model.EhfCaseLockStatus;
import com.ahct.model.EhfCaseTherapy;
import com.ahct.model.EhfCochlearFollowup;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientSmsData;
import com.ahct.model.EhfSmsBuffer;
import com.ahct.model.EhfmCmbDtls;
import com.ahct.model.EhfmFamilyHistoryMst;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmLocations;
import com.ahct.model.EhfmPastHistoryMst;
import com.ahct.model.EhfmSeq;
import com.ahct.model.EhfmTherapyCatMst;
import com.ahct.model.EhfmTherapyProcMst;
import com.ahct.model.EhfmUsrHospitalMpg;
import com.ahct.model.EhfmUsrSpecialityMpg;
import com.ahct.model.EjhsAppointmentDtl;
import com.ahct.model.EjhsWellnessConsultDtls;
import com.ahct.model.EjhsWellnessLeaveDetails;
import com.ahct.patient.vo.PatientVO;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailNotifier;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;



public class CommonServiceImpl implements CommonService {
	private final static Logger GLOGGER = Logger.getLogger ( CommonServiceImpl.class ) ;
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	EmailNotifier emailNotifier;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	String illnessHistory;
	String familyHis;

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
	public GenericDAO getGenericDao() {
				return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	public EmailNotifier getEmailNotifier() {
		return emailNotifier;
	}
	public void setEmailNotifier(EmailNotifier emailNotifier) {
		this.emailNotifier = emailNotifier;
	}
	/**
     * ;
     * @param String seqIdentifier
     * @return EhfmSeq ehfmSeq
     * @function This Method is Used For getting EhfmSeq data based on seqIdentifier
     */
	
	public EhfmSeq getSeqNextVal(String seqIdentifier) {
		EhfmSeq ehfmSeq = 
	            genericDao.findById(EhfmSeq.class, String.class, seqIdentifier);
	        return ehfmSeq;
	}
	/**
     * ;
     * @param EhfmSeq ehfmSeq
     * @function This Method is Used For saving seqVal in EhfmSeq Table
     */
	
	public void updateSequenceVal(EhfmSeq ehfmSeq) {
		try {
            genericDao.save(ehfmSeq);
        } catch (Exception e) {
        	GLOGGER.error("Exception occurred in updateSequence() in CommonServiceImpl class."+e.getMessage());
        }
		
	}
	
	/*public SgvcSeqMst getNextVal(String seqIdentifier) {
		SgvcSeqMst sgvcSeqMst = 
	            genericDao.findById(SgvcSeqMst.class, String.class, seqIdentifier);
	        return sgvcSeqMst;
	}
	
	
	public void updateSequence(SgvcSeqMst sgvcSeqMst) {
		try {
            genericDao.save(sgvcSeqMst);
        } catch (Exception e) {
        	GLOGGER.error("Exception occurred in updateSequence() in CommonServiceImpl class."+e.getMessage());
        }
		
	}
	*/
	public List<LabelBean> getDistrictList() throws Exception
    {
    	List<LabelBean> districtList = new ArrayList<LabelBean>();
    	Iterator asrimListItr=null;
    	EhfmLocations asrimLocations=null;
    	try
    	{
//    		HashMap<String,Object> map=new HashMap<String,Object>();
//    		map.put("locHdrId", "LH6");
//    		map.put("activeYn", "Y");
//    		List<EhfmLocations> asrimLocationsList=(List<EhfmLocations>)genericDao.findAllByPropertyMatch(EhfmLocations.class,map);
    		
    		
    		
    		List<GenericDAOQueryCriteria> criteriaList = 
                    new ArrayList<GenericDAOQueryCriteria>();

                criteriaList.add(new GenericDAOQueryCriteria("locName", null, 
                        GenericDAOQueryCriteria.CriteriaOperator.ASC));
                criteriaList.add(new GenericDAOQueryCriteria("locHdrId", "LH6", 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                criteriaList.add(new GenericDAOQueryCriteria("activeYn", "Y", 
                        GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
                List<EhfmLocations> asrimLocationsList = 
                        genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
    		
    		
    		
    		asrimListItr=asrimLocationsList.iterator();
    		while(asrimListItr.hasNext())
    		{
    			asrimLocations=(EhfmLocations)asrimListItr.next();
    			LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimLocations.getId().getLocId());
    			labelBean.setVALUE(asrimLocations.getLocName());
    			districtList.add(labelBean);
    		}
    		
        String args[] = new String[2];
        args[0]="LH6";
        args[1]="Y";
        StringBuffer query = new StringBuffer();
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
       //districtList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getDistrictList() in CommonServiceImpl class."+e.getMessage());
    	}
        return districtList;
    }

    /**
     * ;
     * @param String district ID
     * @return List of type LabelBean  Mandal List
     * @function This Method is Used For Getting List of Mandals for the passed District
     */
	
    public List<LabelBean> getMandalList(String distId) throws Exception
    {
		SessionFactory factory=null;
		Session session=null;
        List<LabelBean> mandalList = new ArrayList<LabelBean>();
        Iterator asrimListItr=null;
    	EhfmLocations asrimLocations=null;
    	try
    	{
    		String mdllocHdrId="LH7";
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		StringBuffer mandalquery = new StringBuffer();
    		mandalquery.append("from EhfmLocations al where al.locHdrId=? and al.id.locParntId=? and al.activeYn=? order by al.locName asc");
            Query mandallst=session.createQuery(mandalquery.toString());
            mandallst.setParameter(0, mdllocHdrId);
            mandallst.setParameter(1, distId);
            mandallst.setParameter(2, "Y");
            Iterator mandalItr=mandallst.iterate();
            while(mandalItr.hasNext())
            {
            	asrimLocations=(EhfmLocations)mandalItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(asrimLocations.getId().getLocId());
    			labelBean.setVALUE(asrimLocations.getLocName());
    			mandalList.add(labelBean);
            }
    	    	   		
        StringBuffer query = new StringBuffer();
        String args[] = new String[3];
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_parnt_id=? and loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
        args[0] = distId;
        args[1]= "LH7";
        args[2] = "Y";
       // mandalList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getMandalList() in CommonServiceImpl class."+e.getMessage());
        }
    	finally
    	{
    		session.close();
    		factory.close();
    	}
        
        return mandalList;
    }
    
    /**
     * ;
     * @param String mandal ID
     * @return List of type LabelBean  Village List
     * @function This Method is Used For Getting List of Villages for the passed Mandal
     */
	
	public List<LabelBean> getVillageList(String mandalId) throws Exception {
		List<LabelBean> villageList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[3];
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_parnt_id=? and loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
        args[0] = mandalId;
        args[1]= "LH8";
        args[2] = "Y";
        villageList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getVillageList() in CommonServiceImpl class."+e.getMessage());
        }
        
        return villageList;
	}

	/**
     * ;
     * @param String village ID
     * @return List of type LabelBean  Hamlet List
     * @function This Method is Used For Getting List of Hamlets for the passed Village
     */
	
	public List<LabelBean> getHamletList(String villageId) throws Exception {
		List<LabelBean> hamletList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[3];
        query.append("select loc.loc_id ID,loc.loc_name VALUE from ehfm_locations loc where loc.loc_parnt_id=? and loc.loc_hdr_id=? and loc.active_yn=? order by loc.loc_name asc");
        args[0] = villageId;
        args[1]= "LH9";
        args[2] = "Y";
        hamletList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getHamletList() in CommonServiceImpl class."+e.getMessage());
        }
        
        return hamletList;
	}

	
	public List<LabelBean> getCastes() throws Exception {
		
		List<LabelBean> casteList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[1];
        query.append("select castes.cmb_dtl_val ID,castes.cmb_dtl_name VALUE from asrim_combo castes where castes.cmb_hdr_id=? order by castes.cmb_dtl_name asc");
        args[0] = "CH6";
        casteList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getCastes() in CommonServiceImpl class."+e.getMessage());
        }
        
        return casteList;
	}

	
	public List<LabelBean> getRelations() throws Exception {
		List<LabelBean> relationList = new ArrayList<LabelBean>();
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[1];
        query.append("select relation.cmb_dtl_val ID,relation.cmb_dtl_name VALUE from asrim_combo relation where relation.cmb_hdr_id=? order by relation.cmb_dtl_name asc");
        args[0] = "CH4";
        relationList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getRelations() in CommonServiceImpl class."+e.getMessage());
        }
        
        return relationList;
	}
	
	/**
     * ;
     * @param String locHdrId
     * @param String locParntId
     * @return List of type LabelBean locationList
     * @function This Method is Used For Getting List of Districts/Mandals/Villages/Hamlets
     */
	
	public List<LabelBean> getAsrimLocations(String locHdrId,String locParntId) throws Exception 
	{
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> locationsList = new ArrayList<LabelBean>();
        Iterator locationsItr=null;
        StringBuffer locationsQuery=null;
    	EhfmLocations ehfmLocations=null;
    	try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		locationsQuery= new StringBuffer();
    		locationsQuery.append("from EhfmLocations al where al.locHdrId=? and al.id.locParntId=? and al.activeYn=? order by al.locName asc");
            Query locationslst=session.createQuery(locationsQuery.toString());
            locationslst.setParameter(0, locHdrId);
            locationslst.setParameter(1, locParntId);
            locationslst.setParameter(2, "Y");
            locationsItr=locationslst.iterate();
            while(locationsItr.hasNext())
            {
            	ehfmLocations=(EhfmLocations)locationsItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmLocations.getId().getLocId());
    			labelBean.setVALUE(ehfmLocations.getLocName());
    			locationsList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getEhfmLocations() in CommonServiceImpl class."+e.getMessage());
    	}
    	finally
    	{
    		session.close();
    		factory.close();
    	}
    	return locationsList;
	}
	/**
     * ;
     * @param String locId
     * @return String locName
     * @function This Method is Used For getting Location Name for given Location Id
     */
	
	public String getLocationName(String locId) throws Exception {
		String locName=null;
		try
		{
		 List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			 criteriaList.add(new GenericDAOQueryCriteria("id.locId",locId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		     List<EhfmLocations> ehfmLocations = genericDao.findAllByCriteria(EhfmLocations.class, criteriaList);
		for(EhfmLocations el:ehfmLocations)
		{
			locName=el.getLocName();
		}
		}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception Occurred in getLocationName() in PatientDaoImpl class."+e.getMessage());
    	}
		return locName;
	}
	/**
     * ;
     * @return List of type LabelBean occupationList
     * @function This Method is Used For Getting List of Occupations
     */
	
	public List<LabelBean> getOccupations() throws Exception {
		List<LabelBean> occupationList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select cast(om.occupationId as string) as ID,om.occName as VALUE from EhfmOccupationMst om where om.activeYN=? order by om.occName asc");
			occupationList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getOccupations() in CommonServiceImpl class."+e.getMessage());
		}
		return occupationList;
	}
	
	/**
     * ;
     * @param String cmbHdrId
     * @return List of type LabelBean comboList
     * @function This Method is Used For Getting List of Castes/Relations
     */
	@SuppressWarnings("unchecked")
	
	public List<LabelBean> getComboDetails(String cmbHdrId)throws Exception
	{
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> comboList = new ArrayList<LabelBean>();
        Iterator<EhfmCmbDtls> cmbItr=null;
        StringBuffer cmbHdrQuery=null;
        EhfmCmbDtls ehfmCmbDtls=null;
		try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		cmbHdrQuery= new StringBuffer();
    		cmbHdrQuery.append("from EhfmCmbDtls ac where ac.cmbHdrId=? order by ac.cmbDtlName asc");
            Query locationslst=session.createQuery(cmbHdrQuery.toString());
            locationslst.setParameter(0, cmbHdrId);
            cmbItr=locationslst.iterate();
            while(cmbItr.hasNext())
            {
            	ehfmCmbDtls=(EhfmCmbDtls)cmbItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmCmbDtls.getCmbDtlId());
    			labelBean.setVALUE(ehfmCmbDtls.getCmbDtlName());
    			comboList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getComboDetails() in CommonServiceImpl class."+e.getMessage());
    	}
		finally
    	{
    		session.close();
    		factory.close();
    	}
		return comboList;
	}
	/**
     * ;
     * @param String cmbHdrId
     * @param String cmbVal
     * @return String cmbName
     * @function This Method is Used For getting Combo Name
     */
	
	public String getCmbHdrname(String cmbHdrId, String cmbVal)
			throws Exception {
		String cmbName=null;
		try
		{
		 List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
			 criteriaList.add(new GenericDAOQueryCriteria("id.cmbDtlId",cmbVal,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			 criteriaList.add(new GenericDAOQueryCriteria("cmbHdrId",cmbHdrId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		     List<EhfmCmbDtls> ehfmCmbDtls = genericDao.findAllByCriteria(EhfmCmbDtls.class, criteriaList);
		for(EhfmCmbDtls ac:ehfmCmbDtls)
		{
			cmbName=ac.getCmbDtlName();
		}
		}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getCmbHdrname() in CommonServiceImpl class."+e.getMessage());
    	}
		return cmbName;
	}
	/**
     * ;
     * @param String distId
     * @return phase renewal
     * @function This Method is Used For Getting current renewal phase for district provided
     */
	
	public int getPhaseId(String distId) throws Exception {
		SessionFactory factory=null;
		Session session=null;
		int renewal=0;
		Iterator phaseItr=null;
        StringBuffer phaseQuery=null;
        try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		phaseQuery= new StringBuffer();
    		phaseQuery.append("select max(apd.id.renewal) as renewal from AsrimPhaseDuration apd where apd.id.phaseId= (select ads.phaseId from AsrimDistSeq ads where ads.distId=? )");
            Query phaselst=session.createQuery(phaseQuery.toString());
            phaselst.setParameter(0, distId);
            phaseItr=phaselst.iterate();
            if(phaseItr.hasNext())
            {
            renewal=((Long) phaseItr.next()).intValue();
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getPhaseId() in CommonServiceImpl class."+e.getMessage());
    	}
        finally
    	{
    		session.close();
    		factory.close();
    	}
		return renewal;
	}
	

	/**
     * ;
     * @return List of type LabelBean phaseList
     * @function This Method is Used For Getting List of Phases
     */
	
	public List<LabelBean> getPhases() throws Exception {
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> phaseList = new ArrayList<LabelBean>();
        Iterator phaseItr=null;
        StringBuffer phaseQuery=null;
		try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		phaseQuery= new StringBuffer();
    		phaseQuery.append("select distinct p.phaseId,p.phaseName from PhaseMaster p");
            Query locationslst=session.createQuery(phaseQuery.toString());
            phaseItr=locationslst.iterate();
            while(phaseItr.hasNext())
            {
            	Object[] phase=(Object[])phaseItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(phase[0].toString());
    			labelBean.setVALUE(phase[1].toString());
    			phaseList.add(labelBean);
            }
    	}
		catch(Exception e)
    	{
			GLOGGER.error("Exception occurred in getPhases() in CommonServiceImpl class."+e.getMessage());
    	}
		finally
    	{
    		session.close();
    		factory.close();
    	}
		return phaseList;
	}
	
	
	/*public List<LabelBean> getGoDetails(String goType) {
		List<LabelBean> lGoData=new ArrayList<LabelBean>();
		List<EhfmGoMst> lGoList=null;
		try
		{List<GenericDAOQueryCriteria> criteriaList=new ArrayList<GenericDAOQueryCriteria>() ;
		criteriaList.add(new GenericDAOQueryCriteria("id.goType",goType,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		 criteriaList.add(new GenericDAOQueryCriteria("id.goNum",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
			lGoList=genericDao.findAllByCriteria(EhfmGoMst.class, criteriaList);
			for(EhfmGoMst efmGoMst:lGoList)
			{
				LabelBean labelBean=new LabelBean();
				labelBean.setID(efmGoMst.getId().getGoNum());
				labelBean.setVALUE(efmGoMst.getGoTitle());
				lGoData.add(labelBean);
				
			}
			
	}
	catch(Exception e)
	{
		GLOGGER.error("Exception occurred in getGoDetails() in CommonServiceImpl class."+e.getMessage());
	}
		return lGoData;
	}
	*/
	
	public List<LabelBean> getHospitals() throws Exception {
		List<LabelBean> hospitalList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.hospId as ID,(AH.hospName||'--'||AH.hospId) as VALUE,AH.hospName as HOSPNAME from EhfmHospitals AH where taashaHosp =?  order by AH.hospName asc");
			//where AH.taashaHosp='Y'
			hospitalList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getHospitals() in CommonServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}

	/**
     * ;
     * @param String parntCode
     * @return List<LabelBean> personalHistoryList
     * @function This Method is Used For getting Personal History List
     */
	
	public List<LabelBean> getPersonalHistory(String parntCode) throws Exception {
		List<LabelBean> historyList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eph.code as ID,eph.description as VALUE from EhfmPersonalHistoryMst eph where eph.parntCode=? and eph.activeYN=?");
			historyList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{parntCode,"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getPersonalHistory() in CommonServiceImpl class."+e.getMessage());
		}
		return historyList;
	}
	/**
     * ;
     * @param String parntCode
     * @return List<LabelBean> personalHistoryList
     * @function This Method is Used For getting Personal History List
     */
	@Override
	public List<LabelBean> getDentalPersonalHistory(String parntCode) throws Exception {
		List<LabelBean> historyList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eph.code as ID,eph.description as VALUE from EhfmPersonalHistoryMst eph where eph.parntCode=? and eph.activeYN=? and eph.code not in (?,?) ");
			historyList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getPersonalHistory() in CommonServiceImpl class."+e.getMessage());
		}
		return historyList;
	}
	
	/**
     * ;
     * @return List<LabelBean> familyHistoryList
     * @function This Method is Used For getting Family History List
     */
	
	public List<LabelBean> getFamilyHistory() throws Exception {
		List<LabelBean> historyList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select efh.code as ID,efh.description as VALUE from EhfmFamilyHistoryMst efh where efh.activeYN=?");
			historyList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getFamilyHistory() in CommonServiceImpl class."+e.getMessage());
		}
		return historyList;
	}
	/**
     * ;
     * @return List<LabelBean> investigationsList
     * @function This Method is Used For getting Investigations List(Book Of Trust)
     */
	
	public List<LabelBean> getInvestigations() throws Exception {
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select eim.invCode as ID,eim.invName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN=? order by eim.invName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getInvestigations() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	/**
     * ;
     * @return List<LabelBean> drugsList
     * @function This Method is Used For getting Drugs List(Book Of Trust)
     */
	
	public List<LabelBean> getDrugs() throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugTypeCode as ID,edm.drugTypeName as VALUE from EhfmDrugsMst edm where edm.activeYN=? order by edm.drugTypeName asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	
	
	public List<LabelBean> getOpDrugsAuto() throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.chemicalCode as ID,edm.chemicalName as VALUE from EhfmOpDrugMst edm where edm.activeYN=? order by edm.chemicalName asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	
	
	public List<LabelBean> getAsriDrugs(String drugSubTypeId) throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.drugCode as  ID , edm.drugName ||' ( '|| edm.id.drugCode || ' )'  as VALUE from EhfmDrugsMst edm where edm.id.drugSubTypeCode=? and edm.activeYN=? order by edm.drugName ||' ( '|| edm.id.drugCode || ' )' asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{drugSubTypeId,"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	/**
     * ;
     * @return List<LabelBean> examinFndsList
     * @function This Method is Used For getting General Examination Findings
     */
	
	public List<LabelBean> getGenExaminFndgs(String schemeId) throws Exception {
		List<LabelBean> examinFndsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select efm.id.code as ID,efm.description as VALUE,efm.subFieldtype as LVL from EhfmGeneralExaminFndsMst efm where efm.activeYn=?and efm.id.schemeId=? order by to_number(substr(efm.id.code,'3'))");
			examinFndsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y",schemeId});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getGenExaminFndgs() in CommonServiceImpl class."+e.getMessage());
		}
		return examinFndsList;
	}
	@Override
	public List<LabelBean> getDentalGenExaminFndgs(String schemeId) throws Exception {
		List<LabelBean> examinFndsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select efm.id.code as ID,efm.description as VALUE,efm.subFieldtype as LVL from EhfmGeneralExaminFndsMst efm where efm.activeYn=? " );
					query.append("and efm.id.code in (?,?,?,?) and efm.id.schemeId=? order by to_number(substr(efm.id.code,'3'))");
			examinFndsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y","GE11","GE12","GE13","GE14",schemeId});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getGenExaminFndgs() in CommonServiceImpl class."+e.getMessage());
		}
		return examinFndsList;
	}
	
	public List<LabelBean> getPastIllnessHitory() throws Exception {
		List<LabelBean> pastIllnessList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select ephm.diseaseCode as ID,ephm.diseaseName as VALUE from EhfmPastHistoryMst ephm where ephm.activeYN=? ");
			pastIllnessList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getPastIllnessHitory() in CommonServiceImpl class."+e.getMessage());
		}
		return pastIllnessList;
	}
	
	public String getPastIllnessHitoryNames(String illnessHistory) 
	{
		this.illnessHistory=illnessHistory;
		String illnessHistoryLocal;
		String illnessHistoryFinal="";
		EhfmPastHistoryMst ehfmPastHistoryMst=new EhfmPastHistoryMst();
		String arr[]=illnessHistory.split("~");
		try
		{
				for(int i=0;i<arr.length;i++)
					{
						ehfmPastHistoryMst=genericDao.findById(EhfmPastHistoryMst.class,String.class,arr[i]);
		    			illnessHistoryLocal=ehfmPastHistoryMst.getDiseaseName();
		    			if(i==0)
		    				{	illnessHistoryFinal=illnessHistoryLocal;	}
		    			else
		    				{	illnessHistoryFinal=illnessHistoryFinal+","+illnessHistoryLocal;  }
					}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getPastIllnessHitoryNames() in CommonServiceImpl class."+e.getMessage());
		}
		
		return illnessHistoryFinal;
	}
	
	public String getFamilyHisNames(String familyHis) 
	{
		this.familyHis=familyHis;
		String familyHisLocal;
		String familyHisFinal="";
		EhfmFamilyHistoryMst ehfmFamilyHistoryMst=new EhfmFamilyHistoryMst();
		String arr[]=familyHis.split("~");
		try
		{
				for(int i=0;i<arr.length;i++)
					{
					ehfmFamilyHistoryMst=genericDao.findById(EhfmFamilyHistoryMst.class,String.class,arr[i]);
						familyHisLocal=ehfmFamilyHistoryMst.getDescription();
		    			if(i==0)
		    				{	familyHisFinal=familyHisLocal;	}
		    			else
		    				{	familyHisFinal=familyHisFinal+","+familyHisLocal;  }
					}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getFamilyHisNames() in CommonServiceImpl class."+e.getMessage());
		}
		
		return familyHisFinal;
	}
	
	
	public List<LabelBean> getAllDiagnosisCat() throws Exception {
		List<LabelBean> diaCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.diaCatId as ID,AH.diaCatName as VALUE from EhfmDiagnosisCatMst AH where AH.activeYN=? order by AH.diaCatName");
			diaCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAllDiagnosisCat() in CommonServiceImpl class."+e.getMessage());
		}
		return diaCatList;
	}
	
	public List<LabelBean> getDiagnosisSubCat(String lStrDiagCat) {
		List<LabelBean> diaSubCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.diaSubCatId as ID,AH.diaSubCatName as VALUE from EhfmDiagnosisSubCatMst AH where AH.activeYN=? and AH.diaCatId=? order by AH.diaSubCatName");
			diaSubCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y",lStrDiagCat});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getDiagnosisSubCat() in CommonServiceImpl class."+e.getMessage());
		}
		return diaSubCatList;
	}
	@Override
	public List<LabelBean> getMainSymptonLst() {
		List<LabelBean> mainSymptomList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.id.mainSymptomCode as ID,AH.mainSymptomName as VALUE from EhfmSystematicExamFnd AH where AH.activeYn=?  order by AH.mainSymptomName asc");
			mainSymptomList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getMainSymptonLst() in CommonServiceImpl class."+e.getMessage());
		}
		return mainSymptomList;
	}
	@Override
	public List<LabelBean> getDentalMainSymptonLst() {
		List<LabelBean> mainSymptomList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.id.mainSymptomCode as ID,AH.mainSymptomName as VALUE from EhfmSystematicExamFnd AH where AH.activeYn=? ");
			query.append(" and AH.id.mainSymptomCode in (?)");
			query.append(" order by AH.mainSymptomName asc");
			mainSymptomList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y","K00-K13"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getMainSymptonLst() in CommonServiceImpl class."+e.getMessage());
		}
		return mainSymptomList;
	}
	
	public List<LabelBean> getAsriCategoryList(String hospId,String schemeId) {
		List<LabelBean> asriCatList=new ArrayList<LabelBean>(); 
		String scheme=schemeId;
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			//query.append("select distinct cm.id.asriCatCode as ID , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE from EhfmTherapyCatMst cm,EhfmSpecialities am where am.disMainId=cm.id.asriCatCode and cm.activeYN='Y'  ");
			query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmHospCatMst ehc,EhfmSpecialities adm,EhfmMainTherapyNabh em where em.id.asriCode=adm.id.disMainId and em.id.process in (?,?,?)  and em.id.state ='CD202' and ehc.id.hospId=? and ehc.isActvFlg=? and ehc.id.catId=adm.disMainId and ehc.id.schemeId='CD202' order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc");
			asriCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"IP","DOP","IPM",hospId,"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAsriCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return asriCatList;
	}
	
	
	public List<String> getICDCategoryList(String asriCatCode,String procType) {
		//List<String> icdCatList1=new ArrayList<String>(); 
		List<LabelBean> icdCatList1 = new ArrayList<LabelBean>();
		List<String> icdCatList=new ArrayList<String>(); 
		Iterator catItr=null;
		EhfmTherapyCatMst ehfmTherapyCatMst=null;
		EhfmTherapyProcMst ehfmTherapyProcMst=null;
		try
		{
			/*List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>(); 
    		criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y",GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
         	criteriaList.add(new GenericDAOQueryCriteria("id.asriCatCode",asriCatCode,GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
			criteriaList.add(new GenericDAOQueryCriteria("icdCatName",null,GenericDAOQueryCriteria.CriteriaOperator.ASC));
		    List<EhfmTherapyCatMst> ehfmTherapyCatList = genericDao.findAllByCriteria(EhfmTherapyCatMst.class, criteriaList);
		    catItr=ehfmTherapyCatList.iterator();
		    while(catItr.hasNext())
    		{
		    	ehfmTherapyCatMst=(EhfmTherapyCatMst)catItr.next();    	
		    	icdCatList.add(ehfmTherapyCatMst.getId().getIcdCatCode()+"~"+ehfmTherapyCatMst.getIcdCatName()+"("+ehfmTherapyCatMst.getId().getIcdCatCode()+")@");
    		}*/
    		StringBuffer query = new StringBuffer();
			query.append("select distinct cm.id.icdCatCode as ID ,cm.icdCatName  as VALUE from EhfmTherapyCatMst cm , EhfmTherapyProcMst etp where cm.id.asriCatCode='"+asriCatCode+"' and cm.activeYN='Y' ");
			query.append(" and cm.id.asriCatCode=etp.id.asriCode and  cm.id.icdCatCode=etp.icdCatCode and etp.id.state=?  ");
			String[] args;
			if(!"".equalsIgnoreCase(procType) && "IPD".equalsIgnoreCase(procType))
			{
				query.append(" and etp.id.process in (?) ");
				args = new String[2];
				args[0] = "CD202";
				args[1] = "IPD";
			}
			else if(!"".equalsIgnoreCase(procType) && "IPM".equalsIgnoreCase(procType))
			{
				query.append(" and etp.id.process in (?) ");
				args = new String[2];
				args[0] = "CD202";
				args[1] = "IPM";
			}
			else
			{
				query.append(" and etp.id.process in (?,?) ");
				args = new String[3];
				args[0] = "CD202";
				args[1] = "IP";
				args[2] = "DOP";
			}
			icdCatList1=genericDao.executeHQLQueryList (LabelBean.class,query.toString(),args);
			
			for(int i=0; i<icdCatList1.size();i++)
			{
			   icdCatList.add(icdCatList1.get(i).getID()+"~"+icdCatList1.get(i).getVALUE()+"("+icdCatList1.get(i).getID()+")@");
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getICDCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return icdCatList;
	}
	
	public List<LabelBean> getDynamicWrkFlowDtls(String pCurrStatus,
			String pCurrRole,String mainModule,String subModule) {
		List<LabelBean> buttonList = new ArrayList<LabelBean>();
        StringBuffer query=null;
    	try
    	{
    		query= new StringBuffer();
    		query.append("select ew.buttonName as ID ,ac.cmbDtlName as VALUE from EhfmWorkflow ew,EhfmCmbDtls ac where ew.buttonName=ac.cmbDtlId and ew.currStatus=? and ew.currRole=? " );
    		query.append(" and ew.mainModule=? and ew.subModule=? and ew.activeYn = ? order by ac.cmbDtlName");
    		buttonList = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),new String[]{pCurrStatus,pCurrRole,mainModule,subModule,"Y" });
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getDynamicWrkFlowDtls() in CommonServiceImpl class."+e.getMessage());
    	}
    	finally
    	{
    	}
    	return buttonList;
	}
	/**
	 * get list of asri categories mapped with userRoles
	 */
	
	public List<LabelBean> getCategoryList(String userId, String userRole)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		StringBuffer query = new StringBuffer();
		String[] args ;
		if(userId!=null && !"".equalsIgnoreCase(userRole) && userRole != null && !"".equalsIgnoreCase(userRole) && config.getString("preauth_medco_role").equalsIgnoreCase(userRole))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmMedcoDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.medcoId =? and md.id.hospId = hc.id.hospId    and  cm.activeYN =?  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg =?  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
			args = new String[3];
			args[0] = userId;
			args[1] = "Y";
			args[2] = "Y";
		}
		else if(userId!=null && !"".equalsIgnoreCase(userRole) && userRole != null && !"".equalsIgnoreCase(userRole) && config.getString("preauth_mithra_role").equalsIgnoreCase(userRole))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmHospMithraDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.mithraId =? and md.id.hospId = hc.id.hospId    and  cm.activeYN =?  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg =?  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
			args = new String[3];
			args[0] = userId;
			args[1] = "Y";
			args[2] = "Y";
		}
		else
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am " );
			query.append(" where cm.activeYN =?  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
			args = new String[1];
			args[0] = "Y";
		}	
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	public List<LabelBean> getCategorys(String pStrCatId,String userId,String caseId)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		String patientType="";
		try
		{
		StringBuffer query = new StringBuffer();
		/**
		 * get list of asri categories mapped with asrim diseases and medco with hospital mapping
		 */
		if(!"".equalsIgnoreCase(caseId) && caseId != null)
		{
			EhfCase ehfCaseObj = genericDao.findById(EhfCase.class, String.class, caseId);
			if(ehfCaseObj != null)
			{
				EhfPatient ehfPat = genericDao.findById(EhfPatient.class, String.class, ehfCaseObj.getCasePatientNo());
				if(ehfPat != null)
				{
					if(ehfPat.getPatientIpop() != null)
						patientType=ehfPat.getPatientIpop().toString();
				}
			}
		}
		String[] args = null ;
		if(pStrCatId == null || pStrCatId.equalsIgnoreCase(""))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmMedcoDtls md , EhfmHospCatMst hc, EhfmMainTherapyNabh em" );
			query.append(" where md.id.medcoId =? and md.id.hospId = hc.id.hospId    and  cm.activeYN =?  ");
			query.append(" and hc.id.catId = am.id.disMainId  and hc.isActvFlg =? and em.id.state=? and em.id.asriCode=am.id.disMainId and cm.id.asriCatCode = am.id.disMainId  ");
			if(!"".equalsIgnoreCase(patientType) && config.getString("PatientIPOP.IPD").equalsIgnoreCase(patientType))
			{
				query.append(" and  em.id.process in (?)  ");
				args = new String[5];
				args[0] = userId;
				args[1] = "Y";
				args[2] = "Y";
				args[3] = "CD202";
				args[4] = "IPD";
			}
			else
			{
				query.append(" and  em.id.process in (?,?)  ");
				args = new String[6];
				args[0] = userId;
				args[1] = "Y";
				args[2] = "Y";
				args[3] = "CD202";
				args[4] = "IP";
				args[5] = "DOP";
			}
			query.append(" order by am.disMainName asc ");
			
		}
		
		if(pStrCatId != null && !pStrCatId.equalsIgnoreCase(""))
		{
			
			query = new StringBuffer();
			query.append(" select  distinct  cm.id.icdCatCode as ICDCODE , cm.icdCatName||' ( '||cm.id.icdCatCode ||' )'  as ICDNAME , cm.icdCatName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm,EhfmTherapyProcMst etp where cm.activeYN =? and  etp.id.state=?   and etp.id.asriCode=cm.id.asriCatCode and etp.icdCatCode=cm.id.icdCatCode ");	
			query.append(" and  cm.id.asriCatCode = ? 	");
			
			if(!"".equalsIgnoreCase(patientType) && config.getString("PatientIPOP.IPD").equalsIgnoreCase(patientType))
			{
				query.append(" and etp.id.process in (?)  ");
				args = new String[4];
				args[0] = "Y";
				args[1] = "CD202";
				args[2] = pStrCatId;
				args[3] = "IPD";
			}
			else
			{
				query.append(" and etp.id.process in (?,?,?) ");
				args = new String[6];
				args[0] = "Y";
				args[1] = "CD202";
				args[2] = pStrCatId;
				args[3] = "IP";
				args[4] = "DOP";
				args[5] = "IPM";
			}
			query.append(" order by cm.icdCatName asc ");
		}
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	public List<LabelBean> getICDCategoryList(String pStrCatId,String userId,String hosType)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		StringBuffer query = new StringBuffer();
		/**
		 * get list of asri categories mapped with asrim diseases and medco with hospital mapping
		 */
		String[] args = null;
		if(pStrCatId == null || pStrCatId.equalsIgnoreCase(""))
		{
			query.append(" select distinct cm.id.asriCatCode as ID  , am.disMainName||' ( '|| cm.id.asriCatCode || ' )'  as VALUE ,am.disMainName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm , EhfmSpecialities am, EhfmMedcoDtls md , EhfmHospCatMst hc " );
			query.append(" where md.id.medcoId =? and md.id.hospId = hc.id.hospId    and  cm.activeYN =?  ");
			query.append(" and hc.id.catId = am.disMainId  and hc.isActvFlg =?  ");
			query.append(" and cm.id.asriCatCode = am.disMainId order by am.disMainName asc ");
			args = new String[3];
			args[0] = userId;
			args[1] = "Y";
			args[2] = "Y";
			
		}
		
		if(pStrCatId != null && !pStrCatId.equalsIgnoreCase("")  && hosType.equals("DC"))
		{
			query = new StringBuffer();
			query.append("select  distinct  cm.id.icdCatCode as ICDCODE , cm.icdCatName||' ( '||cm.id.icdCatCode ||' )'  as ICDNAME , cm.icdCatName as UNITID ");
			query.append("FROM  EhfmTherapyCatMst cm, EhfmTherapyProcMst m where cm.id.icdCatCode=m.icdCatCode and  cm.id.asriCatCode = ? and cm.activeYN =? and m.id.state=? and m.icdCatCode not in ");
            query.append("(SELECT distinct mm.icdCatCode FROM EhfmTherapyProcMst mm  where mm.id.asriCode=? and mm.id.process=? and mm.id.state=? and mm.activeYN=? and mm.icdCatCode not in ( SELECT distinct mt.icdCatCode FROM EhfmTherapyProcMst mt where mt.id.asriCode=? and mt.id.process=? and mt.id.state=? and mt.activeYN=? group by mt.icdCatCode))");
            query.append("order by cm.id.icdCatCode asc");
            args = new String[12];
			args[0] = pStrCatId;
			args[1] = "Y";
			args[2] = "CD202";
			args[3] = "S18";
			args[4] = "IP";
			args[5] = "CD202";
			args[6] = "Y";
			args[7] = "S18";
			args[8] = "DOP";
			args[9] = "Y";
			args[10] = "CD202";
			args[11] = "Y";
		}
		if(pStrCatId != null && !pStrCatId.equalsIgnoreCase("")  && hosType.equals("HOSPITAL"))
		{
			query = new StringBuffer();
			query.append(" select  distinct  cm.id.icdCatCode as ICDCODE , cm.icdCatName||' ( '||cm.id.icdCatCode ||' )'  as ICDNAME , cm.icdCatName as UNITID ");
			query.append(" from EhfmTherapyCatMst cm where cm.activeYN =?");	
			query.append(" and  cm.id.asriCatCode = ? order by cm.icdCatName asc	");
			args = new String[2];
			args[0] = "Y";
			args[1] = pStrCatId;
		}
		
		
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	@Override
	public List<LabelBean> getProcedures(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
	    String hospType=null;
	    EhfmHospitals ehfmHospitals=null;
	    if(pStrHospId!=null && !"".equalsIgnoreCase(pStrHospId))
	    	ehfmHospitals = genericDao.findById(EhfmHospitals.class, String.class, pStrHospId);
		if(ehfmHospitals!= null )
		{
			hospType = 	ehfmHospitals.getHospType()+"";
		}
		
	    StringBuffer query = new StringBuffer();
		query.append(" select distinct pm.id.icdProcCode as ICDCODE , pm.procName||' ( '||pm.id.icdProcCode ||' )'  as ICDNAME , pm.procName as UNITID ");
		query.append(" from EhfmTherapyProcMst pm where pm.activeYN =? ");
		ArrayList<String> list = new ArrayList<String>();
		list.add("Y");
		
			
		if(pStrIcdCatCode != null && !pStrIcdCatCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.icdCatCode = ?	");
			list.add(pStrIcdCatCode);
		}
		if(pStrAsriCode != null && !pStrAsriCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.id.asriCode = ?	");
			list.add(pStrAsriCode);
		}
		if(hospType != null && !"".equalsIgnoreCase(hospType) && hospType.equalsIgnoreCase("C")){
			query.append(" and (	pm.govOrCorp  not in (?) OR   pm.govOrCorp IS NULL)");
			list.add("G");
		}	
		query.append(" and pm.id.state=? ");
		list.add(Scheme);
		if(dentalEnhFlag!=null && !("").equalsIgnoreCase(dentalEnhFlag) && ("Y").equalsIgnoreCase(dentalEnhFlag)){
			query.append(" and pm.dentalEnhFlag=? ");
			list.add(dentalEnhFlag);
		}
		String[] args = null;
		query.append(" order by pm.procName asc ");
		if(list!=null && list.size()>0){
			args = new String[list.size()];
			for(int i=0;i<list.size();i++){
				args[i] = list.get(i);
			}
		}
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	@Override
	public List<LabelBean> getProceduresNABH(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag,String ipFlag)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		ArrayList<String> list = new ArrayList<String>();
	    String hospType=null;
	    EhfmHospitals ehfmHospitals=null;
	    if(pStrHospId!=null && !"".equalsIgnoreCase(pStrHospId))
	    	ehfmHospitals = genericDao.findById(EhfmHospitals.class, String.class, pStrHospId);
		if(ehfmHospitals!= null )
		{
			hospType = 	ehfmHospitals.getHospType()+"";
		}
		
	    StringBuffer query = new StringBuffer();
		query.append(" select distinct pm.id.icdProcCode as ICDCODE , pm.procName||' ( '||pm.id.icdProcCode ||' )'  as ICDNAME , pm.procName as UNITID ");
		query.append(" from EhfmTherapyProcMst pm where pm.activeYN =? and ");
		list.add("Y");
		if(!"".equalsIgnoreCase(ipFlag) && "IPD".equalsIgnoreCase(ipFlag))
		{
			query.append("  pm.id.process in (?) ");
			list.add("IPD");
		}
		else if (!"".equalsIgnoreCase(ipFlag) && "IPM".equalsIgnoreCase(ipFlag))
		{
			query.append("  pm.id.process in (?) ");
			list.add("IPM");
		}
		else
		{
			query.append("  pm.id.process in (?,?,?) ");
			list.add("IP");
			list.add("DOP");
			list.add("IPM");
		}
				
			
		if(pStrIcdCatCode != null && !pStrIcdCatCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.icdCatCode = ?	");
			list.add(pStrIcdCatCode);
		}
		if(pStrAsriCode != null && !pStrAsriCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.id.asriCode = ?	");
			list.add(pStrAsriCode);
		}
		if(hospType != null && !"".equalsIgnoreCase(hospType) && hospType.equalsIgnoreCase("C")){
			query.append(" and (	pm.govOrCorp  not in (?) OR   pm.govOrCorp IS NULL)");
			list.add("G");
		}
		query.append(" and pm.id.state=? ");
		list.add(Scheme);
		if(dentalEnhFlag!=null && !("").equalsIgnoreCase(dentalEnhFlag) && ("Y").equalsIgnoreCase(dentalEnhFlag)){
			query.append(" and pm.dentalEnhFlag=? ");
			list.add(dentalEnhFlag);
		}
		query.append(" order by pm.procName asc ");
		String[] args = null;
		if(list!=null && list.size()>0){
			args = new String[list.size()];
			for(int i=0;i<list.size();i++){
				args[i] = list.get(i);
			}
		}
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	public List<LabelBean> getICDProcedures(String pStrIcdCatCode , String pStrAsriCode ,String pStrHospId,String Scheme,String dentalEnhFlag)
	{
		List<LabelBean> lstCat = new ArrayList<LabelBean>();
		try
		{
		ArrayList<String> list = new ArrayList<String>();
	    String hospType=null;
	    EhfmHospitals ehfmHospitals=null;
	    if(pStrHospId!=null && !"".equalsIgnoreCase(pStrHospId))
	    	ehfmHospitals = genericDao.findById(EhfmHospitals.class, String.class, pStrHospId);
		if(ehfmHospitals!= null )
		{
			hospType = 	ehfmHospitals.getHospType()+"";
		}
		
	    StringBuffer query = new StringBuffer();
		query.append(" select distinct pm.id.icdProcCode as ICDCODE , pm.procName||' ( '||pm.id.icdProcCode ||' )'  as ICDNAME , pm.procName as UNITID ");
		query.append(" from EhfmTherapyProcMst pm ");
		query.append(" where pm.activeYN = ? and pm.id.process =? ");	
		list.add("Y");
		list.add("DOP");
		if(pStrIcdCatCode != null && !pStrIcdCatCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.icdCatCode = ?	");
			list.add(pStrIcdCatCode);
		}
		if(pStrAsriCode != null && !pStrAsriCode.equalsIgnoreCase(""))
		{
			query.append(" and  pm.id.asriCode = ?	");
			list.add(pStrAsriCode);
		}
		if(hospType != null && !"".equalsIgnoreCase(hospType) && hospType.equalsIgnoreCase("C")){
			query.append(" and (	pm.govOrCorp  not in (?) OR   pm.govOrCorp IS NULL)");
			list.add("G");
		}
		query.append(" and pm.id.state=? ");
		list.add(Scheme);
		if(dentalEnhFlag!=null && !("").equalsIgnoreCase(dentalEnhFlag) && ("Y").equalsIgnoreCase(dentalEnhFlag)){
			query.append(" and pm.dentalEnhFlag=? ");
			list.add(dentalEnhFlag);
		}
		query.append(" order by pm.procName asc ");
		String[] args = null;
		if(list!=null && list.size()>0){
			args = new String[list.size()];
			for(int i=0;i<list.size();i++){
				args[i] = list.get(i);
			}
		}
		lstCat = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		return lstCat;
	}
	
	/**
     * ;
     * @param PatientSmsVO patientSmsVO
     * @return String Msg
     * @function This method is used for sending sms to the specified patient
     */
	
	public String sendPatientSms(PatientSmsVO patientSmsVO) {
		String lStrResultMsg=null;
		try
		{
			//SendSMS sendSms =new SendSMS();
			//lStrResultMsg=sendSms.sendSMS(patientSmsVO.getSmsText(),patientSmsVO.getPhoneNo());
			GLOGGER.info("Sms Result Msg :"+lStrResultMsg);
			if("Y".equalsIgnoreCase(config.getString("AsriSms"))){
				Map<String,Object> lMap = new HashMap<String,Object>();
				String templateId="1407162124390244733";
				lMap.put("msg", patientSmsVO.getSmsText());
				lMap.put("mobile",patientSmsVO.getPhoneNo());
				lMap.put("templateId",templateId);
				lStrResultMsg = this.saveToAsritSms(lMap);
		  }
			EhfPatientSmsData ehfPatientSmsData=new EhfPatientSmsData();
			//ehfPatientSmsData.setSerialNo(patientSmsVO.getSerialNo());
			ehfPatientSmsData.setPhoneNo(patientSmsVO.getPhoneNo());
			ehfPatientSmsData.setSmsText(patientSmsVO.getSmsText());
			ehfPatientSmsData.setEmpCode(patientSmsVO.getEmpCode());
			ehfPatientSmsData.setEmpName(patientSmsVO.getEmpName());
			ehfPatientSmsData.setCrtUsr(patientSmsVO.getCrtUsr());
			ehfPatientSmsData.setCrtDt(patientSmsVO.getCrtDt());
			ehfPatientSmsData.setSmsAction(patientSmsVO.getSmsAction());
			ehfPatientSmsData.setPatientId(patientSmsVO.getPatientId());
			genericDao.save(ehfPatientSmsData);
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in sendPatientSms() in CommonServiceImpl class."+e.getMessage());
		}
		return lStrResultMsg;
	}
	/**
     * ;
     * @param EmailVO emailVO
     * @param Map model
     * @function This method is used for sending Email to the specified patient
     */
	
	public void generateMail(EmailVO emailVO, Map model) {
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(emailVO.getFromEmail());
			mailMessage.setBcc(emailVO.getCcBcc());
			mailMessage.setCc(emailVO.getCcEmail());
			mailMessage.setSubject(emailVO.getSubject());
			mailMessage.setTo(emailVO.getToEmail());
			mailMessage
					.setSentDate(new java.sql.Timestamp(new Date().getTime()));

			emailNotifier.sendImageMessage(mailMessage, emailVO.getTemplateName(),
					model, emailVO.getTemplateSource());
		} catch (Exception e) {
//			GLOGGER.error("Exception occurred in generateMail() in CommonServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * @function This method is used for sending Email to the specified receipent without any images
	 * @param emailVO
	 * @param model
	 */
	public void generateNonImageMail(EmailVO emailVO,Map model)
		{
			try{
					SimpleMailMessage mailMessage=new SimpleMailMessage();
					mailMessage.setFrom(emailVO.getFromEmail());
					mailMessage.setBcc(emailVO.getCcBcc());
					mailMessage.setCc(emailVO.getCcEmail());
					mailMessage.setSubject(emailVO.getSubject());
					mailMessage.setTo(emailVO.getToEmail());
					mailMessage
							.setSentDate(new java.sql.Timestamp(new Date().getTime()));
					
				//	emailNotifier.sendEHSMessage(mailMessage, emailVO.getTemplateName(),
				//	model, emailVO.getTemplateSource());
				}
			catch (Exception e) {
//				GLOGGER.error("Exception occurred in generateNonImageMail() in CommonServiceImpl class."+e.getMessage());
				e.printStackTrace();
			}
		}
	/**
     * ;
     * @return String sno
     * @function This method is used for getting primary key(sno) max count value for sequence id
     */
	
	public Long getCaseTherapyInvestId() {
		Long sno=0L;
		SessionFactory factory=null;
		Session session=null;
		Iterator caseInvestItr=null;
        StringBuffer caseInvestQuery=null;
        try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		caseInvestQuery= new StringBuffer();
    		caseInvestQuery.append("select max(cti.sno) as sno from EhfCaseTherapyInvest cti");
            Query caseInvestlst=session.createQuery(caseInvestQuery.toString());
            caseInvestItr=caseInvestlst.iterate();
            if(caseInvestItr.hasNext())
            {
            sno=((Long) caseInvestItr.next()).longValue();
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getPhaseId() in CommonServiceImpl class."+e.getMessage());
    	}
        finally
    	{
    		session.close();
    		factory.close();
    	}
		return sno;
	}
	
	public void sendEmailMessage(){
//		System.out.println("in Common service for sending Email");
		EmailVO emailVO = new EmailVO();
		Map<String, String> model = new HashMap<String, String>();
		
        StringBuffer patientQuery=null;
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(config.getString("emailFrom"));
			mailMessage.setSubject("Wish You A Happy Independence Day");			
			
    		patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.email as ID from AsrimUsers a where a.activeYn='Y' and a.email is not null ");
    		List<LabelBean> asrimusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : asrimusrList){
    			String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    			model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    			model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendImageIndMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.middleName as CONST ,a.emailId as ID from EhfmUsers a where a.serviceFlag='Y' and a.emailId is not null ");
    		List<LabelBean> ehfmusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : ehfmusrList){
    			String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase("") && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    			    model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST()+" "+labelBean.getSUBNAME());
    			else if(labelBean.getSUBNAME()==null && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST());
    			else if(labelBean.getCONST()==null && labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    				model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendImageIndMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		 	
    		String msgEmailTo= config.getString("ehfIndMsgTo");
    		StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
    		while (token.hasMoreElements()) {
    			String mailId=(String) token.nextElement();
    			
        		model.put("patientName", "Team");
    			
    			mailMessage.setTo(mailId);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendImageIndMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		
			
		} catch (Exception e) {
//			GLOGGER.error("Exception occurred in generateMail() in CommonServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void sendNewYearEmailMessage(){
//		System.out.println("in Common service for sending Email");
		boolean test=config.getString("EmailTesting").equalsIgnoreCase("Y");
		Map<String, String> model = new HashMap<String, String>();
        StringBuffer patientQuery=null;
		try {
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(config.getString("emailFrom"));
			mailMessage.setSubject(config.getString("NewYrSubject"));			
			String othersEmails=config.getString("OtherEmails");
			String[] emailIds=othersEmails.split("~");
    		for(String emailId : emailIds){
    			String[] emailToArray = {emailId};
    			model.put("patientName", emailId);
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendNewYearMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		if(test)
    			return;
    		patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.email as ID from AsrimUsers a where a.activeYn='Y' and a.email is not null ");
    		List<LabelBean> asrimusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : asrimusrList){
    			String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    			model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    			model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			//emailNotifier.sendNewYearMessage(mailMessage, config.getString("EhfMessageTemplateName"),
    			//		model, EmailConstants.TEMPLATE_FROM_FA);
    			return;
    		}
    		/*patientQuery= new StringBuffer();
    		patientQuery.append(" select distinct a.firstName as EMPNAME,a.lastName as SUBNAME,a.middleName as CONST ,a.emailId as ID from EhfmUsers a where a.serviceFlag='Y' and a.emailId is not null ");
    		List<LabelBean> ehfmusrList = genericDao.executeHQLQueryList(LabelBean.class, patientQuery.toString());
    		for(LabelBean labelBean : ehfmusrList){
    			//String[] emailToArray = {labelBean.getID()};
    			if(labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase("") && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    			    model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST()+" "+labelBean.getSUBNAME());
    			else if(labelBean.getSUBNAME()==null && labelBean.getCONST()!=null && !labelBean.getCONST().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getCONST());
    			else if(labelBean.getCONST()==null && labelBean.getSUBNAME()!=null && !labelBean.getSUBNAME().equalsIgnoreCase(""))
    				model.put("patientName", labelBean.getEMPNAME()+" "+labelBean.getSUBNAME());
    			else
    				model.put("patientName", labelBean.getEMPNAME());
    			    			
    			mailMessage.setTo(emailToArray);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			emailNotifier.sendNewYearMessage(mailMessage, config.getString("EHFNewYearMsg"),
    					model, EmailConstants.TEMPLATE_FROM_FA);
    		}
    		 	
    		String msgEmailTo= config.getString("ehfIndMsgTo");
    		StringTokenizer token = new StringTokenizer(msgEmailTo,"$");
    		while (token.hasMoreElements()) {
    			String mailId=(String) token.nextElement();
    			
        		model.put("patientName", "Team");
    			
    			mailMessage.setTo(mailId);
    			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));

    			emailNotifier.sendNewYearMessage(mailMessage, config.getString("EHFNewYearMsg"),
    					model, EmailConstants.TEMPLATE_FROM_FA);
    		}*/
    		
			
		} catch (Exception e) {
//			GLOGGER.error("Exception occurred in generateMail() in CommonServiceImpl class."+e.getMessage());
			e.printStackTrace();
		}
	}
	/**
     * ;
     * @param String userId
     * @param String caseId
     * @param String lockStatus
     * @return int recordsInserted
     * @function This method is used for locking a specific case against a specified user
     */
	
	public int lockCase(String userId, String caseId, String lockStatus) {
		
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date crtDt=null;
		EhfCaseLockStatus ehfCaseLockStatus=null;
		try {
			crtDt=sdfw.parse(sdfw.format(new Date()));
		} catch (ParseException e) {
			GLOGGER.error("Exception occurred in lockCase() in CommonServiceImpl class."+e.getMessage());
		}
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put("caseId",caseId);
		keyMap.put("userId", userId);
		ehfCaseLockStatus=genericDao.findFirstByPropertyMatch(EhfCaseLockStatus.class, keyMap);
		if(ehfCaseLockStatus!=null)
		{
			ehfCaseLockStatus.setLockStatus(lockStatus);
			ehfCaseLockStatus.setLstUpdDt(crtDt);
			ehfCaseLockStatus.setLstUpdUsr(userId);
			ehfCaseLockStatus = genericDao.save(ehfCaseLockStatus);
		}
		else
		{
			if("Y".equals(lockStatus))
			{
			ehfCaseLockStatus=new EhfCaseLockStatus();
			EhfmSeq ehfmLockSeq=getSeqNextVal("LOCK_SNO");
			int seqId=ehfmLockSeq.getSeqNextVal().intValue();
			ehfmLockSeq.setSeqNextVal(ehfmLockSeq.getSeqNextVal()+1);
			updateSequenceVal(ehfmLockSeq);
			ehfCaseLockStatus.setSno(seqId);
			ehfCaseLockStatus.setUserId(userId);
			ehfCaseLockStatus.setCaseId(caseId);
			ehfCaseLockStatus.setLockStatus(lockStatus);
			ehfCaseLockStatus.setCrtDt(crtDt);
			ehfCaseLockStatus.setCrtUsr(userId);
			ehfCaseLockStatus = genericDao.save(ehfCaseLockStatus);
			}
		}
		if(ehfCaseLockStatus!=null)
		{
			return 1;
		}
		return 0;
	}
	/**
     * ;
     * @param String userId
     * @function This method is used for unlocking cases locked by a specified user
     */
	
	public void unlockCases(String userId) {
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		Date crtDt=null;
		try {
			crtDt=sdfw.parse(sdfw.format(new Date()));
		} catch (ParseException e) {
			GLOGGER.error("Exception occurred in lockCase() in CommonServiceImpl class."+e.getMessage());
		}
		List<EhfCaseLockStatus> lockedCasesList=null;
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put("userId", userId);
		keyMap.put("lockStatus","Y");
		lockedCasesList=genericDao.findAllByPropertyMatch(EhfCaseLockStatus.class, keyMap);
		if(lockedCasesList!=null && lockedCasesList.size()>0)
		{
			for(EhfCaseLockStatus ehfCaseLockStatus:lockedCasesList)
			{
				ehfCaseLockStatus.setLockStatus("N");
				ehfCaseLockStatus.setLstUpdDt(crtDt);
				ehfCaseLockStatus.setLstUpdUsr(userId);
				genericDao.save(ehfCaseLockStatus);
			}
		}
	}
	/**
     * ;
     * @param String caseId
     * @function This method is used for getting lock status for a specific case
     */
	
	public String getLockStatus(String caseId) {
		String lockStatus=null;
		List<EhfCaseLockStatus> lockedList=null;
		Map<String,Object> keyMap=new HashMap<String,Object>();
		keyMap.put("caseId", caseId);
		keyMap.put("lockStatus", "Y");
		lockedList=genericDao.findAllByPropertyMatch(EhfCaseLockStatus.class, keyMap);
		if(lockedList!=null && lockedList.size()>0)
		{
			lockStatus="Y";
		}
		else
		{
			lockStatus="N";
		}
		return lockStatus;
	}
	
	
	public List<LabelBean> getInvestBlockName(){
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN=? order by eim.invMainName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	@Override
	public List<LabelBean> getInvestBlockNameNew(PatientVO patientVO){
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		String schemeId=patientVO.getSchemeId();
		String patientScheme=patientVO.getPatientScheme();
		String hospType=patientVO.getHospType();
		String hospId=patientVO.getHospitalCode();
		try
		{
			query = new StringBuffer();
			query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN=? ");
			if(hospId!=null && (config.getString("HOSP_NIMS").equalsIgnoreCase(hospId)) )
				query.append(" and eim.invMainCode  like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			/*if(schemeId!=null && hospType!=null &&     (   (config.getString("AP").equalsIgnoreCase(schemeId)) || !(config.getString("HOSP_NIMS").equalsIgnoreCase(hospId) )       ))*/
			else
				query.append(" and eim.invMainCode not like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			query.append( " order by eim.invMainName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	@Override
	public List<LabelBean> getDentalInvestBlockNameNew(PatientVO patientVO){
		List<LabelBean> investigationsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		String schemeId=patientVO.getSchemeId();
		String patientScheme=patientVO.getPatientScheme();
		String hospType=patientVO.getHospType();
		String hospId=patientVO.getHospitalCode();
		try
		{
			query = new StringBuffer();
			query.append("select distinct eim.invMainCode as ID,eim.invMainName as VALUE from EhfmGenInvestigationsMst eim where eim.activeYN=? ");
//			query.append(" and eim.invMainCode in ('I51','123','I01','I24','I68')");
			//if(hospId!=null && (config.getString("HOSP_NIMS").equalsIgnoreCase(hospId)) )
			//	query.append(" and eim.invMainCode  like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			/*if(schemeId!=null && hospType!=null &&     (   (config.getString("AP").equalsIgnoreCase(schemeId)) || !(config.getString("HOSP_NIMS").equalsIgnoreCase(hospId) )       ))*/
			//else
				query.append(" and eim.invMainCode not like  ('%"+config.getString("GeneralOPInvestId")+"%') ");
			query.append( " order by eim.invMainName asc");
			investigationsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getInvestBlockName() in CommonServiceImpl class."+e.getMessage());
		}
		return investigationsList;
	}
	
	public List<LabelBean> getOpDrugs(String pStrIpOpType) throws Exception {
		List<LabelBean> drugsList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.id.mainGrpCode as ID,edm.mainGrpName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpDrugMst edm ");
			else
				query.append(" EhfmOpDrugMst edm ");
			query.append(" where edm.activeYN=? order by edm.mainGrpName asc");
			drugsList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return drugsList;
	}
	
	public List<LabelBean> getRouteType(String pStrIpOpType) throws Exception {
		List<LabelBean> routeList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.routeTypeCode as ID,edm.routeTypeName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpRouteMst edm ");
			else
				query.append(" EhfmOpRouteMst edm ");
			query.append("   where edm.activeYN=? order by edm.routeTypeName asc");
			routeList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in getDrugs() in CommonServiceImpl class."+e.getMessage());
		}
		return routeList;
	}
	
	public List<LabelBean> getStrengthType(String pStrIpOpType) throws Exception {
		List<LabelBean> strengthList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct edm.strengthUnitCode as ID,edm.strengthUnitName as VALUE from ");
			if(pStrIpOpType != null && pStrIpOpType.equalsIgnoreCase("IP"))
				query.append(" EhfmIpStrengthMst edm ");
			else
				query.append(" EhfmOpStrengthMst edm ");
			query.append("  where edm.activeYN=? order by edm.strengthUnitName asc");
			strengthList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception occurred in strengthList() in CommonServiceImpl class."+e.getMessage());
		}
		return strengthList;
	}
	
	public String getSequence(String pStrSeqName) throws Exception {
     String lStrSeqRetVal = "";    
	    try{	     
	    	StringBuffer query = new StringBuffer();
	        query.append(" SELECT "+pStrSeqName+".NEXTVAL NEXTVAL  FROM DUAL ");
	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
	        if(seqList != null){	        	
	          lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
	        }
	    }catch(Exception e){	    
	    	e.printStackTrace();	    	
	    }	    
	    return lStrSeqRetVal;
	}
	
	public List<String> getDocListBySpec(String asriCatCode,String hospCode,String scheme) {
		List<LabelBean> docAvailLst = new ArrayList<LabelBean>();	
		List<String> docSpecList=new ArrayList<String>(); 
		try
		{
			StringBuffer query = new StringBuffer();
			query.append(" select z.id.regNum  as ID,z.splstName as  VALUE,z.title as CONST  ");
			query.append(" from EhfmSplstDctrs z , EhfmDotorSplty y where z.isActiveYn =? "); //and z.isConsultant='"+preauthVO.getConsultant()+"'  
			query.append(" and y.id.spctlyCode=? and z.id.regNum = y.id.regNum and z.id.regNum is not null "); //and z.apprvStatus = 'CD896'
			query.append(" and y.id.hospId = ? and z.id.hospId=? and z.id.scheme  in (?,?) and  z.id.scheme=y.scheme ");
			docAvailLst = genericDao.executeHQLQueryList(LabelBean.class, query.toString(),new String[]{"Y",asriCatCode,hospCode,hospCode,scheme,config.getString("COMMON")});
			for(LabelBean labelBean:docAvailLst)
			{
				docSpecList.add(labelBean.getID()+"~"+labelBean.getCONST()+" "+labelBean.getVALUE()+"@");	
			}
			if("G".equalsIgnoreCase(getHospType(hospCode)))
			{
				docSpecList.add("0~Others@");	
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}		
		return docSpecList;
	}
	
	public String getHospType(String hospId) {
		StringBuffer hqlQuery = new StringBuffer();
		String hospType=null;
  	  	List<LabelBean>  hospList=null;
  	  	hqlQuery.append("select cast(ah.hospType as string) as VALUE from EhfmHospitals ah where  ah.hospId = ? ");
  		hqlQuery.append("  and ah.hospActiveYN=? ");
  		hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),new String[]{hospId,"Y"});
  		if(hospList != null && hospList.size()>0 )
  			{
  				hospType=hospList.get(0).getVALUE();
  			}
  	  	return hospType;
	}
	public String getDutyDoctorById(String regNo) {
		String query="select distinct title||' '||doctorName as ID from EhfmDutyDctrs where id.regNum=? ";
		String user="";
		try
		{
			List<LabelBean> list=genericDao.executeHQLQueryList(LabelBean.class, query,new String[]{regNo});
			if(list!=null && !list.isEmpty())
			{
				user=list.get(0).getID();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	synchronized public void saveToBuffer(String message,String phoneList)
	{
		
		try
		{
			long val=Long.parseLong(String.valueOf(getSequence("SMS_BUFFER_SEQ")));
			EhfSmsBuffer ehfSmsBuffer=new EhfSmsBuffer();
			ehfSmsBuffer.setSerialNo(val+"");
			ehfSmsBuffer.setPhoneNo(phoneList);
			ehfSmsBuffer.setSmsText(message);
			ehfSmsBuffer.setResentYN("N");
			ehfSmsBuffer.setCrtDt(new Date());
			genericDao.save(ehfSmsBuffer);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
    public String getDecryptedPswd(String pStrLoginName){

		StringBuffer query = new StringBuffer();
		String[] args=new String[1];
		args[0]=pStrLoginName;
		query.append("  select DECRYPT_USER_PSWD(?) as ID from dual  ");
		List<LabelBean> lStPswd=genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
		if(lStPswd!=null)	
		return lStPswd.get(0).getID();
		return null;
	}
    
    public EhfmHospitals getHospInfo(String hospId) throws Exception
    {
    	EhfmHospitals ehfmHospitals=genericDao.findById(EhfmHospitals.class,String.class,hospId);
    	return ehfmHospitals;
    }
    
    public String ckDentalClinic(String userid, String caseId)
	{
		List<LabelBean> hospids;
		String query=" select b.hosp_id as HOSPID from ehfm_medco_dtls a , ehfm_hospitals b ,ehf_empnl_hospinfo c , ehfm_cmb_dtls d ,ehf_case e where a.medco_id=? and  e.case_id=?  and a.hosp_id=b.hosp_id and b.hosp_empnl_ref_num=c.hospinfo_id and c.applictn_type=d.cmb_dtl_id and c.applictn_type=? and e.scheme_id=? ";
		hospids=genericDao.executeSQLQueryList(LabelBean.class, query,new String[]{userid,caseId,"CD10001","CD202"});
		String hospID = "hospital";
		if(hospids!=null && hospids.size()>0)
	    {		
			hospID=hospids.get(0).getHOSPID(); 
	    }
		return hospID;
	}
	
	public String saveToAsritSms(Map<String,Object> lmap)
    {
    	String msg = "";
    	AsritSms asritsms = new AsritSms();
    	try{
    		asritsms.setSno(99999L);
	    	asritsms.setMessage((String)lmap.get("msg"));
	    	asritsms.setMobile((String)lmap.get("mobile"));
	    	asritsms.setSmsSent("N");
	    	asritsms.setCrtDt(new Timestamp(new Date().getTime()));
	    	asritsms.setCrtUsr("EHFUSERTS");
	    	asritsms.setStateCode("7");
	    	asritsms.setTemplateId((String)lmap.get("templateId"));
	    	asritsms = genericDao.save(asritsms);
	    	msg="success";
    	}
    	catch(Exception e)
    	{
    		msg="failure";
//    		GLOGGER.error("Exception in saveToAsritSms method of CommonServiceImpl "+e.getMessage());
    		e.printStackTrace();
    		
    	}
    	
  return msg;  	
    	    	
    }
	//sai krishna:CR#8134:API Integration Main query change.
	@Override
	public List<LabelBean> getGeneralInvest(String patientId,String dispId) {
		List<LabelBean> returnLst=new ArrayList<LabelBean>();
		try
			{
				if(patientId!=null)
					{
					EhfPatient patientClassObj=genericDao.findById(EhfPatient.class, String.class, patientId);
						if(patientClassObj!=null)
							{
								String gender=null,genderBoth=null;
								if(patientClassObj.getGender()!=null && !"".equalsIgnoreCase(patientClassObj.getGender())
									&& patientClassObj.getSchemeId()!=null && !"".equalsIgnoreCase(patientClassObj.getSchemeId()))
									{
										
										if(patientClassObj.getGender().equalsIgnoreCase("M"))
											gender=config.getString("MaleStat");
										else if(patientClassObj.getGender().equalsIgnoreCase("F"))
											gender=config.getString("FemaleStat");
										
										if(config.getString("bothGender")!=null)
											genderBoth=config.getString("bothGender");
										
										StringBuffer query=new StringBuffer();
										query.append("SELECT DISTINCT INVST_ID AS ID ,INVST_NAME AS VALUE FROM EHFM_WC_TD_API_MPG WHERE DISP_ID = '"+dispId+"' AND (AHCT_FACILITY_ID, ");
										query.append("CHARGE_ITEM_ID) NOT IN ( SELECT B.DISP_ID,A.CHARGE_ITEM_ID FROM( SELECT DISTINCT HUB_ID,LAB_LOC_ID,CHARGE_ITEM_ID FROM EHF_TD_HUB_DTLS) A, ");
										query.append("(SELECT DISTINCT TD_HUB_ID,LAB_LOC_ID,TD_FACILITY_ID,DISP_ID FROM EHFM_TD_LAB_LOC_FCT_MPG) B WHERE A.HUB_ID = B.TD_HUB_ID ");
										query.append("AND A.LAB_LOC_ID = B.LAB_LOC_ID AND B.DISP_ID = '"+dispId+"') AND CHARGE_ITEM_ID IS NOT NULL ORDER BY INVST_NAME ");

										returnLst=genericDao.executeSQLQueryList(LabelBean.class,query.toString());
									}
							}
					}
			}
		catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error("Exeption occurred in getGeneralInvest method of AnnualCheckUpServiceImpl Class"+e.getMessage());
				return returnLst;
			}
		return returnLst;
	}
	@Override
	public List<LabelBean> getDistrictListNew()
	{
			List<LabelBean> districtList = new ArrayList<LabelBean>();
			StringBuffer query = new StringBuffer();
		 try
	   	{
			 	query.append(" select loc_id as ID,loc_name as VALUE from ehfm_locations WHERE loc_parnt_id in (?) order by loc_name ");
		 		districtList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{"S35"});
	   	}
	   	catch(Exception e)
	   	{
	   		GLOGGER.error("Exception occurred in getDistrictListNew() in CommonServiceImpl class."+e.getMessage());
	   	}
	       return districtList;
	 }
	@Override
	public List<LabelBean> getSpecialityList()
	{
		List<LabelBean> specialityList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
	 try
	 	{
		 	query.append(" select dis_main_id as ID,dis_main_name ||' ( '|| dis_main_id || ' )' as VALUE from ehfm_specialities order by dis_main_name ");
		 	specialityList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
	 	}
   	catch(Exception e)
   	{
   		GLOGGER.error("Exception occurred in getSpecialityList() in CommonServiceImpl class."+e.getMessage());
   	}
       return specialityList;
 }
	@Override
	public List<LabelBean> getDrugTypeList(String drugTypeCode)
	{
		List<LabelBean> drugTypeList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
	 try
	 	{
		 	query.append(" select drug_id as ID,drug_name as VALUE  from ehfm_disp_drug_mst where drug_type=? order by drug_name asc  ");
		 	drugTypeList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{drugTypeCode});
	 	}
	   	catch(Exception e)
	   	{
	   		GLOGGER.error("Exception occurred in getSpecialityList() in CommonServiceImpl class."+e.getMessage());
	   	}
       return drugTypeList;
 }
	@Override
	public List<LabelBean> getHosptlDtlsList() {
		List<LabelBean> hospList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select h.hosp_id ID, hosp_name VALUE from ehfm_hospitals h, ehfm_edc_hosp_action_dtls ad  WHERE  ");
		    query.append("  ad.hosp_id = h.hosp_id and ad.hosp_status = ? and ad.scheme = ? ");
		 	hospList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{"Y","CD202"});
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
   return hospList;
  }
	@Override
	public List<LabelBean> getMedicalCatgryList(String splcty) {
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select ee.proc_name as VALUE,EE.ICD_CAT_CODE AS ID from  EHFM_MAIN_THERAPY_NABH ee WHERE EE.MEDICAL_SURG=? AND EE.ACTIVE_YN=? and ee.asri_code=? order by ee.proc_name  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{"M","Y",splcty});
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return medcialList;
	}
	@Override
	public List<LabelBean> getMedicalSpltyList(String hospId, String schemeId)
	{
		List<LabelBean> asriCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			//query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmSpecialities adm where adm.id.disMainId not in ('DM95','S1','S10','S11','S12','S13','S14','S15','S16','S17','S2','S3','S4','S5','S6','S7','S9','S8','S18','S19')   order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc");
			query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmHospCatMst ehc,EhfmSpecialities adm,EhfmMainTherapyNabh em where em.id.asriCode=adm.id.disMainId and em.id.process in (?)  and em.id.state ='CD202' and ehc.id.hospId=? and ehc.isActvFlg=? and ehc.id.catId=adm.disMainId and ehc.id.schemeId='CD202' order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc ");
			asriCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new Object[]{"IPM",hospId,"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAsriCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return asriCatList;
	}
	@Override
	public List<LabelBean> getMedicalCatgryListDflt() {
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select ee.proc_name as VALUE,EE.ICD_CAT_CODE AS ID from  EHFM_MAIN_THERAPY_NABH ee WHERE EE.MEDICAL_SURG=? AND EE.ACTIVE_YN=? order by ee.proc_name  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{"M","Y"});
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return medcialList;
	}
	@Override
	public String getloggedUserHospId(String lStrUserId,String schemeId) {
		String hospId="";
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select hosp_id as ID from ehfm_medco_dtls WHERE medco_id=? and end_dt is null and active_yn=? and scheme=?  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{lStrUserId,"Y",schemeId});
		
				if(medcialList.size()>0)
				{
					if(medcialList != null)
						hospId=medcialList.get(0).getID();
				}
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return hospId;
	}
	
	@Override
		public String getloggedUserDispId(String lStrUserId,String schemeId) {
		String DispId="";
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select disp_id as ID from ehfm_disp_usr_mpg WHERE user_id=? and end_dt is null and active_yn=? and scheme=?  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{lStrUserId,"Y",schemeId});
		
				if(medcialList.size()>0)
				{
					if(medcialList != null)
						DispId=medcialList.get(0).getID();
				}
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return DispId;
	}
	@Override
	public List<LabelBean> getTelephncHospitals() 
	{
		List<LabelBean> hospitalList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			query.append("select distinct AH.hospId as ID,(AH.hospName||'--'||AH.hospId) as VALUE,AH.hospName as HOSPNAME from EhfmHospitals AH where AH.taashaHosp=? order by AH.hospName asc");
			
			hospitalList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new String[]{"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getHospitals() in CommonServiceImpl class."+e.getMessage());
		}
		return hospitalList;
	}
	@Override
	public List<LabelBean> getDispSpecialities() {
		List<LabelBean> dispSpecList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
	 try
	 	{
		 	query.append(" select grp_id as ID, speciality_name as VALUE from EHFM_DISP_SPECIALITIES where active_yn=? order by speciality_name ");
		 	dispSpecList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{"Y"});
	 	}
	   	catch(Exception e)
	   	{
	   		GLOGGER.error("Exception occurred in getSpecialityList() in CommonServiceImpl class."+e.getMessage());
	   	}
       return dispSpecList;	
	}
	@Override
	public String getspecialityDisp(String cardNo,String dispName,String appDate){
		String specilty="";
		StringBuffer query =null,query1=null;
		List<LabelBean> lstapp= new ArrayList<LabelBean>();
		try
	 	{
			query1 = new StringBuffer();
		query1.append(" select  ec.Grp_id as CONSULTANT_NAME                   ");
	    query1.append("   from EJHS_WELLNESS_CENTERS      wc,                  ");
		query1.append("        EJHS_APPOINTMENT_DTLS      ea,                  ");
		query1.append("        EJHS_WELLNESS_CONSULTDTLS   ec                 ");
		query1.append("  where wc.CENTER_NAME = ea.WELLNESS_CENTER             ");
		query1.append("  and ec.consult_code = ea.consultant                 ");
		query1.append("   and wc.DISP_ID = '"+dispName+"'                      ");
		query1.append("   and ea.Pat_card_number = '"+cardNo+"'                ");
		query1.append("   and ea.app_date = '"+appDate+"'                ");
		query1.append("    and ea.ACTIVE_YN = 'Y'                              ");
		 lstapp = genericDao.executeSQLQueryList(LabelBean.class, query1.toString());
		 if(lstapp.size()>0){
			 specilty=lstapp.get(0).getCONSULTANT_NAME();
		 }	
	 	}
	   	catch(Exception e)
	   	{   e.printStackTrace();
	   		GLOGGER.error("Exception occurred in getSpecialityList() in CommonServiceImpl class."+e.getMessage());
	   	}
       return specilty;	
		
	}
	@Override
	public List<LabelBean> getCardDetails(String cardNo) {
		List<LabelBean> cardDetails=new ArrayList<LabelBean>(); 
		StringBuffer query =null,query1=null;
		try
		{
			query = new StringBuffer();
			
			query.append(" select ef.enroll_Name as NAME,decode(ef.enroll_Gender,'M','Male','F','Female','N/A') as EGENDER, ");
			query.append(" to_char(ef.enroll_Dob, 'dd/mm/yyyy') as BENDOB, er.relation_Name as RELATION,ee.emp_Hno || ', ' || ");
			query.append(" ee.emp_Hstreetno || ', ' || el2.loc_Name || ', ' || el3.loc_Name || ', ' || el1.loc_Name || ' District' as ADDRESS, ");
			query.append(" (select a.enroll_Name from Ehf_Enrollment_Family a where a.enroll_Prnt_Id = ef.enroll_Prnt_Id and ");
			query.append(" a.enroll_Relation = '0') as EMPNAME, ee.emp_Code as EMPID, ef.aadhar_Id as AADHARID, el4.loc_name as DDO, ac.cmb_dtl_name as CARDTYPE ");
			query.append(" from Ehf_Enrollment ee, Ehf_Enrollment_Family ef, Ehfm_Relation_Mst er, Ehfm_Locations el1, Ehfm_Locations el2, ");
			query.append(" Ehfm_Locations el3, Ehfm_Locations el4, asrim_users au, asrim_combo ac where ee.enroll_Prnt_Id = ef.enroll_Prnt_Id and ef.enroll_Relation = er.Relation_id ");
			query.append(" and el1.loc_Id = ee.emp_Hdist and el2.loc_Id = ee.emp_Hvill_twn and el3.loc_Id = ee.Emp_Hmand_Munci ");
			query.append(" and el4.loc_id = ee.post_dist and au.login_name=ee.emp_code and ac.cmb_dtl_id=au.user_role and ef.ehf_Card_no = ? ");
			cardDetails=genericDao.executeSQLQueryList ( LabelBean.class,query.toString(),new String[]{cardNo});

			if(cardDetails.size()<=0){
				cardDetails=new ArrayList<LabelBean>();
				query1 = new StringBuffer();
				query1.append(" select jf.name as NAME, decode(jf.Gender,'M','Male','F','Female','N/A') as EGENDER,to_char(jf.Dob, 'dd/mm/yyyy') as BENDOB, ");
				query1.append(" er.relation_name as RELATION, je.home_Houseno || ', ' || je.home_streetname || ', ' || el2.loc_Name || ', ' || el3.loc_Name || ', ' || el1.loc_Name || ' District' as ADDRESS, ");
				query1.append(" (select a.Name from ehf_jrnlst_family a where a.journal_enroll_prnt_id = jf.journal_enroll_prnt_id and a.Relation = '0') as EMPNAME, je.journal_Code as EMPID, ");
				query1.append(" jf.aadhar_Id as AADHARID, el4.loc_name as DDO, cast('Journalist' as varchar2(20)) as CARDTYPE, je.acc_card_no as ACCRIDNO, je.home_email as EMAILID, je.home_mobile_no as MOBILENO, ac.cmb_dtl_name as IDTYPE ");
				query1.append(" from  ehf_jrnlst_enrollment je, ehf_jrnlst_family jf, Ehfm_Relation_Mst er, Ehfm_Locations el1, Ehfm_Locations el2, Ehfm_Locations el3, Ehfm_Locations el4, asrim_combo ac ");
				query1.append(" where je.journal_enroll_prnt_id=jf.journal_enroll_prnt_id and jf.relation=er.relation_id and el1.loc_Id = je.home_district and el2.loc_Id = je.home_village ");
				query1.append(" and el3.loc_Id = je.home_muncipality and el4.loc_id = je.home_district and ac.cmb_dtl_id=jf.aadhar_exists and jf.journal_card_no=? ");
				cardDetails=genericDao.executeSQLQueryList ( LabelBean.class,query1.toString(),new String[]{cardNo});
			}
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getHospitals() in CommonServiceImpl class."+e.getMessage());
		}
		return cardDetails;
	}
	
	/**
	 * Added for fetching new locations
	 */
	@Override
	public LabelBean getNewLocations(LabelBean labelBeanVillage) {
		List<LabelBean> villageList =new  ArrayList<LabelBean>();
		LabelBean lb=null;
        try
        {
        StringBuffer query = new StringBuffer();
        String args[] = new String[1];
        query.append("select new_district_id as NEW_DIST,new_mandal_id NEW_MAND,new_village_id NEW_VILLAGE from ehf_district_mapping where old_village_id=?");
        args[0] = labelBeanVillage.getID();
       
        villageList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
        }
        catch(Exception e)
        {
        	GLOGGER.error("Exception occurred in getVillageList() in CommonServiceImpl class."+e.getMessage());
        }
        
       if(villageList!=null && villageList.size()>0)
       {
    	   lb=villageList.get(0);
       }
    	   return lb;
	}
	
	@Override
	public List<LabelBean> getLocationsNew(String locHdrId, String distId,String stateType) 
	{
		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> locationsList = new ArrayList<LabelBean>();
        Iterator locationsItr=null;
        StringBuffer locationsQuery=null;
    	EhfmLocations ehfmLocations=null;
    	try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		locationsQuery= new StringBuffer();
    		
    		if(stateType!=null && "O".equalsIgnoreCase(stateType))
    		{
    			locationsQuery.append("from EhfmLocations al where al.locHdrId=? and al.id.locParntId=? and al.activeYn='N' order by al.locName asc");
    		}
    		else
    		locationsQuery.append("from EhfmLocations al where al.locHdrId=? and al.id.locParntId=? and al.activeYn='Y' order by al.locName asc");
            Query locationslst=session.createQuery(locationsQuery.toString());
            locationslst.setParameter(0, locHdrId);
            locationslst.setParameter(1, distId);
            locationsItr=locationslst.iterate();
            while(locationsItr.hasNext())
            {
            	ehfmLocations=(EhfmLocations)locationsItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmLocations.getId().getLocId());
    			labelBean.setVALUE(ehfmLocations.getLocName());
    			locationsList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getEhfmLocations() in CommonServiceImpl class."+e.getMessage());
    	}
    	finally
    	{
    		session.close();
    		factory.close();
    	}
    	return locationsList;
	}
	@Override
	public String getActionDoneName(String caseId,String module)
	{
		String cmbName="";
		try{
			if(module != null && (!"".equalsIgnoreCase(module) && "ehfCase".equalsIgnoreCase(module)))
			{
				EhfCase ehfCase = genericDao.findById(EhfCase.class, String.class, caseId);
				if(ehfCase != null && !"".equalsIgnoreCase(ehfCase.getCaseStatus()))
					return getCaseStatusName(ehfCase.getCaseStatus());
			}
			else if(module != null && (!"".equalsIgnoreCase(module) && "ehfFollowup".equalsIgnoreCase(module)))
			{
				EhfCaseFollowupClaim caseFollowUpClaim = genericDao.findById(EhfCaseFollowupClaim.class, String.class,caseId);
				if(caseFollowUpClaim != null && !"".equalsIgnoreCase(caseFollowUpClaim.getFollowUpStatus()))
					return getCaseStatusName(caseFollowUpClaim.getFollowUpStatus());
			}
			else if(module != null && (!"".equalsIgnoreCase(module) && "ehfCochlrFlp".equalsIgnoreCase(module)))
			{
				EhfCochlearFollowup cochlearFollowup = genericDao.findById(EhfCochlearFollowup.class, String.class,caseId);
				if(cochlearFollowup != null && !"".equalsIgnoreCase(cochlearFollowup.getFollowupType()))
					return getCaseStatusName(cochlearFollowup.getFollowupType());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occured in getActionDoneName of CommonServiceImpl"+ e.getMessage());
		}
		return cmbName;
	}
	public String getCaseStatusName(String caseStatus)
	{
		String cmbName="";
		try{
			EhfmCmbDtls cmbDtls = genericDao.findById(EhfmCmbDtls.class, String.class, caseStatus);
			if(cmbDtls != null && !"".equalsIgnoreCase(cmbDtls.getCmbDtlName()))
				cmbName=cmbDtls.getCmbDtlName().replaceAll("\\s+","");
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			GLOGGER.error("Exception Occured in getCaseStatusName of CommonServiceImpl"+ e.getMessage());
		}
		return cmbName;
	}

	public List<LabelBean> getMedicalSpecialities(String hospId, String schemeId, String critical)
	{
		List<LabelBean> asriCatList=new ArrayList<LabelBean>(); 
		StringBuffer query =null;
		try
		{
			query = new StringBuffer();
			//query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmSpecialities adm where adm.id.disMainId not in ('DM95','S1','S10','S11','S12','S13','S14','S15','S16','S17','S2','S3','S4','S5','S6','S7','S9','S8','S18','S19')   order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc");
			query.append(" select distinct adm.disMainId as ID,adm.disMainName||' ( '|| adm.disMainId || ' )' as VALUE from EhfmHospCatMst ehc,EhfmSpecialities adm,EhfmMainTherapyNabh em where em.id.asriCode=adm.id.disMainId and em.id.process in (?)  and em.id.state ='CD202' and ehc.id.hospId=? and ehc.isActvFlg=? and ehc.id.catId=adm.disMainId and ehc.id.schemeId='CD202' ");
/*		    if(critical!=null && !"".equalsIgnoreCase(critical) && critical.equalsIgnoreCase("Y"))
		    {
		    	query.append(" and 	em.criticalFlg='Y' ");
		    }
		    else
		    {
		    	query.append(" and 	em.criticalFlg='N' ");
		    }*/
			
			query.append( "order by adm.disMainName||' ( '|| adm.disMainId || ' )' asc ");
			
			asriCatList=genericDao.executeHQLQueryList ( LabelBean.class,query.toString(),new Object[]{"IPM",hospId,"Y"});
		}
		catch(Exception e)
		{
			GLOGGER.error("Exception occurred in getAsriCategoryList() in CommonServiceImpl class."+e.getMessage());
		}
		return asriCatList;
	}
	
	public List<LabelBean> getMedicalCatgryListCritical(String splcty,String critical) {
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" select ee.proc_name as VALUE,EE.ICD_CAT_CODE AS ID from  EHFM_MAIN_THERAPY_NABH ee WHERE EE.MEDICAL_SURG=? AND EE.ACTIVE_YN=? and ee.asri_code=? ");
		    /*if(critical!=null && !"".equalsIgnoreCase(critical) && critical.equalsIgnoreCase("Y"))
		    {
		    	query.append(" and 	ee.CRITICAL_NONCRITICAL='Y' ");
		    }
		    else
		    {
		    	query.append(" and 	ee.CRITICAL_NONCRITICAL='N' ");
		    }	*/   
		    
		    query.append("  order by ee.proc_name  ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),new String[]{"M","Y",splcty});
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return medcialList;
	}
	public List<LabelBean> getAllUsersFromDepts(String caseId) {
		List<GenericDAOQueryCriteria> criteriaList = 
                new ArrayList<GenericDAOQueryCriteria>();
           EhfCase ehfCase=genericDao.findById(EhfCase.class,String.class , caseId);
           criteriaList.add(new GenericDAOQueryCriteria("id.hospitalId", ehfCase.getCaseHospCode(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
           criteriaList.add(new GenericDAOQueryCriteria("activeYN","Y", GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
           List<EhfmUsrHospitalMpg> husrHosp = genericDao.findAllByCriteria(EhfmUsrHospitalMpg.class, criteriaList);
       	List<GenericDAOQueryCriteria> criteriaList1 = 
                new ArrayList<GenericDAOQueryCriteria>();  
       	criteriaList1.add(new GenericDAOQueryCriteria("caseId", caseId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
            List<EhfCaseTherapy> therapyList = genericDao.findAllByCriteria(EhfCaseTherapy.class, criteriaList1);
		StringBuffer sb=new StringBuffer();
		sb.append("select eu.userId as ID,upper(eu.firstName) ||' '|| upper(eu.lastName)   || ' - ' || upper(eu.loginName) as EMPNAME,");
		sb.append(" eu.loginName as LOGINNAME");
		sb.append(" from EhfmUsers eu,EhfmUsrGrpsMpg ug,EhfmGrps eg,EhfmUsrSpecialityMpg eug");
		sb.append(" where  ug.id.usergrpId=eu.id.userId");
		sb.append(" and eg.id.grpId=ug.id.grpId");
		sb.append(" and eg.id.grpId=eug.id.grpId");
		sb.append(" and eu.id.userId=eug.id.userId");
		sb.append(" and eu.serviceFlag='Y' and eu.serviceExpiryDt is null ");
		sb.append(" and ug.id.grpId in ('GP7') and eu.userType ='CD202' ");
		sb.append(" and eug.id.specialityId='"+therapyList.get(0).getAsriCatCode()+"' ");
		sb.append(" and eug.activeYN='Y' ");
		if(husrHosp!=null&&husrHosp.size()>0){
		sb.append(" and eu.userId not in (");
		for (int k = 0; k < husrHosp.size(); k++) {
		if (k != 0 && k != husrHosp.size()) {
			sb.append(" , ");
			}
			sb.append(" '" + husrHosp.get(k).getId().getUserId() + "' ");
		}
		sb.append(" ) ");
		}
		List<LabelBean> usrLst=genericDao.executeHQLQueryList(LabelBean.class, sb.toString());
		
		return usrLst;
	}
	@Override
	public List<LabelBean> getapp(String center, int lStrStartIndex, int lStrRowsperpage, int pageNo,String fromDate,String toDate) {
		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		int startIndex=0,maxResults=10,pageId=1,totalPages=0,totalRecords=0;
		  
		BigDecimal b = null ;
	
		List<LabelBean> lstapp= new ArrayList<LabelBean>();
		try
		{

			
			query.append(" select ea.PAT_CARD_NUMBER as PAT_CARD_NUMBER,          ");
			query.append("        nvl(f.enroll_name || j.name,'-') as PAT_NAME,   ");
			query.append("        ea.MOBILE_NO as MOBILE_NO,                      ");
			query.append("        ea.scheme as PATIENTSCHEME ,                      ");
			query.append("       to_char( ea.APP_DATE )  as APP_DATE,                        ");
			query.append("        ea.TIME_SLOT as TIME_SLOT,                      ");
			query.append("        g.GRP_NAME as GRP_NAME,                         ");
			query.append("        ec.CONSULTANT_NAME as CONSULTANT_NAME           ");
			query.append("   from EJHS_WELLNESS_CENTERS      wc,                  ");
			query.append("        EJHS_APPOINTMENT_DTLS      ea,                  ");
			query.append("        EHFM_GRPS                  g,                   ");
			query.append("        EJHS_WELLNESS_CONSULTDTLS   ec,                 ");
			query.append("        ehf_enrollment_family      f,                   ");
			query.append("        EHF_JRNLST_FAMILY         j                     ");
			query.append("  where wc.CENTER_NAME = ea.WELLNESS_CENTER             ");
			query.append("   and wc.DISP_ID = '"+center+"'                        ");
		   if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty())
			{
				 query.append("and EA.APP_DATE between TO_DATE('"+fromDate+"','DD/MM/YYYY') and TO_DATE('"+toDate+"','DD/MM/YYYY')"); 

			}
			query.append("    and ea.doc_id = ec.doc_id                           ");
			query.append("    and ec.GRP_ID = g.GRP_ID                            ");
			query.append("    and j.journal_card_no(+) = ea.PAT_CARD_NUMBER       ");
			query.append("    and f.ehf_card_no(+) = ea.PAT_CARD_NUMBER           ");
			query.append("    and EA.ACTIVE_YN = 'Y'                              ");
			query.append("    and EA.patient_id is null                              ");
			query.append("    order by ea.APP_DATE                          ");
		     
		     
		   if(lStrStartIndex>=0 &&lStrRowsperpage>=0)
			 {
			   
			   
				query1.append(" select count(*) as COUNT     ");
			    query1.append("   from EJHS_WELLNESS_CENTERS      wc,                  ");
				query1.append("        EJHS_APPOINTMENT_DTLS      ea,                  ");
				query1.append("        EHFM_GRPS                  g,                   ");
				query1.append("        EJHS_WELLNESS_CONSULTDTLS   ec,                 ");
				query1.append("        ehf_enrollment_family      f,                   ");
				query1.append("        EHF_JRNLST_FAMILY         j                     ");
				query1.append("  where wc.CENTER_NAME = ea.WELLNESS_CENTER             ");
				query1.append("   and wc.DISP_ID = '"+center+"'                        ");
				if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty())
				{
					 query1.append("and EA.APP_DATE between TO_DATE('"+fromDate+"','DD/MM/YYYY') and TO_DATE('"+toDate+"','DD/MM/YYYY')"); 

				}
				query1.append("    and ea.doc_id = ec.doc_id                           ");
				query1.append("    and ec.GRP_ID = g.GRP_ID                            ");
				query1.append("    and j.journal_card_no(+) = ea.PAT_CARD_NUMBER       ");
				query1.append("    and f.ehf_card_no(+) = ea.PAT_CARD_NUMBER           ");
				query1.append("    and EA.ACTIVE_YN = 'Y'                              ");
				query1.append("    and EA.patient_id is null                              ");

			     
			//   query1.append(" select count(*) as COUNT  from EjhsWellnessCenters     wc,      EjhsAppointmentDtl      ea,       EhfmGrps                g,      EjhsWellnessConsultDtls ec ,EhfEnrollmentFamily f,EhfJrnlstFamily j ");
			 //  query1.append("  where wc.centerName = ea.wellnessCenter   and wc.dispId = '"+center+"'   and ea.docId = ec.docId   and ec.grpId = g.grpId  and j.journalCradNo(+) = ea.patCardNumber   and f.ehfCardNo(+) = ea.patCardNumber  AND  EA.activeYN='Y' ");
			      
			 //  query1.append(" select count(*) as COUNT from EJHS_WELLNESS_CENTERS      wc,    EJHS_APPOINTMENT_DTLS       ea,    EHFM_GRPS                   g,    EJHS_WELLNESS_CONSULTDTLS     ec where wc.CENTER_NAME = ea.WELLNESS_CENTER  and wc.DISP_ID = '"+center+"'   and ea.doc_id = ec.doc_id  and ec.GRP_ID = g.GRP_ID ");
			 //  b=(BigDecimal) session.createSQLQuery(query1.toString()).uniqueResult();
				 b= (BigDecimal) session.createSQLQuery(query1.toString()).uniqueResult();
			   LabelBean lb=new LabelBean();
		         lb.setCOUNT(b.intValue());
		         //Getting the required results 
		     lstapp=session.createSQLQuery(query.toString()).setFirstResult(lStrStartIndex).setMaxResults(lStrRowsperpage).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
			// lstapp=session.createQuery(query.toString()).setFirstResult(lStrStartIndex).setMaxResults(lStrRowsperpage).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
				lstapp.add(lb);
			 } 
		   else
		   {
			   lstapp = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		   }
		// TODO Auto-generated method stub
			if(lstapp.size()>0)
			{
					if(lstapp.get(0)!=null)
						{
							if(b.intValue()!=0)
								{
								
									 totalRecords = b.intValue();
 								
								if(lStrRowsperpage==0)
 									maxResults=10;
 								else
 									maxResults=lStrRowsperpage;
 								
 							
								if(pageNo==0)
 									{
 										pageId=1;
 										lStrStartIndex=0;
 									}
 								else
 									{
 										pageId=pageNo;
 										lStrStartIndex=(pageId-1) * (maxResults);
 									}
 								
								if(totalRecords%maxResults==0)
 									totalPages=(totalRecords/maxResults);
 								else
 									totalPages=(totalRecords/maxResults)+1;
 									
								lstapp= new ArrayList<LabelBean>();
								lstapp=session.createSQLQuery(query.toString()).setFirstResult(lStrStartIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();	 				
								}
							else
								lstapp=null;
						}
					else
						lstapp=null;
					
			}
		   else
			   lstapp=null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
finally
		{
			session.close();
		}
		// TODO Auto-generated method stub
		if(lstapp!=null &&lstapp.size()>0)  
		  {	
			lstapp.get(0).setTotalPages(totalPages);
			lstapp.get(0).setPageId(pageId);
			lstapp.get(0).setMaxresults(maxResults);
			lstapp.get(0).setTotalRecords(totalRecords);
			lstapp.get(0).setStrtIndex(lStrStartIndex);
		  }
	
		return lstapp;
	
		}
	@Override
	public List<LabelBean> checkUsrMapped(String lStrUserId) {
		Boolean Flag= false;
		List<LabelBean> list= null;
		String args [] = new String [1];
		args[0]=lStrUserId;
		try
		{
			StringBuffer	query = new StringBuffer();
			query.append("  select d.disp_id as DISPID, dt.center_name as CENTERID, d.user_id USERID  from EHFM_DISP_USR_MPG d, ejhs_wellness_centers dt where d.disp_id = dt.disp_id  and d.user_id = ? ");
			
			list=genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	 	 return list; 
	}
	@Override
	public List<LabelBean> getwellnessapp() {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		List<LabelBean> wellnessapplist= new ArrayList<LabelBean>();
		try
		{
         query.append(" select CENTER_ID AS CENTERID , DISP_ID AS DISPID from ejhs_wellness_centers " );
		 wellnessapplist = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return wellnessapplist;
	}
	@Override
	public List<LabelBean> getDoctorNames(String lStrUserId, String dispId) {
		List<LabelBean> docNames=new ArrayList<LabelBean>();
		StringBuffer query=new StringBuffer();
		try
		{
		query.append( " select dm.DOC_ID as ID, dm.CONSULTANT_NAME as VALUE from ejhs_wellness_consultdtls dm  WHERE dm.DISP_ID='"+dispId+"' and dm.active_yn='Y' ");
		docNames=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return docNames;
	
	
	}
	
	@Override
	public String saveEmployeeDtls(PatientVO patientVo) {
		// TODO Auto-generated method stub
		String msg = "";
		EjhsWellnessLeaveDetails leavePatient=null;
		leavePatient=new EjhsWellnessLeaveDetails();
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sdfn=new SimpleDateFormat("dd-MM-yyyy");
		Date crtDt=null;
		try {
			crtDt=sdfw.parse(sdfw.format(new Date()));
		} catch (ParseException e) {
			GLOGGER.error("Exception occurred in lockCase() in CommonServiceImpl class."+e.getMessage());
		}
		//Date date = sdfw.parse(patientVo.getLeaveToDate());
		//Date date1 = sdfw.parse(patientVo.getLeaveFromdate());
		try
		{
		leavePatient.setUserId(patientVo.getLeaveId());
		leavePatient.setCrtDt(crtDt);
		leavePatient.setLeaveFomDate(sdfn.parse(patientVo.getLeaveFromdate()));
		leavePatient.setLeaveToDate(sdfn.parse(patientVo.getLeaveToDate()));
		leavePatient.setDocId(patientVo.getDocRegNo());
		leavePatient.setDispId(patientVo.getDispCode());
		leavePatient.setCrtUsr(patientVo.getLstUpdUsr());
		leavePatient.setActiveYN("Y");
		leavePatient.setWellnessCenterName(patientVo.getLeaveWellName());
		leavePatient.setGrpId(patientVo.getLeaveGrpId());
		leavePatient.setConsultName(patientVo.getLeaveConsultName());
		leavePatient.setConsultMobile(patientVo.getLeaveConsultMob());
		leavePatient.setReason(patientVo.getReason());
		 genericDao.save(leavePatient);
		msg="success";
		}
		catch(Exception e){
			e.printStackTrace();
		}
		 
		return msg;
	}
	@Override
	public PatientVO getEmployeeDtls(PatientVO patientVo) {
		PatientVO empDtls=new PatientVO();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		try
		{
		criteriaList.add(new GenericDAOQueryCriteria("docId", patientVo.getDocRegNo(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EjhsWellnessConsultDtls> ehfConsult = genericDao.findAllByCriteria(EjhsWellnessConsultDtls.class, criteriaList);
		criteriaList.removeAll(criteriaList);
		if(ehfConsult != null && !ehfConsult.isEmpty()){
			empDtls.setLeaveConsultName(ehfConsult.get(0).getConsultantName());
			empDtls.setLeaveGrpId(ehfConsult.get(0).getGrpId());
			empDtls.setLeaveConsultMob(ehfConsult.get(0).getConMob());
			empDtls.setLeaveWellName(ehfConsult.get(0).getWellnessName());
			
			
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return empDtls;
	}
	@Override
	public PatientVO getDocDtls(PatientVO patientVo) {
		PatientVO empDtls1=new PatientVO();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		try
		{
		criteriaList.add(new GenericDAOQueryCriteria("docId", patientVo.getDocRegNo(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EjhsWellnessConsultDtls> ehfConsult1 = genericDao.findAllByCriteria(EjhsWellnessConsultDtls.class, criteriaList);
		criteriaList.removeAll(criteriaList);
		if(ehfConsult1 != null && !ehfConsult1.isEmpty()){
			empDtls1.setLeaveConsultName(ehfConsult1.get(0).getConsultantName());
			empDtls1.setLeaveConsultMob(ehfConsult1.get(0).getConMob());
			empDtls1.setLeaveWellName(ehfConsult1.get(0).getWellnessName());
			
			
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return empDtls1;
	}
	@Override
	public List<LabelBean> getMobileNum(String docId,String fromDate,String toDate) {
		List<LabelBean> mobileNums=new ArrayList<LabelBean>();
		StringBuffer query=new StringBuffer();
		try
		{
		//query.append( " select dm.MOBILE_NO as ID, to_char(dm.APP_DATE) as VALUE from ejhs_appointment_dtls dm  WHERE dm.DOC_ID='"+docId+"' and dm.APP_DATE between to_date('"+fromDate+"','DD/MM/YYYY') and to_date('"+toDate+"','DD/MM/YYYY') ");
			query.append( " select dm.MOBILE_NO as ID,dm.APP_DATE as DOS,dm.WELLNESS_CENTER  as WELLNESSCENTER  from ejhs_appointment_dtls dm  WHERE dm.DOC_ID='"+docId+"' and dm.APP_DATE between to_date('"+fromDate+"','DD/MM/YYYY') and to_date('"+toDate+"','DD/MM/YYYY') ");
		mobileNums=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mobileNums;
	}
	@Override
	public String saveEmployeeDtls(String docId, Date leavedate, String id) {
		String msg = "";
		SimpleDateFormat sdfn=new SimpleDateFormat("MM/dd/yyyy");
		try
		{
		DateFormat df3 = new SimpleDateFormat("yyyy-MM-dd");
		Date d3 = (Date)df3.parse(leavedate.toString());
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date date = (Date)formatter.parse(d3.toString());
		System.out.println(date);        

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		String formatedDate = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DATE) + "/" +         cal.get(Calendar.YEAR);
		System.out.println("formatedDate : " + formatedDate);    
		
		/*String tempDate=sdfn.format(leavedate);
		Date finDate=sdfn.parse(tempDate);*/
	/*	Date crtDt=null;
		try {
			crtDt=sdfn.parse(sdfn.format(leavedate));
		} catch (ParseException e) {
			GLOGGER.error("Exception occurred in lockCase() in CommonServiceImpl class."+e.getMessage());
		}*/
		//SimpleDateFormat sdfn=new SimpleDateFormat("MM-DD-YYYY");
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
	
		criteriaList.add(new GenericDAOQueryCriteria("docId", docId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("id.appDate", d3, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		criteriaList.add(new GenericDAOQueryCriteria("mobileNo", id, GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
		List<EjhsAppointmentDtl> ehfConsult = genericDao.findAllByCriteria(EjhsAppointmentDtl.class, criteriaList);
		criteriaList.removeAll(criteriaList);
		if(ehfConsult != null && !ehfConsult.isEmpty()){
			
			ehfConsult.get(0).setActiveYN("N");
			genericDao.saveAll(ehfConsult);
			
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return msg;
	}
	@Override
	public List<LabelBean> getDoctorDates(String docId) {
		List<LabelBean> docDates=new ArrayList<LabelBean>();
		StringBuffer query=new StringBuffer();
		try
		{
		query.append( " select dm.LEAVE_FROMDATE as DOB, dm.LEAVE_TODATE as DOS from ejhs_wellness_leavedtls dm  WHERE dm.DOC_ID='"+docId+"' and dm.active_yn='Y' ");
		docDates=genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return docDates;
	}
	@Override
	public List<LabelBean> getappNims(int lStrStartIndex, int lStrRowsperpage, int pageNo,String fromDate,String toDate) {

		SessionFactory factory=hibernateTemplate.getSessionFactory();
		Session session=factory.openSession();
		StringBuffer query = new StringBuffer();
		StringBuffer query1 = new StringBuffer();
		int startIndex=0,maxResults=10,pageId=1,totalPages=0,totalRecords=0;
		  
		BigDecimal b = null ;
	
		List<LabelBean> lstapp= new ArrayList<LabelBean>();
		try
		{

			
			query.append(" select ea.EMP_ID as PAT_CARD_NUMBER,          ");
			query.append("   ea.ENTRY_ID as UNITID,          ");
			query.append("        ea.EMP_NAME as PAT_NAME,   ");
			query.append("        ea.MOBILE as MOBILE_NO,                      ");
			query.append("        ea.APPOINTMENT_DATE   as APP_DATE,                        ");
			query.append("        ms.SLOT_VALUE as TIME_SLOT,                      ");
			query.append("        ea.SERVICE as SERVICE_TYPE,                      ");
			//query.append("        ea.SERVICETYPE as GRP_NAME,                         ");
			query.append(" cast(CASE ea.SERVICETYPE WHEN 'A' THEN 'AYUSH' WHEN 'M' THEN 'MHC' END AS varchar2(100)) AS GRP_NAME, ");
			query.append("        EA.SERVICE_FLAG AS PATIENTSCHEME,                         ");
			query.append("        EA.RELATION AS RELATION                         ");
			query.append("    from    mhc_entries      ea,                  ");
			query.append("        mhc_SLOTS      ms                  ");
			query.append("  where ea.TIME_SLOT = MS.SLOT_ID             ");
			query.append("    and EA.patient_id is null                              ");
			query.append("    and EA.active_yn = 'Y'                             ");

			
			
		   if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty())
			{
				 query.append("and TO_DATE(EA.APPOINTMENT_DATE,'DD-MM-YYYY') between TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+toDate+"','DD-MM-YYYY')"); 

			}
			query.append("    order by TO_DATE(EA.APPOINTMENT_DATE,'DD-MM-YYYY') asc                          ");
		     
		     
		   if(lStrStartIndex>=0 &&lStrRowsperpage>=0)
			 {
			   
			   
				query1.append(" select count(*) as COUNT     ");
			    query1.append("   from mhc_entries      EA,                  ");
				query1.append("        MHC_SLOTS      MS                  ");
				query1.append("  where ea.TIME_SLOT = MS.SLOT_ID             ");
				query1.append("  and ea.active_yn = 'Y'             ");
				if(fromDate !=null && !fromDate.isEmpty() && toDate !=null && !toDate.isEmpty())
				{
					 query1.append("and TO_DATE(EA.APPOINTMENT_DATE,'DD-MM-YYYY') between TO_DATE('"+fromDate+"','DD-MM-YYYY') and TO_DATE('"+toDate+"','DD-MM-YYYY')"); 

				}
				query1.append("    and EA.patient_id is null                              ");

			     
			//   query1.append(" select count(*) as COUNT  from EjhsWellnessCenters     wc,      EjhsAppointmentDtl      ea,       EhfmGrps                g,      EjhsWellnessConsultDtls ec ,EhfEnrollmentFamily f,EhfJrnlstFamily j ");
			 //  query1.append("  where wc.centerName = ea.wellnessCenter   and wc.dispId = '"+center+"'   and ea.docId = ec.docId   and ec.grpId = g.grpId  and j.journalCradNo(+) = ea.patCardNumber   and f.ehfCardNo(+) = ea.patCardNumber  AND  EA.activeYN='Y' ");
			      
			 //  query1.append(" select count(*) as COUNT from EJHS_WELLNESS_CENTERS      wc,    EJHS_APPOINTMENT_DTLS       ea,    EHFM_GRPS                   g,    EJHS_WELLNESS_CONSULTDTLS     ec where wc.CENTER_NAME = ea.WELLNESS_CENTER  and wc.DISP_ID = '"+center+"'   and ea.doc_id = ec.doc_id  and ec.GRP_ID = g.GRP_ID ");
			 //  b=(BigDecimal) session.createSQLQuery(query1.toString()).uniqueResult();
				 b= (BigDecimal) session.createSQLQuery(query1.toString()).uniqueResult();
			   LabelBean lb=new LabelBean();
		         lb.setCOUNT(b.intValue());
		         //Getting the required results 
		     lstapp=session.createSQLQuery(query.toString()).setFirstResult(lStrStartIndex).setMaxResults(lStrRowsperpage).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
			// lstapp=session.createQuery(query.toString()).setFirstResult(lStrStartIndex).setMaxResults(lStrRowsperpage).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();
				lstapp.add(lb);
			 } 
		   else
		   {
			   lstapp = genericDao.executeSQLQueryList(LabelBean.class, query.toString());
		   }
		// TODO Auto-generated method stub
			if(lstapp.size()>0)
			{
					if(lstapp.get(0)!=null)
						{
							if(b.intValue()!=0)
								{
								
									 totalRecords = b.intValue();
 								
								if(lStrRowsperpage==0)
 									maxResults=10;
 								else
 									maxResults=lStrRowsperpage;
 								
 							
								if(pageNo==0)
 									{
 										pageId=1;
 										lStrStartIndex=0;
 									}
 								else
 									{
 										pageId=pageNo;
 										lStrStartIndex=(pageId-1) * (maxResults);
 									}
 								
								if(totalRecords%maxResults==0)
 									totalPages=(totalRecords/maxResults);
 								else
 									totalPages=(totalRecords/maxResults)+1;
 									
								lstapp= new ArrayList<LabelBean>();
								lstapp=session.createSQLQuery(query.toString()).setFirstResult(lStrStartIndex).setMaxResults(maxResults).setResultTransformer(Transformers.aliasToBean(LabelBean.class)).list();	 				
								}
							else
								lstapp=null;
						}
					else
						lstapp=null;
					
			}
		   else
			   lstapp=null;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
finally
		{
			session.close();
		}
		// TODO Auto-generated method stub
		if(lstapp!=null &&lstapp.size()>0)  
		  {	
			lstapp.get(0).setTotalPages(totalPages);
			lstapp.get(0).setPageId(pageId);
			lstapp.get(0).setMaxresults(maxResults);
			lstapp.get(0).setTotalRecords(totalRecords);
			lstapp.get(0).setStrtIndex(lStrStartIndex);
		  }
	
		return lstapp;
	
		
	}
	@Override
	public List<LabelBean> getComboDetailsNew(String string) {

		SessionFactory factory=null;
		Session session=null;
		List<LabelBean> comboList = new ArrayList<LabelBean>();
        Iterator<EhfmCmbDtls> cmbItr=null;
        StringBuffer cmbHdrQuery=null;
        EhfmCmbDtls ehfmCmbDtls=null;
		try
    	{
    		factory= hibernateTemplate.getSessionFactory();
    		session=factory.openSession();
    		cmbHdrQuery= new StringBuffer();
    		cmbHdrQuery.append("from EhfmCmbDtls ac where ac.cmbHdrId=? and aisMarital='Y' order by ac.cmbDtlName asc");
            Query locationslst=session.createQuery(cmbHdrQuery.toString());
            locationslst.setParameter(0, string);
            cmbItr=locationslst.iterate();
            while(cmbItr.hasNext())
            {
            	ehfmCmbDtls=(EhfmCmbDtls)cmbItr.next();
            	LabelBean labelBean =new LabelBean();
    			labelBean.setID(ehfmCmbDtls.getCmbDtlId());
    			labelBean.setVALUE(ehfmCmbDtls.getCmbDtlName());
    			comboList.add(labelBean);
            }
    	}
    	catch(Exception e)
    	{
    		GLOGGER.error("Exception occurred in getComboDetails() in CommonServiceImpl class."+e.getMessage());
    	}
		finally
    	{
    		session.close();
    		factory.close();
    	}
		return comboList;
	
	}
	@Override
	public void generateMailNew(EmailVO emailVO, Map<String, String> model,Map<String, List<String>> model1) {
		String status="Success";
	    try {

		       // String file =(String) model.get("Filename") ;
		      //  String fileName =  (String) model.get("lStrFileName") ;;
	    	List<String> lst=(List<String>) model1.get("lStrFileName") ;
		        
	        SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setFrom(emailVO.getFromEmail());
			mailMessage.setSubject(emailVO.getSubject());
			mailMessage.setTo(emailVO.getToEmail());
			mailMessage.setSentDate(new java.sql.Timestamp(new Date().getTime()));
		
			  emailNotifier.sendMultipleAttachMessage(mailMessage, emailVO.getTemplateName(),model, emailVO.getTemplateSource(),lst);
			  System.out.println("MAIL SENT SUCCESSFULLY");
			  GLOGGER.info("mail sent");
			  
	    } catch (Exception e) {
	    	GLOGGER.error("Exception occurred in generateMailNew() in CommonServiceImpl class."+e.getMessage());
	    	 System.out.println("error is"+e.getMessage());
	    }
	    

		
	}
	//Chandana - 8251 - New method for getting the flag based on the dispId, year and month
	@Override
	public String getFlagMonthBased(String dispId, String monthYr) {
		String DispId="";
		char flagChar = 'N';
		List<LabelBean> medcialList = new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		try
		{
		    query.append(" SELECT CASE WHEN COUNT(*) > 0  THEN 'Y' ELSE 'N' END AS SERVICETYPE FROM EHF_DISP_DRUG_EXCEL_UPLOAD WHERE ACTIVE_YN = 'Y' ");
		    query.append(" AND DISP_ID = '"+dispId+"' AND MONTH_YEAR = '"+monthYr+"' ");
		    medcialList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());
				if(medcialList.size()>0)
				{
					if(medcialList != null){
						flagChar = (char) medcialList.get(0).getSERVICETYPE();
						DispId = String.valueOf(flagChar);
					}
				}
		}
	   	catch(Exception e)
	   	{
	   		e.printStackTrace();
	   	}
		return DispId;
	}
}
