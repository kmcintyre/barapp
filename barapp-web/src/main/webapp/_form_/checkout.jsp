<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.util.BarappUtil" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<% boolean warnings = false; %>
<% boolean dealbreaker = false; %>

<table width="250">
<tr><td class="largeFont">
Errors:<br>
<% if ( cashout.getShift().getShiftWorkers().size() == 0 ) { 
	dealbreaker = true;
	%>
	<div style="margin-left:20px">
	<font color="red">
		Nobody bartended!
	</font>
	</div>
<% } %>

<% if ( request.isUserInRole("ROLE_ADMIN") && cashout.getCashoutId() == null && request.getSession().getAttribute("adminsave") == null ) { 
	dealbreaker = true;
	%>
	<div style="margin-left:20px">
	<font color="red">
		Admin can't save cashout - see <a href="<%= request.getContextPath() %>/secure/print.jsp">Print Out</a>
	</font>
	</div>
<% } %>

<% if ( !dealbreaker ) { %>
	<div style="margin-left:20px">
	No Error<br>
	</div>
<% } %>


<div text-align="left">
Warnings:<br>
<% if ( !BarappUtil.doubleToString( cashout.getDrawer().getTotal() ).equals("400") ) { 
	warnings = true;
	%>
	<div style="margin-left:20px">
	<font color="red">
		Drawer Total is not $400
	</font>
	</div>
<% } %>

<% if ( !BarappUtil.doubleToString( cashout.getCashbox().getTotal() ).equals("1000") ) { 
	warnings = true; %>
	<div style="margin-left:20px">
	<font color="red">
		Blackbox Total is not $1000
	</font>
	</div>
<% } %>

<% if ( BarappUtil.doubleToString( cashout.getDrop().getTotal() ).equals("0") ) { 
	warnings = true; %>
	<div style="margin-left:20px">
	<font color="red">
		Drop is $0
	</font>
	</div>
<% } %>
<% if ( !warnings ) { %>
	<div style="margin-left:20px">
	No Warnings
	</div>
<% } %>

</td></tr>
</table>
<br>
<% if ( !dealbreaker ) { %>
	<form action="<%= request.getContextPath() %>/secure/cashout/save.do" method="post">
		<input type="submit" class="largeFont" value=" Save & Print ">
	</form>
<% } %>
