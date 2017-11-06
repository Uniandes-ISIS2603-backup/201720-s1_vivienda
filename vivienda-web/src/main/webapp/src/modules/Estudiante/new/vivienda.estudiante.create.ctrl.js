(function (ng) {
    var mod = ng.module("viviendaEstudianteModule");
    
    mod.controller('estudiantesCreateCtrl', ['$scope', '$http','$state','$rootScope',
        function ($scope, $http,$state,$rootScope) {
            $rootScope.edit = false;
            $scope.createEstudiante = function () {
                $http.post("http://localhost:8080/vivienda-web/api/estudiantes", {
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