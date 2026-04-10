String.prototype.trim = function () {
    return this.replace(/^\s*/, "").replace(/\s*$/, "");
};

var genInventList="";
var genOldList="";
var addedProcs="";
var otherInvestCount=0;
var otherDrugCount=0;
var selectedProcs=null;
var comboProcIds=null;
var nonComboProcIds=null;
var standaloneProcIds=null;
var deleteLst=new Array();
var consultCount=1;
var consultDataList="";

addedProcs=new Array();



var isNN = (navigator.appName.indexOf("Netscape")!=-1);
function partial(func /*, 0..n args */) {
	 var args = new Array();
	 for (var i = 1; i < arguments.length; i++) { args.push(arguments[i]); }
	  return function() {
	    var allArguments = args.concat(Array.prototype.slice.call(arguments));
	    return func.apply(this, allArguments);
	  };
}
function focusBox(arg)
{	
  aField = arg; 
  setTimeout("aField.focus()", 0);
  var x=getOffset( arg ).top;  

}
function focusFieldView(el)
{
//fn_goToField(el);
focusBox(document.getElementById(el));
}
function addTooltip(input) 
{
	var numOptions = document.getElementById(input).options.length;
	 for ( var i = 0; i < numOptions; i++)
		document.getElementById(input).options[i].title = document
				.getElementById(input).options[i].text;
} 
function autoTab(input,len, e) 
{
	var keyCode = (isNN) ? e.which : e.keyCode; 
     if( keyCode== 32 )
     {
       input.value='';
       return 0;
  	 }
	 if(keyCode!=8 && keyCode!=32 && keyCode!=16)
	 {
	//if(validateAlphaNum('Employee/Pensioner/Journalist/New Born Baby Parents Card No',input,'Health Card No'))
	if(validateAlphaNum('Employee/Pensioner/New Born Baby Parent\'s Card No',input,'Health Card No'))
	{
     

	  function containsElement(arr, ele) 
	  {
	 	 var found = false, index = 0;
		 while(!found && index < arr.length)
		 if(arr[index] == ele)
			 found = true;
			  else
			 index++;
		   return found;
	 }
	  function getIndex(input) 
	  {
		   var index = -1, i = 0, found = false;
			while (i < input.form.length && index == -1)
					if (input.form[i] == input)index = i;
					  else i++; 
			return index;
	 }
    var filter = (isNN) ? [0,8,9] : [0,8,9,16,17,18,37,38,39,40,46];
     if(input.value.length >= len && !containsElement(filter,keyCode)) 
     {
       input.value = input.value.slice(0, len);
       //input.form[(getIndex(input)+1) % input.form.length].focus();
       var eleIndex=((getIndex(input)+1) % input.form.length);
       if(input.form[eleIndex].disabled==false)
      focusBox(input.form[(getIndex(input)+1) % input.form.length]);
     }
  
 return true;
 }
 }
}
function validateBackSpace(e)
{
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;  
    if( code== 8 )
    {
      e.returnValue=false;
 	 }
}
function validateMaxlength(input,e)
{
	var fieldValue=input.value;
	var code;
    if (!e) var e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which; 
	if(fieldValue.trim().length>300)
		{
		input.value=fieldValue.trim().substr(0,300);
		jqueryAlertMsg('Maxlength Validation','Exceeded maximum limits of 300 words.');		
		if(code==8 || code==46 || code==37 || code==38 || code==39 || code==40)
			//Key Code check for backspace,delete,Arrow Left, Arrow Up,Arrow Right,Arrow Down
			{
			e.returnValue=true;
			}
		else
			{
			e.returnValue=false;
		 	}
		}
}
function validateSpecialChars(input,arg1)
{
	var a=input.value;
	var specialChars=new Array('~','!','`','@','$','#','%','^','&','*','(',')','.');
	if(contains(specialChars,a.substring(a.length-1)))
		{
		var fr = partial(focusBox,input);
		jqueryAlertMsg('Special Character Validation','Special characters are not allowed',fr);
		input.value=a.substring(0,a.length-1);
		return false;
		}
}
function contains(a, obj) {
    for (var i = 0; i < a.length; i++) {
        if (a[i] == obj) {
            return true;
        }
    }
    return false;
}
function validateSplKeyCodes(evt)      
{         
	var charCode = (evt.which) ? evt.which : evt.keyCode;       
			if (charCode>31&& (charCode<47 || charCode>58)&&(charCode<65 || charCode>90)
					&&(charCode<97 || charCode>122)&&(charCode!= 63 &&(charCode<39 || charCode>41)
							&&(charCode!=44)&&(charCode!=46)&&(charCode!=91)&&(charCode!=93)&&(charCode!=95)
							&&(charCode!=32)&&(charCode!=38)&&(charCode!=39)&&(charCode!=40)))	
			    return false; 	
				return true;  
} 
function chkSpecailChars(vFileName)
{
   var val =1;  
   var iChars = "*|\":<>[]{}`\';()$#%&^.,!@?/";    
    for (var i = 0; i < vFileName.length; i++) 
    {
        if (iChars.indexOf(vFileName.charAt(i)) != -1)
        {         
          val = 0; break;
        } 
    }
    return val;
}

function validatePin(input)
{ 
	var a=input.value;
	var regAlphaNum=/^[0-9]+$/;
	var fr = partial(focusBox,input);
	if(a.search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Pin code Validation','Pin code allows only numbers',fr);
		input.value="";
		return false;
		}
	if(a.trim().length<6 || a.trim().length>6)
		{
		jqueryErrorMsg('Pin code Validation','Pin code must contain 6 digits only',fr);
		input.value="";
		return false;
		}
	if(a.trim().charAt(0)=="0")
	{
		jqueryErrorMsg('Pin code Validation','Pin code cannot start with 0',fr);
	input.value="";
	return false;
	}
}
function validateMobile(input)
{
	var a = input.value;
	var fr = partial(focusBox,input);
	if(a=="")
	{
		jqueryErrorMsg('Contact Number Validation','Enter the Contact No',fr);
	input.value="";
	return false;
	}
	var regAlphaNum=/^[0-9]+$/;
	if(a.search(regAlphaNum)==-1)
	{
		jqueryErrorMsg('Contact Number Validation','Enter the valid Contact No',fr);
	input.value="";
	return false;
	}
	if((a.trim().length < 10) || (a.trim().length > 10))
	{
		jqueryErrorMsg('Contact Number Validation','Contact No must contain 10 digits',fr);
	input.value="";
	return false;
	}
	if(!(a.trim().charAt(0)=="7" || a.trim().charAt(0)=="8" || a.trim().charAt(0)=="9"))
		{
		jqueryErrorMsg('Contact Number Validation','Contact No must start with 7/8/9',fr);
		input.value="";
		return false;
		}
}
function validateAddress(input,arg1)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	var regAlphaNum=/^[0-9a-zA-Z,.:\/\- ]+$/;
	if(a.trim()=="")
	{
		input.value="";
		jqueryErrorMsg('Address Validation',"Blank spaces are not allowed for "+arg1,fr);
		return false;
	}
	if(a.charAt(0)=="-" || a.charAt(0)=="." || a.charAt(0)=="," || a.charAt(0)==":" || a.charAt(0)=="/")
	{
		input.value="";
		jqueryErrorMsg('Address Validation',arg1+ " should not start with special characters",fr);
		return false;
	}
	if(a.trim().search(regAlphaNum)==-1)
	//if(a.trim()=="")
		{
		input.value="";
		jqueryErrorMsg('Address Validation','Only alphanumerics and -,.,:,/ are allowed in '+arg1,fr);
		return false;
		}
	else
		{
			var flag=true;
			flag=consecutiveSpecialChar(a.trim());
			if(flag)
			{
				input.value=a.trim();
			}
			else
			{
				jqueryErrorMsg('Address Validation',"Consecutive Special Characters and spaces are not allowed for "+arg1,fr);
				input.value="";
				return false;
			}
		}
	
}
function checkAlpha(arg1,arg2,fieldType) //only alphabets A-Z and a-z
{
     var Names=eval("document.forms[0]."+arg1);
     var fr = partial(focusBox,Names);
     var Names1=Names.value;
     if(Names1.trim()=="")
 	{
    	 Names.value="";
    	 jqueryErrorMsg('Alphabet Validation','Blank spaces are not allowed for '+arg2,fr);
    	 return false;
 	}
	if(fieldType=='Qualification')
	{
		if(Names1.charAt(0)=="."  || Names1.charAt(0)=="(" || Names1.charAt(0)==")")
		{
			Names.value="";
			jqueryErrorMsg('Alphabet Validation',arg2+ " should not start with special characters",fr);
			return false;
		}
		if(Names1.charAt(Names1.length-1)=="."  || Names1.charAt(Names1.length-1)=="(")
		{
			Names.value="";
			jqueryErrorMsg('Alphabet Validation',arg2+ " should not end with special characters",fr);
			return false;
		}
		if(Names1 != null && Names1 != "")
		{
			var reg=/^[a-zA-Z.() ]+$/;
			if(Names1.search(reg)==-1)
			{
				Names.value="";
				jqueryErrorMsg('Alphabet Validation','Only alphabets and . () are allowed in '+arg2,fr);
				return 0;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(Names1.trim());
				if(flag)
				{
					Names.value=Names1.trim();
				}
				else
				{
					jqueryErrorMsg('Special Character Validation',"Consecutive Special Characters and spaces are not allowed for "+arg2,fr);
					Names.value="";
					return false;
				}
			}
		}
	 }
	 else
	 {
		if(Names1 != null && Names1 != "")
		{
			var reg=/^[a-zA-Z]+$/;
			if(Names1.search(reg)==-1)
			{
				Names.value="";
				jqueryErrorMsg('Alphabet Validation','Enter only alphabets in '+arg2,fr);
				return 0;
			}
			else
				Names.value=Names1.trim();
			return 1;
     
		}
	 }
}
function checkAlphaSpace(arg1,arg2) //only alphabets A-Z and a-z
{
    var Names=eval("document.forms[0]."+arg1);
    var fr = partial(focusBox,Names);
    var Names1=Names.value;
    if(Names1.trim()=="")
  	{
		Names.value="";
    	jqueryErrorMsg('Alphabet Validation','Blank spaces are not allowed for '+arg2,fr);
		return false;
  	}
    if(Names1 != null && Names1 != "")
    {
		var reg=/^[a-zA-Z ]+$/;
		if(Names1.search(reg)==-1)
		{
    	   Names.value="";
    	   jqueryErrorMsg('Alphabet Validation','Enter only alphabets in '+arg2,fr);
           return 0;
		}
		else
		{
			var flag=true;
			flag=consecutiveSpecialChar(Names1.trim());
			if(flag)
			{
				Names.value=Names1.trim();
			}
			else
			{
				jqueryErrorMsg('Special Character Validation',"Consecutive Special Characters and spaces are not allowed for "+arg2,fr);
				Names.value="";
				return false;
			}
		}
     
    }
}
function validateNumber(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	var regDigit=/^\d+$/ ;
	if(a.search(regDigit)==-1)
		{
		input.value="";
		jqueryErrorMsg('Number Validation','Only numbers are allowed for '+arg1,fr);
		return false;
		}
	if(a.charAt(0)=="0")
	{
	input.value="";
		jqueryErrorMsg('Number Validation',arg1+ ' should not start with 0',fr);
	return false;
	}
}
function validateAlphaSpace(arg1,input,fieldType)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
		input.value="";
		jqueryErrorMsg('Alphabet Validation',"Blank spaces are not allowed for "+arg1,fr);
		return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Alphabet Validation',arg1+ " should not start with space",fr);
		return false;
		}
	if(fieldType=="Designation")
		{
			if(a.charAt(0)=="." || a.charAt(0)==",")
			{
				input.value="";
				jqueryErrorMsg('Alphabet Validation',arg1+ " should not start with special characters",fr);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)==",")
			{
				input.value="";
				jqueryErrorMsg('Alphabet Validation',arg1+ " should not end with special characters",fr);
				return false;
			}
			var regAlphaNum=/^[a-zA-Z,. ]+$/;
			if(a.trim().search(regAlphaNum)==-1)
			{
				jqueryErrorMsg('Alphabet Validation',"Only alphabets and ., are allowed for "+arg1,fr);
				input.value="";
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					jqueryErrorMsg('Special Character Validation',"Consecutive Special Characters and spaces are not allowed for "+arg1,fr);
					input.value="";
					return false;
				}
			}
		}
	else
		{
			var regAlphaNum=/^[a-zA-Z ]+$/;
			if(a.trim().search(regAlphaNum)==-1)
			{
				jqueryErrorMsg('Alphabet Validation',"Only alphabets are allowed for "+arg1,fr);
				input.value="";
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					jqueryErrorMsg('Special Character Validation',"Consecutive spaces are not allowed for "+arg1,fr);
					input.value="";
					return false;
				}
			}
		}
}
function validateAlphaNum(arg1,input,fieldType)
{

	var a=input.value;
	
		if(fieldType=="Registration No")
		{
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="/")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="/")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z\/]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and / are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					focusBox(input);
					input.value="";
					return false;
				}
			}
		}
			
		if(fieldType=="Legal Case No")
		{
			if(a!=null && a!=''){
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="." || a.charAt(0)=="-")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)=="-")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z.-]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and .- are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					focusBox(input);
					input.value="";
					return false;
				}
			}
			}
		}
		
		if(fieldType=="Drug Batch Num")
		{
			if(a!=null && a!=''){
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="." || a.charAt(0)=="-")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)=="-")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z.-]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and .- are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					focusBox(input);
					input.value="";
					return false;
				}
			}
			}
		}
		
		if(fieldType=="AHC Field")
		{
			if(a!=null && a!=''){
			if(a.trim()=="")
			{
				input.value="";
				alert("Blank spaces are not allowed for "+arg1);
				focusBox(input);
				return false;
			}
			if(a.charAt(0)=="." || a.charAt(0)=="-")
			{
				input.value="";
				alert(arg1+ " should not start with special characters");
				focusBox(input);
				return false;
			}
			if(a.charAt(a.length-1)=="." || a.charAt(a.length-1)=="-")
			{
				input.value="";
				alert(arg1+ " should not end with special characters");
				focusBox(input);
				return false;
			}
			var regAlphaNum=/^[0-9a-zA-Z.]+$/;
			if(a.search(regAlphaNum)==-1)
			{
				input.value="";
				alert("Only alphanumerics and . are allowed for "+arg1);
				focusBox(input);
				return false;
			}
			else
			{
				var flag=true;
				flag=consecutiveSpecialChar(a.trim());
				if(flag)
				{
					input.value=a.trim();
				}
				else
				{
					alert("Consecutive Special Characters and spaces are not allowed for "+arg1);
					
					input.value="";
					focusBox(input);
					return false;
				}
			}
			}
		}
		else{
				if(a.trim()=="")
				{
					input.value="";
					alert("Blank spaces are not allowed for "+arg1);
					focusBox(input);
					return false;
				}
				var regAlphaNum=/^[0-9a-zA-Z]+$/;
				if(a.search(regAlphaNum)==-1)
				{
					input.value="";
					alert("Only alphanumerics are allowed for "+arg1);
					focusBox(input);
					return false;
				}
				else
				{
					input.value=a.trim();
				}
			}
		return true;
}
function validateAlphaNumSpace(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Alphanumeric Validation',"Blank spaces are not allowed for "+arg1,fr);
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Alphanumeric Validation',arg1+ " should not start with space",fr);
		return false;
		}
	var regAlphaNum=/^[0-9a-zA-Z ]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Alphanumeric Validation',"Only alphanumerics are allowed for "+arg1,fr);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
}


function validateAlphaNumCommaSpace(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
		input.value="";
		jqueryErrorMsg('Alphanumeric Validation',"Blank spaces are not allowed for "+arg1,fr);
		return false;
	}
	if(a.charAt(0)==" "||a.charAt(0)==',')
		{
		input.value="";
		jqueryErrorMsg('Alphanumeric Validation',arg1+ " should not start with space or comma",fr);
		return false;
		}
	var regAlphaNum=/^[0-9a-zA-Z ,.&\/]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Alphanumeric Validation',"Only alphanumerics  . , & and /are allowed for "+arg1,fr);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
}



function validateBatchNum(arg1,input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
		input.value="";
		jqueryErrorMsg('Alphanumeric Validation',"Blank spaces are not allowed for "+arg1,fr);
		return false;
	}
	if(a.charAt(0)==" "||a.charAt(0)==',')
		{
		input.value="";
		jqueryErrorMsg('Alphanumeric Validation',arg1+ " should not start with space or comma",fr);
		return false;
		}
	var alphaNum=/^[0-9a-zA-Z]+$/;
	for(var i=0;i<a.length;i++)
		{
	if(a.charAt(i).trim().search(alphaNum)==-1)
		{
		if((a.length-i>1) && a.charAt(i+1).trim().search(alphaNum)==-1)
			{
			jqueryErrorMsg('Alphanumeric Validation',"Consecutive Special Characters are not allowed for "+arg1,fr);
			input.value="";
			return false;
			}
			
		}
		
		}
	var regAlphaNum=/^[0-9a-zA-Z , -.&\/]+$/;
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Alphanumeric Validation',"Only alphanumerics - . , & and /are allowed for "+arg1,fr);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
}

function validateSpaces(input,arg1)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Text Validation',"Blank spaces are not allowed for "+arg1,fr);
	return false;
	}
	if(a.charAt(0)==" ")
	{
		input.value="";
		jqueryErrorMsg('Text Validation',arg1+ " should not start with space",fr);
		return false;
	}
}
//Common for patient and telephonic registration details
function checkGenderRelation()
{
	var lField='relation';
	var fr=partial(focusBox,document.getElementById(lField));
	var relation= document.getElementById("relation").options[document.getElementById("relation").selectedIndex].text;
	if(document.forms[0].gender[0].checked)
	{
	 if(relation=='Husband' || relation=='Son' || relation=='Father' || relation=='Grandson' || 
			 relation=='Grand Father' || relation=='Son in-law' || relation=='Father in-law' || 
			 relation=='Others' || relation=='Brother' || relation=='Self' || relation=='FamilyHead' || 
		     relation=='New Born Baby')
		 return true;
	 else
		 {
		 jqueryErrorMsg('Gender Relation Validation',"Select valid Relationship",fr);
	 		return false;
		 }
	}
	if(document.forms[0].gender[1].checked)
	{
	 if(relation=='Wife' || relation=='Daughter' || relation=='Mother' || relation=='Grand Daughter' || 
		 relation=='Grand Mother' || relation=='Daughter in-law' || relation=='Mother in-law' || 
		 relation=='Others' || relation=='Sister' || relation=='Self' || relation=='FamilyHead' ||  
	     relation=='New Born Baby')
	 return true;
 	else
	 {
 		jqueryErrorMsg('Gender Relation Validation',"Select valid Relationship",fr);
 		return false;
	 }
	}
}
function readCardData()
{
	var errMsg='';
	if (!document.forms[0].card_type[0].checked && !document.forms[0].card_type[1].checked && !document.forms[0].card_type[2].checked && !document.forms[0].card_type[3].checked) 
		errMsg=errMsg+"Select Card Type \n";
	else if(document.forms[0].card_type[0].checked)
	{
		var empCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("ECNo"+i).value=='')	
			{
				empCardCount++;	
			}
			}
		if(empCardCount>0)
			{
			errMsg=errMsg+"Enter valid Employee Card No \n";
			}
	}
	else if(document.forms[0].card_type[1].checked)
	{
		var penCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("WCNo"+i).value=='')	
			{
				penCardCount++;	
			}
		}
		if(penCardCount>0)
			{
			errMsg=errMsg+"Enter valid Pensioner Card No \n";
			}
	}
	else if(document.forms[0].card_type[2].checked)
	{
		var jouCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("JCNo"+i).value=='')	
			{
				jouCardCount++;	
			}
		}
		if(jouCardCount>0)
			{
			errMsg=errMsg+"Enter valid Journalist Card No \n";
			}
	}
	else if(document.forms[0].card_type[3].checked)
	{
		var RjouCardCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("RJCNo"+i).value=='')	
			{
				RjouCardCount++;	
			}
		}
		if(RjouCardCount>0)
			{
			errMsg=errMsg+"Enter valid Retired Journalist Card No \n";
			}
	}
	/*else if(document.forms[0].card_type[3].checked)
	{
		var newBornCount=0;
		for(var i=0;i<11;i++)
			{
			if (document.getElementById("NBNo"+i).value=='')	
			{
				newBornCount++;	
			}
		}
		if(newBornCount>0)
			{
			errMsg=errMsg+"Enter valid Parent Card No of the New Born Baby \n";
			}
	}*/
	
	if(errMsg=='')
		{
		var wapNo="";
		var familyNo="";
		if(document.forms[0].card_type[0].checked)
			{
			wapNo=document.forms[0].ECNo0.value+document.forms[0].ECNo1.value+document.forms[0].ECNo2.value+document.forms[0].ECNo3.value+document.forms[0].ECNo4.value+document.forms[0].ECNo5.value
			+document.forms[0].ECNo6.value+document.forms[0].ECNo7.value+document.forms[0].ECNo8.value;
			familyNo=document.forms[0].ECNo9.value+document.forms[0].ECNo10.value;
			}
		else if(document.forms[0].card_type[1].checked)
			{
			wapNo=document.forms[0].WCNo0.value+document.forms[0].WCNo1.value+document.forms[0].WCNo2.value+document.forms[0].WCNo3.value+document.forms[0].WCNo4.value+document.forms[0].WCNo5.value
			+document.forms[0].WCNo6.value+document.forms[0].WCNo7.value+document.forms[0].WCNo8.value;
			familyNo=document.forms[0].WCNo9.value+document.forms[0].WCNo10.value;
			}
		else if(document.forms[0].card_type[2].checked)
			{
			wapNo=document.forms[0].JCNo0.value+document.forms[0].JCNo1.value+document.forms[0].JCNo2.value+document.forms[0].JCNo3.value+document.forms[0].JCNo4.value+document.forms[0].JCNo5.value
			+document.forms[0].JCNo6.value+document.forms[0].JCNo7.value+document.forms[0].JCNo8.value;
			familyNo=document.forms[0].JCNo9.value+document.forms[0].JCNo10.value;
			}
		else if(document.forms[0].card_type[3].checked)
		{
		wapNo=document.forms[0].RJCNo0.value+document.forms[0].RJCNo1.value+document.forms[0].RJCNo2.value+document.forms[0].RJCNo3.value+document.forms[0].RJCNo4.value+document.forms[0].RJCNo5.value
		+document.forms[0].RJCNo6.value+document.forms[0].RJCNo7.value+document.forms[0].RJCNo8.value;
		familyNo=document.forms[0].RJCNo9.value+document.forms[0].RJCNo10.value;
		}
		/*else if(document.forms[0].card_type[3].checked)
			{
			wapNo=document.forms[0].NBNo0.value+document.forms[0].NBNo1.value+document.forms[0].NBNo2.value+document.forms[0].NBNo3.value+document.forms[0].NBNo4.value+document.forms[0].NBNo5.value
			+document.forms[0].NBNo6.value+document.forms[0].NBNo7.value+document.forms[0].NBNo8.value;
			familyNo=document.forms[0].NBNo9.value+document.forms[0].NBNo10.value;
			}*/
		wapNo=wapNo.toUpperCase();
		return wapNo+"/"+familyNo;
		}
	else
		{
		jqueryErrorMsg('Health Card Validation',errMsg);
		return false;
		}

}

function sameAddr(status)
{
	var errMsg="";
	var lField='';
	
	if(document.getElementById("commCheck").checked==true)
		{
		document.getElementById("commCheck").value='Y';
		if(document.getElementById("hno").value=="")
			{
			errMsg=errMsg+"Enter House No in Card Address \n";
			if(lField=='')
		        lField='hno'; 
			}
		if(document.getElementById("street").value=="")
			{
			errMsg=errMsg+"Enter Street in Card Address \n";
			if(lField=='')
		        lField='street'; 
			}
		if(document.getElementById("state").value==-1)
		{
		errMsg=errMsg+"Select State in Card Address \n";
		if(lField=='')
	        lField='state'; 
		}
		if(document.getElementById("district").value==-1)
			{
			errMsg=errMsg+"Select District in Card Address \n";
			if(lField=='')
		        lField='district'; 
			}  
		if(document.getElementById("mdl_mcl").value==-1)
			{
			errMsg=errMsg+"Select Mandal/Municipality in Card Address \n";
			if(lField=='')
		        lField='mdl_mcl'; 
			}
		if(document.getElementById("mdl_mcl").value == 'Mdl')
			{
			if(document.getElementById("mandal").value==-1)
				{
				errMsg=errMsg+"Select Mandal in Card Address \n";
				if(lField=='')
			        lField='mandal'; 
				}
			}
		else if(document.getElementById("mdl_mcl").value == 'Mpl')
	 		{
			if(document.getElementById("municipality").value==-1)
				{
				errMsg=errMsg+"Please Select Municipality in Card Address \n";
				if(lField=='')
			        lField='municipality'; 
				}
	 		}
		if(document.getElementById("village").value == "-1")
			{
			errMsg=errMsg+"Select Village in Card Address \n";
			if(lField=='')
		        lField='village'; 
			}
		if(document.getElementById("tgGovPatCond")!=null)
			{
				var tgGovPatCond=document.getElementById("tgGovPatCond").value;
				if(tgGovPatCond==null || tgGovPatCond=='' || tgGovPatCond==' ' ||
						(tgGovPatCond!=null && tgGovPatCond!='' && tgGovPatCond!=' ' && tgGovPatCond!='Y'))
					{
						if(document.getElementById("pin").value=="")
						{
						errMsg=errMsg+"Enter Pin code in Card Address \n";
						if(lField=='')
					        lField='pin'; 
						}
					}
			}
		else
			{
				if(document.getElementById("pin").value=="")
					{
					errMsg=errMsg+"Enter Pin code in Card Address \n";
					if(lField=='')
				        lField='pin'; 
					}
			}	
		
		if(errMsg=="")
			{
			document.getElementById("comm_hno").value=document.getElementById("hno").value;
			document.forms[0].comm_hno.disabled=true;
		
			document.forms[0].comm_street.value = document.forms[0].street.value;
			document.forms[0].comm_street.disabled=true;
		
			document.getElementById("same_state").style.display="";
			document.getElementById("comm_statetd").style.display="none";
			document.forms[0].same_state.value=document.getElementById("state").options[document.getElementById("state").selectedIndex].text;
			document.forms[0].same_state.disabled=true;
			
			document.getElementById("same_dist").style.display="";
			document.getElementById("comm_disttd").style.display="none";
			document.forms[0].same_dist.value=document.getElementById("district").options[document.getElementById("district").selectedIndex].text;
			document.forms[0].same_dist.disabled=true;
		
			document.getElementById("same_mdl_mcltd").style.display="";
			document.getElementById("comm_mdl_mcltd").style.display="none";
			document.forms[0].same_mdl_mcl.value=document.getElementById("mdl_mcl").options[document.getElementById("mdl_mcl").selectedIndex].text;
			document.forms[0].same_mdl_mcl.disabled=true;
		
			if(document.getElementById("mdl_mcl").value == 'Mdl')
				{
				document.getElementById("mandalcommtab").style.display="";
				document.getElementById("munciplcommtab").style.display="none";
				document.getElementById("same_mandal").style.display="";
				document.getElementById("comm_mandaltd").style.display="none";
				document.getElementById("comm_municipaltd").style.display="none";
				document.forms[0].same_mandal.value=document.getElementById("mandal").options[document.getElementById("mandal").selectedIndex].text;
				document.forms[0].same_mandal.disabled=true;
				}
			else if(document.getElementById("mdl_mcl").value == 'Mpl')
	 			{
				document.getElementById("mandalcommtab").style.display="none";
				document.getElementById("munciplcommtab").style.display="";
				document.getElementById("same_municipalitytd").style.display="";
		 		document.getElementById("comm_municipaltd").style.display="none";
		 		document.getElementById("comm_mandaltd").style.display="none";
		 		document.forms[0].same_municipality.value=document.getElementById("municipality").options[document.getElementById("municipality").selectedIndex].text;
		 		document.forms[0].same_municipality.disabled=true;	
	 			}
			document.getElementById("same_villagetd").style.display="";
			document.getElementById("comm_villagetd").style.display="none";
			document.forms[0].same_village.value=document.getElementById("village").options[document.getElementById("village").selectedIndex].text;
			document.forms[0].same_village.disabled=true;
		
			document.forms[0].comm_pin.value = document.forms[0].pin.value;
			document.forms[0].comm_pin.disabled=true;
			}
		else
			{
			document.getElementById("commCheck").value='N';
			document.getElementById("commCheck").checked=false;
			var fr = partial(focusBox,document.getElementById(lField));
	 		jqueryAlertMsg('Same Address Check',errMsg,fr);
			return false;
			}
		}
	else if(document.getElementById("commCheck").checked==false)
		{
		document.getElementById("commCheck").value='N';
		document.forms[0].comm_hno.value="";
        document.forms[0].comm_street.value="";
		document.forms[0].comm_pin.value="";
		document.forms[0].comm_hno.disabled=false;
		document.forms[0].comm_street.disabled=false;
		document.forms[0].comm_pin.disabled=false;
		
		document.getElementById("same_state").style.display = 'none';
		document.getElementById("same_state").value="";
		document.getElementById("comm_state").value="-1";
		document.getElementById("comm_statetd").style.display = '';
		
		document.getElementById("same_dist").style.display = 'none';
		document.getElementById("same_dist").value="";
		//document.getElementById("comm_dist").value="-1";
		document.getElementById("comm_disttd").style.display = '';
		document.getElementById("comm_dist").options.length = 1;
		
		document.getElementById("same_mdl_mcltd").style.display='none';
		document.getElementById("same_mdl_mcl").value="";
		document.getElementById("comm_mdl_mcl").value="-1";
		document.getElementById("comm_mdl_mcltd").style.display='';
		
		document.getElementById("same_mandal").style.display='none';
		document.getElementById("same_mandal").value="";
		document.getElementById("mandalcommtab").style.display='';
		document.getElementById("munciplcommtab").style.display='none';
		document.getElementById("same_mandal").style.display='none';
		document.forms[0].comm_mandal.options.length=1;
		document.getElementById("comm_mandaltd").style.display='';
		document.forms[0].comm_municipality.options.length=1;
		document.getElementById("comm_municipaltd").style.display='none';
		document.getElementById("same_municipalitytd").style.display='none';
		
		document.getElementById("same_villagetd").style.display='none';
		document.getElementById("same_village").value="";
		document.forms[0].comm_village.options.length=1;
		document.getElementById("comm_villagetd").style.display='';
		
		}
}
function checkFamilyHead(status)
{
	if(document.forms[0].card_type[1].checked)
	{
		if(status)
		{
			document.forms[0].WCNo9.disabled=true;
			document.forms[0].WCNo9.value=0;
			document.forms[0].WCNo10.disabled=true;
			document.forms[0].WCNo10.value=1;
			//document.forms[0].child.checked = false;
	       // document.forms[0].child.disabled = true;
		}

		else
		{
			document.forms[0].WCNo9.disabled=false;
			document.forms[0].WCNo9.value='';
			document.forms[0].WCNo10.disabled=false;
			document.forms[0].WCNo10.value='';
			//document.forms[0].child.disabled = false;
		}
	}
	else if(document.forms[0].card_type[0].checked)
	{
		if(status)
		{
			document.forms[0].ECNo9.disabled=true;
			document.forms[0].ECNo9.value=0;
			document.forms[0].ECNo10.disabled=true;
			document.forms[0].ECNo10.value=1;
			//document.forms[0].child.checked = false;
	       // document.forms[0].child.disabled = true;
		}
		else
		{
			document.forms[0].ECNo9.disabled=false;
			document.forms[0].ECNo9.value='';
			document.forms[0].ECNo10.disabled=false;
			document.forms[0].ECNo10.value='';
			//document.forms[0].child.disabled = false;
		}
	}
	else if(document.forms[0].card_type[2].checked)
	{
		if(status)
		{
			document.forms[0].JCNo9.disabled=true;
			document.forms[0].JCNo9.value=0;
			document.forms[0].JCNo10.disabled=true;
			document.forms[0].JCNo10.value=1;
			//document.forms[0].child.checked = false;
	       // document.forms[0].child.disabled = true;
		}
		else
		{
			document.forms[0].JCNo9.disabled=false;
			document.forms[0].JCNo9.value='';
			document.forms[0].JCNo10.disabled=false;
			document.forms[0].JCNo10.value='';
			//document.forms[0].child.disabled = false;
		}
	}
	else if(document.forms[0].card_type[3].checked)
	{
		if(status)
		{
			document.forms[0].RJCNo9.disabled=true;
			document.forms[0].RJCNo9.value=0;
			document.forms[0].RJCNo10.disabled=true;
			document.forms[0].RJCNo10.value=1;
			//document.forms[0].child.checked = false;
	       // document.forms[0].child.disabled = true;
		}
		else
		{
			document.forms[0].RJCNo9.disabled=false;
			document.forms[0].RJCNo9.value='';
			document.forms[0].RJCNo10.disabled=false;
			document.forms[0].RJCNo10.value='';
			//document.forms[0].child.disabled = false;
		}
	}
	/*else if(document.forms[0].card_type[3].checked)
	{
		if(status)
		{
			document.forms[0].NBNo9.disabled=true;
			document.forms[0].NBNo9.value=0;
			document.forms[0].NBNo10.disabled=true;
			document.forms[0].NBNo10.value=1;
			//document.forms[0].child.checked = false;
	       // document.forms[0].child.disabled = true;
		}
		else
		{
			document.forms[0].NBNo9.disabled=false;
			document.forms[0].NBNo9.value='';
			document.forms[0].NBNo10.disabled=false;
			document.forms[0].NBNo10.value='';
			//document.forms[0].child.disabled = false;
		}
	}*/
}


function enable_cardType(status)
{
	if (document.forms[0].card_type[1].checked)
		{
			document.getElementById("head").checked=false;
			document.getElementById("head").disabled=false;
			for(var i=0;i<=10;i++)
			    {
			        document.getElementById('WCNo'+i).value="";
			    }
			document.forms[0].WCNo9.disabled=false;
			document.forms[0].WCNo10.disabled=false;
			document.getElementById("label_pencard").style.display='';
			document.getElementById("label_empcard").style.display='none';
			document.getElementById("label_joucard").style.display='none';
			document.getElementById("label_Rjoucard").style.display='none';
			
		//	document.getElementById("label_newBorncard").style.display='none';
		}
	else if (document.forms[0].card_type[0].checked)
		{
			document.getElementById("head").checked=false;
			document.getElementById("head").disabled=false;
			for(var i=0;i<=10;i++)
			    {
			        document.getElementById('ECNo'+i).value="";
			    }
			document.forms[0].ECNo9.disabled=false;
			document.forms[0].ECNo10.disabled=false;
			document.getElementById("label_pencard").style.display='none';
			document.getElementById("label_empcard").style.display='';
			document.getElementById("label_joucard").style.display='none';
			document.getElementById("label_Rjoucard").style.display='none';
			//document.getElementById("label_newBorncard").style.display='none';
		}
	else if (document.forms[0].card_type[2].checked)
		{
			document.getElementById("head").checked=false;
			document.getElementById("head").disabled=false;
			for(var i=0;i<=10;i++)
			    {
			        document.getElementById('JCNo'+i).value="";
			    }
			document.forms[0].JCNo9.disabled=false;
			document.forms[0].JCNo10.disabled=false;
			document.getElementById("label_pencard").style.display='none';
			document.getElementById("label_empcard").style.display='none';
			document.getElementById("label_joucard").style.display='';
			document.getElementById("label_Rjoucard").style.display='none';
			//document.getElementById("label_newBorncard").style.display='none';
		}
	else if (document.forms[0].card_type[3].checked)
	{
		document.getElementById("head").checked=false;
		document.getElementById("head").disabled=false;
		for(var i=0;i<=10;i++)
		    {
		        document.getElementById('RJCNo'+i).value="";
		    }
		document.forms[0].RJCNo9.disabled=false;
		document.forms[0].RJCNo10.disabled=false;
		document.getElementById("label_pencard").style.display='none';
		document.getElementById("label_empcard").style.display='none';
		document.getElementById("label_joucard").style.display='none';
		document.getElementById("label_Rjoucard").style.display='';
		//document.getElementById("label_newBorncard").style.display='none';
	}
	/*else if (document.forms[0].card_type[3].checked)
		{
			document.getElementById("head").checked=true;
			document.getElementById("head").disabled=true;
			for(var i=0;i<=10;i++)
			    {
			        document.getElementById('NBNo'+i).value="";
			    }
			
			document.forms[0].NBNo9.disabled=true;
			document.forms[0].NBNo9.value=0;
			document.forms[0].NBNo10.disabled=true;
			document.forms[0].NBNo10.value=1;
			document.getElementById("label_pencard").style.display='none';
			document.getElementById("label_empcard").style.display='none';
			document.getElementById("label_joucard").style.display='none';
			document.getElementById("label_newBorncard").style.display='';
		}*/
}
function distSelected(param)
{
	var district=null;
	var lStrHdrId=null;
	
	if(param==1)
		{
		document.getElementById("village").options.length = 1;
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		if(document.getElementById("district").value=="-1")
			{
			alert("Select District in Card Address");
            return false;
			}
		else
			district=document.getElementById("district").value;
		}
	if(param==2)
		{
		document.getElementById("comm_village").options.length = 1;
		if(document.getElementById("comm_dist").value=="-1")
		 	{
			alert("Select District in Communication Address");
         	return false;
		 	}
		else
			district=document.getElementById("comm_dist").value;
		}
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var mandalList = resultArray.split(","); 
            		if(mandalList.length>0)
            		{   
            			if(param==1)
                		{
            				if(document.getElementById('mdl_mcl').value == 'Mdl')
                			{
            					document.forms[0].mandal.options.length=0;
            					document.forms[0].mandal.options[0]=new Option("---select---","-1");
            					document.getElementById("municipalitytab").style.display='none';
            					document.getElementById("municipalitylist").style.display='none';
            					document.getElementById("mandaltab").style.display='';
            					document.getElementById("mandallist").style.display='';
                			}
            				else if(document.getElementById('mdl_mcl').value == 'Mpl')
                			{
            					document.forms[0].municipality.options.length=0;
            					document.forms[0].municipality.options[0]=new Option("---select---","-1");
            					document.getElementById("municipalitytab").style.display='';
            					document.getElementById("municipalitylist").style.display='';
            					document.getElementById("mandaltab").style.display='none';
            					document.getElementById("mandallist").style.display='none';
                			}
            				else
                			{
            					document.forms[0].mandal.options.length=1;
            					document.forms[0].municipality.options.length=1;
            					document.forms[0].village.options.length=1;
            					document.getElementById("municipalitytab").style.display='none';
            					document.getElementById("municipalitylist").style.display='none';
            					document.getElementById("mandaltab").style.display='';
            					document.getElementById("mandallist").style.display='';
                			}
                		}
            			if(param==2)
              	  		{
            				if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
                			{
            					document.forms[0].comm_mandal.options.length=0;
            					document.forms[0].comm_mandal.options[0]=new Option("---select---","-1");
            					document.getElementById("munciplcommtab").style.display='none';
            					document.getElementById("comm_municipaltd").style.display='none';
            					document.getElementById("mandalcommtab").style.display='';
            					document.getElementById("comm_mandaltd").style.display='';
                			}
            				else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
            				{
            					document.forms[0].comm_municipality.options.length=0;
            					document.forms[0].comm_municipality.options[0]=new Option("---select---","-1");
            					document.getElementById("munciplcommtab").style.display='';
            					document.getElementById("comm_municipaltd").style.display='';
                       	 		document.getElementById("mandalcommtab").style.display='none';
                       	 		document.getElementById("comm_mandaltd").style.display='none';
            				}
            				else
            				{
            					document.forms[0].comm_mandal.options.length=1;
            					document.forms[0].comm_municipality.options.length=1;
            					document.forms[0].comm_village.options.length=1;
            					document.getElementById("munciplcommtab").style.display='none';
                       	 		document.getElementById("comm_municipaltd").style.display='none';
                       	 		document.getElementById("mandalcommtab").style.display='';
                       	 		document.getElementById("comm_mandaltd").style.display='';
            				}
              	  		}
				    
            			for(var i = 0; i<mandalList.length;i++)
            			{	
            					var arr=mandalList[i].split("~");
            					if(arr[1]!=null && arr[0]!=null)
            					{
            						var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            						var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            						if(param==1)
            						{
            							if(document.getElementById('mdl_mcl').value == 'Mdl')
            								document.forms[0].mandal.options[i+1] =new Option(val1,val2);
            							else if(document.getElementById('mdl_mcl').value == 'Mpl')
            								document.forms[0].municipality.options[i+1] =new Option(val1,val2); 
            						}
            						if(param==2)
            						{
            							if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
            								document.forms[0].comm_mandal.options[i+1] =new Option(val1,val2);
            							else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
            								document.forms[0].comm_municipality.options[i+1] =new Option(val1,val2);
                        	 
            						}
            					}
            			}
            		}
            	}
            }
        }
    }
    if(param==1){
    	if(document.getElementById('mdl_mcl').value == 'Mdl')
		lStrHdrId = 'LH7';
		else if(document.getElementById('mdl_mcl').value == 'Mpl')
		lStrHdrId = 'LM7';
	}
	else if(param==2){
		if(document.getElementById('comm_mdl_mcl').value == 'Mdl')
		lStrHdrId = 'LH7';
		else if(document.getElementById('comm_mdl_mcl').value == 'Mpl')
		lStrHdrId = 'LM7';
		}
    url = "./patientDetails.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&distId="+district;
    xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function mandalSelected(param,type)
{
	var mandal=null;
	var lStrHdrId=null;
	if(param==1)
		{
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		if(type=='Mdl')
		mandal=document.getElementById("mandal").value;
		else
		mandal=document.getElementById("municipality").value;	
		}
	if(param==2)
		{
		if(type=='Mdl')
			mandal=document.getElementById("comm_mandal").value;
		else
			mandal=document.getElementById("comm_municipality").value;	
		}
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var villageList = resultArray.split(","); 
            		if(villageList.length>0)
            		{  
            			if(param==1)
            			{
            				document.forms[0].village.options.length=0;
            				document.forms[0].village.options[0]=new Option("---select---","-1");
            			}
            			if(param==2)
          	  			{
            				document.forms[0].comm_village.options.length=0;
            				document.forms[0].comm_village.options[0]=new Option("---select---","-1");
          	  			}
            			for(var i = 0; i<villageList.length;i++)
            			{	
            				var arr=villageList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					if(param==1)
            					{
            						document.forms[0].village.options[i+1] =new Option(val1,val2);
            					}
            					if(param==2)
            					{
            						document.forms[0].comm_village.options[i+1] =new Option(val1,val2);
            					}
            				}
            			}
            		}
            	}
            }
        }
    }

    if(type == 'Mdl')
		lStrHdrId = 'LH8';
	else if(type == 'Mpl')
		lStrHdrId = 'LM8';
    	
	url = "./patientDetails.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&mandalId="+mandal;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function villageSelected(param)
{
	var village=null;
	if(param==1)
		{
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		village=document.getElementById("village").value;
		}
}
function pinChanged(param)
{
	if(param==1)
		{
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		}
}
function stateSelected(param)
{
	resetDist(param);
	var state=null;
	var lStrHdrId='LH6';
	if(param==1)
	{
		state=document.getElementById("state").value;
		if(document.getElementById("commCheck").checked==true)
		{
			resetCommAddr();
		}
	}
	if(param==2)
		{
			state=document.getElementById("comm_state").value;
		}
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("]*","");            
            		var districtList = resultArray.split(","); 
            		if(districtList.length>0)
            		{  
            			if(param==1)
            			{
            				document.forms[0].district.options.length=0;
            				document.forms[0].district.options[0]=new Option("---select---","-1");
            			}
            			if(param==2)
          	  			{
            				document.forms[0].comm_dist.options.length=0;
            				document.forms[0].comm_dist.options[0]=new Option("---select---","-1");
          	  			}
            			for(var i = 0; i<districtList.length;i++)
            			{	
            				var arr=districtList[i].split("~");
            				if(arr[1]!=null && arr[0]!=null)
            				{
            					var val1 = arr[1].replace(/^\s+|\s+$/g,"");
            					var val2 = arr[0].replace(/^\s+|\s+$/g,"");
            					if(param==1)
            					{
            						document.forms[0].district.options[i+1] =new Option(val1,val2);
            					}
            					if(param==2)
            					{
            						document.forms[0].comm_dist.options[i+1] =new Option(val1,val2);
            					}
            				}
            			}
            		}
            	}
            }
        }
    }
	url = "./patientDetails.do?actionFlag=getLocations&callType=Ajax&lStrHdrId="+lStrHdrId+"&stateId="+state;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
}
function pinChanged(param)
{
	if(param==1)
		{
		if(document.getElementById("commCheck").checked==true)
		{
		resetCommAddr();
		}
		}
}
function resetDist(param)
{
	if(param==1)
		{
		document.getElementById("mdl_mcl").value="-1";
		document.getElementById("municipalitytab").style.display='none';
		document.getElementById("municipalitylist").style.display='none';
		document.getElementById("mandaltab").style.display='';
		document.getElementById("mandallist").style.display='';
		document.getElementById("village").options.length = 1;
		document.getElementById("mandal").options.length = 1;
		document.getElementById("municipality").options.length = 1;
		
		if(document.getElementById("commCheck").checked==true)
			{
			resetCommAddr();
			}
		}
	else if(param==2)
		{
		document.getElementById("comm_mdl_mcl").value="-1";
		document.getElementById("munciplcommtab").style.display='none';
        document.getElementById("comm_municipaltd").style.display='none';
        document.getElementById("mandalcommtab").style.display='';
        document.getElementById("comm_mandaltd").style.display='';
		document.getElementById("comm_village").options.length = 1;
		document.getElementById("comm_mandal").options.length = 1;
		document.getElementById("comm_municipality").options.length = 1;
		}
}
function resetCommAddr()
{
	document.getElementById("commCheck").checked=false;
	document.getElementById("commCheck").value='N';
	document.forms[0].comm_hno.value="";
    document.forms[0].comm_street.value="";
    document.forms[0].comm_pin.value="";
	document.forms[0].comm_hno.disabled=false;
	document.forms[0].comm_street.disabled=false;
	document.forms[0].comm_pin.disabled=false;
	
	document.getElementById("same_state").style.display = 'none';
	document.getElementById("comm_statetd").style.display = '';
	
	document.getElementById("same_dist").style.display = 'none';
	document.getElementById("comm_disttd").style.display = '';
	
	document.getElementById("same_mdl_mcltd").style.display='none';
	document.getElementById("comm_mdl_mcltd").style.display='';
	
	document.getElementById("same_mandal").style.display='none';
	document.getElementById("mandalcommtab").style.display='';
	document.getElementById("munciplcommtab").style.display='none';
	document.getElementById("same_mandal").style.display='none';
	document.getElementById("comm_mandaltd").style.display='';
	document.getElementById("comm_municipaltd").style.display='none';
	document.getElementById("same_municipalitytd").style.display='none';
	
	document.getElementById("same_villagetd").style.display='none';
	document.getElementById("comm_villagetd").style.display='';
	
	document.getElementById("comm_state").value="-1";			
	document.getElementById("comm_dist").value="-1";
	document.getElementById("comm_mdl_mcl").value="-1";
	document.getElementById("comm_village").options.length = 1;
	document.getElementById("comm_mandal").options.length = 1;
	document.getElementById("comm_municipality").options.length = 1; 
}
function validateCardIssueDate(arg1,input)
{
	var count=0;
	var dobEntered=document.getElementById("dateOfBirth").value;
	dobEntered = dobEntered.split("-");
	var dbmth = parseInt(dobEntered[1],10); 
	var dbdy = parseInt(dobEntered[0],10);
	var dbyr = parseInt(dobEntered[2],10);
	if(isNaN(dbyr))
	{
		input.value="";
		jqueryErrorMsg('Card Issue Date Validation',"Select Date Of Birth");
		count=count+1;
		return false;
	}
	var entered = input.value;
	entered = entered.split("-");
	var cmth = parseInt(entered[1],10); 
	var cdy = parseInt(entered[0],10);
	var cyr = parseInt(entered[2],10);
	if(isNaN(cyr))
	{
		input.value="";
		jqueryErrorMsg('Card Issue Date Validation',"Select "+arg1);
		count=count+1;
	}
	if(count==0)
	{
	var DoB=""+(dbmth)+"/"+ dbdy +"/"+ dbyr;
	DoB=Date.parse(DoB);
	var cardIssueDt=""+(cmth)+"/"+cdy+"/"+cyr;
	cardIssueDt=Date.parse(cardIssueDt);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(cardIssueDt>DoC || cardIssueDt<DoB)
		{
		input.value="";
		jqueryErrorMsg('Card Issue Date Validation',arg1+" should be less than or equal to Today's Date and greater than or equal to Date Of Birth");
		}
	}
}
function populateAge(input)
{
document.getElementById("years").value=0;
document.getElementById("months").value=0;
document.getElementById("days").value=0;
document.getElementById("ageString").value="0~0~0";
var entered = input.value;
entered = entered.split("-");
var byr = parseInt(entered[2]); 
var bmth = parseInt(entered[1],10); 
var bdy = parseInt(entered[0],10);
var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
if(isNaN(byr))
{
	input.value="";
	jqueryErrorMsg('Date Of Birth Validation',"Select Date Of Birth");
}
else
{
DoB=Date.parse(DoB);
var dob=new Date(DoB);

var today= new Date();
var nowmonth = today.getMonth();
var nowday = today.getDate();
var nowyear = today.getFullYear();
var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
DoC=Date.parse(DoC);
var now=new Date(DoC);

var minYears= new Date();
minYears.setFullYear(minYears.getFullYear()-150);
var minDate=""+(minYears.getMonth()+1)+"/"+ minYears.getDate() +"/"+ minYears.getFullYear();
minDate=Date.parse(minDate);

if(DoB>DoC || DoB<minDate)
	{
	input.value="";
	jqueryErrorMsg('Date Of Birth Validation',"Date Of Birth should be less than Today's Date and age should be less than 150 years");
	}
else
	{
var yearDob = dob.getFullYear();
var monthDob =dob.getMonth();
var dateDob = dob.getDate();
var yearNow = now.getFullYear();
var monthNow = now.getMonth();
var dateNow = now.getDate();
var yearAge="";
var monthAge="";
var dateAge="";

yearAge = yearNow - yearDob;

if (monthNow >= monthDob)
  monthAge = monthNow - monthDob;
else {
  yearAge--;
   monthAge = 12 + monthNow -monthDob;
}

if (dateNow >= dateDob)
  dateAge = dateNow - dateDob;
else {
  monthAge--;
  if(monthAge<1 && yearAge<1 && monthDob==1)
	  {
	  dateAge = 28+dateNow-dateDob;
	  }
  else
  dateAge = 31 + dateNow - dateDob;

  if (monthAge < 0) {
    monthAge = 11;
    yearAge--;
  }
}
var year=""+yearAge;
var browserName=navigator.appName;
if(browserName=="Microsoft Internet Explorer")
	{
	document.getElementById('years').value=year;
	document.getElementById('ageString').value=year+"~"+monthAge+"~"+dateAge;
	
	}
  else
	  {
	  document.getElementById('years').value=yearAge;
	  document.getElementById('ageString').value=yearAge+"~"+monthAge+"~"+dateAge;
	  }

document.getElementById("months").value=monthAge;
document.getElementById("days").value=dateAge;
	}
	}
}

//End Of patient and telephonic registration details
function validatePropSurgeryDate(arg1,input)
{
	var entered = input.value;
	entered = entered.split("-");
	var byr = parseInt(entered[2]); 
	if(isNaN(byr))
	{
		input.value="";
		jqueryErrorMsg('Proposed Surgery Date Validation',"Select "+arg1);
	}
	else
	{
	var bmth = parseInt(entered[1],10); 
	var bdy = parseInt(entered[0],10);
	var DoB=""+(bmth)+"/"+ bdy +"/"+ byr;
	DoB=Date.parse(DoB);
	var today= new Date();
	var nowmonth = today.getMonth();
	var nowday = today.getDate();
	var nowyear = today.getFullYear();
	var DoC=""+(nowmonth+1)+"/"+ nowday +"/"+ nowyear;
	DoC=Date.parse(DoC);
	if(DoC>DoB)
		{
		input.value="";
		jqueryErrorMsg('Proposed Surgery Date Validation',arg1+" should be greater than or equal to today's date");
		}
	else
		{
	var maxSurgDate = new Date();
	maxSurgDate.setMonth(maxSurgDate.getMonth()+3);
	var maxSurgCal=""+(maxSurgDate.getMonth()+1)+"/"+maxSurgDate.getDate()+"/"+ maxSurgDate.getFullYear();
	maxSurgCal=Date.parse(maxSurgCal);
	if(DoB>maxSurgCal)
		{
		input.value="";
		jqueryErrorMsg('Proposed Surgery Date Validation',arg1+" should not be greater than 3 months from today's date");
		}
		}
	}
	
}
 //Common for Diagnosis Details in telephonic and case registration

function getDiagnMainCatList()
{
	document.getElementById("diagCode").value="";
	document.forms[0].mainCatName.options.length=1;
	document.getElementById("mainCatCode").value="";
	getDiagnCategoryList();
	if(document.forms[0].diagType.value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("diagCode").value=document.forms[0].diagType.value;
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("@]*","");            
            		var mainCatList = resultArray.split("@,"); 
            		if(mainCatList.length>0)
            		{  
                		document.forms[0].mainCatName.options.length=0;
                        document.forms[0].mainCatName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<mainCatList.length;i++)
                        {	
                        	var arr=mainCatList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].mainCatName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnMainCategory&callType=Ajax&lStrDiagnId="+document.forms[0].diagType.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDiagnCategoryList()
{
	document.getElementById("mainCatCode").value="";
	document.getElementById("catName").options.length=1;
	document.getElementById("catCode").value="";
	getDiagnSubCategoryList();
	if(document.forms[0].mainCatName.value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("mainCatCode").value=document.forms[0].mainCatName.value;
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
            		resultArray = resultArray.replace("@]*","");            
            		var categoryList = resultArray.split("@,"); 
            		if(categoryList.length>0)
            		{  
                		document.forms[0].catName.options.length=0;
                        document.forms[0].catName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<categoryList.length;i++)
                        {	
                        	var arr=categoryList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].catName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCategory&callType=Ajax&lStrDiagnMainId="+document.forms[0].mainCatName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDiagnSubCategoryList()
{
	document.getElementById("catCode").value="";
	document.forms[0].subCatName.options.length=1;
	document.forms[0].diseaseName.options.length=1;
	document.forms[0].disAnatomicalName.options.length=1;
	document.getElementById("subCatCode").value="";
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("catName").value=="-1")
	{
		return false;
	}
	else
	{
	document.getElementById("catCode").value=document.getElementById("catName").value;
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
            else
            {
            	if(resultArray!=null)
            	{
            		var finalSubDetailsList = resultArray.split("$"); 
            		var subCategory;
            		var disease;
            		var disAnatomical;
            		if(finalSubDetailsList.length>0)
            		{  
            			subCategory=finalSubDetailsList[0];
            			disease=finalSubDetailsList[1];
            			disAnatomical=finalSubDetailsList[2];
            		}
            		subCategory = subCategory.replace("[","");
            		subCategory = subCategory.replace("@]",""); 
            		var subCategoryList = subCategory.split("@,"); 
            		if(subCategoryList.length>0)
            		{  
				   		document.forms[0].subCatName.options.length=0;
                        document.forms[0].subCatName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<subCategoryList.length;i++)
                        {	
                              var arr=subCategoryList[i].split("~");
                              if(arr[1]!=null && arr[0]!=null)
                              {
                            	  var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                            	  var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                            	  document.forms[0].subCatName.options[i+1] =new Option(val1,val2);
                              }
                        }
            		}
            		disease = disease.replace("[","");
            		disease = disease.replace("@]","");            
            		var diseaseList = disease.split("@,"); 
            		if(diseaseList.length>0)
            		{  
                		document.forms[0].diseaseName.options.length=0;
                        document.forms[0].diseaseName.options[0]=new Option("---select---","-1");
                        for(var i = 0; i<diseaseList.length;i++)
                        {	
                        	var arr=diseaseList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].diseaseName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
				
            		disAnatomical = disAnatomical.replace("[","");
            		disAnatomical = disAnatomical.replace("@]*","");  
            		var disAnatomicalList = disAnatomical.split("@,"); 
            		if(disAnatomicalList.length>0)
            		{  
                		document.forms[0].disAnatomicalName.options.length=0;
                        document.forms[0].disAnatomicalName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<disAnatomicalList.length;i++)
                        {	
                        	var arr=disAnatomicalList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].disAnatomicalName.options[i+1] =new Option(val1,val2);
                        	}
                        }	
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCatSubDetails&callType=Ajax&lStrListType=categoryId&lStrDiagnCode="+document.forms[0].catName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}

function getDiagnDiseaseList()
{
	document.forms[0].diseaseName.options.length=1;
	document.forms[0].disAnatomicalName.options.length=1;
	document.getElementById("subCatCode").value="";
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("subCatName").value=="-1")
		{
			return false;
		}
		else
			{
	document.getElementById("subCatCode").value=document.getElementById("subCatName").value;
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
            else
            {
            	if(resultArray!=null)
            	{
            		var finalSubDetailsList = resultArray.split("$"); 
            		var disease;
            		var disAnatomical;
            		if(finalSubDetailsList.length>0)
            		{  
            			disease=finalSubDetailsList[0];
            			disAnatomical=finalSubDetailsList[1];
            		}
		
            		disease = disease.replace("[","");
            		disease = disease.replace("@]","");            
            		var diseaseList = disease.split("@,"); 
            		if(diseaseList.length>0)
            		{  
                		document.forms[0].diseaseName.options.length=0;
                        document.forms[0].diseaseName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<diseaseList.length;i++)
                        {	
                        	var arr=diseaseList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].diseaseName.options[i+1] =new Option(val1,val2);
                        	}
                        }
            		}
				
            		disAnatomical = disAnatomical.replace("[","");
            		disAnatomical = disAnatomical.replace("@]*","");            
            		var disAnatomicalList = disAnatomical.split("@,"); 
            		if(disAnatomicalList.length>0)
            		{  
                		document.forms[0].disAnatomicalName.options.length=0;
                        document.forms[0].disAnatomicalName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<disAnatomicalList.length;i++)
                        {	
                        	var arr=disAnatomicalList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].disAnatomicalName.options[i+1] =new Option(val1,val2);
                        	}
                        }	
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCatSubDetails&callType=Ajax&lStrListType=subCategoryId&lStrDiagnCode="+document.forms[0].subCatName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
			}
}
function getDiagnDisAnatomicalList()
{
	document.forms[0].disAnatomicalName.options.length=1;
	document.getElementById("diseaseCode").value="";
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("diseaseName").value=="-1")
	{
		return false;
	}
	else
		{
	document.getElementById("diseaseCode").value=document.getElementById("diseaseName").value;
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
            else
            {
            	if(resultArray!=null)
            	{
            		var finalSubDetailsList = resultArray.split("$"); 
            		var disAnatomical;
            		if(finalSubDetailsList.length>0)
            		{  
            			disAnatomical=finalSubDetailsList[0];
            		}
				 
            		disAnatomical = disAnatomical.replace("[","");
            		disAnatomical = disAnatomical.replace("@]*","");            
            		var disAnatomicalList = disAnatomical.split("@,"); 
            		if(disAnatomicalList.length>0)
            		{  
                		document.forms[0].disAnatomicalName.options.length=0;
                        document.forms[0].disAnatomicalName.options[0]=new Option("---select---","-1");
            		
                        for(var i = 0; i<disAnatomicalList.length;i++)
                        {	
                        	var arr=disAnatomicalList[i].split("~");
                        	if(arr[1]!=null && arr[0]!=null)
                        	{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                        		document.forms[0].disAnatomicalName.options[i+1] =new Option(val1,val2);
                        	}	
                        }	
            		}
            	}
            }
        }
    }
    	
	url = "./patientDetails.do?actionFlag=getDiagnCatSubDetails&callType=Ajax&lStrListType=diseaseId&lStrDiagnCode="+document.forms[0].diseaseName.value;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getDisAnatomicalCode()
{
	document.getElementById("disAnatomicalCode").value="";
	if(document.getElementById("disAnatomicalName").value=="-1")
		{
		return false;
		}
	else
		{
	document.getElementById("disAnatomicalCode").value=document.getElementById("disAnatomicalName").value;
		}
}
//End Of Diagnosis Details

function viewTelephonicInfo(telephonicId){
	window.open('./patientDetails.do?actionFlag=ViewTelephonicDtls&telephonicId='+telephonicId,'','scrollbars=1,left=20,top=20,width=1000,height=650');
}
function resizePatImage(id,width,height)
 {
 var patImage=document.getElementById(id);
 patImage.setAttribute("width",width);
  patImage.setAttribute("height",height);
 }    
//Used to validate consecutive special characters and spaces
function consecutiveSpecialChar(value)
{
	var str = /\W/g;
	var Symbol = "";
	var count=0;
	if (value.match(str))
	{
		var var_length = value.length;
		var i = 0;
		for (i = 0; i < var_length-1; i++) 
		{
			symbol1 = value.charAt(i);
			symbol2 = value.charAt(i+1);
			if ((symbol1.match(str) && symbol2.match(str))) {
				return false;
			}
			else
			{
				count=count+1;
			}
		}
		if(count==var_length-1)
		{
			return true;
		}
	}
	else
	{
		return true;
	}

}

function getChemicalSubGrp(){
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("pSubGrpCode").value="";
	document.forms[0].cSubGrpName.options.length=1;
	if(document.getElementById("pSubGrpName").value=="-1")
		{
		return false;
		}
	else
		{
	var pSubGrpCode=document.getElementById("pSubGrpName").value;
	document.getElementById("pSubGrpCode").value=pSubGrpCode;
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
                	var drugList = resultArray.split("@,"); 
                	if(drugList.length>0)
                	{  
                		document.forms[0].cSubGrpName.options.length=0;
                        document.forms[0].cSubGrpName.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<drugList.length;i++)
               		 	{	
                     		var arr=drugList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].cSubGrpName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    /*if(chronicId!=null)
	{
	url = "./chronicAction.do?actionFlag=getOpChemSubList&callType=Ajax&pharSubCode="+pSubGrpCode;
	}
else
	{*/
	url = "./patientDetails.do?actionFlag=getOpChemSubList&callType=Ajax&pharSubCode="+pSubGrpCode;
	/*}*/
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}


function getDrugDetails()
{
	document.getElementById("asriDrugCode").value="";
	if(document.getElementById("drugName").value=="-1")
	{
	return false;
	}
else
	{document.getElementById("asriDrugCode").value=document.getElementById("drugName").value;
	}
}

function getRouteTypeList()
{
	
	
	//document.forms[0].routeType.options.length=1;
	document.getElementById("asriDrugCode").value="";
	document.getElementById("dosage").value="-1";
	document.getElementById("medicatPeriod").value="";
	/*document.getElementById("batchNo").value="";
	document.getElementById("expiryDt").value="";*/
	
	if(document.getElementById("drugName").value=="-1")
	{
		
		
	return false;
	}
else
	{
	var actcode=document.getElementById("drugName").value;
	document.getElementById("asriDrugCode").value=actcode;	
	
	
	/*var xmlhttp;
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
            		if(resultArray.replace("*","")>0)
            		{
            			jqueryAlertMsg("Therapy Category Validation","Therapy "+ opCatName+" is not allowed for this Employee/Pensioner to register for ChronicOP");
            		}
            		else
            		{
            			resultArray = resultArray.replace("[","");
                		resultArray = resultArray.replace("@]",""); 
                		var routeTypeList = resultArray.split("@,"); 
               	 		if(routeTypeList.length>0)
                		{  
                			document.forms[0].routeType.options.length=0;
                    		document.forms[0].routeType.options[0]=new Option("---select---","-1");
                			for(var i = 0; i<routeTypeList.length;i++)
               			 	{	
                           		var arr=routeTypeList[i].split("~");
	                   		 	if(arr[1]!=null && arr[0]!=null)
                     			{
                         	 		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        	 		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       		 		document.forms[0].routeType.options[i+1] =new Option(val1,val2);
                     			}
                			}
           				}
            		}
            	}
        	}
        }
    }
    
	url = "./chronicAction.do?actionFlag=getRouteTypeList&callType=Ajax&actCode="+actcode;
    	
  
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);*/
	}
}
/*function getStrengthTypeList()
{
	
	document.forms[0].strengthType.options.length=1;

	if(document.getElementById("drugName").value=="-1")
	{
	return false;
	}
	else
	{
	var actcode=document.getElementById("drugName").value;
	document.getElementById("asriDrugCode").value=actcode;	
	
	var xmlhttp;
    var url;
    var chronicId=document.getElementById("patientNo").value;

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
            		if(resultArray.replace("*","")>0)
            		{
            			jqueryAlertMsg("Therapy Category Validation","Therapy "+ opCatName+" is not allowed for this Employee/Pensioner to register for ChronicOP");
            		}
            		else
            		{
            			resultArray = resultArray.replace("[","");
                		resultArray = resultArray.replace("@]",""); 
                		var strengthTypeList = resultArray.split("@,"); 
               	 		if(strengthTypeList.length>0)
                		{  
                			document.forms[0].strengthType.options.length=0;
                    		document.forms[0].strengthType.options[0]=new Option("---select---","-1");
                			for(var i = 0; i<strengthTypeList.length;i++)
               			 	{	
                           		var arr=strengthTypeList[i].split("~");
	                   		 	if(arr[1]!=null && arr[0]!=null)
                     			{
                         	 		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        	 		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       		 		document.forms[0].strengthType.options[i+1] =new Option(val1,val2);
                     			}
                			}
           				}
            		}
            	}
        	}
        }
    }
    
    	url = "./chronicAction.do?actionFlag=getStrengthTypeList&callType=Ajax&actCode="+actcode;
    	
  
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}*/
function getRouteList(){
	var jq = jQuery.noConflict();
	
	if(document.getElementById("routeType").value=="-1")
		{
		return false;
		}
	else
		{
	var routeTypeCode=document.getElementById("routeType").value;
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
                	var routeList = resultArray.split("@,"); 
                	if(routeList.length>0)
                	{  
                		document.forms[0].route.options.length=0;
                        document.forms[0].route.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<routeList.length;i++)
               		 	{	
                     		var arr=routeList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].route.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    url = "./patientDetails.do?actionFlag=getRouteList&callType=Ajax&routeTypeCode="+routeTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
		document.getElementById("route").value="-1";
		document.getElementById("strengthType").value="-1";
		document.getElementById("strength").value="-1";
		document.getElementById("dosage").value="-1";
		document.getElementById("medicatPeriod").value="";
		jq("#route, #strengthType, #strength, #dosage").select2().val('');
}
function getStrengthList(){
	var jq = jQuery.noConflict();
	if(document.getElementById("strengthType").value=="-1")
		{
		return false;
		}
	else
		{
	var strengthTypeCode=document.getElementById("strengthType").value;
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
                	var strengthList = resultArray.split("@,"); 
                	if(strengthList.length>0)
                	{  
                		document.forms[0].strength.options.length=0;
                        document.forms[0].strength.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<strengthList.length;i++)
               		 	{	
                     		var arr=strengthList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 	document.forms[0].strength.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    url = "./patientDetails.do?actionFlag=getStrengthList&callType=Ajax&strengthTypeCode="+strengthTypeCode;
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
		document.getElementById("strength").value="-1";
		document.getElementById("dosage").value="-1";
		document.getElementById("medicatPeriod").value="";
		jq("#strength, #dosage").select2().val('');
}



function getChronicIcdList()
{
	var chronicId=document.getElementById("patientNo").value;
	document.getElementById("opPkgCode").value="";
	document.forms[0].opIcdName.options.length=1;
	getOpIcdCode();
	if(document.getElementById("opPkgName").value=="-1")
		{
		return false;
		}
	else
		{
	var opPkgCode=document.getElementById("opPkgName").value;
	document.getElementById("opPkgCode").value=opPkgCode;
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
            else
            {
            	if(resultArray!=null)
            	{
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]","");            
                	var opIcdList = resultArray.split("@,"); 
                	if(opIcdList.length>0)
                	{  
                		document.forms[0].opIcdName.options.length=0;
                        document.forms[0].opIcdName.options[0]=new Option("---select---","-1");
                		for(var i = 0; i<opIcdList.length;i++)
               		 	{	
                     		var arr=opIcdList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.forms[0].opIcdName.options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    }
    	/*if(chronicId!=null){
    		
    	}
    	else
    		{*/
    url = "./chronicAction.do?actionFlag=getOpIcdList&callType=Ajax&lStrOpPkgCode="+opPkgCode;
}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	/*}*/
}


function getOpIcdCode()
{
	document.forms[0].opIcdCode.value="";
	if(document.forms[0].opIcdName.value=="-1")
		{
		return false;
		}
	else
		{
		document.forms[0].opIcdCode.value=document.forms[0].opIcdName.value;
		}
}
function enableIPOP()
{
	var jq = jQuery.noConflict();
	var hospGovu = document.getElementById("hospGovu").value;
	var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;
	
	var hospId = document.getElementById("hospitalId").value;
	
	var caseId=document.getElementById("caseId").value;
	if(hospGovu!=null && hospGovu!="G")
		{
	document.getElementById("diagType").value=-1;
	document.getElementById("diagCode").value="";
	document.getElementById("mainCatName").options.length = 1;
	document.getElementById("mainCatCode").value = "";
	document.getElementById("catName").options.length = 1;
	document.getElementById("catCode").value= "";
	document.getElementById("subCatName").options.length = 1;
	document.getElementById("subCatCode").value = "";
	document.getElementById("diseaseName").options.length = 1;
	document.getElementById("diseaseCode").value = "";
	document.getElementById("disAnatomicalName").options.length = 1;
	document.getElementById("disAnatomicalCode").value = "";
		}
	catCount=0;
	
	
	
	otherDocDetails=new Array();
	document.getElementById("otherDocDetailsList").value=otherDocDetails;
	
	if(hospGovu!=null && hospGovu!="G")
	{
	var existDrugsTable = document.getElementById("existDrugs");
	for(var count = existDrugsTable.rows.length - 1 ; count>0; count--)
		{
		existDrugsTable.deleteRow(count);
		}
	if(document.getElementById("existDrugs")!=null)
		document.getElementById("existDrugs").style.display="none";
	if(document.getElementById("existDrugsHead")!=null)
		document.getElementById("existDrugsHead").style.display="none";
	if(document.getElementsByClassName("existDrugsHead")!=null)
		jq(".existDrugsHead").hide();
	}
	
	if(document.forms[0].patientType[1].checked)
		{
		if(document.getElementById("toDispatch"))
		document.getElementById("toDispatch").disabled=true;
		
		if(schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
		{
		if(hospGovu!=null && hospGovu!="G")
		
			{
		jq('.onlyAp').css('display','');
		
		
		document.getElementById("onlyIp1").style.display="";
		document.getElementById("onlyIp2").style.display="";
		
		document.getElementById("docNameList").style.display="";
		document.getElementById("docName").style.display="";
		document.getElementById("doctorName").style.display="";
			}
		//document.getElementById("ipNote2").style.display="";
		document.getElementById("ipNote1").style.display="";
		document.getElementById("opNote").style.display="none";
		document.getElementById("Save").style.display="";
		document.getElementById("Reset").style.display="";
	
		if(document.getElementById("empPastHistory"))
		document.getElementById("empPastHistory").style.display="none";
		
		}

		document.getElementById("asriCatName").value=-1;
		//if(hospId!=null && hospId!="EHS34")
		if(hospGovu!=null && hospGovu!="G")	
		{
		document.getElementById("ipDiagnosedBy").value="-1";
		document.getElementById("ipDoctorName").options.length= 1;
		}
		
		document.getElementById("IPHead2").style.display="";
		document.getElementById("OPHead2").style.display="none";
		document.getElementById("prescriptionData").style.display="none";
		document.getElementById("OPDoctor").style.display="none";
		document.getElementById("diagnosisData").style.display="";
		}
		
		
	else if(document.forms[0].patientType[0].checked)
		{
		if(document.getElementById("toDispatch"))
		document.getElementById("toDispatch").disabled=false;
		//if((schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")|| hospId=="EHS34")
		if((schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")|| hospGovu=="G")
		{
			if(document.getElementById("onlyIp1"))
			document.getElementById("onlyIp1").style.display="none";
			if(document.getElementById("onlyIp2"))
			document.getElementById("onlyIp2").style.display="none";
			document.getElementById("ipNote1").style.display="none";
			//document.getElementById("ipNote2").style.display="none";
			document.getElementById("opNote").style.display="";
			
			document.getElementById("docName").style.display="none";
			document.getElementById("docNameList").style.display="none";
			document.getElementById("doctorName").style.display="none";
			
			if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="";
			
			document.getElementById("addAttach").style.display="";
			
			jq('.onlyAp').css('display','none');
			var opActiveMsg=document.getElementById("opActiveMsg").value;
			
			if(opActiveMsg!=null && opActiveMsg!='')
			{
				document.getElementById("Save").style.display="none";
				document.getElementById("Reset").style.display="none";	
				//enable_dtrsform();
			}
		
		}
		
		if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="";
		//if(hospId!=null && hospId!="EHS34")
			if(hospGovu!=null && hospGovu!="G")
			{
		document.getElementById("drugTypeCode").value=-1;
		document.getElementById("diagnosedBy").value="-1";
		document.getElementById("dosage").value="-1";
		document.getElementById("medicatPeriod").value="";
				
		drugCount=0;
		
		var drugTable = document.getElementById("drugsTable");
		for(var count = drugTable.rows.length-1 ; count>0; count--)
			{
			drugTable.deleteRow(count);
			}
		document.getElementById("drugsTable").style.display="none";
			}
		drugs=new Array();
		drugsTemp=new Array();
		
		document.getElementById("opNo").value="";
		document.getElementById("opRemarks").value="";
		
		document.forms[0].doctorName.options.length=1;
		document.getElementById("docNameList").style.display="";
		document.getElementById("docNametext").style.display="none";
		document.getElementById("otherDocName").value="";
		document.getElementById("doctorDataDiv").style.display="none";
		document.getElementById("doctorData").style.display="none";
		document.getElementById("docRegNo").value="";
		document.getElementById("docQual").vlaue="";
		document.getElementById("docMobileNo").value="";
		
		//document.getElementById("ChronicOPPrescription").style.display="none";
		//document.getElementById("chronicTherapy").style.display="none";
		document.getElementById("IPHead2").style.display="none";
		document.getElementById("OPHead2").style.display="";
		
		document.getElementById("prescriptionData").style.display="";
		document.getElementById("OPDoctor").style.display="";
		document.getElementById("diagnosisData").style.display="";
	//	document.getElementById("ChronicOPTherapy").style.display="none";
		}
	else if(document.forms[0].patientType[2] && document.forms[0].patientType[2].checked)
	{
	

		document.getElementById("OPDoctor").style.display="";
		
		
		document.getElementById("IPHead2").style.display="none";
		document.getElementById("OPHead2").style.display="none";
		document.getElementById("prescriptionData").style.display="none";
		document.getElementById("OPDoctor").style.display="none";
		document.getElementById("diagnosisData").style.display="none";
		
		
	}
	//parent.fn_resizePage();
}



function fn_getDoctorsDetails(){
 	document.getElementById("docNameList").style.display='';
 	document.getElementById("docNametext").style.display='none';
 	document.getElementById("doctorData").style.display='none';
 	document.getElementById('doctorDataDiv').style.display='none';
 	if(document.forms[0].doctorName.value=="-1")
 		{
 		return false;
 		}
 	else
 		{
		var xmlhttp;
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
		var doctorType=document.forms[0].diagnosedBy.value;
		var hospId = document.getElementById("hospitalId").value;
		var selVal=document.forms[0].doctorName.selectedIndex;        
		var doctorId=document.forms[0].doctorName.options[selVal].value;        
		//document.forms[0].doctorId.value=doctorId;       
		var url;
		var dd3;
		if(doctorId == '0')
		{
		document.getElementById("docNameList").style.display='none';
        document.getElementById("docNametext").style.display='';
        document.getElementById("doctorData").style.display='';
        document.getElementById('doctorDataDiv').style.display='none';
        document.getElementById("docRegNo").value="";
        document.getElementById("docQual").value="";
        document.getElementById("docMobileNo").value="";
     	}
		else
		{
		var val1='';val2='';val3='';
    	url = './patientDetails.do?actionFlag=getDoctorsDetails&callType=Ajax&hospId='+hospId+'&doctorType='+doctorType+'&doctId='+doctorId;	
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
           	resultArray = resultArray.replace("*","");
            if(resultArray.length>0)
           	{   
            	var arr=resultArray.split("~");                     
                val1=arr[0];
                val2=arr[2];
                val3=arr[1];
            } 
            var details="";
			document.getElementById('doctorDataDiv').style.display='';
			dd3= document.getElementById('doctorDataDiv');
			document.forms[0].docRegNo.value=val1;
			document.forms[0].docQual.value=val2;
			document.forms[0].docMobileNo.value=val3; 
			details="<table width=100% border=0 align=left cellSpacing=0 cellPadding=0 >";
            details=details+"<tr><td width='30%'><b>Registration No:</b>"+val1+"</td><td width='30%'><b>Qualification:</b>"+val2+"</td><td width='30%'><b>Contact No:</b>"+val3+"</td><td>&nbsp;</td></tr>";		
            details=details+"</table>";
			dd3.innerHTML=details;
       		}
       		}			
    	}
	}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
 	}
	//parent.fn_resizePage();
}
function fn_getIPDoctorsDetails(){

  	if(document.forms[0].docSpecReg.value=="-1")
  	{
  		return false;
  	}
  	else
  	{
		var hospId = document.getElementById("hospitalId").value;
  		var doctorId=document.forms[0].docSpecReg.value;   
		var selVal=document.forms[0].docSpecReg.selectedIndex;		
		if(doctorId == '0')
		{
        	document.getElementById("ipDoctorData").style.display='';
			document.getElementById("ipOtherDocName").value="";
       		document.getElementById("ipDocRegNo").value="";
        	document.getElementById("ipDocQual").value="";
        	document.getElementById("ipDocMobileNo").value="";
     	}
		else
		{
			document.getElementById("ipDoctorData").style.display='none';
			document.getElementById("ipDocRegNo").value=document.forms[0].docSpecReg.options[selVal].value;
			document.getElementById("ipOtherDocName").value=document.forms[0].docSpecReg.options[selVal].text;
			document.getElementById("ipDocQual").value="";
        	document.getElementById("ipDocMobileNo").value="";
		}

    }
  //parent.fn_resizePage();
}

function getSubLevelDetails(input)
{
	if(input.checked)
	{
		
		var personalHabits='';
		var KnownAllg='';
		if(input.name=="Known Allergies")
			{
			if(input.value=="PR5.1")
			{
				KnownAllg=KnownAllg+'<table width="100%" border="1"><tr><td>'+
				'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
				'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
                
				document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
			}else
				{  
			document.getElementById("Known AllergiesTd").innerHTML="";
				}
			}
		if(input.name=="Habits/Addictions")
		 {
			if(input.value=="PR6.1")
			{
		  personalHabits=personalHabits+'<table width="100%" border="1"><tr><td>'+
	      'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Regular'+
	      '<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>Occasional'+
	      '<input type="radio" name="alcohol" id="alcohol" value="Teetotaler" title="Teetotaler"/>Teetotaler </td></tr>'+
	     	'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	      '<div id="smokingTd" style="display:none">'+
	     'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	      'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	      '<tr><td>Drug Use:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
	      '<tr><td>Betel nut:<input type="radio" name="betelNut" id="betelNut" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="betelNut" id="betelNut" value="No" title="No"/>No</td></tr>'+
	      '<tr><td>Betel leaf:<input type="radio" name="betelLeaf" id="betelLeaf" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="betelLeaf" id="betelLeaf" value="No" title="No"/>No</td></tr></table>';
		document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
			}
		else
			{
		document.getElementById("Habits/AddictionsTd").innerHTML="";
			}
		}
	}
 else
	{
	document.getElementById("Habits/AddictionsTd").innerHTML="";
	} 
	// parent.fn_resizePage();
}
function getSubLevelDetailsNims(input)
{

	if(input.checked)
	{
		var personalHabits='';
		var KnownAllg='';
		if(input.name=="Known Allergies")
			{
			if(input.value=="PR5.1")
			{
				KnownAllg=KnownAllg+'<table width="100%" border="1"><tr><td>'+
				'Allergic to Medicine:<input type="radio" name="AllMed" id="AllMed" value="AllMedYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllMed" id="AllMed" value="AllMedNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllMedDiv" style="display:none">Specify:<input type="text" name="AllMedRemrk" id="AllMedRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr>'+
				'<tr><td>Allergic to Substance other than medicine:<br><input type="radio" name="AllSub" id="AllSub" value="AllSubYes" onclick="displayTextBox(this)" title="Yes"/>Yes'+
				'<input type="radio" name="AllSub" id="AllSub" value="AllSubNo" onclick="displayTextBox(this)" title="No"/>No'+
				'<div id="AllSubDiv" style="display:none">Specify:<input type="text" name="AllSubRemrk" id="AllSubRemrk" maxlength="300" title="Remark" style="width:7em" onkeypress="return checkAlphaNumericCodes(event);" onchange="validateSpecify(this)"/></div></td></tr></table>';
                
				document.getElementById("Known AllergiesTd").innerHTML=KnownAllg;
			}else
				{  
			document.getElementById("Known AllergiesTd").innerHTML="";
				}
			}
		if(input.name=="Habits/Addictions")
		 {
			if(input.value=="PR6.1")
			{
		  personalHabits=personalHabits+'<table width="100%" border="1"><tr><td>'+
	      'Alcohol:<input type="radio" name="alcohol" id="alcohol" value="Regular" title="Regular"/>Yes'+
	      '<input type="radio" name="alcohol" id="alcohol" value="Occasional" title="Occasional"/>No'+
	      
	     	'<tr><td>Tobacco:<input type="radio" name="tobacco" id="tobacco" value="Snuff" onclick="displayPackYears(this)" title="Snuff"/>Snuff'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Chewable" onclick="displayPackYears(this)" title="Chewable"/>Chewable'+
	      '<input type="radio" name="tobacco" id="tobacco" value="Smoking" onclick="displayPackYears(this)" title="Smoking"/>Smoking'+
	      '<div id="smokingTd" style="display:none">'+
	     'Pack :<input type="text" name="packNo" id="packNo" maxlength="2" title="Smoking Pack No" style="width:7em" onchange="validateNumber(\'Smoking Pack No\',this)"/>  (per day)<br>'+
	      'Years:<input type="text" name="smokeYears" id="smokeYears" maxlength="2" title="Smoking Years" style="width:7em" onchange="validateNumber(\'Smoking Years\',this)"/>  (since years)</div></td></tr>'+
	      '<tr><td>Drug Abuse:<input type="radio" name="drugUse" id="drugUse" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="drugUse" id="drugUse" value="No" title="No"/>No</td></tr>'+
	      '<tr><td>Ghutka:<input type="radio" name="Ghutka" id="Ghutka" value="Yes" title="Yes"/>Yes'+
	      '<input type="radio" name="Ghutka" id="Ghutka" value="No" title="No"/>No</td></tr></table>';
		document.getElementById("Habits/AddictionsTd").innerHTML=personalHabits;
			}
		else
			{
		document.getElementById("Habits/AddictionsTd").innerHTML="";
			}
		}
	}
 else
	{
	document.getElementById("Habits/AddictionsTd").innerHTML="";
	} 
	// parent.fn_resizePage();

}
function validateSpecify(input)
{
	var a=input.value;
	var fr = partial(focusBox,input);
	if(a.trim()=="")
	{
	input.value="";
		jqueryErrorMsg('Text Validation',"Blank spaces are not allowed for Specify");
	return false;
	}
	if(a.charAt(0)==" ")
		{
		input.value="";
		jqueryErrorMsg('Text Validation',"Specify should not start with space");
		return false;
		}
}


function confirmRemoveRow(src,type)
{
	var fr;
	if(type=="speciality")
		{
		fr=partial(removeProcInvest,src);
		jqueryConfirmMsg('Delete Procedure Investigation Confirmation','Do you want to delete Procedure Investigation?',fr);
		}
	else if(type=="drug")
		{
		fr=partial(removeDrug,src);
		jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
		}
	else if(type=="drug1")
		{
		fr=partial(removeDrug1,src);
		jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
		}
	else if(type=="symptom")
	{
		fr=partial(removeSymptoms,src);
		jqueryConfirmMsg('Delete Symptom Confirmation',"Do you want to delete Symptom?",fr);
		}
	else if(type=="geninvestigations")
		{
		fr=partial(removeGenInvestigations,src);
		jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
		}
	else if(type=="geninvestigation")
	{
	fr=partial(deleteGenInvest,src);
	jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
	}
	else if(type=="ipinvestigations")
		{
		fr=partial(removeRow,src);
		jqueryConfirmMsg('Delete IP Investigation Confirmation',"Do you want to delete IP Investigation?",fr);
		}
	else if(type=='procSelected')
		{
		if(src.value!='0')
		{
			jqueryConfirmMsg('Delete Procedure Confirmation','Do you want to delete selected Procedure?',removeProcSelected);
		}
		}
	else if(type=="editDrug")
		{
		fr=partial(editDrug,src);
		jqueryConfirmMsg('Edit Drug Confirmation',"Do you want to edit drug?",fr);
		}
		//parent.fn_resizePage();
}

function confirmRemoveGenInvest(src,type,investId,price){
	fr=partial(removeGenInvestOnload,src,investId,price);
	jqueryConfirmMsg('Delete General Investigation Confirmation',"Patient Type IP details entered will be reset.Do you want to delete general investigations?",fr);
}
function confirmRemoveChronicInvest(src,type,investId){
	fr=partial(removeGenInvestOnload,src,investId,'');
	jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete general investigations?",fr);
}
function confirmRemoveSymOnload(src,type,id){
	if(type=="drug")
	{
	fr=partial(removeDrugAjax,src,id);
	jqueryConfirmMsg('Delete Drug Confirmation','Do you want to delete Drug?',fr);
	}
else if(type=="symptom")
{
	fr=partial(removeSymptomsAjax,src,id);
	jqueryConfirmMsg('Delete Symptom Confirmation',"Do you want to delete Symptom?",fr);
	}
}
function confirmRemoveRowOnload(src,type,procCode,investId,asriCatCode)
{
		fr=partial(removeProcInvestOnload,src,procCode,investId,asriCatCode);
		jqueryConfirmMsg('Delete Procedure Investigation Confirmation','Do you want to delete Procedure Investigation?',fr);
       // parent.fn_resizePage();
}
function commonCodeinRemove(procWiseLst,procCode)
	{
		var count=0;
		var newProcCode=null;
		if(procWiseLst!=null && procWiseLst.length>0)
			{
				for(var j=0;j<procWiseLst.length;j++)
					{
						var splitProcCombo=procWiseLst[j].split(",");
						if(splitProcCombo[0].indexOf(procCode+"!@#")>-1)
							{
								procWiseLst.splice(j,1);
								count++;
							}
					}
				if(count>0)
					{
						for(var j=0;j<procWiseLst.length;j++)
							{
								if(newProcCode==null || newProcCode=='' || newProcCode==' ')
									newProcCode=procWiseLst[j];
								else
									newProcCode=newProcCode+"$"+procWiseLst[j];
							}	
					}
				else
					newProcCode='N';
			}
		return newProcCode;
	}
function removeComboProc(procCode)
	{
		var newProcCode=null;
		/*if(comboProcIds!=null && comboProcIds!='' && comboProcIds!=' ')
			{
				var procWiseLst=comboProcIds.split("$");
				var oldLength=procWiseLst.length;
				newProcCode=commonCodeinRemove(procWiseLst,procCode);//Check whether the Proc Code contains in Existing Combo Codes
				if((newProcCode!=null && newProcCode!='' && newProcCode!=' ' && newProcCode!='N') || (oldLength!=null && oldLength==1 && (newProcCode==null || newProcCode=='' || newProcCode==' ')))
					comboProcIds=newProcCode;
			}*/
		if(nonComboProcIds!=null && nonComboProcIds!='' && nonComboProcIds!=' ')
			{
				newProcCode=null;
				var procWiseLst=nonComboProcIds.split("$");
				var oldLength=procWiseLst.length;
				newProcCode=commonCodeinRemove(procWiseLst,procCode);//Check whether the Proc Code contains in Existing Non Combo Codes	
				if((newProcCode!=null && newProcCode!='' && newProcCode!=' ' && newProcCode!='N') || (oldLength!=null && oldLength==1 && (newProcCode==null || newProcCode=='' || newProcCode==' ')))
					nonComboProcIds=newProcCode;
			}
		if(standaloneProcIds!=null && standaloneProcIds!='' && standaloneProcIds!=' ')
			{
				newProcCode=null;
				var procWiseLst=standaloneProcIds.split("$");
				var oldLength=procWiseLst.length;
				newProcCode=commonCodeinRemove(procWiseLst,procCode);//Check whether the Proc Code contains in Existing Stand Alone Non Combo Codes
				if((newProcCode!=null && newProcCode!='' && newProcCode!=' ' && newProcCode!='N') || (oldLength!=null && oldLength==1 && (newProcCode==null || newProcCode=='' || newProcCode==' ')))
					standaloneProcIds=newProcCode;
			}
	}
function removeProcInvestOnload(src,procCode,investId,asriCatCode){
	
	deleteLst.push(procCode);
	var localSchemeId=document.getElementById("scheme").value;
	if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
		removeComboProc(procCode);
	
    specRemove = specRemove+procCode+"~"+asriCatCode+"~"+investId+"@";		
	var oRow = src.parentNode.parentNode; 
	document.getElementById("categoryTableID").deleteRow(oRow.rowIndex);           		
	for(var i=0;i<specOld.length;i++)
		{
			if(procCode!='' && procCode!=null && procCode!=' ' )
				{
					if(specOld[i].indexOf(procCode,0)!=-1)
	   					specOld.splice(i,1);
				}		
			else if(investId!='' && investId!='NA' && investId!=null)
       			{
           			if(specOld[i].indexOf(investId,0)!=-1)
        				specOld.splice(i,1);
           		}
       	}
   		
    if(specOld.length==0)
    	
			{
				document.getElementById('categoryTableID').style.display='none';
				if(catCount==0)
					medOrSur='';
			}
}
function removeProcInvest(src)
{
		var oRow=src.parentNode.parentNode;
		var matchCount=0;
		var matchProcCount=0;
		var remSpecValues=spec[oRow.rowIndex-1].split("~");
		spec.splice(oRow.rowIndex-1,1);
		otherDocDetails.splice(oRow.rowIndex-1,1);
		//alert(remSpecValues[2]);
		for(var i=0;i<spec.length;i++)
		{
			var specValues=spec[i].split("~");
			if(specValues[2]==remSpecValues[2])
			{
			matchProcCount++;
			}
			if(specValues[2]==remSpecValues[2] && remSpecValues[0]=='' && remSpecValues[1]=='')
				{
				matchCount++;
				speciality=remSpecValues[0]+"~"+remSpecValues[1]+"~"+specValues[2]+'~'+specValues[3]+'~'+specValues[4]+'~'+specValues[5]+"~"+specValues[6]+"~"+specValues[7]+'~'+specValues[8]+'~'+specValues[9];
				spec[i]=speciality;
				}
			if(matchCount==1)
				break;
		}
		if(matchProcCount==0)
			{
				for(var p=0;p<procList.length;p++)
				{
				var procValues=procList[p].split("~");
				if(procValues[1]==remSpecValues[2])
					{
					procList.splice(p,1);
					var localSchemeId=document.getElementById("scheme").value;
					deleteLst.push(procValues[1]);
					if(localSchemeId!=null && localSchemeId!='' && localSchemeId=='CD202')
						removeComboProc(procValues[1]);
					}
				}
			}
		document.getElementById("speciality").value=spec;
		document.getElementById("otherDocDetailsList").value=otherDocDetails;
		document.getElementById("categoryTable").deleteRow(oRow.rowIndex);
		catCount--;
		for(var i=1;i<=catCount;i++)
			{
			
			var newRow=document.getElementById("categoryTable").rows[i];
			var snoCell=newRow.cells[0];
			//snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
			}
		
		if(catCount==0)
			{
			document.getElementById("categoryTable").style.display="none";
			if(specOld.length==0)
				medOrSur='';
			}
		if(!document.getElementById("telephonicId").value=='')
		{
			var therapy=document.getElementById("therapy").value;
			var therapyMatch=0;
			for(var c=0;c<spec.length;c++)
			{
				var specValues=spec[c].split("~");
				if(specValues[2]!=therapy)
				{
					therapyMatch++;
				}
			}
			if(therapyMatch==0)
			{
			//	document.getElementById("treatingDocLabel").style.display='none';
				//document.getElementById("treatingDocRemarks").innerHTML='';
			}
		}
		//parent.fn_resizePage();
}



function addDrugs()
{
	var jq = jQuery.noConflict();
	var otherDrugs=false;
	var OtherDrugName="";
	if(document.getElementById("drugOthers"))
    var otherDrugs=document.getElementById("drugOthers").checked;
    var drugId='';

if(!otherDrugs)

{
	
if(document.getElementById("drugTypeCode").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugTypeCode"));
	alert("Please select Drug Type");
	return false;
	}
if(document.getElementById("drugSubTypeCode").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugSubTypeCode"));
	alert("Please select Drug Name");
	return false;
	}




}

if(jq("#drugSubTypeCode option:selected").text()=="Others"){
	if(document.getElementById("otherDrugsTxt").value==""){
		var fr=partial(focusBox,document.getElementById("otherDrugsTxt"));
		alert("Please enter other drug name");
	return false;
	}
	OtherDrugName=document.getElementById("otherDrugsTxt").value;
}

/*Valid for other drugs and regular drugs*/

//if(document.getElementById("routeType").value==-1)
//	{
//	var fr=partial(focusBox,document.getElementById("routeType"));
//	alert("Please select Route ");
//	return false;
//	}
//if(document.getElementById("route").value==-1)
//{
//var fr=partial(focusBox,document.getElementById("route"));
//alert("Please select Route");
//return false;
//}
//if(document.getElementById("strengthType").value==-1)
//	{
//	var fr=partial(focusBox,document.getElementById("strengthType"));
//	alert("Please select Strength ");
//	return false;
//	}
//if(document.getElementById("strength").value==-1)
//{
//var fr=partial(focusBox,document.getElementById("strength"));
//alert("Please select Strength ");
//return false;
//}
/*if(document.getElementById("dosage").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("dosage"));
	alert("Please select Dosage");
	return false;
	}*/
//if(document.getElementById("medicatPeriod").value=="")
//	{
//	var fr=partial(focusBox,document.getElementById("medicatPeriod"));
//	alert("Please enter Medication Period");
//	return false;
//	}
if(document.getElementById("medicatPeriod").value >90)
{
	alert("Medication Period cannot be greater than 90 Days");
//document.forms[0].medicatPeriod.value='';
return false;
}

//}

var drugTable=document.getElementById("drugsTable");   
var drug="";
var rows = document.getElementById("drugInfo").getElementsByTagName("tr").length;

var selectedRowCount=0;
for(var i=0;i<rows-1;i++)
	 {
	
	 var id="Set"+i;
	 var setName="Set"+(i+1);
	 if(document.getElementById(id).checked===true)
	 {
		 selectedRowCount++;
		 var mstrDrugCode=document.getElementById(id).value;
		 var quanId="quan"+i;
		 var quantity=document.getElementById(quanId).value;
		 
		 if(drugs.length>0){
			for(var c=0;c<drugs.length;c++)
				{
				var drugValues=drugs[c].split("~");
				if(drugValues[13]!=null){
				var drugCode=drugValues[13].replace("@","").trim();
				if(drugCode==mstrDrugCode)
					{
					jqueryErrorMsg('Unique Drug Validation',"Drug Set already exists.Please select another Drug set");
					return false;
					}
				}
				}
			}	
		 		 
		 if(quantity===null || ""===quantity )
		 {
		 alert("Please Enter Quantity for Selected Drug.");
		 return false;
		 }
		 		 
//		 var batchId="batch"+i;
//		 var batchNo=document.getElementById(batchId).value;
//		 drugList=drugList+drugCode+"~"+quantity+"~"+batchNo+"@";
		
//			 if(document.getElementById(quanId).value=='')
//			 {
//				 alert("Please Enter quantity1");
//			  	focusBox(document.getElementById(quanId));
//			 }
//			 else{
	var newRow=drugTable.insertRow(-1);
	var newcell=newRow.insertCell(0);
	newcell.innerHTML='<td width="5%">'+parseInt(drugCount+1)+'</td>';
	
	newcell=newRow.insertCell(1);
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugTypeCode").options[document.getElementById("drugTypeCode").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(2);
	newcell.innerHTML='<td width="10%">'+document.getElementById("drugSubTypeCode").options[document.getElementById("drugSubTypeCode").selectedIndex].text+'</td>';
	newcell=newRow.insertCell(3);
	if(document.getElementById("routeType").value=="-1")
		newcell.innerHTML='<td width="5%">N/A</td>';
	else
		newcell.innerHTML='<td width="5%">'+document.getElementById("routeType").options[document.getElementById("routeType").selectedIndex].text+'</td>';	
	newcell=newRow.insertCell(4);
	if(document.getElementById("route").value=="-1")
		newcell.innerHTML='<td width="5%">N/A</td>';
	else
		newcell.innerHTML='<td width="10%">'+document.getElementById("route").options[document.getElementById("route").selectedIndex].text+'</td>';	
	newcell=newRow.insertCell(5);
	if(document.getElementById("strengthType").value=="-1")
		newcell.innerHTML='<td width="5%">N/A</td>';
	else
		newcell.innerHTML='<td width="5%">'+document.getElementById("strengthType").options[document.getElementById("strengthType").selectedIndex].text+'</td>';	
	newcell=newRow.insertCell(6);
	if(document.getElementById("strength").value=="-1")
		newcell.innerHTML='<td width="5%">N/A</td>';
	else
		newcell.innerHTML='<td width="10%">'+document.getElementById("strength").options[document.getElementById("strength").selectedIndex].text+'</td>';	
    newcell=newRow.insertCell(7);
	if(document.getElementById("dosage").value=="-1")
		newcell.innerHTML='<td width="5%">N/A</td>';
	else
		newcell.innerHTML='<td width="5%">'+document.getElementById("dosage").value+'</td>';
	newcell=newRow.insertCell(8);
	if(document.getElementById("medicatPeriod").value=="")
		newcell.innerHTML='<td width="5%">N/A</td>';
	else
		newcell.innerHTML='<td width="5%">'+document.getElementById("medicatPeriod").value+'</td>';
	newcell=newRow.insertCell(9);
	newcell.innerHTML='<td width="5%">'+quantity+'</td>';
	newcell=newRow.insertCell(10);
	newcell.innerHTML='<td width="5%">'+setName+'</td>';
	newcell=newRow.insertCell(11);
	newcell.innerHTML='<td width="5%"><input class="but" type="button" value="Delete" name='+parseInt(drugCount+1)+' id='+parseInt(drugCount+1)+' /></td>';
			  
	var deleteButName='';
    deleteButName=parseInt(drugCount+1);
	
	 document.getElementById(deleteButName).onclick = function(){
		 confirmRemoveRow(this,"drug");
		 };
		 drug=document.getElementById("drugTypeCode").value+"~"+document.getElementById("drugSubTypeCode").value+"~"+document.getElementById("drugSubTypeCode").value+"~"+
	        document.getElementById("drugSubTypeCode").value+"~"+document.getElementById("drugSubTypeCode").value+"~"+document.getElementById("routeType").value+"~"+
	        document.getElementById("route").value+"~"+document.getElementById("strengthType").value+"~"+document.getElementById("strength").value+"~"+document.getElementById("dosage").value+"~"+
	        document.getElementById("medicatPeriod").value+"~"+OtherDrugName+"~"+quantity+"~"+mstrDrugCode;
		 drugs[drugCount]=drug+"@";
		  document.getElementById("drugs").value=drugs;
		  drugCount++;
	 }
	 }
if(selectedRowCount==0){
	  alert("Please select atleast one drug set");
	  return false;
}
//if(selectedRowCount>0){
//	  for(var i=0;i<rows-1;i++)
//		 {
//			
//		
//		 var setId="Set"+i;
//		 var quanId="quan"+i;
//		 if(document.getElementById(setId).checked===true){
//			 if(document.getElementById(quanId).value=='')
//			 {
//				 alert("Please Enter quantity");
////			  	focusBox(document.getElementById(quanId));
//			  	return false;
//			 }
//		}
//		}
//	}
	if(drugCount>0)
		{
		document.getElementById("drugsTable").style.display="";
		}
	document.getElementById("drugTypeCode").value="-1";
	document.getElementById("drugSubTypeCode").value="-1";
	document.getElementById("routeType").value="-1";
	document.getElementById("route").value="-1";
	document.getElementById("strengthType").value="-1";
	document.getElementById("strength").value="-1";
	document.getElementById("dosage").value="-1";
	document.getElementById("medicatPeriod").value="";
//	if(document.getElementById("drugNameAuto"))
//	$("#drugNameAuto").select2('val','');
//	$("#drugTypeCode").select2().val('');
	jq("#drugTypeCode, #drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").select2().val('');
	document.getElementById("drugInfo").style.display="none";
//	document.getElementById("quantity").value="";
//	document.getElementById("OtherDrugsTxt").value="";
	//parent.fn_resizePage();
}

function check_quantity(quantity)
{
	
	 if(document.getElementById(quantity).value!=null)
	 {
		 var regAlphaNum=/^[0-9]+$/;
		 var quan=document.getElementById(quantity).value;
		 if(quan.trim().search(regAlphaNum)==-1)
			 {
			 alert("Only Numbers are allowed in Quantity");
			 document.getElementById(quantity).value="";
			 document.getElementById(quantity).focus();
			 return false;
			 }
	 }
}


function addChronicDrugs()
{

if(document.getElementById("drugName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("drugName"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Drug Name");
	return false;
	}
/*if(document.getElementById("routeType").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("routeType"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Route ");
	return false;
	}
if(document.getElementById("route").value==-1)
{
var fr=partial(focusBox,document.getElementById("route"));
jqueryAlertMsg('Drug Addition Required Fields',"Please select Route");
return false;
}
if(document.getElementById("strengthType").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("strengthType"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Strength ");
	return false;
	}
if(document.getElementById("strength").value==-1)
{
var fr=partial(focusBox,document.getElementById("strength"));
jqueryAlertMsg('Drug Addition Required Fields',"Please select Strength ");
return false;
}*/
if(document.getElementById("dosage").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("dosage"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please select Dosage");
	return false;
	}
if(document.getElementById("medicatPeriod").value=="")
	{
	var fr=partial(focusBox,document.getElementById("medicatPeriod"));
	jqueryAlertMsg('Drug Addition Required Fields',"Please enter Medication Period");
	return false;
	}
/*if(document.getElementById("batchNo").value=="" || document.getElementById("batchNo").value==null)
{
var fr=partial(focusBox,document.getElementById("batchNo"));
jqueryAlertMsg('Drug Addition Required Fields',"Please enter Batch Number");
return false;
}
if(document.getElementById("expiryDt").value=="" || document.getElementById("expiryDt").value==null)
{
var fr=partial(focusBox,document.getElementById("expiryDt"));
jqueryAlertMsg('Drug Addition Required Fields',"Please enter Drug Expiry Date");
return false;
}*/
var mediPeriod=document.forms[0].medicatPeriod.value;
if(mediPeriod>90)
{
	alert("Medication Period cannot be greater than 90 Days");
//document.forms[0].medicatPeriod.value='';
return false;
}
for(var c=0;c<drugs.length;c++)
	{
	var drugValues=drugs[c].split("~");
	if(drugValues[0]==document.getElementById("drugName").value)
		{
		jqueryErrorMsg('Unique Drug Validation',"Drug Name already added.Please select another Drug Name");
		return false;
		}
	}

	var drug=document.getElementById("drugName").value+"~"+document.getElementById("dosage").value+"~"+document.getElementById("medicatPeriod").value+"~"+""+"~"+"";
	drugs[drugCount]=drug+"@";
	document.getElementById("drugs").value=drugs;
	
	
	

	//getDrugSubTypeList();
	//parent.fn_resizePage();
}






function removeDrug(src)
{
		var oRow=src.parentNode.parentNode;
		drugs.splice(oRow.rowIndex-1,1);
		document.getElementById("drugs").value=drugs;
		document.getElementById("drugsTable").deleteRow(oRow.rowIndex);
		drugCount--;
		for(var i=1;i<=drugCount;i++)
			{
			var newRow=document.getElementById("drugsTable").rows[i];
			var snoCell=newRow.cells[0];
			snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
			}
		if(drugCount==0)
			{
			document.getElementById("drugsTable").style.display="none";
			if(document.getElementById("prescription"))
			document.getElementById("prescription").disabled=true;
			if(document.getElementById("prescription"))
			document.getElementById("prescription").className='butdisable';
			}
			//parent.fn_resizePage();
}
function removeSymptoms(src){
	var oRow=src.parentNode.parentNode;
	symptoms.splice(oRow.rowIndex-1,1);
	document.getElementById("symptoms").value=symptoms;
	document.getElementById("symptomsTable").deleteRow(oRow.rowIndex);
	symptomCount--;
	for(var i=1;i<=symptomCount;i++)
		{
		var newRow=document.getElementById("symptomsTable").rows[i];
		var snoCell=newRow.cells[0];
		snoCell.innerHTML='<td width="10%">'+parseInt(i)+'</td>';
		}
	if(symptomCount==0)
		{
		document.getElementById("symptomsTable").style.display="none";
		}
		//parent.fn_resizePage();
}
function addSymptoms()
{
	var otherSymptom=false;
	var symptomId=''; 
	
	if(document.getElementById("otherSymptoms"))
	otherSymptom=document.getElementById("otherSymptoms").checked;
	
	var otherSymptomCount=document.getElementById("otherSymptomCount").value;
	if(!otherSymptom)
		{
	if(document.getElementById("mainSymptomName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("mainSymptomName"));
	alert("Please select Main Symptom Name");
	partial(focusBox,document.getElementById("mainSymptomName"));
	return false;
	}
	if(document.getElementById("mainSymptomCode").value=="")
		{
		var fr=partial(focusBox,document.getElementById("mainSymptomCode"));
		alert("Please Enter Main Symptom Code");
		partial(focusBox,document.getElementById("mainSymptomCode"));
		return false;
		}
	
	if(document.getElementById("subSymptomName").value==-1)
		{
		var fr=partial(focusBox,document.getElementById("subSymptomName"));
		alert("Please select Sub Symptom Name");
		partial(focusBox,document.getElementById("subSymptomName"));
		return false;
		}
	if(document.getElementById("subSymptomCode").value=="")
		{
		var fr=partial(focusBox,document.getElementById("subSymptomCode"));
		alert("Please Enter Sub Symptom Name");
		partial(focusBox,document.getElementById("subSymptomCode"));
		return false;
		}
	if(document.getElementById("symptomName").value==-1)
		{
		var fr=partial(focusBox,document.getElementById("symptomName"));
		alert("Please select Symptom Name");
		partial(focusBox,document.getElementById("symptomName"));
		return false;
		}
	if(document.getElementById("symptomCode").value=="")
		{
		var fr=partial(focusBox,document.getElementById("symptomCode"));
		alert("Please enter Symptom Code");
		partial(focusBox,document.getElementById("symptomCode"));
		return false;
		}
	
	for(var c=0;c<symptoms.length;c++)
	{
	var symptomsValues=symptoms[c].split("~");
	if(symptoms[c].indexOf(document.getElementById("symptomName").value) !== -1)
		{
		jqueryErrorMsg('Unique Symptoms Validation',"Symptom Name already added.Please select another Symptom Name",fr);
		return false;
		}
	}
	
		}
	
	if(otherSymptom)
		{
		var otherSymptomName=document.getElementById("otherSymptomName").value.trim();
		if(otherSymptomName==null || otherSymptomName=='')
			{
			alert("Please enter other Symptom Name");
			document.getElementById("otherSymptomName").focus();
			document.getElementById("otherSymptomName").value='';
			return false;
			}
		
		
		for(var i=0;i<symptoms.length;i++)
			{
			var symptomValues=symptoms[i].split("~");
			
			if(symptomValues[2]!=null)
				{
				var symptomName='';
				symptomName=symptomValues[2].replace("@","");
				symptomName=symptomName.trim();
			
				var uniqueSymptom=(symptomName.toUpperCase()==document.getElementById("otherSymptomName").value.toUpperCase());
				if(uniqueSymptom)
					{
				jqueryErrorMsg('Unique Symptoms Validation',"Symptom Name already added.Please select another Symptom Name",fr);
				document.getElementById("otherSymptomName").value='';
				return false;
				    }
				}
			}
		}
	
		var sympTable=document.getElementById("symptomsTable");    
		var newRow=sympTable.insertRow(-1);
		if(!otherSymptom)
			{
		var newcell=newRow.insertCell(0);
		newcell.innerHTML='<td width="5%" class="tbcellBorder">'+parseInt(symptomCount+1)+'</td>';
		var newcell=newRow.insertCell(1);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("symptomCode").value+'</td>';
		newcell=newRow.insertCell(2);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("symptomName").options[document.getElementById("symptomName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(3);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("subSymptomCode").value+'</td>';
		newcell=newRow.insertCell(4);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("subSymptomName").options[document.getElementById("subSymptomName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(5);
		newcell.innerHTML='<td width="10%" class="tbcellBorder">'+document.getElementById("mainSymptomCode").value+'</td>';  
		newcell=newRow.insertCell(6);
		newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("mainSymptomName").options[document.getElementById("mainSymptomName").selectedIndex].text+'</td>';
		newcell=newRow.insertCell(7);
		newcell.innerHTML='<td width="5%" class="tbcellBorder"><button class="btn btn-warning" style="padding: 3px 5px;" type="button" value="Delete" name='+document.getElementById("symptomName").value+' id='+document.getElementById("symptomName").value+' onclick="removeSymptom(this)">Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			}
		else
			{
			otherSymptomCount++;
			
			symptomId="OS"+otherSymptomCount;
			var newcell=newRow.insertCell(0);
			newcell.innerHTML='<td width="5%" class="tbcellBorder">'+parseInt(symptomCount+1)+'</td>';
			var newcell=newRow.insertCell(1);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(2);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(3);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(4);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">Others</td>';
			newcell=newRow.insertCell(5);
			newcell.innerHTML='<td width="10%" class="tbcellBorder">Others</td>';  
			newcell=newRow.insertCell(6);
			newcell.innerHTML='<td width="15%" class="tbcellBorder">'+document.getElementById("otherSymptomName").value+'</td>';
			newcell=newRow.insertCell(7);
			newcell.innerHTML='<td width="5%" class="tbcellBorder"><button class="btn btn-warning" style="padding: 3px 5px;" type="button" value="Delete" name='+symptomId+' id='+symptomId+' onclick="removeSymptom(this)"/>Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			
			}
		var deleteButName='';
		 if(!otherSymptom)
		  deleteButName=document.getElementById("symptomName").value;
		 else
		  deleteButName=symptomId;
		 
		 document.getElementById(deleteButName).onclick = function(){
			 confirmRemoveRow(this,"symptom");
			 }; 
			 var symptm='';
		
		if(!otherSymptom)
		{	 
		symptm=document.getElementById("mainSymptomName").value+"~"+document.getElementById("subSymptomName").value+"~"+document.getElementById("symptomName").value;
		}
		else
		{
			symptm=symptomId+"~others~"+document.getElementById("otherSymptomName").value;
			document.getElementById("otherSymptomName").value='';
		}
		symptoms[symptomCount]=symptm+"@";
	    document.getElementById("symptoms").value=symptoms;
	    symptomCount++;
		if(symptomCount>0)
			{
			document.getElementById("symptomsTable").style.display="";
			}
		document.getElementById("mainSymptomName").value="-1";
		document.getElementById("subSymptomName").value="-1";
		document.getElementById("symptomName").value="-1";
		document.getElementById("mainSymptomCode").value="";
		document.getElementById("symptomCode").value="";
		document.getElementById("subSymptomCode").value="";
		document.forms[0].subSymptomName.options.length=1;
		document.forms[0].symptomName.options.length=1;
		
		//parent.fn_resizePage();
}
function confirmPatientTypeReset()
{
	
	/*var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;*/
	/*if(schemeId!='CD202' && hospType!='G')
		{*/
	if(document.forms[0].patientType[1].checked)
	{
		
		jqueryConfirmMsg('General Investigations Add Confirmation','Patient Type IP details entered will be reset.Do you want to add general investigations?',addGenInvestigation);
		
	}
	else if(document.forms[0].patientType[0].checked)
	{
		jqueryConfirmMsg('General Investigations Add Confirmation','Patient Type OP details entered will be reset.Do you want to add general investigations?',addGenInvestigation);
	}
	else if(document.forms[0].patientType[2] && document.forms[0].patientType[2].checked)
	{
		jqueryConfirmMsg('General Investigations Add Confirmation','Do you want to add general investigations?',addGenInvestigation);
	}
	else
	{
		//addGenInvestigations();
		addGenInvestigation();
}	/*}
	else
		{
		addGenInvestigation();
		}*/
}

function addGenInvestigation(){
	
}
function deleteGenInvest(src,investId){

    var oRow=src.parentNode.parentNode;
	genInventList.splice(oRow.rowIndex-1,1);		
    document.getElementById("genTestTable").deleteRow(oRow.rowIndex);
	genInventCount--;
	
	
		if(genInventCount==0)
			{
			document.getElementById("send").disabled = true;

					document.getElementById('genTestTable').style.display='none';
			        if(genOldList.length==0){
					document.forms[0].patientType[1].disabled=true;
					document.forms[0].patientType[1].checked=false;
					document.forms[0].patientType[0].checked=false;
					//document.forms[0].patientType[2].checked=false;
					if(document.getElementById('patientType'))
						{
					var a=document.getElementById('patientType').value;
					document.getElementById('patientType').value='';
					
					
					
					if((a=="OP") || (a=="IP"))
						{
					document.getElementById("diagType").value=-1;
					document.getElementById("diagCode").value="";
					document.getElementById("mainCatName").options.length = 1;
					document.getElementById("mainCatCode").value = "";
					document.getElementById("catName").options.length = 1;
					document.getElementById("catCode").value= "";
					document.getElementById("subCatName").options.length = 1;
					document.getElementById("subCatCode").value = "";
					document.getElementById("diseaseName").options.length = 1;
					document.getElementById("diseaseCode").value = "";
					document.getElementById("disAnatomicalName").options.length = 1;
					document.getElementById("disAnatomicalCode").value = "";
					
					
					
					document.getElementById('IPHead2').style.display='none';
					document.getElementById("diagnosisData").style.display="none";
					document.getElementById('OPHead2').style.display='none';
					document.getElementById("prescriptionData").style.display="none";
					document.getElementById("OPDoctor").style.display="none";
			        }}
			        }
			}
			//parent.fn_resizePage();
			}

function removeGenInvestOnload(src,investId,price){
	
	           genInvestRemove=genInvestRemove+investId+"@";
           		var oRow = src.parentNode.parentNode; 
           		var a=null;
           		var b=null;
           		
           		document.getElementById("genTestTableID").deleteRow(oRow.rowIndex);
           		genOldList.splice(oRow.rowIndex-1,1);
           		
           		for(var i=0;i<genOldList.length;i++){
           	        if(genOldList[i].indexOf(investId,0)!=-1)
           	        	{
           	        	
           	        	
           	        	var schemeId=document.getElementById("scheme").value;
           	        	var patientScheme="";
           	        	if(document.getElementById("patientScheme"))
           	        	 patientScheme=document.getElementById("patientScheme").value;
           	        	var hospType=document.getElementById("hosptype").value;
           	        	
           	        	genOldList.splice(i,1);
           	        	if(document.forms[0].patientType[0].checked)
           	        		{
           	        	
           	        	if( schemeId=="CD202" && patientScheme=="CD501" && hospType=="G")
           	 		{
           	        	
           	        	var invPrice=price;
           	        	
//alert(invPrice);
           	        	var totInvestPrice=document.forms[0].totInvestPrice.value;
           	        	var effInvPrice=totInvestPrice-invPrice;
           	        	document.forms[0].totInvestPrice.value=effInvPrice;
           	        	document.getElementById("totalInvPrice").innerHTML='<b><font color="#B01000"> &#x20B9;'+effInvPrice+'</font></b>';
           	        	
           	        	document.getElementById("costOfInvest").innerHTML='<b><font color="#B01000"> &#x20B9;'+effInvPrice+'</font></b>';
           	        	//var totOpCost=document.forms[0].totalOpCost.value;
           	        	var consulAmt=document.getElementById("consultFee").value;
           	        	var effOpCost=parseFloat(consulAmt)+parseFloat(effInvPrice);
           	        	//document.forms[0].totalOpCost.value=effOpCost;
           	        	document.getElementById("totalOpCost").innerHTML='<b><font color="#B01000"> &#x20B9;'+effOpCost+'</font></b>';
           	        			}
           	        	
           	        	
           	        	
           		         }
           	        	}
           	       	}
           		
            if(genOldList.length==0)
    				{
            	//document.getElementById("opIcdName").disabled=false;
    			//document.getElementById("opPkgName").disabled=false;
    			document.getElementById('genTestTableID').style.display='none';
    			//document.getElementById("drugs").value="";
    			//document.getElementById("drugsTable").style.display="none";
            	
						if(genInventCount==0)
						{
							document.forms[0].patientType[1].disabled=true;
							document.forms[0].patientType[1].checked=false;
							document.forms[0].patientType[0].checked=false;
							//document.forms[0].patientType[2].checked=false;
							//document.forms[0].patientType[1].value="";
							//document.forms[0].patientType[0].value="";
							if(document.getElementById('patientType') != null)
							{
							 a=document.getElementById('patientType').value;
							document.getElementById("patientType").value="";
							 b=document.getElementById("patientType").value;
							}
							
							if((a!="ChronicOP"))
							{
							document.getElementById("diagType").value=-1;
							document.getElementById("diagCode").value=-1;
							document.getElementById("mainCatName").options.length = 1;
							document.getElementById("mainCatCode").value = "";
							document.getElementById("catName").options.length = 1;
							document.getElementById("catCode").value=-1;
							document.getElementById("subCatName").options.length = 1;
							document.getElementById("subCatCode").value = "";
							document.getElementById("diseaseName").options.length = 1;
							document.getElementById("diseaseCode").value = "";
							document.getElementById("disAnatomicalName").options.length = 1;
							document.getElementById("disAnatomicalCode").value = "";
							
							
							document.getElementById('IPHead2').style.display='none';
							document.getElementById("diagnosisData").style.display="none";
							document.getElementById('OPHead2').style.display='none';
							document.getElementById("prescriptionData").style.display="none";
							document.getElementById("OPDoctor").style.display="none";
						}}
    				}
}

var str="";
function getExaminSubCatValue(input)
{
	
	var examinField="";
	if(input.checked)
		{
	if(input.name=='Dehydration' && input.value=='Y')
		{
		examinField=examinField+"<input type='radio' name='dehydrationY' id='dehydrationY' value='Mild' title='Mild'/>Mild<input type='radio'  name='dehydrationY' id='dehydrationY' value='Moderate' title='Moderate'/>Moderate<input type='radio'  name='dehydrationY' id='dehydrationY' value='Severe' title='Severe'/>Severe";
		}
	if(input.name=='Temperature(C/F)' && input.value=='C')
		examinField=examinField+"<input type='text' name='temperatureC' id='temperatureC' maxlength='4' title='Mild' onchange='validateTemperature(this)'/>";
	else if(input.name=='Temperature(C/F)' && input.value=='F')
		examinField=examinField+"<input type='text' name='temperatureF' id='temperatureF' maxlength='4' title='Mild' onchange='validateTemperature(this)'/>";
	document.getElementById(input.name+"Sub").innerHTML=examinField;
		}
	else
		{
		document.getElementById(input.name+"Sub").innerHTML="";
		}
		 //parent.fn_resizePage();
	}
function getSubFieldType(input)
{
	
	var examinField="";
	var id=input.value;	
	var maxlength=5;
	var subTypeField=document.getElementById(id+"h").value;
	var prop = document.getElementById(id+"h").name;
	
	
	if(prop=="Height (in Cm)")
	{maxlength=10;}
     else if(prop=="Weight (in Kg)")
	{maxlength=10;}
    else if(prop=="BMI")
	maxlength=21;
			
	if(input.checked)
	{
	if(subTypeField=='T')
		{
		if(prop!='BP Lt.Arm mm/Hg'&& prop!='BP Rt.Arm mm/Hg'&& prop!='Temperature(C/F)')
			{examinField=examinField+"<input type='text' name='"+id+"' id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field'onchange='validateInnerNum(this)'";
			 if(prop=="BMI") 
				 examinField=examinField+" readOnly/> ";
			 else
				examinField=examinField+" /> ";
			}
		
		else if(prop=='BP Lt.Arm mm/Hg')
			{
			
			examinField=examinField+"<input type='text' style='width:37%' name='"+id+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> / <input type='text' name='BP1' id='BP1' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
			
			}
		else if(prop=='BP Rt.Arm mm/Hg') 
			examinField=examinField+"<input type='text' style='width:37%' name='"+id+"' id='"+id+"' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/> / <input type='text' name='BP2' id='BP2' style='width:38%' maxlength='5' title='"+prop+" Text Field'onchange='validateInnerNum(this)'/>";
		else if(prop=='Temperature(C/F)')
			{examinField=examinField+"<input type='radio' name='temp' id='temp' value='C' title='Centigrade' onclick='showTemp()'/>C<input type='radio'  name='temp' id='temp' value='F' title='ForeignHeat' onclick='showTemp()' />F&nbsp;&nbsp;&nbsp;<input type='text' style='width:57%;' name='"+id+"'  id='"+id+"' maxlength='"+maxlength+"' title='"+prop+" Text Field' onchange='validateInnerNum(this)'/>";

			}
		}
	else if(subTypeField=='R')
		{
		
			examinField=examinField+"<input type='radio' name='"+prop+"' id='"+id+"' value='Y' title='Yes' onclick='getExaminSubCatValue(this)'/>Yes<input type='radio'  name='"+prop+"' id='"+id+"' value='N' title='No' onclick='getExaminSubCatValue(this)'/>No<br /><span id='"+prop+"Sub'></span>";
		}
	
	document.getElementById(id).innerHTML=examinField;
	}
	else
	{
	document.getElementById(id).innerHTML="";
	}
	//parent.fn_resizePage();
}
function showTemp(){document.forms[0].GE11.value="";}

function validateTemperature(input){
	
	var hospGovu = document.getElementById("hospGovu").value;
	var a=input.value;
	var fr=partial(focusBox,input);
	var regAlphaNum=/^[0-9.CF]+$/;
	var inputlength=input.value.length;
	var mainStrlength=inputlength-1;
	var substr=input.value.slice(-1);
	var mainstr=input.value.substring(0,mainStrlength);
	if(a.trim()=="")
		{
		jqueryErrorMsg('Number Validation',"Blank spaces are not allowed for Temperature",fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	if(a.charAt(0)=="." || a.charAt(0)==" " || a.charAt(0)=="0")
	{
		jqueryErrorMsg('Number Validation',input.title+ " should not start with . or space or 0",fr);
		partial(focusBox,input);
		input.value="";
		return false;
	}
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Number Validation',"Only numbers,characters C/F and . are allowed for Temperature",fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
	if(input.id=='temperatureC'){
		if(mainstr>45 ||mainstr<24){
			jqueryErrorMsg('Number Validation',"Allowed temperature range is 24-45 C",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		if( substr!="C")
			{
			jqueryErrorMsg('Number Validation',"Last charachter should be C for"+input.title,fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		}
	if(input.id=='temperatureF'){
		if(mainstr<75 || mainstr>111){
			jqueryErrorMsg('Number Validation',"Allowed temperature range is 75-111 F",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		if( substr!="F")
		{
			jqueryErrorMsg('Number Validation',"Last charachter should be F for"+input.title,fr);
			partial(focusBox,input);
			input.value="";
			return false;
		}
		}
	
}

function getSymptomLst(){
	var mainSymptomCode=null;var subSymptomCode=null;
	document.getElementById("mainSymptomCode").value="";
	document.getElementById("subSymptomCode").value="";
	document.forms[0].symptomName.options.length=1;
	document.getElementById('symptomCode').value="";
	if(document.getElementById("mainSymptomName").value=="-1" || document.getElementById("subSymptomName").value=="-1")
		{
		return false;
		}
	else
		{
	mainSymptomCode=document.getElementById("mainSymptomName").value;
	document.getElementById("mainSymptomCode").value=mainSymptomCode;
	subSymptomCode=document.getElementById("subSymptomName").value;
	document.getElementById("subSymptomCode").value=subSymptomCode;
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
    	alert("Your browser does not support XMLHTTP!");
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
                var symptomList = resultArray.split("@,"); 

            		document.forms[0].symptomName.options.length=0;
                    document.forms[0].symptomName.options[0]=new Option("---select---","-1");
          	  		
                
				 for(var i = 0; i<symptomList.length;i++)
               		 {	
                    
                     var arr=symptomList[i].split("~");
                   
                     if(arr[1]!=null && arr[0]!=null)
                     {
                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                    	 document.forms[0].symptomName.options[i+1] =new Option(val1,val2);
                     }
                	}
            	 }
      		  }
            }
   	 	}
    
	url = "./patientDetails.do?actionFlag=getSymptomLst&callType=Ajax&mainSymptomCode="+mainSymptomCode+"&subSymptomCode="+subSymptomCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getsubSymptomLst(){
	var mainSymptomCode=null;
	document.getElementById("mainSymptomCode").value="";
	document.forms[0].subSymptomName.options.length=1;
	document.getElementById('subSymptomCode').value="";
	document.forms[0].symptomName.options.length=1;
	document.getElementById('symptomCode').value="";
	getSymptomLst();
	if(document.getElementById("mainSymptomName").value=="-1")
		{
		return false;
		}
	else
		{
	mainSymptomCode=document.getElementById("mainSymptomName").value;
	document.getElementById("mainSymptomCode").value=mainSymptomCode;
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
                var symptomList = resultArray.split("@,"); 

            		document.forms[0].subSymptomName.options.length=0;
                    document.forms[0].subSymptomName.options[0]=new Option("---select---","-1");
          	  		
                
				 for(var i = 0; i<symptomList.length;i++)
               		 {	
                    
                     var arr=symptomList[i].split("~");
                   
                     if(arr[1]!=null && arr[0]!=null)
                     {
                         var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                    	 document.forms[0].subSymptomName.options[i+1] =new Option(val1,val2);
                     }
                	}
            	 }
      		  }
            }
   	 	}
    
	url = "./patientDetails.do?actionFlag=getSubSymptomLst&callType=Ajax&mainSymptomCode="+mainSymptomCode;
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
		}
}
function getSymptomCode()
{
	if(document.getElementById('symptomName').value=="-1")
		document.getElementById('symptomCode').value="";
	else
	document.getElementById('symptomCode').value=document.getElementById('symptomName').value;
}
function getComplaintType(flag){
	var patComCode = "";
	if(flag=="onLoad")
	{
	patComCode = document.getElementById("patComplaintCode").value.split("~"); 
	}
	else
	{
		document.getElementById("complaintCode").value="";
		document.forms[0].patientComplaint.options.length=0;
		document.getElementById("patComplaintCode").value="";
	}
	var complaint=document.forms[0].complaints;

	var complaintCnt=0;
	for (var x=0;x<complaint.length;x++)
	{
		if (complaint[x].selected)
		{
			complaintCnt++;
		}
	}
	if(complaintCnt==0)
	{
		return false;
	}
	else
		{
		var complainLen=document.getElementById("complaints").length;
		var mainCompId="";
		for (var x=0;x<complainLen;x++)
		{
			if (document.forms[0].complaints[x].selected)
			{
				mainCompId = mainCompId + document.forms[0].complaints[x].value+"~";
			}
		}
		mainCompId=mainCompId.substring(0,mainCompId.length-1);
		document.getElementById("complaintCode").value=mainCompId;
		
		
		
	//var mainCompId=document.getElementById("complaints").value;
	
	//document.getElementById("complaintCode").value=mainCompId;
	var xmlhttp;
	var url;
	var docType; 
		
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
	url = "./patientDetails.do?actionFlag=getComplaintList&callType=Ajax&mainCompId="+mainCompId;    
	xmlhttp.onreadystatechange=function()
		{
   		if(xmlhttp.readyState==4)
   		{
      	 	var resultArray=xmlhttp.responseText;
      		if(resultArray.trim()=="SessionExpired*"){
      			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
        	}
        	else{
       			resultArray = resultArray.replace("[","");
       			resultArray = resultArray.replace("@]*","");
       			var lsubCompList = resultArray.split("@,"); 
		       	if(lsubCompList.length>0)
       			{
       				document.forms[0].patientComplaint.options.length=0;
		            for(var i = 0; i<lsubCompList.length;i++)
             		{	
                  		var arr=lsubCompList[i].split("~"); 
                  		if(arr[1]!=null && arr[0]!=null)
                  		{
                      		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                      		var val2 = arr[0].replace(/^\s+|\s+$/g,"");  
                      		
                      		document.forms[0].patientComplaint.options[i] =new Option(val1,val2);
                      		for(var j=0;j<patComCode.length;j++){
                                if(patComCode[j]==val2)
                              	  document.forms[0].patientComplaint[i].selected = true;                
                                  } 
                            if(document.getElementById("patComplaintCode").value==""){
                      		document.forms[0].presentHistory.disabled=true;
                      		document.forms[0].presentHistory.value="";}
                            else{
                            	document.forms[0].presentHistory.disabled=false;
                                }
                  		}
              		}
        		} 
   			}
   		}			
	}
	xmlhttp.open("Post",url,false);
	xmlhttp.send(null);
	}
}
function getPatComplaintCode()
{
	var patComplainLen=document.getElementById("patientComplaint").length;
	var patComplaints="";
	for (var x=0;x<patComplainLen;x++)
    {
       if (document.forms[0].patientComplaint[x].selected)
       {
    	   patComplaints = patComplaints + document.forms[0].patientComplaint[x].value+"~";
       }
    }
	document.getElementById("patComplaintCode").value=patComplaints.substring(0,patComplaints.length-1);
	document.forms[0].presentHistory.disabled=false;
}





function getOtherText(input){
	var othrField="";
	var id=input.value;	
	var subTypeField=document.getElementById(id).value;
	var prop = document.getElementById(id).name;
	var surgName='History Of Past Illness Surgeries text Field';
	if(input.checked){
	if(id=='PH11')
	othrField=othrField+"<input type='text' name='pastHistryOthr' id='pastHistryOthr' maxlength='100' title='History Of Past Illness Other text Field' onchange='validateInnerAlphaSpace(this)'/>";
	if(id=='PH8')
	othrField=othrField+"<input type='text' name='pastHistryCancers' id='pastHistryCancers' maxlength='100' title='History Of Past Illness Cancers text Field' onchange='validateInnerAlphaSpace(this)'/>";
	if(id=='PH12')
	othrField=othrField+"<input type='text' name='pastHistryEndDis' id='pastHistryEndDis' maxlength='100' title='History Of Past Illness Endocrine Diseases text Field' onchange='validateInnerAlphaSpace(this)'/>";
	if(id=='PH10')
	othrField=othrField+'<textarea name="pastHistrySurg" id="pastHistrySurg" title="History Of Past Illness Surgeries text Field" style="width:12em;height:2em" onkeydown="return maxLengthPress(this,3000,event)" onkeypress="return validateSplKeyCodes(event)" onchange="validateSpaces(this,\''+surgName+'\')"/>';
	else if(id=='FH11')
		othrField=othrField+"<input type='text' name='familyHistoryOthr' id='familyHistoryOthr' maxlength='100' title='Family History Other Text Field' onchange='validateInnerAlphaSpace(this)'/>";
	document.getElementById(id).innerHTML=othrField;
	}
else
	{
	document.getElementById(id).innerHTML="";
	}
}

function displayTextBox(input)
{
	if(input.value=="AllMedYes")
	document.getElementById("AllMedDiv").style.display="";
	else if(input.value=="AllSubYes")
		document.getElementById("AllSubDiv").style.display="";
	else if(input.value=="AllSubNo"){
		document.getElementById("AllSubDiv").style.display="none";}
	else if(input.value=="AllMedNo"){
		document.getElementById("AllMedDiv").style.display="none";		
	}
	//parent.fn_resizePage();
}
function displayPackYears(input)
{
	if(input.value=="Smoking")
	document.getElementById("smokingTd").style.display="";
	else
		document.getElementById("smokingTd").style.display="none";
	//parent.fn_resizePage();
}
function validateInnerAlphaSpace(input)
{
	var a=input.value;
	var fr=partial(focusBox,input);
	var regAlphaNum=/^[a-zA-Z ]+$/;
	if(a.trim()=="")
	{
		jqueryErrorMsg('Alphabet Validation',"Blank spaces are not allowed for"+input.title,fr);
		partial(focusBox,input);
	input.value="";
	return false;
	}
	if(a.charAt(0)==" ")
	{
		jqueryErrorMsg('Alphabet Validation',input.title+ " should not start with space",fr);
		partial(focusBox,input);
	input.value="";
	return false;
	}
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Alphabet Validation',"Only alphabets are allowed for "+input.title,fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
}
function validateInnerNum(input)
{	
	var a=input.value;
	var fr=partial(focusBox,input);
	var regAlphaNum=/^[0-9.]+$/;
	var hospId=document.getElementById("hospitalId").value;
	if(hospId!=null && hospId!='EHS34')
		{
     if(input.id=='GE1' || input.id=='GE2'){
     	 document.getElementById('GE3').innerHTML='<input type="text" id="GE3" value="" readOnly/>';
     }}
			
	if(a.trim()=="")
		{
		jqueryErrorMsg('Number Validation',"Blank spaces are not allowed for "+input.title,fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	 
	if(a.charAt(0)=="." || a.charAt(0)==" " || a.charAt(0)=="0")
	{
		jqueryErrorMsg('Number Validation',input.title+ " should not start with . or space or 0",fr);
		partial(focusBox,input);
		input.value="";
		return false;
	}
	 
	if(a.trim().search(regAlphaNum)==-1)
		{
		jqueryErrorMsg('Number Validation',"Only numbers and . are allowed for "+input.title,fr);
		partial(focusBox,input);
		input.value="";
		return false;
		}
	else
		input.value=a.trim();
	
	if(input.id=='GE1' || input.id=='GE2' || input.id=='GE11' || input.id=='GE12' || input.id=='GE13' || input.id=='GE14' || input.id=='GE15' || input.id=='BP1' || input.id=='BP2')
if(input.value.split(".").length-1>1){
	jqueryErrorMsg('Number Validation',"Please Enter Correct Value in "+input.title,fr);
	partial(focusBox,input);
	input.value="";
	return false;
}
	
	if(input.id=='GE1'){
		if(input.value>264){
			jqueryErrorMsg('Number Validation'," Height Should be in range of 0- 264 cm.",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
			
		heightVar=input.value;
		
		
		var weightVar=document.forms[0].GE2.value;
		if(hospId!="EHS34"){
		if(heightVar!=null && weightVar!='' && weightVar!=null){
			var heightVarr=heightVar*1/100;
			var bmiCal=((weightVar*1)/(heightVarr*heightVarr)).toFixed(2);
			document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
			}
		}}
	
	if(input.id=='GE2'){
		if(input.value>300){
			jqueryErrorMsg('Number Validation', " Weight Should be in range of 0- 300 Kg.",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		
		if(hospId!="EHS34"){
		weightVar=input.value;
		var heightVar=document.forms[0].GE1.value;
		if(heightVar!=null && heightVar!='' && weightVar!=null){
		var heightVarw=heightVar*1/100;
		var bmiCal=((weightVar*1)/(heightVarw*heightVarw)).toFixed(2);
		document.getElementById('GE3').innerHTML='<input type=\'text\' id=\'GE3\' value=\''+bmiCal+'\' readOnly/>';
		}}			
		}
	if(input.id=='GE12'){
		if(input.value>250){
			jqueryErrorMsg('Number Validation', " Pulse rate should be in range of 0-250 per minute",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}		
		}
	if(input.id=='GE13'){
		if(input.value>60){
			jqueryErrorMsg('Number Validation', " Respiration should be in range of 0-60 per minute",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}		
		}
	if(input.id=='GE14'||input.id=='GE15'||input.id=='BP1'||input.id=='BP2'){
		if(input.value>300 || input.value<0){
			jqueryErrorMsg('Number Validation',"BP range should be between 0-300 ",fr);
			partial(focusBox,input);
			input.value="";
			return false;
			}
		}	
	if(input.id=='GE11'){	
		var a=input.value;
		var fr=partial(focusBox,input);
		var regAlphaNumT=/^[0-9.CF]+$/;
		var inputlength=input.value.length;
		var mainStrlength=inputlength-1;
		var substr=input.value.slice(-1);
		var mainstr=input.value.substring(0,mainStrlength);
		
		if(document.forms[0].temp[0].checked==true){
			
			if(input.value>45 || input.value<24){
				jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 24-45 C",fr);
				input.value="";
				return false;
				}
			}
		else if(document.forms[0].temp[1].checked==true){
			if(input.value<75 || input.value>111){
				jqueryErrorMsg('Temperature Validation',"Allowed temperature range is 75-111 F",fr);
				input.value="";
				return false;
				}
			}
		else{
			jqueryErrorMsg('Temperature Validation',"Please Select C or F",fr);
			input.value="";
			return false;
			}				
		}
}
function getGenInvestigation(){
	 var chronicId=document.getElementById("patientNo").value;
	if(document.getElementById("genBlockInvestName").value=="-1")
	{
	return false;
	}
else
	{
	var blockId=document.getElementById("genBlockInvestName").value;
	var xmlhttp;var url;
    if (window.XMLHttpRequest)
    {xmlhttp=new XMLHttpRequest();}
    else if (window.ActiveXObject)
    {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
    else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
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
            		resultArray = resultArray.replace("[","");
                	resultArray = resultArray.replace("@]*","");            
                	var opIcdList = resultArray.split("@,"); 
                	
                	if(opIcdList.length>0)
                	{  
                		document.getElementById("genInvestigations").options.length=0;
                		document.getElementById("genInvestigations").options[0]=new Option("---select---","-1");
                		for(var i = 0; i<opIcdList.length;i++)
               		 	{	
                     		var arr=opIcdList[i].split("~");
                     		if(arr[1]!=null && arr[0]!=null)
                     		{
                         		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                         		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                       	 		document.getElementById("genInvestigations").options[i+1] =new Option(val1,val2);
                     		}
                		}
            		}
            	}
        	}
        }
    } 
  
	url = "./patientDetails.do?actionFlag=getGenInvestList&callType=Ajax&lStrBlockId="+blockId;}
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	
}

function getInvestPrice()
{
var xmlHttp;
var blockId=document.getElementById("genBlockInvestName").value;
var investId=document.getElementById("genInvestigations").value;
//alert(blockId);alert(investId);
if(blockId==null || blockId=="-1" || investId==null || investId=="-1" )
	{
	return false;
	}
else
	{
if(window.XMLHttpRequest)
	{
	xmlHttp=new XMLHttpRequest();
	}
else if(window.ActiveXObject())
	{
	xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
else{
	jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
    }

xmlhttp.onreadystatechange=function()
{
	 if(xmlhttp.readyState==4)
     {
	var resultArray=xmlhttp.responseText;

	if(resultArray=="SessionExpired")
		{
		jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
		}
	else if(resultArray!=null)
		{
		resultArray=resultArray.replace("*","");
		//alert(resultArray);
		document.getElementById("investPrice").value=resultArray;
		}
	
	
     }
}

var url=url = "./patientDetails.do?actionFlag=getInvestPrice&callType=Ajax&blockId="+blockId+"&investId="+investId;
xmlhttp.open("Post", url,true);
xmlhttp.send(null);
	}
}

function getChronicGenInvestigation(){
	 var chronicId=document.getElementById("patientNo").value;
	if(document.getElementById("genBlockInvestName").value=="-1")
	{
		document.getElementById("genInvestigations").value="-1";
		
		return false;
	
	}
else
	{
	var blockId=document.getElementById("genBlockInvestName").value;
	var packageCode=document.getElementById("opPkgCode").value;
	if(packageCode==null || packageCode=="" || blockId=="-1")
		{
		
		return false;
		}
	
	var xmlhttp;var url;
   if (window.XMLHttpRequest)
   {xmlhttp=new XMLHttpRequest();}
   else if (window.ActiveXObject)
   {xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");}
   else{jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");}
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
           		resultArray = resultArray.replace("[","");
               	resultArray = resultArray.replace("@]","");            
               	var opIcdList = resultArray.split("@,"); 
               	if(opIcdList.length>0)
               	{  
               		document.forms[0].genInvestigations.options.length=0;
                       document.forms[0].genInvestigations.options[0]=new Option("---select---","-1");
               		for(var i = 0; i<opIcdList.length;i++)
              		 	{	
                    		var arr=opIcdList[i].split("~");
                    		if(arr[1]!=null && arr[0]!=null)
                    		{
                        		var val1 = arr[1].replace(/^\s+|\s+$/g,"");
                        		var val2 = arr[0].replace(/^\s+|\s+$/g,"");
                      	 		document.forms[0].genInvestigations.options[i+1] =new Option(val1,val2);
                    		}
               		}
           		}
           	}
       	}
       }
    
		}
	  url = "./chronicAction.do?actionFlag=getGenInvestList&callType=Ajax&lStrBlockId="+blockId+"&packageCode="+packageCode;
	
 
	xmlhttp.open("Post",url,true);
	xmlhttp.send(null);
	}
}
function fn_openAtachment(filepath,fileName)
{  
	var url = "./patientDetails.do?actionFlag=viewAttachment&filePath="+filepath+"&fileName="+fileName;
    window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
}

function enable_dtrsform()
{
	var hospGovu = document.getElementById("hospGovu").value;
	var schemeId=document.getElementById("scheme").value;
	if(document.getElementById("patientScheme"))
	var patientScheme=document.getElementById("patientScheme").value;
	if(document.getElementById("hosptype"))
	var hospType=document.getElementById("hosptype").value;
	if(document.getElementById("hospitalId"))
	var hospId=document.getElementById("hospitalId").value;
	var enableFlag=false;
	if((schemeId=="CD202" && hospType=="G" && patientScheme =="CD501") || hospGovu=="G")
	enableFlag=true;
	else
	enableFlag=fn_savePatientDetails('DTRS');	
	if(enableFlag==true)
	{
		//if(schemeId!='CD202' && hospType!='G')
		document.getElementById("dtrsTd").style.display='';
	}
	else
		
	{
		
		
		if(document.getElementById("prescription"))
			{
		document.getElementById("prescription").disabled=true;
		document.getElementById("prescription").className='butdisable';
			}
		document.getElementById("dtrsTd").style.display='none';
	}
	
	/*if((hospId!=null && hospId=="EHS34")||(schemeId=="CD202" && hospType=="G"))
		{
		var caseId=document.getElementById("caseId").value;
		if(caseId=='' || caseId==null)
			document.getElementById("Submit").disabled=true;
		else{
			document.getElementById("dtrsTd").style.display='';
			
		}
		}*/
}

function confirmDentalPatientTypeReset()
{
		addGenInvestigation();
}


function generateDTRSPrint(caseId,hospId)
{
	if(caseId!=null && caseId!='')
		{
	var url="./patientDetails.do?actionFlag=dtrsPrintForm&fromDisp=Y&caseId="+caseId+"&printFormType=DTRS";
	window.open(url, '_blank','toolbar=no,resize=yes,scrollbars=yes,width=900, height=700, top=50,left=50');
		}
	else
		{
		bootbox.alert("Please generate DTRS Form and try again");
		return false;
		}
}


//Dispensary Changes

function confirmPatientTypeResetDisp()
{
	
	/*var schemeId=document.getElementById("scheme").value;
	var patientScheme=document.getElementById("patientScheme").value;
	var hospType=document.getElementById("hosptype").value;*/
	/*if(schemeId!='CD202' && hospType!='G')
		{*/
	if(document.forms[0].patientType[1].checked)
	{
		
		jqueryConfirmMsg('General Investigations Add Confirmation','Patient Type IP details entered will be reset.Do you want to add general investigations?',addGenInvestigationDisp);
		
	}
	else if(document.forms[0].patientType[0].checked)
	{
		jqueryConfirmMsg('General Investigations Add Confirmation','Patient Type OP details entered will be reset.Do you want to add general investigations?',addGenInvestigationDisp);
	}
	else if(document.forms[0].patientType[2] && document.forms[0].patientType[2].checked)
	{
		jqueryConfirmMsg('General Investigations Add Confirmation','Do you want to add general investigations?',addGenInvestigationDisp);
	}
	else
	{
		//addGenInvestigations();
		addGenInvestigationDisp();
}	/*}
	else
		{
		addGenInvestigation();
		}*/
}

function addGenInvestigationDisp(){
	
	var otherInvest=false;
	if(document.getElementById("invOthers"))
  otherInvest=document.getElementById("invOthers").checked;
	if(document.getElementById("otherInvestCount"))
		{
	var otherInvestCountTemp=document.getElementById("otherInvestCount").value;
	
	if(otherInvestCount<=otherInvestCountTemp)
		{
		otherInvestCount=otherInvestCountTemp;
		}
		}
	var testId='';
	if(!otherInvest)
		{
	if(document.getElementById("genBlockInvestName").value==-1)
	{
	var fr=partial(focusBox,document.getElementById("genBlockInvestName"));
	alert("Please select Investigation Block");
	partial(focusBox,document.getElementById("genBlockInvestName"));
	return false;
	}
	/*if(document.getElementById("genInvestigations").value=="-1")
	{
		var fr=partial(focusBox,document.getElementById("genInvestigations"));
		alert("Please Select Investigations");
		partial(focusBox,document.getElementById("genInvestigations"));
		return false;
	}*/
		}
	else 
	{
	var otherInvName=document.getElementById("otherInvName").value.trim();
	if(otherInvName==null || otherInvName=="")
		{
		alert("Please Enter Investigation Name");
		document.getElementById("otherInvName").focus();
		document.getElementById("otherInvName").value='';
		return false;
		}

	for(var i=0;i<genOldList.length;i++)
	{
	var invValues=genOldList[i].split("~");
	
	if(invValues[2]!=null)
		{
		var invName='';
		
		invName=invValues[2].replace("@","");
		//invName=invValues[2].replace("\\","\\\\");
		var invNameTemp=invName;
		if(invNameTemp.toUpperCase().localeCompare(document.getElementById("otherInvName").value.trim().toUpperCase())==0)
			{
			
			alert("Investigation Name already added.Please select another Investigation Name");
			document.getElementById("otherInvName").value='';
			document.getElementById("otherInvName").focus();
			return false;
		    }
		}
	}
	
	for(var i=0;i<genInventList.length;i++)
	{
	var invValues=genInventList[i].split("~");
	
	if(invValues[2]!=null)
		{
		var invName='';
		invName=invValues[2].replace("@","");
		invName=invName.trim();
	
		
		if(invName.trim().toUpperCase()==document.getElementById("otherInvName").value.trim().toUpperCase())
			{
			alert("Investigation Name already added.Please select another Investigation Name");
			document.getElementById("otherInvName").value='';
			document.getElementById("otherInvName").focus();
			return false;
		    }
		}
	}
	
	
	}
	if(!otherInvest)
		{
	for(var c=0;c<genOldList.length;c++){
		//var symptomsValues=genInventList[c].split("~");
		if(genOldList[c].indexOf(document.getElementById("genInvestigations").value) !== -1)
			{
			var fr=partial(focusBox,document.getElementById("genInvestigations"));
			alert("Investigation Name already added.Please select another Investigation Name");
			partial(focusBox,document.getElementById("genInvestigations"));
			return false;
			}
		}
	//alert(genInventList.length);
	for(var c=0;c<genInventList.length;c++)
	{
	//var symptomsValues=genInventList[c].split("~");
	if(genInventList[c].indexOf(document.getElementById("genBlockInvestName").value) !== -1)
		{
		var fr=partial(focusBox,document.getElementById("genBlockInvestName"));
		jqueryErrorMsg('Unique Investigation Validation',"Investigation Name already added.Please select another Investigation Name",fr);
		partial(focusBox,document.getElementById("genBlockInvestName"));
		return false;
		}
	}
	
  }
	if(genInventCount<=15)
	{
		if(genInventCount>=0)
		{
			if(document.forms[0].patientType[1].checked)
			{
				//jqueryInfoMsg('Patient Type Details Reset Message',"IP details entered are being reset");
			document.forms[0].patientType[1].checked=false;
			document.getElementById('OPHead2').style.display='none';
			document.getElementById('IPHead2').style.display='none';
			document.getElementById("diagnosisData").style.display="none";
			document.getElementById("prescriptionData").style.display="none";
			document.getElementById("OPDoctor").style.display="none";
			//document.getElementById("ChronicOPTherapy").style.display="none";
			document.getElementById("opClaimTable").style.display="none";
			if(document.getElementById("totalInvestAmt"))
			document.getElementById("totalInvestAmt").style.display="none";
			}
			else if(document.forms[0].patientType[0].checked)
			{
				//jqueryInfoMsg('Patient Type Details Reset Message',"OP details entered are being reset");
			document.forms[0].patientType[0].checked=false;
			document.getElementById('OPHead2').style.display='none';
			document.getElementById('IPHead2').style.display='none';
			document.getElementById("diagnosisData").style.display="none";
			document.getElementById("prescriptionData").style.display="none";
			document.getElementById("OPDoctor").style.display="none";
			//document.getElementById("ChronicOPTherapy").style.display="none";
			document.getElementById("opClaimTable").style.display="none";
			if(document.getElementById("totalInvestAmt"))
			document.getElementById("totalInvestAmt").style.display="none";
			if(document.getElementById("empPastHistory"))
			document.getElementById("empPastHistory").style.display="none";
			if(document.getElementById("consultationDataOld"))
			document.getElementById("consultationDataOld").style.display="none";
			if(document.getElementById("consultationDataNew"))
			document.getElementById("consultationDataNew").style.display="none";
			
			}
			else if(document.forms[0].patientType[2] && document.forms[0].patientType[2].checked)
			{
				//jqueryInfoMsg('Patient Type Details Reset Message',"Chronic OP details entered are being reset");
		
				document.forms[0].patientType[2].checked=false;
			//document.getElementById("opIcdName").disabled=true;
			//document.getElementById("opPkgName").disabled=true;
			
			}
			document.forms[0].patientType[1].disabled=false;
			document.getElementById('genTestTable').style.display='';
			
		}
		var investTableId=document.getElementById('genTestTable');
      
		var newRow=investTableId.insertRow(-1);
		//var newcell=newRow.insertCell(0);
		//newcell.innerHTML='<td width="10%">'+parseInt(genInventCount+1)+'</td>';
		if(otherInvest)
		{
				otherInvestCount++;				
				testId="OI"+otherInvestCount;				
				var newcell=newRow.insertCell(0);
				newcell.innerHTML='<td width="30%">Others</td>';
				var newcell=newRow.insertCell(1);
				newcell.innerHTML='<td width="25%">'+document.getElementById("otherInvName").value+'</td>';
		}
		else
			{
				var newcell=newRow.insertCell(0);
				newcell.innerHTML='<td width="30%">'+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+'</td>';
				var newcell=newRow.insertCell(1);
				newcell.innerHTML='<td width="20%">'+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+'</td>';
			}
				newcell=newRow.insertCell(2);
				newcell.innerHTML='<td width="20%"><input type="file"  id='+document.getElementById("genBlockInvestName").value+' name="genAttach['+genInvestAttachId+']" onchange="checkBrowser(this)"/></td>';
				newcell=newRow.insertCell(3);
				var ele = document.getElementById("genBlockInvestName").value;
				newcell.innerHTML='<td style="width:5%;text-align:center;"><span class ="medcoClass" id="lbl'+ele+'">Lab Report <span style="display:none;">'+ele+'</span></span></td>';
			if(document.forms[0].patientType[0].checked)
				{
					newcell=newRow.inserCell(4);
					newcell.innerHTML='<td width="20%">'+document.getElementById("investPrice").value+'</td>';
					newcell=newRow.inserCell(5);
				}
			else
				{
					newcell=newRow.insertCell(4);
				}
			if(!otherInvest)
			{
			    newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+document.getElementById("genBlockInvestName").value+'d id='+document.getElementById("genBlockInvestName").value+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';
			}
			else
			{
				newcell.innerHTML='<td width="20%"><button  class="btn btn-warning" type="button"  style="padding: 3px 5px;" value="Delete" name='+testId+'d id='+testId+'d >Delete&nbsp;<i class="fa fa-times"></i></button></td>';	
			}

		
		
		var deleteButName='';
		
		if(otherInvest)
			{
	    deleteButName=testId+'d';
			}
		else
			{
		deleteButName=document.getElementById("genBlockInvestName").value+'d';
			}
		document.getElementById(deleteButName).onclick = function(){
			 //confirmRemoveRow(this,"geninvestigation");
			fr=partial(deleteGenInvest,this,document.getElementById("genBlockInvestName").value);
			jqueryConfirmMsg('Delete General Investigation Confirmation',"Do you want to delete General Investigation?",fr);
			 }; 
			 var  genInvest="";
				if(document.getElementById("opPkgName"))
				{
					 if(otherInvest)
						 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value;	
				else if(document.getElementById("opPkgName").value!=null && document.getElementById("opPkgName").value!="-1")
		     genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text;
				}
			 else
				 {
		     
				 if(otherInvest)
				 genInvest="others~"+testId+"~"+document.getElementById("otherInvName").value+"~"+0;
				 else
				 genInvest=document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").value+"~"+document.getElementById("genBlockInvestName").options[document.getElementById("genBlockInvestName").selectedIndex].text+"~"+document.getElementById("investPrice").value;	 
				 }
		genInventList[genInventCount]=genInvest;
		//alert(genInventList[genInventCount]);
	    //document.getElementById("symptoms").value=symptoms;
	    genInventCount++;
	    if(genInventCount>0)
			{
			document.getElementById("genTestTable").style.display="";
			}
		
		genInvestAttachId=genInvestAttachId+1;
		
	}
	else
	{
		alert("Cannot add more than 15 tests");
	}
	//parent.fn_resizePage();	
}




function blockConsecutiveChars(name,input, value)
{
	var flag=true;
	var fr=partial(focusBox,input);
	flag=consecutiveSpecialChar(value.trim());
	if(flag)
	{
		input.value=value.trim();
	}
	else
	{
		jqueryErrorMsg('Special Character Validation',"Consecutive spaces are not allowed for "+name,fr);
		input.value="";
		return false;
	}
}

function fn_openLabReport(arg,obj)
{
	
	var reportType ="Haematology Report";
	var patientId=document.getElementById("patientNo").value;
	//alert(patientId);
	url="patientDetails.do?actionFlag=getLabReport&reportType="+reportType+"&investgtnId="+arg+"&patientId="+patientId;
	window.open(url,"",'width=500,height=250,resizable=yes,top=100,left=110,titlebar=no,menubar=no,toolbar=no,scrollbars=yes');
	/*document.forms[0].action=url;
	document.forms[0].method="post";
	document.forms[0].submit();*/
	
}

$('body').on('click', '.labReport', function(){

	var jq = jQuery.noConflict();	
	var reportType ="Haematology Report";
	var patientId=jq("#patientNo").val();
	url="patientDetails.do?actionFlag=getLabReport&reportType="+reportType+"&investgtnId="+jq(jq(this).children('span')).text()+"&patientId="+patientId;
	var newWindowX = 760;
	var newWindowY = 500;
	var currentWindowX = window.screen.width;
	var currentWindowY = window.screen.height;
	var left  = (currentWindowX/2) - (newWindowX/2);
	var top  = (currentWindowY/2) - (newWindowY/2);
	var newWindowStyle = "width="+newWindowX+",height="+newWindowY+",resizable=0,top="+top+",left="+left+"titlebar=no,menubar=no,toolbar=no,scrollbars=yes";
	window.open(url,"",newWindowStyle);
});


$('body').on('click', '.editReport', function(){

	var jq = jQuery.noConflict();
	var reportType ="Haematology Report";
	var patientId=jq("#patientNo").val();
	url="patientDetails.do?actionFlag=getLabReport&reportType="+reportType+"&investgtnId="+jq(jq(this).children('span')).text()+"&patientId="+patientId+"&editFlg=Y";
	var newWindowX = 760;
	var newWindowY = 500;
	var currentWindowX = window.screen.width;
	var currentWindowY = window.screen.height;
	var left  = (currentWindowX/2) - (newWindowX/2);
	var top  = (currentWindowY/2) - (newWindowY/2);
	var newWindowStyle = "width="+newWindowX+",height="+newWindowY+",resizable=0,top="+top+",left="+left+"titlebar=no,menubar=no,toolbar=no,scrollbars=yes";
	window.open(url,"",newWindowStyle);
});


function fn_getDrugQuant(){
	var jq = jQuery.noConflict();
	var drugId=document.getElementById("drugSubTypeCode").value;
	var drugName=jq("#drugSubTypeCode selected:option").text();
	var reqDrugQuant=document.getElementById("quantity").value;
	var availDrugQuant='';
	
	var xmlHttp;
	
	//alert(blockId);alert(investId);
	if(drugId==null || drugId=="-1")
		{
		return false;
		}
	else
		{
	if(window.XMLHttpRequest)
		{
		xmlHttp=new XMLHttpRequest();
		}
	else if(window.ActiveXObject())
		{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	else{
		jqueryInfoMsg('Ajax XMLHTTP Support',"Your browser does not support XMLHTTP!");
	    }

	xmlhttp.onreadystatechange=function()
	{
		 if(xmlhttp.readyState==4)
	     {
		var resultArray=xmlhttp.responseText;

		if(resultArray=="SessionExpired")
			{
			jqueryInfoMsg('Session Expiry',"Session has been expired",parent.sessionExpireyClose);
			}
		else if(resultArray!=null)
			{
			resultArray=resultArray.replace("*","");
//			alert(resultArray);
			availDrugQuant=resultArray;
//			alert(availDrugQuant);
			if(parseInt(reqDrugQuant)>parseInt(availDrugQuant)){
			alert("Available Quantity:"+availDrugQuant);
			document.getElementById("quantity").value="";
			}
			}
		
		
	     }
	}

	var url=url = "./patientDetails.do?actionFlag=getDrugQuant&callType=Ajax&drugId="+drugId;
	xmlhttp.open("Post", url,true);
	xmlhttp.send(null);
		}
}

function fn_getDrugDetails() {
	var jq = jQuery.noConflict();
	var xmlhttp;
	var url;
	if (document.getElementById("drugTypeCode").value == "-1"
			|| document.getElementById("drugSubTypeCode").value == "-1") {
		if (document.getElementById("drugTypeCode").value == "-1") {
			// alert("Please select Drug Type from the dropdown.");
			jq("#drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").val('-1');
			document.getElementById("medicatPeriod").value = "";
			jq("#drugSubTypeCode, #routeType, #route, #strengthType, #strength, #dosage").select2().val('');
			document.getElementById("drugInfo").style.display = "none";
			return false;
		}
		if (document.getElementById("drugSubTypeCode").value == "-1") {
			// alert("Please select Drug Name from the dropdown");
			jq("#routeType, #route, #strengthType, #strength, #dosage").val('-1');
			document.getElementById("medicatPeriod").value = "";
			jq("#routeType, #route, #strengthType, #strength, #dosage").select2().val('');
			document.getElementById("drugInfo").style.display = "none";
			return false;
		}
	}

	else {
		var drugType = document.getElementById("drugTypeCode").value;
		var drugId = document.getElementById("drugSubTypeCode").value;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		} else {
			jqueryInfoMsg('Ajax XMLHTTP Support',
					"Your browser does not support XMLHTTP!");
		}
		coverScreen();
		url = "patientDetails.do?actionFlag=getDrugDetailsAjax&callType=Ajax&drugType="+ drugType + "&drugId=" + drugId;
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4) {
				var resultArray = xmlhttp.responseText;
				resultArray = resultArray.replace("[", "");
				resultArray = resultArray.replace("@]", "");
				resultArray = resultArray.replace("*", "");
				resultArray = resultArray.replace("]", "");

				if (resultArray.trim() == "SessionExpired*") {
					removeScreenCover();
					jqueryInfoMsg('Session Expiry', "Session has been expired",
							parent.sessionExpireyClose);
				} else {

					var rows = document.getElementById("drugInfo")
							.getElementsByTagName("tr").length;
					if (rows > 1) {
						for ( var i = 0; i < rows - 1; i++) {
							document.getElementById("drugInfo").deleteRow(-1);
						}
					}
					var drugsList = resultArray.split(",");
					if (drugsList.length > 0) {
						var setCount = 0;
						for ( var i = 0; i < drugsList.length; i++) {
							var arr = drugsList[i].split("~");
							var table = document.getElementById("drugInfo");

							if (arr[0] != null && arr[4] != null
									&& arr[4] != ''
									&& arr[4].replace(/^\s+|\s+$/g, "") != 0) {

								var drugCode = arr[0].replace(/^\s+|\s+$/g, "");
								var mftr = arr[1].replace(/^\s+|\s+$/g, "");
								var dstr = arr[2].replace(/^\s+|\s+$/g, "");
								var price = arr[3].replace(/^\s+|\s+$/g, "");
								var quantity = arr[4].replace(/^\s+|\s+$/g, "");
								var expDate = arr[5].replace(/^\s+|\s+$/g, "");
								var batchNo = arr[6].replace(/^\s+|\s+$/g, "");

								var newRow = table.insertRow(-1);
								var newcell = newRow.insertCell(0);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss" align="center"><input type="checkbox" style="vertical-align: sub" id="Set'+ setCount+ '" value="'+ drugCode+ '" onclick="checkedOrnot(this,'+ setCount + ')"></input></td>';
								var newcell = newRow.insertCell(1);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss">'+ mftr + '</td>';
								var newcell = newRow.insertCell(2);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss">'+ dstr + '</td>';
								var newcell = newRow.insertCell(3);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss">'+ batchNo + '</td>';
								var newcell = newRow.insertCell(4);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss">'+ expDate + '</td>';
								var newcell = newRow.insertCell(5);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss">'+ quantity + '</td>';
								var newcell = newRow.insertCell(6);
								newcell.innerHTML = '<td class="tbcellBorder tbcellCss"><input type="text" id="quan'+ setCount+ '" onchange="check_quantity(this.id);" onkeyup="javascript:checkAvlQnt(this,'+ quantity+ ')" disabled="true"/></td>';
								setCount++;
							}
						}
						removeScreenCover();
						if (setCount > 0)
							document.getElementById("drugInfo").style.display = "";
						else {
							document.getElementById("drugInfo").style.display = "none";
							alert("Requested drugs not available");
							document.getElementById("drugSubTypeCode").value = "-1";
							jq("#drugSubTypeCode").select2().val();
						}
					}
				}

			}
		}

		xmlhttp.open("Post", url, true);
		xmlhttp.send(null);

	}
	document.getElementById("routeType").value = "-1";
	document.getElementById("route").value = "-1";
	document.getElementById("strengthType").value = "-1";
	document.getElementById("strength").value = "-1";
	document.getElementById("dosage").value = "-1";
	document.getElementById("medicatPeriod").value = "";
	jq("#routeType, #route, #strengthType, #strength, #dosage").select2().val(
			'');
}
function checkAvlQnt(thisele,quantity){
	if(thisele.value>quantity){
	alert("Please enter the quantity lesser than "+quantity);
	thisele.value='';
	}
}
function fn_getDrugMfg(){
	
	if(document.getElementById("drugSubTypeCode").value=='-1')
		alert("Please select drug name");
	else{
		var drugId=document.getElementById("drugSubTypeCode").value;
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
	            		alert(resultArray);
	            		resultArray = resultArray.replace("[","");
	                	resultArray = resultArray.replace("@]*","");            
	                	var mfgList = resultArray.split("@,"); 
	                	if(mfgList.length>0)
	                	{  
	                		document.forms[0].dispDrugMfg.options.length=0;
	                        document.forms[0].dispDrugMfg.options[0]=new Option("---select---","-1");
	                		for(var i = 0; i<mfgList.length;i++)
	               		 	{	
	                     		var arr=mfgList[i].split("~");
	                     		if(arr[1]!=null && arr[0]!=null)
	                     		{
	                         	var val1 = arr[1].replace(/^\s+|\s+$/g,"");
	                         	var val2 = arr[0].replace(/^\s+|\s+$/g,"");
	                       	 	document.forms[0].dispDrugMfg.options[i+1] =new Option(val1,val2);
	                     		}
	                		}
	            		}
	            	}
	        	}
	        }
	    }
	    url = "./patientDetails.do?actionFlag=getDispDrugMfg&callType=Ajax&drugId="+drugId;
		xmlhttp.open("Post",url,false);
		xmlhttp.send(null);
	}
}
function checkedOrnot(thiscbk,i){
	var id="quan"+i;
	if(thiscbk.checked===true){
		document.getElementById(id).disabled=false;
		document.getElementById(id).value="";
	}
	else{
		document.getElementById(id).disabled=true;
		document.getElementById(id).value="";
	}
		
}
