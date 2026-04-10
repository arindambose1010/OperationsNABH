<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.ArrayList,
                              java.util.Iterator,
                              java.util.List,com.ahct.patient.vo.PatientVO" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
     
     <%@ include file="/common/include.jsp"%>

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<style> #ui-id-4{ width:15%; }#ui-id-6,#ui-id-3,#ui-id-1,#ui-id-2,#ui-id-5 { width:35%; }
.custom-combobox-input {    margin: 0;    padding: 0.3em; background:#fff;border:1px solid #e6e6e6; }
body{font-size:1.2em !important;}
</style>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<style>
input:disabled, select:disabled{
background-color:#ddd;
}
</style>
<script type="text/javascript">


$("document").ready(function(){

	if(document.getElementById("state").value!=null && document.getElementById("state").value!="-1"){
		if(document.getElementById("state").value=="S35"){
			document.getElementById("stateType").disabled=false;
			if(document.getElementById("stateType").value!=null && document.getElementById("stateType").value!="-1")
			document.getElementById("district").disabled=false;
			else
				document.getElementById("district").disabled=true;
		}
		else{
			document.getElementById("stateType").disabled=true;
			document.getElementById("stateType").value="-1";
			document.getElementById("district").disabled=false;
		}
	}
	else{
		document.getElementById("stateType").disabled=true;
		document.getElementById("district").disabled=true;
	}

 

});

function parseDate(input) {
  var parts = input.split('-');
  return new Date(parts[2], parts[1]-1, parts[0]); 
}
var fromDisp = '${fromDisp}';
var aisMail = '${aisMail}';
var aisMailSend = '${aisMailSend}';

if(aisMail!=null && aisMail=='Y')
{
	if(aisMail=='Y')
	{
		alert("Mail Sent Successfully");
	}
}
if(aisMailSend!=null && aisMailSend=='Y')
{
	alert("Email Id is not Registered for this Particular Patient ID.");
}



//var fileCnt = 0;
function fnSearch()
{
	if( document.forms[0].patientName.value==""  &&
            document.forms[0].cardNo.value==""  &&
            document.forms[0].state.value=="-1" &&
            document.forms[0].stateType.value=="-1" &&
            document.forms[0].district.value=="-1" && 
	         document.forms[0].fromDate.value=="" && 
            document.forms[0].toDate.value=="" &&
		     document.forms[0].patientNo.value=="")
            {  
				jqueryAlertMsg('Search Criteria Validation','Please enter any search criteria! ');
                return false;
            }
	var patname=document.forms[0].patientName.value;
	if(patname != '' && document.forms[0].district.value=="-1")
	{
		jqueryAlertMsg('Search Criteria Validation',"Please select District also to continue with Patient Name search");
		return false;
	}
	if(document.forms[0].stateType.value!="-1" && document.forms[0].district.value=="-1")
		{
		jqueryAlertMsg('Search Criteria Validation',"Please select District also to continue with district type search");
		return false;
		}
	var regFrom=document.forms[0].fromDate.value;
	var regTo=document.forms[0].toDate.value;
	if((regFrom.length > 0 && regTo.length == 0) || (regFrom.length == 0 && regTo.length > 0) )
		{
		jqueryAlertMsg('Search Criteria Validation','Please select none or both Registration From and Registration To dates');
		return false;
		}
	else if(regFrom.length > 0 && regTo.length > 0)
		{     
			if(fnCompareDates(regFrom, regTo) && fnMonthDiff(regFrom, regTo))
				{
					search="true"; 
				}
			else
				{
					return false;      
				}
		} 
	if(document.forms[0].stateType.value!="-1" && document.forms[0].stateType.value=="O"){
	if(parseDate(regFrom)>=parseDate("${bifurcationDate}") || parseDate(regTo)>=parseDate("${bifurcationDate}")){
		alert("Please select from date and to date before "+'${bifurcationDate}'+" for district type Old");
	return false;
	}
	}
	else if(document.forms[0].stateType.value!="-1" && document.forms[0].stateType.value=="N"){
		if(parseDate(regFrom)<parseDate("${bifurcationDate}") || parseDate(regTo)<parseDate("${bifurcationDate}")){
			alert("Please select from date and to date on or after "+"${bifurcationDate}"+" for district type new");
		return false;
		}
	}
	document.getElementById("district").disabled=false;
	document.getElementById("currPatId").value=document.forms[0].patientNo.value;
	document.getElementById("currPatName").value=document.forms[0].patientName.value;
	document.getElementById("currHealthCardNo").value=document.forms[0].cardNo.value;
	document.getElementById("currStateId").value=document.forms[0].state.value;
	document.getElementById("currDistrictId").value=document.forms[0].district.value;
	document.getElementById("currFromDate").value=document.forms[0].fromDate.value;
	document.getElementById("currToDate").value=document.forms[0].toDate.value;
	
	fn_pagination(0,'button');
	
}
function checkBrowser(input)
{
     if(navigator.appName == "Netscape")
    {
		var sizeCheck=checkFileSizeFF(input);
		var fileCheck=checkFileNameMatch(input);
		if(sizeCheck==false || fileCheck==false)
		input.value='';
    }
     if(navigator.appName == "Microsoft Internet Explorer")
    {  
		var fieldName=input.name;
		var fieldId=input.id;  	
		var sizeCheck=checkFileSizeIE(input);
		var fileCheck=checkFileNameMatch(input);
		if(sizeCheck==false || fileCheck==false)
		{
			var oRow = input.parentNode.parentNode; 
			var filecell;
			if(fieldName.charAt(0)=='g' || fieldName.charAt(0)=='u')
			{
				filecell=oRow.cells[2];
			}
			else if(fieldName.charAt(0)=='a')
			{ 
				filecell=oRow.cells[6];
			}
			filecell.innerHTML='<input type="file"  id='+fieldId+' name='+fieldName+' onchange="checkBrowser(this)"/>';
		}
    }
}	
function openCase(patientId,caseId)
{
	var fromDisp = '${fromDisp}';
	if(caseId=='NA')
		document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+patientId+"&fromDisp="+fromDisp;
	else
	   document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+patientId+"&caseId="+caseId+"&fromDisp="+fromDisp;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
}
function fn_addAttach()
{
	
	var fromDisp = '${fromDisp}';
	if(caseId=='NA')
		document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+patientId+"&fromDisp="+fromDisp;
	else
	   document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+patientId+"&caseId="+caseId+"&fromDisp="+fromDisp;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
}
function sendPatient()
{
	var fileCnt=document.getElementById("fileCnt").value;
	if(fileCnt>0)
	{
		var patientId=document.getElementById("currPatId").value;
		var url="./patientDetailsNew.do?actionFlag=sendMail&advSearch=false&aisView=Y&patientId="+patientId;
		document.forms[0].action=url;
	 	//document.forms[0].method="post";
	 	document.forms[0].submit();
	}
	else if(fileCnt<1)
	{
		alert("Please attach atleast one Attachment to send Mail.");
	}
}
/* function printPatient(patientId)
{
	var fromDisp = '${fromDisp}';
	window.open('./patientDetails.do?actionFlag=viewPatientDetails&fromDisp='+fromDisp+'&patientId='+patientId+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=800,toolbar=no,resize=no,scrollbars=yes');
} */
/* function cancelPatient(patientId)
{
	 bootbox.confirm("Are you sure you want to cancel registration ? ", function(result) {
		 	if(result)
			{
					 document.forms[0].action="./patientDetails.do?actionFlag=cancelPatient&patientId="+patientId;
				   	 document.forms[0].method="post";
				   	 document.forms[0].submit();
			 }
		});
} */

function cancelPatient()
{
	var aisRemarks=document.getElementById("aisRemarks").value;
	var patientId=document.getElementById("buttonVal").value;
	 if(aisRemarks!=null && aisRemarks!="")
	{
		 $('#myModal1').modal('hide');	
	 bootbox.confirm("Are you sure you want to Cancel Registration ? ", function(result) {
		 	if(result)
			{
		 			 
		 		
					 document.forms[0].action="./patientDetailsNew.do?actionFlag=cancelPatientDirReg&advSearch=false&aisView=Y&patientId="+patientId+"&aisRemarks="+aisRemarks;
				   	 document.forms[0].method="post";
				   	 document.forms[0].submit();
			}
		});
	}
	else
	{
		alert("Please Enter Remarks");
		$('#myModal1').modal('show');	
	}
	 

}
function cancelPatient1(patientId)
{
	document.getElementById("aisRemarks").value="";
	document.getElementById("buttonVal").value=patientId;
}




function fn_viewImg(url)
{
	window.open('./patientDetailsNew.do?actionFlag=viewAttachment&&filePath='+url);
}
		

function regPatient(patientId)
{
	document.getElementById("fileCnt").value=0;
	document.getElementById("currPatId").value=patientId;
	var xmlhttp;
    var url;

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
            		resultArray = resultArray.replace("]",""); 
            		resultArray = resultArray.replace("*","");  
            		var packagesList = resultArray.split("~"); 
            		if(packagesList.length>0)
            		{  
            			
            			var table=document.getElementById("tbodyC1");
            			table.innerHTML="";
            			for(var i = 0; i<packagesList.length-1;i++)
            			{	
                			var arr=packagesList[i].split("^");

            				var newRow=table.insertRow(-1);
            				var newcell=newRow.insertCell(0);
            				newcell.innerHTML=i+1;
            				var newcell=newRow.insertCell(1);
            				newcell.innerHTML='<td>'+arr[0]+'</td>';
            				arr[1]=arr[1].trim();
            				
            				if(arr[2]!=null && arr[2]==='EMP')
            					{
            					 var newcell=newRow.insertCell(2);
                				 // newcell.innerHTML='<iframe id='+arr[1]+' name='+arr[1]+' src="./patientDetailsNew.do?actionFlag=uploadPage&attachType="'+arr[1]+'"&perseq="'+patientId+'></iframe>';
                				  newcell.innerHTML='<td data-title="upload"><iframe id="iframe1" name="iframe1" height="70" style="width:70%;border:1;"   src="./patientDetailsNew.do?actionFlag=uploadPage&path='+arr[1]+'&patientId='+patientId+'" frameborder=no scrolling=no> </iframe> </td></td><td data-title="Action"><i class="fa fa-times"></i></button></td>';
            					}
            				else if(arr[2]!=null && arr[2]!='EMP')
            					{
            					 //alert(arr[2]);
            					 var str = arr[2];
            					 var pieces = str.split(/[\s_]+/);
            					 var fileName = pieces[pieces.length-1];
            					 //alert(pieces[pieces.length-1]);
            					 var newcell=newRow.insertCell(2);
            					 newcell.innerHTML='<td><a href="javascript:fn_viewImg(\''+arr[2]+'\')">'+fileName+'</a></td>';
            					 document.getElementById("fileCnt").value=parseInt(document.getElementById("fileCnt").value)+1;
            					}
            					
            				
     
            			}
            			$("#myModal").modal('show');
            		}
            	}
            }
        }
    }
    url = "./patientDetails.do?actionFlag=regPatient&patientId="+patientId;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
				
			 
		
}
function fnAdd(id,ab)
{
	var xmlhttp;
    var url;

    
   
	var path=document.getElementById(id).value;
	alert(path);
	
	var code=id.name;
	if(path==null || path=='')
		{
		alert("Please upload the attachment");
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
	            		resultArray = resultArray.replace("]",""); 
	            		resultArray = resultArray.replace("*","");  
	            		var packagesList = resultArray.split("~"); 
	            		
	            	}
	            }
	        }
	    }
	    url="./patientDetailsNew.do?actionFlag=uploadAtach&path="+path+"&code="+code+"&patientId="+ab;
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		
	
		}
}
function fnCompareDates(FromDate,ToDate)
{
    var FromDateVal;
    var ToDateVal;
    var k = FromDate.indexOf("-");
    var t = FromDate.indexOf("-",3);  
    FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,FromDate.length);
  	k = ToDate.indexOf("-");
    t = ToDate.indexOf("-",3);
    ToDateVal = ToDate.substr(k+1,t-k-1)+"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,ToDate.length);
	 if (Date.parse(FromDateVal) > Date.parse(ToDateVal))
         {
		 jqueryAlertMsg('Search Criteria Validation',"From Date should be less than To Date");
     		return false; 
          } 
    else
      return true;       
}
function fnMonthDiff(FromDate,ToDate)
{
    var FromDateVal;
	var ToDateVal;            
	var k = FromDate.indexOf("-");
	var t = FromDate.indexOf("-",3);   
	FromDateVal = FromDate.substr(k+1,t-k-1)+"/"+FromDate.substr(0,k)+"/"+FromDate.substr(t+1,t-1); 

	var fromYear = parseInt(FromDate.substr(t+1,t-1));			
	var fromMon = Number(FromDate.substr(k+1,t-k-1));
	var fromDt=Number(FromDate.substr(0,k));	
	k = ToDate.indexOf("-");
	t = ToDate.indexOf("-",3);
	ToDateVal = ToDate.substr(k+1,t-k-1) +"/"+ToDate.substr(0,k)+"/"+ToDate.substr(t+1,t-1);

	var toYear = Number(ToDate.substr(t+1,t-1));
	var toMon = Number(ToDate.substr(k+1,t-k-1));
	var toDt=Number(ToDate.substr(0,k));
	if(toYear == fromYear)
	{				
		if((toDt - fromDt >=0 && toMon - fromMon <= 2) || (toDt - fromDt <0 && toMon - fromMon <= 3))
		{
			return true;
		}
		else 
		{   
			jqueryAlertMsg('Search Criteria Validation','Can not select more than 3 months difference');
			return false;
		}
	}
	else if(toYear > fromYear)
	{
		if((toDt - fromDt >=0 &&  fromMon - toMon >=10) || (toDt - fromDt <0 && fromMon - toMon >= 9))
		{
			return true;
		}
		else 
		{  
			jqueryAlertMsg('Search Criteria Validation','Can not select more than 3 months difference');
			return false;
		}
	}
	else
	{
		jqueryAlertMsg('Search Criteria Validation','Please select valid From and To Dates');
		return false;
	}
}
function fn_pagination(pageNo,actionType)
{
	
	var fromDisp = '${fromDisp}';
	
	document.forms[0].advSearch.value="true";
	var stateType=document.getElementById("stateType").value;
	var patientId=document.getElementById("patientNo").value;

	
	var url="./patientDetailsNew.do?actionFlag=ViewRegisteredPatientsAis&aisView=Y&actionType="+actionType+"&pageNo="+pageNo+"&fromDispnsry="+fromDisp+"&stateType="+stateType;
	document.forms[0].action=url;
	 //document.forms[0].method="post";
	 document.forms[0].submit(); 
}
function showPagination(num)
{
	document.forms[0].showPage.value=num; 
	fn_pagination(1,num);
	}
function showinSetsOf(num)
{
	document.forms[0].rowsPerPage.value=num; 
	document.forms[0].showPage.value='1'; 
	fn_pagination(1,num);	
}
function validateDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		jqueryErrorMsg('Date Validation',"Please Select "+arg1);
	}
	else
	{
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoB>DoC)
		{
		input.value="";
		jqueryErrorMsg('Date Validation',arg1+" should not be greater than Today's Date");
		}
	}
}
function resetSearch()
{
	document.getElementById("patientNo").value="";
	document.getElementById("patientName").value="";
	document.getElementById("cardNo").value="";
	document.getElementById("stateType").value="-1";
	document.getElementById("district").value="-1";
	document.getElementById("state").value="-1";
	document.getElementById("fromDate").value="";
	document.getElementById("toDate").value="";
}
function validateHealthCard(arg1,input)
{
	var a=input.value;
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Health Card Validation',"Please Enter "+arg1);
	//focusBox(input);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Health Card Validation',arg1+ " should not start with space");
		//focusBox(input);
		return false;
		}
	var regAlphaNum=/^[0-9a-zA-Z\/\ ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Health Card Validation',"Only alphanumeric are allowed for "+arg1);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
}
function  refreshParentPage()
{
window.close();
}
function viewPreviousPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo;
	var minPageNo=pageNo-10;
	var action='link';
	var i=0;
	if(minPageNo>0)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+')">Previous</a>&nbsp;&nbsp;';
	}
	else
	{
		minPageNo=minPageNo+1;
	}
	for(i=minPageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+',\''+action+'\')"><b>'+i+'</b></a>&nbsp;&nbsp;';
		}
		
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')">Next</a>';
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function viewNextPages(pageNo,noOfPages,selectedPage)
{
	var pageDisplays='';
	var pageNoLim=pageNo+10;
	var action='link';
	var i=0;
	if(pageNoLim>noOfPages)
	{
		pageNoLim=noOfPages+1;
	}
	pageDisplays=pageDisplays+'<a href="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+')">Previous</a>&nbsp;&nbsp;';
	for(i=pageNo;i<pageNoLim;i++)
	{
		if(selectedPage==i)
		{
			pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
		}
		else
		{
			pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+',\''+action+'\')"><b>'+i+'</b></a>&nbsp;&nbsp;';
		}
		
	}
	if(i<noOfPages)
	{
		pageDisplays=pageDisplays+'<a href="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+')">Next</a>';
	}
	document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
}
function enableStateType(){
	document.getElementById("stateType").value="-1";
	document.getElementById("district").value="-1";
	
	if(document.getElementById("state").value!=null && document.getElementById("state").value!="-1"){
		if(document.getElementById("state").value=="S35"){
			document.getElementById("stateType").disabled=false;
			document.getElementById("district").disabled=true;
		}
		else{
			document.getElementById("stateType").disabled=true;
			document.getElementById("stateType").value="-1";
			stateSelected();
		}
	}
}
function stateSelected()
{
	var state=null;
	var lStrHdrId='LH6';
	state=document.getElementById("state").value;
	var stateType = document.getElementById("stateType").value;
	var xmlhttp;
    var url;

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
            		resultArray = resultArray.replace("]*","");            
            		var districtList = resultArray.split(","); 
            		if(districtList.length>0)
            		{  
            			document.getElementById("district").disabled=false;
            			document.forms[0].district.options.length=0;
            			document.forms[0].district.options[0]=new Option("---select---","-1");
            			for(var i = 0; i<districtList.length;i++)
            			{	
            				var arr=districtList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					document.forms[0].district.options[i+1] =new Option(val1,val2);
            				}
            			}
            		}
            	}
            }
        }
    }
    url = "./patientDetails.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state+"&stateType="+stateType;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
</script>
</head>
<body>
<form action="/patientDetails.do" enctype="multipart/form-data" method="post" >
<logic:notEmpty name="patientForm" property="msg">
    <script language="javascript" type="text/javascript">
    bootbox.alert('<bean:write name="patientForm" property="msg"/>');
    </script>
    </logic:notEmpty>
<c:if test="${saveMsg ne null && saveMsg ne '' }">
<script>
alert('${saveMsg}');
</script>
</c:if>
<div>
<table class="tbheader">
<tr><th style="padding-left:40%"><b><fmt:message key="EHF.Title.RegisteredPatientsView"/></b></th></tr>
</table>
<table width="100%">
<%-- <tr  height="30px"><th><fmt:message key="EHF.Title.AdvancedSearch"/></th></tr> --%>
<tr><td>
<table width="100%" class="tb">
<tr>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientNo"/></b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="patientNo" maxlength="12" styleId="patientNo" onchange="validateNumber('Patient No',this)" title="Enter Patient No" style="WIDTH: 10em;;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b><fmt:message key="EHF.PatientName"/></b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="patientName" maxlength="100" styleId="patientName" onchange="checkAlphaSpace('patientName','Patient Name')" title="Enter Patient Name" style="WIDTH: 10em;"/></td>
<td width="16%" class="labelheading1 tbcellCss"><b>Employee ID/Card No</b></td>
<td width="16%" class="tbcellBorder"><html:text name="patientForm"  property="cardNo" styleId="cardNo" maxlength="21" title="Enter Health Card No" onchange="validateHealthCard('Health Card No',this)" style="WIDTH: 10em;"/></td>
</tr>
<tr>
<td class="labelheading1 tbcellCss"><b>State</b></td>
<td colspan="" class="tbcellBorder"><html:select name="patientForm" property="state" styleId="state" title="Select State" onmouseover="addTooltip('state')" onchange="enableStateType()" style="WIDTH: 15em;">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="stateList" labelProperty="VALUE"/>
    </html:select></td>
    
<td class="labelheading1 tbcellCss"><b>District Type</b></td>
<td colspan="" class="tbcellBorder"><html:select name="patientForm" property="stateType" styleId="stateType" title="Select District Type" onmouseover="addTooltip('stateType')" onchange="stateSelected()" style="WIDTH: 15em;">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:option value="O">Old Districts </html:option>
			<html:option value="N">New Districts </html:option>
    </html:select></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.District"/></b></td>
<td colspan="" class="tbcellBorder"><html:select name="patientForm" property="district" styleId="district" title="Select District" onmouseover="addTooltip('district')" style="WIDTH: 15em;">
            <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="districtList" labelProperty="VALUE"/>
</html:select></td>

</tr>
<tr>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Registration"/></b>: <b><fmt:message key="EHF.FromDate"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm" property="fromDate" styleId="fromDate" title="Enter From Date" onchange="validateDate('From Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 10em;"/></td>
<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ToDate"/></b></td>
<td class="tbcellBorder"><html:text name="patientForm" property="toDate" styleId="toDate" title="Enter To Date" onchange="validateDate('To Date',this)"  onkeydown="validateBackSpace(event)" readonly="true" style="WIDTH: 10em;"/></td>
</tr>
<tr>

<td colspan="3" align="right">  <button class="but"  type="button" onclick="javascript:fnSearch()">Search</button></td>
<td colspan="3"> <button class="but"  type="button" onclick="resetSearch()">Reset</button></td>
</tr>
</table>
</td></tr>
</table>
<br>
<html:hidden name="patientForm" property="startIndex" styleId="startIndex"/>
<table  width="100%" class="tb">
<logic:notEmpty name="registeredPatientsList">

<div class="leftone">
<ul class="pagination">
<li>&nbsp;&nbsp;
	<b>Displaying <bean:write name="recordsList"/></b>&nbsp;&nbsp;&nbsp;&nbsp;
	<b>Total no of records: <bean:write name="totalRecords" scope="request"/></b>&nbsp;&nbsp;&nbsp;</li></ul> 
</div>
<div class="rightone" >
<ul class="pagination">
<li><b>Show in sets of</b>&nbsp;&nbsp;</li>
<c:set var="ListNoSet" value="10,20,50,100,1000"/>  
<c:forEach var="set" items="${ListNoSet}"  >
<c:if test="${lStrRowsperpage eq set }" >
<li class="active"><span><c:out value="${set}" /></span></li> 
</c:if>
<c:if test="${lStrRowsperpage ne set }" >
<li><a href="javascript:showinSetsOf('<c:out value="${set}" />')"> <c:out value="${set}" /></a></li>
</c:if>
</c:forEach>
</ul>
</div>
<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 ">
<div  id="pageNoDisplay">
<ul class="pagination">
<%
int noOfPages = ((Integer) request.getAttribute("noOfPages")).intValue();
int selectedPage = ((Integer) request.getAttribute("selectedPage")).intValue();
int a=selectedPage/10;
int pageNo=0;
int minVal=(a*10);
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages+1;
}
if(minVal>=10)
	{
		%>
		<a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>)">Previous</a>&nbsp;
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
			<b><%=pageNo%></b>
			<%
		}
		else
		{
			%>
			<a href="javascript:fn_pagination(<%=pageNo%>,'link')"><b><%=pageNo%></b></a>&nbsp;
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		<a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>)">Next</a>
		<%
	}
%>
</ul>
</div> </div>


	<tr>
<td>
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal">&times;</button> 
          <h4 class="modal-title" align="center">Attachments</h4>
        </div>
           <div class="modal-body" style="overflow:scroll !important; height:720px">
	
           
          <table id="hospDtlsTbl" class="table table-bordered table-hover" style="font-size: 12px;border: 1px solid #ddd;" >
					 <thead id="thead_dashboard">
				
						<tr>
							<th>S No</th>
							<th>Package Name</th>
					        <th>Attachment</th>
					        
						</tr>
		
					</thead>
					<tbody id="tbodyC1">
	           </tbody>	
	         
	      	
					</table>
					       <div align="center">
					    <%--    <logic:iterate name="registeredPatientsList" id="patientVO"> --%>
 <button class="btn btn-primary"  type="button" title="Click to Send mail" onclick="javascript:sendPatient()">Send Email</button>
<%--  </logic:iterate> --%>
</div>
				
        </div>
  
            
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
    </div>
  </div>
</div>
</td>
</tr>

<tr>
<td>
  <div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
           <button type="button" class="close" data-dismiss="modal">&times;</button> 
          <h4 class="modal-title" align="center">Remarks<font color="red"></font></h4>
        </div>
           <div class="modal-body" style="overflow:scroll !important; height:150px">
	
           
          <table id="hospDtlsTbl" class="table table-bordered table-hover" style="font-size: 12px;border: 1px solid #ddd;" >
					 <thead id="thead_dashboard">
				
						<tr>
						<td  class="labelheading1 tbcellCss"><span  style="padding: 24px 10px; margin: 0 auto;display: table;" ><b>Remarks <font color="red">*</font>&nbsp;&nbsp;&nbsp;&nbsp;</b></span></td>
						<td colspan="6"><html:textarea   name="patientForm" property="aisRemarks" styleClass="form-control" styleId="aisRemarks" style="float:left;width:100%;overflow-y:auto;resize:none;height:6em" cols="30" onkeypress="return validateSplKeyCodes(event);" onchange="check_maxLength(this.id,'3000')" onkeydown="return maxLengthPress(this,4000,event)"  title="Please Enter Remarks"/></td>
						</tr>
		
					</thead>
					<tbody id="tbodyC1">
	           </tbody>	
	      	
					</table>
					<div id="buttonVal">
					</div>
				
        </div>
  		
            
        <div class="modal-footer">
          <button type="button" class="btn btn-primary"  onclick="javascript:cancelPatient()">Submit</button>
        </div>
    </div>
  </div>
</div>
</td>
</tr>


<div class="modal fade" id="viewImage" style="z-index:2000 !important;">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h3 class="modal-title" id="titlea"></h3>
										</div>
										<div class="modal-body"
											style="overflow-y: scroll;  align: center">

											<iframe id="imageViewDiv" frameborder="0" width="100%"
												height="420px"></iframe>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">
												<b>Close</b>
											</button>
										</div>
									</div>
								</div>
							</div>
<!-- PAGINATION  -->

<%-- <div  class="leftone">
<ul class="pagination">

<li class="lispacing">Showing Results</li>
<li class="lispacing"><bean:write name="patientForm" property="startIndex" />  - <bean:write name="patientForm" property="endIndex" /> 
of <bean:write name="patientForm" property="totalRows" /> </li>
</ul></div>

<div id="pageNoDisplay" class="centerone">

<ul class="pagination"> 


<%
int noOfPages = ((Integer) request.getAttribute("noOfPages")).intValue();
int selectedPage = ((Integer) request.getAttribute("selectedPage")).intValue();
int a=selectedPage/10;
int pageNo=0;
int minVal=(a*10);
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages+1;
}
if(minVal>=10)
	{
		%>
		
		<li><a href="#"><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>)" style="cursor: pointer;"></span></a></li>
		
		<%
	}
	else
	{
		minVal=minVal+1;
	}
for(int i=minVal;i<maxVal;i++)
{
	pageNo=i;
		if(selectedPage==pageNo)
		{
			%>
		<li class="active"><span><%=pageNo%></span></li> 
			<%
		}
		else
		{
			%>
			<li><a href="javascript:showPagination(<%=pageNo%>)"><%=pageNo%></a></li>
			<%
		}
}
if(pageNo<noOfPages)
	{
		%>
		
		<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>)" style="cursor: pointer;"></span></li>
		
		<%
	}
%>
</ul></div> --%>


<table  width="100%" class="tb">
<tr >
<th class="tbheader1" style="width:7%"><fmt:message key="EHF.PatientNo"/></th>
<th class="tbheader1" style="width:20%"><fmt:message key="EHF.PatientName"/></th>
<th class="tbheader1" style="width:11%">Employee/AIS Card No</th>
<th class="tbheader1" style="width:6%"><fmt:message key="EHF.Gender"/></th>
<th class="tbheader1" style="width:12%"><fmt:message key="EHF.RegistrationDate"/></th>
<!-- <th class="tbheader1" style="width:10%">View</th> -->
<th class="tbheader1" style="width:10%">Add Attachments</th>
<th class="tbheader1" style="width:10%">Cancel Registration</th>
</tr>
	<logic:iterate name="registeredPatientsList" id="patientVO">
<tr>
<td class="tbcellBorder" align="center">&nbsp;
<b><bean:write name="patientVO" property="patientId"/></b>
<logic:notEmpty name="patientVO" property="telephonicId">
<img src="images/telephone.png" height="23" width="23" alt="This is a Telephonic Registered case" title="This is a Telephonic Registered case"/>
</logic:notEmpty>
</td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="firstName"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="rationCard"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="gender"/></td>
<td class="tbcellBorder" align="center"><bean:write name="patientVO" property="regHospDt"/></td>
<td class="tbcellBorder" align="center">
 <button class="btn btn-primary"  type="button" title="Click to Add Attachments" onclick="javascript:regPatient('<bean:write name="patientVO" property="patientId"/>')" data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false">Add Attachments</button>
 </td>
<td align="center" class="tbcellBorder">
<button class="btn btn-danger"  type="button" title="Click to Cancel Registration"  data-toggle="modal" data-target="#myModal1" data-backdrop="static"  onclick="javascript:cancelPatient1('<bean:write name="patientVO" property="patientId" />')">Cancel</button>
</td>
</tr>
</logic:iterate>
</table>
</logic:notEmpty>
<logic:empty name="registeredPatientsList">
<table width="50%"  cellpadding="1" cellspacing="1" align="center" style="padding-top:0px;margin:1px auto;" class="tb">
<tr >
<td align="center" height="50">
<b><bean:write name="patientForm" property="errMsg"/></b>
</td>
</tr>
</table>
</logic:empty>

</table>

<input type="hidden" name="advSearch" value="false"/>  
 <html:hidden name="patientForm" property="currPatId" styleId="currPatId"/> 
<html:hidden name="patientForm" property="currPatName" styleId="currPatName"/>  
<html:hidden name="patientForm" property="currHealthCardNo" styleId="currHealthCardNo"/> 
<html:hidden name="patientForm" property="currStateId" styleId="currStateId"/> 
<html:hidden name="patientForm" property="currDistrictId" styleId="currDistrictId"/>  
<html:hidden name="patientForm" property="currFromDate" styleId="currFromDate"/>  
<html:hidden name="patientForm" property="currToDate"  styleId="currToDate"/>
<html:hidden name="patientForm" property="rowsPerPage"  styleId="rowsPerPage"/>  
<html:hidden name="patientForm" property="showPage"  styleId="showPage"/>  
<html:hidden property="totalRows" name="patientForm" />
<input type ="hidden" name="hospGovu" id= "hospGovu" value="${hospGovu}"/>
<html:hidden property="fromDisp" name="patientForm" value="${fromDisp}" />
<html:hidden name="patientForm" property="noOfPages" styleId="noOfPages"/>
<input type="hidden" name="fileCnt" id= "fileCnt" value="0"/>
</form>
</body>
</html>
</fmt:bundle>