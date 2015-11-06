angular.module('ProductApp.controllers', []);

angular.module('ProductApp', [
  'ProductApp.controllers',
  'ngRoute',
  'ngCookies'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.
  when("/products", {templateUrl: "templates/products.html", controller: "ProductController"}).
  otherwise({redirectTo: '/products'});
}]);