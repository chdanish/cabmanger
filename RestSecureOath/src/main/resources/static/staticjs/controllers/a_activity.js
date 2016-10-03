'use strict'
app.controller('a_activity', function($rootScope,$scope,cacheService,activity_factory) {
    
	$scope.activities=activity_factory.getlist();
	cacheService.get('/a_activity/pageable/list').then(function(data){
		console.log(data);
		for(var i=0;i<data.content.length;i++){
			activity_factory.add(data.content[i]);
		}
		console.log($scope.activities);
	});
	
	$scope.file_changed = function(element,files) {
		  var driverid = element.attributes['driverid'].value;
		  var file = files[0];
		  var uploadUrl = "/a_vehicles/snapupload/"+driverid;
		  cacheService.fileupload( uploadUrl,file).then(function(data){
			  vehicle_factory.updatesnap(driverid,data.status);
		  });
		 }
	$scope.startactivity = false;
	$scope.edit=true;
	$scope.toggleModal = function(id) {
		cacheService.update('/a_startactivity/vehicle/list').then(function(data){
			  $scope.vehiclelist=data;
		  });
	    $scope.startactivity = !$scope.startactivity;
	  };
	  
	  $scope.activityDTO={
			startReading		:"",
			startReading_snap	:"",
			vehicle				:"",
			};
	  
	  $scope.activitysubmit=function(){
			console.log($scope.activityDTO);		
		}
	  
    
});

app.factory('activity_factory', function($http,cacheService) {

    var userId;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(activity) {
    	console.log(activity.activityID);
	    	if(activity.vehicle.snap==null){
	    		cacheService.get("static/images/avatar_car.txt").then(function(data){
	    			activity.vehicle.snap=data;
	    		});
	    	}
	    	if(activity.driver.snap==null){
	    		cacheService.get("static/images/avtar.txt").then(function(data){
	    			activity.driver.snap=data;
	    		});
	    	}
    	factory.list[activity.activityID]=activity;
    	console.log(factory.list);
        }
    
    factory.getlist = function() {
            return factory.list;
        }
   
 

    return factory;
});