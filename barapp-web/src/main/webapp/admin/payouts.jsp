<%@ page import="com.nwice.barapp.model.*" %> 
<%@ page import="com.nwice.barapp.servlet.ShiftServlet" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.nwice.barapp.util.*" %>
<%@ page import="org.apache.log4j.Logger" %>

<%@ include file="calendar.jsp" %>

<jsp:useBean id="cashoutManager" scope="application" class="com.nwice.barapp.manager.CashoutManager"/>

<jsp:useBean id="userManager" scope="application" class="com.nwice.barapp.manager.UserManager"/>
	
	<% Logger logger = Logger.getLogger("cashouts.jsp"); %>
	
	<% logger.info("Getting Shifts"); %>
	
	<% Cashout[] cashouts = cashoutManager.getCashoutsByDates(from_date,to_date); %>

	<% if ( cashouts.length > 0 ) { %>
	
		<% logger.info("cashouts length:" + cashouts.length); %>
	
		<% Map payoutsMap = Collections.synchronizedMap(new TreeMap()); %>
		
		<%
		for ( int i = 0; i < cashouts.length; i++ ) { 
			Cashout co = cashouts[i];
			Shift so = co.getShift();
						
			ShiftWorkerObject[] workers = (ShiftWorkerObject[])so.getShiftWorkers().toArray(new ShiftWorkerObject[so.getShiftWorkers().size()]);
			logger.info("workers length:" + workers.length);
			for ( int j = 0; j < workers.length; j++ ) {
				String workerName = userManager.getUserById( workers[j].getUser()).getLastname() + ", " + userManager.getUserById( workers[j].getUser()).getFirstname(); 
				logger.info("worker:" + workerName);
				Double payout = workers[j].getPayout();
				logger.info("payout:" + payout);				
				
				if ( payout.doubleValue() > new Double(0).doubleValue() ) {
				
					if  ( payoutsMap.containsKey(workerName) ) {
						Double t = (Double)payoutsMap.get(workerName);
						payout = new Double( payout.doubleValue() + t.doubleValue() );
					} 
					payoutsMap.put( workerName, payout );
				}
			}
			PayoutObject[] payouts = (PayoutObject[])co.getPayouts().toArray(new PayoutObject[co.getPayouts().size()]);
			logger.info("payouts length:" + payouts.length);
			for ( int j = 0; j < payouts.length; j++ ) {
				String name = payouts[j].getName(); 
				if ( name.equals("Misc") ) {
					char chars[] = payouts[j].getDescription().toCharArray();
					chars[0] = Character.toUpperCase(chars[0]);
					name = "Misc-" + new String(chars);
				}
				logger.info("name:" + name);
				Double payout = payouts[j].getTotal();
				logger.info("payout:" + payout);				
				
				if ( payout.doubleValue() > new Double(0).doubleValue() ) {
				
					if  ( payoutsMap.containsKey(name) ) {
						Double t = (Double)payoutsMap.get(name);
						payout = new Double( payout.doubleValue() + t.doubleValue() );
					} 
					payoutsMap.put( name, payout );
				}
			}			
		}
		%>
	
		
		<table border="0" cellpadding="2" cellspacintg="0">
		<tr>
		<td colspan="2" class="smallFont" align="right">
			total shifts: <%= cashouts.length %>
		</td>
		</tr>		
		<tr>
		<td>
			Pay To
		</td>
		<td>
			Payout Amt
		</td>	
		</tr>
		

		
		<%
		Set s = payoutsMap.keySet();
		Iterator iter = s.iterator();
		double total_payouts = 0.0;
		%>
		
		<% while ( iter.hasNext() ) {
			String wn = (String)iter.next();
			%>
			<tr>
			<td>
				<%= wn %>
			</td>
			<td>
				<%
				Double temp_d = (Double)payoutsMap.get(wn);
				total_payouts = total_payouts + temp_d.doubleValue();
				%>
				$<%= BarappUtil.doubleToString( temp_d ) %>
			</td>
			</tr>					
		<% } %>		
		<tr>
			<td>
				Total:
			</td>
			<td>
				$<%= BarappUtil.doubleToString( new Double(total_payouts) ) %>
			</td>
		</tr>
		</table>
	<% } else { %>
		No Shifts
	<% } %>
