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
			 
			/*  if(document.getElementById("dispDrugName").value!=null)
			 {
			 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-()+% ]+$/;
			 var drugName=document.getElementById("dispDrugName").value;
			 if(drugName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/,(,),+,% are allowed in Drug Name");
				 document.getElementById("dispDrugName").value="";
				 document.getElementById("dispDrugName").focus();
				 return false;
				 }
			 } */
			 
			
			 
			
			 if( document.getElementById("manufacturerName").value==null ||  document.getElementById("manufacturerName").value=='')
			 {
			 alert("Please enter Manufacturer Name");
			 document.getElementById("manufacturerName").focus();
			 return false;
			 }
			 
			/*  if( document.getElementById("manufacturerName").value!=null )
			 {
				 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-& ]+$/;
			 var mftName=document.getElementById("manufacturerName").value;
			 if(mftName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/,& are allowed in Manufacturer Name");
				 document.getElementById("manufacturerName").value="";
				 document.getElementById("manufacturerName").focus();
				 return false;
				 }
			 } */
			 
			 
			 if( document.getElementById("distributerName").value==null ||  document.getElementById("distributerName").value=='')
			 {
			 alert("Please enter Distributer Name");
			 document.getElementById("distributerName").focus();
			 return false;
			 }
			 
			/*  if( document.getElementById("distributerName").value!=null )
			 {
				 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-& ]+$/;
			 var dstrName=document.getElementById("distributerName").value;
			 if(dstrName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/,& are allowed in Distributer Name");
				 document.getElementById("distributerName").value="";
				 document.getElementById("distributerName").focus();
				 return false;
				 }
			 }
			  */
			 
			 if( document.getElementById("dispDrugQuantity").value==null ||  document.getElementById("dispDrugQuantity").value=='')
			 {
			 alert("Please enter Drug Quantity");
			 document.getElementById("dispDrugQuantity").focus();
			 return false;
			 }
			  
			 
			 if(document.getElementById("dispDrugQuantity").value!=null)
			 {
			 var quant=document.getElementById("dispDrugQuantity").value;
			 var regAlphaNum=/^[0-9]+$/;
			 var quan=document.getElementById("dispDrugQuantity").value;
			 if(quan.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only Numbers are allowed in Quantity");
				 document.getElementById("dispDrugQuantity").value="";
				 document.getElementById("dispDrugQuantity").focus();
				 return false;
				 }
			 }
			 
			 if( document.getElementById("invoiceNumber").value==null ||  document.getElementById("invoiceNumber").value=='')
			 { 
			 alert("Please enter Invoice Number");
			 document.getElementById("invoiceNumber").focus();
			 return false;
			 }
			 if( document.getElementById("invoiceDate").value==null ||  document.getElementById("invoiceDate").value=='')
			 { 
			 alert("Please enter Invoice Date");
			 document.getElementById("invoiceDate").focus();
			 return false;
			 }
			 if( document.getElementById("dispDrugPrice").value==null ||  document.getElementById("dispDrugPrice").value=='')
			 {
			 alert("Please enter Drug Price");
			 document.getElementById("dispDrugPrice").focus();
			 return false;
			 }
		 
		 if(document.getElementById("dispDrugPrice").value!=null)
		 {
		 var quant=document.getElementById("dispDrugPrice").value;
		 if(isNaN(quant))
			 {
			 alert("Drug Price should be a number");
			 document.getElementById("dispDrugPrice").value="";
			 document.getElementById("dispDrugPrice").focus();
			 return false;
			 }
		 }
		
		 if( document.getElementById("drugBatchNo").value==null ||  document.getElementById("drugBatchNo").value=='')
			 {
			 alert("Please enter Drug Batch No");
			 document.getElementById("drugBatchNo").focus();
			 return false;
			 }
		 /* if(document.getElementById("drugBatchNo").value!=null)
			 {
			 var regAlphaNum=/^[0-9a-zA-Z,.:\/\- ]+$/;
			 var batchNo=document.getElementById("drugBatchNo").value;
			 if(batchNo.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/ are allowed in Batch Number");
				 document.getElementById("drugBatchNo").value="";
				 document.getElementById("drugBatchNo").focus();
				 return false;
				 }
			 } */
		 if(document.getElementById("drugexpiryDate").value==null || document.getElementById("drugexpiryDate").value=='')
			 {
			 alert("Please enter Drug Expiry Date");
			 document.getElementById("drugexpiryDate").focus();
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
		
		 if(confirm("Do you want to Submit?"))
		 {
		     $(':input[type="button"]').prop('disabled', true);

		  var url="./patientDetails.do?actionFlag=saveInventoryDrugDetails&drugName="+drugName+"&drugList="+drugList;
			document.forms[0].action=url;
			document.forms[0].method="post";
			document.forms[0].submit();
		 }
		 //jq(':input[type="button"]').prop('disabled', false);
  }
  
  function fn_onLoad()
  {
	 var status="${status}";
	
	 if(status!=null)
		 {
		 if(status=="Yes")
			 {
			 alert("Details have been added successfully to the inventory.");
			 document.getElementById("drugType").value="-1";
			 $("#drugType").select2().val='-1';
			 document.getElementById("dispDrugID").value="-1";
			 /* document.getElementById("presentDrugQuantity").value="";
			 document.getElementById("dispDrugQuantity").value="";
			 document.getElementById("drugBatchNo").value="";
			 document.getElementById("drugexpiryDate").value=""; */
			 }
		
		 
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
	          			alert("No Drug exists for the drug Type:"+$("#drugType option:selected").text()+".");
	          			// document.getElementById("otherDrugs").style.display="block";
	          			document.forms[0].dispDrugID.options.length=0;
	          			document.forms[0].dispDrugID.options[0] =new Option("---Select---","-1");
	          			//document.forms[0].dispDrugID.options[1] =new Option("Others","Others");
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
               			//document.forms[0].dispDrugID.options[x+2] =new Option("Others","Others");
	          			}
	                       		
	                   		
	        		}
	      		}
	  		 }
	  		
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		   
		}
		 
	 }
	

	function fn_checkDrug()
	{
		 if(document.getElementById("dispDrugName").value!=null)
		 {
		 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-()+&% ]+$/;
		 var drugName=document.getElementById("dispDrugName").value;
		 if(drugName.trim().search(regAlphaNum)==-1)
			 {
			 alert("Only alphanumerics and -,.,:,/,(,),+,&,% are allowed in Drug Name");
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
				 document.getElementById("dispDrugPrice").value="";
				 document.getElementById("dispDrugQuantity").value="";
				 document.getElementById("drugBatchNo").value="";
				 document.getElementById("drugexpiryDate").value="";
				 document.getElementById("invoiceNumber").value="";
				 document.getElementById("invoiceDate").value="";
				 document.getElementById("otherDrugs").style.display="block";
				 document.getElementById("drugInfo").style.display="none";
				 
				 document.getElementById("addMnD").style.display="none";
				 document.getElementById("addNew").style.display="none";
				 document.getElementById("Submit").style.display="block";
				 
				 }
			 else
				 {
				 document.getElementById("otherDrugs").style.display="none";
				 document.getElementById("drugInfo").style.display="block";
				 fn_getDrugDetails();
				 
				 }
			 }
		
	 }
	 
	 function fn_getDrugDetails()
		{
		var xmlhttp;
	    var url;
	   
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
		   var drugType=document.getElementById("drugType").value;
		   var drugId=document.getElementById("dispDrugID").value;
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
	  		 url = "patientDetails.do?actionFlag=getDrugDetailsAjax&callType=Ajax&drugType="+drugType+"&drugId="+drugId;    
	  		 xmlhttp.onreadystatechange=function()
	  		 {
	      		if(xmlhttp.readyState==4)
	      		{
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
	    				 document.getElementById("addMnD").style.display="none";
	          			document.getElementById("addNew").style.display="block";
	          			document.getElementById("Submit").style.display="block";
	          			var drugsList = resultArray.split(","); 
               			if(drugsList.length>0)
               			{
               				for(var i = 0; i<drugsList.length;i++)
                   			{	
                        		var arr=drugsList[i].split("~"); 
                        		var table=document.getElementById("drugInfo");
                                
                       			if( arr[0]!=null)
                        		{
                            		
                            		var drugCode = arr[0].replace(/^\s+|\s+$/g,"");
                            		var mftr = arr[1].replace(/^\s+|\s+$/g,"");
                            		var dstr=arr[2].replace(/^\s+|\s+$/g,"");
                            		var price=arr[3].replace(/^\s+|\s+$/g,"");
                            		var quantity=arr[4].replace(/^\s+|\s+$/g,"");
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
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+quantity+'</td>';
                            		var newcell=newRow.insertCell(5);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss">'+batchNo+'</td>';
                            		var newcell=newRow.insertCell(6);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="text" id="quan'+i+'" onkeyup="fn_checkDrugQuantity(this);" style="width:70px" maxlength="6" disabled/></td>';
                            		/* var newcell=newRow.insertCell(6);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="text" id="batch'+i+'" onchange="check_batchNo(this.id);"/></td>';
                           */
                            		var newcell=newRow.insertCell(7);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="text" id="invoice'+i+'" style="width:70px" maxlength="10" disabled/></td>';
                               		var newcell=newRow.insertCell(8);
                            		newcell.innerHTML='<td class="tbcellBorder tbcellCss"><input type="text" id="invoiceDate'+i+'" style="width:70px" maxlength="10" disabled/></td>';
                            		
                            		$( "#invoiceDate"+i ).datepicker({
                            			defaultDate: "+1w",
                            			changeMonth: true,
                            			changeYear: true,
                            			showOn: "both", 
                                      buttonImage: "images/calend.gif", 
                                      buttonText: "Calendar",
                                      buttonImageOnly: true ,
                                      minDate: "-5Y",
                            			maxDate:  0,	
                            			disabled:true,
                            			onClose: function( selectedDate ) {
                            				$( "#toDate" ).datepicker( "option", "minDate", selectedDate );
                            			}
                            		});
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
		 var z="invoice"+num;
		 var w="invoiceDate"+num;
		 if(document.getElementById(y).checked===true)
			 {
			 document.getElementById(x).disabled = false;
			 document.getElementById(z).disabled = false;
			 document.getElementById(w).disabled = false;
			 document.getElementById("invoiceDate"+num).value='';
			 $( "#invoiceDate"+num ).datepicker( "option", "disabled", false );
			 }
		 else if(document.getElementById(y).checked===false)
			 {
			 document.getElementById(x).disabled = true;
			 document.getElementById(z).disabled = true;
			 document.getElementById(w).disabled = true;
			 document.getElementById("invoiceDate"+num).value='';
			 $( "#invoiceDate"+num ).datepicker( "option", "disabled", true );
			 }
			 
	 }
	 

	 function fn_addNewMnfNDist()
	 {
		 document.getElementById("addMnD").style.display="block";
		 document.getElementById("drugInfo").style.display="none";
		 document.getElementById("Submit").style.display="none";
		 document.getElementById("addNew").style.display="none";
		
		 document.getElementById("mftrName").value="-1";
		 $("#mftrName").select2().val='-1';

		 document.getElementById("dstrName").value="-1";
		 $("#dstrName").select2().val='-1';

		 document.getElementById("drugPrice").value="";
		 document.getElementById("quantity").value="";
		 document.getElementById("batchNo").value="";
		 document.getElementById("expiryDt").value="";
		 document.getElementById("invoiceNumberNew").value="";
		 document.getElementById("invoiceDateMn").value="";
		 

	 }

	 function fn_saveMnfDist()
	 {
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
		
		 if( document.getElementById("mftrName").value=="-1" )
		 {
		 alert("Please Select Manufacturer Name");
		 document.getElementById("mftrName").focus();
		 return false;
		 }
		 
		/*  if( document.getElementById("mftrName").value!=null )
			 {
			 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-& ]+$/;
			 var mftNo=document.getElementById("mftrName").value;
			 if(mftNo.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/,& are allowed in Manufacturer Name");
				 document.getElementById("mftrName").value="";
				 document.getElementById("mftrName").focus();
				 return false;
				 }
			 } */
		 
		 if( document.getElementById("dstrName").value=="-1")
		 {
		 alert("Please Select Distributer Name");
		 document.getElementById("dstrName").focus();
		 return false;
		 }
		/*  
		 if( document.getElementById("dstrName").value!=null )
		 {
			 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-& ]+$/;
		 var dstNo=document.getElementById("dstrName").value;
		 if(dstNo.trim().search(regAlphaNum)==-1)
			 {
			 alert("Only alphanumerics and -,.,:,/,& are allowed in Distributer Name");
			 document.getElementById("dstrName").value="";
			 document.getElementById("dstrName").focus();
			 return false;
			 }
		 }
		  */
		 
		 if( document.getElementById("quantity").value==null ||  document.getElementById("quantity").value=='')
		 {
		 alert("Please enter Drug Quantity");
		 document.getElementById("quantity").focus();
		 return false;
		 }
		 
		 if( document.getElementById("drugPrice").value==null ||  document.getElementById("drugPrice").value=='')
		 {
		 alert("Please enter Drug Price");
		 document.getElementById("drugPrice").focus();
		 return false;
		 }
	
	 if(document.getElementById("drugPrice").value!=null)
	 {
	 var quant=document.getElementById("drugPrice").value;
	 if(isNaN(quant))
		 {
		 alert("Drug Price should be a number");
		 document.getElementById("drugPrice").value="";
		 document.getElementById("drugPrice").focus();
		 return false;
		 }
	 }
	 if(document.getElementById("quantity").value!=null)
		 {
		 var quant=document.getElementById("quantity").value;
		 var regAlphaNum=/^[0-9]+$/;
		 var quan=document.getElementById("quantity").value;
		 if(quan.trim().search(regAlphaNum)==-1)
			 {
			 alert("Only Numbers are allowed in Quantity");
			 document.getElementById("quantity").value="";
			 document.getElementById("quantity").focus();
			 return false;
			 }
		 }
	 if( document.getElementById("batchNo").value==null ||  document.getElementById("batchNo").value=='')
		 {
		 alert("Please enter Drug Batch No");
		 document.getElementById("batchNo").focus();
		 return false;
		 }
	 
	 if( document.getElementById("invoiceNumberNew").value==null ||  document.getElementById("invoiceNumberNew").value=='')
		 {
		 alert("Please enter Invoice Number");
		 document.getElementById("invoiceNumberNew").focus();
		 return false;
		 }
	 /* if(document.getElementById("batchNo").value!=null)
		 {
		 var regAlphaNum=/^[0-9a-zA-Z,.:\/\- ]+$/;
		 var batchNo=document.getElementById("batchNo").value;
		 if(batchNo.trim().search(regAlphaNum)==-1)
			 {
			 alert("Only alphanumerics and -,.,:,/ are allowed in Batch Number");
			 document.getElementById("batchNo").value="";
			 document.getElementById("batchNo").focus();
			 return false;
			 }
		 } */
	 if(document.getElementById("expiryDt").value==null || document.getElementById("expiryDt").value=='')
		 {
		 alert("Please enter Drug Expiry Date");
		 document.getElementById("expiryDt").focus();
		 return false;
		 }
		 if(document.getElementById("invoiceDateMn").value==null || document.getElementById("invoiceDateMn").value=='')
		 {
		 alert("Please enter Invoice Date");
		 document.getElementById("invoiceDateMn").focus();
		 return false;
		 }
		 
		 if(confirm("Do you want to Submit?"))
			 {
			 var url="./patientDetails.do?actionFlag=addNewMnfNDst";
				document.forms[0].action=url;
				document.forms[0].method="post";
				document.forms[0].submit();
			 
			 }
		 $(':input[type="button"]').prop('disabled', true);
		document.getElementById('processImage').style.display="";
		 
			
	 }
	 
	 function fn_checkMftName1(name){
		 var mftName=name.value;
		 
			var distName=$("#manufacturerName").find('option:selected').text();
			alert(distName);
		// document.getElementById("manufacturerName").value
		 
	 } 
	  function fn_checkMftName(name)
	  {
		  var mftName=name.value;
		  if( mftName!=null && mftName!="" )
			 {
			 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-& ]+$/;
			 if(mftName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/,& are allowed in Manufacturer Name");
				 var dID=name.id;
				 document.getElementById(dID).value="";
				 document.getElementById(dID).focus();
				 return false;
				 }
			 name.value=mftName.replace(/  +/g, ' ');
			 }
	  }
	  
	  
	  function fn_checkDbtrName(name)
	  {
		  var dbtrName=name.value;
		  if( dbtrName!=null && dbtrName!="" )
			 {
				 var regAlphaNum=/^[0-9a-zA-Z,.:\/\-& ]+$/;
			 if(dbtrName.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/,& are allowed in Distributer Name");
				 var dbID=name.id;
				 document.getElementById(dbID).value="";
				 document.getElementById(dbID).focus();
				 return false;
				 }
			 name.value=dbtrName.replace(/  +/g, ' ');
			 }
		  
	  }
	  
	  function fn_checkDrugPrice(price)
	  {
		  var drugPrice=price.value;
		  if(drugPrice!=null && drugPrice!="")
			 {
			 if(isNaN(drugPrice))
				 {
				 alert("Drug Price should be a number");
				 var pID=price.id;
				 document.getElementById(pID).value="";
				 document.getElementById(pID).focus();
				 return false;
				 }
			 price.value=drugPrice.replace(/\s/g, '');
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
	  
	
	 
	  function fn_checkBatchNo(num)
	  {
		  var batchNo=num.value;
		  if(batchNo!=null && batchNo!="")
			 {
			 var regAlphaNum=/^[0-9a-zA-Z,.:\/\- ]+$/;
			 if(batchNo.trim().search(regAlphaNum)==-1)
				 {
				 alert("Only alphanumerics and -,.,:,/ are allowed in Batch Number");
				 var bID=num.id;
				 document.getElementById(bID).value="";
				 document.getElementById(bID).focus();
				 return false;
				 }
			 num.value=batchNo.replace(/\s/g, '');;
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
	    
	    
	/*    $( "#mftrName" ).autocomplete({
		      source: availableTags,
		      autoFocus:true,
		      open: function() {
		          $("ul.ui-menu").width( $(this).innerWidth() );
		      }
		    });
	    */
	    
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
	    
    
    
  /*  $( "#dstrName" ).autocomplete({
      source: distributerNames,
      autoFocus:true,
      open: function() {
          $("ul.ui-menu").width( $(this).innerWidth() );
      }
    }); */
	  } );
	  

	
  </script>
</head>
<body onload="fn_onLoad();">
<br>
<form action="/patientDetails.do" method="post" name=patientForm>

<table  id="drugForm" width="100%" style="margin:0 auto" >
<tr><th class="tbheader" colspan="2">DRUG DETAILS FORM</th></tr>
<tr>
<td  width="53%" class="labelheading1 tbcellCss"><b>Drug Type</b> <font color="red">*</font></td>
<td  width="50%" class="labelheading1 tbcellCss"><b>Drug Name</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="53%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="drugType" styleId="drugType" style="width:17em;" title="Select Drug Type"  onchange="fn_getDrugList()"  >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dispDrugTypeList" labelProperty="VALUE"/>
</html:select>
</td>
<td width="50%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="dispDrugID" styleId="dispDrugID" style="width:17em;" title="Select Drug Name"  onchange="fn_otherDrugs();" >
		<html:option value="-1">Select</html:option>
</html:select>
<!-- <br><font size="2" color="red">Please Select 'Others' to add New Drug</font> -->
</td>
</tr>

<tr>
<td    colspan="2" >
<table width="100%" id="otherDrugs" style="display:none;margin:0 auto">
<tr>
<td width="27%" class="labelheading1 tbcellCss"><b>New Drug Name</b> <font color="red">*</font></td>
<td width="26%" class="labelheading1 tbcellCss"><b>Manufacturer Name</b> <font color="red">*</font></td>
<td width="29%" class="labelheading1 tbcellCss"><b>Distributer Name</b> <font color="red">*</font></td>
<td width="30%" class="labelheading1 tbcellCss"><b>Rate Contract Price</b> <font color="red">*</font></td> 
<td width="20%" class="labelheading1 tbcellCss"><b>Quantity</b> <font color="red">*</font></td> 

</tr>
<tr>
<td width="27%"  class="tbcellBorder"  title="New Drug Name"><html:text name="patientForm" property="dispDrugName" styleId="dispDrugName" onchange="fn_checkDrug()" maxlength="30"></html:text></td>
<td width="26%" class="tbcellBorder"  title="Manufacturer Name"><html:text name="patientForm" property="manufacturerName" styleId="manufacturerName" maxlength="30" onkeyup="fn_checkMftName(this)"></html:text></td>
<td width="29%" class="tbcellBorder"  title="Distributer Name"><html:text name="patientForm" property="distributerName" styleId="distributerName" maxlength="30" onkeyup="fn_checkDbtrName(this)"></html:text></td>
<td width="30%" class="tbcellBorder"  title="Drug Price"><html:text name="patientForm" property="dispDrugPrice" styleId="dispDrugPrice" maxlength="6" onkeyup="fn_checkDrugPrice(this)"></html:text></td>
<td width="27%" class="tbcellBorder"  title="Quantity"><html:text name="patientForm" property="dispDrugQuantity" styleId="dispDrugQuantity" maxlength="6" onkeyup="fn_checkDrugQuantity(this)"></html:text></td>

</tr>

<tr>
<td width="20%" class="labelheading1 tbcellCss"><b>Batch No</b> <font color="red">*</font></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Expiry Date</b> <font color="red">*</font></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Invoice Number</b> <font color="red">*</font></td>
<td width="20%" class="labelheading1 tbcellCss"><b>Invoice Date</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="26%" class="tbcellBorder"  title="Drug Batch No"><html:text name="patientForm" property="drugBatchNo" styleId="drugBatchNo" maxlength="20" onkeyup="fn_checkBatchNo(this)" ></html:text> </td>
<td width="29%" class="tbcellBorder"  title="Expiry Date"><html:text name="patientForm" property="drugexpiryDate" styleId="drugexpiryDate" style="width:50%"  /></td>
<td width="27%" class="tbcellBorder"  title="Invoice Number"><html:text name="patientForm" property="invoiceNumber" styleId="invoiceNumber" maxlength="25"></html:text></td>
<td width="27%" class="tbcellBorder"  title="Invoice Date"><html:text name="patientForm" property="invoiceDate" styleId="invoiceDate" title="Enter From Date"  style="WIDTH: 10em;"/></td>

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

<tr>
<td    colspan="3" >
<table width="100%" id="addNew" style="display:none;margin:0 auto">
<tr><td  align="center">
<button type="button" class="btn btn-primary" id="addMnfNDist" onclick="javascript:fn_addNewMnfNDist()"  title="Click Here To Add New Manufacturer or Distributer">Add New Manufacturer/Distributer</button>
</td></tr>
</table>
</td></tr>

<tr>
<td  colspan="3" >
<table width="100%" id="addMnD" style="display:none;margin:0 auto">
<tr>
<td  class="labelheading1 tbcellCss"><b>Manufacturer Name</b> <font color="red">*</font></td>
<td  class="labelheading1 tbcellCss"><b>Distributer Name</b> <font color="red">*</font></td>
<td   class="labelheading1 tbcellCss"><b>Rate Contract Price</b> <font color="red">*</font></td> 
<td  class="labelheading1 tbcellCss"><b>Quantity</b> <font color="red">*</font></td> 

</tr>
<tr>
<td width="26%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="mftrName" styleId="mftrName" style="width:17em;" title="Select Manufacturer Name" >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="mftList" labelProperty="VALUE"/>
</html:select>
</td>
<td width="26%" valign="top" class="tbcellBorder">
<html:select name="patientForm" property="dstrName" styleId="dstrName" style="width:17em;" title="Select Distributer Name" >
		<html:option value="-1">Select</html:option>
		<html:options property="ID" collection="dstrList" labelProperty="VALUE"/>
</html:select>
</td>
<td   class="tbcellBorder"  title="Drug Price"><html:text name="patientForm" property="drugPrice" styleId="drugPrice" maxlength="6" onkeyup="fn_checkDrugPrice(this)"></html:text></td>
<td width="20%" class="tbcellBorder"  title="Quantity"><html:text name="patientForm" property="quantity" styleId="quantity" maxlength="6" onkeyup="fn_checkDrugQuantity(this)"></html:text></td>

</tr>

<tr>
<td  class="labelheading1 tbcellCss"><b>Batch No</b> <font color="red">*</font></td>
<td class="labelheading1 tbcellCss"><b>Expiry Date</b> <font color="red">*</font></td>
<td  class="labelheading1 tbcellCss"><b>Invoice Number</b> <font color="red">*</font></td>
<td  class="labelheading1 tbcellCss"><b>Invoice Date</b> <font color="red">*</font></td>
</tr>
<tr>
<td width="20%" class="tbcellBorder"  title="Drug Batch No"><html:text name="patientForm" property="batchNo" styleId="batchNo" maxlength="20" onkeyup="fn_checkBatchNo(this)"></html:text> </td>
<td width="20%" class="tbcellBorder"  title="Expiry Date"><html:text name="patientForm" property="expiryDt" styleId="expiryDt" style="width:70%"/></td>
<td width="20%" class="tbcellBorder"  title="Invoice Number"><html:text name="patientForm" property="invoiceNumberNew" styleId="invoiceNumberNew" maxlength="25" style="width:100%"/></td>
<td width="27%" class="tbcellBorder"  title="Invoice Date"><html:text name="patientForm" property="invoiceDateMn" styleId="invoiceDateMn" title="Enter From Date"  style="WIDTH: 10em;"/></td>

</tr>
<tr><td class="tbcellBorder" colspan="4" align="center" >
<button type="button" class="btn btn-primary" id="addMnfDst" onclick="javascript:fn_saveMnfDist()"  title="Click Here To Submit"> Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</td></tr>
</table>
</td>
</tr>
		

			<tr><td class="tbcellBorder" colspan="2" align="center">
<button type="button" class="btn btn-primary" id="Submit" onclick="javascript:fn_saveInventoryDetails()"  title="Click Here To Submit"> Submit&nbsp;<span class="glyphicon glyphicon-ok"></span></button>
</td></tr>
</table>

		<div>
			<table id="processImage" width="100%"
				style="top: 50%; width: 100%; display: none;">
				<tr>
					<td style="text-align: center;">
						<div>
							<img src="images/Progress.gif" width="100" height="100"
								border="0"></img>
						</div>
					</td>
				</tr>
			</table>
		 </div>

<script>

$("#drugType").select2(); 
$("#dispDrugID").select2();
$("#mftrName ").select2();
$("#dstrName ").select2();

</script>

</form>
</body>
</html>