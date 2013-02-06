<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.nwice.barapp.report.ReportConstant"%>
<html>
<style type="text/css">
table.theform td { 
FONT-SIZE: 10pt; 
FONT-FAMILY: Times New Roman
}
</style>
<body bgcolor="#EEEEEE">
<div style="width:100%">

<%
int width = ReportConstant.defaultWidth; 
int height = ReportConstant.defaultHeight; 
String report = new String("");

if ( request.getCookies() != null ) {
for ( int c = 0; c < request.getCookies().length; c++ ) {
	Cookie cookie = request.getCookies()[c];
	if ( cookie.getName().equals("report_height") ) {
		try {
			height = Integer.parseInt(cookie.getValue());
		} catch (Exception e) {}
	} else if ( cookie.getName().equals("report_width") ) {
		try {
			width = Integer.parseInt(cookie.getValue());
		} catch (Exception e) {}
	}
}}
%>


<% if ( request.getParameter("report") != null ) { %>
	<% report = request.getParameter("report"); %>
<% } %>

<% if ( request.getParameter("width") != null ) { %>
	<% 
	width = Integer.parseInt(request.getParameter("width"));
	Cookie cookie = new Cookie ("report_width", "" + width);
	cookie.setMaxAge(365 * 24 * 60 * 60);
	response.addCookie(cookie);
	%>
<% } %>

<% if ( request.getParameter("height") != null ) { %>
	<% 
	height = Integer.parseInt(request.getParameter("height")); 
	Cookie cookie = new Cookie ("report_height", "" + height);
	cookie.setMaxAge(365 * 24 * 60 * 60);
	response.addCookie(cookie);
	%>
<% } %>


<form action="reports.jsp" name="report"/>

<div style="float:left;margin:1 1 1 1;text-align:right;width:250" >
	<input type="submit">
</div>

<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Chart: 	
	<select name="report">
		<% for (int j = 0; j <  ReportConstant.reports.length; j++) {  
			String s = ReportConstant.reports[j].toLowerCase();
			s = s.replaceAll(" ","_");
			%>	
			<option value="<%= s %>" 
			<% if ( s.equals(report) ) { %>selected<% } %>
			><%= ReportConstant.reports[j] %>		
		<% } %>		
	</select>	
</div>

<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Size:
	<input size="3" name="width" value="<%= width %>"> x <input size="3" name="height" value="<%= height %>">
</div>

<%
String cal_setup = "/admin/report/helper/calendar_setup.jsp";
String cal = "/admin/report/helper/calendar.jsp";
%>

<jsp:include page="<%= cal_setup %>"/>
	
<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	From: 
	<jsp:include page="<%= cal %>">		
		<jsp:param name="pass_date" value='<%= ReportConstant.fromDate %>'/>		
	</jsp:include>
</div>	

<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	To: 
	<jsp:include page="<%= cal %>">		
		<jsp:param name="pass_date" value="<%= ReportConstant.toDate %>"/>		
	</jsp:include>
</div>

<% if ( report.length() > 0 ) { 
	String jsp = "/admin/report/charts/" + request.getParameter("report") + ".jsp";
	%>
	<jsp:include page="<%= jsp %>"/>
<% } else { %>
	<div style="margin:1 1 1 1;width:100%;display:inline">
	<img src="<%= request.getContextPath() %>/spacer.gif" width="1" height="<%= height %>"/>
	</div>
<%} %>


	


</form>
</div>
</body>
</html>