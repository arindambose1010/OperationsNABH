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
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/Newstyle.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="js/select2.min.js"></script>
<style type="text/css">
.blueFont {
    color: blue !important;
}
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
var date = new Date();
$(document).ready(function() {
	$('#fromDate').datepicker({
		autoclose:true,
		format : 'dd-mm-yyyy',
		todayHighlight:true,
		clearBtn:true,
		startDate:'01/11/2020',
		endDate:new Date(),
 });	

	$('#destDate').datepicker({
		startDate:'01/11/2020',
		endDate:new Date(),
		todayHighlight:true,
		format : 'dd-mm-yyyy',
		autoclose:true,
		clearBtn:true,
	});	
	fn_getIndentNo($('#indent_no').val());
		});	
				
	
	</script>


<script type="text/javascript">
//var jq=jQuery.noConflict();
function getPoNoDetails(arg,poNo)
{
	parent.fn_loadImage();
	var url = 'patientDetailsNew.do';
    var params={
			'actionFlag':'poNoOnclickRpt',
			'poNo':poNo
			};
   $.ajax({
        type: 'POST',
        url: url,
		data: params,
        success: function(data)
        {
        	data=data.replace("*","");
		 	var result=JSON.parse(data);
		 	var content="";
		 	
				//alert(result);
				document.getElementById("tbodyC1").innerHTML="";
				document.getElementById("header").innerHTML=arg.innerHTML;
			
		 	for(var j=0;j<result.length;j++){
					
						content=content+"<tr>";
						content=content+"<td style='text-align:center' data-title='S No'>"+(j+1)+"</td>";
						content=content+"<td class='bothSideBorder2Px blueFont' style='cursor:pointer;text-align:center' data-title='MANUFACTURE PO NUMBER' onclick='getMnfPoNoDetails(this,\""+result[j].LVL+"\")' >"+result[j].LVL+"</td>";
						/* content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INDENT NO'>"+result[j].INDENT_NO+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='ITEM ID'>"+result[j].ITEMID+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DRUG NAME'>"+result[j].DRUGNAME+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='MANUFACTURE NAME'>"+result[j].MNFCTRNAME+"</td>"; */
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DISTRIBUTOR NAME'>"+result[j].DISTRIBUTOR_NAME+"</td>";
						/* content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INDENT COUNT'>"+result[j].INDENT_COUNT+"</td>"; */
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='PO DATE'>"+result[j].PO_DT+"</td>";
						
						
						content=content+"</tr>"; 
					document.getElementById("tbodyC1").innerHTML=content; 
											
				} 
		 	parent.$('html, body').animate({scrollTop: 0}, 500, 'linear');	
		 	$("#phcDataDiv").hide();
		 	$("#phcBackBtn").hide();
		 	$("#phcHeading").hide();
			$("#phcCountDiv").show();
			$('#poNoDtls').modal('show'); 
			parent.fn_removeLoadingImage();
 		 	parent.resizeMiddleFrame();	
			
			
			
		    
			
        }
        
    }); 
}
function getMnfPoNoDetails(arg,mnfPoNo)
{
	parent.fn_loadImage();
	var url = 'patientDetailsNew.do';
    var params={
			'actionFlag':'mnfPoNoOnclick',
			'mnfPoNo':mnfPoNo
			};
   $.ajax({
        type: 'POST',
        url: url,
		data: params,
        success: function(data)
        {
        	data=data.replace("*","");
		 	var result=JSON.parse(data);
		 	var content="";
		 	var content1="";
				//alert(result);
				document.getElementById("tbodyC2").innerHTML="";
				document.getElementById("header2").innerHTML=arg.innerHTML;
		 	for(var j=0;j<result.length;j++){
					
						content=content+"<tr>";
						content=content+"<td style='text-align:center' data-title='S No'>"+(j+1)+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DRUG NAME'>"+result[j].DRUGNAME+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='MANUFACTURE NAME'>"+result[j].MNFCTRNAME+"</td>";
						/* content=content+"<td  class='bothSideBorder2Px' style='cursor:pointer' data-title='MANUFACTURE PO NUMBER'>"+result[j].LVL+"</td>"; */
						/* content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INDENT NO'>"+result[j].INDENT_NO+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='ITEM ID'>"+result[j].ITEMID+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DRUG NAME'>"+result[j].DRUGNAME+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='MANUFACTURE NAME'>"+result[j].MNFCTRNAME+"</td>"; */
						/* content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DISTRIBUTOR NAME'>"+result[j].DISTRIBUTOR_NAME+"</td>"; */
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INDENT COUNT'>"+result[j].INDENT_COUNT+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INDENT ID'>"+result[j].INDENT_ID+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INITIATED BY'>"+result[j].EMPNAME+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='PO DATE'>"+result[j].PO_DT+"</td>";
						content=content+"</tr>"; 
					document.getElementById("tbodyC2").innerHTML=content; 
			
				}
		 	content1=content1+"<tr>";
			content1=content1+"<input class='but' type='button' style='margin-left:1200px;' value='CSV' onclick='javascript:fn_generateCSV2(\""+mnfPoNo+"\");'/>";
			content1=content1+"</tr>";
			document.getElementById("tbodyC3").innerHTML=content1; 
			parent.$('html, body').animate({scrollTop: 0}, 500, 'linear');	
		 	$("#phcDataDiv2").hide();
		 	$("#phcBackBtn2").hide();
		 	$("#phcHeading2").hide();
			$("#phcCountDiv2").show();
			$('#mnfPoNoDtls').modal('show'); 
			parent.fn_removeLoadingImage();
 		 	parent.resizeMiddleFrame();		
        }
        
    }); 
}
function closeModal(arg,frameId)
{
	document.getElementById(frameId).src="";
	$("#"+arg).modal('hide');
}
function generate()
{
	if(document.getElementById('fromDate').value=="" || document.getElementById('fromDate').value==null||document.getElementById('fromDate').value=='-1'){
		alert("Please Select From Date");
		return false;
	}
	if(document.getElementById('destDate').value=="" || document.getElementById('destDate').value==null||document.getElementById('destDate').value=='-1'){
		alert("Please Select To Date");
		return false;
	}
	if(document.getElementById('wellnesscenter').value=="" || document.getElementById('wellnesscenter').value==null||document.getElementById('wellnesscenter').value=='-1'){
		alert("Please Select Wellness Center Name");
		return false;
	}
		fn_loadImage();
		var dispname = document.getElementById("wellnesscenter").value;
		//var indNo = $("#indent_no :selected").text();
		var skillsSelect = document.getElementById("indent_no");
		var indNo = skillsSelect.options[skillsSelect.selectedIndex].text;
		var destDate=document.getElementById('destDate').value;
		var fromDate=document.getElementById('fromDate').value;
		var distributor=document.getElementById('distributor').value;
		document.forms[0].action="patientDetailsNew.do?actionFlag=getPORpt&indNo="+indNo+"&dispname="+dispname+"&destDate="+destDate+"&fromDate="+fromDate+"&distributor="+distributor;
    	document.forms[0].submit();
	
}
function fn_reset()
{
	/* document.getElementById('dispname').value=""; */
	document.getElementById("wellnesscenter").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getPORpt";
	document.forms[0].submit();
	
}
 function fn_resetNew()
{
	/*  document.getElementById('dispname').value=""; */
	 document.getElementById("wellnesscenter").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getPORpt";
	document.forms[0].submit();
	
} 
 function fn_submit()
 {
 	/*  document.getElementById('dispname').value=""; */
 		 var drugList="";
 		   
 		var table = document.getElementById("miscActTbl"); 
 			// var totalRows = document.getElementById("someTableID").rows.length;
 			 
 				 var rows = document.getElementById("miscActTbl").getElementsByTagName("tr").length;
 				 var flag=0;
 				 var formData = new FormData();
				 //formData.append("dispId",$("#dispid :selected").text());
  				 formData.append("dispname",$("#dispname :selected").text());

 				  for(var i=0;i<rows-1;i++)
 					 {
 					 var j=i+1;
 					 var id="chb"+j;
 					
 					 
 					 if(document.getElementById(id).checked==true)
 					 {  var rowId = i+1; 
 					 if(document.getElementById("indNo"+j).value!=null&&document.getElementById("indNo"+j).value!="")
 						 var indNo =document.getElementById("indNo"+j).value;
 					 else
 						var indNo ="NA";
 					 if(document.getElementById("indDt"+j).value!=null&&document.getElementById("indDt"+j).value!="")
 					 	var indDt =document.getElementById("indDt"+j).value;
 					 else
 						var indDt ="NA";
 					 if(document.getElementById("itemId"+j).value!=null&&document.getElementById("itemId"+j).value!="")
 						 var itemId = document.getElementById("itemId"+j).value;
 					 else
 						var itemId = "NA";
 					if(document.getElementById("drugID"+j).value!=null&&document.getElementById("drugID"+j).value!="")
 						var drugID =document.getElementById("drugID"+j).value; 
 					 else 
 						var drugID ="NA";
 					  if(document.getElementById("drugname"+j).value!=null&&document.getElementById("drugname"+j).value!="")
 						 var drugname =document.getElementById("drugname"+j).value; 
 					 else 
 						var drugname ="NA";
 					 if(document.getElementById("drugType"+j).value!=null&&document.getElementById("drugType"+j).value!="")
						 var drugType =document.getElementById("drugType"+j).value; 
					 else
						var drugType ="NA";
 					 if(document.getElementById("mnfctrId"+j).value!=null&&document.getElementById("mnfctrId"+j).value!="")
 					 	var mnfctrId =document.getElementById("mnfctrId"+j).value; 
 					 else 
 						var mnfctrId ="NA";
 					  if(document.getElementById("mnfctName"+j).value!=null&&document.getElementById("mnfctName"+j).value!="")
 					 	var mnfctName =document.getElementById("mnfctName"+j).value; 
 					 else
 						var mnfctName ="NA";
 					 if(document.getElementById("dstrId"+j).value!=null&&document.getElementById("dstrId"+j).value!="")
 					 	var dstrId =document.getElementById("dstrId"+j).value; 
 					 else 
 						var dstrId ="NA";
 					 if(document.getElementById("dstrName"+j).value!=null&&document.getElementById("dstrName"+j).value!="")
 					 	var dstrName =document.getElementById("dstrName"+j).value; 
 					 else 
 						var dstrName ="NA";
 					 if(document.getElementById("indCnt"+j).value!=null&&document.getElementById("indCnt"+j).value!="")
 						var indCnt =document.getElementById("indCnt"+j).value; 
 					 else
 						var indCnt ="NA";
 					var dispid=document.getElementById("dispname"+j).value;
 		 			 
 					 flag+=1;
 						 if(flag>0)
 							 {
 							
 							 drugList=drugList+indNo+"~"+indDt+"~"+itemId+"~"+drugID+"~"+drugname+"~"+drugType+"~"+mnfctrId+"~"+mnfctName+"~"+dstrId+"~"+dstrName+"~"+indCnt+"~"+dispid+"@";
 							
 							}
 						//document.getElementById(id).remove();
 						// document.getElementsByTagName("tr")[j].remove();
 					 }
 				
 					 
 				
 				 } 
 				 formData.append('drugList', drugList); 
 				
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
  					        url: 'patientDetailsNew.do?actionFlag=submitIndented',
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
  										if(result=='FAIL'){ 												
  											alert("Failed");
  											
  										} else
  											{
  											alert("PO Number is Generated successfully.");
  											parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=getPORpt';
  											fn_purchaseForm(pno,wc);
  											}
  										fn_removeLoadingImage();
  										/* parent.parent.resizeSubMiddleFrame(); */
  					 				}
  					        	}						
  							}
  						});
  	 			 }
 			 //jq(':input[type="button"]').prop('disabled', false);
  				//fn_purchaseForm();
 	}
 
/* function validate()
{

	if(document.forms[0].fromDate.value==='' || document.forms[0].fromDate.value===null){
		alert('Please Select From Date');
		focusBox(document.getElementById("fromDate"));
		return false;
	}else if(document.forms[0].destDate.value==='' || document.forms[0].destDate.value===null){
		alert('Please Select To Date');
		focusBox(document.getElementById("destDate"));
		return false;
		}
	return true;
} */
function fn_enable(arg){
	if($('#chb'+arg).is(':checked')){
		$('#sno'+arg).attr("disabled",false);
		
	}
	else{
		$('#sno'+arg).attr("disabled",true);
		
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
function focusBox(arg)
{
  aField = arg; 
  setTimeout("aField.focus()", 0); 
}
function newexportToCsv()
{
	var yearFlag='${yearFlag}';
	var dispname=document.getElementById('dispname').value;
	var drugName=document.getElementById('dispDrugID').value;
	document.forms[0].action = "patientDetailsNew.do?actionFlag=getIndentReportsCsvNew&yearFlag="+yearFlag+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";
	document.forms[0].submit();  
	   
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
 /* function checkAll(inp) {
	  document.querySelectorAll('.tbheader1 border').forEach(el => el.checked = inp.checked);
	} */
 function setCheckAll() {
	  document.querySelector('input.checkAll').checked =
	     document.querySelectorAll('.tbheader1 border').length ==
	     document.querySelectorAll('.tbheader1 border:checked').length;
	}

	 /* document.getElementById('sno').onclick = function() {
		    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
		    for (var checkbox of checkboxes) {
		        checkbox.checked = this.checked;
		    }
		} */

	 function checkAll() 
	 {
	     //alert("Check all the checkboxes..."); 
	     var allRows = document.getElementsByTagName("input");
	     for (var i=0; i < allRows.length; i++) {
	         if (allRows[i].type == 'checkbox') 
	         {
	             allRows[i].checked = true;
	         }
	     }

	 }
function fn_getIndentNo(wcName){
		fn_loadImage();
		//jq('#processImagetable').show();
			var url = "patientDetailsNew.do?";
			var dispId = document.getElementById("wellnesscenter").value;
			var params = {actionFlag:"getIndentNoRpt1",dispId:dispId,wcName:wcName,ajaxCal:'Y'};
			$.post(url,params, function(data){
				try{
					var result = $.parseJSON(data);
					if(result!=null){
						$('#indentNoId').show();
						$('#indent_no option:not(:first)').remove();
						$.each(result, function(data,value){
							var s ='<option value="'+value.VALUE+'">'+value.VALUE+'</option>';
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
			fn_getIndentNo($('#indent_no').val());
});
function fn_purchaseForm(poNo,wc)
{
    //var wc = document.getElementById('wellnesscenter').value;
    //var poNo = document.getElementById('poNo').value;
    document.forms[0].action = "patientDetailsNew.do?actionFlag=purchasePdf&poNo="+poNo+"&wc="+wc;
    document.forms[0].submit();
    /* document.MyForm.actionFlag.value="purchasePdf";
    document.MyForm.rationCard.value='IND-WC - ADILABAD-1190';
    document.MyForm.action="patientDetailsNew.do?";
    document.MyForm.submit(); */
}
function fn_generateCSV()
{
	var wc = document.getElementById("wellnesscenter").value; 
	var indNo=document.getElementById("indent_no").value; 
	var url="./patientDetailsNew.do?actionFlag=poRep&csvFlag=Y&indNo="+indNo+"&dispname="+wc;
	document.forms[0].action=url;
	document.forms[0].submit();

}
function fn_generateCSV1()
{
	var poNo=document.getElementById("poNo").value; 
	var url="./patientDetailsNew.do?actionFlag=dstrRep&csvFlag=Y&poNo="+poNo;
	document.forms[0].action=url;
	document.forms[0].submit();
}
function fn_generateCSV2(mnfPoNo)
{
	//var mnfPoNo=document.getElementById("mnfPoNo").value; 
	var url="./patientDetailsNew.do?actionFlag=drgRep&csvFlag=Y&mnfPoNo="+mnfPoNo;
	document.forms[0].action=url;
	document.forms[0].submit();
}
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
				//var original2 = document.getElementById("footer");
				//var clone2 = original2.cloneNode(true);
				//clone2.style.display="";
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
				/* for(var k=0;k<clone.children[1].children.length;k++)
				{
					var childNodes2=clone.children[1].children[k].children;	
					for(var i=0;i<childNodes2.length;i++)
					{
						childNodes2[i].style.border="1px solid black";
					}
				} */ 
				div.appendChild(clone1);
				div.appendChild(head);
				//div.appendChild(clone2);
			 div.appendChild(clone);
			// var divToPrint=div;
			 var newWin= window.open("");
			 newWin.document.write(div.outerHTML);
			    newWin.document.close(); 
			    newWin.focus();
			    newWin.print();
			    newWin.close();	
			/* $("#printable").html(divToPrint);
				window.document.close(); // necessary for IE >= 10
			    window.focus(); // necessary for IE >= 10*/
				/*window.print();
				window.close(); */
	   	
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
		 <th colspan="8" class="tbheader">PO Report</th>

</tr>
<tr>
		<td  class="labelheading1 tbcellCss" align="left"  ><b>From Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" align="left" ><html:text property="fromDate" styleId="fromDate" /></td>
		<td  class="labelheading1 tbcellCss" align="left" ><b>To Date</b>&nbsp;<span style="color:red">*</span></td>
		<td  class="tbcellBorder" align="left" ><html:text property="destDate" styleId="destDate"  /></td>
	
	
	</tr>
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" style="width:25%;" align="left" ><b>Wellness Center</b>&nbsp;<span style="color:red">*</span></td>
	<td align="left"  class="tbcellBorder">
			<html:select  property="wellnesscenter" name="patientForm" styleId="wellnesscenter"   title="Select Wellness Center" onchange = "fn_getIndentNo(this.value)">
		    <option value="-1">----------Select-------------</option>
			
			 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
			</html:select> 
		</td>
		
	   <td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>Indent NO</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%" id = "indentNoId">

<html:select  property="indent_no" name="patientForm" styleId="indent_no"   title="Select Indent NO">
						 <option value="-1">----------Select-------------</option>
						
						</html:select> 

	</td> 
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>Distributor Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder">
			<html:select  property="distributor" name="patientForm" styleId="distributor"   title="Select Distributor">
		    <option value="-1">----------Select-------------</option>
			
			 <html:options collection="distributorList"  property="ID" labelProperty="VALUE"></html:options>
			</html:select> 
		</td>  
 </tr>
</td>
</tr>

<tr></tr><tr></tr><tr></tr>
	<tr align="center">
	<td colspan="2"  align="right">
		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/></td>
		<td colspan="1"  align="left">
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" /></td>
	   
	</tr>
	<tr></tr><tr></tr><tr></tr>
	
</table>


<logic:present name="patientForm" property="POList">
<bean:size id="size" name="patientForm" property="POList"/>

<logic:greaterThan value="0" name="size"> 

<%-- <div class="row">
<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" style="padding-left: 3%;">
<ul class="pagination">
						Showing Results :${startIndex+1} - ${endResults} of ${totalRecords}
</ul>
</div>


<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" id="pageNoDisplay" >
						<ul class="pagination">
							<li>
								Pages :&nbsp;&nbsp;
							</li>	
						<% int selectedPage=Integer.parseInt(request.getAttribute("pageId").toString());  
								  int totalPages=Integer.parseInt(request.getAttribute("totalPages").toString());
								  int totalRecords=Integer.parseInt(request.getAttribute("totalRecords").toString());
								  int endIndex=Integer.parseInt(request.getAttribute("endIndex").toString());
								  
								  int a=0,minVal=0,maxVal=0;
								  a=selectedPage/10;
								  minVal=a*10;
								  if(selectedPage%10==0)
									  minVal=minVal-10;
								  maxVal=minVal+10;
								  if(maxVal>=totalPages)
									  maxVal=totalPages; 
								  minVal=minVal+1;
								  if(minVal > 10)
								  	{
									  %>
										<li><a href="#"><span class="glyphicon glyphicon-backward" 
											onclick="javascript:viewPreviousPages(<%=minVal%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer; top: 0px;"></span></a></li>
									  <%  
								  	}
								  for(int i=minVal;i<=maxVal ;i++)
								  	{
									  	if(i==selectedPage)
										  	{
												%>
												<li class="active"><span><%=i%></span></li> 
												<%
											}
									  	else
											{
												%>
												<li><a href="javascript:fn_pagination(<%=i%>,<%=endIndex%>)"><%=i%></a></li>
												<%
											}
									  		
								  	}
								  if(maxVal<totalPages)
								  	{
									  	%>
										<li><span class="glyphicon glyphicon-forward" onclick="javascript:viewNextPages(<%=maxVal+1%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)" style="cursor: pointer; top: 0px;"></span></li>
										<%
								  	}
							   %>
						</ul>
</div>

<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group" style="padding-left: 8%;">		
								<ul class="pagination">
									<li class="lispacing">Show results in sets of </li><c:if test="${endIndex ne 10}"><li><a href="javascript:fn_pagination(1,10)">10</a></li></c:if>
										<c:if test="${endIndex eq 10}"><li class="active"><span>10</span></li></c:if>	
										<c:if test="${endIndex ne 20}"><li><a href="javascript:fn_pagination(1,20)">20</a></li></c:if>
										<c:if test="${endIndex eq 20}"><li class="active"><span>20</span></li></c:if>
										<c:if test="${endIndex ne 50}"><li><a href="javascript:fn_pagination(1,50)">50</a></li></c:if>
										<c:if test="${endIndex eq 50}"><li class="active"><span>50</span></li></c:if>
										<c:if test="${endIndex ne 100}"><li><a href="javascript:fn_pagination(1,100)">100</a></li></c:if>
										<c:if test="${endIndex eq 100}"><li class="active"><span>100</span></li></c:if>
										<c:if test="${endIndex ne 1000}"><li><a href="javascript:fn_pagination(1,1000)">1000</a></li></c:if>
										<c:if test="${endIndex eq 1000}"><li class="active"><span>1000</span></li></c:if>
								</ul> 
</div>     
       
</div> --%>

<%-- <p><b>Generated On Date:</b>${updDate} </p>
<br>
<span><b>Download as: &nbsp;&nbsp;</b></span>
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv()"/> --%>
<div class="row" align="center" style="margin-left: 1195px;">

		<input class="but" type="button"  value="CSV" onclick="javascript:fn_generateCSV();"/>
		
		
	
</div>	
<!-- <a href="javascript:fn_print('miscActTbl','','Indent Report');" title="Print"><span style="padding:2%" class="glyphicon glyphicon-print"></span></a>  		    
 -->
<table id="miscActTbl" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1 border" ><!-- <input type="checkbox" id="select_all"/> --><!-- <input class="checkAll" type="checkbox" onclick="checkAll(this)"> -->INDENT NO</th>
<th class="tbheader1 border" >INDENT DATE</th>
<th class="tbheader1 border" >ITEM ID</th>
<th class="tbheader1 border" >DRUGS NAME</th>
	<th class="tbheader1 border" >DRUG TYPE</th>
	<th class="tbheader1 border" >DISTRIBUTOR NAME</th>
	<th class="tbheader1 border" >MANUFACTURER NAME</th>
	<th class="tbheader1 border" >INDENTED QTY</th>
	<th class="tbheader1 border" >PO NO</th>
	<th class="tbheader1 border" >PO DATE</th>
	<th class="tbheader1 border" >WELLNESS CENTER</th>

</tr>

<logic:iterate name="patientForm" property="POList" id="labelBean" indexId="cnt">
<tr>
<logic:notEmpty name="labelBean" property="INDENT_NO">
<td align="left" class="tbcellBorder" ><%-- <input class="tbcellBorder" style="display:" type="checkbox" name="chb" id="chb${cnt+1}" > --%><bean:write name="labelBean" property="INDENT_NO"/> </td>
<input type="hidden" id="indNo${cnt+1}" name="indNo${cnt+1}" value="<bean:write name="labelBean" property="INDENT_NO"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="INDENT_NO">
 <td align="left" class="tbcellBorder" >NA</td>
 </logic:empty>
 
<logic:notEmpty name="labelBean" property="INDENT_DT">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INDENT_DT"/></td>
<input type="hidden" id="indDt${cnt+1}" value="<bean:write name="labelBean" property="INDENT_DT"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="INDENT_DT">
 <td align="left" class="tbcellBorder" >NA</td>
 </logic:empty>

<logic:notEmpty name="labelBean" property="ITEMID">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ITEMID"/></td>
<input type="hidden" id="itemId${cnt+1}" value="<bean:write name="labelBean" property="ITEMID"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="ITEMID">
 <td align="left" class="tbcellBorder" >NA</td>
 </logic:empty> 

 

<logic:notEmpty name="labelBean" property="DRUGNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGNAME"/></td>
<input type="hidden" id="drugname${cnt+1}"  value="<bean:write name="labelBean" property="DRUGNAME"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGNAME">
 <td align="left" class="tbcellBorder" >NA</td>
 </logic:empty> 
 
<logic:notEmpty name="labelBean" property="DRUGTYPE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUGTYPE"/></td>
<input type="hidden" id="drugType${cnt+1}"  value="<bean:write name="labelBean" property="DRUGTYPE"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUGTYPE">
 <td align="left" class="tbcellBorder" >NA</td>
 </logic:empty> 
 

	 <logic:notEmpty name="labelBean" property="DISTRIBUTOR_NAME">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISTRIBUTOR_NAME"/></td>
	<input type="hidden" id="dstrName${cnt+1}"  value="<bean:write name="labelBean" property="DISTRIBUTOR_NAME"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="DISTRIBUTOR_NAME">
	 <td align="left" class="tbcellBorder" >NA</td>
	 </logic:empty> 
	 
	 <logic:notEmpty name="labelBean" property="MNFCTRNAME">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="MNFCTRNAME"/></td>
	<input type="hidden" id="mnfctName${cnt+1}"  value="<bean:write name="labelBean" property="MNFCTRNAME"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="MNFCTRNAME">
	 <td align="left" class="tbcellBorder" >NA</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="INDENT_COUNT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="INDENT_COUNT"/></td>
	<input type="hidden" id="indCnt${cnt+1}" value="<bean:write name="labelBean" property="INDENT_COUNT"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="INDENT_COUNT">
	 <td align="left" class="tbcellBorder" >NA</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="PO_NO">
	<td align="left" class="bothSideBorder2Px blueFont tbcellBorder" onclick="getPoNoDetails(this,'<bean:write  name="labelBean" property="PO_NO"/>');"><bean:write  name="labelBean" property="PO_NO"/></td>
	<input type="hidden" id="poNo${cnt+1}"  name="poNo${cnt+1}" value="<bean:write name="labelBean" property="PO_NO"/>" >
		<input type="hidden" id = "poNo" value="<bean:write name="labelBean" property="PO_NO"/>" >
	
	</logic:notEmpty>
	<logic:empty name="labelBean" property="PO_NO">
	 <td align="left" class="tbcellBorder" >NA</td>
	 </logic:empty>
	 
	  <logic:notEmpty name="labelBean" property="PO_DT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PO_DT"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="PO_DT">
	 <td align="left" class="tbcellBorder" >NA</td>
	 </logic:empty>
	 
<logic:notEmpty name="labelBean" property="DISPNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISPNAME"/></td>
<input type="hidden" id="dispname${cnt+1}" value="<bean:write name="labelBean" property="DISPNAME"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="DISPNAME">
 <td align="left" class="tbcellBorder" >NA</td>
 </logic:empty> 
 
</tr>
</logic:iterate>
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="patientForm" property="POList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty>
<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />
<div class="modal fade" id="poNoDtls" role="dialog" >
    <div class="modal-dialog" style="width:100%">
    
      <!-- Modal content-->
      <div class="modal-content " >
     	<div style="text-align:center;color:#fdfdfd; font-size: 14px;">
          
          
          <button type="button" class="close" style="opacity: 1;color: #01964d;" onclick="closeModal('poNoDtls','phcDataFrameDiv')">×</button> 
          <h3 style="text-align:center;color:rgb(1, 140, 128) !important; font-size: 16px;"><b>Distributor Wise Report for PO NO: </b><div id="header" style="display: contents; color: red;"></div></h3>
          <input class="but" type="button" style="margin-left:1200px;" value="CSV" onclick="javascript:fn_generateCSV1();"/>
        </div>

        <div class="modal-body">
        <div>
		<div id="phcCountDiv">

		<section id="no-more-tables">
          <table id="teamDtlsTbl" class="table fontclass table-bordered" >
					 <thead id="thead_dashboard">
				<tr>
							<th class="tableHeader"  style="font-size: 14px !important;text-align:center"><b>S.No</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>MANUFACTURE PO NUMBER</b></th>
							<!-- <th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;"><b>INDENT NO</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;"><b>ITEM ID</b></th>
							<th class="tableHeader "  style="font-size: 14px !important;"><b>DRUG NAME</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;"><b>MANUFACTURE NAME</b></th> -->
							<th class="tableHeader" style="font-size: 14px !important;text-align:center"><b>DISTRIBUTOR NAME</b></th>
							<!-- <th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;"><b>INDENT COUNT</b></th> -->
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>PO DATE</b></th>
						</tr>
					</thead>
					<tbody id="tbodyC1">
	           </tbody>	
					
					</table>
					
					</section>
					</div>
         <div id="phcDataDiv" style="display:none;">
			<iframe id="phcDataFrameDiv" name="phcDataFrameDiv" frameborder="0" width="100%" height="450px"></iframe>
  		 </div> 
					</div>
        </div>
        
      </div>
      
    </div>
  </div>
  <div class="modal fade" id="mnfPoNoDtls" role="dialog" >
    <div class="modal-dialog" style="width:100%">
    
      <!-- Modal content-->
      <div class="modal-content " >
     	<div style="text-align:center;color:#fdfdfd; font-size: 14px;">
          
          
          <button type="button" class="close" style="opacity: 1;color: #01964d;" onclick="closeModal('mnfPoNoDtls','phcDataFrameDiv2')">×</button> 
          <h3 style="text-align:center;color:rgb(1, 140, 128) !important; font-size: 16px;"><b>Drug Wise Report for Manufacture PO NO: </b><div id="header2" style="display: contents; color: red;"></div></h3>
<!--           <input class="but" type="button" style="margin-left:1200px;" value="CSV" onclick="javascript:fn_generateCSV2();"/>
 -->        </div>

        <div class="modal-body">
        <div>
		<div id="phcCountDiv2">

		<section id="no-more-tables">
          <table id="teamDtlsTbl" class="table fontclass table-bordered" >
					 <thead id="thead_dashboard">
				<tr>
							<th class="tableHeader"  style="font-size: 14px !important;text-align:center"><b>S.No</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>DRUG NAME</b></th>
							<!-- <th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;"><b>INDENT NO</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;"><b>ITEM ID</b></th>
							<th class="tableHeader "  style="font-size: 14px !important;"><b>DRUG NAME</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;"><b>MANUFACTURE NAME</b></th> -->
							<th class="tableHeader" style="font-size: 14px !important;text-align:center"><b>MANUFACTURE NAME</b></th>
							<!-- <th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;"><b>INDENT COUNT</b></th> -->
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>INDENT COUNT</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>INDENT ID</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>INITIATED BY</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>PO DATE</b></th>
						</tr>
					</thead>
					<tbody id="tbodyC2">
	           </tbody>	
					<div id="tbodyC3">
	           </div>	
					</table>
					
					</section>
					</div>
         <div id="phcDataDiv2" style="display:none;">
			<iframe id="phcDataFrameDiv2" name="phcDataFrameDiv2" frameborder="0" width="100%" height="450px"></iframe>
  		 </div> 
					</div>
        </div>
        
      </div>
      
    </div>
  </div>
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
<img src="images/tgLogo.png" alt="..." height="80px" width="120px" style="float:right" >
</td>
</tr>
<%-- <tr>
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
</tr> --%>

</table>
<br>
</div> 
       
    </div>	
    <logic:iterate name="patientForm" property="POList" id="labelBean" indexId="cnt">
   <input type="hidden" id="drugID${cnt+1}" value="<bean:write name="labelBean" property="DRUGID"/>" >
   <input type="hidden" id="mnfctrId${cnt+1}" value="<bean:write name="labelBean" property="MNFCTRID"/>" >
   <input type="hidden" id="dstrId${cnt+1}" value="<bean:write name="labelBean" property="DISTRIBUTOR_ID"/>" >
   </logic:iterate>
</html:form>


</body>
</html>