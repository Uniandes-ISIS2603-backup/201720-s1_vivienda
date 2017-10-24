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
            $stateProvider.state('tarjetaList', {
                // Url que aparecerá en el browser
                url: '/tarjeta/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'tarjeta.get.html',
                        controller: 'tarjetaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
