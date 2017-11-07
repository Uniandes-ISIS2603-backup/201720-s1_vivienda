(function (ng) {
    var mod = ng.module("pisoModule");
    mod.constant("pisosContext", "api/pisos");

    mod.controller('pisoDeleteCtrl', ['$scope', '$http', 'torresContext', '$state',
        function ($scope, $http, torresContext, $state) {
            var id = $state.params.id;
            $scope.deletePsio = function () {
                $http.delete(pisosContext + '/'+ id, {}).then(function (response) {
                    $state.go('pisoList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);