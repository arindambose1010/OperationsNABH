- <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" import="java.util.ResourceBundle,java.util.ArrayList,java.util.HashMap" isErrorPage="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ include file="/common/include.jsp"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>Referral Route</title>  
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen"> 
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script LANGUAGE="JavaScript" type="text/javascript" SRC="Preauth/maximizeScreen.js"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script LANGUAGE="JavaScript" SRC="scripts/PreauthScripts.js"></script>


<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>

<%@ include file="/common/includePatientDetails.jsp"%>
<c:if test="${resMsg != null }">
<script>
jqueryInfoMsg('Result','${resMsg}');
</script>
</c:if>
<script type="text/javascript">
var scc='';
function fn_maxmizeTop()
{
	parent.fn_maxmizeTop();
}
function fn_maxmizeRight(){
	parent.fn_maxmizeRight();
}

var ViewType = '${viewType}';
var caseFlag = parent.caseApprovalFlag;
var caseId = '${caseId}';
function viewTelephonicInfo(telephonicId){
	window.open('preauthDetails.do?actionFlag=ViewTelephonicDtls&telephonicId='+telephonicId,'','scrollbars=1,left=20,top=20,width=1000,height=650');
}
function CasesView()  
{	
     var url='casesSearchAction.do?actionFlag=OnloadCaseSearch&caseSearchType=ipTab&CaseId=${caseId}&flag=N&casesForApproval='+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;
	 document.forms[0].action=url;
	 document.forms[0].target="_parent";
     document.forms[0].submit();	
}
function fn_getClinicData(vType,message)
{
//fn_changeColors(id);
//	var url="clinicalNotesAction.do?actionFlag=clinicalNotes&caseId=${PatientDtls.PreauthVO.caseId}&CaseStat=${PatientDtls.PreauthVO.caseStatusId}&notesType="+vType+"&RestrictFlag=${RestrictFlag}";   
	var url="clinicalNotesAction.do?actionFlag=clinicalNotes&caseId=${caseId}&notesType="+vType+"&caseApprovalFlag="+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&ClinicalCount='+message+'&disSearchType='+parent.disSearchType+'&module='+parent.module;
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();
}

function showBiometricAttendence()
{
	var url="/ASRI/preauthAction.do?actionFlag=getPatientBiometric&CaseId=${caseId}&viewType=View";
	showBiometricAtt = window.open(url,"biometricAtt",'width=750,height=200,resizable=yes,top=100,left=0,toolbar=no,minimize=no,status=no,memubar=no,location=no,scrollbars=yes');
}
function openPhoto(arg)
{
 var patphoto = document.getElementById("patPhoto");
 if(patphoto.alt == 'Click here to View Patient Photo')
 {
  patphoto.alt = 'Click to view larger Image';
  patphoto.src = '/ASRI/FrontServlet?requestType=ReadAttachRequestHandler&actionVal=openAtach&filepath='+arg;
  
 }
 else
 {
   fn_openAtach1(arg);
 }
}
function chkSpecailChars(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
}
function sendImage()
{
  var Pid='${patCommonDtls.PATID}';
  var Rno='${patCommonDtls.CARDNO}';
  var CaseStat='${patCommonDtls.CASESTAT}';
  var CaseId='${caseId}';
  var url="/ASRI/FrontServlet?requestType=BlobInsertRH&RestrictFlag=${RestrictFlag}&actionVal=BlobInsertImage&PatientId="+Pid+"&RationNo="+Rno+"&feedback=g=fb&caseStat="+CaseStat+"&CaseId="+CaseId; 
  document.forms[0].action=url;
  document.forms[0].method="post";
  document.forms[0].submit();
}
function Validate(arg)
{
    var validFileobj=eval(document.getElementById(arg));
    var validFile=validFileobj.value;
    if(validFile=="")
    {
          alert("Please  Attach File");
          return;
    }
    else
    {          
          var pos1=validFile.lastIndexOf("\\");
          var sub1=validFile.substring(pos1+1,len);
          newArray=sub1.split(' ');
          if(newArray.length > 1)
          {
            alert('File Name contains spaces please Rename the file name with out spaces..');
            return false;
          }		  
          vSplit=validFile.split("\\");
          vFileName = vSplit[(vSplit.length)-1];      
          var len=validFile.length; 		  
          if(len > 0)
          {
              var pos=validFile.lastIndexOf(".");
              var sub=validFile.substring(pos+1,len);
    
              if((sub=="gif")||(sub=="jpeg")||(sub=="jpg")||(sub=="bmp")||(sub=="WMF")
              ||(sub=="wmf")||(sub=="GIF")||(sub=="JPEG")||(sub=="JPG")||(sub=="BMP"))
              {
                     rtVal = chkSpecailChars(vFileName);
                     if(rtVal ==0) 
                     {
                           alert('File Name should not contain special characters');
                           if(arg == 'Attachment')
                           {
                        	   window.form1.reset();
                        	 
                           }
                           window.location.reload( );                   
                     }
                    else
                    {          
                      if(arg == 'Attachment')
                      {
                    	  fn_UploadFile();
                      }
                    }    
              }
              else
              {
                 if(arg == 'Attachment')
                 {
                	 window.form1.reset();

                 
                 }                 
                 alert("Attach Images only");                 
                 //window.location.reload( );
              }
           }
    }
}
function validateFile()
{
    event.returnValue=false;
}
function right(e) 
{
    if (navigator.appName == 'Netscape' && (e.which == 3 || e.which == 2))
        return false;
    else if (navigator.appName == 'Microsoft Internet Explorer' && (event.button == 2 || event.button == 3)) 
    {
        alert("Sorry, you do not have permission to right click");
        return false;
    }
    return true;
}
function fn_PastData()
{
   //var url="casesApprovalAction.do?actionFlag=getPastHistory&caseId=${caseId}&cardNo=${patCommonDtls.CARDNO}";   
   var url="empPenCaseSearch.do?actionFlag=caseSearch&employeeNo=${patCommonDtls.EMPLOYEENO}&fromPastHistory=Y";
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();	
}
function fn_attachments()
{
	var url="attachmentAction.do?actionFlag=onload&UpdType=ehfPreauth&caseId=${caseId}&caseAttachmentFlag=Y&caseApprovalFlag="+parent.caseApprovalFlag+'&errSearchType='+parent.errSearchType+'&disSearchType='+parent.disSearchType+'&module='+parent.module;      
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();	
}
function fn_OnlineCaseSheet(caseSheetFlag)
{
	var url="preauthDetails.do?actionFlag=getOnlineCaseSheet&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseSheetFlag="+caseSheetFlag;      
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();	
}
function fn_getIP()
{	
	if(parent.ipRefershFlag != null && parent.ipRefershFlag !='' && parent.ipRefershFlag=='Y')
		{
		return;
		}
	else
		{
   var url="patCommonDtls.htm?actionFlag=getPatDetails&CaseId=${patCommonDtls.CASEID}&PatientID=${patCommonDtls.PATID}";   
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();
		}
}
function fn_flagging()
{
	url="flaggingAction.do?actionFlag=getFlagPage&caseId=${caseId}";
	document.forms[0].action=url;
	document.forms[0].target="bottomFrame";
	document.forms[0].submit();
}

function fn_getFraudCr()
{
  //fn_changeColors(id);
   var url="patCommonDtls.htm?actionFlag=viewCMAremarks&CaseId=${patCommonDtls.CASEID}";   
   document.forms[0].action=url;   
   document.forms[0].target="bottomFrame";
   document.forms[0].submit();    
}
function fn_preauthDetails()
{	
	 var url1="preauthDetails.do?actionFlag=preauthDetailsEhf&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseApprovalFlag="+parent.caseApprovalFlag+"&patientAge=${patCommonDtls.AGEYEARS}";
	   document.forms[0].action=url1;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit();
	}
function fn_preauthDetails1()
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 url = 'preauthDetails.do?actionFlag=checkClinicalNotes&caseId=${caseId}&callType=Ajax';
	 xmlhttp.onreadystatechange=function()
	{
	    if(xmlhttp.readyState==4)
	    {	
	    	
	    	 var resultArray=xmlhttp.responseText;
		        var resultArray = resultArray.split("*");
		        if(resultArray[0]=="SessionExpired"){
		    		//alert("Session has been expired");
		    		//parent.sessionExpireyClose();
		    		 var fr = partial(parent.sessionExpireyClose);
		    		 jqueryInfoMsg('Session details',"Session has been expired",fr);
		    		}
		    		else
		    		{
		        if(resultArray[0]!=null)
		        {
		        	 if('${viewType}' != null && '${viewType}' =='medco' && parent.caseApprovalFlag =='Y')
		        		 {
		        		 if(resultArray[0] =='success')
			        	   {
		        			 var url1="preauthDetails.do?actionFlag=preauthDetailsEhf&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseApprovalFlag="+parent.caseApprovalFlag+"&patientAge=${patCommonDtls.AGEYEARS}";  
		        			 document.forms[0].action=url1;   
		  		    	     document.forms[0].target="bottomFrame";
		  		    	     document.forms[0].submit();
			        	   }
		        		 else
		        			 {
		        			 var fr = partial(fn_navigateClinical);
		        			  jqueryAlertMsg('Mandatory check',resultArray[0],fr);
		        			 }
		        		 }
		        	 else
		        		 {
		        		 var url1="preauthDetails.do?actionFlag=preauthDetailsEhf&caseId=${caseId}&patientId=${patCommonDtls.PATID}&caseApprovalFlag="+parent.caseApprovalFlag+"&patientAge=${patCommonDtls.AGEYEARS}";
		        		   document.forms[0].action=url1;   
				    	   document.forms[0].target="bottomFrame";
				    	   document.forms[0].submit();
		        		 }
		          
		          	   
		        }
	    }// end of if
	    	
	    }}
	 
	 xmlhttp.open("Post",url,true);
	 xmlhttp.send(null);  	
}
function fn_navigateClinical(message)
{
	 parent.leftFrame.fn_highlight('clinicalNotes');
	 fn_getClinicData('PRE',message);
	}
function fn_UploadFile()
{
	var url ="patCommonDtls.htm?actionFlag=uploadFile&CaseId=${patCommonDtls.CASEID}&Status=${patCommonDtls.STATUS}&patId=${patCommonDtls.PATID}";
	 document.forms[0].action=url;   
	 document.forms[0].target="topFrame";
	 document.forms[0].submit();	   
	}
function fn_claim()//076
{
 //alert("CaseId="+'${patCommonDtls.CASEID}'+"&UserRole="+'${UserRole}'+"&CaseStat="+'${patCommonDtls.CASESTAT}'+"&payee="+'${PatientDtls.PreauthVO.payee}'+"&ProcessFlag="+'${PatientDtls.PreauthVO.processFlag}'+"&Status="+'${status}'+"&ErrCase="+'${ErrCase}'+"&TdsPmtType="+'${TdsPmtType}'+"&ErrAprvFlag="+'${ErrAprv}'+"&ErrStatus="+'${PatientDtls.PreauthVO.errStatus}'+"&PendUpdFlag="+'${PendUpdFlag}'+"&StatusFlag="+'${StatusFlag}'+"&DeductorType="+'${PatientDtls.PreauthVO.deductorType}'+"&RationCard="+'${ratCard}'+"&Phase="+'${phase}'+"&PhaseRenewal="+'${PatientDtls.PreauthVO.renewal}'+"&hospType="+'${PatientDtls.PreauthVO.hospType}'+"&RestrictFlag="+'${RestrictFlag}');

var url="ClaimsFlow.do?actionFlag=Claims&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}&caseApprovalFlag="+parent.caseApprovalFlag;;	 
	// var url="ClaimsFlow.do?actionFlag=Claims&CaseId=1329543";
		 //&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}";
	   document.forms[0].action=url;   
	   document.forms[0].target="bottomFrame";
	   document.forms[0].submit(); 
		
		//document.getElementById('dataFrame').src ="ClaimsFlow.do?actionFlag=Claims&CaseId=${patCommonDtls.CASEID}&UserRole=${UserRole}&CaseStat=${patCommonDtls.CASESTAT}&payee=${PatientDtls.PreauthVO.payee}&ProcessFlag=${PatientDtls.PreauthVO.processFlag}&Status=${status}&ErrCase=${ErrCase}&TdsPmtType=${TdsPmtType}&ErrAprvFlag=${ErrAprv}&ErrStatus=${PatientDtls.PreauthVO.errStatus}&PendUpdFlag=${PendUpdFlag}&StatusFlag=${StatusFlag}&DeductorType=${PatientDtls.PreauthVO.deductorType}&RationCard=${ratCard}&Phase=${phase}&PhaseRenewal=${PatientDtls.PreauthVO.renewal}&hospType=${PatientDtls.PreauthVO.hospType}&RestrictFlag=${RestrictFlag}";
}
function showCMS()
{
	var caseScheme="${caseScheme}";
	
   var url="https://ehf.telangana.gov.in/CMS/login.htm?flag=showChangeMgmt&caseId=${patCommonDtls.CASEID}&caseNo=${patCommonDtls.CASENO}&caseScheme="+caseScheme+"&nabhFlag=Y";
   //var url="http://ehf.telangana.gov.in/CMS/login.htm?flag=showChangeMgmt&caseId=${patCommonDtls.CASEID}&caseNo=${patCommonDtls.CASENO}&caseScheme="+caseScheme;
	   //var url="http://172.25.147.51:8080/CMS/changeMgmtAction.do?flag=showChangeMgmt&caseId=${patCommonDtls.CASEID}&caseNo=${patCommonDtls.CASENO}&caseScheme="+caseScheme;
   var child= window.open( url,'ChangeManagement','width=800, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,fullscreen=YES');
   if (window.focus) {child.focus();
	}
  
}
function fn_removeLoadingImage()  
{
	document.getElementById('processImagetable').style.display="none";  
}
function fn_onloads(){
	var s$ = jQuery.noConflict();
	//deciduos teeth retriving
// 		var decDenSelArr=[];
		var carries='${PatientOpList.carriesdecidous}';
		if(carries!=null && carries!="")
			{
// 			decDenSelArr.push("CH87");
// 			document.forms[0].deciduousDent.value="CH87";
// 			document.getElementById("cariesDecdious").style.display="";
		var carriesdeciocus=carries.split('~');
		for(var i=0 ; i<carriesdeciocus.length;i++)
		{
			s$('input[type=checkbox][value='+carriesdeciocus[i]+']').prop('checked',true);
		}
			}
		
		var grosslydecade='${PatientOpList.grosslydecadedecidous}';
		if(grosslydecade!=null && grosslydecade!="")
			{
// 			decDenSelArr.push("CH88");
// 			document.forms[0].deciduousDent.value="CH88";
// 			document.getElementById("grosslyDecdious").style.display="";
		var grosslydecadedecidous=grosslydecade.split('~');
		for(var i=0 ; i<grosslydecadedecidous.length;i++)
		{
			s$('input[type=checkbox][value='+grosslydecadedecidous[i]+']').prop('checked',true);
		}
			}
		
		var mobiled='${PatientOpList.mobiledecidous}';
		if(mobiled!=null && mobiled!="")
			{
// 			decDenSelArr.push("CH89");
// 			document.forms[0].deciduousDent.value="CH89";
// 			document.getElementById("mobileDecdious").style.display="";
		var mobiledecidous=mobiled.split('~');
		for(var i=0 ; i<mobiledecidous.length;i++)
		{
			s$('input[type=checkbox][value='+mobiledecidous[i]+']').prop('checked',true);
		}
			}
		
		var missingdes='${PatientOpList.missingdecidous}';
		if(missingdes!=null && missingdes!="")
			{
// 			decDenSelArr.push("CH90");
// 			document.forms[0].deciduousDent.value="CH90";
// 			document.getElementById("missingDecdious").style.display="";
		var missingdecidous=missingdes.split('~');
		for(var i=0 ; i<missingdecidous.length;i++)
		{
			s$('input[type=checkbox][value='+missingdecidous[i]+']').prop('checked',true);
		}
			}		
		// permanent  dentattion retreving		
// 		var perDenSelArr=[];
		var carriesper='${PatientOpList.carriespermanent}';
		if(carriesper!=null && carriesper!="")
			{
// 			perDenSelArr.push("CH96");
// 			document.forms[0].permanentDent.value="CH96";
// 			document.forms[0].permanentDent.value.selected=true;
// 			document.getElementById("cariesDiv").style.display="";
		var carriespermanent=carriesper.split('~');
		for(var i=0 ; i<carriespermanent.length;i++)
		{
			s$('input[type=checkbox][value='+carriespermanent[i]+']').prop('checked',true);
		}
			}
		
		var rootstumpper='${PatientOpList.rootstumppermannet}';
		if(rootstumpper!=null && rootstumpper!="")
			{
// 			perDenSelArr.push("CH92");
// 			document.forms[0].permanentDent.value="CH92";
// 			document.getElementById("rootDiv").style.display="";
		var rootstumppermannet=rootstumpper.split('~');
		for(var i=0 ; i<rootstumppermannet.length;i++)
		{
			s$('input[type=checkbox][value='+rootstumppermannet[i]+']').prop('checked',true);
		}
			}
		
		var mobilityper='${PatientOpList.mobilitypermanent}';
		if(mobilityper!=null && mobilityper!="")
			{
// 			perDenSelArr.push("CH93");
// 			document.forms[0].permanentDent.value="CH93";
// 			document.getElementById("mobilityDiv").style.display="";
		var mobilitypermanent=mobilityper.split('~');
		for(var i=0 ; i<mobilitypermanent.length;i++)
		{
			s$('input[type=checkbox][value='+mobilitypermanent[i]+']').prop('checked',true);
		}
			}
		
		var attritionper='${PatientOpList.attritionpermanent}';
		if(attritionper!=null && attritionper!="")
			{
// 			perDenSelArr.push("CH94");
// 			document.forms[0].permanentDent.value="CH94";
// 			document.getElementById("attritionDiv").style.display="";
		var attritionpermanent=attritionper.split('~');
		for(var i=0 ; i<attritionpermanent.length;i++)
		{
			s$('input[type=checkbox][value='+attritionpermanent[i]+']').prop('checked',true);
		}
			}
		
		var missingper='${PatientOpList.missingpermanent}';
		if(missingper!=null && missingper!="")
			{
// 			perDenSelArr.push("CH95");
// 			document.forms[0].permanentDent.value="CH95";
// 			document.getElementById("missingDiv").style.display="";
		var missingpermanent=missingper.split('~');
		for(var i=0 ; i<missingpermanent.length;i++)
		{
			s$('input[type=checkbox][value='+missingpermanent[i]+']').prop('checked',true);
		}
			}
		
		 //permanent other dentation 
		var otherper='${PatientOpList.otherpermanent}';
		if(otherper!=null && otherper!="" && otherper!="-1~")
			{
// 			perDenSelArr.push("CH91");
			var otherpermanent=otherper.split('~');
			if(otherpermanent[0]!=null)
				{
// 				document.forms[0].permanentDent.value="CH91";
// 			document.getElementById("otherDiv").style.display="";
// 			document.getElementById("otherPermTextDiv").style.display="";
			var otherVal="";
			if(otherpermanent[0]=="CH104"){
				otherVal="Non Vital";
			}
			if(otherpermanent[0]=="CH103"){
				otherVal="RCT Treated";
			}

			if(otherpermanent[0]=="CH102"){
				otherVal="Retained";
			}

			if(otherpermanent[0]=="CH105"){
				otherVal="Impacted";
			}
 			document.getElementById("otherPermntDent").textContent=otherVal;
 			document.getElementById("otherPermText").innerHTML=otherpermanent[1];
				}
				
			}
		
		var probingdepth='${PatientOpList.probingdepth}';
		if(probingdepth!=null && probingdepth!="")
			{
			var probingids=probingdepth.split('~');
			for(var i=0 ; i<probingids.length ; i++)
				{
				var probingdepthval=probingids[i].split('@');
				
				if(probingdepthval!=null && probingdepthval!="")
				document.getElementById(probingdepthval[0]).value=probingdepthval[1];
				}
			}
		
		
		s$('input[name=probeDepth]').prop('disabled','disabled');
		 s$('input[name=childCaries]').prop('disabled','disabled');
		 s$('input[name=childMissing]').prop('disabled','disabled');
		 s$('input[name=grosslyDecayed]').prop('disabled','disabled');
		 s$('input[name=childMobile]').prop('disabled','disabled');
		 s$('input[name=caries]').prop('disabled','disabled');
		 s$('input[name=decayed]').prop('disabled','disabled');
		 s$('input[name=mobile]').prop('disabled','disabled');
		 s$('input[name=attrition]').prop('disabled','disabled');
		 s$('input[name=missing]').prop('disabled','disabled');
		
		
}



</script>
<style type="text/css">
#imageID {
top: 151px;
}
</style>
</head>
<body onload="javascript: fn_onloads();">
<form name="AdmnNotes" method=post enctype="multipart/form-data" modelAttribute="uploadItem" id="form1">
<table border="0" width="100%" >
<tr class="tbheader">
<td id="topSlide" width="5%">
<img id="menuImage" src="images/rightLeftArrow.jpg" title="Maximize/Minimize" style=cursor:hand; width="25" height="25" alt="Hide Menu" align="top" onclick="javascript:fn_maxmizeRight()" ></img>
</td>
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Dental Patient Details</strong></td>
<td id="menuSlide" width="5%">  
<img id="topImage" src="images/back.jpg" width="30" height="20" style=cursor:hand; title="Back" alt="Back" align="top" onclick="javascript:fn_maxmizeTop()" ></img>
</td>
</tr></table>
<!-- Patient Details  -->
<table width="100%" onload="javascript:fn_getIP();fn_removeLoadingImage();">
<tr><td width="100%" valign="top">

 <table width="83%" border="0" align="left" cellpadding="0" cellspacing="4">
 <tr><td width="10%" class="labelheading1 tbcellCss" ><b>Name  </b></td><td width="20%" class="tbcellBorder" colspan="3">${patCommonDtls.PATIENTNAME} ,    ${patCommonDtls.GENDER} ,    ${patCommonDtls.AGE}</td>
 	<c:if test="${patCommonDtls.telephonicId != null && patCommonDtls.telephonicId !='' }">
	 <td width="20%" class="labelheading1 tbcellCss"><b>Telephonic ID </b></td>
 <td class="tbcellBorder"><a href="javascript:viewTelephonicInfo('${patCommonDtls.telephonicId}')">${patCommonDtls.telephonicId}</a>  <!--<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>--> </td>
 </c:if>
 <c:if test="${patCommonDtls.telephonicId == null || patCommonDtls.telephonicId =='' }">
  <c:if test="${hideBackButton != 'Y'}">
				<td colspan="1">	 <!--  <button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>--></td>
	</c:if>		
</c:if>

 </tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Card No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CARDNO}</td><td width="10%" rowspan="1" class="labelheading1 tbcellCss"><b>Case Status </b></td>
 <td width="34%" rowspan="1" class="tbcellBorder"> <b>
 <c:if test="${PatientOpList.patientType=='IP'}">${patCommonDtls.STATUS}</c:if>
	<c:if test="${PatientOpList.patientType=='DOP' && patCommonDtls.STATUS=='IP case Registered'}">DOP Case Registered</c:if>
	<c:if test="${PatientOpList.patientType=='DOP' && patCommonDtls.STATUS!='IP case Registered'}">${patCommonDtls.STATUS}</c:if>
 </b></td>
 <td width="8%" class="labelheading1 tbcellCss"><b>District</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.DISTRICT}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Case No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CASENO}</td><td width="10%" class="labelheading1 tbcellCss"><b>Slab Type </b></td><td width="34%" class="tbcellBorder"> ${patCommonDtls.slabType}</td><td width="8%" class="labelheading1 tbcellCss"><b>Mandal</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.MANDAL}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>IP No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.IPNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>NWH Name </b></td>
 
 <c:if test="${checkNABH eq 'Y'}">
 <td width="34%" class="tbcellBorder" style="color: blue;" title="NABH Hospital"><b>${patCommonDtls.HOSPNAME}</b></td>
 </c:if>
 <c:if test="${checkNABH eq 'N'}">
<td width="34%" class="tbcellBorder">${patCommonDtls.HOSPNAME}</td>
 </c:if>
 <td width="8%" class="labelheading1 tbcellCss"><b>Village</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.VILLAGE}</td></tr>
 <tr><td width="10%" class="labelheading1 tbcellCss"><b>Claim No  </b></td><td width="20%" class="tbcellBorder">${patCommonDtls.CLAIMNO}</td><td width="10%" class="labelheading1 tbcellCss"><b>IP Reg Date </b></td><td width="34%" class="tbcellBorder">${patCommonDtls.date}</td><td width="8%" class="labelheading1 tbcellCss"><b>Contact No</b></td><td width="15%" class="tbcellBorder">${patCommonDtls.CONTACT}</td></tr>
 <c:if test="${PatientOpList.relationName ne null }"><c:if test="${PatientOpList.relationName eq 'New Born Baby'}"><tr><td width="10%" class="labelheading1 tbcellCss"><b>Patient Type</b></td><td width="20%" class="tbcellBorder"><b>${PatientOpList.relationName}</b></td></tr></c:if></c:if>
 <!-- <tr><td class="labelheading1 tbcellCss"><b>Case Status &nbsp;:</b></td><td colspan="2" class="tbcellBorder"><b>${patCommonDtls.STATUS}</b></td>
 <td></td><td></td><td colspan="1" align="center">
					<c:if test="${hideBackButton != 'Y'}">
					<button class="but"   type="button" id="Back" value='Back' onclick = 'CasesView()' >Back</button>
					</c:if>
					&nbsp;
				</td></tr>-->
 
  
 </table>
 <fieldset style="border:0;">
 <table width="17%" border="0" align="center">
	<tr height="20">
		<td>
			<c:if test="${fromCMS ne 'Y'}">
				<button class="but" style="font-size:12px; width:110px"  type="button" id="raiseCms" value="raiseCms" onclick ="javascript:showCMS();" >
					RAISE CMS/Grievance
				</button>
			</c:if>
			<c:if test="${fromCMS eq 'Y'}">
				&nbsp;
			</c:if>
		</td>
	</tr>
 <tr>
 <td align="center" width="100%" >
 <c:choose>
 <c:when test="${patOnBedPic != null && patOnBedPic != 'N'}" >
	<img src="common/showDocument.jsp" width="110" height="90" alt="NO DATA" align="center" id="patImage" 
  onmouseover="javascript:resizePatImage('patImage','140','110')" onmouseout="resizePatImage('patImage','110','90')" />
 </c:when>
  <c:otherwise>
 
<img src="images/photonot.gif" width="110" height="90" alt="NO DATA" align="center" 
/>
  <%-- <form:input  path="fileData" type="file" cssStyle="width:120px" id="Attachment" size='2'  />
  <button class="but"   type="button" onclick="javascript:Validate('Attachment')" value="Upload" > Uplaod </button> --%>   
  
  </c:otherwise>
 </c:choose>
 </td></tr>
 <tr><td>

 </td></tr>
 </table>
 </fieldset>
 </td></tr>
</table>
<!-- Patient Details  -->

<table border="0" width="100%" >
<tr class="tbheader">
<td  colspan="6">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong>Registration Details</strong></td>
</tr></table>
<table border="0" width="100%" >
<tr>
<!--<td  class="labelheading1 tbcellCss"><b>Card Issue Date</b></td>
<td  align="left" class="tbcellBorder">${PatientOpList.cardIssueDt}</td>-->
<td  class="labelheading1 tbcellCss"><b>Occupation</b></td>
<td  align="left" class="tbcellBorder">${PatientOpList.occName}</td>
<td   class="labelheading1 tbcellCss"><b>Relationship With family head</b></td>
<td  align="left" class="tbcellBorder">${PatientOpList.relationName}</td>
</tr>
<tr>
<td colspan="2" class="tbheader"><center><b>Card Address</b></center></td>
<td colspan="2" class="tbheader"><center><b>Communication Address</b></center></td>
</tr>
<tr>
<td  width="20%" class="labelheading1 tbcellCss">
	<b>House No</b>
</td>
<td width="30%" class="tbcellBorder">
	${PatientOpList.cardHNo}
</td>

<td width="20%"  class="labelheading1 tbcellCss">
	<b>House No</b>
</td>
<td width="30%" class="tbcellBorder">
	${PatientOpList.houseNo}
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss">
	<b>Street</b>
</td>
<td  class="tbcellBorder">
	${PatientOpList.cardStreet}
</td>
<td   class="labelheading1 tbcellCss">
	<b>Street</b>
</td>
<td  class="tbcellBorder">
		${PatientOpList.street}
</td>
</tr>
<tr>
<!-- <td>
	<b>Hamlet:</b>
</td>
<td>&nbsp;</td> -->
<td  class="labelheading1 tbcellCss">
	<b>Village</b>
</td>
<td class="tbcellBorder">
		${PatientOpList.cardVillage}
</td> 
<!-- <td>
	<b>Hamlet:</b>
</td>
<td>&nbsp;</td> -->
<td  class="labelheading1 tbcellCss">
	<b>Village</b>
</td>
<td class="tbcellBorder">
	${PatientOpList.village}
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss">
	<b>Mandal</b>
</td>
<td  class="tbcellBorder">
	${PatientOpList.cardMandal}
</td>
 <td  class="labelheading1 tbcellCss">
	<b>Mandal</b>
</td>
<td class="tbcellBorder">
${PatientOpList.mandal}
</td>
</tr>
<tr>
<td  class="labelheading1 tbcellCss">
	<b>District</b>
</td>
<td class="tbcellBorder">
		${PatientOpList.cardDistrict}
</td>

<td  class="labelheading1 tbcellCss">
	<b>District</b>
</td>
<td class="tbcellBorder">
		${PatientOpList.district}
</td>
</tr>
<tr><td class="tbheader" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Medical Details</b></td></tr>
<tr>
<td class="labelheading1 tbcellCss"><b>Complaint Type</b></td>
<td  class="tbcellBorder">${PatientOpList.complaintType}</td>
<td class="labelheading1 tbcellCss"><b>Patient Complaint</b></td>
<td  class="tbcellBorder">${PatientOpList.patComplaint}</td></tr>
<tr>
<td class="labelheading1 tbcellCss"><b>History of Present Illness</b></td>
<td  class="tbcellBorder">${PatientOpList.presentIllness}</td>
<c:if test="${telephonicRemks != null && telephonicRemks !='' }" >
<td class="labelheading1 tbcellCss"><b>Telephonic Intimation Remarks</b></td>
<td  class="tbcellBorder">${telephonicRemks}</td>
</c:if>
</tr>
</table>  
  <!-- IP Details -->

<!-- Personal history -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Personal History</b></td></tr>
</table>

<table border="0" width="100%" >
<c:set  value="${PatientOpList.lstPerHis}" var="perHisList"></c:set>

<c:set var="loopCount" value="0" /> 
<c:forEach  items="${PatientOpList.lstPersonalHistory}" varStatus="loop">	
<c:set var="loopCount" value="${loopCount + 1}" />

<tr>

<c:if test="${(loopCount % 2) eq 1}">
	<tr>
	</c:if>	
	<c:if test="${PatientOpList.lstPersonalHistory[loop.index].VALUE != 'Appetite' && PatientOpList.lstPersonalHistory[loop.index].VALUE != 'Bowels'}">
		<td width="20%" class="labelheading1 tbcellCss">
<c:set value="${PatientOpList.lstPersonalHistory[loop.index].lstSub}" var="sample"></c:set>
<b>&nbsp;&nbsp;${PatientOpList.lstPersonalHistory[loop.index].VALUE} </b>
</td></c:if>
<c:forEach items="${sample}" varStatus="loop1">
<c:if test="${PatientOpList.lstPersonalHistory[loop.index].VALUE != 'Appetite'  && PatientOpList.lstPersonalHistory[loop.index].VALUE != 'Bowels'}">
<td  class="tbcellBorder">
<c:set value="${sample[loop1.index].ID}" var="subLstId"></c:set>
<c:if test="${subLstId!='PR6.2' && subLstId!='PR5.2'}">
&nbsp;&nbsp;${sample[loop1.index].VALUE}&nbsp;
</c:if>
<c:if test="${fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId=='PR6.2'}">
No
</c:if>
<c:if test="${subLstId=='PR5.2'}">
No
</c:if>
  <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled" checked="checked"/>  &nbsp;
<c:if test="${subLstId=='PR6.1'}">
<c:set var="test" value="${PatientOpList.test}" scope="page" />
<td  class="tbcellBorder"> &nbsp;&nbsp;No <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;</td>

      <table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Alcohol  </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="alcohol" id="alcohol" value="Regular" disabled="disabled"/> &nbsp;Regular&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="alcohol" id="alcohol" value="Occasional" disabled="disabled"/> &nbsp;Occasional&nbsp;</td>
	     <td class="tbcellBorder"> <input type="radio" name="alcohol" id="alcohol" value="Teetotaler" disabled="disabled"/> &nbsp;Teetotaler&nbsp; </td></tr>
	    	<tr><td class="labelheading1 tbcellCss"> &nbsp;b.<b>Tobacco  </b></td><td class="tbcellBorder"><input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Snuff</td>
	      <td class="tbcellBorder"><input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Chewable&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" disabled="disabled"/> &nbsp;Smoking&nbsp;
	      <span id="smokingTd" style="display:none" >
	      &nbsp;Pack  <input  class="tbcellBorder" type="text" name="packNo" id="packNo" style="width:40px" maxlength="3" title="Smoking Pack No" disabled="disabled"/>
	       &nbsp;Years  <input class="tbcellBorder" type="text" name="smokeYears" id="smokeYears" style="width:40px" maxlength="3" title="Smoking Years" disabled="disabled"/>
	     </span>
	     </td>
	      </tr>
<!-- 	      <tr><td class="labelheading1 tbcellCss"> &nbsp;c.<b>Drug Use  </b></td><td class="tbcellBorder"><input type="radio" name="drugUse" id="drugUse" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td> -->
<!-- 	      <td class="tbcellBorder"><input type="radio" name="drugUse" id="drugUse" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr> -->
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;c.<b>Betel nut  </b></td><td class="tbcellBorder"><input type="radio" name="betelNut" id="betelNut" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder"> <input type="radio" name="betelNut" id="betelNut" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;d.<b>Pan Chewing  </b></td><td class="tbcellBorder"><input type="radio" name="PanChewing" id="PanChewing" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="PanChewing" id="PanChewing" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	       <tr><td class="labelheading1 tbcellCss"> &nbsp;e.<b>Gutka  </b></td><td class="tbcellBorder"><input type="radio" name="Gutka" id="Gutka" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="Gutka" id="Gutka" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;f.<b>Finger Sucking  </b></td><td class="tbcellBorder"><input type="radio" name="FingerSucking" id="FingerSucking" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="FingerSucking" id="FingerSucking" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;g.<b>Nail Biting  </b></td><td class="tbcellBorder"><input type="radio" name="NailBiting" id="NailBiting" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="NailBiting" id="NailBiting" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;h.<b>Tongue Biting  </b></td><td class="tbcellBorder"><input type="radio" name="TongueBiting" id="TongueBiting" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="TongueBiting" id="TongueBiting" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;i.<b>Mouth Breathing  </b></td><td class="tbcellBorder"><input type="radio" name="MouthBreathing" id="MouthBreathing" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="MouthBreathing" id="MouthBreathing" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      <tr><td class="labelheading1 tbcellCss"> &nbsp;j.<b>Bruxism  </b></td><td class="tbcellBorder"><input type="radio" name="Bruxism" id="Bruxism" value="Yes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	      <td class="tbcellBorder"><input type="radio" name="Bruxism" id="Bruxism" value="No" disabled="disabled"/> &nbsp;No&nbsp;</td></tr>
	      </table>      
</c:if>
<c:if test="${subLstId=='PR5.1'}">
<c:set var="testKnown" value="${PatientOpList.testKnown}" scope="page" />
<td  class="tbcellBorder"> &nbsp;&nbsp; No
<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;</td>

      <table width="100%" border="0" align="center"><tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Allergic to Medicine </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="AllMed" id="AllMed" value="AllMedYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="AllMed" id="AllMed" value="AllMedNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder">
	    	<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="4000"  title="Remark" style="width:70%" disabled="disabled"/></div>
			</td></tr>
			<tr><td class="labelheading1 tbcellCss" nowrap="nowrap" width="20%">
	     &nbsp;a.<b>Allergic to Substance other than medicine </b></td><td class="tbcellBorder" nowrap="nowrap"><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" disabled="disabled"/> &nbsp;Yes&nbsp;</td>
	     <td class="tbcellBorder" > <input type="radio" name="AllSub" id="AllSub" value="AllSubNo" disabled="disabled"/> &nbsp;No&nbsp;</td><td  class="tbcellBorder">
	    	<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="4000" title="Remark" style="width:70%" disabled="disabled"/></div>
			</td></tr></table>			
			</c:if>
</c:if>
<c:if test="${!fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId!='PR6.2'}">
<!-- <input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp; -->
<c:if test="${subLstId=='PR6.1'}">
<c:if test="${!fn:contains(perHisList,'PR6.2') }" >
No<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;
</c:if>
</c:if>
</c:if>
</c:if>
<c:if test="${!fn:contains(perHisList,sample[loop1.index].ID) }" >
<c:if test="${subLstId!='PR5.2'}">
<!--<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp; -->
<c:if test="${subLstId=='PR5.1'}">
<c:if test="${!fn:contains(perHisList,'PR5.2') }" >
No<input type="checkbox" value="${sample[loop1.index].VALUE}" disabled="disabled"/>&nbsp;
</c:if>
</c:if>
</c:if>
</c:if>
</td></c:if>
</c:forEach>
</tr>
</c:forEach>
<!--  <tr>
<c:forEach  items="${PatientOpList.lstPersonalHistory}" varStatus="loop">	
<c:set value="${PatientOpList.lstPersonalHistory[loop.index].VALUE}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${sample}" delims="^" varStatus="status" begin="0">
<c:forTokens var="tokenName1" items="${tokenName}" delims="~" varStatus="status" begin="0" >
&nbsp;&nbsp;<c:out value="${tokenName1}"/>
&nbsp;&nbsp;<input type="checkbox" value="${tokenName1}"/>
</c:forTokens>
</c:forTokens>
<br>
</c:forEach>
</tr>-->
</table>

<!-- Family History -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Family History</b></td></tr>
</table> 
<table border="0" width="100%" align=center >
<tr><td align="center">
<c:set var="loopCount2" value="0" />
<c:forEach  items="${familyHistoryList}" varStatus="loop">								
<c:set value="${familyHistoryList[loop.index].ID}" var="sample"></c:set>	
<c:forTokens var="tokenName" items="${PatientOpList.familyHis}" delims="~" varStatus="status" begin="0">
<c:choose>
<c:when test="${tokenName == sample}">	
<c:set var="loopCount2" value="${loopCount2 + 1}" /> 
<c:if test="${(loopCount2 % 4) eq 1}">
	<tr>
	</c:if>		
<td class="tbcellBorder" >
<c:out value="${familyHistoryList[loop.index].VALUE}"/>
<c:if test="${tokenName == 'FH11' }">
${PatientOpList.familyHistoryOthr}
</c:if>
</c:when>
</c:choose>  
</c:forTokens>     	
</c:forEach>
</td></tr>
<tr><td align="center">
<c:if test="${fn:length(PatientOpList.familyHis) eq 0 }">
Family history not found
</c:if>
</td></tr>
</table>

<!-- General Examination Findings -->
<table width="100%">
<tr><td class="tbheader">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>General Examination Findings</b></td></tr>
</table>
 
 <table border="0" width="100%" align=center>

<tr>
 <td class="labelheading1 tbcellCss" width="20%"><b>Temperature</b> </td>
 <td  class="tbcellBorder" width="20%">${PatientOpList.temperature}</td> 

 <td class="labelheading1 tbcellCss" width="20%"><b>Pulse Rate per Minute</b></td>
 <td  class="tbcellBorder" width="20%">${PatientOpList.pulseRate}</td> 
 </tr>
 <tr>
 <td class="labelheading1 tbcellCss" width="20%"><b>Respiration Rate</b></td>
 <td  class="tbcellBorder" width="20%">${PatientOpList.respirationRate}</td>

 <td class="labelheading1 tbcellCss" width="20%"><b>BP</b> </td>
 <td  class="tbcellBorder" width="20%">${PatientOpList.bpLmt}</td>
  </tr>

 <tr><td width="20%"class="labelheading1 tbcellCss print_cell"><strong>vii) Drug history</strong> </td>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.drugHstVal}
 		     
		      </td></tr>	
		      
		      
		      <tr><td rowspan="2" width="20%"class="labelheading1 tbcellCss print_cell"><strong>vii) Medical history</strong> </td>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Medical history:</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.medHistVal}

		      </td></tr>	 
		      
		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Medical history Other</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.medicalHistoryOthr}
 		    
		      </td></tr>
 
 
 <!-- Systematic Examination Findings-->
<tr><td class="tbheader" colspan="4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b>Systematic Examination Findings</b></td></tr>

 <tr><td colspan="4">
<table width="100%" border="0">
 <tr>  
      	<td width="5%" class="labelheading1 tbcellCss"><b>S.No</b></td>        
        <td width="35%" class="labelheading1 tbcellCss"><b>Main Symptom Name</b></td>   
       	<td width="30%" class="labelheading1 tbcellCss"><b>Sub Symptom Name</b></td>
        <td width="30%" class="labelheading1 tbcellCss"><b>Symptom Name</b></td>
 </tr>
 <%int i=1; %>
<c:forEach items="${symptomsList}" var="element"> 
  <tr>
    <td width="5%" class="tbcellBorder"><%=i++ %></td>
    <td width="35%" class="tbcellBorder">${element.ID}</td>
    <td width="30%" class="tbcellBorder">${element.SUBID}</td>
    <td width="30%" class="tbcellBorder">${element.VALUE}</td>
  </tr>
</c:forEach>  
</table>
</td></tr>

<tr>
<td class="labelheading1 tbcellCss" ><b>Investigations</b></td>
<td  class="tbcellBorder">${PatientOpList.investRemarks}</td>
</tr>

<!--Added For dental  case sheet-->


				<tr><td colspan="4" class="tbcellCss print_cell"><strong><span class="labelheading1">viii) Extra Oral Examinations</span></strong>	
			
			<table width="100%">
				<tr>
<!-- 				<td rowspan="4" width="30%"class="labelheading1 tbcellCss print_cell"><strong>ix) Extra Oral Examinations</strong> </td> -->
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Regional Lymphadenopathy</strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.regionalLymphadenopathyDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.regionalLymphadenopathyDtrsTxt}</td>
		      </tr>
		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Jaws</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.jawsDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.jawsDtrsTxt}</td></tr>	
		      		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>TMJ</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.tmjDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.tmjDtrsTxt}</td></tr>	
						      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Face</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.faceDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.faceDtrsTxt}</td></tr>
		      </table></td></tr>

<tr><td colspan="4" class="tbcellCss print_cell labelheading1"><strong>ix) Intra Oral Examinations</strong></td></tr>
<tr>
<td colspan="4" class="tbcellCss print_cell"><strong><span class="labelheading1">Soft Tissue Examinations</span></strong>
<table width="100%">
<tr>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral0) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Hard Palate</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral0}</td>
<%-- </c:if> --%>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral1) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Soft Palate</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral1}</td>
<%-- </c:if> --%>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral2) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Floor of the mouth</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral2}</td>
<%-- </c:if> --%>
</tr>
<tr>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral3) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Tongue</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral3}</td>
<%-- </c:if> --%>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral4) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Frenal Attachment</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral4}</td>
<%-- </c:if> --%>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral5) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Buccal Mucosa</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral5}</td>
<%-- </c:if> --%>
</tr>
<tr>
<%-- <c:if test="${fn:length(PatientOpList.dntsublistoral6) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Gingiva</strong></td>
<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral6}</td>
<%-- </c:if> --%>
<%-- <c:if test="${fn:length(PatientOpList.swSite) ne 0 or fn:length(PatientOpList.swSize) ne 0 or fn:length(PatientOpList.swExtension) ne 0 or fn:length(PatientOpList.swColour) ne 0 or fn:length(PatientOpList.swConsistency) ne 0 or fn:length(PatientOpList.swTenderness) ne 0 or fn:length(PatientOpList.swBorders) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Swelling</strong></td>
<td class="tbcellBorder print_cell" width="15%">
<span><b>Site:</b>&nbsp;${PatientOpList.swSite}</span><br>
<span><b>Size:</b>&nbsp;${PatientOpList.swSize}</span><br>
<span><b>Extension:</b>&nbsp;${PatientOpList.swExtension}</span><br>
<span><b>Colour:</b>&nbsp;${PatientOpList.swColour}</span><br>
<span><b>Consistency</b>:&nbsp;${PatientOpList.swConsistency}</span><br>
<span><b>Tenderness:</b>&nbsp;${PatientOpList.swTenderness}</span><br>
<span><b>Borders:</b>&nbsp;${PatientOpList.swBorders}</span>
</td>
<%-- </c:if> --%>
<%-- <c:if test="${fn:length(PatientOpList.psSite) ne 0 or fn:length(PatientOpList.psDischarge) ne 0}"> --%>
<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Pus/Discharge</strong></td>
<td class="tbcellBorder print_cell" width="15%">
<span><b>Site:</b>&nbsp;${PatientOpList.psSite}</span><br>
<span><b>Discharge:</b>&nbsp;${PatientOpList.psDischarge}</span>
</td>
<%-- </c:if> --%>
</tr>
</table></td>
</tr>
</table>

<table width="100%">
<tr><td colspan="4" class="labelheading1 tbcellCss print_cell"><strong>Hard Tissue examinations</strong></td></tr>
	<c:if test="${fn:length(PatientOpList.carriesdecidous) ne 0 or fn:length(PatientOpList.missingdecidous) ne 0 or fn:length(PatientOpList.grosslydecadedecidous) ne 0 or fn:length(PatientOpList.mobiledecidous) ne 0}">
		      <tr><td width="100%" class="labelheading1 tbcellCss print_cell"><strong>Decidious Dentation</strong></td></tr>
		      <tr><td width="100%" class="tbcellBorder print_cell" colspan="4">
		      
		      <table width="100%" id="decidiousBlock">
		      <c:if test="${fn:length(PatientOpList.carriesdecidous) ne 0}">
		      	<tr id="cariesDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Caries</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="childCaries1" name="childCaries" value="c1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries2" value="c2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries3" name="childCaries" value="c3" > C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries4" value="c4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries5" name="childCaries" value="c5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries6" value="c6" > A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries7" value="c7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries8" name="childCaries" value="c8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries9" value="c9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries10" name="childCaries" value="c10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries11" name="childCaries" value="c11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries12" value="c12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries13" name="childCaries" value="c13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries14" value="c14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries15" name="childCaries" value="c15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries16" value="c16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries17" value="c17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries18" name="childCaries" value="c18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries19" value="c19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries20" name="childCaries" value="c20"> E
				</div></td>
				</tr>
				</c:if>
				
				<c:if test="${fn:length(PatientOpList.missingdecidous) ne 0}">
				<tr id="missingDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Missing</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="missingTeeth1" name="childMissing" value="m1" > E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth2" value="m2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth3" name="childMissing" value="m3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth4" value="m4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth5" name="childMissing" value="m5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth6" value="m6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth7" value="m7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth8" name="childMissing" value="m8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth9" value="m9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth10" name="childMissing" value="m10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth11" name="childMissing" value="m11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth12" value="m12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth13" name="childMissing" value="m13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth14" value="m14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth15" name="childMissing" value="m15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth16" value="m16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth17" value="m17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth18" name="childMissing" value="m18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth19" value="m19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth20" name="childMissing" value="m20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(PatientOpList.grosslydecadedecidous) ne 0}">
				<tr id="grosslyDecdious"><td colspan="2" class="labelheading1 tbcellCss">Grossly Decayed</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="grosslyDecayed1" name="grosslyDecayed" value="g1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed2" value="g2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed3" name="grosslyDecayed" value="g3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed4" value="g4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed5" name="grosslyDecayed" value="g5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed6" value="g6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed7" value="g7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed8" name="grosslyDecayed" value="g8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed9" value="g9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed10" name="grosslyDecayed" value="g10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed11" name="grosslyDecayed" value="g11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed12" value="g12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed13" name="grosslyDecayed" value="g13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed14" value="g14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed15" name="grosslyDecayed" value="g15"> A
				 				| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed16" value="g16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed17" value="g17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed18" name="grosslyDecayed" value="g18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed19" value="g19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed20" name="grosslyDecayed" value="g20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(PatientOpList.mobiledecidous) ne 0}">
				<tr id="mobileDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Mobile</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss"  id="childMobile1"  name="childMobile" value="mm1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile2" value="mm2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile3" name="childMobile" value="mm3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile4" value="mm4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile5" name="childMobile" value="mm5"> A
				 				| 
				 <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile6" value="mm6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile7" value="mm7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile8" name="childMobile" value="mm8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile9" value="mm9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile10" name="childMobile" value="mm10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile11" name="childMobile" value="mm11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile12" value="mm12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile13" name="childMobile" value="mm13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile14" value="mm14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile15" name="childMobile" value="mm15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile16" value="mm16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile17" value="mm17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile18" name="childMobile" value="mm18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile19" value="mm19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile20" name="childMobile" value="mm20"> E
				</div></td>
				</tr>
				</c:if>
				
				 </table>
		      
		      </td></tr>
		      </c:if>
		      
		      <c:if test="${fn:length(PatientOpList.carriespermanent) ne 0 or fn:length(PatientOpList.rootstumppermannet) ne 0 or fn:length(PatientOpList.mobilitypermanent) ne 0 or fn:length(PatientOpList.attritionpermanent) ne 0 or fn:length(PatientOpList.missingpermanent) ne 0 or fn:length(PatientOpList.otherpermanent) gt 3}">
		      <tr><td width="100%" class="labelheading1 tbcellCss print_cell"><strong>Permanent Dentation</strong></td></tr>
		      <tr><td width="100%" class="tbcellBorder print_cell">
		      <table width="100%" id="permanentBlock" >
		<c:if test="${fn:length(PatientOpList.carriespermanent) ne 0}">
		<tr id="cariesDiv" ><td colspan="1" class="labelheading1 tbcellCss">Caries</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries1" name="caries" value="pc1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries2" name="caries" value="pc2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries3" name="caries" value="pc3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries4" name="caries" value="pc4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries5" name="caries" value="pc5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries6" name="caries" value="pc6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries7" name="caries" value="pc7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries8" name="caries" value="pc8"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries9" name="caries" value="pc9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries10" name="caries" value="pc10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries11" name="caries" value="pc11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries12" name="caries" value="pc12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries13" name="caries" value="pc13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries14" name="caries" value="pc14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries15" name="caries" value="pc15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries16" name="caries" value="pc16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries17" name="caries" value="pc17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries18" name="caries" value="pc18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries19" name="caries" value="pc19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries20" name="caries" value="pc20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries21" name="caries" value="pc21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries22" name="caries" value="pc22"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries23" name="caries" value="pc23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries24" name="caries" value="pc24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries25" name="caries" value="pc25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries26" name="caries" value="pc26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries27" name="caries" value="pc27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries28" name="caries" value="pc28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries29" name="caries" value="pc29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries30" name="caries" value="pc30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries31" name="caries" value="pc31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries32" name="caries" value="pc32">8
		</div></td></tr>
		</c:if>
		<c:if test="${fn:length(PatientOpList.rootstumppermannet) ne 0}">
		<tr id="rootDiv" ><td colspan="1" class="labelheading1 tbcellCss">Root stump <br>/ Grossly Decayed </td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed1" name="decayed" value="pr1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed2" name="decayed" value="pr2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed3" name="decayed" value="pr3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed4" name="decayed" value="pr4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed5" name="decayed" value="pr5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed6" name="decayed" value="pr6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed7" name="decayed" value="pr7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed8" name="decayed" value="pr8"> 1
		 				|
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed9" name="decayed" value="pr9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed10" name="decayed" value="pr10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed11" name="decayed" value="pr11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed12" name="decayed" value="pr12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed13" name="decayed" value="pr13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed14" name="decayed" value="pr14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed15" name="decayed" value="pr15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed16" name="decayed" value="pr16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed17" name="decayed" value="pr17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed18" name="decayed" value="pr18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed19" name="decayed" value="pr19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed20" name="decayed" value="pr20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed21" name="decayed" value="pr21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed22" name="decayed" value="pr22"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed23" name="decayed" value="pr23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed24" name="decayed" value="pr24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed25" name="decayed" value="pr25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed26" name="decayed" value="pr26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed27" name="decayed" value="pr27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed28" name="decayed" value="pr28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed29" name="decayed" value="pr29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed30" name="decayed" value="pr30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed31" name="decayed" value="pr31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed32" name="decayed" value="pr32">8
		</div></td>
		</tr>
		</c:if>
		<c:if test="${fn:length(PatientOpList.mobilitypermanent) ne 0}">
		<tr id="mobilityDiv" ><td colspan="1" class="labelheading1 tbcellCss">Mobility</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile1" name="mobile" value="pm1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile2" name="mobile" value="pm2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile3" name="mobile" value="pm3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile4" name="mobile" value="pm4"> 5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile5" name="mobile" value="pm5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile6" name="mobile" value="pm6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile7" name="mobile" value="pm7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile8" name="mobile" value="pm8"> 1 
						|
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile9" name="mobile" value="pm9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile10" name="mobile" value="pm10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile11" name="mobile" value="pm11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile12" name="mobile" value="pm12"> 4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile13" name="mobile" value="pm13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile14" name="mobile" value="pm14"> 6
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile15" name="mobile" value="pm15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile16" name="mobile" value="pm16"> 8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile17" name="mobile" value="pm17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile18" name="mobile" value="pm18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile19" name="mobile" value="pm19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile20" name="mobile" value="pm20"> 5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile21" name="mobile" value="pm21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile22" name="mobile" value="pm22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile23" name="mobile" value="pm23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile24" name="mobile" value="pm24"> 1
						 | 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile25" name="mobile" value="pm25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile26" name="mobile" value="pm26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile27" name="mobile" value="pm27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile28" name="mobile" value="pm28"> 4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile29" name="mobile" value="pm29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile30" name="mobile" value="pm30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile31" name="mobile" value="pm31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile32" name="mobile" value="pm32"> 8
		</div></td>
		</tr>
		</c:if>
		<c:if test="${fn:length(PatientOpList.attritionpermanent) ne 0}">
		<tr id="attritionDiv" ><td colspan="1" class="labelheading1 tbcellCss">Attrition <br>/ Abrasion </td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition1" name="attrition" value="pa1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition2" name="attrition" value="pa2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition3" name="attrition" value="pa3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition4" name="attrition" value="pa4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition5" name="attrition" value="pa5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition6" name="attrition" value="pa6"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition7" name="attrition" value="pa7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition8" name="attrition" value="pa8"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition9" name="attrition" value="pa9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition10" name="attrition" value="pa10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition11" name="attrition" value="pa11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition12" name="attrition" value="pa12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition13" name="attrition" value="pa13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition14" name="attrition" value="pa14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition15" name="attrition" value="pa15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition16" name="attrition" value="pa16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition17" name="attrition" value="pa17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition18" name="attrition" value="pa18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition19" name="attrition" value="pa19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition20" name="attrition" value="pa20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition21" name="attrition" value="pa21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition22" name="attrition" value="pa22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition23" name="attrition" value="pa23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition24" name="attrition" value="pa24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition25" name="attrition" value="pa25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition26" name="attrition" value="pa26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition27" name="attrition" value="pa27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition28" name="attrition" value="pa28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition29" name="attrition" value="pa29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition30" name="attrition" value="pa30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition31" name="attrition" value="pa31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition32" name="attrition" value="pa32">8
		</div></td>
		</tr> 
		</c:if>
		<c:if test="${fn:length(PatientOpList.missingpermanent) ne 0}">
		<tr id="missingDiv" ><td colspan="1" class="labelheading1 tbcellCss">Missing</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing1" name="missing" value="pmi1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing2" name="missing" value="pmi2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing3" name="missing" value="pmi3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing4" name="missing" value="pmi4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing5" name="missing" value="pmi5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing6" name="missing" value="pmi6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing7" name="missing" value="pmi7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing8" name="missing" value="pmi8"> 1
		 				| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing9" name="missing" value="pmi9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing10" name="missing" value="pmi10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing11" name="missing" value="pmi11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing12" name="missing" value="pmi12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing13" name="missing" value="pmi13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing14" name="missing" value="pmi14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing15" name="missing" value="pmi15"> 7  <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing16" name="missing" value="pmi16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing17" name="missing" value="pmi17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing18" name="missing" value="pmi18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing19" name="missing" value="pmi19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing20" name="missing" value="pmi20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing21" name="missing" value="pmi21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing22" name="missing" value="pmi22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing23" name="missing" value="pmi23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing24" name="missing" value="pmi24"> 1
		 				| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing25" name="missing" value="pmi25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing26" name="missing" value="pmi26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing27" name="missing" value="pmi27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing28" name="missing" value="pmi28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing29" name="missing" value="pmi29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing30" name="missing" value="pmi30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing31" name="missing" value="pmi31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing32" name="missing" value="pmi32">8
		</div></td>
		</tr>
		</c:if> 
		<c:if test="${fn:length(PatientOpList.otherpermanent) gt 3}">
		<tr id="otherDiv">
		<td class="labelheading1 tbcellCss" colspan="1" id="">Others-<span id="otherPermntDent"></span>
		</td>
		<td id="otherPermText" class="tbcellBorder print_cell" colspan="2" >
		</td>
		</tr>
		</c:if>
		</table></td></tr></c:if>
		<tr><td>
		   <table width="100%">
		<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>Previous Dental Treatment</strong> </td>
		    <td width="30%" class="tbcellBorder print_cell">${PatientOpList.previousDentalTreatment}</td></tr>	
		<c:if test="${fn:length(PatientOpList.occlusionTxt) ne 0}">
		<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>Occlusion</strong> </td>
			<td width="30%" class="tbcellBorder print_cell">${PatientOpList.occlusionTxt}</td>		
		    <td width="30%" class="tbcellBorder print_cell">${PatientOpList.occlusionTypeTxt}</td></tr></c:if>
		</table></td></tr>
		<c:if test="${fn:length(PatientOpList.probingdepth) ne 0}">
		<tr><td width="100%"class="labelheading1 tbcellCss print_cell"><strong>Clinical Probing</strong></td></tr>
		<tr><td colspan="4" class="tbcellBorder" align="center">
		<table border=1>
		<tr><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth0" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth6" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth11" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth1" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth7" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth12" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth2" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth8" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth13" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth3" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth9" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth14" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth4" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth10" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth15" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth5" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
		<tr><td style="text-align:center">8</td><td style="text-align:center">7</td><td style="text-align:center">6</td><td style="text-align:center">5</td><td style="text-align:center">4</td><td style="text-align:center">3</td><td style="text-align:center">2</td><td style="text-align:center">1</td><td style="text-align:center">1</td><td style="text-align:center">2</td>
		<td style="text-align:center">3</td><td style="text-align:center">4</td><td style="text-align:center">5</td><td style="text-align:center">6</td><td style="text-align:center">7</td><td style="text-align:center">8</td></tr>
		<tr><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth16" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth22" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth27" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth17" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth23" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth28" name="probeDepth" onchange=javascript:fn_checkprobingdepth(this.id,this.value);></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth18" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth24" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth29" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth19" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth25" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth30" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth20" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth26" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth31" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth21" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
		</table>

</td></tr></c:if>      
		      
</table>



<table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="20%"><b>Patient Diagnosed By</b></td>
<td  class="tbcellBorder">${PatientOpList.docType}</td>
<td class="labelheading1 tbcellCss" width="20%"><b>Doctor Name</b></td>
<td  class="tbcellBorder">${PatientOpList.docName}</td> 

</tr>
<tr>
	
<td class="labelheading1 tbcellCss" width="20%"><b>Patient Type</b></td>
<td  class="tbcellBorder">${PatientOpList.patientType}</td> 
	<td align="left" class="labelheading1 tbcellCss print_cell" width="20%">
		<b>Medico Legal case</b>
	</td>
	<td align="left" class="tbcellBorder print_cell" width="20%">
	<c:if test="${PatientOpList.legalCaseCheck eq 'Y'}">
		Yes
	</c:if>
	<c:if test="${PatientOpList.legalCaseCheck eq 'N'}">
		No					</c:if>
	</td>
	<c:if test="${PatientOpList.legalCaseCheck eq 'Y'}">
	
		<td align="left" class="labelheading1 tbcellCss print_cell" width="20%">
			<b>Case No</b>
		</td>
		<td align="left" class="tbcellBorder print_cell" width="20%">
			${PatientOpList.legalCaseNo}
		</td>
		
			
		<td align="left" class="labelheading1 tbcellCss print_cell" width="20%">
			<b>Police Station</b>
		</td>
		<td align="left" class="tbcellBorder print_cell" width="20%">
			${PatientOpList.policeStatName}
		</td>
		<td colspan="2">&nbsp;</td>
	</c:if>
</tr>
</table>

<script type="text/javascript">
var addition='${test}';
var additionKnown='${testKnown}';
if(document.getElementById("habitsTd") != null)
document.getElementById("habitsTd").style.display='block';
addition=addition.replace("PR6.1(","");
additionKnown=additionKnown.replace("PR5.1,","");
var additionList = addition.split(",");
var addKnownList = additionKnown.split(",");

if(addKnownList.length>0){
	for(var i = 0; i<addKnownList.length;i++)
    {	    
		var addtn = addKnownList[i].split("$");
		if(addtn[0]=='AllMed'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllMedYes'){
				
				document.forms[0].AllMed[0].checked='checked';
				document.getElementById("AllMedDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllMedRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllMedNo'){
				document.forms[0].AllMed[1].checked='checked';
		}
	   }
		if(addtn[0]=='AllSub'){
			var spitedY = addtn[1].split("(");	
			if(spitedY[0]=='AllSubYes'){
				
				document.forms[0].AllSub[0].checked='checked';
				document.getElementById("AllSubDiv").style.display='block';
				var valueY = addtn[1].split("@");
				document.getElementById("AllSubRemrk").value=valueY[1];
			}
			else if(addtn[1]=='AllSubNo'){
				document.forms[0].AllSub[1].checked='checked';
		}
	   }
}
}
if(additionList.length>0)
{
	for(var i = 0; i<additionList.length;i++)
    {	
	    var addtn = additionList[i].split("$");
	    if(addtn[0]=='Alcohol')
	    	{if(addtn[1]=='Regular')
	    		document.forms[0].alcohol[0].checked='checked';
	    	else if (addtn[1]=='Occasional')
	    		document.forms[0].alcohol[1].checked='checked';
	    	else if (addtn[1]=='Teetotaler')
	    		document.forms[0].alcohol[2].checked='checked';
	    	}
	    else if(addtn[0]=='Tobacco')
		    {
	    	var tabacoLst = addtn[1].split("(");
	    	
	    	if(tabacoLst[0]=='Snuff')
	    		document.forms[0].tobacco[0].checked='checked';
	    	else if (tabacoLst[0]=='Chewable')
	    		document.forms[0].tobacco[1].checked='checked';
	    	else if (tabacoLst[0]=='Smoking')
		    	{
	    		document.forms[0].tobacco[2].checked='checked';
	    		tabacoLst[1] = tabacoLst[1].replace(")","");
	    		
	    		document.getElementById("smokingTd").style.display='block';
	    		
	    		var smokSub = tabacoLst[1].split("*");
	    	
	    		if(smokSub.length>0)
		    		{
                       for(var j=0;j<smokSub.length;j++){
                    	   
                    	  var smokeVal= smokSub[j].split("@");
                    	  
                    	  if(smokeVal[0]=='PackNo')
                    		  document.forms[0].packNo.value=smokeVal[1];
                    	  else
                    		  document.forms[0].smokeYears.value=smokeVal[1];
                           } 
		    		}
		    	}
             }
	    else if(addtn[0]=='DrugUse')
		    {
              if(addtn[1]=='Yes')
            	  document.forms[0].drugUse[0].checked='checked';
              else  if(addtn[1]=='No')
            	  document.forms[0].drugUse[1].checked='checked';
            }
	    else if(addtn[0]=='BetelNut')
	    {
	    	if(addtn[1]=='Yes')
          	  document.forms[0].betelNut[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].betelNut[1].checked='checked';
	    }
	    else if(addtn[0]=='PanChewing')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].PanChewing[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].PanChewing[1].checked='checked';
	    }
	    else if(addtn[0]=='Gutka')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].Gutka[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].Gutka[1].checked='checked';
	    }
	    else if(addtn[0]=='FingerSucking')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].FingerSucking[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].FingerSucking[1].checked='checked';
	    }
	    else if(addtn[0]=='NailBiting')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].NailBiting[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].NailBiting[1].checked='checked';
	    }
	    else if(addtn[0]=='TongueBiting')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].TongueBiting[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].TongueBiting[1].checked='checked';
	    }
	    else if(addtn[0]=='MouthBreathing')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].MouthBreathing[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].MouthBreathing[1].checked='checked';
	    }
	    else if(addtn[0]=='Bruxism')
	    {
	    	addtn[1] = addtn[1].replace(")","");
	    	if(addtn[1]=='Yes')
          	  document.forms[0].Bruxism[0].checked='checked';
            else  if(addtn[1]=='No')
          	  document.forms[0].Bruxism[1].checked='checked';
	    }
    }
}
</script>

<!--Start of PatientDetails -->

<!-- End of Grievance -->
<input type=hidden name=requestType>
<input type=hidden name=actionVal>
<input type=hidden name=invId>
</form>
</body>
</html>