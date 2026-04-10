<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
import=" java.text.SimpleDateFormat, 
				  java.text.DateFormat, java.io.File, java.io.FileOutputStream, com.ahct.common.vo.LabelBean, 
				  java.util.StringTokenizer,java.util.*"
	isErrorPage="false"
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
<!-- Bootstrap -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/Newstyle.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">

<!-- Select2 -->
<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>

<!-- DataTables CSS -->
<link href="css/static.jquery.dataTables.css" rel="stylesheet">

<!-- DataTables JS (core) -->
<script src="js/jquery-dataTables.min.js"></script>

<!-- Buttons extension (must load AFTER DataTables) -->
<link rel="stylesheet" href="css/buttons.dataTables.min.css">
<script src="js/dataTables-buttons.min.js"></script>

<!-- JSZip (MANDATORY for Excel) -->
<script src="js/jszip.min.js"></script>

<!-- pdfmake (MANDATORY for PDF) -->
<script src="js/pdfmake.min.js"></script>
<script src="js/vfs_fonts.js"></script>

<!-- HTML5 export buttons -->
<script src="js/buttons-html5.min.js"></script>
<script src="js/buttons.print.min.js"></script>

<!-- Other scripts -->
<script src="js/jquery.msgBox.js"></script>
<style type="text/css">
.small-excel-button {
    padding: 5px 13px !important;
    font-weight: bold !important;
    margin-top: -27px !important;
    margin-left: 87.5%;
}
.small-pdf-button{
    padding: 5px 13px !important;
    font-weight: bold !important;
    margin-top: -27px !important;
}
.blueFont {
    color: blue !important;
}

.exportVal {
    display: none;
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
	.table>tbody>tr>th, 
	.table>tfoot>tr>th, 
	.table>thead>tr>td, 
	.table>tbody>tr>td, 
	.table>tfoot>tr>td {
    padding: 8px;
    line-height: 1.42857143;
    vertical-align: top;
    border-top: 1px solid #ddd;
}
      
    }
</style>
<%List<LabelBean> indentList=new ArrayList<LabelBean>(); 
indentList=(List<LabelBean>)request.getAttribute("indentList");

%>
<script type="text/javascript">
var $ = jQuery.noConflict();
//Tejasri -9073- Added this to download excel and pdf
$(document).ready(function() {
	$('#miscActTbl').DataTable({
	    dom: 'lBfrtip',
	    buttons: [
	        {
	            extend: 'excelHtml5',
	            text: 'Excel',
	            title: 'Stock Indent Approval Report',
	            className: 'small-excel-button',
	            exportOptions: { columns: ':visible' }
	        },
	        {
	            extend: 'pdfHtml5',
	            text: 'PDF',
	            title: 'Indent_Report',
	            filename: 'Stock Indent Approval Report',
	            className: 'small-pdf-button',
	            orientation: 'landscape',
	            pageSize: 'A3',
	            download: 'download',   
	            exportOptions: {
	                columns: ':visible',
	                format: {
	                    body: function (data, row, column, node) {
	                        var span = $(node).find('span.exportVal').text().trim();
	                        return span || $(node).text().trim();
	                    }
	                }
	            },
	            customize: function (doc) {
	                doc.pageMargins = [20, 20, 20, 20];
	                doc.content[0].alignment = 'center';
	            }
	        }
	    ],
	    paging: false,
	    searching: false,
	    info: false,
	    ordering: false
	});
	
});
function generate()
{
	if(document.getElementById('dispname').value=="" || document.getElementById('dispname').value==null||document.getElementById('dispname').value=='-1'){
		alert("Please Select Wellness Center Name");
		return false;
	}
	
		fn_loadImage();
		var yearFlag='${yearFlag}';
		document.forms[0].action="patientDetailsNew.do?actionFlag=getstkIndentedPo&yearFlag="+yearFlag+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";
    	document.forms[0].submit();
	
}
function indentValue(Indplaced,cnt)
{
	var Indplaced1=parseFloat(Indplaced);
	if(Indplaced =='' || Indplaced1 <=0){
		alert("Selected quantity should be minimum 1.");
		document.getElementById("Indprice"+cnt).value=0;
		document.getElementById("indCnt"+cnt).focus();
		return false;
	}
	var priceId = "price"+cnt;
	var IndpriceId = "Indprice"+cnt;
	var price=document.getElementById(priceId).value;
	var price1=parseFloat(price);
	var Indvalue=Indplaced1*price1;
	document.getElementById(IndpriceId).value=Indvalue;
	remarks(cnt);
	
}
function fn_reset()
{
	/* document.getElementById('dispname').value=""; */
	document.getElementById("dispDrugID").value=""; 
	document.getElementById("drugTypeID").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getstkIndentedPo";
	document.forms[0].submit();
	
}
function fn_back()
{
	fn_loadImage();
	document.forms[0].action="patientDetailsNew.do?actionFlag=getstkIndentedPoPage1";
	document.forms[0].submit();
	
}
 function fn_resetNew()
{
	/*  document.getElementById('dispname').value=""; */
	 document.getElementById("dispDrugID").value=""; 
	 document.getElementById("drugTypeID").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=getstkIndentedPo";
	document.forms[0].submit();
	
} 
 function fn_submit()
 {
 	/*  document.getElementById('dispname').value=""; */
 		 var drugList="";
 		   var lowStockFlag = '${lowStockFlag}';//Chandana - 8809 - Added for lowstock flag
 		var table = document.getElementById("miscActTbl"); 
 			// var totalRows = document.getElementById("someTableID").rows.length;
 			 
 				 var rows = document.getElementById("miscActTbl").getElementsByTagName("tr").length;
 				 var flag=0;
 				 var formData = new FormData();
 				 if(lowStockFlag == 'Y')//Chandana - 8809 - Added the condition for sending the dispid to backend
 					formData.append("dispId","${dispid}");
 				 else{
	 				 var dispId=document.getElementById("dispId").value;
					 formData.append("dispId",dispId);
 				 }
				var dispname1=document.getElementById("dispname").value;
				var dispname="WC - "+dispname1;
				var drugCode ;
				var indCnt;
				var regAlphaNum=/^[A-Za-z ]*$/;
  				 formData.append("dispname",dispname);
 				  for(var i=0;i<rows-1;i++)
 					 {
 					 var j=i+1;
 					 var id="chb"+j;
 					
 					var remarks1 =document.getElementById("remarks"+j).value;
 					
 					 if(document.getElementById(id).checked==true)
 					 {  
 						 var rowId = i+1; 
 						/* if(remarks1!=""&&remarks1!=null&&remarks1.trim().search(regAlphaNum)==-1)
						{
						alert("Remarks should be letters only");
						document.getElementById("remarks"+j).value="";
						document.getElementById("remarks"+j).focus();
						return false;
						}
 						if(remarks1!=""&&remarks1!=null&&remarks1.length<=5){
 							alert("Remarks must be greater than 5 letters of row no:"+j);
 							document.getElementById("remarks"+j).focus();
		 					 return false;
 						} */
 					 if(document.getElementById("drugCode"+j).value!=null&&document.getElementById("drugCode"+j).value!="")
 						 drugCode =document.getElementById("drugCode"+j).value;
 					 else
 						drugCode ="NA";
 					 if(document.getElementById("drugname"+j).value!=null&&document.getElementById("drugname"+j).value!="")
 					 	var drugName =document.getElementById("drugname"+j).value;
 					 else
 						var drugName ="NA";
 					 if(document.getElementById("idealStk"+j).value!=null&&document.getElementById("idealStk"+j).value!="")
 						 var idealStk = document.getElementById("idealStk"+j).value;
 					 else
 						var idealStk = "NA";
 					 if(document.getElementById("avl"+j).value!=null&&document.getElementById("avl"+j).value!="")
 						 var avlCnt =document.getElementById("avl"+j).value; 
 					 else
 						var avlCnt ="NA";
 					 if(document.getElementById("indnotRec"+j).value!=null&&document.getElementById("indnotRec"+j).value!="")
 					 	var indnotRec =document.getElementById("indnotRec"+j).value; 
 					 else
 						var indnotRec ="NA";
 					 if(document.getElementById("indCnt"+j).value!=null&&document.getElementById("indCnt"+j).value!="")
 					 	indCnt =document.getElementById("indCnt"+j).value; 
 					 else
 						indCnt ="NA";
 					 if(document.getElementById("price"+j).value!=null&&document.getElementById("price"+j).value!="")
 						var price =document.getElementById("price"+j).value; 
 					 else
 						var price ="NA";
 					 if(document.getElementById("Indprice"+j).value!=null&&document.getElementById("Indprice"+j).value!="")
 						var Indprice =document.getElementById("Indprice"+j).value; 
 					 else
 						var Indprice ="NA";
 					 if(indCnt==0 || indCnt=='NA' ){
 						alert("Please enter Indent to be placed greater than 0 in row no:"+j);
 						document.getElementById("indCnt"+j).focus();
	 					 return false;
 					 }
 					 else if(indCnt != document.getElementById("actualIndCnt"+j).value){
	 					if(remarks1==null||remarks1==""){
			 					 alert("Please enter Remarks of row no:"+j);
			 					document.getElementById("remarks"+j).focus();
			 					 return false;
			 					remarks1="NA";
			 					 } 					 	
	 					 else{
	 						document.getElementById("remarks"+j).focus();
	 					 }	 
 					 }
 					 if(document.getElementById("indent"+j).value!=null&&document.getElementById("indent"+j).value!="")
 					 	var indent =document.getElementById("indent"+j).value; 
 					 else
 						var indent ="NA";
 					 if(document.getElementById("itemId"+j).value!=null&&document.getElementById("itemId"+j).value!="")
  					 	var itemId =document.getElementById("itemId"+j).value; 
  					 else
  						var itemId ="NA";
  						 if(document.getElementById("drugTyp"+j).value!=null&&document.getElementById("drugTyp"+j).value!="")
  	  					 	var drugTyp =document.getElementById("drugTyp"+j).value; 
  	  					 else
  	  						var drugTyp ="NA";
  						
  						
 					 flag+=1;
 						 if(flag>0)
 							 {
 							
 							 drugList=drugList+drugCode+"~"+drugName+"~"+indCnt+"~"+idealStk+"~"+avlCnt+"~"+indnotRec+"~"+price+"~"+Indprice+"~"+remarks1+"~"+indent+"~"+itemId+"~"+drugTyp+"@";
 							
 							}
 						//document.getElementById(id).remove();
 						// document.getElementsByTagName("tr")[j].remove();
 					 }
 				
 					 
 				
 				 } 
 				 var remarks2 = document.getElementById("remarks").value;
 				if(remarks2==null||remarks2==""){
					 alert("Please enter Initiation Remarks");
					document.getElementById("remarks").focus();
					 return false;
 				} else if(remarks2!=""&&remarks2!=null&&remarks2.trim().search(regAlphaNum)==-1){
					alert("Initiation Remarks should be letters only");
					document.getElementById("remarks").value="";
					document.getElementById("remarks").focus();
					return false;
				}
 				else if(remarks2!=""&&remarks2!=null&&remarks2.length<=5){
						alert("Initiation Remarks must be greater than 5 letters of row no:"+j);
						document.getElementById("remarks").focus();
	 					 return false;
				}
 				 formData.append('remarks', remarks2); 
 				formData.append('drugList', drugList);
  				 if(flag===0)
  				 {
  				 alert("Please select altleast one Check box to Submit");
				 return false;
  				 }
  				//var id=;
  			    //var dispId = document.getElementById("dispId").value;
  		
  				if(confirm("Do you want to Submit?"))
  	 			 {
  	 			     $(':input[type="button"]').prop('disabled', true);
  	 			     fn_loadImage();
  	 			  var flag = "${criticalFalg}";//Chandana - 9008 - Added this to get the flag to differentiate the total, critical or non critical
  	 			 /*  var url="./patientDetailsNew.do?actionFlag=submitLowStockList&drugList="+drugList;
  	 				document.forms[0].action=url;
  	 				document.forms[0].method="post";
  	 				document.forms[0].submit(); */
  	 				var url = "";//Chandana - 8809 - Added this var for url
  	 			  if(lowStockFlag == 'Y')//Chandana - 8809 - Added if conditions for calling the backend based on the lowstock flag
				        url = "patientDetailsNew.do?actionFlag=submitLowStockList";
				  else
					  url = "patientDetailsNew.do?actionFlag=submitLowStockListAdmin";
  	 			    $.ajax({
  	 			    		url: url,
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
  											if(lowStockFlag == 'Y')//Chandana - 8809 - Added if condition for redirecting to the jsp's based on the condition
  												parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=viewlowStockReport&indentedStock=Y&lowStock=Y';
  											else
  												parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=getstkIndentedPo&indentedStock=Y&lowStock=Y&dispId='+dispId+'&flag='+flag;
  										} else
  											{
	  											if(lowStockFlag == 'Y'){//Chandana - 8809 - Added if condition for redirecting to the jsp's based on the condition
	  												alert("Items submitted for indenting. Awaiting for approval at Admin level.");
	  												parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=viewlowStockReport&indentedStock=Y&lowStock=Y';
	  											}else{
		  											alert("Items submitted for approval. Awaiting for EO approval.");
		  											parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=getstkIndentedPo&indentedStock=Y&lowStock=Y&dispId='+dispId+'&flag='+flag;
	  											}
  											}
  										//fn_removeLoadingImage();
  										/* parent.parent.resizeSubMiddleFrame(); */
  					 				}
  					        	}						
  							}
  						});
  	 			 }
 			 //jq(':input[type="button"]').prop('disabled', false);
 		
 	}
 

function fn_enable(arg){

	if($('#chb'+arg).is(':checked')){
		if ($('input[type=checkbox]:checked').length > 70) {
			 $(this).prop('checked', false);
	        alert("allowed only 70");
	    
		}
		else{
			$('#sno'+arg).attr("disabled",false);
		}
	}
	else{
		$('#sno'+arg).attr("disabled",true);
		
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
function fn_purchaseForm()
{
    //var yearFlag='${yearFlag}
    var rationCard='IND-WC - ADILABAD-1190';
    //var drugName=document.getElementById('dispDrugID').value;
    document.forms[0].action = "patientDetailsNew.do?actionFlag=purchasePdf&rationCard="+rationCard;
    document.forms[0].submit();
    /* document.MyForm.actionFlag.value="purchasePdf";
    document.MyForm.rationCard.value='IND-WC - ADILABAD-1190';
    document.MyForm.action="patientDetailsNew.do?";
    document.MyForm.submit(); */
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
function getAvlDetails(arg,dispId,drugCode)
{
	parent.fn_loadImage();
	var url = 'patientDetailsNew.do';
    var params={
			'actionFlag':'avlOnclick',
			'drugCode':drugCode,
			'dispId':dispId
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
						content=content+"<td  style='text-align:center' data-title='DRUGNAME'>"+result[j].DRUGNAME+"</td>";
						/* content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='INDENT NO'>"+result[j].INDENT_NO+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='ITEM ID'>"+result[j].ITEMID+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DRUG NAME'>"+result[j].DRUGNAME+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='MANUFACTURE NAME'>"+result[j].MNFCTRNAME+"</td>"; */
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='BATCH NO'>"+result[j].BATCHNO+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='AVAILABLE QUANTITY'>"+result[j].QTY+"</td>"; 
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='EXPIRYDATE'>"+result[j].EXPIRYDATE+"</td>";
						
						
						content=content+"</tr>"; 
					document.getElementById("tbodyC1").innerHTML=content; 
											
				} 
		 	parent.$('html, body').animate({scrollTop: 0}, 500, 'linear');	
		 	$("#phcDataDiv").hide();
		 	$("#phcBackBtn").hide();
		 	//$("#phcHeading").hide();
			$("#phcCountDiv").show();
			$('#avlDtls').modal('show'); 
			parent.fn_removeLoadingImage();
			
			
			
		    
			
        }
        
    }); 
}


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
 function checkInput(ele){
		var val = ele.value;
		 if(val.search(/[^0-9]/) > -1){
			ele.value = val.replace(/[^0-9]+/, '');
		}
		if(ele.value.charAt(0) == 0 && ele.value.charAt(1)){
		ele.value = ele.value.replace(/^00+/, '0');
		}

	}
	function checkAlphaNumeric(ele){
		var val = ele.value;
		
		if(val.search(/&/) > -1){
			ele.value = val.replace(/&/g, '');
		}
		

	}
	function closeModal(arg,frameId)
	{
		document.getElementById(frameId).src="";
		$("#"+arg).modal('hide');
	}
	function remarks(cnt){
		document.getElementById("remarks"+cnt).value="";
		if(document.getElementById("remarks"+cnt).value==""||document.getElementById("remarks"+cnt).value==null){
			//alert("Please Enter Remarks");
			document.getElementById("remarks"+cnt).focus();
			return false;
		}
	}
 
</script>
</head>
<body>
<html:form action="/patientDetailsNew.do">
<div id="processImagetable" style="top:15%;left:45%;display:none;position:fixed;z-index:60;height:100%">
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
	<tr><!-- Chandana - 8809 - Added when condition -->
	<c:choose>
	   <c:when test="${lowStockFlag eq 'Y' }">
	   	<c:set var="dispName" value="${dispname}" />
	   		<th colspan="8" class="tbheader">Based on Low Stock (Indent Report) - <%= request.getAttribute("dispname") %> </th>
		</c:when>
		<c:when test="${indentedStock eq 'Y' && lowStock eq 'Y'}">
			<th colspan="8" class="tbheader">Stock Indent Approval </th>
		</c:when>
		
		 <c:otherwise>
		 <th colspan="8" class="tbheader">Indent Report</th>
		</c:otherwise>
	</c:choose>	
	</tr>
	<%-- <tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Wellness Center Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						 <option value="-1">----------Select-------------</option>
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td>
		<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Type</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="drugTypeID" styleId="drugTypeID" style="width:12em;" title="Select Drug Type"  onchange="fn_getDrugList(this.value)"  >
		<html:option value="-1">----------Select-------------</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/> 
</html:select>
</td>

<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:12em;" title="Select Drug Name"    >
		<html:option value="-1">----------Select-------------</html:option>
		<logic:notEmpty name="dispDrugsList">
					<html:options property="ID" collection="dispDrugsList" labelProperty="VALUE"/>
					</logic:notEmpty>
</html:select>
 
</td>
</tr> --%>

</table>
<!-- <div class="row" align="center">

		<input class="but" type="button"  value="Generate" onclick="javascript:generate()"/>
		
		<input class="but" type="button" value="Reset" onclick="javascript:fn_reset()" />
		<td colspan="2"  align="center">
		<input class="but" type="button" value="Print" onclick="javascript:fn_purchaseForm()" /></td>	
	   
	
</div>	 -->

<logic:present name="patientForm" property="indentList">
<bean:size id="size" name="patientForm" property="indentList"/>

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
<%-- 
<div  style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<p><b>Generated On Date:</b>${updDate} </p>
<br>
<!-- <span><b>Download as: &nbsp;&nbsp;</b></span> -->
<!-- <img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv()"/> -->
<!-- <a href="javascript:fn_print('miscActTbl','','Indent Report');" title="Print"><span style="padding:2%" class="glyphicon glyphicon-print"></span></a>   -->		    
</div> --%>
<c:if test="${lowStockFlag ne 'Y' }"><!-- Chandana - 8809 - Added this condition for not shwoing the div for incharge -->
<div class="row">
					<div align="left" style="margin-left:35px">
						<label> <span style="width: 17px;background-color: #B8E6E6;border: 1px solid black;height: 17px;">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;Indent Raised by Incharge&nbsp;</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<!-- <label> <span style="width: 17px;background-color: #B8E6E6;border: 1px solid black;height: 17px;">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;Indent Raised by WC&nbsp;</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
						<label> <span style="width: 17px;background-color: #FFEBCC;border: 1px solid black;height: 17px;">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;Indent to be Raised by Admin&nbsp;</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label> <span style="width: 17px;background-color: #f8b0b0;border: 1px solid black;height: 17px;">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;Indent rejected by EO&nbsp;</label>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<label> <span style="width: 17px;background-color: red;border: 1px solid black;height: 17px;">&nbsp;&nbsp;&nbsp;&nbsp;</span>&nbsp;&nbsp;Critical Drugs&nbsp;</label>
						
					</div>
				</div>
</c:if>
<table class="table" id="miscActTbl" style="text-align:center;margin:0 auto;width: 90%;font-size:xx-small; margin-top: 1%;">
<thead>
<tr>
<c:if test="${total ne 'Y'}">
<th class="tbheader1 border" id = "sno">S.NO<input type="checkbox" id="select_all"/><!-- <input class="checkAll" type="checkbox" onclick="checkAll(this)"> --></th>
</c:if>
<c:if test="${total eq 'Y'}">
<th class="tbheader1 border" id = "sno">S.NO</th><!-- <input class="checkAll" type="checkbox" onclick="checkAll(this)"> --></th>
</c:if>
<!-- <th class="tbheader1 border" >INDENT NO</th> -->
<th class="tbheader1 border" >ITEM ID</th>
<th class="tbheader1 border" >WELLNESS CENTER</th>
<th class="tbheader1 border" >DRUG TYPE</th>
<th class="tbheader1 border" >DRUG NAME</th>
<c:choose>
<c:when test="${indentedStock eq 'Y'}">
	<th class="tbheader1 border" >IDEAL STOCK TO BE KEPT</th>
	<th class="tbheader1 border" >SUM OF LAST 6 MONTHS CONSUMPTION</th>	
	<th class="tbheader1 border" >STOCK AVAILABLE</th>
	<th class="tbheader1 border" >INDENTED BUT NOT RECEIVED</th>
	<th class="tbheader1 border" >NOTIONAL STOCK AVAILABLE</th>
	<th class="tbheader1 border" >NOTIONAL DEFICIT VIS A VIS IDEAL STOCK</th>
	<th class="tbheader1 border" >FURTHER INDENT TO BE PLACED</th>
	<th class="tbheader1 border" >RATE CONTRACT PRICE</th>
	<th class="tbheader1 border" >INDENT VALUE</th>
</c:when>
<c:otherwise>
<th class="tbheader1 border" >AVERAGE MONTHLY CONSUMPTION</th>
<th class="tbheader1 border" >REQUIRED STOCK FOR NEXT 6 MONTHS</th>
<th class="tbheader1 border" >AVAILABLE STOCK</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED</th>
<th class="tbheader1 border" >RATE CONTRACT PRICE</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED VALUE</th>
</c:otherwise>
</c:choose>
<th class="tbheader1 border" >RATE CONTRACT YEAR</th>
<th class="tbheader1 border" >REMARKS</th>
</tr>
</thead>
<tbody>
<logic:iterate name="patientForm" property="indentList" id="labelBean" indexId="cnt">

<%

if(indentList.get(cnt).getINDENT_ID()!=null && !"CD1413".equalsIgnoreCase(indentList.get(cnt).getSTATUS())){
	%>
	<tr style="background-color:#B8E6E6;">
	<% }else if(indentList.get(cnt).getSTATUS()!=null && "CD1413".equalsIgnoreCase(indentList.get(cnt).getSTATUS())){%>
	<!-- <tr style="background-color: red;"> -->
	<tr style="background-color: #f8b0b0;">
	<%} else{%>
	<tr style="background-color: #FFEBCC;">
	<%} %>
<c:if test="${total ne 'Y'}">
<td align="left" class="tbcellBorder" ><input class="tbcellBorder" type="checkbox" name="chb" id="chb${cnt+1}" onclick="fn_enable('${cnt+1}');">${startIndex+cnt+1} </td>
</c:if>
<c:if test="${total eq 'Y'}">
<td align="left" class="tbcellBorder"> ${startIndex + cnt + 1}</td>
</c:if>
<input type="hidden" id="drugCode${cnt+1}" value="<bean:write name="labelBean" property="DRUG_ID"/>" >
<input type="hidden" id="indent${cnt+1}" value="<bean:write name="labelBean" property="INDENT_ID"/>" >
<input type="hidden" id="indent" value="<bean:write name="labelBean" property="INDENT_ID"/>" >
<input type="hidden" id="dispId" value= "<bean:write name="labelBean" property="DISP_ID"/>">

<%-- <logic:notEmpty name="labelBean" property="INDENT_ID">
<td align="left" class="tbcellBorder" ><input type="text" id="indent${cnt+1}" style="width: 220px;" value="<bean:write name="labelBean" property="INDENT_ID"/>" disabled></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="INDENT_ID">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  --%>
 
<logic:notEmpty name="labelBean" property="ITEM_ID">
<logic:equal name="labelBean" property="CRITICAL_FLG" value="Y">
        <td align="left" class="tbcellBorder" style="color:red;">
            <bean:write name="labelBean" property="ITEM_ID"/>
        </td>
    </logic:equal>
    <logic:notEqual name="labelBean" property="CRITICAL_FLG" value="Y">
        <td align="left" class="tbcellBorder">
            <bean:write name="labelBean" property="ITEM_ID"/>
        </td>
    </logic:notEqual>
<%-- <td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ITEM_ID"/></td> --%>
<input type="hidden" id="itemId${cnt+1}" name="itemId" value="<bean:write name="labelBean" property="ITEM_ID"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="ITEM_ID">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
<logic:notEmpty name="labelBean" property="DISP_NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISP_NAME"/></td>
<input type="hidden" id="dispname" name="dispname" value="<bean:write name="labelBean" property="DISP_NAME"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="DISP_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
<logic:notEmpty name="labelBean" property="DRUG_TYPE">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DRUG_TYPE"/></td>
<input type="hidden" id="drugTyp${cnt+1}" name="drugTyp" value="<bean:write name="labelBean" property="DRUG_TYPE"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUG_TYPE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
<logic:notEmpty name="labelBean" property="DRUG_NAME">
<td align="left" class="tbcellBorder" onkeyup="checkAlphaNumeric(this);" ><bean:write name="labelBean" property="DRUG_NAME"/></td>
<input type="hidden" id="drugname${cnt+1}" value="<bean:write name="labelBean" property="DRUG_NAME"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="DRUG_NAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
 <c:choose>
<c:when test="${indentedStock eq 'Y'}">
	 <logic:notEmpty name="labelBean" property="REQ">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="REQ"/></td>
	<input type="hidden" id="idealStk${cnt+1}"  value="<bean:write name="labelBean" property="REQ"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="REQ">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty> 
	 
	 <logic:notEmpty name="labelBean" property="LST_3M_QUANT">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="LST_3M_QUANT"/></td>
	<input type="hidden" id="lst${cnt+1}" value="<bean:write name="labelBean" property="LST_3M_QUANT"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="LST_3M_QUANT">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="AVL">
	<td align="left" class="bothSideBorder2Px tbcellBorder" ><a class="blueFont" href="javascript:getAvlDetails(this,'<bean:write  name="labelBean" property="DISP_ID"/>','<bean:write  name="labelBean" property="DRUG_ID"/>');"><bean:write name="labelBean" property="AVL"/></a></td>
	<input type="hidden" id="avl${cnt+1}" value="<bean:write name="labelBean" property="AVL"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="AVL">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="IND_NOTRECV">
	<td align="left" class="tbcellBorder"><bean:write name="labelBean" property="IND_NOTRECV"/></td>
	<input type="hidden" id="indnotRec${cnt+1}" value="<bean:write name="labelBean" property="IND_NOTRECV"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="IND_NOTRECV">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	  <logic:notEmpty name="labelBean" property="NAT_STOCK">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="NAT_STOCK"/></td>
	<input type="hidden" id="natstkAvl${cnt+1}" value="<bean:write name="labelBean" property="NAT_STOCK"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="NAT_STOCK">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	   <logic:notEmpty name="labelBean" property="NAT_DEF">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="NAT_DEF"/></td>
	<input type="hidden" id="natDef${cnt+1}" value="<bean:write name="labelBean" property="NAT_DEF"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="NAT_DEF">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	 <%--  <logic:notEmpty name="labelBean" property="NAT_DEF">
	<td align="left" class="tbcellBorder" ><input type="text" id="indCnt${cnt+1}" style="width: 110px;" onchange="indentValue(this.value,${cnt+1});" value="<bean:write name="labelBean" property="NAT_DEF"/>" ></td>
	<input type="hidden" id="actualIndCnt${cnt+1}" value="<bean:write name="labelBean" property="NAT_DEF"/>" >
	</logic:notEmpty> --%>
	
	
	<logic:notEmpty name="labelBean" property="NAT_DEF">
     <td align="left" class="tbcellBorder">
    <!-- Hidden text for Excel export -->
    <span class="exportVal">
        <bean:write name="labelBean" property="NAT_DEF"/>
    </span>
    <!-- Editable input field -->    
    <input type="text" id="indCnt${cnt+1}" style="width: 110px;"
           onchange="indentValue(this.value,${cnt+1});"
           value="<bean:write name='labelBean' property='NAT_DEF'/>" >
   </td>
       <input type="hidden" id="actualIndCnt${cnt+1}"
       value="<bean:write name='labelBean' property='NAT_DEF'/>" >
    </logic:notEmpty>
    
    
	<logic:empty name="labelBean" property="NAT_DEF">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	  <logic:notEmpty name="labelBean" property="PRICE">
	  <td class="tbcellBorder">
   		<bean:write name="labelBean" property="PRICE"/>
    	<input type="hidden" id="price${cnt+1}" value="<bean:write name='labelBean' property='PRICE'/>">
     </td>
	<%-- <td align="left" class="tbcellBorder" ><input type="text" id="price${cnt+1}" style="width: 110px;" value="<bean:write name="labelBean" property="PRICE"/>" disabled></td> --%>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="PRICE">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="IND_VAL">
	<td class="tbcellBorder">
	    <bean:write name="labelBean" property="IND_VAL"/>
	    <input type="hidden" id="Indprice${cnt+1}" value="<bean:write name='labelBean' property='IND_VAL'/>">
	</td>
	 
	<%-- <td align="left" class="tbcellBorder" ><input type="text" id="Indprice${cnt+1}" style="width: 110px;" value="<bean:write name="labelBean" property="IND_VAL"/>" disabled></td> --%>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="IND_VAL">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>

</c:when>
<c:otherwise>
	 <logic:notEmpty name="labelBean" property="AVG_STK">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="AVG_STK"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="AVG_STK">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty> 
	
	    <logic:notEmpty name="labelBean" property="REQ">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="REQ"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="REQ">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>  
	 
	     <logic:notEmpty name="labelBean" property="AVL">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="AVL"/></td>
	<input type="hidden" id="avl${cnt+1}" value="<bean:write name="labelBean" property="AVL"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="AVL">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>  
	 
	<logic:notEmpty name="labelBean" property="STOCK">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="STOCK"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="STOCK">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>  
	 
	<logic:notEmpty name="labelBean" property="PRICE">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PRICE"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="PRICE">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>  
	
	<logic:notEmpty name="labelBean" property="TOT_STOCKPRICE">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="TOT_STOCKPRICE"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="TOT_STOCKPRICE">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>   
 </c:otherwise>
</c:choose>
 
<logic:notEmpty name="labelBean" property="CONTRACT_YEAR">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="CONTRACT_YEAR"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="CONTRACT_YEAR">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 <logic:notEmpty name="labelBean" property="REMARKS">
<td align="left" class="tbcellBorder" ><input type="text" id="remarks${cnt+1}" value="<bean:write name="labelBean" property="REMARKS"/>" ></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="REMARKS">
 <td align="left" class="tbcellBorder" ><input type="text" id="remarks${cnt+1}" value="<bean:write name="labelBean" property="REMARKS"/>" ></td>
 </logic:empty>  
 
</tr>
</logic:iterate>
<%-- <tr>
	<td colspan="5" align="left" class="tbcellBorder" style="text-align:center;color:red" >Total</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
	<c:if test="${indentedStock eq 'Y'}">
	<td align="left" class="tbcellBorder" >-NA-</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
	</c:if>
	<td align="left" class="tbcellBorder" >${totStockPrice}</td>
	<td align="left" class="tbcellBorder" >-NA-</td>
</tr> --%>
</tbody>
</table>
<table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<c:if test="${total ne 'Y'}">
<div class="row" align="center"> 
		Initiation Remarks : <input type="text" id="remarks" >
</div>
<div class="row" align="center"> 
		<input class="but" type="button" value="Submit" onclick="javascript:fn_submit()" />
		<c:if test="${lowStockFlag ne 'Y' }">
		<input class="but" type="button" value="Back" onclick="javascript:fn_back()" /></c:if>
</div>
</c:if>
<c:if test="${total eq 'Y'}">
<div class="row" align="center"> 
		<c:if test="${lowStockFlag ne 'Y' }">
		<input class="but" type="button" value="Back" onclick="javascript:fn_back()" />
	    </c:if>
</div>
</c:if>
</table>
</logic:greaterThan>
<logic:equal value="0" name="size"><!-- Chandana - 9008 - changed lessthan to equal -->
	<div class="error-desk" align="center">
		<br> <h4>No Records Found</h4> <br>
	</div>
</logic:equal>
</logic:present>

<%-- <logic:empty name="patientForm" property="indentList">
 
          <div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
          
    
		</logic:empty> --%>
<html:hidden property="showPage" name="patientForm" />
<html:hidden property="startPage" name="patientForm" value="${startPage}" />
<html:hidden property="endPage" name="patientForm" value="${endPage}"/>
<html:hidden property="rowsPerPage" name="patientForm" />
<!-- <table class="table"   cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
	<td colspan="2"  align="center">
		<input class="but" type="button" value="Submit" onclick="javascript:fn_submit()" /></td>	
	</tr>
</table> -->
<div class="modal fade" id="avlDtls" role="dialog" >
    <div class="modal-dialog" style="width:100%">
    
      <!-- Modal content-->
      <div class="modal-content " >
     	<div style="text-align:center;color:#fdfdfd; font-size: 14px;">
          
          
          <button type="button" class="close" style="opacity: 1;color: #01964d;" onclick="closeModal('avlDtls','phcDataFrameDiv')">×</button> 
         <h3 style="text-align:center;color:rgb(1, 140, 128) !important; font-size: 16px;"><div id="header" style="display: contents; color: white;"></div></h3>
          <input class="but" type="button" style="margin-left:1200px;" value="Print" onclick="javascript:fn_print('no-more-tables','','Report Based on Particular PO Number');"/>
       </div>

        <div class="modal-body">
        <div>
		<div id="phcCountDiv">

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
							<th class="tableHeader" style="font-size: 14px !important;text-align:center"><b>BATCH NO</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;text-align:center"><b>AVAILABLE QUANTITY</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>EXPIRY DATE</b></th>
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
</html:form>


</body>
</html>