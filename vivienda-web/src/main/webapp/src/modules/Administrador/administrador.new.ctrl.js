(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('adminNewCtrl', ['$scope', '$http', 'adminContext', '$state', '$rootScope',
        function ($scope, $http, adminContext, $state, $rootScope) {
            $rootScope.edit = false;
            $scope.createAdmin = function () {
                $http.post("http://localhost:8080/vivienda-web/api/administradores", {
                    documento: $scope.adminDocumento,
                    nombre: $scope.adminNombre,
                    username: $scope.adminUsername,
                    password: $scope.adminPassword
                }).then(function (response) {
                    //Author created successfully
                    $state.go('adminList', {adminId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);