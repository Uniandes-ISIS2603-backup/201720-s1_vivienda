(function (ng) {
    var mod = ng.module("cuentaModule");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('cuentaCtrl', ['$scope', '$http', 'cuentaContext', '$state',
        function ($scope, $http, cuentaContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/cuentas").then(function (response) {
                $state.params.cuentaId = null;
                $scope.cuentasRecords = response.data;
            });
             if (($state.params.cuentaId !== undefined)&& ($state.params.cuentaId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/cuentas" + '/' + $state.params.cuentaId).then(function (response) {
                    $scope.currentCuenta = response.data;
                });
            }
        }
    ]);
}
)(angular);