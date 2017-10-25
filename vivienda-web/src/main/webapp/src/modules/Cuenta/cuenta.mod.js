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
            $stateProvider.state('cuentaList', {
                // Url que aparecerá en el browser
                url: '/cuenta/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'cuenta.get.html',
                        controller: 'cuentaCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
