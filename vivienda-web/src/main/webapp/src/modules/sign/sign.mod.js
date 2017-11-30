(function (ng) {
var mod = ng.module("signModule", []);
    mod.constant("signContext", "api/sign");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/sign/';

            $stateProvider.state('signUp', {
                url: '/signUp',
                views: {
                    'mainView': {
                        controller: 'signCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sign.html'
                    }
                }
            });
        }]);

})(window.angular);