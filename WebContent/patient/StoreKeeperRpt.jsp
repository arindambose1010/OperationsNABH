<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="/common/include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="js/select2.min.js"></script>
<style type="text/css">
.border{
border : 1 px solid white;
}
#printable { display: none; }
	@page 
    {
        size:  auto;   /* auto is the initial value */
        margin: 5mm;  /* this affects the margin in the printer settings */
    }
    @media print
    {
        #non-printable { display: none; }
        #printable { display: flex;
 
       justify-content:center;
         }
        #logo img {
		display:none;
		
	}
	#logo:after {
		content:url(images/TG_logo2.png);
		
	}
      
    }
</style>
<script>
$(function() {
	var table = document.getElementById("miscActTbl"); 
	 
	 var rows = document.getElementById("miscActTbl").getElementsByTagName("tr").length;
	  for(var i=0;i<rows-1;i++)
		 {
		 var j=i+1;

		 $('#ExpDt'+j).datepicker({
				autoclose:true,
				format : 'dd-mm-yyyy',
				todayHighlight:true,
				clearBtn:true,
				startDate:'+180d',
				//endDate:new Date(),
		 });
		 $('#InvDt'+j).datepicker({
				autoclose:true,
				format : 'dd-mm-yyyy',
				todayHighlight:true,
				clearBtn:true,
				startDate:'01/11/2020',
				//endDate:new Date(),
		 });
		 }
});

				
	
	</script>


<script type="text/javascript">
function generate()
{
	if(document.getElementById('indent_no').value=="" || document.getElementById('indent_no').value==null||document.getElementById('indent_no').value=='-1'){
		alert("Please Select PO NO");
		return false;
	}
		fn_loadImage();
		
		var skillsSelect = document.getElementById("indent_no");
		var indNo = skillsSelect.options[skillsSelect.selectedIndex].text;
		document.forms[0].action="patientDetailsNew.do?actionFlag=getlowStkUpdt&indNo="+indNo;
    	document.forms[0].submit();
	
}
function fn_reset()
{
	document.getElementById("indent_no").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getlowStkUpdt";
	document.forms[0].submit();
	
}

 
 function fn_submit()
 {
 		 var storeList="";
 		   
 		var table = document.getElementById("miscActTbl"); 
 			 
 				 var rows = document.getElementById("miscActTbl").getElementsByTagName("tr").length;
 				 var flag=0;
 				 var formData = new FormData();
 				
 				  for(var i=0;i<rows-1;i++)
 					 {
 					 var j=i+1;
 					 var id="chb"+j;
 					
 					 
 					 if(document.getElementById(id).checked==true)
 					 {  var rowId = i+1; 
 					
 					if(document.getElementById("Re_Qt"+j).value!=null&&document.getElementById("Re_Qt"+j).value!="")
 						var recvQt =document.getElementById("Re_Qt"+j).value; 
 					 else {
 						 alert('Please Enter RECEIVED QTY');
 					focusBox(document.getElementById("Re_Qt"));
 					return false;
 					 }
 						//var recvQt ="NA";
 					if(document.getElementById("Bt_No"+j).value!=null&&document.getElementById("Bt_No"+j).value!="")
 						var btcNo =document.getElementById("Bt_No"+j).value; 
 					else {
						 alert('Please Enter BATCH NO');
					focusBox(document.getElementById("Bt_No"));
					return false;
					 }
 					if(document.getElementById("ExpDt"+j).value!=null&&document.getElementById("ExpDt"+j).value!="")
 						var expDt =document.getElementById("ExpDt"+j).value; 
 					else {
						 alert('Please Enter EXPIRY DATE');
					focusBox(document.getElementById("ExpDt"));
					return false;
					 }
 					if(document.getElementById("InvNo"+j).value!=null&&document.getElementById("InvNo"+j).value!="")
 						var invNo =document.getElementById("InvNo"+j).value; 
 					else {
						 alert('Please Enter INVOICE NO');
					focusBox(document.getElementById("InvNo"));
					return false;
					 }
 					if(document.getElementById("InvDt"+j).value!=null&&document.getElementById("InvDt"+j).value!="")
 						var invDt =document.getElementById("InvDt"+j).value; 
 					else {
						 alert('Please Enter INVOICE DATE');
					focusBox(document.getElementById("InvDt"));
					return false;
					 }
 					var indentNo=document.getElementById("indNo"+j).value;
 					 var dugId=document.getElementById("drugID"+j).value;
 					 //alert(recCnt);
 					if(document.getElementById("recCnt"+j).value!=null && document.getElementById("recCnt"+j).value!="NA" && document.getElementById("recCnt"+j).value!="")
 						var recCnt=document.getElementById("recCnt"+j).value;
 					 else 
 						var recCnt ="0";
 					 
 					 var indCnt=document.getElementById("indCnt"+j).value;
					var drugty=document.getElementById("drugTy"+j).value;
					var price=document.getElementById("price"+j).value;
					var mnfId=document.getElementById("mnfId"+j).value;
					var disId=document.getElementById("disId"+j).value;
					var disp=document.getElementById("disp"+j).value;
					var disNm=document.getElementById("disNm"+j).value;
					var mnfNm=document.getElementById("mnfNm"+j).value;
					var drgName=document.getElementById("drgName"+j).value
 					var a=parseInt(recCnt);  
 					var b=parseInt(recvQt);
 					var c=parseInt(indCnt);
 					var d=a+b;
 					if(d>c){
 						alert("Sum of PO RECEIVED and RECEIVED QTY must not be Greater than PO Qty");
 						return false;
 						document.getElementById("Re_Qt"+j).value="";
 					}

 					 flag+=1;
 						 if(flag>0)
 							 {
 							
 							 storeList=storeList+dugId+"~"+indentNo+"~"+recvQt+"~"+btcNo+"~"+expDt+"~"+invNo+"~"+invDt+"~"+drugty+"~"+mnfId+"~"+disId+"~"+price+"~"+disp+"~"+disNm+"~"+mnfNm+"~"+drgName+"@";
 							
 							}
 						
 					 }

 				
 				 }
 				 //alert(storeList);
 				 formData.append('storeList', storeList); 
 				
  				 if(flag===0)
  				 {
  				 alert("Please select altleast one Check box to Submit");
				 return false;
  				 }
  				 
  				if(confirm("Do you want to Submit?"))
  	 			 {
  	 			     $(':input[type="button"]').prop('disabled', true);
  	 			     fn_loadImage();
  	 			    $.ajax({
  					        url: 'patientDetailsNew.do?actionFlag=submitStore',
  					        type: 'POST',
  							data: formData,
  							contentType: false,
  							enctype: 'multipart/form-data',
  							processData: false,
  					        success: function(data) {		        	
  					        	data=data.replace("*","");
  					        	data =data.trim();
  					        	if(data.length >0){
  						        	
  								 	var result=data;
  									if(result!=null){
  										var resultArray = result.split("~");
  										if(resultArray[0]=='FAIL'){ 												
  											alert("Failed");
 											parent.document.getElementById("middleFrame").src="patientDetailsNew.do?actionFlag=getlowStkUpdt";
  										} else
  											{
  											alert("Received Qty has been updated successfully");
 											parent.document.getElementById("middleFrame").src="patientDetailsNew.do?actionFlag=getlowStkUpdt";
  											}
  										fn_removeLoadingImage();
  										
  					 				}
  					        	}						
  							}
  						});
  	 			 }
 			 
 	}
 

function fn_enable(arg){
	if($('#chb'+arg).is(':checked')){
		$('#sno'+arg).attr("disabled",false);
		
	}
	else{
		$('#sno'+arg).attr("disabled",true);
		
	}
}
function fn_checkedEnable(arg){
	if($('#chb'+arg).is(':checked')){
		$('#Re_Qt'+arg).attr("disabled",false);
		$('#Bt_No'+arg).attr("disabled",false);
		$('#ExpDt'+arg).attr("disabled",false);
		$('#InvNo'+arg).attr("disabled",false);
		$('#InvDt'+arg).attr("disabled",false);		
	}
	else{
		$('#Re_Qt'+arg).attr("disabled",true);
		$('#Bt_No'+arg).attr("disabled",true);
		$('#ExpDt'+arg).attr("disabled",true);
		$('#InvNo'+arg).attr("disabled",true);
		$('#InvDt'+arg).attr("disabled",true);
	}
}
function fn_display(){
	var table = document.getElementById("miscActTbl"); 
		 
			 var rows = document.getElementById("miscActTbl").getElementsByTagName("tr").length;
			 

			  for(var i=0;i<rows-1;i++)
				 {
				 var j=i+1;
				 var id="chb"+j;
				 var id1='poNo'+j;
				 
				 
				 if(document.getElementById(id1).value!=null && document.getElementById(id1).value!="" && document.getElementById(id1).value!="NA"){
					 document.getElementById(id).style.display="none";
						
					}
				 if(document.getElementById(id1).value!=null && document.getElementById(id1).value!="" && document.getElementById(id1).value=="NA"){
					 document.getElementById(id).style.display="";
						
					}
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



function showinSetsOf(num)
{   

		document.forms[0].rowsPerPage.value=num; 
		document.forms[0].showPage.value='1'; 
		fn_pagination(1,num);
}

function showPagination(num)
{
		document.forms[0].showPage.value=num; 
		fn_pagination(num,document.forms[0].rowsPerPage.value);		
}
	
function fn_pagination(pageId,endIndex)
{	
  var dispname=document.getElementById('dispname').value;
  var drugName=document.getElementById('dispDrugID').value;
  fn_loadImage();
  var url="./patientDetailsNew.do?actionFlag=getIndentReportsNew&dispname="+dispname+"&pageId="+pageId+"&endIndex="+endIndex+"&drugName="+drugName+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";
 document.forms[0].action=url;
 document.forms[0].submit(); 
}


function viewPreviousPages(minVal,totalPages,selectedPage,endIndex)
{
	var newMinVal=minVal-10;
	var pageNoDisplay='';
	pageNoDisplay=pageNoDisplay+'<ul class="pagination">';
	if(newMinVal >1)
		{
			pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+newMinVal+','+totalPages+','+selectedPage+','+endIndex+')" style="cursor: pointer; top: 0px;"></span></li>';
		}
	for(var i=newMinVal;i<minVal;i++)
		{
			if(selectedPage==i)
			{
				pageNoDisplay=pageNoDisplay+' <li class="active"><a href="javascript:fn_pagination('+i+','+endIndex+')">'+i+'</a></li>';
			}
		else
			{
				pageNoDisplay=pageNoDisplay+' <li><a href="javascript:fn_pagination('+i+','+endIndex+')">'+i+'</a></li>';
			}
		}
	
	pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+minVal+','+totalPages+','+selectedPage+','+endIndex+')" style="cursor: pointer; top: 0px;"></span></li></ul>';
	document.getElementById("pageNoDisplay").innerHTML=pageNoDisplay;

}
function viewNextPages(pageNo,noOfPages,selectedPage,end_index)
{
	var pageNoDisplay='';
	pageNoDisplay=pageNoDisplay+'<ul class="pagination">';
	var minVal=pageNo;
	var maxVal=minVal+9;
	if(maxVal>noOfPages)
		maxVal=noOfPages;

	pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-backward" onclick="javascript:viewPreviousPages('+pageNo+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer; top: 0px;"></span></li>';
	for( var i=minVal ; i<=maxVal ; i++)
		{
			if(selectedPage==i)
				{
					pageNoDisplay=pageNoDisplay+'<li class="active"><a href=javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
				}
			else
				{
					pageNoDisplay=pageNoDisplay+'<li> <a href="javascript:fn_pagination('+i+','+end_index+')">'+i+'</a></li>';
				}
		}
	if(maxVal<noOfPages)
		{
			pageNoDisplay=pageNoDisplay+'<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages('+i+','+noOfPages+','+selectedPage+','+end_index+')" style="cursor: pointer; top: 0px;"></span></li></ul>';
		}
	
	document.getElementById("pageNoDisplay").innerHTML=pageNoDisplay;
}

 function fn_getDrugList()
 {
 	document.forms[0].dispDrugID.value="-1";
 	  $("#dispDrugID").select2().val("");
 		var xmlhttp;
 	   var url;
 	   var drugType = document.getElementById("drugTypeID").value;
 	   if(drugType=='-1')
 		   {  	   
 	   	   document.forms[0].dispDrugID.value="-1";
 	   	   $("#dispDrugID").select2().val("");
 	      
 		   return false;
 		   }
 	  else
 		   {
 		 var yearFlag='${yearFlag}';
 		  fn_loadImage();
 			document.forms[0].action="patientDetailsNew.do?actionFlag=viewlowStockReport&yearFlag="+yearFlag+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";
 	    	document.forms[0].submit();
 }
 }
 
 function setCheckAll() {
	  document.querySelector('input.checkAll').checked =
	     document.querySelectorAll('.tbheader1 border').length ==
	     document.querySelectorAll('.tbheader1 border:checked').length;
	}

	 function checkAll() 
	 {
	     var allRows = document.getElementsByTagName("input");
	     for (var i=0; i < allRows.length; i++) {
	         if (allRows[i].type == 'checkbox') 
	         {
	             allRows[i].checked = true;
	         }
	     }

	 }
 function receivValue(Recevied,cnt)
 {
	
	 var recvQt = "Re_Qt"+cnt;
		var indCnt = "indCnt"+cnt;
		var recCnt= "recCnt"+cnt;
		
		var recvQt=document.getElementById(recvQt).value;
		var indCnt=document.getElementById(indCnt).value;
   		if(document.getElementById(recCnt).value!=null &&document.getElementById(recCnt).value!="NA" && document.getElementById(recCnt).value!="")
 		var recCnt=document.getElementById(recCnt).value;
	    else
			var recCnt ="0"; 
			var a=parseInt(recCnt);  
			var b=parseInt(recvQt);
			var c=parseInt(indCnt);
			var d=a+b;
			var regAlphaNum=/^[0-9\/\ ]+$/;
			if(recvQt.trim().search(regAlphaNum)==-1)
				{
				alert("Received Quantity should be numeric only");
				document.getElementById("Re_Qt"+cnt).value="";
				return false;
				}
			if(d>c){
				document.getElementById("Re_Qt"+cnt).value="";

				alert("Sum of PO RECEIVED and RECEIVED QTY must not be Greater than PO Qty");
				//alert("Re_Qt"+cnt);
				return false;
			}
 	
 }
	 
function fn_getIndentNo(wcName){
		fn_loadImage();
			var url = "patientDetailsNew.do?";
			var dispId = document.getElementById("wellnesscenter").value;
			var params = {actionFlag:"getIndentNo",dispId:dispId,wcName:wcName,ajaxCal:'Y'};
			$.post(url,params, function(data){
				try{
					var result = $.parseJSON(data);
					if(result!=null){
						$('#indentNoId').show();
						$('#indent_no option:not(:first)').remove();
						$.each(result, function(data,value){
							var s ='<option value="'+value.ID+'">'+value.VALUE+'</option>';
							$('#indent_no').append(s);
							
						});
						
					}
					fn_removeLoadingImage();
				}catch(e){}
			})
			.fail(function(){
			});
			

	}
$(document).ready(function(){
			$('#select_all').on('click',function(){
			    if(this.checked){
			        $('.tbcellBorder').each(function(){
			            this.checked = true;
			        });
			    }else{
			         $('.tbcellBorder').each(function(){
			            this.checked = false;
			        });
			    }
			});

			$('.tbcellBorder').on('click',function(){
			    if($('.tbcellBorder:checked').length == $('.tbcellBorder').length){
			        $('#select_all').prop('checked',true);
			    }else{
			        $('#select_all').prop('checked',false);
			    }
			});
			
});

 function fn_print(id,type,heading){	
		
		var currentDate=new Date();
		var dd = currentDate.getDate(); 
		var mm = currentDate.getMonth()+1;
		var yyyy = currentDate.getFullYear(); 
		var hr = currentDate.getHours();
		var min =currentDate.getMinutes();
		var sec = currentDate.getSeconds();
		
		if(dd<10){dd='0'+dd} 
		if(mm<10){mm='0'+mm} 
		if(hr<10){hr ='0'+hr}
		if(min<10){min ='0'+min}
		if(sec<10){sec ='0'+sec}
		
		document.getElementById("dateToday").innerHTML =dd+'-'+mm+'-'+yyyy+' '+hr+':'+min+':'+sec; 
		
				 var original1 = document.getElementById("headerPrint");
				var clone1 = original1.cloneNode(true);
				
				var div = document.createElement("div");	
				var head=document.createElement("span");
				var original = document.getElementById(id);
				var clone = original.cloneNode(true);
				
				clone.style.border="1px solid black";
				clone.style.borderCollapse="collapse";
				clone.style.font="14px";
				clone1.style.display="";
				div.style.padding = "0px 10px 10px 10px";

				 for(var k=0;k<clone.children[0].children.length;k++)
				{
					var childNodes1=clone.children[0].children[k].children;	
					for(var i=0;i<childNodes1.length;i++)
					{
						childNodes1[i].style.border="1px solid black";
					}
				}
				
				div.appendChild(clone1);
				div.appendChild(head);
			 div.appendChild(clone);
			 var newWin= window.open("");
			 newWin.document.write(div.outerHTML);
			    newWin.document.close(); 
			    newWin.focus();
			    newWin.print();
			    newWin.close();	
			
	   	
	}

</script>
</head>
<body>
<html:form action="/patientDetailsNew.do">
<div id="processImagetable" style="top:50%;left:45%;display:none;position:absolute;z-index:60;height:100%">
<table border="0"  width="100%" style="height:400" >
   <tr>
      <td>
        <div id="processImage" >
          <img src="images/Progress.gif" width="100" height="100" border="0" ></img>
         </div>
       </td>
     </tr>
  </table>
</div>
<table style="width:80%;margin:1% auto">
	<tr>
		 <th colspan="8" class="tbheader">Drug Updating Screen</th>

</tr>
	<tr>
	
		
	   <td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>PO NO</b>&nbsp;</td>
	    <td align="left"  class="tbcellBorder" style="width:16%" >

           <html:select  property="indent_no" name="patientForm" styleId="indent_no"   title="Select Indent NO">
						 <option value="-1">----------Select-------------</option>
						 <html:options collection="indentNolist"  property="VALUE" labelProperty="VALUE"></html:options>
						</html:select> 

	    </td>   
 
<!-- </td> -->
</tr>

<tr></tr><tr></tr><tr></tr>
	<tr align="center">
	<td colspan="2"  align="right">
		<input class="but" type="button"  value="Update" onclick="javascript:generate()"/></td>
		<td colspan="1"  align="left">
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" /></td>
	   
	</tr>
	<tr></tr><tr></tr><tr></tr>
	
</table>


<logic:present name="storeList" >
<bean:size id="size" name="storeList"/>

<logic:greaterThan value="0" name="size"> 


<table id="miscActTbl"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1 border" ><!-- <input type="checkbox" id="select_all"/><input class="checkAll" type="checkbox" onclick="checkAll(this)"> -->PO NO</th>
<th class="tbheader1 border" >PO DATE</th>
<th class="tbheader1 border" >INDENT NO</th>
<th class="tbheader1 border" >INDENT DATE</th>
<th class="tbheader1 border" >DRUG NAME</th>
	<th class="tbheader1 border" >DISTRIBUTOR NAME</th>
	<th class="tbheader1 border" >PO Qty(INDENTED Qty)</th>
	<th class="tbheader1 border" >PO RECEIVED</th>
	<th class="tbheader1 border" >RECEIVED QTY</th>
	<th class="tbheader1 border" >BATCH NO</th>
	<th class="tbheader1 border" >EXPIRY DATE</th>
	<th class="tbheader1 border" >INVOICE NO</th>
	<th class="tbheader1 border" >INVOICE DATE</th>
</tr>

<logic:iterate name="storeList"  id="labelBean" indexId="cnt">
 <tr>
	<logic:notEmpty name="labelBean" property="PO_ID">
	<td align="left" class="tbcellBorder" ><input class="tbcellBorder" style="display:" type="checkbox" name="chb" id="chb${cnt+1}" onclick="fn_checkedEnable('${cnt+1}')"><bean:write name="labelBean" property="PO_ID"/> </td>
	<input type="hidden" id="PoNo${cnt+1}"  value="<bean:write name="labelBean" property="PO_ID"/>" >
	</logic:notEmpty>
	
	 
	 <logic:notEmpty name="labelBean" property="PO_DATE">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PO_DATE"/> </td>
	<input type="hidden" id="PoDt${cnt+1}"  value="<bean:write name="labelBean" property="PO_DATE"/>" >
	</logic:notEmpty>
	
	 
	<logic:notEmpty name="labelBean" property="INDENT_ID">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INDENT_ID"/> </td>
	<input type="hidden" id="indNo${cnt+1}"  value="<bean:write name="labelBean" property="INDENT_ID"/>" >
	</logic:notEmpty>
	 
	<logic:notEmpty name="labelBean" property="INDENT_DT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INDENT_DT"/></td>
	<input type="hidden" id="indDt${cnt+1}" value="<bean:write name="labelBean" property="INDENT_DT"/>" >
	</logic:notEmpty>
	
	 
	 <logic:notEmpty name="labelBean" property="DRUG_NAME">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUG_NAME"/></td>
	<input type="hidden" id="drgName${cnt+1}"  value="<bean:write name="labelBean" property="DRUG_NAME"/>" >
	</logic:notEmpty>

	 <logic:notEmpty name="labelBean" property="DISTRIBUTOR_NAME">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISTRIBUTOR_NAME"/></td>
	<input type="hidden" id="dstrName${cnt+1}"  value="<bean:write name="labelBean" property="DISTRIBUTOR_NAME"/>" >
	</logic:notEmpty>
	
	 
	 
	 
	 <logic:notEmpty name="labelBean" property="INDENT_COUNT_CONSTANT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INDENT_COUNT_CONSTANT"/></td>
	<input type="hidden" id="indCnt${cnt+1}" value="<bean:write name="labelBean" property="INDENT_COUNT_CONSTANT"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="INDENT_COUNT_CONSTANT">
	 <td align="left" class="tbcellBorder" >NA</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="RECEIVED_QTY">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="RECEIVED_QTY"/></td>
	<input type="hidden" id="recCnt${cnt+1}" value="<bean:write name="labelBean"  property="RECEIVED_QTY"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="RECEIVED_QTY">
	 <td align="left" class="tbcellBorder" ><input type="text" style="width:70px;" id="recCnt${cnt+1}" value="NA" disabled>
	 </td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="RECQTY">
	<td align="left" class="tbcellBorder" ><input type="text" id="Re_Qt${cnt+1}" onchange="receivValue(this.value,${cnt+1});"  disabled="true" style="width: 110px;" value="<bean:write name="labelBean" property="RECQTY"/>" ></td>
	<bean:write  name="labelBean" property="RECQTY"/></td>
	<input type="hidden" id="Re_Qt${cnt+1}"  value="<bean:write name="labelBean" property="RECQTY"/>" >
		
	
	</logic:notEmpty>
	<logic:empty name="labelBean" property="RECQTY">
	 <td align="left" class="tbcellBorder" ><input type="text"  id="Re_Qt${cnt+1}"  onchange="receivValue(this.value,${cnt+1});"style="width: 110px;"  disabled="true"></td>
	 </logic:empty>
	 
	  <logic:notEmpty name="labelBean" property="BTNO">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="BTNO"/>
	<input type="text" maxlength="15" id="Bt_No${cnt+1}" style="width: 110px;" disabled="true" value="<bean:write name="labelBean" property="BTNO"/>" ></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="BTNO">
	 <td align="left" class="tbcellBorder" ><input type="text" maxlength="15" id="Bt_No${cnt+1}"    style="width: 110px;" disabled="true"></td>
	 </logic:empty>
	 
	<logic:notEmpty name="labelBean" property="EXPDT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="EXPDT"/>
	<input type="text" id="ExpDt${cnt+1}" style="width: 110px;" readonly="true" disabled="true" value="<bean:write name="labelBean" property="EXPDT"/>" ></td>
	<input type="hidden" id="ExpDt${cnt+1}" value="<bean:write name="labelBean" property="EXPDT"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="EXPDT">
	 <td align="left" class="tbcellBorder" ><input type="text" id="ExpDt${cnt+1}" style="width: 110px;" disabled="true" ></td>
	 </logic:empty> 
 
 <logic:notEmpty name="labelBean" property="INVNO">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INVNO"/>
	<input type="text" maxlength="30" id="InvNo${cnt+1}" style="width: 110px;" disabled="true" value="<bean:write name="labelBean" property="INVNO"/>" ></td>
	<input type="hidden" id="InvNo${cnt+1}" value="<bean:write name="labelBean" property="INVNO"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="INVNO">
	 <td align="left" class="tbcellBorder" ><input type="text" maxlength="30" id="InvNo${cnt+1}" style="width: 110px;" disabled="true"></td>
	 </logic:empty> 
 
 <logic:notEmpty name="labelBean" property="INVDT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INVDT"/>
	<input type="text" id="InvDt${cnt+1}" style="width: 110px;" disabled="true" value="<bean:write name="labelBean" property="INVDT"/>" ></td>
	<input type="hidden" id="InvDt${cnt+1}" value="<bean:write name="labelBean" property="INVDT"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="INVDT">
	 <td align="left" class="tbcellBorder" ><input type="text" id="InvDt${cnt+1}" style="width: 110px;" disabled="true"></td>
	 </logic:empty> 
 
</tr> 
</logic:iterate>
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="storeList" >
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty>
<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />
<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
	<td  align="center">
		<input class="but" type="button" value="Submit" onclick="javascript:fn_submit();" /></td>	
	 <!-- <td  align="left">
		<input class="but" type="button" value="Print" onclick="javascript:fn_purchaseForm()" /></td>	 -->
 </tr>
</table>
<div id="footer" style="display:none; position: fixed; bottom:0;">

		 <table style="    width: 100%;">
		 <tr>
		 <td style="font: normal; font-size: 10px;"></td>
		 </tr>
		 </table>
	 </div>			
	<div id="printable">
	<div id="headerPrint">
<table style=" font-family:Trebuchet MS;    width: 100%;border-bottom: 3px solid rgba(6, 170, 26, 1);">
<tr>
<td style="width:100%;text-align:center;padding-left: 13%;"><font style="font-size:20px"><b>Reports</b></font>
<br>
<br>
<span style="font-size: 18px;"><b>GOVERNMENT OF TELANGANA</b></span>

</td>
<td id="logo">
<img src="images/TG_logo2.png" alt="..." height="80px" width="120px" style="float:right" >
</td>
</tr>
<tr>
<br>
<c:choose>
 <c:when test="${yearFlag eq 'Y'}">
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>
INDENT REPORT (FROM JAN 2021)
</b></span></td></c:when>
<c:when test="${indentedStock eq 'Y'}">
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>
INDENT REPORT (2019-2020) BASED ON INDENTED STOCK
</b></span></td>
</c:when>
 <c:otherwise>
<td  style="width:100%;text-align:center;padding-left: 13%;"><span style="font-size: 16px;"><b>
INDENT REPORT (2019-2020)
</b></span></td></c:otherwise>
</c:choose>
</tr>
<tr >
<td colspan="2" style="width:100%;text-align:right;">
<span >Report Generated On :<span id="dateToday" ></span></span>
</td>
</tr>

</table>
<br>
</div> 
       
    </div>	
   <logic:iterate name="storeList"  id="labelBean" indexId="cnt">
   <input type="hidden" id="drugID${cnt+1}" value="<bean:write name="labelBean" property="ID"/>" >
<input type="hidden" id="drugTy${cnt+1}" value="<bean:write name="labelBean" property="DRUG_TYPE_ID"/>" >
<input type="hidden" id="mnfId${cnt+1}" value="<bean:write name="labelBean" property="MNFCTR_ID"/>" >
<input type="hidden" id="disId${cnt+1}" value="<bean:write name="labelBean" property="DISTRIBUTOR_ID"/>" >
<input type="hidden" id="price${cnt+1}" value="<bean:write name="labelBean" property="DRUGPRICE"/>" >
<input type="hidden" id="disp${cnt+1}" value="<bean:write name="labelBean" property="DISP_ID"/>" >
<input type="hidden" id="disNm${cnt+1}" value="<bean:write name="labelBean" property="DISTRIBUTOR_NAME"/>" >
<input type="hidden" id="mnfNm${cnt+1}" value="<bean:write name="labelBean" property="MNFCTRNAME"/>" >
   </logic:iterate>  -
</html:form>


</body>
</html>