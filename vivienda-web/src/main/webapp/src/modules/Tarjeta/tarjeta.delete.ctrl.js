(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaDeleteCtrl', ['$scope', '$http', 'tarjetaContext', '$state',
        function ($scope, $http, tarjetaContext, $state) {
            var idtar = $state.params.tarjetaId;
            $scope.deleteTarjeta = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/tarjetas" + '/' + idtar, {}).then(function (response) {
                    $state.go('tarjetaList', {tarjetaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);