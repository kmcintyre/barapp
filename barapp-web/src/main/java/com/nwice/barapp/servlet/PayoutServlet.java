package com.nwice.barapp.servlet;
 
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.manager.UserManager;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Payout;
import com.nwice.barapp.model.ShiftWorker;

@Controller
public class PayoutServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(PayoutServlet.class);
	
	private static String[] workers = { "Bartender 1", "Bartender 2", "Barback" };
	
	private static String[] picks = { "D.J.", "Fruit", "Pipe Dreams", "Beer Gas", "Doorman", "Misc" };
	
	private UserManager userManager;
	
    @Autowired
    public PayoutServlet(CashoutManager cashoutManager, UserManager userManager) {
    	log.info("PayoutServlet created");
    	this.cashoutManager = cashoutManager;
    	this.userManager = userManager;
    }
	
	
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
	
	@RequestMapping(value="/secure/payout.do", method = RequestMethod.GET)
	public String payoutDo(HttpServletRequest request) {
		
		String redirect = "/secure/index.jsp?action=payouts";
		
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
				
				sw.setBarappUser( userManager.getUserById(new Integer(usera)));
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
		
			wash(co, request);
				
		if ( !request.getParameter("action").equals("next") && !request.getParameter("action").equals("cancel") ) {
			redirect = "/secure/index.jsp?action=" + request.getParameter("action");
		}
		
		return redirect;
	}
		
}
