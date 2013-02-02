package com.nwice.barapp.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashbox;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.money.DefaultMoney;
import com.nwice.barapp.money.ExtendedMoney;


@Controller
public class CashboxServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(CashboxServlet.class);
	
    @Autowired
    public CashboxServlet(CashoutManager cashoutManager) {
    	log.info("CashboxServlet created");
    	this.cashoutManager = cashoutManager;
    }	
	
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
	
	
	@RequestMapping(value="/secure/cashbox.do", method = RequestMethod.GET)
	public String service(HttpServletRequest request) {
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
	   	return "/secure/index.jsp";
    }	
		
	
}
