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
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link href="css/select2.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css"> 
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
	
	fn_getDrugList($('#drugTypeID').val());
	
		});	
				
	
	</script>


<script type="text/javascript">
function generate()
{
		fn_loadImage();
		var yearFlag='${yearFlag}';
		document.forms[0].action="patientDetailsNew.do?actionFlag=getLowIndentReportsNew&yearFlag="+yearFlag+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";
    	document.forms[0].submit();
    	
    	
	
}
function fn_reset()
{
	/* document.getElementById('dispname').value=""; */
	document.getElementById("dispDrugID").value=""; 
	document.getElementById("drugTypeID").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=viewlowStockReport";
	document.forms[0].submit();
	
}
 function fn_resetNew()
{
	/*  document.getElementById('dispname').value=""; */
	 document.getElementById("dispDrugID").value=""; 
	 document.getElementById("drugTypeID").value=""; 
	document.forms[0].action="patientDetailsNew.do?actionFlag=viewlowStockReport";
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
 				  for(var i=0;i<rows-1;i++)
 					 {
 					 var j=i+1;
 					 var id="chb"+j;
 					
 					 
 					 if(document.getElementById(id).checked==true)
 					 {  var rowId = i+1; 
 					 
 					 var drugCode =document.getElementById("drugCode"+j).value;
 					 var drugName =document.getElementById("drugname"+j).value;
 					 var idealStk = document.getElementById("idealStk"+j).value;
 					 var avlCnt =document.getElementById("avl"+j).value; 
 					 var indnotRec =document.getElementById("indnotRec"+j).value; 
 					 var indCnt =document.getElementById("indCnt"+j).value; 
 					var itemId =document.getElementById("itemId"+j).value;
 					var drugTyp =document.getElementById("drugTyp"+j).value; 
 					 flag+=1;
 						 if(flag>0)
 							 {
 							drugList="";
 							 drugList=drugList+drugCode+"~"+drugName+"~"+indCnt+"~"+idealStk+"~"+avlCnt+"~"+indnotRec+"~"+itemId+"~"+drugTyp;
 							
 							
 							formData.append('drugList'+flag, drugList); 							
 							  	
 							}
 						
 					 }
 				
 					 
 				
 				 } 
  				 if(flag===0)
  				 {
  				 alert("Please select altleast one Check box to Submit");
				 return false;
  				 }
 			 
  				formData.append("dispId","${dispid}");
 			 if(confirm("Do you want to Submit?"))
 			 {
 			     $(':input[type="button"]').prop('disabled', true);
 			     fn_loadImage();
 			 /*  var url="./patientDetailsNew.do?actionFlag=submitLowStockList&drugList="+drugList;
 				document.forms[0].action=url;
 				document.forms[0].method="post";
 				document.forms[0].submit(); */
 			    $.ajax({
				        url: 'patientDetailsNew.do?actionFlag=submitLowStockList',
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
										parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=viewlowStockReport&indentedStock=Y&lowStock=Y';
									} else
										{
										alert("Items submitted for indenting. Awaiting for approval at Admin level.");
										parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=viewlowStockReport&indentedStock=Y&lowStock=Y';
										}
									fn_removeLoadingImage();
									/* parent.parent.resizeSubMiddleFrame(); */
				 				}
				        	}						
						}
					});
 			 }
 			 //jq(':input[type="button"]').prop('disabled', false);
 		
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


 function fn_getDrugList(ele)
{
 document.forms[0].dispDrugID.value="-1";
 var yearFlag='${yearFlag}';
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
			jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
 		 }   
 		 url = "patientDetailsNew.do?actionFlag=viewlowStockIndent&drugType="+ele+"&yearFlag=${yearFlag}"+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";    
 		 xmlhttp.onreadystatechange=function()
 		 {
     		if(xmlhttp.readyState==4)
     		{
  	   		var resultArray=xmlhttp.responseText;
  	 	    resultArray = resultArray.replace("[","");
   	    resultArray = resultArray.replace("@]",""); 
   	    resultArray = resultArray.replace("]",""); 
   	    resultArray = resultArray.replace("*",""); 
			resultArray = resultArray.trim();
   	    
   	   
         		if(resultArray.trim()=="SessionExpired*")
         		{
         			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
         		}
         		else
         		{         	                                           
                      		var drugsList = resultArray.split(","); 
                  			if(drugsList.length>0)
                  			{
                      			document.forms[0].dispDrugID.options.length=0;
                     			document.forms[0].dispDrugID.options[0]=new Option("--select--","-1");
                      			for(var i = 0; i<drugsList.length;i++)
                      			{	
                           		var arr=drugsList[i].split("~");                     
                          			if(arr[1]!=null && arr[0]!=null)
                           		{
                               		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                               		var val2 = arr[0].replace(/^\s+|\s+$/g,"");                                                                              
                               		document.forms[0].dispDrugID.options[i+1] =new Option(val1,val2);
                           		}
                          
                       		}
                   		}
       		}
     		}
 		 }
 		
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	   
	}
	 
}
 
 /* function fn_getDrugList()
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
 		$.ajax({
	        url: 'patientDetailsNew.do?actionFlag=viewlowStockIndent',
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
						try {
			                
			                $('#dispDrugID').find('option').remove();
			                $('#dispDrugID').append('<option value="">Select</option>');
			                var responseArr = response1.split(",");
			                for (var i = 0; i < responseArr.length; i++) {
			                    
			                    console.log(listVal);
			                    $('#assignTo').append(
			                            '<option value="'listVal[0]'">' + listVal[1]
			                                    + '</option>');
			                }
			    
			            } catch (e) {
			            }
						parent.parent.resizeSubMiddleFrame();
	 				}
	        	}						
			}
		});
 		 fn_loadImage();
 			 document.forms[0].action="patientDetailsNew.do?actionFlag=viewlowStockIndent&yearFlag="+yearFlag+"&indentedStock=${indentedStock}"+"&lowStock=${lowStock}";
 	    	document.forms[0].submit(); 
 }
 } */
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
			fn_getDrugList($('#drugTypeID').val());
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
	<c:choose>
	  
		<c:when test="${indentedStock eq 'Y' && lowStock eq 'Y'}">
			<th colspan="8" class="tbheader">Indent Report (2019-2020) Based on Indented Stock (Low Stock Alert) - ${dispname}</th>
		</c:when>
		
		 <c:otherwise>
		 <th colspan="8" class="tbheader">Indent Report (2019-2020) - ${dispname}</th>
		</c:otherwise>
	</c:choose>	
	</tr>
	<tr>
	<%-- <td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Wellness Center Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						 <option value="-1">ALL WCs</option>
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td> --%>
		<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Type</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="drugTypeID" styleId="drugTypeID" style="width:12em;" title="Select Drug Type"  onchange="fn_getDrugList(this.value)"  >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/> 
</html:select>
</td>

<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Drug Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:12em;" title="Select Drug Name"    >
		<%-- <html:option value="-1">Select</html:option> --%>
		<%-- <logic:notEmpty name="dispDrugsList">
					<html:options property="ID" collection="dispDrugsList" labelProperty="VALUE"/>
					</logic:notEmpty> --%>
</html:select>
 
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

<div  style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<p><b>Generated On Date:</b>${updDate} </p>
<br>
<!-- <span><b>Download as: &nbsp;&nbsp;</b></span>
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv()"/>
<a href="javascript:fn_print('miscActTbl','','Indent Report');" title="Print"><span style="padding:2%" class="glyphicon glyphicon-print"></span></a>  		     -->
</div>
<table   id="miscActTbl"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 90%;">
<tr>
<th class="tbheader1 border" id = "sno">S.NO<input type="checkbox" id="select_all"/><!-- <input class="checkAll" type="checkbox" onclick="checkAll(this)"> --></th>
<th class="tbheader1 border" >ITEM ID</th>
<th class="tbheader1 border" >WELLNESS CENTER</th>
<th class="tbheader1 border" >DRUG TYPE</th>
<th class="tbheader1 border" >DRUG NAME</th>
<c:choose>
<c:when test="${indentedStock eq 'Y'}">
	<th class="tbheader1 border" >IDEAL STOCK TO BE KEPT</th>
	<th class="tbheader1 border" >SUM OF LAST 3 MONTHS CONSUMPTION</th>		
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
<th class="tbheader1 border" >REQUIRED STOCK FOR NEXT 3 MONTHS</th>
<th class="tbheader1 border" >AVAILABLE STOCK</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED</th>
<th class="tbheader1 border" >RATE CONTRACT PRICE</th>
<th class="tbheader1 border" >STOCK TO BE PROCURED VALUE</th>
</c:otherwise>
</c:choose>
<th class="tbheader1 border" >RATE CONTRACT YEAR</th>
</tr>

<logic:iterate name="patientForm" property="indentList" id="labelBean" indexId="cnt">
<input type="hidden" id="drugCode${cnt+1}" value="<bean:write name="labelBean" property="DRUG_ID"/>" > 
<%-- <input type="hidden" id="drugCode${cnt+1}" value="<bean:write name="labelBean" property="DRUGCODE"/>" > --%>
<tr>
<td align="left" class="tbcellBorder" ><input class="tbcellBorder" type="checkbox" name="chb" id="chb${cnt+1}" onclick="fn_enable('${cnt+1}');">${startIndex+cnt+1} </td>

<logic:notEmpty name="labelBean" property="ITEM_ID">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="ITEM_ID"/></td>
<input type="hidden" id="itemId${cnt+1}" name="itemId" value="<bean:write name="labelBean" property="ITEM_ID"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="ITEM_ID">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 
 
<logic:notEmpty name="labelBean" property="DISP_NAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISP_NAME"/></td>
<input type="hidden" id="dispname${cnt+1}" name="dispname" value="<bean:write name="labelBean" property="DISP_NAME"/>" >
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
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="AVL"/></td>
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
	<input type="hidden" id="natstkAvl${cnt+1}" value="<bean:write name="labelBean" property="IND_NOTRECV"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="NAT_STOCK">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	   <logic:notEmpty name="labelBean" property="NAT_DEF">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="NAT_DEF"/></td>
	<input type="hidden" id="natDef${cnt+1}" value="<bean:write name="labelBean" property="IND_NOTRECV"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="NAT_DEF">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	  <logic:notEmpty name="labelBean" property="NAT_DEF">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="NAT_DEF"/></td>
	<input type="hidden" id="indCnt${cnt+1}" value="<bean:write name="labelBean" property="NAT_DEF"/>" >
	</logic:notEmpty>
	<logic:empty name="labelBean" property="NAT_DEF">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	  <logic:notEmpty name="labelBean" property="PRICE">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="PRICE"/></td>
	</logic:notEmpty>
	<logic:empty name="labelBean" property="PRICE">
	 <td align="left" class="tbcellBorder" >-NA-</td>
	 </logic:empty>
	 
	 <logic:notEmpty name="labelBean" property="IND_VAL">
	<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="IND_VAL"/></td>
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
</table>
</logic:greaterThan>

</logic:present>
<logic:empty name="patientForm" property="indentList">
 
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
	<td colspan="2"  align="center">
		<input class="but" type="button" value="Submit" onclick="javascript:fn_submit()" /></td>	
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
</html:form>


</body>
</html>