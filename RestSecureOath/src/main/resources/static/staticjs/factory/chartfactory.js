'use strict'
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