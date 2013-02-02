<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.manager.*" %>
<%@ page import="com.nwice.barapp.servlet.*" %>
<%@ page import="java.util.*" %>

<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<% WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application); %>
<% ShiftManager shiftManager = (ShiftManager)context.getBean("shiftManager"); %>


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
	
	String link = request.getContextPath() + "/secure/cashout/start.do";
	
	if ( request.getParameter("action") != null ) {
		link = link.concat("&action=" + request.getParameter("action"));
	}
	%>
	<% if ( request.isUserInRole("ROLE_BARTENDER") && shiftManager.needShift() ) { %>
	
		<a class="largeFont" href="<%= link %>?start=yes">Start Cashout</a>
		
	<% } else if ( request.isUserInRole("ROLE_ADMIN") ) { %>

		<% if ( request.getParameter("create") == null ) { %>
			<a class="largeFont" href="<%= request.getContextPath() %>/admin/cashout/demo.do">Demo Cashout</a>
			<br><br>
			<a class="largeFont" href="<%= request.getContextPath() %>/secure/index.jsp?create=yes">Create Cashout</a>
		<% } else { %>
			<jsp:include page="/admin/create.jsp"/>
		<% } %>
	<% } else if ( request.isUserInRole("ROLE_BARTENDER") && !shiftManager.needShift() ) { %>
		
		<span class="largeFont">This shift is already complete.</span>
		
	<% } %>
<% } %>

<jsp:include page="/_layout_/box_bottom.jsp"/>
				
</div>
	
<jsp:include page="/_layout_/body_bottom.jsp"/>