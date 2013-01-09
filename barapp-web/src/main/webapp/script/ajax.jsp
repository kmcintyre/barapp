<script>
   var http_request = false;
   function makePOSTRequest(url, parameters) {
      http_request = false;
      if (window.XMLHttpRequest) { // Mozilla, Safari,...
         http_request = new XMLHttpRequest();
         if (http_request.overrideMimeType) {
            http_request.overrideMimeType('text/xml');
         }
      } else if (window.ActiveXObject) { // IE
         try {
            http_request = new ActiveXObject("Msxml2.XMLHTTP");
         } catch (e) {
            try {
               http_request = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (e) {}
         }
      }
      if (!http_request) {
         alert('Cannot create XMLHTTP instance');
         return false;
      }
      
      http_request.onreadystatechange = alertContents;
      http_request.open('POST', url, true);
      http_request.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
      http_request.setRequestHeader("Content-length", parameters.length);
      http_request.setRequestHeader("Connection", "close");
      http_request.send(parameters);
   }

   function alertContents() {
      if (http_request.readyState == 4) {
         if (http_request.status == 200) {
            //alert(http_request.responseText);
            result = http_request.responseText;
            alert(result);
            document.getElementById('myspan').innerHTML = result;            
         } else {
            alert('There was a problem with the request.');
         }
      }
   }
   
   function action(obj) {
      get("action=" + obj);
   }
   

   function get(obj) {
	 // if (document.getElementById && document.getElementsByTagName && document.createTextNode) {
	  	
	  //}
	  //var poststr = "mytextarea1=doingit";
      //var poststr = "mytextarea1=" + encodeURI( document.getElementById("mytextarea1").value ) + "&mytextarea2=" + encodeURI( document.getElementById("mytextarea2").value );
      makePOSTRequest('post.do', obj);
   }



function startUp() {
       //initShowHide();
}

window.onload = startUp;
</script>