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

<script type="text/javascript">
    function fn_back() {
        window.location.href = 'patientDetailsNew.do?actionFlag=viewLHSRegisteredMembers&regFlag=Y';
    }
    
    
    
    $(document).ready(function () {
        var today = new Date();

        // Init Term Start Date
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

                // Clear end date
                $("#termEndDate").val("");

                // Set min date for end date
                $("#termEndDate").datepicker("option", "minDate", startDate);
            }
        });

        var futureYear = today.getFullYear() + 30;
        // Init Term End Date
        $("#termEndDate").datepicker({
            dateFormat: 'dd/mm/yy',
            changeMonth: true,
            changeYear: true,
            yearRange: "1900:" + futureYear,
            showOn: "button",
            buttonImage: "images/calend.gif",
            buttonImageOnly: true,
            buttonText: "Select date"
        });

        // If termStartDate already has a value on page load, set min date for termEndDate
        var existingStart = $("#termStartDate").val();
        if (existingStart) {
            var parts = existingStart.split('/');
            var startDate = new Date(parts[2], parts[1] - 1, parts[0]);
            $("#termEndDate").datepicker("option", "minDate", startDate);
        }
    });
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

    
    // Store the original dependents on page load
       let originalDependentsHTML = "";

       window.onload = function () {
           const tableBody = document.getElementById("dependentTableBody");
           originalDependentsHTML = tableBody.innerHTML;
       };

       function deleteDependentRow(button) {
    	    var tableBody = document.getElementById("dependentTableBody");
    	    var row = button.closest("tr");
    	    
    	    // Get the cardNo from the first cell (adjust index if needed)
    	    var cardNo = row.cells[0].innerText.trim();

    	    // Find index of dependent in dependentData by cardNo (or other unique property)
    	    var indexToRemove = -1;
    	    for (var i = 0; i < dependentData.length; i++) {
    	        if (dependentData[i].cardNo === cardNo) {
    	            indexToRemove = i;
    	            break;
    	        }
    	    }

    	    if (indexToRemove !== -1) {
    	        dependentData.splice(indexToRemove, 1);
    	        console.log("Removed dependent with cardNo:", cardNo);
    	    } else {
    	        console.log("Dependent with cardNo not found in dependentData:", cardNo);
    	    }

    	    // Now remove the row from the table
    	    var rowIndex = row.rowIndex - 1; // adjust if you have a header row
    	    tableBody.deleteRow(rowIndex);

    	    if (tableBody.rows.length === 0) {
    	        tableBody.innerHTML = '<tr id="noDependentRow"><td colspan="10" style="text-align:center;">No dependent added</td></tr>';
    	    }
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
               dataTable.style.display = "";
               button.value = "Reset Dependent";
           } else {
               // Hide and reset table to original dependents
               div.style.display = "none";
               table.style.display = "none";
               dataTable.style.display = "";

               // Restore the original HTML (either actual dependents or "No dependent added" row)
               tableBody.innerHTML = originalDependentsHTML;

               button.value = "Add Dependent";

               // Clear all form inputs
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
           }
       }


       
       var dependentData = [];

       function addDependentTableRow() {
           // Remove "No dependent added" row if present
           var noRow = document.getElementById("noDependentRow");
           if (noRow) noRow.remove();

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

           if (
               dependentName === "" &&
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

           document.getElementById("dependentTable").style.display = "";

           var dependentRow = {
        	   cardNo: "",
               dependentName: dependentName,
               dependentMobileNo: dependentMobileNo,
               dependentGender: dependentGender,
               dependentDob: depDob,
               relationship: relationship,
               maritalStatus: maritalStatus,
               aadhaarNo: dependentAadhaarNo,
               aadhaarPhoto: dependentAadhaarPhoto
           };

           dependentData.push(dependentRow);
           console.log("Added dependent. Total dependents:", dependentData.length);

           var tableBody = document.getElementById("dependentTableBody");
           var newRow = tableBody.insertRow();

           newRow.insertCell(0).innerText = ""; // Empty cardNo for new dependents
           newRow.insertCell(1).innerText = dependentName;
           newRow.insertCell(2).innerText = dependentMobileNo;
           newRow.insertCell(3).innerText = dependentGender;
           newRow.insertCell(4).innerText = depDob;
           newRow.insertCell(5).innerText = relationship;
           newRow.insertCell(6).innerText = maritalStatus;
           newRow.insertCell(7).innerText = dependentAadhaarNo;
          // newRow.insertCell(8).innerText = dependentAadhaarNo;

           var aadhaarCell = newRow.insertCell(8);
           if (dependentAadhaarPhoto) {
               var fileLink = document.createElement("a");
               fileLink.href = URL.createObjectURL(dependentAadhaarPhoto);
               fileLink.download = dependentAadhaarPhoto.name;
               fileLink.textContent = dependentAadhaarPhoto.name;
               fileLink.target = "_blank";
               aadhaarCell.appendChild(fileLink);
           } else {
               aadhaarCell.innerText = "";
           }

           var actionCell = newRow.insertCell(9);
           var deleteBtn = document.createElement("button");
           deleteBtn.innerText = "Delete";
           deleteBtn.onclick = function () {
               var rowIndex = newRow.rowIndex - 1;
               tableBody.deleteRow(rowIndex);
               dependentData.splice(rowIndex, 1);

               if (tableBody.rows.length === 0) {
                   tableBody.innerHTML = '<tr id="noDependentRow"><td colspan="8" style="text-align:center;">No dependent added</td></tr>';
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
   	function fn_submit() {
   		if(event) event.preventDefault();
   		alert("submit");
	    var fullName = document.getElementsByName("fullName")[0].value.trim();
	    var fatherName = document.getElementsByName("fatherName")[0].value.trim();
	    var dob = document.getElementsByName("dob")[0].value.trim();
	    var mobileNo = document.getElementsByName("mobileNo")[0].value.trim();
	    var emailId = document.getElementsByName("emailId")[0].value.trim();

	    var genderRadio = document.querySelector('input[name="gender"]:checked');
	    var gender = genderRadio ? genderRadio.value : "";

	    var memberType = document.getElementsByName("memberType")[0].value.trim();

	    var constituency = "";
	    var input = document.getElementById("constType");
	    if (input) {   
	        constituency = input.value.trim();
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
	    
	 // Correct way to access file inputs
	 var aadhaarFileInput = document.getElementById("aadhaarFile");
	var removeExistingAadharInput = document.getElementById("removeExistingAadhar");
	var existingFileNameInput = document.getElementById("existingAadharFileName");
	var existingFileNamePathInput = document.getElementById("existingAadharFilePath");
	    
	    var formData = new FormData();
	    
		var dependentTableBody = document.getElementById("dependentTableBody"); 
		var dependents = [];
		console.log("Dependent data length at submit:", dependentData.length);
	    console.log(dependentData);

		for (var i = 0; i < dependentData.length; i++) {
		    var dep = dependentData[i];

		    dependents.push({
		    	dependnetCardNo:dep.cardNo || "",
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

	  /*   if (aadhaarFile) {
	        formData.append("aadhaarFile", aadhaarFile);
	    } */
	    if (aadhaarFileInput && aadhaarFileInput.files.length > 0) {
	        // New file selected - send new file and flag to remove old file
	        formData.append("aadhaarFile", aadhaarFileInput.files[0]);
	        removeExistingAadharInput.value = "true";
	    } else {
	        // No new file selected - send existing file name to keep it on server
	        formData.append("existingAadharFileName", existingFileNameInput.value);
	        formData.append("existingAadharFilePath", existingFileNamePathInput.value);
	        
	        removeExistingAadharInput.value = "false";
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
	    
	    var url = "./patientDetailsNew.do?actionFlag=updateLhsMemberDetails&fromDisp=Y";
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
	                        window.location.href = "./patientDetailsNew.do?actionFlag=loadPatientPage&idno=" + idno + "&lhsFlag=True&caseStatus=" + caseStatus;
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

  //for view.
    function viewDocument(filePath) {
        window.open('./patientDetailsNew.do?actionFlag=viewLhsDocument&filePath=' + filePath+'&pageType=print','PatientRegPrintPage','left=50,top=50,width=900,height=900,toolbar=no,resize=no,scrollbars=yes');
    }

    function validateAttachment(fileInput) {
        const allowedExtensions = ['pdf', 'jpg', 'jpeg', 'png'];
        const maxSize = 200 * 1024; 

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

   
</script>

<logic:notEmpty name="registeredLhsPatientsList">
    <bean:define id="patientVO" name="registeredLhsPatientsList" />
    <logic:notEmpty name="patientVO" property="constituency">
        <bean:define id="constituency" name="patientVO" property="constituency" />
    </logic:notEmpty>

    <logic:notEmpty name="patientVO" property="memberType">
        <bean:define id="memberType" name="patientVO" property="memberType" />
    </logic:notEmpty>
</logic:notEmpty>

<script type="text/javascript">
    window.onload = function () {
        // Using bean:write ensures no null errors and escapes HTML properly
        var constituency = '<bean:write name="constituency" ignore="true"/>';
        var memberType = '<bean:write name="memberType" ignore="true"/>';

        if (constituency && memberType) {
        	onMemberTypeChange(constituency, memberType);
        }
    };

 function onMemberTypeChange(constituency, memberType) {
   
   	    if (memberType === 'MLA') {
   	    	document.getElementById("districtMla").style.display = '';
   	        document.getElementById("districtMlaId").style.display = '';
   	        document.getElementById("constName").style.display = '';
   	        document.getElementById("constVal").style.display = '';
   	        document.getElementById("districtMlc").style.display = 'none';
   	    	document.getElementById("districtMlcId").style.display = 'none';
   	    
   	    	fetch('./patientDetailsNew.do?actionFlag=getMlaConstituencyId&constituencyName=' + encodeURIComponent(constituency), {
   	    	    method: 'POST'
   	    	})
   	    	.then(response => {
   	    	    if (response.ok) {
   	    	        return response.json();
   	    	    }
   	    	})
   	    	.then(data => {
   	    	    if (data && Array.isArray(data) && data.length > 0) {
   	    	        // Take first constituency ID and call getDistrict
   	    	        var constituencyId = data[0].id; 
   	    	        getDistrict(constituencyId);
   	    	    }
   	    	});

   	    }else if(memberType === 'MLC'){
   	    	
   	    	 document.getElementById("districtMlc").style.display = '';
   	    	document.getElementById("districtMlcId").style.display = '';
   	    	document.getElementById("districtMla").style.display = 'none';
   	    	document.getElementById("districtMlaId").style.display = 'none'; 
   	    	document.getElementById("constName").style.display = '';
   	        document.getElementById("constVal").style.display = '';
   	    }
   			else if(memberType === 'EX-MLA'){
   				 //   	document.getElementById("constName").style.display = 'none';
   				 //       document.getElementById("constVal").style.display = 'none';
   				        document.getElementById("districtMlc").style.display = '';
   				    	document.getElementById("districtMlcId").style.display = '';
   				    	document.getElementById("districtMla").style.display = 'none';
   				    	document.getElementById("districtMlaId").style.display = 'none';
   			}
   			else if(memberType === 'EX-MLC'){
   		    //	document.getElementById("constName").style.display = 'none';
   		    //    document.getElementById("constVal").style.display = 'none';
   		        document.getElementById("districtMlc").style.display = '';
   		    	document.getElementById("districtMlcId").style.display = '';
   		    	document.getElementById("districtMla").style.display = 'none';
   		    	document.getElementById("districtMlaId").style.display = 'none';
            	}
   			else if(memberType === 'FSOD'){
   		    //	document.getElementById("constName").style.display = 'none';
   		    //    document.getElementById("constVal").style.display = 'none';
   		        document.getElementById("districtMlc").style.display = '';
   		    	document.getElementById("districtMlcId").style.display = '';
   		    	document.getElementById("districtMla").style.display = 'none';
   		    	document.getElementById("districtMlaId").style.display = 'none';
            	}
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

document.addEventListener("DOMContentLoaded", function() {
    dependentData = [];
    var rows = document.querySelectorAll("#dependentTableBody tr");
    rows.forEach(function(row) {
        var cells = row.querySelectorAll("td");
        if (cells.length === 10) {
            dependentData.push({
                cardNo: cells[0].innerText.trim(),
                dependentName: cells[1].innerText.trim(),
                dependentMobileNo: cells[2].innerText.trim(),
                dependentGender: cells[3].innerText.trim(),
                dependentDob: cells[4].innerText.trim(),
                relationship: cells[5].innerText.trim(),
                maritalStatus: cells[6].innerText.trim(),
                aadhaarNo: cells[7].innerText.trim(),
                aadhaarPhoto: null
            });
        }
    });
    console.log("Initial dependentData loaded:", dependentData);
});

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
            background-color: #ddd;
            border: 1px solid #888;
        }
        .back-btn {
            padding: 5px 15px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        .reject-btn {
            padding: 3px 5px;
            background-color: #ddd;
            border: 1px solid #888;
        }
        
        
    </style>
</head>
<body>
<br>
<div>
      <input type="button" class="back-btn" 
             style="background-color: #4A90E2; color: white; margin-left: 94%;" 
             value="Back" onclick="fn_back()" />
</div>

 
    <div class="section-title" style="text-align: center;">MLA/MLC Personal Details</div>
    
<logic:notEmpty name="registeredLhsPatientsList">
    <bean:define id="patientVO" name="registeredLhsPatientsList" />
</logic:notEmpty>

    <table style="margin-top: 11px;">
		<tr>

			<td><label>Full Name</label></td>
			<td><input type="text" name="fullName" value="<bean:write name='patientVO' property='firstName'/>" /></td>

			<td><label>Father Name </label></td>
			<td><input type="text" name="fatherName"
				value="<bean:write name='patientVO' property='fatherName'/>" /></td>
				
			<td><label>Gender</label></td>
					<td>
					    <input type="radio" name="gender" value="M"
					        <logic:equal name="patientVO" property="gender" value="Male">checked="checked"</logic:equal> />
					    Male
					
					    <input type="radio" name="gender" value="F"
					        <logic:equal name="patientVO" property="gender" value="Female">checked="checked"</logic:equal> />
					    Female
					</td>
			
		</tr>
		
		 <tr>
		
			<td><label>Date of Birth </label></td>
			<td><input type="text" name="dob" id="dob" style="width: 120px;"
				value="<bean:write name='patientVO' property='dateOfBirth'/>" /></td>

			<td><label>Mobile Number </label></td>
			<td><input type="text" name="mobileNo" id="mobileNo"
				maxlength="10" onkeyup="validateMobile(this)"
				onchange="mobileLength(this)"
				value="<bean:write name='patientVO' property='mobile'/>" /></td>
				
			<td><label>Aadhaar Number </label></td>
            <td>
              <input type="text" name="aadharNo" id="aadharNo" style="width: 120px;"
				value="<bean:write name='patientVO' property='aadharID'/>" />
		    </td>
		    
		</tr>
		
	</table>

    <div class="section-title" style="text-align: center;">MLA/MLC Member Details</div>
    <table style="margin-top: 11px;">
        <tr>
       
			  <td><label>Member Type</label></td>
			<td>
			  <bean:define id="memberType" name="patientVO" property="memberType" />
              <input type="text" name="memberType" id="memberType" value="<%= memberType %>" readonly />
			</td>
    
            <logic:equal name="patientVO" property="memberType" value="MLA">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="MLC">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="EX-MLA">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="EX-MLC">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
			<logic:equal name="patientVO" property="memberType" value="FSOD">
			    <bean:define id="showConst" value="true" />
			</logic:equal>
			
         <logic:present name="patientVO" property="constituency">
			    <td id="constName" style="display:none;">
			        <label>Constituency Name</label>
			    </td>
			    <td id="constVal" style="display:none;">
			       <bean:define id="constituency" name="patientVO" property="constituency" />
                  <input type="text" name="constType" id="constType" value="<%= constituency %>" readonly/>
			    </td>
	     </logic:present>  
	         
		</tr>
		<tr>	
            <td><label>MLA/MLC/PS ID Number </label></td>
          <!--   <td><input type="text" name="lhsId" /></td> -->
            <td><input type="text" style="width: 120px;"
				value="<bean:write name='patientVO' property='cardNo'/>" readonly/></td>
       
            <td>
			  <label>Term Start Date </label>
			</td>
			<td>
			  <input type="text" name="termStartDate" id="termStartDate" style="width: 120px;"
				value="<bean:write name='patientVO' property='termStartDate'/>" />
			</td>

            
            <td><label>Term End Date </label></td>
            <td>
			  <input type="text" name="termEndDate" id="termEndDate" style="width: 120px;"
				value="<bean:write name='patientVO' property='termEndDate'/>" />
			</td>
			
			  <td><label>Registartion Number </label></td>
            <td>
			 <td><input type="text" name="lhsId" id="lhsId" style="width: 120px;"
				value="<bean:write name='patientVO' property='patientId'/>" /></td>
        </tr>
        
        <c:if test="${caseStatus == 'A'}">
         <tr>
		    <td colspan="6">
		        <input type="button" value="Add Dependent" class="submit-btn" id="toggleDependentBtn" onclick="addDependentRow(this);" />
		    </td>
		</tr>
	 </c:if>
	 
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
    
       <table id="dependentTable" border="1" style="margin-top:20px; width: 100%;">
    <thead style="background: #4A90E2;color: white;">
        <tr>
            <th>Dependent Card No</th>
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
        <logic:notEmpty name="registeredDependentsList">
            <logic:iterate id="dependentVO" name="registeredDependentsList">
                <tr>
                    <td><bean:write name="dependentVO" property="cardNo" /></td>
                    <td><bean:write name="dependentVO" property="firstName" /></td>
                    <td><bean:write name="dependentVO" property="gender" /></td>
                    <td><bean:write name="dependentVO" property="dateOfBirth" /></td>
                    <td><bean:write name="dependentVO" property="type1" /></td>
                    <td><bean:write name="dependentVO" property="memberType" /></td>
                    <td><bean:write name="dependentVO" property="mobile" /></td>
                    <td><bean:write name="dependentVO" property="aadharID" /></td>
                    <td>
                        <logic:notEmpty name="dependentVO" property="aadharFileName">
                            <bean:define id="aadharFilePath" name="dependentVO" property="aadharFilePath" />
                            <bean:define id="aadharFileName" name="dependentVO" property="aadharFileName" />
                             
                            <a href="javascript:void(0);"
                               onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= aadharFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
                               <%= aadharFileName %>
                            </a>
                        </logic:notEmpty>
                        <logic:empty name="dependentVO" property="aadharFileName">
                            No file chosen
                        </logic:empty>
                    </td>
                    <td>
					    <button type="button" onclick="deleteDependentRow(this)">Delete</button>
					</td>
                </tr>
            </logic:iterate>
        </logic:notEmpty>
        <logic:empty name="registeredDependentsList">
            <tr id="noDependentRow">
                <td colspan="10" style="text-align:center;">No dependent added</td>
            </tr>
        </logic:empty>
    </tbody>
</table>

    <div class="section-title" style="text-align: center;">MLA/MLC Address Details</div>
    <table style="margin-top: 11px;">
        <tr>
            <td><label>Permanent Address </label></td>
            <td>
              <!--  <input type="text" name="perAdd" /> -->
              <input type="text" name="perAdd" id="perAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='permanentAddress'/>" />
            </td>
            
            <td><label>Official Address </label></td>
            <td>
            <!--  <input type="text" name="offAdd" /> -->
             <input type="text" name="offAdd" id="offAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='officialAddress'/>" />
            </td>
            
            <td><label>Constituency Address </label></td>
            <td>
             <!-- <input type="text" name="constAdd" /> -->
             <input type="text" name="constAdd" id="constAdd" style="width: 120px;"
				value="<bean:write name='patientVO' property='constituencyAddress'/>" />
            </td>
        </tr>
        <tr>  
          <%-- <td id="districtMla"><label>District</label></td>
			<td id="districtMlaId">
			    <logic:present name="lhsMemberDistrict">
			        <bean:define id="district" name="lhsMemberDistrict" />
                    <input type="text" name="district" id="district" value="<%= district %>" readonly />
			    </logic:present>
			</td> --%>
			<td id="districtMla"><label>District</label></td>
			<td id="districtMlaId">
			         <logic:present name="lhsMemberDistrict">
				        <bean:define id="district" name="lhsMemberDistrict" />
				        <select name="district" id="district" onchange="populatePinCode(this.value)">
				            <option value=""><%= district %></option>
				        </select>
				    </logic:present>
				
				    <logic:notPresent name="lhsMemberDistrict">
				        <select name="district" id="district" onchange="populatePinCode(this.value)">
				            <option value="">---select---</option>
				        </select>
				    </logic:notPresent>
			
			</td>
			
	
	<td id="districtMlc" style="display:none;"><label>District</label></td>
			<td id="districtMlcId" style="display:none;">
			        <logic:present name="lhsMemberDistrict">
			        <bean:define id="district" name="lhsMemberDistrict" />
			        <html:select name="patientForm" property="district" styleId="districtID" style="width:17em;">
			            <html:option value=""><%= district %></html:option>
			            <html:options collection="districtsList" property="districtCode" labelProperty="firstName" />
			        </html:select>
			    </logic:present>
			
			    <logic:notPresent name="lhsMemberDistrict">
			        <html:select name="patientForm" property="district" styleId="districtID" style="width:17em;">
			            <html:option value="-1">---Select---</html:option>
			            <html:options collection="districtsList" property="districtCode" labelProperty="firstName" />
			        </html:select>
			    </logic:notPresent>
			
			</td>
			 
            <td><label>PIN Code </label></td>
            <td>
             <!--  <input type="text" name="pinCode" id="pinCode" /> -->
             <input type="text" name="pinCode" id="pinCode" style="width: 120px;"
				value="<bean:write name='patientVO' property='pinCode'/>" />
            </td>
            <td><label>Email ID</label></td>
			<td><input type="text" name="emailId" id="email"
				value="<bean:write name='patientVO' property='eMailId'/>" /></td>
            
         </tr>
         <tr>
          <tr>
<td><label>Aadhaar Card</label></td>
<td>
    <logic:notEmpty name="patientVO" property="aadharFileName">
        <bean:define id="aadharFilePath" name="patientVO" property="aadharFilePath" />
        <bean:define id="aadharFileName" name="patientVO" property="aadharFileName" />
        
        <input type="hidden" id="removeExistingAadhar" name="removeExistingAadhar" value="false" />
        <input type="hidden" id="existingAadharFileName" name="existingAadharFileName" value="<%= aadharFileName %>" />
        <input type="hidden" id="existingAadharFilePath" name="existingAadharFilePath" value="<%= aadharFilePath %>" />
        
        <!-- Show existing file name -->
        <span id="existingAadharFile">
            <a href="javascript:void(0);"
               onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= aadharFilePath %>', 
                                   'PatientRegPrintPage', 
                                   'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
               <%= aadharFileName %>
            </a>
            <br/>
        </span>
        
        <!-- File input for uploading new file -->
        <input type="file" id="aadhaarFile" name="aadhaarFile" 
               onchange="document.getElementById('removeExistingAadhar').value='true'; 
                        document.getElementById('existingAadharFile').style.display='none';" />
    </logic:notEmpty>
    
    <logic:empty name="patientVO" property="aadharFileName">
        <input type="file" id="aadhaarFile" name="aadhaarFile" onchange="validateAttachment(this)" /></label>
      </logic:empty>
</td>

             <td><label>MLA/MLC Photo</label></td>
	      
	         <td>
				  <logic:notEmpty name="patientVO" property="photoFileName">
				   
				     <bean:define id="photoFilePath" name="patientVO" property="photoFilePath" />
					 <bean:define id="photoFileName" name="patientVO" property="photoFileName" />
					 <input type="hidden" id="removeExistingPhoto" name="removeExistingPhoto" value="false" />
					  <span id="existingPhotoFile">
				            <a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= photoFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= photoFileName %>
									</a>
				            <br/>
				        </span>
					<input type="file" id="aadhaarFile" name="aadhaarFile" 
                      onchange="document.getElementById('removeExistingPhoto').value='true'; 
                        document.getElementById('existingPhotoFile').style.display='none';" />		
				  </logic:notEmpty>
				  
				  <logic:empty name="patientVO" property="photoFileName">
				   <input type="file" id="photoFile" name="photoFile" onchange="validateAttachment(this)" /></label>
				  </logic:empty>
			 </td>
	            
	           <td><label>Support Document</label></td>
	          
	            <td>
				  <logic:notEmpty name="patientVO" property="supportFileName">
				      <bean:define id="supportFilePath" name="patientVO" property="supportFilePath" />
					  <bean:define id="supportFileName" name="patientVO" property="supportFileName" />
					  <input type="hidden" id="removeExistingsupport" name="removeExistingsupport" value="false" />
						<span id="existingSupportFile">
				           <a href="javascript:void(0);"
									   onclick="window.open('patientDetailsNew.do?actionFlag=viewDocument&fileName=<%= supportFilePath %>', 'PatientRegPrintPage', 'left=50,top=50,width=900,height=900,toolbar=no,resizable=no,scrollbars=yes');">
									   <%= supportFileName %>
									</a>
				            <br/>
				        </span>	
					<input type="file" id="aadhaarFile" name="aadhaarFile" 
                      onchange="document.getElementById('removeExistingsupport').value='true'; 
                        document.getElementById('existingSupportFile').style.display='none';" />
				  </logic:notEmpty>
				  
				  <logic:empty name="patientVO" property="supportFileName">
				    <input type="file" id="supportFile" name="supportFile" onchange="validateAttachment(this)" /></label>
				  </logic:empty>
  
			   </td>
         </tr>
         <tr>
         
		 <c:if test="${caseStatus == 'REV'}">
		 
			        <td><label>Revert Reason</label></td>
			        <td> 
			        <input type="text" name="rejReason" id="rejReason"
				        value="<bean:write name='patientVO' property='revertRemarks'/>" readonly />
		         </td>
		</c:if>
         </tr>
      
    </table>
    <br>
   
      <!--    <input type="button" class="submit-btn" 
	           style="background-color: #4A90E2; color: white;" 
	           value="Submit" onclick="fn_submit()" /> -->


   
</body>
</html>
</fmt:bundle>

