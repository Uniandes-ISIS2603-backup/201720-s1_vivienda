(function (ng) {
    var mod = ng.module("cuentaModule");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('cuentaNewCtrl', ['$scope', '$http', 'cuentaContext', '$state',
        function ($scope, $http, cuentaContext, $state) {
            $scope.createCuenta = function () {
                //-----------------------------------------------------------------
                $http.post(cuentaContext, {
                    id: $scope.id,
                    renta: $scope.renta

                }).then(function (response) {
                    //Author created successfully
                    $state.go('cuentaList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);