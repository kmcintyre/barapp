

		<% ShiftWorker[] workers = (ShiftWorker[])cashout.getShift().getShiftWorkers().toArray(new ShiftWorker[cashout.getShift().getShiftWorkers().size()]); %>
			<% for ( int i = 0; i < workers.length; i++ ) { %>
				<tr>
					<td style="padding-left:10px; padding-right:10px"><%= workers[i].getDescription() %></td><td 
					style="padding-left:10px; padding-right:10px"><nobr><%= userManager.getUserById( workers[i].getUser() ).getLastname() %>, <%= userManager.getUserById( workers[i].getUser() ).getFirstname().substring(0,1) %></nobr></td><td align="center" 
					style="padding-left:10px; padding-right:10px"><b><%= BarappUtil.doubleToString(workers[i].getPayout()) %></b></td><td align="center" 
					style="padding-left:10px; padding-right:10px"><b><%= BarappUtil.doubleToString(workers[i].getTips()) %></b></td>
				</tr>
			<% } %>