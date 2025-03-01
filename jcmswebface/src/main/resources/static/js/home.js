/**
 * 
 */
 
 
var app = angular.module("homeApp", []);
app.controller("homeCtrl", function($scope, $http) {

	$scope.selectedUserId="";	
	$scope.showmodal=false;	


	$scope.showUsers=function(){
		 $http({
			    method : "GET",
			    url : "./homectrl/fetchallusers"
			  }).then(function mySuccess(response) {
			      $scope.users = response.data.pocket;
			      alert(response.data.message);
			    }, function myError(response) {
			      $scope.users = response.statusText;
			  });
		
	};
	
	
	$scope.processUsers=function(clickedId){
		$scope.selectedUserId=clickedId;
		//alert("process users;"+$scope.selectedUserId);
		$scope.openModalPopup();
	};


	$scope.openModalPopup=function(){
		$scope.showmodal=true;
	};
	
	
	$scope.closeModalPopup=function(){
		$scope.showmodal=false;
	};
	
	
	
	$scope.registerCandidate=function(){
	
	$scope.referencenumber="reference10000";
	$scope.firstname="noorulameen";
	$scope.lastname="ameen";
	$scope.email="noorull11@gmail.com";
	$scope.contact="98848123450";
	$scope.gender="male";
	$scope.dateofbirth="15/03/1988";
	$scope.address1="A1";
	$scope.address2="A2";
	$scope.address3="A3";
	$scope.city="cHENNAI";
	$scope.zip="612804";
	$scope.country="INDIA";
	$scope.color="#225466";
	$scope.password="Pass@123";
	$scope.retypepassword="Pass@123";


	
	alert("register users...");
	var url = "./homectrl/registercandidateinfo";
	var CandidatePersonalData = {
			  referencenumber:$scope.referecenumber,
			  firstname:$scope.firstname,
			  lastname:$scope.lastname,
			  email:$scope.email,
			  contact:$scope.contact,
			  gender:$scope.gender,
			  dateofbirth:$scope.dateofbirth,
			  address1:$scope.address1,
			  address2:$scope.address2,
			  address3:$scope.address3,
			  city:$scope.city,
			  state:$scope.city,
			  zip:$scope.zip,
			  country:$scope.country,
			  photo:$scope.country,
			  color:$scope.color,
			  password:$scope.password,
			  retypepassword:$scope.retypepassword
	};
	
	 $http.post(url,  JSON.stringify(CandidatePersonalData)).then(function (response) {
		  alert("success");
		}, function (response) {
		// this function handles error
		alert("failure");
		alert(response.statusText);

		});
	 
	 
	
	};

});



 