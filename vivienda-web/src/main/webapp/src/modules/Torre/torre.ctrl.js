/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    var mod = ng.module("torreModule");
    mod.constant("torresContext","api/torres"); 
    mod.controller('torreCtrl',['$scope','$http','torresContext',
        function($scope, $http, torresContext){
            $http.get("http://localhost:8080/vivienda-web/api/torres").then(function(response){
                $scope.torresRecords = response.data;
            });
      
        }
    ]);
})(angular); 

