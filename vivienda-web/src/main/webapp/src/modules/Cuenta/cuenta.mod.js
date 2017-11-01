(function (ng) {
    // Definición del módulo
    var mod = ng.module("cuentaModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Cuenta/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/cuentaList");
            // Definición del estado 'authorsList' donde se listan los autores
             $stateProvider.state('cuenta', {
                // Url que aparecerá en el browser
                url: '/cuentas',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cuenta.html',
                        controller: 'cuentaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('cuentaList', {
                // Url que aparecerá en el browser
                url: '/list',
                parent: 'cuenta',
                views: {
                    'listView': {
                        templateUrl: basePath + 'cuenta.get.html',
                        controller: 'cuentaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('cuentaDetail', {
                url: '/{cuentaId:int}/detail',
                parent: 'cuenta',
                param: {
                    cuentaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'cuenta.detail.html',
                        controller: 'cuentaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('cuentaDelete', {
                url: '/delete/{cuentaId:int}',
                parent: 'cuenta',
                param: {
                    cuentaId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'cuenta.delete.html',
                        controller: 'cuentaDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
