(function (ng) {
    var mod = ng.module("cuentaModule");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('cuentaDeleteCtrl', ['$scope', '$http', 'cuentaContext', '$state',
        function ($scope, $http, cuentaContext, $state) {
            var idcue = $state.params.cuentaId;
            $scope.deleteCuenta = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/cuentas" + '/' + idcue, {}).then(function (response) {
                    $state.go('cuentaList', {cuentaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);