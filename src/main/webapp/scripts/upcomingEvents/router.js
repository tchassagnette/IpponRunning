'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/upcomingEvents', {
                    templateUrl: 'views/upcomingEvents.html',
                    controller: 'UpComingEventsController',
                    resolve:{
                        resolvedEvent: ['Event', function (Event) {
                            return Event.query();
                        }]
                    }
                })
        }]);