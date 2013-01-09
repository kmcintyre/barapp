	<% if ( cashouts.length > 0 ) { %>
		<table border="0" cellpadding="2" cellspacintg="0">
		<tr>
		<td colspan="5" class="smallFont">			
			<div style="float:left" class="smallFont">
				<a class="smallFont" href="<%= request.getContextPath() %>/admin/index.jsp?action=cashouts&view=daily">Daily</a>						
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
			Shift
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
		int cashoutId = 0;		
		for ( int i = 0; i < cashouts.length; i++ ) { 
			try {
			Cashout co = cashouts[i];
			cashoutId = co.getCashoutId().intValue();
			logger.info("Cashout id:" + co.getCashoutId());
			logger.info("Shift id:" + co.getShift().getShiftId());
			%>
			
			<tr>
			<td>
				<% if ( request.getSession().getAttribute("daily") == null ) { %>
					<a href="<%= request.getContextPath() %>/admin/admin_cashout.do?cashoutId=<%= co.getCashoutId() %>">Edit</a> 
					<!-- <%= co.getShift().getShiftDate() %> -->
				<% } %>
			</td>
			<td>
				<%= ShiftServlet.displayFormat.format(co.getShift().getShiftDate()) %> 
				<% if ( ShiftServlet.isAmShift( co.getShift() ) ) { %>
					Day
				<% } else { %>
					Night
				<% } %>
			</td>
			<td>
				$<%= BarappUtil.doubleToString(co.getDropObject().getTotal()) %>
			</td>
				<%
				double t = 0.0;
				dtotal = dtotal + co.getDropObject().getTotal().doubleValue();
				PayoutObject[] payouts = (PayoutObject[])co.getPayouts().toArray(new PayoutObject[co.getPayouts().size()]);
				for ( int j = 0; j < payouts.length; j++ ) {
					t = t + payouts[j].getTotal().doubleValue();
					ptotal = ptotal + payouts[j].getTotal().doubleValue();
				}
				ShiftWorkerObject[] workers = (ShiftWorkerObject[])co.getShift().getShiftWorkers().toArray(new ShiftWorkerObject[co.getShift().getShiftWorkers().size()]);
				for ( int j = 0; j < workers.length; j++ ) {
					t = t + workers[j].getPayout().doubleValue();
					ptotal = ptotal + workers[j].getPayout().doubleValue();				
				}
				ttotal = ttotal + t + co.getDropObject().getTotal().doubleValue();
				%>
			<td>
				$<%= BarappUtil.doubleToString( new Double(t) ) %>
			</td>
				
			<td>
				$<%= BarappUtil.doubleToString( new Double( co.getDropObject().getTotal().doubleValue() + t) ) %>
			</td>			
			</tr>	
		<% } catch (Exception e) { logger.error(e); %><%= cashoutId %><% } %>
		<% } %>
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
		