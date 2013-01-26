package com.nwice.barapp.calendartag.decorator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.jsp.PageContext;

import com.nwice.barapp.calendartag.util.CalendarTagUtil;
import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.ShiftWorker;
import com.nwice.barapp.util.BarappUtil;

public class DefaultCalendarDecorator implements CalendarDecorator {

    private final static String ACTIVE_DAY_STYLE = "calendarActiveDayStyle";
    private final static String ODD_MONTH_STYLE = "calendarMonthStyle";
    private final static String EVEN_MONTH_STYLE = "calendarMonthStyle";
    
    protected String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    protected String[] days = {"", "S", "M", "T", "W", "T", "F", "S"};

    private static DateFormat cformat = new SimpleDateFormat("MM/dd/yyyy");
    
    protected PageContext pageContext;
    protected Calendar calendar;
    protected Calendar start;
    protected Calendar end;

    public String getDayStyleClass(boolean isOddMonth, boolean isSelectedDay) {
        if (isSelectedDay) {
            return ACTIVE_DAY_STYLE;
        } else if(isOddMonth) {
            return ODD_MONTH_STYLE;
        } else {
            return EVEN_MONTH_STYLE;
        }
    }

    public void initializeCalendar() {
        // nothing done in default calendar decorator
    }
    
    public String getPreviousLink(String url) {
        return "<a href=\"" + url + "\"><<</a>";
    }
    
    public String getPreviousLink(String url,String append) {
        return "<a href=\"" + url + append + "\"><<</a>";
    }

    public String getNextLink(String url) {
        return "<a href=\"" + url + "\">>></a>";
    }
    
    public String getNextLink(String url,String append) {
        return "<a href=\"" + url + append + "\">>></a>";
    }

    public String printCashout(Cashout[] cashouts, Calendar c, boolean daily, boolean drop, boolean payouts, boolean bartenders, boolean tips, boolean amtotal, boolean pmtotal) {
    	StringBuffer sb = new StringBuffer("");    	
    	Cashout am = null;
    	Cashout pm = null;
    	try {
    		am = getAmShift(cashouts,c.getTime());
    	} catch (Exception e) {}    	
    	try {
    		pm = getPmShift(cashouts,c.getTime());
    	} catch (Exception e) {}
    	    	
    	if ( daily && (am != null || pm != null)) {
    		
    		double d = 0.0d;
    		if ( am != null ) {
    			d = d + am.getDrop().getTotal().doubleValue();
    			d = d + getSum( am.getPayouts() ).doubleValue();
    			d = d + getSWSum( am.getShift().getShiftWorkers() ).doubleValue();
    		}
    		if ( pm != null ) {
    			d = d + pm.getDrop().getTotal().doubleValue();
    			d = d + getSum( pm.getPayouts() ).doubleValue();
    			d = d + getSWSum( pm.getShift().getShiftWorkers() ).doubleValue();    			
    		}
    		sb.append("$" + BarappUtil.doubleToString( new Double(d)) + " Total");
    	}
    		
    	StringBuffer amsb = new StringBuffer("");
    	StringBuffer pmsb = new StringBuffer("");
    
    	if ( drop || payouts || bartenders || amtotal || pmtotal ) {
    		if ( amtotal) { 
    			amsb.append("<center>am:</center>");
    		}
    		if ( pmtotal ) {
    			pmsb.append("<center>pm:</center>");
    		}
	    	if ( amtotal && am != null) {
	    		amsb.append("$" + BarappUtil.doubleToString( new Double(am.getDrop().getTotal().doubleValue() + getSum( am.getPayouts() ).doubleValue() + getSWSum( am.getShift().getShiftWorkers() ).doubleValue()) ) + " Total<br>");
	    	}
	    	if ( pmtotal && pm != null) {
	    		pmsb.append("$" + BarappUtil.doubleToString( new Double(pm.getDrop().getTotal().doubleValue() + getSum( pm.getPayouts() ).doubleValue() + getSWSum( pm.getShift().getShiftWorkers() ).doubleValue()) ) + " Total<br>");
	    	}	    	
	    	if ( amtotal && am == null) {
	    		if ( c.getTime().compareTo( Calendar.getInstance().getTime()) < 0 ) {
	    			amsb.append("<a href=" + this.pageContext.getServletContext().getContextPath() + "/secure/cashout_do?shiftoveride=&start=yes&ampm=AM&create_date=" + cformat.format(c.getTime()) + ">Create AM Shift</a>");
	    		}
	        } 
	    	if ( pmtotal && pm == null) {
	    		if ( c.getTime().compareTo( Calendar.getInstance().getTime()) < 0 ) {
	    			pmsb.append("<a href=" + this.pageContext.getServletContext().getContextPath() + "/secure/cashout_do?shiftoveride=&start=yes&ampm=PM&create_date=" + cformat.format(c.getTime()) + ">Create PM Shift</a>");
	    		}
	        }
	    	
	    	if ( drop ) {
	    		if ( amtotal && am != null ) {
	    			amsb.append("$" + BarappUtil.doubleToString( am.getDrop().getTotal()) + " Drop<br>");
	    		}
	    		if ( pmtotal && pm != null ) {
	    			pmsb.append("$" + BarappUtil.doubleToString( pm.getDrop().getTotal()) + " Drop<br>");
	    		}
	    	}
	    	if ( payouts ) {
	    		if ( amtotal && am != null ) {
	    			amsb.append("$" + 
	    					BarappUtil.doubleToString( 
	    							new Double( 
	    									getSum(
	    									am.getPayouts()
	    									).doubleValue() 
	    									+ 
	    							getSWSum( 
	    									am.getShift().getShiftWorkers() 
	    									).doubleValue() 
	    									)
	    						) + " Payouts<br>");
	    		}
	    		if ( pmtotal && pm != null ) {
	    			pmsb.append("$" + 
	    					BarappUtil.doubleToString( 
	    							new Double( 
	    									getSum(
	    									pm.getPayouts()
	    									).doubleValue() 
	    									+ 
	    							getSWSum( 
	    									pm.getShift().getShiftWorkers() 
	    									).doubleValue() 
	    									)
	    						) + " Payouts<br>");    		
	    		}
	    	}    	
	    	if ( bartenders ) {
	    		
	    		if ( amtotal && am != null ) {
	    			amsb.append( PrintHelp.printBartenders(am,tips) );
	    		}
	    		if ( pmtotal && pm != null ) {
	    			pmsb.append( PrintHelp.printBartenders(pm,tips) );
	    			    		
	    		}
	    	}
    	}
    	return sb.toString() + amsb.toString() + pmsb.toString();
    }
    
    
    public String getCalendarTitle() {

        if (start.get(Calendar.MONTH) == end.get(Calendar.MONTH) &&
                start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {
            return months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.YEAR);
        } else {
            if (start.get(Calendar.YEAR) == end.get(Calendar.YEAR)) {
                return months[start.get(Calendar.MONTH)] + " - " +
                        months[end.get(Calendar.MONTH)] + " " +
                        calendar.get(Calendar.YEAR);
            } else {
                return months[start.get(Calendar.MONTH)] + " " +
                        start.get(Calendar.YEAR) + " - " +
                        months[end.get(Calendar.MONTH)] + " " +
                        calendar.get(Calendar.YEAR);
            }
        }

    }

    public String getWeekdayTitle(int day) {
        return days[day];
    }

    public String getEmptyDay() {
        return "";
    }

    public String getDay(String url) {
	  StringBuffer sb = new StringBuffer();
	  sb.append("<div style=display:inline;float:right>");
        if (calendar.get(Calendar.DATE) != 1 ||
                (start.get(Calendar.MONTH) == end.get(Calendar.MONTH) &&
                start.get(Calendar.YEAR) == end.get(Calendar.YEAR))) {
            sb.append("<a href=\"" + url + "\">" +
                    calendar.get(Calendar.DATE) + "</a>");
        } else {
            sb.append("<a href=\"" + url + "\">" +
                    calendar.get(Calendar.DATE) + "</a> " +
                    "<i>" + months[calendar.get(Calendar.MONTH)] + "</i>");
        }
	  sb.append("</div>");
	  return sb.toString();
    }

    public void setPageContext(PageContext pageContext) {
        this.pageContext = pageContext;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
    
    public void setStart(Calendar start) {
        this.start = start;
    }

    public void setEnd(Calendar end) {
        this.end = end;
    }
    
    private Double getSWSum(Collection l) {
    	double sum = 0.0d;
    	Iterator i = l.iterator();
    	while ( i.hasNext() ) {
    		try {
    			ShiftWorker dfo = (ShiftWorker)i.next();
        		sum = sum + dfo.getPayout().doubleValue();
        	} catch (Exception e) {}
        }    	
        return new Double(sum);
    }
    
    private Double getSum(Collection l) {
    	double sum = 0.0d;
    	Iterator i = l.iterator();
    	while ( i.hasNext() ) {
    		try {
    			DefaultFund dfo = (DefaultFund)i.next();
        		sum = sum + dfo.getTotal().doubleValue();
        	} catch (Exception e) {}
        }    	
        return new Double(sum);
    }
    
    public Cashout getAmShift(Cashout[] cashouts, Date d) {
        for (int i = 0;  i < cashouts.length; i++) {
        	if ( CalendarTagUtil.differenceInDays( 
        				CalendarTagUtil.getCalendar( cashouts[i].getShift().getShiftDate() ), 
        				CalendarTagUtil.getCalendar( d )
        			) == 0 
        			) {
        		if ( cashouts[i].getShift().getAmpm().equals("AM") ) {
        			return cashouts[i];
        		}
        	}
        }
        return null;
    }

    public boolean hasAmShift(Cashout[] cashouts, Date d) {
	  if ( getAmShift( cashouts, d ) == null ) { return false; }
	  return true;
    }

    public Cashout getPmShift(Cashout[] cashouts, Date d) {
        for (int i = 0;  i < cashouts.length; i++) {
        	if ( CalendarTagUtil.differenceInDays( 
    				CalendarTagUtil.getCalendar( cashouts[i].getShift().getShiftDate() ), 
    				CalendarTagUtil.getCalendar( d )
        			) == 0 
    			) {
        		if ( cashouts[i].getShift().getAmpm().equals("PM") ) {
        			return cashouts[i];
        		}
        	}
        }
        return null;
    }

    public boolean hasPmShift(Cashout[] cashouts, Date d) {
	  if ( getPmShift( cashouts, d ) == null ) { return false; }
	  return true;
    }
    
}

