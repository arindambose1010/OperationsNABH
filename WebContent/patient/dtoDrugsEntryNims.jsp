 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" 
import="java.util.ArrayList,java.util.Iterator,java.util.List,com.ahct.patient.vo.PatientVO,java.util.Calendar,com.ahct.common.vo.LabelBean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ include file="/common/include.jsp"%>


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
<script src="bootstrap/js/bootbox.min.js"></script>
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
var viewFl="${viewFl}";
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
		document.getElementById("newDrugName").value="";
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
	url = "patientDetailsNew.do?actionFlag=getDrugListAjaxNims&callType=Ajax&drugType="+drugTypeCode;
	coverScreen();
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}	
}
var drugCount=0;

function removeDrugDTO(src)
{
		var drugs=document.getElementById("drugs").value.split("@");
		var oRow=src.parentNode.parentNode;
		drugs.splice(oRow.rowIndex-1,1);
		document.getElementById("drugs").value=drugs;
		document.getElementById("drugs").value=document.getElementById("drugs").value.split(",").join("@");
		document.getElementById("drugsTable").deleteRow(oRow.rowIndex);
		drugCount--;
		for(var i=1;i<=drugCount;i++)
			{
			var newRow=document.getElementById("drugsTable").rows[i];
			var snoCell=newRow.cells[0];
			snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
			}
		if(drugCount==0)
			{
			document.getElementById("drugsTable").style.display="none";
			}
			//parent.fn_resizePage();
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
		document.getElementById("pastHistoryFrame").src=url;
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
function checkDrug(){
	var jq = jQuery.noConflict();
	var drug=document.getElementById("drugs").value;
	var x=document.getElementById("drugSubTypeCode").value;
	if(x!=null && x!='' && (x=='OD6375' || x=='OD6374' || x=='OD6373' || x=='OD6372' || x=='OD6371' || x=='OD6370' || x=='OD6369' || x=='OD6368' || x=='OD6367' || x=='OD6366' || x=='OD6365' || x=='OD6364'))		{
		document.getElementById("newDrugName").value="";
		document.getElementById("drugNew").style.display="";
		document.getElementById("drugNewtext").style.display="";
		
		}
	else {
		document.getElementById("newDrugName").value="";
		document.getElementById("drugNew").style.display="none";
		document.getElementById("drugNewtext").style.display="none";
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
	}
}

function addDrugsNew()
{
	var jq = jQuery.noConflict();
	var otherDrugs=false;
	var OtherDrugName="";
	
	if(document.getElementById("newDrugName"))
    var otherDrugs=document.getElementById("newDrugName").value;
    var drugId='';

if(!otherDrugs)

{
	
if(document.getElementById("drugTypeCode").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugTypeCode"));
	alert("Please select Drug Type");
	return false;
	}
if(document.getElementById("drugSubTypeCode").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugSubTypeCode"));
	alert("Please select Drug Name");
	return false;
	}
   if(document.getElementById("dosage").value==-1)
{
   var fr=partial(focusBox,document.getElementById("dosage"));
   alert("Please select Dosage");
    return false;
}
   if(document.getElementById("medicatPeriod").value==null || document.getElementById("medicatPeriod").value=='')
   {
      var fr=partial(focusBox,document.getElementById("dosage"));
      alert("Please give medication Period");
       return false;
   }
   if(document.getElementById("quantity").value==null || document.getElementById("quantity").value=='')
   {
      var fr=partial(focusBox,document.getElementById("quantity"));
      alert("Please give quantity");
       return false;
   }
	




}

if(jq("#drugSubTypeCode option:selected").text()=="OTHERS"){
	if(document.getElementById("newDrugName").value==""){
		var fr=partial(focusBox,document.getElementById("otherDrugsTxt"));
		alert("Please enter other drug name");
	return false;
	}
	OtherDrugName=document.getElementById("newDrugName").value;
}


if(document.getElementById("medicatPeriod").value >90)
{
	alert("Medication Period cannot be greater than 90 Days");
return false;
}



var drugTable=document.getElementById("drugsTable");   
var drug=document.getElementById("drugs").value;

/*var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;
var selectedRowCount=0;
*/
/*for(var i=0;i<rows-1;i++)
	 {
	
	 var id="Set"+i;
	 var setName="Set"+(i+1);
	 if(document.getElementById(id).checked===true)
	 {
		 selectedRowCount++;
		 var mstrDrugCode=document.getElementById(id).value;
		 var quanId="quan"+i;
		 var quantity=document.getElementById(quanId).value;*/
		 if(otherDrugs==null && otherDrugs=='')
			 {
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
			 }
		 		 
		/* if(quantity===null || ""===quantity )
		 {
		 alert("Please Enter Quantity for Selected Drug.");
		 return false;
		 }
		 -----------------to be complted
		 
		 */
		 		 
//		 var batchId="batch"+i;
//		 var batchNo=document.getElementById(batchId).value;
//		 drugList=drugList+drugCode+"~"+quantity+"~"+batchNo+"@";
		
//			 if(document.getElementById(quanId).value=='')
//			 {
//				 alert("Please Enter quantity1");
//			  	focusBox(document.getElementById(quanId));
//			 }
//			 else{
	var newRow=drugTable.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td width="5%">'+parseInt(drugCount+1)+'</td>';
	
	newcell=newRow.insertCell(1);
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text+'</td>';
	if(otherDrugs!=null && otherDrugs!='')
		{
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="10%">'+document.getElementById("newDrugName").value+'</td>';
		}
	else
		{
	newcell=newRow.insertCell(2);
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugSubTypeCode").options[document.getElementById("drugSubTypeCode").selectedIndex].text+'</td>';
		}
	newcell=newRow.insertCell(3);
	newcell.innerHTML='<td width="5%">'+document.getElementById("dosage").value+'</td>';
	newcell=newRow.insertCell(4);
	newcell.innerHTML='<td width="5%">'+document.getElementById("medicatPeriod").value+'</td>';
	newcell=newRow.insertCell(5);
	newcell.innerHTML='<td width="5%">'+document.getElementById("quantity").value+'</td>';
	newcell=newRow.insertCell(6);
	newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+parseInt(drugCount+1)+' id='+parseInt(drugCount+1)+' /></td>'; 
	 var deleteButName='';
    deleteButName=parseInt(drugCount+1);
	
	 document.getElementById(deleteButName).onclick = function(){
		 confirmRemoveRowDTO(this,"drug");
		 }; 
		 if(otherDrugs!=null && otherDrugs!='')
			 {
		 drug=drug+document.getElementById("drugTypeCode").value+"~"+document.getElementById("drugSubTypeCode").value+"~"+document.getElementById("newDrugName").value+"~"+document.getElementById("dosage").value+"~"+
	        document.getElementById("medicatPeriod").value+"~"+document.getElementById("quantity").value;
}
		 else
			 {
			 drug=drug+document.getElementById("drugTypeCode").value+"~"+document.getElementById("drugSubTypeCode").value+"~"+"NA"+"~"+document.getElementById("dosage").value+"~"+
		        document.getElementById("medicatPeriod").value+"~"+document.getElementById("quantity").value;
			 }
		 //drugs[drugCount]=drug+"@";
		 drug += "@";
		  document.getElementById("drugs").value=drug;
		  drugCount++;
	/* }
	 }*/
/*if(selectedRowCount==0){
	  alert("Please select atleast one drug set");
	  return false;
}*/
//if(selectedRowCount>0){
//	  for(var i=0;i<rows-1;i++)
//		 {
//			
//		
//		 var setId="Set"+i;
//		 var quanId="quan"+i;
//		 if(document.getElementById(setId).checked===true){
//			 if(document.getElementById(quanId).value=='')
//			 {
//				 alert("Please Enter quantity");
////			  	focusBox(document.getElementById(quanId));
//			  	return false;
//			 }
//		}
//		}
//	}
	if(drugCount>0)
		{
		document.getElementById("drugsTable").style.display="";
		}
	document.getElementById("drugTypeCode").value="-1";
	document.getElementById("drugSubTypeCode").value="-1";
	document.getElementById("newDrugName").value="";
	document.getElementById("drugNew").style.display="none";
	document.getElementById("drugNewtext").style.display="none";
	//document.getElementById("routeType").value="-1";
	//document.getElementById("route").value="-1";
	//document.getElementById("strengthType").value="-1";
	//document.getElementById("strength").value="-1";
	document.getElementById("dosage").value="-1";
	document.getElementById("medicatPeriod").value="";
	document.getElementById("quantity").value="";
//	if(document.getElementById("drugNameAuto"))
//	$("#drugNameAuto").select2('val','');
//	$("#drugTypeCode").select2().val('');
	jq("#drugTypeCode, #drugSubTypeCode, #medicatPeriod, #quantity,  #dosage").select2().val('');
	/*document.getElementById("drugInfo").style.display="none";*/
//	document.getElementById("quantity").value="";
//	document.getElementById("OtherDrugsTxt").value="";
	//parent.fn_resizePage();
	
}


function fn_submitDrugs(id)
{
	
	
	var x = document.getElementById("drugsTable").rows.length;
    if(x<2)
    	{
    	alert("Please Add Drugs");
    	return false;
    	}
    if(id!=null && id!='' && id=='PartialSave')
    	{
    bootbox.confirm("Do you Want to Partial Approve ? ", function(result) {
	 	if(result)
		{
	 		var url="./patientDetailsNew.do?actionFlag=saveDTOdrugsNims&id="+id+"&fromDisp=Y";
	 		document.forms[0].action=url;
	 		document.forms[0].method="post";
	 		document.forms[0].submit();
	 		 document.getElementById("Save").disabled=true;
		 }
	});
    	}
    if(id!=null && id!='' && id=='Save')
	{
bootbox.confirm("Do you Want to Approve ? ", function(result) {
 	if(result)
	{
 		var url="./patientDetailsNew.do?actionFlag=saveDTOdrugsNims&id="+id+"&fromDisp=Y";
 		document.forms[0].action=url;
 		document.forms[0].method="post";
 		document.forms[0].submit();
 		 document.getElementById("Save").disabled=true;
	 }
});
	}
    
	
}
function fn_backDrugs()
{
	//var url="./patientDetails.do?actionFlag=ViewRegisteredPatients&fromDispnsry=Y";
	var url="./patientDetailsNew.do?actionFlag=ViewRegisteredPatientsNims&fromDispnsry=Y&vieFlag=N&advSearch=false";
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

function fn_viewImg1(VALUE)
{
	window.open('./patientDetailsNew.do?actionFlag=viewAttachment&&filePath='+VALUE);
}


</script>
  <%--   <jsp:include page="/patient/DispPatientDetails.jsp"/>   --%>
</head>
<body onload="fn_onloadDisabled();">
<form action="/patientDetailsNew.do" method="post" enctype="multipart/form-data">
<table class="tbheader">
<tr><td align="center"><b>AIS Registration</b></td></tr>
</table>
<br>
<table>
<tr><td  width="15%" class="labelheading1 tbcellCss" ><b>Registration Id</b></td><td id="patNo" width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="patientNo"/></b></td>
<td width="5%">&nbsp;</td>
</tr>
<tr><td width="15%" class="labelheading1 tbcellCss"><b>Officer Id</b></td><td width="15%" class="tbcellBorder"><b> &nbsp;<bean:write name="patientForm" property="cardNo"/></b></td>
<td width="5%">&nbsp;</td></tr>



<tr><td class="labelheading1 tbcellCss" width="40%"><b>Officer Name</b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="fname" /></b></td></tr>
<tr><td class="labelheading1 tbcellCss" width="40%"><b>Pickup Location</b></td><td class="tbcellBorder" width="60%"><b>&nbsp;<bean:write name="patientForm" property="pickupName" /></b></td></tr>

<tr><td><font ><b>Attachments:</b></font></td></tr>
<tr><td class="labelheading1 tbcellCss" width="40%"><b>ID Proof Attachments</b></td>
<td class="tbcellBorder" width="40%">
<logic:iterate id="attachlt" name="attachList">
<a href="javascript:fn_viewImg1('<bean:write name='attachlt' property='VALUE'/>')" title="Click here to View Prescription"><bean:write name='attachlt' property='ATTACHNAME'/></a>
</logic:iterate>
</td>
</tr>
<tr><td class="labelheading1 tbcellCss" width="40%"><b>Prescription Attachments</b></td>
<td class="tbcellBorder" width="40%">
<logic:iterate id="attachltPr" name="attachListPr">
<a href="javascript:fn_viewImg1('<bean:write name='attachltPr' property='VALUE'/>')" title="Click here to View Prescription"><bean:write name='attachltPr' property='ATTACHNAME'/></a>
</logic:iterate>
</td>
</tr>
<%-- <tr><td class="labelheading1 tbcellCss"><b>Gender</b></td><td class="tbcellBorder"><b>&nbsp;<bean:write name="patientForm" property="gender"/></b></td></tr>
 --%>

</table>


   <c:if test="${ viewFl eq 'N' }" >
<div id="docPrescriptionDataTable">
<br/>
<table style="width:95%;margin: 0 auto !important" >
<tr><td><font ><b>Prescription:</b></font></td></tr>
<tr>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Drug Type</b> <font color="red" >*</font></td>
<td width="25%" class="labelheading1 tbcellCss existDrugs"><b>Drug Name</b><font color="red" >*</font></td>
<td width="20%"  id="drugNew" style="display:none" class="labelheading1 tbcellCss existDrugs"><b>New Drug Name</b><font color="red" >*</font></td>
<td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Dosage</b><font color="red" >*</font></td>
<td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Medication Period</b><font color="red" >*</font></td>
 <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Quantity</b><font color="red" >*</font> </td> 
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
<td class="tbcellBorder" id="drugNewtext" style="display:none">
<html:text name="patientForm" property="newDrugName" styleId="newDrugName"  style="WIDTH:17em"  title="Enter Medication Period"/>
</td>
<td class="tbcellBorder">
<html:select name="patientForm" property="dosage" styleId="dosage" style="width:17em;" title="Select Dosage"   onmouseover="addTooltip('dosage')">
		<html:option value="-1">Select</html:option>
		<html:option value="OD">OD</html:option>
		<html:option value="BD">BD</html:option>
		<html:option value="TID">TID</html:option>
		<html:option value="QID">QID</html:option>
</html:select>
</td>

<td class="tbcellBorder">
<html:text name="patientForm" property="medicatPeriod" styleId="medicatPeriod" maxlength="3" style="WIDTH:17em" onchange="validateNumber('Medication Period',this);" title="Enter Medication Period"/>
</td>
<td class="tbcellBorder"> 
 		<html:text name="patientForm" property="quantity" styleId="quantity"  maxlength="4" style="WIDTH:17em" onchange="validateNumber('Quantity',this);"  title="Enter Quantity" /> 
</td> 
<td>
<button type="button" class="btn btn-primary"   name="addDrug"  id="addDrug" onclick="addDrugsNew()" data-toggle="tooltip" data-placement="bottom" title="Click Here To Add Chronic Drugs">Add Drugs&nbsp;<span class="glyphicon glyphicon-plus-sign"></span></button>
</td>
</tr>


</table>
<br/>
 <table  width="95%"  id="drugsTable" style="display:none;margin: 0 auto !important" border="1">
      <tr>  
      	<td width="5%" class="labelheading1 tbcellCss existDrugs"><b>S.No</b></td>        
        <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Drug Type</b></td>   
        <td width="10%" class="labelheading1 tbcellCss existDrugs"><b>Drug Name</b></td> 
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Dosage</b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Medication Period</b></td>
        <td width="5%" class="labelheading1 tbcellCss existDrugs"><b>Quantity</b></td>
         <td width="5%" class="tbheader1 existDrugs">&nbsp;</td>
        </tr>
      <%--   <logic:present name="patientForm" property="drugLt">
        <bean:size  id="drugSize" name="patientForm" property="drugLt"/>
        <logic:greaterThan value="0" name="drugSize">
		<%int k = 1;%>
        <logic:iterate id="drugLst" name="patientForm" property="drugLt" >
        <tr>  
      	<td width="5%"><%=k++ %></td>        
        <td width="10%"><bean:write name="drugLst" property="DRUGTYPECODE" /></td>   
       	<td width="10%"><bean:write name="drugLst" property="DRUGNAME" /></td>
       	<td width="5%"><bean:write name="drugLst" property="ROUTETYPENAME" /></td> 
       	<td width="10%"><bean:write name="drugLst" property="ROUTENAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="STRENGTHTYPENAME" /></td>
       	<td width="10%"><bean:write name="drugLst" property="STRENGTHNAME" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="DOSAGE" /></td>  
       	<td width="5%"><bean:write name="drugLst" property="MEDICATIONPERIOD" /></td> 
       	<td width="5%"><bean:write name="drugLst" property="QUANTITY" /></td> 
       	<td width="5%"><input class="but" type="button" value="Delete" name=<bean:write name='drugLst' property='DRUGSUBTYPENAME' /> id=<bean:write name='drugLst' property='DRUGSUBTYPENAME' /> onclick="javascript:confirmRemoveRow(this,'drug');"/></td>
        </tr>
        </logic:iterate></logic:greaterThan></logic:present>    --%>     
        </table>

</div>
</c:if>
   <c:if test="${ viewFl eq 'N' }" >
 <table  width="95%" >
<tr>
<td >
<button class="btn btn-primary " style="text-align:center;margin-left: 30em;" type="button" id="Save" onclick="javascript:fn_submitDrugs(id)">Approve&nbsp;</button>
<button class="btn btn-primary " style="text-align:center" type="button" id="PartialSave" onclick="javascript:fn_submitDrugs(id)">Partial Approve&nbsp;</button>
<button class="btn btn-primary " style="text-align:center" type="button" id="Back" onclick="javascript:fn_backDrugs()">Back&nbsp;</button>
</td>
<!-- <td width="20%" align="right" colspan="3">
<button class="btn btn-primary " style="text-align:center" type="button" id="Save" onclick="javascript:fn_submitDrugs(id)">Approve&nbsp;</button>
</td>
 <td width="20%" align="right" colspan="3">
<button class="btn btn-primary " style="text-align:center" type="button" id="PartialSave" onclick="javascript:fn_submitDrugs(id)">Partial Approve&nbsp;</button>
</td> 

<td width="20%"  colspan="2">
<button class="btn btn-primary " style="text-align:center" type="button" id="Back" onclick="javascript:fn_backDrugs()">Back&nbsp;</button>
</td> -->
</tr>
</table>
</c:if>

<br><br><br>
<div id="warningData" style="color:red">

</div>
 <!-- div for showing past history  -->
 
 <div id="pastHistoryModal" class="modal col-lg-12 col-md-12 col-sm-12 col-xs-12 fade">
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
 </div>
 

<html:hidden name="patientForm" property="drugs" styleId="drugs"/>
<html:hidden name="patientForm" property="caseId" styleId="caseId"/>
<html:hidden name="patientForm" property="patientScheme" styleId="patientScheme"/>
<html:hidden name="patientForm" property="hospitalId" styleId="hospitalId"/>
<html:hidden name="patientForm" property="patientNo" styleId="patientNo"/>
<html:hidden name="patientForm" property="enablePharma" styleId="enablePharma"/>
<html:hidden name="patientForm" property="investPrice" styleId="investPrice"/>
<input type="hidden" id="notify">
<script>
    $("#drugTypeCode").select2();
    $("#drugSubTypeCode").select2();
    
    $("#dosage").select2();
    $("#specialistDoctorName").select2();
    
    
    
    </script>
</form>
</body>
</html>


