(
        function (ng) {
            var mod = ng.module("prestadorModule");
            mod.constant("prestadorContext", "api/prestadores");
            mod.controller('prestadorUpdateCtrl', ['$scope', '$http', 'prestadorContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, prestadorContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    var idprestador = $state.params.prestadorId;
                    
                    $http.get("http://localhost:8080/vivienda-web/api/prestadores" + '/' + idPrestador, ).then(function (response) {
                            var prestador = response.data;
                            $scope.prestadorNombre = prestador.nombre;
                            $scope.prestadorDocumento = prestador.Documento;
                            $scope.prestadorDisponible = prestador.disponible;
                            
                    });
                    
                    
                    $scope.createPrestador = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        $http.put("http://localhost:8080/vivienda-web/api/prestadores" + '/' + idPrestador, {
                            nombre: $scope.prestadorNombre,
                            documento: $scope.prestadorDocumento,
                            disponible: $scope.prestadorDisponible
                            
                        }).then(function (response) {
                            //Author created successfully
                            $state.go('prestadorList', {prestadorId: response.data.id}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);