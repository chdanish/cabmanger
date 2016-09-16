var app = angular.module('hello', [ 'ngRoute','ngCookies' ]);
app.config(function($routeProvider, $httpProvider,$cookiesProvider) {

	/*$routeProvider.when('/', {
		templateUrl : 'login',
		controller : 'navigation'
	})
	.otherwise('uaa/login');*/
	
	$httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';

});
app.controller('navigation',

function($rootScope, $scope, $http, $location, $route ,$window, $routeParams,$cookieStore,$cacheFactory,cacheService,$timeout) {

	    
	    this.tab = 1;
	    $scope.edit = true;
	   
	    
	    this.setTab = function (tabId) {
            this.tab = tabId;
        };

        this.isSet = function (tabId) {
            return this.tab === tabId;
        };
        
        


        	  var authenticate = function(credentials, callback) {

        	    var headers = credentials ? {authorization : "Basic "
        	        + btoa(credentials.username + ":" + credentials.password)
        	    } : {};

        	    $http.post('login', {headers : headers}).success(function(data) {
        	      if (data.name) {
        	        $rootScope.authenticated = true;
        	      } else {
        	        $rootScope.authenticated = false;
        	      }
        	      callback && callback();
        	    }).error(function() {
        	      $rootScope.authenticated = false;
        	      callback && callback();
        	    });

        	  }
        
        $scope.credentials = {};
        $scope.login = function() {
            authenticate($scope.credentials, function() {
              if ($rootScope.authenticated) {
            	console.log("success!!");
                $location.path("/");
                $scope.error = false;
              } else {
            	  console.log("Failed!!");
                $location.path("/login");
                $scope.error = true;
              }
            });
        };
	    
	    $scope.submit = function() {
	    	
	    	 var SignupDTO = { firstName: $scope.fName, lastName: $scope.lName, userName: $scope.uName, password: $scope.pw2, email: $scope.email};
	    	 //$window.alert($scope.fName+" " + $scope.lName);
	    	 cacheService.post('signup',SignupDTO).then(function(data) {
	    		 if(data.status=='done'){
	    			 $timeout(function() {angular.element('#login').triggerHandler('click');}, 0);
	    		 }else $timeout(function() {angular.element('#signup').triggerHandler('click');}, 0);
	    	 });
	    }
	    
	   /* ---------------------------------------------------------------------------------------------------*/
	    
	    
		  $scope.facebookauth = function (){
			 
			  console.log("Looking for cookie");
			 if( $cookieStore.get('SESSION')){
				 console.log("Cookie found");
				 $cookieStore.remove('JSESSIONID');
					$cookieStore.remove('SESSION');
				 
			 }
			
				
				$cookieStore.remove('JSESSIONID');
				$cookieStore.remove('SESSION');
				  
				  
				  
				  $window.location.href='http://localhost:8080/facebookauth';
			  
		  }
	    
	    

}).controller('login', function($scope, $http) {
	$http.get('resource/').success(function(data) {
		$scope.greeting = data;
	})
});


 
app.directive('pwCheck', [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
          var firstPassword = '#' + attrs.pwCheck;
          elem.add(firstPassword).on('keyup', function () {
            scope.$apply(function () {
              var v = elem.val()===$(firstPassword).val();
              ctrl.$setValidity('pwmatch',elem.val() === scope[attrs.pwCheck]);
            });
          });
        }
      }
    }]);

//-------------------------------------------------------------------------------------
app.controller('facebookauth',

		function( $http, $httpProvider,$cookies) {

	  
	  $scope.facebookauth = function (){
		  
			$httpProvider.defaults.withCredentials = false;
			superCache.removeAll();
			  
			  var JSESSIONID = $cookies.get('JSESSIONID');
			  // Setting a cookie
			  $cookies.put('JSESSIONID', '');
			  
			  var JSESSIONID = $cookies.get('SESSION');
			  // Setting a cookie
			  $cookies.put('SESSION', '');
			  
			  $window.location.href='/facebookauth';
		  
	  }
	
});

//-Debug Code Starts here-------------------------------------------------------------------------

app.run([
        '$rootScope',
        function($rootScope) {
          // see what's going on when the route tries to change
          $rootScope.$on('$locationChangeStart', function(event, newUrl, oldUrl) {
            // both newUrl and oldUrl are strings
            console.log('locationChangeStart--Starting to leave %s to go to %s', oldUrl, newUrl);
          });
        }
      ]);
app.run([
	    '$rootScope', '$location', function ($rootScope, $location)  {
	      // see what's going on when the route tries to change
	      $rootScope.$on('$routeChangeStart', function(event, current, next, rejection) {
	        // next is an object that is the route that we are starting to go to
	        // current is an object that is the route where we are currently
	        var currentPath = current.$$route.originalPath;
	        var nextPath = next.$$route.originalPath;
	
	        console.log('routeChangeStart--Starting to leave %s to go to %s', currentPath, nextPath);
	      });
	    }
	  ]);


app.service('cacheService',function($http, $cacheFactory, $q) {
	  var cache = $cacheFactory('resourceCache', {capacity: 100}); // 1
	  var getreq =  function(url) {
	      if (cache.get(url)) {
		        return cache.get(url); // 2
		      }

		      var promise = $http.get(url).then(
		        function(response) {
		          cache.put(url, $q.when(response.data)); // 4
		          return response.data;
		        }
		      );
		      cache.put(url, promise); // 3
		      return promise;
	  }
	  var postreq =  function(url,data) {
	           var promise = $http.post(url,data).then(
		        function(response) {
		          cache.put(url, $q.when(response.data)); // 4
		          return response.data;
		        }
		      );
		      cache.put(url, promise); // 3
		      return promise;
	  }
	  var remove = function(url){
		  cache.remove(url);
		  console.log("Cache cleared for url: "+url);
	  }
	  
	  var update = function(url){
		  cache.remove(url);
		  console.log("Cache cleared for url: "+url);
		  return getreq(url);
	  }
	  
	  return {
	    get		:getreq,
	    post	:postreq,
	    remove	:remove,
	    update	:update,
	  };
	});
