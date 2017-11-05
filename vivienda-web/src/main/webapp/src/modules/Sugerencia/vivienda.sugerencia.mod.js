(function (ng) {
var mod = ng.module("viviendaSugerenciaModule", []);
    mod.constant("viviendaContext", "api/sugerencias");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Sugerencia/';
            
            $urlRouterProvider.otherwise("");

            $stateProvider.state('viviendaSugerenciaObtenerTodos', {
                url: 'vivienda/sugerencias',
                views: {
                    'mainView': {
                        controller: 'viviendaSugerenciaObtenerTodosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.sugerencias.obtenerTodos.html'
                    }
                }
            });
        }]);

})(window.angular);

