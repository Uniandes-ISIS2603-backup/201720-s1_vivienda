(function (ng) {
    var mod = ng.module("viviendaEstudianteModule", []);
    mod.constant("viviendaContext", "api/estudiantes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/Estudiante/';
            $urlRouterProvider.otherwise("");

            $stateProvider.state('viviendaEstudianteObtenerTodos', {
                url: 'vivienda/estudiantes',
                views: {
                    'mainView': {
                        controller: 'viviendaEstudianteObtenerTodosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.estudiantes.obtenerTodos.html'
                    }
                }
            }).state('estudiantesDetail', {
                url: 'vivienda/estudiantes/(documento:int)',
                params:{
                    documento:null
                },
                views: {
                    'mainView': {
                        controller: 'estudiantesDetailCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'vivienda.estudiantes.detail.html'
                    }
                }
            }).state('estudiantesBorrar', {
                url: 'vivienda/estudiantes/(documento:int)',
                params:{
                    documento:null
                },
                views: {
                    'mainView': {
                        controller: 'estudiantesBorrarCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath+'delete/vivienda.estudiantes.borrar.html'
                    }
                }
            }).state('estudiantesCreate', {
                url: 'vivienda/estudiantes',
                views: {
                    'mainView': {
                        controller: 'estudiantesCreateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'new/vivienda.estudiantes.create.html'
                    }
                }
            }).state('estudianteError', {
                url: '/error',
                param: {
                    documento: null
                },
                views: {
                    'mainView': {
                        templateUrl: basePath + 'estudiantes.error.html'
                    }
                }
            }).state('estudiantesUpdate', {
                url: 'vivienda/estudiantes/(documento:int)',
                params:{
                    documento:null
                },
                views: {
                    'mainView': {
                        controller: 'estudiantesUpdateCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'update/vivienda.estudiantes.update.html'
                    }
                }
            });
        }]);

})(window.angular);

