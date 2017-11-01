(function (ng) {
     var mod = ng.module("ordenPagoModule");
    mod.constant("ordenPagoContext", "api/ordenPagos");
    mod.controller('ordenPagoDeleteCtrl', ['$scope', '$http', 'ordenPagoContext', '$state',
        function ($scope, $http, ordenPagoContext, $state) {
            var idord = $state.params.ordenPagoId;
            $scope.deleteOrdenPago = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/ordenPagos" + '/' + idord, {}).then(function (response) {
                    $state.go('ordenPagoList', {ordenPagoId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);