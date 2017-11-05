(function (ng) {

    var mod = ng.module("viviendaEstudianteModule");

    mod.controller('estudiantesDetailCtrl', ['$scope', '$http','$state', function ($scope, $http,$state) {

            $scope.estudiante;
            $scope.suge;
            if($state.params.documento !== undefined){
            $http.get("http://localhost:8080/vivienda-web/api/estudiantes"+'/'+$state.params.documento)

                    .then(function (response) {

                        $scope.estudiante = response.data;
           
                    });
                }
            
                
        }]);

// Código continua con las funciones de despliegue de errores

})(window.angular);

