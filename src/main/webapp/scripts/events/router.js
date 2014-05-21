'use strict';

ipponrunningApp
    .config(['$routeProvider', '$httpProvider', '$translateProvider',
        function ($routeProvider, $httpProvider, $translateProvider) {
            $routeProvider
                .when('/events', {
                    templateUrl: 'views/event.html',
                    controller: 'EventsController',
                    resolve:{
                        resolvedEvent: ['Event', function (Event) {
                            return Event.query();
                        }]
                    }
                })
        }]);
