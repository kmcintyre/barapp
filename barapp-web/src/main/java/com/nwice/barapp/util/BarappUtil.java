package com.nwice.barapp.util;

public class BarappUtil {
	
	public static String doubleToString(Double d) {
		String temp = d.toString();
		try {
			temp = temp.substring(0, temp.indexOf(".") + 3 );
		} catch (Exception e) {}		
		if ( temp.endsWith(".0")) return temp.substring(0, temp.length() - 2);
		return temp;
	}
		
}

