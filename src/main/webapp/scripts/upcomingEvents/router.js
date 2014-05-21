'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/upcomingEvents', {
                    templateUrl: 'views/upcomingEvents.html',
                    controller: 'UpComingEventsController',
                    resolve:{
                        events: ['UpComingEvents', function (UpComingEvents) {
                            return UpComingEvents.query();
                        }]
                    }
                })
        }]);