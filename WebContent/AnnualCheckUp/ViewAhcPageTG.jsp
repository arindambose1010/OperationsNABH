<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"
	import="java.util.ArrayList,java.util.Iterator,java.util.List,com.ahct.patient.vo.PatientVO"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ include file="/common/include.jsp"%>

<fmt:setLocale value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
	<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title><fmt:message key="EHF.Title.AHC" /></title>
	<link rel="stylesheet" type="text/css"
		href="bootstrap/css/bootstrap.min.css">
		<link href="css/select2.min.css" rel="stylesheet">
	<LINK href="css/patient.css" type="text/css" rel="stylesheet">
	<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet"
		type="text/css" media="screen">
		<LINK href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="screen">
	<script src="js/jquery-1.9.1.min.js"></script>
	<script src="bootstrap/js/bootstrap.min.js"></script>
	<script src="js/select2.min.js"></script>
	
	<script src="js/jquery.msgBox.js" type="text/javascript"></script>
	<script src="js/patientscripts.js"></script>
	<%@ include file="/common/includeCalendar.jsp"%>
	<style>
	fieldset {
    border: 1px solid silver;
    margin: 0 2px;
    padding: 0.35em 0.625em 0.75em;
	}
	.info{
		color:red;
	}
	body{
	font-size:1.2em;
	}
	.btn
	{
	border-radius:20px;
	}
	.btn:hover
	{
	border-radius:5px;
	}
	.form-control{font-size:1.1em !important;font-weight:normal}
	</style>
	<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
	<script>

	var genInventCount=0;
	var updateGenInvestCount=0;
	var updateGenInvestLst=new Array();
	var genInventList=new Array();
	var genInvestAttachId=0;
	var updGenInvestAttachId=0;

	var genOldList=new Array();







	function addGenInvestigationAhc(){
		
		var otherInvest=false;
		if(document.getElementById("invOthers"))
	    otherInvest=document.getElementById("invOthers").checked;
		if(document.getElementById("otherInvestCount"))
			{
		var otherInvestCountTemp=document.getElementById("otherInvestCount").value;
		
		if(otherInvestCount<=otherInvestCountTemp)
			{
			otherInvestCount=otherInvestCountTemp;
			}
			}
		var testId='';
		if(!otherInvest)
			{
		if(document.getElementById("genBlockInvestName").value==-1)
		{
		var fr=partial(focusBox,document.getElementById("genBlockInvestName"));
		alert("Please select Investigation Block");
		partial(focusBox,document.getElementById("genBlockInvestName"));
		return false;
		}
		if(document.getElementById("genInvestigations").value=="-1")
		{
			var fr=partial(focusBox,document.getElementById("genInvestigations"));
			alert("Please Select Investigations");
			partial(focusBox,document.getElementById("genInvestigations"));
			return false;
		}
			}
		else 
		{
		var otherInvName=document.getElementById("otherInvName").value.trim();
		if(otherInvName==null || otherInvName=="")
			{
			alert("Please Enter Investigation Name");
			document.getElementById("otherInvName").focus();
			document.getElementById("otherInvName").value='';
			return false;
			}

		for(var i=0;i<genOldList.length;i++)
		{
		var invValues=genOldList[i].split("~");
		
		if(invValues[2]!=null)
			{
			var invName='';
			
			invName=invValues[2].replace("@","");
			//invName=invValues[2].replace("\\","\\\\");
			var invNameTemp=invName;
			if(invNameTemp.toUpperCase().localeCompare(document.getElementById("otherInvName").value.trim().toUpperCase())==0)
				{
				
				alert("Investigation Name already added.Please select another Investigation Name");
				document.getElementById("otherInvName").value='';
				document.getElementById("otherInvName").focus();
				return false;
			    }
			}
		}
		
		for(var i=0;i<genInventList.length;i++)
		{
		var invValues=genInventList[i].split("~");
		
		if(invValues[2]!=null)
			{
			var invName='';
			invName=invValues[2].replace("@","");
			invName=invName.trim();
		
			
			if(invName.trim().toUpperCase()==document.getElementById("otherInvName").value.trim().toUpperCase())
				{
				alert("Investigation Name already added.Please select another Investigation Name");
				document.getElementById("otherInvName").value='';
				document.getElementById("otherInvName").focus();
				return false;
			    }
			}
		}
		
		
		}
		if(!otherInvest)
			{
		for(var c=0;c<genOldList.length;c++){
			//var symptomsValues=genInventList[c].split("~");
			if(genOldList[c].indexOf(document.getElementById("genInvestigations").value) !== -1)
				{
				var fr=partial(focusBox,document.getElementById("genInvestigations"));
				alert("Investigation Name already added.Please select another Investigation Name");
				partial(focusBox,document.getElementById("genInvestigations"));
				return false;
				}
			}
		//alert(genInventList.length);
		for(var c=0;c<genInventList.length;c++)
		{
		//var symptomsValues=genInventList[c].split("~");
		if(genInventList[c].indexOf(document.getElementById("genInvestigations").value) !== -1)
			{
			var fr=partial(focusBox,document.getElementById("genInvestigations"));
			jqueryErrorMsg('Unique Investigation Validation',"Investigation Name already added.Please select another Investigation Name",fr);
			partial(focusBox,document.getElementById("genInvestigations"));
			return false;
			}
		}
		
	    }
		if(genInventCount<=15)
		{
			if(genInventCount>=0)
			{
				document.getElementById('genTestTable').style.display='';	
			}
			var investTableId=document.getElementById('genTestTable');
	        
			var newRow=investTableId.insertRow(-1);
			//var newcell=newRow.insertCell(0);
			//newcell.innerHTML='<td width="10%">'+parseInt(genInventCount+1)+'</td>';
			if(otherInvest)
				{
			otherInvestCount++;
			
			testId="OI"+otherInvestCount;
			
			var newcell=newRow.insertCell(0);
			newcell.innerHTML='<td width="30%">Others</td>';
			var newcell=newRow.insertCell(1);
			newcell.innerHTML='<td width="25%">'+document.getElementById("otherInvName").value+'</td>';
				}
			else
				{
				var newcell=newRow.insertCell(0);
				newcell.innerHTML='<td width="30%">'+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+'</td>';
				var newcell=newRow.insertCell(1);
				newcell.innerHTML='<td width="25%">'+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text+'</td>';
				}
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="25%"><input type="file"  id='+document.getElementById("genInvestigations").value+' name="genAttach['+genInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
			if(document.getElementById("opPkgName"))
				{
			if(document.getElementById("opPkgName").value!=null && document.getElementById("opPkgName").value!="-1")
				{
		
			newcell=newRow.insertCell(3);
			newcell.innerHTML='<td width="20%"><button  class="btn btn-danger" type="button"  name='+document.getElementById("genInvestigations").value+'d id='+document.getElementById("genInvestigations").value+'d >Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
				}
				}
			else
				{
		
				newcell=newRow.insertCell(3);
					
				if(!otherInvest)
				{
				    newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+document.getElementById("genInvestigations").value+'d id='+document.getElementById("genInvestigations").value+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';
				}
				else
				{
					newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+testId+'d id='+testId+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';	
				}
				}
			
			
			var deleteButName='';
			if(otherInvest)
				{
		    deleteButName=testId+'d';
				}
			else
				{
			deleteButName=document.getElementById("genInvestigations").value+'d';
				}
			document.getElementById(deleteButName).onclick = function(){
				 //confirmRemoveRow(this,"geninvestigation");
				fr=partial(deleteGenInvestAhc,this,document.getElementById("genInvestigations").value);
				jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
				 }; 
				 var  genInvest="";
					if(document.getElementById("opPkgName"))
					{
				 if(document.getElementById("opPkgName").value!=null && document.getElementById("opPkgName").value!="-1")
			     genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genInvestigations").value+"~"+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text;
					}
				 else
					 {
			     
					 if(otherInvest)
					 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value+"~"+0;
					 else
					 genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genInvestigations").value+"~"+document.getElementById("genInvestigations").options[document.getElementById("genInvestigations").selectedIndex].text;	 
					 }
			genInventList[genInventCount]=genInvest;
			//alert(genInventList[genInventCount]);
		    //document.getElementById("symptoms").value=symptoms;
		    genInventCount++;
		    if(genInventCount>0)
				{
				document.getElementById("genTestTable").style.display="";
				}
			
			genInvestAttachId=genInvestAttachId+1;
			
		}
		else
		{
			alert("Cannot add more than 15 tests");
		}
		//parent.fn_resizePage();	
	}
	function deleteGenInvestAhc(src,investId){

	    var oRow=src.parentNode.parentNode;
		genInventList.splice(oRow.rowIndex-1,1);	
		alert(oRow.rowIndex);	
	    document.getElementById("genTestTable").deleteRow(oRow.rowIndex);
		genInventCount--;
		
		
			if(genInventCount==0)
				{
						
						document.getElementById('genTestTable').style.display='none';
				        if(genOldList.length==0){
						
						
						
						
						
						
						
						
				        }
				}
				//parent.fn_resizePage();
				}

	function removeGenInvestOnloadAhc(src,investId,price){
		
		           genInvestRemove=genInvestRemove+investId+"@";
	           		var oRow = src.parentNode.parentNode; 
	           		//genOldList.splice(oRow.rowIndex-1,1);
	           		
	           		document.getElementById("genTestTableID").deleteRow(oRow.rowIndex);
	           		
	           		for(var i=0;i<genOldList.length;i++){
	           	        if(genOldList[i].indexOf(investId,0)!=-1)
	           	        	{
	           	        	
	           	        	
	           	        	var schemeId=document.getElementById("scheme").value;
	           	        	var patientScheme="";
	           	        	if(document.getElementById("patientScheme"))
	           	        	 patientScheme=document.getElementById("patientScheme").value;
	           	        	var hospType=document.getElementById("hosptype").value;
	           	        	
	           	        	genOldList.splice(i,1);
	           	        	if(document.forms[0].patientType[0].checked)
	           	        		{
	           	        	
	           	      /*  	if( schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
	           	 		{
	           	        	
	           	        	var invPrice=price;
	           	        	
	//alert(invPrice);
	           	        	var totInvestPrice=document.forms[0].totInvestPrice.value;
	           	        	var effInvPrice=totInvestPrice-invPrice;
	           	        	document.forms[0].totInvestPrice.value=effInvPrice;
	           	        	document.getElementById("totalInvPrice").innerHTML='<b><font color="#B01000"> &#x20B9;'+effInvPrice+'</font></b>';
	           	        	
	           	        	document.getElementById("costOfInvest").innerHTML='<b><font color="#B01000"> &#x20B9;'+effInvPrice+'</font></b>';
	           	        	//var totOpCost=document.forms[0].totalOpCost.value;
	           	        	var consulAmt=document.getElementById("consultFee").value;
	           	        	var effOpCost=parseFloat(consulAmt)+parseFloat(effInvPrice);
	           	        	//document.forms[0].totalOpCost.value=effOpCost;
	           	        	document.getElementById("totalOpCost").innerHTML='<b><font color="#B01000"> &#x20B9;'+effOpCost+'</font></b>';
	           	        			}*/
	           	        	
	           	        	
	           	        	
	           		         }
	           	        	}
	           	       	}
	           		
	            if(genOldList.length==0)
	    				{
	            
	    			document.getElementById('genTestTableID').style.display='none';
	    			
	    				}
	}
	function removeRow(src)
	{  
			var availableList1 = document.getElementById('investigations');
			var selectedList1  = document.getElementById('investigationSel');  

			for(var i=0;i<selectedList1.length;i++)
			{
				if(src.name==selectedList1[i].id){        
					var newOption = new Option();
					newOption.text = selectedList1.options[i].text; 
					newOption.value = selectedList1.options[i].value;
					newOption.id = newOption.value ;
					availableList1.options[availableList1.length] = newOption; 

					selectedList1.options.selectedIndex=i;
					selectedList1.remove(selectedList1.options.selectedIndex);         
					// var oRow = src.parentElement.parentElement;----Not working in mozilla
					var oRow = src.parentNode.parentNode; 
					// once the row reference is obtained, delete it passing in its rowIndex/
					// document.all("testTable").deleteRow(oRow.rowIndex);----Not working in mozilla
					document.getElementById("testTable").deleteRow(oRow.rowIndex);
					var investTableId=document.getElementById('testTable');
					for(var i=1;selectedList1.length>=i;i++)
					{
						var newRow=investTableId.rows[i];
						var snocell=newRow.cells[0];
						snocell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
					}
					if(selectedList1.length==0){
						document.getElementById('testTable').style.display='none';
					}
				}
			} 
			//parent.fn_resizePage();
	}








	
	var save='${save}';
	var msg='<bean:write name="annualCheckUpForm" property="msg"/>';
	if(msg!=null && msg!=''){
		
		alert(msg);
		if(save!=null && save=='N')
		parent.fn_annualRegisteredPatientsView();
	}
	
	function fn_loadProcessImage() {
		document.getElementById('processImagetable').style.display = "";
		setTimeout(
				function() {
					document.getElementById('processImagetable').style.display = "none";
				}, 3000)
	}
	
	
	
	function getSubFieldType(input)
	{
		
		var examinField="";
		var id=input.value;	
		
		var maxlength=5;
		var subTypeField=document.getElementById(id+"h").value;
		var prop = document.getElementById(id+"h").name;
		if(prop=="Height (in Cm)")
		{maxlength=10;}
	     else if(prop=="Weight (in Kg)")
		{maxlength=10;}
	    else if(prop=="BMI")
		maxlength=21;
				
		if(input.checked)
		{
		if(subTypeField=='T')
			{
			if(prop!='BP Lt.Arm mm/Hg'&& prop!='BP Rt.Arm mm/Hg'&& prop!='Temperature(C/F)')
				{examinField=examinField+"<input  type='text' name='"+id+"' id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'onchange='validateInnerNum(this)'";
				 if(prop=="BMI") 
					 examinField=examinField+" readOnly/> ";
				 else
					examinField=examinField+" /> ";
				}
			else if(prop=='BP Lt.Arm mm/Hg')
				examinField=examinField+"<input  type='text' style='width:37%' name='"+prop+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>&nbsp; / &nbsp;&nbsp;<input type='text' name='BP1' id='BP1' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			else if(prop=='BP Rt.Arm mm/Hg') 
				examinField=examinField+"<input  type='text' style='width:37%' name='"+prop+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> &nbsp;/&nbsp;&nbsp; <input type='text' name='BP2' id='BP2' style='width:38%'  maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			else if(prop=='Temperature(C/F)')
				{examinField=examinField+"<input type='radio' name='temp' id='temp' value='C' title='Centigrade' onclick='showTemp()'/>C<input type='radio'  name='temp' id='temp' value='F' title='ForeignHeat' onclick='showTemp()' />F&nbsp;&nbsp;&nbsp;<input type='text'  style='width:57%;' name='"+prop+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'  onchange='validateInnerNum(this)'/>";

				}
			}
		else if(subTypeField=='R')
			{
			
				examinField=examinField+"<input type='radio' name='"+prop+"' id='"+id+"' value='Y' title='Yes' onclick='getExaminSubCatValue(this)'/>Yes<input type='radio'  name='"+prop+"' id='"+id+"' value='N' title='No' onclick='getExaminSubCatValue(this)'/>No<br /><span id='"+prop+"Sub'></span>";
			}
		
		document.getElementById(id).innerHTML=examinField;
		}
		else
		{
		document.getElementById(id).innerHTML="";
		}
		//parent.fn_resizePage();
	}
	function getOtherText(input){
		var othrField="";
		var id=input.value;	
		var subTypeField=document.getElementById(id).value;
		var prop = document.getElementById(id).name;
		var surgName='History Of Past illness Surgeries text Field';
		if(input.checked){
		if(id=='PH11')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryOthr' id='pastHistryOthr' maxlength='100' title='History Of Past illness Other text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH8')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryCancers' id='pastHistryCancers' maxlength='100' title='History Of Past illness Cancers text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH12')
		othrField=othrField+"<input type='text' class='form-control' name='pastHistryEndDis' id='pastHistryEndDis' maxlength='100' title='History Of Past illness Endocrine Diseases text Field' onchange='validateInnerAlphaSpace(this)'/>";
		if(id=='PH10')
		othrField=othrField+'<textarea class="form-control" name="pastHistrySurg" id="pastHistrySurg" title="History Of Past illness Surgeries text Field" style="width:12em;height:2em" onkeydown="return maxLengthPress(this,4000,event)" onkeypress="return validateSplKeyCodes(event)" onchange="validateSpaces(this,\''+surgName+'\')"/>';
		else if(id=='FH11')
			othrField=othrField+"<input type='text' class='form-control' name='familyHistoryOthr' id='familyHistoryOthr' maxlength='100' title='Family History Other Text Field' onchange='validateInnerAlphaSpace(this)'/>";
		document.getElementById(id).innerHTML=othrField;
		}
	else
		{
		document.getElementById(id).innerHTML="";
		}
	}
	function getSubListHistory(input,button)
	{	
		
		var parntCode=input.value;
		var prop = document.getElementById(parntCode+"p").name;
		if(input.checked)
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
			   alert("Your browser does not support XMLHTTP!");
		   }   
		   url = "./annualCheckUpAction.do?actionFlag=getPersonalHistory&callType=Ajax&parntCode="+parntCode;    
		   xmlhttp.onreadystatechange=function()
		   {
			  
		       if(xmlhttp.readyState==4)
		       {
	          		var resultArray=xmlhttp.responseText;
	          		
	          		if(resultArray.trim()=="SessionExpired*"){
		    	    	alert("Session has been expired");
		    	    	parent.sessionExpireyClose();
		            }
		           else
		           {
		        	   
		           		resultArray = resultArray.replace("[","");
		           		
		           		resultArray = resultArray.replace("]","");
		           	
		           		var pHistoryList = resultArray.split(","); 
		           		if(pHistoryList.length>0)
		           		{
		        	   		var phistory="";
		               		for(var i = 0; i<pHistoryList.length;i++)
		               		{	
		                    	var arr=pHistoryList[i].split("~");                     
		                    	if(arr[1]!=null && arr[0]!=null)
		                    	{
		                        	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                        	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                        	
	                                if(button!='reset' && '${lstPerHis}'!=null && '${lstPerHis}'.indexOf(val2,0)!=-1)	                                         
			                    	{
	    		                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"' checked='checked'/>"+""+val1;
	                                 }
			                    	else
			                    	phistory=phistory+"<input type='radio' name='"+prop+"' id='"+parntCode+"' value='"+val2+"' onclick='getSubLevelDetails(this)' title='"+val1+"'/>"+""+val1;	
				             	}
		               		}
		               		document.getElementById(parntCode).innerHTML=phistory+"<span id='"+prop+"Td'></span>";
		            	}
		         	}
		       }			
		   }
			xmlhttp.open("Post",url,false);
			xmlhttp.send(null);
		}
		else
			{
			document.getElementById(parntCode).innerHTML="";
			}
			 //parent.fn_resizePage();
	}
</script>
</logic:equal>
	</head>
	<body onload="fn_loadProcessImage();">
	 <div id="processImagetable"
		style="top: 50%; width: 100%; position: absolute; z-index: 60;">
	<table border="0" align="left" width="50%">
		<tr>
			<td align="center">
			<div id="processImage" align="center"><img
				src="images/Progress.gif" width="100" height="100" border="0"
				tabindex="3"></img></div>
			</td>
		</tr>
	</table>
	</div> 
	<div id="middleFrame_content">
	<form action="/annualCheckUpAction.do" method="post"
		enctype="multipart/form-data"><br>
	<table style="margin: 0 auto; width: 100%">
		<tr>
			<td colspan="4">
			<div class="col-lg-6"> 
				<table class="tbheader table-responsive" width="100%">
			
				<tr style="margin: 0 auto; width: 100%">
					<td align="center" width="100%"><b>&nbsp;&nbsp;&nbsp;<fmt:message
						key="EHF.Title.AnnualHealthCheckup" /></b></td>
				</tr>
			</table>
			</div>
			</td>
		</tr>
		<tr>
			<td>
		<table class="table table-responsive" width="100%">
				<tr>
					<td class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.HealthCardNo" /></b></td>
					<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write
						name="annualCheckUpForm" property="cardNo" /></b></td>
					<td>&nbsp;</td>
					<td class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.PatientNo" /></b></td>
					<td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write
						name="annualCheckUpForm" property="patientNo" /></b></td>
					<td>&nbsp;</td>
				</tr>
			</table>
			
			<br>
			<div class="col-lg-6"> 
				<table class="tbheader table-responsive" width="100%">
					<tr>
						<td align="center" width="100%"><b><fmt:message key="EHF.Title.PatientDetails" /></b></td>
					</tr>
				</table>
			</div>
		
			</td>
		</tr>
		<tr>
			<td>
			<table class="table table-responsive" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="27%" valign="top">
					<fieldset ><legend
						class="legendStyle"><b><fmt:message
						key="EHF.FrameSet.PatientDetails" /></b></legend>
					<div  id="commonDtlsField" style="height:21em;">
					<table width="100%" height="200px" class="table-responsive" style="table-layout:fixed;word-wrap:break-word;">
						<%-- <tr><td class="labelheading1 tbcellCss" width="50%"><b><fmt:message key="EHF.CardIssueDate"/></b></td><td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write name="annualCheckUpForm" property="cardIssueDt"/></b></td></tr> --%>
						<tr>
							<td class="labelheading1 tbcellCss" width="40%"><b><fmt:message
								key="EHF.Name" /></b></td>
							<td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="name" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Gender" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="gender" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.DateOfBirth" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="dateOfBirth" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Age" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="years" />Y <bean:write
								name="annualCheckUpForm" property="months" />M <bean:write
								name="annualCheckUpForm" property="days" />D</b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Relationship" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="relation" /></b></td>
						</tr>
						<%-- <tr><td class="labelheading1"><b><fmt:message key="EHF.Caste"/></b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="annualCheckUpForm" property="caste"/></b></td></tr> --%>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Slab" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="slab" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Designation" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="designation" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.ContactNo" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="contactNo" /></b></td>
						</tr>
					</table>
					</div>
					</fieldset>
					</td>
					<td width="29%" valign="top">
					<fieldset ><legend
						class="legendStyle"><b><fmt:message
						key="EHF.CardAddress" /></b></legend>
					<div  id='cardAddressField' style="height:21em;">
					<table width="100%" height="200px" class="table-responsive" style="table-layout:fixed;word-wrap:break-word;">
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><b><fmt:message
								key="EHF.HouseNo" /></b></td>
							<td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="houseNo" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Street" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="street" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b>State</b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="state" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.District" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="district" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Mdl/Mcl" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="mandal" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Village" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="village" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Pin" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="pin" /></b></td>
						</tr>
					</table>
					
					</div>
					</fieldset>
					</td>
					<td  width="29%" valign="top">
					<fieldset height="20em;"><legend
						class="legendStyle"><b><fmt:message
						key="EHF.CommunicationAddress" /></b></legend>
					<div id="commAddressField" style="height:21em;">
					<table width="100%" 
						style="table-layout: fixed; word-wrap: break-word;">
						<tr>
							<td class="labelheading1 tbcellCss" width="50%"><b><fmt:message
								key="EHF.HouseNo" /></b></td>
							<td class="tbcellBorder" width="50%"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commHouseNo" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Street" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commStreet" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b>State</b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commState" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.District" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commDistrict" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Mdl/Mcl" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commMandal" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Village" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commVillage" /></b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss"><b><fmt:message
								key="EHF.Pin" /></b></td>
							<td class="tbcellBorder"><b>&nbsp;<bean:write
								name="annualCheckUpForm" property="commPin" /></b></td>
						</tr>
					</table>
				
					</div>
					</fieldset>
					</td>
					<td width="15%" valign="top" >
					<fieldset  id='photoField'><legend
						class="legendStyle"><b><fmt:message key="EHF.Photo" /></b></legend>
					<table width="80%" height="80%" style="margin: auto auto">
						<tr>
							<td align="center"><logic:notEmpty name="annualCheckUpForm"
								property="photoUrl">
								<img id="patImage" src="common/showDocument.jsp" width="150"
									height="150" alt="NO DATA"
									onmouseover="resizePatImage('patImage','200','200')"
									onmouseout="resizePatImage('patImage','150','150')" />
							</logic:notEmpty> <logic:empty name="annualCheckUpForm" property="photoUrl">
								<img src="images/photonot.gif" width="150" height="150"
									alt="NO DATA" />
							</logic:empty></td>
						</tr>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td align="center"><b><fmt:message
						key="EHF.Title.CaseDetails" /></b></td>
				</tr>
			</table>
			<br>
			<table width="100%">
				<tr>
					<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.RegisteredHospital" /> </b></td>
					<td width="25%" class="tbcellBorder"><b><bean:write
						name="annualCheckUpForm" property="hospitalName" /></b></td>
					<td width="25%" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.DateAndTime" /> </b></td>
					<td width="25%" class="tbcellBorder"><b><bean:write
						name="annualCheckUpForm" property="dtTime" /></b></td>
				</tr>
			</table>
			
			<table class="tbheader" style="margin: 0 auto; width: 100%">
				<tr>
					<td width="100%" align="center"><b><fmt:message
						key="EHF.Title.MedicalDetails" /></b></td>
				</tr>
			</table>
			
			<table width="100%" class="medicalDetailsTable" border="0">
				<tr>
					<td colspan="2" valign="top" class="labelheading1 tbcellCss"><b><fmt:message
						key="EHF.HistoryOfPresentIllness" /></b> <font color="red">*</font></td>
					<td colspan="2" valign="top" rowspan="3" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.ExaminationFindings" /></b> <font color="red">*</font></legend>
					<table width="100%">
						<logic:iterate id="examinFnds" name="examinFndgsList">
							<tr>
								<!-- <td width="60%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="examinationFnd"
									styleId="examinationFnd" onclick="getSubFieldType(this)">
									<bean:write name="examinFnds" property="ID" /> 
								</html:multibox> <bean:write name="examinFnds" property="VALUE" /></td>  -->
								
<c:if test="${(examinFnds.ID ne 'GE14')}">
		 <td width="60%">&nbsp;&nbsp;
		 
 		 <html:multibox name="annualCheckUpForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       
       		<bean:write name="examinFnds" property="VALUE"/></td>
       			</c:if>	
       			
       			
       					<c:if test="${examinFnds.ID eq 'GE14'}">
		 <td width="60%">&nbsp;&nbsp;
		
 		 <html:multibox name="annualCheckUpForm"  property="examinationFnd" styleId="examinationFnd" onclick="getSubFieldType(this)">
      		 <bean:write name="examinFnds" property="ID"/>
       	</html:multibox>
       
       		BP mm/Hg</td>
       			</c:if>	
       			
								<td id="<bean:write name="examinFnds" property='ID'/>"
									width="39%"></td>
								<td width="1%"><input type="hidden"
									name="<bean:write name='examinFnds' property='VALUE'/>"
									id="<bean:write name='examinFnds' property='ID'/>h"
									value="<bean:write name='examinFnds' property='LVL'/>" /></td>
							</tr>
						</logic:iterate>
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td valign="top" colspan="2" class="tbcellBorder"><html:textarea
						name="annualCheckUpForm" property="presentHistory" 
						styleClass="form-control" styleId="presentHistory"
						style="resize:none;" 
						title="Enter History Of Present Illness"
						onkeydown="return maxLengthPress(this,4000,event)"
						onkeypress="return checkAlphaNumericCodes(event);"
						onchange="validateSpaces(this,'History Of Present Illness')" /></td>
				</tr>
				<tr>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.PersonalHistory" /></b> <font color="red">*</font></legend>
					<table width="100%">
						<logic:iterate id="phistory" name="personalHistoryList">
							<tr>
								<td width="43%"><html:multibox name="annualCheckUpForm"
									property="personalHistory" styleId="personalHistory"
									style="WIDTH:3em;" onclick="getSubListHistory(this,'NA')">
									<bean:write name="phistory" property="ID" />
								</html:multibox> <bean:write name="phistory" property="VALUE" /></td>
								<td id="<bean:write name="phistory" property='ID'/>" width="59%"
									height="1em"></td>
								<td width="1%"><input type="hidden"
									name="<bean:write name='phistory' property='VALUE'/>"
									id="<bean:write name='phistory' property='ID'/>p" /></td>
							</tr>
						</logic:iterate>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<!--  <tr>
      <td  id="habitsTd" colspan="3">
      </td></tr> -->
					</table>
					</fieldset>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.PastHistory" /></b> </legend>
					<table width="100%">
						<logic:iterate id="psthistory" name="pastHistoryList"
							indexId="cnt">
							<tr>
								<td width="50%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="pastHistory"
									styleId="pastHistory" onclick="getOtherText(this)"
									title="History Of Past Illness">
									<bean:write name="psthistory" property="ID" />
								</html:multibox> <bean:write name="psthistory" property="VALUE" /></td>
								<td id="<bean:write name="psthistory" property='ID'/>"
									width="60%"></td>
							</tr>
						</logic:iterate>

					</table>
					</fieldset>
					</td>
					<td colspan="2" class="tbcellBorder">
					<fieldset><legend class="legendStyle"><b><fmt:message
						key="EHF.FamilyHistory" /></b> </legend>
					<table width="100%">
						<logic:iterate id="fhistory" name="familyHistoryList">
							<tr>
								<td width="40%">&nbsp;&nbsp; <html:multibox
									name="annualCheckUpForm" property="familyHistory"
									styleId="familyHistory" onclick="getOtherText(this)">
									<bean:write name="fhistory" property="ID" />
								</html:multibox> <bean:write name="fhistory" property="VALUE" /></td>
								<td id="<bean:write name="fhistory" property='ID'/>" width="30%"></td>
							</tr>
						</logic:iterate>
					</table>
					</fieldset>
					</td>
				</tr>
			</table>
			
			
			
			
			
			
			
			<tr><td colspan="4"><b><fmt:message key="EHF.GenInvestigations"/></b> <font color="red" class="onlyAp">*&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr><td colspan="4"><table width="100%">
<tr>
<td class="labelheading1 tbcellCss" width="25%" id="InvBlockName"><b>Investigation Block Name</b><font color="red" class="onlyAp">*</font></td>
<td class="labelheading1 tbcellCss" width="25%" id="InvName"><b>Investigation Name</b><font color="red" class="onlyAp">*</font></td>
      
      
  
     
   
      
        
<td width="25%">&nbsp;</td></tr>
<tr>
<td class="tbcellBorder" id="InvBlckLst"">
<html:select name="annualCheckUpForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" onchange="getGenInvestigation();">
          <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
          <html:options property="ID" collection="investigationsList" labelProperty="VALUE"/>
         
  
          
    </html:select></td>
    <td class="tbcellBorder" id="invLst"><html:select name="annualCheckUpForm" property="genInvestigations" styleId="genInvestigations" style="WIDTH: 17em;"   onmouseover="addTooltip('genInvestigations')">
       <html:option value="-1"><fmt:message key="EHF.Select"/></html:option>
    </html:select>
    </td>
    </tr>
   
    <tr>
   
    
  <tr><td><br></td></tr>
 <tr>
<td colspan="4" style="align:center"><button class="btn btn-success" type="button" name="add" value="Add Tests" onclick="addGenInvestigationAhc();">Add Tests&nbsp;<i class="fa fa-plus"></i></button></td>
</tr></table></td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTableID" style="display:none" border="1">
   <tr><td colspan="4" class="labelheading1 tbcellCss">Previously Added Investigation</td></tr>
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr>
        <logic:present name="annualCheckUpForm" property="genInvList">
        <bean:size  id="genInvSize" name="annualCheckUpForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
		<%int j = 1;%>
		<script>var rowNo=2;</script>
        <logic:iterate id="gnInvlst" name="annualCheckUpForm" property="genInvList" indexId="sno" >
        <tr>  
      	<!-- <td width="10%"><%=j++ %></td>  -->
      	<td width="30%" ><bean:write name="gnInvlst" property="ACTION" /></td>       
        <td width="25%"><bean:write name="gnInvlst" property="VALUE" /></td> 
		<logic:empty name="gnInvlst" property="LVL">
		<script>
			var updateGenInvest="<bean:write name="gnInvlst" property="ACTION" />~<bean:write name="gnInvlst" property="VALUE" />~<bean:write name="gnInvlst" property="ID" />";
			updateGenInvestLst[updateGenInvestCount]=updateGenInvest;
			updateGenInvestCount++;
			var investTableId=document.getElementById('genTestTableID');   
			var newRow=investTableId.rows[rowNo];
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="25%"><input type="file"  id=<bean:write name="gnInvlst" property="ID" /> name="updateGenAttach['+updGenInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
			updGenInvestAttachId++;
		</script>
		</logic:empty>
		<logic:notEmpty  name="gnInvlst" property="LVL">
       	<td width="25%"><a href="javascript:fn_openAtachment('<bean:write name="gnInvlst" property="LVL" />','<bean:write name="gnInvlst" property="LVL" />');">View</a></td>
		</logic:notEmpty>
		<script>rowNo++;</script>
       	<td width="20%"><button class="btn btn-warning" type="button" value="Delete" name=<bean:write name="gnInvlst" property="ID" /> id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:deleteGenInvestAhc(this,'<bean:write name="gnInvlst" property="ID" />');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>
   </table>
</td></tr>
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%"  id="genTestTable" style="display:none" border="1">
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="25%" ><b><fmt:message key="EHF.TestName"/></b></td>   
        	<td width="25%"><b><fmt:message key="EHF.Attachment"/></b></td>
        	<td width="20%">&nbsp;</td></tr> 
  </table>
</td></tr>
			

			
			
			<table>
			<tr>
			<td class="tbcellBorder labelheading1"><b>Consultation Done By</b></td>
			<td class="tbcellBorder labelheading1"><input type="text" name="diagnosedBy" id="diagnosedBy"/></td>
			</tr>
			
			</table>
			
			
			
			
			
			
			
			
			
			
			
			
			<table  style="margin: 0 auto; width: 100%">
<tr>
		
<td align="center" colspan="3"> <button class="btn btn-primary"  type="button" id="Submit" onclick="javascript:fn_savePatientDetails('submit')">Submit&nbsp;<i class="fa fa-medkit"></i></button>
 <button class="btn btn-primary"  type="button" id="Save" onclick="javascript:fn_saveDetails()">Save&nbsp;<i class="fa fa-floppy-o"></i></button>
 <button class="btn btn-primary"  type="button" id="Reset" onclick="javascript:fn_resetAll()">Reset&nbsp;<i class="fa fa-eraser"></i></button></td>
<td width="20%"></td>
</tr>
</table>
</td></tr>
</table>
			
	
	<html:hidden name="annualCheckUpForm" property="patientNo" styleId="patientNo"/>
	<html:hidden name="annualCheckUpForm" property="examFndsVal" styleId="examFndsVal"/>
	<html:hidden name="annualCheckUpForm" property="personalHistVal" styleId="personalHistVal"/>
	<html:hidden name="annualCheckUpForm" property="personalHist" styleId="personalHist"/>

	</form>
	</div>
	</body>
	<logic:equal name ="annualCheckUpForm" property="medcoFlag" value="Y">
	<script>
	function resetMedicalDetails()
	{
		
	
		document.getElementById("presentHistory").value="";
		
		var pastHistory=document.forms[0].pastHistory;
		for(var i=0;i<pastHistory.length;i++)
		{
			if(pastHistory[i].checked)
			{
	        pastHistory[i].checked=false;
	        getOtherText(pastHistory[i]);
			}
		}
		var personalHistory=document.forms[0].personalHistory;
		for(var i=0;i<personalHistory.length;i++)
		{
			if(personalHistory[i].checked)
			{
	        personalHistory[i].checked=true;
	        getSubListHistory(personalHistory[i],'reset');
			}
		}
		var examinationFnd=document.forms[0].examinationFnd;
		for(var i=0;i<examinationFnd.length;i++)
		{
			if(examinationFnd[i].checked)
			{
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]);
			}
		}
		var familyHistory=document.forms[0].familyHistory;
		for(var i=0;i<familyHistory.length;i++)
		{
			if(familyHistory[i].checked)
			{
				familyHistory[i].checked=false;
				getOtherText(familyHistory[i]);
			}
		}
		
		}
	function fn_resetAll(){
		var msg='Do you want to reset?';
		 if(confirm(msg)==true)
			 resetMedicalDetails();
	}
	function fn_saveDetails(){
		var patId=document.getElementById("patientNo").value;
		
		//Mandatory Check validation For Personal History and its sublist
		var personalHistory=document.forms[0].personalHistory;
		var personalCount=0;
		var personalSubCount=0;
		var personalHistVal="";
		var genTestsCount=0;
	
		var lErrorMsg='';
		var lField='';
		
		for(var i=0;i<personalHistory.length;i++)
		{
			if(personalHistory[i].checked)
			{
			var personalHistValue=personalHistory[i].value;
			var personalHistSubList=document.forms[0].elements[personalHistValue];
			for(var j=0;j<personalHistSubList.length;j++)
			{
				if(personalHistSubList[j].checked)	
				{
				personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
				personalSubCount++;
				if(personalHistSubList[j].value=='PR5.1'){
					if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
					{
						
					}
					else if(document.forms[0].AllMed[0].checked){
						if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
							{
							personalHistVal = personalHistVal+",AllMed$AllMedYes";					
							}
							else {
							personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
							}
							
						}
					else
					{
					var AllMedList=document.forms[0].AllMed;
					for(var z=0;z<AllMedList.length;z++)
						{
						if(AllMedList[z].checked)
							{
							personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
							}
						}
					}
					if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
					{
						
					}
					else if(document.forms[0].AllSub[0].checked){
						if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
							{
							personalHistVal = personalHistVal+",AllSub$AllSubYes";				
							}
							else {
							personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
							}
							
						}
					else
					{
					var AllSubList=document.forms[0].AllSub;
					for(var z=0;z<AllSubList.length;z++)
						{
						if(AllSubList[z].checked)
							{
							personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
							}
						}
					}
				}
				
				if(personalHistSubList[j].value=='PR6.1')
					{
					if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
						{
							 
						}
					else
						{
						var alcoholSubList=document.forms[0].alcohol;
						for(var z=0;z<alcoholSubList.length;z++)
							{
							if(alcoholSubList[z].checked)
								{
								personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
								}
							}
						}
					if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
					{
						
					}
					else if(document.forms[0].tobacco[2].checked)
						{
						if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
							{
							 
							}
						else
							{
							personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
							}
						if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
						{
						
						}
						else
							{
							personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
							}
					}
					else
					{
						var tobaccoSubList=document.forms[0].tobacco;
						for(var z=0;z<tobaccoSubList.length;z++)
							{
							if(tobaccoSubList[z].checked)
								{
								personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
								}
							}
					}
							
					
						var drugUseSubList=document.forms[0].drugUse;
						for(var z=0;z<drugUseSubList.length;z++)
							{
							if(drugUseSubList[z].checked)
								{
								personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
								}
							}
					
					
						var betelNutSubList=document.forms[0].betelNut;
						for(var z=0;z<betelNutSubList.length;z++)
							{
							if(betelNutSubList[z].checked)
								{
								personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
								}
							}
					  var betelLeafSubList=document.forms[0].betelLeaf;
						for(var z=0;z<betelLeafSubList.length;z++)
							{
							if(betelLeafSubList[z].checked)
								{
								personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
								}
							}			
				  }
				}
			}
			
			if(personalSubCount==0)
				{
				
				}
			personalSubCount=0;
			personalHistVal = personalHistVal+"#";
		     }
			else
				{
				personalCount++;
				}
		}

		//Mandatory Check validation For Examination Findings and its sublist
		var examinFnds=document.forms[0].examinationFnd;
		var examinCount=0;
		var examinSubCount=0;
		var examFndsVal="";
		for(var i=0;i<examinFnds.length;i++)
		{
			if(examinFnds[i].checked)
			{
				//examinCount++;
				var examinFndsValue=examinFnds[i].value;		
				var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
				var subType=document.forms[0].elements[examinFndsValue].type;
				
				if(examinFndsValue=='GE11'){			
						var tempType= '';
						if(document.forms[0].temp[0].checked==true) tempType='C';
						if(document.forms[0].temp[1].checked==true) tempType='F';
						examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
						examFndsVal = examFndsVal+"#";					
					}
				else if(examinFndsValue=='GE14'){
					    examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
						examFndsVal = examFndsVal+"#";
					}
				else if(examinFndsValue=='GE15'){
						examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
					}
				else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){				
				if(subType=="text" )
				{
	            examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
				}
				else
				{
					var examinFndsSubList=document.forms[0].elements[examinFndsValue];
					
					
					for(var j=0;j<examinFndsSubList.length;j++)
					{
					if(examinFndsSubList[j].checked)	
						{
						examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
						examinSubCount++; 
						if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
							{
							var d;
								var dehydSubList=document.forms[0].dehydrationY;
								 for(d=0;d<dehydSubList.length;d++)
									{
									if(dehydSubList[d].checked)
										{
										examFndsVal = examFndsVal+dehydSubList[d].value;
										}
									}
							}
						}
					}
					examinSubCount=0;
				}
				examFndsVal = examFndsVal+"#";
			}
			}
			else
				{
				examinCount++;
				}
			}
			
		for (var x=0;x<pastHistory.length;x++)
		{
			if (pastHistory[x].checked)
			{
				if(pastHistory[x].value=="PH10")
				{
					if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
					{
						var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
						document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
					}
				}
			}
		}
		
		if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
		{
			//var fr=partial(focusBox,document.getElementById(lField));
			var fr=partial(focusFieldView,lField);
			alert(lErrorMsg);partial(focusFieldView,lField);
		    return;
		 }
		else
		{
		var saveFlag="Yes";
		var checkType="Save";
		var res=confirm('Do you want to Save?');
		
		if(res==true){
			registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType);
		}
		}
		}
	function fn_savePatientDetails(checkType)
	{
		var patId=document.getElementById("patientNo").value;
		var lErrorMsg='';
		var lField='';
		var genTestsCount=0;
		var ipTestsCount=0;
		var updTestsCount=0;


		 if(genInventList.length==0 && genOldList.length==0 ){
			 if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select General Investigations ";
					}
			        if(lField=='')
			        lField='genInvestigations';   
			}
			
	//Mandatory check for Present History
	if(document.forms[0].presentHistory.value=='' || document.forms[0].presentHistory.value==null){
		if(lErrorMsg==''){
	             lErrorMsg=lErrorMsg+"Enter History Of Present Illness\n ";
			}
	        if(lField=='')
	        lField='presentHistory';   
	}
	//Mandatory check for History Of Past Illness
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryCnt=0;
	for (var x=0;x<pastHistory.length;x++)
	{
	  if (pastHistory[x].checked)
	  {
	   pastHistoryCnt++;
	   if(pastHistory[x].value=="PH11")
		   {
		   if(document.getElementById("pastHistryOthr").value=='' || document.getElementById("pastHistryOthr").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter Other History Of Past Illness value ";
			      }
			        if(lField=='')
			        lField='pastHistryOthr'; 
			   }
		   }
		   if(pastHistory[x].value=="PH8")
		   {
		   if(document.getElementById("pastHistryCancers").value=='' || document.getElementById("pastHistryCancers").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Cancers value ";
			      }
			        if(lField=='')
			        lField='pastHistryCancers'; 
			   }
		   }
		   if(pastHistory[x].value=="PH12")
		   {
		   if(document.getElementById("pastHistryEndDis").value=='' || document.getElementById("pastHistryEndDis").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Endocrine Diseases value ";
			      }
			        if(lField=='')
			        lField='pastHistryEndDis'; 
			   }
		   }
		   if(pastHistory[x].value=="PH10")
		   {
		   if(document.getElementById("pastHistrySurg").value=='' || document.getElementById("pastHistrySurg").value==null)
			   {
			   if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Enter History Of Past Illness Surgeries value ";
			      }
			        if(lField=='')
			        lField='pastHistrySurg'; 
			   }
			 if(checkType=='SaveDTRS')
				{
				if(document.getElementById("pastHistrySurg").value!='' || document.getElementById("pastHistrySurg").value!=null)
					{
						var pastHistrySugValue=document.getElementById("pastHistrySurg").value;
						document.getElementById("pastHistrySurg").value=pastHistrySugValue.replace(/\n/g,' ');
					}
				}
		   }
	  }
	}
	//Mandatory Check validation For Personal History and its sublist
	var personalHistory=document.forms[0].personalHistory;
	var personalCount=0;
	var personalSubCount=0;
	var personalHistVal="";

	for(var i=0;i<personalHistory.length;i++)
	{
		if(personalHistory[i].checked)
		{
		//personalCount++;
		var personalHistValue=personalHistory[i].value;
		var personalHistSubList=document.forms[0].elements[personalHistValue];
		for(var j=0;j<personalHistSubList.length;j++)
		{
			if(personalHistSubList[j].checked)	
			{
			personalHistVal = personalHistVal+personalHistValue+"~"+personalHistSubList[j].value;	
			personalSubCount++;
			if(personalHistSubList[j].value=='PR5.1'){
				if (!document.forms[0].AllMed[0].checked && !document.forms[0].AllMed[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Medicine) Options ";
			     	 }
			        if(lField=='')
			        lField='AllMed';   
				}
				else if(document.forms[0].AllMed[0].checked){
					if(document.getElementById("AllMedRemrk").value==''|| document.getElementById("AllMedRemrk").value==null)
						{
							if(lErrorMsg==''){
						        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Medicine) Specify Reason ";
						      }
							 if(lField=='') lField='AllMedRemrk'; 						
						}
						else {
						personalHistVal = personalHistVal+",AllMed$AllMedYes(AllMedRemrk@"+document.getElementById("AllMedRemrk").value;
						}
						
					}
				else
				{
				var AllMedList=document.forms[0].AllMed;
				for(var z=0;z<AllMedList.length;z++)
					{
					if(AllMedList[z].checked)
						{
						personalHistVal = personalHistVal+",AllMed$"+AllMedList[z].value;
						}
					}
				}
				if (!document.forms[0].AllSub[0].checked && !document.forms[0].AllSub[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Known Allergies(Allergies to Substance other than medicine) Options ";
			     	 }
			        if(lField=='')
			        lField='AllSub';   
				}
				else if(document.forms[0].AllSub[0].checked){
					if(document.getElementById("AllSubRemrk").value==''|| document.getElementById("AllSubRemrk").value==null)
						{
							if(lErrorMsg==''){
						        lErrorMsg=lErrorMsg+"Enter Personal History Known Allergies(Allergies to Substance other than Medicine) Specify Reason ";
						      }
							 if(lField=='') lField='AllSubRemrk'; 						
						}
						else {
						personalHistVal = personalHistVal+",AllSub$AllSubYes(AllSubRemrk@"+document.getElementById("AllSubRemrk").value;
						}
						
					}
				else
				{
				var AllSubList=document.forms[0].AllSub;
				for(var z=0;z<AllSubList.length;z++)
					{
					if(AllSubList[z].checked)
						{
						personalHistVal = personalHistVal+",AllSub$"+AllSubList[z].value;
						}
					}
				}
			}
			
			if(personalHistSubList[j].value=='PR6.1')
				{
				if (!document.forms[0].alcohol[0].checked && !document.forms[0].alcohol[1].checked && !document.forms[0].alcohol[2].checked)
					{
						if(lErrorMsg==''){
				         lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Alcohol options ";
				      	}
				        if(lField=='')
				        lField='alcohol';   
					}
				else
					{
					var alcoholSubList=document.forms[0].alcohol;
					for(var z=0;z<alcoholSubList.length;z++)
						{
						if(alcoholSubList[z].checked)
							{
							personalHistVal = personalHistVal+"(Alcohol$"+alcoholSubList[z].value;
							}
						}
					}
				if (!document.forms[0].tobacco[0].checked && !document.forms[0].tobacco[1].checked && !document.forms[0].tobacco[2].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Tobacco Options ";
			     	 }
			        if(lField=='')
			        lField='tobacco';   
				}
				else if(document.forms[0].tobacco[2].checked)
					{
					if(document.getElementById("packNo").value=='' || document.getElementById("packNo").value==null)
						{
						if(lErrorMsg==''){
					        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking PackNo ";
					      }
					     if(lField=='')
					        lField='packNo';   
						}
					else
						{
						personalHistVal = personalHistVal+",Tobacco$Smoking(PackNo@"+document.getElementById("packNo").value;
						}
					if(document.getElementById("smokeYears").value=='' || document.getElementById("smokeYears").value==null)
					{
					if(lErrorMsg==''){
				        lErrorMsg=lErrorMsg+"Enter Personal History Habits/Addictions Tobacco Smoking Years ";
				      }
				      if(lField=='')
				        lField='smokeYears';   
					}
					else
						{
						personalHistVal = personalHistVal+"*Years@"+document.getElementById("smokeYears").value+")";
						}
				}
				else
				{
					var tobaccoSubList=document.forms[0].tobacco;
					for(var z=0;z<tobaccoSubList.length;z++)
						{
						if(tobaccoSubList[z].checked)
							{
							personalHistVal = personalHistVal+",Tobacco$"+tobaccoSubList[z].value;
							}
						}
				}
						
				if (!document.forms[0].drugUse[0].checked && !document.forms[0].drugUse[1].checked)
				{
					if(lErrorMsg==''){
			       lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Drug Use Options ";
			     	 }
			        if(lField=='')
			        lField='drugUse';   
				}
				else
				{
					var drugUseSubList=document.forms[0].drugUse;
					for(var z=0;z<drugUseSubList.length;z++)
						{
						if(drugUseSubList[z].checked)
							{
							personalHistVal = personalHistVal+",DrugUse$"+drugUseSubList[z].value;
							}
						}
				}
				if (!document.forms[0].betelNut[0].checked && !document.forms[0].betelNut[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Nut Options ";
			     	 }
			        if(lField=='')
			        lField='betelNut';   
				}
				else
				{
					var betelNutSubList=document.forms[0].betelNut;
					for(var z=0;z<betelNutSubList.length;z++)
						{
						if(betelNutSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelNut$"+betelNutSubList[z].value;
							}
						}
				}
				if (!document.forms[0].betelLeaf[0].checked && !document.forms[0].betelLeaf[1].checked)
				{
					if(lErrorMsg==''){
			        lErrorMsg=lErrorMsg+"Select Personal History Habits/Addictions Betel Leaf Options ";
			      	}
			        if(lField=='')
			        lField='betelLeaf';   
				}
				else
				{
					var betelLeafSubList=document.forms[0].betelLeaf;
					for(var z=0;z<betelLeafSubList.length;z++)
						{
						if(betelLeafSubList[z].checked)
							{
							personalHistVal = personalHistVal+",BetelLeaf$"+betelLeafSubList[z].value+")";
							}
						}
				}
			  }
			}
		}
		
		if(personalSubCount==0)
			{
			if(lErrorMsg==''){
					lErrorMsg=lErrorMsg+"Select Personal History "+personalHistSubList[0].name+" Options ";
					}
	        if(lField=='')
	        lField=personalHistValue;  
			}
		personalSubCount=0;
		personalHistVal = personalHistVal+"#";
	     }
		else
			{
			personalCount++;
			}
	}
	if(personalCount>0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select All Personal History Options ";
			}
	        if(lField=='')
	        lField='personalHistory';  
		}
	//Mandatory Check validation For Examination Findings and its sublist
	var examinFnds=document.forms[0].examinationFnd;
	var examinCount=0;
	var examinSubCount=0;
	var examFndsVal="";
	for(var i=0;i<examinFnds.length;i++)
	{
		if(examinFnds[i].checked)
		{
			//examinCount++;
			var examinFndsValue=examinFnds[i].value;		
			var examinFndsName=document.forms[0].elements[examinFndsValue].name;		
			var subType=document.forms[0].elements[examinFndsValue].type;
			
			if(examinFndsValue=='GE11'){
				if(document.forms[0].temp[0].checked==false && document.forms[0].temp[1].checked==false){
					if(lErrorMsg==''){
						lErrorMsg=lErrorMsg+"Please select C or F option in "+examinFndsName+"";
						}
						if(lField=='')
						lField=examinFndsValue; 
					}
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					var tempType= '';
					if(document.forms[0].temp[0].checked==true) tempType='C';
					if(document.forms[0].temp[1].checked==true) tempType='F';
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+tempType;
					examFndsVal = examFndsVal+"#";
					}
				}
			else if(examinFndsValue=='GE14'){
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null
						||document.getElementById("BP1").value==""||document.getElementById("BP1").value==null )
					
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP1").value;
					examFndsVal = examFndsVal+"#";
					}
				}
			else if(examinFndsValue=='GE15'){
				if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null||
						document.getElementById("BP2").value==""||document.getElementById("BP2").value==null)
					
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
				}
				if(lField=='')
				lField=examinFndsValue; 
				}
				else{
					examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value+"/"+document.getElementById("BP2").value;
	           }
				}
			else if(examinFndsValue!='GE14' && examinFndsValue!='GE15'){
				
			if(subType=="text" )
			{
			if(document.forms[0].elements[examinFndsValue].value=="" || document.forms[0].elements[examinFndsValue].value==null)
			{
			if(lErrorMsg==''){
			lErrorMsg=lErrorMsg+"Enter Examination Findings "+examinFndsName+"";
			}
			if(lField=='')
			lField=examinFndsValue; 
			}
			else{
				
				examFndsVal = examFndsVal+examinFndsValue+"~"+document.forms[0].elements[examinFndsValue].value;
				
				}
			}
			else
			{
				var examinFndsSubList=document.forms[0].elements[examinFndsValue];
				
				
				for(var j=0;j<examinFndsSubList.length;j++)
				{
				if(examinFndsSubList[j].checked)	
					{
					examFndsVal = examFndsVal+examinFndsValue+"~"+examinFndsSubList[j].value;
					examinSubCount++; 
					if(examinFndsSubList[j].name=="Dehydration" && examinFndsSubList[j].value=="Y")
						{
						if(!document.forms[0].dehydrationY[0].checked && !document.forms[0].dehydrationY[1].checked && !document.forms[0].dehydrationY[2].checked)
							{
							if(lErrorMsg==''){
								lErrorMsg=lErrorMsg+"Select Examination Findings Dehydration Sub Options ";
								}
								if(lField=='')
								lField='dehydrationY'; 
							}
						else
							{
							var d;
							var dehydSubList=document.forms[0].dehydrationY;
							 for(d=0;d<dehydSubList.length;d++)
								{
								if(dehydSubList[d].checked)
									{
									examFndsVal = examFndsVal+dehydSubList[d].value;
									}
								}
	 
							}
						}
					}
				}
				if(examinSubCount==0)
				{
				if(lErrorMsg==''){
				lErrorMsg=lErrorMsg+"Select Examination Findings "+examinFndsSubList[0].name+" Options ";
				}
				if(lField=='')
				lField=examinFndsValue;  
				}
				examinSubCount=0;
			}
			examFndsVal = examFndsVal+"#";
		}
		}
		else
			{
			examinCount++;
			}
		}
		
	if(examinCount>0)
		{
		if(lErrorMsg==''){
	        lErrorMsg=lErrorMsg+"Select All Examination Findings Options ";
			}
	        if(lField=='')
	        lField='examinationFnd'; 
		}
	//Mandatory Check validation For Family History 
	var familyHistory=document.forms[0].familyHistory;
	var familyHistCount=0;
	for(var i=0;i<familyHistory.length;i++)
	{
		if(familyHistory[i].checked)
		{
		familyHistCount++;
		if(familyHistory[i].value=="FH11")
		   {
		   if(document.getElementById("familyHistoryOthr").value=='' || document.getElementById("familyHistoryOthr").value==null)
			   {
			   if(lErrorMsg==''){
			     lErrorMsg=lErrorMsg+"Enter Other Family History Value ";
				 }
			        if(lField=='')
			        lField='familyHistoryOthr'; 
			   }
		   }
		}
	}
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		alert(lErrorMsg);
		focusBox(document.getElementById(lField));
		return;
	 }




/*
	 
	if(document.getElementById("haemoglobin").value==null || document.getElementById("haemoglobin").value==''){
		lErrorMsg=lErrorMsg+"Please enter haemoglobin count. ";
		if(lField=='')
	        lField='haemoglobin';
	}
	else if(document.getElementById("tlc").value==null || document.getElementById("tlc").value==''){
		lErrorMsg=lErrorMsg+"Please enter Total Leukocyte count ";
		if(lField=='')
	        lField='tlc';
	}
	else if(document.getElementById("polymorphs").value==null || document.getElementById("polymorphs").value==''){
		lErrorMsg=lErrorMsg+"Please enter polymorphs count ";
		if(lField=='')
	        lField='polymorphs';
	}
	else if(document.getElementById("lymphocytes").value==null || document.getElementById("lymphocytes").value==''){
		lErrorMsg=lErrorMsg+"Please enter lymphocytes count ";
		if(lField=='')
	        lField='lymphocytes';
	}
	else if(document.getElementById("eosinophils").value==null || document.getElementById("eosinophils").value==''){
		lErrorMsg=lErrorMsg+"Please enter eosiophils count ";
		if(lField=='')
	        lField='eosinophils';
	}
	else if(document.getElementById("basophils").value==null || document.getElementById("basophils").value==''){
		lErrorMsg=lErrorMsg+"Please enter basophils count ";
		if(lField=='')
	        lField='basophils';
	}
	else if(document.getElementById("monocytes").value==null || document.getElementById("monocytes").value==''){
		lErrorMsg=lErrorMsg+"Please enter monocytes count ";
		if(lField=='')
	        lField='monocytes';
	}
	else if(document.getElementById("rbc").value==null || document.getElementById("rbc").value==''){
		lErrorMsg=lErrorMsg+"Please enter RBC count ";
		if(lField=='')
	        lField='rbc';
	}
	else if(document.getElementById("wbc").value==null || document.getElementById("wbc").value==''){
		lErrorMsg=lErrorMsg+"Please enter WBC count ";
		if(lField=='')
	        lField='wbc';
	}
	else if(document.getElementById("platelets").value==null || document.getElementById("platelets").value==''){
		lErrorMsg=lErrorMsg+"Please enter platelets count. ";
		if(lField=='')
	        lField='platelets';
	}
	else if(document.getElementById("bloodGroup").value==null || document.getElementById("bloodGroup").value==''){
		lErrorMsg=lErrorMsg+"Please enter blood group. ";
		if(lField=='')
	        lField='bloodGroup';
	}

	else if(document.getElementById("esr").value==null || document.getElementById("esr").value==''){
		lErrorMsg=lErrorMsg+"Please enter Erythrocyte Sedimentation Rate. ";
		if(lField=='')
	        lField='esr';
	}
	else if(document.getElementById("fastingSugar").value==null || document.getElementById("fastingSugar").value==''){
		lErrorMsg=lErrorMsg+"Please enter Fasting sugar. ";
		if(lField=='')
	        lField='fastingSugar';
	}
	else if(document.getElementById("postPrandialSugar").value==null || document.getElementById("postPrandialSugar").value==''){
		lErrorMsg=lErrorMsg+"Please enter Post prandial sugar. ";
		if(lField=='')
	        lField='postPrandialSugar';
	}
	else if(document.getElementById("glycosylatedHaemoglobin").value==null || document.getElementById("glycosylatedHaemoglobin").value==''){
		lErrorMsg=lErrorMsg+"Please enter Glycosylated Haemoglobin ";
		if(lField=='')
	        lField='glycosylatedHaemoglobin';
	}
	else if(document.getElementById("hbsAg").value==null || document.getElementById("hbsAg").value==''){
		lErrorMsg=lErrorMsg+"Please enter HbsAg(surface antigen of Hepatitis B virus)";
		if(lField=='')
	        lField='hbsAg';
	}
	else if(document.getElementById("totalCholesterol").value==null || document.getElementById("totalCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter Total Cholesterol ";
		if(lField=='')
	        lField='totalCholesterol';
	}
	else if(document.getElementById("hdlCholesterol").value==null || document.getElementById("hdlCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter HDL Cholesterol  ";
		if(lField=='')
	        lField='hdlCholesterol';
	}
	else if(document.getElementById("ldlCholesterol").value==null || document.getElementById("ldlCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter LDL Cholesterol ";
		if(lField=='')
	        lField='ldlCholesterol';
	}
	else if(document.getElementById("vldlCholesterol").value==null || document.getElementById("vldlCholesterol").value==''){
		lErrorMsg=lErrorMsg+"Please enter VLDL Cholesterol ";
		if(lField=='')
	        lField='vldlCholesterol';
	}
	else if(document.getElementById("triglycerides").value==null || document.getElementById("triglycerides").value==''){
		lErrorMsg=lErrorMsg+"Please enter Triglycerides. ";
		if(lField=='')
	        lField='triglycerides';
	}
	else if(document.getElementById("bloodUrea").value==null || document.getElementById("bloodUrea").value==''){
		lErrorMsg=lErrorMsg+"Select enter Blood Urea. ";
		if(lField=='')
	        lField='bloodUrea';
	}

	else if(document.getElementById("sCreatinine").value==null || document.getElementById("sCreatinine").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Creatinine. ";
		if(lField=='')
	        lField='sCreatinine';
	}
	else if(document.getElementById("sUricAcid").value==null || document.getElementById("sUricAcid").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Uric Acid ";
		if(lField=='')
	        lField='sUricAcid';
	}
	else if(document.getElementById("serumElectrolytes").value==null || document.getElementById("serumElectrolytes").value==''){
		lErrorMsg=lErrorMsg+"Please enter Serum Electrolytes ";
		if(lField=='')
	        lField='serumElectrolytes';
	}
	else if(document.getElementById("serumCreatinine").value==null || document.getElementById("serumCreatinine").value==''){
		lErrorMsg=lErrorMsg+"Please enter Serum Creatinine ";
		if(lField=='')
	        lField='serumCreatinine';
	}
	else if(document.getElementById("sgot").value==null || document.getElementById("sgot").value==''){
		lErrorMsg=lErrorMsg+"Please enter SGOT(Serum Glutamic Oxaloacetic Transaminase)  ";
		if(lField=='')
	        lField='sgot';
	}
	else if(document.getElementById("sgpt").value==null || document.getElementById("sgpt").value==''){
		lErrorMsg=lErrorMsg+"Please enter SGPT(Serum Glutamic Pyruvic Transaminase)  ";
		if(lField=='')
	        lField='sgpt';
	}
	else if(document.getElementById("sTotalBilirubin").value==null || document.getElementById("sTotalBilirubin").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Bilirubin(total) ";
		if(lField=='')
	        lField='sTotalBilirubin';
	}
	else if(document.getElementById("sDirectBilirubin").value==null || document.getElementById("sDirectBilirubin").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.Bilirubin(direct)  ";
		if(lField=='')
	        lField='sDirectBilirubin';
	}
	else if(document.getElementById("liverSgot").value==null || document.getElementById("liverSgot").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.G.O.T ";
		if(lField=='')
	        lField='liverSgot';
	}
	else if(document.getElementById("liverSgpt").value==null || document.getElementById("liverSgpt").value==''){
		lErrorMsg=lErrorMsg+"Please enter S.G.P.T ";
		if(lField=='')
	        lField='liverSgpt';
	}
	else if(document.getElementById("urineColor").value==null || document.getElementById("urineColor").value==''){
		lErrorMsg=lErrorMsg+"Please enter urine color. ";
		if(lField=='')
	        lField='urineColor';
	}
	else if(document.getElementById("urineAlbumin").value==null || document.getElementById("urineAlbumin").value==''){
		lErrorMsg=lErrorMsg+"Select enter urine albumin. ";
		if(lField=='')
	        lField='urineAlbumin';
	}
	else if(document.getElementById("urineSugar").value==null || document.getElementById("urineSugar").value==''){
		lErrorMsg=lErrorMsg+"Please enter urine sugar. ";
		if(lField=='')
	        lField='urineSugar';
	}
	else if(document.getElementById("urineMicroscopicExam").value==null || document.getElementById("urineMicroscopicExam").value==''){
		lErrorMsg=lErrorMsg+"Select enter urine microscopin examination result. ";
		if(lField=='')
	        lField='urineMicroscopicExam';
	}

	else if(document.getElementById("abdomenUltrasound").value==null || document.getElementById("abdomenUltrasound").value==''){
		lErrorMsg=lErrorMsg+"Please enter ultrasound abdomen report findings ";
		if(lField=='')
	        lField='abdomenUltrasound';
	}
	else if(document.getElementById("chestXrayPAView").value==null || document.getElementById("chestXrayPAView").value==''){
		lErrorMsg=lErrorMsg+"Please enter chest Xray PA view report findings ";
		if(lField=='')
	        lField='chestXrayPAView';
	}
	else if(document.getElementById("pulmonaryFunction").value==null || document.getElementById("pulmonaryFunction").value==''){
		lErrorMsg=lErrorMsg+"Select enter pulmonary function report findings. ";
		if(lField=='')
	        lField='pulmonaryFunction';
	}
	else if(document.getElementById("ecg").value==null || document.getElementById("ecg").value==''){
		lErrorMsg=lErrorMsg+"Please enter ECG report findings ";
		if(lField=='')
	        lField='ecg';
	}
	else if(document.getElementById("twodEcho").value==null || document.getElementById("twodEcho").value==''){
		lErrorMsg=lErrorMsg+"Please enter 2D Echo report findings ";
		if(lField=='')
	        lField='twodEcho';
	}
	
	else if(document.getElementById("retinopathy").value==null || document.getElementById("retinopathy").value==''){
		lErrorMsg=lErrorMsg+"Please enter retinopathy report findings. ";
		if(lField=='')
	        lField='retinopathy';
	}
	else if(document.getElementById("fundoscopy").value==null || document.getElementById("fundoscopy").value==''){
		lErrorMsg=lErrorMsg+"Select enter fundoscopy report findings. ";
		if(lField=='')
	        lField='fundoscopy';
	}
	else if(document.getElementById("audiometry").value==null || document.getElementById("audiometry").value==''){
		lErrorMsg=lErrorMsg+"Please enter audiometry report findings. ";
		if(lField=='')
	        lField='audiometry';
	}
	else if(document.forms[0].haemoglobinReport[0].checked==false && document.forms[0].haemoglobinReport[1].checked==false ){
		lErrorMsg=lErrorMsg+"Please select haemoglobin level of the officer. ";
		if(lField=='')
	        lField='haemoglobinReport';

	}
	else if(document.forms[0].bloodSugarReport[0].checked==false && document.forms[0].bloodSugarReport[1].checked==false && document.forms[0].bloodSugarReport[2].checked==false && document.forms[0].bloodSugarReport[3].checked==false){
		lErrorMsg=lErrorMsg+"Please select Blood sugar report of the officer. ";
		if(lField=='')
	        lField='bloodSugarReport';

	}
	else if(document.forms[0].cholesterolReport[0].checked==false && document.forms[0].cholesterolReport[1].checked==false && document.forms[0].cholesterolReport[2].checked==false ){
		lErrorMsg=lErrorMsg+"Please select cholesterol report of the officer. ";
		if(lField=='')
	        lField='cholesterolReport';

	}
	else if(document.forms[0].liverFunctionReport[0].checked==false && document.forms[0].liverFunctionReport[1].checked==false && document.forms[0].liverFunctionReport[2].checked==false && document.forms[0].liverFunctionReport[3].checked==false){
		lErrorMsg=lErrorMsg+"Please select liver functioning report of the officer. ";
		if(lField=='')
	        lField='liverFunctionReport';

	}
	else if(document.forms[0].kidneyReport[0].checked==false && document.forms[0].kidneyReport[1].checked==false ){
		lErrorMsg=lErrorMsg+"Please select Kidney status of the officer. ";
		if(lField=='')
	        lField='kidneyReport';

	}
	else if(document.forms[0].cardiacReport[0].checked==false && document.forms[0].cardiacReport[1].checked==false  && document.forms[0].cardiacReport[2].checked==false  && document.forms[0].cardiacReport[3].checked==false ){
		lErrorMsg=lErrorMsg+"Please select cardiac status of the officer. ";
		if(lField=='')
	        lField='cardiacReport';

	}
	
	else if(document.getElementById("overallHealthRemarks").value==null || document.getElementById("overallHealthRemarks").value==''){
		lErrorMsg=lErrorMsg+"Please enter Overall Health remarks of the officer. ";
		if(lField=='')
	        lField='overallHealthRemarks';
	}
	
	else if(document.getElementById("healthGrade").value==""){
		lErrorMsg=lErrorMsg+"Please select health grade. ";
		if(lField=='')
	        lField='healthGrade';
	}

	*/
	if(checkType=='submit')
	{
		
	if(lErrorMsg!=null && (lErrorMsg!='' || lErrorMsg!=undefined) && lErrorMsg.length>0)
	{
		alert(lErrorMsg);
		focusBox(document.getElementById(lField));
		return;
	 }
	
	 
	else
	{
		var saveFlag="Submit";
		if(confirm('Do you want to register Annual health checkup case?')==true)
			registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType);
			
	}
	}
	}  
	function registerCase(personalHistVal,examFndsVal,patId,saveFlag,checkType)
	{
	    var selInvData='';
		 var selGenInvData='';
		 var updateGenInvData='';
		 
			document.forms[0].personalHistVal.value=personalHistVal;
	   		document.forms[0].examFndsVal.value=examFndsVal;
	   		document.getElementById("Submit").disabled=true;
			document.getElementById("Submit").className='butdisable';
			document.getElementById("Save").disabled=true;
			document.getElementById("Save").className='butdisable';
			document.getElementById("Reset").disabled=true;
			document.getElementById("Reset").className='butdisable';
/*added by  venkatesh for saving consultation data*/

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

			alert(selGenInvData);
			var url="./annualCheckUpAction.do?actionFlag=saveAhcDetails&patientId="+patId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&saveFlag="+saveFlag+"&checkType="+checkType;
			
			
			
			/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
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
	function maxLengthPaste(field,maxChars,event)
	{
	      event.returnValue=false;
	      if(window.clipboardData)
	    	  {
	      		if((field.value.length +  window.clipboardData.getData("Text").length) > maxChars) 
				{
	      			alert("Characters should not exceed 3000");
	        	return false;
	        	}
	      		event.returnValue=true;
	    	  }
	      if (event.clipboardData) 
	      {
	    	if((field.value.length + event.clipboardData.getData('text/plain').length) > maxChars)
	    		{
	    		alert("Characters should not exceed 3000");
	        	return false;
	        	}
	      		event.returnValue=true;
	      }
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
	function pasteInterceptUltra(evt)
	 {  
	var input=document.getElementById('abdomenUltrasound');
	maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptChestXray(evt)
	{
		var input=document.getElementById('chestXrayPAView');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptPulmonary(evt)
	{
		var input=document.getElementById('pulmonaryFunction');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptEcg(evt)
	{
		var input=document.getElementById('ecg');
		maxLengthPaste(input,4000,evt); 
	}
	function pasteIntercept2D(evt)
	 {  
	var input=document.getElementById('twodEcho');
	maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptTmt(evt)
	{
		var input=document.getElementById('treadmillTest');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptRetinopathy(evt)
	{
		var input=document.getElementById('retinopathy');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptFundoscopy(evt)
	{
		var input=document.getElementById('fundoscopy');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptAudiometry(evt)
	{
		var input=document.getElementById('audiometry');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptHealth(evt)
	{
		var input=document.getElementById('overallHealthRemarks');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptPresent(evt)
	{
		var input=document.getElementById('presentHistory');
		maxLengthPaste(input,4000,evt); 
	}
	function  pasteInterceptSummary(evt)
	{
		var input=document.getElementById('summary');
		maxLengthPaste(input,4000,evt); 
	}
	var personalHistory=document.forms[0].personalHistory;
	for(var i=0;i<personalHistory.length;i++)
	    {
		if('${otherFindings.personalHis}'==''){
			personalHistory[i].checked=true;
			  getSubListHistory(personalHistory[i],'NA');
		}
		else{
			if('${otherFindings.personalHis}'.indexOf(personalHistory[i].value,0)!=-1){
				personalHistory[i].checked=true;
	            getSubListHistory(personalHistory[i],'NA'); 
		}
	     }
	    }
		var examinationFnd=document.forms[0].examinationFnd;
		for(var i=0;i<examinationFnd.length;i++)
		{
			if('${otherFindings.examFindg}'==''){
				examinationFnd[i].checked=true;
				getSubFieldType(examinationFnd[i]);
			}
			else{
				if('${otherFindings.examFindg}'.indexOf(examinationFnd[i].value,0)!=-1){
					examinationFnd[i].checked=true;
					getSubFieldType(examinationFnd[i]); 
			}
		     }
		}

	
	var pastHistory=document.forms[0].pastHistory;
	var pastHistoryVal='${otherFindings.pastIllness}'.split("~");
	
	for(var i=0;i<pastHistory.length;i++)
	    {
		for(var j=0;j<pastHistoryVal.length;j++)
		{
		if(pastHistory[i].value==pastHistoryVal[j])
		{
			pastHistory[i].checked=true;
				if(pastHistory[i].value=='PH11' || pastHistory[i].value=='PH8' || pastHistory[i].value=='PH12' || pastHistory[i].value=='PH10'){
				getOtherText(pastHistory[i]);
				if(pastHistory[i].value=='PH11' && '${otherFindings.pastIllenesOthr}'!='')
					document.getElementById('pastHistryOthr').value='${otherFindings.pastIllenesOthr}';
				if(pastHistory[i].value=='PH8' && '${otherFindings.pastIllenesCancers}'!='')
					document.getElementById('pastHistryCancers').value='${otherFindings.pastIllenesCancers}';
				if(pastHistory[i].value=='PH12' && '${otherFindings.pastIllenesEndDis}'!='')
					document.getElementById('pastHistryEndDis').value='${otherFindings.pastIllenesEndDis}';
				if(pastHistory[i].value=='PH10' && '${otherFindings.pastIllenesSurg}'!='')
					document.getElementById('pastHistrySurg').value='${otherFindings.pastIllenesSurg}';
				}
		}}}
	var familyHistory=document.forms[0].familyHistory;
	var famHistoryVal;
	if('${otherFindings.familyHis}'!='')
	{
		famHistoryVal='${otherFindings.familyHis}'.split("~");
		for(var i=0;i<familyHistory.length;i++)
	    {
			for(var j=0;j<famHistoryVal.length;j++)
			{
				if(familyHistory[i].value==famHistoryVal[j])
				{
					familyHistory[i].checked=true;
					if(familyHistory[i].value=='FH11'){	
						getOtherText(familyHistory[i]);
						if('${otherFindings.familyHistoryOthr}'!='')
							document.getElementById('otherFindings.familyHistoryOthr').value='${otherFindings.familyHistoryOthr}';
					}
				}
			}
		} 
	}
	
	if('${otherFindings.lstPerHis}'!=null && '${otherFindings.lstPerHis}'.indexOf("PR5.1",0)!=-1)
		{ 
			var KnownAllg='<table width="100%" border="1"><tr><td>'+
		'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
		'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
		'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
		'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
		'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
		'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
	    
		document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
		}
		if('${otherFindings.lstPerHis}'!=null && '${otherFindings.lstPerHis}'.indexOf("PR6.1",0)!=-1)
	{
	var personalHabits='<table width="100%" border="1"><tr><td>'+
	'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
	'<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
	'<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
		'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	'<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	'<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	'<div id="smokingTd" style="display:none">'+
	'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	'<tr><td>Drug Use:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
	'<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
	'<tr><td>Betel leaf:<input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" title="Yes"/>Yes'+
	'<input type="radio" name="betelLeaf" id="betelLeaf" value="No" title="No"/>No</td></tr></table>';
	document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
	}

		var personalHistVal= '${otherFindings.lstPerHis}';	
		personalHistVal=personalHistVal.replace("[","");
		personalHistVal=personalHistVal.replace("]","");
		var perHistListing=personalHistVal.split(",");
		for(i=0;i<perHistListing.length;i++){
			var pr= perHistListing[i];
			var prval=pr.substring(0,6);
			prval1=pr.substring(0,5);
			var prval2=prval.substring(prval.length-1,prval.length);
			var val=parseInt(prval2,10)-1;
			if(prval1.trim()=="PR1.1"){
				document.forms[0].PR1[val].checked='checked';
			}
			if(prval1.trim()=="PR2."){
				document.forms[0].PR2[val].checked='checked';
			}
			if(prval1.trim()=="PR3."){
				document.forms[0].PR3[val].checked='checked';
			}
			if(prval1.trim()=='PR4.'){
				document.forms[0].PR4[val].checked='checked';
			}
			if(prval1.trim()=='PR5.'){
				document.forms[0].PR5[val].checked='checked';
			}
			if(prval1.trim()=='PR6.'){
				document.forms[0].PR6[val].checked='checked';
			}
			if(prval1.trim()=='PR7.'){
				document.forms[0].PR7[val].checked='checked';
			}

		}
		
	
	var addition='${otherFindings.addiction}';var additionKnown='${otherFindings.allergy}';
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
						if(spitedY.length>1){
						var valueY = spitedY[1].split("@");					
						document.getElementById("AllMedRemrk").value=valueY[1];
						}
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
						if(spitedY.length>1){
							var valueY = spitedY[1].split("@");					
							document.getElementById("AllSubRemrk").value=valueY[1];
							}
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
			    else if(addtn[0]=='BetelLeaf')
			    {
			    	addtn[1] = addtn[1].replace(")","");
			    	if(addtn[1]=='Yes')
		          	  document.forms[0].betelLeaf[0].checked='checked';
		            else  if(addtn[1]=='No')
		          	  document.forms[0].betelLeaf[1].checked='checked';
			    }
		    }
		}	
		
		if('${otherFindings.height}'!='NA' && '${otherFindings.height}'!=''){
			document.forms[0].GE1.value='${otherFindings.height}';
			heightVar='${otherFindings.height}';
		}
		if('${otherFindings.weight}'!='NA' && '${otherFindings.weight}'!=''){
			document.forms[0].GE2.value='${otherFindings.weight}';
			weightVar='${otherFindings.weight}';
			}
		if('${otherFindings.bmi}'!='NA' && '${otherFindings.bmi}'!='')
			document.forms[0].GE3.value='${otherFindings.bmi}';
		if('${otherFindings.pallor}'!=''){
				if('${otherFindings.pallor}'=='Y')
		      	  document.forms[0].GE4[0].checked='checked';
		        else  if('${otherFindings.pallor}'=='N')
		      	  document.forms[0].GE4[1].checked='checked';
			}
		if('${otherFindings.cyanosis}'!=''){
			if('${otherFindings.cyanosis}'=='Y')
	      	  document.forms[0].GE5[0].checked='checked';
	        else  if('${otherFindings.cyanosis}'=='N')
	      	  document.forms[0].GE5[1].checked='checked';
		}
		if('${otherFindings.clubbingOfFingers}'!=''){
			if('${otherFindings.clubbingOfFingers}'=='Y')
	      	  document.forms[0].GE6[0].checked='checked';
	        else  if('${otherFindings.clubbingOfFingers}'=='N')
	      	  document.forms[0].GE6[1].checked='checked';
		}
		if('${otherFindings.lymphadenopathy}'!=''){
			if('${otherFindings.lymphadenopathy}'=='Y')
	      	  document.forms[0].GE7[0].checked='checked';
	        else  if('${otherFindings.lymphadenopathy}'=='N')
	      	  document.forms[0].GE7[1].checked='checked';
		}
		if('${otherFindings.oedema}'!=''){
			if('${otherFindings.oedema}'=='Y')
	      	  document.forms[0].GE8[0].checked='checked';
	        else  if('${otherFindings.oedema}'=='N')
	      	  document.forms[0].GE8[1].checked='checked';
		}
		if('${otherFindings.malNutrition}'!=''){
			if('${otherFindings.malNutrition}'=='Y')
	      	  document.forms[0].GE9[0].checked='checked';
	        else  if('${otherFindings.malNutrition}'=='N')
	      	  document.forms[0].GE9[1].checked='checked';
		}
		if('${otherFindings.dehydration}'!=''){
			if('${otherFindings.dehydration}'=='Y'){
	      	  document.forms[0].GE10[0].checked='checked';
	      	  var examinField="<input type='radio' name='dehydrationY' id='dehydrationY' value='Mild' title='Mild'/>Mild<input type='radio'  name='dehydrationY' id='dehydrationY' value='Moderate' title='Moderate'/>Moderate<input type='radio'  name='dehydrationY' id='dehydrationY' value='Severe' title='Severe'/>Severe";
		      document.getElementById("DehydrationSub").innerHTML=examinField;
	      	  
	      	  if('${otherFindings.dehydrationType}'!=''){
	      		if('${otherFindings.dehydrationType}'=='Mild')
	      		document.forms[0].dehydrationY[0].checked='checked';
	      		if('${otherFindings.dehydrationType}'=='Moderate')
	      		document.forms[0].dehydrationY[1].checked='checked';
	      		if('${otherFindings.dehydrationType}'=='Severe')
	      		document.forms[0].dehydrationY[2].checked='checked';
	          }
			}
	        else  if('${otherFindings.dehydration}'=='N')
	      	  document.forms[0].GE10[1].checked='checked';
		}	
		if('${otherFindings.temperature}'!='NA' && '${otherFindings.temperature}'!=''){
			var temp = '${otherFindings.temperature}';
			var tempType = temp.charAt(temp.length - 1);
			temp = temp.slice(0,str.length-1);
			document.forms[0].GE11.value=temp;	
			
			if(tempType=="C"){
				document.forms[0].temp[0].checked='checked';
				}
			else if(tempType=="F")
				document.forms[0].temp[1].checked='checked';			
		}	
		if('${otherFindings.pulseRate}'!='NA' && '${otherFindings.pulseRate}'!='')
			document.forms[0].GE12.value='${otherFindings.pulseRate}';
		if('${otherFindings.respirationRate}'!='NA' && '${otherFindings.respirationRate}'!='')
			document.forms[0].GE13.value='${otherFindings.respirationRate}';
		if('${otherFindings.bpLmt}'!='NA' && '${otherFindings.bpLmt}'!=''){
			var bpLmt='${otherFindings.bpLmt}'.split("/");
			document.forms[0].GE14.value=bpLmt[0];
			document.forms[0].BP1.value=bpLmt[1];	
		}	
		if('${otherFindings.bpRmt}'!='NA' && '${otherFindings.bpRmt}'!='')
			{var bpRmt='${otherFindings.bpRmt}'.split("/");
			document.forms[0].GE15.value=bpRmt[0];
			document.forms[0].BP2.value=bpRmt[1];	
		}



		if('${genInvestFlag}'!=null && '${genInvestFlag}'!=''){
			document.getElementById("genTestTableID").style.display="";
			genOldList='${genInvestLst}'.split("@");
			genOldList.splice(genOldList.length-1,1);
		}

			
		var browserName=navigator.appName;
		if(browserName=="Microsoft Internet Explorer")
			{
			//For validating maxlength onpaste event--For textarea fields
			document.getElementById("presentHistory").attachEvent("paste", pasteInterceptPresent, false);
			document.getElementById("abdomenUltrasound").attachEvent("onpaste",pasteInterceptUltra);
			document.getElementById("chestXrayPAView").attachEvent("onpaste",pasteInterceptChestXray);
			document.getElementById("pulmonaryFunction").attachEvent("onpaste",pasteInterceptPulmonary);
			document.getElementById("ecg").attachEvent("onpaste",pasteInterceptEcg);
			document.getElementById("twodEcho").attachEvent("onpaste",pasteIntercept2D);
			document.getElementById("treadmillTest").attachEvent("onpaste",pasteInterceptTmt);
			document.getElementById("retinopathy").attachEvent("onpaste",pasteInterceptRetinopathy);
			document.getElementById("fundoscopy").attachEvent("onpaste",pasteInterceptFundoscopy);
			document.getElementById("audiometry").attachEvent("onpaste",pasteInterceptAudiometry);
			document.getElementById("overallHealthRemarks").attachEvent("onpaste",pasteInterceptHealth);
			document.getElementById("summary").attachEvent("onpaste",pasteInterceptSummary);
			
			}
		else if(browserName == "Netscape")
			{
			document.getElementById("presentHistory").addEventListener("paste", pasteInterceptPresent, false);
			document.getElementById("abdomenUltrasound").addEventListener("onpaste",pasteInterceptUltra);
			document.getElementById("chestXrayPAView").addEventListener("onpaste",pasteInterceptChestXray);
			document.getElementById("pulmonaryFunction").addEventListener("onpaste",pasteInterceptPulmonary);
			document.getElementById("ecg").addEventListener("onpaste",pasteInterceptEcg);
			document.getElementById("twodEcho").addEventListener("onpaste",pasteIntercept2D);
			document.getElementById("treadmillTest").addEventListener("onpaste",pasteInterceptTmt);
			document.getElementById("retinopathy").addEventListener("onpaste",pasteInterceptRetinopathy);
			document.getElementById("fundoscopy").addEventListener("onpaste",pasteInterceptFundoscopy);
			document.getElementById("audiometry").addEventListener("onpaste",pasteInterceptAudiometry);
			document.getElementById("overallHealthRemarks").addEventListener("onpaste",pasteInterceptHealth);
			document.getElementById("summary").addEventListener("onpaste",pasteInterceptSummary);
			}
	</script>
	</logic:equal>
	<script>
	$("#genBlockInvestName").select2();
	$("#genInvestigations").select2();
	</script>
	</html>
</fmt:bundle>