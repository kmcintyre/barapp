<% 
boolean edit = false;
if ( request.getParameter("edit") != null &&  request.getParameter("edit").equals("true") ) {
	edit = true;
}
%>
	<tr>
		<td align="right">$100</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="hundred" size="3" 
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("hundred") %>"
				>
			<% } else { %>
				<%= request.getParameter("hundred") %>
			<% } %>				
		</td>
	</tr>
	<tr>
		<td align="right">$50</td>
		<td>
			<% if ( edit ) { %>		
				<input type="text" name="fifty" size="3" onBlur="javascript:money(this.form)"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("fifty") %>"			
				>
			<% } else { %>
				<%= request.getParameter("fifty") %>
			<% } %>								
		</td>
	</tr>