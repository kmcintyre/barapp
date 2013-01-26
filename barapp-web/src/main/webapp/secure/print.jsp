<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.util.*" %>
<%@ page import="com.nwice.barapp.manager.*" %>
<%@ page import="org.apache.log4j.Logger" %>

<% Logger logger = Logger.getLogger("print.jsp"); %>	

<% SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMMM d"); %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>
<jsp:useBean id="userManager" scope="application" class="com.nwice.barapp.manager.UserManager"/>

<% logger.debug("cashoutId-" + cashout.getCashoutId()); %>

<%
Double u = cashout.getDrop().getTotal();
Double v = new Double(0);

try {

Payout[] pay_outs = (Payout[])cashout.getPayouts().toArray(new Payout[cashout.getPayouts().size()]);
for ( int i = 0; i < pay_outs.length; i++ ) {
	v = new Double(v.doubleValue() + pay_outs[i].getTotal().doubleValue());
}

} catch (Exception e) { 
	%><%= e %><% 
} 

try {
	ShiftWorker[] wor_kers = (ShiftWorker[])cashout.getShift().getShiftWorkers().toArray(new ShiftWorker[cashout.getShift().getShiftWorkers().size()]);
	
	for ( int i = 0; i < wor_kers.length; i++ ) {
		v = new Double(v.doubleValue() + wor_kers[i].getPayout().doubleValue());	
	} 
} catch (Exception e) { 
	%><%= e %><% 
} 	
%>

<table cellpadding="5" cellspacing="0">
<tr id="hideMeOnPrint">
	<form>
	<td colspan="2">
		<input type="button" value="Print" onClick="document.getElementById('hideMeOnPrint').style.display='none';window.print();document.location.href='<%= request.getContextPath() %>/secure/done.jsp'"></td>
	</td>
	</form>
</tr>
<tr>
	<td>
		<b><%= sdf.format(cashout.getShift().getShiftDate()) %> 
		<% if ( cashout.getShift().getAmpm().equals("AM") ) { %>
			Day
		<% } else { %>
			Night
		<% } %>
		</b>
	</td>
	<td align="right">
		<nobr>
		Drop: <b><%= BarappUtil.doubleToString(u) %></b>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Payouts: <b><%= BarappUtil.doubleToString(v) %></b>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		Total: <b><%= BarappUtil.doubleToString( new Double(u.doubleValue() + v.doubleValue()) ) %></b>
		</nobr>
	</td>
</tr>
<tr>
	<td colspan="2">
		<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="20"/>
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<table cellpadding="5" cellspacing="0">
			<tr>
				<td width="215"></td><td width="200"></td><td  width="100" align="center">Payout</td><td  width="100" align="center">Tips</td>				
			</tr>	
			<%@ include file="print_workers.jsp" %>
			<%@ include file="print_payouts.jsp" %>
		</table>				
	</td>
</tr>
<tr>
	<td colspan="2">
		<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="20"/>
	</td>
</tr>
<tr>
	<td valign="top">
		<%@ include file="print_drop.jsp" %>
		<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="100" height="40"/>
		<%@ include file="print_drawer.jsp" %>		
		<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="100" height="40"/>		
		<%@ include file="print_cashbox.jsp" %>
	</td>
	<td valign="middle">
		<% 
		ShortageObject[] test_shortages = (ShortageObject[])cashout.getShortages().toArray( new ShortageObject[cashout.getShortages().size()]); 
		OverringObject[] test_overrings = (OverringObject[])cashout.getOverrings().toArray( new OverringObject[cashout.getOverrings().size()]); 
		%>
		<% if ( test_shortages.length > 0 ) { %>
			<%@ include file="print_shortages.jsp" %>
		<% } %>
		<% if ( test_shortages.length > 0  && test_overrings.length > 0) { %>
			<img  
				src="<%= request.getContextPath() %>/img/spacer.gif" 
				width="100" height="40"/>
		<% } %>
		<% if ( test_overrings.length > 0 ) { %>
				<%@ include file="print_overrings.jsp" %>
		<% } %>
	</td>
</tr>
</table>