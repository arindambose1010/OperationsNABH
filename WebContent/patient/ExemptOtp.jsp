<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,
                              java.util.Iterator,
                              java.util.List,com.ahct.patient.vo.PatientVO" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
     
     <%@ include file="/common/include.jsp"%>

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
.custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script type="text/javascript" src="scripts/ModalPopups.js"></script>
<style>
input:disabled, select:disabled{
background-color:#ddd;
}
</style>
<script type="text/javascript">
var rolId = '${rolId}';

$(document).keypress(function(event){
    if(event.keyCode == 13){
     $('#patientNo').click();
      //  validate(); doesn't need to be called from here
      fnSearch();
    }
});

function disableRadio()
{
	if(rolId=='GP801')
		{
		
		document.getElementById("x1").style.display="";
		document.getElementById("x2").style.display="";
		document.getElementById("oldCaseCheck")[1].checked=true;
		
		}
	
	
	}


function parseDate(input) {
  var parts = input.split('-');
  // new Date(year, month [, day [, hours[, minutes[, seconds[, ms]]]]])
  return new Date(parts[2], parts[1]-1, parts[0]); // Note: months are 0-based
}
var fromDisp = '${fromDisp}';


function fnSearch()
{
	/* if(document.forms[0].patientNo.value=="")
		     
            {  
				jqueryAlertMsg('Search Criteria Validation','Please enter search criteria! ');
                return false;
            } */
	
	
	
	
	//var paginStatus='first';
	//var fr=partial(fn_pagination,paginStatus);
	//jqueryConfirmMsg("Search Confirmation","Do you want to search registered patients with this search criteria",fr);

	document.getElementById("currPatId").value=document.forms[0].patientNo.value;
	
	
	fn_pagination(0,'button');
	
}
function openCase(patientId,caseId)
{
	var fromDisp = '${fromDisp}';
	var fromPharma = '${fromPharma}';
	if(fromPharma!=null && fromPharma!='' && fromPharma == 'Y' ){
		if(caseId=='NA')
			document.forms[0].action="./patientDetails.do?actionFlag=viewPharmacyPage&patientId="+patientId+"&fromDisp="+fromDisp;
		else
		   document.forms[0].action="./patientDetails.do?actionFlag=viewPharmacyPage&patientId="+patientId+"&caseId="+caseId+"&fromDisp="+fromDisp;
		   document.forms[0].method="post";
		   document.forms[0].submit();
	}else{
		if(caseId=='NA')
			document.forms[0].action="./patientDetailsNew.do?actionFlag=viewDTOpage&patientId="+patientId+"&fromDisp="+fromDisp;
		else
		   document.forms[0].action="./patientDetailsNew.do?actionFlag=viewDTOpage&patientId="+patientId+"&caseId="+caseId+"&fromDisp="+fromDisp;
		   document.forms[0].method="post";
		   document.forms[0].submit();	
	}
	 
}

function openModal(patientId,caseId){
	document.getElementById('patId').value = patientId;
	document.getElementById('caseId').value = caseId;
	ModalPopups.Confirm("idConfirm1",
			"Please provide remarks for OTP  exemption to Patient No :  " + patientId ,
			"<div id='remarksdiv' name='remarksdiv'style='width:500px;height:100px;'> Remarks: <input type='textarea'  id='remarks'/></div>",
			{
			okButtonText: "Close",
			titleBackColor: "#C1D2E7",
			onYes: "fn_exemptOtpApproval()",
			onNo: "onReject()",
			yesButtonText: "Exempt",
			noButtonText: "Cancel"
			}
			);
}
function onExempt(){
	alert("Patient Exempted from OTP and go to pharma login");
	return false;
}
function onReject(){
	alert("Patient has to wait until OTP is Received");
	fnSearch();
	return false;
	//ModalPopups.Close("idConfirm1");
}

function fn_pagination(pageNo,actionType)
{
	var fromDisp = '${fromDisp}';
	document.forms[0].advSearch.value="true";
	 var x = document.getElementById("oldCaseCheck").checked;

	var url="./patientDetailsNew.do?actionFlag=viewExemptOtpList&actionType="+actionType+"&pageNo="+pageNo+"&fromDispnsry="+fromDisp+"&x="+x;
	document.forms[0].action=url;
	 //document.forms[0].method="post";
	 document.forms[0].submit(); 
}
function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	fn_pagination(1,num);
	}
function showinSetsOf(num)
{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPage.value='1'; 
	fn_pagination(1,num);	
}
function resetSearch()
{
	document.getElementById("patientNo").value="";
	
}

function  refreshParentPage()
{
window.close();
}
function fn_exemptOtpApproval(){
	parent.fn_loadImage();
	var rmks=document.getElementById('remarks').value;
	$.post('patientDetailsNew.do?actionFlag=exemptOtpApproval', {patientNo:$("#patId").val(),remarks:rmks}, function(result){
		var responseData=result.split("@@");
		if(responseData[0]=='true'){
			alert("OTP Exemption request sent to Pharma login");
			parent.fn_exemptOTP();
		}else{
			alert("Failed requesting OTP exemption.\n Please try again.");
		}
		parent.fn_removeLoadingImage();
});
	ModalPopups.Close("idConfirm1");
	fn_exemptOTP();
}
function fn_exemptOTP(){
	fn_loadImage();
	document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=viewExemptOtpList';
}
</script>
</head>
<body onload="disableRadio();">
<form action="/patientDetails.do" method="post">
<logic:notEmpty name="patientForm" property="msg">
    <script language="javascript" type="text/javascript">
    bootbox.alert('<bean:write name="patientForm" property="msg"/>');
    </script>
    </logic:notEmpty>
<div>
<table class="tbheader">
<tr><th style="padding-left:40%"><b><fmt:message key="EHF.Title.RegisteredPatientsExemptOtp"/></b></th></tr>
</table>
<table width="100%">
<%-- <tr  height="30px"><th><fmt:message key="EHF.Title.AdvancedSearch"/></th></tr> --%>
<tr><td>
<table width="100%" class="tb">
<tr>
<td  width="25%" class="labelheading1 tbcellCss" align="center"><b><fmt:message key="EHF.PatientNo"/></b>
<td width="25%" class="tbcellBorder"><html:text name="patientForm"  property="patientNo" maxlength="12" styleId="patientNo" onchange="validateNumber('Patient No',this)" title="Enter Patient No" style="WIDTH: 10em;;"/></td>



<td width="25%" class="labelheading1 tbcellCss" align="center" style="display:none" id="x1">
<b>Registrations Done Prior to 14th May:</b>
</td>

<td width="25%" class="labelheading1 tbcellCss" align="center" style="display:none" id="x2">
<input type="radio" name="oldCaseCheck" value="Y" id="oldCaseCheck">Yes &nbsp &nbsp &nbsp &nbsp
<input type="radio" name="oldCaseCheck" value="N" id="oldCaseCheck">No 
</td>

</tr>
<tr>
<td colspan="2" align="right">  <button class="but"  type="button" onclick="javascript:fnSearch()">Search</button></td>
<td> <button class="but"  type="button" onclick="resetSearch()">Reset</button></td>
</tr>
</table>
</td></tr>
</table>
<br>
<table  width="100%" class="tb">
<logic:notEmpty name="registeredPatientsList">

<table  width="100%" class="tb">
<tr >
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.PatientNo"/></th>
<th class="tbheader1" style="width:20%"><fmt:message key="EHF.PatientName"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.Employee/PensionerCardNo"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.District"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.Gender"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.Age"/></th>
<th class="tbheader1" style="width:10%"><fmt:message key="EHF.RegistrationDate"/></th>
</tr>
	<logic:iterate name="registeredPatientsList" id="patientVO">
<tr>
<td class="tbcellBorder">&nbsp;
<a href="javascript:openModal('<bean:write name="patientVO" property="patientId"/>','<bean:write name="patientVO" property="caseId"/>')" title="Click On Patient No to view Remarks popup"><b><bean:write name="patientVO" property="patientId"/></b></a>
</td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="firstName"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="rationCard"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="districtCode"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="gender"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="age"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="regHospDt"/></td>
</tr>
</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="registeredPatientsList">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >
<td align="center" height="50">
<b><bean:write name="patientForm" property="errMsg"/></b>
</td>
</tr>
</table>
</logic:empty>
<input type="hidden" name="advSearch" value="false"/>  
<input type="hidden" name="patId" id="patId" value=""/>
<input type="hidden" name="caseId" id="caseId" value=""/>    
<html:hidden name="patientForm" property="currPatId" styleId="currPatId"/>
<html:hidden name="patientForm" property="currPatName" styleId="currPatName"/>  
<html:hidden name="patientForm" property="currHealthCardNo" styleId="currHealthCardNo"/> 
<html:hidden name="patientForm" property="currStateId" styleId="currStateId"/> 
<html:hidden name="patientForm" property="currDistrictId" styleId="currDistrictId"/>  
<html:hidden name="patientForm" property="currFromDate" styleId="currFromDate"/>  
<html:hidden name="patientForm" property="currToDate"  styleId="currToDate"/>
<html:hidden name="patientForm" property="rowsPerPage"  styleId="rowsPerPage"/>  
<html:hidden name="patientForm" property="showPage"  styleId="showPage"/>  
<html:hidden property="totalRows" name="patientForm" />
<input type ="hidden" name="hospGovu" id= "hospGovu" value="${hospGovu}"/>
<html:hidden property="fromDisp" name="patientForm" value="${fromDisp}" />
<html:hidden name="patientForm" property="noOfPages" styleId="noOfPages"/>
<%-- <html:hidden name="patientForm" property="state" styleId="state" value="S35"/> --%>
</table>
</form>
</body>
</html>
</fmt:bundle>