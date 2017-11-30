(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('servicioNewCtrl', ['$scope', '$http', 'servicioContext', '$state', '$rootScope',
        function ($scope, $http, servicioContext, $state, $rootScope) {
            $rootScope.edit = false;
            
           $http.get("http://localhost:8080/vivienda-web/api/prestadores").then(function (response) {
                
                $scope.prestadoresRecords = response.data;
            });
            
            $scope.createServicio = function () {
                
               $http.get("http://localhost:8080/vivienda-web/api/prestadores" + '/' + $scope.servicioAdmin).then(function (response) {
                    $scope.servicioadmin = response.data;
                
                
                     $http.post("http://localhost:8080/vivienda-web/api/servicios", {
                    nombre: $scope.servicioNombre,
                    precio: $scope.servicioPrecio,
                    prestador: { documento:$scope.servicioAdmin}
                }).then(function (response) {
                    $state.go('servicioList', {servicioId: response.data.id}, {reload: true});
                    }, function()
                    {
                        console.log("Fallo");
                        $state.go('servicioError', {servicioId: false}, {reload: true});
                    });
                });
            };
        }
    ]);
}
)(window.angular);