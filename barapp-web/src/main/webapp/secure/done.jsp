<% 	
	request.getSession().removeAttribute("cashout");

	if ( request.isUserInRole("ROLE_ADMIN") ) {
		response.sendRedirect( request.getContextPath() + "/admin/index.jsp?action=cashouts");
	} else {
		response.sendRedirect( request.getContextPath() + "/secure/logout.jsp");
	}
%>