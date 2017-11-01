(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaCtrl', ['$scope', '$http', 'tarjetaContext', '$state',
        function ($scope, $http, tarjetaContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/tarjetas").then(function (response) {
                $state.params.tarjetaId = null;
                $scope.tarjetasRecords = response.data;
            });
            
             if (($state.params.tarjetaId !== undefined)&& ($state.params.tarjetaId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/tarjetas" + '/' + $state.params.tarjetaId).then(function (response) {
                    $scope.currentTarjeta = response.data;
                });
         }
        }
    ]);
}
)(angular);