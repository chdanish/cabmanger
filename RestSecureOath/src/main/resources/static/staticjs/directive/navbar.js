'use strict'
app.directive('myNavbar', function() {return{
restrict: 'E',
transclude: true, // Insert custom content inside the directive
template:'<nav class="navbar navbar-default navbar-fixed-top" role="navigation">'+
  '<div class="container-fluid">'+
     '<div class="">'+
        '<ul class="nav navbar-nav navbar-left">'+
            '<li>'+
                '<a sidebar-directive  state="false" class="btn btn-info btn-lg">'+
          '<span class="glyphicon glyphicon-menu-hamburger"></span>'+ 'Menu'+
        '</a>'+
            '</li>'+
            
        '</ul>'+
        '<ul class="nav navbar-nav navbar-right">'+
            '<li>'+
                '<a  class="">'+
          ' Welcome to Uber Cab Manager'+
       '</a>'+
            '</li>'+
            
        '</ul>'+
    '</div>'+

    '<!-- Collect the nav links, forms, and other content for toggling -->'+
    '<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">'+
      
      
      '<ul class="nav navbar-nav navbar-right">'+
         '<li data-match-route="/"><a ></a></li>'+
        '<li data-match-route="/"><a ></a></li>'+
     '</ul>'+
    '</div><!-- /.navbar-collapse -->'+
  '</div><!-- /.container-fluid -->'+
'</nav>',
link: function(scope, element, attrs) {},

};});