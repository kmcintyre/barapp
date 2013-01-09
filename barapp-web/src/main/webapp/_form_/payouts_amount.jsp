<%@ page import="com.nwice.barapp.util.BarappUtil" %>
<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.servlet.PayoutServlet" %>
<%@ page import="com.nwice.barapp.manager.UserManager" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.log4j.Logger" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<jsp:useBean id="userManager" scope="application" class="com.nwice.barapp.manager.UserManager"/>

<% Logger logger = Logger.getLogger("payouts_amount.jsp"); %>

<% PayoutObject[] payouts = (PayoutObject[])cashout.getPayouts().toArray(new PayoutObject[cashout.getPayouts().size()]); %>

<% ShiftWorkerObject[] workers = (ShiftWorkerObject[])cashout.getShift().getShiftWorkers().toArray(new ShiftWorkerObject[cashout.getShift().getShiftWorkers().size()]); %>

<jsp:useBean id="whoami" scope="session" class="com.nwice.barapp.user.JspUserBean"/>

<% logger.info(whoami); %>

<% BarappUser[] bartenders = userManager.getActiveBartenders(); %>

<% logger.info("Active Bartenders" + bartenders.length); %>

<% BarappUser[] barbacks = userManager.getActiveBarbacks(); %>

<script type="text/javascript">
var checkarray = new Array();
</script>

<table border="0" cellspacing="0" cellpadding="4">
	<form name="payout" action="payout.do">
	<input type="hidden" name="amount" value="yes"/>
	<input type="hidden" name="action"/>
	
	<tr>
		<td class="largeFont"style="padding: 2 15 2 15">
		<b>Pay To</b>
		</td>
		<td class="largeFont"style="padding: 2 15 2 15">
		<b>Description</b>
		</td>
		<td class="largeFont"style="padding: 2 15 2 15">
		<b>Payout Amt</b>
		</td>		
		<td class="largeFont"style="padding: 2 15 2 15">
		<b>Tips</b>
		</td>
	</tr>

	<% for ( int i = 0; i < workers.length; i++ ) { %>
		<tr>
			<td class="largeFont"style="padding: 2 15 2 15">
				<%= workers[i].getDescription() %>
			</td>
			<td class="largeFont"style="padding: 2 15 2 15">
				<% logger.info("Checking Shift-" + workers[i].getDescription()); %>
				
					<%
					BarappUser[] searchThese = bartenders;
					if ( workers[i].getDescription().startsWith("Barback") ) {
						searchThese = barbacks;
					}
					%>
					<% if ( searchThese.length > 1 ) { %>
						<script>
							checkarray[checkarray.length] = '<%= workers[i].getDescription() %>_user';
						</script>
					<% } %>
					<select name="<%= workers[i].getDescription() %>_user">
						<% if ( searchThese.length > 1 ) { %>
							<option value=""> - <%= workers[i].getDescription() %> - 
						<% } %>
						<% for ( int j = 0; j < searchThese.length; j++ ) { %>
							<% boolean selected = false; %>
							<% if ( 
									workers[i].getUser() != null 
									&& 
									searchThese[j].getUserId().intValue() == userManager.getUserById( workers[i].getUser() ).getUserId().intValue()
								) { 
									selected = true;
								}					
							%>				
							<option value="<%= searchThese[j].getUserId() %>" <% if ( selected ) { %>selected<% } %>><%= searchThese[j].getLastname() %>,  <%= searchThese[j].getFirstname() %>
						<% } %>
					</select>					
			</td>
			<td class="largeFont"style="padding: 2 15 2 15">
				<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/totalform.jsp">
					<jsp:param name="denom" value="<%= workers[i].getDescription().concat("_payout") %>"/>
					<jsp:param name="value" value="<%= BarappUtil.doubleToString(workers[i].getPayout()) %>"/>
				</jsp:include>			
			</td>		
			<td class="largeFont"style="padding: 2 15 2 15">
				<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/totalform.jsp">
					<jsp:param name="denom" value="<%= workers[i].getDescription().concat("_tip") %>"/>
					<jsp:param name="value" value="<%= BarappUtil.doubleToString(workers[i].getTips()) %>"/>
				</jsp:include>
			</td>	
		</tr>	
	<% } %>
	
	<% for ( int i = 0; i < payouts.length; i++ ) { 
		String description_value = "";
		if ( payouts[i].getDescription() != null ) {
			description_value = payouts[i].getDescription();
		}
		%>
		<tr>
			<td class="largeFont"style="padding: 2 15 2 15">
				<%= payouts[i].getName() %>
			</td>
			<td class="largeFont"style="padding: 2 15 2 15">	
				<jsp:include page="_static_/descriptionform.jsp">
					<jsp:param name="description_name" value="<%= payouts[i].getName().concat("_description") %>"/>
					<jsp:param name="description_value" value="<%= description_value %>"/>
				</jsp:include>				
			</td>
			<td class="largeFont"style="padding: 2 15 2 15">
				<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/totalform.jsp">
					<jsp:param name="denom" value="<%= payouts[i].getName().concat("_total") %>"/>
					<jsp:param name="value" value="<%= BarappUtil.doubleToString(payouts[i].getTotal()) %>"/>
				</jsp:include>			
			</td>		
			<td class="largeFont"style="padding: 2 15 2 15">
			</td>
		</tr>	
	<% } %>
	<tr>
		<td class="largeFont"colspan="4" align="center">
			<img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="1" height="20"/>
	</td>
	</tr>
				
	<tr>
		<td class="largeFont"colspan="4" align="center">
			<input type="button" onClick="javascript:submitForm('payouts')" value=" Back " class="largeFont">
			<input type="button" onClick="javascript:submitForm('shortages')" value=" Next " class="largeFont">
		</td>
	</tr>	
	</form>
</table>
<script>
	setFormObj(document.payout);
</script>