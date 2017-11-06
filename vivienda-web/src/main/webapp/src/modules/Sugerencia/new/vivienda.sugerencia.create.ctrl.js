(function (ng) {
    var mod = ng.module("viviendaSugerenciaModule");
    
    mod.controller('sugerenciasCreateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            $scope.createSugerencia = function () {
                $http.post("http://localhost:8080/vivienda-web/api/sugerencias", {
                    id: $scope.sugerenciaID,
                    mensaje: $scope.sugerenciaMensaje,
                    estudiante:{ documento: $scope.sugerenciaDocumentoEstudiante},
                    admin:{ documento: $scope.sugerenciaDocumentoAdmin}
                }).then(function (response) {
                    //Author created successfully
                    $state.go('viviendaSugerenciaObtenerTodos', {sugerenciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);