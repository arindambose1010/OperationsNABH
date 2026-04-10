<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.List,com.ahct.common.vo.LabelBean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-nested.tld" prefix="nested"%>
<%@ include file="/common/include.jsp"%>
<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EHS::Patient Diagnosis Form</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script language="javascript" type="text/javascript">
	var s$ = jQuery.noConflict();

	s$(function() {
		/*
		 * this swallows backspace keys on any non-input element.
		 * stops backspace -> back
		 */
		var rx = /INPUT|SELECT|TEXTAREA/i;

		s$(document).bind(
				"keydown keypress",
				function(e) {
					if (e.which == 8) { // 8 == backspace
						if (!rx.test(e.target.tagName) || e.target.disabled
								|| e.target.readOnly) {
							e.preventDefault();
						}
					}
				});
	});

	function fn_Print() {
		window.print();
	}
		
	function closeNrefresh()
	{
		window.close();
	}
</script>
<body>
	<form action="/patientDetails.do" method="post"
		name="caseGeneratedForm">
		<table width="90%" style="margin: 0 auto" class="tb print_table">
		<tr>
		<logic:notEqual name="patientForm" property="patientScheme" value="CD502">
		
			<logic:notEqual name="patientForm" property="scheme" value="CD202">
				<td>
						<%@ include file="/common/Printheader.html" %>
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
		<tr>
		<c:choose>
				<c:when test="${fromDisp eq 'Y'}">
				<th class="tbheader">REFERRAL LETTER FROM WELLNESS CENTER</th>
				</c:when>
				<c:otherwise>
				<tr><th class="tbheader">DIAGNOSTIC TEST REQUISITION SLIP</th>
				</c:otherwise>
		</c:choose>
		</tr>
		<c:if test="${fromDisp eq 'Y'}">
		<tr><td style="text-align:right;border-bottom:1px solid #c5f7de;">Lab Token No:&nbsp; <strong style="font-size: 35px;"><bean:write name="patientForm" property="labTokenNo"/></strong></td></tr>			
		</tr>
		</c:if>
		<tr><td style="text-align:left;" class="tbheader print_heading" ><b>1.&nbsp;Patient&nbsp;Details</b></td></tr>
		<!-- Patient details -->
		<tr><td>
		    <table width="100%">
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Health Card No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="cardNo" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patientNo" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Name</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="patientName" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Date</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dateIp" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="ageString" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="gender" /></td>
			</tr>
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="addr" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Contact No</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="contactno" /></td>
			</tr>
			<logic:notEmpty name="patientForm" property="newBornBaby">
				<logic:equal name="patientForm" property="newBornBaby" value="Y">
					<tr>
						<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Patient Type</strong></td>
						<td width="35%" class="tbcellBorder print_cell"><b>New Born Baby</b></td>
						<td width="50%">&nbsp;</td>	
					</tr>
				</logic:equal>
			</logic:notEmpty>
		</table>
		</td></tr>
		<!--  Case details -->
		<tr><td style="text-align:left;" class="tbheader print_heading"><b>2.&nbsp;Case&nbsp;Details</b></td></tr>
		<tr><td>
			<table width="100%">
			
			<tr>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Registration date and time</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="dtTime" /></td>
				<c:choose>
					<c:when test="${fromDisp eq 'Y'}">
					<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Wellness Center Name</strong></td>
					</c:when>
					<c:otherwise>
					<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Hospital Name</strong></td>
					</c:otherwise>
				</c:choose>
				
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="hospitalName" /></td>
			</tr>
			<tr>
			<c:choose>
					<c:when test="${fromDisp eq 'Y'}">
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Wellness Center Doctor name</strong></td>
					</c:when>
					<c:otherwise>
					<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Medco name</strong></td>
					</c:otherwise>
				</c:choose>
			
				
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="doctorName" /></td>
				<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Slab Eligible</strong></td>
				<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="slab" /></td>
			</tr>
			<tr>
			<c:if test="${fromDisp eq 'Y'}">
			<td width="15%" class="labelheading1 tbcellCss print_cell"><strong>Wellness Center Contact No.</strong></td>
			<td width="35%" class="tbcellBorder print_cell"><bean:write name="patientForm" property="hospContactNo" /></td>
			</c:if></tr>
		</table>
		</td></tr>
		<!-- Medical details -->
		<tr><td style="text-align:left" class="tbheader print_heading"><b>3.&nbsp;Medical&nbsp;Details</b></td></tr>
			<tr>
<td colspan="4" class="labelheading1 tbcellCss"><b>Medical History</b></td></tr>
			<tr><td valign="top" colspan="2" class="tbcellBorder">
<table style="width:100%;" id="medicalhistorytable">
<logic:iterate id="medicalDtls" name="patientForm" property="medicalhsitorydtls" indexId="i">
	 <tr><td style="width:20%;" class="labelheading1 tbcellCss">&nbsp;&nbsp;
      		 <bean:write name="medicalDtls" property="VALUE"/>
      		 </td>
       	<td class="tbcellBorder">
<%--        	<html:textarea name="patientForm" property="textVal${patientForm.medicalhsitorydtls[i].ID}" styleId="textVal${patientForm.medicalhsitorydtls[i].ID}" title="Enter Remarks" style="width:100%;font-weight:normal;"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medical History');blockConsecutiveChars('Medical History',this,this.value)"/> --%>
	<bean:write name="patientForm" property="textVal${patientForm.medicalhsitorydtls[i].ID}"/>
		</td>
       	</tr>
       	</logic:iterate>
        </table></td></tr>
			
			
			
<tr>
<td colspan="4" class="labelheading1 tbcellCss"><b>Treatment Done</b></td></tr>
<tr><td valign="top" colspan="2" class="tbcellBorder">
<table style="width:100%;" id="treatmentdonetable">
<logic:iterate id="treatmentDoneDtls" name="patientForm" property="dispDentalTreatDoneLst" indexId="i">
<!-- 	 <tr><td style="width:20%;" class="labelheading1 tbcellCss">&nbsp;&nbsp; -->
<%--       		 <bean:write name="treatmentDoneDtls" property="VALUE"/> --%>
	
	
		<logic:equal name="treatmentDoneDtls" property="SUBCODE" value="Y">
		<tr><td style="width:20%;" class="labelheading1 tbcellCss" colspan="4">&nbsp;&nbsp;
      		 <b><bean:write name="treatmentDoneDtls" property="VALUE"/></b>		 
       <table width="100%">
       	<logic:iterate id="treatmentDoneSubDtls" name="patientForm" property="dispDentalTreatDoneSubLst" indexId="j">
		<logic:equal name="treatmentDoneSubDtls" property="LEVELID" value="${patientForm.dispDentalTreatDoneLst[i].ID}">
	 	<tr><td style="width:20%;" >&nbsp;&nbsp;
      		 <bean:write name="treatmentDoneSubDtls" property="VALUE"/>
       	</td>
       	<td class="tbcellBorder" style="background-color:#fff;color:#000;">
<%--        	<html:textarea name="patientForm" property="textVal${patientForm.dispDentalTreatDoneSubLst[j].ID}" styleId="textVal${patientForm.dispDentalTreatDoneSubLst[j].ID}" title="Enter Remarks" style="width:100%; color: #000;"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medical History');blockConsecutiveChars('Medical History',this,this.value)"/> --%>
       	<bean:write name="patientForm"  property="textVal${patientForm.dispDentalTreatDoneSubLst[j].ID}"/>
       	</td><tr>
       	</logic:equal>       	
       	</logic:iterate>
       	</table></td></tr>     
       	</logic:equal>
       	<logic:notEqual name="treatmentDoneDtls" property="SUBCODE" value="Y">
       	<tr><td style="width:20%;" class="labelheading1 tbcellCss">&nbsp;&nbsp;
      		 <b><bean:write name="treatmentDoneDtls" property="VALUE"/></b>	
       	</td>
       	<td class="tbcellBorder">
<%--        	<html:textarea name="patientForm" property="textVal${patientForm.dispDentalTreatDoneLst[i].ID}" styleId="textVal${patientForm.dispDentalTreatDoneLst[i].ID}" title="Enter Remarks" style="width:100%;"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medical History');blockConsecutiveChars('Medical History'',this,this.value)"/> --%>
       	<bean:write name="patientForm"  property="textVal${patientForm.dispDentalTreatDoneLst[i].ID}"/>
       	</td>
       	</tr>
       	</logic:notEqual>       	
       	</logic:iterate>
        </table></td></tr>
			
		      <logic:notEmpty name="patientForm" property="investigationLt">
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">viii) Investigations done</span></strong>
		           <table width="100%">
		              <tr>
		                   <c:if test="${schemeId eq 'CD201' }">
							 <th width="50%" class="tbheader1">Investigation Block Name</th>  
							<th width="50%" class="tbheader1">Test name</th>
							</c:if>
							
							  <c:if test="${schemeId eq 'CD202' }">
							 
							<th width="100%" class="tbheader1">Test name</th>
							</c:if>
							
						</tr>
					  <logic:iterate id="inv" name="patientForm" property="investigationLt">
					<c:if test="${schemeId eq 'CD201' }">
					<tr>
					 <td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="TEST"/></td>  
					<td  width="50%" class="tbcellBorder print_cell"><bean:write name="inv" property="NAME"/></td>
					</tr>
					</c:if>
					
						<c:if test="${schemeId eq 'CD202' }">
					<tr>
					  
					<td  width="100%" class="tbcellBorder print_cell"><b><bean:write name="inv" property="NAME"/></b></td>
					</tr>
					</c:if>
					
					</logic:iterate>
		           </table>	      
		      </td></tr></logic:notEmpty>
			<logic:empty name="patientForm" property="investigationLt">
				<tr><td colspan="3" class="labelheading1 tbcellCss print_cell">
					<strong>No Investigations Added</strong>
				</td></tr>
			</logic:empty>			  
		   </table>
		</td></tr>
		<c:if test="${fromDisp eq 'Y' }">
			<logic:present name="patientForm" property="referalDtls">
        <bean:size  id="referalDtlsSize" name="patientForm" property="referalDtls"/>
        <logic:greaterThan value="0" name="referalDtlsSize">
			  
		      <tr><td colspan="3" class="tbcellCss print_cell"><strong><span class="labelheading1">Referred Details:</span></strong>
		           <table width="100%">
		              <tr>
		                  <th width="33%" class="tbheader1">District</th>  
							<th width="33%" class="tbheader1">Hospital</th>
							<th width="33%" class="tbheader1">Speciality</th>
						</tr>
					  <logic:iterate id="inv" name="patientForm" property="referalDtls">
					<tr>
					 <td  width="33%" class="tbcellBorder print_cell"><bean:write name="inv" property="DISTRICT"/></td>  
					<td  width="33%" class="tbcellBorder print_cell"><bean:write name="inv" property="HOSPNAME"/></td>
					<td  width="33%" class="tbcellBorder print_cell"><bean:write name="inv" property="DISNAME"/></td>
					</tr>
					</logic:iterate>
		           </table>	      
		      </td></tr>
		      </logic:greaterThan>
		      </logic:present>
			
		</c:if>
			
	<c:if test="${fromDisp eq 'Y' }">	
	<logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">	
		<tr><td colspan="4" class="tbcellBorder"> 
	<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
	<table width="100%">
	<tr><td style="text-align:left" class="tbheader print_heading"><b>4.Drugs</b></td></tr>
	</table>
 	 <table  width="100%"  id="drugsTable"  border="1">
      <tr>  
      	<td width="5%"><b><fmt:message key="EHF.SNO"/></b></td>        
        <td width="10%"><b>Drug Type</b></td>   
        <td width="10%"><b>Drug Name</b></td>
        <td width="5%"><b>Route Type</b></td>
        <td width="5%"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="5%"><b>Strength Type</b></td>
        <td width="10%"><b><fmt:message key="EHF.Strength"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="5%"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
        <td width="5%"><b>Quantity</b></td>
        <td width="10%"><b>Expiry Date</b></td>
        </tr>
        
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td width="5%"><%=k++ %></td>        
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPECODE" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGSUBTYPECODE" /></td>
       	<td width="5%"><bean:write name="drugLst" property="ROUTETYPENAME" /></td> 
       	<td width="5%"><bean:write name="drugLst" property="ROUTENAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="STRENGTHTYPENAME" /></td>
       	<td width="10%"><bean:write name="drugLst" property="STRENGTHNAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="DOSAGE" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="MEDICATIONPERIOD" /></td> 
       	<td width="5%"><bean:write name="drugLst" property="QUANTITY" /></td>
       	<td width="10%"><bean:write name="drugLst" property="EXPIRYDATE" /></td>
        </tr>
        </logic:iterate>
               
        </table></div>
</td></tr>
</logic:greaterThan></logic:present> 
		</c:if>
		
		<tr><td>
		<table width="100%">
			<tr>
			<c:choose>
			<c:when test="${fromDisp eq 'Y'}">
			<td width="50%" class="tbcellBorder print_cell" height="50px">Name and Signature Wellness Center Doctor</td>
			</c:when>
			<c:otherwise>
			<td width="50%" class="tbcellBorder print_cell" height="50px">Name and Signature of MEDCO/Treating Doctor</td>
			</c:otherwise>
			</c:choose>
			<td width="50%" class="tbcellBorder print_cell" height="50px"><bean:write name="patientForm" property="doctorName" /></td>
			</tr>
			<tr><td class="tbcellBorder" style="color:red;" colspan="2">NOTE:  All the Tests as prescribed by the doctor will be done free of cost. </td></tr>
		</table>
		</td></tr>
		<tr class="print_buttons">
			<td align="center">
				<button class="but" type="button" id="Print" onclick="fn_Print();">Print</button>
<!-- 				<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td> -->
			</td>
		</tr>		
		</table>
		<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
	</form>
</body>
	</html>
</fmt:bundle>