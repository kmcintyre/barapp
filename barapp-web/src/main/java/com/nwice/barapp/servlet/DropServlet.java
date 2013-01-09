package com.nwice.barapp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.model.Drop;
import com.nwice.barapp.money.ExtendedMoney;

/**
 * @web.servlet
 *      name="DropServlet"
 * @web.servlet-mapping
 *      url-pattern="/secure/drop.do"
 **/

public class DropServlet extends CashHandlerServlet {
	
	protected static Logger log = Logger.getLogger(DropServlet.class);

	public void service(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	try {
		Cashout co = getCashout(request.getSession());
		Drop drop = co.getDrop();
		processExtendedMoney( (ExtendedMoney)drop, request, "" );
		co.setDrop( drop );
		wash(co, request);
	} catch (Exception e) {
		log.error(e);
    }
	ServletContext sc = getServletContext(); 
	RequestDispatcher rd = sc.getRequestDispatcher("/secure/index.jsp");
	rd.include(request, response); 
	}	
	
}
