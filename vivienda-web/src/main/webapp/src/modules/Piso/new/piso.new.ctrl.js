(function(ng){
    var mod = ng.module("pisoModule");
    mod.constant("pisoContext", "torres/");
    mod.constant("pisoContext", "piso");
    mod.controller('pisoNewCtrl',['$scope','$http','pisoContext', '$stateParams',
        function($scope, $http, pisoContext, $stateParams){
        	var id = $stateParams.torreId;
            $http.post(pisoContext + id + '/' + pisoContext, {
                disponible: false
            }).then(function(response){
                $state.go('pisoList', {torreId: id}, {reload:true})
            });
        }
    ]);
})(angular); 