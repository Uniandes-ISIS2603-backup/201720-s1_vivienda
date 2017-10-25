(function (ng) {
    var mod = ng.module("cuentaModule");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('cuentaCtrl', ['$scope', '$http', 'cuentaContext',
        function ($scope, $http, cuentaContext) {
            $http.get("http://localhost:8080/vivienda-web/api/cuentas").then(function (response) {
                $scope.cuentasRecords = response.data;
            });
        }
    ]);
}
)(angular);