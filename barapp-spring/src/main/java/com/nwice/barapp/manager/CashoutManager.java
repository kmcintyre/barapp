package com.nwice.barapp.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nwice.barapp.model.Cashout;


@Repository
@Transactional
public class CashoutManager {

	protected static Logger log = Logger.getLogger(CashoutManager.class);
	
	private static DateFormat queryFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	
	@Autowired
	private SessionFactory sessionFactory;

	public void saveOrUpdateCashout(Cashout co) {
		sessionFactory.getCurrentSession().saveOrUpdate(co);
	}
	
	public Cashout[] getAllCashouts() throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Cashout.class);
		List cashouts = crit.list();
		return (Cashout[])cashouts.toArray(new Cashout[cashouts.size()]);
	}
	
	public Cashout getPreviousByDate(Date d) {
		Query query = sessionFactory.getCurrentSession().getNamedQuery("oneBeforeSearch");
		query.setParameter("beforeDate", d);
		log.debug("beforeDate:" + d);
		List cashouts = query.list();
		return (Cashout)cashouts.iterator().next();			
	}
	
	public Cashout getByShiftDate(Date d) {
		log.info("getByShiftDate:" + d);
		Query query = sessionFactory.getCurrentSession().getNamedQuery("exactShift");
		query.setParameter("shiftDate", d);
		Cashout cashout = (Cashout)query.uniqueResult();
		return cashout;			
	}	

	/*
	public Cashout getByShiftDate(Date d) throws Exception {
		
		Cashout[] cos = getAllCashouts();
		for ( int i = 0; i < cos.length; i++ ) {
			if ( queryFormat.format( cos[i].getShift().getShiftDate() ).equals( queryFormat.format(d) ) ) {
				return cos[i];
			}
		}

		log.info("no shifts for shiftDate:" + queryFormat.format(d));
		
		throw new Exception("no shifts for shiftDate:" + queryFormat.format(d));				
	}
	*/	
	
	
	public Cashout[] getCashoutsByDates(Date d, Date d2) throws Exception {
		log.info("getCashoutsByDates:" + d.toString() + " " + d2.toString());
		
		Query query = sessionFactory.getCurrentSession().getNamedQuery("dateSearch");
		query.setParameter("fromDate", d);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d2);
		cal.add(Calendar.DATE, 1);
		
		query.setParameter("toDate", cal.getTime() );
		log.debug("ToDate:" + cal.getTime());
		List cashouts = query.list();
		return (Cashout[])cashouts.toArray(new Cashout[cashouts.size()]);
	}
	
	public Cashout getCashoutById(Integer cashoutId) {
		log.info("getCashoutById:" + cashoutId);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(Cashout.class);
		crit.add( Restrictions.eq("cashoutId",  cashoutId) );
		Object o = crit.uniqueResult();
		return (Cashout)o;
	}

		
}