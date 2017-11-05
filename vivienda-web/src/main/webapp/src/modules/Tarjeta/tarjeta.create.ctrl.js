(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaNewCtrl', ['$scope', '$http', 'tarjetaContext', '$state',
        function ($scope, $http, tarjetaContext, $state) {
            $scope.createTarjeta = function () {
                $http.post(tarjetaContext, {
                    nombre: $scope.nombreTarjeta,
                    numeroTarjeta: $scope.numeroTarjeta
                  
                }).then(function (response) {
                    //Author created successfully
                    $state.go('tarjetaList', {numeroTarjeta: response.data.numeroTarjeta}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);