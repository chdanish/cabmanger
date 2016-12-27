'use strict'
app.directive('displayWidget',['$compile','$timeout','chartService','$interval',
	function($compile,$timeout,$interval,chartService) {

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
    	//console.log(scope.data);
    	var pre ='<div class="testdiv"><div>'
    	var div = $compile(scope.data.tag)(scope);
    	//element.append(pre);
		element.append(div);
		
    },
  };
}]);

