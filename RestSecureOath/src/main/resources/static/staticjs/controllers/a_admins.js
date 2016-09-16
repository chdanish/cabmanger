'use strict'
app.controller('a_admins', function($rootScope,$scope,cacheService,admin_factory) {
    
	$scope.admins=admin_factory.getlist();
	cacheService.get('/a_admins/pageable/list').then(function(data){
		console.log(data);
		for(var i=0;i<data.content.length;i++){
			admin_factory.add(data.content[i]);
		}
	});
    
});

app.factory('admin_factory', function($http) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(user) {
    	console.log(user.userId);
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