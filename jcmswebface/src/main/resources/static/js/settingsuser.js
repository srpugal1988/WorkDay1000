





var app=angular.module("settingsApp",[]);
app.controller("userCtrl", function($scope, $http) {
	
	
	
	$scope.loadpage=function(){
		
		$scope.showUserAddNewPopup=false;
		$scope.userrefno="";
		
		
		$scope.loadRolesDropDown();
		$scope.loadUserReferenceNumber();
		$scope.showUsersListGrid();
		
		
		$scope.firstname="";
		$scope.lastname="";
		$scope.email="";
		$scope.contactnumber="";
		$scope.jobtitle="";
		$scope.username="";
		$scope.password="";
		$scope.retypepassword="";
	}
	
	
	$scope.showUsersListGrid=function(){
		const current = new Date();
		const timestamp = current.getTime();
		 $http({
			    method : "GET",
			    url : "./settingsctrl/user/fetchall?timestamp="+timestamp
			  }).then(function mySuccess(response) {
			      $scope.users = response.data.pocket.userlist;
			      //alert(response.data.message);
			    }, function myError(response) {
			       alert(response.statusText);
			  });
	};
	
	
	$scope.loadRolesDropDown=function(){
		const current = new Date();
		 const timestamp = current.getTime();
		  $http({
			    method : "GET",
			    url : "./settingsctrl/role/fetchall?timestamp="+timestamp
			  }).then(function mySuccess(response) {
			      $scope.roleslist = response.data.pocket.rolesinformationlist;
			      //alert("roles:"+$scope.roleslist);
			    }, function myError(response) {
			      alert(response.statusText);
			  });
			  
			  
		// $scope.roleslist= ["ADMIN", "USER", "JOBSEEKER","CLIENT"];
	};
	
	
	$scope.loadUserReferenceNumber=function(){
		const current = new Date();
		const timestamp = current.getTime();
		 $http({
			    method : "GET",
			    url : "./settingsctrl/user/getnextuserreferencenumber?timestamp="+timestamp
			  }).then(function mySuccess(response) {
				  //alert("userrefer"+response.data.data);
			      $scope.userrefno= response.data.pocket.userreferencenumber;
			      //alert(response.data.message);
			    }, function myError(response) {
				   alert(response.statusText);	
				  $scope.userrefno= "0";
			  });
	};
	
	$scope.processUsers=function(clickedId){
	    $scope.selectedUserId=clickedId;
		alert("process users;"+$scope.selectedUserId);
	};
	
		
	$scope.openPopup1=function(){
		$scope.loadUserReferenceNumber();
		$scope.openUserAddNewPopup();
	};
	
	
	$scope.openUserAddNewPopup=function(){
		$scope.showUserAddNewPopup=true;
	};
	
	
	$scope.closeUserAddNewPopup=function(){
		$scope.showUserAddNewPopup=false;
	};
	
	
	$scope.UserRegistration=function(){
		const current = new Date();
		const timestamp = current.getTime();
		var valstatus=$scope.userregistrationvalidation();
		if(valstatus==true){
				var UserData = {
						  userrefno:$scope.userrefno,
						  firstname:$scope.firstname,
						  lastname:$scope.lastname,
						  email:$scope.email,
						  contactnumber:$scope.contactnumber,
						  jobtitle:$scope.jobtitle,
						  roleid:$scope.selectedrole.roleid,
						  rolename:$scope.selectedrole.rolename,
						  username:$scope.username,
						  password:$scope.password,
						  retypepassword:$scope.retypepassword
				};
			
				$http({
					    method : "POST",
					    url : "./settingsctrl/user/store?timestamp="+timestamp,
					    data: JSON.stringify(UserData)
					  }).then(function mySuccess(response) {
						     if(response.data.code=="100"){
						         alert(response.data.message);
						         $scope.closeUserAddNewPopup();
						         $scope.loadpage();
					         }
					         else if(response.data.code=="99"){
								 alert(response.data.pocket.error.field+":"+response.data.pocket.error.message)
							 }
							 else{
								 alert("some problems");
							 }
					         
					    }, function myError(response) {
					         alert("error"+response.statusText);
					  });
			
	     }
	
	};
	
	
	
	$scope.userregistrationvalidation=function(){
		
		var valstatus=false;
		if($scope.firstname==""){
			alert("Firstname cannot be empty");
			valstatus=false
		}
		else if($scope.lastname==""){
			alert("Lastname cannot be empty");
			valstatus=false
		}
		else if($scope.email==""){
			alert("Email cannot be empty");
			valstatus=false
		}
		else if($scope.contactnumber==""){
			alert("Contactnumber cannot be empty");
			valstatus=false
		}
		else if($scope.jobtitle==""){
			alert("Jobtitle cannot be empty");
			valstatus=false
		}
		else if($scope.selectedrole==undefined){
			alert("Selectedrole cannot be empty");
			valstatus=false
		}
		else if($scope.username==""){
			alert("Username cannot be empty");
			valstatus=false
		}
		else if($scope.password==""){
			alert("Password cannot be empty");
			valstatus=false
		}
		else if($scope.retypepassword==""){
			alert("Retypepassword cannot be empty");
			valstatus=false
		}
		else if($scope.retypepassword!=$scope.password){
			alert("Password and Retypepassword should be same");
			valstatus=false
		}
		else{
			valstatus=true;
		}
		return valstatus;
		
	};
	
});