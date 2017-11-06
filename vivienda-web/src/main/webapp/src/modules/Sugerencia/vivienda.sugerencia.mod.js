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
            }).state('sugerenciasDetail', {
                url: 'vivienda/sugerencias/(sugerenciaID:int)',
                params:{
                    sugerenciaID:null
                },
                views: {
                    'mainView': {
                        controller: 'sugerenciasDetailCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.sugerencias.detail.html'
                    }
                }
            }).state('sugerenciasBorrar', {
                url: 'vivienda/sugerencias/(sugerenciaID:int)',
                params:{
                    sugerenciaID:null
                },
                views: {
                    'mainView': {
                        controller: 'sugerenciasBorrarCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'delete/vivienda.sugerencias.borrar.html'
                    }
                }
            });
        }]);

})(window.angular);

