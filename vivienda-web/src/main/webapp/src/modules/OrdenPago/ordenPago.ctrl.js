(function (ng) {
    var mod = ng.module("ordenPagoModule");
    mod.constant("ordenPagoContext", "api/ordenPagos");
    mod.controller('ordenPagoCtrl', ['$scope', '$http', 'ordenPagoContext',
        function ($scope, $http, ordenPagoContext) {
            $http.get("http://localhost:8080/vivienda-web/api/ordenPagos").then(function (response) {
                $scope.ordenPagosRecords = response.data;
            });
        }
    ]);
}
)(angular);