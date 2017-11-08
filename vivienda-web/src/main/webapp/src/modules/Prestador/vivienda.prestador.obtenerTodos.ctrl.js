(function (ng) {

    var mod = ng.module("viviendaEstudianteModule");

    mod.controller('prestadorObtenerTodosCtrl', ['$scope', '$http', function ($scope, $http) {

            $scope.prestadores = [];
            $http.get("http://localhost:8080/vivienda-web/api/prestadores")

                    .then(function (response) {

                        $scope.prestadores = response.data;

                    });
        }]);

// Código continua con las funciones de despliegue de errores

})(window.angular);

