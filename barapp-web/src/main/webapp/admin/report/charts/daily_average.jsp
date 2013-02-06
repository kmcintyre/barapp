<%@page contentType="text/html"%>
<%@taglib uri='/WEB-INF/cewolf.tld' prefix='cewolf' %>

<%@page import="com.nwice.barapp.report.*"%>
<%@page import="com.nwice.barapp.report.helper.*"%>
<%@page import="com.nwice.barapp.report.service.*"%>
<%@page import="java.util.*"%>
<%@page import="de.laures.cewolf.*"%>
<%@page import="de.laures.cewolf.tooltips.*"%>
<%@page import="de.laures.cewolf.links.*"%>
<%@page import="org.jfree.data.*"%>
<%@page import="org.jfree.data.time.*"%>
<%@page import="org.jfree.data.gantt.*"%>
<%@page import="org.jfree.chart.*"%>
<%@page import="org.jfree.chart.plot.*"%>
<%@page import="org.jfree.data.category.*"%>
<%@page import="org.jfree.data.general.*"%>
<%@page import="org.jfree.data.xy.*"%>
<%@page import="java.awt.*" %>

<%@ page import="org.springframework.web.context.WebApplicationContext"%>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils"%>

<%@ page import="de.laures.cewolf.taglib.CewolfChartFactory" %>
<%@ page import="org.jfree.chart.event.ChartProgressListener" %>
<%@ page import="org.jfree.chart.event.ChartProgressEvent" %>

<% int shift = ReportConstant.defaultDailyAverageShift; %>
<% int view = ReportConstant.defaultDailyAverageView; %>
<% int chart = ReportConstant.defaultDailyAverageChart; %>

<% Integer[] days = null; %>
<% Integer[] bartenders = null; %>

<%
Object[] tns = de.laures.cewolf.taglib.ChartTypes.typeList.toArray(); 
%>

<% WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(application); %>
<% ReportManager reportManager = (ReportManager)context.getBean("reportManager"); %>

<%
String title = "Daily Average";
String from = "";
String to = "";
%>

<% if ( request.getParameter("daily_average_shift") != null ) { %>
	<% shift = Integer.parseInt(request.getParameter("daily_average_shift")); %>
<% } %>

<% if ( request.getParameter("daily_average_view") != null ) { %>
	<% view = Integer.parseInt(request.getParameter("daily_average_view")); %>
<% } %>

<% if ( request.getParameter("daily_average_chart") != null ) { %>
	<% chart = Integer.parseInt(request.getParameter("daily_average_chart")); %>
<% } %>

<% if ( request.getParameter("daily_average_days") != null ) { 
	String[] temp = request.getParameterValues("daily_average_days");		
	days = new Integer[temp.length];
	for (int i = 0; i < temp.length; i++) {
		days[i] = new Integer(temp[i]);	
	}
	%>	
<% } %>

<% if ( request.getParameter("daily_average_bartenders") != null ) { 
	String[] temp = request.getParameterValues("daily_average_bartenders");		
	bartenders = new Integer[temp.length];
	for (int i = 0; i < temp.length; i++) {
		bartenders[i] = new Integer(temp[i]);	
	}
	%>	
<% } %>

	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	View: <select name="daily_average_view">
	<% for (int i = 0; i < ReportConstant.viewArray.length; i++) { %>
		<option value="<%= i %>"
		<% if ( view == i ) { %>selected<% } %>
		><%= ReportConstant.viewArray[i] %>
	<% } %>
	</select>
	</div>
	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Shift: <select name="daily_average_shift">
	<% for (int i = 0; i < ReportConstant.shiftArray.length; i++) { %>
		<option value="<%= i %>"
		<% if ( shift == i ) { %>selected<% } %>
		><%= ReportConstant.shiftArray[i] %>
	<% } %>
	</select>
	</div>
	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Sytle: <select name="daily_average_chart">
		<% for (int j = 0; j <  tns.length; j++) {  %>
			<% if ( tns[j].toString().toLowerCase().indexOf("verticalbar") > -1 ) { %>
				<option value="<%= j %>"
				<% if ( j == chart ) { %>selected<% } %>
				><%= tns[j] %>		
			<% } %>
		<% } %>		
	</select>	
	</div>
	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Days: <select name="daily_average_days" size="7" multiple="yes">
		<% for (int j = 0; j <  ReportConstant.week.length; j++) {  %>
				<option value="<%= j %>"
				<% if ( ReportUtil.arrayContains(new Integer(j), days) ) { %>selected<% } %>
				><%= ReportConstant.week[j] %>
		<% } %>		
	</select>	
	</div>

	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Bartender: 
		<%
		Map m = reportManager.getBartenders();
		%>
		<select name="daily_average_bartenders" size="<%= m.size() %>" multiple="yes">
		<% 
		Iterator iter = m.keySet().iterator();
		while ( iter.hasNext() ) {
			String b = iter.next().toString();
			Integer i = (Integer)m.get(b);
			%>
			<option value="<%= i.toString() %>"
				<% if ( ReportUtil.arrayContains(i, bartenders) ) { %>selected<% } %>
			><%= b %>
			<%
		}
		%>
	</select>	
	</div>	

<% 
DailyAverage da = (DailyAverage)context.getBean("dailyAverage");
%>

<%
da.setShift(shift);
da.setView(view);
%>

<%
pageContext.setAttribute("dailyAverage", da); 

int width = Integer.parseInt( request.getParameter("width") );
int height = Integer.parseInt( request.getParameter("height") );

if ( request.getParameter(ReportConstant.fromDate) != null && request.getParameter(ReportConstant.fromDate).length() > 0 ) { 
	try {
		Date d = ReportConstant.dateFormat.parse( request.getParameter(ReportConstant.fromDate) );	
		da.setFromDate( d );
		from = " from " + ReportConstant.dateFormat.format(d);
	} catch (Exception e) {}
} 
if ( request.getParameter(ReportConstant.toDate) != null && request.getParameter(ReportConstant.toDate).length() > 0 ) { 
	try {
		Date d = ReportConstant.dateFormat.parse( request.getParameter(ReportConstant.toDate) );
		da.setToDate( d );
		to = " to " + ReportConstant.dateFormat.format(d);
	} catch (Exception e) {}
}
if ( days != null ) {
	da.setShowDays( ReportUtil.convertArray(days) );
}
if ( bartenders != null ) {
	da.setShowBartenders( ReportUtil.convertArray(bartenders) );
}
%>

<cewolf:chart
	id="daily_average"
	title="<%= title + from + to %>"
	type="<%= tns[chart].toString() %>"
	yaxislabel="<%= ReportConstant.viewArray[view] %>">
	<cewolf:data>
		<cewolf:producer id="dailyAverage" />
	</cewolf:data>
</cewolf:chart>

<div style="margin:1 1 1 1;width:100%;display:inline">
<cewolf:img chartid="daily_average" renderer="/cewolf" width="<%= width %>" height="<%= height %>">
	<cewolf:map tooltipgeneratorid="dailyAverage" linkgeneratorid="dailyAverage"/>
</cewolf:img>
</div>