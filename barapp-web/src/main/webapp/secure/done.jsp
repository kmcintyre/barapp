<% 	
	request.getSession().removeAttribute("cashout");

	if ( request.isUserInRole("admin") ) {
		request.getSession().removeAttribute("cashoutId");
		response.sendRedirect( request.getContextPath() + "/admin/index.jsp?action=cashouts");
	} else {
		response.sendRedirect( request.getContextPath() + "/secure/logout.jsp");
	}
%>