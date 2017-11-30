(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('tarjetaNewCtrl', ['$scope', '$http', 'tarjetaContext', 'cuentaContext', '$state',
        function ($scope, $http, tarjetaContext, cuentaContext, $state) {

            $scope.allCuentasShow = [];
            $scope.selected = {};



            $scope.getCuenta = function () {
                $http.get(cuentaContext).then(function (response) {
                    $scope.allCuentasShow = response.data;

                });
            };




            $scope.getCuenta();

            $scope.createTarjeta = function () {
                var e = document.getElementById("cuentaSelect");
                var strUser = e.options[e.selectedIndex].value;

                if (strUser === "No asociar cuenta") {
                    $http.post(tarjetaContext, {
                        nombre: $scope.nombreTarjeta,
                        numeroTarjeta: $scope.numeroTarjeta

                    }).then(function (response) {
                        //Author created successfully
                        $state.go('tarjetaList', {numeroTarjeta: response.data.numeroTarjeta}, {reload: true});
                    });
                } else {
                    if (strUser !== undefined && strUser !== null) {
                        $http.get(cuentaContext + "/" + strUser).then(function (response) {
                            $scope.selected = response.data;
                            var newc = {id: $scope.selected.id, renta: $scope.selected.renta};

                            $http.post(tarjetaContext, {
                                nombre: $scope.nombreTarjeta,
                                numeroTarjeta: $scope.numeroTarjeta,
                                cuenta: newc

                            }).then(function (response) {
                                //Author created successfully
                                $state.go('tarjetaList', {numeroTarjeta: response.data.numeroTarjeta}, {reload: true});
                            });
                        });
                    }

                }
            };

        }
    ]);
}
)(angular);