(function (ng) {
    var mod = ng.module("tarjetaModule");
    mod.constant("tarjetaContext", "api/tarjetas");
    mod.controller('tarjetaCtrl', ['$scope', '$http', 'tarjetaContext',
        function ($scope, $http, tarjetaContext) {
            $http.get("http://localhost:8080/vivienda-web/api/tarjetas").then(function (response) {
                $scope.tarjetasRecords = response.data;
            });
        }
    ]);
}
)(angular);