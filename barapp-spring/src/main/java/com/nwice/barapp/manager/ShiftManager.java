package com.nwice.barapp.manager;

import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.nwice.barapp.model.Shift;

public class ShiftManager {

	protected static Logger log = Logger.getLogger(ShiftManager.class);
	
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
	
}