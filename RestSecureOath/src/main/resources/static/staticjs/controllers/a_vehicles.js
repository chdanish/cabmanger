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
	
	$scope.file_changed = function(element,files) {
		  var driverid = element.attributes['driverid'].value;
		  var file = files[0];
		  var uploadUrl = "/a_vehicles/snapupload/"+driverid;
		  cacheService.fileupload( uploadUrl,file).then(function(data){
			  vehicle_factory.updatesnap(driverid,data.status);
		  });
		 }
	$scope.addvehicle = false;
	$scope.edit=true;
	$scope.toggleModal = function(id) {
		if(id){
			console.log(id);
			$scope.vehicleDTO={
					vehicleId			:$scope.vehicles[id].vehicleId,
					make				:$scope.vehicles[id].make,
					modelname			:$scope.vehicles[id].modelname,
					submodel			:$scope.vehicles[id].submodel,
					regnumber			:$scope.vehicles[id].regnumber,
					modelyear			:$scope.vehicles[id].modelyear,
					};			
		}else if(!id){
			$scope.vehicleDTO={
					vehicleId			:"",
					make				:"",
					modelname			:"",
					submodel			:"",
					regnumber			:"",
					modelyear			:"",
					};			
		}
		
	    $scope.addvehicle = !$scope.addvehicle;
	  };
	  
	  $scope.vehicleDTO={
			vehicleId			:"",
			make				:"",
			modelname			:"",
			submodel			:"",
			regnumber			:"",
			modelyear			:"",
			};
	  
	  $scope.vehiclesubmit=function(){
			console.log($scope.vehicleDTO);
			if($scope.vehicleDTO.vehicleId===""){
				console.log("add user");
				cacheService.post('/a_vehicles/add',$scope.vehicleDTO).then(function(data){
				console.log(data);
			});
			}else if($scope.vehicleDTO.vehicleId != ""){
				console.log("Edit user");
				cacheService.post('/a_vehicles/update',$scope.vehicleDTO).then(function(data){
				console.log(data);
			});
			}
			
		}
    
});

app.factory('vehicle_factory', function($http,cacheService) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(vehicle) {
    	console.log(vehicle.vehicleId);
	    	if(vehicle.snap==null){
	    		cacheService.get("static/images/avatar_car.txt").then(function(data){
	    			vehicle.snap=data;
	    		});
	    	}
    	factory.list[vehicle.vehicleId]=vehicle;
    	console.log(factory.list);
        }
    
    factory.getlist = function() {
            return factory.list;
        }
    factory.updatesnap = function(id,snap) {
    	factory.list[id].snap=snap;
    	console.log(factory.list[id]);
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