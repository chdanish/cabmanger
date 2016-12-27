'use strict'
app.directive('widgetMinichart',[
	'$compile','$timeout',
	'cacheService','chartService','chartfactory',
	function($compile, $timeout, cacheService,chartService,chartfactory) {
var topbar = '<div class="navcontainer"><div class="navbar navbar-default nav-links xnavbar-fixed-top navbarwidget">'+
'<div class="container">'+
'<a class="mini-navbar navbar-brand" href="/">'+
    '<img src="http://i.imgur.com/GAQSCtB.png" width="25"'+
         'alt="Driven Car Sales Logo"'+
         'class="img-rounded logo-nav mini-navbar" />'+
'</a>'+
'<ul class="nav navbar-nav">'+
    '<li class="dropdown">'+
        '<a href="#" class="dropdown-toggle"'+
            'data-toggle="dropdown">'+
            'About Driven<strong class="caret"></strong>'+
        '</a>'+
        '<ul class="dropdown-menu">'+
            
            '<li><a href="#">The Team</a></li>'+
            '<li><a href="#">Our Partners</a></li>'+
            '<li class="dropdown">'+
                '<a href="#" class="dropdown-toggle"'+
                    'data-toggle="dropdown">'+
                    'About Driven<strong class="caret"></strong>'+
                '</a>'+
                '<ul class="dropdown-menu">'+
                    '<li><a href="#">The Team</a></li>'+
                    '<li><a href="#">Our Partners</a></li>'+
                '</ul>'+
            '</li>'+
        '</ul>'+
    '</li>'+
    '<li class="dropdown">'+
        '<a href="#" class="dropdown-toggle"'+
            'data-toggle="dropdown">'+
            'About Driven<strong class="caret"></strong>'+
        '</a>'+
        '<ul class="dropdown-menu">'+
            '<li><a href="#">The Team</a></li>'+
            '<li><a href="#">Our Partners</a></li>'+
            '<li class="dropdown">'+
                '<a href="#" class="dropdown-toggle"'+
                    'data-toggle="dropdown">'+
                    'About Driven<strong class="caret"></strong>'+
                '</a>'+
                '<ul class="dropdown-menu">'+
                    '<li><a href="#">The Team</a></li>'+
                    '<li><a href="#">Our Partners</a></li>'+
                '</ul>'+
            '</li>'+
        '</ul>'+
    '</li>'+
'</ul>'+
'</div>'+
'</div></div>';

		
		return {
			/* require: "ngModel", */
			restrict : 'E',
			replace : true,
			template : '',
			scope : {
				data : '=',
				api: '=?',
			},
			transclude : false, // Insert custom content
								// inside the directive
			link : function(scope, element, attrs) {
				var keyEl = angular.element(topbar);
				
				element.ready(function(){
					console.log("ready");
					console.log(document.getElementById("chartdiv5"));
					element.parent().parent().parent().prepend(keyEl);
					var height = scope.data.chart.api.getElement()[0].parentNode.parentNode.parentNode.parentNode.offsetHeight;
					var width  = scope.data.chart.api.getElement()[0].parentNode.parentNode.parentNode.parentNode.offsetWidth;
					
					$timeout(function(){
						scope.data.chart.options.chart.height = height -100;
						scope.data.chart.options.chart.width = width;
						scope.data.chart.api.update();
						console.log("height "+height+" width "+width);
						
					}, 100, true);
				})
				
				//
				console.log("chartdiv"+ String(scope.data.widgetsid ));
				//console.log(new chartfactory.options());
				var mychart = new chartfactory.options();
				scope.data.chart={
						//options	:chartService.options,
						options : mychart,
						data	:chartService.getchart,
						//api		: '=?',
				};				

			},
		};
	} ]);