'use strict'
app.directive('addMiniwidget',['$compile','$timeout', function($compile,$timeout) {
	
	var employees = '<widget-employee></widget-employee>' ;
	
	var administrator = '<widget-administrator></widget-administrator>' ;
	
	var vehicle = '<widget-vehicle></widget-vehicle>' ;
	
	var group = '<widget-group></widget-group>' ;
	var i=0;
	
	
  return {
	  /*require: "ngModel",*/
	  restrict: 'A',
	  replace: true,
	  template: '<button  class="btn btn-xs" ng-click="add_miniwidgetbar2()"  >Mini Bar</button>',
    scope: {
    	type: '=',
    	barcount: '=',
    	maxbar: '=',
    	maxwidgets: '=',
    },
    transclude: false, // Insert custom content inside the directive
    link: function(scope, element, attrs) {
    	//with respect to bars
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
      
     var div;
      scope.add_miniwidgetbar2 = function() {
    	  if(scope.barcount==scope.maxbar) return;//with respect to bars
    	  scope.widgetlimit[scope.barcount]=scope.maxwidgets;
    	  console.log("Adding "+ scope.type+" bar." + scope.widgetlimit[scope.barcount]);
		  var btnDiv = "\'btnDiv"+scope.barcount+"\'";
		  var addemp = 'ng-click="add_miniwidget(' + scope.barcount + ',2);fixme('+String(btnDiv)+')"';
		  var addadmin = 'ng-click="add_miniwidget(' + scope.barcount + ',3);fixme('+String(btnDiv)+')"';
		  var addvehicle = 'ng-click="add_miniwidget(' + scope.barcount + ',4);fixme('+String(btnDiv)+')"';
		  var addgroup = 'ng-click="add_miniwidget(' + scope.barcount + ',5);fixme('+String(btnDiv)+')"';
		  var btx = '<div id='+String(btnDiv)+'>'+
    	  '<button  class="btn btn-info dropdown-toggle" ng-if="widgetlimit['+scope.barcount+'] > 0" ng-disabled="buttonDisabled" ng-click="fixme('+String(btnDiv)+')">'+
          '<i class="material-icons  md-inactive md-dark">add_circle</i></button>'+
      '<ul class="dropdown-menu">'+
          '<li class="dropdown-header">Add mini widget</li>'+
          '<li><a '+addemp+'>Employee</a></li>'+
          '<li><a '+addadmin+'>Administrator</a></li>'+
          '<li><a '+addvehicle+'>Vehicle</a></li>'+
          '<li><a '+addgroup+'>Groups</a></li>'+
          '</ul>'+
      '</div>';
		  
		  /*Attach scope to newly added div and always compile div tobe append prior to append
		  as to avoid attaching scope multiple times as it may propagate click event multiple
		  times.*/
		  div = $compile(angular.element('<div id="minibar' + scope.barcount + '" class="fixrow dropdown">' + String( btx)+ '</div>'))(scope); 
		  angular.element(document.getElementById("dashboard_container")).append(div);
		  scope.barcount++;
      };
      
      scope.add_miniwidget = function(id,type){
    	  console.log("Minidiv ID:"+id+" Type: "+type);
    	  angular.forEach(scope.widgetlimit, function(value, key) {
    		  if(key == id && scope.widgetlimit[key]>0 ){
    			  console.log(scope.widgetlimit[key]);
    			  var div2 = angular.element(document.getElementById("minibar"+id));
    			  if(type==2){
    				  var gg = angular.element(employees);
    				  $compile(gg)(scope);
    				  div2.append(gg);
    			  }
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
    			  }
    			  scope.widgetlimit[key]--;
    		  }
    		});
      }
      
    },
   // templateUrl: 'a_modalDialog',
  };
}]);