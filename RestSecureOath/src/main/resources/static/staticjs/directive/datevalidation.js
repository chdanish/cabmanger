'use strict'
app.directive('dateValidation',[ function() {
    return {
        require: "ngModel",
        link: function(scope, element, attributes, ngModel) {
        	
        	function isValidDate(date) {
        	    var temp = date.split('/');
        	    if(temp[2]){if(temp[2].length<4){ return false}};
        	    var d = new Date(temp[2] + '/' + temp[0] + '/' + temp[1]);
        	    return (d && (d.getMonth() + 1) == temp[0] && d.getDate() == Number(temp[1]) && d.getFullYear() == Number(temp[2]));
        	}        	
        	
            ngModel.$validators.dateValid = function (dateValue) {
            	console.log(isValidDate(dateValue));
            	//either null or a valid date
            	ngModel.$setValidity('datevalid',isValidDate(dateValue)||dateValue==="");           	
            	return isValidDate(dateValue)||dateValue==="";
            };
            
            scope.$watch(scope[attributes.ngModel], function() {
                ngModel.$validate();
            });
        }
    };
}]);