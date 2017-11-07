(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('servicioCtrl', ['$scope', '$http', 'servicioContext', '$state',
        function ($scope, $http, serviciosContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/servicios").then(function (response) {
                $state.params.servicioId = null;
                $scope.servicioRecords = response.data;
            });
            
            if (($state.params.servicioId !== undefined)&& ($state.params.servicioId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/servicios" + '/' + $state.params.servicioId).then(function (response) {
                    $scope.currentMensaje = response.data;
                });
            }
        }
    ]);
}
)(angular);