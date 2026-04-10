<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<title>Stock Ledger Report</title>

<script type="text/javascript">
function newexportToCsv(arg)
{
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
		
			document.forms[0].action = "reportsAction.do?actionFlag=drugreportCsv&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&reportType="+arg;
		
			document.forms[0].submit();  
	   
}

function fn_Print() {
	window.print();
}

function closeNrefresh()
{
	window.close();
}

</script>
</head>
<body>
<html:form action="/patientDetailsNew.do">

<logic:present name="patientForm" property="drugReportList">
<bean:size id="size" name="patientForm" property="drugReportList"/>

<logic:greaterThan value="0" name="size"> 


<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1" >S NO</th>
<th class="tbheader1" >ISSUED DATE</th>
<th class="tbheader1" >CASE ID</th>
<th class="tbheader1" >PATIENT NAME</th>
<th class="tbheader1" >MANUFACTURER NAME</th>
<th class="tbheader1" >DISTRIBUTER NAME</th>
<th class="tbheader1" >ISSUED QUANTITY</th>
<th class="tbheader1" >RECEIVED QUANTITY</th>
</tr>
<logic:iterate name="patientForm" property="drugReportList" id="labelBean" indexId="cnt">
<tr>
<td align="left" class="tbcellBorder" >${startIndex+cnt+1}</td>

    <logic:notEmpty name="labelBean" property="ISSUEDATE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ISSUEDATE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="ISSUEDATE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
     <logic:notEmpty name="labelBean" property="CASEID">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="CASEID"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="CASEID">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 
     <logic:notEmpty name="labelBean" property="PATIENT_NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PATIENT_NAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="PATIENT_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 
     <logic:notEmpty name="labelBean" property="MNFCTRNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="MNFCTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="MNFCTRNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  
 
 
<logic:notEmpty name="labelBean" property="DSTRBTRNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DSTRBTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DSTRBTRNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
 <logic:notEmpty name="labelBean" property="ISSUE_QUANT">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ISSUE_QUANT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="ISSUE_QUANT">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>
 
 <logic:notEmpty name="labelBean" property="RCVD_QUANT">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="RCVD_QUANT"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="RCVD_QUANT">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
</tr>
</logic:iterate>
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="patientForm" property="drugReportList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty>
	<table style="width:100%;align:center">
	<tr class="print_buttons">
			<td align="center">
				<button class="but" type="button" id="Print" onclick="fn_Print();">Print</button>
				<button class="but"  type="button" id="close" onclick="javascript:closeNrefresh();">Close</button></td>
			</td>
		</tr>	
		</table>	
<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />

</html:form>


</body>
</html>