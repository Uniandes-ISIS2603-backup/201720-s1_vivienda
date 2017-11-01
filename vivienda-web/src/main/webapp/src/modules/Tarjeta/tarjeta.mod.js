(function (ng) {
    // Definición del módulo
    var mod = ng.module("tarjetaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Tarjeta/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/tarjetaList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('tarjeta', {
                // Url que aparecerá en el browser
                url: '/tarjetas',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjeta.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetaList', {
                url: '/list',
                parent: 'tarjeta',
                views: {
                    'listView': {
                        templateUrl: basePath + 'tarjeta.get.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetaDetail', {
                url: '/{tarjetaId:int}/detail',
                parent: 'tarjeta',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'tarjeta.detail.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('tarjetaDelete', {
                url: '/delete/{tarjetaId:int}',
                parent: 'tarjeta',
                param: {
                    tarjetaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'tarjeta.delete.html',
                        controller: 'tarjetaDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
