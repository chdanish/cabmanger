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
  <script src="static/staticjs/app.js"></script>
  <script src="static/staticjs/external/angular-base64.js"></script>
  <script src="static/staticjs/controllers/a_drivers.js"></script>
  <script src="static/staticjs/controllers/a_admins.js"></script>
  <script src="static/staticjs/controllers/a_vehicles.js"></script>
  <script src="static/staticjs/directive/fileModel.js"></script>
  <script src="static/staticjs/service/cacheService.js"></script>
</head>
<body ng-app="app">

<div > 
                    <!--  ^--- Added class --> 
  <div class="">
    <div ng-controller="dashboard">
      <div class="sidebar" sidebar-directive="state">
        <navx  id="navigation-toggle" ng-click="toggleState()">Navigation</navx>
        <ul class="navigation">
          <li class="navigation-items"> <a ng-href="#home">Home</a> 
          </li>
          <li class="navigation-items"> <a href="#a_admins">Admin</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_drivers">Drivers</a> 
          </li>
          <li class="navigation-items"> <a href="#a_vehicles">Vehicles</a> 
          </li>
          <li class="navigation-items"> <a ng-href="#a_setup">Settings</a> 
          </li>
        </ul>
      </div>
    </div>
  </div>
</div>
<div ng-view class="container"></div> 
<!-- http://plnkr.co/edit/xzcjStdvmkI2rpfMzLjI?p=preview -->
</body>
</html>