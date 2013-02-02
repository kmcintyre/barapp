<script>
	var formObj;
	function setFormObj(vObj) {
		formObj = vObj;
	}
	function submitForm(action) {
		if ( formObj == null ) {
			document.location.href='<%= request.getContextPath() %>/secure/index.jsp?action=' + action;
		} else {
			var selectedValues = new Array();
			if ( typeof( checkarray ) != "undefined" ) {
				for ( i = 0 ; i < checkarray.length; i++ ) {					
					for ( j = 0; j < formObj.elements.length; j++ ) {
						if ( formObj.elements[j].name == checkarray[i] ) {
							if ( formObj.elements[j].value == '' ) {
								alert( 'Please Select ' + checkarray[i].substring(0,checkarray[i].indexOf('_')));
								formObj.elements[j].focus();
								return;
							} else {
								for ( k = 0; k < selectedValues.length; k++ ) {
									if ( selectedValues[k] == formObj.elements[j].value ) {
										alert( checkarray[i].substring(0,checkarray[i].indexOf('_')) + ' Is A Duplicate');
										formObj.elements[j].focus();
										return;
									}
								}
								selectedValues[selectedValues.length] = formObj.elements[j].value;
							}
						}
					}					
				}
				formObj.action.value = action;
				formObj.submit();
			} else {
				formObj.action.value = action;
				formObj.submit();
			}
		}
	}
</script>

<div class="fullWidth"><table cellpadding="0" cellspacing="0">
	<tr>
		<% if ( request.getParameter("tab_action").equals("drawer") ) { %>
			<td class="tabDrawerOn"><a href="javascript:submitForm('drawer')"><img 
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } else { %>
			<td class="tabDrawerOff"><a href="javascript:submitForm('drawer')"><img 
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } %>
		<% if ( request.getParameter("tab_action").equals("cashbox") ) { %>
			<td class="tabBlackboxOn"><a href="javascript:submitForm('cashbox')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } else { %>
			<td class="tabBlackboxOff"><a href="javascript:submitForm('cashbox')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } %>			
		<% if ( request.getParameter("tab_action").equals("drop") ) { %>
			<td class="tabDropOn"><a href="javascript:submitForm('drop')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } else { %>
			<td class="tabDropOff"><a href="javascript:submitForm('drop')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } %>
		<% if ( request.getParameter("tab_action").equals("payouts") ) { %>
			<td class="tabPayoutOn"><a href="javascript:submitForm('payouts')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } else { %>
			<td class="tabPayoutOff"><a href="javascript:submitForm('payouts')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } %>
		<% if ( request.getParameter("tab_action").equals("shortages") ) { %>
			<td class="tabShortageOn"><a href="javascript:submitForm('shortages')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } else { %>
			<td class="tabShortageOff"><a href="javascript:submitForm('shortages')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } %>					
		<% if ( request.getParameter("tab_action").equals("checkout") ) { %>
			<td class="tabCheckoutOn"><a href="javascript:submitForm('checkout')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } else { %>
			<td class="tabCheckoutOff"><a href="javascript:submitForm('checkout')"><img  
			src="<%= request.getContextPath() %>/img/spacer.gif" 
			width="117" height="28"/></a></td>
		<% } %>					
	</tr>
</table></div>