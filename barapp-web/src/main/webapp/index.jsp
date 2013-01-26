<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/_layout_/header.jsp">
	<jsp:param name="title" value=" Login "/>
</jsp:include>

<jsp:include page="/_layout_/body_top.jsp"/>

<div width="100%">
	<img src="<%= request.getContextPath() %>/img/spacer.gif" width="1" height="71"/>
</div>

<jsp:include page="/_layout_/box_top.jsp"/>

	<table cellspacing="0" cellpadding="0" width="248px" bgColor="#f2f2e1" border="0" class="loginBox">

	<tbody>
		<tr align="middle">
			<td width="5">&nbsp;</td>
			<td class="largeFont" colSpan="3" height="35">Secure Log On 
				<img height="13" src="<%= request.getContextPath() %>/img/locker.gif" width="11">
			</td>

		</tr>

		<tr>
			<td colspan="4" align="center">
				<a href="<%= request.getContextPath() %>/secure/index.jsp"><img src="<%= request.getContextPath() %>/img/logon.gif" vspace="5" border="0"/></a>
			</td>
		</tr>

	</tbody>
</table>

<jsp:include page="/_layout_/box_bottom.jsp"/>

<jsp:include page="/_layout_/body_bottom.jsp"/>