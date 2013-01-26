package com.nwice.barapp.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashbox;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Drawer;
import com.nwice.barapp.model.Drop;
import com.nwice.barapp.model.Shift;

/**
 * @web.servlet
 *      name="CashoutServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/cashout.do"
 **/

public class CashoutServlet extends CashHandlerServlet {
	
	private static Logger log = Logger.getLogger(CashoutServlet.class);
   	
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
		String action = "";
		if ( request.getParameter("action") != null ) {
			action = "&action=" + request.getParameter("action");
		}    	
    	try {    		
    		log.info("Called doGet");    		
    		if ( request.getParameter("start") != null ) {
    			log.debug("creating new Cashout");
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

    			if ( request.getParameter("shiftoveride") != null && request.isUserInRole("admin") ) {
    				
    					DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    				
    					String create_date = request.getParameter("create_date");
    					String ampm = request.getParameter("ampm");
    					if ( ampm.equals("AM") ) {
    						create_date = create_date + " 14:00";
    					} else {
    						create_date = create_date + " 20:00";
    					}
    					
    					Date cd = dateFormat.parse(create_date);
    					
    					CashoutManager cm = new CashoutManager(); 
    					
    					try {
    						Cashout exists = cm.getByShiftDate(cd);
    						response.getWriter().write("This Shift Already Exists");
    						return;
    					} catch (Exception e) {
    						Shift so = new Shift();
    						so.setAmpm(ampm);
    						so.setShiftDate( cd );
    	        			co.setShift( so );
    	        			request.getSession().setAttribute("adminsave", "yes");
    					}
    			} else {
        			Shift shiftObject = ShiftServlet.getNewShift();
        			co.setShift( shiftObject );
    				
    			}
    			log.debug(co.getShift().getShiftDate().toString());
    			request.getSession().setAttribute("cashout", co);
    			log.debug("set Cashout to session");
    		}
    		/*
    		else if ( request.getParameter("force") != null ) {
    			
    			log.info("force RECORD!");
    			
    			save(request,response);
    		}
    		*/
    	} catch (Exception e) {
        	log.error(e);
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher("/secure/index.jsp" + action);
    	rd.include(request, response); 
    }
	
    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    }
    
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	log.info("called doPost-");
    	try {
    		save(request,response);
    		if ( request.getSession().getAttribute("adminsave") != null ) {
    			request.getSession().removeAttribute("adminsave");
    		}
    	} catch (Exception e) {
    		log.error(e.toString());
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher("/secure/print.jsp");
    	rd.include(request, response); 
    }

}