package com.nwice.barapp.servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.ShiftManager;
import com.nwice.barapp.model.Shift;

/**
 * @web.servlet
 *      name="ShiftServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/shift.do"
 **/

public class ShiftServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(ShiftServlet.class);
	
	public static final DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final DateFormat justDayFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat displayFormat = new SimpleDateFormat("EEE, MM-dd");
	/*
	private UserManager userManager;
	private ShiftManager shiftManager;
	*/
	private static long one_day = 1000 * 60 * 60 * 24;
	
	public static boolean isAmShift(Shift so) throws Exception {
		log.info("isAmShift:" + so.getShiftDate() );
		if ( so.getAmpm().equals("AM")  ) {
			log.info("true");
			return true;			
		}
		log.info("false");
		return false;
	}
	
	public static boolean needShift() throws Exception {
		Date now = Calendar.getInstance().getTime();
		if ( Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ) {
			String yesterday = justDayFormat.format(new Date( now.getTime() - one_day ));
			Date y = mysqlDateFormat.parse(yesterday + " 20:00:00");
			Shift so = (new ShiftManager()).getShiftByDate(y);
			if ( so != null ) {
				log.debug("PM Shift already exists:" + y);
				return false;
			}
		} else {			
			String today = justDayFormat.format(now);
			Date x = mysqlDateFormat.parse(today + " 14:00:00");
			Shift so = (new ShiftManager()).getShiftByDate(x);
			if ( so != null ) {
					log.debug("AM Shift already exists:" + today);
					return false;					
			}
		}		
		return true;
	}
	
	public static Shift getNewShift() throws Exception {
		
		Shift so = new Shift();
		
		Date now = Calendar.getInstance().getTime();
		
		if ( Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ) {
			String yesterday = justDayFormat.format(new Date( now.getTime() - one_day ));			
			so.setShiftDate( mysqlDateFormat.parse(yesterday + " 20:00:00") );
			log.debug("Init PM Shift" + yesterday + " 20:00:00");
			so.setAmpm("PM");
			
		} else {
			
			String today = justDayFormat.format(now);
			so.setShiftDate( mysqlDateFormat.parse(today + " 14:00:00") );
			log.debug("Init AM Shift" + today + " 14:00:00");
			so.setAmpm("AM");
		}
		return so;
	}
	/*
	public void init() throws ServletException {
		try {
			userManager = new UserManager();
			shiftManager = new ShiftManager();
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
    	try {
    		CashoutObject co = getCashout(request.getSession());
    		if ( request.getParameter("del_shiftworker") != null ) {
    			int i = new Integer(request.getParameter("del_shiftworker")).intValue();
    			co.getShift().getShiftWorkers().remove(i);
    		} else if ( request.getParameter("add_shiftworker") != null ) {     			
    			Shift so = co.getShift();
    			ShiftWorkerObject swo = new ShiftWorkerObject();
    			swo.setUserObject( userManager.getUserById(new Integer(request.getParameter("bartender"))) );
    			
    			swo.setTips(new Double(request.getParameter("tips")));
    			so.addShiftWorker(swo);
    			
    			 so.setShiftWorkers(Collections.synchronizedList(new ArrayList()));
    			 String[] users = request.getParameterValues("bartenders");
    			 for ( int i = 0; i < users.length; i++ ) {
    			 so.addUser( userManager.getUserById(new Integer(users[i]) ) );
    			 }
    			 
    		}
    		if ( co.getCashoutId() != null ) {
    			cashoutManager.persist(co.getShift());
    		}
    	} catch (Exception e) {
    		log.error(e);
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher("/secure/index.jsp");
    	rd.include(request, response); 
    }
	
	*/
}