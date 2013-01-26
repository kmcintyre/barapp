<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.manager.*" %>
<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<jsp:useBean id="whoami" scope="session" class="com.nwice.barapp.user.JspUserBean"/>

<% WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application); %>
<% UserManager userManager = (UserManager)context.getBean("userManager"); %>

<% if ( whoami.getUser() == null ) { %>
	<%
	whoami.setSessionId(request.getSession().getId());
	if ( request.getUserPrincipal() != null ) {  
		BarappUser uo = userManager.getUserByUsername(request.getUserPrincipal().getName());
		whoami.setUser( uo );
	}
	%>
<% } %>

<% 
SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d, yyyy");
Date displayDate = Calendar.getInstance().getTime(); 
%>

<% if ( request.getSession().getAttribute("cashout") != null ) { %>
	<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>
	<%
	displayDate = cashout.getShift().getShiftDate();
	%>
<% } %>

<table cellspacing="0" cellpadding="0" border="0" class="headerBarBack fullWidth">
	<tr>
		<td align="left"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="10" height="25"/></td>
		<% if ( whoami.getUser() != null ) { %>
			<td style="padding-top:2px;font-size:70%;color:#ffffff">
			<nobr>
				<b>User: <%= whoami.getUser().getUsername() %></b>
			</nobr>
			</td>
		<% } %>
		<td align="right" width="100%" style="padding-top:2px;font-size:70%;color:#ffffff;padding-right:15px">
			<b><%= sdf.format(displayDate) %></b>
		</td>
	</tr>
</table><table width="100%" cellpadding="0" cellspacing="0" style="border: 4px solid #5280b1">
	<tr>
		<td><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="325"/></td>			 
		<td valign="middle" align="center" style="padding:15px" width="100%">
		