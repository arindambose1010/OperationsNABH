// treating doctor mandatory checks

function checkcontact(arg1,arg)//for accepting numbers either 10 0r 11 digits only
{
    var len,str,str1,i
    len=arg1.value.length
    str=arg1.value
    str1="0123456789";
    var e="[7,8,9]";
	if(len<=9 && len>0)
	{
	   alert("Enter 10 digits valid "+arg);
	   focusNClear(arg1);
		//var fr = partial(focusNClear,arg1);
		//jqueryAlertMsg('Preauth mandatory fields',"Enter 10 digits valid "+arg,fr);
	  // arg1.value="";
	 //  arg1.focus();
       return false;	   
	}
	for(i=0,j=0;i<len;i++)
    {
        if((str1.indexOf(str.charAt(i)))==-1)
        {
        	//var fr = partial(focusNClear,arg1);
    		//jqueryAlertMsg('Preauth mandatory fields',"Enter Numeric Data in "+arg,fr);
                alert("Enter Numeric Data in "+arg);
				focusNClear(arg1);
//                arg1.value="";
//                arg1.focus();
                return false;
        }
         if (i==0)
        	{
        	if(str.charAt(i).search(e))
        		{
        		//var fr = partial(focusNClear,arg1);
        		//jqueryAlertMsg('Preauth mandatory fields',arg+" should start with number 7,8 or 9",fr);
        		alert(arg+" should start with number 7,8 or 9");
				focusNClear(arg1);
//        	    arg1.value="";
//        	    arg1.focus();
        	    return false;
        		}
        	}
		if(str.charAt(i)==0)
		{
		j++;
		}
		if(j==len)
		{
			//var fr = partial(focusNClear,arg1);
    		//jqueryAlertMsg('Preauth mandatory fields',arg +" with all zeros is invalid. Please enter a valid "+arg,fr);
			alert(arg +" with all zeros is invalid. Please enter a valid "+arg);
			focusNClear(arg1);
//			arg1.value="";
//			arg1.focus();
			return false;
		}
	}
return true;
}
function chkAlphaNumeric(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	//var fr = partial(focusNClear,textbox1);
		//jqueryAlertMsg('Clinical notes mandatory fields','Blank spaces are not allowed in '+arg2,fr);
    	alert('Blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    	}
    if(textval.charAt(0)==' '){ 
    	//var fr = partial(focusNClear,textbox1);
		//jqueryAlertMsg('Clinical notes mandatory fields','Starting blank spaces are not allowed in '+arg2,fr);
		alert('Starting blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    }
    var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?.";
    if(iChars.indexOf(textval.charAt(0))!= -1){
    	//var fr = partial(focusNClear,textbox1);
		//jqueryAlertMsg('Clinical notes mandatory fields','Starting special characters are not allowed in '+arg2,fr);
		alert('Starting special characters are not allowed in '+arg2);
		focusNClear(textbox1);
    }
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz 0123456789()./";
   var IsNumber=true;
   var Char;
   textval= textval.replace(/\r\n|\r|\n/g, '');
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
        	   //var fr = partial(focusNClear,textbox1);
       		   //jqueryAlertMsg('Clinical notes mandatory fields',"Only Characters from A-Z,a-z,0-9 are allowed in "+arg2,fr);
				alert("Only Characters from A-Z,a-z,0-9 are allowed in "+arg2);
				focusNClear(textbox1);
//                textbox1.value= '';
//                textbox1.focus();
				return false;
			 }
			}
	  if( textval.match(/[,.\/]{2}/i))
		{
		//var fr = partial(focusNClear,textbox1);
		  // jqueryAlertMsg('Clinical notes mandatory fields',"continuous special characters are not allowed in "+arg2,fr);
		  alert("Continuous special characters are not allowed in "+arg2);
		  focusNClear(textbox1);
		}
	}
	else 
		return false;
}

function chkAlpha(arg,arg2)
{
    var textbox1 =  eval("document.forms[0]."+arg);    
    var textval = textbox1.value;
    var tbLen = textval.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	// var fr = partial(focusNClear,textbox1);
 		// jqueryAlertMsg('Preauth mandatory fields','Blank spaces are not allowed in '+arg2,fr);
    	alert('Blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    	}
    if(textval.charAt(0)==' '){ 
    	//var fr = partial(focusNClear,textbox1);
		// jqueryAlertMsg('Preauth mandatory fields','Starting blank spaces are not allowed in '+arg2,fr);
    	alert('Starting blank spaces are not allowed in '+arg2);
		focusNClear(textbox1);
//    	textbox1.focus();
//    	textbox1.value='';
    	return false;
    }
   var ValidChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz ()./";
   var IsNumber=true;
   var Char; 
   if(textval.length > 0)
	{
	  for (i = 0; i < textval.length && IsNumber == true; i++) 
		 { 
	        Char = textval.charAt(i); 
           if (ValidChars.indexOf(Char) == -1) 
			{
        	  // var fr = partial(focusNClear,textbox1);
      		   //jqueryAlertMsg('Preauth mandatory fields',"Only Characters from A-Z,a-z are allowed in "+arg2,fr);
				alert("Only Characters from A-Z,a-z are allowed in "+arg2);
				focusNClear(textbox1);
//                textbox1.value= '';
//                textbox1.focus();
				return false;
			 }
			}
	  
	if( textval.match(/[,.\(\)\/]{2}/i))
		{
		//var fr = partial(focusNClear,textbox1);
		 //  jqueryAlertMsg('Preauth mandatory fields',"continuous special characters are not allowed in "+arg2,fr);
		 alert("Continuous special characters are not allowed in "+arg2);
		 focusNClear(textbox1);
		}
	 var iChars = "~`!#$%^&*+=-[]\\\';,/{}|\":<>?.";
	    if(iChars.indexOf(textval.charAt(0))!= -1){
	    	//var fr = partial(focusNClear,textbox1);
			//jqueryAlertMsg('Preauth mandatory fields','Starting special characters are not allowed in '+arg2,fr);	
			alert("Starting special characters are not allowed in "+arg2);
			focusNClear(textbox1);
	   }

	}
	else 
		return false;
}

function checkRadio1(id,value)
{
	document.getElementById(id.name).value=value;
	}

function fn_cancelPreauth()
{
	 if(document.forms[0].cancelRemarks.value == null || document.forms[0].cancelRemarks.value=='')
		{
			 //var fr = partial(focusBox,document.getElementById('cancelRemarks'));
			 //jqueryAlertMsg('Preauth mandatory fields','Please enter the remarks',fr);
			 alert("Please enter the remarks ");
			focusNClear(document.getElementById('cancelRemarks'));
			return;
		}
	 if(document.forms[0].cancelRemarks.value != null)
		 {
		 var tbLen = document.forms[0].cancelRemarks.value.replace(/\s+/g,'').replace(/\s+$/g,'') ;
		    if(tbLen.length == 0)
		    	{
		    	 //var fr = partial(focusBox,document.getElementById('cancelRemarks'));
				 //jqueryAlertMsg('Preauth mandatory fields','Blank spaces are not allowed in remarks',fr);
		    	 alert('Blank spaces are not allowed in remarks');
				 focusNClear(document.getElementById('cancelRemarks'));
				 return;
		    	}
		 
	}

	//var fr = partial(fn_cancelSub);
	 //jqueryConfirmMsg('Preauth Cancellation','Do you want to cancel preauth ? ',fr);	
	 if(confirm('Do you want to cancel preauth ?'))
	 {
		fn_cancelSub();
	 }

}
function fn_cancelSub()
{	
	document.getElementById('cancelPreauth').disabled=true;
	document.forms[0].action="preauthDetails.do?actionFlag=cancelPreauth";
    document.forms[0].submit();	
}
function trim (str) 
{ 
	return str.replace (/^\s+|\s+$/g, '');
}

function fn_getPrice(drugId)
{
	//document.getElementById('processImagetable').style.display="";
	var jq = jQuery.noConflict();
	document.getElementById("drugPrice").value=null;
	var drugId=drugId.value;
	if(drugId!=null )
		{ 
		var drugSplit=jq("#drugId option:selected").text();
		 var drugPriceSlt=drugSplit.split("Rs.");
		   drugPriceSlt=drugPriceSlt[1].replace("(","");
		   drugPriceSlt=drugPriceSlt.replace(")","");		   
		 document.getElementById("drugPrice").value=drugPriceSlt;
		}

}


function getValidSandostatinInj()
{
	var patientNo = '<bean:write name="preauthDetailsForm" property="patientId" />';
	var icdProcCode=document.getElementById("icdProcCode").value;
	if(icdProcCode != null && icdProcCode != '' && icdProcCode == 'S30.1.1')
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
		 	url = "./patientDetails.do?actionFlag=getValidSandostatinInj&callType=Ajax&patientNo="+patientNo+"&icdProcCode="+icdProcCode;    
		    xmlhttp.onreadystatechange=function()
		    {
		       if(xmlhttp.readyState==4)
		       {
		    	   var resultArray=xmlhttp.responseText;
		    	   if(resultArray.trim()=="SessionExpired*"){
		    		   jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
		            }
		            else
		            {
						var totalResult = resultArray.split("*");
						totalResult = resultArray.replace("[","");
						totalResult = resultArray.replace("]*","");   
						totalResult = resultArray.replace(",","");   
						totalResult = resultArray.replace("*","");   
		                if(totalResult.trim() == 'false')
	                	{
		                	  var fr = partial(focusBox,document.getElementById("category"));
		                	  alert("Cannot add this procedure at this time, as the patient has already availed this procedure in this month");
		                	  partial(focusBox,document.getElementById("category"));
		                	  document.forms[0].icdProcCode.options.length=0;
		      				document.forms[0].icdProcCode.options[0]=new Option("--select--","-1");
		      				document.forms[0].icdCatCode.options.length=0;
		      				document.forms[0].icdCatCode.options[0]=new Option("--select--","-1");
		      				document.forms[0].docSpecReg.options.length=0;
		      				document.forms[0].docSpecReg.options[0]=new Option("--select--","-1");
		      				document.getElementById('procUnits').value="-1";
		      				document.forms[0].procUnits.options.length=0;
		      				document.forms[0].procUnits.options[0]=new Option("--select--","-1");
		      				document.getElementById("unitsTd").style.display='none';
		      				document.getElementById("unitsLabelTd").style.display='none';
		      			  	document.getElementById('myDivSpl').style.display="none";
		      			 	document.getElementById('myDivSplUpload').style.display="none";
		                	 /*  document.getElementById("category").value='-1';
		                	  document.getElementById("icdCatCode").value='';
		                	  document.getElementById("icdProcCode").value='-1';
		                	  document.getElementById("docSpecReg").value=''; */
		                	  return false;
	                	}
		                
	                }
	            }
	       	}
			xmlhttp.open("Post",url,true);
			xmlhttp.send(null);
	}
}

function captureBiometrics(id)
{
 var cardNo = '<bean:write name="preauthDetailsForm" property="cardNo" />';
 var caseId = '<bean:write name="preauthDetailsForm" property="caseId" />';
 
   var fingerPrint='CVERFDGVNERJIOFNDIOHNERIOJGIODFNBUIONGIFMIOFGBETGEGRTGHDBTFG';
  document.forms[0].fingerPrint.value=fingerPrint; 
 
 /*  var fingerPrint=document.forms[0].fingerPrint.value; 
 GetFeature(document.forms[0].fingerPrint);  */
	
	var xmlhttp;
	var url;
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
	 alert("Your Browser Does Not Support XMLHTTP!");
	}
	 
	 xmlhttp.onreadystatechange=function()
		{
		    if(xmlhttp.readyState==4)
		    {	
		    	
		    	var resultArray=xmlhttp.responseText;
	            if(resultArray.trim()=="SessionExpired*")
	            {
	            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	            else
	            {
	            	
	            	if(resultArray!=null)
	            	{
	            		resultArray = resultArray.replace("[","");
	            		resultArray = resultArray.replace("]*","");
	            		resultArray = resultArray.replace("*","");
	            		var response = $.parseJSON(resultArray);
				        if(response.isCaptured.msg == "Success" && id == "captureInTime")
				        {	
				        	document.getElementById("patientInTime").value=response.isCaptured.captureTime;
				        	document.getElementById("captureOutTime").style.display="";
				        	document.getElementById("patientOutTime").style.display="";
				        	document.getElementById("captureInTime").style.display="none";
				        	bootbox.alert(" In Time Captured Successfully");
				        }
				        else if(response.isCaptured.msg == "Failure" && id == "captureInTime")
			        	{
			        		document.getElementById("captureInTime").style.display="";
			        		document.getElementById("captureOutTime").style.display="none";
			        		bootbox.alert(" Failure Occured while capturing In Time ");
			        	}
				        else if(response.isCaptured.msg == "Success" && id == "captureOutTime")
				        {	
				        	document.getElementById("patientOutTime").value=response.isCaptured.captureTime;
				        	document.getElementById("captureOutTime").style.display="none";
				        	document.getElementById("patientOutTime").style.display="";
				        	//document.getElementById("addCycleDia").style.display="";
				        	bootbox.alert(" Out Time Captured Successfully");
				        }
				        else if(response.isCaptured.msg == "Failure" && id == "captureOutTime")
			        	{
			        		document.getElementById("captureInTime").style.display="none";
			        		document.getElementById("captureOutTime").style.display="";
			        		bootbox.alert(" Failure Occured while capturing Out Time ");
			        	}
		    		}
	              }
		    	}
		    }
	 url = 'preauthDetails.do?actionFlag=captureBioDtls&caseId='+caseId+'&cardNo='+cardNo+'&captureTime='+id+'&fingerPrint='+fingerPrint;
	    xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
}

function printPrf()
{
	var caseId= '<bean:write name="preauthDetailsForm" property="caseId" />';
	var patientId= '<bean:write name="preauthDetailsForm" property="patientId" />';
	var url="preauthDetails.do?actionFlag=preauthDetailsEhf&printFlag=Y&caseId="+caseId+"&patientId="+patientId;
	childWindow= window.open(url, '_blank','toolbar=no,resizable=yes,scrollbars=yes,width=980, height=800, top=100,left=50');
}  

function fn_addDrugBreakUp(drugId,drugQuant,unitPrice)
{
	var jq = jQuery.noConflict();
	
if(document.getElementById("drugId").value == null || document.getElementById("drugId").value =='' || document.getElementById("drugId").value=='-1')
	{
	var fr = partial(focusBox,document.getElementById("drugId"));
    //jqueryAlertMsg('Preauth mandatory fields','Please select code',fr);
	alert('Please select Drug Name');
	focusBox(document.getElementById("drugId"));
	//document.getElementById(codeId).focus();
	return;
	}

if(document.getElementById("drugQuant").value == null || document.getElementById("drugQuant").value =='' ||  document.getElementById("drugQuant").value=='-1')
{
	var fr = partial(focusBox,document.getElementById("drugQuant"));
    //jqueryAlertMsg('Preauth mandatory fields','Please select quantity',fr);
alert('Please select drug quantity');
focusBox(document.getElementById("drugQuant"));
//document.getElementById(quanId).focus();
return;
}
if(document.getElementById("drugQuant").value>100)
{
	var fr = partial(focusBox,document.getElementById("drugQuant"));
    //jqueryAlertMsg('Preauth mandatory fields','Please select quantity',fr);
alert('Maximum restricted quantity is 100 only.please enter the quantity upto 100');
focusBox(document.getElementById("drugQuant"));
//document.getElementById(quanId).focus();
return;
}


 //ocument.getElementById("drugImplant").disabled=true;

  var caseId= document.forms[0].caseId.value;
  var patientId= document.forms[0].patientId.value;
  var hospId = document.forms[0].hospitalId.value;
  var maxAmountalloewd= '<bean:write name="preauthDetailsForm" property="maxAmountAllowed" />';

  var totalDrug=drugQuant*unitPrice;
 
  if(totalDrug<199999)
	{
	  document.getElementById('processImagetable').style.display="";
	  jq(':input[type="button"]').prop('disabled', true);
	  document.getElementById("drugDiv").style.display="";
	    document.getElementById("drugTable").style.display="";
	 
  var url = 'preauthDetails.do?actionFlag=saveDrugDetails';
  /*document.getElementById('processImagetable').style.display="";*/
  jq.post(url,{'caseId':caseId,'drugId':drugId,'drugQuantity':drugQuant,'unitPrice':unitPrice,'patientId':patientId,'hospId':hospId},function(data){
	  var jsonData=JSON.parse(data.replace("*",""));
	  jq('#drugTable > tbody').html("");
	  var len = jsonData.cardDetails.length;
	  for(var k=0;k<len;k++){
		    var x = "<tr>";
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugName+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].unitPrice+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugQuantity+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugAmt+'</td>';
			
			x +='<td class="tbcellBorder tbcellCss"><button type="button" class="btn btn-danger"  onclick="fn_deleteDrugBreakUp(\''+jsonData.cardDetails[k].caseId+'\',\''+jsonData.cardDetails[k].drugId+'\',\''+jsonData.cardDetails[k].unitPrice+'\',\''+jsonData.cardDetails[k].drugQuantity+'\');" >Delete<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
			x += "</tr>";
			jq('#drugTable > tbody:last-child').append(x);
			document.getElementById("drugId").value = '-1';
			jq("#drugId").select2().val("-1");
			document.getElementById("drugQuant").value=null;
			document.getElementById("drugPrice").value=null;
			jq(':input[type="button"]').prop('disabled', false);
			  fn_checkamountlockedDrug(totalDrug,"A")
			
			
			
	  }
  },'text')
  .always(function(){document.getElementById('processImagetable').style.display="none";})
  .fail(function(){alert('Network Error. Please try again');});
  document.preauthDetailsForm.drugId.options.length=0;
  document.getElementById("postcodeSearch").value=null;
  document.getElementById("drugId").value = '-1';
  }
	    else
	    	{
	    	alert("Cannot add more than Rs.1,99,999/- at a time");
	    	document.getElementById('processImagetable').style.display="none";
	    	document.getElementById("drugId").value = '-1';
	    	jq("#drugId").select2().val("-1");
			document.getElementById("drugQuant").value=null;
			document.getElementById("drugPrice").value=null;
	    	
	    
	    	}
  
}
function fn_deleteDrugBreakUp(caseId,drugId,unitPrice,drugQuantity)
{
	var jq = jQuery.noConflict();
  var patientId= document.forms[0].patientId.value;
  var hospId = document.forms[0].hospitalId.value;
  
  if(confirm('Do you want to delete drug ?'))

			{
				
  document.getElementById('processImagetable').style.display="";			
  var url = 'preauthDetails.do?actionFlag=deleteDrugDetails';
  var totalDrug=drugQuantity*unitPrice;
  /*document.getElementById('processImagetable').style.display="";*/
  jq.post(url,{'caseId':caseId.toUpperCase(),'drugId':drugId,'unitPrice':unitPrice,'patientId':patientId,'hospId':hospId},function(data){
	  var jsonData=JSON.parse(data.replace("*",""));
	  jq('#drugTable > tbody').html("");
	  var len = jsonData.cardDetails.length;
	
	  for(var k=0;k<len;k++){
		    var x = "<tr>";
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugName+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].unitPrice+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugQuantity+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugAmt+'</td>';
/*			x +='<td class="tbcellBorder tbcellCss"><button type="button" class="btn btn-danger"  onclick="fn_deleteDrugBreakUp(this);" name='+document.getElementById("drugId").value+' id='+document.getElementById("drugId").value+'>Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
*/			
			x +='<td class="tbcellBorder tbcellCss"><button type="button" class="btn btn-danger"  onclick="fn_deleteDrugBreakUp(\''+jsonData.cardDetails[k].caseId+'\',\''+jsonData.cardDetails[k].drugId+'\',\''+jsonData.cardDetails[k].unitPrice+'\',\''+jsonData.cardDetails[k].drugQuantity+'\');" >Delete<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
			
			x += "</tr>";
			jq('#drugTable').show();
			jq('#drugTable > tbody:last-child').append(x);
			//$('#drugTable').closest(x).remove();

			//document.getElementById('processImagetable').style.display="none";
	  }

	  fn_checkamountlockedDrug(totalDrug,'D')
	  
	  if(jsonData.cardDetails.length==0){
		  jq('#drugTable').hide(); 
	 }
	  //alert("Drug deleted successfully");

  },'text')
  .always(function(){document.getElementById('processImagetable').style.display="none";})
  .fail(function(){alert('Network Error. Please try again');});
			}
	
  
}


function fn_getDrugQuant()
{ 
	var jq = jQuery.noConflict();
	document.getElementById('drugPrice').disabled=true;
	 var caseId= document.forms[0].caseId.value;
	 var hospId = document.forms[0].hospitalId.value;
	// $('#myModal').modal({backdrop: 'static', keyboard: false})  
	 document.getElementById('processImagetable').style.display="";
var url = 'preauthDetails.do?actionFlag=getDrugQuantList';
jq('#drugTable').hide();
jq("#drugDiv").hide();
jq.post(url,{'caseId':caseId,'hospId':hospId},function(data){
	  var jsonData=JSON.parse(data.replace("*",""));
	  jq('#drugTable > tbody').html("");
	  var len = jsonData.cardDetails.length;
	  for(var k=0;k<len;k++){
		    var x = "<tr>";
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugName+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].unitPrice+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugQuantity+'</td>';
			x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugAmt+'</td>';
			x +='<td class="tbcellBorder tbcellCss"><button type="button" class="btn btn-danger"  onclick="fn_deleteDrugBreakUp(\''+jsonData.cardDetails[k].caseId+'\',\''+jsonData.cardDetails[k].drugId+'\',\''+jsonData.cardDetails[k].unitPrice+'\',\''+jsonData.cardDetails[k].drugQuantity+'\');" >Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
			x += "</tr>";
			jq('#drugTable > tbody:last-child').append(x);
			jq('#drugTable').show();
			jq("#drugDiv").show();
	  }
	  
/* var drugsLt=jsonData.drugsList;
*/	  /*var drugsLt="${drugTypeLstFinal}";
resultArray = drugsLt.replace("[","");
resultArray = drugsLt.replace("@]*","");            
var drugList1 = drugsLt.split("@,")
      alert(drugsLt1.length);
      alert(drugsLt1[0]);
	  for(var i = 0; i<drugsLt.length;i++)
		{	
			var arr=drugsLt[i].split("~");
		
			if(arr[1]!=null && arr[0]!=null)
			{
				var val1 = arr[1].replace(/^\s+|\s+$/g,"");
				var val2 = arr[0].replace(/^\s+|\s+$/g,"");
			
				document.preauthDetailsForm.drugId.options[i+1] =new Option(val1,val2);				
				
			}
		}*/
},'text')
.always(function(){document.getElementById('processImagetable').style.display="none";})
.fail(function(){alert('Network Error. Please try again');});


	
	}

function fn_addTotalBreakUp()
{

	
	  var tab = document.getElementById('drugTable');
	    var l = tab.rows.length;

	    var s=0;
	    for ( var i = 1; i < l; i++ )
	    {
	        var tr = tab.rows[i];

	        var cll = tr.cells[3];
	       

	        s = Number(s) + Number(cll.innerText);
	    }

	    document.getElementById("drugAmount").value=s;
	    fn_addEnhAmt('0');
	    document.preauthDetailsForm.drugId.options.length=0;
        document.getElementById("postcodeSearch").value=null;
        document.getElementById("drugId").value = '-1';
	    
}
function fn_searchDrugBreakUp()
{      
	var jq = jQuery.noConflict();
	  var str = document.getElementById('postcodeSearch').value;
	  var hospId = document.forms[0].hospitalId.value;
	  var msg = chkSpecailCharsDrug(str,'3000');
	  if(msg !=null && msg !='')
		{
			//jqueryAlertMsg('Preauth mandatory check',msg ,fr);
			return;
		}else{
	  if (str.length < 3) {
        alert("Please enter atleast 3 characters");
        document.getElementById("postcodeSearch").value=null;
        return("too_short");
	    }
	  if(str=='TAB'||str=='tab'||str=='TABS'||str=='tabs'){
		  alert("TAB or TABS Word is Not Allowed ,Please Search With Different Word");
	        document.getElementById("postcodeSearch").value=null;
	        return;	  
	  }
	  else{
	     jq(':input[type="button"]').prop('disabled', true);
	      document.preauthDetailsForm.drugId.value="-1";
		document.getElementById('drugPrice').disabled=true;
		 //var drugSearch= document.forms[0].drugSearch.value;
		document.getElementById('processImagetable').style.display="";
	    var url = 'preauthDetails.do?actionFlag=searchDrugs';
	    jq.post(url,{'drugSearch':str,'hospId':hospId},function(data){
		  var drugsLt=JSON.parse(data.replaceAll("*",""));
	      //var drugsLt=jsonData.drugDetails;
	      document.preauthDetailsForm.drugId.options.length=0;
		  for(var i = 0; i<drugsLt.length;i++)
			{	
				var arr=drugsLt[i];
			
					var val1 = arr.VALUE;
					var val2 = arr.ID;
					
					document.preauthDetailsForm.drugId.options[i+1] =new Option(val1,val2);	
					
			
			}
		  jq(':input[type="button"]').prop('disabled', false);

	},'text')
	.always(function(){document.getElementById('processImagetable').style.display="none";jq('#drugId').select2('open');})
	.fail(function(){alert('Network Error. Please try again');});		
	}
		}  
	
}
function chkSpecailCharsDrug(name,remarkLength)
{
    var chars="*|\":<>[]{}`\';$#%&)("; //04
    var message1='';
    var msg ='';
    var tbLen = name.replace(/\s+/g,'').replace(/\s+$/g,'') ;
    if(tbLen.length == 0)
    	{
    	message1 ="Blank spaces are not allowed in Drug Search";
    	}
    if(name.charAt(0)==' ')
    	{
    	message1 ="Starting blank spaces are not allowed in Drug Search";
    	}
    if(remarkLength == 3000)
    	{
    	if(name.length >= 2999)
    	{
    	message1 = "Drug Search length should not exceed " +remarkLength ;
    	}
    	}
    else
    	{
    if(name.length >= remarkLength)
    	{
    	message1 = "Drug Search length should not exceed " +remarkLength ;
    	}
    	}
    var message='';
    for (var i = 0; i < name.length; i++) 
    {
        if(chars.indexOf(name.charAt(i))!=-1)
        {
            message=" Drug Search should not contain special characters ";
            
        }
    }
    return message+message1;
}
function uploadDrugExcel()
{    var jq = jQuery.noConflict();
     var res = validateAllFields();
     if(res)
 	{ 
	var vFileName=document.getElementById('attachment').value;  
   document.getElementById('processImagetable').style.display="";
   document.getElementById('uploadExcel').style.display="none";
   jq(':input[type="button"]').prop('disabled', true);
    var caseId= document.forms[0].caseId.value;
    var patientId= document.forms[0].patientId.value;
    var hospId = document.forms[0].hospitalId.value;
    jq("a").attr("href", "javascript: void(0);" ); 
	 document.preauthDetailsForm.action='./preauthDetails.do?actionFlag=uploadDrugExcel&caseId='+caseId+'&patientId='+patientId+'&hospId='+hospId;
	 document.preauthDetailsForm.submit(); 
	//document.getElementById('processImagetable').style.display="none";
 	}
     
}
function validateAllFields(){
	var vFileName=document.getElementById('attachment').value;  
	vSplit=vFileName.split("\\");
	vFileName = vSplit[(vSplit.length)-1];	
	var pos=vFileName.lastIndexOf(".");
	var sub=vFileName.substring(pos+1,vFileName.length); 
	if((sub=='xls')||(sub=='XLS')  )
	   {
		 flag = true;
	   } 
	else {
		 flag = false; 
		 alert("Please upload XLS file. Note: Please upload microsft 97/2000/XP .xls file for better performance. ");
		 return false;
		 }
	/* var flag = chkSpecailCharsAttach(vFileName);
	   if(!flag)
	  	 {
	   	 alert("Uploaded file can not contain special characters.Please verify and upload again");
	   	 flag = false; 
	   	 return false;
	  	 }*/
	return true;
	
}

function chkSpecailCharsAttach(vFileName)
{
	
   var flag =true;  
   var iChars = "*|\":<>[]{}`\';()$#%&^.,!@?/";    
   var iChars1="-_";
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {  
        	flag=false;
           break;
        } 
    }
    if( vFileName.match(/[\-\_]{2}/i))
	{
    	flag=false;
	}
    if (iChars1.indexOf(vFileName.charAt(0))!=-1 || iChars1.indexOf(vFileName.charAt(vFileName.length-1)) != -1)
	{
    	flag=false;
	}
    return flag;
} 

function checkForSimilarUploadAttach(vFileName)
{
	var vSplit;
	vSplit=vFileName.split("\\");
    vFileName = vSplit[(vSplit.length)-1];
    var  rtVal=0;
    for(k=0;k<dbFilesArray.length;k++)      		
    {	
        if( vFileName == dbFilesArray[k])
        {
        	 rtVal++;
        }
    }
    if(rtVal ==0)
    	{
    	return false;
    	}
    else
    	return true;
	}
	function validateCaseAttach(vFileName)
	{	
		var msg='success';
		if(vFileName == null || vFileName=='')
		 {
		 alert('Please upload file');
		 //jqueryAlertMsg('Attachments check',"Please upload file");
		 msg="failure";
		 }
		 vSplit=vFileName.split("\\");
		vFileName = vSplit[(vSplit.length)-1];	
		 var pos=vFileName.lastIndexOf(".");
		var sub=vFileName.substring(pos+1,vFileName.length); 
		if((sub=='exe')||(sub=='EXE') || (sub=='rar')||(sub=='RAR') || (sub=='war')||(sub=='WAR')|| (sub=='zip')||(sub=='ZIP') || (sub=='java')||(sub=='JAVA'))
		{
			flag="false";
		    alert("Cannot Upload exe,rar,war files");
			//jqueryAlertMsg('Attachments check',"Cannot Upload exe,rar,war  files");
			msg="failure";
		} 
		if((sub=='jpg')||(sub=='JPG') || (sub=='jpeg')||(sub=='JPEG') || (sub=='gif')||(sub=='GIF')|| (sub=='bmp')||(sub=='BMP')||(sub=='pdf')||(sub=='PDF'))
		{
			  flag="true";
			 
		}
		else
			{
			 flag="false";
			 //jqueryAlertMsg('Attachments check'," Cannot Upload "+sub + " files . Please attach gif,jpg,bmp,pdf //formats only");
			 alert(" Cannot Upload "+sub + " files . Please attach gif,jpg,bmp,pdf formats only");
			 msg="failure";
			}
		var pos1=vFileName.lastIndexOf(".");
		sub1=vFileName.substring(0,pos1);	
		var flag = chkSpecailCharsAttach(sub1);
		if(!flag)
		 {
			// jqueryAlertMsg('Attachments check',"Special characters are not allowed");
		 alert('Special characters are not allowed');
		 msg="failure";
		 }
		flag=checkForSimilarUploadAttach(vFileName);
		if(flag)
		 {
			// jqueryAlertMsg('Attachments check',"Cannot upload similar attachments");
		 alert('Cannot upload similar attachments');
		 msg="failure";
		 }
		return msg;
	}
	function checkFileSizeFF(input)
	{
		var filesize=input.files[0].size;
		if((filesize/(1024))>200)
		{
			jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
			return false;
		}
	}
	function checkFileSizeIE(input)
	{
		try
		{
	 	var myFSO = new ActiveXObject("Scripting.FileSystemObject");
	 	var filepath = input.value;
	 	var thefile = myFSO.getFile(filepath);
	 	var filesize = thefile.size/(1024);
	 	if(filesize>200)
		{
	 		jqueryErrorMsg('File Size Validation',"Uploaded file size exceeded 200KB");
			return false;
		}
		}
		catch(e)
		{
			jqueryInfoMsg('ActiveX Control Enable',"Please Enable ActiveX control.");
			jqueryInfoMsg('Steps To Enable ActiveX Control',"Go To-->Tools-->Internet Options-->Security-->Trusted Sites-->Click on Sites Button-->Add site url to list-->close-->Click on Custom level Button-->Initialize and script ActiveX controls not marked as safe for scripting---Enable");
			return false;
		}
	}
	function checkFileNameMatch(input)
	{
		var curFile=input.value;
		//var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1));
		
		var rtVal=chkSpecailChars(curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.')));
		var fullFileName=curFile.substring(curFile.lastIndexOf('\\')+1);
		var fileName1=curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
		/* if(rtVal ==0)   
			{
			jqueryErrorMsg('File Name Validation',"File name("+fullFileName+") should not contain special characters");
			return false;
			} */
		if(fileName1.charAt(0)=='-' || fileName1.charAt(fileName1.length-1)=='-' || fileName1.charAt(0)=='_' || fileName1.charAt(fileName1.length-1)=='_')
			{
			jqueryErrorMsg('File Name Validation',"File name should not start or end with - or _");
			return false;
			}
		
		if( fileName1.match(/[\-\_]{2}/i))
		{
			jqueryErrorMsg('File Name Validation',"File name should not should not contain consecutive special characters");
			return false;
		}
		var extn=curFile.substring(curFile.lastIndexOf('.')+1).toLowerCase();
		if(extn=='jpg' || extn=='jpeg' || extn=='png' || extn=='bmp' || extn=='pdf')  
			{
			var status=true;
			}
		else
			{
			jqueryErrorMsg('File Type Validation',"Can upload jpg,jpeg,png,bmp extension files only");
			return false;
			}
		var matchCount=0;
		for(var temp=1;temp<document.forms[0].elements.length;temp++)
	    {
	       if(document.forms[0].elements[temp].type=="file")
	       {
	       	   var val=document.forms[0].elements[temp].value;
	       	   var fileName = val.substring(val.lastIndexOf('\\')+1,val.lastIndexOf('.'));
	       	   var curFileName = curFile.substring(curFile.lastIndexOf('\\')+1,curFile.lastIndexOf('.'));
			   if(fileName==curFileName)
			   matchCount++;
			   if(matchCount>1)
				{
					jqueryErrorMsg('File Name Validation',"File with this filename already exists.Cannot upload");
					return false;
				}
	       }
	    }
		
	}
	
	function getTherapyICDProcedureListMed1(param)
	{ 
		
		 var jq=jQuery.noConflict();
		
		var hospId = document.forms[0].hospitalId.value;
		var treatType=ipFlag;

		//var hospId = document.getElementById("hospitalId").value;

		document.forms[0].ICDCatCode1.value="";
		document.forms[0].ICDProcCode1.value="";
		document.forms[0].ICDProcName1.options.length=1;
		/*getProcedureCodes();*/
		jq("#otherId").hide();
		jq("#otherRemarks").hide();
		if(document.forms[0].medicalCat.value=="-1")
			{
			return false;
			}
		else
			{
		var icdCatCode=document.forms[0].medicalCat.value;
		var asriCode=document.forms[0].medicalSpclty.value;
		
		document.forms[0].ICDCatCode1.value=icdCatCode;
		//var schemeId=document.forms[0].scheme.value;

		var xmlhttp;
	    var url;

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
	  
	    xmlhttp.onreadystatechange=function()
	    {
	        if(xmlhttp.readyState==4)
	        {
	            var resultArray=xmlhttp.responseText;
	           

	            if(resultArray.trim()=="SessionExpired*"){
	            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	            else{
	            	if(resultArray!=null)
	            	{
	            		resultArray = resultArray.replace("[","");
	                	resultArray = resultArray.replace("@]*","");            
	                	var procedureList = resultArray.split("@,"); 
	                	if(procedureList.length>0)
	                	{  
	                		if(param==1)
	            			{
	                			document.forms[0].ICDProcName1.options.length=0;
	                        	document.forms[0].ICDProcName1.options[0]=new Option("---select---","-1");
	            			}
	            			for(var i = 0; i<procedureList.length;i++)
	                		{	
	                     		var arr=procedureList[i].split("~");
	                     		if(arr[1]!=null && arr[0]!=null)
	                     		{
	                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                         		if(param==1)
	                    	 		{
	                        	 		document.forms[0].ICDProcName1.options[i+1] =new Option(val1,val2);
	                    	 		}
	                     		}
	                		}
	            		}
	            	}
	        	}
	        }
	    }
	    	
		url = "./patientDetails.do?actionFlag=getProcByCatMed&callType=Ajax&lStrICDCatId="+icdCatCode+"&lStrAsriCode="+asriCode+"&hospId="+hospId+"&treatType="+treatType;
		xmlhttp.open("Post",url,false);
		xmlhttp.send(null);
			}
	}
	function getProcedureCodesMed1()
	{    var jq=jQuery.noConflict();
		document.getElementById("ICDProcCode1").value="";
		//var locSchemeId=document.getElementById("scheme").value;
		var asriCatCode=document.getElementById("asriCatCode1").value;
		if(document.getElementById("ICDProcName1").value=="-1")
			{
				document.getElementById("unitsTd").style.display="none";
				document.getElementById("unitsLabelTd").style.display="none";
				return false;
			}
		else
			{
				var icdProcCode=document.getElementById("ICDProcName1").value;
				document.getElementById("ICDProcCode1").value=icdProcCode;
				/*if(asriCatCode!=null  && ((locSchemeId !=null && locSchemeId=='CD202') || (locSchemeId==null || locSchemeId=='' || locSchemeId==' ') ))
					getTherapyInvestigations();*/
			}
		var anyOther=document.getElementById("ICDProcName1").value;
		var anyOtherText=document.getElementById("ICDProcName1").text;
		var x=jq("#ICDProcName1 option:selected").text();
		if(x.startsWith("Any other"))
		{
			jq("#otherId").show();
			jq("#otherRemarks").show();
		}else{
			jq("#otherId").hide();
			jq("#otherRemarks").hide();
		}

		
		
		
		
		
		
	}

	function chkSpecailChars(name,remarkLength)
	{
	    var chars="*|\":<>[]{}`\';$#%&"; //04
	    var message1='';
	    var msg ='';
	    var tbLen = name.replace(/\s+/g,'').replace(/\s+$/g,'') ;
	    if(tbLen.length == 0)
	    	{
	    	message1 ="Blank spaces are not allowed in remarks";
	    	}
	    if(name.charAt(0)==' ')
	    	{
	    	message1 ="Starting blank spaces are not allowed in remarks";
	    	}
	    if(remarkLength == 3000)
	    	{
	    	if(name.length >= 2999)
	    	{
	    	message1 = "Remarks length should not exceed " +remarkLength ;
	    	}
	    	}
	    else
	    	{
	    if(name.length >= remarkLength)
	    	{
	    	message1 = "Remarks length should not exceed " +remarkLength ;
	    	}
	    	}
	    var message='';
	    for (var i = 0; i < name.length; i++) 
	    {
	        if(chars.indexOf(name.charAt(i))!=-1)
	        {
	            message=" Remarks should not contain special characters ";
	            
	        }
	    }
	    return message+message1;
	}
	function fn_addMedicalDtls(caseId)
	{
		if(document.getElementById("medicalSpclty").value=="-1" || document.getElementById("medicalSpclty").value=="")
		{
			alert("Please select speciality");
			return false;
		}
		if(document.getElementById("medicalCat").value=="-1" || document.getElementById("medicalCat").value=="")
		{
			alert("Please select Category");
			return false;
		}
		if(document.getElementById("ICDProcName1").value=="-1" || document.getElementById("ICDProcName1").value=="")
		{
			alert("Please select procedure");
			return false;
		}

		if(document.getElementById("ipOtherRemarks")!=null && document.getElementById("otherRemarks").style.display!="none" && (document.getElementById("ipOtherRemarks").value==""||document.getElementById("ipOtherRemarks").value==null))
		{
			alert("Please enter reason for selecting any Other procedure");
			return false;
		
		}
		if(confirm('Do You want to change therapy details?'))
		 {
			 fn_addMedicalDtlscnfrm(caseId);
			
			
		 }
				
		
	}
	function fn_addMedicalDtlscnfrm(caseId){
		
		var medicalSpclty =  document.getElementById("medicalSpclty").value;
		var medicalCat = document.getElementById("medicalCat").value;
		var ICDProcName1 =  document.getElementById("ICDProcName1").value;
		var ipOtherRemarks=document.getElementById("ipOtherRemarks").value;
		
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
	 	url = "./preauthDetails.do?actionFlag=saveMedicalDetails&caseId="+caseId+"&medicalSpclty="+medicalSpclty+"&medicalCat="+medicalCat+"&ICDProcName1="+ICDProcName1+"&ipOtherRemarks="+ipOtherRemarks;    
	    xmlhttp.onreadystatechange=function()
	    {
	       if(xmlhttp.readyState==4)
	       {
	    	   var resultArray=xmlhttp.responseText;
	    	   if(resultArray.trim()=="SessionExpired*"){
	    		   jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
	            }
	            else
	            {
	            	
	            	var result = resultArray.split("*");
	            	
					if(result[0] != null && result[0] == 'Y'){
				
							alert("Therapy details changed successfully");
							
							parent.fn_preauthDetails();
							

						
					}
					
	                
                }
            }
       	}
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);
		
	}
	function fn_getDrugQuantNew()
	{ 
		var jq = jQuery.noConflict();
		//document.getElementById('drugPrice').disabled=true;
		var caseId= document.forms[0].caseId.value;
		document.getElementById('processImagetable').style.display="";
		var url = 'preauthDetails.do?actionFlag=getDrugQuantList';
		jq('#drugTableNew').hide();
		jq("#drugDivNew").hide();
		jq.post(url,{'caseId':caseId},function(data){
			  var jsonData=JSON.parse(data.replace("*",""));
			  jq('#drugTableNew > tbody').html("");
			  var len = jsonData.cardDetails.length;
			  if(len!=0){
					 document.getElementById("drugDivNew").html="";
			  for(var k=0;k<len;k++){
				    var x = "<tr>";
					x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugName+'</td>';
					x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].unitPrice+'</td>';
					x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugQuantity+'</td>';
					x +='<td class="tbcellBorder tbcellCss">'+jsonData.cardDetails[k].drugAmt+'</td>';
		/* 		x +='<td class="tbcellBorder tbcellCss"><button type="button" class="btn btn-danger"  onclick="fn_deleteDrugBreakUp(\''+jsonData.cardDetails[k].caseId+'\',\''+jsonData.cardDetails[k].drugId+'\',\''+jsonData.cardDetails[k].unitPrice+'\');" >Delete&nbsp;<span class="glyphicon glyphicon-remove-sign"></span></button></td>';
		*/ 			x += "</tr>";
					jq('#drugTableNew > tbody:last-child').append(x);
					jq('#drugTableNew').show();
					jq("#drugDivNew").show();
			  }
			 } else{
				 jq('#drugTableNew').show();
					jq("#drugDivNew").show();
				 document.getElementById("drugDivNew").innerHTML="<b class='emptyData'><---------No Records Found---------></b>";
			 }
		/* 	  
		 var drugsLt=jsonData.drugsList;
			  
			  for(var i = 0; i<drugsLt.length;i++)
				{	
					var arr=drugsLt[i].split("~");
				
					if(arr[1]!=null && arr[0]!=null)
					{
						var val1 = arr[1].replace(/^\s+|\s+$/g,"");
						var val2 = arr[0].replace(/^\s+|\s+$/g,"");
					
						document.preauthDetailsForm.drugId.options[i+1] =new Option(val1,val2);				
						
					}
				} */
		},'text')
		.always(function(){document.getElementById('processImagetable').style.display="none";})
		.fail(function(){alert('Network Error. Please try again');});


		}
	function fn_openAtachment1()
	{  
		 document.preauthDetailsForm.action= "preauthDetails.do?actionFlag=viewAttachment";
	     document.preauthDetailsForm.submit(); 

	}	
	function getMedicalCategoryList2(id)
	{
		var jq = jQuery.noConflict();
		var critical='NA';
		var hospId = document.forms[0].hospitalId.value;
		var treatType=ipFlag;
		document.getElementById("asriCatCode1").value="";
	    var asriCatCode1=document.getElementById("medicalSpclty").value;
		if(document.forms[0].ICDCatCode1!=null)document.forms[0].ICDCatCode1.value="";
		if(document.forms[0].ICDProcCode1!=null)	document.forms[0].ICDProcCode1.value="";
		if(document.forms[0].ICDProcName1!=null)	document.forms[0].ICDProcName1.options.length=1;
		jq("#otherId").hide();
		jq("#otherRemarks").hide();  
		
			var xmlhttp;
			var url;
			var medicalSpclty = document.getElementById("medicalSpclty").value;
			
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
			 alert("Your Browser Does Not Support XMLHTTP!");
			}
			 xmlhttp.onreadystatechange=function()
			{
				 if(xmlhttp.readyState==4)
			        {
			            var resultArray=xmlhttp.responseText;
			            if(resultArray.trim()=="SessionExpired*"){
			            	jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
			            }
			            else
			            {
			            	if(resultArray!=null)
			            	{   
			            		var totalList = resultArray.split("*");
							
							    var categList = totalList[0];
							    var docList = totalList[1];
								categList = categList.replace("[","");categList = categList.replace("@]","");
								if(docList!=null)
									{
								docList = docList.replace("[","");
								docList = docList.replace("@]","");  
								var doctorList = docList.split("@,"); 
							        }
								var categoryList = categList.split("@,"); 
								
								if(categoryList.length>0)
								{  
									document.getElementById("medicalCat").options.length=0;
									document.getElementById("medicalCat").options[0]=new Option("---select---","-1");
									for(var i = 0; i<categoryList.length;i++)
									{	
										var arr=categoryList[i].split("~");
										if(arr[1]!=null && arr[0]!=null)
										{
											var val1 = arr[1].replace(/^\s+|\s+$/g,"");
											var val2 = arr[0].replace(/^\s+|\s+$/g,"");
											document.getElementById("medicalCat").options[i+1] =new Option(val1,val2);
										}
									}
								}
							
			            		}
			        	}
			        }
			}
			 document.getElementById("asriCatCode1").value=asriCatCode1;
			var	url = "./patientDetails.do?actionFlag=getICDCatByAsriCodeMed&callType=Ajax&lStrAsriCatId="+medicalSpclty+"&hospId="+hospId+"&treatType="+treatType;
			 xmlhttp.open("Post",url,false);
				xmlhttp.send(null);
	}
	