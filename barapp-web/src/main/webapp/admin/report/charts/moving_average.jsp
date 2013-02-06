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

<%@ page import="de.laures.cewolf.taglib.CewolfChartFactory" %>
<%@ page import="org.jfree.chart.event.ChartProgressListener" %>
<%@ page import="org.jfree.chart.event.ChartProgressEvent" %>

<% int shift = ReportConstant.defaultMovingAverageShift; %>
<% int view = ReportConstant.defaultMovingAverageView; %>
<% int chart = ReportConstant.defaultMovingAverageChart; %>
<% int period = ReportConstant.defaultMovingAveragePeriod; %>
<% int interval = ReportConstant.defaultMovingAverageInterval; %>

<% Integer[] days = null; %>

<%
Object[] tns = de.laures.cewolf.taglib.ChartTypes.typeList.toArray(); 
%>


<%
boolean scatter = false;
String title = "";
String from = "";
String to = "";
%>

<% if ( request.getParameter("scatter") != null ) { %>
	<% scatter = true; %>
<% } %>


<% if ( request.getParameter("moving_average_shift") != null ) { %>
	<% shift = Integer.parseInt(request.getParameter("moving_average_shift")); %>
<% } %>

<% if ( request.getParameter("moving_average_view") != null ) { %>
	<% view = Integer.parseInt(request.getParameter("moving_average_view")); %>
<% } %>

<% if ( request.getParameter("moving_average_chart") != null ) { %>
	<% chart = Integer.parseInt(request.getParameter("moving_average_chart")); %>
<% } %>

<% if ( request.getParameter("moving_average_period") != null ) { %>
	<% period = Integer.parseInt(request.getParameter("moving_average_period")); %>
<% } %>

<% if ( request.getParameter("moving_average_interval") != null ) { %>
	<% interval = Integer.parseInt(request.getParameter("moving_average_interval")); %>
<% } %>

<% if ( request.getParameter("moving_average_days") != null ) { 
	String[] temp = request.getParameterValues("moving_average_days");		
	days = new Integer[temp.length];
	for (int i = 0; i < temp.length; i++) {
		days[i] = new Integer(temp[i]);	
	}
	%>	
<% } %>

<%
String[] intervalP = FlexMovingAverage.getIntervalPeriods(interval); 
int intervalA = 0; 
%>

	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	View: <select name="moving_average_view">
	<% for (int i = 0; i < ReportConstant.viewArray.length; i++) { %>
		<option value="<%= i %>"
		<% if ( view == i ) { %>selected<% } %>
		><%= ReportConstant.viewArray[i] %>
	<% } %>
	</select>
	</div>
	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Shift: <select name="moving_average_shift">
	<% for (int i = 0; i < ReportConstant.shiftArray.length; i++) { %>
		<option value="<%= i %>"
		<% if ( shift == i ) { %>selected<% } %>
		><%= ReportConstant.shiftArray[i] %>
	<% } %>
	</select>
	</div>
	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Sytle: <select name="moving_average_chart">
		<% for (int j = 0; j <  ReportConstant.movingAverageCharts.length; j++) {  %>
				<option value="<%= ReportConstant.movingAverageCharts[j] %>"
				<% if ( ReportConstant.movingAverageCharts[j] == chart ) { %>selected<% } %>
				><%= tns[ReportConstant.movingAverageCharts[j]].toString() %>		
		<% } %>
			
			</select> 
			show <input type="checkbox" name="scatter" 
				<% if ( scatter ) { %>checked<% } %>
				>
	
	</div>	

	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Interval: 
		<select name="moving_average_interval" onChange="document.report.moving_average_period.selectedIndex = 0; document.report.submit()">
		<% for (int j = 0; j <  ReportConstant.movingAverageIntervals.length; j++) {  %>
				<option value="<%= j %>"
				<% if ( j == interval ) { 
					title = ReportConstant.movingAverageIntervals[j].substring(0,1).toUpperCase() + ReportConstant.movingAverageIntervals[j].substring(1);
					%>selected<% } %>
				><%= ReportConstant.movingAverageIntervals[j] %>
		<% } %>		
		</select>	
	</div>
	
	<div style="float:left;clear:both;margin:1 1 1 1;text-align:right;width:250">
	Period: 
		<select name="moving_average_period">
		<% for (int j = 0; j <  intervalP.length; j++) {  %>
				<% 
				int ip = Integer.parseInt(intervalP[j].substring(0, intervalP[j].indexOf("-") ));
				%>
				<option value="<%= ip %>"
				<% if ( ip == period ) { 
					intervalA = j;
					%>selected<% } %>
				><%= intervalP[j] %>
		<% } %>		
		</select>	
	</div>
	
	
<% 
FlexMovingAverage wma = new FlexMovingAverage(interval);
%>

<%

int width = Integer.parseInt( request.getParameter("width") );
int height = Integer.parseInt( request.getParameter("height") );

if ( request.getParameter(ReportConstant.fromDate) != null && request.getParameter(ReportConstant.fromDate).length() > 0 ) { 
	try {
		Date d = ReportConstant.dateFormat.parse( request.getParameter(ReportConstant.fromDate) );	
		wma.setFromDate( d );
		from = " from " + ReportConstant.dateFormat.format(d);
	} catch (Exception e) {}
} 
if ( request.getParameter(ReportConstant.toDate) != null && request.getParameter(ReportConstant.toDate).length() > 0 ) { 
	try {
		Date d = ReportConstant.dateFormat.parse( request.getParameter(ReportConstant.toDate) );
		wma.setToDate( d );
		to = " to " + ReportConstant.dateFormat.format(d);
	} catch (Exception e) {}
}

wma.setShift(shift);
wma.setView(view);
pageContext.setAttribute("FlexMovingAverage", wma); 

MovingAverageHelper mah = new MovingAverageHelper();
mah.setPeriod(period);
mah.setPeriodString(intervalP[intervalA]);
mah.setTimeSeries(wma.getTimeSeries());

pageContext.setAttribute("MovingAverageHelper", mah); 

%>

<cewolf:overlaidchart 
	id="moving_average" 
	title="<%= title %>" 
	type="overlaidxy" 
	xaxistype="date"
	yaxistype="number"
	xaxislabel="Date" 
	yaxislabel="<%= ReportConstant.viewArray[view] %>">
	
	<% if ( scatter ) { %>
	<cewolf:plot type="<%= tns[chart].toString() %>">
	    <cewolf:data>
	        <cewolf:producer id="FlexMovingAverage"/>
	    </cewolf:data>
	</cewolf:plot>	
	<% } %>		
	
	<cewolf:plot type="xyline">
		<cewolf:data>
			<cewolf:producer id="MovingAverageHelper"/>
		</cewolf:data>
	</cewolf:plot>
	
	
</cewolf:overlaidchart>

<cewolf:img chartid="moving_average" renderer="/cewolf" width="<%= width %>" height="<%= height %>">
	<cewolf:map tooltipgeneratorid="FlexMovingAverage"/>
</cewolf:img>

