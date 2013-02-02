package com.nwice.barapp.servlet;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Drop;
import com.nwice.barapp.money.ExtendedMoney;


@Controller
public class DropServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(DropServlet.class);

    @Autowired
    public DropServlet(CashoutManager cashoutManager) {
    	log.info("DropServlet created");
    	this.cashoutManager = cashoutManager;
    }
	
	@RequestMapping(value="/secure/drop.do", method = RequestMethod.GET)
	public String dropDo(HttpServletRequest request) {
		Cashout co = getCashout(request.getSession());
		Drop drop = co.getDrop();
		processExtendedMoney( (ExtendedMoney)drop, request, "" );
		co.setDrop( drop );
		wash(co, request); 
	return "/secure/index.jsp";
	}	
	
}
