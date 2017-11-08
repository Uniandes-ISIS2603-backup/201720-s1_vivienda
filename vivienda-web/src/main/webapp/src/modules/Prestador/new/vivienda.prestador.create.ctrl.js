(function (ng) {
    var mod = ng.module("prestadorModule");
    
    mod.controller('prestadoresCreateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            $scope.createPrestador = function () {
                $http.post("http://localhost:8080/vivienda-web/api/prestadores", {
                    documento: $scope.prestadorDocumento,
                    nombre: $scope.prestadorNombre,
                    prestador: $scope.prestadorDisponible
                }).then(function (response) {
                    //Author created successfully
                    $state.go('prestadorObtenerTodos', {prestadorDocumento: response.data.documento}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);