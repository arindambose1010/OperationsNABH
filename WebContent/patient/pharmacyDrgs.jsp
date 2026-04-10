 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.Calendar"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>



<%@ include file="/common/include.jsp"%>


<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration"/>
<html>
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
.custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<head>
<title>Insert title here</title>
<script src=js/jquery-1.9.1.min.js></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>
<script LANGUAGE="JavaScript" SRC="Preauth/maximizeScreen.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>
<%@ include file="/common/includePatientDetails.jsp"%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<!--<script LANGUAGE="JavaScript" SRC="scripts/clinicalNotes.js"></script>-->
<script LANGUAGE="JavaScript" type="text/javascript"   SRC="js/clinicalNotes.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">  
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<link href="css/select2.min.css" rel="stylesheet">

<script src="js/select2.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style>
.coverScreenDiv{
	z-index:100;position:fixed;left:0;top:0;width:100%;height:100%;
	background-color:transparent;
	background: rgba(255,255,255,0.9);
	filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=#90FFFFFF,endColorstr=#90FFFFFF); 
	zoom: 1;
	}
</style>

<script type="text/javascript">
var labGrp = "${labGrp}";
var x = "${enablePharma}";

function coverScreen(){
	$("<div class='coverScreenDiv' id='csDiv'><div> ").appendTo('body').fadeOut(0).fadeIn(300);
	$('#csDiv').append('<div id="processImage22" align="center" style="position:relative;float:middle;">	<img src="images/Progress.gif" style="margin-top:200px" border="0" tabIndex="3"></img>	</div>');
	$('#processImage22').show();
};
//This method removes the above added transparent div.
function removeScreenCover(){
	$('#processImage22').hide();
	$(".coverScreenDiv").fadeOut(300, function(){$(".coverScreenDiv").remove();});
};


</script>

<script>



function getQuant(dbtr,k)
{
	var drgType=document.getElementById("drugType"+k).value;
	var drgName=document.getElementById("drugName"+k).value;
	var mftr=document.getElementById("mftr"+k).value;
	
	
	if(dbtr==null && k==null )
	{
 		alert("Please select Distributer");
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
            	removeScreenCover();
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
					var drugData = resultArray.split("~"); 
                	document.getElementById("avlQuant"+k).value=drugData[0];
                	document.getElementById("drgId"+k).value=drugData[1];
                	document.getElementById("dbtr"+k).value=dbtr;
                	removeScreenCover();
            	}
            	
            	
        	}
        }
    }    	
	url = "patientDetails.do?actionFlag=getQuantAjax&callType=Ajax&drgType="+drgType+"&drgName="+drgName+"&mftr="+mftr+"&dbtr="+dbtr;
	coverScreen();
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}	
}

function fn_backDrugs()
{
	//var url="./patientDetails.do?actionFlag=ViewRegisteredPatients&fromDispnsry=Y";
	var url="./patientDetails.do?actionFlag=ViewRegisteredPatients&fromDispnsry=Y";
	document.getElementById("patientNo").value="";
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
	
	
}
function getDiagnosisDetails(caseStatus,caseId,hospName,regisDate,patientId,caseNo)
{
	if(document.getElementById("pastHistoryCloseBtn"))
	document.getElementById("pastHistoryCloseBtn").disabled = false;
		var url;
		var pastHistory='${fromPastHistory}';
			url="patientDetails.do?actionFlag=dtrsPrintForm&patientId="+patientId+"&caseId="+caseNo+"&decFlag=N&fromDisp=Y";
		//document.getElementById("pastHistoryFrame").src=url;
			window.open(url, '_blank');
}

function getDstbtr(mftr,k)
{
	var drgType=document.getElementById("drugType"+k).value;
	var drgName=document.getElementById("drugName"+k).value;
	
	
	if(mftr==null && k==null )
	{
 		alert("Please select Manufacturer");
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
            	removeScreenCover();
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
					var drugData = resultArray.split(","); 
                	if(drugData.length>=1)
                	{  
                		
                		document.getElementById("dbtrLst"+k).options.length=0;
                		document.getElementById("dbtrLst"+k).options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugData.length;i++)
               		 	{	
                     		var arr=drugData[i].split("~");
                     		
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         		document.getElementById("dbtrLst"+k).options[i+1] =new Option(val1,val2);
                     		
                		}
                		
                		document.getElementById("mftr"+k).value=mftr;
                		removeScreenCover();
            		}
                	else
            		{
                		removeScreenCover();
            			alert("No Distributer Found");
            			return false;
            		}
            	}
            	
            	
        	}
        }
    }    	
	url = "patientDetails.do?actionFlag=getDbtrsAjax&callType=Ajax&drgType="+drgType+"&drgName="+drgName+"&mftr="+mftr;
	coverScreen();
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}	
}

function fn_saveDrgs(drgCount,arg)
{
	//alert(drgCount);
	var x = "${enablePharma}";
	if(x!=null && x=='Y'){
	document.getElementById("Submit").disabled = true;
	document.getElementById("Save").disabled = true;
	document.getElementById("Back").disabled = true;
	var saveFlag="${saveFlag}";
	var totDispatchQuant=0;
	var saveFlg='N';
	if(arg!=null && arg!='' && arg=='Save')
		{
		saveFlg='Y';
		}
	var drgs="";
	for(var k=1;k<=drgCount;k++)
		{
		var j = document.getElementById("batchCnt"+k).value;
		var totQuant = document.getElementById("preQuant"+k).value;
		var issuedQuant ="0";
		if(saveFlag == "Y"){
			issuedQuant=document.getElementById("issuedQuant"+k).value;
		}
			
		var docQuant =document.getElementById("quant"+k).value;
		var drugName=document.getElementById("drugSubName"+k).value;
		for(var i=1;i<=j;i++){
			var quant=document.getElementById("prQuant"+k+i).value;
			totQuant =parseInt(totQuant)+parseInt(quant);
			var avlQuant=document.getElementById("avlQuant"+k+i).value;
			if(parseInt(quant) > parseInt(avlQuant)){
				 alert("Dispatching Quantity cannot be greater than "+avlQuant +" for "+drugName);
				 document.getElementById("prQuant"+k+i).value="0";
				 document.getElementById("Submit").disabled = false;
				 document.getElementById("Save").disabled = false;
				 document.getElementById("Back").disabled = false;
				 return false;
			}
			drgs=drgs+document.getElementById("drugType"+k).value+"~"+document.getElementById("drugName"+k).value+"~"+
		document.getElementById("drugId"+k+i).value+"~"+document.getElementById("batchNo"+k+i).value+"~"+document.getElementById("prQuant"+k+i).value+"@";
		
		}
		var toBeIssued = parseInt(docQuant) -parseInt(issuedQuant);
		if(parseInt(issuedQuant)+parseInt(totQuant) > parseInt(docQuant)){
		  alert("Dispatching Quantity cannot be greater than "+toBeIssued +" for "+drugName);
		    document.getElementById("Submit").disabled = false;
			document.getElementById("Save").disabled = false;
			document.getElementById("Back").disabled = false;
		  return false;
		}
		totDispatchQuant=parseInt(totDispatchQuant)+parseInt(totQuant);
		}
		
	if(totDispatchQuant<=0){
		alert("Total Dispatching Quantity should be greater than Zero");
		 document.getElementById("Submit").disabled = false;
			document.getElementById("Save").disabled = false;
			document.getElementById("Back").disabled = false;
		return false;
	}
	//alert(drgs);
	document.getElementById("drugs").value=drgs;
	
		/* if(document.getElementById("drugsIssue").value=="")
		{
		 alert("Please Add Prescription image");
		 document.getElementById("Submit").disabled = false;
			document.getElementById("Save").disabled = false;
			document.getElementById("Back").disabled = false;
		 return false;
	 } */
	var url="./patientDetails.do?actionFlag=savePharmacydrugs&fromDisp=Y&saveFlg="+saveFlg+"";
			if('${exemptOTP}'== 'Y'){
				if(confirm("Do you want to continue")){
					fn_loadImage();
					document.forms[0].action=url;
					document.forms[0].method="post";
					document.forms[0].submit();
				}else{
					fn_removeLoadingImage();
					document.getElementById("Submit").disabled = false;
					document.getElementById("Save").disabled = false;
					document.getElementById("Back").disabled = false;
					return false;
				}
			}else if( $("#otp").val().length<6){
				alert("Please enter valid OTP");
				document.getElementById("Submit").disabled = false;
				document.getElementById("Save").disabled = false;
				document.getElementById("Back").disabled = false;
				return false;
			}else{
				
				fn_loadImage();
				$.post('patientDetailsNew.do?actionFlag=sendVerifyMobileOtp', {id:$("#patientNo").val(),otp:$("#otp").val(),type:"verify"}, function(result){
					var responseData=result.split("@@");
					if(responseData[0]=='true'){
						if(confirm("Do you want to continue")){
							fn_loadImage();
							document.forms[0].action=url;
							document.forms[0].method="post";
							document.forms[0].submit();
						}else{
							fn_removeLoadingImage();
							document.getElementById("Submit").disabled = false;
							document.getElementById("Save").disabled = false;
							document.getElementById("Back").disabled = false;
							return false;
						}
					}else{
						fn_removeLoadingImage();
						alert("Please enter valid OTP");
						document.getElementById("Submit").disabled = false;
						document.getElementById("Save").disabled = false;
						document.getElementById("Back").disabled = false;
						return false;
					}
				});
			
			}
	}else{
		alert("The drugs has already been issued for the following case");
		var url="./patientDetails.do?actionFlag=ViewRegisteredPatients&fromDispnsry=Y";
		fn_loadImage();
		document.forms[0].action=url;
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	
	
	
}
function dummy(arg){
    for(var k=1;k<=arg;k++)
		{
		var j = document.getElementById("batchCnt"+k).value;
		
		var docQuant =document.getElementById("quant"+k).value;
		for(var i=1;i<=j;i++){
			document.getElementById("avlQuant"+k+i).value=111;
            document.getElementById("prQuant"+k+i).value=docQuant;
        }
        }
}

function validateQuant(quant,k,ki)
{
	if( quant!="" && k!="" && parseInt(quant)!=null && k!=null && ki != null && ki !="" )
		{
		if (isNaN(quant)) 
		  {
		    alert("Dispatch Quantity should be numbers");
			document.getElementById("Back").disabled = false;
		    document.getElementById("prQuant"+ki).value = "0";
		    return false;
		  }
		var saveFlag="${saveFlag}";
		
		var docQuant=document.getElementById("quant"+k).value;
		if(parseInt(quant)>parseInt(docQuant))
			{
			alert("Dispatching Quantity cannot be greater than Actual Quantity");
			document.getElementById("prQuant"+ki).value = "0";
			return false;
			}
		var avlQuant=document.getElementById("avlQuant"+ki).value;
		if(parseInt(quant)>parseInt(avlQuant))
			{
			alert("Dispatching Quantity cannot be greater than Available Quantity");
			document.getElementById("prQuant"+ki).value = "0";
			return false;
			}
		if(saveFlag=='Y')
			{
			var issuedQuant=document.getElementById("issuedQuant"+k).value;
			var toBeIssued=parseInt(docQuant)-parseInt(issuedQuant)
			if(parseInt(quant)>toBeIssued)
			{
			alert("Dispatching Quantity cannot be greater than:"+toBeIssued);
			document.getElementById("prQuant"+ki).value = "0";
			return false;
			}
			var diff=issuedQuant-docQuant;
			if(diff==0)
			{
			alert("All the drugs have been issued");
			document.getElementById("prQuant"+ki).value = "0";
			return false;
			}
			}
		
		}
	else
		{
		alert("Quantity cannot be Empty.");
		document.getElementById("prQuant"+ki).value = "0";
		return false;
		}
	
	
	}

function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 

}
function fn_exemptOtp(){
	parent.fn_loadImage();
	$.post('patientDetailsNew.do?actionFlag=exemptOtp', {id:$("#patientNo").val(),type:"send"}, function(result){
		var responseData=result.split("@@");
		if(responseData[0]=='true'){
			alert("OTP Exemption request sent to Incharge for Approval");
			fn_backDrugs();
		}else{
			alert("Failed requesting OTP exemption.\n Please try again.");
		}
		parent.fn_removeLoadingImage();
});
}
function fn_sendMobileOtp(arg){
	parent.fn_loadImage();
	$("#sendotp").hide();
	$("#resendotp").hide();
	$.post('patientDetailsNew.do?actionFlag=sendVerifyMobileOtp', {mobileNumber:$("#nmobileNumber").val(),id:$("#patientNo").val(),type:"send"}, function(result){
		var responseData=result.split("@@");
		if(responseData[0]=='true'){
			$("#otpVerify").show();
			alert("OTP sent successfully to registered mobile number :"+responseData[1].substring(0,2)+"******"+responseData[1].substring(8,10) );
			$("#Submit").show();
			$("#Save").show();
		}else{
			alert("Failed to send OTP");
			if(arg=='send')
				$("#sendotp").show();
			else
				$("#resendotp").show();
		}
		parent.fn_removeLoadingImage();
	});
	
}
</script>


</head>
<body onload="javascript:fn_removeLoadingImage();">

<form action="/patientDetails.do" enctype="multipart/form-data" method="post">
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
<table  width="80%"  class="tbheader" style="margin:0 auto">
<tr><td align="CENTER"><b>PATIENT DETAILS</b></td></tr>
</table>
<br>
<table  width="80%" style="margin:0 auto">
<tr><td  width="15%" class="labelheading1 tbcellCss" ><b>Registration Number</b></td><td id="patNo" width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>
</tr>
<tr><td width="15%" class="labelheading1 tbcellCss"><b>Card Number</b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td></tr>



<tr><td class="labelheading1 tbcellCss" width="40%"><b>Name</b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>Gender</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
</table>

<tr class="notDisplayLab" id="empPastHistory" style="display:none;">
<td >


<div class="panel-group" id="accordion">
  <div class="panel panel-default">
    <div class="tbheader">
        <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" title="Click Here to View Past History" style="color:#fff; display:block;">
         

			<span id="empPastHstry" style="font-size: 1.25em;"><b><i  style="color:#ff0000" class="fa fa-user-plus"></i>&nbsp;&nbsp;PAST HISTORY&nbsp;&nbsp;<i style="color:#ff0000" class="fa fa-user-plus"></i></b></span>

        </a>
    </div>
    <div id="collapseOne" class="panel-collapse collapse in"> 
      <div class="panel-body">
      
      <table width="100%" style="margin:auto;">
     <logic:notEmpty name="patientForm" property="lstCaseSearch" >
     
     <tr>
											<th class="tbheader1" width="12%">Case ID</th>
<!-- 											<th class="tbheader1" width="14%">PatientName</th> -->
											<th class="tbheader1" width="25%">Hospital Name</th>
<!-- 											<th class="tbheader1" width="10%">Case Type</th> -->
											<!-- <th class="tbheader1" width="15%">Case Status</th> -->
											<th class="tbheader1" width="10%">Registered/Case Draft Date</th>		
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
											<%-- 		<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
														<bean:write  name="data" property="CASESTATUS"/>
													</td> --%>
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

<!-- <div id="pastHistoryModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
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
 </div> -->
 

<BR>
<table  width="80%"  class="tbheader" style="margin:0 auto">
<tr><td align="CENTER"><b>DISPATCH DRUGS</b></td></tr>
</table>
<table width="80%" id="drugInfo" style="margin:0 auto">
<tr>
<th width="2%" class="labelheading2 tbcellCss"><b>SNO</b></th>
<th width="20%" class="labelheading2 tbcellCss"><b>DRUG TYPE</b></th>
<th width="20%" class="labelheading2 tbcellCss"><b>DRUG NAME</b></th>
<th width="20%" class="labelheading2 tbcellCss"><b>QUANTITY</b></th>
<!-- <th width="20%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b></th>
<th width="20%" class="labelheading1 tbcellCss"><b>Distributer Name</b></th> -->



<c:if test="${saveFlag eq 'Y'}">
<th width="10%" class="labelheading2 tbcellCss"><b>ISSUED QUANTITY</b></th> 
</c:if>



<th width="10%" class="labelheading2 tbcellCss"><b>BATCH NO</b></th> 
<th width="10%" class="labelheading2 tbcellCss"><b>EXPIRY DATE</b></th> 
<th width="10%" class="labelheading2 tbcellCss"><b>AVAILABLE QUANTITY</b></th> 
<th width="10%" class="labelheading2 tbcellCss"><b>DISPATCHING QUANTITY</b></th> 
</tr>
<logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" indexId="index"  >
        <tr>  
      	<td width="1%">${index+1}</td> 
      	<input type="hidden" id="drugSubName${index+1}" value=<bean:write name="drugLst" property="DRUGSUBTYPENAME" />>       
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPENAME" /><input type="hidden" id="drugType${index+1}" value=<bean:write name="drugLst" property="DRUGTYPECODE" />></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGSUBTYPENAME" /> <input type="hidden" id="drugName${index+1}" value=<bean:write name="drugLst" property="DRUGSUBTYPECODE" />> </td>
       	<td width="5%"><bean:write name="drugLst" property="QUANTITY" /> <input type="hidden" id="quant${index+1}" value=<bean:write name="drugLst" property="QUANTITY" />> </td> 
       	
        <c:if test="${saveFlag eq 'Y'}">
		<td width="5%"><bean:write name="drugLst" property="issuedQuant" /><input type="hidden" id="issuedQuant${index+1}" value=<bean:write name="drugLst" property="issuedQuant" />> </td> 
		</c:if>
		<logic:present name="drugLst" property="batchNos">
        <bean:size  id="batchSize" name="drugLst" property="batchNos"/>
        <logic:greaterThan value="0" name="batchSize">
		<%int j = 1;%>
        <logic:iterate id="batchLt" name="drugLst" property="batchNos" indexId="ind"  >
        <c:if test="${ind+1 ne 1}">
        <tr style="text-align:right;">
        </c:if>
        <c:choose>
        <c:when test="${ind+1 ne 1}">
        <c:choose>
        <c:when test="${saveFlag eq 'Y' }">
         <td  width="5%" colspan="6" align="right">
        </c:when>
        <c:otherwise>
         <td  width="5%" colspan="5" align="right">
        </c:otherwise>
        </c:choose>
        
        </c:when>
       
        <c:otherwise>
         <td  width="5%" align="right">
        </c:otherwise>
        </c:choose>
       <input type="hidden" id="drugId${index+1}${ind+1}" value=<bean:write name="batchLt" property="DRUGCODE" />>
		<bean:write name="batchLt" property="batchNo" /><input type="hidden" id="batchNo${index+1}${ind+1}" value=<bean:write name="batchLt" property="batchNo" />> </td> 
       	<td  width="5%" align="right"><bean:write name="batchLt" property="expiryDt" /><input type="hidden" id="expDt${index+1}${ind+1}" value=<bean:write name="batchLt" property="expiryDt" />> </td> 
       	<td  width="5%" align="right"><bean:write name="batchLt" property="quantity" /><input type="hidden" id="avlQuant${index+1}${ind+1}" value=<bean:write name="batchLt" property="quantity" />> </td> 
       	<td  width="5%" align="right"><input type="text" id="prQuant${index+1}${ind+1}" value='<bean:write name="batchLt" property="avlbQuantity" />'  onchange="validateQuant(this.value,${index+1},${index+1}${ind+1});"> </td>
        <c:if test="${ind+1 ne 1}">
        </tr>
        </c:if>
         <c:set var = "batchCount" scope = "session" value = "${ind+1}"/>
       	</logic:iterate></logic:greaterThan></logic:present>
       	<input type="hidden" id="batchCnt${index+1}" value="${batchCount}"/>
       	<input type="hidden" id="preQuant${index+1}" name="preQuant${index+1}" value="0"/>
      <%--	<td width="10%">
       	<html:select name="drugLst" property="batchLst" styleId="batchLst" style="width:12em;" title="Select Batch No" onchange="getDrugBatchDtls(this.value,${index+1})" >
        <logic:present name="drugLst" property="batchNos">
         <c:if test="${fn:length(drugLst.batchNos) gt 0}">
        <html:option value="Select">Select</html:option>
        <html:optionsCollection name="drugLst" property="batchNos" label="batchNo" value="batchNo" />
        </c:if>
        </logic:present>
        </html:select>
        <input type="hidden" id="batchNo${index+1}" >
       	</td>
       	
         	<td width="10%">
       	<select name="drugLst"  id="dbtrLst${index+1}" style="width:12em;" title="Select Manufacturer" onchange="getQuant(this.value,${index+1})" >
        </select>
        <input type="hidden" id="dbtr${index+1}"/> 
       	</td>
       	<td width="5%"><input type="text" id="avlQuant${index+1}" > 
       	<input type="hidden" id="drgId${index+1}" > 
       	</td> --%>
       	</tr>
         <c:set var = "drgCount" scope = "session" value = "${index+1}"/>
        </logic:iterate></logic:greaterThan></logic:present> 

</table>
<br/><br/>
<table align="center" style="margin:0 auto">
<c:if test="${exemptOTP eq 'Y'}">
<tr>
<td colspan="3">
<font color="orange" >Exempted for OTP validation.</font>
</td></tr>
</c:if>
<tr>
<td colspan="3">
<span  id="otpVerify" style="display:none;"> Enter OTP:<input type="text" id="otp" name="otp" maxlength="6" >
<a style="text-align:center" id="otpexempt"  href="javascript:fn_exemptOtp()">OTP Exempt&nbsp;</a>
</span>
</td>
</tr>
<tr>
<td>
<button class="btn btn-primary has-spinner" style="text-align:center" type="button" id="sendotp"  onclick="javascript:fn_sendMobileOtp('send')">send OTP&nbsp;</button>
<button class="btn btn-primary has-spinner" style="text-align:center;display:none;" type="button" id="Submit"  onclick="javascript:fn_saveDrgs(<c:out value = "${drgCount}"/>,'Submit')">Issue Drugs&nbsp;</button>
</td>
<td  >
<button class="btn btn-primary has-spinner" style="text-align:center;display:none" type="button" id="Save" onclick="javascript:fn_saveDrgs(<c:out value = "${drgCount}"/>,'Save')">Save Drugs&nbsp;</button>
</td>
<td  >
<button class="btn btn-primary has-spinner" style="text-align:center" type="button" id="Back" onclick="javascript:fn_backDrugs()">Back&nbsp;</button>
</td>
<!-- <td  >
<button class="btn btn-primary has-spinner" style="text-align:center;" type="button" >Add Prescription</button>
</td>
<td>
<input type="file" name='drugsIssue' title="Click browse to select file" id='drugsIssue'/>
</td> -->
</tr>


</table>
<table align="center" style="margin:0 auto">
<tr height="30">
	<td width="27%" class="labelheading1 tbcellCss"><b> Add Prescription  <!-- <font color="red">*</font> --></b>   
	</td>
	<td  width="38%" nowrap="nowrap"  class="tbcellBorder">
	
  &nbsp;<input type="file" name='drugsIssue' title="Click browse to select file" id='drugsIssue'/></input>
                       </td></tr>
						<tr><td width="27%" class="labelheading1 tbcellCss"></td>
						<td width="38%" nowrap="nowrap"  class="tbcellBorder"><input type="file" name='drugsIssue2' title="Click browse to select file" id='drugsIssue2'/></input></td>
                        </tr>
                    <tr><td width="27%" class="labelheading1 tbcellCss"></td>
						<td width="38%" nowrap="nowrap"  class="tbcellBorder"><input type="file" name='drugsIssue3' title="Click browse to select file" id='drugsIssue3'/></input></td>
                        </tr>
                     

</table>
<script>
$(document).ready(function(){
	$('body').find('select').select2();
	if('${exemptOTP}' =='E'){
		parent.fn_loadImage();
		alert("Exemption request raised and waiting for Incharge Approval.");
		fn_backDrugs();
	}else if('${exemptOTP}' == 'Y'){
		$("#sendotp").hide();
		$("#Save").show();
		$("#Submit").show();
	}else if('${enableOTP}'=='Y'){
		$("#sendotp").hide();
		$("#otpVerify").show();
		$("#Save").show();
		$("#Submit").show();
	}
});
	
</script>
<html:hidden name="patientForm" property="drugs" styleId="drugs"/>
<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="patientNo" styleId="patientNo"/>
</form>
</body>
</html>