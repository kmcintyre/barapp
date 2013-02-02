<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.nwice.barapp.manager.*" %>
<%@ page import="org.apache.log4j.Logger" %>
	

<% Logger logger = Logger.getLogger("cashout.jsp"); %>	

	<% try { %>
		<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>
			
		<% if ( request.getParameter("cashout_action").equals("drawer") ) { %>
			<jsp:include page="/_form_/drawer.jsp"/>				
		<% } else if ( request.getParameter("cashout_action").equals("cashbox") ) { %>		
			<jsp:include page="/_form_/cashbox.jsp"/>
		<% } else if ( request.getParameter("cashout_action").equals("drop") ) { %>		
			<jsp:include page="/_form_/drop.jsp"/>
		<% } else if ( request.getParameter("cashout_action").equals("payouts") ) { %>		
			<jsp:include page="/_form_/payouts.jsp"/>
		<% } else if ( request.getParameter("cashout_action").equals("shortages") ) { %>		
			<jsp:include page="/_form_/shortages.jsp"/>
		<% } else if ( request.getParameter("cashout_action").equals("checkout") ) { %>		
			<% if ( cashout.getCashoutId() == null ) { %>
				<jsp:include page="/_form_/checkout.jsp"/>			
			<% } else { %>
				<button onClick="document.location.href = '<%= request.getContextPath() %>/secure/print.jsp'">Re-Print</button>				
			<% } %>
		<% } %>
	
	<% } catch (Exception e) { %>
		Major Fuck UP. <%= e %>
	<% } %>