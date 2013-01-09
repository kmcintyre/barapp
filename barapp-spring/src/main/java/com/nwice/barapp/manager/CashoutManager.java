package com.nwice.barapp.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nwice.barapp.model.Cashout;

public class CashoutManager {

	protected static Logger log = Logger.getLogger(CashoutManager.class);
	
	private static DateFormat inFormat = new SimpleDateFormat("MM/dd/yyyy");
	private static DateFormat outFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm");
	private static DateFormat queryFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	
	private static String first = new String(" 00:00");
	private static String second = new String(" 23:59");
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public Cashout[] getAllCashouts() throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Cashout.class);
		List cashouts = crit.list();
		return (Cashout[])cashouts.toArray(new Cashout[cashouts.size()]);
	}
	
	public Cashout getPreviousByDate(Date d) throws Exception {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("oneBeforeSearch");
		query.setParameter("beforeDate", d, Hibernate.DATE);
		log.debug("beforeDate:" + d);
		List cashouts = query.list();
		return (Cashout)cashouts.iterator().next();
				
	}
	
	public Cashout getByShiftDate(Date d) throws Exception {
		
		Cashout[] cos = getAllCashouts();
		for ( int i = 0; i < cos.length; i++ ) {
			if ( queryFormat.format( cos[i].getShift().getShiftDate() ).equals( queryFormat.format(d) ) ) {
				return cos[i];
			}
		}

		log.debug("no shifts for shiftDate:" + queryFormat.format(d));
		
		throw new Exception("no shifts for shiftDate:" + queryFormat.format(d));				
	}	
	
	
	public Cashout[] getCashoutsByDates(Date d, Date d2) throws Exception {
		String dstring = inFormat.format(d);
		Date qd = outFormat.parse( dstring + first );
		
		String dstring2 = inFormat.format(d2);
		Date qd2 = outFormat.parse( dstring2 + second );
		
		qd2 = new Date(qd2.getTime() + 86400000 ); 
		
		Query query = sessionFactory.getCurrentSession().getNamedQuery("dateSearch");
		query.setParameter("fromDate", qd, Hibernate.DATE);
		log.debug("FromDate:" + qd);
		query.setParameter("toDate", qd2, Hibernate.DATE);
		log.debug("ToDate:" + qd2);
		List cashouts = query.list();
		return (Cashout[])cashouts.toArray(new Cashout[cashouts.size()]);
	}
	
	public Cashout getCashoutById(Integer cashoutId) throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Cashout.class);
		crit.add( Restrictions.eq("cashoutId",  cashoutId) );
		Object o = crit.uniqueResult();
		return (Cashout)o;
	}

		
}