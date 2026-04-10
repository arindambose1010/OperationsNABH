package com.ahct.createEmployee.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.tcs.Webframework.common.util.SendSMS;
import com.ahct.common.service.CommonService;
import com.ahct.common.service.CommonServiceImpl;
import com.ahct.common.util.SMSServices;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.service.CreateEmpService;
import com.ahct.createEmployee.service.CreateEmpServiceImpl;
import com.ahct.model.EhfmDispUsrMpg;
import com.ahct.model.EhfmDispUsrMpgId;
import com.ahct.model.EhfmDispensaryDtls;
import com.ahct.model.EhfmEmpDispAcctDtls;
import com.ahct.model.EhfmEmpDispAcctDtlsId;
import com.ahct.model.EhfmEmployeeMaster;
import com.ahct.model.EhfmSeq;
import com.ahct.model.EhfmUsers;
/*import com.ahct.model.EhfUsersEmailView;
import com.tcs.model.EhfmBankMaster;
import com.tcs.model.EhfmDesignation;
import com.tcs.model.EhfmDesignationId;
import com.tcs.model.EhfmEmpAcctDtls;
import com.tcs.model.EhfmEmpAcctDtlsId;
import com.tcs.model.EhfmEmployeeMaster;
import com.tcs.model.EhfmHospMithraDtls;
import com.tcs.model.EhfmHospMithraDtlsId;
import com.tcs.model.EhfmMedcoDtls;
import com.tcs.model.EhfmMedcoDtlsId;
import com.tcs.model.EhfmSeq;
import com.tcs.model.EhfmUnitGrpsMpgId;
import com.tcs.model.EhfmUnits;
import com.tcs.model.EhfmUnitsGroupMpg;
import com.tcs.model.EhfmUserDtls;
import com.tcs.model.EhfmUsers;
import com.tcs.model.EhfmUsersUnitDtls;
import com.tcs.model.EhfmUsersUnitDtlsAudit;
import com.tcs.model.EhfmUsersUnitDtlsAuditId;
import com.tcs.model.EhfmUsersUnitDtlsId;
import com.tcs.model.EhfmUsrDsgnDtls;
import com.tcs.model.EhfmUsrDsgnDtlsId;
import com.tcs.model.EhfmUsrGrpsMpg;
import com.tcs.model.EhfmUsrGrpsMpgId;
import com.tcs.model.EhfmVendorMaster;
import com.tcs.model.EhfmWorkAllocation;
import com.tcs.model.EhfmWorkAllocationId;*/
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailNotifier;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;


public class CreateEmpServiceImpl implements CreateEmpService {
	GenericDAO genericDao;
	HibernateTemplate hibernateTemplate;
	CommonService commonService;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	EmailNotifier emailNotifier;
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static 
	{
		configurationService = ConfigurationService.getInstance(); 
		config = configurationService.getConfiguration();
	}
	private final static Logger GLOGGER = Logger.getLogger ( CommonServiceImpl.class ) ;
	public EmailNotifier getEmailNotifier() {
		return emailNotifier;
	}

	public void setEmailNotifier(EmailNotifier emailNotifier) {
		this.emailNotifier = emailNotifier;
	}

	private final static Logger logger = Logger
			.getLogger(CreateEmpServiceImpl.class);

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public GenericDAO getGenericDao() {
		return genericDao;
	}

	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}

	@Override
	public List<LabelBean> getDeptDetails() {

		List<LabelBean> deptList = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT deptId as id, deptName as value FROM EhfmDepts where deptType='CD201' order by deptName");
		deptList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return deptList;
	}

	@Override
	public List<LabelBean> getDesgDetails() {

		List<LabelBean> desgList = null;

		StringBuffer query = new StringBuffer();
		query.append("SELECT id.dsgnId as id, dsgnName as value FROM EhfmDesignation where dsgnStatus='A' order by dsgnName");
		desgList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return desgList;
	}

	@Override
	public List<LabelBean> getWeekList() {
		List<LabelBean> weekList = null;
        String day="day";
		StringBuffer query = new StringBuffer();
		query.append("SELECT id.cmbDtlId as id, cmbDtlName as value FROM EhfmCmbDtls where cmbHdrId='CH39' and cmbDtlName like '%"+day+"%' order by id.cmbDtlId");
		weekList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return weekList;
	}

	@Override
	public List<LabelBean> getGrpList() {
		List<LabelBean> grpList = null;

		StringBuffer query = new StringBuffer();
		query.append("SELECT grpId as id, grpName as value FROM EhfmGrps order by grpName");
		grpList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return grpList;
	}

	@Override
	public List<LabelBean> getShiftList() {
		List<LabelBean> shiftList = null;

		StringBuffer query = new StringBuffer();
		query.append("SELECT shiftCode as id, shiftName as value FROM EhfmShifts order by shiftName");
		shiftList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return shiftList;
	}

	@Override
	public List<LabelBean> getLocList() {
		List<LabelBean> locList = null;

		StringBuffer query = new StringBuffer();
		query.append("SELECT id.locId as id, locName as value FROM EhfmLocations where locHdrId='LH6' order by locName");
		locList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return locList;
	}

	@Override
	public List<LabelBean> getLevelList() {
		List<LabelBean> levelList = null;

		StringBuffer query = new StringBuffer();
		query.append("SELECT id.levelId as id, levelName as value FROM EhfmUnitlevels order by levelName");
		levelList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,
				query.toString());
		return levelList;
	}

	@Override
	public List<LabelBean> getReprtngPerDetails(String level) {
		List<LabelBean> reprtngPerDetails = null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
        args[0]=level;
		query.append("SELECT unitId as id, unitName as value FROM EhfmUnits where levelId>? order by unitName");
		reprtngPerDetails = genericDao.executeHQLQueryList(
				CreateEmployeeVO.class, query.toString(),args);
		return reprtngPerDetails;
	}
 
	private Long getNextVal(String SeqId)
	{
		Long cnt = (long) 0;
		try{
			EhfmSeq SgvcSeqMst = genericDao.findById(EhfmSeq.class,String.class,SeqId);
			if(SgvcSeqMst != null)
			{
				cnt =  SgvcSeqMst.getSeqNextVal(); 
			}
			SgvcSeqMst.setSeqNextVal(SgvcSeqMst.getSeqNextVal()+1);
			genericDao.save(SgvcSeqMst);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return cnt;
	}


/*	
	@Transactional
	public String saveInvstDtls(CreateEmployeeVO createEmpVo) {
		Date date = new Date();
		String result = "";
		EhfmUnits ehfmUnits = null;
		EhfmWorkAllocation ehfmWrkAllot = null;
		 String userId = "USR"+getNextVal("USR_ID"); //UserId
		 String unitId=createEmpVo.getDeptId() + "_" +createEmpVo.getDesgId()+userId;  //UserRole
			ehfmUnits = new EhfmUnits();
			ehfmUnits.setUnitId(unitId);
			ehfmUnits.setDeptId(createEmpVo.getDeptId());
			ehfmUnits.setLevelId(createEmpVo.getLevel());
			ehfmUnits.setUnitName((createEmpVo.getUnitName()).toUpperCase());
			ehfmUnits.setParantUnitId((createEmpVo.getReportingPersonId()).toUpperCase());
			ehfmUnits.setLangId(createEmpVo.getLangId());
			ehfmUnits.setUnitShrtName((createEmpVo.getShortName()).toUpperCase());
			ehfmUnits.setGroupId(createEmpVo.getDesgId());
			ehfmUnits.setLocId(createEmpVo.getLocId());
			ehfmUnits.setCrtDt(date);
			ehfmUnits.setLstUpdDt(date);
			ehfmUnits.setUnitType("");
			ehfmUnits.setCrtUsr(createEmpVo.getCrtUsr());
			ehfmUnits.setLstUpdUsr(createEmpVo.getCrtUsr());
			
			try {
				ehfmUnits = genericDao.save(ehfmUnits);
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				result = "failure";
				}

			if (result.equalsIgnoreCase("success")) {
				
				EhfmWorkAllocationId id=new EhfmWorkAllocationId();
				id.setUserRole((createEmpVo.getUnitName()).toUpperCase());
				id.setUserDeptId(createEmpVo.getDeptId());
				ehfmWrkAllot = new EhfmWorkAllocation();
				ehfmWrkAllot.setUserCode("VACANT");
				ehfmWrkAllot.setId(id);
				ehfmWrkAllot.setUserName("VACANT");
				ehfmWrkAllot.setUserDsgnId(createEmpVo.getDesgId());
				ehfmWrkAllot.setUserLoginId("VACANT");
				//ehfmWrkAllot.setUserWorkStation(createEmpVo.getWorkLoc());
				ehfmWrkAllot.setUserWorkLocId(createEmpVo.getLocId());
				ehfmWrkAllot.setUserShiftCode(createEmpVo.getShiftCode());
				ehfmWrkAllot.setUserWeekOff(createEmpVo.getWeekOffDay());
				ehfmWrkAllot.setProcess(unitId);
				ehfmWrkAllot.setReptRole((createEmpVo.getReportingPersonName().toUpperCase()));
				ehfmWrkAllot.setCrtDt(date);
				ehfmWrkAllot.setCrtUsr(createEmpVo.getCrtUsr());
				ehfmWrkAllot.setAllocationFlag("N");
				ehfmWrkAllot.setPrimaryFlag("N");
				ehfmWrkAllot.setWorkflow(createEmpVo.getScheme());
				createEmpVo.setMsg("Vacant Position Created Sucessfully");
				try {
					ehfmWrkAllot = genericDao.save(ehfmWrkAllot);
					result="success";
					} catch (Exception e) {
					e.printStackTrace();
					result = "failure";
					createEmpVo.setMsg("Could Not create Vacant Position");
				}
			}
			
			if (result.equalsIgnoreCase("success")) {
				
				EhfmUnitGrpsMpgId id=null;
				EhfmUnitsGroupMpg ehfmUnitsGroupMpg=null;
				String[] grpList=createEmpVo.getGrpId().split("~");
				if(grpList.length>0)
				{
					for(int k=0;k<grpList.length;k++)
					{
						ehfmUnitsGroupMpg=new EhfmUnitsGroupMpg();
						id=new EhfmUnitGrpsMpgId();
						id.setUnitId(unitId);
						id.setGrpId(grpList[k]);
						ehfmUnitsGroupMpg.setId(id);
						ehfmUnitsGroupMpg.setCrtDt(new Timestamp(new Date().getTime()));
						ehfmUnitsGroupMpg.setCrtUsr(createEmpVo.getCrtUsr());
						try {
							ehfmUnitsGroupMpg = genericDao.save(ehfmUnitsGroupMpg);
							result="success";
							} catch (Exception e) {
							e.printStackTrace();
							result = "failure";
							createEmpVo.setMsg("Could Not create Vacant Position");
						}
						
					}
				}
				
			}
		
		return result;

	}*/



	@Override
	public List<LabelBean> getVacantDetails(String userDeptId) {
		List<LabelBean> vacantList = null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
       args[0]=userDeptId;
       try{
        query.append("SELECT id.userDeptId as id,id.userRole as value FROM EhfmWorkAllocation where id.userDeptId=? and allocationFlag='N' order by id.userRole");
		vacantList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
       }catch(Exception e){
    	   e.printStackTrace();
       }
		return vacantList;
	}
/*
	@Override
	public String checkEmpNo(String userId, String editFlg) {
		String duplicateFlag="false";
		if(editFlg==null){
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
	        criteriaList.add ( new GenericDAOQueryCriteria ( "userNo", userId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
	        List<EhfmUsers> empNoList=genericDao.findAllByCriteria(EhfmUsers.class,criteriaList);
	        criteriaList.removeAll(criteriaList);
	        if(empNoList!=null && empNoList.size()>0) 
	        {
	                duplicateFlag="true";
	        }
			
		}
		
        return duplicateFlag;
	
	}*/
/*	@Override
	public String checkUnitName(String unitName) {
		String duplicateFlag="false";
			List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
	        criteriaList.add ( new GenericDAOQueryCriteria ("unitName", unitName.toUpperCase(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
	        List<EhfmUnits> unitNameList=genericDao.findAllByCriteria(EhfmUnits.class,criteriaList);
	        criteriaList.removeAll(criteriaList);
	        if(unitNameList!=null && unitNameList.size()>0) 
	        {
	                duplicateFlag="true";
	        }
        return duplicateFlag;
	
	}*/
	@Override
/*	public String checkLoginName(String loginName) {
		String duplicateFlag="false";
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
        criteriaList.add ( new GenericDAOQueryCriteria ( "loginName", loginName.toUpperCase(), GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
        List<EhfmUsers> loginNameList=genericDao.findAllByCriteria(EhfmUsers.class,criteriaList);
        criteriaList.removeAll(criteriaList);
        if(loginNameList!=null && loginNameList.size()>0) 
        {
                duplicateFlag="true";
        }
        return duplicateFlag;
	
	}*/

	public String checkEmailId(String emailId) {
		String duplicateFlag="false";
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
        criteriaList.add ( new GenericDAOQueryCriteria ( "emailId", emailId, GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
        List<EhfmUsers> emailList=genericDao.findAllByCriteria(EhfmUsers.class,criteriaList);
        criteriaList.removeAll(criteriaList);
        if(emailList!=null && emailList.size()>0) 
        {
                duplicateFlag="true";
        }
        return duplicateFlag;
	
	}
	@Override
	public List<LabelBean> getDepartment(String department) {
		List<LabelBean> dept=null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
		args[0] = department;
		query.append("SELECT deptId as id, deptName as value FROM EhfmDepts where deptId=? order by deptName");
		dept=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);

		return dept;
	}

	@Override
	public List<LabelBean> getDesgDetails(String vacPos,String dept) {
		
		List<LabelBean> desg=null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		args[0] = vacPos;
		args[1]=dept;
		query.append("SELECT d.id.dsgnId as id, d.dsgnName as value FROM EhfmWorkAllocation w,EhfmDesignation  d  where w.userDsgnId=d.id.dsgnId and w.id.userRole=? and w.id.userDeptId=? order by d.dsgnName");
		
		desg=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);
		
		return desg;
	}

	@Override
	public List<LabelBean> getLevelDetails(String vacPos,String dept) {
		List<LabelBean> level=null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		args[0] = vacPos;
		args[1]=dept;
		query.append("SELECT l.id.levelId as id,l.levelName as value FROM EhfmUnits u, EhfmWorkAllocation w,EhfmUnitlevels l where l.id.levelId=u.levelId and u.unitId=w.process and w.id.userRole=? and w.id.userDeptId=? order by l.levelName ");
		level=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);
		
		return level;
	}
	
	@Override
	public List<LabelBean> getReportingPerDtls(String vacPos,String dept) {
		List<LabelBean> reprtngPersonDtls=null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		args[0] = vacPos;
		args[1]=dept;
		query.append("Select u.unitId as id,u.unitName as value from EhfmUnits u, EhfmWorkAllocation w where w.id.userRole=? and w.id.userDeptId=? and w.reptRole=u.unitId order by u.unitName");
		
		reprtngPersonDtls=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);
		
		return reprtngPersonDtls;
	}
	
	@Override
	//Added by G.Namratha
	public List<LabelBean> getReportingPerson(String dept,String level,String schemeId) {
		List<LabelBean> reprtngPerson=null;
		StringBuffer query = new StringBuffer();
		
		
		
		if(!schemeId.equalsIgnoreCase("CD203"))
		{

			String args[] = new String[3];
			args[0]=dept;
			args[1] = level;
			args[2]=schemeId;
			query.append("Select u.unitId as ID,w.userName as VALUE from EhfmUnits u, EhfmWorkAllocation w where w.id.userRole=u.unitName and u.deptId=? and u.levelId>? and w.allocationFlag='Y' and w.workflow=? order by w.userName");
			reprtngPerson = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		else
		{

			String args[] = new String[2];
			args[0]=dept;
			args[1] = level;
			query.append("Select u.unitId as ID,w.userName as VALUE from EhfmUnits u, EhfmWorkAllocation w where w.id.userRole=u.unitName and u.deptId=? and u.levelId>? and w.allocationFlag='Y' and (w.workflow='CD202' or w.workflow='CD201' or w.workflow='CD203') order by w.userName");
			reprtngPerson = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		
		
		
		return reprtngPerson;	
	}
	//Added by G.Namratha
	@Override
	public List<LabelBean> getStates() {
		List<LabelBean> stateList = null;
		try {
		StringBuffer query = new StringBuffer();
		String[] args = new String[1];
		args[0] = "ST";
		query.append("SELECT id.locId as ID, locName as VALUE FROM EhfmLocations where " +
				"id.locParntId=? order by locName");
		stateList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateList;
	}
	// To get the district list based on the state selected
	@Override
	public List<LabelBean> getDistrictList(String state) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> distList=null;
		String args[] = new String[2];
		args[0]=state;
		args[1]= "LH6";
		query.append("SELECT id.locId as LOCID,locName as DISTRICTNAME  from " +
					"EhfmLocations where id.locParntId=? and locHdrId=? and activeYn='Y' order by locName ");
		distList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		return distList;
	}
	@Override
	public List<LabelBean> getGroupDtls(String vacPos, String dept) {
		List<LabelBean> grpDtls=null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		args[0] = vacPos;
		args[1]=dept;
		query.append("SELECT g.grpId as id,g.grpName as value FROM EhfmGrps g, EhfmWorkAllocation w,EhfmUnits u where g.grpId=u.groupId and u.unitId=w.process and w.id.userRole=? and w.id.userDeptId=? order by g.grpName ");
		grpDtls=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);
		return grpDtls;
		
	}

	public List<LabelBean> getVacantDtls() {
		List<LabelBean> vacantList = null;
		StringBuffer query = new StringBuffer();

       try{

   query.append("select ds.id.dsgnId as ID ,ds.dsgnName as VALUE FROM EhfmDispDesignation ds order by ds.dsgnName ");
   vacantList = genericDao.executeHQLQueryList(LabelBean.class,query.toString());
    	   
    	
       }catch(Exception e){
    	   e.printStackTrace();
       }
		return vacantList;
	}

	
	/*@Override
	public String checkVacantPosition(String dep, String vacPos) {
		String duplicateFlag="false";
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
        criteriaList.add ( new GenericDAOQueryCriteria ( "id.userDeptId", dep.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
        criteriaList.add ( new GenericDAOQueryCriteria ( "id.userRole", vacPos.toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
        List<EhfmWorkAllocation> vacPosList=genericDao.findAllByCriteria(EhfmWorkAllocation.class,criteriaList);
        criteriaList.removeAll(criteriaList);
        if(vacPosList!=null && vacPosList.size()>0) 
        {
                duplicateFlag="true";
        }
        return duplicateFlag;
	
	}

	@Override
	public String getUserSeqId() {
		// TODO Auto-generated method stub@Override
		
			String userSeqId = null;
			try {
				EhfmSeq ehfmseq = commonService.getNextVal("USERID");
				StringBuffer id = new StringBuffer();
				id.append(ehfmseq.getSeqNextVal().toString());
				userSeqId = id.toString();

			} catch (Exception e) {
				e.printStackTrace();
			}
			

			return userSeqId;

		
		
	}
	 *//**
     * @return String lStrSeqRetVal
     * @function This method is used to get the sequence next value of Vendor sequence.
     *//*
 public Number getVendorSequence(){   
	 Number lStrSeqRetVal = 0L;
		try{
	     
	    	StringBuffer query = new StringBuffer();
	        query = new StringBuffer();
	    	query.append(" SELECT VENDOR_MASTER_ID.NEXTVAL COUNT FROM DUAL ");
	        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

	        if(seqList != null){
	        	
	        	
	          lStrSeqRetVal = seqList.get(0).getCOUNT();
	        }
	    }catch(Exception e){
	    	
	    	e.printStackTrace();
	    	
	    }
	 return lStrSeqRetVal;
	 
 }
 
	@Override
	@Transactional
	public String saveCreateEmpDtls(CreateEmployeeVO createEmpVo) {
	
		String result = "";
		EhfmUsers ehfmUsers = null;
		EhfmUserDtls ehfmUserDtls=null;
		EhfmWorkAllocation ehfmWrkAllot = null;
		EhfmUsersUnitDtls ehfmUsrUnitDtls=null;
		EhfmUsrDsgnDtls ehfmUsrDsgnDtls=null;
		EhfmUsrGrpsMpg ehfmUsrGrpMpg=null;
		String userId = "";
		//String userIdPresent="";
		String userType="";
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
		
		
		ehfmWrkAllot =genericDao.findById(EhfmWorkAllocation.class, EhfmWorkAllocationId.class,new EhfmWorkAllocationId(createEmpVo.getUserRole(),createEmpVo.getDeptId()));
		if(ehfmWrkAllot!=null){
					
		
		ehfmWrkAllot.setLstUpdDt(new Timestamp(new Date().getTime()));
		ehfmWrkAllot.setLstUpdUsr(createEmpVo.getCrtUsr());
		userType=ehfmWrkAllot.getWorkflow();
		ehfmWrkAllot.setPrimaryFlag("Y");
		ehfmWrkAllot.setUserDsgnId(createEmpVo.getDesgId());
		
		//to deactivate
		if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
		{
			ehfmWrkAllot.setUserCode("VACANT");
			ehfmWrkAllot.setUserName("VACANT");
			ehfmWrkAllot.setUserLoginId("VACANT");
			ehfmWrkAllot.setAllocationFlag("N");
		}
		else
		{
			ehfmWrkAllot.setUserCode((createEmpVo.getUserNo()));
			ehfmWrkAllot.setUserName((createEmpVo.getfName()+" "+createEmpVo.getlName()).toUpperCase());
			ehfmWrkAllot.setUserLoginId((createEmpVo.getLoginName()).toUpperCase());
			ehfmWrkAllot.setAllocationFlag("Y");
		}

		try {
			
			ehfmWrkAllot = genericDao.save(ehfmWrkAllot);
			result = "success";
			} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
				}
		}
		
		
		
			if (result.equalsIgnoreCase("success")) {
				
				criteriaList.add ( new GenericDAOQueryCriteria ( "userNo", createEmpVo.getUserNo(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
		        List<EhfmUsers> userDtls=genericDao.findAllByCriteria(EhfmUsers.class,criteriaList);
		        criteriaList.removeAll(criteriaList);
		        if(userDtls.size()>0)
		        {
		       // userIdPresent =userDtls.get(0).getUserId();
		        userId=userDtls.get(0).getUserId();
		        ehfmUsers =genericDao.findById(EhfmUsers.class, String.class,userId);
		        }
				
				if(ehfmUsers==null){
					ehfmUsers = new EhfmUsers();
					userId="USR"+getNextVal("USR_ID"); //UserId
					ehfmUsers.setUserId(userId); 
					ehfmUsers.setCrtDt(new Timestamp(new Date().getTime()));
					ehfmUsers.setPasswd(createEmpVo.getPwd());
					ehfmUsers.setCrtUsr(createEmpVo.getCrtUsr());		
				}      
				
				
				
				     ehfmUsers.setLstUpdDt(new Timestamp(new Date().getTime()));
				      ehfmUsers.setLstUpdUser(createEmpVo.getCrtUsr());
					ehfmUsers.setUserNo(createEmpVo.getUserNo());
					ehfmUsers.setLocId(createEmpVo.getLocId());
					ehfmUsers.setLoginName((createEmpVo.getLoginName()).toUpperCase());
					ehfmUsers.setFirstName(createEmpVo.getfName().toUpperCase());
					ehfmUsers.setLastName(createEmpVo.getlName().toUpperCase());
					ehfmUsers.setDoj(createEmpVo.getDoj());
					if((createEmpVo.getDesgId()!=null))
					ehfmUsers.setDsgnId((createEmpVo.getDesgId().toUpperCase()));
					else
					ehfmUsers.setDsgnId("NA");
					ehfmUsers.setEmailId(createEmpVo.getEmail());
					ehfmUsers.setServiceFlag(createEmpVo.getServiceFlg());
					ehfmUsers.setLangId(createEmpVo.getLangId());
					ehfmUsers.setMobileNo(createEmpVo.getMobileNo());
					ehfmUsers.setFrstLognFlg("Y");
					if(createEmpVo.getEditFlg()!=null){
					String pswd=decryptPassword((createEmpVo.getLoginName()).toUpperCase());
					ehfmUsers.setPasswd(pswd);
					}
					
					//To deactivate
					if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
					{
						ehfmUsers.setServiceExpiryDt(new Timestamp(new Date().getTime()));
						ehfmUsers.setPeriodTo(new Timestamp(new Date().getTime()));
						
					}
					ehfmUsers.setBioAuthReq(createEmpVo.getBiometricFlag());
					ehfmUsers.setDigiVerifyReq(createEmpVo.getDigiVerifyReq());
					ehfmUsers.setPeriodFrom(new Timestamp(new Date().getTime()));
					ehfmUsers.setUserType(userType);
					ehfmUsers.setTheme("darkgreen");
					try {
						ehfmUsers = genericDao.save(ehfmUsers);
						result = "success";
					} catch (Exception e) {
						e.printStackTrace();
						result = "failure";
						}
				
				
			}
				if (result.equalsIgnoreCase("success")) {
					ehfmUserDtls =genericDao.findById(EhfmUserDtls.class, String.class,userId);
					if(ehfmUserDtls==null){
						ehfmUserDtls = new EhfmUserDtls();
						ehfmUserDtls.setUserId(userId);	
						ehfmUserDtls.setCrtDt(new Timestamp(new Date().getTime()));
						ehfmUserDtls.setCrtUsr(createEmpVo.getCrtUsr());
					}
					else
					{
						ehfmUserDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
						ehfmUserDtls.setLastUpdDt(new Timestamp(new Date().getTime()));
					}
								
					ehfmUserDtls.setFirstName(createEmpVo.getfName());
					ehfmUserDtls.setLastName(createEmpVo.getlName());
					ehfmUserDtls.setEmailId(createEmpVo.getEmail());
					ehfmUserDtls.setDob(createEmpVo.getDob());
					ehfmUserDtls.setAddr1(createEmpVo.getAddr());
					ehfmUserDtls.setAddr2(createEmpVo.getAddr());
					ehfmUserDtls.setAddrMandMunci(createEmpVo.getAddrMandMunci());
					ehfmUserDtls.setMandMunci(createEmpVo.getMandMunci());
					ehfmUserDtls.setCity(createEmpVo.getCity());
					ehfmUserDtls.setDistrict(createEmpVo.getDist());
					ehfmUserDtls.setState(createEmpVo.getState());
					ehfmUserDtls.setCountry(createEmpVo.getCountry());
					ehfmUserDtls.setPin(createEmpVo.getPin());
					ehfmUserDtls.setCity1(createEmpVo.getCity());
					ehfmUserDtls.setState1(createEmpVo.getState());
				    ehfmUserDtls.setCountry1(createEmpVo.getCountry());
					ehfmUserDtls.setPin1(createEmpVo.getPin());
					ehfmUserDtls.setPhoneNo(createEmpVo.getMobileNo());
					ehfmUserDtls.setAddress(createEmpVo.getAddr());
					ehfmUserDtls.setGender(createEmpVo.getGender());
					ehfmUserDtls.setLangId(createEmpVo.getLangId());
					ehfmUserDtls.setLocId(createEmpVo.getLocId());
					ehfmUserDtls.setDistrict1(createEmpVo.getDist());
			    	ehfmUserDtls.setAddrMandMunci1(createEmpVo.getAddrMandMunci());
					
					try {
						ehfmUserDtls = genericDao.save(ehfmUserDtls);
						result = "success";
						} catch (Exception e) {
						e.printStackTrace();
						result = "failure";
							}
			}
					if (result.equalsIgnoreCase("success")) {
						if(createEmpVo.getEditFlg()==null||createEmpVo.getEditFlg().equalsIgnoreCase("null")){
							
					        criteriaList.add ( new GenericDAOQueryCriteria ( "unitName", createEmpVo.getUnitName(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
					        List<EhfmUnits> unitDtls=genericDao.findAllByCriteria(EhfmUnits.class,criteriaList);
					        criteriaList.removeAll(criteriaList);
					        String unitId=unitDtls.get(0).getUnitId();
							EhfmUsersUnitDtlsId id=new EhfmUsersUnitDtlsId();
							id.setUnitId(createEmpVo.getUnitId());
							id.setUserId(userId);
							id.setStartDt(new Timestamp(new Date().getTime()));
							ehfmUsrUnitDtls = new EhfmUsersUnitDtls();
							ehfmUsrUnitDtls.setEhfmUserUnitDtlsId(id);
							ehfmUsrUnitDtls.setCrtDt(new Timestamp(new Date().getTime()));
							ehfmUsrUnitDtls.setCrtUsr(createEmpVo.getCrtUsr());
							ehfmUsrUnitDtls.setLangId(createEmpVo.getLangId());
							ehfmUsrUnitDtls.setLocId(createEmpVo.getLocId());
						
						try {
							ehfmUsrUnitDtls = genericDao.save(ehfmUsrUnitDtls);
							result = "success";
							} catch (Exception e) {
							e.printStackTrace();
							result = "failure";
								}
						}
						if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
						{
							  criteriaList.add ( new GenericDAOQueryCriteria ( "unitName", createEmpVo.getUserRole(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
						        List<EhfmUnits> unitDtls=genericDao.findAllByCriteria(EhfmUnits.class,criteriaList);
						        criteriaList.removeAll(criteriaList);
						        String unitId="";
						        if(unitDtls.size()>0)
						        {
						        	 unitId=unitDtls.get(0).getUnitId();
						        } 
						         EhfmUsersUnitDtls ehfmUsersUnitDtls = null;
								  
						         criteriaList.add ( new GenericDAOQueryCriteria ( "id.unitId", createEmpVo.getUnitId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
						         criteriaList.add ( new GenericDAOQueryCriteria ( "id.userId", userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
							        List<EhfmUsersUnitDtls> userUnitDtls=genericDao.findAllByCriteria(EhfmUsersUnitDtls.class,criteriaList);
							        criteriaList.removeAll(criteriaList);
						         
							        if(userUnitDtls.size()>0)
							        {
							        	ehfmUsersUnitDtls=userUnitDtls.get(0);
							        }
						         
						         if(ehfmUsersUnitDtls!=null){
								
						        	 ehfmUsersUnitDtls.setEndDt(new Timestamp(new Date().getTime()));
						        	 ehfmUsersUnitDtls.setLstUpdDt(new Timestamp(new Date().getTime()));
						        	 ehfmUsersUnitDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
						}
						         
						         try {
						        	 ehfmUsersUnitDtls = genericDao.save(ehfmUsersUnitDtls);
										result = "success";
										} catch (Exception e) {
										e.printStackTrace();
										result = "failure";
											}
						}
						
					
			}
					if (result.equalsIgnoreCase("success")) {
						
						List<LabelBean> listOfGrps=getGroupList(createEmpVo.getUnitId());
						for(int j=0;j<listOfGrps.size();j++)
						{
						ehfmUsrGrpMpg =genericDao.findById(EhfmUsrGrpsMpg.class, EhfmUsrGrpsMpgId.class,new EhfmUsrGrpsMpgId(listOfGrps.get(j).getGrpId(),userId,createEmpVo.getLangId()));
						
						if(ehfmUsrGrpMpg==null){
							EhfmUsrGrpsMpgId id=new EhfmUsrGrpsMpgId();
							id.setGrpId(listOfGrps.get(j).getGrpId());
							id.setUsergrpId(userId);
							id.setLangId(createEmpVo.getLangId());;
							ehfmUsrGrpMpg=new EhfmUsrGrpsMpg();
							ehfmUsrGrpMpg.setId(id);
							ehfmUsrGrpMpg.setCrtDt(new Timestamp(new Date().getTime()));
							ehfmUsrGrpMpg.setCrtUsr(createEmpVo.getCrtUsr());
						}else{
							ehfmUsrGrpMpg.setLstUpdUsr(createEmpVo.getCrtUsr());
							ehfmUsrGrpMpg.setLstUpdDt(new Timestamp(new Date().getTime()));
						}
						
						
						ehfmUsrGrpMpg.setFlag("G");
						ehfmUsrGrpMpg.setLocId(createEmpVo.getLocId());
						
						
					
					try {
						ehfmUsrGrpMpg = genericDao.save(ehfmUsrGrpMpg);
						result = "success";
						} catch (Exception e) {
						e.printStackTrace();
						result = "failure";
							}
						}
					
			      }
					
					 if (result.equalsIgnoreCase("success")) {
                     	
                     	if(userId!=null && !"".equalsIgnoreCase(userId))
     					{

     								EhfmUsersUnitDtlsAuditId id=new EhfmUsersUnitDtlsAuditId();
     								id.setUserId(userId);
     								id.setUnitId(createEmpVo.getUnitId());
     								Long actOrd=(long) 1;
     								id.setActOrder(actOrd);
     								Date dt=new Date();
     								id.setStartDt(dt);
     								
     								EhfmUsersUnitDtlsAudit userUnit=new EhfmUsersUnitDtlsAudit();
     								userUnit.setId(id);
     								userUnit.setLocId("LC1");
     								userUnit.setLangId("en_US");
     								userUnit.setCrtDt(dt);
     								userUnit.setCrtUsr("admin-tcs");
     								
     								try {
     									userUnit = genericDao.save(userUnit);;
    										result = "Success";
    										} catch (Exception e) {
    										e.printStackTrace();
    										result = "failure";
    										result ="Could Not create Employee";
    									}
     								
     						
     					}
     					
                     }
                     if (result.equalsIgnoreCase("success")) {
						
                    	 if(createEmpVo.getEditFlg()==null){
						ehfmUsrDsgnDtls = new EhfmUsrDsgnDtls();
						EhfmUsrDsgnDtlsId id=new EhfmUsrDsgnDtlsId();
						id.setUserId(userId);
						id.setDsgnId(createEmpVo.getDesgId());
						id.setStartDt(new Timestamp(new Date().getTime()));
						ehfmUsrDsgnDtls.setId(id);
						ehfmUsrDsgnDtls.setCrtDt(new Timestamp(new Date().getTime()));
						ehfmUsrDsgnDtls.setCrtUsr(createEmpVo.getCrtUsr());
					
                    	 }else{
                    		  Date endDate=null;				
      				        criteriaList.add ( new GenericDAOQueryCriteria ( "id.userId", userIdPresent,GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
      				        //criteriaList.add ( new GenericDAOQueryCriteria ( "id.dsgnId", createEmpVo.getDesgId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
      				        criteriaList.add ( new GenericDAOQueryCriteria ( "endDt",null,GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ) ) ;
      				        List<EhfmUsrDsgnDtls> dsgnDtls1=genericDao.findAllByCriteria(EhfmUsrDsgnDtls.class,criteriaList);
      				      criteriaList.removeAll(criteriaList);
      				        ehfmUsrDsgnDtls=new EhfmUsrDsgnDtls();
      				        ehfmUsrDsgnDtls=dsgnDtls1.get(0);
      				        ehfmUsrDsgnDtls.setEndDt(new Timestamp(new Date().getTime()));
      				        
      				        if(dsgnDtls1.get(0).getId().getDsgnId()!=createEmpVo.getDesgId()) {
      				        				        	
      				        	ehfmUsrDsgnDtls = new EhfmUsrDsgnDtls();
      							EhfmUsrDsgnDtlsId id=new EhfmUsrDsgnDtlsId();
      							id.setUserId(userIdPresent);
      							id.setDsgnId(createEmpVo.getDesgId());
      							id.setStartDt(new Timestamp(new Date().getTime()));
      							ehfmUsrDsgnDtls.setId(id);
      							ehfmUsrDsgnDtls.setEndDt(null);
      							ehfmUsrDsgnDtls.setCrtDt(new Timestamp(new Date().getTime()));
      							ehfmUsrDsgnDtls.setCrtUsr(createEmpVo.getCrtUsr());
      				        
      				        
      						}
                    	 }
						
 						ehfmUsrDsgnDtls.setLangId(createEmpVo.getLangId());
						ehfmUsrDsgnDtls.setLocId(createEmpVo.getLocId());
						ehfmUsrDsgnDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
						ehfmUsrDsgnDtls.setLstUpdDt(new Timestamp(new Date().getTime()));
		
			        try {
						ehfmUsrDsgnDtls = genericDao.save(ehfmUsrDsgnDtls);
						result = "Success";
						} catch (Exception e) {
						e.printStackTrace();
						result = "failure";
						result ="Could Not create Employee";
							}
						
						
						
						
			        }
					
				if (result.equalsIgnoreCase("success")) {
					
            		 criteriaList.add ( new GenericDAOQueryCriteria ( "id.userId", userId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
               		criteriaList.add ( new GenericDAOQueryCriteria ( "id.dsgnId", createEmpVo.getDesgId(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
               		 criteriaList.add ( new GenericDAOQueryCriteria ( "endDt",null,GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ) ) ;
				        List<EhfmUsrDsgnDtls> dsgnDtls1=genericDao.findAllByCriteria(EhfmUsrDsgnDtls.class,criteriaList);
				       criteriaList.removeAll(criteriaList);
				        if(dsgnDtls1.size()==0)
				        {
				      ehfmUsrDsgnDtls=new EhfmUsrDsgnDtls();
					EhfmUsrDsgnDtlsId id=new EhfmUsrDsgnDtlsId();
					
					id.setUserId(userId);
					id.setDsgnId(createEmpVo.getDesgId());
					id.setStartDt(new Timestamp(new Date().getTime()));
					ehfmUsrDsgnDtls.setId(id);
					ehfmUsrDsgnDtls.setCrtDt(new Timestamp(new Date().getTime()));
					ehfmUsrDsgnDtls.setCrtUsr(createEmpVo.getCrtUsr());
				        }
				        else{
				        	 ehfmUsrDsgnDtls=dsgnDtls1.get(0);
		               		ehfmUsrDsgnDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
							ehfmUsrDsgnDtls.setLstUpdDt(new Timestamp(new Date().getTime()));   
							
							if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
							{
								ehfmUsrDsgnDtls.setEndDt(new Timestamp(new Date().getTime()));
							}
								
		               	 }
               	 
					
					ehfmUsrDsgnDtls.setLangId(createEmpVo.getLangId());
					ehfmUsrDsgnDtls.setLocId(createEmpVo.getLocId());
					
	
		        try {
					ehfmUsrDsgnDtls = genericDao.save(ehfmUsrDsgnDtls);
					result = "Success";
					} catch (Exception e) {
					e.printStackTrace();
					result = "failure";
					result ="Could Not create Employee";
						}
					
					
               	 
					
		        }
					
					
					if (result.equalsIgnoreCase("success")) {
					
						if(createEmpVo.getAccNumber()!=null && !"null".equalsIgnoreCase(createEmpVo.getAccNumber()) && !"".equals(createEmpVo.getAccNumber())	){
						EhfmEmpAcctDtls ehfmEmpAcctDtls;
						
						ehfmEmpAcctDtls =genericDao.findById(EhfmEmpAcctDtls.class, EhfmEmpAcctDtlsId.class,new EhfmEmpAcctDtlsId(userId,createEmpVo.getAccNumber()));
						if(ehfmEmpAcctDtls==null){
							EhfmEmpAcctDtlsId id=new EhfmEmpAcctDtlsId();
							if(userId!="")
							id.setUserID(userId);
							else
								id.setUserID(userId);
							id.setAccountNo(createEmpVo.getAccNumber());
							ehfmEmpAcctDtls = new EhfmEmpAcctDtls();
							ehfmEmpAcctDtls.setId(id);
							ehfmEmpAcctDtls.setCrtDt(new Timestamp(new Date().getTime()));
							ehfmEmpAcctDtls.setCrtUsr(createEmpVo.getCrtUsr());
							
						}
						else
						{
							
							ehfmEmpAcctDtls.setLstUpdDt(new Timestamp(new Date().getTime()));
							ehfmEmpAcctDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
						}
					
							ehfmEmpAcctDtls.setBankId(createEmpVo.getBankName());
							ehfmEmpAcctDtls.setNameAsPerAcc(createEmpVo.getAccHolderName());
							ehfmEmpAcctDtls.setPanHolderName(createEmpVo.getPanName());
							ehfmEmpAcctDtls.setPanNumber(createEmpVo.getPanNumber());
							if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
							{
								ehfmEmpAcctDtls.setActiveYn("N");
							}
							else
							ehfmEmpAcctDtls.setActiveYn("Y");
						     
				        try {
				        	ehfmEmpAcctDtls = genericDao.save(ehfmEmpAcctDtls);
							result = "Success";
							} catch (Exception e) {
							e.printStackTrace();
							result = "failure";
							result ="Could Not create Employee";
						}
							
							
							String vendorId="";
							if (result.equalsIgnoreCase("success")) {
								EhfmEmployeeMaster ehfmEmployeeMaster=null;
								
								ehfmEmployeeMaster =genericDao.findById(EhfmEmployeeMaster.class, String.class,userId);
								if(ehfmEmployeeMaster==null){
									ehfmEmployeeMaster=new EhfmEmployeeMaster();
									 vendorId="VD"+getVendorSequence();
									 if(userId!="")
										 ehfmEmployeeMaster.setUserId(userId);
											else
												ehfmEmployeeMaster.setUserId(userId);
									ehfmEmployeeMaster.setVendorId(vendorId);
									ehfmEmployeeMaster.setUserCode(createEmpVo.getUserNo());
									ehfmEmployeeMaster.setAppointmentDate(new Timestamp(new Date().getTime()));
									ehfmEmployeeMaster.setCrtDt(new Timestamp(new Date().getTime()));
									ehfmEmployeeMaster.setCrtUsr(createEmpVo.getCrtUsr());
									
								}
								else
								{
									vendorId=ehfmEmployeeMaster.getVendorId();
									ehfmEmployeeMaster.setLstUpdUsr(createEmpVo.getCrtUsr());
									ehfmEmployeeMaster.setLstUpdDt(new Timestamp(new Date().getTime()));
								}
								
								ehfmEmployeeMaster.setAddress(createEmpVo.getAddr());
								ehfmEmployeeMaster.setBirthDate(createEmpVo.getDob());
							
								ehfmEmployeeMaster.setDesigId(createEmpVo.getDesgId());
								ehfmEmployeeMaster.setEmailId(createEmpVo.getEmail());
								ehfmEmployeeMaster.setFirstName(createEmpVo.getfName());
								ehfmEmployeeMaster.setGender(createEmpVo.getGender());
				
								ehfmEmployeeMaster.setInService(createEmpVo.getServiceFlg());
								ehfmEmployeeMaster.setJoinDate(new Timestamp(new Date().getTime()));
								ehfmEmployeeMaster.setLastName(createEmpVo.getlName());
								ehfmEmployeeMaster.setMobileNo(createEmpVo.getMobileNo());
								ehfmEmployeeMaster.setPanNo(createEmpVo.getPanNumber());
								ehfmEmployeeMaster.setPayRole("CD1025");
								ehfmEmployeeMaster.setSchemeId(userType);
								
								
								 try {
									 ehfmEmployeeMaster = genericDao.save(ehfmEmployeeMaster);
										result = "Success";
										} catch (Exception e) {
										e.printStackTrace();
										result = "failure";
										result ="Could Not create Employee";
									}
								
								
							}
							
                           if (result.equalsIgnoreCase("success")) {
								
                        	   
                        	   EhfmVendorMaster ehfmVendorMaster=null;
								
                        	   ehfmVendorMaster =genericDao.findById(EhfmVendorMaster.class, String.class,vendorId);
								if(ehfmVendorMaster==null){
									ehfmVendorMaster=new EhfmVendorMaster();
									ehfmVendorMaster.setVid(vendorId);
									ehfmVendorMaster.setVendorCode(vendorId);
									ehfmVendorMaster.setCtrDt(new Timestamp(new Date().getTime()));
									ehfmVendorMaster.setCrtUsr(createEmpVo.getCrtUsr());
									
								}
								else
								{
									ehfmVendorMaster.setLstUpdUsr(createEmpVo.getCrtUsr());
									ehfmVendorMaster.setLstUpdDt(new Timestamp(new Date().getTime()));
								}
                        	   
                        	 
                        	   ehfmVendorMaster.setVendorName(createEmpVo.getfName()+" "+createEmpVo.getlName());
                        	   ehfmVendorMaster.setVendorAddress(createEmpVo.getAddr());
                        	   ehfmVendorMaster.setVendorEmail(createEmpVo.getEmail());
                        	   ehfmVendorMaster.setBankId(createEmpVo.getBankName());
                        	   String bankName=createEmpVo.getBankName();
                        	   
                        	   try {
                        		   EhfmBankMaster bankDtls = genericDao.findById(EhfmBankMaster.class,String.class,bankName);
              						if(bankDtls != null)
              						{
              						  bankName=  bankDtls.getBankName();
              						}
										} catch (Exception e) {
										e.printStackTrace();
									}
                        	   
                        	   ehfmVendorMaster.setBankName(bankName);
                        	   ehfmVendorMaster.setBankAccountNo(createEmpVo.getAccNumber());
                        	   ehfmVendorMaster.setNameOnBankAccount(createEmpVo.getAccHolderName());
								ehfmVendorMaster.setPanNo(createEmpVo.getPanNumber());
								ehfmVendorMaster.setVendorContactNo(createEmpVo.getMobileNo());
								ehfmVendorMaster.setPanHolderName(createEmpVo.getPanName());
								ehfmVendorMaster.setAccountCode(createEmpVo.getAccNumber());
								if(!userType.equals(""))
								ehfmVendorMaster.setScheme(userType);
								else
									ehfmVendorMaster.setScheme("CD203");
								if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
								{
									ehfmVendorMaster.setActiveYN("N");
								}
								else
								ehfmVendorMaster.setActiveYN("Y");
								 try {
									 ehfmVendorMaster = genericDao.save(ehfmVendorMaster);
										result = "Success";
										} catch (Exception e) {
										e.printStackTrace();
										result = "failure";
										result ="Could Not create Employee";
									}
								
								
							}
						
						
						
						}
							
					}
					
					if(createEmpVo.getEditFlg()==null||createEmpVo.getEditFlg().equalsIgnoreCase("null")){
					if (result.equalsIgnoreCase("success")) {
						HashMap lSmsDataMap = new HashMap();
						String Message =createEmpVo.getSMS();
						lSmsDataMap.put("Message", Message);
						lSmsDataMap.put("MobileNos", createEmpVo.getMobileNo());
						smsFlag(lSmsDataMap);
					}
					}
		
		
					System.out.println(result+"~"+userId);
		return result+"~"+userId;
	
	}

	@Override
	public List<LabelBean> getVacantList() {
		List<LabelBean> vacPosList = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT w.id.userRole as userRole,d.deptName as deptId,ds.dsgnName as desgId,g.grpName as grpId FROM EhfmDesignation ds, EhfmWorkAllocation w, EhfmUnits u,EhfmDepts d,EhfmGrps g where w.userCode='VACANT' and w.id.userDeptId=d.deptId and ds.id.dsgnId=w.userDsgnId and w.process=u.unitId and u.groupId=g.grpId and w.allocationFlag='N'");
		 vacPosList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString());
		return vacPosList;
		
		
	}
	@Override
	public List<LabelBean> getLocationList(String dist, String distId,String lStrHdrId) {
		List<LabelBean> locList = null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		//args[0] = dist;
		args[1]=distId;
		args[0]=lStrHdrId;
		if (dist != null && !"".equalsIgnoreCase(dist)) {
		query.append("select al.id.locId as ID, al.locName as VALUE from EhfmLocations al where al.locHdrId = ? and al.id.locParntId=? and al.activeYn = 'Y' order by al.id.locId ");
		locList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		else{
		query.append("select al.id.locId as ID, al.locName as VALUE from EhfmLocations al where al.locHdrId = ? and al.id.locParntId=? and al.activeYn = 'Y' order by al.id.locId ");
		locList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		return locList;
	}
	private void smsFlag(HashMap smsDataMap) {

		// boolean flag=true;

		SMSServices smsServices = new SMSServices();

		try {
			smsServices.sendSingleSMS((String) smsDataMap.get("Message"),
					(String) smsDataMap.get("MobileNos"));
		} catch (Exception e) {
			logger.error(" Remote Exception in method smsFlag of EmpEnrolmntServiceImpl->"
					+ e.getMessage());
			e.printStackTrace();
		}
		// return flag;
	}


	@Override
	public List<LabelBean> searchEmp(CreateEmployeeVO createEmpVo) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> searchDtls=null;
		query.append("select u.userId as userId, u.userNo as userNo,u.firstName as fName,u.serviceFlag as serviceFlg, g.grpName as grpName,d.dsgnName as desgName from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d where u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
		
		if(createEmpVo.getUserNo()!=null&&createEmpVo.getUserNo()!=""){
			query.append(" and  u.userNo='"+createEmpVo.getUserNo()+"' ");
		}
		if(createEmpVo.getLoginName()!=null&& createEmpVo.getLoginName()!="" && !createEmpVo.getLoginName().equals("")){
			query.append(" and u.loginName='"+createEmpVo.getLoginName()+"' ");
		}
		if(createEmpVo.getfName()!=null&&createEmpVo.getfName()!=""){
			query.append(" and u.firstName='"+createEmpVo.getfName()+"'");
		}
		if(createEmpVo.getGrpId()!=null&&createEmpVo.getGrpId()!="" && !createEmpVo.getGrpId().equalsIgnoreCase("null")){
			query.append(" and gs.id.grpId='"+createEmpVo.getGrpId()+"' ");
		}
		if(createEmpVo.getStatus()!=null&& createEmpVo.getStatus()!="" && !createEmpVo.getStatus().equalsIgnoreCase("null")){
			query.append(" and u.serviceFlag='"+createEmpVo.getStatus()+"' ");
		}
		searchDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString());
		return searchDtls;
	}
	
	
	@Override
	public CreateEmployeeVO loadEmpDtls(String userNo) {
		
		
			StringBuffer query = new StringBuffer();
			String args[] = new String[1];
			args[0]=userNo;
			List<CreateEmployeeVO> loadEmpDtls=null;
			query.append("SELECT u.firstName as fName,u.lastName as lName,u.userNo as userNo,u.loginName " +
					"as loginName,u.doj as doj,d.dob as dob,d.gender as gender,u.emailId as email,d.addr1 as " +
					" addr,d.addr2 as addr1,d.mandMunci as mandMunci,d.mandMunci1 as mandMunci1, " +
					" d.addrMandMunci as addrMandMunci,d.addrMandMunci1 as addrMandMunci1,d.city as city," +
					" d.city1 as city1,d.district as dist,d.district1 as dist1,d.state as state," +
					" d.country as country,d.country1 as country1,d.pin as pin,d.state1 as state1," +
					"d.pin1 as pin1,d.phoneNo as mobileNo,w.id.userDeptId as deptId,w.id.userRole as userRole," +
					"un.levelId as level,w.userDsgnId as desgId,u.userId as userId,g.id.grpId as grpId," +
					"u.serviceFlag as serviceFlg,u.bioAuthReq as biometricFlag," +
					"u.digiVerifyReq as digiVerifyReq FROM EhfmWorkAllocation w,EhfmUsers u,EhfmUserDtls d,EhfmUnits un," +
					"EhfmUsrGrpsMpg g where u.userNo=? and u.userNo=w.userCode and u.userId=d.userId " +
					"and w.id.userRole=un.unitName and g.id.usergrpId=u.userId ");
			loadEmpDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
			if(loadEmpDtls.size()>0)
				return loadEmpDtls.get(0);
			else
				return null;
		
			
		}
	@Override
	public CreateEmployeeVO loadEmpBankDtls(String userNo) {
			StringBuffer query = new StringBuffer();
			String args[] = new String[1];
			args[0]=userNo;
			List<CreateEmployeeVO> loadEmpBankDtls=null;
			
			query.append("select h.id.accountNo as accNumber,h.bankId as bankName,h.nameAsPerAcc as accHolderName,h.panNumber as panNumber,h.panHolderName as panName from EhfmEmpAcctDtls h where h.id.userID=?");
			loadEmpBankDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
			if(loadEmpBankDtls.size()>0)
				return loadEmpBankDtls.get(0);
			else
				return null;
	}
	@Override
	public List<LabelBean> getHospList() {
		List<LabelBean> hospList = null;
		StringBuffer query = new StringBuffer();
		query.append("select h.hospId as id,h.hospName as value from EhfmHospitals h order by hospName");
		hospList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString());
		return hospList;
	}

	@Override
	public List<LabelBean> searchLogin(CreateEmployeeVO createEmpVo) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> searchDtls=null;
		String[] args = new String[1];
		args[0] = createEmpVo.getSearchType();
		query.append("SELECT g.id.grpId as grpId,u.id.userId as userId,u.loginName as loginName,u.firstName as fName,u.lastName as lName FROM EhfmUsers u, EhfmUsrGrpsMpg g where ");
		if(createEmpVo.getLoginType().equalsIgnoreCase("userId")){
			query.append(" UPPER(u.id.userId) like UPPER('%"+createEmpVo.getSearchText()+"%')");
		}else{
			query.append(" (UPPER(u.firstName)||UPPER(u.lastName)) like UPPER('%"+createEmpVo.getSearchText()+"%')");
		}
		query.append(" and u.id.userId=g.id.usergrpId and g.id.grpId=? and u.serviceFlag='Y'");
		
		searchDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
		return searchDtls;
	}

	@Override
	public List<CreateEmployeeVO> getHospsMapped(String userId,String grpId) {
		StringBuffer query = new StringBuffer();
		List<CreateEmployeeVO> hospsMapped=null;
		String[] args = new String[1];
		args[0] =userId;
		if(grpId.equalsIgnoreCase("GP1"))
			query.append("select hospId as id,hospName as value from EhfmHospitals where hospId in(select h.id.hospId from EhfmHospMithraDtls h where h.id.mithraId=? and activeYN='Y') order by hospName");
		else
			query.append("select hospId as id,hospName as value  from EhfmHospitals where hospId in(select h.id.hospId from EhfmMedcoDtls h where h.id.medcoId=? and activeYN='Y') order by hospName");
		hospsMapped=genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
		return hospsMapped;
	}

	@Override
	public List<LabelBean> getHospsNotMapped(String userId,String grpId) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> hospsNotMapped=null;
		String[] args = new String[1];
		args[0] =userId;
		if(grpId.equalsIgnoreCase("GP1"))
			query.append("select hospId as id,hospName as value  from EhfmHospitals where hospId not in(select h.id.hospId from EhfmHospMithraDtls h where h.id.mithraId=? and activeYN='Y') order by hospName");
		else
			query.append("select hospId as id,hospName as value  from EhfmHospitals where hospId not in(select h.id.hospId from EhfmMedcoDtls h where h.id.medcoId=? and activeYN='Y') order by hospName");
		hospsNotMapped=genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
		return hospsNotMapped;
	}

	@Override
	public List<LabelBean> searchHospsList(String searchField) {
		List<LabelBean> searchHospsList = null;
		StringBuffer query = new StringBuffer();
		query.append("select hospId as id,hospName as value from EhfmHospitals  where UPPER(hospName) like UPPER('%"+searchField+"%') order by hospName");
		searchHospsList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString());
		return searchHospsList;
	}
	private String saveHospToMithra ( EhfmHospMithraDtls ehfmHospMithraDtls ) 
	{
	  String lSrResult = "failure" ;
	  try
	  {
		ehfmHospMithraDtls = genericDao.save ( ehfmHospMithraDtls ) ;
	    lSrResult = "success" ;
	  }
	  catch ( Exception e )
	  {
	    e.printStackTrace();
	  }
	  return lSrResult ;
	}
	
	private String saveHospToMedco ( EhfmMedcoDtls ehfmMedcoDtls ) 
	{
	  String lSrResult = "failure" ;
	  try
	  {
		  ehfmMedcoDtls = genericDao.save ( ehfmMedcoDtls ) ;
	    lSrResult = "success" ;
	  }
	  catch ( Exception e )
	  {
	    e.printStackTrace();
	  }
	  return lSrResult ;
	}

	
	@Override
	public String saveMapping(List<CreateEmployeeVO> newList) {
		
		Date date = new Date();
		EhfmHospMithraDtls ehfmHospMithraDtls =null;
		EhfmHospMithraDtlsId ehfmHospMithraDtlsId;
		EhfmMedcoDtls ehfmMedcoDtls=null;
		EhfmMedcoDtlsId ehfmMedcoDtlsId;
		String result ="failure";
		ehfmHospMithraDtls=new EhfmHospMithraDtls();
		ehfmHospMithraDtlsId=new EhfmHospMithraDtlsId();
		ehfmMedcoDtls=new EhfmMedcoDtls();
		ehfmMedcoDtlsId = new EhfmMedcoDtlsId();
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
		for(int i=1;i<newList.size();i++){
			if(newList.get(i).getGrpId().equalsIgnoreCase("GP1")){
				
				criteriaList.add(new GenericDAOQueryCriteria("id.hospId", newList.get(i).getHospId (), 
				          GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("id.mithraId", newList.get(i).getUserId(), 
				          GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add ( new GenericDAOQueryCriteria ( "id.startDt",date,GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
				  List<EhfmHospMithraDtls> mithraDtls=genericDao.findAllByCriteria(EhfmHospMithraDtls.class,criteriaList); 
				  if(mithraDtls.size()>0){
					  	ehfmHospMithraDtls.setActiveYN('Y');			        	
			        	ehfmHospMithraDtls.setId(mithraDtls.get(0).getId())	;
			        	ehfmHospMithraDtls.setLstUpdDt(date);
			        	ehfmHospMithraDtls.setLstUpdUser(mithraDtls.get(0).getCrtUser());
			        	ehfmHospMithraDtls.setCrtDt(mithraDtls.get(0).getCrtDt());
			        	ehfmHospMithraDtls.setCrtUser(mithraDtls.get(0).getCrtUser());
			        	ehfmHospMithraDtls.setShiftId(mithraDtls.get(0).getShiftId());
					  
					  
				  }else{
					  ehfmHospMithraDtlsId.setHospId(newList.get(i).getHospId());
						ehfmHospMithraDtlsId.setMithraId(newList.get(i).getUserId());
						ehfmHospMithraDtlsId.setStartDt(date);
						ehfmHospMithraDtls.setId(ehfmHospMithraDtlsId);
						ehfmHospMithraDtls.setActiveYN(newList.get(i).getActiveYN());
						ehfmHospMithraDtls.setCrtDt(date);
						ehfmHospMithraDtls.setShiftId("SC2");
						ehfmHospMithraDtls.setCrtUser(newList.get(i).getCrtUsr());
						
				  }
			
			ehfmHospMithraDtls.setEndDt(null);
			ehfmHospMithraDtls.setLstUpdDt(date);
			ehfmHospMithraDtls.setLstUpdUser(newList.get(i).getCrtUsr());
			try {
				ehfmHospMithraDtls = genericDao.save(ehfmHospMithraDtls);
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
				result = "failure";
				}
			}else if(newList.get(i).getGrpId().equalsIgnoreCase("GP2")){
				ehfmMedcoDtlsId.setHospId(newList.get(i).getHospId());
				ehfmMedcoDtlsId.setMedcoId(newList.get(i).getUserId());
				ehfmMedcoDtlsId.setStartDt(date);
				ehfmMedcoDtls.setId(ehfmMedcoDtlsId);
				ehfmMedcoDtls.setCrtDt(date);
				ehfmMedcoDtls.setCrtUser(newList.get(i).getCrtUsr());
				ehfmMedcoDtls.setEndDate(newList.get(i).getEndDate());
				ehfmMedcoDtls.setLstUpdDt(date);
				ehfmMedcoDtls.setLstUpdUsr(newList.get(i).getCrtUsr());
				ehfmMedcoDtls.setActiveYN(newList.get(i).getActiveYN());
				try {
					ehfmMedcoDtls = genericDao.save(ehfmMedcoDtls);
					result = "success";
				} catch (Exception e) {
					e.printStackTrace();
					result = "failure";
					}
			}
		}
		return result;
	}

	@Override
	public String deleteMappingDtls(List<CreateEmployeeVO> newList) {
		
		Date date = new Date();
		EhfmHospMithraDtls ehfmHospMithraDtls =null;
		EhfmHospMithraDtlsId ehfmHospMithraDtlsId;
		EhfmMedcoDtls ehfmMedcoDtls=null;
		EhfmMedcoDtlsId ehfmMedcoDtlsId;
		String result ="failure";
		ehfmHospMithraDtls=new EhfmHospMithraDtls();
		ehfmHospMithraDtlsId=new EhfmHospMithraDtlsId();
		ehfmMedcoDtls=new EhfmMedcoDtls();
		ehfmMedcoDtlsId = new EhfmMedcoDtlsId();
		
		for(int i=0;i<newList.size();i++){
			if(newList.get(i).getGrpId().equalsIgnoreCase("GP1")){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.hospId", newList.get(i).getHospId (), 
				          GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("id.mithraId", newList.get(i).getUserId(), 
				          GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add ( new GenericDAOQueryCriteria ( "endDt",null,GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ) ) ;
				  List<EhfmHospMithraDtls> mithraDtls=genericDao.findAllByCriteria(EhfmHospMithraDtls.class,criteriaList); 
				  if(mithraDtls.size()>=0){
			        	ehfmHospMithraDtls=new EhfmHospMithraDtls();
			        	ehfmHospMithraDtls.setActiveYN('N');
			        	ehfmHospMithraDtls.setEndDt(date);
			        	ehfmHospMithraDtls.setId(mithraDtls.get(0).getId())	;
			        	ehfmHospMithraDtls.setLstUpdDt(date);
			        	ehfmHospMithraDtls.setLstUpdUser(mithraDtls.get(0).getCrtUser());
			        	ehfmHospMithraDtls.setCrtDt(mithraDtls.get(0).getCrtDt());
			        	ehfmHospMithraDtls.setCrtUser(mithraDtls.get(0).getCrtUser());
			        	ehfmHospMithraDtls.setShiftId(mithraDtls.get(0).getShiftId());
			        	try {
			        		ehfmHospMithraDtls = genericDao.save(ehfmHospMithraDtls);
							result = "success";
						} catch (Exception e) {
							e.printStackTrace();
							result = "failure";
							}
			        }
			}
			else if(newList.get(i).getGrpId().equalsIgnoreCase("GP2")){
				List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria>();
				criteriaList.add(new GenericDAOQueryCriteria("id.hospId", newList.get(i).getHospId (), 
						GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add(new GenericDAOQueryCriteria("id.medcoId", newList.get(i).getUserId(), 
						          GenericDAOQueryCriteria.CriteriaOperator.EQUALS));
				criteriaList.add ( new GenericDAOQueryCriteria ( "endDate",null,GenericDAOQueryCriteria.CriteriaOperator.IS_NULL ) ) ;
				List<EhfmMedcoDtls> medcoDtls=genericDao.findAllByCriteria(EhfmMedcoDtls.class,criteriaList);
							        
						ehfmMedcoDtls.setActiveYN('N');
						ehfmMedcoDtls.setCrtDt(medcoDtls.get(0).getCrtDt());
						ehfmMedcoDtls.setCrtUser(medcoDtls.get(0).getCrtUser());
						ehfmMedcoDtls.setEndDate(date);
						ehfmMedcoDtls.setId(medcoDtls.get(0).getId());
						ehfmMedcoDtls.setLstUpdDt(date);
						ehfmMedcoDtls.setLstUpdUsr(medcoDtls.get(0).getLstUpdUsr());
						try {
							ehfmMedcoDtls = genericDao.save(ehfmMedcoDtls);
							result = "success";
						} catch (Exception e) {
							e.printStackTrace();
							result = "failure";
							}
						
			}
			
		}
		
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CreateEmployeeVO searchEmployeeList(CreateEmployeeVO createEmpVo) {
		SessionFactory factory=null;
		Session session=null;
		List<CreateEmployeeVO> empList= new ArrayList<CreateEmployeeVO>();
		
	    
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		StringBuffer searchQuery = new StringBuffer();
		StringBuffer countQuery = new StringBuffer();
		try
		{
			if(createEmpVo.getHospId()!=null && createEmpVo.getHospId()!="" &&  !createEmpVo.getHospId().equalsIgnoreCase("allHosp" )){	
				if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && createEmpVo.getDesgId().equalsIgnoreCase("DG3009"))
				{
					countQuery.append("select count(distinct u.loginName) as totValue from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w,EhfmMedcoDtls md,EhfmHospitals h where md.id.hospId=h.hospId and md.id.medcoId=u.userId and w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
					
				}
				else if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && createEmpVo.getDesgId().equalsIgnoreCase("DG250"))
				{
					countQuery.append("select count(distinct u.loginName) as totValue from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w,EhfmHospMithraDtls md,EhfmHospitals h where md.id.hospId=h.hospId and md.id.mithraId=u.userId and w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
					
				}
				
				else
					countQuery.append("select count(distinct u.loginName) as totValue from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w,EhfmHospitals h,EhfmHospMithraDtls md where md.id.hospId=h.hospId and md.id.mithraId=u.userId and w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
				countQuery.append(" and h.id.hospId='"+createEmpVo.getHospId()+"' ");
			}
			else
		    countQuery.append("select count(distinct u.loginName) as totValue from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w where w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
		
			if(createEmpVo.getScheme()!=null&&createEmpVo.getScheme()!=""){
				countQuery.append(" and u.userType='"+createEmpVo.getScheme()+"' ");
			}
		if(createEmpVo.getDeptId()!=null&&createEmpVo.getDeptId()!=""){
			countQuery.append(" and w.id.userDeptId='"+createEmpVo.getDeptId()+"' ");
		}
		if(createEmpVo.getLoginName()!=null&& createEmpVo.getLoginName()!="" && !createEmpVo.getLoginName().equals("")){
			countQuery.append(" and w.userLoginId like '"+("%").concat(createEmpVo.getLoginName().concat("%"))+"' "); 
		}
		if(createEmpVo.getfName()!=null&&createEmpVo.getfName()!=""){
			countQuery.append(" and (u.firstName like '"+("%").concat(createEmpVo.getfName().concat("%"))+"' or u.lastName like '"+("%").concat(createEmpVo.getfName().concat("%"))+"') ");
		}
		if(createEmpVo.getGrpId()!=null&&createEmpVo.getGrpId()!="" && !createEmpVo.getGrpId().equalsIgnoreCase("null")){
			countQuery.append(" and gs.id.grpId='"+createEmpVo.getGrpId()+"' ");
		}
		if(createEmpVo.getStatus()!=null&& createEmpVo.getStatus()!="" && !createEmpVo.getStatus().equalsIgnoreCase("null")){
			countQuery.append(" and u.serviceFlag='"+createEmpVo.getStatus()+"' ");
		}
		if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && !createEmpVo.getDesgId().equalsIgnoreCase("null")){
			countQuery.append(" and d.id.dsgnId='"+createEmpVo.getDesgId()+"' ");
		}
		if(createEmpVo.getStart_index()==0 && createEmpVo.getEnd_index()==0)
			empList =session.createQuery(countQuery.toString()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
		else
			empList =session.createQuery(countQuery.toString()).setFirstResult(createEmpVo.getStart_index()).setMaxResults(createEmpVo.getEnd_index()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
				//genericDao.executeHQLQueryList(CreateEmployeeVO.class,countQuery.toString());
		createEmpVo.setEmpSearchList(empList);
		
		
		if(createEmpVo.getHospId()!=null && createEmpVo.getHospId()!="" &&  !createEmpVo.getHospId().equalsIgnoreCase("allHosp" )){	
			if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && createEmpVo.getDesgId().equalsIgnoreCase("DG3009"))
			{
				searchQuery.append("select distinct(u.loginName) as loginName,u.firstName as fName,u.lastName as lName,u.mobileNo as mobileNo,u.emailId as email, d.dsgnName as desgName from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w,EhfmMedcoDtls md,EhfmHospitals h where md.id.hospId=h.hospId and md.id.medcoId=u.userId and w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
				
			}
			else if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && createEmpVo.getDesgId().equalsIgnoreCase("DG250"))
			{
				searchQuery.append("select distinct(u.loginName) as loginName,u.firstName as fName,u.lastName as lName,u.mobileNo as mobileNo,u.emailId as email, d.dsgnName as desgName from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w,EhfmHospMithraDtls md,EhfmHospitals h where md.id.hospId=h.hospId and md.id.mithraId=u.userId and w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
				
			}
			else
				searchQuery.append("select distinct(u.loginName) as loginName,u.firstName as fName,u.lastName as lName,u.mobileNo as mobileNo,u.emailId as email, d.dsgnName as desgName from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w,EhfmHospitals h,EhfmHospMithraDtls md where md.id.hospId=h.hospId and md.id.mithraId=u.userId and w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
			searchQuery.append(" and h.id.hospId='"+createEmpVo.getHospId()+"' ");
			
		}
		else
		searchQuery.append("select distinct(u.loginName) as loginName,u.firstName as fName,u.lastName as lName,u.mobileNo as mobileNo,u.emailId as email, d.dsgnName as desgName from EhfmUsers u,EhfmGrps g,EhfmUsrGrpsMpg gs,EhfmDesignation d,EhfmWorkAllocation w where w.userCode=u.userNo and u.userId=gs.id.usergrpId and gs.id.grpId=g.grpId and d.id.dsgnId=u.dsgnId");
		if(createEmpVo.getScheme()!=null&&createEmpVo.getScheme()!=""){
			searchQuery.append(" and u.userType='"+createEmpVo.getScheme()+"' ");
		}
		if(createEmpVo.getDeptId()!=null&&createEmpVo.getDeptId()!=""){
			searchQuery.append(" and w.id.userDeptId='"+createEmpVo.getDeptId()+"' ");
		}
		if(createEmpVo.getLoginName()!=null&& createEmpVo.getLoginName()!="" && !createEmpVo.getLoginName().equals("")){
			searchQuery.append(" and w.userLoginId like '"+("%").concat(createEmpVo.getLoginName().concat("%"))+"' ");
		}
		if(createEmpVo.getfName()!=null&&createEmpVo.getfName()!=""){
			searchQuery.append(" and (Upper(u.firstName) like '"+("%").concat(createEmpVo.getfName().concat("%"))+"' or Upper(u.lastName) like '"+("%").concat(createEmpVo.getfName().concat("%"))+"') ");
		}
		if(createEmpVo.getGrpId()!=null&&createEmpVo.getGrpId()!="" && !createEmpVo.getGrpId().equalsIgnoreCase("null")){
			searchQuery.append(" and gs.id.grpId='"+createEmpVo.getGrpId()+"' ");
		}
		if(createEmpVo.getStatus()!=null&& createEmpVo.getStatus()!="" && !createEmpVo.getStatus().equalsIgnoreCase("null")){
			searchQuery.append(" and u.serviceFlag='"+createEmpVo.getStatus()+"' ");
		}
		if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && !createEmpVo.getDesgId().equalsIgnoreCase("null")){
			searchQuery.append(" and d.id.dsgnId='"+createEmpVo.getDesgId()+"' ");
		}
		//empList = genericDao.executeHQLQueryList(CreateEmployeeVO.class,searchQuery.toString());
		if(createEmpVo.getStart_index()==0 && createEmpVo.getEnd_index()==0)
			empList =session.createQuery(searchQuery.toString()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
		else
			empList =session.createQuery(searchQuery.toString()).setFirstResult(createEmpVo.getStart_index()).setMaxResults(createEmpVo.getEnd_index()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
		createEmpVo.setEmpSearchList(empList);
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	finally
	{
	   session.close();
	   factory.close();
	   
	}
		return createEmpVo;
		
	}

	@Override
	public LabelBean getVacPosDtls(String dept, String vacPos) {
		
		String args[] = new String[2];
        args[1]=dept;
        args[0]=vacPos;
		List<LabelBean> vacPosDtls = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT u.levelId as LEVELID,ds.dsgnName as dsgName,g.grpName as grpName,u.groupId as grpId,w.userDsgnId as DSGNID,w.reptRole as reptRole FROM EhfmDesignation ds, EhfmWorkAllocation w, EhfmUnits u,EhfmGrps g where w.id.userRole=? and ds.id.dsgnId=w.userDsgnId and u.groupId=g.grpId and u.deptId=? and u.unitName=w.id.userRole");
		vacPosDtls = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		if(vacPosDtls.size()>0)
			return vacPosDtls.get(0);
		else
			return null;
		
	}
	
	@Override
	public LabelBean getVacPosDtls(String dept, String vacPos) {
		String args[] = new String[2];
        args[1]=dept;
        args[0]=vacPos;
		List<LabelBean> vacPosDtls = null;
		StringBuffer query = new StringBuffer();
		query.append("SELECT u.levelId as LEVELID,ds.dsgnName as dsgName,w.userDsgnId as DSGNID,w.reptRole as reptRole,u.id.unitId as UNITID FROM EhfmDesignation ds, EhfmWorkAllocation w, EhfmUnits u where w.id.userRole=? and ds.id.dsgnId=w.userDsgnId and u.deptId=? and u.unitName=w.id.userRole");
		vacPosDtls = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		if(vacPosDtls.size()>0)
			return vacPosDtls.get(0);
		else
			return null;
	}
	
	@Override
	public String getReportingPerName(String repPersonId) {
		List<LabelBean> reprtngPersonDtls=new ArrayList<LabelBean>();
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
		
		String unitName="";
		if(repPersonId!=null && !("").equalsIgnoreCase(repPersonId))
		{
		try {
			EhfmUnits  ehfmUnits =genericDao.findById(EhfmUnits.class, String.class,repPersonId);
				if(ehfmUnits != null)
				{
					unitName=  ehfmUnits.getUnitName();
				}
					} catch (Exception e) {
					e.printStackTrace();
				}
		args[0] = unitName;	
		query.append("Select w.userName as reptRolePerson from EhfmWorkAllocation w where w.id.userRole=? and w.allocationFlag='Y'");
		reprtngPersonDtls=genericDao.executeHQLQueryList(LabelBean.class, query.toString(),args);
		
	}
		if(reprtngPersonDtls.size()>0)
			return reprtngPersonDtls.get(0).getReptRolePerson();
		else
			return "No Reporting Person";	
	}
	
	@Override
 public String getIFSCCode(String lStrBankCode){
      	 
      	 StringBuffer query = new StringBuffer();
      	 String lStrIFSCCode = "";
      	 String args[] = new String[1];
      	 args[0] = lStrBankCode;
      	 query.append("select ifcCode as VALUE from EhfmBankMaster where bankId = ?");
      	 List<LabelBean> lStrList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
      	 if(lStrList != null)
      		 lStrIFSCCode = lStrList.get(0).getVALUE();
          return lStrIFSCCode;	 
        }
	
	@Override
	 public List<LabelBean> getBankNames() {
	        
	        StringBuffer query = new StringBuffer();
	        query.append(" SELECT bankId as ID , (bankName ||' - '|| bankBranch) as VALUE FROM EhfmBankMaster order by bankName");
	        List<LabelBean> bankList =
	            genericDao.executeHQLQueryList(LabelBean.class,query.toString());
	            return bankList;
	    }

	@Override
	public String decryptPassword(String loginName) {
		
		 StringBuffer query = new StringBuffer();
      	 String password = "";
      	 String args[] = new String[1];
      	 args[0] = loginName;
      	 query.append("select DECRYPT_USER_PSWD(?) as VALUE from dual");
      	 List<LabelBean> lStrList = genericDao.executeSQLQueryList(LabelBean.class,query.toString(),args);
      	 if(lStrList != null)
      		password = lStrList.get(0).getVALUE();
          return password;	 
		
		
		
	}

	@Override
	public List<CreateEmployeeVO> getEmployeeList(String empNumber,
			String loginName,String allocationFlag,String lStrScheme) {
		List<CreateEmployeeVO> empList=null;
		 StringBuffer query = new StringBuffer();
		 int i=0;
		 query.append("select distinct(u.userNo) as userNo, u.firstName ||' ' || u.lastName as fName,u.loginName as loginName,u.serviceFlag as serviceFlg from EhfmUsers u where");
		
		 if(!allocationFlag.equalsIgnoreCase("A"))
		  {
			  query.append(" u.serviceFlag='Y'");
		  }
		  else
		  {
			  query.append(" (u.serviceFlag='Y' or u.serviceFlag='N')");
		  }
		 if(empNumber!=null && !("").equalsIgnoreCase(empNumber))
		  {
			query.append(" and u.userNo like ?");
			i++;
		
		  }
		  if(loginName!=null && !("").equalsIgnoreCase(loginName))
		  {
			query.append(" and u.loginName like ?");
			i++;
			
		  }
		  
		  if(!lStrScheme.equalsIgnoreCase("CD203"))
			{
			  query.append(" and u.userType = ?");
				i++;
			}
		  else
		  {
			  query.append(" and (u.userType ='CD201' or u.userType ='CD202' or u.userType ='CD203')");
		  }
		String[] args= new String[i];
		i=0;

		if(empNumber!=null && !("").equalsIgnoreCase(empNumber))
		  {
			args[i] = ("%").concat(empNumber.concat("%"));
			i++;
			
		  }
		
		  if(loginName!=null && !("").equalsIgnoreCase(loginName))
		  {
			 args[i]=("%").concat(loginName.toUpperCase().concat("%"));
			i++;
			
		  }
		  if(!lStrScheme.equalsIgnoreCase("CD203"))
		  {
			 args[i]=lStrScheme;
			i++;
		  }
		 
		  empList=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);
		 
		return empList;
	}

	@Override
	@Transactional
	public String allocateEmployee(CreateEmployeeVO createEmpVo) {
		EhfmWorkAllocation ehfmWrkAllot = null;
		EhfmUsersUnitDtls ehfmUsrUnitDtls=null;
		EhfmUsers ehfmUsers = null;
		EhfmUsrGrpsMpg ehfmUsrGrpMpg=null;
		String result = "";
		String userIdPresent="";
		List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
		
		ehfmWrkAllot =genericDao.findById(EhfmWorkAllocation.class, EhfmWorkAllocationId.class,new EhfmWorkAllocationId(createEmpVo.getUserRole(),createEmpVo.getDeptId()));
		if(ehfmWrkAllot!=null){
		ehfmWrkAllot.setLstUpdDt(new Timestamp(new Date().getTime()));
		ehfmWrkAllot.setLstUpdUsr(createEmpVo.getCrtUsr());
		ehfmWrkAllot.setPrimaryFlag("Y");
		
		if(createEmpVo.getUnAllocFlag().equalsIgnoreCase("YES"))
		{
			ehfmWrkAllot.setUserCode("VACANT");
			ehfmWrkAllot.setUserName("VACANT");
			ehfmWrkAllot.setUserLoginId("VACANT");
			ehfmWrkAllot.setAllocationFlag("N");
			
		}
		else
		{
		
		ehfmWrkAllot.setUserDsgnId(createEmpVo.getDesgId());
		ehfmWrkAllot.setUserCode((createEmpVo.getUserNo()).toUpperCase());
		ehfmWrkAllot.setUserName((createEmpVo.getfName()).toUpperCase());
		ehfmWrkAllot.setUserLoginId((createEmpVo.getLoginName()).toUpperCase());
		ehfmWrkAllot.setAllocationFlag("Y");
		}
        try {
			ehfmWrkAllot = genericDao.save(ehfmWrkAllot);
			result = "success";
			} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
				}
		}
		
		if (result.equalsIgnoreCase("success")) {
			
			criteriaList.add ( new GenericDAOQueryCriteria ( "loginName", createEmpVo.getLoginName().toUpperCase(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
	        List<EhfmUsers> userDtls=genericDao.findAllByCriteria(EhfmUsers.class,criteriaList);
	        criteriaList.removeAll(criteriaList);
	        if(userDtls.size()>0)
	        {
	        userIdPresent =userDtls.get(0).getUserId();
	        ehfmUsers =genericDao.findById(EhfmUsers.class, String.class,userIdPresent);
	        }
	        if(ehfmUsers!=null && ehfmUsers.getServiceFlag().equalsIgnoreCase("N")){
	        	  ehfmUsers.setLstUpdDt(new Timestamp(new Date().getTime()));
			      ehfmUsers.setLstUpdUser(createEmpVo.getCrtUsr());
			      ehfmUsers.setServiceFlag("Y"); 
			      ehfmUsers.setServiceExpiryDt(null);
					ehfmUsers.setPeriodTo(null);
					ehfmUsers.setPeriodFrom(new Timestamp(new Date().getTime()));
			      
	        }
			
		}
		
		if(result.equalsIgnoreCase("success"))
		{
		        criteriaList.add ( new GenericDAOQueryCriteria ( "unitName", createEmpVo.getUnitName(),GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
		        List<EhfmUnits> unitDtls=genericDao.findAllByCriteria(EhfmUnits.class,criteriaList);
		        criteriaList.removeAll(criteriaList);
		        String unitId="";
		        if(unitDtls.size()>0)
		        {
		        	 unitId=unitDtls.get(0).getUnitId();
		        }
				EhfmUsersUnitDtlsId id=new EhfmUsersUnitDtlsId();
				
				if(!createEmpVo.getUnAllocFlag().equalsIgnoreCase("YES"))
				{
				id.setUnitId(unitId);
				id.setUserId(userIdPresent);
				id.setStartDt(new Timestamp(new Date().getTime()));
				ehfmUsrUnitDtls = new EhfmUsersUnitDtls();
				ehfmUsrUnitDtls.setEhfmUserUnitDtlsId(id);
				ehfmUsrUnitDtls.setCrtDt(new Timestamp(new Date().getTime()));
				ehfmUsrUnitDtls.setCrtUsr(createEmpVo.getCrtUsr());
				ehfmUsrUnitDtls.setLangId(createEmpVo.getLangId());
				ehfmUsrUnitDtls.setLocId(createEmpVo.getLocId());
				}
				else
				{
					
					 criteriaList.add ( new GenericDAOQueryCriteria ( "id.unitId", unitId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
			         criteriaList.add ( new GenericDAOQueryCriteria ( "id.userId", userIdPresent,GenericDAOQueryCriteria.CriteriaOperator.EQUALS ) ) ;
				        List<EhfmUsersUnitDtls> userUnitDtls=genericDao.findAllByCriteria(EhfmUsersUnitDtls.class,criteriaList);
				        criteriaList.removeAll(criteriaList);
			         
				        if(userUnitDtls.size()>0)
				        {
				        	ehfmUsrUnitDtls=userUnitDtls.get(0);
				        }
			         
			         if(ehfmUsrUnitDtls!=null){
					
			        	 ehfmUsrUnitDtls.setEndDt(new Timestamp(new Date().getTime()));
			        	 ehfmUsrUnitDtls.setLstUpdDt(new Timestamp(new Date().getTime()));
			        	 ehfmUsrUnitDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
			}
				}
			try {
				ehfmUsrUnitDtls = genericDao.save(ehfmUsrUnitDtls);
				result = "success";
				} catch (Exception e) {
				e.printStackTrace();
				result = "failure";
					}
			}
		
		if (result.equalsIgnoreCase("success")) {
			if(!createEmpVo.getUnAllocFlag().equalsIgnoreCase("YES"))
			{

				List<LabelBean> listOfGrps=getGroupList(createEmpVo.getUnitId());
				for(int j=0;j<listOfGrps.size();j++)
				{
				EhfmUsrGrpsMpgId id=new EhfmUsrGrpsMpgId();
				id.setGrpId(listOfGrps.get(j).getGrpId());
				id.setUsergrpId(userIdPresent);
				id.setLangId(createEmpVo.getLangId());;
				ehfmUsrGrpMpg=new EhfmUsrGrpsMpg();
				ehfmUsrGrpMpg.setId(id);
				ehfmUsrGrpMpg.setCrtDt(new Timestamp(new Date().getTime()));
				ehfmUsrGrpMpg.setCrtUsr(createEmpVo.getCrtUsr());
				ehfmUsrGrpMpg.setFlag("G");
				ehfmUsrGrpMpg.setLocId(createEmpVo.getLocId());
			
			
		
		try {
			ehfmUsrGrpMpg = genericDao.save(ehfmUsrGrpMpg);
			result = "success";
			} catch (Exception e) {
			e.printStackTrace();
			result = "failure";
				}
			}
			}
		
      }
		
		return result;
	
	}
	@Override
	public List<LabelBean> getAllocatedDeptsList(String loginName) {
		List<LabelBean> allocatedDeptList = null;
		StringBuffer query = new StringBuffer();
		String[] args=new String[1];
		args[0]=loginName.toUpperCase();
		query.append("SELECT distinct(w.id.userDeptId) as ID, d.deptName as VALUE FROM EhfmWorkAllocation w,EhfmDepts d where d.deptType='CD201' and w.id.userDeptId=d.id.deptId and w.userLoginId=? and w.allocationFlag='Y' order by d.deptName");
		allocatedDeptList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);

			return allocatedDeptList;
		
	}

	@Override
	public List<LabelBean> getAlloPosDetails(String dept, String loginName) {
		List<LabelBean> allocatedPosList = null;
		StringBuffer query = new StringBuffer();
		String[] args=new String[2];
		args[0]=loginName.toUpperCase();
		args[1]=dept;
		query.append("SELECT w.id.userRole as ID,w.id.userRole as VALUE FROM EhfmWorkAllocation w where w.userLoginId=? and w.id.userDeptId=? and w.allocationFlag='Y'");
		allocatedPosList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		if(allocatedPosList.size()>0)
			return allocatedPosList;
		else
			return null;
	}

	@Override
	public LabelBean getallocEmpPosDtls(String dept, String empPos) {
		String args[] = new String[2];
        args[1]=dept;
        args[0]=empPos;
        List<LabelBean> empPosDtls = null;
        StringBuffer query = new StringBuffer();
		query.append("SELECT w.reptRole as reptRole FROM EhfmWorkAllocation w where w.id.userRole=? and w.id.userDeptId=? ");
		empPosDtls = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		if(empPosDtls.size()>0)
			return empPosDtls.get(0);
		else
			return null;
        
		
	}

	@Override
	public List<LabelBean> getDesignationList(String deptName) {
		
		StringBuffer query = new StringBuffer();
		List<LabelBean> desgList=null;
		String args[] = new String[1];
		args[0]=deptName;
		query.append("SELECT distinct(g.id.dsgnId) as ID,d.dsgnName as VALUE  from " +
					"EhfmDesignation d,EhfmDsgGrpMpg g where g.id.deptId=? and g.id.dsgnId=d.id.dsgnId and d.dsgnStatus='A' order by d.dsgnName ");
		desgList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		return desgList;
	}

	@Override
	public List<LabelBean> getGroupsList(String desgName,String deptName) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> grpList=null;
		String args[] = new String[2];
		args[0]=desgName;
		args[1]=deptName;
		query.append("SELECT distinct(g.id.grpId) as ID,eg.grpName as VALUE,g.level as LVL  from " +
					"EhfmGrps eg,EhfmDsgGrpMpg g where g.id.dsgnId=? and g.id.deptId=? and g.id.grpId=eg.grpId order by eg.grpName ");
		grpList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		return grpList;
		
	}

	@Override
	public List<LabelBean> getGroupList(String unitId) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> grpList=null;
		String args[] = new String[1];
		args[0]=unitId;
		
		query.append("select ug.id.grpId as grpId,g.grpName as grpName from EhfmUnits u,EhfmUnitsGroupMpg ug,EhfmGrps g " +
				"where ug.id.unitId=u.id.unitId and g.id.grpId=ug.id.grpId and ug.id.unitId=? order by g.grpName");
		grpList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		return grpList;
		
	}

	@Override
	public String saveDesgDetails(CreateEmployeeVO createEmpVO) {
		EhfmDesignation ehfmDesignation=null;
		EhfmDesignationId ehfmDsgnId=null;
		String msg="";
		ehfmDesignation=genericDao.findById(EhfmDesignation.class,EhfmDesignationId.class,new EhfmDesignationId(createEmpVO.getDesignationId(),"en_US"));
		if(ehfmDesignation==null)
		{
			ehfmDesignation=new EhfmDesignation();
			ehfmDsgnId=new EhfmDesignationId();
			ehfmDsgnId.setDsgnId(createEmpVO.getDesignationId());
			ehfmDsgnId.setLangId("en_US");
			ehfmDesignation.setLocId("LC1");
			ehfmDesignation.setId(ehfmDsgnId);
			ehfmDesignation.setCrtDt(new Timestamp(new Date().getTime()));
			ehfmDesignation.setCrtUsr(createEmpVO.getCrtUsr());
		}
		else
		{
			ehfmDesignation.setLstUpdDt(new Timestamp(new Date().getTime()));
			ehfmDesignation.setLstUpdUsr(createEmpVO.getCrtUsr());
			
		}
			ehfmDesignation.setDsgnName(createEmpVO.getDesignationName().toUpperCase());
			ehfmDesignation.setDsgnShortName(createEmpVO.getDesignationSname().toUpperCase());
			ehfmDesignation.setDsgnStatus(createEmpVO.getDesgStatus());
			
			try
			{
			ehfmDesignation=genericDao.save(ehfmDesignation);
			msg="success";
			}
			catch(Exception e)
			{
				logger.error("Exception in method saveDesgDetails of createEmpServiceImpl->"
						+ e.getMessage());
				e.printStackTrace();
				msg="failure";
			}
		
		return msg;
	}
*/
	/**
     * @return String lStrDesgSeq
     * @function This method is used to get the sequence next value of Designation sequence.
     */
public	List<LabelBean> getReferredCenterDtls(String lStrUserId, String roleId){

		List<LabelBean> hospitalList = new ArrayList<LabelBean>();
		SessionFactory factory=null;
		Session session=null;
		
	try
	{
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		StringBuffer query=null;
		 query = new StringBuffer();
		StringBuffer query1=new StringBuffer();
		Query hQuery=null;
		List hospList=null;
		ArrayList<String> hospIdList=null;
		Iterator hospItr=null;
		List resultList=null;
		Iterator resultItr=null;
	 
		 query.append("from EhfmDispUsrMpg dg where dg.id.userId='"+lStrUserId+"' and dg.id.activeyn ='Y' and dg.id.grpId='"+roleId+"' ");

			hQuery=session.createQuery(query.toString());
			hospList=hQuery.list();
			if(hospList.size()>0)
			{
				hospIdList=new ArrayList();
				hospItr=hospList.iterator();
				while(hospItr.hasNext())
				{
					EhfmDispUsrMpg ehfmDispUsrMpgObj=(EhfmDispUsrMpg)hospItr.next();
					EhfmDispUsrMpgId ehfmDispUsrMpgId=ehfmDispUsrMpgObj.getId();
					GLOGGER.info("hospIds "+ehfmDispUsrMpgId.getDispId());
					hospIdList.add(ehfmDispUsrMpgId.getDispId());
				}
			}
			else
			{
				GLOGGER.info("No associated hosp for this user");
			}
	  
	  
	/*  else if(ClaimsConstants.REFDOC.equalsIgnoreCase(roleId))
	  {
		  query.append("from EhfmDispUsrMpg dg where dg.id.userId='"+lStrUserId+"' and dg.id.activeyn ='Y' and dg.id.grpId='"+ClaimsConstants.REFDOC+"' ");

		hQuery=session.createQuery(query.toString());
		hospList=hQuery.list();
		if(hospList.size()>0)
		{
			hospIdList=new ArrayList();
			hospItr=hospList.iterator();
			while(hospItr.hasNext())
			{
				EhfmDispUsrMpg ehfmDispUsrMpgObj=(EhfmDispUsrMpg)hospItr.next();
				EhfmDispUsrMpgId ehfmDispUsrMpgId=ehfmDispUsrMpgObj.getId();
				GLOGGER.info("hospIds "+ehfmDispUsrMpgId.getDispId());
				hospIdList.add(ehfmDispUsrMpgId.getDispId());
			}
		}
		else
		{
			GLOGGER.info("No associated hosp for this user");
		}
	  }*/
	 
	 if(hospList!=null)
		{
    		
		 query1.append("from EhfmDispensaryDtls ah where ah.id.dispId in (:param) ");
			Query hQuery1=session.createQuery(query1.toString());
			hQuery1.setParameterList("param", hospIdList);
			resultList=hQuery1.list();
			if(resultList.size()>0)
				GLOGGER.info("Hosp details retrieved");
			resultItr=resultList.iterator();
			while(resultItr.hasNext())
			{
				EhfmDispensaryDtls ehfmHospitalsObj=(EhfmDispensaryDtls)resultItr.next();
				LabelBean labelBean=new LabelBean();
				labelBean.setID(ehfmHospitalsObj.getDispId());
				labelBean.setVALUE(ehfmHospitalsObj.getDispName()+","+ehfmHospitalsObj.getDispCity());  
				labelBean.setNATURE(ehfmHospitalsObj.getDispType()+"");
				labelBean.setHospActiveYn(ehfmHospitalsObj.getDispActiveyn());
				hospitalList.add(labelBean);
			}
		 
			
		}   	
}
	catch(Exception e)
	{
		e.printStackTrace();
//		GLOGGER.error("Exception Occurred in getReferredCenterDtls() in PatientDaoImpl class."+e.getMessage());
	}
	finally
	{
		session.close();
		factory.close();
	}
		return hospitalList;

	}
	@Override
	public String getDesignationSeq() {
		
		 Number lStrSeqRetVal = 0L;
			try{
		     
		    	StringBuffer query = new StringBuffer();
		        query = new StringBuffer();
		    	query.append(" SELECT ehfm_dsgn_master_sequence.NEXTVAL COUNT FROM DUAL ");
		        List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class,query.toString());

		        if(seqList != null){
		          lStrSeqRetVal = seqList.get(0).getCOUNT();
		        }
		    }catch(Exception e){
		    	
		    	e.printStackTrace();
		    	
		    }
		 return "D"+lStrSeqRetVal;
		
	}

	@Override
	public List<CreateEmployeeVO> searchDesg(CreateEmployeeVO createEmpVO) 
	{
		StringBuffer query = new StringBuffer();
		
		List<CreateEmployeeVO> searchDesgDtls=null;
		
      query.append("select d.id.dsgnId as designationId,d.dsgnName as designationName,d.dsgnShortName as designationSname,d.dsgnStatus as desgStatus from EhfmDesignation d where d.id.langId='en_US' ");
		
		if(createEmpVO.getDesignationId()!=null && createEmpVO.getDesignationId()!="" && !createEmpVO.getDesignationId().equals("")){
			query.append(" and d.id.dsgnId like ('%"+createEmpVO.getDesignationId().toUpperCase()+"%') ");
			
		}
		if(createEmpVO.getDesignationName()!=null && createEmpVO.getDesignationName()!="" && !createEmpVO.getDesignationName().equals("")){
			query.append(" and d.dsgnName like ('%"+createEmpVO.getDesignationName().toUpperCase()+"%') ");
			
		}
		if(createEmpVO.getDesignationSname()!=null && createEmpVO.getDesignationSname()!="" && !createEmpVO.getDesignationSname().equals("")){
			
			query.append(" and d.dsgnShortName like  ('%"+createEmpVO.getDesignationSname().toUpperCase()+"%') ");
			
		}
		
		searchDesgDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString());
		return searchDesgDtls;
	}

	@Override
	public CreateEmployeeVO loadDesgDtls(String designationId) 
	{
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
		args[0]=designationId;
		List<CreateEmployeeVO> loadDesgDtls=null;
		query.append("select d.id.dsgnId as designationId,d.dsgnName as designationName,d.dsgnShortName as designationSname,d.dsgnStatus as desgStatus from EhfmDesignation d where d.id.dsgnId=? ");
		loadDesgDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
		return loadDesgDtls.get(0);
	}




	
	// To get the district list based on the New and Old state selected
	@Override
	public List<LabelBean> getNewDistrictList(String state,String stateType) {
		StringBuffer query = new StringBuffer();
		List<LabelBean> distList=null;
		String args[] = new String[2];
		args[0]=state;
		args[1]= "LH6";
		
		if(stateType!=null && stateType.equalsIgnoreCase("O"))
		{
			query.append("SELECT id.locId as LOCID,locName as DISTRICTNAME  from " +
					"EhfmLocations where id.locParntId=? and locHdrId=? and activeYn='N' order by locName ");
		
		}
		else
		{
			query.append("SELECT id.locId as LOCID,locName as DISTRICTNAME  from " +
					"EhfmLocations where id.locParntId=? and locHdrId=? and activeYn='Y' order by locName ");
			
		}
		distList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		return distList;
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
	public List<LabelBean> getBankCode(String ifscCode){
      	 
      	 StringBuffer query = new StringBuffer();
      	List<LabelBean> lStrBankCode=null;
      	 String args[] = new String[1];
      	 args[0] = ifscCode;
      	 query.append("select bankId as ID,bankName as VALUE ,bankBranch as branch from EhfmBankMaster where ifcCode = ?");
      	 List<LabelBean> lStrList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
      	 if(lStrList != null)
      		lStrBankCode = lStrList;
          return lStrBankCode;	 
        }

	@Override
	public String saveInvstDtls(CreateEmployeeVO createEmpVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUnitSeqId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkEmpNo(String userId, String editFlg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkLoginName(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkVacantPosition(String dep, String vacPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUserSeqId() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	@Transactional
	public String saveCreateEmpDtls(CreateEmployeeVO createEmpVo) {
	
		                  String result = "";
		                  EhfmUsers ehfmUsers = null;
		                  String userId = "";
                          String userType="";
                          String Prefix="";
		                      List<GenericDAOQueryCriteria> criteriaList = new ArrayList<GenericDAOQueryCriteria> () ; 
		
								EhfmEmployeeMaster ehfmEmployeeMaster=null;
								EhfmDispensaryDtls ehfmdispensries=null;
								ehfmdispensries =genericDao.findById(EhfmDispensaryDtls.class, String.class,createEmpVo.getDeptId());
                          if(ehfmdispensries!=null){
                        	  
          					
                        	    Prefix=ehfmdispensries.getDispCode().replace("DISP-", "");
                        	    if(createEmpVo.getLoginName()!=null&&!createEmpVo.getLoginName().equalsIgnoreCase(""))
                        	    	userId=createEmpVo.getLoginName();
                        	    
								ehfmEmployeeMaster =genericDao.findById(EhfmEmployeeMaster.class, String.class,userId);
								if(ehfmEmployeeMaster!=null){
									ehfmEmployeeMaster.setLstUpdUsr(createEmpVo.getCrtUsr());
									ehfmEmployeeMaster.setLstUpdDt(new Timestamp(new Date().getTime()));
									ehfmEmployeeMaster.setInService(createEmpVo.getServiceFlg());

								}
								else
								{									
									ehfmEmployeeMaster=new EhfmEmployeeMaster();
									 userId=Prefix+getNextseq(Prefix).toString();
									 if(userId!="")
										 ehfmEmployeeMaster.setUserId(userId);
											else
												ehfmEmployeeMaster.setUserId(userId);
									ehfmEmployeeMaster.setUserCode(ehfmdispensries.getDispCode());
/*									ehfmEmployeeMaster.setAppointmentDate(new Timestamp(new Date().getTime()));
*/									ehfmEmployeeMaster.setCrtDt(new Timestamp(new Date().getTime()));
									ehfmEmployeeMaster.setCrtUsr(createEmpVo.getCrtUsr());								
									ehfmEmployeeMaster.setInService("Y");

								}
								
								ehfmEmployeeMaster.setAddress(createEmpVo.getAddr());
								ehfmEmployeeMaster.setBirthDate(createEmpVo.getDob());
							
								ehfmEmployeeMaster.setDesigId(createEmpVo.getDesgId());
								ehfmEmployeeMaster.setEmailId(createEmpVo.getEmail());
								if(createEmpVo.getfName()!=null && !createEmpVo.getfName().equalsIgnoreCase(""))
								ehfmEmployeeMaster.setFirstName(createEmpVo.getfName().toUpperCase());
								ehfmEmployeeMaster.setGender(createEmpVo.getGender());
				
								ehfmEmployeeMaster.setJoinDate(createEmpVo.getDoj());
								if(createEmpVo.getlName()!=null && !createEmpVo.getlName().equalsIgnoreCase(""))
								ehfmEmployeeMaster.setLastName(createEmpVo.getlName().toUpperCase());
								ehfmEmployeeMaster.setMobileNo(createEmpVo.getMobileNo());
								ehfmEmployeeMaster.setPanNo(createEmpVo.getPanNumber());
								ehfmEmployeeMaster.setSchemeId(userType);
								ehfmEmployeeMaster.setDispcode(ehfmdispensries.getDispCode());
								ehfmEmployeeMaster.setAddrMandMunci(createEmpVo.getAddrMandMunci());
								ehfmEmployeeMaster.setMandMunci(createEmpVo.getMandMunci());
								ehfmEmployeeMaster.setCity(createEmpVo.getCity());
								ehfmEmployeeMaster.setDistrict(createEmpVo.getDist());
								ehfmEmployeeMaster.setState(createEmpVo.getState());
								ehfmEmployeeMaster.setCountry(createEmpVo.getCountry());
								ehfmEmployeeMaster.setPin(createEmpVo.getPin());
								ehfmEmployeeMaster.setSchemeId("CD202");
								
								
								 try {
									 ehfmEmployeeMaster = genericDao.save(ehfmEmployeeMaster);
										result = "Success";
										} catch (Exception e) {
										e.printStackTrace();
										result = "failure";
										result ="Could Not create Employee";
									}
		/*					if (result!=null&&!result.equalsIgnoreCase("")&&result.equalsIgnoreCase("success")) {
										
										if(createEmpVo.getAccNumber()!=null && !"null".equalsIgnoreCase(createEmpVo.getAccNumber()) && !"".equals(createEmpVo.getAccNumber())	){
											EhfmEmpDispAcctDtls ehfmEmpAcctDtls;
										
										ehfmEmpAcctDtls =genericDao.findById(EhfmEmpDispAcctDtls.class, EhfmEmpDispAcctDtlsId.class,new EhfmEmpDispAcctDtlsId(userId,createEmpVo.getAccNumber()));
										if(ehfmEmpAcctDtls==null){
											EhfmEmpDispAcctDtlsId id=new EhfmEmpDispAcctDtlsId();
											if(userId!="")
											id.setUserID(userId);
											else
												id.setUserID(userId);
											id.setAccountNo(createEmpVo.getAccNumber());
											ehfmEmpAcctDtls = new EhfmEmpDispAcctDtls();
											ehfmEmpAcctDtls.setId(id);
											ehfmEmpAcctDtls.setCrtDt(new Timestamp(new Date().getTime()));
											ehfmEmpAcctDtls.setCrtUsr(createEmpVo.getCrtUsr());
											
										}
										else
										{
											
											ehfmEmpAcctDtls.setLstUpdDt(new Timestamp(new Date().getTime()));
											ehfmEmpAcctDtls.setLstUpdUsr(createEmpVo.getCrtUsr());
										}
									
											ehfmEmpAcctDtls.setBankId(createEmpVo.getBankName());
											ehfmEmpAcctDtls.setNameAsPerAcc(createEmpVo.getAccHolderName().toUpperCase());
											ehfmEmpAcctDtls.setPanHolderName(createEmpVo.getPanName().toUpperCase());
											ehfmEmpAcctDtls.setPanNumber(createEmpVo.getPanNumber().toUpperCase());
											if(createEmpVo.getEditFlg()!=null && createEmpVo.getServiceFlg().equalsIgnoreCase("N"))
											{
												ehfmEmpAcctDtls.setActiveYn("N");
											}
											else
											ehfmEmpAcctDtls.setActiveYn("Y");
										     
								        try {
								        	ehfmEmpAcctDtls = genericDao.save(ehfmEmpAcctDtls);
											result = "Success";
											} catch (Exception e) {
											e.printStackTrace();
											result = "failure";
											result ="Could Not create Employee";
										}
												 
								 
                          }
					}*/
                 }
					System.out.println(result+"~"+userId);
		return result+"~"+userId;
	
	}
	
	private String getNextseq(String prefix) {
		String lStrSeqRetVal = "";

		try {

			StringBuffer query = new StringBuffer();
			query.append(" SELECT " + prefix + "_DISP_EMP_SEQ.NEXTVAL NEXTVAL  FROM DUAL ");
			List<LabelBean> seqList = genericDao.executeSQLQueryList(LabelBean.class, query.toString());

			if (seqList != null) {

				lStrSeqRetVal = seqList.get(0).getNEXTVAL().toString();
			}
		} catch (Exception e) {

			e.printStackTrace();
			GLOGGER.error("Exception Occurred in getSequenceOracle() in PatientRegDaoImpl class." + e.getMessage());

		}

		return lStrSeqRetVal;
	}

	@Override
	public List<LabelBean> getVacantList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getLocationList(String dist, String distId,
			String lStrHdrId) {
		List<LabelBean> locList = null;
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		//args[0] = dist;
		args[1]=distId;
		args[0]=lStrHdrId;
		if (dist != null && !"".equalsIgnoreCase(dist)) {
		query.append("select al.id.locId as ID, al.locName as VALUE from EhfmLocations al where al.locHdrId = ? and al.id.locParntId=? and al.activeYn = 'Y' order by al.id.locId ");
		locList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		else{
		query.append("select al.id.locId as ID, al.locName as VALUE from EhfmLocations al where al.locHdrId = ? and al.id.locParntId=? and al.activeYn = 'Y' order by al.id.locId ");
		locList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
		}
		return locList;
	}

	@Override
	public List<LabelBean> searchEmp(CreateEmployeeVO createEmpVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CreateEmployeeVO searchEmployeeList(CreateEmployeeVO createEmpVo) {

		SessionFactory factory=null;
		Session session=null;
		List<CreateEmployeeVO> empList= new ArrayList<CreateEmployeeVO>();
		
	    
		factory= hibernateTemplate.getSessionFactory();
		session=factory.openSession();
		StringBuffer searchQuery = new StringBuffer();
		StringBuffer countQuery = new StringBuffer();
		try
		{   
		
		    countQuery.append("select count(distinct u.userId) as totValue from EhfmEmployeeMaster u ,EhfmDispensaryDtls w ,EhfmDispDesignation d,EhfmDispUsrMpg m where w.dispCode=u.userCode and d.id.dsgnId=u.desigId and m.id.dispId=w.id.dispId and m.id.userId='"+createEmpVo.getUserId()+"' ");
		
		
		if(createEmpVo.getLoginName()!=null&& createEmpVo.getLoginName()!="" && !createEmpVo.getLoginName().equals("")){
			countQuery.append(" and u.userId = '"+createEmpVo.getLoginName()+"' "); 
		}
		if(createEmpVo.getfName()!=null&&createEmpVo.getfName()!=""){
/*			countQuery.append(" and (u.firstName like '"+("%").concat(createEmpVo.getfName().concat("%"))+"' or u.lastName like '"+("%").concat(createEmpVo.getfName().concat("%"))+"') ");
*/			countQuery.append("and  u.firstName||u.lastName like '%"+createEmpVo.getfName().toUpperCase()+"%')");

		}
		if(createEmpVo.getStatus()!=null&& createEmpVo.getStatus()!="" && !createEmpVo.getStatus().equalsIgnoreCase("null")){
			countQuery.append(" and u.inService='"+createEmpVo.getStatus()+"' ");
		}
		if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && !createEmpVo.getDesgId().equalsIgnoreCase("null")){
			countQuery.append(" and d.id.dsgnId='"+createEmpVo.getDesgId()+"' ");
		}
	/*	if(createEmpVo.getStart_index()==0 && createEmpVo.getEnd_index()==0)
			empList =session.createQuery(countQuery.toString()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
		else
			*/empList =session.createQuery(countQuery.toString()).setFirstResult(createEmpVo.getStart_index()).setMaxResults(createEmpVo.getEnd_index()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
		createEmpVo.setEmpSearchList(empList);

		searchQuery.append("select distinct(u.userId) as loginName,u.firstName as fName,u.lastName as lName,u.mobileNo as mobileNo,u.emailId as email, d.dsgnName as desgName,u.inService as serviceFlg,u.crtDt as startDt from EhfmEmployeeMaster u ,EhfmDispensaryDtls w ,EhfmDispDesignation d,EhfmDispUsrMpg m where w.dispCode=u.userCode and d.id.dsgnId=u.desigId  and m.id.dispId=w.id.dispId and m.id.userId='"+createEmpVo.getUserId()+"' ");

		if(createEmpVo.getLoginName()!=null&& createEmpVo.getLoginName()!="" && !createEmpVo.getLoginName().equals("")){
			searchQuery.append(" and u.userId = '"+createEmpVo.getLoginName().toUpperCase()+"' "); 
		}
		if(createEmpVo.getfName()!=null&&createEmpVo.getfName()!=""){
/*			searchQuery.append(" and (u.firstName like '"+("%").concat(createEmpVo.getfName().concat("%"))+"' or u.lastName like '"+("%").concat(createEmpVo.getfName().concat("%"))+"') ");
*/		searchQuery.append("and  u.firstName||u.lastName like '%"+createEmpVo.getfName().toUpperCase()+"%')");
			}
		if(createEmpVo.getStatus()!=null&& createEmpVo.getStatus()!="" && !createEmpVo.getStatus().equalsIgnoreCase("null")){
			searchQuery.append(" and u.inService='"+createEmpVo.getStatus()+"' ");
		}
		if(createEmpVo.getDesgId()!=null && createEmpVo.getDesgId()!="" && !createEmpVo.getDesgId().equalsIgnoreCase("null")){
			searchQuery.append(" and d.id.dsgnId='"+createEmpVo.getDesgId()+"' ");
		}
		searchQuery.append("order by u.crtDt asc ");
		
		Long count = (Long) session.createQuery(countQuery.toString()).setProperties(createEmpVo).uniqueResult();

	   empList =session.createQuery(searchQuery.toString()).setFirstResult(createEmpVo.getStart_index()).setMaxResults(createEmpVo.getEnd_index()).setResultTransformer(Transformers.aliasToBean(CreateEmployeeVO.class)).list();
		
		
		createEmpVo.setEmpSearchList(empList);
		createEmpVo.setTotRowCount(count.intValue());
		
	} catch (Exception e) {
		
		e.printStackTrace();
	}
	finally
	{
	   session.close();
	   factory.close();
	   
	}
		return createEmpVo;
		
		}

	@Override
	public CreateEmployeeVO loadEmpDtls(String empCode) {
		List<CreateEmployeeVO> loadEmpDtls=null;

		/*
		StringBuffer query = new StringBuffer();
		String args[] = new String[1];
		args[0]=userNo;
		List<CreateEmployeeVO> loadEmpDtls=null;
		query.append("SELECT u.firstName as fName,u.lastName as lName " +
				" as lName,u.doj as doj,d.dob as dob,d.gender as gender,u.emailId  " +
				" as email,d.mandMunci as mandMunci,d.mandMunci1 as mandMunci1, " +
				" d.addrMandMunci as addrMandMunci,d.city" +
				" as city,d.district as dist,,d.state " +
				" as state,d.country as country,d.pin " +
				" as pin,d.phoneNo as mobileNo,u.serviceFlag as serviceFlg" +
				"and w.id.userRole=un.unitName and g.id.usergrpId=u.userId ");
		
	query.append("select u.firstName as fName,u.lastName as lName,u.joinDate as doj,u.mobileNo as mobileNo,u.emailId as email,u.address as addr,  d.dsgnName as desgName,u.inService as serviceFlg from EhfmEmployeeMaster u ,EhfmDispensaryDtls w ,EhfmDispDesignation d where w.dispCode=u.userCode and d.id.dsgnId=u.desigId");
		loadEmpDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
		if(loadEmpDtls.size()>0)
			return loadEmpDtls.get(0);
		else
			return null;
	
	*/
		StringBuffer query=new StringBuffer();
		String[] args=new String[1];
		args[0]=empCode.toUpperCase();
		query.append("select distinct(u.userId) as loginName,u.firstName as fName,u.lastName as lName,u.country as country,u.pin as pin ,u.city as city,u.state as state,u.district as dist,u.addrMandMunci as addrMandMunci,u.mandMunci as mandMunci,u.mobileNo as mobileNo,u.emailId as email, d.id.dsgnId as desgName,u.inService as serviceFlg,u.birthDate as dob,u.joinDate as doj,u.address as addr,u.gender as gender  from EhfmEmployeeMaster u ,EhfmDispensaryDtls w ,EhfmDispDesignation d where w.dispCode=u.userCode and d.id.dsgnId=u.desigId ");
		//query.append(" and u.inService='Y'");
		query.append(" and u.userId=?");
		
		loadEmpDtls=genericDao.executeHQLQueryList(CreateEmployeeVO.class, query.toString(),args);
		if(loadEmpDtls.size()>0)
			return loadEmpDtls.get(0);
		else
			return null;
	}

	@Override
	public List<LabelBean> getHospList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> searchLogin(CreateEmployeeVO createEmpVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreateEmployeeVO> getHospsMapped(String userId, String grpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getHospsNotMapped(String userId, String grpId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> searchHospsList(String searchField) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveMapping(List<CreateEmployeeVO> newList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteMappingDtls(List<CreateEmployeeVO> newList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkUnitName(String unitName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabelBean getVacPosDtls(String dept, String vacPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReportingPerName(String repPersonId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
 public String getIFSCCode(String lStrBankCode){
      	 
      	 StringBuffer query = new StringBuffer();
      	 String lStrIFSCCode = "";
      	 String args[] = new String[1];
      	 args[0] = lStrBankCode;
      	 query.append("select ifcCode as VALUE from EhfmBankMaster where bankId = ?");
      	 List<LabelBean> lStrList = genericDao.executeHQLQueryList(LabelBean.class,query.toString(),args);
      	 if(lStrList != null)
      		 lStrIFSCCode = lStrList.get(0).getVALUE();
          return lStrIFSCCode;	 
        }

	@Override
	 public List<LabelBean> getBankNames() {
	        
	        StringBuffer query = new StringBuffer();
	        query.append(" SELECT bankId as ID , (bankName ||' - '|| bankBranch) as VALUE FROM EhfmBankMaster order by bankName");
	        List<LabelBean> bankList =
	            genericDao.executeHQLQueryList(LabelBean.class,query.toString());
	            return bankList;
	    }

	@Override
	public CreateEmployeeVO loadEmpBankDtls(String userNo) {
			StringBuffer query = new StringBuffer();
			String args[] = new String[1];
			args[0]=userNo;
			List<CreateEmployeeVO> loadEmpBankDtls=null;
			
			query.append("select h.id.accountNo as accNumber,h.bankId as bankName,h.nameAsPerAcc as accHolderName,h.panNumber as panNumber,h.panHolderName as panName from EhfmEmpDispAcctDtls h where h.id.userID=?");
			loadEmpBankDtls = genericDao.executeHQLQueryList(CreateEmployeeVO.class,query.toString(),args);
			if(loadEmpBankDtls.size()>0)
				return loadEmpBankDtls.get(0);
			else
				return null;
	}
	@Override
	public String decryptPassword(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CreateEmployeeVO> getEmployeeList(String empNumber,
			String loginName, String allocationFlag, String lStrScheme) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String allocateEmployee(CreateEmployeeVO createEmpVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getAllocatedDeptsList(String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getAlloPosDetails(String dept, String loginName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LabelBean getallocEmpPosDtls(String dept, String empPos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getDesignationList(String deptName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getGroupsList(String desgName, String deptName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<LabelBean> getGroupList(String unitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveDesgDetails(CreateEmployeeVO createEmpVO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendBulkEmails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CreateEmployeeVO getEmployeedata(String empId) {
		// TODO Auto-generated method stub
		return null;
	}

	 
}
	

	
	

