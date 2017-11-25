(function (ng) {
    var mod = ng.module("viviendaEstudianteModule");
    
    mod.controller('estudiantesUpdateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            fillDoc = $state.params.documento;
            $scope.updateEstudiante = function () {
                $http.put("http://localhost:8080/vivienda-web/api/estudiantes"+'/'+$state.params.documento, {
                    documento: $scope.estudianteDocumento,
                    nombre: $scope.estudianteNombre,
                    passWord: $scope.estudiantePassWord,
                    userName: $scope.estudianteUserName
                }).then(function (response) {
                    //Author created successfully
                    $state.go('viviendaEstudianteObtenerTodos', {estudianteDocumento: response.data.documento}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);