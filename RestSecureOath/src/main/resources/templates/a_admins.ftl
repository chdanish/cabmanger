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
 -->	<div class="row band col-xs-12" >
	<div class="row " >
	<button ng-click="toggleModal()" class="btn col-xs-2">Add Administrator</button>
	</div>
	<div class="row" ng-repeat="x in admins track by $index" ng-if="x.firstName">
			<div class="mycol col-sm-3">
			<img data-ng-src="data:image/png;base64,{{x.snap}}" class="profileimg"/>
			<div class="center">
				<input ng-model="files" driverid="{{x.userId}}"  onchange="angular.element(this).scope().file_changed(this,files)"
				 type="file" accept="image/*">
			 </div>
			 <div class="center">
				 <button ng-click="toggleModal(x.userId)" class="btn btn-xs">Edit Driver</button>
				 <span style="width: 10px"></span>
				 <button ng-click="deleteadmin(x.userId)" class="btn btn-xs">Delete Driver</button>
			 </div>
			 </div>
			<div class="mycol col-sm-3">
				First Name: {{x.firstName}} Last Name: {{x.lastName}}<br>
				Username: {{x.userName}}<br> Password: {{x.password}}<br>
			</div>
			<div class="mycol col-sm-3">
			NationalID: {{x.NationalID}} NationalID_expiry: {{x.nationalID_expiry}}
			</div>
	</div> 

	
	</div>
	
		<modal-dialog box-width="70%" box-height="90%" show="adddriver"  >
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
			    <label class="col-sm-2 control-label">First Name:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="driverDTO.firstName" ng-disabled="!edit" placeholder="First Name" required />
			    </div>
			  </div> 
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Last Name:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="driverDTO.lastName" ng-disabled="!edit" placeholder="Last Name" required />
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">User Name:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="driverDTO.userName" ng-disabled="!edit" placeholder="User Name" required/>
			    </div>
			  </div>
			  <div class="form-group">
			    <label class="col-sm-2 control-label">Email:</label>
			    <div class="col-sm-6">
			    <input type="email" class="form-control"  name="email" ng-model="driverDTO.email" placeholder="@email" required />
			    <span style="color:red" ng-show="myForm.email.$dirty && myForm.email.$invalid">
				<span ng-show="myForm.email.$error.required">Email is required.</span>
				<span ng-show="myForm.email.$error.email">Invalid email address.</span>
				</span>
			   </div>
			    
			  </div>
			   <div class="form-group">
		            <label class="col-sm-2 control-label">Password:</label>
		            <div class="col-sm-6">
		            <input type="password" name="password" class="form-control" ng-model="driverDTO.password" required />
					</div>
		        </div>
		        <div class="form-group">
		            <label class="col-sm-2 control-label">Confirm Password:</label>
		            <div class="col-sm-6">
		            <input type="password" name="confirmPassword" class="form-control" 
		                   ng-model="driverDTO.confirmPassword" 
		                   required compare-to="driverDTO.password" />
		            </div> 
					 <div class="msg-block col-sm-8" ng-show="myForm.$error">
					  <span style="color:red" class="msg-error" ng-show="myForm.confirmPassword.$error.compare" >
					    **Passwords don't match.
					  </span>
					</div>
		        </div>
		        <div class="form-group">
				    <label class="col-sm-2 control-label">National ID:</label>
				    <div class="col-sm-6">
				    <input type="text" class="form-control" ng-model="driverDTO.nationalID" ng-disabled="!edit" placeholder="National ID"/>
				    </div>
			    </div>
			    <div class="form-group">
				    <label class="col-sm-2 control-label">NationalID Expiry:</label>
				    <div class="col-sm-6">
				    <input type="text" name='nationalID_expiry' class="form-control" ng-model="driverDTO.nationalID_expiry" ng-disabled="!edit" placeholder="MM/DD/YYYY" date-validation/>
				    </div>
				    <div class="msg-block col-sm-8" ng-show="myForm.$error && myForm.nationalID_expiry.$touched">
					  <span style="color:red" class="msg-error" ng-show="myForm.nationalID_expiry.$error.datevalid" >
					    **Please input date in following format: MM/DD/YYYY.
					  </span>
					</div>
			    </div>
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="adminsubmit()" class="btn btn-primary" ng-disabled="myForm.$invalid">
			<span></span>Submit
			</button>
        </div>
      </div>
    </div>
  </modal-dialog>

<!-- </div>
</body>
</html> -->