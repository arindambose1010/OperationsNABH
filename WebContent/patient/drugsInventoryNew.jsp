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
		$( "#drugexpiryDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
          buttonImage: "images/calend.gif", 
          buttonText: "Calendar",
          buttonImageOnly: true ,
            minDate: 0,
			maxDate:  "+5Y",			
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		
		$( "#expiryDt" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
          buttonImage: "images/calend.gif", 
          buttonText: "Calendar",
          buttonImageOnly: true ,
            minDate: 0,
			maxDate:  "+5Y",			
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#invoiceDate" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
          buttonImage: "images/calend.gif", 
          buttonText: "Calendar",
          buttonImageOnly: true ,
          minDate: "-5Y",
			maxDate:  0,			
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
		$( "#invoiceDateMn" ).datepicker({
			defaultDate: "+1w",
			changeMonth: true,
			changeYear: true,
			showOn: "both", 
          buttonImage: "images/calend.gif", 
          buttonText: "Calendar",
          buttonImageOnly: true ,
            minDate: "-5Y",
			maxDate:  0,			
			onClose: function( selectedDate ) {
				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
			}
		});
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
		 if(document.getElementById("dispDrugID").value!="-1" && document.getElementById("dispDrugID").value!="Others")
		 {
		 alert("Please select Others as Drug Name from the dropdown to add New Drug");
		 document.getElementById("dispDrugID").value="-1";
		 $("#dispDrugID").select2().val='-1';
		 document.getElementById("dispDrugID").focus();
		 return false;
		 }
		 var x=document.getElementById("dispDrugID").selectedIndex;
		 var drugName=document.getElementById("dispDrugID").options[x].text;
		 var temp="Others";
		 if(drugName.toUpperCase() === temp.toUpperCase())
			 {
			 if( document.getElementById("dispDrugName").value==null ||  document.getElementById("dispDrugName").value=='')
			 {
			 alert("Please enter Drug Name");
			 document.getElementById("dispDrugName").focus();
			 return false;
			 }
			 if( document.getElementById("manufacturerName").value==null ||  document.getElementById("manufacturerName").value=='')
			 {
			 alert("Please enter Manufacturer Name");
			 document.getElementById("manufacturerName").focus();
			 return false;
			 }
			 if( document.getElementById("distributerName").value==null ||  document.getElementById("distributerName").value=='')
			 {
			 alert("Please enter Distributer Name");
			 document.getElementById("distributerName").focus();
			 return false;
			 }
			 
		 
			 }
		 else
			 {
			 var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;
			 var flag=0;
			 for(var i=0;i<rows-1;i++)
				 {
				 var id="chb"+i;
				 if(document.getElementById(id).checked===true)
				 {
					 var drugCode=document.getElementById(id).value;
					 var quanId="quan"+i;
					 var invoiceId="invoice"+i;
					 var invoiceDt="invoiceDate"+i;
					 var quantity=document.getElementById(quanId).value;
					 var invoiceNumber=document.getElementById(invoiceId).value;
					 var invoiceDat=document.getElementById(invoiceDt).value;
					/*  var batchId="batch"+i;
					 var batchNo=document.getElementById(batchId).value; */
					 if(quantity===null || ""===quantity )
						 {
						 alert("Please Enter Quantity for Selected Drug.");
						 return false;
						 }
					 if(invoiceNumber===null || ""===invoiceNumber )
					 {
					 alert("Please Enter Invoice Number for Selected Drug.");
					 return false;
					 }
					 if(invoiceDat===null || ""===invoiceDat )
					 {
					 alert("Please Enter Invoice Date for Selected Drug.");
					 return false;
					 }
					/*  if(batchNo==null || batchNo==="")
						 {
						 alert("Please enter BatchNo for the Selected Drug");
						 return false;
						 } */
					 if(quantity!=null && ""!=quantity)
						 {
						 drugList=drugList+drugCode+"~"+quantity+"~"+invoiceNumber+"~"+invoiceDat+"@";
						 flag=1;
						 }
					 
				 }
				 }
			 if(flag===0)
				 {
				 alert("Please select altleast one Check box to Submit");
				 return false;
				 }
			 }
		 
		 var x=document.getElementById("dispDrugID").selectedIndex;
		 var drugName=document.getElementById("dispDrugID").options[x].text;
		 var dispDrugID=document.getElementById("dispDrugID");
		 if(confirm("Do you want to Submit?"))
		 {
		     $(':input[type="button"]').prop('disabled', true);

		  var url="./patientDetailsNew.do?actionFlag=saveInventoryDrugDetailsNew&drugName="+drugName+"&drugList="+drugList+"&dispDrugID="+dispDrugID;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
		 }
		 //jq(':input[type="button"]').prop('disabled', false);
  }
  
  function fn_onLoad()
  {
	 var status="${status}";
	//alert(status);
	 if(status!=null)
		 {
		 if(status=="Yes")
			 {
			 alert("Details have been added successfully to the inventory.");
			 document.getElementById("drugType").value="-1";
			 $("#drugType").select2().val='-1';
			 document.getElementById("dispDrugID").value="-1";
			 $("#dispDrugID").select2().val='-1';
			 /* document.getElementById("presentDrugQuantity").value="";
			 document.getElementById("dispDrugQuantity").value="";
			 document.getElementById("drugBatchNo").value="";
			 document.getElementById("drugexpiryDate").value=""; */
			 }
		
		 
		 }
  }
	

	function fn_checkDrug()
	{
		 if(document.getElementById("dispDrugName").value!=null)
		 {
			// var regAlphaNum=/^[0-9a-zA-Z,\/\- ]+$/;
		 var regAlphaNum=/^[0-9a-zA-Z-()/.+ ]+$/;
		 var drugName=document.getElementById("dispDrugName").value;
		 if(drugName.trim().search(regAlphaNum)==-1)
			 {
			 alert("Only alphanumerics and -,(,) are allowed in Drug Name");
			 document.getElementById("dispDrugName").value="";
			 document.getElementById("dispDrugName").focus();
			 return false;
			 } 
		 else
		 {
			
		 var drugName=document.getElementById("dispDrugName").value;
		 document.getElementById("dispDrugName").value=drugName.replace(/  +/g, ' ');
		 for(var i = 0; i<loadedDrugList.length;i++)
			{	
			 var ref=drugName.toUpperCase();
			 var temp=loadedDrugList[i].toUpperCase();
			 if(ref === temp)
				 {
				 alert("Drug Name already exists! Please enter New Drug Name.");
				 document.getElementById("dispDrugName").value="";
				 document.getElementById("dispDrugName").focus();
				 return false;
				 }
			}
     	
		 }
		 }
		
	}

	 function fn_otherDrugs()
	 {
		 if(document.getElementById("dispDrugID").value=="-1")
			 {
			 alert("Please select Drug Name from the dropdown");
			 return false;
			 }
		 else if(document.getElementById("dispDrugID").value!="-1" && document.getElementById("dispDrugID").value!="Others")
		 {
		 alert("Please select Others as Drug Name from the dropdown to add New Drug");
		 document.getElementById("dispDrugID").value="-1";
		 $("#dispDrugID").select2().val='-1';
		 return false;
		 }
		 else
			 {
			 var x=document.getElementById("dispDrugID").selectedIndex;
			 var drugName=document.getElementById("dispDrugID").options[x].text;
			 var temp="Others";
			 if(drugName.toUpperCase() === temp.toUpperCase())
				 {
				 var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;
				 if(rows>1)
					 {
					 for(var i=0;i<rows-1;i++)
					 {
						 document.getElementById("drugInfo").deleteRow(-1);
					 }
					 }	 
				 
				 document.getElementById("dispDrugName").value="";
				 document.getElementById("manufacturerName").value="";
				 document.getElementById("distributerName").value="";
				 document.getElementById("otherDrugs").style.display="block";
				 document.getElementById("drugInfo").style.display="none";

				 document.getElementById("Submit").style.display="block";
				 
				 }
			 else
				 {
				 document.getElementById("otherDrugs").style.display="none";
				// document.getElementById("drugInfo").style.display="block";

				// fn_getDrugDetails();
				 
				 }
			 }
		
	 }
	
	
	  function fn_checkMftName(name)
	  {
		  var mftName=name.value;
		  if( mftName!=null && mftName!="" )
			 {
			 var regAlphaNum=/^[0-9a-zA-Z- ]+$/;
			 if(mftName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and - are allowed in Manufacturer Name");
				 var dID=name.id;
				 document.getElementById(dID).value="";
				 document.getElementById(dID).focus();
				 return false;
				 }
			 name.value=mftName.replace(/  +/g, ' ');
			 }
	  }
	  
		function fn_getDrugList()
		 {
			var xmlhttp;
		    var url;
		    var drugType = document.getElementById("drugType").value;
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
		  		 url = "patientDetails.do?actionFlag=getDrugListAjax&callType=Ajax&drugType="+drugType;    
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
		          		{        var result= resultArray;
		          		if(result==="")
		          			{
		          			alert("No Drug exists for the drug Type:"+$("#drugType option:selected").text()+". Please Enter new Drug.");
		          			 document.getElementById("otherDrugs").style.display="block";
		          			document.forms[0].dispDrugID.options.length=0;
		          			document.forms[0].dispDrugID.options[0] =new Option("---Select---","-1");
		          			document.forms[0].dispDrugID.options[1] =new Option("Others","Others");
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
	               			document.forms[0].dispDrugID.options[x+2] =new Option("Others","Others");
		          			}
		                       		
		                   		
		        		}
		      		}
		  		 }
		  		
			xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
			   
			}
			 
		 }
	  function fn_checkDbtrName(name)
	  {
		  var dbtrName=name.value;
		  if( dbtrName!=null && dbtrName!="" )
			 {
				 var regAlphaNum=/^[0-9a-zA-Z- ]+$/;
			 if(dbtrName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and - are allowed in Distributer Name");
				 var dbID=name.id;
				 document.getElementById(dbID).value="";
				 document.getElementById(dbID).focus();
				 return false;
				 }
			 name.value=dbtrName.replace(/  +/g, ' ');
			 }
		  
	  }
	
	  $( function() {
		   var availableTags = [];
		    var list ="${mftList}".replace("[","").replace("]","").trim().split(",");
		    for(var i=0;i<list.length;i++){
		    	var temp=list[i];
		    	var arr=temp.split("-");
		    	var dummyArr = {label: arr[0], value: temp};
		    	availableTags.push(dummyArr);
		    }
		    
	    $( "#manufacturerName" ).autocomplete({
	      source: availableTags,
	      autoFocus:true,
	      open: function() {
	          $("ul.ui-menu").width( $(this).innerWidth() );
	      }
	    });
	    
	    
	    $( "#mftrName" ).autocomplete({
		      source: availableTags,
		      autoFocus:true,
		      open: function() {
		          $("ul.ui-menu").width( $(this).innerWidth() );
		      }
		    });
	    
	    
	    var distributerNames = [];
	    var list ="${dstrList}".replace("[","").replace("]","").trim().split(",");
	    for(var i=0;i<list.length;i++){
	    	var temp=list[i];
	    	var arr=temp.split("-");
	    	var dummyArr = {label: arr[0], value: temp};
	    	distributerNames.push(dummyArr);
	    }
	    
    $( "#distributerName" ).autocomplete({
      source: distributerNames,
      autoFocus:true,
      open: function() {
          $("ul.ui-menu").width( $(this).innerWidth() );
      }
    });
	    
    
    
    $( "#dstrName" ).autocomplete({
      source: distributerNames,
      autoFocus:true,
      open: function() {
          $("ul.ui-menu").width( $(this).innerWidth() );
      }
    });
	  } );
	  

	
  </script>
</head>
<body onload="fn_onLoad();">
<br>
<form action="/patientDetailsNew.do" method="post" name=patientForm>

<table  id="drugForm" width="100%" style="margin:0 auto" >
<tr><th class="tbheader" colspan="2">DRUG DETAILS FORM </th></tr>
<tr>
<td  width="53%" class="labelheading1 tbcellCss"><b>Drug Type</b> <font color="red">*</font></td>
<td  width="50%" class="labelheading1 tbcellCss"><b>Drug Name</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="53%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="drugType" styleId="drugType" style="width:17em;" title="Select Drug Type" onchange="fn_getDrugList()">
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>
<td width="50%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:17em;" title="Select Drug Name"  onchange="fn_otherDrugs();" >
		<html:option value="-1">Select</html:option>
		<!--<html:option value="Others">Others</html:option>-->
</html:select>
 <br><font size="2" color="red">Please Select 'Others' to add New Drug</font> 
</td>
</tr>

<tr>
<td    colspan="2" >
<table width="100%" id="otherDrugs" style="display:none;margin:0 auto">
<tr>
<td width="30%" class="labelheading1 tbcellCss"><b>New Drug Name</b> <font color="red">*</font></td>
<td width="30%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b> <font color="red">*</font></td>
<td width="29%" class="labelheading1 tbcellCss"><b>Distributer Name</b> <font color="red">*</font></td>
<!-- <td width="30%" class="labelheading1 tbcellCss"><b>Rate Contract Price</b> <font color="red">*</font></td> -->
</tr>
<tr>
<td width="27%"  class="tbcellBorder"  title="New Drug Name"><html:text name="patientForm" property="dispDrugName" styleId="dispDrugName" onchange="fn_checkDrug()" maxlength="75"></html:text></td>
<td width="26%" class="tbcellBorder"  title="Manufacturer Name">
<html:text name="patientForm"  property="manufacturerName" styleId="manufacturerName" maxlength="30"  onkeyup="fn_checkMftName(this)" ></html:text></td>
<td width="29%" class="tbcellBorder"  title="Distributer Name"><html:text name="patientForm" property="distributerName" styleId="distributerName" maxlength="30" onkeyup="fn_checkDbtrName(this)"></html:text></td>
<!--<td width="30%" class="tbcellBorder"  title="Drug Price"><html:text name="patientForm" property="dispDrugPrice" styleId="dispDrugPrice" maxlength="6" onkeyup="fn_checkDrugPrice(this)"></html:text></td> -->
</tr>
<tr>
<td  colspan="2" >
<font color="red">Note:Please type slowly to get existing Manufacturer/Distributer name.</font>
</td>
</tr>
</table>
</td>
</tr>
<tr>
<td    colspan="2" >
<table width="100%" id="drugInfo" style="display:none;margin:0 auto">
<tr>
<th width="10%" class="labelheading1 tbcellCss"></th>
<th width="15%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b></th>
<th width="15%" class="labelheading1 tbcellCss"><b>Distributer Name</b></th>
<th width="10%" class="labelheading1 tbcellCss"><b>Rate Contract Price</b> </th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Available Quantity</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Batch No</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Quantity</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Invoice Number</b></th> 
<th width="10%" class="labelheading1 tbcellCss"><b>Invoice Date</b></th> 

</tr>
</table>
</td></tr>
<tr><td class="tbcellBorder" colspan="2" align="center">
<button type="button" class="btn btn-primary" id="Submit" onclick="javascript:fn_saveInventoryDetails()"  title="Click Here To Submit"> Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</td></tr>
</table>

<script>

$("#drugType").select2(); 
$("#dispDrugID").select2();
</script>

</form>
</body>
</html>