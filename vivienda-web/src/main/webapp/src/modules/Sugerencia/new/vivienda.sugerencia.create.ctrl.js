(function (ng) {
    var mod = ng.module("viviendaSugerenciaModule");
    
    mod.controller('sugerenciasCreateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            $scope.createSugerencia = function () {
                $http.post("http://localhost:8080/vivienda-web/api/sugerencias", {
                    mensaje: $scope.sugerenciaMensaje,
                    estudiante:{ documento: $scope.sugerenciaDocumentoEstudiante},
                    admin:{ documento: $scope.sugerenciaDocumentoAdmin}
                }).then(function (response) {
                    //Author created successfully
                    $state.go('viviendaSugerenciaObtenerTodos', {sugerenciaId: response.data.id}, {reload: true});
                });
            };
            $http.get("http://localhost:8080/vivienda-web/api/administradores").then(function (response) {
           
                $scope.administradoresRecords = response.data;
            });
            $http.get("http://localhost:8080/vivienda-web/api/estudiantes").then(function (response) {
                
                $scope.estudiantesRecords = response.data;
            });
        }
    ]);
}
)(angular);