'use strict'
app.directive('modalDialog', function() {
  return {
	  /*require: "ngModel",*/
	  restrict: 'E',
    scope: {
      show: '='
    },
    transclude: true, // Insert custom content inside the directive
    link: function(scope, element, attrs) {
    	var block=element[0].querySelector('#close-modal');
        
        angular.element(block).bind('click', function() {
  		console.log("enter");
      });
      scope.dialogStyle = {};
     
      if (attrs.boxWidth) {
        scope.dialogStyle.width = attrs.boxWidth;
      }
      if (attrs.boxHeight) {
        scope.dialogStyle.height = attrs.boxHeight;
      }
      scope.hideModal = function() {
    	  console.log("modal");
        scope.show = false;
      };
    },
    templateUrl: 'a_modalDialog',
  };
});