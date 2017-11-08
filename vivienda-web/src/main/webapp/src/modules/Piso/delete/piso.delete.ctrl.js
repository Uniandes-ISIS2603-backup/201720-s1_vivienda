(function (ng) {
    var mod = ng.module("pisoModule");
    mod.constant("pisosContext", "api/torres/");

    mod.controller('pisoDeleteCtrl', ['$scope', '$http', 'pisosContext', '$state', '$stateParams',
        function ($scope, $http, pisosContext, $state, $stateParams) {
            var id = $state.params.pisoId;
            
        
            $scope.deletePiso = function () {
                $http.delete(pisosContext + $stateParams.torreId +'/'+ "piso" + '/'+ id, {}).then(function (response) {
                    $state.go('torreList', {reload: true});
                });
            };
        }
    ]);
}
)(angular);