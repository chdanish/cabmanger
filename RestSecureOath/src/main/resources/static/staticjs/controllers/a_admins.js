'use strict'
app.controller('a_admins', function($rootScope,$scope,cacheService,admin_factory) {
    
	$scope.admins=admin_factory.getlist();
	cacheService.get('/a_admins/pageable/list').then(function(data){
		console.log(data);
		for(var i=0;i<data.content.length;i++){
			admin_factory.add(data.content[i]);
		}
	});
	
	$scope.file_changed = function(element,files) {
		  var driverid = element.attributes['driverid'].value;
		  var file = files[0];
		  var uploadUrl = "/a_admins/snapupload/"+driverid;
		  cacheService.fileupload( uploadUrl,file).then(function(data){  
			  admin_factory.updatesnap(driverid,data.status);
		  });
		 }
	
	$scope.adddriver = false;
	$scope.toggleModal = function(id) {
		if(id){
			console.log(id);
			$scope.driverDTO={
					userId				: $scope.admins[id].userId,
					password			: "",
				    confirmPassword		: "",
					firstName			: $scope.admins[id].firstName,
					lastName			: $scope.admins[id].lastName,
					userName			: $scope.admins[id].userName,
					email				: $scope.admins[id].email,
					nationalID			: $scope.admins[id].nationalID,
					nationalID_expiry	: $scope.admins[id].nationalID_expiry ==null ? "":$scope.drivers[id].nationalID_expiry,			
			}			
		}else if(!id){
			$scope.driverDTO={
					userId				:"",
					password			:"",
				    confirmPassword		: "",
					firstName			:"",
					lastName			:"",
					userName			:"",
					email				:"",
					nationalID			:"",
					nationalID_expiry	:"",			
			}			
		}
		
	    $scope.adddriver = !$scope.adddriver;
	  };
	
	$scope.edit=true;		  
	$scope.driverDTO={
			userId				:"",
			password			:"",
		    confirmPassword		:"",
			firstName			:"",
			lastName			:"",
			userName			:"",
			email				:"",
			nationalID			:"",
			nationalID_expiry	:"",			
	};
	
	$scope.adminsubmit=function(){
		console.log($scope.driverDTO);
		if($scope.driverDTO.userId===""){
			console.log("add user");
			cacheService.post('/a_admins/add',$scope.driverDTO).then(function(data){
			console.log(data);
		});
		}else if($scope.driverDTO.userId != ""){
			console.log("Edit user");
			cacheService.post('/a_admins/update',$scope.driverDTO).then(function(data){
			console.log(data);
		});
		}
		
	}//http://ilevin350.com/jekyll/update/2016/03/17/creating-modal-dialogs-with-angular-js-and-custom-directives.html
	
	$scope.deleteadmin= function(id){
		console.log("Delete request for"+id);
		admin_factory.delete(id);
		$scope.admins=admin_factory.getlist();
	}
    
});

app.factory('admin_factory', function($http,cacheService) {

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
    factory.updatesnap = function(id,snap) {
    	factory.list[id].snap=snap;
    	console.log(factory.list[id]);
        return factory.list;
    }
    factory.delete = function(id) {
    	var newlist=[];
    	for(var i=0;i<factory.list.length;i++){
    		if(factory.list[i] && factory.list[i].userId!=id){
    			newlist[factory.list[i].userId]=factory.list[i];
    		}
    	}
    	delete factory.list;
    	factory.list=newlist;
    	console.log(factory.list);
        return factory.list;
    }

    factory.getuser =function(id) {
        return factory.list[id];
    }
 

    return factory;
});