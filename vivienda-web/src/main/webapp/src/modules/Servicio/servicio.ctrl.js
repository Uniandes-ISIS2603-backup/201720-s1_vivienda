(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('servicioCtrl', ['$scope', '$http', 'servicioContext', '$state',
        function ($scope, $http, servicioContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/servicios").then(function (response) {
                $state.params.servicioId = null;
                $scope.serviciosRecords = response.data;
            });
            
            if (($state.params.servicioId !== undefined)&& ($state.params.servicioId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/mensajes" + '/' + $state.params.servicioId).then(function (response) {
                    $scope.currentservicio = response.data;
                    $scope.servicioadmin = $scope.currentServicio.admin;
                });
            }
        }
    ]);
}
)(angular);