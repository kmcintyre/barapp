<%@ page import="com.nwice.barapp.model.BarappUser" %>

<jsp:useBean id="userManager" scope="application" class="com.nwice.barapp.manager.UserManager"/>

<% if ( request.getParameter("useraction") == null ) { %>

	<table border="0" cellpadding="2" cellspacintg="0">
	<tr><td colspan="2">Users: <a href="<%= request.getContextPath() %>/admin/index.jsp?action=users&useraction=add_user">Add User</a></td></tr>
	<% BarappUser[] users = userManager.getAllUsers(); %>

	<% for ( int i = 0; i < users.length; i++ ) { 
		BarappUser uo = users[i];
	%>
	<tr>
	<td>
		<%= uo.getLastname() %>, <%= uo.getFirstname() %>
	</td>
	<td>
		<a href="<%= request.getContextPath() %>/admin/index.jsp?action=users&useraction=edit_user&userId=<%= uo.getUserId() %>">Edit</a>
	</td>
	</tr>	
	<% } %>
	</table>

<% } else if ( request.getParameter("useraction").equals("add_user") ) { %>
	Add User
	<jsp:include page="_form_/user_form.jsp"/>	
<% } else if ( request.getParameter("useraction").equals("edit_user") ) { 
	BarappUser uo = userManager.getUserById( new Integer(request.getParameter("userId")));
	%>
	Edit User
	<jsp:include page="_form_/user_form.jsp">
		<jsp:param name="userId" value="<%= uo.getUserId() %>"/>
		<jsp:param name="firstname" value="<%= uo.getFirstname() %>"/>
		<jsp:param name="lastname" value="<%= uo.getLastname() %>"/>
		<jsp:param name="username" value="<%= uo.getUsername() %>"/>
		<jsp:param name="password" value="<%= uo.getPassword() %>"/>
		<jsp:param name="role" value="<%= uo.getRole() %>"/>
		<jsp:param name="active" value="<%= uo.getActive() %>"/>
	</jsp:include>	
<% } %>
