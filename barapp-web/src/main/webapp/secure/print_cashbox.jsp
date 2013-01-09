		<table cellpadding="5" cellspacing="0">
			<tr>
				<td width="100%"><font size="+1"><b>Blackbox</b>:</font></td><td align="center">Start</td><td align="center">End</td>
			</tr>
			<tr>
				<td>100's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashboxObject().getHundred().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashboxObject().getHundred().intValue() %></b></td>
			</tr>			
			<tr>
				<td>50's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashboxObject().getFifty().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashboxObject().getFifty().intValue()  %></b></td>
			</tr>
			<tr>
				<td>20's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashboxObject().getTwenty().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashboxObject().getTwenty().intValue() %></b></td>
			</tr>
			<tr>
				<td>10's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashboxObject().getTen().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashboxObject().getTen().intValue() %></b></td>
			</tr>			
			<tr>
				<td>5's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashboxObject().getFive().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashboxObject().getFive().intValue() %></b></td>
			</tr>			
			<tr>
				<td>1's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashboxObject().getSingle() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashboxObject().getSingle() %></b></td>
			</tr>
			<tr>
				<td colspan="3" style="padding:2px"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="1"/></td>
			</tr>																		
			<tr>
				<td>Total</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getStartCashboxObject().getTotal()) %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getCashboxObject().getTotal()) %></b></td>
			</tr>			
		</table>		
