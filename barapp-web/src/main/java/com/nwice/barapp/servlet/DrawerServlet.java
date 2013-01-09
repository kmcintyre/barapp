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
import com.nwice.barapp.model.Drawer;
import com.nwice.barapp.money.DefaultMoney;

/**
 * @web.servlet
 *      name="DrawerServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/drawer.do"
 **/

public class DrawerServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(DrawerServlet.class);
	
	public static void importPrevious(Cashout co) throws Exception {
		Cashout previous = previousCashout(co);
		
		((DefaultMoney)previous.getStartDrawer()).limitedCopy(
				(DefaultMoney)co.getStartDrawer()
				);
		((DefaultFund)previous.getStartDrawer()).limitedCopy(
				(DefaultFund)co.getStartDrawer()
				);
		co.getStartDrawer().setDescription("copied from previous");
	}
	
	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String redirect = "/secure/index.jsp";
    	try {

	    		Cashout co = getCashout(request.getSession());
	    		
	    		Drawer drawer = co.getDrawer();    		    	
	    		Drawer startDrawer = co.getStartDrawer();
	    		
	    		processDefaultMoney( (DefaultMoney)drawer, request, "" );    		
	    		processDefaultMoney( (DefaultMoney)startDrawer, request, "_start" );
	    		
	    		co.setDrawer( drawer );
	    		co.setStartDrawer( startDrawer );
	    		
	    		wash(co, request);

    	} catch (Exception e) {
    		log.error(e);
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher(redirect);
    	rd.include(request, response); 
    }	
	
	
}
