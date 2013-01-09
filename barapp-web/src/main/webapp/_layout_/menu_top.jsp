<div style="float:left;margin:8px 8px 8px 8px;">
	<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="138" height="27"/>
</div>
<div style="float:right;margin:8px 0px 8px 8px;">
	<% if ( request.isUserInRole("admin") ) { %> 
		<a class="globalNav" href="<%= request.getContextPath() %>/admin/">Admin</a>
	<% } %>
	<a href="<%= request.getContextPath() %>/secure/logout.jsp"><img src="<%= request.getContextPath() %>/img/logoff.gif" border="0"></a>
</div>