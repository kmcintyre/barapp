<%@ page import="com.nwice.barapp.util.BarappUtil" %>
<%@ page import="com.nwice.barapp.servlet.*" %>
<%@ page import="org.apache.log4j.Logger" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<% Logger logger = Logger.getLogger("drawer.jsp"); %>

<!---
<div style="margin:10;float:left"><a class="smallFont" href="index.jsp?action=cashbox&previous=yes">import prev</a></div>
--->

<%
if ( request.getParameter("previous") != null ) { 
	try {
		CashboxServlet.importPrevious(cashout);
		} catch (Exception e) {
			logger.info( e.toString() + "-No Previous?");
		}
}
%>

<table border="0" cellpadding="4" cellspacing="0">
	<form name="cashbox" action="cashbox.do">
	<input type="hidden" name="action"/>
	<tr>
		<td>
		</td>
		<td class="largeFont" align="center">
			<b>Start</b>
		</td>
		<td class="largeFont" align="center">
			<b>End</b>
		</td>		
	</tr>
	<tr>
		<td class="largeFont">
			100's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="hundred_start"/>
				<jsp:param name="value" value="<%= cashout.getStartCashboxObject().getHundred() %>"/>
				<jsp:param name="tabIndex" value="1"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="hundred"/>
				<jsp:param name="value" value="<%= cashout.getCashboxObject().getHundred() %>"/>
				<jsp:param name="tabIndex" value="7"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			50's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="fifty_start"/>
				<jsp:param name="value" value="<%= cashout.getStartCashboxObject().getFifty() %>"/>
				<jsp:param name="tabIndex" value="2"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="fifty"/>
				<jsp:param name="value" value="<%= cashout.getCashboxObject().getFifty() %>"/>
				<jsp:param name="tabIndex" value="8"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			20's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="twenty_start"/>
				<jsp:param name="value" value="<%= cashout.getStartCashboxObject().getTwenty() %>"/>
				<jsp:param name="tabIndex" value="3"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="twenty"/>
				<jsp:param name="value" value="<%= cashout.getCashboxObject().getTwenty() %>"/>
				<jsp:param name="tabIndex" value="9"/>
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
				<jsp:param name="value" value="<%= cashout.getStartCashboxObject().getTen() %>"/>
				<jsp:param name="tabIndex" value="4"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="ten"/>
				<jsp:param name="value" value="<%= cashout.getCashboxObject().getTen() %>"/>
				<jsp:param name="tabIndex" value="10"/>
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
				<jsp:param name="value" value="<%= cashout.getStartCashboxObject().getFive() %>"/>
				<jsp:param name="tabIndex" value="5"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="five"/>
				<jsp:param name="value" value="<%= cashout.getCashboxObject().getFive() %>"/>
				<jsp:param name="tabIndex" value="11"/>
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
				<jsp:param name="value" value="<%= cashout.getStartCashboxObject().getSingle() %>"/>
				<jsp:param name="tabIndex" value="6"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="single"/>
				<jsp:param name="value" value="<%= cashout.getCashboxObject().getSingle() %>"/>
				<jsp:param name="tabIndex" value="12"/>
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
				<jsp:param name="value" value="<%= BarappUtil.doubleToString(cashout.getStartCashboxObject().getTotal()) %>"/>
			</jsp:include>					
		</td>
		<td>
			<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/autototalform.jsp">
				<jsp:param name="denom" value="total"/>
				<jsp:param name="value" value="<%= BarappUtil.doubleToString(cashout.getCashboxObject().getTotal()) %>"/>
			</jsp:include>
		</td>		
	</tr>
	<tr>
		<td colspan="3" align="right">
			<input type="button" class="largeFont" value=" Next " onClick="submitForm('drop')">
		</td>
	</tr>		
	</form>	
</table>
<script>
	setFormObj(document.cashbox);
</script>