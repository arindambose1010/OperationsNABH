package com.ahct.common.util;

import java.util.Date;

public class ObjectUtils {
	
	public static String getString(String obj){
		if(obj != null && !obj.trim().isEmpty()){
			return obj;
		}else{
			return "";
		}
	}
	
	public static Date getDate(Date obj){
		if(obj != null){
			return obj;
		}else{
			return new Date();
		}
	}
	
}
