//THIS SCRIPT FILE PURELY IMPLEMENTED BY -JAVASCRIPT LANGUAGE  
   
   
   
   
//Ajax Call
function loadMenuBar() {
	      
		  var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		      if (this.readyState == 4 && this.status == 200) {
		        // alert(xhttp.responseText);
		         
		         var myArr = JSON.parse(xhttp.responseText);
		         myFunction(myArr);
		         
		      }
		  };
		  xhttp.open("GET","./menu",true);
		  xhttp.send();
		  
		  
		  function myFunction(arr) {
			    var out = "";
			    var i;
			    for(i = 0; i < arr.length; i++) {
			       var mid="M"+arr[i].id;
			       var rights=arr[i].rights;
			       
			       
			       if(rights=="1"){
			    	      var element = document.getElementById(mid);
			    		  element.style.display="block";
			       }else{
			    	      var element = document.getElementById(mid);
			    		  element.style.display="none";
			       }
			       
			    }
			}
		  
		  applyActiveMenuColour();
		  dragElement();
		 
	 }
	
	

function dragElement() {
	
 var elmnt=document.getElementById("myModal");	
 
 if(elmnt===null){
	 return false;
 }
 
 var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  if (document.getElementById(elmnt.id + "header")) {
    /* if present, the header is where you move the DIV from:*/
    document.getElementById(elmnt.id + "header").onmousedown = dragMouseDown;
  } else {
    /* otherwise, move the DIV from anywhere inside the DIV:*/
    elmnt.onmousedown = dragMouseDown;
  }

  function dragMouseDown(e) {
    e = e || window.event;
    e.preventDefault();
    // get the mouse cursor position at startup:
    pos3 = e.clientX;
    pos4 = e.clientY;
    document.onmouseup = closeDragElement;
    // call a function whenever the cursor moves:
    document.onmousemove = elementDrag;
  }

  function elementDrag(e) {
    e = e || window.event;
    e.preventDefault();
    // calculate the new cursor position:
    pos1 = pos3 - e.clientX;
    pos2 = pos4 - e.clientY;
    pos3 = e.clientX;
    pos4 = e.clientY;
    // set the element's new position:
    elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
    elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
  }

  function closeDragElement() {
    /* stop moving when mouse button is released:*/
    document.onmouseup = null;
    document.onmousemove = null;
  }
}


function applyActiveMenuColour(){
   
   document.getElementById("M1000").style.backgroundColor="#036062;";
   document.getElementById("M2000").style.backgroundColor="#036062;";
   document.getElementById("M3000").style.backgroundColor="#036062;";
   document.getElementById("M4000").style.backgroundColor="#036062;";
   document.getElementById("M5000").style.backgroundColor="#036062;";
   document.getElementById("M6000").style.backgroundColor="#036062;";
   document.getElementById("M7000").style.backgroundColor="#036062;";
   
   
   var pageindex=document.getElementById("idlblmoduleindex").textContent;
   var activemodule="M"+pageindex;
   document.getElementById(activemodule).style.backgroundColor="red";       
}
   
   
	



function loginformsubmit(){
		//document.getElementById("idloginform").submit();
}
function loginformreset(){
		//document.getElementById("idloginform").reset();
		 document.forms["nmloginform"]["username"].value="";
	     document.forms["nmloginform"]["password"].value="";
		 clearerrormessage();
}
function clearerrormessage(){
	    document.getElementById("idspnmessage").textContent ="";
}


		
function validateLoginForm(){
	
	 var usernameValue = document.forms["nmloginform"]["username"].value;
	 var passwordValue = document.forms["nmloginform"]["password"].value;
	 
	 if(usernameValue==""){
		  document.getElementById("idspnmessage").textContent ="Username cannot be empty!";
	 }
	 else if(passwordValue==""){
		 document.getElementById("idspnmessage").textContent ="Password cannot be empty!";
	 }
	 else{
		 document.getElementById("idloginform").submit();
	 }
}	



	

