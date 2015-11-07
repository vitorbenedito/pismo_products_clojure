angular.module('ProductApp.controllers', [])
.controller('ProductController', function ($scope,$http,$location,$route,$routeParams) {

	var route = "/api/products";

	$scope.init = function(){

		if ($route.current.method !== undefined) {
       		$scope[$route.current.method]();
     	}

	};

    $scope.getAll = function(){

        $scope.data = $http.get(route).success(function(response){
            $scope.products = response;
        });
    };

    $scope.createProduct = function(){
    	$scope.product = {};
    	$location.path('/products/new');
    };

    $scope.editProduct = function(product){
    	$location.path('/products/edit/' + product._id);
    };

    $scope.back = function(){
    	$location.path('/products');
    };

    $scope.clear = function(){
    	$scope.product = {};
    };

    $scope.remove = function(product){
    	$http.delete(route + "/" + product._id).
	    success(function(data) {
	   	  $scope.getAll();
	    }).
	    error(function(data, status) {
	        
	    });
    };

    $scope.edit  = function(){
    	$http.get(route + "/" + $routeParams.id).
	    success(function(data) {
	   	  $scope.product = data[0];
	    }).
	    error(function(data, status) {
	        
	    });
    };

    $scope.save = function(){
    	var routeSave = route;
    	var method = "post";
    	if($scope.product._id != null){
    		routeSave = routeSave + "/" + $scope.product._id;
    		method = "put";
    	}
	    $http[method](routeSave, JSON.stringify($scope.product)).
	    success(function(data) {
	      $scope.back();
	    }).
	    error(function(data, status) {
	        
	    });
    };

    $scope.init();

  });