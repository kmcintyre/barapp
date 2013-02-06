package com.nwice.barapp.report.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nwice.barapp.report.ReportConstant;
import com.nwice.barapp.report.ReportUtil;

import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.links.CategoryItemLinkGenerator;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;

@Component
public class DailyAverage extends DateRangeAverage implements DatasetProducer, CategoryToolTipGenerator, CategoryItemLinkGenerator, Serializable {


	private static final Logger log = Logger.getLogger(DailyAverage.class);

	@Autowired
	ReportManager reportManager;
	
	private String allDays = "Average";
	private String[] categories;
	private String[] seriesNames;
	private String[] defaultSeriesNames = { "AM", "PM" };

	private Double[][] startValues; 
	private Double[][] endValues; 
	
	private String[][] tipValues;

	private int[] showDays, showBartenders = null;
	//private int shift = ReportConstant.defaultDailyAverageShift;
	//private int view = ReportConstant.defaultDailyAverageView;
		
	public void setShowDays(int[] days) {
		this.showDays = days; 
	}
	
	public void setShowBartenders(int[] showBartenders) {
		this.showBartenders = showBartenders; 
	}
	
	public String getBartenders() {
		StringBuffer sb = new StringBuffer(" a.cashout_id = b.cashout_id ");
		if ( getFromDate() != null ) {
			sb.append(" and b.shift_date > '" + ReportConstant.dbDateFormat.format(getFromDate()) + " 00:00:00' ");
		}
		if ( getToDate() != null ) {
			sb.append(" and b.shift_date < '" + ReportConstant.dbDateFormat.format(getToDate()) + " 23:59:00' ");
		}		
		return sb.toString();
	}

	
	public String getWhereString() {
		StringBuffer sb = new StringBuffer(" a.cashout_id = b.cashout_id ");
		if ( getFromDate() != null ) {
			sb.append(" and b.shift_date > '" + ReportConstant.dbDateFormat.format(getFromDate()) + " 00:00:00' ");
		}
		if ( getToDate() != null ) {
			sb.append(" and b.shift_date < '" + ReportConstant.dbDateFormat.format(getToDate()) + " 00:00:00' ");
		}
		if ( getShift() == 1 ) {
			sb.append(" and b.ampm = 'AM' ");
		} else if ( getShift() == 2 ) {
			sb.append(" and b.ampm = 'PM' ");
		}
		if ( showDays != null ) {
			sb.append(" and DAYOFWEEK(b.shift_date) in ( ");
			for (int i = 0; i < showDays.length; i++ ) {
				sb.append( showDays[i] + 1 );
				if ( i + 1 < showDays.length ) {
					sb.append( "," );
				}
			}			
			sb.append(" ) ");
		}
		
		return sb.toString();
	}
	
	
	public String getViewString() {
		StringBuffer sb = new StringBuffer("");
		if ( getView() == 0 ) {
			sb.append(" IFNULL(avg(a.total + a.payouts),0) ");
		} else if ( getView() == 1 ) {
			sb.append(" IFNULL(avg(a.total),0) ");
		} else if ( getView() == 2 ) {
			sb.append(" IFNULL(avg(a.payouts),0) ");
		} else if ( getView() == 3 ) {
			sb.append(" IFNULL(avg(a.overrings),0) ");
		} else if ( getView() == 4 ) {
			sb.append(" IFNULL(avg(a.overrings),0) ");
		} else if ( getView() == 5 ) {
			sb.append(" IFNULL(avg(a.tips),0) ");
		} else {
			sb.append(" avg(a.buybacks) ");
		} 
		sb.append("as 'value' ");
		return sb.toString();
	}
	
	private String getBartender(Map m, int i) {
		Iterator iter = m.keySet().iterator();
		while ( iter.hasNext() ) {
			String s = iter.next().toString();
			Integer userId = (Integer)m.get(s);
			if ( userId.intValue() == i ) {
				return s;
			}
		}
		return "Error";
	}
		
	public void init() throws Exception {
		boolean includeaverage = true;
		if ( showDays == null ) {
			categories = new String[8];
			categories[0] = allDays;
			for (int i = 0; i < ReportConstant.week.length; i++ ) {					
				categories[i+1] = ReportConstant.week[i];
			}			
		} else {
			categories = new String[ 1 + showDays.length];
			int adder = 0;
			if ( showDays.length > 1 ) {
				adder = 1;
				categories[0] = allDays;
			} else {
				categories = new String[1];
				includeaverage = false;
			}
			for (int i = 0; i < showDays.length; i++ ) {	
				categories[i + adder] = ReportConstant.week[showDays[i]];
			}				
		} 
		if ( showBartenders != null ) {
			if ( getShift() == 0 ) {
				seriesNames = new String[(showBartenders.length + 1) * 2];
				seriesNames[0] = defaultSeriesNames[0];
				seriesNames[1] = defaultSeriesNames[1];
				for ( int i = 0; i < showBartenders.length; i++) {
					int j = i + 1;				
					int k = showBartenders[i];
					seriesNames[j*2] = defaultSeriesNames[0] + " " + getBartender(reportManager.getBartenders(),k);
					seriesNames[j*2 + 1] = defaultSeriesNames[1] + " " + getBartender(reportManager.getBartenders(),k);
				}
			} else if ( getShift() == 1 ) {
				seriesNames = new String[showBartenders.length + 1];
				seriesNames[0] = defaultSeriesNames[0];
				for ( int i = 0; i < showBartenders.length; i++) {
					int j = i + 1;				
					int k = showBartenders[i];
					seriesNames[j] = defaultSeriesNames[0] + " " + getBartender(reportManager.getBartenders(),k);
				}

			} else if ( getShift() == 2 ) {
				seriesNames = new String[showBartenders.length + 1];
				seriesNames[0] = defaultSeriesNames[1];
				for ( int i = 0; i < showBartenders.length; i++) {
					int j = i + 1;				
					int k = showBartenders[i];
					seriesNames[j] = defaultSeriesNames[1] + " " + getBartender(reportManager.getBartenders(),k);
				}
			} 
			
		} else {
			if ( getShift() == 0 ) {
				seriesNames = defaultSeriesNames;
			} else if ( getShift() == 1 ) {
				seriesNames = new String[1];
				seriesNames[0] = defaultSeriesNames[0];
			} else if ( getShift() == 2 ) {
				seriesNames = new String[1];
				seriesNames[0] = defaultSeriesNames[1];
			} 
		}
		
		
		log.debug("series" + seriesNames.length + " categories" + endValues);
		
		startValues = new Double[seriesNames.length][categories.length];
		endValues = new Double[seriesNames.length][categories.length];		
		tipValues = new String[seriesNames.length][categories.length];
		
		StringBuffer query = new StringBuffer("select count(*) as 'count', DAYOFWEEK(b.shift_date) as 'day', b.ampm, ");
		query.append(getViewString());
		query.append("from vw_total a, tbl_shift b where ");
		query.append(getWhereString()); 				
		query.append(" group by DAYOFWEEK(b.shift_date), b.ampm");
		
		StringBuffer query2 = new StringBuffer("select count(*) as 'count', b.ampm, ");
		query2.append(getViewString());
		query2.append("from vw_total a, tbl_shift b where ");
		query2.append(getWhereString()); 				
		query2.append(" group by b.ampm order by b.ampm");
	    
		log.debug(query.toString());
		
		ReportManager m = new ReportManager(); 
		Connection conn = m.getConnection(); 
	    PreparedStatement ps = conn.prepareStatement(query.toString());
	    
	    ResultSet rs = ps.executeQuery();
	    	    
	  	while( rs.next() ) {	  		
	  		
	  		int day = rs.getInt("day");
	  		String s = rs.getString("ampm");	  
	  		int count = rs.getInt("count");

	  		int x = ReportUtil.getStingInstance(seriesNames, s);
	  		int y = ReportUtil.getStingInstance(categories, ReportConstant.week[day - 1]);	  		
	  		
	  		startValues[x][y] = new Double(0d);	  		
	  		
	  		double t = rs.getDouble("value");
	  		endValues[x][y] = new Double(t);	  		
	  		tipValues[x][y] = "shift(s):" + count + " " + money.format(t);
	  		
	  	}
	  	rs.close();
	  	ps.close();
  		if ( includeaverage ) {
		  	startValues[0][0] = new Double(0d);
			
		  	log.debug(query2.toString());
		    PreparedStatement ps2 = conn.prepareStatement(query2.toString());
		    
		    ResultSet rs2 = ps2.executeQuery();
		    
		  	while( rs2.next() ) {
		  		
		  		
		  		String s = rs2.getString("ampm");	  
		  		int x = ReportUtil.getStingInstance(seriesNames, s);
		  		
		  		int count = rs2.getInt("count");
		  		
		  		startValues[x][0] = new Double(0d);	  		
		  		
		  		double t = rs2.getDouble("value");
		  		endValues[x][0] = new Double(t);	  		
		  		tipValues[x][0] = "shift(s):" + count + " " + money.format(t);	  		
		  	}
		  	rs2.close();
		  	ps2.close();
  		}
	  	
	  	if ( showBartenders != null ) {

	  		for ( int i = 0; i < showBartenders.length; i++) {
	  			int k = showBartenders[i];
		  		
				StringBuffer query3 = new StringBuffer("select count(*) as 'count', DAYOFWEEK(b.shift_date) as 'day', b.ampm, ");
				query3.append(getViewString());
				query3.append("from vw_total a, tbl_shift b, tbl_shift_worker c where ");
				query3.append(getWhereString()); 	
				query3.append(" and b.shift_id = c.shift_id ");
				query3.append(" and c.user = " + k + " ");				 
				query3.append(" group by DAYOFWEEK(b.shift_date), b.ampm order by b.ampm");
				
				log.debug(query3.toString());
				
				PreparedStatement ps3 = conn.prepareStatement(query3.toString());
				ResultSet rs3 = ps3.executeQuery();
				
			  	while( rs3.next() ) {	  		
			  		
			  		int day = rs3.getInt("day");
			  		String s = rs3.getString("ampm") + " " + getBartender(reportManager.getBartenders(),k);	  
			  		int count = rs3.getInt("count");
			  		
			  		log.debug( day + " " + s + " " + count );
			  		int x = ReportUtil.getStingInstance(seriesNames, s);
			  		int y = ReportUtil.getStingInstance(categories, ReportConstant.week[day - 1]);
			  		
			  		startValues[x][y] = new Double(0d);
			  		
			  		double t = rs3.getDouble("value");
			  		
			  		endValues[x][y] = new Double(t);
			  		
			  		tipValues[x][y] = "shift(s):" + count + " " + money.format(t);
			  		
			  	}		
			  	rs3.close();
			  	ps3.close();
			  	
			  	if ( includeaverage ) {
					StringBuffer query4 = new StringBuffer("select count(*) as 'count', b.ampm, ");
					query4.append(getViewString());
					query4.append("from vw_total a, tbl_shift b, tbl_shift_worker c where ");
					query4.append(getWhereString()); 	
					query4.append(" and b.shift_id = c.shift_id ");
					query4.append(" and c.user = " + k + " ");				 
					query4.append(" group by b.ampm order by b.ampm");
					
					log.debug(query4.toString());
					
					PreparedStatement ps4 = conn.prepareStatement(query4.toString());
					ResultSet rs4 = ps4.executeQuery();
					
				  	while( rs4.next() ) {	  		
				  		
				  		String s = rs4.getString("ampm") + " " + getBartender(reportManager.getBartenders(),k);	  
				  		int count = rs4.getInt("count");
				  		
	
				  		int x = ReportUtil.getStingInstance(seriesNames, s);
				  		int y = 0;
				  		
				  		startValues[x][y] = new Double(0d);
				  		
				  		double t = rs4.getDouble("value");
				  		
				  		endValues[x][y] = new Double(t);
				  		tipValues[x][y] = "shift(s):" + count + " " + money.format(t);
				  		
				  	}		
				  	rs4.close();
				  	ps4.close();
			  	}			  	
	  		}
	  	}	  	
	  	
	  	conn.close();
	}
	
	public Object produceDataset(Map params) {
		try {
			init();
		} catch (Exception e) {
			log.debug(e);
		}
		DefaultIntervalCategoryDataset ds = new DefaultIntervalCategoryDataset(seriesNames, categories, startValues, endValues);
	    return ds;
  	}

	
	public String getProducerId() {
		return "dailyAverageProducerId";
	}
	
	public boolean hasExpired(Map params, Date since) {		
        log.debug(getClass().getName() + "hasExpired()");
        return true;
	}

	public String generateToolTip(CategoryDataset arg0, int arg1, int arg2) {
		log.debug("generateToolTip" + arg1 + " " + arg2);
		if ( tipValues[arg1][arg2] != null ) return tipValues[arg1][arg2];
		return null;
	}
	
	 public String generateLink(CategoryDataset arg0, int arg1, int arg2) {
			log.debug("generateLink" + arg1 + " " + arg2);
			if ( tipValues[arg1][arg2] != null ) return tipValues[arg1][arg2];
			return null;
	 }

	public String generateLink(Object arg0, int arg1, Object arg2) {
		log.debug("generateLink arg0:" + arg0 + " arg1:" + arg1 + " arg2:" + arg2);
		//if ( tipValues[arg1][arg2] != null ) return tipValues[arg1][arg2];
		DefaultIntervalCategoryDataset dicd = (DefaultIntervalCategoryDataset)arg0;
		int ampm = arg1 + 1;
		if ( arg2.toString().equals("Average") ) {
			if ( getShift() == 0 ) {
				return "javascript:document.report.daily_average_shift.selectedIndex = " + ampm + ";document.report.submit()";
			} else {
				return null;
			}
		} else {			
			int day = ReportUtil.getStingInstance(ReportConstant.week,arg2.toString());
			return "javascript:document.report.daily_average_shift.selectedIndex = " + ampm + ";document.report.daily_average_days.selectedIndex = " + day + ";document.report.submit()";
		}
		//generateLink arg0:" + dicd.getRowCount()	+ "-" + dicd.getColumnCount() + " arg1:" + arg1 + " arg2:" + arg2 + "')";
		
		//return null;
	}
	
}      
