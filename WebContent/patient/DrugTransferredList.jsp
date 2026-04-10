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
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 

<title>Drugs Transferred List</title>
</head>
<script>
$(function() {
	
	
	var status ='${status}';
	
	if(status != null && status == 'Yes' ){
		alert("Drugs have been credited successfully");
		fn_goBack();
		
		}
	
});
function fn_goBack(){
	$('#Submit').prop('disabled',true);
	$('#back').prop('disabled',true);
	fn_loadImage();
	var url='patientDetailsNew.do?actionFlag=viewDrugTransferredList'; 
	document.forms[0].action=url;
	 document.forms[0].method="post";
	 document.forms[0].submit(); 
}
function fn_saveDrugDetails(){
	fn_loadImage();
	$('#Submit').prop('disabled',true);
	$('#back').prop('disabled',true);
	var list = '${drugList}';
	var url='patientDetailsNew.do?actionFlag=saveDrugtransferList&drugList='+list; 
	document.forms[0].action=url;
	 document.forms[0].method="post";
	 document.forms[0].submit(); 
}
function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 

}
</script>
<body onload="javascript:fn_removeLoadingImage();">
<html:form action="/patientDetailsNew.do" >
<!-- Progress Bar -->
<div id="processImagetable" style="top:35%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:300" >
   <tr>
      <td>
        <div id="processImage" align="center">
          <img src="images/Progress.gif" width="100"
                  height="100" border="0"></img>
         </div>
       </td>
     </tr>
  </table>
</div>

<table style="width:90%;margin:1% auto">
	<tr>
		<th colspan="6" class="tbheader1">Drugs Transferred List</th>
	</tr>
</table>
<table  width="100%" style="margin:0 auto" cellspacing="4" cellpadding="4">


<tr><td colspan="4" > 
<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
  
     
        <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
        <table  width="100%"  id="drugsTable" border="1">
         <tr>  
       	<th class="tbheader1"><b>S NO</b></th>         
        <th class="tbheader1"><b>Drug Type</b></th>
        <th class="tbheader1"><b>Drug Code</b></th>
        <th class="tbheader1"><b>Drug Name</b></th>
        <th class="tbheader1"><b>Manufacturer Name</b></th>
        <th class="tbheader1"><b>Distributor Name</b></th>   
        <th class="tbheader1"><b>Batch No</b></th>
        <th class="tbheader1"><b>Expiry Date</b></th> 
		<th class="tbheader1"><b>Quantity Added Date</b></th>
		<th class="tbheader1"><b>Quantity</b></th>
		<th class="tbheader1"><b>Wellness Center</b></th>    
  
       	
        </tr>
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td style="text-align:center;" width="5%"><%=k++ %></td>         
		<td width="10%"><bean:write name="drugLst" property="drugTypeName" /></td>
		<td width="10%"><bean:write name="drugLst" property="drugCode" /></td>
        <td width="10%"><bean:write name="drugLst" property="drugName" /></td>
        <td width="10%"><bean:write name="drugLst" property="dispDrugMfg" /></td>
        <td width="10%"><bean:write name="drugLst" property="dispDrugDsbtr" /></td>
        <td width="10%"><bean:write name="drugLst" property="batchNo" /></td>
        <td width="10%"><bean:write name="drugLst" property="expiryDt" /></td> 
        <td width="10%"><bean:write name="drugLst" property="crtDt" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="quantity" /></td>
       	<td width="10%"><bean:write name="drugLst" property="routeName" /></td> 
       
        </tr>
        </logic:iterate>
       
        </table>
        <table  width="100%" style="margin:0 auto">
        <tr align="center"><td >
           <button type="button" class="btn btn-primary" id="Submit" onclick="javascript:fn_saveDrugDetails()"  title="Click Here To Acknowledge"> OK&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
        </td></tr>
        
        </table>
        </logic:greaterThan>
        <logic:equal value="0" name="drugSize">
        <p style="margin: 10px auto; text-align: center;"><b>No Records Found</b></p>
        </logic:equal>
        </logic:present>    
        </div>
</td></tr>
   <table  width="100%" style="margin:0 auto">
        <tr align="center"><td >
           <button type="button" class="btn btn-primary" id="back" onclick="javascript:fn_goBack();"  title="Click Here To go back"> Back&nbsp;</button>
        </td></tr>
</table>
</table>

</html:form>
</body>
</html>