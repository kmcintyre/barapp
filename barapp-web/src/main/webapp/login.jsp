<jsp:include page="/_layout_/header.jsp">

	<jsp:param name="title" value=" Login "/>

	</jsp:include>



	<jsp:include page="/_layout_/body_top.jsp"/>



	<div width="100%">

	<img src="<%= request.getContextPath() %>/img/spacer.gif" width="1" height="71"/>

	</div>



	<jsp:include page="/_layout_/box_top.jsp"/>



	<TABLE cellSpacing=0 cellPadding=0 width=248 bgColor="#f2f2e1" border=0 class="loginBox">

	<TBODY>

		<TR align=middle>

			    <TD width=5>&nbsp;</TD>

			    	    <TD class="largeFont" colSpan="3" height="35">Secure Log On <IMG height="13" 

				    	                  src="<%= request.getContextPath() %>/img/locker.gif" width="11"></TD>

							  	</TR>

									<% if ( request.getAttribute("error") != null ) { %>

												<TR align=middle>

														    <TD width=5>&nbsp;</TD>

														    	    <TD class="largeFont" colSpan="3"">

															    	    	<font color="red"><%= request.getAttribute("error") %></font>

																		    </TD>

																		    		</TR>		

																					<% } %>
																						<% if ( request.getParameter("yes_login") != null ) { %>


																								<form method="POST" name="login" action="<%= request.getContextPath() %>/secure/j_security_check">

																										<TR>

																										    	<TD width=5></TD>

																												    <TD class="largeFont" align="right"><b>User ID</b></TD>

																												    		<TD width=10 rowSpan=3><IMG height=10 

																														                  src="<%= request.getContextPath() %>/img/spacer.gif" 

																																                    width=10>

																																		    		</TD>

																																				        <TD><INPUT class="largeFont" style="WIDTH: 160px" tabIndex="1" 

																																					                  maxLength="32" name="j_username">

																																							  		</TD>

																																										</TR>

																																											<TR>

																																													<TD width=5></TD>

																																													    	<TD class="largeFont" align="right"><b>Password</b></TD>

																																																<TD><INPUT class="largeFont" style="WIDTH: 160px" tabIndex="2" 

																																																                  type="password" name="j_password">

																																																		  		</TD>

																																																					</TR>

																																																						<TR>

																																																								<TD width=5></TD>

																																																								        <TD align=middle colSpan=3 height=45>

																																																									        	<DIV class="spacerH5"></DIV>

																																																											        		<input  

																																																														                  tabIndex=3

																																																																  				  type="image"

																																																																				                    src="<%= request.getContextPath() %>/img/logon.gif" 

																																																																						                      border="0">

																																																																								      	        <DIV class="spacerH10"></DIV>

																																																																												</TD>

																																																																													</TR>

																																																																														
																																																																														
																																																																														<script>

																																																																														window.onload=foc;

																								function foc() {

																										document.login.j_username.focus();

																								}

																								</script>

																									<% } else { %>
	<tr><td colspan="2" align="center">
																											<a href="<%= request.getContextPath() %>/secure/index.jsp?yes_login=yes"><img src="<%= request.getContextPath() %>/img/logon.gif" vspace="5" border="0"/></a>

	</td></tr>
																												<% } %>
</TBODY>
</TABLE>

																												<jsp:include page="/_layout_/box_bottom.jsp"/>



																												<jsp:include page="/_layout_/body_bottom.jsp"/>


