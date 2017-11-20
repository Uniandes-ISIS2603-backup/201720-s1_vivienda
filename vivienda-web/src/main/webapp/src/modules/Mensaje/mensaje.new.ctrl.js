(function (ng) {
    var mod = ng.module("mensajeModule");
    mod.constant("mensajeContext", "api/mensajes");
    mod.controller('mensajeNewCtrl', ['$scope', '$http', 'mensajeContext', '$state', '$rootScope',
        function ($scope, $http, mensajeContext, $state, $rootScope) {
            $rootScope.edit = false;
            
            $http.get("http://localhost:8080/vivienda-web/api/administradores").then(function (response) {
            $scope.todoslosadmins = response.data;
            });
            
            $scope.createMensaje = function () {
                
                $http.get("http://localhost:8080/vivienda-web/api/administradores" + '/' + $scope.mensajeAdmin, ).then(function (response) {
                    $scope.menadmin2 = response.data;
                });
                
                $http.post("http://localhost:8080/vivienda-web/api/mensajes", {
                    titulo: $scope.mensajeTitulo,
                    asunto: $scope.mensajeAsunto,
                    mensaje: $scope.mensajeMensaje,
                    admin: $scope.menadmin2
                   
                }).then(function (response) {
                   $state.go('mensajeList', {mensajeId: response.data.id}, {reload: true});
                });
                
            };
        }
    ]);
}
)(window.angular);