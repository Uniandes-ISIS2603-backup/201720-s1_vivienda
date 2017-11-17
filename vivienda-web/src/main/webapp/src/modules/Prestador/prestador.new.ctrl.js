(function (ng) {
    var mod = ng.module("prestadorModule");
    mod.constant("prestadorContext", "api/prestadores");
    mod.controller('prestadorNewCtrl', ['$scope', '$http', 'prestadorContext', '$state', '$rootScope',
        function ($scope, $http, prestadorContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createPrestador = function () {
                $http.post("http://localhost:8080/vivienda-web/api/prestadores", {
                    nombre: $scope.prestadorNombre,
                    documento: $scope.prestadorDocumento,
                    disponible: $scope.prestadorDisponible
                }).then(function (response) {
                    //Author created successfully
                    $state.go('prestadorList', {prestadorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);