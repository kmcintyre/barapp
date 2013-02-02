package com.nwice.barapp.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Drawer;
import com.nwice.barapp.money.DefaultMoney;

@Controller
public class DrawerServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(DrawerServlet.class);

    @Autowired
    public DrawerServlet(CashoutManager cashoutManager) {
    	log.info("DrawerServlet created");
    	this.cashoutManager = cashoutManager;
    }	
	
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
	
	@RequestMapping(value="/secure/drawer.do", method = RequestMethod.GET)
	public String drawerDo(
    		HttpSession session,
    		HttpServletRequest request) {
		
		log.info("called drawerDo");

		Cashout co = getCashout(request.getSession());
	    		
		Drawer drawer = co.getDrawer();    		    	
		Drawer startDrawer = co.getStartDrawer();
		
		processDefaultMoney( (DefaultMoney)drawer, request, "" );    		
		processDefaultMoney( (DefaultMoney)startDrawer, request, "_start" );
		
		co.setDrawer( drawer );
		co.setStartDrawer( startDrawer );
		
		wash(co, request);

    	return "/secure/index.jsp";
    }	
	
	
}
