package com.nwice.barapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.model.Cashbox;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.money.DefaultMoney;
import com.nwice.barapp.money.ExtendedMoney;


/**
 * @web.servlet
 *      name="CashboxServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/cashbox.do"
 **/

public class CashboxServlet extends CashHandlerServlet {
	
	public static void importPrevious(Cashout co) throws Exception {
		Cashout previous = previousCashout(co);
		((ExtendedMoney)previous.getStartCashbox()).limitedCopy(
				(ExtendedMoney)co.getStartCashbox()
				);		
		((DefaultMoney)previous.getStartCashbox()).limitedCopy(
				(DefaultMoney)co.getStartCashbox()
				);
		((DefaultFund)previous.getStartCashbox()).limitedCopy(
				(DefaultFund)co.getStartCashbox()
				);
		co.getStartCashbox().setDescription("copied from previous");
	}
	
	protected static Logger log = Logger.getLogger(CashboxServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
	   	try {
    		if ( request.getParameter("previous") != null ) {
    			Cashout co =  getCashout(request.getSession());
    			Cashout previous = cashoutManager.getPreviousByDate( co.getShift().getShiftDate() );
    			log.info("Previous Date:" + previous.getShift().getShiftDate() );
    		} else {
	    		Cashout co = getCashout(request.getSession());    		
	    		Cashbox cashbox = co.getCashbox();    		
	    		Cashbox startCashbox = co.getStartCashbox();
	    		
	    		processExtendedMoney( (ExtendedMoney)cashbox, request, "" );    		
	    		processExtendedMoney( (ExtendedMoney)startCashbox, request, "_start" );
	    		
	    		co.setCashbox( cashbox );
	    		co.setStartCashbox( startCashbox );
	    		wash(co, request);
    		}
    	} catch (Exception e) {
    		log.error(e);
        }
    	ServletContext sc = getServletContext(); 
    	RequestDispatcher rd = sc.getRequestDispatcher("/secure/index.jsp");
    	rd.include(request, response); 
    }	
		
	
}
