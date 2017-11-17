(function (ng) {
    // Definición del módulo
    var mod = ng.module("prestadorModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Prestador/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/prestadorList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('prestador', {
                // Url que aparecerá en el browser
                url: '/prestadores',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'prestador.html',
                        controller: 'prestadorCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestadorList', {
                url: '/list',
                parent: 'prestador',
                views: {
                    'listView': {
                        templateUrl: basePath + 'prestador.list.html',
                        controller: 'prestadorCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestadorCreate', {
                url: '/create',
                parent: 'prestador',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'prestador.new.html',
                        controller: 'prestadorNewCtrl'
                    }
                }
            }).state('prestadorUpdate', {
                url: '/update/{prestadorId:int}',
                parent: 'prestador',
                param: {
                    prestadorId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'prestador.new.html',
                        controller: 'prestadorUpdateCtrl'
                    }
                }
            }).state('prestadorDetail', {
                url: '/{prestadorId:int}/detail',
                parent: 'prestador',
                param: {
                    prestadorId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'prestador.detail.html',
                        controller: 'prestadorCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('prestadorDelete', {
                url: '/delete/{prestadorId:int}',
                parent: 'prestador',
                param: {
                    prestadorId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'prestador.delete.html',
                        controller: 'prestadorDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
