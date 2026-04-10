
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%>

<html>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<script src="js/DateTimePicker.js"></script>
	<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>
<title>Insert title here</title>

<link href="css/static.jquery.dataTables.css" rel="stylesheet">
<script type="text/javascript" src="js/jquery-dataTables.min.js"></script>
<script type="text/javascript" src="js/dataTables-buttons.min.js"></script>
<script type="text/javascript" src="js/static.jquery.dataTables.js"></script>


<script type="text/javascript">
$(document).ready(function(){
	var $ = jQuery.noConflict();
	$('#regPatientView').DataTable({
			dom: 'lBfrtip',
			buttons: [{
				title: 'PHC Center',
					exportOptions: {
		                columns: ':not(:last-child)'  
		            }
			}],
			columnDefs: [{
				"targets": '_all',
				"orderable": false
			}],
			 searching: true
		});  
});

</script>
<style>
.tbheader1 {
    background-color:#75b4ed;/* light blue or any soft color */
    color: #333333;            /* dark text for contrast */
    font-weight: bold;
    padding: 8px;
    border: 1px solid #ccc;
}

</style>
<script>

function openCase(patientId,caseStatus)
{
	var lhsRegFlag = document.getElementById('regFlag').value;
	var lhsApproveFlag = document.getElementById('lhsApproveFlag').value;
	var caseStatusCode = "";
	caseStatus = caseStatus.trim();
	switch(caseStatus) {
		case "Approved":
			caseStatusCode = "A";
			break;
		case "Waiting For Approval":
			caseStatusCode = "WA";
			break;
		case "Recommended For Approval":
			caseStatusCode = "RA";
			break;
		case "Rejected":
			caseStatusCode = "R";
			break;
		case "Returned":
			caseStatusCode = "REV";
			break;
		default:
			caseStatusCode = ""; 
	}
	
	parent.fn_loadImage();
		   document.forms[0].action="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&editLhsMember=True&patientId=" + patientId + "&regFlag=" + lhsRegFlag +
				                    "&approveFlag="+lhsApproveFlag + "&caseStatusCode=" + encodeURIComponent(caseStatusCode); 

		  document.forms[0].method="post";
		  document.forms[0].submit();
		
}
function fn_Print(patientId,caseStatus){
	var caseStatusCode = "";
	caseStatus = caseStatus.trim();
	switch(caseStatus) {
		case "Approved":
			caseStatusCode = "A";
			break;
		case "Waiting For Approval":
			caseStatusCode = "WA";
			break;
		case "Recommended For Approval":
			caseStatusCode = "RA";
			break;
		case "Rejected":
			caseStatusCode = "R";
			break;
		case "Returned":
			caseStatusCode = "REV";
			break;
		default:
			caseStatusCode = ""; 
	}
	 window.open('./patientDetailsNew.do?actionFlag=lhsRegisteredMembersPrint' +
	            '&lhsRegNo=' + encodeURIComponent(patientId) +
	            '&caseStatus=' + encodeURIComponent(caseStatusCode),
	            'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');

}

function fnSearch()
{           parent.fn_loadImage();
            var patientId = document.getElementById("patientNo").value;
            var status = document.getElementById("status").value;
            var lhsApprove= document.getElementById("lhsApproveFlag").value;
            
            if (!patientId && !status) {
                alert("Please enter a Patient No (or) Status.");
                return;
            }
	var flag= 'search';
	var url=null;
	//var lhsRegFlag = document.getElementById('regFlag').value;
	var lhsRegFlagElement = document.getElementById('regFlag');
    var lhsRegFlag = lhsRegFlagElement ? lhsRegFlagElement.value : '';
	if(lhsRegFlag == 'Y'){
		// url="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&regFlag=Y&flag="+flag+"&patientId="+patientId;
	       url="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&regFlag=Y&flag="+ flag
	               + "&patientId=" + encodeURIComponent(patientId)
	               + "&caseStatusCode=" + encodeURIComponent(status);
	}else{
		// url="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&flag="+flag+"&patientId="+patientId;
		url="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&flag="+flag
            + "&patientId=" + encodeURIComponent(patientId)
            + "&caseStatusCode=" + encodeURIComponent(status)
            + "&approveFlag="+ encodeURIComponent(lhsApprove);
	}
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit(); 
		
}


function resetSearch()
{ 
	parent.fn_loadImage();
	var url =null;
//	var lhsRegFlag = document.getElementById('regFlag').value;
    var lhsRegFlagElement = document.getElementById('regFlag');
    var lhsRegFlag = lhsRegFlagElement ? lhsRegFlagElement.value : '';
	if(lhsRegFlag == 'Y'){
		 url="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&regFlag=Y";
	}else{
		 url="./patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers";
	}
    
	document.getElementById("patientNo").value="";
	 var statusDropdown = document.getElementById("status");
	    if (statusDropdown) {
	        statusDropdown.value = "WA";
	    }
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
}


window.onload = function () {
  var lhsApproveFlag = document.getElementById('lhsApproveFlag').value;
  var statusSelect = document.getElementById('status');

  if (lhsApproveFlag === 'Y') {
    // Loop through options and remove the one with value "WA"
    for (var i = 0; i < statusSelect.options.length; i++) {
      if (statusSelect.options[i].value === 'WA') {
        statusSelect.remove(i);
        break; // Exit loop after removing
      }
    }
  }
};

</script>
</head>
<body>
<form action="/patientDetailsNew.do" method="post">
<div class="container-fluid">
   <table class="tbheader">
 <tr><th><b>REGISTERED MEMBERS VIEW</b></th></tr>
</table>

	<table width="100%" class="tb">
		<tr>
			<td  width="25%" class="labelheading1 tbcellCss" align="center"><b>Member ID </b>
			<td width="25%" class="tbcellBorder"><html:text name="patientForm"  property="patientNo" maxlength="12" styleId="patientNo" title="Enter Patient No" style="WIDTH: 10em;"onkeyup="this.value = this.value.toUpperCase();"/></td>
		    <td  width="25%" class="labelheading1 tbcellCss" align="center"><b>Status </b>
		    <td width="25%" class="tbcellBorder">
		        <select name="status" id="status" style="width: 13em;">
		            <c:choose>
				        <c:when test="${regFlag eq 'Y'}">
				            <option value="WA" <c:if test="${caseStatus eq 'WA'}">selected</c:if>>Waiting For Approval</option>
				            <option value="RA" <c:if test="${caseStatus eq 'RA'}">selected</c:if>>Recommended For Approval</option>
				            <option value="A"  <c:if test="${caseStatus eq 'A'}">selected</c:if>>Approved</option>
				            <option value="R"  <c:if test="${caseStatus eq 'R'}">selected</c:if>>Rejected</option>
				            <option value="REV" <c:if test="${caseStatus eq 'REV'}">selected</c:if>>Returned</option>
				        </c:when>
				
				        <c:when test="${lhsApproveFlag eq 'Y'}">
				            <option value="RA" <c:if test="${caseStatus eq 'RA'}">selected</c:if>>Recommended For Approval</option>
				            <option value="R"  <c:if test="${caseStatus eq 'R'}">selected</c:if>>Rejected</option>
				            <option value="A"  <c:if test="${caseStatus eq 'A'}">selected</c:if>>Approved</option>
				        </c:when>
				
				        <c:otherwise>
				            <option value="WA" <c:if test="${caseStatus eq 'WA'}">selected</c:if>>Waiting For Approval</option>
				            <option value="RA" <c:if test="${caseStatus eq 'RA'}">selected</c:if>>Recommended For Approval</option>
				            <option value="R"  <c:if test="${caseStatus eq 'R'}">selected</c:if>>Rejected</option>
				            <option value="REV" <c:if test="${caseStatus eq 'REV'}">selected</c:if>>Returned</option>
				        </c:otherwise>
				    </c:choose>
		            
		        </select>
		    </td>
		</tr>
		
		<tr>
			<td colspan="2" align="right">  <button class="but"  type="button" onclick="javascript:fnSearch()">Search</button></td>
			<td> <button class="but"  type="button" onclick="resetSearch()">Reset</button></td>
		</tr>
	
	</table>

<br>
  <logic:notEmpty name="registeredPatientsList"> 
 
<table width="100%" class="tb" id="regPatientView">
	<thead>
	   	<tr >
		<th class="tbheader1" style="width:10%">Member ID</th>
		<th class="tbheader1" style="width:20%">Member Name</th>
		<th class="tbheader1" style="width:10%">Gender</th>
		<th class="tbheader1" style="width:10%">Mobile No</th>
		<th class="tbheader1" style="width:10%">Member Type</th>
		<th class="tbheader1" style="width:10%">Registration Date</th>
		<th class="tbheader1" style="width:10%">Status</th>
		<th class="tbheader1" style="width:10%">View</th>
		<th class="tbheader1" style="width:10%">Print</th>
	
	</tr>
</thead>
<tbody>
  <logic:iterate name="registeredPatientsList" id="patientVO"> 
	<tr>
	    <td class="tbcellBorder" align="center"><bean:write name="patientVO" property="patientId"/></td>
	    <td class="tbcellBorder" align="center"><bean:write name="patientVO" property="firstName"/></td>
		<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="gender"/></td>
		<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="mobile"/></td>
		<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="type1"/></td>
		<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="regHospDt"/></td>
	
	     <td class="tbcellBorder" align="center"><bean:write name="patientVO" property="caseStatus"/></td>
	    <td class="tbcellBorder" align="center">
		  <a href="javascript:openCase('<bean:write name="patientVO" property="patientId"/>', 
		          '<bean:write name="patientVO" property="caseStatus"/>')" 
			   title="Edit">
			    <i class="fa fa-pencil" style="color:#007bff; font-size:16px;"></i>
			</a>

		</td>
		<td class="tbcellBorder" align="center">
		    <a href="javascript:fn_Print(
		        '<bean:write name="patientVO" property="patientId"/>',
		        '<bean:write name="patientVO" property="caseStatus"/>'
		    )" title="Print">
		        <i class="fa fa-print" style="color:#28a745; font-size:16px;"></i>
		    </a>
		</td>
				
	    
	</tr>
 </logic:iterate> 

 </tbody>
</table>
 
 </logic:notEmpty> 
 
  <input type="hidden" id="regFlag" name="regFlag" value="${regFlag}" />
  <input type="hidden" id="lhsApproveFlag" name="lhsApproveFlag" value="${lhsApproveFlag}" />
  
 <c:if test="${not empty noRecords}">
  <table style="margin-top: 6%;margin-left: 44%;font-size: 15px;">
    <tr>
        <td colspan="4" style="color:black; text-align:center;">
            ${noRecords}
        </td>
    </tr>
   </table>
</c:if>
</div>
</form>
</body>
</html>