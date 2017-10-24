(function (ng) {
    var app = angular.module('mainApp', [
        // External dependencies
        'ui.router',
        
        // Internal modules dependencies       
        'viviendaEstudianteModule',
        'viviendaSugerenciaModule',
        'administradorModule',
        'mensajeModule',
        'torreModule',
        'pisoModule'
        

    ]);
    // Resuelve problemas de las promesas
    app.config(['$qProvider', function ($qProvider) {
            $qProvider.errorOnUnhandledRejections(false);
        }]);
    
})(window.angular);
