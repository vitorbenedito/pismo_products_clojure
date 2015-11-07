angular.module('ProductApp.controllers', []);

angular.module('ProductApp', [
  'ProductApp.controllers',
  'ngRoute',
  'ngCookies'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
  when("/products", {templateUrl: "templates/products.html", controller: "ProductController", method:"getAll"}).
  when("/products/new", {templateUrl: "templates/create.html", controller: "ProductController"}).
  when('/products/edit/:id', {templateUrl: 'templates/create.html', controller: "ProductController", method:"edit"}).
  otherwise({redirectTo: '/products'});
}]);