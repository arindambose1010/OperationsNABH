package com.ahct.caseSearch.action;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.caseSearch.form.CasesSearchForm;
import com.ahct.caseSearch.service.CasesSearchService;
import com.ahct.caseSearch.vo.CasesSearchVO;
import com.ahct.claims.service.ClaimsFlowService;
import com.ahct.claims.util.ClaimCases;
import com.ahct.claims.util.ClaimsConstants;
import com.ahct.claims.valueobject.ClaimsFlowVO;
import com.ahct.common.service.CommonService;
import com.ahct.common.util.ServiceFinder;
import com.ahct.common.vo.LabelBean;
import com.ahct.flagging.service.FlaggingService;
import com.ahct.flagging.vo.FlaggingVO;
import com.ahct.patient.service.PatientService;
import com.ahct.patient.vo.PatientVO;
import com.ahct.preauth.service.CasesApprovalService;
import com.ahct.preauth.service.PreauthService;
import com.ahct.preauth.vo.PreauthVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.service.TimeLoggingService;
import com.ahct.caseSearch.vo.casesSearchRecordVO;


public class CasesSearchAction extends Action{
	static final Logger gLogger = Logger.getLogger(CasesSearchAction.class);
	 public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	    {
		 response.setHeader("Cache-Control","no-cache");
	        response.setHeader("pragma","no-cache");
	        response.setDateHeader("Expires", 0);    
	        HttpSession session = request.getSession ( false ) ;
	        String lStrResultPage = HtmlEncode.verifySession(request, response);
	        if (lStrResultPage.length() > 0)
	            return mapping.findForward("sessionExpire");
	        ResourceBundle bundle = ResourceBundle.getBundle ( "SGVConstants" ) ;
	        CasesApprovalService casesApprovalService = ( CasesApprovalService ) ServiceFinder.getContext ( request ).getBean ( "casesApprovalService" ) ;
	        CasesSearchService casesSearchService = (CasesSearchService)ServiceFinder.getContext(request).getBean("casesSearchService");
	    	PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
	        ClaimsFlowService claimsService = (ClaimsFlowService) ServiceFinder
			.getContext(request).getBean("claimsFlowService");
	        TimeLoggingService loggingService =  (TimeLoggingService) ServiceFinder.getContext(request).getBean("loggingService");
	        SimpleDateFormat sds = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS a");

	        FlaggingService flaggingService=(FlaggingService) ServiceFinder.getContext(request).getBean("flaggingService");
	        PreauthService preauthService = (PreauthService)ServiceFinder.getContext(request).getBean("preauthService");
	        CasesSearchForm casesSearchForm = (CasesSearchForm)form;
            String lStrRowsperpage=bundle.getString("case.RowsPerPage");
            String lStrStartIndex=bundle.getString("case.StartIndex0");
            String showPage= bundle.getString("case.ShowPage");
            String   lStrEmpId = null;
           // String lStrLocId  = null;
            String lStrLangId = null;
           // String lStrUserName = null;
            String next=bundle.getString("case.next");
            String lStrFlagStatus = request.getParameter("actionFlag");
            String lStrUserGroup = null;String lStrUserState=null;
            if(session == null || session.getAttribute("EmpID")==null)
            {
                 request.setAttribute("Message","Your session has expired. Login again");
                 return mapping.findForward("sessionExpire");
              }
            if ( ( session.getAttribute ( "EmpID" ) != null ) && !( session.getAttribute ( "EmpID" ).equals ( "" ) ) )
			  lStrEmpId = ( String ) session.getAttribute ( "EmpID" ) ;
			    /*if ( ( session.getAttribute ( "LocID" ) != null ) && !( session.getAttribute ( "LocID" ).equals ( "" ) ) )
			    	lStrLocId = ( String ) session.getAttribute ( "LocID" ) ;*/
			    if ( ( session.getAttribute ( "LangID" ) != null ) && !( session.getAttribute ( "LangID" ).equals ( "" ) ) )
			    	lStrLangId = ( String ) session.getAttribute ( "LangID" ) ;
			    /*if ( ( session.getAttribute ( "userName" ) != null ) && !( session.getAttribute ( "userName" ).equals ( "" ) ) )
			    	lStrUserName = ( String ) session.getAttribute ( "userName" ) ;*/
			    if ( ( session.getAttribute ( "userState" ) != null ) && !( session.getAttribute ( "userState" ).equals ( "" ) ) )
			    	lStrUserState = ( String ) session.getAttribute ( "userState" ) ;
			    request.setAttribute("langID", lStrLangId);
			 List<LabelBean> groupsList = null;
				   if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
						    	groupsList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
				   ConfigurationService configurationService = (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		            Configuration config = configurationService.getConfiguration();
		            CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");      
	        
		            
	/*Added for CEO PREAUTH WORKFLOW*/ 		
		            
			        if(lStrFlagStatus!= null && "submitNextLvlCeo".equals(lStrFlagStatus)) 
			        {
			        	PreauthVO preauthVO=new PreauthVO();
			        	PreauthVO preauthVOCase=new PreauthVO();
			        	String buttonVal = request.getParameter("buttonVal");
				    	String viewType = request.getParameter("viewType");
				    	String[] casesSelected=casesSearchForm.getCasesSelected();
				    	casesSelected=casesSelected[0].split(",");
				    	int caseCount=0;
				    	int successCount=0;
				    	
				    	 String caseSearchType=casesSearchForm.getCaseSearchType();
				         String caseForApproval=casesSearchForm.getCaseForApproval();
				    	 String module=casesSearchForm.getModule();
				         String ceoFlag=casesSearchForm.getCeoFlag();
				         
				         request.setAttribute("caseSearchType",caseSearchType);
				         request.setAttribute("casesForApproval",caseForApproval);
				         request.setAttribute("module",module);
				         request.setAttribute("ceoFlag",ceoFlag);
				    	
				    	if(casesSelected!=null){
				    	for(String caseId:casesSelected)
				    	{
				    	
				    		
				    	if(caseId!=null && !("").equalsIgnoreCase(caseId))
				    	{
				    	preauthVOCase=preauthService.getCaseDetails(caseId);
				    	if(preauthVOCase!=null)
				    	{
				    		preauthVO.setCaseId(caseId);
				    		preauthVO.setCruUsr(lStrEmpId);
				    	preauthVO.setViewType(viewType);
				    	
				    	
						if(buttonVal.equalsIgnoreCase("CD24")){
							preauthVO.setRemarks("Preauthorization Approved By Ceo");
						}
						else if(buttonVal.equalsIgnoreCase("CD30")){
							preauthVO.setRemarks("Preauthorization Rejected By Ceo");
						}
						
				    	//preauthVO.setRemarks("CEO APPROVED");
				    	preauthVO.setButtonVal(buttonVal);
				    	preauthVO.setCaseStatusId(preauthVOCase.getCaseStatusId());
				   // 	preauthVO.setUserRole(lStrUserRole);
				    	preauthVO.setUserRole(config.getString("Group.CEO"));
				    	preauthVO.setCochlearYN(preauthVOCase.getCochlearYN());
				    	preauthVO.setScheme(preauthVOCase.getScheme());
				    	
				    	//preauthDetailsForm.setGenRemarks(preauthDetailsForm.getGenRemarks());
				    	/**
				    	 * save and enhancement approve amount and pckg approval amount
				    	 */
				    	preauthVO.setEnhApprvAmt(preauthVOCase.getEnhApprAmt());
				    	
				    	/**
				    	 * save preauth package amount
				    	 */
				    	preauthVO.setApprvdPckAmt(preauthVOCase.getApprvdPckAmt());
				    	preauthVO.setComorBidAppvAmt(preauthVOCase.getComorBidAppvAmt());
				    	preauthVO.setPtdTotalApprvAmt(preauthVOCase.getPtdTotalApprvAmt());
				    	preauthVO.setNabhAmt(preauthVOCase.getNabhAmt());
				    	caseCount++;
				    	}
				    	String msg=null;
				    	if(preauthVOCase.getEnhFlag()!=null && ("Y").equalsIgnoreCase(preauthVOCase.getEnhFlag()))
				    		msg = preauthService.saveEnhancementPreauth(preauthVO);
				    	else 
				    		msg= preauthService.sentVerifyPanel(preauthVO);
				    	
				    	
				    	
				    	if(msg!=null && !"".equalsIgnoreCase(msg)  && !"failure".equalsIgnoreCase(msg))
				    		{
				    		request.setAttribute("ResultMsg", msg);
				    			if(msg!=null)
				    				{
				    					if(!msg.equalsIgnoreCase(config.getString("preauth_msg_CD213")))
					    					{
				    						successCount++;	
					    					}   
				    				}
				    		}
				    	if(successCount==caseCount)
				    	{
				    		request.setAttribute("successFlag", "Y");
				    		
				    	}
				    	else if(successCount==0)
				    	{
				    		request.setAttribute("successFlag", "N");
				    	}
				    	else if(successCount<caseCount && successCount!=0 )
				    	{
				    		request.setAttribute("successFlag", "P");
				    		int diff=caseCount-successCount;
				    		request.setAttribute("diff",diff);
				    	}
				    	
				    	}
				    	
				    	}}
				    	lStrFlagStatus="OnloadCaseSearch";
			        }         
		            
		            
	 /*End of CEO PREAUTH WORKFLOW*/ 		            
		            
		        /*Added for processing bulk payments at ACO level*/
			        
			        if("ClaimsBulkApproval".equalsIgnoreCase(lStrFlagStatus)) {
						
						String errFlag=request.getParameter("errFlag");
						String acoFlag=request.getParameter("acoFlag");
						String actionDone=request.getParameter("actionDone");
						ClaimsFlowVO claimFlowVO = new ClaimsFlowVO();
						String cases=request.getParameter("cases");
						String[] casesSelected=null;
						Date date=new Date();
						if(cases!=null && cases.length()>0)
						{
				    	casesSelected=cases.split("~");
				    	int count=0;
				    	if(casesSelected!=null){
					    	for(String caseId:casesSelected)
					    	{
					    		String caseStatus=null;
					    		ClaimsFlowVO resultVO = new ClaimsFlowVO();
					    	if(caseId!=null && !("").equalsIgnoreCase(caseId))
					    	{
					    		caseStatus=claimsService.getCaseStat(caseId);
					    		if(acoFlag!=null && acoFlag.equalsIgnoreCase("Y"))
					    		claimFlowVO.setRoleId(config.getString("EHF.Claims.ACO"));
					    		claimFlowVO.setCaseStat(caseStatus);
					    		
					    		claimFlowVO.setCaseId(caseId);
					    		claimFlowVO.setActionDone(actionDone);
					    		claimFlowVO.setUserId(lStrEmpId);
					    		claimFlowVO.setAcoRemark("Claim Verified BY ACO");
					    		String patientId = claimsService
										.checksurgFlag(caseId);
					    		String surgFlag = claimsService
										.checksurgFlagNew(patientId);
					    		claimFlowVO.setSurgFlg(surgFlag);
					    	}
					    
					    	
					    	if (errFlag != null
									&& errFlag.equalsIgnoreCase(ClaimsConstants.Y))
					    		resultVO = claimsService.saveErrClaim(claimFlowVO);
					    	else
					    	resultVO = claimsService.saveClaim(claimFlowVO);
					    	if (!resultVO.getMsg().equalsIgnoreCase(ClaimsConstants.Failure)) {
					    		
					    		count++;
					    	}

					    	
						
					}
				    	}
				    	
				    	if (count>0) {
							request.setAttribute("saveMsg", claimFlowVO.getMsg());
							request.setAttribute("disableAll", ClaimsConstants.YES);
							request.setAttribute("nextStat", claimFlowVO.getCaseNextStat());
							request.setAttribute("errSearchType",errFlag);
							request.setAttribute("errSearchTypeM","M");
							
						}
						
						lStrFlagStatus = "OnloadCaseSearch";
						}
					}
			 
			 /*@author Sravan
			  * NABH Hospitals report
			  */
	        if(lStrFlagStatus!= null && "nabhReport".equals(lStrFlagStatus)) 
		        
		    {
	        	int  totalRecords=1,pageNo=1,showPageNew=1,rowsPerPage=10,pages=1;
    			int startIndex=1,maxResults=0,pageId=0;;
    			int endIndex=10;
    			int startPage=1;
    			int endPage=10;
    			
    	 		String csvFlag=request.getParameter("csvFlag");
    	 		CasesSearchVO casesSearchVO = new CasesSearchVO();
    	 		
    	 		if(request.getParameter("inputFromDt")!=null && !request.getParameter("inputFromDt").equals(""))
    	 			casesSearchForm.setFromDate(request.getParameter("inputFromDt"));
                if(request.getParameter("inputToDt")!=null && !request.getParameter("inputToDt").equals(""))
                	casesSearchForm.setToDate(request.getParameter("inputToDt"));
                if(request.getParameter("schemeId")!=null && !request.getParameter("schemeId").equals("-1"))
                	casesSearchForm.setSchemeType(request.getParameter("schemeId"));
                if(casesSearchForm.getSchemeType()!=null)
                	casesSearchVO.setSchemeId(casesSearchForm.getSchemeType());    
                    
                if(casesSearchForm.getFromDate()!=null)
                	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
                if(casesSearchForm.getToDate()!=null)
                	casesSearchVO.setToDate(casesSearchForm.getToDate());
                
				
				if(request.getParameter("startIndex")!=null)
					startIndex=Integer.parseInt(request.getParameter("startIndex"));
				if(request.getParameter("endIndex")!=null)
					maxResults=Integer.parseInt(request.getParameter("endIndex"));
				if(request.getParameter("pageId")!=null)
					pageId=Integer.parseInt(request.getParameter("pageId"));
				
				if("Y".equalsIgnoreCase(csvFlag) && !"".equalsIgnoreCase(csvFlag))
					casesSearchVO.setCsvMsg(csvFlag);
				
				casesSearchVO.setStrtIndex(startIndex);
				casesSearchVO.setMaxresults(maxResults);
				casesSearchVO.setPageId(pageId);
				
			 if(casesSearchForm.getFromDate() != null && casesSearchForm.getToDate() != null && !"".equalsIgnoreCase(casesSearchForm.getFromDate()) && !"".equalsIgnoreCase(casesSearchForm.getToDate()) )
	          {
				 CasesSearchVO nabhHospReport = casesSearchService.getListCasesNabh(casesSearchVO);
		        	
		          if(nabhHospReport != null)
		          { 
		        	if(nabhHospReport.getNabhReportList() != null && nabhHospReport.getNabhReportList().size()>0  )
		        	 {
		        		if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
							{
		        				String lStrDirPath = config.getString("CASESSEARCH.MapPath");
		        				String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE");
							
								if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
									lStrFileName = lStrFileName	+ "CSV";
								else
									lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");

								File lcsvfile = createFile(lStrDirPath,lStrFileName);
								char separator = '^';
						        StringBuilder line = new StringBuilder();  
								if(nabhHospReport.getNabhReportList().size()>0)
									{
							            line.append("CASE_ID");
									    line.append(separator);
										line.append("CASE_NO");
										line.append(separator);
										line.append("PATIENT_TYPE");
										line.append(separator);
										line.append("CLAIM_NUMBER");
										line.append(separator);
										line.append("PATIENT_NAME");
										line.append(separator);
										line.append("CARD_NO");
										line.append(separator);
										line.append("EMP_TYPE");
										line.append(separator);
										line.append("PAST_ILLNESS");
										line.append(separator);
										line.append("CATEGORY_CODE");
										line.append(separator);
										line.append("CATEGORY_NAME");
										line.append(separator);
										line.append("PROCEDURE_CODE");
										line.append(separator);
										line.append("PROCEDURE_NAME");
										line.append(separator);
										line.append("CLIAM_STATUS");
										line.append(separator);
										line.append("AGE");
										line.append(separator);
										line.append("GENDER");
										line.append(separator);
										line.append("HOSP_ID");
										line.append(separator);
										line.append("HOSP_NAME");
										line.append(separator);
										line.append("STATUS_DATE");
										line.append(separator);
										line.append("IP_CASE_REG_DATE");
										line.append(separator);
										line.append("PREAUTH_DATE");
										line.append(separator);
										line.append("PREAUTH_APPV_DATE");
										line.append(separator);
										line.append("DISCHARGE_DATE");
										line.append(separator);
										line.append("DEATH_DATE");
										line.append(separator);
										line.append("ESTIMATED/PREAUTH_AMT");
										line.append(separator);
										line.append("CONSUMBALES_AMT");
										line.append(separator);
										line.append("BILLED_AMT");
										line.append(separator);
										line.append("CLAIM_AMT");
										line.append(separator);
										line.append("CLAIM_SUB_DATE");
										line.append(separator);
										line.append("HOSP_AMT");
										line.append(separator);
										line.append("TDS_AMT");
										line.append(separator);
										line.append("RF_AMT");
										line.append(separator);
										line.append("TRANS_ID");
										line.append(separator);
										line.append("TRANS_DATE");
										line.append(separator);
										line.append("REMARKS");
										line.append(separator);
										line.append("SBH_PAID_DATE");
										line.append("\n");
						              
										
										for(CasesSearchVO cases : nabhHospReport.getNabhReportList())
											{
											if(cases.getCASEID()==null)
												line.append("-NA-");
											else
												line.append(cases.getCASEID());
										    	line.append(separator);
										    	
											if(cases.getCASENO()==null)
												line.append("-NA-");
											else
												line.append(cases.getCASENO());
												line.append(separator);
											
											if(cases.getPATIENTTYPE()==null)
												line.append("-NA-");
											else
											{
												if(cases.getPATIENTTYPE().equalsIgnoreCase("IPM"))
												{
													line.append("IP Medical");
													line.append(separator);
												}
												if(cases.getPATIENTTYPE().equalsIgnoreCase("IP"))
												{
													line.append("IP Surgical");
													line.append(separator);
												}
												if(cases.getPATIENTTYPE().equalsIgnoreCase("IPD"))
												{
													line.append("IP Daycare");
													line.append(separator);
												}
											}
											if(cases.getCLAIMNUM()==null)
												line.append("-NA-");
											else
												line.append(cases.getCLAIMNUM());
												line.append(separator);
											
											if(cases.getPATIENTNAME()==null)
												line.append("-NA-");
											else
												line.append(cases.getPATIENTNAME());
												line.append(separator);
											
											if(cases.getCARDNO()==null)
												line.append("-NA-");
											else
												line.append(cases.getCARDNO());
												line.append(separator);
											
											if(cases.getEMPTYPE()==null)
												line.append("-NA-");
											else
											{
												if(cases.getEMPTYPE().equalsIgnoreCase("R"))
												{
													line.append("Retired Journalist");
													line.append(separator);
												}
												if(cases.getEMPTYPE().equalsIgnoreCase("J"))
												{
													line.append("Working Journalist");
													line.append(separator);
												}
												if(cases.getEMPTYPE().equalsIgnoreCase("P"))
												{
													line.append("Pensioner");
													line.append(separator);
												}
												if(cases.getEMPTYPE().equalsIgnoreCase("E"))
												{
													line.append("Employee");
													line.append(separator);
												}
											}
											if(cases.getPASTILLNESS()==null)
												line.append("-NA-");
											else
												line.append(cases.getPASTILLNESS());
												line.append(separator);
											
											if(cases.getCATEGORYCODE()==null)
												line.append("-NA-");
											else
												line.append(cases.getCATEGORYCODE());
												line.append(separator);
											
											if(cases.getCATEGORYNAME()==null)
												line.append("-NA-");
											else
												line.append(cases.getCATEGORYNAME());
												line.append(separator);
											
											if(cases.getPROCCODE()==null)
												line.append("-NA-");
											else
												line.append(cases.getPROCCODE());
												line.append(separator);
											
											if(cases.getPROCNAME()==null)
												line.append("-NA-");
											else
												line.append(cases.getPROCNAME());
												line.append(separator);
											
											if(cases.getCLIAMSTATUS()==null)
												line.append("-NA-");
											else
												line.append(cases.getCLIAMSTATUS());
												line.append(separator);
											
											if(cases.getAGE()==null)
												line.append("-NA-");
											else
												line.append(cases.getAGE());
												line.append(separator);
											
											if(cases.getGENDER()==null)
												line.append("-NA-");
											else
												line.append(cases.getGENDER());
												line.append(separator);
											
											if(cases.getHOSPID()==null)
												line.append("-NA-");
											else
												line.append(cases.getHOSPID());
												line.append(separator);
											
											if(cases.getHOSPNAME()==null)
												line.append("-NA-");
											else
												line.append(cases.getHOSPNAME());
												line.append(separator);
											
											if(cases.getSTATUSDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getSTATUSDATE());
												line.append(separator);
											
											if(cases.getIPCASEREGDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getIPCASEREGDATE());
												line.append(separator);
											
											if(cases.getPREAUTHDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getPREAUTHDATE());
												line.append(separator);
											
											if(cases.getPREAUTHAPPVDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getPREAUTHAPPVDATE());
												line.append(separator);
											
											if(cases.getDISCHDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getDISCHDATE());
												line.append(separator);
											
											if(cases.getDEATHDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getDEATHDATE());
												line.append(separator);
											
											if(cases.getESTIMATEDAMT()==null)
												line.append("-NA-");
											else
												line.append(cases.getESTIMATEDAMT());
												line.append(separator);
											
											if(cases.getCONSUMAMT()==null)
												line.append("-NA-");
											else
												line.append(cases.getCONSUMAMT());
												line.append(separator);
											
											if(cases.getBILLEDAMT()==null)
												line.append("-NA-");
											else
												line.append(cases.getBILLEDAMT());
												line.append(separator);
											
											if(cases.getCLAIMAMOUNT()==null)
												line.append("-NA-");
											else
												line.append(cases.getCLAIMAMOUNT());
												line.append(separator);
											
											if(cases.getCLAIMSUBDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getCLAIMSUBDATE());
												line.append(separator);
											
											if(cases.getHOSPAMT()==null)
												line.append("-NA-");
											else
												line.append(cases.getHOSPAMT());
												line.append(separator);
											
											if(cases.getTDSAMT()==null)
												line.append("-NA-");
											else
												line.append(cases.getTDSAMT());
												line.append(separator);
											
											if(cases.getRFAMT()==null)
												line.append("-NA-");
											else
												line.append(cases.getRFAMT());
												line.append(separator);
											
											if(cases.getTRANSID()==null)
												line.append("-NA-");
											else
												line.append(cases.getTRANSID());
												line.append(separator);
											
											if(cases.getTRANSDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getTRANSDATE());
												line.append(separator);
											
											if(cases.getREMARKS()==null)
												line.append("-NA-");
											else
												line.append(cases.getREMARKS());
												line.append(separator);
											
											if(cases.getSBHPAIDDATE()==null)
												line.append("-NA-");
											else
												line.append(cases.getSBHPAIDDATE());
												line.append("\n");
											
										}
									}
								
									request.setAttribute("File", line.toString().getBytes());    
								    response.setContentType(config.getString("ACC.CsvContentType"));
								    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
								    response.setCharacterEncoding("UTF-8");
							        return mapping.findForward("openFile");
							}
		        			casesSearchForm.setNabhreport(nabhHospReport.getNabhReportList());
		        			
		                	request.setAttribute("totalPages",nabhHospReport.getTotalPages());
							request.setAttribute("totalRecords",nabhHospReport.getTotalRecords());
							request.setAttribute("pageId",nabhHospReport.getPageId());
							request.setAttribute("endIndex",nabhHospReport.getMaxresults());
							request.setAttribute("startIndex",nabhHospReport.getStrtIndex());
							request.setAttribute("endResults",nabhHospReport.getStrtIndex()+nabhHospReport.getNabhReportList().size());
		            }
		        	else
		    		{
		    		request.setAttribute("totalrecords", "N");        	
		    		}
		          }
	          }
		          return mapping.findForward("nabhReport");
		    }
	        if (lStrFlagStatus!= null &&  "cancelPatient".equalsIgnoreCase(lStrFlagStatus))
			{
				String caseId = request.getParameter("caseId");
				PatientVO patVo=new PatientVO();
				if(caseId != null && !"".equalsIgnoreCase(caseId))
				patVo.setCaseId(caseId);
				patVo.setLstUpdUsr(lStrEmpId);
				String msg = patientService.cancelPatient(patVo);
				request.setAttribute("saveMsg", msg);
				lStrFlagStatus="OnloadCaseSearch";
			}     
		            
		    if(lStrFlagStatus!= null && "OnloadCaseSearch".equals(lStrFlagStatus)) 
	        
		    {
		    	String disallFlag=request.getParameter("disAllowance");
	        	String searchType= request.getParameter("caseSearchType");
	        	lStrStartIndex=bundle.getString("case.StartIndex0");
	        	String nabhCase=request.getParameter("nabhCase");
	        	/**
	        	 * set view flag on click of back button
	        	 */
	        	String msg = request.getParameter("msg");
	        	String caseId = request.getParameter("CaseId");	
	 	        String flag = request.getParameter("flag");
	 	       String module = request.getParameter("module");
	 	        String excelFlag = request.getParameter("excelFlag");
	 	        String ajaxFlag=request.getParameter("ajaxFlag");
	 	        String csvFlag = request.getParameter("csvFlag");
	 	        String caseForDissFlg = request.getParameter("disSearchType");
	 	        String claimType = request.getParameter("errClaimType");
	 	        String casesForApprovalFlag = request.getParameter("casesForApproval");
	 	       String mul=(String)request.getAttribute("errSearchTypeM");
	 	      String errCaseApprovalFlag = "";
	 	       if(mul !=null && !"".equalsIgnoreCase(mul) && "M".equalsIgnoreCase(mul))
	 	       {
	 	    	 errCaseApprovalFlag = (String)request.getAttribute("errSearchType");
	 	       }
	 	       else
	 	       {
	 	    	  errCaseApprovalFlag = request.getParameter("errSearchType"); 
	 	       }
	 	        
	 	      String errDentalCaseApprovalFlag = request.getParameter("errDentalSearchType");
	 	       String lStrSchemeType = request.getParameter("schemeType");
	 	       String patientScheme=request.getParameter("patientScheme");
	 	      String patScheme=request.getParameter("patScheme");
	 	       String ceoFlag=request.getParameter("ceoFlag");
	 	       String pendingFlag=request.getParameter("pendingFlag");
	 	       String exceedFlg=request.getParameter("exceedFlg");
	 	       String nimsFlag=request.getParameter("nimsFlag");
	 	      String searchClaimtype=request.getParameter("claimType");	 	      //String searchClaimtype=request.getParameter("claimType");
	 	     request.setAttribute("disallFlag", disallFlag);
	 	       request.setAttribute("nimsFlag", nimsFlag);
	 	      request.setAttribute("exceedFlg", exceedFlg);
	 	       request.setAttribute("pendingFlag",pendingFlag);
	 	      if(patientScheme == null || patientScheme .equals(""))
	 	    	 patientScheme = request.getParameter("patScheme");
	 	       
	 	       if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
	 	    	  casesForApprovalFlag = casesSearchForm.getCasesForApprovalFlag();
	 	        if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
	 	        	casesForApprovalFlag="N";
	 	        if(errCaseApprovalFlag==null || errCaseApprovalFlag.equalsIgnoreCase(""))
	 	        	errCaseApprovalFlag="N";
	 	       if(errDentalCaseApprovalFlag==null || errDentalCaseApprovalFlag.equalsIgnoreCase(""))
	 	    	  errDentalCaseApprovalFlag="N";
	 	        if(caseForDissFlg==null || caseForDissFlg.equalsIgnoreCase(""))
	 	        	caseForDissFlg="N";
	 	        if(caseId != null && !caseId.equals(""))
	 	          casesSearchService.setviewFlag(caseId,flag,lStrEmpId);
	 	        if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
	 	        	lStrSchemeType = lStrUserState;
	 	        /**
	 	         * end 
	 	         */
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
	        	casesSearchVO.setCaseSearchType(searchType);
	        	casesSearchVO.setClaimStatus(casesSearchForm.getClaimId());
	        	casesSearchVO.setCatId(casesSearchForm.getCatId());
	        	casesSearchVO.setErrStatusId(casesSearchForm.getErrStatusId());
	        	casesSearchVO.setWapNo(casesSearchForm.getWapNo());
	         	casesSearchVO.setPatientScheme(patientScheme);
	        	casesSearchVO.setSurgyId(casesSearchForm.getSurgyId());
	        	casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
	        	casesSearchVO.setClaimNo(casesSearchForm.getClaimNo());
	        	casesSearchVO.setPatName(casesSearchForm.getPatName());
	        	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
	        	casesSearchVO.setToDate(casesSearchForm.getToDate());
	        	casesSearchVO.setSearchCaseType(searchClaimtype);
	        	casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setRowsPerPage(lStrRowsperpage);
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	casesSearchVO.setShowPage(showPage);
	        	casesSearchVO.setGrpList(groupsList);
	        	casesSearchVO.setCasesForApprovalFlag(casesForApprovalFlag);
	        	casesSearchVO.setErrCaseApprovalFlag(errCaseApprovalFlag);
	        	casesSearchVO.setErrDentalCaseApprovalFlag(errDentalCaseApprovalFlag);
	        	casesSearchVO.setCaseForDissFlg(caseForDissFlg);
	        	casesSearchVO.setModule(module);
	        	casesSearchVO.setHospId(casesSearchForm.getHospId());
	        	casesSearchVO.setMainCatName(casesSearchForm.getMainCatName());
	        	casesSearchVO.setCatName(casesSearchForm.getCatName());
	        	casesSearchVO.setProcName(casesSearchForm.getProcName());
	        	casesSearchVO.setTelephonicId(casesSearchForm.getTelephonicId());
	        	casesSearchVO.setExcelFlag(excelFlag);
	        	casesSearchForm.setCasesForApprovalFlag(casesForApprovalFlag);
	        	casesSearchVO.setSchemeVal(lStrSchemeType);
	        	casesSearchVO.setPatientScheme(patientScheme);
	        	casesSearchVO.setCeoFlag(ceoFlag);
	        	casesSearchVO.setPendingFlag(pendingFlag);
	        	casesSearchVO.setExceedFlg(exceedFlg);
	        	casesSearchVO.setDisallowanceFlag(disallFlag);
	        	if(nimsFlag != null && !"".equalsIgnoreCase(nimsFlag))
	        		casesSearchVO.setNimsFlag(nimsFlag);
	        	if(claimType != null && !"".equalsIgnoreCase(claimType))	
	        	{
	        		casesSearchVO.setClaimType(claimType);
	        		casesSearchForm.setClaimType(claimType);
	        	}
	        	casesSearchVO.setNabhCase(nabhCase);
	        	
	        	List<String> lst=new ArrayList<String>();
	        	String acoFlag=null;
	        	for(LabelBean lb:groupsList)
	        		{
	        			if(lb.getID()!=null)
	        				lst.add(lb.getID());
	        		}
    			if(lst!=null)
					{
						if(lst.size()>0)
							if(lst.contains("GP222"))
								casesSearchForm.setShowHospital("show");
						if(lst.contains(config.getString("EHF.Claims.ACO")))
					
							acoFlag="Y";
							
					}	        			
	        	
	        	request.setAttribute("acoFlag",acoFlag);

				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
					casesSearchForm.setShowScheme("show");
					casesSearchForm.setSchemeType(lStrSchemeType);
				}
				else {
					casesSearchForm.setShowScheme("hide");
				}
				
	        	/**
	        	 * set user role
	        	 */
				for(LabelBean labelBean:groupsList)
				{
					if(labelBean.getID() != null && config.getString("view_audit_names")!=null && config.getString("view_audit_names").contains("~"+labelBean.getID()+"~") )
					 {
						  request.setAttribute("is_trust_emp", "Y");
						  casesSearchVO.setColorCodeView("Y");
						  break;
					 }
					 else
					 {
						 request.setAttribute("is_trust_emp", "N");
						 casesSearchVO.setColorCodeView("N");
					 }
				}
	        	 for(LabelBean labelBean:groupsList)
	     	    {
	     	   if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) || labelBean.getID().equalsIgnoreCase(config.getString("preauth_mithra_role")) ))
	     	     {
	     		    request.setAttribute("is_medco_mithra", "Y");
	     			lStrUserGroup = labelBean.getID() ;
	     			break;
	     	     }
	     	     else{
	     		lStrUserGroup = null;
	     		casesSearchVO.setRoleId(labelBean.getID());
	     	     }
	     	     }
	        	//casesSearchVO = casesSearchService.getUserRole(casesSearchVO);
	        	 casesSearchVO.setUserRole(lStrUserGroup);
	        	if(casesSearchForm.getRowsPerPage() != null && !casesSearchForm.getRowsPerPage().equalsIgnoreCase(""))
	        	{
	        		lStrRowsperpage = casesSearchForm.getRowsPerPage();
	        		casesSearchVO.setRowsPerPage(casesSearchForm.getRowsPerPage() );
	        		casesSearchForm.setRowsPerPage( casesSearchForm.getRowsPerPage());
	        	}
	        	if(casesSearchForm.getShowPage() != null && !casesSearchForm.getShowPage().equals(""))
	        	{
	        		casesSearchVO.setShowPage(casesSearchForm.getShowPage());
	        		casesSearchForm.setShowPage(casesSearchForm.getShowPage());
	        		if(Integer.parseInt(casesSearchForm.getShowPage()) >Integer.parseInt(bundle.getString("case.RowsPerPage")) )
	        		{
	        			casesSearchForm.setPrev(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())-1)));
	        			casesSearchForm.setNext(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())+1)));	
	        		
	        		}
	        	}
	        	casesSearchVO.setTotRowCount(casesSearchForm.getTotalRows());
	        	if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y"))
	        		casesSearchVO.setDownloadCSV("Y");
	        	
	        	CasesSearchVO  lstCasesSearchVO=new CasesSearchVO();   
	        	
	        	if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y") && ajaxFlag!=null && ajaxFlag.equalsIgnoreCase("N")){
	        		
	        		lstCasesSearchVO= casesSearchService.getListCases(casesSearchVO);
	        		if( (claimType != null &&  !"".equalsIgnoreCase(claimType)) && "S".equalsIgnoreCase(claimType))
	        		{
	        			if(	lstCasesSearchVO.getLstCaseSearch().size()<1)
	        			{
	        				casesSearchVO.setClaimType("M");
	        				lstCasesSearchVO= casesSearchService.getListCases(casesSearchVO);
	        			}
	        		}
		        	 //for excel 
					List<casesSearchRecordVO> expList = new ArrayList<casesSearchRecordVO>();
					casesSearchRecordVO row1 = new casesSearchRecordVO();
					row1.setSno("SL No.");
					row1.setPatientType("Patient Type");
					row1.setCaseNo("Case Number");
					row1.setClaimNo("Claim Number");
					row1.setPatName("Patient Name");
					row1.setWapNo("Card Number");
					row1.setCaseStatusId("Claim Status");
					row1.setHospitalId("Hospital ID");
					row1.setHospitalName("Hospital Name");
					row1.setLstUpdDt("Status Date");
					row1.setInpatientCaseSubDt("Inpatient Case Registration Date");
					row1.setCsPreauthDt("PreAuth Date");
					row1.setPreauthAprvDt("PreAuth Apprv Date");
					row1.setCsDisDt("Discharge Date");
					row1.setCsDeathDt("Death Date");
					row1.setEstimAmt("Estimated/Preauth Amount");
					row1.setBilledAmt("Billed Amount");
					row1.setHospName("Claim Amount");
					row1.setClmSubDt("Claim Submitted Date");
					row1.setApprovedAmount("Hospital Amount");
					row1.setHospAccountName("TDS Amount");
					row1.setDistrict("RF Amount");
					row1.setCsTransId("Transaction Id");
					row1.setCsTransDt("Transaction Date");
					row1.setCsRemarks("Remarks");
					row1.setCsSbhDt("SBH Paid Date");
					expList.add(row1);
					int i = 0;
					for (CasesSearchVO row : lstCasesSearchVO.getLstCaseSearch()) {
						row1 = new casesSearchRecordVO();
						row1.setSno(++i + "");
						row1.setPatientType(row.getPatientType());
						row1.setCaseNo(row.getCaseNo());
						row1.setClaimNo(row.getClaimNo());
						row1.setPatName(row.getPatientName());
						row1.setWapNo(row.getWapNo());
						row1.setCaseStatusId(row.getClaimStatus());
						row1.setHospitalId(row.getHospId());		
						row1.setHospitalName(row.getHospName());
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
						if(row.getLstUpdDt() != null )
							try {
								row1.setLstUpdDt(sdf.format(row.getLstUpdDt()));
							} catch (Exception e) {
								e.printStackTrace();
							}					
						row1.setInpatientCaseSubDt(row.getInpatientCaseSubDt());
						row1.setCsPreauthDt(row.getCsPreauthDt());
						row1.setPreauthAprvDt(row.getPreauthAprvDate());
						row1.setCsDisDt(row.getCsDisDt());
						row1.setCsDeathDt(row.getCsDeathDt());
						row1.setEstimAmt(row.getEstimAmt()+"");
						row1.setBilledAmt(row.getBilledAmt()+"");
						row1.setHospName(row.getClaimAmt()+"");
						row1.setClmSubDt(row.getClmSubDt());
						if(row.getHospPctAmt()!=null && !"".equalsIgnoreCase(row.getHospPctAmt()) && Integer.parseInt(row.getHospPctAmt())>0)
							{
								
									row1.setApprovedAmount(row.getHospPctAmt());
							}
						else if(row.getTdsHospPctAmt()!=null && !"".equalsIgnoreCase(row.getTdsHospPctAmt()) && Integer.parseInt(row.getTdsHospPctAmt())>0)
							{
								
									row1.setApprovedAmount(row.getTdsHospPctAmt());
							}
						else	
							row1.setApprovedAmount("");
						row1.setHospAccountName(row.getTdsPctAmt());
						row1.setDistrict(row.getTrustPctAmt());
						//CasesSearchVO  caseSearchVO  = casesSearchService.getAccountDetails(row);
						//row1.setApprovedAmount(caseSearchVO.getIssueResultFlagSize());
						//row1.setHospAccountName(caseSearchVO.getIssuestatus());
						//row1.setDistrict(caseSearchVO.getIssuetitle());
						row1.setCsTransId(row.getCsTransId());
						row1.setCsTransDt(row.getCsTransDt());
						row1.setCsRemarks(row.getCsRemarks());
						row1.setCsSbhDt(row.getCsSbhDt());
						expList.add(row1);
					}

					String lStrDirPath = config.getString("CASESSEARCH.MapPath");
					String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE");
					
					if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
						lStrFileName = lStrFileName	+ "CSV";
					else
						lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");

						// Creates file in EHFTemp folder of client machine
						if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y")){
							 char separator = ',';
					         StringBuilder line = new StringBuilder();  
							for(casesSearchRecordVO caseSearchRVO :  expList){
								line.append(caseSearchRVO.getSno());
								line.append(separator);
								line.append(caseSearchRVO.getPatientType());
								line.append(separator);
								line.append(caseSearchRVO.getCaseNo());
								line.append(separator);
								if(caseSearchRVO.getClaimNo()==null)
									line.append("");
								else
								    line.append(caseSearchRVO.getClaimNo());
								line.append(separator);
								line.append(caseSearchRVO.getPatName());
								line.append(separator);
								line.append(caseSearchRVO.getWapNo());
								line.append(separator);
								line.append(caseSearchRVO.getCaseStatusId());
								line.append(separator);
								line.append(caseSearchRVO.getHospitalId());
								line.append(separator);
								line.append(caseSearchRVO.getHospitalName());
								line.append(separator);							
								line.append(caseSearchRVO.getLstUpdDt());
								line.append(separator);
								line.append(caseSearchRVO.getInpatientCaseSubDt());
								line.append(separator);
								if(caseSearchRVO.getCsPreauthDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsPreauthDt());
								line.append(separator);
								if(caseSearchRVO.getPreauthAprvDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getPreauthAprvDt());
								line.append(separator);
								if(caseSearchRVO.getCsDisDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsDisDt());
								line.append(separator);
								if(caseSearchRVO.getCsDeathDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsDeathDt());
								/*
								 * Sravan NABH changes Start
								 */
								
								line.append(separator);
								if(caseSearchRVO.getEstimAmt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getEstimAmt());
								
								
								line.append(separator);
								if(caseSearchRVO.getBilledAmt()==null)
									line.append("0");
								else
								line.append(caseSearchRVO.getBilledAmt());
								
								/*
								 * end 
								 */
								
								line.append(separator);
								if(caseSearchRVO.getHospName()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getHospName());
								line.append(separator);
								if(caseSearchRVO.getClmSubDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getClmSubDt());
								line.append(separator);
								if(caseSearchRVO.getApprovedAmount()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getApprovedAmount());
								line.append(separator);
								if(caseSearchRVO.getHospAccountName()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getHospAccountName());
								line.append(separator);
								if(caseSearchRVO.getDistrict()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getDistrict());
								line.append(separator);							
								if(caseSearchRVO.getCsTransId()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsTransId());
								line.append(separator);
								if(caseSearchRVO.getCsTransDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsTransDt());
								line.append(separator);
								if(caseSearchRVO.getCsRemarks()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsRemarks());
								line.append(separator);
								if(caseSearchRVO.getCsSbhDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsSbhDt());
								line.append("\n");
							}
						    OutputStream responseOut=response.getOutputStream();
							try{
									ByteArrayInputStream newByte=new ByteArrayInputStream(line.toString().getBytes());
									response.setContentType("text/csv");
									response.setHeader("Content-Disposition", "Attachment; filename ="+lStrFileName);
									response.setCharacterEncoding("UTF-8");
									int bufferSize=1024*1024*30;
									request.setAttribute ( "Extn", "CSV" ) ;
									byte outArray[]=new byte[bufferSize];
									int fileSize=newByte.read(outArray,0,bufferSize);
									if(fileSize!=-1)
										{
											responseOut.write(outArray, 0, fileSize);
										}
								}
							catch(Exception r)
								{
									r.printStackTrace();
								}
							finally
									{
										responseOut.flush();
										responseOut.close();
									}
							
						}else{					
						/*ExcelWriter.prepareExl(xlFile, expList);
						byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);
						
						request.setAttribute("File", lbBytes);
						response.setContentType(config
								.getString("REPORT.ExcelContentType"));
						response.setHeader("Content-Disposition",
								"Attachment; filename=" + lStrFileName);*/
						}
						
						return mapping.findForward(null);
					
		        	}  
	        	else if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y") && ajaxFlag!=null && ajaxFlag.equalsIgnoreCase("Y"))
	        		{
	        			lstCasesSearchVO=casesSearchService.getListCasesForCSV(casesSearchVO);
		        		String ajaxMsg=null;
		        		ajaxMsg="CSV Download Failed.Please try after sometime";	        		
		        		if(lstCasesSearchVO!=null)
		        			{
		        				if(lstCasesSearchVO.getCsvMsg()!=null)
		        					{
		        						if(lstCasesSearchVO.getCsvMsg().equalsIgnoreCase("Success"))
		        							ajaxMsg="CSV Download Started.Please find your CSV File in Downloads Tab after 5 Minutes ";
		        					}
		        			}
		        		request.setAttribute("AjaxMessage", ajaxMsg);
		        		return mapping.findForward("ajaxResult");
	        		}
	        	else
	        		lstCasesSearchVO= casesSearchService.getListCases(casesSearchVO);
	        	if( (claimType != null &&  !"".equalsIgnoreCase(claimType)) && "S".equalsIgnoreCase(claimType))
        		{
        			if(	lstCasesSearchVO.getLstCaseSearch().size()<1)
        			{
        				casesSearchVO.setClaimType("M");
        				lstCasesSearchVO= casesSearchService.getListCases(casesSearchVO);
        			}
        		}
	        	//vijay
	        	if(module!=null && module.equalsIgnoreCase("drugbreakup"))
	        	{
	        		lstCasesSearchVO= casesSearchService.getListCases(casesSearchVO);
	        	}
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	    request.setAttribute("StatusList", casesSearchService.getCaseStatus(module));
	        	    request.setAttribute("ErrStatusList", casesSearchService.getCaseErrStatus());
	        	    request.setAttribute("HospList", commonService.getHospitals());
	                request.setAttribute("ErrStatList", casesSearchService.getErroneousList());
	               // request.setAttribute("SurgeryList", casesSearchService.getListOfSurgery());	
	                casesSearchForm.setLstCaseSearch(lstCasesSearchVO.getLstCaseSearch());
	                request.setAttribute("catList", commonService.getCategoryList(lStrEmpId,casesSearchVO.getUserRole()));
	                request.setAttribute("icdCatList", new ArrayList<LabelBean>());
	                request.setAttribute("procList", new ArrayList<LabelBean>());
	                String dentalEnhFlag="";
					if(casesSearchVO.getMainCatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getMainCatName()))
	                {
	                	request.setAttribute("icdCatList", commonService.getCategorys(casesSearchVO.getMainCatName(),lStrEmpId,caseId));
	                	if(casesSearchVO.getCatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getCatName()))
		                {
	                		if(lStrSchemeType.equalsIgnoreCase(config.getString("COMMON")))
	                			lStrSchemeType=config.getString("AP");
	  
		                	request.setAttribute("procList", commonService.getProcedures(casesSearchVO.getCatName(), casesSearchVO.getMainCatName(),casesSearchVO.getHospId(), lStrSchemeType,dentalEnhFlag));
		                }
	                }
	                
	                /**
	            	 * pagination code starting
	            	 */
	               
	                casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
	                casesSearchForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
	                int liTotalRows =0;
	                if(lstCasesSearchVO.getTotRowCount()!=null)
	                	liTotalRows = Integer.parseInt( lstCasesSearchVO.getTotRowCount());
	            	int liRowsPerPage =Integer.parseInt( casesSearchForm.getRowsPerPage());
	            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
	                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
	                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
	                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
	                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
	           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
	                     { 
	                       liTotalPages=liTotalRows/liRowsPerPage;
	                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
	                          liTotalPages++;
	                       }
	           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
	           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
	                 {
	                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
	                    {
	                        liStartPage=liPageNo;
	                        liEndPage=liPageNo+9;
	                    }
	                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
	                    {
	                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
	                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
	                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
	                          liTemp=liPageNo-9;                  
	                        else
	                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
	                        liStartPage=liTemp;
	                        liEndPage=liTemp+9;                
	                    }
	                 } 
	           	  if(liEndPage > liTotalPages)
	                     liEndPage=liTotalPages;
	                 request.setAttribute("liEndPage", liEndPage);
	                 request.setAttribute("liTotalPages", liTotalPages);
	                 request.setAttribute("liStartPage", liStartPage);
	                 request.setAttribute("liPageNo", liPageNo);
	                 request.setAttribute("lStrRowsperpage", lStrRowsperpage);
	                 request.setAttribute("casesForApprovalFlag",casesForApprovalFlag);
	                 
	                 List<Integer> pages = new ArrayList<Integer>();
	                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
	                 {
	                	for(int i=1 ; i<=liTotalPages ;i++)
	                	{
	                		pages.add(i);
	                	}
	                 }
	                request.setAttribute("pages", pages);
	                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
	                {
	                if(casesSearchForm.getNext()!= null)
	                {
	                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
	                {
	                	casesSearchForm.setNext(null);	
	                }
	                }
	                else
	                	 casesSearchForm.setNext(next);
	                }
	              
	               
	                /**
	                 * pagination code ending
	                 */

	                request.setAttribute("autoActionFlag", "OnloadCaseSearch");
	                request.setAttribute("caseSearchType", searchType);
	                request.setAttribute("errSearchType", casesSearchVO.getErrCaseApprovalFlag());
	                request.setAttribute("denErrSearchType", casesSearchVO.getErrDentalCaseApprovalFlag());
	                request.setAttribute("disSearchType", casesSearchVO.getCaseForDissFlg());
	                request.setAttribute("module", module);
	                request.setAttribute("patientScheme", patientScheme);
	                request.setAttribute("ceoFlag",ceoFlag);
	                request.setAttribute("nabhCase", nabhCase);
	                if(searchType!=null && !searchType.equalsIgnoreCase("") && searchType.equalsIgnoreCase("ChronicOp"))
	                return mapping.findForward("searchChronicOP");
	                else if((nabhCase!=null && "Y".equalsIgnoreCase(nabhCase)))
	                	return mapping.findForward("nabhCasesSearch");	
	                else
	                	return mapping.findForward("search");
	        }
		    /**
		     * Added by ramalakshmi for cases search csv
		     */
		    if(lStrFlagStatus!= null && "casesSearchCSV".equals(lStrFlagStatus)) 
		        
		    {
		    	String disallFlag=request.getParameter("disAllowance");
		    	/*String caseStartTime = sds.format(new Date().getTime());*/
	        	String searchType= request.getParameter("caseSearchType");
	        	lStrStartIndex=bundle.getString("case.StartIndex0");
	        	String nabhCase=request.getParameter("nabhCase");
	        	/**
	        	 * set view flag on click of back button
	        	 */
	        	String msg = request.getParameter("msg");
	        	String caseId = request.getParameter("CaseId");	
	 	        String flag = request.getParameter("flag");
	 	       String module = request.getParameter("module");
	 	        String excelFlag = request.getParameter("excelFlag");
	 	        String ajaxFlag=request.getParameter("ajaxFlag");
	 	        String csvFlag = request.getParameter("csvFlag");
	 	        String caseForDissFlg = request.getParameter("disSearchType");
	 	        String casesForApprovalFlag = request.getParameter("casesForApproval");
	 	       String mul=(String)request.getAttribute("errSearchTypeM");
	 	      String errCaseApprovalFlag = "";
	 	       if(mul !=null && !"".equalsIgnoreCase(mul) && "M".equalsIgnoreCase(mul))
	 	       {
	 	    	 errCaseApprovalFlag = (String)request.getAttribute("errSearchType");
	 	       }
	 	       else
	 	       {
	 	    	  errCaseApprovalFlag = request.getParameter("errSearchType"); 
	 	       }
	 	        
	 	      String errDentalCaseApprovalFlag = request.getParameter("errDentalSearchType");
	 	       String lStrSchemeType = request.getParameter("schemeType");
	 	      String exceedFlg=request.getParameter("exceedFlg");
	 	       String patientScheme=request.getParameter("patientScheme");
	 	      String patScheme=request.getParameter("patScheme");
	 	       String ceoFlag=request.getParameter("ceoFlag");
	 	       String pendingFlag=request.getParameter("pendingFlag");
	 	       
	 	      if(patientScheme == null || patientScheme .equals(""))
	 	    	 patientScheme = request.getParameter("patScheme");
	 	       request.setAttribute("pendingFlag",pendingFlag);
	 	      request.setAttribute("exceedFlg", exceedFlg);
	 	       request.setAttribute("disallFlag", disallFlag);

	 	      
	 	       if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
	 	    	  casesForApprovalFlag = casesSearchForm.getCasesForApprovalFlag();
	 	        if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
	 	        	casesForApprovalFlag="N";
	 	        if(errCaseApprovalFlag==null || errCaseApprovalFlag.equalsIgnoreCase(""))
	 	        	errCaseApprovalFlag="N";
	 	       if(errDentalCaseApprovalFlag==null || errDentalCaseApprovalFlag.equalsIgnoreCase(""))
	 	    	  errDentalCaseApprovalFlag="N";
	 	        if(caseForDissFlg==null || caseForDissFlg.equalsIgnoreCase(""))
	 	        	caseForDissFlg="N";
	 	        if(caseId != null && !caseId.equals(""))
	 	          casesSearchService.setviewFlag(caseId,flag,lStrEmpId);
	 	        if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
	 	        	lStrSchemeType = lStrUserState;
	 	        /**
	 	         * end 
	 	         */
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
	        	casesSearchVO.setCaseSearchType(searchType);
	        	casesSearchVO.setClaimStatus(casesSearchForm.getClaimId());
	        	casesSearchVO.setCatId(casesSearchForm.getCatId());
	        	casesSearchVO.setErrStatusId(casesSearchForm.getErrStatusId());
	        	casesSearchVO.setWapNo(casesSearchForm.getWapNo());
	         	casesSearchVO.setPatientScheme(patientScheme);
	        	casesSearchVO.setSurgyId(casesSearchForm.getSurgyId());
	        	casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
	        	casesSearchVO.setClaimNo(casesSearchForm.getClaimNo());
	        	casesSearchVO.setPatName(casesSearchForm.getPatName());
	        	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
	        	casesSearchVO.setToDate(casesSearchForm.getToDate());
	        	casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setRowsPerPage(lStrRowsperpage);
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	casesSearchVO.setShowPage(showPage);
	        	casesSearchVO.setGrpList(groupsList);
	        	casesSearchVO.setCasesForApprovalFlag(casesForApprovalFlag);
	        	casesSearchVO.setErrCaseApprovalFlag(errCaseApprovalFlag);
	        	casesSearchVO.setErrDentalCaseApprovalFlag(errDentalCaseApprovalFlag);
	        	casesSearchVO.setCaseForDissFlg(caseForDissFlg);
	        	casesSearchVO.setModule(module);
	        	casesSearchVO.setHospId(casesSearchForm.getHospId());
	        	casesSearchVO.setMainCatName(casesSearchForm.getMainCatName());
	        	casesSearchVO.setCatName(casesSearchForm.getCatName());
	        	casesSearchVO.setProcName(casesSearchForm.getProcName());
	        	casesSearchVO.setTelephonicId(casesSearchForm.getTelephonicId());
	        	casesSearchVO.setExcelFlag(excelFlag);
	        	casesSearchForm.setCasesForApprovalFlag(casesForApprovalFlag);
	        	casesSearchVO.setSchemeVal(lStrSchemeType);
	        	casesSearchVO.setPatientScheme(patientScheme);
	        	casesSearchVO.setCeoFlag(ceoFlag);
	        	casesSearchVO.setPendingFlag(pendingFlag);
	        	casesSearchVO.setExceedFlg(exceedFlg);
	        	
	        	casesSearchVO.setNabhCase(nabhCase);
	        	casesSearchVO.setDisallowanceFlag(disallFlag);


	        	

	        	
	        	List<String> lst=new ArrayList<String>();
	        	String acoFlag=null;
	        	for(LabelBean lb:groupsList)
	        		{
	        			if(lb.getID()!=null)
	        				lst.add(lb.getID());
	        		}
    			if(lst!=null)
					{
						if(lst.size()>0)
							if(lst.contains("GP222"))
								casesSearchForm.setShowHospital("show");
						if(lst.contains(config.getString("EHF.Claims.ACO")))
					
							acoFlag="Y";
						if(lst.contains(config.getString("EHF.Claims.CEO")))
							ceoFlag="Y";
					}	        			
	        	
	        	request.setAttribute("acoFlag",acoFlag);
	        	request.setAttribute("ceoFlag", ceoFlag);

				if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
					casesSearchForm.setShowScheme("show");
					casesSearchForm.setSchemeType(lStrSchemeType);
				}
				else {
					casesSearchForm.setShowScheme("hide");
				}
				
	        	/**
	        	 * set user role
	        	 */
				for(LabelBean labelBean:groupsList)
				{
					if(labelBean.getID() != null && config.getString("view_audit_names")!=null && config.getString("view_audit_names").contains("~"+labelBean.getID()+"~") )
					 {
						  request.setAttribute("is_trust_emp", "Y");
						  casesSearchVO.setColorCodeView("Y");
						  break;
					 }
					 else
					 {
						 request.setAttribute("is_trust_emp", "N");
						 casesSearchVO.setColorCodeView("N");
					 }
				}
	        	 for(LabelBean labelBean:groupsList)
	     	    {
	     	   if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) || labelBean.getID().equalsIgnoreCase(config.getString("preauth_mithra_role")) ))
	     	     {
	     		    request.setAttribute("is_medco_mithra", "Y");
	     			lStrUserGroup = labelBean.getID() ;
	     			break;
	     	     }
	     	     else{
	     		lStrUserGroup = null;
	     		casesSearchVO.setRoleId(labelBean.getID());
	     	     }
	     	     }
	        	//casesSearchVO = casesSearchService.getUserRole(casesSearchVO);
	        	 casesSearchVO.setUserRole(lStrUserGroup);
	        	if(casesSearchForm.getRowsPerPage() != null && !casesSearchForm.getRowsPerPage().equalsIgnoreCase(""))
	        	{
	        		lStrRowsperpage = casesSearchForm.getRowsPerPage();
	        		casesSearchVO.setRowsPerPage(casesSearchForm.getRowsPerPage() );
	        		casesSearchForm.setRowsPerPage( casesSearchForm.getRowsPerPage());
	        	}
	        	if(casesSearchForm.getShowPage() != null && !casesSearchForm.getShowPage().equals(""))
	        	{
	        		casesSearchVO.setShowPage(casesSearchForm.getShowPage());
	        		casesSearchForm.setShowPage(casesSearchForm.getShowPage());
	        		if(Integer.parseInt(casesSearchForm.getShowPage()) >Integer.parseInt(bundle.getString("case.RowsPerPage")) )
	        		{
	        			casesSearchForm.setPrev(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())-1)));
	        			casesSearchForm.setNext(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())+1)));	
	        		
	        		}
	        	}
	        	casesSearchVO.setTotRowCount(casesSearchForm.getTotalRows());
	        	if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y"))
	        		casesSearchVO.setDownloadCSV("Y");
	        	
	        	CasesSearchVO  lstCasesSearchVO=new CasesSearchVO();   
	        	
	        	if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y") && ajaxFlag!=null && ajaxFlag.equalsIgnoreCase("N")){
	        		
	        		lstCasesSearchVO= casesSearchService.getCasesSearchCSV(casesSearchVO);
	        		
		        	 //for excel 
					List<casesSearchRecordVO> expList = new ArrayList<casesSearchRecordVO>();
					casesSearchRecordVO row1 = new casesSearchRecordVO();
					row1.setSno("SL No.");
					row1.setCaseNo("Case Number");
					row1.setClaimNo("Claim Number");
					row1.setPatName("Patient Name");
					row1.setWapNo("Card Number");
					row1.setCaseStatusId("Claim Status");
					row1.setHospitalId("Hospital ID");
					row1.setHospitalName("Hospital Name");
					row1.setLstUpdDt("Status Date");
					row1.setInpatientCaseSubDt("Inpatient Case Registration Date");
					row1.setCsPreauthDt("PreAuth Date");
					row1.setPreauthAprvDt("PreAuth Apprv Date");
					row1.setCsDisDt("Discharge Date");
					row1.setCsDeathDt("Death Date");
					row1.setClaimInit("Claim Initiated Amount");
					row1.setHospName("Claim Approved Amount");
					row1.setClmSubDt("Claim Submitted Date");
					row1.setApprovedAmount("Hospital Amount");
					row1.setHospAccountName("TDS Amount");
					row1.setDistrict("RF Amount");
					row1.setCsTransId("Transaction Id");
					row1.setCsTransDt("Transaction Date");
					row1.setCsRemarks("Remarks");
					row1.setCsSbhDt("SBH Paid Date");
					expList.add(row1);
					int i = 0;
					for (CasesSearchVO row : lstCasesSearchVO.getLstCaseSearch()) {
						row1 = new casesSearchRecordVO();
						row1.setSno(++i + "");
						row1.setCaseNo(row.getCASENO());
						row1.setClaimNo(row.getCLAIMNO());
						row1.setPatName(row.getPATIENTNAME());
						row1.setWapNo(row.getWAPNO());
						row1.setCaseStatusId(row.getCLAIMSTATUS());
						row1.setHospitalId(row.getHOSPID());		
						row1.setHospitalName(row.getHOSPNAME());
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
						if(row.getLSTUPDDT() != null )
							try {
								row1.setLstUpdDt(sdf.format(row.getLSTUPDDT()));
							} catch (Exception e) {
								e.printStackTrace();
							}					
						row1.setInpatientCaseSubDt(row.getINPATIENTCASESUBDT());
						row1.setCsPreauthDt(row.getCSPREAUTHDT());
						row1.setPreauthAprvDt(row.getPREAUTHAPRVDATE());
						row1.setCsDisDt(row.getCSDISDT());
						row1.setCsDeathDt(row.getCSDEATHDT());
						row1.setClaimInit(row.getCLAIMINITAMT()+"");
						row1.setHospName(row.getCLAIMAMT()+"");
						row1.setClmSubDt(row.getCLMSUBDT());
						if(row.getHOSPPCTAMT()!=null && !"".equalsIgnoreCase(row.getHOSPPCTAMT()) && Integer.parseInt(row.getHOSPPCTAMT())>0)
							{
								
									row1.setApprovedAmount(row.getHOSPPCTAMT());
							}
						else if(row.getTDSHOSPPCTAMT()!=null && !"".equalsIgnoreCase(row.getTDSHOSPPCTAMT()) && Integer.parseInt(row.getTDSHOSPPCTAMT())>0)
							{
								
									row1.setApprovedAmount(row.getTDSHOSPPCTAMT());
							}
						else	
							row1.setApprovedAmount("");
						row1.setHospAccountName(row.getTDSPCTAMT());
						row1.setDistrict(row.getTRUSTPCTAMT());
						//CasesSearchVO  caseSearchVO  = casesSearchService.getAccountDetails(row);
						//row1.setApprovedAmount(caseSearchVO.getIssueResultFlagSize());
						//row1.setHospAccountName(caseSearchVO.getIssuestatus());
						//row1.setDistrict(caseSearchVO.getIssuetitle());
						row1.setCsTransId(row.getCSTRANSID());
						row1.setCsTransDt(row.getCSTRANSDT());
						row1.setCsRemarks(row.getCSREMARKS());
						row1.setCsSbhDt(row.getCSSBHDT());
						expList.add(row1);
					}

					String lStrDirPath = config.getString("CASESSEARCH.MapPath");
					String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE");
					
					if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y"))
						lStrFileName = lStrFileName	+ "CSV";
					else
						lStrFileName = lStrFileName	+ config.getString("REPORT.XlsExtn");

						// Creates file in EHFTemp folder of client machine
						if(csvFlag!=null && csvFlag.equalsIgnoreCase("Y")){
							 char separator = ',';
					         StringBuilder line = new StringBuilder();  
							for(casesSearchRecordVO caseSearchRVO :  expList){
								line.append(caseSearchRVO.getSno());
								line.append(separator);
								line.append(caseSearchRVO.getCaseNo());
								line.append(separator);
								if(caseSearchRVO.getClaimNo()==null)
									line.append("");
								else
								    line.append(caseSearchRVO.getClaimNo());
								line.append(separator);
								line.append(caseSearchRVO.getPatName());
								line.append(separator);
								line.append(caseSearchRVO.getWapNo());
								line.append(separator);
								line.append(caseSearchRVO.getCaseStatusId());
								line.append(separator);
								line.append(caseSearchRVO.getHospitalId());
								line.append(separator);
								line.append(caseSearchRVO.getHospitalName());
								line.append(separator);							
								line.append(caseSearchRVO.getLstUpdDt());
								line.append(separator);
								line.append(caseSearchRVO.getInpatientCaseSubDt());
								line.append(separator);
								if(caseSearchRVO.getCsPreauthDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsPreauthDt());
								line.append(separator);
								if(caseSearchRVO.getPreauthAprvDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getPreauthAprvDt());
								line.append(separator);
								if(caseSearchRVO.getCsDisDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsDisDt());
								line.append(separator);
								if(caseSearchRVO.getCsDeathDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsDeathDt());
								line.append(separator);
								if(caseSearchRVO.getClaimInit()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getClaimInit());
								line.append(separator);
								if(caseSearchRVO.getHospName()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getHospName());
								line.append(separator);
								if(caseSearchRVO.getClmSubDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getClmSubDt());
								line.append(separator);
								if(caseSearchRVO.getApprovedAmount()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getApprovedAmount());
								line.append(separator);
								if(caseSearchRVO.getHospAccountName()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getHospAccountName());
								line.append(separator);
								if(caseSearchRVO.getDistrict()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getDistrict());
								line.append(separator);							
								if(caseSearchRVO.getCsTransId()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsTransId());
								line.append(separator);
								if(caseSearchRVO.getCsTransDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsTransDt());
								line.append(separator);
								if(caseSearchRVO.getCsRemarks()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsRemarks());
								line.append(separator);
								if(caseSearchRVO.getCsSbhDt()==null)
									line.append("");
								else
								line.append(caseSearchRVO.getCsSbhDt());
								line.append("\n");
							}
						    OutputStream responseOut=response.getOutputStream();
							try{
									ByteArrayInputStream newByte=new ByteArrayInputStream(line.toString().getBytes());
									response.setContentType("text/csv");
									response.setHeader("Content-Disposition", "Attachment; filename ="+lStrFileName);
									response.setCharacterEncoding("UTF-8");
									int bufferSize=1024*1024*30;
									request.setAttribute ( "Extn", "CSV" ) ;
									byte outArray[]=new byte[bufferSize];
									int fileSize=newByte.read(outArray,0,bufferSize);
									if(fileSize!=-1)
										{
											responseOut.write(outArray, 0, fileSize);
										}
								}
							catch(Exception r)
								{
									r.printStackTrace();
								}
							finally
									{
										responseOut.flush();
										responseOut.close();
									}
							
						}else{					
						/*ExcelWriter.prepareExl(xlFile, expList);
						byte[] lbBytes = ExcelWriter.getBytesFromFile(xlFile);
						
						request.setAttribute("File", lbBytes);
						response.setContentType(config
								.getString("REPORT.ExcelContentType"));
						response.setHeader("Content-Disposition",
								"Attachment; filename=" + lStrFileName);*/
						}
						
						return mapping.findForward(null);
					
		        	}  
	        	else if(excelFlag!=null && excelFlag.equalsIgnoreCase("Y") && ajaxFlag!=null && ajaxFlag.equalsIgnoreCase("Y"))
	        		{
	        			lstCasesSearchVO=casesSearchService.getListCasesForCSV(casesSearchVO);
		        		String ajaxMsg=null;
		        		ajaxMsg="CSV Download Failed.Please try after sometime";	        		
		        		if(lstCasesSearchVO!=null)
		        			{
		        				if(lstCasesSearchVO.getCsvMsg()!=null)
		        					{
		        						if(lstCasesSearchVO.getCsvMsg().equalsIgnoreCase("Success"))
		        							ajaxMsg="CSV Download Started.Please find your CSV File in Downloads Tab after 5 Minutes ";
		        					}
		        			}
		        		request.setAttribute("AjaxMessage", ajaxMsg);
		        		return mapping.findForward("ajaxResult");
	        		}
	        	else
	        		lstCasesSearchVO= casesSearchService.getListCases(casesSearchVO);
	        		
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	    request.setAttribute("StatusList", casesSearchService.getCaseStatus(module));
	        	    request.setAttribute("ErrStatusList", casesSearchService.getCaseErrStatus());
	        	    request.setAttribute("HospList", commonService.getHospitals());
	                request.setAttribute("ErrStatList", casesSearchService.getErroneousList());
	               // request.setAttribute("SurgeryList", casesSearchService.getListOfSurgery());	
	                casesSearchForm.setLstCaseSearch(lstCasesSearchVO.getLstCaseSearch());
	                request.setAttribute("catList", commonService.getCategoryList(lStrEmpId,casesSearchVO.getUserRole()));
	                request.setAttribute("icdCatList", new ArrayList<LabelBean>());
	                request.setAttribute("procList", new ArrayList<LabelBean>());
	                String dentalEnhFlag="";
					if(casesSearchVO.getMainCatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getMainCatName()))
	                {
	                	request.setAttribute("icdCatList", commonService.getCategorys(casesSearchVO.getMainCatName(),lStrEmpId,caseId));
	                	if(casesSearchVO.getCatName()!=null && !"".equalsIgnoreCase(casesSearchVO.getCatName()))
		                {
	                		if(lStrSchemeType.equalsIgnoreCase(config.getString("COMMON")))
	                			lStrSchemeType=config.getString("AP");
	  
		                	request.setAttribute("procList", commonService.getProcedures(casesSearchVO.getCatName(), casesSearchVO.getMainCatName(),casesSearchVO.getHospId(), lStrSchemeType,dentalEnhFlag));
		                }
	                }
	                
	                /**
	            	 * pagination code starting
	            	 */
	               
	                casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
	                casesSearchForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
	                int liTotalRows =0;
	                if(lstCasesSearchVO.getTotRowCount()!=null)
	                	liTotalRows = Integer.parseInt( lstCasesSearchVO.getTotRowCount());
	            	int liRowsPerPage =Integer.parseInt( casesSearchForm.getRowsPerPage());
	            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
	                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
	                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
	                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
	                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
	           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
	                     { 
	                       liTotalPages=liTotalRows/liRowsPerPage;
	                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
	                          liTotalPages++;
	                       }
	           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
	           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
	                 {
	                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
	                    {
	                        liStartPage=liPageNo;
	                        liEndPage=liPageNo+9;
	                    }
	                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
	                    {
	                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
	                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
	                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
	                          liTemp=liPageNo-9;                  
	                        else
	                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
	                        liStartPage=liTemp;
	                        liEndPage=liTemp+9;                
	                    }
	                 } 
	           	  if(liEndPage > liTotalPages)
	                     liEndPage=liTotalPages;
	                 request.setAttribute("liEndPage", liEndPage);
	                 request.setAttribute("liTotalPages", liTotalPages);
	                 request.setAttribute("liStartPage", liStartPage);
	                 request.setAttribute("liPageNo", liPageNo);
	                 request.setAttribute("lStrRowsperpage", lStrRowsperpage);
	                 request.setAttribute("casesForApprovalFlag",casesForApprovalFlag);
	                 
	                 List<Integer> pages = new ArrayList<Integer>();
	                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
	                 {
	                	for(int i=1 ; i<=liTotalPages ;i++)
	                	{
	                		pages.add(i);
	                	}
	                 }
	                request.setAttribute("pages", pages);
	               /* String caseEndTime = sds.format(new Date().getTime());*/
	                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
	                {
	                if(casesSearchForm.getNext()!= null)
	                {
	                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
	                {
	                	casesSearchForm.setNext(null);	
	                }
	                }
	                else
	                	 casesSearchForm.setNext(next);
	                }
	              
	               
	                /**
	                 * pagination code ending
	                 */

	                request.setAttribute("autoActionFlag", "OnloadCaseSearch");
	                request.setAttribute("caseSearchType", searchType);
	                request.setAttribute("errSearchType", casesSearchVO.getErrCaseApprovalFlag());
	                request.setAttribute("denErrSearchType", casesSearchVO.getErrDentalCaseApprovalFlag());
	                request.setAttribute("disSearchType", casesSearchVO.getCaseForDissFlg());
	                request.setAttribute("module", module);
	                request.setAttribute("patientScheme", patientScheme);
	                request.setAttribute("ceoFlag",ceoFlag);
	                request.setAttribute("nabhCase", nabhCase);
/*	                loggingService.logTime("PreauthLoadingTime", casesSearchVO.getCaseId(), caseStartTime, caseEndTime, lStrEmpId, "EHS_Operations");*/
	                if(searchType!=null && !searchType.equalsIgnoreCase("") && searchType.equalsIgnoreCase("ChronicOp"))
	                return mapping.findForward("searchChronicOP");
	                else if((nabhCase!=null && "Y".equalsIgnoreCase(nabhCase)))
	                	return mapping.findForward("nabhCasesSearch");	
	                	
	                else
	                return mapping.findForward("search");
	        }
		    /**
		     * End of cases search CSV Download
		     */
		    /**
		     * End of cases seach csv
		     */
		    if(lStrFlagStatus!= null && "getCSVFile".equals(lStrFlagStatus))
	    		{
		    		String requestId=casesSearchForm.getRequestId();
		    		String fileName=casesSearchForm.getFname();
		    		String userId=casesSearchForm.getIssueId();
		    		CasesSearchVO csvVO=new CasesSearchVO();
		    		String failed="";
		    		
		    		if(requestId!=null && fileName!=null && userId!=null)
		    			{
		    				csvVO.setRequestId(requestId);
		    				csvVO.setTestName(fileName);
		    				csvVO.setUserId(userId);
		    				
		    				CasesSearchVO finalVO=new CasesSearchVO();
		    				finalVO=casesSearchService.getCSVPath(csvVO);
		    				if(finalVO!=null)
		    					{
		    						if(finalVO.getCsvFilePath()!=null &&
		    									finalVO.getTestName()!=null)
		    							{
			    							String csvFilePath=finalVO.getCsvFilePath();
		    								String csvFileName=finalVO.getTestName();
		    								File file=new File(csvFilePath);
		    								FileInputStream fis=new FileInputStream(file);
		    								DataInputStream dis =new DataInputStream(fis);
		    							    OutputStream responseOut=response.getOutputStream();
		    								try{
		    										byte [] data=new byte[dis.available()];
		    										fis.read(data);
		    										response.setContentType("text/csv");
		    										response.setHeader("Content-Disposition", "Attachment; filename ="+csvFileName);
		    										response.setCharacterEncoding("UTF-8");
		    										request.setAttribute ( "Extn", "CSV" ) ;
				    								responseOut.write(data);
		    									}
		    								catch(Exception r)
		    									{
		    										r.printStackTrace();
//		    										gLogger.error("Error occured in CSV File Download in CasesSearchAction"+r.getMessage());
		    									}
		    								finally
	    										{
	    											fis.close();
	    											dis.close();
	    											responseOut.flush();
	    											responseOut.close();
	    										}
		    							}
		    						else
		    							failed="Y";
		    					}
		    				else
		    					failed="Y";
		    			}
		    		else
		    			failed="Y";
		    		if(failed.equalsIgnoreCase("Y"))
		    			{
		    				request.setAttribute("resultMsg", config.getString("CSV.Failed.Msg"));
		    				lStrFlagStatus="downloadUserCSV";
		    			}
		    		else 
		    			return mapping.findForward(null);
		    		
	    		}
		    if(lStrFlagStatus!= null && "downloadUserCSV".equals(lStrFlagStatus))
		    	{
		    		CasesSearchVO casesSearchVO1=new CasesSearchVO();
		    		CasesSearchVO casesSearchVO2=new CasesSearchVO();
		    		
		    		casesSearchVO1.setUserId(lStrEmpId);
		    		casesSearchVO2=casesSearchService.viewCSVDownloads(casesSearchVO1);
		    		
		    		if(casesSearchVO2!=null)
		    			{
		    				if(casesSearchVO2.getLstCaseSearch()!=null)
		    					casesSearchForm.setLstCaseSearch(casesSearchVO2.getLstCaseSearch());
		    			}
		    		
		    		casesSearchVO2=new CasesSearchVO();
		    		casesSearchVO2=casesSearchService.getUserDtls(casesSearchVO1);
		    		if(casesSearchVO2!=null)
		    			{
		    				if(casesSearchVO2.getPatientName()!=null)
			    				{
			    					request.setAttribute("userEmpName", casesSearchVO2.getPatientName() );
			    				}
		    			}	
		    		return mapping.findForward("csvDownloads");
		    	}
		    
	        if(lStrFlagStatus!= null && "OnloadCaseOpen".equals(lStrFlagStatus)) 
	        {
	        	String caseStartTime = sds.format(new Date().getTime());
	        	
	        		int dop=0,nonDop=0;
	        		String caseIdchk=null;
	        		String patientScheme=null;
	        		String caseIdNew = null;
	        		patientScheme=request.getParameter("patientScheme");
	        		String caseStatus=null,csSurgDt=null,clinicalTab="show";
	        	    String autoCaseId = request.getParameter("autoCaseId");
	        	    if(autoCaseId==null || autoCaseId.equalsIgnoreCase(""))
	        	    	autoCaseId = "0";
	        	    ClaimCases.releaseCase(autoCaseId);
		        	String searchType= request.getParameter("caseSearchType");	        	
		        	String caseId = request.getParameter("CaseId");	
		 	        String flag = request.getParameter("flag");
					    String medFlag=request.getParameter("medFlag");
		 	        String medFlg = request.getParameter("medFlg");
		 	        String module = request.getParameter("module");
		 	        String caseForDissFlg = request.getParameter("disSearchType");
		 	        String casesForApprovalFlag = request.getParameter("casesForApproval");
		 	        String errCaseApprovalFlag = request.getParameter("errSearchType");
		 	        String lStrSchemeType = request.getParameter("schemeType");
		 	        String dentalFlag=request.getParameter("dentalFlag");
		 	       
		 	       if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
		 	    	  casesForApprovalFlag = casesSearchForm.getCasesForApprovalFlag();
		 	        if(casesForApprovalFlag == null || casesForApprovalFlag .equals(""))
		 	        	casesForApprovalFlag="N";
		 	        if(errCaseApprovalFlag==null || errCaseApprovalFlag.equalsIgnoreCase(""))
		 	        	errCaseApprovalFlag="N";
		 	        if(caseForDissFlg==null || caseForDissFlg.equalsIgnoreCase(""))
		 	        	caseForDissFlg="N";
		 	        if(caseId != null && !caseId.equals(""))
		 	          casesSearchService.setviewFlag(caseId,flag,lStrEmpId);
		 	        if(lStrSchemeType == null || lStrSchemeType.equalsIgnoreCase(""))
		 	        	lStrSchemeType = lStrUserState;
		 	        /**
		 	         * end 
		 	         */
		        	CasesSearchVO casesSearchVO = new CasesSearchVO();
		        	casesSearchVO.setCaseSearchType(searchType);
		        	casesSearchVO.setUserId(lStrEmpId);		        	
		        	casesSearchVO.setGrpList(groupsList);
		        	casesSearchVO.setCasesForApprovalFlag(casesForApprovalFlag);
		        	casesSearchVO.setErrCaseApprovalFlag(errCaseApprovalFlag);
		        	casesSearchVO.setCaseForDissFlg(caseForDissFlg);
		        	casesSearchVO.setModule(module);
		        	casesSearchVO.setSchemeVal(lStrSchemeType);
		        	casesSearchVO.setPatientScheme(patientScheme);
		        	casesSearchVO.setDentalFlag(dentalFlag);
		        	casesSearchVO.setMedFlg(medFlg);
					casesSearchVO.setOrganFlagNew(medFlag);
		        	casesSearchForm.setCasesForApprovalFlag(casesForApprovalFlag);
		        	
		        	

		        	 /*for(LabelBean labelBean:groupsList)
		     	    {
		     	   if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) || labelBean.getID().equalsIgnoreCase(config.getString("preauth_mithra_role")) ))
		     	     {
		     		    request.setAttribute("is_medco_mithra", "Y");
		     			lStrUserGroup = labelBean.getID() ;
		     			break;
		     	     }
		     	     else{
		     		lStrUserGroup = null;
		     		casesSearchVO.setRoleId(labelBean.getID());
		     	     }
		     	     }*/
		        	//casesSearchVO = casesSearchService.getUserRole(casesSearchVO);
		        	String medcoUpdatedStatus = config.getString("preauth_medco_updated");
		        	String timeOutCount = "0";
		        	casesSearchVO.setUserRole(lStrUserGroup);
		        	String checkForCase = ClaimCases.getCaseForUserId(lStrEmpId,module);
		        	casesSearchVO.setOldCaseId(checkForCase);
		        	if(checkForCase!=null && checkForCase.equalsIgnoreCase("false")){
		        	CasesSearchVO  lstCasesSearchVO  = casesSearchService.getListCasesDirect(casesSearchVO);
		        	if(lstCasesSearchVO.getLstCaseSearch().size()>0){
		        	for (CasesSearchVO caseSearchVO1 : lstCasesSearchVO.getLstCaseSearch()){
		        	 caseIdNew=caseSearchVO1.getCaseId();
//		        	 System.out.println("caseIdNew : "+caseIdNew);
		        	}
		        	}
		        	if(lstCasesSearchVO.getLstCaseSearch().size()>0){
		        	for (CasesSearchVO caseSearchVO : lstCasesSearchVO.getLstCaseSearch()){
		        		String caseId1 = caseSearchVO.getCaseId();
		        		String caseScheme=caseSearchVO.getSchemeId();
//		        		System.out.println("caseSchme is "+caseScheme);
		        		boolean status = ClaimCases.isAvailable(caseId1,lStrEmpId,module);
		        		if (status) {
		        			timeOutCount = "0";		        			
		        			if(!medcoUpdatedStatus.contains(caseSearchVO.getCaseStatusId()))
		        			timeOutCount = casesSearchService.getTimeOutCount(caseId1,groupsList,module);
		        			 
		        			
		        			String onlineCaseSheetFlag= casesApprovalService.getOnlinecaseSheetFlag(caseId1);
		    				request.setAttribute("onlineCaseSheetFlag", onlineCaseSheetFlag);
		        			
		    				caseIdchk=caseId1;
		        			request.setAttribute("CaseId", caseId1);
		        			request.setAttribute("timeOutCount", timeOutCount);
		        			request.setAttribute("caseScheme",caseScheme);
		        			
                            break;
                        }		        		
		        	}
		        	}else
		        		return mapping.findForward("caseNotFound");
		        	}
		        	else{
		        	    timeOutCount = "0";		        		
		        	    CasesSearchVO  lstCasesSearchVO  = casesSearchService.getListCasesDirect(casesSearchVO);
		        	    if(lstCasesSearchVO != null && lstCasesSearchVO.getLstCaseSearch() != null && lstCasesSearchVO.getLstCaseSearch().size() >0
	        	    		&& lstCasesSearchVO.getLstCaseSearch().get(0).getCaseId() !=null && !"".equalsIgnoreCase(lstCasesSearchVO.getLstCaseSearch().get(0).getCaseId()))
		        	    {
		        		if(!medcoUpdatedStatus.contains(casesSearchService.getCaseStatusForCase(checkForCase)))
		        		timeOutCount = casesSearchService.getTimeOutCount(checkForCase,groupsList,module);
		        		
		        		request.setAttribute("timeOutCount", timeOutCount);
		        		request.setAttribute("CaseId", checkForCase);
		        		caseIdchk=checkForCase;
		        		String onlineCaseSheetFlag= casesApprovalService.getOnlinecaseSheetFlag(checkForCase);
	    				request.setAttribute("onlineCaseSheetFlag", onlineCaseSheetFlag);
		        	    }
		        	    else
		        	    {
		        	    	casesSearchVO.setOldCaseId("");
		        	    	  lstCasesSearchVO  = casesSearchService.getListCasesDirect(casesSearchVO);
				        	if(lstCasesSearchVO.getLstCaseSearch().size()>0){
				        	for (CasesSearchVO caseSearchVO : lstCasesSearchVO.getLstCaseSearch()){
				        		String caseId1 = caseSearchVO.getCaseId();
				        		String caseScheme=caseSearchVO.getSchemeId();
//				        		System.out.println("caseSchme is "+caseScheme);
				        		boolean status = ClaimCases.isAvailable(caseId1,lStrEmpId,module);
				        		if (status) {
				        			timeOutCount = "0";		        			
				        			if(!medcoUpdatedStatus.contains(caseSearchVO.getCaseStatusId()))
				        			timeOutCount = casesSearchService.getTimeOutCount(caseId1,groupsList,module);
				        			 
				        			
				        			String onlineCaseSheetFlag= casesApprovalService.getOnlinecaseSheetFlag(caseId1);
				    				request.setAttribute("onlineCaseSheetFlag", onlineCaseSheetFlag);
				        			
				    				caseIdchk=caseId1;
				        			request.setAttribute("CaseId", caseId1);
				        			request.setAttribute("timeOutCount", timeOutCount);
				        			request.setAttribute("caseScheme",caseScheme);
				        			
		                            break;
		                        }		        		
				        	}
				        	}else
				        		return mapping.findForward("caseNotFound");
		        	    }
		        	}
		        
		        	request.setAttribute("casesForApprovalFlag", casesForApprovalFlag);
		        	request.setAttribute("autoActionFlag", "OnloadCaseOpen");
		        	request.setAttribute("module",module);
		        	
		        	
		        	List<LabelBean> rolesList=null;
					if ( ( session.getAttribute ( "groupList" ) != null ) && !( session.getAttribute ( "groupList" ).equals ( "" ) ) )
				    	rolesList = (List<LabelBean>) session.getAttribute ( "groupList" ) ;
		        	
		        	FlaggingVO fVO=new FlaggingVO();
					fVO=flaggingService.checkAuthority(rolesList);
					if(fVO!=null)
					{
						String authority=fVO.getAuthority();
							if("Y".equalsIgnoreCase(authority))
								{
									FlaggingVO fVO1=new FlaggingVO();
									fVO1=flaggingService.getFlaggedCasesForColor(caseIdchk);
									if(fVO1!=null)
										{
											request.setAttribute("flagColour",fVO1.getFlagColour());
//											System.out.println(fVO1.getFlagColour());
										}
								}
					}
					fVO=new FlaggingVO();
					fVO=flaggingService.checkCaseFlagged(caseIdchk);
					if(fVO!=null)
					{
						if(fVO.getFlagged()!=null)
							request.setAttribute("flagged",fVO.getFlagged());
						else
							request.setAttribute("flagged",null);
					}
					
					String employeeNo=null;
					List<CasesSearchVO> list=casesSearchService.getDopDtls(caseIdchk);
					if(list!=null)
					{
						if(list.size()>0)
							{
								employeeNo=list.get(0).getPATIPOP();
								for(CasesSearchVO casesSearchVODOP:list)
									{
										if(casesSearchVODOP.getProcess()!=null)
											{
												if(casesSearchVODOP.getProcess().equalsIgnoreCase("DOP"))
													{
														dop=dop+1;
													}
												else
													{
														if(casesSearchVODOP.getActiveyn()!=null)
															{
																if(casesSearchVODOP.getActiveyn().equalsIgnoreCase("Y"))
																	nonDop=nonDop+1;
															}
													}
											}
									}
								if(list.get(0).getCaseStatus()!=null)
									caseStatus=list.get(0).getCaseStatus();
								if(list.get(0).getCsSurgDt()!=null)
									csSurgDt=list.get(0).getCsSurgDt();
							}
					}
				//	int count=0;
					CasesSearchVO casesSearchVO2 = new CasesSearchVO();	/////////////////////@@@@@@@@@@@@@@@@@
					casesSearchVO2.setLoginName(employeeNo);
					casesSearchVO2.setCaseForDissFlg("Y");
					if(caseId!=null)
						
					{
						casesSearchVO2.setCaseId(caseId);
					}
					else if(caseIdchk!=null)
						casesSearchVO2.setCaseId(caseIdchk);
					else
					{
						casesSearchVO2.setCaseId(caseIdNew);
					}
					List<CasesSearchVO> caselist1= casesApprovalService.getAllCaseSearchDetails(casesSearchVO2);
					if(caselist1.size()>0)
					{
						request.setAttribute("newUser", "N");	
					}
					else
					{
						request.setAttribute("newUser", "Y");
					}
					request.setAttribute("caseStartTime", caseStartTime);
				/*if(nonDop==0 && dop>0)
					{
						clinicalTab="hide";
						if(csSurgDt!=null)
							{
								clinicalTab="show";
							}
						if(caseStatus!=null)
							{
								if((caseStatus.equalsIgnoreCase("CD8")||caseStatus.equalsIgnoreCase("CD207")))
									clinicalTab="show";
							}
					}
				else if(nonDop>0)
					{
						clinicalTab="show";
					}
				
				request.setAttribute("clinicalTab", clinicalTab);*/
	                return mapping.findForward("PreauthCaseDisplayFrame");
	        }
	        if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("issueWorklist"))  
	         {   
		        casesSearchService = (CasesSearchService)ServiceFinder.getContext(request).getBean("casesSearchService");
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
//	            System.out.println(request.getParameter("issueid"));
	            if(request.getParameter("issueid")!=null && request.getParameter("issueid")!=""){
	        	casesSearchVO.setIssueId(Long.parseLong(request.getParameter("issueid")));}      	
	        	casesSearchVO.setIssuecaseId(request.getParameter("caseid"));
	        	casesSearchVO.setCreateddate(casesSearchForm.getCreateddate());
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setFromDate(request.getParameter("fromdate"));
	        	casesSearchVO.setToDate(request.getParameter("todate"));
	        	if(request.getParameter("issueid")!=null||request.getParameter("caseid")!=null||request.getParameter("fromdate")!=null||request.getParameter("todate")!=null)
	        		casesSearchVO.setSearchFlag("Y");	
	        	//casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	if(request.getParameter("rowsperpage")!=null){
	        	casesSearchVO.setRowsPerPage(request.getParameter("rowsperpage"));}
	        	else
	        	{
	        		casesSearchVO.setRowsPerPage(lStrRowsperpage);	
	        	}
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	if(request.getParameter("showpage")!=null){
	        	casesSearchVO.setShowPage(request.getParameter("showpage"));}
	        	else{
	        		casesSearchVO.setShowPage(showPage);
	        	}
	        	CasesSearchVO  lstCasesSearchVO  = casesSearchService.getListIssues(casesSearchVO);
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	casesSearchForm.setIssueDetailsList(lstCasesSearchVO.getLstCaseSearch());
                /**
            	 * pagination code starting
            	 */
               
                casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
                casesSearchForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
                int liTotalRows =0;
                int liRowsPerPage=0;
                if(lstCasesSearchVO.getTotRowCount()!=null)
                	liTotalRows = Integer.parseInt( lstCasesSearchVO.getTotRowCount());
                if(request.getParameter("rowsperpage")!=null){
            	 liRowsPerPage =Integer.parseInt( request.getParameter("rowsperpage"));}
                else{
                	liRowsPerPage =Integer.parseInt(casesSearchForm.getRowsPerPage() );
                }
            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
                     { 
                       liTotalPages=liTotalRows/liRowsPerPage;
                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
                          liTotalPages++;
                       }
           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
                 {
                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
                    {
                        liStartPage=liPageNo;
                        liEndPage=liPageNo+9;
                    }
                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
                    {
                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
                          liTemp=liPageNo-9;                  
                        else
                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
                        liStartPage=liTemp;
                        liEndPage=liTemp+9;                
                    }
                 } 
           	  if(liEndPage > liTotalPages)
                     liEndPage=liTotalPages;
                 request.setAttribute("liEndPage", liEndPage);
                 request.setAttribute("liTotalPages", liTotalPages);
                 request.setAttribute("liStartPage", liStartPage);
                 request.setAttribute("liPageNo", liPageNo);
                 request.setAttribute("lStrRowsperpage", Integer.parseInt( casesSearchForm.getRowsPerPage()));
                 
                 List<Integer> pages = new ArrayList<Integer>();
                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
                 {
                	for(int i=1 ; i<=liTotalPages ;i++)
                	{
                		pages.add(i);
                	}
                 }
                request.setAttribute("pages", pages);
                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
                {
                if(casesSearchForm.getNext()!= null)
                {
                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
                {
                	casesSearchForm.setNext(null);	
                }
                }
                else
                	 casesSearchForm.setNext(next);
                }
              
                
                /**
                 * pagination code ending
                 */
               
                casesSearchForm.setIssueResultFlag("Y");
                casesSearchForm.setIssueResultFlagSize(lstCasesSearchVO.getTotRowCount());
                return mapping.findForward("search2");
	         }
	        
	        
	        
	        
	        if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("feedbackSearch"))  
	         {   
	        	casesSearchService = (CasesSearchService)ServiceFinder.getContext(request).getBean("casesSearchService");
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
//	            System.out.println(request.getParameter("feedbackId"));
	            if(request.getParameter("feedbackId")!=null && request.getParameter("feedbackId")!="")
	            {
	        	casesSearchVO.setFEEDBACKID((request.getParameter("feedbackId")));
	        	}      	
	        	casesSearchVO.setCreateddate(casesSearchForm.getCreateddate());
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setFromDate(request.getParameter("fromdate"));
	        	casesSearchVO.setToDate(request.getParameter("todate"));
	        	if(request.getParameter("feedbackId")!=null||request.getParameter("fromdate")!=null||request.getParameter("todate")!=null)
	        		casesSearchVO.setSearchFlag("Y");	
	        	//casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	if(request.getParameter("rowsperpage")!=null){
	        	casesSearchVO.setRowsPerPage(request.getParameter("rowsperpage"));}
	        	else
	        	{
	        		casesSearchVO.setRowsPerPage(lStrRowsperpage);	
	        	}
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	if(request.getParameter("showpage")!=null){
	        	casesSearchVO.setShowPage(request.getParameter("showpage"));}
	        	else{
	        		casesSearchVO.setShowPage(showPage);
	        	}
	        	CasesSearchVO  lstCasesSearchVO  = casesSearchService.getFeedbackList(casesSearchVO);
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	casesSearchForm.setIssueDetailsList(lstCasesSearchVO.getLstCaseSearch());
                /**
            	 * pagination code starting
            	 */
               
                casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
                casesSearchForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
                int liTotalRows =0;
                int liRowsPerPage=0;
                if(lstCasesSearchVO.getTotRowCount()!=null)
                	liTotalRows = Integer.parseInt( lstCasesSearchVO.getTotRowCount());
                if(request.getParameter("rowsperpage")!=null){
            	 liRowsPerPage =Integer.parseInt( request.getParameter("rowsperpage"));}
                else{
                	liRowsPerPage =Integer.parseInt(casesSearchForm.getRowsPerPage() );
                }
            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
                     { 
                       liTotalPages=liTotalRows/liRowsPerPage;
                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
                          liTotalPages++;
                       }
           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
                 {
                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
                    {
                        liStartPage=liPageNo;
                        liEndPage=liPageNo+9;
                    }
                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
                    {
                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
                          liTemp=liPageNo-9;                  
                        else
                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
                        liStartPage=liTemp;
                        liEndPage=liTemp+9;                
                    }
                 } 
           	  if(liEndPage > liTotalPages)
                     liEndPage=liTotalPages;
                 request.setAttribute("liEndPage", liEndPage);
                 request.setAttribute("liTotalPages", liTotalPages);
                 request.setAttribute("liStartPage", liStartPage);
                 request.setAttribute("liPageNo", liPageNo);
                 request.setAttribute("lStrRowsperpage", Integer.parseInt( casesSearchForm.getRowsPerPage()));
                 
                 List<Integer> pages = new ArrayList<Integer>();
                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
                 {
                	for(int i=1 ; i<=liTotalPages ;i++)
                	{
                		pages.add(i);
                	}
                 }
                request.setAttribute("pages", pages);
                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
                {
                if(casesSearchForm.getNext()!= null)
                {
                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
                {
                	casesSearchForm.setNext(null);	
                }
                }
                else
                	 casesSearchForm.setNext(next);
                }
              
                
                /**
                 * pagination code ending
                 */
               
                casesSearchForm.setIssueResultFlag("Y");
                casesSearchForm.setIssueResultFlagSize(lstCasesSearchVO.getTotRowCount());
               return mapping.findForward("feedbackSearchpage");
	        }
		      
	        if(lStrFlagStatus!= null && "telephonicIntimationSearch".equals(lStrFlagStatus)) 
	        {
	        	lStrStartIndex=bundle.getString("case.StartIndex0");
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setRowsPerPage(lStrRowsperpage);
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	casesSearchVO.setShowPage(showPage);
	        	casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
	        	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
	        	casesSearchVO.setToDate(casesSearchForm.getToDate());
	        	if(casesSearchForm.getRowsPerPage() != null && !casesSearchForm.getRowsPerPage().equalsIgnoreCase(""))
	        	{
	        		lStrRowsperpage = casesSearchForm.getRowsPerPage();
	        		casesSearchVO.setRowsPerPage(casesSearchForm.getRowsPerPage() );
	        		casesSearchForm.setRowsPerPage( casesSearchForm.getRowsPerPage());
	        	}
	        	if(casesSearchForm.getShowPage() != null && !casesSearchForm.getShowPage().equals(""))
	        	{
	        		casesSearchVO.setShowPage(casesSearchForm.getShowPage());
	        		casesSearchForm.setShowPage(casesSearchForm.getShowPage());
	        		if(Integer.parseInt(casesSearchForm.getShowPage()) >Integer.parseInt(bundle.getString("case.RowsPerPage")) )
	        		{
	        			casesSearchForm.setPrev(casesSearchForm.getShowPage());
	        			casesSearchForm.setNext(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())+1)));	
	        		
	        		}
	        	}
	        	CasesSearchVO  lstCasesSearchVO  = casesSearchService.getTeleIntimationCases(casesSearchVO);
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	casesSearchForm.setLstCaseSearch(lstCasesSearchVO.getLstCaseSearch());
	        	
	        	casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
	            casesSearchForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
	                
	        	int liTotalRows = Integer.parseInt( lstCasesSearchVO.getTotRowCount());
            	int liRowsPerPage =Integer.parseInt( casesSearchForm.getRowsPerPage());
            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
                     { 
                       liTotalPages=liTotalRows/liRowsPerPage;
                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
                          liTotalPages++;
                       }
           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
                 {
                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
                    {
                        liStartPage=liPageNo;
                        liEndPage=liPageNo+9;
                    }
                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
                    {
                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
                          liTemp=liPageNo-9;                  
                        else
                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
                        liStartPage=liTemp;
                        liEndPage=liTemp+9;                
                    }
                 } 
           	  if(liEndPage > liTotalPages)
                     liEndPage=liTotalPages;
                 request.setAttribute("liEndPage", liEndPage);
                 request.setAttribute("liTotalPages", liTotalPages);
                 request.setAttribute("liStartPage", liStartPage);
                 request.setAttribute("liPageNo", liPageNo);
                 request.setAttribute("lStrRowsperpage", lStrRowsperpage);
                 
                 List<Integer> pages = new ArrayList<Integer>();
                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
                 {
                	for(int i=liStartPage ; i<=liEndPage ;i++)
                	{
                		pages.add(i);
                	}
                 }
                request.setAttribute("pages", pages);
                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
                {
                if(casesSearchForm.getNext()!= null)
                {
                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
                {
                	casesSearchForm.setNext(null);	
                }
                }
                else
                	 casesSearchForm.setNext(next);
                }
              
               
                /**
                 * pagination code ending
                 */
                return mapping.findForward("telephonicSearch");
	        }
	        
	       
	        if(lStrFlagStatus!= null && "telephonicIntimationRaised".equals(lStrFlagStatus)) 
	        {
	        	lStrStartIndex=bundle.getString("case.StartIndex0");
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setRowsPerPage(lStrRowsperpage);
	        	casesSearchVO.setStartIndex(lStrStartIndex);
	        	casesSearchVO.setShowPage(showPage);
	        	casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
	        	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
	        	casesSearchVO.setToDate(casesSearchForm.getToDate());
	        	if(casesSearchForm.getRowsPerPage() != null && !casesSearchForm.getRowsPerPage().equalsIgnoreCase(""))
	        	{
	        		lStrRowsperpage = casesSearchForm.getRowsPerPage();
	        		casesSearchVO.setRowsPerPage(casesSearchForm.getRowsPerPage() );
	        		casesSearchForm.setRowsPerPage( casesSearchForm.getRowsPerPage());
	        	}
	        	if(casesSearchForm.getShowPage() != null && !casesSearchForm.getShowPage().equals(""))
	        	{
	        		casesSearchVO.setShowPage(casesSearchForm.getShowPage());
	        		casesSearchForm.setShowPage(casesSearchForm.getShowPage());
	        		if(Integer.parseInt(casesSearchForm.getShowPage()) >Integer.parseInt(bundle.getString("case.RowsPerPage")) )
	        		{
	        			casesSearchForm.setPrev(casesSearchForm.getShowPage());
	        			casesSearchForm.setNext(Integer.toString((Integer.parseInt(casesSearchForm.getShowPage())+1)));	
	        		
	        		}
	        	}
	        	CasesSearchVO  lstCasesSearchVO  = casesSearchService.getRegTeleIntimationCases(casesSearchVO);
	        	casesSearchForm.setStartIndex(lstCasesSearchVO.getStartIndex());
	        	casesSearchForm.setRowsPerPage(casesSearchVO.getRowsPerPage());
	        	casesSearchForm.setLstCaseSearch(lstCasesSearchVO.getLstCaseSearch());
	        	
	        	casesSearchForm.setEndIndex(Integer.toString((Integer.parseInt(casesSearchForm.getStartIndex())+lstCasesSearchVO.getLstCaseSearch().size())));
	            casesSearchForm.setTotalRows(lstCasesSearchVO.getTotRowCount());
	                
	        	int liTotalRows = Integer.parseInt( lstCasesSearchVO.getTotRowCount());
            	int liRowsPerPage =Integer.parseInt( casesSearchForm.getRowsPerPage());
            	int liStartIndex = Integer.parseInt( casesSearchForm.getStartIndex());
                int liPageNo=Integer.parseInt(casesSearchVO.getShowPage());
                int liStartPage=Integer.parseInt(bundle.getString("case.StartIndex1"));
                int liEndPage=Integer.parseInt(bundle.getString("case.RowsPerPage"));
                int liTotalPages=Integer.parseInt(bundle.getString("case.StartIndex0"));
           	    if((liTotalRows != Integer.parseInt(bundle.getString("case.StartIndex0"))) && (liRowsPerPage!=Integer.parseInt(bundle.getString("case.StartIndex0"))))
                     { 
                       liTotalPages=liTotalRows/liRowsPerPage;
                       if((liTotalRows%liRowsPerPage)>Integer.parseInt(bundle.getString("case.StartIndex0"))) 
                          liTotalPages++;
                       }
           	  liPageNo=(liStartIndex/liRowsPerPage)+Integer.parseInt(bundle.getString("case.StartIndex1"));
           	  if(liPageNo > Integer.parseInt(bundle.getString("case.StartIndex1")) && liPageNo <=liTotalPages)
                 {
                    if(liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"))==1)
                    {
                        liStartPage=liPageNo;
                        liEndPage=liPageNo+9;
                    }
                    else if(liPageNo >Integer.parseInt(bundle.getString("case.RowsPerPage")))
                    {
                        int liTemp=Integer.parseInt(bundle.getString("case.StartIndex0"));
                        liTemp=liPageNo%Integer.parseInt(bundle.getString("case.RowsPerPage"));
                        if(liTemp == Integer.parseInt(bundle.getString("case.StartIndex0")))
                          liTemp=liPageNo-9;                  
                        else
                          liTemp=(liPageNo-liTemp)+Integer.parseInt(bundle.getString("case.StartIndex1"));
                        liStartPage=liTemp;
                        liEndPage=liTemp+9;                
                    }
                 } 
           	  if(liEndPage > liTotalPages)
                     liEndPage=liTotalPages;
                 request.setAttribute("liEndPage", liEndPage);
                 request.setAttribute("liTotalPages", liTotalPages);
                 request.setAttribute("liStartPage", liStartPage);
                 request.setAttribute("liPageNo", liPageNo);
                 request.setAttribute("lStrRowsperpage", lStrRowsperpage);
                 
                 List<Integer> pages = new ArrayList<Integer>();
                 if(liTotalPages >Integer.parseInt(bundle.getString("case.StartIndex0")))
                 {
                	for(int i=liStartPage ; i<=liEndPage ;i++)
                	{
                		pages.add(i);
                	}
                 }
                request.setAttribute("pages", pages);
                if(pages.size() > Integer.parseInt(bundle.getString("case.RowsPerPage")))
                {
                if(casesSearchForm.getNext()!= null)
                {
                if(!(Integer.parseInt(casesSearchForm.getNext()) <= pages.size()))
                {
                	casesSearchForm.setNext(null);	
                }
                }
                else
                	 casesSearchForm.setNext(next);
                }
              
               
                /**
                 * pagination code ending
                 */
                return mapping.findForward("telephonicIntimationReg");
	        }
	        /**
	         * set view flag for case UNBLOCK_CASES proc
	         */
	        if(lStrFlagStatus!= null && "setViewFlag".equals(lStrFlagStatus)) 
	        {
		        String caseId = request.getParameter("CaseId");	
		        String flag = request.getParameter("flag");
		        String msg = casesSearchService.setviewFlag(caseId,flag,lStrEmpId);
		        return mapping.findForward("ajaxResult");
	        }
	        
	        
	        

		    
	        
	        
	        
	        /**
	         * view Death Cases
	         */
	        if(lStrFlagStatus!= null && "viewDeathCases".equals(lStrFlagStatus)) 
	        {
	        	int start_index=0;
	        	int end_index=0;
	        	CasesSearchVO casesSearchVO = new CasesSearchVO();
		        String lStrSchemeType="";
	        	String searchType= request.getParameter("caseSearchType");	
	        	if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON"))){
					casesSearchForm.setShowScheme("show");
					lStrSchemeType = request.getParameter("schemeType");
		        }
				else {
					casesSearchForm.setShowScheme("hide");
					lStrSchemeType= lStrUserState;
				}
	        	
	        	for(LabelBean labelBean:groupsList)
	     	    {
	        		if(labelBean.getID() != null && (labelBean.getID().equalsIgnoreCase(config.getString("preauth_medco_role")) || labelBean.getID().equalsIgnoreCase(config.getString("preauth_mithra_role")) ))
	        		{
		     		    request.setAttribute("is_medco_mithra", "Y");
		     			lStrUserGroup = labelBean.getID() ;
		     			break;
	        		}
		     	    else
		     	    {
			     		lStrUserGroup = null;
			     	}
	     	     }
	        	casesSearchVO.setUserRole(lStrUserGroup);
	        	casesSearchVO.setUserId(lStrEmpId);
	        	casesSearchVO.setClaimStatus(casesSearchForm.getClaimId());
	        	casesSearchVO.setWapNo(casesSearchForm.getWapNo());
	        	casesSearchVO.setCaseNo(casesSearchForm.getCaseNo());
	        	casesSearchVO.setClaimNo(casesSearchForm.getClaimNo());
	        	casesSearchVO.setPatName(casesSearchForm.getPatName());
	        	casesSearchVO.setFromDate(casesSearchForm.getFromDate());
	        	casesSearchVO.setToDate(casesSearchForm.getToDate());
	        	casesSearchVO.setSearchFlag(request.getParameter("searchFlag"));
	        	casesSearchVO.setSchemeVal(lStrSchemeType);
				casesSearchVO.setStart_index(start_index);
		        casesSearchVO.setEnd_index(end_index); 
	        	
	        	List<CasesSearchVO> ListNoOfRecords=casesSearchService.getDeathCases(casesSearchVO);
	        	int pageId;
	        	if(request.getParameter("pageId")!=null&&!"".equalsIgnoreCase(request.getParameter("pageId")))
	        		{
	        			pageId=Integer.parseInt(request.getParameter("pageId"));
	        			end_index=Integer.parseInt(request.getParameter("end_index"));
	        			start_index=end_index*(pageId-1);
	        		}
	        	else
	        		{
	        			pageId=1;
	        			start_index=0;
	        			end_index=10;
	        		}

	        	int noOfRecords=ListNoOfRecords.size();
	        	int division;
	        	int noOfPages=0;
	        	division=(noOfRecords%end_index);
				if(division==0)
        			noOfPages=(noOfRecords/end_index);
				if(division!=0)
    				noOfPages=(noOfRecords/end_index)+1;
	        	if(lStrUserState!=null && lStrUserState.equalsIgnoreCase(config.getString("COMMON")))
				{
					casesSearchForm.setShowScheme("show");
					casesSearchForm.setSchemeType(lStrSchemeType);
				}
				else
				{casesSearchForm.setShowScheme("hide");	}
				
	        	
	        	String csvFlag=request.getParameter("csvFlag");
	        	 List<CasesSearchVO> casesSearchVOCsvLst=null;
				if(csvFlag!=null && "Y".equalsIgnoreCase(csvFlag))
					{
						casesSearchVO.setStart_index(0);
						casesSearchVO.setEnd_index(0); 
						casesSearchVOCsvLst=casesSearchService.getDeathCases(casesSearchVO);
						String lStrDirPath = config.getString("CASESSEARCH.MapPath");
						String lStrFileName = config.getString("CASESSEARCH.Record")+config.getString("DOT_VALUE")+ "CSV";
						File lcsvfile = createFile(lStrDirPath,lStrFileName);
						char separator = ',';
				        StringBuilder line = new StringBuilder();  
						if(casesSearchVOCsvLst.size()>0)
							{
					            line.append("Case Number");
							    line.append(separator);
								line.append("Claim Number");
								line.append(separator);
								line.append("Patient Name");
								line.append(separator);
								line.append("Card Number");
								line.append(separator);
								line.append("Claim Status");
								line.append(separator);
								line.append("Hospital Name");
								line.append(separator);
								line.append("Date of Registration");
								line.append(separator);
								line.append("Death Date");
								line.append(separator);
								
								line.append("\n");
				                line.append("\n");
								
								for(CasesSearchVO casesSearchVOCsv : casesSearchVOCsvLst)
									{
									    line.append(casesSearchVOCsv.getCaseNo());
									    line.append(separator);
										line.append(casesSearchVOCsv.getClaimNo());
										line.append(separator);
										line.append(casesSearchVOCsv.getPatientName());
										line.append(separator);
										line.append(casesSearchVOCsv.getWapNo());
										line.append(separator);
										line.append(casesSearchVOCsv.getClaimStatus());
										line.append(separator);
										line.append(casesSearchVOCsv.getHospName());
										line.append(separator);
										line.append(casesSearchVOCsv.getCaseBlockedUsr());
										line.append(separator);
										line.append(casesSearchVOCsv.getCaseForDissFlg());
										line.append(separator);
										line.append("\n");
									}
							}
						request.setAttribute("File", line.toString().getBytes());    
					    response.setContentType(config.getString("REPORT.CaseSearchCvsContentType"));
					    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
					    response.setCharacterEncoding("UTF-8");
				        return mapping.findForward("openFile");
					}
	        	
	        	
				casesSearchVO.setStart_index(start_index);
		        casesSearchVO.setEnd_index(end_index); 
	        	request.setAttribute("StatusList", casesSearchService.getCaseStatus(null));
        	    request.setAttribute("deathCasesList",casesSearchService.getDeathCases(casesSearchVO));
        	    request.setAttribute("pageId",pageId);
        	    request.setAttribute("noOfRecords",noOfRecords);
        	    request.setAttribute("lastPage",noOfPages);
        	    request.setAttribute("start_index",start_index);
        	    request.setAttribute("end_index",end_index);
        	    request.setAttribute("endresults",start_index+casesSearchService.getDeathCases(casesSearchVO).size());
//        	    System.out.println(casesSearchService.getDeathCases(casesSearchVO).size());
        	    return mapping.findForward("deathCasesView");
	        }
	        return null;   
	    }
	 
	 
	 
	 /**
		 * Creates the file.
		 *
		 * @param lStrDirPath the l str dir path
		 * @param lStrFileName the l str file name
		 * @return the file
		 * @throws IOException Signals that an I/O exception has occurred.
		 */
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
}
