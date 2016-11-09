<!-- <!DOCTYPE html>
<html> -->
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet"
	href="https://getbootstrap.com/dist/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="static/css/modal.css" />
<link rel="stylesheet" href="static/css/a_home.css" />
<style type="text/css">
hr.style3 {
	border-top: 1px dashed #8c8b8b;
}

* {
	box-sizing: content-box;
}

.mycol {
	border-top: 1px dashed #8c8b8b !important;
	align-items: center;
	font-size: .8em;
	padding: 0px !important;
	margin: 0px !important;
}

.mycol p {
	padding: 0px !important;
	margin: 0px !important;
	background-color: inherit;
}

.col-sm-3 {
	min-width: 30% !important;
}
</style>
</head>
<!-- <body>
<div class="container col-xs-12 col-md-12 col-lg-12" style="border:solid #ff0000 1px; ">
 -->
<div class="row band  col-xs-11">
	<div class="container-fluid" dnd-list="bars">
		<div class="row">
		<button ng-click="addbar()"  class="btn btn-info dropdown-toggle" >
			<i class="material-icons  md-inactive md-dark">add_circle</i>
		</button>
			<!-- <div class="col-xs-12">
				<ol class="breadcrumb">ADD: 
					<li><button id="mm" add_miniwidget type='"mini"' barcount="countmini" maxbar="2" maxwidgets='4' class="btn btn-xs"  >Mini Widget Bar</button></li>
					<li><button add-largewidget type='"large"' barcount="countlarge" maxbar="2" maxwidgets='4' class="btn btn-xs" >Large Widget Bar</button></li>
					<li><button ng-click="addbar()" class="btn btn-xs" >ADD Bar</button></li>
					<li><button  draggable id="widgetEmployee" class="btn btn-xs" >Employee</button></li>
					<li><button  draggable id="widgetAdministrator" class="btn btn-xs" >Adminitrator</button></li>
					<li><button  draggable id="widgetVehicle" class="btn btn-xs" >Vehicle</button></li>
					<li><button  draggable id="widgetGroup" class="btn btn-xs" >Groups</button></li>
				</ol>
				<h1>Dashboard</h1>
				<div id="dashboard_container"></div>				
			</div> -->
			<!-- /col -->
		</div>
		<!-- /row -->
		<div ng-if="bar.dashboardbarid" droppable drop="handleDrop" id="{{bar.dashboardbarid}}"
		 ng-repeat="bar in bars track by $index | orderBy:'-bar_order'" class="fixrow " stylex="min-height: 20px;background-color: red;">
					<div id="bar{{bar.dashboardbarid}}" class="dropdown pull-right">
					    <a ng-click="opendropdown(bar.dashboardbarid)" class="btn btn-xs dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">Options
					    <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
	      					<li><a  class="btn btn-xs"  >Move Up</a></li>
							<li><a  class="btn btn-xs" >Move Down</a></li>
							<li><a  draggable id="widgetEmployee" class="btn btn-xs" >Employee</a></li>
							<li><a  draggable id="widgetAdministrator" class="btn btn-xs" >Adminitrator</a></li>
							<li><a  draggable id="widgetVehicle" class="btn btn-xs" >Vehicle</a></li>
							<li><a  draggable id="widgetGroup" class="btn btn-xs" >Groups</a></li>
							<li><a ng-click="deletebar(bar.dashboardbarid)" class="btn btn-xs" >Delete</a></li>
						</ul>
					</div>
					<div display-widget data="widg" ng-if="widg.widgetid" ng-repeat="widg in bar.widget | orderBy:['-widget_order']">
						<!-- Order: {{widg.widget_order}} Id: {{widg.widgetid}} Type: {{widg.widget_type}} -->
					</div>
			<hr class="bottommargin"></hr>	<!-- Order: {{bar.bar_order}} Id: {{bar.dashboardbarid}} --></div>
	</div>
	<!-- /container -->
</div>
<!-- <nvd3 options="options" data="datam" class="with-3d-shadow with-transitions"></nvd3>
 -->


<!-- </div>
</body>
</html> -->