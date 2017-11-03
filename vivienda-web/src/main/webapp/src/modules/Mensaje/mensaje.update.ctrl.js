(
        function (ng) {
            var mod = ng.module("mensajeModule");
            mod.constant("mensajeContext", "api/mensajes");
            mod.controller('mensajeUpdateCtrl', ['$scope', '$http', 'mensajeContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, mensajeContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    var idMensaje = $state.params.mensajeId;
                    
                    $http.get("http://localhost:8080/vivienda-web/api/mensajes" + '/' + idMensaje, ).then(function (response) {
                            var mensaje = response.data;
                            $scope.mensajeId = mensaje.id;
                            $scope.mensajeTitulo = mensaje.titulo;
                            $scope.mensajeAsunto = mensaje.asunto;
                            $scope.mensajeMensaje = mensaje.mensaje;
                    });
                    
                    
                    $scope.createMensaje = function () {
                        $http.put("http://localhost:8080/vivienda-web/api/mensajes" + '/' + idMensaje, {
                            id: $scope.mensajeId,
                            titulo: $scope.mensajeTitulo,
                            asunto: $scope.mensajeAsunto,
                            mensaje: $scope.mensajeMensaje
                        }).then(function (response) {
                            $state.go('mensajeList', {mensajeId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);