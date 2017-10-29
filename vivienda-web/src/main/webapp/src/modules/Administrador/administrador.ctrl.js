(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('adminCtrl', ['$scope', '$http', 'adminContext', '$state',
        function ($scope, $http, adminContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/administradores").then(function (response) {
                $state.params.adminId = null;
                $scope.administradoresRecords = response.data;
            });
            
            if (($state.params.adminId !== undefined)&& ($state.params.adminId !== null)) {
                $http.get("http://localhost:8080/vivienda-web/api/administradores" + '/' + $state.params.adminId).then(function (response) {
                    $scope.currentAdmin = response.data;
                });
            }
        }
    ]);
}
)(angular);