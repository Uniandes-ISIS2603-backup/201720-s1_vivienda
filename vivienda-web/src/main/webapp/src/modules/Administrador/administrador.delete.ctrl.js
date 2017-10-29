(function (ng) {
    var mod = ng.module("administradorModule");
    mod.constant("adminContext", "api/administradores");
    mod.controller('adminDeleteCtrl', ['$scope', '$http', 'adminContext', '$state',
        function ($scope, $http, adminContext, $state) {
            var idadmin = $state.params.adminId;
            $scope.deleteAdmin = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/administradores" + '/' + idadmin, {}).then(function (response) {
                    $state.go('adminList', {adminId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(window.angular);