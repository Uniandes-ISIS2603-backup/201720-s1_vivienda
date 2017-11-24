(function (ng) {
    // Definición del módulo
    var mod = ng.module("mensajeModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Mensaje/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/mensajeList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('mensaje', {
                // Url que aparecerá en el browser
                url: '/mensajes',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'mensaje.html',
                        controller: 'mensajeCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('mensajeCreate', {
                url: '/create',
                parent: 'mensaje',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'mensaje.new.html',
                        controller: 'mensajeNewCtrl'
                    }
                }
            }).state('mensajeUpdate', {
                url: '/update/{mensajeId:int}',
                parent: 'mensaje',
                param: {
                    mensajeId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'mensaje.new.html',
                        controller: 'mensajeUpdateCtrl'
                    }
                }
            }).state('mensajeList', {
                url: '/list',
                parent: 'mensaje',
                views: {
                    'listView': {
                        templateUrl: basePath + 'mensaje.list.html',
                        controller: 'mensajeCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('mensajeDetail', {
                url: '/{mensajeId:int}/detail',
                parent: 'mensaje',
                param: {
                    mensajeId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'mensaje.detail.html',
                        controller: 'mensajeCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('mensajeDelete', {
                url: '/delete/{mensajeId:int}',
                parent: 'mensaje',
                param: {
                    mensajeId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'mensaje.delete.html',
                        controller: 'mensajeDeleteCtrl'
                    }
                }
            }).state('mensajeError', {
                url: '/error',
                parent: 'mensaje',
                param: {
                    mensajeId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'mensaje.error.html'
                    }
                }
            });
        }
    ]);
})(window.angular);
