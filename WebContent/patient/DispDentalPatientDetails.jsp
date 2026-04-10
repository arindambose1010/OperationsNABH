<!DOCTYPE html >
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean,com.ahct.patient.vo.CommonDtlsVO"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
var labGrp = "${labGrp}";



</script>

<script type="text/vbscript">  
      Function GetFeatureAccrual(IdOfHiddenTextField)	
	Dim Obj
	If (IdOfHiddenTextField Is Nothing) Then
		MsgBox "Invalid Hidden Field Argument Passed", vbExclamation, "Web API BMA Application"
	Else
		Set Obj = CreateObject ("Web_API_3.Legend")
		If (Obj Is Nothing) Then
			MsgBox "Unable To Create Instance For Web API, Check Dll Is Registered Properly", vbExclamation, "Web API BMA Application"
		Else
			IdOfHiddenTextField.Value = ""
			Obj.GetFeatureAccrual
			IdOfHiddenTextField.Value = Obj.Feature
			If IdOfHiddenTextField.Value <> "" Then
				MsgBox "Fingerprint Captured Successfully.", vbInformation, "Web API BMA Application"
			Else
				MsgBox "Fingerprint Not Captured Successfully", vbExclamation, "Web API BMA Application"
			End If
		End If
	End If
End Function 
</script>
  
    <jsp:include page="/patient/DispDentalPatientDtls.jsp"/>
</head>
<fmt:bundle basename="Registration">
<body onload="fn_loadProcessImage();fn_ipop();">
<div id="processImagetable"
		style="top: 50%; width: 100%; position: absolute; z-index: 60;">
	<table border="0" align="center" width="100%">
		<tr>
			<td>
			<div id="processImage" align="center"><img
				src="images/Progress.gif" width="100" height="100" border="0"
				tabindex="3"></img></div>
			</td>
		</tr>
	</table>
	</div>
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" enctype="multipart/form-data">
<br>
<table width="95%" style="margin:0 auto">
<tr><td colspan="4">
<table class="tbheader">
<tr><td align="left"><b>&nbsp;&nbsp;&nbsp;<fmt:message key="EHF.Title.PatientRegistration"/></b></td></tr>
</table>
<br>
<table width="100%">
<tr><td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.HealthCardNo"/></b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td>
<td  width="15%" class="labelheading1 tbcellCss" ><b><fmt:message key="EHF.PatientNo"/></b></td><td id="patNo" width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>
<logic:equal  name="patientForm" property="telephonicReg" value="Yes"><b><td width="15%" class="labelheading1 tbcellCss">Telephonic ID</td>
<td width="15%" class="tbcellBorder"><a href="javascript:viewTelephonicInfo('<bean:write name="patientForm" property="telephonicId" />')"><bean:write name="patientForm" property="telephonicId" /></a></td></b>
</logic:equal>
<logic:notEqual  name="patientForm" property="telephonicReg" value="Yes">
<td width="30%">&nbsp;</td>
</logic:notEqual>
</tr>
</table>
<br>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.PatientDetails"/></b></td></tr>
</table>
<table width="100%">
<tr>
<td width="27%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.FrameSet.PatientDetails"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commonDtlsField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="cardIssueDt"/></b></td></tr> --%> 
<tr><td class="labelheading1 tbcellCss" width="40%"><b><fmt:message key="EHF.Name"/></b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateOfBirth"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="dateOfBirth"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Age"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="years"/>Y
 		<bean:write name="patientForm" property="months"/>M
		<bean:write name="patientForm" property="days"/>D</b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Relationship"/></b></td><td class="tbcellBorder"><b>&nbsp;<c:if test="${patientForm.relation eq 'New Born Baby'}"  ><b></c:if><bean:write name="patientForm" property="relation"/></b></td></tr>
<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="caste"/></b></td></tr> --%>
 <tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Slab"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="slab"/></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Designation"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="occupation"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="contactno"/></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="29%" valign="top">
<fieldset style="height:20em;" >
 <legend class="legendStyle" ><b><fmt:message key="EHF.CardAddress"/></b></legend>
  <div style="height:18em;overflow:hidden" id='cardAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="hno"/></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="street"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="state"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="district" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="mandal" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="29%" valign="top">
<fieldset style="height:20em;">
 <legend class="legendStyle"><b><fmt:message key="EHF.CommunicationAddress"/></b></legend>
 <div style="height:18em;overflow:hidden" id='commAddressField'>
<table width="100%" height="200px" style="table-layout:fixed;word-wrap:break-word;">
<tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.HouseNo"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="patientForm" property="comm_hno" /></b></td> </tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Street"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_street" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>State</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_state" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_dist" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Mdl/Mcl"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_mandal"/></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Village"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_village" /></b></td></tr> 
<tr><td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Pin"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="comm_pin" /></b></td></tr>
</table>
</div>
</fieldset>
</td>
<td width="15%" valign="top">
<fieldset style="height:20em;" id='photoField'>
 <legend class="legendStyle"><b><fmt:message key="EHF.Photo"/></b></legend>
<table width="80%" height="80%" style="margin:auto auto">
<tr><td align="center">
 <logic:notEmpty name="patientForm" property="photoUrl">
<img id="patImage"  src="common/showDocument.jsp" width="150" height="150" alt="NO DATA" onmouseover="resizePatImage('patImage','200','200')" onmouseout="resizePatImage('patImage','150','150')"/>
</logic:notEmpty>
<logic:empty name="patientForm" property="photoUrl">
<img  src="images/photonot.gif" width="150" height="150" alt="NO DATA"  />
</logic:empty>
</td></tr></table>
</fieldset>
</td></tr>
</table>
</br>
<table class="tbheader">
<tr><td><b><fmt:message key="EHF.Title.CaseDetails"/></b></td></tr>
</table>
<br>
<table width="100%">
<tr>
<td width="15%" class="labelheading1 tbcellCss"><b>Referral Center</b></td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="hospitalName"/></b></td>
<td width="15%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateAndTime"/> </b> </td><td width="15%" class="tbcellBorder"><b><bean:write name="patientForm" property="dtTime"/></b></td>
</tr>
</table>

<c:forTokens items="${grpsForTokens}" delims="," var="name">

<logic:notEmpty name="groupList"> 
<logic:iterate name="groupList" id="labelbean">
<logic:equal name="labelbean" property="ID" value="${name}">
<table class="tbheader notDisplayLab">
<tr><td><b><fmt:message key="EHF.Title.MedicalDetails"/></b></td></tr>
</table>
<br>
<table width="100%" class="medicalDetailsTable" id ="medicalDetailsTable" border="0">



<tr>
<td colspan="4"><b>Medical History</b><font color="red" class="onlyAp">*</font></td></tr>
<tr><td valign="top" colspan="2" class="tbcellBorder">
<table style="width:100%;" id="medicalhistorytable">
<logic:iterate id="medicalDtls" name="patientForm" property="medicalhsitorydtls" indexId="i">
	 <tr><td style="width:20%;" class="labelheading1 tbcellCss">&nbsp;&nbsp;
<%--  		 <html:checkbox name="patientForm" value="${medicalDtls.ID}" property="medicalDtlsid" styleId="medicalDtlsid${i}" onclick="javascript:fn_showMediacalText(this.id);" title="Medical History" > --%>
      		 <bean:write name="medicalDtls" property="VALUE"/>
      		 
<%--        	</html:checkbox> --%>
       	</td>
       	<td class="tbcellBorder">
       	<html:textarea name="patientForm" property="textVal${patientForm.medicalhsitorydtls[i].ID}" styleId="textVal${patientForm.medicalhsitorydtls[i].ID}" title="Enter Remarks" style="width:100%;font-weight:normal;"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'${patientForm.medicalhsitorydtls[i].VALUE}');blockConsecutiveChars('${patientForm.medicalhsitorydtls[i].VALUE}',this,this.value)"/>
<%-- 		<html:textarea name="patientForm" property="medicalHistsText${i}" styleId="medicalHistsText${i}" title="Enter Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'Medical History');blockConsecutiveChars('Medical History'',this,this.value)"/> --%>
		</td>
       	</tr>
       	</logic:iterate>
        </table></td></tr>


<tr>
<tr><td><br></td></tr>
<td colspan="4"><b>Treatment Done</b></td></tr>
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
       	<td>
       	<html:textarea name="patientForm" property="textVal${patientForm.dispDentalTreatDoneSubLst[j].ID}" styleId="textVal${patientForm.dispDentalTreatDoneSubLst[j].ID}" title="Enter Remarks" style="width:100%;font-weight:normal;color: #000;"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'${patientForm.dispDentalTreatDoneSubLst[j].VALUE}');blockConsecutiveChars('${patientForm.dispDentalTreatDoneSubLst[j].VALUE}',this,this.value)"/>
       	</td><tr>
       	</logic:equal>       	
       	</logic:iterate>
       	</table></td></tr>     
       	</logic:equal>
       	<logic:notEqual name="treatmentDoneDtls" property="SUBCODE" value="Y">
       	<tr><td style="width:20%;" class="labelheading1 tbcellCss">&nbsp;&nbsp;
      		 <b><bean:write name="treatmentDoneDtls" property="VALUE"/></b>	
       	</td>
       	<td>
       	<html:textarea name="patientForm" property="textVal${patientForm.dispDentalTreatDoneLst[i].ID}" styleId="textVal${patientForm.dispDentalTreatDoneLst[i].ID}" title="Enter Remarks" style="width:100%;font-weight:normal;"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'${patientForm.dispDentalTreatDoneLst[i].ID}');blockConsecutiveChars('${patientForm.dispDentalTreatDoneLst[i].ID}',this,this.value)"/>
       	</td>
       	</tr>
       	</logic:notEqual>       	
       	</logic:iterate>
        </table></td></tr>

</table>

</td></tr>
<tr class = "notDisplayLab"><td colspan="4" >
<font color="red">Select atleast One General Investigation to enable IP.</font>
</td></tr>
<tr><td><br></td></tr>
<tr class = "notDisplayLab"><td colspan="4"><b><fmt:message key="EHF.GenInvestigations"/></b> <font color="red" >&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4"><table width="100%">
<tr class = "notDisplayLab">
<td class="labelheading1 tbcellCss" width="25%" id="InvBlockName"><b>Investigation Block Name</b></td>
<td class="labelheading1 tbcellCss" width="25%" id="InvName" style="display: none;"><b>Investigation Name</b></td>
      
      
    <logic:equal value="CD202" name="patientForm" property="scheme">
      <logic:equal value="G" name="patientForm" property="hosptype"> 
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox value="O"  title="Click Here to Add other Investigations" styleId="invOthers" onchange="fn_enableOthers();" name="patientForm" property="investOthers" ><b>&nbsp;Other Investigations</b></html:checkbox>
        </td>
         </logic:equal> 
        </logic:equal>
      
        
<td width="25%">&nbsp;</td></tr>
<tr id="genInvestList">
<td class="tbcellBorder" id="InvBlckLst">
<html:select name="patientForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" >
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
          <html:options property="ID" collection="investigationsList" labelProperty="VALUE"/>
         
  
          
    </html:select></td>
<td  style="align:center">
<button class="btn btn-success" type="button" name="add" value="Add Tests" onclick="confirmPatientTypeResetDisp()">Add Tests&nbsp;<i class="fa fa-plus"></i></button>
</td>
    <td class="tbcellBorder" id="invLst" style="display: none;"><html:select name="patientForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 17em;"  onchange="javascript:getInvestPrice();" onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
    </tr>
   
    <tr>
   
     <td class="labelheading1 tbcellCss" width="25%" Id="otherInvNameHead" style="display:none"><b>Other Investigation Name</b></td>
     </tr>
     <tr>
     <td class="tbCellBorder"><html:text name="patientForm" property="otherInvName" styleId="otherInvName" style="display:none;width:97%" styleClass="otherFields" onchange="javascript:check_maxLength('otherInvName','200')"></html:text></td>
  </tr>
  <tr><td><br></td></tr>
</table></td></tr>
<logic:present name="patientForm" property="genInvList">
        <bean:size  id="genInvSize" name="patientForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
<tr><td colspan="4" class="tbcellBorder" style="text-align:center">
<a href="javascript:fn_openAllInOneReport();">View All Reports</a>
</td></tr>
</logic:greaterThan></logic:present>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTableID" style="display:none;border-collapse:collapse;border:1px solid #c5f7de" border="1">
   <tr><td colspan="4" class="labelheading1 tbcellCss">Previously Added Investigation</td></tr>
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="20%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="20%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="10%"><b>Lab Report</b></td>
        	<td width="20%">&nbsp;</td></tr>
        <logic:present name="patientForm" property="genInvList">
        <bean:size  id="genInvSize" name="patientForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
		<%int j = 1;%>
		<script>var rowNo=2;</script>
        <logic:iterate id="gnInvlst" name="patientForm" property="genInvList" indexId="sno" >
        <tr>  
      	<!-- <td width="10%"><%=j++ %></td>  -->
      	<td width="30%" ><bean:write name="gnInvlst" property="ACTION" /></td>       
        <td width="20%"><bean:write name="gnInvlst" property="VALUE" /></td> 
		<logic:empty name="gnInvlst" property="LVL">
		<script>
			var updateGenInvest="<bean:write name="gnInvlst" property="ACTION" />~<bean:write name="gnInvlst" property="VALUE" />~<bean:write name="gnInvlst" property="ID" />";
			updateGenInvestLst[updateGenInvestCount]=updateGenInvest;
			updateGenInvestCount++;
			var investTableId=document.getElementById('genTestTableID');   
			var newRow=investTableId.rows[rowNo];
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="20%"><input type="file"  id=<bean:write name="gnInvlst" property="ID" /> name="updateGenAttach['+updGenInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
			updGenInvestAttachId++;
		</script>
		</logic:empty>
		<logic:notEmpty  name="gnInvlst" property="LVL">
       	<td width="25%"><a href="javascript:fn_openAtachment('<bean:write name="gnInvlst" property="LVL" />','<bean:write name="gnInvlst" property="LVL" />');">View</a></td>
		</logic:notEmpty>
		<td width="10%">
			<c:choose>
				<c:when test="${gnInvlst.TYPE eq 'Y'}">
					<!-- <a class="LabReportSubmited" href="javascript:fn_openLabReport('<bean:write name="gnInvlst" property="ID" />')">Lab Report</a>-->
					<span class ="labReport LabReportSubmited" id="lbl<bean:write name="gnInvlst" property="ID" />">Lab Report <span style="display:none;"><bean:write name="gnInvlst" property="ID" /></span></span>
				</c:when>
				<c:otherwise>
					<!--<a class="labReport" href="javascript:fn_openLabReport('<bean:write name="gnInvlst" property="ID" />')">Lab Report</a>-->
					<span class ="labReport" id="lbl<bean:write name="gnInvlst" property="ID" />">Lab Report <span style="display:none;"><bean:write name="gnInvlst" property="ID" /></span></span>
				</c:otherwise>
			</c:choose>
		</td>
		<c:if test="${labGrp eq 'GP797'}">
		<td><span class ="editReport" id="lbl<bean:write name="gnInvlst" property="ID" />">Edit <span style="display:none;"><bean:write name="gnInvlst" property="ID" /></span></span></td></c:if>
		<script>rowNo++;</script>
       	<td width="20%"><button class="btn btn-warning notDisplayLab" type="button" value="Delete" name=<bean:write name="gnInvlst" property="ID" /> id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:confirmRemoveGenInvest(this,'geninvestigations','<bean:write name="gnInvlst" property="ID" />');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
   </table>
</td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTable" style="display:none" border="1">
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="20%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="20%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="10%"><b>Lab Report</b></td>
        	<td width="20%">&nbsp;</td></tr> 
  </table>
</td></tr>


        

<tr><td colspan="4" class="labelheading1 tbcellCss">
<table width="100%" id="ipOpTable">
<tr><td  width="15%"><b><fmt:message key="EHF.PatientType"/></b><font color="red">*</font></td>


<td id="patientType2" width="15%"><html:radio name="patientForm" property="patientType" value="OP" title="Out Patient" styleId="patientTypeOP" onclick="enableIPOP()" /><b>General OP</b></td>
<td id="patientType1" width="10%" ><html:radio name="patientForm" property="patientType" value="IP" title="In Patient" styleId="patientTypeIP" disabled="true" onclick="enableIPOP()"/><b><fmt:message key="EHF.IP"/></b></td>
<!-- <c:if test="${schemeId eq 'CD202' and patientScheme eq 'CD501' and hospType eq 'G' }">
 <td id="patientType3" width="15%" ><html:radio name="patientForm" property="patientType" value="ChronicOP" title="Chronic OP" styleId="patientTypeCOP"  onclick="saveChronicOP()"/><b>Chronic OP</b></td>
 </c:if> -->

<!--<td id="patientType3" width="10%" class="labelheading1 tbcellCss"><html:radio name="patientForm" property="patientType" value="ChronicOP" title="Chronic Out Patient" styleId="patientType" onclick="enableIPOP()"/><b><fmt:message key="EHF.ChronicOP"/></b></td>-->

<c:choose>
<c:when test="${schemeId eq 'CD202' && hospType eq 'G' }">
<td  width="25%" class="labelheading1 tbcellCss"><button class="btn btn-success"  type="button" id="saveDTRS" onclick="javascript:fn_saveDetailsWithoutMandate('SaveDTRS');">Save and Generate DTRS&nbsp;<i class="fa fa-print"></i></button></td>
</c:when>
<c:otherwise>

<td  width="25%" class="labelheading1 tbcellCss"><button class="btn btn-success"  type="button" id="saveDTRS" onclick="javascript:fn_savePatientDetails('SaveDTRS')">Save and Generate DTRS&nbsp;<i class="fa fa-print"></i></button></td>
</c:otherwise>
</c:choose>
<td id="dtrsTd" width="25%"  class="labelheading1 tbcellCss" style="display:none">
<a href="javascript:generateDTRSPrint('<bean:write name="patientForm" property="caseId" />','<bean:write name="patientForm" property="hospitalId" />')"><b>DTRS Print Form</b></a>
</td>
<logic:notEqual  name="patientForm" property="enablePharma" value="Y">
<%-- <%-- <td id="toDispatchTd" class="labelheading1 tbcellCss"><button class="btn btn-success" id="toDispatch"  type="button" onclick="javascript:fn_enablePharma()" disabled>Dispatch Drugs</button></td> --%>
</logic:notEqual> 
</tr>
</table>
</td></tr>
<tr class="notDisplayLab">
<td id="empPastHistory" style="display:none;">


<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click Here to View Past History" style="color:#fff; display:block;">
         

			<span id="empPastHstry" style="font-size: 1.25em;"><b><i  style="color:#ff0000" class="fa fa-user-plus"></i>&nbsp;&nbsp;Patient Past History&nbsp;&nbsp;<i style="color:#ff0000" class="fa fa-user-plus"></i></b></span>

        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse"> 
      <div class="panel-body">
      
      <table width="95%" style="margin:auto;">
     <logic:notEmpty name="patientForm" property="lstCaseSearch" >
     
     <tr>
											<th class="tbheader1" width="12%">Case ID</th>
<!-- 											<th class="tbheader1" width="14%">PatientName</th> -->
											<th class="tbheader1" width="25%">Hospital Name</th>
<!-- 											<th class="tbheader1" width="10%">Case Type</th> -->
											<th class="tbheader1" width="15%">Case Status</th>
											<th class="tbheader1" width="10%">Registered Date</th>		
										<!--	<th class="tbheader1" width="3%">Preauth Amount</th>
											<th class="tbheader1" width="2%">Claim Amount</th>	   -->				
											
										</tr>
										
										
										
										<logic:iterate  name="patientForm" property="lstCaseSearch" id="data" >
												<tr>
													<td align="center" width="12%" class="tbcellBorder" style="word-wrap: break-word;">
														<b><font style="color:#862010"><a  id="pastHstryView"  data-toggle="modal" data-target="#pastHistoryModal" href="#" onclick="javascript:getDiagnosisDetails('<bean:write name="data" property="CASESTATUSID"/>','<bean:write  name="data" property="CATID"/>','<bean:write  name="data" property="HOSPNAME"/>','<bean:write  name="data" property="SUBMITTEDDATE"/>','<bean:write  name="data" property="PATIENTID"/>','<bean:write  name="data" property="CASEID"/>','<bean:write  name="data" property="PATIPOP"/>')"><bean:write  name="data" property="CASEID"/></a></font></b>
													</td>
<!-- 													<td align="center" width="14%" class="tbcellBorder" style="word-wrap: break-word;"> -->
<%-- 														<bean:write  name="data" property="PATNAME"/> --%>
<!-- 													</td> -->
													<td align="center" width="18%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="HOSPNAME"/>
													</td>
<!-- 													<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;"> -->
<%-- 														<b><font style="color:#B32900"><bean:write  name="data" property="PATIPOP"/></font></</b> --%>
<!-- 													</td> -->
													<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="CASESTATUS"/>
													</td>
													<td align="center" width="20%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="SUBMITTEDDATE"/>
													</td>
												</tr>
										
										</logic:iterate>
										</logic:notEmpty>
									
      <logic:empty name="patientForm" property="lstCaseSearch" >
      <tr>
      <td align="center" style="color:#434645">
      <i class="fa fa-user-times"></i>&nbsp;<b>No Past History Found</b>
      </td>
      </tr>
      </logic:empty>
      </table>
      </div>
      </div>
    </div>
  </div>
</td></tr>

<tr><td style="display:none" id="diagnosisData" colspan="4">
<table width="100%">
<tr><td><b><font ><fmt:message key="EHF.Diagnosis"/>:</font></b></td></tr>



    <logic:equal value="CD202" name="patientForm" property="scheme">
     <logic:equal value="G" name="patientForm" property="hosptype">
    <tr>
      <td class="labelheading1 tbcellCss" width="25%">
        <html:checkbox  title="Click Here to Add Other Diagnosis" styleId="diagOthers" onchange="fn_enableOtherDiag();" name="patientForm" property="diagOthers" value="Y"><b>&nbsp;Other Diagnosis</b></html:checkbox>
        </td>
        <td class="labelheading1 tbcellCss" id="diagOthersHead" style="display:none"><b>Other Diagnosis Name</b></td>
        <td class="tbCellBorder" colspan="2" Id="diagOthersName" style="display:none;"><html:textarea  name="patientForm" property="diagOthersName"  style="width:100%" styleClass="otherFields" onchange="javascript:check_maxLength('otherDiagnosis','4000');"></html:textarea></td>
        </tr> 


        <tr class="existDiag">
<td class="tbcellCss" style="color:#A81C06">
<input type="radio"  name="diagCondition" value="c" Id="contains" onchange="javascript:getDiagListAuto();" checked="checked">&nbsp;&nbsp;<b>Contains</b>&nbsp;&nbsp;&nbsp;&nbsp;
<input type="radio"  name="diagCondition" value="s" Id="startsWith" onchange="javascript:getDiagListAuto();">&nbsp;&nbsp;<b>Starts With</b>

</td>
<td  class="tbcellCss">
<html:text name="patientForm" property="diagnosisName" styleId="diagnosisName" onkeyup="javascript:getDiagListAuto();" style="width:100%"></html:text>
</td>
<td  class="labelheading1 tbcellCss existDrugs" id="diagAuto1" colspan="2" style="color:#A81C06">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Please Enter KeyWords to Filter Diagnosis List&nbsp;&nbsp;</font></b><i class="fa fa-list"></i>
</td>
        </tr>
        <tr class="existDiag">
                <td  class="labelheading1 tbcellCss existDrugs" width="10%"><b>	Disease Anatomical Name</b></td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugName" styleId="disNameAuto" style="width:17em;" title="Select Diagnosis Name" onkeyup="javascript:getDiagListAuto();" onchange="javascript:getDiagDetailsAuto();" onmouseover="addTooltip('drugName')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		 
</html:select></td>

<td  class="labelheading1 tbcellCss existDrugs" id="diagAuto2" colspan="2" style="color:#A81C06;display:none">
<span ><i  class="fa fa-hand-o-left fa-3 autoData"></i></span><b><font>&nbsp;&nbsp;Select Diagnosis Name to Auto Populate Data&nbsp;&nbsp;</font></b><i class="fa fa-medkit"></i> 
</td>

        </tr>
        
        </logic:equal>
        </logic:equal>

<tr class="existDiag">
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagType"/></b> <font color="red" >*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiagCode"/></b> <font color="red" >*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatName"/></b> <font color="red" >*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MainCatCode"/></b><font color="red" >*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="diagType" styleId="diagType" style="WIDTH:17em" title="Select Diagnosis Type" onchange="getDiagnMainCatList('noOnload')" onmouseover="addTooltip('diagType')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            <html:options property="ID" collection="diagnTypeList" labelProperty="VALUE"/>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diagCode" styleId="diagCode" title="Diagnosis Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="mainCatName" styleId="mainCatName" style="WIDTH:17em" title="Select Main Category Name" onchange="getDiagnCategoryList()" onmouseover="addTooltip('mainCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
      </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="mainCatCode" styleId="mainCatCode" title="Main Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr class="existDiag">
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatName"/></b> <font color="red" >*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.CatCode"/></b> <font color="red" >*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatName"/></b> <font color="red" >*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.SubCatCode"/></b> <font color="red" >*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="catName" styleId="catName" style="WIDTH:17em" title="Select Category Name" onchange="getDiagnSubCategoryList()" onmouseover="addTooltip('catName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="catCode" styleId="catCode" title="Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="subCatName" styleId="subCatName" style="WIDTH:17em" title="Select Sub Category Name" onchange="getDiagnDiseaseList()" onmouseover="addTooltip('subCatName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
            </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="subCatCode" styleId="subCatCode" title="Sub Category Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
<tr class="existDiag">
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseName"/></b> <font color="red" >*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DiseaseCode"/></b> <font color="red" >*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalName"/></b> <font color="red" >*</font></td>
<td  class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DisAnatomicalCode"/></b> <font color="red" >*</font></td>
</tr>
<tr class="existDiag">
<td class="tbcellBorder"><html:select name="patientForm" property="diseaseName" styleId="diseaseName" style="WIDTH:17em" title="Select Disease Name" onchange="getDiagnDisAnatomicalList()" onmouseover="addTooltip('diseaseName')">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
 	 </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="diseaseCode" styleId="diseaseCode" title="Disease Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
<td class="tbcellBorder"><html:select name="patientForm" property="disAnatomicalName" styleId="disAnatomicalName" style="WIDTH:17em" title="Select Disease Anatomical Name" onmouseover="addTooltip('disAnatomicalName')" onchange="getDisAnatomicalCode()">
               <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select></td>
<td class="tbcellBorder"><html:text name="patientForm"  property="disAnatomicalCode" styleId="disAnatomicalCode" title="Disease Anatomical Code" maxlength="10" style="WIDTH:17em" onkeydown="validateBackSpace(event)" readonly="true"/></td>
</tr>
</table>
</td></tr>

<tr><td style="display:none" id="IPHead2" colspan="4">
<table width="100%" class="notDisplayLab">
<tr><td><b><font><fmt:message key="EHF.Therapy"/>:</font></b></td></tr>
<tr>
<td width="25%"  class="labelheading1 tbcellCss"><b>Select Speciality </b> <font color="red" >*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b>Select District </b> <font color="red" >*</font></td>
<td width="25%"  class="labelheading1 tbcellCss"><b> Select Hospital </b> <font color="red" >*</font></td>
</tr>
<tr>

<td class="tbcellBorder"><html:select name="patientForm" property="asriCatName" styleId="asriCatName" style="WIDTH: 25em;" title="Select Category "  onmouseover="addTooltip('asriCatName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:options property="ID" collection="specialityList" labelProperty="VALUE"/>
</html:select>
</td>

<td class="tbcellBorder">
<html:select name="patientForm" property="district" styleId="district" title="Select District" style="WIDTH: 25em;" onmouseover="addTooltip('district')" onchange="javascript:getHospitals(asriCatName,district);">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:options property="ID" collection="districtsList" labelProperty="VALUE"/>
</html:select>
</td>

<td class="tbcellBorder">
<html:select name="patientForm" property="currHospId" styleId="currHospId" title="Select Hospital" style="WIDTH: 25em;" onmouseover="addTooltip('currHospId')" >
 <c:if test="${fn:length(hospDtlsList) gt 0}">
    <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
  <html:options property="ID" collection="hospDtlsList" labelProperty="VALUE"/>
  </c:if>
</html:select>

</td>

</tr>

<tr>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font>
<html:select name="patientForm" property="ipDiagnosedBy" styleId="ipDiagnosedBy" style="width:17em" onchange="fn_getIPDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('ipDiagnosedBy')"> 
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="REFERRAL CENTER DOCTOR">REFERRAL CENTER DOCTOR</html:option>
</html:select>
</td>
<td valign="top" id="ipDocNameList" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font>
<html:select name="patientForm" property="ipDoctorName" styleId="ipDoctorName" style="WIDTH:17em" title="Select Doctor Name" onmouseover="addTooltip('ipDoctorName')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td>&nbsp;</td>
</tr>
</table>


<table width="100%" class="notDisplayLab">
<tr id="remarksDivIP">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font>
<html:textarea name="patientForm" property="remarks" styleId="remarks" title="Enter Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'IP Remarks');blockConsecutiveChars('IP Remarks',this,this.value)"/>
</td>
</tr>
</table>
</td>
</tr>
<!--End Of  Fields For Patient Type IP -->
</table>
<!-- <table style="width:95%;margin: 0 auto !important" class ="notDisplayLab"> -->

		
<logic:present name="patientForm" property="pastDrugHist">
<table id="pastDrugsTable" border="1" style="width:95%;margin: 0 auto !important" class='tbcellBorder'>
        <bean:size  id="pastDrugHistSize" name="patientForm" property="pastDrugHist"/>
        <logic:greaterThan value="0" name="pastDrugHistSize">
        <tr>
        <th colspan='12' class="labelheading1 tbcellCss existDrugs" style="text-align: center;">Dispatched Drug History (Past 10 days)</th>
        </tr>
        <tr>  
      	<td width="5%" class="labelheading1 tbcellCss existDrugs">S.NO</td>
      	<td width="10%" class="labelheading1 tbcellCss existDrugs">PAST PATIENT NO</td>
		<td width="10%" class="labelheading1 tbcellCss existDrugs">PAST CASE NO</td>      	        
        <td width="10%" class="labelheading1 tbcellCss existDrugs">DRUG TYPE</td>   
       	<td width="10%" class="labelheading1 tbcellCss existDrugs">DRUG NAME</td>
       	<td width="5%" class="labelheading1 tbcellCss existDrugs">ROUTE</td> 
       	<td width="5%" class="labelheading1 tbcellCss existDrugs">STRENGTH</td>
       	<td width="5%" class="labelheading1 tbcellCss existDrugs">DOSAGE</td>  
       	<td width="5%" class="labelheading1 tbcellCss existDrugs">MEDICATION PERIOD</td> 
       	<td width="5%" class="labelheading1 tbcellCss existDrugs">QUANTITY</td>
       	<td width="20%" class="labelheading1 tbcellCss existDrugs">DISPENSARY NAME</td>
       	<td width="10%" class="labelheading1 tbcellCss existDrugs">ISSUE DATE</td>
        </tr>
		<%int k = 1;%>
        <logic:iterate id="pastDrugHistLt" name="patientForm" property="pastDrugHist" >
        <tr>  
      	<td width="5%"><%=k++ %></td>
      	<td width="10%"><bean:write name="pastDrugHistLt" property="PASTPATIENTID" /></td>
		<td width="10%"><bean:write name="pastDrugHistLt" property="PASTCASEID" /></td>      	        
        <td width="10%"><bean:write name="pastDrugHistLt" property="DRUGTYPE" /></td>   
       	<td width="10%"><bean:write name="pastDrugHistLt" property="DRUGNAME" /></td>
       	<td width="5%"><bean:write name="pastDrugHistLt" property="ROUTETYPENAME" /><br><bean:write name="pastDrugHistLt" property="ROUTENAME" /></td> 
       	<td width="5%"><bean:write name="pastDrugHistLt" property="STRENGTHTYPENAME" /><br><bean:write name="pastDrugHistLt" property="STRENGTHNAME" /></td>
       	<td width="5%"><bean:write name="pastDrugHistLt" property="DOSAGE" /></td>  
       	<td width="5%"><bean:write name="pastDrugHistLt" property="MEDICATIONPERIOD" /></td> 
       	<td width="5%"><bean:write name="pastDrugHistLt" property="QUANTITY" /></td>
       	<td width="20%"><bean:write name="pastDrugHistLt" property="DISPNAME" /></td>
       	<td width="10%"><bean:write name="pastDrugHistLt" property="ISSUEDATE" /></td>
        </tr>
        </logic:iterate></logic:greaterThan>
        </table>
        </logic:present> 



<table style="width:95%;margin: 0 auto !important" id ="prescriptionDataTable">
<tr><td style="display:none" id="prescriptionData" colspan="4">
<table width="100%">
<tr><td><font ><b><fmt:message key="EHF.Prescription"/>:</b></font></td></tr>
<tr><td colspan="4" id="existDrugsHead" style="display:none" class="labelheading1 tbcellCss"><font size="2px">Existing Drugs</font></td></tr>
<tr><td colspan="4" class="tbcellBorder">
<table  width="100%"  id="existDrugs" style="display:none" border="1">
      <tr>  
      	<td width="5%"><fmt:message key="EHF.SNO"/></td>        
        <td width="15%"><fmt:message key="EHF.DrugTypeName"/></td>   
       	<td width="15%"><fmt:message key="EHF.DrugSubTypeName"/></td>
        <td width="15%"><fmt:message key="EHF.DrugName"/></td>
        <td width="15%"><fmt:message key="EHF.Route"/></td>
        <td width="10%"><fmt:message key="EHF.Strength"/></td>
        <td width="10%"><fmt:message key="EHF.Dosage"/></td>
        <td width="10%"><fmt:message key="EHF.MedicationPeriod"/></td>
        <td width="5%">&nbsp;</td>
        </tr></table>
</td></tr>
<tr>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Drug Type</b> <font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Drug Name</b><font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs" id="otherDrugLabel" style="display:none;"><b>Other Drug Name</b><font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.Route"/></b></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugTypeCode" styleId="drugTypeCode" style="width:17em;" title="Select Drug type" onchange="getDrugSubTypeList(this.value)" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
<%-- <html:select name="patientForm" property="dispDrugMfg" onchange="" styleId="dispDrugMfg" style="width:17em;" title="Select Drug Manufacturer"  onmouseover="addTooltip('dispDrugMfg')"> --%>
<%-- </html:select> --%>
</td>
<td valign="top" class="tbcellBorder existDrugs" id="drugNameSelectTd">
<%-- <html:select name="patientForm" property="drugSubTypeCode" onchange="OtherDrugValid();fn_getDrugMfg();" styleId="drugSubTypeCode" style="width:17em;" title="Select Drug Sub type"  onmouseover="addTooltip('drugSubTypeCode')"> --%>
<html:select name="patientForm" property="drugSubTypeCode" onchange="fn_getDrugDetails()" styleId="drugSubTypeCode" style="width:17em;" title="Select Drug Sub type"  onmouseover="addTooltip('drugSubTypeCode')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		 <c:if test="${fn:length(drugTypeList) gt 0}">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="drugTypeList" labelProperty="VALUE"/>
		</c:if>
</html:select>
</td>


<td valign="top" class="tbcellBorder existDrugs" id="otherDrugsTd" style="display:none;">
<input type="text" name="patientForm" Id="otherDrugsTxt" style="width:17em;" title="Enter Other Drug Name"/>
</td>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="routeType" styleId="routeType" style="width:8em;" title="Select Route Type" onchange="getRouteList()" onmouseover="addTooltip('routeType')">
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:options property="ID" collection="routeTypeLst" labelProperty="VALUE"/>
</html:select>

<html:select name="patientForm" property="route" styleId="route" style="width:8em;" title="Select Route "  onmouseover="addTooltip('route')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select>
</td> 
</tr>

<tr>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.Strength"/></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.Dosage"/></b></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
<!-- <td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Quantity</b><font color="red" >*</font> </td> -->
</tr>

<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="strengthType" styleId="strengthType" style="width:8em;" title="Select Strength Type" onchange="getStrengthList()" onmouseover="addTooltip('strengthType')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:options property="ID" collection="strengthTypeLst" labelProperty="VALUE"/>
</html:select>

<html:select name="patientForm" property="strength" styleId="strength" style="width:8em;" title="Select Strength "  onmouseover="addTooltip('strength')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>		
</html:select>
</td>

<td class="tbcellBorder">
<html:select name="patientForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select>
</td>

<td class="tbcellBorder">
<html:text name="patientForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:17em" onchange="validateNumber('Medication Period',this)" title="Enter Medication Period"/>
</td>
<!-- <td class="tbcellBorder"> -->
<%-- 		<html:text name="patientForm" property="quantity" styleId="quantity" maxlength="3" style="WIDTH:17em" onchange="validateNumber('Quantity',this);fn_getDrugQuant()" title="Enter Quantity"/> --%>
<!-- 		</td> -->
 </tr>


<tr>
<td  class="tbcellBorder"  colspan="4">
<table width="100%" id="drugInfo" style="display:none;margin:0 auto">
<tr>
<th width="10%" class="labelheading1 tbcellCss"></th>
<th width="20%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b></th>
<th width="20%" class="labelheading1 tbcellCss"><b>Distributer Name</b></th>
<th width="10%" class="labelheading1 tbcellCss"><b>Batch No</b></th>
<th width="10%" class="labelheading1 tbcellCss"><b>Expiry Date</b></th>  
<th width="10%" class="labelheading1 tbcellCss"><b>Available Quantity</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Dispatching Quantity</b></th> 
</tr>
</table>
</td></tr>



<tr>
<td colspan="2">
<button type="but" class="btn btn-success" name="addDrug" id="addDrug" value="Add Drugs" onclick="addDrugs()">Add Drugs&nbsp;<i class="fa fa-plus-square"></i></button></td>
</tr>

<tr><td colspan="4" class="tbcellBorder"> 
<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
  <table  width="95%"  id="drugsTable" style="display:none;margin: 0 auto !important" border="1">
      <tr>  
      	<td width="5%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.SNO"/></b></td>        
        <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Drug Type</b></td>   
        <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Drug Name</b></td> 
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Route Type</b></td>
        <td width="10%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.Route"/></b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Strength Type</b></td>
        <td width="10%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.Strength"/></b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.Dosage"/></b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b><fmt:message key="EHF.MedicationPeriod"/></b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Quantity</b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Issuing Set</b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs">&nbsp;</td>
        </tr>
        <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td width="5%"><%=k++ %></td>        
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPECODE" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGNAME" /></td>
       	<td width="5%"><bean:write name="drugLst" property="ROUTETYPENAME" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="ROUTENAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="STRENGTHTYPENAME" /></td>
       	<td width="10%"><bean:write name="drugLst" property="STRENGTHNAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="DOSAGE" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="MEDICATIONPERIOD" /></td> 
       	<td width="5%"><bean:write name="drugLst" property="QUANTITY" /></td> 
       	<td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name='drugLst' property='DRUGSUBTYPENAME' /> id=<bean:write name='drugLst' property='DRUGSUBTYPENAME' /> onclick="javascript:confirmRemoveRow(this,'drug');"/></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>        
        </table></div>
</td></tr>
</table>
<table style="width: 100%;">
<tbody><tr>
<td align="center"> 
<!-- <button class="btn btn-primary has-spinner" type="button" onclick="javascript:fn_saveDetails()">Save&nbsp;<span class="spinner"><i class="fa fa-medkit"></i></span></button> -->
 <button class="btn btn-primary has-spinner" type="button" id="Save" onclick="javascript:fn_disDrugPat()">Submit&nbsp;<span class="spinner"><i class="fa fa-floppy-o"></i></span></button>
<!--  <button class="btn btn-primary" type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset&nbsp;<i class="fa fa-eraser"></i></button> -->
</td>
</tr>
</tbody></table>
</table>
<table style="width:95%;margin: 0 auto !important">
<tr><td style="display:none" id="OPHead2" colspan="4">
<table width="100%">
<tr>
<td width="20%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.OPNO"/></b> <font color="red">*</font></td>
<td width="20%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.RegistrationDate"/></b> <font color="red">*</font></td>
<td width="40%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Remarks"/></b> <font color="red">*</font></td>
<td width="20%">&nbsp;</td>
</tr>
<tr>
<td valign="top" class="tbcellBorder"><html:text name="patientForm" property="opNo" styleId="opNo" maxlength="20" style="width:17em" onchange="validateAlphaNum('OP NO',this,'')" title="Enter OP NO"/></td>
<td valign="top" class="tbcellBorder"><html:text name="patientForm" property="opDate" styleId="opDate" style="width:17em" value="${serverDt}" disabled="true"/></td>
<td class="tbcellBorder">
<html:textarea name="patientForm" property="opRemarks" styleId="opRemarks" title="Enter OP Remarks" style="width:18em;;height:3em"  onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event);" onchange="validateSpaces(this,'OP Remarks');blockConsecutiveChars('OP Remarks',this,this.value)"/>
</td>
<td>&nbsp;</td>
</tr>
</table></td></tr>
</table>
</table>

<table width="95%" style="margin:auto;display:none" id="OPDoctor">
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientDiagnosedby"/></b> <font color="red">*</font></td>
<td width="25%" id="docName" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DoctorName"/></b> <font color="red">*</font></td>

<c:if test="${schemeId eq 'CD201'}">
<td width="25%">&nbsp;</td>
<td width="25%">&nbsp;</td>
</c:if>

<c:if test="${schemeId eq 'CD202'}">
<td width="25%" class="labelheading1 tbcellCss" id="unitNameHead" style="display:none;"><b>Unit Name</b> <font color="red">*</font></td>
<td width="25%" class="labelheading1 tbcellCss" id="unitHODNameHead" style="display:none;"><b>Unit HOD Name</b> <font color="red">*</font></td>

</c:if>
</tr>
<tr>
<td valign="top" class="tbcellBorder">

<c:if test="${schemeId eq 'CD201' || hospType ne 'G'}">
<html:select name="patientForm" property="diagnosedBy" styleId="diagnosedBy" style="width:17em" onchange="fn_getDoctorsList()" title="Select Patient Diagnosed by" onmouseover="addTooltip('diagnosedBy')"> 
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
<html:option value="REFERRAL CENTER DOCTOR">REFERRAL CENTER DOCTOR</html:option>
</html:select>
</c:if>
</td>

<c:if test="${schemeId eq 'CD202'}">
<td valign="top" id="unitName" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="unitName" styleId="unitName" maxlength="50" style="WIDTH:17em"  title="Enter Unit Name"/></td>
<td valign="top" id="unitHodName" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="unitHodName" styleId="unitHodName" maxlength="50" style="WIDTH:17em" title="Enter Unit HOD  Name"/></td>
<td valign="top" id="addConsult" style="display:none"  class="labelheading1 tbcellCss"><button class="btn btn-primary" id="addConsult" onclick="javascript:fn_addConsultant();">Add Consultation Details&nbsp;<i class="fa fa-stethoscope"></i></button></td>
</c:if>




<td valign="top" id="docNameList" class="tbcellBorder">
<html:select name="patientForm" property="doctorName" styleId="doctorName" style="WIDTH:17em"  title="Select Doctor Name" onmouseover="addTooltip('doctorName')"> <!-- onchange="fn_getDoctorsDetails()" -->
<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
</html:select></td>
<td valign="top" id="docNametext" style="display:none"  class="labelheading1 tbcellCss"><html:text name="patientForm" property="otherDocName" styleId="otherDocName" maxlength="50" style="WIDTH:17em" onchange="checkAlphaSpace('otherDocName','Doctor Name')" title="Enter Doctor Name"/></td>
<td width="20%">&nbsp;</td>
<td class="tbcellCss" id="consulFee" style="display:none"><b><font color="#B01000">Rs 150</font></b></td>
</tr>
<tr><td colspan="4" class="tbcellBorder">			
	<div id="doctorDataDiv"> </div></td>
</tr>
<tr id="doctorData" style="display:none">
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Doctor.RegistrationNo"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docRegNo" styleId="docRegNo" styleClass="otherFields" maxlength="20" style="WIDTH:9em"    onchange="javascript:check_maxLength('docRegNo','100')" title="Enter Doctor Registration No"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.doctor.Qualification"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docQual" styleId="docQual" maxlength="30" style="WIDTH:9em" styleClass="otherFields" onchange="javascript:check_maxLength('docQual','100')" title="Enter Doctor Qualification"/></td>
<td valign="top" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b><font color="red">*</font>&nbsp;
<html:text name="patientForm" property="docMobileNo" styleId="docMobileNo" maxlength="10" onchange="validateMobile(this);" style="WIDTH:9em" title="Enter Doctor Contact No"/></td>
</tr>
</table>



<table width="95%" id="consultationDataNew" style="margin:auto;display:none;">
<tbody>
<tr>
<td class="tbheader" colspan="4">Consultation Details&nbsp;&nbsp;&nbsp;<i class="fa fa-medkit"></i></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss" style="width:10%"><b>S.No</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Diagnosed By</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit HOD Name</b></td>

</tr>

</tbody>

</table>

<br><br>

<logic:notEmpty name="patientForm" property="consultData">
<table width="95%" style="margin:auto;" id="consultationDataOld">
<tbody>
<tr>
<td class="tbheader" colspan="5">Previously Added Consultation Details&nbsp;&nbsp;&nbsp;<i class="fa fa-medkit"></i></td></tr>
<tr>
<td class="labelheading1 tbcellCss" style="width:10%"><b>S.No</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Diagnosed By</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Unit HOD Name</b></td>
<td class="labelheading1 tbcellCss" style="width:20%"><b>Consultation Date</b></td>

</tr>



<c:set var="count" value="1"></c:set>
<logic:iterate id="consultData" name="patientForm" property="consultData">
<tr>
<td class="tbcellBorder" style="width:10%"><b>${count}</b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="unitName"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="diagnoisedBy"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="hodName"></bean:write></b></td>
<td class="tbcellBorder" style="width:20%"><b><bean:write name="consultData" property="consultationTime"></bean:write></b></td>
</tr>
<c:set var="count" value="${count+1}"></c:set>
</logic:iterate>


</tbody>

</table>
</logic:notEmpty>

<table width="100%" id="opClaimTable" style="display:none">
<tbody>
<tr>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Consulation Fee</b></td>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Cost of Investigations</b> </td>
<td width="30%" class="labelheading1 tbcellCss" id="consulFeeHead" ><b>Total OP Amount</b></td>
</tr>
<tr>
<td class="tbcellCss" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="consultFee"></bean:write>&nbsp;/-</font></b></td>
<td class="tbcellCss" id="costOfInvest" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="totInvestPrice"></bean:write>&nbsp;/-</font></b></td>
<td class="tbcellCss" id="totalOpCost" ><b><font color="#B01000">&#x20B9; <bean:write name="patientForm" property="totalOpCost"></bean:write>&nbsp;/-</font></b></td>
</tr>
</tbody>
</table>

<!-- Biometric Capture -->
<!-- <table width="100%" id="biomCapture" >
<tr>
<td colspan="3" >
Capture Biometric Details
</td>
</tr>
<tr><td class="labelheading1 tbcellCss"> Hand</td>
<td class="labelheading1 tbcellCss">Finger</td>
<td class="labelheading1 tbcellCss"></td>
</tr>
<tr><td class="tbcellBorder"> <select>
<option value="-1">--Select--</option>
<option value="R">RIGHT</option>
<option value="L">LEFT</option>
</select> </td>
<td class="tbcellBorder"><select>
<option value="-1">--Select--</option>
<option value="T">Thumb Finger</option>
<option value="I">Index Finger</option>
<option value="M">Middle Finger</option>
<option value="R">Ring Finger</option>
<option value="L">Little Finger</option>
</select></td>
<td class="tbcellBorder"><button class="but"
										style="float: left; margin-left: 2px !important;"
										type="button" title="Click to Capture Biometric Details"
										onclick="javascript:fn_captureBiometrics()" name="bioCapture"
										id="bioCapture">CAPTURE BIOMETRICS</button></td>
</tr>
</table> -->
<table>
<table width="100%" class = "notDisplayLab"><tr>
<td colspan="4" id="opNote" style="display:none">
<font color="red"><b>OP Case Should be Submitted before 14 days after Case Registration</b></font>
</td></tr>

<tr><td colspan="4" id="ipNote1">
<font color="red">Please save general mandatory details to generate DTRS Print Form and enable submit.</font>
</td></tr>
<tr><td>&nbsp;</td></tr>

<tr><td colspan="4">
<table width="100%">
<tr>
<td style="height: 1em; font-size:small;" nowrap="nowrap" width=20%>
		<font color="red"><fmt:message key="EHF.MandatoryFields"/> <br /></font>
</td></tr></table></table>


<table   style="width:100%" id="btnsTable">
<tr>
<td align="center"> 
<button class="btn btn-primary has-spinner"  type="button"  onclick="javascript:fn_saveDetails()">Save&nbsp;<span class="spinner"><i class="fa fa-medkit"></i></span></button>
 <button class="btn btn-primary has-spinner"  type="button" id="Save" onclick="javascript:fn_savePatientDetails('submit')">Submit&nbsp;<span class="spinner"><i class="fa fa-floppy-o"></i></span></button>
 <button class="btn btn-primary"  type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset&nbsp;<i class="fa fa-eraser"></i></button>
</td>
</tr>
</table>

<br><br>

<!--  modal for showing attachments -->
<div class="modal fade col-lg-12 col-md-12 col-sm-12 col-xs-12" id="attachModal" >
<div class="modal-dialog" id="modal-1gx">
 <div class="modal-content">
 
 <div class="modal-body" style="height: 410px;">
 
 <iframe  id="attachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div></div>
 
 
 
 <!-- div for showing past history  -->
 
 <div id="pastHistoryModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
 <div class="modal-dialog" id="modal-1gx" style="
    margin-left: 8%;
    margin-right: 5%;">
 <div class="modal-content" style=";width:140%;align:center;margin:auto;">
 <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Case Details</label>
</div>

 <div class="modal-body" style="height:410px;">
 <iframe id="pastHistoryFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" id="pastHistoryCloseBtn" data-dismiss="modal">Close</button>
 </div>
 </div></div>
 </div>
 
 
  <!-- div for  adding attachments   -->
 
 <div id="addAttachModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
 <div class="modal-dialog" id="modal-1gx" style="
    margin-left: 8%;
    margin-right: 5%;">
 <div class="modal-content" style=";width:140%;align:center;margin:auto;">
 <div class="modal-header" style="height: 36px;color:#FFF;">
<span class="glyphicon glyphicon-user"> </span><label>&nbsp;Attachments</label>
</div>

 <div class="modal-body" style="height:410px;">
 <iframe id="addAttachFrame" class="embed-responsive-item" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
 </div>
 <div class="modal-footer">
 <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
 </div>
 </div></div>
 </div>
 
 
<input type ="hidden" name="hospGovu" id= "hospGovu" value="${hospGovu}"/>
<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="testsCount" styleId="testsCount"/> 
<html:hidden name="patientForm" property="patientNo" styleId="patientNo" value="${patientForm.patientNo}"/>
<html:hidden name="patientForm" property="hospitalId" styleId="hospitalId"/>
<input type="hidden" name="investigationsSel" id="investigationsSel">
<html:hidden name="patientForm" property="personalHistVal" styleId="personalHistVal"/>
<html:hidden name="patientForm" property="examFndsVal" styleId="examFndsVal"/>
<html:hidden name="patientForm" property="speciality" styleId="speciality"/>
<html:hidden name="patientForm" property="otherDocDetailsList" styleId="otherDocDetailsList"/>
<html:hidden name="patientForm" property="drugs" styleId="drugs"/>
<html:hidden name="patientForm" property="symptoms" styleId="symptoms"/>
<html:hidden name="patientForm" property="hosptype" styleId="hosptype"/>
<html:hidden name="patientForm" property="cardNo" styleId="cardNo" value="${patientForm.cardNo}"/>
<html:hidden name="patientForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="patientForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="patientForm" property="therapyType" styleId="therapyType"/>
<html:hidden name="patientForm" property="therapyCategory" styleId="therapyCategory"/>
<html:hidden name="patientForm" property="therapy" styleId="therapy"/>
<html:hidden name="patientForm" property="personalHist" styleId="personalHist"/>
<html:hidden name="patientForm" property="gender" styleId="gender"/>
<html:hidden name="patientForm" property="years" styleId="years"/>
<html:hidden name="patientForm" property="months" styleId="months"/>
<html:hidden name="patientForm" property="days" styleId="days"/>
<html:hidden name="patientForm" property="scheme" styleId="scheme"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="dentalSpecApproval"/>
<html:hidden name="patientForm" property="dentalProc"/>
<html:hidden name="patientForm" property="consultFee" styleId="consultFee"/>
<html:hidden name="patientForm" property="investPrice" styleId="investPrice"/>
<html:hidden name="patientForm" property="totInvestPrice" styleId="totInvestPrice"/>
<html:hidden name="patientForm" property="totalOpCost" styleId="totalOpCost"/>
<html:hidden name="patientForm" property="otherInvestCount" styleId="otherInvestCount"/>
<html:hidden name="patientForm" property="otherDrugCount" styleId="otherDrugCount"/>
<html:hidden name="patientForm" property="opActiveMsg" styleId="opActiveMsg"/>
<html:hidden name="patientForm" property="otherSymptomCount" styleId="otherSymptomCount"/>

<html:hidden name="patientForm" property="postDist" styleId="postDist"/>
<html:hidden name="patientForm" property="stoCode" styleId="stoCode"/>
<html:hidden name="patientForm" property="ddoCode" styleId="ddoCode"/>
<html:hidden name="patientForm" property="deptHod" styleId="deptHod"/>

<html:hidden name="patientForm" property="checkType" styleId="checkType"/>
<html:hidden name="patientForm" property="enablePharma" styleId="enablePharma"/>
<html:hidden name="patientForm" property="hosType" styleId="hosType" value="${patientForm.hosType}"/>
<html:hidden name="patientForm" property="contactno" styleId="contactno" value="${patientForm.contactno}"/>
<html:hidden name="patientForm" property="fname" styleId="fname" value="${patientForm.fname}"/>


<script>

$(function(){
	var jq = jQuery.noConflict();
    jq('a, button').click(function() {
        jq(this).toggleClass('active');
    });
});



//getChronicOPType();
var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	//For validating maxlength onpaste event--For textarea fields
// 	document.getElementById("presentHistory").attachEvent("onpaste",pasteIntercept);
	/* document.getElementById("remarks").attachEvent("onpaste",pasteInterceptRemarks);
	document.getElementById("opRemarks").attachEvent("onpaste",pasteInterceptOpRemarks);
	if(document.getElementById("treatingDocLabel").style.display=='')
		{
		document.getElementById("treatingDocRmks").attachEvent("onpaste",pasteInterceptDocRemarks);
		} */
	}
else if(browserName == "Netscape")
	{
// 	document.getElementById("presentHistory").addEventListener("paste", pasteIntercept, false);
	//document.getElementById("remarks").addEventListener("paste", pasteInterceptRemarks, false);
	//document.getElementById("opRemarks").addEventListener("paste", pasteInterceptOpRemarks, false);
	/* if(document.getElementById("treatingDocLabel").style.display=='')
		{
		document.getElementById("treatingDocRmks").addEventListener("paste",pasteInterceptDocRemarks,false);
		} */
	}

		
		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
		document.getElementById("genTestTableID").style.display="";
		genOldList='${genInvestLst}'.split("@");
		genOldList.splice(genOldList.length-1,1);
		if(genOldList.length>0)
			document.forms[0].patientType[1].disabled=false;
		/*genInventCount=genInventList.length-1;*/
		}
		
		if('${PatientOpList.ipOpFlag}'!=null){
			if('${PatientOpList.ipOpFlag}'=='IP'){
			document.forms[0].patientType[1].disabled=false;
			document.forms[0].patientType[1].checked=true;
			document.getElementById('IPHead2').style.display='';
			document.getElementById("diagnosisData").style.display="";
			getDiagDtlsOnload();
			if ('${PatientOpList.docType}'!=null){
				setTimeout(function(){fn_getIPDoctorsList();}, 1999);}
			
			if('${invetLst}'!='')
    		{
    			document.getElementById("categoryTableID").style.display="";
    			specOld='${invetLst}'.split("@");
    			specOld.splice(specOld.length-1,1);    			
    			if(specOld.length>0){
        			
                var specialApprovalFlag="${dentalSpecialAppvFlag}";
               
        			if(specialApprovalFlag!=null)
        			{
        			    document.getElementById("normalProc").disabled=true;
        				
        				document.getElementById("SpecialProc").checked=true;
        			}
        			else
        			{
        				document.getElementById("SpecialProc").disabled=true;
        				
        				document.getElementById("normalProc").checked=true;
        			}
                    for(var i=0;i<specOld.length;i++){
                    	var inSpec = specOld[i].split("~");
                    	medOrSur=inSpec[0].substr(0,1);
                    }
    				
        		}  
    			/*catCount=spec.length-1;*/
    		}
			}
	        else if ('${PatientOpList.ipOpFlag}'=='OP')
	        	{
	        	if(document.getElementById("toDispatch"))
	        		document.getElementById("toDispatch").disabled=false;
	        	document.getElementById("OPHead2").style.display="";
	    		document.getElementById("prescriptionData").style.display="";
	    		document.getElementById("OPDoctor").style.display="";
	    		document.getElementById("diagnosisData").style.display="";
	    		//document.getElementById('IPHead2').style.display='none';
				//document.getElementById("diagnosisData").style.display="none";
	    		
	    		getDiagDtlsOnload();
	    		if ('${PatientOpList.docType}'!=null){
					setTimeout(function(){fn_getDoctorsList();}, 1999);
				}
	    		if('${drugsLst}'!='')
	    		{
	    			document.getElementById("drugsTable").style.display="";
	    			drugs='${drugsLst}'.split("#");
	    			
	    			drugCount=drugs.length-1;
	    		}	    		
	    		}
		}
		function OtherDrugValid(){
			var jq = jQuery.noConflict();
			if(jq("#drugSubTypeCode option:selected").text()=="Others"){
				document.getElementById("otherDrugsTd").style.display="";
				document.getElementById("otherDrugLabel").style.display="";
			}
			else{
				document.getElementById("otherDrugsTd").style.display="none";
				document.getElementById("otherDrugLabel").style.display="none";
			}
		}
		function validateInnerNum(input)
		{
			
			var hospGovu = document.getElementById("hospGovu").value;

			
			var a=input.value;
			var fr=partial(focusBox,input);
			var regAlphaNum=/^[0-9.]+$/;
			var hospId=document.getElementById("hospitalId").value;
			//if(hospId!=null && hospId!='EHS34')
			 if(hospGovu!=null && hospGovu!="G")
				{
				 
		     if(input.id=='GE1' || input.id=='GE2'){
		     	 document.getElementById('GE3').innerHTML='<input type="text" id="GE3" value="" readOnly/>';
		     }}
				
			if(a.trim()=="")
				{
				jqueryErrorMsg('Number Validation',"Blank spaces are not allowed for "+input.title,fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}
			 
			if(a.charAt(0)=="." || a.charAt(0)==" " || a.charAt(0)=="0")
			{
				jqueryErrorMsg('Number Validation',input.title+ " should not start with . or space or 0",fr);
				partial(focusBox,input);
				input.value="";
				return false;
			}
			 
			if(a.trim().search(regAlphaNum)==-1)
				{
				jqueryErrorMsg('Number Validation',"Only numbers and . are allowed for "+input.title,fr);
				partial(focusBox,input);
				input.value="";
				return false;
				}
			else
				input.value=a.trim();
			
			if(input.id=='GE1' || input.id=='GE2' || input.id=='GE11' || input.id=='GE12' || input.id=='GE13' || input.id=='GE14' || input.id=='GE15' || input.id=='BP1' || input.id=='BP2')
		if(input.value.split(".").length-1>1){
			jqueryErrorMsg('Number Validation',"Please Enter Correct Value in "+input.title,fr);
			partial(focusBox,input);
			input.value="";
			return false;
		}
			
			if(input.id=='GE1'){
				if(input.value>264){
					jqueryErrorMsg('Number Validation'," Height Should be in range of 0- 264 cm.",fr);
					partial(focusBox,input);
					input.value="";
					return false;
					}
					
				heightVar=input.value;
				
				
				var weightVar=document.forms[0].GE2.value;
				//if(hospId!="EHS34")
				if(hospGovu!=null && hospGovu!="G")
				{
				if(heightVar!=null && weightVar!='' && weightVar!=null){
					var heightVarr=heightVar*1/100;
					var bmiCal=((weightVar*1)/(heightVarr*heightVarr)).toFixed(2);
					document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
					}
				}}
			
			if(input.id=='GE2'){
				if(input.value>300){
					jqueryErrorMsg('Number Validation', " Weight Should be in range of 0- 300 Kg.",fr);
					partial(focusBox,input);
					input.value="";
					return false;
					}
				
				//if(hospId!="EHS34")
				if(hospGovu!=null && hospGovu!="G")
				{
				weightVar=input.value;
				var heightVar=document.forms[0].GE1.value;
				if(heightVar!=null && heightVar!='' && weightVar!=null){
				var heightVarw=heightVar*1/100;
				var bmiCal=((weightVar*1)/(heightVarw*heightVarw)).toFixed(2);
				document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
				}}			
				}
			if(input.id=='GE12'){
				if(input.value>250){
					jqueryErrorMsg('Number Validation', " Pulse rate should be in range of 0-250 per minute",fr);
					partial(focusBox,input);
					input.value="";
					return false;
					}		
				}
			if(input.id=='GE13'){
				if(input.value>60){
					jqueryErrorMsg('Number Validation', " Respiration should be in range of 0-60 per minute",fr);
					partial(focusBox,input);
					input.value="";
					return false;
					}		
				}
			if(input.id=='GE14'||input.id=='GE15'||input.id=='BP1'||input.id=='BP2'){
				if(input.value>300 || input.value<0){
					jqueryErrorMsg('Number Validation',"BP range should be between 0-300 ",fr);
					partial(focusBox,input);
					input.value="";
					return false;
					}
				}	
			if(input.id=='GE11'){	
				
				//if(hospId!="EHS34")
				if(hospGovu!=null && hospGovu!="G")
				
					{
					
				var a=input.value;
				var fr=partial(focusBox,input);
				var regAlphaNumT=/^[0-9.CF]+$/;
				var inputlength=input.value.length;
				var mainStrlength=inputlength-1;
				var substr=input.value.slice(-1);
				var mainstr=input.value.substring(0,mainStrlength);
				
				if(document.forms[0].temp[0].checked==true){
					
					if(input.value<24 || input.value>45){
						jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-45 C",fr);
						input.value="";
						return false;
						}
					}
			   else if(document.forms[0].temp[1].checked==true){
					if(input.value<75 || input.value>111){
						jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 75-111 F",fr);
						input.value="";
						return false;
						}
					}
				else{
					jqueryErrorMsg('Temperature Validation',"Please Select C or F",fr);
					input.value="";
					return false;
					}				
				}
				//else if(hospId!=null && hospId =="EHS34")
				else if(hospGovu!=null && hospGovu=="G")
					{
					
		    
		 	 
					var a=input.value;
					var fr=partial(focusBox,input);
					var regAlphaNumT=/^[0-9.CF]+$/;
					var inputlength=input.value.length;
					var mainStrlength=inputlength-1;
					var substr=input.value.slice(-1);
					var mainstr=input.value.substring(0,mainStrlength);

						if(input.value<24 || input.value>111){
							jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-111 C/F",fr);
							input.value="";
							return false;
							}
						
					
							
					}
			}
		}
		
function getDrugSubTypeList(id)
{
	var jq = jQuery.noConflict();
	var drugTypeCode= document.getElementById("drugTypeCode").value;
	if(document.getElementById("drugTypeCode").value=="-1" )
	{
// 		alert("Please select Drug Type");
		jq("#drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").val('-1');
		document.getElementById("medicatPeriod").value="";
		jq("#drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").select2().val('');
		document.getElementById("drugInfo").style.display="none";
		return false;
	}
else
	{
	var xmlhttp;var url;
    if (window.XMLHttpRequest)
    {xmlhttp=new XMLHttpRequest();}
    else if (window.ActiveXObject)
    {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
    else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
  xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            	    resultArray = resultArray.replace("[","");
    	    	    resultArray = resultArray.replace("@]",""); 
    	    	    resultArray = resultArray.replace("]",""); 
    	    	    resultArray = resultArray.replace("*",""); 
    				resultArray = resultArray.trim();
					var hospListData = resultArray.split(","); 
                	if(hospListData.length>1)
                	{  
                		document.forms[0].drugSubTypeCode.options.length=0;
                        document.forms[0].drugSubTypeCode.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<hospListData.length;i++)
               		 	{	
                     		var arr=hospListData[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null && arr[1].replace(/^\s+|\s+$/g,"")!='Others')
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].drugSubTypeCode.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
                	else
            		{
            			alert("No Drugs Found");
            			document.getElementById("drugTypeCode").value='-1';
            			jq("#drugTypeCode").select2().val('');
            			return false;
            		}
            	}
            	
        	}
        }
    }    	
// 	url = "./patientDetails.do?actionFlag=getDrugSubTypeList&callType=Ajax&drugTypeCode="+drugTypeCode;
	url = "patientDetails.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugTypeCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}	
}
		
function getHospitals(spclty,dist)
{
	var speciality= document.getElementById("asriCatName").value;
	var district= document.getElementById("district").value;
	if(document.getElementById("district").value=="-1" )
	{
		alert("Please select District");
		return false;
	}
	if(document.getElementById("asriCatName").value=="-1" )
	{
		alert("Please select Speciality");
		return false;
	}
else
	{
	var district=document.getElementById("district").value;
	var xmlhttp;var url;
    if (window.XMLHttpRequest)
    {xmlhttp=new XMLHttpRequest();}
    else if (window.ActiveXObject)
    {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
    else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
  xmlhttp.onreadystatechange=function()
    {
        if(xmlhttp.readyState==4)
        {
            var resultArray=xmlhttp.responseText;
            if(resultArray.trim()=="SessionExpired*"){
            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
                	resultArray = resultArray.replace("*",""); 
                	var hospListData = resultArray.split("@,"); 
                	if(hospListData.length>1)
                	{  
                		document.forms[0].currHospId.options.length=0;
                        document.forms[0].currHospId.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<hospListData.length;i++)
               		 	{	
                     		var arr=hospListData[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].currHospId.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
                	else
            		{
            			alert("No Hospitals found for the selected Speciality in selected District");
            			document.getElementById("currHospId").value="-1";
            			return false;
            		}
            	}
            	
        	}
        }
    }    	
	url = "./patientDetails.do?actionFlag=getHospitals&callType=Ajax&district="+district+"&speciality="+speciality;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}		
		
function getDiagDtlsOnload()
{
	if('${PatientOpList.diagnosisType}'!=null && '${PatientOpList.diagnosisType}'!='' && '${PatientOpList.diagnosisType}'!='-1')
	{
		getDiagnMainCatList();
		setTimeout(function()
		{		                                                     		  	           
			if('${PatientOpList.mainCatName}'!=null && '${PatientOpList.mainCatName}'!='' && '${PatientOpList.mainCatName}'!='-1')
			{
				document.getElementById("mainCatCode").value='${PatientOpList.mainCatName}';
			    document.forms[0].mainCatName.value='${PatientOpList.mainCatName}';
			    getDiagnCategoryList();
			    setTimeout(function()
				{
					if('${PatientOpList.catId}'!=null && '${PatientOpList.catId}'!='' && '${PatientOpList.catId}'!='-1')
					{
						document.getElementById("catCode").value='${PatientOpList.catId}';
				    	document.forms[0].catName.value='${PatientOpList.catId}';
				    	getDiagnSubCategoryList();
				    	setTimeout(function()
						{
							if('${PatientOpList.subCatName}'!=null && '${PatientOpList.subCatName}'!='' && '${PatientOpList.subCatName}'!='-1')
							{
								document.getElementById("subCatCode").value='${PatientOpList.subCatName}';
								document.forms[0].subCatName.value='${PatientOpList.subCatName}';
								getDiagnDiseaseList();
								setTimeout(function()
								{
									if('${PatientOpList.disMainId}'!=null && '${PatientOpList.disMainId}'!='')
									{
										document.getElementById("diseaseCode").value='${PatientOpList.disMainId}';
										document.forms[0].diseaseName.value='${PatientOpList.disMainId}';
										getDiagnDisAnatomicalList();
									}
									setTimeout(function()
									{
										if('${PatientOpList.disAnatomicalSitename}'!=null && '${PatientOpList.disAnatomicalSitename}'!=''&& '${PatientOpList.disAnatomicalSitename}'!='-1')
										{
											document.getElementById("disAnatomicalCode").value='${PatientOpList.disAnatomicalSitename}';
											document.forms[0].disAnatomicalName.value='${PatientOpList.disAnatomicalSitename}';
										}
									},1999);
								},1999);
							}
						}, 1999);
					   }
			    }, 1999);
			}
		}, 1999);
	}	
}
enable_dtrsform();





</script>

<script>
$("#genBlockInvestName").select2();
$("#currHospId").select2();
$("#district").select2();
$("#asriCatName").select2();
$("#drugTypeCode, #drugSubTypeCode,#routeType,#route,#strengthType,#strength,#dosage").select2();
$("#disNameAuto").select2();


</script>
<%--</logic:equal>--%>
</logic:equal> 
</logic:iterate> 
</logic:notEmpty>
</c:forTokens> 
</td></tr></table>
<html:hidden name="patientForm" property="biometricValue"  styleId="biometricValue" ></html:hidden>
<html:hidden name="patientForm" property="fingerPrint"  styleId="fingerPrint" ></html:hidden>
</form>
</div>

</body>




</fmt:bundle>
</html>



