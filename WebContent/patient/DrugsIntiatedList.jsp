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

<title>Drugs transferred List</title>
</head>
<script>
function fn_DrugsIntiatedList(reqId){
	var url='patientDetailsNew.do?actionFlag=viewDrugsIntiatedList&requestId='+reqId;  
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
<div id="processImagetable" style="top:50%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:400" >
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
		<th colspan="6" class="tbheader1">Drugs Transfer Request List</th>
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
        <th class="tbheader1"><b>Request Id</b></th>
        <th class="tbheader1"><b>Wellness Center</b></th>
        <th class="tbheader1"><b>Total Quantity</b></th>
        <th class="tbheader1"><b>Sent Date</b></th>
           
  
       	
        </tr>
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td style="text-align:center;" width="5%"><%=k++ %></td>   
      	<td width="10%"><a href="javascript:fn_DrugsIntiatedList('<bean:write name="drugLst" property="route" />')"><bean:write name="drugLst" property="route" /></a></td>       
		<td width="10%"><bean:write name="drugLst" property="routeName" /></td> 
        <td width="10%"><bean:write name="drugLst" property="quantity" /></td>
        <td width="10%"><bean:write name="drugLst" property="crtDt" /></td>   
       	 </tr>
        </logic:iterate>
        </table>
        
        </logic:greaterThan>
        <logic:equal value="0" name="drugSize">
        <p style="margin: 10px auto; text-align: center;"><b>No Records Found</b></p>
        </logic:equal>
        </logic:present>    
        </div>
</td></tr>

</table>
</html:form>
</body>
</html>