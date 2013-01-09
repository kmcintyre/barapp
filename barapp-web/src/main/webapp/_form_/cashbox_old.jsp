<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<table border="0" cellpadding="2" cellspacing="0" class="box">
<% 
String edit = "false";
if ( request.getParameter("action") != null && request.getParameter("action").equals("cashbox") ) { 
	edit = "true";
	%>
	<form name="cashbox.do" action="cashbox.do">
<% } %>
	<tr>
		<td colspan="2">
			Cashbox
			<% if ( edit.equals("false")  ) { %>				
				<a href="index.jsp?action=cashbox">Edit</a>
			<% } %>
		</td>
	</tr>
	<jsp:include page="_static_/extendedmoney.jsp">
		<jsp:param name="hundred" value="<%= cashout.getCashboxObject().getHundred() %>"/>			
		<jsp:param name="fifty" value="<%= cashout.getCashboxObject().getFifty() %>"/>
		<jsp:param name="edit" value="<%= edit %>"/>
	</jsp:include>	
	<jsp:include page="_static_/defaultmoney.jsp">
		<jsp:param name="twenty" value="<%= cashout.getCashboxObject().getTwenty() %>"/>
		<jsp:param name="ten" value="<%= cashout.getCashboxObject().getTen() %>"/>
		<jsp:param name="five" value="<%= cashout.getCashboxObject().getFive() %>"/>				
		<jsp:param name="single" value="<%= cashout.getCashboxObject().getSingle() %>"/>				
		<jsp:param name="edit" value="<%= edit %>"/>			
	</jsp:include>
	<jsp:include page="_static_/autototal.jsp">
		<jsp:param name="total" value="<%= cashout.getCashboxObject().getTotal() %>"/>
		<jsp:param name="edit" value="<%= edit %>"/>
	</jsp:include>
	<% if ( request.getParameter("action") != null && request.getParameter("action").equals("cashbox") ) { %>	
		<jsp:include page="_static_/submit.jsp">
			<jsp:param name="name" value="Update Cashbox"/>
			<jsp:param name="expected" value="1000"/>		
		</jsp:include>
	</form>	
	<% } %>	
</table>

