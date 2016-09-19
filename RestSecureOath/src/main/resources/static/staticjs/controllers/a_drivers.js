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
	$scope.adddriver = false;
	$scope.toggleModal = function(id) {
		if(id){
			console.log(id);
			$scope.driverDTO={
					userId				: $scope.drivers[id].userId,
					password			: "",
				    confirmPassword		: "",
					firstName			: $scope.drivers[id].firstName,
					lastName			: $scope.drivers[id].lastName,
					userName			: $scope.drivers[id].userName,
					email				: $scope.drivers[id].email,
					nationalID			: $scope.drivers[id].nationalID,
					nationalID_expiry	: $scope.drivers[id].nationalID_expiry ==null ? "":$scope.drivers[id].nationalID_expiry,
					licenseID			: $scope.drivers[id].licenseID,
					licenseID_expiry	: $scope.drivers[id].licenseID_expiry  ==null ? "":$scope.drivers[id].licenseID_expiry,			
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
					licenseID			:"",
					licenseID_expiry	:"",			
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
			licenseID			:"",
			licenseID_expiry	:"",			
	};

	$scope.driversubmit=function(){
		console.log($scope.driverDTO);
		if($scope.driverDTO.userId===""){
			console.log("add user");
			cacheService.post('/a_drivers/add',$scope.driverDTO).then(function(data){
			console.log(data);
		});
		}else if($scope.driverDTO.userId != ""){
			console.log("Edit user");
			cacheService.post('/a_drivers/update',$scope.driverDTO).then(function(data){
			console.log(data);
		});
		}
		
	}//http://ilevin350.com/jekyll/update/2016/03/17/creating-modal-dialogs-with-angular-js-and-custom-directives.html
	
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


