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
	
		<% 
		Map hoursMap = Collections.synchronizedMap(new HashMap());
		Map tipsMap = Collections.synchronizedMap(new HashMap());
		for ( int i = 0; i < cashouts.length; i++ ) { 
			Cashout co = cashouts[i];
			Shift so = co.getShift();
						
			ShiftWorkerObject[] workers = (ShiftWorkerObject[])so.getShiftWorkers().toArray(new ShiftWorkerObject[so.getShiftWorkers().size()]);
			logger.info("workers length:" + workers.length);
			for ( int j = 0; j < workers.length; j++ ) {
				String workerName = userManager.getUserById( workers[j].getUser()).getLastname() + ", " + userManager.getUserById( workers[j].getUser()).getFirstname(); 
				logger.info("worker:" + workerName);
				Double tips = workers[j].getTips();
				logger.info("tips:" + tips);				
				Integer hours = new Integer(6);
				
				if ( so.getAmpm().equals("PM") ) {
					if ( workers[j].getDescription().equals("Bartender 1") ) { 
						hours = new Integer(9);
					} else {
						hours = new Integer(7);
					}
				}
				
				logger.info("hours:" + hours);
				
				if  ( hoursMap.containsKey(workerName) ) {
					Integer h = (Integer)hoursMap.get(workerName);
					hours = new Integer( hours.intValue() + h.intValue() );
				} 
				hoursMap.put( workerName, hours );	
				logger.info(workerName + " totalhours:" + hours);				
				if  ( tipsMap.containsKey(workerName) ) {
					Double t = (Double)tipsMap.get(workerName);
					tips = new Double( tips.doubleValue() + t.doubleValue() );
				} 
				logger.info(workerName + " totaltips:" + tips);
				tipsMap.put( workerName, tips );
			}
		}
		%>
	
		
		<table border="0" cellpadding="2" cellspacintg="0">
		<tr>
		<td colspan="3" class="smallFont" align="right">
			total shifts: <%= cashouts.length %>
		</td>
		</tr>		
		<tr>
		<td>
			Bartender Name
		</td>
		<td>
			Hours
		</td>	
		<td>
			Tips
		</td>		
		</tr>
		

		
		<%
		Set s = hoursMap.keySet();
		Iterator iter = s.iterator();
		int total_hours = 0;
		double total_tips = 0.0;
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
				Integer temp_i = (Integer)hoursMap.get(wn);
				total_hours = total_hours + temp_i.intValue();
				%>
				<%= temp_i %>
			</td>
			<td>
				<%
				Double temp_d = (Double)tipsMap.get(wn);
				total_tips = total_tips + temp_d.doubleValue();
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
				<%= total_hours %>
			</td>			
			<td>
				$<%= BarappUtil.doubleToString( new Double(total_tips) ) %>
			</td>
		</tr>
		</table>
	<% } else { %>
		No Shifts
	<% } %>
