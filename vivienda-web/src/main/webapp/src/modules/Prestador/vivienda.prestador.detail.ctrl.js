(function (ng) {

    var mod = ng.module("prestadorModule");

    mod.controller('prestadoresDetailCtrl', ['$scope', '$http','$state', function ($scope, $http,$state) {

            $scope.prestador;
           
            if($state.params.documento !== undefined){
            $http.get("http://localhost:8080/vivienda-web/api/prestadores"+'/'+$state.params.documento)

                    .then(function (response) {

                        $scope.prestador = response.data;
           
                    });
                }
            
                
        }]);

// Código continua con las funciones de despliegue de errores

})(window.angular);

