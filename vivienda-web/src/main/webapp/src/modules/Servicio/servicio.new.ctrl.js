(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('servicioNewCtrl', ['$scope', '$http', 'servicioContext', '$state', '$rootScope',
        function ($scope, $http, servicioContext, $state, $rootScope) {
            $rootScope.edit = false;
            $http.get("http://localhost:8080/vivienda-web/api/prestadores").then(function (response) {
                
                $scope.prestadoresRecords = response.data;
            });
            $http.get("http://localhost:8080/vivienda-web/api/prestadores").then(function (response) {
                    $scope.servicioadmin2 = response.data;});
            $scope.createServicio = function () {
                try
                {
                $http.get("http://localhost:8080/vivienda-web/api/prestadores" + '/' + $scope.servicioAdmin).then(function (response) {
                    $scope.servicioadmin = response.data;
                });
                
                if($scope.servicioadmin !== undefined && $scope.servicioadmin !== null)
                {
                $http.post("http://localhost:8080/vivienda-web/api/servicios", {
                    nombre: $scope.servicioId,
                    precio: $scope.servicioPrecio,
                    admin: $scope.servicioadmin
                }).then(function (response) {
                    $state.go('servicioList', {servicioId: response.data.id}, {reload: true});
                });
                }
                
                }
                catch(Error)
                {
                    $state.go('servicioList', {servicioId: null}, {reload: true});
                }
            };
        }
    ]);
}
)(window.angular);