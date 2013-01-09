package com.nwice.barapp.servlet;
 
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.UserManager;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Payout;
import com.nwice.barapp.model.ShiftWorker;

/**
 * @web.servlet
 *      name="PayoutServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/payout.do"
 **/

public class PayoutServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(PayoutServlet.class);
	
	private static String[] workers = { "Bartender 1", "Bartender 2", "Barback" };
	
	private static String[] picks = { "D.J.", "Fruit", "Pipe Dreams", "Beer Gas", "Doorman", "Misc" };
	
	public static String[] getShiftOptions() {
		return workers;
	}
	
	public static String[] getOptions() {
		return picks;
	}
	
	private void addPayout(Collection l, String name) {
		Iterator iter = l.iterator();
		while ( iter.hasNext() ) {
			Payout po = (Payout)iter.next();
			if ( po.getName().equals(name) ) {
				return;
			}
		}
		Payout po = new Payout();
		po.setName(name);
		l.add(po);
	}
	
	private void removePayout(Collection l, String name) {
		Iterator iter = l.iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			Payout po = (Payout)o;
			if ( po.getName().equals(name) ) {
				l.remove(o);
				return;
			}
		}
	}
	
	private void addWorker(Collection l, String name) {
		Iterator iter = l.iterator();
		while ( iter.hasNext() ) {
			ShiftWorker swo = (ShiftWorker)iter.next();
			if ( swo.getDescription().equals(name) ) {
				return;
			}
		}
		ShiftWorker swo = new ShiftWorker();
		swo.setDescription(name);
		l.add(swo);
	}
	
	private void removeWorker(Collection l, String name) {
		Iterator iter = l.iterator();
		while ( iter.hasNext() ) {
			Object o = iter.next();
			ShiftWorker swo = (ShiftWorker)o;
			if ( swo.getDescription().equals(name) ) {
				l.remove(o);
				return;
			}
		}
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		
		String redirect = request.getContextPath() + "/secure/index.jsp?action=payouts";
		
		Cashout co = getCashout(request.getSession());
		
		if ( request.getParameter("action").equals("cancel") ) {
			co.getPayouts().clear();
			co.getShift().getShiftWorkers().clear();
		} else if ( request.getParameter("action").equals("next") ) {
			for ( int i = 0; i < workers.length; i++ ) {
				if ( request.getParameter(workers[i]) != null ) {
					addWorker( co.getShift().getShiftWorkers(), workers[i]);
				} else {
					removeWorker( co.getShift().getShiftWorkers(), workers[i]);
				}
			}
			for ( int i = 0; i < picks.length; i++ ) {
				if ( request.getParameter(picks[i]) != null ) {
					addPayout( co.getPayouts(), picks[i]);
				} else {
					removePayout( co.getPayouts(), picks[i]);
				}
			}
			redirect = redirect.concat("&amount=yes");
		} else if ( request.getParameter("amount") != null ) {
			
			for (ShiftWorker sw : co.getShift().getShiftWorkers()) {
				String payouta = request.getParameter( sw.getDescription().concat("_payout"));
				String tipa = request.getParameter( sw.getDescription().concat("_tip"));				
				String usera = request.getParameter( sw.getDescription().concat("_user"));
				try {
					sw.setPayout( new Double(payouta));
				} catch (NumberFormatException e) {
					log.debug(e.toString());
				}
				try {
					sw.setTips( new Double(tipa));
				} catch (NumberFormatException e) {
					log.debug(e.toString());
				}
				try {
					UserManager um = new UserManager();
					
					sw.setBarappUser( um.getUserById(new Integer(usera)));
				} catch (Exception e) {
					log.debug(e.toString());
				} 
			}
			
			Payout[] payouts = (Payout[])co.getPayouts().toArray(new Payout[co.getPayouts().size()]);
			for (int i = 0; i < payouts.length; i++) {
				String description = request.getParameter( payouts[i].getName().concat("_description"));
				String total = request.getParameter( payouts[i].getName().concat("_total"));
				payouts[i].setDescription(description);
				try {
					payouts[i].setTotal( new Double(total));
				} catch (NumberFormatException e) {
					log.debug(e.toString());
				}
			}
		}
		
		try {		
			wash(co, request);
		} catch (Exception e) {
			log.error(e); 
		}
				
		if ( !request.getParameter("action").equals("next") && !request.getParameter("action").equals("cancel") ) {
			redirect = request.getContextPath() + "/secure/index.jsp?action=" + request.getParameter("action");
		}
		response.sendRedirect(redirect);
	}
		
}
