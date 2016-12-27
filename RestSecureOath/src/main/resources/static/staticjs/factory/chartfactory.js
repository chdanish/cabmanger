'use strict'
app.factory('chartfactory', function() {
	  
    var values,key,color,classed,area,strokeWidth;
    

    
    function options(){
    	console.log("init chart");
    	this.chart={};
    	//console.log(this.options);
    	this.chart.type	= 'lineChart';
    	this.chart.margin = {
			            top: 10,
			            right: 20,
			            bottom: 40,
			            left: 55
			        	};
    	this.chart.x		= function(d){ return d.x; };
    	this.chart.y		= function(d){ return d.y; };
    	this.chart.useInteractiveGuideline= true;
    	this.chart.xAxis = {
			            axisLabel: 'Time (ms)',
			            axisLabelDistance: -5
			        };
    	this.chart.yAxis= {
			            axisLabel: 'Voltage (v)',
			            tickFormat: function(d){
			                return d3.format('.02f')(d);
			            },
			            axisLabelDistance: -10
			        };
    	this.chart.showLegend= true;
    	
    	return this;
    }

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
		options:options,
	}
});