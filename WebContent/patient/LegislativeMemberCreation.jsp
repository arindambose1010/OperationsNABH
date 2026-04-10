<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
    <%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
    <%@ include file="/common/include.jsp"%>

<%
int liTabIndex = 0;
String photoUrl=(String)request.getAttribute("photo");
 %>
<fmt:setLocale  value='<%=session.getAttribute("LangID")%>' />
<fmt:bundle basename="Registration">
<html>
<head>
<title ><fmt:message key="EHF.Title.PatientRegistration"/></title>
<LINK href="css/patient.css" type="text/css" rel="stylesheet">
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/font-awesome/css/font-awesome.min.css" rel="stylesheet">
<!-- <script type="text/javascript" src="js/calendar.js"></script>  -->
<script src="js/jquery-1.9.1.min.js"></script>
<%@ include file="/common/includeCalendar.jsp"%>  
<%-- <%@ include file="/common/includeScrollbar.jsp"%> --%>
<script src="js/jquery.msgBox.js" type="text/javascript"></script>
<script type="text/javascript" src="js/patientscripts.js"></script>
<script src="js/jquery-ui.js" type="text/javascript"></script>
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
<script>


$(document).ready(function () {
    var today = new Date();

    $("#dob").datepicker({
        dateFormat: 'dd/mm/yy',
        maxDate: today,
        changeMonth: true,
        changeYear: true,
        yearRange: "1900:" + today.getFullYear(),
        showOn: "button",
        buttonImage: "images/calend.gif", // Path to your calendar icon
        buttonImageOnly: true,
        buttonText: "Select date"
    });
});


$(document).ready(function () {
    var today = new Date();

    $("#dependentDob").datepicker({
        dateFormat: 'dd/mm/yy',
        maxDate: today,
        changeMonth: true,
        changeYear: true,
        yearRange: "1900:" + today.getFullYear(),
        showOn: "button",
        buttonImage: "images/calend.gif", // Path to your calendar icon
        buttonImageOnly: true,
        buttonText: "Select date"
    });
});


$(document).ready(function () {
    var today = new Date();

    $("#termStartDate").datepicker({
        dateFormat: 'dd/mm/yy',
        maxDate: today,
        changeMonth: true,
        changeYear: true,
        yearRange: "1900:" + today.getFullYear(),
        showOn: "button",
        buttonImage: "images/calend.gif",
        buttonImageOnly: true,
        buttonText: "Select date",

        onSelect: function (selectedDate) {
            var parts = selectedDate.split('/');
            var startDate = new Date(parts[2], parts[1] - 1, parts[0]); 

           
            $("#termEndDate").datepicker("option", "minDate", startDate);
        }
    });

    var futureYear = today.getFullYear() + 30;
    
    $("#termEndDate").datepicker({
        dateFormat: 'dd/mm/yy',
       // maxDate: today,
        changeMonth: true,
        changeYear: true,
        yearRange: "1900:" + futureYear,
        showOn: "button",
        buttonImage: "images/calend.gif",
        buttonImageOnly: true,
        buttonText: "Select date"
    });
});


var printWindow; 

function fn_submit() {
    var fullName = document.getElementsByName("fullName")[0].value.trim();
    var fatherName = document.getElementsByName("fatherName")[0].value.trim();
  //  var spouseName = document.getElementsByName("spouseName")[0].value.trim();
    var dob = document.getElementsByName("dob")[0].value.trim();
    var mobileNo = document.getElementsByName("mobileNo")[0].value.trim();
    var emailId = document.getElementsByName("emailId")[0].value.trim();

    var genderRadio = document.querySelector('input[name="gender"]:checked');
    var gender = genderRadio ? genderRadio.value : "";

    var memberType = document.getElementsByName("memberType")[0].value.trim();

    var selectBox = document.getElementById("constType");
    var constituency = selectBox.options[selectBox.selectedIndex].text.trim();
    if (constituency === "---select---") {
        constituency = "";
    }
    
    var mlaId = document.getElementsByName("lhsId")[0].value.trim();
    var termStartDate = document.getElementsByName("termStartDate")[0].value.trim();
    var termEndDate = document.getElementsByName("termEndDate")[0].value.trim();

    var permanentAddress = document.getElementsByName("perAdd")[0].value.trim();
    var officialAddress = document.getElementsByName("offAdd")[0].value.trim();
    var constituencyAddress = document.getElementsByName("constAdd")[0].value.trim();
    
    if(memberType == "MLA"){
     var districtValue = document.getElementsByName("district")[0].value.trim();
     var district = (districtValue === "-1") ? "" : districtValue;

    }else{
    	 var districtSelect = document.getElementById("districtID");
    	 var districtValue = districtSelect.value.trim() || "";
    	 district = (districtValue === "-1") ? "" : districtValue;
    }

    var pincode = document.getElementsByName("pinCode")[0].value.trim();
    var aadhaar = document.getElementsByName("aadharNo")[0].value.trim();
    
   
    var aadhaarFile = document.getElementById("aadhaarFile").files[0];
    var photoFile = document.getElementById("photoFile").files[0];
    var supportDocFile = document.getElementById("supportDocFile").files[0];
    
 // Create FormData object
    var formData = new FormData();
    
    //dependent details
	var dependentTableBody = document.getElementById("dependentTableBody"); // tbody element of your table

	var dependents = [];

	for (var i = 0; i < dependentData.length; i++) {
	    var dep = dependentData[i];

	    dependents.push({
	        dependentName: dep.dependentName,
	        dependentMobileNo: dep.dependentMobileNo,
	        dependentGender: dep.dependentGender,
	        dependentRelation: dep.relationship,
	        maritalStatus: dep.maritalStatus,
	        dependentDob: dep.dependentDob,
	        dependentAadhaarNo: dep.aadhaarNo,
	        dependentAadhaarDoc: dep.aadhaarPhoto ? dep.aadhaarPhoto.name : ""
	    });

	    if (dep.aadhaarPhoto) {
	        formData.append("dependentAadhaarFile" + i, dep.aadhaarPhoto);
	    }
	}

	formData.append("dependentCount", dependents.length);
	formData.append("dependentsJson", JSON.stringify(dependents));

		
    
 
		//Validate constituency only if it's visible and rendered
		if ((memberType === "MLA" || memberType === "MLC") && constituency === "") {
		    alert("Please select a constituency.");
		    return;
		}
				
		 // These two are always mandatory
		 if (memberType === "---select---") {
		     alert("Please select member type.");
		     return;
		 }
		
		 if (mlaId.trim() === "") {
		     alert("Please enter MLA/MLC/PSO ID.");
		     return;
		 }

 
    // Create FormData object
    formData.append("fname", fullName);
    formData.append("fatherName", fatherName);
    formData.append("dateOfBirth", dob);
    formData.append("gender", gender);
    formData.append("mobile", mobileNo);
    formData.append("eMailId", emailId);
    formData.append("memberType", memberType);
    formData.append("constituency", constituency);
    formData.append("mlaId", mlaId);
    formData.append("termStartDate", termStartDate);
    formData.append("termEndDate", termEndDate);
    formData.append("permanentAddress", permanentAddress);
    formData.append("officialAddress", officialAddress);
    formData.append("constituencyAddress", constituencyAddress);
    formData.append("district", district);
    formData.append("pincode", pincode);
    formData.append("aadharID", aadhaar);
    
    



    if (aadhaarFile) {
        formData.append("aadhaarFile", aadhaarFile);
    }
    if (photoFile) {
        formData.append("photoFile", photoFile);
    }
    if (supportDocFile) {
        formData.append("supportDocFile", supportDocFile);
    }

    if (
            fullName === "" ||
            fatherName === "" ||
            dob === "" ||
            mobileNo === "" ||
            emailId === "" ||
            gender === "" ||
            memberType === "" ||
            mlaId === "" ||
            termStartDate === "" ||
            termEndDate === "" ||
            permanentAddress === "" ||
            officialAddress === "" ||
            constituencyAddress === "" ||
            pincode === "" ||
            aadhaar === "" ||
            district === "" ||
            constituency === "" ||
            !aadhaarFile ||
            !photoFile ||
            !supportDocFile
        ) {
            var confirmProceed = confirm("The form is not completely filled. Do you still want to continue with the submission?");
            if (!confirmProceed) {
                return;
            }
        }
    

    
    var url = "./patientDetailsNew.do?actionFlag=submitLHSEnrollmentDetails&fromDisp=Y";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", url, true);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                try {
                    var jsonResponse = JSON.parse(xhr.responseText);

                    if (jsonResponse.status === "success") {
                        var idno = encodeURIComponent(jsonResponse.idno);
                        var caseStatus = encodeURIComponent(jsonResponse.caseStatus || '');
                        
                        // Now open lhsPatient.jsp with required parameters
                        //window.location.href = "./patient/patient.jsp?idno=" + idno + "&lhsFlag=True&caseStatus=" + caseStatus;
                        window.location.href = "./patientDetailsNew.do?actionFlag=loadPatientPage&idno=" + idno + "&lhsFlag=True&caseStatus=" + caseStatus;

                    }else if(jsonResponse.status === "exist"){
                    	alert("Member already exist");
                    }else {
                        alert("Submission failed. Please try again.");
                    }
                } catch (e) {
                    alert("Invalid response from server.");
                }
            } else {
                alert("Error submitting the form.");
            }
        }
    };
    xhr.send(formData);

}

function onMemberTypeChange(memberType) {
	
	 clearAllFieldsExceptMemberType();

	    var constDropdown = document.getElementById("constType");
	    constDropdown.innerHTML = '<option value="">---select---</option>';
	    
	    // Remove onchange handler to prevent unintended calls
	    constDropdown.onchange = null;

	    // Also clear district and pincode
	    document.getElementById("district").innerHTML = '<option value="">---select---</option>';
	    var pinCodeField = document.getElementById("pinCode");
	    pinCodeField.value = '';
	    pinCodeField.readOnly = (memberType === 'MLA');


	    if (memberType === 'MLA') {
	    	document.getElementById("districtMla").style.display = '';
	        document.getElementById("districtMlaId").style.display = '';
	        document.getElementById("constName").style.display = '';
	        document.getElementById("constVal").style.display = '';
	        document.getElementById("districtMlc").style.display = 'none';
	    	document.getElementById("districtMlcId").style.display = 'none';
	    
	        fetch('./patientDetailsNew.do?actionFlag=getLhsMlaConstituencies', {
	            method: 'POST'
	        })
	        .then(response => {
	            if (response.ok) {
	                return response.json();
	            }
	        })
	        .then(data => {
	            if (data && Array.isArray(data)) {
	                data.forEach(item => {
	                    var option = document.createElement('option');
	                    option.value = item.id;     // Or use item.name if preferred
	                    option.text = item.name;
	                    constDropdown.appendChild(option);
	                });
	                // Attach onchange only if MLA
	                constDropdown.onchange = function () {
	                    var selectedConstId = this.value; // This will be item.constituencyNo
	                    getDistrict(selectedConstId);
	                };
	            }
	        });
	    }else if(memberType === 'MLC'){
	    	
	    	 document.getElementById("districtMlc").style.display = '';
	    	document.getElementById("districtMlcId").style.display = '';
	    	document.getElementById("districtMla").style.display = 'none';
	    	document.getElementById("districtMlaId").style.display = 'none'; 
	    	document.getElementById("constName").style.display = '';
	        document.getElementById("constVal").style.display = '';
	    	

	    	 fetch('./patientDetailsNew.do?actionFlag=getLhsMlcConstituencies', {
		            method: 'POST'
		        })
		        .then(response => {
		            if (response.ok) {
		                return response.json();
		            }
		        })
		        .then(data => {
		            if (data && Array.isArray(data)) {
		                data.forEach(item => {
		                    var option = document.createElement('option');
		                    option.text = item.name;
		                    constDropdown.appendChild(option);
		                });
		            }
		        });
	    }
			else if(memberType === 'EX-MLA'){
				    	document.getElementById("constName").style.display = 'none';
				        document.getElementById("constVal").style.display = 'none';
				        document.getElementById("districtMlc").style.display = '';
				    	document.getElementById("districtMlcId").style.display = '';
				    	document.getElementById("districtMla").style.display = 'none';
				    	document.getElementById("districtMlaId").style.display = 'none';
			}
			else if(memberType === 'EX-MLC'){
		    	document.getElementById("constName").style.display = 'none';
		        document.getElementById("constVal").style.display = 'none';
		        document.getElementById("districtMlc").style.display = '';
		    	document.getElementById("districtMlcId").style.display = '';
		    	document.getElementById("districtMla").style.display = 'none';
		    	document.getElementById("districtMlaId").style.display = 'none';
         	}
			else if(memberType === 'FSOD'){
		    	document.getElementById("constName").style.display = 'none';
		        document.getElementById("constVal").style.display = 'none';
		        document.getElementById("districtMlc").style.display = '';
		    	document.getElementById("districtMlcId").style.display = '';
		    	document.getElementById("districtMla").style.display = 'none';
		    	document.getElementById("districtMlaId").style.display = 'none';
         	}
	}
	
function clearAllFieldsExceptMemberType() {
	        // Clear Legislative Member Details
	        document.querySelector("input[name='lhsId']").value = "";
	        document.querySelector("input[name='termStartDate']").value = "";
	        document.querySelector("input[name='termEndDate']").value = "";
	        document.querySelector("select[name='constType']").selectedIndex = 0;

	        // Clear Address Details
	        document.querySelector("input[name='perAdd']").value = "";
	        document.querySelector("input[name='offAdd']").value = "";
	        document.querySelector("input[name='constAdd']").value = "";
	        document.querySelector("input[name='pinCode']").value = "";
	      //  document.querySelector("input[name='aadharNo']").value = "";
	      
	      //dependent
	      
	  document.getElementsByName("dependentName")[0].value = "";
	  var genderRadio = document.querySelector('input[name="depGender"]:checked');
       if (genderRadio) genderRadio.checked = false;
     document.getElementsByName("depMobileNo")[0].value = "";
     document.getElementsByName("relationship")[0].selectedIndex = 0;
     document.getElementsByName("maritalStatus")[0].selectedIndex = 0;
     document.getElementsByName("dependentAadharNo")[0].value = "";
     document.getElementsByName("dependentDob")[0].value = "";
     document.getElementById("dependentaadhaarFile").value = "";

}
	

function getDistrict(constituencyId) {
    var districtDropdown = document.getElementById("district");
    districtDropdown.innerHTML = '<option value="">---select---</option>';
    document.getElementById("pinCode").value = '';

    if (!constituencyId) return;

    fetch('./patientDetailsNew.do?actionFlag=getLhsDistrictsList&constituencyId=' + constituencyId, {
        method: 'POST'
    })
    .then(response => response.ok ? response.json() : null)
    .then(data => {
        if (data && Array.isArray(data)) {
            districtsDataMap = {}; // Reset

            data.forEach(item => {
                var option = document.createElement('option');
                option.value = item.id;
                option.text = item.name;

                districtDropdown.appendChild(option);

                // Store district data in map for later use
                districtsDataMap[item.id] = {
                    name: item.name,
                    pinCode: item.pin
                };
            });

            // Set onchange only once
            districtDropdown.onchange = function () {
                var selectedId = this.value;
              if (districtsDataMap[selectedId]) {
                    document.getElementById("pinCode").value = districtsDataMap[selectedId].pinCode;
                } else {
                    document.getElementById("pinCode").value = '';
                } 
            };
        }
    });
}

function validateMobile(input) {
	 input.value = input.value.replace(/[^0-9]/g, '');
	 if (input.value.length > 0 && !/^[6789]/.test(input.value)) {
		    input.value = '';
	}
}
function mobileLength(input){
	if (input.value.length > 0 && input.value.length !== 10) {
        alert("Mobile number must be exactly 10 digits");
        input.value = '';
        input.focus();
        return;
    }
}


function validateGmail() {
    var email = document.getElementById("email").value.trim();

    // Gmail regex: allows only gmail.com addresses
    var gmailPattern = /^[a-zA-Z0-9._%+-]+@gmail\.com$/;

    if (!gmailPattern.test(email)) {
        alert("Please enter a valid Gmail address (e.g., example@gmail.com)");
        document.getElementById("email").value = '';
        document.getElementById("email").focus();
    }
}

function aadharValidate(input) {
    input.value = input.value.replace(/[^0-9]/g, '');
}
 function aadhaarLength(input){
	if (input.value.length > 0 && input.value.length !== 12) {
        alert("Aadhaar number must be exactly 12 digits");
        input.value = '';
        input.focus();
        return;
    }
}
 function validatePincode(input) {
	    // Allow only digits
	    input.value = input.value.replace(/\D/g, '');

	    if (input.value.length !== 6) {
	        alert("Please enter a valid 6-digit Pincode.");
	        input.value = '';
	        input.focus();
	        return;
	    }
	}

 

function validateAttachment(fileInput) {
    const allowedExtensions = ['pdf', 'jpg', 'jpeg', 'png'];
    const maxSize = 200 * 1024; // 200 KB in bytes

    if (fileInput.files.length === 0) {
        alert("Please select a file.");
        return false;
    }

    const file = fileInput.files[0];
    const fileName = file.name;
    const fileSize = file.size;

   
    const extension = fileName.split('.').pop().toLowerCase();

    if (!allowedExtensions.includes(extension)) {
        alert("Invalid file format. Accepted formats: .pdf, .jpg, .jpeg, .png");
        fileInput.value = ""; 
        return false;
    }

   
    if (fileSize > maxSize) {
        alert("File size must be less than or equal to 200 KB.");
        fileInput.value = ""; 
        return false;
    }
    return true;
}


 function addDependentRow(btn) {
	 var div = document.getElementById("dependentDiv");
	    var table = document.getElementById("dependentDetails");
	    var dataTable = document.getElementById("dependentTable");
	    var tableBody = document.getElementById("dependentTableBody");
	    var button = document.getElementById("toggleDependentBtn");

	    if (div.style.display === "none") {
	        // Show everything
	        div.style.display = "";
	        table.style.display = "";
	        button.value = "Reset Dependent";
	    } else {
	        // Hide and clear everything
	        div.style.display = "none";
	        table.style.display = "none";
	        dataTable.style.display = "none";
	        button.value = "Add Dependent";

	        // Clear all input fields and selections
	        var inputs = table.querySelectorAll("input, select");
	        inputs.forEach(function (el) {
	            if (el.type === "text") {
	                el.value = "";
	            } else if (el.type === "radio") {
	                el.checked = false;
	            } else if (el.tagName === "SELECT") {
	                el.selectedIndex = 0;
	            }
	        });

	        // Clear dependent table rows
	        while (tableBody.firstChild) {
	            tableBody.removeChild(tableBody.firstChild);
	        }
	    }
	}



 var dependentData = []; // Global array to store all dependent details including files

 function addDependentTableRow() {
     var dependentName = document.getElementsByName("dependentName")[0].value.trim();
     var genderRadio = document.querySelector('input[name="depGender"]:checked');
     var dependentGender = genderRadio ? genderRadio.value : "";
     var dependentMobileNo = document.getElementsByName("depMobileNo")[0].value.trim();
     var relationship = document.getElementsByName("relationship")[0].value;
     var maritalStatus = document.getElementsByName("maritalStatus")[0].value;
     var dependentAadhaarNo = document.getElementsByName("dependentAadharNo")[0].value.trim();
     var dependentAadhaarPhotoInput = document.getElementById("dependentaadhaarFile");
     var dependentAadhaarPhoto = (dependentAadhaarPhotoInput && dependentAadhaarPhotoInput.files.length > 0) 
         ? dependentAadhaarPhotoInput.files[0] 
         : null;
     var depDob = document.getElementsByName("dependentDob")[0].value.trim();

     
     if (       dependentName === "" &&
    	        dependentGender === "" &&
    	        dependentMobileNo === "" &&
    	        relationship === "" &&
    	        maritalStatus === "" &&
    	        dependentAadhaarNo === "" &&
    	        depDob === "" &&
    	        !dependentAadhaarPhoto
    	    ) {
    	        const confirmMsg = confirm("Please fill the dependent details");
    	        if (!confirmMsg) {
    	            return; // stop further processing
    	        } else {
    	            return; // allow to skip without adding
    	        }
    	    }

     // Show table if hidden
     document.getElementById("dependentTable").style.display = "";

     // Save actual data to array
     var dependentRow = {
         dependentName: dependentName,
         dependentGender: dependentGender,
         dependentDob: depDob,
         relationship: relationship,
         maritalStatus: maritalStatus,
         dependentMobileNo: dependentMobileNo,
         aadhaarNo: dependentAadhaarNo,
         aadhaarPhoto: dependentAadhaarPhoto
     };

     dependentData.push(dependentRow); // Save to global array

     // Add visible row to table
     var tableBody = document.getElementById("dependentTableBody");
     var newRow = tableBody.insertRow();

     newRow.insertCell(0).innerText = dependentName;
    
     newRow.insertCell(1).innerText = dependentGender;
     newRow.insertCell(2).innerText = depDob;
     newRow.insertCell(3).innerText = relationship;
     newRow.insertCell(4).innerText = maritalStatus;
     newRow.insertCell(5).innerText = dependentMobileNo;
     newRow.insertCell(6).innerText = dependentAadhaarNo;
    // newRow.insertCell(7).innerText = dependentAadhaarPhoto ? dependentAadhaarPhoto.name : "";
    
    var aadhaarCell = newRow.insertCell(7);
	if (dependentAadhaarPhoto) {
	    var fileLink = document.createElement("a");
	    fileLink.href = URL.createObjectURL(dependentAadhaarPhoto);
	    fileLink.download = dependentAadhaarPhoto.name;
	    fileLink.textContent = dependentAadhaarPhoto.name;
	    fileLink.target = "_blank"; // opens in new tab
	    aadhaarCell.appendChild(fileLink);
	} else {
	    aadhaarCell.innerText = "";
	}


     // Action Cell
     var actionCell = newRow.insertCell(8);
     var deleteBtn = document.createElement("button");
     deleteBtn.innerText = "Delete";
     deleteBtn.onclick = function () {
         var rowIndex = newRow.rowIndex - 1;
         tableBody.deleteRow(rowIndex);
         dependentData.splice(rowIndex, 1); // Remove from array

         if (tableBody.rows.length === 0) {
             document.getElementById("dependentTable").style.display = "none";
         }
     };
     actionCell.appendChild(deleteBtn);

     // Clear form inputs
     document.getElementsByName("dependentName")[0].value = "";
     if (genderRadio) genderRadio.checked = false;
     document.getElementsByName("depMobileNo")[0].value = "";
     document.getElementsByName("relationship")[0].selectedIndex = 0;
     document.getElementsByName("maritalStatus")[0].selectedIndex = 0;
     document.getElementsByName("dependentAadharNo")[0].value = "";
     document.getElementsByName("dependentDob")[0].value = "";
     document.getElementById("dependentaadhaarFile").value = "";
 }

 function checkAgeForChild() {
	    var relationship = document.getElementById("relationshipType").value;
	    var dobInput = document.getElementById("dependentDob").value;

	    
	    if (relationship === "Daughter" || relationship === "Son") {
	        if (!dobInput) {
	            alert("Please enter Date of Birth first.");
	            return;
	        }

	        
	        var parts = dobInput.split('/');
	        if (parts.length !== 3) {
	            alert("Please enter DOB in correct format (dd/mm/yy).");
	            return;
	        }

	        var day = parseInt(parts[0], 10);
	        var month = parseInt(parts[1], 10) - 1; 
	        var year = parseInt(parts[2], 10);
	        if (year < 100) {
	            year += 2000; 
	        }

	        var dob = new Date(year, month, day);
	        var today = new Date();

	        var age = today.getFullYear() - dob.getFullYear();
	        var m = today.getMonth() - dob.getMonth();
	        if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
	            age--;
	        }

	        if (age >= 25) {
	            alert("Daughter/Son age should be below 25 years.");
	            
	            document.getElementById("dependentDob").value = "";
	            document.getElementById("relationshipType").value = "";
	        }
	    }
	}


function removeRow(button) {
    const row = button.closest('tr');
    row.remove();

    const tbody = document.getElementById("dependentTableBody");
    if (tbody.rows.length === 0) {
        document.getElementById("dependentTable").style.display = "none";
    }
    
}

function checkAgeForChild() {
    var relationship = document.getElementById("relationshipType").value;
    var dobInput = document.getElementById("dependentDob").value;

    // Only apply for Daughter or Son
    if (relationship === "Daughter" || relationship === "Son") {
        if (!dobInput) {
            alert("Please enter Date of Birth first.");
            return;
        }

        // Convert dd/mm/yy to a Date object
        var parts = dobInput.split('/');
        if (parts.length !== 3) {
            alert("Please enter DOB in correct format (dd/mm/yy).");
            return;
        }

        var day = parseInt(parts[0], 10);
        var month = parseInt(parts[1], 10) - 1; // Months are 0-based
        var year = parseInt(parts[2], 10);
        if (year < 100) {
            year += 2000; // Handle 2-digit years as 20xx
        }

        var dob = new Date(year, month, day);
        var today = new Date();

        var age = today.getFullYear() - dob.getFullYear();
        var m = today.getMonth() - dob.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < dob.getDate())) {
            age--;
        }

        if (age >= 25) {
            alert("Daughter/Son age should be below 25 years.");
            // Optionally reset the relationship field
            document.getElementById("dependentDob").value = "";
            document.getElementById("relationshipType").value = "";
        }
    }
}

function fn_Print(lhsRegNo) {
    if (!lhsRegNo) {
        alert("IDNO missing for print!");
        return;
    }

    window.open(
        './patientDetailsNew.do?actionFlag=lhsRegisteredMembersPrint&lhsRegNo=' + encodeURIComponent(lhsRegNo),
        'PatientRegPrintPage',
        'left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes'
    );
}


</script>
  <style>
        .section-title {
            background-color: #4A90E2;
            color: white;
            padding: 5px;
            font-weight: bold;
            margin-top: 10px;
            
        }
        .form-section {
            border: 1px solid #c0c0c0;
            padding: 10px;
            margin-bottom: 10px;
        }
        label {
            font-weight: bold;
        }
        table {
            width: 100%;
        }
        td {
            padding: 5px;
        }
        .mandatory {
            color: red;
        }
        .submit-btn {
            padding: 5px 15px;
            background-color: #4A90E2;;
            border: 1px solid #fff;
            color: white;
		    padding: 5px;
		    font-weight: bold;
        }
        
    #dependentTable th {
        border: 1px solid #999;
        padding: 5px;
        text-align: left;
    }

    #dependentTable td {
       border: 1px solid #999; /* Add border to body only */
        padding: 8px;
    }

    #dependentTable {
        border-collapse: collapse;
        margin-top: 20px;
    }

    #dependentTable button {
        padding: 4px 8px;
    }
</style>
        
</head>
<body>
 
    <div class="section-title" style="text-align: center;">MLA/MLC Personal Details</div>
    <table style="margin-top: 11px;">
        <tr>
           
            <td><label>Full Name </label></td>
            <td><input type="text" name="fullName" maxlength="50"/></td>
            
            <td><label>Father Name </label></td>
            <td><input type="text" name="fatherName" maxlength="50"/></td>
            
            <td><label>Gender</label></td>
            <td>
                <input type="radio" name="gender" value="M" /> Male
                <input type="radio" name="gender" value="F" /> Female
            </td>
            
            
          </tr>
          <tr>
            
           <!--  <td><label>Date Of Birth <span class="mandatory">*</span></label></td>
            <td><input type="text" name="dob" /></td> -->
            
            <td><label>Date of Birth </label></td>
            <td>
			  <input id="dob" name="dob" style="width: 120px;"  placeholder="dd/mm/yyyy" readonly/>
			</td>
            
            <td><label>Mobile Number</label></td>
            <td><input type="text" name="mobileNo" id="mobileNo" onkeyup="validateMobile(this)" onchange="mobileLength(this)" maxlength="10" /></td>
         
           <td><label>Aadhaar Number </label></td>
            <td><input type="text" name="aadharNo" id="aadharNo" maxlength="12" oninput="aadharValidate(this)" onchange="aadhaarLength(this)"/></td>
            
          </tr>

    </table>

    <div class="section-title" style="text-align: center;">MLA/MLC Member Details</div>
    <table style="margin-top: 11px;">
        <tr>
        
            <td><label>Member Type<span class="mandatory">*</span></label></td>
            <td>
                <select name="memberType" id="memberType" onchange="onMemberTypeChange(this.value)">
                    <option>---select---</option>
                    <option value="MLA">MLA</option>
                    <option value="MLC">MLC</option>
                    <option value="EX-MLA">EX-MLA</option>
                    <option value="EX-MLC">EX-MLC</option>
                    <option value="FSOD">Family/Spouse of Deceased (MLA/MLC)</option>
                </select>
            </td>
            
           
            
            <td id="constName" style="display:none;"><label>Constituency Name <span class="mandatory">*</span></label></td>
            <td id="constVal" style="display:none;">
                <select name="constType" id="constType">
                  <option value="">---select---</option>
                </select>
            </td>
       </tr>
       <tr>     
            
            <td><label>MLA/MLC/PSO ID Number<span class="mandatory">*</span> </label></td>
            <td><input type="text" name="lhsId" id="lhsId" onkeyup="this.value = this.value.toUpperCase();"/></td>
       
           
          <!--   <td><label>Term Start Date <span class="mandatory">*</span></label></td>
            <td><input type="text" name="name" /></td> -->
            
            <td>
			  <label>Term Start Date </label>
			</td>
			<td>
			  <input id="termStartDate" name="termStartDate" style="width: 120px;" readonly/>
			</td>

            
            <td><label>Term End Date </label></td>
            <td>
			  <input id="termEndDate" name="termEndDate" style="width: 120px;" readonly/>
			</td>
            
        </tr>
        
        <tr>
		    <td colspan="6">
		        <input type="button" value="Add Dependent" class="submit-btn" id="toggleDependentBtn" onclick="addDependentRow(this);" />
		    </td>
		</tr>
        
    </table>
    
   <div class="section-title" style="text-align: center; display:none;" id="dependentDiv">Dependent Details</div>  
    <table id="dependentDetails" style="display:none;width: 100%; border-collapse: collapse;">
        <tr>
            <td><label>Dependent Name</label></td>
            <td><input type="text" name="dependentName" id="dependentName" maxlength="50"/></td>
            
            <td><label>Gender</label></td>
            <td>
                <input type="radio" name="depGender" id="depGender" value="M" /> Male
                <input type="radio" name="depGender" id="depGender" value="F" /> Female
            </td>
            
            <td><label >Date of Birth</label></td>
            <td>
			 <input id="dependentDob" name="dependentDob" style="width: 120px;" placeholder="dd/mm/yyyy" readonly/>

			</td>
			
		</tr>
		<tr>
            <td><label>Marital Status</label></td>
            <td>
                <select name="maritalStatus" id="maritalStatusType">
                    <option value="">---select---</option>
                    <option>Married</option>
                    <option>Unmarried</option>
                </select>
            </td> 
            
             <td><label>Relationship</label></td>
            <td>
                <select name="relationship" id="relationshipType" onchange="checkAgeForChild()">
                    <option value="">---select---</option>
                    <option>Spouse</option>
                    <option>Father</option>
                    <option>Mother</option>
                    <option>Daughter</option>
                    <option>Son</option>
                </select>
            </td> 
            
             <td><label >Mobile Number</label></td>
             <td><input type="text" name="depMobileNo" id="depMobileNo" onkeyup="validateMobile(this)" onchange="mobileLength(this)" maxlength="10" /></td>
          
           </tr>
           <tr> 
             <td><label >Aadhaar Number</label></td>
            <td><input type="text" name="dependentAadharNo" id="dependentAadharNo" maxlength="12" oninput="aadharValidate(this)" onchange="aadhaarLength(this)"/></td>
            
            <td><label>Aadhaar Document</label></td>
            <td>
             <input type="file" id="dependentaadhaarFile" name="dependentaadhaarFile" onchange="validateAttachment(this)" /></label>
            </td> 
            
            <td>
             <button type="button" class="submit-btn" onclick="addDependentTableRow()">Add + </button>
            </td>
        </tr>
    </table>
    
    <table id="dependentTable" border="1" style="margin-top:20px; display:none; width: 100%;" >
	  <thead style="background: #4A90E2;color: white;">
	    <tr>
	      <th>Dependent Name</th>
	      <th>Gender</th>
	      <th>Date of Birth</th>
	      <th>Relationship</th>
	      <th>Marital Status</th>
	      <th>Mobile Number</th>
	      <th>Aadhaar Number</th>
	      <th>Aadhaar Document</th> 
	      <th>Action</th>
	    </tr>
	  </thead>
	  <tbody id="dependentTableBody">
	    <!-- Rows will be added here -->
	  </tbody>
	</table>
    <div class="section-title" style="text-align: center;">MLA/MLC Address Details</div>
    <table style="margin-top: 11px;">
        <tr>
            <td><label>Permanent Address </label></td>
            <td><input type="text" name="perAdd" maxlength="150"/></td>
            
            <td><label>Official Address </label></td>
            <td><input type="text" name="offAdd" maxlength="150"/></td>
            
            <td><label>Constituency Address </label></td>
            <td><input type="text" name="constAdd" maxlength="150"/></td>
        </tr>
        <tr>  
            <td id="districtMla"><label>District </label></td>
			<td id="districtMlaId">
			    <select name="district" id="district" onchange="populatePinCode(this.value)">
			        <option value="">---select---</option>
			    </select>
			</td>
			
	
			
			<td id="districtMlc" style="display:none;"><label>District </label></td>
			 <td id="districtMlcId" style="display:none;"><html:select name="patientForm" property="district" styleId="districtID" style="width:17em;">
			   <html:option value="-1">---Select---</html:option>
			   <html:options collection="districtsList" property="districtCode"labelProperty="firstName" />
			   </html:select>
			</td> 
            
            <td><label>PIN Code </label></td>
            <td><input type="text" name="pinCode" id="pinCode" onchange="validatePincode(this)"/></td>
            
             <td><label>Email ID </label></td>
            <td><input type="text" name="emailId" id="email" onchange="validateGmail()"/></td>
            
         </tr>
         <tr>
           <td><label>Aadhaar Document</label></td>
           <td>
             <input type="file" id="aadhaarFile" name="aadhaarFile" onchange="validateAttachment(this)" /></label>
            </td>
            
             <td><label>MLA/MLC Photo</label></td>
	           <td>
	               <input type="file" id="photoFile" name="photoFile" onchange="validateAttachment(this)" /></label>
	            </td>
	            
	           <td><label>Support Document</label></td>
	            <td>
	                 <input type="file" id="supportDocFile" name="supportDocFile" onchange="validateAttachment(this)" /></label>
	            </td>
         </tr>
         <tr>
          <td style="height: 10px; font-size:11px; width=10px" nowrap="nowrap" >
				<font color="red"> Accepted Format: .pdf,.jpg,.jpeg,.png <br>
                                   Accepted File Size: Upto 200KB </font>
		   </td>
         </tr>
    </table>
    <br/>
    <input type="button" class="submit-btn" style="margin-left: 50%;" value="Submit" onclick="fn_submit()" />

</body>
</html>
</fmt:bundle>




