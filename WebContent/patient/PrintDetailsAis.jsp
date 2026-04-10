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
<title>AIS PRINT FORM</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js"></script>

<script type="text/javascript" src="js/jquery.min.js"></script>
<style>
@page { size: auto;  margin: 0mm; }
</style>
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
<table width="100%" style="margin:0 auto" class="tb print_table">
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
</tr>
<tr><th class="tbheader">AIS/LEGISLATIVE REGISTRATION FORM</th></tr>
<tr><th>&nbsp;</th></tr></tr>
<tr><td style="text-align:center;"><strong><fmt:message key="EHF.PatientNo"/> : <bean:write name="patientForm" property="patientNo"/></strong></td></tr>
<tr><th>&nbsp;</th></tr></tr>
<!--  personal details -->
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>PERSONAL DETAILS</b></td></tr>
<tr><td>
		 <table width="100%" cellspacing="2px" cellpadding="2px" class="tb">
			<tr height="45px">
				<td width="25%" class="tbcellCss print_cell"><span class="labelheading1"><b>AIS/LEGISLATIVE ID:</b></span>&nbsp;<bean:write name="patientForm" property="cardNo"/></td>
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
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Marital Status:</b></span>&nbsp;<bean:write name="patientForm" property="maritalStatus"/></td></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.Relationship"/>:</b></span>&nbsp;<bean:write name="patientForm" property="relation" /></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Service Type:</b></span>&nbsp;<bean:write name="patientForm" property="serType"/></td>
		</tr>
		<tr height="45px">
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>Email Id:</b></span>&nbsp;<bean:write name="patientForm" property="eMailId"/></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b><fmt:message key="EHF.ContactNo"/>:</b></span>&nbsp;<bean:write name="patientForm" property="contactno"/></td>
			<td class="tbcellCss print_cell"><span class="labelheading1"><b>In Service:</b></span>&nbsp;<bean:write name="patientForm" property="servType"/></td>
		</tr>
		</table>
</td></tr>
<tr><td class="tbheader print_heading" style="text-align:left;padding-left:10px"><b>REGISTERED HOSPITAL & PACKAGE DETAILS</b></td></tr>
<tr><td>
	<table width="100%"  height="40px" class="tb">
	<tr>
		<c:if test="${fromDisp eq 'Y'}">
		<!-- <td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Referral Center:</b></span>&nbsp;NIMS HOSPITAL</td>
		<td width="50%" class="tbcellCss print_cell"><span class="labelheading1"><b>Referral Contact:</b></span>&nbsp;040-23489022</td>
		 -->
		 
		  <td width="50%" class="tbcellCss print_cell">
			    <span class="labelheading1"><b>Referral Center:</b></span>&nbsp;
			    <c:choose>
			        <c:when test="${param.nimsAyush ne 'Y'}">
			            NIMS HOSPITAL
			        </c:when>
			        <c:otherwise>
			             AYUSH SERVICES NIMS
			        </c:otherwise>
			    </c:choose>
			</td>
			
			<td width="50%" class="tbcellCss print_cell">
			    <span class="labelheading1"><b>Referral Contact:</b></span>&nbsp;040-23489022
			</td>
		  
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
</table>
<table>
<tr>
<c:if test="${param.nimsAyush ne 'Y'}">
<td class="tbcellCss"><img src="images/MHC.jpg" style="width:100%; height:1000px;"></img></td>
</c:if>
</tr>
</table>
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