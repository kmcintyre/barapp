		<table cellpadding="5">
			<tr>
				<td width="100%"><font size="+1"><b>Drawer</b>:</font></td><td align="center">Start</td><td align="center">End</td>
			</tr>
			<tr>
				<td>20's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawer().getTwenty().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawer().getTwenty().intValue() %></b></td>
			</tr>
			<tr>
				<td>10's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawer().getTen().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawer().getTen().intValue() %></b></td>
			</tr>			
			<tr>
				<td>5's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawer().getFive().intValue() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawer().getFive().intValue() %></b></td>
			</tr>			
			<tr>
				<td>1's</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getStartDrawer().getSingle() %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= cashout.getDrawer().getSingle() %></b></td>
			</tr>						
			<tr>
				<td colspan="3" style="padding:2px"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="1"/></td>
			</tr>
			<tr>
				<td>Total</td><td style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getStartDrawer().getTotal()) %></b></td><td 
				style="border: solid black 1px;padding-left:40px; padding-right:40px"><b><%= BarappUtil.doubleToString(cashout.getDrawer().getTotal()) %></b></td>
			</tr>			
		</table>
