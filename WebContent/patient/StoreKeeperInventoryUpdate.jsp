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
<form id="vendorForm" method="post" action="vendorActionNew.do" >
<table  id="drugForm" width="100%" style="margin:0 auto" >
<tr><th class="tbheader" colspan="3" style = "background-color: #38b376;">DRUG RECEIVING FORM</th>
</tr>

</table>
<br/>
<br/>
  	
		<div style="width:100%; overflow-x:auto; overflow-y:auto; max-height:450px; border:1px solid #ccc;margin-left: 10px">
		<section id="no-more-tables">
          <table class="table"  id="miscActTbl"  cellSpacing="1" cellPadding="1" style="font-size:11px;text-align:center;margin:0 auto;width: 100%;">
					 <thead id="thead_dashboard">
					 	<tr>
					 		
					       <th class="tbheader1 border" id = "sno" style = "background-color: #b4d6b4;color:black;">S.NO<!-- <input type="checkbox" id="select_all"/><input class="checkAll" type="checkbox" onclick="checkAll(this)"></th> --></th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Indent No</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">PO No</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">Invoice Number</th>
 							<th class="tbheader1 border" style = "background-color: #b4d6b4;color:black;">STATUS</th>
 							
						</tr>
					</thead>
					<logic:present name="storeDrugList" scope="request">
  					<tbody>
					<logic:iterate name="storeDrugList" id="labelBean" indexId="index">

					<input type="hidden" id="status${index+1}" value="<bean:write name="labelBean" property="INTERNALSTATUS"/>" >
					<input type="hidden" id="INDENT_ID" value="<bean:write name="labelBean" property="INDENT_ID"/>" >
					<input type="hidden" id="DISPNAME" value="<bean:write name="labelBean" property="DISP_ID"/>" >
					
    <tr>
        <td class="tbcellBorder"><%= index + 1 %></td>
        <logic:notEmpty name="labelBean" property="INDENT_ID">
            <td class="tbcellBorder">
            <bean:write name="labelBean" property="INDENT_ID" />
        </td>
        </logic:notEmpty>
        <logic:empty name="labelBean" property="INDENT_ID">
            <td class="tbcellBorder">-NA-</td>
        </logic:empty>
        
        <logic:notEmpty name="labelBean" property="POID">
            <td class="tbcellBorder" 
            <%-- style="cursor:pointer; color:blue; text-decoration:underline;"
            onclick="fn_openIndentDetails('${labelBean.POID}','${labelBean.STATUS}')" --%>>
                <bean:write name="labelBean" property="POID" />
            </td>
        </logic:notEmpty>
        
        <logic:empty name="labelBean" property="POID">
            <td class="tbcellBorder">-NA-</td>
        </logic:empty>

        <logic:notEmpty name="labelBean" property="INVOICENO">
            <td class="tbcellBorder" style="cursor:pointer; color:blue; text-decoration:underline;"
             onclick="fn_openIndentDetails('${labelBean.INVOICENO}','${labelBean.STATUS}','${labelBean.DISPID}','${labelBean.POID}')">
                <bean:write name="labelBean" property="INVOICENO" />
            </td>
        </logic:notEmpty>
        
        <logic:empty name="labelBean" property="INVOICENO">
            <td class="tbcellBorder">-NA-</td>
        </logic:empty>

        <logic:notEmpty name="labelBean" property="INTERNALSTATUS">
            <td class="tbcellBorder">
                <bean:write name="labelBean" property="INTERNALSTATUS" />
            </td>
        </logic:notEmpty>
        <logic:empty name="labelBean" property="STATUS">
            <td class="tbcellBorder">-NA-</td>
        </logic:empty>
    </tr>
</logic:iterate>
					</tbody>
					</logic:present>
					<logic:notPresent name="storeDrugList" scope="request">
    <tr><td colspan="6" style="text-align:center;">No data available</td></tr>
</logic:notPresent>	
					</table>
					</section>
					</div>
				<br/>
			<div class="modal fade" id="poNoDtls" role="dialog" >
    <div class="modal-dialog" style="width:100%">
    
      <!-- Modal content-->
      <div class="modal-content" style = "overflow-x: auto; overflow-y: auto;width: 100%;" >
     	<div style="text-align:center;color:#fdfdfd; font-size: 14px;">
          
          
          <button type="button" class="close" style="opacity: 1;color: #01964d;margin-right: -12%;margin-left: 17%;" onclick="closeModal('poNoDtls','phcDataFrameDiv')">X</button> 
          <h3 style="text-align:center;color:rgb(1, 140, 128) !important; font-size: 16px;"><div id="header" style="display: contents; color: white;"></div></h3>
          
        </div>

        <div class="modal-body">
        <div>
		<div id="phcCountDiv">

		<section id="no-more-tables">
          <table id="teamDtlsTbl" class="table fontclass table-bordered" style = "font-size:11px;">
					 <thead id="thead_dashboard" style = "background-color: #b4d6b4;color: black">
				<tr id="teamHdrRow">
							<th class="tableHeader bothSideBorder2Px"  style="text-align:center"><b>S.No</b></th>
							<th class="tableHeader bothSideBorder2Px" style="text-align:center"><b>PO NO</b></th>
							<th class="tableHeader bothSideBorder2Px" style="text-align:center""><b>PO DATE</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="text-align:center""><b>DISTRIBUTOR NAME</b></th>
							<th class="tableHeader bothSideBorder2Px" style="text-align:center""><b>DRUG TYPE</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="text-align:center""><b>DRUG NAME</b></th>
							<th class="tableHeader bothSideBorder2Px"  style="text-align:center""><b>Rate Contract Price</b></th>  
							<th class="tableHeader bothSideBorder2Px"  style="text-align:center""><b>PO QTY</b></th> 
							<th class="tableHeader bothSideBorder2Px" style="text-align:center">Batch No </th>
					        <th class="tableHeader bothSideBorder2Px" style="text-align:center">Invoice Date</th>
					        <th class="tableHeader bothSideBorder2Px" style="text-align:center">Invoice Number </th>
          					<th class="tableHeader bothSideBorder2Px" style="text-align:center">Expiry Date </th>
					        <th class="tableHeader bothSideBorder2Px" style="text-align:center">Issued Quantity</th>
					        <th class="tableHeader bothSideBorder2Px" style="text-align:center">Received Quantity</th>
							<th class="tableHeader bothSideBorder2Px"  style="text-align:center""><b>PO STATUS</b></th> 
							<th class="tableHeader bothSideBorder2Px" style="text-align:center">Invoice Amount</th>
							<th class="tableHeader bothSideBorder2Px" style="text-align:center">GST SLAB</th>
							<th class="tableHeader bothSideBorder2Px" style="text-align:center">Total Amount</th>
							<th class="tableHeader bothSideBorder2Px" style="text-align:center">View Document</th>
						</tr>
					</thead>
					<tbody id="tbodyC1">
	           </tbody>	
					
					</table>
					
					</section>
					<div style="text-align:center; margin-top: 15px;">
    <input class="but" type="button" value="Submit" 
        id="submitBtn" onclick="fn_submit();" 
        style="padding: 8px 20px; font-size: 14px; display: inline-block;" />
	</div>
					</div>
         <div id="phcDataDiv" style="display:none;">
			<iframe id="phcDataFrameDiv" name="phcDataFrameDiv" frameborder="0" width="100%" height="450px"></iframe>
  		 </div> 
					</div>
        </div>
        
      </div>
     
    </div>
  </div>

</form>
<script type="text/javascript">
//var $= jQuery.noConflict();
$(document).on("input", ".onlyNumber", function () {
    this.value = this.value.replace(/[^0-9]/g, '');
});

function fn_openIndentDetails(invoiceNo,status,dispId,poNo){
	var dispId = document.getElementById("DISPNAME").value;
	//alert(dispId);
	
		var url = 'vendorActionNew.do';
	    var params={
				'actionFlag':'indentOnclick',
				'invoiceNo':invoiceNo,
				'status':status,
				'dispId':dispId,
				'poNo':poNo,
				};
	    $("#remainingQtyTh").remove();
	    if (status === 'CD1422') {
	        $("#teamHdrRow th").eq(13).after(
	            "<th id='remainingQtyTh' style='text-align:center'>Remaining Qty</th>"
	        );
	    }
	   $.ajax({
	        type: 'POST',
	        url: url,
			data: params,
	        success: function(data)
	        {
	        	data=data.replace("*","");
			 	var result=JSON.parse(data);
			 	var content="";
			 	
					document.getElementById("tbodyC1").innerHTML="";
					//document.getElementById("header").innerHTML =let poNo;
					for (var j = 0; j < result.length; j++) {
					    content += "<tr>";

					    content += "<td>" + (j + 1) + "</td>";

					    content += "<td class='poId'>" + result[j].POID + "</td>";
					    content += "<td class='poDate'>" + result[j].PODATE + "</td>";
					    content += "<td class='distributor'>" + result[j].DISTRIBUTOR_NAME + "</td>";
					    content += "<td class='drugType'>" + result[j].DRUG_TYPE + "</td>";
					    content += "<td class='drugName'>" + result[j].DRUGNAME + "</td>";
					    content += "<td class='rcPrice'>" + result[j].RC_PRICE + "</td>";
					    content += "<td class='poQty'>" + result[j].INDENT_COUNT + "</td>";
					    content += "<td class='batchNo'>" + result[j].BATCHNO + "</td>";
					    content += "<td class='invoiceDate'>" + result[j].INVOICE_NUM + "</td>";
					    content += "<td class='invoiceNo'>" + result[j].INVOICENUMBER + "</td>";
					    content += "<td class='expiryDate'>" + result[j].DOSAGE + "</td>";
					    content += "<td class='quantity'>" + result[j].QUANTITY + "</td>";
					    var receivedQtyVal = result[j].RECEIVED_STOCK ? result[j].RECEIVED_STOCK : "";
					    if (status === 'CD1422') {
					    	content += "<td><input type='number' style='width:92px;'  class='receivedQty form-control' " +
				               "value='" + receivedQtyVal + "' readonly></td>";
					    }else {
					        content += "<td><input type='number' style='width:92px' class='receivedQty form-control' value='" +
					     
				               receivedQtyVal + "'></td>";
						}
					   /*  content += "<td><input type='number' class='receivedQty' /></td>"; */
					    if (status === 'CD1422') {
					    	content += "<td><input type='number' " +
					           "style='width:92px;' " +
					           "class='remainingQty form-control' " +
					           "min='1' step='1' " +
					           "oninput='validateRemainingQty(this)' /></td>";
		                }
					    content += "<td class='internalStatus'>" + result[j].INTERNALSTATUS + "</td>";
					    content += "<td class='invoiceAmt'>" + result[j].NEXTVAL + "</td>";
					    content += "<td class='gstSlab'>" + result[j].CONST + "</td>";
					    content += "<td class='totalAmt'>" + result[j].DIFF + "</td>";
					    content += "<td class='viewDocument' " +
				           "style='cursor:pointer; color:blue; text-decoration:underline;' " +
				           "onclick=\"viewDocument('" + result[j].RETURN_DATE + "')\">" +
				           result[j].RETURN_DATE +
				           "</td>";
						content += "<input type='hidden' class='drugId' value='" + result[j].DRUG_ID + "'/>";
					    content += "<input type='hidden' class='drugTypeId' value='" + result[j].DRUGTYPE + "'/>";
					    content += "<input type='hidden' class='mnfctrId' value='" + result[j].MNFCTRID + "'/>";
					    content += "<input type='hidden' class='dstrbtrId' value='" + result[j].DSTRBTR_ID + "'/>";
					    content += "<input type='hidden' class='dispId' value='" + result[j].DISPNAME + "'/>";
					    content += "<input type='hidden' class='statusList' value='" + result[j].STATUS + "'/>";
					    content += "<input type='hidden' class='itemId' value='" + result[j].ITEMID + "'/>";
					    content += "<input type='hidden' class='indentId' value='" + result[j].INDENT_ID + "'/>";

					    content += "</tr>";

					}
					document.getElementById("tbodyC1").innerHTML = content;

			 	$('html, body').animate({scrollTop: 0}, 500, 'linear');	
			 	$("#phcDataDiv").hide();
			 	$("#phcBackBtn").hide();
			 	$("#phcHeading").hide();
				$("#phcCountDiv").show();
				$('#poNoDtls').modal('show'); 
				//fn_removeLoadingImage();
	 		 	
	        }
	        
	    }); 
	}
	
function validateRemainingQty(input) {

    input.value = input.value.replace(/[^0-9]/g, '');

    if (input.value !== '') {
        var val = parseInt(input.value, 10);

        if (val < 1) {
            input.value = '';
            alert('Remaining Qty must be at least 1');
        }
    }
}


function closeModal(arg,frameId)
{
	document.getElementById(frameId).src="";
	$("#"+arg).modal('hide');
}

function fn_submit() {

    var rows = $("#tbodyC1 tr");
    if (rows.length === 0) {
        alert("No records available to submit.");
        return;
    }

    var storePOList = [];
    var dispId = "";
    var isValid = true;

    rows.each(function (index) {

        var row = $(this);

        if (!dispId) {
            dispId = row.find(".dispId").val();
        }

        var receivedQty = Number(row.find(".receivedQty").val());
        var remainingQtyInput = row.find(".remainingQty");
        var remainingQty = 0;

        if (remainingQtyInput.length > 0) {
            remainingQty = Number(remainingQtyInput.val());

            if (remainingQty < 0) {
                alert("Remaining Quantity cannot be negative in row " + (index + 1));
                isValid = false;
                return false;
            }
            if (remainingQty === 0) {
                alert("Remaining Quantity cannot be 0 in row " + (index + 1));
                isValid = false;
                return false;
            }
        }
        
        if (receivedQty < 0) {
            alert("Received Quantity cannot be negative in row " + (index + 1));
            isValid = false;
            return false;
        }
        
        if (receivedQty === 0) {
            alert("Received Quantity cannot be 0 in row " + (index + 1));
            isValid = false;
            return false;
        }

    
        var finalReceivedQty = receivedQty + remainingQty;

        
        var quantity = Number(row.find(".quantity").text().trim());
        var remainingQtyInput = row.find(".remainingQty");

        if (remainingQtyInput.length > 0) {
            var remainingQty = Number(remainingQtyInput.val());

            var maxRemainingQty = quantity - Number(receivedQty);

            if (remainingQty > maxRemainingQty) {
                alert(
                    "Remaining Quantity cannot exceed " + maxRemainingQty +
                    " in row " + (index + 1)
                );
                isValid = false;
                return false;
            }
        }

        var poQty = row.find(".poQty").text().trim();
        if (finalReceivedQty > Number(poQty)) {
            alert("Total Received Quantity cannot exceed PO Quantity in row " + (index + 1));
            isValid = false;
            return false;
        }
        var poStatus = row.find(".statusList").val();
        var currentReceiptQty;

        if (poStatus === 'CD1422') {
            // Partial case: remainingQty is what store is entering NOW
            currentReceiptQty = remainingQty;
        } else {
            // First time: receivedQty is what store is entering NOW
            currentReceiptQty = receivedQty;
            receivedQty = 0; 
        }

        storePOList.push({

            poId: row.find(".poId").text().trim(),
            dispId: dispId,
            drugId: row.find(".drugId").val(),
            drugTypeId: row.find(".drugTypeId").val(),
            mnfctrId: row.find(".mnfctrId").val(),
            dstrbtrId: row.find(".dstrbtrId").val(),
            poDate: row.find(".poDate").text().trim(),
            invoiceDate: row.find(".invoiceDate").text().trim(),
            expiryDate: row.find(".expiryDate").text().trim(),
            drugName: row.find(".drugName").text().trim(),
            drugType: row.find(".drugType").text().trim(),
            batchNo: row.find(".batchNo").text().trim(),
            poQty: poQty,
            //receivedQty: finalReceivedQty,
            receivedQty: receivedQty,
            currentReceiptQty: currentReceiptQty,
            quantity: row.find(".quantity").text().trim(),
            rcPrice: row.find(".rcPrice").text().trim(),
            invoiceNo: row.find(".invoiceNo").text().trim(),
            invoiceAmount: row.find(".invoiceAmt").text().trim(),
            gstSlab: row.find(".gstSlab").text().trim(),
            totalAmount: row.find(".totalAmt").text().trim(),
            poStatus: row.find(".statusList").val(),
            internalStatus: row.find(".internalStatus").text().trim(),
            itemId: row.find(".itemId").val(),
            indentId: row.find(".indentId").val()

        });
    });

    if (!isValid || storePOList.length === 0) return;
    if (!confirm("Do you want to submit ?")) return;

    var formData = new FormData();
    formData.append("storePOList", JSON.stringify(storePOList));
    formData.append("dispname", dispId);

    $.ajax({
        url: "vendorActionNew.do?actionFlag=addNewMnfNDist1",
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function () {
            alert("Details have been added successfully to the inventory!");
            location.reload();
        },
        error: function (xhr) {
            alert("Error while saving data");
            console.error(xhr.responseText);
        }
    });
}

function viewDocument(docPath){
	let newWindow = window.open("", "_blank", "width=1150,height=600,resizable=yes,scrollbars=yes");
    newWindow.document.write("<html><body><p>Loading attachment...</p></body></html>");
    $.ajax({
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

</script>
</body>

</html>