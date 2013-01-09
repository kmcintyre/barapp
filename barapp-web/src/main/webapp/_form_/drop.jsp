<%@ page import="com.nwice.barapp.util.BarappUtil" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<table border="0" cellpadding="4" cellspacing="0">
	<form name="drop" action="drop.do">
	<input type="hidden" name="action"/>
	<tr>
		<td class="largeFont">
			100's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="hundred"/>
				<jsp:param name="value" value="<%= cashout.getDropObject().getHundred() %>"/>
				<jsp:param name="tabIndex" value="1"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			50's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="fifty"/>
				<jsp:param name="value" value="<%= cashout.getDropObject().getFifty() %>"/>
				<jsp:param name="tabIndex" value="2"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			20's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="twenty"/>
				<jsp:param name="value" value="<%= cashout.getDropObject().getTwenty() %>"/>
				<jsp:param name="tabIndex" value="3"/>
			</jsp:include>
		</td>		
	</tr>
	<tr>
		<td class="largeFont">
			10's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="ten"/>
				<jsp:param name="value" value="<%= cashout.getDropObject().getTen() %>"/>
				<jsp:param name="tabIndex" value="4"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			5's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="five"/>
				<jsp:param name="value" value="<%= cashout.getDropObject().getFive() %>"/>
				<jsp:param name="tabIndex" value="5"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			1's
		</td>
		<td>
			<span style="color:#ffffff;font-size:11px">&#36;</span><jsp:include page="_static_/moneyform.jsp">
				<jsp:param name="denom" value="single"/>
				<jsp:param name="value" value="<%= cashout.getDropObject().getSingle() %>"/>
				<jsp:param name="tabIndex" value="6"/>
			</jsp:include>
		</td>		
	</tr>	
	<tr>
		<td class="largeFont">
			Total
		</td>
		<td>
			<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/autototalform.jsp">
				<jsp:param name="denom" value="total"/>
				<jsp:param name="value" value="<%= BarappUtil.doubleToString(cashout.getDropObject().getTotal()) %>"/>
			</jsp:include>
		</td>		
	</tr>
	<tr>
		<td colspan="3" align="right">
			<input type="button" class="largeFont" value=" Next " onClick="submitForm('payouts')">
		</td>
	</tr>	
	</form>	
</table>
<script>
	setFormObj(document.drop);
</script>