<head>
<meta charset="UTF-8">
<title>SETUP</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
 *{
    box-sizing: content-box;
}
  .vertical-center {
  min-height: 100%;  /* Fallback for browsers do NOT support vh unit */
  min-height: 100vh; /* These two lines are counted as one :-)       */

  display: flex;
  align-items: center;
}
</style>
</head>
<!-- <body>  -->
<div class="row band col-xs-11 " > <!-- 
                      ^--- Added class  -->
  <div  ng-controller="navo">
	<h4>Welcome to Uber Cab Manger</h4>
	<p>** Please fill the below information to get started!!</p>
		<div>
			<form role="form" class="col-xs-10"  data-ajax="false" >
			  <div class="form-group">
			    <label for="name">Company Name:</label>
			    <input type="text" class="form-control" ng-model="SignupDTO.name" id="name" name="name"/>
			  </div>
			  <div class="form-group">
			    <label for="registration ">Company Registration :</label>
			    <input type="text" class="form-control" ng-model="SignupDTO.registration" id="registration " name="registration "/>
			  </div>
			  <div class="form-group">
			    <label for="distanceunit">Distance Unit(Km\Mi):</label>
			    <input list="dunit" class="form-control" ng-model="SignupDTO.distanceunit" id="distanceunit" name="distanceunit" placeholder="Select unit from menu"/>
				  <datalist id="dunit">
				    <option value="KILOMETER">
				    <option value="MILE">
				  </datalist> 
			  </div>
			  <div class="form-group">
			    <label for="fuelunit ">Fuel Unit(liter\Galon):</label>
			    <input list="funit" class="form-control" ng-model="SignupDTO.fuelunit" id="fuelunit" name="fuelunit" placeholder="Select unit from menu"/>
				  <datalist id="funit">
				    <option value="LITRE">
				    <option value="GALON">
				  </datalist> 
			  </div>
			  <input type="hidden" id="csrf_token" name="${_csrf.parameterName}" value="${_csrf.token}"/>
			  <button ng-click="submit()" class="btn btn-primary" ng-disabled="error || incomplete">
			  <span></span>Submit
			  </button>
			</form>
		</div>
  </div>
</div>

<!-- </body>
 -->