<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
  	 <%@ include file="/common/include.jsp"%>
  	 
  	 <fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>
<style>
@page { size: auto;  margin: 0mm; }
</style>
<script type="text/javascript" src="js/jquery.min.js"></script>
 <script language="javascript" type="text/javascript">	
 var s$=jQuery.noConflict();

 s$(function(){
    /*
     * this swallows backspace keys on any non-input element.
     * stops backspace -> back
     */
    var rx = /INPUT|SELECT|TEXTAREA/i;

    s$(document).bind("keydown keypress", function(e){
        if( e.which == 8 ){ // 8 == backspace
            if(!rx.test(e.target.tagName) || e.target.disabled || e.target.readOnly ){
                e.preventDefault();
            }
        }
    });
 });
 
 
function fn_Print()
{
window.print();
}
function closeNrefresh()
{
	window.opener.close();
	window.close();
}
</script>
</head>
<body>
<form action="/patientDetails.do" method="post" name="printForm">
<table width="95%" style="margin:0 auto" class="tb print_table">
<tr>

<logic:equal name="patientForm" property="scheme" value="CD202">
<br>
<td align="center"><br>
<h2>INTEGRATED NIMS WELLNESS CENTER</h2><br></td>

</logic:equal>



<!-- <td><table  style="width:100%;margin:0 auto"> -->
<!-- 	       <tr><td> -->
<!-- 	         <table  style="width:80%;margin:0 auto" > -->
<!-- 			<tr  class="screen_header"><td width="10%" style="text-align:right;"><img border='0' src="images/telanganalogo.png"/></td> -->
<!-- 				<td width="90%" style="font-size:22px;text-align:center;"><span style="font-size:14px;"></span><br />TELANGANA GOVERNMENT EMPLOYEES AND JOURNALISTS HEALTH SCHEME</td> -->
<!-- 				<td width="15%" style="text-align:left;"><img border='0' src="images/telangana_cm_right.png"/></td> -->
<!-- 				<td width="18%" style="font-size:22px;text-align:center;">&nbsp;</td> -->
<!-- 			</tr> -->
<!-- 			</table> -->
<!-- 			</td></tr> -->
<!-- 			<tr><td style="text-align:center"> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  &nbsp &nbsp &nbsp &nbsp &nbsp -->
<!-- 		D.No. 8-2-293/82a/ahct, Road No: 46, Jubilee Hills,Hyderabad - 500033 <br /> -->
<!-- 	 &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp 	Contact : 040-23547107, FAX NO : 040-23555657 -->
<!-- 	</td></tr> -->
<!-- 		</table> -->
<!-- </td> -->

</tr>

<!-- <tr><th class="tbheader">AIS REGISTRATION FORM</th></tr> -->
<%-- <tr><td style="text-align:center;border-bottom:1px solid #c5f7de;"><strong><fmt:message key="EHF.PatientNo"/> : <bean:write name="patientForm" property="patientNo"/></strong></td></tr>
 --%>
<!--  personal details -->
<tr ><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>PERSONAL DETAILS</b></td></tr>
<tr><td>
		 <table width="100%" cellspacing="2px" cellpadding="2px" class="tb">
			<tr height="45px"><td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>Employee Id:</b></span>&nbsp;<bean:write name="patientForm" property="cardNo"/></td>
		<%-- <td width="25%"><b><fmt:message key="EHF.CardIssueDate"/>:&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td> --%>
				<td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>Employee Name:</b></span>&nbsp;<bean:write name="patientForm" property="fname" /></td>
			
		</tr>
			<tr height="45px"><td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>Pickup Location:</b></span>&nbsp;<bean:write name="patientForm" property="pickupName"/></td>
		<%-- <td width="25%"><b><fmt:message key="EHF.CardIssueDate"/>:&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td> --%>
				<td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>Month/Year of Prescription:</b></span>&nbsp;<bean:write name="patientForm" property="months" />/<bean:write name="patientForm" property="years" /></td>
			
		</tr>
	
	
	
		</table>
</td></tr>
<table width="95%" style="margin:0 auto" class="tb print_table">
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>ISSUED DRUGS</b></td></tr>
</table>
<table width="95%" style="margin:0 auto" class="tb print_table">
<tr>
<th width="10%" class="labelheading2 tbcellCss"><b>SNO</b></th>
<th width="20%" class="labelheading2 tbcellCss"><b>DRUG TYPE</b></th>
<th width="20%" class="labelheading2 tbcellCss"><b>DRUG NAME</b></th>
<th width="10%" class="labelheading2 tbcellCss"><b>ISSUED QUANTITY</b></th>
<!-- <th width="20%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b></th>
<th width="20%" class="labelheading1 tbcellCss"><b>Distributer Name</b></th> -->
<!-- <th width="10%" class="labelheading2 tbcellCss"><b>AVAILABLE QUANTITY</b></th> 
 --><c:if test="${saveFlag eq 'Y'}">
<th width="10%" class="labelheading2 tbcellCss"><b>ISSUED QUANTITY</b></th> 
</c:if>
<!-- <th width="10%" class="labelheading2 tbcellCss"><b>DISPATCHING QUANTITY</b></th> 
 -->
<!-- <th width="10%" class="labelheading2 tbcellCss"><b></b></th> 
 -->
</tr>
<logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" indexId="index"  >
        <tr>  
      	<td width="5%">${index+1}</td>        
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPENAME" /><input type="hidden" id="drugType${index+1}" value=<bean:write name="drugLst" property="DRUGTYPECODE" />></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGSUBTYPENAME" /> <input type="hidden" id="drugName${index+1}" value=<bean:write name="drugLst" property="DRUGSUBTYPECODE" />> </td>
       	<td width="5%"><bean:write name="drugLst" property="QUANTITY" /> <input type="hidden" id="quant${index+1}" value=<bean:write name="drugLst" property="QUANTITY" />> </td> 
<%--        	<td width="5%"><bean:write name="drugLst" property="quantity" /><input type="hidden" id="avlQuant${index+1}" value=<bean:write name="drugLst" property="quantity" />> </td> 
 --%>        <c:if test="${saveFlag eq 'Y'}">
		<td width="5%"><bean:write name="drugLst" property="issuedQuant" /><input type="hidden" id="issuedQuant${index+1}" value=<bean:write name="drugLst" property="issuedQuant" />> </td> 
		</c:if>
       <%-- 	<td width="10%">
       	<html:select name="drugLst" property="mftrLst" styleId="mftrLst" style="width:12em;" title="Select Manufacturer" onchange="getDstbtr(this.value,${index+1})" >
        <logic:present name="drugLst" property="mftrs">
         <c:if test="${fn:length(drugLst.mftrs) gt 0}">
        <html:option value="Select">Select</html:option>
        <html:optionsCollection name="drugLst" property="mftrs" label="mftrName" value="mftrId" />
        </c:if>
        </logic:present>
        </html:select>
        <input type="hidden" id="mftr${index+1}" >
       	</td>
       	<td width="10%">
       	<select name="drugLst"  id="dbtrLst${index+1}" style="width:12em;" title="Select Manufacturer" onchange="getQuant(this.value,${index+1})" >
        </select>
        <input type="hidden" id="dbtr${index+1}"/> 
       	</td>
       	<td width="5%"><input type="text" id="avlQuant${index+1}" > 
       	<input type="hidden" id="drgId${index+1}" > 
       	</td> --%>
<%--        	<td width="5%"><input type="text" id="prQuant${index+1}" value='<bean:write name="drugLst" property="QUANTITY" />'> </td>
 --%><%--        <td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name='drugLst' property='DRUGID' /> id=<bean:write name='drugLst' property='DRUGID' /> onclick="javascript:confirmRemoveRowNims('<bean:write name='drugLst' property='DRUGID' />');" /></td>
 --%>       	
        </tr>
         <c:set var = "drgCount" scope = "session" value = "${index+1}"/>
        </logic:iterate></logic:greaterThan></logic:present>  

</table>
<%-- <!--  end of personal details -->
<!--  card address -->

<!--  end of card address -->
<!-- communication address -->

<!-- end of communication address -->
<!--registered hospital details  -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>REGISTERED HOSPITAL DETAILS</b></td></tr>
<c:if test="${aisFlagNew eq 'N'}">
<tr><td>
	<table width="100%"  height="40px" class="tb">
	<tr>
	<c:if test="${fromDisp eq 'N'}">
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.RegisteredHospital"/>:</b></span>&nbsp;<bean:write name="patientForm" property="hospitalName"/></td>
		</c:if>
		<c:if test="${fromDisp eq 'Y'}">
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Referral Center:</b></span>&nbsp;<bean:write name="patientForm" property="hospitalName"/></td>
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Referral Center Contact:</b></span>&nbsp;<bean:write name="patientForm" property="hospContactNo"/></td>
		</c:if>
	
	</tr>
	<tr>
	<c:if test="${fromDisp eq 'Y'}">
	
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b> Speciality Type:</b></span>&nbsp;<bean:write name="patientForm" property="specialityType"/></td>	
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b> Room No:</b></span>&nbsp;<bean:write name="patientForm" property="roomNo"/></td>	
	</c:if>
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.DateAndTime"/>:</b></span>&nbsp;<bean:write name="patientForm" property="dtTime"/></td>
	</tr>
	</table>
</td></tr>
</c:if>
<c:if test="${aisFlagNew eq 'Y'}">
<tr><td>
	<table width="100%"  height="40px" class="tb">
	<tr>
		<c:if test="${fromDisp eq 'Y'}">
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Referral Center:</b></span>&nbsp;NIMS HOSPITAL</td>
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Referral Contact:</b></span>&nbsp;040-23489022</td>
		
		</c:if>
	</tr>
	<tr>
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.DateAndTime"/>:</b></span>&nbsp;<bean:write name="patientForm" property="dtTime"/></td>
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Health CheckUp Type:</b></span>&nbsp;<bean:write name="patientForm" property="ayush"/></td>
	</tr>
	<tr>
	<logic:notEmpty name="patientForm" property="dosage">
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Package Type:</b></span>&nbsp;<bean:write name="patientForm" property="dosage"/></td>
		</logic:notEmpty>
		<c:if test="${ayushName ne null && ayushName ne ''}">
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Sub Category:</b></span>&nbsp;<bean:write name="patientForm" property="ayushID"/></td>
		</c:if>
	</tr>
	</table>
</td></tr>
</c:if>
</table>

<c:if test="${fromDisp eq 'Y'}">
<c:if test="${aisFlagNew eq 'Y'}"> 
<table>
<tr>
<td class="tbcellCss"><img src="images/MHC.jpg" style="width:100%; height:1000px;"></img></td>
</tr>
</table>
</c:if>
 </c:if>  --%>

<table width="95%" style="margin:0 auto" class="tb print_table">
<tr class="print_buttons">
<td style="text-align:center">
<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
</tr>
</table>
</form>
</body>
</html>
</fmt:bundle>