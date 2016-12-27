<!-- <!DOCTYPE html>
<html> -->
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="https://getbootstrap.com/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="static/css/modal.css"/>
<style type="text/css">
.row{
	 margin-left: 2px; 
}

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
 -->	<div class="row band  col-xs-12" >
	<div class="row" >
	<button ng-click="toggleModal()" class="btn col-xs-2">Add Vehicle</button>
	</div>
	<div class="row " ng-repeat="x in vehicles track by $index" ng-if="x.vehicleId">
			<div class="mycol col-sm-3">
			<img data-ng-src="data:image/png;base64,{{x.snap}}" class="profileimg" />
				<div class="center">
					<input ng-model="files"  driverid="{{x.vehicleId}}"  onchange="angular.element(this).scope().file_changed(this,files)"
					 type="file" accept="image/*">
				</div>
				<div class="center">
					 <button ng-click="toggleModal(x.vehicleId)" class="btn btn-xs">Edit Driver</button>
					 <span style="width: 10px"></span>
					 <button ng-click="deletedriver(x.vehicleId)" class="btn btn-xs">Delete Driver</button>
				</div>
			 </div>	
			<div class="mycol col-sm-3">
				Make: {{x.make}}<br/>
				Model Name: {{x.modelname}}<br/>
				Sub Model: {{x.submodel}}<br/>
				Model Year: {{x.modelyear}}<br/>
				Registration No# : {{x.regnumber}}<br/>
				Created on: {{x.createdAT}} <br/>
			</div>
			<div class="mycol col-sm-3">
			</div>
	</div> 

	
	</div>
	
	<modal-dialog box-width="70%" box-height="90%" show="addvehicle"  >
    <div class="row">
      <div class="col-md-12">
        <h3>Add New Driver</h3>
        <hr class="col-md-12" style="border-top:1px solid darkblue; width: 95%"/>
      </div>
    </div>
    <div  class="scrollable" >
      <div class="col-sm-12">
      	<div  class="col-sm-12">
            <h3 ng-show="edit">Signup new account:</h3>
			<h3 ng-hide="edit">Edit User:</h3>
			
			<form class="form-horizontal" name="myForm" novalidate>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Make:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="vehicleDTO.make" ng-disabled="!edit" placeholder="First Name" required />
			    </div>
			  </div> 
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Model Name:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="vehicleDTO.modelname" ng-disabled="!edit" placeholder="Last Name" required />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Sub Model:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="vehicleDTO.submodel" ng-disabled="!edit" placeholder="User Name" required/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Registration No#:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="vehicleDTO.regnumber" ng-disabled="!edit" placeholder="User Name" required/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Model Year: {{vehicleDTO.modelyear}}</label>
			    <div class="col-sm-6">
			    <input type="range" min="1980" max="2016" class="form-control" ng-model="vehicleDTO.modelyear" ng-disabled="!edit" placeholder="User Name" required/>
			    </div>
			  </div>
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="vehiclesubmit()" class="btn btn-primary" ng-disabled="myForm.$invalid">
			<span></span>Submit
			</button>
        </div>
      </div>
    </div>
  </modal-dialog>

<!-- </div>
</body>
</html> -->