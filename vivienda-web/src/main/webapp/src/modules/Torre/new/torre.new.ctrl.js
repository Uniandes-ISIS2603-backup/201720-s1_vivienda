(function (ng) {
	    var mod = ng.module("torreModule");
	    mod.constant("torresContext", "api/torres");
	    mod.controller('torreNewCtrl', ['$scope', '$http', 'torresContext', '$state', '$rootScope',
	        function ($scope, $http, torresContext, $state, $rootScope) {
	            $rootScope.edit = false;
	            $scope.createTorre = function () {
	                $http.post(torresContext, {
	                    id: $scope.id,
	                    estado: $scope.estado
	                }).then(function (response) {
	                    //Usuario created successfully
	                    $state.go('torreList', {id: response.data.id}, {reload: true});
	                });
	            };
	        }
	    ]);
	}
	)(angular);