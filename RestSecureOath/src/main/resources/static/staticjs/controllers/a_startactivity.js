'use strict'
app.controller('a_startactivity', function($rootScope,$scope,cacheService,activity_factory) {
    
	cacheService.update('/a_startactivity/vehicle/list').then(function(data){
		  $scope.vehiclelist=data;
	  });
	
	$scope.file_changed = function(element,files) {
		  var driverid = element.attributes['driverid'].value;
		  var file = files[0];
		  var uploadUrl = "/a_vehicles/snapupload/"+driverid;
    var img = new Image();      
    img.onload = function() {
    var canvas = document.createElement('CANVAS');
  	var ctx = canvas.getContext('2d');
    canvas.height = this.height;
    canvas.width = this.width;
    ctx.drawImage(this, 0, 0);
    var outputFormat='image/jpg';
    $scope.activityDTO.startReading_snap = canvas.toDataURL(outputFormat);
    $scope.$apply();
    }
     var reader = new FileReader();
     reader.onload = function(readerEvt) {
          var binaryString = readerEvt.target.result;
          img.src = 'data:image/jpg;base64,'+btoa(binaryString);
      };

      reader.readAsBinaryString(file);
    /*image/png, image/jpeg, image/jpg, image/gif, image/bmp, image/tiff, image/x-icon, image/svg+xml, image/webp, image/xxx */
   // img.src = 'https://mdn.mozillademos.org/files/5395/backdrop.png';
		 }
	$scope.selectedvehicle={};
	  $scope.activityDTO = {
			startReading		:null,
			startReading_snap	:"",
			vehicleID			:"",//vehicleID
			};
	  
	  $scope.activitysubmit=function(){
		  $scope.activityDTO.vehicleID=$scope.selectedvehicle.vehicleId;
			console.log($scope.activityDTO);
			cacheService.post('/a_startactivity/startactivity',$scope.activityDTO).then(function(data){
				  console.log(data);
			  });
		}
	  
	  
});

