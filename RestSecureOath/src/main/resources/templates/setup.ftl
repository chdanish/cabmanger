<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SETUP</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">

  .vertical-center {
  min-height: 100%;  /* Fallback for browsers do NOT support vh unit */
  min-height: 100vh; /* These two lines are counted as one :-)       */

  display: flex;
  align-items: center;
}
</style>
<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.min.js"></script>
<script type="text/javascript">
var app = angular.module('setup', []);
app.controller('navigation',function($rootScope, $scope, $http, $location ,$window){
	$scope.data={};
	
	$scope.submit = function() {
    	
   	 var SignupDTO = { name: $scope.name, registration: $scope.registration, distanceunit: $scope.distanceunit, fuelunit: $scope.fuelunit};
   	 //$window.alert($scope.fName+" " + $scope.lName);
   	 $http.post('setup',SignupDTO).then(function(data) {
   		 console.log(data);
   		//$location.path("/dashboard");
   		$window.location.href="/dashboard";
   	 });
   }
});

</script>
</head>
<body ng-app="setup">

<div class="jumbotron vertical-center"> <!-- 
                      ^--- Added class  -->
  <div class="container" ng-controller="navigation">
	<h4>Welcome to Uber Cab Manger</h4>
	<p>** Please fill the below information to get started!!</p>
		<div>
			<form role="form"  data-ajax="false" >
			  <div class="form-group">
			    <label for="name">Company Name:</label>
			    <input type="text" class="form-control" ng-model="name" id="name" name="name"/>
			  </div>
			  <div class="form-group">
			    <label for="registration ">Company Registration :</label>
			    <input type="text" class="form-control" ng-model="registration" id="registration " name="registration "/>
			  </div>
			  <div class="form-group">
			    <label for="distanceunit">Distance Unit:</label>
			    <input list="dunit" class="form-control" ng-model="distanceunit" id="distanceunit" name="distanceunit" placeholder="Select unit from menu"/>
				  <datalist id="dunit">
				    <option value="KILOMETER">
				    <option value="MILE">
				  </datalist> 
			  </div>
			  <div class="form-group">
			    <label for="fuelunit ">Fuel Unit:</label>
			    <input list="funit" class="form-control" ng-model="fuelunit" id="fuelunit" name="fuelunit" placeholder="Select unit from menu"/>
				  <datalist id="funit">
				    <option value="LITRE">
				    <option value="GALON">
				  </datalist> 
			  </div>
			  <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			  <button ng-click="submit()" class="btn btn-primary" ng-disabled="error || incomplete">
			  <span></span>Submit
			  </button>
			</form>
		</div>
  </div>
</div>

</body>
</html>