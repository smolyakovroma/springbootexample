var app = angular.module("myApp", []);

app.controller("appCtrl", function($scope, $http){

        $scope.websites = [];
        $http.get('http://localhost:8080/api/stackoverflow').success(function(data){
           $scope.websites = data;
        });
});