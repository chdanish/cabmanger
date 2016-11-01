'use strict'
app.controller('a_home', function( $scope,chartfactory,chartService) {
	
	
	 $scope.options = {
	            chart: {
	                type: 'lineChart',
	                height: 450,
	                margin : {
	                    top: 20,
	                    right: 20,
	                    bottom: 40,
	                    left: 55
	                },
	                x: function(d){ return d.x; },
	                y: function(d){ return d.y; },
	                useInteractiveGuideline: true,
	                dispatch: {
	                    stateChange: function(e){ console.log("stateChange"); },
	                    changeState: function(e){ console.log("changeState"); },
	                    tooltipShow: function(e){ console.log("tooltipShow"); },
	                    tooltipHide: function(e){ console.log("tooltipHide"); }
	                },
	                xAxis: {
	                    axisLabel: 'Time (ms)'
	                },
	                yAxis: {
	                    axisLabel: 'Voltage (v)',
	                    tickFormat: function(d){
	                        return d3.format('.02f')(d);
	                    },
	                    axisLabelDistance: -10
	                },
	                callback: function(chart){
	                    console.log("!!! lineChart callback !!!");
	                }
	            },
	            title: {
	                enable: true,
	                text: 'Title for Line Chart'
	            },
	            subtitle: {
	                enable: true,
	                text: 'Subtitle for simple line chart. Lorem ipsum dolor sit amet, at eam blandit sadipscing, vim adhuc sanctus disputando ex, cu usu affert alienum urbanitas.',
	                css: {
	                    'text-align': 'center',
	                    'margin': '10px 13px 0px 7px'
	                }
	            },
	            caption: {
	                enable: true,
	                html: '<b>Figure 1.</b> Lorem ipsum dolor sit amet, at eam blandit sadipscing, <span style="text-decoration: underline;">vim adhuc sanctus disputando ex</span>, cu usu affert alienum urbanitas. <i>Cum in purto erat, mea ne nominavi persecuti reformidans.</i> Docendi blandit abhorreant ea has, minim tantas alterum pro eu. <span style="color: darkred;">Exerci graeci ad vix, elit tacimates ea duo</span>. Id mel eruditi fuisset. Stet vidit patrioque in pro, eum ex veri verterem abhorreant, id unum oportere intellegam nec<sup>[1, <a href="https://github.com/krispo/angular-nvd3" target="_blank">2</a>, 3]</sup>.',
	                css: {
	                    'text-align': 'justify',
	                    'margin': '10px 13px 0px 7px'
	                }
	            }
	        };
	 sinAndCos();
	        $scope.datam = chartService.getchart;

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
	  
	$scope.add_widget = function(id,type){
  	  console.log("ID:"+id+" Type: "+type);
    }
	
	
	$scope.$watch("countmini", function(newVal) {
        console.log("countmini: "+newVal);
    });
	});

app.factory('home_factory', function($http,cacheService) {

    var userId,createdAT,userName,password,email,firstName,lastName,enabled,
    nationalID,nationalID_expiry,licenseID,licenseID_expiry;
    
    var factory = {}; 

    factory.list = [];

    factory.add = function(user) {
    	console.log(user.userId);
	    	if(user.snap==null){
	    		cacheService.get("static/images/avtar.txt").then(function(data){
	    			user.snap=data;
	    		});
	    	}
    	factory.list[user.userId]=user;
    	//console.log(factory.list);
        }
    
    factory.getlist = function() {
            return factory.list;
        }
    factory.updatesnap = function(id,snap) {
    	factory.list[id].snap=snap;
    	console.log(factory.list[id]);
        return factory.list;
    }
    factory.delete = function(id) {
    	var newlist=[];
    	for(var i=0;i<factory.list.length;i++){
    		if(factory.list[i] && factory.list[i].userId!=id){
    			newlist[factory.list[i].userId]=factory.list[i];
    		}
    	}
    	delete factory.list;
    	factory.list=newlist;
    	console.log(factory.list);
        return factory.list;
    }

    factory.getuser =function(id) {
        return factory.list[id];
    }
 

    return factory;
});

app.factory('chartfactory', function() {
	  
    var values,key,color,classed,area,strokeWidth;

	function datum(values,key,color,area,classed,strokeWidth){
		this.values=values;
		this.key=key;
		this.color=color;
		this.area=area;
		this.classed=classed;
		this.strokeWidth=strokeWidth;
	}
	
	
	return {
		datum:datum,
	}
});


