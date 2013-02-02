<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.servlet.PayoutServlet" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<% Payout[] payouts = (Payout[])cashout.getPayouts().toArray(new Payout[cashout.getPayouts().size()]); %>

<% ShiftWorker[] shiftWorkers = (ShiftWorker[])cashout.getShift().getShiftWorkers().toArray(new ShiftWorker[cashout.getShift().getShiftWorkers().size()]); %>

<table border="0" cellspacing="0">
	<form name="payout" action="<%= request.getContextPath() %>/secure/payout.do">
	<input type="hidden" name="action"/>
	
	<% String[] workers = PayoutServlet.getShiftOptions(); %>
	
	<% for ( int i = 0; i < workers.length; i++ ) { %>
		<% if ( i % 2 == 0 ) { %>
			<tr><td class="largeFont" style="padding-right:10px;padding-bottom:5px">
		<% } else { %>
			<td class="largeFont" style="padding-left:10px;padding-bottom:5px">
		<% } %>
		
		<% 
		boolean checked = false;
		for ( int j = 0; j < shiftWorkers.length; j++ ) {
			if ( shiftWorkers[j].getDescription().equals( workers[i]) ) {
				checked = true;
			}
		} 
		%>
	
		<jsp:include page="_static_/optionform.jsp">
			<jsp:param name="name" value="<%= workers[i] %>"/>
			<jsp:param name="checked" value="<%= checked %>"/>			
		</jsp:include>

		<% if ( i % 2 == 0 ) { %>
			</td>
		<% } else { %>
			</td></tr>
		<% } %>
	<% } %>
	
	<% String[] picks = PayoutServlet.getOptions(); %>
	
	<% for ( int i = 0; i < picks.length; i++ ) { %>
		
		<% if ( i % 2 == 1 ) { %>
			<tr><td class="largeFont" style="padding-right:10px;padding-bottom:5px">
		<% } else { %>
			<td class="largeFont" style="padding-left:10px;padding-bottom:5px">
		<% } %>
		
		<% 
		boolean checked = false;
		for ( int j = 0; j < payouts.length; j++ ) {
			if ( payouts[j].getName().equals( picks[i]) ) {
				checked = true;
			}
		} 
		%>
		
		<jsp:include page="_static_/optionform.jsp">
			<jsp:param name="name" value="<%= picks[i] %>"/>
			<jsp:param name="checked" value="<%= checked %>"/>			
		</jsp:include>		
		
		<% if ( i % 2 == 1 ) { %>
			</td>
		<% } else { %>
			</td></tr>
		<% } %>
	
	<% } %>
	<tr>
		<td colspan="2" align="center">
			<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="20"/>
		</td>		
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input class="largeFont" type="button" onClick="javascript:document.payout.action.value='cancel';document.payout.submit();" value=" Clear "> 
			<input class="largeFont" type="button" onClick="javascript:document.payout.action.value='next';document.payout.submit();" value=" Next ">		
		</td>		
	</tr>	
	</form>
</table>					