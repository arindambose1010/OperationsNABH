 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/tld/struts-html.tld" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/common/include.jsp"%>
<html>
<head>
<title>Preauth Request Form</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/themes/<%=themeColour%>/commonEhfCss.css" rel="stylesheet" type="text/css" media="screen">
<link href="css/print.css" rel="stylesheet" type="text/css" media="print">
<script src="js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script>
var s$ = jQuery.noConflict();
s$(document).ready(function(){
	//deciduos teeth retriving
//		var decDenSelArr=[];
	var carries='${PatientOpList.carriesdecidous}';
	var ipFlag='${ipFlag}';
	var critical1='${critical1}';
	var ipOtherRemarks1='${ipOtherRemarks1}';
	
	
	if(carries!=null && carries!="")
		{
//			decDenSelArr.push("CH87");
//			document.forms[0].deciduousDent.value="CH87";
//			document.getElementById("cariesDecdious").style.display="";
	var carriesdeciocus=carries.split('~');
	for(var i=0 ; i<carriesdeciocus.length;i++)
	{
		s$('input[type=checkbox][value='+carriesdeciocus[i]+']').prop('checked',true);
	}
		}
	
	var grosslydecade='${PatientOpList.grosslydecadedecidous}';
	if(grosslydecade!=null && grosslydecade!="")
		{
//			decDenSelArr.push("CH88");
//			document.forms[0].deciduousDent.value="CH88";
//			document.getElementById("grosslyDecdious").style.display="";
	var grosslydecadedecidous=grosslydecade.split('~');
	for(var i=0 ; i<grosslydecadedecidous.length;i++)
	{
		s$('input[type=checkbox][value='+grosslydecadedecidous[i]+']').prop('checked',true);
	}
		}
	
	var mobiled='${PatientOpList.mobiledecidous}';
	if(mobiled!=null && mobiled!="")
		{
//			decDenSelArr.push("CH89");
//			document.forms[0].deciduousDent.value="CH89";
//			document.getElementById("mobileDecdious").style.display="";
	var mobiledecidous=mobiled.split('~');
	for(var i=0 ; i<mobiledecidous.length;i++)
	{
		s$('input[type=checkbox][value='+mobiledecidous[i]+']').prop('checked',true);
	}
		}
	
	var missingdes='${PatientOpList.missingdecidous}';
	if(missingdes!=null && missingdes!="")
		{
//			decDenSelArr.push("CH90");
//			document.forms[0].deciduousDent.value="CH90";
//			document.getElementById("missingDecdious").style.display="";
	var missingdecidous=missingdes.split('~');
	for(var i=0 ; i<missingdecidous.length;i++)
	{
		s$('input[type=checkbox][value='+missingdecidous[i]+']').prop('checked',true);
	}
		}		
	// permanent  dentattion retreving		
//		var perDenSelArr=[];
	var carriesper='${PatientOpList.carriespermanent}';
	if(carriesper!=null && carriesper!="")
		{
//			perDenSelArr.push("CH96");
//			document.forms[0].permanentDent.value="CH96";
//			document.forms[0].permanentDent.value.selected=true;
//			document.getElementById("cariesDiv").style.display="";
	var carriespermanent=carriesper.split('~');
	for(var i=0 ; i<carriespermanent.length;i++)
	{
		s$('input[type=checkbox][value='+carriespermanent[i]+']').prop('checked',true);
	}
		}
	
	var rootstumpper='${PatientOpList.rootstumppermannet}';
	if(rootstumpper!=null && rootstumpper!="")
		{
//			perDenSelArr.push("CH92");
//			document.forms[0].permanentDent.value="CH92";
//			document.getElementById("rootDiv").style.display="";
	var rootstumppermannet=rootstumpper.split('~');
	for(var i=0 ; i<rootstumppermannet.length;i++)
	{
		s$('input[type=checkbox][value='+rootstumppermannet[i]+']').prop('checked',true);
	}
		}
	
	var mobilityper='${PatientOpList.mobilitypermanent}';
	if(mobilityper!=null && mobilityper!="")
		{
//			perDenSelArr.push("CH93");
//			document.forms[0].permanentDent.value="CH93";
//			document.getElementById("mobilityDiv").style.display="";
	var mobilitypermanent=mobilityper.split('~');
	for(var i=0 ; i<mobilitypermanent.length;i++)
	{
		s$('input[type=checkbox][value='+mobilitypermanent[i]+']').prop('checked',true);
	}
		}
	
	var attritionper='${PatientOpList.attritionpermanent}';
	if(attritionper!=null && attritionper!="")
		{
//			perDenSelArr.push("CH94");
//			document.forms[0].permanentDent.value="CH94";
//			document.getElementById("attritionDiv").style.display="";
	var attritionpermanent=attritionper.split('~');
	for(var i=0 ; i<attritionpermanent.length;i++)
	{
		s$('input[type=checkbox][value='+attritionpermanent[i]+']').prop('checked',true);
	}
		}
	
	var missingper='${PatientOpList.missingpermanent}';
	if(missingper!=null && missingper!="")
		{
//			perDenSelArr.push("CH95");
//			document.forms[0].permanentDent.value="CH95";
//			document.getElementById("missingDiv").style.display="";
	var missingpermanent=missingper.split('~');
	for(var i=0 ; i<missingpermanent.length;i++)
	{
		s$('input[type=checkbox][value='+missingpermanent[i]+']').prop('checked',true);
	}
		}
	
	 //permanent other dentation 
	var otherper='${PatientOpList.otherpermanent}';
	if(otherper!=null && otherper!="" && otherper!="-1~")
		{
//			perDenSelArr.push("CH91");
		var otherpermanent=otherper.split('~');
		if(otherpermanent[0]!=null)
			{
//				document.forms[0].permanentDent.value="CH91";
//			document.getElementById("otherDiv").style.display="";
//			document.getElementById("otherPermTextDiv").style.display="";
		var otherVal="";
		if(otherpermanent[0]=="CH104"){
			otherVal="Non Vital";
		}
		if(otherpermanent[0]=="CH103"){
			otherVal="RCT Treated";
		}

		if(otherpermanent[0]=="CH102"){
			otherVal="Retained";
		}

		if(otherpermanent[0]=="CH105"){
			otherVal="Impacted";
		}
			document.getElementById("otherPermntDent").textContent=otherVal;
			document.getElementById("otherPermText").innerHTML=otherpermanent[1];
			}
			
		}
	
	var probingdepth='${PatientOpList.probingdepth}';
	if(probingdepth!=null && probingdepth!="")
		{
		var probingids=probingdepth.split('~');
		for(var i=0 ; i<probingids.length ; i++)
			{
			var probingdepthval=probingids[i].split('@');
			
			if(probingdepthval!=null && probingdepthval!="")
			document.getElementById(probingdepthval[0]).value=probingdepthval[1];
			}
		}
	
	
	s$('input[name=probeDepth]').prop('disabled','disabled');
	 s$('input[name=childCaries]').prop('disabled','disabled');
	 s$('input[name=childMissing]').prop('disabled','disabled');
	 s$('input[name=grosslyDecayed]').prop('disabled','disabled');
	 s$('input[name=childMobile]').prop('disabled','disabled');
	 s$('input[name=caries]').prop('disabled','disabled');
	 s$('input[name=decayed]').prop('disabled','disabled');
	 s$('input[name=mobile]').prop('disabled','disabled');
	 s$('input[name=attrition]').prop('disabled','disabled');
	 s$('input[name=missing]').prop('disabled','disabled');
	
});
function fn_Print()
{
	window.print();
}
</script>
</head>
<body>
	<form action="/preauthDetails.do" method="post" name="printPreauthForm">
		<table align="center" width="95%"  style="margin:0 auto;padding:5px;border:1px solid #f6f6f6;line-height:17px;" class="tb print_table">
			<!-- Title and address -->
			<tr>
				<td colspan="4">
					<logic:equal name="preauthDetailsForm" property="state" value="CD202">
						<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/PrintHeaderJouTG.html" %>
						</logic:equal>
						<logic:notEqual name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/Printheader_tg.html" %>
						</logic:notEqual>
					</logic:equal>
					
					<logic:notEqual name="preauthDetailsForm" property="state" value="CD202">
						
						<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/PrintHeaderJouAP.html" %>
						</logic:equal>
						<logic:notEqual name="preauthDetailsForm" property="patientScheme" value="CD502">
						<%@ include file="/common/Printheader.html" %>
						</logic:notEqual>
					</logic:notEqual>
				</td>
			</tr>
			<!-- end of title and address -->

			<tr>
				<td colspan="4" align="center"><FONT size="2" style="STRONG">
					<b>PRE-AUTHORISATION REQUEST FORM  ${lStrCurentDtTime}</b></FONT>
				</td>
			</tr>
			<tr><td colspan="4">&nbsp;</td></tr>
			<!-- part-1 -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-1(TO BE FILLED BY THE BENEFICIARY)</b></td>
			</tr>
			<tr>
				<td align="left" class="labelheading1 tbcellCss print_cell"><strong>Name of the patient</strong> </td>
				<td class="tbcellBorder print_cell">${PatientOpList.patientName}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Age</strong></td>
				<td class="tbcellBorder print_cell">${PatientOpList.age}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Gender</strong></td>
				<td class="tbcellBorder print_cell">
					<c:choose>
						<c:when test="${PatientOpList.gender eq 'M'}">
							Male
						</c:when>
						<c:when test="${PatientOpList.gender eq 'F'}">
							Female
						</c:when>
						<c:otherwise>
							${PatientOpList.gender}
						</c:otherwise>
					</c:choose>
				</td>
			
						<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
				<td class="labelheading1 tbcellCss print_cell"><strong>WJHS Health Card No</strong></td>
				        </logic:equal>
				        
				        <logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
				<td class="labelheading1 tbcellCss print_cell"><strong>
				
				<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
				
				
				
				Health Card No</strong></td>
				        </logic:equal>
				        
				<td class="tbcellBorder print_cell">${PatientOpList.rationCard}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>IP No</strong></td>
				<td class="tbcellBorder print_cell" >${PatientOpList.patientIPNo}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Case No</strong></td>
				<td class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="caseNo" /></td>
			</tr>
			<c:if test="${PatientOpList.relationName ne null}">
				<c:if test="${PatientOpList.relationName eq 'New Born Baby'}">
					<tr>
						<td class="labelheading1 tbcellCss print_cell"><strong>Relation</strong></td>
						<td class="tbcellBorder print_cell" ><b>${PatientOpList.relationName}</b></td>
					</tr>
				</c:if>
			</c:if>
			<!-- end of part1 -->
			<!-- postal address -->
			<tr>
				<td colspan="4"  class="tbheader print_heading" style="text-align:left"><b>POSTAL ADDRESS</b></td>
			</tr>
			<tr>
				<td  align="left" class="labelheading1 tbcellCss print_cell" width="25%"><strong>House No</strong></td>
				<td width="25%" class="tbcellBorder print_cell" >${PatientOpList.houseNo}</td>
				<td class="labelheading1 tbcellCss print_cell" width="25%"><strong>Street Name</strong></td>
				<td width="25%" class="tbcellBorder print_cell">${PatientOpList.street}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Village</strong></td>
				<td class="tbcellBorder print_cell" >${PatientOpList.village}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Mandal</strong></td>
				<td class="tbcellBorder print_cell">${PatientOpList.mandal}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>District</strong></td><td  class="tbcellBorder print_cell">${PatientOpList.district}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Pincode</strong></td><td  class="tbcellBorder print_cell">${PatientOpList.pincode}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Patient Tel.No</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.contactNo}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Mobile No</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.contactNo}</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Name of the referral PHC/Hospital</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.hospitalName}</td>
				<td class="labelheading1 tbcellCss print_cell"><strong>District</strong></td><td class="tbcellBorder print_cell" >${PatientOpList.hospDistrict}</td>
			</tr>
			<!-- end of postal address -->
			<!-- part II -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-II(TO BE FILLED BY THE HOSPITAL)-ALL COLUMNS ARE COMPULSARY</b></td>
			</tr>
			<tr>
				<td colspan="4" ><span class="labelheading1"><strong>1.Hospital Details</strong></span></td>
			</tr>
			<tr>
				<td  align="left" class="labelheading1 tbcellCss print_cell"><strong>Name of the Hospital/Nursing Home</strong></td>
				<td class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm"  property="hospitalName" /></td>
				<td class="labelheading1 tbcellCss print_cell"><strong>Tel No</strong></td>
				<td class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm"  property="hospContactNo" /></td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><strong>Address</strong></td>
				<td colspan="3" class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm"  property="hospAddress" /></td>
			</tr>
			
			<!--
			<tr>
				<td class="labelheading1 tbcellCss print_cell">Name of the Treating Doctor</td><td>
				<bean:write name="preauthDetailsForm"  property="docName" />
				</td>
				<td class="labelheading1 tbcellCss print_cell">Qualification</td><td>
				<bean:write name="preauthDetailsForm"  property="docQual" />
				</td>
			</tr>
			<tr>
				<!--To show Doctor Speciality -->
				<!--<td class="labelheading1 tbcellCss print_cell">Speciality</td>
				<td  class="tbcellBorder"><bean:write name="preauthDetailsForm"  property="docSpec" />
				</td>-->
				<!--
				<td class="labelheading1 tbcellCss print_cell">Reg No</td><td>
				<bean:write name="preauthDetailsForm"  property="docReg" />
				</td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell">Tel. No</td><td ></td>
				<td class="labelheading1 tbcellCss print_cell">Mobile No</td><td >
				<bean:write name="preauthDetailsForm"  property="docMobNo" />
				</td>
			</tr>

			<tr><td>&nbsp;<br/><br/></td></tr>
			<tr><td colspan="1" class="labelheading1 tbcellCss print_cell">Signature</td><td></td><td class="labelheading1 tbcellCss print_cell">Date</td><td>${lStrCurentDt}</td></tr>

			-->


			<tr>
				<td colspan="4" ><span class="labelheading1"><strong>2.Online Case Sheet</strong></span></td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell" ><strong>(i)History of present illness</strong></td>
				<td colspan="3" class="tbcellBorder print_cell">${PatientOpList.presentIllness}</td>
			</tr>
			<!-- end of part -II -->
			<!-- History of past illness -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left" ><b>History of Past Illness</b></td>
			</tr>
			<tr>
				<td colspan="4" class="tbcellBorder print_cell">
					<table width="100%">
						<tr>
							<td colspan="2">
								<c:set var="loopCount1" value="0" />
								<c:forEach  items="${pastHistoryList}" varStatus="loop">								
									<c:set value="${pastHistoryList[loop.index].ID}" var="sample"></c:set>	
									<c:forTokens var="tokenName" items="${PatientOpList.pastIllness}" delims="~" varStatus="status" begin="0">
										<c:choose>
											<c:when test="${tokenName == sample}">

												<c:set var="loopCount1" value="${loopCount1 + 1}" /> 
												<c:if test="${(loopCount1 % 2) eq 1}">
													<tr>
												</c:if>		
													<td width="50%" class="tbcellBorder print_cell">
														${loopCount1}. &nbsp;<c:out value="${pastHistoryList[loop.index].VALUE}"/>
														
														<c:if test="${tokenName == 'PH11' }">
															${PatientOpList.pastIllenesOthr}
														</c:if>
														<c:if test="${tokenName == 'PH8' }">
															( ${PatientOpList.pastIllenesCancers})
														</c:if>
														<c:if test="${tokenName == 'PH10' }">
															( ${PatientOpList.pastIllenesSurg})
														</c:if>
														<c:if test="${tokenName == 'PH12' }">
															( ${PatientOpList.pastIllenesEndDis})
														</c:if>
													</td>
											</c:when>
										</c:choose>  
									</c:forTokens>     	
								</c:forEach>
							</td>
						</tr>
						<c:if test="${fn:length(PatientOpList.pastIllness) eq 0}">
							<tr>
								<td style="text-align:center">History of past illness not found</td>
							</tr>
						</c:if>
					</table>

				</td>
			</tr>

			<!-- end of past history end -->
			<!-- general examination findings -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>General Examination Findings</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table border="0"  align="center" width="100%">
						<c:if test="${dentalOrNot eq 'no' or dentalOrNot eq null}">
						<tr>
							<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Height</b></td>
							<td width="25%" class="tbcellBorder print_cell">${PatientOpList.height}</td>
							<td class="labelheading1 tbcellCss print_cell" width="25%"><b>Weight</b></td>
							<td width="25%" class="tbcellBorder print_cell">${PatientOpList.weight}</td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"> <b>BMI</b></td>
							<td class="tbcellBorder print_cell">${PatientOpList.bmi}</td>
							<td class="labelheading1 tbcellCss print_cell" ><b>Pallor</b></td>
							<td class="tbcellBorder print_cell" >
								<c:choose>
									<c:when test="${PatientOpList.pallor=='Y'}">
										<input type="checkbox" name="PallorChkBox" value="Y" checked="checked" disabled="disabled">Yes
										<input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
									</c:when>
									<c:when test="${PatientOpList.pallor=='N'}">
										<input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="PallorChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="PallorChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="PallorChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>
						</tr>

						<tr>
							<td class="labelheading1 tbcellCss print_cell" ><b>Cyanosis</b></td>
							<td class="tbcellBorder print_cell">
								<c:choose>
									<c:when test="${PatientOpList.cyanosis=='Y'}">
										<input type="checkbox" name="CyanosisChkBox" value="Y" checked="checked" disabled="disabled">Yes
										<input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled"> No
									</c:when>
									<c:when test="${PatientOpList.cyanosis=='N'}">
										<input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="CyanosisChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="CyanosisChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="CyanosisChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>
						 
							<td class="labelheading1 tbcellCss print_cell"><b>Clubbing of Fingers/Toes</b></td>
							<td class="tbcellBorder print_cell">
								<c:choose>
									<c:when test="${PatientOpList.clubbingOfFingers=='Y'}">
										<input type="checkbox" name="ClubbingChkBox" value="Y" checked="checked" disabled="disabled">Yes
										<input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled" >No
									</c:when>
									<c:when test="${PatientOpList.clubbingOfFingers=='N'}">
										<input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="ClubbingChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="ClubbingChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="ClubbingChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>
						</tr>

						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Lymphadenopathy</b></td>
							<td class="tbcellBorder print_cell">
								<c:choose>
									<c:when test="${PatientOpList.lymphadenopathy=='Y'}">
										<input type="checkbox" name="LymphadenopathyChkBox" value="Y" checked="checked" disabled="disabled">Yes
										<input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
									</c:when>
									<c:when test="${PatientOpList.lymphadenopathy=='N'}">
										<input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="LymphadenopathyChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="LymphadenopathyChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="LymphadenopathyChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>

							<td class="labelheading1 tbcellCss print_cell"><b>Edema of Feet</b> </td>
							<td class="tbcellBorder print_cell">
								<c:choose>
									<c:when test="${PatientOpList.edema=='Y'}">
										<input type="checkbox" name="EdemaChkBox" value="Y" checked="checked" disabled="disabled">Yes
										<input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
									</c:when>
									<c:when test="${PatientOpList.edema=='N'}">
										<input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="EdemaChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="EdemaChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="EdemaChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell" ><b>Malnutrition</b></td>
							<td class="tbcellBorder print_cell">
								<c:choose>
									<c:when  test="${PatientOpList.malNutrition=='Y'}">
										<input type="checkbox" name="MalnutritionChkBox" value="Y" checked="checked" disabled="disabled">Yes
										<input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
									</c:when>
									<c:when test="${PatientOpList.malNutrition=='N'}">
										<input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="MalnutritionChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="MalnutritionChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="MalnutritionChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>
							<td class="labelheading1 tbcellCss print_cell"><b>Dehydration</b></td>
							<td class="tbcellBorder print_cell">
								<c:choose>
									<c:when  test="${PatientOpList.dehydration=='Y'}">
										<input type="checkbox" name="DehydrationChkBox" value="Y" checked="checked" disabled="disabled">Yes (${ PatientOpList.dehydrationType})
										<input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
									</c:when>
									<c:when test="${PatientOpList.dehydration=='N'}">
										<input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="DehydrationChkBox" value="N" checked="checked" disabled="disabled">No
									</c:when>
									<c:otherwise>
										<input type="checkbox" name="DehydrationChkBox" value="Y" disabled="disabled">Yes
										<input type="checkbox" name="DehydrationChkBox" value="N" disabled="disabled">No
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						</c:if>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Temperature</b> </td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.temperature}</td> 
							<td class="labelheading1 tbcellCss print_cell"><b>Pulse Rate per Minute</b></td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.pulseRate}</td> 
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Respiration Rate</b></td>
							<td class="tbcellBorder print_cell"  >${PatientOpList.respirationRate}</td>
							<td class="labelheading1 tbcellCss print_cell"><b>BP</b> </td>
							<td  class="tbcellBorder print_cell" >${PatientOpList.bpLmt}</td>
						</tr>
<!-- 						<tr> -->
<!-- 							<td class="labelheading1 tbcellCss print_cell" width="12%"><b>BP Rt. Arm</b></td> -->
<%-- 							<td  class="tbcellBorder print_cell" >${PatientOpList.bpRmt}</td> --%>
<!-- 						</tr> -->
					</table>
				</td>
			</tr>
			<!-- end of general	examination findings -->
			
			<!-- Added for Dental Details -->
			   <c:if test="${dentalOrNot eq 'yes'}">
			   
			    <tr>
		      	<td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Medical history</strong></td>
		      	<td width="20%" class="tbcellBorder print_cell">${PatientOpList.medHistVal}</td>
		     	<td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Medical history Other</strong> </td>
		      	<td width="20%" class="tbcellBorder print_cell">${PatientOpList.medicalHistoryOthr}</td>
		     	</tr>			   
				<tr>
				<td width="20%"class="labelheading1 tbcellCss print_cell"><strong>Drug history</strong> </td>
		      	<td width="20%" class="tbcellBorder print_cell">${PatientOpList.drugHstVal}</td>
		      	</tr>		      
			</c:if>	
						
			<!-- Systematic Examination Findings-->

			<tr>
				<td class="tbheader print_heading" colspan="4" style="text-align:left"><b>Systematic Examination Findings</b></td>
			</tr>

			<tr>
				<td colspan="4">
					<table width="100%">
						<c:if test="${fn:length(symptomsList) > 0}">
							<tr>  
								<td width="5%" class="labelheading1 tbcellCss print_cell"><b>S.No</b></td>        
								<td width="35%" class="labelheading1 tbcellCss print_cell"><b>Main Symptom Name</b></td>   
								<td width="30%" class="labelheading1 tbcellCss print_cell"><b>Sub Symptom Name</b></td>
								<td width="30%" class="labelheading1 tbcellCss print_cell"><b>Symptom Name</b></td>
							</tr>
						</c:if>
						<%int i=1; %>
						<c:forEach items="${symptomsList}" var="element"> 
							<tr>
								<td width="5%" class="tbcellBorder print_cell" ><%=i++ %></td>
								<td width="35%" class="tbcellBorder print_cell" >${element.ID}</td>
								<td width="30%" class="tbcellBorder print_cell" >${element.SUBID}</td>
								<td width="30%" class="tbcellBorder print_cell" >${element.VALUE}</td>
							</tr>
						</c:forEach>  
						<c:if test="${fn:length(symptomsList) eq 0}">
							<tr>
								<td colspan="4" align="center" class="tbcellBorder print_cell">
									<b>Systematic Examination Findings not found</b>
								</td>
							</tr>
						</c:if>
					</table>
				</td>
			</tr>
			 
			 <!-- Added for Dental Details -->
			<c:if test="${dentalOrNot eq 'yes'}">
						<tr><td colspan="4" class="tbcellCss print_cell"><strong><span class="labelheading1">Extra Oral Examinations</span></strong>	
			
			<table width="100%">
				<tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Regional Lymphadenopathy</strong></td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.regionalLymphadenopathyDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.regionalLymphadenopathyDtrsTxt}</td>
		      </tr>
		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Jaws</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.jawsDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.jawsDtrsTxt}</td></tr>	
		      		      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>TMJ</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.tmjDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.tmjDtrsTxt}</td></tr>	
						      <tr>
		      <td width="20%" class="labelheading1 tbcellCss print_cell"><strong>Face</strong> </td>
		      <td width="20%" class="tbcellBorder print_cell"><strong>${PatientOpList.faceDtrsSub}</strong></td>
		      <td width="20%" class="tbcellBorder print_cell">${PatientOpList.faceDtrsTxt}</td></tr>
		      </table></td></tr>
		      
		      
		      <tr><td colspan="4" class="tbcellCss print_cell labelheading1"><strong>Intra Oral Examinations</strong></td></tr>
				<tr>
				<td colspan="4" class="tbcellCss print_cell"><strong><span class="labelheading1">Soft Tissue Examinations</span></strong>
				<table width="100%">
				<tr>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Hard Palate</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral0}</td>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Soft Palate</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral1}</td>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Floor of the mouth</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral2}</td>
				
				</tr>
				<tr>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Tongue</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral3}</td>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Frenal Attachment</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral4}</td>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Buccal Mucosa</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral5}</td>
				
				</tr>
				<tr>
				
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Gingiva</strong></td>
				<td class="tbcellBorder print_cell" width="15%">${PatientOpList.dntsublistoral6}</td>
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Swelling</strong></td>
				<td class="tbcellBorder print_cell" width="15%">
				<span><b>Site:</b>&nbsp;${PatientOpList.swSite}</span><br>
				<span><b>Size:</b>&nbsp;${PatientOpList.swSize}</span><br>
				<span><b>Extension:</b>&nbsp;${PatientOpList.swExtension}</span><br>
				<span><b>Colour:</b>&nbsp;${PatientOpList.swColour}</span><br>
				<span><b>Consistency</b>:&nbsp;${PatientOpList.swConsistency}</span><br>
				<span><b>Tenderness:</b>&nbsp;${PatientOpList.swTenderness}</span><br>
				<span><b>Borders:</b>&nbsp;${PatientOpList.swBorders}</span>
				</td>
				<td class="labelheading1 tbcellCss print_cell" width="15%"><strong>Pus/Discharge</strong></td>
				<td class="tbcellBorder print_cell" width="15%">
				<span><b>Site:</b>&nbsp;${PatientOpList.psSite}</span><br>
				<span><b>Discharge:</b>&nbsp;${PatientOpList.psDischarge}</span>
				</td>
				</tr>
				</table></td>
				</tr>
				<tr><td colspan="4">
				<table width="100%">
				<tr><td colspan="4" class="labelheading1 tbcellCss print_cell"><strong>Hard Tissue examinations</strong></td></tr>
	<c:if test="${fn:length(PatientOpList.carriesdecidous) ne 0 or fn:length(PatientOpList.missingdecidous) ne 0 or fn:length(PatientOpList.grosslydecadedecidous) ne 0 or fn:length(PatientOpList.mobiledecidous) ne 0}">
		      <tr><td width="100%" class="labelheading1 tbcellCss print_cell"><strong>Decidious Dentation</strong></td></tr>
		      <tr><td width="100%" class="tbcellBorder print_cell" colspan="4">
		      
		      <table width="100%" id="decidiousBlock">
		      <c:if test="${fn:length(PatientOpList.carriesdecidous) ne 0}">
		      	<tr id="cariesDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Caries</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="childCaries1" name="childCaries" value="c1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries2" value="c2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries3" name="childCaries" value="c3" > C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries4" value="c4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries5" name="childCaries" value="c5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries6" value="c6" > A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries7" value="c7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries8" name="childCaries" value="c8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries9" value="c9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries10" name="childCaries" value="c10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries11" name="childCaries" value="c11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries12" value="c12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries13" name="childCaries" value="c13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries14" value="c14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries15" name="childCaries" value="c15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries16" value="c16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries17" value="c17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries18" name="childCaries" value="c18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childCaries" id="childCaries19" value="c19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childCaries20" name="childCaries" value="c20"> E
				</div></td>
				</tr>
				</c:if>
				
				<c:if test="${fn:length(PatientOpList.missingdecidous) ne 0}">
				<tr id="missingDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Missing</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="missingTeeth1" name="childMissing" value="m1" > E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth2" value="m2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth3" name="childMissing" value="m3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth4" value="m4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth5" name="childMissing" value="m5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth6" value="m6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth7" value="m7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth8" name="childMissing" value="m8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth9" value="m9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth10" name="childMissing" value="m10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth11" name="childMissing" value="m11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth12" value="m12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth13" name="childMissing" value="m13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth14" value="m14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth15" name="childMissing" value="m15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth16" value="m16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth17" value="m17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth18" name="childMissing" value="m18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMissing" id="missingTeeth19" value="m19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="missingTeeth20" name="childMissing" value="m20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(PatientOpList.grosslydecadedecidous) ne 0}">
				<tr id="grosslyDecdious"><td colspan="2" class="labelheading1 tbcellCss">Grossly Decayed</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss" id="grosslyDecayed1" name="grosslyDecayed" value="g1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed2" value="g2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed3" name="grosslyDecayed" value="g3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed4" value="g4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed5" name="grosslyDecayed" value="g5"> A 
								| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed6" value="g6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed7" value="g7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed8" name="grosslyDecayed" value="g8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed9" value="g9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed10" name="grosslyDecayed" value="g10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed11" name="grosslyDecayed" value="g11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed12" value="g12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed13" name="grosslyDecayed" value="g13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed14" value="g14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed15" name="grosslyDecayed" value="g15"> A
				 				| 
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed16" value="g16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed17" value="g17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed18" name="grosslyDecayed" value="g18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="grosslyDecayed" id="grosslyDecayed19" value="g19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="grosslyDecayed20" name="grosslyDecayed" value="g20"> E
				</div></td>
				</tr>
				</c:if>
				<c:if test="${fn:length(PatientOpList.mobiledecidous) ne 0}">
				<tr id="mobileDecdious" ><td colspan="2" class="labelheading1 tbcellCss">Mobile</td>
				<td colspan="4" align="center" class="tbcellBorder">
				<div style="width:100%;">
				<input style="width:22px;" type="checkbox" class="bgColorCss"  id="childMobile1"  name="childMobile" value="mm1"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile2" value="mm2"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile3" name="childMobile" value="mm3"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile4" value="mm4">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile5" name="childMobile" value="mm5"> A
				 				| 
				 <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile6" value="mm6"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile7" value="mm7"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile8" name="childMobile" value="mm8"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile9" value="mm9">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile10" name="childMobile" value="mm10"> E
				
				<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
				
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile11" name="childMobile" value="mm11"> E <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile12" value="mm12"> D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile13" name="childMobile" value="mm13"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile14" value="mm14">B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile15" name="childMobile" value="mm15"> A 
								|  
				<input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile16" value="mm16"> A <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile17" value="mm17"> B 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile18" name="childMobile" value="mm18"> C <input type="checkbox" class="bgColorCss" style="width:22px;" name="childMobile" id="childMobile19" value="mm19">D 
				<input type="checkbox" class="bgColorCss" style="width:22px;" id="childMobile20" name="childMobile" value="mm20"> E
				</div></td>
				</tr>
				</c:if>
				
				 </table>
		      
		      </td></tr>
		      </c:if>
		      
		      <c:if test="${fn:length(PatientOpList.carriespermanent) ne 0 or fn:length(PatientOpList.rootstumppermannet) ne 0 or fn:length(PatientOpList.mobilitypermanent) ne 0 or fn:length(PatientOpList.attritionpermanent) ne 0 or fn:length(PatientOpList.missingpermanent) ne 0 or fn:length(PatientOpList.otherpermanent) gt 3}">
		      <tr><td width="100%" class="labelheading1 tbcellCss print_cell"><strong>Permanent Dentation</strong></td></tr>
		      <tr><td width="100%" class="tbcellBorder print_cell">
		      <table width="100%" id="permanentBlock" >
		<c:if test="${fn:length(PatientOpList.carriespermanent) ne 0}">
		<tr id="cariesDiv" ><td colspan="1" class="labelheading1 tbcellCss">Caries</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries1" name="caries" value="pc1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries2" name="caries" value="pc2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries3" name="caries" value="pc3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries4" name="caries" value="pc4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries5" name="caries" value="pc5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries6" name="caries" value="pc6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries7" name="caries" value="pc7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries8" name="caries" value="pc8"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries9" name="caries" value="pc9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries10" name="caries" value="pc10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries11" name="caries" value="pc11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries12" name="caries" value="pc12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries13" name="caries" value="pc13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries14" name="caries" value="pc14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries15" name="caries" value="pc15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries16" name="caries" value="pc16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries17" name="caries" value="pc17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries18" name="caries" value="pc18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries19" name="caries" value="pc19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries20" name="caries" value="pc20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries21" name="caries" value="pc21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries22" name="caries" value="pc22"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries23" name="caries" value="pc23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries24" name="caries" value="pc24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries25" name="caries" value="pc25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries26" name="caries" value="pc26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries27" name="caries" value="pc27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries28" name="caries" value="pc28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries29" name="caries" value="pc29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries30" name="caries" value="pc30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries31" name="caries" value="pc31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="caries32" name="caries" value="pc32">8
		</div></td></tr>
		</c:if>
		<c:if test="${fn:length(PatientOpList.rootstumppermannet) ne 0}">
		<tr id="rootDiv" ><td colspan="1" class="labelheading1 tbcellCss">Root stump <br>/ Grossly Decayed </td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed1" name="decayed" value="pr1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed2" name="decayed" value="pr2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed3" name="decayed" value="pr3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed4" name="decayed" value="pr4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed5" name="decayed" value="pr5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed6" name="decayed" value="pr6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed7" name="decayed" value="pr7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed8" name="decayed" value="pr8"> 1
		 				|
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed9" name="decayed" value="pr9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed10" name="decayed" value="pr10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed11" name="decayed" value="pr11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed12" name="decayed" value="pr12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed13" name="decayed" value="pr13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed14" name="decayed" value="pr14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed15" name="decayed" value="pr15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed16" name="decayed" value="pr16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed17" name="decayed" value="pr17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed18" name="decayed" value="pr18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed19" name="decayed" value="pr19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed20" name="decayed" value="pr20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed21" name="decayed" value="pr21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed22" name="decayed" value="pr22"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed23" name="decayed" value="pr23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed24" name="decayed" value="pr24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed25" name="decayed" value="pr25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed26" name="decayed" value="pr26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed27" name="decayed" value="pr27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed28" name="decayed" value="pr28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed29" name="decayed" value="pr29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed30" name="decayed" value="pr30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed31" name="decayed" value="pr31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="decayed32" name="decayed" value="pr32">8
		</div></td>
		</tr>
		</c:if>
		<c:if test="${fn:length(PatientOpList.mobilitypermanent) ne 0}">
		<tr id="mobilityDiv" ><td colspan="1" class="labelheading1 tbcellCss">Mobility</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile1" name="mobile" value="pm1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile2" name="mobile" value="pm2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile3" name="mobile" value="pm3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile4" name="mobile" value="pm4"> 5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile5" name="mobile" value="pm5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile6" name="mobile" value="pm6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile7" name="mobile" value="pm7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile8" name="mobile" value="pm8"> 1 
						|
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile9" name="mobile" value="pm9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile10" name="mobile" value="pm10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile11" name="mobile" value="pm11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile12" name="mobile" value="pm12"> 4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile13" name="mobile" value="pm13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile14" name="mobile" value="pm14"> 6
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile15" name="mobile" value="pm15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile16" name="mobile" value="pm16"> 8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile17" name="mobile" value="pm17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile18" name="mobile" value="pm18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile19" name="mobile" value="pm19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile20" name="mobile" value="pm20"> 5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile21" name="mobile" value="pm21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile22" name="mobile" value="pm22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile23" name="mobile" value="pm23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile24" name="mobile" value="pm24"> 1
						 | 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile25" name="mobile" value="pm25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile26" name="mobile" value="pm26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile27" name="mobile" value="pm27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile28" name="mobile" value="pm28"> 4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile29" name="mobile" value="pm29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile30" name="mobile" value="pm30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile31" name="mobile" value="pm31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="mobile32" name="mobile" value="pm32"> 8
		</div></td>
		</tr>
		</c:if>
		<c:if test="${fn:length(PatientOpList.attritionpermanent) ne 0}">
		<tr id="attritionDiv" ><td colspan="1" class="labelheading1 tbcellCss">Attrition <br>/ Abrasion </td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition1" name="attrition" value="pa1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition2" name="attrition" value="pa2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition3" name="attrition" value="pa3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition4" name="attrition" value="pa4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition5" name="attrition" value="pa5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition6" name="attrition" value="pa6"> 3
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition7" name="attrition" value="pa7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition8" name="attrition" value="pa8"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition9" name="attrition" value="pa9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition10" name="attrition" value="pa10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition11" name="attrition" value="pa11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition12" name="attrition" value="pa12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition13" name="attrition" value="pa13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition14" name="attrition" value="pa14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition15" name="attrition" value="pa15"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition16" name="attrition" value="pa16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition17" name="attrition" value="pa17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition18" name="attrition" value="pa18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition19" name="attrition" value="pa19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition20" name="attrition" value="pa20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition21" name="attrition" value="pa21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition22" name="attrition" value="pa22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition23" name="attrition" value="pa23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition24" name="attrition" value="pa24"> 1 
						| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition25" name="attrition" value="pa25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition26" name="attrition" value="pa26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition27" name="attrition" value="pa27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition28" name="attrition" value="pa28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition29" name="attrition" value="pa29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition30" name="attrition" value="pa30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition31" name="attrition" value="pa31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="attrition32" name="attrition" value="pa32">8
		</div></td>
		</tr> 
		</c:if>
		<c:if test="${fn:length(PatientOpList.missingpermanent) ne 0}">
		<tr id="missingDiv" ><td colspan="1" class="labelheading1 tbcellCss">Missing</td>
		<td colspan="5" align="center" class="tbcellBorder"><div style="width:100%;">
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing1" name="missing" value="pmi1"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing2" name="missing" value="pmi2"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing3" name="missing" value="pmi3"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing4" name="missing" value="pmi4">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing5" name="missing" value="pmi5"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing6" name="missing" value="pmi6"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing7" name="missing" value="pmi7"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing8" name="missing" value="pmi8"> 1
		 				| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing9" name="missing" value="pmi9"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing10" name="missing" value="pmi10"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing11" name="missing" value="pmi11"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing12" name="missing" value="pmi12">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing13" name="missing" value="pmi13"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing14" name="missing" value="pmi14"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing15" name="missing" value="pmi15"> 7  <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing16" name="missing" value="pmi16">8 
		
		<hr style="margin-bottom:0px;margin-top:0px; color: black; height: 1px; background-color:black;"/>
		
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing17" name="missing" value="pmi17"> 8 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing18" name="missing" value="pmi18"> 7 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing19" name="missing" value="pmi19"> 6 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing20" name="missing" value="pmi20">5
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing21" name="missing" value="pmi21"> 4 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing22" name="missing" value="pmi22"> 3 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing23" name="missing" value="pmi23"> 2 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing24" name="missing" value="pmi24"> 1
		 				| 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing25" name="missing" value="pmi25"> 1 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing26" name="missing" value="pmi26"> 2 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing27" name="missing" value="pmi27"> 3 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing28" name="missing" value="pmi28">4 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing29" name="missing" value="pmi29"> 5 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing30" name="missing" value="pmi30"> 6 
		<input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing31" name="missing" value="pmi31"> 7 <input type="checkbox" class="bgColorCss" style="width:22px;"  id="missing32" name="missing" value="pmi32">8
		</div></td>
		</tr>
		</c:if> 
		<c:if test="${fn:length(PatientOpList.otherpermanent) gt 3}">
		<tr id="otherDiv">
		<td class="labelheading1 tbcellCss" colspan="1" id="">Others-<span id="otherPermntDent"></span>
		</td>
		<td id="otherPermText" class="tbcellBorder print_cell" colspan="2" >
		</td>
		</tr>
		</c:if>
		</table></td></tr></c:if>
			<c:if test="${fn:length(PatientOpList.otherpermanent) gt 3}">
		<tr id="otherDiv">
		<td class="labelheading1 tbcellCss" colspan="1" id="">Others-<span id="otherPermntDent"></span></td>
		<td id="otherPermText" class="tbcellBorder print_cell" colspan="2" ></td>
		</tr>
		</c:if>
			<tr><td>
		   <table width="100%">
		<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>Previous Dental Treatment</strong> </td>
		    <td width="30%" class="tbcellBorder print_cell">${PatientOpList.previousDentalTreatment}</td></tr>	
		<c:if test="${fn:length(PatientOpList.occlusionTxt) ne 0}">
		<tr><td width="30%"class="labelheading1 tbcellCss print_cell"><strong>Occlusion</strong> </td>
			<td width="30%" class="tbcellBorder print_cell">${PatientOpList.occlusionTxt}</td>		
		    <td width="30%" class="tbcellBorder print_cell">${PatientOpList.occlusionTypeTxt}</td></tr></c:if>
		</table></td></tr>
		<c:if test="${fn:length(PatientOpList.probingdepth) ne 0}">
		<tr><td width="100%"class="labelheading1 tbcellCss print_cell"><strong>Clinical Probing</strong></td></tr>
		<tr><td colspan="4" class="tbcellBorder" align="center">
		<table border=1>
		<tr><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth0" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth6" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth11" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth1" name="probeDepth"  onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth7" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth12" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth2" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth8" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth13" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth3" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth9" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth14" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth4" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth10" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth15" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth5" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
		<tr><td style="text-align:center">8</td><td style="text-align:center">7</td><td style="text-align:center">6</td><td style="text-align:center">5</td><td style="text-align:center">4</td><td style="text-align:center">3</td><td style="text-align:center">2</td><td style="text-align:center">1</td><td style="text-align:center">1</td><td style="text-align:center">2</td>
		<td style="text-align:center">3</td><td style="text-align:center">4</td><td style="text-align:center">5</td><td style="text-align:center">6</td><td style="text-align:center">7</td><td style="text-align:center">8</td></tr>
		<tr><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth16" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth22" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth27" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth17" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth23" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth28" name="probeDepth" onchange=javascript:fn_checkprobingdepth(this.id,this.value);></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth18" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth24" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth29" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth19" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth25" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth30" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth20" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth26" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);" ></td><td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth31" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td>
		<td><input type="text" style="width:20px;text-align:center" maxlength=1 id="probeDepth21" name="probeDepth" onchange="javascript:fn_checkprobingdepth(this.id,this.value);"></td></tr>
		</table>

		</td></tr></c:if>
		</table></td></tr>
		</c:if> 
				 
			<tr>
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Investigations</b></td>
				<td class="tbcellBorder print_cell"  >${PatientOpList.investRemarks}</td>
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Patient Diagnosed By</b></td>
				<td  class="tbcellBorder print_cell" >${PatientOpList.docType}</td>
			</tr>


				<!--<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Registration No.</b></td>
				<td   >${PatientOpList.docReg}</td>
				</tr><tr>
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Qualification</b></td>
				<td   >${PatientOpList.docQual}</td>

				</tr>

				<tr>
				<td class="labelheading1 tbcellCss print_cell" width="10%"><b>Mobile No.</b></td>
				<td   >${PatientOpList.docMobNo}</td> -->
			<tr>
				<td class="labelheading1 tbcellCss print_cell" width="10%"><b>Doctor Name</b></td>
				<td class="tbcellBorder print_cell">${PatientOpList.docName}</td> 
				<td class="labelheading1 tbcellCss print_cell" width="12%"><b>Patient Type</b></td>
				<td class="tbcellBorder print_cell"  >${PatientOpList.patientType}</td> 
			</tr>

			<tr>
				<td colspan="4">
					<table  border="0" width="100%" align=center>
						<tr>
							<td  colspan="6" style="text-align:left" class="tbheader print_heading" ><b> Diagnosis</b></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Diagnosis Type</b></td>
							<td class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="diagnosisType" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Main Category Name</b></td><td class="tbcellBorder print_cell"  ><bean:write name="preauthDetailsForm"  property="mainCatName" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Category Name</b></td><td class="tbcellBorder print_cell"  ><bean:write name="preauthDetailsForm"  property="catName" /></td>
						</tr>
						<tr>
							<td class="labelheading1 tbcellCss print_cell"><b>Sub Category Name</b></td><td  class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="subCatName" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Disease Name</b></td><td  class="tbcellBorder print_cell" ><bean:write name="preauthDetailsForm"  property="disName" /></td>
							<td class="labelheading1 tbcellCss print_cell"><b>Disease Anatomical Name</b></td><td class="tbcellBorder print_cell"  ><bean:write name="preauthDetailsForm"  property="disAnatomicalSitename" /></td>
						</tr>
					</table>
				</td>
			</tr>

			<!-- plan of treatment -->
			
			<c:if test="${ipFlag eq 'IP' || ipFlag eq 'IPD'}">
			
			<tr>
				<td colspan="4" style="text-align:left" class="tbheader print_heading"><b> Plan of Treatment(Surgical)</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table cellpadding="1" cellspacing="1" align="center">
						<tr>
							<td width="10%" style="word-wrap:break-word;" class="labelheading1 tbcellCss print_cell"><b>Category Name</b></td>
							<td width="13%" class="labelheading1 tbcellCss print_cell"><b>ICD Category Name</b></td>
							<td width="12%" class="labelheading1 tbcellCss print_cell"><b>Procedure Name</b></td>
							<td width="12%" class="labelheading1 tbcellCss print_cell"><b>Units</b></td>
							<td width="20%" class="labelheading1 tbcellCss print_cell" ><b>Special Investigations</b> </td>
							<td width="25%" class="labelheading1 tbcellCss print_cell"><b>Remarks</b></td>
							<td width="15%" class="labelheading1 tbcellCss print_cell"><b>Treating Doctor</b></td>
							<!--<td width="10%" class="labelheading1 tbcellCss print_cell"><b>Doctor Reg No</b></td>-->
							<!--<td width="10%" class="labelheading1 tbcellCss print_cell">Days</td>-->
						</tr>
						<logic:present name="preauthDetailsForm" property="lstPreauthVO">
							<bean:size id="caseList" name="preauthDetailsForm" property="lstPreauthVO"/>
							<logic:greaterThan value="0" name="caseList">
								<c:set var="procNamesList" value="" />
								<logic:iterate id="results" name="preauthDetailsForm" property="lstPreauthVO" indexId="index" >
									<tr id="splInvetsDataTable" style="display:true">
										<td class="tbcellBorder print_cell"  style="word-wrap:break-word;">
											<bean:write name="results" property="asriCatName" />(<bean:write name="results" property="catId" />)
										</td>
										<td class="tbcellBorder print_cell"  style="word-wrap:break-word;">
											<bean:write name="results" property="catName" />(<bean:write name="results" property="icdCatCode" />)
										</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;">
											<bean:write name="results" property="procName" />(<bean:write name="results" property="icdProcCode" />)
										</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;">
										<logic:notEqual name="results" property="opProcUnits" value="-1">
											<bean:write name="results" property="opProcUnits" />
										</logic:notEqual>
										<logic:equal name="results" property="opProcUnits" value="-1">
											-NA-
										</logic:equal>
										</td>
										<c:if test="${procNamesList ne ''}">
											<c:set var="procNamesList" value="${procNamesList} , ${results.procName}" />
										</c:if>
										<c:if test="${procNamesList eq ''}">
											<c:set var="procNamesList" value="${results.procName}" />
										</c:if>
										<bean:size id="splattachList" name="results" property="lstSplInvet" />
										<c:set var="splInvstCount" value="1" />
										<logic:greaterThan value="0" name="splattachList" >
											<td  class="tbcellBorder print_cell"  style="word-wrap:break-word;">
												<logic:iterate id="results1" name="results" property="lstSplInvet" indexId="index1" >
													<a href="javascript:javascript:fn_openAtachment('<bean:write name="results1" property="filePath" />')" >
														<bean:write name="results1" property="filename" />
													</a> 
													<c:if test="${fn:length(results.lstSplInvet) ne splInvstCount}">
														,
													</c:if>
													<c:set var="splInvstCount" value="${splInvstCount+1 }" />
												</logic:iterate>
											</td>
										</logic:greaterThan> 
										<logic:equal value="0" name="splattachList">
											<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >&nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;    -- &nbsp;</td>
										</logic:equal>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >&nbsp;&nbsp;${results.investRemarks}</td>
										<td class="tbcellBorder print_cell" style="word-wrap:break-word;"  >&nbsp;&nbsp;<bean:write name="results" property="docName" /></td>
									</tr>
								</logic:iterate>
							</logic:greaterThan>
						</logic:present>
					</table>
				</td>
			</tr>
			</c:if>
			<br/>
			<c:if test="${ipFlag eq 'IPM'}">
			
			<tr>
				<td colspan="4" style="text-align:left" class="tbheader print_heading"><b> Plan of Treatment(Medical)</b></td>
			</tr>
			<tr><td colspan="4">
		   <table width="100%">
				<tr>
				
				<td   class="labelheading1 tbcellCss"><b>Speciality</b></td>
				<td   class="tbcellBorder"><bean:write name="preauthDetailsForm" property="medicalSpclty" /></td>
				<td   class="labelheading1 tbcellCss"><b>Category</b></td>
				<td   class="tbcellBorder"><bean:write name="preauthDetailsForm" property="icdProcName1" /></td>
			    </tr>
			 <c:if test="${critical1 ne null && critical1 ne '' }">
			
    		<tr>
    		<td   class="labelheading1 tbcellCss"><b>Procedure</b></td>
				<td   class="tbcellBorder" style="word-wrap:break-word;width:25%"><bean:write name="preauthDetailsForm" property="medicalCat" /></td>
			    
    		
    		
    		<td  style="word-wrap:break-word;width:25%" class="labelheading1 tbcellCss"><b>Case Type</b></td>
    		<c:if test="${critical1 eq 'Y'}">
    		<td  class="tbcellBorder" style="word-wrap:break-word;width:25%">CRITICAL </td>
    		</c:if>
    		<c:if test="${critical1 eq 'N'}">
    		<td  class="tbcellBorder" style="word-wrap:break-word;width:25%">NON CRITICAL </td>
    		</c:if>
    		<!-- <td  style="word-wrap:break-word;width:25%" > &nbsp;</td>
    		<td  style="word-wrap:break-word;width:25%" > &nbsp; </td> -->
    		</tr>
    		</c:if>  
    		 <c:if test="${ipOtherRemarks1 ne null && ipOtherRemarks1 ne '' }">
			
    		<tr>
    		<td   class="labelheading1 tbcellCss"><b>Reason for selecting any other procedure</b></td>
				<%-- <td   class="tbcellBorder" style="word-wrap:break-word;width:25%"><bean:write name="preauthDetailsForm" property="medicalCat" /></td> --%>
			    
    		
    		<td  class="tbcellBorder" style="word-wrap:break-word;width:25%">${ipOtherRemarks1} </td>
    		
    		<!-- <td  style="word-wrap:break-word;width:25%" > &nbsp;</td>
    		<td  style="word-wrap:break-word;width:25%" > &nbsp; </td> -->
    		</tr>
    		</c:if>  
			    
			
			</table>
			</td>
			</tr>
			</c:if>
		
			
			<!--end of plan of treatment -->
			<!-- declaration -->
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td colspan="6">I hereby declare that I have not requested for the treatment of the same patient/treated the same patient earlier for the same procedure.
								And/or I hereby declare that this preauthorisation request is in continuation of the earlier treatment given.
						</tr>
						<tr>
							<td style="height:80px;text-align:right;vertical-align:bottom;">
								Signature of Treating Doctor with seal 
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of declaration -->
			<!-- admission and financial details -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>Admission and Financial Details</b></td>
			</tr>
			<tr>
				<td class="labelheading1 tbcellCss print_cell"><b>Admission Type</b></td>
				<td class="tbcellBorder print_cell" align="center" >
					<logic:equal value="PLN" name="preauthDetailsForm" property="admissionType" >
						Planned
					</logic:equal>
					<logic:equal value="EMG" name="preauthDetailsForm" property="admissionType" >
						Emergency
					</logic:equal>
				</td>
				<td class="labelheading1 tbcellCss print_cell"><b>Date of Admission</b></td>
				<td align="center" class="tbcellBorder print_cell"><bean:write name="preauthDetailsForm" property="ipRegDate"/></td>
			</tr>
			<!-- end of admission and financial details -->
			<!-- Cost Estimation Break-up -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>4.Cost Estimation Break-up</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td width="40%">Total Package amount admissible under the scheme :</td>
							<td><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></td>
						</tr>
						<tr>
							<td width="40%">Whether Telephonic Intimation given?:</td>
							<td><bean:write name="preauthDetailsForm" property="telephonicFlag" /></td>
						</tr>
						<tr style="height:80px;vertical-align:bottom;">
							<td width="40%" >Signature of Billing Head with Stamp</td>
							<td>&nbsp;</td>
							<td width="10%" style="text-align:right">Date: </td>
							<td>${lStrCurentDt}</td>
							<td></td>
						</tr>	
						<tr>
							<td colspan="4" style="text-align:justify;">
								The hospital hereby declare that this preauthorisation request is raised for the specific treatment <b> ${procNamesList} </b> of the patient <b>  ${PatientOpList.patientName} </b> with 
								
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
								
								Health Card No. <b>${PatientOpList.rationCard} </b>. 
								The hospital completely owns the responsibility for the diagnosis and treatment / procedure proposed for this patient. The hospital acknowledges that this patient is an 
								
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								Employees
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								Working Journalists
								</logic:equal> 
								Health Scheme beneficiary, hence to be evaluated and treated on cashless basis and the hospital has not charged the patient for clinical and diagnostic evaluation.  The hospital accepts that the preauthorisation given by the Insurance / Trust is for the provision of financial assistance and hence the Insurance / Trust are not responsible for the diagnosis, treatment procedure and its outcome. The hospital followed all the guidelines issued by the Trust from time to time and abides by all the clauses of MOU in raising this preauthorisation.
								The hospital did not treat this patient for the same procedure / treatment under any other scheme and did not receive any financial assistance under any other Government scheme.
							</td>
						</tr>
						<tr>
							<td style="height:80px;vertical-align:bottom;">Signature of MEDCO of the Hospital With seal</td><td>&nbsp;</td>
							<td width="10%" style="text-align:right;height:80px;vertical-align:bottom;">Date: </td><td style="height:80px;vertical-align:bottom;">${lStrCurentDt}</td><td></td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of cost estimatiom break up -->
			<!-- PART-III COUNSELLING OF THE PATIENT/GUARDIAN/ATTENDENT/RELATIVE. -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-III COUNSELLING OF THE PATIENT/GUARDIAN/ATTENDENT/RELATIVE.</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td style="text-align:justify;">I have counselled the patient / guardian / attendants / relatives about the risks and benefits in the surgery / therapy in his / her own language and attached the documents for the same. I have done the video counselling for mandatory cases / high risk cases and the same is attached in the video attachments slot.</td>
						</tr>
						<tr>
							<td style="height:40px;vertical-align:bottom;">Counselling remarks</td>
						</tr>
						<tr>
							<td style="height:40px;vertical-align:bottom;">Name of Counselling Doctor</td>
						</tr>
						<tr>
							<td>Date and Time: ${lStrCurentDtTime}</td>
							<td></td>
						</tr>
						<tr style="height:40px;vertical-align:bottom;">
							<td>Signature</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of PART-III COUNSELLING OF THE PATIENT/GUARDIAN/ATTENDENT/RELATIVE.  -->
			<!-- PART-IV Consent by beneficiary/guardian/attendant. -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-IV Consent by beneficiary/guardian/attendant.</b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table>
						<tr>
							<td colspan="2" style="text-align:justify;">
								I / We hereby declare that I am having 
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
								Health Card no.&nbsp;&nbsp;&nbsp;
								<b>${PatientOpList.rationCard}</b> &nbsp;(Village)
								<b>${PatientOpList.cardVillage}</b>&nbsp;&nbsp;(Mandal)
								<b>${PatientOpList.cardMandal}</b> &nbsp;&nbsp;District <b>${PatientOpList.cardDistrict}</b>&nbsp; by Govt of <logic:equal name="preauthDetailsForm" property="state" value="CD202">Telangana</logic:equal> <logic:notEqual name="preauthDetailsForm" property="state" value="CD202">A.P</logic:notEqual> and is presently residing at (H.No ) <b>${PatientOpList.houseNo} </b> &nbsp;(Village)
								<b> ${PatientOpList.village}</b>&nbsp;&nbsp;(Mandal)
								<b>${PatientOpList.mandal}</b> &nbsp;&nbsp;District  <b>${PatientOpList.district} </b>&nbsp;&nbsp;  .I/We have been explained by treating doctors in my own language the risks and benefits involved in the surgery/therapy and I have given consent for <b>${procNamesList}</b> procedure. I/We further state that I am not covered by any other insurance/reimbursement scheme by government.
							</td>
						</tr>
						<tr style="height:60px;vertical-align:bottom;">
							<td> Signature/Left Thumb impression of patient :</td>
							<td>Name of Patient:${PatientOpList.patientName}</td>
						</tr>
						<tr style="height:60px;vertical-align:bottom;">
							<td>  Signature/Left Thumb impression of patient relative<br />( If patient is child/if patient is unfit to sign )</td>
							<td> Name of the patient relative : </td>
						</tr>
						<tr style="height:20px;vertical-align:bottom;">
							<td> Relationship with patient : </td>
							<td > Mobile No of the relative :</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of PART-IV Consent by beneficiary/guardian/attendant. -->
			
			<!-- PART-V DECLARATION BY AAROGYAMITHRA -->
			<tr>
				<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-V DECLARATION BY <c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201'}" >
								VAIDYA MITHRA
							</c:when>
							<c:otherwise >
								AAROGYA MITHRA
							</c:otherwise>
						</c:choose></b></td>
			</tr>
			<tr>
				<td colspan="4">
					<table id="mitDec" >
						<tr>
							<td colspan="3" style="text-align:justify;">
								The Patient Mr/Ms&nbsp;&nbsp;<b> ${PatientOpList.patientName} </b>
								admitted in&nbsp;&nbsp;&nbsp; <b><bean:write name="preauthDetailsForm"  property="hospitalName" /></b> hospital on&nbsp;<b><bean:write name="preauthDetailsForm" property="admissionDate"/></b> &nbsp; is a card holder &nbsp;with 
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
								Health Card no: <b> ${PatientOpList.rationCard} </b> &nbsp;and belonging to&nbsp;&nbsp;
								<b>${PatientOpList.cardVillage}</b> &nbsp;village&nbsp;&nbsp;<b>${PatientOpList.cardMandal}</b> &nbsp;Mandal&nbsp;&nbsp; <b>${PatientOpList.cardDistrict}</b>
								&nbsp;District. The details have been personally verified by me. I declare that the patient is on bed in the hospital <b><bean:write name="preauthDetailsForm"  property="hospitalName" /></b> and the preauthorisation request is genuine and there is no duplication.
							</td>
						</tr>
						<tr>
							<td style="height:60px;vertical-align:bottom;">Signature of <c:choose>
							<c:when test="${preauthCaseSchemeId eq 'CD201'}" >
								Vaidya Mithra
							</c:when>
							<c:otherwise >
								Aarogya mithra
							</c:otherwise>
						</c:choose></td>
						</tr>
						<tr>
							<td>Name:${mitActorname}</td>
							<td>Code:<bean:write name="preauthDetailsForm" property="hospCode" /></td>
							<td>Date:${mitActDt}</td>
						</tr>
					</table>
				</td>
			</tr>
			<!-- end of PART-V DECLARATION BY AAROGYAMITHRA  -->
			
			<!-- PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor) -->
			<c:if test="${ppdView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table  id="ppdDec" >
							<tr>
								<td style="text-align:justify;">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having 
									<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
									Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the Panel Doctor to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ppdActorname}</td>
							</tr>
							<tr>
								<td>Date:${ppdActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor) -->
			
			<!-- PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor) for Rejection-->
			<c:if test="${ppdRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor)</b></td>
				</tr>
				<tr>
					<td colspan="4" >
						<table id="ppdRejDec" >
							<tr>
								<td >PRE-AUTHORIZATION REJECTED for the following Reasons:<br/> 
									${ppdRejRemarks}
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ppdRejActorname}</td>
							</tr>
							<tr>
								<td>Date:${ppdRejActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
				
			</c:if>
			<!-- end of PART-VI DECLARATION BY PRE-AUTHORIZATION(By Panel Doctor)-->
			
			<!-- PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			<c:if test="${ptdAprvView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="ptdDec" >
							<tr>
								<td style="text-align:justify;">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having 
									<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal> 
									Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the Trust to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ptdAprvActorname}</td>
							</tr>
							<tr>
								<td>Date:${ptdAprvActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			
			<!-- PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			<c:if test="${ptdRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="ptdRejDec">
							<tr>
								<td>PRE-AUTHORIZATION REJECTED for the following Reasons:${ptdRejRemarks}</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${ptdRejActorname}</td>
							</tr>
							<tr>
								<td>Date:${ptdRejActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VII DECLARATION BY PRE-AUTHORIZATION(By Trust Committee)-->
			
			<!-- PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			<c:if test="${eoAprvView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="eoDec" >
							<tr>
								<td style="text-align:justify;">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having
									 <logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
									  Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the EO to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${eoAprvActorname}</td>
							</tr>
							<tr>
								<td>Date:${eoAprvActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			
			<!-- PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			<c:if test="${eoRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="eoRejDec" >
							<tr>
								<td >
								PRE-AUTHORIZATION REJECTED for the following Reasons:<br/> 
								${eoRejRemarks}
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr>
								<td>Name:${eoRejActorname}</td>
							</tr>
							<tr>
								<td>Date:${eoRejActDt}</td>
							</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-VIII DECLARATION BY PRE-AUTHORIZATION(By EO)-->
			
			<!-- PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->
			<c:if test="${ceoAprvView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="ceoDec" >
							<tr>
								<td style="text-align:justify">
									The network hospital<b> <bean:write name="preauthDetailsForm"  property="hospitalName" /> </b>code<b><bean:write name="preauthDetailsForm" property="hospCode" /></b>which has admitted Mr/Ms <b> ${PatientOpList.patientName} </b>(the patient) on <b><bean:write name="preauthDetailsForm" property="ipRegDate"/></b>(date and time)
									having 
									
									<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD501">
								EHS 
								</logic:equal>
								<logic:equal name="preauthDetailsForm" property="patientScheme" value="CD502">
								WJHS
								</logic:equal>
								
									Health Card No <b> ${PatientOpList.rationCard} </b> and belonging to District <b>${PatientOpList.cardDistrict}</b> suffering from <b><bean:write name="preauthDetailsForm" property="disName" /></b> having given consent for
									<b>${procNamesList}</b>surgery/therapy is hereby authorised by the CEO to undertake the procedure/treatment subject to maximum package rate of Rs<b><bean:write name="preauthDetailsForm" property="preauthTotalPackageAmt" /></b>
								</td>
							</tr>
							<tr>
								<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
							</tr>
							<tr><td>Name:${ceoAprvActorname}</td></tr>
							<tr><td>Date:${ceoAprvActDt}</td></tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->	
			
			<!-- PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->	
			<c:if test="${ceoRejView eq 'Y'}">
				<tr>
					<td colspan="4" class="tbheader print_heading" style="text-align:left"><b>PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)</b></td>
				</tr>
				<tr>
					<td colspan="4">
						<table id="eoRejDec" >
						<tr>
							<td>
								PRE-AUTHORIZATION REJECTED for the following Reasons:<br/> 
								${ceoRejRemarks}
							</td>
						</tr>
						<tr>
							<td style="height:60px;vertical-align:bottom;">Authorized Signature</td>
						</tr>
						<tr><td>Name:${ceoRejActorname}</td></tr>
						<tr><td>Date:${ceoRejActDt}</td>
						</tr>
						</table>
					</td>
				</tr>
			</c:if>
			<!-- end of PART-IX DECLARATION BY PRE-AUTHORIZATION(By CEO)-->
			<!-- Print button -->
			<tr class="print_buttons">
				<td colspan="4" style="text-align:center">
					<button class="but"  type="button" id="Print" onclick="fn_Print()">Print</button>
				</td>
			</tr>

		</table>
	</form>
</body>
</html>