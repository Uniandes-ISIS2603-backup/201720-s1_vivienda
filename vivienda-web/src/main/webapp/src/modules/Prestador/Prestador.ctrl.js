(function (ng) {
    var mod = ng.module("mensajeModule");
    mod.constant("mensajeContext", "api/mensajes");
    mod.controller('mensajeCtrl', ['$scope', '$http', 'prestadorContext',
        function ($scope, $http, prestadoresContext) {
            $http.get("http://localhost:8080/vivienda-web/api/prestadores").then(function (response) {
                $scope.prestadoresRecords = response.data;
            });
        }
    ]);
}
)(angular);