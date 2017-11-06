(function (ng) {

    var mod = ng.module("viviendaSugerenciaModule");

    mod.controller('sugerenciasDetailCtrl', ['$scope', '$http','$state', function ($scope, $http,$state) {

            $scope.sugerencia;
           
            if($state.params.sugerenciaID !== undefined){
            $http.get("http://localhost:8080/vivienda-web/api/sugerencias"+'/'+$state.params.sugerenciaID)

                    .then(function (response) {
                            
                        $scope.sugerencia = response.data;
           
                    });
                }
            
                
        }]);
    
})(window.angular);
