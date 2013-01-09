		<table cellpadding="5" cellspacing="0">			
			<tr>
				<td colspan="2"><font size="+1"><b>Drop</b>:</font></td>
			</tr>
			<tr>
				<td width="100%">100's</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= cashout.getDropObject().getHundred().intValue() %></b></td>
			</tr>
			<tr>
				<td>50's</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= cashout.getDropObject().getFifty().intValue() %></b></td>
			</tr>			
			<tr>
				<td>20's</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= cashout.getDropObject().getTwenty().intValue() %></b></td>
			</tr>
			<tr>
				<td>10's</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= cashout.getDropObject().getTen().intValue() %></b></td>
			</tr>			
			<tr>
				<td>5's</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= cashout.getDropObject().getFive().intValue() %></b></td>
			</tr>			
			<tr>
				<td>1's</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= cashout.getDropObject().getSingle() %></b></td>
			</tr>
			<tr>
				<td colspan="2" style="padding:2px"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="1"/></td>
			</tr>												
			<tr>
				<td>Total</td><td style="border: solid black 1px;padding-left:80px; padding-right:80px"><b><%= BarappUtil.doubleToString(cashout.getDropObject().getTotal()) %></b></td>
			</tr>			
		</table>