
<%! String printDay(String d, double d_1, double d_2, double d_3) { 
		String s = "<tr><td></td><td> " + d + " </td><td> $" + BarappUtil.doubleToString( new Double(d_1) ) + " </td><td> $" + BarappUtil.doubleToString( new Double(d_2) ) + " </td><td> $ "+ BarappUtil.doubleToString( new Double(d_3) ) + "</td></tr>";
		System.out.println(s);
		return s;
	}
%>

	<% if ( cashouts.length > 0 ) { %>
		<table border="0" cellpadding="2" cellspacintg="0">
		<tr>
		<td colspan="5" class="smallFont">			
			<div style="float:left" class="smallFont">
				<a class="smallFont" href="<%= request.getContextPath() %>/admin/index.jsp?action=cashouts&view=shift">Shift</a>						
			</div>
			<div style="float:right"  class="smallFont">
				total shifts: <%= cashouts.length %>
			</div>
		</td>
		</tr>
		<tr>
		<td>			
		</td>
		<td>
			Day
		</td>
		<td>
			Drop
		</td>	
		<td>
			Payouts
		</td>		
		<td>
			Total
		</td>				
		</tr>
		<%
		double ttotal = 0.0;
		double ptotal = 0.0;		
		double dtotal = 0.0;
		
		double dailypayout = 0.0;
		double dailydrop = 0.0;		
		double dailytotal = 0.0;		
		String d = "";
		
		int cashoutId = 0;		
		for ( int i = 0; i < cashouts.length; i++ ) { 
			try {
			Cashout co = cashouts[i];
			cashoutId = co.getCashoutId().intValue();
			logger.info("Cashout id:" + co.getCashoutId());
			logger.info("Shift id:" + co.getShift().getShiftId());
			if ( ! ShiftManager.displayFormat.format(co.getShift().getShiftDate()).equals( d ) && !d.equals("") ) { %>
				<%= printDay(d, dailydrop, dailypayout, dailypayout + dailydrop) %>
				<%
				dtotal = dtotal + dailydrop;
				ptotal = ptotal + dailypayout;	
				dailytotal = dailydrop + dailypayout;			
				ttotal = ttotal + dailytotal;		
				dailypayout = 0.0;
				dailydrop = 0.0;		
				dailytotal = 0.0;		
				d = ShiftManager.displayFormat.format(co.getShift().getShiftDate());				
				%>
			<% } else if ( d.equals("") ) { %>
				<% d = ShiftManager.displayFormat.format(co.getShift().getShiftDate()); %>
			<% } %>
			
			<%
			dailydrop = dailydrop + co.getDrop().getTotal().doubleValue();
			%>
			
			<%
			Payout[] payouts = (Payout[])co.getPayouts().toArray(new Payout[co.getPayouts().size()]);
			for ( int j = 0; j < payouts.length; j++ ) {
				dailypayout = dailypayout + payouts[j].getTotal().doubleValue();
			}
			ShiftWorker[] workers = (ShiftWorker[])co.getShift().getShiftWorkers().toArray(new ShiftWorker[co.getShift().getShiftWorkers().size()]);
			for ( int j = 0; j < workers.length; j++ ) {
				dailypayout = dailypayout + workers[j].getPayout().doubleValue();		
			} 
			%>
			<% } catch (Exception e) { logger.error(e); %><%= cashoutId %><% } %>
		<% } %>
		<%= printDay(d, dailydrop, dailypayout, dailypayout + dailydrop) %>
		<%
		dtotal = dtotal + dailydrop;
		ptotal = ptotal + dailypayout;	
		dailytotal = dailydrop + dailypayout;			
		ttotal = ttotal + dailytotal;		
		%>
		
		<tr>
		<td>
		</td>
		<td>
		</td>
		<td>
			$<%= BarappUtil.doubleToString( new Double(dtotal) ) %>
		</td>	
		<td>
			$<%= BarappUtil.doubleToString( new Double(ptotal) ) %>
		</td>		
		<td>
			$<%= BarappUtil.doubleToString( new Double(ttotal) ) %>
		</td>				
		</tr>
		</table>
	<% } else { %>
		No Cashouts
	<% } %>
		