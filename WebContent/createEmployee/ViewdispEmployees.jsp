<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%> 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
   <fmt:setLocale  value='<%=session.getAttribute("LangID")%>' /> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<script src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<script src="js/select2.min.js"></script>
<%-- 	<jsp:include page="/patient/DispPatientDetails.jsp"/> --%>
<style type="text/css">
.btn
{
border-radius:20px;
}
.modal-header
{
background-color:#0286AD;
}
.btn:hover
{
border-radius:5px;
}

*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}    
select:focus
{
  outline:#000 dotted 1px; 
} 
radio:focus
{
  outline:#000 dotted 1px; 
}
textarea:focus
{
  outline:#000 dotted 1px; 
}
checkbox:focus
{
  outline:#000 dotted 1px; 
}

</style>
 <script>

  
  function fn_search()
  {
	 if(document.forms[0].drugType.value=="-1"){
		 alert("Please select any criteria");
		 $("#drugType").select2('open');
		 return false;
	 }
  	var url="./patientDetails.do?actionFlag=viewDrugBalance";
  	document.forms[0].action=url;
  	 //document.forms[0].method="post";
  	 document.forms[0].submit();
//   	document.forms[0].drugType.value="-1";
//   	document.forms[0].dispDrugID.value="-1";
// 	   $("#dispDrugID,#dispDrugID").select2().val("");
  }
  function fn_csvDownload()
  {
  	var url="./patientDetails.do?actionFlag=viewDrugBalance&csvFlag=Y";
  	document.forms[0].action=url;
  	 //document.forms[0].method="post";
  	 document.forms[0].submit();
//   	document.forms[0].drugType.value="-1";
//   	document.forms[0].dispDrugID.value="-1";
// 	   $("#dispDrugID,#dispDrugID").select2().val("");
  }
  function fn_getDrugList()
	 {
	  document.forms[0].dispDrugID.value="-1";
	   $("#dispDrugID").select2().val("");
		var xmlhttp;
	    var url;
	    var drugType = document.getElementById("drugType").value;
	    if(drugType=='-1')
		   {  	   
	    	   document.forms[0].dispDrugID.value="-1";
	    	   $("#dispDrugID").select2().val("");
	       
		   return false;
		   }
	   else
		   {

		   if (window.XMLHttpRequest)
	  		{
	   		xmlhttp=new XMLHttpRequest();
	  		}
	  		else if (window.ActiveXObject)
	  		{		
	   		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  		}
	 		 else
	 		 {
	 			jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	  		 }   
	  		 url = "patientDetails.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugType;    
	  		 xmlhttp.onreadystatechange=function()
	  		 {
	      		if(xmlhttp.readyState==4)
	      		{
	   	   		var resultArray=xmlhttp.responseText;
	   	 	    resultArray = resultArray.replace("[","");
	    	    resultArray = resultArray.replace("@]",""); 
	    	    resultArray = resultArray.replace("]",""); 
	    	    resultArray = resultArray.replace("*",""); 
				resultArray = resultArray.trim();
	    	    
	    	   
	          		if(resultArray.trim()=="SessionExpired*")
	          		{
	          			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	          		}
	          		else
	          		{         	                                           
	                       		var drugsList = resultArray.split(","); 
	                   			if(drugsList.length>0)
	                   			{
	                       			document.forms[0].dispDrugID.options.length=0;
	                      			document.forms[0].dispDrugID.options[0]=new Option("--select--","-1");
	                       			for(var i = 0; i<drugsList.length;i++)
	                       			{	
	                            		var arr=drugsList[i].split("~");                     
	                           			if(arr[1]!=null && arr[0]!=null)
	                            		{
	                                		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                                		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                                                                              
	                                		document.forms[0].dispDrugID.options[i+1] =new Option(val1,val2);
	                            		}
	                           
	                        		}
	                    		}
	        		}
	      		}
	  		 }
	  		
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		   
		}
		 
	 }
  
  function deleteDrugRec(drugCode){
	  if(confirm("Do you want to Delete the drug set")){
	  var url="./patientDetails.do?actionFlag=deleteDrugSet&drugCode="+drugCode;
	  	document.forms[0].action=url;
	  	 //document.forms[0].method="post";
	  	 document.forms[0].submit();
		}
  }
  </script>
</head>
<!-- <body onload="fn_onLoad();"> -->
<body>
<br>
<form action="/patientDetails.do" method="post" name=createEmployeeForm>


<table  width="90%" style="margin:0 auto" cellspacing="4" cellpadding="4">
<tr>
<th width="30%" class="tbheader1">
<b>Employee Id</b>
</th>
<th width="30%" class="tbheader1">
<b>Employee Name</b>
</th>
</tr>
<tr>
<td width="30%" valign="top" class="tbcellBorder existDrugs">
<html:select name="createEmployeeForm" property="drugType" styleId="drugType" style="width:17em;" title="Select Drug Type"  onchange="fn_getDrugList()"  >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>



<td width="30%" valign="top" class="tbcellBorder existDrugs">
<html:select name="createEmployeeForm" property="dispDrugID" styleId="dispDrugID" style="width:17em;" title="Select Drug Name"    >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugsList" labelProperty="VALUE"/>
</html:select>
</td>
<td width="30%">
<button id="drugSearch" class="btn btn-primary" onclick="javascript:event.preventDefault(); fn_search()">Search</button>
</td>

</tr>
</table>
<!--<table width="90%" style="margin:0 auto" cellspacing="4" cellpadding="4">
<tr>
<td align="right">
<img src="images/csv1.png"  onmouseover="this.src='images/csv2.png'" onmouseout="this.src='images/csv1.png'" onclick="javascript: fn_csvDownload()"/>
</td>
</tr>
</table>-->
<table  width="90%" style="margin:0 auto" cellspacing="4" cellpadding="4">
<tr><th class="tbheader" colspan="2">DRUGS OUT STANDING BALANCE</th></tr>

<tr><td colspan="4" > 
<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
  
     
        <logic:present name="createEmployeeForm" property="drugLt">
        <bean:size  id="drugSize" name="createEmployeeForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
        <table  width="100%"  id="drugsTable" border="1">
         <tr>  
<%--       	<td width="5%"><b><fmt:message key="EHF.SNO"/></b></td>         --%>
        <th class="tbheader1"><b>Drug Type</b></th>
        <th class="tbheader1"><b>Drug Name</b></th>
         <th class="tbheader1"><b>Manufacturer Name</b></th>
        <th class="tbheader1"><b>Distributor Name</b></th>   
         <th class="tbheader1"><b>Batch No</b></th>
        <th class="tbheader1"><b>Expiry Date</b></th>      
       	<th class="tbheader1"><b>Current Balance</b></th>
       	<th>&nbsp;</th>
        </tr>
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="createEmployeeForm" property="drugLt" >
        <tr>  
<%--       	<td width="5%"><%=k++ %></td>         --%>
		<td width="10%"><bean:write name="drugLst" property="drugTypeName" /></td>
        <td width="10%"><bean:write name="drugLst" property="drugName" /></td>
        <td width="10%"><bean:write name="drugLst" property="dispDrugMfg" /></td>
        <td width="10%"><bean:write name="drugLst" property="dispDrugDsbtr" /></td>
        <td width="10%"><bean:write name="drugLst" property="batchNo" /></td>
        <td width="10%"><bean:write name="drugLst" property="expiryDt" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="outDrugsQuantity" /></td> 
       	<td width="10%"><input type="button" value="Delete" class="btn btn-primary" onclick="javascript:event.preventDefault();deleteDrugRec('<bean:write name="drugLst" property="dispDrugMstrDrgCode" />')" /></td>
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
<script>
$("#dispDrugID,#drugType").select2();
</script>

</form>
</body>
</html>