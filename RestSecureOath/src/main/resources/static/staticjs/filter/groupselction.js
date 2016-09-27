'use strict'
app.filter('groupselction', function() {
    return function(input) {
    	var list=[];
    	list.push("");
    	for(var i=1;i<input.length;i++){
    		if(input[i]&&input[i].name&&input[i].name!='Owner'){
    			list.push(input[i]);	
    		}
    	}    	
    	console.log(list);
        return list;
      };
    });