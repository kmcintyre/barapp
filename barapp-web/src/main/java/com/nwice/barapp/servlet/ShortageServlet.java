package com.nwice.barapp.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Overring;
import com.nwice.barapp.model.Shortage;

@Controller
public class ShortageServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(ShortageServlet.class);

    @Autowired
    public ShortageServlet(CashoutManager cashoutManager) {
    	log.info("ShortageServlet created");
    	this.cashoutManager = cashoutManager;
    }
	
	
	@RequestMapping(value="/secure/shortage.do", method = RequestMethod.GET)
	public String shortageDo(HttpServletRequest request) {
    		Cashout co = getCashout(request.getSession());
    		co.getShortages().clear();
    		co.getOverrings().clear();
    		
    		for ( int i = 0; i < 5; i++ ) {
    			String total_param = "total_shortage_" + i;
    			try {
    				
    				if ( request.getParameter(total_param).length() > 0 ) {
    					Shortage shortage = new Shortage();
    					processDefaultFund( (DefaultFund)shortage, request, "_shortage_" + i );
    					log.debug("Adding Shortage");
    					co.addShortage( shortage );
    				}
    			} catch (java.lang.NumberFormatException e) {    				
    			} catch (Exception e) {
    				log.debug(e);
    			}
    		} 
    		for ( int i = 0; i < 5; i++ ) {
    			String total_param = "total_overring_" + i;
    			try {    				
    				if ( request.getParameter(total_param).length() > 0 ) {
    					Overring overring = new Overring();    					
    					processDefaultFund( (DefaultFund)overring, request, "_overring_" + i );
    					log.debug("Adding Overring");
    					co.addOverring( overring );
    				}
    				
    			} catch (java.lang.NumberFormatException e) {
    				    				
    			} catch (Exception e) {
    				log.debug(e);
    			}
    		}    		
    		wash(co, request);
    	return "/secure/index.jsp";
    }	
		
	
}
