<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" import="java.util.List,com.ahct.common.vo.LabelBean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
     <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
     <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
     <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
  	 <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
	 <%@ include file="/common/include.jsp"%> 

<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />


<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">

<link href="css/select2.min.css" rel="stylesheet">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">
<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
<!--<LINK href="bootstrap/css/custombox.css" rel="stylesheet" type="text/css" media="screen">-->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  

<script src="js/jquery.msgBox.js" type="text/javascript"></script>

<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>
<!--<script src="bootstrap/js/custombox.js"></script>
<script src="bootstrap/js/legacy.js"></script>-->
<script type="text/javascript" src="js/DispDentalPatientscripts.js?v=1.1"></script>
<script src="js/select2.min.js"></script>

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

<style type="text/css">
*{margin:0;padding:0;}

 input:focus 
{
  outline:#000 dotted 1px; 
}  
.bootbox-close-button
{
display:none;
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
body {
font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
font-size: 12px;
line-height: 1.42857143;
color: #333;
background-color: #fff;
}
	.labReport {
		text-decoration: underline;
	    cursor: pointer;
	    color: #258463;
	} 
	
	.editReport{
		text-decoration: underline;
	    cursor: pointer;
	    color: #8b0000;
	}
	
	.LabReportSubmited {
	    color: #0000FF !important;
	}
	
	.labReportnotSubmit {
	    color: #258463 !important;
	} 
	
	.medcoClass {
		text-decoration: underline;
	    cursor: pointer;
	    color: #258463;
	} 
</style>
<script type="text/javascript">


var heightVar = null;
var weightVar = null;

$(document).ready(function()
		{
	var jq = jQuery.noConflict();
var schemeId='${schemeId}';
var hospType='${hospType}';
var hospId=document.getElementById("hospitalId").value;
var hospGovu = document.getElementById("hospGovu").value;
if(schemeId=='CD201')
jq('.onlyAp').css('display','');
if(schemeId=='CD202')
{
	jq('.onlyAp').css('display','none');
	if( document.forms[0].patientType[1].checked || hospType!='G')
	{
jq('.onlyAp').css('display','');
		}



}

		if(hospId)
			jq('.onlyAp').css('display','none');

		
		
			});


function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
}



function focusBox(arg)
{	
	  aField = arg; 
	 setTimeout("aField.focus()", 0); 
	  
 	   var x=getOffset( arg ).top;  
 	 /* var offset = $jq(Field).offset();
	  var top = offset.top;
	  top = top+elemJqueryScrollTop;
	  $("body").mCustomScrollbar("update");
	  $("body").mCustomScrollbar("scrollTo",top); */
	  
	
}

function browserDetect()
{
	 var objAgent = navigator.userAgent; 
	 var objbrowserName  = navigator.appName;
	 var objOffsetVersion;
	if ((objOffsetVersion=objAgent.indexOf("Chrome"))!=-1) { 
		 objbrowserName = "Chrome"; 
	}
	else if ((objOffsetVersion=objAgent.indexOf("MSIE"))!=-1) { 
		objbrowserName = "Microsoft Internet Explorer"; 
	}
	else if ((objOffsetVersion=objAgent.indexOf("Firefox"))!=-1) { 
		objbrowserName = "Firefox"; 
	}
	return objbrowserName;
}
function checkAlphaNumericCodes(event) {
	browserName=browserDetect();
	//if(navigator.appName=="Microsoft Internet Explorer" || navigator.appVersion=='5.0 (Windows NT 5.1) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/18.0.1025.168 Safari/535.19')
	if(browserName=="Microsoft Internet Explorer" || browserName=="Chrome")
	{
	var charCode=event.keyCode;
	if ((charCode<65 || charCode>90)&&(charCode<97 || charCode>122)&&(charCode<48 || charCode>57)&&(charCode!=13 && charCode!=32))
			    return false; 	
					return true;  
	}
	else if(browserName=="Firefox")
	{
		var inputValue = String.fromCharCode(event.keyCode || event.charCode)
		var regExpr = /^[0-9a-zA-Z\s]+$/;
		if(event.keyCode != 0) {
			if(event.keyCode == 38 || event.keyCode == 40 || event.keyCode == 37 || event.keyCode == 39 ||
				event.keyCode == 8 || event.keyCode == 13 || event.keyCode == 46 || event.keyCode == 36 ||
				event.keyCode == 35 || event.keyCode == 33 || event.keyCode == 34 || event.keyCode == 45 ||
				event.keyCode == 9) {
			} else {
				return false;
			}
		} else if(event.charCode != 0){
			if(!inputValue.match(regExpr)) {
				return false;
			}
		}
		return true;
	}
}
var medOrSur='';
var genInvestAttachId=0;
var updGenInvestAttachId=0;
var ipInvestAttachId=0;
var therapyAttachId=0;
var a=0;
var genInventCount=0;
var updateGenInvestCount=0;
var updateGenInvestLst=new Array();
var genInventList=new Array();
var consultDataList=new Array();
var genOldList=new Array();
var catCount=0;
var spec=new Array();
var specOld=new Array();
var drugCount=0;
var drugs=new Array();
var existDrugsArr=new Array();
var symptomCount=0;
var symptoms=new Array();
var genInvestRemove='';
var specRemove='';
var otherDocDetails=new Array();

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
function checkFileSizeFF(input)
{
	var filesize=input.files[0].size;
	if((filesize/(1024))>200)
	{
		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
		return false;
	}
}
function checkFileSizeIE(input)
{
	try
	{
 	var myFSO = new ActiveXObject("Scripting.FileSystemObject");
 	var filepath = input.value;
 	var thefile = myFSO.getFile(filepath);
 	var filesize = thefile.size/(1024);
 	if(filesize>200)
	{
 		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
		return false;
	}
	}
	catch(e)
	{
		jqueryInfoMsg('ActiveX Control Enable',"Please Enable ActiveX control.");
		jqueryInfoMsg('Steps To Enable ActiveX Control',"Go To-->Tools-->Internet Options-->Security-->Trusted Sites-->Click on Sites Button-->Add site url to list-->close-->Click on Custom level Button-->Initialize and script ActiveX controls not marked as safe for scripting---Enable");
		return false;
	}
}
function checkFileNameMatch(input)
{
	var curFile=input.value;
	//var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
	
	var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')));
	var fullFileName=curFile.substring(curFile.lastIndexOf('\\')+1);
	var fileName1=curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
	if(rtVal ==0)   
		{
		jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
		return false;
		}
	if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
		{
		jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
		return false;
		}
	
	if( fileName1.match(/[\-\_]{2}/i))
	{
		jqueryErrorMsg('File Name Validation',"File name should not should not contain consecutive special characters");
		return false;
	}
	var extn=curFile.substring(curFile.lastIndexOf('.')+1).toLowerCase();
	if(extn=='jpg' || extn=='jpeg' || extn=='png' || extn=='bmp')  
		{
		var status=true;
		}
	else
		{
		jqueryErrorMsg('File Type Validation',"Can upload jpg,jpeg,png,bmp extension files only");
		return false;
		}
	var matchCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
       	   var fileName = val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
       	   var curFileName = curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
		   if(fileName==curFileName)
		   matchCount++;
		   if(matchCount>1)
			{
				jqueryErrorMsg('File Name Validation',"File with this filename already exists.Cannot upload");
				return false;
			}
       }
    }
	
}
function maxLengthPress(field,maxChars,e)
{
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(field.value.length >= maxChars) 
	{
		if(code==8 || code==9 || code==46 || code==37 || code==38 || code==39 || code==40)
			//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
			{
				e.returnValue=true;
				return true;
			}
		else
			{
				e.returnValue=false;
	        	return false;
		 	}
         }
}
//For validating maxlength onpaste event--For textarea fields
function maxLengthPaste(field,maxChars)
{
      event.returnValue=false;
      if(window.clipboardData)
    	  {
      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
			{
      			jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
    	  }
      if (event.clipboardData) 
      {
    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
    		{
    		jqueryAlertMsg('Maxlength Validation',"Characters should not exceed 3000");
        	return false;
        	}
      		event.returnValue=true;
      }
}
function pasteIntercept(evt)
 {  
var input=document.getElementById('presentHistory');
maxLengthPaste(input,3000); 
}
function  pasteInterceptDocRemarks(evt)
{
	var input=document.getElementById('treatingDocRmks');
	maxLengthPaste(input,3000); 
}
function  pasteInterceptOpRemarks(evt)
{
	var input=document.getElementById('opRemarks');
	maxLengthPaste(input,3000); 
}

/*added by venkatesh for getting dental package"*/
/*added for checking whether used dental package is above 50000*/
function verifyUsedPackage()
{
	//document.getElementById("Save").disabled=true;
	//document.getElementById("Save").className='butdisable';
	

		document.getElementById("Save").disabled=true;
		//document.getElementById("Save").className='butdisable';
		document.getElementById("saveDTRS").disabled=true;
		//document.getElementById("saveDTRS").className='butdisable';
		document.getElementById("Reset").disabled=true;
		//document.getElementById("Reset").className='butdisable';
		
		/*Added by Srikalyan for Dental Changes related to TG.  
	*/
	if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
		{
			var localSchemeId=document.getElementById("scheme").value;
			if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
				{
				//(comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
				if(		(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
						(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
						{
							var lErrorMsg=checkDentalTGCond();
							if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' )
								{
									bootbox.alert(lErrorMsg);
									focusBox(document.getElementById('asriCatName'));
									document.getElementById("Save").disabled=false;
									document.getElementById("saveDTRS").disabled=false;
									document.getElementById("Reset").disabled=false;
									return false;
								}	
						}
						
				}								
		}
	/*End for Dental Changes related to TG.  
	*/
var patientId=document.getElementById("patientNo").value;;
var speciality=spec;
  
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
	 bootbox.alert("Your Browser Does Not Support XMLHTTP!");
	}





	 url = 'patientDetails.do?actionFlag=verifyUtilizedPackage&patientId='+patientId+'&speciality='+speciality+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
		    	 //bootbox.alert(resultArray);
			        var resultArray = resultArray.split("*");
					if(resultArray[0]=="SessionExpired"){
			    		bootbox.alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null && resultArray[0]!="")
			        {	
                       //bootbox.alert(resultArray[0]);
			            var resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]",""); 
			            var resultArray2= resultArray1.split("~");
			                 
			            bootbox.alert(resultArray2[0]);
			           
			            var amount=resultArray2[1];
			            var alertFlag=resultArray2[2];
			            var caseBlock=resultArray2[3];
			            //bootbox.alert(caseBlock);
			            if(/*amount>=50000 &&*/ caseBlock=="true")
			            {
                          // bootbox.alert(caseBlock);
                          
                          document.getElementById("Save").disabled=false;
	     			   	  document.getElementById("Save").className='but';
		                  document.getElementById("saveDTRS").disabled=false;
		                  document.getElementById("saveDTRS").className='but';
		                  document.getElementById("Reset").disabled=false;
		                  document.getElementById("Reset").className='but';
                          
			            	document.getElementById("ICDProcName").value="-1";
				            return false;
                           
			            }
			            else
							fn_saveDetails();
				            
			            
			     
			           
			        }

			        else
			        {
			        	fn_saveDetails();
			        }
			    		}
		    }

		}
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	      
	
}
/*added by venkatesh*/

/*added for checking dental procedure validity (1 year,5 years,lifetime) */

function validateDentalProc()
{

var patientId=document.getElementById("patientNo").value;
var icdProcCode=document.getElementById("ICDProcName").value;
var asriCatCode=document.getElementById("asriCatCode").value;

if(asriCatCode=="-1" || icdProcCode=="-1")
{
	return false;
}
if(asriCatCode=='S18')
{

  
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
	 bootbox.alert("Your Browser Does Not Support XMLHTTP!");
	}





	 url = 'patientDetails.do?actionFlag=getDentalProcValidity&patientId='+patientId+'&procCode='+icdProcCode+'&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	 var resultArray=xmlhttp.responseText;
		    	 //bootbox.alert(resultArray);
			        var resultArray = resultArray.split("*");
					if(resultArray[0]=="SessionExpired"){
			    		bootbox.alert("Session has been expired");
			    		 parent.sessionExpireyClose();
			    		 //var fr = partial(parent.sessionExpireyClose);
			    		 //jqueryInfoMsg('Session details',"Session has been expired",fr);
			    		}
			    		else
			    		{
			        if(resultArray[0]!=null && resultArray[0]!="")
			        {	
                       //bootbox.alert(resultArray[0]);
			            var resultArray1 = resultArray[0].replace("[","");
			            resultArray1 = resultArray1.replace("]","");        
			            bootbox.alert(resultArray1);
			            document.getElementById("ICDProcName").value="-1";
			            document.getElementById("ICDProcCode").value=" ";
			            
			            return false;
			          
			          
			        }
			        else
			        {
			        	validateDentalWorkflowCases();
			        }

			        
			    		}
		    }

		}
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);	      
}
}

function fn_saveDetailsWithoutMandate(checkType)
{
document.getElementById("checkType").value=checkType;
fn_saveDetails();
}
var pharmaViewFlg;
function fn_enablePharma(){
	if(confirm("Do you want to authorize drugs dispatch?")==true){
	document.getElementById("enablePharma").value="Y";
	pharmaViewFlg="Y";
	fn_saveDetails();
	}
	else{
		pharmaViewFlg="";
		document.getElementById("enablePharma").value="";
	}
}
function fn_saveDetails(){
	var patId=document.getElementById("patientNo").value;
	document.getElementById("drugs").value=drugs;


	//Mandatory Check validation For Personal History and its sublist
	
	var genTestsCount=0;
	var updTestsCount=0;
	var ipTestsCount=0;
	var lErrorMsg='';
	var lField='';

	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	//verifyUsedPackage();
	

		document.getElementById("Save").disabled=false;
		//document.getElementById("Save").className='but';
		document.getElementById("saveDTRS").disabled=false;
		//document.getElementById("saveDTRS").className='but';
		document.getElementById("Reset").disabled=false;
		//document.getElementById("Reset").className='but';
		



		
	
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
           if(val==null || val=="")
           	{
        	 if(document.forms[0].elements[temp].name.charAt(0)=='g')
        	   genTestsCount=genTestsCount+1;
			 else if(document.forms[0].elements[temp].name.charAt(0)=='u')
        	   updTestsCount=updTestsCount+1;
        	 else if(document.forms[0].elements[temp].name.charAt(0)=='a')
        		 ipTestsCount=ipTestsCount+1;
			if(lField=='')
				lField=''+document.forms[0].elements[temp].id+'';
           	}
           else
			{
				var rtVal=chkSpecailChars(val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.')));
				var fullFileName=val.substring(val.lastIndexOf('\\')+1);
				var fileName1=val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
				if(rtVal ==0)   
				{
					jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
					return false;
				}
				if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
				{
					jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
					return false;
				}
				 var sub=val.substring(val.lastIndexOf('.')+1);
				 if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
				  {
					 if(lErrorMsg=='')
		            	{
							lErrorMsg=lErrorMsg+"Cannot upload exe,rar,war files ";
		       	        }
				  } 
				}
			}
         }
    if(schemeId!='CD202'  && hospType!='G')
    {
	if(genTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"General investigation attachments are mandatory";
	    } 
  	}
	if(updTestsCount>0)
		{
			if(lErrorMsg=='')
			{
				lErrorMsg=lErrorMsg+"General investigation attachments are mandatory ";
			} 
		} 	
	if(ipTestsCount>0)
  	{
		if(lErrorMsg=='')
		{
			lErrorMsg=lErrorMsg+"IP investigation attachments are mandatory ";
	    }
  	}
    }

	/*Added by Srikalyan for Dental Changes related to TG.  
	*/
	if(lErrorMsg==null || lErrorMsg=='' || lErrorMsg==undefined || lErrorMsg.length==0)
		{
			var localSchemeId=document.getElementById("scheme").value;
			if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
				{
					if((comboProcIds!=null && comboProcIds!='' && comboProcIds.length>0) ||
							(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds.length>0) ||
							(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds.length>0))
								{
									lErrorMsg=checkDentalTGCond();
									if(lErrorMsg!=null && lErrorMsg!='' && lErrorMsg!=' ' && lField=='')
										lField='asriCatName';
								}	
				}								
		}
	/*End for Dental Changes related to TG.  
	*/
    
    /*Added by Venkatesh for NIMS and TG Government hospitals (for submit and DTRS Generation skipping mandatory fields)*/
    var checkType='';
        if(document.getElementById("checkType"))
        	checkType=document.getElementById("checkType").value;
        else
        	checkType="Save";
    	
    var opIP='';
   
    if(document.forms[0].patientType[1].checked)
    {
    	 opIP=document.forms[0].patientType[1].value;
    }
    else if(document.forms[0].patientType[0].checked)
    {
    	 opIP=document.forms[0].patientType[0].value;
    }
    /*else if(document.forms[0].patientType[2].checked)
    {
    	 opIP=document.forms[0].patientType[2].value;
    }*/
    
    
    //if(((hospId!=null && hospId=="EHS34")||(schemeId=="CD202" && hospType=="G")) && checkType!="Save" &&  opIP!="ChronicOP")
    if(((schemeId=="CD202" && hospType=="G")) && checkType!="Save" &&  opIP!="ChronicOP")	
    {

        if(checkType=="submit")
        {
    	if(opIP == '')
    	{
    	if(lErrorMsg==''){
    	       lErrorMsg=lErrorMsg+"Select Patient Type";
    		   }
    	        if(lField=='')
    	        lField='patientType';  
    	}
  	   
    	if(opIP == 'IP')
    	{
    	//Mandatory Check for Proposed Surgery Date
    		var catCode=document.getElementById("asriCatName").value;
    	
    		if(document.forms[0].asriCatName.value==null || document.forms[0].asriCatName.value==''  ||     document.forms[0].asriCatName.value=='-1')
			{
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select Speciality ";
    				 }
    		         if(lField=='')
    		         lField='asriCatName';
		    
			}
    		
    		if(document.forms[0].district.value==null || document.forms[0].district.value==''  ||     document.forms[0].district.value=='-1')
    		{
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select District ";
    				 }
    		         if(lField=='')
    		         lField='district';
    	    
    		}
    		
    		if(document.forms[0].currHospId.value==null || document.forms[0].currHospId.value==''  ||     document.forms[0].currHospId.value=='-1')
    		{
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select Hospital ";
    				 }
    		         if(lField=='')
    		         lField='currHospId';
    	    
    		}
    		
    		if(document.forms[0].remarks.value=='' || document.forms[0].remarks.value==null)
        	{
        	 if(lErrorMsg==''){
        	        lErrorMsg=lErrorMsg+"Enter Remarks ";
        			}
        	        if(lField=='')
        	        lField='remarks';   
        	}
    	
    	
    		if(hospGovu!=null && hospGovu!="G")
   			{
    			if(document.forms[0].ipDiagnosedBy.value=='-1' || document.forms[0].ipDiagnosedBy.value==null)
    				{
		    		  if(lErrorMsg==''){
		    		         lErrorMsg=lErrorMsg+"Select IP Patient Diagnosed by ";
		    				 }
		    		         if(lField=='')
		    		         lField='ipDiagnosedBy';   
    				}
    		//Mandatory Check For Doctor Name Drop Down List
	    		if(schemeId=='CD202')
	    		{
	    			if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1')
	    			{
		    		 	 if(lErrorMsg==''){
		    		         lErrorMsg=lErrorMsg+"Select IP Doctor Name ";
		    				 }
		    		         if(lField=='')
		    		         lField='ipDoctorName';
	    		    
	    			}
	    		}
    		
    		}
   		}

    	if(opIP == 'OP')
    	{
    		//Mandatory Check for Prescription Details
    		var schemeId=document.getElementById("scheme").value;
    		var patientScheme=document.getElementById("patientScheme").value;
    		/*if(schemeId=='CD201')
    		{
    		if(drugCount==0)
    			{
    			if(lErrorMsg==''){
    		        lErrorMsg=lErrorMsg+"Add Drug (Main Group Name,Therapeutic Main Group Name,Pharmacologicl SubGroup Name,Chemical SubGroup Name,Chemical Substance Name,Route,Strength,Dosage,Medication Period Details) ";
    				}
    		     if(lField=='')
    		    	 lField='addDrug';
    			}
    		}*/
    		//Mandatory Check For Doctor Details
    	var hospType='${hospType}';
    		/*if(schemeId=='CD201'  || patientScheme!='CD501' || hospType=='C')
    			{
    		if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
    		  if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
    				 }
    		         if(lField=='')
    		         lField='diagnosedBy';   
    			}
    		
    			if(document.forms[0].doctorName.value==null || document.forms[0].doctorName.value=='' || document.forms[0].doctorName.value=='-1'){
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select OP Doctor Name ";
    				 }
    		         if(lField=='')
    		         lField='doctorName';
    		    
    			}
    			}*/



    			//Mandatory Check For Doctor Name TG OP
    		
    			//if(schemeId=='CD202' || hospId=="EHS34")
    			if(schemeId=='CD202' || hospGovu=="G")
    			{

    		
    		if(document.getElementById("consultationDataOld")==null && document.getElementById("consultationDataNew").style.display=="none")
    		{
    			 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
    				 }
    		         if(lField=='')
    		         lField='diagnosedBy';   
    			

    		}	
    	if(document.getElementById("consultationDataOld")&& document.getElementById("consultationDataNew"))
    	{

    		if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
    		{

    			  if(lErrorMsg==''){
    			         lErrorMsg=lErrorMsg+"Please Add Consultation Details ";
    					 }
    			         if(lField=='')
    			         lField='diagnosedBy';   
    				}
    		
    		
    				if(document.getElementById("consultationDataOld").style.display=="none" && document.getElementById("consultationDataNew").style.display=="none" )
    				{
    				if(document.forms[0].diagnosedBy.value=='-1' || document.forms[0].diagnosedBy.value==null){
    					  if(lErrorMsg==''){
    					         lErrorMsg=lErrorMsg+"Select OP Patient Diagnosed by ";
    							 }
    					         if(lField=='')
    					         lField='diagnosedBy';   
    						}

    				}
    	}

    			}
    			
    			
    			 
    		}
    	if(opIP == 'OP')
    	{
    		//Mandatory check for OP No
    		if(document.forms[0].opNo.value=='' || document.forms[0].opNo.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter OP NO ";
    			}
    	        if(lField=='')
    	        lField='opNo';   
    		}
    		if(document.forms[0].opRemarks.value=='' || document.forms[0].opRemarks.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Enter OP Remarks ";
    			}
    	        if(lField=='')
    	        lField='opRemarks';   
    		}
    		if(document.forms[0].opDate.value=='' || document.forms[0].opDate.value==null){
    	 	if(lErrorMsg==''){
    	        lErrorMsg=lErrorMsg+"Select OP Date ";
    			}
    	        if(lField=='')
    	        lField='opDate';   
    		} 

    	}

        }
    	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
    	{
    		var fr=partial(focusBox,document.getElementById(lField));
    		//var fr=partial(focusFieldView,lField);
    		//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
    	   bootbox.alert(lErrorMsg);
    	    	focusBox(document.getElementById(lField));
    	    return;
    	 }
   
    	else
    	{
		var checkType=document.getElementById("checkType").value;
		 if(checkType=='DTRS')
		 {
		 	if(lErrorMsg=='')
		 	{
		 		return true;
		 	}
		 	else
		 	{
		 		return false;
		 	}
		 }
    	if(checkType=="SaveDTRS")
    	{
    		var saveFlag="Yes";
    	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
    	jqueryConfirmMsg('Case Registration Confirmation','Do you want to save and generate DTRS?',fr);

    	}
    	else

    	{
    		var saveFlag="Submit";
    		var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
    		jqueryConfirmMsg('Case Registration Confirmation','Do you want to register patient case?',fr);	
    	}
    	
    	}
    }
    else
    {
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		var fr=partial(focusBox,document.getElementById(lField));
		//var fr=partial(focusFieldView,lField);
		//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
	   bootbox.alert(lErrorMsg);
	    	focusBox(document.getElementById(lField));
	    return;
	 }
	else
	{
	var saveFlag="Yes";
	var checkType="Save";
	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	if(pharmaViewFlg!=null || pharmaViewFlg!=""){
	var fl=partial(pharmaFlgOnFalse);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save?',fr,fl);
	}
	else
		jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save?',fr);
	}}
}
function pharmaFlgOnFalse(){
	pharmaViewFlg="";
	document.getElementById("enablePharma").value="";
	}

function fn_savePatientDetails(checkType)
{
	var doctorType=document.forms[0].diagnosedBy.value;
	document.getElementById("drugs").value=drugs;
	
	var patId=document.getElementById("patientNo").value;
	
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
    var ipCase=document.forms[0].patientType[1].checked;
	
	var lErrorMsg='';
	var lField='';
	var genTestsCount=0;
	var ipTestsCount=0;
	var updTestsCount=0;
	for(var temp=1;temp<document.forms[0].elements.length;temp++)
    {
       if(document.forms[0].elements[temp].type=="file")
       {
       	   var val=document.forms[0].elements[temp].value;
           if(val==null || val=="")
           	{
        	 if(document.forms[0].elements[temp].name.charAt(0)=='g')
        	   genTestsCount=genTestsCount+1;
			else if(document.forms[0].elements[temp].name.charAt(0)=='u')
        		 updTestsCount=updTestsCount+1;
        	 else if(document.forms[0].elements[temp].name.charAt(0)=='a')
        		 ipTestsCount=ipTestsCount+1;
			if(lField=='')
				lField=''+document.forms[0].elements[temp].id+'';
           	}
           else
			{
				var rtVal=chkSpecailChars(val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.')));
				var fullFileName=val.substring(val.lastIndexOf('\\')+1);
				var fileName1=val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
				if(rtVal ==0)   
				{
					jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
					return false;
				}
				if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
				{
					jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
					return false;
				}
				 var sub=val.substring(val.lastIndexOf('.')+1);
				 if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP'))
				  {
					 if(lErrorMsg=='')
		            	{
							lErrorMsg=lErrorMsg+"Cannot upload exe,rar,war files ";
		       	        }
				  } 
				}
			}
         }
    

	



var opIP='';

if(document.forms[0].patientType[1].checked)
{
	 opIP=document.forms[0].patientType[1].value;
}
else if(document.forms[0].patientType[0].checked)
{
	 opIP=document.forms[0].patientType[0].value;
}
//Commented chronic OP
/*else if(document.forms[0].patientType[2].checked)
{
	 opIP=document.forms[0].patientType[2].value;
}*/	   
//if(hospId !=null && hospId!="EHS34")
if(hospGovu!=null && hospGovu!="G")
{
 if(genInventList.length==0 && genOldList.length==0 && (opIP !='' && opIP != 'OP' && opIP != 'ChronicOP')){
 if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select General Investigations ";
		}
        if(lField=='')
        lField='genBlockInvestName';   
}
}
//Check to enable dtrs form
if(checkType=='SaveDTRS')
{
	if(opIP == '')
	{
	if(lErrorMsg==''){
	       lErrorMsg=lErrorMsg+"Select Patient Type";
		   }
	        if(lField=='')
	        lField='patientType';  
	}
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		var fr=partial(focusBox,document.getElementById(lField));
		//var fr=partial(focusFieldView,lField);
		//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
	   bootbox.alert(lErrorMsg);
	    	focusBox(document.getElementById(lField));
	    return;
	 }
	 
	else
	{
		var saveFlag="Yes";
		var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
		jqueryConfirmMsg('Case Registration Confirmation','Do you want to Save and generate DTRS Form?',fr);
	}
}
 if(checkType=='DTRS')
{
	
	if(lErrorMsg=='')
	{
		
		return true;
	}
	else
	{
		return false;
	}
}
else if(checkType=='submit')
{
if(opIP == '')
{
if(lErrorMsg==''){
       lErrorMsg=lErrorMsg+"Select Patient Type";
	   }
        if(lField=='')
        lField='patientType';  
}
else
{
	//Mandatory Check for Diagnosis Details
	var hospType='${hospType}';
if(hospGovu!=null && hospGovu!="G")
{
	if((schemeId=='CD201' && opIP == 'OP' && '${dentalFlg}'!='Y' )||(opIP=='IP')||(hospType!='G'))
	{
			if(document.getElementById("diagType").value=="-1" || document.getElementById("diagType").value=="")
				{
				if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Diagnosis Type ";
					}
			        if(lField=='')
			        lField='diagType'; 
				}
			if(document.getElementById("mainCatName").value=="-1" || document.getElementById("mainCatName").value=="")
			{
			if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Diagnosis Main Category Name ";
				}
		        if(lField=='')
		        lField='mainCatName'; 
			}
			if(document.getElementById("catName").value=="-1" || document.getElementById("catName").value=="")
			{
			if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Diagnosis Category Name ";
				}
		        if(lField=='')
		        lField='catName'; 
			}
			if(document.getElementById('subCatName').value=="-1" || document.getElementById('subCatName').value=="")
			{
			if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Diagnosis Sub Category Name ";
				}
		        if(lField=='')
		        lField='subCatName'; 
			}
			if(document.getElementById("diseaseName").value=="-1" || document.getElementById("diseaseName").value=="")
			{
			if(lErrorMsg==''){
		        lErrorMsg=lErrorMsg+"Select Diagnosis Disease Name ";
				}
		        if(lField=='')
		        lField='diseaseName'; 
			}
			if(document.getElementById("disAnatomicalName").value=="-1" || document.getElementById("disAnatomicalName").value=="")
			{
				if(lErrorMsg==''){
			       lErrorMsg=lErrorMsg+"Select Diagnosis Disease Anatomical Name ";
				   }
			        if(lField=='')
			        lField='disAnatomicalName'; 
			}
			
		}
	}
	
}
if(opIP == 'IP')
{
	//Mandatory Check for Therapy Details
	if(document.forms[0].asriCatName.value==null || document.forms[0].asriCatName.value==''  ||     document.forms[0].asriCatName.value=='-1')
			{
    		 	 if(lErrorMsg==''){
    		         lErrorMsg=lErrorMsg+"Select Speciality ";
    				 }
    		         if(lField=='')
    		         lField='asriCatName';
		    
			}
	if(document.forms[0].district.value==null || document.forms[0].district.value==''  ||     document.forms[0].district.value=='-1')
	{
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select District ";
			 }
	         if(lField=='')
	         lField='district';
    
	}
	
	if(document.forms[0].currHospId.value==null || document.forms[0].currHospId.value==''  ||     document.forms[0].currHospId.value=='-1')
	{
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select Hospital ";
			 }
	         if(lField=='')
	         lField='currHospId';
    
	}
	if(document.forms[0].remarks.value=='' || document.forms[0].remarks.value==null)
	{
	 if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Enter Remarks ";
			}
	        if(lField=='')
	        lField='remarks';   
	}

//Mandatory Check for Proposed Surgery Date
var catCode=document.getElementById("asriCatName").value;
if(hospGovu!=null && hospGovu!="G")
		{
if(document.forms[0].ipDiagnosedBy.value=='-1' || document.forms[0].ipDiagnosedBy.value==null){
	  if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Patient Diagnosed by ";
			 }
	         if(lField=='')
	         lField='ipDiagnosedBy';   
	}
	//Mandatory Check For Doctor Name Drop Down List
	if(schemeId=='CD202')
	{
if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Doctor Name ";
			 }
	         if(lField=='')
	         lField='ipDoctorName';
	    
		}
	}
	
	}

//if(hospId!=null && hospId=="EHS34")
if(hospGovu!=null && hospGovu=="G")
{
	if(document.forms[0].ipDoctorName.value==null || document.forms[0].ipDoctorName.value==''  ||     document.forms[0].ipDoctorName.value=='-1'){
	 	 if(lErrorMsg==''){
	         lErrorMsg=lErrorMsg+"Select IP Consultant Name ";
			 }
	         if(lField=='')
	         lField='ipDoctorName';
	    
		}
}


	}

if(opIP == 'OP')
{
	//Mandatory check for OP No
	if(document.forms[0].opNo.value=='' || document.forms[0].opNo.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter OP NO ";
		}
        if(lField=='')
        lField='opNo';   
	}
	if(document.forms[0].opRemarks.value=='' || document.forms[0].opRemarks.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Enter OP Remarks ";
		}
        if(lField=='')
        lField='opRemarks';   
	}
	if(document.forms[0].opDate.value=='' || document.forms[0].opDate.value==null){
 	if(lErrorMsg==''){
        lErrorMsg=lErrorMsg+"Select OP Date ";
		}
        if(lField=='')
        lField='opDate';   
	} 

}

if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
{
	var fr=partial(focusBox,document.getElementById(lField));
	//var fr=partial(focusFieldView,lField);
	//jqueryAlertMsg('Case Registration Page Mandatory Fields',lErrorMsg,fr);
   bootbox.alert(lErrorMsg);
    	focusBox(document.getElementById(lField));
    return;
 }
else
{
	var saveFlag="Submit";
	var fr=partial(registerCase,personalHistVal,examFndsVal,patId,saveFlag,checkType);
	jqueryConfirmMsg('Case Registration Confirmation','Do you want to register patient case?',fr);
}
}
}  
function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
{
	
	//document.getElementById("Save").disabled=true;
	//document.getElementById("Save").className='butdisable';
    var selInvData='';
	 var selGenInvData='';
	 var updateGenInvData='';
	
	 var consultationData='';
	 var selectedList1  = document.getElementById('addTests');  
	 //var selectedList2  = document.getElementById('investigationSel');
	var contactno = document.forms[0].contactno.value;
	var name = document.forms[0].fname.value;
	var cardNo = document.forms[0].cardNo.value;
	var patientNo = document.forms[0].patientNo.value;
		
	 for(var i=0;i<genInventList.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var price='';
        var investInfo = genInventList[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
	   	   price= investInfo[3];
          if((selGenInvData!=null || selGenInvData!='') && selGenInvData.length>0)
          {
       	   selGenInvData=selGenInvData+'~';
          }
                 
          selGenInvData=selGenInvData+ltext+'$'+lId+'$'+price;  
  			
    	}


	 /*Added by venkatesh to save consultation doctors details*/
	 
	
	 for(var i=0;i<consultDataList.length;i++)
	 	{
		 	
     
     var consultInfo = consultDataList[i].split("~");
     
       if((consultationData!=null || consultationData!='') && consultationData.length>0)
       {
    	   consultationData=consultationData+'~';
       }
              
       consultationData=consultationData+consultInfo[0]+'$'+consultInfo[1]+'$'+consultInfo[2];

       
			
 	}

	 	
 	for(var i=0;i<updateGenInvestLst.length;i++)
	 	{
        var ltext='';
        var lvalue='';
        var lId='';
        var investInfo = updateGenInvestLst[i].split("~");
          ltext = investInfo[2]; 
	   	   lId =  investInfo[1]; 
          if((updateGenInvData!=null || updateGenInvData!='') && updateGenInvData.length>0)
          {
       	   updateGenInvData=updateGenInvData+'~';
          }          
          updateGenInvData=updateGenInvData+ltext+'$'+lId;  
    	}
 	
	 for(var c=0;c<spec.length;c++)
		{
			var specValues=spec[c].split("~");
			if(specValues[8]!='NA' && specValues[7]!='NA')
			{
				if((selInvData!=null || selInvData!='') && selInvData.length>0)
		           {
		           selInvData=selInvData+'~';
		           }          
		           selInvData=selInvData+specValues[8]+'$'+specValues[7]+'$'+specValues[2]; 
			}				   
		}
		//document.forms[0].addTests.value=selGenInvData;
		document.forms[0].investigationsSel.value=selInvData;
		document.forms[0].personalHistVal.value=personalHistVal;
   		document.forms[0].examFndsVal.value=examFndsVal;
		document.getElementById("Save").disabled=true;
		document.getElementById("Save").className='butdisable';
		document.getElementById("saveDTRS").disabled=true;
		document.getElementById("saveDTRS").className='butdisable';
		document.getElementById("Reset").disabled=true;
		document.getElementById("Reset").className='butdisable';
		var url="./patientDetails.do?actionFlag=saveCaseDetails&fromDisp=Y&patientId="+patId+"&cardNo="+cardNo+"&patientNo="+patientNo+"&contactno="+contactno+"&name="+name+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType+"&consultationData="+consultationData+"&genInvestRemove="+genInvestRemove+"&specRemove="+specRemove
		+"&dentalFlg="+'${dentalFlg}'+"&dispDental=Y";
		
		if(document.forms[0].caseId.value!=''){
			url=url+"&caseId="+document.forms[0].caseId.value;
		}
		
		/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
		document.forms[0].action=url;
		document.forms[0].method="post";
		document.forms[0].submit();
}


function fn_resetAll(){
	var fr;var msg='Do you want to reset?';
	fr=partial(resetMedicalDetails);
	if(genOldList.length>0 && specOld.length>0)
		msg='Previously Added Investigation and Speciality Cannot be reset.Please Delete them Manually.\nDo you want to reset?';
	else if	(genOldList.length>0)
		msg='Previously Added Investigation Cannot be reset.Please Delete them Manually.\nDo you want to reset?';	
	else if	(specOld.length>0)
		msg='Previously Added Investigation Cannot be reset.Please Delete them Manually.\nDo you want to reset?';			
	 jqueryConfirmMsg('Case Details Reset Confirmation',msg,fr);
}

function resetMedicalDetails()
{
	//document.forms[0].action="./patientDetails.do?actionFlag=viewPatientDetails&patientId="+document.getElementById("patientNo").value+"&caseId=";
	// document.forms[0].method="post";
	//document.forms[0].submit();
	
	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	
	//if(hospId!="EHS34")
	if(hospGovu!=null && hospGovu!="G")
	{
	document.getElementById("diagType").value=-1;
	document.getElementById("diagCode").value="";
	document.getElementById("mainCatName").options.length = 1;
	document.getElementById("mainCatCode").value = "";
	document.getElementById("catName").options.length = 1;
	document.getElementById("catCode").value= "";
	document.getElementById("subCatName").options.length = 1;
	document.getElementById("subCatCode").value = "";
	document.getElementById("diseaseName").options.length = 1;
	document.getElementById("diseaseCode").value = "";
	document.getElementById("disAnatomicalName").options.length = 1;
	document.getElementById("disAnatomicalCode").value = "";
	
	genInventCount=0;	
	var genTestTable = document.getElementById("genTestTable");
	for(var count = genTestTable.rows.length-1 ; count>0; count--)
		{
		genTestTable.deleteRow(count);
		}
	if(document.getElementById("genTestTable"))
	document.getElementById("genTestTable").style.display="none";
	}
	genInventList=new Array();
	
	document.getElementById("testsCount").value=0;	
	document.forms[0].patientType[1].disabled=true;
	
	
	if(document.forms[0].patientType[1].checked==true)
	{
	document.getElementById("asriCatName").value=-1;
	document.getElementById("asriCatCode").value="";
	document.getElementById("ICDCatName").options.length = 1;
	document.getElementById("ICDCatCode").value="";
	document.getElementById("ICDProcName").options.length = 1;
	document.getElementById("ICDProcCode").value="";
	
	document.getElementById("procUnits").value=-1;
	document.getElementById("unitsTd").style.display='none';
	document.getElementById("unitsLabelTd").style.display='none';
	
	document.getElementById("docSpecReg").options.length = 1;
	document.getElementById("ipDoctorData").style.display='none';
	document.getElementById("ipOtherDocName").value="";
    document.getElementById("ipDocRegNo").value="";
    document.getElementById("ipDocQual").value="";
    document.getElementById("ipDocMobileNo").value="";
	
	catCount=0;
	var catTable = document.getElementById("categoryTable");
	for(var count = catTable.rows.length - 1 ; count>0; count--)
		{
		catTable.deleteRow(count);
		}
	document.getElementById("categoryTable").style.display="none";
	spec=new Array();
	document.getElementById("speciality").value=spec;
	otherDocDetails=new Array();
	document.getElementById("otherDocDetailsList").value=otherDocDetails;
	
	if(document.getElementById("ipDiagnosedBy"))
	document.getElementById("ipDiagnosedBy").value="-1";
	//if(hospId!="EHS34")
	if(hospGovu!=null && hospGovu!="G")
	document.getElementById("ipDoctorName").options.length= 1;
	else
		document.getElementById("ipDoctorName").value="";	
	
	if(specOld.length==0){	
		if(document.getElementById("ipDocNameList"))	
	document.getElementById("ipDocNameList").style.display="";
		if(document.getElementById("IPHead2"))	
	document.getElementById("IPHead2").style.display="none";
	document.getElementById("diagnosisData").style.display="none";
	document.forms[0].patientType[1].checked=false;
	if(genOldList.length>0)
		document.forms[0].patientType[1].disabled=false;
	else
		document.forms[0].patientType[1].disabled=true;
	}else{
		document.forms[0].patientType[1].checked=true;
		document.forms[0].patientType[1].disabled=false;		
		}	
	}
else if(document.forms[0].patientType[0].checked==true)
	{

	//if(hospId!=null && hospId!="EHS34")
	if(hospGovu!=null && hospGovu!="G")
	{
	var existDrugsTable = document.getElementById("existDrugs");
	for(var count = existDrugsTable.rows.length - 1 ; count>0; count--)
		{
		existDrugsTable.deleteRow(count);
		}
	document.getElementById("existDrugs").style.display="none";
	document.getElementById("existDrugsHead").style.display="none";
	
	document.getElementById("drugTypeCode").value=-1;

	document.getElementById("route").value="";
	document.getElementById("strength").value="";
	document.getElementById("dosage").value="";
	document.getElementById("medicatPeriod").value="";
	
	drugCount=0;
	
	var drugTable = document.getElementById("drugsTable");
	for(var count = drugTable.rows.length-1 ; count>0; count--)
		{
		drugTable.deleteRow(count);
		}
	document.getElementById("drugsTable").style.display="none";
	drugs=new Array();
	}
	document.getElementById("opNo").value="";
	document.getElementById("opRemarks").value="";
	document.getElementById("diagnosisData").style.display="none";
	document.getElementById("OPHead2").style.display="none";
	document.getElementById("prescriptionData").style.display="none";
	document.getElementById("OPDoctor").style.display="none";
	document.getElementById("diagnosedBy").value="-1";
	document.forms[0].doctorName.options.length=1;
	document.getElementById("docNameList").style.display="";
	document.getElementById("docNametext").style.display="none";
	document.getElementById("otherDocName").value="";
	document.getElementById("doctorDataDiv").style.display="none";
	document.getElementById("doctorData").style.display="none";
	document.getElementById("docRegNo").value="";
	document.getElementById("docQual").value="";
	document.getElementById("docMobileNo").value="";
	
	document.forms[0].patientType[0].checked=false;
	
	
	}
	/* if(document.getElementById("empPastHistory"))
		document.getElementById("empPastHistory").style.display="none"; */

}

function fn_getDoctorsList(){
	document.getElementById("docNameList").style.display='';
 	document.getElementById("docNametext").style.display='none';
 	document.getElementById("doctorData").style.display='none';
 	document.getElementById('doctorDataDiv').style.display='none';
 	document.getElementById('doctorDataDiv').innerHTML="";
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	var xmlhttp;
    var url;
    var docType; 
    if(document.forms[0].diagnosedBy.value=='-1')
	   {
	   document.forms[0].doctorName.options.length=1;
	   return false;
	   }
   else
	   {
		docType=document.forms[0].diagnosedBy.value;	
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
   		 url = "./patientDetails.do?actionFlag=getDoctorListAjax&fromDisp=Y&callType=Ajax&docTypeVal="+docType+"&hospId="+hospId;    
   		 xmlhttp.onreadystatechange=function()
   		 {
       		if(xmlhttp.readyState==4)
       		{
    	   		var resultArray=xmlhttp.responseText;
           		if(resultArray.trim()=="SessionExpired*")
           		{
           			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
           		}
           		else
           		{
           			resultArray = resultArray.replace("[","");
           			resultArray = resultArray.replace("]*","");
           			var ldocDetailsList = resultArray.split(","); 
           			if(ldocDetailsList.length>0)
           			{
               			document.forms[0].doctorName.options.length=0;
              			document.forms[0].doctorName.options[0]=new Option("--select--","-1");
               			for(var i = 0; i<ldocDetailsList.length;i++)
               			{	
                    		var arr=ldocDetailsList[i].split("~");                     
                   			if(arr[1]!=null && arr[0]!=null)
                    		{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                                               
                        		document.forms[0].doctorName.options[i+1] =new Option(val1,val2);
                    		}
                    /* else
                    {
                        document.forms[0].doctorName.options[0]=new Option("--select--","-1");
                      
                    } */
                		}
            		}
           			if('${PatientOpList.doctorDtls}'!=null && '${PatientOpList.doctorDtls}'!='')
      	 			{document.forms[0].doctorName.value='${PatientOpList.doctorDtls}';

      	 			//fn_getDoctorsDetails();
      	 			}
         		}
       		}			
   		}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	   }
}

function fn_getIPDoctorsList()
{
	var patientType="IP";
	document.getElementById("ipDocNameList").style.display='';
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	var xmlhttp;
	var url;
	var docType; 
	if(document.forms[0].ipDiagnosedBy.value=="-1")
		{
		document.forms[0].ipDoctorName.options.length=1;
		return false;
		}
	else
		{
	docType=document.forms[0].ipDiagnosedBy.value;	
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
	//url = "./patientDetails.do?actionFlag=getDoctorListAjax&docTypeVal="+docType+"&hospId="+hospId+"&patientType="+patientType+"&therapyCategory="+therapyCat;
	url = "./patientDetails.do?actionFlag=getDoctorListAjax&fromDisp=Y&callType=Ajax&docTypeVal="+docType+"&hospId="+hospId; 
	xmlhttp.onreadystatechange=function()
		{
   		if(xmlhttp.readyState==4)
   		{
  	 		var resultArray=xmlhttp.responseText;
   			if(resultArray.trim()=="SessionExpired*"){
   				jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
            }
            else{
       			resultArray = resultArray.replace("[","");
       			resultArray = resultArray.replace("]*","");
       			var ldocDetailsList = resultArray.split(","); 
       			if(ldocDetailsList.length>0)
       			{
           			document.forms[0].ipDoctorName.options.length=0;
           			document.forms[0].ipDoctorName.options[0]=new Option("--select--","-1");
          	 		for(var i = 0; i<ldocDetailsList.length;i++)
           			{	
                		var arr=ldocDetailsList[i].split("~");                     
                		if(arr[1]!=null && arr[0]!=null)
                		{
                    		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                    		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                         
                    		document.forms[0].ipDoctorName.options[i+1] =new Option(val1,val2);
                		}
                		 else
                		{
                    		document.forms[0].doctorName.options[0]=new Option("--select--","-1");
                        } 
            		}
          	 		if('${PatientOpList.doctorDtls}'!=null && '${PatientOpList.doctorDtls}'!='')
          	 			{document.forms[0].ipDoctorName.value='${PatientOpList.doctorDtls}';
          	 			//fn_getIPDoctorsDetails();
          	 			}
        		} 
   		  	}
   		}			
	}
xmlhttp.open("Post",url,true);
xmlhttp.send(null);
		}
	//}
}

function fn_loadProcessImage()
{
	document.getElementById('processImagetable').style.display="";
	setTimeout(function()
	{
		document.getElementById('processImagetable').style.display="none";
	},4000);
}

function fn_ipop()
{
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	var hospId=document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	var surgProc="${surgProc}";
	var opActiveMsg='${opActiveMsg}';
	//if( (schemeId=="CD202" && patientScheme=="CD501" && hospType=="G") || (hospId=="EHS34") )
	if( (schemeId=="CD202" && patientScheme=="CD501" && (hospType=="G" || hospType=="D")) || (hospGovu=="G") )
	{
		
	if( document.forms[0].patientType[0].checked)
	{
	if(opActiveMsg!=null && opActiveMsg!='')
	{
	/* bootbox.alert(opActiveMsg);
	$('#middleFrame_content').find('input, textarea, button, select').attr('disabled','disabled');
	$('.globalEnable').prop('disabled',false);
	document.getElementById("Save").disabled=false;
	document.getElementById("Reset").style.display="none"; */
	}
	document.getElementById("ipNote1").style.display="none";
	//document.getElementById("ipNote2").style.display="none";
	document.getElementById("opNote").style.display="";
// 	document.getElementById("docNameList").style.display="none";
// 	document.getElementById("docName").style.display="none";
// 	document.getElementById("doctorName").style.display="none";
	document.getElementById("empPastHistory").style.display="";
	document.getElementById("prescriptionData").style.display="";
	
		}
	
	else  if(document.forms[0].patientType[1].checked)
	{
		document.getElementById("opNote").style.display="none";
		
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
		
		document.getElementById("empPastHistory").style.display="none";
		document.getElementById("prescriptionData").style.display="none";

		//if(hospId!=null && hospId=="EHS34" && surgProc!=null && surgProc=="Y")
		if(hospGovu!=null && hospGovu=="G" && surgProc!=null && surgProc=="Y")
		{
		
document.getElementById("surgDate").style.display="";
document.getElementById("surgDateHead").style.display="";
		}
		else
			
		{   
			if(document.getElementById("surgDate"))
			document.getElementById("surgDate").style.display="none";
		    if(document.getElementById("surgDateHead"))
			document.getElementById("surgDateHead").style.display="none";
		}
	}

		
	}
// 	fn_enableHistory();
	fn_enableDiag();
	
}

function fn_enableOthers()
{
if(document.getElementById("invOthers"))
{
	if(document.getElementById("invOthers").checked)
	{
	document.getElementById("InvBlockName").style.display="none";	
	document.getElementById("InvName").style.display="none";
	document.getElementById("invLst").style.display="none";	
	document.getElementById("InvBlckLst").style.display="none";
    document.getElementById("otherInvName").style.display="";
    document.getElementById("otherInvName").value="";
    document.getElementById("otherInvName").focus();
    document.getElementById("otherInvNameHead").style.display="";
	}
	else
	{
		document.getElementById("InvBlockName").style.display="";	
		document.getElementById("InvName").style.display="none";
		document.getElementById("invLst").style.display="none";	
		document.getElementById("InvBlckLst").style.display="";
	    document.getElementById("otherInvName").style.display="none";
	    document.getElementById("otherInvNameHead").style.display="none";
	    document.getElementById("otherInvName").value="";
	}
}

	
}
function fn_enableDiag()
{
	var otherDiagName='${otherDiag}';

	if(otherDiagName!=null && otherDiagName!='' && otherDiagName=='Y')
	{
		if(document.getElementById("diagOthers"))
		document.getElementById("diagOthers").checked=true;
		fn_enableOtherDiag();
	}
}



function fn_enableOtherDiag()
{
	
	var elements=document.getElementsByClassName("existDiag");

	for(var i=0;i<elements.length;i++)
	{
		if(document.getElementById("diagOthers").checked)
		{
        elements[i].style.display="none";
        document.getElementById("diagOthersHead").style.display="";
        document.getElementById("diagOthersName").style.display="";
        document.getElementById("diagOthersName").value="";
        document.getElementById("diagOthersName").focus();
		}
		else
		{
		 elements[i].style.display="";
		  document.getElementById("diagOthersHead").style.display="none";
	        document.getElementById("diagOthersName").style.display="none";
	        document.getElementById("diagOthersName").value="";
		}
			
	}
}

function fn_PastData()
{
   //var url="casesApprovalAction.do?actionFlag=getPastHistory&caseId=${caseId}&cardNo=${patCommonDtls.CARDNO}";   
   var url="empPenCaseSearch.do?actionFlag=caseSearch&employeeNo=${patCommonDtls.EMPLOYEENO}&fromPastHistory=Y";
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();	
}
function getDiagnosisDetails(caseStatus,caseId,hospName,regisDate,patientId,caseNo)
{
	//$("#pastHistoryCloseBtn").prop("diasbled",false);
	document.getElementById("pastHistoryCloseBtn").disabled = false;
	
// 	if(caseStatus!=null && caseStatus!='' && caseStatus=='CD34')
// 	{
		
// 		document.getElementById("pastHistoryFrame").src=bootbox.alert("Cannot show the case details as case is in Case Drafted status.");
// 		//jqueryAlertMsg("Alert","Cannot show the case details as case is in Case Drafted status.");
// 		return;
// 	}
// 	else
// 	{
		var url;
		var pastHistory='${fromPastHistory}';

		/*$(function() {
			
	
	        Custombox.open({
	            target: '#pastHistoryModal',
	            effect: 'superscaled'
	        });
	       
	});*/

		
// 		if(caseStatus!=null && caseStatus!='' && caseStatus=='CD2')
// 		{
// 			if(pastHistory!=null && pastHistory!='' && pastHistory=='Y')
// 				url="preauthDetails.do?actionFlag=getOnlineCaseSheet&caseId="+caseId+"&patientId="+patientId+"&caseSheetFlag=Y&closeBut=Y&fromDisp=Y";     
// 			else
// 				url="clinicalNotesAction.do?actionFlag=printDischargeForm&caseId="+caseId+"&decFlag=N&fromDisp=Y";	
// 		}
// 		else
			url="patientDetails.do?actionFlag=dtrsPrintForm&patientId="+patientId+"&caseId="+caseNo+"&decFlag=N&fromDisp=Y&dispDental=Y";
			//var url='empPenCaseSearch.do?actionFlag=getDiagnosisDetails&patientId='+patientId+'&caseNo='+caseNo+'&hospName='+hospName+'&regisDate='+regisDate+'&caseId='+caseId+'&caseStatus='+caseStatus;
		//window.open(url,"case_details",'toolbar=no,resizable=yes,scrollbars=yes,menubar=no,location=no,height=700,width=900,left=10,top=15');
		document.getElementById("pastHistoryFrame").src=url;

	
		
		
// 	}
	
}

function fn_addAttach()


{
	var url="attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId=${caseId}&caseAttachmentFlag=Y&caseApprovalFlag=Y&module=Operations&opAttach=Y";
	
		document.getElementById("addAttachFrame").src=url;
}


function check_maxLength(id,remarkLength)
{
	var name = document.getElementById(id).value;
	 if(name != null && name !='' && name.length >= remarkLength)
 	{
	 	bootbox.alert("length should not exceed " +remarkLength );
	 	  document.getElementById(id).value='';
	 		document.getElementById(id).focus();
 	}
   if(name.trim().length==0){
	bootbox.alert("Multiple Spaces are not allowed");
	document.getElementById(id).value='';
	document.getElementById(id).focus();
	   }
 

}

$(function() {
	blinkeffect('#empPastHstry');
	})
	function blinkeffect(selector) {
	var jq = jQuery.noConflict();
	jq(selector).fadeOut('slow', function() {
	jq(this).fadeIn('slow', function() {
	blinkeffect(this);
	});
	});
	}

$(function() {
	blinkeffect('.autoData');
	})
	function blinkeffect(selector) {
	var jq = jQuery.noConflict();
	jq(selector).fadeOut('slow', function() {
	jq(this).fadeIn('slow', function() {
	blinkeffect(this);
	});
	});
	}

$(document).ready(function()
		{
	var jq = jQuery.noConflict();
jq('.otherFields').keyup(function(){
	var text=jq(this).val();

 
    var textLength=text.length;
    if(textLength==1)
    {
    if(text.indexOf(" ")!=-1)
    {
	  bootbox.alert("should not Start with Spaces");
      jq(this).val("");
	  return false;
    }
    }

    var regExp1=/^\s*[a-zA-Z0-9,.\n\ s]+\s*$/;

    if(!regExp1.test(text))
    {
    alert("Special Characters are not allowed");
    
    return false;
    }

    

	});
		
    jq(".btn").click(function(event){
        event.preventDefault();
    });
    
    
	if(labGrp == 'GP797'){
		jq('#medicalDetailsTable,#genInvestList,#ipOpTable,#diagnosisData,#OPHead2,#OPDoctor,#remarksDivIP,#btnsTable, .notDisplayLab, #IPHead2, #prescriptionDataTable,#pastDrugsTable').css('display','none');
	}
	else if(labGrp == 'GP801'){
		jq('#medicalDetailsTable,#genInvestList,#ipOpTable,#diagnosisData,#OPHead2,#OPDoctor,#remarksDivIP,#btnsTable, .notDisplayLab, #IPHead2, #genTestTableID').css('display','none');
	}
	else{
		jq('#prescriptionDataTable,#pastDrugsTable').css('display','none');
	}
});

		
function getDiagListAuto()
{
	var xmlhttp;
	var url;
	var diagName=document.getElementById("diagnosisName").value;
	var diagType=document.forms[0].diagCondition.value;
	
	if(window.XMLHttpRequest)
	{
		xmlhttp=new XMLHttpRequest();
	}
	else if(window.ActiveXObject)
	{
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	  else
		{
		jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
		}
	 
	document.getElementById("disNameAuto").options.length=0;
	document.getElementById("disNameAuto").options[i]=new Option("----SELECT----","-1");
	//document.getElementById("disNameAuto").value="-1";
	jq("#disNameAuto").select2('val','-1');
	
	
if(diagName.length<=3)
{
	document.getElementById("diagAuto1").style.display="";
	document.getElementById("diagAuto2").style.display="none";
	return false;
}
else
{
	
	xmlhttp.onreadystatechange=function(){

if(xmlhttp.readyState==4)
{
var result=xmlhttp.responseText;
result = result.replace("*","");
if(result.trim()=="SessionExpired*"){
	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
}
else
{
var jsonData=JSON.parse(result);
if(jsonData.length>0)
{
document.getElementById("diagAuto1").style.display="none";
document.getElementById("diagAuto2").style.display="";
}
else
{
	document.getElementById("diagAuto1").style.display="";
	document.getElementById("diagAuto2").style.display="none";
}
for(var i=0;i<jsonData.length;i++)
{
	document.getElementById("disNameAuto").options[i+1]=new Option(jsonData[i].VALUE,jsonData[i].ID);

}
}	
}	
	};
	url = "./patientDetails.do?actionFlag=getdiagListAuto&callType=Ajax&diagName="+diagName+"&diagType="+diagType; 
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
}

function getDiagDetailsAuto()
{
	var disAnatomicalCode=document.getElementById("disNameAuto").value;
	
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


	if(disAnatomicalCode=='-1')
	{
return false;
	}
	else
	{
	xmlhttp.onreadystatechange=function(){

		if(xmlhttp.readyState==4)
   		{
			var result=xmlhttp.responseText;
		
			result = result.replace("*","");
			
			if(result.trim()=="SessionExpired*"){
					jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
			}
			else
			{
				var jsonData=JSON.parse(result);

				
				
				document.getElementById("diagType").value=jsonData.DIAGNOSISCODE;
				document.getElementById("diagCode").value=jsonData.DIAGNOSISCODE;

				document.getElementById("mainCatName").options[0]=new Option(jsonData.MAINCATNAME,jsonData.MAINCATCODE);
				document.getElementById("mainCatCode").value=jsonData.MAINCATCODE;

				document.getElementById("catName").options[0]=new Option(jsonData.CATNAME,jsonData.CATCODE);
				document.getElementById("catCode").value=jsonData.CATCODE;

				document.getElementById("subCatName").options[0]=new Option(jsonData.SUBCATNAME,jsonData.SUBCATCODE);
				document.getElementById("subCatCode").value=jsonData.SUBCATCODE;

				document.getElementById("diseaseName").options[0]=new Option(jsonData.DISEASENAME,jsonData.DISEASECODE);
				document.getElementById("diseaseCode").value=jsonData.DISEASECODE;

				document.getElementById("disAnatomicalName").options[0]=new Option(jsonData.ANATOMICALNAME,jsonData.ANATOMICALCODE);
				document.getElementById("disAnatomicalCode").value=jsonData.ANATOMICALCODE;
				
				
			
			}
			
   		}};
	}
	url = "./patientDetails.do?actionFlag=getdiagDtlsAuto&callType=Ajax&disAnatomicalCode="+disAnatomicalCode; 
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}

/* Function to segregate Dental Clinics and Dental Hospitals by providing the option of IP  */
<%--function fn_dentalCheck()
{
	if(document.getElementById("patNo"))
	{
	var data=document.getElementById("patNo");
	var patientId=data.innerText;
	patientId=patientId.replace(/[^a-z0-9\s]/gi, '');
	patientId=patientId.trim();
	var uid= '<%=session.getAttribute("UserID") %>' ;
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
		url = "./patientDetails.do?actionFlag=checkClinic&callType=Ajax&userId="+uid+"&patientId="+patientId; 
		xmlhttp.onreadystatechange=function()
			{
	   		if(xmlhttp.readyState==4)
	   		{
	   			var result=xmlhttp.responseText;
	   			result=result.replace(/[^a-z0-9\s]/gi, '');
	   			result=result.trim();
	   			if(result!="hospital")
	   			{
	   				document.getElementById("patientType1").style.visibility = "hidden";
	   			
	   			}
	   			
	   		}
		}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}--%>

function fn_enableIp()
{
	var invName=document.getElementById("otherInvName").value;

	if(invName!=null || invName!="")
	{
document.forms[0].patientType[1].disabled=false;
	}
	else
	{
		document.forms[0].patientType[1].disabled=true;
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

function fn_disDrugPat()
{
	fn_saveDetails();
}


function getIcdCategoryCodes()
{
	var hospId = document.getElementById("hospitalId").value;
	var hospGovu = document.getElementById("hospGovu").value;
	var treatType=document.forms[0].patientType.value;
	
	document.getElementById("asriCatCode").value="";
	document.forms[0].investigations.options.length=0;
	document.forms[0].docSpecReg.options.length=1;
	document.forms[0].ICDCatName.options.length=1;
	document.getElementById("unitsTd").style.display='none';
	document.getElementById("unitsLabelTd").style.display='none';
	document.getElementById("procUnits").value='-1';
	getTherapyICDProcedureList(1);
	if(document.getElementById("asriCatName").value=="-1")
	{
		return false;
	}
	else
	{
		var asriCatCode=document.getElementById("asriCatName").value;
		var schemeId=document.forms[0].scheme.value;
		if(medOrSur!=''){
              if(medOrSur!=asriCatCode.substr(0,1)){
            	  var fr = partial(focusBox,document.getElementById("asriCatName"));
            	  alert("Category("+ asriCatCode+") cannot be added.Please select either Medical or Surgical type Only.");
            	  partial(focusBox,document.getElementById("asriCatName"));
            	  document.getElementById("asriCatName").value='-1';
            	  return false;
				}
		}	
		document.getElementById("asriCatCode").value=asriCatCode;
		/*if(asriCatCode.trim()=='S18')
		{
			document.getElementById("unitsTd").style.display='';
			document.getElementById("unitsLabelTd").style.display='';
		}*/
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
				else{
					if(resultArray!=null)
					{
						var totalList = resultArray.split("*");
						
						var categList = totalList[0];var docList = totalList[1];
                    
						categList = categList.replace("[","");categList = categList.replace("@]","");
						if(docList!=null)
							{
						docList = docList.replace("[","");
						docList = docList.replace("@]","");  
						var doctorList = docList.split("@,"); 
					        }
						var categoryList = categList.split("@,"); 
						
						if(categoryList.length>0)
						{  
							document.forms[0].ICDCatName.options.length=0;
							document.forms[0].ICDCatName.options[0]=new Option("---select---","-1");
							for(var i = 0; i<categoryList.length;i++)
							{	
								var arr=categoryList[i].split("~");
								if(arr[1]!=null && arr[0]!=null)
								{
									var val1 = arr[1].replace(/^\s+|\s+$/g,"");
									var val2 = arr[0].replace(/^\s+|\s+$/g,"");
									document.forms[0].ICDCatName.options[i+1] =new Option(val1,val2);
								}
							}
						}
						if(doctorList.length>0)
						{  
							document.forms[0].docSpecReg.options.length=0;
							document.forms[0].docSpecReg.options[0]=new Option("---select---","-1");
							for(var i = 0; i<doctorList.length;i++)
							{	
								var arr=doctorList[i].split("~");
								if(arr[1]!=null && arr[0]!=null)
								{
									var val1 = arr[1].replace(/^\s+|\s+$/g,"");
									var val2 = arr[0].replace(/^\s+|\s+$/g,"");
									document.forms[0].docSpecReg.options[i+1] =new Option(val1,val2);
								}
							}
						}
					}
				}
			}
		}
    	
		url = "./patientDetails.do?actionFlag=getICDCatByAsriCode&callType=Ajax&lStrAsriCatId="+asriCatCode+"&treatType="+treatType+"&hospId="+hospId;
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
	}
}

function getTherapyICDProcedureList(param)
{
	var patientId=document.getElementById("patientNo").value;
	var hospId = document.getElementById("hospitalId").value;
	var treatType=document.forms[0].patientType.value;
	var hosType=null;
		 if(document.getElementById("hosType")!=null)
			{
			hosType=document.getElementById("hosType").value;
			}
	document.forms[0].ICDCatCode.value="";
	document.forms[0].ICDProcName.options.length=1;
	getProcedureCodes();
	if(document.forms[0].ICDCatName.value=="-1")
		{
		return false;
		}
	else
		{
	var icdCatCode=document.forms[0].ICDCatName.value;
	var asriCode=document.forms[0].asriCatName.value;
	document.forms[0].ICDCatCode.value=icdCatCode;
	var schemeId=document.forms[0].scheme.value;
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
            else{
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var procedureList = resultArray.split("@,"); 
                	if(procedureList.length>0)
                	{  
                		if(param==1)
            			{
                			document.forms[0].ICDProcName.options.length=0;
                        	document.forms[0].ICDProcName.options[0]=new Option("---select---","-1");
            			}
            			for(var i = 0; i<procedureList.length;i++)
                		{	
                     		var arr=procedureList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                         		if(param==1)
                    	 		{
                        	 		document.forms[0].ICDProcName.options[i+1] =new Option(val1,val2);
                    	 		}
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getProcByCat&callType=Ajax&lStrICDCatId="+icdCatCode+"&lStrAsriCode="+asriCode+"&patientId="+patientId+"&treatType="+treatType+"&hospId="+hospId+"&hosType="+hosType;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}

function getProcedureCodes()
{
	var jq = jQuery.noConflict();
	document.getElementById("ICDProcCode").value="";
	var locSchemeId=document.getElementById("scheme").value;
	var asriCatCode=document.getElementById("asriCatCode").value;
	if(document.getElementById("ICDProcName").value=="-1")
		{
			document.getElementById("unitsTd").style.display="none";
			document.getElementById("unitsLabelTd").style.display="none";
			return false;
		}
	else
		{
			var icdProcCode=document.getElementById("ICDProcName").value;
			document.getElementById("ICDProcCode").value=icdProcCode;
			if(asriCatCode!=null  && ((locSchemeId !=null && locSchemeId=='CD202') || (locSchemeId==null || locSchemeId=='' || locSchemeId==' ') ))
				getTherapyInvestigations();
		}
	
	if(asriCatCode!=null && asriCatCode=='S18')
		{
			var checkCond=0;
			document.forms[0].procUnits.options.length=0;
			document.forms[0].procUnits.options[0]=new Option("---select---","-1");
			if(locSchemeId !=null && locSchemeId=='CD202')
				{
					var procName=jq("#ICDProcName option:selected").text();
					if(locSchemeId!=null && locSchemeId!='' && locSchemeId=='CD202')
						{
							//Checking Non Combo Codes at the time of Adding new Procedure 
							if(nonComboProcIds!=null && nonComboProcIds!=='' && nonComboProcIds!= ' ')
								{
									var procWiseLst=nonComboProcIds.split("$");
									for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
										{
											var alertCont=null;
											var indiSpecCombLst=procWiseLst[j].split(",");
											var addedSpecDtls=indiSpecCombLst[0].split("!@#");
											
											var allCombos=indiSpecCombLst[1].split("~");
											for(var k=0;k<allCombos.length;k++)//Checking for every Combo Proc for added Proc
												{
													var splitComboProc=allCombos[k].split("@");
													var noncomboProcedureId=splitComboProc[0];
													var noncomboProcedureName=splitComboProc[1];
													if(allCombos[k].indexOf(document.getElementById("ICDProcName").value+"@")!='-1')
														{
															checkCond++;
															if(alertCont==null || alertCont=='' || alertCont==' ')
																alertCont=noncomboProcedureName+"("+noncomboProcedureId+")";
															else
																alertCont=alertCont+" , "+noncomboProcedureName+"("+noncomboProcedureId+")";
														}
												}
											if(checkCond>0)
												{
													var alertMsg="As Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
													
													resetDentalProc();
													bootbox.alert(alertMsg);
													focusBox(document.getElementById("ICDProcName"));
													return false;
												}
										}
								}
							//Checking Non Combo Codes at the time of Adding new Procedure for Stand Alone Procedures
							if(standaloneProcIds!=null && standaloneProcIds!=='' && standaloneProcIds!= ' ')
								{
									var procWiseLst=standaloneProcIds.split("$");
									for(var j=0;j<procWiseLst.length;j++)//Checking for every added Proc
										{
											var alertCont=null;
											var indiSpecCombLst=procWiseLst[j].split(",");
											var addedSpecDtls=indiSpecCombLst[0].split("!@#");
											
											var allCombos=indiSpecCombLst[1].split("~");
											for(var k=0;k<allCombos.length;k++)//Checking for every Combo Proc for added Proc
												{
													var splitComboProc=allCombos[k].split("@");
													var noncomboProcedureId=splitComboProc[0];
													var noncomboProcedureName=splitComboProc[1];
													if(allCombos[k].indexOf(document.getElementById("ICDProcName").value+"@")!='-1')
														{
															checkCond++;
															if(alertCont==null || alertCont=='' || alertCont==' ')
																alertCont=noncomboProcedureName+"("+noncomboProcedureId+")";
															else
																alertCont=alertCont+" , "+noncomboProcedureName+"("+noncomboProcedureId+")";
														}
												}
											if(checkCond>0)
												{
													var alertMsg="As Stand Alone Procedure "+addedSpecDtls[1]+"("+addedSpecDtls[0]+") is added,Mandatory Non Combinational Procedures "+alertCont+" should not be added.";
													
													resetDentalProc();
													bootbox.alert(alertMsg);
													focusBox(document.getElementById("ICDProcName"));
													return false;
												}
										}
								}
							if(checkCond==0)
								{
									getTherapyInvestigations();
									getDentalConditions(icdProcCode,procName,locSchemeId,'allCond');
								}
						}
				}
			else
				{
					document.getElementById("unitsTd").style.display='';
					document.getElementById("unitsLabelTd").style.display='';
					getDentalUnits("N");
				}
		}
	
	
	
}
function fn_openAllInOneReport()
{
//	var reportType ="Haematology Report";
	var patientId=document.getElementById("patientNo").value;
	//alert(patientId);
	url="patientDetails.do?actionFlag=getLabReport&patientId="+patientId+"&combinedReport=Y";
	window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	/*document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();*/
	
}

function generateDTRSPrint(caseId,hospId)
{
	if(caseId!=null && caseId!='')
		{
	var url="./patientDetails.do?actionFlag=dtrsPrintForm&fromDisp=Y&caseId="+caseId+"&printFormType=DTRS&dispDental=Y";
	window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
		}
	else
		{
		bootbox.alert("Please generate DTRS Form and try again");
		return false;
		}
}

function enableIPOP()
{
	var jq = jQuery.noConflict();
	var hospGovu = document.getElementById("hospGovu").value;
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	
	var hospId = document.getElementById("hospitalId").value;
	
	var caseId=document.getElementById("caseId").value;
	if(hospGovu!=null && hospGovu!="G")
		{
	document.getElementById("diagType").value=-1;
	document.getElementById("diagCode").value="";
	document.getElementById("mainCatName").options.length = 1;
	document.getElementById("mainCatCode").value = "";
	document.getElementById("catName").options.length = 1;
	document.getElementById("catCode").value= "";
	document.getElementById("subCatName").options.length = 1;
	document.getElementById("subCatCode").value = "";
	document.getElementById("diseaseName").options.length = 1;
	document.getElementById("diseaseCode").value = "";
	document.getElementById("disAnatomicalName").options.length = 1;
	document.getElementById("disAnatomicalCode").value = "";
		}
	catCount=0;
	
	
	
	otherDocDetails=new Array();
	document.getElementById("otherDocDetailsList").value=otherDocDetails;
	
	if(hospGovu!=null && hospGovu!="G")
	{
	var existDrugsTable = document.getElementById("existDrugs");
	for(var count = existDrugsTable.rows.length - 1 ; count>0; count--)
		{
		existDrugsTable.deleteRow(count);
		}
	document.getElementById("existDrugs").style.display="none";
	document.getElementById("existDrugsHead").style.display="none";
	}
	
	if(document.forms[0].patientType[1].checked)
		{
		if(document.getElementById("toDispatch"))
		document.getElementById("toDispatch").disabled=true;
		
		if(schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
		{
		if(hospGovu!=null && hospGovu!="G")
		
			{
		jq('.onlyAp').css('display','');
		
		
		document.getElementById("onlyIp1").style.display="";
		document.getElementById("onlyIp2").style.display="";
		
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
			}
		//document.getElementById("ipNote2").style.display="";
		document.getElementById("ipNote1").style.display="";
		document.getElementById("opNote").style.display="none";
		document.getElementById("Save").style.display="";
		document.getElementById("Reset").style.display="";
	
		if(document.getElementById("empPastHistory"))
		document.getElementById("empPastHistory").style.display="none";
		
		}

		document.getElementById("asriCatName").value=-1;
		//if(hospId!=null && hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")	
		{
		document.getElementById("ipDiagnosedBy").value="-1";
		document.getElementById("ipDoctorName").options.length= 1;
		}
		
		document.getElementById("IPHead2").style.display="";
		document.getElementById("OPHead2").style.display="none";
		document.getElementById("prescriptionData").style.display="none";
		document.getElementById("OPDoctor").style.display="none";
		document.getElementById("diagnosisData").style.display="";
		}
		
		
	else if(document.forms[0].patientType[0].checked)
		{
		if(document.getElementById("toDispatch"))
		document.getElementById("toDispatch").disabled=false;
		//if((schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")|| hospId=="EHS34")
		if((schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")|| hospGovu=="G")
		{
			if(document.getElementById("onlyIp1"))
			document.getElementById("onlyIp1").style.display="none";
			if(document.getElementById("onlyIp2"))
			document.getElementById("onlyIp2").style.display="none";
			document.getElementById("ipNote1").style.display="none";
			//document.getElementById("ipNote2").style.display="none";
			document.getElementById("opNote").style.display="";
			
			document.getElementById("docName").style.display="none";
			document.getElementById("docNameList").style.display="none";
			document.getElementById("doctorName").style.display="none";
			
			if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="";
			
			document.getElementById("addAttach").style.display="";
			
			jq('.onlyAp').css('display','none');
			var opActiveMsg=document.getElementById("opActiveMsg").value;
			
			if(opActiveMsg!=null && opActiveMsg!='')
			{
				document.getElementById("Save").style.display="none";
				document.getElementById("Reset").style.display="none";	
				//enable_dtrsform();
			}
		
		}
		
		if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="";
		//if(hospId!=null && hospId!="EHS34")
			if(hospGovu!=null && hospGovu!="G")
			{
		document.getElementById("drugTypeCode").value=-1;
		document.getElementById("diagnosedBy").value="-1";
		document.getElementById("dosage").value="-1";
		document.getElementById("medicatPeriod").value="";
				
		drugCount=0;
		
		var drugTable = document.getElementById("drugsTable");
		for(var count = drugTable.rows.length-1 ; count>0; count--)
			{
			drugTable.deleteRow(count);
			}
		document.getElementById("drugsTable").style.display="none";
			}
		drugs=new Array();
		drugsTemp=new Array();
		
		document.getElementById("opNo").value="";
		document.getElementById("opRemarks").value="";
		
		document.forms[0].doctorName.options.length=1;
		document.getElementById("docNameList").style.display="";
		document.getElementById("docNametext").style.display="none";
		document.getElementById("otherDocName").value="";
		document.getElementById("doctorDataDiv").style.display="none";
		document.getElementById("doctorData").style.display="none";
		document.getElementById("docRegNo").value="";
		document.getElementById("docQual").vlaue="";
		document.getElementById("docMobileNo").value="";
		
		//document.getElementById("ChronicOPPrescription").style.display="none";
		//document.getElementById("chronicTherapy").style.display="none";
		document.getElementById("IPHead2").style.display="none";
		document.getElementById("OPHead2").style.display="";
		
		document.getElementById("prescriptionData").style.display="";
		document.getElementById("OPDoctor").style.display="";
		document.getElementById("diagnosisData").style.display="";
	//	document.getElementById("ChronicOPTherapy").style.display="none";
		}
	else if(document.forms[0].patientType[2] && document.forms[0].patientType[2].checked)
	{
	

		document.getElementById("OPDoctor").style.display="";
		
		
		document.getElementById("IPHead2").style.display="none";
		document.getElementById("OPHead2").style.display="none";
		document.getElementById("prescriptionData").style.display="none";
		document.getElementById("OPDoctor").style.display="none";
		document.getElementById("diagnosisData").style.display="none";
		
		
	}
	//parent.fn_resizePage();
}

</script>





</head>
</html>

