<%@ page contentType="text/html;charset=ISO-8859-1" language="java"
    import="java.util.ResourceBundle, java.util.Locale, java.util.ArrayList, java.util.Hashtable,
                  java.util.StringTokenizer, java.util.Date, java.text.SimpleDateFormat, java.util.Calendar,
                  java.text.DateFormat, java.io.File, java.io.FileOutputStream, java.util.List,com.ahct.common.vo.LabelBean,
                  java.util.StringTokenizer"
    isErrorPage="false"%>
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
#otherDrugs th, #otherDrugs td {
  text-align: center;
  padding: 4px;
  border: 1px solid #cce7cc;
  white-space: nowrap;
}

#otherDrugs input[type="text"], 
#otherDrugs input[type="file"] {
  
  height: 22px;
  box-sizing: border-box;
}

#otherDrugs th {
  color: #007b33;
  font-weight: bold;
}

#otherDrugs {
  table-layout: fixed;
}

</style>
</head>
<body>
<br/>
<form id="fileUpload" method="post" action="vendorActionNew.do" enctype="multipart/form-data" method="post"> 
<table  id="drugForm" width="100%" style="margin:0 auto">
<tr><th class="tbheader" colspan="3" style = "background-color: #38b376;">DRUG ISSUE FORM</th>
</tr>
<tr>
<td width="25%" class="labelheading1 tbcellCss"><b>Status</b></td>
<th width="25%" class="labelheading1 tbcellCss"><b>PO </b><font color="red">*</font></th>
</tr>

<tr>
   <td width="25%" valign="top" class="tbcellBorder">
	<html:select name="patientForm" property="status" styleId="status"
	    	     title="Select Status" onchange="loadPoList()">
		<html:option value="-1">Select</html:option>
		<html:option value="CD1419">PO Issued</html:option>
		<html:option value="CD1420">Partially Issued</html:option>
		<html:option value="CD1425">Waiting for Invoice Upload</html:option>
	</html:select>
   </td>

   <td width="25%" valign="top" class="tbcellBorder">
     <select id="poDropdown" name="poDropdown" onchange="handlePoSelection()" style="padding-right:20%;">
       <option value="-1">Select</option>
     </select>
   </td>
</tr> 
</table>
<br/>

<br/>
  	
		<div id="poTableDiv" style="display:none;width:97%; overflow-x:auto; overflow-y:auto; max-height:450px; margin-left:2%;">

		 <section id="no-more-tables">
          <table class="table"  id="miscActTbl"  cellSpacing="1" cellPadding="1" style="font-size:12px;text-align:center;margin:0 auto;width: 95%;">
					<thead id="thead_dashboard" style = "background-color: #b4d6b4;color:black;">
					 	<tr>
					 		<th id="checkBoxTh" class='tableHeader bothSideBorder2Px' style = "width: 30px;"><label><input type='checkbox' id='checkAll' class='selectableCheckbox' title='click here to select all Cases' onclick='fn_checkAll();' /></label></th>
					 		<th class="tbheader1 border" id = "sno" style = "background-color: #b4d6b4;color:black;">S.NO</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Indent No</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">PO No</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Drug Type</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Drug Name</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Status</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Invoice Number</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Po Qty</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Rate Contract Quantity</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Quantity</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Total Amount</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">View Document</th>
 							<!-- <th id="uploadTh" class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Upload Document</th> -->
 							
						</tr>
					</thead>
					<tbody id="tbodyC1">
					</table>
					</section>
					</div>
				<br/>
					<!-- <input type="file" id="uploadFile" name="fileUpload"  accept=".pdf" style="display:none" onchange="submitUpload()" /> -->
				<input type="file" id="hiddenFileInput" name="fileUpload" accept=".pdf" style="display:none" onchange="submitUploadForSelected()" />
				<tr>
				<td align="center">
				<input class="but" type="button" value="UPLOAD" id="uploadFile" onclick="javascript:fn_submit();" style = "margin-left: 43%;background-color: green;display:none;"/>
				</td>	
			 	</tr>
				</form>
				<script type="text/javascript">
				var jq= jQuery.noConflict();
				
				function viewDocument(docPath){
					let newWindow = window.open("", "_blank", "width=1150,height=600,resizable=yes,scrollbars=yes");
				    newWindow.document.write("<html><body><p>Loading attachment...</p></body></html>");
				    jq.ajax({
				        url: 'vendorActionNew.do?actionFlag=viewVendorDocument',
				        data: { docPath: docPath },
				        type: 'GET',
				        success: function (data) {
				            let attachments = Array.isArray(data) ? data : JSON.parse(data);
				            if (attachments.length > 0) {
				                let html = "<html><head><title>Attachment</title></head><body>";
				                attachments.forEach(item => {
				                    if (item.SAVED_NAME) {
				                        if (item.SAVED_NAME.startsWith("data:application/pdf")) {
				                            html += "<embed src='" + item.SAVED_NAME + "' type='application/pdf' width='100%' height='100%'/>";
				                        } else if (item.SAVED_NAME.startsWith("data:image")) {
				                            html += "<img src='" + item.SAVED_NAME + "' style='width:100%;height:auto;' />";
				                        }
				                    }
				                });
				                html += "</body></html>";
				                newWindow.document.open();
				                newWindow.document.write(html);
				                newWindow.document.close();
				            } else {
				                newWindow.document.write("<p>No attachments found.</p>");
				            }
				        },
				        error: function () {
				            newWindow.document.write("<p>Failed to load attachments.</p>");
				        }
				    });
				}
				let selectedRowIndex = null;

				function openFileChooser(index) {
				    selectedRowIndex = index;
				    const fileInput = document.getElementById("uploadFile");
				    fileInput.value = "";
				    fileInput.click();
				}
				
				function fn_checkAll() {
				    var elements = document.getElementsByClassName("selectableCheckbox");
				    for (var i = 1; i < elements.length; i++) {
				        elements[i].checked = document.getElementById("checkAll").checked;
				    }
				}
				
				function fn_submit() {
				   
				    var checkboxes = jq("#tbodyC1 input.selectableCheckbox:checked");

				    if (checkboxes.length === 0) {
				        alert("Please select at least one row to upload.");
				        return;
				    }

				    document.getElementById("hiddenFileInput").value = ""; 
				    document.getElementById("hiddenFileInput").click();
				}
				
				function submitUploadForSelected() {
				    var fileInput = document.getElementById("hiddenFileInput");
				    var file = fileInput.files[0];
				    if (!file) return;

				    var selectedIndices = [];
				    jq("#tbodyC1 input.selectableCheckbox:checked").each(function () {
				        selectedIndices.push(jq(this).data("rowindex"));
				    });

				    if (selectedIndices.length === 0) return;

				    var totalRows   = selectedIndices.length;
				    var uploadedCount = 0;
				    var failedCount   = 0;
				    function uploadNext(pos) {
				        if (pos >= totalRows) {
				           
				            if (failedCount === 0) {
				                alert("Upload successful for " + uploadedCount + " record(s).");
				            } else {
				                alert("Upload completed. Success: " + uploadedCount + ", Failed: " + failedCount + ".");
				            }
				           
				            jq("#tbodyC1 input.selectableCheckbox:checked").each(function () {
				                jq(this).closest("tr").remove();
				            });
				            document.getElementById("hiddenFileInput").value = "";
				            
				            if (jq("#tbodyC1 tr").length === 0) {

				                jq("#tbodyC1").empty();
				                jq("#poTableDiv").hide();
				                jq("#status").val("-1");
				                jq("#poDropdown").html('<option value="-1">Select</option>');
				                jq("#uploadFile").hide();
				            }
				            /* jq("#tbodyC1").empty();
				            jq("#poTableDiv").hide();
				            jq("#status").val("-1");
				            jq("#poDropdown").html('<option value="-1">Select</option>');
				            jq("#uploadFile").hide(); */
				            return;
				        }

				        var i = selectedIndices[pos];

				        var formData = new FormData();
				        formData.append("upload", file);
				        formData.append("poNo", document.getElementById("poid_"      + i).value);
				        formData.append("indentId", document.getElementById("indentId_"  + i).value);
				        formData.append("itemId", document.getElementById("itemId_"    + i).value);
				        formData.append("invoiceNo", document.getElementById("invoiceNo_" + i).value);
				        formData.append("invoiceDate", document.getElementById("invoiceDate_"+ i).value);
				        formData.append("poQty", document.getElementById("poqty_"     + i).value);
				        formData.append("quantity", document.getElementById("quantity_"  + i).value);

				        jq.ajax({
				            url: "vendorActionNew.do?actionFlag=uploadVendorDocument",
				            type: "POST",
				            data: formData,
				            processData: false,
				            contentType: false,
				            success: function () {
				                uploadedCount++;
				                uploadNext(pos + 1);
				            },
				            error: function () {
				                failedCount++;
				                uploadNext(pos + 1);
				            }
				        });
				    }

				    uploadNext(0);
				}

				/* function submitUpload() {

				    const fileInput = document.getElementById("uploadFile");
				    const file = fileInput.files[0];
				    if (!file) return;

				    const index = selectedRowIndex;

				    let formData = new FormData();
				    formData.append("upload", file);

				    formData.append("poNo", document.getElementById("poid_" + index).value);
				    formData.append("indentId", document.getElementById("indentId_" + index).value);
				    formData.append("itemId", document.getElementById("itemId_" + index).value);
				    formData.append("invoiceNo", document.getElementById("invoiceNo_" + index).value);
				    formData.append("invoiceDate", document.getElementById("invoiceDate_" + index).value);
				    formData.append("poQty", document.getElementById("poqty_" + index).value);
				    formData.append("quantity", document.getElementById("quantity_" + index).value);
				    //formData.append("pendingQty", document.getElementById("pendingQty_" + index).value);

				    jq.ajax({
				        url: "vendorActionNew.do?actionFlag=uploadVendorDocument",
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
				        success: function () {
				            alert("Upload successful");
				            var uploadedPo = document.getElementById("poid_" + index).value;

				            jq("#poDropdown option[value='" + uploadedPo + "']").remove();
				            jq("#tbodyC1").empty();
				            jq("#poTableDiv").hide();
				            jq("#status").val("-1");          
				            jq("#poDropdown").val("-1");
				            jq("#poDropdown").html('<option value="-1">Select</option>');
				            //document.getElementById("row_" + index).remove();
				        },
				        error: function () {
				            alert("Upload failed");
				        }
				    });
				} */
				function loadPoList() {
					jq("#tbodyC1").empty();
				    jq("#poTableDiv").hide();
				    var status = document.getElementById("status").value;
				    var poDropdown = document.getElementById("poDropdown");
				    poDropdown.innerHTML = '<option value="-1">Select</option>';

				    if (status === "-1") return;

				    fetchPODropdown(status);
				}
				
				function fetchPODropdown(status) {

				    if (status === "-1") {
				        document.getElementById("poDropdown").innerHTML =
				            '<option value="-1">SELECT PO</option>';
				        $("#submitBtn").hide();
				        $("#tbodyC1").empty();
				        return;
				    }

				    var xhr = new XMLHttpRequest();
				    xhr.open("POST", "vendorActionNew.do?actionFlag=getUploadList", true);
				    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

				    xhr.onreadystatechange = function () {
				        if (xhr.readyState === 4 && xhr.status === 200) {

				            var poDropdown = document.getElementById("poDropdown");
				            poDropdown.innerHTML = '<option value="-1">SELECT PO</option>';

				            try {
				                var response = JSON.parse(xhr.responseText);

				                if (!response || response.length === 0) {
				                    var opt = document.createElement("option");
				                    opt.value = "-1";
				                    opt.text = "No POs found";
				                    poDropdown.appendChild(opt);
				                    return;
				                }

				                response.forEach(function (item) {
				                    if (item.POID && item.POID !== "-NA-") {
				                        var opt = document.createElement("option");
				                        opt.value = item.PO_ID;
				                        opt.text = item.PO_ID;
				                        poDropdown.appendChild(opt);
				                    }
				                });

				            } catch (e) {
				                console.error("JSON parse error:", e);
				                alert("Error loading PO list");
				            }
				        }
				    };

				    xhr.send("status=" + encodeURIComponent(status));
				}
			
				function handlePoSelection() {

				    var poId = jq("#poDropdown").val();
				    var status = jq("#status").val();
				    //var rows = $("#tbodyC1 tr");

				    if (poId === "-1") {
				        jq("#poTableDiv").hide();
				        jq("#tbodyC1").empty();
				        jq("#uploadFile").hide();
				        return;
				    }
				    
				  

				    jq.ajax({
				        url: 'vendorActionNew.do?actionFlag=getInvoiceList',
				        type: 'POST',
				        data: { poId: poId,status: status },
				        success: function (data) {

				            data = data.replace("*", "");
				            var result = JSON.parse(data);
				            var content = "";

				            if (!result || result.length === 0) {
				                $("#tbodyC1").html(
				                    "<tr><td colspan='12' style='text-align:center;color:red;'>No data available</td></tr>"
				                );
				                jq("#poTableDiv").show();
				                return;
				            }

				            for (var i = 0; i < result.length; i++) {

				                content += "<tr id='row_" + i + "'>";
				                if (status === "CD1425") {
				                    content += "<td><input type='checkbox' class='selectableCheckbox' data-rowindex='" + i + "'></td>";
				                }

				                /* content += "<td><label><input type='checkbox' class='selectableCheckbox' data-rowindex='" + i + "'></label></td>"; */
				                content += "<td class='tbcellBorder'>" + (i + 1) + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].INDENT_ID || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].POID || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].DRUGTYPE || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].DRUGNAME || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].INTERNALSTATUS || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].INVOICENO || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].INDENT_COUNT || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].RCP || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].QUANTITY || "-NA-") + "</td>";
				                content += "<td class='tbcellBorder'>" + (result[i].ROWNUM_ || "-NA-") + "</td>";

				                if (result[i].ID) {
				                    content +=
				                        "<td class='tbcellBorder' style='cursor:pointer;color:blue;text-decoration:underline;' " +
				                        "onclick=\"viewDocument('" + result[i].ID + "')\">" +result[i].ID +"</td>";
				                } else {
				                    content += "<td class='tbcellBorder'>-NA-</td>";
				                }

				                /* if (result[i].ID) {
				                    content +=
				                        "<td class='tbcellBorder uploadTd'>" +
				                        "<span style='cursor:pointer;color:green;text-decoration:underline;' " +
				                        "onclick='openFileChooser(" + i + ")'>Upload</span></td>";
				                } else {
				                    content += "<td class='tbcellBorder uploadTd'>-NA-</td>";
				                } */

				                content += "<input type='hidden' id='poid_" + i + "' value='" + result[i].POID + "'>";
				                content += "<input type='hidden' id='indentId_" + i + "' value='" + result[i].INDENT_ID + "'>";
				                content += "<input type='hidden' id='itemId_" + i + "' value='" + result[i].ITEM_ID + "'>";
				                content += "<input type='hidden' id='invoiceNo_" + i + "' value='" + result[i].INVOICENO + "'>";
				                content += "<input type='hidden' id='invoiceDate_" + i + "' value='" + result[i].INVOICEDATE + "'>";
				                content += "<input type='hidden' id='poqty_" + i + "' value='" + result[i].INDENT_COUNT + "'>";
				                content += "<input type='hidden' id='quantity_" + i + "' value='" + result[i].QUANTITY + "'>";

				                content += "</tr>";
				            }

				            jq("#tbodyC1").html(content);
				            if (status === "CD1425") {
				                jq("#checkBoxTh").show();
				            } else {
				                jq("#checkBoxTh").hide();
				            }
				            if (status === "CD1425") {
						        jq("#uploadFile").show();
						    } else {
						        jq("#uploadFile").hide();
						    }
				            jq("#poTableDiv").show();
				        },
				        error: function () {
				        	jq("#tbodyC1").html(
				                "<tr><td colspan='12' style='text-align:center;color:red;'>Error loading data</td></tr>"
				            );
				        }
				    });
				}




				</script>
				</body>
				</html>
				