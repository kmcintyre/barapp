package com.nwice.barapp.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nwice.barapp.model.Shift;

@Repository
@Transactional
public class ShiftManager {

	protected static Logger log = Logger.getLogger(ShiftManager.class);
	
	public static final DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final DateFormat justDayFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final DateFormat displayFormat = new SimpleDateFormat("EEE, MM-dd");
	
	public static long one_day = 1000 * 60 * 60 * 24;
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public Shift getShiftByDate(Date d) throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Shift.class);
		crit.add( Restrictions.eq("shiftDate",  d) );
		Object o = crit.uniqueResult();
		return (Shift)o;
	}
	
	public Shift getPreviousByDate(Date d) throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Shift.class);
		crit.add( Restrictions.eq("shiftDate",  d) );
		Object o = crit.uniqueResult();
		return (Shift)o;
	}
	
	public boolean needShift() throws Exception {
		Date now = Calendar.getInstance().getTime();
		if ( Calendar.getInstance().get(Calendar.AM_PM) == Calendar.AM ) {
			String yesterday = justDayFormat.format(new Date( now.getTime() - one_day ));
			Date y = mysqlDateFormat.parse(yesterday + " 20:00:00");
			Shift so = getShiftByDate(y);
			if ( so != null ) {
				log.debug("PM Shift already exists:" + y);
				return false;
			}
		} else {			
			String today = justDayFormat.format(now);
			Date x = mysqlDateFormat.parse(today + " 14:00:00");
			Shift so = getShiftByDate(x);
			if ( so != null ) {
					log.debug("AM Shift already exists:" + today);
					return false;					
			}
		}		
		return true;
	}
	
}