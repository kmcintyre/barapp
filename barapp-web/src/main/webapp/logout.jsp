<%@ page import="com.nwice.barapp.*" %><% 	

	if (request.getSession()!= null) {
		request.getSession().invalidate();
	}
	response.sendRedirect(request.getContextPath() + "/index.jsp");
%>