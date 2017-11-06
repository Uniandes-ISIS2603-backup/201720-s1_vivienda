(function (ng) {
    var mod = ng.module("viviendaSugerenciaModule");
    
    mod.controller('sugerenciasUpdateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            $scope.updateSugerencia = function () {
                $http.put("http://localhost:8080/vivienda-web/api/sugerencias"+'/'+$state.params.sugerenciaID, {
                    mensaje: $scope.sugerenciaMensaje
                }).then(function (response) {
                    //Author created successfully
                    $state.go('viviendaSugerenciaObtenerTodos', {sugerenciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);