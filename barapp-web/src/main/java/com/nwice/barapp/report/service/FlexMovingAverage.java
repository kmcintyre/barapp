package com.nwice.barapp.report.service;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.Month;
import org.jfree.data.time.MovingAverage;
import org.jfree.data.time.Quarter;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.Week;
import org.jfree.data.time.Year;
import org.jfree.data.xy.XYDataset;

import com.nwice.barapp.report.ReportConstant;

import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.links.CategoryItemLinkGenerator;
import de.laures.cewolf.tooltips.CategoryToolTipGenerator;
import de.laures.cewolf.tooltips.XYToolTipGenerator;


public class FlexMovingAverage extends DateRangeAverage implements DatasetProducer, XYToolTipGenerator, Serializable {

	private static final Logger log = Logger.getLogger(DailyAverage.class);
	
	private TimeSeries timeSeries;
	int interval = ReportConstant.defaultMovingAverageInterval;

	
	public static String[] getIntervalPeriods(int i) {
		if ( i == 0 ) {
			return ReportConstant.movingAveragePeriod0;
		} else if ( i == 1 ) {
			return ReportConstant.movingAveragePeriod1;
		} else if ( i == 2 ) {
			return ReportConstant.movingAveragePeriod2;
		} else if ( i == 3 ) {
			return ReportConstant.movingAveragePeriod3;
		} else {
			return ReportConstant.movingAveragePeriod4;
		}
	}
	
	private List tips = Collections.synchronizedList(new LinkedList());
	
	public FlexMovingAverage(int interval) {
		this.interval = interval;
		this.interval = interval;
	}
	
	public String getBartenders() {
		StringBuffer sb = new StringBuffer(" a.cashout_id = b.cashout_id ");
		if ( getFromDate() != null ) {
			sb.append(" and b.shift_date > '" + ReportConstant.dbDateFormat.format(getFromDate()) + " 00:00:00' ");
		}
		if ( getToDate() != null ) {
			sb.append(" and b.shift_date < '" + ReportConstant.dbDateFormat.format(getToDate()) + " 23:59:00' ");
		}
		if ( getShift() == 1 ) {
			sb.append(" and b.ampm = 'AM' ");
		} else if ( getShift() == 2 ) {
			sb.append(" and b.ampm = 'PM' ");
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
		return sb.toString();
	}
	
	
	
	public String getViewString() {
		StringBuffer sb = new StringBuffer("");
		if ( getView() == 0 ) {
			sb.append(" a.total + a.payouts");
		} else if ( getView() == 1 ) {
			sb.append(" a.total ");
		} else if ( getView() == 2 ) {
			sb.append(" a.payouts  ");
		} else if ( getView() == 3 ) {
			sb.append(" a.overrings ");
		} else {
			sb.append(" a.shortage ");
		} 
		return sb.toString();
	}
		
	public TimeSeries getTimeSeries() {
		if ( timeSeries == null ) {
			
			//timeSeries = new TimeSeries(ReportConstant.viewArray[getView()], Day.class);
			
	  		if ( interval == 0 ) {
	  			timeSeries = new TimeSeries(ReportConstant.viewArray[getView()], Day.class);
	  		} else if ( interval == 1 ) {
	  			timeSeries = new TimeSeries(ReportConstant.viewArray[getView()], Week.class);	  			
	  		} else if ( interval == 2 ) {
	  			timeSeries = new TimeSeries(ReportConstant.viewArray[getView()], Month.class);
	  		} else if ( interval == 3 ) {
	  			timeSeries = new TimeSeries(ReportConstant.viewArray[getView()], Quarter.class);
	  		} else if ( interval == 4 ) {
	  			timeSeries = new TimeSeries(ReportConstant.viewArray[getView()], Year.class);
	  		}
			
	  		RegularTimePeriod addtp = null;
	  		double addvalue = 0d;
	  		
	  		try {
				String query = "select b.shift_date as 'date', IFNULL(sum( " + getViewString() + " ),0) as 'total' from vw_total a, tbl_shift b where " +
				getWhereString() + 				
				" group by YEAR(b.shift_date), MONTH(b.shift_date), DAY(b.shift_date) order by b.shift_date";
	    
				ReportManager m = new ReportManager(); 
				Connection conn = m.getConnection(); 
			    PreparedStatement ps = conn.prepareStatement(query);
			    
			    log.debug(query);
			    
			    ResultSet rs = ps.executeQuery();
			    while( rs.next() ) {
			  		Date d = rs.getDate("date");
			  		RegularTimePeriod t = null;
			  		if ( interval == 0 ) {
			  			t = new Day(d);
			  		} else if ( interval == 1 ) {
			  			t = new Week(d);
			  		} else if ( interval == 2 ) {
			  			t = new Month(d);
			  		} else if ( interval == 3 ) {
			  			t = new Quarter(d);
			  		} else if ( interval == 4 ) {
			  			t = new Year(d);
			  		}
			  		
			  		double value = rs.getDouble("total");
			  		
			  		if ( addtp == null) {
			  			addtp = t;
			  			addvalue = value;
			  		} else {
			  			if ( t.equals(addtp) ) {
			  				addvalue = addvalue + value;
			  			} else {
			  				timeSeries.add(addtp, addvalue);
			  				tips.add( addtp.toString() + " " + money.format(addvalue) );
				  			addtp = t;
				  			addvalue = value;
			  			}
			  		}
			  	}
			    if ( addtp != null) { 
			    	timeSeries.add(addtp, addvalue);
			    	tips.add( addtp.toString() + " " + money.format(addvalue) );
			    }

			    conn.close();
			} catch (Exception e) {
				log.error("error creating timeSeries:" + e);
			}
		}
		return timeSeries;
	}
	
	public String getShiftString() {
		if ( getShift() == 0 ) {
			return " IFNULL(a.total + a.payouts,0) as 'total' ";
		} else if ( getShift() == 1 ) {
			return " IFNULL(a.total,0) as 'total' ";
		} else if ( getShift() == 2 ) {
			return " IFNULL(a.payouts,0) as 'total' ";
		} else if ( getShift() == 3 ) {
			return " IFNULL(a.overrings,0) as 'total' ";
		} else {
			return " IFNULL(a.shortage,0) as 'total' ";
		} 
	}
	
	public Object init() throws Exception {	    
	    TimeSeriesCollection dataset = new TimeSeriesCollection(getTimeSeries());        
	    dataset.setDomainIsPointsInTime(true);	  	
        return dataset;
	}
	
	public Object produceDataset(Map params) {
		try {
			return init();
		} catch (Exception e) {
			log.debug(e);
		}
        final TimeSeries s1 = new TimeSeries("Random Data", Week.class);
        RegularTimePeriod t = new Week();
        double v = 100.0;
        for (int i = 0; i < 20; i++) {
            s1.add(t, v);
            v = v * (1 + ((Math.random() - 0.499) / 100.0));
            t = t.next();
        }

        TimeSeriesCollection dataset = new TimeSeriesCollection(s1);
        dataset.setDomainIsPointsInTime(true);
        return dataset;		
  	}

	
	public String getProducerId() {
		return "MovingAverageProducerId";
	}
	
	public boolean hasExpired(Map params, Date since) {		
        return true;
	}


	public String generateToolTip(XYDataset data, int arg1, int arg2) {
		return tips.get(arg2).toString();
	}
	
}      
