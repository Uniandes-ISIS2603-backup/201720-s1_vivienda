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
            $stateProvider.state('ordenPagoList', {
                // Url que aparecerá en el browser
                url: '/ordenPago/list',
                views: {
                    'mainView': {
                        templateUrl: basePath + 'ordenPago.get.html',
                        controller: 'ordenPagoCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            });
        }
    ]);
})(window.angular);
