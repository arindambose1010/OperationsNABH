package com.ahct.patient.action;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.SMSServices;
import com.ahct.common.vo.LabelBean;
import com.ahct.common.vo.PatientSmsVO;
import com.ahct.model.EhfOpConsultData;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfPatientTests;
import com.ahct.model.EhfmHospitals;
import com.ahct.model.EhfmSeq;
import com.ahct.patient.constants.FileConstants;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.vo.AttachmentVO;
import com.ahct.patient.vo.CaseTherapyVO;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.LabelVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.SpecialityVO;
import com.ahct.preauth.service.CasesApprovalService;
import com.google.gson.Gson;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;
public class PatientAction extends Action {
	private final static Logger GLOGGER = Logger.getLogger ( PatientAction.class ) ;
	@SuppressWarnings("unchecked")
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{	
		String templateId=null;
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0);    
		HttpSession session = request.getSession ( false ) ;
		PatientForm patientForm=(PatientForm)form;
		PatientVO patientVO=null;
		//PreauthVO preauthVO=null;
		ConfigurationService configurationService = 
				(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
//		LoginService loginService =(LoginService)ServiceFinder.getContext(request).getBean("loginService");
		 TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		CasesApprovalService casesApprovalService = ( CasesApprovalService ) ServiceFinder.getContext ( request ).getBean ( "casesApprovalService" ) ;
		String userName=null;
		String lStrLangID=null;
		String lStrUserId =null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String grpId=null;
		String roleId=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");
		String serverDt = sdfw.format(new Date());
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		String onlyDate=sdf.format(new Date());
		FileInputStream fis=null;
		DataInputStream dis=null;
		String schemeId=null;
		byte bytes[]=null;
		String smsNextVal="";
		String callType=null;
		String ipOpType = null;
		String bifurcationDate=config.getString("bifurcationDate");
		request.setAttribute("bifurcationDate", bifurcationDate);
		if(request.getParameter("callType")!=null && !"".equals(request.getParameter("callType")))
		{
			callType=request.getParameter("callType");
		}
		String lStrResultPage = HtmlEncode.verifySession(request, response);
		if (lStrResultPage.length() > 0)
		{
			if(callType!=null && "Ajax".equals(callType))
			{
				request.setAttribute("AjaxMessage","SessionExpired");
				return mapping.findForward("ajaxResult");
			}
			else
				return mapping.findForward("sessionExpire");
		}
		String userIDu=(String) session.getAttribute("UserID");
		String fromDisp = request.getParameter("fromDisp");
		String mobileAppDispp=request.getParameter("mobileApp");
		String mobileAppDisppNew=request.getParameter("mobileAppAis");
		String hospGovu=patientService.checkGovernmentHosp(userIDu,fromDisp);
		request.setAttribute("hospGovu", hospGovu);
		String bioRegFlag = config.getString("biometricRegistration");
		if(bioRegFlag==null && session.getAttribute("bioRegFlag")!=null)
			bioRegFlag=(String)session.getAttribute("bioRegFlag");
		if(bioRegFlag!=null &&!"".equalsIgnoreCase(bioRegFlag))
			session.setAttribute("bioRegFlag",bioRegFlag);
		if(session.getAttribute("EmpID").toString()!=null)
		{
			lStrUserId = session.getAttribute("EmpID").toString();
		}
		if(session.getAttribute("LangID").toString()!=null)
		{
			lStrLangID = session.getAttribute("LangID").toString();
		}
		if(session.getAttribute("userName").toString()!=null)
		{
			userName=session.getAttribute("userName").toString();
		}
		if(session.getAttribute("groupList").toString()!=null)
		{
			grpList=(List<LabelBean>)session.getAttribute("groupList");
		}
		if(session.getAttribute("userState").toString()!=null)
		{
			schemeId=session.getAttribute("userState").toString();
		}
		for(LabelBean labelBean:grpList)
		{
			lStrgrpList.add(labelBean.getID());
		}
		if(lStrgrpList.contains(config.getString("Group.Mithra")))
		{
			roleId=config.getString("Group.Mithra");
		}
		else if(lStrgrpList.contains(config.getString("Group.Medco")))
		{
			roleId=config.getString("Group.Medco");
		}
		else if(lStrgrpList.contains(config.getString("Group.Pex")))
		{
			roleId=config.getString("Group.Pex");
		}
		else
		{
			roleId=lStrgrpList.get(0);
		}
		if(lStrgrpList.contains("GP797"))
		{
			request.setAttribute("labGrp", "GP797");
		}
		else if(lStrgrpList.contains("GP801"))
		{
			request.setAttribute("labGrp", "GP801");
			grpId="GP801";
		}
		String stateHdrId =config.getString("IPOP.StateHDRID");
		String statePrntId =config.getString("IPOP.StatePrntId");
		String distHdrId =config.getString("IPOP.DistrictHDRID");
		String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
		String lStrMandalHdrId = config.getString("IPOP.MandalHDRID");
		String lStrMunicipalHdrId = config.getString("IPOP.MunicipalityHDRID");
		String lStrVillageHdrId = config.getString("IPOP.VillageHDRID");
		String lStrMplVillageHdrId = config.getString("IPOP.MunicipalVillageHDRID");
		String lStrActionVal = request.getParameter("actionFlag");
		String lStrPageName = null;
		List<LabelBean> hospDtlsList = null;
		request.setAttribute("hospDtlsList", hospDtlsList);
		List<LabelBean> drugTypeList = null;
		request.setAttribute("drugTypeList", drugTypeList);
		String loggedUserHospId= commonService.getloggedUserHospId(lStrUserId,schemeId);
		List<LabelBean> medicalSpltyList=null;
		if(!"".equalsIgnoreCase(loggedUserHospId) && loggedUserHospId != null)
		{
			medicalSpltyList	=commonService.getMedicalSpltyList(loggedUserHospId,schemeId);     
			request.setAttribute("medicalSpltyList", medicalSpltyList);
		}
		List<LabelBean> medicalCatList = commonService.getMedicalCatgryListDflt();   
		request.setAttribute("medicalCatList", medicalCatList);
		String medHosp=loggedUserHospId;
		String dentalrnt=request.getParameter("dentalredirect");
		request.setAttribute("dentalornt", dentalrnt);
		List<LabelBean> wellnessapplist=null;
		String 	dispname =null;
			if(lStrUserId!=null && !"".equalsIgnoreCase(lStrUserId))
		{
				wellnessapplist=commonService.checkUsrMapped(lStrUserId);
		}
			if(wellnessapplist!=null && wellnessapplist.size()>0)
		{
				dispname=wellnessapplist.get(0).getDISPID();
		}
		if ("openPatRegForm".equalsIgnoreCase(lStrActionVal))
		{
			String cardType = "";
			String cardEmp="";
			 fromDisp = request.getParameter("fromDispnsry");
			 String aisFlagAis=request.getParameter("aisFlagAis");
			 String unitId=request.getParameter("unitId");
			 request.setAttribute("unitId", unitId);
			 String aisDG = request.getParameter("aisDG");
			 /*added by sowmya cr:8193*/
			 String ayushNims = request.getParameter("nimsAyush");
			 if(aisDG!=null && !"".equalsIgnoreCase(aisDG) && "Y".equalsIgnoreCase(aisDG))
			 {
				 List<LabelBean> mftrList=patientService.getAisNames("","");
					List<String> mfd=new ArrayList<String>();
					if(mftrList!=null)
					{
						for(LabelBean lb:mftrList)
						{
							String temp=lb.getID()+"-"+lb.getVALUE();
							mfd.add(temp);
						}
					}
					request.setAttribute("mftList", mfd);
			 }
			 /*added by sowmya cr:8193*/
			 //for nims ayush services.
			 if(ayushNims!=null && !"".equalsIgnoreCase(ayushNims)){
				 request.setAttribute("nimsAyush", ayushNims); 
			 }
			 request.setAttribute("aisDG", aisDG);
			 String aisEmp=request.getParameter("aisEmp");
			 if(aisEmp!=null && "Y".equalsIgnoreCase(aisEmp))
			 {
			 cardEmp="Y";
			 }
			 if(aisFlagAis!=null && "Y".equalsIgnoreCase(aisFlagAis))
			 {
				 cardType="A";
			 }
			 String appntdate= request.getParameter("appntDate");
			 String aisFlag=request.getParameter("aisFlag");
			 if(!"".equalsIgnoreCase(aisFlag) && "Y".equalsIgnoreCase(aisFlag))
				{
					request.setAttribute("aisFlag", aisFlag);
				}	
			 else
			 {
				 request.setAttribute("aisFlag", "N");
			 }
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				patientForm.setFromDisp(fromDisp);
				request.setAttribute("fromDisp", fromDisp);
			}	
			else
			{
				patientForm.setFromDisp("N");
				request.setAttribute("fromDisp", "N");
			}
			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);
			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			session.setAttribute("castesList",castesList);
			 if(!"".equalsIgnoreCase(aisFlag) && "Y".equalsIgnoreCase(aisFlag))
			 {
				 List<LabelBean> relationList=patientService.getRelationsNew();
					session.setAttribute("relationList",relationList);
			 }
			 else
			 {
			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);
			 }
			 if(!"".equalsIgnoreCase(aisFlag) && "Y".equalsIgnoreCase(aisFlag))
			 {
			List<LabelBean> maritalStatusList=commonService.getComboDetailsNew(config.getString("IPOP.MaritalStatusCMBHDRID"));
			session.setAttribute("maritalStatusList",maritalStatusList);
			 }
			 else
			 {
			List<LabelBean> maritalStatusList=commonService.getComboDetails(config.getString("IPOP.MaritalStatusCMBHDRID"));
			session.setAttribute("maritalStatusList",maritalStatusList);
			 }
			List<LabelBean> hospitalList = null;
			 if(!"".equalsIgnoreCase(aisFlag) && !"Y".equalsIgnoreCase(aisFlag))
			 {
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				 hospitalList=patientService.getReferredCenterDtls(lStrUserId,roleId);
				session.setAttribute("hospitalList",hospitalList);
			}
			else
			{	
			 hospitalList=patientService.getHospital(lStrUserId,roleId);
				session.setAttribute("hospitalList",hospitalList);
			}
		}
			String newBornBabyFlag="N";
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			if(aisFlag!=null && !"Y".equalsIgnoreCase(aisFlag))
			{
			if(hospitalList.size()==0)
				return mapping.findForward("noAuthorisation");
			}
			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);
			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));
			if((!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)))
			{
				newBornBabyFlag="Y";
				request.setAttribute("newBornBabyFlag", newBornBabyFlag);
				List<LabelBean> dispSpecList=commonService.getDispSpecialities();
				session.setAttribute("dispSpecList",dispSpecList);
			}
		if(!(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)))
		{
			String hospStatus= patientService.getHospActiveStatus(lStrUserId,roleId);
			if(config.getString("inactive_status")!=null && hospStatus!=null 
					&& config.getString("inactive_status").contains("~"+hospStatus+"~"))
			{
				request.setAttribute("inActiveStatusMsg", config.getString("msg_"+hospStatus));
				request.setAttribute("inActiveStatusFlag", "Y");
			}
		}
       if(mobileAppDispp!=null&&!mobileAppDispp.equalsIgnoreCase("")&&mobileAppDispp.equalsIgnoreCase("Y")){
    	   mobileAppDispp=request.getParameter("mobileApp");
    	   String cardNo=request.getParameter("cardNo");
    	   String patScheme=request.getParameter("patDispScheme");
    	   String specialitType="";
    	   if(cardNo.startsWith("PEN"))
    		   cardType="P";
    	   else if(patScheme!=null&&!patScheme.equalsIgnoreCase("")&&patScheme.equalsIgnoreCase("CD502"))
    		   cardType="J";
    	   else if(patScheme!=null&&!patScheme.equalsIgnoreCase("")&&patScheme.equalsIgnoreCase("CD501"))
    		   cardType="E";
    	   else 
    		   cardType="A";
    	   patientForm.setCardNo(cardNo);
    	   patientForm.setCard_type(cardType);
    	   request.setAttribute("mobileApp",mobileAppDispp );
    	   request.setAttribute("appntdate",appntdate );
    	   specialitType=commonService.getspecialityDisp(cardNo,dispname,appntdate);
    	   patientForm.setSpecialityType(specialitType);
       }
       if(mobileAppDisppNew!=null&&!mobileAppDisppNew.equalsIgnoreCase("")&&mobileAppDisppNew.equalsIgnoreCase("Y")){
    	   mobileAppDispp=request.getParameter("mobileAppAis");
    	   String cardNo=request.getParameter("cardNo");
    	   String serv=request.getParameter("serv");
    	   String servType = "";
    	   String aisType = "";
    	   String patScheme=request.getParameter("patDispScheme");
    	   String relationShip=request.getParameter("relation");
    	   if(serv!=null && !"".equalsIgnoreCase(serv) && ("IAS".equalsIgnoreCase(serv) || "IPS".equalsIgnoreCase(serv) || "IFS".equalsIgnoreCase(serv)))
    	   {
    		   cardType="A";
    	   }
    	   else
    		   cardType="E";
    	   patientForm.setCardNo(cardNo);
    	   patientForm.setCard_type(cardType);
    	   if(patScheme!=null && patScheme!="" && "Y".equalsIgnoreCase(patScheme))
    	   {
    		   servType = "IS";
    	   }
    	   else if(patScheme!=null && patScheme!="" && "N".equalsIgnoreCase(patScheme))
    	   {
    		   servType = "RS";
    	   }
    	   patientForm.setServType(servType);
    	   if(relationShip!=null && relationShip!="" && "Y".equalsIgnoreCase(relationShip))
    	   {
    		   aisType = "R";
    	   }
    	   else if(relationShip!=null && relationShip!="" && "N".equalsIgnoreCase(relationShip))
    	   {
    		   aisType = "S";
    	   }
    	   patientForm.setAisType(aisType);
    	   request.setAttribute("mobileApp",mobileAppDispp );
    	   request.setAttribute("appntdate",appntdate );  
       }
       	if(cardType!=null && cardType.equalsIgnoreCase("A"))
       	{
       		if(aisDG!=null && !"".equalsIgnoreCase(aisDG) && "N".equalsIgnoreCase(aisDG))
			{
       			lStrPageName = "InAisPatientRegistration"; 
			}
       		else if(aisDG!=null && !"".equalsIgnoreCase(aisDG) && "Y".equalsIgnoreCase(aisDG))
       		{
       			lStrPageName = "InAisDirPatientRegistration";
       		}
       	}
    	else if(cardEmp!=null && cardEmp.equalsIgnoreCase("Y"))
       	{
       		lStrPageName = "InPatientEmployee"; 
       	}
       	else 
       	{
       		lStrPageName = "InPatientRegistration"; 
       	}
       	}
		if("retrieveCardDetails".equalsIgnoreCase(lStrActionVal))
		{
			String newBornBabyFlag="N";
			userName = session.getAttribute("userName").toString();
			 String aisFlag=request.getParameter("aisFlag");
			String aisDG= request.getParameter("aisDG");
			String checkFlag = request.getParameter("checkFlag");
			List<PatientVO> lhsRegDependentsList = null;
			String ayushFlag = request.getParameter("ayush");
			if(ayushFlag!=null && !"".equalsIgnoreCase(ayushFlag))
			{
				request.setAttribute("nimsAyush", ayushFlag);
			}
			if(aisDG!=null && !"".equalsIgnoreCase(aisDG) && "N".equalsIgnoreCase(aisDG))
			{
				request.setAttribute("disabled", "Y");
			}
			else
				request.setAttribute("disabled", "N");
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			 String unitId=request.getParameter("unitId");
			 request.setAttribute("unitId", unitId);
			PatientVO patientVo=new PatientVO();
			String cardNo=request.getParameter("cardNo");
			String aisFl=request.getParameter("aisFl");
			String aisTy = request.getParameter("aisTy");
			String type = request.getParameter("serviceTyp");
			String typeFlag= request.getParameter("service");
			if(aisTy!=null && !"".equalsIgnoreCase(aisTy) && "S".equalsIgnoreCase(aisTy))
			{
				request.setAttribute("aisDis", "Y");
			}
			patientVo.setDependentFlag(aisTy);
			if(aisFl!=null && !"".equalsIgnoreCase(aisFl) && "Y".equalsIgnoreCase(aisFl))
			{
				patientVo.setEmpais("Y");
			}
			 fromDisp=request.getParameter("fromDisp");
			request.setAttribute("fromDisp", fromDisp);
			if((!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)))
			{
				newBornBabyFlag="Y";
				request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			}
			if(aisFlag!=null && !"".equalsIgnoreCase(aisFlag) && "Y".equalsIgnoreCase(aisFlag))
				patientVo.setAisFlag("Y");
			patientForm.setFromDisp(fromDisp);
			patientVo.setCardNo(cardNo);
			if(unitId!=null && !"".equalsIgnoreCase(unitId))
			{
				patientVo.setUnitID(unitId);
			}
			patientVo.setFromDisp(fromDisp);
			if(aisFl!=null && "Y".equalsIgnoreCase(aisFl))
			{
				patientVo.setCardType("E");
				request.setAttribute("aisFl", aisFl);
			}
			else
			{
			patientVo.setCardType(patientForm.getCard_type());
			}
			patientVo.setCheckFlag(checkFlag);
			patientVo.setSchemeId(schemeId);
			List<LabelBean> hospitalList=new ArrayList<LabelBean>();
			if(((List<LabelBean>)session.getAttribute("hospitalList"))!=null)
				hospitalList=(List<LabelBean>)session.getAttribute("hospitalList");
			else
				hospitalList=patientService.getHospital(lStrUserId,roleId);
			patientForm.setTgGovPatCond("N");
			if(hospitalList!=null)
				{
					if(hospitalList.size()>0)
						{
							if(hospitalList.get(0).getID()!=null && !"".equalsIgnoreCase(hospitalList.get(0).getID()))
								{
									patientVo.setHospitalCode(hospitalList.get(0).getID());
								}
						}
				}
				List<LabelBean> pastVisitList=patientService.getPatientPastVisitDetails(cardNo);
								String msgNew="";
				for(LabelBean lb:pastVisitList)
				{
					msgNew=msgNew+lb.getID()+"~"+lb.getVALUE()+"$";
				}
				request.setAttribute("pastVisitListNew", msgNew);
				request.setAttribute("pastVisitList", pastVisitList);
			if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase("A"))
			{
				String mobileAppAisNew=request.getParameter("mobileAppAis");
				patientVo.setMobileAppReg(mobileAppAisNew);
				if(type!=null && !"".equalsIgnoreCase(type) && "IS".equalsIgnoreCase(type))
				{
					patientVo.setTypeDirAis("Y");
				}
				else if(type!=null && !"".equalsIgnoreCase(type) && "RS".equalsIgnoreCase(type))
				{
					patientVo.setTypeDirAis("N");
				}
				patientVo.setTypeDirAisFlag(typeFlag);
				patientVO=patientService.retrieveAisCardDetails(patientVo);
				//sowmya added for dependents list.
				lhsRegDependentsList = patientVO.getDependentList();
				if(lhsRegDependentsList != null && lhsRegDependentsList.size() > 0){
					request.setAttribute("lhsDependentList", lhsRegDependentsList);
				}
				if(patientVO.getFirstName()==null || "".equalsIgnoreCase(patientVO.getFirstName()))
				{
					request.setAttribute("errFlg", "Y");
				}
				List<LabelBean> aisDepList=patientService.getAisDepNames(cardNo);
				List<String> adl=new ArrayList<String>();
				if(aisDepList!=null)
				{
					for(LabelBean lb:aisDepList)
					{
						String temp=lb.getID();
						adl.add(temp);
					}
				}
				request.setAttribute("aisDepList", adl);
			}
			else
			{	
				patientVO=patientService.retrieveCardDetails(patientVo);
			}
			if(patientVO!=null)
			{
				if(patientVO.getVillageCode()!=null )
				{
					LabelBean labelBeanVillage=new LabelBean();
					labelBeanVillage.setID(patientVO.getVillageCode());
					labelBeanVillage=commonService.getNewLocations(labelBeanVillage);
					if(labelBeanVillage!=null)
					{
						patientVO.setDistrictCode(labelBeanVillage.getNEW_DIST());					
						patientVO.setMandalCode(labelBeanVillage.getNEW_MAND());
						patientVO.setVillageCode(labelBeanVillage.getNEW_VILLAGE());
					}
				}
			}
			patientForm.setTelephonicId(patientForm.getTelephonicId());
			patientForm.setTelephonicReg(patientForm.getTelephonicReg());
			if(patientVO!=null && !(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_cont_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_delist_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_deempan_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("scheme_hosp_cont_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("invalid_hosp_scheme").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("invalid_born_baby").equalsIgnoreCase(patientVO.getMsg()))
							    && !(config.getString("invalid_state_remarks").equalsIgnoreCase(patientVO.getMsg()))
							   && !(config.getString("invalid_born_baby_Scheme").equalsIgnoreCase(patientVO.getMsg()))
							   && !("DIS".equalsIgnoreCase(patientVO.getMsg()))
							   )
			{
				patientForm.setCardIssueDt(patientVO.getCardIssueDt());
				patientForm.setEmpCode(patientVO.getEmpCode());
				patientForm.setCardNo(cardNo);
				patientForm.setCard_type(patientForm.getCard_type());
				patientForm.setHead(patientForm.getHead());
				patientForm.setDisableFlag(config.getString("NFlag"));
				patientForm.setAddrEnable(config.getString("YFlag"));
				patientForm.setErrMsg("");
				if(patientForm.getCard_type()!=null && !patientForm.getCard_type().equalsIgnoreCase("A"))
				{
				String dob[]=patientVO.getDateOfBirth().substring(0,10).split("-");
				String finalDob=dob[2]+"-"+dob[1]+"-"+dob[0];
				patientForm.setDateOfBirth(finalDob);
				}
				patientForm.setGender(patientVO.getGender());
				patientForm.setFname(patientVO.getFirstName());
				patientForm.setFname1(patientVO.getFirstName());
				patientForm.setEkycFlag(patientVO.getEkycFlag());//Chandana - 8133 - for abha
				patientForm.setAbhaId(patientVO.getAbhaId());//Chandana - 8133 - for abha id
				patientForm.setUserName(userName);//Chandana - 8133
				patientForm.setRelation(patientVO.getRelation());
				patientForm.setCaste(patientVO.getCaste());
				patientForm.setContactno(patientVO.getContactNo());
				patientForm.setMaritalStatus(patientVO.getMaritalStatus());
				patientForm.setOccupation(patientVO.getOccupationCd());
				patientForm.setSlab(patientVO.getSlab());
				patientForm.setSerType(patientVO.getServiceType());
				patientForm.setScheme(patientVO.getSchemeId());
				patientForm.setTelScheme(patientVO.getSchemeId());
				patientForm.setPrc(patientVO.getPrc());
				patientForm.setPayScale(patientVO.getPayScale());
				patientForm.setDept(patientVO.getDept());
				patientForm.setDeptHod(patientVO.getDeptHod());
				patientForm.setPostDist(patientVO.getPostDist());
				patientForm.setPostDDOcode(patientVO.getPostDDOcode());
				patientForm.setServDsgn(patientVO.getServDsgn());
				patientForm.setDdoOffUnit(patientVO.getDdoOffUnit());
				patientForm.setCurrPay(patientVO.getCurrPay());
				patientForm.setDesignation(patientVO.getDesignation());
				patientForm.setAadharID(patientVO.getAadharID());
				patientForm.setAadharEID(patientVO.getAadharEID());
				request.setAttribute("scheme", patientVO.getSchemeId());
				if(patientVO.geteMailId()!=null && !patientVO.geteMailId().equals(""))
				{
					patientForm.seteMailId(patientVO.geteMailId());
				}
				if(patientVO.getCheckType()!=null && !patientVO.getCheckType().equals(""))
				{
					if(patientVO.getCheckType().equals("A"))
					{
						patientForm.setAyush("F");
					}
					else
						patientForm.setAyush(patientVO.getCheckType());
				}
				if(patientVO.getType1()!=null && !patientVO.getType1().equals(""))
				{
					patientForm.setAyushSubg(patientVO.getType1());
				}
				if(patientVO.getType2()!=null && !patientVO.getType2().equals(""))
				{
					if(patientVO.getType2().equalsIgnoreCase("P1") || patientVO.getType2().equalsIgnoreCase("P2") || patientVO.getType2().equalsIgnoreCase("P3"))
					{
						patientForm.setDosage(patientVO.getType2());
					}
					request.setAttribute("checkboxValue", patientVO.getType2());
				}
				if(patientVO.getType3()!=null && !patientVO.getType3().equals(""))
				{
					patientForm.setRelation(patientVO.getType3());
				}
				if(patientVO.getPhoto()!=null)
				{
					try
					{
						File photo = new File(patientVO.getPhoto());
						fis = new FileInputStream(photo);
						dis= new DataInputStream(fis);
						bytes = new byte[dis.available()];
						fis.read(bytes);
						String lStrContentType=null;
						lStrContentType="image/jpg";
						session.setAttribute("ContentType", lStrContentType);
						session.setAttribute("File", bytes);
						fis.close();
						dis.close();
						patientForm.setPhotoUrl(patientVO.getPhoto());
					}
					catch(Exception e)
					{
						e.printStackTrace();					}
				}
				patientForm.setHno(patientVO.getAddr1());
				patientForm.setStreet(patientVO.getAddr2());
				List<LabelBean> districtList=null;
				if(patientVO.getState()!=null)
				{
					patientForm.setState(patientVO.getState());
					districtList=commonService.getAsrimLocations(distHdrId, patientVO.getState());
				}
				request.setAttribute("districtList",districtList);
				patientForm.setDistrict(patientVO.getDistrictCode());
				patientForm.setMdl_mcl(patientVO.getMdl_mpl());
				if(patientVO.getMdl_mpl()!=null && patientVO.getMdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> mdlList=commonService.getAsrimLocations(lStrMandalHdrId, patientVO.getDistrictCode());
					request.setAttribute("mdlList", mdlList);
					patientForm.setMandal(patientVO.getMandalCode());
					List<LabelBean> villList=commonService.getAsrimLocations(lStrVillageHdrId, patientVO.getMandalCode());
					request.setAttribute("villList", villList);
				}
				else if(patientVO.getMdl_mpl()!=null && patientVO.getMdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> mplList=commonService.getAsrimLocations(lStrMunicipalHdrId, patientVO.getDistrictCode());
					request.setAttribute("mplList", mplList);
					patientForm.setMunicipality(patientVO.getMandalCode());
					List<LabelBean> villList=commonService.getAsrimLocations(lStrMplVillageHdrId, patientVO.getMandalCode());
					request.setAttribute("villList", villList);
				}
				patientForm.setVillage(patientVO.getVillageCode());
				patientForm.setDtTime(serverDt);
				patientForm.setTgGovPatCond("N");
				if(hospitalList!=null)
					{
						if(hospitalList.size()>0)
							{
								if(hospitalList.get(0)!=null)
									{
										//Checking for only Government Hospitals and for Patient whose Scheme is TG
										if(hospitalList.get(0).getID()!=null && !"".equalsIgnoreCase(hospitalList.get(0).getID())
												&& hospitalList.get(0).getNATURE()!=null && "G".equalsIgnoreCase(hospitalList.get(0).getNATURE())
												&& patientVO.getSchemeId()!=null && !"".equalsIgnoreCase(patientVO.getSchemeId())
												&& config.getString("TG").equalsIgnoreCase(patientVO.getSchemeId()))
											{
												patientForm.setTgGovPatCond("Y");
												if(bioRegFlag!=null && !"".equalsIgnoreCase(bioRegFlag) 
														 && "Y".equalsIgnoreCase(bioRegFlag))
													{
														List<LabelBean> bioFingerOptions=new ArrayList<LabelBean>();
														if(config.getString("BioMetricFingersDropDown")!=null &&
																!"".equalsIgnoreCase(config.getString("BioMetricFingersDropDown")))
															{
																String[] bioFingerLst=config.getString("BioMetricFingersDropDown").split("@");
																for(String locFinger : bioFingerLst)
																	{
																		String[] values=locFinger.split("~"); 
																		LabelBean localBean=new LabelBean();
																		localBean.setID(values[0]);
																		localBean.setVALUE(values[1]);
																		bioFingerOptions.add(localBean);
																	}
																request.setAttribute( "bioFingerOptions" , bioFingerOptions );
															}
													}	
											}
									}
							}
					}	
			}
			else if(patientVO!=null &&(config.getString("inactivate_card_death_remarks").equalsIgnoreCase(patientVO.getMsg())
					   || (config.getString("scheme_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_cont_hosp_susp_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_delist_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_deempan_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("scheme_hosp_cont_susppay_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("invalid_hosp_scheme").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("invalid_state_remarks").equalsIgnoreCase(patientVO.getMsg()))
					   || (config.getString("invalid_born_baby_Scheme").equalsIgnoreCase(patientVO.getMsg()))
					   || ("DIS".equalsIgnoreCase(patientVO.getMsg())))
					   )
			{
				patientForm.setDisableFlag(config.getString("YFlag"));
				patientForm.setCardNo("");
				if(patientVO.getMsg()!=null && !"".equalsIgnoreCase(patientVO.getMsg()) && !"DIS".equalsIgnoreCase(patientVO.getMsg()))
				patientForm.setCard_type("");
				patientForm.setHead("");
				patientForm.setDtTime(serverDt);
				if(patientVO.getMsg()!=null && config.getString("inactivate_card_death_remarks").equalsIgnoreCase(patientVO.getMsg()))	
					patientForm.setErrMsg(config.getString("inactivate_card_death_remarks"));
				if(patientVO.getMsg()!=null && config.getString("invalid_state_remarks").equalsIgnoreCase(patientVO.getMsg()))
					patientForm.setErrMsg(config.getString("invalid_state_remarks"));
				else if(patientVO.getMsg()!=null && !"".equalsIgnoreCase(patientVO.getMsg()) && "DIS".equalsIgnoreCase(patientVO.getMsg()))	
				{
					patientForm.setErrMsg("MHC can be availed only once in a year");
					request.setAttribute("mhcView", "Y");
				}
				else 
					patientForm.setErrMsg(patientVO.getMsg());
			}
			else
			{
				patientForm.setDisableFlag(config.getString("YFlag"));
				patientForm.setCardNo("");
				patientForm.setHead("");
				patientForm.setDtTime(serverDt);
				patientForm.setErrMsg(config.getString("err.InvalidCardNo"));
			}
			 mobileAppDispp=request.getParameter("mobileApp");
			 String appntdate= request.getParameter("appntdate");
		 if(mobileAppDispp!=null&&!mobileAppDispp.equalsIgnoreCase("")&&mobileAppDispp.equalsIgnoreCase("Y")){
    	   String specialitType="";
    	   request.setAttribute("mobileApp",mobileAppDispp );
    	   request.setAttribute("appntdate",appntdate );
    	   request.setAttribute("triggerFlag","N" );
    	   specialitType=commonService.getspecialityDisp(cardNo,dispname,appntdate);
    	   patientForm.setSpecialityType(specialitType);
       } 
		 if(aisFlag!=null&&!aisFlag.equalsIgnoreCase("") && aisFlag.equalsIgnoreCase("Y")){
	    	   request.setAttribute("mobileApp",mobileAppDispp );
	    	   request.setAttribute("appntdate",appntdate );
	    	   request.setAttribute("triggerFlag","N" );
	    	   request.setAttribute("aisFlag","Y" );
	       } 
			if(session.getAttribute("userState").toString()!=null)
			{
				schemeId=session.getAttribute("userState").toString();
			}
			request.setAttribute("execScheme", schemeId);
			if(aisFl!=null && "Y".equalsIgnoreCase(aisFl))
			{
				lStrPageName = "InPatientEmployee";
			}
			else
			{
			if(request.getParameter("SelOper")!=null && request.getParameter("SelOper").equalsIgnoreCase("TELE"))
			{
				lStrPageName = "telephonicPatientEntry";
			}
			else if(patientForm.getCard_type()!=null &&  !"".equalsIgnoreCase(patientForm.getCard_type()) && !patientForm.getCard_type().equalsIgnoreCase("A"))
			{	
				lStrPageName = "InPatientRegistration";
			}
			else
			{
				if(aisDG!=null && !"".equalsIgnoreCase(aisDG) && "N".equalsIgnoreCase(aisDG))
				{
					lStrPageName = "InAisPatientRegistration";
				}
				else if(aisDG!=null && !"".equalsIgnoreCase(aisDG) && "Y".equalsIgnoreCase(aisDG))
				{
					lStrPageName = "InAisDirPatientRegistration";
				}
			}
			}
		}
		if (lStrActionVal!= null &&  "cancelPatientReg".equalsIgnoreCase(lStrActionVal))
		{
			String cardNo = request.getParameter("cardNo");
			String aisRemarks = request.getParameter("aisRemarks");
			PatientVO patVo=new PatientVO();
			if(cardNo != null && !"".equalsIgnoreCase(cardNo))
			patVo.setCardNo(cardNo);
			patVo.setLstUpdUsr(lStrUserId);
			patVo.setGicRemarks(aisRemarks);
			String msg = patientService.cancelPatientReg(patVo);
			request.setAttribute("saveMsg", msg);
			lStrActionVal="viewAppointentsEmp";
		} 
		if("viewAppointentsEmp".equalsIgnoreCase(lStrActionVal))
		{
		    int pageNo=1,rowsPerPage=10;
 			int startIndex=1,maxResults=0;
 		   //String flag= request.getParameter("from");
 			String fromDate=request.getParameter("fromDate");
 			String toDate=request.getParameter("toDate");
 			patientForm.setCurrFromDate(fromDate);
 			patientForm.setCurrToDate(toDate);
 			List<LabelBean> appointmentsList= new ArrayList<LabelBean>();;
				if(request.getParameter("pageId")!=null && !request.getParameter("pageId").equals("")){
					pageNo=Integer.parseInt(request.getParameter("pageId"));
				}
				if(request.getParameter("startIndex")!=null)
					startIndex=Integer.parseInt(request.getParameter("startIndex"));
				if(request.getParameter("endIndex")!=null)
					maxResults=Integer.parseInt(request.getParameter("endIndex"));
				if(pageNo==0)
				{
					request.setAttribute("selectedPage",pageNo+1);
				}
				else
				{
					request.setAttribute("selectedPage",pageNo);
				}
				if(pageNo>0)
				{
					startIndex = getStartIndex(rowsPerPage,pageNo);
				}
					 appointmentsList=commonService.getappNims(startIndex,maxResults,pageNo,fromDate,toDate);
			 if(appointmentsList!=null &&appointmentsList.size()>0)
           {
           	request.setAttribute("totalPages",appointmentsList.get(0).getTotalPages());
					request.setAttribute("totalRecords",appointmentsList.get(0).getTotalRecords());
					request.setAttribute("pageId",appointmentsList.get(0).getPageId());
					request.setAttribute("endIndex",appointmentsList.get(0).getMaxresults());
					request.setAttribute("startIndex",appointmentsList.get(0).getStrtIndex());
					request.setAttribute("endResults",appointmentsList.get(0).getStrtIndex()+appointmentsList.size());  
			     			request.setAttribute("appointmentsList", appointmentsList);
			     			patientForm.setAppointmentsList(appointmentsList);
			}
			else
			{
				request.setAttribute("totalPages","1");
				request.setAttribute("totalRecords","0");
				request.setAttribute("pageId",pageNo);
				request.setAttribute("endIndex",startIndex);
				request.setAttribute("startIndex",startIndex);
				request.setAttribute("endResults","0"); 
				request.setAttribute("appointmentsList", "N");
			}
			return mapping.findForward("appointmentsReportNew");
		}
		if ("verifyPatientBiometrics".equalsIgnoreCase(lStrActionVal))
			{
				String patientId=request.getParameter("patientId"),biometricValue=request.getParameter("biometricValue");
				boolean ajaxMsg=false;
				if(patientId!=null && !"".equalsIgnoreCase(patientId) && biometricValue!=null 
						&& !"".equalsIgnoreCase(biometricValue))
					{
						ajaxMsg	=patientService.verifyPatientBiometrics(patientId,biometricValue);
					}
				request.setAttribute("AjaxMessage", ajaxMsg);
				lStrPageName="ajaxResult"; 
			}
		/*
		 * End of Code added by Srikalyan for Biometric Validation
		 */
		if ("registerPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			String caseStartTime = sds.format(new Date().getTime());
			patientVO=new PatientVO();
			String tgGovPatCond=request.getParameter("tgGovPatCond");
			String unitId=request.getParameter("unitId");
			patientVO.setEntryId(unitId);
			String aisFlagNe = request.getParameter("aisflagNe");
			String aisSubmit = request.getParameter("aisSubmit");
			String aisSubmitDir = request.getParameter("aisSubmitDir");
			String empDirect = request.getParameter("empDirect");
			String aisTy = request.getParameter("aisTy");
			String serviceTyp = request.getParameter("serviceTyp");
			/*added by sowmya for ayush separation*/
			String nimsAyush = request.getParameter("nimsAyush");
			if(aisSubmit!=null && "Y".equalsIgnoreCase(aisSubmit))
			{
				request.setAttribute("aisSubmit", "Y");
			}
			if(aisSubmitDir!=null && "Y".equalsIgnoreCase(aisSubmitDir))
			{
				request.setAttribute("aisSubmitDir", "Y");
			}
			else
				request.setAttribute("aisSubmitDir", "N");
			if(empDirect!=null && "Y".equalsIgnoreCase(empDirect))
			{
				request.setAttribute("empDirect", "Y");
			}
			else
				request.setAttribute("empDirect", "N");
			if(patientForm.getHospNims()!=null && "NI".equalsIgnoreCase(patientForm.getHospNims()) && aisFlagNe!=null && !"".equalsIgnoreCase(aisFlagNe) && "N".equalsIgnoreCase(aisFlagNe))
			{
				patientVO.setMhcScheduler("Y");
			}
			else
			{
				patientVO.setMhcScheduler("N");
			}
			//Dispensary Changes by sravan
			 fromDisp=request.getParameter("fromDisp");
			request.setAttribute("fromDisp", fromDisp);
			if(tgGovPatCond!=null && !"".equalsIgnoreCase(tgGovPatCond)
					&& "Y".equalsIgnoreCase(tgGovPatCond))
				{
					patientVO.setTgGovPatCond(tgGovPatCond);
					if(bioRegFlag!=null && !"".equalsIgnoreCase(bioRegFlag) 
							 && "Y".equalsIgnoreCase(bioRegFlag))
						{
							patientVO.setBioRegFlag(bioRegFlag);
							patientVO.setFingerPrint(patientForm.getBiometricValue());
							patientVO.setFingerCaptured(patientForm.getBioFinger());
							patientVO.setBioHand(patientForm.getBioHand());
						}
				}
			String newBornBabyFlag="N";
			newBornBabyFlag=patientService.checkNewBornCond(lStrUserId);
			request.setAttribute("newBornBabyFlag", newBornBabyFlag);
			String wapNo=request.getParameter("wapNo");
			String familyNo=request.getParameter("familyNo");
			mobileAppDispp=request.getParameter("mobileApp");
			String appntdate= request.getParameter("appntdate");
			String ayushID = request.getParameter("ayushID");
			String liNextVal="";
			String userId=null;
			int phaseRenewal=0;
			Date date = new Date();
			String crtDate=sdfw.format(date);
			Date crtDt = sdfw.parse((sdfw.format(date)));
			userId=session.getAttribute("EmpID").toString(); 
			GLOGGER.info("Card No"+wapNo+"/"+familyNo) ;
			liNextVal = patientService.getSequence("PATIENT_ID");
			patientVO.setFromDisp(fromDisp);
			patientVO.setPatientId(liNextVal);
			patientVO.setProcessInstanceId("1");
			patientVO.setCrtDt(crtDate);
			patientVO.setCrtUsr(userId);
			//Chandana - 8443 - Added the below code for abha numbers, using abha number getting the main card number either journalist or emp or pensioner
			if (patientForm.getCard_type().equalsIgnoreCase("AB")) {
			    String cardDetails = patientService.getCardDtlsForAbhaSearch(patientForm.getCardNo());
			    if (cardDetails != null && cardDetails.contains("~")) {
			        String[] parts = cardDetails.split("~");
			        String cardNum = parts[0];
			        String type = parts[1];
			        patientForm.setCardNo(cardNum);
			        patientForm.setCard_type(type);
			        patientVO.setRationCard(cardNum);
			        patientVO.setAbhaId(wapNo);
			    }
			}
			else{
				if(aisFlagNe!=null && aisFlagNe.equalsIgnoreCase("Y"))
					patientVO.setRationCard((wapNo).toUpperCase());
				else
					patientVO.setRationCard((wapNo+"/"+familyNo).toUpperCase());
			}
			patientVO.setCardType(patientForm.getCard_type());
			if( (!"".equalsIgnoreCase(fromDisp) && "N".equalsIgnoreCase(fromDisp)) || (fromDisp.length()==0))
			{
				patientVO.setNabhHosp("Y");
			}
			if(patientForm.getCard_type()!=null)
				{
					if(patientForm.getCard_type().equalsIgnoreCase("E") || patientForm.getCard_type().equalsIgnoreCase("P")
							|| patientForm.getCard_type().equalsIgnoreCase("NB"))
						patientVO.setPatientScheme(config.getString("Scheme.EHS"));
					else if(patientForm.getCard_type().equalsIgnoreCase("J") || patientForm.getCard_type().equalsIgnoreCase("R"))
						patientVO.setPatientScheme(config.getString("Scheme.JHS"));
					else if(patientForm.getCard_type().equalsIgnoreCase("A"))
						patientVO.setPatientScheme(config.getString("Scheme.AIS"));
				}
			patientVO.setCardIssueDt(patientForm.getCardIssueDt());
			patientVO.setHospNims(patientForm.getHospNims());
			patientVO.setFirstName(patientForm.getFname());
			if(aisTy!=null && !"".equalsIgnoreCase(aisTy) && "S".equalsIgnoreCase(aisTy))
			{
				patientVO.setFirstName(patientForm.getFname());
				patientVO.setRelationAis("N");
				patientVO.setRelation("0");
			}
			else if(aisTy!=null && !"".equalsIgnoreCase(aisTy) && "R".equalsIgnoreCase(aisTy))
			{
				if(patientForm.getFname1()!=null)
				patientVO.setFirstName(patientForm.getFname1());
				else
				patientVO.setFirstName(patientForm.getFname());	
				patientVO.setRelationAis("Y");
				patientVO.setRelation(patientForm.getRelation());
			}
			else
			{
				patientVO.setRelationAis("NA");
				patientVO.setRelation(patientForm.getRelation());
			}
			if(serviceTyp!=null && !"".equalsIgnoreCase(serviceTyp) && "RS".equalsIgnoreCase(serviceTyp))
			{
				patientVO.setServiceAis("R");
			}
			else if(serviceTyp!=null && !"".equalsIgnoreCase(serviceTyp) && "IS".equalsIgnoreCase(serviceTyp))
			{
				patientVO.setServiceAis("I");
			}
			if(patientForm.getHospNims()!=null && "NI".equalsIgnoreCase(patientForm.getHospNims()))
			{
				request.setAttribute("aisFlagNew", "Y");
			}
			else
			{
				request.setAttribute("aisFlagNew", "N");
			}
			patientVO.setDosage(patientForm.getDosage());
			patientVO.setDateOfBirth(patientForm.getDateOfBirth());
			patientVO.setGender(patientForm.getGender());
			if(patientForm.getCard_type()!=null && patientForm.getCard_type()!="")
			{
			if((!familyNo.equalsIgnoreCase("01") && patientForm.getHead()==null)||
					patientForm.getCard_type().equalsIgnoreCase("NB"))
			{
				patientVO.setFamilyHead(config.getString("NFlag"));
			}
			else if(!patientForm.getCard_type().equalsIgnoreCase("NB"))
			{
				patientVO.setFamilyHead(config.getString("YFlag"));
				patientVO.setChild_yn(config.getString("NFlag"));
			}
		}
			String[] age=patientForm.getAgeString().split("~");
			patientVO.setEmpCode(patientForm.getEmpCode());
			if(!aisFlagNe.equalsIgnoreCase("Y"))
			{
			patientVO.setAge(age[0]);
			patientVO.setAgeMonths(age[1]);
			patientVO.setAgeDays(age[2]);
			}
			patientVO.setCaste(patientForm.getCaste());
			patientVO.setMaritalStatus(patientForm.getMaritalStatus());
			patientVO.setOccupationCd(patientForm.getOccupation());
			patientVO.setContactNo(patientForm.getContactno());
			patientVO.seteMailId(patientForm.geteMailId());
			patientVO.setSlab(patientForm.getSlab());
			patientVO.setServiceType(patientForm.getSerType());
			patientVO.setAyushID(ayushID);
			patientVO.seteMailId(patientForm.geteMailId());
			patientVO.setAddr1(patientForm.getHno());
			patientVO.setAddr2(patientForm.getStreet());
			patientVO.setVillageCode(patientForm.getVillage());
			patientVO.setState(patientForm.getState());
			patientVO.setDistrictCode(patientForm.getDistrict());
			patientVO.setMdl_mpl(patientForm.getMdl_mcl());
			patientVO.setPrc(patientForm.getPrc());
			patientVO.setPayScale(patientForm.getPayScale());
			patientVO.setDept(patientForm.getDept());
			patientVO.setDeptHod(patientForm.getDeptHod());
			patientVO.setPostDist(patientForm.getPostDist());
			patientVO.setPostDDOcode(patientForm.getPostDDOcode());
			patientVO.setServDsgn(patientForm.getServDsgn());
			patientVO.setDdoOffUnit(patientForm.getDdoOffUnit());
			patientVO.setCurrPay(patientForm.getCurrPay());
			patientVO.setDesignation(patientForm.getDesignation());
			patientVO.setAadharID(patientForm.getAadharID());
			patientVO.setAadharEID(patientForm.getAadharEID());
			patientVO.setMobileApp(mobileAppDispp);
			patientVO.setAppDate(appntdate);
			if(aisFlagNe!=null && !aisFlagNe.equalsIgnoreCase("Y"))
			{
			if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
			{
				patientVO.setMandalCode(patientForm.getMandal());
			}
			else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
			{
				patientVO.setMandalCode(patientForm.getMunicipality());
			}
			}
			patientVO.setPincode(patientForm.getPin());
			patientVO.setSrcRegistration(config.getString("PatientIPOP.Source"));
			//start sameaddress check
			if(patientForm.getCommCheck()==null)
			{
				patientVO.setPermAddr1(patientForm.getComm_hno());
				patientVO.setPermAddr2(patientForm.getComm_street());
				patientVO.setC_pin_code(patientForm.getComm_pin());
				patientVO.setC_state(patientForm.getComm_state());
				patientVO.setC_district_code(patientForm.getComm_dist());
				patientVO.setC_mdl_mpl(patientForm.getComm_mdl_mcl());
				if(aisFlagNe!=null && !aisFlagNe.equalsIgnoreCase("Y"))
				{
				if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_mandal());
				}
				else if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_municipality());
				}
				}
				patientVO.setC_village_code(patientForm.getComm_village());	
			}
			else
			{
				patientVO.setPermAddr1(patientForm.getHno());
				patientVO.setPermAddr2(patientForm.getStreet());
				patientVO.setC_pin_code(patientForm.getPin());
				patientVO.setC_state(patientForm.getState());
				patientVO.setC_district_code(patientForm.getDistrict());
				patientVO.setC_mdl_mpl(patientForm.getMdl_mcl());
				if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getMandal());
				}
				else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getMunicipality());
				}
				patientVO.setC_village_code(patientForm.getVillage());	
			}
			//end sameaddress check
			patientVO.setPatient_ipop(config.getString("PatientIPOP.RegisterStatus"));
			//phaseRenewal=commonService.getPhaseId(patientForm.getDistrict());
			phaseRenewal=0;
			patientVO.setPhaseId(phaseRenewal+"");
			patientVO.setRenewal(config.getString("PatientIPOP.Renewal"));
			//patientVO.setSchemeId(config.getString("PatientIPOP.SchemeId"));
			patientVO.setSchemeId(patientForm.getScheme());
			patientVO.setCid(config.getString("PatientIPOP.Cid"));
			if(patientForm.getCard_type()!=null && patientForm.getCard_type()!="")
			{
			if(patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Emp")))
				patientVO.setEmployeeRenewal(config.getString("PatientIPOP.Renewal"));
			else if(patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Pen")))
				patientVO.setPensionerRenewal(config.getString("PatientIPOP.Renewal"));
			}
			patientVO.setRegHospId(patientForm.getHospitalId());
			patientVO.setRegHospDt(crtDate);
			if(!patientForm.getTelephonicId().equals(""))
			{
				patientVO.setTelephonicId(patientForm.getTelephonicId());
				patientVO.setRegState("Telephonic Registration");
			}
			List<SpecialityVO> specList = new ArrayList<SpecialityVO>();
			if (patientForm.getSpecialities() != null
					&& !patientForm.getSpecialities().equalsIgnoreCase("")) {
				String s = patientForm.getSpecialities().substring(0,
						patientForm.getSpecialities().length() - 1);
				String[] specialities = s.split("@");
				SpecialityVO specVO = null;
				for (int z = 0; z < specialities.length; z++) {
					String speciality = specialities[z];
					String[] specialityValues = speciality.split("~");
					specVO = new SpecialityVO();
					  if (!"".equalsIgnoreCase(fromDisp) &&
					  "Y".equalsIgnoreCase(fromDisp)) {
					if (specialityValues.length > 0) {
						if (specialityValues.length == 2) {
							specVO.setSpecialityType(specialityValues[0]);
							specVO.setRoomNo(specialityValues[1]);
						}
						if (specialityValues.length == 1) {
							specVO.setSpecialityType(specialityValues[0]);
						}
					}
					 } 
					specList.add(specVO);
				}
			}
			patientVO.setSpecialities(specList);
			patientService.saveDocSpecialities(patientVO);
			patientVO.setSpecialityType(patientForm.getSpecialityType());
			patientVO.setRoomNo(patientForm.getRoomNo());
			patientVO.setTokenNo(patientForm.getTokenNo());
			try{
				int rowsInserted=patientService.registerPatient(patientVO);
				String caseEndTime = sds.format(new Date().getTime());
				if(rowsInserted==0)
				{
					GLOGGER.info("Patient cannot be registered");
					lStrPageName="failure";
				}
				else
				{
					if(patientForm.getTelephonicId() != null && !"".equalsIgnoreCase(patientForm.getTelephonicId()))
						loggingService.logTime("TelephonicRegistration", patientVO.getPatientId(), caseStartTime, caseEndTime, lStrUserId, "EHS_OperationsNABH");
					else
					loggingService.logTime("DirectPatientRegistration", patientVO.getPatientId(), caseStartTime, caseEndTime, lStrUserId, "EHS_OperationsNABH");
					patientForm.setPatientNo(liNextVal);             
					if(!"".equalsIgnoreCase(patientVO.getFromDisp()) && "Y".equalsIgnoreCase(patientVO.getFromDisp()))	
					{
						PatientVO patientVOnew = new PatientVO();
						patientVOnew.setPatientId(patientVO.getPatientId());
						patientVOnew.setFromDisp(patientVO.getFromDisp());
						patientVOnew.setCardNo(patientVO.getRationCard());
						patientVOnew.setContactNo(patientVO.getContactNo());
						patientVOnew.setCrtDt(patientVO.getCrtDt());
						patientVOnew.setCrtUsr(patientVO.getCrtUsr());
						patientVOnew.setUpdGenAttachmentsList(new ArrayList<AttachmentVO>());
						patientVOnew.setGenAttachmentsList(new ArrayList<AttachmentVO>());
						patientVOnew.setPersonalHistory(null);
						patientVOnew.setPersonalHistVal("");
						patientVOnew.setExamFndsVal("");
						patientVOnew.setSchemeId(patientVO.getSchemeId());
						patientVOnew.setPatientScheme(patientVO.getPatientScheme());
						patientVOnew.setCheckType("SaveDTRS");
						patientVOnew.setSaveFlag("Yes");
						patientVOnew.setPatientType("OP");
						patientService.saveCaseDetails(patientVOnew);
					}
					if(aisFlagNe!=null && !aisFlagNe.equalsIgnoreCase("Y"))
					{
					if("true".equalsIgnoreCase(config.getString("SmsRequired")))
					{
						if(patientVO.getContactNo()!=null && !patientVO.getContactNo().equals(""))
						{
							smsNextVal = patientService.getSequence("EHF_DISP_DRUG_SEQ");
							PatientSmsVO patientSmsVO=new PatientSmsVO();
							patientSmsVO.setSerialNo(Long.parseLong(smsNextVal));
							patientSmsVO.setPhoneNo(patientVO.getContactNo());
							if(fromDisp.equalsIgnoreCase("Y"))
								patientSmsVO.setSmsText("Dear "+patientVO.getFirstName().trim()+" , You have been registered as a Patient in "+patientService.getDispName(patientForm.getHospitalId())+" and it is pending with doctor for diagnosis\n\nAHCT, Govt. of Telangana");
							else
								patientSmsVO.setSmsText("Dear "+patientVO.getFirstName().trim()+" , You have been registered as a Patient in "+patientService.getHospName(patientForm.getHospitalId())+" and it is pending with doctor for diagnosis\n\nAHCT, Govt. of Telangana");
							patientSmsVO.setEmpCode(patientVO.getEmpCode());
							patientSmsVO.setEmpName(patientVO.getFirstName().trim());
							patientSmsVO.setCrtUsr(patientVO.getCrtUsr());
							patientSmsVO.setCrtDt(crtDt);
							patientSmsVO.setSmsAction("Patient Registered");
							patientSmsVO.setPatientId(patientVO.getPatientId());
							commonService.sendPatientSms(patientSmsVO);
						}
					}
					}
					if(patientForm.getHospNims()!=null && "NI".equalsIgnoreCase(patientForm.getHospNims()))
					{
							if("true".equalsIgnoreCase(config.getString("SmsRequired")))
							{
								if(patientVO.getContactNo()!=null && !patientVO.getContactNo().equals(""))
								{
									SMSServices SMSServicesobj = new SMSServices();
									if(patientVO.getRelationAis()!=null && !"".equalsIgnoreCase(patientVO.getRelationAis()) && "N".equalsIgnoreCase(patientVO.getRelationAis()))
									{
									templateId="1407161769866089352";
									String msgNew= "Sarvasri "+patientVO.getFirstName().trim()+" "+patientVO.getServiceType().trim()+" , Your appointment has been successfully booked with reference Id "+patientVO.getPatientId().trim()+".For further queries, please contact below numbers between  10:00 AM and 05:00 PM. Mobile Number:8333816002,Landline:04023489000\n\nAHCT, Govt. of Telangana";
									SMSServicesobj.sendSingleSMS(msgNew,patientVO.getContactNo(),templateId);
									SMSServicesobj.sendSingleSMS(msgNew,config.getString("nimsPhn"),templateId);
									}
									else if(patientVO.getRelationAis()!=null && !"".equalsIgnoreCase(patientVO.getRelationAis()) && "Y".equalsIgnoreCase(patientVO.getRelationAis()))
									{
										String msgNew= "Dear "+patientVO.getFirstName().trim()+" , Your appointment has been successfully booked with reference Id "+patientVO.getPatientId().trim()+".For further queries,please contact below numbers between  10:00 AM and 05:00 PM.Mobile Number:8333816002,Landline:04023489000";	
										SMSServicesobj.sendSingleSMS(msgNew,patientVO.getContactNo(),null);
										SMSServicesobj.sendSingleSMS(msgNew,config.getString("nimsPhn"),null);
									}
									else if(patientVO.getRelationAis()!=null && !"".equalsIgnoreCase(patientVO.getRelationAis()) && "NA".equalsIgnoreCase(patientVO.getRelationAis()))
									{
										String msgNew= "Dear "+patientVO.getFirstName().trim()+" , Your appointment has been successfully booked with reference Id "+patientVO.getPatientId().trim()+".For further queries,please contact below numbers between  10:00 AM and 05:00 PM.Mobile Number:8333816002,Landline:04023489000";	
										SMSServicesobj.sendSingleSMS(msgNew,patientVO.getContactNo(),null);
										SMSServicesobj.sendSingleSMS(msgNew,config.getString("nimsPhn"),null);
									}
									}
								}
						}
					if(aisFlagNe!=null && !"".equalsIgnoreCase(aisFlagNe) && "Y".equalsIgnoreCase(aisFlagNe))
					{
						request.setAttribute("aisRed", "Y");
					}
					request.setAttribute("nimsAyush", nimsAyush);
					lStrPageName="patient";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error ( "Exception occured while registering patient in PatientAction." +e.getMessage()) ;
			}       				
		}
		if ("getLocations".equalsIgnoreCase(lStrActionVal))
		{
			String locHdrId=request.getParameter("lStrHdrId");
			String stateId=request.getParameter("stateId");
			String distId=request.getParameter("distId");
			String mandalId=request.getParameter("mandalId");
			String villageId=request.getParameter("villageId");
			String stateType=request.getParameter("stateType");
			try {
				List<LabelBean> cmbHdrList=null;
				List<String> locationsList = new ArrayList<String>();
				if(stateId!=null)
				{
					cmbHdrList=commonService.getLocationsNew(locHdrId, stateId,stateType);
					session.setAttribute("districtList",cmbHdrList);
				}
				else if(distId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, distId);
					session.setAttribute("mandalList",cmbHdrList);
				}
				else if(mandalId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, mandalId);
					session.setAttribute("villageList",cmbHdrList);
				}
				else if(villageId!=null)
				{
					cmbHdrList=commonService.getAsrimLocations(locHdrId, villageId);
					session.setAttribute("hamletList",cmbHdrList);
				}
				for (LabelBean labelBean: cmbHdrList) {
					if (labelBean.getID() != null && 
							labelBean.getVALUE() != null)
						if (locationsList != null && locationsList.size() > 0) {
							locationsList.add(labelBean.getID() + "~" + 
									labelBean.getVALUE());
						} else
							locationsList.add(labelBean.getID() + "~" + 
									labelBean.getVALUE());
				}
				if (locationsList != null && locationsList.size() > 0)
					request.setAttribute("AjaxMessage", locationsList);
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred in Ajax getLocations actionFlag in PatientAction." +e.getMessage()) ;
			}
			lStrPageName="ajaxResult";
		}
		if ("cancelPatient".equalsIgnoreCase(lStrActionVal))
		{
			String patientId = request.getParameter("patientId");
			PatientVO patVo=new PatientVO();
			if(patientId != null && !"".equalsIgnoreCase(patientId))
			patVo.setPatientId(patientId);
			patVo.setLstUpdUsr(userIDu);
			String msg = patientService.cancelPatient(patVo);
			patientForm.setMsg(msg);
			lStrActionVal="ViewRegisteredPatients";
		}
		if ("regPatient".equalsIgnoreCase(lStrActionVal))
		{
			String ayushValues= "";
			String patientId = request.getParameter("patientId");
			request.setAttribute("patId",patientId);
			patientForm.setPatientNo(patientId);
			PatientVO patVo=new PatientVO();
			if(patientId != null && !"".equalsIgnoreCase(patientId))
			patVo.setPatientId(patientId);
			patVo.setLstUpdUsr(userIDu);
			ayushValues=patientService.getTherapyOrAyush(patientId);
			if(ayushValues!=null && !"".equalsIgnoreCase(ayushValues) && "Y".equalsIgnoreCase(ayushValues))
			{
			 String msg = patientService.regPatient(patVo);
			 request.setAttribute("AjaxMessage",msg);
			}
			else
			{
				 String msg = patientService.regPatientPck(patVo);
				 request.setAttribute("AjaxMessage",msg);
			}
			lStrPageName="ajaxResult";	
		}
		if ("getValidSandostatinInj".equalsIgnoreCase(lStrActionVal))
		{
			String patientNo= request.getParameter("patientNo");
			String icdProcCode= request.getParameter("icdProcCode");
			boolean validInjection = patientService.validateSandostatinInj(patientNo,icdProcCode);
			request.setAttribute("AjaxMessage", validInjection);
		     lStrPageName="ajaxResult";
		}
		if ("ViewRegisteredPatients".equalsIgnoreCase(lStrActionVal))
		{
			String oldCase="";
			List<LabelBean> hospitalList= null;
			fromDisp = request.getParameter("fromDispnsry");
			String stateType=request.getParameter("stateType");
            String tdLabflg=request.getParameter("tdLabflg");//sai krishna:CR#8134API Integration.
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				hospitalList=patientService.getReferredCenterDtls(lStrUserId,roleId);
				if("GP801".equalsIgnoreCase(grpId)){
					request.setAttribute("fromPharma","Y");
				}
			}
			else
			{	
				hospitalList=patientService.getHospital(lStrUserId,roleId);
			}
			List<LabelBean> distList= new ArrayList<LabelBean>();
			PatientVO retPatVO=null;
			List<PatientVO> registeredPatientsList=null;
			HashMap<String, String> searchMap=new HashMap<String, String>();
			int noOfPages=0;int totRowsCount=0;
			int lStrRowsperpage =10;
			if(patientForm.getRowsPerPage()!=null && !("").equalsIgnoreCase(patientForm.getRowsPerPage()))
				lStrRowsperpage=Integer.parseInt(patientForm.getRowsPerPage());
			int lStrStartIndex = 0;
			int pageNo=0;
			String actionType=null;
			searchMap.put("lStrStartIndex", lStrStartIndex+"");
			searchMap.put("lStrRowsperpage", lStrRowsperpage+"");
			if(hospitalList.size()>0)
			{
				searchMap.put("userHospId",hospitalList.get(0).getID());
			}
			if(request.getParameter("pageNo")!=null && !request.getParameter("pageNo").equals("")){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(pageNo==0)
			{
				request.setAttribute("selectedPage",pageNo+1);
			}
			else
			{
				request.setAttribute("selectedPage",pageNo);
			}
			if(request.getParameter("actionType")!=null && !request.getParameter("actionType").equals("")){
				actionType=request.getParameter("actionType");
			}
			if(request.getParameter("advSearch")!=null && request.getParameter("advSearch").equalsIgnoreCase("true"))
			{
				String patientName="",patientNo="",cardNo="",district="",state="",fromDate="",toDate="";
				if(actionType!=null && actionType.equalsIgnoreCase("link"))
				{
					if(!patientForm.getCurrPatId().equals("")){
						patientNo=patientForm.getCurrPatId();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCurrPatName().equals("")){
						patientName=patientForm.getCurrPatName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getCurrHealthCardNo().equals("")){
						cardNo=patientForm.getCurrHealthCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getCurrStateId().equals("-1") && !patientForm.getCurrStateId().equals("")){
						state=patientForm.getCurrStateId();
						searchMap.put("state",state);
					}
					if(!patientForm.getCurrDistrictId().equals("-1") && !patientForm.getCurrDistrictId().equals("")){
						district=patientForm.getCurrDistrictId();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getCurrFromDate().equals("")){
						fromDate=patientForm.getCurrFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getCurrToDate().equals("")){
						toDate=patientForm.getCurrToDate();
						searchMap.put("toDate",toDate);
					}
				}
				else if(actionType!=null && actionType.equalsIgnoreCase("button"))
				{
					if(patientForm.getPatientName()!=null && !patientForm.getPatientName().equals(""))
					{
						patientName=patientForm.getPatientName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getPatientNo().equals(""))
					{
						patientNo=patientForm.getPatientNo();
						searchMap.put("patientNo",patientNo);
					}
					if(patientForm.getCardNo()!=null && !patientForm.getCardNo().equals(""))
					{
						cardNo=patientForm.getCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(patientForm.getState()!=null && !patientForm.getState().equals("-1"))
					{
						state=patientForm.getState();
						searchMap.put("state",state);
					}
					if(patientForm.getDistrict()!=null && !patientForm.getDistrict().equals("-1"))
					{
						district=patientForm.getDistrict();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(patientForm.getFromDate()!=null && !patientForm.getFromDate().equals(""))
					{
						fromDate=patientForm.getFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(patientForm.getToDate()!=null && !patientForm.getToDate().equals(""))
					{
						toDate=patientForm.getToDate();
						searchMap.put("toDate",toDate);
					}
					 oldCase = request.getParameter("x");
					if(oldCase!=null && "true".equalsIgnoreCase(oldCase))
					{
						searchMap.put("oldCase","Y");
					}
					else
					{
						searchMap.put("oldCase","N");
					}
				}
				/*patientForm.setPatientNo("");
        		patientForm.setPatientName("");
        		patientForm.setCardNo("");
        		patientForm.setDistrict("-1");
        		patientForm.setFromDate("");
        		patientForm.setToDate("");*/
			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				/*List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
				session.setAttribute("districtList",distList);*/
/*				List<LabelBean> phaseList=commonService.getPhases();
				session.setAttribute("phaseList",phaseList);*/
				//Creating ageList
/*				List<LabelBean> ageList=new ArrayList<LabelBean>();
				for(int i=1;i<=7;i++)
				{
					LabelBean labelBean=new LabelBean();
					labelBean.setID(""+i);
					labelBean.setVALUE(config.getString("REPORTS.AgeGrp"+i));
					ageList.add(labelBean);
				}
				session.setAttribute("ageList",ageList); */
			}
			if(pageNo>0)
			{
				lStrStartIndex = getStartIndex(lStrRowsperpage,pageNo);
				searchMap.put("lStrStartIndex", lStrStartIndex+"");
			}
			searchMap.put("schemeId",schemeId);
			//Dispensary Changes by sravan
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				searchMap.put("fromDisp",fromDisp);
				searchMap.put("roleId",roleId);
				searchMap.put("medcoId",lStrUserId);
				request.setAttribute("fromDisp",fromDisp);
				patientForm.setFromDisp(fromDisp);
			}
			searchMap.put("tdLabflg",tdLabflg);
			retPatVO=patientService.getRegisteredPatients(searchMap);
			registeredPatientsList=retPatVO.getRegisteredPatList();
			totRowsCount = retPatVO.getTotRowCount();
			noOfPages = getNoOfPages(totRowsCount, lStrRowsperpage);
			request.setAttribute("noOfPages", noOfPages);
			patientForm.setNoOfPages(noOfPages);
			if(registeredPatientsList!=null && registeredPatientsList.size()>0)
			{
				int endIndex=0;
				if(lStrStartIndex+lStrRowsperpage<= retPatVO.getTotRowCount())
				{
					endIndex=lStrStartIndex+lStrRowsperpage;
				}
				else
				{
					endIndex=retPatVO.getTotRowCount();
				}
				request.setAttribute("recordsList",lStrStartIndex+"-"+endIndex);
				request.setAttribute("totalRecords", retPatVO.getTotRowCount());
				request.setAttribute("registeredPatientsList", registeredPatientsList);
				request.setAttribute("searchListSize",retPatVO.getTotRowCount());
			}
			else if(retPatVO.getMsg()!=null && !retPatVO.getMsg().equalsIgnoreCase(""))
			{
				patientForm.setErrMsg(retPatVO.getMsg());
			}
			else
			{
				patientForm.setErrMsg(config.getString("err.NoRecordsFound"));
			}
			request.setAttribute("districtList",distList);
			request.setAttribute("tdLabflg", tdLabflg);
			if(patientForm.getPatientNo()!=null && !"".equalsIgnoreCase(patientForm.getPatientNo()))
			{
				String patientId = patientForm.getPatientNo();
				EhfPatient ehfPatient=null;
				ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				if(ehfPatient.getPharmaView()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmaView()))
				patientForm.setEnablePharma(ehfPatient.getPharmaView());
				request.setAttribute("enablePharma",patientForm.getEnablePharma());
			}
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp) && (roleId!=null && (roleId.equalsIgnoreCase("GP801") || roleId.equalsIgnoreCase("GP813")))){
				request.setAttribute("rolId",roleId);
				if(roleId!=null&&!"".equalsIgnoreCase(roleId)&&roleId.equalsIgnoreCase("GP813")){
					lStrPageName="ViewRegisteredPatientsDisp";
				}else{
				if(roleId!=null && "GP801".equalsIgnoreCase(roleId) && actionType!=null && actionType.equalsIgnoreCase("button"))
				lStrPageName="ViewRegisteredPatientsDisp";
				else
				lStrPageName="hideRegisteredPatientsDisp";
				}
			}else{
				lStrPageName="viewRegisteredPatients";	
			}
		}
		if ("viewPatientDetails".equalsIgnoreCase(lStrActionVal))
		{
			request.setAttribute("dentalFlg", request.getParameter("dental"));
			String patientId=null;
			String lStrCaseId = request.getParameter("caseId");
			String aisFlagNew=request.getParameter("aisFlagNew");
			String aisSubmit = request.getParameter("aisSubmit");
			 fromDisp = request.getParameter("fromDisp");
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				patientForm.setFromDisp(fromDisp);
				request.setAttribute("fromDisp", fromDisp);
			}	
			else
			{
				patientForm.setFromDisp("N");
				request.setAttribute("fromDisp", "N");
			}
			request.setAttribute("aisFlagNew", aisFlagNew);
			List<CommonDtlsVO> referalDtls=patientService.getDtrsReferealDtls(lStrCaseId,fromDisp);
			if(referalDtls.size()>0)
			{
				if(referalDtls != null)
					patientForm.setReferalDtls(referalDtls);
			}
			EhfPatient ehfPatient=null;
			patientId=request.getParameter("patientId");
			patientForm.setPatientNo(patientId);
			String userID=(String) session.getAttribute("UserID");
			//String dentalrnt=request.getParameter("dentalredirect");
			String check=patientService.checkDentalClinic(userID,patientId);
			String hospGov=patientService.checkGovernmentHosp(userID,patientId,fromDisp);
			if(!check.equals("hospital"))
			{
				patientForm.setHosType("DC");
			}
			else
			{
				patientForm.setHosType("hospital");
			}
			// request.setAttribute("patientId",patientId);
			ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
			String treatType=null;
			EhfmHospitals ehfmHospitals = patientService.getHospTaashaFlg(ehfPatient.getRegHospId());
			if(ehfmHospitals!=null)
				treatType=ehfmHospitals.getTreatType();
			if(treatType!=null && !treatType.equalsIgnoreCase(""))
				request.setAttribute("treatType", treatType);
			else
				request.setAttribute("treatType", "B");
			//storing patcrtdt
			session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
			int otherInvestCount=0;
			int otherDrugCount=0;
			patientForm.setCardNo(ehfPatient.getCardNo());
			patientForm.setFname(ehfPatient.getName());
			patientForm.setEnablePharma(ehfPatient.getPharmaView());
			PatientVO investVo=new PatientVO();
			if(ehfPatient!= null)
			{
				if("IPM".equalsIgnoreCase(ehfPatient.getPatientIpop()))
				{
					List<LabelBean> catList = patientService.getCatName(lStrCaseId);
					if(catList!=null && catList.size() > 0)	
					{
						if(catList.get(0).getICDCODE() != null)
							patientForm.setMedicalSpclty(catList.get(0).getICDCODE());
						if(catList.get(0).getID() != null)
							patientForm.setMedicalCat(catList.get(0).getID());
						request.setAttribute("medCatName",catList.get(0).getID() );
						if(catList.get(0).getCOMPON() != null)
							patientForm.setICDProcName1(catList.get(0).getCOMPON());
						request.setAttribute("medProcName",catList.get(0).getCOMPON() );
						if(catList.get(0).getCOMPROLE() != null)
							patientForm.setIpOtherRemarks(catList.get(0).getCOMPROLE());
						request.setAttribute("ipOtherRemarks",catList.get(0).getCOMPROLE() );
					}
				}
			}
			if(ehfPatient.getCardIssueDt()!=null)
			{
				patientForm.setCardIssueDt(sdf.format(ehfPatient.getCardIssueDt()));
			}
			else
				patientForm.setCardIssueDt("NA");
			if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
			{
				patientForm.setGender("Male");
			}
			else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
			{
				patientForm.setGender("Female");
			}
			if(ehfPatient.getEnrollDob()!=null)
			{
				patientForm.setDateOfBirth(sdf.format(ehfPatient.getEnrollDob()));
			}
			if(ehfPatient.getAge()!=null)
			{
				patientForm.setYears(ehfPatient.getAge().toString());
				if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 ))
				{
					request.setAttribute("child", "Y");
				}
				else
				{
					request.setAttribute("child", "N");
				}
			}
			if(ehfPatient.getAgeMonths()!=null)
			{
				patientForm.setMonths(ehfPatient.getAgeMonths().toString());
			}
			if(ehfPatient.getAgeDays()!=null)
			{
				patientForm.setDays(ehfPatient.getAgeDays().toString());
			}
			if(ehfPatient.getMaritalStatus()!=null)
			{	
				String martialId= ehfPatient.getMaritalStatus();
				String martialName=patientService.getMartialName(martialId);
				if(martialName!=null && !"".equalsIgnoreCase(martialName))
				{
					patientForm.setMaritalStatus(martialName);
				}
			}
			String relationId=ehfPatient.getRelation();
			String relationName=patientService.getRelationName(relationId);
			patientForm.setRelation(relationName);
			String casteId=ehfPatient.getCaste();
			if(casteId!=null && !casteId.equals(""))
			{
				String casteName=commonService.getCmbHdrname(lStrcastesHdrId, casteId);
				patientForm.setCaste(casteName);
			}
			if(ehfPatient.getContactNo()!=null && !ehfPatient.getContactNo().equals(""))
			{
				patientForm.setContactno(ehfPatient.getContactNo().toString());
			}
			if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
			{
				patientForm.seteMailId(ehfPatient.getEmailId());
			}
			if(ehfPatient.getSerType()!=null && !ehfPatient.getSerType().equals(""))
			{
				patientForm.setSerType(ehfPatient.getSerType());
			}
			if(ehfPatient.getServiceAis()!=null && !ehfPatient.getServiceAis().equals(""))
			{
				if(ehfPatient.getServiceAis()!=null && ehfPatient.getServiceAis().equalsIgnoreCase("I"))
				patientForm.setServType("Yes");
				else
					patientForm.setServType("No");
			}
			if(ehfPatient.getPackagetype()!=null && ehfPatient.getPackagetype().equals("-1"))
			{
				patientForm.setAyush("Ayush Services");
			}
			else
			{
				patientForm.setAyush("Master Health Checkup");
			}
			if(ehfPatient.getPackagetype()!=null && ehfPatient.getPackagetype().equals("P1"))
			{
				patientForm.setDosage("Master Health Check up(4000)");
			}
			else if(ehfPatient.getPackagetype()!=null && ehfPatient.getPackagetype().equals("P2"))
			{
				patientForm.setDosage("Executive Health Check up(6000)");
			}
			else if(ehfPatient.getPackagetype()!=null && ehfPatient.getPackagetype().equals("P3"))
			{
				patientForm.setDosage("Life Health Check up(Male-13000 and Female-14000)");
			}
			if(ehfPatient.getAyushID()!=null && !"".equalsIgnoreCase(ehfPatient.getAyushID()))
			{
				String ayushId=ehfPatient.getAyushID();
				String ayushName=patientService.getAyushName(ayushId);
				patientForm.setAyushID(ayushName);
				request.setAttribute("ayushName", ayushName);
			}
			patientForm.setOccupation(ehfPatient.getOccupationCd());
			if(ehfPatient.getSchemeId()!=null )
				session.setAttribute("regPatientScheme",ehfPatient.getSchemeId());
			patientForm.setScheme(ehfPatient.getSchemeId());
			patientForm.setTelScheme(ehfPatient.getSchemeId());
			investVo.setSchemeId(ehfPatient.getSchemeId());
			/*else
        	patientForm.setOccupation("NA");*/
			//Setting slab
			String slabType=null;
			String slab=null;
			if(ehfPatient.getSlab()!=null)
			{
				slabType=ehfPatient.getSlab();
			}
			if(config.getString("Slab.SemiPrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.SemiPrivateWard");
			else if(config.getString("Slab.PrivateWard").equalsIgnoreCase(slabType))
				slab=config.getString("Slab.Name.PrivateWard");
			patientForm.setSlab(slab);
			//End Of Slab
			patientForm.setHno(ehfPatient.getHouseNo());
			patientForm.setStreet(ehfPatient.getStreet());
			patientForm.setState(patientService.getLocationName(ehfPatient.getState()));
			String distCode=ehfPatient.getDistrictCode();
			String distName=patientService.getLocationName(distCode);
			patientForm.setDistrict(distName);
			String mandalCode=ehfPatient.getMandalCode();
			String mandalName=patientService.getLocationName(mandalCode);
			patientForm.setMandal(mandalName);
			String villageCode=ehfPatient.getVillageCode();
			String villageName=patientService.getLocationName(villageCode);
			patientForm.setVillage(villageName);
			if(ehfPatient.getPinCode()!=null && !ehfPatient.getPinCode().equalsIgnoreCase(""))
				patientForm.setPin(ehfPatient.getPinCode());
			else
				patientForm.setPin("NA");
			//Setting Communication Address
			patientForm.setComm_hno(ehfPatient.getcHouseNo());
			patientForm.setComm_street(ehfPatient.getcStreet());
			patientForm.setComm_state(patientService.getLocationName(ehfPatient.getcState()));
			patientForm.setComm_dist(patientService.getLocationName(ehfPatient.getcDistrictCode()));
			patientForm.setComm_mandal(patientService.getLocationName(ehfPatient.getcMandalCode()));
			patientForm.setComm_village(patientService.getLocationName(ehfPatient.getcVillageCode()));
			if(ehfPatient.getcPinCode()!=null && !ehfPatient.getcPinCode().equalsIgnoreCase(""))
				patientForm.setComm_pin(ehfPatient.getcPinCode());
			else
				patientForm.setComm_pin("NA");
			patientForm.setDtTime(sdfw.format(ehfPatient.getRegHospDate()));
			String photoUrl=null;
			if(ehfPatient.getPatientScheme()!=null)
				{
					patientForm.setPatientScheme(ehfPatient.getPatientScheme());
					request.setAttribute("patientScheme",ehfPatient.getPatientScheme());
					investVo.setPatientScheme(ehfPatient.getPatientScheme());
					if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
							config.getString("Scheme.JHS").equalsIgnoreCase(ehfPatient.getPatientScheme()))
						 photoUrl=patientService.getJournalistPhoto(ehfPatient.getCardNo());
					else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
							 && ehfPatient.getNewBornBaby().equalsIgnoreCase("Y"))
						photoUrl=null;
					else
						photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
				}
			else if(ehfPatient.getCardType()!=null && ehfPatient.getNewBornBaby()!=null && ehfPatient.getCardType().equalsIgnoreCase(config.getString("NewBornBaby"))
					 && ehfPatient.getNewBornBaby().equalsIgnoreCase(config.getString("Y")))
				photoUrl=null;
			else
				photoUrl=patientService.getPatientPhoto(ehfPatient.getCardNo());
			if(photoUrl!=null)
			{
				try
				{
					File photo = new File(photoUrl);
					fis = new FileInputStream(photo);
					dis= new DataInputStream(fis);
					bytes = new byte[dis.available()];
					fis.read(bytes);
					String lStrContentType=null;
					lStrContentType="image/jpg";
					session.setAttribute("ContentType", lStrContentType);
					session.setAttribute("File", bytes);
					fis.close();
					dis.close();
					patientForm.setPhotoUrl(photoUrl);
				}
				catch(Exception e)
				{
					GLOGGER.error ( "Exception occured when photo is not available in the path specified to display for Ramco in PatientAction." +e.getMessage()) ;
				}
			}
			String hospCode = "";
			String hospName = "";
			String dispName="",contactNo="";
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp) && aisFlagNew!=null && "N".equalsIgnoreCase(aisFlagNew))
			{
				 hospCode=ehfPatient.getDispCode();
				 hospName=patientService.getDispensaryName(hospCode);
				 /* added by sowmya for ayush print */
				/* String[] dispDetArr=hospName.split("~");
				 dispName=dispDetArr[0];
				 contactNo=dispDetArr[1];*/
				 if (hospName != null) {
					    String[] dispDetArr = hospName.split("~");
					    if (dispDetArr.length > 0) {
					        dispName = dispDetArr[0];
					    }
					    if (dispDetArr.length > 1) {
					        contactNo = dispDetArr[1];
					    }
					}
				 PreauthVO preauthDtlsVO = new PreauthVO();
				preauthDtlsVO = patientService.getPatientFullDtls(lStrCaseId,
						ehfPatient.getPatientId(), fromDisp);
				if (preauthDtlsVO.getSpecLt() != null
						&& preauthDtlsVO.getSpecLt().size() > 0) {
					patientForm.setSpecLt(preauthDtlsVO.getSpecLt());
					request.setAttribute("specLst", preauthDtlsVO.getSpecLt());
				}
			}
			else
			{
				hospCode=ehfPatient.getRegHospId();
				hospName=patientService.getHospName(hospCode);
			}
			String dentalFlg=null;
			patientForm.setHospitalId(hospCode);
			request.setAttribute("hospitalId",hospCode);
			investVo.setHospitalCode(hospCode);
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				patientForm.setHospitalName(dispName);
				patientForm.setHospContactNo(contactNo);
			}
			else{
				dentalFlg=patientService.checkDentalHospital(hospCode);
			patientForm.setHospitalName(hospName);
			}
			//ADDED BY YASHASVI FOR SPECIALITIES
			String specialityType=ehfPatient.getSpecialityType();
			String finalSpeciality="", grpsForTokens="GP797,GP801";
			List<LabelBean> dispSpecList=commonService.getDispSpecialities();
			if(!"".equalsIgnoreCase(specialityType) && specialityType != null)
			{
				for(int i=0; i<dispSpecList.size();i++){
					grpsForTokens=grpsForTokens+","+dispSpecList.get(i).getID();
					if(specialityType.equalsIgnoreCase(dispSpecList.get(i).getID())){
						finalSpeciality=dispSpecList.get(i).getVALUE();
					}
				}
				session.setAttribute("grpsForTokens",grpsForTokens);
			}
			patientForm.setSpecialityType(finalSpeciality);
			patientForm.setTokenNo(ehfPatient.getTokenNo());
			patientForm.setRoomNo(ehfPatient.getRoomNo());
			if(ehfPatient.getIntimationId()!=null)
			{
				patientForm.setTelephonicId(ehfPatient.getIntimationId());
				patientForm.setTelephonicReg("Yes");
				PatientVO patientVo = new PatientVO();
				patientVo.setTelephonicId(ehfPatient.getIntimationId());
				PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);
				patientForm.setTherapyType(patientVO1.getAsriCatCode());
				patientForm.setTherapyCategory(patientVO1.getICDcatName());
				patientForm.setTherapy(patientVO1.getTherapyCatId());
			}
			String pageType="";
			if(request.getParameter("pageType")!=null && !request.getParameter("pageType").equals(""))
			{
				pageType=request.getParameter("pageType");
			}
			if(pageType.equalsIgnoreCase(""))
			{
				List<LabelBean> hospitalList=null;
				if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					 hospitalList=patientService.getReferredCenterDtls(lStrUserId,roleId);
					 session.setAttribute("hospitalList",hospitalList);
					 if(lStrgrpList.contains("GP801")){
						List<CommonDtlsVO> previousDrugsHist=patientService.getPreviousDrugHist(patientForm.getCardNo());
						if(previousDrugsHist!=null)
							patientForm.setPastDrugHist(previousDrugsHist);
					 }
				}
				else
				{	
					hospitalList=patientService.getHospital(lStrUserId,roleId);
				}
				if(hospitalList!=null && hospitalList.size()>0)
				{
					patientForm.setHosptype(hospitalList.get(0).getNATURE());
					request.setAttribute("hospType",hospitalList.get(0).getNATURE());
					investVo.setHospType(hospitalList.get(0).getNATURE());
					investVo.setHospitalCode(hospitalList.get(0).getID());
				}
				List<LabelBean> mainComplaintList=null;
				mainComplaintList=patientService.getMainComplaintLst();
				if(!"".equalsIgnoreCase(ehfPatient.getPatientScheme()) && 
						config.getString("Scheme.EHS").equalsIgnoreCase(ehfPatient.getPatientScheme())
					&& hospitalList.get(0).getNATURE()!=null && ("G").equalsIgnoreCase(hospitalList.get(0).getNATURE())
					&& !"".equalsIgnoreCase(ehfPatient.getSchemeId()) && 
					config.getString("TG").equalsIgnoreCase(ehfPatient.getSchemeId())
				)
				{
					LabelBean otherCmpLst=new LabelBean();
					otherCmpLst.setID("OC");
					otherCmpLst.setVALUE("Others");
					if(mainComplaintList!=null)
					{
					}
				}
				request.setAttribute("mainComplaintList", mainComplaintList);
				List<LabelBean> districtsList = null;
				districtsList= commonService.getDistrictListNew();
				request.setAttribute("districtsList", districtsList);
				List<LabelBean> specialityList = null;
				specialityList= commonService.getSpecialityList();
				request.setAttribute("specialityList", specialityList);
				hospDtlsList = null;
				hospDtlsList= commonService.getHosptlDtlsList();
				request.setAttribute("hospDtlsList", hospDtlsList);
				drugTypeList = null;
				request.setAttribute("drugTypeList", drugTypeList);
				List<LabelBean> pastHistoryList=null;
				pastHistoryList=commonService.getPastIllnessHitory();
				request.setAttribute("pastHistoryList", pastHistoryList);
				List<LabelBean> personalHistoryList=null;
				personalHistoryList=commonService.getPersonalHistory("PR");
				request.setAttribute("personalHistoryList", personalHistoryList);
				List<LabelBean> familyHistoryList=null;
				familyHistoryList=commonService.getFamilyHistory();
				request.setAttribute("familyHistoryList", familyHistoryList);
				List<LabelBean> investigationsList=null;
				if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
					investigationsList=commonService.getGeneralInvest(patientId,dispId);//sai:API Integration Main.
					if(investigationsList!=null && investigationsList.size()>0)
						request.setAttribute("investigationsList", investigationsList);
				}
				else
				{
					investigationsList=commonService.getInvestBlockNameNew(investVo);
					request.setAttribute("investigationsList", investigationsList);
				}
				 List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
					request.setAttribute("dispDrugTypeList", drugsTypeList);
				/*List<LabelBean> diaglist=patientService.getDiagList();     //getDiagnosis list();
				request.setAttribute("diaglist", diaglist);*/
				List<LabelBean> drugsList=commonService.getOpDrugs(null);     //getDrugs();
				request.setAttribute("drugsList", drugsList);
				List<LabelBean> drugsListAuto=commonService.getOpDrugsAuto();     //getopDrugs automatic
				request.setAttribute("drugsListAuto", drugsListAuto);
				List<LabelBean> routeTypeLst=commonService.getRouteType(null);     //getDrugs();
				request.setAttribute("routeTypeLst", routeTypeLst);
				List<LabelBean> strengthTypeLst=commonService.getStrengthType(null);     //getDrugs();
				request.setAttribute("strengthTypeLst", strengthTypeLst);
				List<LabelBean> examinFndgsList=null;
				examinFndgsList=commonService.getGenExaminFndgs(ehfPatient.getSchemeId());
				request.setAttribute("examinFndgsList", examinFndgsList);
				List<LabelBean> mainSymptonLst=null;
				mainSymptonLst=commonService.getMainSymptonLst();
				request.setAttribute("mainSymptonLst", mainSymptonLst);
				List<LabelBean> diagnTypeList = patientService.getDiagnosisTypes();
				session.setAttribute("diagnTypeList",diagnTypeList);
				List<LabelBean> categoryList = commonService.getAsriCategoryList(hospCode,schemeId);
				session.setAttribute("AsricategoryList",categoryList);
				List<LabelBean> procedureList= new ArrayList<LabelBean>();
				session.setAttribute("ICDprocedureList",procedureList);
				List<LabelBean> opCategoryList= patientService.getTherapyOPCategory();
				request.setAttribute("opCategoryList",opCategoryList);
				List<LabelBean> dentalsublist= new ArrayList<LabelBean>();
				session.setAttribute("Dentalsublist",dentalsublist);
				List<LabelBean> Dentalmainlist0= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist0",Dentalmainlist0);
				List<LabelBean> Dentalmainlist1= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist1",Dentalmainlist1);
				List<LabelBean> Dentalmainlist2= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist2",Dentalmainlist2);
				List<LabelBean> Dentalmainlist3= new ArrayList<LabelBean>();
				request.setAttribute("Dentalmainlist3",Dentalmainlist3);
				List<LabelBean> Dentalsublistjaws1= new ArrayList<LabelBean>();
				request.setAttribute("Dentalsublistjaws1",Dentalsublistjaws1);
				List<LabelBean> Dentalsublistrl0= new ArrayList<LabelBean>();
				request.setAttribute("Dentalsublistrl0",Dentalsublistrl0);
				List<LabelBean> dentalUnitsList = new ArrayList<LabelBean>();
				String dentalUnitsStr=config.getString("Dental.Units");
				String[] dentalUnitslst=dentalUnitsStr.split("~");
				for(int i=0;i<dentalUnitslst.length;i++)
				{
					LabelBean lb=new LabelBean();
					lb.setID(dentalUnitslst[i]);
					lb.setVALUE(dentalUnitslst[i]);
					dentalUnitsList.add(lb);
				}
				request.setAttribute("dentalUnitsList",dentalUnitsList);
				List<LabelBean> medicalhsitorydtls=null;
				List<LabelBean> treatmentDone=null,treatmentDoneSub=null;
				if(fromDisp!=null && fromDisp.equalsIgnoreCase("Y") && ehfPatient.getSpecialityType()!=null && ehfPatient.getSpecialityType().equalsIgnoreCase("GP796")){
					medicalhsitorydtls=patientService.getdispdentalexaminationDtls("DHD1");
					patientForm.setMedicalhsitorydtls(medicalhsitorydtls);
					treatmentDone=patientService.getdispdentalexaminationDtls("DHD2");
					patientForm.setDispDentalTreatDoneLst(treatmentDone);
					String treatDoneStr=null;
					for(int i=0;i<treatmentDone.size();i++){
						if(i<1){
							treatDoneStr=treatmentDone.get(i).getID();
						}
						else
							treatDoneStr=treatDoneStr+"','"+treatmentDone.get(i).getID();
					}
					treatmentDoneSub=patientService.getdispdentalexaminationDtls(treatDoneStr);
					patientForm.setDispDentalTreatDoneSubLst(treatmentDoneSub);
				}
				CasesSearchVO casesSearchVO = new CasesSearchVO();
				casesSearchVO.setCaseId(lStrCaseId);
				casesSearchVO.setCARDNO(ehfPatient.getCardNo());  
				String empCode=null;
				empCode=patientService.getEmpCode(ehfPatient.getPatientId());
				if(request.getParameter("employeeNo")!=null && !"".equalsIgnoreCase(request.getParameter("employeeNo")))
					casesSearchVO.setLoginName(request.getParameter("employeeNo"));
				else
				casesSearchVO.setLoginName(empCode);
				request.setAttribute("fromPastHistory", "Y");
				request.setAttribute("caseId", lStrCaseId);
				//casesSearchVO.setCaseForDissFlg("Y");
				request.setAttribute("employeeNo", empCode);
				if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
				List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
				request.setAttribute("treatHist", caseHist);
				patientForm.setLstCaseSearch(caseHist);
				}
				else{
				List<CasesSearchVO> casesList=patientService.getOpPastHistoryDetails(casesSearchVO);
				if(casesList.size()==0)
				{
					//casesSearchForm.setFlag("false");
				}
				else
				{
					//casesSearchForm.setFlag("true");
					patientForm.setLstCaseSearch(casesList);
				}
				}
				PreauthVO preauthDtlsVO = new PreauthVO();
				PatientVO patientDtlsVo= new PatientVO();
				patientForm.setPatientType("");
				preauthDtlsVO = patientService.getPatientFullDtls(lStrCaseId, ehfPatient.getPatientId(),fromDisp);
				if(ehfPatient.getSpecialityType()!=null && ehfPatient.getSpecialityType().equalsIgnoreCase("GP796")){
					patientForm.setTextValDH1(preauthDtlsVO.getDiabetesRemarks());
					patientForm.setTextValDH2(preauthDtlsVO.getHypertensionRemarks());
					patientForm.setTextValDH3(preauthDtlsVO.getCardiacHistRemarks());
					patientForm.setTextValDH4(preauthDtlsVO.getDrugAllergyRemarks());
					patientForm.setTextValDH5(preauthDtlsVO.getThyroidRemarks());
					patientForm.setTextValDH6(preauthDtlsVO.getOralProphylaxisRemarks());
					patientForm.setTextValDH71(preauthDtlsVO.getCompositeRemarks());
					patientForm.setTextValDH72(preauthDtlsVO.getGicRemarks());
					patientForm.setTextValDH73(preauthDtlsVO.getPitFissureSealantsRemarks());
					patientForm.setTextValDH8(preauthDtlsVO.getExtractionRemarks());
					patientForm.setTextValDH9(preauthDtlsVO.getMedicationRemarks());
				}
				if(dentalFlg !=null && "Y".equalsIgnoreCase(dentalFlg) && ("yes".equalsIgnoreCase(dentalrnt) || "yes".equalsIgnoreCase(preauthDtlsVO.getTreatmentDntl()))){
					List<LabelBean> mainComplaintList1=new ArrayList<LabelBean>();
					mainComplaintList=patientService.getDentalMainComplaintLst();
					for(int i=0 ; i<mainComplaintList.size() ; i++)
					{
						if(!mainComplaintList.get(i).getVALUE().equalsIgnoreCase("SORES"))
						{ 
							LabelBean locBean= new LabelBean();
							locBean.setID(mainComplaintList.get(i).getID()); 
							locBean.setVALUE(mainComplaintList.get(i).getVALUE());
							mainComplaintList1.add(locBean);
						}
					}
					mainSymptonLst=commonService.getDentalMainSymptonLst();
					investigationsList=commonService.getDentalInvestBlockNameNew(investVo);
					examinFndgsList=commonService.getDentalGenExaminFndgs(ehfPatient.getSchemeId());
					personalHistoryList=commonService.getDentalPersonalHistory("PR");
					request.setAttribute("personalHistoryList", personalHistoryList);
					request.setAttribute("examinFndgsList", examinFndgsList);
					request.setAttribute("investigationsList", investigationsList);
					request.setAttribute("mainComplaintList", mainComplaintList1);
					request.setAttribute("mainSymptonLst", mainSymptonLst);
					List<LabelBean> extraoraldtls=null;
					List<LabelBean> intraoraldtls=null;
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					List<LabelBean> permanentdentdtlsothr=null;
			        String extraoral="HD1";
					String intraoral="HD2";
					String occlusion="CH12";
					String deciduousdent ="CH13";
					String  permanentdent="CH14";
					String  permanentdentothr="CH91";
					String  medicalHsitory="HD6";
					medicalhsitorydtls=patientService.getdentalexaminationDtls(medicalHsitory);
					extraoraldtls=patientService.getdentalexaminationDtls(extraoral);
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					permanentdentdtls=patientService.getdentalexaminationDtls(permanentdent);
					permanentdentdtlsothr=patientService.getdentalexaminationDtls(permanentdentothr);
					patientForm.setMedicalhsitorydtls(medicalhsitorydtls);
                    patientForm.setExtraoraldtls(extraoraldtls);
					patientForm.setIntraoraldtls(intraoraldtls);
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					request.setAttribute("medicalhsitorydtls", medicalhsitorydtls);
					request.setAttribute("extraoraldtls", extraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", deciduousdentdtls);
					request.setAttribute("permanentdentdtls", permanentdentdtls);
					request.setAttribute("permanentdentdtlsothr", permanentdentdtlsothr);
				}
				request.setAttribute("dentalFlg",dentalFlg );
                if(lStrCaseId!=null && !lStrCaseId.equalsIgnoreCase("")){
                patientForm.setCaseId(lStrCaseId);
				preauthDtlsVO = patientService.getPatientFullDtls(lStrCaseId, ehfPatient.getPatientId(),fromDisp);		
				if(preauthDtlsVO.getCarriesdecidous()!=null)
				{
					patientForm.setCarriesdecidous(preauthDtlsVO.getCarriesdecidous());
				}
				if(preauthDtlsVO.getExtraoral()!=null)
				{
				String[] extraoralsublist=preauthDtlsVO.getExtraoral();
				for (int i=0 ; i<extraoralsublist.length; i++)
				{
					List<LabelBean> extraoralsubmodlist=null;
					extraoralsubmodlist=patientService.getdentalexaminationDtls(extraoralsublist[i]);
					if("CH4".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist0", extraoralsubmodlist);
					}
					if("CH3".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist1", extraoralsubmodlist);
					}
					if("CH2".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist2", extraoralsubmodlist);
					}
					if("CH1".equalsIgnoreCase(extraoralsublist[i]))
					{
						request.setAttribute("Dentalmainlist3", extraoralsubmodlist);
					}
				}
				}
				if(preauthDtlsVO.getSubdentalrl0()!=null)
				{
				String lymphnodessublist=preauthDtlsVO.getSubdental0();
					List<LabelBean> lymphnodessubmodlist=null;
					lymphnodessubmodlist=patientService.getdentalexaminationDtls(lymphnodessublist);
					request.setAttribute("Dentalsublistrl0", lymphnodessubmodlist);
				}
				if(preauthDtlsVO.getOcclusionType()!=null)
				{
					String occlusiondtlslist=preauthDtlsVO.getOcclusion();
					List<LabelBean> occlusiondtlssublist=null;
					occlusiondtlssublist=patientService.getdentalexaminationDtls(occlusiondtlslist);
					request.setAttribute("Dentalsublist", occlusiondtlssublist);
				}
				if(preauthDtlsVO.getSubdentaljaws1()!=null)
				{
				String jawsvalue=preauthDtlsVO.getSubdental1();
				List<LabelBean> jawsubmodlist=null;
			    jawsubmodlist=patientService.getdentalexaminationDtls(jawsvalue);
			    request.setAttribute("Dentalsublistjaws1", jawsubmodlist);
				}
				request.setAttribute("denatlcasesheet", preauthDtlsVO);	
				if(preauthDtlsVO.getMedicalDtlsid()!=null )
			    {
				String[] medicalList = preauthDtlsVO.getMedicalDtlsid();
			    patientForm.setMedicalDtlsid(medicalList);
				}
				if(preauthDtlsVO.getShowMedicalTextval()!=null)
				{
					patientForm.setShowMedicalTextval(preauthDtlsVO.getShowMedicalTextval());
				}
				preauthDtlsVO.setDentalFlg(dentalFlg);
				if(preauthDtlsVO.getDentalFlg()!=null && "Y".equalsIgnoreCase(preauthDtlsVO.getDentalFlg())){
					/*added by pavan for the dental case sheet fetching new options add to sheet*/
					List<LabelBean> extraoraldtls=null;
					List<LabelBean> intraoraldtls=null;
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					List<LabelBean> permanentdentdtlsothr=null;
//					List<LabelBean> medicalhsitorydtls=null;
			        String extraoral="HD1";
					String intraoral="HD2";
					String occlusion="CH12";
					String deciduousdent ="CH13";
					String  permanentdent="CH14";
					String  permanentdentothr="CH91";
					String  medicalHsitory="HD6";
					medicalhsitorydtls=patientService.getdentalexaminationDtls(medicalHsitory);
					extraoraldtls=patientService.getdentalexaminationDtls(extraoral);
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					permanentdentdtls=patientService.getdentalexaminationDtls(permanentdent);
					permanentdentdtlsothr=patientService.getdentalexaminationDtls(permanentdentothr);
					//patientForm.setMedicalhsitorydtls(medicalhsitorydtls);
                    patientForm.setExtraoraldtls(extraoraldtls);
					patientForm.setIntraoraldtls(intraoraldtls);
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					request.setAttribute("medicalhsitorydtls", medicalhsitorydtls);
					request.setAttribute("extraoraldtls", extraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", deciduousdentdtls);
					request.setAttribute("permanentdentdtls", permanentdentdtls);
					request.setAttribute("permanentdentdtlsothr", permanentdentdtlsothr);
							//request.setAttribute("patientForm", );
					patientDtlsVo=patientService.getPatientDentalDtls(ehfPatient.getPatientId());
					patientForm.setDiagnosis(patientDtlsVo.getDiagnosis());
					patientForm.setAdmissionDetails(patientDtlsVo.getAdmissionDetails());
					patientForm.setPatientType(patientDtlsVo.getPatientType());
					patientForm.setAdvancedInvestigations(patientDtlsVo.getAdvancedInvestigations());
					patientForm.setFollowupAdv(patientDtlsVo.getFollowupAdv());
					request.setAttribute("dentalDtls", patientDtlsVo);
				}
			    patientForm.setComplaints(preauthDtlsVO.getComplaintType());
			    patientForm.setComplaintCode(preauthDtlsVO.getComplaintType());
			    patientForm.setOtherComplaint(preauthDtlsVO.getOtherComplaintName());
			    patientForm.setPatComplaintCode(preauthDtlsVO.getPatComplaint());
			    patientForm.setPresentHistory(preauthDtlsVO.getPresentIllness());
			    patientForm.setDrughistoryid(preauthDtlsVO.getDrugHst());
			    if(preauthDtlsVO.getDrugHstVal()!=null || preauthDtlsVO.getDrugHstVal()!="")
			    patientForm.setDrughistory(preauthDtlsVO.getDrugHstVal());
				if(preauthDtlsVO.getPersonalHis()!=null && !preauthDtlsVO.getPersonalHis().equalsIgnoreCase(""))
			    {
				String[]  persHist = preauthDtlsVO.getPersonalHis().split("~");
			    patientForm.setPersonalHist(persHist);
				}
				if(preauthDtlsVO.getExamFindg()!=null && !preauthDtlsVO.getExamFindg().equalsIgnoreCase(""))
			    {
				String[] examfndList = preauthDtlsVO.getExamFindg().split("~");
			    patientForm.setExaminationFnd(examfndList);
				}
			    if(preauthDtlsVO.getIpOpFlag()!=null && !preauthDtlsVO.getIpOpFlag().equalsIgnoreCase(""))
			    	{
			    	patientForm.setPatientType(preauthDtlsVO.getIpOpFlag());
			    	patientForm.setDiagType(preauthDtlsVO.getDiagnosisType());
			    	patientForm.setDiagCode(preauthDtlsVO.getDiagnosisType());
			    	patientForm.setMainCatName(preauthDtlsVO.getMainCatName());
			    	patientForm.setMainCatCode(preauthDtlsVO.getMainCatName());
			    	patientForm.setCatName(preauthDtlsVO.getCatId());
			    	patientForm.setCatCode(preauthDtlsVO.getCatId());
			    	patientForm.setSubCatName(preauthDtlsVO.getSubCatName());
			    	patientForm.setSubCatCode(preauthDtlsVO.getSubCatName());
                     if(!"".equalsIgnoreCase(preauthDtlsVO.getDrugName())  && preauthDtlsVO.getDrugName() != null )
			    	{
			    		patientForm.setDrugName(preauthDtlsVO.getDrugName());
			    	}			    	
                    if(preauthDtlsVO.getOtherDiagName()!=null && !("").equalsIgnoreCase(preauthDtlsVO.getOtherDiagName()))			    	{
			    	patientForm.setDiagOthersName(preauthDtlsVO.getOtherDiagName());
			    	request.setAttribute("otherDiag","Y");
			    	}
			    	if(preauthDtlsVO.getIpOpFlag()!=null && (preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("IP") || preauthDtlsVO.getIpOpFlag().equalsIgnoreCase(config.getString("PatientIPOP.IPD"))))
			    	{
			    		patientForm.setIpNo(preauthDtlsVO.getPatientIPNo());
			    		patientForm.setIpDiagnosedBy(preauthDtlsVO.getDocType());
			    		//patientForm.setIpDoctorName(preauthDtlsVO.getDoctorDtls());
			    		patientForm.setIpDoctorName(preauthDtlsVO.getDocName());
			    		patientForm.setDocRegNo(preauthDtlsVO.getDocReg());
			    		patientForm.setDocQual(preauthDtlsVO.getDocQual());
			    		patientForm.setDocMobileNo(preauthDtlsVO.getDocMobNo());
			    		patientForm.setAdmissionType(preauthDtlsVO.getAdmType());
			    		patientForm.setLegalCase(preauthDtlsVO.getLegalCaseCheck());
			    		patientForm.setLegalCaseNo(preauthDtlsVO.getLegalCaseNo());
			    		patientForm.setPoliceStatName(preauthDtlsVO.getPoliceStatName());
			    		if(preauthDtlsVO.getDate()!=null)
			    		patientForm.setIpDate(sdf.format(preauthDtlsVO.getDate()));
			    		patientForm.setRemarks(preauthDtlsVO.getRemarks());
			    		List<PreauthVO> investList = patientService.getcaseSurgeryDtls(lStrCaseId);
			    		String investLst="";List<PreauthVO> finlInvestLst = new ArrayList<PreauthVO>();
			    		String procList="";
			    		if(investList!=null && investList.size()>0){
			    			for(PreauthVO preVO: investList){
			    				if(preVO.getLstSplInvet()!=null && preVO.getLstSplInvet().size()>0)
			    					{
			    					for(PreauthVO preVO1 : preVO.getLstSplInvet()){
			    						investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    						PreauthVO preVO2 = new PreauthVO();
			    						preVO2.setAsriCatName(preVO.getAsriCatName());
			    						preVO2.setCatId(preVO.getCatId());
			    						if(preVO.getCatId()!=null && preVO.getCatId().contains("S"))
			    							request.setAttribute("surgProc","Y");
			    						preVO2.setCatName(preVO.getCatName());
			    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
			    						preVO2.setProcName(preVO.getProcName());
			    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
			    						preVO2.setSeqNo(preVO.getSeqNo());
			    						preVO2.setDocName(preVO.getDocName());
			    						preVO2.setDocReg(preVO.getDocReg());
			    						preVO2.setDocMobNo(preVO.getDocMobNo());
			    						preVO2.setDocQual(preVO.getDocQual());
			    						preVO2.setOpProcUnits(preVO.getOpProcUnits());
			    						preVO2.setRobotSurgery(preVO.getRobotSurgery());
			    						preVO2.setTherapyId(preVO1.getTherapyId());
			    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   						
			    						investLst = investLst+"~"+preVO1.getTherapyId()+"~"+preVO1.getFilename()+"~"+preVO.getOpProcUnits();
			    						//Added by Srikalyan for Dental Conditions
			    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    						procList+=preVO.getIcdProcCode()+"~";
			    						preVO2.setFilename(preVO1.getFilename());
			    						preVO2.setFilePath(preVO1.getIpOpFlag());
			    						if(preVO1.getIpOpFlag()!=null && !("").equalsIgnoreCase(preVO1.getIpOpFlag()))
			    						preVO2.setName("View");
			    						else
			    							preVO2.setName("NA");	
			    						preVO2.setAuditName(preVO.getIcdProcCode()+preVO1.getTherapyId());
			    						preVO2.setInvestRemarks(preVO1.getTherapyId());	
				    					finlInvestLst.add(preVO2);
			    					}
			    					}
			    					else
			    					{
			    					investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    					investLst = investLst+"~NA~NA"+"~"+preVO.getOpProcUnits();
		    						//Added by Srikalyan for Dental Conditions
		    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    					PreauthVO preVO2 = new PreauthVO();
			    					preVO2.setAsriCatName(preVO.getAsriCatName());
		    						preVO2.setCatId(preVO.getCatId());
		    						preVO2.setCatName(preVO.getCatName());
		    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
		    						preVO2.setProcName(preVO.getProcName());
		    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
		    						preVO2.setSeqNo(preVO.getSeqNo());
		    						preVO2.setDocName(preVO.getDocName());
		    						preVO2.setDocReg(preVO.getDocReg());
		    						preVO2.setDocMobNo(preVO.getDocMobNo());
		    						preVO2.setDocQual(preVO.getDocQual());
		    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   		
		    						preVO2.setRobotSurgery(preVO.getRobotSurgery());
		    						preVO2.setFilename("NA");
		    						preVO2.setFilePath("NA");
		    						preVO2.setName("NA");preVO2.setAuditName(preVO2.getIcdProcCode()+"NA");
		    						preVO2.setInvestRemarks("NA");
			    					finlInvestLst.add(preVO2);
			    					}
			    			}
			    			patientForm.setInvestigationLt(finlInvestLst);
			    			request.setAttribute("invetLst",investLst);
			    			request.setAttribute("procList",procList);
			    		}
			    	}
			    	else if(preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("OP") || preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("IPM"))
			    	{
			    		/*Added to get active state of op case*/
			    		String opActiveMsg=null;
			    		opActiveMsg=patientService.getDaysActiveOP(lStrCaseId);
			    		if(opActiveMsg!=null)
			    		{
			    			request.setAttribute("opActiveMsg",opActiveMsg);
			    			patientForm.setOpActiveMsg(opActiveMsg);
			    		}
			    		/*Added by venkatesh to  get cross conultation details */
			    		List<LabelBean> consultData=new ArrayList<LabelBean>();
			    		consultData=patientService.getConsultDtls(patientId);
			    		patientForm.setConsultData(consultData);
			    		String consultationData="";
			    		if(consultData!=null && consultData.size()>0)
			    		{
			    			int count=0;
			    			for(LabelBean docData:consultData)
			    			{
			    				if(count>0)
			    				{
			    					consultationData=consultationData+"$";
			    				}
			    				consultationData=consultationData+docData.getUnitName()+"~"+docData.getHodName();
			    				count++;
			    			}
			    		}
		 		request.setAttribute("consultationData",consultationData);
			    	patientForm.setOpNo(preauthDtlsVO.getPatientIPNo());
			    	patientForm.setOpRemarks(preauthDtlsVO.getRemarks());
			    	patientForm.setDiagnosedBy(preauthDtlsVO.getDocType());	
			    	if(preauthDtlsVO.getDrugList()!=null && preauthDtlsVO.getDrugList().size()>0){
			    	patientForm.setDrugLt(preauthDtlsVO.getDrugList());
			    	String drugLst="";
			    	for(DrugsVO drugVO: preauthDtlsVO.getDrugList()){
			    		if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
						{
			    			drugLst = drugLst + drugVO.getDRUGTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"~"+drugVO.getOTHERDRUGNAME()+"~"+drugVO.getQUANTITY()+"~"+drugVO.getDISPDRUGMSTRDRGCODE()+"@#";
						}
			    		else{			    		
			    		if(drugVO.getDRUGCODE()!=null && drugVO.getDRUGCODE().contains("OD"))
			    		{
			    			if(hospCode!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode))
			    				patientForm.setOtherDrugName(drugVO.getOTHERDRUGNAME());
			    			else
			    			{
			    			drugLst = drugLst + drugVO.getOTHERDRUGNAME()+"~"+drugVO.getDRUGCODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"@#";
			    			otherDrugCount=Integer.parseInt(drugVO.getDRUGCODE().substring(2,drugVO.getDRUGCODE().length()));
			    			}
			    		}
			    		else
			    		{
			    			drugLst = drugLst + drugVO.getDRUGTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getPSUBGRPCODE()+"~"+drugVO.getCSUBGRPCODE()+"~"+drugVO.getDRUGCODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"~"+drugVO.getOTHERDRUGNAME()+"~"+drugVO.getQUANTITY()+"@#";
			    		}
			    		}
			    	}
					request.setAttribute("drugsLst",drugLst);
					patientForm.setOtherDrugCount(otherDrugCount);
					request.setAttribute("otherDrugCount",otherDrugCount);
			    	}
			    	}	
			    	else if(preauthDtlsVO.getIpOpFlag()!=null && preauthDtlsVO.getIpOpFlag().equalsIgnoreCase("DOP"))
			    	{
			    		patientForm.setIpNo(preauthDtlsVO.getPatientIPNo());
			    		patientForm.setIpDiagnosedBy(preauthDtlsVO.getDocType());
			    		patientForm.setIpDoctorName(preauthDtlsVO.getDocName());
			    		patientForm.setDocRegNo(preauthDtlsVO.getDocReg());
			    		patientForm.setDocQual(preauthDtlsVO.getDocQual());
			    		patientForm.setDocMobileNo(preauthDtlsVO.getDocMobNo());
			    		patientForm.setAdmissionType(preauthDtlsVO.getAdmType());
			    		patientForm.setLegalCase(preauthDtlsVO.getLegalCaseCheck());
			    		patientForm.setLegalCaseNo(preauthDtlsVO.getLegalCaseNo());
			    		patientForm.setPoliceStatName(preauthDtlsVO.getPoliceStatName());
			    		if(preauthDtlsVO.getDate()!=null)
			    		patientForm.setIpDate(sdf.format(preauthDtlsVO.getDate()));
			    		patientForm.setRemarks(preauthDtlsVO.getRemarks());
			    		List<PreauthVO> investList = patientService.getcaseSurgeryDtls(lStrCaseId);
			    		String investLst="";List<PreauthVO> finlInvestLst = new ArrayList<PreauthVO>();
			    		if(investList!=null && investList.size()>0){
			    			for(PreauthVO preVO: investList){
			    				if(preVO.getLstSplInvet()!=null && preVO.getLstSplInvet().size()>0)
			    					{
			    					for(PreauthVO preVO1 : preVO.getLstSplInvet()){
			    						investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    						PreauthVO preVO2 = new PreauthVO();
			    						preVO2.setAsriCatName(preVO.getAsriCatName());
			    						preVO2.setCatId(preVO.getCatId());
			    						if(preVO.getCatId()!=null && preVO.getCatId().contains("S"))
			    							request.setAttribute("surgProc","Y");
			    						preVO2.setCatName(preVO.getCatName());
			    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
			    						preVO2.setProcName(preVO.getProcName());
			    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
			    						preVO2.setSeqNo(preVO.getSeqNo());
			    						preVO2.setDocName(preVO.getDocName());
			    						preVO2.setDocReg(preVO.getDocReg());
			    						preVO2.setDocMobNo(preVO.getDocMobNo());
			    						preVO2.setDocQual(preVO.getDocQual());
			    						preVO2.setOpProcUnits(preVO.getOpProcUnits());
			    						preVO2.setTherapyId(preVO1.getTherapyId());
			    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   						
			    						investLst = investLst+"~"+preVO1.getTherapyId()+"~"+preVO1.getFilename()+"~"+preVO.getOpProcUnits();
			    						//Added by Srikalyan for Dental Conditions
			    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    						preVO2.setFilename(preVO1.getFilename());
			    						preVO2.setFilePath(preVO1.getIpOpFlag());
			    						if(preVO1.getIpOpFlag()!=null && !("").equalsIgnoreCase(preVO1.getIpOpFlag()))
			    						preVO2.setName("View");
			    						else
			    							preVO2.setName("NA");	
			    						preVO2.setAuditName(preVO.getIcdProcCode()+preVO1.getTherapyId());
			    						preVO2.setInvestRemarks(preVO1.getTherapyId());	
				    					finlInvestLst.add(preVO2);
			    					}
			    					}
			    					else
			    					{
			    					investLst=investLst+preVO.getCatId()+"~"+preVO.getIcdCatCode()+"~"+preVO.getIcdProcCode()+"~"+preVO.getDocName()+"~"+preVO.getDocReg()+"~"+preVO.getDocQual()+"~"+preVO.getDocMobNo();
			    					investLst = investLst+"~NA~NA"+"~"+preVO.getOpProcUnits();
		    						//Added by Srikalyan for Dental Conditions
		    						investLst = investLst+"~"+preVO.getProcName()+"@";
			    					PreauthVO preVO2 = new PreauthVO();
			    					preVO2.setAsriCatName(preVO.getAsriCatName());
		    						preVO2.setCatId(preVO.getCatId());
		    						preVO2.setCatName(preVO.getCatName());
		    						preVO2.setIcdCatCode(preVO.getIcdCatCode());
		    						preVO2.setProcName(preVO.getProcName());
		    						preVO2.setIcdProcCode(preVO.getIcdProcCode());
		    						preVO2.setSeqNo(preVO.getSeqNo());
		    						preVO2.setDocName(preVO.getDocName());
		    						preVO2.setDocReg(preVO.getDocReg());
		    						preVO2.setDocMobNo(preVO.getDocMobNo());
		    						preVO2.setDocQual(preVO.getDocQual());
		    						preVO2.setIpOpFlag(preVO.getIpOpFlag());   		
		    						preVO2.setFilename("NA");
		    						preVO2.setFilePath("NA");
		    						preVO2.setName("NA");preVO2.setAuditName(preVO2.getIcdProcCode()+"NA");
		    						preVO2.setInvestRemarks("NA");
			    					finlInvestLst.add(preVO2);
			    					}
			    			}
			    			patientForm.setInvestigationLt(finlInvestLst);
			    			request.setAttribute("invetLst",investLst);
			    		}
			    	}
			    	}
			    List<LabelBean> symptomsList = new ArrayList<LabelBean>();
			    List<LabelBean> genInvList = new ArrayList<LabelBean>();
			    symptomsList = patientService.getSymptomsDtls(lStrCaseId);
			    String symptomsLst="";
			    int otherSymptomCount=0;
			    for(LabelBean lb:symptomsList){
			    	if(lb.getWFTYPE()!=null && lb.getWFTYPE().contains("OS"))
			    	{
			    		otherSymptomCount++;
			    		patientForm.setOtherSymptomCount(otherSymptomCount);
			    		symptomsLst = symptomsLst+lb.getWFTYPE()+"~"+lb.getDSGNID()+"~"+lb.getID()+"@#";
			    	}
			    	else
			    	{
			    	symptomsLst = symptomsLst+lb.getUNITID()+"~"+lb.getDSGNID()+"~"+lb.getLEVELID()+"@#";
			    	}
			    }
				request.setAttribute("symptomsList",symptomsList);
				request.setAttribute("symptomsLst",symptomsLst);
				patientForm.setConsultFee(50);
				patientForm.setSymList(symptomsList);
				String genInvestLst="";
				long totInvCost=0;
				if(preauthDtlsVO.getInvList()!=null && preauthDtlsVO.getInvList().size()>0){
					for(PreauthVO preauthVo : preauthDtlsVO.getInvList()){
						LabelBean inv = new LabelBean();
						inv.setID(preauthVo.getSPLINVSTID());
						if(preauthVo.getSPLINVSTID()!=null && preauthVo.getSPLINVSTID().contains("OI"))
						{
							if(hospCode!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode))
								patientForm.setOtherInvName(preauthVo.getNAME());
							else
							otherInvestCount=Integer.parseInt(preauthVo.getSPLINVSTID().substring(2,preauthVo.getSPLINVSTID().length()));
						}
						inv.setVALUE(preauthVo.getNAME());
						inv.setLVL(preauthVo.getROUTE());
						inv.setACTION(preauthVo.getTEST());
						inv.setPrice(preauthVo.getPRICE());
						inv.setTYPE(preauthVo.getLABRPTSUBMIT());
						if(preauthVo.getPRICE()!=null)
						{
							totInvCost=totInvCost+Long.valueOf(preauthVo.getPRICE());	
						}
						genInvestLst = genInvestLst+preauthVo.getTEST()+"~"+preauthVo.getSPLINVSTID()+"~"+preauthVo.getNAME()+"~"+preauthVo.getPRICE()+"@";
						genInvList.add(inv);
					}
					patientForm.setGenInvList(genInvList);
					patientForm.setTotInvestPrice(totInvCost);
					patientForm.setTotalOpCost(50+patientForm.getTotInvestPrice());
					request.setAttribute("genInvestFlag", "true");
					request.setAttribute("genInvestLst", genInvestLst);
				}
				//Dispensary Chages
				String dispGenInvestLst="";
				long dispTotInvCost=0;
				 List<LabelBean> dispGenInvList = new ArrayList<LabelBean>();
				if(preauthDtlsVO.getDispnsryList()!=null && preauthDtlsVO.getDispnsryList().size()>0)
				{
					for(PreauthVO preauthVo : preauthDtlsVO.getDispnsryList()){
						LabelBean inv = new LabelBean();
						inv.setID(preauthVo.getDISPSPLINVSTID());
						if(preauthVo.getDISPSPLINVSTID()!=null && preauthVo.getDISPSPLINVSTID().contains("OI"))
						{
							if(hospCode!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode))
								patientForm.setOtherInvName(preauthVo.getDISPNAME());
							else
							otherInvestCount=Integer.parseInt(preauthVo.getDISPSPLINVSTID().substring(2,preauthVo.getDISPSPLINVSTID().length()));
						}
						inv.setVALUE(preauthVo.getDISPNAME());
						inv.setLVL(preauthVo.getDISPROUTE());
						inv.setACTION(preauthVo.getDISPTEST());
						inv.setPrice(preauthVo.getDISPPRICE());
						if(preauthVo.getDISPPRICE()!=null)
						{
							dispTotInvCost=totInvCost+Long.valueOf(preauthVo.getDISPPRICE());	
						}
						dispGenInvestLst = dispGenInvestLst+preauthVo.getDISPTEST()+"~"+preauthVo.getDISPSPLINVSTID()+"~"+preauthVo.getDISPNAME()+"~"+preauthVo.getDISPPRICE()+"@";
						dispGenInvList.add(inv);
					}
					patientForm.setDispGenInvList(dispGenInvList);
					patientForm.setTotInvestPrice(dispTotInvCost);
					patientForm.setTotalOpCost(50+patientForm.getTotInvestPrice());
					request.setAttribute("genInvestFlag", "true");
					request.setAttribute("dispGenInvestLst", dispGenInvestLst);
				}
			}			
				request.setAttribute("serverDt",serverDt);
				request.setAttribute("schemeId",ehfPatient.getSchemeId());
				request.setAttribute("dentalEnhFlag","N");
				patientForm.setOtherInvestCount(otherInvestCount);
				request.setAttribute("otherInvestCount",otherInvestCount);
				request.setAttribute("PatientOpList",preauthDtlsVO);
				request.setAttribute("hospType",patientForm.getHosptype());
				String ceoApprovalFlag=preauthDtlsVO.getCeoApprovalFlag();
				request.setAttribute("dentalSpecialAppvFlag",ceoApprovalFlag);
				//(config.getString("HOSP_NIMS").equalsIgnoreCase(hospCode)) 
				/*dentalrntdbvalue retrieved to redirect the pageto respective case sheet */
				String dentalrntdbvalue=preauthDtlsVO.getTreatmentDntl();
				request.setAttribute("dentalrntdbvalue", dentalrntdbvalue);
				/*
				 * Check whether NIMS Hospital to remove IP surgical radio button 
				 */
				/*boolean nabhFlag=false;
				if(lStrgrpList.contains(config.getString("Group.Medco")) || lStrgrpList.contains(config.getString("Group.Mithra")))
				{
					nabhFlag=true;													
				}																	
				if(nabhFlag)
				{
					if(!"".equalsIgnoreCase(lStrUserId) && lStrUserId != null)
					{
						boolean  checkNimsMedco = loginService.checkNimsMedco(lStrUserId);
						if(checkNimsMedco)
						{
							request.setAttribute("nimsHosp", "Y");
						}
						else
						{
							request.setAttribute("nimsHosp", "N");
						}
					}
				}
				*/
				if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					if(ehfPatient.getRegHospId() != null)
					{
						patientForm.setCurrHospId(ehfPatient.getRegHospId());
					}
					if(ehfPatient.getRefSpclty() != null)
					{
						patientForm.setAsriCatName(ehfPatient.getRefSpclty());
					}
					if(ehfPatient.getRefDist() != null)
					{
						patientForm.setDistrict(ehfPatient.getRefDist());
					}
				}
				String criticalFlg=patientService.getCriticalFlg(lStrCaseId);
				if(criticalFlg!=null && !"".equalsIgnoreCase(criticalFlg))
				{
					patientForm.setCritical(criticalFlg);
				}
				if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)){
						lStrPageName="DispnsryPatientDtls";	
						if("GP796".equalsIgnoreCase(ehfPatient.getSpecialityType()))
							lStrPageName="DispDentalPatientDetails";
				}
				else if((hospGov!=null && "G".equalsIgnoreCase(hospGov) && lStrCaseId == null) ||
						(hospGov!=null && "G".equalsIgnoreCase(hospGov) && "no".equalsIgnoreCase(dentalrnt)) ||
						(hospGov!=null && "G".equalsIgnoreCase(hospGov) && "no".equalsIgnoreCase(dentalrntdbvalue)))
				{
				lStrPageName="patientDetailsNims";	
				}
				else if ((dentalFlg !=null && "Y".equalsIgnoreCase(dentalFlg) && "yes".equalsIgnoreCase(dentalrnt)) || 
						(dentalFlg !=null && "Y".equalsIgnoreCase(dentalFlg) && "yes".equalsIgnoreCase(dentalrntdbvalue)))
				{
					lStrPageName="dentalPatientDetails";
				}
				else
				lStrPageName="patientDetails";
			}
			else if(pageType.equalsIgnoreCase("print"))
			{
				if(aisSubmit!=null && "Y".equalsIgnoreCase(aisSubmit))
				{
					lStrPageName="printDetailsAis";
				}
				else
				{
				lStrPageName="printDetails";
		}
			}
		}
		if ("getHospitals".equalsIgnoreCase(lStrActionVal))
		{
			String district=request.getParameter("district");			
			String speciality=request.getParameter("speciality");			
			 hospDtlsList=null;
			List<String> hospListArray = new ArrayList<String>();
			hospDtlsList=patientService.getHospDtlsList(district,speciality);
				if (hospDtlsList != null && hospDtlsList.size() > 0)
				{	
					for (LabelBean labelBean: hospDtlsList) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							hospListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", hospListArray);
					request.setAttribute("hospDtlsList", hospDtlsList);
				}
			lStrPageName="ajaxResult";
		}
		if ("getMedSpecility".equalsIgnoreCase(lStrActionVal))
		{
			String critical=request.getParameter("critical");			
			List<String> specialities = new ArrayList<String>();
			List<LabelBean> medicalCatList1=commonService.getMedicalSpecialities(medHosp, schemeId, critical);   
			request.setAttribute("medicalCatList", medicalCatList1);
				if (medicalCatList1 != null && medicalCatList1.size() > 0)
				{	
					for (LabelBean labelBean: medicalCatList1) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							specialities.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", specialities);
				}
			lStrPageName="ajaxResult";
		}
		if ("getAllSpecility".equalsIgnoreCase(lStrActionVal))
		{
			List<String> specialities = new ArrayList<String>();
			List<LabelBean> medicalCatList1=commonService.getMedicalSpltyList(medHosp, schemeId);   
			request.setAttribute("medicalCatList", medicalCatList1);
				if (medicalCatList1 != null && medicalCatList1.size() > 0)
				{	
					for (LabelBean labelBean: medicalCatList1) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							specialities.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", specialities);
				}
			lStrPageName="ajaxResult";
		}
		if ("getMedicalSubCat".equalsIgnoreCase(lStrActionVal))
		{
			List<String> hospListArray = new ArrayList<String>();
			List<LabelBean> medicalCatList1=null;
			String medicalSpclty=request.getParameter("medicalSpclty");			
			String critical=request.getParameter("critical");
			if(critical!=null && !"".equalsIgnoreCase(critical) && (critical.equalsIgnoreCase("Y") || critical.equalsIgnoreCase("N") ) )
			{
				 medicalCatList1=commonService.getMedicalCatgryListCritical(medicalSpclty, critical);    
			}
			else
			{
				 medicalCatList1=commonService.getMedicalCatgryList(medicalSpclty);     //getDrugs();
			}
				request.setAttribute("medicalCatList", medicalCatList1);
				if (medicalCatList1 != null && medicalCatList1.size() > 0)
				{	
					for (LabelBean labelBean: medicalCatList1) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							hospListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", hospListArray);
				}
			lStrPageName="ajaxResult";
		}
		if ("getDrugSubTypeList".equalsIgnoreCase(lStrActionVal))
		{
			String drugTypeCode=request.getParameter("drugTypeCode");			
			drugTypeList=null;
			List<String> hospListArray = new ArrayList<String>();
			drugTypeList=commonService.getDrugTypeList(drugTypeCode);
				if (drugTypeList != null && drugTypeList.size() > 0)
				{	
					for (LabelBean labelBean: drugTypeList) {
						if (labelBean.getID() != null && 
								labelBean.getVALUE() != null)
						{
							hospListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
						}
					}
					request.setAttribute("AjaxMessage", hospListArray);
					request.setAttribute("drugTypeList", drugTypeList);
				}
			lStrPageName="ajaxResult";
		}
		if("getDentalCaseSheetList".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> dentalSubmoduleList=null;
			String type=request.getParameter("type");
			List<String> subdentalList = new ArrayList<String>();
			String mainCompId=request.getParameter("mainCompId");
			dentalSubmoduleList=patientService.getdentalexaminationDtls(mainCompId);
			if("mainlst0".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist0", dentalSubmoduleList);
			else if("mainlst1".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist1", dentalSubmoduleList);
			else if("mainlst2".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist2", dentalSubmoduleList);
			else if("mainlst3".equalsIgnoreCase(type))
				request.setAttribute("Dentalmainlist3", dentalSubmoduleList);
			else if("sublst0".equalsIgnoreCase(type))
				request.setAttribute("Dentalsublistrl0", dentalSubmoduleList);
			else if("sublst1".equalsIgnoreCase(type))
				request.setAttribute("Dentalsublistjaws1", dentalSubmoduleList);
			patientForm.setDentalsublist(dentalSubmoduleList);
			if (dentalSubmoduleList != null && dentalSubmoduleList.size() > 0) {
				for (LabelBean labelBean : dentalSubmoduleList) {
					if (labelBean.getID() != null && labelBean.getVALUE() != null)
					{
						if (dentalSubmoduleList != null && dentalSubmoduleList.size() > 0) {
							subdentalList.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						} else
							subdentalList.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						//catList.add(labelBean.getICDCODE() + "~"+ labelBean.getICDNAME());
						}
				}
			}
			    if(dentalSubmoduleList!=null && dentalSubmoduleList.size()>0)
				request.setAttribute("AjaxMessage", subdentalList);
			     lStrPageName="ajaxResult";
		}
		if ("getProcName".equalsIgnoreCase(lStrActionVal))
			{
				String ajaxMsg=null;
				String icdProcCode=request.getParameter("icdProcCode");
				callType=request.getParameter("callType");
				String patSchemeId=request.getParameter("patSchemeId");
				if(icdProcCode !=null && !"".equalsIgnoreCase(icdProcCode) &&
						callType!=null && !"".equalsIgnoreCase(callType) &&
						patSchemeId!=null && !"".equalsIgnoreCase(patSchemeId))
					{
						if(callType.equalsIgnoreCase("Ajax"))
							{
								PreauthVO resultVO=new PreauthVO();
								resultVO=patientService.getProcName(icdProcCode,patSchemeId);
								if(resultVO!=null && resultVO.getProcName()!=null)
									{	
										ajaxMsg =resultVO.getProcName();
									}
							}
					}
				request.setAttribute("AjaxMessage", ajaxMsg);
				lStrPageName="ajaxResult";
			}
		if ("getPersonalHistory".equalsIgnoreCase(lStrActionVal))
		{
			String parntCode=null;
			List<String> personalHistoryList = new ArrayList<String>(); 
			if(request.getParameter("parntCode")!=null && !request.getParameter("parntCode").equals(""))
			{
				parntCode=request.getParameter("parntCode");
			}
			List<LabelBean> pHistoryList=null;
			pHistoryList=commonService.getPersonalHistory(parntCode);
			try
			{
				for (LabelBean labelBean: pHistoryList) {
					if (labelBean.getID() != null && 
							labelBean.getVALUE() != null)
					{
						personalHistoryList.add(labelBean.getID() + "~" + labelBean.getVALUE());
					}
				}
				if (personalHistoryList != null && personalHistoryList.size() > 0)
					request.setAttribute("AjaxMessage", personalHistoryList);
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred in Ajax getLocations actionFlag in PatientAction." +e.getMessage()) ;
			}
			lStrPageName="ajaxResult";
		}
		if ("getTherapyInvestigations".equalsIgnoreCase(lStrActionVal))
		{
			String therapyId=null;String asriCode=null,scheme=null;
			if(request.getParameter("therapyId")!=null && !request.getParameter("therapyId").equals(""))
			{
				therapyId=request.getParameter("therapyId");        		
			}
			if(request.getParameter("asriCode")!=null && !request.getParameter("asriCode").equals(""))
			{
				asriCode=request.getParameter("asriCode");        		
			}
			if(request.getParameter("scheme")!=null && !request.getParameter("scheme").equals(""))
			{
				scheme=request.getParameter("scheme");        		
			}
			patientVO=new PatientVO();
			patientVO.setTherapyCatId(therapyId);
			patientVO.setAsriCatCode(asriCode);
			patientVO.setSchemeId(schemeId);
			List<String> getInvestigationsList = patientService.getSpecialInvestigationLst(patientVO);
			GLOGGER.info("size of List"+getInvestigationsList.size());
			String procedureType= patientService.getProcedureType(therapyId, asriCode);
			GLOGGER.info("Proc Type"+procedureType);
			request.setAttribute("AjaxMessage",getInvestigationsList);
			request.setAttribute("AjaxMessage1",procedureType);
			lStrPageName="ajaxResult";	
		}
		if ("deleteInvestigationsOnload".equalsIgnoreCase(lStrActionVal))
		{
			String investId = request.getParameter("investId");
			String caseId = request.getParameter("caseId");
			String procCode = request.getParameter("procId");
			String asriCode =  request.getParameter("asriCode");
			String result = patientService.deleteInvestigations(caseId,procCode,investId,asriCode);
			request.setAttribute("AjaxMessage",result);
			lStrPageName="ajaxResult";	
		}	if ("deleteGenInvest".equalsIgnoreCase(lStrActionVal))
		{
			String investId = request.getParameter("investId");
			String patientId = request.getParameter("patientNo");
			String result = patientService.deleteGenInvest(patientId,investId);
			request.setAttribute("AjaxMessage",result);
			lStrPageName="ajaxResult";	
		}		
		if("getDrugSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugTypeId");        		
			}
			drugSubList=patientService.getDrugSubList(drugTypeId);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getOpDrugSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("lStrDrugTypeId")!=null && !request.getParameter("lStrDrugTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugTypeId");        		
			}
			drugSubList=patientService.getOpDrugSubList(drugTypeId,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getPharDrugList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String drugTypeId=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("lStrDrugSubTypeId")!=null && !request.getParameter("lStrDrugSubTypeId").equals(""))
			{
				drugTypeId=request.getParameter("lStrDrugSubTypeId");        		
			}
			drugSubList=patientService.getOpPharSubList(drugTypeId,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getOpChemSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String pharSubCode=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("pharSubCode")!=null && !request.getParameter("pharSubCode").equals(""))
			{
				pharSubCode=request.getParameter("pharSubCode");        		
			}
			drugSubList=patientService.getOpChemSubList(pharSubCode,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getChemSubList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugSubList=null;
			String cSubGrpCode=null;
			ipOpType = request.getParameter("ipOpType");
			if(request.getParameter("cSubGrpCode")!=null && !request.getParameter("cSubGrpCode").equals(""))
			{
				cSubGrpCode=request.getParameter("cSubGrpCode");        		
			}
			drugSubList=patientService.getChemSubList(cSubGrpCode,ipOpType);
			if (drugSubList != null && drugSubList.size() > 0)
				request.setAttribute("AjaxMessage", drugSubList);
			lStrPageName="ajaxResult";
		}
		if("getRouteList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> routeList=null;
			String routeTypeCode=null;
			if(request.getParameter("routeTypeCode")!=null && !request.getParameter("routeTypeCode").equals(""))
			{
				routeTypeCode=request.getParameter("routeTypeCode");        		
			}
			ipOpType = request.getParameter("ipOpType");
			routeList=patientService.getRouteList(routeTypeCode,ipOpType);
			if (routeList != null && routeList.size() > 0)
				request.setAttribute("AjaxMessage", routeList);
			lStrPageName="ajaxResult";
		}
		if("getStrengthList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> strengthList=null;
			String strengthTypeCode=null;
			if(request.getParameter("strengthTypeCode")!=null && !request.getParameter("strengthTypeCode").equals(""))
			{
				strengthTypeCode=request.getParameter("strengthTypeCode");        		
			}
			ipOpType = request.getParameter("ipOpType");
			strengthList=patientService.getStrengthList(strengthTypeCode,ipOpType);
			if (strengthList != null && strengthList.size() > 0)
				request.setAttribute("AjaxMessage", strengthList);
			lStrPageName="ajaxResult";
		}
		if ("getDrugList".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drugListArray = new ArrayList<String>();
			List<LabelBean> drugList=null;
			String drugSubTypeId=null;
			if(request.getParameter("lStrDrugSubTypeId")!=null && !request.getParameter("lStrDrugSubTypeId").equals(""))
			{
				drugSubTypeId=request.getParameter("lStrDrugSubTypeId");        		
			}
			drugList=commonService.getAsriDrugs(drugSubTypeId);
			try
			{
				for (LabelBean labelBean: drugList) {
					if (labelBean.getID() != null && 
							labelBean.getVALUE() != null)
					{
						drugListArray.add(labelBean.getID() + "~" + labelBean.getVALUE()+"@");
					}
				}
				if (drugListArray != null && drugListArray.size() > 0)
					request.setAttribute("AjaxMessage", drugListArray);
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred in Ajax getDrugList actionFlag in PatientAction." +e.getMessage()) ;
			}
			lStrPageName="ajaxResult";	
		}
		if("getDrugDetails".equalsIgnoreCase(lStrActionVal))
		{
			String drugCode=null;
			if(request.getParameter("lStrDrugCode")!=null && !request.getParameter("lStrDrugCode").equals(""))
			{
				drugCode=request.getParameter("lStrDrugCode");        		
			}
			String drugDetails=patientService.getDrugDetails(drugCode);
			request.setAttribute("AjaxMessage",drugDetails);
			lStrPageName="ajaxResult";	
		}
		if ("getDoctorListAjax".equalsIgnoreCase(lStrActionVal))
		{
			String lStrHospId=null;
			lStrHospId=request.getParameter("hospId");
			fromDisp=request.getParameter("fromDisp");
			List<String> doctorsList = new ArrayList<String>();
			List<LabelBean> lDoctorList=null;
			String lStrDocType=null;
			if(request.getParameter("docTypeVal")!=null && !request.getParameter("docTypeVal").equals(""))
			{
				lStrDocType=request.getParameter("docTypeVal");
				try{
					if(request.getParameter("patientType")!=null && !request.getParameter("patientType").equals(""))
					{
						String consultant="";
						String therapyCategory=request.getParameter("therapyCategory");
						if(config.getString("doctorType.InHouseDoctor").equalsIgnoreCase(lStrDocType))
						{
							consultant="N";
						}
						else if(config.getString("doctorType.Consultant").equalsIgnoreCase(lStrDocType))
						{
							consultant="Y";
						}
						patientVO=new PatientVO();
						patientVO.setHospitalCode(lStrHospId);
						patientVO.setTherapyCatId(therapyCategory);
						patientVO.setDoctorType(consultant);
						lDoctorList=patientService.getDocAvailLst(patientVO);
						lDoctorList=patientService.getDoctorsList(lStrDocType,lStrHospId,schemeId,fromDisp, roleId);
					}
					//End Of IP Doctor Details based on therapy category
					else
					{
						lDoctorList=patientService.getDoctorsList(lStrDocType,lStrHospId,schemeId,fromDisp, roleId);
					}
				}
				catch(Exception e){
					GLOGGER.error ( "Exception occurred using getDoctorListAjax actionFlag in PatientAction." +e.getMessage()) ;
				}
				if(lDoctorList!=null && lDoctorList.size()>0)
				{
					for (LabelBean labelBean: lDoctorList) {
						if (labelBean.getID() != null && labelBean.getVALUE() != null)
						{
							doctorsList.add(labelBean.getID() + "~" + 
									labelBean.getVALUE());
						}
					}
					GLOGGER.info ( "Retrieving Doctor Details using Ajax Call in PatientAction." ) ;
					//doctorsList.add("0~Others");
				}
				if(doctorsList!=null && doctorsList.size() > 0)
					request.setAttribute("AjaxMessage", doctorsList);
			}
			lStrPageName="ajaxResult";
		}
		if ("getDoctorsDetails".equalsIgnoreCase(lStrActionVal))
		{
			String lStrHospId=null;
			String lStrDocType=null;
			String lStrDocId=null;
			if(request.getParameter("hospId")!=null && !request.getParameter("hospId").equals("")) 
			{
				lStrHospId=request.getParameter("hospId");
			}
			if(request.getParameter("doctorType")!=null && !request.getParameter("doctorType").equals("")) 
			{
				lStrDocType=request.getParameter("doctorType");
			}
			if(request.getParameter("doctId")!=null && !request.getParameter("doctId").equals("")) 
			{
				lStrDocId=request.getParameter("doctId");
			}
			List<LabelBean>  lDocDataList=null;
			try
			{
				lDocDataList= patientService.getSelDocDetails(lStrDocType,lStrHospId,lStrDocId,schemeId) ;
			}
			catch(Exception e)
			{
				GLOGGER.error ( "Exception occurred using getDoctorsDetails actionFlag in PatientAction." +e.getMessage()) ;
			}
			String lDocResult=null;
			if(lDocDataList!=null && lDocDataList.size() > 0)
			{
				if(lDocDataList.get(0).getID()!=null){
					lDocResult=lDocDataList.get(0).getID();
				}
				else{
					lDocResult="NA";
				}
				if(lDocDataList.get(0).getVALUE()!=null){
					lDocResult=lDocResult+"~"+lDocDataList.get(0).getVALUE();     
				}
				else{
					lDocResult=lDocResult+"~NA";  
				}
				if(lDocDataList.get(0).getDSGNNAME()!=null){
					lDocResult=lDocResult+"~"+lDocDataList.get(0).getDSGNNAME();    
				}
				else{
					lDocResult=lDocResult+"~NA";   
				}
			}
			request.setAttribute("AjaxMessage", lDocResult);
			lStrPageName="ajaxResult";
		}
		if ("saveCaseDetails".equalsIgnoreCase(lStrActionVal))
		{
			try{
			String caseStartTime = sds.format(new Date().getTime());
			patientForm.setPatientNo("");
			String saveFlg = request.getParameter("saveFlag");
			 fromDisp = request.getParameter("fromDisp");
			String  contactno = request.getParameter("contactno");
			String  name = request.getParameter("name");
			String  dtoCs = request.getParameter("dtoCs");
			String  patientNo = request.getParameter("patientNo");
			String dispDental=request.getParameter("dispDental");
			SimpleDateFormat sdfp=new SimpleDateFormat("dd-MM-yyyy");
			String patCrtDt=sdfp.format(session.getAttribute("patCrtdt"));	
			Date date = new Date();
			String crtDate=sdfw.format(date);
			patientVO=new PatientVO();
			String surgeryNextVal="";
			String lStrPatientId=null;
			String treatingDocRmks=null;
			String investigationList=null;
			String genInvestigationList=null;
			String updateGenInvestList=null;
			int i=0;
			String lDir=null;
			String lFileName=null;
			String lFileExt=null;
			int lCount=0;
			String lStrTotFileName=null;
			Date ldtToday = new Date();
			long ltime = ldtToday.getTime();
			List<LabelBean> lSelInvList=new ArrayList<LabelBean>();
			List<LabelBean> lGenInvList=new ArrayList<LabelBean>();
			List<LabelBean> lUpdateGenInvList=new ArrayList<LabelBean>(); 
			String userId=null;
			String checkType=null;
			 if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					patientForm.setFromDisp(fromDisp);
					patientVO.setFromDisp(fromDisp);
					request.setAttribute("fromDisp", fromDisp);
					String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
					patientVO.setDispCode(dispId);
					if(dispDental!=null && dispDental.equalsIgnoreCase("Y")){
						patientVO.setDispDentalFlag(dispDental);
						patientVO.setDiabetesRemarks(patientForm.getTextValDH1());
						patientVO.setHypertensionRemarks(patientForm.getTextValDH2());
						patientVO.setCardiacHistRemarks(patientForm.getTextValDH3());
						patientVO.setDrugAllergyRemarks(patientForm.getTextValDH4());
						patientVO.setThyroidRemarks(patientForm.getTextValDH5());
						patientVO.setOralProphylaxisRemarks(patientForm.getTextValDH6());
						patientVO.setCompositeRemarks(patientForm.getTextValDH71());
						patientVO.setGicRemarks(patientForm.getTextValDH72());
						patientVO.setPitFissureSealantsRemarks(patientForm.getTextValDH73());
						patientVO.setExtractionRemarks(patientForm.getTextValDH8());
						patientVO.setMedicationRemarks(patientForm.getTextValDH9());
					}
				}	
				else
				{
					patientForm.setFromDisp("N");
					patientVO.setFromDisp("N");
					request.setAttribute("fromDisp", "N");
				}
			userId=session.getAttribute("EmpID").toString(); 
			if(request.getParameter("patientId")!=null && !request.getParameter("patientId").equals("")){
				lStrPatientId=request.getParameter("patientId");
			}
			if(request.getParameter("treatingDocRmks")!=null && !request.getParameter("treatingDocRmks").equals("")){
				treatingDocRmks=request.getParameter("treatingDocRmks");
			}
			if(request.getParameter("checkType")!=null && !request.getParameter("checkType").equals("")){
				checkType=request.getParameter("checkType");
				patientVO.setCheckType(checkType);
			}		
			String subdentalJawTxt = request.getParameter("subdentalJawTxt");
			patientVO.setSchemeId(patientForm.getScheme());
			patientVO.setPatientScheme(patientForm.getPatientScheme());
			patientVO.setHospType(patientForm.getHosptype());
			patientVO.setPersonalHistVal(patientForm.getPersonalHistVal());
			patientVO.setExamFndsVal(patientForm.getExamFndsVal());
			//pavan dental case sheet 
			patientVO.setTreatmentDntl(patientForm.getTreatmentDntl());
			patientVO.setDrughistoryid(patientForm.getDrughistoryid());
			if(patientForm.getDrughistory()!=null || patientForm.getDrughistory()!="")
			patientVO.setDrughistory(patientForm.getDrughistory());
			patientVO.setMedicalDtlsid(patientForm.getMedicalDtlsid());
			patientVO.setShowMedicalTextval(patientForm.getShowMedicalTextval());
			patientVO.setExtraoral(patientForm.getExtraoral());
			patientVO.setIntraoral(patientForm.getIntraoral());
			patientVO.setContactNo(contactno);
			//added for swelling and pus discharge details
			patientVO.setSwSite(patientForm.getSwSite());
			patientVO.setSwSize(patientForm.getSwSize());
			patientVO.setSwExtension(patientForm.getSwExtension());
			patientVO.setSwColour(patientForm.getSwColour());
			patientVO.setSwConsistency(patientForm.getSwConsistency());
			patientVO.setSwTenderness(patientForm.getSwTenderness());
			patientVO.setSwBorders(patientForm.getSwBorders());
			patientVO.setPsSite(patientForm.getPsSite());
			patientVO.setPsDischarge(patientForm.getPsDischarge());			
			patientVO.setSubdental0(patientForm.getSubdental0());
			patientVO.setSubdental1(patientForm.getSubdental1());
			patientVO.setSubdental2(patientForm.getSubdental2());
			patientVO.setSubdental3(patientForm.getSubdental3());
			patientVO.setDntsublistoral0(patientForm.getDntsublistoral0());
			patientVO.setDntsublistoral1(patientForm.getDntsublistoral1());
			patientVO.setDntsublistoral2(patientForm.getDntsublistoral2());
			patientVO.setDntsublistoral3(patientForm.getDntsublistoral3());
			patientVO.setDntsublistoral4(patientForm.getDntsublistoral4());
			patientVO.setDntsublistoral5(patientForm.getDntsublistoral5());
			patientVO.setDntsublistoral6(patientForm.getDntsublistoral6());
//			patientVO.setSubdentalrl0(patientForm.getSubdentalrl0());
			patientVO.setSubdentaljaws1(patientForm.getSubdentaljaws1());
			if("CH68".equalsIgnoreCase(patientVO.getSubdental0()))
				patientVO.setSubdentalrltxt(patientForm.getSubdentalrltxt());
			else
				patientVO.setSubdentalrl0(patientForm.getSubdentalrl0());
//			patientVO.setSubdentaljawstxt(patientForm.getSubdentaljawstxt());
			if(!"".equalsIgnoreCase(subdentalJawTxt) && subdentalJawTxt != null)
				patientVO.setSubdentaljawstxt(subdentalJawTxt);
			patientVO.setCarriesdecidous(patientForm.getCarriesdecidous());
			patientVO.setMissingdecidous(patientForm.getMissingdecidous());
			patientVO.setMobiledecidous(patientForm.getMobiledecidous());
			patientVO.setGrosslydecadedecidous(patientForm.getGrosslydecadedecidous());
			patientVO.setCarriespermanent(patientForm.getCarriespermanent());
			patientVO.setMobilitypermanent(patientForm.getMobilitypermanent());
			patientVO.setRootstumppermannet(patientForm.getRootstumppermannet());
			patientVO.setAttritionpermanent(patientForm.getAttritionpermanent());
			patientVO.setMissingpermanent(patientForm.getMissingpermanent());
			patientVO.setProbingdepth(patientForm.getProbingdepth());
			patientVO.setOtherpermanent(patientForm.getOtherpermanent());
			patientVO.setPreviousDentalTreatment(patientForm.getPreviousDentalTreatment());
			patientVO.setOcclusion(patientForm.getOcclusion());
			patientVO.setOcclusionType(patientForm.getOcclusionType());
			patientVO.setOcclusionOther(patientForm.getOcclusionOther());
			patientVO.setDiagnosis(patientForm.getDiagnosis());
			patientVO.setFollowupAdv(patientForm.getFollowupAdv());
			patientVO.setAdmissionDetails(patientForm.getAdmissionDetails());
			patientVO.setAdvancedInvestigations(patientForm.getAdvancedInvestigations());
			patientVO.setMedicationGiven(patientForm.getMedicationGiven());
			patientVO.setCrtDt(crtDate);
			patientVO.setCaseSurgId(surgeryNextVal);
			patientVO.setPatientId(lStrPatientId);
			patientVO.setCrtUsr(userId);
			if(grpId!=null)
			patientVO.setCrtUsrGrp(grpId);
			patientVO.setLangId(lStrLangID);
			patientVO.setUserRole(roleId);
			//if(patientForm.getComplaints()!=null && !patientForm.getComplaints().equalsIgnoreCase("") && !patientForm.getComplaints().equalsIgnoreCase("-1"))
			patientVO.setComplaints(patientForm.getComplaintCode());
			patientVO.setPatientComplaint(patientForm.getPatientComplaint());
			if(patientForm.getPresentHistory()!=null && patientForm.getPresentHistory().length()>3000)
			{
				patientVO.setPresentHistory(patientForm.getPresentHistory().substring(0, 3000));
			}
			else if(patientForm.getPresentHistory()!=null)
			{
				patientVO.setPresentHistory(patientForm.getPresentHistory());
			}
			else 
				patientVO.setPresentHistory("");
			if(patientForm.getOtherComplaint()!=null && !("").equalsIgnoreCase(patientForm.getOtherComplaint()))
			{
				patientVO.setOtherComplaint(patientForm.getOtherComplaint());
			}
			else
			{
				patientVO.setOtherComplaint("");
			}
			patientVO.setPersonalHistory(patientForm.getPersonalHistory());
			patientVO.setFamilyHistory(patientForm.getFamilyHistory());
			patientVO.setPastHistory(patientForm.getPastHistory());
			patientVO.setExaminationFnd(patientForm.getExaminationFnd());
			patientVO.setFamilyHistoryOthr(patientForm.getFamilyHistoryOthr());
			patientVO.setPastHistryOthr(patientForm.getPastHistryOthr());
			patientVO.setPastHistryCancers(patientForm.getPastHistryCancers()); 
			patientVO.setPastHistryEndDis(patientForm.getPastHistryEndDis());
			if(patientForm.getPastHistrySurg()!=null && patientForm.getPastHistrySurg().length()>3000)
			{
				patientVO.setPastHistrySurg(patientForm.getPastHistrySurg().substring(0, 3000));
			}
			else if(patientForm.getPastHistrySurg()!=null)
			{
				patientVO.setPastHistrySurg(patientForm.getPastHistrySurg());
			}
			if(patientForm.getEnablePharma()!=null)
				patientVO.setEnablePharma(patientForm.getEnablePharma());
			if(patientForm.getDiagType()!=null && !patientForm.getDiagType().equalsIgnoreCase("") && !patientForm.getDiagType().equalsIgnoreCase("-1"))
			{
				patientVO.setDiagnosisType(patientForm.getDiagType());
//				GLOGGER.error("Info Message while setting Diagnosis Type "+patientForm.getDiagType()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getMainCatName()!=null && !patientForm.getMainCatName().equalsIgnoreCase("") && !patientForm.getMainCatName().equalsIgnoreCase("-1"))
			{
				patientVO.setMainCatName(patientForm.getMainCatName());
//				GLOGGER.error("Info Message while setting Diagnosis Main Category Code "+patientForm.getMainCatName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getCatName()!=null && !patientForm.getCatName().equalsIgnoreCase("") && !patientForm.getCatName().equalsIgnoreCase("-1"))
			{
				patientVO.setCatName(patientForm.getCatName());
//				GLOGGER.error("Info Message while setting Diagnosis Category Code "+patientForm.getCatName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getSubCatName()!=null && !patientForm.getSubCatName().equalsIgnoreCase("") && !patientForm.getSubCatName().equalsIgnoreCase("-1"))
			{
				patientVO.setSubCatName(patientForm.getSubCatName());
//				GLOGGER.error("Info Message while setting Diagnosis Sub Category Code "+patientForm.getSubCatName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getDiseaseName()!=null && !patientForm.getDiseaseName().equalsIgnoreCase("") && !patientForm.getDiseaseName().equalsIgnoreCase("-1"))
			{
				patientVO.setDiseaseName(patientForm.getDiseaseName());
//				GLOGGER.error("Info Message while setting Diagnosis Disease Code "+patientForm.getDiseaseName()+" for patient Id "+lStrPatientId);
			}
			if(patientForm.getDisAnatomicalName()!=null && !patientForm.getDisAnatomicalName().equalsIgnoreCase("") && !patientForm.getDisAnatomicalName().equalsIgnoreCase("-1"))
			{
				patientVO.setDisAnatomicalName(patientForm.getDisAnatomicalName());
//				GLOGGER.error("Info Message while setting Diagnosis Disease Anatomical Code "+patientForm.getDisAnatomicalName()+" for patient Id "+lStrPatientId);
			}
			patientVO.setOtherDiagName("");
			String hospitalId=patientForm.getHospitalId();
			if(hospitalId!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId) && !("").equalsIgnoreCase(patientForm.getDiagOthersName()) )
			{
				patientVO.setOtherDiagName(patientForm.getDiagOthersName());
				patientVO.setDisAnatomicalName("Others");
				patientVO.setDiseaseName("Others");
				patientVO.setSubCatName("Others");
				patientVO.setCatName("Others");
				patientVO.setMainCatName("Others");
				patientVO.setDiagnosisType("Others");	
			}
			else if(patientForm.getDiagOthers()!=null && ("Y").equalsIgnoreCase(patientForm.getDiagOthers())&& patientForm.getDiagOthersName()!=null && !("").equalsIgnoreCase(patientForm.getDiagOthersName()))
			{
				patientVO.setOtherDiagName(patientForm.getDiagOthersName());
				patientVO.setDisAnatomicalName("Others");
				patientVO.setDiseaseName("Others");
				patientVO.setSubCatName("Others");
				patientVO.setCatName("Others");
				patientVO.setMainCatName("Others");
				patientVO.setDiagnosisType("Others");
			}
			List<LabelBean> symptomsList = new ArrayList<LabelBean>();
			LabelBean symptom = null;
			if(patientForm.getSymptoms()!=null && !patientForm.getSymptoms().equalsIgnoreCase("")){
			String sym=patientForm.getSymptoms().substring(0, patientForm.getSymptoms().length()-1);
			String[] symptoms=sym.split("@,");
			for(int z=0;z<symptoms.length;z++)
			{
				String symptm=symptoms[z];
				if(symptm.contains("@"))
					{String[] symptoms1=symptm.split("@");
					symptm=symptoms1[0];
					}
				String[] symptmValues=symptm.split("~");
				symptom = new LabelBean();
				if(symptmValues[0]!=null && symptmValues[0].contains("OS"))
				{
					symptom.setID(symptmValues[0]);
					symptom.setSUBCODE("others");
					symptom.setVALUE("others");
					symptom.setOtherSymptomName(symptmValues[2]);
				}
				else
				{
				symptom.setID(symptmValues[0]);
				symptom.setSUBCODE(symptmValues[1]);
				symptom.setVALUE(symptmValues[2]);
				}
				symptomsList.add(symptom);
			}
			patientVO.setSymptoms(symptomsList);
			}
			List<LabelBean> consultList = new ArrayList<LabelBean>();
			if(request.getParameter("consultationData")!=null && !("").equalsIgnoreCase(request.getParameter("consultationData")))
			{
				String[] consultation=request.getParameter("consultationData").split("~");
				for(String cons:consultation)
				{
					LabelBean consultData = new LabelBean();
					StringTokenizer st=new StringTokenizer(cons,"$");
					while(st.hasMoreTokens())
					{	
						consultData.setUnitName(st.nextToken());
						consultData.setDiagnoisedBy(st.nextToken());
						consultData.setHodName(st.nextToken());
					}
					consultList.add(consultData);
				}
				patientVO.setConsultList(consultList);
			}
			patientVO.setPatientType(patientForm.getPatientType());
			//patientVO.setCaseId(liNextVal);
			if(patientForm.getPatientType()!=null && (patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IP"))) 
					|| patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IPD")))) )
			{
				patientVO.setDiagnosedBy(patientForm.getIpDiagnosedBy());
				patientVO.setDoctorName(patientForm.getIpDoctorName());
				if( (!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)) )
				{
					if(!"".equalsIgnoreCase(patientForm.getCurrHospId())  && patientForm.getCurrHospId() != null)
						patientVO.setRefHospId(patientForm.getCurrHospId());
					String taashaFlg=null;
					EhfmHospitals ehfmHospitals = null;
					if(patientForm.getCurrHospId()!= null && !"".equals(patientForm.getCurrHospId()))
						ehfmHospitals = patientService.getHospTaashaFlg(patientForm.getCurrHospId());
					if(ehfmHospitals!=null)
						taashaFlg=ehfmHospitals.getTaashaHosp();
					if( (taashaFlg!=null && !"".equalsIgnoreCase(taashaFlg) && "Y".equalsIgnoreCase(taashaFlg)))
						patientVO.setNabhHosp("Y");
					if(!"".equalsIgnoreCase(patientForm.getAsriCatName())  && patientForm.getAsriCatName() != null)
						patientVO.setRefSpclty(patientForm.getAsriCatName());
					if(!"".equalsIgnoreCase(patientForm.getDistrict())  && patientForm.getDistrict() != null)
						patientVO.setRefDist(patientForm.getDistrict());
				}
				List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
				CaseTherapyVO caseTherapy = null;
				if(patientForm.getSpeciality()!=null && !patientForm.getSpeciality().equalsIgnoreCase("")){
				String s=patientForm.getSpeciality();
				//String otherDocDetails=patientForm.getOtherDocDetailsList();
				String[] specialities=s.split(",");
				//String[] otherDocList=otherDocDetails.split(",");
				//String caseProcCodes="";
				Long caseTheSeqNextVal=0L;
				for(int z=0;z<specialities.length;z++)
				{
					String speciality=specialities[z];
					//String otherDocDet=otherDocList[z];
					String[] specValues=speciality.split("~");
					//String[] otherDocValues=otherDocDet.split("~");
					if(!" ".equals(specValues[0]) && !" ".equals(specValues[1]))
					{
						caseTherapy = new CaseTherapyVO();
						if(("S16").equals(specValues[0]))
						{
							patientVO.setCochlearYN("Y");
						}
						if(("S19").equals(specValues[0]))
						{
							patientVO.setOrganTransYN("Y");
						}
						caseTherapy.setAsriCatCode(specValues[0]);
						caseTherapy.setICDCatCode(specValues[1]);
						caseTherapy.setICDProcCode(specValues[2]);
						caseTherapy.setDocName(specValues[3]);
						caseTherapy.setDocRegNum(specValues[4]);
						caseTherapy.setDocQual(specValues[5]);
						caseTherapy.setDocMobileNo(specValues[6]);
					/*	if(!"-1".equals(specValues[9]))
						{
							caseTherapy.setProcUnits(specValues[9]);
						}*/
						caseTherapy.setProcUnits(specValues[9]);
						caseTherapy.setSurgProcUnits(specValues[9]);
							if(specValues[10] != null)
								caseTherapy.setRoboticSurgery(specValues[10]);	
						//caseProcCodes=caseProcCodes+"'"+specValues[2]+"',";
						caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
						caseTherapy.setCaseTherapyId(caseTheSeqNextVal);
						caseTheSeqNextVal=caseTheSeqNextVal+1;
						caseTherapyList.add(caseTherapy);
					}
				}
				}
				patientVO.setCaseTherapy(caseTherapyList);
				patientVO.setIpNo(patientForm.getIpNo());
				patientVO.setAdmissionType(patientForm.getAdmissionType());
				patientVO.setIpDate(patientForm.getIpDate());
				if(patientForm.getLegalCase()!=null)
				{
					patientVO.setLegalCase(patientForm.getLegalCase());
					if(patientForm.getLegalCase().equalsIgnoreCase("Y"))
					{
						patientVO.setLegalCaseNo(patientForm.getLegalCaseNo());
						patientVO.setPoliceStatName(patientForm.getPoliceStatName());
					}
				}
				if(treatingDocRmks!=null && treatingDocRmks.length()>3000)
				{
					patientVO.setTeleDocremarks(treatingDocRmks.substring(0,3000));
				}
				else
				{
					patientVO.setTeleDocremarks(treatingDocRmks);
				}
				if(patientForm.getRemarks()!=null && patientForm.getRemarks().length()>3000)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks().substring(0,3000));
				}
				else if(patientForm.getRemarks()!=null)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks());
				}
			}
			if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase("IPM"))
			{
				patientVO.setMedicalSplty(patientForm.getMedicalSpclty());
				patientVO.setCategory(patientForm.getMedicalCat());
				patientVO.setICDprocName(patientForm.getICDProcName1());
				patientVO.setDiagnosedBy(patientForm.getDiagnosedBy());
				patientVO.setIpOtherRemark(patientForm.getIpOtherRemarks());
				patientVO.setDoctorName(patientForm.getDoctorName());
				patientVO.setOpRemarks(patientForm.getOpRemarks());
				patientVO.setDocQual(patientForm.getIpDoctorName());
				if( (!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)) )
				{
					if(!"".equalsIgnoreCase(patientForm.getCurrHospId())  && patientForm.getCurrHospId() != null)
						patientVO.setRefHospId(patientForm.getCurrHospId());
					if(!"".equalsIgnoreCase(patientForm.getAsriCatName())  && patientForm.getAsriCatName() != null)
						patientVO.setRefSpclty(patientForm.getAsriCatName());
					if(!"".equalsIgnoreCase(patientForm.getDistrict())  && patientForm.getDistrict() != null)
						patientVO.setRefDist(patientForm.getDistrict());
				}
				Long caseTheSeqNextVal=0L;
				caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
				patientVO.setCaseThrpySeq(caseTheSeqNextVal);
				List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
				CaseTherapyVO caseTherapy = null;
				if(patientForm.getSpeciality()!=null && !patientForm.getSpeciality().equalsIgnoreCase("")){
				String s=patientForm.getSpeciality();
				String[] specialities=s.split(",");
				for(int z=0;z<specialities.length;z++)
				{
					String speciality=specialities[z];
					String[] specValues=speciality.split("~");
					if(!" ".equals(specValues[0]) && !" ".equals(specValues[1]))
					{
						caseTherapy = new CaseTherapyVO();
						if(("S16").equals(specValues[0]))
						{
							patientVO.setCochlearYN("Y");
						}
						if(("S19").equals(specValues[0]))
						{
							patientVO.setOrganTransYN("Y");
						}
						caseTherapy.setAsriCatCode(specValues[0]);
						caseTherapy.setICDCatCode(specValues[1]);
						caseTherapy.setICDProcCode(specValues[2]);
						caseTherapy.setDocName(specValues[3]);
						caseTherapy.setDocRegNum(specValues[4]);
						caseTherapy.setDocQual(specValues[5]);
						caseTherapy.setDocMobileNo(specValues[6]);
						caseTherapy.setProcUnits(specValues[9]);
						caseTherapy.setSurgProcUnits(specValues[9]);
						//caseProcCodes=caseProcCodes+"'"+specValues[2]+"',";
						caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
						caseTherapy.setCaseTherapyId(caseTheSeqNextVal);
						caseTheSeqNextVal=caseTheSeqNextVal+1;
						caseTherapyList.add(caseTherapy);
					}
				}
				}
				patientVO.setCaseTherapy(caseTherapyList);
				patientVO.setOpNo(patientForm.getOpNo());
				patientVO.setAdmissionType(patientForm.getAdmissionType());
				patientVO.setIpDate(patientForm.getIpDate());
				if(patientForm.getLegalCase()!=null)
				{
					patientVO.setLegalCase(patientForm.getLegalCase());
					if(patientForm.getLegalCase().equalsIgnoreCase("Y"))
					{
						patientVO.setLegalCaseNo(patientForm.getLegalCaseNo());
						patientVO.setPoliceStatName(patientForm.getPoliceStatName());
					}
				}
				if(treatingDocRmks!=null && treatingDocRmks.length()>3000)
				{
					patientVO.setTeleDocremarks(treatingDocRmks.substring(0,3000));
				}
				else
				{
					patientVO.setTeleDocremarks(treatingDocRmks);
				}
				if(patientForm.getRemarks()!=null && patientForm.getRemarks().length()>3000)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks().substring(0,3000));
				}
				else if(patientForm.getRemarks()!=null)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks());
				}
			}
			else if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.OP")))
			{
				patientVO.setDiagnosedBy(patientForm.getDiagnosedBy());
				if(patientForm.getScheme()!=null && (config.getString("TG").equalsIgnoreCase(patientForm.getScheme()))
				&& (patientForm.getPatientScheme()!=null && (config.getString("Scheme.EHS").equalsIgnoreCase(patientForm.getPatientScheme())
						&& (patientForm.getHosptype()!=null && (("G").equalsIgnoreCase(patientForm.getHosptype())		
				)))))
				{	
				patientVO.setOpTotPkgAmt(50+patientForm.getTotInvestPrice()+"");
				patientVO.setConsultFee(50+"");
				patientVO.setTotInvestAmt(patientForm.getTotInvestPrice()+"");
				}
				patientVO.setOpNo(patientForm.getOpNo());
				if(patientForm.getOpRemarks()!=null && patientForm.getOpRemarks().length()>3000)
				{
					patientVO.setOpRemarks(patientForm.getOpRemarks().substring(0,3000));
				}
				else if(patientForm.getOpRemarks()!=null)
				{
					patientVO.setOpRemarks(patientForm.getOpRemarks());
				}
				patientVO.setOpDate(serverDt);
				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				DrugsVO drugsVO = null;
	/*pavan*/	
				//if(hospitalId!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId) && !("").equalsIgnoreCase(patientForm.getOtherDrugName()) && patientForm.getOtherDrugName()!=null )
					if(hospitalId!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId) && !("").equalsIgnoreCase(patientForm.getOtherDrugName()) && patientForm.getOtherDrugName()!=null )
				{
					drugsVO = new DrugsVO();
					drugsVO.setDrugTypeName("others");
					drugsVO.setDrugSubTypeName("others");
					drugsVO.setpSubGrpCode("others");
					drugsVO.setcSubGrpCode("others");
					drugsVO.setOtherDrugName(patientForm.getOtherDrugName());
					drugsVO.setDrugName("OD1");
					drugsVO.setRouteType("");
					drugsVO.setRoute("");
					drugsVO.setStrengthType("");
					drugsVO.setStrength("");
					drugsVO.setDosage("");
					drugsVO.setMedicationPeriod("");	
					Long drugSeqNextVal = Long.parseLong(patientService.getSequence("OP_DRUG_ID"));
					drugsVO.setDrugId(drugSeqNextVal);
					//drugSeqNextVal=drugSeqNextVal+1;
					drugsList.add(drugsVO);
				}
				else if(patientForm.getDrugs()!=null && !patientForm.getDrugs().equalsIgnoreCase("")){
				String s=patientForm.getDrugs().substring(0, patientForm.getDrugs().length()-1);
				String[] drugs=s.split("@,");
				for(int z=0;z<drugs.length;z++)
				{
					if(drugs[z].contains("@"))
					{String[] drugs1=drugs[z].split("@");
					drugs[z]=drugs1[0];
					}
					String drug=drugs[z];
					String[] drugValues=drug.split("~");
					drugsVO = new DrugsVO();
					if(drugValues[1]!=null && drugValues[1].contains("OD") && !"Y".equalsIgnoreCase(fromDisp))
					{
						drugsVO.setDrugTypeName("others");
						drugsVO.setDrugSubTypeName("others");
						drugsVO.setpSubGrpCode("others");
						drugsVO.setcSubGrpCode("others");
						drugsVO.setOtherDrugName(drugValues[0]);
						drugsVO.setDrugName(drugValues[1]);
						drugsVO.setRouteType(drugValues[2]);
						drugsVO.setRoute(drugValues[3]);
						drugsVO.setStrengthType(drugValues[4]);
						drugsVO.setStrength(drugValues[5]);
						drugsVO.setDosage(drugValues[6]);
						drugsVO.setMedicationPeriod(drugValues[7]);	
					}
					else
					{
						 if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
							{
							 	drugsVO.setDrugTypeCode(drugValues[0]);
								drugsVO.setDrugSubTypeCode(drugValues[1]);
								drugsVO.setRouteType(drugValues[5]);
								drugsVO.setRoute(drugValues[6]);
								drugsVO.setStrengthType(drugValues[7]);
								drugsVO.setStrength(drugValues[8]);
								drugsVO.setDosage(drugValues[9]);
								drugsVO.setMedicationPeriod(drugValues[10]);
								drugsVO.setOtherDrugName(drugValues[11]);
								drugsVO.setQuantity(drugValues[12]);
								drugsVO.setDispDrugMstrDrgCode(drugValues[13]);
							}
						 else
						 {
							drugsVO.setDrugTypeName(drugValues[0]);
							drugsVO.setDrugSubTypeName(drugValues[1]);
							drugsVO.setpSubGrpCode(drugValues[2]);
							drugsVO.setcSubGrpCode(drugValues[3]);
							drugsVO.setDrugName(drugValues[4]);
							drugsVO.setRouteType(drugValues[5]);
							drugsVO.setRoute(drugValues[6]);
							drugsVO.setStrengthType(drugValues[7]);
							drugsVO.setStrength(drugValues[8]);
							drugsVO.setDosage(drugValues[9]);
							drugsVO.setMedicationPeriod(drugValues[10]);
						 }
					}
					Long drugSeqNextVal = Long.parseLong(patientService.getSequence("OP_DRUG_ID"));
					drugsVO.setDrugId(drugSeqNextVal);
					//drugSeqNextVal=drugSeqNextVal+1;
					drugsList.add(drugsVO);
				}
				}
				patientVO.setDrugs(drugsList);
				patientVO.setDoctorName(patientForm.getDoctorName());
				patientVO.setOtherDocName(patientForm.getOtherDocName());
				patientVO.setDocRegNo(patientForm.getDocRegNo());
				patientVO.setDocQual(patientForm.getDocQual());
				patientVO.setDocMobileNo(patientForm.getDocMobileNo());
				patientVO.setUnitName(patientForm.getUnitName());
				patientVO.setUnitHodName(patientForm.getUnitHodName());
			}
			else if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.DOP"))))
			{
				patientVO.setAdmissionType(""); //Admission type removed in DOP
				patientVO.setDiagnosedBy(patientForm.getIpDiagnosedBy());
				patientVO.setDoctorName(patientForm.getIpDoctorName());
                List<CaseTherapyVO> caseTherapyList = new ArrayList<CaseTherapyVO>();
				CaseTherapyVO caseTherapy = null;
				if(patientForm.getSpeciality()!=null && !patientForm.getSpeciality().equalsIgnoreCase("")){
				String s=patientForm.getSpeciality();
				String[] specialities=s.split(",");
				Long caseTheSeqNextVal=0L;
				for(int z=0;z<specialities.length;z++)
				{
					String speciality=specialities[z];
					String[] specValues=speciality.split("~");
					if(!" ".equals(specValues[0]) && !" ".equals(specValues[1]))
					{
						caseTherapy = new CaseTherapyVO();
						if(("S16").equals(specValues[0]))
						{
							patientVO.setCochlearYN("Y");
						}
						if(("S19").equals(specValues[0]))
						{
							patientVO.setOrganTransYN("Y");
						}
						caseTherapy.setAsriCatCode(specValues[0]);
						caseTherapy.setICDCatCode(specValues[1]);
						caseTherapy.setICDProcCode(specValues[2]);
						caseTherapy.setDocName(specValues[3]);
						caseTherapy.setDocRegNum(specValues[4]);
						caseTherapy.setDocQual(specValues[5]);
						caseTherapy.setDocMobileNo(specValues[6]);
						caseTherapy.setProcUnits(specValues[9]);
						caseTherapy.setSurgProcUnits(specValues[9]);
						//caseProcCodes=caseProcCodes+"'"+specValues[2]+"',";
						caseTheSeqNextVal = Long.parseLong(patientService.getSequence("CASE_THERAPY_ID"));
						caseTherapy.setCaseTherapyId(caseTheSeqNextVal);
						caseTheSeqNextVal=caseTheSeqNextVal+1;
						caseTherapyList.add(caseTherapy);
					}
				}
				}
				patientVO.setCaseTherapy(caseTherapyList);
				patientVO.setIpNo(patientForm.getIpNo());
				//patientVO.setAdmissionType(patientForm.getAdmissionType());
				patientVO.setIpDate(patientForm.getIpDate());
				if(patientForm.getLegalCase()!=null)
				{
					patientVO.setLegalCase(patientForm.getLegalCase());
					if(patientForm.getLegalCase().equalsIgnoreCase("Y"))
					{
						patientVO.setLegalCaseNo(patientForm.getLegalCaseNo());
						patientVO.setPoliceStatName(patientForm.getPoliceStatName());
					}
				}
				if(treatingDocRmks!=null && treatingDocRmks.length()>3000)
				{
					patientVO.setTeleDocremarks(treatingDocRmks.substring(0,3000));
				}
				else
				{
					patientVO.setTeleDocremarks(treatingDocRmks);
				}
				if(patientForm.getRemarks()!=null && patientForm.getRemarks().length()>3000)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks().substring(0,3000));
				}
				else if(patientForm.getRemarks()!=null)
				{
					patientVO.setIpCaseRemarks(patientForm.getRemarks());
				}
			}
			else if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.ChronicOP")))
			{
				patientVO.setDiagnosedBy(patientForm.getDiagnosedBy());
				patientVO.setDoctorName(patientForm.getDoctorName());
				patientVO.setOtherDocName(patientForm.getOtherDocName());
				patientVO.setDocRegNo(patientForm.getDocRegNo());
				patientVO.setDocQual(patientForm.getDocQual());
				patientVO.setDocMobileNo(patientForm.getDocMobileNo());
				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				patientVO.setDrugs(drugsList);
				patientVO.setOpCatCode(patientForm.getOpCatName());
				patientVO.setOpPkgCode(patientForm.getOpPkgName());
				patientVO.setOpIcdCode(patientForm.getOpIcdName());
			}
			GLOGGER.info("PatientId"+lStrPatientId+"while Patient Case registration in PatientAction.") ;
			if(request.getParameter("addTests")!=null && !request.getParameter("addTests").equals(""))
			{
				genInvestigationList=request.getParameter("addTests");
				StringTokenizer st=new StringTokenizer(genInvestigationList,"~");
				while(st.hasMoreTokens()){
					LabelBean labelBean=new LabelBean();
					String lL=st.nextToken();
					 GLOGGER.info("investigation :"+lL);
					String[] st1=new String[3];
					st1=lL.split("\\$");
					//StringTokenizer st1=new StringTokenizer(lL,"$");
						labelBean.setVALUE(st1[0]);
						labelBean.setID(st1[1]);   
						 if(patientForm.getPatientType()!=null && (patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IP"))) 
									|| patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IPD")))) )
							 labelBean.setPrice(""); 
						 else if(patientForm.getScheme()!=null && patientForm.getScheme().equalsIgnoreCase(config.getString("TG")) && (patientForm.getHosptype()!=null && ("G").equalsIgnoreCase(patientForm.getHosptype()))
								 && (patientForm.getPatientScheme()!=null && (config.getString("Scheme.EHS")).equalsIgnoreCase(patientForm.getPatientScheme()))		 
						 )
							 if(st1[2]!=null)
						labelBean.setPrice(st1[2]); 
						 else
							 labelBean.setPrice(""); 
						 lGenInvList.add(labelBean)  ;
				}
			}
						List<String> remList=new ArrayList<String>();
			String genInvestRemoved = request.getParameter("genInvestRemove");
			if(genInvestRemoved!=null && !"".equals(genInvestRemoved))
			{
				StringTokenizer str=new StringTokenizer(genInvestRemoved,"@");
				while(str.hasMoreTokens()){
					String investId=str.nextToken();
					if(investId!=null && !"".equals(investId))
						remList.add(investId);
				}
			}
			if(request.getParameter("updateTests")!=null && !request.getParameter("updateTests").equals(""))
			{
				updateGenInvestList=request.getParameter("updateTests");
				LabelBean labelBean=new LabelBean();
				StringTokenizer st=new StringTokenizer(updateGenInvestList,"~");
				while(st.hasMoreTokens()){
					labelBean=new LabelBean();
					String lL=st.nextToken();
					StringTokenizer st1=new StringTokenizer(lL,"$");
					while(st1!=null && st1.hasMoreTokens()){
						String investId=st1.nextToken();
						if(remList.contains(investId))
						{
							st1=null;
						}
						if(!remList.contains(investId))
						{
							labelBean.setID(investId); 
							labelBean.setVALUE(st1.nextToken());
							lUpdateGenInvList.add(labelBean)  ;
						}
					}
				}
			}
			if(request.getParameter("investigationsSel")!=null && !request.getParameter("investigationsSel").equals(""))
			{
				investigationList=request.getParameter("investigationsSel");
				LabelBean labelBean=new LabelBean();
				StringTokenizer st=new StringTokenizer(investigationList,"~");
				while(st.hasMoreTokens()){
					labelBean=new LabelBean();
					String lL=st.nextToken();
					StringTokenizer st1=new StringTokenizer(lL,"$");
					while(st1.hasMoreTokens()){
						labelBean.setVALUE(st1.nextToken());
						labelBean.setID(st1.nextToken());
						labelBean.setICDCODE(st1.nextToken());
						//labelBean.setPrice(st1.nextToken());
						lSelInvList.add(labelBean)  ;
					}
				}
			}
			FormFile lFormFile=null;
			List<AttachmentVO> lTestsAttachList=new ArrayList<AttachmentVO>(); 
			List<AttachmentVO> lGenTestAttachList=new ArrayList<AttachmentVO>();
			List<AttachmentVO> lUpdGenTestAttachList=new ArrayList<AttachmentVO>();
			String errmsg="";
			//For General Investigations
			if(hospitalId!=null /*&& !config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId)*/)
			{
			for(LabelBean labelBean: lGenInvList){
				//used to avoid deleted attachments-conflict
				while(patientForm.getGenAttach(i)==null )
				{
					i++;
				}
				if(patientForm.getGenAttach(i)!=null ){
					lFormFile=patientForm.getGenAttach(i);
					if (lFormFile.getFileSize() > 204800) 
					{
						errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
					}
				}
				i++;
			}
			//For Updating  General Investigations
			i=0;
			for(LabelBean labelBean: lUpdateGenInvList){
				//used to avoid deleted attachments-conflict
				while(patientForm.getUpdateGenAttach(i)==null )
				{
					if(i==99)
					{
						break;
					}
					i++;
				}
				if(patientForm.getUpdateGenAttach(i)!=null ){
					lFormFile=patientForm.getUpdateGenAttach(i);
					if (lFormFile.getFileSize() > 204800) 
					{
						errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
					}
				}
				i++;
			}
			}
			//For IP Therapy Investigations
			i=0;
			for(LabelBean labelBean: lSelInvList){
				//used to avoid deleted attachments-conflict
				while(patientForm.getAttach(i)==null )
				{
					i++;
				}
				if(patientForm.getAttach(i)!=null ){
					lFormFile=patientForm.getAttach(i);
					if (lFormFile.getFileSize() > 204800) 
					{
						errmsg=errmsg+"\\'"+labelBean.getVALUE()+"\\' \\n";
					}
				}
				i++;
			}
			String lStrCaseId=null;
			if("".equals(errmsg))
			{
				i=0; 
				//For General Investigations
				for(LabelBean labelBean: lGenInvList){
					AttachmentVO attachmentVO=new AttachmentVO();
					//used to avoid deleted attachments-conflict
					if(hospitalId!=null /*&& !config.getString("HOSP_NIMS").equalsIgnoreCase(hospitalId)*/)
					{
					while(patientForm.getGenAttach(i)==null )
					{
						i++;
					}
					lFormFile=patientForm.getGenAttach(i);                                     
					lFileName=lFormFile.getFileName();
					if(lFileName!=null && !lFileName.equals(""))
					{
						lCount=lFileName.lastIndexOf(".");
						lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
						// save file in folder's
						String lStrSharePath = 
								config.getString("STORAGE_BOX") + 
								/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
								config.getString("SLASH_VALUE") + lStrPatientId + 
								config.getString("SLASH_VALUE") + 
								config.getString("TESTS_ATTACH")+ 
								config.getString("SLASH_VALUE");
						lStrTotFileName = ltime + "_" + lFileName;
						lDir = lStrSharePath+lStrTotFileName; 
						try
						{
							File lFile = new File(lDir);
							File lDirectory = new File(lStrSharePath);
							boolean lbDirectioryPresent = false; 
							if(!lDirectory.exists())
							{
								lbDirectioryPresent = lDirectory.mkdirs();
							}
							else
							{
								lbDirectioryPresent = true;
							}
							if(lbDirectioryPresent)
							{
								if(lFile.exists())
								{
									lFile.delete();
								}
								FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
								byte[] fileData = lFormFile.getFileData();
								lFileOutputStream.write(fileData);
								lFileOutputStream.flush();
								lFileOutputStream.close();
								lFileOutputStream = null;
							}
						}
						catch(Exception e) {
							GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction." +e.getMessage()) ;
						}
					}}
					attachmentVO.setTestId(labelBean.getID());
					attachmentVO.setTestName(labelBean.getVALUE());
					 /*if(patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.OP")))*/
					attachmentVO.setPrice(labelBean.getPrice());	 
					//attachmentVO.setFileName(lStrTotFileName);
					if(lFileName!=null && !lFileName.equals(""))
					{
						attachmentVO.setFileName(lFileName);
						attachmentVO.setFileSize(lFormFile.getFileSize());
						attachmentVO.setFileReportPath(lDir);
						attachmentVO.setFileContentType(lFormFile.getContentType());
						attachmentVO.setFileExtsn(lFileExt);
					}
					String genTestSeqNextVal = patientService.getSequence("GEN_INVEST_ID");
					attachmentVO.setAttachId(genTestSeqNextVal);
					//genTestSeqNextVal++;
					lGenTestAttachList.add(attachmentVO);                 
					i++;
				}
				patientVO.setGenAttachmentsList(lGenTestAttachList);  
				//ehfmSeqGenTests.setSeqNextVal(Long.valueOf(genTestSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqGenTests);
				i=0; 
				//For Updating  General Investigations
				for(LabelBean labelBean: lUpdateGenInvList){
					AttachmentVO attachmentVO=new AttachmentVO();
					//used to avoid deleted attachments-conflict
					while(patientForm.getUpdateGenAttach(i)==null )
					{
						if(i==99)
						{
							break;
						}
						i++;
					}
					lFormFile=patientForm.getUpdateGenAttach(i);  
					if(lFormFile!=null)
					{
					lFileName=lFormFile.getFileName();
					if(lFileName!=null && !lFileName.equals(""))
					{
						lCount=lFileName.lastIndexOf(".");
						lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
						// save file in folder's
						String lStrSharePath = 
								config.getString("STORAGE_BOX") + 
								/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
								config.getString("SLASH_VALUE") + lStrPatientId + 
								config.getString("SLASH_VALUE") + 
								config.getString("TESTS_ATTACH")+ 
								config.getString("SLASH_VALUE");
						lStrTotFileName = ltime + "_" + lFileName;
						lDir = lStrSharePath+lStrTotFileName; 
						try
						{
							File lFile = new File(lDir);
							File lDirectory = new File(lStrSharePath);
							boolean lbDirectioryPresent = false; 
							if(!lDirectory.exists())
							{
								lbDirectioryPresent = lDirectory.mkdirs();
							}
							else
							{
								lbDirectioryPresent = true;
							}
							if(lbDirectioryPresent)
							{
								if(lFile.exists())
								{
									lFile.delete();
								}
								FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
								byte[] fileData = lFormFile.getFileData();
								lFileOutputStream.write(fileData);
								lFileOutputStream.flush();
								lFileOutputStream.close();
								lFileOutputStream = null;
							}
						}
						catch(Exception e) {
							GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction while saving general investigations " +e.getMessage()) ;
						}
					}
					attachmentVO.setTestId(labelBean.getID());
					attachmentVO.setTestName(labelBean.getVALUE());
					//attachmentVO.setFileName(lStrTotFileName);
					if(lFileName!=null && !lFileName.equals(""))
					{
						attachmentVO.setFileName(lFileName);
						attachmentVO.setFileSize(lFormFile.getFileSize());
						attachmentVO.setFileReportPath(lDir);
						attachmentVO.setFileContentType(lFormFile.getContentType());
						attachmentVO.setFileExtsn(lFileExt);
					}
					String genTestSeqNextVal = patientService.getSequence("GEN_INVEST_ID");
					attachmentVO.setAttachId(genTestSeqNextVal);
					//genTestSeqNextVal++;
					lUpdGenTestAttachList.add(attachmentVO);  
					}
					i++;
				}
				patientVO.setUpdGenAttachmentsList(lUpdGenTestAttachList);  
				i=0;
				//For IP Therapy Investigations
				//patientVO.setCaseTherInvestSeqId(caseTheInvestSeqNextVal);
				for(LabelBean labelBean: lSelInvList){
					AttachmentVO attachmentVO=new AttachmentVO(); 
					//used to avoid deleted attachments-conflict
					while(patientForm.getAttach(i)==null )
					{
						i++;
					}
					lFormFile=patientForm.getAttach(i);                                     
					lFileName=lFormFile.getFileName();
					if(lFileName!=null && !("").equalsIgnoreCase(lFileName))
					{
					lCount=lFileName.lastIndexOf(".");
					lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
					// save file in folder's
					String lStrSharePath = 
							config.getString("STORAGE_BOX") + 
							/*config.getString("SLASH_VALUE")+*/  patCrtDt + 
							config.getString("SLASH_VALUE") + lStrPatientId + 
							config.getString("SLASH_VALUE") + 
							config.getString("TESTS_ATTACH")+ 
							config.getString("SLASH_VALUE");
					lStrTotFileName = ltime + "_" + lFileName;
					lDir = lStrSharePath+lStrTotFileName; 
					try
					{
						File lFile = new File(lDir);
						File lDirectory = new File(lStrSharePath);
						boolean lbDirectioryPresent = false; 
						if(!lDirectory.exists())
						{
							lbDirectioryPresent = lDirectory.mkdirs();
						}
						else
						{
							lbDirectioryPresent = true;
						}
						if(lbDirectioryPresent)
						{
							if(lFile.exists())
							{
								lFile.delete();
							}
							FileOutputStream lFileOutputStream = new FileOutputStream(lFile);
							byte[] fileData = lFormFile.getFileData();
							lFileOutputStream.write(fileData);
							lFileOutputStream.flush();
							lFileOutputStream.close();
							lFileOutputStream = null;
						}
					}
					catch(Exception e) {
						GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction." +e.getMessage()) ;
					}
					}
					attachmentVO.setTestId(labelBean.getID());
					attachmentVO.setTestName(labelBean.getVALUE());
					//attachmentVO.setFileName(lStrTotFileName);
					attachmentVO.setIcdProcCode(labelBean.getICDCODE());
					attachmentVO.setFileName(lFileName);
					attachmentVO.setFileSize(lFormFile.getFileSize());
					attachmentVO.setFileReportPath(lDir);
					attachmentVO.setFileContentType(lFormFile.getContentType());
					attachmentVO.setFileExtsn(lFileExt);
					String caseTheInvestSeqNextVal = patientService.getSequence("CASE_THER_INVEST_ID");
					attachmentVO.setAttachId(caseTheInvestSeqNextVal);
					//caseTheInvestSeqNextVal++;
					lTestsAttachList.add(attachmentVO);                 
					i++;
				}
				patientVO.setAttachmentsList(lTestsAttachList); 
				//ehfmSeqCaseTherInvest.setSeqNextVal(Long.valueOf(caseTheInvestSeqNextVal));
				//commonService.updateSequenceVal(ehfmSeqCaseTherInvest);
                patientVO.setSaveFlag(saveFlg);
                String specRemove = request.getParameter("specRemove");
    			String genInvestRemove = request.getParameter("genInvestRemove");
    			String caseId = request.getParameter("caseId");
                if(specRemove!=null && !specRemove.equalsIgnoreCase("") && caseId!=null && !caseId.equalsIgnoreCase("")){
                	String[] specRem = specRemove.split("@");
                	for(i=0;i<specRem.length;i++){
                		if(specRem[i]!=null && !specRem[i].equalsIgnoreCase("")){
                			String[] specDtls = specRem[i].split("~");
                			patientService.deleteInvestigations(caseId,specDtls[0],specDtls[2],specDtls[1]);         
                		}
                	}
                }
                if(genInvestRemove!=null && !genInvestRemove.equalsIgnoreCase("")){
                	String[] genInvestRem = genInvestRemove.split("@");
                	for(i=0;i<genInvestRem.length;i++){
                		if(genInvestRem[i]!=null && !genInvestRem[i].equalsIgnoreCase("")){
                			patientService.deleteGenInvest(patientVO.getPatientId(),genInvestRem[i]);         
                		}
                	}
                }
                String dentalFlg=request.getParameter("dentalFlg");
                patientVO.setDentalFlg(dentalFlg);
                if(dentalFlg!=null && "Y".equalsIgnoreCase(dentalFlg)){
                	patientVO.setSoftTissue(StringUtils.join(patientForm.getSoftTissue(), "~"));
                	patientVO.setDeciduosDentition(StringUtils.join(patientForm.getChildCaries(), "~"));
                	patientVO.setMissing(StringUtils.join(patientForm.getMissingTeeth(), "~"));
                	patientVO.setCaries(StringUtils.join(patientForm.getCaries(), "~"));
                	patientVO.setDecayed(StringUtils.join(patientForm.getDecayed(), "~"));
                	//patientVO.setMobile(StringUtils.join(patientForm.getMobile(), "~"));
                	patientVO.setAttrition(StringUtils.join(patientForm.getAttrition(), "~"));
                	patientVO.setPreviousDentalTreatment(patientForm.getPreviousDentalTreatment());
                	patientVO.setOcclusion(patientForm.getOcclusion());
                	patientVO.setTmj(patientForm.getTmj());
                	patientVO.setProbeDepth(StringUtils.join(patientForm.getProbeDepth(), "~"));
                	patientVO.setDiagnosis(patientForm.getDiagnosis());
                	patientVO.setFollowupAdv(patientForm.getFollowupAdv());
                	if(patientForm.getPatientType()!=null && ( "IP".equalsIgnoreCase(patientForm.getPatientType()) || config.getString("PatientIPOP.IPD").equalsIgnoreCase(patientForm.getPatientType()))  )
                	{
                		patientVO.setAdmissionDetails(patientForm.getAdmissionDetails());
                		patientVO.setAdvancedInvestigations(patientForm.getAdvancedInvestigations());
                	}
                }
                if(patientForm.getPatientScheme()!=null)
                	patientVO.setPatientScheme(patientForm.getPatientScheme());
                if(patientForm.getCritical()!=null && !"".equalsIgnoreCase(patientForm.getCritical()))
                		{
                	patientVO.setCritical(patientForm.getCritical());
                		}
                patientVO.setDtoCs(dtoCs);
				lStrCaseId=patientService.saveCaseDetails(patientVO);
				String caseEndTime = sds.format(new Date().getTime());
				if(lStrCaseId != null && !"".equalsIgnoreCase(lStrCaseId))
				{
					String[] tokens = lStrCaseId.split("/");
					String lastToken = tokens[tokens.length-1];
					if(lastToken != null && !"".equalsIgnoreCase(lastToken) && "D".equalsIgnoreCase(lastToken))
						lastToken = tokens[tokens.length-2];
					else
						lastToken = tokens[tokens.length-1];
					String actionDone = commonService.getActionDoneName(lastToken,"ehfCase");
					loggingService.logTime(actionDone, lastToken, caseStartTime, caseEndTime, lStrUserId, "EHS_OperationsNABH");
				        	String facilityId = commonService.getloggedUserDispId(lStrUserId,schemeId);
				        	String url = config.getString("tdUrl");
			            	   patientService.tdEhfRegistration(patientNo,url,facilityId);
							   request.setAttribute("savedDrugsFlag", "Y");
				}
				patientForm.setPatientScheme(patientVO.getPatientScheme());
				patientForm.setPatientType(patientVO.getPatientType());
				patientForm.setCaseId(lStrCaseId);
				PreauthVO smsDrugs=patientService.getPatientFullDtls(lStrCaseId, patientNo, fromDisp);
//				if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit"))
				if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit") && !lStrgrpList.contains("GP801"))
				{
					patientForm.setMsg("Case Registered Successfully with Case ID :");
					if (patientForm.getPatientType()!=null && (patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IP"))) 
							|| patientForm.getPatientType().equalsIgnoreCase((config.getString("PatientIPOP.IPD")))) )
		               {
		            	   if(patientVO.getGenAttachmentsList().size()>0)	
						{	
							if("true".equalsIgnoreCase(config.getString("SmsRequired")))
							{
								if(!"".equalsIgnoreCase(patientVO.getContactNo()) &&  patientVO.getContactNo()!=null)
								{
									int count=0;
									String investigationsList="";
									for(AttachmentVO invstData: patientVO.getGenAttachmentsList())
					    			{
					    				if(count>0)
					    				{
					    					investigationsList=investigationsList+",";
					    				}
					    				investigationsList=investigationsList+invstData.getTestName();
					    				count++;
					    			}
									//SendSMS sendSms =new SendSMS();
									SMSServices SMSServicesobj = new SMSServices();
									String msg= "Dear "+name.trim()+" , Your general investigations details are: "+investigationsList;
									// sendSms.sendSMS(msg,patientVO.getContactNo() );
									 SMSServicesobj.sendSingleSMS(msg,patientVO.getContactNo(),null);
								}
							 }
						}
		               }
					if (patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.OP")) && patientVO.getDrugs().size() >0)
					{
						if(patientVO.getDrugs() != null)	
						{	
							if("true".equalsIgnoreCase(config.getString("SmsRequired")))
							{
								if(!"".equalsIgnoreCase(patientVO.getContactNo()) &&  patientVO.getContactNo()!=null)
								{
									int count=0;
									String drugs="";
									for(DrugsVO docData:smsDrugs.getDrugList())
					    			{
					    				if(count>0)
					    				{
					    					drugs=drugs+",";
					    				}
					    				if(docData.getOTHERDRUGNAME()!=null && !docData.getOTHERDRUGNAME().equalsIgnoreCase(""))
					    					drugs=drugs+docData.getOTHERDRUGNAME();
					    				else
					    				if(docData.getDRUGNAME() != null  && !docData.getDRUGNAME().equalsIgnoreCase(""))
					    					drugs=drugs+docData.getDRUGNAME();
					    				count++;
					    			}
									//SendSMS sendSms =new SendSMS();
									SMSServices SMSServicesobj = new SMSServices();
									String msg= "Dear "+name.trim()+" , Your prescription drugs are: "+drugs;
									// sendSms.sendSMS(msg,patientVO.getContactNo() );
									 SMSServicesobj.sendSingleSMS(msg,patientVO.getContactNo(),null);
								}
							 }
						}
					}
			}
				else if((!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)) && grpId!=null && grpId.equalsIgnoreCase("GP801"))
	               {	
					patientForm.setMsg("Case saved and drugs dispatched successfully with Case ID :");
					if (patientForm.getPatientType()!=null && patientForm.getPatientType().equalsIgnoreCase(config.getString("PatientIPOP.OP")) && patientVO.getDrugs().size() >0)
						{
							if(patientVO.getDrugs() != null)	
							{	
								if("true".equalsIgnoreCase(config.getString("SmsRequired")))
								{
									if(!"".equalsIgnoreCase(patientVO.getContactNo()) &&  patientVO.getContactNo()!=null)
									{
										int count=0;
										String drugs="";
										for(DrugsVO docData:smsDrugs.getDrugList())
						    			{
						    				if(count>0)
						    				{
						    					drugs=drugs+",";
						    				}
						    				if(docData.getOTHERDRUGNAME()!=null && !docData.getOTHERDRUGNAME().equalsIgnoreCase(""))
						    					drugs=drugs+docData.getOTHERDRUGNAME();
						    				else
						    				if(docData.getDRUGNAME() != null  && !docData.getDRUGNAME().equalsIgnoreCase(""))
						    					drugs=drugs+docData.getDRUGNAME();
						    				count++;
						    			}
										//SendSMS sendSms =new SendSMS();
										SMSServices SMSServicesobj = new SMSServices();
										String msg= "Dear "+name.trim()+" , Your prescription drugs are: "+drugs;
										// sendSms.sendSMS(msg,patientVO.getContactNo() );
										SMSServicesobj.sendSingleSMS(msg,patientVO.getContactNo(),null);
									}
								 }
							}
						}
			}
				else{
					patientForm.setMsg("Case Saved Successfully with Case ID :");
					if (patientVO.getCaseStatus()!=null && patientVO.getCaseStatus().equalsIgnoreCase("CD3000"))
		               {
						if(patientVO.getGenAttachmentsList().size()>0)	
						{	
							if("true".equalsIgnoreCase(config.getString("SmsRequired")))
							{
								if(!"".equalsIgnoreCase(patientVO.getContactNo()) &&  patientVO.getContactNo()!=null)
								{
									int count=0;
									String investigationsList="";
									for(AttachmentVO invstData: patientVO.getGenAttachmentsList())
					    			{
					    				if(count>0)
					    				{
					    					investigationsList=investigationsList+",";
					    				}
					    				investigationsList=investigationsList+invstData.getTestName();
					    				count++;
					    			}
									//SendSMS sendSms =new SendSMS();
									SMSServices SMSServicesobj = new SMSServices();
									String msg= "Dear "+name.trim()+" , Your general investigations details are: "+investigationsList+". You can view the lab reports in EJHS mobile application.";
									 //sendSms.sendSMS(msg,patientVO.getContactNo() );
									SMSServicesobj.sendSingleSMS(msg,patientVO.getContactNo(),null);
								}
							 }
						}
		               }
					if(checkType!=null)
					{
						patientForm.setDisableFlag(checkType);
					}
				}
				if(lStrCaseId!=null && !lStrCaseId.equalsIgnoreCase(""))
				{
					if(lStrCaseId.equalsIgnoreCase("Already Registered"))
					{
						patientForm.setErrMsg("Patient Already Registered");
						patientForm.setCaseId("");
						lStrPageName="patient";
					}
					else
					{
						if(patientVO.getSaveFlag()!=null && patientVO.getSaveFlag().equalsIgnoreCase("Submit")){
						if (config.getBoolean("EmailRequired")) 
						{
							EhfPatient ehfPatient=null;
							ehfPatient=(EhfPatient)patientService.getPatientDetails(lStrPatientId);
							String mailId=null;
							if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
							{
								mailId=ehfPatient.getEmailId();
								String[] emailToArray = {mailId};
								EmailVO emailVO = new EmailVO();
								emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
								emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
								emailVO.setFromEmail(config.getString("emailFrom"));
								emailVO.setToEmail(emailToArray);
								emailVO.setSubject(config.getString("patientEmailSubject"));
								Map<String, String> model = new HashMap<String, String>();
								model.put("patientName", ehfPatient.getName().trim());
								model.put("registeredHosp", patientService.getHospName(patientForm.getHospitalId()));
								model.put("status", "Case Registered as "+patientForm.getPatientType()+" with Case Id "+lStrCaseId);
								model.put("statusDate",crtDate);
								if(ehfPatient.getPatientScheme()!=null)
									{
										if(ehfPatient.getPatientScheme().equalsIgnoreCase(config.getString("Scheme.JHS")))
											{
												emailVO.setSubject(config.getString("patientEmailSubjectJournalist"));
												emailVO.setFromEmail(config.getString("emailFromJournalist"));
												emailVO.setTemplateName(config.getString("EHFPatientTemplateNameJourn"));
												commonService.generateNonImageMail(emailVO, model);
											}
										else
											{
												emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
												commonService.generateMail(emailVO, model);
											}
									}
								else
									{
										emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
										commonService.generateMail(emailVO, model);
									}
							}
						}
						}						
					}
				}
				lStrPageName="patient";
			}
			else
			{
				patientForm.setErrMsg("Uploaded File Size Should not exceed 200KB");
				lStrPageName="patient"; 
			}
		}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		if ("telephonicEntry".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);
			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			// List<LabelBean> castesList=patientService.getCastes();
			session.setAttribute("castesList",castesList);
			//List<LabelBean> relationList=commonService.getComboDetails(lStrRelationsHdrId);
			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);
			// List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			List<LabelBean> hospitalList = commonService.getTelephncHospitals();
			session.setAttribute("hospitalList",hospitalList);
			List<LabelBean> diagnTypeList = patientService.getDiagnosisTypes();
			session.setAttribute("diagnTypeList",diagnTypeList);
			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);
			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));
			session.setAttribute("userRole",roleId);
			if(session.getAttribute("userState").toString()!=null)
			{
				schemeId=session.getAttribute("userState").toString();
			}
			request.setAttribute("execScheme", schemeId);
			lStrPageName = "telephonicPatientEntry";
		}
		/*if("getTherapySubcategory".equalsIgnoreCase(lStrActionVal)){
			String lStrCatId=request.getParameter("lStrCatId");
			List<LabelBean> subCategoryList=patientService.getTherapySubCategory(lStrCatId);
	         session.setAttribute("ICDsubCategoryList",subCategoryList);
	         List<String> subCategoryList1 = new ArrayList<String>();
	         for (LabelBean labelBean: subCategoryList) {
	                if (labelBean.getID() != null && 
	                    labelBean.getVALUE() != null)
	                    if (subCategoryList1 != null && subCategoryList1.size() > 0) {
	                    	subCategoryList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE());
	                    } else
	                    	subCategoryList1.add(labelBean.getID() + "~" + 
	                                       labelBean.getVALUE());
	            }
	            if (subCategoryList1 != null && subCategoryList1.size() > 0)
	               request.setAttribute("AjaxMessage", subCategoryList1);
	         lStrPageName="ajaxResult";
		}*/
		if("getDiagnMainCategory".equalsIgnoreCase(lStrActionVal)){
			String lStrDiagnId=request.getParameter("lStrDiagnId");
			List<LabelBean> mainCatList=patientService.getDiagnMainCategory(lStrDiagnId);
			session.setAttribute("mainCatList",mainCatList);
			List<String> mainCatList1 = new ArrayList<String>();
			for (LabelBean labelBean: mainCatList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
					if (mainCatList1 != null && mainCatList1.size() > 0) {
						mainCatList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
					} else
						mainCatList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
			}
			if (mainCatList1 != null && mainCatList1.size() > 0)
				request.setAttribute("AjaxMessage", mainCatList1);
			lStrPageName="ajaxResult";
		}
		if("getDiagnCategory".equalsIgnoreCase(lStrActionVal)){
			String lStrDiagnMainId=request.getParameter("lStrDiagnMainId");
			List<LabelBean> categoryList=patientService.getDiagnCategory(lStrDiagnMainId);
			session.setAttribute("categoryList",categoryList);
			List<String> categoryList1 = new ArrayList<String>();
			for (LabelBean labelBean: categoryList) {
				if (labelBean.getID() != null && 
						labelBean.getVALUE() != null)
					if (categoryList1 != null && categoryList1.size() > 0) {
						categoryList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
					} else
						categoryList1.add(labelBean.getID() + "~" + 
								labelBean.getVALUE()+"@");
			}
			if (categoryList1 != null && categoryList1.size() > 0)
				request.setAttribute("AjaxMessage", categoryList1);
			lStrPageName="ajaxResult";
		}
		if("getDiagnCatSubDetails".equalsIgnoreCase(lStrActionVal))
		{
			String lStrDiagnCode=request.getParameter("lStrDiagnCode");
			String lStrListType=request.getParameter("lStrListType");
			//List<List<String>> finalSubDetailsList=new ArrayList<List<String>>();
			String finalSubDetailsList="";
			List<LabelBean> subCategoryList=null;
			List<LabelBean> diseaseList=null;
			List<LabelBean> disAnatomicalList=null;
			if("categoryId".equalsIgnoreCase(lStrListType))
			{
				subCategoryList=patientService.getDiagnSubCategory(lStrDiagnCode);
				diseaseList=patientService.getDiagnDisease(lStrDiagnCode,lStrListType);
				disAnatomicalList=patientService.getDiagnDisAnatomical(lStrDiagnCode,lStrListType);
			}
			else if("subCategoryId".equalsIgnoreCase(lStrListType))
			{
				diseaseList=patientService.getDiagnDisease(lStrDiagnCode,lStrListType);
				disAnatomicalList=patientService.getDiagnDisAnatomical(lStrDiagnCode,lStrListType);
			}
			else if("diseaseId".equalsIgnoreCase(lStrListType))
			{
				disAnatomicalList=patientService.getDiagnDisAnatomical(lStrDiagnCode,lStrListType);
			}
			if(subCategoryList!=null)
			{
				List<String> finalSubCatList = new ArrayList<String>();
				for(LabelBean labelBean : subCategoryList)
				{
					finalSubCatList.add(labelBean.getID()+"@");
				}
				//finalSubDetailsList.add(finalSubCatList);
				finalSubDetailsList=finalSubDetailsList+finalSubCatList;
			}
			if(diseaseList!=null)
			{
				List<String> finalDiseaseList = new ArrayList<String>();
				for(LabelBean labelBean : diseaseList)
				{
					finalDiseaseList.add(labelBean.getID()+"@");
				}
				//finalSubDetailsList.add(finalDiseaseList);
				if(finalSubDetailsList.equals(""))
				{
					finalSubDetailsList=finalSubDetailsList+finalDiseaseList;
				}
				else
				{
					finalSubDetailsList=finalSubDetailsList+"$"+finalDiseaseList;
				}
			}
			if(disAnatomicalList!=null)
			{
				List<String> finaldisAnatomicalList = new ArrayList<String>();
				for(LabelBean labelBean : disAnatomicalList)
				{
					finaldisAnatomicalList.add(labelBean.getID()+"@");
				}
				//finalSubDetailsList.add(finaldisAnatomicalList);
				if(finalSubDetailsList.equals(""))
				{
					finalSubDetailsList=finalSubDetailsList+finaldisAnatomicalList;
				}
				else
				{
					finalSubDetailsList=finalSubDetailsList+"$"+finaldisAnatomicalList;
				}
			}
			//if (finalSubDetailsList != null && finalSubDetailsList.size() > 0)
			request.setAttribute("AjaxMessage", finalSubDetailsList);
			lStrPageName="ajaxResult";
		}
		if ("captureTelephonicPatientDtls".equalsIgnoreCase(lStrActionVal))
		{
			String wapNo=null;
			String familyNo=null;
			String cardNo=null;
			if(request.getParameter("wapNo")!=null && !request.getParameter("wapNo").equals(""))
			{
				wapNo=request.getParameter("wapNo");
			}
			if(request.getParameter("familyNo")!=null && !request.getParameter("familyNo").equals(""))
			{
				familyNo=request.getParameter("familyNo");
			}
			if(request.getParameter("cardNo")!=null && !request.getParameter("cardNo").equals(""))
			{
				cardNo=request.getParameter("cardNo");
			}
			patientVO=new PatientVO();
			String liNextVal="";
			String userId=null;
			Date date = new Date();
			String crtDate=sdfw.format(date);
			userId=session.getAttribute("EmpID").toString(); 
			GLOGGER.info("Card No"+wapNo+"/"+familyNo) ;
			liNextVal = patientService.getSequence("TELEPHONIC_ID");
			patientVO.setTelephonicId(liNextVal);
			patientVO.setCrtDt(crtDate);
			patientVO.setCrtUsr(userId);
			if(cardNo!=null)
			{
				patientVO.setRationCard(cardNo.toUpperCase());
			}
			patientVO.setCardType(patientForm.getCard_type());
			patientVO.setCardIssueDt(patientForm.getCardIssueDt());
			patientVO.setDateOfBirth(patientForm.getDateOfBirth());
			patientVO.setFirstName(patientForm.getFname());
			patientVO.setGender(patientForm.getGender());
			patientVO.setSchemeId(patientForm.getScheme());
			if(patientForm.getScheme()==null || patientForm.getScheme() .equalsIgnoreCase(""))
				{
					if(patientForm.getTelScheme()!=null &&
							!"".equalsIgnoreCase(patientForm.getTelScheme()))
						patientVO.setSchemeId(patientForm.getTelScheme());
				}
			if(familyNo!=null && !familyNo.equalsIgnoreCase("01") && patientForm.getHead()==null)
			{
				patientVO.setFamilyHead(config.getString("NFlag"));
			}
			else if(familyNo!=null)
			{
				patientVO.setFamilyHead(config.getString("YFlag"));
			}
			String[] age=patientForm.getAgeString().split("~");
			patientVO.setEmpCode(patientForm.getEmpCode());
			patientVO.setAge(age[0]);
			patientVO.setAgeMonths(age[1]);
			patientVO.setAgeDays(age[2]);
			patientVO.setRelation(patientForm.getRelation());
			patientVO.setCaste(patientForm.getCaste());
			patientVO.setOccupationCd(patientForm.getOccupation());
			patientVO.setContactNo(patientForm.getContactno());
			patientVO.setSlab(patientForm.getSlab());
			patientVO.setAddr1(patientForm.getHno());
			patientVO.setAddr2(patientForm.getStreet());
			patientVO.setVillageCode(patientForm.getVillage());
			patientVO.setState(patientForm.getState());
			patientVO.setDistrictCode(patientForm.getDistrict());
			patientVO.setMdl_mpl(patientForm.getMdl_mcl());
			if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
			{
				patientVO.setMandalCode(patientForm.getMandal());
			}
			else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
			{
				patientVO.setMandalCode(patientForm.getMunicipality());
			}
			patientVO.setPincode(patientForm.getPin());
			//start sameaddress check
			if(patientForm.getCommCheck()==null)
			{
				patientVO.setPermAddr1(patientForm.getComm_hno());
				patientVO.setPermAddr2(patientForm.getComm_street());
				patientVO.setC_pin_code(patientForm.getComm_pin());
				patientVO.setC_state(patientForm.getComm_state());
				patientVO.setC_district_code(patientForm.getComm_dist());
				patientVO.setC_mdl_mpl(patientForm.getComm_mdl_mcl());
				if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_mandal());
				}
				else if(patientForm.getComm_mdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getComm_municipality());
				}
				patientVO.setC_village_code(patientForm.getComm_village());	
			}
			else
			{
				patientVO.setPermAddr1(patientForm.getHno());
				patientVO.setPermAddr2(patientForm.getStreet());
				patientVO.setC_pin_code(patientForm.getPin());
				patientVO.setC_state(patientForm.getState());
				patientVO.setC_district_code(patientForm.getDistrict());
				patientVO.setC_mdl_mpl(patientForm.getMdl_mcl());
				if(patientForm.getMdl_mcl().equalsIgnoreCase("Mdl"))
				{
					patientVO.setC_mandal_code(patientForm.getMandal());
				}
				else if(patientForm.getMdl_mcl().equalsIgnoreCase("Mpl"))
				{
					patientVO.setC_mandal_code(patientForm.getMunicipality());
				}
				patientVO.setC_village_code(patientForm.getVillage());	
			}
			//end sameaddress check
			if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Emp")))
				patientVO.setEmployeeRenewal("0");
			if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase(config.getString("NewBornBaby")))
				patientVO.setEmployeeRenewal("0");
			else if(patientForm.getCard_type()!=null && patientForm.getCard_type().equalsIgnoreCase(config.getString("PatientIPOP.Pen")))
				patientVO.setPensionerRenewal("0");
			patientVO.setRegHospId(patientForm.getHospitalId());
			patientVO.setTeleCallerDesgn(patientForm.getTeleCallerDesgn());
			patientVO.setTeleCallerName(patientForm.getTeleCallerName());
			patientVO.setTeleDocDesgn(patientForm.getTeleDocDesgn());
			patientVO.setTeleDocName(patientForm.getTeleDocName());
			patientVO.setTeleDocPhoneNo(patientForm.getTeleDocPhoneNo());
			patientVO.setTeleDocremarks(patientForm.getRemarksProcedure()+"~"+patientForm.getRemarksDiagnosis());
			patientVO.setTelePhoneNo(patientForm.getTelePhoneNo());
			patientVO.setDiagnosisType(patientForm.getDiagType());
			patientVO.setMainCatName(patientForm.getMainCatName());
			patientVO.setSubCatName(patientForm.getSubCatName());
			patientVO.setCatName(patientForm.getCatName());
			patientVO.setDiseaseName(patientForm.getDiseaseName());
			patientVO.setDisAnatomicalName(patientForm.getDisAnatomicalName());
			patientVO.setAsriCatCode(patientForm.getAsriCatName());
			patientVO.setICDcatName(patientForm.getICDCatName());
			patientVO.setICDsubCatName(patientForm.getICDSubCatName());
			patientVO.setICDprocName(patientForm.getICDProcName());
			if(patientForm.getIndication().length()>3000)
			{
				patientVO.setIndication(patientForm.getIndication().substring(0, 3000));
			}
			else
			{
				patientVO.setIndication(patientForm.getIndication());
			}
			try{
				int rowsInserted=patientService.captureTelephonicPatientDtls(patientVO);
				if(rowsInserted==0)
				{
					GLOGGER.info("Patient cannot be registered");
					lStrPageName="failure";
				}
				else
				{
					patientForm.setTelephonicId(liNextVal);
					lStrPageName="telephonicSucess";
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
//				GLOGGER.error ( "Exception occured while Capturing telephonic intimation in PatientAction." +e.getMessage()) ;
			}    
		}
		if("telephonicDirectReg".equalsIgnoreCase(lStrActionVal)){
			List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
			session.setAttribute("stateList", stateList);
/*			List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
			session.setAttribute("districtList",distList);*/
			List<LabelBean> castesList=commonService.getComboDetails(lStrcastesHdrId);
			session.setAttribute("castesList",castesList);
			List<LabelBean> relationList=patientService.getRelations();
			session.setAttribute("relationList",relationList);
			List<LabelBean> hospitalList = patientService.getHospital(lStrUserId,roleId);
			session.setAttribute("hospitalList",hospitalList);
			List<LabelBean> occupationList=commonService.getOccupations();
			session.setAttribute("occupationList",occupationList);
			List<LabelBean> maritalStatusList=commonService.getComboDetails("CH5");
			session.setAttribute("maritalStatusList",maritalStatusList);
			patientForm.setDtTime(serverDt);
			patientForm.setDisableFlag(config.getString("YFlag"));
			PatientVO patientVo=new PatientVO();
			String cardNo=null;
			String cardType=null;
			String telephonicId=request.getParameter("telephonicId");
			if(request.getParameter("cardNo")!=null && !request.getParameter("cardNo").equals(""))
			{
				cardNo=request.getParameter("cardNo");
			}
			if(request.getParameter("cardType")!=null && !request.getParameter("cardType").equals(""))
			{
				cardType = request.getParameter("cardType");
			}
			patientVo.setTelephonicId(telephonicId);
			patientForm.setTelephonicId(telephonicId);
			patientVo.setCardNo(cardNo);
			patientVo.setCardType(cardType);
			patientForm.setScheme(schemeId);
			//patientForm.setCard_type(cardType);
			PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);   
			if(patientVO1!=null)
			{
				if(cardNo!=null)
				{
					patientForm.setCardNo(cardNo);
					patientForm.setCard_type(cardType);
					//patientForm.setSlab(patientVO1.getSlab());
				}
				patientForm.setDisableFlag(config.getString("NFlag"));
				patientForm.setSlab(patientVO1.getSlab());
				if(patientVO1.getEmpCode()!=null && !patientVO1.getEmpCode().equals(""))
				{
					patientForm.setEmpCode(patientVO1.getEmpCode());
				}
				if(patientVO1.getCardIssueDt()!=null)
				{
					patientForm.setCardIssueDt(patientVO1.getCardIssueDt());
				}
				if(patientVO1.getDateOfBirth()!=null)
					patientForm.setDateOfBirth(patientVO1.getDateOfBirth());
				if(patientVO1.getFamilyHead()!=null)
					patientForm.setHead(patientVO1.getFamilyHead());
				if(patientVO1.getGender()!=null)
					patientForm.setGender(patientVO1.getGender());
				if(patientVO1.getFirstName()!=null)
					patientForm.setFname(patientVO1.getFirstName()); 
				if(patientVO1.getRelation()!=null)
					patientForm.setRelation(patientVO1.getRelation());
				if(patientVO1.getContactNo()!=null)
					patientForm.setContactno(patientVO1.getContactNo());
				if(patientVO1.getRegHospId()!=null)
					patientForm.setHospitalId(patientVO1.getRegHospId());
				//setting card address
				if(patientVO1.getAddr1()!=null)
					patientForm.setHno(patientVO1.getAddr1());
				if(patientVO1.getAddr2()!=null)
					patientForm.setStreet(patientVO1.getAddr2());
				List<LabelBean> districtList=null;
				if(patientVO1.getState()!=null)
				{
					patientForm.setState(patientVO1.getState());
					districtList=commonService.getAsrimLocations(distHdrId, patientVO1.getState());
				}
				request.setAttribute("districtList",districtList);
				if(patientVO1.getDistrictCode()!=null)
					patientForm.setDistrict(patientVO1.getDistrictCode());
				if(patientVO1.getMdl_mpl()!=null)
					patientForm.setMdl_mcl(patientVO1.getMdl_mpl());
				if(patientVO1.getMdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> mdlList=commonService.getAsrimLocations(lStrMandalHdrId, patientVO1.getDistrictCode());
					request.setAttribute("mdlList", mdlList);
					patientForm.setMandal(patientVO1.getMandalCode());
					List<LabelBean> villList=commonService.getAsrimLocations(lStrVillageHdrId, patientVO1.getMandalCode());
					request.setAttribute("villList", villList);
				}
				else if(patientVO1.getMdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> mplList=commonService.getAsrimLocations(lStrMunicipalHdrId, patientVO1.getDistrictCode());
					request.setAttribute("mplList", mplList);
					patientForm.setMunicipality(patientVO1.getMandalCode());
					List<LabelBean> villList=commonService.getAsrimLocations(lStrMplVillageHdrId, patientVO1.getMandalCode());
					request.setAttribute("villList", villList);
				}
				if(patientVO1.getVillageCode()!=null)
					patientForm.setVillage(patientVO1.getVillageCode());
				if(patientVO1.getPincode()!=null)
					patientForm.setPin(patientVO1.getPincode());
				//setting communication address
				if(patientVO1.getPermAddr1()!=null)
					patientForm.setComm_hno(patientVO1.getPermAddr1());
				if(patientVO1.getPermAddr2()!=null)
					patientForm.setComm_street(patientVO1.getPermAddr2());
				List<LabelBean> cdistrictList=null;
				if(patientVO1.getC_state()!=null)
				{
					patientForm.setComm_state(patientVO1.getC_state());
					cdistrictList=commonService.getAsrimLocations(distHdrId, patientVO1.getC_state());
				}
				request.setAttribute("cdistrictList",cdistrictList);
				if(patientVO1.getC_district_code()!=null)
					patientForm.setComm_dist(patientVO1.getC_district_code());
				if(patientVO1.getC_mdl_mpl()!=null)
					patientForm.setComm_mdl_mcl(patientVO1.getC_mdl_mpl());
				if(patientVO1.getC_mdl_mpl().equalsIgnoreCase("Mdl"))
				{
					List<LabelBean> cmdlList=commonService.getAsrimLocations(lStrMandalHdrId, patientVO1.getC_district_code());
					request.setAttribute("cmdlList", cmdlList);
					patientForm.setComm_mandal(patientVO1.getC_mandal_code());
					List<LabelBean> cvillList=commonService.getAsrimLocations(lStrVillageHdrId, patientVO1.getC_mandal_code());
					request.setAttribute("cvillList", cvillList);
				}
				else if(patientVO1.getC_mdl_mpl().equalsIgnoreCase("Mpl"))
				{
					List<LabelBean> cmplList=commonService.getAsrimLocations(lStrMunicipalHdrId, patientVO1.getC_district_code());
					request.setAttribute("cmplList", cmplList);
					patientForm.setComm_municipality(patientVO1.getC_mandal_code());
					List<LabelBean> cvillList=commonService.getAsrimLocations(lStrMplVillageHdrId, patientVO1.getC_mandal_code());
					request.setAttribute("cvillList", cvillList);
				}
				if(patientVO1.getC_village_code()!=null)
					patientForm.setComm_village(patientVO1.getC_village_code());
				if(patientVO1.getC_pin_code()!=null)
					patientForm.setComm_pin(patientVO1.getC_pin_code());
				if(patientVO1.getOccupationCd()!=null)
					patientForm.setOccupation(patientVO1.getOccupationCd());
				patientForm.setDtTime(serverDt);
			}
			else
			{
				patientForm.setDisableFlag(config.getString("YFlag"));
				patientForm.setCardNo("");
				patientForm.setCard_type("");
				patientForm.setHead("");
				patientForm.setDtTime(serverDt);
				patientForm.setErrMsg(config.getString("err.InvalidCardNo"));
			}
			patientForm.setTelephonicReg("Yes");
			lStrPageName = "InPatientRegistration"; 
		}
		if("ViewTelephonicDtls".equalsIgnoreCase(lStrActionVal)){
			String telephonicId=request.getParameter("telephonicId");
			if(telephonicId==null || telephonicId.equalsIgnoreCase("")){
				telephonicId = patientForm.getTelephonicId();
			}
			PatientVO patientVo = new PatientVO();
			patientVo.setTelephonicId(telephonicId);
			patientForm.setTelephonicId(telephonicId);
			PatientVO patientVO1 =  patientService.getTelephonicIntimationDtls(patientVo);
			if(patientVO1.getCardNo()!=null && !patientVO1.getCardNo().equals(""))
			{
				patientForm.setCardNo(patientVO1.getCardNo());	
			}
			else
			{
				patientForm.setCardNo("NA");
			}
			patientForm.setCard_type(patientVO1.getCardType());
			patientForm.setFname(patientVO1.getFirstName());
			if(patientVO1.getCardIssueDt()!=null)
				patientForm.setCardIssueDt(patientVO1.getCardIssueDt());
			else
				patientForm.setCardIssueDt("NA");
			if(patientVO1.getDateOfBirth()!=null)
				patientForm.setDateOfBirth(patientVO1.getDateOfBirth());
			else
				patientForm.setDateOfBirth("NA");
			patientForm.setYears(patientVO1.getAge());
			patientForm.setMonths(patientVO1.getAgeMonths());
			patientForm.setDays(patientVO1.getAgeDays());
			/*String casteName=commonService.getCmbHdrname(lStrcastesHdrId, patientVO1.getCaste());
        patientForm.setCaste(casteName);*/
			patientForm.setGender(patientVO1.getGender());
			patientForm.setRelation(patientVO1.getRelOthers());
			patientForm.setOccupation(patientVO1.getOccOthers());
			patientForm.setContactno(patientVO1.getContactNo());
			patientForm.setHno(patientVO1.getAddr1());
			patientForm.setStreet(patientVO1.getAddr2());
			patientForm.setState(patientService.getLocationName(patientVO1.getState()));
			String distName=patientService.getLocationName(patientVO1.getDistrictCode());
			patientForm.setDistrict(distName);
			String mandalName=patientService.getLocationName(patientVO1.getMandalCode());
			patientForm.setMandal(mandalName);
			String villName=patientService.getLocationName(patientVO1.getVillageCode());
			patientForm.setVillage(villName);
			patientForm.setPin(patientVO1.getPincode());
			patientForm.setComm_hno(patientVO1.getPermAddr1());
			patientForm.setComm_street(patientVO1.getPermAddr2());
			patientForm.setComm_state(patientService.getLocationName(patientVO1.getC_state()));
			String cDistName=patientService.getLocationName(patientVO1.getC_district_code());
			patientForm.setComm_dist(cDistName);
			String cMandalName=patientService.getLocationName(patientVO1.getC_mandal_code());
			patientForm.setComm_mandal(cMandalName);
			String cVillName=patientService.getLocationName(patientVO1.getC_village_code());
			patientForm.setComm_village(cVillName);
			patientForm.setComm_pin(patientVO1.getC_pin_code());
			patientForm.setHospitalName(patientVO1.getRegHospDt());
			patientForm.setTeleCallerDesgn(patientVO1.getTeleCallerDesgn());
			patientForm.setTeleCallerName(patientVO1.getTeleCallerName());
			patientForm.setTeleDocDesgn(patientVO1.getTeleDocDesgn());
			patientForm.setTeleDocName(patientVO1.getTeleDocName());
			patientForm.setTeleDocPhoneNo(patientVO1.getTeleDocPhoneNo());
			patientForm.setTelePhoneNo(patientVO1.getTelePhoneNo());
			patientForm.setDiagType(patientVO1.getDiagnosisType());
			patientForm.setMainCatName(patientVO1.getMainCatName());
			patientForm.setCatName(patientVO1.getCatName());
			patientForm.setSubCatName(patientVO1.getSubCatName());
			patientForm.setDiseaseName(patientVO1.getDiseaseName());
			patientForm.setDisAnatomicalName(patientVO1.getDisAnatomicalName());
			patientForm.setAsriCatName(patientVO1.getAsriCatCode());
			patientForm.setICDCatName(patientVO1.getICDcatName());
			patientForm.setICDSubCatName(patientVO1.getICDsubCatName());
			patientForm.setICDProcName(patientVO1.getICDprocName());
			patientForm.setIndication(patientVO1.getIndication());
			if(patientVO1.getTeleDocremarks()!=null)
				{
					String[] remarks=patientVO1.getTeleDocremarks().split("~");
					patientForm.setRemarksProcedure(remarks[0]);
					patientForm.setRemarksDiagnosis(remarks[1]);
				}
			patientForm.setDtTime(patientVO1.getCrtDt());
			patientForm.setHead(patientVO1.getFamilyHead());
			lStrPageName = "viewTelephonicDetails"; 
		}
		if("getComplaintList".equalsIgnoreCase(lStrActionVal)){
			String mainCompId=request.getParameter("mainCompId");
			List<String> complaintList=null;
			complaintList=patientService.getComplaints(mainCompId);
			for(int i=0 ;i<complaintList.size();i++)
			{
			if(complaintList.get(i).equals("S18.22.1~Trauma to tooth or Jaw@"))
				complaintList.set(i, "S18.22.1~Trauma to tooth or Face@");
			}
			if (complaintList != null && complaintList.size() > 0)
				request.setAttribute("AjaxMessage", complaintList);
			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getSubSymptomLst".equalsIgnoreCase(lStrActionVal)){
			String mainSymptomCode=request.getParameter("mainSymptomCode");			
			List<String> symptomList=null;
			symptomList=patientService.getSubSymptomLists(mainSymptomCode);
			if (symptomList != null && symptomList.size() > 0)
				request.setAttribute("AjaxMessage", symptomList);
			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getSymptomLst".equalsIgnoreCase(lStrActionVal)){
			String mainSymptomCode=request.getParameter("mainSymptomCode");
			String subSymptomCode=request.getParameter("subSymptomCode");
			List<String> symptomList=null;
			symptomList=patientService.getSymptomLists(mainSymptomCode,subSymptomCode);
			if (symptomList != null && symptomList.size() > 0)
				request.setAttribute("AjaxMessage", symptomList);
			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getGenInvestList".equalsIgnoreCase(lStrActionVal)){
			String lStrBlockId=request.getParameter("lStrBlockId");			
			List<String> symptomList=null;
			symptomList=patientService.getInvestigations(lStrBlockId);
			if (symptomList != null && symptomList.size() > 0)
				request.setAttribute("AjaxMessage", symptomList);
			lStrPageName="ajaxResult";
		}
		if(lStrActionVal!=null && "getInvestPrice".equalsIgnoreCase(lStrActionVal))
			{
				String blockId=request.getParameter("blockId");
				String investId=request.getParameter("investId");
				String price=null;
				price=patientService.getInvestPrice(blockId,investId);
				if(price!=null)
				{
					long investPrice=Long.valueOf(price);
					request.setAttribute("AjaxMessage",investPrice);
				}
				return mapping.findForward("ajaxResult");
			}
		if("getICDCatByAsriCode".equalsIgnoreCase(lStrActionVal)){
			String asriCatCode=request.getParameter("lStrAsriCatId");
			String hospCode=request.getParameter("hospId");
			String procType=request.getParameter("procType");
			//String treatType=request.getParameter("treatType");
			List<String> icdCatListTemp=null;
			List<String> icdCatList=new ArrayList<String>(); 
			List<String> doctorList=null;
			icdCatListTemp=commonService.getICDCategoryList(asriCatCode,procType);
			String[] transDetails=config.getString("transplantation_list").split("~");
			List<String> transHospLst=new ArrayList<String>(),transCodeLst=new ArrayList<String>();
			for(int i=0;i<transDetails.length;i++){
				if(i%2==0){
					transHospLst.add(transDetails[i]);
				}
				else
					transCodeLst.add(transDetails[i]);
			}
			//Liver transplantation only for KIMS SEC & Yashodha SEC
			//Heart Transplantation for Appollo Jubilee Hills
			int count=0;
				for(int i=0;i<icdCatListTemp.size();i++)
				{
					if(("S19").equalsIgnoreCase(asriCatCode))
					{
					if(transHospLst.contains(hospCode)){
					for(int j=0; j<transHospLst.size();j++)
					{
						if(transHospLst.get(j).equalsIgnoreCase(hospCode)){							
								if(("S19").equalsIgnoreCase(asriCatCode) && (icdCatListTemp.get(i).contains(transCodeLst.get(j))))
								{
									icdCatList.add(count,icdCatListTemp.get(i));
									count++;
								}
								else if(!("S19").equalsIgnoreCase(asriCatCode))
								{
									icdCatList.add(i,icdCatListTemp.get(i));
								}
						}
					}
					}
					}
					else
					{
						//String temp=catList.get(i);
						icdCatList.add(i,icdCatListTemp.get(i));
					}
				}
			doctorList=commonService.getDocListBySpec(asriCatCode,hospCode,schemeId);
			if (icdCatList != null && icdCatList.size() > 0)
				request.setAttribute("AjaxMessage", icdCatList);
			if (doctorList != null && doctorList.size() > 0)
				request.setAttribute("AjaxMessage1", doctorList);
			if( (!"".equalsIgnoreCase(asriCatCode) && config.getString("Robotic.Surgeries").contains("~"+asriCatCode+"~"))
					&& (!"".equalsIgnoreCase(hospCode) &&  config.getString("Robotic.Hosp").contains("~"+hospCode+"~")) ) 
				{
					request.setAttribute("AjaxMessage2", "Y");
				}
			else{
				request.setAttribute("AjaxMessage2", "N");
				}
			lStrPageName="ajaxResult";
		}
		if("getICDCatByAsriCodeMed".equalsIgnoreCase(lStrActionVal)){
			String asriCatCode=request.getParameter("lStrAsriCatId");
			String hospCode=request.getParameter("hospId");
			String treatType=request.getParameter("treatType");
			//String treatType=request.getParameter("treatType");
			List<String> icdCatListTemp=null;
			List<String> icdCatList=new ArrayList<String>(); 
			icdCatListTemp=commonService.getICDCategoryList(asriCatCode,treatType);
			String[] transDetails=config.getString("transplantation_list").split("~");
			List<String> transHospLst=new ArrayList<String>(),transCodeLst=new ArrayList<String>();
			for(int i=0;i<transDetails.length;i++){
				if(i%2==0){
					transHospLst.add(transDetails[i]);
				}
				else
					transCodeLst.add(transDetails[i]);
			}
			//Liver transplantation only for KIMS SEC & Yashodha SEC
			//Heart Transplantation for Appollo Jubilee Hills
			int count=0;
				for(int i=0;i<icdCatListTemp.size();i++)
				{
					if(("S19").equalsIgnoreCase(asriCatCode))
					{
					if(transHospLst.contains(hospCode)){
					for(int j=0; j<transHospLst.size();j++)
					{
						if(transHospLst.get(j).equalsIgnoreCase(hospCode)){							
								if(("S19").equalsIgnoreCase(asriCatCode) && (icdCatListTemp.get(i).contains(transCodeLst.get(j))))
								{
									icdCatList.add(count,icdCatListTemp.get(i));
									count++;
								}
								else if(!("S19").equalsIgnoreCase(asriCatCode))
								{
									icdCatList.add(i,icdCatListTemp.get(i));
								}
						}
					}
					}
					}
					else
					{
						//String temp=catList.get(i);
						icdCatList.add(i,icdCatListTemp.get(i));
					}
				}
			//doctorList=commonService.getDocListBySpec(asriCatCode,hospCode,schemeId);
			if (icdCatList != null && icdCatList.size() > 0)
				request.setAttribute("AjaxMessage", icdCatList);
	/*		if (doctorList != null && doctorList.size() > 0)
				request.setAttribute("AjaxMessage1", doctorList);*/
			if( (!"".equalsIgnoreCase(asriCatCode) && config.getString("Robotic.Surgeries").contains("~"+asriCatCode+"~"))
					&& (!"".equalsIgnoreCase(hospCode) &&  config.getString("Robotic.Hosp").contains("~"+hospCode+"~")) ) 
				{
					request.setAttribute("AjaxMessage2", "Y");
				}
			else{
				request.setAttribute("AjaxMessage2", "N");
				}
			lStrPageName="ajaxResult";
		}
		if("getProcByCat".equalsIgnoreCase(lStrActionVal)){
			EhfPatient ehfPatient=null;
			String lStrPatientId=null;
			String hosType=null;
			String state=null;
			lStrPatientId=request.getParameter("patientId");
			state=request.getParameter("scheme");
			hosType=request.getParameter("hosType");
			String treatType=request.getParameter("treatType");
			if(state==null || state=="")
			{
			patientForm.setPatientNo(lStrPatientId);
			if(lStrPatientId!=null)
				{
					ehfPatient=(EhfPatient)patientService.getPatientDetails(lStrPatientId);
					state=ehfPatient.getSchemeId();
				}
			}
			String icdCatCode=request.getParameter("lStrICDCatId");
			String asriCatCode=request.getParameter("lStrAsriCode");
			String hospId = request.getParameter("hospId");
			List<String> therapyProcList=null;
			therapyProcList=patientService.getIcdProcList(icdCatCode,asriCatCode,hospId,state,hosType,treatType);
			//For Adding and removing Dental Follow Up Procedure
			if(asriCatCode!=null && asriCatCode.equalsIgnoreCase(config.getString("DentalSurgeryID")))
				therapyProcList=patientService.checkDenFlp(lStrPatientId,therapyProcList);
			if (therapyProcList != null && therapyProcList.size() > 0)
				request.setAttribute("AjaxMessage", therapyProcList);
			lStrPageName="ajaxResult";
		}
		if("getProcByCatMed".equalsIgnoreCase(lStrActionVal)){
			String lStrPatientId=null;
			String hosType=null;
			String state="CD202";
			lStrPatientId=request.getParameter("patientId");
			//state=request.getParameter("scheme");
			String treatType=request.getParameter("treatType");
			String icdCatCode=request.getParameter("lStrICDCatId");
			String asriCatCode=request.getParameter("lStrAsriCode");
			String hospId = request.getParameter("hospId");
			List<String> therapyProcList=null;
			therapyProcList=patientService.getIcdProcList(icdCatCode,asriCatCode,hospId,state,hosType,treatType);
			//For Adding and removing Dental Follow Up Procedure
			if(asriCatCode!=null && asriCatCode.equalsIgnoreCase(config.getString("DentalSurgeryID")))
				therapyProcList=patientService.checkDenFlp(lStrPatientId,therapyProcList);
			if (therapyProcList != null && therapyProcList.size() > 0)
				request.setAttribute("AjaxMessage", therapyProcList);
			lStrPageName="ajaxResult";
		}
		if("getUnitsByProc".equalsIgnoreCase(lStrActionVal))
		{
			String icdProcCode=request.getParameter("lStrProcCode");
			int dentalUnits = 0;
			dentalUnits=patientService.getDenUnitsList(icdProcCode);
			request.setAttribute("AjaxMessage", dentalUnits);
			lStrPageName="ajaxResult";
		}
		if("getTherapyProcCodes".equalsIgnoreCase(lStrActionVal)){
			String icdProcCode=request.getParameter("lStrICDProcId");
			String procCode=null;
			procCode=patientService.getTherapyProcCodes(icdProcCode);
			if (procCode != null)
				request.setAttribute("AjaxMessage", procCode);
			lStrPageName="ajaxResult";
		}
		if("getOpPkgList".equalsIgnoreCase(lStrActionVal)){
			String cardNo=null;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			String opCatCode= request.getParameter("lStrOpCatCode");
			List<String> opPkgList=null;
			opPkgList=patientService.getOpPkgList(opCatCode);
			if (opPkgList != null && opPkgList.size() > 0)
				request.setAttribute("AjaxMessage", opPkgList);
			lStrPageName="ajaxResult";
		}
		if("getOpIcdList".equalsIgnoreCase(lStrActionVal)){
			String opCode= request.getParameter("lStrOpPkgCode");
			List<String> opIcdList=null;
			opIcdList=patientService.getOpIcdList(opCode);
			if (opIcdList != null && opIcdList.size() > 0)
				request.setAttribute("AjaxMessage", opIcdList);
			lStrPageName="ajaxResult";
		}
		if("casePrintForm".equalsIgnoreCase(lStrActionVal))
		{
			String caseNo=null;
			String caseId=null;
			String hospId=null;
			CommonDtlsVO commonDtlsVO=null;
			//String opTgFlag=null;
			 fromDisp=request.getParameter("fromDisp");
			 if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					patientForm.setFromDisp(fromDisp);
					request.setAttribute("fromDisp", fromDisp);
				}	
				else
				{
					patientForm.setFromDisp("N");
					request.setAttribute("fromDisp", "N");
				}
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseNo=request.getParameter("caseId");
			}
			String[] caseValues=caseNo.split("/");
			if(caseValues!=null){
			caseId=caseValues[2]!=null?caseValues[2]:"";
			hospId=caseValues[1];
			}
			String dentalrntdbvalue=patientService.getTreatmentDntlvalue(caseId);
			request.setAttribute("dentalOrNot", dentalrntdbvalue);
			List<CommonDtlsVO> commonDtls=patientService.getPatientCommonDtls(caseId);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				patientForm.setPatientName(commonDtlsVO.getPATIENTNAME());
				patientForm.setScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getPatientScheme()!=null){
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				;}
				patientForm.setTelScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getScheme()!=null)
				{
					request.setAttribute("schemeId",commonDtlsVO.getScheme());
					request.setAttribute("caseSchemeId",commonDtlsVO.getScheme());
				}
				patientForm.setCardNo(commonDtlsVO.getCARDNO());
				patientForm.setDistrict(commonDtlsVO.getDISTRICT());
				patientForm.setMandal(commonDtlsVO.getMANDAL());
				patientForm.setVillage(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null && !"".equals(commonDtlsVO.getDate()))
					patientForm.setDtTime(sdfw.format(commonDtlsVO.getDate()));
				if(commonDtlsVO.getINTIID()!=null)
					request.setAttribute("regHospId", commonDtlsVO.getINTIID());
				patientForm.setAgeString(commonDtlsVO.getAGE());
				patientForm.setContactno(commonDtlsVO.getCONTACT());
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				patientForm.setHospitalName(commonDtlsVO.getHOSPNAME());
				patientForm.setGender(commonDtlsVO.getGENDER());
				patientForm.setAddr(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientForm.setDateIp(commonDtlsVO.getPATDT());
				patientForm.setIpNo(commonDtlsVO.getIPNO());
				patientForm.setAdmissionType(commonDtlsVO.getADMTYPE());
				patientForm.setHospaddr(commonDtlsVO.getHOSPADDR());
				patientForm.setDocMobileNo(commonDtlsVO.getDOCCONTACT());
				patientForm.setDocQual(commonDtlsVO.getDOCQUAL());
				patientForm.setDocRegNo(commonDtlsVO.getDOCREGNO());
				if(commonDtlsVO.getPatientScheme()!=null)
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				PreauthVO patientData=patientService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
				if(patientData!=null)
				{
					patientForm.setPatTemp(patientData.getTemperature());
					patientForm.setPatPulse(patientData.getPulseRate());
					patientForm.setRespir(patientData.getRespirationRate());
					patientForm.setBloodPrLt(patientData.getBpLmt());
					patientForm.setBloodPrRt(patientData.getBpRmt());
					patientForm.setHeight(patientData.getHeight());
					patientForm.setWeight(patientData.getWeight());
					patientForm.setBmi(patientData.getBmi());
					patientForm.setPastHist(patientData.getPastIllness());
					patientForm.setFamHist(patientData.getFamilyHis());
					patientForm.setSymptomName(patientData.getSymName());
					patientForm.setComplaints(patientData.getPatComplaint());
					patientForm.setComplType(patientData.getComplaintType());
					patientForm.setPresentHistory(patientData.getPresentIllness());
					patientForm.setPastIllnessValue(patientData.getPastIllnessValue());
					patientForm.setFamilyHistValue(patientData.getFamilyHistValue());
					patientForm.setFamilyHistoryOthr(patientData.getFamilyHistoryOthr());
					patientForm.setPastHistryOthr(patientData.getPastIllenesOthr());
					patientForm.setRemarks(patientData.getRemarks());
					patientForm.setDrughistory(patientData.getDrugHstVal());
					patientForm.setMedicalHistVal(patientData.getMedHistVal());
					patientForm.setShowMedicalTextval(patientData.getMedicalHistoryOthr());
					patientForm.setRegionalLymphadenopathyDtrsTxt(patientData.getRegionalLymphadenopathyDtrsTxt());
					patientForm.setJawsDtrsTxt(patientData.getJawsDtrsTxt());
					patientForm.setRegionalLymphadenopathyDtrsSub(patientData.getRegionalLymphadenopathyDtrsSub());
					patientForm.setJawsDtrsSub(patientData.getJawsDtrsSub());
					patientForm.setTmjDtrsSub(patientData.getTmjDtrsSub());
					patientForm.setFaceDtrsSub(patientData.getFaceDtrsSub());
					List<LabelBean> intraoraldtls=null;
					String intraoral="HD2";
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					patientForm.setIntraoraldtls(intraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					patientForm.setDntsublistoral0(patientData.getDntsublistoral0());
					patientForm.setDntsublistoral1(patientData.getDntsublistoral1());
					patientForm.setDntsublistoral2(patientData.getDntsublistoral2());
					patientForm.setDntsublistoral3(patientData.getDntsublistoral3());
					patientForm.setDntsublistoral4(patientData.getDntsublistoral4());
					patientForm.setDntsublistoral5(patientData.getDntsublistoral5());
					patientForm.setDntsublistoral6(patientData.getDntsublistoral6());
					patientForm.setSwSite(patientData.getSwSite());
					patientForm.setSwSize(patientData.getSwSize());
					patientForm.setSwExtension(patientData.getSwExtension());
					patientForm.setSwColour(patientData.getSwColour());
					patientForm.setSwConsistency(patientData.getSwConsistency());
					patientForm.setSwTenderness(patientData.getSwTenderness());
					patientForm.setSwBorders(patientData.getSwBorders());
					patientForm.setPsSite(patientData.getPsSite());
					patientForm.setPsDischarge(patientData.getPsDischarge());
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					String occlusion="CH12";
					String deciduousdent ="CH13";
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					EhfPatient ehfPatient=null;
					ehfPatient=(EhfPatient)patientService.getPatientDetails(commonDtlsVO.getPATID());
					PreauthVO preauthDtlsVO = new PreauthVO();
					preauthDtlsVO = patientService.getPatientFullDtls(caseId, ehfPatient.getPatientId(),fromDisp);		
					if(preauthDtlsVO.getCarriesdecidous()!=null)
					{
						patientForm.setCarriesdecidous(preauthDtlsVO.getCarriesdecidous());
					}
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", preauthDtlsVO);
					patientForm.setPreviousDentalTreatment(patientData.getPreviousDentalTreatment());
					patientForm.setOcclusionTxt(patientData.getOcclusionTxt());
					patientForm.setOcclusionTypeTxt(patientData.getOcclusionTypeTxt());
					patientForm.setOcclusionOtherTxt(patientData.getOcclusionOtherTxt());
				}
				CommonDtlsVO othrDtls=patientService.getOtherDtls(caseId, commonDtlsVO.getPATID(),fromDisp);
				patientForm.setAllergy(othrDtls.getAllergy());
				patientForm.setHabbits(othrDtls.getHabbits());
				patientForm.setInvestigationLt(othrDtls.getInvestigations());
				int diagCount=0;
				if(othrDtls.getDiagnosisType()!=null && !("").equalsIgnoreCase(othrDtls.getDiagnosisType()))
				patientForm.setDiagType(othrDtls.getDiagnosisType());
				else
				{
					request.setAttribute("DiagnosisType","N");
					diagCount++;
				}
				if(othrDtls.getMainCatName()!=null && !("").equalsIgnoreCase(othrDtls.getMainCatName()))
				patientForm.setMainCatName(othrDtls.getMainCatName());
				else
				{
					request.setAttribute("MainCatName","N");
					diagCount++;
				}
				if(othrDtls.getCatName()!=null && !("").equalsIgnoreCase(othrDtls.getCatName()))
				patientForm.setCatName(othrDtls.getCatName());
				else
				{
					request.setAttribute("CatName","N");
					diagCount++;
				}
				if(othrDtls.getSubCatName()!=null && !("").equalsIgnoreCase(othrDtls.getSubCatName()))
				patientForm.setSubCatName(othrDtls.getSubCatName());
				else
				{
					request.setAttribute("SubCatName","N");
					diagCount++;
				}
				if(othrDtls.getDisName()!=null && !("").equalsIgnoreCase(othrDtls.getDisName()))
				patientForm.setDiseaseName(othrDtls.getDisName());
				else
				{
					request.setAttribute("DisName","N");
					diagCount++;
				}
				int diagAny=0;
				if(othrDtls.getDisAnatomicalSitename()!=null && !("").equalsIgnoreCase(othrDtls.getDisAnatomicalSitename()))
				patientForm.setDisAnatomicalName(othrDtls.getDisAnatomicalSitename());
				else
				{
					diagAny++;
					diagCount++;
				}
				if(othrDtls.getOtherDiagName()!=null&& !("").equalsIgnoreCase(othrDtls.getDisAnatomicalSitename()))
				{
					patientForm.setDisAnatomicalName(othrDtls.getOtherDiagName());
					request.setAttribute("DisAnatomicalSitename","Y");
				}
				else
				{
					diagAny++;
				}
				if(diagAny==2)
				{
					request.setAttribute("DisAnatomicalSitename","N");
				}
				if(diagCount>6)
				{
					request.setAttribute("disableDiag","Y");
				}
				String mithraName = patientService.getEmpNameById(commonDtlsVO.getMithra());
				if(mithraName!=null && !mithraName.equalsIgnoreCase(""))
					patientForm.setMithra(mithraName);
				List<PreauthVO> procDtls=patientService.getcaseSurgeryDtls(caseId);
				patientForm.setProcList(procDtls);
				patientForm.setCaseId(caseNo);
				request.setAttribute("decFlag", request.getParameter("decFlag"));
				if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IP") || commonDtlsVO.getPATTYPE().equalsIgnoreCase("DOP") || commonDtlsVO.getPATTYPE().equalsIgnoreCase(config.getString("PatientIPOP.IPD")))
					{
						patientForm.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
						lStrPageName="ipCaseCopy";
					}
					else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IPM"))
					{
						if("IPM".equalsIgnoreCase(commonDtlsVO.getPATTYPE()))
						{
							String[] caseValuesArr=caseNo.split("/");
							caseId=caseValuesArr[2];
							List<LabelBean> catList = patientService.getCatName(caseId);
							if(catList.size() > 0)
							{
								patientForm.setMedicalSpclty(catList.get(0).getICDNAME());
								patientForm.setMedicalCat(catList.get(0).getVALUE());
								patientForm.setNewProc(catList.get(0).getNEWPROC());
								if(catList.get(0).getCOMPROLE() != null)
								{
								request.setAttribute("ipOtherRemarks2",catList.get(0).getCOMPROLE());
								}
							}
							patientForm.setOpNo(commonDtlsVO.getIPNO());
							patientForm.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
							String critical =patientService.getCriticalFlg(caseId);
							if(critical!=null && !"".equalsIgnoreCase(critical))
							{
								patientForm.setCritical(critical);
							}
							lStrPageName="ipmCaseCopy";
						}
					}
					else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
					{
						List<DrugsVO> drugs=patientService.getDrugs(commonDtlsVO.getPATID(),"op");
						if(drugs!=null && drugs.size()>0)
						patientForm.setDrugLt(drugs);
						String doctorName="";
						if((commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))|| commonDtlsVO.getINTIID()!=null && "G".equalsIgnoreCase(hospGovu))
						{
							/*EhfOpConsultData opDocDtls=new EhfOpConsultData();
							opDocDtls=patientService.getOpDocDtls(commonDtlsVO.getPATID());
							if(opDocDtls!=null)
							{
							request.setAttribute("opTgFlag","Y");
							opTgFlag="Y";
							patientForm.setDiagnosedBy(opDocDtls.getDiagnoisedBy());
							patientForm.setDoctorName(opDocDtls.getUnitHodName());
							if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
							{
								patientForm.setDiagnosedBy("Resident Doctor");	
							}
						}*/
						}
						  if(commonDtlsVO.getUnitHodName()!=null && !("").equalsIgnoreCase(commonDtlsVO.getUnitHodName()))
							 {
								doctorName=commonDtlsVO.getUnitHodName();
							 }
						  else if("Consultant".equalsIgnoreCase(commonDtlsVO.getDoctType()) || "InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDoctorById(commonDtlsVO.getDOCID());
						}
						else if("DutyDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDutyDoctorById(commonDtlsVO.getDOCID());
						}
						else if("MEDCO".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName=patientService.getEmpNameById(commonDtlsVO.getDOCID());
						}
						if(doctorName!=null && !doctorName.equalsIgnoreCase(""))
							patientForm.setDoctorName(doctorName);
						patientForm.setDiagnosedBy(commonDtlsVO.getDoctType());
						request.setAttribute("diagnosedBy",commonDtlsVO.getDoctType());
												if((commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))|| commonDtlsVO.getINTIID()!=null && config.getString("HOSP_NIMS").equalsIgnoreCase(commonDtlsVO.getINTIID()))
						{
							if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
							{
								EhfOpConsultData opDocDtls=new EhfOpConsultData();
								opDocDtls=patientService.getOpDocDtls(commonDtlsVO.getPATID());
								if(opDocDtls!=null)
								{
								request.setAttribute("opTgFlag","Y");
								//opTgFlag="Y";
								patientForm.setDiagnosedBy(opDocDtls.getDiagnoisedBy());
								patientForm.setDoctorName(opDocDtls.getUnitHodName());
								request.setAttribute("diagnosedBy",opDocDtls.getDiagnoisedBy());
								/*if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
								{
									patientForm.setDiagnosedBy("Resident Doctor");	
								}*/
							}
							}
						}
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
						lStrPageName="opCaseCopyTG";
						}
						else
						{
							lStrPageName="opCaseCopy";
						}
					}
				}
		}
		if("submitPharmaCase".equalsIgnoreCase(lStrActionVal))
			{
				String pharmaCases=request.getParameter("pharmaCases");
				String apprPharma=request.getParameter("apprPharma");
				request.setAttribute("pharmaCases", pharmaCases);
				request.setAttribute("apprPharma", apprPharma);
				String caseId=null,patientId=null,returnMsg=null,drugDtls=null;
				caseId=request.getParameter("pharmaCaseId");
				patientId=request.getParameter("patientId");
				drugDtls=request.getParameter("drugDtls");
				if(caseId!=null && !"".equalsIgnoreCase(caseId)
						&& (patientId!=null && !"".equalsIgnoreCase(patientId)))
					{
						returnMsg=patientService.submitPharmacyCase(caseId,patientId,drugDtls);
						lStrActionVal="getPharmaOPCases";
					}
				else
					{
						returnMsg=config.getString("insufficientData.Pharmacy");
						lStrActionVal="pharmaCasePrintForm";
					}
				request.setAttribute("returnMsg", returnMsg);
			}
		if("pharmaCasePrintForm".equalsIgnoreCase(lStrActionVal))
		{
			String pharmaCases=request.getParameter("pharmaCases");
			String apprPharma=request.getParameter("apprPharma");
			request.setAttribute("pharmaCases", pharmaCases);
			request.setAttribute("apprPharma", apprPharma);
			String caseNo=null;
			//String patientType=null;
			String caseId=null;
			//String hospId=null;
			CommonDtlsVO commonDtlsVO=null;
			//String opTgFlag=null;
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseNo=request.getParameter("caseId");
			}
			if(request.getParameter("patientId")!=null)
				request.setAttribute("patientId", request.getParameter("patientId"));
			if(request.getParameter("caseId")!=null)
				request.setAttribute("caseId", request.getParameter("caseId"));
			String[] caseValues=caseNo.split("/");
			caseId=caseValues[2];
			//hospId=caseValues[1];
			List<CommonDtlsVO> commonDtls=patientService.getPatientCommonDtls(caseId);
			request.setAttribute("pharmaCaseId", caseId);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				if(commonDtlsVO.getScheme()!=null && 
						config.getString("TG").equalsIgnoreCase(commonDtlsVO.getScheme())
						&& commonDtlsVO.getHospType()!='\u0000' && commonDtlsVO.getHospType()=='G')
					{
						if(bioRegFlag!=null && !"".equalsIgnoreCase(bioRegFlag) 
								 && "Y".equalsIgnoreCase(bioRegFlag))
							{
								PatientVO biomVO=patientService.getBiomDtls(commonDtlsVO);
								if(biomVO!=null)
									{
										request.setAttribute("showBiom", "Y");
										patientForm.setTgGovPatCond("Y");
										patientForm.setBioFinger(biomVO.getFingerCaptured());
										patientForm.setFingerPrint(biomVO.getFingerPrint());
										patientForm.setBioHand(biomVO.getBioHand());
									}
							}	
					}
				patientForm.setPatientName(commonDtlsVO.getPATIENTNAME());
				patientForm.setScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getPatientScheme()!=null){
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				;}
				patientForm.setTelScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getScheme()!=null)
				{
					request.setAttribute("schemeId",commonDtlsVO.getScheme());
					request.setAttribute("caseSchemeId",commonDtlsVO.getScheme());
				}
				if(commonDtlsVO.getINTIID()!=null)
					request.setAttribute("regHospId", commonDtlsVO.getINTIID());
				patientForm.setCardNo(commonDtlsVO.getCARDNO());
				patientForm.setDistrict(commonDtlsVO.getDISTRICT());
				patientForm.setMandal(commonDtlsVO.getMANDAL());
				patientForm.setVillage(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null && !"".equals(commonDtlsVO.getDate()))
					patientForm.setDtTime(sdfw.format(commonDtlsVO.getDate()));
				patientForm.setAgeString(commonDtlsVO.getAGE());
				patientForm.setContactno(commonDtlsVO.getCONTACT());
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				request.setAttribute("pharmaPatId", commonDtlsVO.getPATID());
				patientForm.setHospitalName(commonDtlsVO.getHOSPNAME());
				patientForm.setGender(commonDtlsVO.getGENDER());
				patientForm.setAddr(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientForm.setDateIp(commonDtlsVO.getPATDT());
				patientForm.setIpNo(commonDtlsVO.getIPNO());
				patientForm.setAdmissionType(commonDtlsVO.getADMTYPE());
				patientForm.setHospaddr(commonDtlsVO.getHOSPADDR());
				patientForm.setDocMobileNo(commonDtlsVO.getDOCCONTACT());
				patientForm.setDocQual(commonDtlsVO.getDOCQUAL());
				patientForm.setDocRegNo(commonDtlsVO.getDOCREGNO());
				if(commonDtlsVO.getPatientScheme()!=null)
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				PreauthVO patientData=patientService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
				if(patientData!=null)
				{
					patientForm.setPatTemp(patientData.getTemperature());
					patientForm.setPatPulse(patientData.getPulseRate());
					patientForm.setRespir(patientData.getRespirationRate());
					patientForm.setBloodPrLt(patientData.getBpLmt());
					patientForm.setBloodPrRt(patientData.getBpRmt());
					patientForm.setHeight(patientData.getHeight());
					patientForm.setWeight(patientData.getWeight());
					patientForm.setBmi(patientData.getBmi());
					patientForm.setPastHist(patientData.getPastIllness());
					patientForm.setFamHist(patientData.getFamilyHis());
					patientForm.setSymptomName(patientData.getSymName());
					patientForm.setComplaints(patientData.getPatComplaint());
					patientForm.setComplType(patientData.getComplaintType());
					patientForm.setPresentHistory(patientData.getPresentIllness());
					patientForm.setPastIllnessValue(patientData.getPastIllnessValue());
					patientForm.setFamilyHistValue(patientData.getFamilyHistValue());
					patientForm.setFamilyHistoryOthr(patientData.getFamilyHistoryOthr());
					patientForm.setPastHistryOthr(patientData.getPastIllenesOthr());
					patientForm.setRemarks(patientData.getRemarks());
				}
				/*CommonDtlsVO othrDtls=patientService.getOtherDtls(caseId, commonDtlsVO.getPATID());
				patientForm.setAllergy(othrDtls.getAllergy());
				patientForm.setHabbits(othrDtls.getHabbits());
				patientForm.setInvestigationLt(othrDtls.getInvestigations());
				int diagCount=0;
				if(othrDtls.getDiagnosisType()!=null && !("").equalsIgnoreCase(othrDtls.getDiagnosisType()))
				patientForm.setDiagType(othrDtls.getDiagnosisType());
				else
				{
					request.setAttribute("DiagnosisType","N");
					diagCount++;
				}
				if(othrDtls.getMainCatName()!=null && !("").equalsIgnoreCase(othrDtls.getMainCatName()))
				patientForm.setMainCatName(othrDtls.getMainCatName());
				else
				{
					request.setAttribute("MainCatName","N");
					diagCount++;
				}
				if(othrDtls.getCatName()!=null && !("").equalsIgnoreCase(othrDtls.getCatName()))
				patientForm.setCatName(othrDtls.getCatName());
				else
				{
					request.setAttribute("CatName","N");
					diagCount++;
				}
				if(othrDtls.getSubCatName()!=null && !("").equalsIgnoreCase(othrDtls.getSubCatName()))
				patientForm.setSubCatName(othrDtls.getSubCatName());
				else
				{
					request.setAttribute("SubCatName","N");
					diagCount++;
				}
				if(othrDtls.getDisName()!=null && !("").equalsIgnoreCase(othrDtls.getDisName()))
				patientForm.setDiseaseName(othrDtls.getDisName());
				else
				{
					request.setAttribute("DisName","N");
					diagCount++;
				}
				if(othrDtls.getDisAnatomicalSitename()!=null && !("").equalsIgnoreCase(othrDtls.getDisAnatomicalSitename()))
				patientForm.setDisAnatomicalName(othrDtls.getDisAnatomicalSitename());
				else
				{
					request.setAttribute("DisAnatomicalSitename","N");
					diagCount++;
				}
				if(diagCount>6)
				{
					request.setAttribute("disableDiag","Y");
				}*/
				String mithraName = patientService.getEmpNameById(commonDtlsVO.getMithra());
				if(mithraName!=null && !mithraName.equalsIgnoreCase(""))
					patientForm.setMithra(mithraName);
				List<PreauthVO> procDtls=patientService.getcaseSurgeryDtls(caseId);
				patientForm.setProcList(procDtls);
				patientForm.setCaseId(caseNo);
				request.setAttribute("decFlag", request.getParameter("decFlag"));
				if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("IP") || commonDtlsVO.getPATTYPE().equalsIgnoreCase(config.getString("PatientIPOP.IPD")) )
					{
						patientForm.setDoctorName(patientService.getEmpNameById(commonDtlsVO.getDOCID()));
						lStrPageName="ipCaseCopy";
					}
					else if(commonDtlsVO.getPATTYPE().equalsIgnoreCase("OP"))
					{
						List<DrugsVO> drugs=patientService.getDrugs(commonDtlsVO.getPATID(),"op");
						if(drugs!=null)
							request.setAttribute("drugsSize",drugs.size());
						patientForm.setDrugLt(drugs);
						String doctorName="";
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
							request.setAttribute("opTgFlag","Y");
							//opTgFlag="Y";
							if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
							{
								patientForm.setDiagnosedBy("Resident Doctor");	
							}
						}
						  if(commonDtlsVO.getUnitHodName()!=null && !("").equalsIgnoreCase(commonDtlsVO.getUnitHodName()))
							 {
								doctorName=commonDtlsVO.getUnitHodName();
							 }
						  else if("Consultant".equalsIgnoreCase(commonDtlsVO.getDoctType()) || "InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDoctorById(commonDtlsVO.getDOCID());
						}
						else if("DutyDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName = patientService.getDutyDoctorById(commonDtlsVO.getDOCID());
						}
						else if("MEDCO".equalsIgnoreCase(commonDtlsVO.getDoctType()))
						{
							doctorName=patientService.getEmpNameById(commonDtlsVO.getDOCID());
						}
						if(doctorName!=null && !doctorName.equalsIgnoreCase(""))
							patientForm.setDoctorName(doctorName);
						patientForm.setDiagnosedBy(commonDtlsVO.getDoctType());
						request.setAttribute("diagnosedBy",commonDtlsVO.getDoctType());
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
							if("InHouseDoctor".equalsIgnoreCase(commonDtlsVO.getDoctType()))
							{
								patientForm.setDiagnosedBy("Resident Doctor");	
							}
						}
						if(commonDtlsVO.getScheme()!=null && (config.getString("TG")).equalsIgnoreCase(commonDtlsVO.getScheme()))
						{
						lStrPageName="opPharmaCaseCopyTG";
						}
						else
						{
							lStrPageName="opPharmaCaseCopyTG";
						}
					}
				}
		}
		if("getChronicDetails".equalsIgnoreCase(lStrActionVal))
		{
			String cardNo=null;
			if(request.getParameter("lStrCardNo")!=null && !request.getParameter("lStrCardNo").equals(""))
			{
				cardNo=request.getParameter("lStrCardNo");
			}
			List<DrugsVO> drugsList=patientService.getChronicDetails(cardNo);
			List<String> drugsLst=new ArrayList<String>(); 
			for(DrugsVO drugsVO : drugsList)
			{
				drugsLst.add(drugsVO.getDrugTypeName()+"~"+drugsVO.getDrugSubTypeName()+"~"+drugsVO.getDrugName()+"~"+drugsVO.getRoute()+"~"+drugsVO.getStrength()+"~"+drugsVO.getDosage()+"~"+drugsVO.getMedicationPeriod()+"@");
			}
			if (drugsList != null && drugsList.size() > 0)
				request.setAttribute("AjaxMessage", drugsLst);
			lStrPageName="ajaxResult";
		}
		if("dtrsPrintForm".equalsIgnoreCase(lStrActionVal))
		{
			String caseId=null;
			CommonDtlsVO commonDtlsVO=null;
			DrugsVO drugsVO = new DrugsVO();
			String treatmentDntl=request.getParameter("treatmentDntl");
			 fromDisp=request.getParameter("fromDisp");
			 String dispDentalFlag=request.getParameter("dispDental");
			 if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
				{
					patientForm.setFromDisp(fromDisp);
					request.setAttribute("fromDisp", fromDisp);
				}	
				else
				{
					patientForm.setFromDisp("N");
					request.setAttribute("fromDisp", "N");
				}
			if(request.getParameter("caseId")!=null && !request.getParameter("caseId").equals(""))
			{
				caseId=request.getParameter("caseId");
			}
			String dentalrntdbvalue=patientService.getTreatmentDntlvalue(caseId);
			if(!"".equalsIgnoreCase(caseId) && (!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)))
			{
				drugsVO = patientService.getDispDrugDtlsList(caseId);	
				if(drugsVO.getDrugList()!=null && drugsVO.getDrugList().size()>0)
				{
			    	patientForm.setDrugLt(drugsVO.getDrugList());
			    	String drugLst="";
			    	for(DrugsVO drugVO: drugsVO.getDrugList())
			    	{
			    		if(drugVO.getDRUGCODE()!=null && drugVO.getDRUGCODE().contains("OD"))
			    		{
			    			drugLst = drugLst + drugVO.getOTHERDRUGNAME()+"~"+drugVO.getDRUGCODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"@#";
			    		}
			    		else
			    		{
			    			drugLst = drugLst + drugVO.getDRUGTYPECODE()+"~"+drugVO.getDRUGSUBTYPECODE()+"~"+drugVO.getROUTETYPE()+"~"+drugVO.getROUTE()+"~"+drugVO.getSTRENGTHTYPE()+"~"+drugVO.getSTRENGTH()+"~"+drugVO.getDOSAGE()+"~"+drugVO.getMEDICATIONPERIOD()+"@#";
			    		}
			    	}
					request.setAttribute("drugsLst",drugLst);
				}
				List<CommonDtlsVO> referalDtls=patientService.getDtrsReferealDtls(caseId,fromDisp);
				if(referalDtls.size()>0)
				{
					if(referalDtls != null)
						patientForm.setReferalDtls(referalDtls);
				}
			}
			List<CommonDtlsVO> commonDtls=patientService.getDtrsFormDtls(caseId,fromDisp);
			if(commonDtls!=null && commonDtls.size()>0)
			{
				commonDtlsVO=commonDtls.get(0);
			}
			if(commonDtlsVO!=null)
			{
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				patientForm.setScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getPatientScheme()!=null){
					patientForm.setPatientScheme(commonDtlsVO.getPatientScheme());
				}
				patientForm.setTelScheme(commonDtlsVO.getScheme());
				if(commonDtlsVO.getScheme()!=null)
				request.setAttribute("schemeId",commonDtlsVO.getScheme());
				patientForm.setPatientName(commonDtlsVO.getPATIENTNAME());
				patientForm.setCardNo(commonDtlsVO.getCARDNO());
				patientForm.setDistrict(commonDtlsVO.getDISTRICT());
				patientForm.setMandal(commonDtlsVO.getMANDAL());
				patientForm.setSlab(commonDtlsVO.getSlabType());
				patientForm.setVillage(commonDtlsVO.getVILLAGE());
				if(commonDtlsVO.getDate()!=null)
				{
					patientForm.setDtTime(sdfw.format(commonDtlsVO.getDate()));
				}
				patientForm.setAgeString(commonDtlsVO.getAGE());
				patientForm.setContactno(commonDtlsVO.getCONTACT());
				patientForm.setPatientNo(commonDtlsVO.getPATID());
				patientForm.setDateIp(commonDtlsVO.getPATDT());
				patientForm.setHospitalName(commonDtlsVO.getHOSPNAME());
				patientForm.setGender(commonDtlsVO.getGENDER());
				patientForm.setAddr(commonDtlsVO.getPATADDR()+", "+commonDtlsVO.getVILLAGE()+", "+commonDtlsVO.getMANDAL()+", "+commonDtlsVO.getDISTRICT());
				patientForm.setHospaddr(commonDtlsVO.getHOSPADDR());
				if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp)){
					if(commonDtlsVO.getDispContact()!=null)
					patientForm.setHospContactNo(commonDtlsVO.getDispContact());
				}
				PreauthVO patientData=patientService.getPatientOpDtls(caseId,commonDtlsVO.getPATID());
				if(patientData!=null)
				{
					patientForm.setPatTemp(patientData.getTemperature());
					patientForm.setPatPulse(patientData.getPulseRate());
					patientForm.setRespir(patientData.getRespirationRate());
					patientForm.setBloodPrLt(patientData.getBpLmt());
					patientForm.setBloodPrRt(patientData.getBpRmt());
					patientForm.setPastHist(patientData.getPastIllness());
					patientForm.setWeight(patientData.getWeight());
					patientForm.setHeight(patientData.getHeight());
					patientForm.setBmi(patientData.getBmi());
					patientForm.setFamHist(patientData.getFamilyHis());
					patientForm.setSymptomName(patientData.getSymName());
					patientForm.setComplaints(patientData.getPatComplaint());
					patientForm.setComplType(patientData.getComplaintType());
					patientForm.setPresentHistory(patientData.getPresentIllness());
					patientForm.setPastIllnessValue(patientData.getPastIllnessValue());
					patientForm.setFamilyHistValue(patientData.getFamilyHistValue());
					patientForm.setFamilyHistoryOthr(patientData.getFamilyHistoryOthr());
					patientForm.setPastHistryOthr(patientData.getPastIllenesOthr());
					patientForm.setRemarks(patientData.getRemarks());
					if(patientData.getNewBornBaby()!=null)
						patientForm.setNewBornBaby(patientData.getNewBornBaby());
					patientForm.setDrughistory(patientData.getDrugHstVal());
					patientForm.setMedicalHistVal(patientData.getMedHistVal());
					patientForm.setShowMedicalTextval(patientData.getMedicalHistoryOthr());
					patientForm.setRegionalLymphadenopathyDtrsTxt(patientData.getRegionalLymphadenopathyDtrsTxt());
					patientForm.setJawsDtrsTxt(patientData.getJawsDtrsTxt());
					patientForm.setRegionalLymphadenopathyDtrsSub(patientData.getRegionalLymphadenopathyDtrsSub());
					patientForm.setJawsDtrsSub(patientData.getJawsDtrsSub());
					patientForm.setTmjDtrsSub(patientData.getTmjDtrsSub());
					patientForm.setFaceDtrsSub(patientData.getFaceDtrsSub());
					List<LabelBean> intraoraldtls=null;
					String intraoral="HD2";
					intraoraldtls=patientService.getdentalexaminationDtls(intraoral);
					patientForm.setIntraoraldtls(intraoraldtls);
					request.setAttribute("intraoraldtls", intraoraldtls);
					patientForm.setDntsublistoral0(patientData.getDntsublistoral0());
					patientForm.setDntsublistoral1(patientData.getDntsublistoral1());
					patientForm.setDntsublistoral2(patientData.getDntsublistoral2());
					patientForm.setDntsublistoral3(patientData.getDntsublistoral3());
					patientForm.setDntsublistoral4(patientData.getDntsublistoral4());
					patientForm.setDntsublistoral5(patientData.getDntsublistoral5());
					patientForm.setDntsublistoral6(patientData.getDntsublistoral6());
					patientForm.setSwSite(patientData.getSwSite());
					patientForm.setSwSize(patientData.getSwSize());
					patientForm.setSwExtension(patientData.getSwExtension());
					patientForm.setSwColour(patientData.getSwColour());
					patientForm.setSwConsistency(patientData.getSwConsistency());
					patientForm.setSwTenderness(patientData.getSwTenderness());
					patientForm.setSwBorders(patientData.getSwBorders());
					patientForm.setPsSite(patientData.getPsSite());
					patientForm.setPsDischarge(patientData.getPsDischarge());
					List<LabelBean> occlusiondtls=null;
					List<LabelBean> deciduousdentdtls=null;
					List<LabelBean> permanentdentdtls=null;
					List<LabelBean> medicalhsitorydtls=null,treatmentDone=null,treatmentDoneSub=null;
					String occlusion="CH12";
					String deciduousdent ="CH13";
					occlusiondtls=patientService.getdentalexaminationDtls(occlusion);
					deciduousdentdtls=patientService.getdentalexaminationDtls(deciduousdent);
					if(dispDentalFlag!=null && "Y".equalsIgnoreCase(dispDentalFlag)){
						medicalhsitorydtls=patientService.getdispdentalexaminationDtls("DHD1");
						patientForm.setMedicalhsitorydtls(medicalhsitorydtls);
						treatmentDone=patientService.getdispdentalexaminationDtls("DHD2");
						patientForm.setDispDentalTreatDoneLst(treatmentDone);
						String treatDoneStr=null;
						for(int i=0;i<treatmentDone.size();i++){
							if(i<1){
								treatDoneStr=treatmentDone.get(i).getID();
							}
							else
								treatDoneStr=treatDoneStr+"','"+treatmentDone.get(i).getID();
						}
						treatmentDoneSub=patientService.getdispdentalexaminationDtls(treatDoneStr);
						patientForm.setDispDentalTreatDoneSubLst(treatmentDoneSub);
					}
					EhfPatient ehfPatient=null;
					ehfPatient=(EhfPatient)patientService.getPatientDetails(commonDtlsVO.getPATID());
					PreauthVO preauthDtlsVO = new PreauthVO();
					preauthDtlsVO = patientService.getPatientFullDtls(caseId, ehfPatient.getPatientId(),fromDisp);		
					if(preauthDtlsVO.getCarriesdecidous()!=null)
					{
						patientForm.setCarriesdecidous(preauthDtlsVO.getCarriesdecidous());
					}
					patientForm.setOcclusiondtls(occlusiondtls);
					patientForm.setDeciduousdentdtls(deciduousdentdtls);
					patientForm.setPermanentdentdtls(permanentdentdtls);
					request.setAttribute("occlusiondtls", occlusiondtls);
					request.setAttribute("deciduousdentdtls", preauthDtlsVO);
					patientForm.setPreviousDentalTreatment(patientData.getPreviousDentalTreatment());
					patientForm.setOcclusionTxt(patientData.getOcclusionTxt());
					patientForm.setOcclusionTypeTxt(patientData.getOcclusionTypeTxt());
					patientForm.setOcclusionOtherTxt(patientData.getOcclusionOtherTxt());
					if(dispDentalFlag!=null && "Y".equalsIgnoreCase(dispDentalFlag)){
					patientForm.setTextValDH1(patientData.getDiabetesRemarks());
					patientForm.setTextValDH2(patientData.getHypertensionRemarks());
					patientForm.setTextValDH3(patientData.getCardiacHistRemarks());
					patientForm.setTextValDH4(patientData.getDrugAllergyRemarks());
					patientForm.setTextValDH5(patientData.getThyroidRemarks());
					patientForm.setTextValDH6(patientData.getOralProphylaxisRemarks());
					patientForm.setTextValDH71(patientData.getCompositeRemarks());
					patientForm.setTextValDH72(patientData.getGicRemarks());
					patientForm.setTextValDH73(patientData.getPitFissureSealantsRemarks());
					patientForm.setTextValDH8(patientData.getExtractionRemarks());
					patientForm.setTextValDH9(patientData.getMedicationRemarks());
					}
				}
				CommonDtlsVO othrDtls=patientService.getOtherDtls(caseId, commonDtlsVO.getPATID(),fromDisp);
				patientForm.setAllergy(othrDtls.getAllergy());
				patientForm.setHabbits(othrDtls.getHabbits());
				patientForm.setInvestigationLt(othrDtls.getInvestigations());
				patientForm.setDoctorName(patientService.getEmpNameById(lStrUserId));
			}
			//pravalika
			String patientId = request.getParameter("patientId");
			//String cardNo = ehfPatient.getCardNo();
			//String cardNo=patientForm.getCardNo();
			caseId = patientForm.getCaseId();
			List<EhfPatientTests> investigationList = patientService.getInvestigationDetails(patientId);
			request.setAttribute("investigationList", investigationList);
			List<LabelBean> drugsDetails= patientService.getPrintDrugDtls(caseId);
			request.setAttribute("drugList1", drugsDetails);
			if((treatmentDntl!=null && "yes".equalsIgnoreCase(treatmentDntl)) || (dentalrntdbvalue!=null && "yes".equalsIgnoreCase(dentalrntdbvalue)))
			lStrPageName="dentaldtrsForm";
			else if(dispDentalFlag!=null && "Y".equalsIgnoreCase(dispDentalFlag))
				lStrPageName="dispDentalDtrsForm";
			else
			lStrPageName="dtrsForm";
		}
		if("getPharmaOPCases".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			List<LabelBean> distList= new ArrayList<LabelBean>();
			PatientVO retPatVO=null;
			List<PatientVO> registeredPatientsList=null;
			HashMap<String, String> searchMap=new HashMap<String, String>();
			int noOfPages=0;int totRowsCount=0;
			int lStrRowsperpage = 10;
			int lStrStartIndex = 0;
			int pageNo=0;
			String actionType=null;
			String pharmaScheme=config.getString("TG");
			String patientScheme=request.getParameter("patientScheme");
			String stateType=request.getParameter("stateType");
			request.setAttribute("patientScheme",patientScheme);
			request.setAttribute("pharmaScheme",pharmaScheme);
			String pharmaCases=request.getParameter("pharmaCases");
			String apprPharma=request.getParameter("apprPharma");
			request.setAttribute("pharmaCases",pharmaCases);
			request.setAttribute("apprPharma",apprPharma);
			searchMap.put("pharmaCases",pharmaCases);
			searchMap.put("apprPharma",apprPharma);
			searchMap.put("pharmaScheme",pharmaScheme);
			searchMap.put("patientScheme",patientScheme);
			searchMap.put("lStrStartIndex", lStrStartIndex+"");
			searchMap.put("lStrRowsperpage", lStrRowsperpage+"");
			searchMap.put("grpId",roleId);
			if(hospitalList.size()>0)
			{
				searchMap.put("userHospId",hospitalList.get(0).getID());
			}
			if(request.getParameter("pageNo")!=null && !request.getParameter("pageNo").equals("")){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(pageNo==0)
			{
				request.setAttribute("selectedPage",pageNo+1);
			}
			else
			{
				request.setAttribute("selectedPage",pageNo);
			}
			if(request.getParameter("actionType")!=null && !request.getParameter("actionType").equals("")){
				actionType=request.getParameter("actionType");
			}
			if(request.getParameter("advSearch")!=null && request.getParameter("advSearch").equalsIgnoreCase("true"))
			{
				String patientName="",patientNo="",caseNo="",cardNo="",district="",state="",fromDate="",toDate="",hospId="";
				if(actionType!=null && actionType.equalsIgnoreCase("link"))
				{
					if(!patientForm.getCurrCaseId().equals("")){
						caseNo=patientForm.getCurrCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getCurrPatId().equals("")){
						patientNo=patientForm.getCurrPatId();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCurrPatName().equals("")){
						patientName=patientForm.getCurrPatName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getCurrHealthCardNo().equals("")){
						cardNo=patientForm.getCurrHealthCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getCurrStateId().equals("-1") && !patientForm.getCurrStateId().equals("")){
						state=patientForm.getCurrStateId();
						searchMap.put("state",state);
					}
					if(!patientForm.getCurrDistrictId().equals("-1") && !patientForm.getCurrDistrictId().equals("")){
						district=patientForm.getCurrDistrictId();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getCurrFromDate().equals("")){
						fromDate=patientForm.getCurrFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getCurrToDate().equals("")){
						toDate=patientForm.getCurrToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getCurrHospId().equals("-1") && !patientForm.getCurrHospId().equals("")){
						hospId=patientForm.getCurrHospId();
						searchMap.put("userHospId",hospId);
					}
				}
				else if(actionType!=null && actionType.equalsIgnoreCase("button"))
				{
					if(!patientForm.getCaseId().equals(""))
					{
						caseNo=patientForm.getCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getPatientName().equals(""))
					{
						patientName=patientForm.getPatientName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getPatientNo().equals(""))
					{
						patientNo=patientForm.getPatientNo();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCardNo().equals(""))
					{
						cardNo=patientForm.getCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getState().equals("-1"))
					{
						state=patientForm.getState();
						searchMap.put("state",state);
					}
					if(!patientForm.getDistrict().equals("-1"))
					{
						district=patientForm.getDistrict();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getFromDate().equals(""))
					{
						fromDate=patientForm.getFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getToDate().equals(""))
					{
						toDate=patientForm.getToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getHospitalId().equals("-1"))
					{
						hospId=patientForm.getHospitalId();
						searchMap.put("userHospId",hospId);
					}
				}
			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				/*List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
				session.setAttribute("districtList",distList);*/
				if(!"GP1".equalsIgnoreCase(roleId) && !"GP2".equalsIgnoreCase(roleId))
				{
					session.setAttribute("hospList", commonService.getHospitals());
				}
			}
			if(pageNo>0)
			{
				lStrStartIndex = getStartIndex(lStrRowsperpage,pageNo);
				searchMap.put("lStrStartIndex", lStrStartIndex+"");
			}
			retPatVO=patientService.getOPCases(searchMap);
			registeredPatientsList=retPatVO.getRegisteredPatList();
			totRowsCount = retPatVO.getTotRowCount();
			noOfPages = getNoOfPages(totRowsCount, lStrRowsperpage);
			request.setAttribute("noOfPages", noOfPages);
			patientForm.setNoOfPages(noOfPages);
			if(registeredPatientsList.size()>0)
			{
				int endIndex=0;
				if(lStrStartIndex+lStrRowsperpage<= retPatVO.getTotRowCount())
				{
					endIndex=lStrStartIndex+lStrRowsperpage;
				}
				else
				{
					endIndex=retPatVO.getTotRowCount();
				}
				request.setAttribute("recordsList",lStrStartIndex+"-"+endIndex);
				request.setAttribute("totalRecords", retPatVO.getTotRowCount());
				request.setAttribute("registeredPatientsList", registeredPatientsList);
				request.setAttribute("searchListSize",retPatVO.getTotRowCount());
			}
			else if(retPatVO.getMsg()!=null && !retPatVO.getMsg().equalsIgnoreCase(""))
			{
				patientForm.setErrMsg(retPatVO.getMsg());
			}
			else
			{
				patientForm.setErrMsg(config.getString("err.NoRecordsFound"));
			}
			request.setAttribute("districtList",distList);
			lStrPageName="opPharmPatientsView";
		}
		if ("saveDTOdrugs".equalsIgnoreCase(lStrActionVal)) {
			String caseId = patientForm.getCaseId();
			if (caseId != null && !"".equalsIgnoreCase(caseId)) {
				patientVO = new PatientVO();
				patientVO.setCaseId(caseId);
				patientVO.setCrtUsr(lStrUserId);
				patientVO.setPatientId(patientForm.getPatientNo());
				//patientVO.setSpeclstDoctorName(patientForm.getSpecialistDoctorName());
				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				if (patientForm.getDrugs() != null
						&& !patientForm.getDrugs().equalsIgnoreCase("")) {
					String s = patientForm.getDrugs().substring(0,
							patientForm.getDrugs().length() - 1);
					String[] drugs = s.split("@");
					DrugsVO drugsVO = null;
					for (int z = 0; z < drugs.length; z++) {
						String drug = drugs[z];
						String[] drugValues = drug.split("~");
						drugsVO = new DrugsVO();
						if (!"".equalsIgnoreCase(fromDisp)
								&& "Y".equalsIgnoreCase(fromDisp)) {
							drugsVO.setDrugTypeCode(drugValues[0]);
							drugsVO.setDrugSubTypeCode(drugValues[1]);
							drugsVO.setDosage(drugValues[2]);
							drugsVO.setMedicationPeriod(drugValues[3]);
							drugsVO.setQuantity(drugValues[4]);
						}
						Long drugSeqNextVal = Long.parseLong(patientService
								.getSequence("OP_DRUG_ID"));
						drugsVO.setDrugId(drugSeqNextVal);
						drugsList.add(drugsVO);
					}
				}
				/*List<SpecialityVO> specList = new ArrayList<SpecialityVO>();
				if (patientForm.getSpecialityDoctors() != null
						&& !patientForm.getSpecialityDoctors().equalsIgnoreCase("")) {
					String s = patientForm.getSpecialityDoctors().substring(0,
							patientForm.getSpecialityDoctors().length() - 1);
					String[] doctors = s.split("@");
					SpecialityVO specVO = null;
					for (int z = 0; z < doctors.length; z++) {
						String doctor = doctors[z];
						String[] specialityValues = doctor.split("~");
						specVO = new SpecialityVO();
						  if (!"".equalsIgnoreCase(fromDisp) &&
						  "Y".equalsIgnoreCase(fromDisp)) {						 
								specVO.setSpecialistDoctorId(specialityValues[0]);
								specVO.setSpecialistDoctorName(specialityValues[1]);
						 } 
						specList.add(specVO);
					}
				}*/
				patientVO.setDrugs(drugsList);
				String patCaseId = patientService.saveDocDrugs(patientVO);
				/*String patCaseId1 = patientService.saveSpecialistDoctors(patientForm.getPatientNo() , specList);*/
				if (patCaseId != null && patCaseId.equalsIgnoreCase(caseId)) {
					patientForm.setMsg("Drugs Issued Successfully.");
				} else {
					patientForm.setMsg("Drugs Cannot be Issued");
				}
				request.setAttribute("fromDisp", "Y");
				request.setAttribute("deoFlg", "Y");
				request.setAttribute("dispPatID", patientForm.getPatientNo());
				lStrPageName = "patient";
			}
		}
		if("getOPCases".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> hospitalList=patientService.getHospital(lStrUserId,roleId);
			List<LabelBean> distList= new ArrayList<LabelBean>();
			PatientVO retPatVO=null;
			List<PatientVO> registeredPatientsList=null;
			HashMap<String, String> searchMap=new HashMap<String, String>();
			int noOfPages=0;int totRowsCount=0;
			int lStrRowsperpage = 10;
			int lStrStartIndex = 0;
			int pageNo=0;
			String actionType=null;
			String patientScheme=request.getParameter("patientScheme");
			String stateType=request.getParameter("stateType");
			request.setAttribute("patientScheme",patientScheme);
			searchMap.put("patientScheme",patientScheme);
			searchMap.put("lStrStartIndex", lStrStartIndex+"");
			searchMap.put("lStrRowsperpage", lStrRowsperpage+"");
			searchMap.put("grpId",roleId);
			if(hospitalList.size()>0)
			{
				searchMap.put("userHospId",hospitalList.get(0).getID());
			}
			if(request.getParameter("pageNo")!=null && !request.getParameter("pageNo").equals("")){
				pageNo=Integer.parseInt(request.getParameter("pageNo"));
			}
			if(pageNo==0)
			{
				request.setAttribute("selectedPage",pageNo+1);
			}
			else
			{
				request.setAttribute("selectedPage",pageNo);
			}
			if(request.getParameter("actionType")!=null && !request.getParameter("actionType").equals("")){
				actionType=request.getParameter("actionType");
			}
			if(request.getParameter("advSearch")!=null && request.getParameter("advSearch").equalsIgnoreCase("true"))
			{
				String patientName="",patientNo="",caseNo="",cardNo="",district="",state="",fromDate="",toDate="",hospId="";
				if(actionType!=null && actionType.equalsIgnoreCase("link"))
				{
					if(!patientForm.getCurrCaseId().equals("")){
						caseNo=patientForm.getCurrCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getCurrPatId().equals("")){
						patientNo=patientForm.getCurrPatId();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCurrPatName().equals("")){
						patientName=patientForm.getCurrPatName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getCurrHealthCardNo().equals("")){
						cardNo=patientForm.getCurrHealthCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getCurrStateId().equals("-1") && !patientForm.getCurrStateId().equals("")){
						state=patientForm.getCurrStateId();
						searchMap.put("state",state);
					}
					if(!patientForm.getCurrDistrictId().equals("-1") && !patientForm.getCurrDistrictId().equals("")){
						district=patientForm.getCurrDistrictId();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getCurrFromDate().equals("")){
						fromDate=patientForm.getCurrFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getCurrToDate().equals("")){
						toDate=patientForm.getCurrToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getCurrHospId().equals("-1") && !patientForm.getCurrHospId().equals("")){
						hospId=patientForm.getCurrHospId();
						searchMap.put("userHospId",hospId);
					}
				}
				else if(actionType!=null && actionType.equalsIgnoreCase("button"))
				{
					if(!patientForm.getCaseId().equals(""))
					{
						caseNo=patientForm.getCaseId();
						searchMap.put("caseNo",caseNo);
					}
					if(!patientForm.getPatientName().equals(""))
					{
						patientName=patientForm.getPatientName();
						searchMap.put("patientName",patientName);
					}
					if(!patientForm.getPatientNo().equals(""))
					{
						patientNo=patientForm.getPatientNo();
						searchMap.put("patientNo",patientNo);
					}
					if(!patientForm.getCardNo().equals(""))
					{
						cardNo=patientForm.getCardNo();
						searchMap.put("cardNo",cardNo);
					}
					if(!patientForm.getState().equals("-1"))
					{
						state=patientForm.getState();
						searchMap.put("state",state);
					}
					if(!patientForm.getDistrict().equals("-1"))
					{
						district=patientForm.getDistrict();
						searchMap.put("district",district);
						distList=commonService.getLocationsNew(distHdrId,state,stateType);
					}
					if(!patientForm.getFromDate().equals(""))
					{
						fromDate=patientForm.getFromDate();
						searchMap.put("fromDate",fromDate);
					}
					if(!patientForm.getToDate().equals(""))
					{
						toDate=patientForm.getToDate();
						searchMap.put("toDate",toDate);
					}
					if(!patientForm.getHospitalId().equals("-1"))
					{
						hospId=patientForm.getHospitalId();
						searchMap.put("userHospId",hospId);
					}
				}
			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
				/*List<LabelBean> distList=commonService.getAsrimLocations(distHdrId,distParntId);
				session.setAttribute("districtList",distList);*/
				if(!"GP1".equalsIgnoreCase(roleId) && !"GP2".equalsIgnoreCase(roleId))
				{
					session.setAttribute("hospList", commonService.getHospitals());
				}
			}
			if(pageNo>0)
			{
				lStrStartIndex = getStartIndex(lStrRowsperpage,pageNo);
				searchMap.put("lStrStartIndex", lStrStartIndex+"");
			}
			retPatVO=patientService.getOPCases(searchMap);
			registeredPatientsList=retPatVO.getRegisteredPatList();
			totRowsCount = retPatVO.getTotRowCount();
			noOfPages = getNoOfPages(totRowsCount, lStrRowsperpage);
			request.setAttribute("noOfPages", noOfPages);
			patientForm.setNoOfPages(noOfPages);
			if(registeredPatientsList.size()>0)
			{
				int endIndex=0;
				if(lStrStartIndex+lStrRowsperpage<= retPatVO.getTotRowCount())
				{
					endIndex=lStrStartIndex+lStrRowsperpage;
				}
				else
				{
					endIndex=retPatVO.getTotRowCount();
				}
				request.setAttribute("recordsList",lStrStartIndex+"-"+endIndex);
				request.setAttribute("totalRecords", retPatVO.getTotRowCount());
				request.setAttribute("registeredPatientsList", registeredPatientsList);
				request.setAttribute("searchListSize",retPatVO.getTotRowCount());
			}
			else if(retPatVO.getMsg()!=null && !retPatVO.getMsg().equalsIgnoreCase(""))
			{
				patientForm.setErrMsg(retPatVO.getMsg());
			}
			else
			{
				patientForm.setErrMsg(config.getString("err.NoRecordsFound"));
			}
			request.setAttribute("districtList",distList);
			lStrPageName="opRegPatientsView";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "viewAttachment" ) )
	      {
			String lStrFilePath = null;
      	   	String lStrContentType = request.getContentType();
	          lStrContentType = (lStrContentType==null)?FileConstants.EMPTY_STRING:lStrContentType;
/*      	   if(docSeqId != null && !docSeqId.equalsIgnoreCase(""))
      	   {
      		   lStrFilePath = attachmentService.getIvestPath(docSeqId);  
      	   }
      	   else
      		   lStrFilePath = request.getParameter("filePath");*/ 
	          lStrFilePath = request.getParameter("filePath");
      	    File file = null ;
		        FileInputStream fis1 = null ;
		        DataInputStream dis1 = null ;
		        String lStrType = null;
		        ServletOutputStream out = response.getOutputStream();
	          String fileExt = lStrFilePath.substring((lStrFilePath.lastIndexOf(".")+1));
	          String lStrFileName=lStrFilePath.substring((lStrFilePath.lastIndexOf("/")+1));
	          String attachMode="attachment";
	          if(fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPG) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPEG) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_GIF)){
	              attachMode="inline";
	          }
	           lStrContentType=FileConstants.FILE_EXT.get(fileExt);
	           String dir =  lStrFilePath  ;
	          byte[] lbBytes = null ;
	          try
	          {
	            if ( lStrFileName != null && !lStrFileName.equals ( "" ) )
	            {
	              file = new File ( dir ) ; 
	              fis1 = new FileInputStream ( file ) ;
	              dis1 = new DataInputStream ( fis1 ) ;
	              lbBytes = new byte[ dis1.available () ] ;
	              fis1.read ( lbBytes ) ;
	              request.setAttribute ( "File", lbBytes ) ;
	              request.setAttribute ( "ContentType", lStrContentType ) ;
	              request.setAttribute ( "FileName", lStrFileName ) ;
	              request.setAttribute ( "Extn", lStrType ) ;
	              response.setContentType ( lStrContentType ) ;
	              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);//006
	              out.write(lbBytes);
	              //out.close();			             
	            }
	            else
	            {
	              lbBytes = new byte[ 0 ] ;
	            }
	          }
	          catch ( Exception e )
	          {
	            e.getMessage () ;
	            e.printStackTrace();
	          }
	          finally
	          {
	        	  out.close();
	          }
	      }
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "chechInvest" ) )
		{
			String patientId=request.getParameter("patientId");
			String investValue=request.getParameter("investValue");
			//EhfmOpDrugMst ehfmOpDrugMst=new EhfmOpDrugMst();
			String result=patientService.getinvestcheck(patientId,investValue);
			request.setAttribute("AjaxMessage",result);
			return mapping.findForward("ajaxResult");
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getdiagDtlsAuto" ) )
		{
			String disAnatomicalCode=request.getParameter("disAnatomicalCode");
			Gson jsonData=new Gson();
			LabelBean diagDtls=new LabelBean();
			if(disAnatomicalCode!=null && !("").equalsIgnoreCase(disAnatomicalCode))
			{
			diagDtls=patientService.getDiagnosisDtlsAuto(disAnatomicalCode);
			}
			request.setAttribute("AjaxMessage",jsonData.toJson(diagDtls));
			return mapping.findForward("ajaxResult");
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getdiagListAuto" ) )
		{
			String diagName=request.getParameter("diagName");
			String diagType=request.getParameter("diagType");
			Gson jsonData=new Gson();
			List<LabelBean> diagDtls=new ArrayList<LabelBean>();
			if(diagName!=null && !("").equalsIgnoreCase(diagName))
			{
			diagDtls=patientService.getDiagListSearch(diagName,diagType);
			}
			request.setAttribute("AjaxMessage",jsonData.toJson(diagDtls));
			return mapping.findForward("ajaxResult");
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "gettokenNoAjax" ) )
		{
			String specialityType=request.getParameter("specialityTypeVal");
			String lStrHospId=request.getParameter("hospId");
			String roomNo=request.getParameter("roomNo");
			String tokenNo="";
			try{
				tokenNo=patientService.getTokenNo(lStrHospId,onlyDate,specialityType,roomNo);
				}
				catch(Exception e)
				{
//					GLOGGER.error ( "Exception occurred using gettokenNoAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
				request.setAttribute("AjaxMessage", tokenNo);
			lStrPageName="ajaxResult";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getRoomNoAjax" ) )
		{
			String specialityType=request.getParameter("specialityTypeVal");
			String lStrHospId=request.getParameter("hospId");
			List<LabelBean> roomNos=null;
			List<String> roomNumbers=new ArrayList<String>();
			try{
				//tokenNo=patientService.getTokenNo(lStrHospId,onlyDate,specialityType,roomNo);
				roomNos=patientService.getRoomNos(lStrHospId,specialityType);
				}
				catch(Exception e)
				{
//					GLOGGER.error ( "Exception occurred using gettokenNoAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
			for (LabelBean labelBean: roomNos) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
				{
					roomNumbers.add(labelBean.getID() + "~" + 
							labelBean.getVALUE());
				}
			}
			GLOGGER.info ( "Retrieving Doctor Details using Ajax Call in PatientAction." ) ;
			//doctorsList.add("0~Others");
		 if(roomNumbers!=null && roomNumbers.size() > 0)
			request.setAttribute("AjaxMessage", roomNumbers);
			lStrPageName="ajaxResult";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "showTokenNumbers" ) )
		{
			String hospId=null;
			List<LabelBean> roomTokens=null;
			try{
				//hospIds=patientService.getReferredCenterDtls(lStrUserId, roleId);
				hospId=request.getParameter("hospId");
				patientForm.setHospitalName("XXX");
				roomTokens=patientService.getAllRoomTokenNo(hospId);
				request.setAttribute("roomTokens", roomTokens);
				lStrPageName="showTokenNos";
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
				lStrPageName="showTokenNos";
		}
		if ("getLabReport".equalsIgnoreCase(lStrActionVal))
		{
			String reportType = request.getParameter("reportType");
			String investgtnId = request.getParameter("investgtnId");
			String patientId = request.getParameter("patientId");
			String editFlg = request.getParameter("editFlg");
			String combinedReport= request.getParameter("combinedReport");
			patientForm.setInvestigationSel(investgtnId);
			HashMap<String,Object> lMap = new HashMap<String,Object>();
			String tableName = "EhfmOprtnsReportsLabels";
			lMap.put("reportType", reportType);
			lMap.put("tableName", tableName);
			lMap.put("investgtnId",investgtnId);
			lMap.put("patientId",patientId);
//			if(combinedReport!=null && !combinedReport.equalsIgnoreCase(""))
			lMap.put("combinedReport",combinedReport);
			List<LabelBean>  investList =  patientService.getPatInvestList(patientId);
			patientForm.setInvestList(investList);
			List<LabelVO>  fieldsList =  patientService.retrieveLabelsData(lMap);
			patientForm.setLabelsList(fieldsList);
			 if(fieldsList.size() > 0)
			 {
				 patientForm.setInvestigations(fieldsList.get(0).getREPORTNAME());
			 }
			boolean rslt = patientService.checkLabReportsubmitted(lMap);
			request.setAttribute("isSubmitted", rslt);
			if(rslt || (combinedReport!=null && combinedReport.equalsIgnoreCase("Y")))
			{
				 EhfPatient EhfPatientObj = patientService.getPatientDetails(patientId);
				 patientForm.setPatientNo(EhfPatientObj.getPatientId());
				 patientForm.setPatientName(EhfPatientObj.getName());
				 patientForm.setEmployeeNo(EhfPatientObj.getEmployeeNo());
				 patientForm.setCardNo(EhfPatientObj.getCardNo());
				 String hospCode=EhfPatientObj.getDispCode();
				 patientForm.setHospitalName(patientService.getDispName(hospCode));
				 if(EhfPatientObj.getGender()!=null && "M".equalsIgnoreCase(EhfPatientObj.getGender()))
				{
						patientForm.setGender("Male");
				}
				else if(EhfPatientObj.getGender()!=null && "F".equalsIgnoreCase(EhfPatientObj.getGender()))
				{
						patientForm.setGender("Female");
				}
				if(EhfPatientObj.getEnrollDob()!=null)
				{
						patientForm.setDateOfBirth(sdf.format(EhfPatientObj.getEnrollDob()));
				}
				if(EhfPatientObj.getAge()!=null)
				{
						patientForm.setYears(EhfPatientObj.getAge().toString());
				}
				if(EhfPatientObj.getAgeMonths()!=null)
				{
						patientForm.setMonths(EhfPatientObj.getAgeMonths().toString());
				}
				if(EhfPatientObj.getAgeDays()!=null)
				{
						patientForm.setDays(EhfPatientObj.getAgeDays().toString());
				}
				String relationId=EhfPatientObj.getRelation();
				String relationName=patientService.getRelationName(relationId);
				patientForm.setRelation(relationName);
				String distCode=EhfPatientObj.getDistrictCode();
				String distName=patientService.getLocationName(distCode);
				patientForm.setDistrict(distName);
				patientForm.setDtTime(sdfw.format(EhfPatientObj.getRegHospDate()));
				if(EhfPatientObj.getContactNo()!=null && !EhfPatientObj.getContactNo().equals(""))
				{
					patientForm.setContactno(EhfPatientObj.getContactNo().toString());
				}
				if(editFlg!=null && editFlg.equalsIgnoreCase("Y"))
					lStrPageName = "labReportsPage";
				else{
					if(combinedReport!=null && combinedReport.equalsIgnoreCase("Y"))
						lStrPageName="viewAllLabReports";
				else
				lStrPageName="viewLabReports";
				}
			}
			else					
				lStrPageName = "labReportsPage";
		}
		if ("saveLabReport".equalsIgnoreCase(lStrActionVal))
		{
			 String patientId = request.getParameter("patientId");
			 String investgtnId = request.getParameter("investgtnId");
			 String btnType = request.getParameter("btnType");
			 List<LabelVO> dataList = patientForm.getLabelsList();
			 HashMap<String,Object> lMap = new HashMap<String,Object>();
			 lMap.put("patientId",patientId);
			 lMap.put("investgtnId",investgtnId);
			 lMap.put("crtUsr",lStrUserId);
			 lMap.put("dataList",dataList);
			 lMap.put("btnType",btnType);			 
			 boolean bRslt =  patientService.saveLabReportData(lMap);
			 List<LabelVO>  fieldsList =  patientService.retrieveLabelsData(lMap);
			 patientForm.setLabelsList(fieldsList);
			 patientForm.setInvestigationSel(investgtnId);
			 request.setAttribute("isSubmitted", "false");
			 if(fieldsList.size() > 0)
			 {
				 patientForm.setInvestigations(fieldsList.get(0).getREPORTNAME());
			 }
			 if("submit".equalsIgnoreCase(btnType))
			 {
				 	 request.setAttribute("isSubmitted", "true");
					 EhfPatient EhfPatientObj = patientService.getPatientDetails(patientId);
					 patientForm.setPatientNo(EhfPatientObj.getPatientId());
					 patientForm.setPatientName(EhfPatientObj.getName());
					 patientForm.setEmployeeNo(EhfPatientObj.getEmployeeNo());
					 patientForm.setCardNo(EhfPatientObj.getCardNo());
					 String hospCode=EhfPatientObj.getDispCode();
					 patientForm.setHospitalName(patientService.getDispName(hospCode));
					 if(EhfPatientObj.getGender()!=null && "M".equalsIgnoreCase(EhfPatientObj.getGender()))
					{
							patientForm.setGender("Male");
					}
					else if(EhfPatientObj.getGender()!=null && "F".equalsIgnoreCase(EhfPatientObj.getGender()))
					{
							patientForm.setGender("Female");
					}
					if(EhfPatientObj.getEnrollDob()!=null)
					{
							patientForm.setDateOfBirth(sdf.format(EhfPatientObj.getEnrollDob()));
					}
					if(EhfPatientObj.getAge()!=null)
					{
							patientForm.setYears(EhfPatientObj.getAge().toString());
					}
					if(EhfPatientObj.getAgeMonths()!=null)
					{
							patientForm.setMonths(EhfPatientObj.getAgeMonths().toString());
					}
					if(EhfPatientObj.getAgeDays()!=null)
					{
							patientForm.setDays(EhfPatientObj.getAgeDays().toString());
					}
					String relationId=EhfPatientObj.getRelation();
					String relationName=patientService.getRelationName(relationId);
					patientForm.setRelation(relationName);
					String distCode=EhfPatientObj.getDistrictCode();
					String distName=patientService.getLocationName(distCode);
					patientForm.setDistrict(distName);
					patientForm.setDtTime(sdfw.format(EhfPatientObj.getRegHospDate()));
					if(EhfPatientObj.getContactNo()!=null && !EhfPatientObj.getContactNo().equals(""))
					{
						patientForm.setContactno(EhfPatientObj.getContactNo().toString());
					}
					boolean labRslt = patientService.checkAllSubmit(lMap);
					if(labRslt){
						patientService.generateSendInvReports(patientId);
						if("true".equalsIgnoreCase(config.getString("SmsRequired")))
						{
							if(!"".equalsIgnoreCase(EhfPatientObj.getContactNo().toString()) &&  EhfPatientObj.getContactNo()!=null)
							{
								//SendSMS sendSms =new SendSMS();
								SMSServices SMSServicesobj = new SMSServices();
//								String msg= "Dear "+EhfPatientObj.getName().trim()+" , Your Investigation reports have been generated. Please visit http://wc.ehf.telangana.gov.in/patientInvestigations.htm?patientId="+patientId;
								String msg= "Dear "+EhfPatientObj.getName().trim()+" , Your Investigation reports have been generated and mailed to your registered email address. ";
								 //sendSms.sendSMS(msg,EhfPatientObj.getContactNo().toString());
								 SMSServicesobj.sendSingleSMS(msg,EhfPatientObj.getContactNo().toString(),null);
							}
						 }
					}
				 return mapping.findForward("viewLabReports");	
			 }
			 else
			 {
				 request.setAttribute("msg", bRslt);
				 lStrPageName = "labReportsPage";
			 }
		}
		if ("getDrugQuant".equalsIgnoreCase(lStrActionVal)){
			String drugId = request.getParameter("drugId");
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			LabelBean drugBalParam=new LabelBean();
			drugBalParam.setDrugId(drugId);
			drugBalParam.setHOSPID(dispId);
			String DrugQuant="";
			try{
				DrugQuant=patientService.getDrugQuant(drugBalParam);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				request.setAttribute("AjaxMessage", DrugQuant);
			lStrPageName="ajaxResult";
		}
		if ("saveInventoryDrugDetails".equalsIgnoreCase(lStrActionVal))
		{
			String drugName = request.getParameter("drugName");
			String dispDrugID = request.getParameter("dispDrugID");
			if(dispDrugID != null && !"-1".equalsIgnoreCase(dispDrugID)){
				drugName = patientService.getDrugName(dispDrugID);
			}
			List<LabelBean> detList=new ArrayList<LabelBean>();
			String pharmacistId=userIDu;
			String drugList=request.getParameter("drugList");
		    if(drugName.equalsIgnoreCase("Others"))
		    {
		    	LabelBean drugDetails=new LabelBean();
		    	String drugId=patientForm.getDispDrugID();
				String drugQty=patientForm.getDispDrugQuantity();
				String invoiceNumber=patientForm.getInvoiceNumber();
				String expiryDateString = patientForm.getDrugexpiryDate();
				String invoiceDateNew = patientForm.getInvoiceDate();
			    DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
			    Date expiryDate;
			        expiryDate = df.parse(expiryDateString);
			        Date invDate;
			        invDate = df.parse(invoiceDateNew);
			    String batchNo=patientForm.getDrugBatchNo(); 
			    String drugType=patientForm.getDrugType();
			    long quantity=Long.parseLong(drugQty);
			    drugDetails.setUSERID(pharmacistId);
			    drugDetails.setDrugId(drugId);
			    drugDetails.setQuantity(quantity);
			    drugDetails.setINVOICENUMBER(invoiceNumber);
			    drugDetails.setExpiryDate(expiryDate);
			    drugDetails.setInvDate(invDate);
			    drugDetails.setBatchNo(batchNo);
			    drugDetails.setDrugType(drugType);
			    drugDetails.setDrugName(drugName);
		    	drugDetails.setNewDrugName(patientForm.getDispDrugName());
		        drugDetails.setDRUGAMOUNT(patientForm.getDispDrugPrice());
		    	drugDetails.setManufaturerName(patientForm.getManufacturerName());
				drugDetails.setDistributerName(patientForm.getDistributerName());
				detList.add(drugDetails);
		    }
		    else
		    {
		    	String[] arr=drugList.split("@");
		    	for(int i=0;i<arr.length;i++)
		    	{
		    		String[] drugDtls=arr[i].split("~");
		    		String drugCode=drugDtls[0];
		    		String quant=drugDtls[1];
		    		String invoiceNumber=drugDtls[2];
		    		String invoiceDate=drugDtls[3];
		    		   DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
		    		  Date invDate;
				        invDate = df.parse(invoiceDate);
		    		long quantity=Long.parseLong(quant);
		    		//String batchNo=drugDtls[2];
		    		LabelBean drugDetails=new LabelBean();
		    		drugDetails.setDrugCode(drugCode);
		    		drugDetails.setQuantity(quantity);
		    		//drugDetails.setBatchNo(batchNo);
		    		drugDetails.setUSERID(pharmacistId);
		    		drugDetails.setDrugName(drugName);
		    		drugDetails.setINVOICENUMBER(invoiceNumber);
		    		drugDetails.setInvDate(invDate);
		    		detList.add(drugDetails);
		    	}
		    }
		    boolean flag=false;
		    flag=patientService.updateDrugsInventory(detList);
		    if(flag==true)
		    {
		    	request.setAttribute("status", "Yes");
		    }
		    lStrActionVal="viewDrugInventoryForm";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getDrugListAjax" ) )
		{
			String drugType=request.getParameter("drugType");
			List<LabelBean> drugsList=null;
			List<String> drugs=new ArrayList<String>();
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			try{
				drugsList=patientService.getDispDrugList(drugType,dispId);
				}
				catch(Exception e)
				{
//					GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
			LabelBean temp=new LabelBean();
			for (LabelBean labelBean: drugsList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
				{
					if( labelBean.getVALUE().equalsIgnoreCase("Others"))
					{
						temp=labelBean;
					}
					else
					{
						drugs.add(labelBean.getID() + "~" + 
								labelBean.getVALUE());
					}
				}
			}
			GLOGGER.info ( "Retrieving Drugs List using Ajax Call in PatientAction." ) ;
			//doctorsList.add("0~Others");
		 if(drugs!=null && drugs.size() > 0)
			request.setAttribute("AjaxMessage", drugs);
			lStrPageName="ajaxResult";
		}
			/*if ( lStrActionVal!=null && lStrActionVal.equalsIgnoreCase ("leaveResultList"))
		{
			fromDisp = request.getParameter("fromDispnsry");
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
					List<LabelBean> doctorNames = null; 
					 doctorNames=commonService.getDoctorNames(lStrUserId,dispId);
					request.setAttribute("dispDoctorNames", doctorNames);
					lStrPageName="leaveEmp";
		}*/
	   			if ( lStrActionVal!=null && lStrActionVal.equalsIgnoreCase ("getLeaveDates"))
		{
			SimpleDateFormat sdf1 = new SimpleDateFormat("M-d-yyyy");
			String docId=request.getParameter("x");
			String result="";
					List<LabelBean> doctorDates = null; 
					 doctorDates=commonService.getDoctorDates(docId);
					 List<Date> Dates = new ArrayList<Date>();
					 if(doctorDates!=null&&doctorDates.size()>0){
						 for(int i=0;i<doctorDates.size();i++){
							 if(doctorDates.get(i).getDOB()!=null && doctorDates.get(i).getDOS()!=null){
								 if(!doctorDates.get(i).getDOB().equals(doctorDates.get(i).getDOS())){
									 Dates.addAll(getDatesBetweenUsingJava7(doctorDates.get(i).getDOB(),doctorDates.get(i).getDOS()));
									 Dates.add(doctorDates.get(i).getDOB());
									 Dates.add(doctorDates.get(i).getDOS());
								 }
								 else
									 Dates.add(doctorDates.get(i).getDOB());
							 }
						 }
					 }
					 if(Dates!=null && Dates.size()>0){
						 for(Date d:Dates){
							 result += sdf1.format(d.getTime())+",";
						 }
					 }
					request.setAttribute("AjaxMessage", result);
					lStrPageName="ajaxResult";
		}
		if ( lStrActionVal!=null && lStrActionVal.equalsIgnoreCase ("saveLeaveDetails"))
		{
			String msg = "failure";
			PatientVO patientVo=new PatientVO();
			PatientVO patientVo1=new PatientVO();
			String docId=request.getParameter("z");
			String startDate=request.getParameter("x");
			String toDate=request.getParameter("y");
			String reason=patientForm.getReason();
			patientVo.setDocRegNo(docId);
			patientVo.setReason(reason);
			patientVo.setLeaveFromdate(startDate);
			patientVo.setLeaveToDate(toDate);
			patientVo.setLstUpdUsr(lStrUserId);
			patientVo1=commonService.getEmployeeDtls(patientVo);
			patientVo.setLeaveConsultMob(patientVo1.getLeaveConsultMob());
			patientVo.setLeaveConsultName(patientVo1.getLeaveConsultName());
			patientVo.setLeaveWellName(patientVo1.getLeaveWellName());
			patientVo.setLeaveGrpId(patientVo1.getLeaveGrpId());
			Long leaveSeqVal=0L;
			leaveSeqVal = Long.parseLong(patientService.getLeaveSequence("WELLNESS_CENTER_SN_ID"));
			//String strSeqVal= leaveSeqVal.toString();
			patientVo.setLeaveId(leaveSeqVal.toString());
			//leaveSeqVal=leaveSeqVal+1;
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			patientVo.setDispCode(dispId);
			commonService.saveEmployeeDtls(patientVo);
			List<LabelBean> doctorNames = null; 
			 doctorNames=commonService.getDoctorNames(lStrUserId,dispId);
			request.setAttribute("dispDoctorNames", doctorNames);
			List<LabelBean> mobileNo = null; 
			mobileNo=commonService.getMobileNum(docId,startDate,toDate);
			//request.setAttribute("dispDoctorNames", doctorNames);
			if("true".equalsIgnoreCase(config.getString("SmsRequired")))
		     {
					//SendSMS sendSms =new SendSMS();
				SMSServices SMSServicesobj = new SMSServices();
					if(mobileNo != null)
					{
						//String[] cntctNo =  mobileNo.split("~");;
						for(int i=0; i<mobileNo.size();i++)
						{
							LabelBean mobNo=mobileNo.get(i);
							if(mobNo!=null && mobNo.getID()!=null && !"".equalsIgnoreCase(mobNo.getID()))
							{
								SimpleDateFormat sdfapp=new SimpleDateFormat("dd-MM-yyyy");
								String leaveDate=sdfapp.format(mobNo.getDOS());
								//msg= "Your Doctor appointment has been cancelled.We regret for the inconvenience caused " ;
								msg= "Your Doctor appointment on "+leaveDate+" at "+mobNo.getWELLNESSCENTER()+" has been cancelled as the doctor has appliead leave.We regret for the inconvenience caused " ;
								 SMSServicesobj.sendSingleSMS(msg,mobNo.getID(),null);
								 commonService.saveEmployeeDtls(docId,mobNo.getDOS(),mobNo.getID());
							}
						}
					}
		     }
			patientForm.setMsg("Leave details Saved successfully");
request.setAttribute("leaveMsg", patientForm.getMsg());
					lStrPageName="leaveEmp";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getDrugDetailsAjax" ) )
		{
			String drugType=request.getParameter("drugType");
			String drugId=request.getParameter("drugId");
			String userId=userIDu;
			List<LabelBean> details=null;
			List<String> drugs=new ArrayList<String>();
			try{
				details=patientService.getDispDrugDetails(drugType,drugId,userId,"");
				}
				catch(Exception e)
				{
//					GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
			for (LabelBean labelBean: details) {
				if (labelBean.getID() != null )
				{
					drugs.add(labelBean.getID() + "~" + labelBean.getManufaturerName()+"~"+labelBean.getDistributerName()+
							"~"+labelBean.getDrugPrice()+"~"+labelBean.getQuantity()+"~"+sdf.format(labelBean.getExpiryDate())+"~"+labelBean.getBatchNo());
				}
			}
			request.setAttribute("AjaxMessage", drugs);
			lStrPageName="ajaxResult";
		}
		if("getDispDrugMfg".equalsIgnoreCase(lStrActionVal))
		{
			List<LabelBean> mfgList=null;
			List<String> mfgArr=new ArrayList<String>();
			String drugId=request.getParameter("drugId");
			commonService.getloggedUserDispId(lStrUserId,schemeId);
			try{
				mfgList=patientService.getDispDrugMfg(drugId);
				}
				catch(Exception e)
				{
//					GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
			for (LabelBean labelBean: mfgList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
				{
					mfgArr.add(labelBean.getID() + "~" +labelBean.getVALUE());
				}
			}
//			if (mfgList != null && mfgList.size() > 0)
//				request.setAttribute("mfgList", mfgList);
			request.setAttribute("AjaxMessage", mfgArr);
			lStrPageName="ajaxResult";
		}
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "addNewMnfNDst" ) )
		{
			try{
				String drugType=patientForm.getDrugType();
				String drugId=patientForm.getDispDrugID();
				String mftrName=patientForm.getMftrName();
				String invDate=patientForm.getInvoiceDateMn();
				 DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
				 Date invDateNew;
			        invDateNew = df.parse(invDate);
				String dbtr=patientForm.getDstrName();
				String price=patientForm.getDrugPrice();
				String drugQuantity=patientForm.getQuantity();
				String batchNo=patientForm.getBatchNo();
				String pharmacistId=userIDu;
				String expiryDt=patientForm.getExpiryDt();
				String invoiceNumber=patientForm.getInvoiceNumberNew();
				LabelBean lb=new LabelBean();
				lb.setUSERID(pharmacistId);
				lb.setDrugType(drugType);
				lb.setDrugId(drugId);
				lb.setInvDate(invDateNew);
				lb.setManufaturerName(mftrName);
				lb.setDistributerName(dbtr);
				double drugPrice=Double.parseDouble(price);
				lb.setDrugPrice(drugPrice);
				long quant=Long.parseLong(drugQuantity);
				lb.setQuantity(quant);
				lb.setINVOICENUMBER(invoiceNumber);
				    Date expiryDate;
				        expiryDate = df.parse(expiryDt);
				  lb.setExpiryDate(expiryDate);
				  lb.setBatchNo(batchNo);
				  boolean flag=patientService.addNewMnfNDist(lb);
				  if(flag==true)
				    	request.setAttribute("status", "Yes");
				  lStrActionVal="viewDrugInventoryForm";
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		if ("deleteDrugSet".equalsIgnoreCase(lStrActionVal))
		{
			try{
			String drugCode=request.getParameter("drugCode");
			boolean delDrugset=patientService.deleteDrugSet(drugCode);
			if(delDrugset==true)
		    	request.setAttribute("status", "Success");
			}
			catch(Exception e){
				e.printStackTrace();
			}
			lStrActionVal="viewDrugBalance";
		}
		if ("viewDrugBalance".equalsIgnoreCase(lStrActionVal)){
			String drugId=patientForm.getDispDrugID();
			String drugType=patientForm.getDrugType();
			String searchType=patientForm.getSearchType();
			if(searchType == null)
				searchType="Avbl";
			String lStrContentType="application/vnd.ms-excel";//Chanadna - 8251 - Added this for content file excel file
			String csvFlag = request.getParameter("csvFlag");
			 List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
				request.setAttribute("dispDrugTypeList", drugsTypeList);
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");//Chandana - 8251 - Added this to get the month and year from the current date
    		String monthYr = dateFormat.format(new Date());
			String flag = commonService.getFlagMonthBased(dispId, monthYr);//Chandana - 8251 - Added this to get the flag for monthly wise
			request.setAttribute("flag", flag);
			LabelBean drugBalParam=new LabelBean();
			drugBalParam.setDrugId(drugId);
			drugBalParam.setDrugType(drugType);
			drugBalParam.setHOSPID(dispId);
			drugBalParam.setSEARCHTYPE(searchType);
			List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispId);
			request.setAttribute("dispDrugsList", drugsList);
			List<DrugsVO> drugsBal=patientService.getDispInventDrugBal(drugBalParam);
			request.setAttribute("InventDrugsQuant", drugsBal);
			request.setAttribute("searchType", searchType);
			patientForm.setDrugLt(drugsBal);
			patientForm.setSearchType(searchType);
			if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
			{
				String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE");
					lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");
		        int sNo=1;
				if(drugsBal.size()>0)
					{
					//Chandana - 8251 - Removed existing line code as we are not going to use .csv file, we are going to use excel
					HSSFWorkbook workbook = new HSSFWorkbook();
			        Sheet sheet = workbook.createSheet("CasesData");
			        CellStyle lockedStyle = workbook.createCellStyle();
			        lockedStyle.setLocked(true);
			        CellStyle unlockedStyle = workbook.createCellStyle();
			        unlockedStyle.setLocked(false);
			        int rowIdx = 0;
			        int colIdx = 0;
			        Row headerRow = sheet.createRow(rowIdx++);
			        headerRow.createCell(colIdx++).setCellValue("S_No");
			        headerRow.createCell(colIdx++).setCellValue("Drug_Type");
			        headerRow.createCell(colIdx++).setCellValue("Drug_Name");
			        if ("Avblbatch".equalsIgnoreCase(searchType) || "Allbatch".equalsIgnoreCase(searchType)) {
			            headerRow.createCell(colIdx++).setCellValue("Manufacturer_Name");
			            headerRow.createCell(colIdx++).setCellValue("Distributor_Name");
			            headerRow.createCell(colIdx++).setCellValue("Batch_Name");
			            headerRow.createCell(colIdx++).setCellValue("Expiry_Date");
			            headerRow.createCell(colIdx++).setCellValue("Drug_Code");
			        }
			        if("Avblbatch".equalsIgnoreCase(searchType)){//Chandana - 8251 - Added this to show the below two in available drugs with batch number excel
			        	headerRow.createCell(colIdx++).setCellValue("Physical_stock_as_on_today");
			            headerRow.createCell(colIdx++).setCellValue("Remarks");
			        }
			        if(!"Avblbatch".equalsIgnoreCase(searchType))//Chandana - 8251 - Added condition
			        	headerRow.createCell(colIdx++).setCellValue("Quantity");//Chandana - 8251 - hiding the quantity from excel for available drugs with batch number, as by latest disucssion
			        for (DrugsVO drugs : drugsBal) {
			        	Row dataRow = sheet.createRow(rowIdx++);
			        	int dataColIdx = 0;
			        	if("Avblbatch".equalsIgnoreCase(searchType)){
			        		Cell c1 = dataRow.createCell(dataColIdx++);
					            c1.setCellValue(sNo++);
					            c1.setCellStyle(lockedStyle);
				            Cell c2 = dataRow.createCell(dataColIdx++);
					            c2.setCellValue(drugs.getDrugTypeName());
					            c2.setCellStyle(lockedStyle);
				            Cell c3 = dataRow.createCell(dataColIdx++);
					            c3.setCellValue(drugs.getDrugName());
					            c3.setCellStyle(lockedStyle);
				            Cell c4 = dataRow.createCell(dataColIdx++);
					            c4.setCellValue(drugs.getDispDrugMfg());
					            c4.setCellStyle(lockedStyle);
				            Cell c5 = dataRow.createCell(dataColIdx++);
					            c5.setCellValue(drugs.getDispDrugDsbtr());
					            c5.setCellStyle(lockedStyle);
				            Cell c6 = dataRow.createCell(dataColIdx++);
					            c6.setCellValue(drugs.getBatchNo());
					            c6.setCellStyle(lockedStyle);
				            Cell c7 = dataRow.createCell(dataColIdx++);
					            c7.setCellValue(drugs.getExpiryDt());
					            c7.setCellStyle(lockedStyle);
				            Cell c8 = dataRow.createCell(dataColIdx++);
					            c8.setCellValue(drugs.getDispDrugMstrDrgCode());
					            c8.setCellStyle(lockedStyle);
				            Cell editable1 = dataRow.createCell(dataColIdx++);
					            editable1.setCellValue("");
					            editable1.setCellStyle(unlockedStyle);
				            Cell editable2 = dataRow.createCell(dataColIdx++);
					            editable2.setCellValue("");
					            editable2.setCellStyle(unlockedStyle);
				            sheet.protectSheet("");
			        	}
			        	else{
			        		dataRow.createCell(dataColIdx++).setCellValue(sNo++);
				            dataRow.createCell(dataColIdx++).setCellValue(drugs.getDrugTypeName());
				            dataRow.createCell(dataColIdx++).setCellValue(drugs.getDrugName());
				            if ("Allbatch".equalsIgnoreCase(searchType)) {
				                dataRow.createCell(dataColIdx++).setCellValue(drugs.getDispDrugMfg());
				                dataRow.createCell(dataColIdx++).setCellValue(drugs.getDispDrugDsbtr());
				                dataRow.createCell(dataColIdx++).setCellValue(drugs.getBatchNo());
				                dataRow.createCell(dataColIdx++).setCellValue(drugs.getExpiryDt());
				                dataRow.createCell(dataColIdx++).setCellValue(drugs.getDispDrugMstrDrgCode()); 
				            }
				            	dataRow.createCell(dataColIdx++).setCellValue(drugs.getOutDrugsQuantity());
			        	}  
			        }
			        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			            sheet.autoSizeColumn(i);
			        }
			        response.setContentType(lStrContentType);
			        response.setHeader("Content-Disposition", "attachment; filename=" + lStrFileName);
			        OutputStream out = response.getOutputStream();
			        workbook.write(out);
			        out.flush();
			        return null;
					}
				else
					lStrPageName="drugsOutStandingBalance";
			}
			else			
			lStrPageName="drugsOutStandingBalance";
		}
		if ("viewDrugInventoryForm".equalsIgnoreCase(lStrActionVal))
		{
			try
			{
				List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
				request.setAttribute("dispDrugTypeList", drugsTypeList);
				List<LabelBean> mftrList=patientService.getDrugMftrList();
				List<String> mfd=new ArrayList<String>();
				if(mftrList!=null)
				{
					for(LabelBean lb:mftrList)
					{
						String temp=lb.getVALUE()+"-"+lb.getID();
						mfd.add(temp);
					}
				}
				List<LabelBean> dstrList=patientService.getDrugDstbtrList();
				List<String> dstr=new ArrayList<String>();
				if(dstrList!=null)
				{
					for(LabelBean lb:dstrList)
					{
						String temp=lb.getVALUE()+"-"+lb.getID();
						dstr.add(temp);
					}
				}
				request.setAttribute("mftList", mftrList);
			//	request.setAttribute("mftList", mfd);
				request.setAttribute("dstrList", dstrList);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			lStrPageName = "drugsInventory";
		}
		if ("viewEnablePharma".equalsIgnoreCase(lStrActionVal))
		{
			EhfPatient ehfPatient=null;
			String patientId=request.getParameter("patientId");
			if(patientId!=null && !"".equalsIgnoreCase(patientId))
			{
				patientForm.setPatientNo(patientId);
				ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				if(ehfPatient.getPharmaView()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmaView()))
				{
				patientForm.setEnablePharma(ehfPatient.getPharmaView());
				request.setAttribute("enablePharma",patientForm.getEnablePharma());
				}
			}
			lStrPageName="pharmacyDrgsPge";
		}
		if ("viewPharmacyPage".equalsIgnoreCase(lStrActionVal))
		{
			EhfPatient ehfPatient=null;
			String patientId=request.getParameter("patientId");
			String caseId=patientForm.getCaseId();
			patientVO = new PatientVO();
			if(patientId!=null && !"".equalsIgnoreCase(patientId))
			{
				patientForm.setPatientNo(patientId);
				ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				patientForm.setCardNo(ehfPatient.getCardNo());
				patientForm.setFname(ehfPatient.getName());
				patientForm.setEnablePharma(ehfPatient.getPharmaView());
				if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
					patientForm.setGender("Male");
				else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
					patientForm.setGender("Female");
				if(ehfPatient.getAge()!=null)
				{
					patientForm.setYears(ehfPatient.getAge().toString());
					if(ehfPatient.getAge().toString() != null && (ehfPatient.getAge() <=13 ))
						request.setAttribute("child", "Y");
					else
						request.setAttribute("child", "N");
				}
				if(ehfPatient.getAgeMonths()!=null)
					patientForm.setMonths(ehfPatient.getAgeMonths().toString());
				if(ehfPatient.getAgeDays()!=null)
					patientForm.setDays(ehfPatient.getAgeDays().toString());
				//Drug Details
				patientVO.setCaseId(caseId);
				patientVO.setPatientId(patientId);
				String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
				patientVO.setDispCode(dispId);
				if(ehfPatient.getPharmDrugSave()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmDrugSave()))
				{
					patientVO.setSaveFlag(ehfPatient.getPharmDrugSave());
					request.setAttribute("saveFlag", "Y");
				}
				else
				{
					patientVO.setSaveFlag("N");
					request.setAttribute("saveFlag", "N");
				}
				if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
					List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
					request.setAttribute("treatHist", caseHist);
					patientForm.setLstCaseSearch(caseHist);
					}
				List<DrugsVO> drugsList=patientService.getPharmDrugDtls(patientVO);
				patientForm.setDrugLt(drugsList);
				if(patientForm.getEnablePharma()!=null && !"".equalsIgnoreCase(patientForm.getEnablePharma()))
				{
				String enablePharma=patientForm.getEnablePharma();
				request.setAttribute("enablePharma", enablePharma);
				}
				if(ehfPatient.getotpFlag()!=null && "N".equalsIgnoreCase(ehfPatient.getotpFlag()))
					request.setAttribute("enableOTP", "Y");
				if(ehfPatient.getotpFlag()!=null)
					request.setAttribute("exemptOTP", ehfPatient.getotpFlag());
				lStrPageName="pharmacyDrgsPge";
			}
			else
				lStrPageName="patient";
		}
		if ("getDbtrsAjax".equalsIgnoreCase(lStrActionVal))
		{
			List<String> drgsLT=new ArrayList<String>();
			String drgType=request.getParameter("drgType");
			String drgName=request.getParameter("drgName");
			String mftr=request.getParameter("mftr");
			DrugsVO drgs=new DrugsVO();
			drgs.setDrugTypeCode(drgType);
			drgs.setDrugSubTypeCode(drgName);
			drgs.setMftrId(mftr);
			  List<LabelBean> drgsLt=patientService.getDistributer(drgs);
			  if(drgsLt!=null && drgsLt.size()>0)
			  {
				  for(LabelBean labelBean:drgsLt )
				  {
					  if (labelBean.getID() != null && labelBean.getVALUE() != null)
						  drgsLT.add(labelBean.getID()+"~"+labelBean.getVALUE()+"@");
				  }
			  }
			  request.setAttribute("AjaxMessage", drgsLT);	
			lStrPageName="ajaxResult";
		}
		if ("getQuantAjax".equalsIgnoreCase(lStrActionVal))
		{
			String drgsLT=new String();
			String drgType=request.getParameter("drgType");
			String drgName=request.getParameter("drgName");
			String mftr=request.getParameter("mftr");
			String dbtr=request.getParameter("dbtr");
			DrugsVO drgs=new DrugsVO();
			drgs.setDrugTypeCode(drgType);
			drgs.setDrugSubTypeCode(drgName);
			drgs.setMftrId(mftr);
			drgs.setDbtr(dbtr);
			  List<LabelBean> drgsLt=patientService.getAvailabeDrugs( drgs );
			  if(drgsLt!=null && drgsLt.size()>0)
			  {
				  if(drgsLt.get(0)!=null)
				  {
					  if (drgsLt.get(0).getIDVAL() != null && drgsLt.get(0).getVALUE() != null)
						  drgsLT=drgsLt.get(0).getIDVAL()+"~"+drgsLt.get(0).getVALUE();
				  }
			  }
			  request.setAttribute("AjaxMessage", drgsLT);
			lStrPageName="ajaxResult";
		}
		if ("savePharmacydrugs".equalsIgnoreCase(lStrActionVal))
		{
			EhfPatient ehfPatient=null;
			String patientId=patientForm.getPatientNo();
			if(patientId!=null && !"".equalsIgnoreCase(patientId))
			{
				patientForm.setPatientNo(patientId);
				ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				if(ehfPatient.getPharmaView()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmaView()))
				patientForm.setEnablePharma(ehfPatient.getPharmaView());
			}
			if(ehfPatient.getPharmaView()!=null && "Y".equalsIgnoreCase(ehfPatient.getPharmaView()))
			{
		  String saveFlg=request.getParameter("saveFlg");
		  String caseId=patientForm.getCaseId();
		  if(caseId!=null && !"".equalsIgnoreCase(caseId))
		  {
			  patientVO = new PatientVO();
			  patientVO.setCaseId(caseId);
			  patientVO.setCrtUsr(lStrUserId);
			  patientVO.setPatientId(patientForm.getPatientNo());
			  patientVO.setState(saveFlg);
			  patientVO.setRoleId(roleId);
			  List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
			  if(patientForm.getDrugs()!=null && !patientForm.getDrugs().equalsIgnoreCase("")){
			  				String s=patientForm.getDrugs().substring(0, patientForm.getDrugs().length()-1);
			  				String[] drugs=s.split("@");
			  				DrugsVO drugsVO=null;
			  				for(int z=0;z<drugs.length;z++)
			  				{
			  					String drug=drugs[z];
			  					String[] drugValues=drug.split("~");
			  					drugsVO = new DrugsVO();
			  					 if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			  							{
			  							 	drugsVO.setDrugTypeCode(drugValues[0]);
			  								drugsVO.setDrugSubTypeCode(drugValues[1]);
			  								drugsVO.setDRUGCODE(drugValues[2]);
			  								drugsVO.setBatchNo(drugValues[3]);
			  								drugsVO.setQuantity(drugValues[4]);
			  							}
			  					drugsList.add(drugsVO);
			  				}
			  				}
			  				patientVO.setDrugs(drugsList);
			  				String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			  				patientVO.setDispCode(dispId);
			  			//Upload Drugs Issued Related File
				  			String dir1 = null;
				  			String dir2 = null;
				  			String dir3 = null;
				  			String fileName1=patientForm.getDrugsIssue().getFileName();
				  			String fileName2=patientForm.getDrugsIssue2().getFileName();
				  			String fileName3=patientForm.getDrugsIssue3().getFileName();
				  			if(patientForm.getDrugsIssue()!= null  && !"".equalsIgnoreCase(fileName1))
					    	{
				  				FormFile obj = 	patientForm.getDrugsIssue();
						    	if(obj.getFileName() != null)
						    	{
						    			String lStrSharePath = config.getString("mapPath")+config.getString("DrugsIssued")+caseId+"/";
							             String filePath= lStrSharePath + patientForm.getDrugsIssue().getFileName(); 
							             java.util.Date ldtToday = new java.util.Date();
					                     String fullPath = filePath ; 
					                     dir1 =fullPath;
					                     ( new File ( dir1 ) ).mkdirs () ;
					                     dir1 = dir1 +"/"+ ldtToday.getTime()+"_"+obj.getFileName();
					                     File lFileFS = new File ( dir1 ) ;
					                       FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
					                       fos.write ( obj.getFileData() ) ;
					                       fos.close();
					                }
					    	}
				  			if(patientForm.getDrugsIssue2()!= null  && !"".equalsIgnoreCase(fileName2))
					    	{
				  				FormFile obj = 	patientForm.getDrugsIssue2();
						    	if(obj.getFileName() != null)
						    	{
						    			String lStrSharePath = config.getString("mapPath")+config.getString("DrugsIssued")+caseId+"/";
							             String filePath= lStrSharePath + patientForm.getDrugsIssue2().getFileName(); 
							             java.util.Date ldtToday = new java.util.Date();
					                     String fullPath = filePath ; 
					                     dir2 =fullPath;
					                     ( new File ( dir2 ) ).mkdirs () ;
					                     dir2 = dir2 +"/"+ ldtToday.getTime()+"_"+obj.getFileName();
					                     File lFileFS = new File ( dir2 ) ;
					                       FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
					                       fos.write ( obj.getFileData() ) ;
					                       fos.close();
					                }
					    	}
				  			if(patientForm.getDrugsIssue3()!= null  && !"".equalsIgnoreCase(fileName3))
					    	{
				  				FormFile obj = 	patientForm.getDrugsIssue3();
						    	if(obj.getFileName() != null)
						    	{
						    			String lStrSharePath = config.getString("mapPath")+config.getString("DrugsIssued")+caseId+"/";
							             String filePath= lStrSharePath + patientForm.getDrugsIssue3().getFileName(); 
							             java.util.Date ldtToday = new java.util.Date();
					                     String fullPath = filePath ; 
					                     dir3 =fullPath;
					                     ( new File ( dir3 ) ).mkdirs () ;
					                     dir3 = dir3 +"/"+ ldtToday.getTime()+"_"+obj.getFileName();
					                     File lFileFS = new File ( dir3 ) ;
					                       FileOutputStream fos = new FileOutputStream ( lFileFS  + "/" ) ;
					                       fos.write ( obj.getFileData() ) ;
					                       fos.close();
					                }
					    	}
				  			   patientVO.setFileName(fileName1+'~'+fileName2+'~'+fileName3);
		                       patientVO.setFilePath(dir1+'~'+dir2+'~'+dir3);
			  			String patCaseId=patientService.savePharmaDrugs(patientVO);
			  			if(patCaseId!=null && patCaseId.equalsIgnoreCase(caseId))
			  				patientForm.setMsg("Drugs Issued Successfully.");
			  			else
			  				patientForm.setMsg("Drugs Cannot be Issued");
			  			request.setAttribute("fromPharma", "Y");
			  			lStrPageName="patient";	
		  }
			}
			else 
			{
				patientForm.setMsg("Drugs has already been issued for this case");
			    lStrPageName="hideRegisteredPatientsDisp";
			}	
		}
		if ("checkOldDrugsAjax".equalsIgnoreCase(lStrActionVal))
		{
			String drgType=request.getParameter("drgType");
			String drgName=request.getParameter("drgName");
			String patientId=request.getParameter("patientId");
			PatientVO dtls=new PatientVO();
			dtls.setDrugTypeCode(drgType);
			dtls.setDrugSubTypeCode(drgName);
			dtls.setPatientId(patientId);
			DrugsVO drgData=patientService.getOldDrugStatus(dtls);
			String drgsIssued="";
			if(drgData!=null)
			{
				if(drgData.getDrugName()!=null && !"".equalsIgnoreCase(drgData.getDrugName()))
				{
					if(drgData.getQuantity()!=null && drgData.getDosage()!=null && drgData.getMedicationPeriod()!=null && drgData.getCrtDt()!=null)
						drgsIssued=drgsIssued+drgData.getDrugName()+"~"+drgData.getCrtDt()+"~"+drgData.getQuantity()+"~"+drgData.getDosage()+"~"+drgData.getMedicationPeriod()+"~"+drgData.getDrugSubTypeCode();
				}
			}
			  request.setAttribute("AjaxMessage", drgsIssued);
			lStrPageName="ajaxResult";
		}
		return mapping.findForward(lStrPageName);
	  }
	private int getNoOfPages(int totRows,int noOfRowsPerPage)
	{
		int noOfPages = 0;
		if(totRows>0 && noOfRowsPerPage>0)
		{
			noOfPages = totRows/noOfRowsPerPage;
			if(totRows%noOfRowsPerPage>0)
				noOfPages++;
		}
		return noOfPages;
	}
	private int getStartIndex(int noOfRowsPerPage,int pageNo)
	{
		int startIndex = 0;
		if(noOfRowsPerPage>0 && pageNo>0)
		{
			if(pageNo==1)
				startIndex = 0;
			else if(pageNo>1)
				startIndex = (pageNo-1)*noOfRowsPerPage;
		}
		return startIndex;
	}
	public static List<Date> getDatesBetweenUsingJava7(
			  Date startDate, Date endDate) {
			    List<Date> datesInRange = new ArrayList<>();
			    Calendar calendar = new GregorianCalendar();
			    calendar.setTime(startDate);
			    Calendar endCalendar = new GregorianCalendar();
			    endCalendar.setTime(endDate);
			    while (calendar.before(endCalendar)) {
			        Date result = calendar.getTime();
			        datesInRange.add(result);
			        calendar.add(Calendar.DATE, 1);
			    }
			    return datesInRange;
			}
}