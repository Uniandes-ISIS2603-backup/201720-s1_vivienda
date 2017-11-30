(function (ng) {
    var mod = ng.module("ordenPagoModule");
    mod.constant("ordenPagoContext", "api/ordenPagos");
    mod.controller('ordenPagoCtrl', ['$scope', '$http', 'ordenPagoContext', '$state',
        function ($scope, $http, ordenPagoContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/ordenPagos").then(function (response) {
                $state.params.ordenPagoId = null;
                $scope.ordenPagosRecords = response.data;
            });

            if (($state.params.ordenPagoId !== undefined) && ($state.params.ordenPagoId !== null)) {
                
                $http.get("http://localhost:8080/vivienda-web/api/ordenPagos" + '/' + $state.params.ordenPagoId).then(function (response) {
                    $scope.currentOrdenPago = response.data;
                });
            }
            
               $scope.buscarOrden = function () {
                $http.get("http://localhost:8080/vivienda-web/api/ordenPagos" + '/' + $scope.ordenBuscarId).then(function (response) {
                    $scope.currentOrdenPago = response.data;
                    $state.go('ordenPagoDetail', {ordenPagoId: $scope.ordenBuscarId}, {reload: true});
                }, function()
                {
                    $state.go('ordenError', {ordenBuscarId: null}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);