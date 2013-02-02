<html>
<head>
<title><%= request.getParameter("title") %></title>
</head>

<style type="text/css">

img {
	border:0;
}

.tbl4p td, th {
	padding: 4px;
}

.largeFont {
	font-size: 14pt
}

.smallFont {
	font-size: 10pt
}

.loginTable {
	width: 248px;
	bgColor: #f2f2e1;
	border: 1px solid #5280b1;
}

.globalNav {
	color:#666666;
	font-size: .8em;
}

.dateStyle {
	display:inline;
	float:right;
	font-size:1.1em;
	padding-right:15px;
	padding-top:3px;
	color:#ffffff;
}

.userStyle {
	display:inline;
	float:left;
	font-size:1.1em;
	padding-left:15px;
	padding-top:3px;
	color:#ffffff;
}

.spacerH5 { height: 5px }
.spacerH10 { height: 10px }
.spacerW25 {width: 25px;}
.spacerH30 {height: 30px;}
.fullWidth {width: 779px;}

.tabDrawerOff {
	background-image: url(<%= request.getContextPath() %>/img/tabs_drawer_off.gif); 
}
.tabDrawerOn {
	background-image: url(<%= request.getContextPath() %>/img/tabs_drawer_on.gif); 
}
.tabBlackboxOff {
	background-image: url(<%= request.getContextPath() %>/img/tabs_blackbox_off.gif); 
}
.tabBlackboxOn {
	background-image: url(<%= request.getContextPath() %>/img/tabs_blackbox_on.gif); 
}
.tabDropOff {
	background-image: url(<%= request.getContextPath() %>/img/tabs_drop_off.gif); 
}
.tabDropOn {
	background-image: url(<%= request.getContextPath() %>/img/tabs_drop_on.gif); 
}
.tabPayoutOff {
	background-image: url(<%= request.getContextPath() %>/img/tabs_payouts_off.gif); 
}
.tabPayoutOn {
	background-image: url(<%= request.getContextPath() %>/img/tabs_payouts_on.gif); 
}
.tabShortageOff {
	background-image: url(<%= request.getContextPath() %>/img/tabs_shortages_off.gif); 
}
.tabShortageOn {
	background-image: url(<%= request.getContextPath() %>/img/tabs_shortages_on.gif);
}
.tabCheckoutOff {
	background-image: url(<%= request.getContextPath() %>/img/tabs_checkout_off.gif);
}
.tabCheckoutOn {
	background-image: url(<%= request.getContextPath() %>/img/tabs_checkout_on.gif);
}

.headerBarBack {
	height:25px;
	background-image: url(<%= request.getContextPath() %>/img/default_bg.gif); background-repeat: no-repeat
}
</style>

<jsp:include page="/script/money.jsp"/>

