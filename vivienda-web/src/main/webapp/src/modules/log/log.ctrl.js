(function (ng) {

    var mod = ng.module("logModule");

    mod.controller("logCtrl", ['$scope', '$state', '$stateParams', '$http', 'logContext', function ($scope, $state, $stateParams, $http, context) {

//            // inicialmente el listado de ciudades está vacio
//            $scope.records = {};
//            // carga las ciudades
//            $http.get(context).then(function (response) {
//                $scope.records = response.data;
//            });
//
//            // el controlador recibió un entretenimientoId ??
//            // revisa los parámetros (ver el :entretenimientoId en la definición de la ruta)
//            if ($stateParams.entretenimientoId !== null && $stateParams.entretenimientoId !== undefined) {
//
//                // toma el id del parámetro
//                id = $stateParams.entretenimientoId;
//                // obtiene el dato del recurso REST
//                $http.get(context + "/" + id)
//                        .then(function (response) {
//                            // $http.get es una promesa
//                            // cuando llegue el dato, actualice currentRecord
//                            $scope.currentRecord = response.data;
//                        });
//
//                // el controlador no recibió un entretenimientoId
//            } else {
//                // el registro actual debe estar vacio
//                $scope.currentRecord = {
//                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
//                    nombre: '' /*Tipo String*/,
//                    valor: 0 /*Tipo Double*/,
//                    fechaInicio: '' /*Tipo String*/,
//                    fechaFin: '' /*Tipo String*/,
//                    calificacion: 0 /*Tipo Double*/
//                };
//
//            }
//
//
//            this.saveRecord = function (id) {
//                currentRecord = $scope.currentRecord;
//
//                // si el id es null, es un registro nuevo, entonces lo crea
//                if (id == null) {
//
//                    // ejecuta POST en el recurso REST
//                    return $http.post(context, currentRecord)
//                            .then(function () {
//                                // $http.post es una promesa
//                                // cuando termine bien, cambie de estado
//                                $state.go('entretenimientosList');
//                            });
//
//                    // si el id no es null, es un registro existente entonces lo actualiza
//                } else {
//
//                    // ejecuta PUT en el recurso REST
//                    return $http.put(context + "/" + currentRecord.id, currentRecord)
//                            .then(function () {
//                                // $http.put es una promesa
//                                // cuando termine bien, cambie de estado
//                                $state.go('entretenimientosList');
//                            });
//                }
//                ;
//            };
//
//            this.deleteRecord = function (id) {
//                $http.delete(context + "/" + id);
//                $state.reload('entretenimientosList');
//
//            };

// Código continua con las funciones de despliegue de errores


        }]);
})(window.angular);