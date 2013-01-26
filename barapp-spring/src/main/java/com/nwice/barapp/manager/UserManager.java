package com.nwice.barapp.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nwice.barapp.model.BarappUser;

@Repository
@Transactional
public class UserManager  {

	protected static Logger log = Logger.getLogger(UserManager.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveOrUpdateUser(BarappUser uo) {
		sessionFactory.getCurrentSession().saveOrUpdate(uo);
	}
		
	public BarappUser getUserById(Integer userId) throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(BarappUser.class);
		crit.add( Restrictions.eq("barappUserId",  userId) );
		Object o = crit.uniqueResult();
		return (BarappUser)o;
	}

	public BarappUser getUserByUsername(String username) throws Exception {	
		log.info("Called getUserByUsername:" + username);
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(BarappUser.class);
		crit.add( Restrictions.eq("username",  username) );
		Object o = crit.uniqueResult();
		return (BarappUser)o;
	}
	
	public BarappUser[] getActiveBartenders() throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(BarappUser.class);
		crit.add( Restrictions.eq("role", "bartender") );
		crit.add( Restrictions.eq("active", new Boolean(true) ));
		List users = crit.list();
		return (BarappUser[])users.toArray(new BarappUser[users.size()]);
	}

	public BarappUser[] getActiveBarbacks() throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(BarappUser.class);
		crit.add( Restrictions.eq("role", "barback") );
		crit.add( Restrictions.eq("active", new Boolean(true) ));
		List users = crit.list();
		return (BarappUser[])users.toArray(new BarappUser[users.size()]);
	}
	
	public BarappUser[] getAllUsers() throws Exception {		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(BarappUser.class);
		List users = crit.list();
		return (BarappUser[])users.toArray(new BarappUser[users.size()]);
	}
		
	public void createUser(String firstname, String lastname, String username, String password, String role, Boolean active) 
	throws Exception {
		BarappUser uo = new BarappUser();
		uo.setFirstname(firstname);
		uo.setLastname(lastname);
		uo.setUsername(username);
		uo.setPassword(password);
		uo.setRole(role);
		uo.setActive(active);
		sessionFactory.getCurrentSession().save(uo);
	}
	
}