(function (ng) {
    var mod = ng.module("prestadorModule");
    
    mod.controller('prestadoresUpdateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            $scope.updatePrestador = function () {
                $http.put("http://localhost:8080/vivienda-web/api/prestadores"+'/'+$state.params.documento, {
                    documento: $scope.prestadorDocumento,
                    nombre: $scope.prestadorNombre,
                    disponible: $scope.prestadorDisponible 
                }).then(function (response) {
                    //Author created successfully
                    $state.go('prestadorObtenerTodos', {prestadorDocumento: response.data.documento}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);