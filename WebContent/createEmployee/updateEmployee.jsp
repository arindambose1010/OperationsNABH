<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
      <%@ include file="/common/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:setLocale value='en_US'/>

<html>
<head>

<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Update Employee</title>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-select.min.css">
<link href="bootstrap/css/formValidation.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/fileinput.min.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/themes/navyblue/theme.css" rel="stylesheet" type="text/css" media="screen">
<link href="bootstrap/css/font-awesome.css" rel="stylesheet">
<script  src="bootstrap/js/modernizr.min.js"></script>
<script  src="bootstrap/js/css3-mediaqueries.js"></script> 
<script  src="bootstrap/js/html5.js"></script>
<script src="bootstrap/js/respond.min.js"></script>
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/formValidation.min.js"></script>
<script src="bootstrap/validator/bootstrap.min.js"></script>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
 <%@ include file="/common/includeBootstrapCalendar.jsp"%> 

<style type="text/css">
.bottom-margin {
    margin: 0px 0px 3px 0px !important;
}
body{font-family:Arial, Helvetica, sans-serif;font-size:13px;color:#333;}
  #remarksDiv {
width:100%;
height:540px;
overflow:auto;
background:white;
}
table.center {
    margin-left:auto; 
    margin-right:auto;
  }
  
   .tbcellBorder2{
  margin:2px; 
  padding:8px;
  border-right:2px solid #ffff;
  }  
   .modal-dialog{
width:950px;
} 

.tdclass2{
word-break: break-word; 

}

.newDivClass
 {
 	margin-top:5px;
 	
 } 

.but {
	background-color:<%=themeColour%>  !important;
}
 .but{
	border-radius:16px;
}
 .form-group{
margin-bottom:5px !important;
}
 
.text-center
{
    text-align: center !important;
}
.ui-autocomplete-input {
    width: 15em;
}
</style>

<script>
 var date = new Date();
var m = date.getMonth(), d = date.getDate(), y = date.getFullYear();

$(document).ready(function() {
	
							 	 $('#empDob').datepicker({
	 						 	defaultDate: "+1w",
							 	endDate: new Date(y, m, d),
								todayHighlight: true,
								autoclose: true,
								format:'dd-mm-yyyy'
     							})
     							.on('changeDate', function(e) {
         						// Revalidate the date field
         						$('#createEmployeeForm').formValidation('revalidateField', 'empDob');
    							 });
	
	 							$('#empDoj').datepicker({
		 						defaultDate: "+1w",
								endDate: new Date(y, m, d),
								todayHighlight: true,
								autoclose: true,
								format:'dd-mm-yyyy'
	     						})
	     						.on('changeDate', function(e) {
	         					// Revalidate the date field
	        					 $('#createEmployeeForm').formValidation('revalidateField', 'empDoj');
	     						}); 
	
    							$('#createEmployeeForm').formValidation({
    							framework: 'bootstrap',
        						icon: {
            							valid: 'glyphicon glyphicon-ok',
            							invalid: 'glyphicon glyphicon-remove',
           								validating: 'glyphicon glyphicon-refresh'
        							},
        						fields: {
        							fName: {
                						validators: {
                    						notEmpty: {
                       						 message: 'The first name is required'
                    						},
                						}
            						},
            						lName: {
            			                validators: {
            			                    notEmpty: {
            			                        message: 'The last name is required'
            			                    },
            			           	 		different: {
            			                	field: 'fName',
            			                	message: 'The last name cannot be the same as firstname'
            			            		}
            			                }
            			            },
            			            gender: {
            			                validators: {
            			                    notEmpty: {
            			                        message: 'The gender is required'
            			                    }
            			                }
            			            },
            		            empAddress: {
            		        		validators: {
            		        			notEmpty:	{
            		        				message: 'Employee Address is required'
            		        			},
            		        			
            		        		}
            		        	},
            		        	postalCode: {
            		        		validators: {
            		        			notEmpty: {
            		        				message: 'Postal Code should not be empty'
            		        			},
            		        			regexp:	{
            		        				regexp: /^[0-9]+$/,
            		        				message: 'Postal Code can only consists of numbers'
            		        			},
            		        			regexp: {
            		        				 regexp: /^\d{6}$/,
            		        				 message: 'Postal Code should contain only six digits'
            		        			}
            		        		}
            		        	},
            		        	mobNumber :{
            		            	validators: {
            		                    notEmpty: {
            		                        message: 'The mobile number is required'
            		                    },
            		                    number: {
            		                        message: 'The input is not a valid mobile number'
            		                    },
            		                    stringLength: {
            		                        min: 10,
            		                        max: 10,
            		                        message: 'The mobile number must be 10 digits.'
            		                    }
            		                }
            		            },
            		             email: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The email address is required'
            		                    },
            		                    emailAddress: {
            		                        message: 'The input is not a valid email address'
            		                    }
            		                }
            		            }, 
            		            accHolderName: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^[a-zA-Z ]+$/,
            		                        message: 'The account holder name can only consist of alphabets'
            		                    }
            		                }
            		            },
            		            panNumber: {
            		                validators: {
            		                    regexp: {
            		                        regexp: /^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$/,

            		                        message: 'First 5 should be alphabets followed by 4 numbers and an alphabet'
            		                    },
            		                    stringLength: {
            		                        min: 10,
            		                        max: 10,
            		                        message: 'The PAN number must be 10 digits.'
            		                    }
            		                }
            		            },
            		            state: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'Please select a state'
            		                    }
            		                }
            		            } ,
            		            district:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select a district'
            		            		}
            		            	}
            		            } ,
            		            distType:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select district type'
            		            		}
            		            	}
            		            },
            		            mandals:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select mandal or municipality'
            		            	
            		            		}
            		            	}
            		            },
            		            empDob: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The date of birth is required'
            		                    }
            		                   
            		                }
            		            },
            		            inService: {
        			                validators: {
        			                    notEmpty: {
        			                        message: 'The In Service  is required'
        			                    }
        			                }
        			            },
            		            empDoj: {
            		                validators: {
            		                    notEmpty: {
            		                        message: 'The date of joining is required'
            		                    }
            		                   
            		                }
            		            },
            		            villages:{
            		            	validators:{
            		            		notEmpty:{
            		            			message:'please select city or village'
            		            		}
            		            	}
            		            } ,
            		            accNumber: {
            		            	validators: {
            		        			regexp:	{
            		        				regexp: /^[0-9]+$/,
            		        				message: 'Account Number can only consists of numbers'
            		        				}
            		        		}
            		        	}
            		            
        				
        				
        				}
        				
    				});
			});
			

$(function() { 
	   $("#search").click(function(){
	      $(this).button('loading').delay(1000).queue(function() {
	         
	      });        
	   });
	   $("#reset").click(function(){
		   document.getElementById("empNumber").value='';
	   });
	});

function fn_showAlert()
{
	var successflag="${success}";
 	if(successflag=="Success")
	{
		jqueryAlertMsg("Alert","Employee Updated Successfully");
		parent.parent.fn_loadSearchEmp();
	}

}

function validateAlphabetic(arg) { 
	var value = document.getElementById(arg).value;
	var len = value.length; 
	var var_length = value.length;
	var str = /\W/g;
	var space=/\s/g;
	var pattern=/^[A-Z a-z.]+$/; 	
	var dot=/^[.]+$/;
	if (value != ""){ 
               
	if (value.match(dot)) {
				var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert","Plaese enter valid Name", fr);
				document.getElementById(arg).value = "";
				return false;
			}
			if (!(value.match(pattern))) {
				var fr = partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert","Only alphabets and . are allowed in Name" , fr);
				document.getElementById(arg).value = "";
				return false;
			} else if (value.charCodeAt(0) == 32) {
				var fr = partial(focusBox, document.getElementById(arg));
					jqueryAlertMsg("Alert","Name cannot start with white space", fr);
				document.getElementById(arg).value = "";
				return false;
			} 

			var j = 0;
			for (i = 0; i < var_length - 1; i++) {

				symbol1 = value.charAt(i);
				symbol2 = value.charAt(i + 1);

				if ((symbol1.match(str, space) && symbol2.match(str, space))
						|| (value.charAt(var_length - 1).match(str, space))
						|| (value.charAt(0).match(str, space))) {
					var fr = partial(focusBox, document
							.getElementById(arg));
					jqueryAlertMsg('Alert'," More than one white space or dot is not allowed between words ",fr);
					document.getElementById(arg).value = "";
					return false;
				}

			}

		}
	}
function getAge(arg) 
{
var dateString=document.getElementById(arg).value;
var now = new Date();
var yearNow = now.getYear();
var monthNow = now.getMonth();
var dateNow = now.getDate();
var dob = new Date(dateString.substring(6,10),dateString.substring(0,2)-1,dateString.substring(3,5));
var yearDob = dob.getYear();
var monthDob = dob.getMonth();
var dateDob = dob.getDate();
var age = {};
var ageString = "";
var yearString = "";
var monthString = "";
var dayString = "";

yearAge = yearNow - yearDob;
if (monthNow >= monthDob)
	var monthAge = monthNow - monthDob;
else {
	yearAge--;
	var monthAge = 12 + monthNow -monthDob;
}

if (dateNow >= dateDob)
	var dateAge = dateNow - dateDob;
else {
	monthAge--;
    var dateAge = 31 + dateNow - dateDob;
    if (monthAge < 0) {
      	monthAge = 11;
      	yearAge--;
    }
  }

  age = {
      years: yearAge,
      months: monthAge,
      days: dateAge
      };
    if(age.years<18)
        {
    	 var fr=partial(focusBox, document.getElementById(arg));
         jqueryAlertMsg("Alert","Employee age must be greater than 18",fr);
         document.getElementById(arg).value="";	
         return ; 
        }
}

function fnValidatePAN(Obj) {
    var pattern=/^([0-9a-zA-Z])+$/;
    var alpha=/^([a-zA-Z])+$/;
    var num=/^([0-9])+$/;
   var ObjVal=document.getElementById(Obj).value;
  
    if ( ObjVal!= "") {
    	
       if(!ObjVal.match(pattern))
           {
    	   var fr=partial(focusBox,document.getElementById(Obj));
	        jqueryAlertMsgTop("Alert","Please enter valid Pan Card Number",fr);
        document.getElementById(Obj).value="";
        return false;
           }
       if((!ObjVal.match(alpha) && ObjVal.match(num)) || (ObjVal.match(alpha) && !ObjVal.match(num)))
       {
           
	   var fr=partial(focusBox,document.getElementById(Obj));
        jqueryAlertMsgTop("Alert","Pan number must contains both alphabets and numbers ",fr);
    document.getElementById(Obj).value="";
    return false;
       }
       if(ObjVal.length<10)
           {
    	    var fr=partial(focusBox,document.getElementById(Obj));
	         jqueryAlertMsgTop("Alert","Pan card number length must be 10 ",fr);
       document.getElementById(Obj).value="";
       return false;
           }
    }
}
function LoginName(arg) 
{
	var loginName = document.getElementById(arg).value;
	
	 if (window.XMLHttpRequest) 
	 {
				xmlhttp = new XMLHttpRequest();
	 }
	 else if (window.ActiveXObject) 
	 		{
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			} else 
			{
				alert("Your browser does not support XMLHTTP!");
			}

		var url = '/<%=context%>/createEmployee.do?actionFlag=checkLoginName&loginName='+loginName;
		xmlhttp.open("Post",url,true);
		xmlhttp.send(null);				      
		  
	xmlhttp.onreadystatechange=function()
	{
		if(xmlhttp.readyState==4)
		{
		var resultArray=xmlhttp.responseText;
		if(resultArray =="SessionExpired")
		{
			alert("Session has been expired");
			parent.location.href="index.jsp";
		}
		else
		{  
			if(resultArray!=null)
			{   
				if(resultArray=="true")
				{
				var fr=partial(focusBox, document.getElementById(arg));
				jqueryAlertMsg("Alert","Employee already exists with the given LoginName "+loginName,fr);
				document.getElementById(arg).value="";	
				}
			else
			return;
			}
		}
	}
  }
}
function UserNo(arg)
{
var userNo = document.getElementById(arg).value; 
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
   alert("Your browser does not support XMLHTTP!");
  }

	    var url='/<%=context%>/createEmployee.do?actionFlag=checkUserNo&userNo='+userNo;
		  xmlhttp.open("Post",url,true);
		  xmlhttp.send(null);				      
		  
  xmlhttp.onreadystatechange=function()
  {
  if(xmlhttp.readyState==4)
  {
	  
	  var resultArray=xmlhttp.responseText;
	 if(resultArray =="SessionExpired")
		{
			alert("Session has been expired");
			parent.location.href="index.jsp";
		}
		else
		{  
	if(resultArray!=null)
    {   if(resultArray=="true")
        {
     	   var fr=partial(focusBox, document.getElementById(arg));
    	   jqueryAlertMsg("Alert","Employee already exists with the given Employee Number "+userNo,fr);
    	   document.getElementById(arg).value="";	
           }
       else
           return;
    }
	}
  }
  }
}
function fn_getDistricts()
{
	
//document.getElementById('mandalTR').style.display="none";

var xmlhttp;
var url;
var state=document.getElementById('state').value;

url= '/<%=context%>/createEmployee.do?actionFlag=getDistrictList&state='+state;
if (window.XMLHttpRequest){
 xmlhttp=new XMLHttpRequest();
}
else if (window.ActiveXObject){		
 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
else{
 alert("Your Browser Does Not Support XMLHTTP!");
}
xmlhttp.onreadystatechange=function(){
    if(xmlhttp.readyState==4){	
    	document.forms[0].district.options[0]=new Option('-------- Select --------',"");
        var resultArray=xmlhttp.responseText;
    
        if(resultArray!= null){
        	 resultArray2 = resultArray.replace("[","");
	         resultArray2 = resultArray2.replace("]","");            
	         var addList = resultArray2.split(", @");
	         if(addList.length>0){   
	                for(var i = 0; i<addList.length;i++){	
	                    var arr=addList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null){
	                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                     	 document.forms[0].district.options[i+1] =new Option(val1,val2);       
					   } 
	                }
	            }
        	}
    }			
}
xmlhttp.open("Post",url,true);
xmlhttp.send(null);
document.getElementById('district').options.length=0;
$('#district-input').val('');
}
function fn_getVacantPosition()
{
	document.getElementById('vacPos1').style.display="";
	document.getElementById('vacPos2').style.display="";	
	document.getElementById('level1').style.display="none";
	document.getElementById('level2').style.display="none";
	document.getElementById('vacPosTR').style.display="none";
	var xmlhttp;
	var url;
	var deptId=document.getElementById('deptName').value;
	url= '/<%=context%>/createEmployee.do?actionFlag=getVacantPosition&deptId='+deptId;
	if (window.XMLHttpRequest){
	xmlhttp=new XMLHttpRequest();
	}
	else if (window.ActiveXObject){		
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	else{
		alert("Your Browser Does Not Support XMLHTTP!");
	}
xmlhttp.onreadystatechange=function(){
    if(xmlhttp.readyState==4){	
    	document.forms[0].vacPosName.options[0]=new Option('-------- Select --------',"");
        var resultArray=xmlhttp.responseText;
        if(resultArray!= null){
        	 resultArray2 = resultArray.replace("[","");
	         resultArray2 = resultArray2.replace("]","");            
	         var addList = resultArray2.split(", @");
	         if(addList.length>0){   
	                for(var i = 0; i<addList.length;i++){	
	                    var arr=addList[i].split("~");
	                     if(arr[1]!=null && arr[0]!=null){
	                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                     	 document.forms[0].vacPosName.options[i+1] =new Option(val1,val2);       
					   } 
	                }
	            }
        	}
			if(resultArray=="[]")
			{
				document.forms[0].vacPosName.options[0]=new Option('No Vacant Position',"NA");
				var fr=partial(focusBox,document.getElementById('vacPosName'));
				jqueryAlertMsg("Alert","Please create a new vacant position for creating new employee ",fr);
				return false;
			}
		}			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	document.getElementById('vacPosName').options.length=0;
	$('#vacPosName-input').val('');	
}
function fn_getVacantPositionDetails()
{
	var xmlhttp;
	var url;
	var deptId=document.getElementById('deptName').value;
	var vacPosName=document.getElementById('vacPosName').value;
	if(vacPosName!="NA"){
	 document.getElementById('level1').style.display="";
		document.getElementById('level2').style.display="";
		document.getElementById('vacPosTR').style.display="";
		 document.forms[0].groupName.value="";
url= '/<%=context%>/createEmployee.do?actionFlag=getVacantPosDetails&dept='+deptId+'&vacPosName='+vacPosName;
if (window.XMLHttpRequest){
 xmlhttp=new XMLHttpRequest();
}
else if (window.ActiveXObject){		
 xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
}
else{
 alert("Your Browser Does Not Support XMLHTTP!");
}
xmlhttp.onreadystatechange=function(){
    if(xmlhttp.readyState==4){
	    
        var resultArray=xmlhttp.responseText;
        if(resultArray!= null){
        	 resultArray2 = resultArray.replace("[","");
	         resultArray2 = resultArray2.replace("]","");
	         resultArray2=resultArray2.split("^");
        	var vacPosDtls = resultArray2[0].split("~");
        	document.getElementById('level').value=vacPosDtls[0];
        	document.getElementById('desgName').value=vacPosDtls[1];
        	document.getElementById('repPerson').value=vacPosDtls[2];
        	
        	
        	var groupNames=resultArray2[1].split("~");
        	
        	if(groupNames.length>0){   
                for(var i = 0; i<groupNames.length;i++){	
                     if(groupNames[i]!="null"){
                         var val1 = groupNames[i].replace(/^\s+|\s+$/g,"");
                         if(document.forms[0].groupName.value!=null)
                     	 document.forms[0].groupName.value +=val1+"\n";  
                         else
    	                   document.forms[0].groupName.value=val1;
				   } 
                     else
                    	 document.forms[0].groupName.value="No Groups Found";
                }
            }
        	
        	}
    }			
}
xmlhttp.open("Post",url,true);
xmlhttp.send(null);
 }	
}
function fn_getList()
{	
document.getElementById('mandals').options.length=0;
document.getElementById('villages').options.length=0;
$('#mandals-input').val('');
$('#villages-input').val('');
document.forms[0].distType[0].checked=false;
document.forms[0].distType[1].checked=false;
document.getElementById('mandalTR').style.display="";
document.getElementById('distType1').style.display="";
document.getElementById('distType2').style.display="";
document.getElementById('mandalsTD1').style.display="none";
document.getElementById('mandalsTD2').style.display="none";
document.getElementById('villagesTD1').style.display="none";
document.getElementById('villagesTD2').style.display="none";
}

function fn_getMandals(value){
	
	/* var radioValue = document.getElementsByTagName('distType');
	  alert(radioValue);
	   */
 var distId=document.getElementById('district').value;
 var mandals=document.getElementById('mandals').value;
 document.getElementById('mandalsTD1').style.display="";
 document.getElementById('mandalsTD2').style.display="";
 document.getElementById('villagesTD1').style.display="none";
	//document.getElementById('villagesTD2').style.display="none";

if(value=="Mdl")
    {
	document.getElementById('mandalsTD1').innerHTML='Mandals <span style="color: #ff0000"> *</span>';
	document.getElementById('mandalsTD2').title="Select Mandal";
	//document.getElementById('villagesTD1').innerHTML="Village";
	//document.getElementById('villagesTD2').title="Select Villages";
    }
if(value=="Mpl")
{
document.getElementById('mandalsTD1').innerHTML='Municipalities <span style="color: #ff0000"> *</span>';
document.getElementById('mandalsTD1').title="Select Municipalities";
//document.getElementById('villagesTD1').innerHTML="City";
//document.getElementById('villagesTD2').title="Select City";
}
	
 var mand=value;
  	var xmlhttp;
	var url;
	url= '/<%=context%>/createEmployee.do?actionFlag=getMandalList&mandal='+mand+'&distId='+distId;
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
	    	document.forms[0].mandals.options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        
	        if(resultArray!= null)
	        	{
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0)
		            {   
		                for(var i = 0; i<addList.length;i++)
		                {	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null)
		                     {
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].mandals.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
	        	}
	        if(resultArray=="[]")
			{
			document.forms[0].mandals.options[0]=new Option('No Mandal/Municipality',"NA");
			}
	        
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);

	document.getElementById('mandals').options.length=0;
	$('#mandals-input').val('');

}
function fn_getVillages(){
$('#villages-input').val('');
document.getElementById('villagesTD1').style.display="";
//document.getElementById('villagesTD2').style.display="";
 document.getElementById('villages').length=0;
 if(document.forms[0].distType[0].checked==true){
	value="Mdl";
  }
 if(document.forms[0].distType[1].checked==true){
    value="Mpl";
	 }
 var mand=value;
 var mandal=document.getElementById('mandals').value;
 	var xmlhttp;
	var url;
	url= '/<%=context%>/createEmployee.do?actionFlag=getMandalList&mandal='+mand+'&mmpty='+mandal;
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
	    	document.forms[0].villages.options[0]=new Option('-------- Select --------',"");
	        var resultArray=xmlhttp.responseText;
	        if(resultArray!= null)
	        	{
	        	 resultArray2 = resultArray.replace("[","");
		         resultArray2 = resultArray2.replace("]","");            
		         var addList = resultArray2.split(", @");
		         if(addList.length>0)
		            {   
		                for(var i = 0; i<addList.length;i++)
		                {	
		                    var arr=addList[i].split("~");
		                     if(arr[1]!=null && arr[0]!=null)
		                     {
		                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
		                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
		                     	 document.forms[0].villages.options[i+1] =new Option(val1,val2);       
						   } 
		                }
		            }
		            
	        	}
	        if(resultArray=="[]")
			{
			
			document.forms[0].villages.options[0]=new Option('No City/Village',"NA");
			}
	    }			
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	    //Ajax end
}
function getIFSCCode(arg){

	document.getElementById("ifscCode").disabled=false;
	document.getElementById("accHolderName").disabled=false;
	document.getElementById("accNumber").disabled=false;
	document.getElementById("panCardName").disabled=false;
	document.getElementById("panNumber").disabled=false;

	   var xmlhttp;
 var url;
 var resultArray;
 var IFSCCode;
	   
 var bankCode=document.getElementById(arg).value;  

 if(bankCode=="Select")
	 {
	
	 document.getElementById('ifscCode').value="";
	 document.getElementById("accHolderName").value="";
		document.getElementById("accNumber").value="";
		document.getElementById("panCardName").value="";
		document.getElementById("panNumber").value="";
	 document.getElementById("ifscCode").disabled=true;
		document.getElementById("accHolderName").disabled=true;
		document.getElementById("accNumber").disabled=true;
		document.getElementById("panCardName").disabled=true;
		document.getElementById("panNumber").disabled=true;
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
 	 jqueryAlertMsg("Alert","Your browser does not support XMLHTTP!");
  }
  xmlhttp.onreadystatechange=function()
  {
      if(xmlhttp.readyState==4)
      {
          resultArray=xmlhttp.responseText;
  				IFSCCode = resultArray.replace(/^\s+|\s+$/g,"");
  				if(IFSCCode!=null)
				document.getElementById('ifscCode').value = IFSCCode;
					
      }
  }  
 
   url= 'createEmployee.do?actionFlag=GETIFSCCODE&bankCode='+bankCode;
   xmlhttp.open("Post",url,true);
   xmlhttp.send(null);

} 
}
function addTooltip(id) {
var numOptions = document.getElementById(id).options.length;
for ( var i = 0; i < numOptions; i++)
	document.getElementById(id).options[i].title = document
			.getElementById(id).options[i].text;

}
function updateEmployee()
{
	 $('#createEmployeeForm').formValidation().formValidation('validate');
		var x=$('#createEmployeeForm').data('formValidation').isValid();		
		 var inServiceFlag="";
		 for (var i=0; i<document.forms[0].elements.length; i++)
			{	
				var type = document.forms[0].elements[i].type;
				var id=document.forms[0].elements[i].id;
				
				if (type=="radio" && id=="inService")
				{	
					if(document.forms[0].elements[i].checked)
					{	
						inServiceFlag=document.forms[0].elements[i].value;
					}
				}
			}
			
     if(x)
         {
    	 var fr=partial(onSuccessSave);
    	 if(inServiceFlag=="N") 
			{
    		 jqueryConfirmMsg("Confirm","Are you sure you want to deactivate the employee?",fr);
			}
    	 else
    		 {
    		 jqueryConfirmMsg("Confirm","Are you sure you want to update employee details?",fr);
    		 }
    	
        } 

}
function loadEmployee()
{
	var empNumber=document.getElementById('empNumber1').value;
	if(empNumber=="")
	{
	var fr=partial(focusBox,document.getElementById('empNumber1'));
	jqueryAlertMsg("Alert","Please enter Employee Number to update employee",fr);
	return false;
	}
	else
	{
	 document.forms[0].action = 'createEmployee.do?actionFlag=loadEmployee';
	 document.forms[0].submit();
	 document.forms[0].search.disabled = true;
	 document.forms[0].update.disabled = false;
	}
}
function closeIframe() {
	document.getElementById("updateEmp2").style.display = "none";
}

function resetPage()
{
	  
	    //document.getElementById('fName').value="";
	    //document.getElementById("lName").value="";
	    document.forms[0].gender[0].checked=false;
		document.forms[0].gender[1].checked=false;
		//eraser('empDob');eraser('empDoj');
	    document.getElementById('empDoj').value="";
		document.getElementById('empDob').value="";
	    document.getElementById('empAddress').value="";
	    document.getElementById('villages').value="";


	    document.getElementById('state').value=""; $('#state-input').val('');
	    document.getElementById('district').value="";$('#district-input').val('');
	    document.getElementById('state').value=""; $('#state-input').val('');
	    document.getElementById('district').value="";$('#district-input').val('');
	    //document.getElementById('mandalTR').style.display="none";
	    document.getElementById('postalCode').value="";
	    document.getElementById('mobNumber').value="";
	    document.getElementById('email').value="";
	    document.getElementById('vacPosName').value="";
	    document.forms[0].inService[0].checked=false;
		document.forms[0].inService[1].checked=false;
		 document.getElementById('mandals').value="";$('#mandals-input').val('');
		    document.forms[0].distType[0].checked=false;
			document.forms[0].distType[1].checked=false;
		    document.getElementById('ifscCode').value="";
		    document.getElementById('accHolderName').value="";
		    document.getElementById('accNumber').value="";
		    document.getElementById('panCardName').value="";
		    document.getElementById('panNumber').value="";
			 document.getElementById('bankName').value="";$('#bankName-input').val('');


		  
}
function addTooltip(id) {
	var numOptions = document.getElementById(id).options.length;
	for ( var i = 0; i < numOptions; i++)
		document.getElementById(id).options[i].title = document
				.getElementById(id).options[i].text;

}
function partial(func /*, 0..n args */) {
	var args = new Array();
	for ( var i = 1; i < arguments.length; i++) {
		args.push(arguments[i]);
	}
	return function() {
		var allArguments = args.concat(Array.prototype.slice
				.call(arguments));
		return func.apply(this, allArguments);
	};
}
function fn_loadImage() {
	//document.getElementById('processImagetable').style.display = "";
}

function onSuccessSave()
	{
	 fn_loadImage();
	 document.forms[0].update.disabled = true;
	 document.getElementById("deptName").disabled=false;
	 document.getElementById("vacPosName").disabled=false;
	 document.getElementById("loginName").disabled=false;
	
	 document.forms[0].action = 'createEmployee.do?actionFlag=saveCreatePVTEmp&editFlag=true';
	 document.forms[0].submit();
	 
	}


function fn_reset()
{
	document.getElementById("empNumber").value="";
	}
</script>
</head>

<body onload="fn_showAlert()">
	<html:form action="/createEmployee.do" method="POST" enctype="multipart/form-data" styleId="createEmployeeForm">
	
<%-- 			<div class="container-fluid center-block" id="updateEmp1" style="padding-left:35%">
				<div align="right">		
					<span style="color: #ff0000"> *-Mandatory Fields</span>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss" align="center">
	           				Enter Employee Number<font color="Red">*</font>
	           				<html:text title="Enter Employee Number" styleClass="form-control" property="empNumber" styleId="empNumber1" name="createEmployeeForm"/>
	           	</div>
           	</div>
           	<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center"><br/>
					<button id="search" class="btn but" data-loading-text="Loading..." type="button" onclick="loadEmployee()">
						 <span class="glyphicon glyphicon-search"></span>Search </button> 
					<!-- <button class="btn but" id="reset" name="reset"  type="reset"  title="Reset" onclick="fn_reset()">Reset</button> 	 -->
			</div>	 --%>
		
				<div class="container" id="updateEmp2">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group"><br/>
						<div align="center"><b>Personal Details</b></div>
						</div>

                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						First Name<font color="Red">*</font>
           						<html:text title="Enter FirstName of Employee" styleClass="form-control" readonly="true"  property="fName" styleId="fName" maxlength="50" name="createEmployeeForm"/>
           					</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Last Name<font color="Red">*</font>
           						<html:text title="Enter LastName of Employee" styleClass="form-control" disabled="true" property="lName" styleId="lName" maxlength="50" name="createEmployeeForm"/>
           					</div> 
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Employee Number<font color="Red">*</font>
           						<html:text styleClass="form-control" property="loginName" maxlength="25" readonly="true"  title="Enter login Name" styleId="loginName" name="createEmployeeForm"/>		
           					</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Gender<font color="Red">*</font>
           						<br>
           						<html:radio title="Select Gender of Employee" name="createEmployeeForm" property = "gender"  styleId="gender"  value = "M"/>Male &nbsp;&nbsp;
								<html:radio title="Select Gender of Employee" name="createEmployeeForm" property = "gender"  styleId="gender"  value = "F"/>Female
							</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Date of Birth<font color="Red">*</font>		  		
           						<html:text styleClass="form-control" property="empDob" title="Select Employee DOB" styleId="empDob" name="createEmployeeForm" onchange="getAge('empDob')"/>
           					</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Date of Joining<font color="Red">*</font>	
           						<html:text styleClass="form-control" property="empDoj" title="Select Employee DOJ"  styleId="empDoj" name="createEmployeeForm"/>
           					</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Address<font color="Red">*</font>	
           						<html:text  title="Enter Address of Employee" styleClass="form-control" property="empAddress" styleId="empAddress" name="createEmployeeForm"/>
           					</div>
           				
           					
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						Country	
           						<html:text styleClass="form-control" property="country" title="Country" styleId="country" name="createEmployeeForm" value="India" readonly="true"/>
           					</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						State<font color="Red">*</font>	
           						<br>	
           						<html:select styleClass="form-control" property="state" name="createEmployeeForm" styleId="state" onmouseover="addTooltip('state');"  onchange="fn_getDistricts()" title="Select State">
								<option value="">--------Select---------</option>
								<logic:notEmpty name="stateList">
						 		<html:options collection="stateList" labelProperty="VALUE" property="ID"/>
						 		</logic:notEmpty>
								</html:select>	 	  
							</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           						District<font color="Red">*</font>	
           						<br>
           						<html:select styleClass="form-control" property="district" name="createEmployeeForm" styleId="district" onmouseover="addTooltip('district');" title="Select District" onchange="javascript:fn_getList()">
								<option value="">--------Select---------</option>
								<logic:notEmpty name="distList">
								
						 		<html:options collection="distList" labelProperty="DISTRICTNAME" property="LOCID"/>
						 		</logic:notEmpty>
								</html:select>
								</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
                 				<div id="distType1">Mandal/Municipality<font color="Red">*</font></div>
								<div class="tbcellBorder" id="distType2">
									<html:radio title="Select Mandal/Municipality" name="createEmployeeForm" property = "distType"  styleId="distType" onclick="fn_getMandals('Mdl')" value = "Mdl"/>Mandal &nbsp;&nbsp;
									<html:radio title="Select Mandal/Municipality" name="createEmployeeForm" property = "distType"  styleId="distType" onclick="fn_getMandals('Mpl')"  value = "Mpl"/>Municipality
								</div>
								</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
                  				<div id="mandalsTD1">Mandals/Municipalities <font color="Red">*</font></div>
						 		<div id="mandalsTD2">
						 			<html:select styleClass="form-control" property="mandals" name="createEmployeeForm" styleId="mandals" onmouseover="addTooltip('mandals');"  onchange="javascript:fn_getVillages()" title="Select Mandal/Municipality">
									<option value="">--------Select---------</option>
									<logic:notEmpty name="mdlList">
						 			<html:options collection="mdlList" labelProperty="VALUE" property="ID"/>
						 			</logic:notEmpty>
									</html:select>
								</div>
							</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
                 		 		<div id="villagesTD1">City/Village <font color="Red">*</font>
						 			<html:select styleClass="form-control" property="villages" name="createEmployeeForm" styleId="villages" onmouseover="addTooltip('villages');" title="Select City/Village/Town">
									<option value="">--------Select---------</option>
									<logic:notEmpty name="vilList">
				
						 			<html:options collection="vilList" labelProperty="VALUE" property="ID"/>
						 			</logic:notEmpty>
									</html:select>
								</div>
								</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           							Postal Code<font color="Red">*</font>
           							<html:text styleClass="form-control" maxlength="6" property="postalCode" title="Enter Postal Code" styleId="postalCode" name="createEmployeeForm"/>
							</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           							Mobile Number<font color="Red">*</font>
           							<div class="input-group">
									<div class="input-group-addon">+91</div>
									<html:text styleClass="form-control" maxlength="10" property="mobNumber" title="Enter Mobile Number" styleId="mobNumber" name="createEmployeeForm"/>
									</div>
							</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           							E-Mail<font color="Red">*</font>
           							<html:text styleClass="form-control" property="email" title="Enter Email" styleId="email" name="createEmployeeForm" onchange="validateEmailId('email');"/>
           					</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								In Service <font color="Red">*</font>
								<br>
								<html:radio title="Select In Service" name="createEmployeeForm" property = "inService"  styleId="inService"  value = "Y" />Yes &nbsp;&nbsp;
								<html:radio title="Select In Service" name="createEmployeeForm" property = "inService"  styleId="inService"  value = "N" />No	
							</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group"><br/>
						<div align="center"><b>Other Details</b></div>
				</div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Department<font color="Red">*</font>
           				<html:select styleClass="form-control" property="deptName" name="createEmployeeForm" styleId="deptName" onmouseover="addTooltip('deptName');" title="Select Department" >
						<html:options property="ID" collection="hospitalList" labelProperty="VALUE"/>
								
						</html:select>
					    </div>
                        	<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
           				Designation<font color="Red">*</font>
 
           					<html:select  styleClass="form-control" property="vacPosName" name="createEmployeeForm" styleId="vacPosName" onmouseover="addTooltip('vacPosName');"  title="Select Vacant Position">
							<option value="">--------Select---------</option>	
							<logic:notEmpty name="vacNamesList">
							 <html:options collection="vacNamesList" labelProperty="VALUE" property="ID"/>
							 </logic:notEmpty>
							</html:select>

				    </div>
	                   <%--  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group">
					          <div align="center"><b>Bank Details</b></div>
						</div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								Bank Name
								<html:select property="bankName" name="createEmployeeForm" styleClass="form-control" styleId="bankName"  onmouseover="addTooltip('bankName');" title="Bank Name" onchange = "getIFSCCode('bankName')"  >
                     			<option  value="Select">----------Select Bank Name---------</option>\
                     			<logic:notEmpty name="BankCombo">
                    			<html:options collection="BankCombo" labelProperty="VALUE" property="ID"/>
                    			</logic:notEmpty>
                      			</html:select>
                      		</div>
                      		<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								IFSC Code
								<html:text styleClass="form-control" property="ifscCode" disabled="${disabled}" readonly="true" styleId="ifscCode"  name="createEmployeeForm" title="IFSC code" />
							</div>
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								Name As Per Bank Account
								<html:text  disabled="${disabled}"  onchange="validateAlphabetic('accHolderName')" styleClass="form-control" property="accHolderName" title="Name of Account Holder" styleId="accHolderName" name="createEmployeeForm" />    
					       </div>
					       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
					       Account Number
					       <html:text styleClass="form-control" property="accNumber" disabled="${disabled}"  maxlength="20" styleId="accNumber"  name="createEmployeeForm" title="Account Number" />
					      </div>
					    
							<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
								Name as per PanCard
								<html:text  disabled="${disabled}" styleClass="form-control" property="panCardName" title="Name on Pan Card" styleId="panCardName" name="createEmployeeForm"  onchange ="validateAlphabetic('panCardName')" />    
					       </div>
					       <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12 form-group bottom-margin tbcellCss">
					       		PAN Card Number
					       		<html:text  disabled="${disabled}" styleClass="form-control" property="panNumber" title="Pan CardNumber" styleId="panNumber" name="createEmployeeForm" onchange="fnValidatePAN('panNumber')" />
					       </div>
					   </div>



			
			
					   </div>
				 --%>
					  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center"><br/>
					       <button class="btn but" id="update" name="Update" tabindex="0" type="button" title="Update" onclick="updateEmployee();">Update</button>
					       <button class="btn but" id="reset" name="reset" tabindex="0" type="button" title="Reset" onclick="resetPage();">Reset</button>
					</div>

			
			<div class="container-fluid center-block">
				<c:if test="${size eq '0'}">
				
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 " align="center"><br/>
						<label><b>No Data found</b></label>
					</div>
				
				</c:if>
			</div>
			<div id="processImagetable" style="top:20%;z-index:50;position:absolute;left:45%">
         </div>
		</html:form>
	</body>
</html>
					