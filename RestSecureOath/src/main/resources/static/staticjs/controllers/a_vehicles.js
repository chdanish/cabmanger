'use strict'
app.controller('a_vehicles', function($rootScope,$scope,cacheService,vehicle_factory) {
    
	$scope.vehicles=vehicle_factory.getlist();
	cacheService.get('/a_vehicles/pageable/list').then(function(data){
		console.log(data);
		for(var i=0;i<data.content.length;i++){
			vehicle_factory.add(data.content[i]);
		}
		console.log($scope.vehicles);
	});
    
});

app.factory('vehicle_factory', function($http) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(vehicle) {
    	console.log(vehicle.vehicleId);
    	factory.list[vehicle.vehicleId]=vehicle;
    	console.log(factory.list);
        }
    
    factory.getlist = function() {
            return factory.list;
        }
    factory.delete = function(id) {
        return delete factory.list[id];
    }

    factory.getuser =function(id) {
        return factory.list[id];
    }
 

    return factory;
});