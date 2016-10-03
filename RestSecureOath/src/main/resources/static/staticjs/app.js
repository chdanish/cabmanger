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
	}).when('/a_groups', {
		templateUrl : '/a_groups',
		controller : 'a_groups'
	}).when('/a_admins', {
		templateUrl : '/a_admins',
		controller : 'a_admins'
	}).when('/a_drivers', {
		templateUrl : '/a_drivers',
		controller : 'a_drivers'
	}).when('/a_vehicles', {
		templateUrl : '/a_vehicles',
		controller : 'a_vehicles'
	}).when('/a_activity', {
		templateUrl : '/a_activity',
		controller : 'a_activity'
	}).when('/a_startactivity', {
		templateUrl : '/a_startactivity',
		controller : 'a_startactivity'
	}).when('/a_endactivity', {
		templateUrl : '/a_endactivity',
		controller : 'a_endactivity'
	}).otherwise('/home');
	/*$locationProvider
	.html5Mode({enabled: true,
	      requireBase: false
	    })
	    .hashPrefix('#');*/
});

app.controller('dashboard', function($rootScope,$scope,cacheService,$location,$window) {
    
    $scope.state = false;
    
    $scope.toggleState = function() {
        $scope.state = !$scope.state;
    };
    cacheService.get('/setup/info').then(function(data) {
    	if(data.status.name===null||data.status.distanceunit===null
    			||data.status.fuelunit===null){
    		$location.path("/a_setup");
    	}
    },function(error) {
    	console.log("Controller: dashboard, Error: "+ error);
    	$location.path("/");
    });
    
    $scope.activitymenu=false;
    $scope.activitymenuswitch=function(){
    	$scope.activitymenu=!$scope.activitymenu;
    };
    
    $scope.logout = function() {
    	cacheService.post('/logout').then(function(data){
    		$window.location.href='/';},function(error) {
        	console.log("Ctrl: dashboard,Req: /logout, Error: "+ error);
        	$window.location.href='/';
        });
    }
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
	},function(error) {
		console.log("Controller: navo, Error: "+ error);
		$location.path("/");
    });
	
	$scope.submit = function() {
   	cacheService.post('setup',$scope.SignupDTO).then(function(data) {
   		 console.log(data);
   		//$location.path("/dashboard");
   		$window.location.href="/";
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
app.directive('sidebarDirective', function() {
    return {
    		scope: {
        state:'=',
        },
        link : function(scope, element, attr) {
        element.on('click', function() {
                if( scope.state === false)
                  {
                		document.getElementById("mySidenav").style.width = "250px";
    								document.getElementById("main").style.marginLeft = "250px";
    								document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
                    return;
                  } else if(scope.state === true)
                  {
                		 document.getElementById("mySidenav").style.width = "0";
   									 document.getElementById("main").style.marginLeft= "0";
    								document.body.style.backgroundColor = "white";
                    return;
                  }
            });
        }
    };
});
  
  
//}())
