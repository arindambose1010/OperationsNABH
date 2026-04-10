 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>




<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration"/>
<html>
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
.custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<head>
<title>Insert title here</title>
<script src=js/jquery-1.9.1.min.js></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<script LANGUAGE="JavaScript" SRC="Preauth/maximizeScreen.js"></script>
 <%@ include file="/common/includeCalender2.jsp"%> 
<%@ include file="/common/includePatientDetails.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<!--<script LANGUAGE="JavaScript" SRC="scripts/clinicalNotes.js"></script>-->
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="js/clinicalNotes.js"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
 <script src="js/jquery.ui.datepicker.js"></script> 
<%@ include file="/common/includePatientDetails.jsp"%>  
 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="css/select2.min.css" rel="stylesheet">






	
<script src="js/select2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>

fieldset.scheduler-border {
    border: 1px solid black;
    padding: 0 1.4em 2.4em 2.4em !important;
    margin: 0 0 1.5em 0 !important;
    -webkit-box-shadow:  0px 0px 0px 0px #000;
            box-shadow:  0px 0px 0px 0px #000;
}



legend.scheduler-border {
    border: solid 1px #DDD !important;
    padding: 0 10px 10px 10px;
    border-bottom: none;
    background-color: #1FB5AD !important;
     text-align: left !important;
    color: #fff;
    padding-top: 0.2em;
    padding-bottom: 0.2em;
}

legend.scheduler-border {
    width: auto !important;
    border: none;
    font-size: 14px;
        background-color: #e6b447;
    color: #fff;
}
.coverScreenDiv{
	z-index:100;position:fixed;left:0;top:0;width:100%;height:100%;
	background-color:transparent;
	background: rgba(255,255,255,0.9);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#90FFFFFF,endColorstr=#90FFFFFF); 
	zoom: 1;
	}
</style>

<script type="text/javascript">


 function coverScreen(){
	var jq = jQuery.noConflict();
	jq("<div class='coverScreenDiv' id='csDiv'><div> ").appendTo('body').fadeOut(0).fadeIn(300);
	jq('#csDiv').append('<div id="processImage22" align="center" style="position:relative;float:middle;">	<img src="images/Progress.gif" style="margin-top:200px" border="0" tabIndex="3"></img>	</div>');
	jq('#processImage22').show();
};
//This method removes the above added transparent div.
function removeScreenCover(){
	var jq = jQuery.noConflict();
	jq('#processImage22').hide();
	jq(".coverScreenDiv").fadeOut(300, function(){jq(".coverScreenDiv").remove();});
}; 
/* $(function() {
    $( ".datepicker" ).datepicker();
                 }); */
                 
            
</script>


<script>



/** Days to be disabled as an array */


/* $( "#fromDate" ).datepicker({
	changeMonth: true,
	changeYear: true,
	showOn: "button",
	buttonImage: "calendar-blue.gif",
	buttonImageOnly: true,
	dateFormat : "dd-mm-yy",
	beforeShowDay: onlyFuturedays
});

function onlyFuturedays(date) {   
	var day = date.getDay();   
	var today = new Date();   
	today.setDate(today.getDate()-1);   
	return [(date & gt; today), ''];
} */
function checkFieldNum()
{
	/* 
	date1 = new Date();
	date2 = new Date();
	diff  = new Date(); */

	var frmDt=document.getElementById("fromDate").value;
	var frmDtSP=frmDt.split("-");
	frmDt=frmDtSP[1]+"-"+frmDtSP[0]+"-"+frmDtSP[2];
	
	
	var toDt=document.getElementById("toDate").value;
	var toDtSP=toDt.split("-");
	toDt=toDtSP[1]+"-"+toDtSP[0]+"-"+toDtSP[2];
	
	
	
	var fromDate=new Date(frmDt);

	var toDate = new Date(toDt);

	var timeDiff = Math.abs(fromDate.getTime() - toDate.getTime());
	var diffDays = Math.ceil(timeDiff / (1000 * 3600 * 24)); 
	

	document.getElementById("diffDate").value = diffDays+1;
	
    return true;
	
	}


function validateDate(arg1,input)
{
	
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		jqueryErrorMsg('Date Validation',"Please Select "+arg1);
	}
	else
	{
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+2)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
/* 	if(DoB>DoC)
		{
		input.value="";
		jqueryErrorMsg('Date Validation',arg1+" should not be greater than Today's Date");
		}  */
	}
}
/* function getLeaveDates()
{
	var x=document.getElementById("specialistDoctorName").value;
	var url="./patientDetails.do?actionFlag=getLeaveDates&fromDisp=Y&x="+x;
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
} */
function getLeaveDates()
{
	
	var x=document.getElementById("specialistDoctorName").value;
	if(x!='-1')
		{
	var url="./patientDetails.do?actionFlag=getLeaveDates&fromDisp=Y&x="+x;
	var xmlhttp;
	if(window.XMLHttpRequest)
		xmlhttp=new XMLHttpRequest();
	else if(window.ActiveXObject)
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	else 
		alert('Your browser does not Support XMLHttp(Ajax)');
	
	xmlhttp.onreadystatechange=function()
		{
			if(xmlhttp.readyState==4)
				{
				     var result=xmlhttp.responseText;
				     var data=result.replace("*","");
				 
				  disableddates=data.split(',');
				 
				    /*  document.getElementById("docName").value=data[0];
				     document.getElementById("docMobile").value=data[1];
				     document.getElementById("docWellness").value=data[2].replace("*","");; */
				   
					
				}
		}
	xmlhttp.open("post",url,true);
	xmlhttp.send(null);
	$("#details").show();
	 $("#leavedtls").show();
	/* var url="./patientDetails.do?actionFlag=docLeaveDetails&fromDisp=Y&x="+x;
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit(); */
		}
	else
		{
		 document.getElementById("docName").value="";
	     document.getElementById("docMobile").value="";
	     document.getElementById("docWellness").value="";
	     $("#details").hide();
	     $("#leavedtls").hide();
		}
	}

function fn_submitLeave()
{
	
	if(document.getElementById("specialistDoctorName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("specialistDoctorName"));
	alert("Please select Doctor Name");
	return false;
	}
	if(document.getElementById("fromDate").value==null || document.getElementById("fromDate").value=='')
	{
	var fr=partial(focusBox,document.getElementById("fromDate"));
	alert("Please select From Date");
	return false;
	}
	if(document.getElementById("toDate").value==null || document.getElementById("toDate").value=='' )
	{
	var fr=partial(focusBox,document.getElementById("toDate"));
	alert("Please select To Date");
	return false;
	}
	if(document.getElementById("reason").value==null || document.getElementById("reason").value=='' )
	{
	var fr=partial(focusBox,document.getElementById("reason"));
	alert("Please enter Reason for applying leave");
	return false;
	}
	document.getElementById('Save').disabled=true;
	document.getElementById('processImagetable').style.display="";
	var z=document.getElementById("specialistDoctorName").value;
	var x=document.getElementById("fromDate").value;
	var y=document.getElementById("toDate").value;
	
    
	var url="./patientDetails.do?actionFlag=saveLeaveDetails&fromDisp=Y&x="+x+'&y='+y+'&z='+z;
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
	document.getElementById('processImagetable').style.display="none";
	
	 
}
function getDtlsDoc()
{
    document.getElementById("fromDate").value="";
	document.getElementById("toDate").value=""; 
	document.getElementById("diffDate").value="";
	document.getElementById("reason").value=""; 
	var x=document.getElementById("specialistDoctorName").value;
	if(x!='-1')
		{
	var url="./patientDetailsNew.do?actionFlag=docLeaveDtls&fromDisp=Y&x="+x;
	var xmlhttp;
	if(window.XMLHttpRequest)
		xmlhttp=new XMLHttpRequest();
	else if(window.ActiveXObject)
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	else 
		alert('Your browser does not Support XMLHttp(Ajax)');
	
	xmlhttp.onreadystatechange=function()
		{
			if(xmlhttp.readyState==4)
				{
				     var result=xmlhttp.responseText;
				     var data=result.split("@");
				     document.getElementById("docName").value=data[0];
                     document.getElementById("docMobile").value=data[1];
			    	 document.getElementById("docWellness").value=data[2].replace("*","");;
			    	 
					
				}
		}
	xmlhttp.open("post",url,true);
	xmlhttp.send(null);
	$("#details").show();
    $("#leavedtls").show();
	/* var url="./patientDetails.do?actionFlag=docLeaveDetails&fromDisp=Y&x="+x;
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit(); */
		}
	else
		{
		 document.getElementById("docName").value="";
	     document.getElementById("docMobile").value="";
	     document.getElementById("docWellness").value="";
	
	     $("#details").hide();
	     $("#leavedtls").hide();
		}
	}
/* function getDtlsDoc1()
{
	
	var x=document.getElementById("specialistDoctorName").value;
	
	//var url="./patientDetails.do?actionFlag=docLeaveDtls&fromDisp=Y&x="+x;
	
	
    
	var url="./patientDetails.do?actionFlag=docLeaveDtls1&fromDisp=Y&x="+x;
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
	$("#details").show();
	
	} */

function fn_displayMsg()
	{
		
		var msg='${leaveMsg}';
		if(msg!=null && msg!='')
			{
			alert(msg);
			 document.getElementById("fromDate").value="";
			document.getElementById("toDate").value=""; 
			document.getElementById("diffDate").value="";
			document.getElementById("reason").value=""; 
			document.getElementById("specialistDoctorName").value="-1";
			$("#specialistDoctorName").select2().val("-1");
		
			}
	}
function validateBackSpace(e)
{
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;  
    if( code== 8 )
    {
      e.returnValue=false;
 	 }
}




</script>

</head>
<body onload="fn_displayMsg()">
<form action="/patientDetails.do" method="post">
<table class="tbheader">
<tr><td align="right"><b>Leave Registration</b></td></tr>




 <tr align="center">
<td width="50%"  class="labelheading1 tbcellCss existDrugs"><b>Specialist Doctor</b> <font color="red" >*</font></td>

<td width="50%" align="left" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="specialistDoctorName" styleId="specialistDoctorName" style="width:17em;" title="Select Doctor Name" onchange="javascript:getDtlsDoc(this);getLeaveDates()" >
		<html:option value="-1">---------------Select----------------</html:option>
		<html:options property="ID" collection="dispDoctorNames" labelProperty="VALUE"/> 
</html:select>
</td>
</tr>
<%-- <tr> 
<td width="15%" class="labelheading1 tbcellCss existDrugs"><b>From Date</b></td>
<td width="15%"><html:text name="patientForm" property="fromDate" styleId="fromDate" title="Enter From Date"  onchange="javascript:validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" style="WIDTH: 10em;color:black;"/></td>
<td width="15%" class="labelheading1 tbcellCss"><b>To Date</b></td>
<td width="15%" class="tbcellBorder"><html:text name="patientForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="javascript:validateDate('To Date',this);checkFieldNum()"  onkeydown="validateBackSpace(event)" style="WIDTH: 10em;color:black;"/></td>
 <td width="10%" class="labelheading1 tbcellCss"><b>Days</b></td>
<td width="5%" class="tbcellBorder"><html:text name="patientForm" property="diffDate" styleId="diffDate" title="Enter To Date" readonly="true" style="color:black;"/></td>
  
</tr> --%>
<%-- <td width="16%" class="labelheading1 tbcellCss"><b>Registered From Date</b></td>
<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:110px"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('fromDate','250','480')" title="Calendar"></img> --></td>
		<td width="16%" class="labelheading1 tbcellCss"><b>Registered To Date</b></td>
		<td width="16%" class="tbcellBorder"><html:text name="casesSearchFormClaims" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="width:110px"/><!-- <img border='0' src='images/calend.gif' alt="Calendar" onClick="CalenderWindowOpen('toDate','250','740')" title="Calendar"></img> --></td>
 --%>


<%--  <tr id="details" style="display:none;">
<fieldset style="height:15em;">
 <legend class="legendStyle"><b>Doctor Details</b></legend>

<td>Doctor Name</td><td>:&nbsp;<bean:write name="patientForm" property="leaveDocname" /></td>
<td>Mobile No</td><td>:&nbsp;<bean:write name="patientForm" property="leaveDocMob"/></td>
<td>Wellness center</td><td>:&nbsp;<bean:write name="patientForm" property="wellnessName"/></td>


</fieldset> --%>
<!-- <tr id="details" style="display:none;">  
<form>
 <fieldset>
  <legend class="tbheader">Doctor Details</legend>
  Doctor Name <input type="text" id="docName" readonly="true" ><br>
  Mobile No <input type="text" id="docMobile" readonly="true"><br>
  Wellness Center <input type="text" id="docWellness" readonly="true">
 </fieldset>
</form>
</tr> -->
</table>
<fieldset class="scheduler-border" id="details" style="display:none;">
 <legend class="scheduler-border" >Doctor Details</legend>
    <table>
    <tr>
<td width="12%" class="labelheading1 tbcellCss existDrugs"><b>Doctor Name</b></td>

<td width="16%"><input type="text" id="docName" readonly="true" style="WIDTH: 15em;color:black;" /></td>

<td width="16%" class="labelheading1 tbcellCss existDrugs"><b>Mobile No</b></td>
<td width="16%"><input type="text" id="docMobile" readonly="true" style="WIDTH: 12em;color:black;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>Wellness Center</b></td>
<td width="16%"><input type="text" id="docWellness" readonly="true" style="WIDTH: 12em;color:black;"/></td>
</tr>  
</table>
</fieldset>
<fieldset class="scheduler-border" id="leavedtls" style="display:none;">
 <legend class="scheduler-border" >Leave Dates</legend>
    <table>
    <tr>
<td width="16%" class="labelheading1 tbcellCss existDrugs"><b>From Date</b> <font color="red" >*</font></td>

<td width="16%"><html:text name="patientForm"  property="fromDate" styleId="fromDate" title="Enter From Date"  onchange="javascript:validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" style="WIDTH: 10em;color:black;"/></td>

<td width="16%" class="labelheading1 tbcellCss existDrugs"><b>To Date</b><font color="red" >*</font></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="javascript:validateDate('To Date',this);checkFieldNum()"  onkeydown="validateBackSpace(event)" style="WIDTH: 10em;color:black;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>No of Days</b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm" property="diffDate" styleId="diffDate" title="Enter To Date" readonly="true" style="color:black;"/></td>
  
</tr>  
<tr><td width="16%" class="labelheading1 tbcellCss existDrugs"><b>Reason</b> <font color="red" >*</font></td>
<td width="16%"><html:textarea name="patientForm"  property="reason" styleId="reason" title="Enter Reason"  onkeydown="validateBackSpace(event)" style="WIDTH: 10em;color:black;"/></td>

</tr>
</table>
</fieldset>


<!-- <fieldset class="scheduler-border" id="details" style="display:none;">
 <legend class="scheduler-border" >Doctor Details</legend>
    <table>
    <tr>
<td width="12%" class="labelheading1 tbcellCss existDrugs"><b>Doctor Name</b></td>

<td width="16%"><input type="text" id="docName" readonly="true" style="WIDTH: 15em;color:black;" /></td>

<td width="16%" class="labelheading1 tbcellCss existDrugs"><b>Mobile No</b></td>
<td width="16%"><input type="text" id="docMobile" readonly="true" style="WIDTH: 12em;color:black;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>Wellness Center</b></td>
<td width="16%"><input type="text" id="docWellness" readonly="true" style="WIDTH: 12em;color:black;"/></td>
</tr>  
</table>
</fieldset> -->

<!-- <tr >
<th width="16%" >Doctor Name</th>
<th width="16%">Mobile Number</th>
<th width="16%">Wellness Center</th>
</tr>
<tr>
<td><input type="text" id="docName" readonly="true" /></td>
<td><input type="text" id="docMobile" readonly="true"/></td>
<td><input type="text" id="docWellness" readonly="true"/></td>
</tr> -->





 <table  width="95%" >
<tr>
<td width="20%" align="center">
<button class="btn btn-primary has-spinner" style="text-align:center" type="button" id="Save" onclick="javascript:fn_submitLeave()">Submit&nbsp;</button>
</td>


<!-- <td width="20%" colspan="2">
<button class="btn btn-primary has-spinner" style="text-align:center" type="button" id="Back" onclick="javascript:fn_backDrugs()">Back&nbsp;</button>
</td> -->
</tr>
</table>
<div id="processImagetable" style="top:40%;z-index:50;position:absolute;left:45%;display:none;" align="center">
                <img src="images/Progress.gif" width="100" height="100" border="0" tabIndex="3"></img>
              </div> 

<br><br><br>
<div id="warningData" style="color:red">

</div>


<input type="hidden" id="notify">
<script>

    $("#specialistDoctorName").select2();
    
    
    
    </script>
</form>
</body>
</html>


