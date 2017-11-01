(function(ng){
    //Definicion del modulo
    var mod = ng.module("pisoModule", ['ui.router']); 
    //Configuracion de los estados del modulo
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            //En basePath se encuentran los templates y controladores de modulo 
            var basePath = 'src/modules/Piso'; 
            //Mostrar la lista de torres sea el estado por defecto del modulo
            $urlRouterProvider.otherwise("/pisoList");
            //Definicion del estado 'torreList' donde se listen las torres
            $stateProvider.state('pisoList', {
                //Url que aparecera en el browser 
                url : '/:torreId/piso/list',
                param: {
                    torreId: null
                },
                views: {
                    'mainView' : {
                        templateUrl: basePath + '/piso.get.html', 
                        controller : 'pisoCtrl', 
                        controllerAs: 'ctrl'
                    }
                }
            });
    }
]);
    
})(window.angular); 
