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
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">

<script src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<%@ include file="/common/includeCalendar.jsp"%> 
<link href="css/select2.min.css" rel="stylesheet">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">

<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>

<script src="js/select2.min.js"></script>
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
<style>
	

.ui-menu .ui-menu-item {
        margin:0;
        padding: 0;
        width: 200px;
        list-style-type: none;
}
</style>	
 <script>
function fn_saveInventoryDetails()
  {	
	  if( document.getElementById("manufacturerName").value==null ||  document.getElementById("manufacturerName").value=='')
			 {
			 alert("Please enter Manufacturer Name");
			 document.getElementById("manufacturerName").focus();
			 return false;
			 }
		 else
			 {
		 if(confirm("Do you want to Submit?"))
		 {
		     $(':input[type="button"]').prop('disabled', true);

		  var url="./patientDetailsNew.do?actionFlag=saveManfctDrugDetailsNew"
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
		 }
  }
  } 
  function fn_onLoad()
  {
	 var status="${status}";
	//alert(status);
	 if(status!=null)
		 {
		 if(status=="Yes")
			 {
			 alert("Details have been added successfully.");
			 document.getElementById("manufacturerName").value="";
			
			 }
		
		 
		 }
  }
	
function fn_checkMftName(name)
	  {
		  var mftName=name.value;
		  if( mftName!=null && mftName!="" )
			 {
			 var regAlphaNum=/^[0-9a-zA-Z- ]+$/;
			 if(mftName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and - are allowed in Manufacturer Name");
				 var dID=name.id;
				 document.getElementById(dID).value="";
				 document.getElementById(dID).focus();
				 return false;
				 }
			 name.value=mftName.replace(/  +/g, ' ');
 }
	  }
function fn_checkDrug()
{
		 var manufacturerName=document.getElementById("manufacturerName").value;
		 document.getElementById("manufacturerName").value=manufacturerName.replace(/  +/g, ' ');
		 var list ="${mftList}".replace("[","").replace("]","").trim().split(",");
		    for(var i=0;i<list.length;i++){
		    	var temp=list[i];
		    	var arr=temp.split("-");
		    	var tempp=arr[0].toUpperCase().replace(/^\s+/g, '');
		        var ref=manufacturerName.toUpperCase();
			 if(ref == tempp)
				 {
				 alert("Manufacturer Name already exists! Please enter New Manufacturer Name.");
				 document.getElementById("manufacturerName").value="";
				 document.getElementById("manufacturerName").focus();
				 return false;
				 }
			
	  
}
}		    
	
  </script>
</head>
<body onload="fn_onLoad();">
<br>
<form action="/patientDetailsNew.do" method="post" name=patientForm>

<table  id="drugForm" width="100%" style="margin:0 auto" >
<tr><th class="tbheader" colspan="2">MANUFACTURER ADDITION FORM </th></tr>
<tr>
<td width="30%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="26%" class="tbcellBorder"  title="Manufacturer Name">
<html:text name="patientForm"  property="manufacturerName" styleId="manufacturerName" maxlength="30"  onkeyup="fn_checkMftName(this)" onchange="javascript:fn_checkDrug()"></html:text></td>
</tr>
<tr><td class="tbcellBorder" colspan="2" align="center">
<button type="button" class="btn btn-primary" id="Submit" onclick="javascript:fn_saveInventoryDetails()"  title="Click Here To Submit"> Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</td></tr>
</table>

 

</form>
</body>
</html>