(function (ng) {

    var mod = ng.module("viviendaSugerenciaModule");

    mod.controller("viviendaSugerenciaObtenerTodosCtrl", ['$scope', '$http', function ($scope, $http) {

            $scope.sugerencias = [];
            $http.get("http://localhost:8080/vivienda-web/api/sugerencias")

                    .then(function (response) {

                        $scope.sugerencias = response.data;

                    });
        }]);

// Código continua con las funciones de despliegue de errores

})(window.angular);

