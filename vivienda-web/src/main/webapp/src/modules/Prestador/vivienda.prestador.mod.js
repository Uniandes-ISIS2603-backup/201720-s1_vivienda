(function (ng) {
    var mod = ng.module("prestadorModule", []);
    mod.constant("prestadorContext", "api/prestadores");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Prestador/';
            $urlRouterProvider.otherwise("");

            $stateProvider.state('prestadorObtenerTodos', {
                url: 'vivienda/estudiantes',
                views: {
                    'mainView': {
                        controller: 'prestadorObtenerTodosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.prestadores.obtenerTodos.html'
                    }
                }
            }).state('prestadoresDetail', {
                url: 'vivienda/prestadores/(documento:Long)',
                params:{
                    documento:null
                },
                views: {
                    'mainView': {
                        controller: 'prestadoresDetailCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.prestadores.detail.html'
                    }
                }
            }).state('prestadoresBorrar', {
                url: 'vivienda/prestadores/(documento:Long)',
                params:{
                    documento:null
                },
                views: {
                    'mainView': {
                        controller: 'prestadoresBorrarCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath+'delete/vivienda.prestadores.borrar.html'
                    }
                }
            }).state('prestadoresCreate', {
                url: 'vivienda/prestadores',
                views: {
                    'mainView': {
                        controller: 'prestadoresCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'new/vivienda.prestadores.create.html'
                    }
                }
            }).state('prestadoresUpdate', {
                url: 'vivienda/prestadores/(documento:Long)',
                params:{
                    documento:null
                },
                views: {
                    'mainView': {
                        controller: 'prestadoresUpdateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'update/vivienda.prestadores.update.html'
                    }
                }
            });
        }]);

})(window.angular);

