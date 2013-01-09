<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="com.nwice.barapp.servlet.ShiftServlet" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.nwice.barapp.util.*" %>
<%@ page import="org.apache.log4j.Logger" %>

<%@ include file="calendar.jsp" %>

<jsp:useBean id="cashoutManager" scope="application" class="com.nwice.barapp.manager.CashoutManager"/>

	<% Cashout[] cashouts = cashoutManager.getCashoutsByDates(from_date,to_date); %>

	<%
	String st = "Shift";
	if ( request.getParameter("view") != null && request.getParameter("view").equals("daily") ) {
		request.getSession().setAttribute("daily","true");
	} else if ( request.getParameter("view") != null && request.getParameter("view").equals("shift") ) {
		request.getSession().removeAttribute("daily");
	}
	%>

	<% Logger logger = Logger.getLogger("cashouts.jsp"); %>
	
	<% logger.info("Cashout Length:" + cashouts.length ); %>
	
	<% if ( request.getSession().getAttribute("daily") != null ) { %>
		<%@ include file="cashoutsdaily.jsp" %>
	<% } else { %>
		<%@ include file="cashoutsshift.jsp" %>
	<% } %>	