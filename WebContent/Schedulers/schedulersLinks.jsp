<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/common/include.jsp"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedulers</title>
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
<script src="js/jquery-1.9.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<style> 
.btn-primary:hover{
background : #00315e !important;
}
body{font-size:1.3em !important;}
</style>
</head>
<body onload="javascript:fn_checkMsg();">
<form name="schedulersForm" action="/schedulersAction.do" method="POST">
<div class="tbheader" style="width:100%">
	<span><b>Operations Schedulers</b></span>
</div>
<div style="width:50%;float:left;">
<table width="100%">
				<tr>
					<td class="tbheader1" >
						S.No
					</td>
					<td class="tbheader1" >
						Scheduler Name
					</td>
					<td class="tbheader1" >
						Action		
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						1.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation YASHODA SECUNDERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenYASHSEC()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						2.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation YASHODA MALAKPET
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenYASHMPT()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						3.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation YASHODA SOMAJIGUDA
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenYASHSMG()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						4.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation APOLLO HYDERGUDA
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenAPPOLOHYDER()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						5.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation APOLLO JUBLIEEHILLS
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenAPPOLOJUBLIEE()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						6.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation APOLLO SECUNDERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenAPPOLOSEC()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						7.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation CARE NAMPALLY
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenCARENAM()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						8.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation CARE BANJARA
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenCAREBAN()">Run Scheduler</button>
					</td>
				</tr>
								<tr>
					<td class="tbcellBorder" align="center">
						9.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation CARE MUSHEERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenCAREMSB()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						10.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation PRIME HYDERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenPRIME()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						11.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation KIMS HYDERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenKIMSHYD()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						12.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation ASIAN HYD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenAINUHYD()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						13.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation STAR HOSPITAL HYDERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenSTARHYD()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						14.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation CONTINENTAL HOSPITAL
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenCONRRY()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						15.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation SUNSHINE HOSPITAL
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenSUNSEC()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						16.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation MAXCURE HOSPITAL
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenMAXHYD()">Run Scheduler</button>
					</td>
				</tr>
							<tr>
					<td class="tbcellBorder" align="center">
						16.
					</td>
					<td class="tbcellBorder" align="center">
					MHC Scheduler
					</td>
					<td class="tbcellBorder" align="center">
					<button type="button" name="MHCScheduler" id="MHCScheduler" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runMHCScheduler()">Run Scheduler</button>
						
					</td>
				</tr>
	<!-- 						<tr>
					<td class="tbcellBorder" align="center">
						1.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation YASHODA SECUNDERABAD
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGen()">Run Scheduler</button>
					</td>
				</tr>
				
							<tr>
					<td class="tbcellBorder" align="center">
						1.
					</td>
					<td class="tbcellBorder" align="center">
					Lucene Drug generation others(including YASHSMG)
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="luceneDrug" id="luceneDrug" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_LucenDrugGenOthers()">Run Scheduler</button>
					</td>
				</tr>
				 -->
				
			</table>

</div>

<div style="width:50%;float:left;display: none">
			<table width="100%" >
				<tr>
					<td class="tbheader1" >
						S.No
					</td>
					<td class="tbheader1" >
						Scheduler Name
					</td>
					<td class="tbheader1" >
						Action		
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						1.
					</td>
					<td class="tbcellBorder" align="center">
						Claims Generate Bank File
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="ClaimsScheduler1" id="ClaimsScheduler1" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runClaimsScheduler1()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						2.
					</td>
					<td class="tbcellBorder" align="center">
						Erroneous claims Generate Bank File
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="ClaimsScheduler2" id="ClaimsScheduler2" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runClaimsScheduler2()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						3.
					</td>
					<td class="tbcellBorder" align="center">
						Followup claims Generate Bank File
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="ClaimsScheduler3" id="ClaimsScheduler3" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runClaimsScheduler3()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						4.
					</td>
					<td class="tbcellBorder" align="center">
						Update Claim Status Sent By Bank
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="ClaimsScheduler4" id="ClaimsScheduler4" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runClaimsScheduler4()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						5.
					</td>
					<td class="tbcellBorder" align="center">
						AHC Scheduler to Update the Enrollment Family Table
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="AHCScheduler1" id="AHCScheduler1" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runAHCScheduler1()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						6.
					</td>
					<td class="tbcellBorder" align="center">
						AHC Claims Generate Bank File
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="AHCScheduler2" id="AHCScheduler2" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runAHCScheduler2()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						7.
					</td>
					<td class="tbcellBorder" align="center">
						AHC Update Claim Status Sent By Bank
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="AHCScheduler3" id="AHCScheduler3" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runAHCScheduler3()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						8.
					</td>
					<td class="tbcellBorder" align="center">
						CHRONIC Claims Generate Bank File
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="chronicScheduler1" id="chronicScheduler1" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runCHRONICScheduler1()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						9.
					</td>
					<td class="tbcellBorder" align="center">
						CHRONIC Update Claim Status Sent By Bank
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="chronicScheduler2" id="chronicScheduler2" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runCHRONICScheduler2()">Run Scheduler</button>
					</td>
				</tr>
			</table>
	</div>
		<div style="width:50%;float:right;display: none" >
			<table width="100%" >
				<tr>
					<td class="tbheader1" >
						S.No
					</td>
					<td class="tbheader1" >
						Scheduler Name
					</td>
					<td class="tbheader1" >
						Action		
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						10.
					</td>
					<td class="tbcellBorder" align="center">
						Panel Doctor Payments Initialization
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="panelDocScheduler1" id="panelDocScheduler1" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runPnlDocScheduler1()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						11.
					</td>
					<td class="tbcellBorder" align="center">
						Panel Doctor Payments(Includes generating files and reading response files)
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="panelDocScheduler2" id="panelDocScheduler2" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runPnlDocScheduler2()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						12.
					</td>
					<td class="tbcellBorder" align="center">
						Flagging(Money Collection)
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="flaggingScheduler" id="flaggingScheduler" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runFlaggingScheduler()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						13.
					</td>
					<td class="tbcellBorder" align="center">
						Medical Audit-Fetch Dental Data
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="medicalAuditScheduler1" id="medicalAuditScheduler1" class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runMAScheduler1()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						14.
					</td>
					<td class="tbcellBorder" align="center">
						Medical Audit-Fetch all data Except Dental
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="medicalAuditScheduler2" id="medicalAuditScheduler2"  class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runMAScheduler2()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						15.
					</td>
					<td class="tbcellBorder" align="center">
						Medical Audit-Case Group
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="medicalAuditScheduler3" id="medicalAuditScheduler3"  class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runMAScheduler3()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						16.
					</td>
					<td class="tbcellBorder" align="center">
						Medical Audit-High Cost Cases
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="medicalAuditScheduler4" id="medicalAuditScheduler4"  class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runMAScheduler4()">Run Scheduler</button>
					</td>
				</tr>
					<tr>
					<td class="tbcellBorder" align="center">
						17.
					</td>
					<td class="tbcellBorder" align="center">
						Medical Audit-High Volume Cases
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="medicalAuditScheduler5" id="medicalAuditScheduler5"  class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runMAScheduler5()">Run Scheduler</button>
					</td>
				</tr>
				<tr>
					<td class="tbcellBorder" align="center">
						18.
					</td>
					<td class="tbcellBorder" align="center">
						Auto Cancel Cases Scheduler
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="autoCancelCaseScheduler" id="autoCancelCaseScheduler"  class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_runAutoCancelScheduler()">Run Scheduler</button>
					</td>
				</tr>
				
				<tr>
					<td class="tbcellBorder" align="center">
						19.
					</td>
					<td class="tbcellBorder" align="center">
						Journalist Claims Generate Bank File
					</td>
					<td class="tbcellBorder" align="center">
						<button type="button" name="jrnlstClaimsScheduler" id="jrnlstClaimsScheduler"  class="but btn btn-primary" title="Click to run Scheduler" onclick="javascript:fn_jrnlstClaimsScheduler()">Run Scheduler</button>
					</td>
				</tr>
				
			</table>
	</div>
<html:hidden name="schedulersForm" property="msg" styleId="msg"></html:hidden>
<script>
	function fn_checkMsg()
		{
			var msg=document.forms[0].msg.value;
			if(msg!=null && msg!='')
				alert(msg);
		}
	function fn_jrnlstClaimsScheduler()
	{
		
		document.getElementById("jrnlstClaimsScheduler").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=claimsScheduler&type=generateJrnlstBankFile";
		document.forms[0].method="post";
		document.forms[0].submit();
		
	}
	function fn_runClaimsScheduler1()
		{
			document.getElementById("ClaimsScheduler1").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=claimsScheduler&type=generateBankFile";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_LucenDrugGen()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenYASHSEC()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=YASHSEC";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenYASHMPT()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=YASHHYD";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenYASHSMG()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=YASHSMG";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenCARENAM()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=CAN";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenCAREBAN()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=CAB";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenCAREMSB()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=CAM";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenPRIME()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=PRIMEHYD";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenAINUHYD()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=AINUHYD";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenKIMSHYD()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=KIMSHYD";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenSTARHYD()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=STARHYD";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenMAXHYD()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=MAXHYD";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenCONRRY()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=CONRRY";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenSUNSEC()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=SNSHSEC";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenAPPOLOHYDER()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=APPH";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenAPPOLOSEC()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=APPS";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_LucenDrugGenAPPOLOJUBLIEE()
	{
		document.getElementById("luceneDrug").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=generateDrugLucene&type=generateDrugLucene&hospId=APPJ";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_runClaimsScheduler2()
		{
			document.getElementById("ClaimsScheduler2").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=claimsScheduler&type=generateErrBankFile";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runClaimsScheduler3()
		{
			document.getElementById("ClaimsScheduler3").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=claimsScheduler&type=generateFollowUpBankFile";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runClaimsScheduler4()
		{
			document.getElementById("ClaimsScheduler4").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=claimsScheduler&type=readClaimsBankFile";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runAHCScheduler1()
		{
			document.getElementById("AHCScheduler1").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=ahcScheduler&type=updateEnrollmentFamily";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runMHCScheduler()
	{
		document.getElementById("MHCScheduler").disabled="true";
		document.forms[0].action="schedulersAction.do?actionFlag=mhcScheduler&type=updateEnrollmentFamily";
		document.forms[0].method="post";
		document.forms[0].submit();
	}
	function fn_runAHCScheduler2()
		{
			document.getElementById("AHCScheduler2").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=ahcScheduler&type=generateAHcFile";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runAHCScheduler3()
		{
			document.getElementById("AHCScheduler3").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=ahcScheduler&type=AHCUpdateClaimStatusSentByBank";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runCHRONICScheduler1()
		{
			document.getElementById("chronicScheduler1").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=chronicScheduler&type=generateChronicFile";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runCHRONICScheduler2()
		{
			document.getElementById("chronicScheduler2").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=chronicScheduler&type=chronicClaimSentStatusUpdating";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runPnlDocScheduler1()
		{
			document.getElementById("panelDocScheduler1").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=panelDocScheduler&type=panelDocPmtsInit";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runPnlDocScheduler2()
		{
			document.getElementById("panelDocScheduler2").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=panelDocScheduler&type=panelDocPmts";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runFlaggingScheduler()
		{
			document.getElementById("flaggingScheduler").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=flaggingScheduler&type=flaggingMoneyCollection";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runMAScheduler1()
		{
			document.getElementById("medicalAuditScheduler1").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=medicalAuditScheduler&type=fetchDataDental";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runMAScheduler2()
		{
			document.getElementById("medicalAuditScheduler2").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=medicalAuditScheduler&type=fetchData";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runMAScheduler3()
		{
			document.getElementById("medicalAuditScheduler3").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=medicalAuditScheduler&type=findCaseGroup";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runMAScheduler4()
		{
			document.getElementById("medicalAuditScheduler4").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=medicalAuditScheduler&type=findHighCostCase";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runMAScheduler5()
		{
			document.getElementById("medicalAuditScheduler5").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=medicalAuditScheduler&type=findHighVolumeCase";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	function fn_runAutoCancelScheduler()
		{
			document.getElementById("autoCancelCaseScheduler").disabled="true";
			document.forms[0].action="schedulersAction.do?actionFlag=autoCancelCasesScheduler&type=autoCancel";
			document.forms[0].method="post";
			document.forms[0].submit();
		}
	
</script>
</form>
</body>
</html>