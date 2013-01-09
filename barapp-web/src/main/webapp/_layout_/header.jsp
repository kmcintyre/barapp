<html>
<head>
<title><%= request.getParameter("title") %></title>
</head>

<style type="text/css">

.largeFont {
	font-size: 14pt
}

.smallFont {
	font-size: 10pt
}

img {border:0;}

.loginBox {
	border: 1px solid #5280b1;
}

.bodyTextBold {
	FONT-WEIGHT: bold; FONT-SIZE: 75%
}
.inputTextBox {
	FONT-SIZE: 75%; MARGIN: 2px 0px
}
.spacerH5 {
	FONT-SIZE: 5px; HEIGHT: 5px
}
.spacerH10 {
	FONT-SIZE: 10px; HEIGHT: 10px
}

.globalNav {color:#666666; font-size:70%; text-align: right;}
a.globalNavLinks:link,a.globalNavLinks:visited  {color:#666666; text-decoration:none;}
a.globalNavLinks:active,a.globalNavLinks:hover {color:#5280b1; text-decoration:underline;}

.spacerW25 {width: 25px;}
.spacerH30 {height: 30px; font-size: 30px;}
.fullWidth {width: 779px;}

.tabDrawerOff {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_drawer_off.gif); 
}
.tabDrawerOn {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_drawer_on.gif); 
}
.tabBlackboxOff {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_blackbox_off.gif); 
}
.tabBlackboxOn {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_blackbox_on.gif); 
}
.tabDropOff {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_drop_off.gif); 
}
.tabDropOn {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_drop_on.gif); 
}
.tabPayoutOff {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_payouts_off.gif); 
}
.tabPayoutOn {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_payouts_on.gif); 
}
.tabShortageOff {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_shortages_off.gif); 
}
.tabShortageOn {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_shortages_on.gif);
}
.tabCheckoutOff {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_checkout_off.gif);
}
.tabCheckoutOn {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/tabs_checkout_on.gif);
}

.headerBarBack {
	BACKGROUND-IMAGE: url(<%= request.getContextPath() %>/img/default_bg.gif); BACKGROUND-REPEAT: no-repeat
}


.box {
	border: 1px solid black;
	width:200px;
	margin:2px;
}
.box2 {
	border: 1px solid black;
	width:300px;
	margin:2px;
}
</style>

<jsp:include page="/script/money.jsp"/>

