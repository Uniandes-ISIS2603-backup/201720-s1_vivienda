(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('ordenNoPagadaCtrl', ['$scope', '$http', 'adminContext', '$state',
        function ($scope, $http, adminContext, $state) {
            $http.get("http://localhost:8080/vivienda-web/api/ordenPagos").then(function (response) {
                $scope.ordenPagosAdminRecords = [];
                $scope.ordensinfiltro = response.data;
                var contador = 0;
                for(var pos = 0; pos < $scope.ordensinfiltro.length; pos++)
                {
                    if($scope.ordensinfiltro[pos].pagada === false)
                    {
                        $scope.ordenPagosAdminRecords[contador] = $scope.ordensinfiltro[pos];
                        contador++;
                    }
                }
            });
        }
    ]);
}
)(angular);