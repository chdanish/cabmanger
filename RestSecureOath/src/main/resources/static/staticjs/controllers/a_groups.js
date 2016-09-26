'use strict'
app.controller('a_groups', function($rootScope,$scope,cacheService,groups_factory) {
    
	$scope.groups=groups_factory.getlist();
	cacheService.get('/a_groups/pageable/list').then(function(data){
		console.log(data);
		for(var i=0;i<data.content.length;i++){
			groups_factory.add(data.content[i]);
		}
		console.log($scope.vehicles);
	});
	
	
	$scope.addgroup = false;
	$scope.edit=true;
	$scope.toggleModal = function(id) {
			$scope.groupDTO={name :""};					
	    $scope.addgroup = !$scope.addgroup;
	  };
	  
	  $scope.groupDTO={
			name :"",
			};
	  
	  $scope.groupsubmit=function(){
			console.log($scope.groupDTO.name);
			cacheService.post('/a_groups/add/'+$scope.groupDTO.name).then(function(data){
				console.log(data);
			});			
		}
	  $scope.addtogroup = false;
	  $scope.edit=true;
	  $scope.toggleModaladduser = function(id) {				
		    $scope.addtogroup = !$scope.addtogroup;
		    cacheService.get('/a_groups/pageable/userlist').then(function(data){
				console.log(data);
		    });
		  };
	  
	  $scope.deletegroup=function(id){
		  console.log(id);
		  groups_factory.delete(id);
		  $scope.groups=groups_factory.getlist();
	  }
	  
	  cacheService.get("static/images/avtar.txt").then(function(data){
			$scope.avatar=data;
	    });
	  cacheService.get("static/images/avatar_car.txt").then(function(data){
			$scope.avatar_vehicle=data;
	    });
});

app.factory('groups_factory', function($http,cacheService) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(group) {
    	console.log(group.groupId);
    	factory.list[group.groupId]=group;
    	console.log(factory.list);
        }
    
    factory.getlist = function() {
            return factory.list;
        }
    factory.delete = function(id) {
    	var newlist=[];
    	for(var i=0;i<factory.list.length;i++){
    		if(factory.list[i] && factory.list[i].groupId!=id){
    			newlist[factory.list[i].groupId]=factory.list[i];
    		}
    	}
    	delete factory.list;
    	factory.list=newlist;
    	console.log(factory.list);
        return factory.list;
    }

    factory.getgroup =function(id) {
        return factory.list[id];
    }
 

    return factory;
});