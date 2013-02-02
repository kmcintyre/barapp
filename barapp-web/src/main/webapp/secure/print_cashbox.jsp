		<table cellpadding="5">
			<tr>
				<td width="100%"><font size="+1"><b>Blackbox</b>:</font></td><td align="center">Start</td><td align="center">End</td>
			</tr>
			<tr>
				<td>100's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashbox().getHundred().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashbox().getHundred().intValue() %></b></td>
			</tr>			
			<tr>
				<td>50's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashbox().getFifty().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashbox().getFifty().intValue()  %></b></td>
			</tr>
			<tr>
				<td>20's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashbox().getTwenty().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashbox().getTwenty().intValue() %></b></td>
			</tr>
			<tr>
				<td>10's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashbox().getTen().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashbox().getTen().intValue() %></b></td>
			</tr>			
			<tr>
				<td>5's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashbox().getFive().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashbox().getFive().intValue() %></b></td>
			</tr>			
			<tr>
				<td>1's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartCashbox().getSingle() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getCashbox().getSingle() %></b></td>
			</tr>
			<tr>
				<td colspan="3" style="padding:2px"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="1"/></td>
			</tr>																		
			<tr>
				<td>Total</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getStartCashbox().getTotal()) %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getCashbox().getTotal()) %></b></td>
			</tr>			
		</table>		
