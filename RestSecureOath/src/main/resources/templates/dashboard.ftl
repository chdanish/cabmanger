<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DASHBOARD</title>
<style type="text/css">


</style>
  <!-- <script data-require="angular.js@1.3.5" data-semver="1.3.5" src="https://code.angularjs.org/1.3.5/angular.js"></script>
   --><script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.5/angular.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/angular.js/1.3.5/angular-route.js"></script>
  <link rel="stylesheet" href="static/css/style.css" />
  <link rel="stylesheet" href="static/css/sidebar.css" />
  <link rel="stylesheet" href="static/css/navbar.css" />
  <script src="static/staticjs/app.js"></script>
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
  <script src="static/staticjs/directive/navbar.js"></script>
  <script src="static/staticjs/directive/password.js"></script>
  <script src="static/staticjs/directive/modalDialog.js"></script>
  <script src="static/staticjs/directive/editmodalDialog.js"></script>
  <script src="static/staticjs/service/cacheService.js"></script>
</head>
<body ng-app="app">
<div id="main" class="bigcontainer">
	<my-navbar></my-navbar>
	<div ng-view class="right "></div>
</div>

<div  ng-controller="dashboard" id="mySidenav" class="sidenav">
  <a href="javascript:void(0)" class="closebtn" sidebar-directive state="true">&times;</a>
  <ul class="navigation col-xs-12">  		
          <li class="navigation-items"> <a ng-href="#home">Home</a> 
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