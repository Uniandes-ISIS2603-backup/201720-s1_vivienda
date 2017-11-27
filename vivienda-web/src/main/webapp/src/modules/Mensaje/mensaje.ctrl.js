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
            
            $scope.buscarMensaje = function () {
                $http.get("http://localhost:8080/vivienda-web/api/mensajes").then(function (response) {
                    $scope.todosMensajes = response.data;
                    var bool = false;
                    for(var pos = 0;pos < $scope.todosMensajes.length; pos++)
                    {
                        if($scope.todosMensajes[pos].titulo === $scope.mensajeBuscarTitulo)
                        {
                            $scope.currentMensaje = $scope.todosMensajes[pos];
                            bool = true;
                            break;
                        }
                    }
                    if(bool === true)
                    {
                        $state.go('mensajeDetail', {mensajeId: $scope.currentMensaje.id}, {reload: true});
                    }
                    else
                    {
                        $state.go('mensajeError', {adminId: null}, {reload: true});
                    }
                }, function()
                {
                    $state.go('mensajeError', {adminId: null}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);