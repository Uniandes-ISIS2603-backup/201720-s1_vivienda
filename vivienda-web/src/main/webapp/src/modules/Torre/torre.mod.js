/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
    //Definicion del modulo
    var mod = ng.module("torreModule", ['ui.router']); 
    //Configuracion de los estados del modulo
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider){
            //En basePath se encuentran los templates y controladores de modulo 
            var basePath = 'src/modules/Torre'; 
            //Mostrar la lista de torres sea el estado por defecto del modulo
            $urlRouterProvider.otherwise("/torreList");
            //Definicion del estado 'torreList' donde se listen las torres
            $stateProvider.state('torreList', {
                //Url que aparecera en el browser 
                url : '/torre/list',
                views: {
                    'mainView' : {
                        templateUrl: basePath + '/torre.get.html', 
                        controller : 'torreCtrl', 
                        controllerAs: 'ctrl'
                    }
                }
            });
    }
]);
    
})(window.angular); 


