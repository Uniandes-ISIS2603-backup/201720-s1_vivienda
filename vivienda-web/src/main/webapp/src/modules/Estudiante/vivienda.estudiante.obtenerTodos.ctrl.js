(function (ng) {

    var mod = ng.module("viviendaEstudianteModule");

    mod.controller("viviendaEstudianteObtenerTodosCtrl", ['$scope', '$http', function ($scope, $http) {

            $scope.elements = [];
            $http.get("http://localhost:8080/vivienda-web/api/estudiantes")

                    .then(function (response) {

                        $scope.elements = response.data;

                    });
        }]);

// Código continua con las funciones de despliegue de errores

})(window.angular);

