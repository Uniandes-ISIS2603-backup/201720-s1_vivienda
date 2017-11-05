(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('tarjetaUpdateCtrl', ['$scope', '$http', 'tarjetaContext', '$state', 'cuentaContext', '$rootScope', '$filter',
        function ($scope, $http, tarjetaContext, $state, cuentaContext, $rootScope, $filter) {
            $rootScope.edit = true;

            var idTarjeta = $state.params.tarjetaId;

            // Este arreglo guardara los ids de las cuentas asociadas y por asociar a la tarjeta.
            var idCuenta = [];
            // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
            $scope.allCuentasShow = [];
            
            
            
            //Consulto la tarjeta a editar.
            $http.get(tarjetaContext + '/' + idTarjeta).then(function (response) {
                var tarjeta = response.data;
                $scope.nombreTarjeta = tarjeta.nombre;
                $scope.numeroTarjeta = tarjeta.numeroTarjeta;
                $scope.cuentaTarjeta = tarjeta.cuenta;
                $scope.mergeCuenta($scope.cuentaTarjeta);                
            });

            /*
             * Esta función añade los ids de los books que ya tiene el autor asociado.
             * @param {type} books: Son los books que ya tiene asociado el autor.
             * @returns {undefined}
             */
            $scope.mergeCuenta = function (cuenta) {
                for (var item in cuenta) {
                    if (item == "id") {
                        idCuenta.push("" + cuenta[item]);
                    }
                    else{
                        
                    }
                }
                $scope.getCuenta(cuenta);
               
            };

            /*
             * Esta función recibe como param los books que tiene el autor para hacer un filtro visual con todos los books que existen.
             * @param {type} books
             * @returns {undefined}
             */
            $scope.getCuenta = function (cuenta) {
                $http.get(cuentaContext).then(function (response) {
                    $scope.allCuentas = response.data;
                    $scope.nuevaCuenta = cuenta;

                    //Cuentas no asociadas 
                    var filteredCuentas = [];
                    for (i = 0; i < $scope.allCuentas.length; i++) {
                        var cu = $scope.allCuentas[i];
                        if(cu.id == $scope.nuevaCuenta.id){
                        }
                        else{
                            filteredCuentas.push(cu);
                        }
                    }
       
                    $scope.allCuentasShow = filteredCuentas;
                  
                });
            };

            //funciones para el drag and drop de HTML5 nativo
            $scope.allowDrop = function (ev) {
                ev.preventDefault();
            };

            $scope.drag = function (ev) {
                ev.dataTransfer.setData("text", ev.target.id);
            };

            $scope.dropAdd = function (ev) {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("text");
                ev.target.appendChild(document.getElementById(data));
                //Cuando un book se añade al autor, se almacena su id en el array idsBook
                idCuenta.push("" + data);
            };

            $scope.dropDelete = function (ev) {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("text");
                ev.target.appendChild(document.getElementById(data));
                //Para remover el book que no se va asociar, por eso se usa el splice que quita el id del book en el array idsBook
                var index = idCuenta.indexOf(data);
                if (index > -1) {
                    idCuenta.splice(index, 1);
                }
            };

            $scope.createTarjeta = function () {
                /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                 en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                 */
                $scope.newCuentas();
                $http.put(tarjetaContext + "/" + idTarjeta, {
                    nombre: $scope.nombreTarjeta,
                    numeroTarjeta: $scope.numeroTarjeta
                }).then(function (response) {
                    if (idCuenta.length >= 0) {
                        $http.put(tarjetaContext + "/" + response.data.numeroTarjeta, $scope.cuentaTarjeta).then(function (response) {
                        });
                    }
                    //Author created successfully
                    $state.go('tarjetaList', {tarjetaId: response.data.numeroTarjeta}, {reload: true});
                });
            };

            $scope.newCuentas = function () {
                
               $scope.cuentaTarjeta = [];
               console.log(idCuenta);
                for (var ite in idCuenta) {
                    for (var all in $scope.allCuentas) {
                        if ($scope.allCuentas[all].id === parseInt(idCuenta[ite])) {
                            $scope.cuentaTarjeta.push($scope.allCuentas[all]);
                        }
                    }
                }
            };
        }
    ]);
}
)(angular);