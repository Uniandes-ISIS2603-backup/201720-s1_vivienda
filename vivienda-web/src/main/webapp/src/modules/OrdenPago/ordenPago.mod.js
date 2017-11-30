(function (ng) {
    // Definición del módulo
    var mod = ng.module("ordenPagoModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/OrdenPago/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/ordenPagoList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('ordenPago', {
                // Url que aparecerá en el browser
                url: '/ordenPagos',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ordenPago.html',
                        controller: 'ordenPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ordenPagoList', {
                // Url que aparecerá en el browser
                url: '/list',
                parent: 'ordenPago',
                views: {
                    'listView': {
                        templateUrl: basePath + 'ordenPago.get.html',
                        controller: 'ordenPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ordenPagoDetail', {
                url: '/{ordenPagoId:int}/detail',
                parent: 'ordenPago',
                param: {
                    ordenPagoId: null
                },
                views: {
                    'detailView': {
                       
                        templateUrl: basePath + 'ordenPago.detail.html',
                        controller: 'ordenPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('ordenError', {
                url: '/error',
                parent: 'ordenPago',
                param: {
                    ordenPagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ordenPago.error.html'
                    }
                }
            }).state('ordenPagoDelete', {
                url: '/delete/{ordenPagoId:int}',
                parent: 'ordenPago',
                param: {
                    ordenPagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ordenPago.delete.html',
                        controller: 'ordenPagoDeleteCtrl'
                    }
                }
            }).state('ordenPagoUpdate', {
                url: '/update/{ordenPagoId:int}',
                parent: 'ordenPago',
                param: {
                    ordenPagoId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ordenPago.update.html',
                        controller: 'ordenPagoUpdateCtrl'
                    }
                }
            }).state('ordenPagoCreate', {
                url: '/create',
                parent: 'ordenPago',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'ordenPago.new.html',
                        controller: 'ordenPagoNewCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
