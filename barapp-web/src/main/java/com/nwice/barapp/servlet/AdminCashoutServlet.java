package com.nwice.barapp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;

/**
 * @web.servlet
 *      name="AdminCashoutServlet"
 * @web.servlet-mapping
 *      url-pattern="/admin/admin_cashout.do"
 **/

public class AdminCashoutServlet extends CashHandlerServlet {
	
	private static Logger log = Logger.getLogger(AdminCashoutServlet.class);
    
	private CashoutManager cashoutManager;

	public void init() throws ServletException {
		try {
			cashoutManager = new CashoutManager();
		} catch (Exception e) {
			log.error(e);
		}
	}
		
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	try {    		
    		Integer i = new Integer( request.getParameter("cashoutId") );
    		Cashout co = cashoutManager.getCashoutById(i);
    		request.getSession().setAttribute("cashout", co);
    		request.getSession().setAttribute("cashoutId", i);
    		log.debug("set Cashout to session");    		
    	} catch (Exception e) {
        	log.error(e);
        }
    	response.sendRedirect( request.getContextPath() + "/secure/index.jsp");
    }
	
}