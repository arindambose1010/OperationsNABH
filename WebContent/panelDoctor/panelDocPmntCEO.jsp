<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@taglib prefix="display" uri="http://displaytag.sf.net"  %> 
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Panel Doctor Payments</title>
    <script src="js/jquery-1.9.1.js"></script>
    <%@ include file="/common/include.jsp"%> 
    <%-- <%@ include file="/common/includeCalendar.jsp"%> --%> 
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/accountstyle.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/select2.min.js"></script>
<script type="text/javascript">


var checkedValue="[";
var checkedWID="[";
var amount= parseFloat("0");

$(function() { 
	$('.select2Class').select2();
   /*  $( "#selectDate" ).datepicker({ 
            changeMonth: true, 
            changeYear: true, 
      		showOn: "button", 
            buttonImage: "images/calend.gif", 
            buttonImageOnly: true,
            dateFormat: "dd-mm-yy",
            minDate:new Date(2012, 03, 01),
            maxDate:new Date()
    });  */
});
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}

function focusBox(arg)
{	
aField = arg; 
setTimeout("aField.focus()", 0);  

}

function validateMaxlength(input,e)
{
	var fieldValue=input.value;
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(fieldValue.trim().length>3000)
		{
			input.value=fieldValue.trim().substr(0,3000);
			jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 3000 words.');	
	
			if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40)
				//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
				{
				e.returnValue=true;
				}
			else
				{
				e.returnValue=false;
			 	}
		}
	
}

function goToHomePage()
{

	document.forms[0].action = "panelDocPay.do?actionFlag=getCasesForCEO";  
	document.forms[0].submit();
}


function resetList(arg)
{
	var obj =  arg;
	if (obj == null) return;
	if (obj.options == null) return;
	while (obj.options.length > 1) {
		obj.remove(1);
	}
}



function validateDate(arg1,input)
{
	
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		
		input.value="";
		jqueryShowContentMsg('Date Validation',"Please Select "+arg1);
		return false;
	}
}


function popitup(url) {
	
	newwindow=window.open(url,'PanelDoctor','width=1000, height=600, top=60,left=70,resizable=yes,titlebar=yes,status=yes,scrollbars=yes,menubar=no,toolbar=no');	
	if (window.focus) {newwindow.focus();}
	return false;
}


function popUp(docId,docName,wId)
{
	var scheme="";
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	if(document.panelDocPayForm.userType.value=="CD203")
		scheme=document.panelDocPayForm.scheme.value;
	popitup("panelDocPay.do?actionFlag=getAllCasesStatus&docId="+docId+"&docName="+docName+"&dispType=PAO&pmntType="+pmntStatusLabel+"&scheme="+scheme+"&wId="+wId);
	
}

function fn_pmntType()
{
	var pmntStatusValue=document.panelDocPayForm.paymentStatus;
	var pmntStatusLabel=pmntStatusValue.options[pmntStatusValue.selectedIndex].text;
	var indexSpace=pmntStatusLabel.lastIndexOf(" ");
	var action=pmntStatusLabel.substring(indexSpace+1);
	document.panelDocPayForm.actionSelect.value=action;
		
}
function Reset()
	{
		fn_loadImage();
		document.forms[0].action='panelDocPay.do?actionFlag=getCasesForCEO';
		document.forms[0].method="post";
		document.forms[0].submit();
	}

function fn_addCheckValues(arg,amt,wId){
	var aInputs = document.getElementsByName('individualPayment');
	//aInputs[0].checked=false;
	if(!checkedWID.match(wId))
		{
			checkedValue = checkedValue+'~'+arg;
			checkedWID=checkedWID+'~'+wId;
			amount=amount+parseFloat(amt);
		}
	else
		{
			checkedValue = checkedValue.replace('~'+arg, '');
			checkedWID = checkedWID.replace('~'+wId, '');
			amount = amount-parseInt(amt);
		
		}
	 document.panelDocPayForm.amount.value=amount;
	
}

function checkAllBoxes(arg){
	var chckAmt=0;
	var chckDoc='';
	var wIDs='';
	var spltArg;
	arg=document.getElementById(arg);
	 var aInputs = document.getElementsByName('individualPayment');
	    for (var i=0;i<aInputs.length;i++) {
		        spltArg=aInputs[i].value.split('@');
        		chckDoc=spltArg[0];
        		chckAmt=spltArg[1];
        		wIDs=spltArg[2];
	        	if(arg.checked && !aInputs[i].checked)
	    		{
	        		aInputs[i].checked = arg.checked;
	        		checkedValue = checkedValue+'~'+chckDoc;
	        		checkedWID = checkedWID+'~'+wIDs;
	        		amount=amount+parseFloat(chckAmt);
	    		}
	    	else if(!arg.checked && aInputs[i].checked)
	    		{
	    		aInputs[i].checked = arg.checked;
	    	    checkedValue = checkedValue.replace('~'+chckDoc, '');
	    	    checkedWID = checkedWID.replace('~'+wIDs, '');
	    		amount = amount-parseFloat(chckAmt);
	    		} 
        		
	        	 document.panelDocPayForm.amount.value=amount;
	        	 
	        	 if(arg.checked || !arg.checked)
	 	    		focusBox(document.getElementById("amount"));
	        }	    
}
function fn_loadImage()
{
	document.getElementById('processImagetable').style.display="";

}

function getCases(){
	fn_loadImage();
	
	document.getElementById('getcases').disabled=true;
	 if(document.panelDocPayForm.actionSelect.value == "Rejected"){
		 document.forms[0].action = "panelDocPay.do?actionFlag=viewRejctdCasesCEO";  
	 }
	 else if(document.panelDocPayForm.actionSelect.value == "Payments"){
		 document.forms[0].action = "panelDocPay.do?actionFlag=viewBankRejctdCases";  
	 }
		 else {
		 
  		 document.forms[0].action = "panelDocPay.do?actionFlag=getCasesForCEO";  
		 }
	 
  		 document.forms[0].submit();
		
	
}


function fn_submitOnConfirm(arg)
{
	var type=arg;
	document.forms[0].action="panelDocPay.do?actionFlag=getPayClaimForCases&checkedDocValues="+checkedValue+"&action="+type;
    document.forms[0].submit();
	}
function fn_generateFile(arg)
{
	var caseType=arg;
	
	var checkFlag = 'N';
	 var aInputs = document.getElementsByName('individualPayment');
	for (var i = 0; i < aInputs.length; i++) {
	  if(aInputs[i].checked)
		  {checkFlag='Y';
		 
		  break;}
	  else{
		  checkFlag='N';
		 
	  }
	}
	
	if(checkFlag!=null && checkFlag=='Y'){
		var fr = partial(fn_submitOnConfirm,caseType);
		jqueryConfirmMsg('Claim Payment Confirmation','Do you want to proceed?',fr);
	
	}
	else{
		var fr = partial(focusBox,document.getElementsByName('individualPayment'));
		jqueryErrorMsg('Panel Doctor Payment Mandatory Field',"Please select atleast one case for payment",fr);
		return;
	}
}

function fn_pagination(pageId,end_index)
{
	 if(document.panelDocPayForm.actionSelect.value == "Rejected")
	 {
		 document.forms[0].action = "panelDocPay.do?actionFlag=viewRejctdCasesCEO&pageId="+pageId+"&end_index="+end_index;  
	 }
	 else if(document.panelDocPayForm.actionSelect.value == "Payments")
	 {
		 document.forms[0].action = "panelDocPay.do?actionFlag=viewBankRejctdCases&pageId="+pageId+"&end_index="+end_index;  
	 }
	 else
	 {
	 
 		 document.forms[0].action = "panelDocPay.do?actionFlag=getCasesForCEO&pageId="+pageId+"&end_index="+end_index;  
	 }
	
	document.forms[0].method="post";
	document.forms[0].submit();
}

function viewPreviousPages(pageNo,noOfPages,selectedPage,end_index)
	{
		var pageDisplays='';
		pageDisplays=pageDisplays+'<ul>';
		var pageNoLim=pageNo;
		var minPageNo=pageNo-10;
		if(minPageNo>1)
			{
				pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+minPageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
			}
		for(var i=minPageNo;i<pageNoLim;i++)
			{
				if(selectedPage==i)
					{
						//pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
						pageDisplays=pageDisplays+' <li class="active"><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
					}
				else
					{
						//pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
						pageDisplays=pageDisplays+' <li><a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
					}
			}
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
		document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
	}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
	{
		var pageDisplays='';
		pageDisplays=pageDisplays+'<ul>';
		var pageNoLim=pageNo+10;
		if(pageNoLim>noOfPages)
			{
				pageNoLim=noOfPages;
			}
		
		pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li>';
		for(var i=pageNo;i<=pageNoLim;i++)
		{
			if(selectedPage==i)
			{
				//pageDisplays=pageDisplays+'<b>'+i+'</b>&nbsp;';
				pageDisplays=pageDisplays+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
			}
			else
			{
				//pageDisplays=pageDisplays+'<a href="javascript:fn_pagination('+i+','+end_index+')"><b>'+i+'</b></a>&nbsp;&nbsp;';
				pageDisplays=pageDisplays+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
			}
			
		}
		if(pageNoLim<noOfPages)
			{
				pageDisplays=pageDisplays+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer;"></span></li></ul>';
			}
		document.getElementById("pageNoDisplay").innerHTML=pageDisplays;
	}

</script>
</head>
<style>
body{font-size:1.3em !important;}
</style>
<body style="height:100%" >
<center>
<html:form action="/panelDocPay.do" method="post" >
<html:hidden name="panelDocPayForm" property="result" />
<html:hidden name="panelDocPayForm" property="totalAmt" />
<html:hidden name="panelDocPayForm" property="actionSelect" />
<html:hidden name="panelDocPayForm" property="userType" />
<table width="95%" style="margin:0 auto" ><tr><td><!--  border="1" -->
<br>
<table class="tbheader" >
<tr><th style="text-align:center"><b>Panel Doctor Payment</b></th></tr>
</table>
<table class="contentTable">
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td style="WIDTH: 5em"></td>
<logic:notEqual value="CD203" name="panelDocPayForm" property="userType" >
<td style="WIDTH: 15em"></td>
</logic:notEqual>
<td  class="labelheading1 tbcellCss" style="WIDTH: 15em"><b>Payment Status : &nbsp;</b></td>
<td class="tbcellBorder" >
<html:select name="panelDocPayForm" styleClass="select2Class" property="paymentStatus" style="WIDTH: 280px" onchange="javascript:fn_pmntType()">
 <logic:notEmpty name="panelDocPayForm" property="paymentStatusList">
<html:options collection="PaymentStatusList" property="IDVAL" labelProperty="VALUE"/>
</logic:notEmpty>
</html:select>
</td>
<logic:equal value="CD203" name="panelDocPayForm" property="userType" >
<td style="WIDTH: 4em"></td>
<td style="WIDTH: 4em"></td>
<td class="labelheading1 tbcellCss" style="WIDTH: 100px"><b>Scheme : &nbsp;</b></td>
<td class="tbcellBorder" >
<html:select name="panelDocPayForm" property="scheme" style="WIDTH: 150px">
<html:option value="CD201" key="CD201">Andhra Pradesh</html:option>
<html:option value="CD202" key="CD202">Telengana</html:option>
 <%-- <logic:notEmpty name="panelDocPayForm" property="schemeList">
<html:options collection="schemeStatusList" property="IDVAL" labelProperty="VALUE"/>
</logic:notEmpty> --%>
</html:select>
</td>
</logic:equal>

</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
</table>

<table class="contentTable">
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr>
<td style="WIDTH: 8em"></td>
<td style="WIDTH: 25em"></td><td><button class="btn btn-primary"  type="button" id="getcases" onclick="javascript:getCases()">Get Cases</button>
</td>
<td style="WIDTH: 2em"></td><td style="display: none"><button class="btn btn-primary"  type="button" id="reset" onclick="javascript:Reset()">Reset</button>
</tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
</table>
</td></tr>
</table>

 <div id="processImagetable" style="top:60%;width:90%;position:absolute;z-index:60;height:100%;display:none">
   <table border="0" align="center" width="90%" style="height:400" >
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

<logic:equal value="List Not Found" name="panelDocPayForm" property="flag">
<table border='0' width=95% align="center"><tr><td>
</td></tr>
<tr><td>
<table border="0" width="95%"  cellpadding="1" cellspacing="1" align="center">
<tr align="center">
<td>
No Records Found
</td>
</tr>
<tr><td>&nbsp;</td></tr>
<tr><td>&nbsp;</td></tr>
</table>
</td></tr></table>
</logic:equal>
<logic:equal value="List Found" name="panelDocPayForm" property="flag" >
 <logic:notEmpty name="panelDocPayForm"  property="panelDocList"> 
<table border='0' width=95% align="center"><tr><td>
<!-- <table class="tbheader1" border=0 >
<tr><th><b>Panel Doctor Payment </b></th></tr>
</table> -->

 <table width=100% align="center">
 	<tr>
 		<td width="20%" >
 			Total No of Records found:${noOfRecords}
 		</td>
 		<td width="20%" >
 			Displaying Records from ${start_index} to ${endresults}
 		</td>
 		<td  id="pageNoDisplay" width="30%" align="center">
	<ul class="pagination">
<%
int noOfPages = ((Integer) request.getAttribute("lastPage")).intValue();
int selectedPage = ((Integer) request.getAttribute("pageId")).intValue();
int end_index=((Integer) request.getAttribute("end_index")).intValue();
int pageNo=0;
int a=selectedPage/10;
int minVal=(a*10);
if(selectedPage%10==0)
	{
		minVal=minVal-10;
	}
int maxVal=minVal+10;
if(maxVal>noOfPages)
{
	maxVal=noOfPages;
}
if(minVal>=10&&minVal!=noOfPages)
{
	minVal=minVal+1;
	%>
	<%-- <a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Prev</a>&nbsp; --%>
		<li><a href="javascript:viewPreviousPages(<%=minVal%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)"><span class="glyphicon glyphicon-backward" style="cursor: pointer;"></span></a></li>
	<%
}
else
{
	minVal=minVal+1;
}
for(int i=minVal;i<=maxVal;i++)
{
pageNo=i;
	if(selectedPage==pageNo)
	{
		%>
		<%-- <b><%=pageNo%></b> --%>
		<li class="active"><span><%=pageNo%></span></li> 
		<%
	}
	else
	{
		%>
		<%-- <a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><b><%=pageNo%></b></a>&nbsp; --%>
		<li><a href="javascript:fn_pagination(<%=pageNo%>,<%=end_index%>)"><%=pageNo%></a></li>
		<%
	}
}
if(pageNo<noOfPages)
	{
		%>
		<%-- <a href="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)">Next</a> --%>
		<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=pageNo+1%>,<%=noOfPages%>,<%=selectedPage%>,<%=end_index%>)" style="cursor: pointer;"></span></li>
		<%
	}
%>
</ul>
 		</td>
 				<td width="30%"> 		<ul class="pagination">
	<li class="lispacing">Show results in sets of </li><c:if test="${end_index ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
												<c:if test="${end_index eq 10}"><li class="active"><span>10</span></li></c:if>	
												<c:if test="${end_index ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
												<c:if test="${end_index eq 20}"><li class="active"><span>20</span></li></c:if>
												<c:if test="${end_index ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
												<c:if test="${end_index eq 50}"><li class="active"><span>50</span></li></c:if>
												<c:if test="${end_index ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
												<c:if test="${end_index eq 100}"><li class="active"><span>100</span></li></c:if><!-- </td> -->
												
	</ul> </td> 
 	</tr>
 </table>
 </td></tr>
</table>

 <table  width=95% align="center" >
 
 <tr><td>
<%-- <%int i = 1;%>
<display:table  id="panelDocPayVO"  name="panelDocPayForm.panelDocList" pagesize="100" style="width:100%;align:center;border:2;rowspan:5" export="false" requestURI="/panelDocPay.do"  cellpadding="2" cellspacing="2" decorator="com.ahct.panelDoctor.util.PanelDocDecorator">


<display:column value="<%=i++ %>" title="S No"  style="text-align:center;width:5%"  />

<display:column   property="DOC_NAME" title="Panel Doctor" style="width:15%"/>
<display:column   property="ACCOUNTNO" title="Account No" style="width:15%"/>
<display:column  property="CL_APP_AMT" title="Claim Approved Amount" style="text-align:right;width:11%"/>
<display:column  property="CL_REJ_AMT" title="Claim Rejected Amount" style="text-align:right;width:11%"/>
<display:column  property="CL_PEND_AMT" title="Claim Pending Amount" style="text-align:right;width:11%"/>
<display:column  property="PR_APP_AMT" title="Preauth Approved Amount" style="text-align:right;width:11%"/>
<display:column  property="PR_REJ_AMT" title="Preauth Rejected Amount" style="text-align:right;width:11%"/>
<display:column  property="PR_PEND_AMT" title="Preauth Pending Amount" style="text-align:right;width:11%"/>
<display:column  property="AMOUNT" title="Total Amount" style="text-align:right;width:11%"/>
<display:column property="ID" title="<input type='checkbox' name='checkId' id='check_id' title='select All' onclick='javascript:checkAll(this);' />" media="html" >
  
</display:column>
</display:table> --%>
<table>
		<tr>
				<td class="tbheader1" >
					S.No
				</td>
				<td class="tbheader1" >
					Panel Doctor
				</td>
				<td class="tbheader1" >
					Month-Year  
				</td>
				<td class="tbheader1" >
					Claim Approved Amount  
				</td>
				<td class="tbheader1" >
					Claim Rejected Amount  
				</td>
				<td class="tbheader1" >
					Claim Pending Amount 
				</td>
				<td class="tbheader1" >
					Preauth Approved Amount
				</td>
				<td class="tbheader1" >
					Preauth Rejected Amount
				</td>
				<td class="tbheader1" >
					Preauth Pending Amount 
				</td>
				<td class="tbheader1" >
					Total Amount
				</td>
				<td class="tbheader1" >
					<html:checkbox name="panelDocPayForm" property="checkAll" styleId="checkAll"  onclick="checkAllBoxes(this.id)" title="Select All"></html:checkbox>
				</td>	
		</tr>
		<%int i = 1;%>
		<logic:iterate name="panelDocPayForm" property="panelDocList" id="results">
		<tr>
			<td class="tbcellBorder" >
				<%=i++ %>
			</td>
			<td class="tbcellBorder" >
				<a href="javascript:popUp('${results.DOC_ID}','${results.DOC_NAME}','${results.WID}')"><font color="#FF4444"><bean:write name="results" property="EMPNAME" />&nbsp;<bean:write name="results" property="DOC_NAME" /></font></a>
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="MONTH"/>-<bean:write name="results" property="YEAR"/>
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="CLAIM_APRV_AMT" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="CLAIM_REJ_AMT" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="CLAIM_PEND_AMT" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="PREAUTH_APRV_AMT" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="PREAUTH_REJ_AMT" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="PREAUTH_PEND_AMT" />
			</td>
			<td class="tbcellBorder" >
				<bean:write name="results" property="TOTAL_PNLDOC_AMT" />
			</td>
			<td class="tbcellBorder" >
				<html:checkbox name="panelDocPayForm" property="individualPayment" styleId="individualPayment"  onclick="fn_addCheckValues('${results.DOC_ID}','${results.TOTAL_PNLDOC_AMT}','${results.WID}')" value="${results.DOC_ID}@${results.TOTAL_PNLDOC_AMT}@${results.WID}" title="Click to select"></html:checkbox>
			</td>
		</tr>
		</logic:iterate>
</table>

<center>
<table border='0' width="30%" align="center">
<tr><td>&nbsp;</td></tr>


<tr>
<td class="labelheading1 tbcellCss" style="WIDTH:50%"><b>Sum of amount : </b></td>
<td  class="labelheading1 tbcellCss Label5" style="WIDTH:50%" ><b><html:text name="panelDocPayForm" size="14" styleId="amount" property="amount" readonly="true" /></b>
</td>
</tr>
<!-- <tr><td>&nbsp;</td></tr>
 --><%-- <tr>
<td class="labelheading1 tbcellCss" ><b>Remarks : </b></td>
<td  class="labelheading1 tbcellCss Label5" style="WIDTH: 2em" ><b><html:textarea  name="panelDocPayForm"  property="remarks" style="WIDTH: 190px"  onkeypress="validateMaxlength(this,event);"> </html:textarea></b>
</td>
</tr> --%>
</table> 
</center>

<logic:equal value="PayNow" name="panelDocPayForm" property="rejId" >
<table class="contentTable">
<tr><td>&nbsp;</td></tr>
<tr>
<td style="WIDTH: 8em"></td>

<td style="WIDTH: 25em"></td><td ><button class="btn btn-danger"  type="button" id="actionSubmit" onclick="javascript:fn_generateFile('PayNow')">Pay Now</button></td>
</tr>
</table>
</logic:equal>
<logic:equal value="PayRejCase" name="panelDocPayForm" property="rejId" >
<table class="contentTable">
<tr><td>&nbsp;</td></tr>
<tr>
<td style="WIDTH: 8em"></td>

<td style="WIDTH: 25em"></td><td ><button class="btn btn-danger"  type="button" id="actionSubmit" onclick="javascript:fn_generateFile('PayRejCase')">Pay Now</button></td>
</tr>
</table>
</logic:equal>
</td></tr>
</table> 
</logic:notEmpty> 
</logic:equal>
<c:if test="${saveMsg ne null}">
<script>
jqueryInfoMsg('Panel Doctor Information','${saveMsg}',goToHomePage);
</script>
</c:if>
<c:if test="${failAccList ne null}">
<script>

var failList='${failAccList}';
//failList=failList.replace(",", "  <br> ");
jqueryInfoMsg('Panel Doctor Information','Some Claims Of Doctor(s) Were Not Processed Due To Unavailability of Bank Details:  <br>'+failList);
</script>
</c:if>
</html:form>
</center>
</body>
</html>