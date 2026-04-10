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
<logic:notEqual name="patientForm" property="patientScheme" value="CD502">
<logic:notEqual name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/Printheader_tg.html" %>
</td>
</logic:notEqual>
<logic:equal name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/Printheader_tg.html" %>
</td>
</logic:equal>
</logic:notEqual>
<logic:equal name="patientForm" property="patientScheme" value="CD502">
<logic:notEqual name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/PrintHeaderJouAP.html" %>
</td>
</logic:notEqual>
<logic:equal name="patientForm" property="scheme" value="CD202">
<td>
		<%@ include file="/common/PrintHeaderJouTG.html" %>
</td>
</logic:equal>
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
<tr><th class="tbheader">PATIENT REGISTRATION FORM</th></tr>
<tr><td style="text-align:center;border-bottom:1px solid #c5f7de;"><strong><fmt:message key="EHF.PatientNo"/> : <bean:write name="patientForm" property="patientNo"/></strong></td></tr>
<c:if test="${fromDisp eq 'Y'}">
<c:if test="${aisFlagNew ne 'Y'}">
<tr><td style="text-align:right;border-bottom:1px solid #c5f7de;">Token No:&nbsp; <strong style="font-size: 35px;"><bean:write name="patientForm" property="tokenNo"/></strong></td></tr>
<%-- <tr><td style="text-align:right;border-bottom:1px solid #c5f7de;"> Room No:&nbsp;<c:if test="${patientForm.roomNo eq '-1'}"  ><b>NA</b></c:if> <c:if test="${patientForm.roomNo ne '-1'}"  ><strong style="font-size: 15px;"><bean:write name="patientForm" property="roomNo"/></strong></c:if></td></tr> --%>
</c:if>
</c:if>
<!--  personal details -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>PERSONAL DETAILS</b></td></tr>
<tr><td>
		 <table width="100%" cellspacing="2px" cellpadding="2px" class="tb">
			<tr height="45px"><td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.HealthCardNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="cardNo"/></td>
		<%-- <td width="25%"><b><fmt:message key="EHF.CardIssueDate"/>:&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td> --%>
				<td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Name"/>:</b></span>&nbsp;<bean:write name="patientForm" property="fname" /></td>
				<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Gender"/>:</b></span>&nbsp;<bean:write name="patientForm" property="gender"/></td>
				<td width="25%" rowspan="3" style="text-align:center" class="tbcellCss">
				 <logic:notEmpty name="patientForm" property="photoUrl">
				<img src="common/showDocument.jsp" width="150" height="120" alt="NO DATA"/>
				</logic:notEmpty>
				<logic:empty name="patientForm" property="photoUrl">
				<img src="images/photonot.gif" width="150" height="120" alt="NO DATA"/>
				</logic:empty>
			</td>
		</tr>
		<tr height="45px">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Age"/>:</b></span>&nbsp;<bean:write name="patientForm" property="years"/>Y
		 		<bean:write name="patientForm" property="months"/>M
				<bean:write name="patientForm" property="days"/>D</td>
			<c:if test="${aisFlagNew ne 'Y'}">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Relationship"/>:</b></span>&nbsp;<c:if test="${patientForm.relation eq 'New Born Baby'}"  ><b></c:if><bean:write name="patientForm" property="relation" /></td>
			</c:if>
			<c:if test="${aisFlagNew eq 'Y'}">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b></b></span></td>
			</c:if>
			<c:if test="${aisFlagNew ne 'Y'}">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Slab"/>:</b></span>&nbsp;<bean:write name="patientForm" property="slab"/></td>
			</c:if>
			<c:if test="${aisFlagNew eq 'Y'}">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b></b></span></td>
			</c:if>
		</tr>
		<tr height="45px">
		<!-- <td class="tbcellCss"><b><fmt:message key="EHF.Caste"/>:</b>&nbsp;<bean:write name="patientForm" property="caste"/></td> -->
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Designation"/>:</b></span>&nbsp;<bean:write name="patientForm" property="occupation"/></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.ContactNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="contactno"/></td>
			<c:if test="${aisFlagNew eq 'Y'}">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Email Id:</b></span>&nbsp;<bean:write name="patientForm" property="eMailId"/></td>
			</c:if>
		</tr>
		</table>
</td></tr>
<!--  end of personal details -->
<!--  card address -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>CARD ADDRESS</b></td></tr>
<tr><td>
	<table width="100%" height="80px" class="tb">
	<tr>
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.HouseNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="hno"/></td> 
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Street"/>:</b></span>&nbsp;<bean:write name="patientForm" property="street"/></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.District"/>:</b></span>&nbsp;<bean:write name="patientForm" property="district" /></td>
	</tr>
	<tr>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Mdl/Mcl"/>:</b></span>&nbsp;<bean:write name="patientForm" property="mandal" /></td>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Village"/>:</b></span>&nbsp;<bean:write name="patientForm" property="village" /></td> 
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Pin"/>:</b></span>&nbsp;<bean:write name="patientForm" property="pin" /></td>
	</tr>
	</table>
</td></tr>
<!--  end of card address -->
<!-- communication address -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>COMMUNICATION ADDRESS</b></td></tr>
<tr><td>
	<table width="100%" height="80px" class="tb">
	<tr>
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.HouseNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_hno" /></td> 
	<td width="35%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Street"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_street" /></td>
	<td width="30%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.District"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_dist" /></td>
	</tr>
	<tr>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Mdl/Mcl"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></td>
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Village"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_village" /></td> 
	<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Pin"/>:</b></span>&nbsp;<bean:write name="patientForm" property="comm_pin" /></td>
	</tr>
	</table>
</td></tr>
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
	<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.DateAndTime"/>:</b></span>&nbsp;<bean:write name="patientForm" property="dtTime"/></td>
	</tr>
	</table>
</td></tr>
<tr><td>
<c:if test="${fromDisp eq 'Y'}">
		<%-- <td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b> Speciality Type:</b></span>&nbsp;<bean:write name="patientForm" property="specialityType"/></td> --%>
<table width="100%" style="margin:auto;text-align:center">
<logic:present name="patientForm" property="specLt">
     <logic:notEmpty name="patientForm" property="specLt" >
     
     <tr>
		<th class="tbheader1" width="12%">S.No</th>
		<th class="tbheader1" width="25%">Speciality Type</th>
		<th class="tbheader1" width="10%">Room No</th>		
	</tr>
	<bean:size  id="specSize" name="patientForm" property="specLt"/>
        <logic:greaterThan value="0" name="specSize">
		<%int k = 1;%>
        <logic:iterate id="specLst" name="patientForm" property="specLt" indexId="index"  >
        <tr>  
      	<td width="1%"><b>${index+1}</b></td> 
       	<td width="10%"><b>
       	<%-- <c:if test="${specLst.SPECIALITYTYPE eq 'GP811'}"  >Physiotherapy</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP790'}"  >General Physician</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP795'}"  >Gynaecologist</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP791'}"  >Ayurveda</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP792'}"  >Homeopathy</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP793'}"  >Unani</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP796'}"  >Dental</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP798'}"  >Ophthalmology</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP799'}"  >Pediatrics</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP800'}"  >Yoga & Naturopathy</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP802'}"  >Cardiologist</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP803'}"  >Orthopedician</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP804'}"  >Diabetologist</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP806'}"  >Neurology</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP807'}"  >Pulmanology</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP808'}"  >Oncology</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP809'}"  >ENT</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP810'}"  >Nephrology</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP812'}"  >Medical Officer</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP814'}"  >Radiology</c:if>
       	<c:if test="${specLst.SPECIALITYTYPE eq 'GP794'}"  >General Surgeon</c:if> --%>
       	<bean:write name="specLst" property="SPECIALITYNAME" /> <input type="hidden" id="speciality${index+1}" value=<bean:write name="specLst" property="SPECIALITYNAME" />> 
       	</b></td>
       	<td width="5%"><b>
       	<c:if test="${specLst.ROOMNO eq '-1'}"  >NA</c:if> 
       	<c:if test="${specLst.ROOMNO ne '-1'}"  ><bean:write name="specLst" property="ROOMNO" /></c:if>
       	<%-- <bean:write name="specLst" property="ROOMNO" /><input type="hidden" id="room${index+1}" value=<bean:write name="specLst" property="ROOMNO" />>   --%> 
       	</b></td>
       	</logic:iterate>
       	</logic:greaterThan>
		</logic:notEmpty>
									
      <logic:empty name="patientForm" property="specLt" >
      <tr>
      <td align="center" style="color:#434645">
      <i class="fa fa-user-times"></i>&nbsp;<b>No Past History Found</b>
      </td>
      </tr>
      </logic:empty>
      </logic:present>
      </table>	
</c:if>
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
 </c:if> 

<table width="100%" style="margin:0 auto" class="tb print_table">
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