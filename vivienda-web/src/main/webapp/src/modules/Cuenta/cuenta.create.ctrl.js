(function (ng) {
    var mod = ng.module("cuentaModule");
    mod.constant("cuentaContext", "api/cuentas");
    mod.controller('cuentaNewCtrl', ['$scope', '$http', 'cuentaContext', '$state',
        function ($scope, $http, cuentaContext, $state) {

            $scope.allEstudiantesShow = [];

            $scope.est = {};
            $scope.getEstudiante = function () {
                $http.get("api/estudiantes").then(function (response) {
                    $scope.allEstudiantesShow = response.data;
                });

            };

            $scope.getEstudiante();
            $scope.createCuenta = function () {
                var e = document.getElementById("estudianteSelect");
                var strUser = e.options[e.selectedIndex].value;
                
               
                    $http.get("api/estudiantes/" + strUser).then(function (response) {
                        $scope.est = response.data;
                        $http.post(cuentaContext, {
                            id: $scope.id,
                            renta: $scope.renta,
                            estudiante: $scope.est

                        }).then(function (response) {
                            //Author created successfully
                            $state.go('cuentaList', {id: response.data.id}, {reload: true});
                        });
                    });

                
                //-----------------------------------------------------------------

            };
        }
    ]);
}
)(angular);