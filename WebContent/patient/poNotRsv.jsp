<%@ page contentType="text/html;charset=ISO-8859-1" language="java"
    import="java.util.ResourceBundle, java.util.Locale, java.util.ArrayList, java.util.Hashtable,
                  java.util.StringTokenizer, java.util.Date, java.text.SimpleDateFormat, java.util.Calendar,
                  java.text.DateFormat, java.io.File, java.io.FileOutputStream, java.util.List,com.ahct.common.vo.LabelBean,
                  java.util.StringTokenizer"
    isErrorPage="false"%>
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
    .tbheader1  {
background-color: #20996c;
color: black;
text-align: center;
height: 6%;
}
.tbheader  {
background:#20996c;
repeat-x; 
color: #fff;
text-align: center;
}
.but  {
background-color: #20AFA7 !important;
color: #fff !important;

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
	
	//fn_getDrugList($('#drugTypeID').val());
		});	
				
	
	</script>


<script type="text/javascript">
function generate(val)
{
	
		document.forms[0].action="patientDetailsNew.do?actionFlag=poNotRsv";
    	document.forms[0].submit();
	
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
function cancel(arg,poNo,drugId)
{
	
		document.forms[0].action="patientDetailsNew.do?actionFlag=poNotRsvCacl&poNO="+poNo+"&drugId="+drugId;
    	document.forms[0].submit();
	
}
function fn_cancel()
{
		 var statList="";
		   
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
					if(document.getElementById("drugId"+j).value!=null&&document.getElementById("drugId"+j).value!="")
					 	var drugId =document.getElementById("drugId"+j).value;
					 else
						var drugId ="NA";
					if(document.getElementById("poNo"+j).value!=null&&document.getElementById("poNo"+j).value!="")
					 	var poNo =document.getElementById("poNo"+j).value;
					 else
						var poNo ="NA";
					 	
						flag+=1;
						 if(flag>0)
							 {
							
							 statList=statList+poNo+"~"+drugId+"@";
							
							}
						
					 }

					 }
				 formData.append('statList', statList); 
				
 				 if(flag===0)
 				 {
 				 alert("Please select altleast one Check box to Cancel");
				 return false;
 				 }
			 
 				if(confirm("Do you want to Cancel?"))
 	 			 {
 	 			     $(':input[type="button"]').prop('disabled', true);
 	 			     fn_loadImage();
 	 			 /*  var url="./patientDetailsNew.do?actionFlag=submitLowStockList&drugList="+drugList;
 	 				document.forms[0].action=url;
 	 				document.forms[0].method="post";
 	 				document.forms[0].submit(); */
 	 			    $.ajax({
 					        url: 'patientDetailsNew.do?actionFlag=poNotRsvCacl',
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
 											parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=poNotRsv';
 										} else
 											{
 											alert("Cancelled");
 											parent.document.getElementById("middleFrame").src='patientDetailsNew.do?actionFlag=poNotRsv';
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
function closeModal(arg,frameId)
{
	document.getElementById(frameId).src="";
	$("#"+arg).modal('hide');
}
function fn_loadImage()
{
document.getElementById('processImagetable').style.display="";

}
function fn_removeLoadingImage()  
{
	 document.getElementById('processImagetable').style.display="none"; 	 
}
function poRpt(arg,poNo)
{
	fn_loadImage();
	var url = 'patientDetailsNew.do';
    var params={
			'actionFlag':'poNoOnclick',
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
						content=content+"<td style='cursor:pointer;text-align:center' data-title='POID'>"+result[j].POID+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='PO DATE'>"+result[j].PODATE+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DRUG NAME'>"+result[j].DRUGNAME+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='DRUG TYPE'>"+result[j].DRUGTYPE+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='AVALABLE STOCK'>"+result[j].AVL+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='PO QTY'>"+result[j].POQTY+"</td>"; 
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='RECEVIED STOCK'>"+result[j].RECQTY+"</td>";
						content=content+"<td style='text-align:center' class='bothSideBorder2Px' data-title='PENDING STOCK'>"+result[j].PENIND+"</td>";
					

						
						content=content+"</tr>"; 
					document.getElementById("tbodyC1").innerHTML=content; 
											
				} 
		 	$('html, body').animate({scrollTop: 0}, 500, 'linear');	
		 	$("#phcDataDiv").hide();
		 	$("#phcBackBtn").hide();
		 	$("#phcHeading").hide();
			$("#phcCountDiv").show();
			$('#poNoDtls').modal('show'); 
			fn_removeLoadingImage();
 		 	
        }
        
    }); 
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
	<c:choose>
	    <c:when test="${yearFlag eq 'Y'}">
		<th colspan="8" class="tbheader">Indent Report (From Jan 2021)</th>
		</c:when>
		<c:when test="${indentedStock eq 'Y' && lowStock eq 'Y'}">
			<th colspan="8" class="tbheader">Drug Indent Approvals(Wellness Center)</th>
		</c:when>
		<c:when test="${indentedStock eq 'Y' && lowStock ne 'Y'}">
			<th colspan="8" class="tbheader">Indent Report (2019-2020) Based on Indented Stock </th>
		</c:when>
		 <c:otherwise>
		 <th colspan="11" class="tbheader">PO ORDER NOT RECEIVED WITHIN 45 DAYS</th>
		</c:otherwise>
	</c:choose>	
	</tr>
	<tr>
	<td colspan="1" class="labelheading1 tbcellCss" align="left" style="width:25%"><b>	Wellness Center Name</b>&nbsp;</td>
	<td align="left"  class="tbcellBorder" style="width:16%">

<html:select  property="dispname" name="patientForm" styleId="dispname"   title="Select Wellness Center">
						 <option value="-1">ALL WCs</option>
					 <html:options collection="wellnesslist"  property="DISPID" labelProperty="DISPNAME"></html:options>
						
						</html:select> 

	</td>

</tr> 

	
</table>
<tr>
<div class='row' align="center">
		<input class="tbheader1" type="button"  value="Generate" onclick="javascript:generate()"/>
</div>
</tr>
<logic:present name="patientForm" property="indentedPOList">
<bean:size id="size" name="patientForm" property="indentedPOList"/>

<logic:greaterThan value="0" name="size"> 

<%-- <div  style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<p><b>Generated On Date:</b>${updDate} </p>
<br>
<span><b>Download as: &nbsp;&nbsp;</b></span>
<img id="csvImg" src="images/csv3.png" onmouseover="this.src='images/csv4.png'" onmouseout="this.src='images/csv3.png'" onclick="javascript:newexportToCsv()"/>
</div> 
<div style="clear:both;float:right;margin-right:2%; padding-bottom: 1%;">
<a href="javascript:fn_print('miscActTbl','','Indent Report');" title="Print"><span style="padding:2%" class="glyphicon glyphicon-print"></span></a>  		    
</div> --%>
<table class="table"  id="miscActTbl"  cellSpacing="1" cellPadding="1" style="text-align:center;margin:0 auto;width: 100%;">
<tr>
<th class="tbheader1 border" id = "sno">S.NO<!-- <input type="checkbox" id="select_all"/><input class="checkAll" type="checkbox" onclick="checkAll(this)"></th> --></th>
 <th class="tbheader1 border" >PO NO </th> 
<!-- <th class="tbheader1 border" >WELLNESS CENTER</th> -->
 <th class="tbheader1 border" >PO DATE</th> 
<th class="tbheader1 border" >DISTRIBUTOR NAME</th>
<th class="tbheader1 border" >MANUFACTURER NAME</th>
<th class="tbheader1 border" >WELLNESS CENTER</th>
</tr>

<logic:iterate name="patientForm" property="indentedPOList" id="labelBean" indexId="cnt">
<input type="hidden" id="status${cnt+1}" value="<bean:write name="labelBean" property="INTERNALSTATUS"/>" >
<input type="hidden" id="dispId${cnt+1}" value= "<bean:write name="labelBean" property="DISPID"/>">
<input type="hidden" id="drugId${cnt+1}" value= "<bean:write name="labelBean" property="DRUGCODE"/>">

<tr>
<td align="left" class="tbcellBorder" ><input class="tbcellBorder" type="checkbox" name="chb" id="chb${cnt+1}" onclick="fn_enable('${cnt+1}');"> ${startIndex+cnt+1} </td>

<logic:notEmpty name="labelBean" property="POID">
<td align="left" class="bothSideBorder2Px blueFont tbcellBorder" onclick="poRpt(this,'<bean:write  name="labelBean" property="POID"/>');"><bean:write name="labelBean" property="POID"/></td>
<input type="hidden" id="poNo${cnt+1}"  name="poNo${cnt+1}" value="<bean:write name="labelBean" property="POID"/>" >
</logic:notEmpty>
<logic:empty name="labelBean" property="POID">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 

<logic:notEmpty name="labelBean" property="PODATE">
<td align="left" class="tbcellBorder" > <bean:write name="labelBean" property="PODATE"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="PODATE">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty> 



<logic:notEmpty name="labelBean" property="DSTRBTRNAME">
<td align="left" class="tbcellBorder" > <bean:write name="labelBean" property="DSTRBTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DSTRBTRNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>
 
 <logic:notEmpty name="labelBean" property="MNFCTRNAME">
<td align="left" class="tbcellBorder" > <bean:write name="labelBean" property="MNFCTRNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="MNFCTRNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>

<logic:notEmpty name="labelBean" property="DISPNAME">
<td align="left" class="tbcellBorder" ><bean:write name="labelBean" property="DISPNAME"/></td>
</logic:notEmpty>
<logic:empty name="labelBean" property="DISPNAME">
 <td align="left" class="tbcellBorder" >-NA-</td>
 </logic:empty>  

</tr>
</logic:iterate> 

</table>
<div class="row" align="center"> 
<input class="tbheader1" type="button" value="Cancel" onclick="javascript:fn_cancel()" />	
</div>

</logic:greaterThan>
<logic:lessThan value="0" name="size">
<div class="error-desk" align="center">
         
            <br> <h4>No Records Found</h4> <br>
            </div>
            </logic:lessThan>
</logic:present>


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
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;text-align:center"><b>S.No</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>PO NO</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center""><b>PO DATE</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;text-align:center""><b>DRUG NAME</b></th>
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center""><b>DRUG TYPE</b></th> 
							<th class="tableHeader bothSideBorder2Px" style="font-size: 14px !important;text-align:center"><b>AVALABLE STOCK</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;text-align:center""><b>PO QTY</b></th> 
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;text-align:center""><b>RECEIVED STOCK</b></th> 
							<th class="tableHeader bothSideBorder2Px"  style="font-size: 14px !important;text-align:center""><b>PENDING STOCK</b></th> 
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