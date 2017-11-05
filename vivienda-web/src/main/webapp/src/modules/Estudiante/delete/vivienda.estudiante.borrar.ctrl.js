(function (ng) { 
    var mod = ng.module("viviendaEstudianteModule");
    
    mod.controller('estudiantesBorrarCtrl', ['$scope', '$http','$state',
        function ($scope, $http,$state) {
            var documento = $state.params.documento;
            
            $scope.borrarEstudiante = function () {
                $http.delete("http://localhost:8080/vivienda-web/api/estudiantes"+'/'+documento,{}).then(function (response) {
                    alert("Entro");
                    $state.go('viviendaEstudianteObtenerTodos', {estudianteId: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);