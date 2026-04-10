package com.ahct.claims.util;
public class IndianLakhs 
{
    public IndianLakhs() 
    {
    }
    public static String IndianFormatLakhs(String value)
    {   
        String stringValue = value;
        String lakhsPart,thousendPart,hunderedPart,commPartScnd,stringValue1;
        String thusndFormat=null,hundredFormat=null;
        String Valueee=null;  
        stringValue=stringValue.replaceAll(",", "");
        if(stringValue!=null && !stringValue.equals(""))
        {
            stringValue=stringValue.concat(".00");              
            int dotLocation = stringValue.indexOf(".");        
            String integralPart = stringValue.substring(0, dotLocation);           
            String checkminus = stringValue.substring(0, 1);            
            if(checkminus.equals("-"))
            {
                stringValue=stringValue.substring(1, stringValue.length());            
                int dotMinusLocation = stringValue.indexOf(".");
                String integralMinusPart = stringValue.substring(0, dotMinusLocation);
                if ((integralMinusPart.length() > 7) )
                {            
                    lakhsPart = integralMinusPart.substring(0,integralMinusPart.length()-7);         
                    thousendPart = integralMinusPart.substring(integralMinusPart.length()-7,integralMinusPart.length());         
                    stringValue1=lakhsPart;
                    if(thousendPart.length()>5)
                    {
                        thusndFormat = thousendPart.substring(0,thousendPart.length()-5); 
                    }
                    hunderedPart = thousendPart.substring(thousendPart.length()-5,thousendPart.length());
                    Valueee=lakhsPart+","+thusndFormat;                
                    if(hunderedPart.length()>3)
                    {
                        hundredFormat = hunderedPart.substring(0,hunderedPart.length()-3);                
                        hunderedPart = hunderedPart.substring(hunderedPart.length()-3,hunderedPart.length());
                        Valueee=lakhsPart+","+thusndFormat+","+hundredFormat+","+hunderedPart;                
                    }
                    stringValue="-".concat(Valueee);
                }
                else if ((integralMinusPart.length() > 5) )
                {            
                    lakhsPart = integralMinusPart.substring(0,integralMinusPart.length()-5);            
                    thousendPart = integralMinusPart.substring(integralMinusPart.length()-5,integralMinusPart.length());            
                    stringValue1=lakhsPart;
                    if(thousendPart.length()>3)
                    {
                        hunderedPart = thousendPart.substring(0,thousendPart.length()-3);            
                        commPartScnd = thousendPart.substring(thousendPart.length()-3,thousendPart.length());
                        Valueee=lakhsPart+","+hunderedPart+","+commPartScnd;
                    }
                    stringValue="-".concat(Valueee);
                }
                else if (integralMinusPart.length() > 3) 
                {
                    String checks = integralMinusPart.substring(0,integralMinusPart.length()-3);            
                    String check = integralMinusPart.substring(integralMinusPart.length()-3,integralMinusPart.length());            
                    stringValue=checks+","+check;
                    stringValue="-".concat(stringValue);
                }
                else if (integralMinusPart.length() == 3 || integralMinusPart.length() == 2 || integralMinusPart.length() == 1)
                {       
                    stringValue=integralMinusPart;
                    stringValue="-".concat(stringValue);       
                }            
                else
                {
                    int testval=0;
                    String impval=String.valueOf(testval); 
                    integralPart=impval;
                    stringValue=integralPart+".00";
                    stringValue="-".concat(stringValue);            
                } 
            }
            else  if ((integralPart.length() > 7) )
            {         
                lakhsPart = integralPart.substring(0,integralPart.length()-7);         
                thousendPart = integralPart.substring(integralPart.length()-7,integralPart.length());         
                stringValue1=lakhsPart;
                if(thousendPart.length()>5)
                {
                    thusndFormat = thousendPart.substring(0,thousendPart.length()-5); 
                }
                hunderedPart = thousendPart.substring(thousendPart.length()-5,thousendPart.length());
                Valueee=lakhsPart+","+thusndFormat;
                
                if(hunderedPart.length()>3)
                {
                    hundredFormat = hunderedPart.substring(0,hunderedPart.length()-3);            
                    hunderedPart = hunderedPart.substring(hunderedPart.length()-3,hunderedPart.length());
                    Valueee=lakhsPart+","+thusndFormat+","+hundredFormat+","+hunderedPart;            
                }
                stringValue=Valueee;
            }
            else if ((integralPart.length() > 5) )
            {        
                lakhsPart = integralPart.substring(0,integralPart.length()-5);            
                thousendPart = integralPart.substring(integralPart.length()-5,integralPart.length());            
                stringValue1=lakhsPart;
                if(thousendPart.length()>3)
                {
                    hunderedPart = thousendPart.substring(0,thousendPart.length()-3);            
                    commPartScnd = thousendPart.substring(thousendPart.length()-3,thousendPart.length());
                    Valueee=lakhsPart+","+hunderedPart+","+commPartScnd;
                }
                stringValue=Valueee;
            }
            else if (integralPart.length() > 3) 
            {
                String checks = integralPart.substring(0,integralPart.length()-3);            
                String check = integralPart.substring(integralPart.length()-3,integralPart.length());            
                stringValue=checks+","+check;
            }
            else if (integralPart.length() == 3 || integralPart.length() == 2 || integralPart.length() == 1)
            {
                stringValue=integralPart;        
            }
            else
            {
                int testval=0;
                String impval=String.valueOf(testval); 
                integralPart=impval;
                stringValue=integralPart+".00";        
            }
        }		 
        return stringValue;   
    }    
}
