(function (ng) {
var mod = ng.module("viviendaModule", []);
    mod.constant("viviendaContext", "api/estudiantes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Estudiante/';
            $urlRouterProvider.otherwise("");

            $stateProvider.state('viviendaEstudianteObtenerTodos', {
                url: 'vivienda/estudiantes',
                views: {
                    'mainView': {
                        controller: 'viviendaEstudianteObtenerTodosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.estudiantes.obtenerTodos.html'
                    }
                }
            });
        }]);

})(window.angular);

