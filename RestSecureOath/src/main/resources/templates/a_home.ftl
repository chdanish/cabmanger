<!-- <!DOCTYPE html>
<html> -->
<head>
<meta charset="UTF-8">
<title>home</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

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
<div class="row band  col-xs-12">
	<div class="container-fluid" dnd-list="bars">
		<div class="row">
		<div id="main12" class="dropdown pull-right">
					    <a ng-click="" class="btn btn-xs dropdown-toggle ddowncolor" type="button" id="menu1" data-toggle="dropdown">
					    <i class="material-icons  md-inactive md-dark">add_circle</i><span class="caret pull-left"></span></a>
						<ul class="dropdown-menu " role="menu" aria-labelledby="menu1">
							<li class="dropdown-submenu pull-right">
							       <a class="test pull-left" tabindex="-1" >Add Widget <span class="caret pull-left"></span></a>
							       <ul class="dropdown-menu">
							         	<li><a  ng-click="addwidget('widgetEmployee')"  id="widgetEmployee" class="btn btn-xs" >Employee</a></li>
										<li><a  ng-click="addwidget('widgetAdministrator')"  id="widgetAdministrator" class="btn btn-xs" >Adminitrator</a></li>
										<li><a  ng-click="addwidget('widgetVehicle')"  id="widgetVehicle" class="btn btn-xs" >Vehicle</a></li>
										<li><a  ng-click="addwidget('widgetGroup')"  id="widgetGroup" class="btn btn-xs" >Groups</a></li>
										<li><a  ng-click="addwidget('widgetMinichart')" id="miniChart" class="btn btn-xs" >Mini Chart</a></li>
							       </ul>
							</li>							
						</ul>
					</div>
			<!-- /col -->
		</div>
		<!-- /row -->
		<div gridster="gridsterOptions">
		  	<ul class="with-3d-shadow with-transitions">
		  		<li resize-detector resizeid="{{widget.widgetsid}}" gridster-item="widget" ng-repeat="widget in dashboard.widgets">
		  		  <div class="box fullsize">
		            <div ng-hide="false" class="box-header">
		                <h3>{{ widget.name }}</h3>
		                <button ng-click="deletewidget(widget.widgetsid)" type="button" class="close boxclose"  aria-label="Close"><span aria-hidden="true">&times;</span></button>
		            </div>
		            <div id="chartdiv{{widget.widgetsid}}" class="box-content fullsize chart">
			            <div  class="fullsize" display-widget data="widget">
			            
		               <nvd3 class="fullsize chart" ng-if="widget.chart" options="widget.chart.options" 
		                    data="widget.chart.data" 
		                    api="widget.chart.api" 
		                    config="config" 
		                    events="events"></nvd3> 
		            </div>
		        </div>
		  		</li>
		  	</ul>
  		</div>
	</div>
	<!-- /container -->
</div>
<!-- <nvd3 options="options" data="datam" class="with-3d-shadow with-transitions"></nvd3>
 -->


<!-- </div>
</body>
</html> -->