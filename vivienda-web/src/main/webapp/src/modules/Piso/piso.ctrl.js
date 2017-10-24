(function(ng){
    var mod = ng.module("pisoModule");
    mod.constant("pisoContext","api/torres/1/pisos"); 
    mod.controller('pisoCtrl',['$scope','$http','pisoContext',
        function($scope, $http, pisoContext){
            $http.get("http://localhost:8080/vivienda-web/api/torres/1/pisos").then(function(response){
                $scope.pisosRecords = response.data;
            });
        }
    ]);
})(angular); 
