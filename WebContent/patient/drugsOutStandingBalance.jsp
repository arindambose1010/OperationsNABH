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
/* Chandana - 8251 - added belo styles for div scroll instead fo page scroll */
.scrollable-div {
    max-height: 291px; 
    overflow-y: auto; 
    overflow-x: hidden;
    border: 1px solid;
}
.scrollable-div::-webkit-scrollbar {
    width: 5px;
}
.scrollable-div::-webkit-scrollbar-track {
    background: #f1f1f1;
}
.scrollable-div::-webkit-scrollbar-thumb {
    background: #888;
    border-radius: 4px;
}
.scrollable-div::-webkit-scrollbar-thumb:hover {
    background: #555;
}
</style>
 <script>

 function fn_generateCSV()
 {
	 
 	var url="./patientDetails.do?actionFlag=viewDrugBalance&csvFlag=Y";
 	document.forms[0].action=url;
 	document.forms[0].submit();

 }
 
 
  
  function fn_search()
  {
	 if(document.forms[0].drugType.value=="-1" && document.forms[0].searchType.value==""){
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
//Chandana - 8251 - Added below togglediv function for dispaying the upload option based on the conditions
function toggleUploadDiv() {
	var flag = '${flag}';
	var selectedValue = document.getElementById("searchType").value;
	var uploadDiv = document.getElementById("uploadDiv");	
	/* if (selectedValue === "Avblbatch" || selectedValue === "Allbatch") { */
	if (selectedValue === "Avblbatch") {
		if(flag == 'N'){
			uploadDiv.style.display = "block";
		}
	} else {
		uploadDiv.style.display = "none";
	}
}
// Optional: Hide uploadDiv initially on page load
window.onload = function() {
	toggleUploadDiv();
};

//Chandana - 8251 - Added the below function for uploading the excel
function fn_upload() {
    const fileInput = document.getElementById('id_File_Inc');
    var selectedFile = fileInput.files[0];    
    if (!selectedFile) {
        alert("Please upload file");
        return;
    }
    var fileName = selectedFile.name.toLowerCase();
    if (!(fileName.endsWith(".xls") || fileName.endsWith(".xlsx"))) {
        alert("Please upload an Excel file (.xls or .xlsx)");
        fileInput.value = ''; // clear invalid file
        return;
    }
    var confirmUpload = confirm("Are you sure you want to upload the excel?");
    if (confirmUpload) {
        var url = "patientDetailsNew.do?actionFlag=UploadFile";
        var data = new FormData();
        data.append('id_File_Inc', selectedFile);
        data.append('fileName', selectedFile.name);

        var xhttps = new XMLHttpRequest();
        xhttps.onload = function () {
            if (this.status === 200) {
                /* alert("File uploaded successfully");
                window.location.reload();
                fileInput.value = ''; */
                var responseMsg = JSON.parse(this.responseText);
                
                if (responseMsg === "File uploaded successfully") {
                	alert(responseMsg);
                	window.location.reload();
                    fileInput.value = '';
                }else{
                	alert(responseMsg);
                    fileInput.value = '';
                }
            }
            else{
            	alert("Error uploading file.");
            }
        };
        xhttps.open("POST", url, true);
        xhttps.send(data);
    }
}

function validateAttachment(vFileName){
	if(vFileName == null || vFileName=='')
	{
		alert("Please upload file");
		flag = false;
		return;
	}
	vSplit=vFileName.split("\\");
	vFileName = vSplit[(vSplit.length)-1];	
	var pos=vFileName.lastIndexOf(".");
	var sub=vFileName.substring(pos+1,vFileName.length); 
	if((sub=='xls')||(sub=='XLS')  )
	{
		flag = true;
	} 
	else
	{
		flag = false; 
		alert("Please upload XLS file");
		return;
	}
	/* var flag = chkSpecailCharsAttach(vFileName);
	if(!flag)
	{
		alert("Uploaded file can not contain special characters.Please verify and upload again");
		flag = false; 
		return;
	} */
	return flag;
}
  </script>
</head>
<!-- <body onload="fn_onLoad();"> -->
<body>
<br>
<form action="/patientDetails.do" method="post" name="patientForm" enctype="multipart/form-data"><!-- Chandana - 8251 - Added enctype for taking the file to backend -->


<table  width="90%" style="margin:0 auto" cellspacing="4" cellpadding="4">
<tr>
<th width="30%" class="tbheader1">
<b>Drug Type</b>
</th>
<th width="30%" class="tbheader1">
<b>Drug Name</b>
</th>
<th width="30%" class="tbheader1">
<b>Search Type</b>
</th>
</tr>
<tr>
<td width="30%" valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugType" styleId="drugType" style="width:17em;" title="Select Drug Type"  onchange="fn_getDrugList()"  >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>



<td width="30%" valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:17em;" title="Select Drug Name"    >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugsList" labelProperty="VALUE"/>
</html:select>
</td>
<td width="30%" valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="searchType" styleId="searchType" style="width:17em;height:28px;" title="Select Search Type"  >
		<html:option value="Avbl">Available Drugs with out Batch No</html:option>
		<html:option value="All">All Drugs with out Batch No</html:option>
		<html:option value="Avblbatch">Available Drugs With Batch No</html:option>
		<html:option value="Allbatch">All Drugs with Batch No</html:option>
</html:select>
</td>
<td width="30%">
<button id="drugSearch" class="btn btn-primary" onclick="javascript:event.preventDefault(); fn_search()">Search</button>
</td>
<td>
<button id="Excel" class="btn btn-warning"  onclick="javascript:fn_generateCSV();">Excel</button>
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
<tr><th class="tbheader" colspan="2">TOTAL AVAILABLE DRUGS</th></tr>

<tr><td colspan="4" > 
<div id='drugsContent' style="overflow:auto; overflow-y:hidden"> 
  
     
        <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
        <div class="scrollable-div"><!-- Chandana - 8251 - Added this div for makinf scroll for the list of drugs displaying -->
        <table  width="100%"  id="drugsTable" border="1">
         <tr>  
       	<th class="tbheader1"><b>S NO</b></th>         
        <th class="tbheader1"><b>Drug Type</b></th>
        <!-- Chandana - 8251 - Added below column, it will display based on the condition -->
        <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}">  
         <th class="tbheader1"><b>Drug Code</b></th>
        </c:if>
        <th class="tbheader1"><b>Drug Name</b></th>
         <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}">  
         <th class="tbheader1"><b>Manufacturer Name</b></th>
          </c:if>
          <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}">  
        <th class="tbheader1"><b>Distributor Name</b></th> 
         </c:if>
         <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}">  
         <th class="tbheader1"><b>Batch No</b></th>
         </c:if>
         <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}"> 
        <th class="tbheader1"><b>Expiry Date</b></th>  
         </c:if>    
       	<th class="tbheader1"><b>Current Balance</b></th>
       	
        </tr>
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td style="text-align:center;" width="5%"><%=k++ %></td>         
		<td width="10%"><bean:write name="drugLst" property="drugTypeName" /></td>
		<!-- Chandana - 8251 - Added below column, it will display based on the condition -->
		<c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}">  
         <td width="10%"><bean:write name="drugLst" property="dispDrugMstrDrgCode" /></td>
        </c:if>
        <td width="10%"><bean:write name="drugLst" property="drugName" /></td>
        <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}"> 
        <td width="10%"><bean:write name="drugLst" property="dispDrugMfg" /></td>
        </c:if>
        <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}"> 
        <td width="10%"><bean:write name="drugLst" property="dispDrugDsbtr" /></td>
        </c:if>
        <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}">  
        <td width="10%"><bean:write name="drugLst" property="batchNo" /></td>
        </c:if>
        <c:if test="${searchType eq 'Avblbatch' || searchType eq 'Allbatch'}"> 
        <td width="10%"><bean:write name="drugLst" property="expiryDt" /></td> 
        </c:if>  
       	<td width="10%"><bean:write name="drugLst" property="outDrugsQuantity" /></td> 
       
        </tr>
        </logic:iterate>
        </table>
        </div>
        </logic:greaterThan>
        <logic:equal value="0" name="drugSize">
        <p style="margin: 10px auto; text-align: center;"><b>No Records Found</b></p>
        </logic:equal>
        </logic:present>    
        </div>
        <!-- Chandana - 8251 - Added below div for uploading option -->
        <div class="row" id="uploadDiv" style="display:none;">
			<div class="form-group col-xs-12 col-sm-3 col-md-3 col-lg-3">
				<label for="component">Upload Excel<span class="required" style="color: red;"> * </span></label>
				<input type="file" name="id_File_Inc" id="id_File_Inc" class="FieldBlack" accept=".xls, .xlsx" onmousedown="right(this)"  onmouseup="right(this)"> 
			</div>
			<div class="form-group col-xs-12 col-sm-4 col-md-4 col-lg-4" style="margin-right:-9%;">
				<button type="button" class="btn btn-success" name="btn_OPEntry" onclick="javascript:fn_upload()" value="Upload" id="id_btnUploadEntry" class="ActionButton" style="background-color:#108a4d">Upload</button>
				<!-- <button type="button" class="btn btn-success" name="btn_OPEntry" onclick="saveExcelData()" value="Upload" id="id_btnOPEntry" class="ActionButton" style="background-color:#108a4d">Submit</button> -->
			</div>
		</div>
</td></tr>

</table>
<script>
$("#dispDrugID,#drugType").select2();
</script>

</form>
</body>
</html>