<input type="checkbox" name="<%= request.getParameter("name") %>" 
<% if ( request.getParameter("checked").equals("true") ) { %>
	checked
<% } %>
>

<%= request.getParameter("name") %>
