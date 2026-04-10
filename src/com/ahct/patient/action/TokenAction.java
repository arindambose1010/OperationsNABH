package com.ahct.patient.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.common.vo.LabelBean;
import com.ahct.patient.form.PatientForm;
import com.ahct.patient.service.PatientService;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;

public class TokenAction  extends Action{
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientService patientService =(PatientService)ServiceFinder.getContext(request).getBean("patientService");
		String lStrActionVal = request.getParameter("actionFlag");
		String lStrPageName = null; 
		PatientForm patientForm=(PatientForm)form;
		if ( lStrActionVal != null && lStrActionVal.equalsIgnoreCase ( "showTokenNumbers" ) )
		{
			

			String hospId=null;
			List<LabelBean> roomTokens=null;
			String hospName="";
			String dispName="",contactNo="";
			try{
				
				hospId=request.getParameter("hospId");
				hospName=patientService.getDispensaryName(hospId);
				String[] dispDetArr=hospName.split("~");
				dispName=dispDetArr[0];
				contactNo=dispDetArr[1];
				request.setAttribute("dispName", dispName);
				request.setAttribute("contactNo", contactNo);
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
		return mapping.findForward(lStrPageName);
	}

}
