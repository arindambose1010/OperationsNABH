<!--Tejasri- CR-8812 Wellness Centers - Attendance dashboard to Wellness Centre Admin -->
<%@ page contentType="text/html;charset=ISO-8859-1" language="java"
    import="java.util.ResourceBundle, java.util.Locale, java.util.ArrayList, java.util.Hashtable,
                  java.util.StringTokenizer, java.util.Date, java.text.SimpleDateFormat, java.util.Calendar,
                  java.text.DateFormat, java.io.File, java.io.FileOutputStream, java.util.List,com.ahct.common.vo.LabelBean,
                  java.util.StringTokenizer"
    isErrorPage="false"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
 <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ include file="/common/include.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">

<!-- jQuery -->
<script src="js/jquery-1.9.1.min.js"></script>

<!-- Bootstrap -->
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/Newstyle.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="bootstrap/js/bootstrap-datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/datepicker3.css">

<!-- Select2 -->
<link href="css/select2.min.css" rel="stylesheet">
<script src="js/select2.min.js"></script>

<!-- DataTables CSS -->
<link href="css/static.jquery.dataTables.css" rel="stylesheet">

<!-- DataTables JS (core) -->
<script src="js/jquery-dataTables.min.js"></script>

<!-- Buttons extension (must load AFTER DataTables) -->
<link rel="stylesheet" href="css/buttons.dataTables.min.css">
<script src="js/dataTables-buttons.min.js"></script>

<!-- JSZip (MANDATORY for Excel) -->
<script src="js/jszip.min.js"></script>

<!-- pdfmake (MANDATORY for PDF) -->
<script src="js/pdfmake.min.js"></script>
<script src="js/vfs_fonts.js"></script>

<!-- HTML5 export buttons -->
<script src="js/buttons-html5.min.js"></script>
<script src="js/buttons.print.min.js"></script>

<!-- Other scripts -->
<script src="js/jquery.msgBox.js"></script>

<title>Wellness Center Attendance</title>

<style>
body { font-size:1.2em !important; }
.pagination { display:none; }
.bottom-margin { margin:0px 0px 3px !important; }
.marginTop { margin-top:3px; }
.but { border-radius:16px; }

/* FULL TABLE BORDERS */
#wellnessCentreList,
#wellnessCentreList th,
#wellnessCentreList td {
    border: 1px solid #000 !important;
    border-collapse: collapse !important;
}

#wellnessCentreList th, 
#wellnessCentreList td {
    padding: 9px 7px !important;
    font-size: 14px !important;
}

.dt-buttons {
    padding-left: 94% !important;
    margin-top: 7%;
    height:1%
}
.small-excel-button {
    padding: 6px 13px !important;  /* bigger button */
    font-weight: bold !important;
    margin-top: -27px !important;
}
.excel-button{
   padding: 6px 13px !important;  /* bigger button */
   font-weight: bold !important;
   margin-top: -12%;
   margin-left: -1387%;
}
.excelsmall-button{
   padding: 6px 13px !important;  /* bigger button */
   font-weight: bold !important;
   margin-top: -16%;
   margin-left: -1561%;
}

#attendanceTable thead th {
    font-size: 14px !important;
}
#attendanceTable_wrapper{
margin-top :-6%;
}
input[type=search] {
    /* -webkit-appearance: none; */
    border: solid;
}
.table-bordered thead tr th {
    font-size: 14px !important;
    color: rgb(255, 255, 255) !important;
    vertical-align: middle !important;
    background: rgb(1, 140, 128) !important;
}
#wellnessCentreList td {
    border: 1px solid #000 !important;
    border-collapse: collapse !important;
}
</style>

<script type="text/javascript">
var $ = jQuery.noConflict();

$(document).ready(function() {
	$('#wellnessCentreList').DataTable({
		dom : 'lBfrtip',
		buttons : [ {
			extend : 'excelHtml5',
			text : 'Excel',
			title : 'Wellness Center Attendance Report',
		    className: 'small-excel-button'
		} ],
		paging: false,      
        searching: false,   
        info: false,        
        ordering: false
	});
	
});




	$(document).ready(function () {

	    // Disable typing + autocomplete
	    $("#fromDate")
	        .attr("autocomplete", "off")
	        .attr("readonly", "readonly");

	    // Advance Search button  show date section
	    $("#advSearchBtn").click(function () {
	        $("#searchSection").slideDown();
	        
	    });

	    // FROM DATE PICKER
	    $('#fromDate').datepicker({
	        format: 'dd/mm/yyyy',
	        autoclose: true,
	        todayHighlight: true,
	        endDate: new Date() // cannot choose future date
	    });

	});

	function fn_backToMainPage(){
		parent.fn_loadImage();
		document.forms[0].action = "patientDetailsNew.do?actionFlag=getWellnessCentreList"; 
		document.forms[0].method = "post";
		document.forms[0].submit();
	}
	function fn_ResetBtn() {

	    parent.fn_loadImage();

	    /* ===== DESTROY SEARCH RESULT DATATABLE ===== */
	    if ($.fn.DataTable.isDataTable('#attendanceTable')) {
	        $('#attendanceTable').DataTable().clear().destroy();
	    }

	    /* ===== REMOVE TABLE + TITLE COMPLETELY ===== */
	    $('#tableContainer').empty();

	    /* ===== HIDE NO DATA MESSAGE ===== */
	    $('#noData').hide();

	    /* ===== CLEAR DATE FIELDS ===== */
	    $('#fromDate').val('');

	    /* ===== SHOW ADVANCE SEARCH BUTTON AGAIN ===== */
	    $('#advSearchBtnDiv').show();

	    /* ===== KEEP SEARCH SECTION VISIBLE ===== */
	    $('#searchSection').show();

	    parent.fn_removeLoadingImage();
	}
	function fn_back(){
		parent.fn_loadImage();
		document.forms[0].action = "patientDetailsNew.do?actionFlag=getWellnessCentreList"; 
		document.forms[0].method = "post";
		document.forms[0].submit();
	} 
	function fn_OpenWcDetails(wfType) {
		document.getElementById("advSearchBtn").style.display = 'none';
	    /* ================= HEADER STYLE ================= */
	    if (!document.getElementById("greenHeaderStyle")) {
	        $('head').append(
	            '<style id="greenHeaderStyle">' +
	            '#attendanceTable thead th {' +
	                'background-color: #108A4D !important;' +
	                'color: white !important;' +
	                'font-weight: bold;' +
	                'text-align: left;' +
	            '}' +
	            '</style>'
	        );
	    }
	    /* ================= BORDER STYLE ================= */
	    if (!document.getElementById("attendanceTableBorderStyle")) {
	        $('head').append(
	            '<style id="attendanceTableBorderStyle">' +
	            '#attendanceTable > tbody > tr > td,' +
	            '#attendanceTable > tbody > tr > th,' +
	            '#attendanceTable > tfoot > tr > td,' +
	            '#attendanceTable > tfoot > tr > th,' +
	            '#attendanceTable > thead > tr > td,' +
	            '#attendanceTable > thead > tr > th {' +
	                'border-top: none !important;' +
	                'border: 1px solid #000 !important;' +
	                'font-size: 14px !important;' +
	            '}' +
	            '</style>'
	        );
	    }
	    /* ================================================= */

	    parent.fn_loadImage();

	    $.ajax({
	        url: 'patientDetailsNew.do?actionFlag=EmployeeWiseWellnessDtls',
	        type: 'GET',
	        data: {
	            wfType: wfType
	        },

	        success: function (data) {

	            var parsedData = data.trim().replace(/[\*\s]+$/, "");
	            var result = JSON.parse(parsedData);

	            $('#noData').hide();
	            $('#tableContainer').empty();
				 $('#tableContainer').html(
					   	  
							
					        '<div class="col-md-9 col-sm-9 col-lg-9 col-xs-12">' +
					            '<h3 class="text-center" style="color: green; margin-top:-1%;margin-left:-23%">' +
					                'Employee Wise Wellness Center List' +
					            '</h3>' +
					        '</div>' +
					        '<div class="col-md-3 col-sm-3 col-lg-3 col-xs-12 text-right"  style="top: -8px;">' +
					            '<button type="button" class="btn btn-round btn-primary" ' +
					                'onclick="fn_backToMainPage();" ' +
					                'id="backBtn" style="margin-top: 8px;padding-right: -22%;margin-right: -301%;padding: 6px 16px;">' +
					                'Back' +
					            '</button>' +
					        '</div>' 
				
					    
					);
	            /* ===== TABLE ===== */
	            var tableHtml =
	                '<table id="attendanceTable" ' +
	                'class="table table-condensed col-lg-12 col-md-12 col-sm-12 col-xs-12" ' +
	                'style="margin-top:1%; width:100%; border-collapse:collapse;">' +
	                    '<thead>' +
	                        '<tr>' +
	                            '<th>S NO</th>' +
	                            '<th>Wellness Center Name</th>' +
	                            '<th>Employee Name</th>' +
	                            '<th>Role</th>' +
	                            '<th>Check-In Time</th>' +
	                            '<th>Check-Out Time</th>' +
	                            '<th>Working Hours</th>' +
	                            '<th>In-Premises Status</th>' +
	                            '<th>Outside-Premises Status</th>' +
	                            '<th>Check-In Address</th>' +
	                            '<th>Check-Out Address</th>' +
	                        '</tr>' +
	                    '</thead>' +
	                    '<tbody id="allRows"></tbody>' +
	                '</table>';

	            $('#tableContainer').append(tableHtml);

	            /* ===== ROWS ===== */
	            $.each(result, function (index, item) {

	                var row =
	                    '<tr>' +
	                        '<td class="text-center">' + (index + 1) + '</td>' +
	                        '<td>' + (item.PENIND || '') + '</td>' +
	                        '<td>' + (item.NAME || '') + '</td>' +
	                        '<td>' + (item.COMPROLE || '') + '</td>' +
	                        '<td>' + (item.EXP_DATE || '') + '</td>' +
	                        '<td>' + (item.OUT_DATE || '') + '</td>' +
	                        '<td>' + (item.POQTY || '') + '</td>' +
	                        '<td>' + (item.BTNO || '') + '</td>' +
	                        '<td>' + (item.INVNO || '') + '</td>' +
	                        '<td>' + (item.ADDRESS || '') + '</td>' +
	                        '<td>' + (item.WCNAME || '') + '</td>' +
	                    '</tr>';

	                $('#allRows').append(row);
	            });

	            parent.fn_removeLoadingImage();

	            /* ===== DATATABLE ===== */
	            $('#attendanceTable').DataTable({
	                paging: true,
	                searching: true,
	                ordering: true,
	                info: false,
	                order: [],
	                dom: '<"top-section"lfB>rtip',
	                buttons: [{
	                    extend: 'excelHtml5',
	                    text: 'Excel',
	                    title: 'Employee Wise Wellness Center List',
	                    className: 'excel-button'
	                }],
	                columnDefs: [
	                    { targets: 0, orderable: false }
	                ]
	            }).on('order.dt search.dt', function () {

	                let table = $('#attendanceTable').DataTable();
	                table.column(0, { search: 'applied', order: 'applied' })
	                    .nodes()
	                    .each(function (cell, i) {
	                        cell.innerHTML = i + 1;
	                    });

	            }).draw();
	        },

	        error: function (xhr, status, error) {
	            console.error('Error fetching data:', error);
	            parent.fn_removeLoadingImage();
	        }
	    });
	}

	function fn_CheckInDetails(wfType) {
		document.getElementById("advSearchBtn").style.display = 'none';
	    /* ================= HEADER STYLE ================= */
	    if (!document.getElementById("greenHeaderStyle")) {
	        $('head').append(
	            '<style id="greenHeaderStyle">' +
	            '#attendanceTable thead th {' +
	                'background-color: #108A4D !important;' +
	                'color: white !important;' +
	                'font-weight: bold;' +
	                'text-align: left;' +
	            '}' +
	            '</style>'
	        );
	    }

	    /* ================= BORDER STYLE ================= */
	    if (!document.getElementById("attendanceTableBorderStyle")) {
	        $('head').append(
	            '<style id="attendanceTableBorderStyle">' +
	            '#attendanceTable > tbody > tr > td,' +
	            '#attendanceTable > tbody > tr > th,' +
	            '#attendanceTable > tfoot > tr > td,' +
	            '#attendanceTable > tfoot > tr > th,' +
	            '#attendanceTable > thead > tr > td,' +
	            '#attendanceTable > thead > tr > th {' +
	                'border-top: none !important;' +
	                'border: 1px solid #000 !important;' +
	            '}' +
	            '</style>'
	        );
	    }
	    /* ================================================= */

	    parent.fn_loadImage();

	    $.ajax({
	        url: 'patientDetailsNew.do?actionFlag=EmployeeWiseWellnessDtls',
	        type: 'GET',
	        data: {
	            wfType: wfType,
	            reqType: 'checkIn'
	        },

	        success: function (data) {

	            var parsedData = data.trim().replace(/[\*\s]+$/, "");
	            var result = JSON.parse(parsedData);

	            $('#noData').hide();
	            $('#tableContainer').empty();
				 $('#tableContainer').html(
				   	  
				
					        '<div class="col-md-9 col-sm-9 col-lg-9 col-xs-12">' +
					            '<h3 class="text-center" style="color: green; margin-top:-1%;margin-left:-23%">' +
					                'Check In Wise Wellness Center List' +
					            '</h3>' +
					        '</div>' +
				
					        '<div class="col-md-3 col-sm-3 col-lg-3 col-xs-12 text-right" style="top: -8px;">' +
					            '<button type="button" class="btn btn-round btn-primary" ' +
					                'onclick="fn_backToMainPage();" ' +
					                'id="backBtn" style="margin-top: 8px;padding-right: -22%;padding: 6px 16px;margin-right: -301%;">' +
					                'Back' +
					            '</button>' +
					        '</div>' 
				
					    
					);
	            /* ===== TABLE ===== */
	            var tableHtml =
	                '<table id="attendanceTable" ' +
	                'class="table table-condensed col-lg-12 col-md-12 col-sm-12 col-xs-12" ' +
	                'style="margin-top:1%; width:100%; border-collapse:collapse;">' +
	                    '<thead>' +
	                        '<tr>' +
	                            '<th>S NO</th>' +
	                            '<th>Employee Name</th>'+
	                            '<th>Wellness Center Name</th>' +
	                            '<th>Role</th>' +
	                            '<th>Check-In Time</th>' +
	                            '<th>Check-Out Time</th>' +
	                            '<th>Check-In Address</th>' +
	                            '<th>In-Premises Status</th>' +
	                        '</tr>' +
	                    '</thead>' +
	                    '<tbody id="allRows"></tbody>' +
	                '</table>';

	            $('#tableContainer').append(tableHtml);

	            /* ===== ROWS ===== */
	            $.each(result, function (index, item) {
	                var row =
	                    '<tr>' +
	                        '<td class="text-center">' + (index + 1) + '</td>' +
	                        '<td>' + (item.NAME || '') + '</td>' +
	                        '<td>' + (item.PENIND || '') + '</td>' +
	                        '<td>' + (item.COMPROLE || '') + '</td>' +
	                        '<td>' + (item.EXP_DATE || '') + '</td>' +
	                        '<td>' + (item.OUT_DATE || '') + '</td>' +
	                        '<td>' + (item.ADDRESS || '') + '</td>' +
	                        '<td>' + (item.LEVELID || '') + '</td>' +
	                    '</tr>';

	                $('#allRows').append(row);
	            });

	            parent.fn_removeLoadingImage();

	            /* ===== DATATABLE ===== */
	            $('#attendanceTable').DataTable({
	                paging: true,
	                searching: true,
	                ordering: true,
	                info: false,
	                order: [],
	                dom: '<"top-section"lfB>rtip',
	                buttons: [{
	                    extend: 'excelHtml5',
	                    text: 'Excel',
	                    title: 'Check In Wise Wellness Center List',
	                    className: 'excel-button'
	                }],
	                columnDefs: [
	                    { targets: 0, orderable: false }
	                ]
	            }).on('order.dt search.dt', function () {

	                let table = $('#attendanceTable').DataTable();
	                table.column(0, { search: 'applied', order: 'applied' })
	                    .nodes()
	                    .each(function (cell, i) {
	                        cell.innerHTML = i + 1;
	                    });

	            }).draw();
	        },

	        error: function (xhr, status, error) {
	            console.error('Error fetching data:', error);
	            parent.fn_removeLoadingImage();
	        }
	    });
	}
	function fn_CheckOutDetails(wfType) {
		document.getElementById("advSearchBtn").style.display = 'none';
	    /* ================= HEADER STYLE ================= */
	    if (!document.getElementById("greenHeaderStyle")) {
	        $('head').append(
	            '<style id="greenHeaderStyle">' +
	            '#attendanceTable thead th {' +
	                'background-color: #108A4D !important;' +
	                'color: white !important;' +
	                'font-weight: bold;' +
	                'text-align: left;' +
	            '}' +
	            '</style>'
	        );
	    }

	    /* ================= BORDER STYLE ================= */
	    if (!document.getElementById("attendanceTableBorderStyle")) {
	        $('head').append(
	            '<style id="attendanceTableBorderStyle">' +
	            '#attendanceTable > tbody > tr > td,' +
	            '#attendanceTable > tbody > tr > th,' +
	            '#attendanceTable > tfoot > tr > td,' +
	            '#attendanceTable > tfoot > tr > th,' +
	            '#attendanceTable > thead > tr > td,' +
	            '#attendanceTable > thead > tr > th {' +
	                'border-top: none !important;' +
	                'border: 1px solid #000 !important;' +
	            '}' +
	            '</style>'
	        );
	    }
	    /* ================================================= */

	    parent.fn_loadImage();

	    $.ajax({
	        url: 'patientDetailsNew.do?actionFlag=EmployeeWiseWellnessDtls',
	        type: 'GET',
	        data: {
	            wfType: wfType,
	            reqType: 'checkout'
	        },

	        success: function (data) {

	            var parsedData = data.trim().replace(/[\*\s]+$/, "");
	            var result = JSON.parse(parsedData);

	            $('#noData').hide();
	            $('#tableContainer').empty();
				 $('#tableContainer').html(
				   	  
				
					        '<div class="col-md-9 col-sm-9 col-lg-9 col-xs-12">' +
					            '<h3 class="text-center" style="color: green; margin-top:-1%;margin-left:-23%">' +
					                'Check Out Wise Wellness Center List' +
					            '</h3>' +
					        '</div>' +
				
					        '<div class="col-md-3 col-sm-3 col-lg-3 col-xs-12 text-right" style="top: -8px;">' +
					            '<button type="button" class="btn btn-round btn-primary" ' +
					                'onclick="fn_backToMainPage();" ' +
					                'id="backBtn" style="margin-top: 8px;padding-right: -22%;padding: 6px 16px;margin-right: -301%;">' +
					                'Back' +
					            '</button>' +
					        '</div>' 
				
					    
					);

	            /* ===== TABLE ===== */
	            var tableHtml =
	                '<table id="attendanceTable" ' +
	                'class="table table-condensed col-lg-12 col-md-12 col-sm-12 col-xs-12" ' +
	                'style="margin-top:1%; width:100%; border-collapse:collapse;">' +
	                    '<thead>' +
	                        '<tr>' +
	                        '<th>S NO</th>' +
	                        '<th>Employee Name</th>'+
                            '<th>Wellness Center Name</th>' +
                            '<th>Role</th>' +
                            '<th>Check-In Time</th>' +
                            '<th>Check-Out Time</th>' +
                            '<th>Check-Out Address</th>' +
                            '<th>Out-Premises Status</th>' +
	                        '</tr>' +
	                    '</thead>' +
	                    '<tbody id="allRows"></tbody>' +
	                '</table>';

	            $('#tableContainer').append(tableHtml);

	            /* ===== ROWS ===== */
	            $.each(result, function (index, item) {
	                var row =
	                	'<tr>' +
	                        '<td class="text-center">' + (index + 1) + '</td>' +
	                        '<td>' + (item.NAME || '') + '</td>' +
	                        '<td>' + (item.PENIND || '') + '</td>' +
	                        '<td>' + (item.COMPROLE || '') + '</td>' +
	                        '<td>' + (item.EXP_DATE || '') + '</td>' +
	                        '<td>' + (item.OUT_DATE || '') + '</td>' +
	                        '<td>' + (item.ADDRESS || '') + '</td>' +
	                        '<td>' + (item.LEVELID || '') + '</td>' +
                       '</tr>';


	                $('#allRows').append(row);
	            });

	            parent.fn_removeLoadingImage();

	            /* ===== DATATABLE ===== */
	            $('#attendanceTable').DataTable({
	                paging: true,
	                searching: true,
	                ordering: true,
	                info: false,
	                order: [],
	                dom: '<"top-section"lfB>rtip',
	                buttons: [{
	                    extend: 'excelHtml5',
	                    text: 'Excel',
	                    title: 'Check Out Wise Wellness Center List',
	                    className: 'excel-button'
	                }],
	                columnDefs: [
	                    { targets: 0, orderable: false }
	                ]
	            }).on('order.dt search.dt', function () {

	                let table = $('#attendanceTable').DataTable();
	                table.column(0, { search: 'applied', order: 'applied' })
	                    .nodes()
	                    .each(function (cell, i) {
	                        cell.innerHTML = i + 1;
	                    });

	            }).draw();
	        },

	        error: function (xhr, status, error) {
	            console.error('Error fetching data:', error);
	            parent.fn_removeLoadingImage();
	        }
	    });
	}

	function fn_SearchWiseCount() {

	    document.getElementById("advSearchBtn").style.display = 'none';

	    var fromDate = $('#fromDate').val();
	    if (!fromDate) {
	        alert("Please Enter the From Date");
	        return;
	    }

	    /* ================= HEADER STYLE ================= */
	    if (!document.getElementById("greenHeaderStyle")) {
	        $('head').append(
	            '<style id="greenHeaderStyle">' +
	            '#attendanceTable thead th {' +
	                'background-color: #108A4D !important;' +
	                'color: white !important;' +
	                'font-weight: bold;' +
	                'text-align: left;' +
	            '}' +
	            '</style>'
	        );
	    }

	    /* ================= BORDER STYLE ================= */
	    if (!document.getElementById("attendanceTableBorderStyle")) {
	        $('head').append(
	            '<style id="attendanceTableBorderStyle">' +
	            '#attendanceTable th, #attendanceTable td {' +
	                'border: 1px solid #000 !important;' +
	            '}' +
	            '</style>'
	        );
	    }

	    parent.fn_loadImage();
	    $.ajax({
	        url: 'patientDetailsNew.do?actionFlag=SearchByWellnessCenter',
	        type: 'GET',
	        data: {
	            fromDate: fromDate,
	            reqType: 'search'
	        },
	        success: function (data) {

	            var parsedData = data.trim().replace(/[\*\s]+$/, "");
	            var result = JSON.parse(parsedData);

	            $('#noData').hide();
	            $('#tableContainer').empty();

	            /* ===== TITLE ===== */
	            $('#tableContainer').html(
	                '<h3 class="text-center" style="color: green; margin-top:13.5%;margin-left: -5%;">' +
	                    'Search Wise Wellness Center List' +
	                '</h3>'
	            );

	            /* ===== TABLE ===== */
	            var tableHtml =
	                '<table id="attendanceTable" class="table table-condensed" ' +
	                'style="width:100%; border-collapse:collapse; margin-top:1%;">' +
	                    '<thead>' +
	                        '<tr>' +
	                            '<th>S NO</th>' +
	                            '<th>Wellness Center Name</th>' +
	                            '<th>Total</th>' +
	                            '<th>Check In</th>' +
	                            '<th>Check In Percentage</th>' +
	                            '<th>Check Out</th>' +
	                            '<th>Chcek Out Percentage</th>' +
	                        '</tr>' +
	                    '</thead>' +
	                    '<tbody id="allRows"></tbody>' +
	                '</table>';

	            $('#tableContainer').append(tableHtml);

	            /* ===== ROWS ===== */
	            $.each(result, function (index, item) {

	                var row =
	                    '<tr>' +
	                        '<td class="text-center">' + (index + 1) + '</td>' +
	                        '<td style="cursor:pointer;color:blue;text-decoration:underline;" ' +
	                            'onclick="fn_SearchWCDetails(\'' + item.WFTYPE + '\')">' +
	                            (item.PENIND) +
	                        '</td>' +
	                        '<td>' + (item.DIFF) + '</td>' +
	                        '<td style="cursor:pointer;color:blue;text-decoration:underline;" ' +
	                            'onclick="fn_SearchCheckIn(\'' + item.WFTYPE + '\')">' +
	                            (item.SNO) +
	                        '</td>' +
	                        '<td>' + (item.LVL) + '</td>' +
	                        '<td style="cursor:pointer;color:blue;text-decoration:underline;" ' +
	                            'onclick="fn_SearchCheckOut(\'' + item.WFTYPE + '\')">' +
	                            (item.QTY) +
	                        '</td>' +
	                        '<td>' + (item.INVNO) + '</td>' +
	                    '</tr>';

	                $('#allRows').append(row);
	            });

	            /* ===== DESTROY OLD DATATABLE (IMPORTANT) ===== */
	            if ($.fn.DataTable.isDataTable('#attendanceTable')) {
	                $('#attendanceTable').DataTable().destroy();
	            }

	            /* ===== DATATABLE (SEARCH + EXCEL ONLY) ===== */
	            $('#attendanceTable').DataTable({
	                destroy: true,
	                paging: false,        //  pagination OFF
	                searching: true,      //  search ON
	                ordering: true,
	                info: false,          //  info OFF
	                lengthChange: false,  // entries OFF
	                order: [],
	                dom: '<"top-section"fB>rt',   // ONLY search + Excel
	                buttons: [{
	                    extend: 'excelHtml5',
	                    text: 'Excel',
	                    title: 'Search Wise Wellness Center List',
	                    className: 'excelsmall-button'
	                }],
	                columnDefs: [
	                    { targets: 0, orderable: false }
	                ]
	            }).on('order.dt search.dt', function () {

	                let table = $('#attendanceTable').DataTable();
	                table.column(0, { search: 'applied', order: 'applied' })
	                    .nodes()
	                    .each(function (cell, i) {
	                        cell.innerHTML = i + 1;
	                    });

	            }).draw();

	            parent.fn_removeLoadingImage();
	        },

	        error: function () {
	            parent.fn_removeLoadingImage();
	        }
	    });
	}
function fn_SearchWCDetails(wfType,fromDate) {
	var fromDate = $('#fromDate').val();
	document.getElementById("advSearchBtn").style.display = 'none';
    /* ================= HEADER STYLE ================= */
    if (!document.getElementById("greenHeaderStyle")) {
        $('head').append(
            '<style id="greenHeaderStyle">' +
            '#attendanceTable thead th {' +
                'background-color: #108A4D !important;' +
                'color: white !important;' +
                'font-weight: bold;' +
                'text-align: left;' +
            '}' +
            '</style>'
        );
    }

    /* ================= BORDER STYLE ================= */
    if (!document.getElementById("attendanceTableBorderStyle")) {
        $('head').append(
            '<style id="attendanceTableBorderStyle">' +
            '#attendanceTable > tbody > tr > td,' +
            '#attendanceTable > tbody > tr > th,' +
            '#attendanceTable > tfoot > tr > td,' +
            '#attendanceTable > tfoot > tr > th,' +
            '#attendanceTable > thead > tr > td,' +
            '#attendanceTable > thead > tr > th {' +
                'border-top: none !important;' +
                'border: 1px solid #000 !important;' +
                'font-size: 14px !important;' +
            '}' +
            '</style>'
        );
    }
    /* ================================================= */

    parent.fn_loadImage();
    $.ajax({
        url: 'patientDetailsNew.do?actionFlag=SearchByWellnessCenter',
        type: 'GET',
        data: {
            wfType: wfType,
            fromDate:fromDate,
            reqType: 'Total',
        },
        success: function (data) {

            var parsedData = data.trim().replace(/[\*\s]+$/, "");
            var result = JSON.parse(parsedData);

            $('#noData').hide();
            $('#tableContainer').empty();

            /* ===== TITLE + BACK BUTTON ===== */
            $('#tableContainer').html(
                '<h3 class="text-center" style="color: green; margin-top:13.5%;margin-left: -7%;">' +
                    'Search Employee Wise Wellness Center List' +
                '</h3>' +
         
           	    '<div style="text-align:center; margin-top:10px;margin-left: 79%;">' +
           	        '<button type="button" onclick="fn_SearchWiseCount();" ' +
           	            'style="background:#428bca; color:white; padding:6px 16px;' +
           	            'border:none; border-radius:4px; font-weight:bold; cursor:pointer;">' +
           	            'Back' +
           	        '</button>' +
           	    '</div>'
                	

            );
            /* ===== TABLE ===== */
            var tableHtml =
                '<table id="attendanceTable" ' +
                'class="table table-condensed col-lg-12 col-md-12 col-sm-12 col-xs-12" ' +
                'style="margin-top:1%; width:100%; border-collapse:collapse;">' +
                    '<thead>' +
                        '<tr>' +
                            '<th>S NO</th>' +
                            '<th>Wellness Center Name</th>' +
                            '<th>Employee Name</th>' +
                            '<th>Role</th>' +
                            '<th>Check-In Time</th>' +
                            '<th>Check-Out Time</th>' +
                            '<th>Working Hours</th>' +
                            '<th>In-Premises Status</th>' +
                            '<th>Outside-Premises Status</th>' +
                            '<th>Check-In Address</th>' +
                            '<th>Check-Out Address</th>' +
                        '</tr>' +
                    '</thead>' +
                    '<tbody id="allRows"></tbody>' +
                '</table>';

            $('#tableContainer').append(tableHtml);

            /* ===== ROWS ===== */
            $.each(result, function (index, item) {

                var row =
                    '<tr>' +
                        '<td class="text-center">' + (index + 1) + '</td>' +
                        '<td>' + (item.PENIND || '') + '</td>' +
                        '<td>' + (item.NAME || '') + '</td>' +
                        '<td>' + (item.COMPROLE || '') + '</td>' +
                        '<td>' + (item.EXP_DATE || '') + '</td>' +
                        '<td>' + (item.OUT_DATE || '') + '</td>' +
                        '<td>' + (item.POQTY || '') + '</td>' +
                        '<td>' + (item.BTNO || '') + '</td>' +
                        '<td>' + (item.INVNO || '') + '</td>' +
                        '<td>' + (item.ADDRESS || '') + '</td>' +
                        '<td>' + (item.WCNAME || '') + '</td>' +
                    '</tr>';

                $('#allRows').append(row);
            });

            parent.fn_removeLoadingImage();

            /* ===== DATATABLE ===== */
            $('#attendanceTable').DataTable({
                paging: true,
                searching: true,
                ordering: true,
                info: false,
                order: [],
                dom: '<"top-section"lfB>rtip',
                buttons: [{
                    extend: 'excelHtml5',
                    text: 'Excel',
                    title: 'Search Employee Wise Wellness Center List',
                    className: 'excel-button'
                }],
                columnDefs: [
                    { targets: 0, orderable: false }
                ]
            }).on('order.dt search.dt', function () {

                let table = $('#attendanceTable').DataTable();
                table.column(0, { search: 'applied', order: 'applied' })
                    .nodes()
                    .each(function (cell, i) {
                        cell.innerHTML = i + 1;
                    });

            }).draw();
        },

        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            parent.fn_removeLoadingImage();
        }
    });
}


function fn_SearchCheckIn(wfType) {
	var fromDate = $('#fromDate').val();
	document.getElementById("advSearchBtn").style.display = 'none';
    /* ================= HEADER STYLE ================= */
    if (!document.getElementById("greenHeaderStyle")) {
        $('head').append(
            '<style id="greenHeaderStyle">' +
            '#attendanceTable thead th {' +
                'background-color: #108A4D !important;' +
                'color: white !important;' +
                'font-weight: bold;' +
                'text-align: left;' +
            '}' +
            '</style>'
        );
    }

    /* ================= BORDER STYLE ================= */
    if (!document.getElementById("attendanceTableBorderStyle")) {
        $('head').append(
            '<style id="attendanceTableBorderStyle">' +
            '#attendanceTable > tbody > tr > td,' +
            '#attendanceTable > tbody > tr > th,' +
            '#attendanceTable > tfoot > tr > td,' +
            '#attendanceTable > tfoot > tr > th,' +
            '#attendanceTable > thead > tr > td,' +
            '#attendanceTable > thead > tr > th {' +
                'border-top: none !important;' +
                'border: 1px solid #000 !important;' +
            '}' +
            '</style>'
        );
    }
    /* ================================================= */

    parent.fn_loadImage();

    $.ajax({
        url: 'patientDetailsNew.do?actionFlag=SearchByWellnessCenter',
        type: 'GET',
        data: {
            wfType: wfType,
            reqType: 'checkIn',
            fromDate:fromDate,
        },
        success: function (data) {

            var parsedData = data.trim().replace(/[\*\s]+$/, "");
            var result = JSON.parse(parsedData);

            $('#noData').hide();
            $('#tableContainer').empty();

            /* ===== TITLE + BACK BUTTON ===== */
            $('#tableContainer').html(
                '<h3 class="text-center" style="color: green; margin-top:13.5%;margin-left: -7%;">' +
                    'Search Check In Wise Wellness Center List' +
                '</h3>' +
         
           	    '<div style="text-align:center; margin-top:10px;margin-left: 79%;">' +
           	        '<button type="button" onclick="fn_SearchWiseCount();" ' +
           	            'style="background:#428bca; color:white; padding:6px 16px;' +
           	            'border:none; border-radius:4px; font-weight:bold; cursor:pointer;">' +
           	            'Back' +
           	        '</button>' +
           	    '</div>'
                	

            );
        
            /* ===== TABLE ===== */
            var tableHtml =
                '<table id="attendanceTable" ' +
                'class="table table-condensed col-lg-12 col-md-12 col-sm-12 col-xs-12" ' +
                'style="margin-top:1%; width:100%; border-collapse:collapse;">' +
                    '<thead>' +
                        '<tr>' +
                            '<th>S NO</th>' +
                            '<th>Employee Name</th>' +
                            '<th>Wellness Center Name</th>' +
                            '<th>Role</th>' +
                            '<th>Check-In Time</th>' +
                            '<th>Check-Out Time</th>' +
                            '<th>Check-In Address</th>' +
                            '<th>In-Premises Status</th>' +
                        '</tr>' +
                    '</thead>' +
                    '<tbody id="allRows"></tbody>' +
                '</table>';

            $('#tableContainer').append(tableHtml);

            /* ===== ROWS ===== */
            $.each(result, function (index, item) {
                var row =
                    '<tr>' +
                        '<td class="text-center">' + (index + 1) + '</td>' +
                        '<td>' + (item.NAME || '') + '</td>' +
                        '<td>' + (item.PENIND || '') + '</td>' +
                        '<td>' + (item.COMPROLE || '') + '</td>' +
                        '<td>' + (item.EXP_DATE || '') + '</td>' +
                        '<td>' + (item.OUT_DATE || '') + '</td>' +
                        '<td>' + (item.ADDRESS || '') + '</td>' +
                        '<td>' + (item.LEVELID || '') + '</td>' +
                    '</tr>';

                $('#allRows').append(row);
            });

            parent.fn_removeLoadingImage();

            /* ===== DATATABLE ===== */
            $('#attendanceTable').DataTable({
                paging: true,
                searching: true,
                ordering: true,
                info: false,
                order: [],
                dom: '<"top-section"lfB>rtip',
                buttons: [{
                    extend: 'excelHtml5',
                    text: 'Excel',
                    title: 'Search Check In Wise Wellness Center List',
                    className: 'excel-button'
                }],
                columnDefs: [
                    { targets: 0, orderable: false }
                ]
            }).on('order.dt search.dt', function () {

                let table = $('#attendanceTable').DataTable();
                table.column(0, { search: 'applied', order: 'applied' })
                    .nodes()
                    .each(function (cell, i) {
                        cell.innerHTML = i + 1;
                    });

            }).draw();
        },

        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            parent.fn_removeLoadingImage();
        }
    });
}

function fn_SearchCheckOut(wfType) {
	var fromDate = $('#fromDate').val();
	document.getElementById("advSearchBtn").style.display = 'none';
    /* ================= HEADER STYLE ================= */
    if (!document.getElementById("greenHeaderStyle")) {
        $('head').append(
            '<style id="greenHeaderStyle">' +
            '#attendanceTable thead th {' +
                'background-color: #108A4D !important;' +
                'color: white !important;' +
                'font-weight: bold;' +
                'text-align: left;' +
            '}' +
            '</style>'
        );
    }

    /* ================= BORDER STYLE ================= */
    if (!document.getElementById("attendanceTableBorderStyle")) {
        $('head').append(
            '<style id="attendanceTableBorderStyle">' +
            '#attendanceTable > tbody > tr > td,' +
            '#attendanceTable > tbody > tr > th,' +
            '#attendanceTable > tfoot > tr > td,' +
            '#attendanceTable > tfoot > tr > th,' +
            '#attendanceTable > thead > tr > td,' +
            '#attendanceTable > thead > tr > th {' +
                'border-top: none !important;' +
                'border: 1px solid #000 !important;' +
            '}' +
            '</style>'
        );
    }
    /* ================================================= */

    parent.fn_loadImage();

    $.ajax({
        url: 'patientDetailsNew.do?actionFlag=SearchByWellnessCenter',
        type: 'GET',
        data: {
            wfType: wfType,
            reqType: 'checkOut',
            fromDate:fromDate,
        },

        success: function (data) {

            var parsedData = data.trim().replace(/[\*\s]+$/, "");
            var result = JSON.parse(parsedData);

            $('#noData').hide();
            $('#tableContainer').empty();
            $('#tableContainer').html(
                    '<h3 class="text-center" style="color: green; margin-top:13.5%;margin-left: -7%;">' +
                        'Search Check Out Wise Wellness Center List' +
                    '</h3>' +
             
               	    '<div style="text-align:center; margin-top:10px;margin-left: 79%;">' +
               	        '<button type="button" onclick="fn_SearchWiseCount();" ' +
               	            'style="background:#428bca; color:white; padding:6px 16px;' +
               	            'border:none; border-radius:4px; font-weight:bold; cursor:pointer;">' +
               	            'Back' +
               	        '</button>' +
               	    '</div>'
                    	

                );
            /* ===== TABLE ===== */
           var tableHtml =
	                '<table id="attendanceTable" ' +
	                'class="table table-condensed col-lg-12 col-md-12 col-sm-12 col-xs-12" ' +
	                'style="margin-top:1%; width:100%; border-collapse:collapse;">' +
	                    '<thead>' +
	                    '<tr>' +
	                    '<th>S NO</th>' +
	                    '<th>Employee Name</th>' +
                            '<th>Wellness Center Name</th>' +
                            '<th>Role</th>' +
                            '<th>Check-In Time</th>' +
                            '<th>Check-Out Time</th>' +
                            '<th>Check-Out Address</th>' +
                            '<th>Out-Premises Status</th>' +
	                    '</tr>' +
	                    '</thead>' +
	                    '<tbody id="allRows"></tbody>' +
	                '</table>';

	            $('#tableContainer').append(tableHtml);

	            /* ===== ROWS ===== */
	            $.each(result, function (index, item) {
	                var row =
	                	'<tr>' +
	                        '<td class="text-center">' + (index + 1) + '</td>' +
	                        '<td>' + (item.NAME || '') + '</td>' +
	                        '<td>' + (item.PENIND || '') + '</td>' +
	                        '<td>' + (item.COMPROLE || '') + '</td>' +
	                        '<td>' + (item.EXP_DATE || '') + '</td>' +
	                        '<td>' + (item.OUT_DATE || '') + '</td>' +
	                        '<td>' + (item.ADDRESS || '') + '</td>' +
	                        '<td>' + (item.LEVELID || '') + '</td>' +
                       '</tr>';


	                $('#allRows').append(row);
	            });

            parent.fn_removeLoadingImage();

            /* ===== DATATABLE ===== */
            $('#attendanceTable').DataTable({
                paging: true,
                searching: true,
                ordering: true,
                info: false,
                order: [],
                dom: '<"top-section"lfB>rtip',
                buttons: [{
                    extend: 'excelHtml5',
                    text: 'Excel',
                    title: 'Search Check Out Wise Wellness Center List',
                    className: 'excel-button'
                }],
                columnDefs: [
                    { targets: 0, orderable: false }
                ]
            }).on('order.dt search.dt', function () {

                let table = $('#attendanceTable').DataTable();
                table.column(0, { search: 'applied', order: 'applied' })
                    .nodes()
                    .each(function (cell, i) {
                        cell.innerHTML = i + 1;
                    });

            }).draw();
        },

        error: function (xhr, status, error) {
            console.error('Error fetching data:', error);
            parent.fn_removeLoadingImage();
        }
    });
}

</script>



</head>

</head>
<body>
<form action="/patientDetailsNew.do" method="post">

<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 labelheading1 tbcellCss" style="margin-top:-5%">
<div colspan="8" class="tbheader"><b>WELLNESS CENTER ATTENDANCE REPORT </b>
</div>               
</div>

 <div class="row" style="margin-top: 5%;">
    <div class="col-md-3 col-sm-3 col-lg-3 col-xs-12" id="advSearchBtnDiv"> 
        <button type="button" class="btn btn-round btn-primary" onclick="fn_AdvanceSearch();"
            id="advSearchBtn" style="margin-left:3%; margin-top:-4%">
            Advance Search
        </button>
    </div>

 <div class="col-md-9 col-sm-9 col-lg-9 col-xs-12" id="searchSection" style="display:none; margin-top:-1%;">
        <label style="font-size:medium; padding-left:3%;"><strong>Date Search :</strong></label>
        <input type="text" name="fromDate" id="fromDate" placeholder=" (DD/MM/YYYY)">
        <!--  <input type="text" name="toDate" id="toDate" placeholder="To Date (DD/MM/YYYY)"> -->
        <button type="button" class="btn btn-round btn-green" id="searchBtn"
            onclick="fn_SearchWiseCount();" style="margin-left:1%">Search</button>
        <button type="button" class="btn btn-round btn-warning" id="resetBtn"
            onclick="fn_ResetBtn();" style="margin-left:1%">Reset</button>
        <button type="button" class="btn btn-round btn-primary" id="backBtn"
           onclick="fn_back();" style="margin-left:1%;">Back</button>
    </div>
</div>

<div id="tableContainer" style="margin-top:-12%;width:99.7%">				
    <table id="wellnessCentreList" class="tb" style="margin-top:3%;width:100%;border-collapse: collapse;">
        <thead>
            <tr>
                <th class="tbheader1" style="text-align: left;">S NO</th>
                <th class="tbheader1" style="text-align: left;">Wellness Center Name</th>
                <th class="tbheader1" style="text-align: left;">Count</th>
                <th class="tbheader1" style="text-align: left;">Check In</th>
                <th class="tbheader1" style="text-align: left;">Check In Percentage</th>
                <th class="tbheader1" style="text-align: left;">Check Out</th>
                <th class="tbheader1" style="text-align: left;">Check Out Percentage</th>
            </tr>				
        </thead>
        <tbody id="dashboardRows">
            <c:forEach var="dashboardDtls" items="${wellnessCentreList}" varStatus="status">
    			<tr>
    			    <td class="text-center">${status.index + 1}</td>
                    <td style="cursor:pointer; color:blue; text-decoration:underline;" onclick="fn_OpenWcDetails('${dashboardDtls.WFTYPE}')"> ${dashboardDtls.PENIND}</td>
                    <td>${dashboardDtls.DIFF}</td>
                    <td style="cursor:pointer; color:blue; text-decoration:underline;"
                    onclick="fn_CheckInDetails('${dashboardDtls.WFTYPE}')">
                    ${dashboardDtls.SNO}
                    </td>
                    <td>${dashboardDtls.LVL}</td>
                    <td style="cursor:pointer; color:blue; text-decoration:underline;"
                     onclick="fn_CheckOutDetails('${dashboardDtls.WFTYPE}')">
                    ${dashboardDtls.QTY}
                    </td>
                    <td>${dashboardDtls.INVNO}</td>
                </tr>
            </c:forEach>
            
        </tbody>
    </table>		
</div>

<div id="noData" class="col-lg-12 col-md-12 col-sm-12 col-xs-12 form-group" style="text-align:center; display:none">
    <h5><b>NO RECORDS FOUND</b></h5>
</div>
</form>
</body>
</html>
