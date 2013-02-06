<%@ page import="com.nwice.barapp.report.*" %>
<%@ page import="com.nwice.barapp.report.service.ReportManager" %>
<%@ page import="java.util.*" %>

<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<% WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application); %>
<% ReportManager reportManager = (ReportManager)context.getBean("reportManager"); %>

<%
Date missing_from = null;
Date missing_to = null;

if ( request.getParameter(ReportConstant.fromDate) != null && request.getParameter(ReportConstant.fromDate).length() > 0 ) { 
	missing_from = ReportConstant.dateFormat.parse( request.getParameter(ReportConstant.fromDate) );	
} 

if ( request.getParameter(ReportConstant.toDate) != null && request.getParameter(ReportConstant.toDate).length() > 0 ) { 
	missing_to = ReportConstant.dateFormat.parse( request.getParameter(ReportConstant.toDate) );
}
%>

<table>
<tr><td>
<b>Missing Shifts</b>
</td></tr>

<%
List list = reportManager.getMissingShifts(missing_from, missing_to);
Iterator iter = list.iterator();
while (iter.hasNext()) {
%>
	<tr><td>
	<%= iter.next() %>
	</td></tr>
<%
}
%>
</table>




