(function (ng) {
    // Definición del módulo
    var mod = ng.module("administradorModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Administrador/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/adminList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('administrador', {
                // Url que aparecerá en el browser
                url: '/administradores',
                abstract: true,
                views: {
                    'mainView': {
                        templateUrl: basePath + 'administrador.html',
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('adminList', {
                url: '/list',
                parent: 'administrador',
                views: {
                    'listView': {
                        templateUrl: basePath + 'administrador.list.html',
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('adminCreate', {
                url: '/create',
                parent: 'administrador',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.new.html',
                        controller: 'adminNewCtrl'
                    }
                }
            }).state('adminUpdate', {
                url: '/update/{adminId:int}',
                parent: 'administrador',
                param: {
                    adminId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.new.html',
                        controller: 'adminUpdateCtrl'
                    }
                }
            }).state('adminDetail', {
                url: '/{adminId:int}/detail',
                parent: 'administrador',
                param: {
                    adminId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.detail.html',
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('adminDelete', {
                url: '/delete/{adminId:int}',
                parent: 'administrador',
                param: {
                    adminId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.delete.html',
                        controller: 'adminDeleteCtrl'
                    }
                }
            }).state('adminError', {
                url: '/error',
                parent: 'administrador',
                param: {
                    adminId: null
                },
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.error.html'
                    }
                }
            }).state('ordenPagada', {
                url: '/ordenPagada',
                parent: 'administrador',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.ordenpago.html',
                        controller: 'ordenPagadaCtrl'
                    }
                }
            }).state('ordenNoPagada', {
                url: '/ordenNoPagada',
                parent: 'administrador',
                views: {
                    'detailView': {
                        templateUrl: basePath + 'administrador.ordenpago.html',
                        controller: 'ordenNoPagadaCtrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
