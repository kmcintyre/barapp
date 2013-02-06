package com.nwice.barapp.report;

import org.apache.log4j.Logger;

public class ReportUtil {
	
	private static final Logger log = Logger.getLogger(ReportUtil.class);
	
	public static boolean arrayContains(Object o, Object[] obs) {
		if ( obs == null ) return false;
		for ( int i = 0; i < obs.length; i++) {
			if ( o.equals(obs[i]) ) return true;
		}
		return false;
	}
	
	public static int getStingInstance(String[] strings, String s) {
		for (int i = 0; i < strings.length;i++) {
			if ( s.equals(strings[i]) ) { return i; }
		}
		return 0;
	}
	
	public static int[] convertArray(Integer[] ints) {
		int[] ret = new int[ints.length];
		for (int i = 0; i < ints.length;i++) {
			log.debug("showDay:" + ints[i].intValue() );
			ret[i] = ints[i].intValue();			
		}
		return ret;
	}
		
	
}