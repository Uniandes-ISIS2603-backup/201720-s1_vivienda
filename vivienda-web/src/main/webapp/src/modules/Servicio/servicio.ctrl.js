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
                $http.get("http://localhost:8080/vivienda-web/api/servicios" + '/' + $state.params.servicioId).then(function (response) {
                    $scope.currentServicio = response.data;
                $scope.servicioadmin = $scope.currentServicio.prestador;
                });
            }
            
            $scope.buscarServicio = function () {
                $http.get("http://localhost:8080/vivienda-web/api/servicios").then(function (response) {
                    $scope.todosServicios = response.data;
                    var bool = false;
                    for(var pos = 0;pos < $scope.todosServicios.length; pos++)
                    {
                        if($scope.todosServicios[pos].titulo === $scope.mensajeBuscarTitulo)
                        {
                            $scope.currentServicio = $scope.todosServicios[pos];
                            bool = true;
                            break;
                        }
                    }
                    if(bool === true)
                    {
                        $state.go('servicioDetail', {servicioId: $scope.currentServicio.id}, {reload: true});
                    }
                    else
                    {
                        $state.go('servicioError', {adminId: null}, {reload: true});
                    }
                }, function()
                {
                    $state.go('servicioError', {adminId: null}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);