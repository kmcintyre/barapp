<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.servlet.*" %>
<%@ page import="java.util.*" %>

<jsp:include page="/_layout_/header.jsp">
	<jsp:param name="title" value=" Checkout "/>
</jsp:include>

<jsp:include page="/_layout_/body_top.jsp"/>

<jsp:include page="/_layout_/menu_top.jsp"/>

<div style="clear:both">

<% 
String action = "drawer";
if ( request.getParameter("action") != null ) {
	action = request.getParameter("action");
}
%>

<% if ( request.getSession().getAttribute("cashout") != null ) { %>
	<jsp:include page="/_layout_/tab.jsp">
		<jsp:param name="tab_action" value="<%= action %>"/>
	</jsp:include>
<% } else { %>
	<div><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="600" height="28"/></div>
<% } %>

<jsp:include page="/_layout_/box_top.jsp"/>

<% if ( request.getSession().getAttribute("cashout") != null ) { %>
	<jsp:include page="cashout.jsp">
		<jsp:param name="cashout_action" value="<%= action %>"/>
	</jsp:include>
<% } else { 
	String link = request.getContextPath() + "/secure/cashout.do?start=yes";
	if ( request.getParameter("action") != null ) {
		link = link.concat("&action=" + request.getParameter("action"));
	}
	%>
	<% if ( request.isUserInRole("bartender") && ShiftServlet.needShift() ) { %>
		<a class="largeFont" href="<%= link %>">Start Cashout</a>
	<% } else if ( request.isUserInRole("admin") ) { %>

		<% if ( request.getParameter("create") == null ) { %>
			<a class="largeFont" href="<%= link %>">Demo Cashout</a>
			<br><br>
			<a class="largeFont" href="index.jsp?create=yes">Create Cashout</a>
		<% } else { %>
			<jsp:include page="/admin/create.jsp"/>
		<% } %>
	<% } else if ( request.isUserInRole("bartender") && !ShiftServlet.needShift() ) { %>
		<span class="largeFont">This shift is done.</span>
	<% } %>
<% } %>

<jsp:include page="/_layout_/box_bottom.jsp"/>
				
</div>
	
<jsp:include page="/_layout_/body_bottom.jsp"/>