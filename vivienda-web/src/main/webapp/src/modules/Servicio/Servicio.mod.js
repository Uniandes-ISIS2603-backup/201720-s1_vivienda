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
            $stateProvider.state('mensajeList', {
                // Url que aparecerá en el browser
                url: '/mensaje/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'mensaje.get.html',
                        controller: 'mensajeCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
