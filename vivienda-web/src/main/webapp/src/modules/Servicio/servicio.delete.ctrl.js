(function (ng) {
    var mod = ng.module("servicioModule");
    mod.constant("servicioContext", "api/servicios");
    mod.controller('servicioDeleteCtrl', ['$scope', '$http', 'servicioContext', '$state',
        function ($scope, $http, serviciosContext, $state) {
            var idser = $state.params.servicioId;
            $scope.deleteServicio = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/servicios" + '/' + idser, {}).then(function (response) {
                    $state.go('servicioList', {servicioId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);