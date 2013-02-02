package com.nwice.barapp.servlet;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nwice.barapp.manager.CashoutManager;

import com.nwice.barapp.model.Cashbox;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Drawer;
import com.nwice.barapp.model.Drop;
import com.nwice.barapp.model.Shift;

@Controller
public class CashoutServlet extends CashHandlerServlet {
	
	private static Logger log = Logger.getLogger(CashoutServlet.class);
	
    @Autowired
    public CashoutServlet(CashoutManager cashoutManager) {
    	log.info("CashoutServlet created");
    	this.cashoutManager = cashoutManager;
    }

    public Cashout createCashout() {
		Cashout co = new Cashout();
		
		Drawer drawerObject = new Drawer();
		co.setDrawer( drawerObject );
		
		Drawer startDrawer = new Drawer();
		co.setStartDrawer( startDrawer );    			

		Cashbox cashboxObject = new Cashbox();
		co.setCashbox( cashboxObject );
		
		Cashbox startCashbox = new Cashbox();
		co.setStartCashbox( startCashbox );

		Drop dropObject = new Drop();
		co.setDrop( dropObject );
		
		return co;
    }
    
	
	@RequestMapping(value="/admin/cashout/demo.do", method = RequestMethod.GET)
    public String cashoutDo(HttpSession session) {
			Cashout co = createCashout();
			
			try {
				co.setShift( ShiftServlet.getNewShift() );
				
				session.setAttribute("cashout", co);
				
			} catch (Exception e) {
				log.error("Couldn't create new shift");
			}			
			return "/secure/index.jsp";
	}

	@RequestMapping(value="/admin/cashout/edit.do", method = RequestMethod.GET)
    public String adminCashoutDo(HttpServletRequest request) {
    	try {    		
    		Integer i = new Integer( request.getParameter("cashoutId") );
    		Cashout co = cashoutManager.getCashoutById(i);
    		request.getSession().setAttribute("cashout", co);
    		log.debug("set Cashout to session");    		
    	} catch (Exception e) {
        	log.error(e);
        }    	
    	return "/secure/index.jsp";
    }	
	
	@RequestMapping(value="/secure/cashout/start.do", method = RequestMethod.GET)
    public String cashoutStart(
    		@RequestParam("start") String start,
    		HttpSession session
    		) {
			if ( start.equals("yes") ) {
				
				Cashout co = createCashout();
				
				try {
					co.setShift( ShiftServlet.getNewShift() );
					
				} catch (Exception e) {
					log.error("Couldn't create new shift");
				}
				
				session.setAttribute("cashout", co);
			}
			return "/secure/index.jsp";
	}
    
	@RequestMapping(value="/admin/cashout/start.do", method = RequestMethod.GET)
    public String adminCashoutStart(
    		@RequestParam("start") String start,
    		@RequestParam("shiftoveride") Boolean shiftoveride,
    		@RequestParam("create_date") String create_date,
    		@RequestParam("ampm") String ampm,
    		HttpSession session
    		) {
    	
			log.info("Called adminCashoutStart");
			
			try {
				
				if ( start.equals("yes") ) {
					log.info("creating new Cashout");
					Cashout co = createCashout();
					if ( shiftoveride.booleanValue()) {
						
						log.info("shift override");
						
    					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    					if ( ampm.equals("AM") ) {
    						create_date = create_date + " 14:00";
    					} else {
    						create_date = create_date + " 20:00";
    					}
    					
    					Date cd = dateFormat.parse(create_date);
    					
						if ( cashoutManager.getByShiftDate(cd) == null ) {
    						
    						Shift so = new Shift();
    						so.setAmpm(ampm);
    						so.setShiftDate( cd );
    	        			co.setShift( so );
    	        			
    	        			session.setAttribute("adminsave", "yes");
    	        			
    	        			session.setAttribute("cashout", co);
    	        			
    					} else {

    						throw new Exception("Shift Exists already");
    						
    					}

					}	
				}					
			} catch (Exception e) {
				// TODO: handle exception
			}

			return "/secure/index.jsp";			
						
    }
	    
    @RequestMapping(value="/secure/cashout/save.do", method = RequestMethod.POST)
    public String cashoutSave(HttpServletRequest request) {
    	log.info("called doPost-");
    	
    	try {
    		if ( getCashout(request.getSession()).getCashoutId() == null ) {
    			log.info("adding a RECORD!");
    			
        		Cashout co = getCashout(request.getSession());
        		
        		Date d = co.getShift().getShiftDate();

        		log.info("Calling persist on:" + getCashout(request.getSession()).getShift().getShiftDate());
        		
        		request.getSession().removeAttribute("cashout");
        		
        		cashoutManager.saveOrUpdateCashout(co);
        		
        		log.info("done calling persist");
        		
        		Cashout saved = cashoutManager.getByShiftDate(d);
        		
        		log.debug("new RECORD ID - " + saved.getCashoutId());
        		request.getSession().setAttribute("cashout", saved);
    		} else {
    			log.info("shouldn't have to do this!");
    		}
		} catch (Exception e) {
			log.error("Error saving cashout", e);
		}
    	return "/secure/print.jsp";
    }

}