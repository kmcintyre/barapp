<input type="text" name="<%= request.getParameter("denom") %>" size="4"
	onkeydown="return allNumbers(event,this.form)"
	onBlur="javascript:money(this.form)"
	onFocus="javascript:this.select()"
	value="<%= request.getParameter("value") %>"
	tabIndex="<%= request.getParameter("tabIndex") %>"
	class="largeFont"
	>
