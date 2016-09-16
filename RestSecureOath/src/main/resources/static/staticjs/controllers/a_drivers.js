'use strict'
app.controller('a_drivers', function($rootScope,$scope,cacheService,driver_factory) {
    
	$scope.drivers=driver_factory.getlist();
	cacheService.get('/a_drivers/pageable/list').then(function(data){
		console.log(data);
		for(var i=0;i<data.content.length;i++){
			driver_factory.add(data.content[i]);
		}
	});
	
	$scope.file_changed = function(element,files) {
		  var driverid = element.attributes['driverid'].value;
		  console.log("Driver ID: "+driverid);
		  console.log(files[0]);
		  var file = files[0];
		  var uploadUrl = "/a_drivers/snapupload/"+driverid;
		  cacheService.fileupload( uploadUrl,file);
		 }
    
});

app.factory('driver_factory', function($http,cacheService) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(user) {
    	console.log(user.userId);
    	if(user.snap==null){
    		cacheService.get("static/images/avtar.txt").then(function(data){
    			user.snap=data;
    		});
    	}
    	factory.list[user.userId]=user;
    	//console.log(factory.list);
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


