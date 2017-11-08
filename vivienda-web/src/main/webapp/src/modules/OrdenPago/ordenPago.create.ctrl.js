(function (ng) {
    var mod = ng.module("ordenPagoModule");
    mod.constant("ordenPagoContext", "api/ordenPagos");
    mod.controller('ordenPagoNewCtrl', ['$scope', '$http', 'ordenPagoContext', '$state',
        function ($scope, $http, ordenPagoContext, $state) {
            $scope.createOrdenPago = function () {
                //----------------Obtener el valor de la cajita--------------------
                var bool = document.getElementById("cbpagada").checked;
                $scope.pagada = bool;
                //-----------------------------------------------------------------
                $http.post(ordenPagoContext, {
                    idPago: $scope.idPago,
                    pagada: $scope.pagada,
                    precio: $scope.precio

                }).then(function (response) {
                    //Author created successfully
                    $state.go('ordenPagoList', {idPago: response.data.idPago}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);