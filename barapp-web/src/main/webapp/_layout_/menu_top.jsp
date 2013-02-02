<div style="float:left;margin:8px 8px 8px 8px;">
	<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="138" height="27"/>
</div>
<div style="float:right;margin:8px 0px 8px 8px;">

	<% if ( request.isUserInRole("ROLE_ADMIN") ) { %>
		<button class="globalNav" onclick="document.location.href='<%= request.getContextPath() %>/secure/'">Secure</button>
		<button class="globalNav" onclick="document.location.href='<%= request.getContextPath() %>/admin/'">Admin</button>
		&nbsp;
		&nbsp;
		&nbsp;
		&nbsp;
		&nbsp;
	<% } %>
	<button class="globalNav" onclick="document.location.href='<%= request.getContextPath() %>/logout.jsp'">Logout</button>
</div>