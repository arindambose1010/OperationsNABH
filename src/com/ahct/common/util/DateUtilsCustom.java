package com.ahct.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtilsCustom {
	
	public static Date getConvertedDate(String requestDate) {
	    if (requestDate != null && !requestDate.trim().isEmpty()) {
	        SimpleDateFormat inputFormatter = new SimpleDateFormat("dd-MM-yyyy");
	        SimpleDateFormat outputFormatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
	        Date convertedDate = null;
	        try {
	            Date parsedDate = inputFormatter.parse(requestDate);
	            String formattedDate = outputFormatter.format(parsedDate);
	            convertedDate = outputFormatter.parse(formattedDate);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return convertedDate;
	    } else {
	        return null;
	    }
	}
	
	public static Date getCurrentDate(){
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");					
			Date currentDate = null;
			try {
				currentDate = formatter.parse(formatter.format(date));
			} catch (ParseException e) {				
				e.printStackTrace();
			}			
			return currentDate;		
	}
	
}
