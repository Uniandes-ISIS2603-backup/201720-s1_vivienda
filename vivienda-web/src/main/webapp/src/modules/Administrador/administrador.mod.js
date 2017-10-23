(function (ng) {
    // Definición del módulo
    var mod = ng.module("administradorModule", ['ui.router']);

    // Configuración de los estados del módulo
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            // En basePath se encuentran los templates y controladores de módulo
            var basePath = 'src/modules/Administrador/';
            // Mostrar la lista de autores será el estado por defecto del módulo
            $urlRouterProvider.otherwise("/administradorList");
            // Definición del estado 'authorsList' donde se listan los autores
            $stateProvider.state('administradorList', {
                // Url que aparecerá en el browser
                url: '/administrador/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'administrador.get.html',
                        controller: 'adminCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
