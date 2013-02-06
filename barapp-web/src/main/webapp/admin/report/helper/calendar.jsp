<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%

long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
String pass_date = request.getParameter("pass_date");
String anchor = request.getParameter("pass_date") + "_anchor";

String pass_date_value = null;
if ( request.getParameter(pass_date) != null && request.getParameter(pass_date).length() > 0 ) { 
	pass_date_value = request.getParameter(pass_date);
}
%>

<input size="10" type="text" 
	name="<%= pass_date %>" 
	id="<%= pass_date %>" 
		<% if ( pass_date_value != null ) { %>
			value="<%= pass_date_value %> "
		<% } %>	
	><A 
		
		HREF="#" 
		onClick="cal.select(document.report.<%= pass_date %>,'<%= anchor %>','MM/dd/yyyy'); 
		return false;" 
		NAME="<%= anchor %>" 
		ID="<%= anchor %>"><img 
		ALIGN="texttop"
		src="<%= request.getContextPath() %>/img/icon-date.gif" 
		border="0"></A>