<% if ( request.getParameter("expected") == null ) { %>
	<tr><td align="center" colspan="2">
			<input name="submitButton" type="button" value="<%= request.getParameter("name") %>" onClick="this.form.submit();"/>
	</td></tr>
<% } else { %>
	<script>
		function checkTotal(obj, value) {
			if ( parseInt(obj.total.value) == parseInt(value) ) {
				obj.submit();
			} else {
				ret=confirm('The total is suppose to be ' + value + '. Submit anyway?');
				if ( ret == true ) {
					obj.submit();
				}
			}
		}
	</script>
	<tr><td align="center" colspan="2">
			<input name="submitButton" type="button" value="<%= request.getParameter("name") %>" onClick="checkTotal(this.form,'<%= request.getParameter("expected") %>')"/>
	</td></tr>	
<% } %>
	