(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('adminCtrl', ['$scope', '$http', 'adminContext', '$state',
        function ($scope, $http, adminContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/administradores").then(function (response) {
                $state.params.adminId = null;
                $scope.administradoresRecords = response.data;
            });
            
            $http.get("http://localhost:8080/vivienda-web/api/mensajes").then(function (response) {
                $scope.mensajesRecords = response.data;
            });
            
            $http.get("http://localhost:8080/vivienda-web/api/sugerencias").then(function (response) {
                $scope.sugerenciasRecords = response.data;
            });
            
            $http.get("http://localhost:8080/vivienda-web/api/torres").then(function (response) {
                $scope.torresRecords = response.data;
            });
            
            if (($state.params.adminId !== undefined)&& ($state.params.adminId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/administradores" + '/' + $state.params.adminId).then(function (response) {
                    $scope.currentAdmin = response.data;
                });
            }
            
            $scope.buscarAdministrador = function () {
                $http.get("http://localhost:8080/vivienda-web/api/administradores" + '/' + $scope.adminBuscarDocumento).then(function (response) {
                    $scope.currentAdmin = response.data;
                    $state.go('adminDetail', {adminId: $scope.adminBuscarDocumento}, {reload: true});
                }, function()
                {
                    $state.go('adminError', {adminId: null}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);