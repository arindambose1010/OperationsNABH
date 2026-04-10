package com.ahct.common.vo;

import java.io.Serializable;

public class SMSTemplate implements Serializable{
    private String templateId;
    private String templateContent;
    private String mobileNumber;
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
//	public String toJson() {
//        return "{" +
//                "\"mobileNumber\":\"" + mobileNumber + "\"," +
//                "\"templateContent\":\"" + templateContent + "\"," +
//                "\"templateId\":\"" + templateId + "\"" +
//                "}";
//    }
    
    
}
