(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('adminCtrl', ['$scope', '$http', 'adminContext',
        function ($scope, $http, adminContext) {
            $http.get('data/administradores.json').then(function (response) {
                $scope.administradoresRecords = response.data;
            });
        }
    ]);
}
)(angular);