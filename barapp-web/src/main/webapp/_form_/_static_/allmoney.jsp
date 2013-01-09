<% 
boolean edit = false;
if ( request.getParameter("edit") != null &&  request.getParameter("edit").equals("true") ) {
	edit = true;
}
%>

	<tr>
		<td align="right">25&#162;</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="quarter" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("quarter") %>"
				>
			<% } else { %>
				<%= request.getParameter("quarter") %>
			<% } %>
		</td>
	</tr>	
	<tr>
		<td align="right">10&#162;</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="dime" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("dime") %>"			
				>
			<% } else { %>
				<%= request.getParameter("dime") %>
			<% } %>				
		</td>
	</tr>	
	<tr>
		<td align="right">5&#162;</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="nickel" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("nickel") %>"						
				>
			<% } else { %>
				<%= request.getParameter("nickel") %>
			<% } %>				
		</td>
	</tr>	
	<tr>
		<td align="right">1&#162;</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="penny" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("penny") %>"			
				>
			<% } else { %>
				<%= request.getParameter("penny") %>
			<% } %>				
		</td>
	</tr>