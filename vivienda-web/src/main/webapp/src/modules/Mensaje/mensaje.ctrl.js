(function (ng) {
    var mod = ng.module("mensajeModule");
    mod.constant("mensajeContext", "api/mensajes");
    mod.controller('mensajeCtrl', ['$scope', '$http', 'mensajeContext', '$state',
        function ($scope, $http, mensajesContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/mensajes").then(function (response) {
                $state.params.mensajeId = null;
                $scope.mensajesRecords = response.data;
            });
            
            if (($state.params.mensajeId !== undefined)&& ($state.params.mensajeId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/mensajes" + '/' + $state.params.mensajeId).then(function (response) {
                    $scope.currentMensaje = response.data;
                    $scope.menadmin = $scope.currentMensaje.admin;
                });
            }
        }
    ]);
}
)(angular);