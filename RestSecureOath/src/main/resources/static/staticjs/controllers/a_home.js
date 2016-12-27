'use strict'
app.controller('a_home', function( $scope,$window, $timeout,$http,chartfactory,
		chartService,cacheService,home_factory,DataService) {
	
	$scope.oldlist,$scope.newlist;
	
	 		sinAndCos();
	        /*Random Data Generator */
	        function sinAndCos() {
	        	var chartdata=[];
	            var sin = [],sin2 = [],
	                cos = [];

	            //Data is represented as an array of {x,y} pairs.
	            for (var i = 0; i < 100; i++) {
	                sin.push({x: i, y: Math.sin(i/10)});
	                sin2.push({x: i, y: i % 10 == 5 ? null : Math.sin(i/10) *0.25 + 0.5});
	                cos.push({x: i, y: .5 * Math.cos(i/10+ 2) + Math.random() / 10});
	            }
	            
	            chartService.addtochart(new chartfactory.datum( sin, 'Sine Wave','#ff7f0e', true,'dashed',2));
	            chartService.addtochart(new chartfactory.datum( cos, 'Cosine Wave','#2ca02c'));
	            chartService.addtochart(new chartfactory.datum( sin2, 'Another sine wave','#7777ff',false,'dashed', 2));
	            //Line chart data should be sent as an array of series objects.
	           // return chartService.getchart;
	        };

	$scope.countmini=0;
	$scope.countlarge=0;
	
	var dashid= function(){
		var getdash = document.getElementById('userdata').getAttribute("dashid");

	}
	
	
	
	  $scope.gridsterOptions = {
		    rowHeight: '130',
			margins: [5, 5],
			columns: 8,
			mobileModeEnabled: false,
			resizable: {
		     enabled: true,
		     handles: ['n', 'e', 's', 'w', 'ne', 'se', 'sw', 'nw'],
		     
		     // optional callback fired when resize is started
		     start: function(event, $element, widget) {
		    	 $scope.oldlist = angular.copy($scope.dashboard.widgets);
		     },
		     
		     // optional callback fired when item is resized,
		     resize: function(event, $element, widget) {
		    	// if (widget.chart.api) widget.chart.api.update();
		     }, 
		    
		      // optional callback fired when item is finished resizing 
		     stop: function(event, $element, widget) {
		    	 $scope.gridsterOptions.resizable.enabled =false;
		    	 home_factory.onchange($scope.dashboard.widgets,$scope.oldlist)
		    	 	.then(function(data){
		    	 		$scope.dashboard.widgets= data;
		    	 		$scope.gridsterOptions.resizable.enabled =true;
		    	 		},
						function(error){
						  $scope.dashboard.widgets=$scope.oldlist;
						  $scope.gridsterOptions.resizable.enabled =true;
					});
		     } 
		    },
		    draggable: {
		        enabled: true, // whether dragging items is supported
		        handle: 'h3', // optional selector for drag handle
		        start: function(event, $element, widget) {
		        	$scope.oldlist = angular.copy($scope.dashboard.widgets);
		        }, // optional callback fired when drag is started,
		        drag: function(event, $element, widget) {}, // optional callback fired when item is moved,
		        stop: function(event, $element, widget) {
			    	 $scope.gridsterOptions.draggable.enabled =false;
			    	 home_factory.onchange($scope.dashboard.widgets,$scope.oldlist)
			    	 	.then(function(data){
			    	 		$scope.dashboard.widgets= data;
			    	 		$scope.gridsterOptions.draggable.enabled =true;
			    	 		},
							function(error){
							  $scope.dashboard.widgets=$scope.oldlist;
							  $scope.gridsterOptions.draggable.enabled =true;
						});
			     } // optional callback fired when item is finished dragging
		     },
			};
	  $scope.dashboard={
			  widgets:null, 
	  }	;
	  home_factory.getlist().then(function(data){
		  $scope.dashboard.widgets= data;
	  });
		  
	  $scope.addwidget= function(type){
		  home_factory.addwidget(type).then(function(data){
			  $scope.dashboard.widgets= data;
		  });
	  }
	  
	  $scope.deletewidget= function(id){
		  console.log(id);
		  home_factory.delete(id).then(function(data){
			  $scope.dashboard.widgets= data;
		  });
	  }
	 var watcherflag = true;

	 
	  $scope.$watch('dashboard.widgets',function(newitems,olditems){
		 //to do code if require
	} , true);
	  

	});

app.factory('home_factory', function($http,cacheService,$q) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];
    
    factory.getdash = function() {
    	return cacheService.update('/dashboard/getdash').then(function(data){
       		console.log(data.status.widgets);
       		factory.list=data.status.widgets;
       		return factory.list;
       	 });
    
        }
    
    factory.getlist = function() {
    	
    	console.log("length: "+factory.list.length);
    	if (!factory.list.length) {
    		return factory.getdash();
    		}
    	return factory.list;
        }



    factory.addwidget = function(widgettype) {
    	console.log(widgettype);
    	return cacheService.post('/dashboard/addwidget/'+widgettype).then(function(data){
       		console.log(data.status.widgets);
       		factory.list=data.status.widgets;
       		return factory.list;
       	 });
        }
   
    factory.delete = function(id) {
    	console.log(id);
    	return cacheService.post('/dashboard/deletewidget/'+id).then(function(data){
       		console.log(data.status.widgets);
       		factory.list=data.status.widgets;
       		return factory.list;
       	 });       	
    }
    
    factory.onchange = function promiseupdate(array1,array2) {
  	  // perform some asynchronous operation, resolve or reject the promise when appropriate.
  	  return $q(function(resolve, reject) {
  		var arr1 = array1;
    	var arr2 = array2;
    	var locallist  = []; 
    	angular.forEach(arr1, function(value1) {
			angular.forEach(arr2, function(value2) {
				if (value1.widgetsid === value2.widgetsid ) {
					if(value1.col != value2.col || value1.row != value2.row 
							|| value1.sizeX != value2.sizeX || value1.sizeY != value2.sizeY){
						console.log(value1.type);
						locallist[value1.widgetsid] = value1;						
					}
				}
			});
		});
    	if(locallist.length>0){
    		factory.update(locallist).then(function(data){
    			resolve(data);
    		},function(err){reject(false);})
    	} else reject(false);
  	  });
  	}
    
    factory.update = function promiseupdate(obj) {
    	  // perform some asynchronous operation, resolve or reject the promise when appropriate.
    	  return $q(function(resolve, reject) {
    		  cacheService.post('/dashboard/moves',obj).then(function(data){
    			  factory.list=data.status.widgets;
    			  resolve(factory.list);
    	    	},function(error){ console.log(error);
    	    		reject(obj);})
    	  });
    	}


    return factory;
});

