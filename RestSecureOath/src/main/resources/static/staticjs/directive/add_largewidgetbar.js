'use strict'
app.directive('addLargewidget',['$compile','chartService', function($compile,chartService) {
	var chart = '<div class="col-xs-4"><nvd3 options="options" data="datam" class="with-3d-shadow with-transitions"></nvd3></div>' ;
  return {
	  /*require: "ngModel",*/
	  restrict: 'A',
	  replace: true,
	  template: '<button  class="btn btn-xs" ng-click="add_largewidgetbar2()" >Large Bar</button>',
    scope: {
    	type: '=',
    	barcount: '=',
    	maxbar: '=',
    	maxwidgets: '=',
    },
    transclude: false, // Insert custom content inside the directive
    link: function(scope, element, attrs) {

    	scope.widgetlimit={};
    	
    	scope.fixme= function(id){
       	 console.log(id);
       	 var getbtndiv = angular.element(document.getElementById(id));
       	 
       	 console.log(getbtndiv[0].className);
       	 if(getbtndiv[0].className != "open"){
       		 getbtndiv.addClass("open");
       	 } else if(getbtndiv[0].className === "open"){
       		 getbtndiv.removeClass("open");
       	 }    	 
        }
    	
      scope.add_largewidgetbar2 = function() {
    	  if(scope.barcount==scope.maxbar) return;//with respect to bars
    	  scope.widgetlimit[scope.barcount]=scope.maxwidgets;
    	  console.log("Adding "+ scope.type+" bar." + scope.widgetlimit[scope.barcount]);
		  var btnDiv = "\'LargebtnDiv"+scope.barcount+"\'"; //Should be unique name e.g LargebtnDiv
		  var addchart = 'ng-click="add_largewidget(' + scope.barcount + ',2);fixme('+String(btnDiv)+')"';
		 
		  var btx = '<div id='+String(btnDiv)+'>'+
    	  '<button  class="btn btn-info dropdown-toggle" ng-if="widgetlimit['+scope.barcount+'] > 0" ng-disabled="buttonDisabled" ng-click="fixme('+String(btnDiv)+')">'+
          '<i class="material-icons  md-inactive md-dark">add_circle</i></button>'+
          '<ul class="dropdown-menu">'+
          '<li class="dropdown-header">SELECT WIDGET </li>'+
          '<li><a '+addchart+'>Chart</a></li>'+
          '<li><a >Chart & Mini Widget</a></li>'+
          '</ul>'+
          '</div>';
        	  //var div = angular.element(document.getElementById("dashboard_container")).append(angular.element('<div id="largebar' + i + '" class="row">' + btn+ '</div>'));
        	  var div = $compile(angular.element('<div id="minibar' + scope.barcount + '" class="fixrow dropdown">' + String( btx)+ '</div>'))(scope); 
    		  angular.element(document.getElementById("dashboard_container")).append(div);
    		  scope.barcount++;
      };
      scope.options=chartService.getoptions;
      scope.datam = chartService.getchart;
      
      scope.add_largewidget = function(id,type){
    	  console.log("Large div ID:"+id+" Type: "+type);
    	  angular.forEach(scope.widgetlimit, function(value, key) {
    		  if(key == id && scope.widgetlimit[key]>0 ){
    			  console.log(scope.widgetlimit[key]);
    			  var div2 = angular.element(document.getElementById("minibar"+id));
    			  if(type==2){
    				  var gg = angular.element(chart);
    				  $compile(gg)(scope);
    				  div2.append(gg);
    			  }/*
    			  if(type==3){
    				  var gg = angular.element(administrator);
    				  $compile(gg)(scope);
    				  div2.append(gg);    				  
    			  }
    			  if(type==4){
    				  var gg = angular.element(vehicle);
    				  $compile(gg)(scope);
    				  div2.append(gg);
    			  }
    			  if(type==5){
    				  var gg = angular.element(group);
    				  $compile(gg)(scope);
    				  div2.append(gg);    				  
    			  }*/
    			  scope.widgetlimit[key]--;
    		  }
    		});
      }
      
    },
   // templateUrl: 'a_modalDialog',
  };
}]);