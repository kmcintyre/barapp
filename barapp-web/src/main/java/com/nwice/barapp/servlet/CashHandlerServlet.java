package com.nwice.barapp.servlet;

import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.nwice.barapp.fund.DefaultFund;
import com.nwice.barapp.manager.CashoutManager;
import com.nwice.barapp.model.Cashout;
import com.nwice.barapp.money.DefaultMoney;
import com.nwice.barapp.money.ExtendedMoney;

public class CashHandlerServlet extends HttpServlet {

	protected static Logger log = Logger.getLogger(CashHandlerServlet.class);
	
	protected CashoutManager cashoutManager;
	
	public void init() throws ServletException {
		try {
			cashoutManager = new CashoutManager();
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	public Cashout getCashout(HttpSession session) {
		return (Cashout)session.getAttribute("cashout");
	}
	
	private Integer processInteger(Object o) {
		try {
			return new Integer( o.toString() );
		} catch (Exception e) {
			return new Integer(0);
		}
	}
	
	private Double processDouble(Object o) {
		try {
			return new Double( o.toString() );
		} catch (Exception e) {
			return new Double(0);
		}
	}
	
	public static void wash(Cashout co, HttpServletRequest request) throws Exception {
		CashoutManager cm = new CashoutManager(); 
		if ( co.getCashoutId() != null ) {
			Integer i = co.getCashoutId(); 
			cm.getSessionFactory().getCurrentSession().saveOrUpdate(co);
			Cashout co2 = cm.getCashoutById(i);
			request.getSession().setAttribute("cashout", co2);
			log.info("Washed-" + i);
		} else {
			log.info("Skipped Wash");
		}
		
	}
	
	
	public static Cashout previousCashout(Cashout co) throws Exception {
		return previousCashout(co.getShift().getShiftDate());
	}
	
	public static Cashout previousCashout(Date d) throws Exception {
		return new CashoutManager().getPreviousByDate(d);
	}
	
	public static boolean hasPreviousCashout(Date d) {
		try {
			Cashout co = previousCashout(d);
			return true;
		} catch (Exception e) {
			log.info("Nothing Previous:" + d.toString() );
			return false;
		}		
	}


	public void processDefaultFund(DefaultFund dfo, HttpServletRequest request, String ammend) {
		log.debug("Called processDefaultFund");
		String total = new String("0.0");
		if ( request.getParameter("total".concat(ammend)) != null ) {
			total = request.getParameter("total".concat(ammend));
		}
		dfo.setTotal( processDouble( total ) );
		
		String description = new String("");
		
		if ( request.getParameter("description".concat(ammend)) != null ) {
			description = request.getParameter("description".concat(ammend));
		}
		dfo.setDescription( description );
		
	}
	
	public void processDefaultMoney(DefaultMoney dmo, HttpServletRequest request, String ammend) {
		log.debug("Called processDefaultMoney");
		
		String single = new String("0");
		
		if ( request.getParameter("single".concat(ammend)) != null ) {
			single = request.getParameter("single".concat(ammend));
		} 
		dmo.setSingle( processInteger(single) );
		
		String five = new String("0");
		if ( request.getParameter("five".concat(ammend)) != null ) {
			five = request.getParameter("five".concat(ammend));
		} 		
		dmo.setFive( processInteger(five) );
		String ten = new String("0");
		if ( request.getParameter("ten".concat(ammend)) != null ) {
			ten = request.getParameter("ten".concat(ammend));
		} 				
		dmo.setTen( processInteger(ten) );
		String twenty = new String("0");
		if ( request.getParameter("twenty".concat(ammend)) != null ) {
			twenty = request.getParameter("twenty".concat(ammend));
		} 					
		dmo.setTwenty( processInteger(twenty) );		
		processDefaultFund( dmo, request, ammend );
	}
	
	public void processExtendedMoney(ExtendedMoney emo, HttpServletRequest request, String ammend) {
		String fifty = new String("0");
		if ( request.getParameter("fifty".concat(ammend)) != null ) {
			fifty = request.getParameter("fifty".concat(ammend));
		} 						
		emo.setFifty( processInteger(fifty) );
		String hundred = new String("0");
		if ( request.getParameter("hundred".concat(ammend)) != null ) {
			hundred = request.getParameter("hundred".concat(ammend));
		} 							
		emo.setHundred( processInteger(hundred) );
		processDefaultMoney( emo, request, ammend );
	}

	/*
	public void processAllMoneyObject(AllMoneyObject amo, HttpServletRequest request)  {
		amo.setPenny( processInteger(request.getParameter("penny")) );
		amo.setNickel( processInteger(request.getParameter("nickel")) );	
		amo.setDime( processInteger(request.getParameter("dime")) );
		amo.setQuarter( processInteger(request.getParameter("quarter")) );
		processExtendedMoney(amo, request);
	}
	*/
	
	
}