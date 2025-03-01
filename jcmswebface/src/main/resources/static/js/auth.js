/**
 * 
 */
 
 
var app = angular.module("loginApp", []);
app.controller("loginCtrl", function($scope, $http, $window) {
	
	$scope.isloadiconshow=false;
	
	
	$scope.LoginRequestMethod=function(){
    		
		
		if($scope.username=="" || $scope.username==undefined){
		     alert("Username cannot be empty");
		     document.getElementById("idspnmessage").textContent ="Username cannot be empty!";
		}
		else if($scope.password=="" || $scope.password==undefined){
		    alert("Password cannot be empty");	  
		     document.getElementById("idspnmessage").textContent ="Password cannot be empty!";
		}
		else
		{    
		
		        $scope.isloadiconshow=true;
		        
				document.getElementById("idspnmessage").textContent ="";
				
				var params={
					username: $scope.username,
					password: $scope.password
			    };
			
				 $http({
					    method : "POST",
					    url : "./auth/login",
					    data :  JSON.stringify(params)
					  }).then(function mySuccess(response) {
						   $scope.isloadiconshow=false;
						   
					        $scope.code = response.data.code;
					        $scope.message= response.data.message;
					      
					      if($scope.code=="100"){//loginsuccess
							  $window.location.href = "./1000";
						  }
						  else{//loginfailure
						     document.getElementById("idspnmessage").textContent ="Invalid Username or password";
							 // $window.location.href = "./1";
							  alert("Invalid Username or password");
						  }
					     
					    }, function myError(response) {
							$scope.isloadiconshow=false;
					        alert(response.statusText);
					  });
					  
		}			  
		
	};
	

	
	$scope.processUsers=function(clickedId){
		$scope.selectedUserId=clickedId;
		alert("process users;"+$scope.selectedUserId);
		
	};


});
 

	
	
//$http.post(url,  JSON.stringify(Userdata)).then(function (response) {
		//var params={
			//		username: $scope.username,
				//	password: $scope.password
		//	};
			