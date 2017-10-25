(function (ng) {
    var mod = ng.module("mensajeModule");
    mod.constant("mensajeContext", "api/mensajes");
    mod.controller('mensajeCtrl', ['$scope', '$http', 'mensajeContext',
        function ($scope, $http, mensajesContext) {
            $http.get("http://localhost:8080/vivienda-web/api/mensajes").then(function (response) {
                $scope.mensajesRecords = response.data;
            });
        }
    ]);
}
)(angular);