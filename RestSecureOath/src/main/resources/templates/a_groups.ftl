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
/* .mycol p{
	padding: 0px !important;
	margin: 0px !important;
	background-color: inherit;
	min-width: 100% !important;
} */

.col-sm-3{
min-width: 30% !important;
}
</style> 
</head>
<!-- <body>
<div class="container col-xs-12 col-md-12 col-lg-12" style="border:solid #ff0000 1px; ">
 -->	<div class="row band col-xs-12 " >
	<div class="row" >
	<button ng-click="toggleModal()" class="btn col-xs-2">Add Group</button>
	</div>
	<div class="row" ><label>Filter By>>    </label>
	<label for="filter_type">Type:</label>{{gfilter.type}}
	<select name="filter_type" id="filter_type" ng-model="gfilter.type" >
      <option value=""></option>
      <option value="USER">USER</option>
      <option value="VEHICLE">VEHICLE</option>
    </select>
    <label for="filter_name">Name:</label>{{filter.name.name}}
	<select name="filter_name" id="filter_name" ng-model="gfilter.name" 
			ng-options="option.name for option in groups   |groupselction" >
    </select>  
	</div>
	<div class="row" ng-repeat="x in groups track by $index" ng-if="x.groupId && (x.name ==gfilter.name.name|| gfilter.name.name==null) && x.name!='Owner'">
			 <div class="container row "><h2 >Group: {{x.name}}</h2></div>
			 <div class="">
				  
				  <div class="table-responsive ">
				  <table ng-if="x.user.length > 0 && (gfilter.type == 'USER'|| gfilter.type == '')" class="table">
				    <thead>
				      <tr>
				        <th><h2>Users</h2></th>
				        <th>Firstname</th>
				        <th>Lastname</th>
				        <th>UserName</th>
				        <th>Roles</th>
				        <th>Options</th>
				      </tr>
				    </thead>
				    <tbody>
				      <tr ng-repeat="u in x.user">
				        <td><img data-ng-src="data:image/png;base64,{{u.snap}}" ng-if="u.snap" height="50%" width="50%" />
				        <img data-ng-src="data:image/png;base64,{{avatar}}" ng-if="!u.snap" height="50%" width="50%" /></td>
				        <td>{{u.firstName}}</td>
				        <td>{{u.lastName}}</td>
				        <td>{{u.userName}}</td>
				        <td ><p ng-repeat="r in u.roles">{{r}}</p></td>
				        <td><button ng-click="changegroup(u.userId)" class="btn btn-xs">Change</button></td>
				      </tr>
				    </tbody>
				  </table>
				  <table ng-if="x.vehicle.length > 0  && (gfilter.type == 'VEHICLE'|| gfilter.type == '')" class="table ">
				    <thead>
				      <tr>
				        <th><h2>Vehicles</h2></th>
				        <th>Make</th>
				        <th>Model Name</th>
				        <th>Model Year</th>
				        <th>Registeration#</th>
				        <th>Options</th>
				      </tr>
				    </thead>
				    <tbody>
				      <tr ng-repeat="v in x.vehicle">
				        <td><img data-ng-src="data:image/png;base64,{{u.snap}}" ng-if="u.snap" height="50%" width="50%" />
				        <img data-ng-src="data:image/png;base64,{{avatar_vehicle}}" ng-if="!u.snap" height="50%" width="50%" /></td>
				        <td>{{v.make}}</td>
				        <td>{{v.modelname}}</td>
				        <td>{{v.modelyear}}</td>
				        <td >{{v.regnumber}}</td>
				        <td><button ng-click="changegroup(v.vehicleId)" class="btn btn-xs">Change</button></td>
				      </tr>
				    </tbody>
				  </table>
				  </div>
				</div>
			<hr/>
	</div> 

	
	</div>
	
	<modal-dialog box-width="70%" box-height="90%" show="addgroup"  >
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
			    <label class="col-sm-2 control-label">Name:</label>
			    <div class="col-sm-6">
			    <input type="text" class="form-control" ng-model="groupDTO.name" ng-disabled="!edit" placeholder="Group Name" required />
			    </div>
			  </div>
			</form>
			
			<hr style="width: 95%;">
			<button ng-click="groupsubmit()" class="btn btn-primary" ng-disabled="myForm.$invalid">
			<span></span>Submit
			</button>
        </div>
      </div>
    </div>
  </modal-dialog>
  
    <modal-dialog box-width="400px" box-height="150px" show="addtogroup">
    <div class="row">
      <div class="col-md-12">
        <h3>Header</h3>
        <hr style="border-top:1px solid darkblue"/>
      </div>
    </div>
    <div class="row">
      <div class="col-md-12">
        This is an important message
      </div>
    </div>
  </modal-dialog>

<!-- </div>
</body>
</html> -->