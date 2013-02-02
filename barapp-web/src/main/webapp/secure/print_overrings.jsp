		<table cellpadding="5" cellspacing="0">
			<tr>
				<td><b>Overrings</b>:</td><td width="100%" align="center">Item</td><td align="center">Price</td>				
			</tr>
			<% Overring[] overrings = (Overring[])cashout.getOverrings().toArray( new Overring[cashout.getOverrings().size()]); %>
			<% for ( int i = 0; i < overrings.length; i++ ) { %>
				<tr>
					<td colspan="2" style="border: solid black 1px;padding-left:25px; padding-right:25px"><font size="-1"><%= overrings[i].getDescription() %></font></td><td 
					style="border: solid black 1px;padding-left:25px; padding-right:25px"><b><%= BarappUtil.doubleToString(overrings[i].getTotal()) %></b></td>
				</tr>
			<% } %>
		</table>	
