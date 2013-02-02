		<table cellpadding="5" cellspacing="0">
			<tr>
				<td><b>Shortages</b>:</td><td width="100%" align="center">Item</td><td align="center">Price</td>				
			</tr>
			<% Shortage[] shortages = (Shortage[])cashout.getShortages().toArray( new Shortage[cashout.getShortages().size()]); %>
			<% for ( int i = 0; i < shortages.length; i++ ) { %>
				<tr>
					<td colspan="2" style="border: solid black 1px;padding-left:25px; padding-right:25px"><font size="-1"><%= shortages[i].getDescription() %></font></td><td 
					style="border: solid black 1px;padding-left:25px; padding-right:25px"><b><%= BarappUtil.doubleToString(shortages[i].getTotal()) %></b></td>
				</tr>
			<% } %>
		</table>