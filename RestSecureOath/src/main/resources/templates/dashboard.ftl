<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DASHBOARD</title>
<style type="text/css">


</style>

<!-- <script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script> -->
  <!-- <script data-require="angular.js@1.3.5" data-semver="1.3.5" src="https://code.angularjs.org/1.3.5/angular.js"></script>
   --><script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.5/angular-route.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/d3/3.5.6/d3.min.js" charset="utf-8"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.1/nv.d3.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular-nvd3/1.0.5/angular-nvd3.min.js"></script>
  <!-- <script src="https://rawgit.com/marmotz/angular-drag-and-drop-lists/master/angular-drag-and-drop-lists.js" type="text/javascript"></script> -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/nvd3/1.8.4/nv.d3.css" />
  
   <!-- <link rel="stylesheet" href="https://raw.githack.com/ManifestWebDesign/angular-gridster/v0.13.5/dist/angular-gridster.min.css"/> -->
   <!-- <link rel="stylesheet" href="http://rawgit.com/ManifestWebDesign/angular-gridster/v0.13.5/dist/angular-gridster.min.css"/> -->

   <script src="https://raw.githack.com/ManifestWebDesign/angular-gridster/v0.13.5/dist/angular-gridster.min.js"></script>
    <!-- http://embed.plnkr.co/jEQMch/ -->
  
  <link rel="stylesheet" href="static/css/external/gridster/angular-gridster.min.css" />
  <link rel="stylesheet" href="static/css/style.css" />
  <link rel="stylesheet" href="static/css/sidebar.css" />
  <link rel="stylesheet" href="static/css/navbar.css" />
  <script src="static/staticjs/app.js"></script>
  <script src="static/staticjs/controllers/a_home.js"></script>
  <script src="static/staticjs/controllers/a_groups.js"></script>
  <script src="static/staticjs/controllers/a_drivers.js"></script>
  <script src="static/staticjs/controllers/a_admins.js"></script>
  <script src="static/staticjs/controllers/a_vehicles.js"></script>
  <script src="static/staticjs/controllers/a_activity.js"></script>
  <script src="static/staticjs/controllers/a_startactivity.js"></script>
  <script src="static/staticjs/controllers/a_endactivity.js"></script>
  <script src="static/staticjs/filter/datetime.js"></script>
  <script src="static/staticjs/filter/unique.js"></script>
  <script src="static/staticjs/filter/groupselction.js"></script>
  <script src="static/staticjs/directive/datevalidation.js"></script>
  <script src="static/staticjs/directive/dnd.js"></script>
  <script src="static/staticjs/directive/navbar.js"></script>
  <script src="static/staticjs/directive/password.js"></script>
  <script src="static/staticjs/directive/modalDialog.js"></script>
  <script src="static/staticjs/directive/editmodalDialog.js"></script>
  <script src="static/staticjs/directive/resizedetector.js"></script>
  <script src="static/staticjs/directive/displaywidget.js"></script>
  <script src="static/staticjs/directive/widgetminichart.js"></script>
  <script src="static/staticjs/directive/widgetemployee.js"></script>
  <script src="static/staticjs/directive/widgetadministrator.js"></script>
  <script src="static/staticjs/directive/widgetvehicle.js"></script>
  <script src="static/staticjs/directive/widgetgroup.js"></script>
  <script src="static/staticjs/directive/add_miniwidgetbar.js"></script>
  <script src="static/staticjs/directive/add_largewidgetbar.js"></script>
  <script src="static/staticjs/service/chartService.js"></script>
  <script src="static/staticjs/service/dataservice.js"></script>
  <script src="static/staticjs/service/cacheService.js"></script>
  <script src="static/staticjs/factory/chartfactory.js"></script>
</head>
<body ng-app="app">

<div id="main" class="bigcontainer">

	<my-navbar></my-navbar>
	<div ng-view class="right "></div>
	<div ng-hide="true" id="userdata" dashid="${dashboardid}" ></div>
</div>

<div  ng-controller="dashboard" id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" sidebar-directive state="true">&times;</a>
  <ul class="navigation col-xs-12">  		
          <li class="navigation-items"> <a ng-href="#a_home">Home</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_groups">Groups</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_admins">Admin</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_drivers">Drivers</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_vehicles">Vehicles</a> 
          </li>
          <li role="navigation-items" class="dropdown">
		    <a ng-click="activitymenuswitch()" ng-href="#a_activity" class="dropdown-toggle" data-toggle="dropdown"  aria-haspopup="true" aria-expanded="false">
		      Activity <span class="caret"></span>
		    </a>
		    <ul class="dropdown-menu" style="display: block" ng-if="activitymenu" >
		      <li><a xng-href="$">Approved</a></li>
		      <li><a xng-href="$">Pending</a></li>
		    </ul>
		  </li>
		  <li class="navigation-items"> <a ng-href="#a_startactivity">Start Activity</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_endactivity">End Activity</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_setup">Settings</a> 
          </li>
          <li class="navigation-items"> <a  ng-click="logout()">Logout</a> 
          </li>
        </ul>
</div>
 
<!-- http://plnkr.co/edit/xzcjStdvmkI2rpfMzLjI?p=preview -->
</body>
</html>