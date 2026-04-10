
package com.ahct.createEmployee.action;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ahct.common.service.CommonService;
import com.ahct.common.util.LdapAuthentication;
import com.ahct.common.util.SMSServices;
import com.ahct.common.vo.LabelBean;
import com.ahct.createEmployee.VO.CreateEmployeeVO;
import com.ahct.createEmployee.VO.EmployeeExcelVO;
import com.ahct.createEmployee.form.CreateEmployeeForm;
import com.ahct.createEmployee.service.CreateEmpService;
import com.ahct.login.vo.LoginCreationVO;
import com.tcs.framework.common.util.HtmlEncode;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.emailConfiguration.EmailConstants;
import com.tcs.framework.emailConfiguration.EmailVO;
import com.tcs.framework.persistanceConfiguration.ServiceFinder;



public class CreateEmployeeAction extends Action {
	@SuppressWarnings({ "unchecked" })
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpSession session = request.getSession();
	
		 ConfigurationService configurationService = 
	           (ConfigurationService)ServiceFinder.getContext(request).getBean("configurationService");
		Configuration config = configurationService.getConfiguration();
		CommonService commonService =(CommonService)ServiceFinder.getContext(request).getBean("commonService");
			CreateEmpService createEmployeeService = (CreateEmpService) ServiceFinder
					.getContext(request).getBean("createEmployeeService");
		CreateEmployeeForm createEmployeeForm=(CreateEmployeeForm) form;
		String lStrFlagStatus = request.getParameter("actionFlag");
		String  mapPath = "/storageNAS-Production/";
		String disabled="true";
		String butDisabled="disabled";
		String lStrNewPswd = RandomStringUtils.randomAlphanumeric(8);
		String lStrUserId = session.getAttribute("EmpID").toString();
		String fromDisp = request.getParameter("fromDisp");
		 String lStrScheme="";
		try
         {
		  lStrScheme = session.getAttribute("userState").toString();
         }
		catch ( Exception e )
         {
           e.getMessage () ;
           e.printStackTrace();
         }
		String langId = session.getAttribute("LangID").toString();
		String locId = session.getAttribute("LocId").toString();
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		//String lStrUserId="743748";
		//String langId ="en_US";
		//String locId ="LC1";
		String lStrResultPage= HtmlEncode.verifySession(request, response);
	    if (lStrResultPage.length() > 0){
	        return mapping.findForward("sessionExpire");
	    }
		
	    lStrResultPage = "";
		
	    /*if(lStrScheme.equalsIgnoreCase("CD203"))
		{
			createEmployeeForm.setSchemeFlag("show");
		}*/
	    
		//Common details retrieved globally
		List<CreateEmployeeVO> empList=null;
		List<LabelBean> deptList=createEmployeeService.getDeptDetails();
		
		//List<LabelBean> desgList = createEmployeeService.getDesgDetails();
		//List<LabelBean> grpList = createEmployeeService.getGrpList();
		List<LabelBean> desgList = new ArrayList<LabelBean>();
		List<LabelBean> grpList = new ArrayList<LabelBean>();
		List<LabelBean> weekList=createEmployeeService.getWeekList();
		List<LabelBean> shiftList=createEmployeeService.getShiftList();
		List<LabelBean> levelList=createEmployeeService.getLevelList(); 
		List<LabelBean> repPersonList = new ArrayList<LabelBean>();
		List<String> lStrgrpList=new ArrayList<String>();
		String grpId=null;
		String roleId=null;
		request.setAttribute("repPersonList", repPersonList);
		if(session.getAttribute("groupList").toString()!=null)
		{
			grpList=(List<LabelBean>)session.getAttribute("groupList");
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
		else if(lStrgrpList.contains(config.getString("GROUP.HR")))
		{
		roleId=config.getString("GROUP.HR");
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


		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "getAllocDetails" ))
		{
		  String detailsReceived=request.getParameter("selectedRadio");
		  String[] details=detailsReceived.split("~");
			String loginName=details[1];
			List<LabelBean> allocatedDeptsList= createEmployeeService.getAllocatedDeptsList(loginName);
			request.setAttribute("allocatedDeptsList",allocatedDeptsList);
			List<String> allocDeptsAjax = new ArrayList<String>();
			for (LabelBean labelBean : allocatedDeptsList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					if (allocDeptsAjax != null && allocDeptsAjax.size() > 0) {
						allocDeptsAjax.add("@" + labelBean.getID() + "~"
								+ labelBean.getVALUE());
					} else {
						allocDeptsAjax.add(labelBean.getID() + "~"
								+ labelBean.getVALUE());
					}
			}
			request.setAttribute("AjaxMessage", allocDeptsAjax);
			return mapping.findForward("ajaxResult");
				
		}




		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "checkLoginName" ) ) 
        {
			String loginName=(String) request.getParameter("loginName");
			String flag=createEmployeeService.checkLoginName(loginName);
			request.setAttribute("AjaxMessage", flag);
			
			return mapping.findForward("ajaxResult");
        }
		
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "checkUserNo" ) ) 
        {
			String userNo=(String) request.getParameter("userNo");
			String flag=createEmployeeService.checkEmpNo(userNo.toUpperCase(),null);
			request.setAttribute("AjaxMessage", flag);
			return mapping.findForward("ajaxResult");
        }
		
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "checkEmailId" ) ) 
        {
			String emailId=(String) request.getParameter("emailId");
			String flag=createEmployeeService.checkEmailId(emailId);
			request.setAttribute("AjaxMessage", flag);
			return mapping.findForward("ajaxResult");
        }
		String editFlag="";

		 //sathish	 
			if (lStrFlagStatus != null && lStrFlagStatus.equalsIgnoreCase("saveCreatePVTEmp")) {
				CreateEmployeeVO createEmpVo = new CreateEmployeeVO();
				 editFlag=(String)request.getParameter("editFlag");
				String userLoginId=createEmployeeForm.getLoginName();
				String userNo=createEmployeeForm.getEmpNumber();
				String password=lStrNewPswd;
				createEmpVo.setPwd(lStrNewPswd);
				//createEmpVo.setUserNo(userNo.toUpperCase());
				createEmpVo.setEditFlg(editFlag);
				createEmpVo.setLocId(locId);
				createEmpVo.setLoginName(userLoginId);
				createEmpVo.setfName(createEmployeeForm.getfName());
				createEmpVo.setlName(createEmployeeForm.getlName());
				if(createEmployeeForm.getEmpDoj().equals("")||createEmployeeForm.getEmpDoj()=="")
					createEmpVo.setDoj(null);
				else
					createEmpVo.setDoj(sdf.parse(createEmployeeForm.getEmpDoj()));
				createEmpVo.setEmail(createEmployeeForm.getEmail());
				createEmpVo.setLangId(langId);
				createEmpVo.setMobileNo(createEmployeeForm.getMobNumber());
				createEmpVo.setCrtUsr(lStrUserId);
				createEmpVo.setAddr(createEmployeeForm.getEmpAddress());
				createEmpVo.setCountry(createEmployeeForm.getCountry());
				createEmpVo.setDist(createEmployeeForm.getDistrict());
				createEmpVo.setState(createEmployeeForm.getState());
				createEmpVo.setAddr(createEmployeeForm.getEmpAddress());
				createEmpVo.setMandMunci(createEmployeeForm.getDistType());//PRSNT_MAND
				createEmpVo.setAddrMandMunci(createEmployeeForm.getMandals());//PRSNT_MANDALS
				createEmpVo.setCity(createEmployeeForm.getVillages());
				createEmpVo.setPin(createEmployeeForm.getPostalCode());
				if(createEmployeeForm.getEmpDob().equals("")||createEmployeeForm.getEmpDob()=="")
					createEmpVo.setDob(null);
				else
				createEmpVo.setDob(sdf.parse(createEmployeeForm.getEmpDob()));
				createEmpVo.setGender(createEmployeeForm.getGender());
				
			   	 String vacPos=createEmployeeForm.getVacPosName();
				 String dept=createEmployeeForm.getDeptName();
				 createEmpVo.setDesgId(createEmployeeForm.getVacPosName());
				 //LabelBean vacantPosDtls=createEmployeeService.getVacPosDtls(dept,vacPos);
				 createEmpVo.setDeptId(createEmployeeForm.getDeptName());
				createEmpVo.setServiceFlg(createEmployeeForm.getInService());
				createEmpVo.setUserRole(createEmployeeForm.getVacPosName());
				createEmpVo.setLocation("Hyderabad");
				createEmpVo.setAccHolderName(createEmployeeForm.getAccHolderName());
				createEmpVo.setBankName(createEmployeeForm.getBankName());
				createEmpVo.setAccNumber(createEmployeeForm.getAccNumber());
				createEmpVo.setPanNumber(createEmployeeForm.getPanNumber());
				createEmpVo.setPanName(createEmployeeForm.getPanCardName());
				createEmpVo.setIfscCode(createEmployeeForm.getIfscCode());
				
				String result = createEmployeeService.saveCreateEmpDtls(createEmpVo);
				String result1[]= result.split("~");
				String userID="";
				if(result1.length>0)
				{
					result=result1[0];
					userID=result1[1];
					request.setAttribute("success",result);
					request.setAttribute("userID",userID);
				}else{
					request.setAttribute("success","failure");
				}
				
				lStrFlagStatus="employeeCreation";
				
				
			}
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ("employeeCreation"))
		{   
			List<LabelBean> hospitalList = null;
			 fromDisp = request.getParameter("fromDispnsry");
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				 hospitalList=createEmployeeService.getReferredCenterDtls(lStrUserId,roleId);
				session.setAttribute("hospitalList",hospitalList);
			}
			fromDisp = request.getParameter("fromDispnsry");
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				createEmployeeForm.setFromDisp(fromDisp);
				request.setAttribute("fromDisp", fromDisp);
			}	
			else
			{
				createEmployeeForm.setFromDisp("N");
				request.setAttribute("fromDisp", "N");
			}
			List<LabelBean> stateList = createEmployeeService.getStates();
			request.setAttribute("stateList",stateList);
			List<LabelBean> distList = new ArrayList<LabelBean>();
			List<LabelBean> vilList = new ArrayList<LabelBean>();
			List<LabelBean> mdlList = new ArrayList<LabelBean>();
			request.setAttribute("vilList", vilList);
			request.setAttribute("mdlList", mdlList);
			request.setAttribute("distList", distList);
			List<LabelBean> vacNamesList=createEmployeeService.getVacantDtls();
			request.setAttribute("vacNamesList", vacNamesList);
			createEmployeeForm.setInService("Y");			
			if(editFlag!=null )
			{
				lStrResultPage="updateEmployee";
			}
			if(editFlag=="" || editFlag==null)
			lStrResultPage="empCreation";
		}
		
		//To add Employee Designation Details by Rama Lakshmi
		
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ("addDesgDetails"))
		{
			
		    createEmployeeForm.setDesignationId(createEmployeeService.getDesignationSeq());
		    request.setAttribute("editDesgFlag","NO");
			return mapping.findForward("addDesignation");
			
		}
		
		//To save Employee Designation Details
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ("loadDispEmployee"))
		{   
			CreateEmployeeVO resultVo=new CreateEmployeeVO();
			List<LabelBean> hospitalList = null;

			String empId=request.getParameter("empId");
			fromDisp = request.getParameter("fromDispnsry");
			
			if(!"".equalsIgnoreCase(fromDisp) && "Y".equalsIgnoreCase(fromDisp))
			{
				 hospitalList=createEmployeeService.getReferredCenterDtls(lStrUserId,roleId);
				session.setAttribute("hospitalList",hospitalList);
			}
		

			CreateEmployeeVO createEmpVo =createEmployeeService.loadEmpDtls(empId);
			if(createEmpVo!=null)
			{
			if(createEmpVo.getCity()!=null && !"".equalsIgnoreCase(createEmpVo.getCity()))
			{
				LabelBean labelBeanVillage=new LabelBean();
				labelBeanVillage.setID(createEmpVo.getCity());
				LabelBean newStateDtls=createEmployeeService.getNewLocations(labelBeanVillage);
				if(newStateDtls!=null)
				{
					createEmpVo.setDist(newStateDtls.getNEW_DIST());
					createEmpVo.setAddrMandMunci1(newStateDtls.getNEW_MAND());
					createEmpVo.setCity(newStateDtls.getNEW_VILLAGE());
				}
			}
			createEmployeeForm.setEditFlag("true");
			createEmployeeForm.setfName(createEmpVo.getfName());
			createEmployeeForm.setlName(createEmpVo.getlName());
			createEmployeeForm.setEmpNumber(createEmpVo.getUserNo());
			createEmployeeForm.setLoginName(createEmpVo.getLoginName());
			if(createEmpVo.getDoj()!=null)
			createEmployeeForm.setEmpDoj(dateFormat.format(createEmpVo.getDoj()));
			if(createEmpVo.getDob()!=null)
			createEmployeeForm.setEmpDob(dateFormat.format(createEmpVo.getDob()));
			createEmployeeForm.setGender(createEmpVo.getGender());
			createEmployeeForm.setState(createEmpVo.getState());
			createEmployeeForm.setCountry(createEmpVo.getCountry());
			createEmployeeForm.setDistrict(createEmpVo.getDist());
			createEmployeeForm.setEmail(createEmpVo.getEmail());
			createEmployeeForm.setMobNumber(createEmpVo.getMobileNo());
			createEmployeeForm.setEmpAddress(createEmpVo.getAddr());
			createEmployeeForm.setDistType(createEmpVo.getMandMunci());
			createEmployeeForm.setMandals(createEmpVo.getAddrMandMunci());
			createEmployeeForm.setVillages(createEmpVo.getCity());
			createEmployeeForm.setPostalCode(createEmpVo.getPin());
			createEmployeeForm.setInService(createEmpVo.getServiceFlg());
			createEmployeeForm.setDeptName(createEmpVo.getDeptId());
			createEmployeeForm.setVacPosName(createEmpVo.getDesgName());
			 CreateEmployeeVO createEmpVo1 =createEmployeeService.loadEmpBankDtls(empId);
			 if(createEmpVo1!=null)
			 {
			 createEmployeeForm.setAccHolderName(createEmpVo1.getAccHolderName());
			 createEmployeeForm.setBankName(createEmpVo1.getBankName());
			 createEmployeeForm.setAccNumber(createEmpVo1.getAccNumber());
			 createEmployeeForm.setPanNumber(createEmpVo1.getPanNumber());
			 createEmployeeForm.setPanCardName(createEmpVo1.getPanName());
			 String lStrIFSCCode = createEmployeeService.getIFSCCode(createEmpVo1.getBankName());
			 createEmployeeForm.setIfscCode(lStrIFSCCode);
			 disabled="false";
			 }
			 else
			 {
				 createEmployeeForm.setAccHolderName("");
				 createEmployeeForm.setBankName("");
				 createEmployeeForm.setAccNumber("");
				 createEmployeeForm.setPanNumber("");
				 createEmployeeForm.setPanCardName("");
				 createEmployeeForm.setIfscCode("");
			 }
			
			 List<LabelBean> stateList = createEmployeeService.getStates();
				request.setAttribute("stateList",stateList);
				String lStrHdrMdl = null;
				String lStrHdrVil = null;
				List<LabelBean> mdlList=new ArrayList<LabelBean>();
				if (createEmpVo.getMandMunci() != null  && !createEmpVo.getMandMunci().equals("")) {
				if (createEmpVo.getMandMunci().equalsIgnoreCase("mdl")) {
					lStrHdrVil = "LH8";
					lStrHdrMdl = "LH7";

				} else {
					lStrHdrVil = "LM8";
					lStrHdrMdl = "LM7";
				}
				}
				
				if (createEmpVo.getDist() != null && !"".equalsIgnoreCase(createEmpVo.getDist())) {
					mdlList = createEmployeeService.getLocationList(createEmpVo.getDist(), createEmpVo.getDist(), lStrHdrMdl);
					if(mdlList.size()==0)
					{
					     request.setAttribute("mandalFlag",true);
					}
				}
					List<LabelBean> vilList = createEmployeeService.getLocationList(null, createEmpVo.getAddrMandMunci(), lStrHdrVil);
					if(vilList.size()==0)
					{
						 request.setAttribute("villageFlag",true);
					}
				List<LabelBean> vacNamesList=createEmployeeService.getVacantDtls();
				request.setAttribute("vacNamesList", vacNamesList);
				List<LabelBean> bankNamesList= createEmployeeService.getBankNames();
				request.setAttribute("BankCombo",bankNamesList);
				request.setAttribute("deptNamesList",deptList);
				request.setAttribute("vilList", vilList);
				request.setAttribute("mdlList", mdlList);
				request.setAttribute("disabled",disabled);
				request.setAttribute("distList", createEmployeeService.getDistrictList(createEmpVo.getState()));		
			 
			 
			}
		
	/*		resultVo=createEmployeeService.getEmployeedata(empId);
	         request.setAttribute("resultVo", resultVo);*/
	         lStrResultPage="updateEmployee";
		}
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("saveDesgDetails"))
		{
			CreateEmployeeVO createEmpVO=new CreateEmployeeVO();
			String updateFlag=request.getParameter("updateFlag");
			
			createEmpVO.setEditFlag(updateFlag);
			
			createEmpVO.setDesignationId(createEmployeeForm.getDesignationId());
			createEmpVO.setDesignationName(createEmployeeForm.getDesignationName());
			createEmpVO.setDesignationSname(createEmployeeForm.getDesignationSname());
			createEmpVO.setDesgStatus(createEmployeeForm.getDesgStatus());
			
			createEmpVO.setCrtUsr(lStrUserId);
			
			String saveFlag=createEmployeeService.saveDesgDetails(createEmpVO);
			request.setAttribute("saveFlag",saveFlag);
			
			if(updateFlag.equalsIgnoreCase("true"))
			request.setAttribute("editDesgFlag","YES");
			if(updateFlag.equalsIgnoreCase("false"))
				request.setAttribute("editDesgFlag","NO");
			createEmployeeForm.setDesignationId(createEmployeeService.getDesignationSeq());
			return mapping.findForward("addDesignation");	
		}
		
		//For Emp Designation search page
		
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("searchEmpDesg"))
		{	
			CreateEmployeeVO createEmpVO=new CreateEmployeeVO();
			List<CreateEmployeeVO> searchDesgDtls=createEmployeeService.searchDesg(createEmpVO);
			if(searchDesgDtls.size()<=0)
			{
				createEmployeeForm.setSuccessFlag("NoRecords");
			}
			else
			{
        	 createEmployeeForm.setSearchDesgList(searchDesgDtls);
			}
			
			return mapping.findForward("searchDesgList");	
		}
		
        //To search designation by the given criteria
		
		if (lStrFlagStatus != null&& lStrFlagStatus.equalsIgnoreCase("searchDsgnById"))
		{
		 	
			CreateEmployeeVO createEmpVO=new CreateEmployeeVO();
			
			String designationId=request.getParameter("designationId");
			String designationName=request.getParameter("designationName");
			String designationSname=request.getParameter("designationSname");
			createEmployeeForm.setDesignationId(designationId);
			createEmployeeForm.setDesignationName(designationName);
			createEmployeeForm.setDesignationSname(designationSname);
			
			createEmpVO.setDesignationId(designationId);
			createEmpVO.setDesignationName(designationName);
			createEmpVO.setDesignationSname(designationSname);
			 
           
			List<CreateEmployeeVO> searchDesgDtls=createEmployeeService.searchDesg(createEmpVO);
			if(searchDesgDtls.size()<=0)
			{
				createEmployeeForm.setSuccessFlag("NoRecords");
			}
			else
			{
        	 createEmployeeForm.setSearchDesgList(searchDesgDtls);
			}
			return mapping.findForward("searchDesgList");	
		
		}
		
		//To edit the designation details
		if (lStrFlagStatus != null&& lStrFlagStatus.equalsIgnoreCase("EditDesgDtls"))
			{
					CreateEmployeeVO createEmpVO=new CreateEmployeeVO();
					
					String designationId=request.getParameter("designationId");
					request.setAttribute("designationId",designationId);
					
					createEmpVO=createEmployeeService.loadDesgDtls(designationId);
					createEmployeeForm.setDesignationName(createEmpVO.getDesignationName());
					createEmployeeForm.setDesignationSname(createEmpVO.getDesignationSname());
					createEmployeeForm.setDesgStatus(createEmpVO.getDesgStatus());
					
					//request.setAttribute("editBankDtls","YES");
						request.setAttribute("editDesgFlag","YES");
					
					return mapping.findForward("addDesignation");											
			}
		
		
		
		
		
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ("loadEmployee"))
		{
			String empNumber=createEmployeeForm.getEmpNumber();
			empNumber=empNumber.toUpperCase();
			String flag=createEmployeeService.checkEmpNo(empNumber,null);
			if(flag=="false")
			{
				createEmployeeForm.setEditFlag("false");
				createEmployeeForm.setSuccessFlag("No Records");
				request.setAttribute("size", 0);
			}
		
			else
			{
			CreateEmployeeVO createEmpVo =createEmployeeService.loadEmpDtls(empNumber);
			if(createEmpVo!=null)
			{
			if(createEmpVo.getCity()!=null && !"".equalsIgnoreCase(createEmpVo.getCity()))
			{
				LabelBean labelBeanVillage=new LabelBean();
				labelBeanVillage.setID(createEmpVo.getCity());
				LabelBean newStateDtls=createEmployeeService.getNewLocations(labelBeanVillage);
				if(newStateDtls!=null)
				{
					createEmpVo.setDist(newStateDtls.getNEW_DIST());
					createEmpVo.setAddrMandMunci1(newStateDtls.getNEW_MAND());
					createEmpVo.setCity(newStateDtls.getNEW_VILLAGE());
				}
			}
			createEmployeeForm.setEditFlag("true");
			createEmployeeForm.setfName(createEmpVo.getfName());
			createEmployeeForm.setlName(createEmpVo.getlName());
			createEmployeeForm.setEmpNumber(createEmpVo.getUserNo());
			createEmployeeForm.setLoginName(createEmpVo.getLoginName());
			if(createEmpVo.getDoj()!=null)
			createEmployeeForm.setEmpDoj(dateFormat.format(createEmpVo.getDoj()));
			if(createEmpVo.getDob()!=null)
			createEmployeeForm.setEmpDob(dateFormat.format(createEmpVo.getDob()));
			createEmployeeForm.setGender(createEmpVo.getGender());
			createEmployeeForm.setState(createEmpVo.getState());
			createEmployeeForm.setCountry(createEmpVo.getCountry());
			createEmployeeForm.setDistrict(createEmpVo.getDist());
			createEmployeeForm.setEmail(createEmpVo.getEmail());
			createEmployeeForm.setMobNumber(createEmpVo.getMobileNo());
			createEmployeeForm.setEmpAddress(createEmpVo.getAddr());
			createEmployeeForm.setDistType(createEmpVo.getMandMunci());
			createEmployeeForm.setMandals(createEmpVo.getAddrMandMunci1());
			createEmployeeForm.setVillages(createEmpVo.getCity());
			createEmployeeForm.setPostalCode(createEmpVo.getPin());
			
			createEmployeeForm.setDigiSignReq(createEmpVo.getDigiVerifyReq());
			createEmployeeForm.setBioMetricReq(createEmpVo.getBiometricFlag());
			createEmployeeForm.setInService(createEmpVo.getServiceFlg());
			createEmployeeForm.setDeptName(createEmpVo.getDeptId());
			createEmployeeForm.setVacPosName(createEmpVo.getUserRole());
			
			 LabelBean vacantPosDtls=createEmployeeService.getVacPosDtls(createEmpVo.getDeptId(),createEmpVo.getUserRole());
			if(vacantPosDtls!=null )
			{
				
				 String unitId=vacantPosDtls.getUNITID();
				 List<LabelBean> listOfGrps=createEmployeeService.getGroupList(unitId);
				 String grpsList=null;
					for(LabelBean labelBean : listOfGrps)
					{
						/*if(labelBean.getGrpName()!=null)
							if (grpsList != null) {
								grpsList=grpsList+"~"+labelBean.getGrpName();
							} else {
								grpsList=labelBean.getGrpName();
							}
							*/
					}
				request.setAttribute("grpsList",grpsList);
			// createEmployeeForm.setGroupName(vacantPosDtls.getGrpName());
			 
				
			 createEmployeeForm.setLevel(vacantPosDtls.getLEVELID());
/*			 String repPersonName=createEmployeeService.getReportingPerName(vacantPosDtls.getReptRole());
			 createEmployeeForm.setRepPerson(repPersonName);
*//*			 createEmployeeForm.setDesgName(vacantPosDtls.getDsgName());
*/			}
			 CreateEmployeeVO createEmpVo1 =createEmployeeService.loadEmpBankDtls(createEmpVo.getUserId());
			 if(createEmpVo1!=null)
			 {
			 createEmployeeForm.setAccHolderName(createEmpVo1.getAccHolderName());
			 createEmployeeForm.setBankName(createEmpVo1.getBankName());
			 createEmployeeForm.setAccNumber(createEmpVo1.getAccNumber());
			 createEmployeeForm.setPanNumber(createEmpVo1.getPanNumber());
			 createEmployeeForm.setPanCardName(createEmpVo1.getPanName());
			 String lStrIFSCCode = createEmployeeService.getIFSCCode(createEmpVo1.getBankName());
			 createEmployeeForm.setIfscCode(lStrIFSCCode);
			 disabled="false";
			 }
			 else
			 {
				 createEmployeeForm.setAccHolderName("");
				 createEmployeeForm.setBankName("");
				 createEmployeeForm.setAccNumber("");
				 createEmployeeForm.setPanNumber("");
				 createEmployeeForm.setPanCardName("");
				 createEmployeeForm.setIfscCode("");
			 }
			 List<LabelBean> stateList = createEmployeeService.getStates();
				request.setAttribute("stateList",stateList);
				String lStrHdrMdl = null;
				String lStrHdrVil = null;
				List<LabelBean> mdlList=new ArrayList<LabelBean>();
				if (createEmpVo.getMandMunci() != null  && !createEmpVo.getMandMunci().equals("")) {
				if (createEmpVo.getMandMunci().equalsIgnoreCase("mdl")) {
					lStrHdrVil = "LH8";
					lStrHdrMdl = "LH7";

				} else {
					lStrHdrVil = "LM8";
					lStrHdrMdl = "LM7";
				}
				}
				
				if (createEmpVo.getDist() != null && !"".equalsIgnoreCase(createEmpVo.getDist())) {
					mdlList = createEmployeeService.getLocationList(createEmpVo.getDist(), createEmpVo.getDist(), lStrHdrMdl);
					if(mdlList.size()==0)
					{
					     request.setAttribute("mandalFlag",true);
					}
				}
					List<LabelBean> vilList = createEmployeeService.getLocationList(null, createEmpVo.getAddrMandMunci1(), lStrHdrVil);
					if(vilList.size()==0)
					{
						 request.setAttribute("villageFlag",true);
					}
				  List<LabelBean> vacNamesList=createEmployeeService.getVacantDtls();
				List<LabelBean> bankNamesList= createEmployeeService.getBankNames();
				request.setAttribute("BankCombo",bankNamesList);
				request.setAttribute("vacNamesList",vacNamesList);
				request.setAttribute("deptNamesList",deptList);
				request.setAttribute("vilList", vilList);
				request.setAttribute("mdlList", mdlList);
				request.setAttribute("disabled",disabled);
				request.setAttribute("distList", createEmployeeService.getDistrictList(createEmpVo.getState()));		
			 
			 
			}
			
			
		    }
			
			lStrResultPage="updateEmployee";
		}
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ("updateEmployee"))
		{	
			lStrResultPage="updateEmployee";
			createEmployeeForm.setEditFlag("NA");
		}
        if(lStrFlagStatus != null && "GETIFSCCODE".equals(lStrFlagStatus)){
			
			String lStrBankCode = request.getParameter("bankCode");
          	String lStrIFSCCode = createEmployeeService.getIFSCCode(lStrBankCode);
          	request.setAttribute("AjaxMessage", lStrIFSCCode);
          	return mapping.findForward("ajaxResult");
		}
        if(lStrFlagStatus != null && "getBankName".equals(lStrFlagStatus)){
			
			String lStrIFSCCode = request.getParameter("ifscCode");
          	List<LabelBean> bankCode = createEmployeeService.getBankCode(lStrIFSCCode);
          	String result="";
          	for(LabelBean l:bankCode)
          	{
       		result=result+l.getID()+"~"+l.getVALUE()+"~"+l.getBranch()+"@";
          	}
          	if(result.length()>0)
          	{
          		result=result.substring(0,result.length()-1);
          	}
          	
          	request.setAttribute("AjaxMessage", result);
          	return mapping.findForward("ajaxResult");
		}
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "getReportingPersonDetails" ) )
		{
			
				String level=request.getParameter("level");
				String deptId=request.getParameter("deptId");
				
				if ((deptId != null && !"".equalsIgnoreCase(deptId)) && (level != null && !"".equalsIgnoreCase(level)) ){
					 repPersonList=createEmployeeService.getReportingPerson(deptId,level,lStrScheme);
					request.setAttribute("repPersonList", repPersonList);
					@SuppressWarnings("rawtypes")
					List repPersonsAjax = new ArrayList();
					for (LabelBean labelBean : repPersonList) {
						if (labelBean.getID() != null && labelBean.getVALUE() != null)
							if (repPersonsAjax != null && repPersonsAjax.size() > 0) {
								repPersonsAjax.add("@" + labelBean.getID() + "~"
										+ labelBean.getVALUE());
							} else {
								repPersonsAjax.add(labelBean.getID() + "~"
										+ labelBean.getVALUE());
							}
					}
					request.setAttribute("AjaxMessage", repPersonsAjax);
					return mapping.findForward("ajaxResult");
					}
			}
		
		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("getVacantPosition")) {
			String userDeptId=request.getParameter("deptId");
            List<LabelBean> vacantList=createEmployeeService.getVacantDtls();
            
            request.setAttribute("vacNamesList", vacantList);
			@SuppressWarnings("rawtypes")
			List vacPosAjax = new ArrayList();
			for (LabelBean labelBean : vacantList) {
				if (labelBean.getID() != null && labelBean.getVALUE() != null)
					if (vacPosAjax != null && vacPosAjax.size() > 0) {
						vacPosAjax.add("@" + labelBean.getID() + "~"
								+ labelBean.getVALUE());
					} else {
						vacPosAjax.add(labelBean.getID() + "~"
								+ labelBean.getVALUE());
					}
			}
			request.setAttribute("AjaxMessage", vacPosAjax);
			return mapping.findForward("ajaxResult");
			}
          
			
/*		if(lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase("getVacantPosDetails")) {
			 String vacPos=request.getParameter("vacPosName");
			 String dept=request.getParameter("dept");
			 String vacantPosDtlString="";
			
			 LabelBean vacantPosDtls=createEmployeeService.getVacPosDtls(dept,vacPos);
			 String unitId=vacantPosDtls.getUNITID();
			 List<LabelBean> listOfGrps=createEmployeeService.getGroupList(unitId);
			 String repPersonName=createEmployeeService.getReportingPerName(vacantPosDtls.getReptRole());
			 vacantPosDtlString=vacantPosDtls.getLEVELID()+"~"+vacantPosDtls.getDsgName()+"~"+repPersonName;
			 String grpsList=null;
		for(LabelBean labelBean : listOfGrps)
		{
			if(labelBean.getGrpName()!=null)
				if (grpsList != null) {
					grpsList=grpsList+"~"+labelBean.getGrpName();
				} else {
					grpsList=labelBean.getGrpName();
				}
				
		}
		vacantPosDtlString=vacantPosDtlString+"^"+grpsList;
			 request.setAttribute("AjaxMessage", vacantPosDtlString);
				return mapping.findForward("ajaxResult");
			
			
		}*/
		
		
		
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "saveVacantPositionDtls" ) ) 
        { 
			//String userLoginId=createEmployeeForm.getDeptName();
			//String password=lStrNewPswd;
			String vacPosName=request.getParameter("vacPosName");
			createEmployeeForm.setVacPosName(vacPosName);
			CreateEmployeeVO createEmpVo = new CreateEmployeeVO();
			String unitSeqID = createEmployeeService.getUnitSeqId();
			createEmpVo.setDeptId(createEmployeeForm.getDeptName());
			createEmpVo.setUnitSeqId(Integer.parseInt(unitSeqID));
			createEmpVo.setUnitName(createEmployeeForm.getVacPosName());
			createEmpVo.setLevel(createEmployeeForm.getLevel());
			createEmpVo.setReportingPersonId(createEmployeeForm.getRepPerson());
			createEmpVo.setLangId(langId);
			createEmpVo.setShortName(createEmployeeForm.getVacPosName());
			createEmpVo.setLocId(locId);
			createEmpVo.setGrpId(createEmployeeForm.getGroupType());
			createEmpVo.setCrtUsr(lStrUserId);
			createEmpVo.setDesgId(createEmployeeForm.getDesgName());
			createEmpVo.setShiftCode(createEmployeeForm.getShift());
			createEmpVo.setReportingPersonName(createEmployeeForm.getRepPerson());
			
			//createEmpVo.setWorkLoc("Hyderabad");
			createEmpVo.setUnitId(createEmployeeForm.getDeptName());
			createEmpVo.setWeekOffDay(createEmployeeForm.getWeekOffDay());
			if(!lStrScheme.equalsIgnoreCase("CD203"))
				createEmpVo.setScheme(lStrScheme);
				else
					createEmpVo.setScheme(createEmployeeForm.getSchemeType());

			String result = createEmployeeService.saveInvstDtls(createEmpVo);
			if(result=="success")
			{
				createEmployeeForm.setSuccessFlag("Success");
				lStrFlagStatus="vacantPosition";
				request.setAttribute("result",result);
			}
			
			
        }
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "getDistrictList" ) ) 
        {
			String state=request.getParameter("state");
			String stateType=request.getParameter("stateType");
			if (state != null && !"".equalsIgnoreCase(state)) {
				List<LabelBean> distList = createEmployeeService.getNewDistrictList(state, stateType);
				request.setAttribute("distList", distList);
				List<String> distAjax = new ArrayList<String>();
				for (LabelBean labelBean : distList) {
					if (labelBean.getLOCID() != null
							&& labelBean.getDISTRICTNAME() != null)
						if (distAjax != null && distAjax.size() > 0) {
							distAjax.add("@" + labelBean.getLOCID() + "~"
									+ labelBean.getDISTRICTNAME());
						} else {
							distAjax.add(labelBean.getLOCID() + "~"
									+ labelBean.getDISTRICTNAME());
						}
				}
				request.setAttribute("AjaxMessage", distAjax);
				return mapping.findForward("ajaxResult");
			

			}
        }
		
		//To get designations according to departments
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "getDesignations" ) ) 
        {
			String deptName=request.getParameter("deptName");
			if (deptName != null && !"".equalsIgnoreCase(deptName)) {
				List<LabelBean> desgsList = createEmployeeService.getDesignationList(deptName);
				request.setAttribute("desgNamesList",desgsList);
				List<String> desgAjax = new ArrayList<String>();
				for (LabelBean labelBean : desgsList) {
					if (labelBean.getID() != null
							&& labelBean.getVALUE() != null)
						if (desgAjax != null && desgAjax.size() > 0) {
							desgAjax.add("@" + labelBean.getID() + "~"
									+ labelBean.getVALUE());
						} else {
							desgAjax.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						}
				}
				request.setAttribute("AjaxMessage", desgAjax);
				return mapping.findForward("ajaxResult");
			

			}
        }
		//To get groups according to designations
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "getGroups" ) ) 
        {
		
			String desgName=request.getParameter("desgName");
			String deptName=request.getParameter("deptName");
			if (desgName != null && !"".equalsIgnoreCase(desgName)) {
				List<LabelBean> grpsList = createEmployeeService.getGroupsList(desgName,deptName);
				request.setAttribute("groupNamesList",grpsList);
				List<String> grpsAjax= new ArrayList<String>();
				for (LabelBean labelBean : grpsList) {
					if (labelBean.getID() != null
							&& labelBean.getVALUE() != null)
						if (grpsAjax != null && grpsAjax.size() > 0) {
							grpsAjax.add("@" + labelBean.getID() + "~"
									+ labelBean.getVALUE());
						} else {
							grpsAjax.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						}
				}
				request.setAttribute("AjaxMessage", grpsAjax);
				return mapping.findForward("ajaxResult");
			

			}
        }
		// To get the mandals/municipalities list based on the district selected
		if (lStrFlagStatus != null
				&& lStrFlagStatus.equalsIgnoreCase("getMandalList")) {
			String dist = request.getParameter("distId");
			String mandal = request.getParameter("mandal");
			String distId = request.getParameter("distId");
			String mmty = request.getParameter("mmpty");
			String lStrHdrMdl = null;
			String lStrHdrVil = null;
			if (mandal.equalsIgnoreCase("mdl")) {
				lStrHdrVil = "LH8";
				lStrHdrMdl = "LH7";

			} else {
				lStrHdrVil = "LM8";
				lStrHdrMdl = "LM7";
			}
			if (dist != null && !"".equalsIgnoreCase(dist)) {
				List<LabelBean> mList = createEmployeeService.getLocationList(
						dist, distId, lStrHdrMdl);
				request.setAttribute("mdlList", mList);
				List<String> mandalsAjax = new ArrayList<String>();
				for (LabelBean labelBean : mList) {
					if (labelBean.getID() != null
							&& labelBean.getVALUE() != null)
						if (mandalsAjax != null && mandalsAjax.size() > 0) {
							mandalsAjax.add("@" + labelBean.getID() + "~"
									+ labelBean.getVALUE());
						} else {
							mandalsAjax.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						}
				}
				request.setAttribute("AjaxMessage", mandalsAjax);
				return mapping.findForward("ajaxResult");
			} else {
				List<LabelBean> cList = createEmployeeService.getLocationList(
						null, mmty, lStrHdrVil);
				request.setAttribute("vilList", cList);
				List<String> villsAjax = new ArrayList<String>();
				for (LabelBean labelBean : cList) {
					if (labelBean.getID() != null
							&& labelBean.getVALUE() != null)
						if (villsAjax != null && villsAjax.size() > 0) {
							villsAjax.add("@" + labelBean.getID() + "~"
									+ labelBean.getVALUE());
						} else {
							villsAjax.add(labelBean.getID() + "~"
									+ labelBean.getVALUE());
						}
				}
				request.setAttribute("AjaxMessage", villsAjax);
				return mapping.findForward("ajaxResult");

			}
		}
		
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "vacantPosition" ) ) 
        {
			request.setAttribute("shiftList",shiftList);
			desgList = createEmployeeService.getDesgDetails();
			grpList = createEmployeeService.getGrpList();
			request.setAttribute("deptNamesList",deptList);
			request.setAttribute("desgNamesList",desgList);
			request.setAttribute("groupNamesList",grpList);
			request.setAttribute("weekList",weekList);
			request.setAttribute("levelList",levelList);
			request.setAttribute("disabled",disabled);
			request.setAttribute("butDisabled",butDisabled);
			lStrResultPage="newVacantPostion";
        }
		
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "searchEmployees" ) ) 
        {   
		
			List<LabelBean> hospList=createEmployeeService.getHospList();
			desgList=createEmployeeService.getDesgDetails();
			grpList=createEmployeeService.getGrpList();
			request.setAttribute("deptNamesList",deptList);
			request.setAttribute("desgNamesList",desgList);
			request.setAttribute("groupNamesList",grpList);
			request.setAttribute("hospNamesList",hospList);
			createEmployeeForm.setSchemeType("CD203");
			createEmployeeForm.setStatus("Y");
            return mapping.findForward("searchEmployees");
        }
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ( "createEmp" ) ) 
        { 
			lStrResultPage="createEmp";
        }
		
		if ( lStrFlagStatus!=null && lStrFlagStatus.equalsIgnoreCase ("searchResultList"))
		{
			

			Map<String, Object> returnMap = null;
			
			String desgName=(String) request.getParameter("desgName");
			String loginName=(String) request.getParameter("loginName");
			String empName=(String) request.getParameter("empName");
			String status=(String) request.getParameter("status");
			
			List<CreateEmployeeVO> reptList=new ArrayList<CreateEmployeeVO>();
			
			CreateEmployeeVO empSearchResultsList=null;
			CreateEmployeeVO createEmployeeVO =new CreateEmployeeVO();
			List<CreateEmployeeVO> employeeList=new ArrayList<CreateEmployeeVO>();
	
/*			loginName=loginName.toUpperCase();*/
/*			empName=empName.toUpperCase();
*/			
	
			createEmployeeForm.setDesgName(desgName);
			createEmployeeForm.setLoginName(loginName);
			createEmployeeForm.setEmpName(empName);
			//createEmployeeForm.setStatus(status);
			createEmployeeVO.setDesgId(desgName);
			createEmployeeVO.setfName(empName);
			createEmployeeVO.setStatus(status);
			if(loginName!=null&&!loginName.equalsIgnoreCase(""))
			createEmployeeVO.setLoginName(loginName.toUpperCase());
			
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
			empSearchResultsList=createEmployeeService.searchEmployeeList(createEmployeeVO);
		
				
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
							
						empSearchResultsList=createEmployeeService.searchEmployeeList(createEmployeeVO);
						employeeList=empSearchResultsList.getEmpSearchList();
						totalRecords= Integer.valueOf(empSearchResultsList.getTotRowCount());
						if(empSearchResultsList!=null)
						{
							if(empSearchResultsList.getEmpSearchList()!=null)
								{
								
								totalPages=totalRecords%maxResults!=0?totalRecords/maxResults+1:totalRecords/maxResults;

					/*	if(totalRecords%10==0)
							totalPages=totalRecords/10;
						else
							totalPages=(totalRecords/10)+1;*/
					        List<LabelBean> desgNamesList=createEmployeeService.getVacantDtls();
							request.setAttribute("desgNamesList", desgNamesList);
							createEmployeeForm.setEmpList(empSearchResultsList.getEmpSearchList());
							request.setAttribute("pageNo",pageIdVO);
							request.setAttribute("noOfRecords",totalRecords);
							request.setAttribute("lastPage",totalPages);
							request.setAttribute("start_index",startIndex);
							request.setAttribute("end_index",maxResults);
							request.setAttribute("endresults",startIndex+empSearchResultsList.getEmpSearchList().size());
							
								}
						  }
						
					else
						createEmployeeForm.setSuccessFlag("NoRecords");

		
			
			
				/*
			empSearchResultsList=createEmployeeService.searchEmployeeList(createEmployeeVO);*/
			
			if(createEmployeeVO.getExportFlag().equals("Y"))
				lStrFlagStatus="exportToCsv";
			
			 empList=empSearchResultsList.getEmpSearchList();
			 if(empList.size()>0)
				{
				 	request.setAttribute("empList", empList);
				}
			
			
			
			 lStrResultPage="searchResults";
		}
		
		 if(lStrFlagStatus != null && ("generateEmpExcel").equals(lStrFlagStatus)) {
			
				
					List<EmployeeExcelVO> exclList=new ArrayList<EmployeeExcelVO>();
					EmployeeExcelVO row1=new EmployeeExcelVO();
					int i=0;
					row1.setSno("S.No");
						row1.setLoginName("Employee LoginName");
						row1.setEmployeeName("Employee Name");
						row1.setDesignation("Designation");
						row1.setMobileNumber("MobileNumber");
						row1.setEmailId("EmailId");
						exclList.add(row1);
					for(CreateEmployeeVO row:empList)
					{	
						row1=new EmployeeExcelVO();
						row1.setSno(++i+"");
						row1.setLoginName(row.getLoginName());
						row1.setEmployeeName(row.getfName()+" "+row.getlName());
						row1.setDesignation(row.getDesgName());
						row1.setMobileNumber(row.getMobileNo());
						row1.setEmailId(row.getEmail());
						exclList.add(row1);
					}
					
					String lStrDirPath=mapPath;
			        String lStrFileName=mapPath+"EmployeeSearchExcel.xls";

			         	//Creates file in EHFTemp folder of client machine 
			         	File xlFile = createFile(lStrDirPath,lStrFileName);
					prepareExl(xlFile,exclList);
					    byte[] lbBytes= getBytesFromFile(xlFile);
			            
					    //Setting request and response objects
			            request.setAttribute("File", lbBytes);                 
			            response.setContentType("application/vnd.oasis.opendocument.spreadsheet");
			            response.setHeader("Content-Disposition","Attachment; filename=EmployeeSearchExcel.xls"); 
			            return mapping.findForward("openFile"); 
				}
		 

		 if(lStrFlagStatus!= null && "exportToCsv".equals(lStrFlagStatus))
		 {
	         List<EmployeeExcelVO> exclList=new ArrayList<EmployeeExcelVO>();
	         
					 String lStrDirPath="/storageNAS-Production/";
			         
					 String lStrFileName="EmployeeSearchCSV.csv";
					 String lStrFileName1=lStrDirPath+"/"+lStrFileName;     	         
						     //Creates file in EHFTemp folder of client machine 
					         File lcsvfile = createFile(lStrDirPath,lStrFileName1); 
					        // File lcsvfile = new File(lStrFileName);
					        
					         char separator = ',';
					         StringBuilder line = new StringBuilder();   
					         
			                  
			                  line.append("Employee LoginName");
			                  line.append(separator);
			                  line.append("Employee Name");
			                  line.append(separator);
					          line.append("Designation");
			                  line.append(separator);
			                  line.append("MobileNumber");
			                  line.append(separator);
			                  line.append("EmailId");
			                  line.append(separator);
			                  line.append("Status");	
			                  		                  
			                  line.append("\n");
				                
			                
			                  if(empList!=null && empList.size()>0)
			                  {
					             for(CreateEmployeeVO row:empList)
					             {   String status=null;
					            	 line.append(row.getLoginName()!=null?row.getLoginName():"");
					                  line.append(separator);
					                  line.append(row.getfName()!=null?row.getfName()+" "+row.getlName():"");				                 
					                  line.append(separator);
					                  line.append(row.getDesgName()!=null?row.getDesgName():"");
					                  line.append(separator);
					                  line.append(row.getMobileNo()!=null?row.getMobileNo():"");
					                  line.append(separator);
					                  line.append(row.getEmail()!=null?row.getEmail():"");
					                  line.append(separator);
					                  if(row.getServiceFlg()!=null&&!row.getServiceFlg().equalsIgnoreCase("")&&row.getServiceFlg().equalsIgnoreCase("Y"))
					                	  status="Active";
					                  else
					                	  status="In-Active";
					                  line.append(status!=null?status:"");
					                  line.append("\n");
					                        
					             }
			                  }
					             
					             
					       
				            //Setting request and response objects
				            request.setAttribute("File", line.toString().getBytes());    
						    response.setContentType(config.getString("ADMIN.CsvContentType"));
						    response.setHeader("Content-Disposition","Attachment; filename="+lStrFileName);
						    response.setCharacterEncoding("UTF-8");
					     
					         return mapping.findForward("openFile");
					         /*  }
				           else
				            {
					        generalLedgerForm.setFlag(config.getString("ACC.ListNotFound"));
							return mapping.findForward("generalLedger");
				            }*/
					
				    }
				
		 if(lStrFlagStatus!= null && "sendGroupEmail".equals(lStrFlagStatus))
		 {
			 lStrResultPage="groupEmail";
		 }
		 if(lStrFlagStatus!= null && "sendGroupMails".equals(lStrFlagStatus))
		 {
			 createEmployeeService.sendBulkEmails();
			 lStrResultPage="groupEmail";
		 }
		 
		 		return mapping.findForward(lStrResultPage);
		}	

	
	
	
	
	
	public static <T> void prepareExl(File xlFile,List<T> empList) throws Exception
	{
		Field[] fields = empList.get(0).getClass().getDeclaredFields();
		WritableWorkbook writableWorkbook = Workbook.createWorkbook(xlFile);
		
		WritableFont lLblFont = new WritableFont(WritableFont.ARIAL,
                  WritableFont.DEFAULT_POINT_SIZE,
                  WritableFont.BOLD,
                  false,
                  UnderlineStyle.NO_UNDERLINE,
                  Colour.DARK_BLUE);
		WritableFont ldataFont = new WritableFont(WritableFont.ARIAL);
		
		WritableSheet writableSheet = writableWorkbook.createSheet("Sheet1", 0);
		WritableCellFormat lLblFmt = new WritableCellFormat(lLblFont);
		WritableCellFormat ldataFmt = new WritableCellFormat(ldataFont);
		
		// sets background of Label cell
	       lLblFmt.setBackground(Colour.GRAY_25);
	       lLblFmt.setWrap(true);
	       lLblFmt.setBorder(Border.ALL,BorderLineStyle.THIN);
	       lLblFmt.setAlignment(Alignment.GENERAL);
	       lLblFmt.setVerticalAlignment(VerticalAlignment.TOP);
	     //setting the format for data cells
	       ldataFmt.setBorder(Border.ALL,BorderLineStyle.THIN);
	       ldataFmt.setWrap(true);
	       ldataFmt.setAlignment(Alignment.GENERAL);
	       ldataFmt.setVerticalAlignment(VerticalAlignment.TOP);
	       ldataFmt.setWrap(true);
	       
	       //setting the Headers (First Row of the List Should Be headers)
	       for(int i=0;i<fields.length;i++)
	       {
	    	   fields[i].setAccessible(true);
	    	   if(fields[i].get(empList.get(0)) != null)
	    	   writableSheet.addCell(new Label(i,0,fields[i].get(empList.get(0)).toString(),lLblFmt));
	       }

			Label[] label = new Label[fields.length];
			
			for(int i=1;i<empList.size();i++)
			{
				
				for(int j=0;j<fields.length;j++)
				{
					fields[j].setAccessible(true);
					 if(fields[j].get(empList.get(i)) != null)
					 {
					label[j] =new Label(j,i,fields[j].get(empList.get(i)).toString(),ldataFmt);
					writableSheet.addCell(label[j]);
					
					 }
				}
			}
			

			//Write and close the workbook
			writableWorkbook.write();
			writableWorkbook.close(); 
	}
	public Map<String, Object> fn_pagination(Map<String, Object> map) {

		Map<String, Object> returnMap = new HashMap<String, Object>();

		int totalRecords = 1, showPage = 1, rowsPerPage = 10, pages = 1;
		int startIndex = 1;
		int endIndex = 10;
		int startPage = 1;
		int endPage = 10;
		int tmpStartPage;
		int tmpEndPage;
		String pageNO = "";
		totalRecords = Integer.parseInt(map.get("totalRecords").toString());
		rowsPerPage = Integer.parseInt(map.get("rowsPerPage").toString());
		pageNO = map.get("pageNO").toString();
		tmpStartPage = Integer.parseInt(map.get("startPage").toString());
		tmpEndPage = Integer.parseInt(map.get("endPage").toString());

		returnMap.put("totalRecords", totalRecords);
		returnMap.put("rowsPerPage", rowsPerPage);

		if (totalRecords > rowsPerPage) {

			if ((totalRecords % rowsPerPage) == 0) {
				pages = totalRecords / rowsPerPage;

			} else {

				pages = (totalRecords / rowsPerPage) + 1;
			}

		} else {

			pages = 1;
		}

		if (pageNO == null || "".equalsIgnoreCase(pageNO)) {

			if (pages > 10 && showPage <= 10) {
				returnMap.put("next", "next");

			}
			if (pages > 10 && (showPage - 10) > 1) {

				returnMap.put("prev", "prev");

			}

		}

		if ((pageNO != null && !"".equalsIgnoreCase(pageNO) && !"prev"
				.equalsIgnoreCase(pageNO)) && !"next".equalsIgnoreCase(pageNO)) {

			String str = pageNO;
			showPage = Integer.parseInt(str);

			startIndex = ((showPage - 1) * rowsPerPage) + 1;

			if ((showPage * rowsPerPage) < totalRecords) {

				endIndex = showPage * rowsPerPage;

			} else {

				endIndex = totalRecords;

			}

			int x = tmpStartPage;

			int y = tmpEndPage;

			if (y > pages) {
				y = pages;
			}

			if (pages > 10 && pages > y) {

				returnMap.put("next", "next");

			}

			if (pages > 10 && x > 10) {
				returnMap.put("prev", "prev");
			}

			startPage = x;
			endPage = y;

		}

		if (pageNO != null && "prev".equalsIgnoreCase(pageNO)) {

			int x = tmpStartPage;

			int y = tmpEndPage;
			if (x >= 10 && pages > 10) {

				returnMap.put("next", "next");

			}

			if (x - 10 > 10 && pages > 10) {

				returnMap.put("prev", "prev");
			}

			if (showPage == 1) {

				if (totalRecords > rowsPerPage) {

					startIndex = 1;
					endIndex = rowsPerPage;
				} else {

					startIndex = (showPage - 1) * rowsPerPage + 1;
					if ((startIndex + rowsPerPage) < totalRecords) {

						endIndex = startIndex + rowsPerPage;
					} else {

						endIndex = totalRecords;
					}

				}
			}
			startPage = x - 10;
			endPage = x - 1;
			returnMap.put("startPage", startPage);
			returnMap.put("endPage", endPage);

			startIndex = ((showPage - 1) * rowsPerPage) + 1;

			if ((showPage * rowsPerPage) < totalRecords) {

				endIndex = showPage * rowsPerPage;

			} else {

				endIndex = totalRecords;

			}
		}

		if (pageNO != null && "next".equalsIgnoreCase(pageNO)) {

			int x = tmpStartPage;

			int y = tmpEndPage;

			startPage = y + 1;
			endPage = y + 10;
			if (endPage > pages) {

				endPage = pages;
			}

			if (pages > startPage + 10) {
				returnMap.put("next", "next");

			}

			if (startPage - 10 >= 1) {

				returnMap.put("prev", "prev");

			}
			returnMap.put("startPage", startPage);
			returnMap.put("endPage", endPage);

			showPage = startPage;
			startIndex = ((showPage - 1) * rowsPerPage) + 1;

			if ((showPage * rowsPerPage) < totalRecords) {

				endIndex = showPage * rowsPerPage;

			} else {

				endIndex = totalRecords;

			}

		}

		if (showPage == 1) {

			if (totalRecords > rowsPerPage) {

				startIndex = 1;
				endIndex = rowsPerPage;
			} else {

				startIndex = (showPage - 1) * rowsPerPage + 1;
				if ((startIndex + rowsPerPage) < totalRecords) {

					endIndex = startIndex + rowsPerPage;
				} else {

					endIndex = totalRecords;
				}

			}
		}
		startIndex = startIndex - 1;

		returnMap.put("showPage", showPage);

		returnMap.put("startIndex", startIndex);

		returnMap.put("endIndex", endIndex);
		returnMap.put("startPage", startPage);
		returnMap.put("endPage", endPage);
		returnMap.put("pages", pages);
		return returnMap;

	}


	public static byte[] getBytesFromFile(File file)
	        throws IOException
	{
	        FileInputStream fileinputstream = new FileInputStream(file);
	        long l = file.length();
	        if(l <= 0x50fffffffL);
	        byte abyte0[] = new byte[(int)l];
	        int i = 0;
	        for(int j = 0; i < abyte0.length && (j = fileinputstream.read(abyte0, i, abyte0.length - i)) >= 0; i += j);
	        if(i < abyte0.length)
	        {
	            throw new IOException("Could not completely read file " + file.getName());
	        } else
	        {
	            fileinputstream.close();
	            return abyte0;
	        }
	 }
	
	private File createFile(String lStrDirPath,String lStrFileName) throws IOException 
	{
		//Making Directory		  
		File file = new File(lStrDirPath);
		if (!file.exists()) 
		{
			file.mkdir();
		}
		File lfile = new File(lStrFileName);
		if(!lfile.exists())
		{
			lfile.createNewFile();
		}
		else
		{
			lfile.delete();
			lfile.createNewFile();
		}
		return lfile;
		
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
	private void smsFlag(HashMap smsDataMap) {

		// boolean flag=true;

		SMSServices smsServices  = new SMSServices();

		try {
			smsServices.sendSingleSMS((String) smsDataMap.get("Message"),
					(String) smsDataMap.get("MobileNos"),null);
		}  catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
		// return flag;
	}


}
