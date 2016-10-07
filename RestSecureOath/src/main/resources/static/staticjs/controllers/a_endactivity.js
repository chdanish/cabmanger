'use strict'
app.controller('a_endactivity', function($rootScope,$scope,cacheService,
		activity_factory,$location) {
    

	$scope.showdata=false;
	cacheService.update('/a_endactivity/checkunfinishactivity').then(function(data){
		  console.log(data);
		  if(!data[0]){
			 // $location.path("/a_startactivity");
			  $scope.showdata=false;
		  }else if(data[0]){
			  $scope.showdata=true;
			  $scope.startdata= data[0];
		  }
		  
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
    $scope.endactivityDTO.endReading_snap = canvas.toDataURL(outputFormat);
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
	  $scope.endactivityDTO = {
			endReading		:null,
			endReading_snap	:"",
			activityID			:"",//vehicleID
			};
	  
	  $scope.endactivitysubmit=function(){
		  $scope.endactivityDTO.activityID=$scope.startdata.activityID;
			console.log($scope.endactivityDTO);
			cacheService.post('/a_endactivity/endactivity',$scope.endactivityDTO).then(function(data){
				  console.log(data);
				  $scope.showdata=false;
			  });
		}
	  
	  
});

