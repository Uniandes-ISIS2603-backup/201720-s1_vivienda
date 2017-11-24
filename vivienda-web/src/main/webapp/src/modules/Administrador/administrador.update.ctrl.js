(
        function (ng) {
            var mod = ng.module("administradorModule");
            mod.constant("adminContext", "api/administradores");
            mod.controller('adminUpdateCtrl', ['$scope', '$http', 'adminContext', '$state', '$rootScope', '$filter',
                function ($scope, $http, adminContext, $state, $rootScope, $filter) {
                    $rootScope.edit = true;
                    var idAdmin = $state.params.adminId;
                    
                    $http.get("http://localhost:8080/vivienda-web/api/administradores" + '/' + idAdmin, ).then(function (response) {
                            var admin = response.data;
                            $scope.adminDocumento = admin.documento;
                            $scope.adminUsername = admin.username;
                            $scope.adminNombre = admin.nombre;
                            $scope.adminPassword = admin.password;
                    });
                    
                    
                    $scope.createAdmin = function () {
                        /*Se llama a la función newBooks() para buscar cada uno de los ids de los books
                         en el array que tiene todos los books y así saber como queda la lista final de los books asociados al autor.
                         */
                        $http.put("http://localhost:8080/vivienda-web/api/administradores" + '/' + idAdmin, {
                            documento: $scope.adminDocumento,
                            username: $scope.adminUsername,
                            nombre: $scope.adminNombre,
                            password: $scope.adminPassword
                        }).then(function (response) {
                            $state.go('adminList', {adminId: response.data.id}, {reload: true});
                        }, function()
                        {
                            $state.go('adminError', {adminId: false}, {reload: true});
                        });
                    };
                }
            ]);
        }
)(window.angular);