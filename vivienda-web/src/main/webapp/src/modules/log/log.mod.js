(function (ng) {
var mod = ng.module("logModule", []);
    mod.constant("logContext", "api/log");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/log/';

            $stateProvider.state('signin', {
                url: '/signin',
                views: {
                    'mainView': {
                        controller: 'logCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'login.html'
                    }
                }
            });
        }]);

})(window.angular);