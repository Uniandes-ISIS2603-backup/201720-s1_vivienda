(function (ng) {
    var mod = ng.module("ordenPagoModule");
    mod.constant("ordenPagoContext", "api/ordenPagos");
    mod.controller('ordenPagoNewCtrl', ['$scope', '$http', 'ordenPagoContext', '$state',
        function ($scope, $http, ordenPagoContext, $state) {


            $scope.allCuentasShow = [];
            $scope.allServiciosShow = [];
            $scope.cuentaSelected = {};
            $scope.servicioSelected = {};

            $scope.getCuenta = function () {
                $http.get("api/cuentas").then(function (response) {
                    $scope.allCuentasShow = response.data;

                });
            };

            $scope.getServicio = function () {
                $http.get("api/servicios").then(function (response) {
                    $scope.allServiciosShow = response.data;

                });
            };

            $scope.getCuenta();
            $scope.getServicio();
            $scope.createOrdenPago = function () {
                //----------------Obtener el valor de la cajita--------------------
                var bool = document.getElementById("cbpagada").checked;
                $scope.pagada = bool;
                //-----------------------------------------------------------------
                var e = document.getElementById("cuentaSelect");
                var strUser = e.options[e.selectedIndex].value;
                var e2 = document.getElementById("servicioSelect");
                var strUser2 = e2.options[e2.selectedIndex].value;

                //Buscar los objetos
                $http.get("api/cuentas" + "/" + strUser).then(function (response) {
                    $scope.cuentaSelected = response.data;
                    $http.get("api/servicios" + "/" + strUser2).then(function (response) {
                        $scope.servicioSelected = response.data;
                        var newc = {id: $scope.cuentaSelected.id, renta: $scope.cuentaSelected.renta};
                        var news = {nombre: $scope.servicioSelected.nombre, precio: $scope.servicioSelected.precio};
                        $http.post(ordenPagoContext, {
                            idPago: $scope.idPago,
                            pagada: $scope.pagada,
                            precio: $scope.precio,
                            cuenta: newc,
                            servicio: news

                        }).then(function (response) {
                            //Author created successfully
                            $state.go('ordenPagoList', {idPago: response.data.idPago}, {reload: true});
                        });
                    });
                });

            };
        }
    ]);
}
)(angular);