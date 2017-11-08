(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('tarjetaUpdateCtrl', ['$scope', '$http', 'tarjetaContext', '$state', 'cuentaContext', '$rootScope', '$filter',
        function ($scope, $http, tarjetaContext, $state, cuentaContext, $rootScope, $filter) {
            $rootScope.edit = true;
            var idTarjeta = $state.params.tarjetaId;
            // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
            $scope.allCuentasShow = [];



            //Consulto la tarjeta a editar.
            $http.get(tarjetaContext + '/' + idTarjeta).then(function (response) {
                var tarjeta = response.data;
                $scope.nombreTarjeta = tarjeta.nombre;
                $scope.numeroTarjeta = tarjeta.numeroTarjeta;
                $scope.getCuenta();
                $scope.cuentaTarjeta = tarjeta.cuenta;
                $scope.currentCuenta = tarjeta.cuenta.id;
                $scope.cuent = tarjeta.cuenta;


            });
            /*
             * Esta función recibe como param los books que tiene el autor para hacer un filtro visual con todos los books que existen.
             * @param {type} books
             * @returns {undefined}
             */
            $scope.getCuenta = function () {
                $http.get(cuentaContext).then(function (response) {
                    $scope.allCuentasShow = response.data;
                });

            };

            $scope.createTarjeta = function () {

                /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                 en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                 */
                //---------------------------------------------------------------
                var e = document.getElementById("cuentaSelect");
                var strUser = e.options[e.selectedIndex].value;
                if (strUser !== "No cambiar" && strUser !== "Eliminar cuenta") {
                    //Ya tengo el id de la cuenta, ahora la busco
                    $http.get(cuentaContext + '/' + strUser).then(function (response) {
                        $scope.cuentaTarjeta = {};
                        $scope.cuentaTarjeta = response.data;
                        //-----------------------------------------------------------
                        $http.put(tarjetaContext + "/" + idTarjeta, {
                            nombre: $scope.nombreTarjeta,
                            numeroTarjeta: $scope.numeroTarjeta
                        }).then(function (response) {

                            if ($scope.cuentaTarjeta.id !== undefined && $scope.cuentaTarjeta.id !== null) {
                                var newc = {id: $scope.cuentaTarjeta.id, renta: $scope.cuentaTarjeta.renta};

                                $http.put(tarjetaContext + "/" + idTarjeta, {
                                    nombre: $scope.nombreTarjeta,
                                    numeroTarjeta: idTarjeta,
                                    cuenta: newc
                                }).then(function (response) {
                                });
                            }
                            //Author created successfully
                            $state.go('tarjetaList', {tarjetaId: response.data.numeroTarjeta}, {reload: true});
                        });

                        //-----------------------------------------------------------
                    });
                } else if (strUser === "No cambiar")
                {
                    //-------------------------------------------------------
                    if ($scope.cuent !== null && $scope.cuent !== undefined) {
                        var newc = {id: $scope.cuent.id, renta: $scope.cuent.renta};
                        $http.put(tarjetaContext + "/" + idTarjeta, {
                            nombre: $scope.nombreTarjeta,
                            numeroTarjeta: $scope.numeroTarjeta,
                            cuenta: newc
                        }).then(function (response) {
                            //Author created successfully
                            $state.go('tarjetaList', {tarjetaId: response.data.numeroTarjeta}, {reload: true});
                        });
                        //-------------------------------------------------------
                    } else {
                        $http.put(tarjetaContext + "/" + idTarjeta, {
                            nombre: $scope.nombreTarjeta,
                            numeroTarjeta: $scope.numeroTarjeta
                        }).then(function (response) {
                            //Author created successfully
                            $state.go('tarjetaList', {tarjetaId: response.data.numeroTarjeta}, {reload: true});
                        });
                    }
                } else {
                    //-------------------------------------------------------
                    $http.put(tarjetaContext + "/" + idTarjeta, {
                        nombre: $scope.nombreTarjeta,
                        numeroTarjeta: $scope.numeroTarjeta
                    }).then(function (response) {
                        //Author created successfully
                        $state.go('tarjetaList', {tarjetaId: response.data.numeroTarjeta}, {reload: true});
                    });
                    //-------------------------------------------------------

                }
                //---------------------------------------------------------------

            };
        }
    ]);
}
)(angular);