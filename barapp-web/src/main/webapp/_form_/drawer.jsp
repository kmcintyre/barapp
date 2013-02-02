<%@ page import="com.nwice.barapp.util.BarappUtil" %>
<%@ page import="com.nwice.barapp.servlet.*" %>
<%@ page import="org.apache.log4j.Logger" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<% Logger logger = Logger.getLogger("drawer.jsp"); %>

<!---
<div style="margin:10;float:left"><a class="smallFont" href="index.jsp?action=drawer&previous=yes">import prev</a></div>
--->

<%
if ( request.getParameter("previous") != null ) { 
	try {
		DrawerServlet.importPrevious(cashout);
		} catch (Exception e) {
			logger.info( e.toString() + "-No Previous?" + cashout.getShift().getShiftDate());
		}
}
%>

<table class="tbl4p">
	<form name="drawer" action="<%= request.getContextPath() %>/secure/drawer.do">
	<input type="hidden" name="action"/>
	<tr>
		<td></td>
		<td class="largeFont" align="center">
			<b>Start</b>
		</td>
		<td class="largeFont" align="center">
			<b>End</b>
		</td>		
	</tr>
	<tr>
		<td class="largeFont">
			20's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="twenty_start"/>
				<jsp:param name="value" value="<%= cashout.getStartDrawer().getTwenty() %>"/>
				<jsp:param name="tabIndex" value="1"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="twenty"/>
				<jsp:param name="value" value="<%= cashout.getDrawer().getTwenty() %>"/>
				<jsp:param name="tabIndex" value="5"/>
			</jsp:include>
		</td>		
	</tr>
	<tr>
		<td class="largeFont">
			10's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="ten_start"/>
				<jsp:param name="value" value="<%= cashout.getStartDrawer().getTen() %>"/>
				<jsp:param name="tabIndex" value="2"/>				
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="ten"/>
				<jsp:param name="value" value="<%= cashout.getDrawer().getTen() %>"/>
				<jsp:param name="tabIndex" value="6"/>				
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			5's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="five_start"/>
				<jsp:param name="value" value="<%= cashout.getStartDrawer().getFive() %>"/>
								<jsp:param name="tabIndex" value="3"/>				
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="five"/>
				<jsp:param name="value" value="<%= cashout.getDrawer().getFive() %>"/>
								<jsp:param name="tabIndex" value="7"/>				
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			1's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="single_start"/>
				<jsp:param name="value" value="<%= cashout.getStartDrawer().getSingle() %>"/>
								<jsp:param name="tabIndex" value="4"/>				
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="single"/>
				<jsp:param name="value" value="<%= cashout.getDrawer().getSingle() %>"/>
								<jsp:param name="tabIndex" value="8"/>				
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			Total
		</td>
		<td>
			<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/autototalform.jsp">
				<jsp:param name="denom" value="total_start"/>
				<jsp:param name="value" value="<%= BarappUtil.doubleToString(cashout.getStartDrawer().getTotal()) %>"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/autototalform.jsp">
				<jsp:param name="denom" value="total"/>
				<jsp:param name="value" value="<%= BarappUtil.doubleToString(cashout.getDrawer().getTotal()) %>"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td colspan="3" align="right">
			<input type="button" class="largeFont" value=" Next " onClick="submitForm('cashbox')">
		</td>
	</tr>	
	</form>	
</table>
<script>
	setFormObj(document.drawer);
</script>
