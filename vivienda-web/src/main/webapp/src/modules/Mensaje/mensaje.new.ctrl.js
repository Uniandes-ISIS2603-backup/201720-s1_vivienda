(function (ng) {
    var mod = ng.module("mensajeModule");
    mod.constant("mensajeContext", "api/mensajes");
    mod.controller('mensajeNewCtrl', ['$scope', '$http', 'mensajeContext', '$state', '$rootScope',
        function ($scope, $http, mensajeContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createMensaje = function () {
                try
                {
                $http.get("http://localhost:8080/vivienda-web/api/administradores" + '/' + $scope.mensajeAdmin).then(function (response) {
                    $scope.menadmin = response.data;
                });
                
                if($scope.menadmin !== undefined && $scope.menadmin !== null)
                {
                $http.post("http://localhost:8080/vivienda-web/api/mensajes", {
                    id: $scope.mensajeId,
                    titulo: $scope.mensajeTitulo,
                    asunto: $scope.mensajeAsunto,
                    mensaje: $scope.mensajeMensaje,
                    admin: $scope.menadmin
                }).then(function (response) {
                    $state.go('mensajeList', {mensajeId: response.data.id}, {reload: true});
                });
                }
                
                }
                catch(Error)
                {
                    $state.go('mensajeList', {mensajeId: null}, {reload: true});
                }
            };
        }
    ]);
}
)(window.angular);