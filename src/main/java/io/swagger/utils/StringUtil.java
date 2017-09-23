package io.swagger.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isContainChinese(String con) {
		if (null != con && !"".equals(con)) {
	    for (int i = 0; i < con.length(); i = i + 1) {
	        if (Pattern.compile("[\u4e00-\u9fa5]").matcher(
	                String.valueOf(con.charAt(i))).find()) {
	            return true;
	        }
	    }
		}
	    return false;
	}
	
	public static boolean isNumeric(String str)
	{
	    Pattern pattern = Pattern.compile("[0-9]*");
	    Matcher isNum = pattern.matcher(str);
	    if( !isNum.matches() ){
	       return false;
	    }
	       return true;
	}
	
	public static boolean isAlphabet(String con) {
	    if (null != con && !"".equals(con)) {
	        if (con.matches("^[A-Za-z]+$")) {
	            return true;
	        }
	    }
	    return false;
	}
	
	public static boolean isContainWord(String con,String key) {
		Pattern pattern = Pattern.compile("^.*"+key+".*$");
	    Matcher isContain = pattern.matcher(con);
	    if( !isContain.matches() ){
	       return false;
	    }
	       return true;
	}
	
	public static boolean isContainAlphabet(String con,String alphabetKey,String spliter) {
		Pattern pattern = Pattern.compile("^.*"+alphabetKey+".*$");
	    Matcher isContain = pattern.matcher(con);
	    if( isContain.matches() ){
	       return true;
	    }else{
	    	StringBuffer patternStr = new StringBuffer("^");
	    	for (int i = 0; i < alphabetKey.length(); i = i + 1) {
	    		if(i==alphabetKey.length()-1){
	    			patternStr.append(alphabetKey.charAt(i)+".*");
	    		}else{
	    		    patternStr.append(alphabetKey.charAt(i)+".*"+spliter);
	    		}
		    }
	    	patternStr.append("$");
	    	if (Pattern.compile(patternStr.toString()).matcher(con).matches()) {
	    		return true;
	    	}
        }
	    return false;
	}
	
	public static boolean isMobileNO(String mobiles){  

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  

		Matcher m = p.matcher(mobiles);  

		return m.matches();

	}  
	
}
