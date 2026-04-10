<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
<%-- <%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,java.util.Iterator,java.util.List,com.ahct.patient.vo.PatientVO" %> --%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	 <%@ include file="/common/include.jsp"%>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen"> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<link href="css/themes/darkgreen/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>  
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/jquery.ui.datepicker.js"></script>
<script src="js/DateTimePicker.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>
<script src="bootstrap/js/bootbox.min.js"></script> 
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/select2.min.js"></script>
<script type="text/javascript">

function trimDot(arg)
{	
	arg.value = arg.value.replace(/\s+/g, ' ').replace(/(\.\s+)+/g, '. ').replace(/\.+/g, '.').replace(/,+/g, ',').replace(/:+/g, ':').replace(/\-+/g, '-').replace(/\/+/g, '/').replace(/^[ ]+|[ ]+$/g,'').replace(/^[,]+|[,]+$/g,'').replace(/^[.]+/g,'');
}
function checkalphanum(id)
{
	var add=id;
	var address=jQuery(add).val();
	var alphanum=new RegExp('[^A-Za-z., ]','g');
	if( alphanum.test(address) )
	{
		jQuery(add).val(address.replace(alphanum,''));
		alert('Only characters from A-Z,a-z and special characters . ,  are allowed in Input');
		return false;
	}
}
var date = new Date();
var enrollId='${empHistoryForm.enrollId}';
var ENROLLPRNTID;
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

$(function() {
	$( "#dOs" ).datepicker({
         	defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			numberOfMonths: 1,
			dateFormat : 'dd/mm/yy',
			maxDate: new Date(y, m, d),
			yearRange: '1900:' + new Date().getFullYear()
	});	
});
function fnSearch()
{
	if( document.forms[0].studentId.value=="")
	{  
		alert('Please enter a valid Employee Id / Pensioner Id');
        return false;
    }
	else
	{
	document.forms[0].action="empHistory.do?actionFlag=getEmpDtls";
	document.forms[0].method="";
	document.forms[0].method="post";
	document.forms[0].submit();
	}
}
function fnView(enrollId)
{
		if(document.getElementById("drugInfo").style.display!="none")
		document.getElementById("drugInfo").deleteRow(1);
		var $ = jQuery.noConflict();
		$.post("empHistory.do?actionFlag=getFedBackDtls",{enrollId:enrollId},
		function(data){
					data = data.replace("*","");
					var jsonData = $.parseJSON(data);
					var table=document.getElementById("drugInfo");
       				var newRow=table.insertRow(-1);
       				var newcell=newRow.insertCell(0);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].AADHARID+'</td>';
	           		var newcell=newRow.insertCell(1);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].UNITID+'</td>';
	           		var newcell=newRow.insertCell(2);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].ENROLLPRNTID+'</td>';
	           		var newcell=newRow.insertCell(3);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EMPNAME+'</td>';
	           		var newcell=newRow.insertCell(4);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EHFCARDNO+'</td>';
	           		var newcell=newRow.insertCell(5);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EMPRATNCRDNO+'</td>';
	           		var newcell=newRow.insertCell(6);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].GENDER+'</td>';
	           		var newcell=newRow.insertCell(7);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].ENROLLSTATUS+'</td>';  
	           		var newcell=newRow.insertCell(8);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].RELATION+'</td>';
	           		document.getElementById("drugInfo").style.display="";
	           		document.getElementById("fedSub").style.display="none";
	           		document.getElementById("empFeedBack").style.display="";
	           		enrollId=jsonData.grpList[0].UNITID;
	           		ENROLLPRNTID= jsonData.grpList[0].ENROLLPRNTID;
	           		document.getElementById("preexistDiseases").value=jsonData.grpList[0].PREEXISTDISEASES;
	           		document.getElementById("preexistDiseases").readOnly=true;
	           		document.getElementById("drugscurrent").value=jsonData.grpList[0].DRUGSCURRENT;
	           		document.getElementById("drugscurrent").readOnly=true;
	           		document.getElementById("knownDrugaller").value=jsonData.grpList[0].KNOWNDRUGALLER;
	           		document.getElementById("knownDrugaller").readOnly=true;
	           		document.getElementById("mdclhtry").value=jsonData.grpList[0].MDCLHTRY;
	           		document.getElementById("mdclhtry").readOnly=true;
	           		document.getElementById("anycongenital").value=jsonData.grpList[0].ANYCONGENITAL;
	           		document.getElementById("anycongenital").readOnly=true;
	           		document.getElementById("anysurgerdne").value=jsonData.grpList[0].ANYSURGERDNE;
	           		document.getElementById("anysurgerdne").readOnly=true;
	           		$('#anysurgerdne').attr('disabled',true);
	           		$('#dOs').attr('disabled',true);
	           		if(document.forms[0].anysurgerdne.value=="Yes")
	           			{
	           			document.getElementById("sur").style.display="";
	           			}
	           		else{
	           			document.getElementById("sur").style.display="none";
	           			}
	           			if(jsonData.grpList[0].NOSURGERY.trim()!="undefined" && jsonData.grpList[0].NOSURGERY.trim()!="")
	           			{
		           		document.getElementById("noSurgery").value=jsonData.grpList[0].NOSURGERY;
		           		document.getElementById("noSurgery").readOnly=true;
	           			}
	           			else
	           			{
	           			document.getElementById("noSurgery").value="";
	           			}
	           		
	           			
	           		if(jsonData.grpList[0].DDO.trim()!="undefined" && jsonData.grpList[0].DDO.trim()!="")
	           			{
	           			document.getElementById("dOs").value=jsonData.grpList[0].DDO;
	           			document.getElementById("dOs").readOnly=true;
	           			}
	           	
	           		else
	           			{
	           			document.getElementById("dOs").value="";
	           			}  
	           		
		});
}
function fnUpdate(enrollId)
{
		if(document.getElementById("drugInfo").style.display!="none")
		document.getElementById("drugInfo").deleteRow(1);
		var $ = jQuery.noConflict();
		$.post("empHistory.do?actionFlag=getFedBackDtls",{enrollId:enrollId},
		function(data){
					data = data.replace("*","");
					var jsonData = $.parseJSON(data);
					var table=document.getElementById("drugInfo");
       				var newRow=table.insertRow(-1);
       				var newcell=newRow.insertCell(0);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].AADHARID+'</td>';
	           		var newcell=newRow.insertCell(1);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].UNITID+'</td>';
	           		var newcell=newRow.insertCell(2);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].ENROLLPRNTID+'</td>';
	           		var newcell=newRow.insertCell(3);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EMPNAME+'</td>';
	           		var newcell=newRow.insertCell(4);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EHFCARDNO+'</td>';
	           		var newcell=newRow.insertCell(5);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EMPRATNCRDNO+'</td>';
	           		var newcell=newRow.insertCell(6);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].GENDER+'</td>';
	           		var newcell=newRow.insertCell(7);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].ENROLLSTATUS+'</td>';  
	           		var newcell=newRow.insertCell(8);
	           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].RELATION+'</td>';
	           		document.getElementById("drugInfo").style.display="";
	           		document.getElementById("fedSub").style.display="";
	           		document.getElementById("empFeedBack").style.display="";
	           		 enrollId=jsonData.grpList[0].UNITID;
	           		ENROLLPRNTID= jsonData.grpList[0].ENROLLPRNTID;
	           		document.getElementById("enrollId").value=jsonData.grpList[0].UNITID;
	           		document.getElementById("preexistDiseases").value=jsonData.grpList[0].PREEXISTDISEASES;
	           		document.getElementById("drugscurrent").value=jsonData.grpList[0].DRUGSCURRENT;
	           		document.getElementById("knownDrugaller").value=jsonData.grpList[0].KNOWNDRUGALLER;
	           		document.getElementById("mdclhtry").value=jsonData.grpList[0].MDCLHTRY;
	           		document.getElementById("anycongenital").value=jsonData.grpList[0].ANYCONGENITAL;
	           		document.getElementById("anysurgerdne").value=jsonData.grpList[0].ANYSURGERDNE;
	           		document.getElementById("preexistDiseases").readOnly=false;
	           		document.getElementById("drugscurrent").readOnly=false;
	           		document.getElementById("knownDrugaller").readOnly=false;
	           		document.getElementById("mdclhtry").readOnly=false;
	           		document.getElementById("anycongenital").readOnly=false;
	           		document.getElementById("anysurgerdne").disabled=false;
	           		document.getElementById("noSurgery").readOnly=false;
	           		document.getElementById("dOs").disabled=false;
	           		if(document.forms[0].anysurgerdne.value=="Yes")
	           			{
	           			document.getElementById("sur").style.display="";
	           			}
	           		else{
	           			document.getElementById("sur").style.display="none";
	           			}
	           			/* if(jsonData.grpList[0].NOSURGERY!=null && jsonData.grpList[0].NOSURGERY == null )
	           			{ */
	           				if(jsonData.grpList[0].NOSURGERY.trim()!="undefined")
	           				{
		           				document.getElementById("noSurgery").value=jsonData.grpList[0].NOSURGERY;
	           				}
	           			/* } */
	           			else
	           			{
	           			document.getElementById("noSurgery").value="";
	           			}
	           		
	           			
	           		/* if(jsonData.grpList[0].DDO != null && jsonData.grpList[0].DDO == null)
	           		{ */
	           			if(jsonData.grpList[0].DDO.trim()!="undefined")
	           			{
	           			document.getElementById("dOs").value=jsonData.grpList[0].DDO;
	           			}
	           		/* } */
	           		else
	           			{
	           			document.getElementById("dOs").value="";
	           			}  
	           		
		});
}
function fnSubmit()
{    
	var enrollIdNew = document.getElementById("enrollId").value;
	if(enrollIdNew != '' || enrollIdNew != null)
			enrollId=enrollIdNew;
				if(document.forms[0].preexistDiseases.value==""  &&
	            document.forms[0].drugscurrent.value=="" &&
	            document.forms[0].knownDrugaller.value=="" &&
	            document.forms[0].mdclhtry.value=="" && 
		         document.forms[0].anycongenital.value=="" && 
	            document.forms[0].anysurgerdne.value=="-1" &&
			     document.forms[0].noSurgery.value=="" && 
					document.forms[0].dOs.value=="")
	 	{  
			alert('Please Enter All The Fields');
			return false;
     	}
	  	if(document.forms[0].preexistDiseases.value=="")
        	 {
        	 	alert("Please enter Pre-Existing Diseases");
        	 	document.forms[0].preexistDiseases.focus();
        	 	return false;
        	 }
 	     if(document.forms[0].drugscurrent.value=="")
        	 {
        	 	alert("Please enter Drugs Being Used");
        	 	document.forms[0].drugscurrent.focus();
        	 	return false;
        	 }
         if(document.forms[0].knownDrugaller.value=="")
        	 {
        	 	alert("Please enter Any Allergies after using Drugs");
        	 	document.forms[0].knownDrugaller.focus();
        	 	return false;
        	 }
         if(document.forms[0].mdclhtry.value=="")
        	 {
        	 	alert("Please enter Medical History");
        	 	document.forms[0].mdclhtry.focus();
        	 	return false;
        	 }
         if(document.forms[0].anycongenital.value=="")
    	 	{
    			alert("Please enter Congenital Anomalies");
    			document.forms[0].anycongenital.focus();
   	 			return false;
    	 	}
             if(document.forms[0].anysurgerdne.value=="-1")
        	 {
        	 	alert("Plese select whether previously Surgery Done or Not");
        	 	document.forms[0].anysurgerdne.focus();
        	 	return false;
        	 }
             if(document.forms[0].anysurgerdne.value=="Yes")
            	 {
            	 if(document.forms[0].noSurgery.value=="" || document.forms[0].noSurgery.value==null){
            	 	alert("please enter the Surgery Name");
            	 	document.forms[0].noSurgery.focus();
            	 	return false;
            	 }
            	 if(document.forms[0].dOs.value=="" || document.forms[0].dOs.value==null){
             	 	alert("please enter the Surgery Date");
             	 	document.forms[0].dOs.focus();
             	 	return false;
             	 }
            }
	
		document.forms[0].action="empHistory.do?actionFlag=EhfMedicalHistory&enrollId="+enrollId+"&ENROLLPRNTID="+ENROLLPRNTID;
		document.forms[0].method="post";
		document.forms[0].submit();
}
 function onLoad()
 {
	 var x='${msg}';
	 if(x!="")
	 alert(x);
	 document.getElementById("studentId").value="";
	 
 }
 function fnReset()
 {
 	document.getElementById("preexistDiseases").value="";
 	document.getElementById("drugscurrent").value="";
 	document.getElementById("knownDrugaller").value="";
 	document.getElementById("mdclhtry").value="";
 	document.getElementById("anycongenital").value="";
 	document.getElementById("anysurgerdne").value="-1";
 	document.getElementById("noSurgery").value="";
 	document.getElementById("dOs").value="";
 	document.getElementById("sur").style.display="none";
 }
function fn_employeeDtls(id)
{	
	document.getElementById("preexistDiseases").value="";
 	document.getElementById("drugscurrent").value="";
 	document.getElementById("knownDrugaller").value="";
 	document.getElementById("mdclhtry").value="";
 	document.getElementById("anycongenital").value="";
 	document.getElementById("anysurgerdne").value="-1";
 	document.getElementById("noSurgery").value="";
 	document.getElementById("dOs").value="";
 	document.getElementById("sur").style.display="none";
	document.getElementById("preexistDiseases").readOnly=false;
	document.getElementById("drugscurrent").readOnly=false;
	document.getElementById("knownDrugaller").readOnly=false;
	document.getElementById("mdclhtry").readOnly=false;
	document.getElementById("anycongenital").readOnly=false;
	document.getElementById("anysurgerdne").disabled=false;
	document.getElementById("noSurgery").readOnly=false;
	document.getElementById("dOs").disabled=false;
	
	if(document.getElementById("drugInfo").style.display!="none")
	document.getElementById("drugInfo").deleteRow(1);
	var cardNo=id;
	var $ = jQuery.noConflict();
	$.post("empHistory.do?actionFlag=getEmpFullDtls",{cardNo:cardNo},
	function(data){
		data = data.replace("*","");
		var jsonData = $.parseJSON(data);
				var table=document.getElementById("drugInfo");
           		var newRow=table.insertRow(-1);
           		var newcell=newRow.insertCell(0);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].AADHARID+'</td>';
           		var newcell=newRow.insertCell(1);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].UNITID+'</td>';
           		var newcell=newRow.insertCell(2);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].ENROLLPRNTID+'</td>';
           		var newcell=newRow.insertCell(3);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EMPNAME+'</td>';
           		var newcell=newRow.insertCell(4);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EHFCARDNO+'</td>';
           		var newcell=newRow.insertCell(5);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].EMPRATNCRDNO+'</td>';
           		var newcell=newRow.insertCell(6);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].GENDER+'</td>';
           		var newcell=newRow.insertCell(7);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].ENROLLSTATUS+'</td>';  
           		var newcell=newRow.insertCell(8);
           		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+jsonData.grpList[0].RELATION+'</td>';  
           		document.getElementById("drugInfo").style.display="";
           		document.getElementById("empFeedBack").style.display="";
           		document.getElementById("fedSub").style.display="";
           		enrollId=jsonData.grpList[0].UNITID;
           		ENROLLPRNTID= jsonData.grpList[0].ENROLLPRNTID;
           		document.getElementById("enrollId").value=jsonData.grpList[0].UNITID;
           		
           		
	});
					                	
}
</script>
<style>
 #ui-id-4
 {
  	width:15%; 
 }
 #ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 
 { 
 	width:35%; 
 }
.custom-combobox-input 
{    
	margin: 0;    
	padding: 0.3em; 
	background:#fff;
	border:1px solid #e6e6e6; 
}
body
{
	font-size:1.2em !important;
}
input[type='text'] 
{
    border: 1px solid #ccc;
    border-radius: 3px;
    padding: 2px 3px;
    box-shadow: 1px 1px 5px 1px #ddd inset;
}  
input[type='radio'] {
    position: relative;
}
.cTable td{
    text-align: center;
    border: 1px solid #c5f7de;
}
.tb {
    border: 1px solid #bcd8ff;
    padding: 2px;
}
.tbheader1 {
    background-color: #14a55e;
    height: 100%;
    color: #fff;
    margin: 3px;
    text-align: center;
    padding: 5px 3px;
    border-collapse: collapse;
    }
table {
    background-color: transparent;
    padding: 2px;
}
th {
    text-align: center;
}
#empFeedBack{
	width: 100%;
	margin: 0 auto; 
	border: 1px solid #bcd8ff; 
	padding: 2px;
}
 .but1{
    border: 1px solid #14a55e;
    border-radius: 5px;
    font-weight: bold;
    padding: 3px 15px;
    text-align: center;
    color: #fff;
    background-color: #0c884b;
    background-image: -webkit-linear-gradient(top, #0c884b, #01964d);
}
.tbcellBorder{
	width: inherit;
}
</style>
<body  onload="onLoad();">

<html:form action="/empHistory.do" method="post">
<div>
<table class="tbheader" width="100%">
<tbody><tr><th><b>Patient Details and Treatment History</b></th></tr>
</tbody></table>

<table>
<tr style="text-align:left;">
<td width="25%"></td>
<p>&nbsp;</p>
<td width="20%"><b>Employee Id/Pensioner Id</b></td>
<td width="16%">
<html:text name="empHistoryForm" property="studentId" styleId="studentId" title="Enter EmployeeId/PensionerId"/>
</td>
<td width="20%"><button class="but1" type="button" title="Search Details" onclick="javascript:fnSearch()">Search</button></td>
<td width="20%"></td>
</tr>
</table>
<c:if test="${norec eq 'N' }">
<div>
<table  width="100%" class="tb">
<tr>
<p>&nbsp;</p>
<% 
int i=1; 
%>
<th class="tbheader1" style="width:2%">S.NO</th>
<th class="tbheader1" style="width:8%">Name</th>
<th class="tbheader1" style="width:2%">Gender</th>
<th class="tbheader1" style="width:2%">Age</th>
<th class="tbheader1" style="width:5%">Relation</th>
<th class="tbheader1" style="width:3%">&nbsp;</th>
</tr>
<logic:iterate name="empHistoryForm" property="empList" id="patientVO">
<tr>
<td class="tbcellBorder" align="center"><%=i++ %></td>
<%-- <td class="tbcellBorder" align="center"><bean:write name="patientVO" property="SNO"/></td> --%>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="EMPNAME"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="GENDER"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="COUNT"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="RELATION"/></td>
<td class="tbcellBorder" align="center">
<logic:equal value="" name="patientVO" property="MEDFLG">
<label><input type="button" name="options" class="but1" value="Create" title="Create Feedback Form" onclick = "fn_employeeDtls('<bean:write name="patientVO" property="EHFCARDNO"/>');"></label>
</logic:equal>
<logic:equal value="Y" name="patientVO" property="MEDFLG">
<label><input type="button" name="options" class="but1" value="View" title="Click On View Button To View Submitted Details" onclick="javascript:fnView('<bean:write name="patientVO" property="UNITID"/>')"></label>
<label><input type="button" name="options" class="but1" value="Update" title="Update Your Details" onclick="javascript:fnUpdate('<bean:write name="patientVO" property="UNITID"/>')"></label>
</logic:equal>
</td>
</tr>
</logic:iterate>
</table>
</div>
</c:if>

<table  width="100%" class="cTable"  id="drugInfo" style="display:none;">
<tbody>
<tr>
<p>&nbsp;</p>
<th class="tbheader1" style="width:9%">Aadhar Id</th>
<th class="tbheader1" style="width:6%">Enroll Id</th>
<th class="tbheader1" style="width:10%">Enroll Parent Id</th>
<th class="tbheader1" style="width:15%">Name</th>
<th class="tbheader1" style="width:9%">Card No</th>
<th class="tbheader1" style="width:12%">Ration Card No</th>
<th class="tbheader1" style="width:5%">Gender</th>
<th class="tbheader1" style="width:15%">Status</th>
<th class="tbheader1" style="width:10%">Relation Name</th>
</tr>
</tbody>
</table>
<p>&nbsp;</p>
<table id="empFeedBack" style="display: none;">
<tr ><td class="tbheader1" colspan="6"   width="100%"><b>Employee Feedback Form</b></td></tr>
<tr>
<td width="20%" class="labelheading1 tbcellCss"><b>Pre-Existing Diseases</b><span style="color:red">*</span></td>
<td colspan="1" class="tbcellBorder">
<html:textarea name="empHistoryForm" property="preexistDiseases" styleId="preexistDiseases" style="width:13em;" title="Enter  Pre-Existing Diseases" onchange="checkalphanum(preexistDiseases);trimDot(this);"/>
</td>
<td class="labelheading1 tbcellCss"><b>Drugs Being Used</b><span style="color:red">*</span></td>
<td width="16%" class="tbcellBorder">
<html:textarea name="empHistoryForm" property="drugscurrent" styleId="drugscurrent" style="width:13em;" title="Enter Drugs Being Used" onchange="checkalphanum(drugscurrent);trimDot(this);"/>
</td>
<td class="labelheading1 tbcellCss"><b>Any Allergies to the Drugs</b><span style="color:red">*</span></td>
<td width="16%" class="tbcellBorder">
<html:textarea name="empHistoryForm" property="knownDrugaller" styleId="knownDrugaller" style="width:13em;" title="Enter Any Allergies to the Drugs" onchange="checkalphanum(knownDrugaller);trimDot(this);"/>
</td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Medical History</b><span style="color:red">*</span></td>
<td width="16%" class="tbcellBorder">
<html:textarea name="empHistoryForm" property="mdclhtry" styleId="mdclhtry" style="width:13em;" title="Enter Medical History" onchange="checkalphanum(mdclhtry);trimDot(this);"/>
</td>
<td class="labelheading1 tbcellCss"><b>Any Congenital Anomalies/Infertility</b><span style="color:red">*</span></td>
<td width="16%" class="tbcellBorder">
<html:textarea name="empHistoryForm" property="anycongenital" styleId="anycongenital" style="width:13em;" title="Enter Any Congenital Anomalies/Infertility" onchange="checkalphanum(anycongenital);trimDot(this);"/>
</td>
<td width="15%" class="labelheading1 tbcellCss"><b>Any Surgeries done</b><span style="color:red">*</span></td>
<td class="tbcellBorder">
<html:select name="empHistoryForm" property="anysurgerdne" styleId="anysurgerdne" title="Select Whether Previously Surgery done or not" style="width:13em;" onchange="fn_sel(this)" onmouseover="addTooltip('anysurgerdne')">
<html:option value="-1">---Select---</html:option>
<html:option value="Yes">YES</html:option>
<html:option value="No">NO</html:option>

</html:select>
</td>
</tr>
<tr id="sur" style="display:none">
<td class="labelheading1 tbcellCss"><b>Surgery Name</b><span style="color:red">*</span></td>
<td width="16%" class="tbcellBorder">
<html:textarea name="empHistoryForm" property="noSurgery" styleId="noSurgery" style="width:13em;" title="Enter Surgery Name" onchange="checkalphanum(noSurgery);trimDot(this);"/>
</td>
<td class="labelheading1 tbcellCss"><b>Surgery Date</b><span style="color:red">*</span></td>
<td class="tbcellBorder">
<html:text name="empHistoryForm" property="dOs" styleId="dOs" style="width:13em;" title="Enter Surgery Date"/>
</td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
<tr id="fedSub">
<td colspan="3" align="right"><button class="but" type="button" title="Submit Your Form" onclick="javascript:fnSubmit()">Submit</button></td>
<td colspan="3"><button class="but" type="button" title="Reset Form" onclick="javascript:fnReset()">Reset</button></td>
</tr>
</table>
<c:if test="${norec eq 'Y' }">
<table  width="100%" class="tb1">
<tr>
<td width="30%"></td>
<td><img src="images/norec.png" width="300px" height="300px" border="10"></td>
</tr>
</table>
</c:if>
</div>
<input type="hidden" name="enrollId"  id="enrollId" value="${enrollId}"/>
</html:form>
</body>
<script>


$('#studentId').keypress(function (e) {
    var regex = new RegExp("^[a-zA-Z0-9]+$");
    var str = String.fromCharCode(!e.charCode ? e.which : e.charCode);
    if (regex.test(str)) {
        return true;
    }
    e.preventDefault();
    alert("Special characters or Blank spaces are not allowed");
    return false;
});
function fn_sel(surD){
	if(surD.value=="Yes")
		document.getElementById("sur").style.display="";
	else
		document.getElementById("sur").style.display="none";
}

</script>
</head>
</html>


<!-- buttonImage: "images/calend.gif",
			buttonText: "Calendar",
	        buttonImageOnly: true , 
	        showOn: "both",-->