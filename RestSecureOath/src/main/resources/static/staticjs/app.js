//(function() {
  
var app = angular.module('app', [ 'ngRoute' ]);

app.config(function($routeProvider, $locationProvider, $httpProvider) {

	$routeProvider
	.when('/home', {
		templateUrl : 'home',
		controller : 'dashboard'
	})
	.when('/a_setup', {
		templateUrl : '/a_setup',
		controller : 'navo'
	}).when('/a_admins', {
		templateUrl : '/a_admins',
		controller : 'a_admins'
	}).when('/a_drivers', {
		templateUrl : '/a_drivers',
		controller : 'a_drivers'
	}).when('/a_vehicles', {
		templateUrl : '/a_vehicles',
		controller : 'a_vehicles'
	})
	.otherwise('/home');
	/*$locationProvider
	.html5Mode({enabled: true,
	      requireBase: false
	    })
	    .hashPrefix('#');*/
});

app.controller('dashboard', function($scope) {
    
    $scope.state = false;
    
    $scope.toggleState = function() {
        $scope.state = !$scope.state;
    };
    
});
app.controller('navo',function($rootScope, $scope, $http, $location ,$window,cacheService){
	$scope.data={};
	
	cacheService.get('/setup/info').then(function(data) {
		console.log(data.status);
		$scope.SignupDTO = { 
	   			 name			: data.status.name,
	   			 registration	: data.status.regisration,
	   			 distanceunit	: data.status.distanceunit,
	   			 fuelunit		: data.status.fuelunit,
	   			 };
	});
	
	$scope.submit = function() {
   	cacheService.post('setup',$scope.SignupDTO).then(function(data) {
   		 console.log(data);
   		//$location.path("/dashboard");
   		$window.location.href="/dashboard";
   	 });
   }
});

app.directive('sidebarDirective', function() {
    return {
        link : function(scope, element, attr) {
            scope.$watch(attr.sidebarDirective, function(newVal) {
                  if(newVal)
                  {
                    element.addClass('show'); 
                    return;
                  }
                  element.removeClass('show');
            });
        }
    };
});  
  
  
//}())
