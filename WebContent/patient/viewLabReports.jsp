<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/common/include.jsp"%> 
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<HTML>
<title>Lab Reports Page</title>
	<HEAD>
	
	<script src="js/jquery-1.9.1.min.js"></script>
	 <LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<style type= "text/css">



</style>

</HEAD>
<script language="javascript">

var jq = $.noConflict();
jq(document).ready(function(){
	
	var isSubmitted = "${isSubmitted}";
	var investigatnId = "${patientForm.investigationSel}";

	if(isSubmitted == 'true')
	{
		window.opener.$('#lbl'+investigatnId).css("color","#0000FF");
	}
	
});

function fn_Print() {
	window.print();
}

function closeNrefresh()
{
	window.close();
}


</script>
<fmt:bundle basename="Registration">
<body style="width:100%;">
<html:form action="/patientDetails.do" method="post">
	<table width="90%" style="margin: 0 auto" class="tb print_table">
<tr><td>
	<table style="width:100%;margin:auto;">
	       <tbody><tr><td>
	         <table style="width:80%;margin:0 auto">
			<tbody><tr class="screen_header"><td width="15%" style="text-align:right;"><img border="0" src="images/telanganalogo.png"></td>
				<td width="70%" style="font-size:22px;text-align:center;"><!-- <span style="font-size:14px;"></span><br /> -->TELANGANA GOVERNMENT 
				<br>EMPLOYEES AND JOURNALIST HEALTH SCHEME</td>
			 	 <td width="15%" style="text-align:left;"><img border="0" src="images/telangana_cm_right.png"></td>  
				<!-- <td width="18%" style="font-size:22px;text-align:center;">&nbsp;</td> -->
			</tr>
			
			<tr class="print_header"><td width="15%" style="text-align:right;"><img border="0" src="images/telanganalogo.png"></td>
				<td width="70%" style="font-size:22px;text-align:center;"><!-- <span style="font-size:14px;"></span><br /> -->TELANGANA GOVERNMENT 
				<br>EMPLOYEES AND JOURNALIST HEALTH SCHEME</td>
				  <td width="15%" style="text-align:left;"><img border="0" src="images/telangana_cm_right.png"></td>  
				<!-- <td width="18%" style="font-size:22px;text-align:center;">&nbsp;</td> -->
			</tr>
			</tbody></table>
			</td></tr>
			<!--<tr><td style="text-align:center">
		D.No. 8-2-293/82a/ahct, Road No: 46, Jubilee Hills,Hyderabad - 500033 <br>
		Contact : 040-23547107, FAX NO : 040-23555657
	</td></tr>-->
		</tbody></table>
	<table width="100%">
		<tr class="tbheader print_heading">
	 			<td><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></td>
		</tr>
	</table>
	<table width="100%" align="center">	
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.Name"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="patientName" /></b></td>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.PatientNo"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
		</tr>

		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.Employee/PensionerCardNo"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="employeeNo"/></b></td>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.HealthCardNo"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
		</tr>

		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.Gender"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.District"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="district"/></b></td>
		</tr>

		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.DateOfBirth"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="dateOfBirth"/></b></td>
				<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.Age"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="years"/>Y
	 				<bean:write name="patientForm" property="months"/>M
					<bean:write name="patientForm" property="days"/>D</b>
			</td>
		</tr>

		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.Relationship"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="relation"/></b></td>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b><fmt:message key="EHF.ContactNo"/></b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="contactno"/></b></td>
		</tr>
		
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%"><b>Registration date and time</b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="dtTime"/></b></td>
				<td class="labelheading1 tbcellCss print_cell" width="15%"><b>Wellness Center Name</b></td>
			<td class="tbcellBorder print_cell" width="35%"><b>&nbsp;<bean:write name="patientForm" property="hospitalName"/></b></td>
		</tr>

	</table>

		<table border="0" width="100%" align="center" >
			<tr class="tbheader print_heading"><td><bean:write name="patientForm" property="investigations"/></td></tr>
		</table>
	<table border="0" width="100%" align="center" >
		<tr class="tbheader print_heading">
			<td style=""><b>Description of the field</b></td>
			<td style=""><b>Result</b></td>
			<td style=""><b>Reference Interval</b></td>
			<!-- <td style=""><b>Method</b></td>-->
		</tr>
		
		<logic:iterate id="labelsItem" name="patientForm" property="labelsList" indexId="cnt">
			<tr class="legendStyle">
					<td width="30%" class="labelheading1 tbcellCss print_cell"><b><bean:write name="labelsItem" property="INVESTIGATIONNAME"/></b></td>
					<td width="30%" class="labelheading1 tbcellCss print_cell" align="center"><bean:write name="labelsItem" property="VALUEOFFIELD"/></td>					
					<td width="30%" class="labelheading1 tbcellCss print_cell"><b><bean:write name="labelsItem" property="REFERENCEINTERVAL"/></b></td>
					<!--<td><b><bean:write name="labelsItem" property="METHOD"/></b></td>-->								
			</tr>
						
		</logic:iterate>
			
			
	</table>
	<table style="width:100%;align:center; margin-top:15px">
		<tr>
			<td class="labelheading1 tbcellCss print_cell" width="15%" height="70"><b>Checked By</b></td>
			<td class="tbcellBorder print_cell" width="35%" height="70"></td>
				<td class="labelheading1 tbcellCss print_cell" width="15%" height="70"><b>Authorized By</b></td>
			<td class="tbcellBorder print_cell" width="35%" height="70"></td>
		</tr>
	</table>
	<table style="width:100%;align:center">
	<tr class="print_buttons">
			<td align="center">
				<button class="but" type="button" id="Print" onclick="fn_Print();">Print</button>
				<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
			</td>
		</tr>	
		</table>	
	</td></tr>
	</table>		
</html:form>
</body>
</fmt:bundle>
<footer style="position:fixed;bottom:0px !important;width: 100%;">
<div id="footer"><span class="" style="position:absolute;left:10px">Employees Health Scheme </span>
<span class="" style="position:absolute;right:10px">© 2014 All Rights reserved. Aarogyasri Health Care Trust</span>
<span class="clearfix"></span></div>
</footer>

</HTML>