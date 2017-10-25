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
            $stateProvider.state('prestadorList', {
                // Url que aparecerá en el browser
                url: '/prestador/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'prestador.get.html',
                        controller: 'prestadorCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
