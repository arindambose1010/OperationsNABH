package com.ahct.login.controller;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ahct.common.util.HtmlEncode;
import com.ahct.common.vo.LabelBean;
import com.ahct.login.form.EmployeeForm;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;
import com.ahct.patient.service.PatientService;
import com.google.gson.Gson;

@SuppressWarnings("unused")
public class EmployeeHistoryAction extends Action 
{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
    {
		response.setHeader("Pragma", "");
		response.setHeader("Cache-Control", "private");
		response.setDateHeader("Expires", Long.MAX_VALUE);
		HttpSession session=request.getSession(false);		
		EmployeeForm empHistoryForm = (EmployeeForm)form;
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		//Session timeout
				String lStrResultPage = HtmlEncode.verifySession ( request, response ) ;
				 if ( lStrResultPage.length () > 0 ){
					 
			     return mapping.findForward ( "sessionExpire" ) ;
				 }
				 
				 String lstrFrwdname="";
		// bean wiring. it will help to insert details and fetch the data
		
				
		
		String lStrFlagStatus = request.getParameter("actionFlag");		
		String userId = (String) session.getAttribute("UserID");
		if(lStrFlagStatus!= null && "emp1History".equals(lStrFlagStatus)) 
        {
			lstrFrwdname="EmpHistoryPage";	
        }
		if(lStrFlagStatus!= null && "getEmpDtls".equals(lStrFlagStatus)) 
        {
			String empId= empHistoryForm.getStudentId();
			List<LabelBean> grpList=null;
			grpList =patientService.getEmpDtls(empId);
			if(grpList.size()>0)
			{
				if(grpList != null)
				{
					empHistoryForm.setEmpList(grpList);
					request.setAttribute("norec", "N");
				}
			}
			else
			{
				request.setAttribute("norec", "Y");
			}
			lstrFrwdname="EmpHistoryPage";	
        }
		if(lStrFlagStatus!= null && "getFedBackDtls".equals(lStrFlagStatus)) 
        {
			String enrollId= request.getParameter("enrollId");
			if(!"".equalsIgnoreCase(enrollId) && enrollId != null)
			{
				empHistoryForm.setEnrollId(enrollId);
				request.setAttribute("enrollId", enrollId);
			}
			List<LabelBean> grpList=null;
			grpList =patientService.getFedBackDtls(enrollId);
			if(grpList.size()>0)
			{
					Gson gson = new Gson();
					empHistoryForm.setEmpList(grpList);
					request.setAttribute("norec", "N");
					HashMap<String,Object> lParamMap = new HashMap<String,Object>();
					lParamMap.put("grpList",grpList);
					String json = gson.toJson(lParamMap);
					request.setAttribute("AjaxMessage", json);
			}
			else
			{
				request.setAttribute("norec", "Y");
			}
			return mapping.findForward("ajaxResult");
        }
		if(lStrFlagStatus!= null && "getEmpFullDtls".equals(lStrFlagStatus)) 
        {
			String ehfCardNo= request.getParameter("cardNo").toUpperCase();
			List<LabelBean> grpList=null,feedBackData=null;
			grpList =patientService.getEmpDtls1(ehfCardNo);
			
			if(grpList.size()>0)
			{
				Gson gson = new Gson();
				HashMap<String,Object> lParamMap = new HashMap<String,Object>();
				if(!"".equalsIgnoreCase(grpList.get(0).getUNITID()) && grpList.get(0).getUNITID() != null)
					empHistoryForm.setEnrollId(grpList.get(0).getUNITID());
				lParamMap.put("grpList",grpList);
				String json = gson.toJson(lParamMap);
				request.setAttribute("empDtls", "Y");
				request.setAttribute("AjaxMessage", json);
			}
			
			
			
			return mapping.findForward("ajaxResult");
        }
		if(lStrFlagStatus!= null && "EhfMedicalHistory".equals(lStrFlagStatus)) 
		{
			String enrollId= request.getParameter("enrollId");
			if(!"".equalsIgnoreCase(enrollId) && enrollId != null)
				empHistoryForm.setEnrollId(enrollId);
			String ENROLLPRNTID= request.getParameter("ENROLLPRNTID");
			LabelBean labelBeanVo = new LabelBean();
			String lstrSeq = patientService.getSequence("EHF_FEEDBACK_SEQ");
			labelBeanVo.setEnrollId(empHistoryForm.getEnrollId());
			labelBeanVo.setENROLLPRNTID(empHistoryForm.getENROLLPRNTID());
			labelBeanVo.setPreexistDiseases(empHistoryForm.getPreexistDiseases());
			labelBeanVo.setDrugscurrent(empHistoryForm.getDrugscurrent());
			labelBeanVo.setKnowndrugaller(empHistoryForm.getKnownDrugaller());
			labelBeanVo.setMdclhtry(empHistoryForm.getMdclhtry());
			labelBeanVo.setAnycongenital(empHistoryForm.getAnycongenital());
			labelBeanVo.setAnysurgerdne(empHistoryForm.getAnysurgerdne());
			labelBeanVo.setNoSurgery(empHistoryForm.getNoSurgery());
			labelBeanVo.setdOs(empHistoryForm.getdOs());
			labelBeanVo.setCrtUsr(userId);
			labelBeanVo.setLstUpdUsr(userId);
			labelBeanVo.setSEQID(lstrSeq);
			LabelBean msg1=patientService.saveEmployeeFeedbackData(labelBeanVo);
			if(msg1.getMsg().equalsIgnoreCase("Success")){
				empHistoryForm.setMsg("Employee Feedback-Form Submitted Successfully");
				request.setAttribute("msg", "Employee Feedback-Form Submitted Successfully");
			}
            lstrFrwdname="EmpHistoryPage";
        }
		return mapping.findForward(lstrFrwdname);
    }
}
