(function (ng) {
    var mod = ng.module("ordenPagoModule");
    mod.constant("ordenPagoContext", "api/ordenPagos");
    mod.constant("cuentaContext", "api/cuentas");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('ordenPagoUpdateCtrl', ['$scope', '$http', 'ordenPagoContext', '$state', 'cuentaContext', 'servicioContext', '$rootScope', '$filter',
        function ($scope, $http, ordenPagoContext, $state, cuentaContext, servicioContext, $rootScope, $filter) {
            $rootScope.edit = true;
            var idOrdenPago = $state.params.ordenPagoId;
            // Este arreglo mostrará los books una vez esten filtrados visualmente por lo que el autor ya tiene asociado.
            $scope.allCuentasShow = [];
            $scope.allServiciosShow = [];
            //Consulto la tarjeta a editar.
            $http.get(ordenPagoContext + '/' + idOrdenPago).then(function (response) {
                var ordenPago = response.data;
                $scope.idPago = ordenPago.idPago;
                $scope.pagada = ordenPago.pagada;
                $scope.precio = ordenPago.precio;
                $scope.getCuenta();
                $scope.getServicio();
                $scope.cuentaTarjeta = ordenPago.cuenta;
                $scope.currentCuenta = ordenPago.cuenta.id;
                $scope.cuent = ordenPago.cuenta;
                $scope.servicioOrden = ordenPago.servicio;
                $scope.currentServicio = ordenPago.servicio.nombre;
                $scope.serv = ordenPago.servicio;
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

            $scope.getServicio = function () {
                $http.get(servicioContext).then(function (response) {
                    $scope.allServiciosShow = response.data;
                });
            };

            $scope.createOrdenPago = function () {

                /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                 en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                 */
                //---------------------------------------------------------------
                var e = document.getElementById("cuentaSelect");
                var strUser = e.options[e.selectedIndex].value;
                var e2 = document.getElementById("servicioSelect");
                var strUser2 = e2.options[e2.selectedIndex].value;
                //Si si hay una cuenta entonces haga put
                if (strUser !== "No cambiar" && strUser !== "Eliminar cuenta") {
                    //Ya tengo el id de la cuenta, ahora la busco
                    $http.get(cuentaContext + '/' + strUser).then(function (response) {
                        $scope.cuentaTarjeta = {};
                        $scope.cuentaTarjeta = response.data;
                        //-----------------------------------------------------------
                        $http.put(ordenPagoContext + "/" + idOrdenPago, {
                            idPago: $scope.idPago,
                            pagada: $scope.pagada,
                            precio: $scope.precio
                        }).then(function (response) {
                            if ($scope.cuentaTarjeta.id !== undefined && $scope.cuentaTarjeta.id !== null) {
                                var newc = {id: $scope.cuentaTarjeta.id, renta: $scope.cuentaTarjeta.renta};
                                console.log(newc);
                                $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                    idPago: $scope.idPago,
                                    pagada: $scope.pagada,
                                    precio: $scope.precio,
                                    cuenta: newc
                                }).then(function (response) {

                                    //Servicio----------------------------------------------------------------------------
                                    if (strUser2 !== "No cambiar" && strUser2 !== "Eliminar servicio") {
                                        //Asigno el servicio elegido
                                        $http.get(servicioContext + '/' + strUser2).then(function (response) {
                                            $scope.servicioOrden = {};
                                            $scope.servicioOrden = response.data;
                                            //-----------------------------------------------------------
                                            $http.put(ordenPagoContext + "/" + idOrdenPago, {

                                                idPago: $scope.idPago,
                                                pagada: $scope.pagada,
                                                precio: $scope.precio,
                                                cuenta: newc
                                            }).then(function (response) {
                                                if ($scope.servicioOrden.nombre !== undefined && $scope.servicioOrden.nombre !== null) {
                                                    var news = {nombre: $scope.servicioOrden.nombre, precio: $scope.servicioOrden.precio};
                                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                                        idPago: $scope.idPago,
                                                        pagada: $scope.pagada,
                                                        precio: $scope.precio,
                                                        cuenta: newc,
                                                        servicio: news
                                                    }).then(function (response) {
                                                        $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                                    });
                                                }
                                            });
                                            //-----------------------------------------------------------
                                        });
                                    } else if (strUser2 === "No cambiar") {
                                        //Considero que no haya ninguno o que si hubiera
                                        //-------------------------------------------------------
                                        if ($scope.serv !== null && $scope.serv !== undefined) {
                                            var news = {nombre: $scope.serv.nombre, precio: $scope.serv.precio};
                                            $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                                idPago: $scope.idPago,
                                                pagada: $scope.pagada,
                                                precio: $scope.precio,
                                                cuenta: newc,
                                                servicio: news
                                            }).then(function (response) {
                                                //Author created successfully
                                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                            });

                                            //-------------------------------------------------------
                                        }
                                        //-------------------------------------------------------
                                        else {
                                            $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                                idPago: $scope.idPago,
                                                pagada: $scope.pagada,
                                                precio: $scope.precio,
                                                cuenta: newc
                                            }).then(function (response) {
                                                //Author created successfully
                                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                            });
                                        }
                                        //-------------------------------------------------------
                                    } else if (strUser2 === "Eliminar servicio") {
                                        //Envío el bloque vacío
                                        $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                            idPago: $scope.idPago,
                                            pagada: $scope.pagada,
                                            precio: $scope.precio,
                                            cuenta: newc
                                        }).then(function (response) {
                                            //Author created successfully
                                            $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                        });
                                    } else
                                    {
                                        $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                    }
                                    //------------------------------------------------------------------------------------
                                });
                            }
                            //Author created successfully
                            $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                        });
                        //-----------------------------------------------------------
                    });
                } else if (strUser === "No cambiar")
                {
                    //-------------------------------------------------------
                    if ($scope.cuent !== null && $scope.cuent !== undefined) {
                        var newc = {id: $scope.cuent.id, renta: $scope.cuent.renta};
                        $http.put(ordenPagoContext + "/" + idOrdenPago, {
                            idPago: $scope.idPago,
                            pagada: $scope.pagada,
                            precio: $scope.precio,
                            cuenta: newc
                        }).then(function (response) {
                            //Author created successfully

                            //Servicio----------------------------------------------------------------------------
                            if (strUser2 !== "No cambiar" && strUser2 !== "Eliminar servicio") {
                                //Asigno el servicio elegido
                                $http.get(servicioContext + '/' + strUser2).then(function (response) {
                                    $scope.servicioOrden = {};
                                    $scope.servicioOrden = response.data;
                                    //-----------------------------------------------------------
                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {

                                        idPago: $scope.idPago,
                                        pagada: $scope.pagada,
                                        precio: $scope.precio,
                                        cuenta: newc
                                    }).then(function (response) {
                                        if ($scope.servicioOrden.nombre !== undefined && $scope.servicioOrden.nombre !== null) {
                                            var news = {nombre: $scope.servicioOrden.nombre, precio: $scope.servicioOrden.precio};
                                            $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                                idPago: $scope.idPago,
                                                pagada: $scope.pagada,
                                                precio: $scope.precio,
                                                cuenta: newc,
                                                servicio: news
                                            }).then(function (response) {
                                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                            });
                                        }
                                    });
                                    //-----------------------------------------------------------
                                });
                            } else if (strUser2 === "No cambiar") {
                                //Considero que no haya ninguno o que si hubiera
                                //-------------------------------------------------------
                                if ($scope.serv !== null && $scope.serv !== undefined) {
                                    var news = {nombre: $scope.serv.nombre, precio: $scope.serv.precio};
                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                        idPago: $scope.idPago,
                                        pagada: $scope.pagada,
                                        precio: $scope.precio,
                                        cuenta: newc,
                                        servicio: news
                                    }).then(function (response) {
                                        //Author created successfully
                                        $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                    });

                                    //-------------------------------------------------------
                                }
                                //-------------------------------------------------------
                                else {
                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                        idPago: $scope.idPago,
                                        pagada: $scope.pagada,
                                        precio: $scope.precio,
                                        cuenta: newc
                                    }).then(function (response) {
                                        //Author created successfully
                                        $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                    });
                                }
                                //-------------------------------------------------------
                            } else if (strUser2 === "Eliminar servicio") {
                                //Envío el bloque vacío
                                $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                    idPago: $scope.idPago,
                                    pagada: $scope.pagada,
                                    precio: $scope.precio,
                                    cuenta: newc
                                }).then(function (response) {
                                    //Author created successfully
                                    $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                });
                            } else
                            {
                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                            }
                            //------------------------------------------------------------------------------------
                        });
                        //-------------------------------------------------------
                    }
                    //-------------------------------------------------------
                    else {
                        $http.put(ordenPagoContext + "/" + idOrdenPago, {
                            idPago: $scope.idPago,
                            pagada: $scope.pagada,
                            precio: $scope.precio
                        }).then(function (response) {
                            //Author created successfully
                            //Servicio----------------------------------------------------------------------------
                            if (strUser2 !== "No cambiar" && strUser2 !== "Eliminar servicio") {
                                //Asigno el servicio elegido
                                $http.get(servicioContext + '/' + strUser2).then(function (response) {
                                    $scope.servicioOrden = {};
                                    $scope.servicioOrden = response.data;
                                    //-----------------------------------------------------------
                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {

                                        idPago: $scope.idPago,
                                        pagada: $scope.pagada,
                                        precio: $scope.precio
                                    }).then(function (response) {
                                        if ($scope.servicioOrden.nombre !== undefined && $scope.servicioOrden.nombre !== null) {
                                            var news = {nombre: $scope.servicioOrden.nombre, precio: $scope.servicioOrden.precio};
                                            $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                                idPago: $scope.idPago,
                                                pagada: $scope.pagada,
                                                precio: $scope.precio,
                                                servicio: news
                                            }).then(function (response) {
                                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                            });
                                        }
                                    });
                                    //-----------------------------------------------------------
                                });
                            } else if (strUser2 === "No cambiar") {
                                //Considero que no haya ninguno o que si hubiera
                                //-------------------------------------------------------
                                if ($scope.serv !== null && $scope.serv !== undefined) {
                                    var news = {nombre: $scope.serv.nombre, precio: $scope.serv.precio};
                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                        idPago: $scope.idPago,
                                        pagada: $scope.pagada,
                                        precio: $scope.precio,
                                        servicio: news
                                    }).then(function (response) {
                                        //Author created successfully
                                        $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                    });

                                    //-------------------------------------------------------
                                }
                                //-------------------------------------------------------
                                else {
                                    $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                        idPago: $scope.idPago,
                                        pagada: $scope.pagada,
                                        precio: $scope.precio
                                    }).then(function (response) {
                                        //Author created successfully
                                        $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                    });
                                }
                                //-------------------------------------------------------
                            } else if (strUser2 === "Eliminar servicio") {
                                //Envío el bloque vacío
                                $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                    idPago: $scope.idPago,
                                    pagada: $scope.pagada,
                                    precio: $scope.precio
                                }).then(function (response) {
                                    //Author created successfully
                                    $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                });
                            } else
                            {
                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                            }
                            //------------------------------------------------------------------------------------
                        });
                    }
                    //-------------------------------------------------------
                } else if (strUser === "Eliminar cuenta") {
                    $http.put(ordenPagoContext + "/" + idOrdenPago, {
                        idPago: $scope.idPago,
                        pagada: $scope.pagada,
                        precio: $scope.precio
                    }).then(function (response) {
                        //Author created successfully
                        //Servicio----------------------------------------------------------------------------
                        if (strUser2 !== "No cambiar" && strUser2 !== "Eliminar servicio") {
                            //Asigno el servicio elegido
                            $http.get(servicioContext + '/' + strUser2).then(function (response) {
                                $scope.servicioOrden = {};
                                $scope.servicioOrden = response.data;
                                //-----------------------------------------------------------
                                $http.put(ordenPagoContext + "/" + idOrdenPago, {

                                    idPago: $scope.idPago,
                                    pagada: $scope.pagada,
                                    precio: $scope.precio
                                }).then(function (response) {
                                    if ($scope.servicioOrden.nombre !== undefined && $scope.servicioOrden.nombre !== null) {
                                        var news = {nombre: $scope.servicioOrden.nombre, precio: $scope.servicioOrden.precio};
                                        $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                            idPago: $scope.idPago,
                                            pagada: $scope.pagada,
                                            precio: $scope.precio,
                                            servicio: news
                                        }).then(function (response) {
                                            $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                        });
                                    }
                                });
                                //-----------------------------------------------------------
                            });
                        } else if (strUser2 === "No cambiar") {
                            //Considero que no haya ninguno o que si hubiera
                            //-------------------------------------------------------
                            if ($scope.serv !== null && $scope.serv !== undefined) {
                                var news = {nombre: $scope.serv.nombre, precio: $scope.serv.precio};
                                $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                    idPago: $scope.idPago,
                                    pagada: $scope.pagada,
                                    precio: $scope.precio,
                                    servicio: news
                                }).then(function (response) {
                                    //Author created successfully
                                    $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                });

                                //-------------------------------------------------------
                            }
                            //-------------------------------------------------------
                            else {
                                $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                    idPago: $scope.idPago,
                                    pagada: $scope.pagada,
                                    precio: $scope.precio
                                }).then(function (response) {
                                    //Author created successfully
                                    $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                                });
                            }
                            //-------------------------------------------------------
                        } else if (strUser2 === "Eliminar servicio") {
                            //Envío el bloque vacío
                            $http.put(ordenPagoContext + "/" + idOrdenPago, {
                                idPago: $scope.idPago,
                                pagada: $scope.pagada,
                                precio: $scope.precio
                            }).then(function (response) {
                                //Author created successfully
                                $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                            });
                        } else
                        {
                            $state.go('ordenPagoList', {ordenPagoId: response.data.idPago}, {reload: true});
                        }
                        //------------------------------------------------------------------------------------
                    });
                }
                //------------------------------------------------------------------------------------------
            }
            ;
        }
    ]);
}
)(angular);