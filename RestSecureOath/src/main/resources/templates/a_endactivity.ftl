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
	<div ng-if="showdata" class="row" >
	 <button ng-click="toggleaddrefuel()" class="btn col-xs-2">Add Refuel</button> 
	 <span ></span>
	 <button ng-click="toggleModal()" class="btn col-xs-2">Add Ride</button> 
	</div>
	<div class="row "  >
			<div  class="scrollable" >
      <div class="col-sm-12">
      <div ng-if="!showdata" class="col-sm-12">
            	<h3>Activity not initialized.</h3>
            	<h3>Go to <a ng-href="#a_startactivity">Start Activity</a> </h3>              
      </div>
      	<div ng-if="showdata" class="col-sm-12">
            <h3 >End Activity:</h3>
	
			
			<form  class="form-horizontal" name="myForm" novalidate>
			<div class="form-group">
			    <label class="col-sm-2 control-label">Start Time:</label>
			    <div class="col-sm-6 control-label">
			    {{startdata.createdAT | datetime}}
			    </div>
			  </div>
			<div class="form-group">
			    <label class="col-sm-2 control-label">Start Reading:</label>
			    <div class="col-sm-6 control-label">
			    {{startdata.startReading}}
			    </div>
			  </div>
			<div class="form-group">
			    <label class="col-sm-2 control-label">Vehicle:</label>
			    <div class="col-sm-6 control-label">
			    {{startdata.vehicle.make}} {{startdata.vehicle.modelname}} {{startdata.vehicle.regnumber}}
			    </div>
			  </div>			
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Meter Reading:</label>
			    <div class="col-sm-6">
			    <input type="number" class="form-control" ng-model="endactivityDTO.endReading" ng-disabled="false" placeholder="123456" required />
			    </div>
			  </div>
			
			  <div class="form-group">
			  	<img data-ng-src="{{endactivityDTO.endReading_snap}}" class="profileimg" ng-if='endactivityDTO.endReading_snap' height='auto' style="max-width: 300px;" />
			    <label class="col-sm-2 control-label">Meter Snap:</label>
			    <div class="col-sm-6">
			    <input ng-model="files"  driverid="{{x.vehicleId}}"  onchange="angular.element(this).scope().file_changed(this,files)"
					 type="file" accept="image/*">
			    </div>
			  </div>
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="endactivitysubmit()" class="btn btn-primary" ng-disabled="myForm.$invalid">
			<span></span>End Activity
			</button>
        </div>
      </div>
    </div>
	</div> 

	
	</div>
	
	<modal-dialog box-width="70%" box-height="90%" show="addrefuel"  >
    <div class="row">
      <div class="col-md-12">
        <h3>Add Refuel</h3>
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
			    <input type="text" class="form-control" ng-model="xreading" ng-disabled="!edit" placeholder="(Optional)"  />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Refuel</label>
			    <div class="col-sm-6">**Select any two options below
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Volume:</label>
			    <div class="col-sm-6">
			    <input type="checkbox" ng-disabled="checkboxModel.cost&&checkboxModel.rate" ng-model="checkboxModel.volume">
			    <input type="number" class="form-control" ng-model="addrefuelDTO.volume" ng-disabled="!checkboxModel.volume" placeholder="volume" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Cost:</label>
			    <div class="col-sm-6">
			    <input type="checkbox" ng-disabled="checkboxModel.volume&&checkboxModel.rate" ng-model="checkboxModel.cost">
			    <input type="number" class="form-control" ng-model="addrefuelDTO.cost" ng-disabled="!checkboxModel.cost" placeholder="cost" />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Rate:</label>
			    <div class="col-sm-6">
			    <input type="checkbox" ng-disabled="checkboxModel.volume&&checkboxModel.cost" ng-model="checkboxModel.rate">
			    <input type="number" class="form-control" ng-model="addrefuelDTO.rate" ng-disabled="!checkboxModel.rate" placeholder="rate"  />
			    </div>
			  </div>			  
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="submitaddrefuel()" class="btn btn-primary" ng-disabled="submitbutton3||myForm.$invalid">
			<span></span>Submit
			</button>
        </div>
      </div>
    </div>
  </modal-dialog>
  
  <modal-dialog box-width="70%" box-height="90%" show="addride"  >
    <div class="row">
      <div class="col-md-12">
        <h3>Add Ride</h3>
        <hr class="col-md-12" style="border-top:1px solid darkblue; width: 95%"/>
      </div>
    </div>
    <div  class="scrollable" >
      <div class="col-sm-12">
      	<div  class="col-sm-12">
            <h3 >Start new Activity:</h3>
	
			
			<form class="form-horizontal" name="myForm" novalidate>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Fare:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="activityDTO.startReading" ng-disabled="!edit" placeholder="e.g 200" required />
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