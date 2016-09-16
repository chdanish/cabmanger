<!-- <!DOCTYPE html>
<html> -->
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="https://getbootstrap.com/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style type="text/css">
.band {
  width: 100% !important;
  background-color: #D7EAD8;
  color: #323F3F;
  clear: both;
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
.container{
    display: flex;
    align-items: center;
    justify-content: center;
	width:  100%;
    height: 100%;
}
.col-sm-3{
min-width: 30% !important;
}
</style> 
</head>
<!-- <body>
<div class="container col-xs-12 col-md-12 col-lg-12" style="border:solid #ff0000 1px; ">
 -->	<div class="row band col-xs-12 col-md-12 col-lg-12" >
	<button class="btn col-xs-3 col-md-3">Add Administrator</button>
	<div class="row band" ng-repeat="x in admins track by $index" ng-if="x.firstName">
			<div class="mycol col-sm-3">
			<img data-ng-src="data:image/png;base64,{{x.snap}}" />
			<input ng-model="files" driverid="{{x.userId}}"  onchange="angular.element(this).scope().file_changed(this,files)"
			 type="file" accept="image/*">
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
	

<!-- </div>
</body>
</html> -->