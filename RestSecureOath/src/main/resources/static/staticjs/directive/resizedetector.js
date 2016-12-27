'use strict'
app.directive('resizeDetector',['$compile','$timeout','$window','chartService','$interval',
	function($compile,$timeout,$interval,$window,chartService) {

  return { // Insert custom content inside the directive
    link: function(scope, element, attrs) {
    	//Width change watcher
    	scope.$watch(function(){
			if(element[0].style.width){
				return element[0].style.width;
			}else return 0;
		}, function(val){
	         // console.log("width: "+val);
	          var data = { 
	        		  resizeid:attrs.resizeid,
	        		  width: element[0].style.width,
	        		  height:element[0].style.height
	          		};
	          scope.$broadcast('resize',data);
		});
    	
    	//Height change watcher
    	scope.$watch(function(){
			if(element[0].style.height){
				return element[0].style.width;
			}else return 0;
		}, function(val){
	         // console.log("height: "+val);
	          var data = { 
	        		  resizeid:attrs.resizeid,
	        		  width: element[0].style.width,
	        		  height:element[0].style.height
	          		};
	          scope.$broadcast('resize',data);
		});
		
    },
  };
}]);

