<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%>

<%
int liTabIndex = 0;
String photoUrl=(String)request.getAttribute("photo");
 %>
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
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
<!-- Added by Srikalyan for TG Patient Registration to Capture Patient's Biometrics -->
<script language="vbscript" type="text/vbscript">  
      Function GetFeatureAccrual (IdOfHiddenTextField)
	
	Dim Obj

	If (IdOfHiddenTextField Is Nothing) Then

		MsgBox "Invalid Hidden Field Argument Passed", vbExclamation, "Web API BMA Application"
	Else

		Set Obj = CreateObject ("Web_API_3.Legend")

		If (Obj Is Nothing) Then

			MsgBox "Unable To Create Instance For Web API, Check Dll Is Registered Properly", vbExclamation, "Web API BMA Application"
		Else

			IdOfHiddenTextField.Value = ""

			Obj.GetFeatureAccrual

			IdOfHiddenTextField.Value = Obj.Feature

			If IdOfHiddenTextField.Value <> "" Then
				MsgBox "Fingerprint Captured Successfully.", vbInformation, "Web API BMA Application"
			Else
				MsgBox "Fingerprint Not Captured Successfully", vbExclamation, "Web API BMA Application"
			End If

		End If

	End If

End Function 
    </script>
<!-- End by Srikalyan for TG Patient Registration to Capture Patient's Biometrics -->
<style>
blink {
        animation: blinker 0.6s linear infinite;
        color: #c91c34;
       }
      @keyframes blinker {  
        50% { opacity: 0; }
       }
       .blink-one {
         animation: blinker-one 1s linear infinite;
       }
       @keyframes blinker-one {  
         0% { opacity: 0; }
       }
       .blink-two {
         animation: blinker-two 1.4s linear infinite;
       }
       @keyframes blinker-two {  
         100% { opacity: 0; }
       }
       
.ui-menu .ui-menu-item {
        margin:0;
        padding: 0;
        width: 200px;
        list-style-type: none;
}
</style>
<script>
var fromDisp = '${fromDisp}';
var aisFlag= '${aisFlag}';
var aisDG = '${aisDG}';
//alert("aisDG is"+aisDG);
var unitId= '${unitId}';
var yearsEmp='${patientForm.dateOfBirth}';
var disabled='${disabled}';


$( function() {
	   var availableTags = [];
	    var list ="${mftList}".replace("[","").replace("]","").trim().split(",");
	    for(var i=0;i<list.length;i++){
	    	var temp=list[i];
	    	var arr=temp.split("-");
	    	var dummyArr = {label: arr[0], value: temp};
	   
	    	availableTags.push(dummyArr);
	    }
	    
  $( "#fname" ).autocomplete({
    source: availableTags,
    autoFocus:true,
    open: function() {
        $("ul.ui-menu").width( $(this).innerWidth() );
    }
  });



} );

function fn_emp()
{

	 var x= $('#fname').val();
	var arr= x.split("-");
	$('#fname').val(arr[0]);
	$('#ais_no').val(arr[1]);
}



function fn_captureBiometrics()
	{
		if(document.getElementById("bioFinger")!=null && document.getElementById("bioHand")!=null )
			{
				var bioFinger=document.getElementById("bioFinger").value;
				var bioHand=document.getElementById("bioHand").value;
				if(bioHand!=null && bioHand!='' && bioHand!=' ')
					{
						if(bioHand=='-1')
							{
								document.getElementById("captured").style.display="none";
								document.getElementById("unCaptured").style.display="";
								alert("Please select the Hand to which the Biometrics need to be Captured.");
								focusFieldView("bioHand");
							}
						else if(bioFinger=='-1')
							{
								document.getElementById("captured").style.display="none";
								document.getElementById("unCaptured").style.display="";
								alert("Please select the finger to which the Biometrics need to be Captured.");
								focusFieldView("bioFinger");
								return false;
							}
						else
							{
								GetFeatureAccrual(document.getElementById("biometricValue"));
								//document.getElementById("biometricValue").value="TESTVALUE";
								var biometricReturnValue=document.getElementById("biometricValue").value;
								if(biometricReturnValue!=null && biometricReturnValue!='' && biometricReturnValue!=' ')	
									{
										document.getElementById("captured").style.display="";
										document.getElementById("unCaptured").style.display="none";
									}
								else
									{
										document.getElementById("captured").style.display="none";
										document.getElementById("unCaptured").style.display="";
										alert("There is a problem in fetching the Biometric Data at this moment.Please try again after some time");
										return false;
									}
							}
					}
				else
					{
						document.getElementById("captured").style.display="none";
						document.getElementById("unCaptured").style.display="";
						alert("Please select the Hand to which the Biometrics need to be Captured.");
						focusFieldView("bioHand");
						return false;
					}
			}
		
			
	}
	function disableRelatn()
	{
		if(document.forms[0].aisType.value=='S')
		{
		 document.getElementById("disRel").style.display = 'none';
		 document.getElementById("disRelNew").style.display = 'none';
		 document.getElementById("sName").style.display='';
		 document.getElementById("dName").style.display='none';
		}
	}
	function enableRelatn()
	{
		 if(document.forms[0].aisType.value=='R')
		{
		 document.getElementById("disRel").style.display = '';
		 document.getElementById("disRelNew").style.display = '';
		 document.getElementById("sName").style.display='none';
		 document.getElementById("dName").style.display='';
		}
	}
function checkGenderRelationNew()
{
	var lField='relation';
	var fr=partial(focusBox,document.getElementById(lField));
	if(document.getElementById("relation")!=null)
	{
	var relation= document.getElementById("relation").options[document.getElementById("relation").selectedIndex].text;
	}
	var aisTy= document.forms[0].aisType.value;
	if(aisTy!=null && aisTy!='' && aisTy=='R')
		{
	if(document.forms[0].gender[0].checked)
	{
	 if(relation=='Husband' || relation=='Son' || relation=='Father' || relation=='Grandson' || 
			 relation=='Grand Father' || relation=='Son in-law' || relation=='Father in-law' || 
			 relation=='Others' || relation=='Brother' || relation=='Self' || relation=='FamilyHead' || 
		     relation=='New Born Baby')
		 return true;
	 else
		 {
		 jqueryErrorMsg('Gender Relation Validation',"Select valid Relationship",fr);
	 		return false;
		 }
	}
	if(document.forms[0].gender[1].checked)
	{
	 if(relation=='Wife' || relation=='Daughter' || relation=='Mother' || relation=='Grand Daughter' || 
		 relation=='Grand Mother' || relation=='Daughter in-law' || relation=='Mother in-law' || 
		 relation=='Others' || relation=='Sister' || relation=='Self' || relation=='FamilyHead' ||  
	     relation=='New Born Baby')
	 return true;
 	else
	 {
 		jqueryErrorMsg('Gender Relation Validation',"Select valid Relationship",fr);
 		return false;
	 }
	}
		}

}

function exportToPdf() {
	 document.forms[0].action="./patientDetailsNew.do?actionFlag=mhcPdf&genFlag=P";
   document.forms[0].submit();
}
function fn_enablePackage()
{
	newAyush.style.display = "";
	newAyushh.style.display = "";
	//newAyushTe.style.display = "none";
	newAyushGe.style.display = "none";
	newSub.style.display = "none";
	newAyushthe.style.display = "none";
	document.getElementById('ayushSubg').checked = false;
	//document.getElementById('ayushSubg1').checked = false;
	var chkds = $("input[name='ayushCheck']:checkbox");
	if (chkds.is(":checked"))  {
		$('input:checkbox').removeAttr('checked');  
	}
	/* var chkds1 = $("input[name='ayushCheckT']:checkbox");
	if (chkds1.is(":checked"))  {
		$('input:checkbox').removeAttr('checked');  
	}  */
	
}
function fn_disablePackage()
{
	newAyush.style.display = "none";
	newAyushh.style.display = "none";
	newSub.style.display = "";
	newAyushthe.style.display = "";
	document.getElementById("dosage").value='-1';
	document.getElementById('ayushSubg').checked = false;
	//document.getElementById('ayushSubg1').checked = false;
	
}
function fn_enableAtushg()
{
	//newAyushTe.style.display = "none";
	newAyushGe.style.display = "";

}
/* function fn_enableAtusht()
{
	newAyushGe.style.display = "none";
	//newAyushTe.style.display = "";
	document.forms[0].ayushSubg.checked=false;
	var chkds = $("input[name='ayushCheck']:checkbox");
	if (chkds.is(":checked"))  {
		$('input:checkbox').removeAttr('checked');  
	} 
} */
function checkExtension(id)
	{
		fileName=document.getElementById(id).value;
			if((fileName!=null)||(fileName!="")||(fileName.length>0))
				{
					var dotPos=fileName.lastIndexOf(".");
					var ext=fileName.substring((dotPos+1),fileName.length);
						if((ext=="png")||(ext=="PNG")||(ext=="gif")||(ext=="GIF")||(ext=="jpeg")||(ext=="JPEG")||(ext=="jpg")||(ext=="JPG")) 
							{
								var split=fileName.split("\\");
								fileName=split[(split.length)-1];
								
									if(fileName.length>104)
										{
											alert("Filename cannot be of length more than 100 characters");
											document.getElementById(id).value=null;
											focusBox(document.getElementById(id));
											return false;
										}
									else
										{
											fn_checkAttachValue(fileName,id);
										}
							}
						else
							{
								alert("Please upload valid attachments only(PNG/GIF/JPEG/JPG)");
								document.getElementById(id).value=null;
								focusBox(document.getElementById(id));
								return false;
							}
				}
	
	}
function fn_checkAttachValue(name,id)
	{
		var specialCharName=/^[a-zA-Z0-9.]*$/;
		
		if(!specialCharName.test(name))
			{
				alert("Attachments cannot have special characters and spaces");
				document.getElementById(id).value=null;
				focusBox(document.getElementById(id));
				return false;
			}
						
		if(!(window.ActiveXObject))
			{
				var size=document.getElementById(id).files[0].size;
				if(size>204800)
					{
						alert("Cannot upload a file of size more than 200KB");
						document.getElementById(id).value=null;
						focusBox(document.getElementById(id));
						return false;
					}		
			}
	}
function retrieveDetails()
{
	var wapFamilyNo=document.getElementById("ais_no").value;
	var fromDisp = '${fromDisp}';
	var unitId = '${unitId}';
	var mobileApp='${mobileApp}';
	var appntdate='${appntdate}';
	var serviceTyp= document.forms[0].servType.value;
	var aisTy= document.forms[0].aisType.value;
	var aisFlag='Y';
	var aisDG= '${aisDG}';
 	if(wapFamilyNo!=false)
	 {
 		document.forms[0].action="./patientDetails.do?actionFlag=retrieveCardDetails&cardNo="+wapFamilyNo+"&fromDisp="+fromDisp+"&appntdate="+appntdate+"&aisFlag="+aisFlag+"&aisDG="+aisDG+"&unitId="+unitId+"&serviceTyp="+serviceTyp+"&aisTy="+aisTy+"&mobileAppAis=N";
		document.forms[0].method="post";
 		document.forms[0].submit(); 
	 }
 	else
	 return false;
}
function retrieveDetailsApp()
{
	var wapFamilyNo=document.getElementById("ais_no").value;
	var fromDisp = '${fromDisp}';
	var unitId = '${unitId}';
	var mobileApp='${mobileApp}';
	var appntdate='${appntdate}';
	var serviceTyp= document.forms[0].servType.value;
	var aisTy= document.forms[0].aisType.value;
	var aisFlag='Y';
	var aisDG= '${aisDG}';
 	if(wapFamilyNo!=false)
	 {
 		document.forms[0].action="./patientDetails.do?actionFlag=retrieveCardDetails&cardNo="+wapFamilyNo+"&fromDisp="+fromDisp+"&appntdate="+appntdate+"&aisFlag="+aisFlag+"&aisDG="+aisDG+"&unitId="+unitId+"&serviceTyp="+serviceTyp+"&aisTy="+aisTy+"&mobileAppAis=Y";
		document.forms[0].method="post";
 		document.forms[0].submit(); 
	 }
 	else
	 return false;
}

function showButton()
{
	var aisDG= '${aisDG}';
	if(aisDG!=null && aisDG!='' && aisDG=='Y')
	{
		
		if(document.forms[0].aisType[0].checked && document.forms[0].servType[0].checked)
		{
			
			
			document.getElementById("RetrieveDetails").style.display="";
			retrieveDetails();
		}
	}
	else if(aisDG!=null && aisDG!='' && aisDG=='N')
	{				
			document.getElementById("RetrieveDetails").style.display="";
			retrieveDetailsApp();
	}
	if(disabled!=null && disabled=='Y')
	{
		document.getElementById("ais_no").readOnly=true;
		document.getElementById("fname").readOnly=true;
		document.getElementById("serType").disabled=true;
		document.getElementById("relation").disabled=true;
		document.forms[0].gender[0].disabled=true;
    	document.forms[0].gender[1].disabled=true;
    	document.forms[0].servType[0].disabled=true;
    	document.forms[0].servType[1].disabled=true;
    	document.forms[0].aisType[0].disabled=true;
    	document.forms[0].aisType[1].disabled=true;
    	if(document.forms[0].ayush.value=='M')
    	{
    		newAyush.style.display = "";
    		newAyushh.style.display = "";
    		//newAyushTe.style.display = "none";
    		newAyushGe.style.display = "none";
    		newSub.style.display = "none";
    		newAyushthe.style.display = "none";
    	}
    	else if(document.forms[0].ayush.value=='F')
    	{
    		newAyush.style.display = "none";
    		newAyushh.style.display = "none";
    		newSub.style.display = "";
    		newAyushthe.style.display = "";
    		if(document.forms[0].ayushSubg.value=='G')
        	{
        		//newAyushTe.style.display = "none";
        		newAyushGe.style.display = "";
        	} 
    	}
     	
    	
    	var checkboxValue = '${checkboxValue}';
    	var checkboxValue = checkboxValue.split(',');
    	$.each(checkboxValue, function(i, val){

    		   $("input[value='" + val + "']").prop('checked', true);

    		});
	}
}

function validate()
{
	var errMsg='';
	var lField='';
	var fromDisp = '${fromDisp}';
	var aisFlag='${aisFlag}';
	var aisTy= document.forms[0].aisType.value;
	


		if(document.getElementById("fname").value==null || document.getElementById("fname").value=='')
		{
			if(errMsg=='')
				errMsg=errMsg+"Please Enter Name <br>";
			if(lField=='')
        		lField='fname'; 
		}
		else if (!document.forms[0].gender[0].checked && !document.forms[0].gender[1].checked)
		{
		if(errMsg=='')
			errMsg=errMsg+"Please Select Gender <br>";
		if(lField=='')
	        lField='gender'; 
		}
		/* else if(document.getElementById("maritalStatus").value==-1)
	 	{
			if(errMsg=='')
				errMsg=errMsg+"Please Select Marital Status <br>"; 
			if(lField=='')
		        lField='maritalStatus'; 
	 	} */
	/* 	else if (document.getElementById("eMailId").value==null || document.getElementById("eMailId").value=='') 
		{
			var email = document.getElementById("eMailId").value;
			var flag = '';
			if(email!=null && email!='')
			{
				flag = testEmail(email);
				document.getElementById("eMailId").value="";
			}
			if (!flag) {
				if(errMsg=='')
					errMsg=errMsg+"Please Enter E-mail ID<br>";
				if(lField=='')
				    lField='eMailId'; 
			}
		} */
		else if(document.getElementById("contactno").value==null || document.getElementById("contactno").value=='')	
 		{
		if(errMsg=='')
			errMsg=errMsg+"Please Enter Contact No <br>";
		if(lField=='')
	        lField='contactno'; 
 		}
		else if(document.getElementById("serType").value==-1)
	 	{
			if(errMsg=='')
				errMsg=errMsg+"Please Select Service Type <br>"; 
			if(lField=='')
		        lField='serType'; 
	 	}
		else if(document.getElementById("relation").value==-1 && aisTy!=null && aisTy!='' && aisTy=='R')
 		{
		if(errMsg=='')
			errMsg=errMsg+"Please Select Relationship <br>";
 		if(lField=='')
        lField='relation'; 
 		}
		else if(!document.forms[0].ayush[0].checked && !document.forms[0].ayush[1].checked)
		{
		if(errMsg=='')
		errMsg=errMsg+"Please Select Health Check Up <br>";
		if(lField=='')
		lField='ayush'; 
		}
		else if(document.forms[0].ayush[0].checked || document.forms[0].ayush[1].checked)
		{
			if(document.forms[0].ayush.value=='M')
			{
				if(document.getElementById("dosage").value==-1)
		 		{
				if(errMsg=='')
					errMsg=errMsg+"Please Select Package Type<br>";
		 		if(lField=='')
		        lField='dosage'; 
		 		}
				
			}
			else if(document.forms[0].ayush.value=='F')
			{
				if(!document.forms[0].ayushSubg.checked)
				{
					if(errMsg=='')
						errMsg=errMsg+"Please Select Sub Category <br>";
						if(lField=='')
				    	lField='ayushSubg'; 
				}
				else if(document.forms[0].ayush[0].checked || document.forms[0].ayush[1].checked)
				{
					if(document.forms[0].ayushSubg.checked)
					{
						if(document.forms[0].ayushSubg.value=='G')
						{
						
						    var checkboxs=document.getElementsByName("ayushCheck");
						    var okay=false;
						    for(var i=0,l=checkboxs.length;i<l;i++)
						    {
						        if(checkboxs[i].checked)
						        {
						            okay=true;
						            break;
						        }
						    }
						    if(okay)
						    {
									if(lField=='')
							    	lField='ayushSubg'; 
						    }
						    else
						    {
						    	if(errMsg=='')
									errMsg=errMsg+"Please Select any one checkbox for General Therapy. <br>";
						    }
					}
				}
			/* 	if(document.forms[0].ayushSubg[1].checked)
				{
					if(document.forms[0].ayushSubg[1].value=='T')
					{
						 var checkboxs1=document.getElementsByName("ayushCheckT");
						    var okay=false;
						    for(var i=0,l=checkboxs1.length;i<l;i++)
						    {
						        if(checkboxs1[i].checked)
						        {
						            okay=true;
						            break;
						        }
						    }
						    if(okay)
							{
						    	if(lField=='')
							    	lField='ayushSubg'; 
							}
						    else 
						    {
						    	if(errMsg=='')
									errMsg=errMsg+"Please Select any one checkbox for Therapy Packages. <br>";
						    }
					}
					} */
				}
				
			}
			
		}

	var bioRegFlag='${bioRegFlag}';
	if(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond=='Y' && bioRegFlag!=null
			&& bioRegFlag !='' && bioRegFlag!=' ' && bioRegFlag=='Y' )
		{
			var bioHand=document.getElementById("bioHand").value;
			var bioFinger=document.getElementById("bioFinger").value;
			if(bioHand!=null && bioHand!='' && bioHand!=' ')
				{
					if(bioHand=='-1')
						{
							document.getElementById("captured").style.display="none";
							document.getElementById("unCaptured").style.display="";
							alert("Please select the Hand to which the Biometrics need to be Captured.");
							focusFieldView("bioHand");
							return false;
						}
					else if(bioFinger=='-1')
						{
							document.getElementById("captured").style.display="none";
							document.getElementById("unCaptured").style.display="";
							alert("Please select the finger to which the Biometrics need to be Captured.");
							focusFieldView("bioFinger");
							return false;
						}
					else
						{
							var biometricReturnValue=document.getElementById("biometricValue").value;
							if(biometricReturnValue==null || biometricReturnValue=='' || biometricReturnValue==' ')	
								{
									document.getElementById("captured").style.display="none";
									document.getElementById("unCaptured").style.display="";
									alert("Please Capture the Biometrics of Patient.");
									focusFieldView("biometricValue");
									return false;
								}
						}
					
				}
			else
				{
					document.getElementById("captured").style.display="none";
					document.getElementById("unCaptured").style.display="";
					alert("Please select the Hand to which the Biometrics need to be Captured.");
					focusFieldView("bioHand");
					return false;
				}
		}
	if(!errMsg=='')
	{
 		var fr = partial(focusFieldView,lField);
 		jqueryAlertMsg('Patient Registration Page Mandatory Fields',errMsg,fr);
		//focusBox(document.getElementById(lField));
		return false;
	}
  else
	  {
	 	if(checkGenderRelationNew()!=false)
	 		{
	 			jqueryConfirmMsg('Patient Registration Confirmation','Do you want to register patient?',registerPatientDetails);
	 		}
	 	else
	 		{
	 		return false;
	 		}
	  }
}
function getAisNames(id)
{
	var aisServ=id.value;

 	
 		document.forms[0].action="./patientDetailsNew.do?actionFlag=getAisHolderNames&cardNo="+aisServ;

		document.forms[0].method="post";
 		document.forms[0].submit(); 
	
}


function testEmail(str) {
	var str = str.value;
	var at = "@";
	var dot = ".";
	var lat = str.indexOf(at);
	var lstr = str.length;
	var ldot = str.indexOf(dot);
	var spclChar = "!@#$%^&*()_-+=|\}]{[':<>,?/";
	// check if '@' is at the first position or at last position or absent in given email                 
	if (str.indexOf(at) == -1 || str.indexOf(at) == 0
			|| str.indexOf(at) == lstr) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	// check if '.' is at the first position or at last position or absent in given email                
	if (str.indexOf(dot) == -1 || str.indexOf(dot) == 0
			|| str.indexOf(dot) == lstr) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	// check if '@' is used more than one times in given email 
	if (str.indexOf(at, (lat + 1)) != -1) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	// check for the position of '.'                 
	if (str.substring(lat - 1, lat) == dot
			|| str.substring(lat + 1, lat + 2) == dot) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	// check if '.' is present after two characters  from location of '@'                 
	if (str.indexOf(dot, (lat + 2)) == -1) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	// check for blank spaces in given email                 
	if (str.indexOf(" ") != -1) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	//check if any special character is present after '@'
	if (str.indexOf(spclChar, (lat + 1)) != -1) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	if (str.indexOf(('""'), (lat + 1)) != -1) {
		alert("Invalid E-Mail ID");
		document.getElementById("eMailId").value="";
		return false;
	}
	return true;
}



function registerPatientDetails()
{
	var wapNo="";
	var familyNo="";
	var unitId= '${unitId}';
	var fromDisp = '${fromDisp}';
	var mobileApp='${mobileApp}';
	var appntdate='${appntdate}';
	
	wapNo=document.getElementById("ais_no").value;
	var serviceTyp= document.forms[0].servType.value;
	var aisTy= document.forms[0].aisType.value;
	
	
	var cardFamilyNo=wapNo;
	


	if(document.getElementById("cardNo").value==cardFamilyNo)
		{
	   	
		
		document.getElementById("fname").disabled=false;
		document.getElementById("serType").disabled=false;
		document.getElementById("relation").disabled=false;
		document.forms[0].gender[0].disabled=false;
		document.forms[0].gender[1].disabled=false; 
		document.getElementById("ais_no").readOnly="";
		document.getElementById("fname").readOnly="";
    	document.forms[0].servType[0].disabled=false;
    	document.forms[0].servType[1].disabled=false;
    	document.forms[0].aisType[0].disabled=false;
    	document.forms[0].aisType[1].disabled=false;
		
		var ayushID="";
		 for (var i=0; i<document.forms[0].elements.length; i++)
			{	
			
				var type = document.forms[0].elements[i].type;
				var id=document.forms[0].elements[i].id;
				if (type=="checkbox" && id=="ayushCheck")
				{	
					if(document.forms[0].elements[i].checked)
					{
						if(ayushID==null || ayushID=="")
						{
							ayushID=document.forms[0].elements[i].value;
						}
						else
						{
							ayushID=ayushID+"~"+document.forms[0].elements[i].value;
						}
					}
				}
			}
	   document.getElementById("RegisterPatient").className='butdisable';	
	   document.getElementById("RegisterPatient").disabled=true;
	   var tgGovPatCond=document.getElementById("tgGovPatCond").value;
	   document.forms[0].action="./patientDetails.do?actionFlag=registerPatientDetails&aisSubmit=Y&fromDisp=${fromDisp}&aisflagNe=Y&tgGovPatCond="+tgGovPatCond+"&wapNo="+wapNo+"&familyNo="+familyNo+"&mobileApp="+mobileApp+"&appntdate="+appntdate+"&ayushID="+ayushID+"&unitId="+unitId+"&serviceTyp="+serviceTyp+"&aisTy="+aisTy;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
		}
	
	else
		{
		jqueryAlertMsg('Patient Registration Page','Card  No is changed.Click on Retrieve Details to get patient details');
		}
	/* else if(cardFamilyNo!=null && cardFamilyNo!='')
		{
		document.getElementById("serType").disabled=false;
 		document.forms[0].gender[0].disabled=false;
		document.forms[0].gender[1].disabled=false; 
		document.getElementById("fname").disabled=false;
		document.getElementById("relation").disabled=false;
		
		
		var ayushID="";
		 for (var i=0; i<document.forms[0].elements.length; i++)
			{	
			
				var type = document.forms[0].elements[i].type;
				var id=document.forms[0].elements[i].id;
				if (type=="checkbox" && id=="ayushCheck")
				{	
					if(document.forms[0].elements[i].checked)
					{
						if(ayushID==null || ayushID=="")
						{
							ayushID=document.forms[0].elements[i].value;
						}
						else
						{
							ayushID=ayushID+"~"+document.forms[0].elements[i].value;
						}
					}
				}
			}
	   document.getElementById("RegisterPatient").className='butdisable';	
	   document.getElementById("RegisterPatient").disabled=true;
	   var tgGovPatCond=document.getElementById("tgGovPatCond").value;
	   document.forms[0].action="./patientDetails.do?actionFlag=registerPatientDetails&aisSubmit=Y&fromDisp=${fromDisp}&aisflagNe=Y&tgGovPatCond="+tgGovPatCond+"&wapNo="+wapNo+"&familyNo="+familyNo+"&mobileApp="+mobileApp+"&appntdate="+appntdate+"&ayushID="+ayushID+"&unitId="+unitId+"&serviceTyp="+serviceTyp+"&aisTy="+aisTy;
	   document.forms[0].method="post";
	   document.forms[0].submit(); 
		} */
	     
}


function validateDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	if(isNaN(cyr))
	{
		input.value="";
		jqueryAlertMsg('Date Validation','Select '+arg1);
	}
	else
	{
	var DoB=""+(cmth)+"/"+ cdy +"/"+ cyr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoB>=DoC)
		{
		input.value="";
		jqueryAlertMsg('Date Validation',arg1+" should be less than Today's Date");
		}
	}
}

function resetData()
{
		$('.tgGovCond').css('display','block');
		
		
		
		document.getElementById("tgGovPatCond").value="N";
		document.getElementById("disableFlag").value="Y";
		//disableScreen();
		document.forms[0].card_type[0].checked=false;
		
	
		
		
		resetPatientData();
	
}
function resetPatientData()
{
	document.getElementById("ais_no").value="";

	 document.forms[0].gender[0].checked=false;
	document.forms[0].gender[1].checked=false;
	 document.forms[0].aisType[0].checked=false;
		document.forms[0].aisType[1].checked=false; 
	//document.getElementById("child").checked=false;
	//document.getElementById("cardIssueDt").value="";
	document.getElementById("fname").value="";
/* 	document.getElementById("dateOfBirth").value="";
	document.getElementById("years").value="";
	document.getElementById("months").value="";
	document.getElementById("days").value=""; */
	document.getElementById("relation").value="-1";
	//document.getElementById("caste").value="-1";
	document.getElementById("serType").value="-1";
	document.getElementById("maritalStatus").value="-1";
	//document.getElementById("occupation").value="";
	document.getElementById("contactno").value="";
	$('input:radio').removeAttr('checked');
	$('input:checkbox').removeAttr('checked');
	document.getElementById("ais_no").readOnly=false;
	document.getElementById("fname").readOnly=false;
	document.getElementById("serType").disabled=false;
	document.getElementById("eMailId").value="";
	}

function viewTelephonicInfo(){
	window.open('patientDetails.do?actionFlag=ViewTelephonicDtls&telephonicId='+document.forms[0].telephonicId.value,'TelephonicRegisteredDetails','scrollbars=1,left=20,top=20,width=1000,height=650');
}
function fn_chkTGGovCond()
	{
		if(document.getElementById("tgGovPatCond")!=null && document.getElementById("bioTable")!=null)
			{
				var tgGovPatCond=document.getElementById("tgGovPatCond").value;
				if(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond=='Y')
					{
						$('.tgGovCond').css('display','none');
						document.getElementById("bioTable").style.display="";
					}
				else
					{
						document.getElementById("bioTable").style.display="none";
						$('.tgGovCond').css('display','block');
					}
			}
		
		
	}
	/* function readCardData()
	{
		
		var errMsg='';
		if (!document.forms[0].card_type[0].checked) 
			errMsg=errMsg+"Select Card Type \n";
		if(errMsg=='')
			{
			var wapNo="";
			
			if(document.forms[0].card_type[0].checked)
				{
				wapNo=document.getElementById("cardNo").value;
				
				}
			
			wapNo=wapNo.toUpperCase();
			return wapNo;
			}
		else
			{
			jqueryErrorMsg('Health Card Validation',errMsg);
			if('${mobileApp}'!=null &&'${mobileApp}'!='' &&  '${mobileApp}'=='Y'){
			parent.parent.fn_viewAppointments();
			}
			return false;			
			}

	}
 */
$('document').ready(function(){
	document.getElementById("card_type").checked=true;
	if('${fromDisp}'!=null && '${fromDisp}'=='Y' && '${fromDisp}'!='Y' && '${pastVisitList}'!=null && '${fn:length(pastVisitList)}'>0){
	var data='${pastVisitListNew}';
	
		
		
		var temp=data.split("$");
		var msg="Last registered as patient in past 7 days  on ";
		for(var i=0;i<temp.length-1;i++)
			{
			var dt=temp[i].split("~");
			msg=msg+dt[0]+" at "+ dt[1] +",";
			}
	
		msg= msg.substring(0, msg.length-1) ;
		alert(msg);	
	}

	if('${errFlg}'!=null &&'${errFlg}'!='' && '${errFlg}'=='Y')
		{
		alert("Invalid Employee Name / Employee Id");
		$('input:radio').removeAttr('checked');
		document.getElementById("ais_no").value="";
		document.getElementById("disRel").style.display = '';
		 document.getElementById("disRelNew").style.display = '';
		 parent.fn_viewAppointmentsaAisReg();
		}
	if('${aisDis}'!=null &&'${aisDis}'!='' && '${aisDis}'=='Y' && '${errFlg}'!='Y')
	{
		 document.getElementById("disRel").style.display = 'none';
		 document.getElementById("disRelNew").style.display = 'none';
	}
	if('${triggerFlag}'!=null &&'${triggerFlag}'!='' && '${triggerFlag}'=='N'){
		//fn_getRoomNo();
		if(document.forms[0].card_type[0].checked)
		{
		      document.getElementById("ais_no").setAttribute('readOnly','readOnly');
		}
		
		
		/* $(':radio:not(:checked)').attr('disabled', true); */
		/* document.forms[0].card_type[0].disabled=true;
		document.getElementById("head").disabled=true; */
	}
});
function fn_back(){
	//parent.parent.fn_viewAppointments();
	parent.fn_viewAppointmentsEmp();
}
</script>
</head>
<body onload="javascript:fn_chkTGGovCond();showButton();enableRelatn();disableRelatn();">
<div id="middleFrame_content">
<form action="/patientDetails.do" method="post" name="InPatientForm">
<html:hidden name="patientForm" property="tgGovPatCond" styleId="tgGovPatCond" ></html:hidden>
<html:hidden name="patientForm" property="biometricValue"  styleId="biometricValue" ></html:hidden>
<table width="95%" style="margin:0 auto" class="tb" cellspacing="2" cellpadding="2">
<tr>

	<c:if test="${inActiveStatusFlag eq 'Y' }">
	<td style="height:400px;text-align:center;vertical-align:center;" class="labelheading1 tbcellCss">	
		<b>${inActiveStatusMsg}</b>
	</td>
	</c:if>
	<c:if  test="${inActiveStatusFlag ne 'Y' }">
	<td>
		<table class="tbheader">
		<tr><th><b>Employee Registration</b></th>
		<c:if test="${mobileApp eq 'Y' }">
		<td style="width:85px;"> <button class="btn" style="color:red;" type="button" class="btn btn-warning" id="backBut" onclick="javascript:fn_back()">back</button>
		</td>
		</c:if>
		</tr>
		
		</table>
		<table width="100%">
		<tr><td class="labelheading1 tbcellCss" width="15%"><b>Service Type</b><font color="red">*</font></td>
	 	 <td class="tbcellBorder"  width="70%">
			<html:radio name="patientForm" property="card_type" value="A" styleId="card_type"  title="AIS Officer Card No"/><b><label id="AIS">&nbsp;AIS</label></b> 
			</td>  
	<%-- 	   	<td class="tbcellBorder" width="70%">
		<html:select name="patientForm" property="serType" styleId="serType" title="Select Service Type" style="WIDTH: 12em" onmouseover="addTooltip('serType')"  onkeydown="validateBackSpace(event)">
					<html:option value="-1">------------select----------</html:option>
					<!--<html:option value="A"><fmt:message key="EHF.GeneralWard"/></html:option>-->
					<html:option value="IAS">IAS</html:option>
					<html:option value="IPS">IPS</html:option>
					<html:option value="IFS">IFS</html:option>
					</html:select></td>  --%>
		  <td width="20%">&nbsp;</td>
			<td width="10%">&nbsp;</td>
		  </tr>

		<tr><td class="tbcellBorder">
		<%-- <html:checkbox name="patientForm" property="head" styleId="head"  title="Check Family Head"></html:checkbox> --%>
		<b>Employee ID</b> 
		</td>
		<td class="tbcellBorder"><input type="text" name="ais_no" id="ais_no" title=" Ais Card No"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<html:radio name="patientForm" property="servType" value="IS"  onclick="javascript:showButton()" styleId="servType"  title="Self"/><b><label>&nbsp;In-Service</label></b> 
		<html:radio name="patientForm" property="servType" value="RS"  onclick="javascript:showButton()" styleId="servType1"  title="Retired"/><b><label>&nbsp;Retired&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></b> 
		<html:radio name="patientForm" property="aisType" value="S" onclick="javascript:showButton();disableRelatn();" styleId="aistype"  title="Self"/><b><label>&nbsp;Self</label></b> 
		<html:radio name="patientForm" property="aisType" value="R" onclick="javascript:showButton();enableRelatn();" styleId="aistype1"  title="Dependent"/><b><label>&nbsp;Dependent</label></b> 
		<td> <button class="but"  type="button" id="RetrieveDetails" onclick="javascript:retrieveDetails()" style="display: none">Retrieve</button>
</td>
		<logic:equal  name="patientForm" property="telephonicReg" value="Yes">
		<td><a href="javascript:viewTelephonicInfo()">View Telephonic Intimation Details</a></td>
		</logic:equal>
		</tr>
		</table>
		<br>
		<table class="tbheader">
		<tr><td><b>Employee Details</b></td></tr>
		</table>

		<table class="contentTable">
		<tr>
		<td class="labelheading1 tbcellCss" id="sName"><b><fmt:message key="EHF.Name"/></b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss" style="display:none" id="dName"><b>Dependent Name</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.Gender"/></b> <font color="red">*</font>
			</td>	
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.MaritalStatus"/></b> </td>
		<td class="labelheading1 tbcellCss"><b>E-mail ID</b> </td>	
		</tr>
		<tr>
		<td class="tbcellBorder"> <html:text name="patientForm" property="fname" styleId="fname" title="Enter Name" maxlength="100" style="WIDTH: 14em" onchange="fn_emp(this);" /></td>
		<td class="tbcellBorder"><html:radio name="patientForm" property="gender" value="M" title="Male" styleId="gender" /><b><fmt:message key="EHF.Male"/></b> 
			<html:radio name="patientForm" property="gender" value="F" title="Female" styleId="gender"/><b><fmt:message key="EHF.Female"/></b></td><%-- <td>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;  &nbsp; &nbsp;  &nbsp;<html:checkbox name="patientForm" property="child" styleId="child" onclick="fn_chkChildYN(this.checked)"><fmt:message key="EHF.Child"/></html:checkbox> </td>  --%>
		<td class="tbcellBorder"><html:select name="patientForm" property="maritalStatus" styleId="maritalStatus" title="Select Marital Status" style="WIDTH: 12em" onmouseover="addTooltip('maritalStatus')">
				<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
				<html:options property="ID" collection="maritalStatusList" labelProperty="VALUE"/>
			</html:select></td>
		<td class="tbcellBorder"> <html:text name="patientForm" property="eMailId" styleId="eMailId" title="Enter E-mail ID" maxlength="100" style="WIDTH: 14em"  onchange="testEmail(this)" /></td>

		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.ContactNo"/></b> <font color="red">*</font></td> 
		<td class="labelheading1 tbcellCss"><b>Service Type</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss" id="disRel"><b><fmt:message key="EHF.Relationship"/></b> <font color="red">*</font></td> 

		</tr>
		<tr>
		<td class="tbcellBorder"><html:text name="patientForm" property="contactno" styleId="contactno" maxlength="10" title="Enter Contact No" onchange="validateMobile(this)"/></td> 
	 	<td class="tbcellBorder">
		<html:select name="patientForm" property="serType" styleId="serType" title="Select Service Type" style="WIDTH: 12em" onmouseover="addTooltip('serType')"  onkeydown="validateBackSpace(event)">
					<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
					<!--<html:option value="A"><fmt:message key="EHF.GeneralWard"/></html:option>-->
					<html:option value="IAS">IAS</html:option>
					<html:option value="IPS">IPS</html:option>
					<html:option value="IFS">IFS</html:option>
					</html:select></td> 
		<td class="tbcellBorder"  id="disRelNew"><html:select name="patientForm" property="relation" styleId="relation" title="Select Relationship" style="WIDTH: 12em" onmouseover="addTooltip('relation')">
			<html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
			<html:options property="ID" collection="relationList" labelProperty="VALUE"/> 
			</html:select></td> 
		</tr>
		
		<table class="tbheader">
		<tr><td><b>Hospital and Checkup Details</b></td></tr>
		</table>
		<table class="contentTable">
		<tr>
		
		<c:if test="${fromDisp eq 'Y'}">
		<td class="labelheading1 tbcellCss"><b>Hospital</b> <font color="red">*</font></td>
		</c:if>
		<td class="labelheading1 tbcellCss"><b><fmt:message key="EHF.DateAndTime"/></b> <font color="red">*</font></td></tr>
		<tr>
			
				<td class="tbcellBorder">
			<html:select name="patientForm" property="hospNims" styleId="hospNims" style="width:17em;" title="Select Dosage"   onmouseover="addTooltip('dosage')">
		<html:option value="NI">NIMS HOSPITAL</html:option>
		<%-- <html:option value="GO">GOLD</html:option>
		<html:option value="SI">SILVER</html:option> --%>
		
</html:select>
</td>
			
		<td class="tbcellBorder"><html:text name="patientForm"  property="dtTime" styleId="dtTime" title="Registration Date & Time" style="WIDTH: 15em"/></td>
		</tr>
		<tr>
		<td class="labelheading1 tbcellCss"><b>Health Check Up</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss"><b>Download As:</b> </td>
		</tr>	
		<tr>
		<td class="tbcellBorder" ><html:radio name="patientForm" property="ayush" value="M" title="Master" styleId="ayush" onclick="javascript:fn_enablePackage()" /><b>Master Health Checkup</b> 
								  <html:radio name="patientForm" property="ayush" value="F" title="Ayush"  styleId="ayush1" onclick="javascript:fn_disablePackage()"/><b>Ayush Services</b></td>
		<td class="tbcellBorder" onclick="javascript:exportToPdf()"><a><blink>Master Health CheckUp Document</blink></a></td>						  
			
			
		</tr>
		
		
		<tr>
	
		
		<td class="labelheading1 tbcellCss" id="newAyush" style="display: none"><b>Package Type:</b> <font color="red">*</font></td>
		<td class="labelheading1 tbcellCss" id="newSub" style="display: none"><b>Sub Category:</b> <font color="red">*</font></td>
		
	
		

		</tr>
		<tr>
	<td class="tbcellBorder" id="newAyushthe" style="display: none" >
					<html:radio name="patientForm" property="ayushSubg" value="G" title="Ayush" styleId="ayushSubg" onclick="javascript:fn_enableAtushg()"/><b>General Therapy</b>
			<%-- <html:radio name="patientForm" property="ayushSubg" value="T" title="Ayush" styleId="ayushSubg1" onclick="javascript:fn_enableAtusht()"/><b>Therapy Packages</b> --%></td>

	
		

		</tr>
	
	    <tr>
	    	<td class="labelheading1 tbcellCss" id="newAyushGe" style="display: none">

                     <input type="checkbox" name="ayushCheck" id="ayushCheck" value="101">Snehakarma(includes Steam Bath) <br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="102">Nadi Swedam(includes Steam Therapy)<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="103">Pinda Swedam(includes Steam Bath)<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="104">Kati Vasthi(As per Location)<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="105">Janu Vasthi<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="106">Nasyakarma<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="107">Shirodhara(Detox Therapy)<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="108">General Massage & Steam Bath & Diet Counselling<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="109">Partial Massage with Fomentation & Diet Counselling<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="110">General Massage & Hot/Cold blanket pack & Diet Counselling<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="111">Specific Symptomatic treatment for pain(Local Massage & Infrared light & Steam Bath)<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="112">Weight Moderation & Dietics<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="113">Stress Relief/Migrane/Body Reform & Dietics<br>
        <input type="checkbox" name="ayushCheck" id="ayushCheck" value="114">Body Rejuvenation<br>
		</td>
		<!-- 	<td class="labelheading1 tbcellCss" id="newAyushTe" style="display: none">
		<input type="checkbox" name="ayushCheckT" id="ayushCheck" value="1">Abhyanga Sweda(7 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="2">Navrakizi(7 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="3">Patra Potaji Pinda Sweda(7 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="4">Sirovasti(7 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="8">Vamana Karma(28 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="9">Virechana Karma(28 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="10">Snehapana(7 days)<br>
        <input type="checkbox" name="ayushCheckT" id="ayushCheck" value="12">Pizhichil(7 days)<br>
		</td> -->
	  
			
			<td class="tbcellBorder" id="newAyushh" style="display: none">
			<html:select name="patientForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage"  onmouseover="addTooltip('dosage')">
		<html:option value="-1">Select</html:option>
				<html:option value="P1">Master Health Check up(4000)</html:option>
		<html:option value="P2">Executive Health Check up(6000)</html:option>
		<html:option value="P3">Life Health Check up(Male-13000 and Female-14000)</html:option>
		
</html:select>
</td>
		
	
		
		</tr>  
		
		
		</table>
		<table width="100%">
		<tr>
		<td style="height: 10px; font-size:small;" nowrap="nowrap" width=20%>
				<font color="red"> <fmt:message key="EHF.MandatoryFields"/><br /></font>
		</td>
		<%-- <c:if test="${disabled eq 'Y' }">  --%>
		<td width="20%" align="center" id="Register"> <button class="but"  type="button" id="RegisterPatient" onclick="validate()">Submit</button></td>
		<%-- </c:if>  --%>
		<%-- <c:if test="${disabled eq 'N' or aisDG eq 'Y' }"> 
		<td width="20%" align="right" id="Register"> <button class="but"  type="button" id="RegisterPatient" onclick="validate()">Submit</button></td>
		<td width="20%" colspan="2" id="Resetbtn"> <button class="but"  type="button" id="reset" onclick="jqueryConfirmMsg('Data Reset Confirmation','Do you want to reset patient data?',resetData)">Reset</button></td>
		</c:if> --%>
		<td width="20%"></td>
		</tr>
		</table>
<html:hidden name="patientForm" property="disableFlag" styleId="disableFlag"/>
<html:hidden name="patientForm" property="addrEnable" styleId="addrEnable"/>
<html:hidden name="patientForm" property="errMsg" styleId="errMsg"/>
<html:hidden name="patientForm" property="cardNo" styleId="cardNo"/>
<html:hidden name="patientForm" property="card_type" styleId="card_type"/>
<html:hidden name="patientForm" property="ageString" styleId="ageString"/>
<html:hidden name="patientForm" property="telephonicId" styleId="telephonicId"/>
<html:hidden name="patientForm" property="telephonicReg" styleId="telephonicReg"/>
<html:hidden name="patientForm" property="empCode" styleId="empCode"/>
<html:hidden name="patientForm" property="eMailId" styleId="eMailId"/>
<html:hidden name="patientForm" property="scheme" styleId="scheme"/>
<html:hidden name="patientForm" property="prc" styleId="prc"/>
<html:hidden name="patientForm" property="payScale" styleId="payScale"/>
<html:hidden name="patientForm" property="dept" styleId="dept"/>
<html:hidden name="patientForm" property="deptHod" styleId="deptHod"/>
<html:hidden name="patientForm" property="postDist" styleId="postDist"/>
<html:hidden name="patientForm" property="postDDOcode" styleId="postDDOcode"/>
<html:hidden name="patientForm" property="servDsgn" styleId="servDsgn"/>
<html:hidden name="patientForm" property="ddoOffUnit" styleId="ddoOffUnit"/>
<html:hidden name="patientForm" property="currPay" styleId="currPay"/>
<html:hidden name="patientForm" property="designation" styleId="designation"/>
<html:hidden name="patientForm" property="aadharID" styleId="aadharID"/>
<html:hidden name="patientForm" property="aadharEID" styleId="aadharEID"/>
<html:hidden name="patientForm" property="ayushID" styleId="ayushID"/>
<html:hidden property="fromDisp" name="patientForm" value="${fromDisp}"/>

<script>
 
 if(document.getElementById("cardNo").value!=null || document.getElementById("cardNo").value!="")
 {
 var cardFamilyNo=document.getElementById("cardNo").value;
 if(document.forms[0].card_type[0].checked)
 		{
 			document.getElementById("ais_no").value=cardFamilyNo;
 			
} }

</script>
</td>
</c:if>
</tr>
</table>
</form>
</body>
</html>
</fmt:bundle>




