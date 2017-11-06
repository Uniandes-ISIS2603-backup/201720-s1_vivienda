(function (ng) { 
    var mod = ng.module("viviendaSugerenciaModule");
    
    mod.controller('sugerenciasBorrarCtrl', ['$scope', '$http','$state',
        function ($scope, $http,$state) {
            var id = $state.params.sugerenciaID;
            
            $scope.borrarSugerencia = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/sugerencias"+'/'+id,{}).then(function (response) {
                   
                    $state.go('viviendaSugerenciaObtenerTodos', {sugerenciaId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);