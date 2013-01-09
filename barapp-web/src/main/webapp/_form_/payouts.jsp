
<% if ( request.getParameter("amount") != null ) { %>
	<jsp:include page="payouts_amount.jsp"/>
<% } else { %>
	<jsp:include page="payouts_pick.jsp"/>
<% } %>