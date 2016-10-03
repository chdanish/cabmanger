'use strict'
app.filter('datetime', function() {
    return function(input) {
    console.log(input+"XXXXXXXXXXXXXXXXXXXXXXXX");
    var days = ["Sun","Mon","Tue","Wed",
                "Thu","Fri","Sat"]
    if(input){
    	var datetime = new Date(input);
    	var out_date ="Date: "+days[datetime.getDay()]+" "+datetime.getDate()+" "+datetime.getFullYear();
    	var out_time = " Time: "+datetime.getHours()+":"+datetime.getMinutes();
    	return out_date+out_time;
    }
        return input;
      };
    });