(function (ng) {
    var mod = ng.module("torreModule");
    mod.constant("torresContext", "api/torres");
    mod.controller('torreDeleteCtrl', ['$scope', '$http', 'torresContext', '$state',
        function ($scope, $http, torresContext, $state) {
            var id = $state.params.id;
            $scope.deleteTorre = function () {
                $http.delete(torresContext + '/' + id, {}).then(function (response) {
                    $state.go('torreList', {id: response.data.id}, {reload: true});
                });
            };
        }
    ]);
}
)(angular);