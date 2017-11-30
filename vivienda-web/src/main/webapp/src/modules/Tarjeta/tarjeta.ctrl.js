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
         
           $scope.buscarTarjeta = function () {
                $http.get("http://localhost:8080/vivienda-web/api/tarjetas" + '/' + $scope.tarjetaBuscarNumero).then(function (response) {
                    $scope.currentTarjeta = response.data;
                    $state.go('tarjetaDetail', {tarjetaId: $scope.tarjetaBuscarNumero}, {reload: true});
                }, function()
                {
                    $state.go('tarjetaError', {tarjetaId: null}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);