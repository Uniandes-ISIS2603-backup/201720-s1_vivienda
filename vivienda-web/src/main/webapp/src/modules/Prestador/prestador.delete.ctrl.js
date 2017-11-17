(function (ng) {
    var mod = ng.module("prestadorModule");
    mod.constant("prestadorContext", "api/prestadores");
    mod.controller('prestadorDeleteCtrl', ['$scope', '$http', 'prestadorContext', '$state',
        function ($scope, $http, prestadorContext, $state) {
            var idprestador = $state.params.prestadorId;
            $scope.deletePrestador = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/prestadores" + '/' + idprestador, {}).then(function (response) {
                    $state.go('prestadorList', {prestadorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);