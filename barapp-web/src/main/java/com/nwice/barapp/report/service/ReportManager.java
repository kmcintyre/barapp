package com.nwice.barapp.report.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nwice.barapp.report.ReportConstant;

@Component
public class ReportManager  {

	private static final long millisPerHour = 60 * 60 * 1000;
	
	private static final Logger log = Logger.getLogger(ReportManager.class);
	
	@Autowired
	DataSource dataSource;
	
	public ReportManager() {
		log.info("Created reportManager");
	}
	
    public Connection getConnection() throws Exception {
        return dataSource.getConnection();
    }

    
    public List getMissingShifts(Date from, Date to) {  
    	log.info("getMissingShifts: from- " + from + " to-" + to);
    	Vector v = new Vector();
    	try {
	    	StringBuffer sb = new StringBuffer("select count(*) as \"count\", YEAR(b.shift_date) as \"year\", MONTH(b.shift_date) as \"month\", DAY(b.shift_date) as \"day\" from tbl_shift b ");
			if ( from != null || to != null) {
				sb.append(" where ");
				if ( from != null ) {
					sb.append(" b.shift_date > '" + ReportConstant.dbDateFormat.format(from) + " 00:00:00' ");
				
				}
				if ( from != null && to != null) {
					sb.append(" and ");
				}
				if ( to != null ) {
					sb.append(" b.shift_date < '" + ReportConstant.dbDateFormat.format(to) + " 00:00:00' ");
				
				}						
			}
			sb.append(" group by YEAR(b.shift_date), MONTH(b.shift_date), DAY(b.shift_date) order by YEAR(b.shift_date), MONTH(b.shift_date), DAY(b.shift_date) ");
			String query = sb.toString();
			
			log.info(query);
			
			Connection conn = getConnection(); 
		    PreparedStatement ps = conn.prepareStatement(query);		  
		    
		    ResultSet rs = ps.executeQuery();		    
		    while( rs.next() ) {
		    	int count = rs.getInt("count");
		    	if ( count < 2) {
		    		int year = rs.getInt("year");
		    		int month = rs.getInt("month");
		    		int day = rs.getInt("day");
		    		String addto = "" + year + "/" + month + "/" + day;
		    		log.debug("Adding:" + addto);
		    		v.add(addto);
		    	}
		    }
		    rs.close();
		    ps.close();
		    conn.close();
    	} catch (Exception e) {
    		log.error(e);
    	}
    	return v;
    }
    
	public Map getBartenders() {
		
			Map bartenders = Collections.synchronizedMap(new TreeMap());
			try {
				String query = "select barapp_user_id, lastname, firstname from tbl_bar_user where role = 'ROLE_BARTENDER' order by lastname";				 
				Connection conn = getConnection(); 
			    PreparedStatement ps = conn.prepareStatement(query.toString());
			    ResultSet rs = ps.executeQuery();
			  	while( rs.next() ) {	  		
			  		int userId = rs.getInt("barapp_user_id");
			  		String lastname  = rs.getString("lastname");
			  		String firstname  = rs.getString("firstname");
			  		bartenders.put(lastname + ", " + firstname.substring(0,1), new Integer(userId) );
			  	}
			  	rs.close();
			  	ps.close();
			  	conn.close();
			} catch (Exception e) {
				log.error("getBartenders" + e.toString());
			}
			return bartenders;
		
	}
    
}

