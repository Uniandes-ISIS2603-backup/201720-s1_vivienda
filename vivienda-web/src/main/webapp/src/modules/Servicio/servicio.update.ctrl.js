(
        function (ng) {
            var mod = ng.module("servicioModule");
            mod.constant("servicioContext", "api/servicios");
            mod.controller('servicioUpdateCtrl', ['$scope', '$http', 'servicioContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, servicioContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    var idServicio = $state.params.servicioId;
                    
                    $http.get("http://localhost:8080/vivienda-web/api/servicios" + '/' + idServicio, ).then(function (response) {
                            var servicio = response.data;
                            $scope.servicioId = servicio.nombre;
                            $scope.servicioPrecio = servicio.precio;
                          
                    });
                    
                    
                    $scope.createServicio = function () {
                        $http.put("http://localhost:8080/vivienda-web/api/servicios" + '/' + idServicio, {
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