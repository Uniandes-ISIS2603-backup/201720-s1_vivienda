(function (ng) {
    var mod = ng.module("pisoModule");
    mod.constant("pisosContext", "api/torres/");

    mod.controller('pisoDeleteCtrl', ['$scope', '$http', 'pisosContext', '$state',
        function ($scope, $http, pisosContext, $state) {
            var id = $state.params.pisoId;
            
        
            $scope.deletePiso = function () {
                $http.delete(pisosContext + torreId +'/'+ "piso" + '/'+ id, {}).then(function (response) {
                    $state.go('pisoList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);