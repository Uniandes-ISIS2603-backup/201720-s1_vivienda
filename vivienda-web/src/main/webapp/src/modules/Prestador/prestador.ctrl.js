(function (ng) {
    var mod = ng.module("prestadorModule");
    mod.constant("prestadorContext", "api/prestadores");
    mod.controller('prestadorCtrl', ['$scope', '$http', 'prestadorContext', '$state',
        function ($scope, $http, prestadorContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/prestadores").then(function (response) {
                $state.params.prestadorId = null;
                $scope.prestadoresRecords = response.data;
            });
            
            $http.get("http://localhost:8080/vivienda-web/api/servicios").then(function (response) {
                $scope.serviciosRecords = response.data;
            });
            
           
            
            if (($state.params.prestadorId !== undefined)&& ($state.params.prestadorId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/prestadores" + '/' + $state.params.prestadorId).then(function (response) {
                    $scope.currentPrestador = response.data;
                });
            }
        }
    ]);
}
)(angular);