'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/event/:id', {
                    templateUrl: 'views/eventDetail.html',
                    controller: 'EventDetailController',
                });
        }]);
