





var app=angular.module("settingsApp",[]);
app.controller("roleCtrl", function($scope, $http) {
	
	
	
	$scope.loadpage=function(){
		
		$scope.showUserRoleNewPopup=false;
		$scope.selectedrole="";
		
		
		$scope.loadRolesDropDown();
		//$scope.loadModuleRightsInformation();
	}
	
	
	$scope.roleChanging=function(){
		$scope.loadModuleRightsInformation();
	}
	
	$scope.ChangeModuleAccess=function(mod){
		//alert(mod.roleid+":"+mod.id+":"+mod.modulename);
		
		const current = new Date();
		const timestamp = current.getTime();
		
		var rolesrights = {
			id: mod.id,
			roleid: mod.roleid,
			modulename: mod.modulename,
			rights: mod.rights,
			level: mod.level,
			moduleid: mod.moduleid
	    };
		
		 $http({
			    method : "POST",
			    url : "./settingsctrl/role/rightschange?roleid="+mod.roleid+"&timestamp="+timestamp,
			    data : JSON.stringify(rolesrights)
			  }).then(function mySuccess(response) {
			       alert("Roles rights changed for the module:"+mod.modulename);
			       
			       $scope.loadModuleRightsInformation();
			       
			    }, function myError(response) {
			       alert(response.statusText);
			  });
		
	}
	
	$scope.loadModuleRightsInformation=function(){
		const current = new Date();
		const timestamp = current.getTime();
		
		var rolename=$scope.selectedrole.rolename;
		var roleid=$scope.selectedrole.roleid;
		
		 $http({
			    method : "GET",
			    url : "./settingsctrl/role/rights?roleid="+roleid+"&timestamp="+timestamp
			    
			  }).then(function mySuccess(response) {
			      $scope.rolesrightlist = response.data.pocket.rolesrightlist;
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
	
	
	
	$scope.processUsers=function(clickedId){
	    $scope.selectedUserId=clickedId;
		alert("process users;"+$scope.selectedUserId);
	};
	
		
	$scope.openPopup1=function(){
		$scope.openUserRoleNewPopup();
	};
	
	
	$scope.openUserRoleNewPopup=function(){
		$scope.showUserRoleNewPopup=true;
	};
	
	
	$scope.closeUserRoleNewPopup=function(){
		$scope.showUserRoleNewPopup=false;
	};
	

	
$scope.RoleRegistration=function(){
	
	const current = new Date();
	const timestamp = current.getTime();
	
	
	var RoleData = {
		rolename:$scope.rolename
	};

	 $http({
		    method : "POST",
		    url : "./settingsctrl/role/store?timestamp="+timestamp,
		    data: JSON.stringify(RoleData)
		  }).then(function mySuccess(response) {
		         alert(response.data.message);
		         $scope.closeUserRoleNewPopup();
		         $scope.loadpage();
		    }, function myError(response) {
		         alert("error"+response.statusText);
		  });
	
	};
	
	
	
		$scope.fileuploadfn = function() {
		
			const current = new Date();
			const timestamp = current.getTime();
			
			
			document.getElementById("uploadForm").submit();
			
	        	/*
	        var file = $scope.myfile;
	        
	        alert(file);
	        
		    var formData = new FormData();
			formData.append("myfile",file);
			formData.append('branch',$scope.branch);
 
		    
		   $http({
		    method : "POST",
		    url : "./files/upload?timestamp="+timestamp,
		    data: $scope.files, 
            headers: {"Content-Type": "multipart/form-data;boundary=----WebKitFormBoundaryMY3HZDvy2mdmpAtE"}
    
		  }).then(function mySuccess(response) {
			     alert("success");
		    }, function myError(response) {
		         alert("error"+response.statusText);
		  });
	//        multipart/form-data; boundary=----WebKitFormBoundaryMY3HZDvy2mdmpAtE
	
	     */
	      
	    
		    
	   };
	   

         
	  
});