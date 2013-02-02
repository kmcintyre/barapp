package com.nwice.barapp.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;

@Controller
public class AdminCashoutServlet extends CashHandlerServlet {
	
	private static Logger log = Logger.getLogger(AdminCashoutServlet.class);
	
    @Autowired
    public AdminCashoutServlet(CashoutManager cashoutManager) {
    	log.info("AdminCashoutServlet created");
    	this.cashoutManager = cashoutManager;
    }
	
	
	@RequestMapping(value="/admin/admin_cashout.do", method = RequestMethod.GET)
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
	
}