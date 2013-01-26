<% 
String userId = "";
String firstname = "";
String lastname = "";
String username = "";
String password = "";
String role = "";
String active = "";

if ( request.getParameter("userId") != null ) { 
	userId = request.getParameter("userId");
	firstname = request.getParameter("firstname");
	lastname = request.getParameter("lastname");
	username = request.getParameter("username");
	password = request.getParameter("password");
	role = request.getParameter("role");
	active = request.getParameter("active");
} 
%>

<form action="<%= request.getContextPath() %>/admin/user_do" method="post">
	<input type="hidden" name="action" value="users"/>
	<% if ( userId.length() > 0 ) { %>
		<input type="hidden" name="userId" value="<%= userId %>"/>
	<% } %>
	Username: <input type="text" name="username" value="<%= username %>"/><br>
	Firstname: <input type="text" name="firstname" value="<%= firstname %>"/><br>
	Lastname: <input type="text" name="lastname" value="<%= lastname %>"/><br>
	Password: <input type="text" name="password" value="<%= password %>"/><br>
	Role: 
		<select name="role">
		<option
		<% if ( role.equals("ROLE_ADMIN") ) { %>selected<% } %>
		>admin
		<option
		<% if ( role.equals("ROLE_BARTENDER") || role.equals("") ) { %>selected<% } %>
		>bartender
		<option
		<% if ( role.equals("ROLE_BARBACK") ) { %>selected<% } %>
		>barback		
		</select>
	Active: <input type="checkbox" name="active" <% if ( active.equals("true") ) { %>checked<% } %>/><br>

	<input type="submit">
</form>