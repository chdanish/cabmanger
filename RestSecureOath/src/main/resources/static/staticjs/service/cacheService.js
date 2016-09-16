"use strict";
app.service('cacheService',function($http, $cacheFactory, $q) {
	  var cache = $cacheFactory('resourceCache', {capacity: 100}); // 1
	  var getreq =  function(url) {
	      if (cache.get(url)) {
		        return cache.get(url); // 2
		      }

		      var promise = $http.get(url).then(
		        function(response) {
		          cache.put(url, $q.when(response.data)); // 4
		          return response.data;
		        }
		      );
		      cache.put(url, promise); // 3
		      return promise;
	  }
	  var postreq =  function(url,data) {
	           var promise = $http.post(url,data).then(
		        function(response) {
		          cache.put(url, $q.when(response.data)); // 4
		          return response.data;
		        }
		      );
		      cache.put(url, promise); // 3
		      return promise;
	  }
	  var remove = function(url){
		  cache.remove(url);
		  console.log("Cache cleared for url: "+url);
	  }
	  
	  var update = function(url){
		  cache.remove(url);
		  console.log("Cache cleared for url: "+url);
		  return getreq(url);
	  }
	  
	  var fileupload = function(url,file) {
		  var fd = new FormData();
	      fd.append('file', file);
	      var config = {transformRequest: angular.identity,
	              		headers: {'Content-Type': undefined}
	          			};
          var promise = $http.post(url,fd,config).then(
  		        function(response) {
  		          cache.put(url, $q.when(response.data)); // 4
  		        return response.data;
  		        }
  		      );
  		      cache.put(url, promise); // http://jsfiddle.net/JeJenny/ZG9re/
  		      return promise;
  	  }
	  
	  return {
	    get			:getreq,
	    post		:postreq,
	    remove		:remove,
	    update		:update,
	    fileupload	:fileupload,
	  };
	});
