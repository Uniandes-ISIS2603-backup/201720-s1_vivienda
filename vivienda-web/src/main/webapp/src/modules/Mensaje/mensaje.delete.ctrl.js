(function (ng) {
    var mod = ng.module("mensajeModule");
    mod.constant("mensajeContext", "api/mensajes");
    mod.controller('mensajeDeleteCtrl', ['$scope', '$http', 'mensajeContext', '$state',
        function ($scope, $http, mensajesContext, $state) {
            var idmen = $state.params.mensajeId;
            $scope.deleteMensaje = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/mensajes" + '/' + idmen, {}).then(function (response) {
                    $state.go('mensajeList', {mensajeId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);