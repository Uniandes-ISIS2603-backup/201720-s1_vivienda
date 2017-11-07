(function (ng) {
    // Definición del módulo
    var mod = ng.module("servicioModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Servicio/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/servicioList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('servicio', {
                // Url que aparecerá en el browser
                url: '/servicios',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'servicio.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('servicioCreate', {
                url: '/create',
                parent: 'servicio',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'servicio.new.html',
                        controller: 'servicioNewCtrl'
                    }
                }
            }).state('servicioUpdate', {
                url: '/update/{servicioId:String}',
                parent: 'mensaje',
                param: {
                    mensajeId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'servicio.new.html',
                        controller: 'servicioUpdateCtrl'
                    }
                }
            }).state('servicioList', {
                url: '/list',
                parent: 'servicio',
                views: {
                    'listView': {
                        templateUrl: basePath + 'servicio.list.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('servicioDetail', {
                url: '/{servicioId: String}/detail',
                parent: 'servicio',
                param: {
                    mensajeId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'servicio.detail.html',
                        controller: 'servicioCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('servicioDelete', {
                url: '/delete/{servicioId:String}',
                parent: 'servicio',
                param: {
                    servicioId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'servicio.delete.html',
                        controller: 'servicioDeleteCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
