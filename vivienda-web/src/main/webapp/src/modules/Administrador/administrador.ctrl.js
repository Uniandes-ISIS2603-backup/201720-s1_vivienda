(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('adminCtrl', ['$scope', '$http', 'adminContext',
        function ($scope, $http, adminContext) {
            $http.get("http://localhost:8080/vivienda-web/api/administradores").then(function (response) {
                $scope.administradoresRecords = response.data;
            });
        }
    ]);
}
)(angular);