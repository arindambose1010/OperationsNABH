package com.ahct.patient.action;
import java.awt.Color;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestHandler;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ExcelWriter;
import com.ahct.common.util.PdfGenerator;
import com.ahct.common.util.SMSServices;
import com.ahct.common.util.StringUtility;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.model.EhfAisDrugs;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.patient.constants.FileConstants;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.vo.CommonDtlsVO;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.SpecialityVO;
import com.google.gson.Gson;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
public class PatientActionNew extends Action{
	private final static Logger GLOGGER = Logger.getLogger ( PatientAction.class ) ;
	private static final float PADDING = 2.0f;
    private static final Font datafont =
            FontFactory.getFont(FontFactory.TIMES, 11);
        private static final Font subfont =
            FontFactory.getFont(FontFactory.TIMES_BOLD, 9);
        private static final Font headerfont =
            FontFactory.getFont(FontFactory.TIMES_BOLD, 11);
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		String templateId=null;
		response.setHeader("Cache-Control","no-cache");
		response.setHeader("pragma","no-cache");
		response.setDateHeader("Expires", 0);    
		HttpSession session = request.getSession ( false ) ;
		PatientForm patientForm=(PatientForm)form;
		String loginName = (String) session.getAttribute("userName");
		PatientVO patientVO=null;
		ConfigurationService configurationService = 
				(ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
		String userName=null;
		String lStrUserId =null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String grpId=null;
		 String lStrFlagStatus = request.getParameter("actionFlag");
		String roleId=null;
		SimpleDateFormat sdfw=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		FileInputStream fis=null;
		DataInputStream dis=null;
		String schemeId=null;
		Date ldtToday = new Date();
		String callType=null;
		String msg=null;
		String bifurcationDate=config.getString("bifurcationDate");
		request.setAttribute("bifurcationDate", bifurcationDate);
//		ReportsForm reportForm = (ReportsForm) form;
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
		String distParntId = config.getString("IPOP.APCode");
		String apStateId = config.getString("IPOP.APCode");
		String tgStateId = config.getString("IPOP.TGCode");
		String lStrcastesHdrId = config.getString("IPOP.CasteCMBHDRID");
		String lStrMandalHdrId = config.getString("IPOP.MandalHDRID");
		String lStrMunicipalHdrId = config.getString("IPOP.MunicipalityHDRID");
		String lStrVillageHdrId = config.getString("IPOP.VillageHDRID");
		String lStrMplVillageHdrId = config.getString("IPOP.MunicipalVillageHDRID");
		String lStrActionVal = request.getParameter("actionFlag");
		String lStrPageName = null; 
		String checkHosp=null;
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
		String dispensaryId =null;
			if(lStrUserId!=null && !"".equalsIgnoreCase(lStrUserId))
		{
				wellnessapplist=commonService.checkUsrMapped(lStrUserId);
		}
			if(wellnessapplist!=null && wellnessapplist.size()>0)
		{
				dispname=wellnessapplist.get(0).getDISPID();
		}
			List<LabelBean> wellnesslist=null;
			List<LabelBean> isUsrMapped = null;
			List<LabelBean> indentNolist = new ArrayList<LabelBean>();
			List<LabelBean> indentPoList = new ArrayList<LabelBean>();
			if(lStrUserId!=null && !"".equalsIgnoreCase(lStrUserId))
			{
				wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
			}
			if(wellnesslist!=null && wellnesslist.size()>0)
			{
				request.setAttribute("wellnesslist", wellnesslist);	
				 dispensaryId=wellnesslist.get(0).getDISPID();
			}
			else 
			{
				 wellnesslist=patientService.getwellness();
				 request.setAttribute("wellnesslist", wellnesslist);	
			}
			if("viewAppointents".equalsIgnoreCase(lStrActionVal))
			{    int pageNo=1,rowsPerPage=10;
 			int startIndex=1,maxResults=0;
  		   String flag= request.getParameter("from");
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
 				if(flag!=null&&"Y".equalsIgnoreCase(flag))
 				{
 					dispname=	patientForm.getDispname();
 				}
 				if(dispname!=null &&!"".equalsIgnoreCase(dispname))
 			   {
 					  patientForm.setDispname(dispname);
 					 appointmentsList=commonService.getapp(dispname,startIndex,maxResults,pageNo,fromDate,toDate);
 			   }
 				else
 				{
 					request.setAttribute("msg", "Wellness center not mapped to the User");
 				}
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
 			return mapping.findForward("appointmentsReport");}
			if(lStrActionVal!=null && lStrActionVal.equalsIgnoreCase("mhcPdf")){
				String genFlag = request.getParameter("genFlag");
			if (genFlag != null && genFlag.equalsIgnoreCase("P")) {			
				File file = null ;
				//FileInputStream fis = null ;
	            //DataInputStream dis = null ;
	        ServletOutputStream out = response.getOutputStream();
			String filepath = config.getString("AIS.MapPath")
					+ config.getString("DOT_VALUE")
					+ config.getString("REPORT.PdfExtn");
	    	String lStrFileName = "Master_Health_Checkup.Pdf";
	  	  String lStrContentType="";
	  	  String attachMode="attachment";
	  	 String lStrFileType=config.getString("REPORT.PdfExtn");
	       if ( filepath != null )
	       {
	         byte[] lbBytes = null ;
	         lStrContentType=config.getString("REPORT.PdfExtn"); 
	          try
	          {
	              file = new File ( filepath ) ; 
	              fis = new FileInputStream ( file ) ;
	              dis = new DataInputStream ( fis ) ;
	              lbBytes = new byte[ dis.available () ] ;
	              fis.read ( lbBytes ) ;
	              request.setAttribute ( "File", lbBytes ) ;
	              request.setAttribute ( "ContentType", lStrContentType ) ;
	              request.setAttribute ( "FileName", lStrFileName ) ;
	              request.setAttribute ( "Extn", lStrFileType ) ;
	              response.setContentType ( lStrContentType ) ; 	            
	              response.setHeader("Content-Disposition",""+attachMode+"; filename="+lStrFileName);
	              out.write(lbBytes);
	          }
	          finally
	          {
	        	  out.close();
	          }
	       }
	        }
				lStrPageName = "InPatientEmployee";
			}
			if("getTherapyCategory".equalsIgnoreCase(lStrActionVal)){
				String lStrHospId=request.getParameter("lStrHospId");
				List<LabelBean> categoryList = commonService.getAsriCategoryList(lStrHospId,schemeId);
				session.setAttribute("AsriCategoryList",categoryList);
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
			if("getTherapyProcedure".equalsIgnoreCase(lStrActionVal)){
				String lStrCatId=request.getParameter("lStrCatId");
				List<LabelBean> procedureList=patientService.getTherapyProcedure(lStrCatId);
				session.setAttribute("ICDprocedureList",procedureList);
				List<String> procedureList1 = new ArrayList<String>();
				for (LabelBean labelBean: procedureList) {
					if (labelBean.getID() != null && 
							labelBean.getVALUE() != null)
						if (procedureList1 != null && procedureList1.size() > 0) {
							procedureList1.add(labelBean.getID() + "~" + 
									labelBean.getVALUE()+"@");
						} else
							procedureList1.add(labelBean.getID() + "~" + 
									labelBean.getVALUE()+"@");
				}
				if (procedureList1 != null && procedureList1.size() > 0)
					request.setAttribute("AjaxMessage", procedureList1);
				lStrPageName="ajaxResult";
			}
			if ("rejectPatient".equalsIgnoreCase(lStrActionVal))
			{
			String caseId = request.getParameter("patientId");
			PatientVO patVo=new PatientVO();
			if(caseId != null && !"".equalsIgnoreCase(caseId))
			patVo.setPatientId(caseId);
			patVo.setLstUpdUsr(lStrUserId);
			patVo.setUserRole(roleId);
			 msg = patientService.rejectPatient(patVo);
			request.setAttribute("saveMsg", msg);
			lStrActionVal="ViewRegisteredPatientsNims";
			}
			if("getAisNames".equalsIgnoreCase(lStrActionVal))
			{
				String serviceType= request.getParameter("servType");
				String typeActive = request.getParameter("seviceType");
				List<LabelBean> mftrList=null;
				//sowmya added condition for legislative
				if("MLA".equalsIgnoreCase(typeActive) || "MLC".equalsIgnoreCase(typeActive)
						|| "EX-MLA".equalsIgnoreCase(typeActive) || "EX-MLC".equalsIgnoreCase(typeActive) 
						|| "FSOD".equalsIgnoreCase(typeActive)){
					mftrList=patientService.getLegislativeNames(typeActive);
				}else{
					mftrList=patientService.getAisNames(serviceType,typeActive);
				}
				List<String> mfd=new ArrayList<String>();
				if(mftrList!=null)
				{
					for(LabelBean lb:mftrList)
					{
						//String temp=lb.getVALUE()+"-"+lb.getID();
						String temp=lb.getID()+"-"+lb.getVALUE();
						mfd.add(temp);
					}
				}
				request.setAttribute("AjaxMessage", mfd);
				return mapping.findForward("ajaxResult");
			}
			//sowmya for create Legislative member
			if ("viewLegislativeReg".equalsIgnoreCase(lStrActionVal))
			{
				List<PatientVO> districtsList = null;
				PatientVO distPatVO = patientService.getAllDistrictList();
					districtsList = distPatVO.getDistrictList();
					if (districtsList != null && districtsList.size() > 0) {
						request.setAttribute("districtsList", districtsList);
				}
				lStrPageName="LegislativeMemberCreation";
			}
			if ("submitLHSEnrollmentDetails".equalsIgnoreCase(lStrActionVal))
			{
				  patientVO = new PatientVO();
				  PatientForm patForm = (PatientForm) form; 
				  FormFile file = patForm.getAadhaarFile();
				  FormFile photoFile = patForm.getPhotoFile();
				  FormFile supportFile = patForm.getSupportDocFile();
				  String savedAadharFilePath = null;
				  String savedPhotoFilePath = null;
				  String savedSupportFilePath = null;
				  String aadharFileName = null;
				  String photoFileName = null;
				  String supportFileName = null;
				  String basePath = null;
				//  String dateFolder = null;
				//  String folderPath = null;
				  if (file != null && file.getFileName() != null && !file.getFileName().isEmpty()) {
				      aadharFileName = file.getFileName();
				  }
				  if (photoFile != null && photoFile.getFileName() != null && !photoFile.getFileName().isEmpty()) {
				      photoFileName = photoFile.getFileName();
				  }
				  if (supportFile != null && supportFile.getFileName() != null && !supportFile.getFileName().isEmpty()) {
				      supportFileName = supportFile.getFileName();
				  }
				  //SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd_HHmmss");
				  String timestamp = String.valueOf(System.nanoTime());
				  basePath = "/storageNAS1-TS-Production/"+"LHSEntollmentDocuments/";
				    String lhsId = patForm.getMlaId();
					if(aadharFileName != null || photoFileName != null || supportFileName != null)
			    	{
						 File dir = new File(basePath);
					        if (!dir.exists()) {
					        	boolean created = dir.mkdirs(); 
					            if (!created) {
					                System.out.println("Failed to create folder: " + basePath);
					            }
					        }
					       if(aadharFileName != null){
					    	  // savedAadharFilePath = folderPath + aadharFileName;
					    	  // savedAadharFilePath = basePath+lhsId+"_"+aadharFileName+"_"+timestamp;
					    	   savedAadharFilePath = basePath+lhsId+"_"+timestamp+"_"+aadharFileName;
					        try (InputStream in = file.getInputStream();
					             FileOutputStream fos = new FileOutputStream(savedAadharFilePath)) {
					            byte[] buffer = new byte[1024];
					            int bytesRead;
					            while ((bytesRead = in.read(buffer)) != -1) {
					                fos.write(buffer, 0, bytesRead);
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					       } 
					       if(photoFileName != null){
					    	 //  savedPhotoFilePath = folderPath + photoFileName;
					    	   savedPhotoFilePath = basePath+lhsId+"_"+timestamp+"_"+photoFileName;
						        try (InputStream in = photoFile.getInputStream();
						             FileOutputStream fos = new FileOutputStream(savedPhotoFilePath)) {
						            byte[] buffer = new byte[1024];
						            int bytesRead;
						            while ((bytesRead = in.read(buffer)) != -1) {
						                fos.write(buffer, 0, bytesRead);
						            }
						        } catch (IOException e) {
						            e.printStackTrace();
						        }
					       }
					      if(supportFileName != null){
					    	//  savedSupportFilePath = folderPath + supportFileName;
					    	  savedSupportFilePath = basePath+lhsId+"_"+timestamp+"_"+supportFileName;
					        try (InputStream in = supportFile.getInputStream();
					             FileOutputStream fos = new FileOutputStream(savedSupportFilePath)) {
					            byte[] buffer = new byte[1024];
					            int bytesRead;
					            while ((bytesRead = in.read(buffer)) != -1) {
					                fos.write(buffer, 0, bytesRead);
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					      }
			    	}
				  patientVO.setFirstName(patForm.getFname());
				  patientVO.setFatherName(patForm.getFatherName());
				  patientVO.setSpouseName(patForm.getSpouseName());
				  patientVO.setDateOfBirth(patForm.getDateOfBirth());
				  patientVO.setMobile(patForm.getMobile());
				  patientVO.setGender(patForm.getGender());
				  patientVO.seteMailId(patForm.geteMailId());
				  patientVO.setMemberType(patForm.getMemberType());
				  patientVO.setConstituency(patForm.getConstituency());
				  patientVO.setLHSId(patForm.getMlaId());
				  patientVO.setTermStartDate(patForm.getTermStartDate());
				  patientVO.setTermEndDate(patForm.getTermEndDate());
				  patientVO.setPermanentAddress(patForm.getPermanentAddress());
				  patientVO.setOfficialAddress(patForm.getOfficialAddress());
				  patientVO.setConstituencyAddress(patForm.getConstituencyAddress());
				  patientVO.setDistrictCode(patForm.getDistrict());
				  patientVO.setPincode(patForm.getPincode());
				  patientVO.setAadhaarNo(patForm.getAadharID());
				  patientVO.setCrtUsr(lStrUserId);
				  patientVO.setAadharFilePath(savedAadharFilePath);
				  patientVO.setAadharFileName(aadharFileName);
				  patientVO.setPhotoFilePath(savedPhotoFilePath);
				  patientVO.setPhotoFileName(photoFileName);
				  patientVO.setSupportFilePath(savedSupportFilePath);
				  patientVO.setSupportFileName(supportFileName);
				  //dependent details
				  String dependentsJson = request.getParameter("dependentsJson");
				  List<PatientVO> dependentList = new ArrayList<>();
				  if (dependentsJson != null && !dependentsJson.trim().isEmpty()) {
				      try {
				          JSONArray jsonArray = new JSONArray(dependentsJson);
				          MultipartRequestHandler multi = patientForm.getMultipartRequestHandler();
				          int counter = 1;
				          for (int i = 0; i < jsonArray.length(); i++) {
				              JSONObject obj = jsonArray.getJSONObject(i);
				              PatientVO dep = new PatientVO();
				              // Set basic fields from JSON
				              dep.setDependentName(obj.optString("dependentName", ""));
				              dep.setDependentMobileNo(obj.optString("dependentMobileNo", ""));
				              dep.setDependentGender(obj.optString("dependentGender", ""));
				              dep.setDependentRelation(obj.optString("dependentRelation", ""));
				              dep.setMaritalStatus(obj.optString("maritalStatus", ""));
				              dep.setDateOfBirth(obj.optString("dependentDob", ""));
				              dep.setDependentAadhaarNo(obj.optString("dependentAadhaarNo", ""));
				              dep.setDependentAadhaarFileName(obj.optString("dependentAadhaarDoc", ""));
				              String savedDependentFilePath = null; // Default to null
				              // Check if file is uploaded for this dependent
				              if (multi != null) {
				                  Map fileElements = multi.getFileElements();
				                  String fileKey = "dependentAadhaarFile" + i;
				                  if (fileElements.containsKey(fileKey)) {
				                      FormFile depFile = (FormFile) fileElements.get(fileKey);
				                      if (depFile != null && depFile.getFileSize() > 0) {
				                          // Ensure base directory exists
				                          File dir = new File(basePath);
				                          if (!dir.exists()) {
				                              boolean created = dir.mkdirs();
				                              if (!created) {
				                                  System.out.println("Failed to create folder: " + basePath);
				                              }
				                          }
				                  		String paddedSuffix = String.format("_%02d", counter);
				                  		lhsId = lhsId+ paddedSuffix;
				                          // Create unique filename using lhsId + timestamp
				                          String fileName = depFile.getFileName();
				                          savedDependentFilePath = basePath + lhsId + "_" + timestamp + "_" + fileName;
				                          // Save file to disk
				                          try (InputStream inputStream = depFile.getInputStream();
				                               FileOutputStream fos = new FileOutputStream(savedDependentFilePath)) {
				                              byte[] buffer = new byte[4096];
				                              int bytesRead;
				                              while ((bytesRead = inputStream.read(buffer)) != -1) {
				                                  fos.write(buffer, 0, bytesRead);
				                              }
				                          }
				                          System.out.println("Saved dependent Aadhaar file: " + savedDependentFilePath);
				                      }
				                  }
				              }
				              dep.setDependentAadhaarDoc(savedDependentFilePath); // null if no upload
				              dependentList.add(dep);
				          }
				          counter++; 
				      } catch (Exception e) {
				          e.printStackTrace(); 
				      }
				  }
				  patientVO.setDependentList(dependentList);
				  PatientVO result = patientService.saveLHSEnrollmentDetails(patientVO);
				  String resp = result.getResult();
				  String idno = result.getEmpCode();
				  String caseStatus = result.getType1();
				  response.setContentType("text/plain");
				    PrintWriter out = response.getWriter();
				  //  out.write(status);
				    String jsonResponse = "{\"status\":\"" + resp + "\",\"idno\":\"" + idno + "\",\"caseStatus\":\"" + caseStatus + "\"}";
				    out.write(jsonResponse);
				    out.flush();
				    out.close();
			}
			if("loadPatientPage".equalsIgnoreCase(lStrActionVal)) {
			    request.setAttribute("idno", request.getParameter("idno"));
			    request.setAttribute("lhsFlag", request.getParameter("lhsFlag"));
			    request.setAttribute("caseStatus", request.getParameter("caseStatus"));
			    return mapping.findForward("patientPage");
			}
			//update dependent/revert
			if ("updateLhsMemberDetails".equalsIgnoreCase(lStrActionVal))
			{
				  patientVO = new PatientVO();
				  PatientForm patForm = (PatientForm) form; 
				  FormFile file = patForm.getAadhaarFile();
				  FormFile photoFile = patForm.getPhotoFile();
				  FormFile supportFile = patForm.getSupportDocFile();
				  String savedAadharFilePath = null;
				  String savedPhotoFilePath = null;
				  String savedSupportFilePath = null;
				  String aadharFileName = null;
				  String photoFileName = null;
				  String supportFileName = null;
				  String basePath = null;
				  if (file != null && file.getFileName() != null && !file.getFileName().isEmpty()) {
				      aadharFileName = file.getFileName();
				  }
				  if (photoFile != null && photoFile.getFileName() != null && !photoFile.getFileName().isEmpty()) {
				      photoFileName = photoFile.getFileName();
				  }
				  if (supportFile != null && supportFile.getFileName() != null && !supportFile.getFileName().isEmpty()) {
				      supportFileName = supportFile.getFileName();
				  }
				  String timestamp = String.valueOf(System.nanoTime());
				  basePath = "/storageNAS1-TS-Production/"+"LHSEntollmentDocuments/";
				    String lhsId = patForm.getMlaId();
					if(aadharFileName != null || photoFileName != null || supportFileName != null)
			    	{
						 File dir = new File(basePath);
					        if (!dir.exists()) {
					        	boolean created = dir.mkdirs(); 
					            if (!created) {
					                System.out.println("Failed to create folder: " + basePath);
					            }
					        }
					       if(aadharFileName != null){
					    	   savedAadharFilePath = basePath+lhsId+"_"+timestamp+"_"+aadharFileName;
					        try (InputStream in = file.getInputStream();
					             FileOutputStream fos = new FileOutputStream(savedAadharFilePath)) {
					            byte[] buffer = new byte[1024];
					            int bytesRead;
					            while ((bytesRead = in.read(buffer)) != -1) {
					                fos.write(buffer, 0, bytesRead);
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					       } 
					       if(photoFileName != null){
					    	   savedPhotoFilePath = basePath+lhsId+"_"+timestamp+"_"+photoFileName;
						        try (InputStream in = photoFile.getInputStream();
						             FileOutputStream fos = new FileOutputStream(savedPhotoFilePath)) {
						            byte[] buffer = new byte[1024];
						            int bytesRead;
						            while ((bytesRead = in.read(buffer)) != -1) {
						                fos.write(buffer, 0, bytesRead);
						            }
						        } catch (IOException e) {
						            e.printStackTrace();
						        }
					       }
					      if(supportFileName != null){
					    	  savedSupportFilePath = basePath+lhsId+"_"+timestamp+"_"+supportFileName;
					        try (InputStream in = supportFile.getInputStream();
					             FileOutputStream fos = new FileOutputStream(savedSupportFilePath)) {
					            byte[] buffer = new byte[1024];
					            int bytesRead;
					            while ((bytesRead = in.read(buffer)) != -1) {
					                fos.write(buffer, 0, bytesRead);
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					      }
			    	}
				  patientVO.setFirstName(patForm.getFname());
				  patientVO.setFatherName(patForm.getFatherName());
				  patientVO.setSpouseName(patForm.getSpouseName());
				  patientVO.setDateOfBirth(patForm.getDateOfBirth());
				  patientVO.setMobile(patForm.getMobile());
				  patientVO.setGender(patForm.getGender());
				  patientVO.seteMailId(patForm.geteMailId());
				  patientVO.setMemberType(patForm.getMemberType());
				  patientVO.setConstituency(patForm.getConstituency());
				  patientVO.setLHSId(patForm.getMlaId());
				  patientVO.setTermStartDate(patForm.getTermStartDate());
				  patientVO.setTermEndDate(patForm.getTermEndDate());
				  patientVO.setPermanentAddress(patForm.getPermanentAddress());
				  patientVO.setOfficialAddress(patForm.getOfficialAddress());
				  patientVO.setConstituencyAddress(patForm.getConstituencyAddress());
				  patientVO.setDistrictCode(patForm.getDistrict());
				  patientVO.setPincode(patForm.getPincode());
				  patientVO.setAadhaarNo(patForm.getAadharID());
				  patientVO.setCrtUsr(lStrUserId);
				  if(patForm.getExistingAadharFileName() != null && patForm.getExistingAadharFilePath()!= null){
					  patientVO.setAadharFilePath(patForm.getExistingAadharFilePath());
					  patientVO.setAadharFileName(patForm.getExistingAadharFileName());
				  }else{
					  patientVO.setAadharFilePath(savedAadharFilePath);
					  patientVO.setAadharFileName(aadharFileName); 
				  }
				  patientVO.setPhotoFilePath(savedPhotoFilePath);
				  patientVO.setPhotoFileName(photoFileName);
				  patientVO.setSupportFilePath(savedSupportFilePath);
				  patientVO.setSupportFileName(supportFileName);
				  String dependentsJson = request.getParameter("dependentsJson");
				  List<PatientVO> dependentList = new ArrayList<>();
				  if (dependentsJson != null && !dependentsJson.trim().isEmpty()) {
				      try {
				          JSONArray jsonArray = new JSONArray(dependentsJson);
				          MultipartRequestHandler multi = patientForm.getMultipartRequestHandler();
				          int maxSuffix = 0;
				          for (int i = 0; i < jsonArray.length(); i++) {
				        	    JSONObject obj = jsonArray.getJSONObject(i);
				        	    String cardNo = obj.optString("dependnetCardNo", "").trim();  // typo in key? Use the correct key you receive
				        	    if (cardNo != null && cardNo.contains("/")) {
				        	        String suffixStr = cardNo.substring(cardNo.lastIndexOf("/") + 1);
				        	        try {
				        	            int suffixNum = Integer.parseInt(suffixStr);
				        	            if (suffixNum > maxSuffix) {
				        	                maxSuffix = suffixNum;
				        	            }
				        	        } catch (NumberFormatException e) {
				        	            // ignore malformed suffix
				        	        }
				        	    }
				        	}
				       // We'll use a counter starting at maxSuffix + 1 for new dependents without cardNo
				          int newDependentCounter = maxSuffix + 1;
				          for (int i = 0; i < jsonArray.length(); i++) {
				              JSONObject obj = jsonArray.getJSONObject(i);
				              PatientVO dep = new PatientVO();
				              String cardNo = obj.optString("dependnetCardNo", "").trim();
				              dep.setDependentCardNo(cardNo);
				              dep.setDependentName(obj.optString("dependentName", ""));
				              dep.setDependentName(obj.optString("dependentName", ""));
				              dep.setDependentMobileNo(obj.optString("dependentMobileNo", ""));
				              dep.setDependentGender(obj.optString("dependentGender", ""));
				              dep.setDependentRelation(obj.optString("dependentRelation", ""));
				              dep.setMaritalStatus(obj.optString("maritalStatus", ""));
				              dep.setDateOfBirth(obj.optString("dependentDob", ""));
				              dep.setDependentAadhaarNo(obj.optString("dependentAadhaarNo", ""));
				              dep.setDependentAadhaarFileName(obj.optString("dependentAadhaarDoc", ""));
				              String savedDependentFilePath = null; 
				              if (multi != null) {
				                  Map fileElements = multi.getFileElements();
				                  String fileKey = "dependentAadhaarFile" + i;
				                  if (fileElements.containsKey(fileKey)) {
				                      FormFile depFile = (FormFile) fileElements.get(fileKey);
				                      if (depFile != null && depFile.getFileSize() > 0) {
				                          File dir = new File(basePath);
				                          if (!dir.exists()) {
				                              boolean created = dir.mkdirs();
				                              if (!created) {
				                                  System.out.println("Failed to create folder: " + basePath);
				                              }
				                          }
				                  		String paddedSuffix = String.format("_%02d", newDependentCounter);
				                  		lhsId = lhsId+ paddedSuffix;
				                          String fileName = depFile.getFileName();
				                          savedDependentFilePath = basePath + lhsId + "_" + timestamp + "_" + fileName;
				                          try (InputStream inputStream = depFile.getInputStream();
				                               FileOutputStream fos = new FileOutputStream(savedDependentFilePath)) {
				                              byte[] buffer = new byte[4096];
				                              int bytesRead;
				                              while ((bytesRead = inputStream.read(buffer)) != -1) {
				                                  fos.write(buffer, 0, bytesRead);
				                              }
				                          }
				                          System.out.println("Saved dependent Aadhaar file: " + savedDependentFilePath);
				                      }
				                  }
				              }
				              dep.setDependentAadhaarDoc(savedDependentFilePath); 
				              dependentList.add(dep);
				          }
				          newDependentCounter++;
				      } catch (Exception e) {
				          e.printStackTrace(); 
				      }
				  }
				  patientVO.setDependentList(dependentList);
				  PatientVO result = patientService.updateLhsMemberDetails(patientVO);
				  String resp = result.getResult();
				  String idno = result.getEmpCode();
				  String caseStatus = result.getType1();
				  response.setContentType("text/plain");
				    PrintWriter out = response.getWriter();
				    String jsonResponse = "{\"status\":\"" + resp + "\",\"idno\":\"" + idno + "\",\"caseStatus\":\"" + caseStatus + "\"}";
				    out.write(jsonResponse);
				    out.flush();
				    out.close();
			}
			if ("viewLHSRegisteredMembers".equalsIgnoreCase(lStrActionVal)){
				PatientVO retPatVO = null;
				PatientVO depPatVO = null;
		        List<PatientVO> registeredPatientsList = null;
		        List<PatientVO> registeredDependentsList = null;
		        HashMap<String, String> searchMap = new HashMap<String, String>();
		        String patientId = request.getParameter("patientId");
		        String status = request.getParameter("caseStatusCode");
		        String lhsApproveFlag = request.getParameter("approveFlag");
		        String editLhsMember = request.getParameter("editLhsMember");
		        String regFlag = request.getParameter("regFlag");
		        String searchFlag = request.getParameter("flag");
		        String approveFlag = request.getParameter("lhsApprove");
		        if (patientId != null && !patientId.trim().isEmpty()){
					searchMap.put("patientNumber", patientId);
				}
		        if (regFlag != null && !regFlag.trim().isEmpty()){
					searchMap.put("registrtionFlag", regFlag);
				}
		        if(editLhsMember != null){
					searchMap.put("editLhsMember", editLhsMember);
				}
		        if (status != null && !status.isEmpty()) {
		            searchMap.put("status", status);
		            request.setAttribute("caseStatus", status);
		        }
		        if (lhsApproveFlag != null && !lhsApproveFlag.isEmpty()) {
		        	request.setAttribute("lhsApproveFlag", lhsApproveFlag);
		        	 searchMap.put("lhsApproveFlag", lhsApproveFlag);
		        }
		        if (status != null && !status.isEmpty()) {
		        	 searchMap.put("approveFlag", approveFlag);
		        }
		        searchMap.put("searchFlag", searchFlag);
				retPatVO = patientService.getRegistredLhsMembersList(searchMap);
				registeredPatientsList = retPatVO.getRegisteredPatList();
				PatientVO registeredLhsPatientDetails = null;
				if (registeredPatientsList != null && registeredPatientsList.size() > 0) {
					request.setAttribute("registeredPatientsList", registeredPatientsList);
					registeredLhsPatientDetails = registeredPatientsList.get(0);
					request.setAttribute("registeredLhsPatientsList", registeredLhsPatientDetails);
				}
				if (registeredPatientsList == null || registeredPatientsList.isEmpty()) {
				    request.setAttribute("noRecords", "No records found");
				    request.setAttribute("regFlag", regFlag);
				}
			if("True".equalsIgnoreCase(editLhsMember)){
				String distId = registeredLhsPatientDetails.getDistrictCode();
				if(distId != null && !distId.trim().isEmpty()) {
				    String lhsMemberDistrict = patientService.getLhsDistrict(distId);
				    if(lhsMemberDistrict != null){
				        request.setAttribute("lhsMemberDistrict", lhsMemberDistrict);
				    }
				}
				depPatVO = patientService.getRegistredLhsDepenentList(searchMap);
				registeredDependentsList = depPatVO.getDependentList();
				if (registeredDependentsList != null && registeredDependentsList.size() > 0) {
					request.setAttribute("registeredDependentsList", registeredDependentsList);
				}
			}	
			//deo login reject,waiting for approval,recommended for approval
				if("True".equalsIgnoreCase(editLhsMember)
						&& (
							("Y".equalsIgnoreCase(regFlag) && !("A".equalsIgnoreCase(status) || "REV".equalsIgnoreCase(status)))
							 || ! "Y".equalsIgnoreCase(regFlag))
					      )  
				{
					request.setAttribute("regFlag", regFlag);
					request.setAttribute("caseStatus", status);
					lStrPageName="LhsRegisteredMemberDetails";
				}
				//deo login view registered approve/revert jsp.
				else if("Y".equalsIgnoreCase(regFlag) && ( "A".equalsIgnoreCase(status) || "REV".equalsIgnoreCase(status)) && !"search".equalsIgnoreCase(searchFlag)){
					List<PatientVO> districtsList = null;
					PatientVO distPatVO = patientService.getAllDistrictList();
					districtsList = distPatVO.getDistrictList();
					if (districtsList != null && districtsList.size() > 0) {
						request.setAttribute("districtsList", districtsList);
				}
					lStrPageName="LhsApprovedMemberDetails";
				}else{
					request.setAttribute("regFlag", regFlag);
					request.setAttribute("caseStatus", status);
					request.setAttribute("lhsApproveFlag", lhsApproveFlag);
			    	lStrPageName="ViewRegisteredLegislativeMembers";
				}
			}
			else if("updateLhsMemberSignedDocument".equalsIgnoreCase(lStrActionVal)){
				  patientVO = new PatientVO();
				  PatientForm patForm = (PatientForm) form; 
				  FormFile file = patForm.getSignedDocFile();
				  String savedSignedFilePath = null;
				  String signedFileName = null;
				  String basePath = null;
				  String rejectRemarks =  null;
				  String approveRemarks = null;
				  if (file != null && file.getFileName() != null && !file.getFileName().isEmpty()) {
					  signedFileName = file.getFileName();
				  }
				  //SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd_HHmmss");
				  String timestamp = String.valueOf(System.nanoTime());
				  basePath = "/storageNAS1-TS-Production/"+"LHSEntollmentDocuments/";
				  String lhsId = patForm.getMlaId();
				  String reject = patForm.getRejectStatus();
				  if("True".equalsIgnoreCase(reject)){
					   rejectRemarks = patForm.getRemarks();
				  }else{
					   approveRemarks = patForm.getRemarks();
				  }
					if(signedFileName != null)
			    	{
						 File dir = new File(basePath);
					        if (!dir.exists()) {
					        	boolean created = dir.mkdirs(); 
					            if (!created) {
					                System.out.println("Failed to create folder: " + basePath);
					            }
					        }
					       if(signedFileName != null){
					    	  // savedAadharFilePath = folderPath + aadharFileName;
					    	  // savedAadharFilePath = basePath+lhsId+"_"+aadharFileName+"_"+timestamp;
					    	   savedSignedFilePath = basePath+lhsId+"_"+timestamp+"_"+signedFileName;
					        try (InputStream in = file.getInputStream();
					             FileOutputStream fos = new FileOutputStream(savedSignedFilePath)) {
					            byte[] buffer = new byte[1024];
					            int bytesRead;
					            while ((bytesRead = in.read(buffer)) != -1) {
					                fos.write(buffer, 0, bytesRead);
					            }
					        } catch (IOException e) {
					            e.printStackTrace();
					        }
					       } 
			    	}
				      patientVO.setCrtUsr(lStrUserId);
					  patientVO.setSignedFilePath(savedSignedFilePath);
					  patientVO.setSignedFileName(signedFileName);
					  patientVO.setLHSId(patForm.getMlaId());
					  patientVO.setType1(patForm.getStatus());
					  patientVO.setApproveRemarks(approveRemarks);
					  patientVO.setRejectRemarks(rejectRemarks);
					  patientVO.setType1(reject);
					  String result = patientService.updateLhsMemberSignedDocument(patientVO);
					//  String status = result.getResult();
					//  String idno = result.getEmpCode();
					  response.setContentType("text/plain");
					    PrintWriter out = response.getWriter();
					    out.write(result);
					//    String jsonResponse = "{\"status\":\"" + status + "\",\"idno\":\"" + idno + "\"}";
					//    out.write(jsonResponse);
					    out.flush();
					    out.close();
			}
			//Lhs view the document.
			else if("viewLhsDocument".equalsIgnoreCase(lStrActionVal)){
				String docPath = request.getParameter("filePath");
				String base64Data = null;
				String ext = null;
				ext = docPath.substring(docPath.lastIndexOf(".")+1);
				if(docPath.toLowerCase().endsWith(".pdf"))
					base64Data=patientService.convertPDFToBase64(docPath);
				else
					base64Data = patientService.convertImageToBase64(docPath);
				request.setAttribute("base64DataDoc", base64Data);
				request.setAttribute("ext", ext);
				return mapping.findForward("viewDocs");
           }
			else if("viewDocument".equalsIgnoreCase(lStrActionVal)){
				String docPath = request.getParameter("fileName");
				String base64Data = null;
				String ext = null;
				ext = docPath.substring(docPath.lastIndexOf(".")+1);
				if(docPath.toLowerCase().endsWith(".pdf"))
					base64Data=patientService.convertPDFToBase64(docPath);
				else
					base64Data = patientService.convertImageToBase64(docPath);
    			request.setAttribute("base64DataDoc", base64Data);
    			request.setAttribute("ext", ext);
    			return mapping.findForward("viewDocs");
            }
			if ("getLhsMlaConstituencies".equalsIgnoreCase(lStrActionVal))
			{
				PatientVO mlaConstituencies = new PatientVO();
				mlaConstituencies = patientService.getLhsMlaConstituencies();
				List<PatientVO> mlaConstituenciesList = mlaConstituencies.getMlaConstituenciesList();
				 JSONArray jsonArray = new JSONArray();
		            if (mlaConstituenciesList != null && !mlaConstituenciesList.isEmpty()) {
		                for (PatientVO vo : mlaConstituenciesList) {
		                    JSONObject obj = new JSONObject();
		                    obj.put("id", vo.getConstituencyNo());
		                    obj.put("name", vo.getFirstName());
		                    jsonArray.put(obj);
		                }
		            }
		            response.getWriter().write(jsonArray.toString());
			}
			if("getMlaConstituencyId".equalsIgnoreCase(lStrActionVal)){
				String constituencyName = request.getParameter("constituencyName");
				String mlaConstituencyId = patientService.getMlaConstituencyId(constituencyName);
				 JSONArray jsonArray = new JSONArray();
				 JSONObject obj = new JSONObject();
                 obj.put("id",mlaConstituencyId);
                 jsonArray.put(obj);
		         response.getWriter().write(jsonArray.toString());
			}
			if ("getLhsMlcConstituencies".equalsIgnoreCase(lStrActionVal))
			{
				PatientVO mlcConstituencies = new PatientVO();
				mlcConstituencies = patientService.getLhsMlcConstituencies();
				List<PatientVO> mlcConstituenciesList = mlcConstituencies.getMlcConstituenciesList();
				JSONArray jsonArray = new JSONArray();
				if (mlcConstituenciesList != null && !mlcConstituenciesList.isEmpty()) {
	                for (PatientVO vo : mlcConstituenciesList) {
	                    JSONObject obj = new JSONObject();
	                    obj.put("name", vo.getIpNo());
	                    jsonArray.put(obj);
	                }
	            }
	            response.getWriter().write(jsonArray.toString());
			}
			//get district drop down based on Constituencies
			if ("getLhsDistrictsList".equalsIgnoreCase(lStrActionVal))
			{
				String constituencyId = request.getParameter("constituencyId");
				PatientVO lhsDistricts = new PatientVO();
				lhsDistricts = patientService.getLhsDistrictsList(constituencyId);
				List<PatientVO> mlcConstituenciesList = lhsDistricts.getLhsDistrictsList();
				JSONArray jsonArray = new JSONArray();
				if (mlcConstituenciesList != null && !mlcConstituenciesList.isEmpty()) {
	                for (PatientVO vo : mlcConstituenciesList) {
	                    JSONObject obj = new JSONObject();
	                    obj.put("name", vo.getFirstName());
	                    obj.put("id", vo.getConstituencyNo());
	                    obj.put("pin", vo.getPinCode());
	                    jsonArray.put(obj);
	                }
	            }
	            response.getWriter().write(jsonArray.toString());
			}
			//for print page after submit
			if ("lhsRegisteredMembersPrint".equalsIgnoreCase(lStrActionVal))
			{
				PatientVO distPatVO = patientService.getAllDistrictList();
				String lhsRegNo=request.getParameter("lhsRegNo");
				String caseStatus=request.getParameter("caseStatus");
				PatientVO retPatVO = null;
				PatientVO dependentVO = null;
		        List<PatientVO> registeredPatientsList = null;
		        HashMap<String, String> searchMap = new HashMap<String, String>();
		        List<PatientVO> registeredDependentsList = null;
		        if(lhsRegNo != null){
					searchMap.put("patientNumber", lhsRegNo);
					searchMap.put("editLhsMember", "True");
					searchMap.put("status", caseStatus);
				}
				retPatVO = patientService.getRegistredLhsMembersList(searchMap);
				registeredPatientsList = retPatVO.getRegisteredPatList();
				if (registeredPatientsList != null && registeredPatientsList.size() > 0) {
					request.setAttribute("registeredLhsPatientsList", registeredPatientsList.get(0));
					String distId = registeredPatientsList.get(0).getDistrictCode();
					if(distId != null && !distId.trim().isEmpty()) {
					    String lhsMemberDistrict = patientService.getLhsDistrict(distId);
					    if(lhsMemberDistrict != null){
					        request.setAttribute("lhsMemberDistrict", lhsMemberDistrict);
					    }
					}
				}
				dependentVO = patientService.getRegistredLhsDepenentList(searchMap);
                registeredDependentsList = dependentVO.getDependentList();
				if (registeredDependentsList != null && registeredDependentsList.size() > 0) {
					request.setAttribute("registeredDependentsList", registeredDependentsList);
				}
				lStrPageName="LhsRegisteredPatientsPrint";
			}
			if ("updateLhsMemberApproveOrReject".equalsIgnoreCase(lStrActionVal))
			{
				String reason = request.getParameter("rejectReason");
				String lhsRegNo = request.getParameter("lhsRegNo");
				String approve = request.getParameter("lhsApprove");
		        HashMap<String, String> searchMap = new HashMap<String, String>();
		        if(lhsRegNo != null){
					searchMap.put("patientNumber", lhsRegNo);
					searchMap.put("rejectReason", reason);
					searchMap.put("rejOrAprvUsr", lStrUserId);
					searchMap.put("lhsApprove", approve);
				}
				    String result = patientService.updateLhsMemberApproveOrReject(searchMap);
					  response.setContentType("text/plain");
					    PrintWriter out = response.getWriter();
					    out.write(result);
					    out.flush();
					    out.close();
			}
			//second level rejection
			if ("updateLhsCheckerRejection".equalsIgnoreCase(lStrActionVal))
			{
				String reason = request.getParameter("rejectReason");
				String lhsRegNo = request.getParameter("lhsRegNo");
				String approve = request.getParameter("lhsApprove");
		        HashMap<String, String> searchMap = new HashMap<String, String>();
		        if(lhsRegNo != null){
					searchMap.put("patientNumber", lhsRegNo);
					searchMap.put("rejectReason", reason);
					searchMap.put("rejOrAprvUsr", lStrUserId);
				}
				    String result = patientService.updateLhsCheckerRejection(searchMap);
					  response.setContentType("text/plain");
					    PrintWriter out = response.getWriter();
					    out.write(result);
					    out.flush();
					    out.close();
			}
			if ("updateLhsMemberRevert".equalsIgnoreCase(lStrActionVal))
			{
				String reason = request.getParameter("revertReason");
				String lhsRegNo = request.getParameter("lhsRegNo");
		        HashMap<String, String> searchMap = new HashMap<String, String>();
		        if(lhsRegNo != null){
					searchMap.put("patientNumber", lhsRegNo);
					searchMap.put("revertReason", reason);
					searchMap.put("rejOrAprvUsr", lStrUserId);
				}
				    String result = patientService.updateLhsMemberRevert(searchMap);
					  response.setContentType("text/plain");
					    PrintWriter out = response.getWriter();
					    out.write(result);
					    out.flush();
					    out.close();
			}
			if ("getAllDentalConditions".equalsIgnoreCase(lStrActionVal))
			{
				String icdProcCode=request.getParameter("icdProcCode");
				String patientId=request.getParameter("patientId");
				String patSchemeId=request.getParameter("patSchemeId");
				String deleteProc = request.getParameter("deleteProc");
				String ajaxMsg=null;
				if(icdProcCode!=null && patientId!=null)	
					{
						PreauthVO patCondVO=new PreauthVO();
						PreauthVO patResCondVO=new PreauthVO();
						patCondVO.setPatientID(patientId);
						patCondVO.setIcdProcCode(icdProcCode);
						patCondVO.setScheme(patSchemeId);
						patCondVO.setTestKnown(deleteProc);
						String dentalTGSpecialCond=config.getString("dentalTGSpecialCond");
						String[] specialStatus=dentalTGSpecialCond.split("~");
						for(String status:specialStatus)
							{
								if(status!=null && !"".equalsIgnoreCase(status) && status.equalsIgnoreCase(icdProcCode))
									{
										ajaxMsg=patientService.checkSpecialDenatlCond(patCondVO);
									}
								if(ajaxMsg!=null && ajaxMsg.length()>0)
									ajaxMsg="AlertCont~~~"+ajaxMsg;
								else
									ajaxMsg=null;
							}
						if(deleteProc!=null && !"".equalsIgnoreCase(deleteProc) && "Y".equalsIgnoreCase(deleteProc))
							ajaxMsg=null;
						if(ajaxMsg==null || (ajaxMsg!=null && ajaxMsg.length()<1))
							{
								patResCondVO=patientService.validateAndGetDentalCond(patCondVO);
								if(patResCondVO!=null)
									{	
										Gson gson=new Gson();
										ajaxMsg=gson.toJson(patResCondVO);
									}			
							}
					}
				request.setAttribute("AjaxMessage", ajaxMsg);
				lStrPageName="ajaxResult";
			}
			if ("uploadAtach".equalsIgnoreCase(lStrActionVal))
			{
				String patientId = request.getParameter("patientId");
				EhfPatient ehfPatient=null;
				ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				SimpleDateFormat sdfp=new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdfs=new SimpleDateFormat("dd/MM/yyyy");
				String patCrtDt=sdfp.format(ehfPatient.getCrtDt());	
				FormFile lFormFile=null;
				String lFileName=null;
				int lCount=0;
				String lDir=null;
				String lFileExt=null;
				long ltime = ldtToday.getTime();
				Date date = new Date();
				String crtDate=sdfs.format(date);
				Date crtDt = sdfs.parse((sdfw.format(date)));
				String lStrTotFileName=null;
				String code = request.getParameter("attachType");
				String path = request.getParameter("path");
				PatientVO patVo=new PatientVO();
				Long leaveSeqVal=0L;
				leaveSeqVal = Long.parseLong(patientService.getLeaveSequence("AIS_SN_NO"));
				patVo.setPatientId(patientId);
				patVo.setEmpCode(code);
				patVo.setCaseRegnDate(crtDt);
				patVo.setCrtUsr(lStrUserId);
				patVo.setLeaveId(leaveSeqVal.toString());
				lFormFile = patientForm.getUpload();
				lFileName=lFormFile.getFileName();
				if(lFileName!=null && !lFileName.equals(""))
				{
					lCount=lFileName.lastIndexOf(".");
					lFileExt= lFileName.substring(lCount + 1, lFileName.length()); 
					// save file in folder's
					String lStrSharePath = 
							config.getString("STORAGE_BOX")  + patCrtDt +
							config.getString("SLASH_VALUE") + patientId + 
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
						patVo.setFileName(lFileName);
						patVo.setFilePath(lDir);
					}
					catch(Exception e) {
						GLOGGER.error ( "Exception occurred using saveCaseDetails actionFlag in PatientAction." +e.getMessage()) ;
					}
				}
				String msgNew=patientService.saveAttach(patVo);
				/*String ayushValues="";
				ayushValues=patientService.getTherapyOrAyushNew(patientId);
				List<LabelBean> attachList=patientService.getAisAttach(patientId);
				List<String> list = new ArrayList<String>();
				if(ayushValues!=null && !"".equalsIgnoreCase(ayushValues))
				{
				if(attachList!=null && attachList.size()==16 && ayushValues!=null && !"".equalsIgnoreCase(ayushValues) && "MHC".equalsIgnoreCase(ayushValues))
				//session.setAttribute("relationList",attachList);
				{
					for(LabelBean lab:attachList)
					{
						list.add(lab.getVALUE());
					}
				}
				else if(attachList!=null && attachList.size()==23 && ayushValues!=null && !"".equalsIgnoreCase(ayushValues) && "EHC".equalsIgnoreCase(ayushValues))
				{
					for(LabelBean lab:attachList)
					{
						list.add(lab.getVALUE());
					}
				}
				else if(attachList!=null && attachList.size()==29 && ayushValues!=null && !"".equalsIgnoreCase(ayushValues) && "LHC".equalsIgnoreCase(ayushValues))
				{
					for(LabelBean lab:attachList)
					{
						list.add(lab.getVALUE());
					}
				}
				if(list!=null && list.size()>0)
				{
				if(msg!=null && !"".equalsIgnoreCase(msg) && "Y".equalsIgnoreCase(msg))
				{
				request.setAttribute("ais", msg);
				patientForm.setAttachName(patVo.getFileName());
				patientForm.setAttachPath(patVo.getFilePath());
					if (config.getBoolean("EmailRequired")) 
				{
					String mailId=null;
					if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
					{
						mailId=ehfPatient.getEmailId();
						String[] emailToArray = {mailId};
						EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("patientEmailSubject"));
						Map<String, String> model = new HashMap<String, String>();
						Map<String, List<String>> model1 = new HashMap<String, List<String>>();
						model.put("patientName", ehfPatient.getName());
							model.put("registeredHosp", "NIMS");
						model.put("status", "Patient Registered");
						model.put("statusDate",crtDate);
						model1.put("lStrFileName",list);
						model.put("Filename",patVo.getFilePath());
										emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
										commonService.generateMailNew(emailVO, model,model1);
					}
				}
				}
				}*/
				 if(msgNew!=null && !"".equalsIgnoreCase(msgNew) && "Y".equalsIgnoreCase(msgNew))
				{
					request.setAttribute("ais", msgNew);
					patientForm.setAttachName(patVo.getFileName());
					patientForm.setAttachPath(patVo.getFilePath());
				}
				//}
				lStrPageName="uploadAttachment";
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
					{
					patientForm.setEnablePharma(ehfPatient.getPharmaView());
					}
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
				  								drugsVO.setQuantity(drugValues[2]);
				  							}
				  					drugsList.add(drugsVO);
				  				}
				  				}
				  				patientVO.setDrugs(drugsList);
				  				String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
				  				patientVO.setDispCode(dispId);
				  			String patCaseId=patientService.savePharmaDrugs(patientVO);
				  			if(patCaseId!=null && patCaseId.equalsIgnoreCase(caseId))
				  			{
				  				PreauthVO smsDrugs=patientService.getPatientFullDtlsPharma(patientId);
				  				String contactNo=patientService.getPatientContactNo(patientId);
					  			if(smsDrugs!=null)	
								{	
									if("true".equalsIgnoreCase(config.getString("SmsRequired")))
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
											if((!"".equalsIgnoreCase(String.valueOf(ehfPatient.getContactNo())) &&  String.valueOf(ehfPatient.getContactNo())!=null) &&(contactNo!=null&&!"".equalsIgnoreCase(contactNo)))
											{
												templateId="1407161769869456024";
											SMSServices SMSServicesobj = new SMSServices();
												String msgNew= "Dear "+ehfPatient.getName().trim()+" ,Following drugs have been issued : "+drugs+"\n\nAHCT, Govt. of Telangana";
											if(String.valueOf(ehfPatient.getContactNo()).equals(contactNo))
											{
												SMSServicesobj.sendSingleSMS(msgNew,contactNo,templateId);	
											}
											else
											{
												SMSServicesobj.sendSingleSMS(msgNew,String.valueOf(ehfPatient.getContactNo()),templateId);
												SMSServicesobj.sendSingleSMS(msgNew,contactNo,templateId);	
											}
											}
									 }
								}
				  				patientForm.setMsg("Drugs Issued Successfully.");
				  			}
				  			else
				  			{
				  				patientForm.setMsg("Drugs Cannot be Issued");
				  			}
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
			if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getLowIndentReportNew"))
			{   
				String drugId=patientForm.getDispDrugID();
			    String drugType=patientForm.getDrugTypeID();
			    String dispname2=patientForm.getDispname();
			    String yearFlag=request.getParameter("yearFlag");
			    String indentedStock = request.getParameter("indentedStock");
			    String lowStock = request.getParameter("lowStock");
			    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
				request.setAttribute("dispDrugTypeList", drugsTypeList);
				List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname2);
				request.setAttribute("dispDrugsList", drugsList);
				request.setAttribute("yearFlag", yearFlag);
				request.setAttribute("indentedStock", indentedStock);
				request.setAttribute("lowStock", lowStock);
				return mapping.findForward("lowStock");
			}
			if ("viewDTOpage".equalsIgnoreCase(lStrActionVal))
			{
				EhfPatient ehfPatient=null;
				String patientId=request.getParameter("patientId");
				if(patientId!=null && !"".equalsIgnoreCase(patientId))
				{
					patientForm.setPatientNo(patientId);
					ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
					session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
					patientForm.setCardNo(ehfPatient.getCardNo());
					patientForm.setFname(ehfPatient.getName());
					patientForm.setEnablePharma(ehfPatient.getPharmaView());
					if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Male");
					}
					else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Female");
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
					if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
						List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
						request.setAttribute("treatHist", caseHist);
						patientForm.setLstCaseSearch(caseHist);
						}
					List<LabelBean> investigationsList=null;
					String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
					investigationsList=commonService.getGeneralInvest(patientId,dispId);//sai:API Integration Main.
						if(investigationsList!=null && investigationsList.size()>0)
							request.setAttribute("investigationsList", investigationsList);
					List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
					request.setAttribute("dispDrugTypeList", drugsTypeList);
					String dispCode=ehfPatient.getDispCode();
					String splstGroup=ehfPatient.getSpecialityType();
					String cardNo = ehfPatient.getCardNo();
					List<LabelBean> doctorNames=patientService.getDoctorNames(dispCode,splstGroup);
					//pravalika
					List<LabelBean> drugsDetails= patientService.getDrugDtls(cardNo);
					request.setAttribute("dispDoctorNames", doctorNames);
					request.setAttribute("drugList", drugsDetails);
					lStrPageName="dtoDrugsPage";
				}
				else
				{
					lStrPageName="patientNew";
				}
			}
			//Sai:API Integration.
			if ("viewTdLabInvestgestions".equalsIgnoreCase(lStrActionVal)){
				EhfPatient ehfPatient=null;
				String patientId=request.getParameter("patientId");
				if(patientId!=null && !"".equalsIgnoreCase(patientId))
				{
					patientForm.setPatientNo(patientId);
					ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
					session.setAttribute("patCrtdt",ehfPatient.getCrtDt());
					patientForm.setCardNo(ehfPatient.getCardNo());
					patientForm.setFname(ehfPatient.getName());
					SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
					SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					Date date = parser.parse((ehfPatient.getCrtDt()).toString());
					String output = formatter.format(date);
					patientForm.setDateOfBirth(output);
					patientForm.setEnablePharma(ehfPatient.getPharmaView());
					if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Male");
					}
					else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Female");
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
					List<PatientVO> labDtls = null;
					 PatientVO result = patientService.getSavedIPLab(patientId);
					 labDtls= result.getLabDtlsList();
					  if (labDtls != null && labDtls.size() > 0)
					  {
						  request.setAttribute("labDetails", labDtls);
					  }
			        request.setAttribute("patientId", patientId);
			    }
			    lStrPageName = "viewTdLabInvestgestions";
			}
				if ("savePharmacydrugsNimsPick".equalsIgnoreCase(lStrActionVal))
			{
				EhfAisDrugs ehfPatient=null;
				String contactNum="";
				String patientId=patientForm.getPatientNo();
				String pickupName=request.getParameter("pickupName");
				String pickupNum=request.getParameter("pickupNum");
				if(patientId!=null && !"".equalsIgnoreCase(patientId))
				{
					patientForm.setPatientNo(patientId);
					ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
					if(ehfPatient.getEmployeeNo()!=null && !"".equalsIgnoreCase(ehfPatient.getEmployeeNo()))
					{
					contactNum=ehfPatient.getContactNo();
					}
				}
				if(ehfPatient.getCaseStatus()!=null && "A".equalsIgnoreCase(ehfPatient.getCaseStatus()))
				{
			  String saveFlg=request.getParameter("saveFlg");
			  String caseId=patientForm.getPatientNo();
			  if(caseId!=null && !"".equalsIgnoreCase(caseId))
			  {
				  patientVO = new PatientVO();
				  patientVO.setCaseId(caseId);
				  patientVO.setCrtUsr(lStrUserId);
				  patientVO.setPatientId(caseId);
				  patientVO.setState(saveFlg);
				  patientVO.setRoleId(roleId);
				  patientVO.setPickupName(pickupName);
				  patientVO.setPickupNum(pickupNum);
				  				String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
				  				patientVO.setDispCode(dispId);
					  			String patCaseId=patientService.savePharmaDrugsPick(patientVO);
				  			if(caseId!=null && !"".equalsIgnoreCase(caseId))
				  			{
				  				PreauthVO smsDrugs=patientService.getPatientFullDtlsPharma(patientId);
				  				//String contactNo=patientService.getPatientDetailsAisCnt(contactNum);
					  			if(smsDrugs.getDrugList()!=null)	
								{	
									if("true".equalsIgnoreCase(config.getString("SmsRequired")))
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
											if(contactNum!=null&&!"".equalsIgnoreCase(contactNum))
											{
											SMSServices SMSServicesobj = new SMSServices();
											String msg1= "Medicines issued to "+pickupName+" ";
												SMSServicesobj.sendSingleSMS(msg1,contactNum,null);	
											}
									 }
								}
				  				patientForm.setMsg("Drugs Issued Successfully.");
				  			}
				  			else
				  			{
				  				patientForm.setMsg("Drugs Cannot be Issued");
				  			}
				  			request.setAttribute("fromPharmaPick", "Y");
				  			lStrPageName="patient";
			  }
				}
				else 
				{
					patientForm.setMsg("Drugs has already been issued for this case");
				    lStrPageName="hideRegisteredPatientsDisp";
				}	
			}
				//Tejasri- CR-8812 Wellness Centers - Attendance dashboard to Wellness Centre Admin
			if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getWellnessCentreList"))
				{
					//String dispId = request.getParameter("dispId");			
					List<LabelBean> wellnessCentreList = patientService.wellnessCentreList(dispname);
					request.setAttribute("wellnessCentreList",wellnessCentreList);
					return mapping.findForward("WellnessCentreList");
			   }
			   if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("EmployeeWiseWellnessDtls"))
				{  
				   String dsgId = request.getParameter("wfType");
				   String fromDate = request.getParameter("fromDate");
				   String toDate = request.getParameter("toDate");
				   System.out.println("System "+dsgId);
				   String reqType = request.getParameter("reqType");
				   List<LabelBean> EmployeeList = patientService.EmployeeList(dsgId,reqType,fromDate,toDate);
					    Gson gson = new Gson();
					    String gsonString = gson.toJson(EmployeeList); 
					    request.setAttribute("AjaxMessage", gsonString);
					    return mapping.findForward("ajaxResult");
			   }
			 if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("SearchByWellnessCenter"))
				{  
				   String dsgId = request.getParameter("wfType");
				   String fromDate = request.getParameter("fromDate");
				   //String toDate = request.getParameter("toDate");
				   String reqType = request.getParameter("reqType");
				   List<LabelBean> SearchList = patientService.SearchList(reqType,fromDate,dsgId,dispname);
					    Gson gson = new Gson();
					    String gsonString = gson.toJson(SearchList); 
					    request.setAttribute("AjaxMessage", gsonString);
					    return mapping.findForward("ajaxResult");
			   }
			 
			if ("savePharmacydrugsNims".equalsIgnoreCase(lStrActionVal))
			{
				EhfAisDrugs ehfPatient=null;
				String contactNum="";
				String patientId=patientForm.getPatientNo();
				if(patientId!=null && !"".equalsIgnoreCase(patientId))
				{
					patientForm.setPatientNo(patientId);
					ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
					if(ehfPatient.getEmployeeNo()!=null && !"".equalsIgnoreCase(ehfPatient.getEmployeeNo()))
					{
					contactNum=ehfPatient.getContactNo();
					}
				}
				if(ehfPatient.getCaseStatus()!=null && "S".equalsIgnoreCase(ehfPatient.getCaseStatus()))
				{
			  String saveFlg=request.getParameter("saveFlg");
			  String caseId=patientForm.getPatientNo();
			  if(caseId!=null && !"".equalsIgnoreCase(caseId))
			  {
				  patientVO = new PatientVO();
				  patientVO.setCaseId(caseId);
				  patientVO.setCrtUsr(lStrUserId);
				  patientVO.setPatientId(caseId);
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
				  								drugsVO.setQuantity(drugValues[2]);
				  							}
				  					drugsList.add(drugsVO);
				  				}
				  				}
				  				patientVO.setDrugs(drugsList);
				  				String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
				  				patientVO.setDispCode(dispId);
				  			String patCaseId=patientService.savePharmaDrugsNims(patientVO);
				  			if(patCaseId!=null && patCaseId.equalsIgnoreCase(caseId))
				  			{
				  				PreauthVO smsDrugs=patientService.getPatientFullDtlsPharma(patientId);
				  				//String contactNo=patientService.getPatientDetailsAisCnt(contactNum);
					  			if(smsDrugs.getDrugList()!=null)	
								{	
									if("true".equalsIgnoreCase(config.getString("SmsRequired")))
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
											if(contactNum!=null&&!"".equalsIgnoreCase(contactNum))
											{
											SMSServices SMSServicesobj = new SMSServices();
											String msg1= "Medicines issued,Please collect";
												SMSServicesobj.sendSingleSMS(msg1,contactNum,null);	
											}
									 }
								}
				  				patientForm.setMsg("Drugs Issued Successfully.");
				  			}
				  			else
				  			{
				  				patientForm.setMsg("Drugs Cannot be Issued");
				  			}
				  			request.setAttribute("fromPharmaNims", "Y");
				  			lStrPageName="patient";
			  }
				}
				else 
				{
					patientForm.setMsg("Drugs has already been issued for this case");
				    lStrPageName="hideRegisteredPatientsDisp";
				}	
			}
			if ("viewDTOpageNims".equalsIgnoreCase(lStrActionVal))
			{
				EhfAisDrugs ehfPatient=null;
				String patientId=request.getParameter("patientId");
				String casesViewFlagNew=request.getParameter("casesViewFlagNew");
				String caseStatusAis=request.getParameter("caseStatus");
				if(casesViewFlagNew!=null && "Y".equalsIgnoreCase(casesViewFlagNew))
				{
					request.setAttribute("viewFl", "Y");
				}
				else
				{
					request.setAttribute("viewFl", "N");
				}
				if(patientId!=null && !"".equalsIgnoreCase(patientId))
				{
					patientForm.setPatientNo(patientId);
					ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
					/*session.setAttribute("patCrtdt",ehfPatient.getCrtDt());*/
					patientForm.setCardNo(ehfPatient.getEmployeeNo());
					patientForm.setFname(ehfPatient.getEmpName());
					if(ehfPatient.getPickLocation()!=null && "A".equalsIgnoreCase(ehfPatient.getPickLocation()))
					{
						patientForm.setPickupName("IAS Association, Begumpet");
					}
					else if(ehfPatient.getPickLocation()!=null && "P".equalsIgnoreCase(ehfPatient.getPickLocation()))
					{
						patientForm.setPickupName("Prasashan Nagar Clinic");
					}
					//patientForm.setPickupName(ehfPatient.getPickLocation());
					//added by bhaskar for attachmentList
					List<LabelBean> attachList=patientService.getAttachList(patientId);
					request.setAttribute("attachList", attachList);
					List<LabelBean> attachListPr=patientService.getAttachListPr(patientId);
					request.setAttribute("attachListPr", attachListPr);
					//end by bhaskar for attachmentsList
				/*	patientForm.setEnablePharma(ehfPatient.getPharmaView());*/
				/*	if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Male");
					}
					else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Female");
					}*/
					/*if(ehfPatient.getAge()!=null)
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
					}*/
					/*if(ehfPatient.getAgeMonths()!=null)
					{
						patientForm.setMonths(ehfPatient.getAgeMonths().toString());
					}
					if(ehfPatient.getAgeDays()!=null)
					{
						patientForm.setDays(ehfPatient.getAgeDays().toString());
					}*/
					/*if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
						List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
						request.setAttribute("treatHist", caseHist);
						patientForm.setLstCaseSearch(caseHist);
						}*/
					/*List<LabelBean> investigationsList=null;
						investigationsList=commonService.getGeneralInvest(patientId);
						if(investigationsList!=null && investigationsList.size()>0)
							request.setAttribute("investigationsList", investigationsList);*/
					List<LabelBean> drugsTypeList=patientService.getDrugTypeListAis();
					request.setAttribute("dispDrugTypeList", drugsTypeList);
					request.setAttribute("roleIdNims", roleId);
					/*String dispCode=ehfPatient.getDispCode();
					String splstGroup=ehfPatient.getSpecialityType();
					*/
					/*List<LabelBean> doctorNames=patientService.getDoctorNames(dispCode,splstGroup);
					request.setAttribute("dispDoctorNames", doctorNames);*/
					lStrPageName="dtoDrugsPageNew";
				}
				else
				{
					lStrPageName="patient";
				}
			}
						if ("ViewRegisteredPatientsNims".equalsIgnoreCase(lStrActionVal))
			{
				String oldCase="";
				List<LabelBean> hospitalList= null;
				fromDisp = request.getParameter("fromDispnsry");
				String stateType=request.getParameter("stateType");
				String viewFlag=request.getParameter("vieFlag");
			if(roleId!=null && "GP702".equalsIgnoreCase(roleId))
			{
				request.setAttribute("fromPharma","Y");
			}
			else if(roleId!=null && "GP703".equalsIgnoreCase(roleId))
			{
				request.setAttribute("fromPo","Y");
			}
			else if(roleId!=null && "GP1256".equalsIgnoreCase(roleId))
			{
				request.setAttribute("fromPick","Y");
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
					searchMap.put("fromDisp",fromDisp);
					searchMap.put("roleId",roleId);
					searchMap.put("medcoId",lStrUserId);
					searchMap.put("viewFlag",viewFlag);
					request.setAttribute("fromDisp",fromDisp);
					patientForm.setFromDisp(fromDisp);
					request.setAttribute("casesViewFlag", viewFlag);
				retPatVO=patientService.getRegisteredPatientsNims(searchMap);
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
				/*if(patientForm.getPatientNo()!=null && !"".equalsIgnoreCase(patientForm.getPatientNo()))
				{
					String patientId = patientForm.getPatientNo();
					EhfPatient ehfPatient=null;
					ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
					if(ehfPatient.getPharmaView()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmaView()))
					patientForm.setEnablePharma(ehfPatient.getPharmaView());
					request.setAttribute("enablePharma",patientForm.getEnablePharma());
				}*/
				request.setAttribute("saveMsg", msg);
					lStrPageName="viewRegisteredPatientsNim";	
			}
				 if ("viewPharmacyPageNimsSearch".equalsIgnoreCase(lStrActionVal))
				{
					EhfAisDrugs ehfPatient=null;
					String patientId=request.getParameter("patientId");
					String casesViewFlagNew=request.getParameter("casesViewFlagNew");
					String caseStatusAis=request.getParameter("caseStatus");
					if(casesViewFlagNew!=null && "Y".equalsIgnoreCase(casesViewFlagNew))
					{
						request.setAttribute("viewFl", "Y");
					}
					else
					{
						request.setAttribute("viewFl", "N");
					}
					//String caseId=patientForm.getCaseId();
					patientVO = new PatientVO();
					if(patientId!=null && !"".equalsIgnoreCase(patientId))
					{
						patientForm.setPatientNo(patientId);
						ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
						patientForm.setCardNo(ehfPatient.getEmployeeNo());
						patientForm.setFname(ehfPatient.getEmpName());
						if(ehfPatient.getPickLocation()!=null && "A".equalsIgnoreCase(ehfPatient.getPickLocation()))
						{
							patientForm.setPickLocation("IAS Association, Begumpet");
						}
						else if(ehfPatient.getPickLocation()!=null && "P".equalsIgnoreCase(ehfPatient.getPickLocation()))
						{
							patientForm.setPickLocation("Prasashan Nagar Clinic");
						}
						List<LabelBean> attachList=patientService.getAttachList(patientId);
						request.setAttribute("attachList", attachList);
						List<LabelBean> attachListPr=patientService.getAttachListPr(patientId);
						request.setAttribute("attachListPr", attachListPr);
						//patientForm.setEnablePharma(ehfPatient.getPharmaView());
						/*if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
						{
							patientForm.setGender("Male");
						}*/
						/*else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
						{
							patientForm.setGender("Female");
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
						}*/
						//Drug Details
						patientVO.setCaseId(patientId);
						patientVO.setPatientId(patientId);
						String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
						patientVO.setDispCode(dispId);
						/*if(ehfPatient.getPharmDrugSave()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmDrugSave()))
						{
							patientVO.setSaveFlag(ehfPatient.getPharmDrugSave());
							request.setAttribute("saveFlag", "Y");
						}
						else
						{
							patientVO.setSaveFlag("N");
							request.setAttribute("saveFlag", "N");
						}*/
				/*		if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
							List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
							request.setAttribute("treatHist", caseHist);
							patientForm.setLstCaseSearch(caseHist);
							}
						*/
						if(caseStatusAis!=null && ("P".equalsIgnoreCase(caseStatusAis) || "S".equalsIgnoreCase(caseStatusAis)))
						{
						List<DrugsVO> drugsList=patientService.getPharmDrugDtls(patientVO);
						patientForm.setDrugLt(drugsList);
						if(drugsList.size()>0)
						{
							request.setAttribute("deoViewFlg", "N");
						}
						else
						{
							request.setAttribute("deoViewFlg", "Y");
						}
						String values="";
						 if(drugsList!=null && drugsList.size()>0)
							{
								for(DrugsVO lb:drugsList)
								{
									if(lb!=null && lb.getDRUGSUBTYPECODE()!=null && !"".equalsIgnoreCase(lb.getDRUGSUBTYPECODE()))
									{
										values=values+lb.getDRUGSUBTYPECODE()+"~";
									}
								}
								values=values.substring(0,values.length()-1);
							}
							System.out.println(values);
							request.setAttribute("values", values);
						}
						else if(caseStatusAis!=null && ("A".equalsIgnoreCase(caseStatusAis) || "I".equalsIgnoreCase(caseStatusAis))) 
						{
						List<DrugsVO> drugsList=patientService.getPharmDrugDtlsPick(patientVO);
						patientForm.setDrugLt(drugsList);
						String values="";
						 if(drugsList!=null && drugsList.size()>0)
							{
								for(DrugsVO lb:drugsList)
								{
									if(lb!=null && lb.getDRUGSUBTYPECODE()!=null && !"".equalsIgnoreCase(lb.getDRUGSUBTYPECODE()))
									{
										values=values+lb.getDRUGSUBTYPECODE()+"~";
									}
								}
								values=values.substring(0,values.length()-1);
							}
							System.out.println(values);
							request.setAttribute("values", values);
							if(drugsList.size()>0)
							{
								request.setAttribute("deoViewFlg", "N");
							}
							else
							{
								request.setAttribute("deoViewFlg", "Y");
							}
						}
						/*if(drugsList.size()>0)
						{
							request.setAttribute("deoViewFlg", "N");
						}
						else
						{
							request.setAttribute("deoViewFlg", "Y");
						}*/
						List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
						request.setAttribute("dispDrugTypeList", drugsTypeList);
						//String details=drugsTypeList.getLeaveConsultName()+"@"+patientVo2.getLeaveConsultMob()+"@"+patientVo2.getLeaveWellName();
						if(patientForm.getEnablePharma()!=null && !"".equalsIgnoreCase(patientForm.getEnablePharma()))
						{
						String enablePharma=patientForm.getEnablePharma();
						request.setAttribute("enablePharma", enablePharma);
						}
							lStrPageName="pharmacyDrgsPgePharmaPick";
					/*	else if(caseStatusAis==null || "P".equalsIgnoreCase(caseStatusAis))
						{
							lStrPageName="pharmacyDrgsPgeNew";
						}
						else if(caseStatusAis==null || "S".equalsIgnoreCase(caseStatusAis))
						{
							lStrPageName="pharmacyDrgsPgePharma";
						}
						else if(caseStatusAis==null || "A".equalsIgnoreCase(caseStatusAis))
						{
							lStrPageName="pharmacyDrgsPgePharmaPick";
						}
						else if(caseStatusAis==null || "I".equalsIgnoreCase(caseStatusAis))
						{
							lStrPageName="pharmacyDrgsPgePharmaPick";
						}*/
						//lStrPageName="pharmacyDrgsPgePharmaPick";
					}
					else
					{
						lStrPageName="patient";
					}
				}
					 if(lStrActionVal!= null && "saveDTOdrugsNimsPhr".equals(lStrActionVal)) 
		     {
				  String caseId=request.getParameter("patientId");
				  String drugsNew=request.getParameter("drugs");
				  if(caseId!=null && !"".equalsIgnoreCase(caseId))
				  {
					  patientVO = new PatientVO();
					  //patientVO.setCaseId(caseId);
					  patientVO.setCrtUsr(lStrUserId);
					  patientVO.setRoleId(roleId);
					  patientVO.setPatientId(caseId);
					  List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
					  if(drugsNew!=null && !drugsNew.equalsIgnoreCase("")){
					  				String s=drugsNew.substring(0, patientForm.getDrugs().length()-1);
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
					  							 	String newId="";
					  							 	if(drugValues[2]!=null && !"NA".equalsIgnoreCase(drugValues[2]))
					  							 	{
					  							 		 newId=patientService.getsaveNewDrug(drugValues[0],drugValues[2],lStrUserId);
					  							 	}
					  							 	if(drugValues[2]!=null && !"NA".equalsIgnoreCase(drugValues[2]))
					  							 	{
					  								drugsVO.setDrugSubTypeCode(newId);
					  							 	}
					  							 	else
					  							 	{
					  							 		drugsVO.setDrugSubTypeCode(drugValues[1]);
					  							 	}
					  								drugsVO.setDosage(drugValues[3]);
					  								drugsVO.setMedicationPeriod(drugValues[4]);
					  								drugsVO.setQuantity(drugValues[5]);
					  							}
					  							Long drugSeqNextVal = Long.parseLong(patientService.getSequence("OP_DRUG_ID"));
					  					drugsVO.setDrugId(drugSeqNextVal);
					  					drugsList.add(drugsVO);
					  				}
					  				}
					  				patientVO.setDrugs(drugsList);
					  			String patCaseId=patientService.saveDocDrugsNew(patientVO);
					  			if(patCaseId!=null && patCaseId.equalsIgnoreCase(caseId))
					  			{
					  				patientForm.setMsg("Drugs Issued Successfully.");
					  			}
					  			else
					  			{
					  				patientForm.setMsg("Drugs Cannot be Issued");
					  			}
					  		   request.setAttribute("AjaxMessage", "Y");
								return mapping.findForward("ajaxResult");
				  }
		     }
			 	 if(lStrActionVal!= null && "saveDrugDetailsNims".equals(lStrActionVal)) 
		     {
	                String patientId=request.getParameter("patientId");
	                String drugId=request.getParameter("id");
                    String userRole=lStrUserId;
				    String lStrCaseId=patientService.saveMedicalCaseDetails(patientId,drugId,userRole);
		            request.setAttribute("AjaxMessage", "Y");
					return mapping.findForward("ajaxResult");
		     }
			 	if ("viewPharmacyPageNims".equalsIgnoreCase(lStrActionVal))
			{
				EhfAisDrugs ehfPatient=null;
				String patientId=request.getParameter("patientId");
				String casesViewFlagNew=request.getParameter("casesViewFlagNew");
				String caseStatusAis=request.getParameter("caseStatus");
				if(casesViewFlagNew!=null && "Y".equalsIgnoreCase(casesViewFlagNew) && caseStatusAis!=null && "P".equalsIgnoreCase(caseStatusAis))
				{
					request.setAttribute("viewFl", "Y");
				}
				else
				{
					request.setAttribute("viewFl", "N");
				}
				String partialFlg=patientService.getpartialFlg(patientId);
				if(partialFlg!=null && !"".equalsIgnoreCase(partialFlg))
				{
					request.setAttribute("partialFlg", partialFlg);
				}
				//String caseId=patientForm.getCaseId();
				patientVO = new PatientVO();
				if(patientId!=null && !"".equalsIgnoreCase(patientId))
				{
					patientForm.setPatientNo(patientId);
					ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
					patientForm.setCardNo(ehfPatient.getEmployeeNo());
					patientForm.setFname(ehfPatient.getEmpName());
					//patientForm.setPickupName(ehfPatient.getPickLocation());
					if(ehfPatient.getPickLocation()!=null && "A".equalsIgnoreCase(ehfPatient.getPickLocation()))
					{
						patientForm.setPickupName("IAS Association, Begumpet");
					}
					else if(ehfPatient.getPickLocation()!=null && "P".equalsIgnoreCase(ehfPatient.getPickLocation()))
					{
						patientForm.setPickupName("Prasashan Nagar Clinic");
					}
					List<LabelBean> attachList=patientService.getAttachList(patientId);
					request.setAttribute("attachList", attachList);
	                List<LabelBean> attachListPr=patientService.getAttachListPr(patientId);
					request.setAttribute("attachListPr", attachListPr);
					//patientForm.setEnablePharma(ehfPatient.getPharmaView());
					/*if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Male");
					}
					else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
					{
						patientForm.setGender("Female");
					}*/
				/*	
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
					}*/
					/*if(ehfPatient.getAgeMonths()!=null)
					{
						patientForm.setMonths(ehfPatient.getAgeMonths().toString());
					}
					if(ehfPatient.getAgeDays()!=null)
					{
						patientForm.setDays(ehfPatient.getAgeDays().toString());
					}*/
					//Drug Details
					patientVO.setCaseId(patientId);
					patientVO.setPatientId(patientId);
					String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
					patientVO.setDispCode(dispId);
					/*if(ehfPatient.getPharmDrugSave()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmDrugSave()))
					{
						patientVO.setSaveFlag(ehfPatient.getPharmDrugSave());
						request.setAttribute("saveFlag", "Y");
					}
					else
					{
						patientVO.setSaveFlag("N");
						request.setAttribute("saveFlag", "N");
					}*/
				/*	if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
						List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
						request.setAttribute("treatHist", caseHist);
						patientForm.setLstCaseSearch(caseHist);
						}*/
					List<DrugsVO> drugsList=patientService.getPharmDrugDtls(patientVO);
					if(drugsList.size()>0)
					{
						request.setAttribute("deoViewFlg", "N");
					}
					else
					{
						request.setAttribute("deoViewFlg", "Y");
					}
					List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
					request.setAttribute("dispDrugTypeList", drugsTypeList);
					patientForm.setDrugLt(drugsList);
					if(patientForm.getEnablePharma()!=null && !"".equalsIgnoreCase(patientForm.getEnablePharma()))
					{
					String enablePharma=patientForm.getEnablePharma();
					request.setAttribute("enablePharma", enablePharma);
					}
					request.setAttribute("roleIdNims", roleId);
					lStrPageName="pharmacyDrgsPgeNew";
				}
				else
				{
					lStrPageName="patient";
				}
			}
						if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getDrugListAjaxNims" ) )
			{
				String drugType=request.getParameter("drugType");
				List<LabelBean> drugsList=null;
				List<String> drugs=new ArrayList<String>();
				//String dispId="DISP14";
				String dispId=config.getString("DispID");
				try{
					drugsList=patientService.getDispDrugList(drugType,dispId);
					}
					catch(Exception e)
					{
//						GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
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
				//For Adding Others at the end.
				if(temp.getID()!=null && temp.getVALUE()!=null)
				{
					drugs.add(temp.getID()+ "~" + temp.getVALUE());
				}
				GLOGGER.info ( "Retrieving Drugs List using Ajax Call in PatientAction." ) ;
				//doctorsList.add("0~Others");
			 if(drugs!=null && drugs.size() > 0)
				request.setAttribute("AjaxMessage", drugs);
				lStrPageName="ajaxResult";
			}
						 if ("viewPharmacyPageNimsNewPick".equalsIgnoreCase(lStrActionVal))
				{
					EhfAisDrugs ehfPatient=null;
					String patientId=request.getParameter("patientId");
					String casesViewFlagNew=request.getParameter("casesViewFlagNew");
					String caseStatusAis=request.getParameter("caseStatus");
					if(casesViewFlagNew!=null && "Y".equalsIgnoreCase(casesViewFlagNew) && caseStatusAis!=null && "P".equalsIgnoreCase(caseStatusAis))
					{
						request.setAttribute("viewFl", "Y");
					}
					else
					{
						request.setAttribute("viewFl", "N");
					}
					//String caseId=patientForm.getCaseId();
					patientVO = new PatientVO();
					if(patientId!=null && !"".equalsIgnoreCase(patientId))
					{
						patientForm.setPatientNo(patientId);
						ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
						patientForm.setCardNo(ehfPatient.getEmployeeNo());
						patientForm.setFname(ehfPatient.getEmpName());
						if(ehfPatient.getPickLocation()!=null && "A".equalsIgnoreCase(ehfPatient.getPickLocation()))
						{
							patientForm.setPickLocation("IAS Association, Begumpet");
						}
						else if(ehfPatient.getPickLocation()!=null && "P".equalsIgnoreCase(ehfPatient.getPickLocation()))
						{
							patientForm.setPickLocation("Prasashan Nagar Clinic");
						}
						//patientForm.setPickLocation(ehfPatient.getPickLocation());
						List<LabelBean> attachList=patientService.getAttachList(patientId);
						request.setAttribute("attachList", attachList);
						List<LabelBean> attachListPr=patientService.getAttachListPr(patientId);
						request.setAttribute("attachListPr", attachListPr);
						//patientForm.setEnablePharma(ehfPatient.getPharmaView());
						/*if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
						{
							patientForm.setGender("Male");
						}*/
						/*else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
						{
							patientForm.setGender("Female");
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
						}*/
						//Drug Details
						patientVO.setCaseId(patientId);
						patientVO.setPatientId(patientId);
						String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
						patientVO.setDispCode(dispId);
						/*if(ehfPatient.getPharmDrugSave()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmDrugSave()))
						{
							patientVO.setSaveFlag(ehfPatient.getPharmDrugSave());
							request.setAttribute("saveFlag", "Y");
						}
						else
						{
							patientVO.setSaveFlag("N");
							request.setAttribute("saveFlag", "N");
						}*/
				/*		if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
							List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
							request.setAttribute("treatHist", caseHist);
							patientForm.setLstCaseSearch(caseHist);
							}
						*/
						List<DrugsVO> drugsList=patientService.getPharmDrugDtlsPick(patientVO);
						if(drugsList.size()>0)
						{
							request.setAttribute("deoViewFlg", "N");
						}
						else
						{
							request.setAttribute("deoViewFlg", "Y");
						}
						List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
						request.setAttribute("dispDrugTypeList", drugsTypeList);
						patientForm.setDrugLt(drugsList);
						String values="";
						 if(drugsList!=null && drugsList.size()>0)
							{
								for(DrugsVO lb:drugsList)
								{
									if(lb!=null && lb.getDRUGSUBTYPECODE()!=null && !"".equalsIgnoreCase(lb.getDRUGSUBTYPECODE()))
									{
										values=values+lb.getDRUGSUBTYPECODE()+"~";
									}
								}
								values=values.substring(0,values.length()-1);
							}
							System.out.println(values);
							request.setAttribute("values", values);
						//String details=drugsTypeList.getLeaveConsultName()+"@"+patientVo2.getLeaveConsultMob()+"@"+patientVo2.getLeaveWellName();
						if(patientForm.getEnablePharma()!=null && !"".equalsIgnoreCase(patientForm.getEnablePharma()))
						{
						String enablePharma=patientForm.getEnablePharma();
						request.setAttribute("enablePharma", enablePharma);
						}
						lStrPageName="pharmacyDrgsPgePharmaPick";
					}
					else
					{
						lStrPageName="patient";
					}
				}
							 if ("viewPatientDetailsAis".equalsIgnoreCase(lStrActionVal))
			 {
					String patientId=null;
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
					EhfAisDrugs ehfPatient=null;
					patientId=request.getParameter("patientId");
					patientForm.setPatientNo(patientId);
					String userID=(String) session.getAttribute("UserID");
//					System.out.println(userID);
					//String dentalrnt=request.getParameter("dentalredirect");
					// request.setAttribute("patientId",patientId);
					ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAisDrugs(patientId);
					//storing patcrtdt
					patientForm.setCardNo(ehfPatient.getEmployeeNo());
					patientForm.setFname(ehfPatient.getEmpName());
					patientForm.setMonths(ehfPatient.getMonth());
					patientForm.setYears(ehfPatient.getYears());
					if(ehfPatient.getPickLocation()!=null && "A".equalsIgnoreCase(ehfPatient.getPickLocation()))
					{
						patientForm.setPickupName("IAS Association, Begumpet");
					}
					else if(ehfPatient.getPickLocation()!=null && "P".equalsIgnoreCase(ehfPatient.getPickLocation()))
					{
						patientForm.setPickupName("Prasashan Nagar Clinic");
					}
					PatientVO patientVO1=new PatientVO();
					patientVO1.setCaseId(patientId);
					patientVO1.setPatientId(patientId);
					patientForm.setScheme("CD202");
					List<DrugsVO> drugsList=patientService.getPharmDrugDtlsPick(patientVO1);						patientForm.setDrugLt(drugsList);
String values="";
					 if(drugsList!=null && drugsList.size()>0)
						{
							for(DrugsVO lb:drugsList)
							{
								if(lb!=null && lb.getDRUGSUBTYPECODE()!=null && !"".equalsIgnoreCase(lb.getDRUGSUBTYPECODE()))
								{
									values=values+lb.getDRUGSUBTYPECODE()+"~";
								}
							}
							values=values.substring(0,values.length()-1);
						}
						System.out.println(values);
						request.setAttribute("values", values);
						lStrPageName="printDetailsDrgs";
//				
			 }
			 			 if ("viewPharmacyPageNimsNew".equalsIgnoreCase(lStrActionVal))
				{
					EhfAisDrugs ehfPatient=null;
					String patientId=request.getParameter("patientId");
					//String caseId=patientForm.getCaseId();
					patientVO = new PatientVO();
					String casesViewFlagNew=request.getParameter("casesViewFlagNew");
					String caseStatusAis=request.getParameter("caseStatus");
					if(casesViewFlagNew!=null && "Y".equalsIgnoreCase(casesViewFlagNew) && caseStatusAis!=null && "P".equalsIgnoreCase(caseStatusAis))
					{
						request.setAttribute("viewFl", "Y");
					}
					else
					{
						request.setAttribute("viewFl", "N");
					}
					if(patientId!=null && !"".equalsIgnoreCase(patientId))
					{
						patientForm.setPatientNo(patientId);
						ehfPatient=(EhfAisDrugs)patientService.getPatientDetailsAis(patientId);
						patientForm.setCardNo(ehfPatient.getEmployeeNo());
						patientForm.setFname(ehfPatient.getEmpName());
						//patientForm.setPickupName(ehfPatient.getPickLocation());
						if(ehfPatient.getPickLocation()!=null && "A".equalsIgnoreCase(ehfPatient.getPickLocation()))
						{
							patientForm.setPickupName("IAS Association, Begumpet");
						}
						else if(ehfPatient.getPickLocation()!=null && "P".equalsIgnoreCase(ehfPatient.getPickLocation()))
						{
							patientForm.setPickupName("Prasashan Nagar Clinic");
						}
						List<LabelBean> attachList=patientService.getAttachList(patientId);
						request.setAttribute("attachList", attachList);
						List<LabelBean> attachListPr=patientService.getAttachListPr(patientId);
						request.setAttribute("attachListPr", attachListPr);
						//patientForm.setEnablePharma(ehfPatient.getPharmaView());
						/*if(ehfPatient.getGender()!=null && "M".equalsIgnoreCase(ehfPatient.getGender()))
						{
							patientForm.setGender("Male");
						}*/
						/*else if(ehfPatient.getGender()!=null && "F".equalsIgnoreCase(ehfPatient.getGender()))
						{
							patientForm.setGender("Female");
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
						}*/
						//Drug Details
						patientVO.setCaseId(patientId);
						patientVO.setPatientId(patientId);
						String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
						patientVO.setDispCode(dispId);
						/*if(ehfPatient.getPharmDrugSave()!=null && !"".equalsIgnoreCase(ehfPatient.getPharmDrugSave()))
						{
							patientVO.setSaveFlag(ehfPatient.getPharmDrugSave());
							request.setAttribute("saveFlag", "Y");
						}
						else
						{
							patientVO.setSaveFlag("N");
							request.setAttribute("saveFlag", "N");
						}*/
				/*		if(fromDisp!=null && "Y".equalsIgnoreCase(fromDisp)){
							List<CasesSearchVO> caseHist=patientService.getDispPastHistoryDetails(ehfPatient.getPatientId(),ehfPatient.getCardNo());
							request.setAttribute("treatHist", caseHist);
							patientForm.setLstCaseSearch(caseHist);
							}
						*/
						List<DrugsVO> drugsList=patientService.getPharmDrugDtls(patientVO);
						if(drugsList.size()>0)
						{
							request.setAttribute("deoViewFlg", "N");
						}
						else
						{
							request.setAttribute("deoViewFlg", "Y");
						}
						List<LabelBean> drugsTypeList=patientService.getDrugTypeListAis();
						request.setAttribute("dispDrugTypeList", drugsTypeList);
						patientForm.setDrugLt(drugsList);
						String values="";
						 if(drugsList!=null && drugsList.size()>0)
							{
								for(DrugsVO lb:drugsList)
								{
									if(lb!=null && lb.getDRUGSUBTYPECODE()!=null && !"".equalsIgnoreCase(lb.getDRUGSUBTYPECODE()))
									{
										values=values+lb.getDRUGSUBTYPECODE()+"~";
									}
								}
								values=values.substring(0,values.length()-1);
							}
							System.out.println(values);
							request.setAttribute("values", values);
						//String details=drugsTypeList.getLeaveConsultName()+"@"+patientVo2.getLeaveConsultMob()+"@"+patientVo2.getLeaveWellName();
						if(patientForm.getEnablePharma()!=null && !"".equalsIgnoreCase(patientForm.getEnablePharma()))
						{
						String enablePharma=patientForm.getEnablePharma();
						request.setAttribute("enablePharma", enablePharma);
						}
						lStrPageName="pharmacyDrgsPgePharma";
					}
					else
					{
						lStrPageName="patient";
					}
				}
			if (lStrActionVal != null && lStrActionVal.equalsIgnoreCase("viewAttachment")) {
				String lStrFilePath = null;
				String cnt=null;
				String lStrContentType = request.getContentType();
				lStrContentType = (lStrContentType == null) ? FileConstants.EMPTY_STRING : lStrContentType;
				String flag=request.getParameter("flag");
				cnt=request.getParameter("cnt");
				if(flag!=null && "drugsAttach".equalsIgnoreCase(flag)){
					String[] attach=request.getParameter("filePath").split("~");
					lStrFilePath=attach[Integer.parseInt(cnt)-1];
				}
				else{
				lStrFilePath = request.getParameter("filePath");
				}
				File file = null;
				FileInputStream fis1 = null;
				DataInputStream dis1 = null;
				String lStrType = null;
				ServletOutputStream out = response.getOutputStream();
				/**
		           * 
		           */
				String fileExt = lStrFilePath.substring((lStrFilePath.lastIndexOf(".") + 1));
				String lStrFileName = lStrFilePath.substring((lStrFilePath.lastIndexOf("/") + 1));
				String attachMode = "inline";
				if (fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPG)
						|| fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_JPEG)
						|| fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_GIF) || fileExt.equalsIgnoreCase(FileConstants.FILE_EXT_PDF)) {
					attachMode = "inline";
				}
				lStrContentType = FileConstants.FILE_EXT.get(fileExt);
				/**
		           * 
		           */
				String dir = lStrFilePath;
				byte[] lbBytes = null;
				try {
					if (lStrFileName != null && !lStrFileName.equals("")) {
						file = new File(dir);
						fis1 = new FileInputStream(file);
						dis1 = new DataInputStream(fis1);
						lbBytes = new byte[dis1.available()];
						fis1.read(lbBytes);
						request.setAttribute("File", lbBytes);
						request.setAttribute("ContentType", lStrContentType);
						request.setAttribute("FileName", lStrFileName);
						request.setAttribute("Extn", lStrType);
						response.setContentType(lStrContentType);
						response.setHeader("Content-Disposition", "" + attachMode + "; filename=" + lStrFileName);// 006
						out.write(lbBytes);
						// out.close();
					} else {
						lbBytes = new byte[0];
					}
				} catch (Exception e) {
					e.getMessage();
					e.printStackTrace();
				} finally {
					out.close();
				}
			}
			if ("cancelPatientDirReg".equalsIgnoreCase(lStrActionVal))
			{
				String patientId = request.getParameter("patientId");
				String aisRemarks = request.getParameter("aisRemarks");
				PatientVO patVo=new PatientVO();
				if(patientId != null && !"".equalsIgnoreCase(patientId))
				patVo.setPatientId(patientId);
				patVo.setCancelRemarks(aisRemarks);
				patVo.setLstUpdUsr(userIDu);
				String msgNew = patientService.cancelPatient(patVo);
				request.setAttribute("saveMsg", msgNew);
				lStrActionVal="ViewRegisteredPatientsAis";
			}
			if ("sendMail".equalsIgnoreCase(lStrActionVal))
			{
				String patientId=request.getParameter("patientId");
				EhfPatient ehfPatient=null;
				SimpleDateFormat sdfp=new SimpleDateFormat("dd-MM-yyyy");
				SimpleDateFormat sdfs=new SimpleDateFormat("dd/MM/yyyy");
				Date date = new Date();
				String crtDate=sdfs.format(date);
				Date crtDt = sdfs.parse((sdfw.format(date)));
				ehfPatient=(EhfPatient)patientService.getPatientDetails(patientId);
				List<LabelBean> attachList=patientService.getAisAttach(patientId);
				List<String> list = new ArrayList<String>();
				if(attachList!=null && attachList.size()>0)
				//session.setAttribute("relationList",attachList);
				{
					for(LabelBean lab:attachList)
					{
						list.add(lab.getVALUE());
					}
				}
				if(list!=null && list.size()>0)
				{
					if (config.getBoolean("EmailRequired")) 
				{
					String mailId=null;
					if(ehfPatient.getEmailId()!=null && !ehfPatient.getEmailId().equals(""))
					{
						mailId=ehfPatient.getEmailId();
						String[] emailToArray = {mailId};
						EmailVO emailVO = new EmailVO();
						emailVO.setTemplateSource(EmailConstants.TEMPLATE_FROM_FA);
						emailVO.setFromEmail(config.getString("emailFrom"));
						emailVO.setToEmail(emailToArray);
						emailVO.setSubject(config.getString("patientEmailSubjectAis"));
						Map<String, String> model = new HashMap<String, String>();
						Map<String, List<String>> model1 = new HashMap<String, List<String>>();
						model.put("patientName", ehfPatient.getName());
							model.put("registeredHosp", "NIMS");
						model.put("status", "Patient Registered");
						model.put("statusDate",crtDate);
						model1.put("lStrFileName",list);
						//model.put("Filename",patVo.getFilePath());
										emailVO.setTemplateName(config.getString("EHFPatientTemplateName"));
										commonService.generateMailNew(emailVO, model,model1);
										request.setAttribute("aisMail", "Y");
										if(ehfPatient.getContactNo().toString()!=null && !ehfPatient.getContactNo().toString().equals(""))
										{
											SMSServices SMSServicesobj = new SMSServices();
											if(ehfPatient.getRelationAis()!=null && !"".equalsIgnoreCase(ehfPatient.getRelationAis()) && "Y".equalsIgnoreCase(ehfPatient.getRelationAis()))
											{
											templateId="1407161769884404148";
											String msgNew= "Dear "+ehfPatient.getName().trim()+" , Your Master Health Checkup reports has been sent successfully to your registered Email ID.\n\nAHCT, Govt. of Telangana";
										    SMSServicesobj.sendSingleSMS(msgNew,ehfPatient.getContactNo().toString(),templateId);
											}
											else if(ehfPatient.getRelationAis()!=null && !"".equalsIgnoreCase(ehfPatient.getRelationAis()) && "N".equalsIgnoreCase(ehfPatient.getRelationAis()))
											{
											templateId="1407161769879841201";
											String msgNew= "Sarvasri "+ehfPatient.getName().trim()+" "+ehfPatient.getSerType().trim()+"  , Your Master Health Checkup reports has been sent successfully to your registered Email ID.\n\nAHCT, Govt. of Telangana";
										    SMSServicesobj.sendSingleSMS(msgNew,ehfPatient.getContactNo().toString(),templateId);
											}
											else
											{
												templateId="1407161769884404148";
												String msgNew= "Dear "+ehfPatient.getName().trim()+" , Your Master Health Checkup reports has been sent successfully to your registered Email ID.\n\nAHCT, Govt. of Telangana";
											    SMSServicesobj.sendSingleSMS(msgNew,ehfPatient.getContactNo().toString(),templateId);
											}
										}
					}
					if(ehfPatient.getEmailId()==null)
					{
						request.setAttribute("aisMailSend", "Y");
					}
				}
				}
				else
				{
					request.setAttribute("aisMail", "N");
				}
				lStrActionVal="ViewRegisteredPatientsAis";
				request.setAttribute("advSearch", "false");
				request.setAttribute("aisView", "Y");
			}
		if ("ViewRegisteredPatientsAis".equalsIgnoreCase(lStrActionVal))
		{
			String oldCase="";
			List<LabelBean> hospitalList= null;
			fromDisp = request.getParameter("fromDispnsry");
			String stateType=request.getParameter("stateType");
			String aisView = request.getParameter("aisView");
			if(aisView!=null && !"".equalsIgnoreCase(aisView) && "Y".equalsIgnoreCase(aisView)){
				request.setAttribute("fromAis","Y");
			}
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
			searchMap.put("userHospId","NI");
			searchMap.put("aisView", aisView);
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
					if(patientForm.getPatientNo()!=null&&!patientForm.getPatientNo().equals("")){
						patientNo=patientForm.getPatientNo();
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
			}
			else if((request.getParameter("advSearch")==null || request.getParameter("advSearch").equalsIgnoreCase("false")))
			{
				List<LabelBean> stateList = commonService.getAsrimLocations(stateHdrId, statePrntId);
				session.setAttribute("stateList", stateList);
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
				lStrPageName="viewRegisteredPatientsAis";
			}
		}
		if (lStrActionVal != null && "uploadPage".equalsIgnoreCase(lStrActionVal)) {
			PatientVO claimReimbursementVo = new PatientVO();
			try {
				if (request.getParameter("patientId") != null && !"".equalsIgnoreCase(request.getParameter("patientId"))) {
					request.setAttribute("patientId", request.getParameter("patientId"));
				}
				request.setAttribute("attachType", request.getParameter("path"));
				claimReimbursementVo.setPatientId(request.getParameter("perseq"));
				//ClaimReimbursementVo attachDet = claimReimbursementservice.getAttachDet(claimReimbursementVo);
				//claimReimbursementForm.setAttachPath(attachDet.getAttachPath());
				//claimReimbursementForm.setAttachName(attachDet.getAttachName());
				//claimReimbursementForm.setDelAttachSeq(attachDet.getDelAttachSeq());
			//request.setAttribute("attachpath", attachDet.getAttachPath());
		/*List<String> attachLst= claimReimbursementservice.getAttachDetNew(claimReimbursementVo);
	    request.setAttribute("attachLst",attachLst);*/
			} catch (Exception e) {
				e.printStackTrace();
			}
				lStrPageName = "uploadAttachment";
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getDrugReport"))
		{
			String drugFlag= request.getParameter("drugFlag");
			request.setAttribute("drugFlag",drugFlag);
			lStrPageName="drugDistribution";
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("drugReport"))
		{
			String fromdate= request.getParameter("fromDate");
			String todate=  request.getParameter("destDate");
			userName=(String) session.getAttribute("UserID");
			//String dispnameNew="EHS34";
			int pageNo=1,rowsPerPage=10;
			int startIndex=1,maxResults=0;
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
               List<LabelBean> drugReportList=patientService.getdrugs(fromdate,todate,dispname,startIndex,maxResults,pageNo,userName);
            if(drugReportList!=null &&drugReportList.size()>0)
            { 
            	request.setAttribute("totalPages",drugReportList.get(0).getTotalPages());
				request.setAttribute("totalRecords",drugReportList.get(0).getTotalRecords());
				request.setAttribute("pageId",drugReportList.get(0).getPageId());
				request.setAttribute("endIndex",drugReportList.get(0).getMaxresults());
				request.setAttribute("startIndex",drugReportList.get(0).getStrtIndex());
				request.setAttribute("endResults",drugReportList.get(0).getStrtIndex()+drugReportList.size());  
            }  
            request.setAttribute("fromDetailed", "N") ;
				patientForm.setFromDetailed("N");
			request.setAttribute("drugReportList", drugReportList);
			patientForm.setDrugReportList(drugReportList);
			if(drugReportList==null)
			{
				request.setAttribute("drugFlag","N");
			}
			else
			{
				request.setAttribute("drugFlag","Y");
			}
			return mapping.findForward("drugDistribution");
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("drugDetailedReport"))
		{
			String fromdate= request.getParameter("fromDate");
			String todate=  request.getParameter("destDate");
			userName = (String) session.getAttribute("UserID");
			//String dispname=request.getParameter("dispname");
			String drugId  =request.getParameter("drugId");
			String drugType=request.getParameter("drugTypeNew");
			String caseId=request.getParameter("caseId");
			int pageNo=1,rowsPerPage=10;
			int startIndex=1,maxResults=0;
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
            List<LabelBean> drugDetailedReportList=patientService.getDrugsDetailed(fromdate,todate,dispname, drugId,drugType,caseId,startIndex,maxResults,pageNo,userName);
            if(drugDetailedReportList!=null &&drugDetailedReportList.size()>0)
            {
            	request.setAttribute("totalPages",drugDetailedReportList.get(0).getTotalPages());
				request.setAttribute("totalRecords",drugDetailedReportList.get(0).getTotalRecords());
				request.setAttribute("pageId",drugDetailedReportList.get(0).getPageId());
				request.setAttribute("endIndex",drugDetailedReportList.get(0).getMaxresults());
				request.setAttribute("startIndex",drugDetailedReportList.get(0).getStrtIndex());
				request.setAttribute("endResults",drugDetailedReportList.get(0).getStrtIndex()+drugDetailedReportList.size());  
            }
            String dispensary= null;
        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
        	{
        		dispensary = patientService.getDispName(dispname);
        	}
				request.setAttribute("fromDetailed", "Y") ;
				patientForm.setFromDetailed("Y");
            request.setAttribute("drugDetailedReportList", drugDetailedReportList);
            request.setAttribute("dispensary", dispensary);
            patientForm.setDrugReportList(drugDetailedReportList);
        	if(drugDetailedReportList==null)
			{
				request.setAttribute("drugFlag","N");
			}
			else
			{
				request.setAttribute("drugFlag","Y");
			}
			if((drugId!=null &&!"".equalsIgnoreCase(drugId)&&drugType!=null&&!"".equalsIgnoreCase(drugType))||(caseId!=null&&!"".equalsIgnoreCase(caseId)))
			{
			    request.setAttribute("fromdate", fromdate);
			    request.setAttribute("todate", todate);
			    request.setAttribute("drugId", drugId);
			    request.setAttribute("drugType", drugType);
				return mapping.findForward("drugDistributionDetailed");
			}
			else
			return mapping.findForward("drugDistribution");
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("drugreportCsv"))
	 	{
	 	    	 		int sno=1;
	 	    	 	    int page =-1;
	 	    	 		String fromdate= request.getParameter("fromDate");
	 	    			String todate=  request.getParameter("destDate");
	 	    			userName = (String) session.getAttribute("UserID");
	 	    			//String dispname=request.getParameter("dispname");
	 	    			String reportType=request.getParameter("reportType");
	 	    			String drugId  =request.getParameter("drugId");
	 	    			String drugType=request.getParameter("drugType");
	 	    			String caseId=request.getParameter("caseId");
	 	    			userName = (String) session.getAttribute("UserID");
	 	    			String lStrDirPath=config.getString("REPORT.MapPath");
	 			        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Drug Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
	 		        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
	 		        	final String separator = "^";
	 					final String NEW_LINE_SEPARATOR = "\n";
	 		        	FileWriter fileWriter = null;
	 		        	List<LabelBean> drugReportList=null;
	 		        	if(reportType!=null && reportType.equalsIgnoreCase("general"))
	 					{
	 		        		drugReportList=patientService.getdrugs(fromdate,todate,dispname,page,page,page,userName);
	 		        	}
	 		        	else if(reportType!=null && reportType.equalsIgnoreCase("detailed"))
	 					{
	 		        		drugReportList=patientService.getDrugsDetailed(fromdate,todate,dispname, drugId,drugType,caseId,page,page,page,userName);
	 		        	}
	 		        	String dispensary= null;
	 		        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
	 		        	{
	 		        		dispensary = patientService.getDispName(dispname);
	 		        	}
	 				try 
	 				{
	 					fileWriter = new FileWriter(lcsvfile);
	 					if(drugReportList!=null &&drugReportList.size()>0)
	 		        	{
	 						fileWriter.append("S NO");
	 	                    fileWriter.append(separator);//1
	 		        	if(reportType!=null && reportType.equalsIgnoreCase("detailed"))
	 		        	{
	 		        		fileWriter.append("CASE ID");
	 			        	fileWriter.append(separator);
	 			        	fileWriter.append("PATIENT NAME");
	 			        	fileWriter.append(separator);
	 		        	}
	 		        	fileWriter.append("DRUG TYPE");
	 		        	fileWriter.append(separator);
	 		        	fileWriter.append("DRUG NAME");
	 		        	fileWriter.append(separator);
	 		        	fileWriter.append("PRESCRIBED QUANTITY");
	 		        	fileWriter.append(separator);
	 		        	fileWriter.append("ISSUED QUANTITY");
	 		        	fileWriter.append(separator);
	 		        	if(reportType!=null && reportType.equalsIgnoreCase("detailed"))
	 		        	{
	 		        		fileWriter.append("ISSUE DATE");
	 			        	fileWriter.append(separator);
	 		        	}
	 		        	   fileWriter.append(NEW_LINE_SEPARATOR); 
	 		        	for(LabelBean drugs:drugReportList)
	 		        	{
	 		        		fileWriter.append(String.valueOf(sno));
	 		        		sno++;
	 		        		 fileWriter.append(separator);
	 		        		 if(reportType!=null && reportType.equalsIgnoreCase("detailed"))
	 		        		 {
	 		        			 if(drugs.getCASEID()!=null && !"".equalsIgnoreCase(drugs.getCASEID()))
	 		        			 {
	 			 	        		fileWriter.append(drugs.getCASEID());
	 			 		        	fileWriter.append(separator);
	 		        			 }
	 		        			 else
	 		        			 {
	 		        				fileWriter.append("NA");
                                    fileWriter.append(separator); 
	 		        			 }
	 		        			if(drugs.getNAME()!=null && !"".equalsIgnoreCase(drugs.getNAME()))
	 		        			 	{
	 		        				fileWriter.append(drugs.getNAME());
	 			 		        	fileWriter.append(separator);
	 		        			 	}
	 		        			else
	 		        			 	{
	 		        				fileWriter.append("NA");
                                    fileWriter.append(separator); 
	 		        			 	}
	 			 	        }
	 		        		 if(drugs.getDRUGTYPE()!=null && !"".equalsIgnoreCase(drugs.getDRUGTYPE()))
	 		        		 {
	 		        			 fileWriter.append(drugs.getDRUGTYPE());
	 		        			 fileWriter.append(separator);
	 		        		 }
	 		        		 else
 		        			 {
 		        				fileWriter.append("NA");
                                fileWriter.append(separator); 
 		        			 }
	 		        		if(drugs.getDRUGNAME()!=null && !"".equalsIgnoreCase(drugs.getDRUGNAME()))
	 		        		 	{
	 		        		 fileWriter.append(drugs.getDRUGNAME());
	 		        		 fileWriter.append(separator);
	 		        		 	}
	 		        		else
 		        			{
 		        				fileWriter.append("NA");
                                fileWriter.append(separator); 
 		        			}
	 		        		if(drugs.getPRESC_QUANTITY()!=null && !"".equalsIgnoreCase(drugs.getPRESC_QUANTITY()))
	 		        		{
	 		        			 fileWriter.append(drugs.getPRESC_QUANTITY());
	 		        			 fileWriter.append(separator);
	 		        		}
	 		        		else
	 		        		{
 	 		        			 fileWriter.append("0");
                                 fileWriter.append(separator);
 	 		        		}	 
	 		        		if(drugs.getQUANTITY()!=null && !"".equalsIgnoreCase(drugs.getQUANTITY()))
	 		        		{
	 		        			fileWriter.append(drugs.getQUANTITY());
	 		        			fileWriter.append(separator);
	 		        		}
	 		        		else
	 		        		{
	 		        			fileWriter.append("0");
                                fileWriter.append(separator);
	 		        		}
	 		        		if(reportType!=null && reportType.equalsIgnoreCase("detailed"))
	 		        		{
	 		        			if(drugs.getISSUEDATE()!=null && !"".equalsIgnoreCase(drugs.getISSUEDATE()))
	 		        			{
	 		        				fileWriter.append(drugs.getISSUEDATE());
	 		        				fileWriter.append(separator);
	 		        			}
	 		        			else
	 		        			{
	 		        				fileWriter.append("NULL");
	 		        				fileWriter.append(separator); 
	 		        			}
	 		        		}
	 		        		   fileWriter.append(NEW_LINE_SEPARATOR);   
	 		        	}
	 		        	System.out.println("CSV file was created successfully !!!");
	 					}
	 		        }
	 				catch (Exception e)
	 		        {
	 		            System.out.println("Error in CsvFileWriter !!!");
	 		            e.printStackTrace();
	 		        } 
	 		        finally
	 		        {
	 		            try 
	 		            {
	 		                fileWriter.flush();
	 		                fileWriter.close();
	 		            }
	 		            catch (IOException e)
	 		            {
	 		                System.out.println("Error while flushing/closing fileWriter !!!");
	 		                e.printStackTrace();
	 		            }
	 				}
	 				byte[] lbBytes= ExcelWriter.getBytesFromFile(lcsvfile);
	 				request.setAttribute("File", lbBytes);                 
		            response.setContentType(config.getString("REPORT.CsvContentType"));
		        	response.setHeader("Content-Disposition","Attachment; filename=Drug Report of "+dispname+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
		        	response.setCharacterEncoding("UTF-8");
		        	return mapping.findForward("openFile"); 
	 	}
		if ( lStrActionVal!=null && lStrActionVal.equalsIgnoreCase ("leaveReport"))
		{
			List<CreateEmployeeVO> empList=null;
			Map<String, Object> returnMap = null;
			String fromaDate=(String) request.getParameter("fromDate");
			String toDate=(String) request.getParameter("toDate");
			String designation=(String) request.getParameter("designation");
			List<CreateEmployeeVO> reptList=new ArrayList<CreateEmployeeVO>();
			CreateEmployeeVO empSearchResultsList=null;
			CreateEmployeeVO createEmployeeVO =new CreateEmployeeVO();
			List<CreateEmployeeVO> employeeList=new ArrayList<CreateEmployeeVO>();
			patientForm.setFromDate(fromaDate);
			patientForm.setToDate(toDate);
			patientForm.setDesignation(designation);
			createEmployeeVO.setFromDate(fromaDate);
			createEmployeeVO.setToDate(toDate);
			createEmployeeVO.setDesgName(designation);
			if(request.getParameter("excelFlag")!=null && !request.getParameter("excelFlag").equals("")){
				createEmployeeVO.setExportFlag("Y");
			}
				else
				{
					createEmployeeVO.setExportFlag("N");
				}
			//pagination
			String pageId=request.getParameter("pageId");
			String end_Index=request.getParameter("end_index");
			createEmployeeVO.setUserId(lStrUserId);
			empSearchResultsList=patientService.getLeaveReport(createEmployeeVO);
							int pageIdVO=0,startIndex=0,maxResults=0,totalRecords=0,totalPages=0;
							if(pageId!=null)
								{
									pageIdVO=Integer.parseInt(pageId);
									maxResults=Integer.parseInt(end_Index);
									startIndex=(pageIdVO-1)*maxResults;
								}
							else
								{
									pageIdVO=1;
									startIndex=0;
									if(createEmployeeVO.getExportFlag().equals("Y")){
										totalRecords= Integer.valueOf(empSearchResultsList.getTotRowCount());
										maxResults=totalRecords;
									}
									else
									maxResults=10;
								}
							createEmployeeVO.setStart_index(startIndex);
							createEmployeeVO.setEnd_index(maxResults);
						empSearchResultsList=patientService.getLeaveReport(createEmployeeVO);
						employeeList=empSearchResultsList.getEmpSearchList();
						totalRecords= Integer.valueOf(empSearchResultsList.getTotRowCount());
						if(empSearchResultsList!=null)
						{
							if(empSearchResultsList.getEmpSearchList()!=null)
								{
								totalPages=totalRecords%maxResults!=0?totalRecords/maxResults+1:totalRecords/maxResults;
							patientForm.setLevRepList(empSearchResultsList.getEmpSearchList());
							request.setAttribute("pageNo",pageIdVO);
							request.setAttribute("noOfRecords",totalRecords);
							request.setAttribute("lastPage",totalPages);
							request.setAttribute("start_index",startIndex);
							request.setAttribute("end_index",maxResults);
							request.setAttribute("endresults",startIndex+empSearchResultsList.getEmpSearchList().size());
								}
						  }
					else
						//patientForm.setSuccessFlag("NoRecords");
			if(createEmployeeVO.getExportFlag().equals("Y"))
				lStrPageName="exportToCsv";
			 empList=empSearchResultsList.getEmpSearchList();
			 if(empList.size()>0)
				{
				 	request.setAttribute("empList", empList);
				}
			 lStrPageName="leaveRportFinal";
		}
		if ( lStrActionVal!=null && lStrActionVal.equalsIgnoreCase ("docLeaveDtls"))
		{
			PatientVO patientVo=new PatientVO();
			PatientVO patientVo2=new PatientVO();
			String docId=request.getParameter("x");
			patientVo.setDocRegNo(docId);			
			patientVo2=commonService.getDocDtls(patientVo);
			List<LabelBean> doctorDates = null; 
			 doctorDates=commonService.getDoctorDates(docId);
			request.setAttribute("dispDoctorNames", doctorDates);
			String details=patientVo2.getLeaveConsultName()+"@"+patientVo2.getLeaveConsultMob()+"@"+patientVo2.getLeaveWellName();
			request.setAttribute("AjaxMessage", details);
			lStrPageName="ajaxResult"; 
		}
		if ("saveDTOdrugsNims".equalsIgnoreCase(lStrActionVal))
		{
		  String caseId=patientForm.getPatientNo();
		  String id=request.getParameter("id");
		  if(caseId!=null && !"".equalsIgnoreCase(caseId))
		  {
			  patientVO = new PatientVO();
			  //patientVO.setCaseId(caseId);
			  patientVO.setCrtUsr(lStrUserId);
			  patientVO.setRoleId(roleId);
			  patientVO.setPatientId(caseId);
			  patientVO.setAisFlag(id);
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
			  							 	String newId="";
			  							 	if(drugValues[2]!=null && !"NA".equalsIgnoreCase(drugValues[2]))
			  							 	{
			  							 		 newId=patientService.getsaveNewDrug(drugValues[0],drugValues[2],lStrUserId);
			  							 	}
			  							 	if(drugValues[2]!=null && !"NA".equalsIgnoreCase(drugValues[2]))
			  							 	{
			  								drugsVO.setDrugSubTypeCode(newId);
			  							 	}
			  							 	else
			  							 	{
			  							 		drugsVO.setDrugSubTypeCode(drugValues[1]);
			  							 	}
			  							/*	if(drugValues[1].equalsIgnoreCase("OD45899"))
			  								{
			  								}*/
			  								drugsVO.setDosage(drugValues[3]);
			  								drugsVO.setMedicationPeriod(drugValues[4]);
			  								drugsVO.setQuantity(drugValues[5]);
			  							}
			  							Long drugSeqNextVal = Long.parseLong(patientService.getSequence("OP_DRUG_ID"));
			  					drugsVO.setDrugId(drugSeqNextVal);
			  					drugsList.add(drugsVO);
			  				}
			  				}
			  				patientVO.setDrugs(drugsList);
			  			String patCaseId=patientService.saveDocDrugsNew(patientVO);
			  			if(patCaseId!=null && patCaseId.equalsIgnoreCase(caseId))
			  			{
			  				patientForm.setMsg("Drugs Issued Successfully.");
			  			}
			  			else
			  			{
			  				patientForm.setMsg("Drugs Cannot be Issued");
			  			}
			  			request.setAttribute("fromDisp", "Y");
			  			request.setAttribute("deoFlg", "Y");
			  			if(roleId!=null && "GP700".equalsIgnoreCase(roleId))
			  			{
			  				request.setAttribute("fromPharmaMo", "Y");
			  			}
			  			else if(roleId!=null && "GP702".equalsIgnoreCase(roleId))
			  			{
			  				request.setAttribute("fromPharmaPo", "Y");
			  			}
			  			request.setAttribute("dispPatID", patientForm.getPatientNo());
			  			lStrPageName="patient";
		  }
		}	
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getOpDrugDtlsAuto" ) )
		{
			String chemicalCode=request.getParameter("chemicalCode");
			Gson jsonData=new Gson();
			EhfmOpDrugMst ehfmOpDrugMst=new EhfmOpDrugMst();
			ehfmOpDrugMst=patientService.getopdrugDataAuto(chemicalCode);
			request.setAttribute("AjaxMessage",jsonData.toJson(ehfmOpDrugMst));
			return mapping.findForward("ajaxResult");
		}
		if ("saveDTOdrugs".equalsIgnoreCase(lStrActionVal)) {
			String caseId = patientForm.getCaseId();
			if (caseId != null && !"".equalsIgnoreCase(caseId)) {
				patientVO = new PatientVO();
				patientVO.setCaseId(caseId);
				patientVO.setCrtUsr(lStrUserId);
				patientVO.setPatientId(patientForm.getPatientNo());
				List<DrugsVO> drugsList = new ArrayList<DrugsVO>();
				if (patientForm.getDrugs() != null
						&& !patientForm.getDrugs().equalsIgnoreCase("")) {
					/*String s = patientForm.getDrugs().substring(0,
							patientForm.getDrugs().length() - 1);*/
					String s = patientForm.getDrugs();
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
				List<SpecialityVO> specList = new ArrayList<SpecialityVO>();
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
							  if (specialityValues.length > 0) {
									if (specialityValues.length == 3) {
										specVO.setSpecialistDoctorId(specialityValues[0]);
										specVO.setSpecialistDoctorName(specialityValues[1]);
										specVO.setOtherSpecialistDoctorName(specialityValues[2]);
									}
									if (specialityValues.length == 2) {
										specVO.setSpecialistDoctorId(specialityValues[0]);
										specVO.setSpecialistDoctorName(specialityValues[1]);
									}
								}					
						 } 
						specList.add(specVO);
					}
				}
				patientVO.setDrugs(drugsList);
				String patCaseId = patientService.saveDocDrugs(patientVO);
				String patCaseId1 = patientService.saveSpecialistDoctors(patientForm.getPatientNo() , specList);			  			
				if(patCaseId!=null && patCaseId.equalsIgnoreCase(caseId))
			  			{
			  				patientForm.setMsg("Drugs Issued Successfully.");
			  			}
			  			else
			  			{
			  				patientForm.setMsg("Drugs Cannot be Issued");
			  			}
			  			request.setAttribute("fromDisp", "Y");
			  			request.setAttribute("deoFlg", "Y");
			  			request.setAttribute("dispPatID", patientForm.getPatientNo());
			  			lStrPageName="patientNew";
			}
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("viewlowStockIndent"))
		{
			String drugId=patientForm.getDispDrugID();
			String drugType=request.getParameter("drugType");
		    String yearFlag=request.getParameter("yearFlag");
		    String indentedStock = request.getParameter("indentedStock");
		    String lowStock = request.getParameter("lowStock");
		    String dispid = null;
			List<LabelBean> drugsList=null;
			List<String> drugs=new ArrayList<String>();
		    wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
		    if(wellnesslist.size()>0){
			    dispname = wellnesslist.get(0).getDISPNAME();
			    dispid= wellnesslist.get(0).getDISPID();
		    }
		    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
			request.setAttribute("dispDrugTypeList", drugsTypeList);
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			try{
				 drugsList=patientService.getDispDrugList(drugType,dispname);
				}
				catch(Exception e)
				{//	GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
			for (LabelBean labelBean: drugsList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
				{
						drugs.add(labelBean.getID() + "~" + 
								labelBean.getVALUE());
		  }
			}
			GLOGGER.info ( "Retrieving Drugs List using Ajax Call in PatientAction." ) ;
			request.setAttribute("dispDrugsList", drugsList);
			request.setAttribute("dispname", dispname);
			request.setAttribute("dispid", dispid);
			request.setAttribute("yearFlag", yearFlag);
			request.setAttribute("indentedStock", indentedStock);
			request.setAttribute("lowStock", lowStock);
			//request.setAttribute("loginName", loginName);
			List<LabelBean> indentList=patientService.lowStockList(drugType,dispid,drugId+"~2");
			request.setAttribute("indentList", indentList);
			patientForm.setIndentList(indentList);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
			String updDate = formatter.format(new Date());
			request.setAttribute("updDate", updDate);
			request.setAttribute("yearFlag", yearFlag);
			request.setAttribute("indentedStock", indentedStock);
			request.setAttribute("lowStock", lowStock);
		if(drugs!=null && drugs.size() > 0)
			request.setAttribute("AjaxMessage", drugs);
			lStrPageName="ajaxResult";
		}else if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("viewlowStockReport"))
		{
			String drugId=patientForm.getDispDrugID();
		    String drugType=patientForm.getDrugTypeID();
		    String yearFlag=request.getParameter("yearFlag");
		    String indentedStock = request.getParameter("indentedStock");
		    String lowStock = request.getParameter("lowStock");
		    String flag = "";//Chandana - 9008 - Added this flag ot get the critical or non critical flag
		    flag = request.getParameter("lowStockCrtitical");
		    String dispid = null;
		    wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
		    if(wellnesslist.size()>0){
			    dispname = wellnesslist.get(0).getDISPNAME();
			    dispid= wellnesslist.get(0).getDISPID();
		    }
		    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
			request.setAttribute("dispDrugTypeList", drugsTypeList);
			List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
			request.setAttribute("dispDrugsList", drugsList);
			request.setAttribute("dispname", dispname);
			request.setAttribute("dispid", dispid);
			request.setAttribute("yearFlag", yearFlag);
			request.setAttribute("indentedStock", indentedStock);
			request.setAttribute("lowStock", lowStock);
			//request.setAttribute("loginName", loginName);
			List<LabelBean> indentList=patientService.lowStockList(drugType,dispid,drugId+"~1"+"~"+flag);//Chandana - 9008 - Added flag also in the drug id, instead of adding new parameter as it needs to change the method signature
			request.setAttribute("indentList", indentList);
			patientForm.setIndentList(indentList);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
			String updDate = formatter.format(new Date());
			request.setAttribute("updDate", updDate);
			request.setAttribute("yearFlag", yearFlag);
			request.setAttribute("indentedStock", indentedStock);
			request.setAttribute("lowStock", lowStock);
			request.setAttribute("lowStockFlag", "Y");//Chandana - 8809 - send the lowstockFlag to jsp using setattribute
			//return mapping.findForward("lowStock");
			return mapping.findForward("indentPO");
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getLowIndentReportsNew"))
		{
			String wcName=patientForm.getDispname();
            String drugName=patientForm.getDispDrugID()+"~1";
		    String drugType=patientForm.getDrugTypeID();
		    String dispid = null;
		    wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
		    if(wellnesslist.size()>0){
			    dispname = wellnesslist.get(0).getDISPNAME();
			    dispid= wellnesslist.get(0).getDISPID();
		    }
		    String yearFlag=request.getParameter("yearFlag");
		    String indentedStock = request.getParameter("indentedStock");
		    String lowStock = request.getParameter("lowStock");
            List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
			request.setAttribute("dispDrugTypeList", drugsTypeList);
			List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
			request.setAttribute("dispDrugsList", drugsList);
			request.setAttribute("yearFlag", yearFlag);
			request.setAttribute("indentedStock", indentedStock);
			request.setAttribute("lowStock", lowStock);
		     int pageNo=1,rowsPerPage=10;
 			int startIndex=1,maxResults=0;
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
			List<LabelBean> indentList=patientService.lowStockList(drugType,dispid,drugName);
			 if(indentList!=null &&indentList.size()>0)
             {
             	request.setAttribute("totalPages",indentList.get(0).getTotalPages());
					request.setAttribute("totalRecords",indentList.get(0).getTotalRecords());
					request.setAttribute("pageId",indentList.get(0).getPageId());
					request.setAttribute("endIndex",indentList.get(0).getMaxresults());
					request.setAttribute("startIndex",indentList.get(0).getStrtIndex());
					request.setAttribute("endResults",indentList.get(0).getStrtIndex()+indentList.size());  
					request.setAttribute("totStockPrice", indentList.get(0).getTOT_STOCKPRICE());
             }
			request.setAttribute("indentList", indentList);
			patientForm.setIndentList(indentList);
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
			String updDate = formatter.format(new Date());
			request.setAttribute("updDate", updDate);
			request.setAttribute("yearFlag", yearFlag);
			request.setAttribute("indentedStock", indentedStock);
			request.setAttribute("lowStock", lowStock);
			request.setAttribute("drugType", drugType);
			return mapping.findForward("lowStock");
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getDrugInvReport")){						
			lStrPageName="drugsInventoryReport";
		}
		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getDrugInvEditReport")){
			wellnesslist=patientService.getwellness();
			request.setAttribute("wellnesslist", wellnesslist);
			List<LabelBean> distributorList = patientService.getdistributorsList();
			request.setAttribute("distributorList", distributorList);
			lStrPageName="drugsInventoryEditReport";
		}
		if (lStrActionVal != null && lStrActionVal.equalsIgnoreCase("drugInveReport")) {
			request.setAttribute("loginName", loginName);
			String distributorId = request.getParameter("distributorId");
			String fromdate = request.getParameter("fromDate");
			String todate = request.getParameter("destDate");
			dispname = request.getParameter("dispname");
			String drugname = request.getParameter("drugname");
			int pageNo = 1, rowsPerPage = 10;
			int startIndex = 1, maxResults = 0;
			if (request.getParameter("pageId") != null
					&& !request.getParameter("pageId").equals("")) {
				pageNo = Integer.parseInt(request.getParameter("pageId"));
			}
			if (request.getParameter("startIndex") != null)
				startIndex = Integer.parseInt(request
						.getParameter("startIndex"));
			if (request.getParameter("endIndex") != null)
				maxResults = Integer.parseInt(request.getParameter("endIndex"));
			if (pageNo == 0) {
				request.setAttribute("selectedPage", pageNo + 1);
			} else {
				request.setAttribute("selectedPage", pageNo);
			}
			if (pageNo > 0) {
				startIndex = getStartIndex(rowsPerPage, pageNo);
			}
			List<LabelBean> drugReportList = patientService.getdrugsInve(fromdate, todate, dispname, drugname, startIndex, maxResults, pageNo, distributorId);
			if (drugReportList != null && drugReportList.size() > 0) {
				request.setAttribute("totalPages", drugReportList.get(0)
						.getTotalPages());
				request.setAttribute("totalRecords", drugReportList.get(0)
						.getTotalRecords());
				request.setAttribute("pageId", drugReportList.get(0)
						.getPageId());
				request.setAttribute("endIndex", drugReportList.get(0)
						.getMaxresults());
				request.setAttribute("startIndex", drugReportList.get(0)
						.getStrtIndex());
				request.setAttribute("endResults", drugReportList.get(0)
						.getStrtIndex() + drugReportList.size());
			}
			if (drugReportList == null) {
				request.setAttribute("totalRecords", 0);
			}
			request.setAttribute("fromDetailed", "N");
			patientForm.setFromDetailed("N");
			request.setAttribute("drugReportList", drugReportList);
			patientForm.setDrugReportList(drugReportList);
			wellnesslist=patientService.getwellness();
			request.setAttribute("wellnesslist", wellnesslist);
			List<LabelBean> distributorList = patientService.getdistributorsList();
			request.setAttribute("distributorList", distributorList);	
			return mapping.findForward("drugsInventoryEditReport");
		}		
		if (lStrActionVal.equalsIgnoreCase("updateDrugInventryReport")) {			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("drugType", StringUtility.getString(request.getParameter("drugTypeVal")));
			paramMap.put("drugCode", StringUtility.getString(request.getParameter("drugCodeVal")));
			paramMap.put("drugName", StringUtility.getString(request.getParameter("drugNameVal")));
			paramMap.put("manufacturerName", StringUtility.getString(request.getParameter("manufacturerNameVal")));
			paramMap.put("distributorName", StringUtility.getString(request.getParameter("distributorNameVal")));
			paramMap.put("batchNo", StringUtility.getString(request.getParameter("batchNoVal")));
			paramMap.put("expiryDate", StringUtility.getString(request.getParameter("expiryDateVal")));
			paramMap.put("invoiceNo", StringUtility.getString(request.getParameter("invoiceNoVal")));
			paramMap.put("quantityAddedDate", StringUtility.getString(request.getParameter("quantityAddedDateVal")));
			paramMap.put("addedQuantity", StringUtility.getString(request.getParameter("addedQuantityVal")));
			paramMap.put("availableQuantity", StringUtility.getString(request.getParameter("availableQuantityVal")));
			paramMap.put("drugTypeNew", StringUtility.getString(request.getParameter("drugTypeNewVal")));
			paramMap.put("drugNameNew", StringUtility.getString(request.getParameter("drugNameNewVal")));
			paramMap.put("manftNameNew", StringUtility.getString(request.getParameter("manftNameNewVal")));
			paramMap.put("distrNameNew", StringUtility.getString(request.getParameter("distrNameNewVal")));
			paramMap.put("lStrUserId", StringUtility.getString(lStrUserId));
			Map<String, Object> respObject = patientService.updateDrugsInve(paramMap);
			request.setAttribute("response", respObject);              
			String result = new Gson().toJson(respObject);
			OutputStream out = response.getOutputStream();
			if (result != null)	out.write(result.getBytes());
			else out.write("".getBytes());
			out.close();
			return null;			
		}
		if(lStrActionVal.equalsIgnoreCase("getDrugInventoryMaster")){	
			OutputStream out = response.getOutputStream();
			Map<String, Object> respObject = patientService.getDrugInventoryMaster();
			request.setAttribute("response", respObject);              
			String result = new Gson().toJson(respObject);
			if (result != null)	out.write(result.getBytes());
			else out.write("".getBytes());
			out.close();
			return null;
		}
		if ("poRep".equalsIgnoreCase(lStrActionVal)){			
			String fromDt=null;
			fromDt=patientForm.getFromDate();
			String toDt=null;
			toDt=patientForm.getDestDate();
			String drugName=null;
			drugName=patientForm.getDrugName();
			String csvFlag = request.getParameter("csvFlag");
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			HashMap paramMap = new HashMap<>();
			paramMap.put("FromDate", fromDt);
			paramMap.put("toDate", toDt);
			paramMap.put("DispId", dispId);
			paramMap.put("DrugName", drugName);
			String wc = request.getParameter("dispname");
			String indNo = request.getParameter("indNo");
			String distributor = request.getParameter("distributor");
			String fromDate = request.getParameter("fromDate");
			String destDate = request.getParameter("destDate");
			//List<DrugsVO> drugsBal=patientService.getDispDrugInventReport(paramMap);
			List<LabelBean> POList = patientService.getindentPORpt(wc, indNo, distributor, fromDate, destDate);
			request.setAttribute("InventDrugsQuant", POList);
			//patientForm.setDrugLt(POList);
			patientForm.setPOList(POList);
			if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
			{
				String lStrDirPath = config.getString("CASESSEARCH.MapPath");
				String lStrFileName = "POReport"+config.getString("DOT_VALUE");
				if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
					lStrFileName = lStrFileName	+ "CSV";
				else
					lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");
				File lcsvfile = createFile(lStrDirPath,lStrFileName);
				char separator = ',';
		        StringBuilder line = new StringBuilder();  
		        int sNo=1;
				if(POList.size()>0)
					{
						line.append("S No");
						line.append(separator);
			            line.append("INDENT NO");
					    line.append(separator);
					    line.append("INDENT DATE");
					    line.append(separator);
						line.append("ITEM ID");
						line.append(separator);
						line.append("DRUGS NAME");
						line.append(separator);
						line.append("DRUG TYPE");
						line.append(separator);
						line.append("DISTRIBUTOR NAME");
						line.append(separator);
						line.append("MANUFACTURER NAME");
						line.append(separator);
						line.append("INDENTED QTY");
						line.append(separator);
						line.append("PO NO");
						line.append(separator);
						line.append("PO DATE");
						line.append(separator);
						line.append("WELLNESS CENTER");
						line.append("\n");
						for(LabelBean drugs : POList)
							{
								line.append(sNo++);
								line.append(separator);
							    line.append(drugs.getINDENT_NO());
							    line.append(separator);
								line.append(drugs.getINDENT_DT());
							    line.append(separator);
								line.append(drugs.getITEMID());
								line.append(separator);
								line.append(drugs.getDRUGNAME());
								line.append(separator);
								line.append(drugs.getDRUGTYPE());
								line.append(separator);							
								line.append(drugs.getDISTRIBUTOR_NAME());
								line.append(separator);
								line.append(drugs.getMNFCTRNAME());
								line.append(separator);
								line.append(drugs.getINDENT_COUNT());
								line.append(separator);
								line.append(drugs.getPO_NO());
								line.append(separator);
								line.append(drugs.getPO_DT());
								line.append(separator);
								line.append(drugs.getDISPNAME());
								line.append("\n");
							}
					request.setAttribute("File", line.toString().getBytes());    
				    response.setContentType(config.getString("ACC.CsvContentType"));
				    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
				    response.setCharacterEncoding("UTF-8");
				    lStrPageName="openFile";
					}
				else
					lStrPageName="indentApprvRpt";
			}
			else			
			lStrPageName="indentApprvRpt";
		}
       if ("dstrRep".equalsIgnoreCase(lStrActionVal)){
			String fromDt=null;
			fromDt=patientForm.getFromDate();
			String toDt=null;
			toDt=patientForm.getDestDate();
			String drugName=null;
			drugName=patientForm.getDrugName();
			String csvFlag = request.getParameter("csvFlag");
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			HashMap paramMap = new HashMap<>();
			paramMap.put("FromDate", fromDt);
			paramMap.put("toDate", toDt);
			paramMap.put("DispId", dispId);
			paramMap.put("DrugName", drugName);
			String poNo=request.getParameter("poNo");
			//List<DrugsVO> drugsBal=patientService.getDispDrugInventReport(paramMap);
			List<LabelBean> POList = patientService.getOnclickPORpt(poNo);
			request.setAttribute("InventDrugsQuant", POList);
			//patientForm.setDrugLt(POList);
			patientForm.setPOList(POList);
			if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
			{
				String lStrDirPath = config.getString("CASESSEARCH.MapPath");
				String lStrFileName = "DistributorWiseReport"+config.getString("DOT_VALUE");
				if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
					lStrFileName = lStrFileName	+ "CSV";
				else
					lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");
				File lcsvfile = createFile(lStrDirPath,lStrFileName);
				char separator = ',';
		        StringBuilder line = new StringBuilder();  
		        int sNo=1;
				if(POList.size()>0)
					{
						line.append("S No");
						line.append(separator);
						line.append("MANUFACTURE PO NUMBER");
						line.append(separator);
						line.append("DISTRIBUTOR NAME");
						line.append(separator);
						line.append("PO DATE");
						line.append("\n");
						for(LabelBean drugs : POList)
							{
								line.append(sNo++);
								line.append(separator);						
								line.append(drugs.getLVL());
								line.append(separator);
								line.append(drugs.getDISTRIBUTOR_NAME());
								line.append(separator);
								line.append(drugs.getPO_DT());
								line.append("\n");	
							}
					request.setAttribute("File", line.toString().getBytes());    
				    response.setContentType(config.getString("ACC.CsvContentType"));
				    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
				    response.setCharacterEncoding("UTF-8");
				    lStrPageName="openFile";
					}
				else
					lStrPageName="indentApprvRpt";
			}
			else			
			lStrPageName="indentApprvRpt";
		}
       if ("drgRep".equalsIgnoreCase(lStrActionVal)){
   			String fromDt=null;
   			fromDt=patientForm.getFromDate();
   			String toDt=null;
   			toDt=patientForm.getDestDate();
   			String drugName=null;
   			drugName=patientForm.getDrugName();
   			String csvFlag = request.getParameter("csvFlag");
   			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
   			HashMap paramMap = new HashMap<>();
   			paramMap.put("FromDate", fromDt);
   			paramMap.put("toDate", toDt);
   			paramMap.put("DispId", dispId);
   			paramMap.put("DrugName", drugName);
   			String poNo=request.getParameter("mnfPoNo");
   			//List<DrugsVO> drugsBal=patientService.getDispDrugInventReport(paramMap);
   			List<LabelBean> POList = patientService.getOnclickMnfPORpt(poNo);
   			request.setAttribute("InventDrugsQuant", POList);
   			//patientForm.setDrugLt(POList);
   			patientForm.setPOList(POList);
   			if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
   			{
   				String lStrDirPath = config.getString("CASESSEARCH.MapPath");
   				String lStrFileName = "DrugWiseReport"+config.getString("DOT_VALUE");
   				if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
   					lStrFileName = lStrFileName	+ "CSV";
   				else
   					lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");
   				File lcsvfile = createFile(lStrDirPath,lStrFileName);
   				char separator = ',';
   		        StringBuilder line = new StringBuilder();  
   		        int sNo=1;
   				if(POList.size()>0)
   					{
   						line.append("S No");
   						line.append(separator);
   						line.append("DRUG NAME");
   						line.append(separator);
   						line.append("MANUFACTURE NAME");
   						line.append(separator);
   						line.append("INDENT COUNT");
   						line.append(separator);
   						line.append("INDENT ID");
   						line.append(separator);
   						line.append("INITIATED BY");
   						line.append(separator);
   						line.append("PO DATE");
   						line.append("\n");
   						for(LabelBean drugs : POList)
   							{
   								line.append(sNo++);
   								line.append(separator);						
   								line.append(drugs.getDRUGNAME());
   								line.append(separator);						
   								line.append(drugs.getMNFCTRNAME());
   								line.append(separator);
   								line.append(drugs.getINDENT_COUNT());
   								line.append(separator);
   								line.append(drugs.getINDENT_ID());
   								line.append(separator);						
   								line.append(drugs.getEMPNAME());
   								line.append(separator);
   								line.append(drugs.getPO_DT());
   								line.append("\n");	
   							}
   					request.setAttribute("File", line.toString().getBytes());    
   				    response.setContentType(config.getString("ACC.CsvContentType"));
   				    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
   				    response.setCharacterEncoding("UTF-8");
   				    lStrPageName="openFile";
   					}
   				else
   					lStrPageName="indentApprvRpt";
   			}
   			else			
   			lStrPageName="indentApprvRpt";
		}
        if ("viewDrugInvRep".equalsIgnoreCase(lStrActionVal)){
			String fromDt=null;
			fromDt=patientForm.getFromDate();
			String toDt=null;
			toDt=patientForm.getDestDate();
			String drugName=null;
			drugName=patientForm.getDrugName();
			String csvFlag = request.getParameter("csvFlag");
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			HashMap paramMap = new HashMap<>();
			paramMap.put("FromDate", fromDt);
			paramMap.put("toDate", toDt);
			paramMap.put("DispId", dispId);
			paramMap.put("DrugName", drugName);
			List<DrugsVO> drugsBal=patientService.getDispDrugInventReport(paramMap);
			request.setAttribute("InventDrugsQuant", drugsBal);
			patientForm.setDrugLt(drugsBal);
			if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
			{
				String lStrDirPath = config.getString("CASESSEARCH.MapPath");
				String lStrFileName = "DrugInventoryReport"+config.getString("DOT_VALUE");
				if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
					lStrFileName = lStrFileName	+ "CSV";
				else
					lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");
				File lcsvfile = createFile(lStrDirPath,lStrFileName);
				char separator = ',';
		        StringBuilder line = new StringBuilder();  
		        int sNo=1;
				if(drugsBal.size()>0)
					{
						line.append("S No");
						line.append(separator);
			            line.append("Drug Type");
					    line.append(separator);
					    line.append("Drug Code");
					    line.append(separator);
						line.append("Drug Name");
						line.append(separator);
						line.append("Manufacturer Name");
						line.append(separator);
						line.append("Distributor Name");
						line.append(separator);
						line.append("Batch Name");
						line.append(separator);
						line.append("Expiry Date");
						line.append(separator);
						line.append("Invoice No");
						line.append(separator);
						line.append("Quantity Added Date");
						line.append(separator);
						line.append("Added Quantity");
						line.append("\n");
						for(DrugsVO drugs : drugsBal)
							{
								line.append(sNo++);
								line.append(separator);
							    line.append(drugs.getDrugTypeName());
							    line.append(separator);
								line.append(drugs.getDrugCode());
							    line.append(separator);
								line.append(drugs.getDrugName());
								line.append(separator);
								line.append(drugs.getDispDrugMfg());
								line.append(separator);
								line.append(drugs.getDispDrugDsbtr());
								line.append(separator);							
								line.append(drugs.getBatchNo());
								line.append(separator);
								line.append(drugs.getExpiryDt());
								line.append(separator);
								line.append(drugs.getInvoiceNo());
								line.append(separator);
								line.append(drugs.getCrtDt());
								line.append(separator);
								line.append(drugs.getDRUGID());
								line.append("\n");
							}
					request.setAttribute("File", line.toString().getBytes());    
				    response.setContentType(config.getString("ACC.CsvContentType"));
				    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
				    response.setCharacterEncoding("UTF-8");
				    lStrPageName="openFile";
					}
				else
					lStrPageName="drugsInventoryReport";
			}
			else			
			lStrPageName="drugsInventoryReport";
		}
        if ("viewDrugTransferForm".equalsIgnoreCase(lStrActionVal))
		{
			try
			{
				List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
				request.setAttribute("dispDrugTypeList", drugsTypeList);
				String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
				List<LabelBean> dispList=patientService.getDispList(dispId);
				request.setAttribute("dispList", dispList);
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
				request.setAttribute("mftList", mfd);
				request.setAttribute("dstrList", dstr);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			lStrPageName = "drugsTransfer";
		}
        if ("saveTransferDrugDetails".equalsIgnoreCase(lStrActionVal))
		{
			String drugName = request.getParameter("drugName");
			List<LabelBean> detList=new ArrayList<LabelBean>();
			String pharmacistId=userIDu;
			String drugId=patientForm.getDispDrugID();
			String drugList=request.getParameter("drugList");
			String drugType=patientForm.getDrugType();
			String transferType=patientForm.getSearchType();
			String TodispId = null;
			TodispId =patientForm.getDispname();
		    HashMap drugparam = new HashMap<>();
		    	String[] arr=drugList.split("@");
		    	for(int i=0;i<arr.length;i++)
		    	{
		    		String[] drugDtls=arr[i].split("~");
		    		String drugCode=drugDtls[0];
		    		String quant=drugDtls[1];
		    		String mnfctr=drugDtls[2];
		    		String dstbr=drugDtls[3];
		    		String batch=drugDtls[5];
		    		String price=drugDtls[4];
		    		long quantity=Long.parseLong(quant);
		    		LabelBean drugDetails=new LabelBean();
		    		drugDetails.setDrugCode(drugCode);
		    		drugDetails.setQuantity(quantity);
		    		drugDetails.setUSERID(pharmacistId);
				    drugDetails.setDrugId(drugId);
				    drugDetails.setBatchNo(batch);
				    drugDetails.setDrugType(drugType);
				    drugDetails.setDrugName(drugName);
			    	drugDetails.setDRUGAMOUNT(price);
			    	drugDetails.setManufaturerName(mnfctr);
					drugDetails.setDistributerName(dstbr);
		    		detList.add(drugDetails);
		    	}
		    	drugparam.put("drugList", detList);
		    	drugparam.put("tdType", transferType);
		    	drugparam.put("toDispId", TodispId);
		    boolean flag=false;
		    flag=patientService.updateTransferDrugs(drugparam);
		    if(flag==true)
		    {
		    	request.setAttribute("status", "Yes");
		    }
		    patientForm.setSearchType("-1");
		    patientForm.setDrugType("-1");
		    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
			request.setAttribute("dispDrugTypeList", drugsTypeList);
			request.setAttribute("type", transferType);
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			List<LabelBean> dispList=patientService.getDispList(dispId);
			request.setAttribute("dispList", dispList);
		    lStrPageName="drugsTransfer";
		}
        if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getDrugListAjax" ) )
		{
			String drugType=request.getParameter("drugType");
			String transferType= null;
			transferType =request.getParameter("transferType");
			List<LabelBean> drugsList=null;
			List<String> drugs=new ArrayList<String>();
			HashMap drugParam = new HashMap<>();
			drugParam.put("drugType", drugType);
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			drugParam.put("dispId", dispId);
			try{
				//if(transferType != null && !"-1".equals(transferType)&& "DtoWC".equalsIgnoreCase(transferType)){
					drugsList=patientService.getDispDrugListSubStore(drugParam);
				//}
				}
				catch(Exception e)
				{//	GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
					e.printStackTrace();
				}
			for (LabelBean labelBean: drugsList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
				{
						drugs.add(labelBean.getID() + "~" + 
								labelBean.getVALUE());
				}
			}
			GLOGGER.info ( "Retrieving Drugs List using Ajax Call in PatientAction." ) ;
		if(drugs!=null && drugs.size() > 0)
			request.setAttribute("AjaxMessage", drugs);
			lStrPageName="ajaxResult";
		}
        if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "getDrugDetailsAjax" ) )
		{
			String drugType=request.getParameter("drugType");
			String drugId=request.getParameter("drugId");
			String transferType=request.getParameter("transferType");
			String userId=userIDu;
			List<LabelBean> details=null;
			List<String> drugs=new ArrayList<String>();
			try{
				details=patientService.getDispDrugDetails(drugType,drugId,userId,transferType);
				}
				catch(Exception e)
				{
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
        if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "viewDrugTransferredList" ) )
      		{
        	HashMap drugParam = new HashMap<>();
        	List<DrugsVO>drugsList= new ArrayList<>();
        	try{
        	String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
			drugParam.put("dispId", dispId);
			drugsList=patientService.getDrugsTransferredList(drugParam);
			patientForm .setDrugLt(drugsList);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	lStrPageName="drugsIntiatedList";
      		}
        if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "viewDrugsIntiatedList" ) )
  		{
    	HashMap drugParam = new HashMap<>();
    	List<DrugsVO>drugsList= new ArrayList<>();
    	try{
    	//String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
    		String reqId= null;
    		reqId=request.getParameter("requestId");
		drugParam.put("reqId", reqId);
		drugsList=patientService.getDrugsIntiatedDtlsList(drugParam);
		patientForm .setDrugLt(drugsList);
		session.setAttribute("drugList", drugsList);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	lStrPageName="drugTransferredList";
  		}
        if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "saveDrugtransferList" ) )
  		{
    	HashMap drugParam = new HashMap<>();
    	List<DrugsVO>drugsList= new ArrayList<>();
    	try{
    	String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
    		String reqId= null;
    		reqId=request.getParameter("requestId");
		drugParam.put("dispId", dispId);
		drugsList =patientForm.getDrugLt();
		drugsList =(List<DrugsVO>)session.getAttribute("drugList");
		drugParam.put("drugList", drugsList);
		drugParam.put("userId", lStrUserId);
		 boolean flag=false;
		 flag=patientService.updateDrugsToWC(drugParam);
		    if(flag==true)
		    {
		    	request.setAttribute("status", "Yes");
		    }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	lStrPageName="drugTransferredList";
  		}
		 if ("saveInventoryDrugDetailsNew".equalsIgnoreCase(lStrActionVal))
		{
			String drugName = request.getParameter("drugName");
			List<LabelBean> detList=new ArrayList<LabelBean>();
			String pharmacistId=userIDu;
			String drugList=request.getParameter("drugList");
		    if(drugName.equalsIgnoreCase("Others"))
		    {
		    	LabelBean drugDetails=new LabelBean();
		    	String drugId=patientForm.getDispDrugID();
			    DateFormat df = new SimpleDateFormat("dd-MM-yyyy"); 
			    String drugType=patientForm.getDrugType();
			    drugDetails.setUSERID(pharmacistId);
			    drugDetails.setDrugId(drugId);			  
			    drugDetails.setDrugType(drugType);
			    drugDetails.setDrugName(drugName);
		    	drugDetails.setNewDrugName(patientForm.getDispDrugName());
		        drugDetails.setDRUGAMOUNT(patientForm.getDispDrugPrice());
		    	drugDetails.setManufaturerName(patientForm.getManufacturerName());
				drugDetails.setDistributerName(patientForm.getDistributerName());
				drugDetails.setBrandName(patientForm.getBrandName());
				detList.add(drugDetails);
		    }
		    boolean flag=false;		    
		    flag=patientService.updateDrugsInventoryNew(detList);
		    if(flag==true)
		    {
		    	request.setAttribute("status", "Yes");
		    }
		    lStrActionVal="viewDrugInventoryFormNew";
		} 
        if ("viewDrugInventoryFormNew".equalsIgnoreCase(lStrActionVal))
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
				request.setAttribute("mftList", mfd);
				request.setAttribute("dstrList", dstr);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			lStrPageName = "drugsInventoryNew";
		}
      //Added by Achalitha
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getledBalReport"))
      		{
      			return mapping.findForward("ledgerReports");
      		}
      	 // investigation Report
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getledBalReports"))
      		{
      			String fromdate= request.getParameter("fromDate");
      			String todate=  request.getParameter("destDate");
      			String drugName=  request.getParameter("drugName");
                //dispname=request.getParameter("dispname");
      		     int pageNo=1,rowsPerPage=10;
       			int startIndex=1,maxResults=0;
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
      			List<LabelBean> ledrepList=patientService.getled(fromdate,todate,dispname,startIndex,maxResults,pageNo,drugName);
      			 if(ledrepList!=null &&ledrepList.size()>0)
                   {
                   	request.setAttribute("totalPages",ledrepList.get(0).getTotalPages());
      					request.setAttribute("totalRecords",ledrepList.get(0).getTotalRecords());
      					request.setAttribute("pageId",ledrepList.get(0).getPageId());
      					request.setAttribute("endIndex",ledrepList.get(0).getMaxresults());
      					request.setAttribute("startIndex",ledrepList.get(0).getStrtIndex());
      					request.setAttribute("endResults",ledrepList.get(0).getStrtIndex()+ledrepList.size());  
                   }
      			request.setAttribute("ledrepList", ledrepList);
      			patientForm.setLedrepList(ledrepList);
      			return mapping.findForward("ledgerReports");
      		}
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getledBalReportsCsv"))
      	 	{  
      	 		int sno=1;
      	 	    int page=-1;
      	 		String fromdate= request.getParameter("fromDate");
      			String todate=  request.getParameter("destDate");
      			String drugName=  request.getParameter("drugName");
                 //dispname=request.getParameter("dispname");
                  //String drugId  =request.getParameter("drugId");
      			//String drugType=request.getParameter("drugTypeNew");
      			//String caseId=request.getParameter("caseId");
      			//String userName=request.getParameter("userName");
                  String reportType=request.getParameter("reportType");
      			String lStrDirPath=config.getString("REPORT.MapPath");
      	        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Stock Ledger Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
              	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
              	char separator = '^';
              	StringBuilder line = new StringBuilder(); 
              	List<LabelBean> ledrepList=null;
              	if(reportType!=null && reportType.equalsIgnoreCase("general"))
      				{
              	   ledrepList=patientService.getled(fromdate,todate,dispname,page,page,page,drugName);
      				}
              	if(reportType!=null && reportType.equalsIgnoreCase("general"))
      			{
              	if(ledrepList!=null &&ledrepList.size()>0)
              	{
              	line.append("S.NO");
              	line.append(separator);
              	line.append("DRUG TYPE");
              	line.append(separator);
              	line.append("DRUG NAME");
              	line.append(separator);
              	line.append("OPENING BALANCE ACTIVE");
              	line.append(separator);
              	line.append("OPENING BALANCE EXPIRED");
              	line.append(separator);
              	line.append("RECEIVED STOCK");
              	line.append(separator);
              	line.append("DISTRIBUTION STOCK ACTIVE");
              	line.append(separator);
              	line.append("DISTRIBUTION STOCK EXPIRED");
              	line.append(separator);
              	line.append("CLOSING BALANCE ACTIVE");
              	line.append(separator);
              	line.append("CLOSING BALANCE EXPIRED");
              	line.append(separator);
              	   line.append("\n");     
              	for(LabelBean drugs:ledrepList)
                   {
              		 line.append(sno);
              		 sno++;
              		 line.append(separator);
              		 line.append(drugs.getDRUG_TYPE());
              		 line.append(separator);
              		 line.append(drugs.getDRUG_NAME());
              		 line.append(separator);
              		 line.append(drugs.getOPENING_BALANCE());
              		 line.append(separator);
              		 line.append(drugs.getOPENING_BALANCE_EXP());
              		 line.append(separator);
              		 line.append(drugs.getRECEIVED_STOCK());
              		 line.append(separator);
              		 line.append(drugs.getDISTRIBUTION_STOCK());
              		 line.append(separator);
              		 line.append(drugs.getDISTRIBUTION_STOCK_EXP());
              		 line.append(separator);
              		 line.append(drugs.getCLOSING_BALANCE());
              		 line.append(separator);
              		 line.append(drugs.getCLOSING_BALANCE_EXP());
              		 line.append(separator);
              		   line.append("\n");     
                   } 
              	}
      			}
              	String dispensary= null;
              	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
              	{
              		dispensary = patientService.getDispName(dispname);
              	}
              	request.setAttribute("File", line.toString().getBytes());    
              	response.setContentType(config.getString("REPORT.CsvContentType"));
              	response.setHeader("Content-Disposition","Attachment; filename=Stock Ledger Report of "+dispensary+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
              	response.setCharacterEncoding("UTF-8");
      	    	return mapping.findForward("openFile");
      	 	}
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getstkIndentedReport"))
      		{
      			String drugId=patientForm.getDispDrugID();
    		    String drugType=patientForm.getDrugTypeID();
    		    String dispname1=patientForm.getDispname();
    		    wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
    		    dispname = wellnesslist.get(0).getDISPNAME();
    		    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    			request.setAttribute("dispDrugTypeList", drugsTypeList);
    			List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
    			request.setAttribute("dispDrugsList", drugsList);
    			request.setAttribute("dispname", dispname);
    			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
    			String updDate = formatter.format(new Date());
    			request.setAttribute("updDate", updDate);
      			return mapping.findForward("stockIndented");
      		}
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getstkIndentedPo"))
      		{
      			String drugId=patientForm.getDispDrugID();
                String drugType=patientForm.getDrugTypeID();
                String dispname2 = request.getParameter("dispId");
                String criticalFalg = request.getParameter("flag");//Chandana - 9008 - Added this to get the flag from frontend
                String dispCode = patientForm.getDispCode();
                String yearFlag=request.getParameter("yearFlag");
                String indentedStock = request.getParameter("indentedStock");
                String lowStock = request.getParameter("lowStock");
                String page = request.getParameter("page");
                String total = request.getParameter("total");//Tejasri -9073- Added this to get the flag from frontend
                String dispid = null;
                dispname = dispname2;
                dispid= wellnesslist.get(0).getDISPID();
                List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
                request.setAttribute("dispDrugTypeList", drugsTypeList);
                List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
                if(page!="Y"){
                List<LabelBean> indentList=patientService.lowStockPO(drugType,dispname+"~"+criticalFalg,drugId);//Chandana - 9008 - Added critical flag concatinating with dispname instead of changing the method signature
                request.setAttribute("indentList", indentList);
                patientForm.setIndentList(indentList);
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                String updDate = formatter.format(new Date());
                request.setAttribute("updDate", updDate);
                request.setAttribute("dispDrugsList", drugsList);
                request.setAttribute("dispname", dispname);
                patientForm.setDispname(dispname);
                request.setAttribute("dispid", dispid);
                request.setAttribute("yearFlag", yearFlag);
                request.setAttribute("indentedStock", indentedStock);
                request.setAttribute("lowStock", lowStock);
                request.setAttribute("page", page);
                request.setAttribute("drugId", drugId);
                request.setAttribute("drugType", drugType);
                request.setAttribute("total",total);
                patientForm.setDrugType(drugType);
                request.setAttribute("criticalFalg", criticalFalg);//Chandana - 9008 - Added this to send back the flag to jsp
                request.setAttribute("lowStockFlag", "N");//Chandana - 8809 - Added this lowStockFlag flag N and sending it to jsp
                //request.setAttribute("indentList", indentList);
      			return mapping.findForward("indentPO");
                }
                else{
                	return mapping.findForward("indentPO");
                }
      		}
    		if (lStrFlagStatus != null && lStrFlagStatus.equalsIgnoreCase("getIndentedPoRpt")) {
    			String lStrContentType="application/vnd.ms-excel";
    			String dispname1 = patientForm.getDispname();
    			String status = patientForm.getStatus();
    			String poDate =patientForm.getFromDate();
    			String yearFlag = request.getParameter("yearFlag");
    			String indentedStock = request.getParameter("indentedStock");
    			String lowStock = request.getParameter("lowStock");
    			String flag = request.getParameter("csvFlag");
    			String dsgnId=null;
    			List<LabelBean> getDsgDtls = null;
    			getDsgDtls = patientService.getDesignationDtls(lStrUserId);
    			if (getDsgDtls != null && !getDsgDtls.isEmpty()) {
    				dsgnId = getDsgDtls.get(0).getVALUE();
    			    request.setAttribute("dsgnId", dsgnId);
    			} else {
    			    request.setAttribute("dsgnId", "");
    			    System.out.println("NO DESIGNATION DETAILS FOUND FOR USER: " + lStrUserId);
    			}
    			String dispId = null;
    			List<LabelBean> getDispId = patientService.getDispIdDtls(lStrUserId);
    			if (getDispId != null && !getDispId.isEmpty()) {
    			    dispId = getDispId.get(0).getCONST();   
    			    request.setAttribute("dispId", dispId);
    			} else {
    			    request.setAttribute("dispId", "");
    			    System.out.println("NO DISPENSARY DETAILS FOUND FOR USER: " + lStrUserId);
    			}
    			List<LabelBean> indentedPOList = patientService.getPoStatusList(dispname1,poDate,dispId);
    			patientForm.setIndentedPOList(indentedPOList);
    			request.setAttribute("yearFlag", yearFlag);
    			request.setAttribute("indentedStock", indentedStock);
    			request.setAttribute("lowStock", lowStock);
    			request.setAttribute("dispId", dispname1);
    			request.setAttribute("status", status);
    			 patientForm.setFromDate(poDate);
    			request.setAttribute("wellnesslist", wellnesslist);	
    			request.setAttribute("indentedPOList", indentedPOList);
    			if(flag!=null && "Y".equalsIgnoreCase(flag))
    			{
    				String lStrFileName = "Indented_PO_Report.xls";
    		        int sNo=1;
    				if(indentedPOList.size()>0)
    					{
    					HSSFWorkbook workbook = new HSSFWorkbook();
    			        Sheet sheet = workbook.createSheet("CasesData");
    			        CellStyle lockedStyle = workbook.createCellStyle();
    			        lockedStyle.setLocked(true);
    			        CellStyle unlockedStyle = workbook.createCellStyle();
    			        unlockedStyle.setLocked(false);
    			        CreationHelper createHelper = workbook.getCreationHelper();

    			        CellStyle dateStyle = workbook.createCellStyle();
    			        dateStyle.cloneStyleFrom(lockedStyle); // keep locked
    			        dateStyle.setDataFormat(
    			            createHelper.createDataFormat().getFormat("dd-MM-yyyy")
    			        );
    			        int rowIdx = 0;
    			        int colIdx = 0;
    			        Row headerRow = sheet.createRow(rowIdx++);
    			        headerRow.createCell(colIdx++).setCellValue("S No");
    			        headerRow.createCell(colIdx++).setCellValue("PO DATE");
    			        headerRow.createCell(colIdx++).setCellValue("PO NO");
    			        headerRow.createCell(colIdx++).setCellValue("STATUS");
    			        headerRow.createCell(colIdx++).setCellValue("WELLNESS CENTER");
    			        for (LabelBean bean : indentedPOList) {
    			        	Row dataRow = sheet.createRow(rowIdx++);
    			        	int dataColIdx = 0;
    			        	
    			        		Cell c1 = dataRow.createCell(dataColIdx++);
    					            c1.setCellValue(sNo++);
    					            c1.setCellStyle(lockedStyle);
    				            Cell c2 = dataRow.createCell(dataColIdx++);
    					            c2.setCellValue(bean.getPODATE());
    					            c2.setCellStyle(dateStyle);
    				            Cell c3 = dataRow.createCell(dataColIdx++);
    					            c3.setCellValue(bean.getPOID());
    					            c3.setCellStyle(lockedStyle);
    				            Cell c4 = dataRow.createCell(dataColIdx++);
    					            c4.setCellValue(bean.getPARTFULL());
    					            c4.setCellStyle(lockedStyle);
    				            Cell c5 = dataRow.createCell(dataColIdx++);
    					            c5.setCellValue(bean.getDISPNAME());
    					            c5.setCellStyle(lockedStyle);
    				            Cell editable1 = dataRow.createCell(dataColIdx++);
    					            editable1.setCellValue("");
    					            editable1.setCellStyle(unlockedStyle);
    				            Cell editable2 = dataRow.createCell(dataColIdx++);
    					            editable2.setCellValue("");
    					            editable2.setCellStyle(unlockedStyle);
    				            sheet.protectSheet("");
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
    			}
    			return mapping.findForward("indentedPoRpt");
    		}
    		if (lStrFlagStatus != null && lStrFlagStatus.equalsIgnoreCase("getIntendPdf")) {
    		    int page = -1;
    			String dispname1 = patientForm.getDispname();
    			String dispName=request.getParameter("dispName");
    			if ("-1".equals(dispName)) {
    			    dispname1 = "All_wcs";
    			}
    			String status = patientForm.getStatus();
    			String poDate =patientForm.getFromDate();
    			String yearFlag = request.getParameter("yearFlag");
    			String indentedStock = request.getParameter("indentedStock");
    			String lowStock = request.getParameter("lowStock");
				    String lStrDirPath = config.getString("FIN.MapPath");
				    File mainDir = new File(lStrDirPath);
				    if (!mainDir.exists()) {
				        mainDir.mkdirs();
				    }
				    String subFolderPath = lStrDirPath + File.separator + "StockIntendPoReports";
				    File subFolder = new File(subFolderPath);
				    if (!subFolder.exists()) {
				        subFolder.mkdirs();
				    }
				    String lStrFileName = subFolderPath + File.separator +
				                          "Report_" + dispName + ".pdf";
				    File xlFile = new File(lStrFileName);
				    List<LabelBean> indentedPOList = patientService.getIntendPdf(dispname1, poDate, page, page, page);
				    PdfGenerator.intendReport(
				            xlFile, indentedPOList, patientForm, "INDENT REPORT");
				    byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);
				    request.setAttribute("File", lbBytes);
				    response.setContentType("application/pdf");
				    response.setHeader("Content-Disposition",
				            "attachment; filename=STOCK_INDENT_REPORT_" + dispName + ".pdf");
				    return mapping.findForward("openFile");
				}
    		if (lStrFlagStatus != null
    				&& lStrFlagStatus.equalsIgnoreCase("poNotRsv")) {
    			String dispname1 = patientForm.getDispname();
    			String status = patientForm.getStatus();
    			String yearFlag = request.getParameter("yearFlag");
    			String indentedStock = request.getParameter("indentedStock");
    			String lowStock = request.getParameter("lowStock");
    			List<LabelBean> indentedPOList = patientService.getpoNotRsv(dispname1);
    			patientForm.setIndentedPOList(indentedPOList);
    			request.setAttribute("yearFlag", yearFlag);
    			request.setAttribute("indentedStock", indentedStock);
    			request.setAttribute("lowStock", lowStock);
    			request.setAttribute("dispId", dispname1);
    			request.setAttribute("status", status);
    			request.setAttribute("wellnesslist", wellnesslist);	
    			request.setAttribute("indentedPOList", indentedPOList);
    			return mapping.findForward("poNotRsv");
    		}
    		if (lStrFlagStatus != null
    				&& lStrFlagStatus.equalsIgnoreCase("poNotRsvCacl")) {
    			try {
    				String dispname1 = patientForm.getDispname();
    				List<LabelBean> indentedPOList = patientService.getpoNotRsv(dispname1);
    				String statList = request.getParameter("statList");
    				String[] arr = statList.split("@");
    				for (int i = 0; i < arr.length; i++) {
    					String drugList = arr[i];
    					String statusList;
    				statusList = patientService.getpoNotRsvCacl(drugList);
    				}
    				request.setAttribute("dispId", dispname1);
    				patientForm.setIndentedPOList(indentedPOList);
    				request.setAttribute("indentedPOList", indentedPOList);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    			return mapping.findForward("poNotRsv");
    		}
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getstkIndentedPoPage1"))
      		{
      			String drugId=patientForm.getDispDrugID();
                String drugType=patientForm.getDrugTypeID();
                String dispname2 = request.getParameter("dispId");
                String dispCode = patientForm.getDispCode();
                String yearFlag=request.getParameter("yearFlag");
                String indentedStock = request.getParameter("indentedStock");
                String lowStock = request.getParameter("lowStock");
                String dispid = null;
                dispname = dispname2;
                dispid= wellnesslist.get(0).getDISPID();
                double noIndentItems=0.0;
                int totalIndentValue=0;
                //Chandana - 9008 - Added below 4 variables to show the total of critical and non critical indent values
                double noCIndentItems=0.0;
                int totalCIndentValue=0;
                double noNcIndentItems=0.0;
                int totalNcIndentValue=0;
                List<LabelBean> indentList=patientService.lowStockwellness(dispname);
                if(indentList.size()>0){
                for(int i=0;i<indentList.size();i++){
                	noIndentItems=noIndentItems+Double.parseDouble(indentList.get(i).getTOTAL_INDENT_VALUE());
                	totalIndentValue=totalIndentValue+Integer.parseInt(indentList.get(i).getNO_INDENT_ITEMS());
                	//Chandana - 9008 - Added below for critical and non critical indent values
                	noCIndentItems=noCIndentItems+Double.parseDouble(indentList.get(i).getTOT_CRTICALINDNT_VALUE());
                	totalCIndentValue=totalCIndentValue+Integer.parseInt(indentList.get(i).getNO_CRTICALINDNT_ITEMS());
                	noNcIndentItems=noNcIndentItems+Double.parseDouble(indentList.get(i).getTOT_NONCINDENT_VALUE());
                	totalNcIndentValue=totalNcIndentValue+Integer.parseInt(indentList.get(i).getNO_NONCINDENT_ITEMS());
                }
                }
                /*DecimalFormat df = new DecimalFormat("#,###");
                df.setMaximumFractionDigits(340);
                String str = df.format(noIndentItems);*/
                String str = String.format("%.2f", noIndentItems);
                String str1 = String.format("%.2f", noCIndentItems);//Chandana - 9008 - Added for Critical
                String str2 = String.format("%.2f", noNcIndentItems);//Chandana - 9008 - Added for non critical
                patientForm.setIndentList(indentList);
                request.setAttribute("indentList", indentList);
                request.setAttribute("dispname", dispname);
                request.setAttribute("dispid", dispid);
                request.setAttribute("yearFlag", yearFlag);
                request.setAttribute("indentedStock", indentedStock);
                request.setAttribute("lowStock", lowStock);
                request.setAttribute("noIndentItems", str);
                request.setAttribute("noCIndentItems", str1);//Chandana - 9008 - setting the critical items and sending to jsp
                request.setAttribute("noNcIndentItems", str2);//Chandana - 9008 - setting the non critical items and sending to jsp
                request.setAttribute("totalIndentValue", totalIndentValue);
                request.setAttribute("totalCIndentValue", totalCIndentValue);//Chandana - 9008 - Added for cirtical indent total
                request.setAttribute("totalNcIndentValue", totalNcIndentValue);//Chandana - 9008 - Added for non critical indent total
      			return mapping.findForward("indentWellness");
      		}
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getstkIndentedPoPage"))
      		{
      			String drugId=patientForm.getDispDrugID();
                String drugType=patientForm.getDrugTypeID();
                String dispname2 = request.getParameter("dispId");
                String dispCode = patientForm.getDispCode();
                String yearFlag=request.getParameter("yearFlag");
                String indentedStock = request.getParameter("indentedStock");
                String lowStock = request.getParameter("lowStock");
                String dispid = null;
                dispname = dispname2;
                dispid= wellnesslist.get(0).getDISPID();
                request.setAttribute("dispname", dispname);
                request.setAttribute("dispid", dispid);
                request.setAttribute("yearFlag", yearFlag);
                request.setAttribute("indentedStock", indentedStock);
                request.setAttribute("lowStock", lowStock);
                SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
                String updDate = formatter.format(new Date());
                request.setAttribute("updDate", updDate);
                request.setAttribute("yearFlag", yearFlag);
                request.setAttribute("indentedStock", indentedStock);
                request.setAttribute("lowStock", lowStock);
                request.setAttribute("lowStockFlag", "N");//Chandana - 8809 - Added this lowStockFlag flag N and sending it to jsp
      			return mapping.findForward("indentPO");
      		}
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("ledgerdrugDetailedReport"))
    		{
    			String fromdate= request.getParameter("fromDate");
    			String todate=  request.getParameter("destDate");
    			String drugName=  request.getParameter("drugName");
    		    //dispname=request.getParameter("dispname");
    			String drugId  =request.getParameter("drugId");
    			String drugType=request.getParameter("drugTypeNew");
    			String caseId=request.getParameter("caseId");
    			//String userName=request.getParameter("userName");
    			int pageNo=1,rowsPerPage=10;
    			int startIndex=1,maxResults=0;
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
                List<LabelBean> drugDetailedReportList=patientService.getLedgerDrugsDetailed(fromdate,todate,dispname, drugId,drugType,caseId,startIndex,maxResults,pageNo,userName,drugName);
                if(drugDetailedReportList!=null &&drugDetailedReportList.size()>0)
                {
                	request.setAttribute("totalPages",drugDetailedReportList.get(0).getTotalPages());
					request.setAttribute("totalRecords",drugDetailedReportList.get(0).getTotalRecords());
					request.setAttribute("pageId",drugDetailedReportList.get(0).getPageId());
					request.setAttribute("endIndex",drugDetailedReportList.get(0).getMaxresults());
					request.setAttribute("startIndex",drugDetailedReportList.get(0).getStrtIndex());
					request.setAttribute("endResults",drugDetailedReportList.get(0).getStrtIndex()+drugDetailedReportList.size());  
                }
                String dispensary= null;
	        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
	        	{
	        		dispensary = patientService.getDispName(dispname);
	        	}
	        	List<LabelBean> users=null;
   				request.setAttribute("fromDetailed", "Y") ;
   				patientForm.setFromDetailed("Y");
                request.setAttribute("drugDetailedReportList", drugDetailedReportList);
                request.setAttribute("dispensary", dispensary);
                patientForm.setDrugReportList(drugDetailedReportList);
    			/*if((drugId!=null &&!"".equalsIgnoreCase(drugId)&&drugType!=null&&!"".equalsIgnoreCase(drugType))||(caseId!=null&&!"".equalsIgnoreCase(caseId)))
    				return mapping.findForward("drugDistributionDetailed");
    			else*/
    			return mapping.findForward("ledgerDetailedReports");
    		}	
      		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getExpReport"))
    		{
    			return mapping.findForward("drugExpiry");
    		}
    		// drugExpiry Report
    				if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getExpReports"))
    				{
    					String fromdate= request.getParameter("fromDate");
    					String todate=  request.getParameter("destDate");
                        dispname=request.getParameter("dispname");
    				     int pageNo=1,rowsPerPage=10;
    		 			int startIndex=1,maxResults=0;
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
    					List<LabelBean> drugExpList=patientService.getexp(fromdate,todate,dispname,startIndex,maxResults,pageNo);
    					 if(drugExpList!=null &&drugExpList.size()>0)
    		             {
    		             	request.setAttribute("totalPages",drugExpList.get(0).getTotalPages());
    							request.setAttribute("totalRecords",drugExpList.get(0).getTotalRecords());
    							request.setAttribute("pageId",drugExpList.get(0).getPageId());
    							request.setAttribute("endIndex",drugExpList.get(0).getMaxresults());
    							request.setAttribute("startIndex",drugExpList.get(0).getStrtIndex());
    							request.setAttribute("endResults",drugExpList.get(0).getStrtIndex()+drugExpList.size());  
    		             }
    					request.setAttribute("drugExpList", drugExpList);
    					patientForm.setDrugExpList(drugExpList);
    					return mapping.findForward("drugExpiry");
    				}
    				if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getExpReportsCsv"))
    			 	{  
    			 		int sno=1;
    			 	    int page=-1;
    			 		String fromdate= request.getParameter("fromDate");
    					String todate=  request.getParameter("destDate");
    		             dispname=request.getParameter("dispname");
    					String lStrDirPath=config.getString("REPORT.MapPath");
    			        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Drug Expiry Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
    		        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
    		        	char separator = '^';
    		        	StringBuilder line = new StringBuilder();   
    		        	List<LabelBean> drugExpList=patientService.getexp(fromdate,todate,dispname,page,page,page);
    		        	if(drugExpList!=null &&drugExpList.size()>0)
    		        	{
    		        	line.append("S.NO");
    		        	line.append(separator);
    		        	line.append("WC NAME");
    		        	line.append(separator);
    		        	line.append("DRUG TYPE");
    		        	line.append(separator);
    		        	line.append("DRUG NAME");
    		        	line.append(separator);
    		        	line.append("MANUFACTURER NAME");
    		        	line.append(separator);
    		        	line.append("DISTRIBUTER NAME");
    		        	line.append(separator);
    		        	line.append("BATCH NO");
    		        	line.append(separator);
    		        	line.append("EXPIRY DATE");
    		        	line.append(separator);
    		        	line.append("QUANTITY");
    		        	line.append(separator);
    		        	   line.append("\n");     
    		        	for(LabelBean drugs:drugExpList)
    		             {
    		        		 line.append(sno);
    		        		 sno++;
    		        		 line.append(separator);
    		        		 line.append(drugs.getDISP_NAME());
    		        		 line.append(separator);
    		        		 line.append(drugs.getDRUG_TYPE());
    		        		 line.append(separator);
    		        		 line.append(drugs.getDRUG_NAME());
    		        		 line.append(separator);
    		        		 line.append(drugs.getMNFCTRNAME());
    		        		 line.append(separator);
    		        		 line.append(drugs.getDSTRBTRNAME());
    		        		 line.append(separator);
    		        		 line.append(drugs.getBATCHNO());
    		        		 line.append(separator);
    		        		 line.append(drugs.getEXPDT());
    		        		 line.append(separator);
    		        		 line.append(drugs.getQUANTITY());
    		        		 line.append(separator);
    		        		   line.append("\n");     
    		             }  
    		        	String dispensary= null;
    		        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
    		        	{
    		        		dispensary = patientService.getDispName(dispname);
    		        	}
    		        	request.setAttribute("File", line.toString().getBytes());    
    		        	response.setContentType(config.getString("REPORT.CsvContentType"));
    		        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
    		        	response.setHeader("Content-Disposition","Attachment; filename=Drug Expiry Report of "+dispensary+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    		        	else
    			        response.setHeader("Content-Disposition","Attachment; filename=Drug Expiry Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    		        	response.setCharacterEncoding("UTF-8");
    		        	}
    		        	return mapping.findForward("openFile");
    			 	}
    				if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getExpiredReport"))
    				{
    					return mapping.findForward("expiredRpt");
    				}
    				// drugExpiry Report
    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getExpiredReports"))
    						{
    							String fromdate= request.getParameter("fromDate");
    							String todate=  request.getParameter("destDate");
    		                     dispname=request.getParameter("dispname");
    						     int pageNo=1,rowsPerPage=10;
    				 			int startIndex=1,maxResults=0;
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
    							List<LabelBean> drugExpList=patientService.getexp(fromdate,todate,dispname,startIndex,maxResults,pageNo);
    							 if(drugExpList!=null &&drugExpList.size()>0)
    				             {
    				             	request.setAttribute("totalPages",drugExpList.get(0).getTotalPages());
    									request.setAttribute("totalRecords",drugExpList.get(0).getTotalRecords());
    									request.setAttribute("pageId",drugExpList.get(0).getPageId());
    									request.setAttribute("endIndex",drugExpList.get(0).getMaxresults());
    									request.setAttribute("startIndex",drugExpList.get(0).getStrtIndex());
    									request.setAttribute("endResults",drugExpList.get(0).getStrtIndex()+drugExpList.size());  
    				             }
    							request.setAttribute("drugExpList", drugExpList);
    							patientForm.setDrugExpList(drugExpList);
    							return mapping.findForward("expiredRpt");
    						}
    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getExpiredReportsCsv"))
    					 	{  
    					 		int sno=1;
    					 	    int page=-1;
    					 		String fromdate= request.getParameter("fromDate");
    							String todate=  request.getParameter("destDate");
    				             dispname=request.getParameter("dispname");
    							String lStrDirPath=config.getString("REPORT.MapPath");
    					        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Expired Drugs Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
    				        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
    				        	char separator = '^';
    				        	StringBuilder line = new StringBuilder();   
    				        	List<LabelBean> drugExpList=patientService.getexp(fromdate,todate,dispname,page,page,page);
    				        	if(drugExpList!=null &&drugExpList.size()>0)
    				        	{
    				        	line.append("S.NO");
    				        	line.append(separator);
    				        	line.append("WC NAME");
    				        	line.append(separator);
    				        	line.append("DRUG TYPE");
    				        	line.append(separator);
    				        	line.append("DRUG NAME");
    				        	line.append(separator);
    				        	line.append("MANUFACTURER NAME");
    				        	line.append(separator);
    				        	line.append("DISTRIBUTER NAME");
    				        	line.append(separator);
    				        	line.append("BATCH NO");
    				        	line.append(separator);
    				        	line.append("EXPIRY DATE");
    				        	line.append(separator);
    				        	line.append("QUANTITY");
    				        	line.append(separator);
    				        	   line.append("\n");     
    				        	for(LabelBean drugs:drugExpList)
    				             {
    				        		 line.append(sno);
    				        		 sno++;
    				        		 line.append(separator);
    				        		 line.append(drugs.getDISP_NAME());
    				        		 line.append(separator);
    				        		 line.append(drugs.getDRUG_TYPE());
    				        		 line.append(separator);
    				        		 line.append(drugs.getDRUG_NAME());
    				        		 line.append(separator);
    				        		 line.append(drugs.getMNFCTRNAME());
    				        		 line.append(separator);
    				        		 line.append(drugs.getDSTRBTRNAME());
    				        		 line.append(separator);
    				        		 line.append(drugs.getBATCHNO());
    				        		 line.append(separator);
    				        		 line.append(drugs.getEXPDT());
    				        		 line.append(separator);
    				        		 line.append(drugs.getQUANTITY());
    				        		 line.append(separator);
    				        		   line.append("\n");     
    				             }  
    				        	String dispensary= null;
    				        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
    				        	{
    				        		dispensary = patientService.getDispName(dispname);
    				        	}
    				        	request.setAttribute("File", line.toString().getBytes());    
    				        	response.setContentType(config.getString("REPORT.CsvContentType"));
    				        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
    				        	response.setHeader("Content-Disposition","Attachment; filename=Expired Drugs Report of "+dispensary+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    				        	else
    					        response.setHeader("Content-Disposition","Attachment; filename=Expired Drugs Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    				        	response.setCharacterEncoding("UTF-8");
    				        	}
    				        	return mapping.findForward("openFile");
    					 	}
    						if ("getDrugsExpiredForRTV".equalsIgnoreCase(lStrActionVal))
    						{
    				        	String search = request.getParameter("search");
    				        	request.setAttribute("search", search);
    				        	List<LabelBean> dstrList=patientService.getDrugDstbtrList();
								request.setAttribute("dstrList", dstrList);
    				        	if(search != null && "Y".equalsIgnoreCase(search)){
    							try 
    							{
    								String fromdate= patientForm.getFromDate();
    								String todate=  patientForm.getDestDate();
    				                dispname=patientForm.getDispname();
    							    String dstrb = patientForm.getDstrName();
    							    String searchType = patientForm.getSearchType();
    					 		    Map dataMap = new HashMap<>();
    								dataMap.put("fromDate", fromdate);
    								dataMap.put("toDate", todate);
    								dataMap.put("dispName", dispname);
    								dataMap.put("distrbutr", dstrb);	
    								dataMap.put("searchType", searchType);	
    								List<LabelBean> drugExpList=patientService.getexpiredDrugs(dataMap);
    								request.setAttribute("drugExpList", drugExpList);
    								patientForm.setDrugExpList(drugExpList);
    							}
    							catch(Exception e)
    							{
    								e.printStackTrace();
    							}
    				         }
    							lStrPageName = "drugsReturnToVendor";
    						}
    						if ("submitRequestForRTV".equalsIgnoreCase(lStrActionVal))
    						{
    				        	try 
    							{   List<LabelBean> detList=new ArrayList<LabelBean>();
    								String pharmacistId=userIDu;
    								dispname=patientForm.getDispname();
    								String drugList=request.getParameter("drugList");
    								String remarks=request.getParameter("remarks");
    								List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    								request.setAttribute("dstrList", dstrList);
    								 HashMap drugparam = new HashMap<>();
    							    	String[] arr=drugList.split("@");
    							    	for(int i=0;i<arr.length;i++)
    							    	{
    							    		String[] drugDtls=arr[i].split("~");
    							    		String drugCode=drugDtls[0];
    							    		String avblquant=drugDtls[1];
    							    		String mnfctr=drugDtls[2];
    							    		String dstbr=drugDtls[3];
    							    		String batch=drugDtls[4];
    							    		String quant=drugDtls[5];
    							    		long avblQuantity=Long.parseLong(avblquant);
    							    		LabelBean drugDetails=new LabelBean();
    							    		drugDetails.setDrugCode(drugCode);
    							    		drugDetails.setQuantity(avblQuantity);
    							    		drugDetails.setUSERID(pharmacistId);
    									    drugDetails.setBatchNo(batch);
    									    drugDetails.setManufaturerName(mnfctr);
    										drugDetails.setDistributerName(dstbr);
    							    		drugDetails.setQUANTITY(quant);
    							    		detList.add(drugDetails);
    							    	}
    							    	drugparam.put("drugList", detList);
    							    	drugparam.put("userId",pharmacistId );
    							    	drugparam.put("dispId", dispname);
    							    	drugparam.put("remarks", remarks);
    							    boolean flag=false;
    							    flag=patientService.submitRequestForRTV(drugparam);
    							    if(flag==true)
    							    {
    							    	request.setAttribute("status", "Yes");
    							    }else{
    							    	request.setAttribute("status", "No");
    							    }
    							}
    							catch(Exception e)
    							{
    								e.printStackTrace();
    							}
    							lStrPageName = "drugsReturnToVendor";
    						}
    						//Tejasri: cr-8927- generating pdf
    						if (lStrActionVal != null && lStrActionVal.equalsIgnoreCase("generatePdf")) {
    						    request.setAttribute("loginName", loginName);
    						    int page = -1;
    						    String poNo = request.getParameter("poNo");
    						    String status = request.getParameter("status");
    						    String poNoForFile = poNo.replaceAll("[\\\\/]", "_");
    						    String lStrDirPath = config.getString("FIN.MapPath");
    						    File mainDir = new File(lStrDirPath);
    						    if (!mainDir.exists()) {
    						        mainDir.mkdirs();
    						    }
    						    String subFolderPath = lStrDirPath + File.separator + "StockIntendPoReports";
    						    File subFolder = new File(subFolderPath);
    						    if (!subFolder.exists()) {
    						        subFolder.mkdirs();
    						    }
    						    String lStrFileName = subFolderPath + File.separator +
    						                          "Report_" + poNoForFile + ".pdf";
    						    File xlFile = new File(lStrFileName);
    						    List<LabelBean> ReportList =
    						            patientService.getGenPdf(poNo, status, page, page, page);
    						    PdfGenerator.prepareReport(
    						            xlFile, ReportList, patientForm, "INDENT REPORT");
    						    byte[] lbBytes = PdfGenerator.getBytesFromFile(xlFile);
    						    request.setAttribute("File", lbBytes);
    						    response.setContentType("application/pdf");
    						    response.setHeader("Content-Disposition",
    						            "attachment; filename=INDENT_REPORT_" + poNoForFile + ".pdf");
    						    return mapping.findForward("openFile");
    						}
    						if ("getDrugsInvoiceList".equalsIgnoreCase(lStrActionVal))
    						{
    				        	String search = request.getParameter("search");
    				        	request.setAttribute("search", search);
    				        	List<LabelBean> dstrList=patientService.getDrugDstbtrList();
								request.setAttribute("dstrList", dstrList);
    				        	if(search != null && "Y".equalsIgnoreCase(search)){
    							try 
    							{
    								String fromdate= patientForm.getFromDate();
    								String todate=  patientForm.getDestDate();
    				                dispname=patientForm.getDispname();
    							    String dstrb = patientForm.getDstrName();
    							    String searchType = patientForm.getSearchType();
    					 		    Map dataMap = new HashMap<>();
    								dataMap.put("fromDate", fromdate);
    								dataMap.put("toDate", todate);
    								dataMap.put("dispName", dispname);
    								dataMap.put("distrbutr", dstrb);	
    								dataMap.put("searchType", searchType);	
    								List<LabelBean> drugExpList=patientService.getDrugsInvoiceList(dataMap);
    								request.setAttribute("dispName", dispname);
    								request.setAttribute("drugExpList", drugExpList);
    								patientForm.setDrugExpList(drugExpList);
    							}
    							catch(Exception e)
    							{
    								e.printStackTrace();
    							}
    				         }
    							lStrPageName = "drugCreditNotePg";
    						}
    						if ("submitCreditNoteRqst".equalsIgnoreCase(lStrActionVal))
    						{
    				        	try 
    							{   List<LabelBean> detList=new ArrayList<LabelBean>();
    								String pharmacistId=userIDu;
    								dispname=request.getParameter("dispName");
    								String drugList=request.getParameter("drugList");
    								List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    								request.setAttribute("dstrList", dstrList);
    								 HashMap drugparam = new HashMap<>();
    							    	String[] arr=drugList.split("@");
    							    	for(int i=0;i<arr.length;i++)
    							    	{
    							    		String[] drugDtls=arr[i].split("~");
    							    		String dstbr=drugDtls[0];
    							    		String invNo=drugDtls[1];
    							    		String invDt=drugDtls[2];
    							    		String retInv=drugDtls[3];
    							    		String retDt=drugDtls[4];
    							    		String amount=drugDtls[5];
    							    		Double amt=Double.parseDouble(amount);
    							    		LabelBean drugDetails=new LabelBean();
    							    		drugDetails.setDSTRBTRNAME(dstbr);
    							    		drugDetails.setINVOICENUMBER(invNo);
    							    		if(invDt != null && !"".equalsIgnoreCase(invDt))
    							    		  drugDetails.setInvDate(sdf.parse(invDt));
    							    		drugDetails.setDrugPrice(amt);
    							    		drugDetails.setISSUE_QUANT(retInv);
    							    		drugDetails.setISSUEDATE(retDt);
    							    		detList.add(drugDetails);
    							    	}
    							    	drugparam.put("drugList", detList);
    							    	drugparam.put("userId",pharmacistId );
    							    	drugparam.put("dispId", dispname);
    							    boolean flag=false;
    							    flag=patientService.submitCreditNoteRequest(drugparam);
    							    if(flag==true)
    							    {
    							    	request.setAttribute("status", "Yes");
    							    }else{
    							    	request.setAttribute("status", "No");
    							    }
    							}
    							catch(Exception e)
    							{
    								e.printStackTrace();
    							}
    							lStrPageName = "drugCreditNotePg";
    						}
    						//Added by Achalitha
    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getReturnVendorReport"))
    	    				{
    							List<LabelBean> dstrList=patientService.getDrugDstbtrList();
								request.setAttribute("dstrList", dstrList);
    	    					return mapping.findForward("returnVendorRpt");
    	    				}
    	    				// return to Vendor Report
    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getReturnVendorReports"))
    	    						{
    	    							String fromdate= request.getParameter("fromDate");
    	    							String todate=  request.getParameter("destDate");
    	    		                    dispname=request.getParameter("dispname");
    	    		                    String dstrName=request.getParameter("dstrName");
    	    		                    List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    									request.setAttribute("dstrList", dstrList);
    	    						     int pageNo=1,rowsPerPage=10;
    	    				 			int startIndex=1,maxResults=0;
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
    	    							List<LabelBean> returnVendorList=patientService.returnVendorList(fromdate,todate,dispname,startIndex,maxResults,pageNo,dstrName);
    	    							 if(returnVendorList!=null &&returnVendorList.size()>0)
    	    				             {
    	    				             	request.setAttribute("totalPages",returnVendorList.get(0).getTotalPages());
    	    									request.setAttribute("totalRecords",returnVendorList.get(0).getTotalRecords());
    	    									request.setAttribute("pageId",returnVendorList.get(0).getPageId());
    	    									request.setAttribute("endIndex",returnVendorList.get(0).getMaxresults());
    	    									request.setAttribute("startIndex",returnVendorList.get(0).getStrtIndex());
    	    									request.setAttribute("endResults",returnVendorList.get(0).getStrtIndex()+returnVendorList.size());  
    	    				             }
    	    							request.setAttribute("returnVendorList", returnVendorList);
    	    							patientForm.setReturnVendorList(returnVendorList);
    	    							return mapping.findForward("returnVendorRpt");
    	    						}
    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getReturnVendorReportsCsv"))
    	    					 	{  
    	    					 		int sno=1;
    	    					 	    int page=-1;
    	    					 		String fromdate= request.getParameter("fromDate");
    	    							String todate=  request.getParameter("destDate");
    	    				            dispname=request.getParameter("dispname");
    	    				            String dstrName=request.getParameter("dstrName");
    	    				            List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    									request.setAttribute("dstrList", dstrList);
    	    							String lStrDirPath=config.getString("REPORT.MapPath");
    	    					        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Return to Vendor Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
    	    				        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
    	    				        	char separator = '^';
    	    				        	StringBuilder line = new StringBuilder();   
    	    				        	List<LabelBean> returnVendorList=patientService.returnVendorList(fromdate,todate,dispname,page,page,page,dstrName);
    	    				        	if(returnVendorList!=null &&returnVendorList.size()>0)
    	    				        	{
    	    				        	line.append("S.NO");
    	    				        	line.append(separator);
    	    				        	line.append("WELLNESS CENTER NAME");
    	    				        	line.append(separator);
    	    				        	line.append("RETURN DATE");
    	    				        	line.append(separator);
    	    				        	line.append("DRUG TYPE");
    	    				        	line.append(separator);
    	    				        	line.append("DRUG NAME");
    	    				        	line.append(separator);
    	    				        	line.append("MANUFACTURER NAME");
    	    				        	line.append(separator);
    	    				        	line.append("DISTRIBUTER NAME");
    	    				        	line.append(separator);
    	    				        	line.append("BATCH NO");
    	    				        	line.append(separator);
    	    				        	line.append("EXPIRY DATE");
    	    				        	line.append(separator);
    	    				        	line.append("PURCHASE INVOICE NO");
    	    				        	line.append(separator);
    	    				        	line.append("PURCHASE INVOICE DATE");
    	    				        	line.append(separator);
    	    				        	line.append("RETURNED QUANTITY");
    	    				        	line.append(separator);
    	    				        	line.append("REMARKS");
    	    				        	line.append(separator);
    	    				        	   line.append("\n");     
    	    				        	for(LabelBean drugs:returnVendorList)
    	    				             {
    	    				        		 line.append(sno);
    	    				        		 sno++;
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getDISP_NAME());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getRETURN_DATE());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getDRUG_TYPE());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getDRUG_NAME());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getMNFCTRNAME());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getDSTRBTRNAME());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getBATCHNO());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getEXPDT());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getINV_NUM());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getINV_DT());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getQUANTITY());
    	    				        		 line.append(separator);
    	    				        		 line.append(drugs.getREMARKS());
    	    				        		 line.append(separator);
    	    				        		   line.append("\n");     
    	    				             }  
    	    				        	String dispensary= null;
    	    				        	String distributorName=null;
    	    				        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
    	    				        	{
    	    				        		dispensary = patientService.getDispName(dispname);
    	    				        	}
    	    				        	if(dstrName!=null &&!"".equalsIgnoreCase(dstrName))
    	    				        	{
    	    				        		distributorName = patientService.getDistrName(dstrName);
    	    				        	}
    	    				        	request.setAttribute("File", line.toString().getBytes());    
    	    				        	response.setContentType(config.getString("REPORT.CsvContentType"));
    	    				        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
    	    				        	response.setHeader("Content-Disposition","Attachment; filename=Return to Vendor Report of "+dispensary+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    				        	else if(dispensary != null && !"".equalsIgnoreCase(dispensary) && distributorName != null && !"".equalsIgnoreCase(distributorName) )
        	    				        response.setHeader("Content-Disposition","Attachment; filename=Return to Vendor Report of "+dispensary+" - " +distributorName+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    				        	else
    	    					        response.setHeader("Content-Disposition","Attachment; filename=Return to Vendor Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    				        	response.setCharacterEncoding("UTF-8");
    	    				        	}
    	    				        	return mapping.findForward("openFile");
    	    					 	}
    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getCreditNoteReport"))
    	    	    				{
    	    							List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    									request.setAttribute("dstrList", dstrList);
    	    	    					return mapping.findForward("creditNoteRpt");
    	    	    				}
    	    	    				// credit Note Report
    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getCreditNoteReports"))
    	    	    						{
    	    	    							String fromdate= request.getParameter("fromDate");
    	    	    							String todate=  request.getParameter("destDate");
    	    	    		                    dispname=request.getParameter("dispname");
    	    	    		                    String dstrName=request.getParameter("dstrName");
    	    	    		                    List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    	    									request.setAttribute("dstrList", dstrList);
    	    	    						     int pageNo=1,rowsPerPage=10;
    	    	    				 			int startIndex=1,maxResults=0;
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
    	    	    							List<LabelBean> creditNoteList=patientService.creditNoteList(fromdate,todate,dispname,startIndex,maxResults,pageNo,dstrName);
    	    	    							 if(creditNoteList!=null &&creditNoteList.size()>0)
    	    	    				             {
    	    	    				             	request.setAttribute("totalPages",creditNoteList.get(0).getTotalPages());
    	    	    									request.setAttribute("totalRecords",creditNoteList.get(0).getTotalRecords());
    	    	    									request.setAttribute("pageId",creditNoteList.get(0).getPageId());
    	    	    									request.setAttribute("endIndex",creditNoteList.get(0).getMaxresults());
    	    	    									request.setAttribute("startIndex",creditNoteList.get(0).getStrtIndex());
    	    	    									request.setAttribute("endResults",creditNoteList.get(0).getStrtIndex()+creditNoteList.size());  
    	    	    				             }
    	    	    							request.setAttribute("creditNoteList", creditNoteList);
    	    	    							patientForm.setCreditNoteList(creditNoteList);
    	    	    							return mapping.findForward("creditNoteRpt");
    	    	    						}
    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getCreditNoteReportsCsv"))
    	    	    					 	{  
    	    	    					 		int sno=1;
    	    	    					 	    int page=-1;
    	    	    					 		String fromdate= request.getParameter("fromDate");
    	    	    							String todate=  request.getParameter("destDate");
    	    	    				            dispname=request.getParameter("dispname");
    	    	    				            String dstrName=request.getParameter("dstrName");
    	    	    				            List<LabelBean> dstrList=patientService.getDrugDstbtrList();
    	    									request.setAttribute("dstrList", dstrList);
    	    	    							String lStrDirPath=config.getString("REPORT.MapPath");
    	    	    					        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Credit Note Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
    	    	    				        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
    	    	    				        	char separator = '^';
    	    	    				        	StringBuilder line = new StringBuilder();   
    	    	    				        	List<LabelBean> creditNoteList=patientService.creditNoteList(fromdate,todate,dispname,page,page,page,dstrName);
    	    	    				        	if(creditNoteList!=null &&creditNoteList.size()>0)
    	    	    				        	{
    	    	    				        	line.append("S.NO");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("WELLNESS CENTER NAME");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("CREDIT NOTE ENTERED DATE");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("VENDOR NAME");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("PURCHASE INVOICE NO");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("PURCHASE INVOICE DATE");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("RETURN INVOICE NO");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("RETURN INVOICE DATE");
    	    	    				        	line.append(separator);
    	    	    				        	line.append("AMOUNT");
    	    	    				        	line.append(separator);
    	    	    				        	   line.append("\n");     
    	    	    				        	for(LabelBean drugs:creditNoteList)
    	    	    				             {
    	    	    				        		 line.append(sno);
    	    	    				        		 sno++;
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getDISP_NAME());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getCRT_DATE());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getDSTRBTRNAME());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getINV_NUM());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getINV_DT());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getRETURN_INV_NUM());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getRETURN_INV_DT());
    	    	    				        		 line.append(separator);
    	    	    				        		 line.append(drugs.getAMOUNT());
    	    	    				        		 line.append(separator);
    	    	    				        		   line.append("\n");     
    	    	    				             }  
    	    	    				        	String dispensary= null;
    	    	    				        	String distributorName=null;
    	    	    				        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
    	    	    				        	{
    	    	    				        		dispensary = patientService.getDispName(dispname);
    	    	    				        	}
    	    	    				        	if(dstrName!=null &&!"".equalsIgnoreCase(dstrName))
    	    	    				        	{
    	    	    				        		distributorName = patientService.getDistrName(dstrName);
    	    	    				        	}
    	    	    				        	request.setAttribute("File", line.toString().getBytes());    
    	    	    				        	response.setContentType(config.getString("REPORT.CsvContentType"));
    	    	    				        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
    	    	    				        	response.setHeader("Content-Disposition","Attachment; filename=Credit Note Report of "+dispensary+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    				        	else if(dispensary != null && !"".equalsIgnoreCase(dispensary) && distributorName != null && !"".equalsIgnoreCase(distributorName) )
    	        	    				        response.setHeader("Content-Disposition","Attachment; filename=Credit Note Report of "+dispensary+" - " +distributorName+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    				        	else
    	    	    					        response.setHeader("Content-Disposition","Attachment; filename=Credit Note Report from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    				        	response.setCharacterEncoding("UTF-8");
    	    	    				        	}
    	    	    				        	return mapping.findForward("openFile");
    	    	    					 	}
    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getIndentReport"))
    	    	    	    				{   
    	    	    							String drugId=patientForm.getDispDrugID();
    	    	    	    				    String drugType=patientForm.getDrugType();
    	    	    	    				    String dispname2=patientForm.getDispname();
    	    	    	    				    String lastIssued = request.getParameter("lastIssued")!=null?(String)request.getParameter("lastIssued"):"";
    	    	    	    				    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    	    	    	    					request.setAttribute("dispDrugTypeList", drugsTypeList);
    	    	    	    					List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispensaryId);
    	    	    	    					request.setAttribute("dispDrugsList", drugsList);
    	    	    	    					request.setAttribute("lastIssued", lastIssued);
    	    	    	    					return mapping.findForward("indentRpt");
    	    	    	    				}
    	    	    	    				// Indent Report
    	    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getIndentReports"))
    	    	    	    						{
    	    	    	    		                    dispname=request.getParameter("dispname");
    	    	    	    		                    String drugName=request.getParameter("drugName");
    	    	    	    		                    String drugType=request.getParameter("drugType");
    	    	    	    		                    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    	    	    	    	    					request.setAttribute("dispDrugTypeList", drugsTypeList);
    	    	    	    	    					List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
    	    	    	    	    					request.setAttribute("dispDrugsList", drugsList);
    	    	    	    						     int pageNo=1,rowsPerPage=10;
    	    	    	    				 			int startIndex=1,maxResults=0;
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
    	    	    	    							List<LabelBean> indentList=patientService.indentList(dispname,startIndex,maxResults,pageNo,drugName);
    	    	    	    							 if(indentList!=null &&indentList.size()>0)
    	    	    	    				             {
    	    	    	    				             	request.setAttribute("totalPages",indentList.get(0).getTotalPages());
    	    	    	    									request.setAttribute("totalRecords",indentList.get(0).getTotalRecords());
    	    	    	    									request.setAttribute("pageId",indentList.get(0).getPageId());
    	    	    	    									request.setAttribute("endIndex",indentList.get(0).getMaxresults());
    	    	    	    									request.setAttribute("startIndex",indentList.get(0).getStrtIndex());
    	    	    	    									request.setAttribute("endResults",indentList.get(0).getStrtIndex()+indentList.size());  
    	    	    	    				             }
    	    	    	    							request.setAttribute("indentList", indentList);
    	    	    	    							patientForm.setIndentList(indentList);
    	    	    	    							SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
    	    	    	    							String updDate = formatter.format(new Date());
    	    	    	    							request.setAttribute("updDate", updDate);
    	    	    	    							return mapping.findForward("indentRpt");
    	    	    	    						}
    	    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getIndentReportsCsv"))
    	    	    	    					 	{  
    	    	    	    					 		int sno=1;
    	    	    	    					 	    int page=-1;
    	    	    	    				            dispname=request.getParameter("dispname");
    	    	    	    				            String drugName=request.getParameter("drugName");
    	    	    	    				            String drugType=request.getParameter("drugType");
    	    	    	    		                    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    	    	    	    	    					request.setAttribute("dispDrugTypeList", drugsTypeList);
    	    	    	    	    					List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
    	    	    	    	    					request.setAttribute("dispDrugsList", drugsList);
    	    	    	    				            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
    	    	    	    							String updDate = formatter.format(new Date());
    	    	    	    							String lStrDirPath=config.getString("REPORT.MapPath");
    	    	    	    					        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Indent Report "+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
    	    	    	    				        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
    	    	    	    				        	char separator = '^';
    	    	    	    				        	StringBuilder line = new StringBuilder();   
    	    	    	    				        	List<LabelBean> indentList=patientService.indentList(dispname,page,page,page,drugName);
    	    	    	    				        	if(indentList!=null &&indentList.size()>0)
    	    	    	    				        	{
    	    	    	    				        	line.append("S.NO");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	line.append("DRUG TYPE");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	line.append("DRUG NAME");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	line.append("AVERAGE MONTHLY CONSUMPTION(LAST 3 MONTHS)");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	line.append("REQUIRED STOCK FOR NEXT 3 MONTHS");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	line.append("AVAILABLE STOCK");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	line.append("STOCK TO BE PROCURED");
    	    	    	    				        	line.append(separator);
    	    	    	    				        	   line.append("\n");
    	    	    	    				        	for(LabelBean drugs:indentList)
    	    	    	    				             {
    	    	    	    				        		 line.append(sno);
    	    	    	    				        		 sno++;
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		 line.append(drugs.getDRUG_TYPE());
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		 line.append(drugs.getDRUG_NAME());
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		 line.append(drugs.getAVG_STK());
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		 line.append(drugs.getREQ());
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		 line.append(drugs.getAVL());
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		 line.append(drugs.getSTOCK());
    	    	    	    				        		 line.append(separator);
    	    	    	    				        		   line.append("\n");     
    	    	    	    				             }  
    	    	    	    				        	String dispensary= null;
    	    	    	    				        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
    	    	    	    				        	{
    	    	    	    				        		dispensary = patientService.getDispName(dispname);
    	    	    	    				        	}
    	    	    	    				        	request.setAttribute("File", line.toString().getBytes());    
    	    	    	    				        	response.setContentType(config.getString("REPORT.CsvContentType"));
    	    	    	    				        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
    	    	    	    				        	response.setHeader("Content-Disposition","Attachment; filename=Indent Report of "+dispensary+" on "+updDate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    	    				        	else
    	    	    	    					        response.setHeader("Content-Disposition","Attachment; filename=Indent Report on "+updDate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    	    				        	response.setCharacterEncoding("UTF-8");
    	    	    	    				        	}
    	    	    	    				        	return mapping.findForward("openFile");
    	    	    	    					 	}
    	    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getIndentReportNew"))
    	    	    	    	    				{   
    	    	    	    							String drugId=patientForm.getDispDrugID();
    	    	    	    	    				    String drugType=patientForm.getDrugType();
    	    	    	    	    				    String dispname3=patientForm.getDispname();
    	    	    	    	    				    String yearFlag=request.getParameter("yearFlag");
    	    	    	    	    				    String indentedStock = request.getParameter("indentedStock");
    	    	    	    	    				    String lowStock = request.getParameter("lowStock");
    	    	    	    	    				    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    	    	    	    	    					request.setAttribute("dispDrugTypeList", drugsTypeList);
    	    	    	    	    					List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispensaryId);
    	    	    	    	    					request.setAttribute("dispDrugsList", drugsList);request.setAttribute("yearFlag", yearFlag);
    	    	    	    	    					request.setAttribute("indentedStock", indentedStock);
    	    	    	    	    					request.setAttribute("lowStock", lowStock);	
    	    	    	    	    					return mapping.findForward("indentRptNew");
    	    	    	    	    				}
    	    	    	    	    				// Indent Report
    	    	    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getIndentReportsNew"))
    	    	    	    	    						{
    	    	    	    	    		                    dispname=request.getParameter("dispname");
    	    	    	    	    		                    String drugName=request.getParameter("drugName");
    	    	    	    	    		                    String drugType=request.getParameter("drugType");
    	    	    	    	    		                    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    	    	    	    	    	    					request.setAttribute("dispDrugTypeList", drugsTypeList);
    	    	    	    	    	    					List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
    	    	    	    	    	    					request.setAttribute("dispDrugsList", drugsList);
    	    	    	    	    						     int pageNo=1,rowsPerPage=10;
    	    	    	    	    				 			int startIndex=1,maxResults=0;
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
    	    	    	    	    							List<LabelBean> indentList=patientService.indentListNew(dispname,startIndex,maxResults,pageNo,drugName);
    	    	    	    	    							 if(indentList!=null &&indentList.size()>0)
    	    	    	    	    				             {
    	    	    	    	    				             	request.setAttribute("totalPages",indentList.get(0).getTotalPages());
    	    	    	    	    									request.setAttribute("totalRecords",indentList.get(0).getTotalRecords());
    	    	    	    	    									request.setAttribute("pageId",indentList.get(0).getPageId());
    	    	    	    	    									request.setAttribute("endIndex",indentList.get(0).getMaxresults());
    	    	    	    	    									request.setAttribute("startIndex",indentList.get(0).getStrtIndex());
    	    	    	    	    									request.setAttribute("endResults",indentList.get(0).getStrtIndex()+indentList.size());  
    	    	    	    	    				             }
    	    	    	    	    							request.setAttribute("indentList", indentList);
    	    	    	    	    							patientForm.setIndentList(indentList);
    	    	    	    	    							SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
    	    	    	    	    							String updDate = formatter.format(new Date());
    	    	    	    	    							request.setAttribute("updDate", updDate);
    	    	    	    	    							return mapping.findForward("indentRptNew");
    	    	    	    	    						}
    	    	    	    	    						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getIndentReportsCsvNew"))
    	    	    	    	    					 	{  
    	    	    	    	    					 		int sno=1;
    	    	    	    	    					 	    int page=-1;
    	    	    	    	    				            dispname=request.getParameter("dispname");
    	    	    	    	    				            String drugName=request.getParameter("drugName");
    	    	    	    	    				            String drugType=request.getParameter("drugType");
    	    	    	    	    		                    List<LabelBean> drugsTypeList=patientService.getDrugTypeList();
    	    	    	    	    	    					request.setAttribute("dispDrugTypeList", drugsTypeList);
    	    	    	    	    	    					List<LabelBean> drugsList=patientService.getDispDrugList(drugType,dispname);
    	    	    	    	    	    					request.setAttribute("dispDrugsList", drugsList);
    	    	    	    	    				            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a"); 
    	    	    	    	    							String updDate = formatter.format(new Date());
    	    	    	    	    							String lStrDirPath=config.getString("REPORT.MapPath");
    	    	    	    	    					        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Indent Report "+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
    	    	    	    	    				        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
    	    	    	    	    				        	char separator = '^';
    	    	    	    	    				        	StringBuilder line = new StringBuilder();   
    	    	    	    	    				        	List<LabelBean> indentList=patientService.indentListNew(dispname,page,page,page,drugName);
    	    	    	    	    				        	if(indentList!=null &&indentList.size()>0)
    	    	    	    	    				        	{
    	    	    	    	    				        	line.append("S.NO");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	line.append("DRUG TYPE");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	line.append("DRUG NAME");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	line.append("AVERAGE MONTHLY CONSUMPTION");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	line.append("REQUIRED STOCK FOR NEXT 3 MONTHS");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	line.append("AVAILABLE STOCK");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	line.append("STOCK TO BE PROCURED");
    	    	    	    	    				        	line.append(separator);
    	    	    	    	    				        	   line.append("\n");
    	    	    	    	    				        	for(LabelBean drugs:indentList)
    	    	    	    	    				             {
    	    	    	    	    				        		 line.append(sno);
    	    	    	    	    				        		 sno++;
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		 line.append(drugs.getDRUG_TYPE());
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		 line.append(drugs.getDRUG_NAME());
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		 line.append(drugs.getAVG_STK());
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		 line.append(drugs.getREQ());
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		 line.append(drugs.getAVL());
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		 line.append(drugs.getSTOCK());
    	    	    	    	    				        		 line.append(separator);
    	    	    	    	    				        		   line.append("\n");     
    	    	    	    	    				             }  
    	    	    	    	    				        	String dispensary= null;
    	    	    	    	    				        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
    	    	    	    	    				        	{
    	    	    	    	    				        		dispensary = patientService.getDispName(dispname);
    	    	    	    	    				        	}
    	    	    	    	    				        	request.setAttribute("File", line.toString().getBytes());    
    	    	    	    	    				        	response.setContentType(config.getString("REPORT.CsvContentType"));
    	    	    	    	    				        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
    	    	    	    	    				        	response.setHeader("Content-Disposition","Attachment; filename=Indent Report of "+dispensary+" on "+updDate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    	    	    				        	else
    	    	    	    	    					        response.setHeader("Content-Disposition","Attachment; filename=Indent Report on "+updDate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
    	    	    	    	    				        	response.setCharacterEncoding("UTF-8");
    	    	    	    	    				        	}
    	    	    	    	    				        	return mapping.findForward("openFile");
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
//    	    	    	    									GLOGGER.error ( "Exception occurred using getDrugListAjax actionFlag in PatientAction." +e.getMessage()) ;
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
    	    	    	    							//For Adding Others at the end.
    	    	    	    						/*	if(temp.getID()!=null && temp.getVALUE()!=null)
    	    	    	    							{
    	    	    	    								drugs.add(temp.getID()+ "~" + temp.getVALUE());
    	    	    	    							}*/
    	    	    	    							GLOGGER.info ( "Retrieving Drugs List using Ajax Call in PatientAction." ) ;
    	    	    	    							//doctorsList.add("0~Others");
    	    	    	    						 if(drugs!=null && drugs.size() > 0)
    	    	    	    							request.setAttribute("AjaxMessage", drugs);
    	    	    	    							lStrPageName="ajaxResult";
    	    	    	    						}
    	    	    	    						if ("saveManfctDrugDetailsNew".equalsIgnoreCase(lStrActionVal)) {
    	    	    	    				            boolean flag = false;
    	    	    	    				            HashMap paramMap = new HashMap<>();
    	    	    	    							paramMap.put("manufacturerName", patientForm.getManufacturerName());
    	    	    	    							paramMap.put("userId", userIDu);
    	    	    	    							flag = patientService.saveManfctDrugDetailsNew(paramMap);
    	    	    	    							if (flag == true) {
    	    	    	    								request.setAttribute("status", "Yes");
    	    	    	    							}
    	    	    	    							lStrActionVal = "viewDrugManfctFormNew";
    	    	    	    						}
    	    	    	    						if ("saveDistrDrugDetailsNew".equalsIgnoreCase(lStrActionVal)) {
    	    	    	    				            boolean flag = false;
    	    	    	    				            HashMap paramMap = new HashMap<>();
    	    	    	    							paramMap.put("distributerName", patientForm.getDistributerName());
    	    	    	    							paramMap.put("userId", userIDu);
    	    	    	    							flag = patientService.saveDistrDrugDetailsNew(paramMap);
    	    	    	    							if (flag == true) {
    	    	    	    								request.setAttribute("status", "Yes");
    	    	    	    							}
    	    	    	    							lStrActionVal = "viewDrugDistrFormNew";
    	    	    	    						}
		if ("viewDrugManfctFormNew".equalsIgnoreCase(lStrActionVal)) {
			try {
				List<LabelBean> mftrList = patientService.getDrugMftrList();
				List<String> mfd = new ArrayList<String>();
				if (mftrList != null) {
					for (LabelBean lb : mftrList) {
						String temp = lb.getVALUE() + "-" + lb.getID();
						mfd.add(temp);
					}
				}
				request.setAttribute("mftList", mfd);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lStrPageName = "drugsManfctNew";
		}
		if ( lStrActionVal!=null && lStrActionVal.equalsIgnoreCase ("leaveResultList"))
		{
			fromDisp = request.getParameter("fromDispnsry");
			String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
					List<LabelBean> doctorNames = null; 
					 doctorNames=commonService.getDoctorNames(lStrUserId,dispId);
					request.setAttribute("dispDoctorNames", doctorNames);
					lStrPageName="leaveEmp";
		}
		if ("viewDrugDistrFormNew".equalsIgnoreCase(lStrActionVal)) {
			try {
				List<LabelBean> dstrList = patientService.getDrugDstbtrList();
				List<String> dstr = new ArrayList<String>();
				if (dstrList != null) {
					for (LabelBean lb : dstrList) {
						String temp = lb.getVALUE() + "-" + lb.getID();
						dstr.add(temp);
					}
				}
				request.setAttribute("dstrList", dstr);
			} catch (Exception e) {
				e.printStackTrace();
			}
			lStrPageName = "drugsDistrNew";
		}
		//Patient Drugs Report
				if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getPatDrugReport"))
				{
					return mapping.findForward("PatDrugReport");
				}
						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getPatDrugReports"))
						{
							String fromdate= request.getParameter("fromDate");
							String todate=  request.getParameter("destDate");
		                     dispname=request.getParameter("dispname");
						     int pageNo=1,rowsPerPage=10;
				 			int startIndex=1,maxResults=0;
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
							List<LabelBean> patList=patientService.getpat(fromdate,todate,dispname,startIndex,maxResults,pageNo);
							 if(patList!=null &&patList.size()>0)
				             {
				             	request.setAttribute("totalPages",patList.get(0).getTotalPages());
									request.setAttribute("totalRecords",patList.get(0).getTotalRecords());
									request.setAttribute("pageId",patList.get(0).getPageId());
									request.setAttribute("endIndex",patList.get(0).getMaxresults());
									request.setAttribute("startIndex",patList.get(0).getStrtIndex());
									request.setAttribute("endResults",patList.get(0).getStrtIndex()+patList.size());  
				             }
							request.setAttribute("patList", patList);
							patientForm.setPatList(patList);
							return mapping.findForward("PatDrugReport");
						}
						if ("submitLowStockList".equalsIgnoreCase(lStrActionVal))
						{//Chandana - 8809
						    String message = "FAIL";
						    wellnesslist = patientService.checkUsrMappedToWellness(lStrUserId);
						    if (wellnesslist.size() > 0) 
						        dispname = wellnesslist.get(0).getDISPNAME();
						    try
						    {   
						        List<LabelBean> detList = new ArrayList<LabelBean>();
						        String inchargeId = userIDu;
						        String dispId = request.getParameter("dispId");
						        String remarks = request.getParameter("remarks");
						        HashMap<String, Object> drugparam = new HashMap<String, Object>();
						        boolean isavailable = true;
						        String drugList = request.getParameter("drugList");
						        String[] arr = drugList.split("@");
						        int i = 1;
						        for (String item : arr)
						        {
						            if (item == null || item.trim().isEmpty()) {
						                i++;
						                continue;
						            }
						            String[] drugDtls = item.split("~");
						                LabelBean drugDetails = new LabelBean();

						                drugDetails.setDrugCode(drugDtls[0]);
						                drugDetails.setDRUGNAME(drugDtls[1]);
						                drugDetails.setSTOCK(drugDtls[2]);
						                drugDetails.setREQ(drugDtls[3]);
						                drugDetails.setAVL(drugDtls[4]);
						                drugDetails.setIND_NOTRECV(drugDtls[5]);
						                drugDetails.setPRICE(drugDtls[6]);
						                drugDetails.setTOT_STOCKPRICE(drugDtls[7]);
						                drugDetails.setREMARKS(drugDtls[8]);
						                drugDetails.setACTIVE_YN("I");
						                drugDetails.setITEM_ID(drugDtls[10]);
						                drugDetails.setDRUG_TYPE(drugDtls[11]);

						                detList.add(drugDetails);
						        }
						        drugparam.put("drugList", detList);
						        drugparam.put("userId", inchargeId);
						        drugparam.put("dispId", dispId);
						        drugparam.put("wcCenter", dispname);
						        drugparam.put("remarks", remarks);
						        boolean flag = false;
						        flag = patientService.submitLowStockList(drugparam);
						        if(flag == true)
						            message = "SUCCESS";
						    }
						    catch(Exception e)
						    {
						        e.printStackTrace();
						    }
						    request.setAttribute("AjaxMessage", message);
						    lStrPageName = "ajaxResult";
						}

				if ("submitLowStockListAdmin".equalsIgnoreCase(lStrActionVal))
				{
					String message="FAIL";
					try 
					{   List<LabelBean> detList=new ArrayList<LabelBean>();
						String inchargeId=userIDu;
						String reqId = null;
						dispname=request.getParameter("dispname");
						String dispId = request.getParameter("dispId");
						String remarks  = request.getParameter("remarks");
						Map<String, String> indNumList = new HashMap<String, String>();
						 HashMap<String,Object> drugparam = new HashMap<String,Object>();
					    	boolean isavailable= true;
						    reqId="IND";
						    String drugList=request.getParameter("drugList");
							String[] arr=drugList.split("@");
						    for(int i=0;i<arr.length;i++)
					    	{
						//if(request.getParameter("drugList"+i) !=null && !"".equals(request.getParameter("drugList"+i))){
		    				//String s = request.getParameter("drugList"+i);
				    		String[] drugDtls=arr[i].split("~");
					    		//String[] drugDtls=request.getParameter("drugList"+i).split("~");
			    			int arraySize = drugDtls.length;
			    		String indNum = "";
			    		if(null != drugDtls[arraySize-3] && drugDtls[arraySize-3].startsWith("IND")) {
			    			indNum = drugDtls[arraySize-3];
			    		}
			    		else
			    			indNum = reqId;
			    		LabelBean drugDetails=new LabelBean();
			    		//drugDetails.setDISPID(drugDtls[0]);
			    		drugDetails.setDrugCode(drugDtls[0]);
			    		drugDetails.setDRUGNAME(drugDtls[1]);
			    		drugDetails.setSTOCK(drugDtls[2]);
			    		drugDetails.setREQ(drugDtls[3]);
			    		drugDetails.setAVL(drugDtls[4]);
			    		drugDetails.setIND_NOTRECV(drugDtls[5]);
			    		drugDetails.setPRICE(drugDtls[6]);
			    		drugDetails.setTOT_STOCKPRICE(drugDtls[7]);
			    		drugDetails.setREMARKS(drugDtls[8]);
			    		drugDetails.setINDENT_ID(indNum);
			    		drugDetails.setACTIVE_YN("N");
			    		drugDetails.setITEM_ID(drugDtls[10]);
			    		drugDetails.setDRUG_TYPE(drugDtls[11]);
			    		if(!indNum.equals("IND"))
			    		indNumList.put(indNum, indNum);
			    		detList.add(drugDetails);
			    	}
					    	drugparam.put("drugList", detList);
					    	drugparam.put("userId",inchargeId );
					    	drugparam.put("dispId", dispId);
					    	drugparam.put("wcCenter", dispname);
					    	drugparam.put("remarks", remarks);
					    boolean flag=false;
					    //flag=patientService.moveLowStockDataFromWCItoAudit(indNumList);
					    flag=patientService.submitLowStockList(drugparam);
					    if(flag==true)
					    {
					    	message = "SUCCESS";
					    }
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
/*//		        	return mapping.findForward("NewRegistrationSuccess");
					lStrPageName = "newRegistrationSuccess";*/
		        	request.setAttribute("AjaxMessage", message);	
					lStrPageName="ajaxResult";
				}
				if ("submitStore".equalsIgnoreCase(lStrActionVal))
				{
					String message="FAIL";
					try 
					{   
						 HashMap<String,Object> drugparam = new HashMap<String,Object>();
						 List<LabelBean> result = null;
					    	String storeList=request.getParameter("storeList");
					    	drugparam.put("storeList", storeList);
					    	drugparam.put("userIDu", userIDu);
							 String res=patientService.submitStoreKeeper(drugparam);
					    	message = res;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
		        	request.setAttribute("AjaxMessage", message);	
					lStrPageName="ajaxResult";
				}
				if ("submitIndented".equalsIgnoreCase(lStrActionVal))
				{
					String message="FAIL";
					try 
					{   List<LabelBean> detList=new ArrayList<LabelBean>();
						dispname=request.getParameter("dispname");
						String dispId = request.getParameter("dispId");
						 HashMap<String,Object> drugparam = new HashMap<String,Object>();
						 String inchargeId=userIDu;
						 List<LabelBean> result = null;
						 List<LabelBean> result1 = null;
					    	boolean isavailable= true;
					    	String drugList=request.getParameter("drugList");
					    	boolean flag=false;
					    	System.out.println(drugList);
					    	drugList = drugList.substring(0, drugList.length()-1).replaceAll("@","','");
					    	System.out.println(drugList);
					    		drugparam.put("drugList", drugList);
					    		drugparam.put("dispId", dispId);
					    		drugparam.put("wcCenter", dispname);
					    	if(dispId!=null &&!"".equalsIgnoreCase(dispId))
					    		session.setAttribute("dispId",dispId);
							 String res=patientService.submitIndented(drugparam);
							 if(res!=null &&!"".equalsIgnoreCase(res))
						    		session.setAttribute("res",res);
					    	message = "SUCCESS"+"~"+res;
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
		        	request.setAttribute("AjaxMessage", message);	
					lStrPageName="ajaxResult";
				}
				if ("avlOnclick".equalsIgnoreCase(lStrActionVal))
				{
					List<LabelBean> POList =new ArrayList<LabelBean>();
					String dispname1=request.getParameter("dispId");
					String drugCode=request.getParameter("drugCode");
					try 
					{   
					    	POList = patientService.getOnclickAvlRpt(dispname1,drugCode);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					String message = new Gson().toJson(POList);
					response.setContentType("application/json");
					request.setAttribute("AjaxMessage", message);
					lStrPageName = "ajaxResult";
				}
				if (lStrFlagStatus != null && lStrFlagStatus.equalsIgnoreCase("poNoOnclick")) {
					String lStrContentType="application/vnd.ms-excel";
					String Flag = request.getParameter("csvFlag");
	    			List<LabelBean> POList =new ArrayList<LabelBean>();
	    			String poNo=request.getParameter("poNo");
	    			String status=request.getParameter("status");
	    			try 
	    			{   
	    			    	POList = patientService.getOnclickPORpt(poNo,status);
	    			}
	    			catch(Exception e)
	    			{
	    				e.printStackTrace();
	    			}
	    			if (Flag != null && "Y".equalsIgnoreCase(Flag)) {

	    		        String fileName = "PO_Details_" + poNo + ".xls";
	    		        int sNo = 1;
	    		        if(POList.size()>=0){
	    		        	HSSFWorkbook workbook = new HSSFWorkbook();
	    			        Sheet sheet = workbook.createSheet("CasesData");
	    			        CellStyle lockedStyle = workbook.createCellStyle();
	    			        lockedStyle.setLocked(true);
	    			        CellStyle unlockedStyle = workbook.createCellStyle();
	    			        unlockedStyle.setLocked(false);
	    			        CreationHelper createHelper = workbook.getCreationHelper();

	    			        CellStyle dateStyle = workbook.createCellStyle();
	    			        dateStyle.cloneStyleFrom(lockedStyle); // keep locked
	    			        dateStyle.setDataFormat(
	    			            createHelper.createDataFormat().getFormat("dd-MM-yyyy")
	    			        );
	    			        int rowIdx = 0;
	    			        int colIdx = 0;
	    			        Row headerRow = sheet.createRow(rowIdx++);
	    		        headerRow.createCell(colIdx++).setCellValue("S No");
	    		        headerRow.createCell(colIdx++).setCellValue("PO NO");
	    		        headerRow.createCell(colIdx++).setCellValue("PO DATE");
	    		        headerRow.createCell(colIdx++).setCellValue("DISTRIBUTOR NAME");
	    		        headerRow.createCell(colIdx++).setCellValue("DRUG NAME");
	    		        headerRow.createCell(colIdx++).setCellValue("DRUG TYPE");
	    		        headerRow.createCell(colIdx++).setCellValue("AVAILABLE STOCK");
	    		        headerRow.createCell(colIdx++).setCellValue("PO QTY");
	    		        headerRow.createCell(colIdx++).setCellValue("RECEIVED STOCK");
	    		        headerRow.createCell(colIdx++).setCellValue("PENDING STOCK");
	    		        headerRow.createCell(colIdx++).setCellValue("PO STATUS");
	    		        for (LabelBean bean : POList) {
	    		        	SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
	    			        Date poDate = null;
    			        	Row dataRow = sheet.createRow(rowIdx++);
    			        	int dataColIdx = 0;
    			        		Cell c1 = dataRow.createCell(dataColIdx++);
    					            c1.setCellValue(sNo++);
    					            c1.setCellStyle(lockedStyle);
    				            Cell c2 = dataRow.createCell(dataColIdx++);
    					            c2.setCellValue(bean.getPOID());
    					            c2.setCellStyle(lockedStyle);
    				            Cell c3 = dataRow.createCell(dataColIdx++);
    					            c3.setCellValue(bean.getPODATE());
    					            c3.setCellStyle(dateStyle);  
    				            Cell c4 = dataRow.createCell(dataColIdx++);
    					            c4.setCellValue(bean.getDSTRBTRNAME());
    					            c4.setCellStyle(lockedStyle);
    				            Cell c5 = dataRow.createCell(dataColIdx++);
    					            c5.setCellValue(bean.getDRUGNAME());
    					            c5.setCellStyle(lockedStyle);
					            Cell c6 = dataRow.createCell(dataColIdx++);
						            c6.setCellValue(bean.getDRUGTYPE());
						            c6.setCellStyle(lockedStyle);
					            Cell c7 = dataRow.createCell(dataColIdx++);
					            	c7.setCellValue(bean.getAVL());
					            	c7.setCellStyle(lockedStyle);
			            	  Cell c8 = dataRow.createCell(dataColIdx++);
					            	c8.setCellValue(bean.getLVL());
					            	c8.setCellStyle(lockedStyle);
					           Cell c9 = dataRow.createCell(dataColIdx++);
					            	String recQty = bean.getRECQTY();
					            	c9.setCellValue(recQty != null && !recQty.trim().isEmpty() ? recQty : "NA");
					            	c9.setCellStyle(lockedStyle);
				            	Cell c10 = dataRow.createCell(dataColIdx++);
					            	c10.setCellValue(bean.getPOQTY());
					            	c10.setCellStyle(lockedStyle);
					            Cell c11 = dataRow.createCell(dataColIdx++);
					            	c11.setCellValue(bean.getPARTFULL());
					            	c11.setCellStyle(lockedStyle);
    				            Cell editable1 = dataRow.createCell(dataColIdx++);
    					            editable1.setCellValue("");
    					            editable1.setCellStyle(unlockedStyle);
    				            Cell editable2 = dataRow.createCell(dataColIdx++);
    					            editable2.setCellValue("");
    					            editable2.setCellStyle(unlockedStyle);
    				            sheet.protectSheet("");
    			        	}
	    		        
	    		        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	    		            sheet.autoSizeColumn(i);
	    		        }

	    		        response.setContentType("application/vnd.ms-excel");
	    		        response.setHeader(
	    		                "Content-Disposition",
	    		                "attachment; filename=" + fileName);

	    		        OutputStream out = response.getOutputStream();
	    		        workbook.write(out);
	    		        out.flush();
	    		        return null;
	    		    }
	    			}
	    			String message = new Gson().toJson(POList);
	    			response.setContentType("application/json");
	    			request.setAttribute("AjaxMessage", message);
	    			return mapping.findForward("ajaxResult");
	    		}
				if ("poNoOnclickRpt".equalsIgnoreCase(lStrActionVal))
				{
					List<LabelBean> POList =new ArrayList<LabelBean>();
					String poNo=request.getParameter("poNo");
					try 
					{   
					    	POList = patientService.getOnclickPORpt(poNo);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					String message = new Gson().toJson(POList);
					response.setContentType("application/json");
					request.setAttribute("AjaxMessage", message);
					lStrPageName = "ajaxResult";
				}
				if ("mnfPoNoOnclick".equalsIgnoreCase(lStrActionVal))
				{
					List<LabelBean> POList =new ArrayList<LabelBean>();
					String poNo=request.getParameter("mnfPoNo");
					try 
					{   
					    	POList = patientService.getOnclickMnfPORpt(poNo);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					String message = new Gson().toJson(POList);
					response.setContentType("application/json");
					request.setAttribute("AjaxMessage", message);
					lStrPageName = "ajaxResult";
				}
				if (lStrActionVal != null
						&& lStrActionVal.equalsIgnoreCase("wcDrugOutstandingBalance")) {
					String wc = null;
					 wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
					    if(wellnesslist.size()>0){
						    dispname = wellnesslist.get(0).getDISPNAME();
						    wc= wellnesslist.get(0).getDISPID();
					    }
					String drugType = request.getParameter("drugType1");
					String drugName = request.getParameter("drugName1");
					String drugDrill = request.getParameter("drugDrill")!=null?(String)request.getParameter("drugDrill"):"";
					String selectType= null;
					selectType = request.getParameter("searchType");
					if(selectType == null){
						selectType ="-1";
					}
					List<LabelBean> drugTypes = patientService.getdrugType();
					List<LabelBean> wcName = patientService.getwcName();
					List<LabelBean> drugReportList = patientService
							.getOutStandingdrugs(drugType, drugName, wc,selectType,drugDrill);
					patientForm.setSearchType(selectType);
					request.setAttribute("selectType", selectType);
					request.setAttribute("drugType", drugTypes);
					request.setAttribute("wellnesscenters", wcName);
					request.setAttribute("drugId", drugName);
					patientForm.setDrugReportList(drugReportList);
					request.setAttribute("wc", wc);
					request.setAttribute("type", drugType);
					request.setAttribute("drugDrill", drugDrill);
					return mapping.findForward("drugOutstanding");
				}
				if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getDrugDistReport"))
	    		{
	    			request.setAttribute("loginName", userName);
	    			return mapping.findForward("drugDistRpt");
	    		}
				if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("drugDistReport"))
	    		{
	    			request.setAttribute("loginName", userName);	
	    			String fromdate= request.getParameter("fromDate");
	    			String todate=  request.getParameter("destDate");
	    			String dispName=request.getParameter("dispname");
	    			String username=request.getParameter("userName");
	    			int pageNo=1,rowsPerPage=10;
	    			int startIndex=1,maxResults=0;
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
	                   List<LabelBean> drugReportList=patientService.getdrugsDistRpt(fromdate,todate,dispname,startIndex,maxResults,pageNo,userName);
	                if(drugReportList!=null &&drugReportList.size()>0)
	                { 
	                	request.setAttribute("totalPages",drugReportList.get(0).getTotalPages());
						request.setAttribute("totalRecords",drugReportList.get(0).getTotalRecords());
						request.setAttribute("pageId",drugReportList.get(0).getPageId());
						request.setAttribute("endIndex",drugReportList.get(0).getMaxresults());
						request.setAttribute("startIndex",drugReportList.get(0).getStrtIndex());
						request.setAttribute("endResults",drugReportList.get(0).getStrtIndex()+drugReportList.size());  
	                } 
	                List<LabelBean> users=null;
	                users=patientService.getrequiredusers(dispname);
	                request.setAttribute("users", users) ;
	                request.setAttribute("fromDetailed", "N") ;
	   				patientForm.setFromDetailed("N");
	    			request.setAttribute("drugReportList", drugReportList);
	    			patientForm.setDrugReportList(drugReportList);
	    			request.setAttribute("userName", username) ;
	    			patientForm.setUserName(userName);
	    			request.setAttribute("dispname", dispName) ;
	    			return mapping.findForward("drugDistRpt");
	    		}
	     		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("drugDistDetailedReport"))
	    		{
	     			request.setAttribute("loginName", userName);
	    			String fromdate= request.getParameter("fromDate");
	    			String todate=  request.getParameter("destDate");
	    			String dispName=request.getParameter("dispname");
	    			String drugId  =request.getParameter("drugId");
	    			String drugType=request.getParameter("drugTypeNew");
	    			String caseId=request.getParameter("caseId");
	    			String username=request.getParameter("userName");
	    			int pageNo=1,rowsPerPage=10;
	    			int startIndex=1,maxResults=0;
	    			request.setAttribute("destDate",todate);
	    			request.setAttribute("fromDate",fromdate);
	    			request.setAttribute("dispname",dispName);
	    			request.setAttribute("drugId",drugId);
	    			request.setAttribute("drugTypeNew",drugType);
	    			request.setAttribute("userName",username);
	    			request.setAttribute("caseId",caseId);
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
	                List<LabelBean> drugDetailedReportList=patientService.getDrugsDistDetailed(fromdate,todate,dispname, drugId,drugType,caseId,startIndex,maxResults,pageNo,userName);
	                if(drugDetailedReportList!=null &&drugDetailedReportList.size()>0)
	                {
	                	request.setAttribute("totalPages",drugDetailedReportList.get(0).getTotalPages());
						request.setAttribute("totalRecords",drugDetailedReportList.get(0).getTotalRecords());
						request.setAttribute("pageId",drugDetailedReportList.get(0).getPageId());
						request.setAttribute("endIndex",drugDetailedReportList.get(0).getMaxresults());
						request.setAttribute("startIndex",drugDetailedReportList.get(0).getStrtIndex());
						request.setAttribute("endResults",drugDetailedReportList.get(0).getStrtIndex()+drugDetailedReportList.size());  
	                }
	                String dispensary= null;
		        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
		        	{
		        		dispensary = patientService.getDispName(dispname);
		        	}
		        	List<LabelBean> users=null;
	                users=patientService.getrequiredusers(dispname);
	                request.setAttribute("users", users) ;
	   				request.setAttribute("fromDetailed", "Y") ;
	   				patientForm.setFromDetailed("Y");
	                request.setAttribute("drugDetailedReportList", drugDetailedReportList);
	                request.setAttribute("dispensary", dispensary);
	    			patientForm.setDrugReportList(drugDetailedReportList);
	    			if((drugId!=null &&!"".equalsIgnoreCase(drugId)&&drugType!=null&&!"".equalsIgnoreCase(drugType))||(caseId!=null&&!"".equalsIgnoreCase(caseId)))
	    				return mapping.findForward("drugDistributionDetailed");
	    			else
	    			return mapping.findForward("drugDistRpt");
	    		}
	     		if (lStrFlagStatus != null
	    				&& lStrFlagStatus.equalsIgnoreCase("getIndentNo")) {
	     			HashMap<String,Object> dataMap = new HashMap<String,Object>();
	    			String dispId = request.getParameter("dispId") != null ? (String) request
	    					.getParameter("dispId") : "";
	    			String ajaxCal = request.getParameter("ajaxCal") != null ? (String) request
	    					.getParameter("ajaxCal") : "";
	    			String wcName = request.getParameter("wcName") != null ? (String) request
	    					.getParameter("wcName") : "";
	    			try {
	    				indentNolist = patientService.getIndentNoList(dispId, ajaxCal);
	    				request.setAttribute("indentNolist", indentNolist);
	    				request.setAttribute("dispId", dispId);
	    				if (ajaxCal != null && "Y".equalsIgnoreCase(ajaxCal)) {
	    					Gson gson = new Gson();
	    					/*
	    					 * String gsonString=""; if(hospitalId!=null &&
	    					 * !"".equalsIgnoreCase(hospitalId))
	    					 * gsonString=gson.toJson(distLst); else if(distId!=null &&
	    					 * !"".equalsIgnoreCase(distId))
	    					 */
	    					String gsonString = gson.toJson(indentNolist);
	    					request.setAttribute("AjaxMessage", gsonString);
	    					return mapping.findForward("ajaxResult");
	    				}
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    			return mapping.findForward("indentApprv");
	    		}
	     		if (lStrFlagStatus != null
	    				&& lStrFlagStatus.equalsIgnoreCase("getIndentNoRpt")) {
	     			HashMap<String,Object> dataMap = new HashMap<String,Object>();
	    			String dispId = request.getParameter("dispId") != null ? (String) request
	    					.getParameter("dispId") : "";
	    			String ajaxCal = request.getParameter("ajaxCal") != null ? (String) request
	    					.getParameter("ajaxCal") : "";
	    			String wcName = request.getParameter("wcName") != null ? (String) request
	    					.getParameter("wcName") : "";
	    			try {
	    				indentNolist = patientService.getIndentNoListRpt(dispId, ajaxCal);
	    				request.setAttribute("indentNolist", indentNolist);
	    				request.setAttribute("dispId", dispId);
	    				if (ajaxCal != null && "Y".equalsIgnoreCase(ajaxCal)) {
	    					Gson gson = new Gson();
	    					/*
	    					 * String gsonString=""; if(hospitalId!=null &&
	    					 * !"".equalsIgnoreCase(hospitalId))
	    					 * gsonString=gson.toJson(distLst); else if(distId!=null &&
	    					 * !"".equalsIgnoreCase(distId))
	    					 */
	    					String gsonString = gson.toJson(indentNolist);
	    					request.setAttribute("AjaxMessage", gsonString);
	    					return mapping.findForward("ajaxResult");
	    				}
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    			return mapping.findForward("indentApprvRpt");
	    		}
	     		if (lStrFlagStatus != null
	    				&& lStrFlagStatus.equalsIgnoreCase("getIndentNoRpt1")) {
	     			HashMap<String,Object> dataMap = new HashMap<String,Object>();
	    			String dispId = request.getParameter("dispId") != null ? (String) request
	    					.getParameter("dispId") : "";
	    			String ajaxCal = request.getParameter("ajaxCal") != null ? (String) request
	    					.getParameter("ajaxCal") : "";
	    			String wcName = request.getParameter("wcName") != null ? (String) request
	    					.getParameter("wcName") : "";
	    			try {
	    				indentNolist = patientService.getIndentNoListRpt1(dispId, ajaxCal);
	    				request.setAttribute("indentNolist", indentNolist);
	    				request.setAttribute("dispId", dispId);
	    				if (ajaxCal != null && "Y".equalsIgnoreCase(ajaxCal)) {
	    					Gson gson = new Gson();
	    					/*
	    					 * String gsonString=""; if(hospitalId!=null &&
	    					 * !"".equalsIgnoreCase(hospitalId))
	    					 * gsonString=gson.toJson(distLst); else if(distId!=null &&
	    					 * !"".equalsIgnoreCase(distId))
	    					 */
	    					String gsonString = gson.toJson(indentNolist);
	    					request.setAttribute("AjaxMessage", gsonString);
	    					return mapping.findForward("ajaxResult");
	    				}
	    			} catch (Exception e) {
	    				e.printStackTrace();
	    			}
	    			return mapping.findForward("indentApprvRpt");
	    		}
	     		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getPO"))
	    		{
	     			String dispId = request.getParameter("dispId") != null ? (String) request.getParameter("dispId") : "";
	    			String ajaxCal = request.getParameter("ajaxCal") != null ? (String) request.getParameter("ajaxCal") : "";
	    			String wcName = request.getParameter("wcName") != null ? (String) request.getParameter("wcName") : "";
					String wc = request.getParameter("dispname");
					String indNo = request.getParameter("indNo");
					String distributorId = request.getParameter("distributorId");
					List<LabelBean> wName = patientService.getwcName();
					List<LabelBean> distributorList = patientService.getdistributorList();
					List<LabelBean> POList = patientService.getindentPO(wc, indNo, distributorId);
					indentNolist = patientService.getIndentNoList(wc, ajaxCal);
					patientForm.setPOList(POList);
					request.setAttribute("wc", wc);
					request.setAttribute("wName", wName);
					request.setAttribute("distributorList", distributorList);
					request.setAttribute("POList", POList);
					request.setAttribute("wellnesslist", wellnesslist);	
					request.setAttribute("indentNolist", indentNolist);
	     			return mapping.findForward("indentApprv");
				}
	     		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getexpDtRpt"))
	    		{
					if(lStrUserId!=null && !"".equalsIgnoreCase(lStrUserId))
					{
						wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
					}
					indentPoList = patientService.getExpdtRpt(wellnesslist.get(0).getDISPID(), "Y");
					String wc = wellnesslist.get(0).getDISPID();
					String indNo = request.getParameter("indNo");
					List<LabelBean> storeList = patientService.getstoreList(wc, indNo);
					//patientForm.setStoreList(storeList);
					patientForm.setIndentPoList(indentPoList);
					request.setAttribute("storeList", storeList);
					request.setAttribute("indentPoList", indentPoList);
	     			return mapping.findForward("ExpdateRpt");
				}
	     		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getlowStkUpdt"))
	    		{
					if(lStrUserId!=null && !"".equalsIgnoreCase(lStrUserId))
					{
						wellnesslist=patientService.checkUsrMappedToWellness(lStrUserId);
					}
					indentNolist = patientService.getStoreIndentNoList(wellnesslist.get(0).getDISPID(), "Y");
					String wc = wellnesslist.get(0).getDISPID();
					String indNo = request.getParameter("indNo");
					List<LabelBean> storeList = patientService.getstoreList(wc, indNo);
					//patientForm.setStoreList(storeList);
					patientForm.setIndentNolist(indentNolist);
					request.setAttribute("storeList", storeList);
					request.setAttribute("indentNolist", indentNolist);
	     			return mapping.findForward("StoreKeeperRpt");
				}
	     		if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getPORpt"))
	    		{
	     			String dispId = request.getParameter("dispId") != null ? (String) request.getParameter("dispId") : "";
	    			String ajaxCal = request.getParameter("ajaxCal") != null ? (String) request.getParameter("ajaxCal") : "";
	    			String wcName = request.getParameter("wcName") != null ? (String) request.getParameter("wcName") : "";
					String wc = request.getParameter("dispname");
					String indNo = request.getParameter("indNo");
					String distributor = request.getParameter("distributor");
					String fromDate = request.getParameter("fromDate");
					String destDate = request.getParameter("destDate");
					List<LabelBean> wName = patientService.getwcName();
					List<LabelBean> distributorList = patientService.getdistributorList();
					List<LabelBean> POList = patientService.getindentPORpt(wc, indNo, distributor, fromDate, destDate);
					indentNolist = patientService.getIndentNoListRpt(wc, ajaxCal);
					patientForm.setPOList(POList);
					request.setAttribute("wc", wc);
					request.setAttribute("wName", wName);
					request.setAttribute("POList", POList);
					request.setAttribute("wellnesslist", wellnesslist);	
					request.setAttribute("indentNolist", indentNolist);
					request.setAttribute("distributorList", distributorList);
	     			return mapping.findForward("indentApprvRpt");
				}
						if(lStrActionVal != null && lStrActionVal.equalsIgnoreCase("getPatDrugReportsCsv"))
					 	{  
					 		int sno=1;
					 	    int page=-1;
					 		String fromdate= request.getParameter("fromDate");
							String todate=  request.getParameter("destDate");
				             dispname=request.getParameter("dispname");
							String lStrDirPath=config.getString("REPORT.MapPath");
					        String lStrFileName=config.getString("REPORT.MapPath")+config.getString("SLASH_VALUE")+"Patient Drug Details from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn");
				        	File lcsvfile = createFile(lStrDirPath,lStrFileName); 
				        	char separator = '^';
				        	StringBuilder line = new StringBuilder();   
				        	List<LabelBean> patList=patientService.getpat(fromdate,todate,dispname,page,page,page);
				        	if(patList!=null &&patList.size()>0)
				        	{
				        	line.append("S.NO");
				        	line.append(separator);
				        	line.append("REG NO");
				        	line.append(separator);
				        	line.append("CASE ID");
				        	line.append(separator);
				        	line.append("CARD NO");
				        	line.append(separator);
				        	line.append("BENEFICIARY NAME");
				        	line.append(separator);
				        	line.append("GENDER");
				        	line.append(separator);
				        	   line.append("\n");     
				        	for(LabelBean drugs:patList)
				             {
				        		 line.append(sno);
				        		 sno++;
				        		 line.append(separator);
				        		 line.append(drugs.getREGNO());
				        		 line.append(separator);
				        		 line.append(drugs.getCASEID());
				        		 line.append(separator);
				        		 line.append(drugs.getCARDNO());
				        		 line.append(separator);
				        		 line.append(drugs.getNAME());
				        		 line.append(separator);
				        		 line.append(drugs.getEGENDER());
				        		 line.append(separator);
				        		   line.append("\n");     
				             }  
				        	String dispensary= null;
				        	if(dispname!=null &&!"".equalsIgnoreCase(dispname))
				        	{
				        		dispensary = patientService.getDispName(dispname);
				        	}
				        	request.setAttribute("File", line.toString().getBytes());    
				        	response.setContentType(config.getString("REPORT.CsvContentType"));
				        	if(dispensary != null && !"".equalsIgnoreCase(dispensary))
				        	response.setHeader("Content-Disposition","Attachment; filename=Patient Drug Details of "+dispensary+" from "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
				        	else
					        response.setHeader("Content-Disposition","Attachment; filename=Patient Drug Detailsfrom "+fromdate+" to "+todate+config.getString("DOT_VALUE")+config.getString("REPORT.CsvExtn")); 
				        	response.setCharacterEncoding("UTF-8");
				        	}
				        	return mapping.findForward("openFile");
					 	}
				else if("sendVerifyMobileOtp".equalsIgnoreCase(lStrActionVal)){
					//function call 
					String message = "";
					if(request.getParameter("type")!=null && "verify".equals((String)request.getParameter("type"))){
						message  = patientService.verifyMobileOTP(request.getParameter("id"), request.getParameter("otp"));
					}else{
						message  = patientService.sendMobileOtp(request.getParameter("id"), request.getParameter("mobileNumber"));
					}
					request.setAttribute("AjaxMessage", message);	
					lStrPageName="ajaxResult";
				}
		else if ("exemptOtp".equalsIgnoreCase(lStrActionVal)){
			//function call 
			String result = "";
			result  = patientService.exemptOtp(request.getParameter("id"));
			request.setAttribute("AjaxMessage", result);	
			lStrPageName="ajaxResult";
		}
		else if("purchasePdf".equalsIgnoreCase(lStrActionVal))
        {
			String wc = (String) session.getAttribute("dispId");
			String poNo = (String) session.getAttribute("res");
        	String s = poNo.substring(0, poNo.length()).replaceAll("/", "_");
        	String manPoId = null;
        	String filePath = "/storageNAS-TS-Production/WcIndent/PurchaseForm_"+s+".pdf";
        	List<PreauthVO> DispnsryList=patientService.getConsentData(wc, poNo);
        	if(DispnsryList==null || DispnsryList.size()==0) {
        		request.setAttribute("PRE-AUTH-LIST-STATUS", "NO DATA FOUND");
        		return mapping.findForward("ViewAdditionalPreAuth");
        	}
     	    	Document document = new Document(PageSize.LEGAL, 36, 36, 36, 36);
	    	String lStrFileName = "PurchaseForm";
	    	File lPdffilePath = new File(filePath);
	    	if(lPdffilePath.exists()) {
	    		lPdffilePath.delete();
	    	}
	    	PdfWriter.getInstance(document, new FileOutputStream(lPdffilePath));
	        document.open();
	        for (PreauthVO ls :DispnsryList){
		    List<PreauthVO> preauthlist = ls.getDispnsryList();
            Paragraph heading=null;
		    heading = new Paragraph("GOVERNMENT OF TELANGANA \n EMPLOYEES AND JOURNALISTS HEALTH SCHEME", FontFactory.getFont(FontFactory.HELVETICA_BOLD,15,0,Color.BLUE));
	        heading.setAlignment(Paragraph.ALIGN_CENTER);
	        heading.setSpacingBefore(15F);
	        heading.setSpacingAfter(1F);
	        document.add(heading);  
	        Paragraph heading1=null;  
		    heading1 = new Paragraph("Jubilee Hills, Hyderabad.", FontFactory.getFont(FontFactory.HELVETICA_BOLD,11,0,Color.BLUE));
	        heading1.setAlignment(Paragraph.ALIGN_CENTER);
	        heading1.setSpacingBefore(1F);
	        heading1.setSpacingAfter(10F);
	        document.add(heading1);
	        heading1 = new Paragraph("-----------------------------------------------------------------------------------------------------------------------------", FontFactory.getFont(FontFactory.HELVETICA_BOLD,10,0,Color.BLUE));
	        heading1.setAlignment(Paragraph.ALIGN_CENTER);
	        heading1.setSpacingBefore(1F);
	        heading1.setSpacingAfter(10F);
	        document.add(heading1);
	        PdfPTable table = new PdfPTable(3);
            table.getDefaultCell().setPadding(0.0f);
            table.setSplitLate(false);
            Font logofont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10, Font.NORMAL); 
            Font logofont1 = FontFactory.getFont(FontFactory.TIMES, 10, Font.NORMAL); 
            PdfPTable innerTable = new PdfPTable(2);
            innerTable.setWidthPercentage(100);
            innerTable.setWidths(new float[]{70, 30});
            manPoId = preauthlist.get(0).getMNFCTRID();
            PdfPCell left = new PdfPCell(new Phrase("PO No.1543/EHS&JHS/RAHCT/" + manPoId.substring(manPoId.indexOf("/")+1), headerfont));
            left.setBorder(0);
            left.setHorizontalAlignment(Element.ALIGN_LEFT);
            innerTable.addCell(left);

            PdfPCell right = new PdfPCell(new Phrase("Dt " + preauthlist.get(0).getPODT(), headerfont));
            right.setBorder(0);
            right.setHorizontalAlignment(Element.ALIGN_RIGHT);
            innerTable.addCell(right);

            PdfPCell wrapper = new PdfPCell(innerTable);
            wrapper.setBorder(0);
            wrapper.setColspan(3);
            table.addCell(wrapper);
            PdfPCell cellSign = new PdfPCell(new Phrase("To", headerfont));
            cellSign.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign.setBorder(0);     
            cellSign.setColspan(3);
            table.addCell(cellSign);
            PdfPCell cellSign1 = new PdfPCell(new Phrase(preauthlist.get(0).getDISTRIBUTOR_NAME(), headerfont));
            cellSign1.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign1.setBorder(0);     
            cellSign1.setColspan(3);
            table.addCell(cellSign1);
            /*PdfPCell cellSign2 = new PdfPCell(new Phrase(" ___________,", headerfont));
            cellSign2.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign2.setBorder(0);     
            cellSign2.setColspan(3);
            table.addCell(cellSign2);
            PdfPCell cellSign3 = new PdfPCell(new Phrase(" ___________,", headerfont));
            cellSign3.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign3.setBorder(0);     
            cellSign3.setColspan(3);
            table.addCell(cellSign3);*/
            PdfPCell cellSign4 = new PdfPCell(new Phrase("Sir,", logofont1));
            cellSign4.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign4.setBorder(0);     
            cellSign4.setColspan(3);
            table.addCell(cellSign4);
            PdfPCell cellSign5 = new PdfPCell(new Phrase("		Sub:-	EHS & JHS - Requirement of  Medicines for Wellness", logofont1));
            cellSign5.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign5.setBorder(0);     
            cellSign5.setColspan(3);
            cellSign5.setPaddingLeft(60);
            table.addCell(cellSign5);
            Phrase phrase = new Phrase();
            phrase.add(new Chunk("		Centre ",logofont1));
            phrase.add(new Chunk(preauthlist.get(0).getDISPNAME(),headerfont));
            phrase.add(new Chunk(" - Purchase Order placed - Reg.",logofont1));
            PdfPCell cellSign6 = new PdfPCell(phrase);
            cellSign6.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign6.setBorder(0);     
            cellSign6.setColspan(3);
            cellSign6.setPaddingLeft(80);
            table.addCell(cellSign6);
            PdfPCell cellSign7 = new PdfPCell(new Phrase("		Ref:-	1.Note file orders at Page No.48 of F.No.1543/EHS&JHS/ RAHCT/ ", logofont1));
            cellSign7.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign7.setBorder(0);     
            cellSign7.setColspan(3);
            cellSign7.setPaddingLeft(60);
            table.addCell(cellSign7);
            PdfPCell cellSign8 = new PdfPCell(new Phrase("		"+preauthlist.get(0).getFIN_YEAR()+", Dt.01.04.2026", logofont1));
            cellSign8.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign8.setBorder(0);     
            cellSign8.setColspan(3);
            cellSign8.setPaddingLeft(80);
            table.addCell(cellSign8);
            PdfPCell cellSign9 = new PdfPCell(new Phrase("		2. Approved NIMS RC 2025-27 for the procurement of Medicines,", logofont1));
            cellSign9.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign9.setBorder(0);     
            cellSign9.setColspan(3);
            cellSign9.setPaddingLeft(80);
            table.addCell(cellSign9);
            PdfPCell cellSign10 = new PdfPCell(new Phrase("		dated:18.08.2025", logofont1));
            cellSign10.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign10.setBorder(0);     
            cellSign10.setColspan(3);
            cellSign10.setPaddingLeft(80);
            table.addCell(cellSign10);
			PdfPCell cellSign13 = new PdfPCell(new Phrase("		3. Short tender approved by NIMS for supply of Medicines 2026-28,", logofont1));
            cellSign13.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign13.setBorder(0);     
            cellSign13.setColspan(3);
            cellSign13.setPaddingLeft(80);
            table.addCell(cellSign13);
            PdfPCell cellSign14 = new PdfPCell(new Phrase("		dated:28.01.2026", logofont1));
            cellSign14.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign14.setBorder(0);     
            cellSign14.setColspan(3);
            cellSign14.setPaddingLeft(80);
            table.addCell(cellSign14);
            PdfPCell cellSign11 = new PdfPCell(new Phrase("********", logofont1));
            cellSign11.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellSign11.setBorder(0); 
            cellSign11.setColspan(3);
            table.addCell(cellSign11);
            phrase = new Phrase();
            phrase.add(new Chunk("                   I request you to kindly supply the following Medicines to Wellness Centre, ",logofont1));
            phrase.add(new Chunk("\""+preauthlist.get(0).getDISPNAME()+"\"",headerfont));
            phrase.add(new Chunk(" under EHS & JHS as per the rate contract of NIMS 2025-2027.",logofont1));
            PdfPCell cellSign12 = new PdfPCell(phrase);
            cellSign12.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellSign12.setBorder(0);     
            cellSign12.setColspan(3);
            //cellSign12.setPaddingLeft();
            table.addCell(cellSign12);
            document.add(table);
            PdfPTable hospinfoTable = new PdfPTable(8);
	        hospinfoTable.setWidthPercentage(80);
	        float[] columnWidths = new float[] { 1f, 1.5f, 2f, 5.5f, 2f, 1.5f, 1f, 1.5f};
	        hospinfoTable.setWidths(columnWidths);
            hospinfoTable.getDefaultCell().setPadding(PADDING);
            hospinfoTable.setSplitLate(false);
            PdfPCell cell222 = new PdfPCell(new Phrase("Sl No", logofont1));
            cell222.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell222);
            PdfPCell cell223 = new PdfPCell(new Phrase("RC Code", logofont1));
            cell223.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell223);  
            PdfPCell cell238 = new PdfPCell(new Phrase("Drug Type", logofont1));
            cell238.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell238);
            PdfPCell cell224 = new PdfPCell(new Phrase("Drug Name", logofont1));
            cell224.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell224);
            PdfPCell cell225 = new PdfPCell(new Phrase("Brand Name", logofont1));
            cell225.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell225);
            PdfPCell cell226 = new PdfPCell(new Phrase("RC Rate", logofont1));
            cell226.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell226);
            PdfPCell cell227 = new PdfPCell(new Phrase("PO Qty", logofont1));
            cell227.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell227);
            PdfPCell cell228= new PdfPCell(new Phrase("Value", logofont1));
            cell228.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell228);
            double total = 0.0;
           for(int i=0;i<preauthlist.size();i++)
    		{
            PdfPCell cell422 = new PdfPCell(new Phrase(i+1+"", logofont1));
            cell422.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell422);
            PdfPCell cell423 = new PdfPCell(new Phrase(preauthlist.get(i).getITEMID(), logofont1));
            cell423.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell423);
            PdfPCell cell328 = new PdfPCell(new Phrase(preauthlist.get(i).getDRUGTYPE(), logofont1));
            cell328.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell328);
            PdfPCell cell424 = new PdfPCell(new Phrase(preauthlist.get(i).getDRUGNAME(), logofont1));
            cell424.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell424);
            PdfPCell cell425 = new PdfPCell(new Phrase(preauthlist.get(i).getMNFCTRNAME(), logofont1));
            cell225.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell425);
            PdfPCell cell426 = new PdfPCell(new Phrase(preauthlist.get(i).getRC_PRICE(), logofont1));
            cell426.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell426);
            PdfPCell cell427 = new PdfPCell(new Phrase(preauthlist.get(i).getINDENT_COUNT(), logofont1));
            cell427.setHorizontalAlignment(Element.ALIGN_CENTER);
            hospinfoTable.addCell(cell427);
            total = total+Double.parseDouble(preauthlist.get(i).getRC_VALUE());
            PdfPCell cell428= new PdfPCell(new Phrase(preauthlist.get(i).getRC_VALUE(), logofont1));
            cell428.setHorizontalAlignment(Element.ALIGN_CENTER);
           hospinfoTable.addCell(cell428);	
    		}
    			PdfPCell cell322 = new PdfPCell(new Phrase("", logofont1));
                cell322.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell322);
                PdfPCell cell323 = new PdfPCell(new Phrase("", logofont1));
                cell323.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell323);
                PdfPCell cell324 = new PdfPCell(new Phrase("", logofont1));
                cell324.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell324);
                PdfPCell cell325 = new PdfPCell(new Phrase("", logofont1));
                cell325.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell325);
                PdfPCell cell326 = new PdfPCell(new Phrase("", logofont1));
                cell326.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell326);
                PdfPCell cell327 = new PdfPCell(new Phrase("Grand Total:-", logofont1));
                cell327.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell327);
                PdfPCell cell329 = new PdfPCell(new Phrase("", logofont1));
                cell329.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell329);
                PdfPCell cell328= new PdfPCell(new Phrase(total+"", logofont1));
                cell328.setHorizontalAlignment(Element.ALIGN_CENTER);
                //cell1.setBorder(1);                
                hospinfoTable.addCell(cell328);
                document.add(hospinfoTable);
                PdfPTable table3 = new PdfPTable(3);
                table3.getDefaultCell().setPadding(0.0f);
                table3.setSplitLate(false);
                PdfPCell cellSig = new PdfPCell(new Phrase("*   Rates should be for delivery at respective wellness centres. No handling, clearing or packing charges shall be paid.", logofont1));
                cellSig.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig.setBorder(0);     
                cellSig.setColspan(3);
                cellSig.setPaddingLeft(30);
                table3.addCell(cellSig);
                PdfPCell cellSig2 = new PdfPCell(new Phrase("*   Supplies shall be made within 30 days from the date of purchase order.", logofont1));
                cellSig2.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig2.setBorder(0);     
                cellSig2.setColspan(3);
                cellSig2.setPaddingLeft(30);
                table3.addCell(cellSig2);
                PdfPCell cellSig3 = new PdfPCell(new Phrase("*   The vendor shall submit single invoice after execution of medicines against the purchase order.", logofont1));
                cellSig3.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig3.setBorder(0);     
                cellSig3.setColspan(3);
                cellSig3.setPaddingLeft(30);
                table3.addCell(cellSig3);
                PdfPCell cellSig4 = new PdfPCell(new Phrase("*   The items supplied by the vendor should have at least 80% of product shelf life and should be of latest batch manufacturing.", logofont1));
                cellSig4.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig4.setBorder(0);     
                cellSig4.setColspan(3);
                cellSig4.setPaddingLeft(30);
                table3.addCell(cellSig4);
                PdfPCell cellSig5 = new PdfPCell(new Phrase("*   Separate Invoice to be raised for each GST slab for the supplies made.", logofont1));
                cellSig5.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig5.setBorder(0);     
                cellSig5.setColspan(3);
                cellSig5.setPaddingLeft(30);
                table3.addCell(cellSig5);
                PdfPCell cellSig6 = new PdfPCell(new Phrase("*   Items should be stamped \"EJHS SUPPLY – NOT FOR SALE\".", logofont1));
                cellSig6.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig6.setBorder(0);     
                cellSig6.setColspan(3);
                cellSig6.setPaddingLeft(30);
                table3.addCell(cellSig6);
                PdfPCell cellSig7 = new PdfPCell(new Phrase("*   Vendor shall peruse the PO within 2 days in detail and confirm the supply status within 2 days in writing, failing which penalty clauses may be imposed on the firm as per tender conditions.", logofont1));
                cellSig7.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig7.setBorder(0);     
                cellSig7.setColspan(3);
                cellSig7.setPaddingLeft(30);
                table3.addCell(cellSig7);
                PdfPCell cellSig8 = new PdfPCell(new Phrase("*   If any product is withdrawn by the firm, it should be informed within 2 days along with a letter from the concerned manufacturer. Otherwise, action can be invoked against the firm as per tender conditions of NIMS RC 2025-27.", logofont1));
                cellSig8.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig8.setBorder(0);     
                cellSig8.setColspan(3);
                cellSig8.setPaddingLeft(30);
                table3.addCell(cellSig8);
                PdfPCell cellSig9 = new PdfPCell(new Phrase("*   The vendor shall abide by all other terms & conditions of NIMS Rate Contract.", logofont1));
                cellSig9.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSig9.setBorder(0);     
                cellSig9.setColspan(3);
                cellSig9.setPaddingLeft(30);
                table3.addCell(cellSig9);
                document.add(table3);
                Paragraph para = null;
    	        StringBuffer sb = new StringBuffer();
                sb.append("            I request you to supply these items at the earliest  and submit bills along with Invoice");
                sb.append(" and Delivery  Challans for making payment.");
                para = new Paragraph(sb.toString(), logofont1);
                para.setSpacingAfter(20);
                para.setIndentationLeft(50);
                para.setIndentationRight(50);
                document.add(para);
                PdfPTable table2 = new PdfPTable(3);
                table2.getDefaultCell().setPadding(0.0f);
                table2.setSplitLate(false);
                PdfPCell end = new PdfPCell(new Phrase("	(This has got the approval of CEO, EHS&JHS)", logofont1));
                end.setHorizontalAlignment(Element.ALIGN_CENTER);
                end.setBorder(0);     
                end.setColspan(3);
                table2.addCell(end);
                PdfPCell cellPlace = new PdfPCell(new Phrase("EXECUTIVE OFFICER", subfont));
                cellPlace.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cellPlace.setBorder(0);     
                cellPlace.setColspan(3);
                table2.addCell(cellPlace);
                PdfPCell incharge = new PdfPCell(new Phrase("Copy to In-charge Medical Officer,", logofont1));
                incharge.setHorizontalAlignment(Element.ALIGN_LEFT);
                incharge.setBorder(0);     
                incharge.setColspan(3);
                table2.addCell(incharge);
                PdfPCell consignee = new PdfPCell(new Phrase("Consignee: EHS & JHS Wellness Centre, ", logofont1));
                consignee.setHorizontalAlignment(Element.ALIGN_LEFT);
                consignee.setBorder(0);     
                consignee.setColspan(3);
                table2.addCell(consignee);
                PdfPCell cellSign22 = new PdfPCell(new Phrase(preauthlist.get(0).getHospAddress()+" ", logofont1));
                cellSign22.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSign22.setBorder(0);     
                cellSign22.setColspan(3);
                table2.addCell(cellSign22);
                PdfPCell cellSign32 = new PdfPCell(new Phrase("Cont. No "+preauthlist.get(0).getNEWCONTACTNO(), logofont1));
                cellSign32.setHorizontalAlignment(Element.ALIGN_LEFT);
                cellSign32.setBorder(0);     
                cellSign32.setColspan(3);
                table2.addCell(cellSign32);
                table2.setSplitLate(false);
                PdfPCell end4 = new PdfPCell(new Phrase("						", subfont));
                end4.setHorizontalAlignment(Element.ALIGN_CENTER);
                end4.setBorder(0);     
                end4.setColspan(3);
                table2.addCell(end4);
                table2.addCell(end4);
                table2.addCell(end4);
                PdfPCell end1 = new PdfPCell(new Phrase("This is system generated PO. Hence signature not required.", subfont));
                end1.setHorizontalAlignment(Element.ALIGN_CENTER);
                end1.setBorder(0);     
                end1.setColspan(3);
                table2.addCell(end1);
                document.add(table2);
                document.newPage();
        }
	 	    document.close();
	 	   response.setContentType("application/octet-stream");
	    response.setHeader("Content-Disposition", "attachment; filename="+s+".pdf");
	    try 
	     {
	       	FileInputStream in = new FileInputStream(new File(filePath));
	        ServletOutputStream out = response.getOutputStream();
	        byte[] outputByte = new byte[4096];
	        //copy binary content to output stream
	        while(in.read(outputByte, 0, 4096) != -1){
	        	out.write(outputByte, 0, 4096);
	        }
	        in.close();
	        out.flush();
	        out.close();
	        session.removeAttribute("dispId");
	        session.removeAttribute("res");
	     }catch(Exception e){
	    	e.printStackTrace();
	   }
	   return null;
        }
		else if ("viewExemptOtpList".equalsIgnoreCase(lStrActionVal)){
			//function call 
			List<PatientVO> result = null;
			String patientId = request.getParameter("patientNo");
			result  = patientService.viewExemptOtpList(lStrUserId,patientId);
			request.setAttribute("registeredPatientsList", result);
			if(result== null){
				patientForm.setErrMsg("No Records Found");
			}
			lStrPageName="exemptOtp";
		}
		else if("exemptOtpApproval".equalsIgnoreCase(lStrActionVal)){
			String result ="";
			String patientId= request.getParameter("patientNo");
			String remarks=request.getParameter("remarks");
			result =patientService.exemptOtpApproval(patientId, lStrUserId, remarks);
			request.setAttribute("AjaxMessage", result);	
			lStrPageName="ajaxResult";
		}
		//Chandana - 8251 - new actionval to get the uploaded excel
		if ("UploadFile".equalsIgnoreCase(lStrActionVal)) {
			PatientForm patForm = (PatientForm) form;
			String resmsg = "";
			boolean inData = true;
			HashMap<String, Object> hParamMap = new HashMap<String, Object>();
			List<CommonDtlsVO> drugDtlsxlVOList = new ArrayList<CommonDtlsVO>();
			try {
				FormFile file = patForm.getId_File_Inc();
				String fileName = patForm.getFileName();
				HSSFWorkbook workbook = new HSSFWorkbook(file.getInputStream());
				if (workbook.getNumberOfSheets() == 1) {
					HSSFSheet worksheet = workbook.getSheetAt(0);
					Iterator<Row> rowIterator = worksheet.rowIterator();
					String[] expectedHeaders = {
							"S_No", "Drug_Type", "Drug_Name", "Manufacturer_Name","Distributor_Name", "Batch_Name", "Expiry_Date","Drug_Code", "Physical_stock_as_on_today", "Remarks" };
					if (!rowIterator.hasNext())
						resmsg = "The uploaded excel sheet does not match with given Template";
					else {
						Row headerRow = rowIterator.next();
						boolean headerMismatch = false;
						for (int i = 0; i < expectedHeaders.length; i++) {
							Cell cell = headerRow.getCell(i);
							String actualHeader = (cell == null) ? "" : cell.toString().trim();
							if (!expectedHeaders[i].equalsIgnoreCase(actualHeader)) {
								resmsg = "The uploaded excel sheet does not match with given Template";
								headerMismatch = true;
								break;
							}
						}
						if (!headerMismatch) {
							while (rowIterator.hasNext() && inData) {
								Row row = rowIterator.next();
								if (row == null || row.getCell(0) == null || row.getCell(0).toString().trim().equals(""))
									inData = false;
								else {
									CommonDtlsVO drugDtlsxlVO = new CommonDtlsVO();
									if (row.getCell(0) != null)
										drugDtlsxlVO.setSNo(row.getCell(0).toString().toUpperCase());//Sno
									if (row.getCell(1) != null)
										drugDtlsxlVO.setDRUGTYPE(row.getCell(1).toString().toUpperCase());//drug type
									if (row.getCell(2) != null) 
										drugDtlsxlVO.setDRUGNAME(row.getCell(2).toString().trim());//drug name
									if (row.getCell(3) != null)
										drugDtlsxlVO.setDISPNAME(row.getCell(3).toString().trim());//Manufacturer_Name
									if (row.getCell(4) != null)
										drugDtlsxlVO.setDISNAME(row.getCell(4).toString().trim());//Distributor_Name
									if (row.getCell(5) != null)
										drugDtlsxlVO.setUnitName(row.getCell(5).toString().trim());//Batch_Name
									if (row.getCell(6) != null) {
										Cell cell = row.getCell(6);
										if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(cell)) {
											SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
											drugDtlsxlVO.setDisDate(sd.format(cell.getDateCellValue()));//Expiry_Date
											}
										else
											drugDtlsxlVO.setDisDate(cell.toString().trim());//Expiry_Date
						            }
									if (row.getCell(7) != null)
										drugDtlsxlVO.setDrugCode(row.getCell(7).toString().trim());//Drug_Code
									if (row.getCell(8) != null && !row.getCell(8).toString().trim().isEmpty()) {
									    String qtyStr = row.getCell(8).toString().trim();
									    try {
									        double qtyValDouble = Double.parseDouble(qtyStr);
									        if (qtyValDouble % 1 != 0) {
									            resmsg = "Physical stock should not contain decimals.";
									            inData = false;
									            break;
									        }
									        long qtyVal = (long) qtyValDouble;
									        if (qtyVal < 0) {
									            resmsg = "Physical stock cannot be negative.";
									            inData = false;
									            break;
									        }
									        if (qtyVal >= 1000000) {
									            resmsg = "Physical stock cannot be greater than 6 digits.";
									            inData = false;
									            break;
									        }
									        drugDtlsxlVO.setQUANTITY(String.valueOf(qtyVal));
									    } catch (NumberFormatException nfe) {
									        resmsg = "Invalid number format in Physical Stock column.";
									        inData = false;
									        break;
									    }
									}
									else
										drugDtlsxlVO.setQUANTITY("0");//Physical_stock_as_on_today
									/*if (row.getCell(9) != null) {
										drugDtlsxlVO.setRemarks(row.getCell(9).toString().trim());//Remarks
									}*/
									if (row.getCell(9) != null) {
									    String remarks = row.getCell(9).toString().trim();

									    if (remarks.matches("^[a-zA-Z0-9,./ ]*$")) {
									        drugDtlsxlVO.setRemarks(remarks);
									    } else {
									        resmsg = "Only alphanumeric and three special characters ( . ,  / ) are allowed in remarks";
									        inData = false;
									        break;
									    }
									}
									drugDtlsxlVOList.add(drugDtlsxlVO);
								}
							}
							if (resmsg.isEmpty()) {
								hParamMap.put("drugXlsList", drugDtlsxlVOList);
								hParamMap.put("userId", lStrUserId);
								String dispId=commonService.getloggedUserDispId(lStrUserId,schemeId);
								hParamMap.put("dispId", dispId);
								SimpleDateFormat dateFormat = new SimpleDateFormat("MM-yyyy");
					    		String monthYr = dateFormat.format(new Date());
					    		hParamMap.put("monthYr", monthYr);
					    		hParamMap.put("fileName", fileName);
					    		hParamMap.put("file", file);
								String res = patientService.updDrugMonthlyDtls(hParamMap);
								if(!res.isEmpty() && "Success".equalsIgnoreCase(res))
									resmsg = "File uploaded successfully";
								else
									resmsg = "Fail to upload the excel";
							}
						}
					}
				} else {
					resmsg = "The uploaded excel sheet does not match with given Template";
					}
				} catch (Exception e) {
					e.printStackTrace();
					resmsg = "The Data in the uploaded excel sheet is in incorrect format, Please upload the file with correct format.";
				}
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(new Gson().toJson(resmsg));
			return null;
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
			{
				noOfPages++;
			}
		}
		return noOfPages;
	}
	private int getStartIndex(int noOfRowsPerPage,int pageNo)
	{
		int startIndex = 0;
		if(noOfRowsPerPage>0 && pageNo>0)
		{
			if(pageNo==1)
			{
				startIndex = 0;
			}
			else if(pageNo>1)
			{
				startIndex = (pageNo-1)*noOfRowsPerPage;
			}
		}
		return startIndex;
	}
	private File createFile(String lStrDirPath, String lStrFileName)
			throws IOException {
		// Making Directory
		File file = new File(lStrDirPath);
		if (!file.exists()) {
			file.mkdir();
		}
		File lfile = new File(lStrFileName);
		if (!lfile.exists()) {
			lfile.createNewFile();
		} else {
			lfile.delete();
			lfile.createNewFile();
		}
		return lfile;
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