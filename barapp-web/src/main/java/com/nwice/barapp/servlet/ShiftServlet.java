package com.nwice.barapp.servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.manager.ShiftManager;
import com.nwice.barapp.manager.UserManager;
import com.nwice.barapp.model.Shift;

public class ShiftServlet {
	
	protected static Logger log = Logger.getLogger(ShiftServlet.class);

	public static boolean isAmShift(Shift so) throws Exception {
		log.info("isAmShift:" + so.getShiftDate() );
		if ( so.getAmpm().equals("AM")  ) {
			log.info("true");
			return true;			
		}
		log.info("false");
		return false;
	}
	
	
	public static Shift getNewShift() throws Exception {
		
		Shift so = new Shift();
		
		Date now = Calendar.getInstance().getTime();
		
		if ( Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ) {
			String yesterday = ShiftManager.justDayFormat.format(new Date( now.getTime() - ShiftManager.one_day ));			
			so.setShiftDate( ShiftManager.mysqlDateFormat.parse(yesterday + " 20:00:00") );
			log.debug("Init PM Shift" + yesterday + " 20:00:00");
			so.setAmpm("PM");
			
		} else {
			
			String today = ShiftManager.justDayFormat.format(now);
			so.setShiftDate( ShiftManager.mysqlDateFormat.parse(today + " 14:00:00") );
			log.debug("Init AM Shift" + today + " 14:00:00");
			so.setAmpm("AM");
		}
		return so;
	}
}