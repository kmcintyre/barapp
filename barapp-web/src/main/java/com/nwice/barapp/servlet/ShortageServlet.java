package com.nwice.barapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Overring;
import com.nwice.barapp.model.Shortage;

/**
 * @web.servlet
 *      name="ShortageServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/shortage.do"
 **/

public class ShortageServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(ShortageServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
    	try {
    		Cashout co = getCashout(request.getSession());
    		co.getShortages().clear();
    		co.getOverrings().clear();
    		
    		for ( int i = 0; i < 5; i++ ) {
    			String total_param = "total_shortage_" + i;
    			try {
    				Double d = new Double(request.getParameter(total_param));
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
    				Double d = new Double(request.getParameter(total_param));
    				
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
    	} catch (Exception e) {
    		log.error(e);
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher("/secure/index.jsp");
    	rd.include(request, response); 
    }	
		
	
}
