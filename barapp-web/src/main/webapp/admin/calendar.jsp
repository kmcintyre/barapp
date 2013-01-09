<script src="<%= request.getContextPath() %>/script/CalendarPopup.js">
</script>
<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();
</SCRIPT>

	<%
	long MILLIS_PER_DAY = 1000 * 60 * 60 * 24;
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	
	Date now = Calendar.getInstance().getTime();
	
	Date from_date = new Date( now.getTime() - MILLIS_PER_DAY * 7 );
	
	if ( request.getParameter("from_date") != null ) {
		from_date = dateFormat.parse( request.getParameter("from_date") );
		request.getSession().setAttribute("from_date", request.getParameter("from_date") );
	} else if ( request.getSession().getAttribute("from_date") != null ) {
		from_date = dateFormat.parse( request.getSession().getAttribute("from_date").toString() );
	}
	
	
	Date  to_date = now;
	if ( request.getParameter("to_date") != null ) {
		to_date = dateFormat.parse( request.getParameter("to_date") );
		request.getSession().setAttribute("to_date", request.getParameter("to_date") );
	} else if ( request.getSession().getAttribute("to_date") != null ) {
		to_date = dateFormat.parse( request.getSession().getAttribute("to_date").toString() );
	}
	%>

	<table border="0" cellpadding="2" cellspacintg="0">
		<form name="dateSelect">
		<input type="hidden" name="action" value="<%= request.getParameter("action") %>">
		<tr> 
			<td>
				<b>From</b>:
			</td>
			<td><input size="10" type="text" name="from_date" id="from_date" value="<%= dateFormat.format(from_date) %>">
				<A HREF="#"
			 	  onClick="cal.select(document.dateSelect.from_date,'anchor1','MM/dd/yyyy'); return false;"
			   	NAME="anchor1" ID="anchor1"><img src="<%= request.getContextPath() %>/img/icon-date.gif" border="0"></A>
			</td>
			<td>
				<b>To</b>:
			</td>		
			<td>
				<input size="10" type="text" name="to_date" value="<%= dateFormat.format(to_date) %>">
				<A HREF="#"
			 	  onClick="cal.select(document.dateSelect.to_date,'anchor2','MM/dd/yyyy'); return false;"
			   	NAME="anchor2" ID="anchor2"><img src="<%= request.getContextPath() %>/img/icon-date.gif" border="0"></A>
			</td>
			<td>
				<input type="submit" value="Go">
			</td>
		</tr>
		</form>
	</table>
