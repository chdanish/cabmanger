"use strict";
app.service('chartService',function() {
	
	var options = {
			chart: {
                type: 'lineChart',
                //height:'',
                updatewidth:function(w){this.width=w;},
                updateheight:function(h){this.height=h;},
                margin : {
                    top: 40,
                    right: 20,
                    bottom: 40,
                    left: 55
                },
                x: function(d){ return d.x; },
                y: function(d){ return d.y; },
                useInteractiveGuideline: true,
                xAxis: {
                    axisLabel: 'Time (ms)',
                    axisLabelDistance: -5
                },
                yAxis: {
                    axisLabel: 'Voltage (v)',
                    tickFormat: function(d){
                        return d3.format('.02f')(d);
                    },
                    axisLabelDistance: -10
                }, 
                showLegend: false
            }
        };
	
	 var chartService = []; // 1
	 
	 var getchart = function(data){
		 return chartService;
	 }
	 
	 var addtochart = function(data){
		 chartService.push(data);
	 }
	 
	/* var api = {};
	 var update = function(height,width){
		// console.log("Height: "+height+"width: "+width);
		 height= parseInt(height)-120;
		 width= parseInt(width)-10;
		 options.chart.height= parseInt(height);
		 options.chart.width= parseInt(width);
	 }*/
	  
	  return {
		  //options:options,
		  /*api :	api,
		  update:update,*/
		  getchart  :getchart,
		  addtochart:addtochart,
	  };
	});
