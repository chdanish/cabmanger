<!-- <!DOCTYPE html>
<html> -->
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="https://getbootstrap.com/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="static/css/modal.css"/>
<style type="text/css">

hr.style3 {
	border-top: 1px dashed #8c8b8b;
}
 *{
    box-sizing: content-box;
}
.mycol{
	border-top: 1px dashed #8c8b8b !important;
	align-items: center;
	font-size: .8em;
	padding: 0px !important;
	margin: 0px !important;
}
.mycol p{
	padding: 0px !important;
	margin: 0px !important;
	background-color: inherit;
}

.col-sm-3{
min-width: 30% !important;
}
</style> 
</head>
<!-- <body>
<div class="container col-xs-12 col-md-12 col-lg-12" style="border:solid #ff0000 1px; ">
 -->	<div class="row band  col-xs-11" >
	<div class="row" >
	<!-- <button ng-click="toggleModal()" class="btn col-xs-2">Filter</button> -->
	</div>
	<div class="row "  >
			<div  class="scrollable" >
      <div class="col-sm-12">
      	<div  class="col-sm-12">
            <h3 >Start New Activity:</h3>
	
			
			<form class="form-horizontal" name="myForm" novalidate>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Meter Reading:</label>
			    <div class="col-sm-6">
			    <input type="number" class="form-control" ng-model="activityDTO.startReading" ng-disabled="false" placeholder="123456" required />
			    </div>
			  </div>
			
			  <div class="form-group">
			  	<img data-ng-src="{{activityDTO.startReading_snap}}" class="profileimg" ng-if='activityDTO.startReading_snap' height='auto' style="max-width: 300px;" />
			    <label class="col-sm-2 control-label">Meter Snap:</label>
			    <div class="col-sm-6">
			    <input ng-model="files"  driverid="{{x.vehicleId}}"  onchange="angular.element(this).scope().file_changed(this,files)"
					 type="file" accept="image/*">
			    </div>
			  </div>
			  
			  <div class="form-group">
			  	<label class="col-sm-2 control-label">Vehicle:</label>
			    <div class="col-sm-6">
			    Make: <select name="vehiclelist" id="vehiclelist" ng-model="make" 
						ng-options="option.make for option in vehiclelist | unique:'make'" >
			    </select> <br/>
			    Model name:<select name="vehiclelist" id="vehiclelist" ng-model="modelname" 
						ng-options="option.modelname for option in vehiclelist | unique:'modelname' | filter:{make:make.make}" >
			    </select> <br/>
			    Registration No#:<select name="vehiclelist" id="vehiclelist" ng-model="selectedvehicle" 
						ng-options="option.regnumber for option in vehiclelist | unique:'regnumber'| filter:{make:make.make} | filter:{modelname:modelname.modelname}" >
			    </select>  <br/>
			    </div>
			  </div>
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="activitysubmit()" class="btn btn-primary" ng-disabled="myForm.$invalid">
			<span></span>Submit
			</button>
        </div>
      </div>
    </div>
	</div> 

	
	</div>
	
	<modal-dialog box-width="70%" box-height="90%" show="startactivity"  >
    <div class="row">
      <div class="col-md-12">
        <h3>Add New Driver</h3>
        <hr class="col-md-12" style="border-top:1px solid darkblue; width: 95%"/>
      </div>
    </div>
    <div  class="scrollable" >
      <div class="col-sm-12">
      	<div  class="col-sm-12">
            <h3 >Start new Activity:</h3>
	
			
			<form class="form-horizontal" name="myForm" novalidate>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Meter Reading:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="activityDTO.startReading" ng-disabled="!edit" placeholder="First Name" required />
			    </div>
			  </div>
			
			  <div class="form-group">
			  	<img data-ng-src="data:image/png;base64,ajkshdkjashd" class="profileimg" />
			    <label class="col-sm-2 control-label">Meter Snap:</label>
			    <div class="col-sm-6">
			    <input ng-model="files"  driverid="{{x.vehicleId}}"  onchange="angular.element(this).scope().file_changed(this,files)"
					 type="file" accept="image/*">
			    </div>
			  </div>
			  
			  <div class="form-group">
			  	<label class="col-sm-2 control-label">Vehicle:</label>
			    <div class="col-sm-6">
			    Make: <select name="vehiclelist" id="vehiclelist" ng-model="make" 
						ng-options="option.make for option in vehiclelist | unique:'make'" >
			    </select> <br/>
			    Model name:<select name="vehiclelist" id="vehiclelist" ng-model="modelname" 
						ng-options="option.modelname for option in vehiclelist | unique:'modelname' | filter:{make:make.make}" >
			    </select> <br/>
			    Registration No#:<select name="vehiclelist" id="vehiclelist" ng-model="activityDTO.vehicle" 
						ng-options="option.regnumber for option in vehiclelist | unique:'regnumber'| filter:{make:make.make} | filter:{modelname:modelname.modelname}" >
			    </select>  <br/>
			    </div>
			  </div>
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="activitysubmit()" class="btn btn-primary" ng-disabled="myForm.$invalid">
			<span></span>Submit
			</button>
        </div>
      </div>
    </div>
  </modal-dialog>

<!-- </div>
</body>
</html> -->