<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>

<%
DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

Date now = Calendar.getInstance().getTime();
%>

<script src="<%= request.getContextPath() %>/script/CalendarPopup.js">
</script>
<SCRIPT LANGUAGE="JavaScript">
	var cal = new CalendarPopup();
</SCRIPT>

<table border="0" cellpadding="2" cellspacintg="0">
<form name="dateSelect" action="<%= request.getContextPath() %>/secure/cashout.do">
<input type="hidden" name="shiftoveride" value="true">
<input type="hidden" name="start" value="yes">
<tr><td>
<b>Shift Date</b>:
</td><td><input size="10" type="text" name="create_date" id="create_date" value="<%= dateFormat.format(now) %>">
<A HREF="#"
	onClick="cal.select(document.dateSelect.create_date,'anchor1','MM/dd/yyyy'); return false;"
	NAME="anchor1" ID="anchor1"><img src="<%= request.getContextPath() %>/img/icon-date.gif" border="0"></A>
</td></tr>
<tr><td>
<b>AM/PM</b>:
</td><td>
<select name="ampm">
	<option>AM
	<option>PM	
</select>
</td></tr>
<tr><td colspan="2" align="right">
<input value="Go" type="submit">
</td></tr>
</form>
</table>


