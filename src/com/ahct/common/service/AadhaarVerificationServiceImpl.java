package com.ahct.common.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ahct.common.util.AadhaarABHAUtility;
import com.ahct.common.vo.LabelBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.tcs.framework.configuration.ConfigurationService;
import com.tcs.framework.persistanceConfiguration.GenericDAO;
import com.tcs.framework.persistanceConfiguration.GenericDAOQueryCriteria;
import com.ahct.common.service.AadhaarVerificationService;
import com.ahct.model.AbhaAddressDtls;
import com.ahct.model.AbhaResponse;
import com.ahct.model.EhfEnrollmentFamily;
import com.ahct.model.EhfJrnlstFamily;
import com.ahct.reports.vo.EmployeeVO;

import java.security.Key;

import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class AadhaarVerificationServiceImpl implements AadhaarVerificationService {
	private final static Logger GLOGGER = Logger.getLogger ( AadhaarVerificationServiceImpl.class ) ;
    GenericDAO genericDao;
    private String method_name=null;
    private String service_url="/syntizenbioauth/rest/aadhaarauthservices/";
    private static final String KEY_STRING  = "TrustKeyAadhaarr";
    private static final String ALGORITHM = "AES";
	
	private static ConfigurationService configurationService;
	private static CompositeConfiguration config;
	public void setGenericDao(GenericDAO genericDao) {
		this.genericDao = genericDao;
	}
	static {
		configurationService = ConfigurationService.getInstance();
		config = configurationService.getConfiguration();
	}
	
	@Override
	public JSONObject generateAbhaOtp(Map<String,String> lMap) {
		System.out.println("Entered----------" + lMap.get("aadharNum"));
		JSONObject jsonObj = new JSONObject();
		try {
			String serviceUrl = config.getString("internalAbhaApi");
			String methodName = "/m1/sendotp";
			String url = serviceUrl + methodName;
			
			SSLSocketFactory sf=null;
			SSLContext context=null;
			context=SSLContext.getInstance("TLSv1.2");
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			
			DefaultHttpClient client=new DefaultHttpClient();
			Scheme scheme=new Scheme("https",443,sf);
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			
			System.out.println("url::" + url);
			HttpPost post = new HttpPost(url);
			// request body
			JSONObject requestBody = new JSONObject();
			requestBody.put("aadhaarNo", lMap.get("aadharNum"));
			
			StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);
			
			post.setHeader("Content-Type", "application/json");
			HttpResponse response = client.execute(post);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("statusCode: "+statusCode);
			String responseBody = EntityUtils.toString(response.getEntity());
			
			System.out.println("API response: " + responseBody);
			jsonObj = new JSONObject(responseBody);
			System.out.println("Response Object::" + jsonObj.toString());
		 } catch (Exception e) {
			 e.printStackTrace();
			 try {
				 jsonObj.put("error", e.getLocalizedMessage());
				 } catch (Exception jsonException) {
		            jsonException.printStackTrace();
		        }
		    }
		    return jsonObj;
	}
		 
	@Override
	public  Map<String, String> verifyAbhaOTP(Map<String,String> lMap) {
		String abhaGenReg = lMap.get("abhaGenReg");
		Map<String, String> respMap = new HashMap<String,String>();
		EmployeeVO empVO = new EmployeeVO();
		String abhaNumber = "", prefAddress="",stateName="", status="";
		String firstName = "", lastName = "", middleName = "", gender="",dob="",mobile="",districtName="",pinCode="",address="",abhaType="", phrAddress="", isNew="";
		JSONObject jsonObj = new JSONObject();
		try {
			String serviceUrl = config.getString("internalAbhaApi");
			String methodName = "/m1/verifyotp";
			String url = serviceUrl + methodName;
			
			SSLSocketFactory sf=null;
			SSLContext context=null;
			context=SSLContext.getInstance("TLSv1.2");
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			
			DefaultHttpClient client=new DefaultHttpClient();
			Scheme scheme=new Scheme("https",443,sf);
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept", "application/json");
			
			JSONObject requestBody = new JSONObject();
			requestBody.put("mobile", lMap.get("mobileNumber"));
			requestBody.put("aadhaarNo", lMap.get("aadharNum"));
			requestBody.put("otpValue", lMap.get("aadharNum_otp"));
			requestBody.put("txnId", lMap.get("abhaTxn"));
			requestBody.put("cardType", lMap.get("cardType"));
			requestBody.put("cardValue", lMap.get("cardValue"));
			requestBody.put("abhaGenReg", lMap.get("abhaGenReg"));
			
			StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			
			int statusCode = response.getStatusLine().getStatusCode();
			String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("Raw Response: " + responseBody);
			
			jsonObj = new JSONObject(responseBody);
			
			System.out.println("Response Object::" + jsonObj.toString());
			if (responseBody == null || responseBody.trim().isEmpty()) {
				respMap.put("msg","Empty response from server.");
				return respMap;
			}
			try {
				jsonObj = new JSONObject(responseBody);
				//String status  = jsonObj.getString("statusCode");
				if (jsonObj.has("statusCode")) {
			        status = jsonObj.get("statusCode").toString();
			    } else if (jsonObj.has("status")) {
			        status = jsonObj.get("status").toString();
			    }
				System.out.println("status  :: "+status);
				respMap.put("statusCode",status);
				
				if("200".equals(status)){
					isNew = jsonObj.get("isNew").toString();
					System.out.println("IsNew flag::: "+isNew);
					
					if (jsonObj.has("responseObject")) {
						JSONObject responseObject = jsonObj.getJSONObject("responseObject");
						abhaNumber = responseObject.getString("ABHANumber");
							abhaNumber = abhaNumber.replaceAll("-", "");
						respMap.put("msg",abhaNumber);
						firstName = responseObject.optString("firstName");
						middleName = responseObject.getString("middleName");
						lastName = responseObject.getString("lastName");
						gender = responseObject.getString("gender");
						dob = responseObject.getString("dob");
						stateName = responseObject.getString("stateName");
						mobile = responseObject.getString("mobile");
						districtName = responseObject.getString("districtName");
						pinCode = responseObject.getString("pinCode");
						address = responseObject.getString("address");
						abhaType = responseObject.getString("abhaType");
						//phrAddress = responseObject.getString("phrAddress");
						JSONArray phrAddressArr = responseObject.getJSONArray("phrAddress");
						System.out.println("phrAddress :: "+phrAddress);
						prefAddress = responseObject.getString("preferredAddress");
						String abhaPhoto = savePhoto(responseObject.getString("photo"), abhaNumber);
						System.out.println("responseObject::" + responseObject.toString());
						respMap.put("msg",abhaNumber);
						
						empVO.setMnum(lMap.get("cardValue"));
						empVO.setAbhaNumber(abhaNumber);
						empVO.setEmpType(lMap.get("cardType"));
						empVO.setAadhar(lMap.get("aadharNum"));
						empVO.setUSERID(lMap.get("userId"));
						empVO.setAddr(address);
						empVO.setPin(pinCode);
						empVO.setDistId(districtName);
						empVO.setFirstName(firstName);
						empVO.setMiddleName(middleName);
						empVO.setLastName(lastName);
						empVO.setGender(gender);
						empVO.setDob(dob);
						empVO.setId(abhaType);
						empVO.setAddr1(prefAddress);
						empVO.setAddr2(stateName);
						empVO.setId(isNew);
						empVO.setValue(abhaGenReg);
						empVO.setFile(abhaPhoto);
						empVO.setAuthType("OTP");
			            if(mobile == null)
			            	empVO.setMobileNo("");
			            else
			            	empVO.setMobileNo(mobile);
			            
			            saveAbhaDetails(empVO, phrAddressArr);
			            }
	                }
	                else if("500".equals(status)){
	                	abhaNumber =  jsonObj.getString("message");  
	                	System.out.println("abhaNumber=>"+abhaNumber);
	                	 respMap.put("msg",abhaNumber);
			             return respMap; 
	                }
	                else if (jsonObj.has("responseObject") && jsonObj.getJSONObject("responseObject").has("error")) {
				    JSONObject errorObj = jsonObj.getJSONObject("responseObject").getJSONObject("error");
				    String errMsg = errorObj.optString("message", "Unknown Error");
				    System.out.println("Error Message => " + errMsg);
				    respMap.put("msg", errMsg);
				    return respMap;
	                }

		        } catch (Exception e) {
		            System.out.println("Error: Response is not a valid JSON object.");
		            e.printStackTrace();
		        }
		        System.out.println("ABHA Number::" + abhaNumber);
		    } catch (Exception e) {
		        e.printStackTrace();
		        respMap.put("msg",e.getMessage());
		    }
		    return respMap;
		}
	 
	 private String saveAbhaDetails(EmployeeVO empVO, JSONArray phrAddressArr) {
		 try {
			 String empNo = empVO.getMnum();
			 String empType = empVO.getEmpType();
			 EhfEnrollmentFamily ehsEnrollmentFamily = new EhfEnrollmentFamily();
			 EhfJrnlstFamily ehfJrnlstFamily = new EhfJrnlstFamily();
			 AbhaAddressDtls abhaAddressDtls = new AbhaAddressDtls();
			 AbhaResponse abhaResponse = new AbhaResponse();
			 
			 //Saving the abha response
			 abhaResponse.setCardId(empVO.getMnum());
			 abhaResponse.setCardType(empVO.getEmpType());
			 abhaResponse.setAbhaId(empVO.getAbhaNumber());
			 abhaResponse.setMobile(empVO.getMobileNo());
			 abhaResponse.setFirstName(empVO.getFirstName());
			 abhaResponse.setMiddleName(empVO.getMiddleName());
			 abhaResponse.setLastName(empVO.getLastName());
			 abhaResponse.setDob(empVO.getDob());
			 abhaResponse.setAadharAddr(empVO.getAddr());
			 abhaResponse.setState(empVO.getAddr2());
			 abhaResponse.setGender(empVO.getGender());
			 abhaResponse.setEkycPhotoPath(empVO.getFile());
			 abhaResponse.setIsAbhaNew(empVO.getId());
			 abhaResponse.setIsEkycVerified("Y");
			 abhaResponse.setActiveYn("Y");
			 abhaResponse.setCrtDt(new Timestamp(System.currentTimeMillis()));
			 abhaResponse.setCrtUsr(empVO.getUSERID());
			 abhaResponse.setAppOprNabh(empVO.getValue());
			 abhaResponse.setAbhaType(empVO.getId());
			 abhaResponse.setAbhaPrefAddr(empVO.getAddr1());
			 abhaResponse.setAadhaarCard(empVO.getAadhar());
			 abhaResponse.setAuthType(empVO.getAuthType());
			 abhaResponse.setPinCode(empVO.getPin());
			 abhaResponse.setDistrict(empVO.getDistId());
			 genericDao.save(abhaResponse);
			 
			//Saving the abhaAddressDtls
			 for (int i = 0; i < phrAddressArr.length(); i++) {
				    String phrAddr = phrAddressArr.getString(i);
				    abhaAddressDtls = new AbhaAddressDtls();
				    abhaAddressDtls.setAbhaId(empVO.getAbhaNumber());
				    abhaAddressDtls.setAbhaAddress(phrAddr);
				    abhaAddressDtls.setActiveYn("Y");
				    abhaAddressDtls.setCrtDt(new Timestamp(System.currentTimeMillis()));
				    abhaAddressDtls.setCrtUsr(empVO.getUSERID());
				    genericDao.save(abhaAddressDtls);
				}
			 String selectQury = null;
			 if("J".equalsIgnoreCase(empType)){
				 selectQury = "SELECT JOURNAL_ENROLL_ID as ID FROM EHF_JRNLST_FAMILY WHERE JOURNAL_CARD_NO ='"+empNo+"' AND EKYC_DONE_YN ='N' ";
			     List<LabelBean> jrnlDtls = genericDao.executeSQLQueryList(LabelBean.class, selectQury.toString());
			     if(jrnlDtls != null && !jrnlDtls.isEmpty()){
			    	 for (LabelBean label : jrnlDtls) {
			             String enrollId = label.getID();
			             ehfJrnlstFamily = genericDao.findById(EhfJrnlstFamily.class, String.class, enrollId);
			             
			             if (ehfJrnlstFamily != null) {
			                 ehfJrnlstFamily.setEkycDoneYN("Y");
			                 ehfJrnlstFamily.setAbhaId(empVO.getAbhaNumber());
			                 ehfJrnlstFamily.setAbhaUpdUsr(empVO.getUSERID());
			                 ehfJrnlstFamily.setAbhaUpdDt(new Timestamp(System.currentTimeMillis()));
			                 genericDao.save(ehfJrnlstFamily);
			             }
			         }
				 }
			}
			else{
				selectQury = "SELECT ENROLL_ID as ID FROM EHF_ENROLLMENT_FAMILY WHERE EHF_CARD_NO ='"+empNo+"' AND EKYC_DONE_YN ='N' ";
		     	List<LabelBean> empDtls = genericDao.executeSQLQueryList(LabelBean.class, selectQury.toString());
		     	if(empDtls != null && !empDtls.isEmpty()){
		     		for (LabelBean label : empDtls) {
		                String enrollId = label.getID();
		                ehsEnrollmentFamily = genericDao.findById(EhfEnrollmentFamily.class, String.class, enrollId);
		                
		                if (ehsEnrollmentFamily != null) {
		                	ehsEnrollmentFamily.setEkycDoneYn("Y");
		                	ehsEnrollmentFamily.setAbhaId(empVO.getAbhaNumber());
		                	ehsEnrollmentFamily.setAbhaUpdUsr(empVO.getUSERID());
		                	ehsEnrollmentFamily.setAbhaUpdDt(new Timestamp(System.currentTimeMillis()));
		                    genericDao.save(ehsEnrollmentFamily);
		                }
		            }
				 }
			}
		return "SUCCESS";
		 }catch (Exception e) {
			 e.printStackTrace();
			 return "ERROR: " + e.getMessage();
		 }
	}

	 //Chandana - 8133 - for saving the photo in local
	 public String savePhoto(String photoString, String abhaNumber) {
		    String directoryPath = "/storageNAS-TS-Production/ABHAPhoto/";  
		    File dir = new File(directoryPath);
		    if (!dir.exists())
		        dir.mkdirs();
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		    String timestamp = dateFormat.format(new java.util.Date());
		    String filePath = directoryPath + "imgbase64_" + abhaNumber + timestamp + ".txt";
		    BufferedWriter writer = null;
		    try {
		        writer = new BufferedWriter(new FileWriter(filePath));
		        writer.write(photoString);   // writing Base64 string as text
		        writer.close();
		        System.out.println("Image Base64 saved successfully at: " + filePath);
		        return filePath;
		    } catch (IOException e) {
		        e.printStackTrace();
		    } finally {
		        if (writer != null) {
		            try { writer.close(); } catch (IOException e) {}
		        }
		    }
		    return filePath;
		}

	@Override
	public  Map<String, String> verifyAbhaBio(Map<String,String> lMap) {
		String abhaGenReg = lMap.get("abhaGenReg");
		Map<String, String> respMap = new HashMap<String,String>();
		EmployeeVO empVO = new EmployeeVO();
		String abhaNumber = "", prefAddress="",stateName="", status="";
		String firstName = "", lastName = "", middleName = "", gender="",dob="",mobile="",districtName="",pinCode="",address="",abhaType="", phrAddress="", isNew="";
		JSONObject jsonObj = new JSONObject();
		try {
			String serviceUrl = config.getString("internalAbhaApi");
			String methodName = "/m1/biometric";
			String url = serviceUrl + methodName;
			
			SSLSocketFactory sf=null;
			SSLContext context=null;
			context=SSLContext.getInstance("TLSv1.2");
			context.init(null, null, null);
			sf=new SSLSocketFactory(context, SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
			
			DefaultHttpClient client=new DefaultHttpClient();
			Scheme scheme=new Scheme("https",443,sf);
			client.getConnectionManager().getSchemeRegistry().register(scheme);
			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json");
			post.setHeader("Accept", "application/json");
			
			JSONObject requestBody = new JSONObject();
			//requestBody.put("txnId", lMap.get("abhaTxn"));
			
			requestBody.put("mobile", lMap.get("mobileNumber"));
	        requestBody.put("aadhaarNo", lMap.get("aadharNum"));
	        requestBody.put("fingerPrintAuthPid", lMap.get("reqBioData"));
	        requestBody.put("cardType", lMap.get("cardType"));
	        requestBody.put("cardValue", lMap.get("cardValue"));
	        requestBody.put("abhaGenReg", lMap.get("abhaGenReg"));
			
			StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
			post.setEntity(entity);
			HttpResponse response = client.execute(post);
			System.out.println("Response: "+response);
			
			int statusCode = response.getStatusLine().getStatusCode();
			String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("Raw Response: " + responseBody);
			
			jsonObj = new JSONObject(responseBody);
			
			System.out.println("Response Object::" + jsonObj.toString());
			if (responseBody == null || responseBody.trim().isEmpty()) {
				respMap.put("msg","Empty response from server.");
				return respMap;
			}
			try {
				jsonObj = new JSONObject(responseBody);
				//String status  = jsonObj.getString("statusCode");
				if (jsonObj.has("statusCode")) {
			        status = jsonObj.get("statusCode").toString();
			    } else if (jsonObj.has("status")) {
			        status = jsonObj.get("status").toString();
			    }
				System.out.println("status  :: "+status);
				respMap.put("statusCode",status);
				
				if("200".equals(status)){
					if (jsonObj.has("responseObject")) {
						JSONObject responseObject = jsonObj.getJSONObject("responseObject");
						abhaNumber = responseObject.getString("ABHANumber");
							abhaNumber = abhaNumber.replaceAll("-", "");
						firstName = responseObject.optString("firstName");
						middleName = responseObject.getString("middleName");
						lastName = responseObject.getString("lastName");
						gender = responseObject.getString("gender");
						dob = responseObject.getString("dob");
						stateName = responseObject.getString("stateName");
						mobile = responseObject.getString("mobile");
						districtName = responseObject.getString("districtName");
						pinCode = responseObject.getString("pinCode");
						address = responseObject.getString("address");
						abhaType = responseObject.getString("abhaType");
						//phrAddress = responseObject.getString("phrAddress");
						JSONArray phrAddressArr = responseObject.getJSONArray("phrAddress");
						System.out.println("phrAddress :: "+phrAddress);
						prefAddress = responseObject.getString("preferredAddress");
						String abhaPhoto = savePhoto(responseObject.getString("photo"), abhaNumber);
						System.out.println("responseObject::" + responseObject.toString());
						respMap.put("msg",abhaNumber);
						
						empVO.setMnum(lMap.get("cardValue"));
						empVO.setAbhaNumber(abhaNumber);
						empVO.setEmpType(lMap.get("cardType"));
						empVO.setAadhar(lMap.get("aadharNum"));
						empVO.setUSERID(lMap.get("userId"));
						empVO.setAddr(address);
						empVO.setPin(pinCode);
						empVO.setDistId(districtName);
						empVO.setFirstName(firstName);
						empVO.setMiddleName(middleName);
						empVO.setLastName(lastName);
						empVO.setGender(gender);
						empVO.setDob(dob);
						empVO.setId(abhaType);
						empVO.setAddr1(prefAddress);
						empVO.setAddr2(stateName);
						empVO.setId(isNew);
						empVO.setValue(abhaGenReg);
						empVO.setFile(abhaPhoto);
						empVO.setAuthType("BIO");
			            if(mobile == null)
			            	empVO.setMobileNo("");
			            else
			            	empVO.setMobileNo(mobile);
			            
			            saveAbhaDetails(empVO, phrAddressArr);
			            }
	                }
	                else if("500".equals(status)){
	                	abhaNumber =  jsonObj.getString("message");  
	                	System.out.println("abhaNumber=>"+abhaNumber);
	                	 respMap.put("msg",abhaNumber);
			             return respMap; 
	                }
	                else if (jsonObj.has("responseObject") && jsonObj.getJSONObject("responseObject").has("error")) {
					    JSONObject errorObj = jsonObj.getJSONObject("responseObject").getJSONObject("error");
					    String errMsg = errorObj.optString("message", "Unknown Error");
					    System.out.println("Error Message => " + errMsg);
					    respMap.put("msg", errMsg);
					    return respMap;
		                }
		        } catch (Exception e) {
		            System.out.println("Error: Response is not a valid JSON object.");
		            e.printStackTrace();
		        }
		        System.out.println("ABHA Number::" + abhaNumber);
		    } catch (Exception e) {
		        e.printStackTrace();
		        respMap.put("msg",e.getMessage());
		    }
		    return respMap;
		}
	
	public static String encrypt(String value) throws Exception {
        Key key = generateKey(KEY_STRING);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(value.getBytes("UTF-8")); // Use UTF-8 encoding
        return new String(Base64.encodeBase64(encryptedBytes)); // Convert byte array to Base64-encoded String
    }
	
	private static Key generateKey(String keyString) {
        byte[] keyData = keyString.getBytes();
        return new SecretKeySpec(keyData, ALGORITHM);
    }

	@Override
	public String checkAadhaar(String aadhaar,String cardType) {
		String result = null;
		try{
			String selectQury = null;
				selectQury = "SELECT IS_EKYC_VERIFIED ID FROM ABHA_RESPONSE WHERE AADHAAR_CARD ='"+aadhaar+"' ";
			List<LabelBean> empDtls = genericDao.executeSQLQueryList(LabelBean.class, selectQury.toString());
	        if (empDtls != null && !empDtls.isEmpty()) 
	        	result = empDtls.get(0).getID();
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
}
