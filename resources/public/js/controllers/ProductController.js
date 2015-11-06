angular.module('ProductApp.controllers', [])
.controller('ProductController', function ($scope,$http) {

	var route = "/api/products";

    $scope.getAll = function(){

        $scope.data = $http.get(route).success(function(response){
            $scope.products = response.products;
        });
    };

    $scope.getAll();

  });