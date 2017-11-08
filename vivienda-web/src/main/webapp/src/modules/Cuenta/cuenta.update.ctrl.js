(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("estudianteContext", "api/estudiantes");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('cuentaUpdateCtrl', ['$scope', '$http', 'estudianteContext', '$state', 'cuentaContext', '$rootScope', '$filter',
        function ($scope, $http, estudianteContext, $state, cuentaContext, $rootScope, $filter) {
            $rootScope.edit = true;
            var idCuenta = $state.params.cuentaId;
            // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
            $scope.allEstudiantesShow = [];

            //Consulto la tarjeta a editar.
            $http.get(cuentaContext + '/' + idCuenta).then(function (response) {
                var cuenta = response.data;
                $scope.id = cuenta.id;
                $scope.renta = cuenta.renta;
                $scope.getEstudiante();
                $scope.estudianteCuenta = cuenta.estudiante;
                $scope.currentEstudiante = cuenta.estudiante.documento;
                $scope.estu = cuenta.estudiante;
            });


            $scope.getEstudiante = function () {
                $http.get(estudianteContext).then(function (response) {
                    $scope.allEstudiantesShow = response.data;
                });

            };

            $scope.createCuenta = function () {
                //---------------------------------------------------------------
                var e = document.getElementById("estudianteSelect");
                var strUser = e.options[e.selectedIndex].value;
                if (strUser !== "No cambiar" && strUser !== "Eliminar estudiante") {
                    //Ya tengo el id de la cuenta, ahora la busco
                    $http.get(estudianteContext + '/' + strUser).then(function (response) {
                        $scope.estudianteCuenta = {};
                        $scope.estudianteCuenta = response.data;
                        //-----------------------------------------------------------
                        $http.put(cuentaContext + "/" + idCuenta, {
                            id: $scope.id,
                            renta: $scope.renta
                        }).then(function (response) {

                            if ($scope.estudianteCuenta.documento !== undefined && $scope.estudianteCuenta.documento !== null) {
                                var newc = {documento: $scope.estudianteCuenta.documento, nombre: $scope.estudianteCuenta.nombre,
                                    passWord: $scope.estudianteCuenta.passWord, userName: $scope.estudianteCuenta.userName};

                                $http.put(cuentaContext + "/" + idCuenta, {
                                    id: $scope.id,
                                    renta: $scope.renta,
                                    estudiante: newc
                                }).then(function (response) {
                                });
                            }
                            //Author created successfully
                            $state.go('cuentaList', {cuentaId: response.data.id}, {reload: true});
                        });

                        //-----------------------------------------------------------
                    });
                } else if (strUser === "No cambiar")
                {
                    //-------------------------------------------------------
                    if ($scope.estu !== null && $scope.estu !== undefined) {
                        var newc = {documento: $scope.estu.documento, nombre: $scope.estu.nombre,
                            passWord: $scope.estu.passWord, userName: $scope.estu.userName};
                        $http.put(cuentaContext + "/" + idCuenta, {
                            id: $scope.id,
                            renta: $scope.renta,
                            estudiante: newc
                        }).then(function (response) {
                            //Author created successfully
                            $state.go('cuentaList', {cuentaId: response.data.id}, {reload: true});
                        });
                        //-------------------------------------------------------
                    } else {
                        $http.put(cuentaContext + "/" + idCuenta, {
                            id: $scope.id,
                            renta: $scope.renta
                        }).then(function (response) {
                            //Author created successfully
                            $state.go('cuentaList', {cuentaId: response.data.id}, {reload: true});
                        });
                    }
                } else {
                    //-------------------------------------------------------
                    $http.put(cuentaContext + "/" + idCuenta, {
                        id: $scope.id,
                        renta: $scope.renta
                    }).then(function (response) {
                        //Author created successfully
                        $state.go('cuentaList', {cuentaId: response.data.id}, {reload: true});
                    });
                    //-------------------------------------------------------

                }
                //---------------------------------------------------------------



            };
        }
    ]);
}
)(angular);