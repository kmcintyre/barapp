<% 
boolean edit = false;
if ( request.getParameter("edit") != null &&  request.getParameter("edit").equals("true") ) {
	edit = true;
}
%>
	<tr>	
		<td align="right">$20</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="twenty" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("twenty") %>"
				>
			<% } else { %>
				<%= request.getParameter("twenty") %>
			<% } %>
		</td>
	</tr>
	<tr>		
		<td align="right">$10</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="ten" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("ten") %>"			
				>
			<% } else { %>
				<%= request.getParameter("ten") %>
			<% } %>				
		</td>
	</tr>
	<tr>			
		<td align="right">$5</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="five" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("five") %>"						
				>
			<% } else { %>
				<%= request.getParameter("five") %>
			<% } %>								
		</td>
	</tr>
	<tr>
		<td align="right">$1</td>
		<td>
			<% if ( edit ) { %>
				<input type="text" name="single" size="3"
				onkeydown="return allNumbers(event,this.form)"
				onBlur="javascript:money(this.form)"
				onFocus="javascript:this.select()"
				value="<%= request.getParameter("single") %>"			
				>
			<% } else { %>
				<%= request.getParameter("single") %>
			<% } %>				
		</td>
	</tr>