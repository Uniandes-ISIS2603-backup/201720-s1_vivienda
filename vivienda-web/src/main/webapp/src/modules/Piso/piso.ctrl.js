(function(ng){
    var mod = ng.module("pisoModule");
    
    mod.constant("pisoContext");
    mod.controller('pisoCtrl',['$scope','$http','pisoContext', '$stateParams',
        function($scope, $http, pisoContext, $stateParams){
        	var id = $stateParams.torreId;
            $http.get("http://localhost:8080/vivienda-web/api/torres/" + id + "/pisos").then(function(response){
                $scope.pisosRecords = response.data;
            });
        }
    ]);
})(angular); 
