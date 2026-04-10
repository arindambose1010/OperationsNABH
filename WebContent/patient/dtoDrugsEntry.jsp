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
<script type="text/javascript" src="js/DispPatientscripts.js"></script> 
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
var medOrSur='';
var genInvestAttachId=0;
var updGenInvestAttachId=0;
var ipInvestAttachId=0;
var therapyAttachId=0;
var a=0;
var genInventCount=0;
var invCount=0;
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

//pravalika

var nonIssueableDrugs = [];

<c:forEach var="drug" items="${drugList}">
<c:if test="${drug.DRUGISSUED_YN eq 'N'}">
  nonIssueableDrugs.push({
    typeCode: '${drug.DRUG_CODE}',
    subTypeCode: '${drug.DRUG_SUB_CODE}',
    //issuedDate: '${drug.CRT_DT}',
    issuedDate: '<fmt:formatDate value="${drug.CRT_DT}" pattern="dd-MM-yyyy" />',
    quantity: '${drug.UNITAMOUNT}',
    dosage: '${drug.DOSAGE}',
    medicationPeriod: '${drug.MEDICATIONPERIOD}'
  });
</c:if>
</c:forEach>



function coverScreen(){
	var jq = jQuery.noConflict();
	jq("<div class='coverScreenDiv' id='csDiv'><div> ").appendTo('body').fadeOut(0).fadeIn(300);
	jq('#csDiv').append('<div id="processImage22" align="center" style="position:relative;float:middle;">	<img src="images/Progress.gif" style="margin-top:200px" border="0" tabIndex="3"></img>	</div>');
	jq('#processImage22').show();
};
//This method removes the above added transparent div.
function removeScreenCover(){
	var jq = jQuery.noConflict();
	jq('#processImage22').hide();
	jq(".coverScreenDiv").fadeOut(300, function(){jq(".coverScreenDiv").remove();});
};


</script>


<script>
function getDrugSubTypeList(id)
{
	var jq=jQuery.noConflict();
	var drugTypeCode= document.getElementById("drugTypeCode").value;
	if(document.getElementById("drugTypeCode").value=="-1" )
	{
// 		alert("Please select Drug Type");
		jq("#drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").val('-1');
		document.getElementById("medicatPeriod").value="";
		jq("#drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").select2().val('');
		document.getElementById("drugInfo").style.display="none";
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
            	resultArray = resultArray.replace("[","");
	    	    resultArray = resultArray.replace("@]",""); 
	    	    resultArray = resultArray.replace("]",""); 
	    	    resultArray = resultArray.replace("*",""); 
				resultArray = resultArray.trim();
				
            	if(resultArray!=null && resultArray!="")
            	{
            	    
					var hospListData = resultArray.split(","); 
                	if(hospListData.length>=1)
                	{  
                		document.forms[0].drugSubTypeCode.options.length=0;
                        document.forms[0].drugSubTypeCode.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<hospListData.length;i++)
               		 	{	
                     		var arr=hospListData[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null && arr[1].replace(/^\s+|\s+$/g,"")!='Others')
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].drugSubTypeCode.options[i+1] =new Option(val1,val2);
                     		}
                		}
                		removeScreenCover();
            		}
                	else
            		{
                		removeScreenCover();
            			alert("No Drugs Found");
            			document.forms[0].drugSubTypeCode.options.length=0;
                        document.forms[0].drugSubTypeCode.options[0]=new Option("---select---","-1");
            			document.getElementById("drugTypeCode").value='-1';
            			jq("#drugTypeCode").select2().val('');
            			jq("#drugSubTypeCode").select2().val('-1');
            			return false;
            		}
            	}
            	else
        		{
            		
            		removeScreenCover();
        			alert("No Drugs Found");
        			document.forms[0].drugSubTypeCode.options.length=0;
        			document.forms[0].drugSubTypeCode.value="";
                    document.forms[0].drugSubTypeCode.options[0]=new Option("---select---","-1");
        			document.getElementById("drugTypeCode").value='-1';
        			jq("#drugTypeCode").select2().val('');
        			jq("#drugSubTypeCode").select2().val('-1');
        			return false;
        		}
            	
            	
        	}
        }
    }    	
// 	url = "./patientDetails.do?actionFlag=getDrugSubTypeList&callType=Ajax&drugTypeCode="+drugTypeCode;
	url = "patientDetails.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugTypeCode;
	coverScreen();
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}	
}
var drugCount=0;
var nextDrugIndex = 0;

function focusBox(el) {
	  if (el) el.focus();
	}

function removeDrugDTO(src) {
    var drugsTable = document.getElementById("drugsTable");
    var oRow = src.closest("tr");
    if (!oRow) return;

    var subTypeCodeEl = oRow.querySelector("[id^='drugSubTypeCode_']");
    var subTypeCode = subTypeCodeEl ? subTypeCodeEl.value.trim() : null;

    drugsTable.deleteRow(oRow.rowIndex);
    //oRow.querySelectorAll("input").forEach(inp => inp.remove());
	//oRow.remove();
    drugCount--;

    if (subTypeCode) {
        var warningDiv = document.getElementById("warningData");
        if (warningDiv) {
            var notes = warningDiv.querySelectorAll(".note-item");
            notes.forEach(function(note) {
                if (note.getAttribute("data-subtype") === subTypeCode) {
                    note.remove();
                }
            });

            if (warningDiv.querySelectorAll(".note-item").length === 0) {
                var label = document.getElementById("noteLabel");
                if (label) label.remove();
            }

            var notifyField = document.getElementById("notify");
            if (notifyField) {
                var warned = notifyField.value ? notifyField.value.split("~") : [];
                warned = warned.filter(code => code.trim() !== subTypeCode);
                notifyField.value = warned.join("~");
            }
        }
    }
    
    var drugsHidden = document.getElementById("drugs").value;
    if (drugsHidden) {
        var drugsArr = drugsHidden.split("@").filter(Boolean);
        var drugCode = oRow.getAttribute("data-drug-code");
        drugsArr = drugsArr.filter(function(item) {
            var parts = item.split("~");
            return parts[1]?.trim() !== drugCode;
        });
        document.getElementById("drugs").value = drugsArr.join("@");
    }


    let sno = 1;
    Array.from(drugsTable.rows).forEach(function(row, idx) {
        if (idx > 0 && row.id !== "noRecordRow") {
            row.cells[0].innerText = sno++;
        }
    });

    const hasDataRows = Array.from(drugsTable.rows).some(
        row => row.rowIndex !== 0 && row.id !== "noRecordRow"
    );

    if (!hasDataRows) {
      
        if (!document.getElementById("noRecordRow")) {
            var noRecordRow = drugsTable.insertRow(-1);
            noRecordRow.id = "noRecordRow";
            var td = noRecordRow.insertCell(0);
            td.colSpan = 7;
            td.style.textAlign = "center";
            td.innerText = "No record found";
        } else {
            document.getElementById("noRecordRow").style.display = "";
        }
    }
}

function registerDtoCase()
{
	
	//document.getElementById("Save").disabled=true;
	//document.getElementById("Save").className='butdisable';
	var selectedList1  = document.getElementById('addTests');  
	var patientId= document.getElementById("patientNo").value; 
	var saveFlag="Yes";
	var checkType="SaveDTRS";
    var selInvData='';
	 var selGenInvData='';
	 var updateGenInvData='';
	
	 var consultationData='';

		
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

		
		var url="./patientDetails.do?actionFlag=saveCaseDetails&fromDisp=Y&dtoCs=Y&saveFlag=Yes&checkType=SaveDTRS&patientId="+patientId+"&addTests="+selGenInvData+"&updateTests="+updateGenInvData+"&genInvestRemove="+genInvestRemove;
		
		
		/*document.forms[0].action="./patientDetails.do?actionFlag=saveCaseDetails&patientId="+patId+"&addTests="+selGenInvDatatherapyId="+therapies+"&doctorType="+doctorType */;
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

function confirmRemoveRowDTO(src,type)
{
	var fr;
	
	 if(type=="drug")
		{
			//fr=partial(removeDrug,src);
			//jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
			if(confirm('Do you want to delete Drug?'))
			{
				removeDrugDTO(src);
			}
		}
	
		//parent.fn_resizePage();
}
function addGenInvestigationDispNewTemp()
{
	var investValue=document.getElementById("genBlockInvestName").value;
	var patientId= document.getElementById("patientNo").value; 
	
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
            	resultArray = resultArray.replace("[","");
	    	    resultArray = resultArray.replace("@]",""); 
	    	    resultArray = resultArray.replace("]",""); 
	    	    resultArray = resultArray.replace("*",""); 
				resultArray = resultArray.trim();
				
            	if(resultArray!=null && resultArray!="" && resultArray=='N')
            	{
            		addGenInvestigationDispNew();
			//sai krishna:CR#8134:API Integration.
            		document.getElementById("genBlockInvestName").value = '-1';
                    $('#genBlockInvestName').trigger('change.select2');
            	}
            	else
        		{
            		alert("Investigation Already Exists.")
        			return false;
        		}
            	
            	
        	}
        }
    }    	
   url="./patientDetails.do?actionFlag=chechInvest&fromDisp=Y&investValue="+investValue+"&patientId="+patientId;
	
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function addGenInvestigationDispNew(){
	
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
	/*if(document.getElementById("genInvestigations").value=="-1")
	{
		var fr=partial(focusBox,document.getElementById("genInvestigations"));
		alert("Please Select Investigations");
		partial(focusBox,document.getElementById("genInvestigations"));
		return false;
	}*/
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
	if(genInventList[c].indexOf(document.getElementById("genBlockInvestName").value) !== -1)
		{
		var fr=partial(focusBox,document.getElementById("genBlockInvestName"));
		jqueryErrorMsg('Unique Investigation Validation',"Investigation Name already added.Please select another Investigation Name",fr);
		partial(focusBox,document.getElementById("genBlockInvestName"));
		return false;
		}
	}
	
  }
	if(genInventCount<=15)
	{
		
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
				newcell.innerHTML='<td width="20%">'+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+'</td>';
			}
				newcell=newRow.insertCell(2);
				newcell.innerHTML='<td width="20%"><input type="file"  id='+document.getElementById("genBlockInvestName").value+' name="genAttach['+genInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
				newcell=newRow.insertCell(3);
				var ele = document.getElementById("genBlockInvestName").value;
				newcell.innerHTML='<td style="width:5%;text-align:center;"><span class ="medcoClass" id="lbl'+ele+'">Lab Report <span style="display:none;">'+ele+'</span></span></td>';
		
			
					newcell=newRow.insertCell(4);
				/* 	newcell.innerHTML='<td width="20%">'+document.getElementById("investPrice").value+'</td>';
					newcell=newRow.insertCell(5);
			 */
					
				
			if(!otherInvest)
			{
			    newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+document.getElementById("genBlockInvestName").value+'d id='+document.getElementById("genBlockInvestName").value+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			}
			else
			{
				newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+testId+'d id='+testId+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';	
			}

		
		
		var deleteButName='';
		
		if(otherInvest)
			{
	    deleteButName=testId+'d';
			}
		else
			{
		deleteButName=document.getElementById("genBlockInvestName").value+'d';
			}
		document.getElementById(deleteButName).onclick = function(){
			 //confirmRemoveRow(this,"geninvestigation");
			fr=partial(deleteGenInvest,this,document.getElementById("genBlockInvestName").value);
			jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
			
			 }; 
			 var  genInvest="";
				if(document.getElementById("opPkgName"))
				{
					 if(otherInvest)
						 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value;	
				else if(document.getElementById("opPkgName").value!=null && document.getElementById("opPkgName").value!="-1")
		     genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text;
				}
			 else
				 {
		     
				 if(otherInvest)
				 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value+"~"+0;
				 else
				 genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+"~"+document.getElementById("investPrice").value;	 
				 }
		genInventList[genInventCount]=genInvest;
		//alert(genInventList[genInventCount]);
	    //document.getElementById("symptoms").value=symptoms;
	    genInventCount++;
	
	    if(genInventCount>0)
			{
	    
			document.getElementById("genTestTable").style.display="";
			document.getElementById("send").disabled = false;

			}
	    
		
		genInvestAttachId=genInvestAttachId+1;
	
	}
	else
	{
		alert("Cannot add more than 15 tests");
	}

	//parent.fn_resizePage();	
}
/* function checkDrug(){
	var jq = jQuery.noConflict();
	var drug=document.getElementById("drugs").value;
	 var drugs=drug.split("@");
	 if(drugs.length>0){
		for(var c=0;c<drugs.length;c++)
			{
			var drugValues=drugs[c].split("~");
			if(drugValues[1]!=null){
			var drugCode=drugValues[1].replace("@","").trim();
			if(drugCode==document.getElementById("drugSubTypeCode").value)
				{
				jqueryErrorMsg('Unique Drug Validation',"Drug Set already exists.Please select another Drug set. If you want to edit the drug details, please delete them and add again.");
				document.getElementById("drugSubTypeCode").value="-1";
				document.getElementById("drugTypeCode").value="-1";
				document.getElementById("medicatPeriod").value="";
				document.getElementById("quantity").value="";
				document.getElementById("dosage").value="";
				jq("#drugTypeCode, #drugSubTypeCode, #medicatPeriod, #quantity,  #dosage").select2().val('');

				return false;
				}
			}
			}
		}
	 document.getElementById("medicatPeriod").value="";
		document.getElementById("quantity").value="";
		document.getElementById("dosage").value="";
		jq("#dosage").select2().val('-1');
} */

function checkDrug() {
	var jq = jQuery.noConflict();
	var selectedDrugCode = document.getElementById("drugSubTypeCode").value;

	if (selectedDrugCode === "-1") return;

	// Step 1: Check against hidden drugs field (already present logic)
	var drug = document.getElementById("drugs").value;
	var drugs = drug.split("@");
	if (drugs.length > 0) {
		for (var c = 0; c < drugs.length; c++) {
			var drugValues = drugs[c].split("~");
			if (drugValues[1] != null) {
				var drugCode = drugValues[1].replace("@", "").trim();
				if (drugCode === selectedDrugCode) {
					showDuplicateDrugAlert();
					return false;
				}
			}
		}
	}

	var table = document.getElementById("drugsTable");
	if (table) {
		var rows = table.getElementsByTagName("tr");
		for (var i = 1; i < rows.length; i++) { 
			var cells = rows[i].getElementsByTagName("td");
			if (cells.length > 2) {
				var existingDrugName = cells[2].innerText.trim(); 

				// Compare selected drug name with this one
				var selectedDrugName = jq("#drugSubTypeCode option:selected").text().trim();
				if (existingDrugName === selectedDrugName) {
					showDuplicateDrugAlert();
					return false;
				}
			}
		}
	}

	// If no duplicate, clear values
	document.getElementById("medicatPeriod").value = "";
	document.getElementById("quantity").value = "";
	document.getElementById("dosage").value = "";
	jq("#dosage").select2().val('-1');
}

function showDuplicateDrugAlert() {
	jqueryErrorMsg('Unique Drug Validation',
		"Drug Set already exists. Please select another Drug set. If you want to edit the drug details, please delete them and add again.");
	document.getElementById("drugSubTypeCode").value = "-1";
	document.getElementById("drugTypeCode").value = "-1";
	document.getElementById("medicatPeriod").value = "";
	document.getElementById("quantity").value = "";
	document.getElementById("dosage").value = "";
	jQuery("#drugTypeCode, #drugSubTypeCode, #medicatPeriod, #quantity, #dosage").select2().val('');
}

var nextDrugIndex = ${fn:length(drugList)};

//pravalika CR 8350 Deleting All drugs at a time
function deleteDrugs() {
    var drugsTable = document.getElementById("drugsTable");
    if (!drugsTable) return;

    var rows = Array.from(drugsTable.rows).filter(row => row.rowIndex !== 0 && row.id !== "noRecordRow");

    if (rows.length === 0) {
        alert("No drugs to delete.");
        return;
    }

    if (!confirm("Are you sure you want to delete all drugs?")) return;

    var subTypeCodesToRemove = [];
    rows.forEach(row => {
        var subTypeCodeEl = row.querySelector("[id^='drugSubTypeCode_']");
        if (subTypeCodeEl) {
            subTypeCodesToRemove.push(subTypeCodeEl.value.trim());
        }
        drugsTable.deleteRow(row.rowIndex);
    });

    drugCount = 0;

    var warningDiv = document.getElementById("warningData");
    if (warningDiv) {
        subTypeCodesToRemove.forEach(function(code) {
            var notes = warningDiv.querySelectorAll('.note-item[data-subtype="' + code + '"]');
            notes.forEach(function(note) {
                note.remove();
            });
        });

        if (warningDiv.querySelectorAll(".note-item").length === 0) {
            var label = document.getElementById("noteLabel");
            if (label) label.remove();
        }

        var notifyField = document.getElementById("notify");
        if (notifyField) {
            var warned = notifyField.value ? notifyField.value.split("~") : [];
            subTypeCodesToRemove.forEach(function(code) {
                warned = warned.filter(function(c) {
                    return c !== code;
                });
            });
            notifyField.value = warned.join("~");
        }
    }
    document.getElementById("drugs").value = "";

    var noRecordRow = document.getElementById("noRecordRow");
    if (!noRecordRow) {
        noRecordRow = drugsTable.insertRow(-1);
        noRecordRow.id = "noRecordRow";
        var td = noRecordRow.insertCell(0);
        td.colSpan = 7;
        td.style.textAlign = "center";
        td.innerText = "No record found";
    } else {
        noRecordRow.style.display = "";
    }
    var suggestionText = document.getElementById("drugSuggestionText");
    if (suggestionText) {
        suggestionText.style.display = "none";
    }

    var tableContainer = document.getElementById("drugsTableContainer");
    if (tableContainer) {
        tableContainer.style.display = "none";
    }
    var deleteAllButton = document.getElementById("deleteAllDrugs");
    if (deleteAllButton) {
        deleteAllButton.style.display = "none";
    }
}


function addDrugsNew() {
	var jq = jQuery.noConflict();
	var otherDrugs = false;
	var OtherDrugName = "";

	if (document.getElementById("drugOthers"))
		otherDrugs = document.getElementById("drugOthers").checked;

	var drugId = '';

	if (!otherDrugs) {
		if (document.getElementById("drugTypeCode").value == -1) {
			focusBox(document.getElementById("drugTypeCode"));
			alert("Please select Drug Type");
			return false;
		}
		if (document.getElementById("drugSubTypeCode").value == -1) {
			focusBox(document.getElementById("drugSubTypeCode"));
			alert("Please select Drug Name");
			return false;
		}
		if (document.getElementById("dosage").value == -1) {
			focusBox(document.getElementById("dosage"));
			alert("Please select Dosage");
			return false;
		}
		if (document.getElementById("medicatPeriod").value == null || document.getElementById("medicatPeriod").value == '') {
			focusBox(document.getElementById("medicatPeriod"));
			alert("Please give Medication Period");
			return false;
		}
	}

	if (jq("#drugSubTypeCode option:selected").text() == "Others") {
		if (document.getElementById("otherDrugsTxt").value == "") {
			focusBox(document.getElementById("otherDrugsTxt"));
			alert("Please enter other drug name");
			return false;
		}
		OtherDrugName = document.getElementById("otherDrugsTxt").value;
	}

	if (document.getElementById("medicatPeriod").value > 90) {
		alert("Medication Period cannot be greater than 90 Days");
		return false;
	}
	
	var newDrugTypeCode = document.getElementById("drugTypeCode").value;
	var newDrugName = document.getElementById("drugSubTypeCode").options[document.getElementById("drugSubTypeCode").selectedIndex].text.trim();
	var newDrugSubTypeCode = document.getElementById("drugSubTypeCode").value;

		  for (var i = 0; i < nonIssueableDrugs.length; i++) {
			    if (
			        nonIssueableDrugs[i].typeCode === newDrugTypeCode &&
			        nonIssueableDrugs[i].subTypeCode === newDrugSubTypeCode
			    ) {
			       
			        var drugName = newDrugName || "Unknown Drug Name";
			        var issuedDate = nonIssueableDrugs[i].issuedDate || "Unknown Date";
			        var quantity = nonIssueableDrugs[i].quantity || "N/A";
			        var dosage = nonIssueableDrugs[i].dosage || "N/A";
			        var medicationPeriod = nonIssueableDrugs[i].medicationPeriod || "N/A";

	        var text = drugName + " was issued on " + issuedDate + 
	                   " QUANTITY: " + quantity + 
	                   " DOSAGE: " + dosage + 
	                   " MEDICATION PERIOD: " + medicationPeriod;
	        //alert(text);

	        var theDiv = document.getElementById("warningData");
	        var notifyField = document.getElementById("notify");
            var warned = notifyField.value ? notifyField.value.split("~") : [];
	
            if (!warned.includes(newDrugSubTypeCode)) {
                alert(text);

                var theDiv = document.getElementById("warningData");
                
                if (!document.getElementById("noteLabel")) {
                    var label = document.createElement("div");
                    label.id = "noteLabel";
                    label.style.marginLeft = "20px"; 
                    label.innerHTML = "<b>Note:</b>";
                    theDiv.appendChild(label);
                }

                var linebreak = document.createElement("br");
                theDiv.appendChild(linebreak);

                var textDiv = document.createElement("div");
                textDiv.className = "note-item";
                textDiv.setAttribute("data-subtype", newDrugSubTypeCode);
                textDiv.style.marginLeft = "20px"; 
                textDiv.textContent = text;
                theDiv.appendChild(textDiv);

                warned.push(newDrugSubTypeCode);
                notifyField.value = warned.join("~");
            }

            break; 
        }
    }

	  var drugTable = document.getElementById("drugsTable");
	 
	  var tableContainer = document.getElementById("drugsTableContainer");
	  if (tableContainer) {
	      tableContainer.style.display = "block";
	  }
	  for (var i = 1; i < drugTable.rows.length; i++) {
		    var row = drugTable.rows[i];
		    if (row.id === "noRecordRow") continue;
		    var existingDrugSubTypeCode = row.querySelector("[id^='drugSubTypeCode_']");
		    if (existingDrugSubTypeCode && existingDrugSubTypeCode.value === newDrugSubTypeCode) {
		        showDuplicateDrugAlert();
		        return false;
		    }
		  }
	var noRecordRow = document.getElementById("noRecordRow");
	if (noRecordRow) {
		noRecordRow.parentNode.removeChild(noRecordRow);
	}
	
	
	var dataRowCount = 0;
	for (var i = 1; i < drugTable.rows.length; i++) {
		if (drugTable.rows[i].id !== "noRecordRow") {
			dataRowCount++;
		}
	}
	var newRow = drugTable.insertRow(-1);
	newRow.setAttribute("data-added", "true");
	//var newcell;
	
	var index = nextDrugIndex;
	nextDrugIndex++;

	var newcell = newRow.insertCell(0);
	newcell.innerHTML = dataRowCount + 1;

	newcell = newRow.insertCell(1);
	//newcell.innerHTML = document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text;
	newcell.innerHTML = document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text +
    '<input type="hidden" id="drugTypeCode_' + index + '" value="' + document.getElementById("drugTypeCode").value + '"/>';

	newcell = newRow.insertCell(2);
	//newcell.innerHTML = newDrugName;
	newcell.innerHTML = newDrugName +
    '<input type="hidden" id="drugSubTypeCode_' + index + '" value="' + document.getElementById("drugSubTypeCode").value + '"/>';

	newcell = newRow.insertCell(3);
	//newcell.innerHTML = document.getElementById("dosage").value;
	newcell.innerHTML = document.getElementById("dosage").value +
    '<input type="hidden" id="dosage_' + index + '" value="' + document.getElementById("dosage").value + '"/>';

	newcell = newRow.insertCell(4);
	//newcell.innerHTML = document.getElementById("medicatPeriod").value;
	newcell.innerHTML = document.getElementById("medicatPeriod").value +
    '<input type="hidden" id="medicationPeriod_' + index + '" value="' + document.getElementById("medicatPeriod").value + '"/>';

	newcell = newRow.insertCell(5);
	//newcell.innerHTML = document.getElementById("quantity").value;
	newcell.innerHTML = document.getElementById("quantity").value +
    '<input type="hidden" id="quantity_' + index + '" value="' + document.getElementById("quantity").value + '"/>';

	newcell = newRow.insertCell(6);
	var deleteButId = "del_" + (drugCount + 1);
	newcell.innerHTML = '<input class="but" type="button" value="Delete" name="' + deleteButId + '" id="' + deleteButId + '" />';

	document.getElementById(deleteButId).onclick = function () {
		confirmRemoveRowDTO(this, "drug");
	};
	drugCount++;

	var tableContainer = document.getElementById("drugsTableContainer");
	if (tableContainer) {
		tableContainer.style.display = "block";
	}

	document.getElementById("drugTypeCode").value = "-1";
	document.getElementById("drugSubTypeCode").value = "-1";
	document.getElementById("dosage").value = "-1";
	document.getElementById("medicatPeriod").value = "";
	document.getElementById("quantity").value = "";
	
	jq("#drugTypeCode, #drugSubTypeCode, #medicatPeriod, #quantity,  #dosage").select2().val('');
} 

function otherDoctors()
{
	var specSize=document.getElementById("specSize").value;

	 for(var i=1;i<=specSize;i++)
	{
		
		 if(document.getElementById("specialistDoctorName"+i).options[document.getElementById("specialistDoctorName"+i).selectedIndex].text=="Others")
			{
			 document.getElementById("otherSpecialistDoctorName"+i).style.display="";
			}
		 if(document.getElementById("specialistDoctorName"+i).options[document.getElementById("specialistDoctorName"+i).selectedIndex].text!="Others")
			{
			 document.getElementById("otherSpecialistDoctorName"+i).style.display="none";
			}
	} 
}

var warnedIndex = 0; 

function fn_submitDrugs() { 
	 var drugsTable = document.getElementById("drugsTable");
	 if (!drugsTable) {
	        alert("Please add drugs");
	        return false;
	    }
	 var rowCount = drugsTable.rows.length;
	 var actualDrugRows = 0;
	    for (var i = 1; i < rowCount; i++) {
	        var row = drugsTable.rows[i];
	        if (row.id !== "noRecordRow") {
	            actualDrugRows++;
	        }
	    }

	    var notifyField = document.getElementById("notify");
	    var warned = notifyField.value ? notifyField.value.split("~") : [];
	    

	    if (actualDrugRows === 0) {
	        alert("Please add drugs");
	        return false;
	    }


	    var pendingAlerts = [];
	    for (var i = 1; i < drugsTable.rows.length; i++) {
	        var row = drugsTable.rows[i];
	        if (row.id === "noRecordRow") continue;

	        var typeCode = row.querySelector("[id^='drugTypeCode_']")?.value;
	        var subTypeCode = row.querySelector("[id^='drugSubTypeCode_']")?.value;
	        if (!typeCode || !subTypeCode) continue;

	        if (!warned.includes(subTypeCode)) {
	            for (var j = 0; j < nonIssueableDrugs.length; j++) {
	                if (nonIssueableDrugs[j].typeCode === typeCode &&
	                    nonIssueableDrugs[j].subTypeCode === subTypeCode) {

	                    pendingAlerts.push({row: row, subTypeCode: subTypeCode, info: nonIssueableDrugs[j]});
	                }
	            }
	        }
	    }

	    if (pendingAlerts.length > 0) {
	        var alertObj = pendingAlerts[0];
	        var row = alertObj.row;
	        var info = alertObj.info;
	        var subTypeCode = alertObj.subTypeCode;

	        var drugName = row.cells[2]?.textContent.trim() || "Unknown Drug Name";
	        var issuedDate = info.issuedDate || "Unknown Date";
	        var quantity = info.quantity || "N/A";
	        var dosage = info.dosage || "N/A";
	        var medicationPeriod = info.medicationPeriod || "N/A";

	        var text = drugName + " was issued on " + issuedDate +
	                   " QUANTITY: " + quantity +
	                   " DOSAGE: " + dosage +
	                   " MEDICATION PERIOD: " + medicationPeriod;

	        alert(text);
	        var theDiv = document.getElementById("warningData");
	        if (!document.getElementById("noteLabel")) {
	            var label = document.createElement("div");
	            label.id = "noteLabel";
	            label.style.marginLeft = "20px"; 
	            label.innerHTML = "<b>Note:</b>";
	            theDiv.appendChild(label);
	        }

	        theDiv.appendChild(document.createElement("br"));
	        var textDiv = document.createElement("div");
	        textDiv.className = "note-item";
	        textDiv.setAttribute("data-subtype", subTypeCode);
	        textDiv.style.marginLeft = "20px"; 
	        textDiv.textContent = text;
	        theDiv.appendChild(textDiv);
	        warned.push(subTypeCode);
	        notifyField.value = warned.join("~");
	        return false; 
	    }          
      
    var drugDataString = "";
    for (var i = 1; i < rowCount; i++) {
        var row = drugsTable.rows[i];
        if (row.id === "noRecordRow") continue;

        var drugTypeCode = row.querySelector("[id^='drugTypeCode_']")?.value.trim() || "";
        var drugSubTypeCode = row.querySelector("[id^='drugSubTypeCode_']")?.value.trim() || "";

        var indexMatch = row.querySelector("[id^='drugTypeCode_']").id.match(/_(\d+)$/);
        if (!indexMatch) continue;
        var index = parseInt(indexMatch[1]);

        var dosageEl = document.getElementById("dosage_" + index);
        var dosage = "";
        if (dosageEl) {
            if (dosageEl.tagName === "SELECT") {
                dosage = dosageEl.options[dosageEl.selectedIndex].value.trim();
            } else if (dosageEl.tagName === "INPUT") {
                dosage = dosageEl.value.trim();
            }
        }

        var medicationPeriod = document.getElementById("medicationPeriod_" + index)?.value.trim() || "";
        var quantity = document.getElementById("quantity_" + index)?.value.trim() || "";

        if (dosage === "-1" || dosage === "") {
            alert("Please select valid dosage for drug.");
            if (dosageEl) dosageEl.focus();
            return false;
        }

        if (dosage !== "" && medicationPeriod === "") {
            alert("Medication period is mandatory");
            var medPeriodEl = document.getElementById("medicationPeriod_" + index);
            if (medPeriodEl) medPeriodEl.focus();
            return false;
        }

        drugDataString += drugTypeCode + "~" + drugSubTypeCode + "~" + dosage + "~" + medicationPeriod + "~" + quantity + "@";
    }

    drugDataString = drugDataString.replace(/@$/, "");
    document.getElementById("drugs").value = drugDataString;
    var specSize=document.getElementById("specSize").value;
	var specialistDoctorNames=document.getElementById("specialityDoctors").value;

	 for(var i=1;i<=specSize;i++)
	{
		
		 if(document.getElementById("specialistDoctorName"+i).value==-1)
			{
			var fr=partial(focusBox,document.getElementById("specialistDoctorName"+i));
			alert("Please select Doctor Name");
			return false;
			}
		 if(document.getElementById("otherSpecialistDoctorName"+i).value!="null")
			{
			 specialistDoctorNames=specialistDoctorNames+document.getElementById("specialistDoctorName"+i).value+"~"+document.getElementById("specialistDoctorName"+i).options[document.getElementById("specialistDoctorName"+i).selectedIndex].text+"~"+document.getElementById("otherSpecialistDoctorName"+i).value;
			}
		 if(document.getElementById("otherSpecialistDoctorName"+i).value=="null")
			{ 
			 specialistDoctorNames=specialistDoctorNames+document.getElementById("specialistDoctorName"+i).value+"~"+document.getElementById("specialistDoctorName"+i).options[document.getElementById("specialistDoctorName"+i).selectedIndex].text;
			 } 
		 specialistDoctorNames += "@";
		  
	} 
	document.getElementById("specialityDoctors").value=specialistDoctorNames;
    if (!confirm("Do you want to submit?")) return false;

    var url = "./patientDetailsNew.do?actionFlag=saveDTOdrugs&fromDisp=Y";
    document.forms[0].action = url;
    document.forms[0].method = "post";
    document.forms[0].submit();
    document.getElementById("Save").disabled = true;   
}
function fn_backDrugs()
{
	//var url="./patientDetails.do?actionFlag=ViewRegisteredPatients&fromDispnsry=Y";
	var url="./patientDetails.do?actionFlag=ViewRegisteredPatients&fromDispnsry=Y&advSearch=false";
	document.getElementById("patientNo").value="";
	document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();
	
	
}

function calculateQuantity()
{
	
	if(document.getElementById("medicatPeriod").value !=null && document.getElementById("medicatPeriod").value>0)
		{
		var dosage=document.getElementById("dosage").value;
		var medicatPrd=document.getElementById("medicatPeriod").value;
		var dos=1;
			
	 	if(dosage=='OD')
		dos=1;
		else if(dosage=='BD')
		dos=2;
		else if(dosage=='TID')
		dos=3;
		else if(dosage=='QID')
		dos=4;
		var totQuant=parseInt(dos)*parseInt(medicatPrd);
		document.getElementById("quantity").value=totQuant;
		checkOldDrugs();
		}
	else
		{
		document.getElementById("quantity").value="";
		return false;
		}
		
	

} 

function onDosageChange(index) {
	  var dosageEl = document.getElementById("dosage_" + index);
	  var selectedValue = dosageEl.value;

	  if (selectedValue === "-1") {
	    alert("Please select dosage");
	    dosageEl.focus();
	    return; 
	  }
	  var periodField = document.getElementById("medicationPeriod_" + index);
	  if (periodField) {
	    periodField.value = "";
	    periodField.focus();
	  }

	  var quantityField = document.getElementById("quantity_" + index);
	  if (quantityField) {
	    quantityField.value = "";
	  }
	}

	function calculateQuantityEdit(index) {
	  var dosageElem = document.getElementById("dosage_" + index);
	  var periodElem = document.getElementById("medicationPeriod_" + index);
	  var quantityElem = document.getElementById("quantity_" + index);

	  if (!dosageElem || !periodElem || !quantityElem) return;

	  var dosage = dosageElem.value;
	  var period = parseInt(periodElem.value);

	  if (isNaN(period) || period <= 0) {
	    quantityElem.value = "";
	    return;
	  }

	  var doseCount = 1;
	  if (dosage === "BD") doseCount = 2;
	  else if (dosage === "TID") doseCount = 3;
	  else if (dosage === "QID") doseCount = 4;

	  quantityElem.value = doseCount * period;
	}


function checkOldDrugs()
{
	var quant=document.getElementById("quantity").value;
	var drgType=document.getElementById("drugTypeCode").value;
	var drgName=document.getElementById("drugSubTypeCode").value;
	var patientId= document.getElementById("patientNo").value; 
	var notify=document.getElementById("notify").value;
	var flag=1;
	if(notify!=null && notify!="")
		{
		var args=notify.split("~");
		for(var i=0;i<args.length;i++)
			{
			if(drgName==args[i])
				{
				flag=0;
				break;
				}
			}
		}
	
	if (isNaN(quant)) 
	  {
	    alert("Dispatch Quantity should be numbers");
	    document.getElementById("quantity").value = "";
	    return false;
	  }
	
	if(quant==null || quant=="" || quant=="0" )
	{
 		alert("Please enter Quantity");
		return false;
	}
else if(flag==1)
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
					var drugData = resultArray; 
					var theDiv = document.getElementById("warningData");
                	if(drugData.length>0)
                	{  
                		var arr=drugData.split("~");
                		 arr[0].replace(/^\s+|\s+$/g,"");
                		 arr[1].replace(/^\s+|\s+$/g,"");
                		 arr[2].replace(/^\s+|\s+$/g,"");
                		 arr[3].replace(/^\s+|\s+$/g,"");
                		 arr[4].replace(/^\s+|\s+$/g,"");
                		 
                		 if(notify==null || notify=="" )
                			 {
                			 var op="Note:";
                			 var note = document.createTextNode(op);
                			 theDiv.appendChild(note);
                			 var linebreak = document.createElement("br");
                     		  theDiv.appendChild(linebreak);
                			 }
                		var text=arr[0]+" was issued on "+arr[1]+" QUANTITY: "+arr[2]+" DOSAGE: "+arr[3]+ " MEDICATION PERIOD:"+arr[4];
                		alert(text);
                		document.getElementById("notify").value=document.getElementById("notify").value+"~"+arr[5];
                		
                		var content = document.createTextNode(text);
                		var linebreak = document.createElement("br");
                		theDiv.appendChild(linebreak);
                		theDiv.appendChild(content);
                		removeScreenCover();
            		}
                	
            	}
            	
            	
        	}
        }
    }    	
	url = "patientDetails.do?actionFlag=checkOldDrugsAjax&callType=Ajax&drgType="+drgType+"&drgName="+drgName+"&patientId="+patientId;
	coverScreen();
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
	removeScreenCover();
}
function fn_onloadDisabled()
{
	document.getElementById("send").disabled = true;

}

</script>
  <%--   <jsp:include page="/patient/DispPatientDetails.jsp"/>   --%>
</head>
<body onload="fn_onloadDisabled();">
<form action="/patientDetails.do" method="post" enctype="multipart/form-data">
<table class="tbheader">
<tr><td align="left"><b>Patient Registration</b></td></tr>
</table>
<br>
<table>
<tr><td  width="15%" class="labelheading1 tbcellCss" ><b>Registration Number</b></td><td id="patNo" width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>
</tr>
<tr><td width="15%" class="labelheading1 tbcellCss"><b>Card Number</b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td></tr>



<tr><td class="labelheading1 tbcellCss" width="40%"><b>Name</b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss"><b>Gender</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>


 <tr>
<%-- <td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Specialist Doctor</b> <font color="red" >*</font></td>

<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="specialistDoctorName" styleId="specialistDoctorName" style="width:17em;" title="Select Doctor Name" >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDoctorNames" labelProperty="VALUE"/>
</html:select>
</td> --%>
<table  width="95%"  style="text-align:center;margin: 0 auto !important" border="1">
<logic:present name="patientForm" property="specLt">
     <logic:notEmpty name="patientForm" property="specLt" >
     
     <tr>
		<th class="tbheader1" width="12%">S.No</th>
		<th class="tbheader1" width="25%">Speciality Type</th>
		<th class="tbheader1" width="10%">Specialist Doctor</th>		
	</tr>
	<bean:size  id="specSize" name="patientForm" property="specLt"/>
        <logic:greaterThan value="0" name="specSize">
		<%int k = 1;%>
        <logic:iterate id="specLst" name="patientForm" property="specLt" indexId="index"  >
        <tr>  
      	<td width="1%"><b>${index+1}</b></td> 
		<td width="10%"><b>
		<bean:write name="specLst" property="SPECIALITYNAME" /><input type="hidden" id="speciality${index+1}" value=<bean:write name="specLst" property="SPECIALITYTYPE" />>
		</b>
		</td>
		<logic:notEmpty name="specLst" property="docList" >
		<bean:size  id="docsize" name="specLst" property="docList"/>
        <logic:greaterThan value="0" name="docsize">
		<%int h = 1;%>
        
       	<td width="10%"><b>
		<html:select name="patientForm" styleId="specialistDoctorName${index+1}" property="specialistDoctorName" onchange="otherDoctors()" style="width:17em;" title="Select Specialist Doctor" >
		<html:option value="-1">Select</html:option>
		<c:forEach items="${specLst.docList}" var="mand">
		   	<html:option value="${mand.ID}">${mand.VALUE}</html:option>
		 </c:forEach>
		<%-- <logic:iterate id="docLst" name="specLst" property="docList" indexId="docindex">
		<html:option value="${docLst.ID}"><bean:write name="docLst" property="VALUE" /></html:option>
		</logic:iterate> --%>
		</html:select>
       	<%-- <input type="hidden" id="doc${index+1}${docindex+1}" value=<bean:write name="docList" property="VALUE" />> --%>
       	<html:text name="patientForm" property="otherSpecialistDoctorName" styleId="otherSpecialistDoctorName${index+1}"  style="display:none;width:17em;"  title="Enter Other Specialist Doctor Name"/>
       	</b></td>
		
		
       	</logic:greaterThan>
       	</logic:notEmpty>
       	<logic:empty name="specLst" property="docList" >
	      <td width="10%">
	      <b>NA</b>
	      </td>
      	</logic:empty>
       	</logic:iterate>
       	</logic:greaterThan>
		</logic:notEmpty>
									
      <logic:empty name="patientForm" property="specLt" >
      <tr>
      <td align="center" style="color:#434645">
      <i class="fa fa-user-times"></i>&nbsp;<b>No Past History Found</b>
      </td>
      </tr>
      </logic:empty>
      </logic:present>
      </table>
</tr>  
</table>
<tr class = "notDisplayLab"><td colspan="4"><b><b>Investigation Name</b> <font color="red" >&nbsp;&nbsp;(Upload files with size less than 200KB)</font></td></tr>
<tr class = "notDisplayLab"><td colspan="4"><table width="100%">
<tr class = "notDisplayLab">
<td class="labelheading1 tbcellCss" width="25%" id="InvBlockName"><b>Investigation Block Name</b></td>
<td class="labelheading1 tbcellCss" width="25%" id="InvName" style="display: none;"><b>Investigation Name</b></td>
      
      

      
        
 <td width="25%">&nbsp;</td></tr>
<tr id="genInvestList">
<td class="tbcellBorder" id="InvBlckLst">
<html:select name="patientForm" property="genBlockInvestName" styleId="genBlockInvestName" title="Select Block Investigation Name" style="WIDTH: 17em;" onmouseover="addTooltip('genBlockInvestName')" >
          <html:option value="-1">-------------Select-----------------</html:option>
          <html:options property="ID" collection="investigationsList" labelProperty="VALUE"/>
         
  
          
    </html:select></td>
<td  style="align:center">
<button class="btn btn-success" type="button" name="add" id="add" value="Add Tests" onclick="addGenInvestigationDispNewTemp()">Add Tests&nbsp;<i class="fa fa-plus"></i></button>
</td>
  <td  style="align:center">
<button class="btn btn-success" type="button" name="send" id="send" value="Add Tests" onclick="registerDtoCase()" >Send To Lab&nbsp;</button>
</td>  
    </tr>
   
     <tr>
   
     <td class="labelheading1 tbcellCss" width="25%" Id="otherInvNameHead" style="display:none"><b>Other Investigation Name</b></td>
     </tr>
     <tr>
     <td class="tbCellBorder"><html:text name="patientForm" property="otherInvName" styleId="otherInvName" style="display:none;width:97%" styleClass="otherFields" ></html:text></td>
  </tr>
  <tr><td><br></td></tr>
 
</table></td></tr>

<logic:present name="patientForm" property="genInvList">
        <bean:size  id="genInvSize" name="patientForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
<tr><td colspan="4" class="tbcellBorder">
  <table  width="100%" id="genTestTableID" style="display:none;border-collapse:collapse;border:1px solid #c5f7de" border="1">
     	<thead>
   			<tr><th colspan="5" class="tbheader1" style="text-align:center">INVESTIGATION DETAILS</th></tr>  	
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<th class="tbheader1" width="30%" ><b>Investigation Block Name</b></td>       
        	<th class="tbheader1"width="20%" ><b>Test Name</b></th>   
        	<th class="tbheader1"width="20%"><b>Attachment</b></th>
        	<th class="tbheader1"width="10%"><b>Lab Report</b></th>
        	<th class="tbheader1" width="20%">&nbsp;</th></tr>
        
		<%int j = 1;%>
		<script>var rowNo=2;</script>
        <logic:iterate id="gnInvlst" name="patientForm" property="genInvList" indexId="sno" >
        <tr>  
      	<!-- <td width="10%"><%=j++ %></td>  -->
      	<td width="30%" ><bean:write name="gnInvlst" property="ACTION" /></td>       
        <td width="20%"><bean:write name="gnInvlst" property="VALUE" /></td> 
		<logic:empty name="gnInvlst" property="LVL">
		<script>
			var updateGenInvest="<bean:write name="gnInvlst" property="ACTION" />~<bean:write name="gnInvlst" property="VALUE" />~<bean:write name="gnInvlst" property="ID" />";
			updateGenInvestLst[updateGenInvestCount]=updateGenInvest;
			updateGenInvestCount++;
			var investTableId=document.getElementById('genTestTableID');   
			var newRow=investTableId.rows[rowNo];
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="20%"><input type="file"  id=<bean:write name="gnInvlst" property="ID" /> name="updateGenAttach['+updGenInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
			updGenInvestAttachId++;
		</script>
		</logic:empty>
		<logic:notEmpty  name="gnInvlst" property="LVL">
       	<td width="25%"><a href="javascript:fn_openAtachment('<bean:write name="gnInvlst" property="LVL" />','<bean:write name="gnInvlst" property="LVL" />');">View</a></td>
		</logic:notEmpty>
		<td width="10%">
			<c:choose>
				<c:when test="${gnInvlst.TYPE eq 'Y'}">
					<!-- <a class="LabReportSubmited" href="javascript:fn_openLabReport('<bean:write name="gnInvlst" property="ID" />')">Lab Report</a>-->
					<span class ="labReport LabReportSubmited" id="lbl<bean:write name="gnInvlst" property="ID" />">Lab Report <span style="display:none;"><bean:write name="gnInvlst" property="ID" /></span></span>
				</c:when>
				<c:otherwise>
					<!--<a class="labReport" href="javascript:fn_openLabReport('<bean:write name="gnInvlst" property="ID" />')">Lab Report</a>-->
					<span class ="labReport" id="lbl<bean:write name="gnInvlst" property="ID" />">Lab Report <span style="display:none;"><bean:write name="gnInvlst" property="ID" /></span></span>
				</c:otherwise>
			</c:choose>
		</td>
		<c:if test="${labGrp eq 'GP797'}">
		<td><span class ="editReport" id="lbl<bean:write name="gnInvlst" property="ID" />">Edit <span style="display:none;"><bean:write name="gnInvlst" property="ID" /></span></span></td></c:if>
		<script>rowNo++;</script>
       	<td width="20%"><button class="btn btn-warning notDisplayLab" type="button" value="Delete" name=<bean:write name="gnInvlst" property="ID" /> id=<bean:write name="gnInvlst" property="ID" /> onclick="javascript:confirmRemoveGenInvest(this,'geninvestigations','<bean:write name="gnInvlst" property="ID" />');">Delete&nbsp;<i class="fa fa-times"></i></button></td>
        </tr>
        </logic:iterate>
          </table>
</td></tr>
</logic:greaterThan></logic:present>
 <logic:present name="patientForm" property="genInvList">
        <bean:size  id="genInvSize" name="patientForm" property="genInvList"/>
        <logic:greaterThan value="0" name="genInvSize">
<tr id="allReportsViewTr"><td colspan="4" class="tbcellBorder" style="    text-align: center;border: 0;font-size: 15px;">
<a href="javascript:fn_openAllInOneReport();">Click here to view Reports</a>
</td></tr>
</logic:greaterThan></logic:present>
<tr ><td colspan="4" class="tbcellBorder" >
  <table  width="100%"   border="1" id="genTestTable" style="display:none">
     	<tr>
     	 	<!-- <td width="10%" ><fmt:message key="EHF.SNO"/></td> -->
     	 	<td width="30%" ><b>Investigation Block Name</b></td>       
        	<td width="20%" ><b>Test Name</b></td>   
        	<td width="20%"><b>Attachment</b></td>
        	<td width="10%"><b>Lab Report</b></td>
        	<td width="20%">Action</td></tr> 
  </table>
</td></tr>  
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
											<th class="tbheader1" width="10%">Registered Date</th>		
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
												<%-- 	<td align="center" width="15%" class="tbcellBorder" style="word-wrap: break-word;">
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


<div id="docPrescriptionDataTable">
<br/>
<table style="width:95%;margin: 0 auto !important" >
<tr><td><font ><b>Prescription:</b></font></td></tr>
<tr>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Drug Type</b> <font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Drug Name</b><font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Dosage</b><font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Medication Period</b><font color="red" >*</font></td>
 <td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Quantity</b><font color="red" >*</font> </td> 
 <td width="25%"></td>
</tr>
<tr>
<td valign="top" class="tbcellBorder existDrugs">
<html:select name="patientForm" property="drugTypeCode" styleId="drugTypeCode" style="width:17em;" title="Select Drug type" onchange="getDrugSubTypeList(this.value)" onmouseover="addTooltip('drugTypeCode')">
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>
<td valign="top" class="tbcellBorder existDrugs" id="drugNameSelectTd">
<html:select name="patientForm" property="drugSubTypeCode"  onchange="checkDrug()" styleId="drugSubTypeCode" style="width:17em;" title="Select Drug Sub type"  onmouseover="addTooltip('drugSubTypeCode')">
		<html:option value="-1">Select</html:option>
		 <c:if test="${fn:length(drugTypeList) gt 0}">
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="drugTypeList" labelProperty="VALUE"/>
		</c:if>
</html:select>
</td>

<td class="tbcellBorder">
<html:select name="patientForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage" onchange="calculateQuantity();"  onmouseover="addTooltip('dosage')">
		<html:option value="-1">Select</html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select>
</td>

<td class="tbcellBorder">
<html:text name="patientForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:17em" onchange="calculateQuantity();validateNumber('Medication Period',this);" title="Enter Medication Period"/>
</td>
<td class="tbcellBorder"> 
 		<html:text name="patientForm" property="quantity" styleId="quantity" readonly ="true" maxlength="3" style="WIDTH:17em"  title="Enter Quantity" /> 
</td> 
<td>
<button type="button" class="btn btn-primary"   name="addDrug"  id="addDrug" onclick="addDrugsNew()" data-toggle="tooltip" data-placement="bottom" title="Click Here To Add Chronic Drugs">Add Drugs&nbsp;<span class="glyphicon glyphicon-plus-sign"></span></button>
</td>
</tr>


</table>
<br/>
<c:if test="${not empty drugList}">
<div id="deleteAllContainer"
     style="display: flex; justify-content: space-between; align-items: center; padding: 10px 20px;">
  
  <span id="drugSuggestionText" style="color: red; font-weight: bold;">
    Suggestion: Drug list based on last 3 visits
  </span>

  <button type="button"
          class="btn btn-primary"
          name="deleteAllDrugs"
          id="deleteAllDrugs"
          onclick="deleteDrugs();"
          style="width: 141px; height: 32px; color: #fff;margin-right: 1%;">
    Delete All
  </button>

</div>

</c:if>
<br/>
 <div id="drugsTableContainer" style="width: 95%; margin: 0 auto; <c:if test='${empty drugList}'>display: none;</c:if>">
  <table id="drugsTable" width="100%" border="1">
    <tr>  
      <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>S.No</b></td>        
      <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Drug Type</b></td>   
      <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Drug Name</b></td> 
      <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Dosage</b></td>
      <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Medication Period</b></td>
      <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Quantity</b></td>
      <td width="5%" class="tbheader1 existDrugs">&nbsp;</td>
    </tr>

    <!-- If drugList is empty, show this row -->
    <tr id="noRecordRow" <c:if test="${not empty drugList}">style="display:none;"</c:if>>
      <td colspan="7" style="text-align:center;">No record found</td>
    </tr>

    <!-- Iterate over drugList -->
    <c:forEach var="drug" items="${drugList}" varStatus="status">
      <tr  data-drug-code="${drug.DRUG_SUB_CODE}" data-type-code="${drug.DRUG_CODE}" data-drug-issued-yn="${drug.DRUGISSUED_YN}">
        <td>${drug.ROWNUM_}</td>
        <td>
          ${drug.DRUGTYPE}
          <input type="hidden" id="drugTypeCode_${status.index}" value="${drug.DRUG_CODE}" />
        </td>
        <td>
          ${drug.DRUGNAME}
          <input type="hidden" id="drugSubTypeCode_${status.index}" value="${drug.DRUG_SUB_CODE}" />
        </td>
        <td>
          <select id="dosage_${status.index}" style="width:6em;" onchange="onDosageChange(${status.index})">
            <option value="-1">Select</option>
            <option value="OD" ${drug.DOSAGE == 'OD' ? 'selected' : ''}>OD</option>
            <option value="BD" ${drug.DOSAGE == 'BD' ? 'selected' : ''}>BD</option>
            <option value="TID" ${drug.DOSAGE == 'TID' ? 'selected' : ''}>TID</option>
            <option value="QID" ${drug.DOSAGE == 'QID' ? 'selected' : ''}>QID</option>
          </select>
        </td>
        <td style="display: none;">
    		<input type="hidden" id="drugIssueYn_${status.index}" value="${drug.DRUGISSUED_YN}" />
  		</td>
  		<td style="display:none;">
  			<input type="hidden" id="issuedDate_${status.index}" value="<fmt:formatDate value='${drug.CRT_DT}' pattern='dd-MM-yyyy' />" />	
  		</td>
        <td>
          <input type="text" id="medicationPeriod_${status.index}" value="${drug.MEDICATIONPERIOD}" onchange="calculateQuantityEdit(${status.index})" size="5"/>
        </td>
        <td>
          <input type="text" id="quantity_${status.index}" value="${drug.UNITAMOUNT}" readonly size="5"/>
        </td>
        <td>
          <input class="but" type="button" value="Delete" onclick="confirmRemoveRowDTO(this,'drug');" />
        </td>
      </tr>
    </c:forEach>
  </table>
</div>

</div>
 <table  width="95%" >
<tr>
<td width="20%" align="right">
<button class="btn btn-primary has-spinner" style="text-align:center" type="button" id="Save" onclick="javascript:fn_submitDrugs()">Submit&nbsp;</button>
</td>


<td width="20%" colspan="2">
<button class="btn btn-primary has-spinner" style="text-align:center" type="button" id="Back" onclick="javascript:fn_backDrugs()">Back&nbsp;</button>
</td>
</tr>
</table>

<br><br><br>
<div id="warningData" style="color:red">

</div>
 <!-- div for showing past history  -->
 
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
 </div>
 </div>
 </div> -->
 
<html:hidden styleId="specSize" property="specSize" value="${specSize}"/>
<html:hidden name="patientForm" property="specialityDoctors" styleId="specialityDoctors"/> 
<html:hidden name="patientForm" property="drugs" styleId="drugs"/>
<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="hospitalId" styleId="hospitalId"/>
<html:hidden name="patientForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="patientForm" property="enablePharma" styleId="enablePharma"/>
<html:hidden name="patientForm" property="investPrice" styleId="investPrice"/>
<input type="hidden" id="notify">
<!-- pravalika -->
<input type="hidden" id="drugs" name="drugs" value="" />
<script>
    $("#drugTypeCode").select2();
    $("#drugSubTypeCode").select2();
    
    $("#dosage").select2();
    $("#specialistDoctorName").select2();
    
    
    
    </script>
</form>
</body>
</html>


