var uri = "http://localhost:8267/PrecisionAarogyasriCommService/";

var serverstatus = "false";

function GetFeature(fieldID){
	var res = CommunicateBiometricSDK("GetFeature");
	if(res.ServerStatus == "true"){
		if(res.Feature != ""){
			fieldID.value = res.Feature;
		}
		else{
			alert("Fingerprint capture failed. Please try again");
		}
	}
	else{
		alert(res.ServerStatus);
	}
}

function GetFeatureAccrual(fieldID) {
	var res = CommunicateBiometricSDK("GetFeatureAccrual");
	if(res.ServerStatus == "true"){
		if(res.Feature != ""){
			fieldID.value = res.Feature;
		}
		else{
			alert("Fingerprint capture failed. Please try again");
		}
	}
	else{
		alert(res.ServerStatus);
	}
}

function CommunicateBiometricSDK(method) {
    var res = "";
	var result;
	try{
		jQuery.support.cors = true;
		var temp = JSON.parse(000); // To check whether the browser is able to use JSON. If not (for old IE versions like 6,7) it will try to access ActiveX methodology.
		jQuery.ajax({
			method: "POST",
			type: "POST",
			async: false,
			crossDomain: true,
			url: uri + method,
			contentType: "application/json; charset=utf-8",
			data: '{"inData":{"EnableLog":"","LocalFilePath":"","SaveImage":""}}',
			dataType: "json",
			processData: false,
			success: function(data){
				serverstatus = "true";			
				res = JSON.parse(JSON.stringify(data));
				if (serverstatus == "true"){
					var feature;
					if(method == "GetFeatureAccrual"){
						feature = res.GetFeatureAccrualResult.Feature;
					}
					else{
						feature = res.GetFeatureResult.Feature
					}
					result = { "ServerStatus":serverstatus, "Feature": feature };
				}
				else{
					result = {"ServerStatus":serverstatus};
				}
			},
			error: function (jqXHR, ajaxOptions, thrownError){
				try{
					result = getFeatureActiveX(method);
				}
				catch(e){
					serverstatus = getHttpError(jqXHR, thrownError);
					result = {"ServerStatus":serverstatus};
				}
			}
		});
	}
	catch(e){
		//result = {"ServerStatus":e};
		try{
			result = getFeatureActiveX(method);
		}
		catch(e){
			serverstatus = getHttpError(jqXHR, thrownError);
			result = {"ServerStatus":serverstatus};
		}
	}
    return result;
}

function getFeatureActiveX(method){
	var result;
	try{
		var ObjActivex;
		ObjActivex = new ActiveXObject('WEB_API_3.Legend');
		if(method == "GetFeatureAccrual"){
			ObjActivex.GetFeatureAccrual();
		}
		else{
			ObjActivex.GetFeature();
		}
		serverstatus = "true";
		result = { "ServerStatus":serverstatus,
				"Feature": ObjActivex.Feature
		};
	}
	catch(err){
		result = {"ServerStatus":err};
	}
	return result;
}

function getHttpError(jqXHR, thrownError) {
    var err = "Unhandled Exception";
    if(jqXHR.status === 0){
        err = 'Service Unavailable';
    }
	else if(jqXHR.status == 404){
        err = 'Requested page not found';
    }
	else if(jqXHR.status == 500){
        err = 'Internal Server Error';
    }
	else if(thrownError === 'parsererror'){
        err = 'Requested JSON parse failed';
    }
	else if(thrownError === 'timeout'){
        err = 'Time out error';
    }
	else if(thrownError === 'abort'){
        err = 'Ajax request aborted';
    }
	else{
        err = 'Unhandled Error';
    }
    return err;
}