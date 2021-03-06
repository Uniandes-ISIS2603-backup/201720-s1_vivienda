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
            }).state('empty',{
                url: '/whatever'
        
            }).state('pisoDetail', {
                url: '/:torreId/:pisoId/detail',
                param: {
                    pisoId: null,
                    torreId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/piso.detail.html',
                        controller: 'pisoDetailCtrl',
                        controllerAs: 'ctrl'
                    }
                }
            }).state('crearPiso',{
                url: '/:torreId/piso/crear',
                param: {
                    torreId: null
                },
                 views: {
                    'mainView' : {
                        templateUrl: basePath + '/new/piso.new.html', 
                        controller : 'pisoNewCtrl', 
                        controllerAs: 'ctrl'
                    }
                }
            }).state('pisoDelete',{
                url: '/:torreId/piso/delete/:pisoId',
                param: {
                    pisoId: null,
                    torreId: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + '/delete/piso.delete.html',
                        controller: 'pisoDeleteCtrl'
                    }                 
                }
            });
    }
]);
    
})(window.angular); 
