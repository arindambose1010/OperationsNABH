<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%> 
   <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employees Health Scheme</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/<%=themeColour%>/theme.css" rel="stylesheet" type="text/css" media="screen">

<script src="js/jquery-1.9.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<%@ include file="/common/includeCalendar.jsp"%> 
<link href="css/select2.min.css" rel="stylesheet">
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">

<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootbox.min.js"></script>

<script src="js/select2.min.js"></script>
<link rel="stylesheet" href="css/jquery.ui.core.css">
<link rel="stylesheet" href="css/jquery.ui.datepicker.css">
<link rel="stylesheet" href="css/jquery.ui.theme.css">
<link rel="stylesheet" href="css/jquery-ui.css">
	<script src="js/jquery.ui.core.js"></script>
	<script src="js/jquery.ui.widget.js"></script>
	<script src="js/jquery.ui.datepicker.js"></script>
	<script src="js/DateTimePicker.js"></script>
	<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>
<style>
	

.ui-menu .ui-menu-item {
        margin:0;
        padding: 0;
        width: 200px;
        list-style-type: none;
}
</style>	
 <script>
 
 var loadedDrugList=new Array();
 
 var date = new Date();
 var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

  $(function() {
		
		
		var status ='${status}';
		var type='${type}';
		if(status != null && status == 'Yes' && (type != null && type != 'WCtoWC' && type != '-1')){
			alert("Drugs have been transferred successfully");
			 //document.getElementById("drugType").value="-1";
			 $("#searchType").select2("val","-1");
			 $("#drugType").select2("val","-1");
			 $("#dispDrugID").select2("val","-1");
			 //document.getElementById("dispDrugID").value="-1";
			 $("#dispShow").hide();
			}
		else if((status != null && status == 'Yes' && (type != null && type == 'WCtoWC' ))){
			alert("Drugs transfer initiated successfully");
			 //document.getElementById("drugType").value="-1";
			 $("#searchType").select2("val","-1");
			 $("#drugType").select2("val","-1");
			 $("#dispDrugID").select2("val","-1");
			 $("#dispname").select2("val","-1");
			 //document.getElementById("dispDrugID").value="-1";
			 $("#dispShow").hide();
		}
  });
  
  
  function fn_saveInventoryDetails()
  {	
	  var drugList="";
	  if(document.getElementById("drugType").value=="-1")
		 {
		 alert("Please select Drug Type from the dropdown");
		 document.getElementById("drugType").focus();

		 return false;
		 }
		 if(document.getElementById("dispDrugID").value=="-1")
			 {
			 alert("Please select Drug Name from the dropdown");
			 document.getElementById("dispDrugID").focus();
			 return false;
			 }
		 var transferType = document.getElementById("searchType").value;
		 if(transferType == 'WCtoWC'){
			   if(document.getElementById("dispname").value =="-1"){
				   alert("Please Select Wellness Center");
				   return false;
			   }
		   }
		 var x=document.getElementById("dispDrugID").selectedIndex;
		 var drugName=document.getElementById("dispDrugID").options[x].text;
		 var table = document.getElementById("drugInfo"); 
		// var totalRows = document.getElementById("someTableID").rows.length;
		 
			 var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;
			 var flag=0;
			 for(var i=0;i<rows-1;i++)
				 {
				 var id="chb"+i;
				 if(document.getElementById(id).checked===true)
				 {  var rowId = i+1;
					 var manfctr = table.rows[rowId].cells[1].innerHTML;
					 var dstrbtr = table.rows[rowId].cells[2].innerHTML;
					 var price   = table.rows[rowId].cells[3].innerHTML;
					 var batchNo = table.rows[rowId].cells[4].innerHTML;
					 var expiryDt = table.rows[rowId].cells[5].innerHTML;
					 var avblQuant   = table.rows[rowId].cells[6].innerHTML;
					 var drugCode=document.getElementById(id).value;
					 var quanId="quan"+i;
					 
					 var quantity=document.getElementById(quanId).value;
					 
					/*  var batchId="batch"+i;
					 var batchNo=document.getElementById(batchId).value; */
					 if(avblQuant=="0"){
						 alert("Available Quantity is Zero .Drug cannot be selected");
						 return false;
					 }
					 if(parseInt(quantity) > parseInt(avblQuant)){
						 alert("Quantity is greater than Available Quantity");
						 return false;
					 }
					 if(quantity===null || ""===quantity )
						 {
						 alert("Please Enter Quantity for Selected Drug.");
						 return false;
						 }
					 if(quantity!=null && ""!=quantity)
						 {
						 drugList=drugList+drugCode+"~"+quantity+"~"+manfctr+"~"+dstrbtr+"~"+price+"~"+batchNo+"@";
						 flag=1;
						 }
					 
				 }
				 
			
			 }
			 if(flag===0)
			 {
			 alert("Please select altleast one Check box to Submit");
			 return false;
			 }
		 
		 var x=document.getElementById("dispDrugID").selectedIndex;
		 var drugName=document.getElementById("dispDrugID").options[x].text;
		
		 if(confirm("Do you want to Submit?"))
		 {
		     $(':input[type="button"]').prop('disabled', true);
		     fn_loadImage();
		  var url="./patientDetailsNew.do?actionFlag=saveTransferDrugDetails&drugName="+drugName+"&drugList="+drugList;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
		 }
		 //jq(':input[type="button"]').prop('disabled', false);
  }
 
	function fn_getDrugList()
	 {
		var xmlhttp;
	    var url;
	    var drugType = document.getElementById("drugType").value;
	    var transferType=document.getElementById("searchType").value;
	    if(transferType=='-1'){
	    	alert("Please select Transfer type");
	    	return false;
	    }
	    if(drugType=='-1')
		   {  	   
	    	   document.forms[0].dispDrugID.value="-1";
	       
		   return false;
		   }
	   else
		   {
		   document.getElementById("dispDrugID").value="-1";
		   $("#dispDrugID").select2().val='-1';
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
		   if(transferType != null && transferType != '-1' && transferType =='DtoWC'){
		  		 url = "patientDetailsNew.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugType+"&transferType="+transferType;    
  
		   }else{
	  		 url = "patientDetails.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugType;  
		   }
		   fn_loadImage();
	  		 xmlhttp.onreadystatechange=function()
	  		 {
	      		if(xmlhttp.readyState==4)
	      		{
	      			fn_removeLoadingImage();
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
	          		{        var result= resultArray;
	          		if(result==="")
	          			{
	          			alert("No Drug exists for the drug Type:"+$("#drugType option:selected").text()+". Please Enter new Drug.");
	          			 //document.getElementById("otherDrugs").style.display="block";
	          			document.forms[0].dispDrugID.options.length=0;
	          			document.forms[0].dispDrugID.options[0] =new Option("---Select---","-1");
	          			
	          			}
	          		else
	          			{
	          			var drugsList = resultArray.split(","); 
	          			var x=0;
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
                            		loadedDrugList[i]=val1;
                            		x=i;
                        		}
                       
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
	

	
	 function fn_otherDrugs()
	 {
		 if(document.getElementById("dispDrugID").value=="-1")
			 {
			 alert("Please select Drug Name from the dropdown");
			 return false;
			 }
		 else
			 {
			    document.getElementById("drugInfo").style.display="block";
				 fn_getDrugDetails();
				 
				 }
			 
	
	 }
	 
	 function fn_getDrugDetails()
		{
		var xmlhttp;
	    var url;
	    if(document.getElementById("searchType").value=="-1")
	  	 {
	  	 alert("Please select Drug  Transfer Type from the dropdown");
	  	 return false;
	  	 }
	    if(document.getElementById("drugType").value=="-1" || document.getElementById("dispDrugID").value=="-1")
	    	{
	    	  if(document.getElementById("drugType").value=="-1")
	      	{
	      	alert("Please select Drug Type from the dropdown.");
	      	return false;
	      	}
		      if(document.getElementById("dispDrugID").value=="-1")
		  	 {
		  	 alert("Please select Drug Name from the dropdown");
		  	 return false;
		  	 }
	    	}
	  
	   else
		   {
		   document.getElementById("drugInfo").style.display="block";
		   var drugType=document.getElementById("drugType").value;
		   var drugId=document.getElementById("dispDrugID").value;
		   var transferType=document.getElementById("searchType").value;
		   
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
		  
		  	  url = "patientDetailsNew.do?actionFlag=getDrugDetailsAjax&callType=Ajax&drugType="+drugType+"&drugId="+drugId+"&transferType="+transferType; 

		  	fn_loadImage();
	  		 xmlhttp.onreadystatechange=function()
	  		 {
	      		if(xmlhttp.readyState==4)
	      		{
	      			fn_removeLoadingImage();
	   	   		var resultArray=xmlhttp.responseText;
	   	 	    resultArray = resultArray.replace("[","");
	    	    resultArray = resultArray.replace("@]","");            
	    	    resultArray = resultArray.replace("*","");
	    	    resultArray = resultArray.replace("]","");
	   	   		
	          		if(resultArray.trim()=="SessionExpired*")
	          		{
	          			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	          		}
	          		else
	          		{ 
	          			 var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;
	    				 if(rows>1)
	    					 {
	    					 for(var i=0;i<rows-1;i++)
	    					 {
	    						 document.getElementById("drugInfo").deleteRow(-1);
	    					 }
	    					 }
	    				 //document.getElementById("addMnD").style.display="none";
	          			//document.getElementById("addNew").style.display="block";
	          			if(resultArray != ""){
	          			document.getElementById("Submit").style.display="block";
	          			var drugsList = resultArray.split(","); 
               			if(drugsList.length>0)
               			{
               				for(var i = 0; i<drugsList.length;i++)
                   			{	
                        		var arr=drugsList[i].split("~"); 
                        		var table=document.getElementById("drugInfo");
                                
                       			if( arr[0]!=null && arr[0] !="")
                        		{
                            		
                            		var drugCode = arr[0].replace(/^\s+|\s+$/g,"");
                            		var mftr = arr[1].replace(/^\s+|\s+$/g,"");
                            		var dstr=arr[2].replace(/^\s+|\s+$/g,"");
                            		var price=arr[3].replace(/^\s+|\s+$/g,"");
                            		var quantity=arr[4].replace(/^\s+|\s+$/g,"");
                            		var expiryDt=arr[5].replace(/^\s+|\s+$/g,"");
                            		var batchNo=arr[6].replace(/^\s+|\s+$/g,"");
                            		              
                            		var newRow=table.insertRow(-1);
                            		var newcell=newRow.insertCell(0);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="checkbox" id="chb'+i+'" value="'+drugCode+'" onclick="fn_enable('+i+');"></input></td>';
                            		var newcell=newRow.insertCell(1);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+mftr+'</td>';
                            		var newcell=newRow.insertCell(2);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+dstr+'</td>';
                            		var newcell=newRow.insertCell(3);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+price+'</td>';
                            		var newcell=newRow.insertCell(4);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+batchNo+'</td>';
                            		var newcell=newRow.insertCell(5);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+expiryDt+'</td>';
                            		var newcell=newRow.insertCell(6);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+quantity+'</td>';
                            		var newcell=newRow.insertCell(7);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="text" id="quan'+i+'" onkeyup="fn_checkDrugQuantity(this);" style="width:70px" maxlength="6" disabled/></td>';
                            		/* var newcell=newRow.insertCell(6);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="text" id="batch'+i+'" onchange="check_batchNo(this.id);"/></td>';
                           */
                        		}
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
	 
	 
	 function fn_enable(num)
	 {
		 var x="quan"+num;
		 var y="chb"+num;
		 
		 if(document.getElementById(y).checked===true)
			 {
			 document.getElementById(x).disabled = false;
			 }
			
		 else if(document.getElementById(y).checked===false)
			 {
			 document.getElementById(x).disabled = true;
			 }
			 
	 }
	 

	  function fn_checkDrugQuantity(quantity)
	  {
		  var drugQuantity=quantity.value;
		  if(drugQuantity!=null && drugQuantity!="")
			 {
			 var regAlphaNum=/^[0-9]+$/;
			 if(drugQuantity.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only Numbers are allowed in Quantity.(No Decimal Points)");
				 var qID=quantity.id;
				 document.getElementById(qID).value="";
				 document.getElementById(qID).focus();
				 return false;
				 }
			 quantity.value=drugQuantity.replace(/\s/g, '');;
			 }
		  
	  }
	  function fn_showTransfer(){
		  var selection= "";
		  selection= document.getElementById("searchType").value;
		  if(selection != '-1' && selection != ""){
			  document.getElementById("dispShow").style.display=""; 
			  $("#dispname").select2("val","-1"); 
			  if(document.getElementById("drugType") != null && document.getElementById("drugType").value !='-1'){
				 // $("#dispDrugID").val('-1');
				 // $("#dispDrugID").select2("val","-1");
				  
				  $("#drugType").select2("val","-1"); 
				$("#dispname").select2("val","-1");  
				
				 $('#Submit').hide();
				 document.getElementById("Submit").style.display="none"; 
			    document.getElementById("drugInfo").style.display="none"; 
			   //$("drugType").val('-1');
				 // $('#drugType').val(null).trigger('change');
			  }
			  /* if(document.getElementById("dispDrugID") != null && document.getElementById("dispDrugID").value !='-1'){
			    
				  $("#dispDrugID").val('-1');
				 // $('#dispDrugID').val(null).trigger('change');
			    } */
			  if(selection == 'WCtoWC'){
				  
				  $('.dispNameShow').show();
			  }else{
				  $('.dispNameShow').hide();
				 
			  }
		  }
		  else{
			  document.getElementById("drugType").value="-1"; 
			  document.getElementById("dispDrugID").value="-1"; 
			  document.getElementById("dispShow").style.display="none";
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
	
	</script>
</head>
<body onload="javascript:fn_removeLoadingImage();">
<br>
<form action="/patientDetails.do" method="post" name="patientForm" >
<!-- Progress Bar -->
<div id="processImagetable" style="top:50%;position:absolute;z-index:60;height:100%">
<table border="0" align="center" width="100%" style="height:400" >
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
<table  id="drugForm" width="100%" >
<tr><th class="tbheader" colspan="2">DRUG TRANSFER FORM</th></tr>
<tr>
<td  width="53%" class="labelheading1 tbcellCss"><b>Transfer Drugs from</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="53%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="searchType" styleId="searchType" style="width:17em;" title="Select Transfer Type"  onchange="fn_showTransfer();"  >
<html:option value="-1">--Select---</html:option>
<!--<html:option value="WCtoD">Wellness Center to Dispensery</html:option> -->
<html:option value="WCtoWC">Wellness Center to Wellness Center</html:option>
<!--<html:option value="DtoWC">Dispensary to Wellness Center</html:option> -->

</html:select>
</tr>
<tr id="dispShow" style="display:none" align="center">
<td colspan="7">
<table>
<tr>
<td  width="53%" class="labelheading1 tbcellCss"><b>Drug Type</b> <font color="red">*</font></td>
<td  width="50%" class="labelheading1 tbcellCss"><b>Drug Name</b> <font color="red">*</font></td>
<td  width="50%" class="labelheading1 tbcellCss dispNameShow" style="display:none"><b>To Wellness center</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="53%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="drugType" styleId="drugType" style="width:17em;" title="Select Drug Type"  onchange="fn_getDrugList()"  >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>
<td width="50%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:17em;" title="Select Drug Name"  onchange="fn_getDrugDetails();" >
		<html:option value="-1">Select</html:option>
</html:select>

</td>
<td width="50%" valign="top" class="tbcellBorder dispNameShow" style="display:none">
<html:select name="patientForm" property="dispname" styleId="dispname" style="width:17em;" title="Select Wellness Center">
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispList" labelProperty="VALUE"/>
</html:select>

</td>
</tr>
<tr>
<td    colspan='3'>
<table width="100%" id="drugInfo" style="display:none;margin:0 auto">
<tr>
<th width="10%" class="labelheading1 tbcellCss"></th>
<th width="15%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b></th>
<th width="15%" class="labelheading1 tbcellCss"><b>Distributer Name</b></th>
<th width="15%" class="labelheading1 tbcellCss"><b>Drug price</b></th>
<th width="10%" class="labelheading1 tbcellCss"><b>Batch No</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Expiry Date</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Available Quantity</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Quantity</b></th>
 

</tr>
</table>
</td></tr>


<tr><td colspan='3' align="center">
<button type="button" class="btn btn-primary" id="Submit" onclick="javascript:fn_saveInventoryDetails()"  title="Click Here To Submit"> Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</td></tr>
</table>
</td>
</tr>
</table>

<script>

$("#drugType").select2(); 
$("#dispDrugID").select2();
$("#dispname").select2();
</script>

</form>
</body>
</html>