'use strict'
app.directive('widgetEmployee',['$compile','$timeout','cacheService', function($compile,$timeout,cacheService) {
	
	var employees = /*'<div class="container col-xs-2 seperation">'+
    '<div class="row borderwigx roundc">'+
    '<div class="panel panel-default  roundc">'+
        '<div class="panel-heading  roundc">'+
             '<h4>'+
        '{{widgettitle}}'+
      '</h4>'+

        '</div>'+*/
        '<table class="table table-fixed col-xs-2 topmagin ">'+
            '<thead >'+
                '<!-- <tr>'+
                    '<th class="col-xs-1">Employees</th>'+
                    '<th class="col-xs-1">Count</th>'+
                '</tr> -->'+
            '</thead>'+
            '<tbody>'+
                '<tr>'+
                    '<td class="col-xs-1">Total</td>'+
                    '<td class="col-xs-1">{{total}}</td>'+
                '</tr>'+
                '<tr>'+
                    '<td class="col-xs-1">Active</td>'+
                    '<td class="col-xs-1">{{active}}</td>'+
                '</tr>'+
                '<tr>'+
                    '<td class="col-xs-1">Blocked</td>'+
                    '<td class="col-xs-1">{{blocked}}</td>'+
                '</tr> '+                   
            '</tbody>'+
        '</table>'
   /* +'</div>'+
'</div>'+
'</div>'*/ ;
	
  return {
	  /*require: "ngModel",*/
	  restrict: 'E',
	  replace: true,
	  template: '<div></div>',
    scope: {
    	type: '=',
    	barcount: '=',
    	maxbar: '=',
    	maxwidgets: '=',
    },
    transclude: false, // Insert custom content inside the directive
    link: function(scope, element, attrs) {
    	 scope.widgettitle="Employees";
    	 element.append(employees);
         $compile(element.contents())(scope);   
         cacheService.update('/a_miniwidget/employee').then(function(data){
     		console.log(data);
     		scope.total=data.total[0].employees-1;// subtracting owner from employee count
     		scope.active=data.active[0].active-1;// subtracting owner from employee count
     		scope.blocked= scope.total-scope.active;
     	 });
         
      
    },
   // templateUrl: 'a_modalDialog',
  };
}]);