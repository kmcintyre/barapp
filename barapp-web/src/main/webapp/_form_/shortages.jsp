<%@ page import="com.nwice.barapp.util.BarappUtil" %>
<%@ page import="com.nwice.barapp.model.*" %>
<%@ page import="java.util.*" %>

<jsp:useBean id="cashout" scope="session" class="com.nwice.barapp.model.Cashout"/>

<table class="tbl4p">
	<form name="shortage" action="<%= request.getContextPath() %>/secure/shortage.do">
	<input type="hidden" name="action"/>
	<tr>
		<td align="center"  class="largeFont">
			<b>Shortages</b>	
		</td>
		<td align="center"  class="largeFont">
			<b>Overrings</b>
		</td>
	</tr>

	<tr>
		<td valign="top">
			<table>
				<tr>
					<td class="largeFont">
					<b>Price</b>
					</td>
					<td class="largeFont">
					<b>Item</b>
					</td>					
				<%
				Shortage[] shortages = (Shortage[])cashout.getShortages().toArray( new Shortage[cashout.getShortages().size()]);
				%>
				<% for ( int i = 0; i < 5; i++ ) { 
					String denom = "total_shortage_" + i;
					String name = "description_shortage_" + i;
					String denom_value = "";
					String name_value = "";
					try { 
						denom_value = BarappUtil.doubleToString(shortages[i].getTotal());
						name_value = shortages[i].getDescription();
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					%>
					<tr>
						<td>
							<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/totalform.jsp">
								<jsp:param name="denom" value="<%= denom %>"/>
								<jsp:param name="value" value="<%= denom_value %>"/>
							</jsp:include>
						</td>
						<td>
							<jsp:include page="_static_/descriptionform.jsp">
								<jsp:param name="description_name" value="<%= name %>"/>
								<jsp:param name="description_value" value="<%= name_value %>"/>
							</jsp:include>
						</td>
					</tr>
				<% } %>
			</table>
		</td>
		<td valign="top">
			<table>
				<tr>
					<td class="largeFont">
					Price
					</td>
					<td class="largeFont">
					Item
					</td>					
				<%
				Overring[] overrings = (Overring[])cashout.getOverrings().toArray( new Overring[cashout.getOverrings().size()]);
				%>
				<% for ( int i = 0; i < 5; i++ ) { 
					String denom = "total_overring_" + i;
					String name = "description_overring_" + i;
					String denom_value = "";
					String name_value = "";
					try { 
						denom_value = BarappUtil.doubleToString(overrings[i].getTotal());
						name_value = overrings[i].getDescription();
					} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					} catch (Exception e) {
						System.out.println(e.toString());
					}
					%>
					<tr>
						<td>
							<span style="color:#5280b1;font-size:11px">&#36;</span><jsp:include page="_static_/totalform.jsp">
								<jsp:param name="denom" value="<%= denom %>"/>
								<jsp:param name="value" value="<%= denom_value %>"/>
							</jsp:include>
						</td>
						<td>
							<jsp:include page="_static_/descriptionform.jsp">
								<jsp:param name="description_name" value="<%= name %>"/>
								<jsp:param name="description_value" value="<%= name_value %>"/>
							</jsp:include>
						</td>
					</tr>
				<% } %>
			</table>		
		</td>		
	</tr>
	<tr>
		<td align="right" colspan="2">
			<input type="button" class="largeFont" value=" Next " onClick="submitForm('checkout')">
		</td>
	</tr>
	</form>
</table>
<script>
	setFormObj(document.shortage);
</script>



					