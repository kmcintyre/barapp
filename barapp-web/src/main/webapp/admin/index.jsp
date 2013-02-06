<%
request.getSession().removeAttribute("cashout");
request.getSession().removeAttribute("cashoutId");
%>

<jsp:include page="/_layout_/header.jsp">
	<jsp:param name="title" value=" Admin "/>
</jsp:include>

<jsp:include page="/_layout_/body_top.jsp"/>

<jsp:include page="/_layout_/menu_top.jsp"/>

<div style="clear:both">
<div><img  src="<%= request.getContextPath() %>/img/spacer.gif" width="600" height="28"/></div>
<jsp:include page="/_layout_/box_top.jsp"/>
<br>

<% if ( request.getParameter("action") == null ) { %>

	<a href="<%= request.getContextPath() %>/admin/index.jsp?action=users">Users</a>
	
	<br>
	<br>
	
	<a href="<%= request.getContextPath() %>/admin/index.jsp?action=shifts">Shifts</a>
	
	<br>
	<br>
	
	<a href="<%= request.getContextPath() %>/admin/index.jsp?action=cashouts">Cashouts</a>

	<br>
	<br>
	
	<a href="<%= request.getContextPath() %>/admin/index.jsp?action=payouts">Payouts</a>	

	<br>
	<br>
	
	<a href="<%= request.getContextPath() %>/admin/index.jsp?action=calendar">Calendar</a>	
	
	<br>
	<br>

	<a href="<%= request.getContextPath() %>/admin/reports.jsp">Reports</a>


<% } else if ( request.getParameter("action").equals("users") )  { %> 
	<jsp:include page="users.jsp"/>
<% } else if ( request.getParameter("action").equals("cashouts") )  { %> 
	<jsp:include page="cashouts.jsp"/>
<% } else if ( request.getParameter("action").equals("shifts") )  { %> 
	<jsp:include page="shifts.jsp"/>
<% } else if ( request.getParameter("action").equals("payouts") )  { %> 
	<jsp:include page="payouts.jsp"/>	
<% } else if ( request.getParameter("action").equals("calendar") )  { %> 
	<jsp:include page="history.jsp"/>
<% } %>

<jsp:include page="/_layout_/box_bottom.jsp"/>
<div style="clear:both">

<jsp:include page="/_layout_/body_bottom.jsp"/>
