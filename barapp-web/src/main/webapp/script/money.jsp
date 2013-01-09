<script>
function s(num) {
	amount = num * 1; 
	if (isNaN(amount)) { 
		//alert("' " + num + " ' is not a valid entry and that field will " + "not be included in the total money calculation.");
		num = 0;
		return 0;
	} else {
		return amount;
	} 
}

function money(form) {

ttl = 0;
ttl_start = 0;

if ( form.hundred ) ttl = ttl + s(form.hundred.value) * 100 ;
if ( form.hundred_start ) ttl_start = ttl_start + s(form.hundred_start.value) * 100 ;

if ( form.fifty ) ttl = ttl + s(form.fifty.value) * 50 ;
if ( form.fifty_start ) ttl_start = ttl_start + s(form.fifty_start.value) * 50 ;

if ( form.twenty ) ttl = ttl + s(form.twenty.value) * 20;
if ( form.twenty_start ) ttl_start = ttl_start + s(form.twenty_start.value) * 20;

if ( form.ten ) ttl = ttl + s(form.ten.value) * 10;
if ( form.ten_start ) ttl_start = ttl_start + s(form.ten_start.value) * 10;

if ( form.five ) ttl = ttl + s(form.five.value) * 5;
if ( form.five_start ) ttl_start = ttl_start + s(form.five_start.value)  * 5;

if ( form.single ) ttl = ttl + s(form.single.value);
if ( form.single_start ) ttl_start = ttl_start + s(form.single_start.value);

// rounds total to two decimal places

ttl = "" + ((Math.round(ttl * 100)) / 100);
ttl_start = "" + ((Math.round(ttl_start * 100)) / 100);

dec1 = ttl.substring(ttl.length-3, ttl.length-2);
dec1_start = ttl_start.substring(ttl_start.length-3, ttl_start.length-2);

dec2 = ttl.substring(ttl.length-2, ttl.length-1);
dec2_start = ttl_start.substring(ttl_start.length-2, ttl_start.length-1);

form.total.value = ttl;
if ( form.total_start ) {
	form.total_start.value = ttl_start;
}
}

function allNumbers(e,formObj) {

var keynum;
var keychar;
var numcheck;

if(window.event) { // IE
	keynum = e.keyCode;
	obj = e.srcElement;
} else if(e.which) { // Netscape/Firefox/Opera

	keynum = e.which;
	obj = e.target;
	while(obj.nodeType != obj.ELEMENT_NODE)
		obj = obj.parentNode;
}
if ( keynum > 95 && keynum < 106 ) {
	return true;
}
if ( keynum == 8 ) {
	return true;
}
if ( keynum == 9 ) {
	return true;
}
if ( keynum == 13) {
	goto_ = 0;
	for ( i = 0; i < formObj.elements.length; i++ ) {
		if ( formObj.elements[i].name == obj.name ) {
			goto_ = formObj.elements[i].tabIndex + 1;
		}
	}
	if ( goto_ > 0 ) {
		for ( i = 0; i < formObj.elements.length; i++ ) {
			if ( formObj.elements[i].tabIndex == goto_ ) {
				formObj.elements[i].focus();
			}
		}	
	}
	return;
}
keychar = String.fromCharCode(keynum);
numcheck = /\d/;
return numcheck.test(keychar);
}

</script>