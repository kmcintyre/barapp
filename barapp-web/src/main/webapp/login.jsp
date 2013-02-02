<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/_layout_/header.jsp">
	<jsp:param name="title" value=" Login "/>
</jsp:include>

<jsp:include page="/_layout_/body_top.jsp"/>

<div width="100%">
	<img src="<%= request.getContextPath() %>/img/spacer.gif" width="1" height="71"/>
</div>

<jsp:include page="/_layout_/box_top.jsp"/>

	<table class="loginTable">

	<tbody>
		<tr align="middle">
			<td width="5">&nbsp;</td>
			<td class="largeFont" colSpan="3" height="35">Secure Log On 
				<img height="13" src="<%= request.getContextPath() %>/img/locker.gif" width="11">
			</td>

		</tr>

		<% if ( request.getParameter("error") != null ) { %>
			<tr align="middle">
				<td width="5">&nbsp;</td>
				<td class="largeFont" colSpan="3"">
					<font color="red"><%= request.getParameter("error") %></font>
				</td>
			</tr>		
		<% } %>
		
			<form method="POST" name="login" action="<c:url value='j_spring_security_check' />">
			<tr>
				<td width="5">&nbsp;</td>
				<td class="largeFont" align="right"><b>User ID</b></td>
				<td width="10" rowspan="3"><img height="10" width="10"
					src="<%= request.getContextPath() %>/img/spacer.gif">

				</td>
				<td><input class="largeFont" style="WIDTH: 160px" tabIndex="1" 
						maxLength="32" name="j_username">
				</td>
			</tr>
			<tr>
				<td width="5">&nbsp;</td>
				<td class="largeFont" align="right"><b>Password</b></td>
				<td><input class="largeFont" style="WIDTH: 160px" tabIndex="2" 
					type="password" name="j_password">
				</td>
			</tr>
			<tr>
				<td width="5">&nbsp;</td>
				<td align="middle" colspan="3" height="45">
					<div class="spacerH5"></div>
						<input type="image" tabIndex="3"
							src="<%= request.getContextPath() %>/img/logon.gif" 
							border="0"
						>
					<DIV class="spacerH10"></DIV>
				</td>
			</tr>
			</form>
			
			<script>
			window.onload=foc;
			function foc() {
					document.login.j_username.focus();
			}
			</script>
</tbody>
</table>

<jsp:include page="/_layout_/box_bottom.jsp"/>

<jsp:include page="/_layout_/body_bottom.jsp"/>