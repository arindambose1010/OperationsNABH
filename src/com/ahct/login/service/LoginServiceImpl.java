package com.ahct.login.service;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.ahct.claims.util.ClaimsConstants;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.LdapAuthentication;
import com.ahct.common.util.SMSServices;
import com.ahct.common.util.SendSMS;
import com.ahct.common.vo.LabelBean;
import com.ahct.login.dao.LoginDao;
import com.ahct.login.vo.EmployeeDetailsVO;
import com.ahct.login.vo.LoginDtlsVO;
import com.ahct.login.vo.MenuVo;
import com.ahct.model.EhfContactDtlsAudit;
import com.ahct.model.EhfContactDtlsAuditId;
import com.ahct.model.EhfSmsData;
import com.ahct.model.EhfmDesignation;
import com.ahct.model.EhfmUserLoginDtls;
import com.ahct.model.EhfmUsers;
import com.ahct.model.SgvcPswdAudit;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;



public class LoginServiceImpl implements LoginService{
	String templateId=null;
	CommonService asriCommonService;
	GenericDAO genericDao;
	LoginDao loginDao;
	
	Logger oslogger = Logger.getLogger(ChangePwdRequestServiceImpl.class);
	
	public CommonService getAsriCommonService() {
		return asriCommonService;
	}
	public GenericDAO getGenericDao() {
		return genericDao;
	}
	public LoginDao getLoginDao() {
		return loginDao;
	}
	public static ConfigurationService getConfigurationService() {
		return configurationService;
	}
	public static CompositeConfiguration getConfig() {
		return config;
	}
	public void setAsriCommonService(CommonService asriCommonService) {
		this.asriCommonService = asriCommonService;
	}
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	public void setLoginDao(LoginDao loginDao) {
		this.loginDao = loginDao;
	}
	HibernateTemplate hibernateTemplate ;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;

	static {
		configurationService = ConfigurationService.getInstance(); 
		config = configurationService.getConfiguration();
	}

	public boolean authenticateUser ( String userID, String password ){
		StringBuffer query = new StringBuffer();
		String args[] = new String[2];
		boolean lStrResult = false ;
		SessionFactory factory=hibernateTemplate.getSessionFactory();
	    Session session = factory.openSession();
		try
	      {
			
		if(!(userID==null || "".equalsIgnoreCase(userID) || password==null || "".equalsIgnoreCase(password)))
		{
			StringBuffer hqlQuery = new StringBuffer();
			hqlQuery.append(" select loginName as ID from EhfmUsers where upper(loginName) = ? and DECRYPT_USER_PSWD(?)=? and serviceFlag=? and userType=?  and  serviceExpiryDt is null " );
		 	String[] args1 = new String[5];
		 	userID=userID.toUpperCase();
		 	args1[0] = userID;
			args1[1] = userID;
			args1[2] = password;
			args1[3]="Y";
			args1[4] = config.getString("Scheme.TG.State");
			
			
		 	List<LabelBean>  empDtlsList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);

			 if(empDtlsList != null && empDtlsList.size()>0 )
	          {
	            lStrResult = true;
	          }
	          else
	          {
	        	  lStrResult = false;
	          }
	          
	      }
		else{
			
			lStrResult = false;
		}
	      }
		catch ( Exception ex )
	      {
	       ex.printStackTrace();
			 
	        return false ;
	      }
		finally
		{
			session.close();
		}
		return lStrResult ;
	}
	 /**
     * 
     * @param textPwd
     * @return String
     * @method SHA1
     * @description This method is used to encrypt the raw password of the user
     */
      public static String SHA1(String textPwd) throws NoSuchAlgorithmException, UnsupportedEncodingException  
      {
          MessageDigest md;
          md = MessageDigest.getInstance("SHA-1");
          byte[] sha1hash = new byte[40];
          md.update(textPwd.getBytes("UTF-8"), 0, textPwd.length());
          sha1hash = md.digest();
          return convertToHex(sha1hash);
      }
	 
      private static String convertToHex(byte[] data) 
      {
          StringBuffer buf = new StringBuffer();
          for (int i = 0; i < data.length; i++) 
          {
              int halfbyte = (data[i] >>> 4) & 0x0F;
              int two_halfs = 0;
              do {
                  if ((0 <= halfbyte) && (halfbyte <= 9))
                      buf.append((char) ('0' + halfbyte));
                  else
                      buf.append((char) ('a' + (halfbyte - 10)));
                  halfbyte = data[i] & 0x0F;
              } while(two_halfs++ < 1);
          }
          return buf.toString();
      }
	 
      public List<MenuVo> getRecSubMenuListForEMP(String pStrParntModId,List<String> grpIdList, String pStrUserId, String pStrLangId)
  	{
    	  StringBuffer hqlQuery = new StringBuffer();
    	  List<LabelBean>  hospList=null;
    	  String hospType=null;
    	  String ahcFlag=null,chronicFlag=null;
    	  String blockHospAHC=null,blockHospChr=null;
    	  String[] args1=null;
    	  if(grpIdList!=null)
    	  {
    	  
    	  if(grpIdList.contains("GP1"))
		  {
    		  args1 = new String[2];
        	  args1[0]=pStrUserId;
        	  args1[1]="Y";
			  hqlQuery.append("select cast(ah.hospType as string) as VALUE,ah.ahcFlag as ahcFlag, ");
			  hqlQuery.append(" ah.chronicFlag as chronicFlag from EhfmHospitals ah,");
			  hqlQuery.append(" EhfmHospMithraDtls amu where amu.id.mithraId=? and amu.endDt is null and ah.hospId = amu.id.hospId ");
			  hqlQuery.append(" and amu.activeYN=? ");
			  //hqlQuery.append("  and ah.hospActiveYN='Y'");
			  hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);
		  }
		  else if(grpIdList.contains("GP2"))
		  {
			  args1 = new String[2];
	    	  args1[0]=pStrUserId;
	    	  args1[1]="Y";
			  hqlQuery.append("select cast(ah.hospType as string) as VALUE,ah.ahcFlag as ahcFlag, ");
			  hqlQuery.append(" ah.chronicFlag as chronicFlag from EhfmHospitals ah,");
			  hqlQuery.append(" EhfmMedcoDtls anu where anu.id.medcoId=? and anu.endDate is null and ah.hospId = anu.id.hospId ");
			  hqlQuery.append(" and anu.activeYN=? ");
			  //hqlQuery.append("  and ah.hospActiveYN='Y'");
			  hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);
		  }
		  else{
		  if(grpIdList.contains(ClaimsConstants.DATAOP))
		  {
			  args1 = new String[3];
	    	  args1[0]=pStrUserId;
			  args1[1]=ClaimsConstants.DATAOP;
			  args1[2]="Y";
		  }
    	  
		  else if(grpIdList.contains(ClaimsConstants.REFDOC))
		  {
			  args1 = new String[3];
	    	  args1[0]=pStrUserId;
			  args1[1]=ClaimsConstants.REFDOC;
			  args1[2]="Y";
		  }
		  else if(grpIdList.contains(ClaimsConstants.AYURVEDA_DOC))
		  {
			  args1 = new String[3];
	    	  args1[0]=pStrUserId;
			  args1[1]=ClaimsConstants.AYURVEDA_DOC;
			  args1[2]="Y";
		  }
		  else if(grpIdList.contains(ClaimsConstants.HOMEOPATHY_DOC))
		  {
			  args1 = new String[3];
	    	  args1[0]=pStrUserId;
			  args1[1]=ClaimsConstants.HOMEOPATHY_DOC;
			  args1[2]="Y";
		  }
		  else if(grpIdList.contains(ClaimsConstants.UNANI_DOC))
		  {
			  args1 = new String[3];
	    	  args1[0]=pStrUserId;
			  args1[1]=ClaimsConstants.UNANI_DOC;
			  args1[2]="Y";
			  
		  }
			  hqlQuery.append(" select cast(ee.id.dispId as string) as VALUE ");
			  hqlQuery.append(" from EhfmDispensaryDtls ee, EhfmDispUsrMpg es ");
		  hqlQuery.append(" where es.id.userId = ? and es.id.grpId = ?  and (es.endDt is null) ");
			  hqlQuery.append(" and ee.id.dispId = es.id.dispId  and es.id.activeyn =? ");
			  if(args1!=null && args1.length>0)
			  hospList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);
		  }
    	  if(hospList != null && hospList.size()>0 )
		  {
			  if(hospList.get(0).getVALUE()!=null)
				  hospType=hospList.get(0).getVALUE();
			  if(hospList.get(0).getAhcFlag()!=null && !"".equalsIgnoreCase(hospList.get(0).getAhcFlag()))
				  ahcFlag=hospList.get(0).getAhcFlag();
			  else
				  blockHospAHC="Y";
			  if(hospList.get(0).getChronicFlag()!=null && !"".equalsIgnoreCase(hospList.get(0).getChronicFlag()))
				  chronicFlag=hospList.get(0).getChronicFlag();
			  else
				  blockHospChr="Y";
		  }
    	  
    	  }
  		  List<MenuVo> list=new ArrayList<MenuVo>();
  	        List<MenuVo> listMenu=loginDao.getActionBarModulesForEMP(pStrParntModId,pStrUserId,pStrLangId);
  	        for(MenuVo menuVo:listMenu)
  	        {
  	        	if((chronicFlag!=null && !chronicFlag.equalsIgnoreCase("Y") && "MD40".equalsIgnoreCase(menuVo.getMODID()))
  	        		||blockHospChr!=null && blockHospChr.equalsIgnoreCase("Y") && "MD40".equalsIgnoreCase(menuVo.getMODID()))
	        	{
	        		continue;	
	        	}
  	        	else if((ahcFlag!=null && !ahcFlag.equalsIgnoreCase("Y") && "MD10A".equalsIgnoreCase(menuVo.getMODID()))
  	        		||blockHospAHC!=null && blockHospAHC.equalsIgnoreCase("Y") && "MD10A".equalsIgnoreCase(menuVo.getMODID()))
	        	{
	        		continue;	
	        	}
	        	else
	        	{
	  	        	MenuVo subMenuVo=new MenuVo();
	  	        	subMenuVo.setMODID(menuVo.getMODID());
	  	        	subMenuVo.setMODNAME(menuVo.getMODNAME());
	  	        	subMenuVo.setMODURL(menuVo.getMODURL());
	  	            List<MenuVo> listSubMenus=getRecSubMenuListForEMP(menuVo.getMODID(),grpIdList,pStrUserId, pStrLangId);
	  	            subMenuVo.setListSubMenu(listSubMenus);
	  	            list.add(subMenuVo);
	        	}  
  	        }
  	         return list;
  	}
	
	public EmployeeDetailsVO checkEmpDetails(String pStrUserName,
			String pStrPassword) {
		EmployeeDetailsVO empDetails = new EmployeeDetailsVO();
		List<LabelBean> grpList=new ArrayList<LabelBean>();
		List<LabelBean> moduleList=new ArrayList<LabelBean>();
		StringBuffer  sql_query = new StringBuffer();
		String args[] = new String[1];
		if(pStrUserName!=null && !"".equalsIgnoreCase(pStrUserName))
			args[0] = pStrUserName.toUpperCase() ;
		else
			args[0]=pStrUserName;
		Session session = null;
		SessionFactory factory = hibernateTemplate.getSessionFactory();
		try{
			sql_query.append(" from EhfmUsers EU ,EhfmDesignation ED where ED.id.dsgnId=EU.dsgnId ");
			if(config.getString("EMPNL.LDAPFlag")!=null &&  config.getString("EMPNL.LDAPFlag").equalsIgnoreCase("true"))
				sql_query.append(" and upper(EU.emailId)=?");
				else
			    sql_query.append(" and upper(EU.loginName)=? ");   //pStrUserName.toUpperCase()
//			System.out.print("Sql Query"+sql_query);
			session = factory.openSession();
		    Query query1 = session.createQuery(sql_query.toString());
		    query1.setParameter(0, args[0]);
			Iterator ite = query1.list().iterator();
			while(ite.hasNext())
			{
			Object[] pair = (Object[]) ite.next();
			EhfmUsers ehfmUsers = (EhfmUsers)pair[0];
			EhfmDesignation ehfmDesg = (EhfmDesignation)pair[1];
			empDetails.setUSERID(ehfmUsers.getUserId());
			empDetails.setLOGINNAME(ehfmUsers.getLoginName());
			empDetails.setNAME(ehfmUsers.getFirstName()+" "+ehfmUsers.getLastName());
			empDetails.setDSGNNAME(ehfmDesg.getDsgnName());
			empDetails.setDIGILOGIN(ehfmUsers.getDigiVerifyReq());
			empDetails.setUSERNO(ehfmUsers.getUserNo());
			empDetails.setDESIGNATIONID(ehfmUsers.getDsgnId());
			empDetails.setSERVICEFLAG(ehfmUsers.getServiceFlag());
			empDetails.setUserState(ehfmUsers.getUserType());
			empDetails.setMobileNo(ehfmUsers.getMobileNo());
			empDetails.setEmail(ehfmUsers.getEmailId());
			empDetails.setUserState(ehfmUsers.getUserType());
			}
			
			StringBuffer grpQuery = new StringBuffer();
			String arg1[]=new String[1];
			arg1[0]=empDetails.getUSERID();
		 	grpQuery.append("  select sg.id.grpId as ID from EhfmUsrGrpsMpg sg where sg.id.usergrpId= ?");
		 	grpList = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),arg1);
		 	empDetails.setGrpList(grpList);
			
			grpQuery = new StringBuffer();
			grpQuery.append("  select distinct ecd.cmbDtlName as ID,ecd.cmbAttrVal as VALUE from EhfmUsrGrpsMpg sg,EhfmGrps eg,EhfmModuleGrpMpg emg,EhfmCmbDtls ecd ,EhfmUsers eu ");
			grpQuery.append("  where sg.id.usergrpId= ? and ecd.cmbDtlId=emg.id.moduleName and sg.id.grpId=eg.grpId and eg.grpId=emg.id.grpId and sg.id.usergrpId=eu.userId and eu.userType=emg.schemeId order by ecd.cmbDtlName");
			
			moduleList = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),arg1);
			empDetails.setModuleList(moduleList);
			for(LabelBean lb : grpList){
				if("GP821".equalsIgnoreCase(lb.getID())) {
					
					String blink="";
					grpQuery = new StringBuffer();
					grpQuery.append(" select count(1) count from (select substr(c.disp_name, 6) disp_name,f.drug_type_name,b.drug_id,EDMF.NEW_ITEM_ID AS ITEM_ID,EDMF.RATE_CONTRACT_YEAR AS contract_year,EDMF.PRICE AS price,d.drug_name, ");
					grpQuery.append(" round(quant / 4, 0) avg_stk,quant req,sum(indent_count) ind_notrecv,sum(avl_quant) nat_stock,sum(avl_quant) avl,SUM(LST_3M_QUANT) LST_3M_QUANT from ehfm_disp_drug_mstr b,ehfm_dispensary_dtls c, ");
					grpQuery.append(" ehfm_disp_drug d,ehfm_disp_drug_type f,EHF_DISP_DRUG_MNF_DST_MPGG EDMF,(SELECT DRUG_ID, QUANT, DISP_ID FROM EHF_WC_DRUG_USE_TOP GROUP BY DRUG_ID, QUANT, DISP_ID) e,(select drug_code avl_drg, sum(quantity) avl_quant ");
					grpQuery.append(" from ehfm_disp_drug_mstr WHERE active_yn = 'Y'and quantity > 0 and trunc(expiry_date) > trunc(sysdate)group by drug_code) avl_stk,(select a.drug_code, a.indent_count from ehf_wc_indent a ");
					grpQuery.append(" where a.Indent_Status ='Y') pend_indent,(SELECT EDM.DRUG_CODE DRUG_CODE,SUM(EPD.QUANTITY) LST_3M_QUANT,EP.DISP_CODE DISP_CODE FROM EHF_PATIENT_DRUGS   EPD,EHF_PATIENT         EP, ");
					grpQuery.append(" EHFM_DISP_DRUG_MSTR EDM WHERE EP.patient_id = EPD.PATIENT_ID AND EPD.DISP_DRUG_MSTR_DRG_CODE = EDM.DRUG_CODE AND EPD.CRT_DT > (SYSDATE - 91) GROUP BY EDM.DRUG_CODE, EP.DISP_CODE) CNSM WHERE b.drug_id = d.drug_id ");
					grpQuery.append(" and f.drug_type_id = b.drug_type_id and b.drug_code = avl_stk.avl_drg(+) and b.drug_id = pend_indent.drug_code(+) and d.drug_id = e.drug_id(+) AND B.DRUG_CODE = CNSM.DRUG_CODE(+) AND B.DRUG_ID=EDMF.DRUG_ID ");
					grpQuery.append(" and b.active_yn = 'Y' and b.disp_id = c.disp_id AND C.DISP_ID = E.DISP_ID group by c.disp_name, b.drug_id, ");
					grpQuery.append(" d.drug_name, f.drug_type_name, EDMF.NEW_ITEM_ID, EDMF.RATE_CONTRACT_YEAR, EDMF.PRICE,round(quant / 4, 0),quant order by drug_name) WHERE nvl(req, 0) - nvl(avl, 0) - nvl(ind_notrecv, 0) > 0  ");
					grpQuery.append(" and nvl(avl, 0) + nvl(ind_notrecv, 0) <= (0.66 * nvl(req, 0)) ");//Chandana - 8754 - Changed the % from 75 to 66 for wellness center wise as required by client
					List<LabelBean> lowstock = genericDao.executeSQLQueryList(LabelBean.class, grpQuery.toString());
					if(lowstock !=null && Integer.parseInt(lowstock.get(0).getCOUNT().toString())>0){
						blink="Y";
					}else
						blink="N";
					grpQuery = new StringBuffer();
					grpQuery.append(" select count(1) count from (select substr(c.disp_name, 6) disp_name,f.drug_type_name,b.drug_id,EDMF.NEW_ITEM_ID AS ITEM_ID,EDMF.RATE_CONTRACT_YEAR AS contract_year,EDMF.PRICE AS price,d.drug_name, ");
					grpQuery.append(" round(quant / 4, 0) avg_stk,quant req,sum(indent_count) ind_notrecv,sum(avl_quant) nat_stock,sum(avl_quant) avl,SUM(LST_3M_QUANT) LST_3M_QUANT from ehfm_disp_drug_mstr b,ehfm_dispensary_dtls c, ");
					grpQuery.append(" ehfm_disp_drug d,ehfm_disp_drug_type f,EHF_DISP_DRUG_MNF_DST_MPGG EDMF,(SELECT DRUG_ID, QUANT, DISP_ID FROM EHF_WC_DRUG_USE_TOP GROUP BY DRUG_ID, QUANT, DISP_ID) e,(select drug_code avl_drg, sum(quantity) avl_quant ");
					grpQuery.append(" from ehfm_disp_drug_mstr WHERE active_yn = 'Y'and quantity > 0 and trunc(expiry_date) > trunc(sysdate)group by drug_code) avl_stk,(select a.drug_code, a.indent_count from ehf_wc_indent a ");
					grpQuery.append(" where a.Indent_Status ='Y') pend_indent,(SELECT EDM.DRUG_CODE DRUG_CODE,SUM(EPD.QUANTITY) LST_3M_QUANT,EP.DISP_CODE DISP_CODE FROM EHF_PATIENT_DRUGS   EPD,EHF_PATIENT         EP, ");
					grpQuery.append(" EHFM_DISP_DRUG_MSTR EDM WHERE EP.patient_id = EPD.PATIENT_ID AND EPD.DISP_DRUG_MSTR_DRG_CODE = EDM.DRUG_CODE AND EPD.CRT_DT > (SYSDATE - 91) GROUP BY EDM.DRUG_CODE, EP.DISP_CODE) CNSM WHERE b.drug_id = d.drug_id ");
					grpQuery.append(" and f.drug_type_id = b.drug_type_id and b.drug_code = avl_stk.avl_drg(+) and b.drug_id = pend_indent.drug_code(+) and d.drug_id = e.drug_id(+) AND B.DRUG_CODE = CNSM.DRUG_CODE(+) AND B.DRUG_ID=EDMF.DRUG_ID ");
					grpQuery.append(" and b.active_yn = 'Y' and b.disp_id = c.disp_id AND C.DISP_ID = E.DISP_ID group by c.disp_name, b.drug_id, ");
					grpQuery.append(" d.drug_name, f.drug_type_name, EDMF.NEW_ITEM_ID, EDMF.RATE_CONTRACT_YEAR, EDMF.PRICE,round(quant / 4, 0),quant order by drug_name) WHERE nvl(req, 0) - nvl(avl, 0) - nvl(ind_notrecv, 0) > 0  ");
					grpQuery.append(" and nvl(avl, 0) + nvl(ind_notrecv, 0) <= (0.66 * nvl(req, 0)) ");
					List<LabelBean> lowstockReport = genericDao.executeSQLQueryList(LabelBean.class, grpQuery.toString());
					if(lowstock !=null && Integer.parseInt(lowstockReport.get(0).getCOUNT().toString())>0){
						blink=blink+"Y";
					}else
						blink=blink+"N";
					empDetails.setLowStockAlert(blink);
				}
			
				if("GP820".equalsIgnoreCase(lb.getID())) {
					grpQuery = new StringBuffer();
					String blink="";
					grpQuery.append(" select count(1) from(select to_char(disp_name) DISP_NAME, to_char(indent_id) INDENT_ID, to_char(drug_type_name) DRUG_TYPE, ");
					grpQuery.append(" to_char(item_id) ITEM_ID, to_char(price) PRICE, to_char(contract_year) CONTRACT_YEAR, to_char(drug_id) DRUG_ID, ");
					grpQuery.append(" to_char(drug_name) DRUG_NAME, to_char(nvl(avg_stk, 0)) AVG_STK, to_char(nvl(req, 0)) REQ, to_char(nvl(avl, 0)) AVL, ");
					grpQuery.append(" to_char(nvl(ind_notrecv, 0)) IND_NOTRECV, to_char(nvl(ind_notrecv, 0) + nvl(avl, 0)) NAT_STOCK, ");
					grpQuery.append(" to_char(nvl(req, 0) - nvl(nat_stock, 0) - nvl(ind_notrecv, 0)) NAT_DEF,to_char((nvl(req, 0) - nvl(nat_stock, 0) - nvl(ind_notrecv, 0)) * nvl(price, 0)) IND_VAL, ");
					grpQuery.append(" to_char(sum((nvl(req, 0) - nvl(nat_stock, 0) - nvl(ind_notrecv, 0)) * nvl(price, 0)) over(PARTITION BY 1)) TOT_STOCKPRICE ");
					grpQuery.append(" from (select substr(c.disp_name, 6) disp_name, '' indent_id, f.drug_type_name, b.drug_id, b.item_id, b.contract_year, ");
					grpQuery.append(" max(b.price) price, d.drug_name, round(sum(quant) / 4, 0) avg_stk, sum(quant) req, sum(indent_count) ind_notrecv, sum(avl_quant) nat_stock, ");
					grpQuery.append(" sum(avl_quant) avl from ehfm_disp_drug_mstr b, ehfm_dispensary_dtls c, ehfm_disp_drug d, ehfm_disp_drug_type f, ");
					grpQuery.append(" (SELECT DRG_CD, SUM(QUANT) QUANT FROM EHF_WC_DRUG_USE_TOP GROUP BY DRG_CD) e, (select drug_code avl_drg,  ");
					grpQuery.append(" sum(quantity) avl_quant from ehfm_disp_drug_mstr WHERE active_yn = 'Y' and quantity > 0 and trunc(expiry_date) > trunc(sysdate) ");
					grpQuery.append(" group by drug_code) avl_stk, (select a.drug_code, a.indent_count, INDENT_ID from ehf_wc_indent a where a.active_yn IN ('I','N')) pend_indent  ");
					grpQuery.append(" WHERE b.drug_id = d.drug_id and f.drug_type_id = b.drug_type_id and b.drug_code = avl_stk.avl_drg(+) and b.drug_id = pend_indent.drug_code(+) ");
					grpQuery.append(" and b.drug_code = e.drg_cd and b.active_yn = 'Y' and b.disp_id = c.disp_id and b.contract_year in ('2020-2022(NIMS)', '2020-2022(TSMSIDC)','2022-2024(NIMS)') ");
					grpQuery.append(" group by c.disp_name, pend_indent.INDENT_ID, b.drug_id, d.drug_name, f.drug_type_name, b.item_id, b.contract_year) ");
					grpQuery.append(" WHERE nvl(req, 0) - nvl(avl, 0) - nvl(ind_notrecv, 0) > 0 and nvl(avl, 0) + nvl(ind_notrecv, 0) <= (0.66 * nvl(req, 0))  ");
					grpQuery.append(" AND DRUG_ID NOT IN(select drug_code from ehf_wc_indent where active_yn = 'I' ) UNION ALL  select to_char(disp_name) DISP_NAME, ");
					grpQuery.append(" to_char(indent_id) INDENT_ID, to_char(drug_type_name) DRUG_TYPE, to_char(item_id) ITEM_ID, to_char(price) PRICE, ");
					grpQuery.append(" to_char(contract_year) CONTRACT_YEAR, to_char(drug_id) DRUG_ID, to_char(drug_name) DRUG_NAME, to_char(nvl(avg_stk, 0)) AVG_STK, ");
					grpQuery.append(" to_char(nvl(req, 0)) REQ, to_char(nvl(avl, 0)) AVL, to_char(nvl(ind_notrecv, 0)) IND_NOTRECV,to_char(nvl(ind_notrecv, 0) + nvl(avl, 0)) NAT_STOCK, ");
					grpQuery.append("  to_char(nvl(req, 0) - nvl(nat_stock, 0) - nvl(ind_notrecv, 0)) NAT_DEF,to_char((nvl(req, 0) - nvl(nat_stock, 0) - nvl(ind_notrecv, 0)) * nvl(price, 0)) IND_VAL, ");
					grpQuery.append(" to_char(sum((nvl(req, 0) - nvl(nat_stock, 0) - nvl(ind_notrecv, 0)) * nvl(price, 0)) over(PARTITION BY 1)) TOT_STOCKPRICE  ");
					grpQuery.append(" from (select substr(c.disp_name, 6) disp_name, EWI.INDENT_ID indent_id, f.drug_type_name, b.drug_id, b.item_id, b.contract_year, ");
					grpQuery.append(" max(b.price) price, d.drug_name, round(sum(quant) / 4, 0) avg_stk, sum(quant) req, sum(pend_indent.indent_count) ind_notrecv, ");
					grpQuery.append(" sum(avl_quant) nat_stock, sum(avl_quant) avl from ehfm_disp_drug_mstr b, ehfm_dispensary_dtls c, ehfm_disp_drug d, ");
					grpQuery.append(" ehfm_disp_drug_type f, ehf_wc_indent EWI, (SELECT DRG_CD, SUM(QUANT) QUANT FROM EHF_WC_DRUG_USE_TOP GROUP BY DRG_CD) e, ");
					grpQuery.append(" (select drug_code avl_drg, sum(quantity) avl_quant from ehfm_disp_drug_mstr WHERE active_yn = 'Y' and quantity > 0  ");
					grpQuery.append(" and trunc(expiry_date) > trunc(sysdate) group by drug_code) avl_stk, (select a.drug_code, a.indent_count, INDENT_ID  ");
					grpQuery.append(" from ehf_wc_indent a where a.active_yn = 'N') pend_indent WHERE b.drug_id = d.drug_id and f.drug_type_id = b.drug_type_id  ");
					grpQuery.append(" and b.drug_code = avl_stk.avl_drg(+) and b.drug_id = pend_indent.drug_code(+) and b.drug_code = e.drg_cd and b.active_yn = 'Y'  ");
					grpQuery.append(" and b.disp_id = c.disp_id and b.contract_year in ('2020-2022(NIMS)', '2020-2022(TSMSIDC)','2022-2024(NIMS)')  AND EWI.active_yn = 'I'  ");
					grpQuery.append(" AND EWI.disp_id = b.disp_id AND EWI.drug_code = b.drug_id group by c.disp_name, EWI.INDENT_ID,b.drug_id, d.drug_name, f.drug_type_name, b.item_id, b.contract_year)) ");

					List<LabelBean> lowstock = genericDao.executeSQLQueryList(LabelBean.class, grpQuery.toString());
					if(lowstock !=null && Integer.parseInt(lowstock.get(0).getCOUNT().toString())>0){
						blink="Y";
					}else
						blink="N";
					empDetails.setLowStockAlert(blink);
				}
}
		}
		catch(Exception e)
		{
		//empDetails.setMsg("Invalid User Name/Password");    
		e.printStackTrace();	
		}
		finally
		{
			session.close();
		}
		return empDetails;
	}
	
	/**
     * 
     * @param user id
     * @return String-theme name
     * @function Used to save the get theme name of a user by their user id
     */
	
	public String getTheme(String pStrUserId) {
//		System.out.println("getTheme");
//		System.out.println(pStrUserId);
		if(pStrUserId!=null){
		EhfmUsers lstEmpDtls =  genericDao.findById(EhfmUsers.class,String.class,pStrUserId);
		if(lstEmpDtls!=null && lstEmpDtls.getTheme()!=null && !"".equalsIgnoreCase(lstEmpDtls.getTheme()))
			return lstEmpDtls.getTheme();
			else
				return "default";
		}
		else
			return "default";
	}
	
	
	/**
     * 
     * @param user name
     * @return mobile number if user exists
     * @function This function is used to get the mobile number of a user.
     */
	
	public String getPhoneNumber(String pStrUserName){
		EhfmUsers lEhfmUsers = null;
		lEhfmUsers = genericDao.findById(EhfmUsers.class, String.class, pStrUserName.trim());
				if(lEhfmUsers!=null)
			return lEhfmUsers.getMobileNo();
		return null;
	}
	
	
	/**
     * 
     * @param theme name and user name
     * @return updates the theme name of the user
     * @function This function is used to update the theme of the user.
     */
	public boolean saveTheme(String pStrTheme,String pStrUserId){
		 List<GenericDAOQueryCriteria> criteraiList = new ArrayList<GenericDAOQueryCriteria>();
		 EhfmUsers ehfmUsers=null;;
		 try{
		 criteraiList.add(new GenericDAOQueryCriteria("userId",pStrUserId,GenericDAOQueryCriteria.CriteriaOperator.EQUALS) );
  	   List<EhfmUsers> lstEmpDtls = genericDao.findAllByCriteria(EhfmUsers.class, criteraiList);
		if(lstEmpDtls!=null && lstEmpDtls.size()>0){
			ehfmUsers=lstEmpDtls.get(0);
			ehfmUsers.setTheme(pStrTheme);
			//String lStrPswd=asriCommonService.getDecryptedPswd(ehfmUsers.getLoginName());
			//if(lStrPswd!=null)
			//	ehfmUsers.setPasswd(lStrPswd);
			//	ehfmUsers.setTheme(pStrTheme);
			//ehfmUsers=genericDao.save(ehfmUsers);
			}
			if(ehfmUsers!=null)
				return true;
		}
		 catch(Exception e){
			 e.printStackTrace();
		 }
		 return false;
	}
	
	
	/**
     * 
     * @param String emp id
     * @return List of side menu with its relevant data
     * @function This function is Used to fetch side menu.
     */
	public List<LabelBean> getMainMods(String pStrEmpId){
		StringBuffer grpQuery = new StringBuffer();
		grpQuery.append("  select distinct EM.modId as ID ,EM.modName as VALUE , EM.subUrl as ACTION,EM.modOrder as COUNT FROM EhfmUsrGrpsMpg EUGM,EhfmModGrpMpg EMGM,EhfmUsers EU,EhfmMod EM where EU.userId=? and EU.userId=EUGM.id.usergrpId and EUGM.id.grpId=EMGM.id.grpId and EMGM.id.modId=EM.modId order by EM.modOrder asc ");
	 	String[] args=new String[1];
	 	args[0]=pStrEmpId;
		List<LabelBean> lStModMenu = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(),args);

		return lStModMenu;
		
		
		
		
	}
	
	
	public List<EmployeeDetailsVO> getContactInfoForEhfmUsers(String login) {
		StringBuffer hqlQuery = new StringBuffer();
		List<EmployeeDetailsVO>  empDtlsList=new ArrayList<EmployeeDetailsVO>();
		hqlQuery.append(" select case when mobileNo is null then ? else mobileNo end as ID,case when emailId is null then ? else emailId end as  VALUE from EhfmUsers where upper(loginName) = ? " );
	 	String[] args1 = new String[3];
		args1[0] = "-NA-";
	 	args1[1] = "-NA-";
	 	args1[2] = login.toUpperCase();
	 
		try{
		
	 	 empDtlsList= genericDao.executeHQLQueryList(EmployeeDetailsVO.class, hqlQuery.toString(),args1);
		}catch(Exception e){
			e.printStackTrace();
		}
		return empDtlsList;
	}
	      public List<EmployeeDetailsVO> checkCasesForClinicalNotes(String userId)
	      {
	    	  List<EmployeeDetailsVO> list=new ArrayList<EmployeeDetailsVO>();
	    	  try
	    	  {
	    		  list=loginDao.checkCasesForClinicalNotes(userId);
		        
	    	  }
	    	  catch(Exception e)
	    	  {
	    		  e.printStackTrace();
	    	  }
		         return list;
	      }
	    
		@Override
		public List<LabelBean> getUserMessage(String userId,String moduleId) {
			
		List<LabelBean> list=null;
		 list=loginDao.getUserMessage(userId,moduleId);
		 
		return list;
		
		}
		@Override
		public List<LabelBean> getGroupMessage(List grpId,String moduleId) {
			
			List<LabelBean> list=null;
			 list=loginDao.getGroupMessage(grpId,moduleId);
			 
			return list;
			
			
		}
		@Override
		public List<LabelBean> getHospitalMessage(String hospId,String grpId,String moduleId) {
			List<LabelBean> list=null;
			 list=loginDao.getHospitalMessage(hospId,grpId,moduleId);
			return list;
			
		}
		@Override
		public String getHospitslId(String userId, String grpId) {
			
			String hospId=null;
			hospId=hospId=loginDao.getHospitslId(userId, grpId);
			
			
			return hospId;
		}
		
		
		
		
		
		/**
		 * This validates user-id with registered mobile number and send the
		 * OTP to that mobile
		 * @param User-id and phone number
		 * @return Returns the result of success or failure return type is a string
		 */
		@Override
		@Transactional
		public String generateOTPAndSendPswdSms(EmployeeDetailsVO employeeDetailsVO) {
			String pStrUserId=null;
			if(employeeDetailsVO.getUSERID()!=null)
			pStrUserId = employeeDetailsVO.getUSERID().trim();
			String lStrReturnMsg = null;
			
			EhfmUsers ehfmUsers = null;
			String OTP = "";
			try {
				/*
				 * Generating a randon 8-digit alpha-numeric string for new
				 * password
				 */
				OTP = RandomStringUtils.randomNumeric(8);
				employeeDetailsVO.setVALUE(OTP);
		
				String lStrPhone=employeeDetailsVO.getMobileNo();
				
				String lStrUserId=employeeDetailsVO.getUSERID();
			
				lStrReturnMsg = OTP;
				
				

				
						EhfSmsData ehfSmsData =null;
						
						if (lStrPhone != null && !"".equalsIgnoreCase(lStrPhone.trim()) && "Y".equalsIgnoreCase(config.getString("sendPasswordSMS"))) {
						try {
							    templateId="1407161769810700722";
								ehfSmsData = new EhfSmsData();
								//ehfSmsData.setSerialNo(getMaxSno());
								ehfSmsData.setCrtDt(new java.sql.Timestamp(
										new Date().getTime()));
								ehfSmsData.setCrtUsr(lStrUserId);
								ehfSmsData.setUserId(lStrUserId);
								ehfSmsData.setPhoneNo(lStrPhone);
								ehfSmsData.setEhfPasswod(OTP);
								ehfSmsData.setTemplateId(templateId);
								String lStrMsg = "Your "
										+ "One-Time Password(OTP) "+ "for logging into \"Employee Health Scheme\" is "+OTP+"\n\nAHCT, Govt. of Telangana"; ;
										
								//SendSMS sendSms =new SendSMS();
								SMSServices SMSServicesobj = new SMSServices();
								SMSServicesobj.sendSingleSMS(lStrMsg,
										lStrPhone,templateId);
								lStrReturnMsg = OTP;
								ehfSmsData.setSmsText(lStrMsg);
						} catch (Exception e) {
							e.printStackTrace();

//							oslogger.error("Exception Occured while reseting password and sending it through SMS in function-->>validateAndSendPswdSmvalidateAndSendPswdSms");
							ehfSmsData.setSmsText("Msg Delivery Failed");
							
						} finally {
							genericDao.save(ehfSmsData);
						}
						}
						/* END of sending SMS */
						/** Saving all the data */
						
						
						/*  Sending E-mail on change of password */
						
						
				}
			 
			
			catch (Exception e) {
				e.printStackTrace();
//				oslogger.error("Exception Occured while generating OTP and sending it through SMS");
				
			}

			return lStrReturnMsg;
		}
	/*added by venkatesh for getting scheme of medco as per mapped hospital*/ 
		
		public String getMedcoScheme(String userId)
		{
			String scheme=null;
			String args[]=new String[1];
			args[1]=userId;
			try
			{
			StringBuffer query=new  StringBuffer();
			List<LabelBean> SchemeList=new ArrayList<LabelBean>();
			query.append(" select b.scheme as schemeId from EhfmMedcoDtls a,EhfmHospitals b");
			query.append( " where a.id.hospId=b.hospId and a.id.medcoId=? ");
			SchemeList=genericDao.executeHQLQueryList(LabelBean.class,query.toString(), args);
			
			if(SchemeList!=null)
			{
				scheme=SchemeList.get(0).getSchemeId();
			}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return scheme;
			
		}
		
		/*
		 * Added for User Service Flag Validation after 
		 * Successful Open AM Authentication
		 */
		@Override
		public boolean authenticateUserServiceFlagForOpenAM ( String userID ){
			StringBuffer query = new StringBuffer();
			String args[] = new String[2];
			boolean lStrResult = false ;
			SessionFactory factory=hibernateTemplate.getSessionFactory();
		    Session session = factory.openSession();
			try
		      {
				
			if(!(userID==null || "".equalsIgnoreCase(userID)))
				{
					StringBuffer hqlQuery = new StringBuffer();
					hqlQuery.append(" select loginName as ID from EhfmUsers where upper(loginName) = ? and serviceFlag=? and userType=? and  serviceExpiryDt is null " );
				 	String[] args1 = new String[3];
				 	userID=userID.toUpperCase();
				 	args1[0] = userID;
					args1[1]="Y";
					args1[2] = config.getString("Scheme.TG.State");
					
				 	List<LabelBean>  empDtlsList= genericDao.executeHQLQueryList(LabelBean.class, hqlQuery.toString(),args1);

					 if(empDtlsList != null && empDtlsList.size()>0 )
			          {
			            lStrResult = true;
			          }
			          else
			          {
			        	  lStrResult = false;
			          }
			          
			      }
			else{
				
				lStrResult = false;
			}
		      }
			catch ( Exception ex )
		      {
		       ex.printStackTrace();
		        return false ;
		      }
			finally
			{
				session.close();
			}
			return lStrResult ;
		}

	/*
	 * Added for Checking Hospital Type 
	 */
	public String checkHospType(String userID)
		{
			String returnMsg=null;
			try
				{
					StringBuffer query =new StringBuffer();
					String[] args1 = new String[1];
					args1[0]=userID;
					query.append( " select c.applictn_type ACTION " );
					query.append( " from ehfm_medco_dtls a,ehfm_hospitals b," );
					query.append( " ehf_empnl_hospinfo c,ehfm_cmb_dtls d" );
					query.append( " where a.medco_id = ? and a.hosp_id = b.hosp_id " );
					query.append( " and b.hosp_empnl_ref_num = c.hospinfo_id and c.applictn_type = d.cmb_dtl_id " );
					List<LabelBean> lst= genericDao.executeSQLQueryList(LabelBean.class, query.toString(), args1);
					if(lst!=null && lst.size()>0 && lst.get(0).getACTION()!=null)
						{
							if(lst.get(0).getACTION().equalsIgnoreCase(config.getString("dentalClinic")))
								returnMsg="clinic";
							else if(lst.get(0).getACTION().equalsIgnoreCase(config.getString("dentalHospital")))
								returnMsg="hospital";
							else
								returnMsg="N";
						}
				}
			catch(Exception e)
				{
					e.printStackTrace();
//					oslogger.error("Exception Occured in checkHospType method of LoginServiceImpl class");
					return returnMsg; 
				}
			return returnMsg;
		}
	/**
     * 
     * @param user name
     * @return if logged user belongs to NIMS hospital
     * @function This function is used to get the hospital details of a user.
     */
	
	public String getNimsMedco(String userId){
		StringBuffer grpQuery = new StringBuffer();
		String[] args1 = new String[1];
		args1[0]=userId;
		List<LabelBean> hosp_list=new ArrayList<LabelBean>();
		grpQuery.append("  select sg.caseHospCode as ID from EhfCase sg where sg.id.caseId= ? ");
		
		hosp_list = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString(), args1);
	 	//empDetails.setGrpList(grpList);
				if(hosp_list.size()>0 && hosp_list!=null)
			return hosp_list.get(0).getID();
		return null;
	}
		
	private List<LabelBean> validateClaimPending(String status, int days, String schemeId,String userid) 
	{
		oslogger.info("Start:validateClaimPending(CD44,CD47,CD49) method");
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		
		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	args[0]=status;
    	args[1]=userid;
    	args[2]=schemeId;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
		
		
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE, PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B, EHFM_MEDCO_DTLS M");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=?");
		query.append(" AND CASE_HOSP_CODE=M.HOSP_ID and  M.MEDCO_ID=? ");
		query.append(" AND A.SCHEME_ID=? AND TRUNC(SYSDATE-A.LST_UPD_DT) between 25 and 30 ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		for(int k=0;k<cnt;k++)
    	{
    		if(k==0){
    			query.append(" ( ? ");
    			
    		}
    		else if(k==cnt-1)
    		{
    			query.append(" , ? ) ");
    		}
    		else
    		{
    			query.append(" , ? ");
    		}
    		
    	}
		query.append(" AND ect.activeyn = ?)");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE(?,'DD/MM/YYYY')");
		query.append(" AND A.CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP ) ");
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newCasesList;
	}
	private List<LabelBean> validateClaimSubmitPending(String status, int days, String schemeId,String userid) 
	{
		System.out.println("Start:validateClaimSubmitPending(CD21) method");
		List<LabelBean> newCasesList = null;
		try{
		StringBuffer query = new StringBuffer();
		
		String test="20.96~N18.6.A~92.23.2~92.23.3~92.24.2~92.20.1~92.20.2~92.27.1~92.27.2~92.29.1~99.23.2~99.23.1~95.49.7~95.49.8~95.49.3~95.49.4~95.49.5~92.24.3~64.3.1~92.23.1~92.24.1~92.29.2~92.29.3~S13.1.8~S13.1.7~S13.1.9~S13.1.6~S13.1.10~S13.1.11~S13.1.2~S13.1.1~S13.1.3~S13.1.5~S13.1.4";
		String testarr[]=test.split("~");
		int cnt=testarr.length;
		
		String[] args=new String[cnt+5];
		int totCnt=args.length;
    	args[0]=status;
    	args[1]=userid;
    	args[2]=schemeId;
    	for(int k=0;k<cnt;k++)
    	{
    		args[k+3]=testarr[k];
    	}
    	args[totCnt-2]="Y";
    	args[totCnt-1]="01/02/2016";
    	
    	
		query.append("SELECT A.CASE_ID AS CASEID ,A.CASE_NO AS CASENO,A.CASE_STATUS AS CASESTATUS,A.CASE_REGN_DATE AS REGNDATE,PATIENT_IPOP as PATIENTTYPE FROM EHF_CASE A , EHF_PATIENT B, EHFM_MEDCO_DTLS M");
		query.append(" WHERE A.CASE_PATIENT_NO=B.PATIENT_ID AND A.CASE_STATUS=? ");
		query.append(" AND CASE_HOSP_CODE=M.HOSP_ID and  M.MEDCO_ID=? ");
		query.append(" AND A.SCHEME_ID=? AND TRUNC(SYSDATE-A.LST_UPD_DT) between 55 and 60 ");
		query.append(" and a.case_id not in (select distinct ect.case_id from ehf_case_therapy ect where ect.icd_proc_code  in ");
		for(int k=0;k<cnt;k++)
    	{
    		if(k==0){
    			query.append(" ( ? ");
    			
    		}
    		else if(k==cnt-1)
    		{
    			query.append(" , ? ) ");
    		}
    		else
    		{
    			query.append(" , ? ");
    		}
    		
    	}
		query.append(" AND ect.activeyn = ?)");
		query.append(" AND TRUNC(B.REG_HOSP_DATE)>=TO_DATE(?,'DD/MM/YYYY')");
		query.append(" AND A.CASE_ID NOT IN (select CASE_ID from EHF_NABH_CANCEL_EXEMP ) ");
		newCasesList = genericDao.executeSQLQueryList(LabelBean.class, query.toString(),args);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return newCasesList;
	}
	
	@Override
	public List<LabelBean> getCasesAbtToExpire(String userid) {
	  List<LabelBean> allCanceledCases = new ArrayList<LabelBean>();
		//Claims
	   List<LabelBean> validateClaimPending1 = validateClaimPending("CD44",25,"CD202",userid);	//CPD Pending
	   if (!validateClaimPending1.isEmpty()) {
		   allCanceledCases.addAll(validateClaimPending1);
	   }
	   
	   List<LabelBean> validateClaimPending2 = validateClaimPending("CD47",25,"CD202",userid);	//CTD Pending
	   if (!validateClaimPending2.isEmpty()) {
		   allCanceledCases.addAll(validateClaimPending2);
	   }
	   
	   List<LabelBean> validateClaimPending3 = validateClaimPending("CD49",25,"CD202",userid);	//CH Pending
	   if (!validateClaimPending3.isEmpty()) {
		   allCanceledCases.addAll(validateClaimPending3);
	   }
	   
	   List<LabelBean> validateClaimSubmitPending = validateClaimSubmitPending("CD21",55,"CD202",userid); 	//Discharge updated(claim submission)	
	   if (!validateClaimSubmitPending.isEmpty()) {
		   allCanceledCases.addAll(validateClaimSubmitPending);
	   }
	   if(allCanceledCases.size()>0)
			return allCanceledCases;
		   else return null;
	}
	@Override
	public EmployeeDetailsVO saveLoginDtls(LoginDtlsVO vo)
	{
		String msg="";
		EmployeeDetailsVO detailsVO = new EmployeeDetailsVO();
		try{
		EhfmUserLoginDtls efmUserLoginDtls =  genericDao.findById(EhfmUserLoginDtls.class,String.class,vo.getSeqId());
		if(efmUserLoginDtls == null)
		{
			String liNextVal="";
			Calendar calendar = null;
			calendar = Calendar.getInstance();
			calendar.set(Calendar.MONTH, calendar.get( Calendar.MONTH ) + 1 );
			liNextVal = "TG"+"/"+calendar.get(Calendar.YEAR)+"/"+calendar.get(Calendar.MONTH)+"/"+getSequence("EHF_SESSION_SEQ");
			efmUserLoginDtls = new EhfmUserLoginDtls();
			
			if(vo.getSeqId() != null && !"".equalsIgnoreCase(vo.getSeqId()))
				detailsVO.setSeqId(vo.getSeqId());
			else
				detailsVO.setSeqId(liNextVal);
			detailsVO.setSessionId(vo.getSessionId());
			
			if(vo.getSeqId() != null && !"".equalsIgnoreCase(vo.getSeqId()))
				efmUserLoginDtls.setSeqId(vo.getSeqId());
			else
				efmUserLoginDtls.setSeqId(liNextVal);
			efmUserLoginDtls.setSessionId(vo.getSessionId());
			efmUserLoginDtls.setIpAddress(vo.getIpAddress());
			//efmUserLoginDtls.setMacAddress(vo.getMacAddress());
			efmUserLoginDtls.setUserId(vo.getUserId());
			efmUserLoginDtls.setLoginTime(new java.sql.Timestamp(new Date().getTime()));
			efmUserLoginDtls.setOperationsnabh(new java.sql.Timestamp(new Date().getTime()));
			efmUserLoginDtls.setCrtdt(new java.sql.Timestamp(new Date().getTime()));
			efmUserLoginDtls.setCrtusr(vo.getUserId());
			genericDao.save(efmUserLoginDtls);
		}
		else
		{
			detailsVO.setSessionId(efmUserLoginDtls.getSessionId());
			detailsVO.setSeqId(efmUserLoginDtls.getSeqId());
			efmUserLoginDtls.setOperationsnabh(new java.sql.Timestamp(new Date().getTime()));
			genericDao.save(efmUserLoginDtls);
		}
		
		msg="Success";
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			oslogger.error("Exception Occured at saveLoginDtls method in LoginServiceImpl class");
			msg="fail";
		}
		return detailsVO;
	}
	@Override
	public EmployeeDetailsVO saveLogOutDtls(LoginDtlsVO vo) 
	{
		EmployeeDetailsVO detailsVO = new EmployeeDetailsVO();
		try
		{
			EhfmUserLoginDtls efmUserLoginDtls =  genericDao.findById(EhfmUserLoginDtls.class,String.class,vo.getSeqId());
			if(efmUserLoginDtls != null && (efmUserLoginDtls.getSeqId() != null && !"".equalsIgnoreCase(efmUserLoginDtls.getSeqId())))
			{
				efmUserLoginDtls.setLogoutTime(new java.sql.Timestamp(new Date().getTime()));
				genericDao.save(efmUserLoginDtls);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
//			oslogger.error("Exception Occured at saveLogOutDtls method in LoginServiceImpl class");
		}
		return detailsVO;
	}
	public String getSequence(String pStrSeqName)throws Exception
	  {
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
	  @Override
	public String getorgFlag(String caseId) {
		// TODO Auto-generated method stub
		StringBuffer grpQuery = new StringBuffer();
		List<LabelBean> hosp_list=new ArrayList<LabelBean>();
		grpQuery.append("  select sg.secFlag as ID from EhfCase sg where sg.id.caseId= '"+caseId+"'");
		
		hosp_list = genericDao.executeHQLQueryList(LabelBean.class, grpQuery.toString());
	 	//empDetails.setGrpList(grpList);
				if(hosp_list.size()>0 && hosp_list!=null)
			return hosp_list.get(0).getID();
		return null;
	}
}
