'use strict'
app.directive('displayWidget',['$compile','$timeout','chartService', function($compile,$timeout,chartService) {
	var widget_map = {
			widgetEmployee		: '<widget-employee></widget-employee>',
			widgetAdministrator	: '<widget-administrator></widget-administrator>',
			widgetGroup			: '<widget-group></widget-group>',
			widgetVehicle		: '<widget-vehicle></widget-vehicle>',
			miniChart 			: '<widget-minichart></widget-minichart>',
			
	};
	var nm = '<div style="min-height: 20px;max-width: 200px;background-color: yellow;">{{data.widget_order}}</div>';
  return {
	  /*require: "ngModel",*/
	  restrict: 'A',
	  replace: false,
	  template: '',
    scope: {
    	data: '=',
    },
    transclude: false, // Insert custom content inside the directive
    link: function(scope, element, attrs) {
    	
    	
    	var widget_list = ["widgetEmployee","widgetAdministrator","widgetGroup","widgetVehicle"];
    	
    	angular.forEach(widget_map,function(value,key){
    		if(key === scope.data.widget_type){
    		console.log("success!! for: "+key);
    		var temp = '<div style="min-height: 20px;background-color: yellow;">{{data.widget_order}}</div>'
    		var div = $compile(value)(scope);
    		element.append(div);
    		return;
    		}
    	});
    },
  };
}]);

