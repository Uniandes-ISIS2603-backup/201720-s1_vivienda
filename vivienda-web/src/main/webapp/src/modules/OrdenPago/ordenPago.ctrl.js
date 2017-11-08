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
                console.log($state.params.ordenPagoId);
                $http.get("http://localhost:8080/vivienda-web/api/ordenPagos" + '/' + $state.params.ordenPagoId).then(function (response) {
                    $scope.currentOrdenPago = response.data;
                });
            }
        }
    ]);
}
)(angular);