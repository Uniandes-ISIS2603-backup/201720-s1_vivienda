(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('servicioNewCtrl', ['$scope', '$http', 'servicioContext', '$state', '$rootScope',
        function ($scope, $http, servicioContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createServicio = function () {
                $http.post("http://localhost:8080/vivienda-web/api/servicios", {
                    nombre: $scope.servicioId,
                    precio: $scope.servicioPrecio,
                   
                }).then(function (response) {
                    $state.go('servicioList', {servicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);