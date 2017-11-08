(function (ng) { 
    var mod = ng.module("prestadorModule");
    
    mod.controller('prestadoresBorrarCtrl', ['$scope', '$http','$state',
        function ($scope, $http,$state) {
            var documento = $state.params.documento;
            
            $scope.borrarPrestador = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/prestadores"+'/'+documento,{}).then(function (response) {
                    
                    $state.go('prestadorObtenerTodos', {prestadorId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);