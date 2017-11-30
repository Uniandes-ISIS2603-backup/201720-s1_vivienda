(
        function (ng) {
            var mod = ng.module("servicioModule");
            mod.constant("servicioContext", "api/servicios");
            mod.controller('servicioUpdateCtrl', ['$scope', '$http', 'servicioContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, servicioContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    var idServicio = $state.params.servicioId;
                    
                 
                    
                    
                    $scope.createMensaje = function () {
                           $http.get("http://localhost:8080/vivienda-web/api/servicios" + '/' + idservicio).then(function (response) {
                            var servicio = response.data;
                           
                            $scope.servicionombre = servicio.precio;
                           
                    });
                    
                    
                    $http.get("http://localhost:8080/vivienda-web/api/prestadores").then(function (response) {
                    $scope.todoslosadmins = response.data;
                    });
                      
                            
                    $http.get("http://localhost:8080/vivienda-web/api/prestadores" + '/' + $scope.servicioAdmin, ).then(function (response) {
                        $scope.menadmin = response.data;
                        
                        
                        $http.put("http://localhost:8080/vivienda-web/api/servicios" + '/' + idservicio, {
                             nombre: $scope.servicioNombre,
                    precio: $scope.servicioPrecio,
                    prestador: { documento:$scope.servicioAdmin}
                           
                            
                        }).then(function (response) {
                            $state.go('servicioList', {servicioId: response.data.id}, {reload: true});
                        }, function()
                        {
                            $state.go('mensajeError', {servicioId: false}, {reload: true});
                        });
                    });  
                    };
                }
            ]);
        }
)(window.angular);