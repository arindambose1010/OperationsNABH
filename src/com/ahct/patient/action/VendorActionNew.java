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
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
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
import com.ahct.common.util.SMSServices;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.login.service.LoginService;
import com.ahct.model.EhfAisDrugs;
import com.ahct.model.EhfPatient;
import com.ahct.model.EhfVendorUpload;
import com.ahct.model.EhfWcIndentVndrGst;
import com.ahct.model.EhfmOpDrugMst;
import com.ahct.model.EhfWcIndent;
import com.ahct.patient.constants.FileConstants;
import com.ahct.patient.dao.PatientDaoImpl;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.util.PdfUtil;
import com.ahct.patient.vo.DrugsVO;
import com.ahct.patient.vo.PatientVO;
import com.ahct.patient.vo.PreauthVO;
import com.ahct.patient.vo.SpecialityVO;
import com.ahct.preauth.service.CasesApprovalService;
import com.ahct.reports.form.ReportsForm;
import com.google.gson.Gson;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.tcs.service.TimeLoggingService;
import com.ahct.common.util.StringUtility;



public class VendorActionNew extends Action{
	private final static Logger GLOGGER = Logger.getLogger ( PatientAction.class ) ;
	private static final float PADDING = 2.0f;
    private static final Font datafont =
            FontFactory.getFont(FontFactory.TIMES, 11);
        private static final Font subfont =
            FontFactory.getFont(FontFactory.TIMES_BOLD, 9);
        private final PdfUtil pdfUtil = new PdfUtil();
		private Font headerFont;
      
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
		LoginService loginService =(LoginService)ServiceFinder.getContext(request).getBean("loginService");
		 TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
		CasesApprovalService casesApprovalService = ( CasesApprovalService ) ServiceFinder.getContext ( request ).getBean ( "casesApprovalService" ) ;
		String userName=null;
		String lStrLangID=null;
		String lStrUserId =null;
		List<LabelBean> grpList=null;
		List<String> lStrgrpList=new ArrayList<String>();
		String grpId=null;
		 String lStrFlagStatus = request.getParameter("actionFlag");
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
		Date ldtToday = new Date();
		String callType=null;
		String ipOpType = null;
		String msg=null;
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
			//CR: 8601 Start Add Drugs for Vendor and Dispatch Tracking for wellness center Pravalika
						
						if ("viewVendorDrugInventoryForm".equalsIgnoreCase(lStrActionVal))
						{
							try
							{
								List<LabelBean> wclist=patientService.getWCList();
								request.setAttribute("wclist", wclist);
								
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
							
							lStrPageName = "VendorDrugsInventory";
						}
						if ("getIndentedPoRpt1".equalsIgnoreCase(lStrActionVal))
						{
							String dispId = request.getParameter("dispname");
							String status=request.getParameter("status");
			    			
			    			List<LabelBean> indentedPOList = patientService.getIndentStatus(dispId,lStrUserId,status);
				    		
				    			String message = new Gson().toJson(indentedPOList);
				    			response.setContentType("application/json");
				    			request.setAttribute("AjaxMessage", message);
				    			return mapping.findForward("ajaxResult");
				    		}
						if ("getPoListforUpload".equalsIgnoreCase(lStrActionVal))
						{
							String status=request.getParameter("status");
			    			
			    			List<LabelBean> indentedPOList = patientService.getPoListforUpload(lStrUserId,status);
				    		
				    			String message = new Gson().toJson(indentedPOList);
				    			response.setContentType("application/json");
				    			request.setAttribute("AjaxMessage", message);
				    			return mapping.findForward("ajaxResult");
				    		}
						
						if ("getInvoiceList".equalsIgnoreCase(lStrActionVal))
						{
							String poId = request.getParameter("poId");
						   String status = request.getParameter("status");
			    			
						    List<LabelBean> invoiceNoList=patientService.getinvoiceNoList(poId,status);
				    		
				    			String message = new Gson().toJson(invoiceNoList);
				    			response.setContentType("application/json");
				    			request.setAttribute("AjaxMessage", message);
				    			return mapping.findForward("ajaxResult");
				    		}
						
						if (lStrFlagStatus != null
			    				&& lStrFlagStatus.equalsIgnoreCase("poNoOnclick1")) {
			    			
			    			List<LabelBean> POList =new ArrayList<LabelBean>();
			    			String poNo=request.getParameter("poNo");
			    			String status=request.getParameter("status");
			    			String drugId=request.getParameter("drugId");
			    			try 
			    			{   
			    			    	POList = patientService.getOnclickPORpt1(poNo,status,lStrUserId,drugId);
			    			}
			    			catch(Exception e)
			    			{
			    				e.printStackTrace();
			    			}
			    			String message = new Gson().toJson(POList);
			    			response.setContentType("application/json");
			    			request.setAttribute("AjaxMessage", message);
			    			return mapping.findForward("ajaxResult");
			    		}
						else if ("saveInventoryDetailsVendor".equalsIgnoreCase(lStrActionVal)) {
						    String message = "";
						    try {
						        String vendorPOListJson = request.getParameter("vendorPOList");
						        String dispId = request.getParameter("dispname");

						        if (vendorPOListJson == null || vendorPOListJson.trim().isEmpty()) {
						            message = "ERROR~No vendor data received";
						            request.setAttribute("AjaxMessage", message);
						            lStrPageName = "ajaxResult";
						            return mapping.findForward(lStrPageName);
						        }

						        JSONArray vendorList = new JSONArray(vendorPOListJson);
						        List<HashMap<String, Object>> vendorPOList = new ArrayList<>();

						        for (int i = 0; i < vendorList.length(); i++) {
						            JSONObject vnd = vendorList.getJSONObject(i);

						            HashMap<String, Object> record = new HashMap<>();

						            record.put("poNo", vnd.optString("poNo", ""));
						            record.put("poId", vnd.optString("poId", ""));
						            record.put("mnfctrname", vnd.optString("mnfctrname", ""));
						            record.put("indentId", vnd.optString("indentId", ""));
						            record.put("drugList", vnd.optString("drugList", ""));
						            record.put("drugName", vnd.optString("drugName", ""));
						            record.put("drugType", vnd.optString("drugType", ""));
						            record.put("itemId", vnd.optString("itemId", ""));
						            record.put("poQty", vnd.optString("poQty", "0"));
						            record.put("poStatus", vnd.optString("poStatus", ""));
						            record.put("status", vnd.optString("cmbStatus", ""));
						            record.put("rcPrice", vnd.optString("rcPrice", ""));
						            record.put("batchNo", vnd.optString("batchNo", ""));
						            record.put("expiryDate", vnd.optString("expiryDate", ""));
						            record.put("invoiceNo", vnd.optString("invoiceNo", ""));
						            record.put("invoiceDate", vnd.optString("invoiceDate", ""));
						            record.put("quantity", vnd.optString("quantity", "0"));
						            record.put("pendingQty", vnd.optString("pendingQty", "0"));
						            record.put("drugId", vnd.optString("drugId", ""));
						            record.put("gstSlab", vnd.optString("gstSlab", ""));
						            record.put("drugTypeId", vnd.optString("drugTypeId", ""));
						            record.put("mnfctrId", vnd.optString("mnfctrId", ""));
						            record.put("dstrbtrId", vnd.optString("dstrbtrId", ""));
						            record.put("invoiceAmount", vnd.optString("invoiceAmount", "0"));
						            String poDateStr = vnd.optString("poDate");
						            java.sql.Date poDate = null;
						            if (poDateStr != null && !poDateStr.trim().isEmpty()) {
						                if (poDateStr.contains("-") && poDateStr.indexOf("-") == 2) {
						                   
						                    String[] parts = poDateStr.split("-");
						                    poDateStr = parts[2] + "-" + parts[1] + "-" + parts[0];
						                }
						                poDate = java.sql.Date.valueOf(poDateStr);
						            }

						            record.put("poDate", poDate);
						            record.put("dispId", dispId);

						            vendorPOList.add(record);
						        }

						        HashMap<String, Object> vendordrug = new HashMap<>();
						        vendordrug.put("vendorPOList", vendorPOList);
						        vendordrug.put("dispId", dispId);

						        message = "SUCCESS";
						        String res = patientService.saveInventoryDetailsVendor(vendordrug, lStrUserId);

						        if (res != null && !"".equalsIgnoreCase(res)) {
						            session.setAttribute("res", res);
						        }

						        message = "SUCCESS~" + res;
						    } catch (Exception e) {
						        e.printStackTrace();
						        message = "ERROR~" + e.getMessage();
						    }
						    request.setAttribute("AjaxMessage", message);
						    lStrPageName = "ajaxResult";
						}

						else if ("vendorPdf".equalsIgnoreCase(lStrActionVal)) 
						{
							String wc = request.getParameter("wc");
						    String poNo = request.getParameter("poNo");
						    String itemIds = request.getParameter("itemIds");
						    String IndentNo=request.getParameter("indentNo");
			    			//String status=request.getParameter("status");
							//String pdfFileName ="VendorForm_" + poNo + "_" + System.currentTimeMillis() + ".pdf";
							//String filePath = "/storageNAS-TS-Production/WcVendor/" + pdfFileName;
							
			    			String baseDir = "/storageNAS-TS-Production/WcVendor/";

			    			String originalPoNo = poNo;
			    			String safePoNo = originalPoNo.replaceAll("[^a-zA-Z0-9_-]", "_");
			    			String pdfFileName = "VendorForm_" + safePoNo + "_" + System.currentTimeMillis() + ".pdf";

			    			File dir = new File(baseDir);
			    			if (!dir.exists()) {
			    			    dir.mkdirs();
			    			}
			    			String filePath = baseDir + pdfFileName;
			    			Rectangle pageSize = PageSize.A4;
							Document document = new Document(pageSize, 15, 15, 15, 15);
			    			File pdfFile = new File(filePath);
			    			if (pdfFile.exists()) {
			    			    pdfFile.delete();
			    			}
			    			PdfWriter.getInstance(document, new FileOutputStream(pdfFile));

				
						    List<LabelBean> DispnsryList = patientService.getConsentData1(wc, poNo);
						    List<LabelBean> POList = patientService.getPoItemsForPdf(poNo,itemIds);

						   /* Rectangle pageSize = PageSize.A4;
						    Document document = new Document(pageSize, 15, 15, 15, 15);*/

						    // pdfFile = new File(filePath);
						    if (pdfFile.exists()) pdfFile.delete();

						    try {
						        PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
						        document.open();

						        boolean hasData = false;
						        LabelBean data = null;

						        if (DispnsryList != null && !DispnsryList.isEmpty()
						                && DispnsryList.get(0).getDispnsryList() != null
						                && !DispnsryList.get(0).getDispnsryList().isEmpty()) 
						        {
						            data = DispnsryList.get(0).getDispnsryList().get(0);
						            hasData = true;
						        }

						        PdfPTable header = new PdfPTable(1);
						        header.setWidthPercentage(100);

						        Font distributorNameFont =
						                new Font(Font.HELVETICA, 15, Font.BOLD, Color.BLUE);

						        Font distributorAddressFont =
						                new Font(Font.HELVETICA, 10, Font.NORMAL, Color.BLUE);

						        String distributorName =
						                hasData ? safe(data.getDISTRIBUTOR_NAME()) : "Distributor Name";

						        String distributorAddress =
						                hasData ? safe(data.getADDRESS()) : "Distributor Address";

						        PdfPCell h1 = new PdfPCell(new Phrase(distributorName, distributorNameFont));
						        h1.setHorizontalAlignment(Element.ALIGN_CENTER);
						        h1.setBorder(Rectangle.NO_BORDER);
						        header.addCell(h1);
						        
						        PdfPCell h2 = new PdfPCell(new Phrase(distributorAddress, distributorAddressFont));
						        h2.setHorizontalAlignment(Element.ALIGN_CENTER);
						        h2.setBorder(Rectangle.NO_BORDER);
						        header.addCell(h2);
						 
						        document.add(header);
						        document.add(new Paragraph(" "));

						        PdfPTable mainRow = new PdfPTable(new float[]{50f, 50f});
						        mainRow.setWidthPercentage(100);

						      
						        PdfPTable left = new PdfPTable(1);
						        left.setWidthPercentage(100);

						        Font headerFont = new Font(Font.HELVETICA, 9, Font.BOLD, Color.WHITE);
						        
						     
						        Color headerBgColor = new Color(46, 161, 104);
						        // Header
						        PdfPCell leftHeader = new PdfPCell(
						                new Phrase("DISTRIBUTOR DETAILS", headerFont));
						        leftHeader.setBackgroundColor(headerBgColor);
						        leftHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
						        leftHeader.setPadding(6);
						        left.addCell(leftHeader);

						        // Inner table
						        PdfPTable leftInner = new PdfPTable(new float[]{3f, 7f});
						        leftInner.setWidthPercentage(100);

						        leftInner.addCell(makeLabelCell("Name"));
						        leftInner.addCell(makeValueCell(hasData ? safe(data.getDISTRIBUTOR_NAME()) : ""));

						        leftInner.addCell(makeLabelCell("Address"));
						        leftInner.addCell(makeValueCell(hasData ? safe(data.getADDRESS()) : ""));

						        leftInner.addCell(makeLabelCell("GST No"));
						        leftInner.addCell(makeValueCell(hasData ? safe(data.getID()) : ""));

						        leftInner.addCell(makeLabelCell("Email"));
						        leftInner.addCell(makeValueCell(hasData ? safe(data.getEMAILID()) : ""));

						        leftInner.addCell(makeLabelCell("Phone"));
						        leftInner.addCell(makeValueCell(hasData ? safe(data.getMOBILE_NO()) : ""));

						        PdfPCell leftInnerCell = new PdfPCell(leftInner);
						        leftInnerCell.setBorder(Rectangle.NO_BORDER);
						        left.addCell(leftInnerCell);

						        PdfPCell leftCell = new PdfPCell(left);
						        leftCell.setBorder(Rectangle.BOX);
						        mainRow.addCell(leftCell);

						        PdfPTable right = new PdfPTable(1);
						        right.setWidthPercentage(100);

						        
						        PdfPCell rightHeader = new PdfPCell(
						                new Phrase("BUYER DETAILS", headerFont));
						        rightHeader.setBackgroundColor(headerBgColor);
						        rightHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
						        rightHeader.setPadding(6);
						        right.addCell(rightHeader);

						        PdfPTable rightInner = new PdfPTable(new float[]{3f, 7f});
						        rightInner.setWidthPercentage(100);

						        rightInner.addCell(makeLabelCell("Name"));
						        rightInner.addCell(makeValueCell(hasData ? safe(data.getWELLNESSCENTER()) : ""));

						        rightInner.addCell(makeLabelCell("Address"));
						        rightInner.addCell(makeValueCell(hasData ? safe(data.getCONST()) : ""));

						        rightInner.addCell(makeLabelCell("GST No"));
						        rightInner.addCell(makeValueCell(""));

						        rightInner.addCell(makeLabelCell("Email"));
						        rightInner.addCell(makeValueCell(hasData ? safe(data.getEMAILID()) : ""));

						        rightInner.addCell(makeLabelCell("Phone"));
						        rightInner.addCell(makeValueCell(hasData ? safe(data.getCONTACTNO()) : ""));

						        PdfPCell rightInnerCell = new PdfPCell(rightInner);
						        rightInnerCell.setBorder(Rectangle.NO_BORDER);
						        right.addCell(rightInnerCell);

						        PdfPCell rightCell = new PdfPCell(right);
						        rightCell.setBorder(Rectangle.BOX);
						        mainRow.addCell(rightCell);

						        document.add(mainRow);
						        document.add(new Paragraph(" "));
						        
						        PdfPTable invoice = new PdfPTable(new float[]{2f, 4f, 2f, 4f,2f, 4f,2f, 4f});
						        invoice.setWidthPercentage(100);
						        
						        //LabelBean po = POList.get(0);
						        LabelBean po = null;
						        if (POList != null && !POList.isEmpty()) {
						            po = POList.get(0);
						        } else {
						            throw new RuntimeException("No PO items found for PDF");
						        }
						        
						        invoice.addCell(makeLabelCell("Inv.No."));
						        invoice.addCell(makeValueCell(safe(po.getINVOICENO())));

						        invoice.addCell(makeLabelCell("Inv Date"));
						        invoice.addCell(makeValueCell(safe(po.getINVOICE_NUM())));

						        invoice.addCell(makeLabelCell("Po No."));
						        invoice.addCell(makeValueCell(safe(po.getPOID())));

						        invoice.addCell(makeLabelCell("Po Date"));
						        invoice.addCell(makeValueCell(formatDateForPdf(po.getPODATE())));
						        //invoice.addCell(makeValueCell(safe(po.getPODATE())));

						        document.add(invoice);
						        document.add(new Paragraph(" "));

						        PdfPTable itemTable = new PdfPTable(new float[]{
						        	    2f,5f, 3f, 3f, 3f, 3f, 2f, 2f, 3f, 3f
						        	});
						        itemTable.setWidthPercentage(100);
						        
						        Font itemHeaderFont = new Font(Font.HELVETICA, 9, Font.BOLD, Color.WHITE);
						        Font itemValueFont  = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.BLACK);

						        String[] headers = {
						        	    "Item Id","Item Description", "Exp Dt", "Batch No", "Mfac By",
						        	    "Issued Qty", "Rate", "GST",
						        	    "Taxable Amt", "Total Amount"
						        	};

						        for (String col : headers) {
						            PdfPCell cell = new PdfPCell(new Phrase(col, itemHeaderFont));
						            cell.setBackgroundColor(headerBgColor);
						            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						            cell.setPadding(4);
						            itemTable.addCell(cell);
						        }
						        if (POList != null && !POList.isEmpty()) {
						        	 double totalTaxableAmount = 0.0;
								     double totalInvoiceAmount = 0.0;

						            for (LabelBean p : POList) {
						            	double taxableAmt = 0.0;
						                double totalAmt = 0.0;
						                
						            	itemTable.addCell(makeItemValueCell(safe(p.getITEMID())));
						                itemTable.addCell(makeItemValueCell(safe(p.getDRUGNAME()) + "\n" + safe(p.getDRUGTYPE())));
						                //itemTable.addCell(makeValueCell(safe(p.getPODATE())));
						                itemTable.addCell(makeItemValueCell(safe(p.getDOSAGE())));
						                itemTable.addCell(makeItemValueCell(safe(p.getBATCHNO())));
						                itemTable.addCell(makeItemValueCell(safe(p.getMNFCTRNAME())));
						               // itemTable.addCell(makeValueCell(safe("")));
						                itemTable.addCell(makeItemValueCell(safe(p.getQUANTITY())));
						                itemTable.addCell(makeItemValueCell(safe(p.getRC_PRICE())));
						                
						                itemTable.addCell(makeItemValueCell(safe(p.getCONST())));
						                itemTable.addCell(makeItemValueCell(safe(p.getNEXTVAL())));
						                itemTable.addCell(makeItemValueCell(safe(p.getDIFF())));
						            }

						        } else {
						            PdfPCell noItems = new PdfPCell(new Phrase("No Item Details Found", new Font(Font.HELVETICA, 11, Font.BOLD)));
						            noItems.setColspan(12);
						            noItems.setHorizontalAlignment(Element.ALIGN_CENTER);
						            noItems.setPadding(10);
						            itemTable.addCell(noItems);
						        }

						        document.add(itemTable);
						        document.add(new Paragraph(" "));
						        
						        /*------TAX DETAILS--------*/
						        
						        document.add(new Paragraph(" "));
						        
						        Font taxHeaderFont = new Font(Font.HELVETICA, 9, Font.BOLD, Color.WHITE);
						        Font taxValueFont  = new Font(Font.HELVETICA, 8, Font.NORMAL, Color.BLACK);
						        
						        PdfPTable taxTable = new PdfPTable(new float[]{
						                2f, 3f, 3f, 3f, 3f, 3f
						        });
						     
						        taxTable.setWidthPercentage(65);
						        taxTable.setHorizontalAlignment(Element.ALIGN_RIGHT);
						        taxTable.setSpacingBefore(8f);

						        String[] taxHeaders = {
						                "Tax %",
						                "Taxable Amount",
						                "CGST Amt",
						                "SGST Amt",
						                "IGST Amt",
						                "Sub Total"
						        };

						        for (String h : taxHeaders) {
						            PdfPCell cell = new PdfPCell(new Phrase(h, taxHeaderFont));
						            cell.setBackgroundColor(headerBgColor);
						            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						            cell.setPadding(4);
						            taxTable.addCell(cell);
						        }

						        double[] gstSlabs = {5, 12, 18};

						        Map<Double, Double> slabTaxableMap = new HashMap<>();
						        for (double slab : gstSlabs) {
						            slabTaxableMap.put(slab, 0.0);
						        }

						        for (LabelBean p : POList) {

						            double taxableAmt = p.getNEXTVAL() == null ? 0.0 : p.getNEXTVAL().doubleValue();
						            double gstPercent = p.getCONST() == null ? 0.0 : Double.parseDouble(p.getCONST());

						            if (slabTaxableMap.containsKey(gstPercent)) {
						                slabTaxableMap.put(
						                        gstPercent,
						                        slabTaxableMap.get(gstPercent) + taxableAmt
						                );
						            }
						        }

						        for (double slab : gstSlabs) {

						            double taxable = slabTaxableMap.get(slab);
						            double cgst = taxable * (slab / 2) / 100;
						            double sgst = taxable * (slab / 2) / 100;
						            double igst = 0.0;
						            double subTotal = taxable + cgst + sgst + igst;

						            taxTable.addCell(makeTaxValueCell(slab + "%", taxValueFont));
						            taxTable.addCell(makeTaxValueCell(String.format("%.2f", taxable), taxValueFont));
						            taxTable.addCell(makeTaxValueCell(String.format("%.2f", cgst), taxValueFont));
						            taxTable.addCell(makeTaxValueCell(String.format("%.2f", sgst), taxValueFont));
						            taxTable.addCell(makeTaxValueCell(String.format("%.2f", igst), taxValueFont));
						            taxTable.addCell(makeTaxValueCell(String.format("%.2f", subTotal), taxValueFont));
						        }


						        document.add(taxTable);
						        document.add(new Paragraph(" "));


						        document.close();
						        String[] itemIdArray = itemIds.split(","); 
						        for (String itemId : itemIdArray) {
						            patientService.updateVendorPdfPath(pdfFileName, filePath, poNo, itemId);
						        }
						        //patientService.updateVendorPdfPath(pdfFileName,filePath,poNo,itemIds);
						        sendPdfToClient(response, filePath, poNo);

						    } catch (Exception e) {
						        e.printStackTrace();
						    }

						    return null;
						}

						if ("uploadInvoiceDocument".equalsIgnoreCase(lStrActionVal))
						{
							
							lStrPageName = "UploadInvoiceDocument";
						}
						if ("getUploadList".equals(lStrActionVal)) {
							List<LabelBean> POList =new ArrayList<LabelBean>();
			    			String status=request.getParameter("status");
			    			//String status=request.getParameter("status");
			    			try 
			    			{   
			    			    	POList = patientService.getUploadList(lStrUserId,status);
			    			}
			    			catch(Exception e)
			    			{
			    				e.printStackTrace();
			    			}
			    			String message = new Gson().toJson(POList);
			    			response.setContentType("application/json");
			    			request.setAttribute("AjaxMessage", message);
			    			return mapping.findForward("ajaxResult");

					    }

						if ("viewVendorDocument".equalsIgnoreCase(lStrActionVal)) {

						    String docId = request.getParameter("docPath"); 

						    List<LabelBean> list1 = patientService.viewVendorDocument(docId);

						    if (list1 == null || list1.isEmpty()) {
						        response.sendError(HttpServletResponse.SC_NOT_FOUND, "File path not found");
						        return null;
						    }

						    String savedPath = list1.get(0).getSTATUS();

						    File file = new File(savedPath);
						    if (!file.exists()) {
						        response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found on NAS");
						        return null;
						    }

						    String base64;
						    if (savedPath.toLowerCase().endsWith(".pdf")) {
						        base64 = "data:application/pdf;base64," +
						                 patientService.convertPDFToBase64(savedPath);
						    } else {
						        base64 = "data:image/png;base64," +
						                 patientService.convertImageToBase64(savedPath);
						    }

						    List<Map<String, String>> list = new ArrayList<>();
						    Map<String, String> map = new HashMap<>();
						    map.put("SAVED_NAME", base64);
						    list.add(map);

						    String json = new Gson().toJson(list);
						    response.setContentType("application/json");
						    response.getWriter().write(json);
						    return null;
						}

						if ("uploadVendorDocument".equalsIgnoreCase(lStrActionVal)) {

				            String result;

				            try {
				            	 PatientForm uplaod = (PatientForm) form;
				            	 FormFile file = uplaod.getUpload(); 

				                HashMap<String, Object> fileUpload = new HashMap<>();
				                List<HashMap<String, Object>> uploadList = new ArrayList<>();
				                HashMap<String, Object> row = new HashMap<>();

				                row.put("poNo", request.getParameter("poNo"));
				                row.put("indentId", request.getParameter("indentId"));
				                row.put("poQty", request.getParameter("poQty"));
				                row.put("itemId", request.getParameter("itemId"));
				                row.put("invoiceNo", request.getParameter("invoiceNo"));
				                row.put("invoiceDate", request.getParameter("invoiceDate"));
				                row.put("quantity", request.getParameter("quantity"));
				                row.put("pendingQty", request.getParameter("pendingQty"));
				                row.put("fileUpload", file); 

				                uploadList.add(row);
				                fileUpload.put("upload", uploadList);

				                result = patientService.uploadVendorDocument(fileUpload, lStrUserId);

				            }
				            catch (Exception e) {
				                e.printStackTrace();
				                result = "ERROR";
				            }

				            Gson gson = new Gson();
				            request.setAttribute("AjaxMessage", gson.toJson(result));
				            return mapping.findForward("ajaxResult");
				        }
				

						if ("viewStoreKeeperDrugInventoryForm".equalsIgnoreCase(lStrActionVal))
						{
							try
							{
								List<LabelBean> storeDrugList=patientService.getStoreDrugList(dispname);
								request.setAttribute("storeDrugList", storeDrugList);
								
							}
							catch(Exception e)
							{
								e.printStackTrace();
							}
							lStrPageName = "StoreKeeperDrugsInventory";
						}
						if ("indentOnclick".equalsIgnoreCase(lStrActionVal))
						{
				    			List<LabelBean> POList =new ArrayList<LabelBean>();
				    			String invoiceNo=request.getParameter("invoiceNo");
				    			String status=request.getParameter("status");
				    			String dispId = request.getParameter("dispId");
				    			String poNo = request.getParameter("poNo");
				    			try 
				    			{   
				    			    	POList = patientService.indentOnclick(invoiceNo,status,dispId,poNo);
				    			}
				    			catch(Exception e)
				    			{
				    				e.printStackTrace();
				    			}
				    			String message = new Gson().toJson(POList);
				    			response.setContentType("application/json");
				    			request.setAttribute("AjaxMessage", message);
				    			return mapping.findForward("ajaxResult");
				    		}
						if ("addNewMnfNDist1".equalsIgnoreCase(lStrActionVal)) {
							 String message = "";
							    try {
							    	String storePOListJson = request.getParameter("storePOList");
							    	String dispId = request.getParameter("dispname");
							        if (storePOListJson == null || storePOListJson.trim().isEmpty()) {
							            message = "ERROR~No vendor data received";
							            request.setAttribute("AjaxMessage", message);
							            lStrPageName = "ajaxResult";
							            return mapping.findForward(lStrPageName);
							        }
							        JSONArray storeList = new JSONArray(storePOListJson);
							        List<HashMap<String, Object>> storePOList = new ArrayList<>();
							        for (int i = 0; i < storeList.length(); i++) {
							        	JSONObject vnd = storeList.getJSONObject(i);
							            HashMap<String, Object> record = new HashMap<>();

							            record.put("poId", vnd.optString("poId"));
							            record.put("dispId", dispId);
							            record.put("drugId", vnd.optString("drugId"));
							            record.put("drugTypeId", vnd.optString("drugTypeId"));
							            record.put("mnfctrId", vnd.optString("mnfctrId"));
							            record.put("dstrbtrId", vnd.optString("dstrbtrId"));

							            record.put("drugName", vnd.optString("drugName"));
							            record.put("drugType", vnd.optString("drugType"));
							            record.put("batchNo", vnd.optString("batchNo"));

							            record.put("poQty", vnd.optString("poQty"));
							            record.put("receivedQty", vnd.optString("receivedQty"));
							            record.put("currentReceiptQty", vnd.optString("currentReceiptQty"));
							            record.put("quantity", vnd.optString("quantity"));

							            record.put("rcPrice", vnd.optString("rcPrice"));
							            record.put("invoiceNo", vnd.optString("invoiceNo"));
							            record.put("invoiceAmount", vnd.optString("invoiceAmount"));
							            record.put("gstSlab", vnd.optString("gstSlab"));
							            record.put("totalAmount", vnd.optString("totalAmount"));

							            record.put("poStatus", vnd.optString("poStatus"));
							            record.put("internalStatus", vnd.optString("internalStatus"));
							            record.put("itemId", vnd.opt("itemId"));
							            record.put("indentId", vnd.opt("indentId"));
							            String poDateStr = vnd.optString("poDate");
							            Date poDate = null;

							            if (poDateStr != null && !poDateStr.trim().isEmpty()) {
							                try {
							                    SimpleDateFormat sdf1 = new SimpleDateFormat("MMM dd, yyyy hh:mm:ss a", Locale.ENGLISH);
							                    poDate = sdf1.parse(poDateStr);
							                } catch (Exception e) {
							                    e.printStackTrace();
							                    System.err.println("Invalid date format for poDate: " + poDateStr);
							                }
							            }
							            record.put("poDate", poDate);

							            record.put("invoiceDate", vnd.optString("invoiceDate"));
							            record.put("expiryDate", vnd.optString("expiryDate"));

							            storePOList.add(record);
							        }
							        HashMap<String, Object> storedrugList = new HashMap<>();
							        storedrugList.put("storePOList", storePOList);
							        storedrugList.put("dispId", dispId);
							       
							        message = "SUCCESS";
							        boolean res = patientService.addNewMnfNDist1(storedrugList, lStrUserId);

							        if(res) {
							            session.setAttribute("res", res);
							        }

							        message = "SUCCESS~" + res;
							    } catch (Exception e) {
							        e.printStackTrace();
							        message = "ERROR~" + e.getMessage();
							    }
							    request.setAttribute("AjaxMessage", message);
							    lStrPageName = "ajaxResult";
							}
							   
		return mapping.findForward(lStrPageName);
	}
	private PdfPCell makeLabelCell(String text) {
	    PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 10, Font.BOLD)));
	    cell.setPadding(4); 
	    return cell;
	}

	private PdfPCell makeValueCell(String text) {
	    PdfPCell cell = new PdfPCell(new Phrase(text, new Font(Font.HELVETICA, 10)));
	    cell.setPadding(4); 
	    return cell;
	}

	
	private String safe(Object o) {
	    return o == null ? "" : String.valueOf(o);
	}
	
	private void sendPdfToClient(HttpServletResponse response, String filePath, String poNo) throws Exception 
	{
	    FileInputStream in = new FileInputStream(new File(filePath));
	    ServletOutputStream out = response.getOutputStream();

	    response.setContentType("application/pdf");
	    String s = poNo.replaceAll("/", "_");
	    response.setHeader("Content-Disposition", "attachment; filename=" + s + ".pdf");

	    byte[] buffer = new byte[4096];
	    int bytesRead;

	    while ((bytesRead = in.read(buffer)) != -1) {
	        out.write(buffer, 0, bytesRead);
	    }

	    in.close();
	    out.flush();
	    out.close();
	}


	private static void addItemHeader(PdfPTable t) {
	    String[] cols = {
	            "Item Description", "Mfg/Dt", "Exp/Dt", "Batch", "Mfac/Mkt By",
	            "HSN Code", "M.R.P", "Qty", "Fr", "Rate", "GST", "Total Amount"
	    };

	    for (String col : cols) {
	        PdfPCell c = new PdfPCell(new Phrase(col, new Font(Font.HELVETICA, 9, Font.BOLD)));
	        c.setHorizontalAlignment(Element.ALIGN_CENTER);
	        c.setPadding(4);
	        t.addCell(c);
	    }
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
	
	private double parseDoubleSafe(String val) {
	    try {
	        return val == null || val.trim().isEmpty() ? 0.0 : Double.parseDouble(val);
	    } catch (Exception e) {
	        return 0.0;
	    }
	}
	private PdfPCell makeTaxValueCell(String value, Font font) {
	    PdfPCell cell = new PdfPCell(new Phrase(value, font));
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	    cell.setPadding(4);     
	    return cell;
	}
	private PdfPCell makeItemValueCell(String value) {
	    Font f = new Font(Font.HELVETICA, 8, Font.NORMAL);
	    PdfPCell cell = new PdfPCell(new Phrase(value == null ? "" : value, f));
	    cell.setPadding(3);
	    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	    return cell;
	}
	
	private String formatDateForPdf(Object dateObj) {
	    if (dateObj == null) return "";
	    try {
	        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	        if (dateObj instanceof Date) {
	            return sdf.format((Date) dateObj);
	        }

	        if (dateObj instanceof String) {
	            String dateStr = (String) dateObj;
	            SimpleDateFormat dbFormat =
	                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
	            Date parsedDate = dbFormat.parse(dateStr);
	            return sdf.format(parsedDate);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return "";
	}




}
