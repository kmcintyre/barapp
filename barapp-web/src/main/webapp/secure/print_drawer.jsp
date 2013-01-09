		<table cellpadding="5" cellspacing="0">
			<tr>
				<td width="100%"><font size="+1"><b>Drawer</b>:</font></td><td align="center">Start</td><td align="center">End</td>
			</tr>
			<tr>
				<td>20's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawerObject().getTwenty().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawerObject().getTwenty().intValue() %></b></td>
			</tr>
			<tr>
				<td>10's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawerObject().getTen().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawerObject().getTen().intValue() %></b></td>
			</tr>			
			<tr>
				<td>5's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawerObject().getFive().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawerObject().getFive().intValue() %></b></td>
			</tr>			
			<tr>
				<td>1's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawerObject().getSingle() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawerObject().getSingle() %></b></td>
			</tr>						
			<tr>
				<td colspan="3" style="padding:2px"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="1"/></td>
			</tr>
			<tr>
				<td>Total</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getStartDrawerObject().getTotal()) %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getDrawerObject().getTotal()) %></b></td>
			</tr>			
		</table>
