<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
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
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
<style type="text/css">
#printable {
	display: none;
}

@page {
	size: auto; /* auto is the initial value */
	margin: 5mm; /* this affects the margin in the printer settings */
}

@media print {
	#non-printable {
		display: none;
	}
	#printable {
		display: flex;
		justify-content: center;
	}
	#logo img {
		display: none;
	}
	#logo:after {
		content: url(images/TG_logo2.png);
	}
}

@media (min-width: 768px){
	.modal-dialog {
	    width: 800px;
	    margin: 30px auto;
	    height: 435px;
	}
}

.modal-header {
    background-color: #1E4B89;
    color: white;
}
.modal-title{
	margin-left: 35%;
}
#middleFrame{
height: 480px;
}

.close {
    float: right;
    font-size: 21px;
    font-weight: 700;
    line-height: 1;
    color: white;
    text-shadow: 0 1px 0 #fff;
    filter: alpha(opacity=20);
    opacity: 1.2;
}

.close:hover {
    float: right;
    font-size: 21px;
    font-weight: 700;
    line-height: 1;
    color: #f0e68c;
    text-shadow: 0 1px 0 #fff;
    filter: alpha(opacity=20);
    opacity: 1.2;
}

.form-control {
    display: block;
    width: 100%;
    height: 34px;
    padding: 6px 12px;
    font-size: 10px;
    
    }
 label {
    display: inline-block;
    max-width: 100%;
    margin-bottom: 5px;
    font-weight: 700;
    font-size: 75%;
}   

.read-only {
    background-color: transparent;
    cursor: default;
}

</style>
<script>

$(document).ready(function(){
    $(this).scrollTop(0);
});

var date = new Date();
$(document).ready(function() {
	// Make the fromDate input field read-only
	$('#fromDate').prop('readonly', true);
	$('#destDate').prop('readonly', true);
	$('#expiryDate').prop('readonly', true);
	$('#quantityAddedDate').prop('readonly', true);
 	
	 $('#fromDate').datepicker({
			autoclose:true,
			format : 'dd-mm-yyyy',
			todayHighlight:true,
			clearBtn:true,
			startDate:'01/01/2012',
			endDate:new Date(),
	 }).on('changeDate', function(selected){
			if(selected.date!=null && selected.date!='' && selected.date!=undefined)
				$('#destDate').datepicker('setStartDate',new Date(selected.date.valueOf()));
			else if(selected.date==undefined || selected.date!=null || selected.date!='' )
				$('#destDate').datepicker('setStartDate','01/01/1900');		
			
		});	
    
		$('#destDate').datepicker({
			startDate: '01/01/2012',
			todayHighlight:true,
			format : 'dd-mm-yyyy',
			autoclose:true,
			clearBtn:true,
			endDate:new Date(),
		}).on('changeDate',function(selected){
	 		if(selected.date!=null && selected.date!='' && selected.date!=undefined)
				$('#fromDate').datepicker('setEndDate',new Date(selected.date.valueOf()));
			else if(selected.date==undefined || selected.date!=null || selected.date!='' )
				$('#fromDate').datepicker('setEndDate',new Date());
		});	
		
		// expiryDate
		$('#expiryDate').datepicker({
			autoclose:true,
			format : 'dd-mm-yyyy',
			todayHighlight:true,
			clearBtn:true,
			startDate:new Date(),
			orientation: "top"
	 	})
				
	 	$('#quantityAddedDate').datepicker({
			autoclose:true,
			format : 'dd-mm-yyyy',
			todayHighlight:true,
			clearBtn:true,
			endDate:new Date(),
			orientation: "top",
	        widgetPositioning: {
	            horizontal: 'auto',
	            vertical: 'bottom'
	        }
	 	});
});
	
	</script>


<script type="text/javascript">
function generate(){			
	if(validate()){		
		var destDate=document.getElementById('destDate').value;
		var fromDate=document.getElementById('fromDate').value;
		var dispname=document.getElementById('dispname').value;
		var distributorId=document.getElementById('dstrbtrname').value;		
		var drugname=document.getElementById('dName').value;

		fn_loadImage();
		document.forms[0].action="patientDetailsNew.do?actionFlag=drugInveReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugname="+drugname+"&distributorId="+distributorId;
    	document.forms[0].submit();
    	$('#generateId').attr('disabled',false);    
    	if(distributorId="ALL"){
    		document.getElementById('dstrbtrname').value;
    	}
	}
}
function fn_reset()
{ 
	 document.getElementById("destDate").value="";
	 document.getElementById("fromDate").value=""; 
     document.getElementById('dName').value="";    
     document.getElementById('dispname').value="";
 	 document.getElementById('dstrbtrname').value="ALL";     
     return false;     
}
function validate()
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
	/* if(document.forms[0].dispname.value=='' || document.forms[0].dispname.value==null){
		alert('Please Select Wellness Center Name ');
		focusBox(document.getElementById("dispname"));
		return false;
	} */
	return true;
}
function fn_loadImage(){
document.getElementById('processImagetable').style.display="";
}
function fn_removeLoadingImage(){
	 document.getElementById('processImagetable').style.display="none"; 	 
}
function focusBox(arg){
  aField = arg; 
  setTimeout("aField.focus()", 0); 
}
function trimDot(arg){	
 	arg.value = arg.value.replace(/\s+/g, '').replace(/(\.\s+)+/g, '.').replace(/\.+/g, '.').replace(/,+/g, ',').replace(/:+/g, ':').replace(/\-+/g, '-').replace(/\/+/g, '/').replace(/^[ ]+|[ ]+$/g,'').replace(/^[,]+|[,]+$/g,'').replace(/^[.]+|[.]+$/g,'');
}
function newexportToCsv(arg){
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
	var drugname=document.getElementById('dName').value;	
	document.forms[0].action = "patientDetailsNew.do?actionFlag=drugreportCsvNew&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugname="+drugname+"&reportType="+arg;
	document.forms[0].submit();  	   
}

function newexportToExcel(arg){
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
	var drugname=document.getElementById('dName').value;	
	var genFlag="E";
	document.forms[0].action = "patientDetailsNew.do?actionFlag=drugreportExcel&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugname="+drugname+"&reportType="+arg+"&genFlag="+genFlag;
	document.forms[0].submit();  
	   
}

function showinSetsOf(num){   

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
	$('#processImagetable').show();
	var detailedRep = '${fromDetailed}';
	var destDate=document.getElementById('destDate').value;
	var fromDate=document.getElementById('fromDate').value;
	var dispname=document.getElementById('dispname').value;
	var drugname=document.getElementById('dName').value;
	var distributorId=document.getElementById('dstrbtrname').value;
	
	if( detailedRep=="N")
	{
		
		var url="./patientDetailsNew.do?actionFlag=drugInveReport&destDate="+destDate+"&fromDate="+fromDate+"&dispname="+dispname+"&drugname="+drugname+"&pageId="+pageId+"&endIndex="+endIndex+"&distributorId="+distributorId;
		document.forms[0].action=url;
		 document.forms[0].submit(); 
	 }
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

function fnGetDrugDetails(DRUGTYPE, DRUG_TYPE_ID, DRUGTYPES, DRUGNAME, DRUG_ID, MNFCTRNAME, MNFCTR_ID, DSTRBTRNAME, DSTRBTR_ID, BATCHNO, EXPDT, INVOICENO, CRTDATE, PACKAGE_AMOUNT, AVAILABLE_QUANTITY, DISPNAME, DISP_ID){
// 	alert("EXPDT:: " +EXPDT);
	document.getElementById('drugType').value = DRUGTYPE;
	document.getElementById('drugTypeId').value = DRUG_TYPE_ID;
	document.getElementById('drugCode').value = DRUGTYPES;
	document.getElementById('drugName').value = DRUGNAME;
	document.getElementById('drugId').value = DRUG_ID;
	document.getElementById('manufacturerName').value = MNFCTRNAME;
	document.getElementById('manufacturerId').value = MNFCTR_ID;
	document.getElementById('distributorName').value = DSTRBTRNAME;
	document.getElementById('distributorId').value = DSTRBTR_ID;
	document.getElementById('batchNo').value = BATCHNO;
	document.getElementById('expiryDate').value = EXPDT;
	document.getElementById('invoiceNo').value = INVOICENO;
	document.getElementById('quantityAddedDate').value = CRTDATE;
// 	document.getElementById('addedQuantity').value = PACKAGE_AMOUNT;
	document.getElementById('availableQuantity').value = AVAILABLE_QUANTITY;
		
	setDropdownValues();
	getDrugInventoryMaster();
	
		
}

function confirmSaving() {
	var batchNo = $('#batchNo').val();
	var expiryDate = $('#expiryDate').val();
	var invoiceNo = $('#invoiceNo').val();
	var quantityAddedDate = $('#quantityAddedDate').val();
	
	if(!batchNo){	
		alert("Please enter Batch No");
		return false;
	}else if(!expiryDate){		
		alert("Please enter Expirty Date");
		return false;
	}else if(!invoiceNo){		
		alert("Please enter Invoice No");
		return false;
	}else if(!quantityAddedDate){		
		alert("Please enter Quantity Added Date");
		return false;
	}else{
	 	var confirmation = confirm("Do you want to proceed?");    
	    if (confirmation) {
			updateRecords();	     
	    } else {
	    return false;
	    }
	}	
   
  }

function updateRecords(){	
// 	$('#updateRecordId').attr('disabled',true);  

	var drugType = $('#drugType').val();
	var drugCode = $('#drugCode').val();
	var drugName = $('#drugName').val();
	var manufacturerName = $('#manufacturerName').val();
	var distributorName = $('#distributorName').val();
	var batchNo = $('#batchNo').val();
	var expiryDate = $('#expiryDate').val();
	var invoiceNo = $('#invoiceNo').val();
	var quantityAddedDate = $('#quantityAddedDate').val();
// 	var addedQuantity = $('#addedQuantity').val();
	var availableQuantity = $('#availableQuantity').val();
	
	var drugTypeNew = $('#drugTypesSelect').val();
	var drugNameNew = $('#drugNamesSelect').val();
	var manftNameNew = $('#manftNamesSelect').val();
	var distrNameNew = $('#distriNamesSelect').val();
		
// 	if(!addedQuantity){
// 		alert("Quantity can not be blank");
// 		return false;
// 	}
	if(!availableQuantity){
		alert("Available Quantity can not be blank");
		return false;
	}
		
	$('#processImagetableNew').show();
	$('#modalbody').hide();
	var requestUrl = "patientDetailsNew.do?actionFlag=updateDrugInventryReport";	
	$.ajax({
		type:"POST",
		url:requestUrl,
		data:{
			drugTypeVal:drugType,
			drugCodeVal:drugCode,
			drugNameVal:drugName,
			manufacturerNameVal:manufacturerName,
			distributorNameVal:distributorName,
			batchNoVal:batchNo,
			expiryDateVal:expiryDate,
			invoiceNoVal:invoiceNo,
			quantityAddedDateVal:quantityAddedDate,
// 			addedQuantityVal:addedQuantity,
			availableQuantityVal:availableQuantity,
			drugTypeNewVal:drugTypeNew,
			drugNameNewVal:drugNameNew,
			manftNameNewVal:manftNameNew,
			distrNameNewVal:distrNameNew
		},
		beforeSend : function() {
		},
		success : function(response) { 
			var resp = JSON.parse(response);
// 			console.log("records::"	+ JSON.stringify(resp));
			
			var resp = JSON.parse(response);					
			if (resp.status == "success") {				
				$('#updateRecordId').attr('disabled',true);  
				alert(resp.message);
				generate();
				$('#updateRecordId').attr('disabled',false);
				console.log("status::"	+ resp.status);														
			}else if (resp.status == "error") {
				$('#processImagetableNew').hide();
				$('#modalbody').show();
				console.log("status::"	+ resp.status);
				$('#updateRecordId').attr('disabled',false);
				alert(resp.message); 
			} else {	
				$('#processImagetableNew').hide();
				$('#modalbody').show();
				alert("Sorry! Some thing went wrong, please try after some time.");
			}
		},
		error : function(textStatus, errorThrown) { 
			$('#updateRecordId').attr('disabled',false);
			$('#processImagetable').hide();
			alert("Some thing went wrong, please try again");
		}
	});

}

</script>
<script type="text/javascript">
        function getDrugInventoryMaster() {  
        	var drugNamesSelect = $('#drugNamesSelect').val();        	
        	if(!drugNamesSelect){          	
            $('#modalbody').hide();         	
        	$('#processImagetableNew').show();	 
        
        	var drugTypes;
        	var requestUrl = "patientDetailsNew.do?actionFlag=getDrugInventoryMaster";        	        	
        	$.ajax({
        		type:"POST",
        		url:requestUrl,
        		data:{},
        		beforeSend : function() {
        		},
        		success : function(response) { 
        			var resp = JSON.parse(response);
        			console.log("records:: "	+ JSON.stringify(resp));        		
        			var resp = JSON.parse(response);					
        			if (resp.status == "success") {        				
        				drugTypes = resp.drugTypes;        				
        				drugNames = resp.drugNames;        				
        				mangftNames = resp.mangftNames;        				
        				distriNames = resp.distriNames;   
        				
        				populateDrugTypeSelect(drugTypes);
        				populateDrugNameSelect(drugNames);
        				populateManftNamesSelect(mangftNames);
        				populateDistriNamesSelect(distriNames);
        				setDropdownValues();
        				  
        				$('#processImagetableNew').hide();
        	            $('#modalbody').show();       				
        																	
        			}else if (resp.status == "error") {
        				console.log("status::"	+ resp.status);	
        				alert(resp.message); 
        			} else {							
        				alert("Sorry! Some thing went wrong, please try after some time.");
        			}
        		},
        		error : function(textStatus, errorThrown) { 
        			$('#processImagetableNew').hide();
        			alert("Some thing went wrong, please try again");
        		}
        	});
        	
        	}
        	
        }         
        
        // Function to populate the Drug Type select element
        function populateDrugTypeSelect(drugTypes) {
            var select = $("#drugTypesSelect");
            select.empty(); // Clear any existing options            
            for (var i = 0; i < drugTypes.length; i++) {
                var option = $("<option></option>")
                    .attr("value", drugTypes[i].DRUG_TYPE_ID)
                    .text(drugTypes[i].DRUG_TYPE_NAME);
                select.append(option);
            }
        }    
        
        // Function to populate the Drug Name select element
        function populateDrugNameSelect(drugNames) {
            var select = $("#drugNamesSelect");
            select.empty(); // Clear any existing options            
            for (var i = 0; i < drugNames.length; i++) {
                var option = $("<option></option>")
                    .attr("value", drugNames[i].DRUG_ID)
                    .text(drugNames[i].DRUG_NAME);
                select.append(option);
            }
        } 
        
        // Function to populate the Manufactures Name select element
        function populateManftNamesSelect(manftNames) {
            var select = $("#manftNamesSelect");
            select.empty(); // Clear any existing options            
            for (var i = 0; i < manftNames.length; i++) {
                var option = $("<option></option>")
                    .attr("value", manftNames[i].MNFCTR_ID)
                    .text(manftNames[i].MNFCTR_NAME);
                select.append(option);
            }
        } 
        
        // Function to populate the Distributor Name select element
        function populateDistriNamesSelect(distrNames) {
            var select = $("#distriNamesSelect");
            select.empty(); // Clear any existing options            
            for (var i = 0; i < distrNames.length; i++) {
                var option = $("<option></option>")
                    .attr("value", distrNames[i].DSTRBTR_ID)
                    .text(distrNames[i].DSTRBTR_NAME);
                select.append(option);
            }
        } 
        
        function setDropdownValues(){

			$('#drugTypesSelect').val('');
			$('#drugNamesSelect').val('');
			$('#manftNamesSelect').val('');
			$('#distriNamesSelect').val('');
			
        	document.getElementById('drugTypesSelect').value = $('#drugTypeId').val();
        	document.getElementById('drugNamesSelect').value = $('#drugId').val();
        	document.getElementById('manftNamesSelect').value = $('#manufacturerId').val();
        	document.getElementById('distriNamesSelect').value = $('#distributorId').val();
        	
        }
            
        $('#drugTypesSelect').attr("disabled", true); 
        
    </script>

</head>
<body >
	<!-- END FOR SETTINGS POPUP -->
	

<!-- Modal -->
<div class="modal fade" id="editDrugsReportModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content" id="processImagetableNew" style="display: none;height:50%">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Drug Inventory</h4>
      </div>
<!--       <div id="modalbody">		 -->
		<div class="form-row">
			<div style="top: 30%; z-index: 50; position: absolute; left: 40%;">
				<div id="modalbodyOne">
					<div class="form-row">
						<table border="0" align="center" width="100%" style="height: 100">
							<tr>
								<td>
									<div id="processImage" align="center">
										<img src="images/Progress.gif" width="100" height="100"
											border="0" tabIndex="3"></img>
									</div>
								</td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
<!-- 	 </div>	 -->
    </div>
    
    <div class="modal-content" id="modalbody" style="height:660px">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Edit Drug Inventory Report</h4>
      </div>
<!--       <div class="modal-body"> -->
			
<!-- 		<div id="modalbody">		 -->
		<div class="form-row">			
			<div class="form-group col-md-6">
				<label for="drugType">DRUG TYPE:</label> 
				<input type="hidden" class="form-control" id="drugType" placeholder="Drug Type" readonly>
				<input type="hidden" class="form-control" id="drugTypeId" placeholder="Drug Type Id" readonly>
				<select class="form-control" id="drugTypesSelect" class="select2" disabled></select>
			</div>
			<div class="form-group col-md-6">
				<label for="drugCode">DRUG CODE:</label> 
				<input
					type="text" class="form-control" id="drugCode"
					placeholder="Drug Code" readonly>
			</div>
			
			<div class="form-group col-md-6">
				<label for="drugName">DRUG NAME:</label> 
				<input type="hidden" class="form-control" id="drugName" placeholder="Drug Name">				
				<input type="hidden" class="form-control" id="drugId" placeholder="Drug Id">				
				<select class="form-control" id="drugNamesSelect" class="select2" disabled></select>
				
			</div>
			<div class="form-group col-md-6">
				<label for="manufacturerName">MANUFACTURER NAME:</label> 
				<input type="hidden" class="form-control" id="manufacturerName" placeholder="Manufacturer Name">
				<input type="hidden" class="form-control" id="manufacturerId" placeholder="Manufacturer Id">
				<select class="form-control" id="manftNamesSelect" class="select2"></select>
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="distributorName">DISTRIBUTOR NAME:</label>
				 <input type="hidden"	class="form-control" id="distributorName" placeholder="DISTRIBUTOR NAME">
				 <input type="hidden"	class="form-control" id="distributorId" placeholder="DISTRIBUTOR NAME">
					<select class="form-control" id="distriNamesSelect" class="select2"></select>
			</div>
			<div class="form-group col-md-6">
				<label for="batchNo">BATCH NO:</label>&nbsp;<span style="color: red">*</span> 
				<input
					type="text" class="form-control" id="batchNo"
					placeholder="BATCH NO" maxlength="30">
			</div>
		</div>
		<div class="form-row">
			<div class="form-group col-md-6">
				<label for="expiryDate">EXPIRY DATE:</label>&nbsp;<span style="color: red">*</span>
				 <input type="text"
					class="form-control" id="expiryDate" placeholder="EXPIRY DATE" style="background-color: transparent;cursor: default;">
			</div>
			<div class="form-group col-md-6">
				<label for="invoiceNo">INVOICE NO:</label>&nbsp;<span style="color: red">*</span> 
				<input
					type="text" class="form-control" id="invoiceNo"
					placeholder="INVOICE NO" maxlength="30">
			</div>
		</div>	
		<div class="form-row">
			<div class="form-group col-md-12">
				<label for="quantityAddedDate">QUANTITY ADDED DATE:</label>&nbsp;<span style="color: red">*</span> 
				<input type="text"
					class="form-control" id="quantityAddedDate" placeholder="QUANTITY ADDED DATE" 
					style="width: 48.2%;background-color: transparent;cursor: default;"
					>
			</div>
			
			<!-- <div class="form-group col-md-6">
				<label for="addedQuantity">ADDED QUANTITY:</label> <input
					type="text" class="form-control" id="addedQuantity"
					placeholder="ADDED QUANTITY" maxlength="10">
			</div> -->
		</div>	
		
		
		<div class="form-row">
				<input
					type="hidden" class="form-control" id="availableQuantity"
					placeholder="AVAILABLE QUANTITY" maxlength="10">
				 <input
					type="hidden" class="form-control" id="actualQuantity"
					placeholder="ACTUAL QUANTITY" maxlength="10">	
					
			<!-- <div class="form-group col-md-6">
				<label for="availableQuantity">AVAILABLE QUANTITY:</label> 
				<input
					type="text" class="form-control" id="availableQuantity"
					placeholder="AVAILABLE QUANTITY" maxlength="10">
			</div>
			<div class="form-group col-md-6">
				<label for="actualQuantity">ACTUAL QUANTITY:</label>
				 <input
					type="text" class="form-control" id="actualQuantity"
					placeholder="ACTUAL QUANTITY" maxlength="10">
			</div> -->
		</div>	
		
			 
<!-- 	</div>	         -->
<!--       </div> -->
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" id="updateRecordId" onclick="confirmSaving()" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>
	<div id="processImagetable"
			style="top: 50%; left: 45%; display: none; position: absolute; z-index: 60; height: 100%">
			<table border="0" width="100%" style="height: 400">
				<tr>
					<td>
						<div id="processImage">
							<img src="images/Progress.gif" width="100" height="100"
								border="0"></img>
						</div>
					</td>
				</tr>
			</table>
		</div>
<!-- Modal End -->
	<html:form action="/patientDetailsNew.do">
	
		<table style="width: 80%; margin: 1% auto; font-size:90%">
			<tr>
				<th colspan="4" class="tbheader">Edit Drug Inventory</th>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss" style="width: 20%;" align="left"><b>From
						Date</b>&nbsp;<span style="color: red">*</span></td>
				<td class="tbcellBorder" style="width: 20%;" align="left"><html:text
						property="fromDate" styleId="fromDate" style="width:70%" /></td>
				<td class="labelheading1 tbcellCss" style="width: 20%;" align="left"><b>To
						Date</b>&nbsp;<span style="color: red">*</span></td>
				<td class="tbcellBorder" style="width: 20%;" align="left"><html:text
						property="destDate" styleId="destDate" style="width:70%" /></td>
			</tr>
			<tr>
				<td colspan="1" class="labelheading1 tbcellCss" align="left"><b>
						Wellness Center Name</b>&nbsp;<span style="color: red">*</span></td>
				<td align="left" class="tbcellBorder">
					<html:select
						property="dispname" name="patientForm" styleId="dispname"
						title="Select Wellness Center">
						<option value="">--------ALL--------</option>
						<c:if
							test="${loginName eq 'TG_D082' or  loginName eq 'TG_D093' or loginName eq 'TG_C246'}">
							<option value="ALL">ALL WCs</option>
						</c:if>
						<html:options collection="wellnesslist" property="DISPID"
							labelProperty="DISPNAME"></html:options>
					</html:select>
				</td>
				<td class="labelheading1 tbcellCss" colspan="1" align="left"><b>Drug
						Name&nbsp;</b></td>
				<td class="tbcellBorder" colspan="1" align="left"><html:text
						property="dName" styleId="dName" style="width:70%" />
			</tr>
			<tr>
				<td colspan="1" class="labelheading1 tbcellCss" align="left"><b>Distributor
						Name</b></td>
				<td align="left" class="tbcellBorder">
				<html:select
						property="DSTRBTRNAME" styleId="dstrbtrname" name="patientForm">				
						<option value="ALL">ALL</option>
						<html:options collection="distributorList" property="DSTRBTRID"
							labelProperty="DSTRBTRNAME"></html:options>
				</html:select>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input class="but" type="button"
					value="Search" onclick="javascript:generate()" id="generateId" /> <input
					class="but" type="button" value="Reset"
					onclick="javascript:fn_reset()" /></td>
			</tr>
		</table>
				
		
<!-- 		<div id="recordsContainer" style="display:none"> -->
		<div id="recordsContainer">
		<logic:present name="patientForm" property="drugReportList">
			<bean:size id="size" name="patientForm" property="drugReportList" />

			<logic:greaterThan value="0" name="size">

				<div class="row" style="font-size: 88%;">
					<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group"
						style="padding-left: 3%;">
						<ul class="pagination">Showing Results :${startIndex+1} -
							${endResults} of ${totalRecords}

						</ul>
					</div>


					<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group"
						id="pageNoDisplay">
						<ul class="pagination">
							<li>Pages :&nbsp;&nbsp;</li>
							<%
								int selectedPage = Integer.parseInt(request
													.getAttribute("pageId").toString());
											int totalPages = Integer.parseInt(request.getAttribute(
													"totalPages").toString());
											int totalRecords = Integer.parseInt(request
													.getAttribute("totalRecords").toString());
											int endIndex = Integer.parseInt(request.getAttribute(
													"endIndex").toString());

											int a = 0, minVal = 0, maxVal = 0;
											a = selectedPage / 10;
											minVal = a * 10;
											if (selectedPage % 10 == 0)
												minVal = minVal - 10;
											maxVal = minVal + 10;
											if (maxVal >= totalPages)
												maxVal = totalPages;
											minVal = minVal + 1;
											if (minVal > 10) {
							%>
							<li><a href="#"><span
									class="glyphicon glyphicon-backward"
									onclick="javascript:viewPreviousPages(<%=minVal%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)"
									style="cursor: pointer; top: 0px;"></span></a></li>
							<%
								}
											for (int i = minVal; i <= maxVal; i++) {
												if (i == selectedPage) {
							%>
							<li class="active"><span><%=i%></span></li>
							<%
								} else {
							%>
							<li><a href="javascript:fn_pagination(<%=i%>,<%=endIndex%>)"><%=i%></a></li>
							<%
								}

											}
											if (maxVal < totalPages) {
							%>
							<li><span class="glyphicon glyphicon-forward"
								onclick="javascript:viewNextPages(<%=maxVal + 1%>,<%=totalPages%>,<%=selectedPage%>,<%=endIndex%>)"
								style="cursor: pointer; top: 0px;"></span></li>
							<%
								}
							%>
						</ul>
					</div>

					<div class="col-xs-12 col-sm-12  col-md-4 col-lg-4 form-group"
						style="padding-left: 8%;">
						<ul class="pagination">
							<li class="lispacing">Show results in sets of</li>
							<c:if test="${endIndex ne 10}">
								<li><a href="javascript:fn_pagination(1,10)">10</a></li>
							</c:if>
							<c:if test="${endIndex eq 10}">
								<li class="active"><span>10</span></li>
							</c:if>
							<c:if test="${endIndex ne 20}">
								<li><a href="javascript:fn_pagination(1,20)">20</a></li>
							</c:if>
							<c:if test="${endIndex eq 20}">
								<li class="active"><span>20</span></li>
							</c:if>
							<c:if test="${endIndex ne 50}">
								<li><a href="javascript:fn_pagination(1,50)">50</a></li>
							</c:if>
							<c:if test="${endIndex eq 50}">
								<li class="active"><span>50</span></li>
							</c:if>
							<c:if test="${endIndex ne 100}">
								<li><a href="javascript:fn_pagination(1,100)">100</a></li>
							</c:if>
							<c:if test="${endIndex eq 100}">
								<li class="active"><span>100</span></li>
							</c:if>
							<c:if test="${endIndex ne 1000}">
								<li><a href="javascript:fn_pagination(1,1000)">1000</a></li>
							</c:if>
							<c:if test="${endIndex eq 1000}">
								<li class="active"><span>1000</span></li>
							</c:if>
						</ul>
					</div>

				</div>
				<table class="table" id="miscActTbl" cellSpacing="1" cellPadding="1"
					style="text-align: center; margin: 0 auto; width: 97%;font-size:80%;">
					<tr>
						<th class="tbheader1">S NO</th>
						<th class="tbheader1">DRUG TYPE</th>
						<th class="tbheader1">DRUG CODE</th>
						<th class="tbheader1">DRUG NAME</th>
						<th class="tbheader1">MANUFACTURER NAME</th>
						<th class="tbheader1">DISTRIBUTOR NAME</th>
						<th class="tbheader1">BATCH NO</th>
						<th class="tbheader1">EXPIRY DATE</th>
						<th class="tbheader1">INVOICE NO</th>
						<th class="tbheader1">QUANTITY ADDED DATE</th>
						<th class="tbheader1">ADDED QUANTITY</th>
<!-- 						<th class="tbheader1">AVAILABLE QUANTITY</th> -->
						<th class="tbheader1">WELLNESS CENTER NAME</th>

					</tr>
					<logic:iterate name="patientForm" property="drugReportList"
						id="labelBean" indexId="cnt">
						<tr>
							<td align="left" class="tbcellBorder">${startIndex+cnt+1}</td>

							<logic:notEmpty name="labelBean" property="DRUGTYPE">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="DRUGTYPE" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="DRUGTYPE">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>

							<logic:notEmpty name="labelBean" property="DRUGTYPES">

								<td align="left" class="tbcellBorder"><a href="javascript:"
									onclick="fnGetDrugDetails(
										'<bean:write	name="labelBean" property="DRUGTYPE" />',
										'<bean:write	name="labelBean" property="DRUG_TYPE_ID" />',
										'<bean:write	name="labelBean" property="DRUGTYPES" />',
										'<bean:write	name="labelBean" property="DRUGNAME" />',
										'<bean:write	name="labelBean" property="DRUG_ID" />',
										'<bean:write	name="labelBean" property="MNFCTRNAME" />',
										'<bean:write	name="labelBean" property="MNFCTR_ID" />',
										'<bean:write	name="labelBean" property="DSTRBTRNAME" />',
										'<bean:write	name="labelBean" property="DSTRBTR_ID" />',
										'<bean:write	name="labelBean" property="BATCHNO" />',
										'<bean:write	name="labelBean" property="EXPDT" />',
										'<bean:write	name="labelBean" property="INVOICENO" />',
										'<bean:write	name="labelBean" property="CRTDATE" />',
										'<bean:write	name="labelBean" property="PACKAGE_AMOUNT" />',
										'<bean:write	name="labelBean" property="AVAILABLE_QUANTITY" />',
										'<bean:write	name="labelBean" property="DISPNAME" />',
										'<bean:write	name="labelBean" property="DISP_ID" />'
									)"
									data-toggle="modal" data-target="#editDrugsReportModal"> <bean:write
											name="labelBean" property="DRUGTYPES" />
								</a></td>


							</logic:notEmpty>
							<logic:empty name="labelBean" property="DRUGTYPES">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>

							<logic:notEmpty name="labelBean" property="DRUGNAME">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="DRUGNAME" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="DRUGNAME">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>
							<logic:notEmpty name="labelBean" property="MNFCTRNAME">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="MNFCTRNAME" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="MNFCTRNAME">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>

							<logic:notEmpty name="labelBean" property="DSTRBTRNAME">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="DSTRBTRNAME" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="DSTRBTRNAME">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>

							<logic:notEmpty name="labelBean" property="BATCHNO">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="BATCHNO" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="BATCHNO">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>
							<logic:notEmpty name="labelBean" property="EXPDT">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="EXPDT" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="EXPDT">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>
							<logic:notEmpty name="labelBean" property="INVOICENO">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="INVOICENO" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="INVOICENO">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>
							<logic:notEmpty name="labelBean" property="CRTDATE">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="CRTDATE" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="CRTDATE">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>
							<logic:notEmpty name="labelBean" property="PACKAGE_AMOUNT">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="PACKAGE_AMOUNT" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="PACKAGE_AMOUNT">
								<td align="left" class="tbcellBorder">0</td>
							</logic:empty>
							
							<%-- <logic:notEmpty name="labelBean" property="AVAILABLE_QUANTITY">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="AVAILABLE_QUANTITY" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="AVAILABLE_QUANTITY">
								<td align="left" class="tbcellBorder">0</td>
							</logic:empty> --%>

							<logic:notEmpty name="labelBean" property="DISPNAME">
								<td align="left" class="tbcellBorder"><bean:write
										name="labelBean" property="DISPNAME" /></td>
							</logic:notEmpty>
							<logic:empty name="labelBean" property="DISPNAME">
								<td align="left" class="tbcellBorder">-NA-</td>
							</logic:empty>
						</tr>
					</logic:iterate>
				</table>
			</logic:greaterThan>
		</logic:present>
		</div>

		<logic:notPresent name="patientForm" property="drugReportList">
			<logic:equal value="0" name="size">
				<div class="error-desk" align="center">

					<br>
					<h4>No Records Found</h4>
					<br>
				</div>

			</logic:equal>
		</logic:notPresent>
		<!-- swarnit codes start here -->
		<logic:notPresent name="patientForm" property="drugReportList">
			<logic:equal value="0" name="totalRecords">
				<div class="error-desk" align="center">
					<br>
					<h4>No Records Found</h4>
					<br>
				</div>
			</logic:equal>
		</logic:notPresent>
		<!-- swarnit codes end here -->


		<%-- <html:hidden property="showPage" name="patientForm" />
		<html:hidden property="startPage" name="patientForm"
			value="${startPage}" />
		<html:hidden property="endPage" name="patientForm" value="${endPage}" />
		<html:hidden property="rowsPerPage" name="patientForm" /> --%>

		<div id="footer" style="display: none; position: fixed; bottom: 0;">

			<table style="width: 100%;">
				<tr>
					<td style="font: normal; font-size: 10px;"></td>
				</tr>
			</table>
		</div>
		<div id="printable">
			<div id="headerPrint">
				<table
					style="font-family: Trebuchet MS; width: 100%; border-bottom: 3px solid rgba(6, 170, 26, 1);">
					<tr>
						<td style="width: 100%; text-align: center; padding-left: 13%;"><font
							style="font-size: 20px"><b>Reports</b></font> <br> <br>
							<span style="font-size: 18px;"><b>GOVERNMENT OF
									TELANGANA</b></span></td>
						<td id="logo"><img src="images/TG_logo2.png" alt="..."
							height="80px" width="120px" style="float: right"></td>
					</tr>
					<tr>
						<br>
						<td style="width: 100%; text-align: center; padding-left: 13%;"><span
							style="font-size: 16px;"><b>DRUG INVENTORY REPORT</td>
					</tr>
					<tr>
						<td colspan="2" style="width: 100%; text-align: right;"><span>Report
								Generated On :<span id="dateToday"></span>
						</span></td>
					</tr>

				</table>
				<br>
			</div>

		</div>


	</html:form>


</body>
<script>
function setInputFilter(textbox, inputFilter, errMsg) {
	  [ "input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop", "focusout" ].forEach(function(event) {
	    textbox.addEventListener(event, function(e) {
	      if (inputFilter(this.value)) {
	        // Accepted value.
	        if ([ "keydown", "mousedown", "focusout" ].indexOf(e.type) >= 0){
	          this.classList.remove("input-error");
	          this.setCustomValidity("");
	        }

	        this.oldValue = this.value;
	        this.oldSelectionStart = this.selectionStart;
	        this.oldSelectionEnd = this.selectionEnd;
	      }
	      else if (this.hasOwnProperty("oldValue")) {
	        // Rejected value: restore the previous one.
	        this.classList.add("input-error");
	        this.setCustomValidity(errMsg);
	        this.reportValidity();
	        this.value = this.oldValue;
	        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
	      }
	      else {
	        // Rejected value: nothing to restore.
	        this.value = "";
	      }
	    });
	  });
	}
	
	// Install input filters.
// 	setInputFilter(document.getElementById("addedQuantity"), function(value) {
// 	  return /^-?\d*$/.test(value); }, "Must be a number");
	
	setInputFilter(document.getElementById("availableQuantity"), function(value) {
		  return /^-?\d*$/.test(value); }, "Must be a number");
	
	setInputFilter(document.getElementById("actualQuantity"), function(value) {
		  return /^-?\d*$/.test(value); }, "Must be a number"); 
	
	function setInputFilterAlphaNumeric(textbox, inputFilter, errMsg) {
		  [ "input", "keydown", "keyup", "mousedown", "mouseup", "select", "contextmenu", "drop", "focusout" ].forEach(function(event) {
		    textbox.addEventListener(event, function(e) {
		      if (inputFilter(this.value)) {
		        // Accepted value.
		        if ([ "keydown", "mousedown", "focusout" ].indexOf(e.type) >= 0){
		          this.classList.remove("input-error");
		          this.setCustomValidity("");
		        }

		        this.oldValue = this.value;
		        this.oldSelectionStart = this.selectionStart;
		        this.oldSelectionEnd = this.selectionEnd;
		      }
		      else if (this.hasOwnProperty("oldValue")) {
		        // Rejected value: restore the previous one.
		        this.classList.add("input-error");
		        this.setCustomValidity(errMsg);
		        this.reportValidity();
		        this.value = this.oldValue;
		        this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
		      }
		      else {
		        // Rejected value: nothing to restore.
		        this.value = "";
		      }
		    });
		  });
		}	
	
	// Install input filters.
	setInputFilterAlphaNumeric(document.getElementById("batchNo"), function(value) {
	  return /^[A-Za-z0-9\-_\\/]*$/.test(value); }, "Must be  alphanumeric");
	
	setInputFilterAlphaNumeric(document.getElementById("invoiceNo"), function(value) {
		  return /^[A-Za-z0-9\-_\\/]*$/.test(value); }, "Must be  alphanumeric");
</script>

</html>