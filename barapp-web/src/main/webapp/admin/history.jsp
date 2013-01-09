<%@ taglib uri="/WEB-INF/zab.tld" prefix="calendar" %>


<style>

.test {
	color:white;
	background:black;
}
.calendarTitleStyle {
	text-align:center;
}
.calendarMonthStyle {
	vertical-align:top;
	border:thin double black;
	background-color:#B0B0B0;
	font-size:11;
}
.calendarActiveDayStyle{
	vertical-align:top;
	border:thin double black;
	background-color:#B0B0B0;
	font-size:11;
}
.calendarEmptyDayStyle {
	border:1px thin white;
	background-color:white;
	font-size:11;
}
.calendarNextLinkStyle {
	text-align:right;
}
</style>

<%
if ( request.getParameter("drop_param") == null || request.getSession().getAttribute("drop_param") == null ) { 
	if ( request.getSession().getAttribute("drop_param") == null ) {
		session.setAttribute("drop_param", "false");
	} else {
		session.setAttribute("drop_param", "false");
	}
} else {
	session.setAttribute("drop_param", "true");
}
if ( request.getParameter("payouts_param") == null || request.getSession().getAttribute("payouts_param") == null ) { 
	if ( request.getSession().getAttribute("payouts_param") == null ) {
		session.setAttribute("payouts_param", "false");
	} else {
		session.setAttribute("payouts_param", "false");
	}
} else {
	session.setAttribute("payouts_param", "true");
}
if ( request.getParameter("bartenders_param") == null || request.getSession().getAttribute("bartenders_param") == null ) { 
	if ( request.getSession().getAttribute("bartenders_param") == null ) {
		session.setAttribute("bartenders_param", "true");
	} else {
		session.setAttribute("bartenders_param", "false");
	}
} else {
	session.setAttribute("bartenders_param", "true");
}
if ( request.getParameter("daily_param") == null || request.getSession().getAttribute("daily_param") == null ) { 
	if ( request.getSession().getAttribute("daily_param") == null ) {
		session.setAttribute("daily_param", "true");
	} else {
		session.setAttribute("daily_param", "false");
	}
} else {
	session.setAttribute("daily_param", "true");
}
if ( request.getParameter("tips_param") == null || request.getSession().getAttribute("tips_param") == null ) { 
	if ( request.getSession().getAttribute("tips_param") == null ) {
		session.setAttribute("tips_param", "false");
	} else {
		session.setAttribute("tips_param", "false");
	}
} else {
	session.setAttribute("tips_param", "true");
}
if ( request.getParameter("amtotal_param") == null || request.getSession().getAttribute("amtotal_param") == null ) { 
	if ( request.getSession().getAttribute("amtotal_param") == null ) {
		session.setAttribute("amtotal_param", "true");
	} else {
		session.setAttribute("amtotal_param", "false");
	}
} else {
	session.setAttribute("amtotal_param", "true");
}
if ( request.getParameter("pmtotal_param") == null || request.getSession().getAttribute("pmtotal_param") == null ) { 
	if ( request.getSession().getAttribute("pmtotal_param") == null ) {
		session.setAttribute("pmtotal_param", "true");
	} else {
		session.setAttribute("pmtotal_param", "false");
	}
} else {
	session.setAttribute("pmtotal_param", "true");
}
%>
<div style="font-size:12;">
<% if ( request.getParameter("calendar") != null ) { %>
	<form action="index.jsp" name="calform">
	<input type="hidden" name="action" value="calendar"/>
<% } else { %>
	<form action="index.jsp" name="calform">
	<input type="hidden" name="action" value="calendar">
<% } %>
Daily: <input onClick="document.calform.submit();" type="checkbox" name="daily_param" 
	<% if ( session.getAttribute("daily_param").toString().equals("true") ) { %>
		checked
	<% } %>
	>
	
AM: <input onClick="document.calform.submit();" type="checkbox" name="amtotal_param" 
	<% if ( session.getAttribute("amtotal_param").toString().equals("true") ) { %>
		checked
	<% } %>
	>
	
PM: <input onClick="document.calform.submit();" type="checkbox" name="pmtotal_param" 
	<% if ( session.getAttribute("pmtotal_param").toString().equals("true") ) { %>
		checked
	<% } %>
	>		

Drop: <input onClick="document.calform.submit();" type="checkbox" name="drop_param" 
	<% if ( session.getAttribute("drop_param").toString().equals("true") ) { %>
		checked
	<% } %>
	>

Payouts: <input onClick="document.calform.submit();" type="checkbox" name="payouts_param" 
	<% if ( session.getAttribute("payouts_param").toString().equals("true") ) { %>
		checked
	<% } %>	
	>
Bartenders: <input onClick="document.calform.submit();" type="checkbox" name="bartenders_param" 
	<% if ( session.getAttribute("bartenders_param").toString().equals("true") ) { %>
		checked
	<% } %>
	>
	
Tips: <input onClick="document.calform.submit();" type="checkbox" name="tips_param" 
	<% if ( session.getAttribute("tips_param").toString().equals("true") ) { %>
		checked
	<% } %>
	>
	
<% if ( request.getParameter("date") != null ) { %>
	<input type="hidden" name="date" value="<%= request.getParameter("date") %>"/>
<% } %>		
</form>
</div>
<table width="100%">
<tr><td align="center">
	<calendar:calendar drop="true" 
	daily="<%= new Boolean(session.getAttribute("daily_param").toString()) %>" 
	drop="<%= new Boolean(session.getAttribute("drop_param").toString()) %>" 
	payouts="<%= new Boolean(session.getAttribute("payouts_param").toString()) %>" 	
	bartenders="<%= new Boolean(session.getAttribute("bartenders_param").toString()) %>" 		
	tips="<%= new Boolean(session.getAttribute("tips_param").toString()) %>" 		
	amtotal="<%= new Boolean(session.getAttribute("amtotal_param").toString()) %>" 		
	pmtotal="<%= new Boolean(session.getAttribute("pmtotal_param").toString()) %>" 		
	dayHeight="100" dayWidth="150"/>
</td></tr>
</table>