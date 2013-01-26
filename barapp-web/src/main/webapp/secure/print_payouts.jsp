<% try { %>
			<% Payout[] payouts = (Payout[])cashout.getPayouts().toArray(new Payout[cashout.getPayouts().size()]); %>
			<% for ( int i = 0; i < payouts.length; i++ ) { %>
				<tr>
					<td style="padding-left:10px; padding-right:10px"><nobr><%= payouts[i].getName() %></nobr></td><td
					style="padding-left:10px; padding-right:10px"><nobr><%= payouts[i].getDescription() %></nobr></td><td align="center" 
					style="padding-left:10px; padding-right:10px"><b><%= BarappUtil.doubleToString(payouts[i].getTotal()) %></b></td>
					<td></td>
				</tr>
			<% } %>
<% } catch (Exception e) { %><%= e %><% } %>			
